package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: EditorSceneFormatImporterGLTF
 */
class EditorSceneFormatImporterGLTF(handle: GodotHandle) : EditorSceneFormatImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorSceneFormatImporterGLTF? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorSceneFormatImporterGLTF? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorSceneFormatImporterGLTF(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorSceneFormatImporterGLTF? =
            if (handle.address() == 0L) null else EditorSceneFormatImporterGLTF(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
