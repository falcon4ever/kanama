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
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorSceneFormatImporterBlend? =
            if (handle.address() == 0L) null else EditorSceneFormatImporterBlend(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
