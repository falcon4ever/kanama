package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
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

    @JvmStatic
    fun fromHandle(handle: MemorySegment): Geometry3D? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): Geometry3D? =
        if (handle.address() == 0L) null else this

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
}
