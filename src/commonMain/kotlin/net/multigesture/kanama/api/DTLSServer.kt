package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Helper class to implement a DTLS server.
 *
 * Generated from Godot docs: DTLSServer
 */
class DTLSServer(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Setup the DTLS server to use the given `server_options`. See `TLSOptions.server`.
     *
     * Generated from Godot docs: DTLSServer.setup
     */
    fun setup(serverOptions: TLSOptions?): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithObjectArgRetLong(Binds.setupBind, segment, serverOptions?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Try to initiate the DTLS handshake with the given `udp_peer` which must be already connected
     * (see `PacketPeerUDP.connect_to_host`). Note: You must check that the state of the return
     * PacketPeerUDP is `PacketPeerDTLS.Status.HANDSHAKING`, as it is normal that 50% of the new
     * connections will be invalid due to cookie exchange.
     *
     * Generated from Godot docs: DTLSServer.take_connection
     */
    fun takeConnection(udpPeer: PacketPeerUDP?): PacketPeerDTLS? {
        checkOpen()
        return PacketPeerDTLS.wrapOwned(ObjectCalls.ptrcallWithObjectArgRetObject(Binds.takeConnectionBind, segment, udpPeer?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): DTLSServer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): DTLSServer? =
            if (handle.address() == 0L) null else RefCounted.owned(DTLSServer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): DTLSServer? =
            if (handle.address() == 0L) null else DTLSServer(GodotHandle(handle))
    }

    private object Binds {
        private const val SETUP_HASH = 1262296096L
        @JvmField
        val setupBind =
            ObjectCalls.getMethodBind("DTLSServer", "setup", SETUP_HASH)

        private const val TAKE_CONNECTION_HASH = 3946580474L
        @JvmField
        val takeConnectionBind =
            ObjectCalls.getMethodBind("DTLSServer", "take_connection", TAKE_CONNECTION_HASH)
    }
}
