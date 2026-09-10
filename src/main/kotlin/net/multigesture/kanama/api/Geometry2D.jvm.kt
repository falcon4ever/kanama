package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

// GENERATED desktop/Android companion for Geometry2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Geometry2D waits on: ptrcallWithPackedVector2ListArgRetDictionary,
//   ptrcallWithPackedVector2ListArgRetPackedVector2ListList,
//   ptrcallWithPackedVector2ListDoubleAndLongArgsRetPackedVector2ListList,
//   ptrcallWithPackedVector2ListDoubleAndTwoLongArgsRetPackedVector2ListList,
//   ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList,
//   ptrcallWithTwoVector2iArgsRetVector2iList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Decomposes the `polygon` into multiple convex hulls and returns an array of
 * `PackedVector2Array`.
 *
 * Generated from Godot docs: Geometry2D.decompose_polygon_in_convex
 */
fun Geometry2D.decomposePolygonInConvex(polygon: List<Vector2>): List<List<Vector2>> {
    return ObjectCalls.ptrcallWithPackedVector2ListArgRetPackedVector2ListList(decomposePolygonInConvexBind, geometry2DSingleton, polygon)
}

/**
 * Merges (combines) `polygon_a` and `polygon_b` and returns an array of merged polygons. This
 * performs `OPERATION_UNION` between polygons. The operation may result in an outer polygon
 * (boundary) and multiple inner polygons (holes) produced which could be distinguished by calling
 * `is_polygon_clockwise`.
 *
 * Generated from Godot docs: Geometry2D.merge_polygons
 */
fun Geometry2D.mergePolygons(polygonA: List<Vector2>, polygonB: List<Vector2>): List<List<Vector2>> {
    return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(mergePolygonsBind, geometry2DSingleton, polygonA, polygonB)
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
fun Geometry2D.clipPolygons(polygonA: List<Vector2>, polygonB: List<Vector2>): List<List<Vector2>> {
    return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(clipPolygonsBind, geometry2DSingleton, polygonA, polygonB)
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
fun Geometry2D.intersectPolygons(polygonA: List<Vector2>, polygonB: List<Vector2>): List<List<Vector2>> {
    return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(intersectPolygonsBind, geometry2DSingleton, polygonA, polygonB)
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
fun Geometry2D.excludePolygons(polygonA: List<Vector2>, polygonB: List<Vector2>): List<List<Vector2>> {
    return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(excludePolygonsBind, geometry2DSingleton, polygonA, polygonB)
}

/**
 * Clips `polyline` against `polygon` and returns an array of clipped polylines. This performs
 * `OPERATION_DIFFERENCE` between the polyline and the polygon. This operation can be thought of as
 * cutting a line with a closed shape.
 *
 * Generated from Godot docs: Geometry2D.clip_polyline_with_polygon
 */
fun Geometry2D.clipPolylineWithPolygon(polyline: List<Vector2>, polygon: List<Vector2>): List<List<Vector2>> {
    return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(clipPolylineWithPolygonBind, geometry2DSingleton, polyline, polygon)
}

/**
 * Intersects `polyline` with `polygon` and returns an array of intersected polylines. This
 * performs `OPERATION_INTERSECTION` between the polyline and the polygon. This operation can be
 * thought of as chopping a line with a closed shape.
 *
 * Generated from Godot docs: Geometry2D.intersect_polyline_with_polygon
 */
fun Geometry2D.intersectPolylineWithPolygon(polyline: List<Vector2>, polygon: List<Vector2>): List<List<Vector2>> {
    return ObjectCalls.ptrcallWithTwoPackedVector2ListArgsRetPackedVector2ListList(intersectPolylineWithPolygonBind, geometry2DSingleton, polyline, polygon)
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
fun Geometry2D.offsetPolygon(polygon: List<Vector2>, delta: Double, joinType: Long = 0L): List<List<Vector2>> {
    return ObjectCalls.ptrcallWithPackedVector2ListDoubleAndLongArgsRetPackedVector2ListList(offsetPolygonBind, geometry2DSingleton, polygon, delta, joinType)
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
fun Geometry2D.offsetPolyline(polyline: List<Vector2>, delta: Double, joinType: Long = 0L, endType: Long = 3L): List<List<Vector2>> {
    return ObjectCalls.ptrcallWithPackedVector2ListDoubleAndTwoLongArgsRetPackedVector2ListList(offsetPolylineBind, geometry2DSingleton, polyline, delta, joinType, endType)
}

/**
 * Given an array of `Vector2`s representing tiles, builds an atlas. The returned dictionary has
 * two keys: `points` is a `PackedVector2Array` that specifies the positions of each tile, `size`
 * contains the overall size of the whole atlas as `Vector2i`.
 *
 * Generated from Godot docs: Geometry2D.make_atlas
 */
fun Geometry2D.makeAtlas(sizes: List<Vector2>): Map<String, Any?> {
    return ObjectCalls.ptrcallWithPackedVector2ListArgRetDictionary(makeAtlasBind, geometry2DSingleton, sizes)
}

/**
 * Returns the Bresenham line (https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm) between
 * the `from` and `to` points. A Bresenham line is a series of pixels that draws a line and is
 * always 1-pixel thick on every row and column of the drawing (never more, never less). Example
 * code to draw a line between two `Marker2D` nodes using a series of `CanvasItem.draw_rect` calls:
 *
 * Generated from Godot docs: Geometry2D.bresenham_line
 */
fun Geometry2D.bresenhamLine(from: Vector2i, to: Vector2i): List<Vector2i> {
    return ObjectCalls.ptrcallWithTwoVector2iArgsRetVector2iList(bresenhamLineBind, geometry2DSingleton, from, to)
}

private val geometry2DSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("Geometry2D")
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
