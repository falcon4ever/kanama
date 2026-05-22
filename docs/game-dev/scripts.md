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
class Player(godotObject: MemorySegment) :
    KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D) {

    @OnPhysicsProcess
    fun physicsProcess(delta: Double) {
        if (self.isOnFloor()) self.moveAndSlide()
    }
}
```

Inside a `KanamaScript<T>`, use `selfAs` for a secondary view of the same
Godot object:

```kotlin
private val node3d = selfAs(::Node3D)
```

See [Kotlin Style — `self` And `selfAs` Caching](style-guide.md#self-and-selfas-caching).

## Scalar Types

| GDScript | Kanama | Note |
|---|---|---|
| `int` | `Long` | Use `Long` at all GDExtension boundaries |
| `float` (method args/returns) | `Double` | Godot's ABI uses 64-bit slots for scalar float |
| `bool` | `Boolean` | |
| `Vector3.x/y/z` | `Float` (`real_t`) | Matches Godot's default single-precision storage |
| `delta` in `_process` | `Double` | Engine timing is always double-precision |

The float/double split matches the official C# binding. Vector components are
`Float` so you can pass them without `.toDouble()` noise; `delta` and scalar
method arguments are `Double` for the same reason. See
[Kotlin Style — Float / Double](style-guide.md#float-double).

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

The function name is up to you — the annotation is what wires it up. Drop the
leading underscore; `fun ready()` is the convention.

## Exports

| GDScript | Kanama |
|---|---|
| `@export var speed = 5.0` | `@ScriptProperty var speed: Double = 5.0` |
| `@export_group("Movement")` | `@ExportGroup("Movement")` on first property in group |
| `@export var scene: PackedScene` | `@ScriptProperty var scene: PackedScene? = null` |
| `@tool` | `@Tool` on the class |

Use `@ScriptProperty` for `@ScriptClass` scripts and `@RegisterProperty` for
`@RegisterClass` types. `@Export` works as an alias in both contexts. See
[Exports and Resources](properties-resources.md).

## Printing

```kotlin
GD.print("Hello from Kotlin")       // mirrors C# GD.Print
System.err.println("[debug] $value") // also appears in Godot output
```

## Rebuild Required

GDScript changes are live. Kotlin scripts must be compiled before Godot sees
them. Use the `Build Scripts` toolbar button (Kanama Tools plugin) or:

```sh
./gradlew installAddonJar -PkanamaProjectDir=... -PkanamaProjectScriptsDir=...
```

Hot reload reloads the jar without restarting Godot. See
[The Editor Loop](../getting-started/editor-workflow.md).

## Packages Are Mandatory

Every script file must declare a package. Scripts without a package cannot
reference other scripts and cause issues with generated registrars:

```kotlin
package com.mygame.scripts

@ScriptClass(attachTo = "Node")
class MyScript(godotObject: MemorySegment) :
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

## Node Lookup

GDScript's `$NodeName` shorthand does not exist in Kanama. Use exported
`@ScriptProperty` references (preferred) or typed lookup helpers:

```kotlin
// Preferred: let the inspector wire it
@ScriptProperty var label: Label? = null

// Manual lookup for required scene structure
val label = self.requireAs("Label", ::Label)
```

Nested paths use the same slash-separated `NodePath` strings that Godot scene
files and GDScript use:

```gdscript
@onready var animation_player = $Character/AnimationPlayer
```

```kotlin
private val animationPlayer by lazy {
    self.requireAs("Character/AnimationPlayer", ::AnimationPlayer)
}
```

Use `requireAs` for nodes that must exist for the scene to work. Use
`getAsOrNull` when the node is optional:

```kotlin
val optionalMarker = self.getAsOrNull("Markers/Spawn", ::Node3D)
```

## Cross-Script References

To reference another Kanama script type (e.g. `var target: Vehicle?`), both
classes must be in the same project and the referenced class must be
`@GlobalClass`. See [Calling Godot APIs — Global Classes](godot-api.md#global-classes).

## Known Gotchas

- **Registered methods have no default arguments from Godot's side.** If a
  `@RegisterFunction` method has Kotlin default parameters, Godot still requires
  all arguments when calling it. Pass them explicitly or use overloads.
- **KSP must run** before IntelliJ resolves generated helpers like
  `PlayerSignals` or `PlayerRpcs`. Run a Gradle sync or build after adding new
  `@Signal` or `@Rpc` declarations.
- **`@Tool` scripts run in the editor.** Guard editor-only code against
  partially initialized scenes — exported node references may be `null` during
  editor tool execution. From a `KanamaScript` subclass, use `isEditorHint()`
  for editor checks and `notifyInspectorChanged()` after changing editor-time
  property-list state.
