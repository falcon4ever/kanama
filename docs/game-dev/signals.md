# Signals and Callbacks

Kanama supports custom signal declaration and emission for registered classes,
plus Callable-based connection helpers for Godot-owned objects and Kanama
script instances.

## Custom Signals

Declare custom signals with `@Signal`. KSP generates a typed `*Signals` helper
for each `@RegisterClass` and `@ScriptClass` declaration:

```kotlin
@Signal
fun pinged(value: Long) = Unit

@RegisterFunction
fun ping(): Long {
    HelloScriptSignals.pinged(this, counter)
    return counter
}
```

These helpers live in `net.multigesture.kanama.generated` and are produced by
the Kanama KSP processor in the game project. External projects should apply
the KSP plugin and depend on `net.multigesture.kanama:processor` through KSP;
then IDEs see the generated source directory after Gradle sync.

When porting a GDScript custom signal, declare the signal with a Kotlin
camelCase method name. KSP exposes it to Godot as snake_case and generates a
typed emitter:

```gdscript
signal coin_collected
coin_collected.emit(coins)
```

```kotlin
@Signal
fun coinCollected(value: Long) = Unit

PlayerSignals.coinCollected(this, coins)
```

The Kotlin declaration exists so Godot sees `coin_collected` in script metadata
and saved `.tscn` connections can keep referring to that signal.

Generated signal helpers also include typed handles for code connections and
awaits. For a `coinCollected(value: Long)` signal, KSP emits
`PlayerSignals.signalCoinCollected(...)`,
`PlayerSignals.connectCoinCollected(...)`, and
`PlayerSignals.awaitCoinCollected(...)` alongside the existing typed emitter.
The string-based `signal(PlayerNames.Signals.coinCollected)` style remains
available when you need lower-level Godot API behavior.

RPC methods follow the same generated-helper pattern. See
[Multiplayer](multiplayer.md#rpc-methods) for `@Rpc` sender helpers such as
`PlayerRpcs.rpcJump(...)` and `PlayerRpcs.callLocalJump(...)`.

## Connecting Signals

For existing Godot signals, use `signal(name)` when you want a small handle:

```kotlin
import net.multigesture.kanama.generated.PlayerNames

@RegisterFunction
fun onBodyEntered(body: GodotObject) {
    if (body.isClass("CharacterBody3D")) {
        collectCoin()
    }
}

// PlayerNames.Methods.onBodyEntered is generated from the
// @RegisterFunction above. It is the Godot-facing name "on_body_entered".
area.signal(Area3D.Signals.bodyEntered)
    .connect(self, PlayerNames.Methods.onBodyEntered)
```

The connection layer mirrors Godot's Callable model. The target is a Godot
object or Kanama script instance, and the method name is the Godot-facing method
name. For `@RegisterFunction fun onBodyEntered(...)`, that default name is
`on_body_entered`; if you use `@RegisterFunction(name = "...")`, connect to the
explicit name.

KSP generates `*Names` sidecar objects for Kanama classes, including `Methods`,
`Properties`, and `Signals` constants. Use those constants when referring to
Kanama-owned names from string-based Godot APIs. For common Godot-owned
signals, wrappers expose small `Signals` constant objects as they are added,
such as `Node.Signals.childEnteredTree`, `Area3D.Signals.bodyEntered`, and
`BaseButton.Signals.pressed`.

## Scene Connections

Scene files can store signal connections, but you can also wire them explicitly
from code when you want the relationship to be searchable during a port:

```kotlin
val player = node.requireAs("Player", ::Node)
val hud = node.requireAs("HUD", ::Node)

player.signal(PlayerNames.Signals.coinCollected)
    .connect(hud, HudNames.Methods.onCoinCollected)
```

Both forms use the same Godot signal system. Scene connections are visible in
the editor and serialized in `.tscn`; code connections are often easier to
review because the emitter, receiver, and method name live together.

Saved scene connections are strict because the method name is stored in the
`.tscn` file. If a scene was created from GDScript and connects to
`_on_body_entered`, the Kotlin method must expose that exact Godot-facing name:

```kotlin
@RegisterFunction("_on_body_entered")
fun onBodyEntered(body: Node) {
    collect()
}
```

Without the explicit name, Kanama exposes the method as `on_body_entered`,
which does not match the saved scene connection. The same rule applies to
custom signal receivers such as `_on_coin_collected`.

To catch this during ports, run:

```sh
python3 /path/to/kanama/scripts/scene_connection_lint.py /path/to/godot_project
```

The lint walks `.tscn` connections, resolves target nodes with attached Kotlin
scripts, and verifies the target method is exposed by `@RegisterFunction` or
`@Method`. KSP also warns when a public `onSomething` method in a
`@ScriptClass` is not registered, since that often means a scene signal
callback was missed.

## Dynamic Scene Instances

For dynamically instantiated scenes, code connections often look a little more
explicit because both sides are Godot objects at runtime. In the Godot 3D
Squash the Creeps demo, the original GDScript wires each spawned mob to the
score label like this:

```gdscript
mob.squashed.connect($UserInterface/ScoreLabel._on_Mob_squashed)
```

The Kanama equivalent uses explicit objects and Godot-facing names:

```kotlin
val mob = mobScene.instantiate()
val scoreLabel = self.requireAs("UserInterface/ScoreLabel", ::Label)
val mobScript = mob.kotlinScriptInstance<Mob>()
    ?: error("Instantiated mob scene is not backed by Mob")

mobScript.initialize(mobSpawnLocation.position, playerPosition)

mob.signal(MobNames.Signals.squashed)
    .connect(scoreLabel, ScoreLabelNames.Methods.onMobSquashed)
```

Read this as:

- `kotlinScriptInstance<Mob>()` retrieves the Kotlin script object attached to
  the spawned scene root, so Kotlin-to-Kotlin calls can use
  `mobScript.initialize(...)` instead of `GodotObject.call("initialize", ...)`.
- `mob` is the specific scene instance that will emit.
- `mob.signal(MobNames.Signals.squashed)` selects the signal on that instance.
- `connect(...)` tells Godot which object and Godot-facing method to call when
  the signal fires.

Prefer typed Kotlin calls when both scripts are Kanama scripts. Use
`GodotObject.call("method_name", ...)` when crossing a dynamic boundary:
calling into GDScript, calling editor/runtime APIs whose methods are not
wrapped yet, or invoking methods discovered by name at runtime.

## Lambda Callbacks And Await

For Kotlin lambda callbacks, generated `@ScriptClass` instances expose small
dispatcher methods that Godot can call through its Callable system. Pass the
script's owning object as the target so Godot has a real object lifetime to
connect against:

```kotlin
val connection = area.signal(Area3D.Signals.bodyEntered).connectObject(self) { body ->
    if (body.isClass("CharacterBody3D")) {
        collect()
    }
}

connection.close()
```

`connect(target, argumentCount) { args -> ... }` supports zero to three emitted
arguments today. `connectObject` is the common one-argument shortcut for signals
such as `body_entered`. `await(target, argumentCount)` and `awaitObject(target)`
are cancellation-aware suspend helpers built on the same dispatch path.

For custom Kanama signals, prefer the generated typed helper when both the
emitter and receiver are Kotlin-visible:

```kotlin
val connection = PlayerSignals.connectCoinCollected(playerScript, self) { coins ->
    updateHud(coins)
}
```

Generated method dispatch accepts common object wrappers directly, so a method
connected to `body_entered` can take a typed wrapper:

```kotlin
@RegisterFunction
fun onBodyEntered(body: Node3D) {
    body.hide()
}
```
