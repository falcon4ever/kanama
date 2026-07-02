package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for syntax highlighters. Provides syntax highlighting data to a `TextEdit`.
 *
 * Generated from Godot docs: SyntaxHighlighter
 */
open class SyntaxHighlighter(handle: MemorySegment) : Resource(handle) {
    fun getLineSyntaxHighlighting(line: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntArgRetDictionary(getLineSyntaxHighlightingBind, handle, line)
    }

    fun updateCache() {
        ObjectCalls.ptrcallNoArgs(updateCacheBind, handle)
    }

    fun clearHighlightingCache() {
        ObjectCalls.ptrcallNoArgs(clearHighlightingCacheBind, handle)
    }

    fun getTextEdit(): TextEdit? {
        return TextEdit.wrap(ObjectCalls.ptrcallNoArgsRetObject(getTextEditBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SyntaxHighlighter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SyntaxHighlighter? =
            if (handle.address() == 0L) null else SyntaxHighlighter(handle)

        private const val GET_LINE_SYNTAX_HIGHLIGHTING_HASH = 3554694381L
        private val getLineSyntaxHighlightingBind by lazy {
            ObjectCalls.getMethodBind("SyntaxHighlighter", "get_line_syntax_highlighting", GET_LINE_SYNTAX_HIGHLIGHTING_HASH)
        }

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
