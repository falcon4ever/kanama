package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for `SyntaxHighlighter` used by the `ScriptEditor`.
 *
 * Generated from Godot docs: EditorSyntaxHighlighter
 */
open class EditorSyntaxHighlighter(handle: GodotHandle) : SyntaxHighlighter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorSyntaxHighlighter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorSyntaxHighlighter? =
            if (handle.address() == 0L) null else EditorSyntaxHighlighter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
