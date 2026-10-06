package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeIntOp
 */
class VisualShaderNodeIntOp(handle: GodotHandle) : VisualShaderNode(handle) {
    var operator: VisualShaderNodeIntOp.Operator
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    fun setOperator(op: VisualShaderNodeIntOp.Operator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOperatorBind, segment, op.value)
    }

    fun getOperator(): VisualShaderNodeIntOp.Operator {
        checkOpen()
        return VisualShaderNodeIntOp.Operator(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOperatorBind, segment))
    }

    @JvmInline
    value class Operator(override val value: Long) : GodotEnumValue {
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
        fun fromHandle(handle: GodotHandle): VisualShaderNodeIntOp? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeIntOp? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeIntOp(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeIntOp? =
            if (handle.address() == 0L) null else VisualShaderNodeIntOp(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_OPERATOR_HASH = 1677909323L
        @JvmField
        val setOperatorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntOp", "set_operator", SET_OPERATOR_HASH)

        private const val GET_OPERATOR_HASH = 1236987913L
        @JvmField
        val getOperatorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntOp", "get_operator", GET_OPERATOR_HASH)
    }
}
