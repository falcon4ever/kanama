package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeVectorBase
 */
open class VisualShaderNodeVectorBase(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeVectorBase.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    fun setOpType(type: VisualShaderNodeVectorBase.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOpTypeBind, segment, type.value)
    }

    fun getOpType(): VisualShaderNodeVectorBase.OpType {
        checkOpen()
        return VisualShaderNodeVectorBase.OpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpTypeBind, segment))
    }

    @JvmInline
    value class OpType(override val value: Long) : GodotEnumValue {
        companion object {
            val VECTOR_2D: OpType get() = OpType(0L)
            val VECTOR_3D: OpType get() = OpType(1L)
            val VECTOR_4D: OpType get() = OpType(2L)
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
