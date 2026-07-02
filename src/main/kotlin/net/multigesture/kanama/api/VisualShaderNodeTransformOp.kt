package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeTransformOp
 */
class VisualShaderNodeTransformOp(handle: MemorySegment) : VisualShaderNode(handle) {
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
        const val OP_AxB: Long = 0L
        const val OP_BxA: Long = 1L
        const val OP_AxB_COMP: Long = 2L
        const val OP_BxA_COMP: Long = 3L
        const val OP_ADD: Long = 4L
        const val OP_A_MINUS_B: Long = 5L
        const val OP_B_MINUS_A: Long = 6L
        const val OP_A_DIV_B: Long = 7L
        const val OP_B_DIV_A: Long = 8L
        const val OP_MAX: Long = 9L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeTransformOp? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTransformOp? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformOp(handle)

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
