package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Object for storing the main query result data.
 *
 * Generated from Godot docs: OpenXRSpatialQueryResultData
 */
class OpenXRSpatialQueryResultData(handle: GodotHandle) : OpenXRSpatialComponentData(handle) {
    /**
     * Returns the number of entities that were retrieved.
     *
     * Generated from Godot docs: OpenXRSpatialQueryResultData.get_capacity
     */
    fun getCapacity(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getCapacityBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the entity id (`XrSpatialEntityIdEXT`) for the entity at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialQueryResultData.get_entity_id
     */
    fun getEntityId(index: Long): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithLongArgRetLong(Binds.getEntityIdBind, segment, index)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Returns the entity state for the entity at this `index`.
     *
     * Generated from Godot docs: OpenXRSpatialQueryResultData.get_entity_state
     */
    fun getEntityState(index: Long): OpenXRSpatialEntityTracker.EntityTrackingState {
        checkOpen()
        val ret = OpenXRSpatialEntityTracker.EntityTrackingState(ObjectCalls.ptrcallWithLongArgRetLong(Binds.getEntityStateBind, segment, index))
        ObjectCalls.keepReachable(this)
        return ret
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
