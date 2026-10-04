# Signals and Callbacks

Every Godot signal has a typed Kotlin property, custom signals are declared with
`@Signal`, and both connect to Kotlin lambdas or to registered methods by name.

## Engine Signals

Each engine signal is a property of its class, typed from Godot's API
(`area.bodyEntered` is a `Signal1<Node3D>`, `timer.timeout` a `Signal0`). Inside a
`KanamaScript` it reads like GDScript:

```gdscript
func _ready():
    area.body_entered.connect(func(body): body.hide())
    timer.timeout.connect(_on_timeout, CONNECT_ONE_SHOT)
    await timer.timeout
    var body = await area.body_entered
```

```kotlin
@OnReady
fun ready() {
    area.bodyEntered.connect { body -> body.hide() }        // body: Node3D
    timer.timeout.connect(GodotObject.ConnectFlags.ONE_SHOT) { onTimeout() }
    launch {
        timer.timeout.await()
        val body = area.bodyEntered.await()
    }
}
```

- `connect { }` inside a script binds the lambda to the script's object, as a
  GDScript lambda is bound to its script: Godot drops the connection (and Kanama
  releases the lambda and what it captured) when that object or the emitter is
  freed, or after one call with `ConnectFlags.ONE_SHOT`. Outside a script pass the
  object explicitly: `area.bodyEntered.connect(owner) { body -> }`.
  `ConnectFlags.DEFERRED` (and `or`-combinations) work as in Godot.
- `connect` returns a `SignalConnection`; `close()` disconnects early (or scope it
  with `use { }`).
- `await()` suspends inside `launch { }`. A one-argument signal returns the value;
  two or more return a `SignalArgs2`…`SignalArgs5` to destructure
  (`val (id, data) = peer.await()`, where GDScript returns an `Array`). If the
  emitting object is freed first, the waiting coroutine is cancelled, and freeing
  the script cancels the wait, as for any script coroutine.
- `emit(…)` emits with typed arguments: `button.pressed.emit()`.
- `connect(target, "method_name")` connects a registered method by name, like a
  scene connection.

Argument types follow the API: Godot `int` is `Long`, `float` is `Double`,
`String` and `StringName` are `String`, an object is its wrapper (borrowed, as a
registered function's argument; `null` only for `Resource`-like types), value types
and collections are the usual Kotlin types, and `Variant` is `Any?`. Godot's API does
not mark engine signal arguments as enums, so they arrive as `Long`. A value of
another type (a GDScript `emit_signal` is not type-checked) is reported as a script
error naming the signal, and the lambda is not called. A property that would
collide with a member of its class gets a `Signal` suffix; no Godot 4.7 signal does.

A signal Godot or a GDScript declares at runtime gets a typed handle the same way:

```kotlin
val healthChanged = Signal1(events, "health_changed", SignalArgType.LONG)
healthChanged.connect { hp -> bar.value = hp.toDouble() }
```

`X.Signals` constants (`Area3D.Signals.bodyEntered = "body_entered"`) remain for
string-based APIs, as does the untyped `signal(name)` handle described below.

**Web.** The Web bridge delivers a signal's first argument only, so Web has
`Signal0` and `Signal1` (an object, `int`, `float`, `bool`, `String`/`StringName`,
`Vector2`, `Vector2i`, `Vector3` or a Godot enum), on the signals its wrappers
expose (`timeout`, `pressed`, `finished`, `body_entered`/`body_exited`,
`animation_finished`, `size_changed`); `connect`, `await`, `emit` and the script's
`connect { }` work as above. Signals with two or more arguments and the other
argument types are not available on Web yet.

## Custom Signals

Declare custom signals with `@Signal`. KSP generates a typed `*Signals` helper
for each `@RegisterClass` and `@ScriptClass` declaration:

```kotlin
@Signal
fun pinged(value: Long) = Unit

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

KSP also generates a typed handle per signal, an extension property on the
script class in `net.multigesture.kanama.generated` (the same `Signal0`…`Signal5`
as the engine signals): `coinCollected.emit(coins)` inside `Player`,
`player.coinCollected.connect { coins -> }` and `player.coinCollected.await()`
outside it. A signal with an argument that has no typed decode (a primitive
packed array, a script class) or more than five arguments keeps only the helpers
above.

A signal argument can be one of Godot's enums (`Node.ProcessMode`,
`BaseMaterial3D.Flags`, see [Godot Enums and Bitfields](godot-api.md#godot-enums-and-bitfields)).
It is emitted as the `int` it stands for, which is what a GDScript handler
connected to the signal receives. The generated `connect*` helper hands its
Kotlin callback every argument typed, and `await*` returns the typed value for
a one-argument signal (for two or more arguments `await*` returns the raw
`List<Any?>`, where an enum is its `Long`):

```kotlin
@Signal
fun modeChanged(mode: Node.ProcessMode) = Unit

val connection =
    PlayerSignals.connectModeChanged(this, self) { mode -> if (mode == Node.ProcessMode.DISABLED) pause() }
PlayerSignals.modeChanged(this, Node.ProcessMode.DISABLED)
```

A lambda connected with the lower-level `signal(...).connect { args -> }` sees
the raw `Long` (as any dynamic call does); wrap it with `Node.ProcessMode(raw)`.

RPC methods follow the same generated-helper pattern. See
[Multiplayer](multiplayer.md#rpc-methods) for `@Rpc` sender helpers such as
`PlayerRpcs.rpcJump(...)` and `PlayerRpcs.callLocalJump(...)`.

## Connecting Signals

To connect an engine signal to a registered method by name, use the typed
property or the untyped `signal(name)` handle:

```kotlin
import net.multigesture.kanama.generated.PlayerNames

fun onBodyEntered(body: GodotObject) {
    if (body.isClass("CharacterBody3D")) {
        collectCoin()
    }
}

// PlayerNames.Methods.onBodyEntered is generated for the public function
// above. It is the Godot-facing name "on_body_entered".
area.bodyEntered.connect(self, PlayerNames.Methods.onBodyEntered)
```

The connection layer mirrors Godot's Callable model. The target is a Godot
object or Kanama script instance, and the method name is the Godot-facing method
name. Every public function is registered: for `fun onBodyEntered(...)` that
name is `on_body_entered`; if you give it `@GodotName("...")`, connect to that
name.

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
@GodotName("_on_body_entered")
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
scripts, and verifies the target script registers the method: a public
function under its snake_case name or its `@GodotName`.

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
val mob = mobScene?.instantiate() ?: return
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

The typed engine signals above are the usual way to connect a lambda. The untyped
handle remains for a signal known only by name: a Kotlin lambda is connected as a
Godot Callable bound to a target object, so pass the script's owning object
(`self`) as the target so the connection has a real object lifetime:

```kotlin
val connection = area.signal(Area3D.Signals.bodyEntered).connectObject(self) { body ->
    if (body.isClass("CharacterBody3D")) {
        collect()
    }
}

connection.close()
```

Lifetime is the same as for the typed form: the connection dies with its target
or the emitter, or after a one-shot call, and Kanama then releases the lambda.
This holds on desktop, Android and iOS; the Web backend does not release them
yet.

`connect(target, argumentCount) { args -> ... }` receives the first
`argumentCount` emitted arguments as a `List<Any?>` (no upper limit since task
134). `connectObject` is the one-argument object shortcut. `await(target,
argumentCount)` and `awaitObject(target)` are cancellation-aware suspend helpers
built on the same dispatch path.

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
fun onBodyEntered(body: Node3D) {
    body.hide()
}
```
