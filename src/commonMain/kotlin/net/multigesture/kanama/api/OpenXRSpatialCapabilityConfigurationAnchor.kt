package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationAnchor
 */
class OpenXRSpatialCapabilityConfigurationAnchor(handle: GodotHandle) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    fun getEnabledComponents(): List<Long> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(Binds.getEnabledComponentsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialCapabilityConfigurationAnchor? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialCapabilityConfigurationAnchor? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialCapabilityConfigurationAnchor(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialCapabilityConfigurationAnchor? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationAnchor(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_ENABLED_COMPONENTS_HASH = 235988956L
        @JvmField
        val getEnabledComponentsBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationAnchor", "get_enabled_components", GET_ENABLED_COMPONENTS_HASH)
    }
}
