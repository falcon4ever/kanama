package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D

/**
 * A tracked body in XR.
 *
 * Generated from Godot docs: XRBodyTracker
 */
class XRBodyTracker(handle: GodotHandle) : XRPositionalTracker(handle) {
    var hasTrackingData: Boolean
        @JvmName("hasTrackingDataProperty")
        get() = getHasTrackingData()
        @JvmName("setHasTrackingDataProperty")
        set(value) = setHasTrackingData(value)

    var bodyFlags: XRBodyTracker.BodyFlags
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
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setHasTrackingDataBind, segment, hasData)
    }

    /**
     * If `true`, the body tracking data is valid.
     *
     * Generated from Godot docs: XRBodyTracker.get_has_tracking_data
     */
    fun getHasTrackingData(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getHasTrackingDataBind, segment)
    }

    /**
     * The type of body tracking data captured.
     *
     * Generated from Godot docs: XRBodyTracker.set_body_flags
     */
    fun setBodyFlags(flags: XRBodyTracker.BodyFlags) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setBodyFlagsBind, segment, flags.value)
    }

    /**
     * The type of body tracking data captured.
     *
     * Generated from Godot docs: XRBodyTracker.get_body_flags
     */
    fun getBodyFlags(): XRBodyTracker.BodyFlags {
        checkOpen()
        return XRBodyTracker.BodyFlags(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBodyFlagsBind, segment))
    }

    /**
     * Sets flags about the validity of the tracking data for the given body joint.
     *
     * Generated from Godot docs: XRBodyTracker.set_joint_flags
     */
    fun setJointFlags(joint: XRBodyTracker.Joint, flags: XRBodyTracker.JointFlags) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoLongArgs(Binds.setJointFlagsBind, segment, joint.value, flags.value)
    }

    /**
     * Returns flags about the validity of the tracking data for the given body joint.
     *
     * Generated from Godot docs: XRBodyTracker.get_joint_flags
     */
    fun getJointFlags(joint: XRBodyTracker.Joint): XRBodyTracker.JointFlags {
        checkOpen()
        return XRBodyTracker.JointFlags(ObjectCalls.ptrcallWithLongArgRetLong(Binds.getJointFlagsBind, segment, joint.value))
    }

    /**
     * Sets the transform for the given body joint.
     *
     * Generated from Godot docs: XRBodyTracker.set_joint_transform
     */
    fun setJointTransform(joint: XRBodyTracker.Joint, transform: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndTransform3DArg(Binds.setJointTransformBind, segment, joint.value, transform)
    }

    /**
     * Returns the transform for the given body joint.
     *
     * Generated from Godot docs: XRBodyTracker.get_joint_transform
     */
    fun getJointTransform(joint: XRBodyTracker.Joint): Transform3D {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetTransform3D(Binds.getJointTransformBind, segment, joint.value)
    }

    /**
     * Godot's `XRBodyTracker.BodyFlags` bitfield as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`XRBodyTracker.BodyFlags.<NAME>`).
     *
     * Generated from Godot docs: XRBodyTracker.BodyFlags
     */
    @JvmInline
    value class BodyFlags(override val value: Long) : GodotEnumValue {
        infix fun or(other: BodyFlags): BodyFlags = BodyFlags(value or other.value)

        infix fun and(other: BodyFlags): BodyFlags = BodyFlags(value and other.value)

        infix fun xor(other: BodyFlags): BodyFlags = BodyFlags(value xor other.value)

        fun inv(): BodyFlags = BodyFlags(value.inv())

        operator fun contains(other: BodyFlags): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Upper body tracking supported.
             *
             * Generated from Godot docs: XRBodyTracker.BODY_FLAG_UPPER_BODY_SUPPORTED
             */
            val UPPER_BODY_SUPPORTED: BodyFlags get() = BodyFlags(1L)
            /**
             * Lower body tracking supported.
             *
             * Generated from Godot docs: XRBodyTracker.BODY_FLAG_LOWER_BODY_SUPPORTED
             */
            val LOWER_BODY_SUPPORTED: BodyFlags get() = BodyFlags(2L)
            /**
             * Hand tracking supported.
             *
             * Generated from Godot docs: XRBodyTracker.BODY_FLAG_HANDS_SUPPORTED
             */
            val HANDS_SUPPORTED: BodyFlags get() = BodyFlags(4L)
        }
    }

    /**
     * Godot's `XRBodyTracker.Joint` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`XRBodyTracker.Joint.<NAME>`).
     *
     * Generated from Godot docs: XRBodyTracker.Joint
     */
    @JvmInline
    value class Joint(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Root joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_ROOT
             */
            val ROOT: Joint get() = Joint(0L)
            /**
             * Hips joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_HIPS
             */
            val HIPS: Joint get() = Joint(1L)
            /**
             * Spine joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_SPINE
             */
            val SPINE: Joint get() = Joint(2L)
            /**
             * Chest joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_CHEST
             */
            val CHEST: Joint get() = Joint(3L)
            /**
             * Upper chest joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_UPPER_CHEST
             */
            val UPPER_CHEST: Joint get() = Joint(4L)
            /**
             * Neck joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_NECK
             */
            val NECK: Joint get() = Joint(5L)
            /**
             * Head joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_HEAD
             */
            val HEAD: Joint get() = Joint(6L)
            /**
             * Head tip joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_HEAD_TIP
             */
            val HEAD_TIP: Joint get() = Joint(7L)
            /**
             * Left shoulder joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_SHOULDER
             */
            val LEFT_SHOULDER: Joint get() = Joint(8L)
            /**
             * Left upper arm joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_UPPER_ARM
             */
            val LEFT_UPPER_ARM: Joint get() = Joint(9L)
            /**
             * Left lower arm joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_LOWER_ARM
             */
            val LEFT_LOWER_ARM: Joint get() = Joint(10L)
            /**
             * Right shoulder joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_SHOULDER
             */
            val RIGHT_SHOULDER: Joint get() = Joint(11L)
            /**
             * Right upper arm joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_UPPER_ARM
             */
            val RIGHT_UPPER_ARM: Joint get() = Joint(12L)
            /**
             * Right lower arm joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_LOWER_ARM
             */
            val RIGHT_LOWER_ARM: Joint get() = Joint(13L)
            /**
             * Left upper leg joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_UPPER_LEG
             */
            val LEFT_UPPER_LEG: Joint get() = Joint(14L)
            /**
             * Left lower leg joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_LOWER_LEG
             */
            val LEFT_LOWER_LEG: Joint get() = Joint(15L)
            /**
             * Left foot joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_FOOT
             */
            val LEFT_FOOT: Joint get() = Joint(16L)
            /**
             * Left toes joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_TOES
             */
            val LEFT_TOES: Joint get() = Joint(17L)
            /**
             * Right upper leg joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_UPPER_LEG
             */
            val RIGHT_UPPER_LEG: Joint get() = Joint(18L)
            /**
             * Right lower leg joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_LOWER_LEG
             */
            val RIGHT_LOWER_LEG: Joint get() = Joint(19L)
            /**
             * Right foot joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_FOOT
             */
            val RIGHT_FOOT: Joint get() = Joint(20L)
            /**
             * Right toes joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_TOES
             */
            val RIGHT_TOES: Joint get() = Joint(21L)
            /**
             * Left hand joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_HAND
             */
            val LEFT_HAND: Joint get() = Joint(22L)
            /**
             * Left palm joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_PALM
             */
            val LEFT_PALM: Joint get() = Joint(23L)
            /**
             * Left wrist joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_WRIST
             */
            val LEFT_WRIST: Joint get() = Joint(24L)
            /**
             * Left thumb metacarpal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_THUMB_METACARPAL
             */
            val LEFT_THUMB_METACARPAL: Joint get() = Joint(25L)
            /**
             * Left thumb phalanx proximal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_THUMB_PHALANX_PROXIMAL
             */
            val LEFT_THUMB_PHALANX_PROXIMAL: Joint get() = Joint(26L)
            /**
             * Left thumb phalanx distal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_THUMB_PHALANX_DISTAL
             */
            val LEFT_THUMB_PHALANX_DISTAL: Joint get() = Joint(27L)
            /**
             * Left thumb tip joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_THUMB_TIP
             */
            val LEFT_THUMB_TIP: Joint get() = Joint(28L)
            /**
             * Left index finger metacarpal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_INDEX_FINGER_METACARPAL
             */
            val LEFT_INDEX_FINGER_METACARPAL: Joint get() = Joint(29L)
            /**
             * Left index finger phalanx proximal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_INDEX_FINGER_PHALANX_PROXIMAL
             */
            val LEFT_INDEX_FINGER_PHALANX_PROXIMAL: Joint get() = Joint(30L)
            /**
             * Left index finger phalanx intermediate joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_INDEX_FINGER_PHALANX_INTERMEDIATE
             */
            val LEFT_INDEX_FINGER_PHALANX_INTERMEDIATE: Joint get() = Joint(31L)
            /**
             * Left index finger phalanx distal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_INDEX_FINGER_PHALANX_DISTAL
             */
            val LEFT_INDEX_FINGER_PHALANX_DISTAL: Joint get() = Joint(32L)
            /**
             * Left index finger tip joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_INDEX_FINGER_TIP
             */
            val LEFT_INDEX_FINGER_TIP: Joint get() = Joint(33L)
            /**
             * Left middle finger metacarpal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_MIDDLE_FINGER_METACARPAL
             */
            val LEFT_MIDDLE_FINGER_METACARPAL: Joint get() = Joint(34L)
            /**
             * Left middle finger phalanx proximal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_MIDDLE_FINGER_PHALANX_PROXIMAL
             */
            val LEFT_MIDDLE_FINGER_PHALANX_PROXIMAL: Joint get() = Joint(35L)
            /**
             * Left middle finger phalanx intermediate joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_MIDDLE_FINGER_PHALANX_INTERMEDIATE
             */
            val LEFT_MIDDLE_FINGER_PHALANX_INTERMEDIATE: Joint get() = Joint(36L)
            /**
             * Left middle finger phalanx distal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_MIDDLE_FINGER_PHALANX_DISTAL
             */
            val LEFT_MIDDLE_FINGER_PHALANX_DISTAL: Joint get() = Joint(37L)
            /**
             * Left middle finger tip joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_MIDDLE_FINGER_TIP
             */
            val LEFT_MIDDLE_FINGER_TIP: Joint get() = Joint(38L)
            /**
             * Left ring finger metacarpal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_RING_FINGER_METACARPAL
             */
            val LEFT_RING_FINGER_METACARPAL: Joint get() = Joint(39L)
            /**
             * Left ring finger phalanx proximal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_RING_FINGER_PHALANX_PROXIMAL
             */
            val LEFT_RING_FINGER_PHALANX_PROXIMAL: Joint get() = Joint(40L)
            /**
             * Left ring finger phalanx intermediate joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_RING_FINGER_PHALANX_INTERMEDIATE
             */
            val LEFT_RING_FINGER_PHALANX_INTERMEDIATE: Joint get() = Joint(41L)
            /**
             * Left ring finger phalanx distal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_RING_FINGER_PHALANX_DISTAL
             */
            val LEFT_RING_FINGER_PHALANX_DISTAL: Joint get() = Joint(42L)
            /**
             * Left ring finger tip joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_RING_FINGER_TIP
             */
            val LEFT_RING_FINGER_TIP: Joint get() = Joint(43L)
            /**
             * Left pinky finger metacarpal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_PINKY_FINGER_METACARPAL
             */
            val LEFT_PINKY_FINGER_METACARPAL: Joint get() = Joint(44L)
            /**
             * Left pinky finger phalanx proximal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_PINKY_FINGER_PHALANX_PROXIMAL
             */
            val LEFT_PINKY_FINGER_PHALANX_PROXIMAL: Joint get() = Joint(45L)
            /**
             * Left pinky finger phalanx intermediate joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_PINKY_FINGER_PHALANX_INTERMEDIATE
             */
            val LEFT_PINKY_FINGER_PHALANX_INTERMEDIATE: Joint get() = Joint(46L)
            /**
             * Left pinky finger phalanx distal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_PINKY_FINGER_PHALANX_DISTAL
             */
            val LEFT_PINKY_FINGER_PHALANX_DISTAL: Joint get() = Joint(47L)
            /**
             * Left pinky finger tip joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_PINKY_FINGER_TIP
             */
            val LEFT_PINKY_FINGER_TIP: Joint get() = Joint(48L)
            /**
             * Right hand joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_HAND
             */
            val RIGHT_HAND: Joint get() = Joint(49L)
            /**
             * Right palm joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_PALM
             */
            val RIGHT_PALM: Joint get() = Joint(50L)
            /**
             * Right wrist joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_WRIST
             */
            val RIGHT_WRIST: Joint get() = Joint(51L)
            /**
             * Right thumb metacarpal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_THUMB_METACARPAL
             */
            val RIGHT_THUMB_METACARPAL: Joint get() = Joint(52L)
            /**
             * Right thumb phalanx proximal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_THUMB_PHALANX_PROXIMAL
             */
            val RIGHT_THUMB_PHALANX_PROXIMAL: Joint get() = Joint(53L)
            /**
             * Right thumb phalanx distal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_THUMB_PHALANX_DISTAL
             */
            val RIGHT_THUMB_PHALANX_DISTAL: Joint get() = Joint(54L)
            /**
             * Right thumb tip joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_THUMB_TIP
             */
            val RIGHT_THUMB_TIP: Joint get() = Joint(55L)
            /**
             * Right index finger metacarpal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_INDEX_FINGER_METACARPAL
             */
            val RIGHT_INDEX_FINGER_METACARPAL: Joint get() = Joint(56L)
            /**
             * Right index finger phalanx proximal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_INDEX_FINGER_PHALANX_PROXIMAL
             */
            val RIGHT_INDEX_FINGER_PHALANX_PROXIMAL: Joint get() = Joint(57L)
            /**
             * Right index finger phalanx intermediate joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_INDEX_FINGER_PHALANX_INTERMEDIATE
             */
            val RIGHT_INDEX_FINGER_PHALANX_INTERMEDIATE: Joint get() = Joint(58L)
            /**
             * Right index finger phalanx distal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_INDEX_FINGER_PHALANX_DISTAL
             */
            val RIGHT_INDEX_FINGER_PHALANX_DISTAL: Joint get() = Joint(59L)
            /**
             * Right index finger tip joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_INDEX_FINGER_TIP
             */
            val RIGHT_INDEX_FINGER_TIP: Joint get() = Joint(60L)
            /**
             * Right middle finger metacarpal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_MIDDLE_FINGER_METACARPAL
             */
            val RIGHT_MIDDLE_FINGER_METACARPAL: Joint get() = Joint(61L)
            /**
             * Right middle finger phalanx proximal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_MIDDLE_FINGER_PHALANX_PROXIMAL
             */
            val RIGHT_MIDDLE_FINGER_PHALANX_PROXIMAL: Joint get() = Joint(62L)
            /**
             * Right middle finger phalanx intermediate joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_MIDDLE_FINGER_PHALANX_INTERMEDIATE
             */
            val RIGHT_MIDDLE_FINGER_PHALANX_INTERMEDIATE: Joint get() = Joint(63L)
            /**
             * Right middle finger phalanx distal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_MIDDLE_FINGER_PHALANX_DISTAL
             */
            val RIGHT_MIDDLE_FINGER_PHALANX_DISTAL: Joint get() = Joint(64L)
            /**
             * Right middle finger tip joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_MIDDLE_FINGER_TIP
             */
            val RIGHT_MIDDLE_FINGER_TIP: Joint get() = Joint(65L)
            /**
             * Right ring finger metacarpal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_RING_FINGER_METACARPAL
             */
            val RIGHT_RING_FINGER_METACARPAL: Joint get() = Joint(66L)
            /**
             * Right ring finger phalanx proximal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_RING_FINGER_PHALANX_PROXIMAL
             */
            val RIGHT_RING_FINGER_PHALANX_PROXIMAL: Joint get() = Joint(67L)
            /**
             * Right ring finger phalanx intermediate joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_RING_FINGER_PHALANX_INTERMEDIATE
             */
            val RIGHT_RING_FINGER_PHALANX_INTERMEDIATE: Joint get() = Joint(68L)
            /**
             * Right ring finger phalanx distal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_RING_FINGER_PHALANX_DISTAL
             */
            val RIGHT_RING_FINGER_PHALANX_DISTAL: Joint get() = Joint(69L)
            /**
             * Right ring finger tip joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_RING_FINGER_TIP
             */
            val RIGHT_RING_FINGER_TIP: Joint get() = Joint(70L)
            /**
             * Right pinky finger metacarpal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_PINKY_FINGER_METACARPAL
             */
            val RIGHT_PINKY_FINGER_METACARPAL: Joint get() = Joint(71L)
            /**
             * Right pinky finger phalanx proximal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_PINKY_FINGER_PHALANX_PROXIMAL
             */
            val RIGHT_PINKY_FINGER_PHALANX_PROXIMAL: Joint get() = Joint(72L)
            /**
             * Right pinky finger phalanx intermediate joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_PINKY_FINGER_PHALANX_INTERMEDIATE
             */
            val RIGHT_PINKY_FINGER_PHALANX_INTERMEDIATE: Joint get() = Joint(73L)
            /**
             * Right pinky finger phalanx distal joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_PINKY_FINGER_PHALANX_DISTAL
             */
            val RIGHT_PINKY_FINGER_PHALANX_DISTAL: Joint get() = Joint(74L)
            /**
             * Right pinky finger tip joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_PINKY_FINGER_TIP
             */
            val RIGHT_PINKY_FINGER_TIP: Joint get() = Joint(75L)
            /**
             * Lower chest joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LOWER_CHEST
             */
            val LOWER_CHEST: Joint get() = Joint(76L)
            /**
             * Left scapula joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_SCAPULA
             */
            val LEFT_SCAPULA: Joint get() = Joint(77L)
            /**
             * Left wrist twist joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_WRIST_TWIST
             */
            val LEFT_WRIST_TWIST: Joint get() = Joint(78L)
            /**
             * Right scapula joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_SCAPULA
             */
            val RIGHT_SCAPULA: Joint get() = Joint(79L)
            /**
             * Right wrist twist joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_WRIST_TWIST
             */
            val RIGHT_WRIST_TWIST: Joint get() = Joint(80L)
            /**
             * Left foot twist joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_FOOT_TWIST
             */
            val LEFT_FOOT_TWIST: Joint get() = Joint(81L)
            /**
             * Left heel joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_HEEL
             */
            val LEFT_HEEL: Joint get() = Joint(82L)
            /**
             * Left middle foot joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_LEFT_MIDDLE_FOOT
             */
            val LEFT_MIDDLE_FOOT: Joint get() = Joint(83L)
            /**
             * Right foot twist joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_FOOT_TWIST
             */
            val RIGHT_FOOT_TWIST: Joint get() = Joint(84L)
            /**
             * Right heel joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_HEEL
             */
            val RIGHT_HEEL: Joint get() = Joint(85L)
            /**
             * Right middle foot joint.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_RIGHT_MIDDLE_FOOT
             */
            val RIGHT_MIDDLE_FOOT: Joint get() = Joint(86L)
            /**
             * Represents the size of the `Joint` enum.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_MAX
             */
            val MAX: Joint get() = Joint(87L)
        }
    }

    /**
     * Godot's `XRBodyTracker.JointFlags` bitfield as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`XRBodyTracker.JointFlags.<NAME>`).
     *
     * Generated from Godot docs: XRBodyTracker.JointFlags
     */
    @JvmInline
    value class JointFlags(override val value: Long) : GodotEnumValue {
        infix fun or(other: JointFlags): JointFlags = JointFlags(value or other.value)

        infix fun and(other: JointFlags): JointFlags = JointFlags(value and other.value)

        infix fun xor(other: JointFlags): JointFlags = JointFlags(value xor other.value)

        fun inv(): JointFlags = JointFlags(value.inv())

        operator fun contains(other: JointFlags): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * The joint's orientation data is valid.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_FLAG_ORIENTATION_VALID
             */
            val ORIENTATION_VALID: JointFlags get() = JointFlags(1L)
            /**
             * The joint's orientation is actively tracked. May not be set if tracking has been temporarily
             * lost.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_FLAG_ORIENTATION_TRACKED
             */
            val ORIENTATION_TRACKED: JointFlags get() = JointFlags(2L)
            /**
             * The joint's position data is valid.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_FLAG_POSITION_VALID
             */
            val POSITION_VALID: JointFlags get() = JointFlags(4L)
            /**
             * The joint's position is actively tracked. May not be set if tracking has been temporarily lost.
             *
             * Generated from Godot docs: XRBodyTracker.JOINT_FLAG_POSITION_TRACKED
             */
            val POSITION_TRACKED: JointFlags get() = JointFlags(8L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRBodyTracker? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): XRBodyTracker? =
            if (handle.address() == 0L) null else RefCounted.owned(XRBodyTracker(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): XRBodyTracker? =
            if (handle.address() == 0L) null else XRBodyTracker(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_HAS_TRACKING_DATA_HASH = 2586408642L
        @JvmField
        val setHasTrackingDataBind =
            ObjectCalls.getMethodBind("XRBodyTracker", "set_has_tracking_data", SET_HAS_TRACKING_DATA_HASH)

        private const val GET_HAS_TRACKING_DATA_HASH = 36873697L
        @JvmField
        val getHasTrackingDataBind =
            ObjectCalls.getMethodBind("XRBodyTracker", "get_has_tracking_data", GET_HAS_TRACKING_DATA_HASH)

        private const val SET_BODY_FLAGS_HASH = 2103235750L
        @JvmField
        val setBodyFlagsBind =
            ObjectCalls.getMethodBind("XRBodyTracker", "set_body_flags", SET_BODY_FLAGS_HASH)

        private const val GET_BODY_FLAGS_HASH = 3543166366L
        @JvmField
        val getBodyFlagsBind =
            ObjectCalls.getMethodBind("XRBodyTracker", "get_body_flags", GET_BODY_FLAGS_HASH)

        private const val SET_JOINT_FLAGS_HASH = 592144999L
        @JvmField
        val setJointFlagsBind =
            ObjectCalls.getMethodBind("XRBodyTracker", "set_joint_flags", SET_JOINT_FLAGS_HASH)

        private const val GET_JOINT_FLAGS_HASH = 1030162609L
        @JvmField
        val getJointFlagsBind =
            ObjectCalls.getMethodBind("XRBodyTracker", "get_joint_flags", GET_JOINT_FLAGS_HASH)

        private const val SET_JOINT_TRANSFORM_HASH = 2635424328L
        @JvmField
        val setJointTransformBind =
            ObjectCalls.getMethodBind("XRBodyTracker", "set_joint_transform", SET_JOINT_TRANSFORM_HASH)

        private const val GET_JOINT_TRANSFORM_HASH = 3474811534L
        @JvmField
        val getJointTransformBind =
            ObjectCalls.getMethodBind("XRBodyTracker", "get_joint_transform", GET_JOINT_TRANSFORM_HASH)
    }
}
