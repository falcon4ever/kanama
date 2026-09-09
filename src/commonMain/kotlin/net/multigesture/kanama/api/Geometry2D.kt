package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Provides methods for some common 2D geometric operations.
 *
 * Generated from Godot docs: Geometry2D
 */
object Geometry2D {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("Geometry2D")
    }

    const val OPERATION_UNION: Long = 0L
    const val OPERATION_DIFFERENCE: Long = 1L
    const val OPERATION_INTERSECTION: Long = 2L
    const val OPERATION_XOR: Long = 3L
    const val JOIN_SQUARE: Long = 0L
    const val JOIN_ROUND: Long = 1L
    const val JOIN_MITER: Long = 2L
    const val END_POLYGON: Long = 0L
    const val END_JOINED: Long = 1L
    const val END_BUTT: Long = 2L
    const val END_SQUARE: Long = 3L
    const val END_ROUND: Long = 4L

    /**
     * Returns `true` if `point` is inside the circle or if it's located exactly on the circle's
     * boundary, otherwise returns `false`.
     *
     * Generated from Godot docs: Geometry2D.is_point_in_circle
     */
    @JvmStatic
    fun isPointInCircle(point: Vector2, circlePosition: Vector2, circleRadius: Double): Boolean {
        return ObjectCalls.ptrcallWithTwoVector2DoubleArgsRetBool(isPointInCircleBind, singleton, point, circlePosition, circleRadius)
    }

    /**
     * Given the 2D segment (`segment_from`, `segment_to`), returns the position on the segment (as a
     * number between 0 and 1) at which the segment hits the circle that is located at position
     * `circle_position` and has radius `circle_radius`. If the segment does not intersect the circle,
     * -1 is returned (this is also the case if the line extending the segment would intersect the
     * circle, but the segment does not).
     *
     * Generated from Godot docs: Geometry2D.segment_intersects_circle
     */
    @JvmStatic
    fun segmentIntersectsCircle(segmentFrom: Vector2, segmentTo: Vector2, circlePosition: Vector2, circleRadius: Double): Double {
        return ObjectCalls.ptrcallWithThreeVector2DoubleArgsRetDouble(segmentIntersectsCircleBind, singleton, segmentFrom, segmentTo, circlePosition, circleRadius)
    }

    /**
     * Returns the 2D point on the 2D segment (`s1`, `s2`) that is closest to `point`. The returned
     * point will always be inside the specified segment.
     *
     * Generated from Godot docs: Geometry2D.get_closest_point_to_segment
     */
    @JvmStatic
    fun getClosestPointToSegment(point: Vector2, s1: Vector2, s2: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithThreeVector2ArgsRetVector2(getClosestPointToSegmentBind, singleton, point, s1, s2)
    }

    /**
     * Returns the 2D point on the 2D line defined by (`s1`, `s2`) that is closest to `point`. The
     * returned point can be inside the segment (`s1`, `s2`) or outside of it, i.e. somewhere on the
     * line extending from the segment.
     *
     * Generated from Godot docs: Geometry2D.get_closest_point_to_segment_uncapped
     */
    @JvmStatic
    fun getClosestPointToSegmentUncapped(point: Vector2, s1: Vector2, s2: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithThreeVector2ArgsRetVector2(getClosestPointToSegmentUncappedBind, singleton, point, s1, s2)
    }

    /**
     * Returns if `point` is inside the triangle specified by `a`, `b` and `c`.
     *
     * Generated from Godot docs: Geometry2D.point_is_inside_triangle
     */
    @JvmStatic
    fun pointIsInsideTriangle(point: Vector2, a: Vector2, b: Vector2, c: Vector2): Boolean {
        return ObjectCalls.ptrcallWithFourVector2ArgsRetBool(pointIsInsideTriangleBind, singleton, point, a, b, c)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): Geometry2D? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): Geometry2D? =
        if (handle.address() == 0L) null else this

    private const val IS_POINT_IN_CIRCLE_HASH = 2929491703L
    private val isPointInCircleBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "is_point_in_circle", IS_POINT_IN_CIRCLE_HASH)
    }

    private const val SEGMENT_INTERSECTS_CIRCLE_HASH = 1356928167L
    private val segmentIntersectsCircleBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "segment_intersects_circle", SEGMENT_INTERSECTS_CIRCLE_HASH)
    }

    private const val GET_CLOSEST_POINT_TO_SEGMENT_HASH = 4172901909L
    private val getClosestPointToSegmentBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "get_closest_point_to_segment", GET_CLOSEST_POINT_TO_SEGMENT_HASH)
    }

    private const val GET_CLOSEST_POINT_TO_SEGMENT_UNCAPPED_HASH = 4172901909L
    private val getClosestPointToSegmentUncappedBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "get_closest_point_to_segment_uncapped", GET_CLOSEST_POINT_TO_SEGMENT_UNCAPPED_HASH)
    }

    private const val POINT_IS_INSIDE_TRIANGLE_HASH = 1025948137L
    private val pointIsInsideTriangleBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "point_is_inside_triangle", POINT_IS_INSIDE_TRIANGLE_HASH)
    }
}
