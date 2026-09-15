package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports an OBJ 3D model as an independent `Mesh` or scene.
 *
 * Generated from Godot docs: ResourceImporterOBJ
 */
class ResourceImporterOBJ(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterOBJ? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ResourceImporterOBJ? =
            if (handle.address() == 0L) null else ResourceImporterOBJ(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
