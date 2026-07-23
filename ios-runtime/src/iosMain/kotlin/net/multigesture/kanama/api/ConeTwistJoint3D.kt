package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: ConeTwistJoint3D
 */
class ConeTwistJoint3D(handle: MemorySegment) : Joint3D(handle) {
    var swingSpan: Double
        @JvmName("swingSpanProperty")
        get() = getParam(0L)
        @JvmName("setSwingSpanProperty")
        set(value) = setParam(0L, value)

    var twistSpan: Double
        @JvmName("twistSpanProperty")
        get() = getParam(1L)
        @JvmName("setTwistSpanProperty")
        set(value) = setParam(1L, value)

    var bias: Double
        @JvmName("biasProperty")
        get() = getParam(2L)
        @JvmName("setBiasProperty")
        set(value) = setParam(2L, value)

    var softness: Double
        @JvmName("softnessProperty")
        get() = getParam(3L)
        @JvmName("setSoftnessProperty")
        set(value) = setParam(3L, value)

    var relaxation: Double
        @JvmName("relaxationProperty")
        get() = getParam(4L)
        @JvmName("setRelaxationProperty")
        set(value) = setParam(4L, value)

    fun setParam(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamBind, handle, param, value)
    }

    fun getParam(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamBind, handle, param)
    }

    companion object {
        const val PARAM_SWING_SPAN: Long = 0L
        const val PARAM_TWIST_SPAN: Long = 1L
        const val PARAM_BIAS: Long = 2L
        const val PARAM_SOFTNESS: Long = 3L
        const val PARAM_RELAXATION: Long = 4L
        const val PARAM_MAX: Long = 5L

        fun fromHandle(handle: MemorySegment): ConeTwistJoint3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): ConeTwistJoint3D? =
            if (handle.address() == 0L) null else ConeTwistJoint3D(handle)

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
