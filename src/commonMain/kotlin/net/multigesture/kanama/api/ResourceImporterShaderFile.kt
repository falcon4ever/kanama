package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ResourceImporterShaderFile? =
            if (handle.address() == 0L) null else RefCounted.owned(ResourceImporterShaderFile(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ResourceImporterShaderFile? =
            if (handle.address() == 0L) null else ResourceImporterShaderFile(GodotHandle(handle))
    }
}
