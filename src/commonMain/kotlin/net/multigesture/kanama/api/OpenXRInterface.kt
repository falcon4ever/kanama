package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Vector3

/**
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

    fun getSessionState(): OpenXRInterface.SessionState {
        checkOpen()
        return OpenXRInterface.SessionState(ObjectCalls.ptrcallNoArgsRetLong(getSessionStateBind, segment))
    }

    fun isUserPresenceSupported(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isUserPresenceSupportedBind, segment)
    }

    fun isUserPresent(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isUserPresentBind, segment)
    }

    fun getDisplayRefreshRate(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDisplayRefreshRateBind, segment)
    }

    fun setDisplayRefreshRate(refreshRate: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDisplayRefreshRateBind, segment, refreshRate)
    }

    fun getRenderTargetSizeMultiplier(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getRenderTargetSizeMultiplierBind, segment)
    }

    fun setRenderTargetSizeMultiplier(multiplier: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setRenderTargetSizeMultiplierBind, segment, multiplier)
    }

    fun isFoveationSupported(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isFoveationSupportedBind, segment)
    }

    fun getFoveationLevel(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getFoveationLevelBind, segment)
    }

    fun setFoveationLevel(foveationLevel: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setFoveationLevelBind, segment, foveationLevel)
    }

    fun getFoveationDynamic(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getFoveationDynamicBind, segment)
    }

    fun setFoveationDynamic(foveationDynamic: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setFoveationDynamicBind, segment, foveationDynamic)
    }

    fun getFoveationWithSubsampledImages(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getFoveationWithSubsampledImagesBind, segment)
    }

    fun setFoveationWithSubsampledImages(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setFoveationWithSubsampledImagesBind, segment, enabled)
    }

    fun isActionSetActive(name: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(isActionSetActiveBind, segment, name)
    }

    fun setActionSetActive(name: String, active: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndBoolArg(setActionSetActiveBind, segment, name, active)
    }

    fun getActionSets(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(getActionSetsBind, segment)
    }

    fun getAvailableDisplayRefreshRates(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(getAvailableDisplayRefreshRatesBind, segment)
    }

    fun setMotionRange(hand: OpenXRInterface.Hand, motionRange: OpenXRInterface.HandMotionRange) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoLongArgs(setMotionRangeBind, segment, hand.value, motionRange.value)
    }

    fun getMotionRange(hand: OpenXRInterface.Hand): OpenXRInterface.HandMotionRange {
        checkOpen()
        return OpenXRInterface.HandMotionRange(ObjectCalls.ptrcallWithLongArgRetLong(getMotionRangeBind, segment, hand.value))
    }

    fun getHandTrackingSource(hand: OpenXRInterface.Hand): OpenXRInterface.HandTrackedSource {
        checkOpen()
        return OpenXRInterface.HandTrackedSource(ObjectCalls.ptrcallWithLongArgRetLong(getHandTrackingSourceBind, segment, hand.value))
    }

    fun getHandJointFlags(hand: OpenXRInterface.Hand, joint: OpenXRInterface.HandJoints): OpenXRInterface.HandJointFlags {
        checkOpen()
        return OpenXRInterface.HandJointFlags(ObjectCalls.ptrcallWithTwoLongArgsRetLong(getHandJointFlagsBind, segment, hand.value, joint.value))
    }

    fun getHandJointRotation(hand: OpenXRInterface.Hand, joint: OpenXRInterface.HandJoints): Quaternion {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetQuaternion(getHandJointRotationBind, segment, hand.value, joint.value)
    }

    fun getHandJointPosition(hand: OpenXRInterface.Hand, joint: OpenXRInterface.HandJoints): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetVector3(getHandJointPositionBind, segment, hand.value, joint.value)
    }

    fun getHandJointRadius(hand: OpenXRInterface.Hand, joint: OpenXRInterface.HandJoints): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetDouble(getHandJointRadiusBind, segment, hand.value, joint.value)
    }

    fun getHandJointLinearVelocity(hand: OpenXRInterface.Hand, joint: OpenXRInterface.HandJoints): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetVector3(getHandJointLinearVelocityBind, segment, hand.value, joint.value)
    }

    fun getHandJointAngularVelocity(hand: OpenXRInterface.Hand, joint: OpenXRInterface.HandJoints): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetVector3(getHandJointAngularVelocityBind, segment, hand.value, joint.value)
    }

    fun isHandTrackingSupported(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isHandTrackingSupportedBind, segment)
    }

    fun isHandInteractionSupported(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isHandInteractionSupportedBind, segment)
    }

    fun isEyeGazeInteractionSupported(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isEyeGazeInteractionSupportedBind, segment)
    }

    fun getVrsMinRadius(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getVrsMinRadiusBind, segment)
    }

    fun setVrsMinRadius(radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setVrsMinRadiusBind, segment, radius)
    }

    fun getVrsStrength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getVrsStrengthBind, segment)
    }

    fun setVrsStrength(strength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setVrsStrengthBind, segment, strength)
    }

    fun setCpuLevel(level: OpenXRInterface.PerfSettingsLevel) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setCpuLevelBind, segment, level.value)
    }

    fun setGpuLevel(level: OpenXRInterface.PerfSettingsLevel) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setGpuLevelBind, segment, level.value)
    }

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

    @JvmInline
    value class SessionState(override val value: Long) : GodotEnumValue {
        companion object {
            val UNKNOWN: SessionState get() = SessionState(0L)
            val IDLE: SessionState get() = SessionState(1L)
            val READY: SessionState get() = SessionState(2L)
            val SYNCHRONIZED: SessionState get() = SessionState(3L)
            val VISIBLE: SessionState get() = SessionState(4L)
            val FOCUSED: SessionState get() = SessionState(5L)
            val STOPPING: SessionState get() = SessionState(6L)
            val LOSS_PENDING: SessionState get() = SessionState(7L)
            val EXITING: SessionState get() = SessionState(8L)
        }
    }

    @JvmInline
    value class Hand(override val value: Long) : GodotEnumValue {
        companion object {
            val LEFT: Hand get() = Hand(0L)
            val RIGHT: Hand get() = Hand(1L)
            val MAX: Hand get() = Hand(2L)
        }
    }

    @JvmInline
    value class HandMotionRange(override val value: Long) : GodotEnumValue {
        companion object {
            val UNOBSTRUCTED: HandMotionRange get() = HandMotionRange(0L)
            val CONFORM_TO_CONTROLLER: HandMotionRange get() = HandMotionRange(1L)
            val MAX: HandMotionRange get() = HandMotionRange(2L)
        }
    }

    @JvmInline
    value class HandTrackedSource(override val value: Long) : GodotEnumValue {
        companion object {
            val UNKNOWN: HandTrackedSource get() = HandTrackedSource(0L)
            val UNOBSTRUCTED: HandTrackedSource get() = HandTrackedSource(1L)
            val CONTROLLER: HandTrackedSource get() = HandTrackedSource(2L)
            val MAX: HandTrackedSource get() = HandTrackedSource(3L)
        }
    }

    @JvmInline
    value class HandJoints(override val value: Long) : GodotEnumValue {
        companion object {
            val PALM: HandJoints get() = HandJoints(0L)
            val WRIST: HandJoints get() = HandJoints(1L)
            val THUMB_METACARPAL: HandJoints get() = HandJoints(2L)
            val THUMB_PROXIMAL: HandJoints get() = HandJoints(3L)
            val THUMB_DISTAL: HandJoints get() = HandJoints(4L)
            val THUMB_TIP: HandJoints get() = HandJoints(5L)
            val INDEX_METACARPAL: HandJoints get() = HandJoints(6L)
            val INDEX_PROXIMAL: HandJoints get() = HandJoints(7L)
            val INDEX_INTERMEDIATE: HandJoints get() = HandJoints(8L)
            val INDEX_DISTAL: HandJoints get() = HandJoints(9L)
            val INDEX_TIP: HandJoints get() = HandJoints(10L)
            val MIDDLE_METACARPAL: HandJoints get() = HandJoints(11L)
            val MIDDLE_PROXIMAL: HandJoints get() = HandJoints(12L)
            val MIDDLE_INTERMEDIATE: HandJoints get() = HandJoints(13L)
            val MIDDLE_DISTAL: HandJoints get() = HandJoints(14L)
            val MIDDLE_TIP: HandJoints get() = HandJoints(15L)
            val RING_METACARPAL: HandJoints get() = HandJoints(16L)
            val RING_PROXIMAL: HandJoints get() = HandJoints(17L)
            val RING_INTERMEDIATE: HandJoints get() = HandJoints(18L)
            val RING_DISTAL: HandJoints get() = HandJoints(19L)
            val RING_TIP: HandJoints get() = HandJoints(20L)
            val LITTLE_METACARPAL: HandJoints get() = HandJoints(21L)
            val LITTLE_PROXIMAL: HandJoints get() = HandJoints(22L)
            val LITTLE_INTERMEDIATE: HandJoints get() = HandJoints(23L)
            val LITTLE_DISTAL: HandJoints get() = HandJoints(24L)
            val LITTLE_TIP: HandJoints get() = HandJoints(25L)
            val MAX: HandJoints get() = HandJoints(26L)
        }
    }

    @JvmInline
    value class PerfSettingsLevel(override val value: Long) : GodotEnumValue {
        companion object {
            val POWER_SAVINGS: PerfSettingsLevel get() = PerfSettingsLevel(0L)
            val SUSTAINED_LOW: PerfSettingsLevel get() = PerfSettingsLevel(1L)
            val SUSTAINED_HIGH: PerfSettingsLevel get() = PerfSettingsLevel(2L)
            val BOOST: PerfSettingsLevel get() = PerfSettingsLevel(3L)
        }
    }

    @JvmInline
    value class PerfSettingsSubDomain(override val value: Long) : GodotEnumValue {
        companion object {
            val COMPOSITING: PerfSettingsSubDomain get() = PerfSettingsSubDomain(0L)
            val RENDERING: PerfSettingsSubDomain get() = PerfSettingsSubDomain(1L)
            val THERMAL: PerfSettingsSubDomain get() = PerfSettingsSubDomain(2L)
        }
    }

    @JvmInline
    value class PerfSettingsNotificationLevel(override val value: Long) : GodotEnumValue {
        companion object {
            val NORMAL: PerfSettingsNotificationLevel get() = PerfSettingsNotificationLevel(0L)
            val WARNING: PerfSettingsNotificationLevel get() = PerfSettingsNotificationLevel(1L)
            val IMPAIRED: PerfSettingsNotificationLevel get() = PerfSettingsNotificationLevel(2L)
        }
    }

    @JvmInline
    value class HandJointFlags(override val value: Long) : GodotEnumValue {
        infix fun or(other: HandJointFlags): HandJointFlags = HandJointFlags(value or other.value)

        infix fun and(other: HandJointFlags): HandJointFlags = HandJointFlags(value and other.value)

        infix fun xor(other: HandJointFlags): HandJointFlags = HandJointFlags(value xor other.value)

        fun inv(): HandJointFlags = HandJointFlags(value.inv())

        operator fun contains(other: HandJointFlags): Boolean = (value and other.value) == other.value

        companion object {
            val NONE: HandJointFlags get() = HandJointFlags(0L)
            val ORIENTATION_VALID: HandJointFlags get() = HandJointFlags(1L)
            val ORIENTATION_TRACKED: HandJointFlags get() = HandJointFlags(2L)
            val POSITION_VALID: HandJointFlags get() = HandJointFlags(4L)
            val POSITION_TRACKED: HandJointFlags get() = HandJointFlags(8L)
            val LINEAR_VELOCITY_VALID: HandJointFlags get() = HandJointFlags(16L)
            val ANGULAR_VELOCITY_VALID: HandJointFlags get() = HandJointFlags(32L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRInterface? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRInterface? =
            if (handle.address() == 0L) null else OpenXRInterface(GodotHandle(handle))

        private const val GET_SESSION_STATE_HASH = 896364779L
        private val getSessionStateBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_session_state", GET_SESSION_STATE_HASH)
        }

        private const val IS_USER_PRESENCE_SUPPORTED_HASH = 36873697L
        private val isUserPresenceSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "is_user_presence_supported", IS_USER_PRESENCE_SUPPORTED_HASH)
        }

        private const val IS_USER_PRESENT_HASH = 36873697L
        private val isUserPresentBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "is_user_present", IS_USER_PRESENT_HASH)
        }

        private const val GET_DISPLAY_REFRESH_RATE_HASH = 1740695150L
        private val getDisplayRefreshRateBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_display_refresh_rate", GET_DISPLAY_REFRESH_RATE_HASH)
        }

        private const val SET_DISPLAY_REFRESH_RATE_HASH = 373806689L
        private val setDisplayRefreshRateBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "set_display_refresh_rate", SET_DISPLAY_REFRESH_RATE_HASH)
        }

        private const val GET_RENDER_TARGET_SIZE_MULTIPLIER_HASH = 1740695150L
        private val getRenderTargetSizeMultiplierBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_render_target_size_multiplier", GET_RENDER_TARGET_SIZE_MULTIPLIER_HASH)
        }

        private const val SET_RENDER_TARGET_SIZE_MULTIPLIER_HASH = 373806689L
        private val setRenderTargetSizeMultiplierBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "set_render_target_size_multiplier", SET_RENDER_TARGET_SIZE_MULTIPLIER_HASH)
        }

        private const val IS_FOVEATION_SUPPORTED_HASH = 36873697L
        private val isFoveationSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "is_foveation_supported", IS_FOVEATION_SUPPORTED_HASH)
        }

        private const val GET_FOVEATION_LEVEL_HASH = 3905245786L
        private val getFoveationLevelBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_foveation_level", GET_FOVEATION_LEVEL_HASH)
        }

        private const val SET_FOVEATION_LEVEL_HASH = 1286410249L
        private val setFoveationLevelBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "set_foveation_level", SET_FOVEATION_LEVEL_HASH)
        }

        private const val GET_FOVEATION_DYNAMIC_HASH = 36873697L
        private val getFoveationDynamicBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_foveation_dynamic", GET_FOVEATION_DYNAMIC_HASH)
        }

        private const val SET_FOVEATION_DYNAMIC_HASH = 2586408642L
        private val setFoveationDynamicBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "set_foveation_dynamic", SET_FOVEATION_DYNAMIC_HASH)
        }

        private const val GET_FOVEATION_WITH_SUBSAMPLED_IMAGES_HASH = 36873697L
        private val getFoveationWithSubsampledImagesBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_foveation_with_subsampled_images", GET_FOVEATION_WITH_SUBSAMPLED_IMAGES_HASH)
        }

        private const val SET_FOVEATION_WITH_SUBSAMPLED_IMAGES_HASH = 2586408642L
        private val setFoveationWithSubsampledImagesBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "set_foveation_with_subsampled_images", SET_FOVEATION_WITH_SUBSAMPLED_IMAGES_HASH)
        }

        private const val IS_ACTION_SET_ACTIVE_HASH = 3927539163L
        private val isActionSetActiveBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "is_action_set_active", IS_ACTION_SET_ACTIVE_HASH)
        }

        private const val SET_ACTION_SET_ACTIVE_HASH = 2678287736L
        private val setActionSetActiveBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "set_action_set_active", SET_ACTION_SET_ACTIVE_HASH)
        }

        private const val GET_ACTION_SETS_HASH = 3995934104L
        private val getActionSetsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_action_sets", GET_ACTION_SETS_HASH)
        }

        private const val GET_AVAILABLE_DISPLAY_REFRESH_RATES_HASH = 3995934104L
        private val getAvailableDisplayRefreshRatesBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_available_display_refresh_rates", GET_AVAILABLE_DISPLAY_REFRESH_RATES_HASH)
        }

        private const val SET_MOTION_RANGE_HASH = 855158159L
        private val setMotionRangeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "set_motion_range", SET_MOTION_RANGE_HASH)
        }

        private const val GET_MOTION_RANGE_HASH = 3955838114L
        private val getMotionRangeBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_motion_range", GET_MOTION_RANGE_HASH)
        }

        private const val GET_HAND_TRACKING_SOURCE_HASH = 4092421202L
        private val getHandTrackingSourceBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_tracking_source", GET_HAND_TRACKING_SOURCE_HASH)
        }

        private const val GET_HAND_JOINT_FLAGS_HASH = 720567706L
        private val getHandJointFlagsBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_joint_flags", GET_HAND_JOINT_FLAGS_HASH)
        }

        private const val GET_HAND_JOINT_ROTATION_HASH = 1974618321L
        private val getHandJointRotationBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_joint_rotation", GET_HAND_JOINT_ROTATION_HASH)
        }

        private const val GET_HAND_JOINT_POSITION_HASH = 3529194242L
        private val getHandJointPositionBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_joint_position", GET_HAND_JOINT_POSITION_HASH)
        }

        private const val GET_HAND_JOINT_RADIUS_HASH = 901522724L
        private val getHandJointRadiusBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_joint_radius", GET_HAND_JOINT_RADIUS_HASH)
        }

        private const val GET_HAND_JOINT_LINEAR_VELOCITY_HASH = 3529194242L
        private val getHandJointLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_joint_linear_velocity", GET_HAND_JOINT_LINEAR_VELOCITY_HASH)
        }

        private const val GET_HAND_JOINT_ANGULAR_VELOCITY_HASH = 3529194242L
        private val getHandJointAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_hand_joint_angular_velocity", GET_HAND_JOINT_ANGULAR_VELOCITY_HASH)
        }

        private const val IS_HAND_TRACKING_SUPPORTED_HASH = 2240911060L
        private val isHandTrackingSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "is_hand_tracking_supported", IS_HAND_TRACKING_SUPPORTED_HASH)
        }

        private const val IS_HAND_INTERACTION_SUPPORTED_HASH = 36873697L
        private val isHandInteractionSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "is_hand_interaction_supported", IS_HAND_INTERACTION_SUPPORTED_HASH)
        }

        private const val IS_EYE_GAZE_INTERACTION_SUPPORTED_HASH = 2240911060L
        private val isEyeGazeInteractionSupportedBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "is_eye_gaze_interaction_supported", IS_EYE_GAZE_INTERACTION_SUPPORTED_HASH)
        }

        private const val GET_VRS_MIN_RADIUS_HASH = 1740695150L
        private val getVrsMinRadiusBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_vrs_min_radius", GET_VRS_MIN_RADIUS_HASH)
        }

        private const val SET_VRS_MIN_RADIUS_HASH = 373806689L
        private val setVrsMinRadiusBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "set_vrs_min_radius", SET_VRS_MIN_RADIUS_HASH)
        }

        private const val GET_VRS_STRENGTH_HASH = 1740695150L
        private val getVrsStrengthBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "get_vrs_strength", GET_VRS_STRENGTH_HASH)
        }

        private const val SET_VRS_STRENGTH_HASH = 373806689L
        private val setVrsStrengthBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "set_vrs_strength", SET_VRS_STRENGTH_HASH)
        }

        private const val SET_CPU_LEVEL_HASH = 2940842095L
        private val setCpuLevelBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "set_cpu_level", SET_CPU_LEVEL_HASH)
        }

        private const val SET_GPU_LEVEL_HASH = 2940842095L
        private val setGpuLevelBind by lazy {
            ObjectCalls.getMethodBind("OpenXRInterface", "set_gpu_level", SET_GPU_LEVEL_HASH)
        }
    }
}
