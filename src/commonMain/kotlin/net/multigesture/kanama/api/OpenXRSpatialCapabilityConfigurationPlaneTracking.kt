package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationPlaneTracking
 */
class OpenXRSpatialCapabilityConfigurationPlaneTracking(handle: GodotHandle) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    fun supportsMesh2d(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.supportsMesh2dBind, segment)
    }

    fun supportsPolygons(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.supportsPolygonsBind, segment)
    }

    fun supportsLabels(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.supportsLabelsBind, segment)
    }

    fun getEnabledComponents(): List<Long> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(Binds.getEnabledComponentsBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialCapabilityConfigurationPlaneTracking? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRSpatialCapabilityConfigurationPlaneTracking? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRSpatialCapabilityConfigurationPlaneTracking(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRSpatialCapabilityConfigurationPlaneTracking? =
            if (handle.address() == 0L) null else OpenXRSpatialCapabilityConfigurationPlaneTracking(GodotHandle(handle))
    }

    private object Binds {
        private const val SUPPORTS_MESH_2D_HASH = 2240911060L
        @JvmField
        val supportsMesh2dBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationPlaneTracking", "supports_mesh_2d", SUPPORTS_MESH_2D_HASH)

        private const val SUPPORTS_POLYGONS_HASH = 2240911060L
        @JvmField
        val supportsPolygonsBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationPlaneTracking", "supports_polygons", SUPPORTS_POLYGONS_HASH)

        private const val SUPPORTS_LABELS_HASH = 2240911060L
        @JvmField
        val supportsLabelsBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationPlaneTracking", "supports_labels", SUPPORTS_LABELS_HASH)

        private const val GET_ENABLED_COMPONENTS_HASH = 235988956L
        @JvmField
        val getEnabledComponentsBind =
            ObjectCalls.getMethodBind("OpenXRSpatialCapabilityConfigurationPlaneTracking", "get_enabled_components", GET_ENABLED_COMPONENTS_HASH)
    }
}
