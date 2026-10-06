package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeFloatParameter
 */
class VisualShaderNodeFloatParameter(handle: GodotHandle) : VisualShaderNodeParameter(handle) {
    var hint: VisualShaderNodeFloatParameter.Hint
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

    fun setHint(hint: VisualShaderNodeFloatParameter.Hint) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setHintBind, segment, hint.value)
    }

    fun getHint(): VisualShaderNodeFloatParameter.Hint {
        checkOpen()
        return VisualShaderNodeFloatParameter.Hint(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHintBind, segment))
    }

    fun setMin(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMinBind, segment, value)
    }

    fun getMin(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMinBind, segment)
    }

    fun setMax(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMaxBind, segment, value)
    }

    fun getMax(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxBind, segment)
    }

    fun setStep(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setStepBind, segment, value)
    }

    fun getStep(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getStepBind, segment)
    }

    fun setDefaultValueEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDefaultValueEnabledBind, segment, enabled)
    }

    fun isDefaultValueEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDefaultValueEnabledBind, segment)
    }

    fun setDefaultValue(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDefaultValueBind, segment, value)
    }

    fun getDefaultValue(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDefaultValueBind, segment)
    }

    @JvmInline
    value class Hint(override val value: Long) : GodotEnumValue {
        companion object {
            val NONE: Hint get() = Hint(0L)
            val RANGE: Hint get() = Hint(1L)
            val RANGE_STEP: Hint get() = Hint(2L)
            val MAX: Hint get() = Hint(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeFloatParameter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeFloatParameter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeFloatParameter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeFloatParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeFloatParameter(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_HINT_HASH = 3712586466L
        @JvmField
        val setHintBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "set_hint", SET_HINT_HASH)

        private const val GET_HINT_HASH = 3042240429L
        @JvmField
        val getHintBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "get_hint", GET_HINT_HASH)

        private const val SET_MIN_HASH = 373806689L
        @JvmField
        val setMinBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "set_min", SET_MIN_HASH)

        private const val GET_MIN_HASH = 1740695150L
        @JvmField
        val getMinBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "get_min", GET_MIN_HASH)

        private const val SET_MAX_HASH = 373806689L
        @JvmField
        val setMaxBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "set_max", SET_MAX_HASH)

        private const val GET_MAX_HASH = 1740695150L
        @JvmField
        val getMaxBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "get_max", GET_MAX_HASH)

        private const val SET_STEP_HASH = 373806689L
        @JvmField
        val setStepBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "set_step", SET_STEP_HASH)

        private const val GET_STEP_HASH = 1740695150L
        @JvmField
        val getStepBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "get_step", GET_STEP_HASH)

        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        @JvmField
        val setDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        @JvmField
        val isDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)

        private const val SET_DEFAULT_VALUE_HASH = 373806689L
        @JvmField
        val setDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "set_default_value", SET_DEFAULT_VALUE_HASH)

        private const val GET_DEFAULT_VALUE_HASH = 1740695150L
        @JvmField
        val getDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeFloatParameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
    }
}
