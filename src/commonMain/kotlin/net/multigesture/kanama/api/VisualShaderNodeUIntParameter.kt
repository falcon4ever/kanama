package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A visual shader node for shader parameter (uniform) of type unsigned `int`.
 *
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

    /**
     * If `true`, the node will have a custom default value.
     *
     * Generated from Godot docs: VisualShaderNodeUIntParameter.set_default_value_enabled
     */
    fun setDefaultValueEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDefaultValueEnabledBind, segment, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, the node will have a custom default value.
     *
     * Generated from Godot docs: VisualShaderNodeUIntParameter.is_default_value_enabled
     */
    fun isDefaultValueEnabled(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isDefaultValueEnabledBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Default value of this parameter, which will be used if not set externally.
     * `default_value_enabled` must be enabled; defaults to `0` otherwise.
     *
     * Generated from Godot docs: VisualShaderNodeUIntParameter.set_default_value
     */
    fun setDefaultValue(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setDefaultValueBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Default value of this parameter, which will be used if not set externally.
     * `default_value_enabled` must be enabled; defaults to `0` otherwise.
     *
     * Generated from Godot docs: VisualShaderNodeUIntParameter.get_default_value
     */
    fun getDefaultValue(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getDefaultValueBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeUIntParameter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeUIntParameter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeUIntParameter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeUIntParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeUIntParameter(GodotHandle(handle))

        /** A new VisualShaderNodeUIntParameter, GDScript's `VisualShaderNodeUIntParameter.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeUIntParameter =
            RefCounted.owned(VisualShaderNodeUIntParameter(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeUIntParameter"))))
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
