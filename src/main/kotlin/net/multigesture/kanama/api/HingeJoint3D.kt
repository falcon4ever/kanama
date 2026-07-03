package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A physics joint that restricts the rotation of a 3D physics body around an axis relative to
 * another physics body.
 *
 * Generated from Godot docs: HingeJoint3D
 */
class HingeJoint3D(handle: MemorySegment) : Joint3D(handle) {
    /**
     * The speed with which the two bodies get pulled together when they move in different directions.
     *
     * Generated from Godot docs: HingeJoint3D.set_param
     */
    fun setParam(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamBind, handle, param, value)
    }

    /**
     * The speed with which the two bodies get pulled together when they move in different directions.
     *
     * Generated from Godot docs: HingeJoint3D.get_param
     */
    fun getParam(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamBind, handle, param)
    }

    /**
     * When activated, a motor turns the hinge.
     *
     * Generated from Godot docs: HingeJoint3D.set_flag
     */
    fun setFlag(flag: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setFlagBind, handle, flag, enabled)
    }

    /**
     * When activated, a motor turns the hinge.
     *
     * Generated from Godot docs: HingeJoint3D.get_flag
     */
    fun getFlag(flag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getFlagBind, handle, flag)
    }

    companion object {
        const val PARAM_BIAS: Long = 0L
        const val PARAM_LIMIT_UPPER: Long = 1L
        const val PARAM_LIMIT_LOWER: Long = 2L
        const val PARAM_LIMIT_BIAS: Long = 3L
        const val PARAM_LIMIT_SOFTNESS: Long = 4L
        const val PARAM_LIMIT_RELAXATION: Long = 5L
        const val PARAM_MOTOR_TARGET_VELOCITY: Long = 6L
        const val PARAM_MOTOR_MAX_IMPULSE: Long = 7L
        const val PARAM_MAX: Long = 8L
        const val FLAG_USE_LIMIT: Long = 0L
        const val FLAG_ENABLE_MOTOR: Long = 1L
        const val FLAG_MAX: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): HingeJoint3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): HingeJoint3D? =
            if (handle.address() == 0L) null else HingeJoint3D(handle)

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
