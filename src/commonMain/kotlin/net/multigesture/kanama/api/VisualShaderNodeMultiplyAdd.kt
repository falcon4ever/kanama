package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Performs a fused multiply-add operation within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeMultiplyAdd
 */
class VisualShaderNodeMultiplyAdd(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeMultiplyAdd.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeMultiplyAdd.set_op_type
     */
    fun setOpType(type: VisualShaderNodeMultiplyAdd.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOpTypeBind, segment, type.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeMultiplyAdd.get_op_type
     */
    fun getOpType(): VisualShaderNodeMultiplyAdd.OpType {
        checkOpen()
        val ret = VisualShaderNodeMultiplyAdd.OpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeMultiplyAdd.OpType` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeMultiplyAdd.OpType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeMultiplyAdd.OpType
     */
    @JvmInline
    value class OpType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * A floating-point scalar type.
             *
             * Generated from Godot docs: VisualShaderNodeMultiplyAdd.OP_TYPE_SCALAR
             */
            val SCALAR: OpType get() = OpType(0L)
            /**
             * A 2D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeMultiplyAdd.OP_TYPE_VECTOR_2D
             */
            val VECTOR_2D: OpType get() = OpType(1L)
            /**
             * A 3D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeMultiplyAdd.OP_TYPE_VECTOR_3D
             */
            val VECTOR_3D: OpType get() = OpType(2L)
            /**
             * A 4D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeMultiplyAdd.OP_TYPE_VECTOR_4D
             */
            val VECTOR_4D: OpType get() = OpType(3L)
            /**
             * Represents the size of the `OpType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeMultiplyAdd.OP_TYPE_MAX
             */
            val MAX: OpType get() = OpType(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeMultiplyAdd? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeMultiplyAdd? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeMultiplyAdd(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeMultiplyAdd? =
            if (handle.address() == 0L) null else VisualShaderNodeMultiplyAdd(GodotHandle(handle))

        /** A new VisualShaderNodeMultiplyAdd, GDScript's `VisualShaderNodeMultiplyAdd.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeMultiplyAdd =
            RefCounted.owned(VisualShaderNodeMultiplyAdd(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeMultiplyAdd"))))
    }

    private object Binds {
        private const val SET_OP_TYPE_HASH = 1409862380L
        @JvmField
        val setOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeMultiplyAdd", "set_op_type", SET_OP_TYPE_HASH)

        private const val GET_OP_TYPE_HASH = 2823201991L
        @JvmField
        val getOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeMultiplyAdd", "get_op_type", GET_OP_TYPE_HASH)
    }
}
