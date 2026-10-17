package net.multigesture.kanama.api

/**
 * The multiplayer stand-ins (`WEB_INERT_FACADES`). Web builds are single-player: ENet needs UDP
 * sockets, so the multiplayer surface reports a local authoritative session (peer 1, no remote
 * peers, the offline peer connected) and the lobby's connect paths fail the way the demo already
 * handles. `Node.getMultiplayer()` (generated) answers [webMultiplayerApi].
 */


/**
 * `AutoCloseable` like desktop's `RefCounted`, so the shared demo sources' `peer.use { }` resolves
 * to the stdlib `use` on both backends without an import.
 */
open class MultiplayerPeer internal constructor() : AutoCloseable {
  open fun closeConnection() = Unit

  override fun close() = Unit

  /** The single-player peer is connected; an ENet peer never connects on Web. */
  open fun getConnectionStatus(): ConnectionStatus = ConnectionStatus.CONNECTED

  /**
   * Desktop's `isClass` over the Godot class each facade stands in for (the shared sources test
   * the peer's class by name: the wrapper is typed as the base peer on desktop).
   */
  fun isClass(className: String): Boolean = className in godotClasses

  internal open val godotClasses: Set<String>
    get() = PEER_CLASSES

  // ===== BEGIN GENERATED ENUMS: MultiplayerPeer (scripts/generate_web_wrappers.py — do not edit) =====
  value class ConnectionStatus(override val value: Long) : GodotEnumValue {
    companion object {
      val DISCONNECTED: ConnectionStatus get() = ConnectionStatus(0L)
      val CONNECTING: ConnectionStatus get() = ConnectionStatus(1L)
      val CONNECTED: ConnectionStatus get() = ConnectionStatus(2L)
    }
  }

  value class TransferMode(override val value: Long) : GodotEnumValue {
    companion object {
      val UNRELIABLE: TransferMode get() = TransferMode(0L)
      val UNRELIABLE_ORDERED: TransferMode get() = TransferMode(1L)
      val RELIABLE: TransferMode get() = TransferMode(2L)
    }
  }
  // ===== END GENERATED ENUMS: MultiplayerPeer =====
}

private val PEER_CLASSES = setOf("MultiplayerPeer", "PacketPeer", "RefCounted", "Object")

class OfflineMultiplayerPeer internal constructor() : MultiplayerPeer() {
  internal override val godotClasses: Set<String>
    get() = OFFLINE_CLASSES

  companion object {
    fun create(): OfflineMultiplayerPeer = OfflineMultiplayerPeer()
  }
}

private val OFFLINE_CLASSES = PEER_CLASSES + "OfflineMultiplayerPeer"

private val ENET_CLASSES = PEER_CLASSES + "ENetMultiplayerPeer"

class ENetMultiplayerPeer internal constructor() : MultiplayerPeer() {
  internal override val godotClasses: Set<String>
    get() = ENET_CLASSES

  /** create_server / create_client fail on Web, so the peer never connects. */
  override fun getConnectionStatus(): MultiplayerPeer.ConnectionStatus =
    MultiplayerPeer.ConnectionStatus.DISCONNECTED

  /** Always fails on Web (no UDP sockets); the lobby surfaces the error to the player. */
  fun createServer(@Suppress("UNUSED_PARAMETER") port: Int): GodotError = GodotError.ERR_UNAVAILABLE

  fun createClient(
    @Suppress("UNUSED_PARAMETER") address: String,
    @Suppress("UNUSED_PARAMETER") port: Int,
  ): GodotError = GodotError.ERR_UNAVAILABLE

  companion object {
    fun create(): ENetMultiplayerPeer = ENetMultiplayerPeer()
  }
}

class MultiplayerAPI internal constructor() {
  fun isServer(): Boolean = true

  /** Owned-handle idiom of the shared demo helpers (`withMultiplayer` closes what it got); a no-op here. */
  fun close() = Unit

  fun getUniqueId(): Int = 1

  fun getPeers(): List<Int> = emptyList()

  fun getRemoteSenderId(): Int = 0

  fun getMultiplayerPeer(): MultiplayerPeer? = peer

  /** True unless a script set the peer to null: the session starts with the offline peer, as desktop's. */
  fun hasMultiplayerPeer(): Boolean = peer != null

  var multiplayerPeer: MultiplayerPeer?
    get() = peer
    set(value) {
      peer = value
    }

  /** No remote peers ever connect, so these signals never fire; connects are no-ops. */
  fun signal(@Suppress("UNUSED_PARAMETER") name: String): InertSignal = InertSignal

  // The typed signals (task 134 D4), as inert as the untyped handle above.
  val peerConnected: InertSignal1<Long>
    get() = InertSignal1()

  val peerDisconnected: InertSignal1<Long>
    get() = InertSignal1()

  val connectedToServer: InertSignal0
    get() = InertSignal0

  val connectionFailed: InertSignal0
    get() = InertSignal0

  val serverDisconnected: InertSignal0
    get() = InertSignal0

  object Signals {
    const val peerConnected = "peer_connected"
    const val peerDisconnected = "peer_disconnected"
    const val connectedToServer = "connected_to_server"
    const val connectionFailed = "connection_failed"
    const val serverDisconnected = "server_disconnected"
  }

  private companion object {
    // Desktop's SceneMultiplayer starts with an OfflineMultiplayerPeer, so this one does too.
    var peer: MultiplayerPeer? = OfflineMultiplayerPeer()
  }

  // ===== BEGIN GENERATED ENUMS: MultiplayerAPI (scripts/generate_web_wrappers.py — do not edit) =====
  value class RPCMode(override val value: Long) : GodotEnumValue {
    companion object {
      val DISABLED: RPCMode get() = RPCMode(0L)
      val ANY_PEER: RPCMode get() = RPCMode(1L)
      val AUTHORITY: RPCMode get() = RPCMode(2L)
    }
  }
  // ===== END GENERATED ENUMS: MultiplayerAPI =====
}

/** A typed signal without arguments that can never fire on Web; connecting is inert (task 134). */
object InertSignal0 {
  fun connect(@Suppress("UNUSED_PARAMETER") callback: () -> Unit) = Unit

  fun connect(@Suppress("UNUSED_PARAMETER") target: GodotObject, @Suppress("UNUSED_PARAMETER") callback: () -> Unit) =
    Unit
}

/** A typed one-argument signal that can never fire on Web; connecting is inert (task 134). */
class InertSignal1<A> {
  fun connect(@Suppress("UNUSED_PARAMETER") callback: (A) -> Unit) = Unit

  fun connect(@Suppress("UNUSED_PARAMETER") target: GodotObject, @Suppress("UNUSED_PARAMETER") callback: (A) -> Unit) =
    Unit
}

/** A signal that can never fire on Web; connecting to it is deliberately inert. */
object InertSignal {
  fun connect(
    @Suppress("UNUSED_PARAMETER") target: GodotObject,
    @Suppress("UNUSED_PARAMETER") argumentCount: Int = 0,
    @Suppress("UNUSED_PARAMETER") handler: (List<Any?>) -> Unit,
  ) = Unit
}

object SceneMultiplayer {
  fun fromApi(api: MultiplayerAPI?): SceneMultiplayerHandle? = api?.let { SceneMultiplayerHandle }
}

object SceneMultiplayerHandle {
  var serverRelay: Boolean = false
}

/** The page's one session, behind the generated `Node.getMultiplayer()`. */
internal val webMultiplayerApi = MultiplayerAPI()

/** Replication node; with a single local peer there is nothing to synchronize. */
class MultiplayerSynchronizer(godotObject: GodotHandle) : Node(godotObject) {
  var publicVisibility: Boolean = true

  fun setMultiplayerAuthority(@Suppress("UNUSED_PARAMETER") id: Int) = Unit

  fun getMultiplayerAuthority(): Int = 1

  // ===== BEGIN GENERATED ENUMS: MultiplayerSynchronizer (scripts/generate_web_wrappers.py — do not edit) =====
  value class VisibilityUpdateMode(override val value: Long) : GodotEnumValue {
    companion object {
      val IDLE: VisibilityUpdateMode get() = VisibilityUpdateMode(0L)
      val PHYSICS: VisibilityUpdateMode get() = VisibilityUpdateMode(1L)
      val NONE: VisibilityUpdateMode get() = VisibilityUpdateMode(2L)
    }
  }
  // ===== END GENERATED ENUMS: MultiplayerSynchronizer =====
}
