package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Wrapper to use a PacketPeer over a StreamPeer.
 *
 * Generated from Godot docs: PacketPeerStream
 */
class PacketPeerStream(handle: GodotHandle) : PacketPeer(handle) {
    var inputBufferMaxSize: Int
        @JvmName("inputBufferMaxSizeProperty")
        get() = getInputBufferMaxSize()
        @JvmName("setInputBufferMaxSizeProperty")
        set(value) = setInputBufferMaxSize(value)

    var outputBufferMaxSize: Int
        @JvmName("outputBufferMaxSizeProperty")
        get() = getOutputBufferMaxSize()
        @JvmName("setOutputBufferMaxSizeProperty")
        set(value) = setOutputBufferMaxSize(value)

    var streamPeer: StreamPeer?
        @JvmName("streamPeerProperty")
        get() = getStreamPeer()
        @JvmName("setStreamPeerProperty")
        set(value) = setStreamPeer(value)

    /**
     * The wrapped `StreamPeer` object.
     *
     * Generated from Godot docs: PacketPeerStream.set_stream_peer
     */
    fun setStreamPeer(peer: StreamPeer?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setStreamPeerBind, segment, listOf(peer?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The wrapped `StreamPeer` object.
     *
     * Generated from Godot docs: PacketPeerStream.get_stream_peer
     */
    fun getStreamPeer(): StreamPeer? {
        checkOpen()
        return StreamPeer.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getStreamPeerBind, segment))
    }

    fun setInputBufferMaxSize(maxSizeBytes: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setInputBufferMaxSizeBind, segment, maxSizeBytes)
    }

    fun setOutputBufferMaxSize(maxSizeBytes: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setOutputBufferMaxSizeBind, segment, maxSizeBytes)
    }

    fun getInputBufferMaxSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getInputBufferMaxSizeBind, segment)
    }

    fun getOutputBufferMaxSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getOutputBufferMaxSizeBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PacketPeerStream? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PacketPeerStream? =
            if (handle.address() == 0L) null else RefCounted.owned(PacketPeerStream(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PacketPeerStream? =
            if (handle.address() == 0L) null else PacketPeerStream(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_STREAM_PEER_HASH = 3281897016L
        @JvmField
        val setStreamPeerBind =
            ObjectCalls.getMethodBind("PacketPeerStream", "set_stream_peer", SET_STREAM_PEER_HASH)

        private const val GET_STREAM_PEER_HASH = 2741655269L
        @JvmField
        val getStreamPeerBind =
            ObjectCalls.getMethodBind("PacketPeerStream", "get_stream_peer", GET_STREAM_PEER_HASH)

        private const val SET_INPUT_BUFFER_MAX_SIZE_HASH = 1286410249L
        @JvmField
        val setInputBufferMaxSizeBind =
            ObjectCalls.getMethodBind("PacketPeerStream", "set_input_buffer_max_size", SET_INPUT_BUFFER_MAX_SIZE_HASH)

        private const val SET_OUTPUT_BUFFER_MAX_SIZE_HASH = 1286410249L
        @JvmField
        val setOutputBufferMaxSizeBind =
            ObjectCalls.getMethodBind("PacketPeerStream", "set_output_buffer_max_size", SET_OUTPUT_BUFFER_MAX_SIZE_HASH)

        private const val GET_INPUT_BUFFER_MAX_SIZE_HASH = 3905245786L
        @JvmField
        val getInputBufferMaxSizeBind =
            ObjectCalls.getMethodBind("PacketPeerStream", "get_input_buffer_max_size", GET_INPUT_BUFFER_MAX_SIZE_HASH)

        private const val GET_OUTPUT_BUFFER_MAX_SIZE_HASH = 3905245786L
        @JvmField
        val getOutputBufferMaxSizeBind =
            ObjectCalls.getMethodBind("PacketPeerStream", "get_output_buffer_max_size", GET_OUTPUT_BUFFER_MAX_SIZE_HASH)
    }
}
