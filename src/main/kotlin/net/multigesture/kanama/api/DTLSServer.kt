package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Helper class to implement a DTLS server.
 *
 * Generated from Godot docs: DTLSServer
 */
class DTLSServer(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Setup the DTLS server to use the given `server_options`. See `TLSOptions.server`.
     *
     * Generated from Godot docs: DTLSServer.setup
     */
    fun setup(serverOptions: TLSOptions?): Long {
        return ObjectCalls.ptrcallWithObjectArgRetLong(setupBind, handle, serverOptions?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Try to initiate the DTLS handshake with the given `udp_peer` which must be already connected
     * (see `PacketPeerUDP.connect_to_host`). Note: You must check that the state of the return
     * PacketPeerUDP is `PacketPeerDTLS.STATUS_HANDSHAKING`, as it is normal that 50% of the new
     * connections will be invalid due to cookie exchange.
     *
     * Generated from Godot docs: DTLSServer.take_connection
     */
    fun takeConnection(udpPeer: PacketPeerUDP?): PacketPeerDTLS? {
        return PacketPeerDTLS.wrap(ObjectCalls.ptrcallWithObjectArgRetObject(takeConnectionBind, handle, udpPeer?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): DTLSServer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): DTLSServer? =
            if (handle.address() == 0L) null else DTLSServer(handle)

        private const val SETUP_HASH = 1262296096L
        private val setupBind by lazy {
            ObjectCalls.getMethodBind("DTLSServer", "setup", SETUP_HASH)
        }

        private const val TAKE_CONNECTION_HASH = 3946580474L
        private val takeConnectionBind by lazy {
            ObjectCalls.getMethodBind("DTLSServer", "take_connection", TAKE_CONNECTION_HASH)
        }
    }
}
