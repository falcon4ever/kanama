package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRSpatialQueryResultData
 */
class OpenXRSpatialQueryResultData(handle: MemorySegment) : OpenXRSpatialComponentData(handle) {
    fun getCapacity(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCapacityBind, handle)
    }

    fun getEntityId(index: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(getEntityIdBind, handle, index)
    }

    fun getEntityState(index: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(getEntityStateBind, handle, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialQueryResultData? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialQueryResultData? =
            if (handle.address() == 0L) null else OpenXRSpatialQueryResultData(handle)

        private const val GET_CAPACITY_HASH = 3905245786L
        private val getCapacityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialQueryResultData", "get_capacity", GET_CAPACITY_HASH)
        }

        private const val GET_ENTITY_ID_HASH = 923996154L
        private val getEntityIdBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialQueryResultData", "get_entity_id", GET_ENTITY_ID_HASH)
        }

        private const val GET_ENTITY_STATE_HASH = 1411962015L
        private val getEntityStateBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialQueryResultData", "get_entity_state", GET_ENTITY_STATE_HASH)
        }
    }
}
