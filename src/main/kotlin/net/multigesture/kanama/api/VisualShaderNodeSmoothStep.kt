package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeSmoothStep
 */
class VisualShaderNodeSmoothStep(handle: MemorySegment) : VisualShaderNode(handle) {
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
        fun fromHandle(handle: MemorySegment): VisualShaderNodeSmoothStep? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeSmoothStep? =
            if (handle.address() == 0L) null else VisualShaderNodeSmoothStep(handle)

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
