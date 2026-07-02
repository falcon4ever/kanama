package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeFloatParameter
 */
class VisualShaderNodeFloatParameter(handle: MemorySegment) : VisualShaderNodeParameter(handle) {
    var hint: Long
        @JvmName("hintProperty")
        get() = getHint()
        @JvmName("setHintProperty")
        set(value) = setHint(value)

    var min: Double
        @JvmName("minProperty")
        get() = getMin()
        @JvmName("setMinProperty")
        set(value) = setMin(value)

    var max: Double
        @JvmName("maxProperty")
        get() = getMax()
        @JvmName("setMaxProperty")
        set(value) = setMax(value)

    var step: Double
        @JvmName("stepProperty")
        get() = getStep()
        @JvmName("setStepProperty")
        set(value) = setStep(value)

    var defaultValueEnabled: Boolean
        @JvmName("defaultValueEnabledProperty")
        get() = isDefaultValueEnabled()
        @JvmName("setDefaultValueEnabledProperty")
        set(value) = setDefaultValueEnabled(value)

    var defaultValue: Double
        @JvmName("defaultValueProperty")
        get() = getDefaultValue()
        @JvmName("setDefaultValueProperty")
        set(value) = setDefaultValue(value)

    fun setHint(hint: Long) {
        ObjectCalls.ptrcallWithLongArg(setHintBind, handle, hint)
    }

    fun getHint(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHintBind, handle)
    }

    fun setMin(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMinBind, handle, value)
    }

    fun getMin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinBind, handle)
    }

    fun setMax(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxBind, handle, value)
    }

    fun getMax(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxBind, handle)
    }

    fun setStep(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStepBind, handle, value)
    }

    fun getStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStepBind, handle)
    }

    fun setDefaultValueEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDefaultValueEnabledBind, handle, enabled)
    }

    fun isDefaultValueEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDefaultValueEnabledBind, handle)
    }

    fun setDefaultValue(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDefaultValueBind, handle, value)
    }

    fun getDefaultValue(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDefaultValueBind, handle)
    }

    companion object {
        const val HINT_NONE: Long = 0L
        const val HINT_RANGE: Long = 1L
        const val HINT_RANGE_STEP: Long = 2L
        const val HINT_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeFloatParameter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeFloatParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeFloatParameter(handle)

        private const val SET_HINT_HASH = 3712586466L
        private val setHintBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "set_hint", SET_HINT_HASH)
        }

        private const val GET_HINT_HASH = 3042240429L
        private val getHintBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "get_hint", GET_HINT_HASH)
        }

        private const val SET_MIN_HASH = 373806689L
        private val setMinBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "set_min", SET_MIN_HASH)
        }

        private const val GET_MIN_HASH = 1740695150L
        private val getMinBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "get_min", GET_MIN_HASH)
        }

        private const val SET_MAX_HASH = 373806689L
        private val setMaxBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "set_max", SET_MAX_HASH)
        }

        private const val GET_MAX_HASH = 1740695150L
        private val getMaxBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "get_max", GET_MAX_HASH)
        }

        private const val SET_STEP_HASH = 373806689L
        private val setStepBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "set_step", SET_STEP_HASH)
        }

        private const val GET_STEP_HASH = 1740695150L
        private val getStepBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "get_step", GET_STEP_HASH)
        }

        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        private val setDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        private val isDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val SET_DEFAULT_VALUE_HASH = 373806689L
        private val setDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "set_default_value", SET_DEFAULT_VALUE_HASH)
        }

        private const val GET_DEFAULT_VALUE_HASH = 1740695150L
        private val getDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
        }
    }
}
