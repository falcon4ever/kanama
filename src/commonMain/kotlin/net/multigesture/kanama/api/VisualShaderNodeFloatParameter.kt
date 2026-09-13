package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: VisualShaderNodeFloatParameter
 */
class VisualShaderNodeFloatParameter(handle: GodotHandle) : VisualShaderNodeParameter(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setHintBind, segment, hint)
    }

    fun getHint(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getHintBind, segment)
    }

    fun setMin(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setMinBind, segment, value)
    }

    fun getMin(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinBind, segment)
    }

    fun setMax(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setMaxBind, segment, value)
    }

    fun getMax(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxBind, segment)
    }

    fun setStep(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setStepBind, segment, value)
    }

    fun getStep(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getStepBind, segment)
    }

    fun setDefaultValueEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setDefaultValueEnabledBind, segment, enabled)
    }

    fun isDefaultValueEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isDefaultValueEnabledBind, segment)
    }

    fun setDefaultValue(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDefaultValueBind, segment, value)
    }

    fun getDefaultValue(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDefaultValueBind, segment)
    }

    companion object {
        const val HINT_NONE: Long = 0L
        const val HINT_RANGE: Long = 1L
        const val HINT_RANGE_STEP: Long = 2L
        const val HINT_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeFloatParameter? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeFloatParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeFloatParameter(GodotHandle(handle))

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
