package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeClamp
 */
class VisualShaderNodeClamp(handle: MemorySegment) : VisualShaderNode(handle) {
    var opType: Long
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    fun setOpType(opType: Long) {
        ObjectCalls.ptrcallWithLongArg(setOpTypeBind, handle, opType)
    }

    fun getOpType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getOpTypeBind, handle)
    }

    companion object {
        const val OP_TYPE_FLOAT: Long = 0L
        const val OP_TYPE_INT: Long = 1L
        const val OP_TYPE_UINT: Long = 2L
        const val OP_TYPE_VECTOR_2D: Long = 3L
        const val OP_TYPE_VECTOR_3D: Long = 4L
        const val OP_TYPE_VECTOR_4D: Long = 5L
        const val OP_TYPE_MAX: Long = 6L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeClamp? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeClamp? =
            if (handle.address() == 0L) null else VisualShaderNodeClamp(handle)

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
