package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
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

    var handTrackingSource: XRHandTracker.HandTrackingSource
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
    fun setHandTrackingSource(source: XRHandTracker.HandTrackingSource) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setHandTrackingSourceBind, segment, source.value)
    }

    /**
     * The source of the hand tracking data.
     *
     * Generated from Godot docs: XRHandTracker.get_hand_tracking_source
     */
    fun getHandTrackingSource(): XRHandTracker.HandTrackingSource {
        checkOpen()
        return XRHandTracker.HandTrackingSource(ObjectCalls.ptrcallNoArgsRetLong(getHandTrackingSourceBind, segment))
    }

    /**
     * Sets flags about the validity of the tracking data for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.set_hand_joint_flags
     */
    fun setHandJointFlags(joint: XRHandTracker.HandJoint, flags: XRHandTracker.HandJointFlags) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoLongArgs(setHandJointFlagsBind, segment, joint.value, flags.value)
    }

    /**
     * Returns flags about the validity of the tracking data for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.get_hand_joint_flags
     */
    fun getHandJointFlags(joint: XRHandTracker.HandJoint): XRHandTracker.HandJointFlags {
        checkOpen()
        return XRHandTracker.HandJointFlags(ObjectCalls.ptrcallWithLongArgRetLong(getHandJointFlagsBind, segment, joint.value))
    }

    /**
     * Sets the transform for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.set_hand_joint_transform
     */
    fun setHandJointTransform(joint: XRHandTracker.HandJoint, transform: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndTransform3DArg(setHandJointTransformBind, segment, joint.value, transform)
    }

    /**
     * Returns the transform for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.get_hand_joint_transform
     */
    fun getHandJointTransform(joint: XRHandTracker.HandJoint): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(getHandJointTransformBind, segment, joint.value)
    }

    /**
     * Sets the radius of the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.set_hand_joint_radius
     */
    fun setHandJointRadius(joint: XRHandTracker.HandJoint, radius: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndDoubleArg(setHandJointRadiusBind, segment, joint.value, radius)
    }

    /**
     * Returns the radius of the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.get_hand_joint_radius
     */
    fun getHandJointRadius(joint: XRHandTracker.HandJoint): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetDouble(getHandJointRadiusBind, segment, joint.value)
    }

    /**
     * Sets the linear velocity for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.set_hand_joint_linear_velocity
     */
    fun setHandJointLinearVelocity(joint: XRHandTracker.HandJoint, linearVelocity: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndVector3Arg(setHandJointLinearVelocityBind, segment, joint.value, linearVelocity)
    }

    /**
     * Returns the linear velocity for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.get_hand_joint_linear_velocity
     */
    fun getHandJointLinearVelocity(joint: XRHandTracker.HandJoint): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetVector3(getHandJointLinearVelocityBind, segment, joint.value)
    }

    /**
     * Sets the angular velocity for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.set_hand_joint_angular_velocity
     */
    fun setHandJointAngularVelocity(joint: XRHandTracker.HandJoint, angularVelocity: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndVector3Arg(setHandJointAngularVelocityBind, segment, joint.value, angularVelocity)
    }

    /**
     * Returns the angular velocity for the given hand joint.
     *
     * Generated from Godot docs: XRHandTracker.get_hand_joint_angular_velocity
     */
    fun getHandJointAngularVelocity(joint: XRHandTracker.HandJoint): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetVector3(getHandJointAngularVelocityBind, segment, joint.value)
    }

    /**
     * Godot's `XRHandTracker.HandTrackingSource` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`XRHandTracker.HandTrackingSource.<NAME>`).
     *
     * Generated from Godot docs: XRHandTracker.HandTrackingSource
     */
    @JvmInline
    value class HandTrackingSource(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The source of hand tracking data is unknown.
             *
             * Generated from Godot docs: XRHandTracker.HAND_TRACKING_SOURCE_UNKNOWN
             */
            val UNKNOWN: HandTrackingSource get() = HandTrackingSource(0L)
            /**
             * The source of hand tracking data is unobstructed, meaning that an accurate method of hand
             * tracking is used. These include optical hand tracking, data gloves, etc.
             *
             * Generated from Godot docs: XRHandTracker.HAND_TRACKING_SOURCE_UNOBSTRUCTED
             */
            val UNOBSTRUCTED: HandTrackingSource get() = HandTrackingSource(1L)
            /**
             * The source of hand tracking data is a controller, meaning that joint positions are inferred from
             * controller inputs.
             *
             * Generated from Godot docs: XRHandTracker.HAND_TRACKING_SOURCE_CONTROLLER
             */
            val CONTROLLER: HandTrackingSource get() = HandTrackingSource(2L)
            /**
             * No hand tracking data is tracked, this either means the hand is obscured, the controller is
             * turned off, or tracking is not supported for the current input type.
             *
             * Generated from Godot docs: XRHandTracker.HAND_TRACKING_SOURCE_NOT_TRACKED
             */
            val NOT_TRACKED: HandTrackingSource get() = HandTrackingSource(3L)
            /**
             * Represents the size of the `HandTrackingSource` enum.
             *
             * Generated from Godot docs: XRHandTracker.HAND_TRACKING_SOURCE_MAX
             */
            val MAX: HandTrackingSource get() = HandTrackingSource(4L)
        }
    }

    /**
     * Godot's `XRHandTracker.HandJoint` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`XRHandTracker.HandJoint.<NAME>`).
     *
     * Generated from Godot docs: XRHandTracker.HandJoint
     */
    @JvmInline
    value class HandJoint(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Palm joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_PALM
             */
            val PALM: HandJoint get() = HandJoint(0L)
            /**
             * Wrist joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_WRIST
             */
            val WRIST: HandJoint get() = HandJoint(1L)
            /**
             * Thumb metacarpal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_THUMB_METACARPAL
             */
            val THUMB_METACARPAL: HandJoint get() = HandJoint(2L)
            /**
             * Thumb phalanx proximal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_THUMB_PHALANX_PROXIMAL
             */
            val THUMB_PHALANX_PROXIMAL: HandJoint get() = HandJoint(3L)
            /**
             * Thumb phalanx distal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_THUMB_PHALANX_DISTAL
             */
            val THUMB_PHALANX_DISTAL: HandJoint get() = HandJoint(4L)
            /**
             * Thumb tip joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_THUMB_TIP
             */
            val THUMB_TIP: HandJoint get() = HandJoint(5L)
            /**
             * Index finger metacarpal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_INDEX_FINGER_METACARPAL
             */
            val INDEX_FINGER_METACARPAL: HandJoint get() = HandJoint(6L)
            /**
             * Index finger phalanx proximal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_INDEX_FINGER_PHALANX_PROXIMAL
             */
            val INDEX_FINGER_PHALANX_PROXIMAL: HandJoint get() = HandJoint(7L)
            /**
             * Index finger phalanx intermediate joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_INDEX_FINGER_PHALANX_INTERMEDIATE
             */
            val INDEX_FINGER_PHALANX_INTERMEDIATE: HandJoint get() = HandJoint(8L)
            /**
             * Index finger phalanx distal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_INDEX_FINGER_PHALANX_DISTAL
             */
            val INDEX_FINGER_PHALANX_DISTAL: HandJoint get() = HandJoint(9L)
            /**
             * Index finger tip joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_INDEX_FINGER_TIP
             */
            val INDEX_FINGER_TIP: HandJoint get() = HandJoint(10L)
            /**
             * Middle finger metacarpal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_MIDDLE_FINGER_METACARPAL
             */
            val MIDDLE_FINGER_METACARPAL: HandJoint get() = HandJoint(11L)
            /**
             * Middle finger phalanx proximal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_MIDDLE_FINGER_PHALANX_PROXIMAL
             */
            val MIDDLE_FINGER_PHALANX_PROXIMAL: HandJoint get() = HandJoint(12L)
            /**
             * Middle finger phalanx intermediate joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_MIDDLE_FINGER_PHALANX_INTERMEDIATE
             */
            val MIDDLE_FINGER_PHALANX_INTERMEDIATE: HandJoint get() = HandJoint(13L)
            /**
             * Middle finger phalanx distal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_MIDDLE_FINGER_PHALANX_DISTAL
             */
            val MIDDLE_FINGER_PHALANX_DISTAL: HandJoint get() = HandJoint(14L)
            /**
             * Middle finger tip joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_MIDDLE_FINGER_TIP
             */
            val MIDDLE_FINGER_TIP: HandJoint get() = HandJoint(15L)
            /**
             * Ring finger metacarpal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_RING_FINGER_METACARPAL
             */
            val RING_FINGER_METACARPAL: HandJoint get() = HandJoint(16L)
            /**
             * Ring finger phalanx proximal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_RING_FINGER_PHALANX_PROXIMAL
             */
            val RING_FINGER_PHALANX_PROXIMAL: HandJoint get() = HandJoint(17L)
            /**
             * Ring finger phalanx intermediate joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_RING_FINGER_PHALANX_INTERMEDIATE
             */
            val RING_FINGER_PHALANX_INTERMEDIATE: HandJoint get() = HandJoint(18L)
            /**
             * Ring finger phalanx distal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_RING_FINGER_PHALANX_DISTAL
             */
            val RING_FINGER_PHALANX_DISTAL: HandJoint get() = HandJoint(19L)
            /**
             * Ring finger tip joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_RING_FINGER_TIP
             */
            val RING_FINGER_TIP: HandJoint get() = HandJoint(20L)
            /**
             * Pinky finger metacarpal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_PINKY_FINGER_METACARPAL
             */
            val PINKY_FINGER_METACARPAL: HandJoint get() = HandJoint(21L)
            /**
             * Pinky finger phalanx proximal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_PINKY_FINGER_PHALANX_PROXIMAL
             */
            val PINKY_FINGER_PHALANX_PROXIMAL: HandJoint get() = HandJoint(22L)
            /**
             * Pinky finger phalanx intermediate joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_PINKY_FINGER_PHALANX_INTERMEDIATE
             */
            val PINKY_FINGER_PHALANX_INTERMEDIATE: HandJoint get() = HandJoint(23L)
            /**
             * Pinky finger phalanx distal joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_PINKY_FINGER_PHALANX_DISTAL
             */
            val PINKY_FINGER_PHALANX_DISTAL: HandJoint get() = HandJoint(24L)
            /**
             * Pinky finger tip joint.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_PINKY_FINGER_TIP
             */
            val PINKY_FINGER_TIP: HandJoint get() = HandJoint(25L)
            /**
             * Represents the size of the `HandJoint` enum.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_MAX
             */
            val MAX: HandJoint get() = HandJoint(26L)
        }
    }

    /**
     * Godot's `XRHandTracker.HandJointFlags` bitfield as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`XRHandTracker.HandJointFlags.<NAME>`).
     *
     * Generated from Godot docs: XRHandTracker.HandJointFlags
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
             * The hand joint's orientation data is valid.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_FLAG_ORIENTATION_VALID
             */
            val ORIENTATION_VALID: HandJointFlags get() = HandJointFlags(1L)
            /**
             * The hand joint's orientation is actively tracked. May not be set if tracking has been
             * temporarily lost.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_FLAG_ORIENTATION_TRACKED
             */
            val ORIENTATION_TRACKED: HandJointFlags get() = HandJointFlags(2L)
            /**
             * The hand joint's position data is valid.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_FLAG_POSITION_VALID
             */
            val POSITION_VALID: HandJointFlags get() = HandJointFlags(4L)
            /**
             * The hand joint's position is actively tracked. May not be set if tracking has been temporarily
             * lost.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_FLAG_POSITION_TRACKED
             */
            val POSITION_TRACKED: HandJointFlags get() = HandJointFlags(8L)
            /**
             * The hand joint's linear velocity data is valid.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_FLAG_LINEAR_VELOCITY_VALID
             */
            val LINEAR_VELOCITY_VALID: HandJointFlags get() = HandJointFlags(16L)
            /**
             * The hand joint's angular velocity data is valid.
             *
             * Generated from Godot docs: XRHandTracker.HAND_JOINT_FLAG_ANGULAR_VELOCITY_VALID
             */
            val ANGULAR_VELOCITY_VALID: HandJointFlags get() = HandJointFlags(32L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRHandTracker? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): XRHandTracker? =
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
