package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A physics joint that restricts the rotation of a 3D physics body around an axis relative to
 * another physics body.
 *
 * Generated from Godot docs: HingeJoint3D
 */
class HingeJoint3D(handle: GodotHandle) : Joint3D(handle) {
    /**
     * The speed with which the two bodies get pulled together when they move in different directions.
     *
     * Generated from Godot docs: HingeJoint3D.set_param
     */
    fun setParam(param: HingeJoint3D.Param, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamBind, segment, param.value, value)
    }

    /**
     * The speed with which the two bodies get pulled together when they move in different directions.
     *
     * Generated from Godot docs: HingeJoint3D.get_param
     */
    fun getParam(param: HingeJoint3D.Param): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamBind, segment, param.value)
    }

    /**
     * When activated, a motor turns the hinge.
     *
     * Generated from Godot docs: HingeJoint3D.set_flag
     */
    fun setFlag(flag: HingeJoint3D.Flag, enabled: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setFlagBind, segment, flag.value, enabled)
    }

    /**
     * When activated, a motor turns the hinge.
     *
     * Generated from Godot docs: HingeJoint3D.get_flag
     */
    fun getFlag(flag: HingeJoint3D.Flag): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getFlagBind, segment, flag.value)
    }

    @JvmInline
    value class Param(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The speed with which the two bodies get pulled together when they move in different directions.
             *
             * Generated from Godot docs: HingeJoint3D.PARAM_BIAS
             */
            val BIAS: Param get() = Param(0L)
            /**
             * The maximum rotation. Only active if `angular_limit/enable` is `true`.
             *
             * Generated from Godot docs: HingeJoint3D.PARAM_LIMIT_UPPER
             */
            val LIMIT_UPPER: Param get() = Param(1L)
            /**
             * The minimum rotation. Only active if `angular_limit/enable` is `true`.
             *
             * Generated from Godot docs: HingeJoint3D.PARAM_LIMIT_LOWER
             */
            val LIMIT_LOWER: Param get() = Param(2L)
            /**
             * The speed with which the rotation across the axis perpendicular to the hinge gets corrected.
             *
             * Generated from Godot docs: HingeJoint3D.PARAM_LIMIT_BIAS
             */
            val LIMIT_BIAS: Param get() = Param(3L)
            val LIMIT_SOFTNESS: Param get() = Param(4L)
            /**
             * The lower this value, the more the rotation gets slowed down.
             *
             * Generated from Godot docs: HingeJoint3D.PARAM_LIMIT_RELAXATION
             */
            val LIMIT_RELAXATION: Param get() = Param(5L)
            /**
             * Target speed for the motor.
             *
             * Generated from Godot docs: HingeJoint3D.PARAM_MOTOR_TARGET_VELOCITY
             */
            val MOTOR_TARGET_VELOCITY: Param get() = Param(6L)
            /**
             * Maximum acceleration for the motor.
             *
             * Generated from Godot docs: HingeJoint3D.PARAM_MOTOR_MAX_IMPULSE
             */
            val MOTOR_MAX_IMPULSE: Param get() = Param(7L)
            /**
             * Represents the size of the `Param` enum.
             *
             * Generated from Godot docs: HingeJoint3D.PARAM_MAX
             */
            val MAX: Param get() = Param(8L)
        }
    }

    @JvmInline
    value class Flag(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * If `true`, the hinges maximum and minimum rotation, defined by `angular_limit/lower` and
             * `angular_limit/upper` has effects.
             *
             * Generated from Godot docs: HingeJoint3D.FLAG_USE_LIMIT
             */
            val USE_LIMIT: Flag get() = Flag(0L)
            /**
             * When activated, a motor turns the hinge.
             *
             * Generated from Godot docs: HingeJoint3D.FLAG_ENABLE_MOTOR
             */
            val ENABLE_MOTOR: Flag get() = Flag(1L)
            /**
             * Represents the size of the `Flag` enum.
             *
             * Generated from Godot docs: HingeJoint3D.FLAG_MAX
             */
            val MAX: Flag get() = Flag(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): HingeJoint3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): HingeJoint3D? =
            if (handle.address() == 0L) null else HingeJoint3D(GodotHandle(handle))

        private const val SET_PARAM_HASH = 3082977519L
        private val setParamBind by lazy {
            ObjectCalls.getMethodBind("HingeJoint3D", "set_param", SET_PARAM_HASH)
        }

        private const val GET_PARAM_HASH = 4066002676L
        private val getParamBind by lazy {
            ObjectCalls.getMethodBind("HingeJoint3D", "get_param", GET_PARAM_HASH)
        }

        private const val SET_FLAG_HASH = 1083494620L
        private val setFlagBind by lazy {
            ObjectCalls.getMethodBind("HingeJoint3D", "set_flag", SET_FLAG_HASH)
        }

        private const val GET_FLAG_HASH = 2841369610L
        private val getFlagBind by lazy {
            ObjectCalls.getMethodBind("HingeJoint3D", "get_flag", GET_FLAG_HASH)
        }
    }
}
