package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector4

/**
 * A 4D vector parameter to be used within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeVec4Parameter
 */
class VisualShaderNodeVec4Parameter(handle: GodotHandle) : VisualShaderNodeParameter(handle) {
    var defaultValueEnabled: Boolean
        @JvmName("defaultValueEnabledProperty")
        get() = isDefaultValueEnabled()
        @JvmName("setDefaultValueEnabledProperty")
        set(value) = setDefaultValueEnabled(value)

    var defaultValue: Vector4
        @JvmName("defaultValueProperty")
        get() = getDefaultValue()
        @JvmName("setDefaultValueProperty")
        set(value) = setDefaultValue(value)

    /**
     * Enables usage of the `default_value`.
     *
     * Generated from Godot docs: VisualShaderNodeVec4Parameter.set_default_value_enabled
     */
    fun setDefaultValueEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDefaultValueEnabledBind, segment, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Enables usage of the `default_value`.
     *
     * Generated from Godot docs: VisualShaderNodeVec4Parameter.is_default_value_enabled
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
     * Generated from Godot docs: VisualShaderNodeVec4Parameter.set_default_value
     */
    fun setDefaultValue(value: Vector4) {
        checkOpen()
        ObjectCalls.ptrcallWithVector4Arg(Binds.setDefaultValueBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A default value to be assigned within the shader.
     *
     * Generated from Godot docs: VisualShaderNodeVec4Parameter.get_default_value
     */
    fun getDefaultValue(): Vector4 {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetVector4(Binds.getDefaultValueBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVec4Parameter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeVec4Parameter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeVec4Parameter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeVec4Parameter? =
            if (handle.address() == 0L) null else VisualShaderNodeVec4Parameter(GodotHandle(handle))

        /** A new VisualShaderNodeVec4Parameter, GDScript's `VisualShaderNodeVec4Parameter.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeVec4Parameter =
            RefCounted.owned(VisualShaderNodeVec4Parameter(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeVec4Parameter"))))
    }

    private object Binds {
        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        @JvmField
        val setDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVec4Parameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        @JvmField
        val isDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVec4Parameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)

        private const val SET_DEFAULT_VALUE_HASH = 643568085L
        @JvmField
        val setDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVec4Parameter", "set_default_value", SET_DEFAULT_VALUE_HASH)

        private const val GET_DEFAULT_VALUE_HASH = 2435802345L
        @JvmField
        val getDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeVec4Parameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
    }
}
