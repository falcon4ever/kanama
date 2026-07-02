package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * Occluder shape resource for use with occlusion culling in `OccluderInstance3D`.
 *
 * Generated from Godot docs: Occluder3D
 */
open class Occluder3D(handle: MemorySegment) : Resource(handle) {
    fun getVertices(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getVerticesBind, handle)
    }

    fun getIndices(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getIndicesBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Occluder3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Occluder3D? =
            if (handle.address() == 0L) null else Occluder3D(handle)

        private const val GET_VERTICES_HASH = 497664490L
        private val getVerticesBind by lazy {
            ObjectCalls.getMethodBind("Occluder3D", "get_vertices", GET_VERTICES_HASH)
        }

        private const val GET_INDICES_HASH = 1930428628L
        private val getIndicesBind by lazy {
            ObjectCalls.getMethodBind("Occluder3D", "get_indices", GET_INDICES_HASH)
        }
    }
}
