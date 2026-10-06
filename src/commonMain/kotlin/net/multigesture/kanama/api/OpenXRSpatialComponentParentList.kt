package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: OpenXRSpatialComponentParentList
 */
class OpenXRSpatialComponentParentList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getParent(index: Long): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetRID(Binds.getParentBind, segment, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentParentList? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialComponentParentList? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialComponentParentList(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialComponentParentList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentParentList(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_PARENT_HASH = 495598643L
        @JvmField
        val getParentBind =
            ObjectCalls.getMethodBind("OpenXRSpatialComponentParentList", "get_parent", GET_PARENT_HASH)
    }
}
