package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Object for storing the queries parent result data.
 *
 * Generated from Godot docs: OpenXRSpatialComponentParentList
 */
class OpenXRSpatialComponentParentList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    /**
     * Returns the RID for the parent entity at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialComponentParentList.get_parent
     */
    fun getParent(index: Long): RID {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetRID(Binds.getParentBind, segment, index)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentParentList? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialComponentParentList? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialComponentParentList(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialComponentParentList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentParentList(GodotHandle(handle))

        /** A new OpenXRSpatialComponentParentList, GDScript's `OpenXRSpatialComponentParentList.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRSpatialComponentParentList =
            RefCounted.owned(OpenXRSpatialComponentParentList(GodotHandle(ObjectCalls.constructObject("OpenXRSpatialComponentParentList"))))
    }

    private object Binds {
        private const val GET_PARENT_HASH = 495598643L
        @JvmField
        val getParentBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentParentList", "get_parent", GET_PARENT_HASH)
    }
}
