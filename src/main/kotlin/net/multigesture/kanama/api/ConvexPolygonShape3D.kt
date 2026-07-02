package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * A 3D convex polyhedron shape used for physics collision.
 *
 * Generated from Godot docs: ConvexPolygonShape3D
 */
class ConvexPolygonShape3D(handle: MemorySegment) : Shape3D(handle) {
    var points: List<Vector3>
        @JvmName("pointsProperty")
        get() = getPoints()
        @JvmName("setPointsProperty")
        set(value) = setPoints(value)

    fun setPoints(points: List<Vector3>) {
        ObjectCalls.ptrcallWithPackedVector3ListArg(setPointsBind, handle, points)
    }

    fun getPoints(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getPointsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ConvexPolygonShape3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConvexPolygonShape3D? =
            if (handle.address() == 0L) null else ConvexPolygonShape3D(handle)

        private const val SET_POINTS_HASH = 334873810L
        private val setPointsBind by lazy {
            ObjectCalls.getMethodBind("ConvexPolygonShape3D", "set_points", SET_POINTS_HASH)
        }

        private const val GET_POINTS_HASH = 497664490L
        private val getPointsBind by lazy {
            ObjectCalls.getMethodBind("ConvexPolygonShape3D", "get_points", GET_POINTS_HASH)
        }
    }
}
