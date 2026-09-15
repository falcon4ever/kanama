package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRSpatialQueryResultData
 */
class OpenXRSpatialQueryResultData(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getCapacity(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getCapacityBind, segment)
    }

    fun getEntityId(index: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(getEntityIdBind, segment, index)
    }

    fun getEntityState(index: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(getEntityStateBind, segment, index)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialQueryResultData? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialQueryResultData? =
            if (handle.address() == 0L) null else OpenXRSpatialQueryResultData(GodotHandle(handle))

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
