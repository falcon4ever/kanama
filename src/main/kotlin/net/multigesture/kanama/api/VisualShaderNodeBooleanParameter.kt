package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeBooleanParameter
 */
class VisualShaderNodeBooleanParameter(handle: MemorySegment) : VisualShaderNodeParameter(handle) {
    var defaultValueEnabled: Boolean
        @JvmName("defaultValueEnabledProperty")
        get() = isDefaultValueEnabled()
        @JvmName("setDefaultValueEnabledProperty")
        set(value) = setDefaultValueEnabled(value)

    var defaultValue: Boolean
        @JvmName("defaultValueProperty")
        get() = getDefaultValue()
        @JvmName("setDefaultValueProperty")
        set(value) = setDefaultValue(value)

    fun setDefaultValueEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDefaultValueEnabledBind, handle, enabled)
    }

    fun isDefaultValueEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDefaultValueEnabledBind, handle)
    }

    fun setDefaultValue(value: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDefaultValueBind, handle, value)
    }

    fun getDefaultValue(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDefaultValueBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeBooleanParameter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeBooleanParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeBooleanParameter(handle)

        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        private val setDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeBooleanParameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        private val isDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeBooleanParameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val SET_DEFAULT_VALUE_HASH = 2586408642L
        private val setDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeBooleanParameter", "set_default_value", SET_DEFAULT_VALUE_HASH)
        }

        private const val GET_DEFAULT_VALUE_HASH = 36873697L
        private val getDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeBooleanParameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
        }
    }
}
