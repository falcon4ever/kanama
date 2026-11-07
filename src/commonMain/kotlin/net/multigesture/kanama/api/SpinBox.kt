package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * An input field for numbers.
 *
 * Generated from Godot docs: SpinBox
 */
class SpinBox(handle: GodotHandle) : Range(handle) {
    var alignment: HorizontalAlignment
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

    var format: String
        @JvmName("formatProperty")
        get() = getFormat()
        @JvmName("setFormatProperty")
        set(value) = setFormat(value)

    var pluralFormat: String
        @JvmName("pluralFormatProperty")
        get() = getPluralFormat()
        @JvmName("setPluralFormatProperty")
        set(value) = setPluralFormat(value)

    var formatAutoTranslateMode: Node.AutoTranslateMode
        @JvmName("formatAutoTranslateModeProperty")
        get() = getFormatAutoTranslateMode()
        @JvmName("setFormatAutoTranslateModeProperty")
        set(value) = setFormatAutoTranslateMode(value)

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

    /**
     * Changes the alignment of the underlying `LineEdit`.
     *
     * Generated from Godot docs: SpinBox.set_horizontal_alignment
     */
    fun setHorizontalAlignment(alignment: HorizontalAlignment) {
        ObjectCalls.ptrcallWithLongArg(Binds.setHorizontalAlignmentBind, segment, alignment.value)
    }

    /**
     * Changes the alignment of the underlying `LineEdit`.
     *
     * Generated from Godot docs: SpinBox.get_horizontal_alignment
     */
    fun getHorizontalAlignment(): HorizontalAlignment {
        return HorizontalAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHorizontalAlignmentBind, segment))
    }

    /**
     * The formatting of the displayed numeric value, using the same rules as `String`'s `%` operator
     * (see GDScript format strings
     * ($DOCS_URL/tutorials/scripting/gdscript/gdscript_format_string.html)). If empty, the value is
     * displayed with no special formatting. If the format string is invalid, the `SpinBox` will
     * display `"ERROR"`, with the error message available as a configuration warning. The following
     * format strings can be used as examples: - `"(%s)"` displays as a number in parentheses (e.g.
     * `(6.9)`). - `"%d ms"` displays as an integer with milliseconds unit (e.g. `420 ms`). -
     * `"%0.2f%%"` displays as a percentage, with 2 decimal places of precision (e.g. `21.37%`).
     *
     * Generated from Godot docs: SpinBox.set_format
     */
    fun setFormat(format: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setFormatBind, segment, format)
    }

    /**
     * The formatting of the displayed numeric value, using the same rules as `String`'s `%` operator
     * (see GDScript format strings
     * ($DOCS_URL/tutorials/scripting/gdscript/gdscript_format_string.html)). If empty, the value is
     * displayed with no special formatting. If the format string is invalid, the `SpinBox` will
     * display `"ERROR"`, with the error message available as a configuration warning. The following
     * format strings can be used as examples: - `"(%s)"` displays as a number in parentheses (e.g.
     * `(6.9)`). - `"%d ms"` displays as an integer with milliseconds unit (e.g. `420 ms`). -
     * `"%0.2f%%"` displays as a percentage, with 2 decimal places of precision (e.g. `21.37%`).
     *
     * Generated from Godot docs: SpinBox.get_format
     */
    fun getFormat(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getFormatBind, segment)
    }

    /**
     * Plural version of `format`. Used only if `format` is not empty and `format_auto_translate_mode`
     * is not disabled. See also `Object.tr_n`.
     *
     * Generated from Godot docs: SpinBox.set_plural_format
     */
    fun setPluralFormat(format: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setPluralFormatBind, segment, format)
    }

    /**
     * Plural version of `format`. Used only if `format` is not empty and `format_auto_translate_mode`
     * is not disabled. See also `Object.tr_n`.
     *
     * Generated from Godot docs: SpinBox.get_plural_format
     */
    fun getPluralFormat(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getPluralFormatBind, segment)
    }

    /**
     * Auto-translation mode for the `format`. Useful when the format has a word that can be
     * translated.
     *
     * Generated from Godot docs: SpinBox.set_format_auto_translate_mode
     */
    fun setFormatAutoTranslateMode(mode: Node.AutoTranslateMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setFormatAutoTranslateModeBind, segment, mode.value)
    }

    /**
     * Auto-translation mode for the `format`. Useful when the format has a word that can be
     * translated.
     *
     * Generated from Godot docs: SpinBox.get_format_auto_translate_mode
     */
    fun getFormatAutoTranslateMode(): Node.AutoTranslateMode {
        return Node.AutoTranslateMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFormatAutoTranslateModeBind, segment))
    }

    /**
     * Adds the specified suffix string after the numerical value of the `SpinBox`. This is used only
     * if `format` is empty.
     *
     * Generated from Godot docs: SpinBox.set_suffix
     */
    fun setSuffix(suffix: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setSuffixBind, segment, suffix)
    }

    /**
     * Adds the specified suffix string after the numerical value of the `SpinBox`. This is used only
     * if `format` is empty.
     *
     * Generated from Godot docs: SpinBox.get_suffix
     */
    fun getSuffix(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getSuffixBind, segment)
    }

    /**
     * Adds the specified prefix string before the numerical value of the `SpinBox`. This is used only
     * if `format` is empty.
     *
     * Generated from Godot docs: SpinBox.set_prefix
     */
    fun setPrefix(prefix: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setPrefixBind, segment, prefix)
    }

    /**
     * Adds the specified prefix string before the numerical value of the `SpinBox`. This is used only
     * if `format` is empty.
     *
     * Generated from Godot docs: SpinBox.get_prefix
     */
    fun getPrefix(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getPrefixBind, segment)
    }

    /**
     * If `true`, the `SpinBox` will be editable. Otherwise, it will be read only.
     *
     * Generated from Godot docs: SpinBox.set_editable
     */
    fun setEditable(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEditableBind, segment, enabled)
    }

    /**
     * If not `0`, sets the step when interacting with the arrow buttons of the `SpinBox`. Note:
     * `Range.value` will still be rounded to a multiple of `Range.step`.
     *
     * Generated from Godot docs: SpinBox.set_custom_arrow_step
     */
    fun setCustomArrowStep(arrowStep: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCustomArrowStepBind, segment, arrowStep)
    }

    /**
     * If not `0`, sets the step when interacting with the arrow buttons of the `SpinBox`. Note:
     * `Range.value` will still be rounded to a multiple of `Range.step`.
     *
     * Generated from Godot docs: SpinBox.get_custom_arrow_step
     */
    fun getCustomArrowStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCustomArrowStepBind, segment)
    }

    /**
     * If `true`, the value will be rounded to a multiple of `custom_arrow_step` when interacting with
     * the arrow buttons. Otherwise, increments the value by `custom_arrow_step` and then rounds it
     * according to `Range.step`.
     *
     * Generated from Godot docs: SpinBox.set_custom_arrow_round
     */
    fun setCustomArrowRound(round: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCustomArrowRoundBind, segment, round)
    }

    /**
     * If `true`, the value will be rounded to a multiple of `custom_arrow_step` when interacting with
     * the arrow buttons. Otherwise, increments the value by `custom_arrow_step` and then rounds it
     * according to `Range.step`.
     *
     * Generated from Godot docs: SpinBox.is_custom_arrow_rounding
     */
    fun isCustomArrowRounding(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCustomArrowRoundingBind, segment)
    }

    /**
     * If `true`, the `SpinBox` will be editable. Otherwise, it will be read only.
     *
     * Generated from Godot docs: SpinBox.is_editable
     */
    fun isEditable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEditableBind, segment)
    }

    /**
     * Sets the value of the `Range` for this `SpinBox` when the `LineEdit` text is changed instead of
     * submitted. See `LineEdit.text_changed` and `LineEdit.text_submitted`. Note: If set to `true`,
     * this will interfere with entering mathematical expressions in the `SpinBox`. The `SpinBox` will
     * try to evaluate the expression as you type, which means symbols like a trailing `+` are removed
     * immediately by the expression being evaluated.
     *
     * Generated from Godot docs: SpinBox.set_update_on_text_changed
     */
    fun setUpdateOnTextChanged(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUpdateOnTextChangedBind, segment, enabled)
    }

    /**
     * Sets the value of the `Range` for this `SpinBox` when the `LineEdit` text is changed instead of
     * submitted. See `LineEdit.text_changed` and `LineEdit.text_submitted`. Note: If set to `true`,
     * this will interfere with entering mathematical expressions in the `SpinBox`. The `SpinBox` will
     * try to evaluate the expression as you type, which means symbols like a trailing `+` are removed
     * immediately by the expression being evaluated.
     *
     * Generated from Godot docs: SpinBox.get_update_on_text_changed
     */
    fun getUpdateOnTextChanged(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUpdateOnTextChangedBind, segment)
    }

    /**
     * If `true`, the `SpinBox` will select the whole text when the `LineEdit` gains focus. Clicking
     * the up and down arrows won't trigger this behavior.
     *
     * Generated from Godot docs: SpinBox.set_select_all_on_focus
     */
    fun setSelectAllOnFocus(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSelectAllOnFocusBind, segment, enabled)
    }

    /**
     * If `true`, the `SpinBox` will select the whole text when the `LineEdit` gains focus. Clicking
     * the up and down arrows won't trigger this behavior.
     *
     * Generated from Godot docs: SpinBox.is_select_all_on_focus
     */
    fun isSelectAllOnFocus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSelectAllOnFocusBind, segment)
    }

    /**
     * Applies the current value of this `SpinBox`. This is equivalent to pressing Enter while editing
     * the `LineEdit` used by the `SpinBox`. This will cause `LineEdit.text_submitted` to be emitted
     * and its currently contained expression to be evaluated.
     *
     * Generated from Godot docs: SpinBox.apply
     */
    fun apply() {
        ObjectCalls.ptrcallNoArgs(Binds.applyBind, segment)
    }

    /**
     * Returns the `LineEdit` instance from this `SpinBox`. You can use it to access properties and
     * methods of `LineEdit`. Warning: This is a required internal node, removing and freeing it may
     * cause a crash. If you wish to hide it or any of its children, use their `CanvasItem.visible`
     * property.
     *
     * Generated from Godot docs: SpinBox.get_line_edit
     */
    fun getLineEdit(): LineEdit? {
        return LineEdit.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getLineEditBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SpinBox? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SpinBox? =
            if (handle.address() == 0L) null else SpinBox(GodotHandle(handle))

        /** A new SpinBox, GDScript's `SpinBox.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): SpinBox =
            SpinBox(GodotHandle(requireConstructed(ObjectCalls.constructObject("SpinBox"), "SpinBox")))
    }

    private object Binds {
        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        @JvmField
        val setHorizontalAlignmentBind =
            ObjectCalls.getMethodBind("SpinBox", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)

        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        @JvmField
        val getHorizontalAlignmentBind =
            ObjectCalls.getMethodBind("SpinBox", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)

        private const val SET_FORMAT_HASH = 83702148L
        @JvmField
        val setFormatBind =
            ObjectCalls.getMethodBind("SpinBox", "set_format", SET_FORMAT_HASH)

        private const val GET_FORMAT_HASH = 201670096L
        @JvmField
        val getFormatBind =
            ObjectCalls.getMethodBind("SpinBox", "get_format", GET_FORMAT_HASH)

        private const val SET_PLURAL_FORMAT_HASH = 83702148L
        @JvmField
        val setPluralFormatBind =
            ObjectCalls.getMethodBind("SpinBox", "set_plural_format", SET_PLURAL_FORMAT_HASH)

        private const val GET_PLURAL_FORMAT_HASH = 201670096L
        @JvmField
        val getPluralFormatBind =
            ObjectCalls.getMethodBind("SpinBox", "get_plural_format", GET_PLURAL_FORMAT_HASH)

        private const val SET_FORMAT_AUTO_TRANSLATE_MODE_HASH = 776149714L
        @JvmField
        val setFormatAutoTranslateModeBind =
            ObjectCalls.getMethodBind("SpinBox", "set_format_auto_translate_mode", SET_FORMAT_AUTO_TRANSLATE_MODE_HASH)

        private const val GET_FORMAT_AUTO_TRANSLATE_MODE_HASH = 2498906432L
        @JvmField
        val getFormatAutoTranslateModeBind =
            ObjectCalls.getMethodBind("SpinBox", "get_format_auto_translate_mode", GET_FORMAT_AUTO_TRANSLATE_MODE_HASH)

        private const val SET_SUFFIX_HASH = 83702148L
        @JvmField
        val setSuffixBind =
            ObjectCalls.getMethodBind("SpinBox", "set_suffix", SET_SUFFIX_HASH)

        private const val GET_SUFFIX_HASH = 201670096L
        @JvmField
        val getSuffixBind =
            ObjectCalls.getMethodBind("SpinBox", "get_suffix", GET_SUFFIX_HASH)

        private const val SET_PREFIX_HASH = 83702148L
        @JvmField
        val setPrefixBind =
            ObjectCalls.getMethodBind("SpinBox", "set_prefix", SET_PREFIX_HASH)

        private const val GET_PREFIX_HASH = 201670096L
        @JvmField
        val getPrefixBind =
            ObjectCalls.getMethodBind("SpinBox", "get_prefix", GET_PREFIX_HASH)

        private const val SET_EDITABLE_HASH = 2586408642L
        @JvmField
        val setEditableBind =
            ObjectCalls.getMethodBind("SpinBox", "set_editable", SET_EDITABLE_HASH)

        private const val SET_CUSTOM_ARROW_STEP_HASH = 373806689L
        @JvmField
        val setCustomArrowStepBind =
            ObjectCalls.getMethodBind("SpinBox", "set_custom_arrow_step", SET_CUSTOM_ARROW_STEP_HASH)

        private const val GET_CUSTOM_ARROW_STEP_HASH = 1740695150L
        @JvmField
        val getCustomArrowStepBind =
            ObjectCalls.getMethodBind("SpinBox", "get_custom_arrow_step", GET_CUSTOM_ARROW_STEP_HASH)

        private const val SET_CUSTOM_ARROW_ROUND_HASH = 2586408642L
        @JvmField
        val setCustomArrowRoundBind =
            ObjectCalls.getMethodBind("SpinBox", "set_custom_arrow_round", SET_CUSTOM_ARROW_ROUND_HASH)

        private const val IS_CUSTOM_ARROW_ROUNDING_HASH = 36873697L
        @JvmField
        val isCustomArrowRoundingBind =
            ObjectCalls.getMethodBind("SpinBox", "is_custom_arrow_rounding", IS_CUSTOM_ARROW_ROUNDING_HASH)

        private const val IS_EDITABLE_HASH = 36873697L
        @JvmField
        val isEditableBind =
            ObjectCalls.getMethodBind("SpinBox", "is_editable", IS_EDITABLE_HASH)

        private const val SET_UPDATE_ON_TEXT_CHANGED_HASH = 2586408642L
        @JvmField
        val setUpdateOnTextChangedBind =
            ObjectCalls.getMethodBind("SpinBox", "set_update_on_text_changed", SET_UPDATE_ON_TEXT_CHANGED_HASH)

        private const val GET_UPDATE_ON_TEXT_CHANGED_HASH = 36873697L
        @JvmField
        val getUpdateOnTextChangedBind =
            ObjectCalls.getMethodBind("SpinBox", "get_update_on_text_changed", GET_UPDATE_ON_TEXT_CHANGED_HASH)

        private const val SET_SELECT_ALL_ON_FOCUS_HASH = 2586408642L
        @JvmField
        val setSelectAllOnFocusBind =
            ObjectCalls.getMethodBind("SpinBox", "set_select_all_on_focus", SET_SELECT_ALL_ON_FOCUS_HASH)

        private const val IS_SELECT_ALL_ON_FOCUS_HASH = 36873697L
        @JvmField
        val isSelectAllOnFocusBind =
            ObjectCalls.getMethodBind("SpinBox", "is_select_all_on_focus", IS_SELECT_ALL_ON_FOCUS_HASH)

        private const val APPLY_HASH = 3218959716L
        @JvmField
        val applyBind =
            ObjectCalls.getMethodBind("SpinBox", "apply", APPLY_HASH)

        private const val GET_LINE_EDIT_HASH = 4071694264L
        @JvmField
        val getLineEditBind =
            ObjectCalls.getMethodBind("SpinBox", "get_line_edit", GET_LINE_EDIT_HASH)
    }
}
