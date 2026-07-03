package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A cryptographic key (RSA or elliptic-curve).
 *
 * Generated from Godot docs: CryptoKey
 */
class CryptoKey(handle: MemorySegment) : Resource(handle) {
    /**
     * Saves a key to the given `path`. If `public_only` is `true`, only the public key will be saved.
     * Note: `path` should be a "*.pub" file if `public_only` is `true`, a "*.key" file otherwise.
     *
     * Generated from Godot docs: CryptoKey.save
     */
    fun save(path: String, publicOnly: Boolean = false): Long {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetLong(saveBind, handle, path, publicOnly)
    }

    /**
     * Loads a key from `path`. If `public_only` is `true`, only the public key will be loaded. Note:
     * `path` should be a "*.pub" file if `public_only` is `true`, a "*.key" file otherwise.
     *
     * Generated from Godot docs: CryptoKey.load
     */
    fun load(path: String, publicOnly: Boolean = false): Long {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetLong(loadBind, handle, path, publicOnly)
    }

    /**
     * Returns `true` if this CryptoKey only has the public part, and not the private one.
     *
     * Generated from Godot docs: CryptoKey.is_public_only
     */
    fun isPublicOnly(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPublicOnlyBind, handle)
    }

    /**
     * Returns a string containing the key in PEM format. If `public_only` is `true`, only the public
     * key will be included.
     *
     * Generated from Godot docs: CryptoKey.save_to_string
     */
    fun saveToString(publicOnly: Boolean = false): String {
        return ObjectCalls.ptrcallWithBoolArgRetString(saveToStringBind, handle, publicOnly)
    }

    /**
     * Loads a key from the given `string_key`. If `public_only` is `true`, only the public key will be
     * loaded.
     *
     * Generated from Godot docs: CryptoKey.load_from_string
     */
    fun loadFromString(stringKey: String, publicOnly: Boolean = false): Long {
        return ObjectCalls.ptrcallWithStringAndBoolArgRetLong(loadFromStringBind, handle, stringKey, publicOnly)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CryptoKey? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CryptoKey? =
            if (handle.address() == 0L) null else CryptoKey(handle)

        private const val SAVE_HASH = 885841341L
        private val saveBind by lazy {
            ObjectCalls.getMethodBind("CryptoKey", "save", SAVE_HASH)
        }

        private const val LOAD_HASH = 885841341L
        private val loadBind by lazy {
            ObjectCalls.getMethodBind("CryptoKey", "load", LOAD_HASH)
        }

        private const val IS_PUBLIC_ONLY_HASH = 36873697L
        private val isPublicOnlyBind by lazy {
            ObjectCalls.getMethodBind("CryptoKey", "is_public_only", IS_PUBLIC_ONLY_HASH)
        }

        private const val SAVE_TO_STRING_HASH = 32795936L
        private val saveToStringBind by lazy {
            ObjectCalls.getMethodBind("CryptoKey", "save_to_string", SAVE_TO_STRING_HASH)
        }

        private const val LOAD_FROM_STRING_HASH = 885841341L
        private val loadFromStringBind by lazy {
            ObjectCalls.getMethodBind("CryptoKey", "load_from_string", LOAD_FROM_STRING_HASH)
        }
    }
}
