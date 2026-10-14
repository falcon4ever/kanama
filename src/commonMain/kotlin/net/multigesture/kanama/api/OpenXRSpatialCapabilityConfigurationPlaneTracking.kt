package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Configuration header for plane tracking.
 *
 * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationPlaneTracking
 */
class OpenXRSpatialCapabilityConfigurationPlaneTracking(handle: GodotHandle) : OpenXRSpatialCapabilityConfigurationBaseHeader(handle) {
    /**
     * Returns `true` if we support the mesh 2D component (only valid after the OpenXR session has
     * started). You can query these using the `OpenXRSpatialComponentMesh2DList` data object.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationPlaneTracking.supports_mesh_2d
     */
    fun supportsMesh2d(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.supportsMesh2dBind, segment)
    }

    /**
     * Returns `true` if we support the polygon 2D component (only valid after the OpenXR session has
     * started). You can query these using the `OpenXRSpatialComponentPolygon2DList` data object.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationPlaneTracking.supports_polygons
     */
    fun supportsPolygons(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.supportsPolygonsBind, segment)
    }

    /**
     * Returns `true` if we support the plane semantic label component (only valid after the OpenXR
     * session has started). You can query these using the
     * `OpenXRSpatialComponentPlaneSemanticLabelList` data object.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationPlaneTracking.supports_labels
     */
    fun supportsLabels(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.supportsLabelsBind, segment)
    }

    /**
     * Returns the components enabled by this configuration. Note: Only valid after this configuration
     * was used to create a spatial context.
     *
     * Generated from Godot docs: OpenXRSpatialCapabilityConfigurationPlaneTracking.get_enabled_components
     */
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
