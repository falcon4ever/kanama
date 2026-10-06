package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An input field for single-line text.
 *
 * Generated from Godot docs: LineEdit
 */
class LineEdit(handle: GodotHandle) : Control(handle) {
    var text: String
        @JvmName("textProperty")
        get() = getText()
        @JvmName("setTextProperty")
        set(value) = setText(value)

    var placeholderText: String
        @JvmName("placeholderTextProperty")
        get() = getPlaceholder()
        @JvmName("setPlaceholderTextProperty")
        set(value) = setPlaceholder(value)

    var alignment: HorizontalAlignment
        @JvmName("alignmentProperty")
        get() = getHorizontalAlignment()
        @JvmName("setAlignmentProperty")
        set(value) = setHorizontalAlignment(value)

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

    var keepEditingOnTextSubmit: Boolean
        @JvmName("keepEditingOnTextSubmitProperty")
        get() = isEditingKeptOnTextSubmit()
        @JvmName("setKeepEditingOnTextSubmitProperty")
        set(value) = setKeepEditingOnTextSubmit(value)

    var expandToTextLength: Boolean
        @JvmName("expandToTextLengthProperty")
        get() = isExpandToTextLengthEnabled()
        @JvmName("setExpandToTextLengthProperty")
        set(value) = setExpandToTextLengthEnabled(value)

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

    var flat: Boolean
        @JvmName("flatProperty")
        get() = isFlat()
        @JvmName("setFlatProperty")
        set(value) = setFlat(value)

    var drawControlChars: Boolean
        @JvmName("drawControlCharsProperty")
        get() = getDrawControlChars()
        @JvmName("setDrawControlCharsProperty")
        set(value) = setDrawControlChars(value)

    var selectAllOnFocus: Boolean
        @JvmName("selectAllOnFocusProperty")
        get() = isSelectAllOnFocus()
        @JvmName("setSelectAllOnFocusProperty")
        set(value) = setSelectAllOnFocus(value)

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

    var virtualKeyboardType: LineEdit.VirtualKeyboardType
        @JvmName("virtualKeyboardTypeProperty")
        get() = getVirtualKeyboardType()
        @JvmName("setVirtualKeyboardTypeProperty")
        set(value) = setVirtualKeyboardType(value)

    var caretBlink: Boolean
        @JvmName("caretBlinkProperty")
        get() = isCaretBlinkEnabled()
        @JvmName("setCaretBlinkProperty")
        set(value) = setCaretBlinkEnabled(value)

    var caretBlinkInterval: Double
        @JvmName("caretBlinkIntervalProperty")
        get() = getCaretBlinkInterval()
        @JvmName("setCaretBlinkIntervalProperty")
        set(value) = setCaretBlinkInterval(value)

    var caretColumn: Int
        @JvmName("caretColumnProperty")
        get() = getCaretColumn()
        @JvmName("setCaretColumnProperty")
        set(value) = setCaretColumn(value)

    var caretForceDisplayed: Boolean
        @JvmName("caretForceDisplayedProperty")
        get() = isCaretForceDisplayed()
        @JvmName("setCaretForceDisplayedProperty")
        set(value) = setCaretForceDisplayed(value)

    var caretMidGrapheme: Boolean
        @JvmName("caretMidGraphemeProperty")
        get() = isCaretMidGraphemeEnabled()
        @JvmName("setCaretMidGraphemeProperty")
        set(value) = setCaretMidGraphemeEnabled(value)

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

    var textDirection: Control.TextDirection
        @JvmName("textDirectionProperty")
        get() = getTextDirection()
        @JvmName("setTextDirectionProperty")
        set(value) = setTextDirection(value)

    var language: String
        @JvmName("languageProperty")
        get() = getLanguage()
        @JvmName("setLanguageProperty")
        set(value) = setLanguage(value)

    var structuredTextBidiOverride: TextServer.StructuredTextParser
        @JvmName("structuredTextBidiOverrideProperty")
        get() = getStructuredTextBidiOverride()
        @JvmName("setStructuredTextBidiOverrideProperty")
        set(value) = setStructuredTextBidiOverride(value)

    var structuredTextBidiOverrideOptions: List<Any?>
        @JvmName("structuredTextBidiOverrideOptionsProperty")
        get() = getStructuredTextBidiOverrideOptions()
        @JvmName("setStructuredTextBidiOverrideOptionsProperty")
        set(value) = setStructuredTextBidiOverrideOptions(value)

    var rightIcon: Texture2D?
        @JvmName("rightIconProperty")
        get() = getRightIcon()
        @JvmName("setRightIconProperty")
        set(value) = setRightIcon(value)

    var iconExpandMode: LineEdit.ExpandMode
        @JvmName("iconExpandModeProperty")
        get() = getIconExpandMode()
        @JvmName("setIconExpandModeProperty")
        set(value) = setIconExpandMode(value)

    var rightIconScale: Double
        @JvmName("rightIconScaleProperty")
        get() = getRightIconScale()
        @JvmName("setRightIconScaleProperty")
        set(value) = setRightIconScale(value)

    /**
     * Returns `true` if the user has text in the Input Method Editor
     * (https://en.wikipedia.org/wiki/Input_method) (IME).
     *
     * Generated from Godot docs: LineEdit.has_ime_text
     */
    fun hasImeText(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasImeTextBind, segment)
    }

    /**
     * Closes the Input Method Editor (https://en.wikipedia.org/wiki/Input_method) (IME) if it is open.
     * Any text in the IME will be lost.
     *
     * Generated from Godot docs: LineEdit.cancel_ime
     */
    fun cancelIme() {
        ObjectCalls.ptrcallNoArgs(Binds.cancelImeBind, segment)
    }

    /**
     * Applies text from the Input Method Editor (https://en.wikipedia.org/wiki/Input_method) (IME) and
     * closes the IME if it is open.
     *
     * Generated from Godot docs: LineEdit.apply_ime
     */
    fun applyIme() {
        ObjectCalls.ptrcallNoArgs(Binds.applyImeBind, segment)
    }

    /**
     * The text's horizontal alignment.
     *
     * Generated from Godot docs: LineEdit.set_horizontal_alignment
     */
    fun setHorizontalAlignment(alignment: HorizontalAlignment) {
        ObjectCalls.ptrcallWithLongArg(Binds.setHorizontalAlignmentBind, segment, alignment.value)
    }

    /**
     * The text's horizontal alignment.
     *
     * Generated from Godot docs: LineEdit.get_horizontal_alignment
     */
    fun getHorizontalAlignment(): HorizontalAlignment {
        return HorizontalAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHorizontalAlignmentBind, segment))
    }

    /**
     * Allows entering edit mode whether the `LineEdit` is focused or not. If `hide_focus` is `true`,
     * the focused state will not be shown (see `Control.grab_focus`). See also
     * `keep_editing_on_text_submit`.
     *
     * Generated from Godot docs: LineEdit.edit
     */
    fun edit(hideFocus: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(Binds.editBind, segment, hideFocus)
    }

    /**
     * Allows exiting edit mode while preserving focus.
     *
     * Generated from Godot docs: LineEdit.unedit
     */
    fun unedit() {
        ObjectCalls.ptrcallNoArgs(Binds.uneditBind, segment)
    }

    /**
     * Returns whether the `LineEdit` is being edited.
     *
     * Generated from Godot docs: LineEdit.is_editing
     */
    fun isEditing(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEditingBind, segment)
    }

    /**
     * If `true`, the `LineEdit` will not exit edit mode when text is submitted by pressing
     * `ui_text_submit` action (by default: Enter or Kp Enter).
     *
     * Generated from Godot docs: LineEdit.set_keep_editing_on_text_submit
     */
    fun setKeepEditingOnTextSubmit(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setKeepEditingOnTextSubmitBind, segment, enable)
    }

    /**
     * If `true`, the `LineEdit` will not exit edit mode when text is submitted by pressing
     * `ui_text_submit` action (by default: Enter or Kp Enter).
     *
     * Generated from Godot docs: LineEdit.is_editing_kept_on_text_submit
     */
    fun isEditingKeptOnTextSubmit(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEditingKeptOnTextSubmitBind, segment)
    }

    /**
     * Erases the `LineEdit`'s `text`.
     *
     * Generated from Godot docs: LineEdit.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
    }

    /**
     * Selects characters inside `LineEdit` between `from` and `to`. By default, `from` is at the
     * beginning and `to` at the end.
     *
     * Generated from Godot docs: LineEdit.select
     */
    fun select(from: Int = 0, to: Int = -1) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.selectBind, segment, from, to)
    }

    /**
     * Selects the whole `String`.
     *
     * Generated from Godot docs: LineEdit.select_all
     */
    fun selectAll() {
        ObjectCalls.ptrcallNoArgs(Binds.selectAllBind, segment)
    }

    /**
     * Clears the current selection.
     *
     * Generated from Godot docs: LineEdit.deselect
     */
    fun deselect() {
        ObjectCalls.ptrcallNoArgs(Binds.deselectBind, segment)
    }

    /**
     * Returns `true` if an "undo" action is available.
     *
     * Generated from Godot docs: LineEdit.has_undo
     */
    fun hasUndo(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasUndoBind, segment)
    }

    /**
     * Returns `true` if a "redo" action is available.
     *
     * Generated from Godot docs: LineEdit.has_redo
     */
    fun hasRedo(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasRedoBind, segment)
    }

    /**
     * Returns `true` if the user has selected text.
     *
     * Generated from Godot docs: LineEdit.has_selection
     */
    fun hasSelection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasSelectionBind, segment)
    }

    /**
     * Returns the text inside the selection.
     *
     * Generated from Godot docs: LineEdit.get_selected_text
     */
    fun getSelectedText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getSelectedTextBind, segment)
    }

    /**
     * Returns the selection begin column.
     *
     * Generated from Godot docs: LineEdit.get_selection_from_column
     */
    fun getSelectionFromColumn(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSelectionFromColumnBind, segment)
    }

    /**
     * Returns the selection end column.
     *
     * Generated from Godot docs: LineEdit.get_selection_to_column
     */
    fun getSelectionToColumn(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSelectionToColumnBind, segment)
    }

    /**
     * String value of the `LineEdit`. Note: Changing text using this property won't emit the
     * `text_changed` signal.
     *
     * Generated from Godot docs: LineEdit.set_text
     */
    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setTextBind, segment, text)
    }

    /**
     * String value of the `LineEdit`. Note: Changing text using this property won't emit the
     * `text_changed` signal.
     *
     * Generated from Godot docs: LineEdit.get_text
     */
    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getTextBind, segment)
    }

    /**
     * If `true`, control characters are displayed.
     *
     * Generated from Godot docs: LineEdit.get_draw_control_chars
     */
    fun getDrawControlChars(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getDrawControlCharsBind, segment)
    }

    /**
     * If `true`, control characters are displayed.
     *
     * Generated from Godot docs: LineEdit.set_draw_control_chars
     */
    fun setDrawControlChars(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDrawControlCharsBind, segment, enable)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: LineEdit.set_text_direction
     */
    fun setTextDirection(direction: Control.TextDirection) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTextDirectionBind, segment, direction.value)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: LineEdit.get_text_direction
     */
    fun getTextDirection(): Control.TextDirection {
        return Control.TextDirection(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextDirectionBind, segment))
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: LineEdit.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setLanguageBind, segment, language)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: LineEdit.get_language
     */
    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getLanguageBind, segment)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: LineEdit.set_structured_text_bidi_override
     */
    fun setStructuredTextBidiOverride(parser: TextServer.StructuredTextParser) {
        ObjectCalls.ptrcallWithLongArg(Binds.setStructuredTextBidiOverrideBind, segment, parser.value)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: LineEdit.get_structured_text_bidi_override
     */
    fun getStructuredTextBidiOverride(): TextServer.StructuredTextParser {
        return TextServer.StructuredTextParser(ObjectCalls.ptrcallNoArgsRetLong(Binds.getStructuredTextBidiOverrideBind, segment))
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: LineEdit.set_structured_text_bidi_override_options
     */
    fun setStructuredTextBidiOverrideOptions(args: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(Binds.setStructuredTextBidiOverrideOptionsBind, segment, args)
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: LineEdit.get_structured_text_bidi_override_options
     */
    fun getStructuredTextBidiOverrideOptions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getStructuredTextBidiOverrideOptionsBind, segment)
    }

    /**
     * Text shown when the `LineEdit` is empty. It is not the `LineEdit`'s default value (see `text`).
     *
     * Generated from Godot docs: LineEdit.set_placeholder
     */
    fun setPlaceholder(text: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setPlaceholderBind, segment, text)
    }

    /**
     * Text shown when the `LineEdit` is empty. It is not the `LineEdit`'s default value (see `text`).
     *
     * Generated from Godot docs: LineEdit.get_placeholder
     */
    fun getPlaceholder(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getPlaceholderBind, segment)
    }

    /**
     * The caret's column position inside the `LineEdit`. When set, the text may scroll to accommodate
     * it.
     *
     * Generated from Godot docs: LineEdit.set_caret_column
     */
    fun setCaretColumn(position: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setCaretColumnBind, segment, position)
    }

    /**
     * The caret's column position inside the `LineEdit`. When set, the text may scroll to accommodate
     * it.
     *
     * Generated from Godot docs: LineEdit.get_caret_column
     */
    fun getCaretColumn(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCaretColumnBind, segment)
    }

    /**
     * Returns the correct column at the end of a composite character like ❤️‍🩹 (mending heart;
     * Unicode: `U+2764 U+FE0F U+200D U+1FA79`) which is comprised of more than one Unicode code point,
     * if the caret is at the start of the composite character. Also returns the correct column with
     * the caret at mid grapheme and for non-composite characters. Note: To check at caret location use
     * `get_next_composite_character_column(get_caret_column())`
     *
     * Generated from Godot docs: LineEdit.get_next_composite_character_column
     */
    fun getNextCompositeCharacterColumn(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getNextCompositeCharacterColumnBind, segment, column)
    }

    /**
     * Returns the correct column at the start of a composite character like ❤️‍🩹 (mending heart;
     * Unicode: `U+2764 U+FE0F U+200D U+1FA79`) which is comprised of more than one Unicode code point,
     * if the caret is at the end of the composite character. Also returns the correct column with the
     * caret at mid grapheme and for non-composite characters. Note: To check at caret location use
     * `get_previous_composite_character_column(get_caret_column())`
     *
     * Generated from Godot docs: LineEdit.get_previous_composite_character_column
     */
    fun getPreviousCompositeCharacterColumn(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getPreviousCompositeCharacterColumnBind, segment, column)
    }

    /**
     * Returns the scroll offset due to `caret_column`, as a number of characters.
     *
     * Generated from Godot docs: LineEdit.get_scroll_offset
     */
    fun getScrollOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getScrollOffsetBind, segment)
    }

    /**
     * If `true`, the `LineEdit` width will increase to stay longer than the `text`. It will not
     * compress if the `text` is shortened.
     *
     * Generated from Godot docs: LineEdit.set_expand_to_text_length_enabled
     */
    fun setExpandToTextLengthEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setExpandToTextLengthEnabledBind, segment, enabled)
    }

    /**
     * If `true`, the `LineEdit` width will increase to stay longer than the `text`. It will not
     * compress if the `text` is shortened.
     *
     * Generated from Godot docs: LineEdit.is_expand_to_text_length_enabled
     */
    fun isExpandToTextLengthEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isExpandToTextLengthEnabledBind, segment)
    }

    /**
     * If `true`, makes the caret blink.
     *
     * Generated from Godot docs: LineEdit.set_caret_blink_enabled
     */
    fun setCaretBlinkEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCaretBlinkEnabledBind, segment, enabled)
    }

    /**
     * If `true`, makes the caret blink.
     *
     * Generated from Godot docs: LineEdit.is_caret_blink_enabled
     */
    fun isCaretBlinkEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCaretBlinkEnabledBind, segment)
    }

    /**
     * Allow moving caret, selecting and removing the individual composite character components. Note:
     * Backspace is always removing individual composite character components.
     *
     * Generated from Godot docs: LineEdit.set_caret_mid_grapheme_enabled
     */
    fun setCaretMidGraphemeEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCaretMidGraphemeEnabledBind, segment, enabled)
    }

    /**
     * Allow moving caret, selecting and removing the individual composite character components. Note:
     * Backspace is always removing individual composite character components.
     *
     * Generated from Godot docs: LineEdit.is_caret_mid_grapheme_enabled
     */
    fun isCaretMidGraphemeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCaretMidGraphemeEnabledBind, segment)
    }

    /**
     * If `true`, the `LineEdit` will always show the caret, even if not editing or focus is lost.
     *
     * Generated from Godot docs: LineEdit.set_caret_force_displayed
     */
    fun setCaretForceDisplayed(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCaretForceDisplayedBind, segment, enabled)
    }

    /**
     * If `true`, the `LineEdit` will always show the caret, even if not editing or focus is lost.
     *
     * Generated from Godot docs: LineEdit.is_caret_force_displayed
     */
    fun isCaretForceDisplayed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCaretForceDisplayedBind, segment)
    }

    /**
     * The interval at which the caret blinks (in seconds).
     *
     * Generated from Godot docs: LineEdit.set_caret_blink_interval
     */
    fun setCaretBlinkInterval(interval: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCaretBlinkIntervalBind, segment, interval)
    }

    /**
     * The interval at which the caret blinks (in seconds).
     *
     * Generated from Godot docs: LineEdit.get_caret_blink_interval
     */
    fun getCaretBlinkInterval(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCaretBlinkIntervalBind, segment)
    }

    /**
     * Maximum number of characters that can be entered inside the `LineEdit`. If `0`, there is no
     * limit. When a limit is defined, characters that would exceed `max_length` are truncated. This
     * happens both for existing `text` contents when setting the max length, or for new text inserted
     * in the `LineEdit`, including pasting.
     *
     * Generated from Godot docs: LineEdit.set_max_length
     */
    fun setMaxLength(chars: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxLengthBind, segment, chars)
    }

    /**
     * Maximum number of characters that can be entered inside the `LineEdit`. If `0`, there is no
     * limit. When a limit is defined, characters that would exceed `max_length` are truncated. This
     * happens both for existing `text` contents when setting the max length, or for new text inserted
     * in the `LineEdit`, including pasting.
     *
     * Generated from Godot docs: LineEdit.get_max_length
     */
    fun getMaxLength(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxLengthBind, segment)
    }

    /**
     * Inserts `text` at the caret. If the resulting value is longer than `max_length`, nothing
     * happens.
     *
     * Generated from Godot docs: LineEdit.insert_text_at_caret
     */
    fun insertTextAtCaret(text: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.insertTextAtCaretBind, segment, text)
    }

    /**
     * Deletes one character at the caret's current position (equivalent to pressing Delete).
     *
     * Generated from Godot docs: LineEdit.delete_char_at_caret
     */
    fun deleteCharAtCaret() {
        ObjectCalls.ptrcallNoArgs(Binds.deleteCharAtCaretBind, segment)
    }

    /**
     * Deletes a section of the `text` going from position `from_column` to `to_column`. Both
     * parameters should be within the text's length.
     *
     * Generated from Godot docs: LineEdit.delete_text
     */
    fun deleteText(fromColumn: Int, toColumn: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.deleteTextBind, segment, fromColumn, toColumn)
    }

    /**
     * If `false`, existing text cannot be modified and new text cannot be added.
     *
     * Generated from Godot docs: LineEdit.set_editable
     */
    fun setEditable(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEditableBind, segment, enabled)
    }

    /**
     * If `false`, existing text cannot be modified and new text cannot be added.
     *
     * Generated from Godot docs: LineEdit.is_editable
     */
    fun isEditable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEditableBind, segment)
    }

    /**
     * If `true`, every character is replaced with the secret character (see `secret_character`).
     *
     * Generated from Godot docs: LineEdit.set_secret
     */
    fun setSecret(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSecretBind, segment, enabled)
    }

    /**
     * If `true`, every character is replaced with the secret character (see `secret_character`).
     *
     * Generated from Godot docs: LineEdit.is_secret
     */
    fun isSecret(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSecretBind, segment)
    }

    /**
     * The character to use to mask secret input. Only a single character can be used as the secret
     * character. If it is longer than one character, only the first one will be used. If it is empty,
     * a space will be used instead.
     *
     * Generated from Godot docs: LineEdit.set_secret_character
     */
    fun setSecretCharacter(character: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setSecretCharacterBind, segment, character)
    }

    /**
     * The character to use to mask secret input. Only a single character can be used as the secret
     * character. If it is longer than one character, only the first one will be used. If it is empty,
     * a space will be used instead.
     *
     * Generated from Godot docs: LineEdit.get_secret_character
     */
    fun getSecretCharacter(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getSecretCharacterBind, segment)
    }

    /**
     * Executes a given action as defined in the `MenuItems` enum.
     *
     * Generated from Godot docs: LineEdit.menu_option
     */
    fun menuOption(option: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.menuOptionBind, segment, option)
    }

    /**
     * Returns the `PopupMenu` of this `LineEdit`. By default, this menu is displayed when
     * right-clicking on the `LineEdit`. You can add custom menu items or remove standard ones. Make
     * sure your IDs don't conflict with the standard ones (see `MenuItems`).
     *
     * Generated from Godot docs: LineEdit.get_menu
     */
    fun getMenu(): PopupMenu? {
        return PopupMenu.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getMenuBind, segment))
    }

    /**
     * Returns whether the menu is visible. Use this instead of `get_menu().visible` to improve
     * performance (so the creation of the menu is avoided).
     *
     * Generated from Godot docs: LineEdit.is_menu_visible
     */
    fun isMenuVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMenuVisibleBind, segment)
    }

    /**
     * If `true`, the context menu will appear when right-clicked.
     *
     * Generated from Godot docs: LineEdit.set_context_menu_enabled
     */
    fun setContextMenuEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setContextMenuEnabledBind, segment, enable)
    }

    /**
     * If `true`, the context menu will appear when right-clicked.
     *
     * Generated from Godot docs: LineEdit.is_context_menu_enabled
     */
    fun isContextMenuEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isContextMenuEnabledBind, segment)
    }

    /**
     * If `true`, "Emoji and Symbols" menu is enabled.
     *
     * Generated from Godot docs: LineEdit.set_emoji_menu_enabled
     */
    fun setEmojiMenuEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEmojiMenuEnabledBind, segment, enable)
    }

    /**
     * If `true`, "Emoji and Symbols" menu is enabled.
     *
     * Generated from Godot docs: LineEdit.is_emoji_menu_enabled
     */
    fun isEmojiMenuEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEmojiMenuEnabledBind, segment)
    }

    /**
     * If `true` and `caret_mid_grapheme` is `false`, backspace deletes an entire composite character
     * such as ❤️‍🩹, instead of deleting part of the composite character.
     *
     * Generated from Godot docs: LineEdit.set_backspace_deletes_composite_character_enabled
     */
    fun setBackspaceDeletesCompositeCharacterEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setBackspaceDeletesCompositeCharacterEnabledBind, segment, enable)
    }

    /**
     * If `true` and `caret_mid_grapheme` is `false`, backspace deletes an entire composite character
     * such as ❤️‍🩹, instead of deleting part of the composite character.
     *
     * Generated from Godot docs: LineEdit.is_backspace_deletes_composite_character_enabled
     */
    fun isBackspaceDeletesCompositeCharacterEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isBackspaceDeletesCompositeCharacterEnabledBind, segment)
    }

    /**
     * If `true`, the native virtual keyboard is enabled on platforms that support it.
     *
     * Generated from Godot docs: LineEdit.set_virtual_keyboard_enabled
     */
    fun setVirtualKeyboardEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setVirtualKeyboardEnabledBind, segment, enable)
    }

    /**
     * If `true`, the native virtual keyboard is enabled on platforms that support it.
     *
     * Generated from Godot docs: LineEdit.is_virtual_keyboard_enabled
     */
    fun isVirtualKeyboardEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isVirtualKeyboardEnabledBind, segment)
    }

    /**
     * If `true`, the native virtual keyboard is shown on focus events on platforms that support it.
     *
     * Generated from Godot docs: LineEdit.set_virtual_keyboard_show_on_focus
     */
    fun setVirtualKeyboardShowOnFocus(showOnFocus: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setVirtualKeyboardShowOnFocusBind, segment, showOnFocus)
    }

    /**
     * If `true`, the native virtual keyboard is shown on focus events on platforms that support it.
     *
     * Generated from Godot docs: LineEdit.get_virtual_keyboard_show_on_focus
     */
    fun getVirtualKeyboardShowOnFocus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getVirtualKeyboardShowOnFocusBind, segment)
    }

    /**
     * Specifies the type of virtual keyboard to show.
     *
     * Generated from Godot docs: LineEdit.set_virtual_keyboard_type
     */
    fun setVirtualKeyboardType(type: LineEdit.VirtualKeyboardType) {
        ObjectCalls.ptrcallWithLongArg(Binds.setVirtualKeyboardTypeBind, segment, type.value)
    }

    /**
     * Specifies the type of virtual keyboard to show.
     *
     * Generated from Godot docs: LineEdit.get_virtual_keyboard_type
     */
    fun getVirtualKeyboardType(): LineEdit.VirtualKeyboardType {
        return LineEdit.VirtualKeyboardType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getVirtualKeyboardTypeBind, segment))
    }

    /**
     * If `true`, the `LineEdit` will show a clear button if `text` is not empty, which can be used to
     * clear the text quickly.
     *
     * Generated from Godot docs: LineEdit.set_clear_button_enabled
     */
    fun setClearButtonEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setClearButtonEnabledBind, segment, enable)
    }

    /**
     * If `true`, the `LineEdit` will show a clear button if `text` is not empty, which can be used to
     * clear the text quickly.
     *
     * Generated from Godot docs: LineEdit.is_clear_button_enabled
     */
    fun isClearButtonEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isClearButtonEnabledBind, segment)
    }

    /**
     * If `true`, shortcut keys for context menu items are enabled, even if the context menu is
     * disabled.
     *
     * Generated from Godot docs: LineEdit.set_shortcut_keys_enabled
     */
    fun setShortcutKeysEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setShortcutKeysEnabledBind, segment, enable)
    }

    /**
     * If `true`, shortcut keys for context menu items are enabled, even if the context menu is
     * disabled.
     *
     * Generated from Godot docs: LineEdit.is_shortcut_keys_enabled
     */
    fun isShortcutKeysEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isShortcutKeysEnabledBind, segment)
    }

    /**
     * If `false`, using middle mouse button to paste clipboard will be disabled. Note: This method is
     * only implemented on Linux.
     *
     * Generated from Godot docs: LineEdit.set_middle_mouse_paste_enabled
     */
    fun setMiddleMousePasteEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setMiddleMousePasteEnabledBind, segment, enable)
    }

    /**
     * If `false`, using middle mouse button to paste clipboard will be disabled. Note: This method is
     * only implemented on Linux.
     *
     * Generated from Godot docs: LineEdit.is_middle_mouse_paste_enabled
     */
    fun isMiddleMousePasteEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMiddleMousePasteEnabledBind, segment)
    }

    /**
     * If `false`, it's impossible to select the text using mouse nor keyboard.
     *
     * Generated from Godot docs: LineEdit.set_selecting_enabled
     */
    fun setSelectingEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSelectingEnabledBind, segment, enable)
    }

    /**
     * If `false`, it's impossible to select the text using mouse nor keyboard.
     *
     * Generated from Godot docs: LineEdit.is_selecting_enabled
     */
    fun isSelectingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSelectingEnabledBind, segment)
    }

    /**
     * If `true`, the selected text will be deselected when focus is lost.
     *
     * Generated from Godot docs: LineEdit.set_deselect_on_focus_loss_enabled
     */
    fun setDeselectOnFocusLossEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDeselectOnFocusLossEnabledBind, segment, enable)
    }

    /**
     * If `true`, the selected text will be deselected when focus is lost.
     *
     * Generated from Godot docs: LineEdit.is_deselect_on_focus_loss_enabled
     */
    fun isDeselectOnFocusLossEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDeselectOnFocusLossEnabledBind, segment)
    }

    /**
     * If `true`, allow drag and drop of selected text.
     *
     * Generated from Godot docs: LineEdit.set_drag_and_drop_selection_enabled
     */
    fun setDragAndDropSelectionEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDragAndDropSelectionEnabledBind, segment, enable)
    }

    /**
     * If `true`, allow drag and drop of selected text.
     *
     * Generated from Godot docs: LineEdit.is_drag_and_drop_selection_enabled
     */
    fun isDragAndDropSelectionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDragAndDropSelectionEnabledBind, segment)
    }

    /**
     * Sets the icon that will appear in the right end of the `LineEdit` if there's no `text`, or
     * always, if `clear_button_enabled` is set to `false`.
     *
     * Generated from Godot docs: LineEdit.set_right_icon
     */
    fun setRightIcon(icon: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setRightIconBind, segment, listOf(icon?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Sets the icon that will appear in the right end of the `LineEdit` if there's no `text`, or
     * always, if `clear_button_enabled` is set to `false`.
     *
     * Generated from Godot docs: LineEdit.get_right_icon
     */
    fun getRightIcon(): Texture2D? {
        return Texture2D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getRightIconBind, segment))
    }

    /**
     * Define the scaling behavior of the `right_icon`.
     *
     * Generated from Godot docs: LineEdit.set_icon_expand_mode
     */
    fun setIconExpandMode(mode: LineEdit.ExpandMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setIconExpandModeBind, segment, mode.value)
    }

    /**
     * Define the scaling behavior of the `right_icon`.
     *
     * Generated from Godot docs: LineEdit.get_icon_expand_mode
     */
    fun getIconExpandMode(): LineEdit.ExpandMode {
        return LineEdit.ExpandMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getIconExpandModeBind, segment))
    }

    /**
     * Scale ratio of the icon when `icon_expand_mode` is set to `ExpandMode.FIT_TO_LINE_EDIT`.
     *
     * Generated from Godot docs: LineEdit.set_right_icon_scale
     */
    fun setRightIconScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRightIconScaleBind, segment, scale)
    }

    /**
     * Scale ratio of the icon when `icon_expand_mode` is set to `ExpandMode.FIT_TO_LINE_EDIT`.
     *
     * Generated from Godot docs: LineEdit.get_right_icon_scale
     */
    fun getRightIconScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRightIconScaleBind, segment)
    }

    /**
     * If `true`, the `LineEdit` doesn't display decoration.
     *
     * Generated from Godot docs: LineEdit.set_flat
     */
    fun setFlat(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setFlatBind, segment, enabled)
    }

    /**
     * If `true`, the `LineEdit` doesn't display decoration.
     *
     * Generated from Godot docs: LineEdit.is_flat
     */
    fun isFlat(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isFlatBind, segment)
    }

    /**
     * If `true`, the `LineEdit` will select the whole text when it gains focus.
     *
     * Generated from Godot docs: LineEdit.set_select_all_on_focus
     */
    fun setSelectAllOnFocus(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSelectAllOnFocusBind, segment, enabled)
    }

    /**
     * If `true`, the `LineEdit` will select the whole text when it gains focus.
     *
     * Generated from Godot docs: LineEdit.is_select_all_on_focus
     */
    fun isSelectAllOnFocus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSelectAllOnFocusBind, segment)
    }

    /** Signal `text_changed(new_text: String)`; see [TypedSignal]. */
    val textChanged: Signal1<String>
        @JvmName("textChangedTypedSignal")
        get() = Signal1(this, "text_changed", SignalArgType.STRING)

    /** Signal `text_change_rejected(rejected_substring: String)`; see [TypedSignal]. */
    val textChangeRejected: Signal1<String>
        @JvmName("textChangeRejectedTypedSignal")
        get() = Signal1(this, "text_change_rejected", SignalArgType.STRING)

    /** Signal `text_submitted(new_text: String)`; see [TypedSignal]. */
    val textSubmitted: Signal1<String>
        @JvmName("textSubmittedTypedSignal")
        get() = Signal1(this, "text_submitted", SignalArgType.STRING)

    /** Signal `editing_toggled(toggled_on: bool)`; see [TypedSignal]. */
    val editingToggled: Signal1<Boolean>
        @JvmName("editingToggledTypedSignal")
        get() = Signal1(this, "editing_toggled", SignalArgType.BOOLEAN)

    object Signals {
        const val textChanged: String = "text_changed"
        const val textChangeRejected: String = "text_change_rejected"
        const val textSubmitted: String = "text_submitted"
        const val editingToggled: String = "editing_toggled"
    }

    /**
     * Godot's `LineEdit.MenuItems` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`LineEdit.MenuItems.<NAME>`).
     *
     * Generated from Godot docs: LineEdit.MenuItems
     */
    @JvmInline
    value class MenuItems(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Cuts (copies and clears) the selected text.
             *
             * Generated from Godot docs: LineEdit.MENU_CUT
             */
            val CUT: MenuItems get() = MenuItems(0L)
            /**
             * Copies the selected text.
             *
             * Generated from Godot docs: LineEdit.MENU_COPY
             */
            val COPY: MenuItems get() = MenuItems(1L)
            /**
             * Pastes the clipboard text over the selected text (or at the caret's position). Non-printable
             * escape characters are automatically stripped from the OS clipboard via `String.strip_escapes`.
             *
             * Generated from Godot docs: LineEdit.MENU_PASTE
             */
            val PASTE: MenuItems get() = MenuItems(2L)
            /**
             * Erases the whole `LineEdit` text.
             *
             * Generated from Godot docs: LineEdit.MENU_CLEAR
             */
            val CLEAR: MenuItems get() = MenuItems(3L)
            /**
             * Selects the whole `LineEdit` text.
             *
             * Generated from Godot docs: LineEdit.MENU_SELECT_ALL
             */
            val SELECT_ALL: MenuItems get() = MenuItems(4L)
            /**
             * Undoes the previous action.
             *
             * Generated from Godot docs: LineEdit.MENU_UNDO
             */
            val UNDO: MenuItems get() = MenuItems(5L)
            /**
             * Reverse the last undo action.
             *
             * Generated from Godot docs: LineEdit.MENU_REDO
             */
            val REDO: MenuItems get() = MenuItems(6L)
            /**
             * ID of "Text Writing Direction" submenu.
             *
             * Generated from Godot docs: LineEdit.MENU_SUBMENU_TEXT_DIR
             */
            val SUBMENU_TEXT_DIR: MenuItems get() = MenuItems(7L)
            /**
             * Sets text direction to inherited.
             *
             * Generated from Godot docs: LineEdit.MENU_DIR_INHERITED
             */
            val DIR_INHERITED: MenuItems get() = MenuItems(8L)
            /**
             * Sets text direction to automatic.
             *
             * Generated from Godot docs: LineEdit.MENU_DIR_AUTO
             */
            val DIR_AUTO: MenuItems get() = MenuItems(9L)
            /**
             * Sets text direction to left-to-right.
             *
             * Generated from Godot docs: LineEdit.MENU_DIR_LTR
             */
            val DIR_LTR: MenuItems get() = MenuItems(10L)
            /**
             * Sets text direction to right-to-left.
             *
             * Generated from Godot docs: LineEdit.MENU_DIR_RTL
             */
            val DIR_RTL: MenuItems get() = MenuItems(11L)
            /**
             * Toggles control character display.
             *
             * Generated from Godot docs: LineEdit.MENU_DISPLAY_UCC
             */
            val DISPLAY_UCC: MenuItems get() = MenuItems(12L)
            /**
             * ID of "Insert Control Character" submenu.
             *
             * Generated from Godot docs: LineEdit.MENU_SUBMENU_INSERT_UCC
             */
            val SUBMENU_INSERT_UCC: MenuItems get() = MenuItems(13L)
            /**
             * Inserts left-to-right mark (LRM) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_LRM
             */
            val INSERT_LRM: MenuItems get() = MenuItems(14L)
            /**
             * Inserts right-to-left mark (RLM) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_RLM
             */
            val INSERT_RLM: MenuItems get() = MenuItems(15L)
            /**
             * Inserts start of left-to-right embedding (LRE) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_LRE
             */
            val INSERT_LRE: MenuItems get() = MenuItems(16L)
            /**
             * Inserts start of right-to-left embedding (RLE) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_RLE
             */
            val INSERT_RLE: MenuItems get() = MenuItems(17L)
            /**
             * Inserts start of left-to-right override (LRO) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_LRO
             */
            val INSERT_LRO: MenuItems get() = MenuItems(18L)
            /**
             * Inserts start of right-to-left override (RLO) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_RLO
             */
            val INSERT_RLO: MenuItems get() = MenuItems(19L)
            /**
             * Inserts pop direction formatting (PDF) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_PDF
             */
            val INSERT_PDF: MenuItems get() = MenuItems(20L)
            /**
             * Inserts Arabic letter mark (ALM) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_ALM
             */
            val INSERT_ALM: MenuItems get() = MenuItems(21L)
            /**
             * Inserts left-to-right isolate (LRI) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_LRI
             */
            val INSERT_LRI: MenuItems get() = MenuItems(22L)
            /**
             * Inserts right-to-left isolate (RLI) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_RLI
             */
            val INSERT_RLI: MenuItems get() = MenuItems(23L)
            /**
             * Inserts first strong isolate (FSI) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_FSI
             */
            val INSERT_FSI: MenuItems get() = MenuItems(24L)
            /**
             * Inserts pop direction isolate (PDI) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_PDI
             */
            val INSERT_PDI: MenuItems get() = MenuItems(25L)
            /**
             * Inserts zero width joiner (ZWJ) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_ZWJ
             */
            val INSERT_ZWJ: MenuItems get() = MenuItems(26L)
            /**
             * Inserts zero width non-joiner (ZWNJ) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_ZWNJ
             */
            val INSERT_ZWNJ: MenuItems get() = MenuItems(27L)
            /**
             * Inserts word joiner (WJ) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_WJ
             */
            val INSERT_WJ: MenuItems get() = MenuItems(28L)
            /**
             * Inserts soft hyphen (SHY) character.
             *
             * Generated from Godot docs: LineEdit.MENU_INSERT_SHY
             */
            val INSERT_SHY: MenuItems get() = MenuItems(29L)
            /**
             * Opens system emoji and symbol picker.
             *
             * Generated from Godot docs: LineEdit.MENU_EMOJI_AND_SYMBOL
             */
            val EMOJI_AND_SYMBOL: MenuItems get() = MenuItems(30L)
            /**
             * Represents the size of the `MenuItems` enum.
             *
             * Generated from Godot docs: LineEdit.MENU_MAX
             */
            val MAX: MenuItems get() = MenuItems(31L)
        }
    }

    /**
     * Godot's `LineEdit.VirtualKeyboardType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`LineEdit.VirtualKeyboardType.<NAME>`).
     *
     * Generated from Godot docs: LineEdit.VirtualKeyboardType
     */
    @JvmInline
    value class VirtualKeyboardType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Default text virtual keyboard.
             *
             * Generated from Godot docs: LineEdit.KEYBOARD_TYPE_DEFAULT
             */
            val DEFAULT: VirtualKeyboardType get() = VirtualKeyboardType(0L)
            /**
             * Multiline virtual keyboard.
             *
             * Generated from Godot docs: LineEdit.KEYBOARD_TYPE_MULTILINE
             */
            val MULTILINE: VirtualKeyboardType get() = VirtualKeyboardType(1L)
            /**
             * Virtual number keypad, useful for PIN entry.
             *
             * Generated from Godot docs: LineEdit.KEYBOARD_TYPE_NUMBER
             */
            val NUMBER: VirtualKeyboardType get() = VirtualKeyboardType(2L)
            /**
             * Virtual number keypad, useful for entering fractional numbers.
             *
             * Generated from Godot docs: LineEdit.KEYBOARD_TYPE_NUMBER_DECIMAL
             */
            val NUMBER_DECIMAL: VirtualKeyboardType get() = VirtualKeyboardType(3L)
            /**
             * Virtual phone number keypad.
             *
             * Generated from Godot docs: LineEdit.KEYBOARD_TYPE_PHONE
             */
            val PHONE: VirtualKeyboardType get() = VirtualKeyboardType(4L)
            /**
             * Virtual keyboard with additional keys to assist with typing email addresses.
             *
             * Generated from Godot docs: LineEdit.KEYBOARD_TYPE_EMAIL_ADDRESS
             */
            val EMAIL_ADDRESS: VirtualKeyboardType get() = VirtualKeyboardType(5L)
            /**
             * Virtual keyboard for entering a password. On most platforms, this should disable autocomplete
             * and autocapitalization. Note: This is not supported on Web. Instead, this behaves identically to
             * `VirtualKeyboardType.DEFAULT`.
             *
             * Generated from Godot docs: LineEdit.KEYBOARD_TYPE_PASSWORD
             */
            val PASSWORD: VirtualKeyboardType get() = VirtualKeyboardType(6L)
            /**
             * Virtual keyboard with additional keys to assist with typing URLs.
             *
             * Generated from Godot docs: LineEdit.KEYBOARD_TYPE_URL
             */
            val URL: VirtualKeyboardType get() = VirtualKeyboardType(7L)
        }
    }

    /**
     * Godot's `LineEdit.ExpandMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`LineEdit.ExpandMode.<NAME>`).
     *
     * Generated from Godot docs: LineEdit.ExpandMode
     */
    @JvmInline
    value class ExpandMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use the original size for the right icon.
             *
             * Generated from Godot docs: LineEdit.EXPAND_MODE_ORIGINAL_SIZE
             */
            val ORIGINAL_SIZE: ExpandMode get() = ExpandMode(0L)
            /**
             * Scale the right icon's size to match the size of the text.
             *
             * Generated from Godot docs: LineEdit.EXPAND_MODE_FIT_TO_TEXT
             */
            val FIT_TO_TEXT: ExpandMode get() = ExpandMode(1L)
            /**
             * Scale the right icon to fit the LineEdit.
             *
             * Generated from Godot docs: LineEdit.EXPAND_MODE_FIT_TO_LINE_EDIT
             */
            val FIT_TO_LINE_EDIT: ExpandMode get() = ExpandMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): LineEdit? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): LineEdit? =
            if (handle.address() == 0L) null else LineEdit(GodotHandle(handle))
    }

    private object Binds {
        private const val HAS_IME_TEXT_HASH = 36873697L
        @JvmField
        val hasImeTextBind =
            ObjectCalls.getMethodBind("LineEdit", "has_ime_text", HAS_IME_TEXT_HASH)

        private const val CANCEL_IME_HASH = 3218959716L
        @JvmField
        val cancelImeBind =
            ObjectCalls.getMethodBind("LineEdit", "cancel_ime", CANCEL_IME_HASH)

        private const val APPLY_IME_HASH = 3218959716L
        @JvmField
        val applyImeBind =
            ObjectCalls.getMethodBind("LineEdit", "apply_ime", APPLY_IME_HASH)

        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        @JvmField
        val setHorizontalAlignmentBind =
            ObjectCalls.getMethodBind("LineEdit", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)

        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        @JvmField
        val getHorizontalAlignmentBind =
            ObjectCalls.getMethodBind("LineEdit", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)

        private const val EDIT_HASH = 107499316L
        @JvmField
        val editBind =
            ObjectCalls.getMethodBind("LineEdit", "edit", EDIT_HASH)

        private const val UNEDIT_HASH = 3218959716L
        @JvmField
        val uneditBind =
            ObjectCalls.getMethodBind("LineEdit", "unedit", UNEDIT_HASH)

        private const val IS_EDITING_HASH = 36873697L
        @JvmField
        val isEditingBind =
            ObjectCalls.getMethodBind("LineEdit", "is_editing", IS_EDITING_HASH)

        private const val SET_KEEP_EDITING_ON_TEXT_SUBMIT_HASH = 2586408642L
        @JvmField
        val setKeepEditingOnTextSubmitBind =
            ObjectCalls.getMethodBind("LineEdit", "set_keep_editing_on_text_submit", SET_KEEP_EDITING_ON_TEXT_SUBMIT_HASH)

        private const val IS_EDITING_KEPT_ON_TEXT_SUBMIT_HASH = 36873697L
        @JvmField
        val isEditingKeptOnTextSubmitBind =
            ObjectCalls.getMethodBind("LineEdit", "is_editing_kept_on_text_submit", IS_EDITING_KEPT_ON_TEXT_SUBMIT_HASH)

        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("LineEdit", "clear", CLEAR_HASH)

        private const val SELECT_HASH = 1328111411L
        @JvmField
        val selectBind =
            ObjectCalls.getMethodBind("LineEdit", "select", SELECT_HASH)

        private const val SELECT_ALL_HASH = 3218959716L
        @JvmField
        val selectAllBind =
            ObjectCalls.getMethodBind("LineEdit", "select_all", SELECT_ALL_HASH)

        private const val DESELECT_HASH = 3218959716L
        @JvmField
        val deselectBind =
            ObjectCalls.getMethodBind("LineEdit", "deselect", DESELECT_HASH)

        private const val HAS_UNDO_HASH = 36873697L
        @JvmField
        val hasUndoBind =
            ObjectCalls.getMethodBind("LineEdit", "has_undo", HAS_UNDO_HASH)

        private const val HAS_REDO_HASH = 36873697L
        @JvmField
        val hasRedoBind =
            ObjectCalls.getMethodBind("LineEdit", "has_redo", HAS_REDO_HASH)

        private const val HAS_SELECTION_HASH = 36873697L
        @JvmField
        val hasSelectionBind =
            ObjectCalls.getMethodBind("LineEdit", "has_selection", HAS_SELECTION_HASH)

        private const val GET_SELECTED_TEXT_HASH = 2841200299L
        @JvmField
        val getSelectedTextBind =
            ObjectCalls.getMethodBind("LineEdit", "get_selected_text", GET_SELECTED_TEXT_HASH)

        private const val GET_SELECTION_FROM_COLUMN_HASH = 3905245786L
        @JvmField
        val getSelectionFromColumnBind =
            ObjectCalls.getMethodBind("LineEdit", "get_selection_from_column", GET_SELECTION_FROM_COLUMN_HASH)

        private const val GET_SELECTION_TO_COLUMN_HASH = 3905245786L
        @JvmField
        val getSelectionToColumnBind =
            ObjectCalls.getMethodBind("LineEdit", "get_selection_to_column", GET_SELECTION_TO_COLUMN_HASH)

        private const val SET_TEXT_HASH = 83702148L
        @JvmField
        val setTextBind =
            ObjectCalls.getMethodBind("LineEdit", "set_text", SET_TEXT_HASH)

        private const val GET_TEXT_HASH = 201670096L
        @JvmField
        val getTextBind =
            ObjectCalls.getMethodBind("LineEdit", "get_text", GET_TEXT_HASH)

        private const val GET_DRAW_CONTROL_CHARS_HASH = 36873697L
        @JvmField
        val getDrawControlCharsBind =
            ObjectCalls.getMethodBind("LineEdit", "get_draw_control_chars", GET_DRAW_CONTROL_CHARS_HASH)

        private const val SET_DRAW_CONTROL_CHARS_HASH = 2586408642L
        @JvmField
        val setDrawControlCharsBind =
            ObjectCalls.getMethodBind("LineEdit", "set_draw_control_chars", SET_DRAW_CONTROL_CHARS_HASH)

        private const val SET_TEXT_DIRECTION_HASH = 119160795L
        @JvmField
        val setTextDirectionBind =
            ObjectCalls.getMethodBind("LineEdit", "set_text_direction", SET_TEXT_DIRECTION_HASH)

        private const val GET_TEXT_DIRECTION_HASH = 797257663L
        @JvmField
        val getTextDirectionBind =
            ObjectCalls.getMethodBind("LineEdit", "get_text_direction", GET_TEXT_DIRECTION_HASH)

        private const val SET_LANGUAGE_HASH = 83702148L
        @JvmField
        val setLanguageBind =
            ObjectCalls.getMethodBind("LineEdit", "set_language", SET_LANGUAGE_HASH)

        private const val GET_LANGUAGE_HASH = 201670096L
        @JvmField
        val getLanguageBind =
            ObjectCalls.getMethodBind("LineEdit", "get_language", GET_LANGUAGE_HASH)

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 55961453L
        @JvmField
        val setStructuredTextBidiOverrideBind =
            ObjectCalls.getMethodBind("LineEdit", "set_structured_text_bidi_override", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3385126229L
        @JvmField
        val getStructuredTextBidiOverrideBind =
            ObjectCalls.getMethodBind("LineEdit", "get_structured_text_bidi_override", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
        @JvmField
        val setStructuredTextBidiOverrideOptionsBind =
            ObjectCalls.getMethodBind("LineEdit", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 3995934104L
        @JvmField
        val getStructuredTextBidiOverrideOptionsBind =
            ObjectCalls.getMethodBind("LineEdit", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)

        private const val SET_PLACEHOLDER_HASH = 83702148L
        @JvmField
        val setPlaceholderBind =
            ObjectCalls.getMethodBind("LineEdit", "set_placeholder", SET_PLACEHOLDER_HASH)

        private const val GET_PLACEHOLDER_HASH = 201670096L
        @JvmField
        val getPlaceholderBind =
            ObjectCalls.getMethodBind("LineEdit", "get_placeholder", GET_PLACEHOLDER_HASH)

        private const val SET_CARET_COLUMN_HASH = 1286410249L
        @JvmField
        val setCaretColumnBind =
            ObjectCalls.getMethodBind("LineEdit", "set_caret_column", SET_CARET_COLUMN_HASH)

        private const val GET_CARET_COLUMN_HASH = 3905245786L
        @JvmField
        val getCaretColumnBind =
            ObjectCalls.getMethodBind("LineEdit", "get_caret_column", GET_CARET_COLUMN_HASH)

        private const val GET_NEXT_COMPOSITE_CHARACTER_COLUMN_HASH = 923996154L
        @JvmField
        val getNextCompositeCharacterColumnBind =
            ObjectCalls.getMethodBind("LineEdit", "get_next_composite_character_column", GET_NEXT_COMPOSITE_CHARACTER_COLUMN_HASH)

        private const val GET_PREVIOUS_COMPOSITE_CHARACTER_COLUMN_HASH = 923996154L
        @JvmField
        val getPreviousCompositeCharacterColumnBind =
            ObjectCalls.getMethodBind("LineEdit", "get_previous_composite_character_column", GET_PREVIOUS_COMPOSITE_CHARACTER_COLUMN_HASH)

        private const val GET_SCROLL_OFFSET_HASH = 1740695150L
        @JvmField
        val getScrollOffsetBind =
            ObjectCalls.getMethodBind("LineEdit", "get_scroll_offset", GET_SCROLL_OFFSET_HASH)

        private const val SET_EXPAND_TO_TEXT_LENGTH_ENABLED_HASH = 2586408642L
        @JvmField
        val setExpandToTextLengthEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "set_expand_to_text_length_enabled", SET_EXPAND_TO_TEXT_LENGTH_ENABLED_HASH)

        private const val IS_EXPAND_TO_TEXT_LENGTH_ENABLED_HASH = 36873697L
        @JvmField
        val isExpandToTextLengthEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "is_expand_to_text_length_enabled", IS_EXPAND_TO_TEXT_LENGTH_ENABLED_HASH)

        private const val SET_CARET_BLINK_ENABLED_HASH = 2586408642L
        @JvmField
        val setCaretBlinkEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "set_caret_blink_enabled", SET_CARET_BLINK_ENABLED_HASH)

        private const val IS_CARET_BLINK_ENABLED_HASH = 36873697L
        @JvmField
        val isCaretBlinkEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "is_caret_blink_enabled", IS_CARET_BLINK_ENABLED_HASH)

        private const val SET_CARET_MID_GRAPHEME_ENABLED_HASH = 2586408642L
        @JvmField
        val setCaretMidGraphemeEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "set_caret_mid_grapheme_enabled", SET_CARET_MID_GRAPHEME_ENABLED_HASH)

        private const val IS_CARET_MID_GRAPHEME_ENABLED_HASH = 36873697L
        @JvmField
        val isCaretMidGraphemeEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "is_caret_mid_grapheme_enabled", IS_CARET_MID_GRAPHEME_ENABLED_HASH)

        private const val SET_CARET_FORCE_DISPLAYED_HASH = 2586408642L
        @JvmField
        val setCaretForceDisplayedBind =
            ObjectCalls.getMethodBind("LineEdit", "set_caret_force_displayed", SET_CARET_FORCE_DISPLAYED_HASH)

        private const val IS_CARET_FORCE_DISPLAYED_HASH = 36873697L
        @JvmField
        val isCaretForceDisplayedBind =
            ObjectCalls.getMethodBind("LineEdit", "is_caret_force_displayed", IS_CARET_FORCE_DISPLAYED_HASH)

        private const val SET_CARET_BLINK_INTERVAL_HASH = 373806689L
        @JvmField
        val setCaretBlinkIntervalBind =
            ObjectCalls.getMethodBind("LineEdit", "set_caret_blink_interval", SET_CARET_BLINK_INTERVAL_HASH)

        private const val GET_CARET_BLINK_INTERVAL_HASH = 1740695150L
        @JvmField
        val getCaretBlinkIntervalBind =
            ObjectCalls.getMethodBind("LineEdit", "get_caret_blink_interval", GET_CARET_BLINK_INTERVAL_HASH)

        private const val SET_MAX_LENGTH_HASH = 1286410249L
        @JvmField
        val setMaxLengthBind =
            ObjectCalls.getMethodBind("LineEdit", "set_max_length", SET_MAX_LENGTH_HASH)

        private const val GET_MAX_LENGTH_HASH = 3905245786L
        @JvmField
        val getMaxLengthBind =
            ObjectCalls.getMethodBind("LineEdit", "get_max_length", GET_MAX_LENGTH_HASH)

        private const val INSERT_TEXT_AT_CARET_HASH = 83702148L
        @JvmField
        val insertTextAtCaretBind =
            ObjectCalls.getMethodBind("LineEdit", "insert_text_at_caret", INSERT_TEXT_AT_CARET_HASH)

        private const val DELETE_CHAR_AT_CARET_HASH = 3218959716L
        @JvmField
        val deleteCharAtCaretBind =
            ObjectCalls.getMethodBind("LineEdit", "delete_char_at_caret", DELETE_CHAR_AT_CARET_HASH)

        private const val DELETE_TEXT_HASH = 3937882851L
        @JvmField
        val deleteTextBind =
            ObjectCalls.getMethodBind("LineEdit", "delete_text", DELETE_TEXT_HASH)

        private const val SET_EDITABLE_HASH = 2586408642L
        @JvmField
        val setEditableBind =
            ObjectCalls.getMethodBind("LineEdit", "set_editable", SET_EDITABLE_HASH)

        private const val IS_EDITABLE_HASH = 36873697L
        @JvmField
        val isEditableBind =
            ObjectCalls.getMethodBind("LineEdit", "is_editable", IS_EDITABLE_HASH)

        private const val SET_SECRET_HASH = 2586408642L
        @JvmField
        val setSecretBind =
            ObjectCalls.getMethodBind("LineEdit", "set_secret", SET_SECRET_HASH)

        private const val IS_SECRET_HASH = 36873697L
        @JvmField
        val isSecretBind =
            ObjectCalls.getMethodBind("LineEdit", "is_secret", IS_SECRET_HASH)

        private const val SET_SECRET_CHARACTER_HASH = 83702148L
        @JvmField
        val setSecretCharacterBind =
            ObjectCalls.getMethodBind("LineEdit", "set_secret_character", SET_SECRET_CHARACTER_HASH)

        private const val GET_SECRET_CHARACTER_HASH = 201670096L
        @JvmField
        val getSecretCharacterBind =
            ObjectCalls.getMethodBind("LineEdit", "get_secret_character", GET_SECRET_CHARACTER_HASH)

        private const val MENU_OPTION_HASH = 1286410249L
        @JvmField
        val menuOptionBind =
            ObjectCalls.getMethodBind("LineEdit", "menu_option", MENU_OPTION_HASH)

        private const val GET_MENU_HASH = 229722558L
        @JvmField
        val getMenuBind =
            ObjectCalls.getMethodBind("LineEdit", "get_menu", GET_MENU_HASH)

        private const val IS_MENU_VISIBLE_HASH = 36873697L
        @JvmField
        val isMenuVisibleBind =
            ObjectCalls.getMethodBind("LineEdit", "is_menu_visible", IS_MENU_VISIBLE_HASH)

        private const val SET_CONTEXT_MENU_ENABLED_HASH = 2586408642L
        @JvmField
        val setContextMenuEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "set_context_menu_enabled", SET_CONTEXT_MENU_ENABLED_HASH)

        private const val IS_CONTEXT_MENU_ENABLED_HASH = 2240911060L
        @JvmField
        val isContextMenuEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "is_context_menu_enabled", IS_CONTEXT_MENU_ENABLED_HASH)

        private const val SET_EMOJI_MENU_ENABLED_HASH = 2586408642L
        @JvmField
        val setEmojiMenuEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "set_emoji_menu_enabled", SET_EMOJI_MENU_ENABLED_HASH)

        private const val IS_EMOJI_MENU_ENABLED_HASH = 36873697L
        @JvmField
        val isEmojiMenuEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "is_emoji_menu_enabled", IS_EMOJI_MENU_ENABLED_HASH)

        private const val SET_BACKSPACE_DELETES_COMPOSITE_CHARACTER_ENABLED_HASH = 2586408642L
        @JvmField
        val setBackspaceDeletesCompositeCharacterEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "set_backspace_deletes_composite_character_enabled", SET_BACKSPACE_DELETES_COMPOSITE_CHARACTER_ENABLED_HASH)

        private const val IS_BACKSPACE_DELETES_COMPOSITE_CHARACTER_ENABLED_HASH = 36873697L
        @JvmField
        val isBackspaceDeletesCompositeCharacterEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "is_backspace_deletes_composite_character_enabled", IS_BACKSPACE_DELETES_COMPOSITE_CHARACTER_ENABLED_HASH)

        private const val SET_VIRTUAL_KEYBOARD_ENABLED_HASH = 2586408642L
        @JvmField
        val setVirtualKeyboardEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "set_virtual_keyboard_enabled", SET_VIRTUAL_KEYBOARD_ENABLED_HASH)

        private const val IS_VIRTUAL_KEYBOARD_ENABLED_HASH = 36873697L
        @JvmField
        val isVirtualKeyboardEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "is_virtual_keyboard_enabled", IS_VIRTUAL_KEYBOARD_ENABLED_HASH)

        private const val SET_VIRTUAL_KEYBOARD_SHOW_ON_FOCUS_HASH = 2586408642L
        @JvmField
        val setVirtualKeyboardShowOnFocusBind =
            ObjectCalls.getMethodBind("LineEdit", "set_virtual_keyboard_show_on_focus", SET_VIRTUAL_KEYBOARD_SHOW_ON_FOCUS_HASH)

        private const val GET_VIRTUAL_KEYBOARD_SHOW_ON_FOCUS_HASH = 36873697L
        @JvmField
        val getVirtualKeyboardShowOnFocusBind =
            ObjectCalls.getMethodBind("LineEdit", "get_virtual_keyboard_show_on_focus", GET_VIRTUAL_KEYBOARD_SHOW_ON_FOCUS_HASH)

        private const val SET_VIRTUAL_KEYBOARD_TYPE_HASH = 2696893573L
        @JvmField
        val setVirtualKeyboardTypeBind =
            ObjectCalls.getMethodBind("LineEdit", "set_virtual_keyboard_type", SET_VIRTUAL_KEYBOARD_TYPE_HASH)

        private const val GET_VIRTUAL_KEYBOARD_TYPE_HASH = 1928699316L
        @JvmField
        val getVirtualKeyboardTypeBind =
            ObjectCalls.getMethodBind("LineEdit", "get_virtual_keyboard_type", GET_VIRTUAL_KEYBOARD_TYPE_HASH)

        private const val SET_CLEAR_BUTTON_ENABLED_HASH = 2586408642L
        @JvmField
        val setClearButtonEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "set_clear_button_enabled", SET_CLEAR_BUTTON_ENABLED_HASH)

        private const val IS_CLEAR_BUTTON_ENABLED_HASH = 36873697L
        @JvmField
        val isClearButtonEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "is_clear_button_enabled", IS_CLEAR_BUTTON_ENABLED_HASH)

        private const val SET_SHORTCUT_KEYS_ENABLED_HASH = 2586408642L
        @JvmField
        val setShortcutKeysEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "set_shortcut_keys_enabled", SET_SHORTCUT_KEYS_ENABLED_HASH)

        private const val IS_SHORTCUT_KEYS_ENABLED_HASH = 36873697L
        @JvmField
        val isShortcutKeysEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "is_shortcut_keys_enabled", IS_SHORTCUT_KEYS_ENABLED_HASH)

        private const val SET_MIDDLE_MOUSE_PASTE_ENABLED_HASH = 2586408642L
        @JvmField
        val setMiddleMousePasteEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "set_middle_mouse_paste_enabled", SET_MIDDLE_MOUSE_PASTE_ENABLED_HASH)

        private const val IS_MIDDLE_MOUSE_PASTE_ENABLED_HASH = 36873697L
        @JvmField
        val isMiddleMousePasteEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "is_middle_mouse_paste_enabled", IS_MIDDLE_MOUSE_PASTE_ENABLED_HASH)

        private const val SET_SELECTING_ENABLED_HASH = 2586408642L
        @JvmField
        val setSelectingEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "set_selecting_enabled", SET_SELECTING_ENABLED_HASH)

        private const val IS_SELECTING_ENABLED_HASH = 36873697L
        @JvmField
        val isSelectingEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "is_selecting_enabled", IS_SELECTING_ENABLED_HASH)

        private const val SET_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH = 2586408642L
        @JvmField
        val setDeselectOnFocusLossEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "set_deselect_on_focus_loss_enabled", SET_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH)

        private const val IS_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH = 36873697L
        @JvmField
        val isDeselectOnFocusLossEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "is_deselect_on_focus_loss_enabled", IS_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH)

        private const val SET_DRAG_AND_DROP_SELECTION_ENABLED_HASH = 2586408642L
        @JvmField
        val setDragAndDropSelectionEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "set_drag_and_drop_selection_enabled", SET_DRAG_AND_DROP_SELECTION_ENABLED_HASH)

        private const val IS_DRAG_AND_DROP_SELECTION_ENABLED_HASH = 36873697L
        @JvmField
        val isDragAndDropSelectionEnabledBind =
            ObjectCalls.getMethodBind("LineEdit", "is_drag_and_drop_selection_enabled", IS_DRAG_AND_DROP_SELECTION_ENABLED_HASH)

        private const val SET_RIGHT_ICON_HASH = 4051416890L
        @JvmField
        val setRightIconBind =
            ObjectCalls.getMethodBind("LineEdit", "set_right_icon", SET_RIGHT_ICON_HASH)

        private const val GET_RIGHT_ICON_HASH = 255860311L
        @JvmField
        val getRightIconBind =
            ObjectCalls.getMethodBind("LineEdit", "get_right_icon", GET_RIGHT_ICON_HASH)

        private const val SET_ICON_EXPAND_MODE_HASH = 3019903192L
        @JvmField
        val setIconExpandModeBind =
            ObjectCalls.getMethodBind("LineEdit", "set_icon_expand_mode", SET_ICON_EXPAND_MODE_HASH)

        private const val GET_ICON_EXPAND_MODE_HASH = 3273584435L
        @JvmField
        val getIconExpandModeBind =
            ObjectCalls.getMethodBind("LineEdit", "get_icon_expand_mode", GET_ICON_EXPAND_MODE_HASH)

        private const val SET_RIGHT_ICON_SCALE_HASH = 373806689L
        @JvmField
        val setRightIconScaleBind =
            ObjectCalls.getMethodBind("LineEdit", "set_right_icon_scale", SET_RIGHT_ICON_SCALE_HASH)

        private const val GET_RIGHT_ICON_SCALE_HASH = 1740695150L
        @JvmField
        val getRightIconScaleBind =
            ObjectCalls.getMethodBind("LineEdit", "get_right_icon_scale", GET_RIGHT_ICON_SCALE_HASH)

        private const val SET_FLAT_HASH = 2586408642L
        @JvmField
        val setFlatBind =
            ObjectCalls.getMethodBind("LineEdit", "set_flat", SET_FLAT_HASH)

        private const val IS_FLAT_HASH = 36873697L
        @JvmField
        val isFlatBind =
            ObjectCalls.getMethodBind("LineEdit", "is_flat", IS_FLAT_HASH)

        private const val SET_SELECT_ALL_ON_FOCUS_HASH = 2586408642L
        @JvmField
        val setSelectAllOnFocusBind =
            ObjectCalls.getMethodBind("LineEdit", "set_select_all_on_focus", SET_SELECT_ALL_ON_FOCUS_HASH)

        private const val IS_SELECT_ALL_ON_FOCUS_HASH = 36873697L
        @JvmField
        val isSelectAllOnFocusBind =
            ObjectCalls.getMethodBind("LineEdit", "is_select_all_on_focus", IS_SELECT_ALL_ON_FOCUS_HASH)
    }
}
