package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeStep
 */
class VisualShaderNodeStep(handle: GodotHandle) : VisualShaderNode(handle) {
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
        const val OP_TYPE_SCALAR: Long = 0L
        const val OP_TYPE_VECTOR_2D: Long = 1L
        const val OP_TYPE_VECTOR_2D_SCALAR: Long = 2L
        const val OP_TYPE_VECTOR_3D: Long = 3L
        const val OP_TYPE_VECTOR_3D_SCALAR: Long = 4L
        const val OP_TYPE_VECTOR_4D: Long = 5L
        const val OP_TYPE_VECTOR_4D_SCALAR: Long = 6L
        const val OP_TYPE_MAX: Long = 7L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeStep? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeStep? =
            if (handle.address() == 0L) null else VisualShaderNodeStep(GodotHandle(handle))

        private const val SET_OP_TYPE_HASH = 715172489L
        private val setOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeStep", "set_op_type", SET_OP_TYPE_HASH)
        }

        private const val GET_OP_TYPE_HASH = 3274022781L
        private val getOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeStep", "get_op_type", GET_OP_TYPE_HASH)
        }
    }
}
