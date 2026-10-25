package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Calculates a Step function within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeStep
 */
class VisualShaderNodeStep(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeStep.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeStep.set_op_type
     */
    fun setOpType(opType: VisualShaderNodeStep.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOpTypeBind, segment, opType.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeStep.get_op_type
     */
    fun getOpType(): VisualShaderNodeStep.OpType {
        checkOpen()
        val ret = VisualShaderNodeStep.OpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeStep.OpType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`VisualShaderNodeStep.OpType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeStep.OpType
     */
    @JvmInline
    value class OpType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * A floating-point scalar type.
             *
             * Generated from Godot docs: VisualShaderNodeStep.OP_TYPE_SCALAR
             */
            val SCALAR: OpType get() = OpType(0L)
            /**
             * A 2D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeStep.OP_TYPE_VECTOR_2D
             */
            val VECTOR_2D: OpType get() = OpType(1L)
            /**
             * The `x` port uses a 2D vector type, while the `edge` port uses a floating-point scalar type.
             *
             * Generated from Godot docs: VisualShaderNodeStep.OP_TYPE_VECTOR_2D_SCALAR
             */
            val VECTOR_2D_SCALAR: OpType get() = OpType(2L)
            /**
             * A 3D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeStep.OP_TYPE_VECTOR_3D
             */
            val VECTOR_3D: OpType get() = OpType(3L)
            /**
             * The `x` port uses a 3D vector type, while the `edge` port uses a floating-point scalar type.
             *
             * Generated from Godot docs: VisualShaderNodeStep.OP_TYPE_VECTOR_3D_SCALAR
             */
            val VECTOR_3D_SCALAR: OpType get() = OpType(4L)
            /**
             * A 4D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeStep.OP_TYPE_VECTOR_4D
             */
            val VECTOR_4D: OpType get() = OpType(5L)
            /**
             * The `a` and `b` ports use a 4D vector type. The `weight` port uses a scalar type.
             *
             * Generated from Godot docs: VisualShaderNodeStep.OP_TYPE_VECTOR_4D_SCALAR
             */
            val VECTOR_4D_SCALAR: OpType get() = OpType(6L)
            /**
             * Represents the size of the `OpType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeStep.OP_TYPE_MAX
             */
            val MAX: OpType get() = OpType(7L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeStep? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeStep? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeStep(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeStep? =
            if (handle.address() == 0L) null else VisualShaderNodeStep(GodotHandle(handle))

        /** A new VisualShaderNodeStep, GDScript's `VisualShaderNodeStep.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeStep =
            RefCounted.owned(VisualShaderNodeStep(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeStep"))))
    }

    private object Binds {
        private const val SET_OP_TYPE_HASH = 715172489L
        @JvmField
        val setOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeStep", "set_op_type", SET_OP_TYPE_HASH)

        private const val GET_OP_TYPE_HASH = 3274022781L
        @JvmField
        val getOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeStep", "get_op_type", GET_OP_TYPE_HASH)
    }
}
