# Calling Godot APIs

Kanama should feel familiar to Godot users coming from GDScript or C#, while
still being honest about the places where Kotlin/JVM and GDExtension have
different constraints. This section mirrors the Godot C# API documentation
topics and tracks the Kotlin equivalent.

## API Differences

Kanama exposes two layers:

- **Godot-shaped wrappers** — stay close to engine names and GDExtension
  signatures. Useful for version alignment and debugging.
- **Kotlin conveniences** — lower-camel names, Kotlin properties, extension
  helpers, and coroutine integration.

### Wrapped Classes

Kanama now promotes generated wrappers in broad slices rather than maintaining
a hand-written class list in this page. Use
[API Coverage](../reference/generated/api-coverage.md) for the current promoted
class/method totals and
[Wrapper Generator Report](../reference/generated/wrapper-generator-report.md) for the
generator reach and skip categories.

The rules the generated wrappers follow (names, properties, integer and decimal types, enums,
nullability, ownership, defaults, collections, platform parity) and how a change to them is
announced are listed in [Generated API Conventions](../reference/wrapper-conventions.md).

When writing gameplay code, prefer typed wrappers such as `Node`, `Node3D`,
`CharacterBody3D`, `Area3D`, `AnimationPlayer`, `Control`, `ResourceLoader`,
`Input`, and `Mathf` whenever they exist. For your own Kanama scripts, prefer
generated `*Methods`, `*Signals`, `*Rpcs`, and `*Names` helpers. Use
`GodotObject.call(...)` only at dynamic boundaries such as mixed
GDScript/Kanama interop or APIs that are not wrapped yet.

### Generated KDoc

Public Godot-backed wrappers and builtin value types carry generated KDoc
imported from Godot's official `doc/classes/*.xml` files. In IntelliJ, this
means wrapped Godot APIs show useful tooltips and documentation while writing
Kotlin scripts. Game projects do not need to run any documentation-generation
commands.

## Value Types

Builtin value types such as `Vector2`, `Vector3`, `Color`, and `Transform3D`
are immutable snapshots in Kanama. This intentionally makes copied Godot values
less error-prone: changing a component means creating a new value and assigning
it back to the Godot property, not mutating a hidden copy.

Every operator and method Godot declares for a value type exists in Kotlin
under its GDScript meaning, generated from `extension_api.json`:

- Operators are Kotlin operators: `a + b`, `v * 2.0`, `2.0 * v`, `-v`,
  `transform * point`, `basis * otherBasis`, `quaternion * vector`,
  `Vector2i(3, 4) * 2`, `Vector2i(3, 4) * 0.5` (a `Vector2`, as in GDScript),
  `color * 0.5`, `transform * points` (a `List<Vector3>`), and `<`/`>` on
  vectors (component by component, as Godot compares). `point * transform` is
  Godot's inverse transform (`xform_inv`), as in GDScript. `2.0 * v` and
  `points * transform` are extension operators: import
  `net.multigesture.kanama.types.times` (the IDE offers it) or the package with
  `net.multigesture.kanama.types.*`.
- Methods are camelCase: `v.directionTo(target)`, `v.snapped(step)`,
  `basis.getEuler()`, `Projection.createPerspective(...)`,
  `Color.fromHsv(h, s, v)`, `aabb.intersectsRay(from, dir)` (`null` when
  GDScript returns `null`). Getters keep `get`: `rect.getCenter()`,
  `transform.getRotation()`; `Transform2D.get_origin()` is the `origin`
  property.

Operators and every method whose Godot implementation is plain arithmetic
(`abs`, `floor`, `round`, `min`/`max`, `clampf`, `lerp`, `moveToward`,
`slide`/`bounce`/`reflect`, `project`, `limitLength`, `Rect2`/`Rect2i`/`AABB`
`hasPoint`/`intersects`/`encloses`/`merge`/`grow*`, `Plane.project`,
`Transform2D.inverse`/`translated`, `Color.lerp`, ...) run in Kotlin with
Godot's own formulas at Godot's width, so their results are Godot's to the bit
and cost no engine call. Every other method (`angle`, `rotated`, `slerp`,
`Basis.getEuler`, `Color.lightened`, ...) is computed by the engine through an
allocation-free call, about as fast as the same call from GDScript. The runtime
smoke compares all of them with GDScript on every run, over random inputs and over
±0, NaN, ±INF and `.5` ties. Constants are the companion values Godot declares:
`Vector2i.LEFT`, `Vector3.MODEL_FRONT`, `Basis.FLIP_X`, `Plane.PLANE_XY`,
`Vector3.INF`, every named color (`Color.RED`, `Color.CORNFLOWER_BLUE`), and the
enums (`Vector3.Axis.X`).

Where Kotlin and GDScript differ:

- Integer division or `%` by zero (`Vector2i(1, 1) / 0`) throws
  `ArithmeticException`; GDScript reports a division-by-zero error.
- Godot's debug build checks some arguments and returns a default instead of
  computing (`slide`, `bounce`, `reflect` with a non-normalized normal;
  `quaternion * vector` and `vector * quaternion` with a non-normalized
  quaternion). These are Kotlin math and compute the formula without the check,
  which is what an exported (release) game does. Engine-computed methods
  (`slerp`, `rotated`, ...) report the same error as GDScript.
- Comparing vectors with a NaN component: Godot answers `false` to all of `<`,
  `<=`, `>` and `>=`. Kotlin's comparisons go through one `compareTo`, which sorts
  a NaN component last, so `<` and `<=` are `false` but `>` and `>=` are `true`.
- A float that becomes an integer (`Color.toHtml` on a channel of NaN, ±INF or
  beyond the `int` range): C++ leaves that conversion undefined, so Godot's own
  result differs by CPU (x86 and arm64 disagree). Kotlin's conversion is defined
  (NaN gives 0, out-of-range values saturate), which matches Godot on arm64
  (Apple silicon, phones). For finite channels every platform agrees.
- On Web (Kotlin/Wasm), the methods that run in Kotlin natively run the same
  Kotlin, with the same results; the engine-computed ones are not all there yet,
  and `angle()`, `rotated` and `slerp` are Kotlin approximations of Godot's
  (rounded to `real_t`, not guaranteed to the bit).

Their components are `Double`, like every other decimal in the API (`Vector3.x`,
`Color.r`, `delta`, scalar arguments), so no `.toFloat()`/`.toDouble()` is
needed between them. Like GDScript, a value type stores its components at
Godot's width (float32 in normal Godot builds; `Color` is always float32), so
`node.position = v; node.position == v` holds, `print(v)` shows `(0.1, 0.2)` as
GDScript does, and vector arithmetic gives Godot's results. The GDScript caveat
applies too: after `v = Vector2(0.1, 0.2)`, `v.x == 0.1` is `false`, so compare
decimals with `isEqualApprox`. See [Decimals](../reference/wrapper-conventions.md#4-decimals).

Node lookup helpers: `getNodeOrNull`, `getAsOrNull(path, ::Class)`,
`getNodeAsOrNull(path, "ClassName", ::Class)`, `requireAs(path, ::Class)`.
String and `NodePath` overloads available for all lookup helpers.

Deferred mutation: `GodotObject.setDeferred(property, value)`.

Interop with GDScript autoloads: `GodotObject.call(method, vararg args)` —
see the GDScript interop section below.

`GodotObject.call(method, vararg args)` is available as a mixed-project
interop path. The supported argument and return types differ by backend:

- **Desktop and Android** accept `null`, `Boolean`, `Int`/`Long`,
  `Float`/`Double`, `String`, a typed Godot enum value, every builtin value type
  (`Vector2`/`Vector3`/`Vector4`, their `i` variants, `Rect2`, `Rect2i`, `AABB`,
  `Plane`, `Quaternion`, `Basis`, `Transform2D`, `Transform3D`, `Projection`,
  `Color`), `NodePath`, `RID`, `ByteArray` (`PackedByteArray`), `List`
  (`Array`), `Map` (`Dictionary`, string keys) and `GodotObject`/`Resource`
  arguments. Returns decode `null`, scalars, `String`, the same value types,
  `NodePath`, `RID`, every `Packed*Array`, `Array` (as `List`), `Dictionary`
  (as `Map`) and objects; an object return is a non-owning `GodotObject` wrapper.
- **iOS** is narrower: arguments are `null`, scalars, `String`, a typed enum
  value, `Vector2`, `Vector2i`, `Vector3`, `Color`, `NodePath`, `RID`, `List`,
  `Map` and `GodotObject`; returns decode `Boolean`, `Long`, `Double`,
  `NodePath`, objects, `PackedStringArray`, `Vector2`, `Vector2i`, `Vector3`
  and `Color`, and any other return type comes back as `null`. An unsupported
  argument type throws.

This is intended for cases such as calling a GDScript autoload while porting a
project incrementally:

```kotlin
Autoloads.Audio.call("play", "res://sounds/jump.ogg")   // GDScript: Audio.play("res://sounds/jump.ogg")
```

For built-in Godot names used at dynamic boundaries, Kanama generates
engine-wide constants from `extension_api.json`:

```kotlin
Autoloads.Audio.call(MethodName.play, "res://sounds/jump.ogg")
player.signal(SignalName.treeExited)
self.getTree()?.setGroup("enemies", PropertyName.visible, false)
```

Script-local generated names such as `PlayerNames.Methods.onBodyEntered` remain
the right choice when Godot APIs need a method, property, or signal name. When
Kotlin code is invoking another Kanama script method directly, prefer the
generated `PlayerMethods.damage(...)`-style helpers instead of string dispatch.

Project autoloads are the generated `Autoloads.<Name>` properties, typed to the
autoload's Kotlin script, scene root class or `extends` class and resolved at
`/root/<Name>` like GDScript's global names (see [Scripts → Autoloads](scripts.md#autoloads)).
`Engine.getSingleton()` is for engine singletons; project autoloads are nodes, not
engine singletons.

## Godot Enums and Bitfields

Every Godot enum and bitfield is a typed Kotlin value class (since 0.5, task 128). A class enum is
nested in its class, a global (`@GlobalScope`) enum is top-level, and the values are named
constants on the type:

```kotlin
self.setProcessMode(Node.ProcessMode.ALWAYS)
Input.setMouseMode(Input.MouseMode.CAPTURED)
if (key.getKeycode() == Key.ESCAPE) { /* ... */ }
tween.tweenProperty(icon, "modulate", Color.WHITE, 0.2).setTrans(Tween.TransitionType.SINE).close()
val err: GodotError = ResourceSaver.save(scene, path)
if (err != GodotError.OK) GD.printErr("save failed: ${err.value}")
```

- **Names.** The type is Godot's enum name (`Node.ProcessMode`, `BaseMaterial3D.Flags`,
  `Vector3.Axis`). Four globals are renamed where Godot's name would collide: `Error` is
  `GodotError` (Kotlin imports its own `Error` into every file), `PropertyHint` is
  `GodotPropertyHint` (Kanama's annotation constants keep `PropertyHint`), and Godot's
  `Variant.Type` / `Variant.Operator` are `VariantType` / `VariantOperator`. (Kanama's runtime
  also has `net.multigesture.kanama.binding.runtime.VariantType`, an internal marshalling enum;
  scripts use the `net.multigesture.kanama.api` one.)
- **Values** drop the enum's common prefix, as Godot's C# bindings do, and keep Godot's
  SCREAMING_CASE: `PROCESS_MODE_ALWAYS` is `Node.ProcessMode.ALWAYS`, `KEY_ESCAPE` is
  `Key.ESCAPE`. A value whose remainder would start with a digit keeps a word (`Key.KEY_0`), and an
  enum without a common prefix keeps Godot's names (`GodotError.ERR_FILE_NOT_FOUND`). The prefix of
  each enum is frozen, so a later Godot adding a value never renames the existing ones.
- **Raw numbers** stay available: `ProcessMode(3L)` builds a value Godot has but this Kanama does
  not name yet, and `.value` reads the `Long` back (for logs, `call()` interop or arithmetic).
  `toString()` prints `ProcessMode(value=3)`.
- **Bitfields** combine with `or`, `and`, `xor`, `inv()` and test with `in`:
  `val f = Control.SizeFlags.EXPAND or Control.SizeFlags.FILL`, `if (Control.SizeFlags.FILL in f)`.
  Godot names no zero for most bitfields; `X(0L)` is the empty set (for example
  `GodotObject.ConnectFlags(0L)`, the default of `connect`).
- **Dynamic calls** (`call`, `set`, `callDeferred`, `emitSignal`, `ConfigFile.setValue`, Array and
  Dictionary elements) accept the typed values: every enum implements `GodotEnumValue`, and the
  Variant encoder passes it as the INT it stands for, so `node.set("process_mode",
  Node.ProcessMode.ALWAYS)` works. What comes BACK from a dynamic path (`get`, `call`,
  `ConfigFile.getValue`, Variant returns) is a `Long`; wrap it with `ProcessMode(raw as Long)` when
  you need the type.
- **Script members.** A Godot enum is a valid `@Export` type (an `int` export with
  Godot's enum or flags hint, storing the Godot value), `List<...>` of one, a registered function's
  parameter or return, a `@Signal` argument and an `@Rpc` argument; GDScript callers see the
  `int`. See [Exports and Resources](properties-resources.md#godot-enums-and-bitfields) and
  [Signals](signals.md#custom-signals).
- **Engine virtuals** whose parameters or return Godot types as an enum take the value class:
  `@OverrideVirtual fun _get_shader_mode(): Shader.Mode`. A `Long` there fails the build and names
  the type (see [Overriding Engine Virtuals](scripts.md#overriding-engine-virtuals)).
- **Required returns.** An object return Godot marks `meta: "required"` (`Node.createTween()`,
  `SceneTree.getRoot()`, the `Tween`/`Tweener` fluent setters, `CanvasItem.makeInputLocal`, ...)
  is non-null, so chains use `.` rather than `?.`. A null from the engine there is an engine bug
  and throws `IllegalStateException("Godot returned null from required <Class>.<method>")`.

Upgrading from 0.4: `scripts/migrate_enum_constants.py <kotlin-src>` rewrites the old
`Node.PROCESS_MODE_ALWAYS`-style constants in a source tree and lists what needs a human; the full
table is [Enum Constant Migration](../reference/generated/enum-migration.md).

## Collections

Godot collections only matter when data crosses the engine boundary. For pure
Kotlin game logic, prefer Kotlin/JDK collections (`List`, `MutableList`, `Map`,
and arrays) because they avoid per-element engine marshalling.

Current Kanama collection support is intentionally narrow:

- `PackedStringArray` is exposed as `List<String>` in wrappers such as
  `DirAccess.getFilesAt`, `ResourceLoader.listDirectory`, and
  `ProjectSettings.getChangedSettings`. Scene-authored `PackedStringArray` and
  `Array[String]` values also decode to `List<String>` when they pass through
  generic Variant paths such as `@Export` setters.
- `PackedByteArray` is exposed as `ByteArray` in `FileAccess` byte helpers.
- Selected object arrays are exposed as non-owning Kotlin wrapper lists, such
  as `Area3D.getOverlappingBodies()` and `Area3D.getOverlappingAreas()`.
- Scalar `Dictionary` values are exposed as `Map<String, Any?>` where the
  wrapper knows the dictionary shape, such as `ProjectSettings` and selected
  singleton metadata calls. Exported `@Export` maps additionally
  register as typed `Dictionary` slots — see
  [Exporting Dictionaries](properties-resources.md#exporting-dictionaries).
- `Vector2` and `Vector3` include Kotlin-side arithmetic and common gameplay
  math helpers such as `length`, `normalized`, `dot`, `lerp`, distance, and
  length limiting; `Vector3` also includes `cross` and `rotated`.
- `Basis` and `Transform3D` expose Godot-style 3D transform math:
  `basis * vector`, `transform * vector`, `basis.determinant()`, and
  `basis.inverse()`. `Basis.x`, `Basis.y`, and `Basis.z` match Godot's public
  column-axis API, even though the GDExtension native memory block is packed as
  rows internally.

General public `Array`/`Dictionary` wrappers and broad Kotlin collection
conversion are intentionally conservative. For APIs outside the promoted
shapes above, use typed wrappers where available or call through explicit
dynamic Godot APIs at the boundary.

## Variant

Kanama already marshals the common scalar Variant-compatible types used by the
current API surface: `null`, `Boolean`, `Long`/`Int`, `Double`/`Float`,
`String`, selected vectors/transforms, `NodePath`, `RID`,
`PackedStringArray`, `PackedByteArray`, scalar `Dictionary`, and selected
object/resource handles where lifetime is explicit.

Use typed wrappers when possible. Kanama keeps the broad public `Variant` API
small because it must define ownership, object lifetime, enum handling, and
generic constraints clearly. This matters for APIs such as
`FileAccess.store_var`, `FileAccess.get_var`, `Node.rpc_config`, and broader
Object/Resource conversions.

## Resource Ownership

**`close()` means "release my reference", never "destroy this object".** It calls
Godot's `unreference()` and destroys the object **only** if that dropped the
count to zero. Every rule below falls out of that one sentence: closing is safe
exactly when someone else still holds a reference, and destructive only when you
are the last owner.

Reading a resource off a node and closing it:

```kotlin
val mesh = meshInstance.getMesh()   // refcount 2 — the +1 is yours
mesh?.close()                       // refcount 1 — mesh alive, the node unaffected
```

`getMesh()` does **not** create a new mesh; it hands you the same object with one
more reference. Close it to release that reference early; if you never do, it is
released late, once the garbage collector has dropped your wrapper (see
[A forgotten `close()`](#a-forgotten-close-is-a-late-release) below).

Compare a resource you created and never handed off:

```kotlin
val material = StandardMaterial3D.create()   // refcount 1 — you are the ONLY owner
material.close()                             // refcount 0 — destroyed; later use is a crash
```

Same call, different outcome, because the number of other owners differs.

### Which category is it?

| Category | What it covers | What to do |
|---|---|---|
| **Owned** | `X.create()`, `ResourceLoader.load…`, every `RefCounted`-typed method return **including plain getters**, **every element of a returned typed `Array` of `RefCounted`** (`getMaterials()`, `actionGetEvents()`, `getProcessedTweens()`, …), the `from*` downcasts (`Mesh.fromObject(...)`, `ArrayMesh.fromResource(...)`), the checked casts to a `RefCounted` class (`res.cast<Texture2D>()`, `castOrNull`), and `@Export` reads of resource-typed fields and collections | `close()` it, or `use { }`, to release it early — for a list, each element (`list.forEach { it.close() }`). Forgotten, it is released after the GC drops the wrapper |
| **Borrowed view** | A wrapper *you* build over a raw handle you already have: `Resource.fromHandle(...)`, a wrapper constructor (`Mesh(other.handle)`), a script's own `self` | Keep the wrapper you got the handle from while you use the view: the view takes no reference. `close()` on it releases nothing (debug builds warn) |
| **Engine-owned, live** | A running `Tween` (from `createTween()` or an element of `getProcessedTweens()`), anything living in the scene tree | To **stop** it use the Godot lifecycle (`kill()`, `queueFree()`): `close()` never stops it. Your wrapper of a tween is still an owned `+1` — close it when you stop using that wrapper (see below) |
| **Nodes and plain `Object`s** | Anything not `RefCounted` — no refcount exists, and `GodotObject` has no `close()` | `Node.queueFree()` |

**Tweens, precisely.** The `SceneTree` holds its own reference to every tween it
processes, so a tween runs until it finishes or is killed whatever your wrappers
do. `close()` on a tween wrapper releases only *your* reference — it never stops
the tween, and a closed wrapper cannot be called again. So: `kill()` to stop a
tween; `close()` (after `kill()`, or once you no longer call it) to release your
wrapper; and for the elements of `SceneTree.getProcessedTweens()`, which are
owned `+1`s of tweens that keep running, close each one when you are done
reading it — that does not affect the tweens.

Closing a getter's result is the common case, and it is safe precisely because
the node still holds its own reference. Handing an owned
wrapper to a sink (`setMesh`, `setStream`, `ResourceSaver.save`) does not move
it into the engine-owned row: the sink takes its own reference and yours is
still yours to close. In the demos corpus this table is what
`scripts/demo_parity_audit.py` enforces — it fails a `close()` on a borrowed
view or a live tween and never flags closing an owned return; the audit
conforms to this page, not the other way round.

`@Export` reads are **owned**: when the engine sets a property (a scene or
`.tres` loads, the inspector, `set("prop", value)`), each resource value it
holds, alone or in an `Array` or `Dictionary`, is kept alive the way GDScript
keeps it. The runtime takes one reference per value for the property and
releases it when the engine sets the property again or when Godot frees the
script instance, also when the garbage collector already dropped the script
object (that reference belongs to the resource or node, not to the Kotlin
object). The wrapper the Kotlin field holds has a reference of its own, so a
copy you keep elsewhere (`cached = res`) stays valid after the property changes;
close it or let the collector release it. You do not need to close a property
field you keep; you do close a temporary you read out of one and discard.
Assigning the property from Kotlin, or removing an element from a `MutableList`
property, does not release the runtime's reference to the old value: it is
released at the next engine set of that property or when the instance is freed.

### A forgotten `close()` is a late release

An owned wrapper you never close is not leaked: when the garbage collector drops
it, Kanama releases its reference on the main thread at the start of the next
frame, the same `unreference()` that `close()` makes. `close()` and `use { }`
stay the way to release **early** and at a moment you choose, so use them for
big resources (meshes, textures, audio) and in loops; the fallback is what keeps
a forgotten getter result from staying alive until shutdown.

- **Only owned wrappers** are released this way. A borrowed view
  (`fromHandle`, a wrapper constructor, values read through `call`/`get`/`getMeta`)
  is never released, because its reference was never yours.
- **Only wrappers made on the engine main thread.** A wrapper you get on a worker
  thread (a `Dispatchers.Default` coroutine, your own `Thread`) is still owned,
  but nothing releases it for you: the worker may still be in a call through it
  when the main thread would release it. Close those (`use { }`), or hand the
  result back to the main thread first ([Threads](scripts.md#threads)).
- **Never twice.** `close()` cancels the fallback, so a wrapper you closed is not
  released again when it is collected.
- **On the main thread.** The collector only queues the release; Godot is called
  from the frame loop, never from the collector's thread. How soon depends on
  when the JVM (or Kotlin/Native) collects: it is not a deadline, and a game that
  allocates little may not collect for a long time. Measured on an Apple M1 Max
  (desktop JVM, 600 frames of about 7 ms): dropping 200 owned getter results a
  frame, one collection ran and a dropped wrapper waited about 260 frames on
  average; dropping 5 a frame, no collection ran at all, so nothing was released
  in those 10 seconds. Releases run within about 1 ms per frame (the rest waits
  for the next frame), and above 100,000 waiting wrappers new ones stop
  registering and a warning names the setting below: close what you make in a
  loop.
- **Native memory is invisible to the collector.** A wrapper is a few dozen
  bytes on the Kotlin heap; the image, mesh or audio it holds lives in Godot's
  memory, which the collector does not see. Dropping a few large resources does
  not make the JVM (or Kotlin/Native) collect any sooner, so their release can be
  much later than their size suggests. Close big resources yourself.
- **At shutdown**, before Godot's leak report, Kanama runs a collection and
  releases what it finds, again while a round still releases something (a freed
  resource can free the resources its properties held), so a dropped wrapper no
  longer shows up as `Leaked instance` at exit. A wrapper you still hold in a field is still yours.
- **Which sites rely on it:** turn on the project setting
  `kanama/debug/log_gc_releases` (off by default) and every release made by the
  collector is logged once per creation site:
  `released by GC: Mesh (created at Player.kt:42)` — the line to wrap in
  `use { }`.

Desktop, Android 13+ and iOS run the same mechanism; the desktop runtime smoke
checks it on every run, while the Android and iOS device runs are still to be
recorded. Android 8-12 debug installs have no `java.lang.ref.Cleaner`, so there
`close()` stays the only release (Kanama's Android release builds need
Android 13 anyway). Web keeps the explicit rule for
now, and its `RefCounted` is not `AutoCloseable` (see the Web note in
[Properties and Resources](properties-resources.md)). `KANAMA_GC_RELEASES=0` in
the game's environment turns the fallback off, for measurements.

### A script object keeps its resource alive

Holding the Kotlin object of a resource's script keeps the resource alive, as
holding the resource does in GDScript. This works whatever happened to the
wrapper you reached it through:

```kotlin
// Keep only the script object; the loaded wrapper is dropped (or closed).
map = ResourceLoader.load("user://map.res")?.kotlinScriptInstance<DataMap>() ?: return
// ... frames later, after any number of collections:
Resource.fromObject(GodotObject(map.godotObject))?.use { ResourceSaver.save(it, "user://map.res") }
```

and the same holds for `newScriptInstance<T>().instance` kept without its
handle. One edge: if the collector drops the script object and the engine
loads the resource from its cache again before the next frame, the script
object is rebuilt on its first use and re-reads its values from the
resource's file, which is what GDScript's re-parse would give. Three details
follow from that:

- re-reading the file loads an uncached copy of the resource, so the script
  class's constructor (and `init` blocks) runs once more for that copy, besides
  once for the rebuilt script object;
- if the file changed on disk since the resource was loaded, the result is
  mixed: the resource's engine properties keep their in-memory values, its
  script properties come from the file;
- a resource that was never saved, or a sub-resource stored inside another
  file (`res://level.tres::Resource_x`), is not re-read: its script object
  starts from its defaults, with a warning.

Kanama follows C#'s model: the script object holds a reference on its
resource, the engine keeps the script object alive while it holds the resource
itself, and once only your code can reach the script object, dropping it
releases the resource like any other forgotten wrapper.

This needs the script class to extend `KanamaScript`. A plain script class
(`@ScriptClass class Data(val godotObject: GodotHandle)`) has nowhere to keep
that link, so a resource carrying one keeps the lifetime it had before the
garbage-collector fallback: wrappers you close still release at once, but a
forgotten wrapper of it is not released until its script is detached
(`setScript(null)`) or the game shuts down. Keeping only its script object is
therefore safe, at the price of that resource staying alive. Extend
`KanamaScript` for resources you create and drop often.

### Two cleanups in one function

Nodes are **not** reference-counted, so `close()` does not apply to them at all.
Saving part of a scene therefore needs two different cleanups in the same
function — one per category:

```kotlin
val scene = PackedScene.create()   // refcount 1 — you are the only owner
scene.pack(someNode)               // serializes someNode and its OWNED sub-nodes
ResourceSaver.save(scene, "user://thing.tscn")
scene.close()                      // refcount 0 — released; the file is already written
someNode.queueFree()               // a Node: no refcount, free it through Godot
```

`ResourceSaver.save` does not take ownership of the scene — the wrapper is still
live after it returns, which is what makes `close()` yours to call (and what
issue #81 got wrong; `scripts/runtime_smoke.sh` asserts the refcount and liveness
after `save` precisely to keep that fixed).

Two traps worth knowing:

- **`pack()` serializes only *owned* sub-nodes.** A node you built and parented
  but never gave an owner (`child.setOwner(root)`) saves as a bare root with
  nothing under it.
- **`queueFree()` is the only exposed free path** for nodes — `Object.free()` is
  not wrapped, deliberately.

### Temporaries handed to a node

When you load a temporary resource and hand it to a
Godot node, release the temporary wrapper with `use` or `close` after the node
has accepted the value:

```kotlin
ResourceLoader.loadAudioStream("res://sounds/jump.ogg")?.use { stream ->
    player.setStream(stream)
}
```

This mirrors GDScript's `stream = load("res://sounds/jump.ogg")`: the node
keeps its own reference, while the local temporary reference is released when
the assignment is done. Kotlin does not have GDScript's deterministic local
reference cleanup: without `use { }` the temporary is released only once the
garbage collector drops it ([a late release](#a-forgotten-close-is-a-late-release)).

Do not register `RefCounted` values as engine singletons. Godot 4.7 preview builds
warns for `Engine.register_singleton` with `RefCounted` instances because the
engine singleton table stores a raw `Object*`, not a `Ref<>`. Kanama's
`Engine.registerSingleton` wrapper rejects this shape before calling Godot; use
an `Object`-derived singleton instead.

Prefer convenience APIs when they exist. For audio players, use:

```kotlin
player.setStreamFromPath("res://sounds/jump.ogg")
player.play()
```

The same rule applies to other returned `RefCounted` helpers, such as
`Tween.tweenProperty(...)` and `Tween.tweenCallback(...)`: if you do not keep
the returned wrapper, close it after configuring it.

Resource-returning getters follow the same rule. For example,
`TextureRect.texture`, `Sprite2D.texture`, and `AudioStreamPlayer.getStream()`
return closeable wrappers. If you only need to inspect the value, close the
temporary wrapper immediately:

```kotlin
val current = crosshair.texture   // refcount 2 — the +1 is yours
check(current != null)
current?.close()                  // refcount 1 — the node keeps its own
```

For gameplay animation, prefer `node.createTween()` over
`SceneTree.createTween()` when the tween belongs to a node. Godot binds tweens
created this way to the node, so they stop processing when the node leaves the
tree and are killed when the node is freed. Use `SceneTree.createTween()` only
for tweens that intentionally outlive any particular node, or bind them
explicitly with `Tween.bindNode(node)`. Both are members, with no import, on
every platform: `node.createTween()`, `getTree().createTween()`, and the
companion forms `SceneTree.createTween()` / `SceneTree.getProcessedTweens()`.

For `@Export` fields, Kanama-generated registrars release closeable
property wrappers when Godot frees the script instance. Mutable script
properties also release the previous closeable wrapper before accepting a new
value from Godot. This covers retained exported resources such as
`PackedScene?`, `Texture2D?`, and `List<Texture2D>`. `List<String>` script
properties export as `Array[String]` and read back as Kotlin strings, including
scene-authored `PackedStringArray` values.

## Inspector Properties and Signals

This page focuses on the Godot API wrapper surface. For inspector-visible
script data, see [Exports and Resources](properties-resources.md). For custom
signals, scene connections, and lambda callbacks, see
[Signals and Callbacks](signals.md).

## Global Classes

Kanama supports globally named classes with `@GlobalClass` (GDScript `class_name`).

```kotlin
@ScriptClass(attachTo = "Node")
@GlobalClass
class Player(godotObject: GodotHandle) :
    KanamaScript<Node>(godotObject, ::Node)
```

This is the Kotlin equivalent of Godot's named script/global class concept and
is intended to make classes easier to find in editor-facing workflows.

Use conservative, case-sensitive file/class naming for global classes. Kanama
supports the generated metadata path today; editor-facing global-class behavior
and typed exported global-class references are advanced usage
until the surrounding object/Variant lifetime rules are broader.

## Coverage References

Use [API Coverage](../reference/generated/api-coverage.md) and
[Wrapper Generator Report](../reference/generated/wrapper-generator-report.md) as the
source of truth for promoted wrapper availability. Dynamic container APIs,
ownership-sensitive object returns, and convenience helpers are promoted when
their Kotlin surface is explicit enough to be stable.
