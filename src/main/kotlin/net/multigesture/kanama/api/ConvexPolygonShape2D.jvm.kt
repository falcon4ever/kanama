package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for ConvexPolygonShape2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ConvexPolygonShape2D waits on: ptrcallWithPackedVector2ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Based on the set of points provided, this assigns the `points` property using the convex hull
 * algorithm, removing all unneeded points. See `Geometry2D.convex_hull` for details.
 *
 * Generated from Godot docs: ConvexPolygonShape2D.set_point_cloud
 */
fun ConvexPolygonShape2D.setPointCloud(pointCloud: List<Vector2>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListArg(setPointCloudBind, handle, pointCloud)
}

/**
 * The polygon's list of vertices that form a convex hull. Can be in either clockwise or
 * counterclockwise order. Warning: Only set this property to a list of points that actually form a
 * convex hull. Use `set_point_cloud` to generate the convex hull of an arbitrary set of points.
 *
 * Generated from Godot docs: ConvexPolygonShape2D.set_points
 */
fun ConvexPolygonShape2D.setPoints(points: List<Vector2>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListArg(setPointsBind, handle, points)
}

private const val SET_POINT_CLOUD_HASH = 1509147220L
private val setPointCloudBind by lazy {
    ObjectCalls.getMethodBind("ConvexPolygonShape2D", "set_point_cloud", SET_POINT_CLOUD_HASH)
}

private const val SET_POINTS_HASH = 1509147220L
private val setPointsBind by lazy {
    ObjectCalls.getMethodBind("ConvexPolygonShape2D", "set_points", SET_POINTS_HASH)
}
