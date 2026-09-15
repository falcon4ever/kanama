package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: EditorSceneFormatImporterFBX2GLTF
 */
class EditorSceneFormatImporterFBX2GLTF(handle: GodotHandle) : EditorSceneFormatImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorSceneFormatImporterFBX2GLTF? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): EditorSceneFormatImporterFBX2GLTF? =
            if (handle.address() == 0L) null else EditorSceneFormatImporterFBX2GLTF(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
