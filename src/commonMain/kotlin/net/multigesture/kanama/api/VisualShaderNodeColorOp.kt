package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeColorOp
 */
class VisualShaderNodeColorOp(handle: GodotHandle) : VisualShaderNode(handle) {
    var operator: VisualShaderNodeColorOp.Operator
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    fun setOperator(op: VisualShaderNodeColorOp.Operator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOperatorBind, segment, op.value)
    }

    fun getOperator(): VisualShaderNodeColorOp.Operator {
        checkOpen()
        return VisualShaderNodeColorOp.Operator(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOperatorBind, segment))
    }

    @JvmInline
    value class Operator(override val value: Long) : GodotEnumValue {
        companion object {
            val SCREEN: Operator get() = Operator(0L)
            val DIFFERENCE: Operator get() = Operator(1L)
            val DARKEN: Operator get() = Operator(2L)
            val LIGHTEN: Operator get() = Operator(3L)
            val OVERLAY: Operator get() = Operator(4L)
            val DODGE: Operator get() = Operator(5L)
            val BURN: Operator get() = Operator(6L)
            val SOFT_LIGHT: Operator get() = Operator(7L)
            val HARD_LIGHT: Operator get() = Operator(8L)
            val MAX: Operator get() = Operator(9L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeColorOp? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeColorOp? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeColorOp(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeColorOp? =
            if (handle.address() == 0L) null else VisualShaderNodeColorOp(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_OPERATOR_HASH = 4260370673L
        @JvmField
        val setOperatorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeColorOp", "set_operator", SET_OPERATOR_HASH)

        private const val GET_OPERATOR_HASH = 1950956529L
        @JvmField
        val getOperatorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeColorOp", "get_operator", GET_OPERATOR_HASH)
    }
}
