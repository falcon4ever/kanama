# Coming from C#

Kanama has what you use in Godot's C# API: the same classes, a `GD` and a
`Mathf` object, attributes for exports and signals, and typed signals you
connect with a lambda. The names are Kotlin's lowerCamelCase instead of
PascalCase, and only that spelling exists: there is no `MoveAndSlide()` beside
`moveAndSlide()`. This page maps the C# you know to Kotlin and lists where the
two differ.

## A Script Side by Side

```csharp
using Godot;

public partial class Player : CharacterBody2D
{
    [Export] public float Speed = 300.0f;

    [Signal] public delegate void HitEventHandler(int damage);

    public override void _PhysicsProcess(double delta)
    {
        Vector2 dir = Input.GetVector("move_left", "move_right", "move_up", "move_down");
        Velocity = dir * Speed;
        MoveAndSlide();
    }

    public void TakeDamage(int amount) => EmitSignal(SignalName.Hit, amount);
}
```

```kotlin
package com.mygame

import net.multigesture.kanama.annotations.Export
import net.multigesture.kanama.annotations.OnPhysicsProcess
import net.multigesture.kanama.annotations.ScriptClass
import net.multigesture.kanama.annotations.Signal
import net.multigesture.kanama.api.*
import net.multigesture.kanama.generated.hit
import net.multigesture.kanama.types.*

@ScriptClass(attachTo = "CharacterBody2D")
class Player(godotObject: GodotHandle) :
    KanamaScript<CharacterBody2D>(godotObject, ::CharacterBody2D) {

    @Export var speed: Double = 300.0

    @Signal fun hit(damage: Long) = Unit

    @OnPhysicsProcess
    fun physicsProcess(delta: Double) {
        val dir = Input.getVector("move_left", "move_right", "move_up", "move_down")
        self.velocity = dir * speed
        self.moveAndSlide()
    }

    fun takeDamage(amount: Long) {
        hit.emit(amount)
    }
}
```

- In C#, the script class *is* the node, so `Velocity` and `MoveAndSlide()`
  need no receiver. In Kotlin, the script is a separate object and the node is
  `self`: `self.velocity`, `self.moveAndSlide()`.
- `@ScriptClass(attachTo = ...)` takes the place of the base class, and there
  is no `partial`.
- Every decimal is a `Double`, including `Speed` and vector components, and an
  integer Godot can see is a `Long`.
- A signal is a `@Signal` function. The build generates a typed handle with the
  same name, `hit`, that you emit, connect and await.
- `[Export]`, `_PhysicsProcess` and the other members map one to one; the
  tables below list them.

## Scripts and Attributes

| C# | Kotlin |
|---|---|
| `public partial class Player : CharacterBody3D` | `@ScriptClass(attachTo = "CharacterBody3D") class Player(godotObject: GodotHandle) : KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D)` |
| `this.Position`, `Position` | `self.position` (`this` is the Kotlin script object) |
| `public override void _Ready()` | `@OnReady fun ready()` |
| `_Process(double delta)`, `_PhysicsProcess` | `@OnProcess fun process(delta: Double)`, `@OnPhysicsProcess` |
| `_EnterTree()`, `_ExitTree()` | `@OnEnterTree`, `@OnExitTree` |
| `_Input(InputEvent @event)`, `_UnhandledInput` | `@OnInput fun input(event: InputEvent)`, `@OnUnhandledInput` |
| any other virtual, such as `_GetAabb()` | `@OverrideVirtual fun _get_aabb(): AABB`, with Godot's own name |
| a public method GDScript calls | a public `fun`; every public function is visible to Godot |
| `[Export] public float Speed = 5.0f;` | `@Export var speed = 5.0` |
| `[ExportGroup("Movement")]`, `[ExportCategory]`, `[ExportSubgroup]` | `@ExportGroup("Movement")`, `@ExportCategory`, `@ExportSubgroup` |
| `[ExportToolButton("Bake")]` | `@ExportToolButton("Bake")` on a function |
| `[Tool]` | `@Tool` |
| `[GlobalClass]` | `@GlobalClass`; as in C#, the file is named after the class |
| `[Rpc]` | `@Rpc` on a public function. `ENetMultiplayerPeer` and `MultiplayerSynchronizer` work as in C#; see [Multiplayer](../game-dev/multiplayer.md) |
| `[Signal] public delegate void HitEventHandler(int damage);` | `@Signal fun hit(damage: Long) = Unit` |

[Writing Scripts](../game-dev/scripts.md) covers each of these, and
[Exports and Resources](../game-dev/properties-resources.md) every export
attribute.

## Nodes and Resources

| C# | Kotlin |
|---|---|
| `GetNode<Timer>("ScoreTimer")` | `self.requireAs<Timer>("ScoreTimer")`, or a field: `private val timer by node<Timer>("ScoreTimer")` |
| `GetNodeOrNull<Timer>("Path")` | `self.getNodeAs<Timer>("Path")` |
| `body as CharacterBody3D` | `body.castOrNull<CharacterBody3D>()` |
| `(CharacterBody3D)body` | `body.cast<CharacterBody3D>()` |
| `body is Player`, where `Player` is a script | `body.isScript<Player>()`, and `body.asScript<Player>()` to get it |
| `GD.Load<PackedScene>("res://mob.tscn")` | `private val mobScene by preload<PackedScene>("res://mob.tscn")` |
| `mobScene.Instantiate<RigidBody3D>()` | `mobScene.instantiateAs<RigidBody3D>()`, or `instantiateScript<Mob>()` for a scene whose root has a script |
| `GetTree()`, `GetParent()` | `self.tree`, `self.parentNode` |
| `GetNode<Global>("/root/Global")` for an autoload | `Autoloads.Global`, typed |
| `node.Call("method", 1)` | `node.call("method", 1)` |
| `QueueFree()` | `self.queueFree()` |
| `new Weapon()` for a resource script | `newScriptInstance<Weapon>()` |
| `material.Dispose()`, `using var file = ...` | `material.close()`, `FileAccess.open(...)?.use { }` |

A resource you create or load holds a reference until you `close()` it, or
until the garbage collector drops your last use of it, as a C# finalizer would.
[Resource Ownership](../game-dev/godot-api.md#resource-ownership) has the
rules.

## Signals and Await

| C# | Kotlin |
|---|---|
| `timer.Timeout += OnTimeout;` | `timer.timeout.connect { onTimeout() }` |
| `area.BodyEntered += body => { };` | `area.bodyEntered.connect { body -> }`, with `body` typed as `Node3D` |
| `EmitSignal(SignalName.Hit, 5)` | `hit.emit(5L)` |
| `player.Hit += OnHit;` | `player.hit.connect { damage -> onHit(damage) }` |
| `Callable.From(() => Fire())` | a Kotlin lambda, passed to `connect` |
| `await ToSignal(timer, Timer.SignalName.Timeout);` | `launch { timer.timeout.await() }` |
| `await ToSignal(GetTree().CreateTimer(1.0), SceneTreeTimer.SignalName.Timeout);` | `launch { wait(1.0) }` |

A `launch { }` block is the `async` method: code after the first `await` or
`wait` runs on a later frame, and the block stops when the script's node is
freed. See [Signals and Await](../game-dev/signals.md).

## Types

| C# | Kotlin |
|---|---|
| `float`, `double` | `Double` |
| `int`, `long` | `Long`; a few engine methods take `Int` where Godot declares a 32-bit value |
| `string`, `StringName` | `String` |
| `Godot.Collections.Array<Node3D>` | `List<Node3D>` |
| `Godot.Collections.Dictionary` | `Map<String, Any?>` |
| `Godot.Collections.Dictionary<string, int>` export | `Map<String, Long>` export |
| `Variant` | `Any?` |
| `byte[]` | `ByteArray` |
| `Rid` | `RID` |
| `Node.ProcessModeEnum.Always` | `Node.ProcessMode.ALWAYS` |
| `Key.Escape`, `Error.Ok` | `Key.ESCAPE`, `GodotError.OK` |
| `GD.Print(x)`, `GD.PushError(msg)` | `GD.print(x)`, `GD.pushError(msg)` |
| `Mathf.Lerp(a, b, t)` | `Mathf.lerp(a, b, t)` |
| `PhysicsRayQueryParameters3D.Create(from, to)` | `PhysicsRayQueryParameters3D.create(from, to)` |
| `spaceState.IntersectRay(query)` | `spaceState.intersectRay(query)`, a `Map<String, Any?>` |

Vectors are immutable, as in C#, so you assign a new one:
`self.velocity = self.velocity.withY(0.0)`. An exported `Map<K, V?>` keeps a
key whose value is `null`, as C#'s `Dictionary` does; a `Map<K, V>` drops it.
[Calling Godot APIs](../game-dev/godot-api.md) covers numbers, enums and
collections.

## Building and Editing

There is no `.csproj` and no .NET SDK. Your scripts are a Gradle project that
IntelliJ IDEA opens directly, and Kotlin's annotation processor generates the
signal handles, the `Autoloads` object and the code Godot calls. Press
**Build Scripts** in the Godot toolbar, or turn on building on save in the
project settings. IntelliJ shows Godot's own class reference as the
documentation of the generated classes and methods. See
[Edit, Build, Run](../getting-started/editor-workflow.md) and
[Open in IntelliJ IDEA](../getting-started/intellij.md).

A desktop export carries its own Java runtime, the way a C# export carries the
.NET runtime, so players install nothing. See
[Export for Desktop](../exporting/desktop.md).

## Debugging and Hot Reload

You attach IntelliJ's **Remote JVM Debug** to the running game, set
breakpoints and step through your scripts; see
[Debugging](../getting-started/editor-workflow.md#debugging). A build reloads
your scripts in the editor and in a running game. Both work on desktop only.
A `@RegisterClass` type, a Kotlin class registered as a new Godot class, needs
an editor restart instead; see
[Limitations](limitations.md).

## Next Steps

- [Writing Scripts](../game-dev/scripts.md) walks through a script in detail.
- [API Coverage](generated/api-coverage.md) lists every generated class and
  method.
- [Limitations](limitations.md) lists what each platform cannot do.
