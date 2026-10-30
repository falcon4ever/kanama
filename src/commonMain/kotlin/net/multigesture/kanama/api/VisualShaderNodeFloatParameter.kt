package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A scalar float parameter to be used within the visual shader graph.
 *
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

    /**
     * A hint applied to the uniform, which controls the values it can take when set through the
     * Inspector.
     *
     * Generated from Godot docs: VisualShaderNodeFloatParameter.set_hint
     */
    fun setHint(hint: VisualShaderNodeFloatParameter.Hint) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setHintBind, segment, hint.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A hint applied to the uniform, which controls the values it can take when set through the
     * Inspector.
     *
     * Generated from Godot docs: VisualShaderNodeFloatParameter.get_hint
     */
    fun getHint(): VisualShaderNodeFloatParameter.Hint {
        checkOpen()
        val ret = VisualShaderNodeFloatParameter.Hint(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHintBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Maximum value for range hints. Used if `hint` is set to `Hint.RANGE` or `Hint.RANGE_STEP`.
     *
     * Generated from Godot docs: VisualShaderNodeFloatParameter.set_min
     */
    fun setMin(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMinBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Maximum value for range hints. Used if `hint` is set to `Hint.RANGE` or `Hint.RANGE_STEP`.
     *
     * Generated from Godot docs: VisualShaderNodeFloatParameter.get_min
     */
    fun getMin(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMinBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Minimum value for range hints. Used if `hint` is set to `Hint.RANGE` or `Hint.RANGE_STEP`.
     *
     * Generated from Godot docs: VisualShaderNodeFloatParameter.set_max
     */
    fun setMax(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMaxBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Minimum value for range hints. Used if `hint` is set to `Hint.RANGE` or `Hint.RANGE_STEP`.
     *
     * Generated from Godot docs: VisualShaderNodeFloatParameter.get_max
     */
    fun getMax(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Step (increment) value for the range hint with step. Used if `hint` is set to `Hint.RANGE_STEP`.
     *
     * Generated from Godot docs: VisualShaderNodeFloatParameter.set_step
     */
    fun setStep(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setStepBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Step (increment) value for the range hint with step. Used if `hint` is set to `Hint.RANGE_STEP`.
     *
     * Generated from Godot docs: VisualShaderNodeFloatParameter.get_step
     */
    fun getStep(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getStepBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Enables usage of the `default_value`.
     *
     * Generated from Godot docs: VisualShaderNodeFloatParameter.set_default_value_enabled
     */
    fun setDefaultValueEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDefaultValueEnabledBind, segment, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Enables usage of the `default_value`.
     *
     * Generated from Godot docs: VisualShaderNodeFloatParameter.is_default_value_enabled
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
     * Generated from Godot docs: VisualShaderNodeFloatParameter.set_default_value
     */
    fun setDefaultValue(value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDefaultValueBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * A default value to be assigned within the shader.
     *
     * Generated from Godot docs: VisualShaderNodeFloatParameter.get_default_value
     */
    fun getDefaultValue(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDefaultValueBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeFloatParameter.Hint` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeFloatParameter.Hint.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeFloatParameter.Hint
     */
    @JvmInline
    value class Hint(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No hint used.
             *
             * Generated from Godot docs: VisualShaderNodeFloatParameter.HINT_NONE
             */
            val NONE: Hint get() = Hint(0L)
            /**
             * A range hint for scalar value, which limits possible input values between `min` and `max`.
             * Translated to `hint_range(min, max)` in shader code.
             *
             * Generated from Godot docs: VisualShaderNodeFloatParameter.HINT_RANGE
             */
            val RANGE: Hint get() = Hint(1L)
            /**
             * A range hint for scalar value with step, which limits possible input values between `min` and
             * `max`, with a step (increment) of `step`). Translated to `hint_range(min, max, step)` in shader
             * code.
             *
             * Generated from Godot docs: VisualShaderNodeFloatParameter.HINT_RANGE_STEP
             */
            val RANGE_STEP: Hint get() = Hint(2L)
            /**
             * Represents the size of the `Hint` enum.
             *
             * Generated from Godot docs: VisualShaderNodeFloatParameter.HINT_MAX
             */
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

        /** A new VisualShaderNodeFloatParameter, GDScript's `VisualShaderNodeFloatParameter.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeFloatParameter =
            RefCounted.owned(VisualShaderNodeFloatParameter(GodotHandle(requireConstructed(ObjectCalls.constructObject("VisualShaderNodeFloatParameter"), "VisualShaderNodeFloatParameter"))))
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
