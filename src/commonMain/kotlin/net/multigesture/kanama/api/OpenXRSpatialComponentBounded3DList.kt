package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Object for storing the queries bounded3d result data.
 *
 * Generated from Godot docs: OpenXRSpatialComponentBounded3DList
 */
class OpenXRSpatialComponentBounded3DList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    /**
     * Returns the center of our bounding box for the entity at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialComponentBounded3DList.get_center_pose
     */
    fun getCenterPose(index: Long): Transform3D {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetTransform3D(Binds.getCenterPoseBind, segment, index)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the size of our bounding box for the entity at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialComponentBounded3DList.get_size
     */
    fun getSize(index: Long): Vector3 {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetVector3(Binds.getSizeBind, segment, index)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentBounded3DList? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialComponentBounded3DList? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialComponentBounded3DList(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialComponentBounded3DList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentBounded3DList(GodotHandle(handle))

        /** A new OpenXRSpatialComponentBounded3DList, GDScript's `OpenXRSpatialComponentBounded3DList.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRSpatialComponentBounded3DList =
            RefCounted.owned(OpenXRSpatialComponentBounded3DList(GodotHandle(ObjectCalls.constructObject("OpenXRSpatialComponentBounded3DList"))))
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
