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

    /**
     * Gets a Variant. If `allow_objects` is `true`, decoding objects is allowed. Internally, this uses
     * the same decoding mechanism as the `@GlobalScope.bytes_to_var` method. Warning: Deserialized
     * objects can contain code which gets executed. Do not use this option if the serialized object
     * comes from untrusted sources to avoid potential security threats such as remote code execution.
     *
     * Generated from Godot docs: PacketPeer.get_var
     */
    fun getVar(allowObjects: Boolean = false): Any? {
        return ObjectCalls.ptrcallWithBoolArgRetVariantScalar(getVarBind, handle, allowObjects)
    }

    /**
     * Sends a `Variant` as a packet. If `full_objects` is `true`, encoding objects is allowed (and can
     * potentially include code). Internally, this uses the same encoding mechanism as the
     * `@GlobalScope.var_to_bytes` method.
     *
     * Generated from Godot docs: PacketPeer.put_var
     */
    fun putVar(varValue: Any?, fullObjects: Boolean = false): Long {
        return ObjectCalls.ptrcallWithVariantAndBoolArgRetLong(putVarBind, handle, varValue, fullObjects)
    }

    /**
     * Gets a raw packet.
     *
     * Generated from Godot docs: PacketPeer.get_packet
     */
    fun getPacket(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(getPacketBind, handle)
    }

    /**
     * Sends a raw packet.
     *
     * Generated from Godot docs: PacketPeer.put_packet
     */
    fun putPacket(buffer: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(putPacketBind, handle, buffer)
    }

    /**
     * Returns the error state of the last packet received (via `get_packet` and `get_var`).
     *
     * Generated from Godot docs: PacketPeer.get_packet_error
     */
    fun getPacketError(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPacketErrorBind, handle)
    }

    /**
     * Returns the number of packets currently available in the ring-buffer.
     *
     * Generated from Godot docs: PacketPeer.get_available_packet_count
     */
    fun getAvailablePacketCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAvailablePacketCountBind, handle)
    }

    /**
     * Maximum buffer size allowed when encoding `Variant`s. Raise this value to support heavier memory
     * allocations. The `put_var` method allocates memory on the stack, and the buffer used will grow
     * automatically to the closest power of two to match the size of the `Variant`. If the `Variant`
     * is bigger than `encode_buffer_max_size`, the method will error out with `ERR_OUT_OF_MEMORY`.
     *
     * Generated from Godot docs: PacketPeer.get_encode_buffer_max_size
     */
    fun getEncodeBufferMaxSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getEncodeBufferMaxSizeBind, handle)
    }

    /**
     * Maximum buffer size allowed when encoding `Variant`s. Raise this value to support heavier memory
     * allocations. The `put_var` method allocates memory on the stack, and the buffer used will grow
     * automatically to the closest power of two to match the size of the `Variant`. If the `Variant`
     * is bigger than `encode_buffer_max_size`, the method will error out with `ERR_OUT_OF_MEMORY`.
     *
     * Generated from Godot docs: PacketPeer.set_encode_buffer_max_size
     */
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
