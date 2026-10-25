package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color

/**
 * A `Color` parameter to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeColorParameter
 */
class VisualShaderNodeColorParameter(handle: GodotHandle) : VisualShaderNodeParameter(handle) {
    var defaultValueEnabled: Boolean
        @JvmName("defaultValueEnabledProperty")
        get() = isDefaultValueEnabled()
        @JvmName("setDefaultValueEnabledProperty")
        set(value) = setDefaultValueEnabled(value)

    var defaultValue: Color
        @JvmName("defaultValueProperty")
        get() = getDefaultValue()
        @JvmName("setDefaultValueProperty")
        set(value) = setDefaultValue(value)

    /**
     * Enables usage of the `default_value`.
     *
     * Generated from Godot docs: VisualShaderNodeColorParameter.set_default_value_enabled
     */
    fun setDefaultValueEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDefaultValueEnabledBind, segment, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Enables usage of the `default_value`.
     *
     * Generated from Godot docs: VisualShaderNodeColorParameter.is_default_value_enabled
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
     * Generated from Godot docs: VisualShaderNodeColorParameter.set_default_value
     */
    fun setDefaultValue(value: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithColorArg(Binds.setDefaultValueBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A default value to be assigned within the shader.
     *
     * Generated from Godot docs: VisualShaderNodeColorParameter.get_default_value
     */
    fun getDefaultValue(): Color {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetColor(Binds.getDefaultValueBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeColorParameter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeColorParameter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeColorParameter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeColorParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeColorParameter(GodotHandle(handle))

        /** A new VisualShaderNodeColorParameter, GDScript's `VisualShaderNodeColorParameter.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeColorParameter =
            RefCounted.owned(VisualShaderNodeColorParameter(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeColorParameter"))))
    }

    private object Binds {
        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        @JvmField
        val setDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeColorParameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        @JvmField
        val isDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeColorParameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)

        private const val SET_DEFAULT_VALUE_HASH = 2920490490L
        @JvmField
        val setDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeColorParameter", "set_default_value", SET_DEFAULT_VALUE_HASH)

        private const val GET_DEFAULT_VALUE_HASH = 3444240500L
        @JvmField
        val getDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeColorParameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
    }
}
