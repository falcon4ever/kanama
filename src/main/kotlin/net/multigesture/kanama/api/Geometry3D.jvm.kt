package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for Geometry3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Geometry3D waits on: ptrcallWithFiveVector3ArgsRetVariantScalar,
//   ptrcallWithFourVector3ArgsRetPackedVector3List,
//   ptrcallWithPackedVector3ListAndPlaneArgRetPackedVector3List,
//   ptrcallWithPackedVector3ListArgRetPackedInt32List, ptrcallWithPlaneListArgRetPackedVector3List,
//   ptrcallWithThreeVector3DoubleArgsRetPackedVector3List,
//   ptrcallWithTwoDoubleIntLongArgsRetPlaneList, ptrcallWithTwoDoubleTwoIntLongArgsRetPlaneList,
//   ptrcallWithTwoVector3PlaneListArgsRetPackedVector3List,
//   ptrcallWithTwoVector3TwoDoubleArgsRetPackedVector3List, ptrcallWithVector3ArgRetPlaneList
// Index: docs/contributing/ios-shape-gap.md

/**
 * Calculates and returns all the vertex points of a convex shape defined by an array of `planes`.
 *
 * Generated from Godot docs: Geometry3D.compute_convex_mesh_points
 */
fun Geometry3D.computeConvexMeshPoints(planes: List<Plane>): List<Vector3> {
    return ObjectCalls.ptrcallWithPlaneListArgRetPackedVector3List(computeConvexMeshPointsBind, geometry3DSingleton, planes)
}

/**
 * Returns an array with 6 `Plane`s that describe the sides of a box centered at the origin. The
 * box size is defined by `extents`, which represents one (positive) corner of the box (i.e. half
 * its actual size).
 *
 * Generated from Godot docs: Geometry3D.build_box_planes
 */
fun Geometry3D.buildBoxPlanes(extents: Vector3): List<Plane> {
    return ObjectCalls.ptrcallWithVector3ArgRetPlaneList(buildBoxPlanesBind, geometry3DSingleton, extents)
}

/**
 * Returns an array of `Plane`s closely bounding a faceted cylinder centered at the origin with
 * radius `radius` and height `height`. The parameter `sides` defines how many planes will be
 * generated for the round part of the cylinder. The parameter `axis` describes the axis along
 * which the cylinder is oriented (0 for X, 1 for Y, 2 for Z).
 *
 * Generated from Godot docs: Geometry3D.build_cylinder_planes
 */
fun Geometry3D.buildCylinderPlanes(radius: Double, height: Double, sides: Int, axis: Long = 2L): List<Plane> {
    return ObjectCalls.ptrcallWithTwoDoubleIntLongArgsRetPlaneList(buildCylinderPlanesBind, geometry3DSingleton, radius, height, sides, axis)
}

/**
 * Returns an array of `Plane`s closely bounding a faceted capsule centered at the origin with
 * radius `radius` and height `height`. The parameter `sides` defines how many planes will be
 * generated for the side part of the capsule, whereas `lats` gives the number of latitudinal steps
 * at the bottom and top of the capsule. The parameter `axis` describes the axis along which the
 * capsule is oriented (0 for X, 1 for Y, 2 for Z).
 *
 * Generated from Godot docs: Geometry3D.build_capsule_planes
 */
fun Geometry3D.buildCapsulePlanes(radius: Double, height: Double, sides: Int, lats: Int, axis: Long = 2L): List<Plane> {
    return ObjectCalls.ptrcallWithTwoDoubleTwoIntLongArgsRetPlaneList(buildCapsulePlanesBind, geometry3DSingleton, radius, height, sides, lats, axis)
}

/**
 * Given the two 3D segments (`p1`, `p2`) and (`q1`, `q2`), finds those two points on the two
 * segments that are closest to each other. Returns a `PackedVector3Array` that contains this point
 * on (`p1`, `p2`) as well the accompanying point on (`q1`, `q2`).
 *
 * Generated from Godot docs: Geometry3D.get_closest_points_between_segments
 */
fun Geometry3D.getClosestPointsBetweenSegments(p1: Vector3, p2: Vector3, q1: Vector3, q2: Vector3): List<Vector3> {
    return ObjectCalls.ptrcallWithFourVector3ArgsRetPackedVector3List(getClosestPointsBetweenSegmentsBind, geometry3DSingleton, p1, p2, q1, q2)
}

/**
 * Tests if the 3D ray starting at `from` with the direction of `dir` intersects the triangle
 * specified by `a`, `b` and `c`. If yes, returns the point of intersection as `Vector3`. If no
 * intersection takes place, returns `null`.
 *
 * Generated from Godot docs: Geometry3D.ray_intersects_triangle
 */
fun Geometry3D.rayIntersectsTriangle(from: Vector3, dir: Vector3, a: Vector3, b: Vector3, c: Vector3): Any? {
    return ObjectCalls.ptrcallWithFiveVector3ArgsRetVariantScalar(rayIntersectsTriangleBind, geometry3DSingleton, from, dir, a, b, c)
}

/**
 * Tests if the segment (`from`, `to`) intersects the triangle `a`, `b`, `c`. If yes, returns the
 * point of intersection as `Vector3`. If no intersection takes place, returns `null`.
 *
 * Generated from Godot docs: Geometry3D.segment_intersects_triangle
 */
fun Geometry3D.segmentIntersectsTriangle(from: Vector3, to: Vector3, a: Vector3, b: Vector3, c: Vector3): Any? {
    return ObjectCalls.ptrcallWithFiveVector3ArgsRetVariantScalar(segmentIntersectsTriangleBind, geometry3DSingleton, from, to, a, b, c)
}

/**
 * Checks if the segment (`from`, `to`) intersects the sphere that is located at `sphere_position`
 * and has radius `sphere_radius`. If no, returns an empty `PackedVector3Array`. If yes, returns a
 * `PackedVector3Array` containing the point of intersection and the sphere's normal at the point
 * of intersection.
 *
 * Generated from Godot docs: Geometry3D.segment_intersects_sphere
 */
fun Geometry3D.segmentIntersectsSphere(from: Vector3, to: Vector3, spherePosition: Vector3, sphereRadius: Double): List<Vector3> {
    return ObjectCalls.ptrcallWithThreeVector3DoubleArgsRetPackedVector3List(segmentIntersectsSphereBind, geometry3DSingleton, from, to, spherePosition, sphereRadius)
}

/**
 * Checks if the segment (`from`, `to`) intersects the cylinder with height `height` that is
 * centered at the origin and has radius `radius`. If no, returns an empty `PackedVector3Array`. If
 * an intersection takes place, the returned array contains the point of intersection and the
 * cylinder's normal at the point of intersection.
 *
 * Generated from Godot docs: Geometry3D.segment_intersects_cylinder
 */
fun Geometry3D.segmentIntersectsCylinder(from: Vector3, to: Vector3, height: Double, radius: Double): List<Vector3> {
    return ObjectCalls.ptrcallWithTwoVector3TwoDoubleArgsRetPackedVector3List(segmentIntersectsCylinderBind, geometry3DSingleton, from, to, height, radius)
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

/**
 * Tetrahedralizes the volume specified by a discrete set of `points` in 3D space, ensuring that no
 * point lies within the circumsphere of any resulting tetrahedron. The method returns a
 * `PackedInt32Array` where each tetrahedron consists of four consecutive point indices into the
 * `points` array (resulting in an array with `n * 4` elements, where `n` is the number of
 * tetrahedra found). If the tetrahedralization is unsuccessful, an empty `PackedInt32Array` is
 * returned.
 *
 * Generated from Godot docs: Geometry3D.tetrahedralize_delaunay
 */
fun Geometry3D.tetrahedralizeDelaunay(points: List<Vector3>): List<Int> {
    return ObjectCalls.ptrcallWithPackedVector3ListArgRetPackedInt32List(tetrahedralizeDelaunayBind, geometry3DSingleton, points)
}

private val geometry3DSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("Geometry3D")
}

private const val COMPUTE_CONVEX_MESH_POINTS_HASH = 1936902142L
private val computeConvexMeshPointsBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "compute_convex_mesh_points", COMPUTE_CONVEX_MESH_POINTS_HASH)
}

private const val BUILD_BOX_PLANES_HASH = 3622277145L
private val buildBoxPlanesBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "build_box_planes", BUILD_BOX_PLANES_HASH)
}

private const val BUILD_CYLINDER_PLANES_HASH = 449920067L
private val buildCylinderPlanesBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "build_cylinder_planes", BUILD_CYLINDER_PLANES_HASH)
}

private const val BUILD_CAPSULE_PLANES_HASH = 2113592876L
private val buildCapsulePlanesBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "build_capsule_planes", BUILD_CAPSULE_PLANES_HASH)
}

private const val GET_CLOSEST_POINTS_BETWEEN_SEGMENTS_HASH = 1056373962L
private val getClosestPointsBetweenSegmentsBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "get_closest_points_between_segments", GET_CLOSEST_POINTS_BETWEEN_SEGMENTS_HASH)
}

private const val RAY_INTERSECTS_TRIANGLE_HASH = 1718655448L
private val rayIntersectsTriangleBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "ray_intersects_triangle", RAY_INTERSECTS_TRIANGLE_HASH)
}

private const val SEGMENT_INTERSECTS_TRIANGLE_HASH = 1718655448L
private val segmentIntersectsTriangleBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "segment_intersects_triangle", SEGMENT_INTERSECTS_TRIANGLE_HASH)
}

private const val SEGMENT_INTERSECTS_SPHERE_HASH = 4080141172L
private val segmentIntersectsSphereBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "segment_intersects_sphere", SEGMENT_INTERSECTS_SPHERE_HASH)
}

private const val SEGMENT_INTERSECTS_CYLINDER_HASH = 2361316491L
private val segmentIntersectsCylinderBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "segment_intersects_cylinder", SEGMENT_INTERSECTS_CYLINDER_HASH)
}

private const val SEGMENT_INTERSECTS_CONVEX_HASH = 537425332L
private val segmentIntersectsConvexBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "segment_intersects_convex", SEGMENT_INTERSECTS_CONVEX_HASH)
}

private const val CLIP_POLYGON_HASH = 2603188319L
private val clipPolygonBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "clip_polygon", CLIP_POLYGON_HASH)
}

private const val TETRAHEDRALIZE_DELAUNAY_HASH = 1230191221L
private val tetrahedralizeDelaunayBind by lazy {
    ObjectCalls.getMethodBind("Geometry3D", "tetrahedralize_delaunay", TETRAHEDRALIZE_DELAUNAY_HASH)
}
