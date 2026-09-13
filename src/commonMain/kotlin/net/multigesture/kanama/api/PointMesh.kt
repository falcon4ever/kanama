package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Mesh with a single point primitive.
 *
 * Generated from Godot docs: PointMesh
 */
class PointMesh(handle: GodotHandle) : PrimitiveMesh(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PointMesh? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): PointMesh? =
            if (handle.address() == 0L) null else PointMesh(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
