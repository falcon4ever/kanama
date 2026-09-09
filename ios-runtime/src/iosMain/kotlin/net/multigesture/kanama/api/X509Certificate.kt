package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: X509Certificate
 */
class X509Certificate(handle: MemorySegment) : Resource(handle) {
    fun save(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(saveBind, handle, path)
    }

    fun load(path: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(loadBind, handle, path)
    }

    fun saveToString(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(saveToStringBind, handle)
    }

    fun loadFromString(string: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(loadFromStringBind, handle, string)
    }

    companion object {
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
