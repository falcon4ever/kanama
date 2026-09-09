package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRSpatialComponentPersistenceList
 */
class OpenXRSpatialComponentPersistenceList(handle: MemorySegment) : OpenXRSpatialComponentData(handle) {
    fun getPersistentState(index: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(getPersistentStateBind, handle, index)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): OpenXRSpatialComponentPersistenceList? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialComponentPersistenceList? =
            if (handle.address() == 0L) null else OpenXRSpatialComponentPersistenceList(handle)

        private const val GET_PERSISTENT_STATE_HASH = 923996154L
        private val getPersistentStateBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialComponentPersistenceList", "get_persistent_state", GET_PERSISTENT_STATE_HASH)
        }
    }
}
