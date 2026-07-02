package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: OpenXRSpatialComponentParentList
 */
class OpenXRSpatialComponentParentList(handle: MemorySegment) : OpenXRSpatialComponentData(handle) {
    fun getParent(index: Long): RID {
        return ObjectCalls.ptrcallWithLongArgRetRID(getParentBind, handle, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialComponentParentList? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialComponentParentList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentParentList(handle)

        private const val GET_PARENT_HASH = 495598643L
        private val getParentBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentParentList", "get_parent", GET_PARENT_HASH)
        }
    }
}
