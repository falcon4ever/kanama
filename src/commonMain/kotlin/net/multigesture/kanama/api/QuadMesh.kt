package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Class representing a square mesh facing the camera.
 *
 * Generated from Godot docs: QuadMesh
 */
class QuadMesh(handle: GodotHandle) : PlaneMesh(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): QuadMesh? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): QuadMesh? =
            if (handle.address() == 0L) null else RefCounted.owned(QuadMesh(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): QuadMesh? =
            if (handle.address() == 0L) null else QuadMesh(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
