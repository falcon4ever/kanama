package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: VisualShaderNodeVec2Parameter
 */
class VisualShaderNodeVec2Parameter(handle: GodotHandle) : VisualShaderNodeParameter(handle) {
    var defaultValueEnabled: Boolean
        @JvmName("defaultValueEnabledProperty")
        get() = isDefaultValueEnabled()
        @JvmName("setDefaultValueEnabledProperty")
        set(value) = setDefaultValueEnabled(value)

    var defaultValue: Vector2
        @JvmName("defaultValueProperty")
        get() = getDefaultValue()
        @JvmName("setDefaultValueProperty")
        set(value) = setDefaultValue(value)

    fun setDefaultValueEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setDefaultValueEnabledBind, segment, enabled)
    }

    fun isDefaultValueEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isDefaultValueEnabledBind, segment)
    }

    fun setDefaultValue(value: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setDefaultValueBind, segment, value)
    }

    fun getDefaultValue(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getDefaultValueBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVec2Parameter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeVec2Parameter? =
            if (handle.address() == 0L) null else VisualShaderNodeVec2Parameter(GodotHandle(handle))

        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        private val setDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec2Parameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        private val isDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec2Parameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val SET_DEFAULT_VALUE_HASH = 743155724L
        private val setDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec2Parameter", "set_default_value", SET_DEFAULT_VALUE_HASH)
        }

        private const val GET_DEFAULT_VALUE_HASH = 3341600327L
        private val getDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec2Parameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
        }
    }
}
