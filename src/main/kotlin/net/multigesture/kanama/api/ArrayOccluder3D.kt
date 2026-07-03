package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * 3D polygon shape for use with occlusion culling in `OccluderInstance3D`.
 *
 * Generated from Godot docs: ArrayOccluder3D
 */
class ArrayOccluder3D(handle: MemorySegment) : Occluder3D(handle) {
    /**
     * Sets `indices` and `vertices`, while updating the final occluder only once after both values are
     * set.
     *
     * Generated from Godot docs: ArrayOccluder3D.set_arrays
     */
    fun setArrays(vertices: List<Vector3>, indices: List<Int>) {
        ObjectCalls.ptrcallWithPackedVector3ListAndPackedInt32ListArgs(setArraysBind, handle, vertices, indices)
    }

    /**
     * The occluder's vertex positions in local 3D coordinates. Note: The occluder is always updated
     * after setting this value. If creating occluders procedurally, consider using `set_arrays`
     * instead to avoid updating the occluder twice when it's created.
     *
     * Generated from Godot docs: ArrayOccluder3D.set_vertices
     */
    fun setVertices(vertices: List<Vector3>) {
        ObjectCalls.ptrcallWithPackedVector3ListArg(setVerticesBind, handle, vertices)
    }

    /**
     * The occluder's index position. Indices determine which points from the `vertices` array should
     * be drawn, and in which order. Note: The occluder is always updated after setting this value. If
     * creating occluders procedurally, consider using `set_arrays` instead to avoid updating the
     * occluder twice when it's created.
     *
     * Generated from Godot docs: ArrayOccluder3D.set_indices
     */
    fun setIndices(indices: List<Int>) {
        ObjectCalls.ptrcallWithPackedInt32ListArg(setIndicesBind, handle, indices)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): ArrayOccluder3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ArrayOccluder3D? =
            if (handle.address() == 0L) null else ArrayOccluder3D(handle)

        private const val SET_ARRAYS_HASH = 3233972621L
        private val setArraysBind by lazy {
            ObjectCalls.getMethodBind("ArrayOccluder3D", "set_arrays", SET_ARRAYS_HASH)
        }

        private const val SET_VERTICES_HASH = 334873810L
        private val setVerticesBind by lazy {
            ObjectCalls.getMethodBind("ArrayOccluder3D", "set_vertices", SET_VERTICES_HASH)
        }

        private const val SET_INDICES_HASH = 3614634198L
        private val setIndicesBind by lazy {
            ObjectCalls.getMethodBind("ArrayOccluder3D", "set_indices", SET_INDICES_HASH)
        }
    }
}
