# Changelog

All notable user-facing changes will be recorded here.

This project uses a Keep a Changelog-style format and follows semantic
versioning once public releases begin.

## Unreleased

### Added

- Fixed iOS lambda signal callbacks dropping non-object scalar arguments.
  Emitted `bool`, `int`, and `float` Variants now reach Kotlin with their
  original values, matching desktop and Android; this fixes integer peer IDs
  from multiplayer signals such as `peer_connected` arriving as `null`.
- Fixed script lifecycle notifications re-enabling `_process` and
  `_physics_process` after user code disabled them in `_ready` or
  `_enter_tree`. Authority-gated multiplayer input scripts now remain disabled
  on non-authoritative peers instead of letting one device drive multiple
  players.
- Fixed iOS value-type `@ScriptProperty` fields being write-only. `Vector2`,
  `Vector3`, `String`, `NodePath`, and `List<String>` are now readable by the
  engine (`Object.get`), matching desktop/Android — the iOS bridge previously
  emitted a reader only for properties with a scalar setter, so these types
  were settable from scene data but read back as `nil`. That silently broke
  `MultiplayerSynchronizer` replication of value-type properties on the
  authority peer (e.g. a `Vector2` movement vector never reaching the host, so
  a client could not move or shoot). A codegen parity guard now flags any
  future set-only data property.
- Fixed iOS lambda/bound signal `Callable`s not being auto-disconnected when
  their receiver is freed. The receiver's `ObjectID` is now recorded so Godot
  disconnects the connection on free, instead of later firing the signal into a
  freed object — a use-after-free crash on the client when a multiplayer host
  ended the game.
- iOS `@Export` / `@ScriptProperty` conversion parity: Kotlin `Int`/`Float`
  fields now narrow correctly from Godot's 64-bit Variant slots, scalar enum
  ordinals clamp and resolve to entries, and `List<Enum>` arrays map each
  ordinal with the same clamp/null-fill semantics as desktop/Android. A
  dedicated integer-array bridge keeps enum ordinals separate from object
  handles. iOS property metadata now preserves enum hints and live
  ScriptInstance getters widen values back to engine slots; scene-loaded
  values plus later `Object.set` / `Object.get` updates are covered by the iOS
  project-script probe.
- **iOS and Android promoted from Experimental to Supported (4.7 stable)**: the
  §7 mobile promotion bar (device-matrix breadth, renderer coverage, release-grade
  packaging, heavy-demo) is fully green on both platforms. Android is
  device-validated across four models (Pixel 7 / Moto g 5G 2023 / Galaxy S10+ /
  Pixel 3 XL) — **debug validated to Android 9; release builds require Android
  13+** (validated 14/16; the A12 release-mode PanamaPort constraint is
  documented and upstream-shaped). iOS is device-validated on iPhone 12 + iPhone
  15 Pro. Carried caveats: packaged mobile addons are runtime-only (compiling
  project scripts needs the Kanama checkout), the Android release path depends on
  the JitPack PanamaPort fork (`0.1.3-kanama-r8.4`), no mobile hot reload, and
  task-26 multiplayer UI polish + the cross-platform join matrix remain
  non-blocking follow-ups. `0.3.0` remains a pre-1.0 preview baseline.
- Linux x86_64 promoted to **Supported (4.7 stable)**: full local CI, native
  bootstrap preflight, strict docs, all 11 demo builds, the nine-demo desktop
  smoke matrix, TPS checked smoke, distribution packaging, and
  desktop-kit/store-addon install smokes passed on Ubuntu 25.04 with Godot
  `4.7.stable.official.5b4e0cb0f` and OpenJDK 25.0.2 (2026-07-13/14). Requires
  the resource-loader/saver teardown fix below.
- Linux arm64 promoted to **Supported (4.7 stable)**: the same full gate suite
  (local CI incl. the resource-loader/saver teardown `runtime_smoke`, strict
  docs, all 11 demo builds, nine-demo desktop smoke matrix, TPS checked smoke,
  distribution packaging, desktop-kit/store-addon install smokes, and an AArch64
  ELF native preflight) passed on a native Ubuntu 26.04 AArch64 host with Godot
  `4.7.stable.official.5b4e0cb0f` and OpenJDK 25.0.3 (2026-07-14).
- Fixed a shutdown hang on Linux x86_64: `KanamaResourceFormatLoader` and
  `KanamaResourceFormatSaver` now destroy their explicitly constructed handler
  objects during `unregister()`, before `ClassDB` tears down their extension
  classes. Leaving the live instances behind corrupted/blocked shutdown on Linux
  (orphan `StringName` and allocator diagnostics); desktop processes now exit
  cleanly.
- Windows x86_64 promoted to **Supported (4.7 stable)**: full local
  revalidation on the 4.7 stable console binary (2026-07-13) — demo audits,
  script builds, imports, nine-demo desktop runtime smoke, TPS smoke, and the
  packaged desktop-kit/store-addon install smokes.
- iOS `Mathf.roundToInt` parity helper (Godot `roundi` semantics, half away
  from zero), matching the desktop `Mathf.roundToInt` facade; demo ports can
  now share the same rounding call on both backends.
- `.gdextension` descriptors written by the addon install and packaging tasks
  now always carry the `ios.*.arm64` xcframework entries. Installing the addon
  from Windows/Linux no longer strips the iOS lines from a project's committed
  descriptor (Godot ignores non-target platform entries).
- iOS wrapper breadth (task 30): the generated iOS Godot API class set grew from
  ~254 to **1017 classes — full desktop-equivalent breadth**. Every desktop
  generated class is now emitted on iOS except the documented exceptions
  (`DirAccess`, whose draft depends on a desktop-only hand-authored
  handle-alias class, and `MethodTweener`, which clashes with the hand-written
  iOS Tween glue; `FileAccess` is since hosted as a hand-written static facade). Un-audited per-method marshalling shapes remain conservatively
  skipped with generator-report entries — never stubbed — and the full
  cross-platform drift gate holds committed == fresh regen at the new counts.
- iOS RefCounted return ownership (task 31 mirror): the C shim now exposes
  `object_destroy`, the generated iOS `RefCounted` owns the +1 reference every
  RefCounted-typed ptrcall return transfers (`close()` releases it: unreference +
  destroy at zero), and fluent self-returning methods emit the same
  reference-neutral collapse pattern as desktop/Android. This closes the
  per-call engine-reference leak on RefCounted-typed returns on iOS; an
  on-device refcount probe (`refcounted-ret-owns-plus1`) guards the convention.
- Resource-typed exports widened
  ([#36](https://github.com/falcon4ever/kanama/issues/36)): `@Export` /
  `@ScriptProperty` slots now accept `AudioStream`, `Texture`, `Mesh`,
  `Shape2D`, `Shape3D`, `Font`, `Animation`, and `StyleBox` (base-typed slots
  accept engine subtypes). Reads stay ownership-managed: retained while held,
  released exactly once on script cleanup.
- Subclassing a generated wrapper (e.g. `class X(...) : Resource(...)`) now
  fails the build with a Kanama error naming the supported pattern
  (`@ScriptClass(attachTo = "Resource")` on a plain class) instead of Kotlin's
  opaque internal-constructor error. `lateinit` exports now warn (no inspector
  default; crashes if read before assignment) and point to the nullable
  `= null` shape. Docs: "Resource Slots" + expanded "Custom Resources" in the
  exports guide.
- `@Export` / `@ScriptProperty` now works on Kotlin `enum class` properties
  ([#37](https://github.com/falcon4ever/kanama/issues/37)). Matching C# enum
  exports, the property registers as an `int` with `PROPERTY_HINT_ENUM` and the
  entry names as the hint string, so the inspector renders a dropdown; values
  are stored as entry ordinals, `.tscn`-stored ints deserialize back into the
  enum slot, and out-of-range stored values clamp to a valid entry instead of
  crashing. Enum-entry initializers (`var mode = MyEnum.NORMAL`) are preserved
  as inspector defaults. `@ScriptClass` scripts work across desktop, Android,
  and iOS.
- `@Export` / `@ScriptProperty` now also works on **lists of Kotlin enums**
  (`List<MyEnum>` / `MutableList<MyEnum>`,
  [#40](https://github.com/falcon4ever/kanama/issues/40)). Matching C# enum-array
  exports, the property registers as a typed int Array whose elements carry
  `PROPERTY_HINT_ENUM` with the entry names (the inspector renders an array of
  dropdowns); elements are stored as ordinals, `.tscn`-stored arrays deserialize
  back into the list, and out-of-range stored elements clamp to a valid entry.
  Same scope as scalar enum exports: `@ScriptClass` scripts work across desktop,
  Android, and iOS. `Map<K, V>` exports remain out of scope (non-String
  Dictionary keys are a policy gate).
- `@OverrideVirtual` now supports **every Variant-expressible return type** in
  the Godot 4.7 virtual surface (170 additional virtuals). New Kotlin return
  types: all fixed-element packed arrays (`ByteArray`, `IntArray`, `LongArray`,
  `FloatArray`, `DoubleArray`, `List<Vector2>`, `List<Vector3>`, `List<Color>`),
  `Map<String, Any?>` (Dictionary), `List<Any?>` (generic and typed Arrays),
  `RID`, `Rect2`, `AABB`, `Transform2D`, `Transform3D`, and `Projection`.
  `StringName`-returning virtuals are declared as `String` and enum/bitfield
  returns as `Long` (the engine converts at the call site). Desktop/Android
  cover all families; iOS covers everything except
  `Rect2`/`AABB`/`Transform2D`/`Transform3D`/`Projection` returns (documented
  in the wrapper-maintenance guide). The only excluded virtuals are the 6
  raw-pointer (`void*`/`const Glyph*`) callbacks, which are not
  Variant-expressible by design.

### Fixed

- `ClassDB.instantiate` no longer frees RefCounted instances before returning
  them ([#42](https://github.com/falcon4ever/kanama/pull/42)): the engine hands
  the fresh instance's **only** reference back inside the return Variant, and
  the borrowed dynamic decode destroyed that Variant after extracting the
  pointer — a use-after-free that crashed or silently dropped anything
  instantiated by class name (the canonical construction path for third-party
  GDExtension classes such as Terrain3D). RefCounted results are now retained
  before the Variant is destroyed and come back as the owning `RefCounted`
  wrapper (`close()` releases; assigning into a node/scene refs independently,
  matching C# semantics). Non-RefCounted results (Nodes) are unchanged.
  Desktop, Android, and iOS (dedicated C-shim entry: the retain must happen
  before the shim destroys the return Variant). Thanks @T-bond for the
  diagnosis and repro.
- Android runtime now works below Android 14: the binding runtime used
  `Path.of(...)` (a Java 11 API Android only ships from API 34) in the `.kt`
  resource loader/saver and the scripts-jar lookup, so script loading died
  with `NoSuchMethodError` on older devices; switched to the equivalent
  `Paths.get(...)` (API 26+). Found and device-validated on a Galaxy S10+
  (Android 12) during the second-device promotion gate, together with a
  PanamaPort-fork fix for the same class of bug (`SDK_INT_FULL` reads that
  crash pre-Android-16 devices).
- Android release builds no longer hit an R8-emitted `filled-new-array` of
  `byte[][]` in PanamaPort's stub generation (the ART interpreter below
  Android 13 segfaults on that instruction): PanamaPort fork
  `0.1.3-kanama-r8.4` routes single-blob code generation through a
  non-varargs path. Validated Android version floors are now documented in
  the Android export guide ("Validated Android Versions"): debug builds
  Android 9+, release builds Android 13+ (validated 14/16 — below 13,
  release-mode ART crashes in PanamaPort's LLVM-driven upcall generation;
  characterized and deferred upstream).
- `@GlobalClass` Kotlin scripts now actually register in the editor's global
  class list ([#39](https://github.com/falcon4ever/kanama/issues/39)): they
  appear in the Create New Resource dialog, and `.tres` resources using them
  match typed export slots (the *"selected resource (Resource) does not match
  … (MyClass)"* rejection reported in
  [#38](https://github.com/falcon4ever/kanama/issues/38) is gone). The script
  language answered the editor's `_handles_global_class_type` routing query
  with global-class-*name* semantics instead of script-resource-*type*
  semantics ("Script" for `.kt` files), so the editor never queried
  `_get_global_class_name` and no Kotlin class ever reached the global class
  cache. A `@GlobalClass` in a file not named `<ClassName>.kt` now gets a
  build warning (the class cannot be mapped back to its script file and stays
  out of the list).

## 0.3.0 - unreleased preview line (merged 2026-07-06, not tagged)

This release closes Kanama's mobile + convergence phase. The headline is
**mobile demo parity**: a full experimental iOS Kotlin/Native backend and a
device-validated Android path, both running the same generated Godot API
wrappers as desktop under a cross-platform drift gate. Wrapper coverage reached
~99% of classes/methods, the docs were reorganized into consumer/maintainer/
internal tracks, and the support tiers were formalized (desktop supported;
iOS + Android experimental but device-validated). See
`docs/internals/release-support-decision.md` for the grounded support matrix and
`docs/internals/release-support-decision.md` §7 for the mobile promotion bar.

### Added

- Added an experimental iOS Kotlin/Native backend that runs full Kanama project
  scripts: a C GDExtension shim plus GENERATED Godot API wrappers (the same
  wrapper generator as desktop/Android) over a C-shim generic `ptrcall`. The core
  self-test and current demo corpus have playable device runs; per-frame Kanama
  binding overhead measured ~0.63 ms on iPhone 12. iOS remains experimental, not
  a supported export — see `docs/internals/active/ios-backend-roadmap.md` for the gaps.
- Added an iOS hand-written/stub registry: `// KANAMA-IOS-{STUB,HANDWRITTEN,SUGAR}`
  markers, `scripts/ios_handwritten_report.py` (generates
  `docs/internals/reference/ios-backend-handwritten.md`), and `scripts/check_ios_no_silent_stubs.py`
  (fails CI on an un-annotated silent stub). The current registry is 0 STUB / 0 SUGAR.
- Added iOS physical-device validation tooling: a visual smoke script with an
  optional Kotlin/Native frame probe that updates a Godot `Label` through a
  cached typed `ptrcall`. Simulator checks remain available for compile/link
  debugging only.
- Made the iOS install path build device-only xcframeworks by default, with an
  explicit `kanamaIosXcframeworkMode=full` escape hatch for simulator work.
- Reached iOS demo parity: the public demo corpus runs on device (full 9-demo
  device gate on iPhone 12, playable corpus + singleton/virtual self-tests on
  iPhone 15 Pro, both on Godot 4.7 stable). Landed the iOS long-tail wrapper
  shapes (Transform2D, NodePath/StringName returns, `Packed*`/`Typed*` arrays,
  Variant scalars), `Callable`/vararg support (object+method callables, varargs),
  and non-POD virtual returns (`String`, `PackedStringArray`, `Variant`/`Any?`)
  across desktop/Android/iOS.
- Added `@Rpc` config delivery so Godot receives RPC configuration on all three
  platform backends (desktop, Android, iOS).
- Hardened the Android path: the Godot 4.7 stable debug demo matrix and an
  R8-minified Match3 release APK both pass on a physical Pixel 7. R8 minification
  is validated against Kanama's PanamaPort fork, which fixes an upstream
  sealed-switch miscompile that crashed the FFI bootstrap under R8.
- Grew the generated Godot API wrapper surface to ~99% coverage (classes
  1032/1036 = 99.6%, callable methods 15274/15385 = 99.3%); engine virtuals are
  overridable across POD plus `String`/`PackedStringArray`/`Variant` returns.
  Residual exotic shapes are documented as promote-on-demand.
- Added a full cross-platform wrapper drift gate (`check_full_drift_gate`):
  committed wrappers are byte-identical to a fresh regeneration per platform
  (desktop 985 / iOS 242), making silent cross-platform wrapper drift
  structurally impossible.
- Extended `isEqualApprox` to composite value types (Transform3D, Basis, AABB,
  Rect2, Transform2D, Projection).
- Added a Godot-version pin as a single source of truth (`kanamaGodotVersion`)
  plus a KDoc sync tool and drift check wired into local CI, so a Godot upgrade
  is a repeatable process.
- Added `MobileControls` (virtual joysticks + on-screen buttons) to the
  `tps-demo-kanama` companion demo for Android/iOS touch play.

### Changed

- Updated the Kanama preview baseline to Godot 4.7 stable. Re-dumped
  `extension_api.json` and regenerated Godot API name constants for the stable
  version metadata; the 4.7 stable `extension_api.json` (excluding the
  `version_status` header field) and GDExtension interface header are
  byte-identical to 4.7 rc 2, so generated bindings, wrappers, name constants,
  and struct layouts are unchanged. Docs, scripts, templates, and the demos
  README were updated from `4.7 rc 2` to `4.7 stable`.
- Updated the Kanama preview baseline to Godot 4.7 rc 2. Re-dumped
  `extension_api.json` and regenerated Godot API name constants for the rc 2
  version metadata; the rc 2 GDExtension interface header and API body are
  byte-identical to beta 5, so generated bindings and wrappers are unchanged.
- Updated the build toolchain to Kotlin 2.3.21, KSP 2.3.9, and
  kotlinx.coroutines 1.11.0, with Gradle build cache enabled for the main and
  Android plugin builds.
- Enabled Kotlin Multiplatform cinterop commonization for the experimental
  iOS runtime.
- `installIosAddon` now preserves a project's Android (and desktop)
  `kanama.gdextension` library entries instead of overwriting them, so installing
  the iOS addon no longer regresses Android support (it mirrors `installAddonJar`'s
  Android-metadata preservation and asserts the entries survive).
- Reorganized the documentation into consumer / maintainer / internal tracks and
  refreshed the agent-facing guides (`AGENTS.md`, `CLAUDE.md`) so a fresh session
  can orient quickly.
- Refreshed generated wrapper KDoc against Godot 4.7 stable `doc/classes`
  (comment-only; verified zero code change).
- Updated the public support wording: macOS is now labeled **Supported (4.7
  stable)**; Android is **experimental, device-validated on Pixel 7** (previously
  under-claimed as "Pixel 7 pending"); iOS names both validated devices
  (iPhone 12 full gate / iPhone 15 Pro corpus). Linux and Windows remain
  **Supported pending 4.7-stable revalidation**.

### Known Limitations

- **Mobile is experimental, not desktop-level support.** iOS and Android are
  co-tiered as experimental (device-validated). No hot reload on mobile.
- **iOS heavy-3D / mobile-multiplayer gap.** The `tps-demo-kanama` demo does not
  run on iOS yet (un-audited wrapper classes, a few method gaps, and JVM-only
  stdlib usage in the demo). Tracked as a Phase 4 goal.
- **Android renderer + minification caveats.** The validated Android renderer is
  OpenGL Compatibility only; Vulkan/Mobile is unproven. R8-minified release APKs
  are validated only against Kanama's PanamaPort fork, not upstream.
- **Linux/Windows pending revalidation.** Last full validation was on Godot 4.7
  beta 2; the metadata-only bump to 4.7 stable carries over API/wrapper shape,
  but the runtime/demo smoke has not been re-run on stable Linux/Windows
  binaries.

## 0.2.2 - 2026-06-05

### Changed

- Updated the Kanama preview baseline to Godot 4.7 beta 5.
- Refreshed `extension_api.json` and generated Godot API name constants for the
  beta 5 version metadata; the beta 5 API/header body matches the beta 4 input
  used by the previous preview.
- Updated Gradle coordinates, release packaging defaults, documentation, and
  demo project defaults for Kanama `0.2.2`.

### Known Limitations

- Android remains experimental. Godot 4.7 beta 5 Android APK smoke validation is
  pending before changing Android support claims.

## 0.2.1 - 2026-05-26

### Added

- Fresh-clone smoke validation for release checks and clean source checkouts.
- A ready-to-run starter project creation task for first-time Kanama projects.

### Changed

- First-run documentation now starts from the new starter project flow and
  source-install validation path.
- Clean Gradle environments now get explicit JVM memory defaults.

### Fixed

- Local CI now creates the generated Godot GDExtension header when it is missing
  from a fresh checkout.
- Maven local validation now publishes/checks the current Kanama version in the
  effective Maven local repository.

## 0.2.0 - 2026-05-26

### Added

- Godot 4.7 beta 4 API baseline updates for generated wrappers, docs, and
  smoke validation.
- Editor workflow helpers for opening Kotlin sources and common build actions
  from the Kanama Tools dock.
- Basic Kotlin syntax highlighting in the Kanama Tools editor integration.
- Additional export inspector metadata support, including property hints,
  categories, groups, subgroups, and inspector tool buttons.
- Typed signal helper overloads for common connect, emit, and await usage while
  keeping the existing string-based signal APIs available.
- Convenience editor-time script helpers for `@Tool` scripts, including editor
  hint checks and inspector/property-list refresh.
- Generated engine-wide `MethodName`, `PropertyName`, and `SignalName`
  constants for type-safe Godot API name references.
- Multiplayer and porting guardrail audits for risky runtime node lookups, raw
  string dispatch, and `SceneReplicationConfig` custom properties.
- Multiplayer docs covering generated RPC helpers, ENet peer setup, replicated
  script properties, and review checklist items for ports.

### Changed

- Script/runtime hot paths are leaner, including vector math, object-array
  decoding, string-name handling, and common object call paths.
- Runtime diagnostics now report more lifecycle and script binding context when
  debugging editor/runtime integration failures.
- Example project registries are separated from runtime registration paths so
  local examples do not hide integration drift in external projects.
- Runtime and local CI smoke checks now assert the QoL metadata, tool button,
  and generated name-constant coverage.
- The replicated-script-property audit can now check multiple project roots in
  one run, so demo aggregate checks can use the same guardrail script as local
  CI.

### Fixed

- Script object lifecycle and property replay now preserve inspector-authored
  values more reliably across load/reload paths.
- Retained resource wrapper lifetime handling no longer drops resource handles
  too early in common script property flows.
- Custom resource script properties now release only the retained custom
  resource handle during parent cleanup. They no longer recursively clean the
  child script's exported resource properties, which could close shared
  resources such as `PackedScene` models too early.
- Getting-started, README, local docs preview, and release-validation examples
  were corrected for the current source-first workflow.

## 0.1.0 - 2026-05-19

### Added

- Desktop Kotlin script runtime for Godot through GDExtension and the JDK
  Foreign Function & Memory API.
- KSP-based script registration for lifecycle callbacks, exported properties,
  signals, global classes, and editor tool scripts.
- Hot reload support for desktop editor workflows.
- Generated Godot API wrappers with reproducibility checks and ABI policy
  audits.
- Promoted Kotlin wrapper classes for the Godot 4.7 beta 4 API baseline, with
  conservative method coverage documented in the wrapper coverage report.
- Generated KDoc carried from Godot API documentation for wrapper classes and
  methods.
- Starter project template and example smoke-test project.
- MkDocs documentation covering setup, API usage, distribution, wrapper
  coverage, and maintainer internals.
- Companion demo projects used as integration coverage for real gameplay code.
- Experimental Android export workflow through a Godot Android plugin AAR,
  Android ART, and PanamaPort, smoke-tested on emulator/Pixel 7 paths with
  eight public demo targets.

### Changed

- Wrapper generation is now an active source-promotion path instead of a report-only
  experiment.
- Public documentation has been organized around getting started, porting,
  manual pages, reference coverage, and internals.

### Known Limitations

- Kanama is desktop-first. macOS arm64 is the active development and smoke-test
  platform; Linux and Windows have runtime/demo smoke coverage with remaining
  automated editor/tool shutdown caveats documented in Version Support.
- Android exports are experimental. The current path includes Android smoke
  targets with selected touch overlays, D-pad controls, virtual joysticks, and
  demo warmup fixes for first-use hitches, but Vulkan/Mobile renderer coverage,
  hot reload, and complete phone-specific UI polish are not release claims yet.
- iOS is not supported. Web export is not planned.
- Broad `Callable`, `Dictionary`, generic container, virtual override, and
  ownership-sensitive APIs remain conservative policy buckets.
