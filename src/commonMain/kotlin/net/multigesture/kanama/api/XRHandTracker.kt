package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * A tracked hand in XR.
 *
 * Generated from Godot docs: XRHandTracker
 */
class XRHandTracker(handle: GodotHandle) : XRPositionalTracker(handle) {
    var hasTrackingData: Boolean
        @JvmName("hasTrackingDataProperty")
        get() = getHasTrackingData()
        @JvmName("setHasTrackingDataProperty")
        set(value) = setHasTrackingData(value)

    var handTrackingSource: Long
        @JvmName("handTrackingSourceProperty")
        get() = getHandTrackingSource()
        @JvmName("setHandTrackingSourceProperty")
        set(value) = setHandTrackingSource(value)

    /**
     * If `true`, the hand tracking data is valid.
     *
     * Generated from Godot docs: XRHandTracker.set_has_tracking_data
     */
    fun setHasTrackingData(hasData: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setHasTrackingDataBind, segment, hasData)
    }

    /**
     * If `true`, the hand tracking data is valid.
     *
     * Generated from Godot docs: XRHandTracker.get_has_tracking_data
     */
    fun getHasTrackingData(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getHasTrackingDataBind, segment)
    }

    /**
     * The source of the hand tracking data.
     *
     * Generated from Godot docs: XRHandTracker.set_hand_tracking_source
     */
    fun setHandTrackingSource(source: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setHandTrackingSourceBind, segment, source)
    }

    /**
     * The source of the hand tracking data.
     *
     * Generated from Godot docs: XRHandTracker.get_hand_tracking_source
     */
    fun getHandTrackingSource(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getHandTrackingSourceBind, segment)
    }

    /**
     * Sets flags about the validity of the tracking data for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.set_hand_joint_flags
     */
    fun setHandJointFlags(joint: Long, flags: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoLongArgs(setHandJointFlagsBind, segment, joint, flags)
    }

    /**
     * Returns flags about the validity of the tracking data for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.get_hand_joint_flags
     */
    fun getHandJointFlags(joint: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetLong(getHandJointFlagsBind, segment, joint)
    }

    /**
     * Sets the transform for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.set_hand_joint_transform
     */
    fun setHandJointTransform(joint: Long, transform: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndTransform3DArg(setHandJointTransformBind, segment, joint, transform)
    }

    /**
     * Returns the transform for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.get_hand_joint_transform
     */
    fun getHandJointTransform(joint: Long): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(getHandJointTransformBind, segment, joint)
    }

    /**
     * Sets the radius of the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.set_hand_joint_radius
     */
    fun setHandJointRadius(joint: Long, radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndDoubleArg(setHandJointRadiusBind, segment, joint, radius)
    }

    /**
     * Returns the radius of the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.get_hand_joint_radius
     */
    fun getHandJointRadius(joint: Long): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetDouble(getHandJointRadiusBind, segment, joint)
    }

    /**
     * Sets the linear velocity for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.set_hand_joint_linear_velocity
     */
    fun setHandJointLinearVelocity(joint: Long, linearVelocity: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndVector3Arg(setHandJointLinearVelocityBind, segment, joint, linearVelocity)
    }

    /**
     * Returns the linear velocity for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.get_hand_joint_linear_velocity
     */
    fun getHandJointLinearVelocity(joint: Long): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetVector3(getHandJointLinearVelocityBind, segment, joint)
    }

    /**
     * Sets the angular velocity for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.set_hand_joint_angular_velocity
     */
    fun setHandJointAngularVelocity(joint: Long, angularVelocity: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndVector3Arg(setHandJointAngularVelocityBind, segment, joint, angularVelocity)
    }

    /**
     * Returns the angular velocity for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.get_hand_joint_angular_velocity
     */
    fun getHandJointAngularVelocity(joint: Long): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetVector3(getHandJointAngularVelocityBind, segment, joint)
    }

    companion object {
        const val HAND_TRACKING_SOURCE_UNKNOWN: Long = 0L
        const val HAND_TRACKING_SOURCE_UNOBSTRUCTED: Long = 1L
        const val HAND_TRACKING_SOURCE_CONTROLLER: Long = 2L
        const val HAND_TRACKING_SOURCE_NOT_TRACKED: Long = 3L
        const val HAND_TRACKING_SOURCE_MAX: Long = 4L
        const val HAND_JOINT_PALM: Long = 0L
        const val HAND_JOINT_WRIST: Long = 1L
        const val HAND_JOINT_THUMB_METACARPAL: Long = 2L
        const val HAND_JOINT_THUMB_PHALANX_PROXIMAL: Long = 3L
        const val HAND_JOINT_THUMB_PHALANX_DISTAL: Long = 4L
        const val HAND_JOINT_THUMB_TIP: Long = 5L
        const val HAND_JOINT_INDEX_FINGER_METACARPAL: Long = 6L
        const val HAND_JOINT_INDEX_FINGER_PHALANX_PROXIMAL: Long = 7L
        const val HAND_JOINT_INDEX_FINGER_PHALANX_INTERMEDIATE: Long = 8L
        const val HAND_JOINT_INDEX_FINGER_PHALANX_DISTAL: Long = 9L
        const val HAND_JOINT_INDEX_FINGER_TIP: Long = 10L
        const val HAND_JOINT_MIDDLE_FINGER_METACARPAL: Long = 11L
        const val HAND_JOINT_MIDDLE_FINGER_PHALANX_PROXIMAL: Long = 12L
        const val HAND_JOINT_MIDDLE_FINGER_PHALANX_INTERMEDIATE: Long = 13L
        const val HAND_JOINT_MIDDLE_FINGER_PHALANX_DISTAL: Long = 14L
        const val HAND_JOINT_MIDDLE_FINGER_TIP: Long = 15L
        const val HAND_JOINT_RING_FINGER_METACARPAL: Long = 16L
        const val HAND_JOINT_RING_FINGER_PHALANX_PROXIMAL: Long = 17L
        const val HAND_JOINT_RING_FINGER_PHALANX_INTERMEDIATE: Long = 18L
        const val HAND_JOINT_RING_FINGER_PHALANX_DISTAL: Long = 19L
        const val HAND_JOINT_RING_FINGER_TIP: Long = 20L
        const val HAND_JOINT_PINKY_FINGER_METACARPAL: Long = 21L
        const val HAND_JOINT_PINKY_FINGER_PHALANX_PROXIMAL: Long = 22L
        const val HAND_JOINT_PINKY_FINGER_PHALANX_INTERMEDIATE: Long = 23L
        const val HAND_JOINT_PINKY_FINGER_PHALANX_DISTAL: Long = 24L
        const val HAND_JOINT_PINKY_FINGER_TIP: Long = 25L
        const val HAND_JOINT_MAX: Long = 26L
        const val HAND_JOINT_FLAG_ORIENTATION_VALID: Long = 1L
        const val HAND_JOINT_FLAG_ORIENTATION_TRACKED: Long = 2L
        const val HAND_JOINT_FLAG_POSITION_VALID: Long = 4L
        const val HAND_JOINT_FLAG_POSITION_TRACKED: Long = 8L
        const val HAND_JOINT_FLAG_LINEAR_VELOCITY_VALID: Long = 16L
        const val HAND_JOINT_FLAG_ANGULAR_VELOCITY_VALID: Long = 32L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRHandTracker? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): XRHandTracker? =
            if (handle.address() == 0L) null else XRHandTracker(GodotHandle(handle))

        private const val SET_HAS_TRACKING_DATA_HASH = 2586408642L
        private val setHasTrackingDataBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "set_has_tracking_data", SET_HAS_TRACKING_DATA_HASH)
        }

        private const val GET_HAS_TRACKING_DATA_HASH = 36873697L
        private val getHasTrackingDataBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "get_has_tracking_data", GET_HAS_TRACKING_DATA_HASH)
        }

        private const val SET_HAND_TRACKING_SOURCE_HASH = 2958308861L
        private val setHandTrackingSourceBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "set_hand_tracking_source", SET_HAND_TRACKING_SOURCE_HASH)
        }

        private const val GET_HAND_TRACKING_SOURCE_HASH = 2475045250L
        private val getHandTrackingSourceBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "get_hand_tracking_source", GET_HAND_TRACKING_SOURCE_HASH)
        }

        private const val SET_HAND_JOINT_FLAGS_HASH = 3028437365L
        private val setHandJointFlagsBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "set_hand_joint_flags", SET_HAND_JOINT_FLAGS_HASH)
        }

        private const val GET_HAND_JOINT_FLAGS_HASH = 1730972401L
        private val getHandJointFlagsBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "get_hand_joint_flags", GET_HAND_JOINT_FLAGS_HASH)
        }

        private const val SET_HAND_JOINT_TRANSFORM_HASH = 2529959613L
        private val setHandJointTransformBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "set_hand_joint_transform", SET_HAND_JOINT_TRANSFORM_HASH)
        }

        private const val GET_HAND_JOINT_TRANSFORM_HASH = 1090840196L
        private val getHandJointTransformBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "get_hand_joint_transform", GET_HAND_JOINT_TRANSFORM_HASH)
        }

        private const val SET_HAND_JOINT_RADIUS_HASH = 2723659615L
        private val setHandJointRadiusBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "set_hand_joint_radius", SET_HAND_JOINT_RADIUS_HASH)
        }

        private const val GET_HAND_JOINT_RADIUS_HASH = 3400025734L
        private val getHandJointRadiusBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "get_hand_joint_radius", GET_HAND_JOINT_RADIUS_HASH)
        }

        private const val SET_HAND_JOINT_LINEAR_VELOCITY_HASH = 1978646737L
        private val setHandJointLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "set_hand_joint_linear_velocity", SET_HAND_JOINT_LINEAR_VELOCITY_HASH)
        }

        private const val GET_HAND_JOINT_LINEAR_VELOCITY_HASH = 547240792L
        private val getHandJointLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "get_hand_joint_linear_velocity", GET_HAND_JOINT_LINEAR_VELOCITY_HASH)
        }

        private const val SET_HAND_JOINT_ANGULAR_VELOCITY_HASH = 1978646737L
        private val setHandJointAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "set_hand_joint_angular_velocity", SET_HAND_JOINT_ANGULAR_VELOCITY_HASH)
        }

        private const val GET_HAND_JOINT_ANGULAR_VELOCITY_HASH = 547240792L
        private val getHandJointAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("XRHandTracker", "get_hand_joint_angular_velocity", GET_HAND_JOINT_ANGULAR_VELOCITY_HASH)
        }
    }
}
