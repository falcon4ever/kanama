package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A physics joint that restricts the movement of a 3D physics body along an axis relative to
 * another physics body.
 *
 * Generated from Godot docs: SliderJoint3D
 */
class SliderJoint3D(handle: GodotHandle) : Joint3D(handle) {
    /**
     * A factor applied to the movement across axes orthogonal to the slider.
     *
     * Generated from Godot docs: SliderJoint3D.set_param
     */
    fun setParam(param: SliderJoint3D.Param, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamBind, segment, param.value, value)
    }

    /**
     * A factor applied to the movement across axes orthogonal to the slider.
     *
     * Generated from Godot docs: SliderJoint3D.get_param
     */
    fun getParam(param: SliderJoint3D.Param): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamBind, segment, param.value)
    }

    /**
     * Godot's `SliderJoint3D.Param` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`SliderJoint3D.Param.<NAME>`).
     *
     * Generated from Godot docs: SliderJoint3D.Param
     */
    @JvmInline
    value class Param(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Constant for accessing `linear_limit/upper_distance`. The maximum difference between the pivot
             * points on their X axis before damping happens.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_LINEAR_LIMIT_UPPER
             */
            val LINEAR_LIMIT_UPPER: Param get() = Param(0L)
            /**
             * Constant for accessing `linear_limit/lower_distance`. The minimum difference between the pivot
             * points on their X axis before damping happens.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_LINEAR_LIMIT_LOWER
             */
            val LINEAR_LIMIT_LOWER: Param get() = Param(1L)
            /**
             * Constant for accessing `linear_limit/softness`. A factor applied to the movement across the
             * slider axis once the limits get surpassed. The lower, the slower the movement.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_LINEAR_LIMIT_SOFTNESS
             */
            val LINEAR_LIMIT_SOFTNESS: Param get() = Param(2L)
            /**
             * Constant for accessing `linear_limit/restitution`. The amount of restitution once the limits are
             * surpassed. The lower, the more velocity-energy gets lost.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_LINEAR_LIMIT_RESTITUTION
             */
            val LINEAR_LIMIT_RESTITUTION: Param get() = Param(3L)
            /**
             * Constant for accessing `linear_limit/damping`. The amount of damping once the slider limits are
             * surpassed.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_LINEAR_LIMIT_DAMPING
             */
            val LINEAR_LIMIT_DAMPING: Param get() = Param(4L)
            /**
             * Constant for accessing `linear_motion/softness`. A factor applied to the movement across the
             * slider axis as long as the slider is in the limits. The lower, the slower the movement.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_LINEAR_MOTION_SOFTNESS
             */
            val LINEAR_MOTION_SOFTNESS: Param get() = Param(5L)
            /**
             * Constant for accessing `linear_motion/restitution`. The amount of restitution inside the slider
             * limits.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_LINEAR_MOTION_RESTITUTION
             */
            val LINEAR_MOTION_RESTITUTION: Param get() = Param(6L)
            /**
             * Constant for accessing `linear_motion/damping`. The amount of damping inside the slider limits.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_LINEAR_MOTION_DAMPING
             */
            val LINEAR_MOTION_DAMPING: Param get() = Param(7L)
            /**
             * Constant for accessing `linear_ortho/softness`. A factor applied to the movement across axes
             * orthogonal to the slider.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_LINEAR_ORTHOGONAL_SOFTNESS
             */
            val LINEAR_ORTHOGONAL_SOFTNESS: Param get() = Param(8L)
            /**
             * Constant for accessing `linear_motion/restitution`. The amount of restitution when movement is
             * across axes orthogonal to the slider.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_LINEAR_ORTHOGONAL_RESTITUTION
             */
            val LINEAR_ORTHOGONAL_RESTITUTION: Param get() = Param(9L)
            /**
             * Constant for accessing `linear_motion/damping`. The amount of damping when movement is across
             * axes orthogonal to the slider.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_LINEAR_ORTHOGONAL_DAMPING
             */
            val LINEAR_ORTHOGONAL_DAMPING: Param get() = Param(10L)
            /**
             * Constant for accessing `angular_limit/upper_angle`. The upper limit of rotation in the slider.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_ANGULAR_LIMIT_UPPER
             */
            val ANGULAR_LIMIT_UPPER: Param get() = Param(11L)
            /**
             * Constant for accessing `angular_limit/lower_angle`. The lower limit of rotation in the slider.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_ANGULAR_LIMIT_LOWER
             */
            val ANGULAR_LIMIT_LOWER: Param get() = Param(12L)
            /**
             * Constant for accessing `angular_limit/softness`. A factor applied to the all rotation once the
             * limit is surpassed.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_ANGULAR_LIMIT_SOFTNESS
             */
            val ANGULAR_LIMIT_SOFTNESS: Param get() = Param(13L)
            /**
             * Constant for accessing `angular_limit/restitution`. The amount of restitution of the rotation
             * when the limit is surpassed.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_ANGULAR_LIMIT_RESTITUTION
             */
            val ANGULAR_LIMIT_RESTITUTION: Param get() = Param(14L)
            /**
             * Constant for accessing `angular_limit/damping`. The amount of damping of the rotation when the
             * limit is surpassed.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_ANGULAR_LIMIT_DAMPING
             */
            val ANGULAR_LIMIT_DAMPING: Param get() = Param(15L)
            /**
             * Constant for accessing `angular_motion/softness`. A factor applied to the all rotation in the
             * limits.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_ANGULAR_MOTION_SOFTNESS
             */
            val ANGULAR_MOTION_SOFTNESS: Param get() = Param(16L)
            /**
             * Constant for accessing `angular_motion/restitution`. The amount of restitution of the rotation
             * in the limits.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_ANGULAR_MOTION_RESTITUTION
             */
            val ANGULAR_MOTION_RESTITUTION: Param get() = Param(17L)
            /**
             * Constant for accessing `angular_motion/damping`. The amount of damping of the rotation in the
             * limits.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_ANGULAR_MOTION_DAMPING
             */
            val ANGULAR_MOTION_DAMPING: Param get() = Param(18L)
            /**
             * Constant for accessing `angular_ortho/softness`. A factor applied to the all rotation across
             * axes orthogonal to the slider.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_ANGULAR_ORTHOGONAL_SOFTNESS
             */
            val ANGULAR_ORTHOGONAL_SOFTNESS: Param get() = Param(19L)
            /**
             * Constant for accessing `angular_ortho/restitution`. The amount of restitution of the rotation
             * across axes orthogonal to the slider.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_ANGULAR_ORTHOGONAL_RESTITUTION
             */
            val ANGULAR_ORTHOGONAL_RESTITUTION: Param get() = Param(20L)
            /**
             * Constant for accessing `angular_ortho/damping`. The amount of damping of the rotation across
             * axes orthogonal to the slider.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_ANGULAR_ORTHOGONAL_DAMPING
             */
            val ANGULAR_ORTHOGONAL_DAMPING: Param get() = Param(21L)
            /**
             * Represents the size of the `Param` enum.
             *
             * Generated from Godot docs: SliderJoint3D.PARAM_MAX
             */
            val MAX: Param get() = Param(22L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SliderJoint3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SliderJoint3D? =
            if (handle.address() == 0L) null else SliderJoint3D(GodotHandle(handle))

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
