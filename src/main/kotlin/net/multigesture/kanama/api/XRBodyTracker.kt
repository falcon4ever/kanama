package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

/**
 * A tracked body in XR.
 *
 * Generated from Godot docs: XRBodyTracker
 */
class XRBodyTracker(handle: MemorySegment) : XRPositionalTracker(handle) {
    var hasTrackingData: Boolean
        @JvmName("hasTrackingDataProperty")
        get() = getHasTrackingData()
        @JvmName("setHasTrackingDataProperty")
        set(value) = setHasTrackingData(value)

    var bodyFlags: Long
        @JvmName("bodyFlagsProperty")
        get() = getBodyFlags()
        @JvmName("setBodyFlagsProperty")
        set(value) = setBodyFlags(value)

    /**
     * If `true`, the body tracking data is valid.
     *
     * Generated from Godot docs: XRBodyTracker.set_has_tracking_data
     */
    fun setHasTrackingData(hasData: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHasTrackingDataBind, handle, hasData)
    }

    /**
     * If `true`, the body tracking data is valid.
     *
     * Generated from Godot docs: XRBodyTracker.get_has_tracking_data
     */
    fun getHasTrackingData(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getHasTrackingDataBind, handle)
    }

    /**
     * The type of body tracking data captured.
     *
     * Generated from Godot docs: XRBodyTracker.set_body_flags
     */
    fun setBodyFlags(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setBodyFlagsBind, handle, flags)
    }

    /**
     * The type of body tracking data captured.
     *
     * Generated from Godot docs: XRBodyTracker.get_body_flags
     */
    fun getBodyFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBodyFlagsBind, handle)
    }

    /**
     * Sets flags about the validity of the tracking data for the given body joint.
     *
     * Generated from Godot docs: XRBodyTracker.set_joint_flags
     */
    fun setJointFlags(joint: Long, flags: Long) {
        ObjectCalls.ptrcallWithTwoLongArgs(setJointFlagsBind, handle, joint, flags)
    }

    /**
     * Returns flags about the validity of the tracking data for the given body joint.
     *
     * Generated from Godot docs: XRBodyTracker.get_joint_flags
     */
    fun getJointFlags(joint: Long): Long {
        return ObjectCalls.ptrcallWithLongArgRetLong(getJointFlagsBind, handle, joint)
    }

    /**
     * Sets the transform for the given body joint.
     *
     * Generated from Godot docs: XRBodyTracker.set_joint_transform
     */
    fun setJointTransform(joint: Long, transform: Transform3D) {
        ObjectCalls.ptrcallWithLongAndTransform3DArg(setJointTransformBind, handle, joint, transform)
    }

    /**
     * Returns the transform for the given body joint.
     *
     * Generated from Godot docs: XRBodyTracker.get_joint_transform
     */
    fun getJointTransform(joint: Long): Transform3D {
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(getJointTransformBind, handle, joint)
    }

    companion object {
        const val BODY_FLAG_UPPER_BODY_SUPPORTED: Long = 1L
        const val BODY_FLAG_LOWER_BODY_SUPPORTED: Long = 2L
        const val BODY_FLAG_HANDS_SUPPORTED: Long = 4L
        const val JOINT_ROOT: Long = 0L
        const val JOINT_HIPS: Long = 1L
        const val JOINT_SPINE: Long = 2L
        const val JOINT_CHEST: Long = 3L
        const val JOINT_UPPER_CHEST: Long = 4L
        const val JOINT_NECK: Long = 5L
        const val JOINT_HEAD: Long = 6L
        const val JOINT_HEAD_TIP: Long = 7L
        const val JOINT_LEFT_SHOULDER: Long = 8L
        const val JOINT_LEFT_UPPER_ARM: Long = 9L
        const val JOINT_LEFT_LOWER_ARM: Long = 10L
        const val JOINT_RIGHT_SHOULDER: Long = 11L
        const val JOINT_RIGHT_UPPER_ARM: Long = 12L
        const val JOINT_RIGHT_LOWER_ARM: Long = 13L
        const val JOINT_LEFT_UPPER_LEG: Long = 14L
        const val JOINT_LEFT_LOWER_LEG: Long = 15L
        const val JOINT_LEFT_FOOT: Long = 16L
        const val JOINT_LEFT_TOES: Long = 17L
        const val JOINT_RIGHT_UPPER_LEG: Long = 18L
        const val JOINT_RIGHT_LOWER_LEG: Long = 19L
        const val JOINT_RIGHT_FOOT: Long = 20L
        const val JOINT_RIGHT_TOES: Long = 21L
        const val JOINT_LEFT_HAND: Long = 22L
        const val JOINT_LEFT_PALM: Long = 23L
        const val JOINT_LEFT_WRIST: Long = 24L
        const val JOINT_LEFT_THUMB_METACARPAL: Long = 25L
        const val JOINT_LEFT_THUMB_PHALANX_PROXIMAL: Long = 26L
        const val JOINT_LEFT_THUMB_PHALANX_DISTAL: Long = 27L
        const val JOINT_LEFT_THUMB_TIP: Long = 28L
        const val JOINT_LEFT_INDEX_FINGER_METACARPAL: Long = 29L
        const val JOINT_LEFT_INDEX_FINGER_PHALANX_PROXIMAL: Long = 30L
        const val JOINT_LEFT_INDEX_FINGER_PHALANX_INTERMEDIATE: Long = 31L
        const val JOINT_LEFT_INDEX_FINGER_PHALANX_DISTAL: Long = 32L
        const val JOINT_LEFT_INDEX_FINGER_TIP: Long = 33L
        const val JOINT_LEFT_MIDDLE_FINGER_METACARPAL: Long = 34L
        const val JOINT_LEFT_MIDDLE_FINGER_PHALANX_PROXIMAL: Long = 35L
        const val JOINT_LEFT_MIDDLE_FINGER_PHALANX_INTERMEDIATE: Long = 36L
        const val JOINT_LEFT_MIDDLE_FINGER_PHALANX_DISTAL: Long = 37L
        const val JOINT_LEFT_MIDDLE_FINGER_TIP: Long = 38L
        const val JOINT_LEFT_RING_FINGER_METACARPAL: Long = 39L
        const val JOINT_LEFT_RING_FINGER_PHALANX_PROXIMAL: Long = 40L
        const val JOINT_LEFT_RING_FINGER_PHALANX_INTERMEDIATE: Long = 41L
        const val JOINT_LEFT_RING_FINGER_PHALANX_DISTAL: Long = 42L
        const val JOINT_LEFT_RING_FINGER_TIP: Long = 43L
        const val JOINT_LEFT_PINKY_FINGER_METACARPAL: Long = 44L
        const val JOINT_LEFT_PINKY_FINGER_PHALANX_PROXIMAL: Long = 45L
        const val JOINT_LEFT_PINKY_FINGER_PHALANX_INTERMEDIATE: Long = 46L
        const val JOINT_LEFT_PINKY_FINGER_PHALANX_DISTAL: Long = 47L
        const val JOINT_LEFT_PINKY_FINGER_TIP: Long = 48L
        const val JOINT_RIGHT_HAND: Long = 49L
        const val JOINT_RIGHT_PALM: Long = 50L
        const val JOINT_RIGHT_WRIST: Long = 51L
        const val JOINT_RIGHT_THUMB_METACARPAL: Long = 52L
        const val JOINT_RIGHT_THUMB_PHALANX_PROXIMAL: Long = 53L
        const val JOINT_RIGHT_THUMB_PHALANX_DISTAL: Long = 54L
        const val JOINT_RIGHT_THUMB_TIP: Long = 55L
        const val JOINT_RIGHT_INDEX_FINGER_METACARPAL: Long = 56L
        const val JOINT_RIGHT_INDEX_FINGER_PHALANX_PROXIMAL: Long = 57L
        const val JOINT_RIGHT_INDEX_FINGER_PHALANX_INTERMEDIATE: Long = 58L
        const val JOINT_RIGHT_INDEX_FINGER_PHALANX_DISTAL: Long = 59L
        const val JOINT_RIGHT_INDEX_FINGER_TIP: Long = 60L
        const val JOINT_RIGHT_MIDDLE_FINGER_METACARPAL: Long = 61L
        const val JOINT_RIGHT_MIDDLE_FINGER_PHALANX_PROXIMAL: Long = 62L
        const val JOINT_RIGHT_MIDDLE_FINGER_PHALANX_INTERMEDIATE: Long = 63L
        const val JOINT_RIGHT_MIDDLE_FINGER_PHALANX_DISTAL: Long = 64L
        const val JOINT_RIGHT_MIDDLE_FINGER_TIP: Long = 65L
        const val JOINT_RIGHT_RING_FINGER_METACARPAL: Long = 66L
        const val JOINT_RIGHT_RING_FINGER_PHALANX_PROXIMAL: Long = 67L
        const val JOINT_RIGHT_RING_FINGER_PHALANX_INTERMEDIATE: Long = 68L
        const val JOINT_RIGHT_RING_FINGER_PHALANX_DISTAL: Long = 69L
        const val JOINT_RIGHT_RING_FINGER_TIP: Long = 70L
        const val JOINT_RIGHT_PINKY_FINGER_METACARPAL: Long = 71L
        const val JOINT_RIGHT_PINKY_FINGER_PHALANX_PROXIMAL: Long = 72L
        const val JOINT_RIGHT_PINKY_FINGER_PHALANX_INTERMEDIATE: Long = 73L
        const val JOINT_RIGHT_PINKY_FINGER_PHALANX_DISTAL: Long = 74L
        const val JOINT_RIGHT_PINKY_FINGER_TIP: Long = 75L
        const val JOINT_LOWER_CHEST: Long = 76L
        const val JOINT_LEFT_SCAPULA: Long = 77L
        const val JOINT_LEFT_WRIST_TWIST: Long = 78L
        const val JOINT_RIGHT_SCAPULA: Long = 79L
        const val JOINT_RIGHT_WRIST_TWIST: Long = 80L
        const val JOINT_LEFT_FOOT_TWIST: Long = 81L
        const val JOINT_LEFT_HEEL: Long = 82L
        const val JOINT_LEFT_MIDDLE_FOOT: Long = 83L
        const val JOINT_RIGHT_FOOT_TWIST: Long = 84L
        const val JOINT_RIGHT_HEEL: Long = 85L
        const val JOINT_RIGHT_MIDDLE_FOOT: Long = 86L
        const val JOINT_MAX: Long = 87L
        const val JOINT_FLAG_ORIENTATION_VALID: Long = 1L
        const val JOINT_FLAG_ORIENTATION_TRACKED: Long = 2L
        const val JOINT_FLAG_POSITION_VALID: Long = 4L
        const val JOINT_FLAG_POSITION_TRACKED: Long = 8L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRBodyTracker? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRBodyTracker? =
            if (handle.address() == 0L) null else XRBodyTracker(handle)

        private const val SET_HAS_TRACKING_DATA_HASH = 2586408642L
        private val setHasTrackingDataBind by lazy {
            ObjectCalls.getMethodBind("XRBodyTracker", "set_has_tracking_data", SET_HAS_TRACKING_DATA_HASH)
        }

        private const val GET_HAS_TRACKING_DATA_HASH = 36873697L
        private val getHasTrackingDataBind by lazy {
            ObjectCalls.getMethodBind("XRBodyTracker", "get_has_tracking_data", GET_HAS_TRACKING_DATA_HASH)
        }

        private const val SET_BODY_FLAGS_HASH = 2103235750L
        private val setBodyFlagsBind by lazy {
            ObjectCalls.getMethodBind("XRBodyTracker", "set_body_flags", SET_BODY_FLAGS_HASH)
        }

        private const val GET_BODY_FLAGS_HASH = 3543166366L
        private val getBodyFlagsBind by lazy {
            ObjectCalls.getMethodBind("XRBodyTracker", "get_body_flags", GET_BODY_FLAGS_HASH)
        }

        private const val SET_JOINT_FLAGS_HASH = 592144999L
        private val setJointFlagsBind by lazy {
            ObjectCalls.getMethodBind("XRBodyTracker", "set_joint_flags", SET_JOINT_FLAGS_HASH)
        }

        private const val GET_JOINT_FLAGS_HASH = 1030162609L
        private val getJointFlagsBind by lazy {
            ObjectCalls.getMethodBind("XRBodyTracker", "get_joint_flags", GET_JOINT_FLAGS_HASH)
        }

        private const val SET_JOINT_TRANSFORM_HASH = 2635424328L
        private val setJointTransformBind by lazy {
            ObjectCalls.getMethodBind("XRBodyTracker", "set_joint_transform", SET_JOINT_TRANSFORM_HASH)
        }

        private const val GET_JOINT_TRANSFORM_HASH = 3474811534L
        private val getJointTransformBind by lazy {
            ObjectCalls.getMethodBind("XRBodyTracker", "get_joint_transform", GET_JOINT_TRANSFORM_HASH)
        }
    }
}
