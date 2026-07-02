package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRSpatialComponentPlaneSemanticLabelList
 */
class OpenXRSpatialComponentPlaneSemanticLabelList(handle: MemorySegment) : OpenXRSpatialComponentData(handle) {
    fun getPlaneSemanticLabel(index: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(getPlaneSemanticLabelBind, handle, index)
    }

    companion object {
        const val PLANE_SEMANTIC_LABEL_UNCATEGORIZED: Long = 1L
        const val PLANE_SEMANTIC_LABEL_FLOOR: Long = 2L
        const val PLANE_SEMANTIC_LABEL_WALL: Long = 3L
        const val PLANE_SEMANTIC_LABEL_CEILING: Long = 4L
        const val PLANE_SEMANTIC_LABEL_TABLE: Long = 5L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialComponentPlaneSemanticLabelList? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialComponentPlaneSemanticLabelList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentPlaneSemanticLabelList(handle)

        private const val GET_PLANE_SEMANTIC_LABEL_HASH = 1889332427L
        private val getPlaneSemanticLabelBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPlaneSemanticLabelList", "get_plane_semantic_label", GET_PLANE_SEMANTIC_LABEL_HASH)
        }
    }
}
