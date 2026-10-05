# Generated API Conventions

This page lists what a script author can rely on in the generated Godot API, the classes in
`net.multigesture.kanama.api` such as `Node`, `Input` and `StandardMaterial3D`. Each rule
names the generator code that produces it and the gate that keeps it, so you can check the page
against the code.

**How stable this is.** Kanama is pre-1.0. These rules are the direction for long-term stability,
not a hard freeze: a rule can still change before 1.0 when that serves scalability or a Godot
upgrade (Godot 4.8 is the next one). Every change to a public signature is announced in the
[CHANGELOG](https://github.com/falcon4ever/kanama/blob/main/CHANGELOG.md) as a source break, and a
gate fails the build when one is not (see [Source breaks](#source-breaks)).

**Scope.** The rules cover the API tree that desktop, Android and iOS compile from the same
source (`src/commonMain/kotlin/net/multigesture/kanama/api`). The Web backend has its own generated
surface; [Platform parity](#13-platform-parity) lists where it follows the same rules and where
it differs. Not covered here: the [annotations](../game-dev/scripts.md) you write scripts with
(the reverse of rule 1 for your own scripts, a public `fun showMessage()` registered as Godot's
`show_message`, is in [Functions Godot Can Call](../game-dev/scripts.md#functions-godot-can-call)),
the builtin value types' own methods (`Vector3.lerp`, `Basis.inverse`), and `@Tool` /
editor-only APIs. The signature gate reaches further than these rules: it also holds the value
types, each platform's own classes and the Web facades to a snapshot (see
[Source breaks](#source-breaks)).

| Area | Current rule | Planned |
|---|---|---|
| [Names](#1-names) | Godot names, lowerCamel members, `Value` suffix on keywords | — |
| [Properties](#2-properties) | getter + setter = `var`, getter only = `val` | — |
| [Integers](#3-integers) | `int32` meta = `Int`, other `int` = `Long` | — |
| [Decimals](#4-decimals) | every decimal value `Double`, value-type and `Color` components included; packed bulk data keeps 32-bit storage | — |
| [Enums and bitfields](#5-enums-and-bitfields) | typed value classes, frozen value names | — |
| [Nullability](#6-nullability) | `meta: "required"` returns non-null, other object returns nullable | — |
| [Ownership](#7-ownership) | `close()` / `use { }` releases a `RefCounted` early; a forgotten one is released after the GC drops it (not yet Web) | Web: the same fallback |
| [Statics and singletons](#8-statics-and-singletons) | statics on the companion, singletons are `object`s | — |
| [Defaults and overloads](#9-defaults-and-overloads) | Godot defaults where Kotlin can express them | — |
| [Collections](#10-collections) | `List`, `Map<String, Any?>`, `ByteArray` | — |
| [Handles and factories](#11-handles-and-factories) | `GodotHandle`, `fromHandle`, `create()` | — |
| [Names as constants](#12-names-as-constants) | `X.Signals.<name>` | — |
| [Platform parity](#13-platform-parity) | one tree for desktop, Android and iOS; Web separate | — |

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

Kept by: the drift gate `scripts/check_wrapper_generator.py` (the committed tree is exactly what
the generator writes) and the signature snapshot ([Source breaks](#source-breaks)).

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

Kept by: `scripts/check_property_coverage.py` (no Godot property on a generated class is dropped
silently) and the drift gate.

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

Kept by: `scripts/audit_wrapper_signatures.py` and `scripts/audit_wrapper_abi_policy.py --strict`
(each helper's width matches Godot's), and the drift gate.

## 4. Decimals

Current rule (since task 134):

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
      result. Since task 134 B every operator Godot declares (`Basis * Basis`,
      `Transform3D * Plane`, `Vector2i % Vector2i`, `Color / Color`, `2.0 * v`, ...) and the short
      component-wise methods (`abs`, `floor`, `round`, `sign`, `min`/`max`, `clampf`,
      `directionTo`, integer-vector `length`, `Color.toHtml`, ...) are Kotlin with Godot's formulas
      (`types/BuiltinFormulas.kt`, `types/shared/BuiltinMath.kt`); the runtime smoke's builtin
      parity row proves every Kotlin-computed member bit-identical to GDScript over fixed-seed
      random inputs. Transcendental results computed in Kotlin on Web (`rotated`, `slerp`, ...) are
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
- **Double precision** (`-PkanamaPrecision=double`, desktop only): compiles and the JVM tests
  pass, but it is not tested end to end against a `precision=double` engine, and two known
  marshalling sizes assume single precision (the hard-coded 24-byte Variant size and the `Rect2`
  cell size in `ObjectCalls`).

Kept by: the value types' signatures in `api-snapshots/types.txt` / `web-types.txt`, the
`RealStorageWidthTest` unit test (storage, equality, `toString` against Godot's strings, real_t
arithmetic) and the `value_type_storage_smoke` rows of `scripts/runtime_smoke.sh`, which compare
an engine round trip, `str(v)`, a few operations and a 256-input randomized parity row (every
bit-identical operation above, hashed per operation) with GDScript's in the same run, and the Web
`WebValueTypeStorageTest` (Node).

## 5. Enums and bitfields

Every Godot enum and bitfield is a `@JvmInline value class` wrapping the `Long` Godot uses (task 128;
`godot_enum_model.render_enum_class`, `enum_type_ref`, `kotlin_type`). Every parameter, return and
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
  this Kanama does not name yet: `Node.ProcessMode(3L)`. `toString()` prints
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

```kotlin
self.setProcessMode(Node.ProcessMode.ALWAYS)
val flags = Control.SizeFlags.EXPAND or Control.SizeFlags.FILL
if (Control.SizeFlags.FILL in flags) { /* ... */ }
val err: GodotError = ResourceSaver.save(scene, path)
node.set("process_mode", Node.ProcessMode.ALWAYS)   // dynamic call: typed value accepted
```

Kept by: `scripts/check_typed_enums.py` (native: no enum slot is a raw `Long`, names follow the
lock, no top-level name shadows a Kotlin default import or a public Kanama type) and
`scripts/check_web_typed_enums.py` (Web). The lock is a generated file, so the drift gate holds it
to the generator. Upgrading from 0.4: [Enum Constant Migration](generated/enum-migration.md).

## 6. Nullability

**Object returns** are nullable (`X?`): Godot promises nothing about them. The exception is a
return Godot marks `meta: "required"`: it is non-null, and a null from the engine throws
`IllegalStateException("Godot returned null from required <Class>.<method>")`
(`kotlin_return_type`, `is_required_return`, `requireGodotReturn` in
`src/commonMain/kotlin/net/multigesture/kanama/binding/runtime/RequiredReturns.kt`). A `Callable`
return is `GodotCallable?`.

```kotlin
val tree = self.getTree() ?: return                  // not marked required: SceneTree?
val sameTree = self.tree                             // non-null accessor: throws outside the tree (task 133)
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

Kept by: `scripts/check_typed_enums.py` (part b: a required return is non-null and goes through
`requireGodotReturn`, every other object return is nullable), `scripts/check_web_typed_enums.py`
(the same on Web) and `scripts/audit_generator_object_policy.py` (the parameter decision and the
override table).

## 7. Ownership

The full statement is [Resource Ownership](../game-dev/godot-api.md#resource-ownership). In short:

- A `RefCounted`-typed return of a typed wrapper (`getMesh()`, `X.create()`, `ResourceLoader.load…`,
  a `Tweener`) is **owned**: it carries a reference that is yours. `close()` it or `use { }` it.
- So is **every element of a returned typed `Array` of `RefCounted`** (`getMaterials()`,
  `getProcessedTweens()`, ...): the Array is destroyed right after the decode, so each element is
  retained first and its wrapper owns that reference (task 131; desktop, Android and iOS). Close
  each element. An element of an untyped `Array[Object]` that is `RefCounted` comes back as a
  `RefCounted` wrapper so it can be closed. Kept by `audit_generator_shape_policy.py` (every
  returned object Array decodes through `readArrayObjectsOwned`). Array and Dictionary elements
  read through a dynamic path (a Variant) stay borrowed.
- `close()` releases your reference; it destroys the object only if that was the last one.
  Calling a member on a closed wrapper throws `IllegalStateException` instead of reaching freed
  memory (`emits_receiver_guard`, `checkOpen()` in the hand root `RefCounted`).
- A fluent method that returns its own receiver (`tweener.setTrans(...)`) returns `this`, not a
  second owned wrapper (`self_return_collapse_wrapper`), so a chain needs no extra `close()`.
- Values read through a dynamic path (`call`, `get`, `getMeta`, ...) are borrowed views: never
  close them. Nodes and plain `Object`s have no `close()`; free nodes with `queueFree()`.
- A lambda signal connection is released by Godot when the receiver or the emitter is freed, a
  one-shot connection fires, or it is disconnected; `SignalConnection.close()` is only needed to
  disconnect early (task 131; desktop, Android and iOS, not yet Web). See
  [Signals](../game-dev/signals.md).
- An exception thrown from a script callback is reported as a Godot script error with the Kotlin
  file and line, and the call returns `null` (task 131; desktop, Android and iOS, not yet Web).

- **Close to release early.** A forgotten `close()` is a late release, not a leak (task 132;
  desktop, Android 13+ and iOS, not yet Web; device runs pending): an owned wrapper registers a cleanup that holds only
  its handle, and once the garbage collector drops the wrapper the reference is released on the
  main thread at the next frame. `close()` cancels that cleanup, so a reference is released once.
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
  fallback does not release forgotten wrappers of its owner (until the script is detached or
  shutdown), the lifetime it had before. See
  [A script object keeps its resource alive](../game-dev/godot-api.md#a-script-object-keeps-its-resource-alive).
- Wrappers made off the engine main thread get no GC fallback (close them).

Kept by: the generator functions above; `refcounted_ownership_problems` in
`scripts/audit_generator_shape_policy.py`, which checks every wrapper source for the owned/borrowed
choice (returns and `create()` owned, `fromHandle` and typed-Array element callbacks borrowed);
and `scripts/runtime_smoke.sh`, which asserts reference counts and liveness around the documented
cases, and that 10,000 dropped owned `Resource`s are released after a collection (with the
fallback off, they stay).

## 8. Statics and singletons

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

Kept by: the drift gate.

## 9. Defaults and overloads

- A Godot default argument is a Kotlin default argument when Kotlin can express it
  (`kotlin_default_expression`, `kotlin_enum_default_expression`; a few exact composite defaults
  in `KOTLIN_DEFAULT_EXPRESSION_OVERRIDES`, such as `Node3D.lookAt(target, up = Vector3.UP)`).
- A default the generator cannot express is a **required** parameter:
  `CanvasItem.drawTexture(texture, position, modulate)` needs the `Color` Godot defaults to white.
  Adding such a default later is not a break.
- An `expect` declaration (the seams the shared tree names, such as `GodotSignal`) carries no
  default argument; it has overloads instead (`signal.connect(target, method)` and
  `connect(target, method, flags)`), because the Android build compiles without the `expect` file.
- Godot has no overloads. Kanama adds a few: a `String` overload beside each `NodePath` lookup on
  `Node` (`getNodeOrNull("Hud/Label")`) and on `GodotObject` (`setIndexed`, `getIndexed`).
  Godot's vararg methods are Kotlin `vararg` (`call(method, vararg args)`, `rpc`, `emitSignal`).

Kept by: `scripts/check_expect_no_defaults.py` (expect declarations) and the drift gate.

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

Godot's own methods of the types Kotlin represents itself are generated too (task 134 D2): String's
and PackedByteArray's as extension functions on `String` / `ByteArray` in
`net.multigesture.kanama.builtins` (never under a name the Kotlin standard library already uses
on that type, except the deprecated `capitalize()`), NodePath's as `NodePath` members, Callable's and Signal's as `GodotCallable` /
`GodotSignal` members; see [Strings, Node Paths and Bytes](../game-dev/godot-api.md#strings-node-paths-and-bytes).

The table is `SCALAR_KOTLIN_TYPES` in `scripts/generate_api_wrapper.py`; a method whose types
have no audited helper shape is not generated rather than generated with a wider type (`CALL_SHAPES` in
`scripts/api_wrapper_candidates.py`; the skips are listed in the
[Wrapper Generator Report](generated/wrapper-generator-report.md)).

```kotlin
val bodies: List<Node3D> = area.getOverlappingBodies()
val groups: List<String> = self.getGroups()
```

Kept by: `scripts/audit_wrapper_signatures.py`, `scripts/audit_variant_marshalling_policy.py`,
`scripts/type_coverage_audit.py` and the drift gate.

## 11. Handles and factories

- `GodotHandle` is the one identity type game code passes around. Every generated class has a
  public constructor from it, but that constructor does not check the class:
  `CharacterBody3D(body.handle)` calls `CharacterBody3D` methods on whatever `body` really is.
- **Checked casts** (task 133) re-type a wrapper safely: `body.castOrNull<CharacterBody3D>()` asks
  Godot (`Object.is_class`) and returns `null` when the object is another class, `cast<T>()`
  throws a `ClassCastException`, and `Node.requireAs<T>(path)` / `getNodeAs<T>(path)` check the
  node they find. They ask Godot every time, also when the wrapper's Kotlin class already matches
  (only `GodotObject` is not asked). A `RefCounted` result is a new wrapper with a reference of
  its own, like the `from*` downcasts (close it or let the GC release it; the original is
  unaffected); any other result is the same wrapper when it already is a `T`, else a new view. They are inline
  reified functions backed by generated class-token tables (`GodotClasses.kt` for the shared tree,
  `PlatformGodotClasses.kt` per platform; `class_token_entries_shared` /
  `class_token_entries_platform`, `render_class_tokens`), built from class literals and constructor
  calls, so they need no reflection and survive R8 renaming. Not yet on Web.
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

Kept by: `check_factory_helpers`, which every regeneration runs (the drift gate's included): a
table key must be a class the generator renders, and no factory may be pasted by hand outside the
table. The class-token tables are part of the generated tree, so the drift gate holds them to the
generator, and `ClassTokenTableTest` checks that the shared table lists every shared class.

## 12. Names as constants

Every generated class with signals has `X.Signals`, one `const val` per signal holding Godot's
name (`render_signal_constants`): `Timer.Signals.timeout == "timeout"`,
`Node.Signals.treeExited == "tree_exited"`. On Web each generated class carries the same constants and typed handles for the signals it declares.

```kotlin
timer.connect(Timer.Signals.timeout, self, "onTimeout")
```

Engine-wide `MethodName`, `PropertyName` and `SignalName` (`scripts/generate_name_constants.py`)
exist on **desktop and Android only** today: they are generated into `src/jvmMain`, not into the
shared tree, so iOS and Web scripts use string literals or `X.Signals`.

## 13. Platform parity

**Desktop, Android and iOS.** Since task 117 the API tree is common Kotlin code in
`src/commonMain/kotlin/net/multigesture/kanama/api`, compiled by every native target, so a member
of it exists on all three or on none: the compiler is the proof. The platform-bound pieces it
names are `expect` declarations (`GodotSignal`, `SignalConnection`, `MainThread`), and
`scripts/check_actual_public_surface.py` keeps an `actual` from adding public members its
`expect` lacks. The exceptions are listed, not silent:

- the classes in `PER_PLATFORM_WRAPPERS`, hand-shaped or generated for one platform with a reason
  each (for example `FileAccess`, `Engine`, `ResourceLoader`);
- members generated for desktop and Android only, in a `<Class>.jvm.kt` companion, while iOS waits
  on a call helper: listed on [iOS Shape Gap](generated/ios-shape-gap.md);
- the engine-wide name constants ([rule 12](#12-names-as-constants)).

**Web** is a separate generated surface (`scripts/generate_web_wrappers.py`, one file per class
under `web-runtime/.../api/generated/`): it carries the members the Web call contract supports,
not every Godot method. Since task 128 it uses the same enum types, value names and prefix lock as
native, the same `meta: "required"` rule for returns, and `Double` for decimals including
value-type components. Where it differs from the native tree today, for example (the list
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

## Source breaks

A source break is any change that can stop a script from compiling: a removed or renamed
member, class or parameter, a changed type or nullability, a removed default argument, a member
that is no longer `open`. Additions are not breaks, and neither are the source-compatible changes
the gate recognises: a parameter that gains a default, a declaration that becomes `open`, a class
that gains a supertype.

**Policy.** Before 1.0 a break is allowed when it serves the long-term shape of the API, but never
silently: every break gets an entry in `CHANGELOG.md` under `## Unreleased` that says what changed
and how to migrate, with a line that starts with the marker below (it may be indented, as a nested
bullet) and **names the owner of every declaration it breaks**: the class or object (`Node`, or
`Node.removeChild`), or `top-level` for a top-level declaration (an extension may name its receiver
class instead). Names match as whole words, so `Node` does not announce `Node3D`.

```text
- **Source break:** `Node.removeChild` takes a nullable `Node?` ...
```

Keep a `## Unreleased` section, even an empty one: with none, the gate sees no announcement.

**The gate.** `scripts/check_public_signature_changes.py` (a `local_ci.sh` stage) compares the
public surface with checked-in snapshots in the repository's `api-snapshots/` directory, one line
per declaration, one file per surface (every source directory is read recursively):

| Snapshot | Sources | What |
|---|---|---|
| `common.txt` | `src/commonMain/.../api` | the shared native tree: generated classes, `GlobalEnums.kt`, the hand roots `GodotObject`, `RefCounted`, `GodotCallable`, `GodotHandle`, the `expect` declarations |
| `types.txt` | `src/commonMain/.../types` | the builtin value types (`Vector3`, `Color`, `Basis`, ...) |
| `jvm.txt`, `ios.txt` | `src/jvmMain/.../api`, `src/iosMain/.../api` | what each native platform declares on its own: the per-platform classes, the `<Class>.jvm.kt` / `<Class>.ios.kt` companions, the `actual`s, and on desktop the name constants (`GD` and `Mathf` are common since task 129 B) |
| `web.txt` | `web-runtime/.../api` | the Web wrappers, generated and hand-written |
| `web-types.txt` | `web-runtime/.../types` | the Web value types |

A line records the owner, kind, name, receiver, type parameters and `where` clauses, parameter
names and types, whether each parameter has a default (not the default's value), the return or
property type, supertypes and source modifiers (`open`, `final`, `override`, ...); annotations,
constant values and bodies are not part of it. Modifiers count whether they stand on the
declaration's own line or on the lines just above it. `private` and `internal` declarations, and
those marked `@Deprecated(level = DeprecationLevel.HIDDEN)`, are skipped, so hiding a declaration
reads as removing it. The snapshots are read from the Kotlin sources, not from a build, in a few
seconds.

**Every public declaration states its type.** A declaration whose type is not written out fails the
gate with its file and line (`fun seed(value: Long) = ...` must be `fun seed(value: Long): Unit =
...`), except where the type is certain from the source: `= Unit`, a literal, a companion constant
built with its own class's constructor (`val ZERO = Vector3(...)`), or a Web import-compatibility
extension whose whole body calls the same-named member, whose type the gate takes over. Today no
declaration is left without a visible type.

The gate compares lines, so it also stops changes that still compile, such as a return that
becomes non-null or a parameter that becomes nullable: those are announced the same way.

| What changed | Gate result | What to do |
|---|---|---|
| nothing | PASS | — |
| signatures added, or a source-compatible change | FAIL: snapshots out of date | `python3 scripts/check_public_signature_changes.py --write`, commit the snapshots |
| a signature removed or changed, its owner not named by a `Source break` line | FAIL naming each change (`was` / `now`) and each unannounced owner | fix the generator, or announce the break in the CHANGELOG |
| a signature removed or changed, its owner named | FAIL: snapshots out of date | `--write`, commit the snapshots with the change |
| a public declaration without a visible type | FAIL naming file and line | write the type |

`--write` refuses to record a removal or change whose owner no `Source break` line names. The
snapshot diff then shows every changed signature in the pull request that changes it.

Generator code and its tables are described in
[Wrapper Maintenance](../contributing/wrapper-maintenance.md).
