package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRSpatialComponentPlaneAlignmentList
 */
class OpenXRSpatialComponentPlaneAlignmentList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getPlaneAlignment(index: Long): OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment {
        checkOpen()
        return OpenXRSpatialComponentPlaneAlignmentList.PlaneAlignment(ObjectCalls.ptrcallWithLongArgRetLong(getPlaneAlignmentBind, segment, index))
    }

    @JvmInline
    value class PlaneAlignment(override val value: Long) : GodotEnumValue {
        companion object {
            val HORIZONTAL_UPWARD: PlaneAlignment get() = PlaneAlignment(0L)
            val HORIZONTAL_DOWNWARD: PlaneAlignment get() = PlaneAlignment(1L)
            val VERTICAL: PlaneAlignment get() = PlaneAlignment(2L)
            val ARBITRARY: PlaneAlignment get() = PlaneAlignment(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentPlaneAlignmentList? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialComponentPlaneAlignmentList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentPlaneAlignmentList(GodotHandle(handle))

        private const val GET_PLANE_ALIGNMENT_HASH = 3340200270L
        private val getPlaneAlignmentBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPlaneAlignmentList", "get_plane_alignment", GET_PLANE_ALIGNMENT_HASH)
        }
    }
}
