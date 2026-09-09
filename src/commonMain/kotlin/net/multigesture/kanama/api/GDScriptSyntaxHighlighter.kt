package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: GDScriptSyntaxHighlighter
 */
class GDScriptSyntaxHighlighter(handle: MemorySegment) : EditorSyntaxHighlighter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): GDScriptSyntaxHighlighter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): GDScriptSyntaxHighlighter? =
            if (handle.address() == 0L) null else GDScriptSyntaxHighlighter(handle)

        // No MethodBinds emitted yet.
    }
}
