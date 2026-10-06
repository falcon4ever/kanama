package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: OpenXRSpatialMarkerTrackingCapability
 */
class OpenXRSpatialMarkerTrackingCapability(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    fun isQrcodeSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isQrcodeSupportedBind, segment)
    }

    fun isMicroQrcodeSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMicroQrcodeSupportedBind, segment)
    }

    fun isArucoSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isArucoSupportedBind, segment)
    }

    fun isAprilTagSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAprilTagSupportedBind, segment)
    }

    fun startEntityDiscovery(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase?, nextSnapshotQuery: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrapOwned(ObjectCalls.ptrcallWithRIDObjectListTwoObjectCallableArgsRetObject(Binds.startEntityDiscoveryBind, segment, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: NULL_SEGMENT, nextSnapshotQuery?.requireOpenHandle() ?: NULL_SEGMENT, userCallback.target.segment, userCallback.method))
    }

    fun doEntityUpdate(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase?, nextSnapshotQuery: OpenXRStructureBase?) {
        ObjectCalls.ptrcallWithRIDObjectListTwoObjectArgs(Binds.doEntityUpdateBind, segment, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: NULL_SEGMENT, nextSnapshotQuery?.requireOpenHandle() ?: NULL_SEGMENT)
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
