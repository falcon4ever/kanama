# Architecture Review — June 2026

External review of the Kanama runtime architecture, target support
(Desktop/Android/iOS), and performance posture, performed against the
0.2.2 preview line (now Godot 4.7 stable; originally reviewed against the
4.7 rc 2 baseline). Finding F1 was fixed in the same pass; F2 is now
validated with Kanama's PanamaPort fork for R8/minified Android exports;
F3–F4 have since been addressed as bounded follow-ups in
[wrapper-coverage-tracker.md](../active/wrapper-coverage-tracker.md).

## Verdict

The architecture is sound. The core design — C bootstrap
(`bootstrap/bootstrap.c`) performing a one-time JNI handoff
(`JNI_CreateJavaVM` plus three JNI calls into `KanamaBinding.init`),
with all subsequent Godot ⇄ JVM traffic over Panama FFM — is the right
model for a JVM-hosted GDExtension binding. The per-platform runtime
choices (embedded JDK on desktop, ART + PanamaPort on Android,
Kotlin/Native AOT + C shim on iOS) are each the correct fit for their
platform constraints.

Strong points verified in code:

- **No reflection in hot paths.** KSP codegen
  (`processor/src/main/kotlin/net/multigesture/kanama/processor/KanamaProcessor.kt`)
  emits per-class registrars with pre-compiled dispatch lambdas.
- **Fast virtual dispatch.** `_process` / `_physics_process` names are
  pre-interned StringNames compared by long equality
  (`src/main/kotlin/binding/ScriptBridge.kt`), with a thread-local
  scratch buffer for delta unmarshalling.
- **GC-safe object identity.** `ObjectRegistry`
  (`src/main/kotlin/binding/ObjectRegistry.kt`) hands Godot monotonic
  long handles — never JVM identity hashes or addresses — with a dense
  `AtomicReferenceArray` fast path and `ConcurrentHashMap` fallback.
- **One shared script-instance vtable.** A single permanently-allocated
  `GDExtensionScriptInstanceInfo3` struct serves every instance;
  instances differ only by opaque handle (`ScriptBridge.kt`).
- **Lazy MethodHandle caching.** Every GDExtension interface function is
  looked up once and cached (`ClassDB.kt`, `VariantConverters.kt`,
  `BuiltinTypes.kt`).
- **Disciplined arena usage.** Process-long arena for upcall stubs and
  interned names; confined arenas for call temporaries; thread-local
  auto-arena scratch on per-frame paths.
- The suspicious-looking `REFCOUNTED_REFERENCE_HASH ==
  REFCOUNTED_UNREFERENCE_HASH` (both `2240911060`) in `ScriptBridge.kt`
  was verified legitimate against `extension_api.json`: `reference()`
  and `unreference()` share the `bool()` signature, so their hashes are
  identical by construction.

## Target Assessment

### Desktop (macOS arm64 / Linux x64+arm64 / Windows x64)

Correct model: native bootstrap dylib/so/dll discovers `libjvm` via
`JAVA_HOME` with platform fallbacks, embeds JDK 25+, loads `kanama.jar`
found relative to the dylib. Windows/Linux stable revalidation remains
process debt, not an architecture problem — the runtime path is
platform-independent above the bootstrap.

### Android (experimental)

The PanamaPort approach — string-level source remap of
`java.lang.foreign.*` → `com.v7878.foreign.*` plus
`invokeWithArguments` rewriting, guarded by pre/post audits
(`android/godot-plugin/plugin/build.gradle.kts`) — is unusual but
pragmatic. It is inherently fragile (textual rewriting of semantics),
and the audit gates are the right mitigation. ABI coverage (arm64-v8a +
x86_64), minSdk 26, and the Godot AAR plugin integration are all
reasonable. See F2 for the forked-PanamaPort R8 path.

### iOS (experimental, Kotlin/Native)

The Kotlin/Native AOT + C ptrcall shim (`ios/bootstrap/kanama_ios_shim.c`)
is the only viable model on iOS (no JIT/JVM permitted), and keeping the
same generated wrapper API surface across backends is the right
abstraction. The deliberate incompleteness (audited type set, unwired
annotations, stubbed `GodotObject.call`/`setDeferred`) is well-documented
in `internals/active/ios-backend-roadmap.md` and — critically — guarded at build
time (`scripts/check_ios_no_silent_stubs.py`, unwired-annotation
warnings), so gaps surface instead of silently misbehaving. The
roadmap's priorities are correct; in particular the move from the
regex-based annotation parser (`ios-runtime/build.gradle.kts`) to a KSP
model should stay near the top: regex parsing of Kotlin source is the
most likely future source of subtle mis-registration bugs.

## Findings

### F1 — Bug (fixed): `compatibility_minimum = "4.3"` vs. 4.7-only APIs

`ClassDB.kt` resolves `classdb_register_extension_class6`, which is
`@since 4.7` (`gdextension/gdextension_interface.h`). Every
`.gdextension` descriptor declared `compatibility_minimum = "4.3"`, so
on Godot 4.3–4.6 the extension would load and then fail at class
registration when `get_proc_address` returns NULL. Fixed: all descriptor
sources (generators in `build.gradle.kts`, `example_project` addon,
Android plugin assets) now declare `4.7`, matching the actual baseline.

### F2 — Fixed with fork: R8/minify via PanamaPort

Obfuscation-resistant script packaging is a stated project motivation,
yet the Android plugin shipped no keep rules and no consumer ProGuard
wiring. Scaffolded in this pass:
`android/godot-plugin/plugin/consumer-rules.pro` (JNI bootstrap entry,
Panama upcall targets, KSP registrars, annotations) wired via
`consumerProguardFiles`.

Validation on a Pixel 7 (2026-06-26) first root-caused why an
R8-minified APK failed at runtime: upstream PanamaPort `v0.1.3` crashes
inside `nativeLinker().downcallHandle()` (`AssertionError: Should not
reach here`) after `KanamaBinding.init` starts. PanamaPort's Android
linker uses Java pattern-matching `switch`es over sealed types
(`_LLVMStorageDescriptor` storages, the `MemoryLayout` hierarchy) that
Godot 4.7's R8 (AGP 8.6.1) mis-optimizes into the
`default -> shouldNotReachHere()` branch. It is a hard contradiction for
consumer rules: keeping the sealed types blocks the optimization
PanamaPort's own `@CheckDiscard` rules require, and not keeping them
leaves the switch broken at runtime.

Kanama now consumes a forked artifact,
`com.github.falcon4ever.PanamaPort:Core:0.1.3-kanama-r8.2`, that rewrites the
affected source switch sites to explicit `instanceof` chains and applies
targeted R8 annotations. `consumer-rules.pro` keeps Kanama's own
reflection/JNI surface, keeps no broad `com.v7878.**` classes, and only
`-dontwarn`s PanamaPort. `scripts/android_export_minified.sh` validated a
minified Match3 release APK on Pixel 7: bootstrap installed Panama upcall
stubs, registered the `.kt` loader, and avoided the prior
`Should not reach here` / `Error loading extension` failure.

### F3 — Performance follow-up: per-call confined arenas in generated wrappers

`src/main/kotlin/binding/runtime/ObjectCalls.kt` (generated, ~1.5 MB)
contains ~1,478 `Arena.ofConfined().use { ... }` blocks — one per
wrapper call. A thread-local `PtrcallScratch` already exists and covers
some shapes (bool/int/long/double/object/vector2/rect2/color cells).
Follow-up: extend the scratch pattern in the wrapper *generator* to all
fixed-size scalar/vector argument+return shapes, keeping confined arenas
only for variable-size returns (String, Array, Packed*) where
destroy-after-read is required. Confined arena create/close is cheap
individually, but it is measurable overhead on per-frame call chains
(transform getters/setters, input polling).

**Reassessed 2026-07 (task 12) — recommend deferring until profile-driven.**
Two premises above no longer hold after the convergence work:

- **`ObjectCalls.kt` is hand-written, not generated.** Post-convergence the
  single generator (`scripts/generate_api_wrapper.py`) emits only the *iOS*
  `ObjectCallsGenerated.kt`; the desktop `ObjectCalls.kt` is the hand-written
  reference template. So there is no "change the generator + regenerate" path —
  any change is a direct hand-transform of the 40k-line file.
- **The hot subset is already done.** The F3 first pass moved exactly the
  per-frame no-arg fixed-size *return* getters (Vector2/3/3i/2i, Quaternion,
  Basis, Transform3D, Rect2, Color, Array/Object list rets) onto the thread-local
  `PtrcallScratch`. Those are the transform/vector polling calls that dominate a
  per-frame chain.

Remaining scope of the 1,506 confined-arena helpers: only **~4** are safe
fixed-size no-arg return conversions still outstanding (Rect2i/AABB/Plane/
Transform2D); **~29** no-arg returns are *variable-size* and must keep arenas
(String, `Packed*List`, Array, Dictionary, Callable, typed lists — destroy-after-
read); the other **~1,473 take arguments** and would need a scratch matrix keyed
by (type × arg-position) plus per-N args-pointer arrays — an aliasing-sensitive,
memory-corruption-prone refactor for modest, unmeasured, per-frame benefit.

Conclusion: the highest-value/lowest-risk slice shipped in F3. The remainder is
premature optimization without a profiled bottleneck — defer until a real
workload measures a per-frame arena cost worth the risk, then convert only the
specific hot helpers that show up, with dedicated non-aliasing cells + a
Bunnymark/A-B measurement.

### F4 — Maintenance follow-up (fixed): scattered Godot version pins

The Godot 4.7-rc2 pin appears independently in the iOS export template
path (`build.gradle.kts`), CI (`.github/workflows/package.yml`
`GODOT_VERSION`), and docs. Acceptable for a preview line, but a single
source of truth (one Gradle property consumed everywhere) would prevent
a partial-upgrade mismatch on the next baseline bump.

Fixed after the review: `kanamaGodotVersion` in `gradle.properties` is now
the source pin, with `scripts/check_godot_version_pin.py` guarding the CI dash
form and API metadata.

### F5 — Observations (no action)

- `ObjectRegistry` dense array never shrinks (worst case ~8 MB at 2^20
  handles) and duplicates the CHM storage — acceptable trade for lookup
  speed.
- Threading: FFM upcall stubs auto-attach calling threads, and the
  registry/caches are CHM-based, so concurrent engine callbacks (e.g.,
  multithreaded physics) won't corrupt runtime state — but user script
  code reached via dispatch lambdas would need its own thread safety.
  Worth a docs note if/when threaded servers are validated.
- Android minSdk 26 and OpenGL-Compatibility-only rendering are
  reasonable experimental-phase boundaries and are documented.
