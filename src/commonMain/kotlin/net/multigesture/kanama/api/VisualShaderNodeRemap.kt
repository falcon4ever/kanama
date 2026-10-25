package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A visual shader node for remap function.
 *
 * Generated from Godot docs: VisualShaderNodeRemap
 */
class VisualShaderNodeRemap(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeRemap.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    fun setOpType(opType: VisualShaderNodeRemap.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOpTypeBind, segment, opType.value)
        ObjectCalls.keepReachable(this)
    }

    fun getOpType(): VisualShaderNodeRemap.OpType {
        checkOpen()
        val ret = VisualShaderNodeRemap.OpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeRemap.OpType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`VisualShaderNodeRemap.OpType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeRemap.OpType
     */
    @JvmInline
    value class OpType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * A floating-point scalar type.
             *
             * Generated from Godot docs: VisualShaderNodeRemap.OP_TYPE_SCALAR
             */
            val SCALAR: OpType get() = OpType(0L)
            /**
             * A 2D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeRemap.OP_TYPE_VECTOR_2D
             */
            val VECTOR_2D: OpType get() = OpType(1L)
            /**
             * The `value` port uses a 2D vector type, while the `input min`, `input max`, `output min`, and
             * `output max` ports use a floating-point scalar type.
             *
             * Generated from Godot docs: VisualShaderNodeRemap.OP_TYPE_VECTOR_2D_SCALAR
             */
            val VECTOR_2D_SCALAR: OpType get() = OpType(2L)
            /**
             * A 3D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeRemap.OP_TYPE_VECTOR_3D
             */
            val VECTOR_3D: OpType get() = OpType(3L)
            /**
             * The `value` port uses a 3D vector type, while the `input min`, `input max`, `output min`, and
             * `output max` ports use a floating-point scalar type.
             *
             * Generated from Godot docs: VisualShaderNodeRemap.OP_TYPE_VECTOR_3D_SCALAR
             */
            val VECTOR_3D_SCALAR: OpType get() = OpType(4L)
            /**
             * A 4D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeRemap.OP_TYPE_VECTOR_4D
             */
            val VECTOR_4D: OpType get() = OpType(5L)
            /**
             * The `value` port uses a 4D vector type, while the `input min`, `input max`, `output min`, and
             * `output max` ports use a floating-point scalar type.
             *
             * Generated from Godot docs: VisualShaderNodeRemap.OP_TYPE_VECTOR_4D_SCALAR
             */
            val VECTOR_4D_SCALAR: OpType get() = OpType(6L)
            /**
             * Represents the size of the `OpType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeRemap.OP_TYPE_MAX
             */
            val MAX: OpType get() = OpType(7L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeRemap? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeRemap? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeRemap(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeRemap? =
            if (handle.address() == 0L) null else VisualShaderNodeRemap(GodotHandle(handle))

        /** A new VisualShaderNodeRemap, GDScript's `VisualShaderNodeRemap.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeRemap =
            RefCounted.owned(VisualShaderNodeRemap(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeRemap"))))
    }

    private object Binds {
        private const val SET_OP_TYPE_HASH = 1703697889L
        @JvmField
        val setOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeRemap", "set_op_type", SET_OP_TYPE_HASH)

        private const val GET_OP_TYPE_HASH = 1678380563L
        @JvmField
        val getOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeRemap", "get_op_type", GET_OP_TYPE_HASH)
    }
}
