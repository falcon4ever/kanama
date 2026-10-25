package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Configuration header for QR code markers.
 *
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationMicroQrCode
 */
class OpenXRSpatialCapabilityConfigurationMicroQrCode(handle: GodotHandle) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    /**
     * Returns the components enabled by this configuration. Note: Only valid after this configuration
     * was used to create a spatial context.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationMicroQrCode.get_enabled_components
     */
    fun getEnabledComponents(): List<Long> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt64List(Binds.getEnabledComponentsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialCapabilityConfigurationMicroQrCode? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialCapabilityConfigurationMicroQrCode? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialCapabilityConfigurationMicroQrCode(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialCapabilityConfigurationMicroQrCode? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationMicroQrCode(GodotHandle(handle))

        /** A new OpenXRSpatialCapabilityConfigurationMicroQrCode, GDScript's `OpenXRSpatialCapabilityConfigurationMicroQrCode.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRSpatialCapabilityConfigurationMicroQrCode =
            RefCounted.owned(OpenXRSpatialCapabilityConfigurationMicroQrCode(GodotHandle(ObjectCalls.constructObject("OpenXRSpatialCapabilityConfigurationMicroQrCode"))))
    }

    private object Binds {
        private const val GET_ENABLED_COMPONENTS_HASH = 235988956L
        @JvmField
        val getEnabledComponentsBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationMicroQrCode", "get_enabled_components", GET_ENABLED_COMPONENTS_HASH)
    }
}
