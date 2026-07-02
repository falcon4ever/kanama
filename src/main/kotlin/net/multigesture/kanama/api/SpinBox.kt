package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * An input field for numbers.
 *
 * Generated from Godot docs: SpinBox
 */
class SpinBox(handle: MemorySegment) : Range(handle) {
    var alignment: Long
        @JvmName("alignmentProperty")
        get() = getHorizontalAlignment()
        @JvmName("setAlignmentProperty")
        set(value) = setHorizontalAlignment(value)

    var editable: Boolean
        @JvmName("editableProperty")
        get() = isEditable()
        @JvmName("setEditableProperty")
        set(value) = setEditable(value)

    var updateOnTextChanged: Boolean
        @JvmName("updateOnTextChangedProperty")
        get() = getUpdateOnTextChanged()
        @JvmName("setUpdateOnTextChangedProperty")
        set(value) = setUpdateOnTextChanged(value)

    var prefix: String
        @JvmName("prefixProperty")
        get() = getPrefix()
        @JvmName("setPrefixProperty")
        set(value) = setPrefix(value)

    var suffix: String
        @JvmName("suffixProperty")
        get() = getSuffix()
        @JvmName("setSuffixProperty")
        set(value) = setSuffix(value)

    var customArrowStep: Double
        @JvmName("customArrowStepProperty")
        get() = getCustomArrowStep()
        @JvmName("setCustomArrowStepProperty")
        set(value) = setCustomArrowStep(value)

    var customArrowRound: Boolean
        @JvmName("customArrowRoundProperty")
        get() = isCustomArrowRounding()
        @JvmName("setCustomArrowRoundProperty")
        set(value) = setCustomArrowRound(value)

    var selectAllOnFocus: Boolean
        @JvmName("selectAllOnFocusProperty")
        get() = isSelectAllOnFocus()
        @JvmName("setSelectAllOnFocusProperty")
        set(value) = setSelectAllOnFocus(value)

    fun setHorizontalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setHorizontalAlignmentBind, handle, alignment)
    }

    fun getHorizontalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHorizontalAlignmentBind, handle)
    }

    fun setSuffix(suffix: String) {
        ObjectCalls.ptrcallWithStringArg(setSuffixBind, handle, suffix)
    }

    fun getSuffix(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSuffixBind, handle)
    }

    fun setPrefix(prefix: String) {
        ObjectCalls.ptrcallWithStringArg(setPrefixBind, handle, prefix)
    }

    fun getPrefix(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPrefixBind, handle)
    }

    fun setEditable(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditableBind, handle, enabled)
    }

    fun setCustomArrowStep(arrowStep: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCustomArrowStepBind, handle, arrowStep)
    }

    fun getCustomArrowStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCustomArrowStepBind, handle)
    }

    fun setCustomArrowRound(round: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCustomArrowRoundBind, handle, round)
    }

    fun isCustomArrowRounding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCustomArrowRoundingBind, handle)
    }

    fun isEditable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditableBind, handle)
    }

    fun setUpdateOnTextChanged(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUpdateOnTextChangedBind, handle, enabled)
    }

    fun getUpdateOnTextChanged(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUpdateOnTextChangedBind, handle)
    }

    fun setSelectAllOnFocus(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSelectAllOnFocusBind, handle, enabled)
    }

    fun isSelectAllOnFocus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSelectAllOnFocusBind, handle)
    }

    /**
     * Applies the current value of this `SpinBox`. This is equivalent to pressing Enter while editing
     * the `LineEdit` used by the `SpinBox`. This will cause `LineEdit.text_submitted` to be emitted
     * and its currently contained expression to be evaluated.
     *
     * Generated from Godot docs: SpinBox.apply
     */
    fun apply() {
        ObjectCalls.ptrcallNoArgs(applyBind, handle)
    }

    fun getLineEdit(): LineEdit? {
        return LineEdit.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLineEditBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SpinBox? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SpinBox? =
            if (handle.address() == 0L) null else SpinBox(handle)

        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        private val setHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        private val getHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val SET_SUFFIX_HASH = 83702148L
        private val setSuffixBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "set_suffix", SET_SUFFIX_HASH)
        }

        private const val GET_SUFFIX_HASH = 201670096L
        private val getSuffixBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "get_suffix", GET_SUFFIX_HASH)
        }

        private const val SET_PREFIX_HASH = 83702148L
        private val setPrefixBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "set_prefix", SET_PREFIX_HASH)
        }

        private const val GET_PREFIX_HASH = 201670096L
        private val getPrefixBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "get_prefix", GET_PREFIX_HASH)
        }

        private const val SET_EDITABLE_HASH = 2586408642L
        private val setEditableBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "set_editable", SET_EDITABLE_HASH)
        }

        private const val SET_CUSTOM_ARROW_STEP_HASH = 373806689L
        private val setCustomArrowStepBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "set_custom_arrow_step", SET_CUSTOM_ARROW_STEP_HASH)
        }

        private const val GET_CUSTOM_ARROW_STEP_HASH = 1740695150L
        private val getCustomArrowStepBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "get_custom_arrow_step", GET_CUSTOM_ARROW_STEP_HASH)
        }

        private const val SET_CUSTOM_ARROW_ROUND_HASH = 2586408642L
        private val setCustomArrowRoundBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "set_custom_arrow_round", SET_CUSTOM_ARROW_ROUND_HASH)
        }

        private const val IS_CUSTOM_ARROW_ROUNDING_HASH = 36873697L
        private val isCustomArrowRoundingBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "is_custom_arrow_rounding", IS_CUSTOM_ARROW_ROUNDING_HASH)
        }

        private const val IS_EDITABLE_HASH = 36873697L
        private val isEditableBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "is_editable", IS_EDITABLE_HASH)
        }

        private const val SET_UPDATE_ON_TEXT_CHANGED_HASH = 2586408642L
        private val setUpdateOnTextChangedBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "set_update_on_text_changed", SET_UPDATE_ON_TEXT_CHANGED_HASH)
        }

        private const val GET_UPDATE_ON_TEXT_CHANGED_HASH = 36873697L
        private val getUpdateOnTextChangedBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "get_update_on_text_changed", GET_UPDATE_ON_TEXT_CHANGED_HASH)
        }

        private const val SET_SELECT_ALL_ON_FOCUS_HASH = 2586408642L
        private val setSelectAllOnFocusBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "set_select_all_on_focus", SET_SELECT_ALL_ON_FOCUS_HASH)
        }

        private const val IS_SELECT_ALL_ON_FOCUS_HASH = 36873697L
        private val isSelectAllOnFocusBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "is_select_all_on_focus", IS_SELECT_ALL_ON_FOCUS_HASH)
        }

        private const val APPLY_HASH = 3218959716L
        private val applyBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "apply", APPLY_HASH)
        }

        private const val GET_LINE_EDIT_HASH = 4071694264L
        private val getLineEditBind by lazy {
            ObjectCalls.getMethodBind("SpinBox", "get_line_edit", GET_LINE_EDIT_HASH)
        }
    }
}
