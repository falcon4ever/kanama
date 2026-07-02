package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeParameter
 */
open class VisualShaderNodeParameter(handle: MemorySegment) : VisualShaderNode(handle) {
    var parameterName: String
        @JvmName("parameterNameProperty")
        get() = getParameterName()
        @JvmName("setParameterNameProperty")
        set(value) = setParameterName(value)

    var qualifier: Long
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
        ObjectCalls.ptrcallWithStringArg(setParameterNameBind, handle, name)
    }

    fun getParameterName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getParameterNameBind, handle)
    }

    fun setQualifier(qualifier: Long) {
        ObjectCalls.ptrcallWithLongArg(setQualifierBind, handle, qualifier)
    }

    fun getQualifier(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getQualifierBind, handle)
    }

    fun setInstanceIndex(instanceIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(setInstanceIndexBind, handle, instanceIndex)
    }

    fun getInstanceIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInstanceIndexBind, handle)
    }

    companion object {
        const val QUAL_NONE: Long = 0L
        const val QUAL_GLOBAL: Long = 1L
        const val QUAL_INSTANCE: Long = 2L
        const val QUAL_INSTANCE_INDEX: Long = 3L
        const val QUAL_MAX: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeParameter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeParameter(handle)

        private const val SET_PARAMETER_NAME_HASH = 83702148L
        private val setParameterNameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParameter", "set_parameter_name", SET_PARAMETER_NAME_HASH)
        }

        private const val GET_PARAMETER_NAME_HASH = 201670096L
        private val getParameterNameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParameter", "get_parameter_name", GET_PARAMETER_NAME_HASH)
        }

        private const val SET_QUALIFIER_HASH = 1276489447L
        private val setQualifierBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParameter", "set_qualifier", SET_QUALIFIER_HASH)
        }

        private const val GET_QUALIFIER_HASH = 3558406205L
        private val getQualifierBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParameter", "get_qualifier", GET_QUALIFIER_HASH)
        }

        private const val SET_INSTANCE_INDEX_HASH = 1286410249L
        private val setInstanceIndexBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParameter", "set_instance_index", SET_INSTANCE_INDEX_HASH)
        }

        private const val GET_INSTANCE_INDEX_HASH = 3905245786L
        private val getInstanceIndexBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParameter", "get_instance_index", GET_INSTANCE_INDEX_HASH)
        }
    }
}
