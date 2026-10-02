package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeStep
 */
class VisualShaderNodeStep(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeStep.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    fun setOpType(opType: VisualShaderNodeStep.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOpTypeBind, segment, opType.value)
    }

    fun getOpType(): VisualShaderNodeStep.OpType {
        checkOpen()
        return VisualShaderNodeStep.OpType(ObjectCalls.ptrcallNoArgsRetLong(getOpTypeBind, segment))
    }

    @JvmInline
    value class OpType(val value: Long) {
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
        fun fromHandle(handle: GodotHandle): VisualShaderNodeStep? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeStep? =
            if (handle.address() == 0L) null else VisualShaderNodeStep(GodotHandle(handle))

        private const val SET_OP_TYPE_HASH = 715172489L
        private val setOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeStep", "set_op_type", SET_OP_TYPE_HASH)
        }

        private const val GET_OP_TYPE_HASH = 3274022781L
        private val getOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeStep", "get_op_type", GET_OP_TYPE_HASH)
        }
    }
}
