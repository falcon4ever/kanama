package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports an OBJ 3D model as an independent `Mesh` or scene.
 *
 * Generated from Godot docs: ResourceImporterOBJ
 */
class ResourceImporterOBJ(handle: MemorySegment) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ResourceImporterOBJ? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ResourceImporterOBJ? =
            if (handle.address() == 0L) null else ResourceImporterOBJ(handle)

        // No MethodBinds emitted yet.
    }
}
