package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Visual shader node for randomizing particle values.
 *
 * Generated from Godot docs: VisualShaderNodeParticleRandomness
 */
class VisualShaderNodeParticleRandomness(handle: GodotHandle) : VisualShaderNode(handle) {
    var opType: VisualShaderNodeParticleRandomness.OpType
        @JvmName("opTypeProperty")
        get() = getOpType()
        @JvmName("setOpTypeProperty")
        set(value) = setOpType(value)

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeParticleRandomness.set_op_type
     */
    fun setOpType(type: VisualShaderNodeParticleRandomness.OpType) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOpTypeBind, segment, type.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A type of operands and returned value.
     *
     * Generated from Godot docs: VisualShaderNodeParticleRandomness.get_op_type
     */
    fun getOpType(): VisualShaderNodeParticleRandomness.OpType {
        checkOpen()
        val ret = VisualShaderNodeParticleRandomness.OpType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOpTypeBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeParticleRandomness.OpType` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`VisualShaderNodeParticleRandomness.OpType.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeParticleRandomness.OpType
     */
    @JvmInline
    value class OpType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * A floating-point scalar.
             *
             * Generated from Godot docs: VisualShaderNodeParticleRandomness.OP_TYPE_SCALAR
             */
            val SCALAR: OpType get() = OpType(0L)
            /**
             * A 2D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeParticleRandomness.OP_TYPE_VECTOR_2D
             */
            val VECTOR_2D: OpType get() = OpType(1L)
            /**
             * A 3D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeParticleRandomness.OP_TYPE_VECTOR_3D
             */
            val VECTOR_3D: OpType get() = OpType(2L)
            /**
             * A 4D vector type.
             *
             * Generated from Godot docs: VisualShaderNodeParticleRandomness.OP_TYPE_VECTOR_4D
             */
            val VECTOR_4D: OpType get() = OpType(3L)
            /**
             * Represents the size of the `OpType` enum.
             *
             * Generated from Godot docs: VisualShaderNodeParticleRandomness.OP_TYPE_MAX
             */
            val MAX: OpType get() = OpType(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParticleRandomness? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeParticleRandomness? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeParticleRandomness(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeParticleRandomness? =
            if (handle.address() == 0L) null else VisualShaderNodeParticleRandomness(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_OP_TYPE_HASH = 2060089061L
        @JvmField
        val setOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleRandomness", "set_op_type", SET_OP_TYPE_HASH)

        private const val GET_OP_TYPE_HASH = 3597061078L
        @JvmField
        val getOpTypeBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParticleRandomness", "get_op_type", GET_OP_TYPE_HASH)
    }
}
