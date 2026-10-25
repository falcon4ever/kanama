# Writing Scripts

A Kanama script is a Kotlin class that Godot attaches to a node, the way it
attaches a `.gd` file. This page shows what a script is made of, how Godot calls
into it, and how it reaches other nodes and scripts. For the Godot API itself,
see [Calling Godot APIs](godot-api.md).

## A Complete Script

```kotlin
package com.mygame

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.OnPhysicsProcess
import net.multigesture.kanama.annotations.OnReady
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.*
import net.multigesture.kanama.types.*

@ScriptClass(attachTo = "CharacterBody2D")
class Player(godotObject: GodotHandle) :
    KanamaScript<CharacterBody2D>(godotObject, ::CharacterBody2D) {

    @Export var speed: Double = 300.0                  // @export var speed = 300.0

    private val sprite by node<AnimatedSprite2D>("Sprite")   // @onready var sprite = $Sprite

    @OnReady
    fun ready() {
        sprite.play("idle")
    }

    @OnPhysicsProcess
    fun physicsProcess(delta: Double) {
        val dir = Input.getVector("move_left", "move_right", "move_up", "move_down")
        self.velocity = dir * speed
        self.moveAndSlide()
    }

    fun heal(amount: Long) { /* GDScript can call this as heal(10) */ }
}
```

- `@ScriptClass(attachTo = "CharacterBody2D")` says which node class the script
  extends, like GDScript's `extends CharacterBody2D`.
- `KanamaScript<CharacterBody2D>` gives the script a typed `self`, the node it
  is attached to.
- `@Export` puts a property in the inspector. See
  [Exports and Resources](properties-resources.md).
- `@OnReady` and `@OnPhysicsProcess` mark the functions Godot calls as
  `_ready` and `_physics_process`.
- Every public function, such as `heal`, can be called from Godot.

The snippets on this page and on [Calling Godot APIs](godot-api.md) are parts of
a script like this one. They use five packages, and IntelliJ adds the imports as
you type:

| Package | What it holds |
|---|---|
| `net.multigesture.kanama.annotations` | `@ScriptClass`, `@Export`, `@OnReady` and the other annotations |
| `net.multigesture.kanama.api` | the Godot classes, `GD`, `Mathf`, `KanamaScript` |
| `net.multigesture.kanama.types` | `Vector2`, `Color`, `Transform3D` and the other value types |
| `net.multigesture.kanama.builtins` | Godot's `String`, `NodePath` and `PackedByteArray` methods |
| `net.multigesture.kanama.generated` | what the build generates from your scripts: `Autoloads`, `PlayerMethods`, `PlayerSignals` |

## Adding a Script to a Node

Save the file under `kotlin-src/` in your Godot project, in a folder for its
package: `kotlin-src/com/mygame/Player.kt`. Every script file declares a
package, and is named after its class: `Player.kt` holds `class Player`,
because Kanama finds a script's class by the file name. Then press **Build Scripts** in the Godot toolbar (see
[Building Your Scripts](#building-your-scripts)) and attach the script as you
would a `.gd` file: drag `Player.kt` from the FileSystem dock onto the node, or
use **Attach Script** on the node and pick the file.

**Attach Script** can also create the file: choose **Kotlin** as the language,
and the editor writes a script for the node's class with a package already
filled in, the one most scripts in that folder use, or one derived from the
folder (`game` at the project root).

## Using `self`

In GDScript `self` is the node. In Kotlin, `this` is the script object, and the
node is `self`:

```kotlin
@OnPhysicsProcess
fun physicsProcess(delta: Double) {
    if (self.isOnFloor()) self.moveAndSlide()      // not isOnFloor()
}
```

The script object holds your fields and functions; the node is the Godot object
they act on. `selfAs` gives a second view of the same node as another class:

```kotlin
private val node2d = selfAs(::Node2D)
```

The constructor parameter is a `GodotHandle`, the identity of the Godot object
the script is attached to. Pass it on to `KanamaScript` and otherwise leave it
alone. See [Kotlin Style](style-guide.md#self-and-selfas-caching) for caching
`self` and `selfAs`.

`KanamaScript` is the only Kanama class you extend. The Godot classes (`Node`,
`Resource`, `AudioStream`, ...) are views onto Godot objects and cannot be
subclassed; the build stops you with a pointer to `@ScriptClass(attachTo = ...)`.
For your own resource types, see
[Custom Resources](properties-resources.md#custom-resources).

## Names and Types

| GDScript | Kotlin |
|---|---|
| `func move_and_slide()` | `fun moveAndSlide()` |
| `var my_property = 0` | `var myProperty: Long = 0` |
| `const MAX_SPEED = 10` | `const val MAX_SPEED = 10L` |
| `int` | `Long` |
| `float`, and `delta` | `Double` |
| `bool` | `Boolean` |
| `Vector3.x`, `Color.r` | `Double` |
| `Node.PROCESS_MODE_ALWAYS` | `Node.ProcessMode.ALWAYS` |

A function Godot can call takes and returns `Long` for an integer; `Int` there is
a build error. An exported property may also be `Int`. The engine's own methods
follow Godot's signatures, so a few of them take `Int` where Godot declares a
32-bit value. Vectors are immutable: `self.velocity = self.velocity.withY(0.0)`
instead of `velocity.y = 0`. [Calling Godot APIs](godot-api.md#numbers) covers
numbers, vectors and enums.

## Lifecycle Callbacks

GDScript overrides virtual methods. Kotlin marks a function with an annotation:

| GDScript | Kotlin annotation |
|---|---|
| `func _ready():` | `@OnReady` |
| `func _process(delta):` | `@OnProcess` |
| `func _physics_process(delta):` | `@OnPhysicsProcess` |
| `func _enter_tree():` | `@OnEnterTree` |
| `func _exit_tree():` | `@OnExitTree` |
| `func _input(event):` | `@OnInput` |
| `func _unhandled_input(event):` | `@OnUnhandledInput` |
| `func _shortcut_input(event):` | `@OnShortcutInput` |
| `func _unhandled_key_input(event):` | `@OnUnhandledKeyInput` |

The annotation is what Godot looks for, so the function name is yours; the
convention drops the underscore (`fun ready()`). A function named like a virtual
without its annotation (`fun _process(delta: Double)`) is a build error that
names the annotation to add.

The input callbacks receive a typed `InputEvent`. Cast it to the event you
handle with `castOrNull`:

```kotlin
@OnUnhandledInput
fun unhandledInput(event: InputEvent) {
    if (event.isActionPressed("ui_accept")) self.tree.reloadCurrentScene()
    val motion = event.castOrNull<InputEventMouseMotion>() ?: return
    self.rotation += motion.getRelative().x * 0.01
}
```

## Overriding Engine Virtuals

Any other engine virtual (`_get_aabb`, `_draw`, `_get_configuration_warnings`,
...) is overridden with `@OverrideVirtual`. Here the function keeps Godot's name,
underscore included, because the name is how the override is matched:

```kotlin
import net.multigesture.kanama.annotations.OverrideVirtual
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.api.*
import net.multigesture.kanama.types.*

@ScriptClass(attachTo = "Mesh")
class ProceduralMesh(godotObject: GodotHandle) : KanamaScript<Mesh>(godotObject, ::Mesh) {
    @OverrideVirtual
    fun _get_aabb(): AABB = AABB(Vector3.ZERO, Vector3(2.0, 2.0, 2.0))
}

@ScriptClass(attachTo = "Material")
class FogMaterial(godotObject: GodotHandle) : KanamaScript<Material>(godotObject, ::Material) {
    @OverrideVirtual
    fun _get_shader_mode(): Shader.Mode = Shader.Mode.FOG
}
```

The build checks the signature against Godot's, so a typo or a wrong parameter
type fails the build instead of never being called. Godot's `Array` is
`List<Any?>`, a `Dictionary` is `Map<String, Any?>`, a `StringName` is `String`,
and an enum or bitfield is its typed class (`Shader.Mode`, not `Long`). A return
Godot marks as required, such as the two `_get_space_state` virtuals, is declared
non-null.

## Exports

| GDScript | Kotlin |
|---|---|
| `@export var speed = 5.0` | `@Export var speed: Double = 5.0` |
| `@export_range(0, 10, 0.5) var speed = 5.0` | `@ExportRange(0.0, 10.0, 0.5) var speed: Double = 5.0` |
| `@export_file("*.png") var icon: String` | `@ExportFile("*.png") var icon: String = ""` |
| `@export_flags_3d_physics var mask: int` | `@ExportFlags3DPhysics var mask: Long = 0` |
| `@export var fov := PI / 3.0` | `@Export var fov: Double = Mathf.PI / 3.0` |
| `@export_group("Movement")` | `@ExportGroup("Movement")` on the first property of the group |
| `@export var scene: PackedScene` | `@Export var scene: PackedScene? = null` |
| `@export_tool_button("Rebuild")` | `@ExportToolButton("Rebuild")` on a function with no parameters |
| `@tool` | `@Tool` on the class |
| `class_name Player` | `@GlobalClass` on the class |

Every GDScript `@export_*` annotation has a Kotlin twin that exports on its own.
The full list is in [Exports and Resources](properties-resources.md#export-hints).

## Exposing Functions to Godot

As with a GDScript `func`, every public function of a script is visible to
Godot under its name in snake_case, for GDScript, `call()`, signal connections
and `Callable`s. No annotation is needed:

```kotlin
@ScriptClass(attachTo = "CanvasLayer")
class Hud(godotObject: GodotHandle) : KanamaScript<CanvasLayer>(godotObject, ::CanvasLayer) {
    fun showMessage(text: String) { /* ... */ }        // Godot: show_message

    @GodotName("_on_start_button_pressed")             // the name the editor saved in the .tscn
    fun onStartButtonPressed() { /* ... */ }

    private fun layout() { /* ... */ }                 // Kotlin only
    internal fun debugState(): Map<String, Any> = mapOf()   // Kotlin only
}
```

The rule: a public function must be one Godot can call, or the build stops and
names the problem. That means its parameters and return are `Long`, `Double`,
`Boolean`, `String`, `NodePath`, a value type, a Godot enum or a Godot object
(see [Limitations](../reference/limitations.md#every-platform) for the types
that are not passed), its snake_case name is not one the node already has
(`queue_free`, `get_name`), no other function lands on the same Godot name,
and it has no `vararg` parameter.
When the build stops, make the function `private` or `internal` if Godot never
calls it; your scripts build as one module, so other scripts can still call an
`internal` function. `@GodotName("...")` gives a function the exact Godot name
you need, such as one a saved signal connection uses.

A caller in Godot that leaves out trailing arguments with Kotlin defaults
(`call("spawn")` for `fun spawn(delay: Double = 0.5)`) gets the Kotlin defaults.
`suspend`, extension and generic functions are Kotlin-only even when public.

## Calling Another Script

A Kotlin script can call another script's public functions directly. KSP
generates a `<Script>Methods` object for each script, which works from a script
object or from any `GodotObject`:

```kotlin
import net.multigesture.kanama.generated.HudMethods
import net.multigesture.kanama.generated.PlayerMethods

// Player declares: fun damage(amount: Double)
fun onHit(player: Player, collider: GodotObject, hud: GodotObject) {
    PlayerMethods.damage(player, 5.0)              // a Player script object
    if (!PlayerMethods.damage(collider, 5.0)) {    // any GodotObject, from a collision or a lookup
        // the collider has no Player script
    }
    val label = HudMethods.currentLabel(hud) ?: return   // a return value is nullable
}
```

Use `call()` only when the target is a GDScript object; see
[Calling Methods by Name](godot-api.md#calling-methods-by-name). The `Methods`,
`Signals` and `Rpcs` objects appear in your IDE after a build, so build once
after adding a public function, a `@Signal` or an `@Rpc`.

## Signals

A `@Signal` function declares a signal; the build generates a typed handle for
it with the same name, which you emit as in GDScript:

```gdscript
signal hit_enemy(damage)
hit_enemy.emit(10)
```

```kotlin
import net.multigesture.kanama.generated.hitEnemy

@ScriptClass(attachTo = "Area2D")
class Sword(godotObject: GodotHandle) : KanamaScript<Area2D>(godotObject, ::Area2D) {
    @Signal
    fun hitEnemy(damage: Long) = Unit

    fun strike() {
        hitEnemy.emit(10L)
    }
}
```

A Godot enum works as a `@Signal` argument, an `@Rpc` argument, and a parameter
or return of a public function: GDScript sees the `int`, Kotlin sees the typed
value (`@Signal fun modeChanged(mode: Node.ProcessMode) = Unit`).
[Signals and Await](signals.md) covers connecting, emitting and awaiting, and
[Multiplayer](multiplayer.md) covers `@Rpc`.

## Node Lookup

GDScript's `@onready var timer: Timer = $ScoreTimer` is a `node<T>()` property:

```kotlin
private val scoreTimer by node<Timer>("ScoreTimer")
private val animationPlayer by node<AnimationPlayer>("Character/AnimationPlayer")
private val camera by node<Camera3D>("%Camera3D")          // scene-unique names work too
private val player by script<Player>("Player")             // the Kotlin script on that node
```

The node is looked up on the first read and kept, like an `@onready` variable,
so read it in `@OnReady` or later. After `request_ready()` and a new `_ready`,
the next read looks the node up again, as GDScript re-runs its `@onready` lines.
Every lookup also takes a `NodePath`. A failed lookup throws an
`IllegalStateException` that names the property:

- read too early: `Main.scoreTimer: node("ScoreTimer") was read before /root/Main was ready`;
- missing node: `Main.scoreTimer: no node at "ScoreTimer" under /root/Main`;
- wrong class: `Main.scoreTimer: node "ScoreTimer" is a Node2D, not a Timer`;
- `script<T>()` on a node without that script: `... has no Kotlin script, not Player`.

For a one-off lookup, `self.requireAs<Label>("Hud/Label")` throws the same way,
and `self.getNodeAs<Label>("Hud/Label")` returns `null` when the node is missing
or of another class, like GDScript's `get_node_or_null(path) as Label`. An
exported reference (`@Export var label: Label? = null`) is the inspector-wired
alternative.

## Casts and Script Checks

| GDScript | Kotlin |
|---|---|
| `var cam := x as Camera3D` | `val cam = x.castOrNull<Camera3D>()` (`null` when it is not one) |
| `var cam: Camera3D = x` | `val cam = x.cast<Camera3D>()` (throws `ClassCastException`) |
| `if body is Player:` | `if (body.isScript<Player>())` |
| `var p := body as Player` | `val p = body.asScript<Player>()` |

`castOrNull` and `cast` ask Godot what the object is, so they are right even for
a wrapper built by hand. Use them instead of `Node3D(other.handle)`, which calls
`Node3D` methods on whatever the object really is. A cast to a resource class
(`res.cast<Texture2D>()`) is a new reference of your own: keep it, or close it
when you are done, without affecting the original. `castOrNull` and `cast` take a
Godot class; `isScript` and `asScript` take your script class.

## Preload and Instancing

```kotlin
private val bulletScene by preload<PackedScene>("res://bullet.tscn")   // const BULLET = preload(...)
private val coinScene by preload<PackedScene>("res://coin.tscn")

fun shoot() {
    val bullet = bulletScene.instantiateAs<RigidBody3D>()   // the root node, class-checked
    val coin = coinScene.instantiateScript<Coin>()          // the Kotlin script on the root
    self.addChild(coin.self)
}
```

`preload` loads the resource on the first read and keeps it for the rest of the
game, shared by every script that preloads the same path, like a GDScript
`preload` constant; do not `close()` it. Give an absolute `res://` or `uid://`
path. A missing file or a resource of another class throws. `instantiateAs<T>()`
and `instantiateScript<T>()` free the instance and throw when its root is not
what you asked for.

## Tree Accessors

`self.tree`, `self.viewport` and `self.parentNode` are the non-null forms of
`getTree()`, `getViewport()` and `getParent()`:

```kotlin
self.tree.callGroup("mobs", "queue_free")
```

They throw an `IllegalStateException` (`Node "Sub" is not inside the tree`, or
`... has no parent`) where GDScript's `get_tree()` would fail, and Godot logs no
error of its own.

## Await

A script starts coroutines with `launch { }` and suspends in them the way a
GDScript function uses `await`:

| GDScript | Kotlin, inside `launch { }` |
|---|---|
| `await get_tree().create_timer(1.0).timeout` | `wait(1.0)` |
| `await get_tree().process_frame` | `nextFrame()` |
| `await $MessageTimer.timeout` | `messageTimer.timeout.await()` |
| `var body = await area.body_entered` | `val body = area.bodyEntered.await()` (typed: `Node3D`) |

```kotlin
private val messageTimer by node<Timer>("MessageTimer")
private val startButton by node<Button>("StartButton")

fun showGameOver() {
    launch {
        messageTimer.timeout.await()
        wait(1.0)
        startButton.show()
    }
}
```

Coroutines run on the main thread and stop when the script's node is freed, so
a coroutine never touches a freed node. Leaving the tree does not stop them, as
in GDScript; `cancelCoroutines()` does. `wait` uses a scene-tree timer with
GDScript's `create_timer` defaults: it keeps running while the game is paused and
follows `Engine.time_scale`. `wait(1.0, processAlways = false)` pauses with the
game, and `ignoreTimeScale = true` ignores the time scale. `scriptScope` is the
scope itself, for other `kotlinx.coroutines` builders. See
[Kotlin Style](style-guide.md#coroutines).

## Autoloads

GDScript reaches an autoload by its global name (`Audio.play(...)`). Kanama
generates the same names as properties of
`net.multigesture.kanama.generated.Autoloads`, one for each enabled autoload in
`project.godot`:

```kotlin
import net.multigesture.kanama.generated.Autoloads

Autoloads.Audio.call("play", "res://sounds/coin.ogg")   // GDScript: Audio.play("res://sounds/coin.ogg")
val settings = Autoloads.Settings                       // the Kotlin script on /root/Settings
settings.loadSettings()
```

| The autoload is | `Autoloads.<Name>` is typed as |
|---|---|
| a Kotlin script (`Settings="*res://kotlin-src/Settings.kt"`) | the script class |
| a scene (`MusicPlayer="*res://MusicPlayer.tscn"`) | the root node's Kotlin script, else the root's class (`AudioStreamPlayer`) |
| a GDScript (`Audio="*res://scripts/audio.gd"`) | the class it `extends` (`Node`); call into it with `call("play", ...)` |

The first read finds the node at `/root/<Name>`, as GDScript's global does, and
throws an `IllegalStateException` naming the autoload when the node is missing
or of another class. The build reads `project.godot` from the folder above your
script sources; a project laid out differently sets the `kanamaGodotProjectDir`
KSP option. `Engine.getSingleton()` is for engine singletons such as `Input`;
autoloads are nodes, not engine singletons.

In the editor, only autoloads whose script is `@Tool` are in the tree, as in
Godot, so check `Engine.isEditorHint()` before reading one from editor code. A
hot reload keeps each Kotlin autoload the same node, so `Autoloads.<Name>` and
any reference you hold keep working; what it keeps and resets is in
[Hot Reload Internals](../contributing/hot-reload-internals.md#autoloads-across-a-reload).

## Script Inheritance

A script class can extend another script class of your project, as a GDScript
script `extends` another one. The subclass has every member of the chain:
exports, signals, lifecycle callbacks, `@OverrideVirtual`s, tool buttons and
public functions, with no forwarding code:

```kotlin
@ScriptClass(attachTo = "Node3D")
@GlobalClass
open class Vehicle(godotObject: GodotHandle) : KanamaScript<Node3D>(godotObject, ::Node3D) {
    @Export var maxSpeed = 20.0
    @OnReady open fun ready() { /* ... */ }
    @OnPhysicsProcess fun physicsProcess(delta: Double) { effectBody(delta) }
    @GodotName("_on_sphere_body_entered") fun onSphereBodyEntered(body: GodotObject) { /* ... */ }
    protected open fun effectBody(delta: Double) {}
}

@ScriptClass(attachTo = "Node3D")
@GlobalClass
class VehicleMotorcycle(godotObject: GodotHandle) : Vehicle(godotObject) {
    override fun ready() {             // still the _ready callback: the annotation is inherited
        super.ready()
        /* ... */
    }
    override fun effectBody(delta: Double) { /* ... */ }
}
```

- **The most derived declaration wins.** Godot calls the override. A
  superclass's `private` member is not inherited.
- **Annotations follow the override.** An override without annotations keeps
  the ones it overrides (`override fun ready()` stays `_ready`, an override of an
  `@Export open var` stays exported). An override with its own annotations uses
  those.
- **Conflicts are build errors.** Two different functions of the chain on one
  Godot name (a base `@OnReady fun ready()` and a subclass `@OnReady fun setup()`),
  or two exports with one name, fail the build naming both. Override the base
  member instead of adding a second one.
- **Generic bases are typed for the subclass.** On `class Sub : Base<Long>()`, a
  base's `@Export var amount: T` is a `Long` export, and `override fun f(x: Long)`
  overrides `open fun f(x: T)`: one Godot method, not two.

The superclass may be a `@ScriptClass` or a plain, possibly abstract, class in
the same build. A class from a library contributes nothing, because annotations
are read from source; the build warns and names it. Interfaces contribute
nothing either: declare or override an interface's default member on the class
to register it.

To type a property as another of your scripts (`var target: Vehicle?`), mark
that script `@GlobalClass`. See
[Global Classes](godot-api.md#global-classes).

## Printing and Errors

```kotlin
GD.print("Hello from Kotlin")        // Godot's Output panel, like GDScript print()
GD.pushError("bad state")            // a Godot error (Output and Errors tab), like push_error()
println("debug")                     // the process's stdout only, not the Output panel
```

Print with `GD.print`: it goes through Godot's logger and reaches the editor's
Output panel. `println` writes to the game process's own stdout, which you see
only in the terminal that started Godot.

An exception that escapes your code into Godot does not crash the game. Kanama
reports it as a script error with your file and line, the way Godot reports a
GDScript runtime error, and the next frame runs as usual:

```text
SCRIPT ERROR: java.lang.IllegalStateException: no target
          at: Player.ready (res://kotlin-src/com/example/game/Player.kt:42)
```

It appears in the Debugger's Errors tab, which opens the file, and the stack
trace goes to stderr. The rest of that call does not run and its caller gets
`null`. Calling a method on a node that was already freed is the same kind of
error, `Invalid access to previously freed instance`, as in GDScript; ask
`GD.isInstanceValid(node)` before using an object that may be gone.

## Building Your Scripts

GDScript changes are live; Kotlin scripts are compiled first. Press **Build
Scripts** in the Godot toolbar, or turn on build on save. A running game picks
up the new build without a restart. See
[Edit, Build, Run](../getting-started/editor-workflow.md).

## Threads

Every callback a script receives, and every coroutine it starts with `launch`,
runs on Godot's main thread. Move slow work to a worker and come back for the
result:

```kotlin
private val status by node<Label>("Status")

fun loadLevel(path: String) {
    launch {
        val text = withContext(Dispatchers.Default) {   // a worker thread
            FileAccess.getFileAsString(path)
        }
        status.text = "loaded ${text.length} characters"    // back on the main thread
    }
}
```

The rule: only the main thread touches the scene tree. From a thread you start
yourself, hand the result back with `MainThread.post { }`. See
[Kotlin Style](style-guide.md#coroutines).

## Common Pitfalls

- **Generated objects are missing in the IDE.** `PlayerMethods`,
  `PlayerSignals` and `PlayerRpcs` exist after a build. Build, or sync Gradle,
  after adding a public function, a `@Signal` or an `@Rpc`.
- **`@Tool` scripts run in the editor.** An exported node reference may be
  `null` while the editor runs the script, so guard editor-only code. In a
  `KanamaScript`, `isEditorHint()` tells you that you are in the editor, and
  `notifyInspectorChanged()` refreshes the inspector after you change what it
  shows.
- **Kanama does not check threads.** A Godot call from your own thread or a
  `Dispatchers.Default` coroutine runs on that thread, as from a GDScript
  `Thread`. Godot's servers and `ResourceLoader` are thread-safe; the scene tree
  is not.
- **A threaded load runs constructors off the main thread.**
  `ResourceLoader.load_threaded_request("res://Thing.kt")` builds the script on
  a worker, so keep scene-tree work out of constructors and `init` blocks and do
  it in `@OnReady`.
- **Release exports do not check freed objects.** In the editor and in debug
  exports a call on a freed node throws; in a release export it is undefined.
  Ask `GD.isInstanceValid(node)` first.

## On Other Platforms

The script model is the same on desktop, Android, iOS and the Web. The Web
runs a smaller set of lifecycle callbacks and function signatures, and reports
errors without a Kotlin line. iOS shows
line 0 for errors in some export modes, and a few types cross differently on
iOS and Android. [Limitations](../reference/limitations.md) lists each case
with its workaround.
