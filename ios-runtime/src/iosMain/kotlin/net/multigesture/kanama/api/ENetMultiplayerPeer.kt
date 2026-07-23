package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ENetMultiplayerPeer
 */
class ENetMultiplayerPeer(handle: MemorySegment) : MultiplayerPeer(handle) {
    val host: ENetConnection?
        @JvmName("hostProperty")
        get() = getHost()

    fun createServer(port: Int, maxClients: Int = 32, maxChannels: Int = 0, inBandwidth: Int = 0, outBandwidth: Int = 0): Long {
        return ObjectCalls.ptrcallWithFiveIntArgsRetLong(createServerBind, handle, port, maxClients, maxChannels, inBandwidth, outBandwidth)
    }

    fun createClient(address: String, port: Int, channelCount: Int = 0, inBandwidth: Int = 0, outBandwidth: Int = 0, localPort: Int = 0): Long {
        return ObjectCalls.ptrcallWithStringAndFiveIntArgsRetLong(createClientBind, handle, address, port, channelCount, inBandwidth, outBandwidth, localPort)
    }

    fun createMesh(uniqueId: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(createMeshBind, handle, uniqueId)
    }

    fun addMeshPeer(peerId: Int, host: ENetConnection?): Long {
        return ObjectCalls.ptrcallWithIntAndObjectArgRetLong(addMeshPeerBind, handle, peerId, host?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun setBindIp(ip: String) {
        ObjectCalls.ptrcallWithStringArg(setBindIpBind, handle, ip)
    }

    fun getHost(): ENetConnection? {
        return ENetConnection.wrap(ObjectCalls.ptrcallNoArgsRetObject(getHostBind, handle))
    }

    fun getPeer(id: Int): ENetPacketPeer? {
        return ENetPacketPeer.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getPeerBind, handle, id))
    }

    companion object {
        fun fromHandle(handle: MemorySegment): ENetMultiplayerPeer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ENetMultiplayerPeer? =
            if (handle.address() == 0L) null else ENetMultiplayerPeer(handle)

        // Instantiate an ENetMultiplayerPeer.
        fun create(): ENetMultiplayerPeer =
            ENetMultiplayerPeer(MemorySegment.ofAddress(IosGodot.constructObject("ENetMultiplayerPeer")))

        private const val CREATE_SERVER_HASH = 2917761309L
        private val createServerBind by lazy {
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "create_server", CREATE_SERVER_HASH)
        }

        private const val CREATE_CLIENT_HASH = 2327163476L
        private val createClientBind by lazy {
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "create_client", CREATE_CLIENT_HASH)
        }

        private const val CREATE_MESH_HASH = 844576869L
        private val createMeshBind by lazy {
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "create_mesh", CREATE_MESH_HASH)
        }

        private const val ADD_MESH_PEER_HASH = 1293458335L
        private val addMeshPeerBind by lazy {
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "add_mesh_peer", ADD_MESH_PEER_HASH)
        }

        private const val SET_BIND_IP_HASH = 83702148L
        private val setBindIpBind by lazy {
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "set_bind_ip", SET_BIND_IP_HASH)
        }

        private const val GET_HOST_HASH = 4103238886L
        private val getHostBind by lazy {
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "get_host", GET_HOST_HASH)
        }

        private const val GET_PEER_HASH = 3793311544L
        private val getPeerBind by lazy {
            ObjectCalls.getMethodBind("ENetMultiplayerPeer", "get_peer", GET_PEER_HASH)
        }
    }
}
