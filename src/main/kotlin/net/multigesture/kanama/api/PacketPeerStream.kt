package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Wrapper to use a PacketPeer over a StreamPeer.
 *
 * Generated from Godot docs: PacketPeerStream
 */
class PacketPeerStream(handle: MemorySegment) : PacketPeer(handle) {
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

    fun setStreamPeer(peer: StreamPeer?) {
        ObjectCalls.ptrcallWithObjectArgs(setStreamPeerBind, handle, listOf(peer?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getStreamPeer(): StreamPeer? {
        return StreamPeer.wrap(ObjectCalls.ptrcallNoArgsRetObject(getStreamPeerBind, handle))
    }

    fun setInputBufferMaxSize(maxSizeBytes: Int) {
        ObjectCalls.ptrcallWithIntArg(setInputBufferMaxSizeBind, handle, maxSizeBytes)
    }

    fun setOutputBufferMaxSize(maxSizeBytes: Int) {
        ObjectCalls.ptrcallWithIntArg(setOutputBufferMaxSizeBind, handle, maxSizeBytes)
    }

    fun getInputBufferMaxSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInputBufferMaxSizeBind, handle)
    }

    fun getOutputBufferMaxSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOutputBufferMaxSizeBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PacketPeerStream? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PacketPeerStream? =
            if (handle.address() == 0L) null else PacketPeerStream(handle)

        private const val SET_STREAM_PEER_HASH = 3281897016L
        private val setStreamPeerBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerStream", "set_stream_peer", SET_STREAM_PEER_HASH)
        }

        private const val GET_STREAM_PEER_HASH = 2741655269L
        private val getStreamPeerBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerStream", "get_stream_peer", GET_STREAM_PEER_HASH)
        }

        private const val SET_INPUT_BUFFER_MAX_SIZE_HASH = 1286410249L
        private val setInputBufferMaxSizeBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerStream", "set_input_buffer_max_size", SET_INPUT_BUFFER_MAX_SIZE_HASH)
        }

        private const val SET_OUTPUT_BUFFER_MAX_SIZE_HASH = 1286410249L
        private val setOutputBufferMaxSizeBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerStream", "set_output_buffer_max_size", SET_OUTPUT_BUFFER_MAX_SIZE_HASH)
        }

        private const val GET_INPUT_BUFFER_MAX_SIZE_HASH = 3905245786L
        private val getInputBufferMaxSizeBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerStream", "get_input_buffer_max_size", GET_INPUT_BUFFER_MAX_SIZE_HASH)
        }

        private const val GET_OUTPUT_BUFFER_MAX_SIZE_HASH = 3905245786L
        private val getOutputBufferMaxSizeBind by lazy {
            ObjectCalls.getMethodBind("PacketPeerStream", "get_output_buffer_max_size", GET_OUTPUT_BUFFER_MAX_SIZE_HASH)
        }
    }
}
