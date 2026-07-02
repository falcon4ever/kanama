package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Plugin for adding custom parsers to extract strings that are to be translated from custom files
 * (.csv, .json etc.).
 *
 * Generated from Godot docs: EditorTranslationParserPlugin
 */
class EditorTranslationParserPlugin(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorTranslationParserPlugin? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorTranslationParserPlugin? =
            if (handle.address() == 0L) null else EditorTranslationParserPlugin(handle)

        // No MethodBinds emitted yet.
    }
}
