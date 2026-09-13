package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationPlaneTracking
 */
class OpenXRSpatialCapabilityConfigurationPlaneTracking(handle: GodotHandle) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    fun supportsMesh2d(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(supportsMesh2dBind, segment)
    }

    fun supportsPolygons(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(supportsPolygonsBind, segment)
    }

    fun supportsLabels(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(supportsLabelsBind, segment)
    }

    fun getEnabledComponents(): List<Long> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getEnabledComponentsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialCapabilityConfigurationPlaneTracking? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): OpenXRSpatialCapabilityConfigurationPlaneTracking? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationPlaneTracking(GodotHandle(handle))

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
