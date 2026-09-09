package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: SyntaxHighlighter
 */
open class SyntaxHighlighter(handle: MemorySegment) : Resource(handle) {
    fun updateCache() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(updateCacheBind, handle)
    }

    fun clearHighlightingCache() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearHighlightingCacheBind, handle)
    }

    fun getTextEdit(): TextEdit? {
        checkOpen()
        return TextEdit.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextEditBind, handle))
    }

    companion object {
        fun fromHandle(handle: MemorySegment): SyntaxHighlighter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SyntaxHighlighter? =
            if (handle.address() == 0L) null else SyntaxHighlighter(handle)

        private const val UPDATE_CACHE_HASH = 3218959716L
        private val updateCacheBind by lazy {
            ObjectCalls.getMethodBind("SyntaxHighlighter", "update_cache", UPDATE_CACHE_HASH)
        }

        private const val CLEAR_HIGHLIGHTING_CACHE_HASH = 3218959716L
        private val clearHighlightingCacheBind by lazy {
            ObjectCalls.getMethodBind("SyntaxHighlighter", "clear_highlighting_cache", CLEAR_HIGHLIGHTING_CACHE_HASH)
        }

        private const val GET_TEXT_EDIT_HASH = 1893027089L
        private val getTextEditBind by lazy {
            ObjectCalls.getMethodBind("SyntaxHighlighter", "get_text_edit", GET_TEXT_EDIT_HASH)
        }
    }
}
