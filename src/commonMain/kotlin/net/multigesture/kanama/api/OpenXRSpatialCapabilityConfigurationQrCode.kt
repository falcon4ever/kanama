package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationQrCode
 */
class OpenXRSpatialCapabilityConfigurationQrCode(handle: GodotHandle) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    fun getEnabledComponents(): List<Long> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getEnabledComponentsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialCapabilityConfigurationQrCode? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialCapabilityConfigurationQrCode? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationQrCode(GodotHandle(handle))

        private const val GET_ENABLED_COMPONENTS_HASH = 235988956L
        private val getEnabledComponentsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationQrCode", "get_enabled_components", GET_ENABLED_COMPONENTS_HASH)
        }
    }
}
