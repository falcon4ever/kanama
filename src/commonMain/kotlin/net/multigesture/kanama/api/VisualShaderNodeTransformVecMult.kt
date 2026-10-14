package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Multiplies a `Transform3D` and a `Vector3` within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeTransformVecMult
 */
class VisualShaderNodeTransformVecMult(handle: GodotHandle) : VisualShaderNode(handle) {
    var operator: VisualShaderNodeTransformVecMult.Operator
        @JvmName("operatorProperty")
        get() = getOperator()
        @JvmName("setOperatorProperty")
        set(value) = setOperator(value)

    /**
     * The multiplication type to be performed.
     *
     * Generated from Godot docs: VisualShaderNodeTransformVecMult.set_operator
     */
    fun setOperator(op: VisualShaderNodeTransformVecMult.Operator) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOperatorBind, segment, op.value)
    }

    /**
     * The multiplication type to be performed.
     *
     * Generated from Godot docs: VisualShaderNodeTransformVecMult.get_operator
     */
    fun getOperator(): VisualShaderNodeTransformVecMult.Operator {
        checkOpen()
        return VisualShaderNodeTransformVecMult.Operator(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOperatorBind, segment))
    }

    /**
     * Godot's `VisualShaderNodeTransformVecMult.Operator` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeTransformVecMult.Operator.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeTransformVecMult.Operator
     */
    @JvmInline
    value class Operator(override val value: Long) : GodotEnumValue {
        companion object {
            val AxB: Operator get() = Operator(0L)
            val BxA: Operator get() = Operator(1L)
            val OP_3x3_AxB: Operator get() = Operator(2L)
            val OP_3x3_BxA: Operator get() = Operator(3L)
            /**
             * Represents the size of the `Operator` enum.
             *
             * Generated from Godot docs: VisualShaderNodeTransformVecMult.OP_MAX
             */
            val MAX: Operator get() = Operator(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformVecMult? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTransformVecMult? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTransformVecMult(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTransformVecMult? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformVecMult(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_OPERATOR_HASH = 1785665912L
        @JvmField
        val setOperatorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformVecMult", "set_operator", SET_OPERATOR_HASH)

        private const val GET_OPERATOR_HASH = 1622088722L
        @JvmField
        val getOperatorBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformVecMult", "get_operator", GET_OPERATOR_HASH)
    }
}
