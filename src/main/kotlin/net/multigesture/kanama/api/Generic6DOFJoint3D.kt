package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A physics joint that allows for complex movement and rotation between two 3D physics bodies.
 *
 * Generated from Godot docs: Generic6DOFJoint3D
 */
class Generic6DOFJoint3D(handle: MemorySegment) : Joint3D(handle) {
    fun setParamX(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamXBind, handle, param, value)
    }

    fun getParamX(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamXBind, handle, param)
    }

    fun setParamY(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamYBind, handle, param, value)
    }

    fun getParamY(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamYBind, handle, param)
    }

    fun setParamZ(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamZBind, handle, param, value)
    }

    fun getParamZ(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamZBind, handle, param)
    }

    fun setFlagX(flag: Long, value: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setFlagXBind, handle, flag, value)
    }

    fun getFlagX(flag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getFlagXBind, handle, flag)
    }

    fun setFlagY(flag: Long, value: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setFlagYBind, handle, flag, value)
    }

    fun getFlagY(flag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getFlagYBind, handle, flag)
    }

    fun setFlagZ(flag: Long, value: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setFlagZBind, handle, flag, value)
    }

    fun getFlagZ(flag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getFlagZBind, handle, flag)
    }

    companion object {
        const val PARAM_LINEAR_LOWER_LIMIT: Long = 0L
        const val PARAM_LINEAR_UPPER_LIMIT: Long = 1L
        const val PARAM_LINEAR_LIMIT_SOFTNESS: Long = 2L
        const val PARAM_LINEAR_RESTITUTION: Long = 3L
        const val PARAM_LINEAR_DAMPING: Long = 4L
        const val PARAM_LINEAR_MOTOR_TARGET_VELOCITY: Long = 5L
        const val PARAM_LINEAR_MOTOR_FORCE_LIMIT: Long = 6L
        const val PARAM_LINEAR_SPRING_STIFFNESS: Long = 7L
        const val PARAM_LINEAR_SPRING_DAMPING: Long = 8L
        const val PARAM_LINEAR_SPRING_EQUILIBRIUM_POINT: Long = 9L
        const val PARAM_ANGULAR_LOWER_LIMIT: Long = 10L
        const val PARAM_ANGULAR_UPPER_LIMIT: Long = 11L
        const val PARAM_ANGULAR_LIMIT_SOFTNESS: Long = 12L
        const val PARAM_ANGULAR_DAMPING: Long = 13L
        const val PARAM_ANGULAR_RESTITUTION: Long = 14L
        const val PARAM_ANGULAR_FORCE_LIMIT: Long = 15L
        const val PARAM_ANGULAR_ERP: Long = 16L
        const val PARAM_ANGULAR_MOTOR_TARGET_VELOCITY: Long = 17L
        const val PARAM_ANGULAR_MOTOR_FORCE_LIMIT: Long = 18L
        const val PARAM_ANGULAR_SPRING_STIFFNESS: Long = 19L
        const val PARAM_ANGULAR_SPRING_DAMPING: Long = 20L
        const val PARAM_ANGULAR_SPRING_EQUILIBRIUM_POINT: Long = 21L
        const val PARAM_MAX: Long = 22L
        const val FLAG_ENABLE_LINEAR_LIMIT: Long = 0L
        const val FLAG_ENABLE_ANGULAR_LIMIT: Long = 1L
        const val FLAG_ENABLE_LINEAR_SPRING: Long = 3L
        const val FLAG_ENABLE_ANGULAR_SPRING: Long = 2L
        const val FLAG_ENABLE_MOTOR: Long = 4L
        const val FLAG_ENABLE_LINEAR_MOTOR: Long = 5L
        const val FLAG_MAX: Long = 6L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Generic6DOFJoint3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Generic6DOFJoint3D? =
            if (handle.address() == 0L) null else Generic6DOFJoint3D(handle)

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
