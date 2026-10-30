package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorTranslationParserPlugin? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorTranslationParserPlugin(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorTranslationParserPlugin? =
            if (handle.address() == 0L) null else EditorTranslationParserPlugin(GodotHandle(handle))

        /** A new EditorTranslationParserPlugin, GDScript's `EditorTranslationParserPlugin.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): EditorTranslationParserPlugin =
            RefCounted.owned(EditorTranslationParserPlugin(GodotHandle(requireConstructed(ObjectCalls.constructObject("EditorTranslationParserPlugin"), "EditorTranslationParserPlugin"))))
    }
}
