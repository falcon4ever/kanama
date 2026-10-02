package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: OpenXRSpatialEntityTracker
 */
open class OpenXRSpatialEntityTracker(handle: GodotHandle) : XRPositionalTracker(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(setSpatialContextBind, segment, spatialContext)
    }

    fun getSpatialContext(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(getSpatialContextBind, segment)
    }

    fun setEntity(entity: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(setEntityBind, segment, entity)
    }

    fun getEntity(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(getEntityBind, segment)
    }

    fun setSpatialTrackingState(spatialTrackingState: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setSpatialTrackingStateBind, segment, spatialTrackingState)
    }

    fun getSpatialTrackingState(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getSpatialTrackingStateBind, segment)
    }

    fun getNext(): OpenXRStructureBase? {
        checkOpen()
        return OpenXRStructureBase.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNextBind, segment))
    }

    fun addNext(next: OpenXRStructureBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(addNextBind, segment, listOf(next?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun removeNext(next: OpenXRStructureBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(removeNextBind, segment, listOf(next?.requireOpenHandle() ?: NULL_SEGMENT))
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
        fun fromHandle(handle: GodotHandle): OpenXRSpatialEntityTracker? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialEntityTracker? =
            if (handle.address() == 0L) null else OpenXRSpatialEntityTracker(GodotHandle(handle))

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
