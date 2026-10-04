package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: GDScriptSyntaxHighlighter
 */
class GDScriptSyntaxHighlighter(handle: GodotHandle) : EditorSyntaxHighlighter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): GDScriptSyntaxHighlighter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): GDScriptSyntaxHighlighter? =
            if (handle.address() == 0L) null else RefCounted.owned(GDScriptSyntaxHighlighter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): GDScriptSyntaxHighlighter? =
            if (handle.address() == 0L) null else GDScriptSyntaxHighlighter(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
