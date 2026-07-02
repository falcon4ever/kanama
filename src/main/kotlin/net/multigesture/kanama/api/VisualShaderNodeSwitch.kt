package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeSwitch
 */
class VisualShaderNodeSwitch(handle: MemorySegment) : VisualShaderNode(handle) {
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
        const val OP_TYPE_FLOAT: Long = 0L
        const val OP_TYPE_INT: Long = 1L
        const val OP_TYPE_UINT: Long = 2L
        const val OP_TYPE_VECTOR_2D: Long = 3L
        const val OP_TYPE_VECTOR_3D: Long = 4L
        const val OP_TYPE_VECTOR_4D: Long = 5L
        const val OP_TYPE_BOOLEAN: Long = 6L
        const val OP_TYPE_TRANSFORM: Long = 7L
        const val OP_TYPE_MAX: Long = 8L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeSwitch? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeSwitch? =
            if (handle.address() == 0L) null else VisualShaderNodeSwitch(handle)

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
