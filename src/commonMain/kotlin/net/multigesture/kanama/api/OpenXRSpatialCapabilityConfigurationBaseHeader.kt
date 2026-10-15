package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Wrapper base class for OpenXR Spatial Capability Configuration headers.
 *
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationBaseHeader
 */
open class OpenXRSpatialCapabilityConfigurationBaseHeader(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns `true` if this object contains a valid configuration that can be used when calling
     * `OpenXRSpatialEntityExtension.create_spatial_context`.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationBaseHeader.has_valid_configuration
     */
    fun hasValidConfiguration(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.hasValidConfigurationBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Gets a pointer to the `XrSpatialCapabilityConfigurationBaseHeaderEXT` struct. Note: This method
     * is intended to be used from GDExtensions.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationBaseHeader.get_configuration
     */
    fun getConfiguration(): Long {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetLong(Binds.getConfigurationBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialCapabilityConfigurationBaseHeader? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialCapabilityConfigurationBaseHeader? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialCapabilityConfigurationBaseHeader(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialCapabilityConfigurationBaseHeader? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationBaseHeader(GodotHandle(handle))
    }

    private object Binds {
        private const val HAS_VALID_CONFIGURATION_HASH = 36873697L
        @JvmField
        val hasValidConfigurationBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationBaseHeader", "has_valid_configuration", HAS_VALID_CONFIGURATION_HASH)

        private const val GET_CONFIGURATION_HASH = 2455072627L
        @JvmField
        val getConfigurationBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationBaseHeader", "get_configuration", GET_CONFIGURATION_HASH)
    }
}
