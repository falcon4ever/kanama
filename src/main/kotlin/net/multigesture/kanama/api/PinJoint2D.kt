package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A physics joint that attaches two 2D physics bodies at a single point, allowing them to freely
 * rotate.
 *
 * Generated from Godot docs: PinJoint2D
 */
class PinJoint2D(handle: MemorySegment) : Joint2D(handle) {
    var softness: Double
        @JvmName("softnessProperty")
        get() = getSoftness()
        @JvmName("setSoftnessProperty")
        set(value) = setSoftness(value)

    var angularLimitEnabled: Boolean
        @JvmName("angularLimitEnabledProperty")
        get() = isAngularLimitEnabled()
        @JvmName("setAngularLimitEnabledProperty")
        set(value) = setAngularLimitEnabled(value)

    var angularLimitLower: Double
        @JvmName("angularLimitLowerProperty")
        get() = getAngularLimitLower()
        @JvmName("setAngularLimitLowerProperty")
        set(value) = setAngularLimitLower(value)

    var angularLimitUpper: Double
        @JvmName("angularLimitUpperProperty")
        get() = getAngularLimitUpper()
        @JvmName("setAngularLimitUpperProperty")
        set(value) = setAngularLimitUpper(value)

    var motorEnabled: Boolean
        @JvmName("motorEnabledProperty")
        get() = isMotorEnabled()
        @JvmName("setMotorEnabledProperty")
        set(value) = setMotorEnabled(value)

    var motorTargetVelocity: Double
        @JvmName("motorTargetVelocityProperty")
        get() = getMotorTargetVelocity()
        @JvmName("setMotorTargetVelocityProperty")
        set(value) = setMotorTargetVelocity(value)

    fun setSoftness(softness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSoftnessBind, handle, softness)
    }

    fun getSoftness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSoftnessBind, handle)
    }

    fun setAngularLimitLower(angularLimitLower: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAngularLimitLowerBind, handle, angularLimitLower)
    }

    fun getAngularLimitLower(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAngularLimitLowerBind, handle)
    }

    fun setAngularLimitUpper(angularLimitUpper: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAngularLimitUpperBind, handle, angularLimitUpper)
    }

    fun getAngularLimitUpper(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAngularLimitUpperBind, handle)
    }

    fun setMotorTargetVelocity(motorTargetVelocity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMotorTargetVelocityBind, handle, motorTargetVelocity)
    }

    fun getMotorTargetVelocity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMotorTargetVelocityBind, handle)
    }

    fun setMotorEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMotorEnabledBind, handle, enabled)
    }

    fun isMotorEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMotorEnabledBind, handle)
    }

    fun setAngularLimitEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAngularLimitEnabledBind, handle, enabled)
    }

    fun isAngularLimitEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAngularLimitEnabledBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PinJoint2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PinJoint2D? =
            if (handle.address() == 0L) null else PinJoint2D(handle)

        private const val SET_SOFTNESS_HASH = 373806689L
        private val setSoftnessBind by lazy {
            ObjectCalls.getMethodBind("PinJoint2D", "set_softness", SET_SOFTNESS_HASH)
        }

        private const val GET_SOFTNESS_HASH = 1740695150L
        private val getSoftnessBind by lazy {
            ObjectCalls.getMethodBind("PinJoint2D", "get_softness", GET_SOFTNESS_HASH)
        }

        private const val SET_ANGULAR_LIMIT_LOWER_HASH = 373806689L
        private val setAngularLimitLowerBind by lazy {
            ObjectCalls.getMethodBind("PinJoint2D", "set_angular_limit_lower", SET_ANGULAR_LIMIT_LOWER_HASH)
        }

        private const val GET_ANGULAR_LIMIT_LOWER_HASH = 1740695150L
        private val getAngularLimitLowerBind by lazy {
            ObjectCalls.getMethodBind("PinJoint2D", "get_angular_limit_lower", GET_ANGULAR_LIMIT_LOWER_HASH)
        }

        private const val SET_ANGULAR_LIMIT_UPPER_HASH = 373806689L
        private val setAngularLimitUpperBind by lazy {
            ObjectCalls.getMethodBind("PinJoint2D", "set_angular_limit_upper", SET_ANGULAR_LIMIT_UPPER_HASH)
        }

        private const val GET_ANGULAR_LIMIT_UPPER_HASH = 1740695150L
        private val getAngularLimitUpperBind by lazy {
            ObjectCalls.getMethodBind("PinJoint2D", "get_angular_limit_upper", GET_ANGULAR_LIMIT_UPPER_HASH)
        }

        private const val SET_MOTOR_TARGET_VELOCITY_HASH = 373806689L
        private val setMotorTargetVelocityBind by lazy {
            ObjectCalls.getMethodBind("PinJoint2D", "set_motor_target_velocity", SET_MOTOR_TARGET_VELOCITY_HASH)
        }

        private const val GET_MOTOR_TARGET_VELOCITY_HASH = 1740695150L
        private val getMotorTargetVelocityBind by lazy {
            ObjectCalls.getMethodBind("PinJoint2D", "get_motor_target_velocity", GET_MOTOR_TARGET_VELOCITY_HASH)
        }

        private const val SET_MOTOR_ENABLED_HASH = 2586408642L
        private val setMotorEnabledBind by lazy {
            ObjectCalls.getMethodBind("PinJoint2D", "set_motor_enabled", SET_MOTOR_ENABLED_HASH)
        }

        private const val IS_MOTOR_ENABLED_HASH = 36873697L
        private val isMotorEnabledBind by lazy {
            ObjectCalls.getMethodBind("PinJoint2D", "is_motor_enabled", IS_MOTOR_ENABLED_HASH)
        }

        private const val SET_ANGULAR_LIMIT_ENABLED_HASH = 2586408642L
        private val setAngularLimitEnabledBind by lazy {
            ObjectCalls.getMethodBind("PinJoint2D", "set_angular_limit_enabled", SET_ANGULAR_LIMIT_ENABLED_HASH)
        }

        private const val IS_ANGULAR_LIMIT_ENABLED_HASH = 36873697L
        private val isAngularLimitEnabledBind by lazy {
            ObjectCalls.getMethodBind("PinJoint2D", "is_angular_limit_enabled", IS_ANGULAR_LIMIT_ENABLED_HASH)
        }
    }
}
