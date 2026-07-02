package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: OpenXRSpatialComponentAnchorList
 */
class OpenXRSpatialComponentAnchorList(handle: MemorySegment) : OpenXRSpatialComponentData(handle) {
    fun getEntityPose(index: Long): Transform3D {
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(getEntityPoseBind, handle, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialComponentAnchorList? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialComponentAnchorList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentAnchorList(handle)

        private const val GET_ENTITY_POSE_HASH = 1965739696L
        private val getEntityPoseBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentAnchorList", "get_entity_pose", GET_ENTITY_POSE_HASH)
        }
    }
}
