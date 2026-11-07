package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * OpenXR extension that defines "self rendering" for spatial containers.
 *
 * Generated from Godot docs: OpenXRSpatialContainerSelfRenderingExtension
 */
class OpenXRSpatialContainerSelfRenderingExtension(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    /**
     * Returns `true` if spatial container self rendering is enabled in the project settings and
     * available on the current runtime.
     *
     * Generated from Godot docs: OpenXRSpatialContainerSelfRenderingExtension.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEnabledBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialContainerSelfRenderingExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialContainerSelfRenderingExtension? =
            if (handle.address() == 0L) null else OpenXRSpatialContainerSelfRenderingExtension(GodotHandle(handle))
    }

    private object Binds {
        private const val IS_ENABLED_HASH = 36873697L
        @JvmField
        val isEnabledBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContainerSelfRenderingExtension", "is_enabled", IS_ENABLED_HASH)
    }
}
