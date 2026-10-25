package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Clamps a value within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeClamp
 */
class VisualShaderNodeClamp(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeClamp.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeClamp.set_op_type
     */
    fun setOpType(opType: VisualShaderNodeClamp.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOpTypeBind, segment, opType.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeClamp.get_op_type
     */
    fun getOpType(): VisualShaderNodeClamp.OpType {
        checkOpen()
        val ret = VisualShaderNodeClamp.OpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeClamp.OpType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`VisualShaderNodeClamp.OpType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeClamp.OpType
     */
    @JvmInline
    value class OpType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * A floating-point scalar.
             *
             * Generated from Godot docs: VisualShaderNodeClamp.OP_TYPE_FLOAT
             */
            val FLOAT: OpType get() = OpType(0L)
            /**
             * An integer scalar.
             *
             * Generated from Godot docs: VisualShaderNodeClamp.OP_TYPE_INT
             */
            val INT: OpType get() = OpType(1L)
            /**
             * An unsigned integer scalar.
             *
             * Generated from Godot docs: VisualShaderNodeClamp.OP_TYPE_UINT
             */
            val UINT: OpType get() = OpType(2L)
            /**
             * A 2D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeClamp.OP_TYPE_VECTOR_2D
             */
            val VECTOR_2D: OpType get() = OpType(3L)
            /**
             * A 3D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeClamp.OP_TYPE_VECTOR_3D
             */
            val VECTOR_3D: OpType get() = OpType(4L)
            /**
             * A 4D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeClamp.OP_TYPE_VECTOR_4D
             */
            val VECTOR_4D: OpType get() = OpType(5L)
            /**
             * Represents the size of the `OpType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeClamp.OP_TYPE_MAX
             */
            val MAX: OpType get() = OpType(6L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeClamp? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeClamp? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeClamp(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeClamp? =
            if (handle.address() == 0L) null else VisualShaderNodeClamp(GodotHandle(handle))

        /** A new VisualShaderNodeClamp, GDScript's `VisualShaderNodeClamp.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeClamp =
            RefCounted.owned(VisualShaderNodeClamp(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeClamp"))))
    }

    private object Binds {
        private const val SET_OP_TYPE_HASH = 405010749L
        @JvmField
        val setOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeClamp", "set_op_type", SET_OP_TYPE_HASH)

        private const val GET_OP_TYPE_HASH = 233276050L
        @JvmField
        val getOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeClamp", "get_op_type", GET_OP_TYPE_HASH)
    }
}
