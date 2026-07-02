package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D

/**
 * Generated from Godot docs: VisualShaderNodeTransformParameter
 */
class VisualShaderNodeTransformParameter(handle: MemorySegment) : VisualShaderNodeParameter(handle) {
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

    fun setDefaultValueEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDefaultValueEnabledBind, handle, enabled)
    }

    fun isDefaultValueEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDefaultValueEnabledBind, handle)
    }

    fun setDefaultValue(value: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setDefaultValueBind, handle, value)
    }

    fun getDefaultValue(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getDefaultValueBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeTransformParameter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeTransformParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeTransformParameter(handle)

        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        private val setDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformParameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        private val isDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformParameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val SET_DEFAULT_VALUE_HASH = 2952846383L
        private val setDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformParameter", "set_default_value", SET_DEFAULT_VALUE_HASH)
        }

        private const val GET_DEFAULT_VALUE_HASH = 3229777777L
        private val getDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeTransformParameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
        }
    }
}
