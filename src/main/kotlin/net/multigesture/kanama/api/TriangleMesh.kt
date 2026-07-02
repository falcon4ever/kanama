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
    fun createFromFaces(faces: List<Vector3>): Boolean {
        return ObjectCalls.ptrcallWithPackedVector3ListArgRetBool(createFromFacesBind, handle, faces)
    }

    fun getFaces(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getFacesBind, handle)
    }

    fun intersectSegment(begin: Vector3, end: Vector3): Map<String, Any?> {
        return ObjectCalls.ptrcallWithTwoVector3ArgsRetDictionary(intersectSegmentBind, handle, begin, end)
    }

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
