package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeUIntOp
 */
class VisualShaderNodeUIntOp(handle: GodotHandle) : VisualShaderNode(handle) {
    var operator: VisualShaderNodeUIntOp.Operator
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    fun setOperator(op: VisualShaderNodeUIntOp.Operator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOperatorBind, segment, op.value)
    }

    fun getOperator(): VisualShaderNodeUIntOp.Operator {
        checkOpen()
        return VisualShaderNodeUIntOp.Operator(ObjectCalls.ptrcallNoArgsRetLong(getOperatorBind, segment))
    }

    @JvmInline
    value class Operator(val value: Long) {
        companion object {
            val ADD: Operator get() = Operator(0L)
            val SUB: Operator get() = Operator(1L)
            val MUL: Operator get() = Operator(2L)
            val DIV: Operator get() = Operator(3L)
            val MOD: Operator get() = Operator(4L)
            val MAX: Operator get() = Operator(5L)
            val MIN: Operator get() = Operator(6L)
            val BITWISE_AND: Operator get() = Operator(7L)
            val BITWISE_OR: Operator get() = Operator(8L)
            val BITWISE_XOR: Operator get() = Operator(9L)
            val BITWISE_LEFT_SHIFT: Operator get() = Operator(10L)
            val BITWISE_RIGHT_SHIFT: Operator get() = Operator(11L)
            val ENUM_SIZE: Operator get() = Operator(12L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeUIntOp? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeUIntOp? =
            if (handle.address() == 0L) null else VisualShaderNodeUIntOp(GodotHandle(handle))

        private const val SET_OPERATOR_HASH = 3463048345L
        private val setOperatorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeUIntOp", "set_operator", SET_OPERATOR_HASH)
        }

        private const val GET_OPERATOR_HASH = 256631461L
        private val getOperatorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeUIntOp", "get_operator", GET_OPERATOR_HASH)
        }
    }
}
