# commonMain Wrapper Unification — Design (Phase 4.3)

> **Design record, not implementation.** This is the design pass the
> [iOS backend roadmap](./ios-backend-roadmap.md) (Post-Mobile-Parity step 1)
> and the [wrapper coverage roadmap](./wrapper-coverage-roadmap.md) (task 4.3)
> explicitly require before any code lands. Implementation is gated on this
> design being reviewed by a maintainer (the roadmaps tag 4.3 **opus 4.8** —
> the architectural tag). Tasks
> 07–12 cannot start until the migration path here is approved.

Design for unifying Kanama's generated Godot API wrappers toward a single
`commonMain` source set with `expect/actual ObjectCalls`, so the generated API
shape is identical across desktop (Panama), Android (PanamaPort), and iOS
(Kotlin/Native C-shim). This is the keystone that ends desktop/iOS wrapper
drift permanently.

## The problem: two wrapper sets that drift

The same `scripts/generate_api_wrapper.py` emits the *same* wrapper text for
desktop and iOS, but the output lands in **two physical source trees** that
are then free to diverge:

- **Desktop / Android — JVM module** (`src/main/kotlin`). Wrappers in
  `net/multigesture/kanama/api/`, value types in `net/multigesture/kanama/types/`,
  `ObjectCalls` in `binding/runtime/ObjectCalls.kt` (package
  `net.multigesture.kanama.binding.runtime`; physical path is historical).
  Uses real Panama `java.lang.foreign.*`. Android consumes this same source
  via textual remap (see below).
- **iOS — `:ios-runtime` (Kotlin/Native KMP module)**. Wrappers in
  `ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api/` (a *separate
  copy*), value types in `iosMain/.../types/`, `ObjectCalls` in
  `iosMain/.../binding/runtime/ObjectCalls.kt` (C-shim based), plus a
  `java/lang/foreign/MemorySegment` shim class. `commonMain` exists but is
  empty today.

The generator's iOS emission path (`generate_api_wrapper.py:2444`) even
documents the delta: *"The generated wrappers are byte-identical to desktop
except the ObjectCalls import must also pull in the generated extension
helpers."* One textual line of difference at generation time — and yet the two
copies drift in the repo because they are checked in separately.

Concrete drift today (the symptoms that motivate this design):

| Symptom | Where | Root cause |
|---|---|---|
| `Node.createTween()` is `fun` (final) on desktop, `open fun` on iOS | `src/main/kotlin/.../api/Node.kt:650` vs `ios-runtime/src/iosMain/.../api/Node.kt:557` | The iOS `open` exception works around a hand-written `SceneTree` subclass that overrides `create_tween` with the correct method bind. Two wrapper files make a per-platform `open` keyword possible. |
| `Rect2i` and `Vector4i` value types exist on JVM, missing on iOS | `src/main/kotlin/.../types/` vs `ios-runtime/src/iosMain/.../types/` (no `Rect2i.kt`/`Vector4i.kt`) | Phase 2.7 long tail — two type directories let a type land on one platform without the other. |
| KDoc is current on desktop, stale/missing on iOS | `Node3D.kt` carries generated Godot-doc KDoc on desktop; the iOS copy doesn't | `sync_kdoc_from_godot_docs.py` only targets `src/main/kotlin`. Two copies = two KDoc surfaces. |

A common wrapper layer in `commonMain` makes all three classes of drift
impossible **by construction** — there is one wrapper file, one type file, one
KDoc block per class, checked in once.

### How Android consumes desktop source today (the remap pipeline)

Android is **not** a KMP target. The Android Godot plugin AAR
(`android/godot-plugin/plugin`, an AGP `com.android.library`) consumes desktop
source by **copying + textually remapping** it:

- `prepareAndroidKanamaSources` (`android/godot-plugin/plugin/build.gradle.kts:159`)
  `Sync`s `src/main/kotlin` (excluding `example/**`) + `annotations/src/main/kotlin`
  + the demo's `kotlin-src` + KSP output into `build/generated/kanamaAndroidSources`.
- A fixed list of `AndroidSourceRemapRule`s rewrites the text:
  `java.lang.foreign` → `com.v7878.foreign` (PanamaPort),
  `MethodHandle.invoke(` → `invokeWithArguments(`, plus several
  `invokeWithArguments` → `invoke` patches for callback sites PanamaPort
  can't handle (`registerAll`, `script.factory`, signal callbacks, …).
- A separate `Real.kt` is written into the generated tree (always
  single-precision `Float`, `com.v7878.foreign`).
- `auditAndroidKanamaSources` (`:226`) fails the build if any forbidden
  fragment (`java.lang.foreign`, `?.invokeWithArguments(`,
  `Files.readString`, …) survives the remap.

So Android already shares desktop source — via text remap + a fail-loud audit,
not via a KMP source set. The unification must preserve this pipeline (or
replace it; see §6 future follow-up).

## 1. Target source-set structure

**Recommendation: convert the root `kanama` module from `kotlin("jvm")` to
`kotlin("multiplatform")`, fold `:ios-runtime` into it, and host the shared
wrappers in `commonMain`.** One Gradle module, one source tree, one wrapper
set — drift impossible by construction.

### Module shape after migration

```
kanama (root, kotlin("multiplatform"))
├── commonMain/                       ← SHARED across all targets
│   └── kotlin/net/multigesture/kanama/
│       ├── api/                      ← generated wrappers (Node3D, CharacterBody3D, …)
│       ├── types/                    ← value types (Vector3, Transform3D, Color, …)
│       └── ffi/MemorySegment.kt      ← expect class MemorySegment
│   └── kotlin/net/multigesture/kanama/binding/runtime/
│       └── ObjectCalls.kt            ← expect object ObjectCalls (generated signatures)
├── jvmMain/                          ← desktop JVM + Android remap source
│   └── kotlin/net/multigesture/kanama/
│       ├── ffi/MemorySegment.kt      ← actual typealias MemorySegment = java.lang.foreign.MemorySegment
│       ├── binding/runtime/ObjectCalls.kt        ← actual object ObjectCalls (Panama)
│       ├── binding/runtime/BuiltinTypes.kt       ← JVM-only (Panama ABI sizes/destroy)
│       ├── binding/runtime/{Upcalls,VariantConverters,VariantType,Signals,…}.kt
│       ├── binding/{KanamaBinding,KanamaScript,KanamaScriptInstance,KanamaScriptLanguage,ObjectRegistry,ScriptBridge,…}.kt
│       ├── ffi/GodotFFI.kt           ← Panama Linker/downcall cache
│       └── …                         ← KSP, hot reload, resource loaders
├── iosMain/                          ← iOS Kotlin/Native (shared by arm64 + simulator)
│   └── kotlin/net/multigesture/kanama/
│       ├── ffi/MemorySegment.kt      ← actual class MemorySegment (the existing Long shim)
│       ├── binding/runtime/ObjectCalls.kt        ← actual object ObjectCalls (C-shim dispatch)
│       ├── binding/runtime/{BuiltinCalls,ObjectCallsGenerated}.kt   ← iOS-only
│       ├── ios/{KanamaIosRuntime,IosCallableRegistry,…}.kt
│       └── api/IosGodotApi.kt        ← hand-written iOS runtime glue (Phase 4.5 shrinks this)
│   └── kotlin/java/lang/foreign/      ← REMOVED (the shim moves to net.multigesture.kanama.ffi)
├── iosArm64Main/                     ← cinterop def + per-target script srcDirs (today's structure)
│   └── nativeInterop/cinterop/kanama_ios.def
└── iosSimulatorArm64Main/            ← same
```

### What is shared (`commonMain`)

- **Generated wrappers** (`api/*.kt`) — the entire `Node3D`/`CharacterBody3D`/…
  surface. One file per class, generated by `scripts/generate_api_wrapper.py`.
- **Value types** (`types/*.kt`) — `Vector2/3/4`, `Transform3D`, `Basis`,
  `Color`, `Quaternion`, `Rect2`, `AABB`, `Plane`, `Projection`, `NodePath`,
  `RID`, … One file per type, shared.
- **`expect class MemorySegment`** — the cross-platform handle type (see §2).
- **`expect object ObjectCalls`** — the platform seam (see §2).
- **Annotations-facing API** — the `@ScriptClass`/`@ScriptProperty`/`@Signal`
  definitions (currently in the separate `:annotations` module; confirm KMP
  compatibility in step 4, or move into `commonMain`).

### What is `expect`/`actual`

| Declaration | `commonMain` | `jvmMain` actual | `iosMain` actual |
|---|---|---|---|
| `MemorySegment` | `expect class` | `actual typealias = java.lang.foreign.MemorySegment` | `actual class` (the existing `Long` shim, repackaged to `net.multigesture.kanama.ffi`) |
| `ObjectCalls` | `expect object` (all generated helper signatures) | `actual object` (Panama `MethodHandle` bodies) | `actual object` (C-shim `kanama_ios_godot_ptrcall` dispatch) |

### What stays platform-specific (NOT shared)

- **Bootstrap** — `bootstrap.c` (JVM JNI_CreateJavaVM) and the iOS C shim
  (`ios/bootstrap/kanama_ios_shim.c`) remain platform-specific native code.
  They are not Kotlin source and are unaffected by the source-set split.
- **FFI layer** — `GodotFFI.kt` (Panama `Linker`/downcall cache) stays in
  `jvmMain`. The iOS equivalent is the C shim + the `@CName` exports in
  `iosMain/.../ios/`.
- **Builtin / Variant machinery** — `BuiltinTypes.kt`,
  `VariantConverters.kt`, `Upcalls.kt` (JVM Panama ABI sizes, destroy helpers,
  upcall stubs) stay `jvmMain`. `BuiltinCalls.kt` (iOS C-shim Variant path)
  stays `iosMain`.
- **Binding/script-language/runtime glue** — `KanamaBinding`, `KanamaScript`,
  `KanamaScriptInstance`, `KanamaScriptLanguage`, `ObjectRegistry`,
  `ScriptBridge`, `KanamaHotReload`, resource loaders — all `jvmMain` (the JVM
  is the only target with a real script language; iOS uses
  `KanamaIosRuntime`).
- **iOS runtime glue** — `KanamaIosRuntime`, `IosCallableRegistry`,
  `IosGodotApi.kt` (hand-written) — `iosMain`.
- **`real_t` / `GodotReal`** — stays per-platform for this migration (see §5).

### Why fold `:ios-runtime` into the root module (Option A)

| Option | Description | Verdict |
|---|---|---|
| **A — root module becomes KMP, `:ios-runtime` folded in** | One KMP module, `jvm()` + `iosArm64()` + `iosSimulatorArm64()`. | **Recommended.** The only option that makes drift impossible by construction (one source tree). Avoids the circular dependency of Option B. |
| B — new `:wrappers` KMP module; root + `:ios-runtime` depend on it | `:wrappers` owns `commonMain` + per-platform `ObjectCalls`; root JVM + `:ios-runtime` keep their glue. | Rejected. The JVM `actual ObjectCalls` needs `GodotFFI` from the root module → `:wrappers` → root → `:wrappers` cycle. Breaking it means moving `GodotFFI`/`BuiltinTypes`/`Upcalls` into `:wrappers/jvmMain` — the same amount of movement as A, plus a new module boundary and publish coordinate. |
| C — root publishes wrapper source as a `commonMain`-shaped artifact `:ios-runtime` consumes | Cross-module source-set sharing. | Rejected. Fragile (no IDE support, recomputation issues). |

Option A is the correct end state. Option B is the fallback if step 1 of the
migration (§3) discovers the KSP-on-iOS-in-root-module wiring is incompatible
with the JVM publish pipeline — but the design commits to A and only falls
back to B with explicit maintainer justification.

### Gradle-model invariants that MUST be preserved

- **`kanama.jar` stays a self-contained fat-jar** (bundles `kotlinx-coroutines`
  + stdlib; `bootstrap.c` puts it alone on the classpath —
  `build.gradle.kts:289`). The KMP `jvmJar` must produce the same artifact
  (now bundling the `jvmMain` classes). Validate byte-stability in step 1.
- **iOS xcframework build** (`assembleIosDeviceKanamaXcframework` etc.) keeps
  producing `kanama_ios.{debug,release}.xcframework` from the
  `iosArm64`/`iosSimulatorArm64` static libs + the C shim. The Gradle tasks
  move from `:ios-runtime` to the root module; the artifact paths and
  `installIosAddon` flow are unchanged.
- **Android `Sync`** keeps producing `build/generated/kanamaAndroidSources`
  for the AGP library. Repoint the `Sync` at `commonMain` + `jvmMain` instead
  of `src/main/kotlin` (see §3 step 1 + the Android consumption note below).
- **KSP-on-iOS wiring** (`kspIosArm64`/`kspIosSimulatorArm64` + the
  `kanamaScriptRoots` arg, validated in Phase 3 — see
  [script-model-unification-design.md](./script-model-unification-design.md)
  Option B) moves from `:ios-runtime` to the root module. Known-good.

### Android consumption after unification (the remap stays)

Android is NOT a KMP target during this migration. The textual-remap pipeline
stays, with three adjustments:

1. **`Sync` source paths change.** `prepareAndroidKanamaSources` copies
   `commonMain/kotlin` + `jvmMain/kotlin` (excluding `example/**`) instead of
   `src/main/kotlin`. The `annotations` module + demo `kotlin-src` + KSP
   output paths are unchanged.
2. **`expect`/`actual` keyword handling.** For files present in *both*
   `commonMain` and `jvmMain` (the `expect`/`actual` pairs — `MemorySegment`,
   `ObjectCalls`), the `Sync` copies **only the `jvmMain` file** and strips
   the `actual` keyword (a new remap rule: `actual ` → ``). For files only in
   `commonMain` (wrappers, value types — the shared surface), copy as-is.
   This avoids the duplicate-declaration conflict when the `expect` and
   `actual` are both stripped to plain Kotlin.
3. **`MemorySegment` import remap.** The wrappers in `commonMain` import
   `net.multigesture.kanama.ffi.MemorySegment` (the `expect`'s package). Add a
   remap rule: `net.multigesture.kanama.ffi.MemorySegment` →
   `com.v7878.foreign.MemorySegment` so the Android copy resolves to
   PanamaPort directly (the `jvmMain` `actual typealias` file is excluded by
   rule 2, so no typealias conflict).

The `auditAndroidKanamaSources` forbidden-fragment list gains `expect ` and
`actual ` so a stale keyword fails the build loudly. The existing
`java.lang.foreign` → `com.v7878.foreign` rule remains (for any residual JVM
imports in `jvmMain` glue like `GodotFFI.kt`).

> **Long-term follow-up (out of scope for 07–12):** restructure
> `android/godot-plugin` to use a KMP `android()` target, consuming
> `commonMain` + `androidMain` (with `actual ObjectCalls` over PanamaPort +
> `actual typealias MemorySegment = com.v7878.foreign.MemorySegment`). That
> eliminates the remap + audit entirely. The remap + audit remain valid
> until then; this design does not require the Android re-architecture.

## 2. `ObjectCalls` `expect/actual` contract

The shared contract is an `expect object ObjectCalls` in `commonMain`
declaring every helper signature the generated wrappers call. The generator
emits the `expect` declaration block from the same CallShape survey that
today emits the iOS `ObjectCallsGenerated.kt` extensions — so the `expect`
and the `actual`s stay in sync by construction, not by hand-maintenance.

### Representative signatures (the full set is generated)

The generated wrappers reference ~1467 distinct `ObjectCalls.*` helper shapes
today (`docs/internals/ios-backend-architecture.md` survey). The `expect`
declares them all. A representative subset (the pattern is uniform):

```kotlin
// commonMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCalls.kt
// GENERATED by scripts/generate_api_wrapper.py — do not edit by hand.

package net.multigesture.kanama.binding.runtime

import net.multigesture.kanama.ffi.MemorySegment
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.types.Vector3i

expect object ObjectCalls {
    // MethodBind + singleton resolution (per-platform cached).
    fun getMethodBind(className: String, methodName: String, hash: Long): MemorySegment
    fun constructObject(className: String): MemorySegment
    fun getSingleton(name: String): MemorySegment

    // No-args ptrcall shapes.
    fun ptrcallNoArgsRetBool(bind: MemorySegment, receiver: MemorySegment): Boolean
    fun ptrcallNoArgsRetInt(bind: MemorySegment, receiver: MemorySegment): Int
    fun ptrcallNoArgsRetLong(bind: MemorySegment, receiver: MemorySegment): Long
    fun ptrcallNoArgsRetDouble(bind: MemorySegment, receiver: MemorySegment): Double
    fun ptrcallNoArgsRetObject(bind: MemorySegment, receiver: MemorySegment): MemorySegment
    fun ptrcallNoArgsRetString(bind: MemorySegment, receiver: MemorySegment): String
    fun ptrcallNoArgsRetNodePath(bind: MemorySegment, receiver: MemorySegment): NodePath
    fun ptrcallNoArgsRetVector2(bind: MemorySegment, receiver: MemorySegment): Vector2
    fun ptrcallNoArgsRetVector3(bind: MemorySegment, receiver: MemorySegment): Vector3
    fun ptrcallNoArgsRetTransform3D(bind: MemorySegment, receiver: MemorySegment): Transform3D
    // … full set generated from the CallShape taxonomy …

    // Single-arg ptrcall shapes (one per (arg-kind, return-kind) combo).
    fun ptrcallWithBoolArgRetUnit(bind: MemorySegment, receiver: MemorySegment, arg: Boolean)
    fun ptrcallWithIntArgRetUnit(bind: MemorySegment, receiver: MemorySegment, arg: Int)
    fun ptrcallWithDoubleArgRetUnit(bind: MemorySegment, receiver: MemorySegment, arg: Double)
    fun ptrcallWithStringArgRetUnit(bind: MemorySegment, receiver: MemorySegment, arg: String)
    fun ptrcallWithObjectArgRetUnit(bind: MemorySegment, receiver: MemorySegment, arg: MemorySegment)
    fun ptrcallWithVector3ArgRetUnit(bind: MemorySegment, receiver: MemorySegment, arg: Vector3)
    fun ptrcallWithVector3ArgRetBool(bind: MemorySegment, receiver: MemorySegment, arg: Vector3): Boolean
    fun ptrcallWithTransform3DArgRetUnit(bind: MemorySegment, receiver: MemorySegment, arg: Transform3D)
    // … full set …

    // Multi-arg + vararg shapes (the Variant object_method_bind_call path).
    fun callWithVariantArgs(
        bind: MemorySegment,
        receiver: MemorySegment,
        args: Array<Any?>,
    ): Any?
    // … full set …

    // Typed-array return shapes (destroy-after-read on JVM; C-shim blob on iOS).
    fun ptrcallNoArgsRetObjectArray(bind: MemorySegment, receiver: MemorySegment): List<MemorySegment>
    fun ptrcallNoArgsRetPackedColorArray(bind: MemorySegment, receiver: MemorySegment): List<Color>
    // … full set …
}
```

### Per-platform `actual` implementations

```kotlin
// jvmMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCalls.kt
package net.multigesture.kanama.binding.runtime
import net.multigesture.kanama.ffi.GodotFFI
import java.lang.foreign.Arena
import java.lang.foreign.MemorySegment
import java.lang.foreign.ValueLayout.*

actual object ObjectCalls {
    private val classdbGetMethodBind by lazy {
        GodotFFI.lookup("classdb_get_method_bind", FunctionDescriptor.of(ADDRESS, ADDRESS, ADDRESS, JAVA_LONG))
    }
    // … the existing Panama implementation (PtrcallScratch, Arena.ofConfined,
    //    MethodHandle.invokeExact, BuiltinTypes destroy paths) …
    actual fun ptrcallNoArgsRetBool(bind: MemorySegment, receiver: MemorySegment): Boolean { /* Panama body */ }
    actual fun ptrcallWithVector3ArgRetUnit(bind: MemorySegment, receiver: MemorySegment, arg: Vector3) { /* Panama body */ }
    // …
}
```

```kotlin
// iosMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCalls.kt
@file:OptIn(ExperimentalForeignApi::class)
package net.multigesture.kanama.binding.runtime
import kotlinx.cinterop.*
import net.multigesture.kanama.ios.cinterop.*

actual object ObjectCalls {
    private const val PT_VOID = 0
    private const val PT_BOOL = 1
    // … the existing PT_* tag constants …
    actual fun ptrcallNoArgsRetBool(bind: MemorySegment, receiver: MemorySegment): Boolean { /* C-shim dispatch */ }
    actual fun ptrcallWithVector3ArgRetUnit(bind: MemorySegment, receiver: MemorySegment, arg: Vector3) { /* C-shim dispatch */ }
    // … plus the generated ObjectCallsGenerated.kt bodies inlined as `actual` members …
}
```

### Key contract rules

1. **Signatures are identical across `expect`/`actual`.** Same names, same
   parameter types, same return types. The `expect` is the single source of
   truth; both `actual`s must satisfy it. The generator's
   `check_wrapper_generator.py` is extended to verify the `expect` member set
   equals the union of helper names the wrappers call (a build-time gate).
2. **The iOS `ObjectCallsGenerated.kt` extension functions become `actual`
   members.** Today the iOS generated helpers are `fun ObjectCalls.foo(...)`
   extensions (`ObjectCallsGenerated.kt`). Extensions do **not** satisfy
   `expect object` member declarations, so the generator must emit them
   *inside* the `actual object ObjectCalls` body. The CallShape survey
   already drives both emissions; the generator change is mechanical (move
   the extension bodies into the object body, drop the `ObjectCalls.` receiver).
3. **`MemorySegment` is the one cross-platform type.** Every signature takes
   `MemorySegment` (the `expect class` from §1) for handles/binds. The JVM
   `typealias` resolves it to `java.lang.foreign.MemorySegment`; the iOS
   `actual class` is the `Long` shim; Android's remap resolves it to
   `com.v7878.foreign.MemorySegment`. No `expect`/`actual` for `MemorySegment`
   in the wrappers' import line beyond the shared `expect class` declaration.
4. **No per-platform wrapper divergence.** A shape that can't be expressed on
   one platform is **skipped on all platforms** (the generator's conservative
   skip logic stays). This is the invariant that makes drift impossible:
   `Node.createTween()` can't be `open` on iOS and `fun` on desktop when
   there's one `Node.kt` in `commonMain`.

### `MemorySegment` `expect`/`actual` (the fragile corner — call out explicitly)

```kotlin
// commonMain/kotlin/net/multigesture/kanama/ffi/MemorySegment.kt
package net.multigesture.kanama.ffi
expect class MemorySegment {
    fun address(): Long
    companion object {
        val NULL: MemorySegment
        fun ofAddress(address: Long): MemorySegment
    }
}
```

```kotlin
// jvmMain/kotlin/net/multigesture/kanama/ffi/MemorySegment.kt
package net.multigesture.kanama.ffi
actual typealias MemorySegment = java.lang.foreign.MemorySegment
```

```kotlin
// iosMain/kotlin/net/multigesture/kanama/ffi/MemorySegment.kt
package net.multigesture.kanama.ffi
actual class MemorySegment private actual constructor(private val value: Long) {
    actual fun address(): Long = value
    actual companion object {
        actual val NULL: MemorySegment = MemorySegment(0)
        actual fun ofAddress(address: Long): MemorySegment = MemorySegment(address)
    }
}
```

The `expect` is in package `net.multigesture.kanama.ffi` (Kanama-own
namespace), **not** `java.lang.foreign`, to avoid the recursive-`typealias`
trap (`typealias MemorySegment = java.lang.foreign.MemorySegment` in package
`java.lang.foreign` would be a self-reference). The existing iOS shim
(`ios-runtime/src/iosMain/kotlin/java/lang/foreign/MemorySegment.kt`) moves
to `iosMain/.../ffi/MemorySegment.kt` and is repackaged. The wrappers' import
line changes from `import java.lang.foreign.MemorySegment` to
`import net.multigesture.kanama.ffi.MemorySegment` (a one-time generator
change).

> **This is the single most likely place for a Kotlin compile surprise.**
> Step 0 of the migration (§3) is a spike that validates exactly this
> `expect`/`typealias`/`actual class`/Android-remap story on all three
> platforms before any broad source movement. If the spike fails, fall back
> to a Kanama-own `GodotHandle` wrapper type (heavier; requires re-emitting
> every wrapper signature) or to Option B's split module before proceeding.

## 3. Migration path (incremental, gates per step)

Each step is independently green and revertible. No big-bang.

### Step 0 — Spike (de-risk the load-bearing Kotlin compile assumptions)

**Scope:** move a single trivial generated wrapper (e.g. `Label.kt`) into a
temporary `commonMain` source set with minimal `expect class MemorySegment` +
`expect object ObjectCalls` (just the signatures `Label` calls). Wire the
root module as KMP with `jvm()` + `iosArm64()` in a throwaway branch.

**Gate:**
- `./gradlew :kanama:jvmJar` builds; the single wrapper compiles against the
  JVM `actual typealias MemorySegment`.
- `DEVELOPER_DIR=… ./gradlew compileKotlinIosArm64` builds; the wrapper
  compiles against the iOS `actual class MemorySegment`.
- `./gradlew :android-godot-plugin:assembleAndroidPluginAar` (or the
  `prepareAndroidKanamaSources` + `auditAndroidKanamaSources` pair) builds;
  the remapped copy resolves `MemorySegment` to PanamaPort.
- If any of these fail, revise the `MemorySegment` approach (e.g. Kanama-own
  `GodotHandle`) before proceeding to step 1.

**Reversibility:** throwaway branch; nothing merged.

### Step 1 — Restructure root module to KMP (no source movement yet)

**Scope:** convert root `kanama` from `kotlin("jvm")` to
`kotlin("multiplatform")` with `jvm()` + `iosArm64()` + `iosSimulatorArm64()`.
Move `:ios-runtime`'s KMP wiring (cinterop, KSP-on-iOS, source set hierarchy,
`kotlinScriptDirs`) into the root module. Delete `:ios-runtime` from
`settings.gradle.kts`. Keep all existing source in `jvmMain` (the current
`src/main/kotlin` content, moved) + `iosMain` (the current
`ios-runtime/src/iosMain` content, moved). `commonMain` empty. Repoint the
Android `Sync` at `jvmMain` instead of `src/main/kotlin`.

**Gate:**
- `./gradlew jar` produces `kanama.jar` byte-stable (same content as the
  pre-migration jar; the `jvmJar` task replaces the `jar` task and the fat-jar
  bundling in `build.gradle.kts:289` is preserved).
- `./gradlew assembleIosDeviceKanamaXcframework` builds; the xcframework
  artifacts are unchanged.
- `./gradlew installAddonJar` smoke (example_project loads in Godot).
- `./gradlew assembleAndroidPluginAar` builds; the remapped Android source
  compiles.
- `./scripts/local_ci.sh /path/to/godot-4.7-stable` passes.
- iOS device smoke: the full demo matrix (Bunnymark, Match3, 3D-Platformer,
  dodge, squash, character-controller, Racing, FPS, third-person) still
  plays on iPhone — this is the gate that the KMP conversion didn't regress
  the iOS runtime.
- Android APK smoke: at least one demo (Match3 or 3D-Platformer) launches on
  Pixel 7.
- `mkdocs build --strict` passes (docs unaffected, but run it).

**Reversibility:** revert the Gradle model change; `:ios-runtime` is restored
from git.

### Step 2 — Introduce `expect`/`actual` for the platform seams (wrappers stay in `jvmMain`/`iosMain`)

**Scope:** add `expect class MemorySegment` in `commonMain`
(`net.multigesture.kanama.ffi`), `actual typealias` in `jvmMain`, `actual class`
in `iosMain` (move + repackage the existing shim). Add `expect object
ObjectCalls` in `commonMain` (generated, full signature set), `actual object
ObjectCalls` in `jvmMain` (the current `binding/runtime/ObjectCalls.kt`
content) and `iosMain` (the current `ios-runtime/.../ObjectCalls.kt` +
`ObjectCallsGenerated.kt` inlined as members). Rewrite the wrappers' imports
(`java.lang.foreign.MemorySegment` → `net.multigesture.kanama.ffi.MemorySegment`).
The wrappers themselves still live in `jvmMain`/`iosMain` (two copies) — only
the seam declarations move to `commonMain`.

**Generator change (minimal):** `generate_api_wrapper.py` emits the `expect
object ObjectCalls { ... }` declaration block to `commonMain` from the
CallShape survey; the `--ios-objectcalls` emission inlines the bodies into the
`iosMain` `actual object` instead of emitting `ObjectCallsGenerated.kt`
extensions. The desktop `--emit-class` output's import line changes to
`net.multigesture.kanama.ffi.MemorySegment`.

**Gate:**
- JVM + iOS + Android all compile with the `expect`/`actual` in place;
  wrappers still in two copies.
- `check_wrapper_generator.py` passes (extended to verify `expect` member set
  = wrappers' helper-name union).
- `auditAndroidKanamaSources` passes with the new `expect `/`actual `
  forbidden-fragment rules and the new `MemorySegment` import-remap rule.
- Same device/APK smoke as step 1.

**Reversibility:** revert the `expect`/`actual` declarations + the import
rewrite; the two-copy state is restored.

### Step 3 — Move generated wrappers + value types to `commonMain` (the keystone)

**Scope:** move `jvmMain/.../api/*.kt` + `jvmMain/.../types/*.kt` to
`commonMain/.../api/` + `commonMain/.../types/`. **Delete the `iosMain`
copies** (they are now redundant — one source in `commonMain`). The wrappers
resolve `ObjectCalls` (expect) + `MemorySegment` (expect) + value types (now
in `commonMain`) — all shared.

**Generator change:** `--api-dir` default becomes
`src/commonMain/kotlin/net/multigesture/kanama/api`. `--ios-emit-class` /
`--ios-output-dir` are retired (no separate iOS copy). `--ios-objectcalls`
writes the `iosMain` `actual object ObjectCalls` generated members. KDoc sync
(`sync_kdoc_from_godot_docs.py`) targets `commonMain` — one KDoc block per
class, shared. Regenerate KDoc as part of this step.

**Resolve `Node.createTween()` openness (the drift symptom):** after this
step, `Node.kt` is one file in `commonMain` — `createTween()` can't be `open`
on iOS and `fun` on desktop. The fix moves to generator policy (task 11:
subclass-specific override generation — `SceneTree.create_tween` overrides
`Node.create_tween` at the generator level) OR the hand-written iOS
`SceneTree` subclass is retired in favor of a generated `SceneTree`. **This
step is not "done" until `createTween()` is `fun` (final) on all platforms
and the FPS demo (which exercises the `SceneTree` path) still plays on
iPhone.** If neither generator policy nor retirement is ready in this step,
hold the step until task 11 lands the override generation — do not merge a
shared `Node.kt` that regresses the FPS demo.

**Gate:**
- `check_wrapper_generator.py` passes; `api_wrapper_coverage.py` regenerated;
  `api_wrapper_generator_report.py` regenerated.
- JVM + iOS + Android build.
- iOS device smoke: the **full demo matrix** — this is the moment drift
  becomes impossible, so the matrix is the proof. Pay particular attention
  to FPS (`SceneTree`/`Tween`), third-person (Variant Vector3,
  AnimationTree), and 3D-Platformer (NodePath, Transform3D).
- Android APK smoke.
- `sync_kdoc_from_godot_docs.py --check` passes (KDoc now single-source).
- `ios_handwritten_report.py` regenerated; 0 STUB / 0 SUGAR maintained.

**Reversibility:** revert the move; the two-copy state is restored from git.
This is the highest-blast-radius step and the one most likely to surface a
hidden platform-specific wrapper edit — the device smoke is the safety net.

### Step 4 — Move annotations-facing API + remaining shared surface to `commonMain`

**Scope:** confirm the `:annotations` module is KMP-compatible (it should be
— it's pure Kotlin annotations) or move the annotation definitions into
`commonMain`. Evaluate any remaining shared surface case-by-case (e.g.
`VariantType` if it can be shared, `GodotStrings`, `GodotStructs`).

**Gate:** same as step 3 (a subset suffices if no wrapper/type movement).

### Step 5 — Retire the Android textual remap (long-term, out of scope for 07–12)

Restructure `android/godot-plugin` to use a KMP `android()` target consuming
`commonMain` + `androidMain` (with `actual ObjectCalls` over PanamaPort +
`actual typealias MemorySegment = com.v7878.foreign.MemorySegment`).
Eliminates the remap + audit. **Flagged as a future follow-up; not required
for the unification's keystone gain.** The remap + audit remain valid until
then.

## 4. Generator changes

`scripts/generate_api_wrapper.py` changes (described, not implemented here):

| Current behavior | After unification |
|---|---|
| `--emit-class` writes desktop wrappers to `--api-dir` (default `src/main/kotlin/.../api`). | `--api-dir` default becomes `src/commonMain/kotlin/.../api`. One file per class. |
| `--ios-emit-class` + `--ios-output-dir` write a separate iOS copy (byte-identical except the `binding.runtime.*` import). | **Retired.** No separate iOS copy. The iOS-specific `import net.multigesture.kanama.binding.runtime.*` line is removed — the `actual object ObjectCalls` members (including the generated ones) are visible to the `commonMain` wrappers via the `expect`. |
| `--ios-objectcalls` writes `ObjectCallsGenerated.kt` as `fun ObjectCalls.foo(...)` extension functions. | Writes the generated helper bodies **inside** the `iosMain` `actual object ObjectCalls` body (as `actual fun foo(...)` members). The CallShape survey is unchanged; only the emission target moves from a separate file to the object body. |
| No `expect` emission. | New: emit `expect object ObjectCalls { ... }` (signatures only) to `commonMain/.../binding/runtime/ObjectCalls.kt` from the same CallShape survey. |
| Wrapper import: `import java.lang.foreign.MemorySegment`. | Wrapper import: `import net.multigesture.kanama.ffi.MemorySegment`. |
| KDoc sync targets `src/main/kotlin`. | KDoc sync targets `src/commonMain/kotlin`. One KDoc block per class. |

**Generator invariants preserved:**

- The conservative skip logic (`IOS_AUDIT_ONLY`, `IOS_ARG_KINDS`,
  `IOS_RET_KOTLIN`) stays — a shape skipped on one platform is skipped on all.
  The iOS audited-type set still gates which shapes generate at all; after
  unification, a shape that generates is shared, and a shape that's skipped
  is absent everywhere (no silent per-platform stub).
- The `--skip-report` / `--ios-skip-report` paths merge into one skip report
  (one source set → one skip set).
- `check_wrapper_generator.py` fixtures extended to verify the `expect`
  member set equals the wrappers' helper-name union (catches a generator
  drift between the `expect` and the `actual`s).

## 5. `GodotReal` decision (Phase 4.4)

**Recommendation: defer 4.4. Centralizing `real_t` is OUT OF SCOPE for this
migration. Task 07 picks it up after the shared wrappers land.**

### Why defer

1. **The current single-precision target is the release baseline (0.2.2).**
   Double-precision Godot builds (`precision=double`) are a future Godot
   build config, not yet a Kanama target. No live consumer needs
   `real_t = Double` today.
2. **iOS would need a value-type rewrite to benefit.** iOS value types
   currently hardcode `Double` components
   (`ios-runtime/src/iosMain/.../types/Vector3.kt`), and the iOS `ObjectCalls`
   marshals `Double`→`float32` at the ptrcall layer (per the
   `ios-backend-architecture.md` width table). Centralizing `real_t` requires
   rewriting every iOS value type to use `real_t` — a Phase-2-sized change
   that couples 4.4 to the long-tail type work (task 08). That blows the
   blast radius of this migration into the type-precision layer.
3. **The unification's keystone gain does not depend on `real_t`.** Ending
   wrapper drift is orthogonal to whether `real_t` is `Float` or `Double`.
   The `commonMain` wrappers + `expect/actual ObjectCalls` land identically
   regardless of precision.
4. **The architecture review (F3) ranks the per-call arena optimization
   (task 12) hotter than `GodotReal` centralization.** F3 is gated on this
   design and is a per-frame performance win; 4.4 is a future-build-config
   enabler. Sequencing 4.4 after 12 (or in parallel with 08) is fine.

### What 4.4 will do (when task 07 picks it up)

After the shared wrappers land in `commonMain`, task 07 introduces:

```kotlin
// commonMain
expect typealias real_t : Number   // or expect value class if Kotlin version supports
expect object GodotReal {
    val SIZE_BYTES: Long
    val ALIGN_BYTES: Long
    fun fromNumber(value: Number): real_t
    fun readIndex(segment: MemorySegment, index: Long): real_t
    fun writeIndex(segment: MemorySegment, index: Long, value: real_t)
    // …
}
```

```kotlin
// jvmMain (driven by -PkanamaPrecision=single|double, today's generateKanamaReal task)
actual typealias real_t = Float   // or Double
actual object GodotReal { actual val SIZE_BYTES = 4L; /* … */ }
```

```kotlin
// iosMain (single-precision for now; double-precision is a future iOS engine build)
actual typealias real_t = Float
actual object GodotReal { actual val SIZE_BYTES = 4L; /* … */ }
```

Plus: rewrite the iOS value types to use `real_t` instead of `Double`, and
update the iOS `ObjectCalls` marshalling to lay out `real_t` components at
their native width. The JVM `generateKanamaReal` task
(`build.gradle.kts:64`) continues to drive JVM precision; the Android `Sync`
keeps its own `Real.kt` override.

### Carve-out from this design (the minimal scaffold, optional)

If the implementer wants a forward-compat scaffold, this migration MAY
introduce the `expect typealias real_t` + `expect object GodotReal` in
`commonMain` with single-precision actuals on JVM + iOS **without rewriting
the iOS value types** (they continue to use `Double`). The scaffold is a
no-op for behavior today but unblocks 07 to later flip the actuals to
`Double` and rewrite the iOS value types without touching `commonMain`. **The
scaffold is optional; if it adds any compile risk, drop it and defer 4.4
entirely.** The lower-risk call is to defer the scaffold too and let 07 own
all of `real_t`.

## 6. Risks + blast radius

| Risk | Blast radius | Mitigation |
|---|---|---|
| **Root module Gradle-model conversion** (`kotlin("jvm")` → `kotlin("multiplatform")`) | Touches every target: JVM publish (`kanama.jar` fat-jar for `bootstrap.c`), iOS xcframework build, Android `Sync` source path. | Step 1 preserves all three publish flows byte-identically before any source movement. The `jvmJar` task replaces `jar`; the fat-jar bundling logic moves with it. |
| **`expect`/`actual` for `MemorySegment`** (the `typealias`-in-same-package self-reference trap) | If the `expect` package is wrong, JVM or iOS compile fails on every wrapper. | Step-0 spike validates the `expect`/`typealias`/`actual class`/Android-remap story on all three platforms with one trivial wrapper before broad movement. The `net.multigesture.kanama.ffi` package choice avoids the self-reference. |
| **`Node.createTween()` openness becomes a generator policy question** | The iOS `open` exception can't survive one shared `Node.kt`. The FPS demo (which exercises `SceneTree.create_tween`) regresses if the override isn't preserved. | Step-3 gate explicitly resolves this: `createTween()` is `fun` (final) on all platforms AND the FPS demo still plays on iPhone. The fix is generator subclass-override emission (task 11) or retirement of the hand-written `SceneTree`. Hold the step until the fix lands. |
| **`ObjectCallsGenerated.kt` extensions become `actual object` members** | The iOS extension-function pattern must become members to satisfy the `expect`. | Generator change in step 2/3; the CallShape survey already drives both emissions, so the emission is mechanical (move bodies into the object, drop the receiver). |
| **Android `Sync` exclude + remap rules grow** | New remap rule for `MemorySegment`; `expect`/`actual` keyword handling; exclude-by-path for the `commonMain` `expect` files. | The `auditAndroidKanamaSources` task already fails loudly on forbidden fragments; add `expect ` and `actual ` to the forbidden list so a stale keyword fails the build. |
| **KSP-on-iOS wiring moves from `:ios-runtime` to root** | The `kspIosArm64`/`kspIosSimulatorArm64` deps + `kanamaScriptRoots` arg move. | Validated in Phase 3 (script-model-unification-design.md Option B, committed `0735e99`); the wiring is known-good. |
| **Per-call arena optimization (task 12) is gated on this** | F3 touches `ObjectCalls.kt`; after unification the JVM `actual ObjectCalls` is the single place. | The design keeps the JVM `actual ObjectCalls` as the single F3 site; iOS is unaffected (C-shim dispatch, no arenas). |
| **KDoc sync becomes single-source** | `sync_kdoc_from_godot_docs.py` targets `commonMain`; the stale iOS KDoc drift disappears. | Regenerate KDoc as part of step 3; `--check` is in CI. |
| **iOS value-type `real_t` divergence (4.4) is NOT fixed by this migration** | iOS value types still hardcode `Double`; a double-precision Godot build would still corrupt iOS value components. | Explicitly deferred (§5); task 07 owns the iOS value-type rewrite. The unification does not claim to fix precision divergence. |
| **`:ios-runtime` deletion removes a Gradle subproject** | Any external consumer depending on `:ios-runtime` coordinates breaks. | `:ios-runtime` is an internal subproject (not published; the iOS artifact is the xcframework from root tasks). No external coordinates to break. Confirmed in `settings.gradle.kts`. |

### What to watch at each step

- **Step 1:** `kanama.jar` byte-stability (diff the pre/post jar contents);
  iOS xcframework artifact path + size; Android `Sync` output diff (should be
  ~identical text after the `jvmMain` repoint).
- **Step 2:** `expect`/`actual` compile on all three targets (the spike
  de-risks this, but the full `ObjectCalls` signature set is larger); the
  Android remap audit catches any stale `expect`/`actual` keyword.
- **Step 3:** the full iOS demo matrix on device — this is the proof that
  drift ended and no hidden platform-specific wrapper edit was lost. FPS +
  third-person are the canaries (they exercise the `SceneTree`/`Tween` and
  Variant paths).
- **Step 4:** no behavior change expected; a subset gate suffices.

## 7. Exit criteria for implementation (tasks 07–12)

This design is the keystone for tasks 07–12. Each follow-on task reads this
section to know exactly what to build.

### Meta-exit-criteria for the unification itself (this design, implemented)

- [ ] One wrapper source set in `commonMain`; no `iosMain` wrapper copies.
- [ ] One value-type source set in `commonMain`; `Rect2i`/`Vector4i` exist on
      all platforms (the Phase 2.7 long-tail types land once, shared).
- [ ] `expect object ObjectCalls` in `commonMain` with all generated helper
      signatures; `actual object ObjectCalls` in `jvmMain` (Panama) + `iosMain`
      (C-shim).
- [ ] `expect class MemorySegment` in `commonMain` (`net.multigesture.kanama.ffi`);
      `actual typealias` in `jvmMain`; `actual class` in `iosMain`; Android
      remap resolves to `com.v7878.foreign.MemorySegment`.
- [ ] `Node.createTween()` is `fun` (final) on all platforms; the `SceneTree`
      override is generator-emitted (task 11) or the hand-written `SceneTree`
      is retired.
- [ ] KDoc is single-source (`sync_kdoc_from_godot_docs.py` targets
      `commonMain`); the stale iOS KDoc drift is gone.
- [ ] `kanama.jar` byte-stable; iOS xcframework unchanged; Android `Sync` +
      audit green; `installAddonJar` + `installIosAddon` flows unchanged.
- [ ] `mkdocs build --strict` passes; `check_wrapper_generator.py` passes;
      `api_wrapper_coverage.py` + `api_wrapper_generator_report.py`
      regenerated; `ios_handwritten_report.py` shows 0 STUB / 0 SUGAR.
- [ ] Full iOS demo matrix green on device; Android APK smoke green on
      Pixel 7; `local_ci.sh` green; `fresh_clone_smoke.sh` green before any
      release tag that includes the unification.

### Per-task exit criteria (what each follow-on task builds against this design)

| Task | What it builds | Where it lands after unification |
|---|---|---|
| **07 — GodotReal centralization** | `expect typealias real_t` + `expect object GodotReal` in `commonMain`; per-platform actuals; iOS value types rewritten to use `real_t`; JVM `-PkanamaPrecision=double` validated. | `commonMain` + `jvmMain` + `iosMain` actuals; the iOS value-type rewrite touches `commonMain/.../types/`. Android `Real.kt` `Sync` override stays. |
| **08 — iOS long-tail shapes** | The ~86 missing shapes (Transform2D, NodePath/StringName returns, Packed*/Typed* arrays, Variant) added to the `expect ObjectCalls` signature set + the iOS `actual` C-shim dispatch + the generator's CallShape set. | A shape added to the `expect` is automatically shared — no separate iOS copy. The shape either generates on all platforms or is skipped on all. |
| **09 — Callable/vararg support** | `Callable` arg + vararg shapes added to `expect ObjectCalls` + per-platform actuals (JVM Panama Variant path, iOS C-shim Variant path). Ownership policy shared in `commonMain`. | The ownership-sensitive `Callable` policy is now one decision in `commonMain`, not two per-platform implementations that can drift. |
| **10 — Handwritten surface reduction** | `IosGodotApi.kt` shrunk to truly-bespoke (coroutine scope, GD math helpers); the hand-written `SceneTree`/`Tween` retired once the `Node.createTween()` openness is resolved at the generator level (task 11). | The hand-written iOS surface is `iosMain`-only; after unification it cannot accidentally shadow a generated `commonMain` wrapper (the wrapper is shared, the hand-written class is `iosMain`-only and must explicitly opt out of generation). |
| **11 — Generator policy cleanup** | Explicit class-collision handling + subclass-override generation (so `SceneTree.create_tween` overrides `Node.create_tween` without an `open` keyword on the shared `Node`). Custom-section fixtures. | This is where the `Node.createTween()` drift is *resolved* (not worked around). The generator emits the override into the `commonMain` `SceneTree.kt` (a generated subclass) or a custom-section; the `open` keyword on `Node` is gone. |
| **12 — Per-call arena optimization** | Extend `PtrcallScratch` in the `jvmMain` `actual ObjectCalls`; retire the ~1,478 per-call `Arena.ofConfined()` blocks in the JVM actual (architecture review F3). | The JVM `actual ObjectCalls` is the single F3 site. iOS `actual ObjectCalls` is unaffected (C-shim dispatch, no arenas). The scratch pattern is JVM-only. |

## Open decisions (need maintainer sign-off)

1. **Option A vs Option B** (root-becomes-KMP vs new `:wrappers` module) —
   recommend **A**. This is the load-bearing structural call; the migration
   path in §3 assumes A. Falling back to B requires explicit justification
   (the only known reason is a step-1 discovery that KSP-on-iOS-in-root is
   incompatible with the JVM publish pipeline — which the Phase 3 validation
   suggests is unlikely).
2. **`MemorySegment` `expect` package** — `net.multigesture.kanama.ffi`
   (recommended, avoids the `typealias` self-reference) vs a Kanama-own
   `GodotHandle` wrapper type (heavier, requires re-emitting every wrapper
   signature). The step-0 spike validates the recommended choice.
3. **`GodotReal` (4.4)** — defer (recommended) vs the minimal scaffold vs
   full centralization in this migration. See §5.
4. **Android long-term** — keep the textual remap (this design) vs restructure
   to a KMP `android()` target (step 5, out of scope). The remap stays for
   07–12; the re-architecture is a separate decision.
5. **`Node.createTween()` resolution** — generator subclass-override emission
   (task 11, recommended) vs retirement of the hand-written iOS `SceneTree`
   (task 10). Step 3 of the migration is held until one of these lands.

## Scope note

This is **design only**. Per the roadmaps (4.3 tagged **opus 4.8** — the
architectural tag) and the task
spec, implementation is gated on maintainer sign-off on this document. Do
not start migrating code (steps 0–4 of §3) until this design is reviewed and
approved. Tasks 07–12 read §7 to know what to build.
