package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports scenes from third-parties' 3D files.
 *
 * Generated from Godot docs: EditorSceneFormatImporter
 */
open class EditorSceneFormatImporter(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        const val IMPORT_SCENE: Long = 1L
        const val IMPORT_ANIMATION: Long = 2L
        const val IMPORT_FAIL_ON_MISSING_DEPENDENCIES: Long = 4L
        const val IMPORT_GENERATE_TANGENT_ARRAYS: Long = 8L
        const val IMPORT_USE_NAMED_SKIN_BINDS: Long = 16L
        const val IMPORT_DISCARD_MESHES_AND_MATERIALS: Long = 32L
        const val IMPORT_FORCE_DISABLE_MESH_COMPRESSION: Long = 64L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): EditorSceneFormatImporter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): EditorSceneFormatImporter? =
            if (handle.address() == 0L) null else EditorSceneFormatImporter(handle)

        // No MethodBinds emitted yet.
    }
}
