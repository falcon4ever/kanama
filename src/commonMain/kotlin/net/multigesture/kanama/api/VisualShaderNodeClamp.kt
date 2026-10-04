package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeClamp
 */
class VisualShaderNodeClamp(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeClamp.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    fun setOpType(opType: VisualShaderNodeClamp.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOpTypeBind, segment, opType.value)
    }

    fun getOpType(): VisualShaderNodeClamp.OpType {
        checkOpen()
        return VisualShaderNodeClamp.OpType(ObjectCalls.ptrcallNoArgsRetLong(getOpTypeBind, segment))
    }

    @JvmInline
    value class OpType(override val value: Long) : GodotEnumValue {
        companion object {
            val FLOAT: OpType get() = OpType(0L)
            val INT: OpType get() = OpType(1L)
            val UINT: OpType get() = OpType(2L)
            val VECTOR_2D: OpType get() = OpType(3L)
            val VECTOR_3D: OpType get() = OpType(4L)
            val VECTOR_4D: OpType get() = OpType(5L)
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

        private const val SET_OP_TYPE_HASH = 405010749L
        private val setOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeClamp", "set_op_type", SET_OP_TYPE_HASH)
        }

        private const val GET_OP_TYPE_HASH = 233276050L
        private val getOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeClamp", "get_op_type", GET_OP_TYPE_HASH)
        }
    }
}
