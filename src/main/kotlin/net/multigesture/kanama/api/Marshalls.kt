package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Data transformation (marshaling) and encoding helpers.
 *
 * Generated from Godot docs: Marshalls
 */
object Marshalls {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("Marshalls")
    }

    @JvmStatic
    fun variantToBase64(variant: Any?, fullObjects: Boolean = false): String {
        return ObjectCalls.ptrcallWithVariantAndBoolArgRetString(variantToBase64Bind, singleton, variant, fullObjects)
    }

    @JvmStatic
    fun base64ToVariant(base64Str: String, allowObjects: Boolean = false): Any? {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetVariantScalar(base64ToVariantBind, singleton, base64Str, allowObjects)
    }

    @JvmStatic
    fun rawToBase64(array: ByteArray): String {
        return ObjectCalls.ptrcallWithByteArrayArgRetString(rawToBase64Bind, singleton, array)
    }

    @JvmStatic
    fun base64ToRaw(base64Str: String): ByteArray {
        return ObjectCalls.ptrcallWithStringArgRetByteArray(base64ToRawBind, singleton, base64Str)
    }

    @JvmStatic
    fun utf8ToBase64(utf8Str: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(utf8ToBase64Bind, singleton, utf8Str)
    }

    @JvmStatic
    fun base64ToUtf8(base64Str: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(base64ToUtf8Bind, singleton, base64Str)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): Marshalls? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): Marshalls? =
        if (handle.address() == 0L) null else this

    private const val VARIANT_TO_BASE64_HASH = 3876248563L
    private val variantToBase64Bind by lazy {
        ObjectCalls.getMethodBind("Marshalls", "variant_to_base64", VARIANT_TO_BASE64_HASH)
    }

    private const val BASE64_TO_VARIANT_HASH = 218087648L
    private val base64ToVariantBind by lazy {
        ObjectCalls.getMethodBind("Marshalls", "base64_to_variant", BASE64_TO_VARIANT_HASH)
    }

    private const val RAW_TO_BASE64_HASH = 3999417757L
    private val rawToBase64Bind by lazy {
        ObjectCalls.getMethodBind("Marshalls", "raw_to_base64", RAW_TO_BASE64_HASH)
    }

    private const val BASE64_TO_RAW_HASH = 659035735L
    private val base64ToRawBind by lazy {
        ObjectCalls.getMethodBind("Marshalls", "base64_to_raw", BASE64_TO_RAW_HASH)
    }

    private const val UTF8_TO_BASE64_HASH = 1703090593L
    private val utf8ToBase64Bind by lazy {
        ObjectCalls.getMethodBind("Marshalls", "utf8_to_base64", UTF8_TO_BASE64_HASH)
    }

    private const val BASE64_TO_UTF8_HASH = 1703090593L
    private val base64ToUtf8Bind by lazy {
        ObjectCalls.getMethodBind("Marshalls", "base64_to_utf8", BASE64_TO_UTF8_HASH)
    }
}
