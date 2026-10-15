package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A `Color` operator to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeColorOp
 */
class VisualShaderNodeColorOp(handle: GodotHandle) : VisualShaderNode(handle) {
    var operator: VisualShaderNodeColorOp.Operator
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    /**
     * An operator to be applied to the inputs.
     *
     * Generated from Godot docs: VisualShaderNodeColorOp.set_operator
     */
    fun setOperator(op: VisualShaderNodeColorOp.Operator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOperatorBind, segment, op.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * An operator to be applied to the inputs.
     *
     * Generated from Godot docs: VisualShaderNodeColorOp.get_operator
     */
    fun getOperator(): VisualShaderNodeColorOp.Operator {
        checkOpen()
        val ret = VisualShaderNodeColorOp.Operator(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOperatorBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeColorOp.Operator` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeColorOp.Operator.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeColorOp.Operator
     */
    @JvmInline
    value class Operator(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Produce a screen effect with the following formula.
             *
             * Generated from Godot docs: VisualShaderNodeColorOp.OP_SCREEN
             */
            val SCREEN: Operator get() = Operator(0L)
            /**
             * Produce a difference effect with the following formula.
             *
             * Generated from Godot docs: VisualShaderNodeColorOp.OP_DIFFERENCE
             */
            val DIFFERENCE: Operator get() = Operator(1L)
            /**
             * Produce a darken effect with the following formula.
             *
             * Generated from Godot docs: VisualShaderNodeColorOp.OP_DARKEN
             */
            val DARKEN: Operator get() = Operator(2L)
            /**
             * Produce a lighten effect with the following formula.
             *
             * Generated from Godot docs: VisualShaderNodeColorOp.OP_LIGHTEN
             */
            val LIGHTEN: Operator get() = Operator(3L)
            /**
             * Produce an overlay effect with the following formula.
             *
             * Generated from Godot docs: VisualShaderNodeColorOp.OP_OVERLAY
             */
            val OVERLAY: Operator get() = Operator(4L)
            /**
             * Produce a dodge effect with the following formula.
             *
             * Generated from Godot docs: VisualShaderNodeColorOp.OP_DODGE
             */
            val DODGE: Operator get() = Operator(5L)
            /**
             * Produce a burn effect with the following formula.
             *
             * Generated from Godot docs: VisualShaderNodeColorOp.OP_BURN
             */
            val BURN: Operator get() = Operator(6L)
            /**
             * Produce a soft light effect with the following formula.
             *
             * Generated from Godot docs: VisualShaderNodeColorOp.OP_SOFT_LIGHT
             */
            val SOFT_LIGHT: Operator get() = Operator(7L)
            /**
             * Produce a hard light effect with the following formula.
             *
             * Generated from Godot docs: VisualShaderNodeColorOp.OP_HARD_LIGHT
             */
            val HARD_LIGHT: Operator get() = Operator(8L)
            /**
             * Represents the size of the `Operator` enum.
             *
             * Generated from Godot docs: VisualShaderNodeColorOp.OP_MAX
             */
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
