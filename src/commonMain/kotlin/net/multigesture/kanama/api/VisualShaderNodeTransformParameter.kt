package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D

/**
 * A `Transform3D` parameter for use within the visual shader graph.
 *
 * Generated from Godot docs: VisualShaderNodeTransformParameter
 */
class VisualShaderNodeTransformParameter(handle: GodotHandle) : VisualShaderNodeParameter(handle) {
    var defaultValueEnabled: Boolean
        @JvmName("defaultValueEnabledProperty")
        get() = isDefaultValueEnabled()
        @JvmName("setDefaultValueEnabledProperty")
        set(value) = setDefaultValueEnabled(value)

    var defaultValue: Transform3D
        @JvmName("defaultValueProperty")
        get() = getDefaultValue()
        @JvmName("setDefaultValueProperty")
        set(value) = setDefaultValue(value)

    /**
     * Enables usage of the `default_value`.
     *
     * Generated from Godot docs: VisualShaderNodeTransformParameter.set_default_value_enabled
     */
    fun setDefaultValueEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDefaultValueEnabledBind, segment, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Enables usage of the `default_value`.
     *
     * Generated from Godot docs: VisualShaderNodeTransformParameter.is_default_value_enabled
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
     * Generated from Godot docs: VisualShaderNodeTransformParameter.set_default_value
     */
    fun setDefaultValue(value: Transform3D) {
        checkOpen()
        ObjectCalls.ptrcallWithTransform3DArg(Binds.setDefaultValueBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A default value to be assigned within the shader.
     *
     * Generated from Godot docs: VisualShaderNodeTransformParameter.get_default_value
     */
    fun getDefaultValue(): Transform3D {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetTransform3D(Binds.getDefaultValueBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeTransformParameter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeTransformParameter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeTransformParameter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeTransformParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformParameter(GodotHandle(handle))

        /** A new VisualShaderNodeTransformParameter, GDScript's `VisualShaderNodeTransformParameter.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeTransformParameter =
            RefCounted.owned(VisualShaderNodeTransformParameter(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeTransformParameter"))))
    }

    private object Binds {
        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        @JvmField
        val setDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformParameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        @JvmField
        val isDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformParameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)

        private const val SET_DEFAULT_VALUE_HASH = 2952846383L
        @JvmField
        val setDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformParameter", "set_default_value", SET_DEFAULT_VALUE_HASH)

        private const val GET_DEFAULT_VALUE_HASH = 3229777777L
        @JvmField
        val getDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeTransformParameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
    }
}
