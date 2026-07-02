package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeVectorBase
 */
open class VisualShaderNodeVectorBase(handle: MemorySegment) : VisualShaderNode(handle) {
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
        const val OP_TYPE_VECTOR_2D: Long = 0L
        const val OP_TYPE_VECTOR_3D: Long = 1L
        const val OP_TYPE_VECTOR_4D: Long = 2L
        const val OP_TYPE_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeVectorBase? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeVectorBase? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorBase(handle)

        private const val SET_OP_TYPE_HASH = 1692596998L
        private val setOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVectorBase", "set_op_type", SET_OP_TYPE_HASH)
        }

        private const val GET_OP_TYPE_HASH = 2568738462L
        private val getOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVectorBase", "get_op_type", GET_OP_TYPE_HASH)
        }
    }
}
