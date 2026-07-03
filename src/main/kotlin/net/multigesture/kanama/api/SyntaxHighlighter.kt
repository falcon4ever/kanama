package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for syntax highlighters. Provides syntax highlighting data to a `TextEdit`.
 *
 * Generated from Godot docs: SyntaxHighlighter
 */
open class SyntaxHighlighter(handle: MemorySegment) : Resource(handle) {
    /**
     * Returns the syntax highlighting data for the line at index `line`. If the line is not cached,
     * calls `_get_line_syntax_highlighting` first to calculate the data. Each entry is a column number
     * containing a nested `Dictionary`. The column number denotes the start of a region, the region
     * will end if another region is found, or at the end of the line. The nested `Dictionary` contains
     * the data for that region. Currently only the key `"color"` is supported.
     *
     * Generated from Godot docs: SyntaxHighlighter.get_line_syntax_highlighting
     */
    fun getLineSyntaxHighlighting(line: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntArgRetDictionary(getLineSyntaxHighlightingBind, handle, line)
    }

    /**
     * Clears then updates the `SyntaxHighlighter` caches. Override `_update_cache` for a callback.
     * Note: This is called automatically when the associated `TextEdit` node, updates its own cache.
     *
     * Generated from Godot docs: SyntaxHighlighter.update_cache
     */
    fun updateCache() {
        ObjectCalls.ptrcallNoArgs(updateCacheBind, handle)
    }

    /**
     * Clears all cached syntax highlighting data. Then calls overridable method
     * `_clear_highlighting_cache`.
     *
     * Generated from Godot docs: SyntaxHighlighter.clear_highlighting_cache
     */
    fun clearHighlightingCache() {
        ObjectCalls.ptrcallNoArgs(clearHighlightingCacheBind, handle)
    }

    /**
     * Returns the associated `TextEdit` node.
     *
     * Generated from Godot docs: SyntaxHighlighter.get_text_edit
     */
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
