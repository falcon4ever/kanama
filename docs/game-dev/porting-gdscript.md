# Porting from GDScript

Move a Godot project from GDScript to Kotlin one script at a time; the script
model itself is on [Writing Scripts](scripts.md).

## Porting Workflow

1. Work in a branch or a copy of the original project.
2. Add Kanama to it, as in
   [Store Addon (Existing Project)](../getting-started/store-addon.md).
3. Port one script at a time. Keep scene names, exported property names and
   signal connections as they are.
4. Build your scripts and open the project in Godot, so imported assets and
   script metadata settle.
5. Play the project and compare it with the original.

A port should behave like the original without changing its logic. If it only
works with a workaround that [Limitations](../reference/limitations.md) does
not list, please [open an issue](https://github.com/falcon4ever/kanama/issues).

## GDScript to Kotlin Mapping

The everyday script constructs, inside a `KanamaScript<T>` subclass (see
[Writing Scripts](scripts.md) for each one):

| GDScript | Kotlin |
|---|---|
| `extends CharacterBody3D` | `@ScriptClass(attachTo = "CharacterBody3D") class Player(godotObject: GodotHandle) : KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D)` |
| `self.position` | `self.position` (`this` is the Kotlin script object) |
| `@onready var timer: Timer = $ScoreTimer` | `private val timer by node<Timer>("ScoreTimer")` |
| `@onready var skin: SophiaSkin = %SophiaSkin` (script class) | `private val skin by script<SophiaSkin>("%SophiaSkin")` |
| `$Path` / `get_node(path)` used once | `self.requireAs<Timer>("Path")` |
| `get_node_or_null(path) as Timer` | `self.getNodeAs<Timer>("Path")` |
| `x as Camera3D` | `x.castOrNull<Camera3D>()` |
| `var cam: Camera3D = x` | `x.cast<Camera3D>()` |
| `body is Player` (script class) | `body.isScript<Player>()` |
| `body as Player` (script class) | `body.asScript<Player>()` |
| `const BULLET = preload("res://bullet.tscn")` | `private val bulletScene by preload<PackedScene>("res://bullet.tscn")` |
| `var b: RigidBody3D = BULLET.instantiate()` | `val b = bulletScene.instantiateAs<RigidBody3D>()` |
| `var c: Coin = COIN.instantiate()` (script root) | `val c = coinScene.instantiateScript<Coin>()`, its node is `c.self` (`coinScene` preloaded as above) |
| `get_tree()` / `get_viewport()` / `get_parent()` | `self.tree` / `self.viewport` / `self.parentNode` |
| `await get_tree().create_timer(1.0).timeout` | `launch { wait(1.0); ... }` |
| `await get_tree().process_frame` | `launch { nextFrame(); ... }` |
| `await $Timer.timeout` | `launch { timer.timeout.await(); ... }` |
| `var body = await area.body_entered` | `launch { val body = area.bodyEntered.await(); ... }` |
| `area.body_entered.connect(func(body): ...)` | `area.bodyEntered.connect { body -> ... }` (`body: Node3D`, typed from Godot's API) |
| `timer.timeout.connect(_on_timeout, CONNECT_ONE_SHOT)` | `timer.timeout.connect(GodotObject.ConnectFlags.ONE_SHOT) { onTimeout() }` |
| `button.pressed.emit()` | `button.pressed.emit()` |
| `func _ready():` | `@OnReady fun ready()` |
| `func _input(event):` | `@OnInput fun input(event: InputEvent)` |
| `func show_message(text):` | `fun showMessage(text: String)` (every public function is registered) |
| `@export var speed := 5.0` | `@Export var speed = 5.0` |
| `Vector3(0, 1, 0)`, `Vector3(speed, 0, 0)`, `Color(1, 1, 1, 0.72)` | the same |
| `velocity.y += gravity * delta` | `self.velocity = self.velocity.withY(self.velocity.y + gravity * delta)` (value types are immutable; `gravity` is a property of your script) |
| `print(position)` → `(0.1, 0.2)` | `GD.print(self.position)` → `(0.1, 0.2)` |
| `var items: Array[String] = []` | `val items = mutableListOf<String>()` (an `Array` is a `List`) |
| `var scores := {}` | `val scores = mutableMapOf<String, Any?>()` (a `Dictionary` is a `Map`; see [Collections](../reference/wrapper-conventions.md#10-collections)) |
| `v.is_equal_approx(w)`, `v == w` | `v.isEqualApprox(w)`, `v == w` (compares the stored components, as in GDScript) |
| `global_transform * Vector3.FORWARD`, `basis * other_basis`, `quat * dir` | the same operators |
| `2.0 * v` | the same, with `import net.multigesture.kanama.types.*`, which new scripts have |
| `Vector2i(4, 6) / 2`, `-color`, `a < b` (vectors) | the same |
| `x ** y` | `x.pow(y)` (`kotlin.math`) or `GD.pow(x, y)` (Godot's `pow`): Kotlin has no power operator |
| `Color.RED`, `Vector2i.LEFT`, `Vector3.MODEL_FRONT` | the same |
| `v.direction_to(t)`, `rect.get_center()`, `Color.from_hsv(h, s, v)` | `v.directionTo(t)`, `rect.getCenter()`, `Color.fromHsv(h, s, v)` |
| `path.get_extension()`, `name.to_snake_case()` | `path.getExtension()`, `name.toSnakeCase()`, with `import net.multigesture.kanama.builtins.*` |
| `String.num(x, 2)` | `String.num(x, 2)`, same import |
| `text.begins_with("a")` | `text.startsWith("a")` (Kotlin's own; [the list](godot-api.md#strings-node-paths-and-bytes)) |
| `", ".join(parts)` | `parts.joinToString(", ")` |
| `"%d" % n` | `"$n"` |
| `text.length()`, `text.find("x")`, `text.split(",")` | `text.godotLength()`, `text.godotFind("x")`, `text.godotSplit(",")`: a name Kotlin already has gets a `godot` prefix |
| `"ff".hex_to_int()`, `"12abc".to_int()` | `"ff".godotHexToInt()`, `"12abc".godotToInt()` |
| `"{name}".format({"name": n})` | `"{name}".godotFormat(mapOf("name" to n))` (same package) |
| `bytes.decode_u32(0)`, `bytes.get_string_from_utf8()` | `bytes.decodeU32(0)`, `bytes.getStringFromUtf8()` (a `ByteArray`, same package) |
| `NodePath("a/b:x").get_name(1)` | `NodePath("a/b:x").getName(1)` |
| `Callable(obj, "m").call(1)` | `GodotCallable(obj, "m").call(1)` (no `bind`) |

`Vector2(0.1, 0.2).x == 0.1` is `false` in Kotlin, as in GDScript. Every
decimal is a `Double`, and a vector, transform or color stores its components
as 32-bit floats, as Godot does, so equality, printing and arithmetic match the
GDScript you port. Compare with `isEqualApprox`. See
[API Conventions](../reference/wrapper-conventions.md#4-decimals).

A function that `await`s in GDScript becomes a `launch { }` block: everything
after the first suspension runs on a later frame, and the block stops when the
script's object is freed.

### Annotations and Declarations

Each GDScript annotation and declaration has one Kotlin form:

| GDScript | Kanama |
|---|---|
| `func f()` (callable from Godot) | a public `fun f()`; `@GodotName("...")` for another name |
| `@export var x` | `@Export var x` |
| `@export var area: Rect2`, `var pose: Transform3D = Transform3D.IDENTITY`, `func f(p: Plane) -> AABB`, `signal moved(t: Transform2D)` | the same types: every Godot value type (`Vector4`/`4i`, `Rect2`/`2i`, `Plane`, `AABB`, `Quaternion`, `Basis`, `Transform2D`/`3D`, `Projection`) is an export, parameter, return and signal type ([Exports](properties-resources.md#export-hints)) |
| `@export_category` / `_group` / `_subgroup` | `@ExportCategory` / `@ExportGroup` / `@ExportSubgroup` |
| `@export_tool_button("Label")` | `@ExportToolButton("Label")` |
| `signal hit(damage)` | `@Signal fun hit(damage: Long) = Unit`; typed handle `player.hit` (`hit.emit(5)`, `player.hit.connect { damage -> }`) |
| `@rpc(...)` | `@Rpc(...)` on a public function |
| `jump.rpc()` / `jump.rpc_id(id)` on a `call_local` RPC | `PlayerRpcs.callLocalJump(this)` / `PlayerRpcs.rpcIdJump(this, id)`; `this` is the script object ([Multiplayer](multiplayer.md#rpc-methods)) |
| `@tool` | `@Tool` |
| `class_name Player` | `@GlobalClass` |
| `func _ready()`, `_enter_tree`, `_exit_tree` | `@OnReady`, `@OnEnterTree`, `@OnExitTree` |
| `func _process(delta)`, `_physics_process` | `@OnProcess`, `@OnPhysicsProcess` |
| `func _input(event)`, `_unhandled_input`, `_shortcut_input`, `_unhandled_key_input` | `@OnInput`, `@OnUnhandledInput`, `@OnShortcutInput`, `@OnUnhandledKeyInput` (`event: InputEvent`) |
| any other `func _virtual()` | `@OverrideVirtual fun _virtual()` |

## Porting Checklist

- `@Export` names register as `snake_case` in Godot. `.tscn` values
  must match: `view_path = NodePath("../View")`, not `viewPath`.
- Remove `uid://...` from script `ext_resource` entries when replacing a `.gd`
  script with a `.kt` script. Godot assigns new UIDs on first open.
- Remove stale `node_paths=PackedStringArray(...)` entries from node headers
  when they only described old GDScript exports.
- Every public function is registered, like a GDScript `func`:
  `fun onCoinCollected()` registers as `on_coin_collected`. Keep a `.tscn`
  connection to `_on_coin_collected` working with
  `@GodotName("_on_coin_collected")`, or update the connection. Make functions Godot
  never calls `private` (or `internal`).
- GDScript's `func _ready()`, `_process`, `_input`, … become a lifecycle
  annotation on a function of any name (`@OnReady fun ready()`); a function
  still named `_process` without one is a build error. Input handlers take the
  typed event: `@OnInput fun input(event: InputEvent)`.
- For instanced scenes, look a node up as the class Godot actually created
  (`node<Node3D>(path)` for an imported GLB root) unless the exact imported root
  class is known and stable.
- Prefer `node<T>()` / `requireAs<T>()` when the original scene requires a node to
  exist. Use `getNodeAs<T>()` only when the original behavior was optional.
- Preserve type checks: `if body is Player` is `body.isScript<Player>()`, not a
  `hasMethod(...)` probe or a node-name test.
- Keep `GodotObject.call(...)` at true mixed-language boundaries, such as a
  GDScript autoload kept during an incremental port. Use typed wrappers and
  direct Kotlin calls for known Kanama scripts.
- Godot enum constants become typed values: `Node.PROCESS_MODE_ALWAYS` is
  `Node.ProcessMode.ALWAYS`, `KEY_ESCAPE` is `Key.ESCAPE`, a bitfield combines
  with `or` and tests with `in`. An `@export var mode: Node.ProcessMode` ports to
  `@Export var mode: Node.ProcessMode = Node.ProcessMode.INHERIT`; it
  stores the same Godot values, so the scene's `mode = 3` keeps working. A
  GDScript `int` that really holds an enum (`var mode := 3`) is worth typing
  during the port. See [Godot Enums and Bitfields](godot-api.md#godot-enums-and-bitfields).
- For `@Rpc` methods on Kanama scripts, use the generated `*Rpcs` senders
  instead of raw `rpc("method_name")` strings, and port `jump.rpc()` as a
  sender call, never as `jump()`: a direct call runs on this peer only.
- For scenes with `MultiplayerSynchronizer`, verify every replicated custom
  `.:property` is exposed with `@Export`.

When a typed lookup fails, check the actual runtime class before changing the
lookup. Godot often imports GLB scene roots as `Node3D`, with the mesh attached
as a descendant, even when the visual asset is conceptually a mesh.

For a multiplayer game, run the two [multiplayer checks](multiplayer.md#checks)
after the first working pass.

## Common Porting Patterns

Mixed projects are valid while you migrate. A Kanama script can call a retained
GDScript autoload through the normal Godot object API:

```kotlin
Autoloads.Audio.call("play", "res://sounds/coin.ogg")   // GDScript: Audio.play("res://sounds/coin.ogg")
```

A Kotlin autoload is typed to its script class, so `Autoloads.Settings.save()`
needs no lookup or cast (see [Scripts → Autoloads](scripts.md#autoloads)). A
script that `extends` another script class ports to a Kotlin subclass with no
forwarding overrides (see [Scripts → Script Inheritance](scripts.md#script-inheritance)),
and each `@export_*` annotation has a typed twin
([Exports and Resources](properties-resources.md#export-hints)).

When the original exported a node (`@export var target: Node3D`), export it
as its class, `@Export var target: Node3D? = null`. When it exported a path on
purpose, keep the `NodePath` and look the node up with it:

```kotlin
@Export var targetPath: NodePath = NodePath(".")
private var target: Node3D? = null

@OnReady
fun ready() {
    target = self.requireAs<Node3D>(targetPath)
}
```

Use `@GodotName("saved_signal_name")` when a `.tscn` file already has a
saved signal connection and you want to preserve the stored method name.

## Example Ports

The public
[kanama-demos](https://github.com/falcon4ever/kanama-demos) repository
includes ports that can be used as examples for common migration areas:

| Project | Useful For |
|---|---|
| Godot 2D Dodge the Creeps | 2D movement, saved scene connections, signals, timers, Android |
| Godot 3D Squash the Creeps | instanced scenes, 3D physics, animation, signal wiring |
| GDQuest 3D Third Person Controller | camera/control logic and CharacterBody3D movement |
| Godot 4 3D Character Controller Tutorial | compact 3D movement and input |
| Godot TPS Demo | larger 3D scenes, threaded loading, RPC senders, multiplayer synchronizers, and scene reload cleanup |
| Kenney Starter Kit 3D Platformer | `NodePath` exports, GDScript autoload interop, typed callbacks, coroutine delays |
| Kenney Starter Kit Match-3 | UI, typed resource lists, textures, scene-authored data |
| Kenney Starter Kit City Builder | tile/grid workflows and larger scene data |
| Kenney Starter Kit FPS | input, weapons/resources, 3D gameplay structure |

For building, reloading and debugging while you port, see
[Edit, Build, Run](../getting-started/editor-workflow.md).

## On Other Platforms

A port runs on desktop, Android and iOS from the same source. Web needs a few
changes, such as driving movement from `velocity` and `moveAndSlide()` instead
of re-reading a position you just wrote; see
[Limitations](../reference/limitations.md#web).
