package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract base class for interacting with streams.
 *
 * Generated from Godot docs: StreamPeer
 */
open class StreamPeer(handle: GodotHandle) : RefCounted(handle) {
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
    fun putData(data: ByteArray): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithByteArrayArgRetLong(Binds.putDataBind, segment, data))
    }

    /**
     * Sends a chunk of data through the connection. If all the data could not be sent at once, only
     * part of it will. This function returns two values, an `Error` code and an integer, describing
     * how much data was actually sent.
     *
     * Generated from Godot docs: StreamPeer.put_partial_data
     */
    fun putPartialData(data: ByteArray): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithByteArrayArgRetArray(Binds.putPartialDataBind, segment, data)
    }

    /**
     * Returns a chunk data with the received bytes, as an `Array` containing two elements: an `Error`
     * constant and a `PackedByteArray`. `bytes` is the number of bytes to be received. If not enough
     * bytes are available, the function will block until the desired amount is received.
     *
     * Generated from Godot docs: StreamPeer.get_data
     */
    fun getData(bytes: Int): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetArray(Binds.getDataBind, segment, bytes)
    }

    /**
     * Returns a chunk data with the received bytes, as an `Array` containing two elements: an `Error`
     * constant and a `PackedByteArray`. `bytes` is the number of bytes to be received. If not enough
     * bytes are available, the function will return how many were actually received.
     *
     * Generated from Godot docs: StreamPeer.get_partial_data
     */
    fun getPartialData(bytes: Int): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetArray(Binds.getPartialDataBind, segment, bytes)
    }

    /**
     * Returns the number of bytes this `StreamPeer` has available.
     *
     * Generated from Godot docs: StreamPeer.get_available_bytes
     */
    fun getAvailableBytes(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getAvailableBytesBind, segment)
    }

    /**
     * If `true`, this `StreamPeer` will using big-endian format for encoding and decoding.
     *
     * Generated from Godot docs: StreamPeer.set_big_endian
     */
    fun setBigEndian(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setBigEndianBind, segment, enable)
    }

    /**
     * If `true`, this `StreamPeer` will using big-endian format for encoding and decoding.
     *
     * Generated from Godot docs: StreamPeer.is_big_endian_enabled
     */
    fun isBigEndianEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isBigEndianEnabledBind, segment)
    }

    /**
     * Puts a signed byte into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_8
     */
    fun put8(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.put8Bind, segment, value)
    }

    /**
     * Puts an unsigned byte into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_u8
     */
    fun putU8(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.putU8Bind, segment, value)
    }

    /**
     * Puts a signed 16-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_16
     */
    fun put16(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.put16Bind, segment, value)
    }

    /**
     * Puts an unsigned 16-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_u16
     */
    fun putU16(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.putU16Bind, segment, value)
    }

    /**
     * Puts a signed 32-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_32
     */
    fun put32(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.put32Bind, segment, value)
    }

    /**
     * Puts an unsigned 32-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_u32
     */
    fun putU32(value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.putU32Bind, segment, value)
    }

    /**
     * Puts a signed 64-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_64
     */
    fun put64(value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.put64Bind, segment, value)
    }

    /**
     * Puts an unsigned 64-bit value into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_u64
     */
    fun putU64(value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.putU64Bind, segment, value)
    }

    /**
     * Puts a half-precision float into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_half
     */
    fun putHalf(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.putHalfBind, segment, value)
    }

    /**
     * Puts a single-precision float into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_float
     */
    fun putFloat(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.putFloatBind, segment, value)
    }

    /**
     * Puts a double-precision float into the stream.
     *
     * Generated from Godot docs: StreamPeer.put_double
     */
    fun putDouble(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.putDoubleBind, segment, value)
    }

    /**
     * Puts a zero-terminated ASCII string into the stream prepended by a 32-bit unsigned integer
     * representing its size.
     *
     * Generated from Godot docs: StreamPeer.put_string
     */
    fun putString(value: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.putStringBind, segment, value)
    }

    /**
     * Puts a zero-terminated UTF-8 string into the stream prepended by a 32 bits unsigned integer
     * representing its size.
     *
     * Generated from Godot docs: StreamPeer.put_utf8_string
     */
    fun putUtf8String(value: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.putUtf8StringBind, segment, value)
    }

    /**
     * Puts a Variant into the stream. If `full_objects` is `true` encoding objects is allowed (and can
     * potentially include code). Internally, this uses the same encoding mechanism as the
     * `@GlobalScope.var_to_bytes` method.
     *
     * Generated from Godot docs: StreamPeer.put_var
     */
    fun putVar(value: Any?, fullObjects: Boolean = false) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantAndBoolArg(Binds.putVarBind, segment, value, fullObjects)
    }

    /**
     * Gets a signed byte from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_8
     */
    fun get8(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.get8Bind, segment)
    }

    /**
     * Gets an unsigned byte from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_u8
     */
    fun getU8(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getU8Bind, segment)
    }

    /**
     * Gets a signed 16-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_16
     */
    fun get16(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.get16Bind, segment)
    }

    /**
     * Gets an unsigned 16-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_u16
     */
    fun getU16(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getU16Bind, segment)
    }

    /**
     * Gets a signed 32-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_32
     */
    fun get32(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.get32Bind, segment)
    }

    /**
     * Gets an unsigned 32-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_u32
     */
    fun getU32(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getU32Bind, segment)
    }

    /**
     * Gets a signed 64-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_64
     */
    fun get64(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.get64Bind, segment)
    }

    /**
     * Gets an unsigned 64-bit value from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_u64
     */
    fun getU64(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getU64Bind, segment)
    }

    /**
     * Gets a half-precision float from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_half
     */
    fun getHalf(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHalfBind, segment)
    }

    /**
     * Gets a single-precision float from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_float
     */
    fun getFloat(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFloatBind, segment)
    }

    /**
     * Gets a double-precision float from the stream.
     *
     * Generated from Godot docs: StreamPeer.get_double
     */
    fun getDouble(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDoubleBind, segment)
    }

    /**
     * Gets an ASCII string with byte-length `bytes` from the stream. If `bytes` is negative (default)
     * the length will be read from the stream using the reverse process of `put_string`.
     *
     * Generated from Godot docs: StreamPeer.get_string
     */
    fun getString(bytes: Int = -1): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getStringBind, segment, bytes)
    }

    /**
     * Gets a UTF-8 string with byte-length `bytes` from the stream (this decodes the string sent as
     * UTF-8). If `bytes` is negative (default) the length will be read from the stream using the
     * reverse process of `put_utf8_string`.
     *
     * Generated from Godot docs: StreamPeer.get_utf8_string
     */
    fun getUtf8String(bytes: Int = -1): String {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetString(Binds.getUtf8StringBind, segment, bytes)
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
        checkOpen()
        return ObjectCalls.ptrcallWithBoolArgRetVariantScalar(Binds.getVarBind, segment, allowObjects)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StreamPeer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): StreamPeer? =
            if (handle.address() == 0L) null else RefCounted.owned(StreamPeer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): StreamPeer? =
            if (handle.address() == 0L) null else StreamPeer(GodotHandle(handle))
    }

    private object Binds {
        private const val PUT_DATA_HASH = 680677267L
        @JvmField
        val putDataBind =
            ObjectCalls.getMethodBind("StreamPeer", "put_data", PUT_DATA_HASH)

        private const val PUT_PARTIAL_DATA_HASH = 2934048347L
        @JvmField
        val putPartialDataBind =
            ObjectCalls.getMethodBind("StreamPeer", "put_partial_data", PUT_PARTIAL_DATA_HASH)

        private const val GET_DATA_HASH = 1171824711L
        @JvmField
        val getDataBind =
            ObjectCalls.getMethodBind("StreamPeer", "get_data", GET_DATA_HASH)

        private const val GET_PARTIAL_DATA_HASH = 1171824711L
        @JvmField
        val getPartialDataBind =
            ObjectCalls.getMethodBind("StreamPeer", "get_partial_data", GET_PARTIAL_DATA_HASH)

        private const val GET_AVAILABLE_BYTES_HASH = 3905245786L
        @JvmField
        val getAvailableBytesBind =
            ObjectCalls.getMethodBind("StreamPeer", "get_available_bytes", GET_AVAILABLE_BYTES_HASH)

        private const val SET_BIG_ENDIAN_HASH = 2586408642L
        @JvmField
        val setBigEndianBind =
            ObjectCalls.getMethodBind("StreamPeer", "set_big_endian", SET_BIG_ENDIAN_HASH)

        private const val IS_BIG_ENDIAN_ENABLED_HASH = 36873697L
        @JvmField
        val isBigEndianEnabledBind =
            ObjectCalls.getMethodBind("StreamPeer", "is_big_endian_enabled", IS_BIG_ENDIAN_ENABLED_HASH)

        private const val PUT_8_HASH = 1286410249L
        @JvmField
        val put8Bind =
            ObjectCalls.getMethodBind("StreamPeer", "put_8", PUT_8_HASH)

        private const val PUT_U8_HASH = 1286410249L
        @JvmField
        val putU8Bind =
            ObjectCalls.getMethodBind("StreamPeer", "put_u8", PUT_U8_HASH)

        private const val PUT_16_HASH = 1286410249L
        @JvmField
        val put16Bind =
            ObjectCalls.getMethodBind("StreamPeer", "put_16", PUT_16_HASH)

        private const val PUT_U16_HASH = 1286410249L
        @JvmField
        val putU16Bind =
            ObjectCalls.getMethodBind("StreamPeer", "put_u16", PUT_U16_HASH)

        private const val PUT_32_HASH = 1286410249L
        @JvmField
        val put32Bind =
            ObjectCalls.getMethodBind("StreamPeer", "put_32", PUT_32_HASH)

        private const val PUT_U32_HASH = 1286410249L
        @JvmField
        val putU32Bind =
            ObjectCalls.getMethodBind("StreamPeer", "put_u32", PUT_U32_HASH)

        private const val PUT_64_HASH = 1286410249L
        @JvmField
        val put64Bind =
            ObjectCalls.getMethodBind("StreamPeer", "put_64", PUT_64_HASH)

        private const val PUT_U64_HASH = 1286410249L
        @JvmField
        val putU64Bind =
            ObjectCalls.getMethodBind("StreamPeer", "put_u64", PUT_U64_HASH)

        private const val PUT_HALF_HASH = 373806689L
        @JvmField
        val putHalfBind =
            ObjectCalls.getMethodBind("StreamPeer", "put_half", PUT_HALF_HASH)

        private const val PUT_FLOAT_HASH = 373806689L
        @JvmField
        val putFloatBind =
            ObjectCalls.getMethodBind("StreamPeer", "put_float", PUT_FLOAT_HASH)

        private const val PUT_DOUBLE_HASH = 373806689L
        @JvmField
        val putDoubleBind =
            ObjectCalls.getMethodBind("StreamPeer", "put_double", PUT_DOUBLE_HASH)

        private const val PUT_STRING_HASH = 83702148L
        @JvmField
        val putStringBind =
            ObjectCalls.getMethodBind("StreamPeer", "put_string", PUT_STRING_HASH)

        private const val PUT_UTF8_STRING_HASH = 83702148L
        @JvmField
        val putUtf8StringBind =
            ObjectCalls.getMethodBind("StreamPeer", "put_utf8_string", PUT_UTF8_STRING_HASH)

        private const val PUT_VAR_HASH = 738511890L
        @JvmField
        val putVarBind =
            ObjectCalls.getMethodBind("StreamPeer", "put_var", PUT_VAR_HASH)

        private const val GET_8_HASH = 2455072627L
        @JvmField
        val get8Bind =
            ObjectCalls.getMethodBind("StreamPeer", "get_8", GET_8_HASH)

        private const val GET_U8_HASH = 2455072627L
        @JvmField
        val getU8Bind =
            ObjectCalls.getMethodBind("StreamPeer", "get_u8", GET_U8_HASH)

        private const val GET_16_HASH = 2455072627L
        @JvmField
        val get16Bind =
            ObjectCalls.getMethodBind("StreamPeer", "get_16", GET_16_HASH)

        private const val GET_U16_HASH = 2455072627L
        @JvmField
        val getU16Bind =
            ObjectCalls.getMethodBind("StreamPeer", "get_u16", GET_U16_HASH)

        private const val GET_32_HASH = 2455072627L
        @JvmField
        val get32Bind =
            ObjectCalls.getMethodBind("StreamPeer", "get_32", GET_32_HASH)

        private const val GET_U32_HASH = 2455072627L
        @JvmField
        val getU32Bind =
            ObjectCalls.getMethodBind("StreamPeer", "get_u32", GET_U32_HASH)

        private const val GET_64_HASH = 2455072627L
        @JvmField
        val get64Bind =
            ObjectCalls.getMethodBind("StreamPeer", "get_64", GET_64_HASH)

        private const val GET_U64_HASH = 2455072627L
        @JvmField
        val getU64Bind =
            ObjectCalls.getMethodBind("StreamPeer", "get_u64", GET_U64_HASH)

        private const val GET_HALF_HASH = 191475506L
        @JvmField
        val getHalfBind =
            ObjectCalls.getMethodBind("StreamPeer", "get_half", GET_HALF_HASH)

        private const val GET_FLOAT_HASH = 191475506L
        @JvmField
        val getFloatBind =
            ObjectCalls.getMethodBind("StreamPeer", "get_float", GET_FLOAT_HASH)

        private const val GET_DOUBLE_HASH = 191475506L
        @JvmField
        val getDoubleBind =
            ObjectCalls.getMethodBind("StreamPeer", "get_double", GET_DOUBLE_HASH)

        private const val GET_STRING_HASH = 2309358862L
        @JvmField
        val getStringBind =
            ObjectCalls.getMethodBind("StreamPeer", "get_string", GET_STRING_HASH)

        private const val GET_UTF8_STRING_HASH = 2309358862L
        @JvmField
        val getUtf8StringBind =
            ObjectCalls.getMethodBind("StreamPeer", "get_utf8_string", GET_UTF8_STRING_HASH)

        private const val GET_VAR_HASH = 3442865206L
        @JvmField
        val getVarBind =
            ObjectCalls.getMethodBind("StreamPeer", "get_var", GET_VAR_HASH)
    }
}
