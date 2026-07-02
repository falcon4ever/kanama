package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeRemap
 */
class VisualShaderNodeRemap(handle: MemorySegment) : VisualShaderNode(handle) {
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
        const val OP_TYPE_SCALAR: Long = 0L
        const val OP_TYPE_VECTOR_2D: Long = 1L
        const val OP_TYPE_VECTOR_2D_SCALAR: Long = 2L
        const val OP_TYPE_VECTOR_3D: Long = 3L
        const val OP_TYPE_VECTOR_3D_SCALAR: Long = 4L
        const val OP_TYPE_VECTOR_4D: Long = 5L
        const val OP_TYPE_VECTOR_4D_SCALAR: Long = 6L
        const val OP_TYPE_MAX: Long = 7L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeRemap? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeRemap? =
            if (handle.address() == 0L) null else VisualShaderNodeRemap(handle)

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
