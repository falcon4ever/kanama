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
- For instanced scenes, prefer `getAsOrNull(path, ::ParentType)` unless the
  exact imported root class is known and stable.
- Prefer `requireAs` when the original scene requires a node to exist. Use
  `getAsOrNull` only when the original behavior was optional.
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
self.getAsOrNull("/root/Audio", ::Node)
    ?.call("play", path)
```

Exported `NodePath` values are a good first step when the original script used
`@export var target: NodePath`:

```kotlin
@Export var targetPath: NodePath = NodePath(".")

private val target by lazy {
    self.requireAs(targetPath, ::Node3D)
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
| `signal hit(damage)` | `@Signal fun hit(damage: Long) = Unit` |
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
