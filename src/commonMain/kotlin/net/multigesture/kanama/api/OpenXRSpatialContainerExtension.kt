package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * OpenXR extension that handles spatial containers.
 *
 * Generated from Godot docs: OpenXRSpatialContainerExtension
 */
class OpenXRSpatialContainerExtension(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    /**
     * Returns `true` if spatial containers are enabled in the project settings and available on the
     * current runtime.
     *
     * Generated from Godot docs: OpenXRSpatialContainerExtension.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEnabledBind, segment)
    }

    /**
     * Returns `true` if the main spatial container is active.
     *
     * Generated from Godot docs: OpenXRSpatialContainerExtension.is_spatial_container_active
     */
    fun isSpatialContainerActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSpatialContainerActiveBind, segment)
    }

    /**
     * Returns an array of supported spatial container bounds mode, see
     * `OpenXRSpatialContainerState.BoundsMode`.
     *
     * Generated from Godot docs: OpenXRSpatialContainerExtension.get_supported_bounds_modes
     */
    fun getSupportedBoundsModes(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getSupportedBoundsModesBind, segment)
    }

    /**
     * Retrieves the `OpenXRSpatialContainerState` of the main spatial container.
     *
     * Generated from Godot docs: OpenXRSpatialContainerExtension.get_spatial_container_state
     */
    fun getSpatialContainerState(): OpenXRSpatialContainerState? {
        return OpenXRSpatialContainerState.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getSpatialContainerStateBind, segment))
    }

    /**
     * Retrieves the bounds of the main spatial container.
     *
     * Generated from Godot docs: OpenXRSpatialContainerExtension.get_spatial_container_bounds
     */
    fun getSpatialContainerBounds(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getSpatialContainerBoundsBind, segment)
    }

    /**
     * Sends a request to the runtime to update the bounds mode of the main spatial container. If the
     * request is accepted, `spatial_container_bounds_changed` will be emitted with the updated bounds
     * mode. Otherwise, `spatial_container_bounds_mode_request_denied` will be emitted.
     *
     * Generated from Godot docs: OpenXRSpatialContainerExtension.request_spatial_container_bounds_mode
     */
    fun requestSpatialContainerBoundsMode(boundsMode: OpenXRSpatialContainerState.BoundsMode): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.requestSpatialContainerBoundsModeBind, segment, boundsMode.value)
    }

    /**
     * Sends a request to the runtime to update the visibility of the spatial container. If the request
     * is accepted, `spatial_container_visible_changed` will be emitted with the updated visibility.
     * Otherwise, `spatial_container_visible_request_denied` will be emitted.
     *
     * Generated from Godot docs: OpenXRSpatialContainerExtension.request_spatial_container_visible
     */
    fun requestSpatialContainerVisible(visible: Boolean): Boolean {
        return ObjectCalls.ptrcallWithBoolArgRetBool(Binds.requestSpatialContainerVisibleBind, segment, visible)
    }

    /** Signal `spatial_container_bounds_changed(spatial_container_rid: RID, spatial_container_infinite_bounds: bool, spatial_container_bounds_mode: int, spatial_container_bounds: Vector3)`; see [TypedSignal]. */
    val spatialContainerBoundsChanged: Signal4<RID, Boolean, Long, Vector3>
        @JvmName("spatialContainerBoundsChangedTypedSignal")
        get() = Signal4(this, "spatial_container_bounds_changed", SignalArgType.valueOf<RID>("RID", RID::class), SignalArgType.BOOLEAN, SignalArgType.LONG, SignalArgType.valueOf<Vector3>("Vector3", Vector3::class))

    /** Signal `spatial_container_bounds_mode_request_denied(spatial_container_rid: RID)`; see [TypedSignal]. */
    val spatialContainerBoundsModeRequestDenied: Signal1<RID>
        @JvmName("spatialContainerBoundsModeRequestDeniedTypedSignal")
        get() = Signal1(this, "spatial_container_bounds_mode_request_denied", SignalArgType.valueOf<RID>("RID", RID::class))

    /** Signal `spatial_container_closed(spatial_container_rid: RID)`; see [TypedSignal]. */
    val spatialContainerClosed: Signal1<RID>
        @JvmName("spatialContainerClosedTypedSignal")
        get() = Signal1(this, "spatial_container_closed", SignalArgType.valueOf<RID>("RID", RID::class))

    /** Signal `spatial_container_interactable_changed(spatial_container_rid: RID, spatial_container_interactable: bool)`; see [TypedSignal]. */
    val spatialContainerInteractableChanged: Signal2<RID, Boolean>
        @JvmName("spatialContainerInteractableChangedTypedSignal")
        get() = Signal2(this, "spatial_container_interactable_changed", SignalArgType.valueOf<RID>("RID", RID::class), SignalArgType.BOOLEAN)

    /** Signal `spatial_container_visible_changed(spatial_container_rid: RID, spatial_container_visible: bool)`; see [TypedSignal]. */
    val spatialContainerVisibleChanged: Signal2<RID, Boolean>
        @JvmName("spatialContainerVisibleChangedTypedSignal")
        get() = Signal2(this, "spatial_container_visible_changed", SignalArgType.valueOf<RID>("RID", RID::class), SignalArgType.BOOLEAN)

    /** Signal `spatial_container_visible_request_denied(spatial_container_rid: RID)`; see [TypedSignal]. */
    val spatialContainerVisibleRequestDenied: Signal1<RID>
        @JvmName("spatialContainerVisibleRequestDeniedTypedSignal")
        get() = Signal1(this, "spatial_container_visible_request_denied", SignalArgType.valueOf<RID>("RID", RID::class))

    object Signals {
        const val spatialContainerBoundsChanged: String = "spatial_container_bounds_changed"
        const val spatialContainerBoundsModeRequestDenied: String = "spatial_container_bounds_mode_request_denied"
        const val spatialContainerClosed: String = "spatial_container_closed"
        const val spatialContainerInteractableChanged: String = "spatial_container_interactable_changed"
        const val spatialContainerVisibleChanged: String = "spatial_container_visible_changed"
        const val spatialContainerVisibleRequestDenied: String = "spatial_container_visible_request_denied"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRSpatialContainerExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRSpatialContainerExtension? =
            if (handle.address() == 0L) null else OpenXRSpatialContainerExtension(GodotHandle(handle))
    }

    private object Binds {
        private const val IS_ENABLED_HASH = 36873697L
        @JvmField
        val isEnabledBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContainerExtension", "is_enabled", IS_ENABLED_HASH)

        private const val IS_SPATIAL_CONTAINER_ACTIVE_HASH = 36873697L
        @JvmField
        val isSpatialContainerActiveBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContainerExtension", "is_spatial_container_active", IS_SPATIAL_CONTAINER_ACTIVE_HASH)

        private const val GET_SUPPORTED_BOUNDS_MODES_HASH = 2915620761L
        @JvmField
        val getSupportedBoundsModesBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContainerExtension", "get_supported_bounds_modes", GET_SUPPORTED_BOUNDS_MODES_HASH)

        private const val GET_SPATIAL_CONTAINER_STATE_HASH = 212870561L
        @JvmField
        val getSpatialContainerStateBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContainerExtension", "get_spatial_container_state", GET_SPATIAL_CONTAINER_STATE_HASH)

        private const val GET_SPATIAL_CONTAINER_BOUNDS_HASH = 3783033775L
        @JvmField
        val getSpatialContainerBoundsBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContainerExtension", "get_spatial_container_bounds", GET_SPATIAL_CONTAINER_BOUNDS_HASH)

        private const val REQUEST_SPATIAL_CONTAINER_BOUNDS_MODE_HASH = 1261129373L
        @JvmField
        val requestSpatialContainerBoundsModeBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContainerExtension", "request_spatial_container_bounds_mode", REQUEST_SPATIAL_CONTAINER_BOUNDS_MODE_HASH)

        private const val REQUEST_SPATIAL_CONTAINER_VISIBLE_HASH = 2703660260L
        @JvmField
        val requestSpatialContainerVisibleBind =
            ObjectCalls.getMethodBind("OpenXRSpatialContainerExtension", "request_spatial_container_visible", REQUEST_SPATIAL_CONTAINER_VISIBLE_HASH)
    }
}
