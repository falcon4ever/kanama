# Multiplayer

Kanama multiplayer code uses Godot's normal networking stack. Create or join a
peer with wrappers such as `ENetMultiplayerPeer`, assign it to the scene tree,
and expose synchronized methods and properties from Kotlin scripts.

## Peer Setup

The common ENet shape mirrors GDScript:

```kotlin
val peer = ENetMultiplayerPeer.create()
peer.createServer(port)
sceneTree.multiplayerPeer = peer
```

For a client:

```kotlin
val peer = ENetMultiplayerPeer.create()
peer.createClient(address, port)
sceneTree.multiplayerPeer = peer
```

Use `OfflineMultiplayerPeer.create()` or clear the tree's peer when returning
to local-only play. Keep the peer owner clear in your code; it is easy to leave
old connection state behind when switching between menu and gameplay scenes.

## RPC Methods

Declare network-callable methods with `@Rpc` and register them as Godot
methods:

```kotlin
@RegisterFunction
@Rpc(callLocal = true)
fun jump() {
    queuedJump = true
}
```

KSP generates typed sender helpers next to the script registrar:

```kotlin
PlayerInputSynchronizerRpcs.callLocalJump(input)
PlayerInputSynchronizerRpcs.rpcJump(input)
PlayerInputSynchronizerRpcs.rpcIdJump(input, peerId)
```

The raw `self.rpc("jump")` style still exists for dynamic boundaries, but use
generated `*Rpcs` helpers for Kanama-owned methods. They keep the Godot method
name and parameter list tied to the annotated Kotlin declaration, similar to
generated signal helpers.

Kanama delivers the `@Rpc` metadata to Godot when the script is registered, so
Godot's normal `Node.rpc`, `Node.rpc_id`, and authority checks use the
annotation's `mode`, `callLocal`, `transferMode`, and `channel` values on
desktop, Android, and iOS.

`callLocalX(...)` is generated only for `@Rpc(callLocal = true)` methods. That
keeps local fallback explicit and prevents accidentally running non-local RPCs
while offline.

## Authority And Synchronizers

For player input, a useful pattern is:

- The server owns world simulation and spawning.
- Each player scene contains a `MultiplayerSynchronizer` for client-owned input.
- The synchronizer's multiplayer authority is assigned before gameplay starts.
- The server reads replicated input and writes authoritative movement state.

Set stable node names for spawned players, usually the peer id as a string, so
Godot's multiplayer replication can match scene paths across peers.

When porting from GDScript, preserve the original authority timing. Moving an
authority assignment from `_enter_tree` to `_ready`, or from the scene root to a
child synchronizer, can produce code that compiles but silently drops input.

## Replicated Properties

If a scene's `SceneReplicationConfig` synchronizes `.:motion`, the attached
Kotlin script must expose that exact property name:

```kotlin
@ScriptProperty
var motion: Vector2 = Vector2.ZERO
```

Use `@ScriptProperty(name = "...")` when the Kotlin property name differs from
the serialized scene name. Built-in node properties such as `position`,
`rotation`, and `transform` are handled by Godot; custom script properties must
be exposed by Kanama.

Run the replication audit on ports that use `MultiplayerSynchronizer`:

```sh
python3 scripts/audit_replicated_script_properties.py /path/to/godot_project
```

Pass multiple project roots to audit a demo collection in one run.

## Runtime Guardrails

Avoid raw RPC strings and required node lookups inside hot callbacks such as
`@OnPhysicsProcess`, `@OnProcess`, `@Rpc`, signal lambdas, and coroutine bodies.
Cache required scene nodes during setup, use generated signal/RPC helpers, and
keep generic string calls at real dynamic boundaries.

This audit catches common risky shapes:

```sh
python3 scripts/audit_runtime_node_lookups.py /path/to/godot_project/kotlin-src
```

Pass multiple source roots when reviewing several ports together.

It is intentionally conservative. Treat findings as review prompts: raw RPC
calls should usually become generated `*Rpcs` calls, and generic dynamic calls
inside runtime callbacks deserve a reason.

## Multiplayer Review Checklist

Before treating a multiplayer port as stable, review the same boundaries that
usually drift during GDScript-to-Kotlin ports:

- authority is assigned at the same lifecycle point as the original project,
  especially for nested `MultiplayerSynchronizer` nodes;
- spawned player node names are stable across peers, usually matching peer ids;
- every custom `.:property` in a `SceneReplicationConfig` is exposed with
  `@ScriptProperty` or `@ScriptProperty(name = "...")`;
- known Kotlin script targets use generated `*Rpcs` helpers or direct typed
  calls instead of raw string dispatch; and
- remaining dynamic `call`, `rpc`, or animation-tree property strings are real
  dynamic boundaries and are covered by smoke or manual host/client testing.
