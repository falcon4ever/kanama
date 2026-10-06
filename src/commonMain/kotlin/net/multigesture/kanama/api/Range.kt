package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Abstract base class for controls that represent a number within a range.
 *
 * Generated from Godot docs: Range
 */
open class Range(handle: GodotHandle) : Control(handle) {
    var minValue: Double
        @JvmName("minValueProperty")
        get() = getMin()
        @JvmName("setMinValueProperty")
        set(value) = setMin(value)

    var maxValue: Double
        @JvmName("maxValueProperty")
        get() = getMax()
        @JvmName("setMaxValueProperty")
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

    var value: Double
        @JvmName("valueProperty")
        get() = getValue()
        @JvmName("setValueProperty")
        set(value) = setValue(value)

    var ratio: Double
        @JvmName("ratioProperty")
        get() = getAsRatio()
        @JvmName("setRatioProperty")
        set(value) = setAsRatio(value)

    var expEdit: Boolean
        @JvmName("expEditProperty")
        get() = isRatioExp()
        @JvmName("setExpEditProperty")
        set(value) = setExpRatio(value)

    var rounded: Boolean
        @JvmName("roundedProperty")
        get() = isUsingRoundedValues()
        @JvmName("setRoundedProperty")
        set(value) = setUseRoundedValues(value)

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

    /**
     * Range's current value. Changing this property (even via code) will trigger `value_changed`
     * signal. Use `set_value_no_signal` if you want to avoid it.
     *
     * Generated from Godot docs: Range.get_value
     */
    fun getValue(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getValueBind, segment)
    }

    /**
     * Minimum value. Range is clamped if `value` is less than `min_value`.
     *
     * Generated from Godot docs: Range.get_min
     */
    fun getMin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMinBind, segment)
    }

    /**
     * Maximum value. Range is clamped if `value` is greater than `max_value`.
     *
     * Generated from Godot docs: Range.get_max
     */
    fun getMax(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxBind, segment)
    }

    /**
     * If greater than `0.0`, `value` will always be rounded to a multiple of this property's value
     * above `min_value`. For example, if `min_value` is `0.1` and step is `0.2`, then `value` is
     * limited to `0.1`, `0.3`, `0.5`, and so on. If `rounded` is also `true`, `value` will first be
     * rounded to a multiple of this property's value, then rounded to the nearest integer.
     *
     * Generated from Godot docs: Range.get_step
     */
    fun getStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getStepBind, segment)
    }

    /**
     * Page size. Used mainly for `ScrollBar`. A `ScrollBar`'s grabber length is the `ScrollBar`'s size
     * multiplied by `page` over the difference between `min_value` and `max_value`.
     *
     * Generated from Godot docs: Range.get_page
     */
    fun getPage(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPageBind, segment)
    }

    /**
     * The value mapped between 0 and 1.
     *
     * Generated from Godot docs: Range.get_as_ratio
     */
    fun getAsRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAsRatioBind, segment)
    }

    /**
     * Range's current value. Changing this property (even via code) will trigger `value_changed`
     * signal. Use `set_value_no_signal` if you want to avoid it.
     *
     * Generated from Godot docs: Range.set_value
     */
    fun setValue(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setValueBind, segment, value)
    }

    /**
     * Sets the `Range`'s current value to the specified `value`, without emitting the `value_changed`
     * signal.
     *
     * Generated from Godot docs: Range.set_value_no_signal
     */
    fun setValueNoSignal(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setValueNoSignalBind, segment, value)
    }

    /**
     * Minimum value. Range is clamped if `value` is less than `min_value`.
     *
     * Generated from Godot docs: Range.set_min
     */
    fun setMin(minimum: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMinBind, segment, minimum)
    }

    /**
     * Maximum value. Range is clamped if `value` is greater than `max_value`.
     *
     * Generated from Godot docs: Range.set_max
     */
    fun setMax(maximum: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMaxBind, segment, maximum)
    }

    /**
     * If greater than `0.0`, `value` will always be rounded to a multiple of this property's value
     * above `min_value`. For example, if `min_value` is `0.1` and step is `0.2`, then `value` is
     * limited to `0.1`, `0.3`, `0.5`, and so on. If `rounded` is also `true`, `value` will first be
     * rounded to a multiple of this property's value, then rounded to the nearest integer.
     *
     * Generated from Godot docs: Range.set_step
     */
    fun setStep(step: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setStepBind, segment, step)
    }

    /**
     * Page size. Used mainly for `ScrollBar`. A `ScrollBar`'s grabber length is the `ScrollBar`'s size
     * multiplied by `page` over the difference between `min_value` and `max_value`.
     *
     * Generated from Godot docs: Range.set_page
     */
    fun setPage(pagesize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPageBind, segment, pagesize)
    }

    /**
     * The value mapped between 0 and 1.
     *
     * Generated from Godot docs: Range.set_as_ratio
     */
    fun setAsRatio(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAsRatioBind, segment, value)
    }

    /**
     * If `true`, `value` will always be rounded to the nearest integer.
     *
     * Generated from Godot docs: Range.set_use_rounded_values
     */
    fun setUseRoundedValues(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseRoundedValuesBind, segment, enabled)
    }

    /**
     * If `true`, `value` will always be rounded to the nearest integer.
     *
     * Generated from Godot docs: Range.is_using_rounded_values
     */
    fun isUsingRoundedValues(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingRoundedValuesBind, segment)
    }

    /**
     * If `true`, and `min_value` is greater or equal to `0`, `value` will be represented exponentially
     * rather than linearly.
     *
     * Generated from Godot docs: Range.set_exp_ratio
     */
    fun setExpRatio(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setExpRatioBind, segment, enabled)
    }

    /**
     * If `true`, and `min_value` is greater or equal to `0`, `value` will be represented exponentially
     * rather than linearly.
     *
     * Generated from Godot docs: Range.is_ratio_exp
     */
    fun isRatioExp(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isRatioExpBind, segment)
    }

    /**
     * If `true`, `value` may be greater than `max_value`.
     *
     * Generated from Godot docs: Range.set_allow_greater
     */
    fun setAllowGreater(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAllowGreaterBind, segment, allow)
    }

    /**
     * If `true`, `value` may be greater than `max_value`.
     *
     * Generated from Godot docs: Range.is_greater_allowed
     */
    fun isGreaterAllowed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isGreaterAllowedBind, segment)
    }

    /**
     * If `true`, `value` may be less than `min_value`.
     *
     * Generated from Godot docs: Range.set_allow_lesser
     */
    fun setAllowLesser(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAllowLesserBind, segment, allow)
    }

    /**
     * If `true`, `value` may be less than `min_value`.
     *
     * Generated from Godot docs: Range.is_lesser_allowed
     */
    fun isLesserAllowed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isLesserAllowedBind, segment)
    }

    /**
     * Binds two `Range`s together along with any ranges previously grouped with either of them. When
     * any of range's member variables change, it will share the new value with all other ranges in its
     * group.
     *
     * Generated from Godot docs: Range.share
     */
    fun share(with: Node) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.shareBind, segment, listOf(with.segment))
    }

    /**
     * Stops the `Range` from sharing its member variables with any other.
     *
     * Generated from Godot docs: Range.unshare
     */
    fun unshare() {
        ObjectCalls.ptrcallNoArgs(Binds.unshareBind, segment)
    }

    /** Signal `value_changed(value: float)`; see [TypedSignal]. */
    val valueChanged: Signal1<Double>
        @JvmName("valueChangedTypedSignal")
        get() = Signal1(this, "value_changed", SignalArgType.DOUBLE)

    /** Signal `changed()`; see [TypedSignal]. */
    val changed: Signal0
        @JvmName("changedTypedSignal")
        get() = Signal0(this, "changed")

    object Signals {
        const val valueChanged: String = "value_changed"
        const val changed: String = "changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Range? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Range? =
            if (handle.address() == 0L) null else Range(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_VALUE_HASH = 1740695150L
        @JvmField
        val getValueBind =
            ObjectCalls.getMethodBind("Range", "get_value", GET_VALUE_HASH)

        private const val GET_MIN_HASH = 1740695150L
        @JvmField
        val getMinBind =
            ObjectCalls.getMethodBind("Range", "get_min", GET_MIN_HASH)

        private const val GET_MAX_HASH = 1740695150L
        @JvmField
        val getMaxBind =
            ObjectCalls.getMethodBind("Range", "get_max", GET_MAX_HASH)

        private const val GET_STEP_HASH = 1740695150L
        @JvmField
        val getStepBind =
            ObjectCalls.getMethodBind("Range", "get_step", GET_STEP_HASH)

        private const val GET_PAGE_HASH = 1740695150L
        @JvmField
        val getPageBind =
            ObjectCalls.getMethodBind("Range", "get_page", GET_PAGE_HASH)

        private const val GET_AS_RATIO_HASH = 1740695150L
        @JvmField
        val getAsRatioBind =
            ObjectCalls.getMethodBind("Range", "get_as_ratio", GET_AS_RATIO_HASH)

        private const val SET_VALUE_HASH = 373806689L
        @JvmField
        val setValueBind =
            ObjectCalls.getMethodBind("Range", "set_value", SET_VALUE_HASH)

        private const val SET_VALUE_NO_SIGNAL_HASH = 373806689L
        @JvmField
        val setValueNoSignalBind =
            ObjectCalls.getMethodBind("Range", "set_value_no_signal", SET_VALUE_NO_SIGNAL_HASH)

        private const val SET_MIN_HASH = 373806689L
        @JvmField
        val setMinBind =
            ObjectCalls.getMethodBind("Range", "set_min", SET_MIN_HASH)

        private const val SET_MAX_HASH = 373806689L
        @JvmField
        val setMaxBind =
            ObjectCalls.getMethodBind("Range", "set_max", SET_MAX_HASH)

        private const val SET_STEP_HASH = 373806689L
        @JvmField
        val setStepBind =
            ObjectCalls.getMethodBind("Range", "set_step", SET_STEP_HASH)

        private const val SET_PAGE_HASH = 373806689L
        @JvmField
        val setPageBind =
            ObjectCalls.getMethodBind("Range", "set_page", SET_PAGE_HASH)

        private const val SET_AS_RATIO_HASH = 373806689L
        @JvmField
        val setAsRatioBind =
            ObjectCalls.getMethodBind("Range", "set_as_ratio", SET_AS_RATIO_HASH)

        private const val SET_USE_ROUNDED_VALUES_HASH = 2586408642L
        @JvmField
        val setUseRoundedValuesBind =
            ObjectCalls.getMethodBind("Range", "set_use_rounded_values", SET_USE_ROUNDED_VALUES_HASH)

        private const val IS_USING_ROUNDED_VALUES_HASH = 36873697L
        @JvmField
        val isUsingRoundedValuesBind =
            ObjectCalls.getMethodBind("Range", "is_using_rounded_values", IS_USING_ROUNDED_VALUES_HASH)

        private const val SET_EXP_RATIO_HASH = 2586408642L
        @JvmField
        val setExpRatioBind =
            ObjectCalls.getMethodBind("Range", "set_exp_ratio", SET_EXP_RATIO_HASH)

        private const val IS_RATIO_EXP_HASH = 36873697L
        @JvmField
        val isRatioExpBind =
            ObjectCalls.getMethodBind("Range", "is_ratio_exp", IS_RATIO_EXP_HASH)

        private const val SET_ALLOW_GREATER_HASH = 2586408642L
        @JvmField
        val setAllowGreaterBind =
            ObjectCalls.getMethodBind("Range", "set_allow_greater", SET_ALLOW_GREATER_HASH)

        private const val IS_GREATER_ALLOWED_HASH = 36873697L
        @JvmField
        val isGreaterAllowedBind =
            ObjectCalls.getMethodBind("Range", "is_greater_allowed", IS_GREATER_ALLOWED_HASH)

        private const val SET_ALLOW_LESSER_HASH = 2586408642L
        @JvmField
        val setAllowLesserBind =
            ObjectCalls.getMethodBind("Range", "set_allow_lesser", SET_ALLOW_LESSER_HASH)

        private const val IS_LESSER_ALLOWED_HASH = 36873697L
        @JvmField
        val isLesserAllowedBind =
            ObjectCalls.getMethodBind("Range", "is_lesser_allowed", IS_LESSER_ALLOWED_HASH)

        private const val SHARE_HASH = 1078189570L
        @JvmField
        val shareBind =
            ObjectCalls.getMethodBind("Range", "share", SHARE_HASH)

        private const val UNSHARE_HASH = 3218959716L
        @JvmField
        val unshareBind =
            ObjectCalls.getMethodBind("Range", "unshare", UNSHARE_HASH)
    }
}
