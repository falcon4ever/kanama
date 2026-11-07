package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed
import net.multigesture.kanama.types.Quaternion

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
        ObjectCalls.ptrcallWithLongAndDoubleArg(Binds.setParamXBind, segment, param.value, value)
    }

    /**
     * The speed that the linear motor will attempt to reach on the X axis.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.get_param_x
     */
    fun getParamX(param: Generic6DOFJoint3D.Param): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(Binds.getParamXBind, segment, param.value)
    }

    /**
     * The speed that the linear motor will attempt to reach on the Y axis.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.set_param_y
     */
    fun setParamY(param: Generic6DOFJoint3D.Param, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(Binds.setParamYBind, segment, param.value, value)
    }

    /**
     * The speed that the linear motor will attempt to reach on the Y axis.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.get_param_y
     */
    fun getParamY(param: Generic6DOFJoint3D.Param): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(Binds.getParamYBind, segment, param.value)
    }

    /**
     * The speed that the linear motor will attempt to reach on the Z axis.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.set_param_z
     */
    fun setParamZ(param: Generic6DOFJoint3D.Param, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(Binds.setParamZBind, segment, param.value, value)
    }

    /**
     * The speed that the linear motor will attempt to reach on the Z axis.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.get_param_z
     */
    fun getParamZ(param: Generic6DOFJoint3D.Param): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(Binds.getParamZBind, segment, param.value)
    }

    /**
     * If `true`, then there is a linear motor on the X axis. It will attempt to reach the target
     * velocity while staying within the force limits.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.set_flag_x
     */
    fun setFlagX(flag: Generic6DOFJoint3D.Flag, value: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(Binds.setFlagXBind, segment, flag.value, value)
    }

    /**
     * If `true`, then there is a linear motor on the X axis. It will attempt to reach the target
     * velocity while staying within the force limits.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.get_flag_x
     */
    fun getFlagX(flag: Generic6DOFJoint3D.Flag): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.getFlagXBind, segment, flag.value)
    }

    /**
     * If `true`, then there is a linear motor on the Y axis. It will attempt to reach the target
     * velocity while staying within the force limits.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.set_flag_y
     */
    fun setFlagY(flag: Generic6DOFJoint3D.Flag, value: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(Binds.setFlagYBind, segment, flag.value, value)
    }

    /**
     * If `true`, then there is a linear motor on the Y axis. It will attempt to reach the target
     * velocity while staying within the force limits.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.get_flag_y
     */
    fun getFlagY(flag: Generic6DOFJoint3D.Flag): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.getFlagYBind, segment, flag.value)
    }

    /**
     * If `true`, then there is a linear motor on the Z axis. It will attempt to reach the target
     * velocity while staying within the force limits.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.set_flag_z
     */
    fun setFlagZ(flag: Generic6DOFJoint3D.Flag, value: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(Binds.setFlagZBind, segment, flag.value, value)
    }

    /**
     * If `true`, then there is a linear motor on the Z axis. It will attempt to reach the target
     * velocity while staying within the force limits.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.get_flag_z
     */
    fun getFlagZ(flag: Generic6DOFJoint3D.Flag): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.getFlagZBind, segment, flag.value)
    }

    /**
     * Sets the target angular orientation as a body-space quaternion describing the desired
     * orientation of body B relative to body A. Replaces any previously set target and supersedes
     * `Param.ANGULAR_SPRING_EQUILIBRIUM_POINT` until `clear_angular_target_rotation` is called. Note:
     * Only for Jolt backend. Other 3D physics backends may ignore this value.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.set_angular_target_rotation
     */
    fun setAngularTargetRotation(targetRotation: Quaternion) {
        ObjectCalls.ptrcallWithQuaternionArg(Binds.setAngularTargetRotationBind, segment, targetRotation)
    }

    /**
     * Returns the joint's current angular target as a body-space quaternion. If no explicit target was
     * set via `set_angular_target_rotation`, the value is derived from
     * `Param.ANGULAR_SPRING_EQUILIBRIUM_POINT` on backends that support it; other backends return the
     * identity quaternion.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.get_angular_target_rotation
     */
    fun getAngularTargetRotation(): Quaternion {
        return ObjectCalls.ptrcallNoArgsRetQuaternion(Binds.getAngularTargetRotationBind, segment)
    }

    /**
     * Returns `true` if a quaternion angular target was explicitly set via
     * `set_angular_target_rotation` and has not been cleared. Returns `false` when the joint is driven
     * by `Param.ANGULAR_SPRING_EQUILIBRIUM_POINT` only.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.has_target_rotation
     */
    fun hasTargetRotation(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasTargetRotationBind, segment)
    }

    /**
     * Clears the quaternion angular target. After clearing, the joint is driven by
     * `Param.ANGULAR_SPRING_EQUILIBRIUM_POINT` on each axis as configured.
     *
     * Generated from Godot docs: Generic6DOFJoint3D.clear_angular_target_rotation
     */
    fun clearAngularTargetRotation() {
        ObjectCalls.ptrcallNoArgs(Binds.clearAngularTargetRotationBind, segment)
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
             * The maximum force the joint can apply along this linear axis. Used by both the spring drive and
             * the linear motor; supersedes `Param.LINEAR_MOTOR_FORCE_LIMIT` when set. Note: Only for Jolt
             * backend. Other 3D physics backends may ignore this value.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_LINEAR_DRIVE_FORCE_LIMIT
             */
            val LINEAR_DRIVE_FORCE_LIMIT: Param get() = Param(22L)
            /**
             * The maximum torque the joint can apply around this angular axis. Used by both the spring drive
             * and the angular motor; supersedes `Param.ANGULAR_MOTOR_FORCE_LIMIT` when set. Note: Only for
             * Jolt backend. Other 3D physics backends may ignore this value.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_ANGULAR_DRIVE_TORQUE_LIMIT
             */
            val ANGULAR_DRIVE_TORQUE_LIMIT: Param get() = Param(23L)
            /**
             * Represents the size of the `Param` enum.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.PARAM_MAX
             */
            val MAX: Param get() = Param(24L)
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
             * If enabled, there is a rotational or angular motor across these axes.
             *
             * Generated from Godot docs: Generic6DOFJoint3D.FLAG_ENABLE_ANGULAR_MOTOR
             */
            val ENABLE_ANGULAR_MOTOR: Flag get() = Flag(4L)
            /**
             * If enabled, there is a rotational or angular motor across these axes.
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

        /** A new Generic6DOFJoint3D, GDScript's `Generic6DOFJoint3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): Generic6DOFJoint3D =
            Generic6DOFJoint3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("Generic6DOFJoint3D"), "Generic6DOFJoint3D")))
    }

    private object Binds {
        private const val SET_PARAM_X_HASH = 2018184242L
        @JvmField
        val setParamXBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "set_param_x", SET_PARAM_X_HASH)

        private const val GET_PARAM_X_HASH = 2599835054L
        @JvmField
        val getParamXBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "get_param_x", GET_PARAM_X_HASH)

        private const val SET_PARAM_Y_HASH = 2018184242L
        @JvmField
        val setParamYBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "set_param_y", SET_PARAM_Y_HASH)

        private const val GET_PARAM_Y_HASH = 2599835054L
        @JvmField
        val getParamYBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "get_param_y", GET_PARAM_Y_HASH)

        private const val SET_PARAM_Z_HASH = 2018184242L
        @JvmField
        val setParamZBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "set_param_z", SET_PARAM_Z_HASH)

        private const val GET_PARAM_Z_HASH = 2599835054L
        @JvmField
        val getParamZBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "get_param_z", GET_PARAM_Z_HASH)

        private const val SET_FLAG_X_HASH = 2451594564L
        @JvmField
        val setFlagXBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "set_flag_x", SET_FLAG_X_HASH)

        private const val GET_FLAG_X_HASH = 2122427807L
        @JvmField
        val getFlagXBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "get_flag_x", GET_FLAG_X_HASH)

        private const val SET_FLAG_Y_HASH = 2451594564L
        @JvmField
        val setFlagYBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "set_flag_y", SET_FLAG_Y_HASH)

        private const val GET_FLAG_Y_HASH = 2122427807L
        @JvmField
        val getFlagYBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "get_flag_y", GET_FLAG_Y_HASH)

        private const val SET_FLAG_Z_HASH = 2451594564L
        @JvmField
        val setFlagZBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "set_flag_z", SET_FLAG_Z_HASH)

        private const val GET_FLAG_Z_HASH = 2122427807L
        @JvmField
        val getFlagZBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "get_flag_z", GET_FLAG_Z_HASH)

        private const val SET_ANGULAR_TARGET_ROTATION_HASH = 1727505552L
        @JvmField
        val setAngularTargetRotationBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "set_angular_target_rotation", SET_ANGULAR_TARGET_ROTATION_HASH)

        private const val GET_ANGULAR_TARGET_ROTATION_HASH = 1222331677L
        @JvmField
        val getAngularTargetRotationBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "get_angular_target_rotation", GET_ANGULAR_TARGET_ROTATION_HASH)

        private const val HAS_TARGET_ROTATION_HASH = 36873697L
        @JvmField
        val hasTargetRotationBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "has_target_rotation", HAS_TARGET_ROTATION_HASH)

        private const val CLEAR_ANGULAR_TARGET_ROTATION_HASH = 3218959716L
        @JvmField
        val clearAngularTargetRotationBind =
            ObjectCalls.getMethodBind("Generic6DOFJoint3D", "clear_angular_target_rotation", CLEAR_ANGULAR_TARGET_ROTATION_HASH)
    }
}
