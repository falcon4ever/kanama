package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeSwitch
 */
class VisualShaderNodeSwitch(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeSwitch.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    fun setOpType(type: VisualShaderNodeSwitch.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOpTypeBind, segment, type.value)
    }

    fun getOpType(): VisualShaderNodeSwitch.OpType {
        checkOpen()
        return VisualShaderNodeSwitch.OpType(ObjectCalls.ptrcallNoArgsRetLong(getOpTypeBind, segment))
    }

    @JvmInline
    value class OpType(val value: Long) {
        companion object {
            val FLOAT: OpType get() = OpType(0L)
            val INT: OpType get() = OpType(1L)
            val UINT: OpType get() = OpType(2L)
            val VECTOR_2D: OpType get() = OpType(3L)
            val VECTOR_3D: OpType get() = OpType(4L)
            val VECTOR_4D: OpType get() = OpType(5L)
            val BOOLEAN: OpType get() = OpType(6L)
            val TRANSFORM: OpType get() = OpType(7L)
            val MAX: OpType get() = OpType(8L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeSwitch? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeSwitch? =
            if (handle.address() == 0L) null else VisualShaderNodeSwitch(GodotHandle(handle))

        private const val SET_OP_TYPE_HASH = 510471861L
        private val setOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeSwitch", "set_op_type", SET_OP_TYPE_HASH)
        }

        private const val GET_OP_TYPE_HASH = 2517845071L
        private val getOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeSwitch", "get_op_type", GET_OP_TYPE_HASH)
        }
    }
}
