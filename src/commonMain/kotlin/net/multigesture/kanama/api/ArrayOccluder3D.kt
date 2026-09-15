package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
 * 3D polygon shape for use with occlusion culling in `OccluderInstance3D`.
 *
 * Generated from Godot docs: ArrayOccluder3D
 */
class ArrayOccluder3D(handle: GodotHandle) : Occluder3D(handle) {
    /**
     * Sets `indices` and `vertices`, while updating the final occluder only once after both values are
     * set.
     *
     * Generated from Godot docs: ArrayOccluder3D.set_arrays
     */
    fun setArrays(vertices: List<Vector3>, indices: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedVector3ListAndPackedInt32ListArgs(setArraysBind, segment, vertices, indices)
    }

    /**
     * The occluder's vertex positions in local 3D coordinates. Note: The occluder is always updated
     * after setting this value. If creating occluders procedurally, consider using `set_arrays`
     * instead to avoid updating the occluder twice when it's created.
     *
     * Generated from Godot docs: ArrayOccluder3D.set_vertices
     */
    fun setVertices(vertices: List<Vector3>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedVector3ListArg(setVerticesBind, segment, vertices)
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
        checkOpen()
        ObjectCalls.ptrcallWithPackedInt32ListArg(setIndicesBind, segment, indices)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ArrayOccluder3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ArrayOccluder3D? =
            if (handle.address() == 0L) null else ArrayOccluder3D(GodotHandle(handle))

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
