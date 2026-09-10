package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
 * A 3D convex polyhedron shape used for physics collision.
 *
 * Generated from Godot docs: ConvexPolygonShape3D
 */
class ConvexPolygonShape3D(handle: MemorySegment) : Shape3D(handle) {
    val points: List<Vector3>
        @JvmName("pointsProperty")
        get() = getPoints()

    /**
     * The list of 3D points forming the convex polygon shape.
     *
     * Generated from Godot docs: ConvexPolygonShape3D.get_points
     */
    fun getPoints(): List<Vector3> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getPointsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ConvexPolygonShape3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConvexPolygonShape3D? =
            if (handle.address() == 0L) null else ConvexPolygonShape3D(handle)

        private const val GET_POINTS_HASH = 497664490L
        private val getPointsBind by lazy {
            ObjectCalls.getMethodBind("ConvexPolygonShape3D", "get_points", GET_POINTS_HASH)
        }
    }
}
