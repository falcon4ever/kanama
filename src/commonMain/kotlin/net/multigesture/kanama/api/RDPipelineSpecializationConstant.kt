package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Pipeline specialization constant (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDPipelineSpecializationConstant
 */
class RDPipelineSpecializationConstant(handle: MemorySegment) : RefCounted(handle) {
    val value: Any?
        @JvmName("valueProperty")
        get() = getValue()

    var constantId: Long
        @JvmName("constantIdProperty")
        get() = getConstantId()
        @JvmName("setConstantIdProperty")
        set(value) = setConstantId(value)

    /**
     * The specialization constant's value. Only `bool`, `int` and `float` types are valid for
     * specialization constants.
     *
     * Generated from Godot docs: RDPipelineSpecializationConstant.get_value
     */
    fun getValue(): Any? {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(getValueBind, handle)
    }

    /**
     * The identifier of the specialization constant. This is a value starting from `0` and that
     * increments for every different specialization constant for a given shader.
     *
     * Generated from Godot docs: RDPipelineSpecializationConstant.set_constant_id
     */
    fun setConstantId(constantId: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(setConstantIdBind, handle, constantId)
    }

    /**
     * The identifier of the specialization constant. This is a value starting from `0` and that
     * increments for every different specialization constant for a given shader.
     *
     * Generated from Godot docs: RDPipelineSpecializationConstant.get_constant_id
     */
    fun getConstantId(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(getConstantIdBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): RDPipelineSpecializationConstant? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RDPipelineSpecializationConstant? =
            if (handle.address() == 0L) null else RDPipelineSpecializationConstant(handle)

        private const val GET_VALUE_HASH = 1214101251L
        private val getValueBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineSpecializationConstant", "get_value", GET_VALUE_HASH)
        }

        private const val SET_CONSTANT_ID_HASH = 1286410249L
        private val setConstantIdBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineSpecializationConstant", "set_constant_id", SET_CONSTANT_ID_HASH)
        }

        private const val GET_CONSTANT_ID_HASH = 3905245786L
        private val getConstantIdBind by lazy {
            ObjectCalls.getMethodBind("RDPipelineSpecializationConstant", "get_constant_id", GET_CONSTANT_ID_HASH)
        }
    }
}
