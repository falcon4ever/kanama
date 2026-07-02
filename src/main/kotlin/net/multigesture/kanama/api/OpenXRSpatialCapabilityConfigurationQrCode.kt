package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationQrCode
 */
class OpenXRSpatialCapabilityConfigurationQrCode(handle: MemorySegment) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    fun getEnabledComponents(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getEnabledComponentsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialCapabilityConfigurationQrCode? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialCapabilityConfigurationQrCode? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationQrCode(handle)

        private const val GET_ENABLED_COMPONENTS_HASH = 235988956L
        private val getEnabledComponentsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationQrCode", "get_enabled_components", GET_ENABLED_COMPONENTS_HASH)
        }
    }
}
