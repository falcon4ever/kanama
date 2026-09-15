package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * Occluder shape resource for use with occlusion culling in `OccluderInstance3D`.
 *
 * Generated from Godot docs: Occluder3D
 */
open class Occluder3D(handle: GodotHandle) : Resource(handle) {
    /**
     * Returns the occluder shape's vertex positions.
     *
     * Generated from Godot docs: Occluder3D.get_vertices
     */
    fun getVertices(): List<Vector3> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getVerticesBind, segment)
    }

    /**
     * Returns the occluder shape's vertex indices.
     *
     * Generated from Godot docs: Occluder3D.get_indices
     */
    fun getIndices(): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getIndicesBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Occluder3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Occluder3D? =
            if (handle.address() == 0L) null else Occluder3D(GodotHandle(handle))

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
