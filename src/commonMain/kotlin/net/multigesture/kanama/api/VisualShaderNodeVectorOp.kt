package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeVectorOp
 */
class VisualShaderNodeVectorOp(handle: GodotHandle) : VisualShaderNodeVectorBase(handle) {
    var operator: VisualShaderNodeVectorOp.Operator
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    fun setOperator(op: VisualShaderNodeVectorOp.Operator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOperatorBind, segment, op.value)
    }

    fun getOperator(): VisualShaderNodeVectorOp.Operator {
        checkOpen()
        return VisualShaderNodeVectorOp.Operator(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOperatorBind, segment))
    }

    @JvmInline
    value class Operator(override val value: Long) : GodotEnumValue {
        companion object {
            val ADD: Operator get() = Operator(0L)
            val SUB: Operator get() = Operator(1L)
            val MUL: Operator get() = Operator(2L)
            val DIV: Operator get() = Operator(3L)
            val MOD: Operator get() = Operator(4L)
            val POW: Operator get() = Operator(5L)
            val MAX: Operator get() = Operator(6L)
            val MIN: Operator get() = Operator(7L)
            val CROSS: Operator get() = Operator(8L)
            val ATAN2: Operator get() = Operator(9L)
            val REFLECT: Operator get() = Operator(10L)
            val STEP: Operator get() = Operator(11L)
            val ENUM_SIZE: Operator get() = Operator(12L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVectorOp? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVectorOp? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVectorOp(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVectorOp? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorOp(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_OPERATOR_HASH = 3371507302L
        @JvmField
        val setOperatorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVectorOp", "set_operator", SET_OPERATOR_HASH)

        private const val GET_OPERATOR_HASH = 11793929L
        @JvmField
        val getOperatorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVectorOp", "get_operator", GET_OPERATOR_HASH)
    }
}
