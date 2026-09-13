package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Imports native GLSL shaders (not Godot shaders) as an `RDShaderFile`.
 *
 * Generated from Godot docs: ResourceImporterShaderFile
 */
class ResourceImporterShaderFile(handle: GodotHandle) : ResourceImporter(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ResourceImporterShaderFile? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): ResourceImporterShaderFile? =
            if (handle.address() == 0L) null else ResourceImporterShaderFile(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
