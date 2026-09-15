package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Plugin for adding custom parsers to extract strings that are to be translated from custom files
 * (.csv, .json etc.).
 *
 * Generated from Godot docs: EditorTranslationParserPlugin
 */
class EditorTranslationParserPlugin(handle: GodotHandle) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorTranslationParserPlugin? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorTranslationParserPlugin? =
            if (handle.address() == 0L) null else EditorTranslationParserPlugin(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
