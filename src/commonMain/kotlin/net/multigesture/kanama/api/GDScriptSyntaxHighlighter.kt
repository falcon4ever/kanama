package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A GDScript syntax highlighter that can be used with `TextEdit` and `CodeEdit` nodes.
 *
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

        /** A new GDScriptSyntaxHighlighter, GDScript's `GDScriptSyntaxHighlighter.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): GDScriptSyntaxHighlighter =
            RefCounted.owned(GDScriptSyntaxHighlighter(GodotHandle(requireConstructed(ObjectCalls.constructObject("GDScriptSyntaxHighlighter"), "GDScriptSyntaxHighlighter"))))
    }
}
