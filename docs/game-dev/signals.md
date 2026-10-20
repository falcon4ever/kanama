# Signals and Await

Every Godot signal is a typed Kotlin property, and your own signals are
declared with `@Signal`. You connect them to lambdas or to functions by name,
and you `await` them inside a coroutine, as in GDScript. The snippets sit
inside a script like the one in
[Writing Scripts](scripts.md#a-complete-script), which lists the imports.

## Engine Signals

```gdscript
func _ready():
    area.body_entered.connect(func(body): body.hide())
    timer.timeout.connect(_on_timeout, CONNECT_ONE_SHOT)
    await timer.timeout
    var body = await area.body_entered
```

```kotlin
private val area by node<Area3D>("Area")
private val timer by node<Timer>("Timer")

@OnReady
fun ready() {
    area.bodyEntered.connect { body -> body.hide() }   // body is a Node3D
    timer.timeout.connect(GodotObject.ConnectFlags.ONE_SHOT) { onTimeout() }
    launch {
        timer.timeout.await()
        val body = area.bodyEntered.await()
        GD.print("entered: ", body.name)
    }
}

fun onTimeout() {
    GD.print("time is up")
}
```

`area.bodyEntered` is a `Signal1<Node3D>` and `timer.timeout` a `Signal0`,
typed from Godot's API:

- **`connect { }`** binds the lambda to your script's node, as a GDScript
  lambda is bound to its script. Godot drops the connection, and Kanama
  releases the lambda, when that node or the emitter is freed, or after one
  call with `ConnectFlags.ONE_SHOT`. `ConnectFlags.DEFERRED` and
  `or`-combinations work as in Godot. Outside a script, pass the owner
  yourself: `area.bodyEntered.connect(owner) { body -> }`.
- **`connect` returns a `SignalConnection`.** `close()` it to disconnect early,
  or scope it with `use { }`.
- **`await()`** suspends inside `launch { }`. A one-argument signal returns the
  value. A signal with two to five arguments returns them to destructure:
  `val (id, data) = signal.await()`, where GDScript returns an `Array`. If the
  emitter is freed first, or your script's node is, the wait is cancelled.
- **`emit(...)`** takes typed arguments: `button.pressed.emit()`.
- **`connect(target, "method_name")`** connects a function by name, as a scene
  connection does. See [Connecting by Name](#connecting-by-name).

## Signal Arguments

```kotlin
private var lastEvent: InputEvent? = null

@OnReady
fun ready() {
    val control = self.requireAs<Control>("Panel")
    control.guiInput.connect { event -> lastEvent = event }   // safe to keep
}
```

Arguments follow Godot's API: an `int` is `Long`, a `float` is `Double`,
`String` and `StringName` are `String`, an object is its class, and a
`Variant` is `Any?`. Godot does not mark engine signal arguments as enums, so
an engine signal hands you an enum as a `Long`; wrap it with
`Node.ProcessMode(value)` when you want the type. An object argument is
non-null unless Godot can emit null for it, such as the body of
`Area3D.bodyShapeEntered` when the body has no node.

You can keep any argument, as in GDScript. A `Resource` or `InputEvent`
argument is a reference of your own: `close()` it when you are done, or the
garbage collector releases it later (see
[Resource Ownership](godot-api.md#resource-ownership)). A `Node` argument is the
node itself: it is `==` to your own reference to it, and it stays usable until
the node is freed.

A value of the wrong type, from a GDScript `emit_signal` for example, is
reported as a script error that names the signal, and the lambda is not
called.

## Custom Signals

```kotlin
import net.multigesture.kanama.generated.coinCollected

@ScriptClass(attachTo = "Area2D")
class Coin(godotObject: GodotHandle) : KanamaScript<Area2D>(godotObject, ::Area2D) {
    @Signal
    fun coinCollected(value: Long) = Unit

    fun collect() {
        coinCollected.emit(10L)
    }
}
```

```gdscript
signal coin_collected(value)
coin_collected.emit(10)
```

A `@Signal` function declares the signal; Godot sees it as `coin_collected`,
so saved scene connections to it keep working. The build generates a typed
handle with the same name, which you emit, connect and await like an engine
signal. Calling the `@Signal` function itself does nothing: emit with
`coinCollected.emit(...)`. A property named like the handle would hide it, so
the build rejects one.

From another script, use the handle on the script object:

```kotlin
import net.multigesture.kanama.generated.coinCollected

private var score = 0L
private val coin by script<Coin>("Coin")

@OnReady
fun ready() {
    coin.coinCollected.connect { value -> score += value }
}
```

When you hold the emitter as a Godot object rather than as its script, such as
an autoload node or a node you looked up, the generated `CoinSignals` object
gives the same handle: `CoinSignals.coinCollected(node).connect { value -> }`.

A Godot enum works as a signal argument (`@Signal fun modeChanged(mode:
Node.ProcessMode) = Unit`). GDScript receives the `int`, and the typed handle
hands Kotlin the `Node.ProcessMode`.

A signal with more than five arguments, or with an argument that has no typed
form such as a packed array, has no typed handle. Connect it by name, or with
the `CoinSignals.connectCoinCollected(...)` and `awaitCoinCollected(...)`
functions the build generates for every signal.

## Connecting by Name

```kotlin
import net.multigesture.kanama.generated.PlayerNames

@OnReady
fun ready() {
    val area = self.requireAs<Area3D>("Area")
    area.bodyEntered.connect(self, PlayerNames.Methods.onBodyEntered)
}

fun onBodyEntered(body: Node3D) {
    body.hide()
}
```

Every public function of a script can be a signal target under its
`snake_case` name, here `on_body_entered`. The build generates a `PlayerNames`
object for each script, with `Methods`, `Properties` and `Signals` constants,
so you never spell the name by hand. The target function may take the
argument as its class (`Node3D`) instead of `GodotObject`. Engine classes have
`Signals` constants too, such as `Area3D.Signals.bodyEntered`.

## Signals Known Only by Name

```kotlin
@OnReady
fun ready() {
    val events = self.requireAs<Node>("/root/Events")         // a GDScript autoload
    val bar = self.requireAs<ProgressBar>("Hud/Health")
    val healthChanged = Signal1(events, "health_changed", SignalArgType.LONG)
    healthChanged.connect { hp -> bar.value = hp.toDouble() }
}
```

A signal a GDScript declares, or one Godot adds at runtime, gets a typed handle
the same way: name its argument types and use it like any other. For a signal
whose arguments you do not know, `events.signal("health_changed")` is the
untyped handle. Its `connect(target, argumentCount) { args -> }` receives the
arguments as a `List<Any?>`, and `await(target, argumentCount)` returns them;
`connectObject` and `awaitObject` are the shortcuts for one object argument.
These connections have the same lifetime as the typed ones.

## Scene Connections

A scene can store a signal connection, and the `.tscn` file saves the target
function's name. A scene made with GDScript usually connects to a name such as
`_on_body_entered`, so give the Kotlin function that exact name:

```kotlin
@GodotName("_on_body_entered")
fun onBodyEntered(body: Node3D) {
    body.hide()
}
```

Without `@GodotName`, the function is `on_body_entered`, and the saved
connection does not find it. To check every saved connection of a project
against your scripts, run this from a Kanama checkout:

```sh
python3 /path/to/kanama/scripts/scene_connection_lint.py /path/to/your-project
```

It reads each `.tscn` connection, finds the Kotlin script on the target node,
and reports a connection whose function the script does not have.

## Signals on Instanced Scenes

The GDScript for Squash the Creeps connects each new mob to the score label:

```gdscript
mob.squashed.connect($UserInterface/ScoreLabel._on_mob_squashed)
```

In Kotlin, with both sides Kotlin scripts, connect the typed handle to a
lambda that calls the label's function:

```kotlin
import net.multigesture.kanama.generated.squashed

@ScriptClass(attachTo = "CharacterBody3D")
class Mob(godotObject: GodotHandle) : KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D) {
    @Signal
    fun squashed() = Unit
}

@ScriptClass(attachTo = "Label")
class ScoreLabel(godotObject: GodotHandle) : KanamaScript<Label>(godotObject, ::Label) {
    private var score = 0L

    fun onMobSquashed() {
        score += 1
        self.text = "Score: $score"
    }
}

@ScriptClass(attachTo = "Node")
class Main(godotObject: GodotHandle) : KanamaScript<Node>(godotObject, ::Node) {
    @Export
    var mobScene: PackedScene? = null

    private val scoreLabel by script<ScoreLabel>("UserInterface/ScoreLabel")

    fun spawnMob() {
        val mob = mobScene?.instantiateScript<Mob>() ?: return
        mob.squashed.connect { scoreLabel.onMobSquashed() }
        self.addChild(mob.self)
    }
}
```

`instantiateScript<Mob>()` returns the Kotlin script on the new scene's root,
so you call its members directly. The lambda belongs to `Main`, and the
connection ends when `Main` or the mob is freed. When the receiver is a
GDScript, connect by name instead:
`mob.squashed.connect(label, "_on_mob_squashed")`.

## Hot Reload

On desktop, a hot reload loads the new build of your scripts while the old
build's objects keep running until they are freed. The scene reload frees the
scene's objects, and Kotlin autoloads are reset in place. A lambda connection
belongs to the build that made it. Once the old build's last script object is
gone, its lambda connections are disconnected on the next frame, even when the
target is still alive, and Kanama prints one warning:

```text
hot-reload: dropped 2 lambda connection(s) of build 1 whose script objects are gone (SceneTree.process_frame x1, Hud.score_changed x1); the new build's _ready connects again
```

So make lambda connections in `@OnReady` or later, and do not expect one to
outlive a hot reload: the new objects' `@OnReady` connects again, running the
new code. While some of the old build's objects are still alive, such as those
of a scene you did not reload, their connections stay and run the old code.

| Connection | GDScript after a reload | Kanama after a hot reload |
|---|---|---|
| by name: `connect(target, "on_hit")`, a scene connection | survives and calls the new function | survives and calls the new function once the target has the new build's object; a node of a scene you did not reload keeps the old one |
| a lambda | moves to the new code when the lambda is still in the source; otherwise it is skipped silently | dropped with its build, with one warning; `@OnReady` connects the new one |

A Kotlin function reference (`::onHit`) is dropped with its build like a
lambda. C# re-binds such a reference by object and method name after a
reload; Kanama does not, so connect by name when a connection must survive a
hot reload.

## On Other Platforms

Signals, connections and `await` work the same on desktop, Android, iOS and
Web, with a few gaps: iOS does not deliver signal arguments of some container
types, and Web has no typed handle for two engine signals. Hot reload is
desktop only. [Limitations](../reference/limitations.md) lists each case with
its workaround.
