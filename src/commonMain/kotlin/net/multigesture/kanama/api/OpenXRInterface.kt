package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Vector3

/**
 * Our OpenXR interface.
 *
 * Generated from Godot docs: OpenXRInterface
 */
class OpenXRInterface(handle: GodotHandle) : XRInterface(handle) {
    var displayRefreshRate: Double
        @JvmName("displayRefreshRateProperty")
        get() = getDisplayRefreshRate()
        @JvmName("setDisplayRefreshRateProperty")
        set(value) = setDisplayRefreshRate(value)

    var renderTargetSizeMultiplier: Double
        @JvmName("renderTargetSizeMultiplierProperty")
        get() = getRenderTargetSizeMultiplier()
        @JvmName("setRenderTargetSizeMultiplierProperty")
        set(value) = setRenderTargetSizeMultiplier(value)

    var foveationLevel: Int
        @JvmName("foveationLevelProperty")
        get() = getFoveationLevel()
        @JvmName("setFoveationLevelProperty")
        set(value) = setFoveationLevel(value)

    var foveationDynamic: Boolean
        @JvmName("foveationDynamicProperty")
        get() = getFoveationDynamic()
        @JvmName("setFoveationDynamicProperty")
        set(value) = setFoveationDynamic(value)

    var foveationWithSubsampledImages: Boolean
        @JvmName("foveationWithSubsampledImagesProperty")
        get() = getFoveationWithSubsampledImages()
        @JvmName("setFoveationWithSubsampledImagesProperty")
        set(value) = setFoveationWithSubsampledImages(value)

    var vrsMinRadius: Double
        @JvmName("vrsMinRadiusProperty")
        get() = getVrsMinRadius()
        @JvmName("setVrsMinRadiusProperty")
        set(value) = setVrsMinRadius(value)

    var vrsStrength: Double
        @JvmName("vrsStrengthProperty")
        get() = getVrsStrength()
        @JvmName("setVrsStrengthProperty")
        set(value) = setVrsStrength(value)

    /**
     * Returns the current state of our OpenXR session.
     *
     * Generated from Godot docs: OpenXRInterface.get_session_state
     */
    fun getSessionState(): OpenXRInterface.SessionState {
        checkOpen()
        return OpenXRInterface.SessionState(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSessionStateBind, segment))
    }

    /**
     * Returns `true` if OpenXR's user presence extension is supported and enabled. Note: This only
     * returns a valid value after OpenXR has been initialized.
     *
     * Generated from Godot docs: OpenXRInterface.is_user_presence_supported
     */
    fun isUserPresenceSupported(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUserPresenceSupportedBind, segment)
    }

    /**
     * Returns `true` if system has detected the presence of a user in the XR experience.
     *
     * Generated from Godot docs: OpenXRInterface.is_user_present
     */
    fun isUserPresent(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUserPresentBind, segment)
    }

    /**
     * The display refresh rate for the current HMD. Only functional if this feature is supported by
     * the OpenXR runtime and after the interface has been initialized.
     *
     * Generated from Godot docs: OpenXRInterface.get_display_refresh_rate
     */
    fun getDisplayRefreshRate(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDisplayRefreshRateBind, segment)
    }

    /**
     * The display refresh rate for the current HMD. Only functional if this feature is supported by
     * the OpenXR runtime and after the interface has been initialized.
     *
     * Generated from Godot docs: OpenXRInterface.set_display_refresh_rate
     */
    fun setDisplayRefreshRate(refreshRate: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDisplayRefreshRateBind, segment, refreshRate)
    }

    /**
     * The render size multiplier for the current HMD. Must be set before the interface has been
     * initialized.
     *
     * Generated from Godot docs: OpenXRInterface.get_render_target_size_multiplier
     */
    fun getRenderTargetSizeMultiplier(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRenderTargetSizeMultiplierBind, segment)
    }

    /**
     * The render size multiplier for the current HMD. Must be set before the interface has been
     * initialized.
     *
     * Generated from Godot docs: OpenXRInterface.set_render_target_size_multiplier
     */
    fun setRenderTargetSizeMultiplier(multiplier: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRenderTargetSizeMultiplierBind, segment, multiplier)
    }

    /**
     * Returns `true` if OpenXR's foveation extension is supported. The interface must be initialized
     * before this returns a valid value. Note: When using the Vulkan rendering driver,
     * `Viewport.vrs_mode` must be set to `Viewport.VRSMode.XR` to support foveation.
     *
     * Generated from Godot docs: OpenXRInterface.is_foveation_supported
     */
    fun isFoveationSupported(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isFoveationSupportedBind, segment)
    }

    /**
     * The foveation level, from `0` (off) to `3` (high). The interface must be initialized before this
     * is accessible.
     *
     * Generated from Godot docs: OpenXRInterface.get_foveation_level
     */
    fun getFoveationLevel(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFoveationLevelBind, segment)
    }

    /**
     * The foveation level, from `0` (off) to `3` (high). The interface must be initialized before this
     * is accessible.
     *
     * Generated from Godot docs: OpenXRInterface.set_foveation_level
     */
    fun setFoveationLevel(foveationLevel: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setFoveationLevelBind, segment, foveationLevel)
    }

    /**
     * If `true`, enables dynamic foveation adjustment. The interface must be initialized before this
     * is accessible. If enabled, foveation will automatically be adjusted between low and
     * `foveation_level`.
     *
     * Generated from Godot docs: OpenXRInterface.get_foveation_dynamic
     */
    fun getFoveationDynamic(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getFoveationDynamicBind, segment)
    }

    /**
     * If `true`, enables dynamic foveation adjustment. The interface must be initialized before this
     * is accessible. If enabled, foveation will automatically be adjusted between low and
     * `foveation_level`.
     *
     * Generated from Godot docs: OpenXRInterface.set_foveation_dynamic
     */
    fun setFoveationDynamic(foveationDynamic: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setFoveationDynamicBind, segment, foveationDynamic)
    }

    /**
     * If `true`, enables subsampled images with foveation, which can provide a performance boost on
     * Vulkan.
     *
     * Generated from Godot docs: OpenXRInterface.get_foveation_with_subsampled_images
     */
    fun getFoveationWithSubsampledImages(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getFoveationWithSubsampledImagesBind, segment)
    }

    /**
     * If `true`, enables subsampled images with foveation, which can provide a performance boost on
     * Vulkan.
     *
     * Generated from Godot docs: OpenXRInterface.set_foveation_with_subsampled_images
     */
    fun setFoveationWithSubsampledImages(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setFoveationWithSubsampledImagesBind, segment, enabled)
    }

    /**
     * Returns `true` if the given action set is active.
     *
     * Generated from Godot docs: OpenXRInterface.is_action_set_active
     */
    fun isActionSetActive(name: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.isActionSetActiveBind, segment, name)
    }

    /**
     * Sets the given action set as active or inactive.
     *
     * Generated from Godot docs: OpenXRInterface.set_action_set_active
     */
    fun setActionSetActive(name: String, active: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndBoolArg(Binds.setActionSetActiveBind, segment, name, active)
    }

    /**
     * Returns a list of action sets registered with Godot (loaded from the action map at runtime).
     *
     * Generated from Godot docs: OpenXRInterface.get_action_sets
     */
    fun getActionSets(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getActionSetsBind, segment)
    }

    /**
     * Returns a list of display refresh rates supported by the current HMD. Only returned if this
     * feature is supported by the OpenXR runtime and after the interface has been initialized.
     *
     * Generated from Godot docs: OpenXRInterface.get_available_display_refresh_rates
     */
    fun getAvailableDisplayRefreshRates(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getAvailableDisplayRefreshRatesBind, segment)
    }

    /**
     * If handtracking is enabled and motion range is supported, sets the currently configured motion
     * range for `hand` to `motion_range`.
     *
     * Generated from Godot docs: OpenXRInterface.set_motion_range
     */
    fun setMotionRange(hand: OpenXRInterface.Hand, motionRange: OpenXRInterface.HandMotionRange) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoLongArgs(Binds.setMotionRangeBind, segment, hand.value, motionRange.value)
    }

    /**
     * If handtracking is enabled and motion range is supported, gets the currently configured motion
     * range for `hand`.
     *
     * Generated from Godot docs: OpenXRInterface.get_motion_range
     */
    fun getMotionRange(hand: OpenXRInterface.Hand): OpenXRInterface.HandMotionRange {
        checkOpen()
        return OpenXRInterface.HandMotionRange(ObjectCalls.ptrcallWithLongArgRetLong(Binds.getMotionRangeBind, segment, hand.value))
    }

    /**
     * If handtracking is enabled and hand tracking source is supported, gets the source of the hand
     * tracking data for `hand`.
     *
     * Generated from Godot docs: OpenXRInterface.get_hand_tracking_source
     */
    fun getHandTrackingSource(hand: OpenXRInterface.Hand): OpenXRInterface.HandTrackedSource {
        checkOpen()
        return OpenXRInterface.HandTrackedSource(ObjectCalls.ptrcallWithLongArgRetLong(Binds.getHandTrackingSourceBind, segment, hand.value))
    }

    /**
     * If handtracking is enabled, returns flags that inform us of the validity of the tracking data.
     *
     * Generated from Godot docs: OpenXRInterface.get_hand_joint_flags
     */
    fun getHandJointFlags(hand: OpenXRInterface.Hand, joint: OpenXRInterface.HandJoints): OpenXRInterface.HandJointFlags {
        checkOpen()
        return OpenXRInterface.HandJointFlags(ObjectCalls.ptrcallWithTwoLongArgsRetLong(Binds.getHandJointFlagsBind, segment, hand.value, joint.value))
    }

    /**
     * If handtracking is enabled, returns the rotation of a joint (`joint`) of a hand (`hand`) as
     * provided by OpenXR.
     *
     * Generated from Godot docs: OpenXRInterface.get_hand_joint_rotation
     */
    fun getHandJointRotation(hand: OpenXRInterface.Hand, joint: OpenXRInterface.HandJoints): Quaternion {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetQuaternion(Binds.getHandJointRotationBind, segment, hand.value, joint.value)
    }

    /**
     * If handtracking is enabled, returns the position of a joint (`joint`) of a hand (`hand`) as
     * provided by OpenXR. This is relative to `XROrigin3D` without worldscale applied!
     *
     * Generated from Godot docs: OpenXRInterface.get_hand_joint_position
     */
    fun getHandJointPosition(hand: OpenXRInterface.Hand, joint: OpenXRInterface.HandJoints): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetVector3(Binds.getHandJointPositionBind, segment, hand.value, joint.value)
    }

    /**
     * If handtracking is enabled, returns the radius of a joint (`joint`) of a hand (`hand`) as
     * provided by OpenXR. This is without worldscale applied!
     *
     * Generated from Godot docs: OpenXRInterface.get_hand_joint_radius
     */
    fun getHandJointRadius(hand: OpenXRInterface.Hand, joint: OpenXRInterface.HandJoints): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetDouble(Binds.getHandJointRadiusBind, segment, hand.value, joint.value)
    }

    /**
     * If handtracking is enabled, returns the linear velocity of a joint (`joint`) of a hand (`hand`)
     * as provided by OpenXR. This is relative to `XROrigin3D` without worldscale applied!
     *
     * Generated from Godot docs: OpenXRInterface.get_hand_joint_linear_velocity
     */
    fun getHandJointLinearVelocity(hand: OpenXRInterface.Hand, joint: OpenXRInterface.HandJoints): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetVector3(Binds.getHandJointLinearVelocityBind, segment, hand.value, joint.value)
    }

    /**
     * If handtracking is enabled, returns the angular velocity of a joint (`joint`) of a hand (`hand`)
     * as provided by OpenXR. This is relative to `XROrigin3D`!
     *
     * Generated from Godot docs: OpenXRInterface.get_hand_joint_angular_velocity
     */
    fun getHandJointAngularVelocity(hand: OpenXRInterface.Hand, joint: OpenXRInterface.HandJoints): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetVector3(Binds.getHandJointAngularVelocityBind, segment, hand.value, joint.value)
    }

    /**
     * Returns `true` if OpenXR's hand tracking is supported and enabled. Note: This only returns a
     * valid value after OpenXR has been initialized.
     *
     * Generated from Godot docs: OpenXRInterface.is_hand_tracking_supported
     */
    fun isHandTrackingSupported(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isHandTrackingSupportedBind, segment)
    }

    /**
     * Returns `true` if OpenXR's hand interaction profile is supported and enabled. Note: This only
     * returns a valid value after OpenXR has been initialized.
     *
     * Generated from Godot docs: OpenXRInterface.is_hand_interaction_supported
     */
    fun isHandInteractionSupported(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isHandInteractionSupportedBind, segment)
    }

    /**
     * Returns the capabilities of the eye gaze interaction extension. Note: This only returns a valid
     * value after OpenXR has been initialized.
     *
     * Generated from Godot docs: OpenXRInterface.is_eye_gaze_interaction_supported
     */
    fun isEyeGazeInteractionSupported(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEyeGazeInteractionSupportedBind, segment)
    }

    /**
     * The minimum radius around the focal point where full quality is guaranteed if VRS is used as a
     * percentage of screen size. Note: Mobile and Forward+ renderers only. Requires
     * `Viewport.vrs_mode` to be set to `Viewport.VRSMode.XR`.
     *
     * Generated from Godot docs: OpenXRInterface.get_vrs_min_radius
     */
    fun getVrsMinRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVrsMinRadiusBind, segment)
    }

    /**
     * The minimum radius around the focal point where full quality is guaranteed if VRS is used as a
     * percentage of screen size. Note: Mobile and Forward+ renderers only. Requires
     * `Viewport.vrs_mode` to be set to `Viewport.VRSMode.XR`.
     *
     * Generated from Godot docs: OpenXRInterface.set_vrs_min_radius
     */
    fun setVrsMinRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVrsMinRadiusBind, segment, radius)
    }

    /**
     * The strength used to calculate the VRS density map. The greater this value, the more noticeable
     * VRS is. This improves performance at the cost of quality. Note: Mobile and Forward+ renderers
     * only. Requires `Viewport.vrs_mode` to be set to `Viewport.VRSMode.XR`.
     *
     * Generated from Godot docs: OpenXRInterface.get_vrs_strength
     */
    fun getVrsStrength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVrsStrengthBind, segment)
    }

    /**
     * The strength used to calculate the VRS density map. The greater this value, the more noticeable
     * VRS is. This improves performance at the cost of quality. Note: Mobile and Forward+ renderers
     * only. Requires `Viewport.vrs_mode` to be set to `Viewport.VRSMode.XR`.
     *
     * Generated from Godot docs: OpenXRInterface.set_vrs_strength
     */
    fun setVrsStrength(strength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVrsStrengthBind, segment, strength)
    }

    /**
     * Sets the CPU performance level of the OpenXR device.
     *
     * Generated from Godot docs: OpenXRInterface.set_cpu_level
     */
    fun setCpuLevel(level: OpenXRInterface.PerfSettingsLevel) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setCpuLevelBind, segment, level.value)
    }

    /**
     * Sets the GPU performance level of the OpenXR device.
     *
     * Generated from Godot docs: OpenXRInterface.set_gpu_level
     */
    fun setGpuLevel(level: OpenXRInterface.PerfSettingsLevel) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setGpuLevelBind, segment, level.value)
    }

    /** Signal `session_begun()`; see [TypedSignal]. */
    val sessionBegun: Signal0
        @JvmName("sessionBegunTypedSignal")
        get() = Signal0(this, "session_begun")

    /** Signal `session_stopping()`; see [TypedSignal]. */
    val sessionStopping: Signal0
        @JvmName("sessionStoppingTypedSignal")
        get() = Signal0(this, "session_stopping")

    /** Signal `session_synchronized()`; see [TypedSignal]. */
    val sessionSynchronized: Signal0
        @JvmName("sessionSynchronizedTypedSignal")
        get() = Signal0(this, "session_synchronized")

    /** Signal `session_focussed()`; see [TypedSignal]. */
    val sessionFocussed: Signal0
        @JvmName("sessionFocussedTypedSignal")
        get() = Signal0(this, "session_focussed")

    /** Signal `session_visible()`; see [TypedSignal]. */
    val sessionVisible: Signal0
        @JvmName("sessionVisibleTypedSignal")
        get() = Signal0(this, "session_visible")

    /** Signal `session_loss_pending()`; see [TypedSignal]. */
    val sessionLossPending: Signal0
        @JvmName("sessionLossPendingTypedSignal")
        get() = Signal0(this, "session_loss_pending")

    /** Signal `instance_exiting()`; see [TypedSignal]. */
    val instanceExiting: Signal0
        @JvmName("instanceExitingTypedSignal")
        get() = Signal0(this, "instance_exiting")

    /** Signal `pose_recentered()`; see [TypedSignal]. */
    val poseRecentered: Signal0
        @JvmName("poseRecenteredTypedSignal")
        get() = Signal0(this, "pose_recentered")

    /** Signal `refresh_rate_changed(refresh_rate: float)`; see [TypedSignal]. */
    val refreshRateChanged: Signal1<Double>
        @JvmName("refreshRateChangedTypedSignal")
        get() = Signal1(this, "refresh_rate_changed", SignalArgType.DOUBLE)

    /** Signal `cpu_level_changed(sub_domain: int, from_level: int, to_level: int)`; see [TypedSignal]. */
    val cpuLevelChanged: Signal3<Long, Long, Long>
        @JvmName("cpuLevelChangedTypedSignal")
        get() = Signal3(this, "cpu_level_changed", SignalArgType.LONG, SignalArgType.LONG, SignalArgType.LONG)

    /** Signal `gpu_level_changed(sub_domain: int, from_level: int, to_level: int)`; see [TypedSignal]. */
    val gpuLevelChanged: Signal3<Long, Long, Long>
        @JvmName("gpuLevelChangedTypedSignal")
        get() = Signal3(this, "gpu_level_changed", SignalArgType.LONG, SignalArgType.LONG, SignalArgType.LONG)

    /** Signal `user_presence_changed(is_user_present: bool)`; see [TypedSignal]. */
    val userPresenceChanged: Signal1<Boolean>
        @JvmName("userPresenceChangedTypedSignal")
        get() = Signal1(this, "user_presence_changed", SignalArgType.BOOLEAN)

    object Signals {
        const val sessionBegun: String = "session_begun"
        const val sessionStopping: String = "session_stopping"
        const val sessionSynchronized: String = "session_synchronized"
        const val sessionFocussed: String = "session_focussed"
        const val sessionVisible: String = "session_visible"
        const val sessionLossPending: String = "session_loss_pending"
        const val instanceExiting: String = "instance_exiting"
        const val poseRecentered: String = "pose_recentered"
        const val refreshRateChanged: String = "refresh_rate_changed"
        const val cpuLevelChanged: String = "cpu_level_changed"
        const val gpuLevelChanged: String = "gpu_level_changed"
        const val userPresenceChanged: String = "user_presence_changed"
    }

    /**
     * Godot's `OpenXRInterface.SessionState` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`OpenXRInterface.SessionState.<NAME>`).
     *
     * Generated from Godot docs: OpenXRInterface.SessionState
     */
    @JvmInline
    value class SessionState(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The state of the session is unknown, we haven't tried setting up OpenXR yet.
             *
             * Generated from Godot docs: OpenXRInterface.SESSION_STATE_UNKNOWN
             */
            val UNKNOWN: SessionState get() = SessionState(0L)
            /**
             * The initial state after the OpenXR session is created or after the session is destroyed.
             *
             * Generated from Godot docs: OpenXRInterface.SESSION_STATE_IDLE
             */
            val IDLE: SessionState get() = SessionState(1L)
            /**
             * OpenXR is ready to begin our session. `session_begun` is emitted when we change to this state.
             *
             * Generated from Godot docs: OpenXRInterface.SESSION_STATE_READY
             */
            val READY: SessionState get() = SessionState(2L)
            /**
             * The application has synched its frame loop with the runtime but we're not rendering anything.
             * `session_synchronized` is emitted when we change to this state.
             *
             * Generated from Godot docs: OpenXRInterface.SESSION_STATE_SYNCHRONIZED
             */
            val SYNCHRONIZED: SessionState get() = SessionState(3L)
            /**
             * The application has synched its frame loop with the runtime and we're rendering output to the
             * user, however we receive no user input. `session_visible` is emitted when we change to this
             * state. Note: This is the current state just before we get the focused state, whenever the user
             * opens a system menu, switches to another application, or takes off their headset.
             *
             * Generated from Godot docs: OpenXRInterface.SESSION_STATE_VISIBLE
             */
            val VISIBLE: SessionState get() = SessionState(4L)
            /**
             * The application has synched its frame loop with the runtime, we're rendering output to the user
             * and we're receiving XR input. `session_focussed` is emitted when we change to this state. Note:
             * This is the state OpenXR will be in when the user can fully interact with your game.
             *
             * Generated from Godot docs: OpenXRInterface.SESSION_STATE_FOCUSED
             */
            val FOCUSED: SessionState get() = SessionState(5L)
            /**
             * Our session is being stopped. `session_stopping` is emitted when we change to this state.
             *
             * Generated from Godot docs: OpenXRInterface.SESSION_STATE_STOPPING
             */
            val STOPPING: SessionState get() = SessionState(6L)
            /**
             * The session is about to be lost. `session_loss_pending` is emitted when we change to this state.
             *
             * Generated from Godot docs: OpenXRInterface.SESSION_STATE_LOSS_PENDING
             */
            val LOSS_PENDING: SessionState get() = SessionState(7L)
            /**
             * The OpenXR instance is about to be destroyed and we're exiting. `instance_exiting` is emitted
             * when we change to this state.
             *
             * Generated from Godot docs: OpenXRInterface.SESSION_STATE_EXITING
             */
            val EXITING: SessionState get() = SessionState(8L)
        }
    }

    /**
     * Godot's `OpenXRInterface.Hand` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`OpenXRInterface.Hand.<NAME>`).
     *
     * Generated from Godot docs: OpenXRInterface.Hand
     */
    @JvmInline
    value class Hand(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Left hand.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_LEFT
             */
            val LEFT: Hand get() = Hand(0L)
            /**
             * Right hand.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_RIGHT
             */
            val RIGHT: Hand get() = Hand(1L)
            /**
             * Maximum value for the hand enum.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_MAX
             */
            val MAX: Hand get() = Hand(2L)
        }
    }

    /**
     * Godot's `OpenXRInterface.HandMotionRange` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`OpenXRInterface.HandMotionRange.<NAME>`).
     *
     * Generated from Godot docs: OpenXRInterface.HandMotionRange
     */
    @JvmInline
    value class HandMotionRange(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Full hand range, if user closes their hands, we make a full fist.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_MOTION_RANGE_UNOBSTRUCTED
             */
            val UNOBSTRUCTED: HandMotionRange get() = HandMotionRange(0L)
            /**
             * Conform to controller, if user closes their hands, the tracked data conforms to the shape of the
             * controller.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_MOTION_RANGE_CONFORM_TO_CONTROLLER
             */
            val CONFORM_TO_CONTROLLER: HandMotionRange get() = HandMotionRange(1L)
            /**
             * Maximum value for the motion range enum.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_MOTION_RANGE_MAX
             */
            val MAX: HandMotionRange get() = HandMotionRange(2L)
        }
    }

    /**
     * Godot's `OpenXRInterface.HandTrackedSource` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`OpenXRInterface.HandTrackedSource.<NAME>`).
     *
     * Generated from Godot docs: OpenXRInterface.HandTrackedSource
     */
    @JvmInline
    value class HandTrackedSource(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The source of hand tracking data is unknown (the extension is likely unsupported).
             *
             * Generated from Godot docs: OpenXRInterface.HAND_TRACKED_SOURCE_UNKNOWN
             */
            val UNKNOWN: HandTrackedSource get() = HandTrackedSource(0L)
            /**
             * The source of hand tracking is unobstructed, this means that an accurate method of hand tracking
             * is used, e.g. optical hand tracking, data gloves, etc.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_TRACKED_SOURCE_UNOBSTRUCTED
             */
            val UNOBSTRUCTED: HandTrackedSource get() = HandTrackedSource(1L)
            /**
             * The source of hand tracking is a controller, bone positions are inferred from controller inputs.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_TRACKED_SOURCE_CONTROLLER
             */
            val CONTROLLER: HandTrackedSource get() = HandTrackedSource(2L)
            /**
             * Represents the size of the `HandTrackedSource` enum.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_TRACKED_SOURCE_MAX
             */
            val MAX: HandTrackedSource get() = HandTrackedSource(3L)
        }
    }

    /**
     * Godot's `OpenXRInterface.HandJoints` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`OpenXRInterface.HandJoints.<NAME>`).
     *
     * Generated from Godot docs: OpenXRInterface.HandJoints
     */
    @JvmInline
    value class HandJoints(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Palm joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_PALM
             */
            val PALM: HandJoints get() = HandJoints(0L)
            /**
             * Wrist joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_WRIST
             */
            val WRIST: HandJoints get() = HandJoints(1L)
            /**
             * Thumb metacarpal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_THUMB_METACARPAL
             */
            val THUMB_METACARPAL: HandJoints get() = HandJoints(2L)
            /**
             * Thumb proximal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_THUMB_PROXIMAL
             */
            val THUMB_PROXIMAL: HandJoints get() = HandJoints(3L)
            /**
             * Thumb distal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_THUMB_DISTAL
             */
            val THUMB_DISTAL: HandJoints get() = HandJoints(4L)
            /**
             * Thumb tip joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_THUMB_TIP
             */
            val THUMB_TIP: HandJoints get() = HandJoints(5L)
            /**
             * Index finger metacarpal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_INDEX_METACARPAL
             */
            val INDEX_METACARPAL: HandJoints get() = HandJoints(6L)
            /**
             * Index finger phalanx proximal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_INDEX_PROXIMAL
             */
            val INDEX_PROXIMAL: HandJoints get() = HandJoints(7L)
            /**
             * Index finger phalanx intermediate joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_INDEX_INTERMEDIATE
             */
            val INDEX_INTERMEDIATE: HandJoints get() = HandJoints(8L)
            /**
             * Index finger phalanx distal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_INDEX_DISTAL
             */
            val INDEX_DISTAL: HandJoints get() = HandJoints(9L)
            /**
             * Index finger tip joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_INDEX_TIP
             */
            val INDEX_TIP: HandJoints get() = HandJoints(10L)
            /**
             * Middle finger metacarpal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_MIDDLE_METACARPAL
             */
            val MIDDLE_METACARPAL: HandJoints get() = HandJoints(11L)
            /**
             * Middle finger phalanx proximal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_MIDDLE_PROXIMAL
             */
            val MIDDLE_PROXIMAL: HandJoints get() = HandJoints(12L)
            /**
             * Middle finger phalanx intermediate joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_MIDDLE_INTERMEDIATE
             */
            val MIDDLE_INTERMEDIATE: HandJoints get() = HandJoints(13L)
            /**
             * Middle finger phalanx distal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_MIDDLE_DISTAL
             */
            val MIDDLE_DISTAL: HandJoints get() = HandJoints(14L)
            /**
             * Middle finger tip joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_MIDDLE_TIP
             */
            val MIDDLE_TIP: HandJoints get() = HandJoints(15L)
            /**
             * Ring finger metacarpal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_RING_METACARPAL
             */
            val RING_METACARPAL: HandJoints get() = HandJoints(16L)
            /**
             * Ring finger phalanx proximal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_RING_PROXIMAL
             */
            val RING_PROXIMAL: HandJoints get() = HandJoints(17L)
            /**
             * Ring finger phalanx intermediate joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_RING_INTERMEDIATE
             */
            val RING_INTERMEDIATE: HandJoints get() = HandJoints(18L)
            /**
             * Ring finger phalanx distal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_RING_DISTAL
             */
            val RING_DISTAL: HandJoints get() = HandJoints(19L)
            /**
             * Ring finger tip joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_RING_TIP
             */
            val RING_TIP: HandJoints get() = HandJoints(20L)
            /**
             * Pinky finger metacarpal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_LITTLE_METACARPAL
             */
            val LITTLE_METACARPAL: HandJoints get() = HandJoints(21L)
            /**
             * Pinky finger phalanx proximal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_LITTLE_PROXIMAL
             */
            val LITTLE_PROXIMAL: HandJoints get() = HandJoints(22L)
            /**
             * Pinky finger phalanx intermediate joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_LITTLE_INTERMEDIATE
             */
            val LITTLE_INTERMEDIATE: HandJoints get() = HandJoints(23L)
            /**
             * Pinky finger phalanx distal joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_LITTLE_DISTAL
             */
            val LITTLE_DISTAL: HandJoints get() = HandJoints(24L)
            /**
             * Pinky finger tip joint.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_LITTLE_TIP
             */
            val LITTLE_TIP: HandJoints get() = HandJoints(25L)
            /**
             * Represents the size of the `HandJoints` enum.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_MAX
             */
            val MAX: HandJoints get() = HandJoints(26L)
        }
    }

    /**
     * Godot's `OpenXRInterface.PerfSettingsLevel` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`OpenXRInterface.PerfSettingsLevel.<NAME>`).
     *
     * Generated from Godot docs: OpenXRInterface.PerfSettingsLevel
     */
    @JvmInline
    value class PerfSettingsLevel(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The application has entered a non-XR section (head-locked / static screen), during which power
             * savings are to be prioritized.
             *
             * Generated from Godot docs: OpenXRInterface.PERF_SETTINGS_LEVEL_POWER_SAVINGS
             */
            val POWER_SAVINGS: PerfSettingsLevel get() = PerfSettingsLevel(0L)
            /**
             * The application has entered a low and stable complexity section, during which reducing power is
             * more important than occasional late rendering frames.
             *
             * Generated from Godot docs: OpenXRInterface.PERF_SETTINGS_LEVEL_SUSTAINED_LOW
             */
            val SUSTAINED_LOW: PerfSettingsLevel get() = PerfSettingsLevel(1L)
            /**
             * The application has entered a high or dynamic complexity section, during which the XR Runtime
             * strives for consistent XR compositing and frame rendering within a thermally sustainable range.
             *
             * Generated from Godot docs: OpenXRInterface.PERF_SETTINGS_LEVEL_SUSTAINED_HIGH
             */
            val SUSTAINED_HIGH: PerfSettingsLevel get() = PerfSettingsLevel(2L)
            /**
             * The application has entered a section with very high complexity, during which the XR Runtime is
             * allowed to step up beyond the thermally sustainable range.
             *
             * Generated from Godot docs: OpenXRInterface.PERF_SETTINGS_LEVEL_BOOST
             */
            val BOOST: PerfSettingsLevel get() = PerfSettingsLevel(3L)
        }
    }

    /**
     * Godot's `OpenXRInterface.PerfSettingsSubDomain` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`OpenXRInterface.PerfSettingsSubDomain.<NAME>`).
     *
     * Generated from Godot docs: OpenXRInterface.PerfSettingsSubDomain
     */
    @JvmInline
    value class PerfSettingsSubDomain(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The compositing performance within the runtime has reached a new level.
             *
             * Generated from Godot docs: OpenXRInterface.PERF_SETTINGS_SUB_DOMAIN_COMPOSITING
             */
            val COMPOSITING: PerfSettingsSubDomain get() = PerfSettingsSubDomain(0L)
            /**
             * The application rendering performance has reached a new level.
             *
             * Generated from Godot docs: OpenXRInterface.PERF_SETTINGS_SUB_DOMAIN_RENDERING
             */
            val RENDERING: PerfSettingsSubDomain get() = PerfSettingsSubDomain(1L)
            /**
             * The temperature of the device has reached a new level.
             *
             * Generated from Godot docs: OpenXRInterface.PERF_SETTINGS_SUB_DOMAIN_THERMAL
             */
            val THERMAL: PerfSettingsSubDomain get() = PerfSettingsSubDomain(2L)
        }
    }

    /**
     * Godot's `OpenXRInterface.PerfSettingsNotificationLevel` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`OpenXRInterface.PerfSettingsNotificationLevel.<NAME>`).
     *
     * Generated from Godot docs: OpenXRInterface.PerfSettingsNotificationLevel
     */
    @JvmInline
    value class PerfSettingsNotificationLevel(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The sub-domain has reached a level where no further actions other than currently applied are
             * necessary.
             *
             * Generated from Godot docs: OpenXRInterface.PERF_SETTINGS_NOTIF_LEVEL_NORMAL
             */
            val NORMAL: PerfSettingsNotificationLevel get() = PerfSettingsNotificationLevel(0L)
            /**
             * The sub-domain has reached an early warning level where the application should start proactive
             * mitigation actions.
             *
             * Generated from Godot docs: OpenXRInterface.PERF_SETTINGS_NOTIF_LEVEL_WARNING
             */
            val WARNING: PerfSettingsNotificationLevel get() = PerfSettingsNotificationLevel(1L)
            /**
             * The sub-domain has reached a critical level where the application should start drastic
             * mitigation actions.
             *
             * Generated from Godot docs: OpenXRInterface.PERF_SETTINGS_NOTIF_LEVEL_IMPAIRED
             */
            val IMPAIRED: PerfSettingsNotificationLevel get() = PerfSettingsNotificationLevel(2L)
        }
    }

    /**
     * Godot's `OpenXRInterface.HandJointFlags` bitfield as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`OpenXRInterface.HandJointFlags.<NAME>`).
     *
     * Generated from Godot docs: OpenXRInterface.HandJointFlags
     */
    @JvmInline
    value class HandJointFlags(override val value: Long) : GodotEnumValue {
        infix fun or(other: HandJointFlags): HandJointFlags = HandJointFlags(value or other.value)

        infix fun and(other: HandJointFlags): HandJointFlags = HandJointFlags(value and other.value)

        infix fun xor(other: HandJointFlags): HandJointFlags = HandJointFlags(value xor other.value)

        fun inv(): HandJointFlags = HandJointFlags(value.inv())

        operator fun contains(other: HandJointFlags): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * No flags are set.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_NONE
             */
            val NONE: HandJointFlags get() = HandJointFlags(0L)
            /**
             * If set, the orientation data is valid, otherwise, the orientation data is unreliable and should
             * not be used.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_ORIENTATION_VALID
             */
            val ORIENTATION_VALID: HandJointFlags get() = HandJointFlags(1L)
            /**
             * If set, the orientation data comes from tracking data, otherwise, the orientation data contains
             * predicted data.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_ORIENTATION_TRACKED
             */
            val ORIENTATION_TRACKED: HandJointFlags get() = HandJointFlags(2L)
            /**
             * If set, the positional data is valid, otherwise, the positional data is unreliable and should
             * not be used.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_POSITION_VALID
             */
            val POSITION_VALID: HandJointFlags get() = HandJointFlags(4L)
            /**
             * If set, the positional data comes from tracking data, otherwise, the positional data contains
             * predicted data.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_POSITION_TRACKED
             */
            val POSITION_TRACKED: HandJointFlags get() = HandJointFlags(8L)
            /**
             * If set, our linear velocity data is valid, otherwise, the linear velocity data is unreliable and
             * should not be used.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_LINEAR_VELOCITY_VALID
             */
            val LINEAR_VELOCITY_VALID: HandJointFlags get() = HandJointFlags(16L)
            /**
             * If set, our angular velocity data is valid, otherwise, the angular velocity data is unreliable
             * and should not be used.
             *
             * Generated from Godot docs: OpenXRInterface.HAND_JOINT_ANGULAR_VELOCITY_VALID
             */
            val ANGULAR_VELOCITY_VALID: HandJointFlags get() = HandJointFlags(32L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRInterface? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRInterface? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRInterface(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRInterface? =
            if (handle.address() == 0L) null else OpenXRInterface(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_SESSION_STATE_HASH = 896364779L
        @JvmField
        val getSessionStateBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_session_state", GET_SESSION_STATE_HASH)

        private const val IS_USER_PRESENCE_SUPPORTED_HASH = 36873697L
        @JvmField
        val isUserPresenceSupportedBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "is_user_presence_supported", IS_USER_PRESENCE_SUPPORTED_HASH)

        private const val IS_USER_PRESENT_HASH = 36873697L
        @JvmField
        val isUserPresentBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "is_user_present", IS_USER_PRESENT_HASH)

        private const val GET_DISPLAY_REFRESH_RATE_HASH = 1740695150L
        @JvmField
        val getDisplayRefreshRateBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_display_refresh_rate", GET_DISPLAY_REFRESH_RATE_HASH)

        private const val SET_DISPLAY_REFRESH_RATE_HASH = 373806689L
        @JvmField
        val setDisplayRefreshRateBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "set_display_refresh_rate", SET_DISPLAY_REFRESH_RATE_HASH)

        private const val GET_RENDER_TARGET_SIZE_MULTIPLIER_HASH = 1740695150L
        @JvmField
        val getRenderTargetSizeMultiplierBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_render_target_size_multiplier", GET_RENDER_TARGET_SIZE_MULTIPLIER_HASH)

        private const val SET_RENDER_TARGET_SIZE_MULTIPLIER_HASH = 373806689L
        @JvmField
        val setRenderTargetSizeMultiplierBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "set_render_target_size_multiplier", SET_RENDER_TARGET_SIZE_MULTIPLIER_HASH)

        private const val IS_FOVEATION_SUPPORTED_HASH = 36873697L
        @JvmField
        val isFoveationSupportedBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "is_foveation_supported", IS_FOVEATION_SUPPORTED_HASH)

        private const val GET_FOVEATION_LEVEL_HASH = 3905245786L
        @JvmField
        val getFoveationLevelBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_foveation_level", GET_FOVEATION_LEVEL_HASH)

        private const val SET_FOVEATION_LEVEL_HASH = 1286410249L
        @JvmField
        val setFoveationLevelBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "set_foveation_level", SET_FOVEATION_LEVEL_HASH)

        private const val GET_FOVEATION_DYNAMIC_HASH = 36873697L
        @JvmField
        val getFoveationDynamicBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_foveation_dynamic", GET_FOVEATION_DYNAMIC_HASH)

        private const val SET_FOVEATION_DYNAMIC_HASH = 2586408642L
        @JvmField
        val setFoveationDynamicBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "set_foveation_dynamic", SET_FOVEATION_DYNAMIC_HASH)

        private const val GET_FOVEATION_WITH_SUBSAMPLED_IMAGES_HASH = 36873697L
        @JvmField
        val getFoveationWithSubsampledImagesBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_foveation_with_subsampled_images", GET_FOVEATION_WITH_SUBSAMPLED_IMAGES_HASH)

        private const val SET_FOVEATION_WITH_SUBSAMPLED_IMAGES_HASH = 2586408642L
        @JvmField
        val setFoveationWithSubsampledImagesBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "set_foveation_with_subsampled_images", SET_FOVEATION_WITH_SUBSAMPLED_IMAGES_HASH)

        private const val IS_ACTION_SET_ACTIVE_HASH = 3927539163L
        @JvmField
        val isActionSetActiveBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "is_action_set_active", IS_ACTION_SET_ACTIVE_HASH)

        private const val SET_ACTION_SET_ACTIVE_HASH = 2678287736L
        @JvmField
        val setActionSetActiveBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "set_action_set_active", SET_ACTION_SET_ACTIVE_HASH)

        private const val GET_ACTION_SETS_HASH = 3995934104L
        @JvmField
        val getActionSetsBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_action_sets", GET_ACTION_SETS_HASH)

        private const val GET_AVAILABLE_DISPLAY_REFRESH_RATES_HASH = 3995934104L
        @JvmField
        val getAvailableDisplayRefreshRatesBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_available_display_refresh_rates", GET_AVAILABLE_DISPLAY_REFRESH_RATES_HASH)

        private const val SET_MOTION_RANGE_HASH = 855158159L
        @JvmField
        val setMotionRangeBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "set_motion_range", SET_MOTION_RANGE_HASH)

        private const val GET_MOTION_RANGE_HASH = 3955838114L
        @JvmField
        val getMotionRangeBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_motion_range", GET_MOTION_RANGE_HASH)

        private const val GET_HAND_TRACKING_SOURCE_HASH = 4092421202L
        @JvmField
        val getHandTrackingSourceBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_tracking_source", GET_HAND_TRACKING_SOURCE_HASH)

        private const val GET_HAND_JOINT_FLAGS_HASH = 720567706L
        @JvmField
        val getHandJointFlagsBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_joint_flags", GET_HAND_JOINT_FLAGS_HASH)

        private const val GET_HAND_JOINT_ROTATION_HASH = 1974618321L
        @JvmField
        val getHandJointRotationBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_joint_rotation", GET_HAND_JOINT_ROTATION_HASH)

        private const val GET_HAND_JOINT_POSITION_HASH = 3529194242L
        @JvmField
        val getHandJointPositionBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_joint_position", GET_HAND_JOINT_POSITION_HASH)

        private const val GET_HAND_JOINT_RADIUS_HASH = 901522724L
        @JvmField
        val getHandJointRadiusBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_joint_radius", GET_HAND_JOINT_RADIUS_HASH)

        private const val GET_HAND_JOINT_LINEAR_VELOCITY_HASH = 3529194242L
        @JvmField
        val getHandJointLinearVelocityBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_joint_linear_velocity", GET_HAND_JOINT_LINEAR_VELOCITY_HASH)

        private const val GET_HAND_JOINT_ANGULAR_VELOCITY_HASH = 3529194242L
        @JvmField
        val getHandJointAngularVelocityBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_joint_angular_velocity", GET_HAND_JOINT_ANGULAR_VELOCITY_HASH)

        private const val IS_HAND_TRACKING_SUPPORTED_HASH = 2240911060L
        @JvmField
        val isHandTrackingSupportedBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "is_hand_tracking_supported", IS_HAND_TRACKING_SUPPORTED_HASH)

        private const val IS_HAND_INTERACTION_SUPPORTED_HASH = 36873697L
        @JvmField
        val isHandInteractionSupportedBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "is_hand_interaction_supported", IS_HAND_INTERACTION_SUPPORTED_HASH)

        private const val IS_EYE_GAZE_INTERACTION_SUPPORTED_HASH = 2240911060L
        @JvmField
        val isEyeGazeInteractionSupportedBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "is_eye_gaze_interaction_supported", IS_EYE_GAZE_INTERACTION_SUPPORTED_HASH)

        private const val GET_VRS_MIN_RADIUS_HASH = 1740695150L
        @JvmField
        val getVrsMinRadiusBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_vrs_min_radius", GET_VRS_MIN_RADIUS_HASH)

        private const val SET_VRS_MIN_RADIUS_HASH = 373806689L
        @JvmField
        val setVrsMinRadiusBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "set_vrs_min_radius", SET_VRS_MIN_RADIUS_HASH)

        private const val GET_VRS_STRENGTH_HASH = 1740695150L
        @JvmField
        val getVrsStrengthBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "get_vrs_strength", GET_VRS_STRENGTH_HASH)

        private const val SET_VRS_STRENGTH_HASH = 373806689L
        @JvmField
        val setVrsStrengthBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "set_vrs_strength", SET_VRS_STRENGTH_HASH)

        private const val SET_CPU_LEVEL_HASH = 2940842095L
        @JvmField
        val setCpuLevelBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "set_cpu_level", SET_CPU_LEVEL_HASH)

        private const val SET_GPU_LEVEL_HASH = 2940842095L
        @JvmField
        val setGpuLevelBind =
            ObjectCalls.getMethodBind("OpenXRInterface", "set_gpu_level", SET_GPU_LEVEL_HASH)
    }
}
