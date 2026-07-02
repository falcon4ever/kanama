package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for `SyntaxHighlighter` used by the `ScriptEditor`.
 *
 * Generated from Godot docs: EditorSyntaxHighlighter
 */
open class EditorSyntaxHighlighter(handle: MemorySegment) : SyntaxHighlighter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorSyntaxHighlighter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorSyntaxHighlighter? =
            if (handle.address() == 0L) null else EditorSyntaxHighlighter(handle)

        // No MethodBinds emitted yet.
    }
}
