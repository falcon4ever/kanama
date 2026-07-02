package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: VisualShaderNodeIntParameter
 */
class VisualShaderNodeIntParameter(handle: MemorySegment) : VisualShaderNodeParameter(handle) {
    var hint: Long
        @JvmName("hintProperty")
        get() = getHint()
        @JvmName("setHintProperty")
        set(value) = setHint(value)

    var min: Int
        @JvmName("minProperty")
        get() = getMin()
        @JvmName("setMinProperty")
        set(value) = setMin(value)

    var max: Int
        @JvmName("maxProperty")
        get() = getMax()
        @JvmName("setMaxProperty")
        set(value) = setMax(value)

    var step: Int
        @JvmName("stepProperty")
        get() = getStep()
        @JvmName("setStepProperty")
        set(value) = setStep(value)

    var enumNames: List<String>
        @JvmName("enumNamesProperty")
        get() = getEnumNames()
        @JvmName("setEnumNamesProperty")
        set(value) = setEnumNames(value)

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

    fun setHint(hint: Long) {
        ObjectCalls.ptrcallWithLongArg(setHintBind, handle, hint)
    }

    fun getHint(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHintBind, handle)
    }

    fun setMin(value: Int) {
        ObjectCalls.ptrcallWithIntArg(setMinBind, handle, value)
    }

    fun getMin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMinBind, handle)
    }

    fun setMax(value: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxBind, handle, value)
    }

    fun getMax(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxBind, handle)
    }

    fun setStep(value: Int) {
        ObjectCalls.ptrcallWithIntArg(setStepBind, handle, value)
    }

    fun getStep(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getStepBind, handle)
    }

    fun setEnumNames(names: List<String>) {
        ObjectCalls.ptrcallWithPackedStringListArg(setEnumNamesBind, handle, names)
    }

    fun getEnumNames(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getEnumNamesBind, handle)
    }

    fun setDefaultValueEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDefaultValueEnabledBind, handle, enabled)
    }

    fun isDefaultValueEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDefaultValueEnabledBind, handle)
    }

    fun setDefaultValue(value: Int) {
        ObjectCalls.ptrcallWithIntArg(setDefaultValueBind, handle, value)
    }

    fun getDefaultValue(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDefaultValueBind, handle)
    }

    companion object {
        const val HINT_NONE: Long = 0L
        const val HINT_RANGE: Long = 1L
        const val HINT_RANGE_STEP: Long = 2L
        const val HINT_ENUM: Long = 3L
        const val HINT_MAX: Long = 4L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): VisualShaderNodeIntParameter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VisualShaderNodeIntParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeIntParameter(handle)

        private const val SET_HINT_HASH = 2540512075L
        private val setHintBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_hint", SET_HINT_HASH)
        }

        private const val GET_HINT_HASH = 4250814924L
        private val getHintBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "get_hint", GET_HINT_HASH)
        }

        private const val SET_MIN_HASH = 1286410249L
        private val setMinBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_min", SET_MIN_HASH)
        }

        private const val GET_MIN_HASH = 3905245786L
        private val getMinBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "get_min", GET_MIN_HASH)
        }

        private const val SET_MAX_HASH = 1286410249L
        private val setMaxBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_max", SET_MAX_HASH)
        }

        private const val GET_MAX_HASH = 3905245786L
        private val getMaxBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "get_max", GET_MAX_HASH)
        }

        private const val SET_STEP_HASH = 1286410249L
        private val setStepBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_step", SET_STEP_HASH)
        }

        private const val GET_STEP_HASH = 3905245786L
        private val getStepBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "get_step", GET_STEP_HASH)
        }

        private const val SET_ENUM_NAMES_HASH = 4015028928L
        private val setEnumNamesBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_enum_names", SET_ENUM_NAMES_HASH)
        }

        private const val GET_ENUM_NAMES_HASH = 1139954409L
        private val getEnumNamesBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "get_enum_names", GET_ENUM_NAMES_HASH)
        }

        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        private val setDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        private val isDefaultValueEnabledBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)
        }

        private const val SET_DEFAULT_VALUE_HASH = 1286410249L
        private val setDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_default_value", SET_DEFAULT_VALUE_HASH)
        }

        private const val GET_DEFAULT_VALUE_HASH = 3905245786L
        private val getDefaultValueBind by lazy {
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
        }
    }
}
