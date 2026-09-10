package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
 * Triangle geometry for efficient, physicsless intersection queries.
 *
 * Generated from Godot docs: TriangleMesh
 */
class TriangleMesh(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns a copy of the geometry faces. Each 3 vertices of the array represent one triangle
     * (face).
     *
     * Generated from Godot docs: TriangleMesh.get_faces
     */
    fun getFaces(): List<Vector3> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getFacesBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TriangleMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TriangleMesh? =
            if (handle.address() == 0L) null else TriangleMesh(handle)

        private const val GET_FACES_HASH = 497664490L
        private val getFacesBind by lazy {
            ObjectCalls.getMethodBind("TriangleMesh", "get_faces", GET_FACES_HASH)
        }
    }
}
