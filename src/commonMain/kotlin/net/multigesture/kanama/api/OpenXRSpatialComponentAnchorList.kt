package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D

/**
 * Object for storing the queries anchor result data.
 *
 * Generated from Godot docs: OpenXRSpatialComponentAnchorList
 */
class OpenXRSpatialComponentAnchorList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    /**
     * Returns the transform for the entity at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialComponentAnchorList.get_entity_pose
     */
    fun getEntityPose(index: Long): Transform3D {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetTransform3D(Binds.getEntityPoseBind, segment, index)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentAnchorList? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialComponentAnchorList? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialComponentAnchorList(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialComponentAnchorList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentAnchorList(GodotHandle(handle))

        /** A new OpenXRSpatialComponentAnchorList, GDScript's `OpenXRSpatialComponentAnchorList.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRSpatialComponentAnchorList =
            RefCounted.owned(OpenXRSpatialComponentAnchorList(GodotHandle(ObjectCalls.constructObject("OpenXRSpatialComponentAnchorList"))))
    }

    private object Binds {
        private const val GET_ENTITY_POSE_HASH = 1965739696L
        @JvmField
        val getEntityPoseBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentAnchorList", "get_entity_pose", GET_ENTITY_POSE_HASH)
    }
}
