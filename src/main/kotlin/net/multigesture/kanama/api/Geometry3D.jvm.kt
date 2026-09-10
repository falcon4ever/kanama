package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for Geometry3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Geometry3D waits on: ptrcallWithPackedVector3ListAndPlaneArgRetPackedVector3List,
//   ptrcallWithPlaneListArgRetPackedVector3List,
//   ptrcallWithTwoVector3PlaneListArgsRetPackedVector3List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Calculates and returns all the vertex points of a convex shape defined by an array of `planes`.
 *
 * Generated from Godot docs: Geometry3D.compute_convex_mesh_points
 */
fun Geometry3D.computeConvexMeshPoints(planes: List<Plane>): List<Vector3> {
    return ObjectCalls.ptrcallWithPlaneListArgRetPackedVector3List(computeConvexMeshPointsBind, geometry3DSingleton, planes)
}

/**
 * Given a convex hull defined though the `Plane`s in the array `planes`, tests if the segment
 * (`from`, `to`) intersects with that hull. If an intersection is found, returns a
 * `PackedVector3Array` containing the point the intersection and the hull's normal. Otherwise,
 * returns an empty array.
 *
 * Generated from Godot docs: Geometry3D.segment_intersects_convex
 */
fun Geometry3D.segmentIntersectsConvex(from: Vector3, to: Vector3, planes: List<Plane>): List<Vector3> {
    return ObjectCalls.ptrcallWithTwoVector3PlaneListArgsRetPackedVector3List(segmentIntersectsConvexBind, geometry3DSingleton, from, to, planes)
}

/**
 * Clips the polygon defined by the points in `points` against the `plane` and returns the points
 * of the clipped polygon.
 *
 * Generated from Godot docs: Geometry3D.clip_polygon
 */
fun Geometry3D.clipPolygon(points: List<Vector3>, plane: Plane): List<Vector3> {
    return ObjectCalls.ptrcallWithPackedVector3ListAndPlaneArgRetPackedVector3List(clipPolygonBind, geometry3DSingleton, points, plane)
}

private val geometry3DSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("Geometry3D")
}

private const val COMPUTE_CONVEX_MESH_POINTS_HASH = 1936902142L
private val computeConvexMeshPointsBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "compute_convex_mesh_points", COMPUTE_CONVEX_MESH_POINTS_HASH)
}

private const val SEGMENT_INTERSECTS_CONVEX_HASH = 537425332L
private val segmentIntersectsConvexBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "segment_intersects_convex", SEGMENT_INTERSECTS_CONVEX_HASH)
}

private const val CLIP_POLYGON_HASH = 2603188319L
private val clipPolygonBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "clip_polygon", CLIP_POLYGON_HASH)
}
