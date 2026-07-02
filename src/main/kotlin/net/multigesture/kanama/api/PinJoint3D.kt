package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A physics joint that attaches two 3D physics bodies at a single point, allowing them to freely
 * rotate.
 *
 * Generated from Godot docs: PinJoint3D
 */
class PinJoint3D(handle: MemorySegment) : Joint3D(handle) {
    fun setParam(param: Long, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamBind, handle, param, value)
    }

    fun getParam(param: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamBind, handle, param)
    }

    companion object {
        const val PARAM_BIAS: Long = 0L
        const val PARAM_DAMPING: Long = 1L
        const val PARAM_IMPULSE_CLAMP: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): PinJoint3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PinJoint3D? =
            if (handle.address() == 0L) null else PinJoint3D(handle)

        private const val SET_PARAM_HASH = 2059913726L
        private val setParamBind by lazy {
            ObjectCalls.getMethodBind("PinJoint3D", "set_param", SET_PARAM_HASH)
        }

        private const val GET_PARAM_HASH = 1758438771L
        private val getParamBind by lazy {
            ObjectCalls.getMethodBind("PinJoint3D", "get_param", GET_PARAM_HASH)
        }
    }
}
