package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An integer scalar operator to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeIntOp
 */
class VisualShaderNodeIntOp(handle: GodotHandle) : VisualShaderNode(handle) {
    var operator: VisualShaderNodeIntOp.Operator
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    /**
     * An operator to be applied to the inputs.
     *
     * Generated from Godot docs: VisualShaderNodeIntOp.set_operator
     */
    fun setOperator(op: VisualShaderNodeIntOp.Operator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOperatorBind, segment, op.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * An operator to be applied to the inputs.
     *
     * Generated from Godot docs: VisualShaderNodeIntOp.get_operator
     */
    fun getOperator(): VisualShaderNodeIntOp.Operator {
        checkOpen()
        val ret = VisualShaderNodeIntOp.Operator(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOperatorBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeIntOp.Operator` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`VisualShaderNodeIntOp.Operator.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeIntOp.Operator
     */
    @JvmInline
    value class Operator(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Sums two numbers using `a + b`.
             *
             * Generated from Godot docs: VisualShaderNodeIntOp.OP_ADD
             */
            val ADD: Operator get() = Operator(0L)
            /**
             * Subtracts two numbers using `a - b`.
             *
             * Generated from Godot docs: VisualShaderNodeIntOp.OP_SUB
             */
            val SUB: Operator get() = Operator(1L)
            /**
             * Multiplies two numbers using `a * b`.
             *
             * Generated from Godot docs: VisualShaderNodeIntOp.OP_MUL
             */
            val MUL: Operator get() = Operator(2L)
            /**
             * Divides two numbers using `a / b`.
             *
             * Generated from Godot docs: VisualShaderNodeIntOp.OP_DIV
             */
            val DIV: Operator get() = Operator(3L)
            /**
             * Calculates the remainder of two numbers using `a % b`.
             *
             * Generated from Godot docs: VisualShaderNodeIntOp.OP_MOD
             */
            val MOD: Operator get() = Operator(4L)
            /**
             * Returns the greater of two numbers. Translates to `max(a, b)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeIntOp.OP_MAX
             */
            val MAX: Operator get() = Operator(5L)
            /**
             * Returns the lesser of two numbers. Translates to `max(a, b)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeIntOp.OP_MIN
             */
            val MIN: Operator get() = Operator(6L)
            /**
             * Returns the result of bitwise `AND` operation on the integer. Translates to `a & b` in the Godot
             * Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeIntOp.OP_BITWISE_AND
             */
            val BITWISE_AND: Operator get() = Operator(7L)
            /**
             * Returns the result of bitwise `OR` operation for two integers. Translates to `a | b` in the
             * Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeIntOp.OP_BITWISE_OR
             */
            val BITWISE_OR: Operator get() = Operator(8L)
            /**
             * Returns the result of bitwise `XOR` operation for two integers. Translates to `a ^ b` in the
             * Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeIntOp.OP_BITWISE_XOR
             */
            val BITWISE_XOR: Operator get() = Operator(9L)
            /**
             * Returns the result of bitwise left shift operation on the integer. Translates to `a << b` in the
             * Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeIntOp.OP_BITWISE_LEFT_SHIFT
             */
            val BITWISE_LEFT_SHIFT: Operator get() = Operator(10L)
            /**
             * Returns the result of bitwise right shift operation on the integer. Translates to `a >> b` in
             * the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeIntOp.OP_BITWISE_RIGHT_SHIFT
             */
            val BITWISE_RIGHT_SHIFT: Operator get() = Operator(11L)
            /**
             * Represents the size of the `Operator` enum.
             *
             * Generated from Godot docs: VisualShaderNodeIntOp.OP_ENUM_SIZE
             */
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
