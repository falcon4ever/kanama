package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorSceneFormatImporterGLTF
 */
class EditorSceneFormatImporterGLTF(handle: MemorySegment) : EditorSceneFormatImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorSceneFormatImporterGLTF? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorSceneFormatImporterGLTF? =
            if (handle.address() == 0L) null else EditorSceneFormatImporterGLTF(handle)

        // No MethodBinds emitted yet.
    }
}
