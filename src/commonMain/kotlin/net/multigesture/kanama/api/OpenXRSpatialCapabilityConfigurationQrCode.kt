package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationQrCode
 */
class OpenXRSpatialCapabilityConfigurationQrCode(handle: GodotHandle) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    fun getEnabledComponents(): List<Long> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(Binds.getEnabledComponentsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialCapabilityConfigurationQrCode? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialCapabilityConfigurationQrCode? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialCapabilityConfigurationQrCode(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialCapabilityConfigurationQrCode? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationQrCode(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_ENABLED_COMPONENTS_HASH = 235988956L
        @JvmField
        val getEnabledComponentsBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationQrCode", "get_enabled_components", GET_ENABLED_COMPONENTS_HASH)
    }
}
