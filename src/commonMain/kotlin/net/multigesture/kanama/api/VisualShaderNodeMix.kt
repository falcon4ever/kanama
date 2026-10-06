package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeMix
 */
class VisualShaderNodeMix(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeMix.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    fun setOpType(opType: VisualShaderNodeMix.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOpTypeBind, segment, opType.value)
    }

    fun getOpType(): VisualShaderNodeMix.OpType {
        checkOpen()
        return VisualShaderNodeMix.OpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpTypeBind, segment))
    }

    @JvmInline
    value class OpType(override val value: Long) : GodotEnumValue {
        companion object {
            val SCALAR: OpType get() = OpType(0L)
            val VECTOR_2D: OpType get() = OpType(1L)
            val VECTOR_2D_SCALAR: OpType get() = OpType(2L)
            val VECTOR_3D: OpType get() = OpType(3L)
            val VECTOR_3D_SCALAR: OpType get() = OpType(4L)
            val VECTOR_4D: OpType get() = OpType(5L)
            val VECTOR_4D_SCALAR: OpType get() = OpType(6L)
            val MAX: OpType get() = OpType(7L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeMix? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeMix? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeMix(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeMix? =
            if (handle.address() == 0L) null else VisualShaderNodeMix(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_OP_TYPE_HASH = 3397501671L
        @JvmField
        val setOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeMix", "set_op_type", SET_OP_TYPE_HASH)

        private const val GET_OP_TYPE_HASH = 4013957297L
        @JvmField
        val getOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeMix", "get_op_type", GET_OP_TYPE_HASH)
    }
}
