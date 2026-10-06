package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeParameter
 */
open class VisualShaderNodeParameter(handle: GodotHandle) : VisualShaderNode(handle) {
    var parameterName: String
        @JvmName("parameterNameProperty")
        get() = getParameterName()
        @JvmName("setParameterNameProperty")
        set(value) = setParameterName(value)

    var qualifier: VisualShaderNodeParameter.Qualifier
        @JvmName("qualifierProperty")
        get() = getQualifier()
        @JvmName("setQualifierProperty")
        set(value) = setQualifier(value)

    var instanceIndex: Int
        @JvmName("instanceIndexProperty")
        get() = getInstanceIndex()
        @JvmName("setInstanceIndexProperty")
        set(value) = setInstanceIndex(value)

    fun setParameterName(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setParameterNameBind, segment, name)
    }

    fun getParameterName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getParameterNameBind, segment)
    }

    fun setQualifier(qualifier: VisualShaderNodeParameter.Qualifier) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setQualifierBind, segment, qualifier.value)
    }

    fun getQualifier(): VisualShaderNodeParameter.Qualifier {
        checkOpen()
        return VisualShaderNodeParameter.Qualifier(ObjectCalls.ptrcallNoArgsRetLong(Binds.getQualifierBind, segment))
    }

    fun setInstanceIndex(instanceIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setInstanceIndexBind, segment, instanceIndex)
    }

    fun getInstanceIndex(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getInstanceIndexBind, segment)
    }

    @JvmInline
    value class Qualifier(override val value: Long) : GodotEnumValue {
        companion object {
            val NONE: Qualifier get() = Qualifier(0L)
            val GLOBAL: Qualifier get() = Qualifier(1L)
            val INSTANCE: Qualifier get() = Qualifier(2L)
            val INSTANCE_INDEX: Qualifier get() = Qualifier(3L)
            val MAX: Qualifier get() = Qualifier(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParameter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeParameter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeParameter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeParameter(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_PARAMETER_NAME_HASH = 83702148L
        @JvmField
        val setParameterNameBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParameter", "set_parameter_name", SET_PARAMETER_NAME_HASH)

        private const val GET_PARAMETER_NAME_HASH = 201670096L
        @JvmField
        val getParameterNameBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParameter", "get_parameter_name", GET_PARAMETER_NAME_HASH)

        private const val SET_QUALIFIER_HASH = 1276489447L
        @JvmField
        val setQualifierBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParameter", "set_qualifier", SET_QUALIFIER_HASH)

        private const val GET_QUALIFIER_HASH = 3558406205L
        @JvmField
        val getQualifierBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParameter", "get_qualifier", GET_QUALIFIER_HASH)

        private const val SET_INSTANCE_INDEX_HASH = 1286410249L
        @JvmField
        val setInstanceIndexBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParameter", "set_instance_index", SET_INSTANCE_INDEX_HASH)

        private const val GET_INSTANCE_INDEX_HASH = 3905245786L
        @JvmField
        val getInstanceIndexBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParameter", "get_instance_index", GET_INSTANCE_INDEX_HASH)
    }
}
