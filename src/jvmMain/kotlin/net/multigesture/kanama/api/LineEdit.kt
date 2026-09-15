package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName

/**
 * An input field for single-line text.
 *
 * Generated from Godot docs: LineEdit
 */
class LineEdit(handle: GodotHandle) : Control(handle) {

    var horizontalAlignment: Long
        @JvmName("horizontalAlignmentProperty")
        get() = getHorizontalAlignment()
        @JvmName("setHorizontalAlignmentProperty")
        set(value) = setHorizontalAlignment(value)

    var keepEditingOnTextSubmit: Boolean
        @JvmName("keepEditingOnTextSubmitProperty")
        get() = isEditingKeptOnTextSubmit()
        @JvmName("setKeepEditingOnTextSubmitProperty")
        set(value) = setKeepEditingOnTextSubmit(value)

    var text: String
        @JvmName("textProperty")
        get() = getText()
        @JvmName("setTextProperty")
        set(value) = setText(value)

    var drawControlChars: Boolean
        @JvmName("drawControlCharsProperty")
        get() = getDrawControlChars()
        @JvmName("setDrawControlCharsProperty")
        set(value) = setDrawControlChars(value)

    var textDirection: Long
        @JvmName("textDirectionProperty")
        get() = getTextDirection()
        @JvmName("setTextDirectionProperty")
        set(value) = setTextDirection(value)

    var language: String
        @JvmName("languageProperty")
        get() = getLanguage()
        @JvmName("setLanguageProperty")
        set(value) = setLanguage(value)

    var structuredTextBidiOverride: Long
        @JvmName("structuredTextBidiOverrideProperty")
        get() = getStructuredTextBidiOverride()
        @JvmName("setStructuredTextBidiOverrideProperty")
        set(value) = setStructuredTextBidiOverride(value)

    var structuredTextBidiOverrideOptions: List<Any?>
        @JvmName("structuredTextBidiOverrideOptionsProperty")
        get() = getStructuredTextBidiOverrideOptions()
        @JvmName("setStructuredTextBidiOverrideOptionsProperty")
        set(value) = setStructuredTextBidiOverrideOptions(value)

    var placeholder: String
        @JvmName("placeholderProperty")
        get() = getPlaceholder()
        @JvmName("setPlaceholderProperty")
        set(value) = setPlaceholder(value)

    var caretColumn: Int
        @JvmName("caretColumnProperty")
        get() = getCaretColumn()
        @JvmName("setCaretColumnProperty")
        set(value) = setCaretColumn(value)

    var expandToTextLengthEnabled: Boolean
        @JvmName("expandToTextLengthEnabledProperty")
        get() = isExpandToTextLengthEnabled()
        @JvmName("setExpandToTextLengthEnabledProperty")
        set(value) = setExpandToTextLengthEnabled(value)

    var caretBlinkEnabled: Boolean
        @JvmName("caretBlinkEnabledProperty")
        get() = isCaretBlinkEnabled()
        @JvmName("setCaretBlinkEnabledProperty")
        set(value) = setCaretBlinkEnabled(value)

    var caretMidGraphemeEnabled: Boolean
        @JvmName("caretMidGraphemeEnabledProperty")
        get() = isCaretMidGraphemeEnabled()
        @JvmName("setCaretMidGraphemeEnabledProperty")
        set(value) = setCaretMidGraphemeEnabled(value)

    var caretForceDisplayed: Boolean
        @JvmName("caretForceDisplayedProperty")
        get() = isCaretForceDisplayed()
        @JvmName("setCaretForceDisplayedProperty")
        set(value) = setCaretForceDisplayed(value)

    var caretBlinkInterval: Double
        @JvmName("caretBlinkIntervalProperty")
        get() = getCaretBlinkInterval()
        @JvmName("setCaretBlinkIntervalProperty")
        set(value) = setCaretBlinkInterval(value)

    var maxLength: Int
        @JvmName("maxLengthProperty")
        get() = getMaxLength()
        @JvmName("setMaxLengthProperty")
        set(value) = setMaxLength(value)

    var editable: Boolean
        @JvmName("editableProperty")
        get() = isEditable()
        @JvmName("setEditableProperty")
        set(value) = setEditable(value)

    var secret: Boolean
        @JvmName("secretProperty")
        get() = isSecret()
        @JvmName("setSecretProperty")
        set(value) = setSecret(value)

    var secretCharacter: String
        @JvmName("secretCharacterProperty")
        get() = getSecretCharacter()
        @JvmName("setSecretCharacterProperty")
        set(value) = setSecretCharacter(value)

    var contextMenuEnabled: Boolean
        @JvmName("contextMenuEnabledProperty")
        get() = isContextMenuEnabled()
        @JvmName("setContextMenuEnabledProperty")
        set(value) = setContextMenuEnabled(value)

    var emojiMenuEnabled: Boolean
        @JvmName("emojiMenuEnabledProperty")
        get() = isEmojiMenuEnabled()
        @JvmName("setEmojiMenuEnabledProperty")
        set(value) = setEmojiMenuEnabled(value)

    var backspaceDeletesCompositeCharacterEnabled: Boolean
        @JvmName("backspaceDeletesCompositeCharacterEnabledProperty")
        get() = isBackspaceDeletesCompositeCharacterEnabled()
        @JvmName("setBackspaceDeletesCompositeCharacterEnabledProperty")
        set(value) = setBackspaceDeletesCompositeCharacterEnabled(value)

    var virtualKeyboardEnabled: Boolean
        @JvmName("virtualKeyboardEnabledProperty")
        get() = isVirtualKeyboardEnabled()
        @JvmName("setVirtualKeyboardEnabledProperty")
        set(value) = setVirtualKeyboardEnabled(value)

    var virtualKeyboardShowOnFocus: Boolean
        @JvmName("virtualKeyboardShowOnFocusProperty")
        get() = getVirtualKeyboardShowOnFocus()
        @JvmName("setVirtualKeyboardShowOnFocusProperty")
        set(value) = setVirtualKeyboardShowOnFocus(value)

    var virtualKeyboardType: Long
        @JvmName("virtualKeyboardTypeProperty")
        get() = getVirtualKeyboardType()
        @JvmName("setVirtualKeyboardTypeProperty")
        set(value) = setVirtualKeyboardType(value)

    var clearButtonEnabled: Boolean
        @JvmName("clearButtonEnabledProperty")
        get() = isClearButtonEnabled()
        @JvmName("setClearButtonEnabledProperty")
        set(value) = setClearButtonEnabled(value)

    var shortcutKeysEnabled: Boolean
        @JvmName("shortcutKeysEnabledProperty")
        get() = isShortcutKeysEnabled()
        @JvmName("setShortcutKeysEnabledProperty")
        set(value) = setShortcutKeysEnabled(value)

    var middleMousePasteEnabled: Boolean
        @JvmName("middleMousePasteEnabledProperty")
        get() = isMiddleMousePasteEnabled()
        @JvmName("setMiddleMousePasteEnabledProperty")
        set(value) = setMiddleMousePasteEnabled(value)

    var selectingEnabled: Boolean
        @JvmName("selectingEnabledProperty")
        get() = isSelectingEnabled()
        @JvmName("setSelectingEnabledProperty")
        set(value) = setSelectingEnabled(value)

    var deselectOnFocusLossEnabled: Boolean
        @JvmName("deselectOnFocusLossEnabledProperty")
        get() = isDeselectOnFocusLossEnabled()
        @JvmName("setDeselectOnFocusLossEnabledProperty")
        set(value) = setDeselectOnFocusLossEnabled(value)

    var dragAndDropSelectionEnabled: Boolean
        @JvmName("dragAndDropSelectionEnabledProperty")
        get() = isDragAndDropSelectionEnabled()
        @JvmName("setDragAndDropSelectionEnabledProperty")
        set(value) = setDragAndDropSelectionEnabled(value)

    var rightIcon: Texture2D?
        @JvmName("rightIconProperty")
        get() = getRightIcon()
        @JvmName("setRightIconProperty")
        set(value) = setRightIcon(value)

    var iconExpandMode: Long
        @JvmName("iconExpandModeProperty")
        get() = getIconExpandMode()
        @JvmName("setIconExpandModeProperty")
        set(value) = setIconExpandMode(value)

    var rightIconScale: Double
        @JvmName("rightIconScaleProperty")
        get() = getRightIconScale()
        @JvmName("setRightIconScaleProperty")
        set(value) = setRightIconScale(value)

    var flat: Boolean
        @JvmName("flatProperty")
        get() = isFlat()
        @JvmName("setFlatProperty")
        set(value) = setFlat(value)

    var selectAllOnFocus: Boolean
        @JvmName("selectAllOnFocusProperty")
        get() = isSelectAllOnFocus()
        @JvmName("setSelectAllOnFocusProperty")
        set(value) = setSelectAllOnFocus(value)

    /**
     * Returns `true` if the user has text in the Input Method Editor
     * (https://en.wikipedia.org/wiki/Input_method) (IME).
     *
     * Generated from Godot docs: LineEdit.has_ime_text
     */
    fun hasImeText(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(hasImeTextBind, segment)

    /**
     * Closes the Input Method Editor (https://en.wikipedia.org/wiki/Input_method) (IME) if it is open.
     * Any text in the IME will be lost.
     *
     * Generated from Godot docs: LineEdit.cancel_ime
     */
    fun cancelIme() {
        ObjectCalls.ptrcallNoArgs(cancelImeBind, segment)
    }

    /**
     * Applies text from the Input Method Editor (https://en.wikipedia.org/wiki/Input_method) (IME) and
     * closes the IME if it is open.
     *
     * Generated from Godot docs: LineEdit.apply_ime
     */
    fun applyIme() {
        ObjectCalls.ptrcallNoArgs(applyImeBind, segment)
    }

    /**
     * The text's horizontal alignment.
     *
     * Generated from Godot docs: LineEdit.set_horizontal_alignment
     */
    fun setHorizontalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setHorizontalAlignmentBind, segment, alignment)
    }

    /**
     * The text's horizontal alignment.
     *
     * Generated from Godot docs: LineEdit.get_horizontal_alignment
     */
    fun getHorizontalAlignment(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getHorizontalAlignmentBind, segment)

    /**
     * Allows entering edit mode whether the `LineEdit` is focused or not. If `hide_focus` is `true`,
     * the focused state will not be shown (see `Control.grab_focus`). See also
     * `keep_editing_on_text_submit`.
     *
     * Generated from Godot docs: LineEdit.edit
     */
    fun edit(hideFocus: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(editBind, segment, hideFocus)
    }

    /**
     * Allows exiting edit mode while preserving focus.
     *
     * Generated from Godot docs: LineEdit.unedit
     */
    fun unedit() {
        ObjectCalls.ptrcallNoArgs(uneditBind, segment)
    }

    /**
     * Returns whether the `LineEdit` is being edited.
     *
     * Generated from Godot docs: LineEdit.is_editing
     */
    fun isEditing(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isEditingBind, segment)

    /**
     * If `true`, the `LineEdit` will not exit edit mode when text is submitted by pressing
     * `ui_text_submit` action (by default: Enter or Kp Enter).
     *
     * Generated from Godot docs: LineEdit.set_keep_editing_on_text_submit
     */
    fun setKeepEditingOnTextSubmit(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setKeepEditingOnTextSubmitBind, segment, enable)
    }

    /**
     * If `true`, the `LineEdit` will not exit edit mode when text is submitted by pressing
     * `ui_text_submit` action (by default: Enter or Kp Enter).
     *
     * Generated from Godot docs: LineEdit.is_editing_kept_on_text_submit
     */
    fun isEditingKeptOnTextSubmit(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isEditingKeptOnTextSubmitBind, segment)

    /**
     * Erases the `LineEdit`'s `text`.
     *
     * Generated from Godot docs: LineEdit.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, segment)
    }

    /**
     * Selects characters inside `LineEdit` between `from` and `to`. By default, `from` is at the
     * beginning and `to` at the end.
     *
     * Generated from Godot docs: LineEdit.select
     */
    fun select(from: Int = 0, to: Int = -1) {
        ObjectCalls.ptrcallWithTwoIntArgs(selectBind, segment, from, to)
    }

    /**
     * Selects the whole `String`.
     *
     * Generated from Godot docs: LineEdit.select_all
     */
    fun selectAll() {
        ObjectCalls.ptrcallNoArgs(selectAllBind, segment)
    }

    /**
     * Clears the current selection.
     *
     * Generated from Godot docs: LineEdit.deselect
     */
    fun deselect() {
        ObjectCalls.ptrcallNoArgs(deselectBind, segment)
    }

    /**
     * Returns `true` if an "undo" action is available.
     *
     * Generated from Godot docs: LineEdit.has_undo
     */
    fun hasUndo(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(hasUndoBind, segment)

    /**
     * Returns `true` if a "redo" action is available.
     *
     * Generated from Godot docs: LineEdit.has_redo
     */
    fun hasRedo(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(hasRedoBind, segment)

    /**
     * Returns `true` if the user has selected text.
     *
     * Generated from Godot docs: LineEdit.has_selection
     */
    fun hasSelection(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(hasSelectionBind, segment)

    /**
     * Returns the text inside the selection.
     *
     * Generated from Godot docs: LineEdit.get_selected_text
     */
    fun getSelectedText(): String =
        ObjectCalls.ptrcallNoArgsRetString(getSelectedTextBind, segment)

    /**
     * Returns the selection begin column.
     *
     * Generated from Godot docs: LineEdit.get_selection_from_column
     */
    fun getSelectionFromColumn(): Int =
        ObjectCalls.ptrcallNoArgsRetInt(getSelectionFromColumnBind, segment)

    /**
     * Returns the selection end column.
     *
     * Generated from Godot docs: LineEdit.get_selection_to_column
     */
    fun getSelectionToColumn(): Int =
        ObjectCalls.ptrcallNoArgsRetInt(getSelectionToColumnBind, segment)

    /**
     * String value of the `LineEdit`. Note: Changing text using this property won't emit the
     * `text_changed` signal.
     *
     * Generated from Godot docs: LineEdit.set_text
     */
    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, segment, text)
    }

    /**
     * String value of the `LineEdit`. Note: Changing text using this property won't emit the
     * `text_changed` signal.
     *
     * Generated from Godot docs: LineEdit.get_text
     */
    fun getText(): String =
        ObjectCalls.ptrcallNoArgsRetString(getTextBind, segment)

    /**
     * If `true`, control characters are displayed.
     *
     * Generated from Godot docs: LineEdit.get_draw_control_chars
     */
    fun getDrawControlChars(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(getDrawControlCharsBind, segment)

    /**
     * If `true`, control characters are displayed.
     *
     * Generated from Godot docs: LineEdit.set_draw_control_chars
     */
    fun setDrawControlChars(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawControlCharsBind, segment, enable)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: LineEdit.set_text_direction
     */
    fun setTextDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextDirectionBind, segment, direction)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: LineEdit.get_text_direction
     */
    fun getTextDirection(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getTextDirectionBind, segment)

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: LineEdit.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(setLanguageBind, segment, language)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: LineEdit.get_language
     */
    fun getLanguage(): String =
        ObjectCalls.ptrcallNoArgsRetString(getLanguageBind, segment)

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: LineEdit.set_structured_text_bidi_override
     */
    fun setStructuredTextBidiOverride(parser: Long) {
        ObjectCalls.ptrcallWithLongArg(setStructuredTextBidiOverrideBind, segment, parser)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: LineEdit.get_structured_text_bidi_override
     */
    fun getStructuredTextBidiOverride(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getStructuredTextBidiOverrideBind, segment)

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: LineEdit.set_structured_text_bidi_override_options
     */
    fun setStructuredTextBidiOverrideOptions(args: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setStructuredTextBidiOverrideOptionsBind, segment, args)
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: LineEdit.get_structured_text_bidi_override_options
     */
    fun getStructuredTextBidiOverrideOptions(): List<Any?> =
        ObjectCalls.ptrcallNoArgsRetArray(getStructuredTextBidiOverrideOptionsBind, segment)

    /**
     * Text shown when the `LineEdit` is empty. It is not the `LineEdit`'s default value (see `text`).
     *
     * Generated from Godot docs: LineEdit.set_placeholder
     */
    fun setPlaceholder(text: String) {
        ObjectCalls.ptrcallWithStringArg(setPlaceholderBind, segment, text)
    }

    /**
     * Text shown when the `LineEdit` is empty. It is not the `LineEdit`'s default value (see `text`).
     *
     * Generated from Godot docs: LineEdit.get_placeholder
     */
    fun getPlaceholder(): String =
        ObjectCalls.ptrcallNoArgsRetString(getPlaceholderBind, segment)

    /**
     * The caret's column position inside the `LineEdit`. When set, the text may scroll to accommodate
     * it.
     *
     * Generated from Godot docs: LineEdit.set_caret_column
     */
    fun setCaretColumn(position: Int) {
        ObjectCalls.ptrcallWithIntArg(setCaretColumnBind, segment, position)
    }

    /**
     * The caret's column position inside the `LineEdit`. When set, the text may scroll to accommodate
     * it.
     *
     * Generated from Godot docs: LineEdit.get_caret_column
     */
    fun getCaretColumn(): Int =
        ObjectCalls.ptrcallNoArgsRetInt(getCaretColumnBind, segment)

    /**
     * Returns the correct column at the end of a composite character like ❤️‍🩹 (mending heart;
     * Unicode: `U+2764 U+FE0F U+200D U+1FA79`) which is comprised of more than one Unicode code point,
     * if the caret is at the start of the composite character. Also returns the correct column with
     * the caret at mid grapheme and for non-composite characters. Note: To check at caret location use
     * `get_next_composite_character_column(get_caret_column())`
     *
     * Generated from Godot docs: LineEdit.get_next_composite_character_column
     */
    fun getNextCompositeCharacterColumn(column: Int): Int =
        ObjectCalls.ptrcallWithIntArgRetInt(getNextCompositeCharacterColumnBind, segment, column)

    /**
     * Returns the correct column at the start of a composite character like ❤️‍🩹 (mending heart;
     * Unicode: `U+2764 U+FE0F U+200D U+1FA79`) which is comprised of more than one Unicode code point,
     * if the caret is at the end of the composite character. Also returns the correct column with the
     * caret at mid grapheme and for non-composite characters. Note: To check at caret location use
     * `get_previous_composite_character_column(get_caret_column())`
     *
     * Generated from Godot docs: LineEdit.get_previous_composite_character_column
     */
    fun getPreviousCompositeCharacterColumn(column: Int): Int =
        ObjectCalls.ptrcallWithIntArgRetInt(getPreviousCompositeCharacterColumnBind, segment, column)

    /**
     * Returns the scroll offset due to `caret_column`, as a number of characters.
     *
     * Generated from Godot docs: LineEdit.get_scroll_offset
     */
    fun getScrollOffset(): Double =
        ObjectCalls.ptrcallNoArgsRetDouble(getScrollOffsetBind, segment)

    /**
     * If `true`, the `LineEdit` width will increase to stay longer than the `text`. It will not
     * compress if the `text` is shortened.
     *
     * Generated from Godot docs: LineEdit.set_expand_to_text_length_enabled
     */
    fun setExpandToTextLengthEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setExpandToTextLengthEnabledBind, segment, enabled)
    }

    /**
     * If `true`, the `LineEdit` width will increase to stay longer than the `text`. It will not
     * compress if the `text` is shortened.
     *
     * Generated from Godot docs: LineEdit.is_expand_to_text_length_enabled
     */
    fun isExpandToTextLengthEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isExpandToTextLengthEnabledBind, segment)

    /**
     * If `true`, makes the caret blink.
     *
     * Generated from Godot docs: LineEdit.set_caret_blink_enabled
     */
    fun setCaretBlinkEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCaretBlinkEnabledBind, segment, enabled)
    }

    /**
     * If `true`, makes the caret blink.
     *
     * Generated from Godot docs: LineEdit.is_caret_blink_enabled
     */
    fun isCaretBlinkEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isCaretBlinkEnabledBind, segment)

    /**
     * Allow moving caret, selecting and removing the individual composite character components. Note:
     * Backspace is always removing individual composite character components.
     *
     * Generated from Godot docs: LineEdit.set_caret_mid_grapheme_enabled
     */
    fun setCaretMidGraphemeEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCaretMidGraphemeEnabledBind, segment, enabled)
    }

    /**
     * Allow moving caret, selecting and removing the individual composite character components. Note:
     * Backspace is always removing individual composite character components.
     *
     * Generated from Godot docs: LineEdit.is_caret_mid_grapheme_enabled
     */
    fun isCaretMidGraphemeEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isCaretMidGraphemeEnabledBind, segment)

    /**
     * If `true`, the `LineEdit` will always show the caret, even if not editing or focus is lost.
     *
     * Generated from Godot docs: LineEdit.set_caret_force_displayed
     */
    fun setCaretForceDisplayed(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCaretForceDisplayedBind, segment, enabled)
    }

    /**
     * If `true`, the `LineEdit` will always show the caret, even if not editing or focus is lost.
     *
     * Generated from Godot docs: LineEdit.is_caret_force_displayed
     */
    fun isCaretForceDisplayed(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isCaretForceDisplayedBind, segment)

    /**
     * The interval at which the caret blinks (in seconds).
     *
     * Generated from Godot docs: LineEdit.set_caret_blink_interval
     */
    fun setCaretBlinkInterval(interval: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCaretBlinkIntervalBind, segment, interval)
    }

    /**
     * The interval at which the caret blinks (in seconds).
     *
     * Generated from Godot docs: LineEdit.get_caret_blink_interval
     */
    fun getCaretBlinkInterval(): Double =
        ObjectCalls.ptrcallNoArgsRetDouble(getCaretBlinkIntervalBind, segment)

    /**
     * Maximum number of characters that can be entered inside the `LineEdit`. If `0`, there is no
     * limit. When a limit is defined, characters that would exceed `max_length` are truncated. This
     * happens both for existing `text` contents when setting the max length, or for new text inserted
     * in the `LineEdit`, including pasting. If any input text is truncated, the `text_change_rejected`
     * signal is emitted with the truncated substring as a parameter:
     *
     * Generated from Godot docs: LineEdit.set_max_length
     */
    fun setMaxLength(chars: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxLengthBind, segment, chars)
    }

    /**
     * Maximum number of characters that can be entered inside the `LineEdit`. If `0`, there is no
     * limit. When a limit is defined, characters that would exceed `max_length` are truncated. This
     * happens both for existing `text` contents when setting the max length, or for new text inserted
     * in the `LineEdit`, including pasting. If any input text is truncated, the `text_change_rejected`
     * signal is emitted with the truncated substring as a parameter:
     *
     * Generated from Godot docs: LineEdit.get_max_length
     */
    fun getMaxLength(): Int =
        ObjectCalls.ptrcallNoArgsRetInt(getMaxLengthBind, segment)

    /**
     * Inserts `text` at the caret. If the resulting value is longer than `max_length`, nothing
     * happens.
     *
     * Generated from Godot docs: LineEdit.insert_text_at_caret
     */
    fun insertTextAtCaret(text: String) {
        ObjectCalls.ptrcallWithStringArg(insertTextAtCaretBind, segment, text)
    }

    /**
     * Deletes one character at the caret's current position (equivalent to pressing Delete).
     *
     * Generated from Godot docs: LineEdit.delete_char_at_caret
     */
    fun deleteCharAtCaret() {
        ObjectCalls.ptrcallNoArgs(deleteCharAtCaretBind, segment)
    }

    /**
     * Deletes a section of the `text` going from position `from_column` to `to_column`. Both
     * parameters should be within the text's length.
     *
     * Generated from Godot docs: LineEdit.delete_text
     */
    fun deleteText(fromColumn: Int, toColumn: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(deleteTextBind, segment, fromColumn, toColumn)
    }

    /**
     * If `false`, existing text cannot be modified and new text cannot be added.
     *
     * Generated from Godot docs: LineEdit.set_editable
     */
    fun setEditable(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditableBind, segment, enabled)
    }

    /**
     * If `false`, existing text cannot be modified and new text cannot be added.
     *
     * Generated from Godot docs: LineEdit.is_editable
     */
    fun isEditable(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isEditableBind, segment)

    /**
     * If `true`, every character is replaced with the secret character (see `secret_character`).
     *
     * Generated from Godot docs: LineEdit.set_secret
     */
    fun setSecret(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSecretBind, segment, enabled)
    }

    /**
     * If `true`, every character is replaced with the secret character (see `secret_character`).
     *
     * Generated from Godot docs: LineEdit.is_secret
     */
    fun isSecret(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isSecretBind, segment)

    /**
     * The character to use to mask secret input. Only a single character can be used as the secret
     * character. If it is longer than one character, only the first one will be used. If it is empty,
     * a space will be used instead.
     *
     * Generated from Godot docs: LineEdit.set_secret_character
     */
    fun setSecretCharacter(character: String) {
        ObjectCalls.ptrcallWithStringArg(setSecretCharacterBind, segment, character)
    }

    /**
     * The character to use to mask secret input. Only a single character can be used as the secret
     * character. If it is longer than one character, only the first one will be used. If it is empty,
     * a space will be used instead.
     *
     * Generated from Godot docs: LineEdit.get_secret_character
     */
    fun getSecretCharacter(): String =
        ObjectCalls.ptrcallNoArgsRetString(getSecretCharacterBind, segment)

    /**
     * Executes a given action as defined in the `MenuItems` enum.
     *
     * Generated from Godot docs: LineEdit.menu_option
     */
    fun menuOption(option: Int) {
        ObjectCalls.ptrcallWithIntArg(menuOptionBind, segment, option)
    }

    /**
     * Returns the `PopupMenu` of this `LineEdit`. By default, this menu is displayed when
     * right-clicking on the `LineEdit`. You can add custom menu items or remove standard ones. Make
     * sure your IDs don't conflict with the standard ones (see `MenuItems`). For example:
     *
     * Generated from Godot docs: LineEdit.get_menu
     */
    fun getMenu(): PopupMenu =
        PopupMenu(GodotHandle(ObjectCalls.ptrcallNoArgsRetObject(getMenuBind, segment)))

    /**
     * Returns whether the menu is visible. Use this instead of `get_menu().visible` to improve
     * performance (so the creation of the menu is avoided).
     *
     * Generated from Godot docs: LineEdit.is_menu_visible
     */
    fun isMenuVisible(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isMenuVisibleBind, segment)

    /**
     * If `true`, the context menu will appear when right-clicked.
     *
     * Generated from Godot docs: LineEdit.set_context_menu_enabled
     */
    fun setContextMenuEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setContextMenuEnabledBind, segment, enable)
    }

    /**
     * If `true`, the context menu will appear when right-clicked.
     *
     * Generated from Godot docs: LineEdit.is_context_menu_enabled
     */
    fun isContextMenuEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isContextMenuEnabledBind, segment)

    /**
     * If `true`, "Emoji and Symbols" menu is enabled.
     *
     * Generated from Godot docs: LineEdit.set_emoji_menu_enabled
     */
    fun setEmojiMenuEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmojiMenuEnabledBind, segment, enable)
    }

    /**
     * If `true`, "Emoji and Symbols" menu is enabled.
     *
     * Generated from Godot docs: LineEdit.is_emoji_menu_enabled
     */
    fun isEmojiMenuEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isEmojiMenuEnabledBind, segment)

    /**
     * If `true` and `caret_mid_grapheme` is `false`, backspace deletes an entire composite character
     * such as ❤️‍🩹, instead of deleting part of the composite character.
     *
     * Generated from Godot docs: LineEdit.set_backspace_deletes_composite_character_enabled
     */
    fun setBackspaceDeletesCompositeCharacterEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBackspaceDeletesCompositeCharacterEnabledBind, segment, enable)
    }

    /**
     * If `true` and `caret_mid_grapheme` is `false`, backspace deletes an entire composite character
     * such as ❤️‍🩹, instead of deleting part of the composite character.
     *
     * Generated from Godot docs: LineEdit.is_backspace_deletes_composite_character_enabled
     */
    fun isBackspaceDeletesCompositeCharacterEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isBackspaceDeletesCompositeCharacterEnabledBind, segment)

    /**
     * If `true`, the native virtual keyboard is enabled on platforms that support it.
     *
     * Generated from Godot docs: LineEdit.set_virtual_keyboard_enabled
     */
    fun setVirtualKeyboardEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVirtualKeyboardEnabledBind, segment, enable)
    }

    /**
     * If `true`, the native virtual keyboard is enabled on platforms that support it.
     *
     * Generated from Godot docs: LineEdit.is_virtual_keyboard_enabled
     */
    fun isVirtualKeyboardEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isVirtualKeyboardEnabledBind, segment)

    /**
     * If `true`, the native virtual keyboard is shown on focus events on platforms that support it.
     *
     * Generated from Godot docs: LineEdit.set_virtual_keyboard_show_on_focus
     */
    fun setVirtualKeyboardShowOnFocus(showOnFocus: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVirtualKeyboardShowOnFocusBind, segment, showOnFocus)
    }

    /**
     * If `true`, the native virtual keyboard is shown on focus events on platforms that support it.
     *
     * Generated from Godot docs: LineEdit.get_virtual_keyboard_show_on_focus
     */
    fun getVirtualKeyboardShowOnFocus(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(getVirtualKeyboardShowOnFocusBind, segment)

    /**
     * Specifies the type of virtual keyboard to show.
     *
     * Generated from Godot docs: LineEdit.set_virtual_keyboard_type
     */
    fun setVirtualKeyboardType(type: Long) {
        ObjectCalls.ptrcallWithLongArg(setVirtualKeyboardTypeBind, segment, type)
    }

    /**
     * Specifies the type of virtual keyboard to show.
     *
     * Generated from Godot docs: LineEdit.get_virtual_keyboard_type
     */
    fun getVirtualKeyboardType(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getVirtualKeyboardTypeBind, segment)

    /**
     * If `true`, the `LineEdit` will show a clear button if `text` is not empty, which can be used to
     * clear the text quickly.
     *
     * Generated from Godot docs: LineEdit.set_clear_button_enabled
     */
    fun setClearButtonEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClearButtonEnabledBind, segment, enable)
    }

    /**
     * If `true`, the `LineEdit` will show a clear button if `text` is not empty, which can be used to
     * clear the text quickly.
     *
     * Generated from Godot docs: LineEdit.is_clear_button_enabled
     */
    fun isClearButtonEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isClearButtonEnabledBind, segment)

    /**
     * If `true`, shortcut keys for context menu items are enabled, even if the context menu is
     * disabled.
     *
     * Generated from Godot docs: LineEdit.set_shortcut_keys_enabled
     */
    fun setShortcutKeysEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShortcutKeysEnabledBind, segment, enable)
    }

    /**
     * If `true`, shortcut keys for context menu items are enabled, even if the context menu is
     * disabled.
     *
     * Generated from Godot docs: LineEdit.is_shortcut_keys_enabled
     */
    fun isShortcutKeysEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isShortcutKeysEnabledBind, segment)

    /**
     * If `false`, using middle mouse button to paste clipboard will be disabled. Note: This method is
     * only implemented on Linux.
     *
     * Generated from Godot docs: LineEdit.set_middle_mouse_paste_enabled
     */
    fun setMiddleMousePasteEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMiddleMousePasteEnabledBind, segment, enable)
    }

    /**
     * If `false`, using middle mouse button to paste clipboard will be disabled. Note: This method is
     * only implemented on Linux.
     *
     * Generated from Godot docs: LineEdit.is_middle_mouse_paste_enabled
     */
    fun isMiddleMousePasteEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isMiddleMousePasteEnabledBind, segment)

    /**
     * If `false`, it's impossible to select the text using mouse nor keyboard.
     *
     * Generated from Godot docs: LineEdit.set_selecting_enabled
     */
    fun setSelectingEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSelectingEnabledBind, segment, enable)
    }

    /**
     * If `false`, it's impossible to select the text using mouse nor keyboard.
     *
     * Generated from Godot docs: LineEdit.is_selecting_enabled
     */
    fun isSelectingEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isSelectingEnabledBind, segment)

    /**
     * If `true`, the selected text will be deselected when focus is lost.
     *
     * Generated from Godot docs: LineEdit.set_deselect_on_focus_loss_enabled
     */
    fun setDeselectOnFocusLossEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDeselectOnFocusLossEnabledBind, segment, enable)
    }

    /**
     * If `true`, the selected text will be deselected when focus is lost.
     *
     * Generated from Godot docs: LineEdit.is_deselect_on_focus_loss_enabled
     */
    fun isDeselectOnFocusLossEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isDeselectOnFocusLossEnabledBind, segment)

    /**
     * If `true`, allow drag and drop of selected text.
     *
     * Generated from Godot docs: LineEdit.set_drag_and_drop_selection_enabled
     */
    fun setDragAndDropSelectionEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDragAndDropSelectionEnabledBind, segment, enable)
    }

    /**
     * If `true`, allow drag and drop of selected text.
     *
     * Generated from Godot docs: LineEdit.is_drag_and_drop_selection_enabled
     */
    fun isDragAndDropSelectionEnabled(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isDragAndDropSelectionEnabledBind, segment)

    /**
     * Sets the icon that will appear in the right end of the `LineEdit` if there's no `text`, or
     * always, if `clear_button_enabled` is set to `false`.
     *
     * Generated from Godot docs: LineEdit.set_right_icon
     */
    fun setRightIcon(icon: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(
            setRightIconBind,
            segment,
            listOf(icon?.requireOpenHandle() ?: MemorySegment.NULL),
        )
    }

    /**
     * Sets the icon that will appear in the right end of the `LineEdit` if there's no `text`, or
     * always, if `clear_button_enabled` is set to `false`.
     *
     * Generated from Godot docs: LineEdit.get_right_icon
     */
    fun getRightIcon(): Texture2D? =
        Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getRightIconBind, segment))

    /**
     * Define the scaling behavior of the `right_icon`.
     *
     * Generated from Godot docs: LineEdit.set_icon_expand_mode
     */
    fun setIconExpandMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setIconExpandModeBind, segment, mode)
    }

    /**
     * Define the scaling behavior of the `right_icon`.
     *
     * Generated from Godot docs: LineEdit.get_icon_expand_mode
     */
    fun getIconExpandMode(): Long =
        ObjectCalls.ptrcallNoArgsRetLong(getIconExpandModeBind, segment)

    /**
     * Scale ratio of the icon when `icon_expand_mode` is set to `EXPAND_MODE_FIT_TO_LINE_EDIT`.
     *
     * Generated from Godot docs: LineEdit.set_right_icon_scale
     */
    fun setRightIconScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRightIconScaleBind, segment, scale)
    }

    /**
     * Scale ratio of the icon when `icon_expand_mode` is set to `EXPAND_MODE_FIT_TO_LINE_EDIT`.
     *
     * Generated from Godot docs: LineEdit.get_right_icon_scale
     */
    fun getRightIconScale(): Double =
        ObjectCalls.ptrcallNoArgsRetDouble(getRightIconScaleBind, segment)

    /**
     * If `true`, the `LineEdit` doesn't display decoration.
     *
     * Generated from Godot docs: LineEdit.set_flat
     */
    fun setFlat(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlatBind, segment, enabled)
    }

    /**
     * If `true`, the `LineEdit` doesn't display decoration.
     *
     * Generated from Godot docs: LineEdit.is_flat
     */
    fun isFlat(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isFlatBind, segment)

    /**
     * If `true`, the `LineEdit` will select the whole text when it gains focus.
     *
     * Generated from Godot docs: LineEdit.set_select_all_on_focus
     */
    fun setSelectAllOnFocus(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSelectAllOnFocusBind, segment, enabled)
    }

    /**
     * If `true`, the `LineEdit` will select the whole text when it gains focus.
     *
     * Generated from Godot docs: LineEdit.is_select_all_on_focus
     */
    fun isSelectAllOnFocus(): Boolean =
        ObjectCalls.ptrcallNoArgsRetBool(isSelectAllOnFocusBind, segment)

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): LineEdit? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): LineEdit? =
            if (handle.address() == 0L) null else LineEdit(GodotHandle(handle))

        private const val NOARGS_VOID_HASH = 3218959716L
        private const val NOARGS_BOOL_HASH = 36873697L
        private const val BOOL_VOID_HASH = 2586408642L
        private const val STRING_VOID_HASH = 83702148L
        private const val NOARGS_STRING_HASH = 201670096L
        private const val INT_VOID_HASH = 1286410249L
        private const val NOARGS_INT_HASH = 3905245786L
        private const val FLOAT_VOID_HASH = 373806689L
        private const val NOARGS_FLOAT_HASH = 1740695150L
        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        private const val EDIT_HASH = 107499316L
        private const val SELECT_HASH = 1328111411L
        private const val GET_SELECTED_TEXT_HASH = 2841200299L
        private const val SET_TEXT_DIRECTION_HASH = 119160795L
        private const val GET_TEXT_DIRECTION_HASH = 797257663L
        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 55961453L
        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3385126229L
        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 3995934104L
        private const val INT_RET_INT_HASH = 923996154L
        private const val DELETE_TEXT_HASH = 3937882851L
        private const val GET_MENU_HASH = 229722558L
        private const val IS_CONTEXT_MENU_ENABLED_HASH = 2240911060L
        private const val SET_VIRTUAL_KEYBOARD_TYPE_HASH = 2696893573L
        private const val GET_VIRTUAL_KEYBOARD_TYPE_HASH = 1928699316L
        private const val SET_TEXTURE_HASH = 4051416890L
        private const val GET_TEXTURE_HASH = 255860311L
        private const val SET_ICON_EXPAND_MODE_HASH = 3019903192L
        private const val GET_ICON_EXPAND_MODE_HASH = 3273584435L

        private val hasImeTextBind by lazy { ObjectCalls.getMethodBind("LineEdit", "has_ime_text", NOARGS_BOOL_HASH) }
        private val cancelImeBind by lazy { ObjectCalls.getMethodBind("LineEdit", "cancel_ime", NOARGS_VOID_HASH) }
        private val applyImeBind by lazy { ObjectCalls.getMethodBind("LineEdit", "apply_ime", NOARGS_VOID_HASH) }
        private val setHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)
        }
        private val getHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)
        }
        private val editBind by lazy { ObjectCalls.getMethodBind("LineEdit", "edit", EDIT_HASH) }
        private val uneditBind by lazy { ObjectCalls.getMethodBind("LineEdit", "unedit", NOARGS_VOID_HASH) }
        private val isEditingBind by lazy { ObjectCalls.getMethodBind("LineEdit", "is_editing", NOARGS_BOOL_HASH) }
        private val setKeepEditingOnTextSubmitBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_keep_editing_on_text_submit", BOOL_VOID_HASH)
        }
        private val isEditingKeptOnTextSubmitBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_editing_kept_on_text_submit", NOARGS_BOOL_HASH)
        }
        private val clearBind by lazy { ObjectCalls.getMethodBind("LineEdit", "clear", NOARGS_VOID_HASH) }
        private val selectBind by lazy { ObjectCalls.getMethodBind("LineEdit", "select", SELECT_HASH) }
        private val selectAllBind by lazy { ObjectCalls.getMethodBind("LineEdit", "select_all", NOARGS_VOID_HASH) }
        private val deselectBind by lazy { ObjectCalls.getMethodBind("LineEdit", "deselect", NOARGS_VOID_HASH) }
        private val hasUndoBind by lazy { ObjectCalls.getMethodBind("LineEdit", "has_undo", NOARGS_BOOL_HASH) }
        private val hasRedoBind by lazy { ObjectCalls.getMethodBind("LineEdit", "has_redo", NOARGS_BOOL_HASH) }
        private val hasSelectionBind by lazy { ObjectCalls.getMethodBind("LineEdit", "has_selection", NOARGS_BOOL_HASH) }
        private val getSelectedTextBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_selected_text", GET_SELECTED_TEXT_HASH)
        }
        private val getSelectionFromColumnBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_selection_from_column", NOARGS_INT_HASH)
        }
        private val getSelectionToColumnBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_selection_to_column", NOARGS_INT_HASH)
        }
        private val setTextBind by lazy { ObjectCalls.getMethodBind("LineEdit", "set_text", STRING_VOID_HASH) }
        private val getTextBind by lazy { ObjectCalls.getMethodBind("LineEdit", "get_text", NOARGS_STRING_HASH) }
        private val getDrawControlCharsBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_draw_control_chars", NOARGS_BOOL_HASH)
        }
        private val setDrawControlCharsBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_draw_control_chars", BOOL_VOID_HASH)
        }
        private val setTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_text_direction", SET_TEXT_DIRECTION_HASH)
        }
        private val getTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_text_direction", GET_TEXT_DIRECTION_HASH)
        }
        private val setLanguageBind by lazy { ObjectCalls.getMethodBind("LineEdit", "set_language", STRING_VOID_HASH) }
        private val getLanguageBind by lazy { ObjectCalls.getMethodBind("LineEdit", "get_language", NOARGS_STRING_HASH) }
        private val setStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind(
                "LineEdit",
                "set_structured_text_bidi_override",
                SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH,
            )
        }
        private val getStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind(
                "LineEdit",
                "get_structured_text_bidi_override",
                GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH,
            )
        }
        private val setStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind(
                "LineEdit",
                "set_structured_text_bidi_override_options",
                SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH,
            )
        }
        private val getStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind(
                "LineEdit",
                "get_structured_text_bidi_override_options",
                GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH,
            )
        }
        private val setPlaceholderBind by lazy { ObjectCalls.getMethodBind("LineEdit", "set_placeholder", STRING_VOID_HASH) }
        private val getPlaceholderBind by lazy { ObjectCalls.getMethodBind("LineEdit", "get_placeholder", NOARGS_STRING_HASH) }
        private val setCaretColumnBind by lazy { ObjectCalls.getMethodBind("LineEdit", "set_caret_column", INT_VOID_HASH) }
        private val getCaretColumnBind by lazy { ObjectCalls.getMethodBind("LineEdit", "get_caret_column", NOARGS_INT_HASH) }
        private val getNextCompositeCharacterColumnBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_next_composite_character_column", INT_RET_INT_HASH)
        }
        private val getPreviousCompositeCharacterColumnBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_previous_composite_character_column", INT_RET_INT_HASH)
        }
        private val getScrollOffsetBind by lazy { ObjectCalls.getMethodBind("LineEdit", "get_scroll_offset", NOARGS_FLOAT_HASH) }
        private val setExpandToTextLengthEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_expand_to_text_length_enabled", BOOL_VOID_HASH)
        }
        private val isExpandToTextLengthEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_expand_to_text_length_enabled", NOARGS_BOOL_HASH)
        }
        private val setCaretBlinkEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_caret_blink_enabled", BOOL_VOID_HASH)
        }
        private val isCaretBlinkEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_caret_blink_enabled", NOARGS_BOOL_HASH)
        }
        private val setCaretMidGraphemeEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_caret_mid_grapheme_enabled", BOOL_VOID_HASH)
        }
        private val isCaretMidGraphemeEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_caret_mid_grapheme_enabled", NOARGS_BOOL_HASH)
        }
        private val setCaretForceDisplayedBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_caret_force_displayed", BOOL_VOID_HASH)
        }
        private val isCaretForceDisplayedBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_caret_force_displayed", NOARGS_BOOL_HASH)
        }
        private val setCaretBlinkIntervalBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_caret_blink_interval", FLOAT_VOID_HASH)
        }
        private val getCaretBlinkIntervalBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_caret_blink_interval", NOARGS_FLOAT_HASH)
        }
        private val setMaxLengthBind by lazy { ObjectCalls.getMethodBind("LineEdit", "set_max_length", INT_VOID_HASH) }
        private val getMaxLengthBind by lazy { ObjectCalls.getMethodBind("LineEdit", "get_max_length", NOARGS_INT_HASH) }
        private val insertTextAtCaretBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "insert_text_at_caret", STRING_VOID_HASH)
        }
        private val deleteCharAtCaretBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "delete_char_at_caret", NOARGS_VOID_HASH)
        }
        private val deleteTextBind by lazy { ObjectCalls.getMethodBind("LineEdit", "delete_text", DELETE_TEXT_HASH) }
        private val setEditableBind by lazy { ObjectCalls.getMethodBind("LineEdit", "set_editable", BOOL_VOID_HASH) }
        private val isEditableBind by lazy { ObjectCalls.getMethodBind("LineEdit", "is_editable", NOARGS_BOOL_HASH) }
        private val setSecretBind by lazy { ObjectCalls.getMethodBind("LineEdit", "set_secret", BOOL_VOID_HASH) }
        private val isSecretBind by lazy { ObjectCalls.getMethodBind("LineEdit", "is_secret", NOARGS_BOOL_HASH) }
        private val setSecretCharacterBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_secret_character", STRING_VOID_HASH)
        }
        private val getSecretCharacterBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_secret_character", NOARGS_STRING_HASH)
        }
        private val menuOptionBind by lazy { ObjectCalls.getMethodBind("LineEdit", "menu_option", INT_VOID_HASH) }
        private val getMenuBind by lazy { ObjectCalls.getMethodBind("LineEdit", "get_menu", GET_MENU_HASH) }
        private val isMenuVisibleBind by lazy { ObjectCalls.getMethodBind("LineEdit", "is_menu_visible", NOARGS_BOOL_HASH) }
        private val setContextMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_context_menu_enabled", BOOL_VOID_HASH)
        }
        private val isContextMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_context_menu_enabled", IS_CONTEXT_MENU_ENABLED_HASH)
        }
        private val setEmojiMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_emoji_menu_enabled", BOOL_VOID_HASH)
        }
        private val isEmojiMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_emoji_menu_enabled", NOARGS_BOOL_HASH)
        }
        private val setBackspaceDeletesCompositeCharacterEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_backspace_deletes_composite_character_enabled", BOOL_VOID_HASH)
        }
        private val isBackspaceDeletesCompositeCharacterEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_backspace_deletes_composite_character_enabled", NOARGS_BOOL_HASH)
        }
        private val setVirtualKeyboardEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_virtual_keyboard_enabled", BOOL_VOID_HASH)
        }
        private val isVirtualKeyboardEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_virtual_keyboard_enabled", NOARGS_BOOL_HASH)
        }
        private val setVirtualKeyboardShowOnFocusBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_virtual_keyboard_show_on_focus", BOOL_VOID_HASH)
        }
        private val getVirtualKeyboardShowOnFocusBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_virtual_keyboard_show_on_focus", NOARGS_BOOL_HASH)
        }
        private val setVirtualKeyboardTypeBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_virtual_keyboard_type", SET_VIRTUAL_KEYBOARD_TYPE_HASH)
        }
        private val getVirtualKeyboardTypeBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_virtual_keyboard_type", GET_VIRTUAL_KEYBOARD_TYPE_HASH)
        }
        private val setClearButtonEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_clear_button_enabled", BOOL_VOID_HASH)
        }
        private val isClearButtonEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_clear_button_enabled", NOARGS_BOOL_HASH)
        }
        private val setShortcutKeysEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_shortcut_keys_enabled", BOOL_VOID_HASH)
        }
        private val isShortcutKeysEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_shortcut_keys_enabled", NOARGS_BOOL_HASH)
        }
        private val setMiddleMousePasteEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_middle_mouse_paste_enabled", BOOL_VOID_HASH)
        }
        private val isMiddleMousePasteEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_middle_mouse_paste_enabled", NOARGS_BOOL_HASH)
        }
        private val setSelectingEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_selecting_enabled", BOOL_VOID_HASH)
        }
        private val isSelectingEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_selecting_enabled", NOARGS_BOOL_HASH)
        }
        private val setDeselectOnFocusLossEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_deselect_on_focus_loss_enabled", BOOL_VOID_HASH)
        }
        private val isDeselectOnFocusLossEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_deselect_on_focus_loss_enabled", NOARGS_BOOL_HASH)
        }
        private val setDragAndDropSelectionEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_drag_and_drop_selection_enabled", BOOL_VOID_HASH)
        }
        private val isDragAndDropSelectionEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_drag_and_drop_selection_enabled", NOARGS_BOOL_HASH)
        }
        private val setRightIconBind by lazy { ObjectCalls.getMethodBind("LineEdit", "set_right_icon", SET_TEXTURE_HASH) }
        private val getRightIconBind by lazy { ObjectCalls.getMethodBind("LineEdit", "get_right_icon", GET_TEXTURE_HASH) }
        private val setIconExpandModeBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_icon_expand_mode", SET_ICON_EXPAND_MODE_HASH)
        }
        private val getIconExpandModeBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_icon_expand_mode", GET_ICON_EXPAND_MODE_HASH)
        }
        private val setRightIconScaleBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_right_icon_scale", FLOAT_VOID_HASH)
        }
        private val getRightIconScaleBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_right_icon_scale", NOARGS_FLOAT_HASH)
        }
        private val setFlatBind by lazy { ObjectCalls.getMethodBind("LineEdit", "set_flat", BOOL_VOID_HASH) }
        private val isFlatBind by lazy { ObjectCalls.getMethodBind("LineEdit", "is_flat", NOARGS_BOOL_HASH) }
        private val setSelectAllOnFocusBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_select_all_on_focus", BOOL_VOID_HASH)
        }
        private val isSelectAllOnFocusBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_select_all_on_focus", NOARGS_BOOL_HASH)
        }
    }
}
