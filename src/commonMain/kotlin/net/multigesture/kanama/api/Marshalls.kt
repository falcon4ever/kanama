package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Data transformation (marshaling) and encoding helpers.
 *
 * Generated from Godot docs: Marshalls
 */
object Marshalls {
    private inline val singleton: RawSegment
        get() = Binds.singleton

    /**
     * Returns a Base64-encoded string of the `Variant` `variant`. If `full_objects` is `true`,
     * encoding objects is allowed (and can potentially include code). Internally, this uses the same
     * encoding mechanism as the `@GlobalScope.var_to_bytes` method.
     *
     * Generated from Godot docs: Marshalls.variant_to_base64
     */
    @JvmStatic
    fun variantToBase64(variant: Any?, fullObjects: Boolean = false): String {
        return ObjectCalls.ptrcallWithVariantAndBoolArgRetString(Binds.variantToBase64Bind, singleton, variant, fullObjects)
    }

    /**
     * Returns a decoded `Variant` corresponding to the Base64-encoded string `base64_str`. If
     * `allow_objects` is `true`, decoding objects is allowed. Internally, this uses the same decoding
     * mechanism as the `@GlobalScope.bytes_to_var` method. Warning: Deserialized objects can contain
     * code which gets executed. Do not use this option if the serialized object comes from untrusted
     * sources to avoid potential security threats such as remote code execution.
     *
     * Generated from Godot docs: Marshalls.base64_to_variant
     */
    @JvmStatic
    fun base64ToVariant(base64Str: String, allowObjects: Boolean = false): Any? {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetVariantScalar(Binds.base64ToVariantBind, singleton, base64Str, allowObjects)
    }

    /**
     * Returns a Base64-encoded string of a given `PackedByteArray`.
     *
     * Generated from Godot docs: Marshalls.raw_to_base64
     */
    @JvmStatic
    fun rawToBase64(array: ByteArray): String {
        return ObjectCalls.ptrcallWithByteArrayArgRetString(Binds.rawToBase64Bind, singleton, array)
    }

    /**
     * Returns a decoded `PackedByteArray` corresponding to the Base64-encoded string `base64_str`.
     *
     * Generated from Godot docs: Marshalls.base64_to_raw
     */
    @JvmStatic
    fun base64ToRaw(base64Str: String): ByteArray {
        return ObjectCalls.ptrcallWithStringArgRetByteArray(Binds.base64ToRawBind, singleton, base64Str)
    }

    /**
     * Returns a Base64-encoded string of the UTF-8 string `utf8_str`.
     *
     * Generated from Godot docs: Marshalls.utf8_to_base64
     */
    @JvmStatic
    fun utf8ToBase64(utf8Str: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(Binds.utf8ToBase64Bind, singleton, utf8Str)
    }

    /**
     * Returns a decoded string corresponding to the Base64-encoded string `base64_str`.
     *
     * Generated from Godot docs: Marshalls.base64_to_utf8
     */
    @JvmStatic
    fun base64ToUtf8(base64Str: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(Binds.base64ToUtf8Bind, singleton, base64Str)
    }

    @JvmStatic
    fun fromHandle(handle: GodotHandle): Marshalls? =
        wrap(handle.segment)

    internal fun wrap(handle: RawSegment): Marshalls? =
        if (handle.address() == 0L) null else this

    private object Binds {
        @JvmField
        val singleton = ObjectCalls.getSingleton("Marshalls")

        private const val VARIANT_TO_BASE64_HASH = 3876248563L
        @JvmField
        val variantToBase64Bind =
            ObjectCalls.getMethodBind("Marshalls", "variant_to_base64", VARIANT_TO_BASE64_HASH)

        private const val BASE64_TO_VARIANT_HASH = 218087648L
        @JvmField
        val base64ToVariantBind =
            ObjectCalls.getMethodBind("Marshalls", "base64_to_variant", BASE64_TO_VARIANT_HASH)

        private const val RAW_TO_BASE64_HASH = 3999417757L
        @JvmField
        val rawToBase64Bind =
            ObjectCalls.getMethodBind("Marshalls", "raw_to_base64", RAW_TO_BASE64_HASH)

        private const val BASE64_TO_RAW_HASH = 659035735L
        @JvmField
        val base64ToRawBind =
            ObjectCalls.getMethodBind("Marshalls", "base64_to_raw", BASE64_TO_RAW_HASH)

        private const val UTF8_TO_BASE64_HASH = 1703090593L
        @JvmField
        val utf8ToBase64Bind =
            ObjectCalls.getMethodBind("Marshalls", "utf8_to_base64", UTF8_TO_BASE64_HASH)

        private const val BASE64_TO_UTF8_HASH = 1703090593L
        @JvmField
        val base64ToUtf8Bind =
            ObjectCalls.getMethodBind("Marshalls", "base64_to_utf8", BASE64_TO_UTF8_HASH)
    }
}
