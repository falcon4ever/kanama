package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Plane
import net.multigesture.kanama.types.Vector3

/**
 * Provides methods for some common 3D geometric operations.
 *
 * Generated from Godot docs: Geometry3D
 */
object Geometry3D {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("Geometry3D")
    }

    /**
     * Calculates and returns all the vertex points of a convex shape defined by an array of `planes`.
     *
     * Generated from Godot docs: Geometry3D.compute_convex_mesh_points
     */
    @JvmStatic
    fun computeConvexMeshPoints(planes: List<Plane>): List<Vector3> {
        return ObjectCalls.ptrcallWithPlaneListArgRetPackedVector3List(computeConvexMeshPointsBind, singleton, planes)
    }

    /**
     * Returns an array with 6 `Plane`s that describe the sides of a box centered at the origin. The
     * box size is defined by `extents`, which represents one (positive) corner of the box (i.e. half
     * its actual size).
     *
     * Generated from Godot docs: Geometry3D.build_box_planes
     */
    @JvmStatic
    fun buildBoxPlanes(extents: Vector3): List<Plane> {
        return ObjectCalls.ptrcallWithVector3ArgRetPlaneList(buildBoxPlanesBind, singleton, extents)
    }

    /**
     * Returns an array of `Plane`s closely bounding a faceted cylinder centered at the origin with
     * radius `radius` and height `height`. The parameter `sides` defines how many planes will be
     * generated for the round part of the cylinder. The parameter `axis` describes the axis along
     * which the cylinder is oriented (0 for X, 1 for Y, 2 for Z).
     *
     * Generated from Godot docs: Geometry3D.build_cylinder_planes
     */
    @JvmStatic
    fun buildCylinderPlanes(radius: Double, height: Double, sides: Int, axis: Long = 2L): List<Plane> {
        return ObjectCalls.ptrcallWithTwoDoubleIntLongArgsRetPlaneList(buildCylinderPlanesBind, singleton, radius, height, sides, axis)
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
    @JvmStatic
    fun buildCapsulePlanes(radius: Double, height: Double, sides: Int, lats: Int, axis: Long = 2L): List<Plane> {
        return ObjectCalls.ptrcallWithTwoDoubleTwoIntLongArgsRetPlaneList(buildCapsulePlanesBind, singleton, radius, height, sides, lats, axis)
    }

    /**
     * Given the two 3D segments (`p1`, `p2`) and (`q1`, `q2`), finds those two points on the two
     * segments that are closest to each other. Returns a `PackedVector3Array` that contains this point
     * on (`p1`, `p2`) as well the accompanying point on (`q1`, `q2`).
     *
     * Generated from Godot docs: Geometry3D.get_closest_points_between_segments
     */
    @JvmStatic
    fun getClosestPointsBetweenSegments(p1: Vector3, p2: Vector3, q1: Vector3, q2: Vector3): List<Vector3> {
        return ObjectCalls.ptrcallWithFourVector3ArgsRetPackedVector3List(getClosestPointsBetweenSegmentsBind, singleton, p1, p2, q1, q2)
    }

    /**
     * Returns the 3D point on the 3D segment (`s1`, `s2`) that is closest to `point`. The returned
     * point will always be inside the specified segment.
     *
     * Generated from Godot docs: Geometry3D.get_closest_point_to_segment
     */
    @JvmStatic
    fun getClosestPointToSegment(point: Vector3, s1: Vector3, s2: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithThreeVector3ArgsRetVector3(getClosestPointToSegmentBind, singleton, point, s1, s2)
    }

    /**
     * Returns the 3D point on the 3D line defined by (`s1`, `s2`) that is closest to `point`. The
     * returned point can be inside the segment (`s1`, `s2`) or outside of it, i.e. somewhere on the
     * line extending from the segment.
     *
     * Generated from Godot docs: Geometry3D.get_closest_point_to_segment_uncapped
     */
    @JvmStatic
    fun getClosestPointToSegmentUncapped(point: Vector3, s1: Vector3, s2: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithThreeVector3ArgsRetVector3(getClosestPointToSegmentUncappedBind, singleton, point, s1, s2)
    }

    /**
     * Returns a `Vector3` containing weights based on how close a 3D position (`point`) is to a
     * triangle's different vertices (`a`, `b` and `c`). This is useful for interpolating between the
     * data of different vertices in a triangle. One example use case is using this to smoothly rotate
     * over a mesh instead of relying solely on face normals. Here is a more detailed explanation of
     * barycentric coordinates. (https://en.wikipedia.org/wiki/Barycentric_coordinate_system)
     *
     * Generated from Godot docs: Geometry3D.get_triangle_barycentric_coords
     */
    @JvmStatic
    fun getTriangleBarycentricCoords(point: Vector3, a: Vector3, b: Vector3, c: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithFourVector3ArgsRetVector3(getTriangleBarycentricCoordsBind, singleton, point, a, b, c)
    }

    /**
     * Tests if the 3D ray starting at `from` with the direction of `dir` intersects the triangle
     * specified by `a`, `b` and `c`. If yes, returns the point of intersection as `Vector3`. If no
     * intersection takes place, returns `null`.
     *
     * Generated from Godot docs: Geometry3D.ray_intersects_triangle
     */
    @JvmStatic
    fun rayIntersectsTriangle(from: Vector3, dir: Vector3, a: Vector3, b: Vector3, c: Vector3): Any? {
        return ObjectCalls.ptrcallWithFiveVector3ArgsRetVariantScalar(rayIntersectsTriangleBind, singleton, from, dir, a, b, c)
    }

    /**
     * Tests if the segment (`from`, `to`) intersects the triangle `a`, `b`, `c`. If yes, returns the
     * point of intersection as `Vector3`. If no intersection takes place, returns `null`.
     *
     * Generated from Godot docs: Geometry3D.segment_intersects_triangle
     */
    @JvmStatic
    fun segmentIntersectsTriangle(from: Vector3, to: Vector3, a: Vector3, b: Vector3, c: Vector3): Any? {
        return ObjectCalls.ptrcallWithFiveVector3ArgsRetVariantScalar(segmentIntersectsTriangleBind, singleton, from, to, a, b, c)
    }

    /**
     * Checks if the segment (`from`, `to`) intersects the sphere that is located at `sphere_position`
     * and has radius `sphere_radius`. If no, returns an empty `PackedVector3Array`. If yes, returns a
     * `PackedVector3Array` containing the point of intersection and the sphere's normal at the point
     * of intersection.
     *
     * Generated from Godot docs: Geometry3D.segment_intersects_sphere
     */
    @JvmStatic
    fun segmentIntersectsSphere(from: Vector3, to: Vector3, spherePosition: Vector3, sphereRadius: Double): List<Vector3> {
        return ObjectCalls.ptrcallWithThreeVector3DoubleArgsRetPackedVector3List(segmentIntersectsSphereBind, singleton, from, to, spherePosition, sphereRadius)
    }

    /**
     * Checks if the segment (`from`, `to`) intersects the cylinder with height `height` that is
     * centered at the origin and has radius `radius`. If no, returns an empty `PackedVector3Array`. If
     * an intersection takes place, the returned array contains the point of intersection and the
     * cylinder's normal at the point of intersection.
     *
     * Generated from Godot docs: Geometry3D.segment_intersects_cylinder
     */
    @JvmStatic
    fun segmentIntersectsCylinder(from: Vector3, to: Vector3, height: Double, radius: Double): List<Vector3> {
        return ObjectCalls.ptrcallWithTwoVector3TwoDoubleArgsRetPackedVector3List(segmentIntersectsCylinderBind, singleton, from, to, height, radius)
    }

    /**
     * Given a convex hull defined though the `Plane`s in the array `planes`, tests if the segment
     * (`from`, `to`) intersects with that hull. If an intersection is found, returns a
     * `PackedVector3Array` containing the point the intersection and the hull's normal. Otherwise,
     * returns an empty array.
     *
     * Generated from Godot docs: Geometry3D.segment_intersects_convex
     */
    @JvmStatic
    fun segmentIntersectsConvex(from: Vector3, to: Vector3, planes: List<Plane>): List<Vector3> {
        return ObjectCalls.ptrcallWithTwoVector3PlaneListArgsRetPackedVector3List(segmentIntersectsConvexBind, singleton, from, to, planes)
    }

    /**
     * Clips the polygon defined by the points in `points` against the `plane` and returns the points
     * of the clipped polygon.
     *
     * Generated from Godot docs: Geometry3D.clip_polygon
     */
    @JvmStatic
    fun clipPolygon(points: List<Vector3>, plane: Plane): List<Vector3> {
        return ObjectCalls.ptrcallWithPackedVector3ListAndPlaneArgRetPackedVector3List(clipPolygonBind, singleton, points, plane)
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
    @JvmStatic
    fun tetrahedralizeDelaunay(points: List<Vector3>): List<Int> {
        return ObjectCalls.ptrcallWithPackedVector3ListArgRetPackedInt32List(tetrahedralizeDelaunayBind, singleton, points)
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): Geometry3D? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): Geometry3D? =
        if (handle.address() == 0L) null else this

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

    private const val GET_CLOSEST_POINT_TO_SEGMENT_HASH = 2168193209L
    private val getClosestPointToSegmentBind by lazy {
        ObjectCalls.getMethodBind("Geometry3D", "get_closest_point_to_segment", GET_CLOSEST_POINT_TO_SEGMENT_HASH)
    }

    private const val GET_CLOSEST_POINT_TO_SEGMENT_UNCAPPED_HASH = 2168193209L
    private val getClosestPointToSegmentUncappedBind by lazy {
        ObjectCalls.getMethodBind("Geometry3D", "get_closest_point_to_segment_uncapped", GET_CLOSEST_POINT_TO_SEGMENT_UNCAPPED_HASH)
    }

    private const val GET_TRIANGLE_BARYCENTRIC_COORDS_HASH = 1362048029L
    private val getTriangleBarycentricCoordsBind by lazy {
        ObjectCalls.getMethodBind("Geometry3D", "get_triangle_barycentric_coords", GET_TRIANGLE_BARYCENTRIC_COORDS_HASH)
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
}
