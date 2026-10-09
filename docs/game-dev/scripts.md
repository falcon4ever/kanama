# Writing Kotlin Scripts

Kanama scripts are Kotlin classes that Godot loads as `.kt` script resources.
This page highlights the Kotlin script model and the most common differences
from GDScript.

## Naming Conventions

GDScript uses `snake_case` everywhere. Kanama follows Kotlin conventions.

| GDScript | Kotlin |
|---|---|
| `func move_and_slide()` | `fun moveAndSlide()` |
| `var my_property = 0` | `var myProperty: Long = 0` |
| `const MAX_SPEED = 10` | `const val MAX_SPEED = 10L` |
| `class MyNode` | `class MyNode` (same) |

Godot's API wrappers convert names automatically — `move_and_slide()` becomes
`moveAndSlide()`, `get_position()` becomes `getPosition()`. Signal name strings
keep their original `snake_case` form in the `.Signals` companion objects.

## `this` Is Not Your Node

In GDScript `self` is the node. In Kanama `this` (the implicit Kotlin receiver)
is the JVM script object — **not** the Godot node the script is attached to.

Extend `KanamaScript<T>` to get a typed `self` wrapper for the attached node:

```kotlin
@ScriptClass(attachTo = "CharacterBody3D")
class Player(godotObject: GodotHandle) :
    KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D) {

    @OnPhysicsProcess
    fun physicsProcess(delta: Double) {
        if (self.isOnFloor()) self.moveAndSlide()
    }
}
```

The constructor parameter is a `GodotHandle` (`net.multigesture.kanama.api.GodotHandle`):
the opaque identity of the Godot object the script is attached to. Pass it on to
`KanamaScript`, hand it to another wrapper (`CharacterBody3D(other.handle)`), and
otherwise leave it alone — it is not a pointer you may inspect, keep, or free. Every
backend declares its own `GodotHandle` under that one name, so the same script source
compiles for desktop, Android, iOS and Web.

Inside a `KanamaScript<T>`, use `selfAs` for a secondary view of the same
Godot object:

```kotlin
private val node3d = selfAs(::Node3D)
```

See [Kotlin Style — `self` And `selfAs` Caching](style-guide.md#self-and-selfas-caching).

`KanamaScript<T>` is the only Kanama API class meant for subclassing. Never
extend a generated wrapper (`Node`, `Resource`, `AudioStream`, ...) — wrappers
are non-owning views (their constructors take a raw handle and never retain), and the build fails with a
pointer to the `@ScriptClass(attachTo = ...)` pattern. For custom resources
specifically, see
[Exports and Resources — Custom Resources](properties-resources.md#custom-resources).

## Scalar Types

| GDScript | Kanama | Note |
|---|---|---|
| `int` | `Long` | Use `Long` for the parameters and returns of your own registered (public) functions (`Int` there is a build error); an `@Export` property may also be `Int` (narrowed on read and write). Engine wrappers follow Godot's signature: Godot's `int` is 64-bit, but where the signature is 32-bit (`int32` in `extension_api.json`, such as `GridMap.setCellItem(position, item: Int, orientation: Int)`) the generated wrapper takes and returns `Int`, so expect both `Int` and `Long` when calling the engine |
| `Node.ProcessMode` (any Godot enum / bitfield) | `Node.ProcessMode` | A typed value class over the `int`; see [Godot Enums and Bitfields](godot-api.md#godot-enums-and-bitfields) |
| `float` (method args/returns) | `Double` | Godot's ABI uses 64-bit slots for scalar float |
| `bool` | `Boolean` | |
| `Vector3.x/y/z`, `Color.r/g/b/a` | `Double` | Like GDScript's 64-bit `float`; stored at Godot's width (`real_t`, float32 for `Color`), as in GDScript |
| `delta` in `_process` | `Double` | Engine timing is always double-precision |

Every decimal is `Double`, as in GDScript: scalar arguments and returns, `delta`,
and vector, transform and color components, so `pos.x * delta` or
`Mathf.lerp(v.y, target, weight)` needs no conversion. As in GDScript, a vector
stores its components at Godot's width (float32 in normal Godot builds), so a
vector you write and read back compares `==` to the one you wrote and prints like
GDScript's `str(v)`, but `Vector2(0.1, 0.2).x == 0.1` is `false`: compare
decimals with `isEqualApprox`. Packed bulk data stays 32-bit
(`PackedFloat32Array` is `List<Float>`). See
[Kotlin Style — Float / Double](style-guide.md#float-double) and
[API conventions — Decimals](../reference/wrapper-conventions.md#4-decimals).

## Value Type Mutation

GDScript structs modify in-place:

```gdscript
velocity.y += gravity * delta
```

Kanama value types (`Vector2`, `Vector3`, etc.) are immutable. Use the `with*`
helpers or construct a new value:

```kotlin
body.velocity = body.velocity.withY(body.velocity.y + gravity * delta)
```

## Lifecycle Callbacks

GDScript uses overridable virtual methods. Kanama uses annotations:

| GDScript | Kanama annotation |
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

The function name is up to you — the annotation is what wires it up. Drop the
leading underscore; `fun ready()` is the convention. A function named like an
engine virtual without an annotation (`fun _process(delta: Double)`, as a
GDScript habit would write it) is a build error naming the annotation to add:
Godot would never call it as the virtual.

The four input callbacks receive the event typed, as in GDScript; cast it to the
subclass you handle:

```kotlin
@OnUnhandledInput
fun unhandledInput(event: InputEvent) {
    if (event.isActionPressed("ui_accept")) restart()
    val motion = InputEventMouseMotion.from(event) ?: return
    look(motion.getRelative())
}
```

(Before Kanama 0.5 the parameter was a `GodotObject`; such a handler is now a
build error naming the fix.)

On the experimental Web backend, lifecycle dispatch covers `@OnEnterTree` too
(since protocol 16); a virtual the Web proxy does not dispatch is rejected at build
time rather than silently never running. See `docs/exporting/web.md`.

## Overriding Engine Virtuals

Beyond the lifecycle annotations, any engine virtual method (the `_*` override
points a GDScript would implement — `_get_aabb`, `_input`, `_draw`, …) can be
overridden with `@OverrideVirtual`. Here the Kotlin function keeps the
GDScript-style name **including** the leading underscore — that name is how the
override is matched, and the processor validates the signature against the
engine's virtual table at build time, so a typo or wrong parameter type fails
the build instead of silently never being called:

```kotlin
@ScriptClass(attachTo = "Mesh")
class ProceduralMesh(val godotObject: GodotHandle) {
    @OverrideVirtual
    fun _get_aabb(): AABB = AABB(Vector3.ZERO, Vector3(2.0, 2.0, 2.0))
}
```

Return types map GDScript-style: Godot `Array` is `List<Any?>`, `Dictionary`
is `Map<String, Any?>` (String keys), and `StringName` returns are declared as
`String`. Every Variant-expressible return family
works on desktop and Android; a handful of value-type returns are not yet
mirrored on iOS (see the coverage notes in
[Wrapper Maintenance](../contributing/wrapper-maintenance.md) if you target
iOS).

A parameter or return Godot types as an enum or bitfield (`enum::Shader.Mode`,
`bitfield::TextServer.FontStyle`) is declared with its typed value class, like
every other Kanama signature of that enum:

```kotlin
@ScriptClass(attachTo = "Material")
class FogMaterial(val godotObject: GodotHandle) {
    @OverrideVirtual
    fun _get_shader_mode(): Shader.Mode = Shader.Mode.FOG
}

@ScriptClass(attachTo = "TextServerExtension")
class MyTextServer(val godotObject: GodotHandle) {
    @OverrideVirtual
    fun _has_feature(feature: TextServer.Feature): Boolean = feature == TextServer.Feature.SHAPING
}
```

An override still written with `Long` (the pre-0.5 spelling) fails the build
and names the type to use (`declare it as Shader.Mode ... not Long`); wrap a raw
number with `Shader.Mode(raw)` and read one with `.value`. The two
`_get_space_state` returns Godot marks required must be declared non-null
(`GodotObject`, not `GodotObject?`).

## Exports

| GDScript | Kanama |
|---|---|
| `@export var speed = 5.0` | `@Export var speed: Double = 5.0` |
| `@export_range(0, 10, 0.5) var speed = 5.0` | `@ExportRange(0.0, 10.0, 0.5) var speed: Double = 5.0` |
| `@export_file("*.png") var icon: String` | `@ExportFile("*.png") var icon: String = ""` |
| `@export_flags_3d_physics var mask: int` | `@ExportFlags3DPhysics var mask: Long = 0` |
| `@export var fov := PI / 3.0` | `@Export var fov: Double = Mathf.PI / 3.0` (the default folds to `1.0471975511965976`) |
| `@export_group("Movement")` | `@ExportGroup("Movement")` on first property in group |
| `@export var scene: PackedScene` | `@Export var scene: PackedScene? = null` |
| `@export_tool_button("Rebuild")` | `@ExportToolButton("Rebuild")` on a zero-argument function |
| `@tool` | `@Tool` on the class |
| `class_name Player` | `@GlobalClass` on the class |

`@Export` is the plain export, on `@ScriptClass` scripts and on `@RegisterClass`
types alike; every GDScript `@export_*` hint annotation has a typed twin that
exports by itself. See [Exports and Resources](properties-resources.md#export-hints).

## Functions Godot Can Call

Like a GDScript `func`, **every public function of a script class is registered
with Godot**, under its Kotlin name converted to snake_case: `fun showMessage(text:
String)` is `show_message`, callable from GDScript, `call()`, signal connections
and `Callable`s. No annotation is needed.

```kotlin
@ScriptClass(attachTo = "CanvasLayer")
class Hud(godotObject: GodotHandle) : KanamaScript<CanvasLayer>(godotObject, ::CanvasLayer) {
    fun showMessage(text: String) { ... }          // Godot: show_message

    @GodotName("_on_start_button_pressed")         // the name the editor saved in the .tscn
    fun onStartButtonPressed() { ... }

    private fun layout() { ... }                   // Kotlin-only
    internal fun debugState(): Map<String, Any> = mapOf()   // Kotlin-only
}
```

- **`@GodotName("...")`** gives a function another Godot-side name, used verbatim.
  Use it where Godot must find the function under a name its Kotlin spelling does
  not produce, typically a signal connection the editor saved as
  `_on_start_button_pressed`.
- **`private`, `protected` and `internal`** functions stay Kotlin-only. Scripts are
  compiled as one module, so other scripts still call an `internal` function.
- A registered function's parameters and return must be types Godot can carry
  (`Long`, `Double`, `Boolean`, `String`, the value types, Godot enums, the
  supported node and resource wrappers, `GodotObject`). Any other type in a public
  function is a build error that names it: make the function `internal` or
  `private` if Godot never calls it.
  `InputEvent` is not one of them: a helper that takes the event from an input
  handler (`fun handleKey(event: InputEvent)`) must be `private` or `internal`.
- A public function whose snake_case name is already an engine method of the
  attached class (`fun queueFree()` is `queue_free`, `fun getName()` is
  `get_name`) is a build error: it would replace the engine's method for scene
  connections, `call()` and `Callable`s on that node. Rename it, make it `internal`
  or `private`, or confirm the override with `@GodotName("queue_free")`. A function
  spelled like an engine virtual (`_process`, or the camelCase
  `_getConfigurationWarnings`) needs its lifecycle annotation or
  `@OverrideVirtual` instead.
- A public function with a `vararg` parameter is a build error (Godot calls with a
  fixed argument list); make it `internal` or `private`.
- `suspend` functions, extension functions, generic functions, and an `override`
  of a member that is not a script's (`toString()`, an interface method) are
  Kotlin-only even when public.
- **Web only:** the Web backend dispatches a registered function only for the
  argument shapes it has an arm for (no arguments, numeric lists, a single String
  or object, mixed String/NodePath/Long/Boolean/object lists, a zero-argument
  value return). Any other public function fails only the Web build, naming the
  shape; make it `internal` if Godot never calls it.
- Functions with a lifecycle annotation, `@OverrideVirtual`, `@Signal` or
  `@ExportToolButton` are wired by that annotation and not registered twice.
- Two functions on one Godot name (Kotlin overloads, for one) are a build error:
  Godot has no overloads.

`@RegisterClass` types follow the same rule; there, `@Export var x` also registers
`get_x`/`set_x`, so a public `fun getX()` beside it is a build error naming both.

The annotations Kanama 0.5 removed (`@RegisterFunction`, `@ScriptProperty`,
`@Process`, … — see the table in [Porting GDScript](porting-gdscript.md)) still
exist only so that a leftover fails to compile with a message naming the
replacement; `scripts/migrate_script_annotations.py` rewrites a source tree.

## Printing and Errors

```kotlin
GD.print("Hello from Kotlin")        // Godot's Output panel, like GDScript print()
GD.pushError("bad state: $value")    // a Godot error (Output + Errors tab), like push_error()
System.err.println("[debug] $value") // the process stderr only, NOT the editor's Output panel
```

`GD.print` and its siblings go through Godot's logger, so they reach the
editor's Output panel when you press Play. `println` and `System.err.println`
write to the game process's stdout/stderr: you see them in the terminal Godot
was started from, or in a `--headless` log. The editor starts the game without
capturing either stream, so they never reach its Output panel, and a game
started from the Dock or Finder shows them nowhere.

On desktop, Android and iOS, an exception that escapes your code at an engine
boundary (a script method, a lifecycle callback such as `_ready` or `_process`,
a signal lambda, a property accessor, a `MainThread` task, a script coroutine
started with `launch`) does not crash the game. Kanama catches it, prints the full stack trace to stderr, and reports it to
Godot as a script error, the way a GDScript runtime error is reported. Godot's
log shows (desktop console output):

```text
SCRIPT ERROR: java.lang.IllegalStateException: no target
          at: Player.ready (res://kotlin-src/com/example/game/Player.kt:42)
```

It appears in Godot's log (the Output panel, a terminal, a device log) and, when
you run from the editor, in the Debugger's Errors tab. The file and line are the
top frame of your own code: Kanama, generated, Kotlin and JDK frames are
skipped. When the file is in the project (`res://kotlin-src/<package path>/` or
the project root) the `res://` path is reported, so the Errors tab can open it;
otherwise the bare file name. The rest of that call does not run and the caller
gets `null`, as with a GDScript runtime error; the next frame calls `_process`
again as usual. On iOS a debug build reports the file and line of your code's
frame too (`res://kotlin-src/Player.kt:42`): an iPhone app carries no debug info,
so the debug addon build maps your scripts' functions to their lines ahead of
time, and the device looks them up by function name. That needs an app that
keeps its symbol table: build Godot's exported Xcode project (Export Project
Only, then Run in Xcode or `xcodebuild build`). Godot's `.ipa` export and its
one-click deploy archive the app, which strips it, and report the failing
callback with line 0. A release build names the class and method with line 0. In an R8-minified Android release
build a frame without source info is not attributed, and the error names the
callback that failed instead. On Web the same report reaches the browser console through
Godot's error log -- `ERROR: SCRIPT ERROR: kotlin.IllegalStateException: no target`, then
`at: Player._ready (no source line on Wasm)`, then Godot's own `at: push_error (...)` line (the
report is printed by `push_error`) -- and the callback returns its default while the game runs on.
A Web export carries no names or source maps, so the report names the script and the callback
that failed, not a Kotlin file and line. Only an exception from your code is contained: the
runtime failing around it (a bridge or protocol fault) still ends the page. A call whose return
type is a typed GDScript value (`Long`, `Double`, `Boolean`, `String`, `Vector2`) answers that
type's default (`0`, `0.0`, `false`, `""`, zero) where desktop answers `null`; a Vector3, Color or
other value-type return answers `null` as on desktop.

Calling a method through a wrapper whose object was freed (a node after
`queueFree()` took effect) is such an error in debug builds — the editor and
debug exports: `IllegalStateException: Invalid access to previously freed
instance (Node3D, instance id …)`, as GDScript reports it. Holding such a
wrapper is not an error: comparing it, `GD.isInstanceValid(node)`, and handing
it back to Godot as a value (an exported property, a method's return value, a
Variant argument) are silent, and Godot receives `null`. A release export does
not check, and a call is undefined behaviour, so ask `GD.isInstanceValid(node)`
before using an object that may be gone. Two wrappers of one object are `==`
(and equal as `Set`/`Map` keys) whatever their class. On Web a call that returns a value or an object
(`getName()`, `getParent()`, `getNode(...)`, `getChild(i)`, `emitSignal(...)`) on an
object the engine freed (a child freed with its parent, a timer, a body) throws the same
`IllegalStateException: Invalid access to previously freed instance`, naming the handle instead of
the class. A call that returns nothing (`node.position = ...`, `queueFree()`, `addChild(...)`) is
queued and applied with the rest of the frame's batch, so it does not throw at the call: on a
freed object the batch reports Godot's `Invalid Kanama Web command opcode/object` error and then
fails with `command batch applied 0 of 1 commands`, which ends the page, where desktop throws at
the call; a position read of a `Node2D`/`Node3D` answers
the last snapshot it had. Ask `GD.isInstanceValid(node)` first on Web too.

## Rebuild Required

GDScript changes are live. Kotlin scripts must be compiled before Godot sees
them. Use the `Build Scripts` toolbar button (Kanama Tools plugin) or:

```sh
./gradlew installAddonJar -PkanamaProjectDir=... -PkanamaProjectScriptsDir=...
```

Hot reload reloads the jar without restarting Godot. See
[The Editor Loop](../getting-started/editor-workflow.md).

## Declare a Package

Every script file should declare a package; the starter script and every demo do.
The build does not enforce it: a package-less `@ScriptClass` builds, loads and
runs on desktop. The generated registrars and helper names are derived from the
fully qualified class name, though, and the iOS emitter builds its imports,
`*Methods`/`*Signals` helpers and per-package compatibility sources from the
package, so a package-less script has no tested path there. The editor's
**New Script** dialog writes one for you: the package most scripts in the target
folder already use, else one derived from the folder (`game` at the project
root). A script you create another way needs one too:

```kotlin
package com.mygame.scripts

@ScriptClass(attachTo = "Node")
class MyScript(godotObject: GodotHandle) :
    KanamaScript<Node>(godotObject, ::Node) { ... }
```

## Signals

See [Signals and Callbacks](signals.md) for the full reference.
Quick comparison:

```gdscript
# GDScript
signal hit_enemy(damage)
emit_signal("hit_enemy", 10)
```

```kotlin
// Kanama
@Signal
fun hitEnemy(damage: Long) = Unit

PlayerSignals.hitEnemy(this, 10L)
```

For multiplayer RPC methods, KSP also generates typed `*Rpcs` helpers from
`@Rpc` declarations. See [Multiplayer](multiplayer.md).

A Godot enum works as a registered function's parameter or return, a `@Signal`
argument and an `@Rpc` argument: it crosses into Godot as the `int` it stands
for (so GDScript callers pass and receive `Node.PROCESS_MODE_ALWAYS`), and
Kotlin sees the value class:

```kotlin
fun nextMode(mode: Node.ProcessMode): Node.ProcessMode = Node.ProcessMode(mode.value + 1)

@Signal fun modeChanged(mode: Node.ProcessMode) = Unit
```

## Registered Method Helpers

KSP generates a `*Methods` helper object for every registered function of a
Kanama script. Use these helpers when Kotlin code needs
to invoke another Kanama script's public Godot-facing method:

```kotlin
fun damage(amount: Double) {
    health -= amount
}

// Direct script instance.
PlayerMethods.damage(playerScript, 5.0)

// Generic GodotObject from a collision or lookup.
if (!PlayerMethods.damage(collider, 5.0)) {
    // The collider was not backed by Player.
}
```

For non-`Unit` methods, the `GodotObject` overload returns a nullable value:

```kotlin
val label = HudMethods.currentLabel(hudObject) ?: return
```

These helpers are for Kanama-to-Kanama calls. For mixed GDScript/Kanama
projects, keep using signals, registered Godot names, or `GodotObject.call`
where the target is a GDScript object or an intentionally dynamic autoload.

## Node Lookup

GDScript's `@onready var timer: Timer = $ScoreTimer` is a `node<T>()` delegate:

```kotlin
private val scoreTimer by node<Timer>("ScoreTimer")
private val animationPlayer by node<AnimationPlayer>("Character/AnimationPlayer")
private val camera by node<Camera3D>("%Camera3D")          // scene-unique names work too
private val player by script<Player>("Player")             // the Kotlin script on that node
```

The node is looked up on first read and cached, like an `@onready` variable that
GDScript assigns just before `_ready`. The read must therefore come once the node
is ready (in `@OnReady` or later). The cache lasts until the next `_ready`: after
`request_ready()` and a re-entry, the next read looks the node up again, as GDScript
re-runs its `@onready` initializers. Every lookup also takes a `NodePath`. Each delegate checks what it finds and throws an
`IllegalStateException` that names the property:

- read before ready: `Main.scoreTimer: node("ScoreTimer") was read before /root/Main was ready`;
- missing node: `Main.scoreTimer: no node at "ScoreTimer" under /root/Main`;
- wrong class: `Main.scoreTimer: node "ScoreTimer" is a Node2D, not a Timer`;
- `script<T>()` on a node without that script: `... has no Kotlin script, not Player`.

For a one-off lookup, `self.requireAs<T>(path)` throws the same way and
`self.getNodeAs<T>(path)` returns `null` when the path is missing or the node is
another class (GDScript `get_node_or_null(path) as T`). Exported references
(`@Export var label: Label? = null`) remain the inspector-wired option.

The older `self.requireAs(path, ::Label)` and `getAsOrNull(path, ::Label)` still
compile; they do not check the class, so prefer the typed forms.

## Casts and Script Checks

| GDScript | Kotlin |
|---|---|
| `var cam := x as Camera3D` | `val cam = x.castOrNull<Camera3D>()` (`null` when it is not one) |
| `var cam: Camera3D = x` | `val cam = x.cast<Camera3D>()` (throws `ClassCastException`) |
| `if body is Player:` | `if (body.isScript<Player>())` |
| `var p := body as Player` | `val p = body.asScript<Player>()` |

`castOrNull` and `cast` ask Godot (`Object.is_class`) every time, even when the
wrapper's Kotlin class already matches (a wrapper minted with `Timer(node.handle)`
proves nothing); only a cast to `GodotObject` skips the question. A cast to a
`RefCounted` class (`res.cast<Texture2D>()`) returns a new wrapper with a reference
of its own, like the `from*` downcasts: kept in a field it keeps the object alive,
and closing it (or forgetting it) releases only that reference, never the
original's. A cast to any other class returns the same wrapper when it already is
a `T`, else a new view of the same object. They take a Kanama wrapper class (`Node3D`, `InputEventKey`,
`PackedScene`, ...); `isScript` / `asScript` take a Kotlin script class.
Replace hand-written `Node3D(other.handle)` casts with them: an unchecked one
calls `Node3D` methods on whatever the object really is.

## Preload and Instancing

```kotlin
private val bulletScene by preload<PackedScene>("res://bullet.tscn")   // const BULLET = preload(...)

fun shoot() {
    val bullet = bulletScene.instantiateAs<RigidBody3D>()   // root node, class-checked
    val coin = coinScene.instantiateScript<Coin>()          // the Kotlin script on the root
    self.addChild(coin.self)
}
```

`preload` loads the resource on first read and keeps it for the rest of the
process, shared by every script that preloads the same path, as a GDScript
`preload` constant does; do not `close()` it. A missing file or a resource of
another class throws. Give an absolute `res://` or `uid://` path. The cache is keyed
by the path text, so preloading one resource by both its `res://` path and its
`uid://` holds it twice; that is harmless, it is the same object. `instantiateAs<T>()` and `instantiateScript<T>()` free the
instance and throw when its root is not what you asked for.

## Tree Accessors

`self.tree`, `self.viewport` and `self.parentNode` are the non-null forms of
`getTree()`, `getViewport()` and `getParent()`: they throw an
`IllegalStateException` (`Node "Sub" is not inside the tree`, or `... has no
parent`) where GDScript's `get_tree()` fails. They check the tree membership
first, so Godot logs no error of its own.

```kotlin
self.tree.callGroup("mobs", "queue_free")      // was requireNotNull(self.getTree())
```

## Await

A script launches coroutines on its own scope and suspends in them the way a
GDScript function `await`s:

| GDScript | Kotlin, inside `launch { }` |
|---|---|
| `await get_tree().create_timer(1.0).timeout` | `wait(1.0)` |
| `await get_tree().process_frame` | `nextFrame()` |
| `await $MessageTimer.timeout` | `messageTimer.timeout.await()` |
| `var body = await area.body_entered` | `val body = area.bodyEntered.await()` (typed: `Node3D`) |

```kotlin
fun showGameOver() {
    launch {
        showMessage("Game Over")
        messageTimer.timeout.await()
        wait(1.0)
        startButton.show()
    }
}
```

The scope runs on the main thread and is cancelled when the script's Godot
object is freed, so a coroutine never touches a freed node. Leaving the tree does
not cancel it (GDScript does not either); `cancelCoroutines()` does. `wait` uses a
`SceneTree` timer with GDScript's `create_timer` defaults: it keeps running while the
tree is paused and follows `Engine.time_scale`. `wait(1.0, processAlways = false)`
pauses with the game, and `ignoreTimeScale = true` ignores the time scale. On Web it
is the frame scheduler's delay (the defaults only). `scriptScope` is the scope itself, for other `kotlinx.coroutines` builders.
See [Kotlin Style → Coroutines](style-guide.md#coroutines).

**Web.** The Web backend has `launch`, `wait`, `nextFrame`, `isScript`,
`asScript` and the tree accessors; the `node`/`script`/`preload` delegates,
`castOrNull`/`cast`, `requireAs<T>`/`getNodeAs<T>` and the `instantiate*` helpers
are desktop, Android and iOS only for now.

## Autoloads

GDScript reaches an autoload by its global name (`Audio.play(...)`); Kanama
generates the same names as properties of `net.multigesture.kanama.generated.Autoloads`,
one per enabled (`*`) entry of the `project.godot` `[autoload]` section:

| Autoload | `Autoloads.<Name>` is typed |
|---|---|
| a Kotlin script (`Settings="*res://kotlin-src/Settings.kt"`) | to the script class: `Autoloads.Settings.loadSettings()` |
| a scene (`MusicPlayer="*res://MusicPlayer.tscn"`) | to the root node's script class when it has a Kotlin script, else to its class (`AudioStreamPlayer`) |
| a GDScript (`Audio="*res://scripts/audio.gd"`) | to the class it `extends` (`Node`): call into it with `call("play", ...)` |

```kotlin
import net.multigesture.kanama.generated.Autoloads

Autoloads.Audio.call("play", "res://sounds/coin.ogg")   // GDScript: Audio.play("res://sounds/coin.ogg")
val settings = Autoloads.Settings                       // the Kotlin script on /root/Settings
```

The first read finds the node at `/root/<Name>`, as GDScript's global does, and
throws an `IllegalStateException` naming the autoload when the node is missing
or of another class; later reads reuse that node while it is alive (a freed one
is looked up again). A worker thread can read an autoload once the main thread
has read it; before that the read throws, because only the main thread may
query the scene tree. The
processor reads `project.godot` above the script sources (the build declares it
as an input, so editing the autoload list re-runs it); a project laid out
differently sets the `kanamaGodotProjectDir` KSP option. A class Kanama has no
wrapper for on the target falls back to its nearest wrapped ancestor. On Web the
lookup goes through the running script's node, so read an autoload from a script
callback (a lifecycle handler, a signal, a coroutine), which is where Web code runs.

**`@Tool` scripts in the editor.** The editor adds an autoload to its own tree
only when the autoload's script is a tool script (`@Tool`), as Godot does for
GDScript. Editor code of a `@Tool` script can read `Autoloads.<Name>` of such an
autoload; any other autoload is not in the editor's tree, so the read throws
there (check `Engine.isEditorHint()` first). In the running game every autoload
is there.

**Keys and paths.** A feature-tag override in `project.godot`
(`Music.android="*res://music_mobile.gd"`) is not an autoload of its own and is
skipped with a build warning; `Autoloads.Music` is typed from the base entry. An
autoload saved by uid (`"*uid://…"`) is typed from the file that declares the
uid (a script's `.uid` file, a scene's header).

**Hot reload.** A desktop hot reload resets each Kotlin autoload in place, so the
autoload stays the same node: `Autoloads.<Name>`, GDScript's global name and any
reference other code holds keep working. What survives and what does not:

| Survives the reload | Does not survive |
|---|---|
| the node itself (identity, name, place under `/root`, its GDScript global) | the old script object: each Kotlin node of the autoload gets a new one, from the new build |
| a stored (exported) property whose type did not change: its value is set back | a stored property whose type changed: it takes the new build's default (or the autoload scene's value) |
| the nodes of the autoload's scene (`.tscn` children) | children created at run time (in `_ready` or later): they are freed |
| connections other objects made **to** the autoload | lambda connections the autoload made (`signal.connect(self) { ... }`): they are disconnected |
| | every other Kotlin field: it starts from its initializer |

Then `_ready` runs again on the reset nodes, children before parents as Godot
readies a tree, so whatever `_ready` sets up (runtime children, connections)
exists once, from the new code. This is a scene reload for the autoload, minus
the new node. Hot reload runs in the editor process too (Build Scripts while
the editor is open), so a `@Tool` autoload in the editor's tree is reset the
same way there.

## Script Inheritance

A script class can extend another script class of the project, as a GDScript
script `extends` another one. The subclass has every member of the chain: the
exported properties, signals, lifecycle handlers, `@OverrideVirtual`s, tool
buttons and registered public functions of its superclasses, with no forwarding
code:

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
    override fun ready() {             // still the _ready handler: the annotation is inherited
        super.ready()
        /* ... */
    }
    override fun effectBody(delta: Double) { /* ... */ }
}
```

The rules:

- **The most derived declaration is the member.** Godot calls the override (Kotlin
  dispatch is virtual); a superclass's `private` member is not inherited.
- **Annotations follow the override.** An override without Kanama annotations
  keeps the overridden declaration's (`override fun ready()` stays `_ready`; the
  override of an `@Export open var` stays exported). An override with its own
  annotations uses those: the subclass wins.
- **Conflicts are build errors.** Two different functions of the chain that land on
  one Godot name (a base `@OnReady fun ready()` and a subclass `@OnReady fun setup()`)
  or two exported properties with one Godot name fail the build naming both:
  override the base member instead of adding a second one.

- **Generic bases are typed for the subclass.** On `class Sub : Base<Long>()`, a
  base's `@Export var amount: T` is a `Long` export and its `open fun f(x: T)` takes
  a `Long`; `override fun f(x: Long)` overrides it (one Godot method, not two).

The superclass may be a `@ScriptClass` itself or a plain (abstract) class in the
same build. A class from a library contributes nothing, because annotations are
read from source; the build warns, naming the library class. Interfaces
contribute nothing either: only the class chain is collected, so a default
member of an interface the script implements is not registered (declare it on
the class, or override it there).

## Cross-Script References

To reference another Kanama script type (e.g. `var target: Vehicle?`), both
classes must be in the same project and the referenced class must be
`@GlobalClass`. See [Calling Godot APIs — Global Classes](godot-api.md#global-classes).

## Threads

Godot calls into Kanama on whichever thread made the call, and Kanama runs it
there. For the scene tree that is the engine's main thread: every callback a
script receives — `@OnReady`, `@OnProcess`, `@OnPhysicsProcess`,
registered functions called from GDScript, signal callbacks, `@OverrideVirtual` —
arrives on the main thread, `initialize` ran there, and `MainThread.post` and a
script's coroutines (`launch { }`) drain their queues there once per frame.
That is also the rule for your own threads: **hand results back to the main
thread** with `MainThread.post` or a script coroutine before touching a
node (see [Kotlin Style → Coroutines](style-guide.md#coroutines)).

A `RefCounted` wrapper you get on another thread is still yours, but it gets
**no garbage-collector fallback**: a worker can still be inside a call through
it when the main thread would release it, so Kanama only registers the fallback
for wrappers made on the main thread. That covers every owned wrapper a worker
makes: a getter result, a loaded resource, and a `from*` downcast (which takes a
reference of its own). **Close what you create or downcast on a worker**
(`use { }`), or hand it to the main thread first; a forgotten one is never
released ([Resource Ownership](godot-api.md#a-forgotten-close-is-a-late-release)).

The other direction needs care too: a wrapper made on the main thread and
handed to a worker is released by the main thread once the collector finds it
unreachable — and the collector may decide that while the worker is still
inside its last call through it. Keep such a wrapper reachable until the worker
is done (close it after, from the main thread, or keep it in a field), rather
than making the worker's call its last use.

Kanama performs **no thread-affinity checks**. A wrapper method called from a
`Dispatchers.Default` coroutine or a `Thread` you started ptrcalls the engine
from that thread, exactly as a GDScript `Thread` would; whether that is safe is
Godot's rule for the API in question (the servers and `ResourceLoader` are
thread-safe, the scene tree is not), and nothing in Kanama will stop you.

`ResourceLoader.load_threaded_request("res://Thing.kt")` is the case where the
engine itself leaves the main thread: it runs Kanama's `.kt` loader on a worker
thread. The loader is written for that — its per-thread state is `ThreadLocal`,
its script catalog is synchronized, and its create-and-attach path only assumes
that creation and attach happen on the *same* thread, which Godot guarantees
because `_instance_create` is synchronous. The consequence for **your** code is
that a `@ScriptClass` constructor or `init` block reached through such a load
runs on that worker too. Keep constructors free of scene-tree access and do that
work in `@OnReady`, which is always dispatched on the main thread.

To see whether a callback is arriving off the initialize thread, run with
`KANAMA_THREAD_DIAGNOSTICS=1`. Kanama then logs, **once per site**, when
`ScriptBridge.siCall` (script method dispatch) or the `.kt` loader's `_load` runs
on a thread other than the one that ran `initialize`:

```
[kanama:thread] KanamaResourceFormatLoader.callLoad ran on thread id=41 name=..., not the initialize thread (...)
```

It is a diagnostic, not an assertion — nothing is refused — and with the
variable unset it costs one boolean read per call.

## Known Gotchas

- **Default arguments depend on how Godot reaches the method.** On a
  `@ScriptClass` script, a registered function whose trailing parameters
  have Kotlin defaults is dispatched by argument count: a Godot-side caller that
  omits them (`call("spawn")`, an untyped `obj.spawn()`, a connection with fewer
  bound arguments) gets the Kotlin defaults. Desktop, Android and iOS do this
  (iOS: task 114); the Web backend is not covered here. The method metadata
  Godot receives lists every parameter. A `@RegisterClass` type's methods are registered in
  `ClassDB` with no default arguments (`default_argument_count` is 0), so Godot
  rejects a call that omits any parameter: pass them explicitly or use overloads.
- **KSP must run** before IntelliJ resolves generated helpers like
  `PlayerMethods`, `PlayerSignals`, or `PlayerRpcs`. Run a Gradle sync or
  build after adding new public functions, `@Signal`, or `@Rpc`
  declarations.
- **`@Tool` scripts run in the editor.** Guard editor-only code against
  partially initialized scenes — exported node references may be `null` during
  editor tool execution. From a `KanamaScript` subclass, use `isEditorHint()`
  for editor checks and `notifyInspectorChanged()` after changing editor-time
  property-list state.
