package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * A 2D convex polygon shape used for physics collision.
 *
 * Generated from Godot docs: ConvexPolygonShape2D
 */
class ConvexPolygonShape2D(handle: MemorySegment) : Shape2D(handle) {
    val points: List<Vector2>
        @JvmName("pointsProperty")
        get() = getPoints()

    /**
     * The polygon's list of vertices that form a convex hull. Can be in either clockwise or
     * counterclockwise order. Warning: Only set this property to a list of points that actually form a
     * convex hull. Use `set_point_cloud` to generate the convex hull of an arbitrary set of points.
     *
     * Generated from Godot docs: ConvexPolygonShape2D.get_points
     */
    fun getPoints(): List<Vector2> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getPointsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ConvexPolygonShape2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConvexPolygonShape2D? =
            if (handle.address() == 0L) null else ConvexPolygonShape2D(handle)

        private const val GET_POINTS_HASH = 2961356807L
        private val getPointsBind by lazy {
            ObjectCalls.getMethodBind("ConvexPolygonShape2D", "get_points", GET_POINTS_HASH)
        }
    }
}
