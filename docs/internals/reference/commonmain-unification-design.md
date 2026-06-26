# commonMain Wrapper Unification Design

Roadmap scope: wrapper coverage Phase 4.3, with a decision for Phase 4.4
(`GodotReal` centralization). This is a design record only. The implementation
must land as staged follow-up work after maintainer sign-off because it changes
the Gradle shape consumed by desktop, Android, and iOS.

## Goal

Kanama should have one generated Godot API wrapper source set shared by desktop,
Android, and iOS. Generated class shape, method openness, signatures, method-bind
hashes, KDoc, and skip policy should be identical across platforms. Platform
differences belong behind `expect` declarations for the runtime call surface,
primarily `ObjectCalls`.

The current split allows drift:

- Desktop and Android consume wrappers from the root JVM module under
  `src/main/kotlin`.
- iOS consumes wrapper copies from `:ios-runtime` under `iosMain`.
- Temporary iOS-only exceptions such as `Node.createTween()` openness can exist
  because the two wrapper sets are separate.

After unification, a generated wrapper either compiles for every platform whose
`ObjectCalls` actual implements its shape, or it is not promoted. Platform-only
API shape changes should become impossible by construction.

## Target Source-Set Structure

The lower-risk target is a new shared wrapper/runtime API module rather than an
immediate conversion of the root project from `kotlin("jvm")` to
`kotlin("multiplatform")`.

Recommended target:

```text
:kanama-common-api
  src/commonMain/kotlin/net/multigesture/kanama/api/...
  src/commonMain/kotlin/net/multigesture/kanama/types/...
  src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCalls.kt
  src/jvmMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCalls.kt
  src/iosMain/kotlin/net/multigesture/kanama/binding/runtime/ObjectCalls.kt
  src/iosMain/kotlin/java/lang/foreign/MemorySegment.kt

:kanama (root JVM runtime)
  depends on :kanama-common-api jvm artifact
  keeps bootstrap, GodotFFI, ScriptLanguage, hot reload, resource loaders,
  JVM-only FFI registration, and packaging tasks

:ios-runtime
  depends on :kanama-common-api ios artifact
  keeps Kotlin/Native runtime, C interop, script registry bridge,
  iOS bootstrap/shim integration, coroutine/frame-loop glue, and bespoke
  platform helpers
```

The root project can be converted to KMP later if there is a strong reason, but
doing that in the same migration would combine wrapper unification with a broad
runtime build rewrite. A small KMP module gives the implementation a reversible
first step and lets the current root JVM packaging tasks keep their shape.

Shared in `commonMain`:

- Generated Godot object wrappers in `net.multigesture.kanama.api`.
- Generated value types and hand-authored value-type policy that is truly
  platform-neutral.
- Name constants and generated KDoc.
- The annotation-facing API used by game scripts: wrapper base types,
  constructors from `MemorySegment`, typed return classes, signal/property
  helper types that do not cross into platform bootstrap.
- `expect object ObjectCalls` containing only the functions referenced by
  common wrappers.

Platform-specific:

- Desktop/JVM `ObjectCalls` actual over real Panama (`java.lang.foreign`,
  `GodotFFI`, `object_method_bind_ptrcall`, `object_method_bind_call`).
- Android build of the JVM actual after the existing source remap to
  PanamaPort (`com.v7878.foreign`). The common wrapper module must not import
  Panama APIs except through `java.lang.foreign.MemorySegment`; Android keeps the
  current textual remap mitigation until it is replaced by a real abstraction.
- iOS `ObjectCalls` actual over Kotlin/Native C interop and
  `kanama_ios_godot_ptrcall` / dedicated C-shim helpers.
- iOS `java.lang.foreign.MemorySegment` shim. It remains an iOS source file so
  common wrappers can keep the same constructor/handle type as desktop.
- Bootstrap, FFI lookup, C shim, KSP-generated project script registries, object
  registries, virtual dispatch registration, hot reload, package/install tasks,
  and platform-specific global helpers such as iOS `KanamaScope`.

The common module should not expose `expect` declarations for the whole runtime.
Only generated-wrapper dependencies move first. Runtime services can be shared
later, after wrappers no longer drift.

## `ObjectCalls` Contract

`ObjectCalls` should be generated as an `expect object` in `commonMain`. The
contract is intentionally concrete: every function the generator may emit is a
declaration, and every platform actual must implement the same signature before
the wrapper using it is promoted.

Base contract:

```kotlin
package net.multigesture.kanama.binding.runtime

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.types.*

expect object ObjectCalls {
    fun getSingleton(name: String): MemorySegment
    fun getMethodBind(className: String, methodName: String, hash: Long): MemorySegment
    fun constructObject(className: String): MemorySegment
    fun notifyPostinitialize(instance: MemorySegment)
    fun destroyObject(instance: MemorySegment)

    fun ptrcallNoArgs(methodBind: MemorySegment, instance: MemorySegment)
    fun ptrcallNoArgsRetBool(methodBind: MemorySegment, instance: MemorySegment): Boolean
    fun ptrcallNoArgsRetInt(methodBind: MemorySegment, instance: MemorySegment): Int
    fun ptrcallNoArgsRetUInt32(methodBind: MemorySegment, instance: MemorySegment): Long
    fun ptrcallNoArgsRetLong(methodBind: MemorySegment, instance: MemorySegment): Long
    fun ptrcallNoArgsRetDouble(methodBind: MemorySegment, instance: MemorySegment): Double
    fun ptrcallNoArgsRetObject(methodBind: MemorySegment, instance: MemorySegment): MemorySegment
    fun ptrcallNoArgsRetString(methodBind: MemorySegment, instance: MemorySegment): String
    fun ptrcallNoArgsRetStringName(methodBind: MemorySegment, instance: MemorySegment): String
    fun ptrcallNoArgsRetNodePath(methodBind: MemorySegment, instance: MemorySegment): NodePath
    fun ptrcallNoArgsRetRID(methodBind: MemorySegment, instance: MemorySegment): RID
    fun ptrcallNoArgsRetVector2(methodBind: MemorySegment, instance: MemorySegment): Vector2
    fun ptrcallNoArgsRetVector2i(methodBind: MemorySegment, instance: MemorySegment): Vector2i
    fun ptrcallNoArgsRetVector3(methodBind: MemorySegment, instance: MemorySegment): Vector3
    fun ptrcallNoArgsRetVector3i(methodBind: MemorySegment, instance: MemorySegment): Vector3i
    fun ptrcallNoArgsRetVector4(methodBind: MemorySegment, instance: MemorySegment): Vector4
    fun ptrcallNoArgsRetColor(methodBind: MemorySegment, instance: MemorySegment): Color
    fun ptrcallNoArgsRetRect2(methodBind: MemorySegment, instance: MemorySegment): Rect2
    fun ptrcallNoArgsRetRect2i(methodBind: MemorySegment, instance: MemorySegment): Rect2i
    fun ptrcallNoArgsRetPlane(methodBind: MemorySegment, instance: MemorySegment): Plane
    fun ptrcallNoArgsRetQuaternion(methodBind: MemorySegment, instance: MemorySegment): Quaternion
    fun ptrcallNoArgsRetAABB(methodBind: MemorySegment, instance: MemorySegment): AABB
    fun ptrcallNoArgsRetBasis(methodBind: MemorySegment, instance: MemorySegment): Basis
    fun ptrcallNoArgsRetTransform2D(methodBind: MemorySegment, instance: MemorySegment): Transform2D
    fun ptrcallNoArgsRetTransform3D(methodBind: MemorySegment, instance: MemorySegment): Transform3D
    fun ptrcallNoArgsRetProjection(methodBind: MemorySegment, instance: MemorySegment): Projection
    fun ptrcallNoArgsRetVariantScalar(methodBind: MemorySegment, instance: MemorySegment): Any?

    fun ptrcallWithBoolArg(methodBind: MemorySegment, instance: MemorySegment, value: Boolean)
    fun ptrcallWithIntArg(methodBind: MemorySegment, instance: MemorySegment, value: Int)
    fun ptrcallWithLongArg(methodBind: MemorySegment, instance: MemorySegment, value: Long)
    fun ptrcallWithDoubleArg(methodBind: MemorySegment, instance: MemorySegment, value: Double)
    fun ptrcallWithStringArg(methodBind: MemorySegment, instance: MemorySegment, value: String)
    fun ptrcallWithStringNameArg(methodBind: MemorySegment, instance: MemorySegment, value: String)
    fun ptrcallWithNodePathArg(methodBind: MemorySegment, instance: MemorySegment, value: NodePath)
    fun ptrcallWithRIDArg(methodBind: MemorySegment, instance: MemorySegment, value: RID)
    fun ptrcallWithVector2Arg(methodBind: MemorySegment, instance: MemorySegment, value: Vector2)
    fun ptrcallWithVector2iArg(methodBind: MemorySegment, instance: MemorySegment, value: Vector2i)
    fun ptrcallWithVector3Arg(methodBind: MemorySegment, instance: MemorySegment, value: Vector3)
    fun ptrcallWithVector3iArg(methodBind: MemorySegment, instance: MemorySegment, value: Vector3i)
    fun ptrcallWithColorArg(methodBind: MemorySegment, instance: MemorySegment, value: Color)
    fun ptrcallWithRect2Arg(methodBind: MemorySegment, instance: MemorySegment, value: Rect2)
    fun ptrcallWithTransform2DArg(methodBind: MemorySegment, instance: MemorySegment, value: Transform2D)
    fun ptrcallWithTransform3DArg(methodBind: MemorySegment, instance: MemorySegment, value: Transform3D)
    fun ptrcallWithObjectArgs(
        methodBind: MemorySegment,
        instance: MemorySegment,
        objectArgs: List<MemorySegment>,
    )

    fun callWithVariantArgs(
        methodBind: MemorySegment,
        instance: MemorySegment,
        args: List<Any?>,
    ): Any?
}
```

The snippet above is the minimum contract family, not the full generated list.
Implementation should generate the complete `expect` file from the same
`CallShape` registry that renders wrappers. For compound shapes such as
`ptrcallWithObjectAndBoolArgRetNodePath` or
`ptrcallWithRIDVariantVector2LongDoubleArgsRetBool`, the exact function name,
argument order, Kotlin types, and return type must be generated into the
contract. Manual additions to one platform actual without the matching generated
`expect` declaration should fail review.

Backend mapping:

- JVM actual keeps the current Panama implementation: lookup through `GodotFFI`,
  `ClassDB` method binds, `object_method_bind_ptrcall`, `object_method_bind_call`
  for Variant fallback, and scratch buffers where proven safe.
- Android consumes the JVM actual after the existing PanamaPort remap from
  `java.lang.foreign.*` to `com.v7878.foreign.*`. The common wrapper sources
  should stay oblivious to the remap. Any future replacement should be a
  platform-handle abstraction, not Android-specific wrapper generation.
- iOS actual keeps the current Kotlin/Native implementation: method bind lookup
  through `kanama_ios_godot_get_method_bind`, generic typed ptrcall through
  `kanama_ios_godot_ptrcall`, and dedicated C helpers for strings, packed
  arrays, typed arrays, or other shapes that cannot ride the generic return
  slot.

The contract has one important policy rule: iOS may implement a shape by
throwing only during a temporary migration step, and only for wrappers that are
not exposed to iOS. The final unified state has no platform-stubbed generated
methods. A missing iOS helper means the common wrapper method must stay skipped
until the C-shim and actual helper are audited.

## Migration Path

The migration should be incremental and keep desktop, Android, and iOS green
after each step.

1. Add a no-wrapper `:kanama-common-api` module.
   - Move only source-set plumbing first: KMP plugin, `commonMain`, `jvmMain`,
     `iosMain`, iOS `MemorySegment` shim copy or relocation, and dependencies.
   - Gate: `./gradlew :kanama-common-api:compileKotlinJvm` and
     `DEVELOPER_DIR=/Applications/Xcode.app/Contents/Developer ./gradlew :kanama-common-api:compileKotlinIosArm64`.

2. Generate the `ObjectCalls` contract from the existing call-shape registry.
   - Add a generator mode that emits `expect object ObjectCalls` declarations
     and per-platform actual skeleton reports.
   - Do not move wrappers yet.
   - Gate: generated contract diff is stable under
     `scripts/check_wrapper_generator.py`; no hand-written declaration drift.

3. Move a tiny wrapper slice into `commonMain`.
   - Start with a low-risk set such as `GodotObject`, `Node`, `Node2D`, `Label`,
     `Vector2`/`Vector3` value types, and only helper shapes already present on
     desktop and iOS.
   - Root JVM runtime and `:ios-runtime` depend on the common module and stop
     compiling their local copies for that slice.
   - Gate: `./gradlew jar`,
     `DEVELOPER_DIR=/Applications/Xcode.app/Contents/Developer ./gradlew compileKotlinIosArm64`,
     `scripts/check_ios_no_silent_stubs.py`, and an API diff proving the moved
     classes have identical public declarations.

4. Expand by audited shape families, not by class count.
   - Promote all methods using an already-implemented call shape together.
   - When a wrapper needs a missing iOS helper, add the C-shim tag/helper,
     Kotlin/Native actual, width-sensitive self-test row, and generator fixture
     before promoting the common method.
   - Gate each family with `scripts/check_wrapper_generator.py`,
     `python3 scripts/api_wrapper_generator_report.py --markdown docs/contributing/wrapper-generator-report.md`,
     `python3 scripts/api_wrapper_coverage.py --markdown docs/contributing/api-coverage.md`,
     JVM compile, iOS compile, and the iOS ObjectCalls/self-test matrix when the
     shape touches the C shim.

5. Delete the iOS generated-wrapper copies.
   - After a class is fully consumed from common wrappers, remove its iOS copy
     and any iOS-only generator openness/sugar exception.
   - `Node.createTween()` becomes whatever the common generator emits. If
     `SceneTree` still needs subclass-specific behavior, solve it as a
     generator policy/custom-section problem shared by all platforms, not by
     editing iOS-only source.
   - Gate: `scripts/ios_handwritten_report.py --markdown docs/internals/reference/ios-backend-handwritten.md`
     remains `0 STUB / 0 SUGAR`; iOS compile; focused demo smoke if the class is
     demo-visible.

6. Remove obsolete generator targets.
   - The generator should have one wrapper output target:
     `:kanama-common-api/src/commonMain/kotlin`. Platform codegen remains only
     for `ObjectCalls` actuals, platform runtime glue, and project-script KSP
     output.
   - Gate: a clean checkout regeneration produces no desktop/iOS wrapper drift;
     `rg "Generated from Godot docs" ios-runtime/src/iosMain/kotlin/net/multigesture/kanama/api`
     finds no generated wrapper copies.

7. Run release-grade validation before changing support claims.
   - Desktop: `./gradlew jar` and local desktop smoke.
   - Android: existing AAR/export smoke, including PanamaPort remap audit.
   - iOS: `compileKotlinIosArm64`, install/export path, and current physical
     device demo matrix for any wrapper family used by demos.
   - Docs: `mkdocs build --strict`.

## Generator Changes

`scripts/generate_api_wrapper.py` should become the single source of truth for
three outputs:

- Common wrapper classes under `:kanama-common-api/src/commonMain/kotlin`.
- Common `expect object ObjectCalls` declarations.
- Platform `actual object ObjectCalls` helper bodies or, at minimum, generated
  actual-helper fixtures that platform source must match.

Required generator behavior:

- Render wrapper classes once. Do not have separate desktop and iOS wrapper
  templates for method openness, signatures, KDoc, or class hierarchy.
- Collect every emitted `CallShape` in a deterministic registry:
  function name, Kotlin argument list, return type, Godot argument/return types,
  scalar width policy, ownership policy, and whether Variant fallback is used.
- Emit the `expect` contract from that registry.
- Emit JVM/Panama actual helpers from the same registry, preserving existing
  scratch-buffer optimizations only where the shape is marked synchronous and
  non-retained.
- Emit iOS actual helpers from the same registry only for audited type tags.
  Unknown iOS shapes must be skipped with a report entry rather than guessed.
- Keep custom sections for genuinely hand-shaped classes, but apply them before
  common emission so all platforms see the same class shape.
- Teach `check_wrapper_generator.py` to compare the common emitted wrappers and
  the `ObjectCalls` contract, not platform-specific copies.
- Keep name constants and KDoc generated once into common sources.

The generator report should distinguish:

- skipped because the method is a virtual callback;
- skipped because no platform has a policy yet;
- skipped because desktop/JVM has a policy but iOS lacks an audited actual;
- hand-shaped by explicit policy.

Only the third category should shrink as commonMain migration advances.

## `GodotReal` Decision

Recommendation: centralize `GodotReal` in the shared module during the wrapper
module setup, but do not make double-precision support a prerequisite for moving
the first wrapper slice.

Rationale:

- Value types are part of the common wrapper API. Keeping separate desktop and
  iOS `real_t` decisions after wrappers move would preserve a second form of
  drift.
- The root JVM build already generates `types/Real.kt` from `kanamaPrecision`
  with `real_t` and `GodotReal` layout helpers.
- iOS currently documents single-precision `real_t` handling in the C-shim path.
  It should consume the same `GodotReal` API from common sources, with an iOS
  actual/layout policy that remains single-precision until double-precision iOS
  templates are deliberately supported.

Lower-risk sequencing:

1. Move the `GodotReal` API shape into `:kanama-common-api`.
2. Keep the default value single precision on every platform.
3. Preserve the existing JVM `-PkanamaPrecision=double` behavior only for JVM
   until an iOS double-precision build can be compiled and device-tested.
4. Add a clear compile-time or build-property guard that rejects unsupported
   iOS double precision instead of silently emitting wrong struct layouts.

This lets task 07 centralize the type decision without blocking Phase 4.3 on a
full double-precision mobile validation matrix.

## Risks And Blast Radius

Main risks:

- Gradle publication shape changes. Downstream starter templates, release kits,
  store add-ons, and local project script compilation must still resolve the same
  public `net.multigesture.kanama` API.
- Package namespace collisions. The shared module and existing root/ios sources
  cannot both compile the same `net.multigesture.kanama.api` classes in one
  target.
- iOS helper gaps. A common wrapper method can compile but crash or corrupt data
  if its iOS actual uses the wrong ptrcall width or lifetime.
- Android remap fragility. Common wrappers must not grow imports that bypass the
  PanamaPort remap assumptions.
- Source-compatible but behavior-incompatible changes. Removing iOS-only
  exceptions may expose real subclass collision problems that need generator
  policy, not local edits.
- Documentation and support wording drift. Wrapper unification is an internal
  maintainability win; it does not by itself upgrade Android or iOS support
  claims.

Watch points:

- `java.lang.foreign.MemorySegment` remains the shared object handle type until
  a separate platform handle abstraction is designed. The iOS shim makes that
  source-compatible today; do not introduce additional FFM classes into common
  wrappers casually.
- Scalar Godot `float` method values are 64-bit ptrcall cells, while `real_t`
  struct components are governed by `GodotReal`. This rule must live in the
  call-shape registry so all actuals agree.
- Object ownership and Variant/container return destruction must stay in the
  actual backend helpers, not in generated common wrappers.
- Hand-written policy classes must be explicitly listed. A class blocked by an
  existing hand-written implementation should fail the generator report rather
  than silently remaining platform-local.

Validation gates by migration level:

- Source-set wiring: JVM and iOS compiles only.
- Contract generation: generator check and deterministic diff.
- First wrapper slice: JVM jar, iOS compile, no silent iOS stubs, public API
  declaration diff.
- New call shape: generator fixture, ABI audit, iOS self-test/ObjectCalls probe
  if C-shim code changes, and focused runtime smoke.
- Full deletion of copies: wrapper coverage/report refresh, handwritten report,
  desktop/Android/iOS smoke paths appropriate to touched classes.

## Implementation Exit Criteria

The follow-on implementation is done when all of these are true:

- Generated wrapper classes are emitted once into common sources and consumed by
  desktop, Android, and iOS.
- `ObjectCalls` is an `expect/actual` contract with JVM/Panama, Android
  PanamaPort-remapped JVM, and iOS C-shim actual implementations.
- `scripts/check_wrapper_generator.py` fails if a generated wrapper or
  `ObjectCalls` declaration is manually edited or diverges from the generator.
- There are no generated Godot API wrapper copies under `ios-runtime/src/iosMain`.
- iOS-specific wrapper openness/sugar exceptions such as `Node.createTween()` are
  removed or replaced by common generator policy.
- The iOS handwritten report remains `0 STUB / 0 SUGAR`; remaining handwritten
  entries are runtime/platform glue.
- `GodotReal` has one shared API and unsupported precision/platform
  combinations fail loudly.
- Desktop `./gradlew jar` passes.
- iOS `compileKotlinIosArm64` passes with the current Xcode/Godot baseline.
- Android package/export smoke still passes with PanamaPort remapping.
- `mkdocs build --strict` passes.
- A maintainer has reviewed and approved the design and staged migration plan
  before implementation begins.
