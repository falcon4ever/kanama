package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A floating-point scalar operator to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeFloatOp
 */
class VisualShaderNodeFloatOp(handle: GodotHandle) : VisualShaderNode(handle) {
    var operator: VisualShaderNodeFloatOp.Operator
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    /**
     * An operator to be applied to the inputs.
     *
     * Generated from Godot docs: VisualShaderNodeFloatOp.set_operator
     */
    fun setOperator(op: VisualShaderNodeFloatOp.Operator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOperatorBind, segment, op.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * An operator to be applied to the inputs.
     *
     * Generated from Godot docs: VisualShaderNodeFloatOp.get_operator
     */
    fun getOperator(): VisualShaderNodeFloatOp.Operator {
        checkOpen()
        val ret = VisualShaderNodeFloatOp.Operator(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOperatorBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeFloatOp.Operator` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeFloatOp.Operator.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeFloatOp.Operator
     */
    @JvmInline
    value class Operator(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Sums two numbers using `a + b`.
             *
             * Generated from Godot docs: VisualShaderNodeFloatOp.OP_ADD
             */
            val ADD: Operator get() = Operator(0L)
            /**
             * Subtracts two numbers using `a - b`.
             *
             * Generated from Godot docs: VisualShaderNodeFloatOp.OP_SUB
             */
            val SUB: Operator get() = Operator(1L)
            /**
             * Multiplies two numbers using `a * b`.
             *
             * Generated from Godot docs: VisualShaderNodeFloatOp.OP_MUL
             */
            val MUL: Operator get() = Operator(2L)
            /**
             * Divides two numbers using `a / b`.
             *
             * Generated from Godot docs: VisualShaderNodeFloatOp.OP_DIV
             */
            val DIV: Operator get() = Operator(3L)
            /**
             * Calculates the remainder of two numbers. Translates to `mod(a, b)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatOp.OP_MOD
             */
            val MOD: Operator get() = Operator(4L)
            /**
             * Raises the `a` to the power of `b`. Translates to `pow(a, b)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatOp.OP_POW
             */
            val POW: Operator get() = Operator(5L)
            /**
             * Returns the greater of two numbers. Translates to `max(a, b)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatOp.OP_MAX
             */
            val MAX: Operator get() = Operator(6L)
            /**
             * Returns the lesser of two numbers. Translates to `min(a, b)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatOp.OP_MIN
             */
            val MIN: Operator get() = Operator(7L)
            /**
             * Returns the arc-tangent of the parameters. Translates to `atan(a, b)` in the Godot Shader
             * Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatOp.OP_ATAN2
             */
            val ATAN2: Operator get() = Operator(8L)
            /**
             * Generates a step function by comparing `b`(x) to `a`(edge). Returns 0.0 if `x` is smaller than
             * `edge` and otherwise 1.0. Translates to `step(a, b)` in the Godot Shader Language.
             *
             * Generated from Godot docs: VisualShaderNodeFloatOp.OP_STEP
             */
            val STEP: Operator get() = Operator(9L)
            /**
             * Represents the size of the `Operator` enum.
             *
             * Generated from Godot docs: VisualShaderNodeFloatOp.OP_ENUM_SIZE
             */
            val ENUM_SIZE: Operator get() = Operator(10L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeFloatOp? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeFloatOp? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeFloatOp(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeFloatOp? =
            if (handle.address() == 0L) null else VisualShaderNodeFloatOp(GodotHandle(handle))

        /** A new VisualShaderNodeFloatOp, GDScript's `VisualShaderNodeFloatOp.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeFloatOp =
            RefCounted.owned(VisualShaderNodeFloatOp(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeFloatOp"), "VisualShaderNodeFloatOp"))))
    }

    private object Binds {
        private const val SET_OPERATOR_HASH = 2488468047L
        @JvmField
        val setOperatorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatOp", "set_operator", SET_OPERATOR_HASH)

        private const val GET_OPERATOR_HASH = 1867979390L
        @JvmField
        val getOperatorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatOp", "get_operator", GET_OPERATOR_HASH)
    }
}
