package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Base class for Positional trackers managed by OpenXR's spatial entity extensions.
 *
 * Generated from Godot docs: OpenXRSpatialEntityTracker
 */
open class OpenXRSpatialEntityTracker(handle: GodotHandle) : XRPositionalTracker(handle) {
    var entity: RID
        @JvmName("entityProperty")
        get() = getEntity()
        @JvmName("setEntityProperty")
        set(value) = setEntity(value)

    var spatialTrackingState: OpenXRSpatialEntityTracker.EntityTrackingState
        @JvmName("spatialTrackingStateProperty")
        get() = getSpatialTrackingState()
        @JvmName("setSpatialTrackingStateProperty")
        set(value) = setSpatialTrackingState(value)

    /**
     * Sets the spatial context used to create this tracker.
     *
     * Generated from Godot docs: OpenXRSpatialEntityTracker.set_spatial_context
     */
    fun setSpatialContext(spatialContext: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.setSpatialContextBind, segment, spatialContext)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Gets the spatial context used to create this `OpenXRSpatialEntityTracker`.
     *
     * Generated from Godot docs: OpenXRSpatialEntityTracker.get_spatial_context
     */
    fun getSpatialContext(): RID {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetRID(Binds.getSpatialContextBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The spatial entity associated with this tracker.
     *
     * Generated from Godot docs: OpenXRSpatialEntityTracker.set_entity
     */
    fun setEntity(entity: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.setEntityBind, segment, entity)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The spatial entity associated with this tracker.
     *
     * Generated from Godot docs: OpenXRSpatialEntityTracker.get_entity
     */
    fun getEntity(): RID {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetRID(Binds.getEntityBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The spatial tracking state for this tracker.
     *
     * Generated from Godot docs: OpenXRSpatialEntityTracker.set_spatial_tracking_state
     */
    fun setSpatialTrackingState(spatialTrackingState: OpenXRSpatialEntityTracker.EntityTrackingState) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSpatialTrackingStateBind, segment, spatialTrackingState.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The spatial tracking state for this tracker.
     *
     * Generated from Godot docs: OpenXRSpatialEntityTracker.get_spatial_tracking_state
     */
    fun getSpatialTrackingState(): OpenXRSpatialEntityTracker.EntityTrackingState {
        checkOpen()
        val ret = OpenXRSpatialEntityTracker.EntityTrackingState(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSpatialTrackingStateBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Gets the head `OpenXRStructureBase` in the next-chain. See also `add_next` and `remove_next`.
     *
     * Generated from Godot docs: OpenXRSpatialEntityTracker.get_next
     */
    fun getNext(): OpenXRStructureBase? {
        checkOpen()
        val ret = OpenXRStructureBase.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getNextBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Adds a new `OpenXRStructureBase` to the next-chain. `get_next` will return this `next` until
     * either `add_next` is called again or it's removed in `remove_next`.
     *
     * Generated from Godot docs: OpenXRSpatialEntityTracker.add_next
     */
    fun addNext(next: OpenXRStructureBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.addNextBind, segment, listOf(next?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(next)
    }

    /**
     * Removes a `next` object previously added in `add_next` from the next-chain.
     *
     * Generated from Godot docs: OpenXRSpatialEntityTracker.remove_next
     */
    fun removeNext(next: OpenXRStructureBase?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeNextBind, segment, listOf(next?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(next)
    }

    /** Signal `next_changed()`; see [TypedSignal]. */
    val nextChanged: Signal0
        @JvmName("nextChangedTypedSignal")
        get() = Signal0(this, "next_changed")

    /** Signal `spatial_tracking_state_changed(spatial_tracking_state: int)`; see [TypedSignal]. */
    val spatialTrackingStateChanged: Signal1<Long>
        @JvmName("spatialTrackingStateChangedTypedSignal")
        get() = Signal1(this, "spatial_tracking_state_changed", SignalArgType.LONG)

    object Signals {
        const val nextChanged: String = "next_changed"
        const val spatialTrackingStateChanged: String = "spatial_tracking_state_changed"
    }

    /**
     * Godot's `OpenXRSpatialEntityTracker.EntityTrackingState` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`OpenXRSpatialEntityTracker.EntityTrackingState.<NAME>`).
     *
     * Generated from Godot docs: OpenXRSpatialEntityTracker.EntityTrackingState
     */
    @JvmInline
    value class EntityTrackingState(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * This anchor has stopped tracking.
             *
             * Generated from Godot docs: OpenXRSpatialEntityTracker.ENTITY_TRACKING_STATE_STOPPED
             */
            val STOPPED: EntityTrackingState get() = EntityTrackingState(1L)
            /**
             * Tracking is currently paused.
             *
             * Generated from Godot docs: OpenXRSpatialEntityTracker.ENTITY_TRACKING_STATE_PAUSED
             */
            val PAUSED: EntityTrackingState get() = EntityTrackingState(2L)
            /**
             * This anchor is currently being tracked.
             *
             * Generated from Godot docs: OpenXRSpatialEntityTracker.ENTITY_TRACKING_STATE_TRACKING
             */
            val TRACKING: EntityTrackingState get() = EntityTrackingState(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialEntityTracker? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialEntityTracker? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialEntityTracker(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialEntityTracker? =
            if (handle.address() == 0L) null else OpenXRSpatialEntityTracker(GodotHandle(handle))

        /** A new OpenXRSpatialEntityTracker, GDScript's `OpenXRSpatialEntityTracker.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRSpatialEntityTracker =
            RefCounted.owned(OpenXRSpatialEntityTracker(GodotHandle(ObjectCalls.constructObject("OpenXRSpatialEntityTracker"))))
    }

    private object Binds {
        private const val SET_SPATIAL_CONTEXT_HASH = 2722037293L
        @JvmField
        val setSpatialContextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "set_spatial_context", SET_SPATIAL_CONTEXT_HASH)

        private const val GET_SPATIAL_CONTEXT_HASH = 2944877500L
        @JvmField
        val getSpatialContextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "get_spatial_context", GET_SPATIAL_CONTEXT_HASH)

        private const val SET_ENTITY_HASH = 2722037293L
        @JvmField
        val setEntityBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "set_entity", SET_ENTITY_HASH)

        private const val GET_ENTITY_HASH = 2944877500L
        @JvmField
        val getEntityBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "get_entity", GET_ENTITY_HASH)

        private const val SET_SPATIAL_TRACKING_STATE_HASH = 2170234447L
        @JvmField
        val setSpatialTrackingStateBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "set_spatial_tracking_state", SET_SPATIAL_TRACKING_STATE_HASH)

        private const val GET_SPATIAL_TRACKING_STATE_HASH = 3351876560L
        @JvmField
        val getSpatialTrackingStateBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "get_spatial_tracking_state", GET_SPATIAL_TRACKING_STATE_HASH)

        private const val GET_NEXT_HASH = 2798796760L
        @JvmField
        val getNextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "get_next", GET_NEXT_HASH)

        private const val ADD_NEXT_HASH = 334698771L
        @JvmField
        val addNextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "add_next", ADD_NEXT_HASH)

        private const val REMOVE_NEXT_HASH = 334698771L
        @JvmField
        val removeNextBind =
            ObjectCalls.getMethodBind("OpenXRSpatialEntityTracker", "remove_next", REMOVE_NEXT_HASH)
    }
}
