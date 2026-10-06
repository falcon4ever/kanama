package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeParameterRef
 */
class VisualShaderNodeParameterRef(handle: GodotHandle) : VisualShaderNode(handle) {
    var parameterName: String
        @JvmName("parameterNameProperty")
        get() = getParameterName()
        @JvmName("setParameterNameProperty")
        set(value) = setParameterName(value)

    fun setParameterName(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setParameterNameBind, segment, name)
    }

    fun getParameterName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getParameterNameBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeParameterRef? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeParameterRef? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeParameterRef(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeParameterRef? =
            if (handle.address() == 0L) null else VisualShaderNodeParameterRef(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_PARAMETER_NAME_HASH = 83702148L
        @JvmField
        val setParameterNameBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParameterRef", "set_parameter_name", SET_PARAMETER_NAME_HASH)

        private const val GET_PARAMETER_NAME_HASH = 201670096L
        @JvmField
        val getParameterNameBind =
            ObjectCalls.getMethodBind("VisualShaderNodeParameterRef", "get_parameter_name", GET_PARAMETER_NAME_HASH)
    }
}
