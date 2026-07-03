package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * An X509 certificate (e.g. for TLS).
 *
 * Generated from Godot docs: X509Certificate
 */
class X509Certificate(handle: MemorySegment) : Resource(handle) {
    /**
     * Saves a certificate to the given `path` (should be a "*.crt" file).
     *
     * Generated from Godot docs: X509Certificate.save
     */
    fun save(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(saveBind, handle, path)
    }

    /**
     * Loads a certificate from `path` ("*.crt" file).
     *
     * Generated from Godot docs: X509Certificate.load
     */
    fun load(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(loadBind, handle, path)
    }

    /**
     * Returns a string representation of the certificate, or an empty string if the certificate is
     * invalid.
     *
     * Generated from Godot docs: X509Certificate.save_to_string
     */
    fun saveToString(): String {
        return ObjectCalls.ptrcallNoArgsRetString(saveToStringBind, handle)
    }

    /**
     * Loads a certificate from the given `string`.
     *
     * Generated from Godot docs: X509Certificate.load_from_string
     */
    fun loadFromString(string: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(loadFromStringBind, handle, string)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): X509Certificate? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): X509Certificate? =
            if (handle.address() == 0L) null else X509Certificate(handle)

        private const val SAVE_HASH = 166001499L
        private val saveBind by lazy {
            ObjectCalls.getMethodBind("X509Certificate", "save", SAVE_HASH)
        }

        private const val LOAD_HASH = 166001499L
        private val loadBind by lazy {
            ObjectCalls.getMethodBind("X509Certificate", "load", LOAD_HASH)
        }

        private const val SAVE_TO_STRING_HASH = 2841200299L
        private val saveToStringBind by lazy {
            ObjectCalls.getMethodBind("X509Certificate", "save_to_string", SAVE_TO_STRING_HASH)
        }

        private const val LOAD_FROM_STRING_HASH = 166001499L
        private val loadFromStringBind by lazy {
            ObjectCalls.getMethodBind("X509Certificate", "load_from_string", LOAD_FROM_STRING_HASH)
        }
    }
}
