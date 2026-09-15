package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeClamp
 */
class VisualShaderNodeClamp(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: Long
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    fun setOpType(opType: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOpTypeBind, segment, opType)
    }

    fun getOpType(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getOpTypeBind, segment)
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
        fun fromHandle(handle: GodotHandle): VisualShaderNodeClamp? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeClamp? =
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
