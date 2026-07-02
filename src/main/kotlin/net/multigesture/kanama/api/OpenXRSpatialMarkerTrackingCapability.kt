package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

/**
 * Generated from Godot docs: OpenXRSpatialMarkerTrackingCapability
 */
class OpenXRSpatialMarkerTrackingCapability(handle: MemorySegment) : OpenXRExtensionWrapper(handle) {
    fun isQrcodeSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isQrcodeSupportedBind, handle)
    }

    fun isMicroQrcodeSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMicroQrcodeSupportedBind, handle)
    }

    fun isArucoSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isArucoSupportedBind, handle)
    }

    fun isAprilTagSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAprilTagSupportedBind, handle)
    }

    fun startEntityDiscovery(spatialContext: RID, componentData: List<OpenXRSpatialComponentData>, nextSnapshotCreate: OpenXRStructureBase?, nextSnapshotQuery: OpenXRStructureBase?, userCallback: GodotCallable): OpenXRFutureResult? {
        return OpenXRFutureResult.wrap(ObjectCalls.ptrcallWithRIDObjectListTwoObjectCallableArgsRetObject(startEntityDiscoveryBind, handle, spatialContext, componentData, nextSnapshotCreate?.requireOpenHandle() ?: MemorySegment.NULL, nextSnapshotQuery?.requireOpenHandle() ?: MemorySegment.NULL, userCallback.target.handle, userCallback.method))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialMarkerTrackingCapability? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialMarkerTrackingCapability? =
            if (handle.address() == 0L) null else OpenXRSpatialMarkerTrackingCapability(handle)

        private const val IS_QRCODE_SUPPORTED_HASH = 2240911060L
        private val isQrcodeSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialMarkerTrackingCapability", "is_qrcode_supported", IS_QRCODE_SUPPORTED_HASH)
        }

        private const val IS_MICRO_QRCODE_SUPPORTED_HASH = 2240911060L
        private val isMicroQrcodeSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialMarkerTrackingCapability", "is_micro_qrcode_supported", IS_MICRO_QRCODE_SUPPORTED_HASH)
        }

        private const val IS_ARUCO_SUPPORTED_HASH = 2240911060L
        private val isArucoSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialMarkerTrackingCapability", "is_aruco_supported", IS_ARUCO_SUPPORTED_HASH)
        }

        private const val IS_APRIL_TAG_SUPPORTED_HASH = 2240911060L
        private val isAprilTagSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialMarkerTrackingCapability", "is_april_tag_supported", IS_APRIL_TAG_SUPPORTED_HASH)
        }

        private const val START_ENTITY_DISCOVERY_HASH = 3452714169L
        private val startEntityDiscoveryBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialMarkerTrackingCapability", "start_entity_discovery", START_ENTITY_DISCOVERY_HASH)
        }
    }
}
