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
     * Checks if two line segments intersect, with line `a` between `from_a` and `to_a` and line `b`
     * between `from_b` and `to_b`. If the line segments intersect, the point of intersection is
     * returned as a `Vector2`. If no intersection takes place, `null` is returned.
     *
     * Generated from Godot docs: Geometry2D.segment_intersects_segment
     */
    @JvmStatic
    fun segmentIntersectsSegment(fromA: Vector2, toA: Vector2, fromB: Vector2, toB: Vector2): Any? {
        return ObjectCalls.ptrcallWithFourVector2ArgsRetVariantScalar(segmentIntersectsSegmentBind, singleton, fromA, toA, fromB, toB)
    }

    /**
     * Returns the point of intersection between the two lines (`from_a`, `dir_a`) and (`from_b`,
     * `dir_b`). Returns a `Vector2`, or `null` if the lines are parallel. `from` and `dir` are not
     * endpoints of a line segment or ray but the slope (`dir`) and a known point (`from`) on that
     * line. To get the intersection between two line segments, use `segment_intersects_segment`.
     *
     * Generated from Godot docs: Geometry2D.line_intersects_line
     */
    @JvmStatic
    fun lineIntersectsLine(fromA: Vector2, dirA: Vector2, fromB: Vector2, dirB: Vector2): Any? {
        return ObjectCalls.ptrcallWithFourVector2ArgsRetVariantScalar(lineIntersectsLineBind, singleton, fromA, dirA, fromB, dirB)
    }

    /**
     * Given the two 2D segments (`p1`, `q1`) and (`p2`, `q2`), finds those two points on the two
     * segments that are closest to each other. Returns a `PackedVector2Array` that contains this point
     * on (`p1`, `q1`) as well the accompanying point on (`p2`, `q2`).
     *
     * Generated from Godot docs: Geometry2D.get_closest_points_between_segments
     */
    @JvmStatic
    fun getClosestPointsBetweenSegments(p1: Vector2, q1: Vector2, p2: Vector2, q2: Vector2): List<Vector2> {
        return ObjectCalls.ptrcallWithFourVector2ArgsRetPackedVector2List(getClosestPointsBetweenSegmentsBind, singleton, p1, q1, p2, q2)
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

    /**
     * Returns `true` if `polygon`'s vertices are ordered in clockwise order, otherwise returns
     * `false`. Note: Assumes a Cartesian coordinate system where `+x` is right and `+y` is up. If
     * using screen coordinates (`+y` is down), the result will need to be flipped (i.e. a `true`
     * result will indicate counter-clockwise).
     *
     * Generated from Godot docs: Geometry2D.is_polygon_clockwise
     */
    @JvmStatic
    fun isPolygonClockwise(polygon: List<Vector2>): Boolean {
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetBool(isPolygonClockwiseBind, singleton, polygon)
    }

    /**
     * Returns `true` if `point` is inside `polygon` or if it's located exactly on polygon's boundary,
     * otherwise returns `false`.
     *
     * Generated from Godot docs: Geometry2D.is_point_in_polygon
     */
    @JvmStatic
    fun isPointInPolygon(point: Vector2, polygon: List<Vector2>): Boolean {
        return ObjectCalls.ptrcallWithVector2PackedVector2ListArgsRetBool(isPointInPolygonBind, singleton, point, polygon)
    }

    /**
     * Triangulates the polygon specified by the points in `polygon`. Returns a `PackedInt32Array`
     * where each triangle consists of three consecutive point indices into `polygon` (i.e. the
     * returned array will have `n * 3` elements, with `n` being the number of found triangles). Output
     * triangles will always be counter clockwise, and the contour will be flipped if it's clockwise.
     * If the triangulation did not succeed, an empty `PackedInt32Array` is returned.
     *
     * Generated from Godot docs: Geometry2D.triangulate_polygon
     */
    @JvmStatic
    fun triangulatePolygon(polygon: List<Vector2>): List<Int> {
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetPackedInt32List(triangulatePolygonBind, singleton, polygon)
    }

    /**
     * Triangulates the area specified by discrete set of `points` such that no point is inside the
     * circumcircle of any resulting triangle. Returns a `PackedInt32Array` where each triangle
     * consists of three consecutive point indices into `points` (i.e. the returned array will have `n
     * * 3` elements, with `n` being the number of found triangles). If the triangulation did not
     * succeed, an empty `PackedInt32Array` is returned.
     *
     * Generated from Godot docs: Geometry2D.triangulate_delaunay
     */
    @JvmStatic
    fun triangulateDelaunay(points: List<Vector2>): List<Int> {
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetPackedInt32List(triangulateDelaunayBind, singleton, points)
    }

    /**
     * Given an array of `Vector2`s, returns the convex hull as a list of points in counterclockwise
     * order. The last point is the same as the first one.
     *
     * Generated from Godot docs: Geometry2D.convex_hull
     */
    @JvmStatic
    fun convexHull(points: List<Vector2>): List<Vector2> {
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetPackedVector2List(convexHullBind, singleton, points)
    }

    /**
     * Given an array of `Vector2`s representing tiles, builds an atlas. The returned dictionary has
     * two keys: `points` is a `PackedVector2Array` that specifies the positions of each tile, `size`
     * contains the overall size of the whole atlas as `Vector2i`.
     *
     * Generated from Godot docs: Geometry2D.make_atlas
     */
    @JvmStatic
    fun makeAtlas(sizes: List<Vector2>): Map<String, Any?> {
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetDictionary(makeAtlasBind, singleton, sizes)
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

    private const val SEGMENT_INTERSECTS_SEGMENT_HASH = 2058025344L
    private val segmentIntersectsSegmentBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "segment_intersects_segment", SEGMENT_INTERSECTS_SEGMENT_HASH)
    }

    private const val LINE_INTERSECTS_LINE_HASH = 2058025344L
    private val lineIntersectsLineBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "line_intersects_line", LINE_INTERSECTS_LINE_HASH)
    }

    private const val GET_CLOSEST_POINTS_BETWEEN_SEGMENTS_HASH = 3344690961L
    private val getClosestPointsBetweenSegmentsBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "get_closest_points_between_segments", GET_CLOSEST_POINTS_BETWEEN_SEGMENTS_HASH)
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

    private const val IS_POLYGON_CLOCKWISE_HASH = 1361156557L
    private val isPolygonClockwiseBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "is_polygon_clockwise", IS_POLYGON_CLOCKWISE_HASH)
    }

    private const val IS_POINT_IN_POLYGON_HASH = 738277916L
    private val isPointInPolygonBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "is_point_in_polygon", IS_POINT_IN_POLYGON_HASH)
    }

    private const val TRIANGULATE_POLYGON_HASH = 1389921771L
    private val triangulatePolygonBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "triangulate_polygon", TRIANGULATE_POLYGON_HASH)
    }

    private const val TRIANGULATE_DELAUNAY_HASH = 1389921771L
    private val triangulateDelaunayBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "triangulate_delaunay", TRIANGULATE_DELAUNAY_HASH)
    }

    private const val CONVEX_HULL_HASH = 2004331998L
    private val convexHullBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "convex_hull", CONVEX_HULL_HASH)
    }

    private const val MAKE_ATLAS_HASH = 1337682371L
    private val makeAtlasBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "make_atlas", MAKE_ATLAS_HASH)
    }
}
