package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

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
    }
}
