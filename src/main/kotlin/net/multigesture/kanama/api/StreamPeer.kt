package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

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
     * Sends a chunk of data through the connection, blocking if necessary until the data is done
     * sending. This function returns an `Error` code.
     *
     * Generated from Godot docs: StreamPeer.put_data
     */
    fun putData(data: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(putDataBind, handle, data)
    }

    /**
     * Sends a chunk of data through the connection. If all the data could not be sent at once, only
     * part of it will. This function returns two values, an `Error` code and an integer, describing
     * how much data was actually sent.
     *
     * Generated from Godot docs: StreamPeer.put_partial_data
     */
    fun putPartialData(data: ByteArray): List<Any?> {
        return ObjectCalls.ptrcallWithByteArrayArgRetArray(putPartialDataBind, handle, data)
    }

    /**
     * Returns a chunk data with the received bytes, as an `Array` containing two elements: an `Error`
     * constant and a `PackedByteArray`. `bytes` is the number of bytes to be received. If not enough
     * bytes are available, the function will block until the desired amount is received.
     *
     * Generated from Godot docs: StreamPeer.get_data
     */
    fun getData(bytes: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntArgRetArray(getDataBind, handle, bytes)
    }

    /**
     * Returns a chunk data with the received bytes, as an `Array` containing two elements: an `Error`
     * constant and a `PackedByteArray`. `bytes` is the number of bytes to be received. If not enough
     * bytes are available, the function will return how many were actually received.
     *
     * Generated from Godot docs: StreamPeer.get_partial_data
     */
    fun getPartialData(bytes: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntArgRetArray(getPartialDataBind, handle, bytes)
    }

    /**
     * Returns the number of bytes this `StreamPeer` has available.
     *
     * Generated from Godot docs: StreamPeer.get_available_bytes
     */
    fun getAvailableBytes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAvailableBytesBind, handle)
    }

    /**
     * If `true`, this `StreamPeer` will using big-endian format for encoding and decoding.
     *
     * Generated from Godot docs: StreamPeer.set_big_endian
     */
    fun setBigEndian(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBigEndianBind, handle, enable)
    }

    /**
     * If `true`, this `StreamPeer` will using big-endian format for encoding and decoding.
     *
     * Generated from Godot docs: StreamPeer.is_big_endian_enabled
     */
    fun isBigEndianEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBigEndianEnabledBind, handle)
    }

    /**
     * Puts a signed byte into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_8
     */
    fun put8(value: Int) {
        ObjectCalls.ptrcallWithIntArg(put8Bind, handle, value)
    }

    /**
     * Puts an unsigned byte into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_u8
     */
    fun putU8(value: Int) {
        ObjectCalls.ptrcallWithIntArg(putU8Bind, handle, value)
    }

    /**
     * Puts a signed 16-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_16
     */
    fun put16(value: Int) {
        ObjectCalls.ptrcallWithIntArg(put16Bind, handle, value)
    }

    /**
     * Puts an unsigned 16-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_u16
     */
    fun putU16(value: Int) {
        ObjectCalls.ptrcallWithIntArg(putU16Bind, handle, value)
    }

    /**
     * Puts a signed 32-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_32
     */
    fun put32(value: Int) {
        ObjectCalls.ptrcallWithIntArg(put32Bind, handle, value)
    }

    /**
     * Puts an unsigned 32-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_u32
     */
    fun putU32(value: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(putU32Bind, handle, value)
    }

    /**
     * Puts a signed 64-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_64
     */
    fun put64(value: Long) {
        ObjectCalls.ptrcallWithLongArg(put64Bind, handle, value)
    }

    /**
     * Puts an unsigned 64-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_u64
     */
    fun putU64(value: Long) {
        ObjectCalls.ptrcallWithLongArg(putU64Bind, handle, value)
    }

    /**
     * Puts a half-precision float into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_half
     */
    fun putHalf(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(putHalfBind, handle, value)
    }

    /**
     * Puts a single-precision float into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_float
     */
    fun putFloat(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(putFloatBind, handle, value)
    }

    /**
     * Puts a double-precision float into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_double
     */
    fun putDouble(value: Double) {
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
        ObjectCalls.ptrcallWithStringArg(putUtf8StringBind, handle, value)
    }

    /**
     * Puts a Variant into the stream. If `full_objects` is `true` encoding objects is allowed (and can
     * potentially include code). Internally, this uses the same encoding mechanism as the
     * `@GlobalScope.var_to_bytes` method.
     *
     * Generated from Godot docs: StreamPeer.put_var
     */
    fun putVar(value: Any?, fullObjects: Boolean = false) {
        ObjectCalls.ptrcallWithVariantAndBoolArg(putVarBind, handle, value, fullObjects)
    }

    /**
     * Gets a signed byte from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_8
     */
    fun get8(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(get8Bind, handle)
    }

    /**
     * Gets an unsigned byte from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_u8
     */
    fun getU8(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getU8Bind, handle)
    }

    /**
     * Gets a signed 16-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_16
     */
    fun get16(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(get16Bind, handle)
    }

    /**
     * Gets an unsigned 16-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_u16
     */
    fun getU16(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getU16Bind, handle)
    }

    /**
     * Gets a signed 32-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_32
     */
    fun get32(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(get32Bind, handle)
    }

    /**
     * Gets an unsigned 32-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_u32
     */
    fun getU32(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getU32Bind, handle)
    }

    /**
     * Gets a signed 64-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_64
     */
    fun get64(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(get64Bind, handle)
    }

    /**
     * Gets an unsigned 64-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_u64
     */
    fun getU64(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getU64Bind, handle)
    }

    /**
     * Gets a half-precision float from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_half
     */
    fun getHalf(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHalfBind, handle)
    }

    /**
     * Gets a single-precision float from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_float
     */
    fun getFloat(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFloatBind, handle)
    }

    /**
     * Gets a double-precision float from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_double
     */
    fun getDouble(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDoubleBind, handle)
    }

    /**
     * Gets an ASCII string with byte-length `bytes` from the stream. If `bytes` is negative (default)
     * the length will be read from the stream using the reverse process of `put_string`.
     *
     * Generated from Godot docs: StreamPeer.get_string
     */
    fun getString(bytes: Int = -1): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getStringBind, handle, bytes)
    }

    /**
     * Gets a UTF-8 string with byte-length `bytes` from the stream (this decodes the string sent as
     * UTF-8). If `bytes` is negative (default) the length will be read from the stream using the
     * reverse process of `put_utf8_string`.
     *
     * Generated from Godot docs: StreamPeer.get_utf8_string
     */
    fun getUtf8String(bytes: Int = -1): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getUtf8StringBind, handle, bytes)
    }

    /**
     * Gets a Variant from the stream. If `allow_objects` is `true`, decoding objects is allowed.
     * Internally, this uses the same decoding mechanism as the `@GlobalScope.bytes_to_var` method.
     * Warning: Deserialized objects can contain code which gets executed. Do not use this option if
     * the serialized object comes from untrusted sources to avoid potential security threats such as
     * remote code execution.
     *
     * Generated from Godot docs: StreamPeer.get_var
     */
    fun getVar(allowObjects: Boolean = false): Any? {
        return ObjectCalls.ptrcallWithBoolArgRetVariantScalar(getVarBind, handle, allowObjects)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): StreamPeer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StreamPeer? =
            if (handle.address() == 0L) null else StreamPeer(handle)

        private const val PUT_DATA_HASH = 680677267L
        private val putDataBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_data", PUT_DATA_HASH)
        }

        private const val PUT_PARTIAL_DATA_HASH = 2934048347L
        private val putPartialDataBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_partial_data", PUT_PARTIAL_DATA_HASH)
        }

        private const val GET_DATA_HASH = 1171824711L
        private val getDataBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_data", GET_DATA_HASH)
        }

        private const val GET_PARTIAL_DATA_HASH = 1171824711L
        private val getPartialDataBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_partial_data", GET_PARTIAL_DATA_HASH)
        }

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

        private const val PUT_VAR_HASH = 738511890L
        private val putVarBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "put_var", PUT_VAR_HASH)
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

        private const val GET_STRING_HASH = 2309358862L
        private val getStringBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_string", GET_STRING_HASH)
        }

        private const val GET_UTF8_STRING_HASH = 2309358862L
        private val getUtf8StringBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_utf8_string", GET_UTF8_STRING_HASH)
        }

        private const val GET_VAR_HASH = 3442865206L
        private val getVarBind by lazy {
            ObjectCalls.getMethodBind("StreamPeer", "get_var", GET_VAR_HASH)
        }
    }
}
