package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeUIntOp
 */
class VisualShaderNodeUIntOp(handle: MemorySegment) : VisualShaderNode(handle) {
    var operator: Long
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    fun setOperator(op: Long) {
        ObjectCalls.ptrcallWithLongArg(setOperatorBind, handle, op)
    }

    fun getOperator(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getOperatorBind, handle)
    }

    companion object {
        const val OP_ADD: Long = 0L
        const val OP_SUB: Long = 1L
        const val OP_MUL: Long = 2L
        const val OP_DIV: Long = 3L
        const val OP_MOD: Long = 4L
        const val OP_MAX: Long = 5L
        const val OP_MIN: Long = 6L
        const val OP_BITWISE_AND: Long = 7L
        const val OP_BITWISE_OR: Long = 8L
        const val OP_BITWISE_XOR: Long = 9L
        const val OP_BITWISE_LEFT_SHIFT: Long = 10L
        const val OP_BITWISE_RIGHT_SHIFT: Long = 11L
        const val OP_ENUM_SIZE: Long = 12L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeUIntOp? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeUIntOp? =
            if (handle.address() == 0L) null else VisualShaderNodeUIntOp(handle)

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
