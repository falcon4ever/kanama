package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An X509 certificate (e.g. for TLS).
 *
 * Generated from Godot docs: X509Certificate
 */
class X509Certificate(handle: GodotHandle) : Resource(handle) {
    /**
     * Saves a certificate to the given `path` (should be a "*.crt" file).
     *
     * Generated from Godot docs: X509Certificate.save
     */
    fun save(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.saveBind, segment, path))
    }

    /**
     * Loads a certificate from `path` ("*.crt" file).
     *
     * Generated from Godot docs: X509Certificate.load
     */
    fun load(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.loadBind, segment, path))
    }

    /**
     * Returns a string representation of the certificate, or an empty string if the certificate is
     * invalid.
     *
     * Generated from Godot docs: X509Certificate.save_to_string
     */
    fun saveToString(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.saveToStringBind, segment)
    }

    /**
     * Loads a certificate from the given `string`.
     *
     * Generated from Godot docs: X509Certificate.load_from_string
     */
    fun loadFromString(string: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.loadFromStringBind, segment, string))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): X509Certificate? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): X509Certificate? =
            if (handle.address() == 0L) null else RefCounted.owned(X509Certificate(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): X509Certificate? =
            if (handle.address() == 0L) null else X509Certificate(GodotHandle(handle))
    }

    private object Binds {
        private const val SAVE_HASH = 166001499L
        @JvmField
        val saveBind =
            ObjectCalls.getMethodBind("X509Certificate", "save", SAVE_HASH)

        private const val LOAD_HASH = 166001499L
        @JvmField
        val loadBind =
            ObjectCalls.getMethodBind("X509Certificate", "load", LOAD_HASH)

        private const val SAVE_TO_STRING_HASH = 2841200299L
        @JvmField
        val saveToStringBind =
            ObjectCalls.getMethodBind("X509Certificate", "save_to_string", SAVE_TO_STRING_HASH)

        private const val LOAD_FROM_STRING_HASH = 166001499L
        @JvmField
        val loadFromStringBind =
            ObjectCalls.getMethodBind("X509Certificate", "load_from_string", LOAD_FROM_STRING_HASH)
    }
}
