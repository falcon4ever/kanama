package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: WebRTCDataChannel
 */
open class WebRTCDataChannel(handle: MemorySegment) : PacketPeer(handle) {
    var writeMode: Long
        @JvmName("writeModeProperty")
        get() = getWriteMode()
        @JvmName("setWriteModeProperty")
        set(value) = setWriteMode(value)

    fun poll(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(pollBind, handle)
    }

    fun closeConnection() {
        ObjectCalls.ptrcallNoArgs(closeConnectionBind, handle)
    }

    fun wasStringPacket(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(wasStringPacketBind, handle)
    }

    fun setWriteMode(writeMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setWriteModeBind, handle, writeMode)
    }

    fun getWriteMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getWriteModeBind, handle)
    }

    fun getReadyState(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getReadyStateBind, handle)
    }

    fun getLabel(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLabelBind, handle)
    }

    fun isOrdered(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOrderedBind, handle)
    }

    fun getId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getIdBind, handle)
    }

    fun getMaxPacketLifeTime(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxPacketLifeTimeBind, handle)
    }

    fun getMaxRetransmits(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxRetransmitsBind, handle)
    }

    fun getProtocol(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getProtocolBind, handle)
    }

    fun isNegotiated(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNegotiatedBind, handle)
    }

    fun getBufferedAmount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBufferedAmountBind, handle)
    }

    companion object {
        const val WRITE_MODE_TEXT: Long = 0L
        const val WRITE_MODE_BINARY: Long = 1L
        const val STATE_CONNECTING: Long = 0L
        const val STATE_OPEN: Long = 1L
        const val STATE_CLOSING: Long = 2L
        const val STATE_CLOSED: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): WebRTCDataChannel? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): WebRTCDataChannel? =
            if (handle.address() == 0L) null else WebRTCDataChannel(handle)

        private const val POLL_HASH = 166280745L
        private val pollBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "poll", POLL_HASH)
        }

        private const val CLOSE_HASH = 3218959716L
        private val closeConnectionBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "close", CLOSE_HASH)
        }

        private const val WAS_STRING_PACKET_HASH = 36873697L
        private val wasStringPacketBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "was_string_packet", WAS_STRING_PACKET_HASH)
        }

        private const val SET_WRITE_MODE_HASH = 1999768052L
        private val setWriteModeBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "set_write_mode", SET_WRITE_MODE_HASH)
        }

        private const val GET_WRITE_MODE_HASH = 2848495172L
        private val getWriteModeBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_write_mode", GET_WRITE_MODE_HASH)
        }

        private const val GET_READY_STATE_HASH = 3501143017L
        private val getReadyStateBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_ready_state", GET_READY_STATE_HASH)
        }

        private const val GET_LABEL_HASH = 201670096L
        private val getLabelBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_label", GET_LABEL_HASH)
        }

        private const val IS_ORDERED_HASH = 36873697L
        private val isOrderedBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "is_ordered", IS_ORDERED_HASH)
        }

        private const val GET_ID_HASH = 3905245786L
        private val getIdBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_id", GET_ID_HASH)
        }

        private const val GET_MAX_PACKET_LIFE_TIME_HASH = 3905245786L
        private val getMaxPacketLifeTimeBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_max_packet_life_time", GET_MAX_PACKET_LIFE_TIME_HASH)
        }

        private const val GET_MAX_RETRANSMITS_HASH = 3905245786L
        private val getMaxRetransmitsBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_max_retransmits", GET_MAX_RETRANSMITS_HASH)
        }

        private const val GET_PROTOCOL_HASH = 201670096L
        private val getProtocolBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_protocol", GET_PROTOCOL_HASH)
        }

        private const val IS_NEGOTIATED_HASH = 36873697L
        private val isNegotiatedBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "is_negotiated", IS_NEGOTIATED_HASH)
        }

        private const val GET_BUFFERED_AMOUNT_HASH = 3905245786L
        private val getBufferedAmountBind by lazy {
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_buffered_amount", GET_BUFFERED_AMOUNT_HASH)
        }
    }
}
