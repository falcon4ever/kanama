package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTransformVecMult
 */
class VisualShaderNodeTransformVecMult(handle: GodotHandle) : VisualShaderNode(handle) {
    var operator: VisualShaderNodeTransformVecMult.Operator
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    fun setOperator(op: VisualShaderNodeTransformVecMult.Operator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOperatorBind, segment, op.value)
    }

    fun getOperator(): VisualShaderNodeTransformVecMult.Operator {
        checkOpen()
        return VisualShaderNodeTransformVecMult.Operator(ObjectCalls.ptrcallNoArgsRetLong(getOperatorBind, segment))
    }

    @JvmInline
    value class Operator(override val value: Long) : GodotEnumValue {
        companion object {
            val AxB: Operator get() = Operator(0L)
            val BxA: Operator get() = Operator(1L)
            val OP_3x3_AxB: Operator get() = Operator(2L)
            val OP_3x3_BxA: Operator get() = Operator(3L)
            val MAX: Operator get() = Operator(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformVecMult? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTransformVecMult? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTransformVecMult(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTransformVecMult? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformVecMult(GodotHandle(handle))

        private const val SET_OPERATOR_HASH = 1785665912L
        private val setOperatorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformVecMult", "set_operator", SET_OPERATOR_HASH)
        }

        private const val GET_OPERATOR_HASH = 1622088722L
        private val getOperatorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformVecMult", "get_operator", GET_OPERATOR_HASH)
        }
    }
}
