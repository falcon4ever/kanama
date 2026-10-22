# API Conventions

This page lists what a script author can rely on in the generated Godot API, the classes in
`net.multigesture.kanama.api` such as `Node`, `Input` and `StandardMaterial3D`. Each rule
names the generator code that produces it, so you can check the page against the code; the checks
that hold each rule are listed in
[Gates and Claims](../contributing/gates.md#api-convention-checks).

**How stable this is.** Kanama is pre-1.0. These rules are the direction for long-term stability,
not a hard freeze: a rule can still change before 1.0 when that serves scalability or a Godot
upgrade (Godot 4.8 is the next one). Every change to a public signature is announced in the
[CHANGELOG](https://github.com/falcon4ever/kanama/blob/main/CHANGELOG.md) as a source break, and
the build fails when one is not (see [Source Breaks](#source-breaks)).

**Scope.** The rules cover the API tree that desktop, Android and iOS compile from the same
source (`src/commonMain/kotlin/net/multigesture/kanama/api`). The Web backend has its own generated
surface; [Platform Parity](#13-platform-parity) lists where it follows the same rules and where
it differs. Not covered here: the [annotations](../game-dev/scripts.md) you write scripts with
(the reverse of rule 1 for your own scripts, a public `fun showMessage()` registered as Godot's
`show_message`, is in [Exposing Functions to Godot](../game-dev/scripts.md#exposing-functions-to-godot)),
the builtin value types' own methods (`Vector3.lerp`, `Basis.inverse`), and `@Tool` /
editor-only APIs. The source-break check reaches further than these rules: it also holds the
value types, the annotations, each platform's own classes and the Web facades to a snapshot (see
[Source Breaks](#source-breaks)).

| Area | Rule |
|---|---|
| [Names](#1-names) | Godot names, lowerCamel members, `Value` suffix on keywords |
| [Properties](#2-properties) | getter + setter = `var`, getter only = `val` |
| [Integers](#3-integers) | `int32` meta = `Int`, other `int` = `Long` |
| [Decimals](#4-decimals) | every decimal value `Double`, value-type and `Color` components included; packed bulk data keeps 32-bit storage |
| [Enums and Bitfields](#5-enums-and-bitfields) | typed value classes, frozen value names |
| [Nullability](#6-nullability) | `meta: "required"` returns non-null, other object returns nullable |
| [Ownership](#7-ownership) | `close()` / `use { }` releases a `RefCounted` early; a forgotten one is released after the garbage collector drops it |
| [Statics and Singletons](#8-statics-and-singletons) | statics on the companion, singletons are `object`s |
| [Defaults and Overloads](#9-defaults-and-overloads) | Godot defaults where Kotlin can express them |
| [Collections](#10-collections) | `List`, `Map<String, Any?>`, `ByteArray` |
| [Handles and Factories](#11-handles-and-factories) | `GodotHandle`, `fromHandle`, `create()` |
| [Names as Constants](#12-names-as-constants) | `X.Signals.<name>` |
| [Platform Parity](#13-platform-parity) | one tree for desktop, Android and iOS; Web separate |

Generator code below is in `scripts/generate_api_wrapper.py` unless another file is named.

## 1. Names

- **Classes** keep Godot's name. Godot's `Object` is `GodotObject` (`WRAPPER_CLASS_ALIASES` in
  `scripts/wrapper_model.py`), and global enums are renamed only when Godot's name would collide
  (see [Enums](#5-enums-and-bitfields)).
- **Methods** are Godot's `snake_case` in `lowerCamelCase`: `get_node_or_null` is
  `getNodeOrNull` (`camel_name` in `scripts/api_wrapper_candidates.py`, `method_function_name`). A
  leading underscore is dropped. `METHOD_NAME_OVERRIDES` renames the few methods whose Godot name
  Kanama or the JVM already uses: Godot's `close()` becomes `closeConnection()`, `closeArchive()`
  or `closeDock()` (`close()` is Kanama's release on every `RefCounted`, see
  [Ownership](#7-ownership)), and `wait()` becomes `waitBlocking()` (`wait()` is final on every JVM
  object).
- **Parameters** are `lowerCamelCase` too. A parameter named like a Kotlin keyword gets the suffix
  `Value` (`RESERVED_WORDS`, `arg_name`): Godot's `internal` is `internalValue`. A handful are
  renamed for readability (`PARAMETER_NAME_OVERRIDES`, the `Time` methods). Parameter names are
  part of the API: Kotlin callers can name arguments.
- **Properties** are the Godot property name in `lowerCamelCase` (`camel_name`). The exceptions
  are in `PROPERTY_NAME_OVERRIDES` and pair with renamed accessors: `Curve3D.closed` is
  `curveClosed` (`setCurveClosed`), `OccluderPolygon2D.closed` is `polygonClosed`.

```kotlin
val label = self.getNodeOrNull("Hud/Label")                        // get_node_or_null
self.addChild(child, internalValue = Node.InternalMode.FRONT)      // `internal` is a Kotlin keyword
peer.closeConnection()                                             // MultiplayerPeer.close()
```

## 2. Properties

A Godot property becomes a Kotlin property when its getter is generated (`render_property`):

- getter and setter of the same Kotlin type: `var`;
- getter only, or a setter whose type differs from the getter's: `val`;
- setter only: no property, the setter method alone.

The accessor methods stay callable (`getProcessMode()` / `setProcessMode(...)`). An indexed
property, one Godot backs with a shared accessor and an index, is a property too, with the index
bound: `BaseMaterial3D.albedoTexture` calls `getTexture` with the albedo index, `Light3D.lightEnergy`
calls `getParam`.

```kotlin
self.processMode = Node.ProcessMode.ALWAYS
material.albedoTexture = texture
light.lightEnergy = 2.0
```

## 3. Integers

Godot's `int` is `Int` when its `meta` is `int8`, `int16`, `int32`, `char32`, `uint8` or
`uint16`, and `Long` otherwise (`int64`, `uint32`, `uint64`, or no meta). The width is exact on
purpose: an `int32` return writes four bytes on the native ABI. Mapping:
`SIGNED_INT_POLICIES` / `UNSIGNED_INT_POLICIES` and `abi_kind` in `scripts/wrapper_model.py`.

Class integer constants that are not enum values stay `const val NAME: Long` on the companion
(`render_companion_constants`), under Godot's name.

```kotlin
val n: Int = self.getChildCount()
val id: Long = self.getInstanceId()
val ready: Long = Node.NOTIFICATION_READY   // a class constant: const val, Long
```

## 4. Decimals

- Every decimal value a script reads or writes is `Double` in every signature, on every platform,
  as in GDScript, where `float` is 64-bit:
    - a scalar `float` argument, return or property (`SCALAR_KOTLIN_TYPES` in
      `scripts/generate_api_wrapper.py`, `FLOAT_POLICIES` in `scripts/wrapper_model.py`): Godot
      passes it as a 64-bit double;
    - the components of the value types (`Vector2`, `Vector3`, `Vector4`, `Quaternion`, `Plane`,
      `Basis`, `Transform2D/3D`, `Rect2`, `AABB`, `Projection`) and of `Color`: constructors,
      properties, operators and methods take and return `Double`.
- **A value type stores its components at Godot's width**, exactly as Godot, GDScript and C# do:
  Godot's `real_t` for the vectors and transforms (float32 in the default build, float64 when
  Kanama is built with `-PkanamaPrecision=double` for a `precision=double` engine) and float32 for
  `Color`, in every build. A constructor or `copy` rounds each component to that width; a property
  widens the stored value back to `Double`. The storage type is the generated, internal
  `GodotRealStorage` (`Real.kt`, from `generateKanamaReal`).
- So Kotlin behaves like GDScript:
    - `node.position = v; node.position == v` is `true`: the marshalling layer moves the stored
      bits unchanged (`GodotRealSegment.readRaw`/`writeRaw` on desktop/Android, the iOS
      `ObjectCalls` packing; the Web bridge passes the widened value, which is exact).
    - `==` and `hashCode` compare the stored values with Godot's `==` (`-0.0 == 0.0`; `NaN`
      equals itself only to keep the JVM `equals` contract).
    - `toString()` prints what GDScript's `str(v)` prints: `(0.1, 0.2)`, `(1.0, 2.0, 3.0)`,
      Quaternion `(0, 0, 0, 1)`, Color `(1.0, 0.5, 0.0, 1.0)` (at most four decimals), Plane
      `[N: (0.0, 1.0, 0.0), D: 0]`, Basis/Transform columns `[X: …, Y: …, Z: …, O: …]`.
    - Basic arithmetic gives Godot's result bit for bit: `+`, `-`, `*`, `/` and unary `-` are
      computed on the stored operands and rounded to the storage width on store (one float32
      operation on float32 inputs equals the Double operation rounded to float32), and a scalar
      operand is narrowed to `real_t` first, as `Vector2 * float` does in Godot. `dot`,
      `length`, `lengthSquared`, `distanceTo`, `cross`, `normalized`, `Quaternion * Quaternion`,
      `Basis(quaternion)`, `Basis * Vector3`, `Transform3D * Vector3`, `Plane.distanceTo`,
      `Rect2.area` and `AABB.volume` follow Godot's `real_t` formulas in Godot's operand order
      (`types/shared/RealMath.kt`, one source for the native and the Web types). Methods computed
      by the engine (`lerp`, `rotated`, `slerp`, `angle`, ...) return the engine's stored
      result. Every operator Godot declares (`Basis * Basis`,
      `Transform3D * Plane`, `Vector2i % Vector2i`, `Color / Color`, `2.0 * v`, ...) and the short
      component-wise methods (`abs`, `floor`, `round`, `sign`, `min`/`max`, `clampf`,
      `directionTo`, integer-vector `length`, `Color.toHtml`, ...) are Kotlin with Godot's formulas
      (`types/BuiltinFormulas.kt`, `types/shared/BuiltinMath.kt`). Transcendental results computed in Kotlin on Web (`rotated`, `slerp`, ...) are
      rounded to `real_t` but not guaranteed to the bit.
- **The same caveat as GDScript:** `v.x = 0.1` (or `Vector2(0.1, 0.2)`) stores the float32
  nearest to 0.1, so `v.x == 0.1` is `false` in a float32 build, while `v == Vector2(0.1, 0.2)`
  is `true` (both sides are stored). Compare decimals with `isEqualApprox`; values exact in
  float32 (`0.5`, `1.25`, small integers) compare equal either way.
- Integer components construct without boxing, as in GDScript: `Vector3(0, 1, 0)`,
  `Vector3(speed, 0, 0)`, `Color(1, 1, 1)`, `Color(1, 1, 1, 0.72)`, `Plane(Vector3.UP, 0)`.
  `Vector2` and `Vector3` have an overload for every Int/Double mix, `Color` adds
  `(Int, Int, Int, Double)`, and `Vector4`, `Quaternion` and `Plane(x, y, z, d)` take all-`Int`
  or all-`Double`; each call matches exactly one overload. The operators take `Int`, `Long`,
  `Double` and `Float` scalars. There is no `Number` overload: it boxed every argument, a hidden
  allocation in a per-frame loop.
- Packed bulk data keeps compact 32-bit storage: `PackedFloat32Array` is `List<Float>`,
  `PackedFloat64Array` is `List<Double>`. The elements of `PackedVector2/3/4Array` and
  `PackedColorArray` (and mesh arrays) are the ordinary value types.
- **Double precision** (`-PkanamaPrecision=double`, desktop only) compiles, but a
  `precision=double` engine is not supported: two marshalling sizes assume single precision (the
  hard-coded 24-byte Variant size and the `Rect2` cell size in `ObjectCalls`). See
  [Limitations](limitations.md#desktop).

### Operators, Methods and Edge Cases

- **Operators** are Kotlin operators with GDScript's meaning: `a + b`, `v * 2.0`, `2.0 * v`, `-v`,
  `transform * point`, `basis * otherBasis`, `quaternion * vector`, `Vector2i(3, 4) * 2`,
  `Vector2i(3, 4) * 0.5` (a `Vector2`, as in GDScript), `color * 0.5`, `transform * points` (a
  `List<Vector3>`), and `<` / `>` on vectors (component by component, as Godot compares).
  `point * transform` is Godot's inverse transform (`xform_inv`). `2.0 * v` and
  `points * transform` are extension operators: import `net.multigesture.kanama.types.times`
  (the IDE offers it) or `net.multigesture.kanama.types.*`.
- **Methods** are camelCase (`v.directionTo(target)`, `v.snapped(step)`, `basis.getEuler()`,
  `Projection.createPerspective(...)`, `Color.fromHsv(h, s, v)`); `aabb.intersectsRay(from, dir)`
  is `null` where GDScript returns `null`. Getters keep `get` (`rect.getCenter()`,
  `transform.getRotation()`), except `Transform2D.get_origin()`, which is the `origin` property.
  Constants are the companion values Godot declares (`Vector2i.LEFT`, `Vector3.MODEL_FRONT`,
  `Basis.FLIP_X`, `Plane.PLANE_XY`, `Vector3.INF`, every named color) and the enums
  (`Vector3.Axis.X`).
- **Where a method runs.** Operators and every method whose Godot implementation is plain
  arithmetic (`abs`, `floor`, `round`, `min`/`max`, `clampf`, `lerp`, `moveToward`,
  `slide`/`bounce`/`reflect`, `project`, `limitLength`, `Rect2`/`Rect2i`/`AABB`
  `hasPoint`/`intersects`/`encloses`/`merge`/`grow*`, `Plane.project`,
  `Transform2D.inverse`/`translated`, `Color.lerp`, ...) run in Kotlin with Godot's formulas at
  Godot's width, so their results are Godot's to the bit and cost no engine call. Every other method
  (`angle`, `rotated`, `slerp`, `Basis.getEuler`, `Color.lightened`, ...) is computed by the engine
  through an allocation-free call, about as fast as the same call from GDScript.

Where Kotlin and GDScript differ:

- Integer division or `%` by zero (`Vector2i(1, 1) / 0`) throws `ArithmeticException`; GDScript
  reports a division-by-zero error.
- Godot's debug build checks some arguments and returns a default instead of computing (`slide`,
  `bounce`, `reflect` with a non-normalized normal; `quaternion * vector` and `vector * quaternion`
  with a non-normalized quaternion). These are Kotlin math and compute the formula without the
  check, which is what an exported (release) game does. Engine-computed methods (`slerp`,
  `rotated`, ...) report the same error as GDScript, and so do `Basis.inverse` of a singular basis,
  `Basis.getRotationQuaternion` of a basis that is not a rotation and `Transform3D.affineInverse` of
  a singular one: they compute in Kotlin and hand exactly the input Godot's check rejects to the
  engine.
- Comparing vectors with a NaN component: Godot answers `false` to all of `<`, `<=`, `>` and `>=`.
  Kotlin's comparisons go through one `compareTo`, which sorts a NaN component last, so `<` and
  `<=` are `false` but `>` and `>=` are `true`.
- A float that becomes an integer (`Color.toHtml` on a channel of NaN, ±INF or beyond the `int`
  range): C++ leaves that conversion undefined, so Godot's own result differs by CPU (x86 and
  arm64 disagree). Kotlin's conversion is defined (NaN gives 0, out-of-range values saturate),
  which matches Godot on arm64 (Apple silicon, phones). For finite channels every platform agrees.

The Web value types are the same classes; how their engine-computed methods run there is in
[Web Internals](../contributing/backends/web.md#the-web-script-environment).

## 5. Enums and Bitfields

Every Godot enum and bitfield is a `@JvmInline value class` wrapping the `Long` Godot uses
(`godot_enum_model.render_enum_class`, `enum_type_ref`, `kotlin_type`). Every parameter, return and
property Godot types as `enum::X` or `bitfield::X` uses it.

- **Placement.** A class enum nests in its class (`Node.ProcessMode`, `BaseMaterial3D.Flags`); a
  global (`@GlobalScope`) enum is top-level (`Key`, `MouseButton`). A global enum is renamed only
  when Godot's name would collide: `Error` is `GodotError`, `PropertyHint` is `GodotPropertyHint`,
  and `Variant.Type` / `Variant.Operator` are `VariantType` / `VariantOperator`
  (`GLOBAL_ENUM_RENAMES` in `scripts/godot_enum_model.py`).
- **Value names** drop the enum's common prefix and keep Godot's SCREAMING_CASE, the rule Godot's
  C# bindings use (`determine_prefix`, `enum_value_name`): `PROCESS_MODE_ALWAYS` is
  `Node.ProcessMode.ALWAYS`. A value that would start with a digit keeps a word (`Key.KEY_0`); an
  enum without a common prefix keeps full names (`GodotError.ERR_FILE_NOT_FOUND`).
- **Frozen prefixes.** Each enum's prefix is computed once and written to
  `scripts/enum_prefix_lock.json`; the generator never recomputes it (`locked_prefixes`). A value
  a later Godot adds that does not fit keeps its full Godot name, and the existing names do not
  change.
- **Raw values.** `.value` is the `Long`; the public constructor builds any value, including one
  this Kanama does not name, such as a value a newer Godot adds: `Node.ProcessMode(3L)`. `toString()` prints
  `ProcessMode(value=3)`.
- **Bitfields** have `or`, `and`, `xor`, `inv()` and `in` (`contains`); the result keeps the type.
  Where Godot names no zero value, `X(0L)` is the empty set.
- **Defaults** are the named value equal to Godot's default (`kotlin_enum_default_expression`).
- **Dynamic calls.** `call`, `set`, `callDeferred`, `emitSignal` and Array / Dictionary elements
  accept the typed values: every enum implements `GodotEnumValue`, which the Variant encoders pass
  as the integer it stands for. What a dynamic path returns (`get`, `call`, Variant returns) is a
  `Long`; wrap it with `Node.ProcessMode(raw as Long)` when you need the type.
- **Script members** typed with a Godot enum (`@Export` properties, registered functions,
  `@Signal`, `@Rpc`, engine virtuals) are covered in [Exports and Resources](../game-dev/properties-resources.md#godot-enums-and-bitfields).
- **Export metadata.** An `@Export` of a Godot enum follows the style of the engine's own
  properties (what `Node.process_mode` shows), not GDScript's: an `int` with `PROPERTY_HINT_ENUM`,
  or `PROPERTY_HINT_FLAGS` for a bitfield, and a hint string of the value names as Godot
  capitalizes them, with the values spelled out (`Inherit:0,Pausable:1,When Paused:2,Always:3,Disabled:4`,
  `Messages:1,Messages Physics:2,Messages All:3`). GDScript's `@export var mode: Node.ProcessMode`
  uses the full names (`Process Mode Inherit:0,...`) and exports a bitfield as a `PROPERTY_HINT_ENUM`
  dropdown of single flags; the stored `int` is the same, so scenes are interchangeable. Whether a
  type is a bitfield is Godot's call: `BaseMaterial3D.Flags` is an enum of flag indices and exports
  as a dropdown. As in GDScript, `class_name` is Godot's qualified name (`Node.ProcessMode`) and
  usage includes `PROPERTY_USAGE_CLASS_IS_ENUM`; a bitfield uses Godot's native
  `PROPERTY_USAGE_CLASS_IS_BITFIELD`, which GDScript types as a plain `int`, so assigning a
  combination of flags does not warn. The default may be a value, the raw form
  (`Node.ProcessMode(3L)`) or an `or` of bitfield values, and is constant-folded. A
  `List<Node.ProcessMode>` is a typed int array (`"2/2:<hint>"`). A value the hint does not list
  (a newer Godot's, an extension's) is kept, not clamped. On Web the generated GDScript proxy
  declares the property with `@export_custom(<hint>, "<hint string>")`, which cannot set
  `class_name`, so Web reports the hint and hint string only.
- **Export hint strings.** The processor builds each `@Export*` hint string as GDScript's parser
  does, so `get_property_list()` reports the same hint and hint string for a Kotlin script and its
  GDScript twin. `@ExportRange` writes its
  extra flags in GDScript's order: `or_greater`, `or_less`, `exp`, `radians_as_degrees`,
  `degrees`, `prefer_slider`, `hide_control`, `suffix:<unit>`. Build errors follow GDScript's
  argument rules: an empty argument, or one with a comma (except placeholder text), a malformed
  `@ExportFlags` value, an `@ExportNodePath` class that is not a `Node` (an engine class or a
  `@GlobalClass` script), a hint on a type it does not take, two hints on one property.

- **Engine virtuals** whose parameters or return Godot types as an enum take the value class
  (`@OverrideVirtual fun _get_shader_mode(): Shader.Mode`); a `Long` there fails the build and
  names the type.
- Kanama's runtime also has `net.multigesture.kanama.binding.runtime.VariantType`, an internal
  marshalling enum; scripts use the `net.multigesture.kanama.api` one.

```kotlin
self.setProcessMode(Node.ProcessMode.ALWAYS)
val flags = Control.SizeFlags.EXPAND or Control.SizeFlags.FILL
if (Control.SizeFlags.FILL in flags) { /* ... */ }
val err: GodotError = ResourceSaver.save(scene, path)
node.set("process_mode", Node.ProcessMode.ALWAYS)   // dynamic call: typed value accepted
```

Moving 0.4 code to the typed enums: [Enum Constant Migration](generated/enum-migration.md).

## 6. Nullability

**Object returns** are nullable (`X?`): Godot promises nothing about them. The exception is a
return Godot marks `meta: "required"`: it is non-null, and a null from the engine throws
`IllegalStateException("Godot returned null from required <Class>.<method>")`
(`kotlin_return_type`, `is_required_return`, `requireGodotReturn` in
`src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/RequiredReturns.kt`). A `Callable`
return is `GodotCallable?`.

```kotlin
val tree = self.getTree() ?: return                  // not marked required: SceneTree?
val sameTree = self.tree                             // non-null accessor: throws outside the tree
val root: Window = tree.getRoot()                    // required: Window, not Window?
tweener.setTrans(Tween.TransitionType.SINE).setEase(Tween.EaseType.OUT)   // required fluent returns chain with `.`
```

**Object parameters** follow one decision, in this order (`object_param_is_nullable`):

1. `meta: "required"`: non-null, always.
2. `Resource` / `RefCounted`-derived: nullable (pass `null` to clear).
3. Listed in `NULLABLE_OBJECT_PARAM_OVERRIDES`, each entry citing the Godot source that accepts
   null: nullable (`Node.setOwner(null)`).
4. Otherwise non-null.

```kotlin
meshInstance.setMesh(null)    // Resource-derived: nullable
child.setOwner(null)          // listed override
parent.addChild(child)        // Node, not required-marked, not listed: non-null
```

`X.fromHandle(handle)` returns `X?`, except `Resource.fromHandle`, which is non-null because a
`@ScriptClass(attachTo = "Resource")` script uses it as its constructor reference
(`NON_NULL_FROM_HANDLE_CLASSES`).

## 7. Ownership

The full statement is [Resource Ownership](../game-dev/godot-api.md#resource-ownership). In short:

- A `RefCounted`-typed return of a typed wrapper (`getMesh()`, `X.create()`, `ResourceLoader.load…`,
  a `Tweener`) is **owned**: it carries a reference that is yours. `close()` it or `use { }` it.
- So is **every element of a returned typed `Array` of `RefCounted`** (`getMaterials()`,
  `getProcessedTweens()`, ...): the Array is destroyed right after the decode, so each element is
  retained first and its wrapper owns that reference (desktop, Android and iOS). Close
  each element. An element of an untyped `Array[Object]` that is `RefCounted` comes back as a
  `RefCounted` wrapper so it can be closed. Array and Dictionary elements read through a dynamic
  path (a Variant) stay borrowed.
- `close()` releases your reference; it destroys the object only if that was the last one.
  Calling a member on a closed wrapper throws `IllegalStateException` instead of reaching freed
  memory (`emits_receiver_guard`, `checkOpen()` in the hand root `RefCounted`).
- A fluent method that returns its own receiver (`tweener.setTrans(...)`) returns `this`, not a
  second owned wrapper (`self_return_collapse_wrapper`), so a chain needs no extra `close()`.
- Values read through a dynamic path (`call`, `get`, `getMeta`, ...) are borrowed views: never
  close them. Nodes and plain `Object`s have no `close()`; free nodes with `queueFree()`.
- A lambda signal connection is released by Godot when the receiver or the emitter is freed, a
  one-shot connection fires, or it is disconnected; `SignalConnection.close()` is only needed to
  disconnect early, on every platform. See
  [Signals](../game-dev/signals.md).
- An exception thrown from a script callback is reported as a Godot script error with the Kotlin
  file and line, and the call returns `null` (desktop, Android and iOS). Containment
  covers lifecycle callbacks, public functions, signal lambdas, property accessors, `MainThread`
  tasks and coroutines started with `launch`; the full stack trace goes to stderr. The reported
  frame is the top frame of game code (Kanama, generated, Kotlin and JDK frames are skipped), as a
  `res://` path when the file is in the project, else the bare file name. A wrapper of a freed
  object handed to Godot as a value (an export, a return, a Variant argument) is silently `null`,
  as in GDScript. On Web the same
  report reaches the Godot log, but the production build cannot name the Kotlin line (it names the
  containment site, see [Web internals](../contributing/backends/web.md)).

- **Close to release early.** A forgotten `close()` is a late release, not a leak, on desktop,
  Android 13+ and iOS: an owned wrapper registers a cleanup that holds only its handle, and once
  the garbage collector drops the wrapper the reference is released on the main thread at the next
  frame. On Web the browser's collector drives it and the bridge drains it each frame. `close()` cancels that cleanup, so a reference is released once.
  Borrowed views register nothing. At shutdown a collection runs before Godot's leak report. The
  project setting `kanama/debug/log_gc_releases` logs each such release once per creation site.
- Ownership is fixed where the wrapper is built: inside the runtime a `RefCounted` wrapper class
  has no `wrap`, only `wrapOwned` (a returned or constructing `+1`) and `wrapBorrowed` (a view),
  so every generated and hand-written site states which one it builds. The `from*` downcasts take
  a `+1` of their own (`RefCounted.retained`), so a downcast kept in a field keeps the object alive
  whatever happens to the wrapper it came from. `fromHandle` and wrapper constructors stay borrowed
  views; `close()` on one releases nothing (debug builds warn).
- A Kotlin script object of a `KanamaScript` on a `RefCounted` owner keeps its owner alive, as in
  GDScript: the instance holds a `+1` and its native link is strong above count 1 and weak at 1
  (C#'s `CSharpInstance` model; desktop, Android 13+ and iOS). For a plain script class the GC
  fallback does not release forgotten wrappers of its owner until the script is detached or the
  game shuts down. See
  [Keeping a Resource Through Its Script](../game-dev/properties-resources.md#keeping-a-resource-through-its-script).
- Wrappers made on any thread get the GC fallback; the release runs on the main thread, after the
  wrapper's last call (`ObjectCalls.keepReachable`).
- **Exported resource slots** are an allowlist, because a resource-typed read is
  ownership-managed (retained while the script holds it, released on cleanup): `PackedScene`,
  `Texture`, `Texture2D`, `NoiseTexture2D`, `Material`, `ShaderMaterial`, `Curve`,
  `FastNoiseLite`, `ButtonGroup`, `LightmapGIData`, `AudioStream`, `Mesh`, `Shape2D`, `Shape3D`,
  `Font`, `Animation`, `StyleBox`, and any `@GlobalClass` custom resource script.

### The Full Ownership Table

| Category | What it covers | What to do |
|---|---|---|
| **Owned** | `X.create()`, `ResourceLoader.load…`, every `RefCounted`-typed method return **including plain getters**, every element of a returned typed `Array` of `RefCounted` (`getMaterials()`, `actionGetEvents()`, `getProcessedTweens()`, …), every `RefCounted` element of an `Array`/`Dictionary` that `call(...)` (or `callv`, `callDeferred`, `callThreadSafe`, `ClassDB.classCallStatic`, `GodotCallable.call`/`callv`) returns, the `from*` downcasts (`Mesh.fromObject(...)`, `ArrayMesh.fromResource(...)`), the checked casts to a `RefCounted` class (`res.cast<Texture2D>()`, `castOrNull`), and `@Export` reads of resource-typed fields and collections | `close()` it, or `use { }`, to release it early; for a list, each element (`list.forEach { it.close() }`). Forgotten, it is released after the GC drops the wrapper (on Web, the browser's) |
| **Borrowed view** | A wrapper you build over a raw handle you already have: `Resource.fromHandle(...)`, a wrapper constructor (`Mesh(other.handle)`), a script's own `self`, and the `RefCounted` elements of a container (`Array`/`Dictionary`) that arrives as an argument of a signal lambda or a script method: the caller's Variant holds them for the call, so do not `close()` them or keep them past it | Keep the wrapper you got the handle from while you use the view: the view takes no reference. `close()` on it releases nothing (debug builds warn) |
| **Engine-owned, live** | A running `Tween` (from `createTween()` or an element of `getProcessedTweens()`), anything living in the scene tree | To stop it use the Godot lifecycle (`kill()`, `queueFree()`): `close()` never stops it. Your wrapper of a tween is still an owned `+1`; close it when you stop using that wrapper |
| **Nodes and plain `Object`s** | Anything not `RefCounted`: no refcount exists, and `GodotObject` has no `close()` | `Node.queueFree()` |

- **Tweens.** The `SceneTree` holds its own reference to every tween it processes, so a tween runs
  until it finishes or is killed whatever your wrappers do. `close()` on a tween wrapper releases
  only your reference; it never stops the tween, and a closed wrapper cannot be called again. So:
  `kill()` to stop a tween; `close()` (after `kill()`, or once you no longer call it) to release
  your wrapper; and for the elements of `SceneTree.getProcessedTweens()`, close each one when you
  are done reading it, which does not affect the tweens.
- **Sinks.** Handing an owned wrapper to a sink (`setMesh`, `setStream`, `ResourceSaver.save`) does
  not move it into the engine-owned row: the sink takes its own reference and yours is still yours
  to close. `ResourceSaver.save` does not take ownership of the scene, so `close()` after it is
  correct.
- **`@Export` reads.** When the engine sets a property (a scene or `.tres` loads, the inspector,
  `set("prop", value)`), each resource value it holds, alone or in an `Array` or `Dictionary`, is
  kept alive the way GDScript keeps it. The runtime takes one reference per value for the property
  and releases it when the engine sets the property again or when Godot frees the script instance,
  also when the garbage collector already dropped the script object. The wrapper the Kotlin field
  holds has a reference of its own, so a copy you keep elsewhere (`cached = res`) stays valid after
  the property changes. Assigning the property from Kotlin, or removing an element from a
  `MutableList` property, does not release the runtime's reference to the old value: it is
  released at the next engine set of that property or when the instance is freed. A mutable script
  property releases the previous wrapper before accepting a new value from Godot; this covers
  `PackedScene?`, `Texture2D?` and `List<Texture2D>` exports.
- **Received objects.** A Godot object a script receives (a signal or callback argument, a lookup, a
  call's result) is one value per object, valid while the object lives, on every platform. A value
  stays valid after the script that obtained it is freed: a node another script stored in a
  `companion object`, or one a top-level initializer made, keeps working.

## 8. Statics and Singletons

- A Godot **static method** is a member of the class's companion: `Image.create(...)`. These
  members carry no `@JvmStatic` (it only matters to Java callers).
- An engine **singleton** is a Kotlin `object` whose members carry `@JvmStatic`:
  `Input.isActionPressed("jump")`, `Time.getTicksMsec()` (`render_draft`, singleton branch;
  the singleton list is Godot's, `load_api_singletons` in `scripts/wrapper_model.py`). Its
  `fromHandle` returns the object itself.

```kotlin
val image = Image.create(64, 64, false, Image.Format.RGBA8)
if (Input.isActionPressed("jump")) { /* ... */ }
```

**`GD`** holds Godot's utility functions, one member per function (all 114), generated like the
classes and the same on desktop, Android and iOS:

- Names are Godot's in camelCase (`deg_to_rad` is `degToRad`), except `printErr`, `printS`,
  `printT`, `printRaw`, `typeOf` (`typeof` is a Kotlin keyword) and `isNaN`. Parameter names are
  Godot's. `float` is `Double`, `int` is `Long`, a `Variant` is `Any?`.
- The generic `abs`, `sign`, `floor`, `ceil`, `round`, `lerp`, `clamp`, `wrap`, `snapped`, `max` and
  `min` have typed overloads (`Double` for all; `Long` and `Int` where Godot has an int form: `abs`,
  `sign`, `max`, `min`, `clamp`, `wrap`). They call the typed functions (`maxf`, `wrapi`, ...),
  except `clamp`, which is GDScript's `clamp` and runs in Kotlin: it sets the value to `min` if it
  is less, then to `max` if it is greater, so `GD.clamp(0.0, 3.0, 1.0)` is `1.0` as in GDScript
  (`clampf` and `clampi` keep Godot's typed result, `3.0`, when `min` is greater than `max`).
  Vectors, mixed types and three or more values take the `Any?` form, which returns what Godot
  returns (`GD.abs(Vector3(-1.0, 2.0, -3.0))` is a `Vector3`, as an `Any?`).
- `print`, `printErr`, `printS`, `printT`, `printRaw`, `printRich`, `printVerbose`, `pushError`,
  `pushWarning` and `str` take any number of values. Godot formats `null`, strings, booleans and
  numbers itself; any other value is passed as its `toString()`.
- Every other `Any?` parameter (`typeOf`, `hash`, `isSame`, `varToStr`, `varToBytes`, `weakref`,
  `typeConvert`, the `Any?` forms of `max`, `lerp`, `clamp`, ...) needs a value Godot has a Variant
  for: `null`, `Boolean`, the numbers, `String`, `NodePath`, the value types, `RID`, a
  `GodotObject`, a `ByteArray`, or a `List` or `Map` of those. Any other Kotlin object throws
  (`Unsupported Variant value type` on desktop, `unsupported Variant argument type` on iOS);
  Kanama does not turn it into a string behind your back.
- `isInstanceValid` takes a `GodotObject?` and answers from the instance id the wrapper captured,
  so it is safe on a wrapper whose object was freed. `isInstanceIdValid` takes a raw id.
- The random functions use Godot's one global generator on every platform, like GDScript and C#,
  so `seed()` from GDScript or Kotlin gives the same sequence. For strict determinism use a
  `RandomNumberGenerator` with its own seed: the global generator is shared with anything else
  that draws from it.

**`Mathf`** is the C#-style math object, written once for every platform: `lerp`, `clamp`, `wrap`,
`snapped`, `moveToward`, `smoothStep`, `pingPong`, `degToRad`, `PI`, `TAU`, ... Where Godot's
function is plain arithmetic, `Mathf` runs Godot's formula in Kotlin with no engine call and returns
the same double as GDScript, bit for bit; the transcendental functions (`sin`, `pow`, `exp`, ...)
and the float-to-int conversions (`roundToInt`, `floorToInt`) call `GD`. Each member also has a
`Float` overload that computes in `Double`.

## 9. Defaults and Overloads

- A Godot default argument is a Kotlin default argument when Kotlin can express it
  (`kotlin_default_expression`, `kotlin_enum_default_expression`; a few exact composite defaults
  in `KOTLIN_DEFAULT_EXPRESSION_OVERRIDES`, such as `Node3D.lookAt(target, up = Vector3.UP)`).
- A default the generator cannot express is a **required** parameter:
  `CanvasItem.drawTexture(texture, position, modulate)` needs the `Color` Godot defaults to white.
  Adding such a default later is not a break. Every such case is listed under "Godot Defaults Not
  Expressed" in the [generator report](generated/wrapper-generator-report.md#godot-defaults-not-expressed)
  (`parameter_default_expression` records them), so a new required parameter is visible in a
  pull request.
- An `expect` declaration (the seams the shared tree names, such as `GodotSignal`) carries no
  default argument; it has overloads instead (`signal.connect(target, method)` and
  `connect(target, method, flags)`), because the Android build compiles without the `expect` file.
- Godot has no overloads. Kanama adds a few: a `String` overload beside each `NodePath` lookup on
  `Node` (`getNodeOrNull("Hud/Label")`) and on `GodotObject` (`setIndexed`, `getIndexed`).
  Godot's vararg methods are Kotlin `vararg` (`call(method, vararg args)`, `rpc`, `emitSignal`).

## 10. Collections

| Godot type | Kotlin type |
|---|---|
| `Array` | `List<Any?>` |
| `Array[T]` (typed) | `List<T>`, for example `List<Node3D>`, `List<String>` |
| `Dictionary` | `Map<String, Any?>` (String keys) |
| `PackedStringArray` | `List<String>` |
| `PackedByteArray` | `ByteArray` |
| `PackedInt32Array` / `PackedInt64Array` | `List<Int>` / `List<Long>` |
| `PackedFloat32Array` / `PackedFloat64Array` | `List<Float>` / `List<Double>` |
| `PackedVector2Array`, `PackedVector3Array`, `PackedVector4Array`, `PackedColorArray` | `List<Vector2>`, `List<Vector3>`, `List<Vector4>`, `List<Color>` |
| `String`, `StringName` | `String` |
| `NodePath` | `NodePath` (plus `String` overloads on `Node`) |
| `Callable` | `GodotCallable(target, method)` |
| `Signal` | `GodotSignal` |
| `Variant` | `Any?` |

Godot's own methods of the types Kotlin represents itself are generated too: String's
and PackedByteArray's as extension functions on `String` / `ByteArray` in
`net.multigesture.kanama.builtins` (a name kotlin-stdlib or `java.lang.String` already has for the
receiver gets a `godot` prefix: `godotFormat`, `godotSplit`), NodePath's as `NodePath` members, Callable's and Signal's as `GodotCallable` /
`GodotSignal` members; see [Strings, Node Paths and Bytes](../game-dev/godot-api.md#strings-node-paths-and-bytes).

A `List<String>` script property exports as `Array[String]` and reads back as Kotlin strings,
including a scene-authored `PackedStringArray` value.

### String, NodePath, ByteArray and Callable Methods

- **Godot's semantics.** Each String function runs Godot's implementation in the engine, so it
  answers what GDScript answers: positions and lengths (`godotFind`, `substr`, `unicodeAt`, `left`,
  `godotLength`) count Unicode code points like GDScript, where Kotlin's `indexOf` and `length`
  count UTF-16 units (they differ only outside the Basic Multilingual Plane, such as emoji). The
  price is one engine call per function, which converts the text both ways, so in a per-frame loop
  over many strings Kotlin's own functions are cheaper. Text crosses as UTF-8, so a lone UTF-16 surrogate arrives as
  `?` and an embedded NUL ends the text. More examples: `"res://levels".pathJoin("one.tscn")`,
  `String.num(3.14159, 2)` (`"3.14"`), `"7".padZeros(3)`, `text.isValidFloat()`,
  `"{0} has {1} HP".godotFormat(listOf("Bob", 3))`.
- **The `godot` prefix.** A star import outranks Kotlin's default imports, so importing the package
  must never change an existing Kotlin call: a Godot method whose camelCase name kotlin-stdlib or
  `java.lang.String` already has for `String` is generated as `godot` + the name: `godotLength()`,
  `godotFind(...)`, `godotCount(...)`, `godotSplit(...)`, `godotFormat(...)`, `godotIndent(...)`,
  `godotCapitalize()`, `godotHexToInt()`, `godotToInt()` and `godotToFloat()` (the last two parse
  leniently, like GDScript: `"12abc"` is 12). The methods Kotlin answers
  identically are not repeated: `isEmpty()`, `contains(...)`, `startsWith(...)` (`begins_with`),
  `endsWith(...)`, `replace(...)`, `repeat(n)`, `parts.joinToString(sep)` (`join`) and
  `removePrefix`/`removeSuffix` (`trim_prefix`/`trim_suffix`).
- **`NodePath`** members are computed in Kotlin by Godot's own parse (ported):
  `NodePath("Arm/Hand:position:x").getName(1)` is `"Hand"`, `getSubname(1)` is `"x"`, plus
  `getNameCount()`, `getConcatenatedNames()`, `getConcatenatedSubnames()`, `isAbsolute()`,
  `isEmpty()`, `slice(begin, end)` and `getAsPropertyPath()`; `hash()` runs in the engine. As in
  Godot, an invalid path (`"a::b"`) is the empty NodePath and an index out of range answers `""`,
  each with Godot's error printed. A `NodePath` keeps only its text, so the parse happens when a
  member first needs it (the last parsed path is remembered): an invalid path's error prints then,
  where GDScript prints it when the NodePath is built.
- **`ByteArray`** gets the PackedByteArray methods Kotlin arrays lack: `decodeU8` ... `decodeS64`,
  `decodeHalf`/`decodeFloat`/`decodeDouble`, the `encode*` twins, `bswap16/32/64`, `hexEncode()`,
  `toInt32Array()` ... `toColorArray()` (Godot's byte codecs, ported to Kotlin: little-endian,
  Godot's half-float conversion), and, run by the engine, `getStringFromUtf8()`/`Utf16`/`Utf32`/
  `Ascii`/`Wchar`, `compress(mode)`, `decompress(size, mode)`, `decompressDynamic(max, mode)`,
  `decodeVar(offset)` and `decodeVarSize`/`hasEncodedVar`. Where Godot prints an error and returns
  0 or an empty array, the Kotlin function throws: `IndexOutOfBoundsException` for an offset
  outside the array, `IllegalArgumentException` for a `to*Array()` of a size that is not a multiple
  of the element and for a `bswap*` count past the end. `encode_var` writes into the array:
  `GD.varToBytes(value).copyInto(bytes, offset)` is the same encoding. The other packed arrays'
  `to_byte_array` is `toByteArray()` on their list types (`listOf(1, 2).toByteArray()`).
- **`GodotCallable(target, "method")`** has Godot's Callable methods for that object + method
  form: `call(...)`, `callv(list)`, `callDeferred(...)`, `isValid()`, `getObject()`,
  `getObjectId()`, `getArgumentCount()`, `rpc(...)` and `rpcId(peer, ...)`; each is the target's
  Object call that Godot's Callable makes (`rpc` on a target that is not a `Node` is a call error
  in both). `getObjectId()` answers the id after the target is freed, as Godot's does. It carries no
  bound arguments, so `bind`/`unbind` and the bound-argument queries are not offered. A
  `GodotSignal` (`node.signal("name")`, or a typed signal's handle) adds `isConnected(target,
  "method")`, `getConnections()`, `hasConnections()`, `getObject()` and `getObjectId()` to
  `connect`/`disconnect`/`emit`.

The table is `SCALAR_KOTLIN_TYPES` in `scripts/generate_api_wrapper.py`. A method whose
combination of types Kanama cannot pass is left out rather than generated with a wider type
(`CALL_SHAPES` in `scripts/api_wrapper_candidates.py`); the
[Wrapper Generator Report](generated/wrapper-generator-report.md) lists each one with the reason.

```kotlin
val bodies: List<Node3D> = area.getOverlappingBodies()
val groups: List<String> = self.getGroups()
```

## 11. Handles and Factories

- `GodotHandle` is the one identity type game code passes around. Every generated class has a
  public constructor from it, but that constructor does not check the class:
  `CharacterBody3D(body.handle)` calls `CharacterBody3D` methods on whatever `body` really is.
- **Checked casts** re-type a wrapper safely: `body.castOrNull<CharacterBody3D>()` asks
  Godot (`Object.is_class`) and returns `null` when the object is another class, `cast<T>()`
  throws a `ClassCastException`, and `Node.requireAs<T>(path)` / `getNodeAs<T>(path)` check the
  node they find. The untyped `requireAs(path, ::Label)` and `getAsOrNull(path, ::Label)` do not
  check the class. They ask Godot every time, also when the wrapper's Kotlin class already matches
  (only `GodotObject` is not asked). A `RefCounted` result is a new wrapper with a reference of
  its own, like the `from*` downcasts (close it or let the GC release it; the original is
  unaffected); any other result is the same wrapper when it already is a `T`, else a new view. They are inline
  reified functions backed by generated class-token tables (`GodotClasses.kt` for the shared tree,
  `PlatformGodotClasses.kt` per platform; `class_token_entries_shared` /
  `class_token_entries_platform`, `render_class_tokens`), built from class literals and constructor
  calls, so they need no reflection and survive R8 renaming. Web does not have them; see
  [Limitations](limitations.md#web).
- `X.fromHandle(handle)` wraps an existing object as a borrowed view
  ([Nullability](#6-nullability) has its return type).
- `X.create()` and the `from*` downcasts (`ArrayMesh.fromResource(res)`,
  `Mesh.fromObject(obj)`) come from one table, `FACTORY_HELPERS` (`render_factory_helpers`); a
  downcast returns `null` when the object is not that class.

```kotlin
val material = StandardMaterial3D.create()     // owned: close() it when done
val mesh = ArrayMesh.fromResource(resource)    // null if it is not an ArrayMesh
val body = collider.castOrNull<Node3D>() ?: return
```

## 12. Names as Constants

Every generated class with signals has `X.Signals`, one `const val` per signal holding Godot's
name (`render_signal_constants`): `Timer.Signals.timeout == "timeout"`,
`Node.Signals.treeExited == "tree_exited"`. On Web each generated class carries the same constants and typed handles for the signals it declares.

```kotlin
timer.connect(Timer.Signals.timeout, self, "onTimeout")
```

Engine-wide `MethodName`, `PropertyName` and `SignalName` (`scripts/generate_name_constants.py`)
are generated into `src/commonMain` (desktop, Android and iOS); Web scripts use
string literals or `X.Signals`.

## 13. Platform Parity

**Desktop, Android and iOS.** The API tree is common Kotlin code in
`src/commonMain/kotlin/net/multigesture/kanama/api`, compiled by every native target, so a member
of it exists on all three or on none: the compiler is the proof. The platform-bound pieces it
names are `expect` declarations (`GodotSignal`, `SignalConnection`, `MainThread`), and an
`actual` adds no public member its `expect` lacks. The exceptions are listed, not silent:

- the classes in `PER_PLATFORM_WRAPPERS`, hand-shaped per platform with a reason each (the list
  is empty);
- members generated for desktop and Android only, in a `<Class>.jvm.kt` companion, because the iOS
  call path does not carry their types: listed on [iOS Shape Gap](generated/ios-shape-gap.md);
- iOS-only sugar in a generated `<Class>.ios.kt` (`IOS_EXTENSION_SECTIONS`: today
  `PhysicsDirectSpaceState3D.intersectRay`'s nullable-argument form over the raycast C shim).

**Web** is a separate generated surface (`scripts/generate_web_wrappers.py`, one file per class
under `web-runtime/.../api/generated/`): it carries the members the Web call contract supports,
not every Godot method. It uses the same enum types, value names and prefix lock as native, the
same `meta: "required"` rule for returns, and `Double` for decimals including value-type
components. Where it differs from the native tree, for example (the list
names examples; the `web` and `common` snapshots in `api-snapshots/` are the authoritative record
of both surfaces):

- a parameter named like a Kotlin keyword is renamed by a table, not suffixed (`KEYWORD_RENAMES`:
  `internal` is `internalMode` on Web, `internalValue` natively);
- an object parameter's nullability follows the Web contract slot (`api_param_type`), for
  example `Node.removeChild(node: Node?)` on Web;
- a few returns are non-null by Web policy where native is nullable: `Node.getTree()` is
  `SceneTree` on Web and throws outside a tree.

Code that must compile for Web and native should not rely on parameter names where they differ,
and should treat `getTree()` as nullable.

A difference is a compile error, never a different result. `GodotObject.set(property, value)` is
Godot's `Object.set` on every backend, like GDScript's `set`: a path such as `"position:x"` names
no property, so it changes nothing; `setIndexed("position:x", value)` is the path form. Web's own
`GodotObject` members that native lacks or declares differently (no `get(property): Any?`, a
`setDeferred` that takes only a `Boolean`) fail the Web compile, and each one is listed with its
reason.

## Source Breaks

A source break is any change that can stop a script from compiling: a removed or renamed
member, class or parameter, a changed type or nullability, a removed default argument, a member
that is no longer `open`. Additions are not breaks, and neither are the source-compatible changes
the check recognises: a parameter that gains a default, a declaration that becomes `open`, a class
that gains a supertype.

**Policy.** Before 1.0 a break is allowed when it serves the long-term shape of the API, but never
silently: every break gets an entry in `CHANGELOG.md` under `## Unreleased` that says what changed
and how to migrate, with a line that starts with the prefix below (it may be indented, as a nested
bullet) and **names the owner of every declaration it breaks**: the class or object (`Node`, or
`Node.removeChild`), or `top-level` for a top-level declaration (an extension may name its receiver
class instead). Names match as whole words, so `Node` does not announce `Node3D`, and a qualified
name announces that member only: `Node.removeChild` does not announce `Node.setProcessMode`.

```text
- **Source break:** `Node.removeChild` takes a nullable `Node?` ...
```

Keep a `## Unreleased` section, even an empty one: with none, no break counts as announced.

The check compares every public declaration with a checked-in snapshot, so it also stops changes
that still compile, such as a return that becomes non-null or a parameter that becomes nullable:
those are announced the same way. How it works, and how to update its snapshots, is in
[Gates and Claims](../contributing/gates.md#source-breaks).

Generator code and its tables are described in
[Wrapper Maintenance](../contributing/wrapper-maintenance.md).
