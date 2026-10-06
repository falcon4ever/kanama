package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: VisualShaderNodeIntParameter
 */
class VisualShaderNodeIntParameter(handle: GodotHandle) : VisualShaderNodeParameter(handle) {
    var hint: VisualShaderNodeIntParameter.Hint
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

    fun setHint(hint: VisualShaderNodeIntParameter.Hint) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setHintBind, segment, hint.value)
    }

    fun getHint(): VisualShaderNodeIntParameter.Hint {
        checkOpen()
        return VisualShaderNodeIntParameter.Hint(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHintBind, segment))
    }

    fun setMin(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMinBind, segment, value)
    }

    fun getMin(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMinBind, segment)
    }

    fun setMax(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxBind, segment, value)
    }

    fun getMax(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxBind, segment)
    }

    fun setStep(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setStepBind, segment, value)
    }

    fun getStep(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getStepBind, segment)
    }

    fun setEnumNames(names: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(Binds.setEnumNamesBind, segment, names)
    }

    fun getEnumNames(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getEnumNamesBind, segment)
    }

    fun setDefaultValueEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDefaultValueEnabledBind, segment, enabled)
    }

    fun isDefaultValueEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDefaultValueEnabledBind, segment)
    }

    fun setDefaultValue(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setDefaultValueBind, segment, value)
    }

    fun getDefaultValue(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDefaultValueBind, segment)
    }

    @JvmInline
    value class Hint(override val value: Long) : GodotEnumValue {
        companion object {
            val NONE: Hint get() = Hint(0L)
            val RANGE: Hint get() = Hint(1L)
            val RANGE_STEP: Hint get() = Hint(2L)
            val ENUM: Hint get() = Hint(3L)
            val MAX: Hint get() = Hint(4L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VisualShaderNodeIntParameter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): VisualShaderNodeIntParameter? =
            if (handle.address() == 0L) null else RefCounted.owned(VisualShaderNodeIntParameter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): VisualShaderNodeIntParameter? =
            if (handle.address() == 0L) null else VisualShaderNodeIntParameter(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_HINT_HASH = 2540512075L
        @JvmField
        val setHintBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_hint", SET_HINT_HASH)

        private const val GET_HINT_HASH = 4250814924L
        @JvmField
        val getHintBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "get_hint", GET_HINT_HASH)

        private const val SET_MIN_HASH = 1286410249L
        @JvmField
        val setMinBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_min", SET_MIN_HASH)

        private const val GET_MIN_HASH = 3905245786L
        @JvmField
        val getMinBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "get_min", GET_MIN_HASH)

        private const val SET_MAX_HASH = 1286410249L
        @JvmField
        val setMaxBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_max", SET_MAX_HASH)

        private const val GET_MAX_HASH = 3905245786L
        @JvmField
        val getMaxBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "get_max", GET_MAX_HASH)

        private const val SET_STEP_HASH = 1286410249L
        @JvmField
        val setStepBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_step", SET_STEP_HASH)

        private const val GET_STEP_HASH = 3905245786L
        @JvmField
        val getStepBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "get_step", GET_STEP_HASH)

        private const val SET_ENUM_NAMES_HASH = 4015028928L
        @JvmField
        val setEnumNamesBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_enum_names", SET_ENUM_NAMES_HASH)

        private const val GET_ENUM_NAMES_HASH = 1139954409L
        @JvmField
        val getEnumNamesBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "get_enum_names", GET_ENUM_NAMES_HASH)

        private const val SET_DEFAULT_VALUE_ENABLED_HASH = 2586408642L
        @JvmField
        val setDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_default_value_enabled", SET_DEFAULT_VALUE_ENABLED_HASH)

        private const val IS_DEFAULT_VALUE_ENABLED_HASH = 36873697L
        @JvmField
        val isDefaultValueEnabledBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "is_default_value_enabled", IS_DEFAULT_VALUE_ENABLED_HASH)

        private const val SET_DEFAULT_VALUE_HASH = 1286410249L
        @JvmField
        val setDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "set_default_value", SET_DEFAULT_VALUE_HASH)

        private const val GET_DEFAULT_VALUE_HASH = 3905245786L
        @JvmField
        val getDefaultValueBind =
            ObjectCalls.getMethodBind("VisualShaderNodeIntParameter", "get_default_value", GET_DEFAULT_VALUE_HASH)
    }
}
