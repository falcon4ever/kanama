package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: PacketPeer
 */
open class PacketPeer(handle: MemorySegment) : RefCounted(handle) {
    var encodeBufferMaxSize: Int
        @JvmName("encodeBufferMaxSizeProperty")
        get() = getEncodeBufferMaxSize()
        @JvmName("setEncodeBufferMaxSizeProperty")
        set(value) = setEncodeBufferMaxSize(value)

    fun getPacketError(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getPacketErrorBind, handle)
    }

    fun getAvailablePacketCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getAvailablePacketCountBind, handle)
    }

    fun getEncodeBufferMaxSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getEncodeBufferMaxSizeBind, handle)
    }

    fun setEncodeBufferMaxSize(maxSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setEncodeBufferMaxSizeBind, handle, maxSize)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): PacketPeer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PacketPeer? =
            if (handle.address() == 0L) null else PacketPeer(handle)

        private const val GET_PACKET_ERROR_HASH = 3185525595L
        private val getPacketErrorBind by lazy {
            ObjectCalls.getMethodBind("PacketPeer", "get_packet_error", GET_PACKET_ERROR_HASH)
        }

        private const val GET_AVAILABLE_PACKET_COUNT_HASH = 3905245786L
        private val getAvailablePacketCountBind by lazy {
            ObjectCalls.getMethodBind("PacketPeer", "get_available_packet_count", GET_AVAILABLE_PACKET_COUNT_HASH)
        }

        private const val GET_ENCODE_BUFFER_MAX_SIZE_HASH = 3905245786L
        private val getEncodeBufferMaxSizeBind by lazy {
            ObjectCalls.getMethodBind("PacketPeer", "get_encode_buffer_max_size", GET_ENCODE_BUFFER_MAX_SIZE_HASH)
        }

        private const val SET_ENCODE_BUFFER_MAX_SIZE_HASH = 1286410249L
        private val setEncodeBufferMaxSizeBind by lazy {
            ObjectCalls.getMethodBind("PacketPeer", "set_encode_buffer_max_size", SET_ENCODE_BUFFER_MAX_SIZE_HASH)
        }
    }
}
