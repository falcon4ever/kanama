package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRSpatialComponentPlaneAlignmentList
 */
class OpenXRSpatialComponentPlaneAlignmentList(handle: MemorySegment) : OpenXRSpatialComponentData(handle) {
    fun getPlaneAlignment(index: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(getPlaneAlignmentBind, handle, index)
    }

    companion object {
        const val PLANE_ALIGNMENT_HORIZONTAL_UPWARD: Long = 0L
        const val PLANE_ALIGNMENT_HORIZONTAL_DOWNWARD: Long = 1L
        const val PLANE_ALIGNMENT_VERTICAL: Long = 2L
        const val PLANE_ALIGNMENT_ARBITRARY: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialComponentPlaneAlignmentList? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialComponentPlaneAlignmentList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentPlaneAlignmentList(handle)

        private const val GET_PLANE_ALIGNMENT_HASH = 3340200270L
        private val getPlaneAlignmentBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPlaneAlignmentList", "get_plane_alignment", GET_PLANE_ALIGNMENT_HASH)
        }
    }
}
