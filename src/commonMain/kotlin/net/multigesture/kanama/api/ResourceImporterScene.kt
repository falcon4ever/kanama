package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports a glTF, FBX, COLLADA, or Blender 3D scene.
 *
 * Generated from Godot docs: ResourceImporterScene
 */
class ResourceImporterScene(handle: MemorySegment) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourceImporterScene? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourceImporterScene? =
            if (handle.address() == 0L) null else ResourceImporterScene(handle)

        // No MethodBinds emitted yet.
    }
}
