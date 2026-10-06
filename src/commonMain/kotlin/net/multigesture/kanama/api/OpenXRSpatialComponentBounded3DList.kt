package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: OpenXRSpatialComponentBounded3DList
 */
class OpenXRSpatialComponentBounded3DList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getCenterPose(index: Long): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(Binds.getCenterPoseBind, segment, index)
    }

    fun getSize(index: Long): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetVector3(Binds.getSizeBind, segment, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentBounded3DList? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialComponentBounded3DList? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialComponentBounded3DList(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialComponentBounded3DList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentBounded3DList(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_CENTER_POSE_HASH = 1965739696L
        @JvmField
        val getCenterPoseBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentBounded3DList", "get_center_pose", GET_CENTER_POSE_HASH)

        private const val GET_SIZE_HASH = 711720468L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentBounded3DList", "get_size", GET_SIZE_HASH)
    }
}
