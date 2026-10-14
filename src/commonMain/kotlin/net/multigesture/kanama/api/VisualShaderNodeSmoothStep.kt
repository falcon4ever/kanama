package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Calculates a SmoothStep function within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeSmoothStep
 */
class VisualShaderNodeSmoothStep(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeSmoothStep.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeSmoothStep.set_op_type
     */
    fun setOpType(opType: VisualShaderNodeSmoothStep.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOpTypeBind, segment, opType.value)
    }

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeSmoothStep.get_op_type
     */
    fun getOpType(): VisualShaderNodeSmoothStep.OpType {
        checkOpen()
        return VisualShaderNodeSmoothStep.OpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpTypeBind, segment))
    }

    /**
     * Godot's `VisualShaderNodeSmoothStep.OpType` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeSmoothStep.OpType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeSmoothStep.OpType
     */
    @JvmInline
    value class OpType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * A floating-point scalar type.
             *
             * Generated from Godot docs: VisualShaderNodeSmoothStep.OP_TYPE_SCALAR
             */
            val SCALAR: OpType get() = OpType(0L)
            /**
             * A 2D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeSmoothStep.OP_TYPE_VECTOR_2D
             */
            val VECTOR_2D: OpType get() = OpType(1L)
            /**
             * The `x` port uses a 2D vector type. The first two ports use a floating-point scalar type.
             *
             * Generated from Godot docs: VisualShaderNodeSmoothStep.OP_TYPE_VECTOR_2D_SCALAR
             */
            val VECTOR_2D_SCALAR: OpType get() = OpType(2L)
            /**
             * A 3D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeSmoothStep.OP_TYPE_VECTOR_3D
             */
            val VECTOR_3D: OpType get() = OpType(3L)
            /**
             * The `x` port uses a 3D vector type. The first two ports use a floating-point scalar type.
             *
             * Generated from Godot docs: VisualShaderNodeSmoothStep.OP_TYPE_VECTOR_3D_SCALAR
             */
            val VECTOR_3D_SCALAR: OpType get() = OpType(4L)
            /**
             * A 4D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeSmoothStep.OP_TYPE_VECTOR_4D
             */
            val VECTOR_4D: OpType get() = OpType(5L)
            /**
             * The `a` and `b` ports use a 4D vector type. The `weight` port uses a scalar type.
             *
             * Generated from Godot docs: VisualShaderNodeSmoothStep.OP_TYPE_VECTOR_4D_SCALAR
             */
            val VECTOR_4D_SCALAR: OpType get() = OpType(6L)
            /**
             * Represents the size of the `OpType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeSmoothStep.OP_TYPE_MAX
             */
            val MAX: OpType get() = OpType(7L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeSmoothStep? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeSmoothStep? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeSmoothStep(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeSmoothStep? =
            if (handle.address() == 0L) null else VisualShaderNodeSmoothStep(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_OP_TYPE_HASH = 2427426148L
        @JvmField
        val setOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeSmoothStep", "set_op_type", SET_OP_TYPE_HASH)

        private const val GET_OP_TYPE_HASH = 359640855L
        @JvmField
        val getOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeSmoothStep", "get_op_type", GET_OP_TYPE_HASH)
    }
}
