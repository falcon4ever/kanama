package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Importer for the `.fbx` scene file format.
 *
 * Generated from Godot docs: EditorSceneFormatImporterFBX2GLTF
 */
class EditorSceneFormatImporterFBX2GLTF(handle: GodotHandle) : EditorSceneFormatImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorSceneFormatImporterFBX2GLTF? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): EditorSceneFormatImporterFBX2GLTF? =
            if (handle.address() == 0L) null else RefCounted.owned(EditorSceneFormatImporterFBX2GLTF(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): EditorSceneFormatImporterFBX2GLTF? =
            if (handle.address() == 0L) null else EditorSceneFormatImporterFBX2GLTF(GodotHandle(handle))

        /** A new EditorSceneFormatImporterFBX2GLTF, GDScript's `EditorSceneFormatImporterFBX2GLTF.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): EditorSceneFormatImporterFBX2GLTF =
            RefCounted.owned(EditorSceneFormatImporterFBX2GLTF(GodotHandle(ObjectCalls.constructObject("EditorSceneFormatImporterFBX2GLTF"))))
    }
}
