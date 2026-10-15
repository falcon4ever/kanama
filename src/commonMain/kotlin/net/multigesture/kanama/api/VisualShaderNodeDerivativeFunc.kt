package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Calculates a derivative within the visual shader graph.
 *
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

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeDerivativeFunc.set_op_type
     */
    fun setOpType(type: VisualShaderNodeDerivativeFunc.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOpTypeBind, segment, type.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeDerivativeFunc.get_op_type
     */
    fun getOpType(): VisualShaderNodeDerivativeFunc.OpType {
        checkOpen()
        val ret = VisualShaderNodeDerivativeFunc.OpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * A derivative function type.
     *
     * Generated from Godot docs: VisualShaderNodeDerivativeFunc.set_function
     */
    fun setFunction(func: VisualShaderNodeDerivativeFunc.Function) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFunctionBind, segment, func.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A derivative function type.
     *
     * Generated from Godot docs: VisualShaderNodeDerivativeFunc.get_function
     */
    fun getFunction(): VisualShaderNodeDerivativeFunc.Function {
        checkOpen()
        val ret = VisualShaderNodeDerivativeFunc.Function(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFunctionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Sets the level of precision to use for the derivative function. When using the Compatibility
     * renderer, this setting has no effect.
     *
     * Generated from Godot docs: VisualShaderNodeDerivativeFunc.set_precision
     */
    fun setPrecision(precision: VisualShaderNodeDerivativeFunc.Precision) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setPrecisionBind, segment, precision.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Sets the level of precision to use for the derivative function. When using the Compatibility
     * renderer, this setting has no effect.
     *
     * Generated from Godot docs: VisualShaderNodeDerivativeFunc.get_precision
     */
    fun getPrecision(): VisualShaderNodeDerivativeFunc.Precision {
        checkOpen()
        val ret = VisualShaderNodeDerivativeFunc.Precision(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPrecisionBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeDerivativeFunc.OpType` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeDerivativeFunc.OpType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeDerivativeFunc.OpType
     */
    @JvmInline
    value class OpType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * A floating-point scalar.
             *
             * Generated from Godot docs: VisualShaderNodeDerivativeFunc.OP_TYPE_SCALAR
             */
            val SCALAR: OpType get() = OpType(0L)
            /**
             * A 2D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeDerivativeFunc.OP_TYPE_VECTOR_2D
             */
            val VECTOR_2D: OpType get() = OpType(1L)
            /**
             * A 3D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeDerivativeFunc.OP_TYPE_VECTOR_3D
             */
            val VECTOR_3D: OpType get() = OpType(2L)
            /**
             * A 4D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeDerivativeFunc.OP_TYPE_VECTOR_4D
             */
            val VECTOR_4D: OpType get() = OpType(3L)
            /**
             * Represents the size of the `OpType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeDerivativeFunc.OP_TYPE_MAX
             */
            val MAX: OpType get() = OpType(4L)
        }
    }

    /**
     * Godot's `VisualShaderNodeDerivativeFunc.Function` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeDerivativeFunc.Function.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeDerivativeFunc.Function
     */
    @JvmInline
    value class Function(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Sum of absolute derivative in `x` and `y`.
             *
             * Generated from Godot docs: VisualShaderNodeDerivativeFunc.FUNC_SUM
             */
            val SUM: Function get() = Function(0L)
            /**
             * Derivative in `x` using local differencing.
             *
             * Generated from Godot docs: VisualShaderNodeDerivativeFunc.FUNC_X
             */
            val X: Function get() = Function(1L)
            /**
             * Derivative in `y` using local differencing.
             *
             * Generated from Godot docs: VisualShaderNodeDerivativeFunc.FUNC_Y
             */
            val Y: Function get() = Function(2L)
            /**
             * Represents the size of the `Function` enum.
             *
             * Generated from Godot docs: VisualShaderNodeDerivativeFunc.FUNC_MAX
             */
            val MAX: Function get() = Function(3L)
        }
    }

    /**
     * Godot's `VisualShaderNodeDerivativeFunc.Precision` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeDerivativeFunc.Precision.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeDerivativeFunc.Precision
     */
    @JvmInline
    value class Precision(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No precision is specified, the GPU driver is allowed to use whatever level of precision it
             * chooses. This is the default option and is equivalent to using `dFdx()` or `dFdy()` in text
             * shaders.
             *
             * Generated from Godot docs: VisualShaderNodeDerivativeFunc.PRECISION_NONE
             */
            val NONE: Precision get() = Precision(0L)
            /**
             * The derivative will be calculated using the current fragment's neighbors (which may not include
             * the current fragment). This tends to be faster than using `Precision.FINE`, but may not be
             * suitable when more precision is needed. This is equivalent to using `dFdxCoarse()` or
             * `dFdyCoarse()` in text shaders.
             *
             * Generated from Godot docs: VisualShaderNodeDerivativeFunc.PRECISION_COARSE
             */
            val COARSE: Precision get() = Precision(1L)
            /**
             * The derivative will be calculated using the current fragment and its immediate neighbors. This
             * tends to be slower than using `Precision.COARSE`, but may be necessary when more precision is
             * needed. This is equivalent to using `dFdxFine()` or `dFdyFine()` in text shaders.
             *
             * Generated from Godot docs: VisualShaderNodeDerivativeFunc.PRECISION_FINE
             */
            val FINE: Precision get() = Precision(2L)
            /**
             * Represents the size of the `Precision` enum.
             *
             * Generated from Godot docs: VisualShaderNodeDerivativeFunc.PRECISION_MAX
             */
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
