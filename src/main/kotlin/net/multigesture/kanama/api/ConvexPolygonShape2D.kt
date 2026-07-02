package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A 2D convex polygon shape used for physics collision.
 *
 * Generated from Godot docs: ConvexPolygonShape2D
 */
class ConvexPolygonShape2D(handle: MemorySegment) : Shape2D(handle) {
    var points: List<Vector2>
        @JvmName("pointsProperty")
        get() = getPoints()
        @JvmName("setPointsProperty")
        set(value) = setPoints(value)

    fun setPointCloud(pointCloud: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setPointCloudBind, handle, pointCloud)
    }

    fun setPoints(points: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setPointsBind, handle, points)
    }

    fun getPoints(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPointsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ConvexPolygonShape2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConvexPolygonShape2D? =
            if (handle.address() == 0L) null else ConvexPolygonShape2D(handle)

        private const val SET_POINT_CLOUD_HASH = 1509147220L
        private val setPointCloudBind by lazy {
            ObjectCalls.getMethodBind("ConvexPolygonShape2D", "set_point_cloud", SET_POINT_CLOUD_HASH)
        }

        private const val SET_POINTS_HASH = 1509147220L
        private val setPointsBind by lazy {
            ObjectCalls.getMethodBind("ConvexPolygonShape2D", "set_points", SET_POINTS_HASH)
        }

        private const val GET_POINTS_HASH = 2961356807L
        private val getPointsBind by lazy {
            ObjectCalls.getMethodBind("ConvexPolygonShape2D", "get_points", GET_POINTS_HASH)
        }
    }
}
