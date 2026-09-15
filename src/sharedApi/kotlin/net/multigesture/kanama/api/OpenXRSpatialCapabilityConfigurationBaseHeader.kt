package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationBaseHeader
 */
open class OpenXRSpatialCapabilityConfigurationBaseHeader(handle: GodotHandle) : RefCounted(handle) {
    fun hasValidConfiguration(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(hasValidConfigurationBind, segment)
    }

    fun getConfiguration(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getConfigurationBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialCapabilityConfigurationBaseHeader? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialCapabilityConfigurationBaseHeader? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationBaseHeader(GodotHandle(handle))

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
