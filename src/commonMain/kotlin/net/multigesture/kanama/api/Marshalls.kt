package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Data transformation (marshaling) and encoding helpers.
 *
 * Generated from Godot docs: Marshalls
 */
object Marshalls {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("Marshalls")
    }

    /**
     * Returns a Base64-encoded string of the UTF-8 string `utf8_str`.
     *
     * Generated from Godot docs: Marshalls.utf8_to_base64
     */
    @JvmStatic
    fun utf8ToBase64(utf8Str: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(utf8ToBase64Bind, singleton, utf8Str)
    }

    /**
     * Returns a decoded string corresponding to the Base64-encoded string `base64_str`.
     *
     * Generated from Godot docs: Marshalls.base64_to_utf8
     */
    @JvmStatic
    fun base64ToUtf8(base64Str: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(base64ToUtf8Bind, singleton, base64Str)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): Marshalls? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): Marshalls? =
        if (handle.address() == 0L) null else this

    private const val UTF8_TO_BASE64_HASH = 1703090593L
    private val utf8ToBase64Bind by lazy {
        ObjectCalls.getMethodBind("Marshalls", "utf8_to_base64", UTF8_TO_BASE64_HASH)
    }

    private const val BASE64_TO_UTF8_HASH = 1703090593L
    private val base64ToUtf8Bind by lazy {
        ObjectCalls.getMethodBind("Marshalls", "base64_to_utf8", BASE64_TO_UTF8_HASH)
    }
}
