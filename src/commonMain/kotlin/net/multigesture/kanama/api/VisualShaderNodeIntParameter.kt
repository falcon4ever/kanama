package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A visual shader node for shader parameter (uniform) of type `int`.
 *
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

    /**
     * Range hint of this node. Use it to customize valid parameter range.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.set_hint
     */
    fun setHint(hint: VisualShaderNodeIntParameter.Hint) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setHintBind, segment, hint.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Range hint of this node. Use it to customize valid parameter range.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.get_hint
     */
    fun getHint(): VisualShaderNodeIntParameter.Hint {
        checkOpen()
        val ret = VisualShaderNodeIntParameter.Hint(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHintBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The minimum value this parameter can take. `hint` must be either `Hint.RANGE` or
     * `Hint.RANGE_STEP` for this to take effect.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.set_min
     */
    fun setMin(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMinBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The minimum value this parameter can take. `hint` must be either `Hint.RANGE` or
     * `Hint.RANGE_STEP` for this to take effect.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.get_min
     */
    fun getMin(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMinBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The maximum value this parameter can take. `hint` must be either `Hint.RANGE` or
     * `Hint.RANGE_STEP` for this to take effect.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.set_max
     */
    fun setMax(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The maximum value this parameter can take. `hint` must be either `Hint.RANGE` or
     * `Hint.RANGE_STEP` for this to take effect.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.get_max
     */
    fun getMax(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The step between parameter's values. Forces the parameter to be a multiple of the given value.
     * `hint` must be `Hint.RANGE_STEP` for this to take effect.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.set_step
     */
    fun setStep(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setStepBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The step between parameter's values. Forces the parameter to be a multiple of the given value.
     * `hint` must be `Hint.RANGE_STEP` for this to take effect.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.get_step
     */
    fun getStep(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getStepBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The names used for the enum select in the editor. `hint` must be `Hint.ENUM` for this to take
     * effect.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.set_enum_names
     */
    fun setEnumNames(names: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(Binds.setEnumNamesBind, segment, names)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The names used for the enum select in the editor. `hint` must be `Hint.ENUM` for this to take
     * effect.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.get_enum_names
     */
    fun getEnumNames(): List<String> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getEnumNamesBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, the node will have a custom default value.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.set_default_value_enabled
     */
    fun setDefaultValueEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDefaultValueEnabledBind, segment, enabled)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, the node will have a custom default value.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.is_default_value_enabled
     */
    fun isDefaultValueEnabled(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isDefaultValueEnabledBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Default value of this parameter, which will be used if not set externally.
     * `default_value_enabled` must be enabled; defaults to `0` otherwise.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.set_default_value
     */
    fun setDefaultValue(value: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setDefaultValueBind, segment, value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Default value of this parameter, which will be used if not set externally.
     * `default_value_enabled` must be enabled; defaults to `0` otherwise.
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.get_default_value
     */
    fun getDefaultValue(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getDefaultValueBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `VisualShaderNodeIntParameter.Hint` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`VisualShaderNodeIntParameter.Hint.<NAME>`).
     *
     * Generated from Godot docs: VisualShaderNodeIntParameter.Hint
     */
    @JvmInline
    value class Hint(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * The parameter will not constrain its value.
             *
             * Generated from Godot docs: VisualShaderNodeIntParameter.HINT_NONE
             */
            val NONE: Hint get() = Hint(0L)
            /**
             * The parameter's value must be within the specified `min`/`max` range.
             *
             * Generated from Godot docs: VisualShaderNodeIntParameter.HINT_RANGE
             */
            val RANGE: Hint get() = Hint(1L)
            /**
             * The parameter's value must be within the specified range, with the given `step` between values.
             *
             * Generated from Godot docs: VisualShaderNodeIntParameter.HINT_RANGE_STEP
             */
            val RANGE_STEP: Hint get() = Hint(2L)
            /**
             * The parameter uses an enum to associate preset values to names in the editor.
             *
             * Generated from Godot docs: VisualShaderNodeIntParameter.HINT_ENUM
             */
            val ENUM: Hint get() = Hint(3L)
            /**
             * Represents the size of the `Hint` enum.
             *
             * Generated from Godot docs: VisualShaderNodeIntParameter.HINT_MAX
             */
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

        /** A new VisualShaderNodeIntParameter, GDScript's `VisualShaderNodeIntParameter.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): VisualShaderNodeIntParameter =
            RefCounted.owned(VisualShaderNodeIntParameter(GodotHandle(ObjectCalls.constructObject("VisualShaderNodeIntParameter"))))
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
