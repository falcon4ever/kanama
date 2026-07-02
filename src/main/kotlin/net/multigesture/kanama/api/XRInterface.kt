package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Base class for an XR interface implementation.
 *
 * Generated from Godot docs: XRInterface
 */
open class XRInterface(handle: MemorySegment) : RefCounted(handle) {
    var interfaceIsPrimary: Boolean
        @JvmName("interfaceIsPrimaryProperty")
        get() = isPrimary()
        @JvmName("setInterfaceIsPrimaryProperty")
        set(value) = setPrimary(value)

    val xrPlayAreaMode: Long
        @JvmName("xrPlayAreaModeProperty")
        get() = getPlayAreaMode()

    val environmentBlendMode: Long
        @JvmName("environmentBlendModeProperty")
        get() = getEnvironmentBlendMode()

    var arIsAnchorDetectionEnabled: Boolean
        @JvmName("arIsAnchorDetectionEnabledProperty")
        get() = getAnchorDetectionIsEnabled()
        @JvmName("setArIsAnchorDetectionEnabledProperty")
        set(value) = setAnchorDetectionIsEnabled(value)

    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getNameBind, handle)
    }

    fun getCapabilities(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCapabilitiesBind, handle)
    }

    fun isPrimary(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPrimaryBind, handle)
    }

    fun setPrimary(primary: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPrimaryBind, handle, primary)
    }

    fun isInitialized(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInitializedBind, handle)
    }

    /**
     * Call this to initialize this interface. The first interface that is initialized is identified as
     * the primary interface and it will be used for rendering output. After initializing the interface
     * you want to use you then need to enable the AR/VR mode of a viewport and rendering should
     * commence. Note: You must enable the XR mode on the main viewport for any device that uses the
     * main output of Godot, such as for mobile VR. If you do this for a platform that handles its own
     * output (such as OpenVR) Godot will show just one eye without distortion on screen.
     * Alternatively, you can add a separate viewport node to your scene and enable AR/VR on that
     * viewport. It will be used to output to the HMD, leaving you free to do anything you like in the
     * main window, such as using a separate camera as a spectator camera or rendering something
     * completely different. While currently not used, you can activate additional interfaces. You may
     * wish to do this if you want to track controllers from other platforms. However, at this point in
     * time only one interface can render to an HMD.
     *
     * Generated from Godot docs: XRInterface.initialize
     */
    fun initialize(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(initializeBind, handle)
    }

    /**
     * Turns the interface off.
     *
     * Generated from Godot docs: XRInterface.uninitialize
     */
    fun uninitialize() {
        ObjectCalls.ptrcallNoArgs(uninitializeBind, handle)
    }

    fun getSystemInfo(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getSystemInfoBind, handle)
    }

    fun getTrackingStatus(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTrackingStatusBind, handle)
    }

    fun getRenderTargetSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getRenderTargetSizeBind, handle)
    }

    fun getViewCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getViewCountBind, handle)
    }

    fun triggerHapticPulse(actionName: String, trackerName: String, frequency: Double, amplitude: Double, durationSec: Double, delaySec: Double) {
        ObjectCalls.ptrcallWithStringStringNameFourDoubleArgs(triggerHapticPulseBind, handle, actionName, trackerName, frequency, amplitude, durationSec, delaySec)
    }

    fun supportsPlayAreaMode(mode: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(supportsPlayAreaModeBind, handle, mode)
    }

    fun getPlayAreaMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPlayAreaModeBind, handle)
    }

    fun setPlayAreaMode(mode: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(setPlayAreaModeBind, handle, mode)
    }

    fun getPlayArea(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getPlayAreaBind, handle)
    }

    fun getAnchorDetectionIsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAnchorDetectionIsEnabledBind, handle)
    }

    fun setAnchorDetectionIsEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAnchorDetectionIsEnabledBind, handle, enable)
    }

    fun getCameraFeedId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCameraFeedIdBind, handle)
    }

    fun isPassthroughSupported(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPassthroughSupportedBind, handle)
    }

    fun isPassthroughEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPassthroughEnabledBind, handle)
    }

    fun startPassthrough(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(startPassthroughBind, handle)
    }

    fun stopPassthrough() {
        ObjectCalls.ptrcallNoArgs(stopPassthroughBind, handle)
    }

    fun getTransformForView(view: Long, camTransform: Transform3D): Transform3D {
        return ObjectCalls.ptrcallWithUInt32Transform3DArgsRetTransform3D(getTransformForViewBind, handle, view, camTransform)
    }

    fun getProjectionForView(view: Long, aspect: Double, near: Double, far: Double): Projection {
        return ObjectCalls.ptrcallWithUInt32ThreeDoubleArgsRetProjection(getProjectionForViewBind, handle, view, aspect, near, far)
    }

    fun getSupportedEnvironmentBlendModes(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getSupportedEnvironmentBlendModesBind, handle)
    }

    fun setEnvironmentBlendMode(mode: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(setEnvironmentBlendModeBind, handle, mode)
    }

    fun getEnvironmentBlendMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getEnvironmentBlendModeBind, handle)
    }

    object Signals {
        const val playAreaChanged: String = "play_area_changed"
    }

    companion object {
        const val XR_NONE: Long = 0L
        const val XR_MONO: Long = 1L
        const val XR_STEREO: Long = 2L
        const val XR_QUAD: Long = 4L
        const val XR_VR: Long = 8L
        const val XR_AR: Long = 16L
        const val XR_EXTERNAL: Long = 32L
        const val XR_NORMAL_TRACKING: Long = 0L
        const val XR_EXCESSIVE_MOTION: Long = 1L
        const val XR_INSUFFICIENT_FEATURES: Long = 2L
        const val XR_UNKNOWN_TRACKING: Long = 3L
        const val XR_NOT_TRACKING: Long = 4L
        const val XR_PLAY_AREA_UNKNOWN: Long = 0L
        const val XR_PLAY_AREA_3DOF: Long = 1L
        const val XR_PLAY_AREA_SITTING: Long = 2L
        const val XR_PLAY_AREA_ROOMSCALE: Long = 3L
        const val XR_PLAY_AREA_STAGE: Long = 4L
        const val XR_PLAY_AREA_CUSTOM: Long = 2147483647L
        const val XR_ENV_BLEND_MODE_OPAQUE: Long = 0L
        const val XR_ENV_BLEND_MODE_ADDITIVE: Long = 1L
        const val XR_ENV_BLEND_MODE_ALPHA_BLEND: Long = 2L
        const val XR_VRS_TEXTURE_FORMAT_UNIFIED: Long = 0L
        const val XR_VRS_TEXTURE_FORMAT_FRAGMENT_SHADING_RATE: Long = 1L
        const val XR_VRS_TEXTURE_FORMAT_FRAGMENT_DENSITY_MAP: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRInterface? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRInterface? =
            if (handle.address() == 0L) null else XRInterface(handle)

        private const val GET_NAME_HASH = 2002593661L
        private val getNameBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_name", GET_NAME_HASH)
        }

        private const val GET_CAPABILITIES_HASH = 3905245786L
        private val getCapabilitiesBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_capabilities", GET_CAPABILITIES_HASH)
        }

        private const val IS_PRIMARY_HASH = 2240911060L
        private val isPrimaryBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "is_primary", IS_PRIMARY_HASH)
        }

        private const val SET_PRIMARY_HASH = 2586408642L
        private val setPrimaryBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "set_primary", SET_PRIMARY_HASH)
        }

        private const val IS_INITIALIZED_HASH = 36873697L
        private val isInitializedBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "is_initialized", IS_INITIALIZED_HASH)
        }

        private const val INITIALIZE_HASH = 2240911060L
        private val initializeBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "initialize", INITIALIZE_HASH)
        }

        private const val UNINITIALIZE_HASH = 3218959716L
        private val uninitializeBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "uninitialize", UNINITIALIZE_HASH)
        }

        private const val GET_SYSTEM_INFO_HASH = 2382534195L
        private val getSystemInfoBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_system_info", GET_SYSTEM_INFO_HASH)
        }

        private const val GET_TRACKING_STATUS_HASH = 167423259L
        private val getTrackingStatusBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_tracking_status", GET_TRACKING_STATUS_HASH)
        }

        private const val GET_RENDER_TARGET_SIZE_HASH = 1497962370L
        private val getRenderTargetSizeBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_render_target_size", GET_RENDER_TARGET_SIZE_HASH)
        }

        private const val GET_VIEW_COUNT_HASH = 2455072627L
        private val getViewCountBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_view_count", GET_VIEW_COUNT_HASH)
        }

        private const val TRIGGER_HAPTIC_PULSE_HASH = 3752640163L
        private val triggerHapticPulseBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "trigger_haptic_pulse", TRIGGER_HAPTIC_PULSE_HASH)
        }

        private const val SUPPORTS_PLAY_AREA_MODE_HASH = 3429955281L
        private val supportsPlayAreaModeBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "supports_play_area_mode", SUPPORTS_PLAY_AREA_MODE_HASH)
        }

        private const val GET_PLAY_AREA_MODE_HASH = 1615132885L
        private val getPlayAreaModeBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_play_area_mode", GET_PLAY_AREA_MODE_HASH)
        }

        private const val SET_PLAY_AREA_MODE_HASH = 3429955281L
        private val setPlayAreaModeBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "set_play_area_mode", SET_PLAY_AREA_MODE_HASH)
        }

        private const val GET_PLAY_AREA_HASH = 497664490L
        private val getPlayAreaBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_play_area", GET_PLAY_AREA_HASH)
        }

        private const val GET_ANCHOR_DETECTION_IS_ENABLED_HASH = 36873697L
        private val getAnchorDetectionIsEnabledBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_anchor_detection_is_enabled", GET_ANCHOR_DETECTION_IS_ENABLED_HASH)
        }

        private const val SET_ANCHOR_DETECTION_IS_ENABLED_HASH = 2586408642L
        private val setAnchorDetectionIsEnabledBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "set_anchor_detection_is_enabled", SET_ANCHOR_DETECTION_IS_ENABLED_HASH)
        }

        private const val GET_CAMERA_FEED_ID_HASH = 2455072627L
        private val getCameraFeedIdBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_camera_feed_id", GET_CAMERA_FEED_ID_HASH)
        }

        private const val IS_PASSTHROUGH_SUPPORTED_HASH = 2240911060L
        private val isPassthroughSupportedBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "is_passthrough_supported", IS_PASSTHROUGH_SUPPORTED_HASH)
        }

        private const val IS_PASSTHROUGH_ENABLED_HASH = 2240911060L
        private val isPassthroughEnabledBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "is_passthrough_enabled", IS_PASSTHROUGH_ENABLED_HASH)
        }

        private const val START_PASSTHROUGH_HASH = 2240911060L
        private val startPassthroughBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "start_passthrough", START_PASSTHROUGH_HASH)
        }

        private const val STOP_PASSTHROUGH_HASH = 3218959716L
        private val stopPassthroughBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "stop_passthrough", STOP_PASSTHROUGH_HASH)
        }

        private const val GET_TRANSFORM_FOR_VIEW_HASH = 518934792L
        private val getTransformForViewBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_transform_for_view", GET_TRANSFORM_FOR_VIEW_HASH)
        }

        private const val GET_PROJECTION_FOR_VIEW_HASH = 3766090294L
        private val getProjectionForViewBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_projection_for_view", GET_PROJECTION_FOR_VIEW_HASH)
        }

        private const val GET_SUPPORTED_ENVIRONMENT_BLEND_MODES_HASH = 2915620761L
        private val getSupportedEnvironmentBlendModesBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_supported_environment_blend_modes", GET_SUPPORTED_ENVIRONMENT_BLEND_MODES_HASH)
        }

        private const val SET_ENVIRONMENT_BLEND_MODE_HASH = 551152418L
        private val setEnvironmentBlendModeBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "set_environment_blend_mode", SET_ENVIRONMENT_BLEND_MODE_HASH)
        }

        private const val GET_ENVIRONMENT_BLEND_MODE_HASH = 1984334071L
        private val getEnvironmentBlendModeBind by lazy {
            ObjectCalls.getMethodBind("XRInterface", "get_environment_blend_mode", GET_ENVIRONMENT_BLEND_MODE_HASH)
        }
    }
}
