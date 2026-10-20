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

`@Rpc` on a public function is GDScript's `@rpc`. Its settings have the same
names and defaults as GDScript's:

```kotlin
@Rpc(mode = RpcMode.ANY_PEER, transferMode = RpcTransferMode.UNRELIABLE_ORDERED, channel = 1)
fun aim(direction: Vector2) {
    // GDScript: @rpc("any_peer", "unreliable_ordered", "call_remote", 1)
}
```

The build generates a `PlayerInputRpcs` object with one sender per RPC. A
sender takes the script object, `this`, not the node `self`: it is typed to
your script class, so the compiler checks that the target has the RPC.

| GDScript | Kotlin | What it does |
|---|---|---|
| `jump.rpc()` | `PlayerInputRpcs.rpcJump(this)` | Calls `jump` on the other peers, and here too when the RPC is `callLocal`. |
| `jump.rpc_id(peer_id)` | `PlayerInputRpcs.rpcIdJump(this, peerId)` | Calls `jump` on one peer. |
| `jump.rpc()` in a game that also runs without a network | `PlayerInputRpcs.callLocalJump(this)` | Does what `rpcJump` does; when the send fails, for example with no peer, calls `jump` here directly. Generated only for `@Rpc(callLocal = true)`. |

`self.rpc("jump")` still works for a function Godot knows only by name, such
as one in a GDScript.

A plain Kotlin call, `jump()`, does not go over the network: it is GDScript's
`jump()`, not `jump.rpc()`. For a function without `callLocal`, the build warns
about such a call and names the sender to use:

```text
w: [ksp] [kanama:ksp] /path/to/your-project/kotlin-src/Menu.kt:376: prepareGame() is an @Rpc function of Menu called directly, so it runs on this peer only. Send it with MenuRpcs.rpcPrepareGame(instance) or MenuRpcs.rpcIdPrepareGame(instance, peerId); to run it here as well, declare @Rpc(callLocal = true) and call MenuRpcs.callLocalPrepareGame(instance).
```

The warning skips `callLocal` functions, because calling one directly is
sometimes what the GDScript original does, and it misses some calls through
another object. So when you port, turn each `x.rpc()` line into a sender call
yourself.

## Authority and Synchronizers

```kotlin
@ScriptClass(attachTo = "Node3D")
class World(godotObject: GodotHandle) : KanamaScript<Node3D>(godotObject, ::Node3D) {
    @Export var playerScene: PackedScene? = null

    private val players by node<Node>("Players")      // the MultiplayerSpawner's spawn path

    @OnReady
    fun ready() {
        self.getMultiplayer()?.use { multiplayer ->
            if (!multiplayer.isServer()) return
            multiplayer.peerConnected.connect { id -> addPlayer(id) }
            addPlayer(1)                                // the host's own player
        }
    }

    private fun addPlayer(id: Long) {
        val player = playerScene?.instantiateAs<CharacterBody3D>() ?: return
        player.name = id.toString()                     // the same name on every peer
        players.addChild(player)
    }
}

@ScriptClass(attachTo = "CharacterBody3D")
class NetPlayer(godotObject: GodotHandle) :
    KanamaScript<CharacterBody3D>(godotObject, ::CharacterBody3D) {

    @OnEnterTree
    fun enterTree() {
        // runs on every peer, so every peer agrees who owns this player's input
        self.requireAs<MultiplayerSynchronizer>("Input").setMultiplayerAuthority(self.name.toInt())
    }
}
```

The server spawns the players, and a `MultiplayerSpawner` whose spawn path is
`Players` creates them on the clients. Each player is named after its peer id,
so Godot's replication finds the same node path on every peer. Each player
scene has a `MultiplayerSynchronizer` named `Input` for the input its client
owns; its authority is set in `@OnEnterTree`, before the game reads input. The
server reads the replicated input and writes the authoritative movement.

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
  for nested `MultiplayerSynchronizer` nodes, because input from a peer without
  authority is dropped.
- Spawned player names are the same on every peer, because replication matches
  nodes by path.
- Every custom `.:property` in a `SceneReplicationConfig` is exported, because
  Godot can only replicate a property the script exposes.
- Every GDScript `x.rpc()` and `x.rpc_id()` became a sender call, because a
  direct Kotlin call runs on this peer only. Calls between Kotlin scripts are
  direct Kotlin calls, because the compiler checks those and not strings.
- Nodes are looked up once, in `@OnReady` or with `node<T>()`, not in
  `@OnProcess`, `@OnPhysicsProcess`, an RPC or a signal lambda, because a
  lookup there repeats every frame and a missing node fails in the middle of a
  game instead of at the start.
- Each remaining `call`, `rpc` or animation-tree property string is a real
  dynamic boundary that a host and client test covers, because the compiler
  cannot check a string.

## Checks

Two scripts in a Kanama checkout find the usual multiplayer porting mistakes:

```sh
python3 /path/to/kanama/scripts/audit_replicated_script_properties.py /path/to/your-project
python3 /path/to/kanama/scripts/audit_runtime_node_lookups.py /path/to/your-project/kotlin-src
```

Both take several roots, to check more than one project in one run:
`audit_replicated_script_properties.py /path/to/game-one /path/to/game-two`.

The first reports a `.:property` in a `SceneReplicationConfig` that the script
on that node does not export. The second reports raw RPC strings and required
node lookups inside per-frame callbacks, RPCs, signal lambdas and coroutines.
It is strict on purpose: treat each finding as a question. A raw RPC string
usually becomes a sender call, and a call by name inside a callback deserves a
reason.

## On Other Platforms

`@Rpc` settings reach Godot on desktop, Android and iOS. Web has no
multiplayer; see [Limitations](../reference/limitations.md#web).
