package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeParticleRandomness
 */
class VisualShaderNodeParticleRandomness(handle: MemorySegment) : VisualShaderNode(handle) {
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
        fun fromHandle(handle: MemorySegment): VisualShaderNodeParticleRandomness? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeParticleRandomness? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleRandomness(handle)

        private const val SET_OP_TYPE_HASH = 2060089061L
        private val setOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleRandomness", "set_op_type", SET_OP_TYPE_HASH)
        }

        private const val GET_OP_TYPE_HASH = 3597061078L
        private val getOpTypeBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParticleRandomness", "get_op_type", GET_OP_TYPE_HASH)
        }
    }
}
