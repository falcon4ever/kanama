package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ResourceImporterOBJ? =
            if (handle.address() == 0L) null else RefCounted.owned(ResourceImporterOBJ(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ResourceImporterOBJ? =
            if (handle.address() == 0L) null else ResourceImporterOBJ(GodotHandle(handle))
    }
}
