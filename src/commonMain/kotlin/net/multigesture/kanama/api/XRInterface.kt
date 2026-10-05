package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Projection
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Base class for an XR interface implementation.
 *
 * Generated from Godot docs: XRInterface
 */
open class XRInterface(handle: GodotHandle) : RefCounted(handle) {
    var interfaceIsPrimary: Boolean
        @JvmName("interfaceIsPrimaryProperty")
        get() = isPrimary()
        @JvmName("setInterfaceIsPrimaryProperty")
        set(value) = setPrimary(value)

    val xrPlayAreaMode: XRInterface.PlayAreaMode
        @JvmName("xrPlayAreaModeProperty")
        get() = getPlayAreaMode()

    val environmentBlendMode: XRInterface.EnvironmentBlendMode
        @JvmName("environmentBlendModeProperty")
        get() = getEnvironmentBlendMode()

    var arIsAnchorDetectionEnabled: Boolean
        @JvmName("arIsAnchorDetectionEnabledProperty")
        get() = getAnchorDetectionIsEnabled()
        @JvmName("setArIsAnchorDetectionEnabledProperty")
        set(value) = setAnchorDetectionIsEnabled(value)

    /**
     * Returns the name of this interface (`"OpenXR"`, `"OpenVR"`, `"OpenHMD"`, `"ARKit"`, etc.).
     *
     * Generated from Godot docs: XRInterface.get_name
     */
    fun getName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(getNameBind, segment)
    }

    /**
     * Returns a combination of `Capabilities` flags providing information about the capabilities of
     * this interface.
     *
     * Generated from Godot docs: XRInterface.get_capabilities
     */
    fun getCapabilities(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCapabilitiesBind, segment)
    }

    /**
     * `true` if this is the primary interface.
     *
     * Generated from Godot docs: XRInterface.is_primary
     */
    fun isPrimary(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isPrimaryBind, segment)
    }

    /**
     * `true` if this is the primary interface.
     *
     * Generated from Godot docs: XRInterface.set_primary
     */
    fun setPrimary(primary: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPrimaryBind, segment, primary)
    }

    /**
     * Returns `true` if this interface has been initialized.
     *
     * Generated from Godot docs: XRInterface.is_initialized
     */
    fun isInitialized(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isInitializedBind, segment)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(initializeBind, segment)
    }

    /**
     * Turns the interface off.
     *
     * Generated from Godot docs: XRInterface.uninitialize
     */
    fun uninitialize() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(uninitializeBind, segment)
    }

    /**
     * Returns a `Dictionary` with extra system info. Interfaces are expected to return `XRRuntimeName`
     * and `XRRuntimeVersion` providing info about the used XR runtime. Additional entries may be
     * provided specific to an interface. Note:This information may only be available after
     * `initialize` was successfully called.
     *
     * Generated from Godot docs: XRInterface.get_system_info
     */
    fun getSystemInfo(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(getSystemInfoBind, segment)
    }

    /**
     * If supported, returns the status of our tracking. This will allow you to provide feedback to the
     * user whether there are issues with positional tracking.
     *
     * Generated from Godot docs: XRInterface.get_tracking_status
     */
    fun getTrackingStatus(): XRInterface.TrackingStatus {
        checkOpen()
        return XRInterface.TrackingStatus(ObjectCalls.ptrcallNoArgsRetLong(getTrackingStatusBind, segment))
    }

    /**
     * Returns the resolution at which we should render our intermediate results before things like
     * lens distortion are applied by the VR platform.
     *
     * Generated from Godot docs: XRInterface.get_render_target_size
     */
    fun getRenderTargetSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getRenderTargetSizeBind, segment)
    }

    /**
     * Returns the number of views that need to be rendered for this device. 1 for Monoscopic, 2 for
     * Stereoscopic.
     *
     * Generated from Godot docs: XRInterface.get_view_count
     */
    fun getViewCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(getViewCountBind, segment)
    }

    /**
     * Triggers a haptic pulse on a device associated with this interface. `action_name` is the name of
     * the action for this pulse. `tracker_name` is optional and can be used to direct the pulse to a
     * specific device provided that device is bound to this haptic. `frequency` is the frequency of
     * the pulse, set to `0.0` to have the system use a default frequency. `amplitude` is the amplitude
     * of the pulse between `0.0` and `1.0`. `duration_sec` is the duration of the pulse in seconds.
     * `delay_sec` is a delay in seconds before the pulse is given.
     *
     * Generated from Godot docs: XRInterface.trigger_haptic_pulse
     */
    fun triggerHapticPulse(actionName: String, trackerName: String, frequency: Double, amplitude: Double, durationSec: Double, delaySec: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithStringStringNameFourDoubleArgs(triggerHapticPulseBind, segment, actionName, trackerName, frequency, amplitude, durationSec, delaySec)
    }

    /**
     * Call this to find out if a given play area mode is supported by this interface.
     *
     * Generated from Godot docs: XRInterface.supports_play_area_mode
     */
    fun supportsPlayAreaMode(mode: XRInterface.PlayAreaMode): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetBool(supportsPlayAreaModeBind, segment, mode.value)
    }

    /**
     * The play area mode for this interface.
     *
     * Generated from Godot docs: XRInterface.get_play_area_mode
     */
    fun getPlayAreaMode(): XRInterface.PlayAreaMode {
        checkOpen()
        return XRInterface.PlayAreaMode(ObjectCalls.ptrcallNoArgsRetLong(getPlayAreaModeBind, segment))
    }

    /**
     * The play area mode for this interface.
     *
     * Generated from Godot docs: XRInterface.set_play_area_mode
     */
    fun setPlayAreaMode(mode: XRInterface.PlayAreaMode): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetBool(setPlayAreaModeBind, segment, mode.value)
    }

    /**
     * Returns an array of vectors that represent the physical play area mapped to the virtual space
     * around the `XROrigin3D` point. The points form a convex polygon that can be used to react to or
     * visualize the play area. This returns an empty array if this feature is not supported or if the
     * information is not yet available.
     *
     * Generated from Godot docs: XRInterface.get_play_area
     */
    fun getPlayArea(): List<Vector3> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getPlayAreaBind, segment)
    }

    /**
     * On an AR interface, `true` if anchor detection is enabled.
     *
     * Generated from Godot docs: XRInterface.get_anchor_detection_is_enabled
     */
    fun getAnchorDetectionIsEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getAnchorDetectionIsEnabledBind, segment)
    }

    /**
     * On an AR interface, `true` if anchor detection is enabled.
     *
     * Generated from Godot docs: XRInterface.set_anchor_detection_is_enabled
     */
    fun setAnchorDetectionIsEnabled(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setAnchorDetectionIsEnabledBind, segment, enable)
    }

    /**
     * If this is an AR interface that requires displaying a camera feed as the background, this method
     * returns the feed ID in the `CameraServer` for this interface.
     *
     * Generated from Godot docs: XRInterface.get_camera_feed_id
     */
    fun getCameraFeedId(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getCameraFeedIdBind, segment)
    }

    /**
     * Returns `true` if this interface supports passthrough.
     *
     * Generated from Godot docs: XRInterface.is_passthrough_supported
     */
    fun isPassthroughSupported(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isPassthroughSupportedBind, segment)
    }

    /**
     * Returns `true` if passthrough is enabled.
     *
     * Generated from Godot docs: XRInterface.is_passthrough_enabled
     */
    fun isPassthroughEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isPassthroughEnabledBind, segment)
    }

    /**
     * Starts passthrough, will return `false` if passthrough couldn't be started. Note: The viewport
     * used for XR must have a transparent background, otherwise passthrough may not properly render.
     *
     * Generated from Godot docs: XRInterface.start_passthrough
     */
    fun startPassthrough(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(startPassthroughBind, segment)
    }

    /**
     * Stops passthrough.
     *
     * Generated from Godot docs: XRInterface.stop_passthrough
     */
    fun stopPassthrough() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(stopPassthroughBind, segment)
    }

    /**
     * Returns the transform for a view/eye. `view` is the view/eye index. `cam_transform` is the
     * transform that maps device coordinates to scene coordinates, typically the
     * `Node3D.global_transform` of the current XROrigin3D.
     *
     * Generated from Godot docs: XRInterface.get_transform_for_view
     */
    fun getTransformForView(view: Long, camTransform: Transform3D): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithUInt32Transform3DArgsRetTransform3D(getTransformForViewBind, segment, view, camTransform)
    }

    /**
     * Returns the projection matrix for a view/eye.
     *
     * Generated from Godot docs: XRInterface.get_projection_for_view
     */
    fun getProjectionForView(view: Long, aspect: Double, near: Double, far: Double): Projection {
        checkOpen()
        return ObjectCalls.ptrcallWithUInt32ThreeDoubleArgsRetProjection(getProjectionForViewBind, segment, view, aspect, near, far)
    }

    /**
     * Returns the an array of supported environment blend modes, see
     * `XRInterface.EnvironmentBlendMode`.
     *
     * Generated from Godot docs: XRInterface.get_supported_environment_blend_modes
     */
    fun getSupportedEnvironmentBlendModes(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(getSupportedEnvironmentBlendModesBind, segment)
    }

    /**
     * Specify how XR should blend in the environment. This is specific to certain AR and passthrough
     * devices where camera images are blended in by the XR compositor.
     *
     * Generated from Godot docs: XRInterface.set_environment_blend_mode
     */
    fun setEnvironmentBlendMode(mode: XRInterface.EnvironmentBlendMode): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetBool(setEnvironmentBlendModeBind, segment, mode.value)
    }

    /**
     * Specify how XR should blend in the environment. This is specific to certain AR and passthrough
     * devices where camera images are blended in by the XR compositor.
     *
     * Generated from Godot docs: XRInterface.get_environment_blend_mode
     */
    fun getEnvironmentBlendMode(): XRInterface.EnvironmentBlendMode {
        checkOpen()
        return XRInterface.EnvironmentBlendMode(ObjectCalls.ptrcallNoArgsRetLong(getEnvironmentBlendModeBind, segment))
    }

    /** Signal `play_area_changed(mode: int)`; see [TypedSignal]. */
    val playAreaChanged: Signal1<Long>
        @JvmName("playAreaChangedTypedSignal")
        get() = Signal1(this, "play_area_changed", SignalArgType.LONG)

    object Signals {
        const val playAreaChanged: String = "play_area_changed"
    }

    /**
     * Godot's `XRInterface.Capabilities` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`XRInterface.Capabilities.<NAME>`).
     *
     * Generated from Godot docs: XRInterface.Capabilities
     */
    @JvmInline
    value class Capabilities(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No XR capabilities.
             *
             * Generated from Godot docs: XRInterface.XR_NONE
             */
            val NONE: Capabilities get() = Capabilities(0L)
            /**
             * This interface can work with normal rendering output (non-HMD based AR).
             *
             * Generated from Godot docs: XRInterface.XR_MONO
             */
            val MONO: Capabilities get() = Capabilities(1L)
            /**
             * This interface supports stereoscopic rendering.
             *
             * Generated from Godot docs: XRInterface.XR_STEREO
             */
            val STEREO: Capabilities get() = Capabilities(2L)
            /**
             * This interface supports quad rendering (not yet supported by Godot).
             *
             * Generated from Godot docs: XRInterface.XR_QUAD
             */
            val QUAD: Capabilities get() = Capabilities(4L)
            /**
             * This interface supports VR.
             *
             * Generated from Godot docs: XRInterface.XR_VR
             */
            val VR: Capabilities get() = Capabilities(8L)
            /**
             * This interface supports AR (video background and real world tracking).
             *
             * Generated from Godot docs: XRInterface.XR_AR
             */
            val AR: Capabilities get() = Capabilities(16L)
            /**
             * This interface outputs to an external device. If the main viewport is used, the on screen output
             * is an unmodified buffer of either the left or right eye (stretched if the viewport size is not
             * changed to the same aspect ratio of `get_render_target_size`). Using a separate viewport node
             * frees up the main viewport for other purposes.
             *
             * Generated from Godot docs: XRInterface.XR_EXTERNAL
             */
            val EXTERNAL: Capabilities get() = Capabilities(32L)
        }
    }

    /**
     * Godot's `XRInterface.TrackingStatus` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`XRInterface.TrackingStatus.<NAME>`).
     *
     * Generated from Godot docs: XRInterface.TrackingStatus
     */
    @JvmInline
    value class TrackingStatus(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Tracking is behaving as expected.
             *
             * Generated from Godot docs: XRInterface.XR_NORMAL_TRACKING
             */
            val NORMAL_TRACKING: TrackingStatus get() = TrackingStatus(0L)
            /**
             * Tracking is hindered by excessive motion (the player is moving faster than tracking can keep
             * up).
             *
             * Generated from Godot docs: XRInterface.XR_EXCESSIVE_MOTION
             */
            val EXCESSIVE_MOTION: TrackingStatus get() = TrackingStatus(1L)
            /**
             * Tracking is hindered by insufficient features, it's too dark (for camera-based tracking), player
             * is blocked, etc.
             *
             * Generated from Godot docs: XRInterface.XR_INSUFFICIENT_FEATURES
             */
            val INSUFFICIENT_FEATURES: TrackingStatus get() = TrackingStatus(2L)
            /**
             * We don't know the status of the tracking or this interface does not provide feedback.
             *
             * Generated from Godot docs: XRInterface.XR_UNKNOWN_TRACKING
             */
            val UNKNOWN_TRACKING: TrackingStatus get() = TrackingStatus(3L)
            /**
             * Tracking is not functional (camera not plugged in or obscured, lighthouses turned off, etc.).
             *
             * Generated from Godot docs: XRInterface.XR_NOT_TRACKING
             */
            val NOT_TRACKING: TrackingStatus get() = TrackingStatus(4L)
        }
    }

    /**
     * Godot's `XRInterface.PlayAreaMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`XRInterface.PlayAreaMode.<NAME>`).
     *
     * Generated from Godot docs: XRInterface.PlayAreaMode
     */
    @JvmInline
    value class PlayAreaMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Play area mode not set or not available.
             *
             * Generated from Godot docs: XRInterface.XR_PLAY_AREA_UNKNOWN
             */
            val UNKNOWN: PlayAreaMode get() = PlayAreaMode(0L)
            /**
             * Play area only supports orientation tracking, no positional tracking, area will center around
             * player.
             *
             * Generated from Godot docs: XRInterface.XR_PLAY_AREA_3DOF
             */
            val AREA_3DOF: PlayAreaMode get() = PlayAreaMode(1L)
            /**
             * Player is in seated position, limited positional tracking, fixed guardian around player.
             *
             * Generated from Godot docs: XRInterface.XR_PLAY_AREA_SITTING
             */
            val SITTING: PlayAreaMode get() = PlayAreaMode(2L)
            /**
             * Player is free to move around, full positional tracking.
             *
             * Generated from Godot docs: XRInterface.XR_PLAY_AREA_ROOMSCALE
             */
            val ROOMSCALE: PlayAreaMode get() = PlayAreaMode(3L)
            /**
             * Same as `PlayAreaMode.ROOMSCALE` but origin point is fixed to the center of the physical space.
             * In this mode, system-level recentering may be disabled, requiring the use of
             * `XRServer.center_on_hmd`.
             *
             * Generated from Godot docs: XRInterface.XR_PLAY_AREA_STAGE
             */
            val STAGE: PlayAreaMode get() = PlayAreaMode(4L)
            /**
             * Custom play area set by a GDExtension.
             *
             * Generated from Godot docs: XRInterface.XR_PLAY_AREA_CUSTOM
             */
            val CUSTOM: PlayAreaMode get() = PlayAreaMode(2147483647L)
        }
    }

    /**
     * Godot's `XRInterface.EnvironmentBlendMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`XRInterface.EnvironmentBlendMode.<NAME>`).
     *
     * Generated from Godot docs: XRInterface.EnvironmentBlendMode
     */
    @JvmInline
    value class EnvironmentBlendMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Opaque blend mode. This is typically used for VR devices.
             *
             * Generated from Godot docs: XRInterface.XR_ENV_BLEND_MODE_OPAQUE
             */
            val OPAQUE: EnvironmentBlendMode get() = EnvironmentBlendMode(0L)
            /**
             * Additive blend mode. This is typically used for AR devices or VR devices with passthrough.
             *
             * Generated from Godot docs: XRInterface.XR_ENV_BLEND_MODE_ADDITIVE
             */
            val ADDITIVE: EnvironmentBlendMode get() = EnvironmentBlendMode(1L)
            /**
             * Alpha blend mode. This is typically used for AR or VR devices with passthrough capabilities. The
             * alpha channel controls how much of the passthrough is visible. Alpha of 0.0 means the
             * passthrough is visible and this pixel works in ADDITIVE mode. Alpha of 1.0 means that the
             * passthrough is not visible and this pixel works in OPAQUE mode.
             *
             * Generated from Godot docs: XRInterface.XR_ENV_BLEND_MODE_ALPHA_BLEND
             */
            val ALPHA_BLEND: EnvironmentBlendMode get() = EnvironmentBlendMode(2L)
        }
    }

    /**
     * Godot's `XRInterface.VRSTextureFormat` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`XRInterface.VRSTextureFormat.<NAME>`).
     *
     * Generated from Godot docs: XRInterface.VRSTextureFormat
     */
    @JvmInline
    value class VRSTextureFormat(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The texture format is the same as returned by `XRVRS.make_vrs_texture`.
             *
             * Generated from Godot docs: XRInterface.XR_VRS_TEXTURE_FORMAT_UNIFIED
             */
            val UNIFIED: VRSTextureFormat get() = VRSTextureFormat(0L)
            /**
             * The texture format is the same as expected by the Vulkan `VK_KHR_fragment_shading_rate`
             * extension.
             *
             * Generated from Godot docs: XRInterface.XR_VRS_TEXTURE_FORMAT_FRAGMENT_SHADING_RATE
             */
            val FRAGMENT_SHADING_RATE: VRSTextureFormat get() = VRSTextureFormat(1L)
            /**
             * The texture format is the same as expected by the Vulkan `VK_EXT_fragment_density_map`
             * extension.
             *
             * Generated from Godot docs: XRInterface.XR_VRS_TEXTURE_FORMAT_FRAGMENT_DENSITY_MAP
             */
            val FRAGMENT_DENSITY_MAP: VRSTextureFormat get() = VRSTextureFormat(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRInterface? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): XRInterface? =
            if (handle.address() == 0L) null else RefCounted.owned(XRInterface(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): XRInterface? =
            if (handle.address() == 0L) null else XRInterface(GodotHandle(handle))

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
