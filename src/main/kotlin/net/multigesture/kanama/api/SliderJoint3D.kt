package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A physics joint that restricts the movement of a 3D physics body along an axis relative to
 * another physics body.
 *
 * Generated from Godot docs: SliderJoint3D
 */
class SliderJoint3D(handle: MemorySegment) : Joint3D(handle) {
    /**
     * A factor applied to the movement across axes orthogonal to the slider.
     *
     * Generated from Godot docs: SliderJoint3D.set_param
     */
    fun setParam(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamBind, handle, param, value)
    }

    /**
     * A factor applied to the movement across axes orthogonal to the slider.
     *
     * Generated from Godot docs: SliderJoint3D.get_param
     */
    fun getParam(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamBind, handle, param)
    }

    companion object {
        const val PARAM_LINEAR_LIMIT_UPPER: Long = 0L
        const val PARAM_LINEAR_LIMIT_LOWER: Long = 1L
        const val PARAM_LINEAR_LIMIT_SOFTNESS: Long = 2L
        const val PARAM_LINEAR_LIMIT_RESTITUTION: Long = 3L
        const val PARAM_LINEAR_LIMIT_DAMPING: Long = 4L
        const val PARAM_LINEAR_MOTION_SOFTNESS: Long = 5L
        const val PARAM_LINEAR_MOTION_RESTITUTION: Long = 6L
        const val PARAM_LINEAR_MOTION_DAMPING: Long = 7L
        const val PARAM_LINEAR_ORTHOGONAL_SOFTNESS: Long = 8L
        const val PARAM_LINEAR_ORTHOGONAL_RESTITUTION: Long = 9L
        const val PARAM_LINEAR_ORTHOGONAL_DAMPING: Long = 10L
        const val PARAM_ANGULAR_LIMIT_UPPER: Long = 11L
        const val PARAM_ANGULAR_LIMIT_LOWER: Long = 12L
        const val PARAM_ANGULAR_LIMIT_SOFTNESS: Long = 13L
        const val PARAM_ANGULAR_LIMIT_RESTITUTION: Long = 14L
        const val PARAM_ANGULAR_LIMIT_DAMPING: Long = 15L
        const val PARAM_ANGULAR_MOTION_SOFTNESS: Long = 16L
        const val PARAM_ANGULAR_MOTION_RESTITUTION: Long = 17L
        const val PARAM_ANGULAR_MOTION_DAMPING: Long = 18L
        const val PARAM_ANGULAR_ORTHOGONAL_SOFTNESS: Long = 19L
        const val PARAM_ANGULAR_ORTHOGONAL_RESTITUTION: Long = 20L
        const val PARAM_ANGULAR_ORTHOGONAL_DAMPING: Long = 21L
        const val PARAM_MAX: Long = 22L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): SliderJoint3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SliderJoint3D? =
            if (handle.address() == 0L) null else SliderJoint3D(handle)

        private const val SET_PARAM_HASH = 918243683L
        private val setParamBind by lazy {
            ObjectCalls.getMethodBind("SliderJoint3D", "set_param", SET_PARAM_HASH)
        }

        private const val GET_PARAM_HASH = 959925627L
        private val getParamBind by lazy {
            ObjectCalls.getMethodBind("SliderJoint3D", "get_param", GET_PARAM_HASH)
        }
    }
}
