package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeParameterRef
 */
class VisualShaderNodeParameterRef(handle: MemorySegment) : VisualShaderNode(handle) {
    var parameterName: String
        @JvmName("parameterNameProperty")
        get() = getParameterName()
        @JvmName("setParameterNameProperty")
        set(value) = setParameterName(value)

    fun setParameterName(name: String) {
        ObjectCalls.ptrcallWithStringArg(setParameterNameBind, handle, name)
    }

    fun getParameterName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getParameterNameBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeParameterRef? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeParameterRef? =
            if (handle.address() == 0L) null else VisualShaderNodeParameterRef(handle)

        private const val SET_PARAMETER_NAME_HASH = 83702148L
        private val setParameterNameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParameterRef", "set_parameter_name", SET_PARAMETER_NAME_HASH)
        }

        private const val GET_PARAMETER_NAME_HASH = 201670096L
        private val getParameterNameBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeParameterRef", "get_parameter_name", GET_PARAMETER_NAME_HASH)
        }
    }
}
