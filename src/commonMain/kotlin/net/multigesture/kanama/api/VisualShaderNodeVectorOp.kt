package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A vector operator to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeVectorOp
 */
class VisualShaderNodeVectorOp(handle: GodotHandle) : VisualShaderNodeVectorBase(handle) {
    var operator: VisualShaderNodeVectorOp.Operator
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    /**
     * The operator to be used.
     *
     * Generated from Godot docs: VisualShaderNodeVectorOp.set_operator
     */
    fun setOperator(op: VisualShaderNodeVectorOp.Operator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOperatorBind, segment, op.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The operator to be used.
     *
     * Generated from Godot docs: VisualShaderNodeVectorOp.get_operator
     */
    fun getOperator(): VisualShaderNodeVectorOp.Operator {
        checkOpen()
        val ret = VisualShaderNodeVectorOp.Operator(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOperatorBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeVectorOp.Operator` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeVectorOp.Operator.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeVectorOp.Operator
     */
    @JvmInline
    value class Operator(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Adds two vectors.
             *
             * Generated from Godot docs: VisualShaderNodeVectorOp.OP_ADD
             */
            val ADD: Operator get() = Operator(0L)
            /**
             * Subtracts a vector from a vector.
             *
             * Generated from Godot docs: VisualShaderNodeVectorOp.OP_SUB
             */
            val SUB: Operator get() = Operator(1L)
            /**
             * Multiplies two vectors.
             *
             * Generated from Godot docs: VisualShaderNodeVectorOp.OP_MUL
             */
            val MUL: Operator get() = Operator(2L)
            /**
             * Divides vector by vector.
             *
             * Generated from Godot docs: VisualShaderNodeVectorOp.OP_DIV
             */
            val DIV: Operator get() = Operator(3L)
            /**
             * Returns the remainder of the two vectors.
             *
             * Generated from Godot docs: VisualShaderNodeVectorOp.OP_MOD
             */
            val MOD: Operator get() = Operator(4L)
            /**
             * Returns the value of the first parameter raised to the power of the second, for each component
             * of the vectors.
             *
             * Generated from Godot docs: VisualShaderNodeVectorOp.OP_POW
             */
            val POW: Operator get() = Operator(5L)
            /**
             * Returns the greater of two values, for each component of the vectors.
             *
             * Generated from Godot docs: VisualShaderNodeVectorOp.OP_MAX
             */
            val MAX: Operator get() = Operator(6L)
            /**
             * Returns the lesser of two values, for each component of the vectors.
             *
             * Generated from Godot docs: VisualShaderNodeVectorOp.OP_MIN
             */
            val MIN: Operator get() = Operator(7L)
            /**
             * Calculates the cross product of two vectors.
             *
             * Generated from Godot docs: VisualShaderNodeVectorOp.OP_CROSS
             */
            val CROSS: Operator get() = Operator(8L)
            /**
             * Returns the arc-tangent of the parameters.
             *
             * Generated from Godot docs: VisualShaderNodeVectorOp.OP_ATAN2
             */
            val ATAN2: Operator get() = Operator(9L)
            /**
             * Returns the vector that points in the direction of reflection. `a` is incident vector and `b` is
             * the normal vector.
             *
             * Generated from Godot docs: VisualShaderNodeVectorOp.OP_REFLECT
             */
            val REFLECT: Operator get() = Operator(10L)
            /**
             * Vector step operator. Returns `0.0` if `a` is smaller than `b` and `1.0` otherwise.
             *
             * Generated from Godot docs: VisualShaderNodeVectorOp.OP_STEP
             */
            val STEP: Operator get() = Operator(11L)
            /**
             * Represents the size of the `Operator` enum.
             *
             * Generated from Godot docs: VisualShaderNodeVectorOp.OP_ENUM_SIZE
             */
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

        /** A new VisualShaderNodeVectorOp, GDScript's `VisualShaderNodeVectorOp.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeVectorOp =
            RefCounted.owned(VisualShaderNodeVectorOp(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeVectorOp"), "VisualShaderNodeVectorOp"))))
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
