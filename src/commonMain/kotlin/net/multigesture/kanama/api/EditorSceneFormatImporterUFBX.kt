package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Import FBX files using the ufbx library.
 *
 * Generated from Godot docs: EditorSceneFormatImporterUFBX
 */
class EditorSceneFormatImporterUFBX(handle: GodotHandle) : EditorSceneFormatImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorSceneFormatImporterUFBX? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorSceneFormatImporterUFBX? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorSceneFormatImporterUFBX(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorSceneFormatImporterUFBX? =
            if (handle.address() == 0L) null else EditorSceneFormatImporterUFBX(GodotHandle(handle))

        /** A new EditorSceneFormatImporterUFBX, GDScript's `EditorSceneFormatImporterUFBX.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): EditorSceneFormatImporterUFBX =
            RefCounted.owned(EditorSceneFormatImporterUFBX(GodotHandle(ObjectCalls.constructObject("EditorSceneFormatImporterUFBX"))))
    }
}
