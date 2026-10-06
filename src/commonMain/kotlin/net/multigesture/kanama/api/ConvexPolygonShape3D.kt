package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * A 3D convex polyhedron shape used for physics collision.
 *
 * Generated from Godot docs: ConvexPolygonShape3D
 */
class ConvexPolygonShape3D(handle: GodotHandle) : Shape3D(handle) {
    var points: List<Vector3>
        @JvmName("pointsProperty")
        get() = getPoints()
        @JvmName("setPointsProperty")
        set(value) = setPoints(value)

    /**
     * The list of 3D points forming the convex polygon shape.
     *
     * Generated from Godot docs: ConvexPolygonShape3D.set_points
     */
    fun setPoints(points: List<Vector3>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedVector3ListArg(Binds.setPointsBind, segment, points)
    }

    /**
     * The list of 3D points forming the convex polygon shape.
     *
     * Generated from Godot docs: ConvexPolygonShape3D.get_points
     */
    fun getPoints(): List<Vector3> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(Binds.getPointsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ConvexPolygonShape3D? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): ConvexPolygonShape3D? =
            if (handle.address() == 0L) null else RefCounted.owned(ConvexPolygonShape3D(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): ConvexPolygonShape3D? =
            if (handle.address() == 0L) null else ConvexPolygonShape3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_POINTS_HASH = 334873810L
        @JvmField
        val setPointsBind =
            ObjectCalls.getMethodBind("ConvexPolygonShape3D", "set_points", SET_POINTS_HASH)

        private const val GET_POINTS_HASH = 497664490L
        @JvmField
        val getPointsBind =
            ObjectCalls.getMethodBind("ConvexPolygonShape3D", "get_points", GET_POINTS_HASH)
    }
}
