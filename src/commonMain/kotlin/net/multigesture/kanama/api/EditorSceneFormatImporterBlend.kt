package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: EditorSceneFormatImporterBlend
 */
class EditorSceneFormatImporterBlend(handle: GodotHandle) : EditorSceneFormatImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorSceneFormatImporterBlend? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorSceneFormatImporterBlend? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorSceneFormatImporterBlend(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorSceneFormatImporterBlend? =
            if (handle.address() == 0L) null else EditorSceneFormatImporterBlend(GodotHandle(handle))
    }
}
