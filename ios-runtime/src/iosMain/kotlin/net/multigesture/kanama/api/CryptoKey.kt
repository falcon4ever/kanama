package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: CryptoKey
 */
class CryptoKey(handle: MemorySegment) : Resource(handle) {
    fun save(path: String, publicOnly: Boolean = false): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetLong(saveBind, handle, path, publicOnly)
    }

    fun load(path: String, publicOnly: Boolean = false): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetLong(loadBind, handle, path, publicOnly)
    }

    fun isPublicOnly(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isPublicOnlyBind, handle)
    }

    fun loadFromString(stringKey: String, publicOnly: Boolean = false): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndBoolArgRetLong(loadFromStringBind, handle, stringKey, publicOnly)
    }

    companion object {
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

        private const val LOAD_FROM_STRING_HASH = 885841341L
        private val loadFromStringBind by lazy {
            ObjectCalls.getMethodBind("CryptoKey", "load_from_string", LOAD_FROM_STRING_HASH)
        }
    }
}
