package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeTransformOp
 */
class VisualShaderNodeTransformOp(handle: GodotHandle) : VisualShaderNode(handle) {
    var operator: VisualShaderNodeTransformOp.Operator
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    fun setOperator(op: VisualShaderNodeTransformOp.Operator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOperatorBind, segment, op.value)
    }

    fun getOperator(): VisualShaderNodeTransformOp.Operator {
        checkOpen()
        return VisualShaderNodeTransformOp.Operator(ObjectCalls.ptrcallNoArgsRetLong(getOperatorBind, segment))
    }

    @JvmInline
    value class Operator(override val value: Long) : GodotEnumValue {
        companion object {
            val AxB: Operator get() = Operator(0L)
            val BxA: Operator get() = Operator(1L)
            val AxB_COMP: Operator get() = Operator(2L)
            val BxA_COMP: Operator get() = Operator(3L)
            val ADD: Operator get() = Operator(4L)
            val A_MINUS_B: Operator get() = Operator(5L)
            val B_MINUS_A: Operator get() = Operator(6L)
            val A_DIV_B: Operator get() = Operator(7L)
            val B_DIV_A: Operator get() = Operator(8L)
            val MAX: Operator get() = Operator(9L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformOp? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTransformOp? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTransformOp(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTransformOp? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformOp(GodotHandle(handle))

        private const val SET_OPERATOR_HASH = 2287310733L
        private val setOperatorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformOp", "set_operator", SET_OPERATOR_HASH)
        }

        private const val GET_OPERATOR_HASH = 1238663601L
        private val getOperatorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformOp", "get_operator", GET_OPERATOR_HASH)
        }
    }
}
