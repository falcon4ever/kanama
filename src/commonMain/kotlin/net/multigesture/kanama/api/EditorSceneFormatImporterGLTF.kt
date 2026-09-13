package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorSceneFormatImporterGLTF
 */
class EditorSceneFormatImporterGLTF(handle: GodotHandle) : EditorSceneFormatImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): EditorSceneFormatImporterGLTF? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): EditorSceneFormatImporterGLTF? =
            if (handle.address() == 0L) null else EditorSceneFormatImporterGLTF(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
