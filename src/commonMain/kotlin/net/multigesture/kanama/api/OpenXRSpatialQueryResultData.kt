package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRSpatialQueryResultData
 */
class OpenXRSpatialQueryResultData(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    fun getCapacity(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getCapacityBind, segment)
    }

    fun getEntityId(index: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(Binds.getEntityIdBind, segment, index)
    }

    fun getEntityState(index: Long): OpenXRSpatialEntityTracker.EntityTrackingState {
        checkOpen()
        return OpenXRSpatialEntityTracker.EntityTrackingState(ObjectCalls.ptrcallWithLongArgRetLong(Binds.getEntityStateBind, segment, index))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialQueryResultData? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialQueryResultData? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialQueryResultData(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialQueryResultData? =
            if (handle.address() == 0L) null else OpenXRSpatialQueryResultData(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_CAPACITY_HASH = 3905245786L
        @JvmField
        val getCapacityBind =
            ObjectCalls.getMethodBind("OpenXRSpatialQueryResultData", "get_capacity", GET_CAPACITY_HASH)

        private const val GET_ENTITY_ID_HASH = 923996154L
        @JvmField
        val getEntityIdBind =
            ObjectCalls.getMethodBind("OpenXRSpatialQueryResultData", "get_entity_id", GET_ENTITY_ID_HASH)

        private const val GET_ENTITY_STATE_HASH = 1411962015L
        @JvmField
        val getEntityStateBind =
            ObjectCalls.getMethodBind("OpenXRSpatialQueryResultData", "get_entity_state", GET_ENTITY_STATE_HASH)
    }
}
