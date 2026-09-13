package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * Abstract base class for controls that represent a number within a range.
 *
 * Generated from Godot docs: Range
 */
open class Range(handle: GodotHandle) : Control(handle) {
    var value: Double
        @JvmName("valueProperty")
        get() = getValue()
        @JvmName("setValueProperty")
        set(value) = setValue(value)

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

    var page: Double
        @JvmName("pageProperty")
        get() = getPage()
        @JvmName("setPageProperty")
        set(value) = setPage(value)

    var useRoundedValues: Boolean
        @JvmName("useRoundedValuesProperty")
        get() = isUsingRoundedValues()
        @JvmName("setUseRoundedValuesProperty")
        set(value) = setUseRoundedValues(value)

    var expRatio: Boolean
        @JvmName("expRatioProperty")
        get() = isRatioExp()
        @JvmName("setExpRatioProperty")
        set(value) = setExpRatio(value)

    var allowGreater: Boolean
        @JvmName("allowGreaterProperty")
        get() = isGreaterAllowed()
        @JvmName("setAllowGreaterProperty")
        set(value) = setAllowGreater(value)

    var allowLesser: Boolean
        @JvmName("allowLesserProperty")
        get() = isLesserAllowed()
        @JvmName("setAllowLesserProperty")
        set(value) = setAllowLesser(value)

    fun valueChanged(newValue: Double) {
        ObjectCalls.ptrcallWithDoubleArg(valueChangedBind, segment, newValue)
    }

    /**
     * Range's current value. Changing this property (even via code) will trigger `value_changed`
     * signal. Use `set_value_no_signal` if you want to avoid it.
     *
     * Generated from Godot docs: Range.get_value
     */
    fun getValue(): Double = ObjectCalls.ptrcallNoArgsRetDouble(getValueBind, segment)
    /**
     * Minimum value. Range is clamped if `value` is less than `min_value`.
     *
     * Generated from Godot docs: Range.get_min
     */
    fun getMin(): Double = ObjectCalls.ptrcallNoArgsRetDouble(getMinBind, segment)
    /**
     * Maximum value. Range is clamped if `value` is greater than `max_value`.
     *
     * Generated from Godot docs: Range.get_max
     */
    fun getMax(): Double = ObjectCalls.ptrcallNoArgsRetDouble(getMaxBind, segment)
    /**
     * If greater than `0.0`, `value` will always be rounded to a multiple of this property's value
     * above `min_value`. For example, if `min_value` is `0.1` and step is `0.2`, then `value` is
     * limited to `0.1`, `0.3`, `0.5`, and so on. If `rounded` is also `true`, `value` will first be
     * rounded to a multiple of this property's value, then rounded to the nearest integer.
     *
     * Generated from Godot docs: Range.get_step
     */
    fun getStep(): Double = ObjectCalls.ptrcallNoArgsRetDouble(getStepBind, segment)
    /**
     * Page size. Used mainly for `ScrollBar`. A `ScrollBar`'s grabber length is the `ScrollBar`'s size
     * multiplied by `page` over the difference between `min_value` and `max_value`.
     *
     * Generated from Godot docs: Range.get_page
     */
    fun getPage(): Double = ObjectCalls.ptrcallNoArgsRetDouble(getPageBind, segment)
    /**
     * The value mapped between 0 and 1.
     *
     * Generated from Godot docs: Range.get_as_ratio
     */
    fun getAsRatio(): Double = ObjectCalls.ptrcallNoArgsRetDouble(getAsRatioBind, segment)

    /**
     * Range's current value. Changing this property (even via code) will trigger `value_changed`
     * signal. Use `set_value_no_signal` if you want to avoid it.
     *
     * Generated from Godot docs: Range.set_value
     */
    fun setValue(value: Double) { ObjectCalls.ptrcallWithDoubleArg(setValueBind, segment, value) }
    /**
     * Sets the `Range`'s current value to the specified `value`, without emitting the `value_changed`
     * signal.
     *
     * Generated from Godot docs: Range.set_value_no_signal
     */
    fun setValueNoSignal(value: Double) { ObjectCalls.ptrcallWithDoubleArg(setValueNoSignalBind, segment, value) }
    /**
     * Minimum value. Range is clamped if `value` is less than `min_value`.
     *
     * Generated from Godot docs: Range.set_min
     */
    fun setMin(minimum: Double) { ObjectCalls.ptrcallWithDoubleArg(setMinBind, segment, minimum) }
    /**
     * Maximum value. Range is clamped if `value` is greater than `max_value`.
     *
     * Generated from Godot docs: Range.set_max
     */
    fun setMax(maximum: Double) { ObjectCalls.ptrcallWithDoubleArg(setMaxBind, segment, maximum) }
    /**
     * If greater than `0.0`, `value` will always be rounded to a multiple of this property's value
     * above `min_value`. For example, if `min_value` is `0.1` and step is `0.2`, then `value` is
     * limited to `0.1`, `0.3`, `0.5`, and so on. If `rounded` is also `true`, `value` will first be
     * rounded to a multiple of this property's value, then rounded to the nearest integer.
     *
     * Generated from Godot docs: Range.set_step
     */
    fun setStep(step: Double) { ObjectCalls.ptrcallWithDoubleArg(setStepBind, segment, step) }
    /**
     * Page size. Used mainly for `ScrollBar`. A `ScrollBar`'s grabber length is the `ScrollBar`'s size
     * multiplied by `page` over the difference between `min_value` and `max_value`.
     *
     * Generated from Godot docs: Range.set_page
     */
    fun setPage(pageSize: Double) { ObjectCalls.ptrcallWithDoubleArg(setPageBind, segment, pageSize) }
    /**
     * The value mapped between 0 and 1.
     *
     * Generated from Godot docs: Range.set_as_ratio
     */
    fun setAsRatio(value: Double) { ObjectCalls.ptrcallWithDoubleArg(setAsRatioBind, segment, value) }

    /**
     * If `true`, `value` will always be rounded to the nearest integer.
     *
     * Generated from Godot docs: Range.set_use_rounded_values
     */
    fun setUseRoundedValues(enabled: Boolean) { ObjectCalls.ptrcallWithBoolArg(setUseRoundedValuesBind, segment, enabled) }
    /**
     * If `true`, `value` will always be rounded to the nearest integer.
     *
     * Generated from Godot docs: Range.is_using_rounded_values
     */
    fun isUsingRoundedValues(): Boolean = ObjectCalls.ptrcallNoArgsRetBool(isUsingRoundedValuesBind, segment)
    /**
     * If `true`, and `min_value` is greater or equal to `0`, `value` will be represented exponentially
     * rather than linearly.
     *
     * Generated from Godot docs: Range.set_exp_ratio
     */
    fun setExpRatio(enabled: Boolean) { ObjectCalls.ptrcallWithBoolArg(setExpRatioBind, segment, enabled) }
    /**
     * If `true`, and `min_value` is greater or equal to `0`, `value` will be represented exponentially
     * rather than linearly.
     *
     * Generated from Godot docs: Range.is_ratio_exp
     */
    fun isRatioExp(): Boolean = ObjectCalls.ptrcallNoArgsRetBool(isRatioExpBind, segment)
    /**
     * If `true`, `value` may be greater than `max_value`.
     *
     * Generated from Godot docs: Range.set_allow_greater
     */
    fun setAllowGreater(allow: Boolean) { ObjectCalls.ptrcallWithBoolArg(setAllowGreaterBind, segment, allow) }
    /**
     * If `true`, `value` may be greater than `max_value`.
     *
     * Generated from Godot docs: Range.is_greater_allowed
     */
    fun isGreaterAllowed(): Boolean = ObjectCalls.ptrcallNoArgsRetBool(isGreaterAllowedBind, segment)
    /**
     * If `true`, `value` may be less than `min_value`.
     *
     * Generated from Godot docs: Range.set_allow_lesser
     */
    fun setAllowLesser(allow: Boolean) { ObjectCalls.ptrcallWithBoolArg(setAllowLesserBind, segment, allow) }
    /**
     * If `true`, `value` may be less than `min_value`.
     *
     * Generated from Godot docs: Range.is_lesser_allowed
     */
    fun isLesserAllowed(): Boolean = ObjectCalls.ptrcallNoArgsRetBool(isLesserAllowedBind, segment)

    /**
     * Binds two `Range`s together along with any ranges previously grouped with either of them. When
     * any of range's member variables change, it will share the new value with all other ranges in its
     * group.
     *
     * Generated from Godot docs: Range.share
     */
    fun share(with: Node) {
        ObjectCalls.ptrcallWithObjectArgs(shareBind, segment, listOf(with.segment))
    }

    /**
     * Stops the `Range` from sharing its member variables with any other.
     *
     * Generated from Godot docs: Range.unshare
     */
    fun unshare() {
        ObjectCalls.ptrcallNoArgs(unshareBind, segment)
    }

    object Signals {
        const val valueChanged: String = "value_changed"
        const val changed: String = "changed"
    }

    companion object {
        private const val FLOAT_VOID_HASH = 373806689L
        private const val NOARGS_FLOAT_HASH = 1740695150L
        private const val BOOL_VOID_HASH = 2586408642L
        private const val NOARGS_BOOL_HASH = 36873697L
        private const val SHARE_HASH = 1078189570L
        private const val NOARGS_VOID_HASH = 3218959716L

        private val valueChangedBind by lazy { ObjectCalls.getMethodBind("Range", "_value_changed", FLOAT_VOID_HASH) }
        private val getValueBind by lazy { ObjectCalls.getMethodBind("Range", "get_value", NOARGS_FLOAT_HASH) }
        private val getMinBind by lazy { ObjectCalls.getMethodBind("Range", "get_min", NOARGS_FLOAT_HASH) }
        private val getMaxBind by lazy { ObjectCalls.getMethodBind("Range", "get_max", NOARGS_FLOAT_HASH) }
        private val getStepBind by lazy { ObjectCalls.getMethodBind("Range", "get_step", NOARGS_FLOAT_HASH) }
        private val getPageBind by lazy { ObjectCalls.getMethodBind("Range", "get_page", NOARGS_FLOAT_HASH) }
        private val getAsRatioBind by lazy { ObjectCalls.getMethodBind("Range", "get_as_ratio", NOARGS_FLOAT_HASH) }
        private val setValueBind by lazy { ObjectCalls.getMethodBind("Range", "set_value", FLOAT_VOID_HASH) }
        private val setValueNoSignalBind by lazy { ObjectCalls.getMethodBind("Range", "set_value_no_signal", FLOAT_VOID_HASH) }
        private val setMinBind by lazy { ObjectCalls.getMethodBind("Range", "set_min", FLOAT_VOID_HASH) }
        private val setMaxBind by lazy { ObjectCalls.getMethodBind("Range", "set_max", FLOAT_VOID_HASH) }
        private val setStepBind by lazy { ObjectCalls.getMethodBind("Range", "set_step", FLOAT_VOID_HASH) }
        private val setPageBind by lazy { ObjectCalls.getMethodBind("Range", "set_page", FLOAT_VOID_HASH) }
        private val setAsRatioBind by lazy { ObjectCalls.getMethodBind("Range", "set_as_ratio", FLOAT_VOID_HASH) }
        private val setUseRoundedValuesBind by lazy { ObjectCalls.getMethodBind("Range", "set_use_rounded_values", BOOL_VOID_HASH) }
        private val isUsingRoundedValuesBind by lazy { ObjectCalls.getMethodBind("Range", "is_using_rounded_values", NOARGS_BOOL_HASH) }
        private val setExpRatioBind by lazy { ObjectCalls.getMethodBind("Range", "set_exp_ratio", BOOL_VOID_HASH) }
        private val isRatioExpBind by lazy { ObjectCalls.getMethodBind("Range", "is_ratio_exp", NOARGS_BOOL_HASH) }
        private val setAllowGreaterBind by lazy { ObjectCalls.getMethodBind("Range", "set_allow_greater", BOOL_VOID_HASH) }
        private val isGreaterAllowedBind by lazy { ObjectCalls.getMethodBind("Range", "is_greater_allowed", NOARGS_BOOL_HASH) }
        private val setAllowLesserBind by lazy { ObjectCalls.getMethodBind("Range", "set_allow_lesser", BOOL_VOID_HASH) }
        private val isLesserAllowedBind by lazy { ObjectCalls.getMethodBind("Range", "is_lesser_allowed", NOARGS_BOOL_HASH) }
        private val shareBind by lazy { ObjectCalls.getMethodBind("Range", "share", SHARE_HASH) }
        private val unshareBind by lazy { ObjectCalls.getMethodBind("Range", "unshare", NOARGS_VOID_HASH) }
    }
}
