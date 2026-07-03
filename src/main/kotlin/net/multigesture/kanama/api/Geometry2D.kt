package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

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
     * Decomposes the `polygon` into multiple convex hulls and returns an array of
     * `PackedVector2Array`.
     *
     * Generated from Godot docs: Geometry2D.decompose_polygon_in_convex
     */
    @JvmStatic
    fun decomposePolygonInConvex(polygon: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetPackedVector2ListList(decomposePolygonInConvexBind, singleton, polygon)
    }

    /**
     * Merges (combines) `polygon_a` and `polygon_b` and returns an array of merged polygons. This
     * performs `OPERATION_UNION` between polygons. The operation may result in an outer polygon
     * (boundary) and multiple inner polygons (holes) produced which could be distinguished by calling
     * `is_polygon_clockwise`.
     *
     * Generated from Godot docs: Geometry2D.merge_polygons
     */
    @JvmStatic
    fun mergePolygons(polygonA: List<Vector2>, polygonB: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(mergePolygonsBind, singleton, polygonA, polygonB)
    }

    /**
     * Clips `polygon_a` against `polygon_b` and returns an array of clipped polygons. This performs
     * `OPERATION_DIFFERENCE` between polygons. Returns an empty array if `polygon_b` completely
     * overlaps `polygon_a`. If `polygon_b` is enclosed by `polygon_a`, returns an outer polygon
     * (boundary) and inner polygon (hole) which could be distinguished by calling
     * `is_polygon_clockwise`.
     *
     * Generated from Godot docs: Geometry2D.clip_polygons
     */
    @JvmStatic
    fun clipPolygons(polygonA: List<Vector2>, polygonB: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(clipPolygonsBind, singleton, polygonA, polygonB)
    }

    /**
     * Intersects `polygon_a` with `polygon_b` and returns an array of intersected polygons. This
     * performs `OPERATION_INTERSECTION` between polygons. In other words, returns common area shared
     * by polygons. Returns an empty array if no intersection occurs. The operation may result in an
     * outer polygon (boundary) and inner polygon (hole) produced which could be distinguished by
     * calling `is_polygon_clockwise`.
     *
     * Generated from Godot docs: Geometry2D.intersect_polygons
     */
    @JvmStatic
    fun intersectPolygons(polygonA: List<Vector2>, polygonB: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(intersectPolygonsBind, singleton, polygonA, polygonB)
    }

    /**
     * Mutually excludes common area defined by intersection of `polygon_a` and `polygon_b` (see
     * `intersect_polygons`) and returns an array of excluded polygons. This performs `OPERATION_XOR`
     * between polygons. In other words, returns all but common area between polygons. The operation
     * may result in an outer polygon (boundary) and inner polygon (hole) produced which could be
     * distinguished by calling `is_polygon_clockwise`.
     *
     * Generated from Godot docs: Geometry2D.exclude_polygons
     */
    @JvmStatic
    fun excludePolygons(polygonA: List<Vector2>, polygonB: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(excludePolygonsBind, singleton, polygonA, polygonB)
    }

    /**
     * Clips `polyline` against `polygon` and returns an array of clipped polylines. This performs
     * `OPERATION_DIFFERENCE` between the polyline and the polygon. This operation can be thought of as
     * cutting a line with a closed shape.
     *
     * Generated from Godot docs: Geometry2D.clip_polyline_with_polygon
     */
    @JvmStatic
    fun clipPolylineWithPolygon(polyline: List<Vector2>, polygon: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(clipPolylineWithPolygonBind, singleton, polyline, polygon)
    }

    /**
     * Intersects `polyline` with `polygon` and returns an array of intersected polylines. This
     * performs `OPERATION_INTERSECTION` between the polyline and the polygon. This operation can be
     * thought of as chopping a line with a closed shape.
     *
     * Generated from Godot docs: Geometry2D.intersect_polyline_with_polygon
     */
    @JvmStatic
    fun intersectPolylineWithPolygon(polyline: List<Vector2>, polygon: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(intersectPolylineWithPolygonBind, singleton, polyline, polygon)
    }

    /**
     * Inflates or deflates `polygon` by `delta` units (pixels). If `delta` is positive, makes the
     * polygon grow outward. If `delta` is negative, shrinks the polygon inward. Returns an array of
     * polygons because inflating/deflating may result in multiple discrete polygons. Returns an empty
     * array if `delta` is negative and the absolute value of it approximately exceeds the minimum
     * bounding rectangle dimensions of the polygon. Each polygon's vertices will be rounded as
     * determined by `join_type`. The operation may result in an outer polygon (boundary) and inner
     * polygon (hole) produced which could be distinguished by calling `is_polygon_clockwise`. Note: To
     * translate the polygon's vertices specifically, multiply them to a `Transform2D`:
     *
     * Generated from Godot docs: Geometry2D.offset_polygon
     */
    @JvmStatic
    fun offsetPolygon(polygon: List<Vector2>, delta: Double, joinType: Long = 0L): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithPackedVector2ListDoubleAndLongArgsRetPackedVector2ListList(offsetPolygonBind, singleton, polygon, delta, joinType)
    }

    /**
     * Inflates or deflates `polyline` by `delta` units (pixels), producing polygons. If `delta` is
     * positive, makes the polyline grow outward. Returns an array of polygons because
     * inflating/deflating may result in multiple discrete polygons. If `delta` is negative, returns an
     * empty array. Each polygon's vertices will be rounded as determined by `join_type`. Each
     * polygon's endpoints will be rounded as determined by `end_type`. The operation may result in an
     * outer polygon (boundary) and inner polygon (hole) produced which could be distinguished by
     * calling `is_polygon_clockwise`.
     *
     * Generated from Godot docs: Geometry2D.offset_polyline
     */
    @JvmStatic
    fun offsetPolyline(polyline: List<Vector2>, delta: Double, joinType: Long = 0L, endType: Long = 3L): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithPackedVector2ListDoubleAndTwoLongArgsRetPackedVector2ListList(offsetPolylineBind, singleton, polyline, delta, joinType, endType)
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

    /**
     * Returns the Bresenham line (https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm) between
     * the `from` and `to` points. A Bresenham line is a series of pixels that draws a line and is
     * always 1-pixel thick on every row and column of the drawing (never more, never less). Example
     * code to draw a line between two `Marker2D` nodes using a series of `CanvasItem.draw_rect` calls:
     *
     * Generated from Godot docs: Geometry2D.bresenham_line
     */
    @JvmStatic
    fun bresenhamLine(from: Vector2i, to: Vector2i): List<Vector2i> {
        return ObjectCalls.ptrcallWithTwoVector2iArgsRetVector2iList(bresenhamLineBind, singleton, from, to)
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

    private const val DECOMPOSE_POLYGON_IN_CONVEX_HASH = 3982393695L
    private val decomposePolygonInConvexBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "decompose_polygon_in_convex", DECOMPOSE_POLYGON_IN_CONVEX_HASH)
    }

    private const val MERGE_POLYGONS_HASH = 3637387053L
    private val mergePolygonsBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "merge_polygons", MERGE_POLYGONS_HASH)
    }

    private const val CLIP_POLYGONS_HASH = 3637387053L
    private val clipPolygonsBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "clip_polygons", CLIP_POLYGONS_HASH)
    }

    private const val INTERSECT_POLYGONS_HASH = 3637387053L
    private val intersectPolygonsBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "intersect_polygons", INTERSECT_POLYGONS_HASH)
    }

    private const val EXCLUDE_POLYGONS_HASH = 3637387053L
    private val excludePolygonsBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "exclude_polygons", EXCLUDE_POLYGONS_HASH)
    }

    private const val CLIP_POLYLINE_WITH_POLYGON_HASH = 3637387053L
    private val clipPolylineWithPolygonBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "clip_polyline_with_polygon", CLIP_POLYLINE_WITH_POLYGON_HASH)
    }

    private const val INTERSECT_POLYLINE_WITH_POLYGON_HASH = 3637387053L
    private val intersectPolylineWithPolygonBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "intersect_polyline_with_polygon", INTERSECT_POLYLINE_WITH_POLYGON_HASH)
    }

    private const val OFFSET_POLYGON_HASH = 1275354010L
    private val offsetPolygonBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "offset_polygon", OFFSET_POLYGON_HASH)
    }

    private const val OFFSET_POLYLINE_HASH = 2328231778L
    private val offsetPolylineBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "offset_polyline", OFFSET_POLYLINE_HASH)
    }

    private const val MAKE_ATLAS_HASH = 1337682371L
    private val makeAtlasBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "make_atlas", MAKE_ATLAS_HASH)
    }

    private const val BRESENHAM_LINE_HASH = 1989391000L
    private val bresenhamLineBind by lazy {
        ObjectCalls.getMethodBind("Geometry2D", "bresenham_line", BRESENHAM_LINE_HASH)
    }
}
