package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Configuration header for micro QR code markers.
 *
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationQrCode
 */
class OpenXRSpatialCapabilityConfigurationQrCode(handle: GodotHandle) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    /**
     * Returns the components enabled by this configuration. Note: Only valid after this configuration
     * was used to create a spatial context.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationQrCode.get_enabled_components
     */
    fun getEnabledComponents(): List<Long> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt64List(Binds.getEnabledComponentsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialCapabilityConfigurationQrCode? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialCapabilityConfigurationQrCode? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialCapabilityConfigurationQrCode(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialCapabilityConfigurationQrCode? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationQrCode(GodotHandle(handle))

        /** A new OpenXRSpatialCapabilityConfigurationQrCode, GDScript's `OpenXRSpatialCapabilityConfigurationQrCode.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRSpatialCapabilityConfigurationQrCode =
            RefCounted.owned(OpenXRSpatialCapabilityConfigurationQrCode(GodotHandle(requireConstructed(ObjectCalls.constructObject("OpenXRSpatialCapabilityConfigurationQrCode"), "OpenXRSpatialCapabilityConfigurationQrCode"))))
    }

    private object Binds {
        private const val GET_ENABLED_COMPONENTS_HASH = 235988956L
        @JvmField
        val getEnabledComponentsBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationQrCode", "get_enabled_components", GET_ENABLED_COMPONENTS_HASH)
    }
}
