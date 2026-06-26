# iOS Backend Architecture

How Kanama runs Kotlin game scripts on iOS: generated Godot API wrappers over a
C-shim generic `ptrcall`, the same wrapper generator as desktop/Android. For live
status, guardrails, how to stay in sync with desktop/Android, the backlog, and
per-demo coverage see [ios-backend-roadmap.md](../active/ios-backend-roadmap.md); for the
hand-written/stub registry see
[ios-backend-handwritten.md](./ios-backend-handwritten.md).

> **Status:** experimental but proven viable — the core smoke matrix and the
> main iOS demo ports are device-validated on real iPhones. iOS is not yet a
> supported export. This page describes the shipped architecture; live demo
> status and remaining support gates live in
> [ios-backend-roadmap.md](../active/ios-backend-roadmap.md).

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
ios-runtime (Kotlin/Native)                                                  │
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
   methods/properties/signals, emitted by the KSP processor + `ios-runtime/build.gradle.kts`.
2. **Godot API wrappers** (also generated, see below): the `Node3D`/`CharacterBody3D`/…
   classes that user scripts call into.

## Why generated wrappers (the hand-written stubs we left behind)

The first iOS slice hand-wrote the Godot API surface in one file,
`ios-runtime/.../api/IosGodotApi.kt` (~1000 lines, ~30 classes), where most methods
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

iOS reuses the desktop/Android solution. Its full ~1086-class API in
`src/main/kotlin/.../api/` is **generated** ("Generated from Godot docs") by
`scripts/generate_api_wrapper.py` from `extension_api.json`. Each generated wrapper
caches a `MethodBind` and calls a typed helper on a runtime abstraction `ObjectCalls`:

```kotlin
// generated CharacterBody3D.kt (desktop)
fun moveAndSlide(): Boolean = ObjectCalls.ptrcallNoArgsRetBool(moveAndSlideBind, handle)
fun setVelocity(velocity: Vector3) = ObjectCalls.ptrcallWithVector3Arg(setVelocityBind, handle, velocity)
```

`ObjectCalls` is the platform seam: desktop implements it with Panama/FFM; **iOS
implements it with the C shim** (`get_method_bind` + the generic `ptrcall` dispatch).
The generated wrappers depend only on `ObjectCalls` + types +
`java.lang.foreign.MemorySegment`, all of which iOS provides (it shims
`MemorySegment`), so the same wrapper source runs on both backends.

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
- The iOS emission target produces the generated wrappers (copied into `iosMain`
  today; `commonMain` + `expect/actual ObjectCalls` sharing is on the backlog) and
  the matching `ObjectCalls` helper bodies (generated from the CallShape set).
- The hand-written layer is now only genuinely bespoke runtime pieces:
  `KanamaScript` base, `KanamaScope`, `Input`/InputMap glue, the signal/Callable
  registry, lifecycle. Everything else is generated.

## Contract: generic ptrcall dispatch (iOS ObjectCalls)

Informed by a survey of the desktop `ObjectCalls.*` helper shapes
(regenerable with `grep -rhoE "ObjectCalls\.[A-Za-z0-9_]+" src/main/.../api/ | sort -u`):
the generated wrappers reference ~1467 distinct `ObjectCalls.*` helper shapes (121 for
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
- **Sharing strategy**: today iOS wrapper copies are generated into `iosMain` (no
  module restructure); moving them to shared `commonMain` with
  `expect/actual ObjectCalls` is a backlog item.

## Generator approach

The desktop generator (`scripts/generate_api_wrapper.py`) renders each wrapper method
as `ObjectCalls.<shape.function>(bind, receiver, args)` (see `render_method`) — this
wrapper code is **platform-agnostic**. So the iOS target does NOT need different
wrapper output:

- **Reuse the generated wrapper classes unchanged** (start by emitting copies into
  `iosMain`; toward shared `commonMain` later). They depend only on `ObjectCalls` +
  types + `MemorySegment`, all present on iOS.
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
- **Validate on device.** Every change ends with an on-device run (0 SIGSEGV baseline,
  guardrail logs clean).
