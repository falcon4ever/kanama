package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
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
        ObjectCalls.ptrcallWithLongArg(setOpTypeBind, segment, opType.value)
    }

    fun getOpType(): VisualShaderNodeRemap.OpType {
        checkOpen()
        return VisualShaderNodeRemap.OpType(ObjectCalls.ptrcallNoArgsRetLong(getOpTypeBind, segment))
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
        fun fromHandle(handle: GodotHandle): VisualShaderNodeRemap? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeRemap? =
            if (handle.address() == 0L) null else VisualShaderNodeRemap(GodotHandle(handle))

        private const val SET_OP_TYPE_HASH = 1703697889L
        private val setOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeRemap", "set_op_type", SET_OP_TYPE_HASH)
        }

        private const val GET_OP_TYPE_HASH = 1678380563L
        private val getOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeRemap", "get_op_type", GET_OP_TYPE_HASH)
        }
    }
}
