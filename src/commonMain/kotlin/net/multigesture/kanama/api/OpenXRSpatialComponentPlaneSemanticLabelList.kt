package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRSpatialComponentPlaneSemanticLabelList
 */
class OpenXRSpatialComponentPlaneSemanticLabelList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getPlaneSemanticLabel(index: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(getPlaneSemanticLabelBind, segment, index)
    }

    companion object {
        const val PLANE_SEMANTIC_LABEL_UNCATEGORIZED: Long = 1L
        const val PLANE_SEMANTIC_LABEL_FLOOR: Long = 2L
        const val PLANE_SEMANTIC_LABEL_WALL: Long = 3L
        const val PLANE_SEMANTIC_LABEL_CEILING: Long = 4L
        const val PLANE_SEMANTIC_LABEL_TABLE: Long = 5L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentPlaneSemanticLabelList? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialComponentPlaneSemanticLabelList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentPlaneSemanticLabelList(GodotHandle(handle))

        private const val GET_PLANE_SEMANTIC_LABEL_HASH = 1889332427L
        private val getPlaneSemanticLabelBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPlaneSemanticLabelList", "get_plane_semantic_label", GET_PLANE_SEMANTIC_LABEL_HASH)
        }
    }
}
