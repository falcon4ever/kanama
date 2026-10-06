package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Pipeline specialization constant (used by `RenderingDevice`).
 *
 * Generated from Godot docs: RDPipelineSpecializationConstant
 */
class RDPipelineSpecializationConstant(handle: GodotHandle) : RefCounted(handle) {
    var value: Any?
        @JvmName("valueProperty")
        get() = getValue()
        @JvmName("setValueProperty")
        set(value) = setValue(value)

    var constantId: Long
        @JvmName("constantIdProperty")
        get() = getConstantId()
        @JvmName("setConstantIdProperty")
        set(value) = setConstantId(value)

    /**
     * The specialization constant's value. Only `bool`, `int` and `float` types are valid for
     * specialization constants.
     *
     * Generated from Godot docs: RDPipelineSpecializationConstant.set_value
     */
    fun setValue(value: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithVariantArg(Binds.setValueBind, segment, value)
    }

    /**
     * The specialization constant's value. Only `bool`, `int` and `float` types are valid for
     * specialization constants.
     *
     * Generated from Godot docs: RDPipelineSpecializationConstant.get_value
     */
    fun getValue(): Any? {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVariantScalar(Binds.getValueBind, segment)
    }

    /**
     * The identifier of the specialization constant. This is a value starting from `0` and that
     * increments for every different specialization constant for a given shader.
     *
     * Generated from Godot docs: RDPipelineSpecializationConstant.set_constant_id
     */
    fun setConstantId(constantId: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setConstantIdBind, segment, constantId)
    }

    /**
     * The identifier of the specialization constant. This is a value starting from `0` and that
     * increments for every different specialization constant for a given shader.
     *
     * Generated from Godot docs: RDPipelineSpecializationConstant.get_constant_id
     */
    fun getConstantId(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getConstantIdBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RDPipelineSpecializationConstant? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RDPipelineSpecializationConstant? =
            if (handle.address() == 0L) null else RefCounted.owned(RDPipelineSpecializationConstant(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RDPipelineSpecializationConstant? =
            if (handle.address() == 0L) null else RDPipelineSpecializationConstant(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_VALUE_HASH = 1114965689L
        @JvmField
        val setValueBind =
            ObjectCalls.getMethodBind("RDPipelineSpecializationConstant", "set_value", SET_VALUE_HASH)

        private const val GET_VALUE_HASH = 1214101251L
        @JvmField
        val getValueBind =
            ObjectCalls.getMethodBind("RDPipelineSpecializationConstant", "get_value", GET_VALUE_HASH)

        private const val SET_CONSTANT_ID_HASH = 1286410249L
        @JvmField
        val setConstantIdBind =
            ObjectCalls.getMethodBind("RDPipelineSpecializationConstant", "set_constant_id", SET_CONSTANT_ID_HASH)

        private const val GET_CONSTANT_ID_HASH = 3905245786L
        @JvmField
        val getConstantIdBind =
            ObjectCalls.getMethodBind("RDPipelineSpecializationConstant", "get_constant_id", GET_CONSTANT_ID_HASH)
    }
}
