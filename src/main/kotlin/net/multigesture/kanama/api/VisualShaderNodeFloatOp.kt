package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeFloatOp
 */
class VisualShaderNodeFloatOp(handle: MemorySegment) : VisualShaderNode(handle) {
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
        const val OP_ATAN2: Long = 8L
        const val OP_STEP: Long = 9L
        const val OP_ENUM_SIZE: Long = 10L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeFloatOp? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeFloatOp? =
            if (handle.address() == 0L) null else VisualShaderNodeFloatOp(handle)

        private const val SET_OPERATOR_HASH = 2488468047L
        private val setOperatorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatOp", "set_operator", SET_OPERATOR_HASH)
        }

        private const val GET_OPERATOR_HASH = 1867979390L
        private val getOperatorBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatOp", "get_operator", GET_OPERATOR_HASH)
        }
    }
}
