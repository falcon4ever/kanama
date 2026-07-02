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

    @JvmStatic
    fun isPointInCircle(point: Vector2, circlePosition: Vector2, circleRadius: Double): Boolean {
        return ObjectCalls.ptrcallWithTwoVector2DoubleArgsRetBool(isPointInCircleBind, singleton, point, circlePosition, circleRadius)
    }

    @JvmStatic
    fun segmentIntersectsCircle(segmentFrom: Vector2, segmentTo: Vector2, circlePosition: Vector2, circleRadius: Double): Double {
        return ObjectCalls.ptrcallWithThreeVector2DoubleArgsRetDouble(segmentIntersectsCircleBind, singleton, segmentFrom, segmentTo, circlePosition, circleRadius)
    }

    @JvmStatic
    fun segmentIntersectsSegment(fromA: Vector2, toA: Vector2, fromB: Vector2, toB: Vector2): Any? {
        return ObjectCalls.ptrcallWithFourVector2ArgsRetVariantScalar(segmentIntersectsSegmentBind, singleton, fromA, toA, fromB, toB)
    }

    @JvmStatic
    fun lineIntersectsLine(fromA: Vector2, dirA: Vector2, fromB: Vector2, dirB: Vector2): Any? {
        return ObjectCalls.ptrcallWithFourVector2ArgsRetVariantScalar(lineIntersectsLineBind, singleton, fromA, dirA, fromB, dirB)
    }

    @JvmStatic
    fun getClosestPointsBetweenSegments(p1: Vector2, q1: Vector2, p2: Vector2, q2: Vector2): List<Vector2> {
        return ObjectCalls.ptrcallWithFourVector2ArgsRetPackedVector2List(getClosestPointsBetweenSegmentsBind, singleton, p1, q1, p2, q2)
    }

    @JvmStatic
    fun getClosestPointToSegment(point: Vector2, s1: Vector2, s2: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithThreeVector2ArgsRetVector2(getClosestPointToSegmentBind, singleton, point, s1, s2)
    }

    @JvmStatic
    fun getClosestPointToSegmentUncapped(point: Vector2, s1: Vector2, s2: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithThreeVector2ArgsRetVector2(getClosestPointToSegmentUncappedBind, singleton, point, s1, s2)
    }

    @JvmStatic
    fun pointIsInsideTriangle(point: Vector2, a: Vector2, b: Vector2, c: Vector2): Boolean {
        return ObjectCalls.ptrcallWithFourVector2ArgsRetBool(pointIsInsideTriangleBind, singleton, point, a, b, c)
    }

    @JvmStatic
    fun isPolygonClockwise(polygon: List<Vector2>): Boolean {
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetBool(isPolygonClockwiseBind, singleton, polygon)
    }

    @JvmStatic
    fun isPointInPolygon(point: Vector2, polygon: List<Vector2>): Boolean {
        return ObjectCalls.ptrcallWithVector2PackedVector2ListArgsRetBool(isPointInPolygonBind, singleton, point, polygon)
    }

    @JvmStatic
    fun triangulatePolygon(polygon: List<Vector2>): List<Int> {
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetPackedInt32List(triangulatePolygonBind, singleton, polygon)
    }

    @JvmStatic
    fun triangulateDelaunay(points: List<Vector2>): List<Int> {
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetPackedInt32List(triangulateDelaunayBind, singleton, points)
    }

    @JvmStatic
    fun convexHull(points: List<Vector2>): List<Vector2> {
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetPackedVector2List(convexHullBind, singleton, points)
    }

    @JvmStatic
    fun decomposePolygonInConvex(polygon: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetPackedVector2ListList(decomposePolygonInConvexBind, singleton, polygon)
    }

    @JvmStatic
    fun mergePolygons(polygonA: List<Vector2>, polygonB: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(mergePolygonsBind, singleton, polygonA, polygonB)
    }

    @JvmStatic
    fun clipPolygons(polygonA: List<Vector2>, polygonB: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(clipPolygonsBind, singleton, polygonA, polygonB)
    }

    @JvmStatic
    fun intersectPolygons(polygonA: List<Vector2>, polygonB: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(intersectPolygonsBind, singleton, polygonA, polygonB)
    }

    @JvmStatic
    fun excludePolygons(polygonA: List<Vector2>, polygonB: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(excludePolygonsBind, singleton, polygonA, polygonB)
    }

    @JvmStatic
    fun clipPolylineWithPolygon(polyline: List<Vector2>, polygon: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(clipPolylineWithPolygonBind, singleton, polyline, polygon)
    }

    @JvmStatic
    fun intersectPolylineWithPolygon(polyline: List<Vector2>, polygon: List<Vector2>): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(intersectPolylineWithPolygonBind, singleton, polyline, polygon)
    }

    @JvmStatic
    fun offsetPolygon(polygon: List<Vector2>, delta: Double, joinType: Long = 0L): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithPackedVector2ListDoubleAndLongArgsRetPackedVector2ListList(offsetPolygonBind, singleton, polygon, delta, joinType)
    }

    @JvmStatic
    fun offsetPolyline(polyline: List<Vector2>, delta: Double, joinType: Long = 0L, endType: Long = 3L): List<List<Vector2>> {
        return ObjectCalls.ptrcallWithPackedVector2ListDoubleAndTwoLongArgsRetPackedVector2ListList(offsetPolylineBind, singleton, polyline, delta, joinType, endType)
    }

    @JvmStatic
    fun makeAtlas(sizes: List<Vector2>): Map<String, Any?> {
        return ObjectCalls.ptrcallWithPackedVector2ListArgRetDictionary(makeAtlasBind, singleton, sizes)
    }

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
