package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A 3D convex polyhedron shape used for physics collision.
 *
 * Generated from Godot docs: ConvexPolygonShape3D
 */
class ConvexPolygonShape3D(handle: MemorySegment) : Shape3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ConvexPolygonShape3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConvexPolygonShape3D? =
            if (handle.address() == 0L) null else ConvexPolygonShape3D(handle)

        // No MethodBinds emitted yet.
    }
}
