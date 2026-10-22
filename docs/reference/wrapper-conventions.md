# API Conventions

This page lists the rules the Kotlin Godot API follows: how a Godot name, type,
enum or default becomes Kotlin. The classes are in `net.multigesture.kanama.api`,
such as `Node`, `Input` and `StandardMaterial3D`, and every one of them follows
these rules. [Calling Godot APIs](../game-dev/godot-api.md) is the short
version; this page is the full reference.

The rules cover desktop, Android and iOS, which compile the same API.
[Platform Parity](#13-platform-parity) lists where Web differs. The annotations
you write scripts with are on [Writing Scripts](../game-dev/scripts.md),
including how your own `fun showMessage()` becomes Godot's `show_message`.

Kanama is pre-1.0, so a rule can still change when a Godot upgrade or the
long-term design of the API needs it. Every change that can stop your code from
compiling is listed in the
[CHANGELOG](https://github.com/falcon4ever/kanama/blob/main/CHANGELOG.md) as a
"Source break", with the old and new spelling and how to migrate.

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
| [Platform Parity](#13-platform-parity) | one API for desktop, Android and iOS; Web separate |

The snippets on this page are parts of a script like the one in
[Writing Scripts](../game-dev/scripts.md#a-complete-script), which lists the
imports.

## 1. Names

```kotlin
val label = self.getNodeOrNull("Hud/Label")                        // get_node_or_null
val child = itemScene.instantiateAs<Node>()                        // itemScene: a preloaded PackedScene
self.addChild(child, internalValue = Node.InternalMode.FRONT)      // `internal` is a Kotlin keyword
ENetMultiplayerPeer.create().use { peer ->
    peer.closeConnection()                                         // MultiplayerPeer.close()
}
```

- **Classes** keep Godot's name. Godot's `Object` is `GodotObject`, and global
  enums are renamed only when Godot's name would collide (see
  [Enums](#5-enums-and-bitfields)).
- **Methods** are Godot's `snake_case` in `lowerCamelCase`: `get_node_or_null`
  is `getNodeOrNull`. A leading underscore is dropped. A few methods whose Godot
  name Kanama or Java already uses are renamed: Godot's `close()` becomes
  `closeConnection()`, `closeArchive()` or `closeDock()`, because `close()`
  releases a `RefCounted` (see [Ownership](#7-ownership)), and `wait()` becomes
  `waitBlocking()`, because every Java object already has a final `wait()`.
- **Parameters** are `lowerCamelCase` too. A parameter named like a Kotlin
  keyword gets the suffix `Value`: Godot's `internal` is `internalValue`. A
  handful are renamed for readability, such as those of the `Time` methods.
  Parameter names are part of the API, because Kotlin callers can name
  arguments.
- **Properties** are the Godot property name in `lowerCamelCase`. The exceptions
  pair with renamed accessors: `Curve3D.closed` is `curveClosed`
  (`setCurveClosed`), `OccluderPolygon2D.closed` is `polygonClosed`.

## 2. Properties

```kotlin
self.processMode = Node.ProcessMode.ALWAYS
val material = StandardMaterial3D.create()
material.albedoTexture = preloadedTexture       // preloadedTexture: a Texture2D you loaded
val light = self.requireAs<OmniLight3D>("Lamp")
light.lightEnergy = 2.0
```

A Godot property becomes a Kotlin property when Godot has a getter for it:

- getter and setter of the same Kotlin type: `var`;
- getter only, or a setter whose type differs from the getter's: `val`;
- setter only: no property, the setter method alone.

The accessor methods stay callable (`getProcessMode()` / `setProcessMode(...)`).
An indexed property, one Godot backs with a shared accessor and an index, is a
property too, with the index bound: `BaseMaterial3D.albedoTexture` calls
`getTexture` with the albedo index, `Light3D.lightEnergy` calls `getParam`.

## 3. Integers

```kotlin
val n: Int = self.getChildCount()
val id: Long = self.getInstanceId()
val ready: Long = Node.NOTIFICATION_READY   // a class constant: const val, Long
```

Godot's `int` is `Int` when Godot declares it as an 8-, 16- or 32-bit value
(`meta` `int8`, `int16`, `int32`, `char32`, `uint8` or `uint16` in Godot's API
description), and `Long` otherwise. The width is exact on purpose: an `int32`
return is four bytes. Class integer constants that are not enum values are
`const val NAME: Long` on the companion, under Godot's name.

## 4. Decimals

Every decimal value a script reads or writes is a `Double`, on every platform,
as in GDScript, where `float` is 64-bit. That covers `float` arguments, returns
and properties, and the components of the value types (`Vector2`, `Vector3`,
`Vector4`, `Quaternion`, `Plane`, `Basis`, `Transform2D/3D`, `Rect2`, `AABB`,
`Projection`) and of `Color`.

A value type stores its components at Godot's width, exactly as Godot, GDScript
and C# do: 32-bit floats for the vectors, transforms and `Color`. A constructor
or `copy` rounds each component to that width, and a property reads it back as
a `Double`. So Kotlin behaves like GDScript:

- `node.position = v; node.position == v` is `true`: the stored value reaches
  Godot unchanged.
- `==` and `hashCode` compare the stored values with Godot's `==`
  (`-0.0 == 0.0`; `NaN` equals itself only, to keep Kotlin's `equals` rules).
- `toString()` prints what GDScript's `str(v)` prints: `(0.1, 0.2)`,
  `(1.0, 2.0, 3.0)`, Quaternion `(0, 0, 0, 1)`, Color `(1.0, 0.5, 0.0, 1.0)` (at
  most four decimals), Plane `[N: (0.0, 1.0, 0.0), D: 0]`, Basis and Transform
  columns `[X: …, Y: …, Z: …, O: …]`.
- Arithmetic gives Godot's result bit for bit: `+`, `-`, `*`, `/` and unary `-`
  are computed on the stored values and rounded to the storage width, and a
  scalar operand is rounded first, as `Vector2 * float` does in Godot. `dot`,
  `length`, `distanceTo`, `cross`, `normalized`, `Quaternion * Quaternion`,
  `Basis * Vector3`, `Transform3D * Vector3`, every other operator Godot
  declares, and the short component-wise methods (`abs`, `floor`, `round`,
  `sign`, `min`/`max`, `clampf`, `directionTo`, `Color.toHtml`, ...) use
  Godot's own formulas. Methods the engine computes (`lerp`, `rotated`, `slerp`,
  `angle`, ...) return the engine's result.

The same caveat as GDScript applies: `v.x = 0.1` stores the 32-bit float
nearest to 0.1, so `v.x == 0.1` is `false`, while `v == Vector2(0.1, 0.2)` is
`true`, because both sides are stored. Compare decimals with `isEqualApprox`.
Values exact in 32 bits (`0.5`, `1.25`, small integers) compare equal either
way.

Integer components construct without boxing, as in GDScript: `Vector3(0, 1, 0)`,
`Vector3(speed, 0, 0)`, `Color(1, 1, 1)`, `Color(1, 1, 1, 0.72)`,
`Plane(Vector3.UP, 0)`. `Vector2` and `Vector3` accept any mix of `Int` and
`Double`, `Color` adds `(Int, Int, Int, Double)`, and `Vector4`, `Quaternion`
and `Plane(x, y, z, d)` take all-`Int` or all-`Double`. The operators take
`Int`, `Long`, `Double` and `Float` scalars.

Packed bulk data keeps compact 32-bit storage: `PackedFloat32Array` is
`List<Float>`, `PackedFloat64Array` is `List<Double>`. The elements of
`PackedVector2/3/4Array` and `PackedColorArray`, and of mesh arrays, are the
ordinary value types.

Kanama works with Godot's standard single-precision builds only; see
[Limitations](limitations.md#desktop).

### Operators, Methods and Edge Cases

- **Operators** are Kotlin operators with GDScript's meaning: `a + b`, `v * 2.0`,
  `2.0 * v`, `-v`, `transform * point`, `basis * otherBasis`,
  `quaternion * vector`, `Vector2i(3, 4) * 2`, `Vector2i(3, 4) * 0.5` (a
  `Vector2`, as in GDScript), `color * 0.5`, `transform * points` (a
  `List<Vector3>`), and `<` / `>` on vectors, component by component, as Godot
  compares. `point * transform` is Godot's inverse transform. `2.0 * v` and
  `points * transform` need `import net.multigesture.kanama.types.times`, which
  the IDE offers, or `net.multigesture.kanama.types.*`.
- **Methods** are camelCase (`v.directionTo(target)`, `v.snapped(step)`,
  `basis.getEuler()`, `Projection.createPerspective(...)`,
  `Color.fromHsv(h, s, v)`); `aabb.intersectsRay(from, dir)` is `null` where
  GDScript returns `null`. Getters keep `get` (`rect.getCenter()`,
  `transform.getRotation()`), except `Transform2D.get_origin()`, which is the
  `origin` property. Constants are the ones Godot declares (`Vector2i.LEFT`,
  `Vector3.MODEL_FRONT`, `Basis.FLIP_X`, `Plane.PLANE_XY`, `Vector3.INF`, every
  named color), and the enums are nested (`Vector3.Axis.X`).
- **Speed.** Operators and every method that is plain arithmetic in Godot
  (`abs`, `floor`, `round`, `min`/`max`, `clampf`, `lerp`, `moveToward`,
  `slide`/`bounce`/`reflect`, `project`, `limitLength`, `Rect2`/`Rect2i`/`AABB`
  `hasPoint`/`intersects`/`encloses`/`merge`/`grow*`, `Plane.project`,
  `Transform2D.inverse`/`translated`, `Color.lerp`, ...) run in Kotlin and cost
  no engine call. Every other method (`angle`, `rotated`, `slerp`,
  `Basis.getEuler`, `Color.lightened`, ...) is computed by the engine, about as
  fast as the same call from GDScript.

### Differences from GDScript

- Integer division or `%` by zero (`Vector2i(1, 1) / 0`) throws
  `ArithmeticException`; GDScript reports a division-by-zero error.
- Godot's debug build checks some arguments and returns a default instead of
  computing: `slide`, `bounce` and `reflect` with a non-normalized normal, and
  `quaternion * vector` and `vector * quaternion` with a non-normalized
  quaternion. Kotlin computes the formula without the check, which is what an
  exported release game does. Engine-computed methods (`slerp`, `rotated`, ...)
  report the same error as GDScript, and so do `Basis.inverse` of a singular
  basis, `Basis.getRotationQuaternion` of a basis that is not a rotation and
  `Transform3D.affineInverse` of a singular one.
- Comparing vectors with a NaN component: Godot answers `false` to all of `<`,
  `<=`, `>` and `>=`. Kotlin sorts a NaN component last, so `<` and `<=` are
  `false` but `>` and `>=` are `true`.
- A float that becomes an integer, such as `Color.toHtml` on a channel of NaN,
  ±INF or beyond the `int` range: Godot's result differs by CPU, x86 or arm64.
  Kotlin gives 0 for NaN and clamps out-of-range values, which matches Godot on
  arm64 (Apple silicon, phones). For finite channels every platform agrees.

## 5. Enums and Bitfields

```kotlin
self.setProcessMode(Node.ProcessMode.ALWAYS)
val flags = Control.SizeFlags.EXPAND or Control.SizeFlags.FILL
if (Control.SizeFlags.FILL in flags) { /* ... */ }
val scene = PackedScene.create()
val err: GodotError = ResourceSaver.save(scene, "user://level.tscn")
self.set("process_mode", Node.ProcessMode.ALWAYS)   // dynamic call: typed value accepted
```

Every Godot enum and bitfield is a Kotlin value class wrapping the `Long` Godot
uses, and every parameter, return and property Godot types with that enum uses
it.

- **Placement.** A class enum nests in its class (`Node.ProcessMode`,
  `BaseMaterial3D.Flags`); a global (`@GlobalScope`) enum is top-level (`Key`,
  `MouseButton`). A global enum is renamed only when Godot's name would
  collide: `Error` is `GodotError`, `PropertyHint` is `GodotPropertyHint`, and
  `Variant.Type` / `Variant.Operator` are `VariantType` / `VariantOperator`.
- **Value names** drop the enum's common prefix and keep Godot's
  SCREAMING_CASE, the rule Godot's C# bindings use: `PROCESS_MODE_ALWAYS` is
  `Node.ProcessMode.ALWAYS`. A value that would start with a digit keeps a
  word (`Key.KEY_0`); an enum without a common prefix keeps full names
  (`GodotError.ERR_FILE_NOT_FOUND`).
- **Frozen names.** Each enum's prefix is fixed. A value a later Godot adds that
  does not fit the prefix keeps its full Godot name, and the existing names do
  not change.
- **Raw values.** `.value` is the `Long`, and the public constructor builds any
  value, including one Kanama does not name, such as a value a newer Godot adds:
  `Node.ProcessMode(3L)`. `toString()` prints `ProcessMode(value=3)`.
- **Bitfields** have `or`, `and`, `xor`, `inv()` and `in`; the result keeps the
  type. Where Godot names no zero value, `X(0L)` is the empty set.
- **Defaults** are the named value equal to Godot's default.
- **Dynamic calls.** `call`, `set`, `callDeferred`, `emitSignal` and `Array` /
  `Dictionary` elements accept the typed values and pass the integer they stand
  for. What a dynamic call returns (`get`, `call`) is a `Long`; wrap it with
  `Node.ProcessMode(raw as Long)` when you need the type.
- **Engine virtuals**, the `_`-prefixed engine methods you override with
  `@OverrideVirtual`, take the value class where Godot uses an enum
  (`@OverrideVirtual fun _get_shader_mode(): Shader.Mode`); a `Long` there fails
  the build and names the type.
- **Script members** typed with a Godot enum (`@Export` properties, public
  functions, `@Signal`, `@Rpc`) are covered in
  [Exports and Resources](../game-dev/properties-resources.md#godot-enums-and-bitfields).

### Enum Exports in the Inspector

An `@Export` of a Godot enum looks like the engine's own properties (what
`Node.process_mode` shows), not like GDScript's: an `int` with
`PROPERTY_HINT_ENUM`, or `PROPERTY_HINT_FLAGS` for a bitfield, and a hint
string of the value names as Godot capitalizes them, with the values spelled
out (`Inherit:0,Pausable:1,When Paused:2,Always:3,Disabled:4`,
`Messages:1,Messages Physics:2,Messages All:3`). GDScript's
`@export var mode: Node.ProcessMode` uses the full names
(`Process Mode Inherit:0,...`) and exports a bitfield as a dropdown of single
flags. The stored `int` is the same, so scenes are interchangeable.

Whether a type is a bitfield is Godot's call: `BaseMaterial3D.Flags` is an enum
of flag indices and exports as a dropdown. As in GDScript, the property's
`class_name` is Godot's qualified name (`Node.ProcessMode`) and its usage
includes `PROPERTY_USAGE_CLASS_IS_ENUM`; a bitfield uses Godot's
`PROPERTY_USAGE_CLASS_IS_BITFIELD`, so assigning a combination of flags from
GDScript does not warn. The default may be a value, the raw form
(`Node.ProcessMode(3L)`) or an `or` of bitfield values. A
`List<Node.ProcessMode>` is a typed int array. A value the hint does not list,
from a newer Godot or an extension, is kept, not clamped. On Web the export
carries the hint and hint string only, without `class_name`.

Every `@Export*` hint string is built the way GDScript's parser builds it, so
`get_property_list()` reports the same hint and hint string for a Kotlin script
and its GDScript twin. `@ExportRange` writes its extra flags in GDScript's
order: `or_greater`, `or_less`, `exp`, `radians_as_degrees`, `degrees`,
`prefer_slider`, `hide_control`, `suffix:<unit>`. Build errors follow
GDScript's argument rules: an empty argument, or one with a comma (except
placeholder text), a malformed `@ExportFlags` value, an `@ExportNodePath`
class that is not a `Node` (an engine class or a `@GlobalClass` script), a hint
on a type it does not take, two hints on one property.

Use the `VariantType` in `net.multigesture.kanama.api`; the one in
`net.multigesture.kanama.binding.runtime` is internal. If you are moving 0.4
code to the typed enums, see [Upgrading from
0.4](../getting-started/upgrading-0.4-to-0.5.md) and
[Enum Constant Migration](generated/enum-migration.md).

## 6. Nullability

```kotlin
val tree = self.getTree() ?: return                  // not marked required: SceneTree?
val sameTree = self.tree                             // non-null accessor: throws outside the tree
val root: Window = tree.getRoot()                    // required: Window, not Window?
val tween = self.createTween()
val tweener = tween.tweenProperty(self, "modulate", Color(1, 1, 1, 0), 0.5)
tweener.setTrans(Tween.TransitionType.SINE).setEase(Tween.EaseType.OUT)   // required fluent returns chain with `.`
```

**Object returns** are nullable (`X?`): Godot promises nothing about them. The
exception is a return Godot marks `meta: "required"`: it is non-null, and a
null from the engine throws
`IllegalStateException("Godot returned null from required <Class>.<method>")`.
A `Callable` return is `GodotCallable?`.

**Object parameters** follow one decision, in this order:

1. Godot marks it `meta: "required"`: non-null, always.
2. A `Resource` or other `RefCounted` type: nullable, so you can pass `null` to
   clear it.
3. Godot's source accepts `null` for it, such as `Node.setOwner`: nullable.
   Kanama keeps a short list of these.
4. Otherwise non-null.

```kotlin
val meshInstance = self.requireAs<MeshInstance3D>("Body")
meshInstance.setMesh(null)    // Resource-derived: nullable
val child = itemScene.instantiateAs<Node>()   // itemScene: a preloaded PackedScene
child.setOwner(null)          // Godot accepts null here
self.addChild(child)          // Node, not marked required, not listed: non-null
```

`X.fromHandle(handle)` returns `X?`, except `Resource.fromHandle`, which is
non-null because a `@ScriptClass(attachTo = "Resource")` script uses it as its
constructor reference.

## 7. Ownership

The full statement is [Resource Ownership](../game-dev/godot-api.md#resource-ownership).
In short:

- A `RefCounted` value a typed method returns (`getMesh()`, `X.create()`,
  `ResourceLoader.load…`, a `Tweener`) is **owned**: it carries a reference
  that is yours. `close()` it or `use { }` it.
- So is **every element of a returned typed `Array` of `RefCounted`**
  (`getMaterials()`, `getProcessedTweens()`, ...) on desktop, Android and iOS:
  close each element. An element of an untyped `Array[Object]` that is
  `RefCounted` comes back as a `RefCounted` wrapper, so it can be closed.
  `Array` and `Dictionary` elements read through a dynamic call stay borrowed.
- `close()` releases your reference; it destroys the object only if that was
  the last one. Calling a member on a closed wrapper throws
  `IllegalStateException` instead of reaching freed memory.
- A fluent method that returns its own receiver (`tweener.setTrans(...)`)
  returns the same wrapper, not a second owned one, so a chain needs no extra
  `close()`.
- Values read through a dynamic call (`call`, `get`, `getMeta`, ...) are
  borrowed views: never close them. Nodes and plain `Object`s have no
  `close()`; free nodes with `queueFree()`.
- A lambda signal connection is released by Godot when the receiver or the
  emitter is freed, a one-shot connection fires, or it is disconnected, on every
  platform. `SignalConnection.close()` is only needed to disconnect early. See
  [Signals and Await](../game-dev/signals.md).
- A forgotten `close()` is a late release, not a leak, on desktop, Android 13
  and newer, iOS and Web: once the garbage collector drops the wrapper, the
  reference is released on the main thread at the next frame. `close()`
  releases it at once, and only once. At shutdown a collection runs before
  Godot's leak report. The project setting `kanama/debug/log_gc_releases` logs
  each such release once per place in your code that created the object.
  Wrappers made on any thread get this fallback; the release still runs on the
  main thread.
- A `from*` downcast (`Mesh.fromObject(obj)`) takes a reference of its own, so
  a downcast kept in a field keeps the object alive whatever happens to the
  wrapper it came from. `fromHandle` and wrapper constructors are borrowed
  views; `close()` on one releases nothing, and debug builds warn.
- A script on a `RefCounted` owner, such as a custom resource, keeps its owner
  alive, as in GDScript and C#, on desktop, Android 13 and newer, and iOS. See
  [Keeping a Resource through Its Script](../game-dev/properties-resources.md#keeping-a-resource-through-its-script).
- **Exported resource slots** are a fixed list, because Kanama keeps each one
  alive while the script holds it: `PackedScene`, `Texture`, `Texture2D`,
  `NoiseTexture2D`, `Material`, `ShaderMaterial`, `Curve`, `FastNoiseLite`,
  `ButtonGroup`, `LightmapGIData`, `AudioStream`, `Mesh`, `Shape2D`, `Shape3D`,
  `Font`, `Animation`, `StyleBox`, and any `@GlobalClass` custom resource
  script.

### Errors in Callbacks

An exception thrown from a script callback is reported as a Godot script error,
and the call returns `null`. In a debug build on desktop, Android and iOS the
error names the Kotlin file and line; an Android minified release build and an
iOS release build cannot (see [Limitations → Android](limitations.md#android)
and [Limitations → iOS](limitations.md#ios)). This covers lifecycle callbacks,
public functions, signal lambdas, property accessors, `MainThread` tasks and
coroutines started with `launch`; the full stack trace goes to the console. The
reported line is the first one in your own code, as a `res://` path when the
file is in the project, else the bare file name. A wrapper of a freed object
handed to Godot as a value (an export, a return, a dynamic-call argument) is
silently `null`, as in GDScript. On Web the error reaches the Godot log without
the Kotlin line; see [Limitations → Web](limitations.md#web).

### The Full Ownership Table

| Category | What it covers | What to do |
|---|---|---|
| **Owned** | `X.create()`, `ResourceLoader.load…`, every `RefCounted` value a method returns **including plain getters**, every element of a returned typed `Array` of `RefCounted` (`getMaterials()`, `actionGetEvents()`, `getProcessedTweens()`, …), every `RefCounted` element of an `Array`/`Dictionary` that `call(...)` (or `callv`, `callDeferred`, `callThreadSafe`, `ClassDB.classCallStatic`, `GodotCallable.call`/`callv`) returns, the `from*` downcasts (`Mesh.fromObject(...)`, `ArrayMesh.fromResource(...)`), the checked casts to a `RefCounted` class (`res.cast<Texture2D>()`, `castOrNull`), and `@Export` reads of resource-typed fields and collections | `close()` it, or `use { }`, to release it early; for a list, each element (`list.forEach { it.close() }`). Forgotten, it is released after the garbage collector drops the wrapper |
| **Borrowed view** | A wrapper you build over a handle you already have: `Resource.fromHandle(...)`, a wrapper constructor (`Mesh(other.handle)`), a script's own `self`, and the `RefCounted` elements of an `Array` or `Dictionary` that arrives as an argument of a signal lambda or a script function: the caller holds them for the call, so do not `close()` them or keep them past it | Keep the wrapper you got the handle from while you use the view, because the view takes no reference. `close()` on it releases nothing (debug builds warn) |
| **Engine-owned, live** | A running `Tween` (from `createTween()` or an element of `getProcessedTweens()`), anything living in the scene tree | Stop it the Godot way (`kill()`, `queueFree()`); `close()` never stops it. Your wrapper of a tween still owns a reference; close it when you stop using that wrapper |
| **Nodes and plain `Object`s** | Anything not `RefCounted`: there is no reference count, and `GodotObject` has no `close()` | `Node.queueFree()` |

- **Tweens.** The `SceneTree` holds its own reference to every tween it
  processes, so a tween runs until it finishes or is killed, whatever your
  wrappers do. `close()` on a tween wrapper releases only your reference; it
  never stops the tween, and a closed wrapper cannot be called again. So:
  `kill()` to stop a tween; `close()`, after `kill()` or once you no longer call
  it, to release your wrapper; and close each element of
  `SceneTree.getProcessedTweens()` when you are done reading it, which does not
  affect the tweens.
- **Handing a value to Godot.** Passing an owned wrapper to a method that
  stores it (`setMesh`, `setStream`, `ResourceSaver.save`) does not hand it
  over: Godot takes its own reference, and yours is still yours to close.
- **`@Export` reads.** When the engine sets a property (a scene or `.tres`
  loads, the inspector, `set("prop", value)`), each resource value it holds,
  alone or in an `Array` or `Dictionary`, is kept alive the way GDScript keeps
  it, until the engine sets the property again or frees the script. A copy you
  keep elsewhere (`cached = res`) stays valid after the property changes.
  Assigning the property from Kotlin, or removing an element from a
  `MutableList` property, does not release the old value at once: it is
  released at the next engine set of that property or when the script is freed.
- **Received objects.** A Godot object a script receives (a signal or callback
  argument, a lookup, a call's result) is valid while the object lives, on every
  platform, also after the script that obtained it is freed: a node another
  script stored in a `companion object`, or one a top-level initializer made,
  keeps working.

## 8. Statics and Singletons

```kotlin
val image = Image.create(64, 64, false, Image.Format.RGBA8)
if (Input.isActionPressed("jump")) { /* ... */ }
```

- A Godot **static method** is a member of the class's companion:
  `Image.create(...)`.
- An engine **singleton** is a Kotlin `object`: `Input.isActionPressed("jump")`,
  `Time.getTicksMsec()`. The singletons are the ones Godot lists. A singleton's
  `fromHandle` returns the object itself.

**`GD`** holds Godot's utility functions, one member per function, the same on
desktop, Android and iOS:

- Names are Godot's in camelCase (`deg_to_rad` is `degToRad`), except
  `printErr`, `printS`, `printT`, `printRaw`, `typeOf` (`typeof` is a Kotlin
  keyword) and `isNaN`. Parameter names are Godot's. `float` is `Double`, `int`
  is `Long`, a `Variant` is `Any?`.
- The generic `abs`, `sign`, `floor`, `ceil`, `round`, `lerp`, `clamp`, `wrap`,
  `snapped`, `max` and `min` have typed overloads: `Double` for all, and `Long`
  and `Int` where Godot has an int form (`abs`, `sign`, `max`, `min`, `clamp`,
  `wrap`). They call the typed functions (`maxf`, `wrapi`, ...), except
  `clamp`, which is GDScript's `clamp`: it sets the value to `min` if it is
  less, then to `max` if it is greater, so `GD.clamp(0.0, 3.0, 1.0)` is `1.0` as
  in GDScript. `clampf` and `clampi` keep Godot's typed result, `3.0`, when
  `min` is greater than `max`. Vectors, mixed types and three or more values
  take the `Any?` form, which returns what Godot returns
  (`GD.abs(Vector3(-1.0, 2.0, -3.0))` is a `Vector3`, as an `Any?`).
- `print`, `printErr`, `printS`, `printT`, `printRaw`, `printRich`,
  `printVerbose`, `pushError`, `pushWarning` and `str` take any number of
  values. Godot formats `null`, strings, booleans and numbers itself; any other
  value is passed as its `toString()`.
- Every other `Any?` parameter (`typeOf`, `hash`, `isSame`, `varToStr`,
  `varToBytes`, `weakref`, `typeConvert`, the `Any?` forms of `max`, `lerp`,
  `clamp`, ...) needs a value Godot can hold: `null`, `Boolean`, the numbers,
  `String`, `NodePath`, the value types, `RID`, a `GodotObject`, a `ByteArray`,
  or a `List` or `Map` of those. Any other Kotlin object throws
  (`Unsupported Variant value type` on desktop,
  `unsupported Variant argument type` on iOS); Kanama does not turn it into a
  string behind your back.
- `isInstanceValid` takes a `GodotObject?` and is safe on a wrapper whose
  object was freed. `isInstanceIdValid` takes a raw id.
- The random functions use Godot's one global generator on every platform,
  like GDScript and C#, so `seed()` from GDScript or Kotlin gives the same
  sequence. For strict determinism use a `RandomNumberGenerator` with its own
  seed, because anything else can draw from the global generator.

**`Mathf`** is the C#-style math object, the same on every platform: `lerp`,
`clamp`, `wrap`, `snapped`, `moveToward`, `smoothStep`, `pingPong`, `degToRad`,
`PI`, `TAU`, ... Where Godot's function is plain arithmetic, `Mathf` computes it
in Kotlin with no engine call and returns the same double as GDScript, bit for
bit; the transcendental functions (`sin`, `pow`, `exp`, ...) and the
float-to-int conversions (`roundToInt`, `floorToInt`) call `GD`. Each member
also has a `Float` overload that computes in `Double`.

## 9. Defaults and Overloads

- A Godot default argument is a Kotlin default argument when Kotlin can express
  it, including a few composite ones such as
  `Node3D.lookAt(target, up = Vector3.UP)`.
- A default Kotlin cannot express makes the parameter **required**:
  `CanvasItem.drawTexture(texture, position, modulate)` needs the `Color` Godot
  defaults to white. Every such parameter is listed under "Godot Defaults Not
  Expressed" in the
  [generator report](generated/wrapper-generator-report.md#godot-defaults-not-expressed).
  When a later Kanama gives one a default, your code still compiles.
- `GodotSignal.connect` and the other members Kanama writes by hand per
  platform have overloads instead of default arguments:
  `signal.connect(target, method)` and `connect(target, method, flags)`.
- Godot has no overloads. Kanama adds a few: a `String` overload beside each
  `NodePath` lookup on `Node` (`getNodeOrNull("Hud/Label")`) and on
  `GodotObject` (`setIndexed`, `getIndexed`). Godot's vararg methods are Kotlin
  `vararg` (`call(method, vararg args)`, `rpc`, `emitSignal`).

## 10. Collections

```kotlin
val area = self.requireAs<Area3D>("Hitbox")
val bodies: List<Node3D> = area.getOverlappingBodies()
val groups: List<String> = self.getGroups()
```

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

A method whose combination of types Kanama cannot pass is left out rather than
given a looser type; the
[Wrapper Generator Report](generated/wrapper-generator-report.md) lists each one
with the reason.

Godot's own methods of the types Kotlin represents itself are there too:
String's and PackedByteArray's as extension functions on `String` and
`ByteArray` in `net.multigesture.kanama.builtins`, NodePath's as `NodePath`
members, and Callable's and Signal's as `GodotCallable` and `GodotSignal`
members; see
[Strings, Node Paths and Bytes](../game-dev/godot-api.md#strings-node-paths-and-bytes).

A `List<String>` script property exports as `Array[String]` and reads back as
Kotlin strings, including a `PackedStringArray` value set in a scene.

### String, NodePath, ByteArray and Callable Methods

- **Godot's semantics.** Each String function runs Godot's implementation in
  the engine, so it answers what GDScript answers: positions and lengths
  (`godotFind`, `substr`, `unicodeAt`, `left`, `godotLength`) count Unicode
  characters like GDScript, where Kotlin's `indexOf` and `length` count UTF-16
  units. They differ only for characters such as emoji. Each call is one engine
  call that converts the text both ways, so in a per-frame loop over many
  strings Kotlin's own functions are cheaper. A lone UTF-16 surrogate arrives as
  `?`, and an embedded NUL ends the text. More examples:
  `"res://levels".pathJoin("one.tscn")`, `String.num(3.14159, 2)` (`"3.14"`),
  `"7".padZeros(3)`, `text.isValidFloat()`,
  `"{0} has {1} HP".godotFormat(listOf("Bob", 3))`.
- **The `godot` prefix.** Importing the package must never change an existing
  Kotlin call, so a Godot method whose name Kotlin's `String` already has is
  named `godot` + the name: `godotLength()`, `godotFind(...)`,
  `godotCount(...)`, `godotSplit(...)`, `godotFormat(...)`, `godotIndent(...)`,
  `godotCapitalize()`, `godotHexToInt()`, `godotToInt()` and `godotToFloat()`.
  The last two parse leniently, like GDScript: `"12abc"` is 12. The methods
  Kotlin answers identically are not repeated: `isEmpty()`, `contains(...)`,
  `startsWith(...)` (`begins_with`), `endsWith(...)`, `replace(...)`,
  `repeat(n)`, `parts.joinToString(sep)` (`join`) and
  `removePrefix`/`removeSuffix` (`trim_prefix`/`trim_suffix`).
- **`NodePath`** members follow Godot's own parsing:
  `NodePath("Arm/Hand:position:x").getName(1)` is `"Hand"`, `getSubname(1)` is
  `"x"`, plus `getNameCount()`, `getConcatenatedNames()`,
  `getConcatenatedSubnames()`, `isAbsolute()`, `isEmpty()`, `slice(begin, end)`,
  `getAsPropertyPath()` and `hash()`. As in Godot, an invalid path (`"a::b"`) is
  the empty NodePath and an index out of range answers `""`, each with Godot's
  error printed. The error prints when a member first reads the path, where
  GDScript prints it when the NodePath is built.
- **`ByteArray`** gets the PackedByteArray methods Kotlin arrays lack:
  `decodeU8` ... `decodeS64`, `decodeHalf`/`decodeFloat`/`decodeDouble`, the
  `encode*` twins, `bswap16/32/64`, `hexEncode()`, `toInt32Array()` ...
  `toColorArray()`, `getStringFromUtf8()`/`Utf16`/`Utf32`/`Ascii`/`Wchar`,
  `compress(mode)`, `decompress(size, mode)`, `decompressDynamic(max, mode)`,
  `decodeVar(offset)` and `decodeVarSize`/`hasEncodedVar`. Where Godot prints an
  error and returns 0 or an empty array, the Kotlin function throws:
  `IndexOutOfBoundsException` for an offset outside the array,
  `IllegalArgumentException` for a `to*Array()` of a size that is not a
  multiple of the element and for a `bswap*` count past the end. For
  `encode_var`, `GD.varToBytes(value).copyInto(bytes, offset)` is the same
  encoding. The other packed arrays' `to_byte_array` is `toByteArray()` on
  their list types (`listOf(1, 2).toByteArray()`).
- **`GodotCallable(target, "method")`** has Godot's Callable methods for an
  object and a method name: `call(...)`, `callv(list)`, `callDeferred(...)`,
  `isValid()`, `getObject()`, `getObjectId()`, `getArgumentCount()`, `rpc(...)`
  and `rpcId(peer, ...)`. `rpc` on a target that is not a `Node` is a call
  error, as in Godot. `getObjectId()` answers the id after the target is freed,
  as Godot's does. A `GodotCallable` carries no extra bound arguments, so
  `bind`/`unbind` and the bound-argument queries are not offered. A
  `GodotSignal` (`node.signal("name")`, or a typed signal's handle) adds
  `isConnected(target, "method")`, `getConnections()`, `hasConnections()`,
  `getObject()` and `getObjectId()` to `connect`/`disconnect`/`emit`.

## 11. Handles and Factories

```kotlin
val material = StandardMaterial3D.create()     // owned: close() it when done
val resource = ResourceLoader.load("res://mesh.tres")
val mesh = resource?.let { ArrayMesh.fromResource(it) }    // null if it is not an ArrayMesh
val collider: GodotObject = self.requireAs<Node>("Collider")
val body = collider.castOrNull<Node3D>() ?: return
```

- `GodotHandle` is the identity of a Godot object. Every class has a public
  constructor from it, but that constructor does not check the class:
  `CharacterBody3D(body.handle)` calls `CharacterBody3D` methods on whatever
  `body` really is.
- **Checked casts** re-type a wrapper safely: `body.castOrNull<CharacterBody3D>()`
  asks Godot and returns `null` when the object is another class, `cast<T>()`
  throws a `ClassCastException`, and `Node.requireAs<T>(path)` /
  `getNodeAs<T>(path)` check the node they find. The untyped
  `requireAs(path, ::Label)` and `getAsOrNull(path, ::Label)` do not check the
  class. A `RefCounted` result is a new wrapper with a reference of its own,
  like the `from*` downcasts: close it or let the garbage collector release it;
  the original is unaffected. Checked casts need no reflection, so they survive
  R8, Android's release shrinker. Web does not have them; see
  [Limitations](limitations.md#web).
- `X.fromHandle(handle)` wraps an existing object as a borrowed view
  ([Nullability](#6-nullability) has its return type).
- `X.create()` makes a new object, and the `from*` downcasts
  (`ArrayMesh.fromResource(res)`, `Mesh.fromObject(obj)`) return `null` when the
  object is not that class.

## 12. Names as Constants

```kotlin
val timer = self.requireAs<Timer>("Timer")
timer.connect(Timer.Signals.timeout, self, "onTimeout")
```

Every class with signals has `X.Signals`, one `const val` per signal holding
Godot's name: `Timer.Signals.timeout == "timeout"`,
`Node.Signals.treeExited == "tree_exited"`. On Web each class carries the same
constants, and typed handles for the signals it declares. The engine-wide
`MethodName`, `PropertyName` and `SignalName` objects hold every method,
property and signal name on desktop, Android and iOS; Web scripts use string
literals or `X.Signals`.

## 13. Platform Parity

**Desktop, Android and iOS** compile one API from one source, so a member exists
on all three or on none, and the compiler tells you. The exceptions are listed:

- members available on desktop and Android only, because iOS cannot pass their
  types: listed on [iOS Shape Gap](generated/ios-shape-gap.md);
- iOS-only conveniences, such as a form of
  `PhysicsDirectSpaceState3D.intersectRay` that takes nullable arguments.

**Web** has its own API, with the members the Web build supports rather than
every Godot method. It uses the same enum types and value names as native, the
same `meta: "required"` rule for returns, and `Double` for decimals including
value-type components. It differs from the native API in places, for example:

- a parameter named like a Kotlin keyword gets a different name: `internal` is
  `internalMode` on Web and `internalValue` natively;
- some object parameters differ in nullability, for example
  `Node.removeChild(node: Node?)` on Web;
- a few returns are non-null on Web where native is nullable: `Node.getTree()`
  is `SceneTree` on Web and throws outside a tree.

Code that must compile for Web and native should not rely on parameter names
where they differ, and should treat `getTree()` as nullable.

A difference is a compile error, never a different result.
`GodotObject.set(property, value)` is Godot's `Object.set` everywhere, like
GDScript's `set`: a path such as `"position:x"` names no property, so it changes
nothing; `setIndexed("position:x", value)` is the path form. A few of Web's own
`GodotObject` members differ from native, such as the missing
`get(property): Any?` and a `setDeferred` that takes only a `Boolean`; code
that uses them fails the Web build.
