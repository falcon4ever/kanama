package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: Range
 */
open class Range(handle: MemorySegment) : Control(handle) {
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

    fun getValue(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getValueBind, handle)
    }

    fun getMin(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMinBind, handle)
    }

    fun getMax(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxBind, handle)
    }

    fun getStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStepBind, handle)
    }

    fun getPage(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPageBind, handle)
    }

    fun getAsRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAsRatioBind, handle)
    }

    fun setValue(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setValueBind, handle, value)
    }

    fun setValueNoSignal(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setValueNoSignalBind, handle, value)
    }

    fun setMin(minimum: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMinBind, handle, minimum)
    }

    fun setMax(maximum: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxBind, handle, maximum)
    }

    fun setStep(step: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStepBind, handle, step)
    }

    fun setPage(pagesize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPageBind, handle, pagesize)
    }

    fun setAsRatio(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAsRatioBind, handle, value)
    }

    fun setUseRoundedValues(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseRoundedValuesBind, handle, enabled)
    }

    fun isUsingRoundedValues(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingRoundedValuesBind, handle)
    }

    fun setExpRatio(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setExpRatioBind, handle, enabled)
    }

    fun isRatioExp(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRatioExpBind, handle)
    }

    fun setAllowGreater(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowGreaterBind, handle, allow)
    }

    fun isGreaterAllowed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isGreaterAllowedBind, handle)
    }

    fun setAllowLesser(allow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAllowLesserBind, handle, allow)
    }

    fun isLesserAllowed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLesserAllowedBind, handle)
    }

    fun share(with: Node) {
        ObjectCalls.ptrcallWithObjectArgs(shareBind, handle, listOf(with.handle))
    }

    fun unshare() {
        ObjectCalls.ptrcallNoArgs(unshareBind, handle)
    }

    object Signals {
        const val valueChanged: String = "value_changed"
        const val changed: String = "changed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Range? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Range? =
            if (handle.address() == 0L) null else Range(handle)

        private const val GET_VALUE_HASH = 1740695150L
        private val getValueBind by lazy {
            ObjectCalls.getMethodBind("Range", "get_value", GET_VALUE_HASH)
        }

        private const val GET_MIN_HASH = 1740695150L
        private val getMinBind by lazy {
            ObjectCalls.getMethodBind("Range", "get_min", GET_MIN_HASH)
        }

        private const val GET_MAX_HASH = 1740695150L
        private val getMaxBind by lazy {
            ObjectCalls.getMethodBind("Range", "get_max", GET_MAX_HASH)
        }

        private const val GET_STEP_HASH = 1740695150L
        private val getStepBind by lazy {
            ObjectCalls.getMethodBind("Range", "get_step", GET_STEP_HASH)
        }

        private const val GET_PAGE_HASH = 1740695150L
        private val getPageBind by lazy {
            ObjectCalls.getMethodBind("Range", "get_page", GET_PAGE_HASH)
        }

        private const val GET_AS_RATIO_HASH = 1740695150L
        private val getAsRatioBind by lazy {
            ObjectCalls.getMethodBind("Range", "get_as_ratio", GET_AS_RATIO_HASH)
        }

        private const val SET_VALUE_HASH = 373806689L
        private val setValueBind by lazy {
            ObjectCalls.getMethodBind("Range", "set_value", SET_VALUE_HASH)
        }

        private const val SET_VALUE_NO_SIGNAL_HASH = 373806689L
        private val setValueNoSignalBind by lazy {
            ObjectCalls.getMethodBind("Range", "set_value_no_signal", SET_VALUE_NO_SIGNAL_HASH)
        }

        private const val SET_MIN_HASH = 373806689L
        private val setMinBind by lazy {
            ObjectCalls.getMethodBind("Range", "set_min", SET_MIN_HASH)
        }

        private const val SET_MAX_HASH = 373806689L
        private val setMaxBind by lazy {
            ObjectCalls.getMethodBind("Range", "set_max", SET_MAX_HASH)
        }

        private const val SET_STEP_HASH = 373806689L
        private val setStepBind by lazy {
            ObjectCalls.getMethodBind("Range", "set_step", SET_STEP_HASH)
        }

        private const val SET_PAGE_HASH = 373806689L
        private val setPageBind by lazy {
            ObjectCalls.getMethodBind("Range", "set_page", SET_PAGE_HASH)
        }

        private const val SET_AS_RATIO_HASH = 373806689L
        private val setAsRatioBind by lazy {
            ObjectCalls.getMethodBind("Range", "set_as_ratio", SET_AS_RATIO_HASH)
        }

        private const val SET_USE_ROUNDED_VALUES_HASH = 2586408642L
        private val setUseRoundedValuesBind by lazy {
            ObjectCalls.getMethodBind("Range", "set_use_rounded_values", SET_USE_ROUNDED_VALUES_HASH)
        }

        private const val IS_USING_ROUNDED_VALUES_HASH = 36873697L
        private val isUsingRoundedValuesBind by lazy {
            ObjectCalls.getMethodBind("Range", "is_using_rounded_values", IS_USING_ROUNDED_VALUES_HASH)
        }

        private const val SET_EXP_RATIO_HASH = 2586408642L
        private val setExpRatioBind by lazy {
            ObjectCalls.getMethodBind("Range", "set_exp_ratio", SET_EXP_RATIO_HASH)
        }

        private const val IS_RATIO_EXP_HASH = 36873697L
        private val isRatioExpBind by lazy {
            ObjectCalls.getMethodBind("Range", "is_ratio_exp", IS_RATIO_EXP_HASH)
        }

        private const val SET_ALLOW_GREATER_HASH = 2586408642L
        private val setAllowGreaterBind by lazy {
            ObjectCalls.getMethodBind("Range", "set_allow_greater", SET_ALLOW_GREATER_HASH)
        }

        private const val IS_GREATER_ALLOWED_HASH = 36873697L
        private val isGreaterAllowedBind by lazy {
            ObjectCalls.getMethodBind("Range", "is_greater_allowed", IS_GREATER_ALLOWED_HASH)
        }

        private const val SET_ALLOW_LESSER_HASH = 2586408642L
        private val setAllowLesserBind by lazy {
            ObjectCalls.getMethodBind("Range", "set_allow_lesser", SET_ALLOW_LESSER_HASH)
        }

        private const val IS_LESSER_ALLOWED_HASH = 36873697L
        private val isLesserAllowedBind by lazy {
            ObjectCalls.getMethodBind("Range", "is_lesser_allowed", IS_LESSER_ALLOWED_HASH)
        }

        private const val SHARE_HASH = 1078189570L
        private val shareBind by lazy {
            ObjectCalls.getMethodBind("Range", "share", SHARE_HASH)
        }

        private const val UNSHARE_HASH = 3218959716L
        private val unshareBind by lazy {
            ObjectCalls.getMethodBind("Range", "unshare", UNSHARE_HASH)
        }
    }
}
