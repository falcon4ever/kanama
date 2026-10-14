package net.multigesture.kanama.api

import kotlin.jvm.JvmField
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

    /**
     * Reserved, but not used for now.
     *
     * Generated from Godot docs: WebRTCDataChannel.poll
     */
    fun poll(): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallNoArgsRetLong(Binds.pollBind, segment))
    }

    /**
     * Closes this data channel, notifying the other peer.
     *
     * Generated from Godot docs: WebRTCDataChannel.close
     */
    fun closeConnection() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.closeConnectionBind, segment)
    }

    /**
     * Returns `true` if the last received packet was transferred as text. See `write_mode`.
     *
     * Generated from Godot docs: WebRTCDataChannel.was_string_packet
     */
    fun wasStringPacket(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.wasStringPacketBind, segment)
    }

    /**
     * The transfer mode to use when sending outgoing packet. Either text or binary.
     *
     * Generated from Godot docs: WebRTCDataChannel.set_write_mode
     */
    fun setWriteMode(writeMode: WebRTCDataChannel.WriteMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setWriteModeBind, segment, writeMode.value)
    }

    /**
     * The transfer mode to use when sending outgoing packet. Either text or binary.
     *
     * Generated from Godot docs: WebRTCDataChannel.get_write_mode
     */
    fun getWriteMode(): WebRTCDataChannel.WriteMode {
        checkOpen()
        return WebRTCDataChannel.WriteMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getWriteModeBind, segment))
    }

    /**
     * Returns the current state of this channel.
     *
     * Generated from Godot docs: WebRTCDataChannel.get_ready_state
     */
    fun getReadyState(): WebRTCDataChannel.ChannelState {
        checkOpen()
        return WebRTCDataChannel.ChannelState(ObjectCalls.ptrcallNoArgsRetLong(Binds.getReadyStateBind, segment))
    }

    /**
     * Returns the label assigned to this channel during creation.
     *
     * Generated from Godot docs: WebRTCDataChannel.get_label
     */
    fun getLabel(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getLabelBind, segment)
    }

    /**
     * Returns `true` if this channel was created with ordering enabled (default).
     *
     * Generated from Godot docs: WebRTCDataChannel.is_ordered
     */
    fun isOrdered(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isOrderedBind, segment)
    }

    /**
     * Returns the ID assigned to this channel during creation (or auto-assigned during negotiation).
     * If the channel is not negotiated out-of-band the ID will only be available after the connection
     * is established (will return `65535` until then).
     *
     * Generated from Godot docs: WebRTCDataChannel.get_id
     */
    fun getId(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getIdBind, segment)
    }

    /**
     * Returns the `maxPacketLifeTime` value assigned to this channel during creation. Will be `65535`
     * if not specified.
     *
     * Generated from Godot docs: WebRTCDataChannel.get_max_packet_life_time
     */
    fun getMaxPacketLifeTime(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxPacketLifeTimeBind, segment)
    }

    /**
     * Returns the `maxRetransmits` value assigned to this channel during creation. Will be `65535` if
     * not specified.
     *
     * Generated from Godot docs: WebRTCDataChannel.get_max_retransmits
     */
    fun getMaxRetransmits(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxRetransmitsBind, segment)
    }

    /**
     * Returns the sub-protocol assigned to this channel during creation. An empty string if not
     * specified.
     *
     * Generated from Godot docs: WebRTCDataChannel.get_protocol
     */
    fun getProtocol(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getProtocolBind, segment)
    }

    /**
     * Returns `true` if this channel was created with out-of-band configuration.
     *
     * Generated from Godot docs: WebRTCDataChannel.is_negotiated
     */
    fun isNegotiated(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isNegotiatedBind, segment)
    }

    /**
     * Returns the number of bytes currently queued to be sent over this channel.
     *
     * Generated from Godot docs: WebRTCDataChannel.get_buffered_amount
     */
    fun getBufferedAmount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBufferedAmountBind, segment)
    }

    /**
     * Godot's `WebRTCDataChannel.WriteMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`WebRTCDataChannel.WriteMode.<NAME>`).
     *
     * Generated from Godot docs: WebRTCDataChannel.WriteMode
     */
    @JvmInline
    value class WriteMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Tells the channel to send data over this channel as text. An external peer (non-Godot) would
             * receive this as a string.
             *
             * Generated from Godot docs: WebRTCDataChannel.WRITE_MODE_TEXT
             */
            val TEXT: WriteMode get() = WriteMode(0L)
            /**
             * Tells the channel to send data over this channel as binary. An external peer (non-Godot) would
             * receive this as array buffer or blob.
             *
             * Generated from Godot docs: WebRTCDataChannel.WRITE_MODE_BINARY
             */
            val BINARY: WriteMode get() = WriteMode(1L)
        }
    }

    /**
     * Godot's `WebRTCDataChannel.ChannelState` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`WebRTCDataChannel.ChannelState.<NAME>`).
     *
     * Generated from Godot docs: WebRTCDataChannel.ChannelState
     */
    @JvmInline
    value class ChannelState(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The channel was created, but it's still trying to connect.
             *
             * Generated from Godot docs: WebRTCDataChannel.STATE_CONNECTING
             */
            val CONNECTING: ChannelState get() = ChannelState(0L)
            /**
             * The channel is currently open, and data can flow over it.
             *
             * Generated from Godot docs: WebRTCDataChannel.STATE_OPEN
             */
            val OPEN: ChannelState get() = ChannelState(1L)
            /**
             * The channel is being closed, no new messages will be accepted, but those already in queue will
             * be flushed.
             *
             * Generated from Godot docs: WebRTCDataChannel.STATE_CLOSING
             */
            val CLOSING: ChannelState get() = ChannelState(2L)
            /**
             * The channel was closed, or connection failed.
             *
             * Generated from Godot docs: WebRTCDataChannel.STATE_CLOSED
             */
            val CLOSED: ChannelState get() = ChannelState(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): WebRTCDataChannel? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): WebRTCDataChannel? =
            if (handle.address() == 0L) null else RefCounted.owned(WebRTCDataChannel(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): WebRTCDataChannel? =
            if (handle.address() == 0L) null else WebRTCDataChannel(GodotHandle(handle))
    }

    private object Binds {
        private const val POLL_HASH = 166280745L
        @JvmField
        val pollBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "poll", POLL_HASH)

        private const val CLOSE_HASH = 3218959716L
        @JvmField
        val closeConnectionBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "close", CLOSE_HASH)

        private const val WAS_STRING_PACKET_HASH = 36873697L
        @JvmField
        val wasStringPacketBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "was_string_packet", WAS_STRING_PACKET_HASH)

        private const val SET_WRITE_MODE_HASH = 1999768052L
        @JvmField
        val setWriteModeBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "set_write_mode", SET_WRITE_MODE_HASH)

        private const val GET_WRITE_MODE_HASH = 2848495172L
        @JvmField
        val getWriteModeBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_write_mode", GET_WRITE_MODE_HASH)

        private const val GET_READY_STATE_HASH = 3501143017L
        @JvmField
        val getReadyStateBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_ready_state", GET_READY_STATE_HASH)

        private const val GET_LABEL_HASH = 201670096L
        @JvmField
        val getLabelBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_label", GET_LABEL_HASH)

        private const val IS_ORDERED_HASH = 36873697L
        @JvmField
        val isOrderedBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "is_ordered", IS_ORDERED_HASH)

        private const val GET_ID_HASH = 3905245786L
        @JvmField
        val getIdBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_id", GET_ID_HASH)

        private const val GET_MAX_PACKET_LIFE_TIME_HASH = 3905245786L
        @JvmField
        val getMaxPacketLifeTimeBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_max_packet_life_time", GET_MAX_PACKET_LIFE_TIME_HASH)

        private const val GET_MAX_RETRANSMITS_HASH = 3905245786L
        @JvmField
        val getMaxRetransmitsBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_max_retransmits", GET_MAX_RETRANSMITS_HASH)

        private const val GET_PROTOCOL_HASH = 201670096L
        @JvmField
        val getProtocolBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_protocol", GET_PROTOCOL_HASH)

        private const val IS_NEGOTIATED_HASH = 36873697L
        @JvmField
        val isNegotiatedBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "is_negotiated", IS_NEGOTIATED_HASH)

        private const val GET_BUFFERED_AMOUNT_HASH = 3905245786L
        @JvmField
        val getBufferedAmountBind =
            ObjectCalls.getMethodBind("WebRTCDataChannel", "get_buffered_amount", GET_BUFFERED_AMOUNT_HASH)
    }
}
