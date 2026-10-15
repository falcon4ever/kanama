package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Implementation for handling spatial entity marker tracking logic.
 *
 * Generated from Godot docs: OpenXRSpatialMarkerTrackingCapability
 */
class OpenXRSpatialMarkerTrackingCapability(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    /**
     * Returns `true` if QR code marker tracking is supported by the current device.
     *
     * Generated from Godot docs: OpenXRSpatialMarkerTrackingCapability.is_qrcode_supported
     */
    fun isQrcodeSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isQrcodeSupportedBind, segment)
    }

    /**
     * Returns `true` if micro QR code marker tracking is supported by the current device.
     *
     * Generated from Godot docs: OpenXRSpatialMarkerTrackingCapability.is_micro_qrcode_supported
     */
    fun isMicroQrcodeSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMicroQrcodeSupportedBind, segment)
    }

    /**
     * Returns `true` if Aruco marker tracking is supported by the current device.
     *
     * Generated from Godot docs: OpenXRSpatialMarkerTrackingCapability.is_aruco_supported
     */
    fun isArucoSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isArucoSupportedBind, segment)
    }

    /**
     * Returns `true` if April tag marker tracking is supported by the current device.
     *
     * Generated from Godot docs: OpenXRSpatialMarkerTrackingCapability.is_april_tag_supported
     */
    fun isAprilTagSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAprilTagSupportedBind, segment)
    }

    /**
     * Calls `OpenXRSpatialEntityExtension.discover_spatial_entities` and
     * `OpenXRSpatialEntityExtension.query_snapshot` with the marker entities associated with
     * `spatial_context`. `component_data` are the `OpenXRSpatialComponentData`s to discover for this
     * marker capability. If `next_snapshot_create` is non-null, then pass this to the `next` parameter
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
     * Generated from Godot docs: OpenXRSpatialMarkerTrackingCapability.start_entity_discovery
     */
    fun startEntityDiscovery(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase?, nextSnapshotQuery: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        val ret = OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithRIDObjectListTwoObjectCallableArgsRetObject(Binds.startEntityDiscoveryBind, segment, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: NULL_SEGMENT, nextSnapshotQuery?.requireOpenHandle() ?: NULL_SEGMENT, userCallback.target.segment, userCallback.method))
        ObjectCalls.keepReachable(nextSnapshotCreate)
        ObjectCalls.keepReachable(nextSnapshotQuery)
        ObjectCalls.keepReachable(userCallback)
        return ret
    }

    /**
     * Calls `OpenXRSpatialEntityExtension.update_spatial_entities` and
     * `OpenXRSpatialEntityExtension.query_snapshot` with the marker entities associated with
     * `spatial_context`. `component_data` are the `OpenXRSpatialComponentData`s to update for this
     * marker capability. If `next_snapshot_create` is non-null, then pass this to the `next` parameter
     * in `OpenXRSpatialEntityExtension.update_spatial_entities`. If `next_snapshot_query` is non-null,
     * then pass this to the `next` parameter in `OpenXRSpatialEntityExtension.query_snapshot`.
     *
     * Generated from Godot docs: OpenXRSpatialMarkerTrackingCapability.do_entity_update
     */
    fun doEntityUpdate(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase?, nextSnapshotQuery: OpenXRStructureBase?) {
        ObjectCalls.ptrcallWithRIDObjectListTwoObjectArgs(Binds.doEntityUpdateBind, segment, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: NULL_SEGMENT, nextSnapshotQuery?.requireOpenHandle() ?: NULL_SEGMENT)
        ObjectCalls.keepReachable(nextSnapshotCreate)
        ObjectCalls.keepReachable(nextSnapshotQuery)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialMarkerTrackingCapability? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialMarkerTrackingCapability? =
            if (handle.address() == 0L) null else OpenXRSpatialMarkerTrackingCapability(GodotHandle(handle))
    }

    private object Binds {
        private const val IS_QRCODE_SUPPORTED_HASH = 2240911060L
        @JvmField
        val isQrcodeSupportedBind =
            ObjectCalls.getMethodBind("OpenXRSpatialMarkerTrackingCapability", "is_qrcode_supported", IS_QRCODE_SUPPORTED_HASH)

        private const val IS_MICRO_QRCODE_SUPPORTED_HASH = 2240911060L
        @JvmField
        val isMicroQrcodeSupportedBind =
            ObjectCalls.getMethodBind("OpenXRSpatialMarkerTrackingCapability", "is_micro_qrcode_supported", IS_MICRO_QRCODE_SUPPORTED_HASH)

        private const val IS_ARUCO_SUPPORTED_HASH = 2240911060L
        @JvmField
        val isArucoSupportedBind =
            ObjectCalls.getMethodBind("OpenXRSpatialMarkerTrackingCapability", "is_aruco_supported", IS_ARUCO_SUPPORTED_HASH)

        private const val IS_APRIL_TAG_SUPPORTED_HASH = 2240911060L
        @JvmField
        val isAprilTagSupportedBind =
            ObjectCalls.getMethodBind("OpenXRSpatialMarkerTrackingCapability", "is_april_tag_supported", IS_APRIL_TAG_SUPPORTED_HASH)

        private const val START_ENTITY_DISCOVERY_HASH = 3452714169L
        @JvmField
        val startEntityDiscoveryBind =
            ObjectCalls.getMethodBind("OpenXRSpatialMarkerTrackingCapability", "start_entity_discovery", START_ENTITY_DISCOVERY_HASH)

        private const val DO_ENTITY_UPDATE_HASH = 3138044275L
        @JvmField
        val doEntityUpdateBind =
            ObjectCalls.getMethodBind("OpenXRSpatialMarkerTrackingCapability", "do_entity_update", DO_ENTITY_UPDATE_HASH)
    }
}
