package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Abstract base class for interacting with streams.
 *
 * Generated from Godot docs: StreamPeer
 */
open class StreamPeer(handle: MemorySegment) : RefCounted(handle) {
    var bigEndian: Boolean
        @JvmName("bigEndianProperty")
        get() = isBigEndianEnabled()
        @JvmName("setBigEndianProperty")
        set(value) = setBigEndian(value)

    /**
     * Returns the number of bytes this `StreamPeer` has available.
     *
     * Generated from Godot docs: StreamPeer.get_available_bytes
     */
    fun getAvailableBytes(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getAvailableBytesBind, handle)
    }

    /**
     * If `true`, this `StreamPeer` will using big-endian format for encoding and decoding.
     *
     * Generated from Godot docs: StreamPeer.set_big_endian
     */
    fun setBigEndian(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setBigEndianBind, handle, enable)
    }

    /**
     * If `true`, this `StreamPeer` will using big-endian format for encoding and decoding.
     *
     * Generated from Godot docs: StreamPeer.is_big_endian_enabled
     */
    fun isBigEndianEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isBigEndianEnabledBind, handle)
    }

    /**
     * Puts a signed byte into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_8
     */
    fun put8(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(put8Bind, handle, value)
    }

    /**
     * Puts an unsigned byte into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_u8
     */
    fun putU8(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(putU8Bind, handle, value)
    }

    /**
     * Puts a signed 16-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_16
     */
    fun put16(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(put16Bind, handle, value)
    }

    /**
     * Puts an unsigned 16-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_u16
     */
    fun putU16(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(putU16Bind, handle, value)
    }

    /**
     * Puts a signed 32-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_32
     */
    fun put32(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(put32Bind, handle, value)
    }

    /**
     * Puts an unsigned 32-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_u32
     */
    fun putU32(value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(putU32Bind, handle, value)
    }

    /**
     * Puts a signed 64-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_64
     */
    fun put64(value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(put64Bind, handle, value)
    }

    /**
     * Puts an unsigned 64-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_u64
     */
    fun putU64(value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(putU64Bind, handle, value)
    }

    /**
     * Puts a half-precision float into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_half
     */
    fun putHalf(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(putHalfBind, handle, value)
    }

    /**
     * Puts a single-precision float into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_float
     */
    fun putFloat(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(putFloatBind, handle, value)
    }

    /**
     * Puts a double-precision float into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_double
     */
    fun putDouble(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(putDoubleBind, handle, value)
    }

    /**
     * Puts a zero-terminated ASCII string into the stream prepended by a 32-bit unsigned integer
     * representing its size. Note: To put an ASCII string without prepending its size, you can use
     * `put_data`:
     *
     * Generated from Godot docs: StreamPeer.put_string
     */
    fun putString(value: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(putStringBind, handle, value)
    }

    /**
     * Puts a zero-terminated UTF-8 string into the stream prepended by a 32 bits unsigned integer
     * representing its size. Note: To put a UTF-8 string without prepending its size, you can use
     * `put_data`:
     *
     * Generated from Godot docs: StreamPeer.put_utf8_string
     */
    fun putUtf8String(value: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(putUtf8StringBind, handle, value)
    }

    /**
     * Gets a signed byte from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_8
     */
    fun get8(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(get8Bind, handle)
    }

    /**
     * Gets an unsigned byte from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_u8
     */
    fun getU8(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getU8Bind, handle)
    }

    /**
     * Gets a signed 16-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_16
     */
    fun get16(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(get16Bind, handle)
    }

    /**
     * Gets an unsigned 16-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_u16
     */
    fun getU16(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getU16Bind, handle)
    }

    /**
     * Gets a signed 32-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_32
     */
    fun get32(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(get32Bind, handle)
    }

    /**
     * Gets an unsigned 32-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_u32
     */
    fun getU32(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(getU32Bind, handle)
    }

    /**
     * Gets a signed 64-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_64
     */
    fun get64(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(get64Bind, handle)
    }

    /**
     * Gets an unsigned 64-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_u64
     */
    fun getU64(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getU64Bind, handle)
    }

    /**
     * Gets a half-precision float from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_half
     */
    fun getHalf(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getHalfBind, handle)
    }

    /**
     * Gets a single-precision float from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_float
     */
    fun getFloat(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getFloatBind, handle)
    }

    /**
     * Gets a double-precision float from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_double
     */
    fun getDouble(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDoubleBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): StreamPeer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StreamPeer? =
            if (handle.address() == 0L) null else StreamPeer(handle)

        private const val GET_AVAILABLE_BYTES_HASH = 3905245786L
        private val getAvailableBytesBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_available_bytes", GET_AVAILABLE_BYTES_HASH)
        }

        private const val SET_BIG_ENDIAN_HASH = 2586408642L
        private val setBigEndianBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "set_big_endian", SET_BIG_ENDIAN_HASH)
        }

        private const val IS_BIG_ENDIAN_ENABLED_HASH = 36873697L
        private val isBigEndianEnabledBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "is_big_endian_enabled", IS_BIG_ENDIAN_ENABLED_HASH)
        }

        private const val PUT_8_HASH = 1286410249L
        private val put8Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_8", PUT_8_HASH)
        }

        private const val PUT_U8_HASH = 1286410249L
        private val putU8Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_u8", PUT_U8_HASH)
        }

        private const val PUT_16_HASH = 1286410249L
        private val put16Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_16", PUT_16_HASH)
        }

        private const val PUT_U16_HASH = 1286410249L
        private val putU16Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_u16", PUT_U16_HASH)
        }

        private const val PUT_32_HASH = 1286410249L
        private val put32Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_32", PUT_32_HASH)
        }

        private const val PUT_U32_HASH = 1286410249L
        private val putU32Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_u32", PUT_U32_HASH)
        }

        private const val PUT_64_HASH = 1286410249L
        private val put64Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_64", PUT_64_HASH)
        }

        private const val PUT_U64_HASH = 1286410249L
        private val putU64Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_u64", PUT_U64_HASH)
        }

        private const val PUT_HALF_HASH = 373806689L
        private val putHalfBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_half", PUT_HALF_HASH)
        }

        private const val PUT_FLOAT_HASH = 373806689L
        private val putFloatBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_float", PUT_FLOAT_HASH)
        }

        private const val PUT_DOUBLE_HASH = 373806689L
        private val putDoubleBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_double", PUT_DOUBLE_HASH)
        }

        private const val PUT_STRING_HASH = 83702148L
        private val putStringBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_string", PUT_STRING_HASH)
        }

        private const val PUT_UTF8_STRING_HASH = 83702148L
        private val putUtf8StringBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_utf8_string", PUT_UTF8_STRING_HASH)
        }

        private const val GET_8_HASH = 2455072627L
        private val get8Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_8", GET_8_HASH)
        }

        private const val GET_U8_HASH = 2455072627L
        private val getU8Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_u8", GET_U8_HASH)
        }

        private const val GET_16_HASH = 2455072627L
        private val get16Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_16", GET_16_HASH)
        }

        private const val GET_U16_HASH = 2455072627L
        private val getU16Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_u16", GET_U16_HASH)
        }

        private const val GET_32_HASH = 2455072627L
        private val get32Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_32", GET_32_HASH)
        }

        private const val GET_U32_HASH = 2455072627L
        private val getU32Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_u32", GET_U32_HASH)
        }

        private const val GET_64_HASH = 2455072627L
        private val get64Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_64", GET_64_HASH)
        }

        private const val GET_U64_HASH = 2455072627L
        private val getU64Bind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_u64", GET_U64_HASH)
        }

        private const val GET_HALF_HASH = 191475506L
        private val getHalfBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_half", GET_HALF_HASH)
        }

        private const val GET_FLOAT_HASH = 191475506L
        private val getFloatBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_float", GET_FLOAT_HASH)
        }

        private const val GET_DOUBLE_HASH = 191475506L
        private val getDoubleBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_double", GET_DOUBLE_HASH)
        }
    }
}
