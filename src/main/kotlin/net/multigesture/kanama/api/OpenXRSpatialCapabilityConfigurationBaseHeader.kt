package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationBaseHeader
 */
open class OpenXRSpatialCapabilityConfigurationBaseHeader(handle: MemorySegment) : RefCounted(handle) {
    fun hasValidConfiguration(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasValidConfigurationBind, handle)
    }

    fun getConfiguration(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getConfigurationBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialCapabilityConfigurationBaseHeader? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialCapabilityConfigurationBaseHeader? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationBaseHeader(handle)

        private const val HAS_VALID_CONFIGURATION_HASH = 36873697L
        private val hasValidConfigurationBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationBaseHeader", "has_valid_configuration", HAS_VALID_CONFIGURATION_HASH)
        }

        private const val GET_CONFIGURATION_HASH = 2455072627L
        private val getConfigurationBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationBaseHeader", "get_configuration", GET_CONFIGURATION_HASH)
        }
    }
}
