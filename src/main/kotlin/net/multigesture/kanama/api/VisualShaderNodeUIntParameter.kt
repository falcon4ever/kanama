package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeUIntParameter
 */
class VisualShaderNodeUIntParameter(handle: MemorySegment) : VisualShaderNodeParameter(handle) {
    var defaultValueEnabled: Boolean
        @JvmName("defaultValueEnabledProperty")
        get() = isDefaultValueEnabled()
        @JvmName("setDefaultValueEnabledProperty")
        set(value) = setDefaultValueEnabled(value)

    var defaultValue: Int
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

    fun setDefaultValue(value: Int) {
        ObjectCalls.ptrcallWithIntArg(setDefaultValueBind, handle, value)
    }

    fun getDefaultValue(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDefaultValueBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeUIntParameter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeUIntParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeUIntParameter(handle)

        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        private val setDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeUIntParameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        private val isDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeUIntParameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val SET_DEFAULT_VALUE_HASH = 1286410249L
        private val setDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeUIntParameter", "set_default_value", SET_DEFAULT_VALUE_HASH)
        }

        private const val GET_DEFAULT_VALUE_HASH = 3905245786L
        private val getDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeUIntParameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
        }
    }
}
