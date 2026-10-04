package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeSmoothStep
 */
class VisualShaderNodeSmoothStep(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeSmoothStep.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    fun setOpType(opType: VisualShaderNodeSmoothStep.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOpTypeBind, segment, opType.value)
    }

    fun getOpType(): VisualShaderNodeSmoothStep.OpType {
        checkOpen()
        return VisualShaderNodeSmoothStep.OpType(ObjectCalls.ptrcallNoArgsRetLong(getOpTypeBind, segment))
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
        fun fromHandle(handle: GodotHandle): VisualShaderNodeSmoothStep? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeSmoothStep? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeSmoothStep(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeSmoothStep? =
            if (handle.address() == 0L) null else VisualShaderNodeSmoothStep(GodotHandle(handle))

        private const val SET_OP_TYPE_HASH = 2427426148L
        private val setOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeSmoothStep", "set_op_type", SET_OP_TYPE_HASH)
        }

        private const val GET_OP_TYPE_HASH = 359640855L
        private val getOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeSmoothStep", "get_op_type", GET_OP_TYPE_HASH)
        }
    }
}
