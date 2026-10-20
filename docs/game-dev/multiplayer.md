# Multiplayer

Kanama uses Godot's own networking. You create a peer, give it to the scene
tree's `MultiplayerAPI`, and mark the functions other peers may call with
`@Rpc`. Scenes, synchronizers and authority work as they do in GDScript. The
snippets sit inside a script like the one in
[Writing Scripts](scripts.md#a-complete-script), which lists the imports.

## Peer Setup

```kotlin
fun host(port: Long) {
    ENetMultiplayerPeer.create().use { peer ->
        if (peer.createServer(port.toInt()) != GodotError.OK) return
        self.getMultiplayer()?.use { it.multiplayerPeer = peer }
    }
}

fun join(address: String, port: Long) {
    ENetMultiplayerPeer.create().use { peer ->
        if (peer.createClient(address, port.toInt()) != GodotError.OK) return
        self.getMultiplayer()?.use { it.multiplayerPeer = peer }
    }
}
```

This is GDScript's `multiplayer.multiplayer_peer = peer`. The `MultiplayerAPI`
keeps its own reference to the peer, so `use { }` releases only yours (see
[Resource Ownership](godot-api.md#resource-ownership)). To go back to local
play, assign `OfflineMultiplayerPeer.create()` the same way. Keep one place in
your code in charge of the peer: old connection state is easy to leave behind
when you switch between menu and game scenes.

## RPC Methods

```kotlin
@ScriptClass(attachTo = "MultiplayerSynchronizer")
class PlayerInput(godotObject: GodotHandle) :
    KanamaScript<MultiplayerSynchronizer>(godotObject, ::MultiplayerSynchronizer) {

    private var queuedJump = false

    @Rpc(callLocal = true)
    fun jump() {
        queuedJump = true
    }

    @OnProcess
    fun process(delta: Double) {
        if (Input.isActionJustPressed("jump")) PlayerInputRpcs.rpcJump(this)
    }
}
```

`@Rpc` on a public function is GDScript's `@rpc`, with the same `mode`,
`callLocal`, `transferMode` and `channel` settings. The build generates a
`PlayerInputRpcs` object with one sender per RPC:

| GDScript | Kotlin |
|---|---|
| `jump.rpc()` | `PlayerInputRpcs.rpcJump(this)` |
| `jump.rpc_id(peer_id)` | `PlayerInputRpcs.rpcIdJump(this, peerId)` |
| `jump.rpc()` on a `call_local` RPC | `PlayerInputRpcs.callLocalJump(this)` |

`callLocalJump` exists only for an `@Rpc(callLocal = true)` function. It sends
the RPC like `rpcJump` and, when the send fails because no network is
connected, runs the function here instead, so local play keeps working.
`self.rpc("jump")` still works for a function Godot knows only by name, such
as one in a GDScript.

A plain Kotlin call, `jump()`, does not go over the network: it is GDScript's
`jump()`, not `jump.rpc()`. For a function without `callLocal`, the build warns
about such a call and names the sender to use:

```text
w: [ksp] [kanama:ksp] /path/to/your-project/kotlin-src/Menu.kt:376: prepareGame() is an @Rpc function of Menu called
directly, so it runs on this peer only. Send it with MenuRpcs.rpcPrepareGame(instance) or
MenuRpcs.rpcIdPrepareGame(instance, peerId); to run it here as well, declare
@Rpc(callLocal = true) and call MenuRpcs.callLocalPrepareGame(instance).
```

A `callLocal` function gets no warning, because calling one directly is
sometimes what the GDScript original does too. So when you port, turn each
`x.rpc()` line into a sender call yourself. The warning sees direct calls in
the file that declares the RPC, and calls through another object (`robot.hit()`)
elsewhere when no other function or Godot method has that name.

## Authority and Synchronizers

A pattern that works for player input:

- The server owns the world simulation and spawns the players.
- Each player scene has a `MultiplayerSynchronizer` for the input its client
  owns.
- The synchronizer's multiplayer authority is set before the game starts.
- The server reads the replicated input and writes the authoritative movement.

Give each spawned player a name that is the same on every peer, usually its
peer id as a string, so Godot's replication can match scene paths.

When you port, set authority at the same point as the original. Moving it from
`_enter_tree` to `_ready`, or from the scene root to a child synchronizer,
compiles but silently drops input.

## Replicated Properties

```kotlin
@Export
var motion: Vector2 = Vector2.ZERO
```

When a scene's `SceneReplicationConfig` synchronizes `.:motion`, the script on
that node must export a property named `motion`. Use `@Export(name = "...")`
when the Kotlin name differs from the name in the scene. Godot replicates its
own properties, such as `position` and `rotation`, without your help.

## Checklist for a Ported Game

- Authority is set at the same lifecycle point as in the original, especially
  for nested `MultiplayerSynchronizer` nodes.
- Spawned player names are the same on every peer.
- Every custom `.:property` in a `SceneReplicationConfig` is exported.
- Every GDScript `x.rpc()` and `x.rpc_id()` became a sender call, not a direct
  Kotlin call, and calls between Kotlin scripts are direct Kotlin calls, not
  strings.
- Look up nodes once, in `@OnReady` or with `node<T>()`, not in `@OnProcess`,
  `@OnPhysicsProcess`, an RPC or a signal lambda.
- Each remaining `call`, `rpc` or animation-tree property string is a real
  dynamic boundary, and a host and client test covers it.

## Checks

Two scripts in a Kanama checkout find the usual multiplayer porting mistakes.
Both take several roots, so you can check more than one project in one run.

```sh
python3 /path/to/kanama/scripts/audit_replicated_script_properties.py /path/to/your-project
python3 /path/to/kanama/scripts/audit_runtime_node_lookups.py /path/to/your-project/kotlin-src
```

The first reports a `.:property` in a `SceneReplicationConfig` that the script
on that node does not export. The second reports raw RPC strings and required
node lookups inside per-frame callbacks, RPCs, signal lambdas and coroutines.
It is strict on purpose: treat each finding as a question. A raw RPC string
usually becomes a sender call, and a call by name inside a callback deserves a
reason.

## On Other Platforms

`@Rpc` settings reach Godot on desktop, Android and iOS. Web has no
multiplayer; see [Limitations](../reference/limitations.md#web).
