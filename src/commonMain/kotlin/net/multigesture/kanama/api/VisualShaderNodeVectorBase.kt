package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A base type for the nodes that perform vector operations within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeVectorBase
 */
open class VisualShaderNodeVectorBase(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeVectorBase.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    /**
     * A vector type that this operation is performed on.
     *
     * Generated from Godot docs: VisualShaderNodeVectorBase.set_op_type
     */
    fun setOpType(type: VisualShaderNodeVectorBase.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOpTypeBind, segment, type.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A vector type that this operation is performed on.
     *
     * Generated from Godot docs: VisualShaderNodeVectorBase.get_op_type
     */
    fun getOpType(): VisualShaderNodeVectorBase.OpType {
        checkOpen()
        val ret = VisualShaderNodeVectorBase.OpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeVectorBase.OpType` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeVectorBase.OpType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeVectorBase.OpType
     */
    @JvmInline
    value class OpType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * A 2D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeVectorBase.OP_TYPE_VECTOR_2D
             */
            val VECTOR_2D: OpType get() = OpType(0L)
            /**
             * A 3D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeVectorBase.OP_TYPE_VECTOR_3D
             */
            val VECTOR_3D: OpType get() = OpType(1L)
            /**
             * A 4D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeVectorBase.OP_TYPE_VECTOR_4D
             */
            val VECTOR_4D: OpType get() = OpType(2L)
            /**
             * Represents the size of the `OpType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeVectorBase.OP_TYPE_MAX
             */
            val MAX: OpType get() = OpType(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVectorBase? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVectorBase? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVectorBase(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVectorBase? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorBase(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_OP_TYPE_HASH = 1692596998L
        @JvmField
        val setOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVectorBase", "set_op_type", SET_OP_TYPE_HASH)

        private const val GET_OP_TYPE_HASH = 2568738462L
        @JvmField
        val getOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVectorBase", "get_op_type", GET_OP_TYPE_HASH)
    }
}
