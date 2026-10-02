package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeFloatOp
 */
class VisualShaderNodeFloatOp(handle: GodotHandle) : VisualShaderNode(handle) {
    var operator: VisualShaderNodeFloatOp.Operator
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    fun setOperator(op: VisualShaderNodeFloatOp.Operator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOperatorBind, segment, op.value)
    }

    fun getOperator(): VisualShaderNodeFloatOp.Operator {
        checkOpen()
        return VisualShaderNodeFloatOp.Operator(ObjectCalls.ptrcallNoArgsRetLong(getOperatorBind, segment))
    }

    @JvmInline
    value class Operator(val value: Long) {
        companion object {
            val ADD: Operator get() = Operator(0L)
            val SUB: Operator get() = Operator(1L)
            val MUL: Operator get() = Operator(2L)
            val DIV: Operator get() = Operator(3L)
            val MOD: Operator get() = Operator(4L)
            val POW: Operator get() = Operator(5L)
            val MAX: Operator get() = Operator(6L)
            val MIN: Operator get() = Operator(7L)
            val ATAN2: Operator get() = Operator(8L)
            val STEP: Operator get() = Operator(9L)
            val ENUM_SIZE: Operator get() = Operator(10L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeFloatOp? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeFloatOp? =
            if (handle.address() == 0L) null else VisualShaderNodeFloatOp(GodotHandle(handle))

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
