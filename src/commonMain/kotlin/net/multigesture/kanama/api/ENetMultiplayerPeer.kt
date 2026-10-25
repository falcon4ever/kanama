package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A MultiplayerPeer implementation using the ENet (http://enet.bespin.org/index.html) library.
 *
 * Generated from Godot docs: ENetMultiplayerPeer
 */
class ENetMultiplayerPeer(handle: GodotHandle) : MultiplayerPeer(handle) {
    val host: ENetConnection?
        @JvmName("hostProperty")
        get() = getHost()

    /**
     * Create server that listens to connections via `port`. The port needs to be an available, unused
     * port between 0 and 65535. Note that ports below 1024 are privileged and may require elevated
     * permissions depending on the platform. To change the interface the server listens on, use
     * `set_bind_ip`. The default IP is the wildcard `"*"`, which listens on all available interfaces.
     * `max_clients` is the maximum number of clients that are allowed at once, any number up to 4095
     * may be used, although the achievable number of simultaneous clients may be far lower and depends
     * on the application. For additional details on the bandwidth parameters, see `create_client`.
     * Returns `GodotError.OK` if a server was created, `GodotError.ERR_ALREADY_IN_USE` if this
     * ENetMultiplayerPeer instance already has an open connection (in which case you need to call
     * `MultiplayerPeer.close` first) or `GodotError.ERR_CANT_CREATE` if the server could not be
     * created.
     *
     * Generated from Godot docs: ENetMultiplayerPeer.create_server
     */
    fun createServer(port: Int, maxClients: Int = 32, maxChannels: Int = 0, inBandwidth: Int = 0, outBandwidth: Int = 0): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithFiveIntArgsRetLong(Binds.createServerBind, segment, port, maxClients, maxChannels, inBandwidth, outBandwidth))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Create client that connects to a server at `address` using specified `port`. The given address
     * needs to be either a fully qualified domain name (e.g. `"www.example.com"`) or an IP address in
     * IPv4 or IPv6 format (e.g. `"192.168.1.1"`). The `port` is the port the server is listening on.
     * The `channel_count` parameter can be used to specify the number of ENet channels allocated for
     * the connection. The `in_bandwidth` and `out_bandwidth` parameters can be used to limit the
     * incoming and outgoing bandwidth to the given number of bytes per second. The default of 0 means
     * unlimited bandwidth. Note that ENet will strategically drop packets on specific sides of a
     * connection between peers to ensure the peer's bandwidth is not overwhelmed. The bandwidth
     * parameters also determine the window size of a connection which limits the amount of reliable
     * packets that may be in transit at any given time. Returns `GodotError.OK` if a client was
     * created, `GodotError.ERR_ALREADY_IN_USE` if this ENetMultiplayerPeer instance already has an
     * open connection (in which case you need to call `MultiplayerPeer.close` first) or
     * `GodotError.ERR_CANT_CREATE` if the client could not be created. If `local_port` is specified,
     * the client will also listen to the given port; this is useful for some NAT traversal techniques.
     *
     * Generated from Godot docs: ENetMultiplayerPeer.create_client
     */
    fun createClient(address: String, port: Int, channelCount: Int = 0, inBandwidth: Int = 0, outBandwidth: Int = 0, localPort: Int = 0): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithStringAndFiveIntArgsRetLong(Binds.createClientBind, segment, address, port, channelCount, inBandwidth, outBandwidth, localPort))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Initialize this `MultiplayerPeer` in mesh mode. The provided `unique_id` will be used as the
     * local peer network unique ID once assigned as the `MultiplayerAPI.multiplayer_peer`. In the mesh
     * configuration you will need to set up each new peer manually using `ENetConnection` before
     * calling `add_mesh_peer`. While this technique is more advanced, it allows for better control
     * over the connection process (e.g. when dealing with NAT punch-through) and for better
     * distribution of the network load (which would otherwise be more taxing on the server).
     *
     * Generated from Godot docs: ENetMultiplayerPeer.create_mesh
     */
    fun createMesh(uniqueId: Int): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithIntArgRetLong(Binds.createMeshBind, segment, uniqueId))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Add a new remote peer with the given `peer_id` connected to the given `host`. Note: The `host`
     * must have exactly one peer in the `ENetPacketPeer.PeerState.CONNECTED` state.
     *
     * Generated from Godot docs: ENetMultiplayerPeer.add_mesh_peer
     */
    fun addMeshPeer(peerId: Int, host: ENetConnection?): GodotError {
        checkOpen()
        val ret = GodotError(ObjectCalls.ptrcallWithIntAndObjectArgRetLong(Binds.addMeshPeerBind, segment, peerId, host?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(host)
        return ret
    }

    /**
     * The IP used when creating a server. This is set to the wildcard `"*"` by default, which binds to
     * all available interfaces. The given IP needs to be in IPv4 or IPv6 address format, for example:
     * `"192.168.1.1"`.
     *
     * Generated from Godot docs: ENetMultiplayerPeer.set_bind_ip
     */
    fun setBindIp(ip: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setBindIpBind, segment, ip)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The underlying `ENetConnection` created after `create_client` and `create_server`.
     *
     * Generated from Godot docs: ENetMultiplayerPeer.get_host
     */
    fun getHost(): ENetConnection? {
        checkOpen()
        val ret = ENetConnection.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getHostBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the `ENetPacketPeer` associated to the given `id`.
     *
     * Generated from Godot docs: ENetMultiplayerPeer.get_peer
     */
    fun getPeer(id: Int): ENetPacketPeer? {
        checkOpen()
        val ret = ENetPacketPeer.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getPeerBind, segment, id))
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ENetMultiplayerPeer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ENetMultiplayerPeer? =
            if (handle.address() == 0L) null else RefCounted.owned(ENetMultiplayerPeer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ENetMultiplayerPeer? =
            if (handle.address() == 0L) null else ENetMultiplayerPeer(GodotHandle(handle))

        /** A new ENetMultiplayerPeer, GDScript's `ENetMultiplayerPeer.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): ENetMultiplayerPeer =
            RefCounted.owned(ENetMultiplayerPeer(GodotHandle(ObjectCalls.constructObject("ENetMultiplayerPeer"))))
    }

    private object Binds {
        private const val CREATE_SERVER_HASH = 2917761309L
        @JvmField
        val createServerBind =
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "create_server", CREATE_SERVER_HASH)

        private const val CREATE_CLIENT_HASH = 2327163476L
        @JvmField
        val createClientBind =
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "create_client", CREATE_CLIENT_HASH)

        private const val CREATE_MESH_HASH = 844576869L
        @JvmField
        val createMeshBind =
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "create_mesh", CREATE_MESH_HASH)

        private const val ADD_MESH_PEER_HASH = 1293458335L
        @JvmField
        val addMeshPeerBind =
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "add_mesh_peer", ADD_MESH_PEER_HASH)

        private const val SET_BIND_IP_HASH = 83702148L
        @JvmField
        val setBindIpBind =
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "set_bind_ip", SET_BIND_IP_HASH)

        private const val GET_HOST_HASH = 4103238886L
        @JvmField
        val getHostBind =
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "get_host", GET_HOST_HASH)

        private const val GET_PEER_HASH = 3793311544L
        @JvmField
        val getPeerBind =
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "get_peer", GET_PEER_HASH)
    }
}
