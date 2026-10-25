package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

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

        /** A new EditorSceneFormatImporterGLTF, GDScript's `EditorSceneFormatImporterGLTF.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): EditorSceneFormatImporterGLTF =
            RefCounted.owned(EditorSceneFormatImporterGLTF(GodotHandle(requireConstructed(ObjectCalls.constructObject("EditorSceneFormatImporterGLTF"), "EditorSceneFormatImporterGLTF"))))
    }
}
