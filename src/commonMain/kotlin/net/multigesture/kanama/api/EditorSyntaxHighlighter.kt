package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

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

        /** A new EditorSyntaxHighlighter, GDScript's `EditorSyntaxHighlighter.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): EditorSyntaxHighlighter =
            RefCounted.owned(EditorSyntaxHighlighter(GodotHandle(requireConstructed(ObjectCalls.constructObject("EditorSyntaxHighlighter"), "EditorSyntaxHighlighter"))))
    }
}
