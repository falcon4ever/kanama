# Porting GDScript

Use this page when converting an existing Godot project from GDScript to Kanama.
It focuses on project and scene issues that show up during real ports. For
language-level translation details, start with
[Writing Kotlin Scripts](scripts.md).

## Porting Workflow

1. Work in a branch or copy of the original Godot project.
2. Add the Kanama addon, Gradle project files, and a Kotlin source directory.
3. Port one script at a time, keeping scene names, exported property names, and
   signal connections close to the original.
4. Build the Kotlin scripts and open the project in Godot to let imported assets
   and script metadata settle.
5. Run the project headlessly or interactively and compare behavior against the
   original project.

Ports should stay close to the original GDScript behavior. If a port needs
different logic to work, prefer fixing Kanama and recording the blocker instead
of leaving a project-specific workaround.

## GDScript to Kotlin Mapping

The everyday script constructs, inside a `KanamaScript<T>` subclass (see
[Writing Kotlin Scripts](scripts.md) for each one):

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
| `const BULLET = preload("res://bullet.tscn")` | `private val bullet by preload<PackedScene>("res://bullet.tscn")` |
| `var b: RigidBody3D = BULLET.instantiate()` | `val b = bullet.instantiateAs<RigidBody3D>()` |
| `var c: Coin = COIN.instantiate()` (script root) | `val c = coin.instantiateScript<Coin>()`, node: `c.self` |
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
| `velocity.y += gravity * delta` | `velocity = velocity.withY(velocity.y + gravity * delta)` (value types are immutable) |
| `print(position)` → `(0.1, 0.2)` | `println(position)` → `(0.1, 0.2)` (same `str()` form) |
| `v.is_equal_approx(w)`, `v == w` | `v.isEqualApprox(w)`, `v == w` (compares the stored components, as in GDScript) |
| `global_transform * Vector3.FORWARD`, `basis * other_basis`, `quat * dir` | the same operators |
| `2.0 * v`, `Vector2i(4, 6) / 2`, `-color`, `a < b` (vectors) | the same (`2.0 * v` is an extension operator: `import net.multigesture.kanama.types.*`, which new scripts already have) |
| `x ** y` | `x.pow(y)` (`kotlin.math`) or `GD.pow(x, y)` (Godot's `pow`): Kotlin has no power operator |
| `Color.RED`, `Vector2i.LEFT`, `Vector3.MODEL_FRONT` | the same |
| `v.direction_to(t)`, `rect.get_center()`, `Color.from_hsv(h, s, v)` | `v.directionTo(t)`, `rect.getCenter()`, `Color.fromHsv(h, s, v)` |

Decimals behave as in GDScript: every decimal is `Double` (GDScript's `float`), and a vector,
transform or color stores its components at Godot's width (float32 in normal builds), so equality,
printing and vector arithmetic give the same results as the GDScript you port, including the
caveat that `Vector2(0.1, 0.2).x == 0.1` is `false`. See
[API conventions — Decimals](../reference/wrapper-conventions.md#4-decimals).

A function that `await`s in GDScript becomes a `launch { }` block: everything
after the first suspension runs on a later frame, and the block stops when the
script's object is freed.

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
  `@GodotName("_on_coin_collected")`, or update the connection. Make helpers Godot
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
- For `@Rpc` methods on Kanama scripts, use generated `*Rpcs` sender helpers
  instead of raw `rpc("method_name")` strings.
- For scenes with `MultiplayerSynchronizer`, verify every replicated custom
  `.:property` is exposed with `@Export`.
- If the port will also run on Web: physics loops should derive movement from
  velocity (`self.velocity` + `moveAndSlide()`), not from re-reading a spatial
  value they just wrote — Web spatial reads are a start-of-dispatch mirror, not
  a live engine call. See the Web export guide's Web-Compatible Project Scripts
  rules.

When a typed lookup fails, check the actual runtime class before changing the
helper. Godot often imports GLB scene roots as `Node3D`, with the mesh attached
as a descendant, even when the visual asset is conceptually a mesh.

For multiplayer ports, run the static guardrails after the first working pass:

```sh
python3 scripts/audit_runtime_node_lookups.py /path/to/godot_project/kotlin-src
python3 scripts/audit_replicated_script_properties.py /path/to/godot_project
```

Both scripts accept multiple roots. Aggregate demo repositories can pass every
`kotlin-src` or project root in one run; the public `kanama-demos` repo wires
those checks into `./gradlew check`.

## Common Porting Patterns

Mixed projects are valid while you migrate. A Kanama script can call a retained
GDScript autoload through the normal Godot object API:

```kotlin
Autoloads.Audio.call("play", path)   // GDScript: Audio.play(path)
```

A Kotlin autoload is typed to its script class, so `Autoloads.Settings.save()`
needs no lookup or cast (see [Scripts → Autoloads](scripts.md#autoloads)). A
script that `extends` another script class ports to a Kotlin subclass with no
forwarding overrides (see [Scripts → Script Inheritance](scripts.md#script-inheritance)),
and each `@export_*` annotation has a typed twin
([Exports and Resources](properties-resources.md#export-hints)).

Exported `NodePath` values are a good first step when the original script used
`@export var target: NodePath`:

```kotlin
@Export var targetPath: NodePath = NodePath(".")

private val target by lazy {
    self.requireAs<Node3D>(targetPath.path)
}
```

Use `@GodotName("saved_signal_name")` when a `.tscn` file already has a
saved signal connection and you want to preserve the stored method name.

### GDScript annotations in Kanama

Kanama has one annotation per GDScript concept (Kanama 0.5 removed the older
aliases; `scripts/migrate_script_annotations.py` rewrites a source tree):

| GDScript | Kanama |
|---|---|
| `func f()` (callable from Godot) | a public `fun f()`; `@GodotName("...")` for another name |
| `@export var x` | `@Export var x` |
| `@export_category` / `_group` / `_subgroup` | `@ExportCategory` / `@ExportGroup` / `@ExportSubgroup` |
| `@export_tool_button("Label")` | `@ExportToolButton("Label")` |
| `signal hit(damage)` | `@Signal fun hit(damage: Long) = Unit`; typed handle `player.hit` (`hit.emit(5)`, `player.hit.connect { damage -> }`) |
| `@rpc(...)` | `@Rpc(...)` on a public function |
| `@tool` | `@Tool` |
| `class_name Player` | `@GlobalClass` |
| `func _ready()`, `_enter_tree`, `_exit_tree` | `@OnReady`, `@OnEnterTree`, `@OnExitTree` |
| `func _process(delta)`, `_physics_process` | `@OnProcess`, `@OnPhysicsProcess` |
| `func _input(event)`, `_unhandled_input`, `_shortcut_input`, `_unhandled_key_input` | `@OnInput`, `@OnUnhandledInput`, `@OnShortcutInput`, `@OnUnhandledKeyInput` (`event: InputEvent`) |
| any other `func _virtual()` | `@OverrideVirtual fun _virtual()` |

## Example Ports

The public
[kanama-demos](https://github.com/falcon4ever/kanama-demos) repository
includes ports that can be used as examples for common migration areas:

| Project | Useful For |
|---|---|
| Godot 2D Dodge the Creeps | 2D movement, saved scene connections, signals, timers, Android smoke |
| Godot 3D Squash the Creeps | instanced scenes, 3D physics, animation, signal wiring |
| GDQuest 3D Third Person Controller | camera/control logic and CharacterBody3D movement |
| Godot 4 3D Character Controller Tutorial | compact 3D movement and input |
| Godot TPS Demo | larger 3D scenes, threaded loading, RPC helpers, multiplayer synchronizers, and scene reload cleanup |
| Kenney Starter Kit 3D Platformer | `NodePath` exports, GDScript autoload interop, typed callbacks, coroutine delays |
| Kenney Starter Kit Match-3 | UI, typed resource lists, textures, scene-authored data |
| Kenney Starter Kit City Builder | tile/grid workflows and larger scene data |
| Kenney Starter Kit FPS | input, weapons/resources, 3D gameplay structure |

For Kanama maintainer rules around demo parity, smoke checks, and when a port
should drive runtime changes, see
[Demo Porting Rules](../contributing/demo-porting-rules.md).

For editor build, scene reload, and IntelliJ debugging, see
[The Editor Loop](../getting-started/editor-workflow.md).
