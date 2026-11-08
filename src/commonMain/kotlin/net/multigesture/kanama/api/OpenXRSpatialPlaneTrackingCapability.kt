package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Implementation for handling spatial entity plane tracking logic.
 *
 * Generated from Godot docs: OpenXRSpatialPlaneTrackingCapability
 */
class OpenXRSpatialPlaneTrackingCapability(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    /**
     * Returns `true` if plane tracking is supported by the current device.
     *
     * Generated from Godot docs: OpenXRSpatialPlaneTrackingCapability.is_supported
     */
    fun isSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSupportedBind, segment)
    }

    /**
     * Calls `OpenXRSpatialEntityExtension.discover_spatial_entities` and
     * `OpenXRSpatialEntityExtension.query_snapshot` with the plane entities associated with
     * `spatial_context`. `component_data` are the `OpenXRSpatialComponentData`s to discover for this
     * plane capability. If `next_snapshot_create` is non-null, then pass this to the `next` parameter
     * in `OpenXRSpatialEntityExtension.discover_spatial_entities`. If `next_snapshot_query` is
     * non-null, then pass this to the `next` parameter in
     * `OpenXRSpatialEntityExtension.query_snapshot`. `user_callback`, when non-null, is called with
     * two parameters usually twice. The first parameter is the `RID` of the discovery snapshot and the
     * second parameter is a boolean where `false` indicates the discovery snapshot is about to be
     * processed, and `true` indicates the discovery snapshot has been processed and `component_data`
     * has valid data. The second call is skipped if an error was encountered. The returned
     * `OpenXRFutureResult` is identical to the return from
     * `OpenXRSpatialEntityExtension.discover_spatial_entities`.
     *
     * Generated from Godot docs: OpenXRSpatialPlaneTrackingCapability.start_entity_discovery
     */
    fun startEntityDiscovery(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase? = null, nextSnapshotQuery: OpenXRStructureBase? = null, userCallback: GodotCallable): OpenXRFutureResult? {
        val ret = OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithRIDObjectListTwoObjectCallableArgsRetObject(Binds.startEntityDiscoveryBind, segment, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: NULL_SEGMENT, nextSnapshotQuery?.requireOpenHandle() ?: NULL_SEGMENT, userCallback.target.segment, userCallback.method))
        ObjectCalls.keepReachable(nextSnapshotCreate)
        ObjectCalls.keepReachable(nextSnapshotQuery)
        ObjectCalls.keepReachable(userCallback)
        return ret
    }

    /**
     * Returns the state of the built-in tracking system.
     *
     * Generated from Godot docs: OpenXRSpatialPlaneTrackingCapability.get_built_in_tracking_state
     */
    fun getBuiltInTrackingState(): OpenXRSpatialEntityExtension.TrackingState {
        return OpenXRSpatialEntityExtension.TrackingState(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBuiltInTrackingStateBind, segment))
    }

    /**
     * Starts the built-in plane tracking logic. This will fail if built-in tracking is already
     * enabled.
     *
     * Generated from Godot docs: OpenXRSpatialPlaneTrackingCapability.start_built_in_tracking
     */
    fun startBuiltInTracking(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.startBuiltInTrackingBind, segment)
    }

    /**
     * Stops the built-in plane tracking logic.
     *
     * Generated from Godot docs: OpenXRSpatialPlaneTrackingCapability.stop_built_in_tracking
     */
    fun stopBuiltInTracking(clearTrackers: Boolean = true) {
        ObjectCalls.ptrcallWithBoolArg(Binds.stopBuiltInTrackingBind, segment, clearTrackers)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialPlaneTrackingCapability? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialPlaneTrackingCapability? =
            if (handle.address() == 0L) null else OpenXRSpatialPlaneTrackingCapability(GodotHandle(handle))
    }

    private object Binds {
        private const val IS_SUPPORTED_HASH = 2240911060L
        @JvmField
        val isSupportedBind =
            ObjectCalls.getMethodBind("OpenXRSpatialPlaneTrackingCapability", "is_supported", IS_SUPPORTED_HASH)

        private const val START_ENTITY_DISCOVERY_HASH = 3452714169L
        @JvmField
        val startEntityDiscoveryBind =
            ObjectCalls.getMethodBind("OpenXRSpatialPlaneTrackingCapability", "start_entity_discovery", START_ENTITY_DISCOVERY_HASH)

        private const val GET_BUILT_IN_TRACKING_STATE_HASH = 434811308L
        @JvmField
        val getBuiltInTrackingStateBind =
            ObjectCalls.getMethodBind("OpenXRSpatialPlaneTrackingCapability", "get_built_in_tracking_state", GET_BUILT_IN_TRACKING_STATE_HASH)

        private const val START_BUILT_IN_TRACKING_HASH = 2240911060L
        @JvmField
        val startBuiltInTrackingBind =
            ObjectCalls.getMethodBind("OpenXRSpatialPlaneTrackingCapability", "start_built_in_tracking", START_BUILT_IN_TRACKING_HASH)

        private const val STOP_BUILT_IN_TRACKING_HASH = 3216645846L
        @JvmField
        val stopBuiltInTrackingBind =
            ObjectCalls.getMethodBind("OpenXRSpatialPlaneTrackingCapability", "stop_built_in_tracking", STOP_BUILT_IN_TRACKING_HASH)
    }
}
