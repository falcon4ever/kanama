package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Abstraction and base class for packet-based protocols.
 *
 * Generated from Godot docs: PacketPeer
 */
open class PacketPeer(handle: MemorySegment) : RefCounted(handle) {
    var encodeBufferMaxSize: Int
        @JvmName("encodeBufferMaxSizeProperty")
        get() = getEncodeBufferMaxSize()
        @JvmName("setEncodeBufferMaxSizeProperty")
        set(value) = setEncodeBufferMaxSize(value)

    fun getVar(allowObjects: Boolean = false): Any? {
        return ObjectCalls.ptrcallWithBoolArgRetVariantScalar(getVarBind, handle, allowObjects)
    }

    fun putVar(varValue: Any?, fullObjects: Boolean = false): Long {
        return ObjectCalls.ptrcallWithVariantAndBoolArgRetLong(putVarBind, handle, varValue, fullObjects)
    }

    fun getPacket(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(getPacketBind, handle)
    }

    fun putPacket(buffer: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(putPacketBind, handle, buffer)
    }

    fun getPacketError(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPacketErrorBind, handle)
    }

    fun getAvailablePacketCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAvailablePacketCountBind, handle)
    }

    fun getEncodeBufferMaxSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getEncodeBufferMaxSizeBind, handle)
    }

    fun setEncodeBufferMaxSize(maxSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setEncodeBufferMaxSizeBind, handle, maxSize)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PacketPeer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PacketPeer? =
            if (handle.address() == 0L) null else PacketPeer(handle)

        private const val GET_VAR_HASH = 3442865206L
        private val getVarBind by lazy {
            ObjectCalls.getMethodBind("PacketPeer", "get_var", GET_VAR_HASH)
        }

        private const val PUT_VAR_HASH = 2436251611L
        private val putVarBind by lazy {
            ObjectCalls.getMethodBind("PacketPeer", "put_var", PUT_VAR_HASH)
        }

        private const val GET_PACKET_HASH = 2115431945L
        private val getPacketBind by lazy {
            ObjectCalls.getMethodBind("PacketPeer", "get_packet", GET_PACKET_HASH)
        }

        private const val PUT_PACKET_HASH = 680677267L
        private val putPacketBind by lazy {
            ObjectCalls.getMethodBind("PacketPeer", "put_packet", PUT_PACKET_HASH)
        }

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
