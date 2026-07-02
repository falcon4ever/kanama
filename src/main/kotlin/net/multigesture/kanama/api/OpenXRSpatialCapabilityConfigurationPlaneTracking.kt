package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationPlaneTracking
 */
class OpenXRSpatialCapabilityConfigurationPlaneTracking(handle: MemorySegment) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    fun supportsMesh2d(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(supportsMesh2dBind, handle)
    }

    fun supportsPolygons(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(supportsPolygonsBind, handle)
    }

    fun supportsLabels(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(supportsLabelsBind, handle)
    }

    fun getEnabledComponents(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getEnabledComponentsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRSpatialCapabilityConfigurationPlaneTracking? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialCapabilityConfigurationPlaneTracking? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationPlaneTracking(handle)

        private const val SUPPORTS_MESH_2D_HASH = 2240911060L
        private val supportsMesh2dBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationPlaneTracking", "supports_mesh_2d", SUPPORTS_MESH_2D_HASH)
        }

        private const val SUPPORTS_POLYGONS_HASH = 2240911060L
        private val supportsPolygonsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationPlaneTracking", "supports_polygons", SUPPORTS_POLYGONS_HASH)
        }

        private const val SUPPORTS_LABELS_HASH = 2240911060L
        private val supportsLabelsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationPlaneTracking", "supports_labels", SUPPORTS_LABELS_HASH)
        }

        private const val GET_ENABLED_COMPONENTS_HASH = 235988956L
        private val getEnabledComponentsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationPlaneTracking", "get_enabled_components", GET_ENABLED_COMPONENTS_HASH)
        }
    }
}
