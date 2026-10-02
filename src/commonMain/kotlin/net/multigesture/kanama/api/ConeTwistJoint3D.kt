package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A physics joint that connects two 3D physics bodies in a way that simulates a ball-and-socket
 * joint.
 *
 * Generated from Godot docs: ConeTwistJoint3D
 */
class ConeTwistJoint3D(handle: GodotHandle) : Joint3D(handle) {
    var swingSpan: Double
        @JvmName("swingSpanProperty")
        get() = getParam(ConeTwistJoint3D.Param.SWING_SPAN)
        @JvmName("setSwingSpanProperty")
        set(value) = setParam(ConeTwistJoint3D.Param.SWING_SPAN, value)

    var twistSpan: Double
        @JvmName("twistSpanProperty")
        get() = getParam(ConeTwistJoint3D.Param.TWIST_SPAN)
        @JvmName("setTwistSpanProperty")
        set(value) = setParam(ConeTwistJoint3D.Param.TWIST_SPAN, value)

    var bias: Double
        @JvmName("biasProperty")
        get() = getParam(ConeTwistJoint3D.Param.BIAS)
        @JvmName("setBiasProperty")
        set(value) = setParam(ConeTwistJoint3D.Param.BIAS, value)

    var softness: Double
        @JvmName("softnessProperty")
        get() = getParam(ConeTwistJoint3D.Param.SOFTNESS)
        @JvmName("setSoftnessProperty")
        set(value) = setParam(ConeTwistJoint3D.Param.SOFTNESS, value)

    var relaxation: Double
        @JvmName("relaxationProperty")
        get() = getParam(ConeTwistJoint3D.Param.RELAXATION)
        @JvmName("setRelaxationProperty")
        set(value) = setParam(ConeTwistJoint3D.Param.RELAXATION, value)

    /**
     * Twist is the rotation around the twist axis, this value defined how far the joint can twist.
     * Twist is locked if below 0.05.
     *
     * Generated from Godot docs: ConeTwistJoint3D.set_param
     */
    fun setParam(param: ConeTwistJoint3D.Param, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamBind, segment, param.value, value)
    }

    /**
     * Twist is the rotation around the twist axis, this value defined how far the joint can twist.
     * Twist is locked if below 0.05.
     *
     * Generated from Godot docs: ConeTwistJoint3D.get_param
     */
    fun getParam(param: ConeTwistJoint3D.Param): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamBind, segment, param.value)
    }

    /**
     * Godot's `ConeTwistJoint3D.Param` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`ConeTwistJoint3D.Param.<NAME>`).
     *
     * Generated from Godot docs: ConeTwistJoint3D.Param
     */
    @JvmInline
    value class Param(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Swing is rotation from side to side, around the axis perpendicular to the twist axis. The swing
             * span defines, how much rotation will not get corrected along the swing axis. Could be defined as
             * looseness in the `ConeTwistJoint3D`. If below 0.05, this behavior is locked.
             *
             * Generated from Godot docs: ConeTwistJoint3D.PARAM_SWING_SPAN
             */
            val SWING_SPAN: Param get() = Param(0L)
            /**
             * Twist is the rotation around the twist axis, this value defined how far the joint can twist.
             * Twist is locked if below 0.05.
             *
             * Generated from Godot docs: ConeTwistJoint3D.PARAM_TWIST_SPAN
             */
            val TWIST_SPAN: Param get() = Param(1L)
            /**
             * The speed with which the swing or twist will take place. The higher, the faster.
             *
             * Generated from Godot docs: ConeTwistJoint3D.PARAM_BIAS
             */
            val BIAS: Param get() = Param(2L)
            /**
             * The ease with which the joint starts to twist. If it's too low, it takes more force to start
             * twisting the joint.
             *
             * Generated from Godot docs: ConeTwistJoint3D.PARAM_SOFTNESS
             */
            val SOFTNESS: Param get() = Param(3L)
            /**
             * Defines, how fast the swing- and twist-speed-difference on both sides gets synced.
             *
             * Generated from Godot docs: ConeTwistJoint3D.PARAM_RELAXATION
             */
            val RELAXATION: Param get() = Param(4L)
            /**
             * Represents the size of the `Param` enum.
             *
             * Generated from Godot docs: ConeTwistJoint3D.PARAM_MAX
             */
            val MAX: Param get() = Param(5L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): ConeTwistJoint3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): ConeTwistJoint3D? =
            if (handle.address() == 0L) null else ConeTwistJoint3D(GodotHandle(handle))

        private const val SET_PARAM_HASH = 1062470226L
        private val setParamBind by lazy {
            ObjectCalls.getMethodBind("ConeTwistJoint3D", "set_param", SET_PARAM_HASH)
        }

        private const val GET_PARAM_HASH = 2928790850L
        private val getParamBind by lazy {
            ObjectCalls.getMethodBind("ConeTwistJoint3D", "get_param", GET_PARAM_HASH)
        }
    }
}
