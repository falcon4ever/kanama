package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A selector function for use within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeSwitch
 */
class VisualShaderNodeSwitch(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeSwitch.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeSwitch.set_op_type
     */
    fun setOpType(type: VisualShaderNodeSwitch.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOpTypeBind, segment, type.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeSwitch.get_op_type
     */
    fun getOpType(): VisualShaderNodeSwitch.OpType {
        checkOpen()
        val ret = VisualShaderNodeSwitch.OpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeSwitch.OpType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`VisualShaderNodeSwitch.OpType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeSwitch.OpType
     */
    @JvmInline
    value class OpType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * A floating-point scalar.
             *
             * Generated from Godot docs: VisualShaderNodeSwitch.OP_TYPE_FLOAT
             */
            val FLOAT: OpType get() = OpType(0L)
            /**
             * An integer scalar.
             *
             * Generated from Godot docs: VisualShaderNodeSwitch.OP_TYPE_INT
             */
            val INT: OpType get() = OpType(1L)
            /**
             * An unsigned integer scalar.
             *
             * Generated from Godot docs: VisualShaderNodeSwitch.OP_TYPE_UINT
             */
            val UINT: OpType get() = OpType(2L)
            /**
             * A 2D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeSwitch.OP_TYPE_VECTOR_2D
             */
            val VECTOR_2D: OpType get() = OpType(3L)
            /**
             * A 3D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeSwitch.OP_TYPE_VECTOR_3D
             */
            val VECTOR_3D: OpType get() = OpType(4L)
            /**
             * A 4D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeSwitch.OP_TYPE_VECTOR_4D
             */
            val VECTOR_4D: OpType get() = OpType(5L)
            /**
             * A boolean type.
             *
             * Generated from Godot docs: VisualShaderNodeSwitch.OP_TYPE_BOOLEAN
             */
            val BOOLEAN: OpType get() = OpType(6L)
            /**
             * A transform type.
             *
             * Generated from Godot docs: VisualShaderNodeSwitch.OP_TYPE_TRANSFORM
             */
            val TRANSFORM: OpType get() = OpType(7L)
            /**
             * Represents the size of the `OpType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeSwitch.OP_TYPE_MAX
             */
            val MAX: OpType get() = OpType(8L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeSwitch? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeSwitch? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeSwitch(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeSwitch? =
            if (handle.address() == 0L) null else VisualShaderNodeSwitch(GodotHandle(handle))

        /** A new VisualShaderNodeSwitch, GDScript's `VisualShaderNodeSwitch.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeSwitch =
            RefCounted.owned(VisualShaderNodeSwitch(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeSwitch"))))
    }

    private object Binds {
        private const val SET_OP_TYPE_HASH = 510471861L
        @JvmField
        val setOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeSwitch", "set_op_type", SET_OP_TYPE_HASH)

        private const val GET_OP_TYPE_HASH = 2517845071L
        @JvmField
        val getOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeSwitch", "get_op_type", GET_OP_TYPE_HASH)
    }
}
