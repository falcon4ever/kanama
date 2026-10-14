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
    fun startEntityDiscovery(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase?, nextSnapshotQuery: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithRIDObjectListTwoObjectCallableArgsRetObject(Binds.startEntityDiscoveryBind, segment, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: NULL_SEGMENT, nextSnapshotQuery?.requireOpenHandle() ?: NULL_SEGMENT, userCallback.target.segment, userCallback.method))
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
    }
}
