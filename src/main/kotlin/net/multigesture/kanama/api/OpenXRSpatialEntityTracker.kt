package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: OpenXRSpatialEntityTracker
 */
open class OpenXRSpatialEntityTracker(handle: MemorySegment) : XRPositionalTracker(handle) {
    var entity: RID
        @JvmName("entityProperty")
        get() = getEntity()
        @JvmName("setEntityProperty")
        set(value) = setEntity(value)

    var spatialTrackingState: Long
        @JvmName("spatialTrackingStateProperty")
        get() = getSpatialTrackingState()
        @JvmName("setSpatialTrackingStateProperty")
        set(value) = setSpatialTrackingState(value)

    fun setSpatialContext(spatialContext: RID) {
        ObjectCalls.ptrcallWithRIDArg(setSpatialContextBind, handle, spatialContext)
    }

    fun getSpatialContext(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getSpatialContextBind, handle)
    }

    fun setEntity(entity: RID) {
        ObjectCalls.ptrcallWithRIDArg(setEntityBind, handle, entity)
    }

    fun getEntity(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getEntityBind, handle)
    }

    fun setSpatialTrackingState(spatialTrackingState: Long) {
        ObjectCalls.ptrcallWithLongArg(setSpatialTrackingStateBind, handle, spatialTrackingState)
    }

    fun getSpatialTrackingState(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSpatialTrackingStateBind, handle)
    }

    fun getNext(): OpenXRStructureBase? {
        return OpenXRStructureBase.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNextBind, handle))
    }

    fun addNext(next: OpenXRStructureBase?) {
        ObjectCalls.ptrcallWithObjectArgs(addNextBind, handle, listOf(next?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun removeNext(next: OpenXRStructureBase?) {
        ObjectCalls.ptrcallWithObjectArgs(removeNextBind, handle, listOf(next?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    object Signals {
        const val nextChanged: String = "next_changed"
        const val spatialTrackingStateChanged: String = "spatial_tracking_state_changed"
    }

    companion object {
        const val ENTITY_TRACKING_STATE_STOPPED: Long = 1L
        const val ENTITY_TRACKING_STATE_PAUSED: Long = 2L
        const val ENTITY_TRACKING_STATE_TRACKING: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialEntityTracker? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialEntityTracker? =
            if (handle.address() == 0L) null else OpenXRSpatialEntityTracker(handle)

        private const val SET_SPATIAL_CONTEXT_HASH = 2722037293L
        private val setSpatialContextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "set_spatial_context", SET_SPATIAL_CONTEXT_HASH)
        }

        private const val GET_SPATIAL_CONTEXT_HASH = 2944877500L
        private val getSpatialContextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "get_spatial_context", GET_SPATIAL_CONTEXT_HASH)
        }

        private const val SET_ENTITY_HASH = 2722037293L
        private val setEntityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "set_entity", SET_ENTITY_HASH)
        }

        private const val GET_ENTITY_HASH = 2944877500L
        private val getEntityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "get_entity", GET_ENTITY_HASH)
        }

        private const val SET_SPATIAL_TRACKING_STATE_HASH = 2170234447L
        private val setSpatialTrackingStateBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "set_spatial_tracking_state", SET_SPATIAL_TRACKING_STATE_HASH)
        }

        private const val GET_SPATIAL_TRACKING_STATE_HASH = 3351876560L
        private val getSpatialTrackingStateBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "get_spatial_tracking_state", GET_SPATIAL_TRACKING_STATE_HASH)
        }

        private const val GET_NEXT_HASH = 2798796760L
        private val getNextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "get_next", GET_NEXT_HASH)
        }

        private const val ADD_NEXT_HASH = 334698771L
        private val addNextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "add_next", ADD_NEXT_HASH)
        }

        private const val REMOVE_NEXT_HASH = 334698771L
        private val removeNextBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "remove_next", REMOVE_NEXT_HASH)
        }
    }
}
