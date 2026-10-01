package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: OpenXRSpatialComponentAnchorList
 */
class OpenXRSpatialComponentAnchorList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getEntityPose(index: Long): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(getEntityPoseBind, segment, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentAnchorList? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialComponentAnchorList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentAnchorList(GodotHandle(handle))

        private const val GET_ENTITY_POSE_HASH = 1965739696L
        private val getEntityPoseBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentAnchorList", "get_entity_pose", GET_ENTITY_POSE_HASH)
        }
    }
}
