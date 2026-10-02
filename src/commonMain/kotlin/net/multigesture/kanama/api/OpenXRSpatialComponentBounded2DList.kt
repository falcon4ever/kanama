package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: OpenXRSpatialComponentBounded2DList
 */
class OpenXRSpatialComponentBounded2DList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getCenterPose(index: Long): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(getCenterPoseBind, segment, index)
    }

    fun getSize(index: Long): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetVector2(getSizeBind, segment, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentBounded2DList? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialComponentBounded2DList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentBounded2DList(GodotHandle(handle))

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
