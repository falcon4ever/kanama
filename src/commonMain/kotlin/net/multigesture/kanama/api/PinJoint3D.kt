package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A physics joint that attaches two 3D physics bodies at a single point, allowing them to freely
 * rotate.
 *
 * Generated from Godot docs: PinJoint3D
 */
class PinJoint3D(handle: GodotHandle) : Joint3D(handle) {
    /**
     * If above 0, this value is the maximum value for an impulse that this Joint3D produces.
     *
     * Generated from Godot docs: PinJoint3D.set_param
     */
    fun setParam(param: PinJoint3D.Param, value: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setParamBind, segment, param.value, value)
    }

    /**
     * If above 0, this value is the maximum value for an impulse that this Joint3D produces.
     *
     * Generated from Godot docs: PinJoint3D.get_param
     */
    fun getParam(param: PinJoint3D.Param): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getParamBind, segment, param.value)
    }

    @JvmInline
    value class Param(val value: Long) {
        companion object {
            /**
             * The force with which the pinned objects stay in positional relation to each other. The higher,
             * the stronger.
             *
             * Generated from Godot docs: PinJoint3D.PARAM_BIAS
             */
            val BIAS: Param get() = Param(0L)
            /**
             * The force with which the pinned objects stay in velocity relation to each other. The higher, the
             * stronger.
             *
             * Generated from Godot docs: PinJoint3D.PARAM_DAMPING
             */
            val DAMPING: Param get() = Param(1L)
            /**
             * If above 0, this value is the maximum value for an impulse that this Joint3D produces.
             *
             * Generated from Godot docs: PinJoint3D.PARAM_IMPULSE_CLAMP
             */
            val IMPULSE_CLAMP: Param get() = Param(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PinJoint3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PinJoint3D? =
            if (handle.address() == 0L) null else PinJoint3D(GodotHandle(handle))

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
