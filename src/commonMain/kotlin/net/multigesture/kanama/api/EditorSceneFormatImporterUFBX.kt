package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
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
    }
}
