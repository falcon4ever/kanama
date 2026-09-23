# iOS Backend Architecture

How Kanama runs Kotlin game scripts on iOS: generated Godot API wrappers over a
C-shim generic `ptrcall`, the same wrapper generator as desktop/Android. For the
hand-written/stub registry see
[ios-backend-handwritten.md](../../reference/generated/ios-backend-handwritten.md).

> This page describes the shipped architecture. The support tier and the device
> evidence behind it live in
> [Version Support → iOS](../../reference/version-support.md#ios); the
> user-facing export workflow is [Exporting → iOS](../../exporting/ios.md).

## Why iOS is different from desktop/Android

Kanama desktop uses the JVM + Panama (FFM) to call into Godot. iOS forbids JIT, so
the JVM model does not apply. The iOS backend is **Kotlin/Native** (AOT-compiled)
plus a **C shim** (`ios/bootstrap/kanama_ios_shim.c`) that implements the GDExtension
entry points and bridges to the Kotlin/Native runtime via `@CName` exports.

What works today (verified on iPhone 12 + iPhone 15 Pro): script loading + lifecycle,
`@OnReady`/`@OnProcess`/`@OnPhysicsProcess`/`@OnInput`/`@RegisterFunction`,
`@OverrideVirtual` for engine virtuals, `@ScriptProperty` (including value types,
object lists, user-script lists, and `List<String>`/`PackedStringArray` delivery),
`@Signal` registration, named + lambda signal connections (custom Godot Callable),
`await`, scene reload, and AudioStreamPlayer playback. Match3, 3D Platformer,
Bunnymark, dodge, squash, Racing, character-controller, FPS, and third-person have
all reached playable device runs; FPS still has an intermittent Audio autoload
SIGSEGV follow-up.

## Components

```
Godot (iOS, GDExtension)
        │
        ▼
ios/bootstrap/kanama_ios_shim.c  ── C shim ──────────────────────────────────┐
  • GDExtension entry, class/loader/language registration                    │
  • ScriptInstance lifecycle, property set, method/signal dispatch           │
  • Marshalling helpers: get_method_bind (cached), ptrcall_* helpers,        │
    Variant boxing, StringName helpers, custom-Callable trampoline           │
  • Guardrails: kanama_ios_check_call_error, kanama_ios_check_variant_arg     │
        │  @CName <-> extern bridge                                          │
        ▼                                                                    │
src/iosMain (Kotlin/Native)                                                  │
  • KanamaIosRuntime.kt — script registry, instance bridges, dispatch        │
  • IosCallableRegistry — lambda/bound signal callbacks                      │
  • binding/runtime/ObjectCalls.kt — the API call abstraction  <─────────────┘
  • api/*.kt — GENERATED Godot API wrappers (same generator as desktop/Android)
  • a bounded set of hand-written/sugar sites (see ios-backend-handwritten.md)
        ▲
        │  build.gradle.kts + KSP processor
Generated per-project script registry (methods/properties/signals of USER scripts)
```

Two distinct codegen concerns, do not confuse them:
1. **Project-script registry** (already generated): the user's `@ScriptClass` scripts'
   methods/properties/signals, emitted by the KSP processor + the root `build.gradle.kts`.
2. **Godot API wrappers** (also generated, see below): the `Node3D`/`CharacterBody3D`/…
   classes that user scripts call into.

## Why generated wrappers (the hand-written stubs we left behind)

The first iOS slice hand-wrote the Godot API surface in one file,
`src/iosMain/.../api/IosGodotApi.kt` (~1000 lines, ~30 classes), where most methods
were **no-op stubs** that compiled and silently returned defaults. Adding one Godot
method took ~6 manual edits across 3 files (`extension_api.json` hash lookup → C
`g_*_bind` + binding function → `kanama_ios.h` decl → `IosGodot` wrapper → Kotlin API
method). That did not scale, and it was the root of an entire bug class:

- **Silent no-op stubs**: `AudioStreamPlayer` and `CharacterBody3D` were fully stubbed
  (`moveAndSlide()` → `false`, `velocity` a dead field) — they compiled and "ran" but
  did nothing. These are invisible until something downstream misbehaves.
- **Hand-marshalling bugs**: the Match3 SIGSEGV (a raw `Callable` passed where a boxed
  `Variant` was required), a StringName over-dereference in script virtuals, and
  Variant type-tag mismatches all came from hand-written marshalling.

That approach could not reach the full API that real demos (3D platformer, FPS,
racing, city-builder) need, so iOS adopted the desktop/Android generated-wrapper
model described next.

## Current architecture: generated wrappers + ObjectCalls

iOS reuses the desktop/Android solution. The Godot API wrappers are **generated**
("Generated from Godot docs") by `scripts/generate_api_wrapper.py` from
`extension_api.json`, once, into the shared tree `src/commonMain/kotlin/.../api/`,
which iOS compiles as-is (task 103). Since task 30 iOS hosts the **full
desktop-equivalent class set** (the shared classes plus its iOS-only generated,
hand-shaped and hand-written collision classes; the only exceptions are the documented
`IOS_UNSUPPORTED_CLASSES`). Methods whose ptrcall shape is not audited on iOS are
desktop-only companions, listed in the generated
[iOS Shape Gap](../../reference/generated/ios-shape-gap.md) page. Each generated
wrapper caches a `MethodBind` and calls a typed helper on a runtime abstraction
`ObjectCalls`:

```kotlin
// generated CharacterBody3D.kt (desktop)
fun moveAndSlide(): Boolean = ObjectCalls.ptrcallNoArgsRetBool(moveAndSlideBind, handle)
fun setVelocity(velocity: Vector3) = ObjectCalls.ptrcallWithVector3Arg(setVelocityBind, segment, velocity)
```

`ObjectCalls` is the platform seam: desktop implements it with Panama/FFM; **iOS
implements it with the C shim** (`get_method_bind` + the generic `ptrcall` dispatch).
The generated wrappers depend only on `ObjectCalls` + types + `GodotHandle`, all of
which iOS provides — `GodotHandle` is a per-platform `@JvmInline value class` over
the backend's pointer type (iOS declares its own over the `MemorySegment` shim), so
the same wrapper source runs on both backends. A wrapper BODY unwraps it once through
the internal `GodotObject.segment`; the raw type never appears in a public signature.

```
extension_api.json
   │  generate_api_wrapper.py (+ iOS emission target)
   ▼
Generated wrappers (Node3D, CharacterBody3D, …)
   │  ObjectCalls.ptrcall*/call*  (cached MethodBind + typed ptrcall)
   ▼
ObjectCalls  ──┬── desktop actual → Panama/FFM
               └── iOS actual → C shim (get_method_bind + ptrcall/method_bind_call)
```

The proven runtime stayed; the hand-written API was replaced with generated wrappers.

### What is generated vs hand-written

- iOS reuses the desktop generator's `extension_api.json` model, its `CallShape`
  signature taxonomy (the finite `(argtypes→return)` → helper-name map), its
  conservative skip logic (`--skip-report`), and its fixture-based check harness
  (`scripts/check_wrapper_generator.py`). See `docs/contributing/wrapper-maintenance.md`.
- The iOS emission target decides the shared tree's method set, renders the iOS-only
  generated wrappers (`src/iosMain/.../api`) and the matching `ObjectCalls` helper
  bodies (generated from the CallShape set); the `expect/actual ObjectCalls` form is
  a separate task (see the Shared Wrapper Tree design check).
- The hand-written layer is now only genuinely bespoke runtime pieces:
  `KanamaScript` base, `KanamaScope`, `Input`/InputMap glue, the signal/Callable
  registry, lifecycle. Everything else is generated.

## Contract: generic ptrcall dispatch (iOS ObjectCalls)

Informed by a survey of the desktop `ObjectCalls.*` helper shapes
(regenerable with `grep -rhoE "ObjectCalls\.[A-Za-z0-9_]+" src/sharedApi/.../api/ | sort -u`):
the generated wrappers reference ~1500 distinct `ObjectCalls.*` helper shapes (1467 at
the task-30 survey; 121 for
the platformer's classes alone), of which only ~7% map to an existing iOS C
primitive. Hand-writing a C function per shape is untenable, so:

- **One generic typed ptrcall dispatch in the C shim** drives all ptrcall shapes:
  `kanama_ios_godot_ptrcall(bind, instance, argTypes[], argValues[], argCount, retType, retOut)`.
  A small `type tag` enum selects each arg's cell layout (bool/int32/int64/uint32/
  float/double/Object/Vector2/Vector2i/Vector3/Vector4/Color/Rect2/Transform2D/
  Transform3D/Basis/Quaternion/AABB/Projection/RID/StringName/NodePath/String/
  typed-list…) and the return read-back. New shapes need **no new C function** —
  only a Kotlin `ObjectCalls` helper body that packs the right arg/return tags.
- **`ObjectCalls` (iOS)** exposes the typed helpers the generated wrappers call
  (`ptrcallNoArgsRetBool`, `ptrcallWithVector3Arg`, …); each is a thin wrapper over
  the generic dispatch and caches its `MethodBind` (via the existing
  `kanama_ios_godot_get_method_bind`, resolved once per wrapper method). These helper
  bodies are themselves **generated** from the survey's CallShape set so the surface
  stays in sync with the generator.
- **Marshalling lives in one place** (the generic dispatch), guarded by
  `kanama_ios_check_call_error` / `kanama_ios_check_variant_arg`. This is the
  error-prone code; concentrating it there (vs scattered per-method functions) is how
  we avoid repeating the SIGSEGV / over-deref / boxing bug class.
- **Varargs / shapes ptrcall can't express** fall back to the Variant
  `object_method_bind_call` path (already used by connect/emit/tween).
- **Sharing strategy**: one shared source directory (`src/commonMain/kotlin`)
  compiled by each platform module, no module restructure (task 103); the KMP
  `expect/actual ObjectCalls` form remains the long-term target.

## Generator approach

The desktop generator (`scripts/generate_api_wrapper.py`) renders each wrapper method
as `ObjectCalls.<shape.function>(bind, receiver, args)` (see `render_method`) — this
wrapper code is **platform-agnostic**. So the iOS target does NOT need different
wrapper output:

- **Reuse the generated wrapper classes unchanged** (they are the shared
  `src/commonMain` files since task 103). They depend only on `ObjectCalls` +
  types + `GodotHandle`, all present on iOS.
- **Generate the iOS `ObjectCalls` helper bodies** for the set of `shape.function`
  names the wrappers use. As the generator picks each method's `CallShape` it knows
  the structured arg types + return type; collect a registry `shape.function ->
  (arg godot-types, return godot-type)` (deduped by name) and emit an iOS helper that
  marshals via the generic `kanama_ios_godot_ptrcall`. The hand-written iOS
  helpers in `ObjectCalls.kt` are the reference template + the override set.
- **Type → PT-tag mapping (the risky core)** — apply exactly:
  scalar `float`/`double` → `PT_FLOAT64` (8 bytes); `int` per `meta` (int32/int64);
  Vector/Color components → float32 (single-precision); Object → `PT_OBJECT`;
  StringName/String/NodePath → CONSTRUCT tags (built C-side from a C string);
  `bool` → `PT_BOOL`. Shapes ptrcall can't express (varargs) → the Variant
  `callWithVariantArgs` path.
- **Validation:** generate one real class (e.g. `CharacterBody3D`), compile via
  `installIosAddon`, and round-trip its key methods on device (extend the ObjectCalls
  self-test) before generating the rest.

The type → PT-tag mapping is the single point where a marshalling mistake would
propagate across the whole API, so it is guarded by the on-device ptrcall matrix
and the ObjectCalls Kotlin probe (see Rules below).

## Performance

This is the **fast** path, not generic reflection: a `MethodBind` is resolved once
per method (cached in the wrapper, like desktop's `*Bind` fields) and calls go through
typed `ptrcall` — the same mechanism as desktop and as the current hand-written iOS
binding functions. Per-call cost is the C↔Kotlin/Native FFI crossing plus argument
marshalling. Guidance (confirm by profiling per-frame `_physics_process` on the 3D
platformer):

- Cache `MethodBind` per generated method; never re-resolve per call.
- Avoid per-call `StringName` allocation (intern/cache method + arg names).
- Prefer `ptrcall` over the Variant `call` path; reserve `call` for varargs/signals.
- Minimize boundary crossings in hot loops.

## Contract: no silent paths (the fault sink)

`ios/bootstrap/kanama_ios_shim.c` is the entire iOS surface, and its default failure mode
used to be silence. 131 exported entry points open with a stack of guards — the engine API
did not resolve, the `MethodBind` is zero, the instance is zero, a C string is NULL, nothing
is pending — and every one of them returned `0` / `-1` / nothing without a word. That is how
every Godot static method reached through the shared wrapper tree stayed a no-op on iOS for
six days behind 205 green self-test checks and two green demo smokes (task 117 P2', 119 item
35), and how a self-test row passed for the wrong reason because a guard returned quietly
(item 37).

Since task 124 the shim has one sink, near the top of the file:

```c
static void kanama_ios_fault(const char *entry, const char *reason, const char *detail);
int32_t kanama_ios_fault_count(void);      /* never reset */
const char *kanama_ios_last_fault(void);   /* "<entry> <reason> <detail>", "" when none */
```

It increments a process-wide counter, stores `"<entry> <reason> <detail>"`, and prints
`[kanama][ios][c] FAULT <entry>: <reason> <detail>` to stderr for the first 64 faults (a tight
loop must not flood the console; the **count** keeps counting past 64). There is deliberately
**no reset function**: a fault in frame 1 is still in the count at the end of the run, which is
what makes the self-test's end-of-run number an assertion rather than a snapshot.

**The rule: every guarded early return reports.** In an exported `kanama_ios_*` entry point or
a `static ..._dispatch` body, an `if (...)` whose body returns must call `kanama_ios_fault(...)`
first. Return values and signatures never change — the bridge keeps behaving exactly as it did,
it simply stops doing it quietly. `scripts/check_ios_shim_faults.py` (a `local_ci.sh` stage)
re-derives the guard list from the source and fails on the first one that does not report, so
the rule holds for the next guard somebody adds rather than for the ones instrumented that day.

The reason token is one of ten fixed spellings — `api-unresolved`, `null-bind`,
`bind-lookup-failed`, `null-instance`, `null-handle`, `null-arg`, `pending-protocol`,
`callable-build`, `unknown-tag`, `encode-failed` — and the detail names the specific thing:
the missing interface pointer, the parameter, `Class.method hash=<hash>`, the tag number.
They are documented for users in `docs/exporting/ios.md`.

Two guards **deliberately** do not report, each documented in its own function comment and
listed in the gate's `BENIGN` table (which can only shrink — a stale entry fails the gate):

- `kanama_ios_godot_is_instance_id_valid(0)` — a zero instance id is a legitimate question
  whose honest answer is "invalid", not a caller bug.
- the `target == NULL` path in `kanama_ios_godot_ptrcall_ret_callable_dispatch` — an empty
  (object-less) `Callable` is a legitimate value; desktop's `readCallable` returns null too.

**Bind lookups report at lookup time.** `kanama_ios_godot_get_method_bind` and
`kanama_ios_godot_get_builtin_method` call the sink with `bind-lookup-failed` and the detail
`Class.method hash=<hash>` whenever the engine returns NULL — not only when the API itself did
not resolve. The names and the hash exist there and nowhere else: by the time the zero bind
reaches a ptrcall entry point all that is left to say is `null-bind`. Desktop mirrors the line
(`[kanama:kt] FAULT bind-lookup-failed Class.method hash=<hash>` on `System.err`) but carries no
counter — there is no shim on desktop, and a null bind crashes at the call, which is loud enough.

**The self-test's two expected probes.** The level-2 (`INITIALIZATION_LEVEL_SCENE`) self-test in
`src/iosMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCalls.kt` ends with two
deliberate wrong calls: a `getMethodBind("Node3D", "set_visible", 1L)` with a wrong hash
(asserted to raise the count by exactly one and to leave `bind-lookup-failed` and
`Node3D.set_visible` in `lastFault()`), then a `ptrcallWithBoolArg` through that null bind
(asserted `+1` with `null-bind`). Every other row in that file proves a call *works*; these two
prove that a call that does *not* work says so — a red run that re-executes itself on the device
on every debug build, so it cannot be forgotten.

Both summary lines therefore end with `faults=<count> expected=2`:

```
[kanama][ios][kn] OBJECTCALLS SELFTEST: <N> passed, 0 failed faults=2 expected=2
[kanama][ios][kn] OBJECTCALLS SELFTEST (frame 1): <N> passed, 0 failed faults=2 expected=2
```

`expected` is the constant `SELFTEST_EXPECTED_FAULTS` declared next to the probes; change it only
when you change the probes. Release builds run no self-test and print neither number.

The probes emit two genuine FAULT lines, so they are **bracketed** by
`OBJECTCALLS SELFTEST fault-probes begin` / `... end`. `kanama-demos/scripts/ios_device_run.sh`
fails the device run on any `[kanama][ios][c] FAULT ` line **outside** that window, and on any
summary line where `faults` and `expected` disagree. Whitelisting the probes by their text instead
would have made `null-bind` — the most common real fault — permanently invisible, so the window is
the thing that is trusted, not the reason token. The runner's check can be re-run against any saved
log with `scripts/ios_device_run.sh --check-console-faults /path/to/console.log`, which is how its
own red runs are reproduced without a phone.

## Rules

- **No silent stubs.** Every API method must call through `ObjectCalls`. A method with
  an empty body or a bare `return false/0.0/null` is a bug — it hides missing
  functionality. Prefer generating the method (or omitting it) over stubbing it.
- **Marshalling goes through guardrails.** Variant-based calls
  (`object_method_bind_call`) must use `kanama_ios_check_call_error`; argument
  Variants are checked with `kanama_ios_check_variant_arg` in debug builds. Both must
  log zero in a healthy run.
- **GDExtension virtual `args[i]` is already a pointer to the argument** — do not
  double-dereference StringName args (a past bug in the connect/signal path).
- **Authoritative ptrcall scalar width table** (from Godot `core/variant/method_ptrcall.h`).
  This is THE type→PT-tag mapping for the generator; getting it wrong corrupts that
  type across the whole API:

  | Godot scalar type | ptrcall encoding | bytes | iOS handling |
  |---|---|---|---|
  | `bool` | `convert<bool,uint8_t>` | 1 | `PT_BOOL` (uint8) |
  | `int8/16/32`, `uint8/16/32` | `convert<…,int64_t>` | **8** | lay int64 (widen) |
  | `int64`, `uint64` | direct | 8 | int64 |
  | `float` | `convert<float,double>` | **8** | lay double (widen Float→Double) |
  | `double` | direct | 8 | double |
  | Object/RID | direct ptr | 8 | `PT_OBJECT` |

  **Key gotcha — only SCALARS widen.** *scalar* `float` AND all *scalar* `int`s are
  **8 bytes** at ptrcall (converted from the smaller C++ type), regardless of `meta`.
  Struct/container types are `PtrToArgDirect`/`PtrToArgByReference` — passed as their
  **native bytes, NOT widened**:

  | Godot type group | ptrcall layout | notes |
  |---|---|---|
  | real_t structs: Vector2/3/4, Rect2, Plane, Quaternion, AABB, Basis, Transform2D/3D, Projection | N× **float32** (single-prec) | components are `real_t` (centralize like desktop `GodotReal`) |
  | int structs: Vector2i/3i/4i, Rect2i | N× **int32** (4B) | NOT widened — unlike scalar ints |
  | Color | 4× **float32** | always float32 (not real_t) |
  | Object, RID | 8B pointer/handle | |
  | String, StringName, NodePath | constructed C-side from a string | CONSTRUCT tag |
  | Callable, Signal, Dictionary, Array, Packed* | opaque handle | defer / skip initially |

  So `set_amount(int)`/`set_volume_db(float)` take 8-byte cells, but `Vector2i(x,y)`
  lays two 4-byte int32s and `Vector3(x,y,z)` three 4-byte float32s. Source:
  `core/variant/method_ptrcall.h`. (The scalar-float case was the inaudible-audio bug;
  the scalar-int case was masked by int32 conversion + small self-test values.)

- **Guardrail: the generator emits ONLY audited types.** Like the desktop generator's
  `--skip-report`, the iOS emission must refuse any method whose arg/return types
  aren't in the audited table above — skipped, never guessed. An un-audited type can
  therefore never silently produce a wrong-width helper. As each new type is added to
  the table it also gets a **width-sensitive** row in the debug-gated self-test matrix
  (use values where a wrong width fails — a small value can mask it, as the int bug
  showed). The matrix + ObjectCalls probe are the ptrcall path's only runtime check
  (`check_call_error`/`check_variant_arg` only cover the Variant path).
- **The on-device self-test runs in two phases, and a row must sit in the right one.**
  The C ptrcall matrix and the Kotlin `kanamaIosRuntimeObjectCallsSelfTest` both run at
  SCENE-level extension init and print
  `[kanama][ios][c] PTRCALL SELFTEST MATRIX: N passed, M failed` and
  `[kanama][ios][kn] OBJECTCALLS SELFTEST: N passed, M failed`. A second Kotlin phase,
  `kanamaIosRuntimeObjectCallsSelfTestFrame`, runs **once on the first frame** — called from
  `kanama_ios_frame` when the frame counter first reaches 1, before `kanama_ios_runtime_frame()`
  — and prints `[kanama][ios][kn] OBJECTCALLS SELFTEST (frame 1): N passed, M failed`. Anything
  needing a singleton Godot registers *after* `initialize_extensions(INITIALIZATION_LEVEL_SCENE)`
  belongs in the frame-1 phase: the servers arrive only with `register_server_singletons()`
  (`servers/register_server_types.cpp:400-401`), so at scene init `getSingleton("RenderingServer")`
  — and `getSingleton("NativeMenu")`, registered by the same call — returns 0. Resolve every
  singleton through the phase's `requireSingleton` helper — it records `singleton-present(<name>)`
  as its own check and the row then skips its dependent calls — because a zero instance is the
  shared tree's static-method marker and now reaches Godot as a null `this` instead of no-opping.
  Expected line shapes on a clean run:

  ```text
  [kanama][ios][c] PTRCALL SELFTEST MATRIX: 70 passed, 0 failed
  [kanama][ios][kn] OBJECTCALLS SELFTEST: 236 passed, 0 failed
  [kanama][ios][kn] OBJECTCALLS SELFTEST (frame 1): 4 passed, 0 failed
  ```

- **A probe must assert a value the no-op path cannot produce.** This is the rule that decides
  whether a row is a probe at all. A static routed with a zero instance used to hit the C guard and
  return `null` / `0` / `false` / `""` / an empty list / a zeroed struct, so a row whose *expected*
  value is any of those cannot tell the working call from the call that never happened — it keeps
  passing on the day the feature breaks. Three rows shipped that way and all three were found only
  once the singleton checks made the self-test honest: `NativeMenu`'s empty Callable, and the two
  `ShaderIncludeDB` rows, whose "non-empty list / non-empty source" is the *correct* answer under
  the GL Compatibility renderer — Godot registers the built-in includes only from the
  RenderingDevice renderer
  (`servers/rendering/renderer_rd/renderer_scene_render_rd.cpp:1796-1798`). Prefer a named element
  over "non-empty", a valid RID over an invalid one, and a value with structure (a 64-character
  lowercase-hex SHA-256) over a truthy one. A default expectation is admissible only when something
  else in the row already rules the no-op out — a preceding non-default assertion through the same
  helper, or a `requireSingleton` / `requireObject` check proving the instance is non-zero, which
  means the static route is not taken. Every phase must contain at least one non-default assertion.
- **Validate on device.** Every change ends with an on-device run (0 SIGSEGV baseline,
  guardrail logs clean).
