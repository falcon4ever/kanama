package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * Triangle geometry for efficient, physicsless intersection queries.
 *
 * Generated from Godot docs: TriangleMesh
 */
class TriangleMesh(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Creates the BVH tree from an array of faces. Each 3 vertices of the input `faces` array
     * represent one triangle (face). Returns `true` if the tree is successfully built, `false`
     * otherwise.
     *
     * Generated from Godot docs: TriangleMesh.create_from_faces
     */
    fun createFromFaces(faces: List<Vector3>): Boolean {
        return ObjectCalls.ptrcallWithPackedVector3ListArgRetBool(createFromFacesBind, handle, faces)
    }

    /**
     * Returns a copy of the geometry faces. Each 3 vertices of the array represent one triangle
     * (face).
     *
     * Generated from Godot docs: TriangleMesh.get_faces
     */
    fun getFaces(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getFacesBind, handle)
    }

    /**
     * Tests for intersection with a segment going from `begin` to `end`. If an intersection with a
     * triangle happens returns a `Dictionary` with the following fields: `position`: The position on
     * the intersected triangle. `normal`: The normal of the intersected triangle. `face_index`: The
     * index of the intersected triangle. Returns an empty `Dictionary` if no intersection happens. See
     * also `intersect_ray`, which is similar but uses an infinite-length ray.
     *
     * Generated from Godot docs: TriangleMesh.intersect_segment
     */
    fun intersectSegment(begin: Vector3, end: Vector3): Map<String, Any?> {
        return ObjectCalls.ptrcallWithTwoVector3ArgsRetDictionary(intersectSegmentBind, handle, begin, end)
    }

    /**
     * Tests for intersection with a ray starting at `begin` and facing `dir` and extending toward
     * infinity. If an intersection with a triangle happens, returns a `Dictionary` with the following
     * fields: `position`: The position on the intersected triangle. `normal`: The normal of the
     * intersected triangle. `face_index`: The index of the intersected triangle. Returns an empty
     * `Dictionary` if no intersection happens. See also `intersect_segment`, which is similar but uses
     * a finite-length segment.
     *
     * Generated from Godot docs: TriangleMesh.intersect_ray
     */
    fun intersectRay(begin: Vector3, dir: Vector3): Map<String, Any?> {
        return ObjectCalls.ptrcallWithTwoVector3ArgsRetDictionary(intersectRayBind, handle, begin, dir)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TriangleMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TriangleMesh? =
            if (handle.address() == 0L) null else TriangleMesh(handle)

        private const val CREATE_FROM_FACES_HASH = 2637816732L
        private val createFromFacesBind by lazy {
            ObjectCalls.getMethodBind("TriangleMesh", "create_from_faces", CREATE_FROM_FACES_HASH)
        }

        private const val GET_FACES_HASH = 497664490L
        private val getFacesBind by lazy {
            ObjectCalls.getMethodBind("TriangleMesh", "get_faces", GET_FACES_HASH)
        }

        private const val INTERSECT_SEGMENT_HASH = 3648293151L
        private val intersectSegmentBind by lazy {
            ObjectCalls.getMethodBind("TriangleMesh", "intersect_segment", INTERSECT_SEGMENT_HASH)
        }

        private const val INTERSECT_RAY_HASH = 3648293151L
        private val intersectRayBind by lazy {
            ObjectCalls.getMethodBind("TriangleMesh", "intersect_ray", INTERSECT_RAY_HASH)
        }
    }
}
