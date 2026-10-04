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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorSyntaxHighlighter? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorSyntaxHighlighter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorSyntaxHighlighter? =
            if (handle.address() == 0L) null else EditorSyntaxHighlighter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
