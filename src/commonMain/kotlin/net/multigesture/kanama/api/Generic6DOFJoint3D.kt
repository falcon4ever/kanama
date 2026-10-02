package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A physics joint that allows for complex movement and rotation between two 3D physics bodies.
 *
 * Generated from Godot docs: Generic6DOFJoint3D
 */
class Generic6DOFJoint3D(handle: GodotHandle) : Joint3D(handle) {
    /**
     * The speed that the linear motor will attempt to reach on the X axis.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.set_param_x
     */
    fun setParamX(param: Generic6DOFJoint3D.Param, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamXBind, segment, param.value, value)
    }

    /**
     * The speed that the linear motor will attempt to reach on the X axis.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.get_param_x
     */
    fun getParamX(param: Generic6DOFJoint3D.Param): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamXBind, segment, param.value)
    }

    /**
     * The speed that the linear motor will attempt to reach on the Y axis.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.set_param_y
     */
    fun setParamY(param: Generic6DOFJoint3D.Param, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamYBind, segment, param.value, value)
    }

    /**
     * The speed that the linear motor will attempt to reach on the Y axis.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.get_param_y
     */
    fun getParamY(param: Generic6DOFJoint3D.Param): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamYBind, segment, param.value)
    }

    /**
     * The speed that the linear motor will attempt to reach on the Z axis.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.set_param_z
     */
    fun setParamZ(param: Generic6DOFJoint3D.Param, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamZBind, segment, param.value, value)
    }

    /**
     * The speed that the linear motor will attempt to reach on the Z axis.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.get_param_z
     */
    fun getParamZ(param: Generic6DOFJoint3D.Param): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamZBind, segment, param.value)
    }

    /**
     * If `true`, then there is a linear motor on the X axis. It will attempt to reach the target
     * velocity while staying within the force limits.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.set_flag_x
     */
    fun setFlagX(flag: Generic6DOFJoint3D.Flag, value: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setFlagXBind, segment, flag.value, value)
    }

    /**
     * If `true`, then there is a linear motor on the X axis. It will attempt to reach the target
     * velocity while staying within the force limits.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.get_flag_x
     */
    fun getFlagX(flag: Generic6DOFJoint3D.Flag): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getFlagXBind, segment, flag.value)
    }

    /**
     * If `true`, then there is a linear motor on the Y axis. It will attempt to reach the target
     * velocity while staying within the force limits.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.set_flag_y
     */
    fun setFlagY(flag: Generic6DOFJoint3D.Flag, value: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setFlagYBind, segment, flag.value, value)
    }

    /**
     * If `true`, then there is a linear motor on the Y axis. It will attempt to reach the target
     * velocity while staying within the force limits.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.get_flag_y
     */
    fun getFlagY(flag: Generic6DOFJoint3D.Flag): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getFlagYBind, segment, flag.value)
    }

    /**
     * If `true`, then there is a linear motor on the Z axis. It will attempt to reach the target
     * velocity while staying within the force limits.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.set_flag_z
     */
    fun setFlagZ(flag: Generic6DOFJoint3D.Flag, value: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setFlagZBind, segment, flag.value, value)
    }

    /**
     * If `true`, then there is a linear motor on the Z axis. It will attempt to reach the target
     * velocity while staying within the force limits.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.get_flag_z
     */
    fun getFlagZ(flag: Generic6DOFJoint3D.Flag): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getFlagZBind, segment, flag.value)
    }

    /**
     * Godot's `Generic6DOFJoint3D.Param` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`Generic6DOFJoint3D.Param.<NAME>`).
     *
     * Generated from Godot docs: Generic6DOFJoint3D.Param
     */
    @JvmInline
    value class Param(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The minimum difference between the pivot points' axes.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_LINEAR_LOWER_LIMIT
             */
            val LINEAR_LOWER_LIMIT: Param get() = Param(0L)
            /**
             * The maximum difference between the pivot points' axes.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_LINEAR_UPPER_LIMIT
             */
            val LINEAR_UPPER_LIMIT: Param get() = Param(1L)
            /**
             * A factor applied to the movement across the axes. The lower, the slower the movement.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_LINEAR_LIMIT_SOFTNESS
             */
            val LINEAR_LIMIT_SOFTNESS: Param get() = Param(2L)
            /**
             * The amount of restitution on the axes' movement. The lower, the more momentum gets lost.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_LINEAR_RESTITUTION
             */
            val LINEAR_RESTITUTION: Param get() = Param(3L)
            /**
             * The amount of damping that happens at the linear motion across the axes.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_LINEAR_DAMPING
             */
            val LINEAR_DAMPING: Param get() = Param(4L)
            /**
             * The velocity the linear motor will try to reach.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_LINEAR_MOTOR_TARGET_VELOCITY
             */
            val LINEAR_MOTOR_TARGET_VELOCITY: Param get() = Param(5L)
            /**
             * The maximum force the linear motor will apply while trying to reach the velocity target.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_LINEAR_MOTOR_FORCE_LIMIT
             */
            val LINEAR_MOTOR_FORCE_LIMIT: Param get() = Param(6L)
            val LINEAR_SPRING_STIFFNESS: Param get() = Param(7L)
            val LINEAR_SPRING_DAMPING: Param get() = Param(8L)
            val LINEAR_SPRING_EQUILIBRIUM_POINT: Param get() = Param(9L)
            /**
             * The minimum rotation in negative direction to break loose and rotate around the axes.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_ANGULAR_LOWER_LIMIT
             */
            val ANGULAR_LOWER_LIMIT: Param get() = Param(10L)
            /**
             * The minimum rotation in positive direction to break loose and rotate around the axes.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_ANGULAR_UPPER_LIMIT
             */
            val ANGULAR_UPPER_LIMIT: Param get() = Param(11L)
            /**
             * The speed of all rotations across the axes.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_ANGULAR_LIMIT_SOFTNESS
             */
            val ANGULAR_LIMIT_SOFTNESS: Param get() = Param(12L)
            /**
             * The amount of rotational damping across the axes. The lower, the more damping occurs.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_ANGULAR_DAMPING
             */
            val ANGULAR_DAMPING: Param get() = Param(13L)
            /**
             * The amount of rotational restitution across the axes. The lower, the more restitution occurs.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_ANGULAR_RESTITUTION
             */
            val ANGULAR_RESTITUTION: Param get() = Param(14L)
            /**
             * The maximum amount of force that can occur, when rotating around the axes.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_ANGULAR_FORCE_LIMIT
             */
            val ANGULAR_FORCE_LIMIT: Param get() = Param(15L)
            /**
             * When rotating across the axes, this error tolerance factor defines how much the correction gets
             * slowed down. The lower, the slower.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_ANGULAR_ERP
             */
            val ANGULAR_ERP: Param get() = Param(16L)
            /**
             * Target speed for the motor at the axes.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_ANGULAR_MOTOR_TARGET_VELOCITY
             */
            val ANGULAR_MOTOR_TARGET_VELOCITY: Param get() = Param(17L)
            /**
             * Maximum acceleration for the motor at the axes.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_ANGULAR_MOTOR_FORCE_LIMIT
             */
            val ANGULAR_MOTOR_FORCE_LIMIT: Param get() = Param(18L)
            val ANGULAR_SPRING_STIFFNESS: Param get() = Param(19L)
            val ANGULAR_SPRING_DAMPING: Param get() = Param(20L)
            val ANGULAR_SPRING_EQUILIBRIUM_POINT: Param get() = Param(21L)
            /**
             * Represents the size of the `Param` enum.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_MAX
             */
            val MAX: Param get() = Param(22L)
        }
    }

    /**
     * Godot's `Generic6DOFJoint3D.Flag` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`Generic6DOFJoint3D.Flag.<NAME>`).
     *
     * Generated from Godot docs: Generic6DOFJoint3D.Flag
     */
    @JvmInline
    value class Flag(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * If enabled, linear motion is possible within the given limits.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.FLAG_ENABLE_LINEAR_LIMIT
             */
            val ENABLE_LINEAR_LIMIT: Flag get() = Flag(0L)
            /**
             * If enabled, rotational motion is possible within the given limits.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.FLAG_ENABLE_ANGULAR_LIMIT
             */
            val ENABLE_ANGULAR_LIMIT: Flag get() = Flag(1L)
            val ENABLE_LINEAR_SPRING: Flag get() = Flag(3L)
            val ENABLE_ANGULAR_SPRING: Flag get() = Flag(2L)
            /**
             * If enabled, there is a rotational motor across these axes.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.FLAG_ENABLE_MOTOR
             */
            val ENABLE_MOTOR: Flag get() = Flag(4L)
            /**
             * If enabled, there is a linear motor across these axes.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.FLAG_ENABLE_LINEAR_MOTOR
             */
            val ENABLE_LINEAR_MOTOR: Flag get() = Flag(5L)
            /**
             * Represents the size of the `Flag` enum.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.FLAG_MAX
             */
            val MAX: Flag get() = Flag(6L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Generic6DOFJoint3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Generic6DOFJoint3D? =
            if (handle.address() == 0L) null else Generic6DOFJoint3D(GodotHandle(handle))

        private const val SET_PARAM_X_HASH = 2018184242L
        private val setParamXBind by lazy {
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "set_param_x", SET_PARAM_X_HASH)
        }

        private const val GET_PARAM_X_HASH = 2599835054L
        private val getParamXBind by lazy {
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "get_param_x", GET_PARAM_X_HASH)
        }

        private const val SET_PARAM_Y_HASH = 2018184242L
        private val setParamYBind by lazy {
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "set_param_y", SET_PARAM_Y_HASH)
        }

        private const val GET_PARAM_Y_HASH = 2599835054L
        private val getParamYBind by lazy {
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "get_param_y", GET_PARAM_Y_HASH)
        }

        private const val SET_PARAM_Z_HASH = 2018184242L
        private val setParamZBind by lazy {
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "set_param_z", SET_PARAM_Z_HASH)
        }

        private const val GET_PARAM_Z_HASH = 2599835054L
        private val getParamZBind by lazy {
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "get_param_z", GET_PARAM_Z_HASH)
        }

        private const val SET_FLAG_X_HASH = 2451594564L
        private val setFlagXBind by lazy {
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "set_flag_x", SET_FLAG_X_HASH)
        }

        private const val GET_FLAG_X_HASH = 2122427807L
        private val getFlagXBind by lazy {
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "get_flag_x", GET_FLAG_X_HASH)
        }

        private const val SET_FLAG_Y_HASH = 2451594564L
        private val setFlagYBind by lazy {
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "set_flag_y", SET_FLAG_Y_HASH)
        }

        private const val GET_FLAG_Y_HASH = 2122427807L
        private val getFlagYBind by lazy {
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "get_flag_y", GET_FLAG_Y_HASH)
        }

        private const val SET_FLAG_Z_HASH = 2451594564L
        private val setFlagZBind by lazy {
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "set_flag_z", SET_FLAG_Z_HASH)
        }

        private const val GET_FLAG_Z_HASH = 2122427807L
        private val getFlagZBind by lazy {
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "get_flag_z", GET_FLAG_Z_HASH)
        }
    }
}
