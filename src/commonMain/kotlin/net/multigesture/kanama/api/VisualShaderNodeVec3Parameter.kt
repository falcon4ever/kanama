package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: VisualShaderNodeVec3Parameter
 */
class VisualShaderNodeVec3Parameter(handle: GodotHandle) : VisualShaderNodeParameter(handle) {
    var defaultValueEnabled: Boolean
        @JvmName("defaultValueEnabledProperty")
        get() = isDefaultValueEnabled()
        @JvmName("setDefaultValueEnabledProperty")
        set(value) = setDefaultValueEnabled(value)

    var defaultValue: Vector3
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

    fun setDefaultValue(value: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setDefaultValueBind, segment, value)
    }

    fun getDefaultValue(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getDefaultValueBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeVec3Parameter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VisualShaderNodeVec3Parameter? =
            if (handle.address() == 0L) null else VisualShaderNodeVec3Parameter(GodotHandle(handle))

        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        private val setDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec3Parameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        private val isDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec3Parameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val SET_DEFAULT_VALUE_HASH = 3460891852L
        private val setDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec3Parameter", "set_default_value", SET_DEFAULT_VALUE_HASH)
        }

        private const val GET_DEFAULT_VALUE_HASH = 3360562783L
        private val getDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeVec3Parameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
        }
    }
}
