# Calling Godot APIs

If you know Godot's API, you know Kanama's. Every Godot 4.7 class has a Kotlin
class of the same name, with the same methods, properties and signals. This
page shows how a Godot name becomes a Kotlin name, and the five places where
Kotlin behaves differently from GDScript: `self`, immutable vectors, number
types, typed enums, and `close()`.

## From Godot Names to Kotlin Names

Take a GDScript line and its Kotlin twin:

```gdscript
if Input.is_action_pressed("jump") and is_on_floor():
    velocity.y = jump_velocity
    $Hud/Label.text = "jump"
```

```kotlin
if (Input.isActionPressed("jump") && self.isOnFloor()) {
    self.velocity = self.velocity.withY(jumpVelocity)
    self.requireAs<Label>("Hud/Label").text = "jump"
}
```

The rules behind it:

- **Methods** are Godot's `snake_case` in `camelCase`: `move_and_slide()` is
  `moveAndSlide()`, `get_node_or_null()` is `getNodeOrNull()`.
- **Properties** are properties: `node.position`, `label.text`,
  `body.velocity`, readable and assignable. The accessor methods still exist
  (`getPosition()`, `setPosition(...)`) if you prefer them.
- **Singletons** are Kotlin objects: `Input.isActionPressed(...)`,
  `Time.getTicksMsec()`, `Engine.isEditorHint()`.
- **Static methods** sit on the companion: `Image.create(...)`.
- **Signals** are properties too: `timer.timeout`, `area.bodyEntered`. See
  [Signals and Await](signals.md).
- **`self`** is the node your script is attached to. Kotlin's `this` is the
  script object, so `isOnFloor()` on its own does not compile; write
  `self.isOnFloor()`. [Writing Scripts](scripts.md#this-is-not-your-node)
  explains why.

Your IDE shows Godot's own documentation on every method, so you rarely need
to leave IntelliJ. If a method you expect is missing, the
[API coverage](../reference/generated/api-coverage.md) page says why, and
[API Conventions](../reference/wrapper-conventions.md) lists every naming rule
with its exceptions.

## Numbers

Godot's `int` is `Long` and `float` is `Double`, as in GDScript, where both
are 64-bit:

```kotlin
val hp: Long = 100
val speed: Double = 5.5
val count: Int = self.getChildCount()       // Godot declares this one 32-bit
self.position = self.position + Vector2(speed * delta, 0.0)
```

A method whose Godot signature is explicitly 32-bit takes and returns `Int`,
so expect both. Vector, transform and color components are `Double` too, so
`pos.x * delta` needs no conversion. Compare decimals with `isEqualApprox`,
not `==`: a vector stores its components at Godot's single precision, so
`Vector2(0.1, 0.2).x == 0.1` is `false`, exactly as in GDScript.

## Vectors and Other Value Types

`Vector2`, `Vector3`, `Color`, `Transform3D` and the other Godot value types
are immutable. You cannot write `velocity.y += 10`; you make a new value and
assign it:

```kotlin
// GDScript: velocity.y += gravity * delta
self.velocity = self.velocity.withY(self.velocity.y + gravity * delta)

val dir = (target - self.position).normalized()
self.position = self.position + dir * speed * delta
if (dir.length() > 0.0 && dir.isEqualApprox(Vector2.UP)) jump()
```

Every operator and method Godot declares exists with its GDScript meaning:
`a + b`, `v * 2.0`, `transform * point`, `-v`, `v.directionTo(target)`,
`rect.getCenter()`, `Color.fromHsv(h, s, v)`, and the constants `Vector2.ZERO`,
`Vector3.UP`, `Color.RED`. Integer arguments work without `.0`:
`Vector3(0, 1, 0)`. One difference from GDScript: dividing an integer vector
by zero throws an `ArithmeticException` instead of printing an error.

## Godot Enums and Bitfields

Every Godot enum is a type. `PROCESS_MODE_ALWAYS` is `Node.ProcessMode.ALWAYS`,
`KEY_ESCAPE` is `Key.ESCAPE`, and the compiler stops you from passing a plain
number where Godot wants a mode:

```kotlin
self.processMode = Node.ProcessMode.ALWAYS
Input.setMouseMode(Input.MouseMode.CAPTURED)
if (key.getKeycode() == Key.ESCAPE) quit()

val flags = Control.SizeFlags.EXPAND or Control.SizeFlags.FILL   // a bitfield
if (Control.SizeFlags.FILL in flags) { /* ... */ }

val err: GodotError = ResourceSaver.save(scene, path)
if (err != GodotError.OK) GD.pushError("save failed: $err")
```

The value names drop the enum's common prefix and keep the rest, the way
Godot's C# bindings do. Godot's `Error` is `GodotError` (Kotlin has its own
`Error`). A raw number is still reachable when you need it: `mode.value` reads
the `Long`, and `Node.ProcessMode(3L)` builds a value by number.

## Strings, Node Paths and Bytes

Godot's `String` is Kotlin's `String`, and Godot's string methods are
available on it after one import:

```kotlin
import net.multigesture.kanama.builtins.*

val ext = path.getExtension()                  // "png"
val folder = path.getBaseDir()                 // "res://art"
val id = "PlayerScore".toSnakeCase()           // "player_score"
val words = csv.godotSplit(",", false)
```

Where Kotlin already has a method of that name with a different meaning,
Godot's version gets a `godot` prefix: `godotSplit`, `godotFind`,
`godotLength`. Kotlin's own `startsWith`, `replace` and `joinToString` do what
`begins_with`, `replace` and `join` do, so they are not duplicated.
`NodePath` has Godot's path methods (`getName(1)`, `getSubname(1)`), and a
`ByteArray` has `PackedByteArray`'s (`decodeU32(0)`, `getStringFromUtf8()`).

## Null Values and Returns That Are Never Null

A method that returns an object returns a nullable type, because Godot
promises nothing about it:

```kotlin
val tree = self.getTree() ?: return            // SceneTree?
val label = self.getNodeOrNull("Hud/Label")    // Node?
```

The exceptions are returns Godot itself marks as required: `self.getTree()`'s
`getRoot()`, `node.createTween()`, and the fluent tween setters. Those are
non-null, so a chain reads `tween.tweenProperty(icon, "modulate", Color.WHITE, 0.2)
.setTrans(Tween.TransitionType.SINE)` with no `?.`. Inside a script, `self.tree`,
`self.viewport` and `self.parentNode` are the non-null forms of `getTree()`,
`getViewport()` and `getParent()`; they throw a clear error when the node is
not in the tree.

To check or narrow a type, ask Godot:

```kotlin
val cam = other.castOrNull<Camera3D>() ?: return     // GDScript: other as Camera3D
if (body.isScript<Player>()) { /* a node with your Player script */ }
```

## Calling Methods by Name

When the target is a GDScript object, or an API you reach dynamically, call it
by name:

```kotlin
Autoloads.Audio.call("play", "res://sounds/jump.ogg")   // a GDScript autoload
node.set("process_mode", Node.ProcessMode.ALWAYS)
val raw = node.get("process_mode") as Long               // dynamic reads come back untyped
```

`Autoloads.<Name>` is generated from your `project.godot`: a Kotlin autoload is
typed to its script class, a GDScript one to the class it extends. For engine
names that must be spelled as strings, `MethodName.play`, `PropertyName.visible`
and `SignalName.treeExited` are generated constants, so a typo is a compile
error. Between two Kotlin scripts, prefer a direct Kotlin call over `call()`;
[Writing Scripts](scripts.md#calling-another-script) shows how.

## Resource Ownership

A `Node` is freed by Godot: `queueFree()` it and forget it. A `Resource`, or any
`RefCounted`, is reference-counted, and a wrapper you hold is one reference.
**`close()` releases your reference. It never destroys an object someone else
still holds.** Everything else about resources follows from that sentence.

```kotlin
val mesh = meshInstance.getMesh()     // the node holds one reference, you hold another
mesh?.close()                         // yours is gone; the node's mesh is unaffected

val material = StandardMaterial3D.create()   // you are the only owner
meshInstance.setMaterialOverride(material)   // now the node holds one too
material.close()                             // release yours; the material lives on
```

`use { }` closes for you at the end of the block, so the usual pattern is:

```kotlin
ResourceLoader.loadAudioStream("res://sounds/jump.ogg")?.use { stream ->
    player.stream = stream
}
player.setStreamFromPath("res://sounds/jump.ogg")   // the same, in one call

FileAccess.open("user://save.txt", FileAccess.ModeFlags.WRITE)?.use { file ->
    file.storeLine("score=42")
}
val text = FileAccess.getFileAsString("user://save.txt")
val saves = DirAccess.getFilesAt("user://")
```

Close every file you open. In GDScript a file closes when its variable goes out
of scope; in Kotlin it stays open, with its writes unflushed, until you close
it. `close()` on a `FileAccess` closes the file and releases your reference.
Godot's own `close()` is `closeFile()`: it closes the file and keeps the object.

Which wrappers are yours to close:

| You got it from | It is | What to do |
|---|---|---|
| `X.create()`, `ResourceLoader.load…`, any getter that returns a resource (`getMesh()`, `getAnimation("walk")`), each element of a returned list of resources, a `cast<T>()` to a resource type | **owned**: a reference of your own | `close()` or `use { }` when you are done. Forgotten, it is released later, after the garbage collector drops the wrapper |
| `X.fromHandle(...)`, a wrapper constructor over a handle you already have, a script's own `self`, resource elements inside a container that arrived as a signal or method argument | **borrowed**: a view, no reference | never close it; keep the object you got the handle from while you use it |
| a running `Tween` from `createTween()`, anything in the scene tree | **engine-owned, live** | stop it through Godot (`kill()`, `queueFree()`); `close()` never stops it |
| a `Node` or any plain `Object` | not reference-counted | `queueFree()`; there is no `close()` |

Two consequences worth knowing:

- **A forgotten `close()` is a late release, not a leak.** When the collector
  drops the wrapper, Kanama releases the reference on the main thread at the
  next frame. The collector does not see the texture or mesh behind a wrapper,
  so a few big resources can stay alive far longer than their size suggests.
  Close big resources and anything you create in a loop. The project setting
  `kanama/debug/log_gc_releases` logs each release the collector had to make,
  with the line that created the wrapper.
- **An exported resource field is yours to keep.** `@Export var scene: PackedScene?`
  stays valid as long as the script instance does. Close only a temporary you
  read out of it and discard. A script object that extends `KanamaScript`
  also keeps its own resource alive, as in GDScript, so holding a `Weapon`
  script keeps the weapon resource usable
  ([details](properties-resources.md#keeping-a-resource-through-its-script)).

An object you receive, as a signal argument, a lookup or a call's result, stays
valid as long as the object lives. A handler can keep it (`lastHit = body`) and
use it frames later, and `body == player` is `true` when both are the same node.
A node you `queueFree()` stays usable until the end of the frame. Using an
object after Godot freed it throws `IllegalStateException` ("Invalid access to
previously freed instance"), as GDScript reports it; ask
`GD.isInstanceValid(node)` when it may be gone.

Saving part of a scene needs both cleanups in one function, one per kind:

```kotlin
val scene = PackedScene.create()
scene.pack(someNode)                          // only sub-nodes with an owner are saved
ResourceSaver.save(scene, "user://thing.tscn")
scene.close()                                 // the file is written; release your reference
someNode.queueFree()                          // a Node: free it through Godot
```

`queueFree()` is the only way to free a node from Kotlin; `Object.free()` is not
available.

## Global Functions: `GD` and `Mathf`

GDScript's global functions are members of `GD`, all of them, with Godot's
documentation:

```kotlin
GD.print("score: ", score)             // Godot's output panel, on every platform
GD.pushError("bad state: $value")      // the Errors tab, like push_error()
GD.seed(42)                            // Godot's global generator, same sequence as GDScript
val roll = GD.randiRange(1, 6)
val t = GD.inverseLerp(0.0, 10.0, 2.5)
if (GD.isInstanceValid(node)) node.show()
```

`typeof` is `GD.typeOf` (a Kotlin keyword), `deg_to_rad` is `GD.degToRad`. The
generic `abs`, `clamp`, `lerp`, `max` and `min` have typed overloads for
numbers; for vectors and mixed types they take `Any?` and return what Godot
returns. `Mathf` is the C#-style math object with the same functions as plain
Kotlin calls: `Mathf.lerp`, `Mathf.moveToward`, `Mathf.degToRad`, `Mathf.PI`.
Use whichever reads better; `GD.print` and `println` are not the same thing,
though. `println` goes to the process's stdout, which the editor does not show.

## Global Classes

`@GlobalClass` is `class_name`: the script appears in the editor's class list,
its resources show up in Create New Resource, and typed GDScript can name it.

```kotlin
@ScriptClass(attachTo = "Node")
@GlobalClass
class Player(godotObject: GodotHandle) :
    KanamaScript<Node>(godotObject, ::Node)
```

The file must be named after the class (`Player.kt`); the build warns when
they differ. One script can export another global class
(`@Export var target: Vehicle? = null`) and check for it with
`body.isScript<Vehicle>()`.

## On Other Platforms

Everything above works the same on desktop, Android and iOS: it is one API,
compiled from one source. On **Web**, every engine call crosses from
WebAssembly into the browser. A node's position and rotation are read from a
snapshot taken at the start of each `_process` and `_physics_process`, and
`castOrNull`, `requireAs<T>` and the other typed lookups are not available yet.
The [Web guide](../exporting/web.md#web-compatible-project-scripts) has the
rules for scripts that must run there.

[Limitations](../reference/limitations.md) is the one page that lists what
differs, per platform, with the workaround for each.
