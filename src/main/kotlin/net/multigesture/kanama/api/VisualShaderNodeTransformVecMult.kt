package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeTransformVecMult
 */
class VisualShaderNodeTransformVecMult(handle: MemorySegment) : VisualShaderNode(handle) {
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
        const val OP_3x3_AxB: Long = 2L
        const val OP_3x3_BxA: Long = 3L
        const val OP_MAX: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeTransformVecMult? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTransformVecMult? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformVecMult(handle)

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
