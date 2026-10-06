package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeUIntParameter
 */
class VisualShaderNodeUIntParameter(handle: GodotHandle) : VisualShaderNodeParameter(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDefaultValueEnabledBind, segment, enabled)
    }

    fun isDefaultValueEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDefaultValueEnabledBind, segment)
    }

    fun setDefaultValue(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setDefaultValueBind, segment, value)
    }

    fun getDefaultValue(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDefaultValueBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeUIntParameter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeUIntParameter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeUIntParameter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeUIntParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeUIntParameter(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        @JvmField
        val setDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeUIntParameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        @JvmField
        val isDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeUIntParameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)

        private const val SET_DEFAULT_VALUE_HASH = 1286410249L
        @JvmField
        val setDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeUIntParameter", "set_default_value", SET_DEFAULT_VALUE_HASH)

        private const val GET_DEFAULT_VALUE_HASH = 3905245786L
        @JvmField
        val getDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeUIntParameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
    }
}
