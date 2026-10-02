package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeParticleRandomness
 */
class VisualShaderNodeParticleRandomness(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeParticleRandomness.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    fun setOpType(type: VisualShaderNodeParticleRandomness.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOpTypeBind, segment, type.value)
    }

    fun getOpType(): VisualShaderNodeParticleRandomness.OpType {
        checkOpen()
        return VisualShaderNodeParticleRandomness.OpType(ObjectCalls.ptrcallNoArgsRetLong(getOpTypeBind, segment))
    }

    @JvmInline
    value class OpType(val value: Long) {
        companion object {
            val SCALAR: OpType get() = OpType(0L)
            val VECTOR_2D: OpType get() = OpType(1L)
            val VECTOR_3D: OpType get() = OpType(2L)
            val VECTOR_4D: OpType get() = OpType(3L)
            val MAX: OpType get() = OpType(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleRandomness? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeParticleRandomness? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleRandomness(GodotHandle(handle))

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
