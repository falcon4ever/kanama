package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: OpenXRSpatialComponentParentList
 */
class OpenXRSpatialComponentParentList(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getParent(index: Long): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetRID(getParentBind, segment, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialComponentParentList? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialComponentParentList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentParentList(GodotHandle(handle))

        private const val GET_PARENT_HASH = 495598643L
        private val getParentBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentParentList", "get_parent", GET_PARENT_HASH)
        }
    }
}
