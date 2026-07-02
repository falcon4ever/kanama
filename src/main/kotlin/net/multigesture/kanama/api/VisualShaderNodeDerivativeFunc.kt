package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeDerivativeFunc
 */
class VisualShaderNodeDerivativeFunc(handle: MemorySegment) : VisualShaderNode(handle) {
    var opType: Long
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    var function: Long
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    var precision: Long
        @JvmName("precisionProperty")
        get() = getPrecision()
        @JvmName("setPrecisionProperty")
        set(value) = setPrecision(value)

    fun setOpType(type: Long) {
        ObjectCalls.ptrcallWithLongArg(setOpTypeBind, handle, type)
    }

    fun getOpType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getOpTypeBind, handle)
    }

    fun setFunction(func: Long) {
        ObjectCalls.ptrcallWithLongArg(setFunctionBind, handle, func)
    }

    fun getFunction(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFunctionBind, handle)
    }

    fun setPrecision(precision: Long) {
        ObjectCalls.ptrcallWithLongArg(setPrecisionBind, handle, precision)
    }

    fun getPrecision(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPrecisionBind, handle)
    }

    companion object {
        const val OP_TYPE_SCALAR: Long = 0L
        const val OP_TYPE_VECTOR_2D: Long = 1L
        const val OP_TYPE_VECTOR_3D: Long = 2L
        const val OP_TYPE_VECTOR_4D: Long = 3L
        const val OP_TYPE_MAX: Long = 4L
        const val FUNC_SUM: Long = 0L
        const val FUNC_X: Long = 1L
        const val FUNC_Y: Long = 2L
        const val FUNC_MAX: Long = 3L
        const val PRECISION_NONE: Long = 0L
        const val PRECISION_COARSE: Long = 1L
        const val PRECISION_FINE: Long = 2L
        const val PRECISION_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeDerivativeFunc? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeDerivativeFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeDerivativeFunc(handle)

        private const val SET_OP_TYPE_HASH = 377800221L
        private val setOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeDerivativeFunc", "set_op_type", SET_OP_TYPE_HASH)
        }

        private const val GET_OP_TYPE_HASH = 3997800514L
        private val getOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeDerivativeFunc", "get_op_type", GET_OP_TYPE_HASH)
        }

        private const val SET_FUNCTION_HASH = 1944704156L
        private val setFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeDerivativeFunc", "set_function", SET_FUNCTION_HASH)
        }

        private const val GET_FUNCTION_HASH = 2389093396L
        private val getFunctionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeDerivativeFunc", "get_function", GET_FUNCTION_HASH)
        }

        private const val SET_PRECISION_HASH = 797270566L
        private val setPrecisionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeDerivativeFunc", "set_precision", SET_PRECISION_HASH)
        }

        private const val GET_PRECISION_HASH = 3822547323L
        private val getPrecisionBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeDerivativeFunc", "get_precision", GET_PRECISION_HASH)
        }
    }
}
