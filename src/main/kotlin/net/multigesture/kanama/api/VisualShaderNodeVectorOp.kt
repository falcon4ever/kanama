package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeVectorOp
 */
class VisualShaderNodeVectorOp(handle: MemorySegment) : VisualShaderNodeVectorBase(handle) {
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
        const val OP_POW: Long = 5L
        const val OP_MAX: Long = 6L
        const val OP_MIN: Long = 7L
        const val OP_CROSS: Long = 8L
        const val OP_ATAN2: Long = 9L
        const val OP_REFLECT: Long = 10L
        const val OP_STEP: Long = 11L
        const val OP_ENUM_SIZE: Long = 12L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeVectorOp? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeVectorOp? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorOp(handle)

        private const val SET_OPERATOR_HASH = 3371507302L
        private val setOperatorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVectorOp", "set_operator", SET_OPERATOR_HASH)
        }

        private const val GET_OPERATOR_HASH = 11793929L
        private val getOperatorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVectorOp", "get_operator", GET_OPERATOR_HASH)
        }
    }
}
