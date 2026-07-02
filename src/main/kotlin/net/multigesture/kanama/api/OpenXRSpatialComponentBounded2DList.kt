package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: OpenXRSpatialComponentBounded2DList
 */
class OpenXRSpatialComponentBounded2DList(handle: MemorySegment) : OpenXRSpatialComponentData(handle) {
    fun getCenterPose(index: Long): Transform3D {
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(getCenterPoseBind, handle, index)
    }

    fun getSize(index: Long): Vector2 {
        return ObjectCalls.ptrcallWithLongArgRetVector2(getSizeBind, handle, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialComponentBounded2DList? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialComponentBounded2DList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentBounded2DList(handle)

        private const val GET_CENTER_POSE_HASH = 1965739696L
        private val getCenterPoseBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentBounded2DList", "get_center_pose", GET_CENTER_POSE_HASH)
        }

        private const val GET_SIZE_HASH = 2299179447L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentBounded2DList", "get_size", GET_SIZE_HASH)
        }
    }
}
