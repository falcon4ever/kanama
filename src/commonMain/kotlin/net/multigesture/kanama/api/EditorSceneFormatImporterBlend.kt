package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: EditorSceneFormatImporterBlend
 */
class EditorSceneFormatImporterBlend(handle: MemorySegment) : EditorSceneFormatImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorSceneFormatImporterBlend? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorSceneFormatImporterBlend? =
            if (handle.address() == 0L) null else EditorSceneFormatImporterBlend(handle)

        // No MethodBinds emitted yet.
    }
}
