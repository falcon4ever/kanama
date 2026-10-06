package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationBaseHeader
 */
open class OpenXRSpatialCapabilityConfigurationBaseHeader(handle: GodotHandle) : RefCounted(handle) {
    fun hasValidConfiguration(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasValidConfigurationBind, segment)
    }

    fun getConfiguration(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getConfigurationBind, segment)
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
