package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeDerivativeFunc
 */
class VisualShaderNodeDerivativeFunc(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeDerivativeFunc.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    var function: VisualShaderNodeDerivativeFunc.Function
        @JvmName("functionProperty")
        get() = getFunction()
        @JvmName("setFunctionProperty")
        set(value) = setFunction(value)

    var precision: VisualShaderNodeDerivativeFunc.Precision
        @JvmName("precisionProperty")
        get() = getPrecision()
        @JvmName("setPrecisionProperty")
        set(value) = setPrecision(value)

    fun setOpType(type: VisualShaderNodeDerivativeFunc.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOpTypeBind, segment, type.value)
    }

    fun getOpType(): VisualShaderNodeDerivativeFunc.OpType {
        checkOpen()
        return VisualShaderNodeDerivativeFunc.OpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpTypeBind, segment))
    }

    fun setFunction(func: VisualShaderNodeDerivativeFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
    }

    fun getFunction(): VisualShaderNodeDerivativeFunc.Function {
        checkOpen()
        return VisualShaderNodeDerivativeFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
    }

    fun setPrecision(precision: VisualShaderNodeDerivativeFunc.Precision) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setPrecisionBind, segment, precision.value)
    }

    fun getPrecision(): VisualShaderNodeDerivativeFunc.Precision {
        checkOpen()
        return VisualShaderNodeDerivativeFunc.Precision(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPrecisionBind, segment))
    }

    @JvmInline
    value class OpType(override val value: Long) : GodotEnumValue {
        companion object {
            val SCALAR: OpType get() = OpType(0L)
            val VECTOR_2D: OpType get() = OpType(1L)
            val VECTOR_3D: OpType get() = OpType(2L)
            val VECTOR_4D: OpType get() = OpType(3L)
            val MAX: OpType get() = OpType(4L)
        }
    }

    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            val SUM: Function get() = Function(0L)
            val X: Function get() = Function(1L)
            val Y: Function get() = Function(2L)
            val MAX: Function get() = Function(3L)
        }
    }

    @JvmInline
    value class Precision(override val value: Long) : GodotEnumValue {
        companion object {
            val NONE: Precision get() = Precision(0L)
            val COARSE: Precision get() = Precision(1L)
            val FINE: Precision get() = Precision(2L)
            val MAX: Precision get() = Precision(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeDerivativeFunc? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeDerivativeFunc? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeDerivativeFunc(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeDerivativeFunc? =
            if (handle.address() == 0L) null else VisualShaderNodeDerivativeFunc(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_OP_TYPE_HASH = 377800221L
        @JvmField
        val setOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeDerivativeFunc", "set_op_type", SET_OP_TYPE_HASH)

        private const val GET_OP_TYPE_HASH = 3997800514L
        @JvmField
        val getOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeDerivativeFunc", "get_op_type", GET_OP_TYPE_HASH)

        private const val SET_FUNCTION_HASH = 1944704156L
        @JvmField
        val setFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeDerivativeFunc", "set_function", SET_FUNCTION_HASH)

        private const val GET_FUNCTION_HASH = 2389093396L
        @JvmField
        val getFunctionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeDerivativeFunc", "get_function", GET_FUNCTION_HASH)

        private const val SET_PRECISION_HASH = 797270566L
        @JvmField
        val setPrecisionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeDerivativeFunc", "set_precision", SET_PRECISION_HASH)

        private const val GET_PRECISION_HASH = 3822547323L
        @JvmField
        val getPrecisionBind =
            ObjectCalls.getMethodBind("VisualShaderNodeDerivativeFunc", "get_precision", GET_PRECISION_HASH)
    }
}
