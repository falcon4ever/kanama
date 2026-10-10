package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: ENetMultiplayerPeer
 */
class ENetMultiplayerPeer(handle: GodotHandle) : MultiplayerPeer(handle) {
    val host: ENetConnection?
        @JvmName("hostProperty")
        get() = getHost()

    fun createServer(port: Int, maxClients: Int = 32, maxChannels: Int = 0, inBandwidth: Int = 0, outBandwidth: Int = 0): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithFiveIntArgsRetLong(Binds.createServerBind, segment, port, maxClients, maxChannels, inBandwidth, outBandwidth))
    }

    fun createClient(address: String, port: Int, channelCount: Int = 0, inBandwidth: Int = 0, outBandwidth: Int = 0, localPort: Int = 0): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringAndFiveIntArgsRetLong(Binds.createClientBind, segment, address, port, channelCount, inBandwidth, outBandwidth, localPort))
    }

    fun createMesh(uniqueId: Int): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithIntArgRetLong(Binds.createMeshBind, segment, uniqueId))
    }

    fun addMeshPeer(peerId: Int, host: ENetConnection?): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithIntAndObjectArgRetLong(Binds.addMeshPeerBind, segment, peerId, host?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun setBindIp(ip: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setBindIpBind, segment, ip)
    }

    fun getHost(): ENetConnection? {
        checkOpen()
        return ENetConnection.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getHostBind, segment))
    }

    fun getPeer(id: Int): ENetPacketPeer? {
        checkOpen()
        return ENetPacketPeer.wrapOwned(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getPeerBind, segment, id))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ENetMultiplayerPeer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ENetMultiplayerPeer? =
            if (handle.address() == 0L) null else RefCounted.owned(ENetMultiplayerPeer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ENetMultiplayerPeer? =
            if (handle.address() == 0L) null else ENetMultiplayerPeer(GodotHandle(handle))

        // Instantiate an ENetMultiplayerPeer.
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
