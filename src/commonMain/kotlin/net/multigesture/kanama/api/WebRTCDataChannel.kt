package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: WebRTCDataChannel
 */
open class WebRTCDataChannel(handle: GodotHandle) : PacketPeer(handle) {
    var writeMode: WebRTCDataChannel.WriteMode
        @JvmName("writeModeProperty")
        get() = getWriteMode()
        @JvmName("setWriteModeProperty")
        set(value) = setWriteMode(value)

    fun poll(): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallNoArgsRetLong(pollBind, segment))
    }

    fun closeConnection() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(closeConnectionBind, segment)
    }

    fun wasStringPacket(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(wasStringPacketBind, segment)
    }

    fun setWriteMode(writeMode: WebRTCDataChannel.WriteMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setWriteModeBind, segment, writeMode.value)
    }

    fun getWriteMode(): WebRTCDataChannel.WriteMode {
        checkOpen()
        return WebRTCDataChannel.WriteMode(ObjectCalls.ptrcallNoArgsRetLong(getWriteModeBind, segment))
    }

    fun getReadyState(): WebRTCDataChannel.ChannelState {
        checkOpen()
        return WebRTCDataChannel.ChannelState(ObjectCalls.ptrcallNoArgsRetLong(getReadyStateBind, segment))
    }

    fun getLabel(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getLabelBind, segment)
    }

    fun isOrdered(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isOrderedBind, segment)
    }

    fun getId(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getIdBind, segment)
    }

    fun getMaxPacketLifeTime(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxPacketLifeTimeBind, segment)
    }

    fun getMaxRetransmits(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxRetransmitsBind, segment)
    }

    fun getProtocol(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getProtocolBind, segment)
    }

    fun isNegotiated(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isNegotiatedBind, segment)
    }

    fun getBufferedAmount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBufferedAmountBind, segment)
    }

    @JvmInline
    value class WriteMode(override val value: Long) : GodotEnumValue {
        companion object {
            val TEXT: WriteMode get() = WriteMode(0L)
            val BINARY: WriteMode get() = WriteMode(1L)
        }
    }

    @JvmInline
    value class ChannelState(override val value: Long) : GodotEnumValue {
        companion object {
            val CONNECTING: ChannelState get() = ChannelState(0L)
            val OPEN: ChannelState get() = ChannelState(1L)
            val CLOSING: ChannelState get() = ChannelState(2L)
            val CLOSED: ChannelState get() = ChannelState(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WebRTCDataChannel? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): WebRTCDataChannel? =
            if (handle.address() == 0L) null else WebRTCDataChannel(GodotHandle(handle))

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
