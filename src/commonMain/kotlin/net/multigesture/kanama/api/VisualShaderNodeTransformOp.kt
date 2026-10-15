package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A `Transform3D` operator to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeTransformOp
 */
class VisualShaderNodeTransformOp(handle: GodotHandle) : VisualShaderNode(handle) {
    var operator: VisualShaderNodeTransformOp.Operator
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    /**
     * The type of the operation to be performed on the transforms.
     *
     * Generated from Godot docs: VisualShaderNodeTransformOp.set_operator
     */
    fun setOperator(op: VisualShaderNodeTransformOp.Operator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOperatorBind, segment, op.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The type of the operation to be performed on the transforms.
     *
     * Generated from Godot docs: VisualShaderNodeTransformOp.get_operator
     */
    fun getOperator(): VisualShaderNodeTransformOp.Operator {
        checkOpen()
        val ret = VisualShaderNodeTransformOp.Operator(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOperatorBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeTransformOp.Operator` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeTransformOp.Operator.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeTransformOp.Operator
     */
    @JvmInline
    value class Operator(override val value: Long) : GodotEnumValue {
        companion object {
            val AxB: Operator get() = Operator(0L)
            val BxA: Operator get() = Operator(1L)
            val AxB_COMP: Operator get() = Operator(2L)
            val BxA_COMP: Operator get() = Operator(3L)
            /**
             * Adds two transforms.
             *
             * Generated from Godot docs: VisualShaderNodeTransformOp.OP_ADD
             */
            val ADD: Operator get() = Operator(4L)
            /**
             * Subtracts the transform `a` from the transform `b`.
             *
             * Generated from Godot docs: VisualShaderNodeTransformOp.OP_A_MINUS_B
             */
            val A_MINUS_B: Operator get() = Operator(5L)
            /**
             * Subtracts the transform `b` from the transform `a`.
             *
             * Generated from Godot docs: VisualShaderNodeTransformOp.OP_B_MINUS_A
             */
            val B_MINUS_A: Operator get() = Operator(6L)
            /**
             * Divides the transform `a` by the transform `b`.
             *
             * Generated from Godot docs: VisualShaderNodeTransformOp.OP_A_DIV_B
             */
            val A_DIV_B: Operator get() = Operator(7L)
            /**
             * Divides the transform `b` by the transform `a`.
             *
             * Generated from Godot docs: VisualShaderNodeTransformOp.OP_B_DIV_A
             */
            val B_DIV_A: Operator get() = Operator(8L)
            /**
             * Represents the size of the `Operator` enum.
             *
             * Generated from Godot docs: VisualShaderNodeTransformOp.OP_MAX
             */
            val MAX: Operator get() = Operator(9L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformOp? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTransformOp? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTransformOp(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTransformOp? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformOp(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_OPERATOR_HASH = 2287310733L
        @JvmField
        val setOperatorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformOp", "set_operator", SET_OPERATOR_HASH)

        private const val GET_OPERATOR_HASH = 1238663601L
        @JvmField
        val getOperatorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformOp", "get_operator", GET_OPERATOR_HASH)
    }
}
