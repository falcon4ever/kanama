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

    fun putData(data: ByteArray): Long {
        return ObjectCalls.ptrcallWithByteArrayArgRetLong(putDataBind, handle, data)
    }

    fun putPartialData(data: ByteArray): List<Any?> {
        return ObjectCalls.ptrcallWithByteArrayArgRetArray(putPartialDataBind, handle, data)
    }

    fun getData(bytes: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntArgRetArray(getDataBind, handle, bytes)
    }

    fun getPartialData(bytes: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntArgRetArray(getPartialDataBind, handle, bytes)
    }

    fun getAvailableBytes(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAvailableBytesBind, handle)
    }

    fun setBigEndian(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBigEndianBind, handle, enable)
    }

    fun isBigEndianEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBigEndianEnabledBind, handle)
    }

    fun put8(value: Int) {
        ObjectCalls.ptrcallWithIntArg(put8Bind, handle, value)
    }

    fun putU8(value: Int) {
        ObjectCalls.ptrcallWithIntArg(putU8Bind, handle, value)
    }

    fun put16(value: Int) {
        ObjectCalls.ptrcallWithIntArg(put16Bind, handle, value)
    }

    fun putU16(value: Int) {
        ObjectCalls.ptrcallWithIntArg(putU16Bind, handle, value)
    }

    fun put32(value: Int) {
        ObjectCalls.ptrcallWithIntArg(put32Bind, handle, value)
    }

    fun putU32(value: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(putU32Bind, handle, value)
    }

    fun put64(value: Long) {
        ObjectCalls.ptrcallWithLongArg(put64Bind, handle, value)
    }

    fun putU64(value: Long) {
        ObjectCalls.ptrcallWithLongArg(putU64Bind, handle, value)
    }

    fun putHalf(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(putHalfBind, handle, value)
    }

    fun putFloat(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(putFloatBind, handle, value)
    }

    fun putDouble(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(putDoubleBind, handle, value)
    }

    fun putString(value: String) {
        ObjectCalls.ptrcallWithStringArg(putStringBind, handle, value)
    }

    fun putUtf8String(value: String) {
        ObjectCalls.ptrcallWithStringArg(putUtf8StringBind, handle, value)
    }

    fun putVar(value: Any?, fullObjects: Boolean = false) {
        ObjectCalls.ptrcallWithVariantAndBoolArg(putVarBind, handle, value, fullObjects)
    }

    fun get8(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(get8Bind, handle)
    }

    fun getU8(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getU8Bind, handle)
    }

    fun get16(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(get16Bind, handle)
    }

    fun getU16(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getU16Bind, handle)
    }

    fun get32(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(get32Bind, handle)
    }

    fun getU32(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getU32Bind, handle)
    }

    fun get64(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(get64Bind, handle)
    }

    fun getU64(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getU64Bind, handle)
    }

    fun getHalf(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHalfBind, handle)
    }

    fun getFloat(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFloatBind, handle)
    }

    fun getDouble(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDoubleBind, handle)
    }

    fun getString(bytes: Int = -1): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getStringBind, handle, bytes)
    }

    fun getUtf8String(bytes: Int = -1): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getUtf8StringBind, handle, bytes)
    }

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
