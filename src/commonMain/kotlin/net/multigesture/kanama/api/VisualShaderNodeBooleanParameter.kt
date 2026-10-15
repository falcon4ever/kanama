package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A boolean parameter to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeBooleanParameter
 */
class VisualShaderNodeBooleanParameter(handle: GodotHandle) : VisualShaderNodeParameter(handle) {
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

    /**
     * Enables usage of the `default_value`.
     *
     * Generated from Godot docs: VisualShaderNodeBooleanParameter.set_default_value_enabled
     */
    fun setDefaultValueEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDefaultValueEnabledBind, segment, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Enables usage of the `default_value`.
     *
     * Generated from Godot docs: VisualShaderNodeBooleanParameter.is_default_value_enabled
     */
    fun isDefaultValueEnabled(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isDefaultValueEnabledBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * A default value to be assigned within the shader.
     *
     * Generated from Godot docs: VisualShaderNodeBooleanParameter.set_default_value
     */
    fun setDefaultValue(value: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDefaultValueBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A default value to be assigned within the shader.
     *
     * Generated from Godot docs: VisualShaderNodeBooleanParameter.get_default_value
     */
    fun getDefaultValue(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.getDefaultValueBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeBooleanParameter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeBooleanParameter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeBooleanParameter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeBooleanParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeBooleanParameter(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        @JvmField
        val setDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeBooleanParameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        @JvmField
        val isDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeBooleanParameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)

        private const val SET_DEFAULT_VALUE_HASH = 2586408642L
        @JvmField
        val setDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeBooleanParameter", "set_default_value", SET_DEFAULT_VALUE_HASH)

        private const val GET_DEFAULT_VALUE_HASH = 36873697L
        @JvmField
        val getDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeBooleanParameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
    }
}
