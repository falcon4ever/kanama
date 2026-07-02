package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeMultiplyAdd
 */
class VisualShaderNodeMultiplyAdd(handle: MemorySegment) : VisualShaderNode(handle) {
    var opType: Long
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    fun setOpType(type: Long) {
        ObjectCalls.ptrcallWithLongArg(setOpTypeBind, handle, type)
    }

    fun getOpType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getOpTypeBind, handle)
    }

    companion object {
        const val OP_TYPE_SCALAR: Long = 0L
        const val OP_TYPE_VECTOR_2D: Long = 1L
        const val OP_TYPE_VECTOR_3D: Long = 2L
        const val OP_TYPE_VECTOR_4D: Long = 3L
        const val OP_TYPE_MAX: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeMultiplyAdd? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeMultiplyAdd? =
            if (handle.address() == 0L) null else VisualShaderNodeMultiplyAdd(handle)

        private const val SET_OP_TYPE_HASH = 1409862380L
        private val setOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeMultiplyAdd", "set_op_type", SET_OP_TYPE_HASH)
        }

        private const val GET_OP_TYPE_HASH = 2823201991L
        private val getOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeMultiplyAdd", "get_op_type", GET_OP_TYPE_HASH)
        }
    }
}
