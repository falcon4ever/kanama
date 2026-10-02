package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRSpatialComponentPlaneSemanticLabelList
 */
class OpenXRSpatialComponentPlaneSemanticLabelList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getPlaneSemanticLabel(index: Long): OpenXRSpatialComponentPlaneSemanticLabelList.PlaneSemanticLabel {
        checkOpen()
        return OpenXRSpatialComponentPlaneSemanticLabelList.PlaneSemanticLabel(ObjectCalls.ptrcallWithLongArgRetLong(getPlaneSemanticLabelBind, segment, index))
    }

    @JvmInline
    value class PlaneSemanticLabel(override val value: Long) : GodotEnumValue {
        companion object {
            val UNCATEGORIZED: PlaneSemanticLabel get() = PlaneSemanticLabel(1L)
            val FLOOR: PlaneSemanticLabel get() = PlaneSemanticLabel(2L)
            val WALL: PlaneSemanticLabel get() = PlaneSemanticLabel(3L)
            val CEILING: PlaneSemanticLabel get() = PlaneSemanticLabel(4L)
            val TABLE: PlaneSemanticLabel get() = PlaneSemanticLabel(5L)
        }
    }

    companion object {
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
