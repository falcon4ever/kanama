package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeVectorBase
 */
open class VisualShaderNodeVectorBase(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: Long
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    fun setOpType(type: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOpTypeBind, segment, type)
    }

    fun getOpType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getOpTypeBind, segment)
    }

    companion object {
        const val OP_TYPE_VECTOR_2D: Long = 0L
        const val OP_TYPE_VECTOR_3D: Long = 1L
        const val OP_TYPE_VECTOR_4D: Long = 2L
        const val OP_TYPE_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVectorBase? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeVectorBase? =
            if (handle.address() == 0L) null else VisualShaderNodeVectorBase(GodotHandle(handle))

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
