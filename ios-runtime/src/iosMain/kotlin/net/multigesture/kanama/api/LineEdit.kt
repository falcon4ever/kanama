package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: LineEdit
 */
class LineEdit(handle: MemorySegment) : Control(handle) {
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

    var alignment: Long
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

    var virtualKeyboardType: Long
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

    fun hasImeText(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasImeTextBind, handle)
    }

    fun cancelIme() {
        ObjectCalls.ptrcallNoArgs(cancelImeBind, handle)
    }

    fun applyIme() {
        ObjectCalls.ptrcallNoArgs(applyImeBind, handle)
    }

    fun setHorizontalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setHorizontalAlignmentBind, handle, alignment)
    }

    fun getHorizontalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHorizontalAlignmentBind, handle)
    }

    fun edit(hideFocus: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(editBind, handle, hideFocus)
    }

    fun unedit() {
        ObjectCalls.ptrcallNoArgs(uneditBind, handle)
    }

    fun isEditing(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditingBind, handle)
    }

    fun setKeepEditingOnTextSubmit(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setKeepEditingOnTextSubmitBind, handle, enable)
    }

    fun isEditingKeptOnTextSubmit(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditingKeptOnTextSubmitBind, handle)
    }

    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun select(from: Int = 0, to: Int = -1) {
        ObjectCalls.ptrcallWithTwoIntArgs(selectBind, handle, from, to)
    }

    fun selectAll() {
        ObjectCalls.ptrcallNoArgs(selectAllBind, handle)
    }

    fun deselect() {
        ObjectCalls.ptrcallNoArgs(deselectBind, handle)
    }

    fun hasUndo(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasUndoBind, handle)
    }

    fun hasRedo(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasRedoBind, handle)
    }

    fun hasSelection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasSelectionBind, handle)
    }

    fun getSelectedText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSelectedTextBind, handle)
    }

    fun getSelectionFromColumn(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSelectionFromColumnBind, handle)
    }

    fun getSelectionToColumn(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSelectionToColumnBind, handle)
    }

    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, handle, text)
    }

    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextBind, handle)
    }

    fun getDrawControlChars(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDrawControlCharsBind, handle)
    }

    fun setDrawControlChars(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawControlCharsBind, handle, enable)
    }

    fun setTextDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextDirectionBind, handle, direction)
    }

    fun getTextDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextDirectionBind, handle)
    }

    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(setLanguageBind, handle, language)
    }

    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageBind, handle)
    }

    fun setStructuredTextBidiOverride(parser: Long) {
        ObjectCalls.ptrcallWithLongArg(setStructuredTextBidiOverrideBind, handle, parser)
    }

    fun getStructuredTextBidiOverride(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStructuredTextBidiOverrideBind, handle)
    }

    fun setStructuredTextBidiOverrideOptions(args: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setStructuredTextBidiOverrideOptionsBind, handle, args)
    }

    fun getStructuredTextBidiOverrideOptions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getStructuredTextBidiOverrideOptionsBind, handle)
    }

    fun setPlaceholder(text: String) {
        ObjectCalls.ptrcallWithStringArg(setPlaceholderBind, handle, text)
    }

    fun getPlaceholder(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPlaceholderBind, handle)
    }

    fun setCaretColumn(position: Int) {
        ObjectCalls.ptrcallWithIntArg(setCaretColumnBind, handle, position)
    }

    fun getCaretColumn(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCaretColumnBind, handle)
    }

    fun getNextCompositeCharacterColumn(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getNextCompositeCharacterColumnBind, handle, column)
    }

    fun getPreviousCompositeCharacterColumn(column: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getPreviousCompositeCharacterColumnBind, handle, column)
    }

    fun getScrollOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getScrollOffsetBind, handle)
    }

    fun setExpandToTextLengthEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setExpandToTextLengthEnabledBind, handle, enabled)
    }

    fun isExpandToTextLengthEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isExpandToTextLengthEnabledBind, handle)
    }

    fun setCaretBlinkEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCaretBlinkEnabledBind, handle, enabled)
    }

    fun isCaretBlinkEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCaretBlinkEnabledBind, handle)
    }

    fun setCaretMidGraphemeEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCaretMidGraphemeEnabledBind, handle, enabled)
    }

    fun isCaretMidGraphemeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCaretMidGraphemeEnabledBind, handle)
    }

    fun setCaretForceDisplayed(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCaretForceDisplayedBind, handle, enabled)
    }

    fun isCaretForceDisplayed(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCaretForceDisplayedBind, handle)
    }

    fun setCaretBlinkInterval(interval: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCaretBlinkIntervalBind, handle, interval)
    }

    fun getCaretBlinkInterval(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCaretBlinkIntervalBind, handle)
    }

    fun setMaxLength(chars: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxLengthBind, handle, chars)
    }

    fun getMaxLength(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxLengthBind, handle)
    }

    fun insertTextAtCaret(text: String) {
        ObjectCalls.ptrcallWithStringArg(insertTextAtCaretBind, handle, text)
    }

    fun deleteCharAtCaret() {
        ObjectCalls.ptrcallNoArgs(deleteCharAtCaretBind, handle)
    }

    fun deleteText(fromColumn: Int, toColumn: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(deleteTextBind, handle, fromColumn, toColumn)
    }

    fun setEditable(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditableBind, handle, enabled)
    }

    fun isEditable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditableBind, handle)
    }

    fun setSecret(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSecretBind, handle, enabled)
    }

    fun isSecret(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSecretBind, handle)
    }

    fun setSecretCharacter(character: String) {
        ObjectCalls.ptrcallWithStringArg(setSecretCharacterBind, handle, character)
    }

    fun getSecretCharacter(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSecretCharacterBind, handle)
    }

    fun menuOption(option: Int) {
        ObjectCalls.ptrcallWithIntArg(menuOptionBind, handle, option)
    }

    fun getMenu(): PopupMenu? {
        return PopupMenu.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMenuBind, handle))
    }

    fun isMenuVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMenuVisibleBind, handle)
    }

    fun setContextMenuEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setContextMenuEnabledBind, handle, enable)
    }

    fun isContextMenuEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isContextMenuEnabledBind, handle)
    }

    fun setEmojiMenuEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmojiMenuEnabledBind, handle, enable)
    }

    fun isEmojiMenuEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmojiMenuEnabledBind, handle)
    }

    fun setBackspaceDeletesCompositeCharacterEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBackspaceDeletesCompositeCharacterEnabledBind, handle, enable)
    }

    fun isBackspaceDeletesCompositeCharacterEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBackspaceDeletesCompositeCharacterEnabledBind, handle)
    }

    fun setVirtualKeyboardEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVirtualKeyboardEnabledBind, handle, enable)
    }

    fun isVirtualKeyboardEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isVirtualKeyboardEnabledBind, handle)
    }

    fun setVirtualKeyboardShowOnFocus(showOnFocus: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVirtualKeyboardShowOnFocusBind, handle, showOnFocus)
    }

    fun getVirtualKeyboardShowOnFocus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getVirtualKeyboardShowOnFocusBind, handle)
    }

    fun setVirtualKeyboardType(type: Long) {
        ObjectCalls.ptrcallWithLongArg(setVirtualKeyboardTypeBind, handle, type)
    }

    fun getVirtualKeyboardType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVirtualKeyboardTypeBind, handle)
    }

    fun setClearButtonEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClearButtonEnabledBind, handle, enable)
    }

    fun isClearButtonEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isClearButtonEnabledBind, handle)
    }

    fun setShortcutKeysEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShortcutKeysEnabledBind, handle, enable)
    }

    fun isShortcutKeysEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShortcutKeysEnabledBind, handle)
    }

    fun setMiddleMousePasteEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMiddleMousePasteEnabledBind, handle, enable)
    }

    fun isMiddleMousePasteEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMiddleMousePasteEnabledBind, handle)
    }

    fun setSelectingEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSelectingEnabledBind, handle, enable)
    }

    fun isSelectingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSelectingEnabledBind, handle)
    }

    fun setDeselectOnFocusLossEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDeselectOnFocusLossEnabledBind, handle, enable)
    }

    fun isDeselectOnFocusLossEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDeselectOnFocusLossEnabledBind, handle)
    }

    fun setDragAndDropSelectionEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDragAndDropSelectionEnabledBind, handle, enable)
    }

    fun isDragAndDropSelectionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDragAndDropSelectionEnabledBind, handle)
    }

    fun setRightIcon(icon: Texture2D?) {
        ObjectCalls.ptrcallWithObjectArgs(setRightIconBind, handle, listOf(icon?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getRightIcon(): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getRightIconBind, handle))
    }

    fun setIconExpandMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setIconExpandModeBind, handle, mode)
    }

    fun getIconExpandMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getIconExpandModeBind, handle)
    }

    fun setRightIconScale(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRightIconScaleBind, handle, scale)
    }

    fun getRightIconScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRightIconScaleBind, handle)
    }

    fun setFlat(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFlatBind, handle, enabled)
    }

    fun isFlat(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFlatBind, handle)
    }

    fun setSelectAllOnFocus(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSelectAllOnFocusBind, handle, enabled)
    }

    fun isSelectAllOnFocus(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSelectAllOnFocusBind, handle)
    }

    object Signals {
        const val textChanged: String = "text_changed"
        const val textChangeRejected: String = "text_change_rejected"
        const val textSubmitted: String = "text_submitted"
        const val editingToggled: String = "editing_toggled"
    }

    companion object {
        const val MENU_CUT: Long = 0L
        const val MENU_COPY: Long = 1L
        const val MENU_PASTE: Long = 2L
        const val MENU_CLEAR: Long = 3L
        const val MENU_SELECT_ALL: Long = 4L
        const val MENU_UNDO: Long = 5L
        const val MENU_REDO: Long = 6L
        const val MENU_SUBMENU_TEXT_DIR: Long = 7L
        const val MENU_DIR_INHERITED: Long = 8L
        const val MENU_DIR_AUTO: Long = 9L
        const val MENU_DIR_LTR: Long = 10L
        const val MENU_DIR_RTL: Long = 11L
        const val MENU_DISPLAY_UCC: Long = 12L
        const val MENU_SUBMENU_INSERT_UCC: Long = 13L
        const val MENU_INSERT_LRM: Long = 14L
        const val MENU_INSERT_RLM: Long = 15L
        const val MENU_INSERT_LRE: Long = 16L
        const val MENU_INSERT_RLE: Long = 17L
        const val MENU_INSERT_LRO: Long = 18L
        const val MENU_INSERT_RLO: Long = 19L
        const val MENU_INSERT_PDF: Long = 20L
        const val MENU_INSERT_ALM: Long = 21L
        const val MENU_INSERT_LRI: Long = 22L
        const val MENU_INSERT_RLI: Long = 23L
        const val MENU_INSERT_FSI: Long = 24L
        const val MENU_INSERT_PDI: Long = 25L
        const val MENU_INSERT_ZWJ: Long = 26L
        const val MENU_INSERT_ZWNJ: Long = 27L
        const val MENU_INSERT_WJ: Long = 28L
        const val MENU_INSERT_SHY: Long = 29L
        const val MENU_EMOJI_AND_SYMBOL: Long = 30L
        const val MENU_MAX: Long = 31L
        const val KEYBOARD_TYPE_DEFAULT: Long = 0L
        const val KEYBOARD_TYPE_MULTILINE: Long = 1L
        const val KEYBOARD_TYPE_NUMBER: Long = 2L
        const val KEYBOARD_TYPE_NUMBER_DECIMAL: Long = 3L
        const val KEYBOARD_TYPE_PHONE: Long = 4L
        const val KEYBOARD_TYPE_EMAIL_ADDRESS: Long = 5L
        const val KEYBOARD_TYPE_PASSWORD: Long = 6L
        const val KEYBOARD_TYPE_URL: Long = 7L
        const val EXPAND_MODE_ORIGINAL_SIZE: Long = 0L
        const val EXPAND_MODE_FIT_TO_TEXT: Long = 1L
        const val EXPAND_MODE_FIT_TO_LINE_EDIT: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): LineEdit? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): LineEdit? =
            if (handle.address() == 0L) null else LineEdit(handle)

        private const val HAS_IME_TEXT_HASH = 36873697L
        private val hasImeTextBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "has_ime_text", HAS_IME_TEXT_HASH)
        }

        private const val CANCEL_IME_HASH = 3218959716L
        private val cancelImeBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "cancel_ime", CANCEL_IME_HASH)
        }

        private const val APPLY_IME_HASH = 3218959716L
        private val applyImeBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "apply_ime", APPLY_IME_HASH)
        }

        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        private val setHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        private val getHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val EDIT_HASH = 107499316L
        private val editBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "edit", EDIT_HASH)
        }

        private const val UNEDIT_HASH = 3218959716L
        private val uneditBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "unedit", UNEDIT_HASH)
        }

        private const val IS_EDITING_HASH = 36873697L
        private val isEditingBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_editing", IS_EDITING_HASH)
        }

        private const val SET_KEEP_EDITING_ON_TEXT_SUBMIT_HASH = 2586408642L
        private val setKeepEditingOnTextSubmitBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_keep_editing_on_text_submit", SET_KEEP_EDITING_ON_TEXT_SUBMIT_HASH)
        }

        private const val IS_EDITING_KEPT_ON_TEXT_SUBMIT_HASH = 36873697L
        private val isEditingKeptOnTextSubmitBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_editing_kept_on_text_submit", IS_EDITING_KEPT_ON_TEXT_SUBMIT_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "clear", CLEAR_HASH)
        }

        private const val SELECT_HASH = 1328111411L
        private val selectBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "select", SELECT_HASH)
        }

        private const val SELECT_ALL_HASH = 3218959716L
        private val selectAllBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "select_all", SELECT_ALL_HASH)
        }

        private const val DESELECT_HASH = 3218959716L
        private val deselectBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "deselect", DESELECT_HASH)
        }

        private const val HAS_UNDO_HASH = 36873697L
        private val hasUndoBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "has_undo", HAS_UNDO_HASH)
        }

        private const val HAS_REDO_HASH = 36873697L
        private val hasRedoBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "has_redo", HAS_REDO_HASH)
        }

        private const val HAS_SELECTION_HASH = 36873697L
        private val hasSelectionBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "has_selection", HAS_SELECTION_HASH)
        }

        private const val GET_SELECTED_TEXT_HASH = 2841200299L
        private val getSelectedTextBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_selected_text", GET_SELECTED_TEXT_HASH)
        }

        private const val GET_SELECTION_FROM_COLUMN_HASH = 3905245786L
        private val getSelectionFromColumnBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_selection_from_column", GET_SELECTION_FROM_COLUMN_HASH)
        }

        private const val GET_SELECTION_TO_COLUMN_HASH = 3905245786L
        private val getSelectionToColumnBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_selection_to_column", GET_SELECTION_TO_COLUMN_HASH)
        }

        private const val SET_TEXT_HASH = 83702148L
        private val setTextBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_text", SET_TEXT_HASH)
        }

        private const val GET_TEXT_HASH = 201670096L
        private val getTextBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_text", GET_TEXT_HASH)
        }

        private const val GET_DRAW_CONTROL_CHARS_HASH = 36873697L
        private val getDrawControlCharsBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_draw_control_chars", GET_DRAW_CONTROL_CHARS_HASH)
        }

        private const val SET_DRAW_CONTROL_CHARS_HASH = 2586408642L
        private val setDrawControlCharsBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_draw_control_chars", SET_DRAW_CONTROL_CHARS_HASH)
        }

        private const val SET_TEXT_DIRECTION_HASH = 119160795L
        private val setTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_text_direction", SET_TEXT_DIRECTION_HASH)
        }

        private const val GET_TEXT_DIRECTION_HASH = 797257663L
        private val getTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_text_direction", GET_TEXT_DIRECTION_HASH)
        }

        private const val SET_LANGUAGE_HASH = 83702148L
        private val setLanguageBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_language", SET_LANGUAGE_HASH)
        }

        private const val GET_LANGUAGE_HASH = 201670096L
        private val getLanguageBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_language", GET_LANGUAGE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 55961453L
        private val setStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_structured_text_bidi_override", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3385126229L
        private val getStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_structured_text_bidi_override", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
        private val setStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 3995934104L
        private val getStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val SET_PLACEHOLDER_HASH = 83702148L
        private val setPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_placeholder", SET_PLACEHOLDER_HASH)
        }

        private const val GET_PLACEHOLDER_HASH = 201670096L
        private val getPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_placeholder", GET_PLACEHOLDER_HASH)
        }

        private const val SET_CARET_COLUMN_HASH = 1286410249L
        private val setCaretColumnBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_caret_column", SET_CARET_COLUMN_HASH)
        }

        private const val GET_CARET_COLUMN_HASH = 3905245786L
        private val getCaretColumnBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_caret_column", GET_CARET_COLUMN_HASH)
        }

        private const val GET_NEXT_COMPOSITE_CHARACTER_COLUMN_HASH = 923996154L
        private val getNextCompositeCharacterColumnBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_next_composite_character_column", GET_NEXT_COMPOSITE_CHARACTER_COLUMN_HASH)
        }

        private const val GET_PREVIOUS_COMPOSITE_CHARACTER_COLUMN_HASH = 923996154L
        private val getPreviousCompositeCharacterColumnBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_previous_composite_character_column", GET_PREVIOUS_COMPOSITE_CHARACTER_COLUMN_HASH)
        }

        private const val GET_SCROLL_OFFSET_HASH = 1740695150L
        private val getScrollOffsetBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_scroll_offset", GET_SCROLL_OFFSET_HASH)
        }

        private const val SET_EXPAND_TO_TEXT_LENGTH_ENABLED_HASH = 2586408642L
        private val setExpandToTextLengthEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_expand_to_text_length_enabled", SET_EXPAND_TO_TEXT_LENGTH_ENABLED_HASH)
        }

        private const val IS_EXPAND_TO_TEXT_LENGTH_ENABLED_HASH = 36873697L
        private val isExpandToTextLengthEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_expand_to_text_length_enabled", IS_EXPAND_TO_TEXT_LENGTH_ENABLED_HASH)
        }

        private const val SET_CARET_BLINK_ENABLED_HASH = 2586408642L
        private val setCaretBlinkEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_caret_blink_enabled", SET_CARET_BLINK_ENABLED_HASH)
        }

        private const val IS_CARET_BLINK_ENABLED_HASH = 36873697L
        private val isCaretBlinkEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_caret_blink_enabled", IS_CARET_BLINK_ENABLED_HASH)
        }

        private const val SET_CARET_MID_GRAPHEME_ENABLED_HASH = 2586408642L
        private val setCaretMidGraphemeEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_caret_mid_grapheme_enabled", SET_CARET_MID_GRAPHEME_ENABLED_HASH)
        }

        private const val IS_CARET_MID_GRAPHEME_ENABLED_HASH = 36873697L
        private val isCaretMidGraphemeEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_caret_mid_grapheme_enabled", IS_CARET_MID_GRAPHEME_ENABLED_HASH)
        }

        private const val SET_CARET_FORCE_DISPLAYED_HASH = 2586408642L
        private val setCaretForceDisplayedBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_caret_force_displayed", SET_CARET_FORCE_DISPLAYED_HASH)
        }

        private const val IS_CARET_FORCE_DISPLAYED_HASH = 36873697L
        private val isCaretForceDisplayedBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_caret_force_displayed", IS_CARET_FORCE_DISPLAYED_HASH)
        }

        private const val SET_CARET_BLINK_INTERVAL_HASH = 373806689L
        private val setCaretBlinkIntervalBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_caret_blink_interval", SET_CARET_BLINK_INTERVAL_HASH)
        }

        private const val GET_CARET_BLINK_INTERVAL_HASH = 1740695150L
        private val getCaretBlinkIntervalBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_caret_blink_interval", GET_CARET_BLINK_INTERVAL_HASH)
        }

        private const val SET_MAX_LENGTH_HASH = 1286410249L
        private val setMaxLengthBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_max_length", SET_MAX_LENGTH_HASH)
        }

        private const val GET_MAX_LENGTH_HASH = 3905245786L
        private val getMaxLengthBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_max_length", GET_MAX_LENGTH_HASH)
        }

        private const val INSERT_TEXT_AT_CARET_HASH = 83702148L
        private val insertTextAtCaretBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "insert_text_at_caret", INSERT_TEXT_AT_CARET_HASH)
        }

        private const val DELETE_CHAR_AT_CARET_HASH = 3218959716L
        private val deleteCharAtCaretBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "delete_char_at_caret", DELETE_CHAR_AT_CARET_HASH)
        }

        private const val DELETE_TEXT_HASH = 3937882851L
        private val deleteTextBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "delete_text", DELETE_TEXT_HASH)
        }

        private const val SET_EDITABLE_HASH = 2586408642L
        private val setEditableBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_editable", SET_EDITABLE_HASH)
        }

        private const val IS_EDITABLE_HASH = 36873697L
        private val isEditableBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_editable", IS_EDITABLE_HASH)
        }

        private const val SET_SECRET_HASH = 2586408642L
        private val setSecretBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_secret", SET_SECRET_HASH)
        }

        private const val IS_SECRET_HASH = 36873697L
        private val isSecretBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_secret", IS_SECRET_HASH)
        }

        private const val SET_SECRET_CHARACTER_HASH = 83702148L
        private val setSecretCharacterBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_secret_character", SET_SECRET_CHARACTER_HASH)
        }

        private const val GET_SECRET_CHARACTER_HASH = 201670096L
        private val getSecretCharacterBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_secret_character", GET_SECRET_CHARACTER_HASH)
        }

        private const val MENU_OPTION_HASH = 1286410249L
        private val menuOptionBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "menu_option", MENU_OPTION_HASH)
        }

        private const val GET_MENU_HASH = 229722558L
        private val getMenuBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_menu", GET_MENU_HASH)
        }

        private const val IS_MENU_VISIBLE_HASH = 36873697L
        private val isMenuVisibleBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_menu_visible", IS_MENU_VISIBLE_HASH)
        }

        private const val SET_CONTEXT_MENU_ENABLED_HASH = 2586408642L
        private val setContextMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_context_menu_enabled", SET_CONTEXT_MENU_ENABLED_HASH)
        }

        private const val IS_CONTEXT_MENU_ENABLED_HASH = 2240911060L
        private val isContextMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_context_menu_enabled", IS_CONTEXT_MENU_ENABLED_HASH)
        }

        private const val SET_EMOJI_MENU_ENABLED_HASH = 2586408642L
        private val setEmojiMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_emoji_menu_enabled", SET_EMOJI_MENU_ENABLED_HASH)
        }

        private const val IS_EMOJI_MENU_ENABLED_HASH = 36873697L
        private val isEmojiMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_emoji_menu_enabled", IS_EMOJI_MENU_ENABLED_HASH)
        }

        private const val SET_BACKSPACE_DELETES_COMPOSITE_CHARACTER_ENABLED_HASH = 2586408642L
        private val setBackspaceDeletesCompositeCharacterEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_backspace_deletes_composite_character_enabled", SET_BACKSPACE_DELETES_COMPOSITE_CHARACTER_ENABLED_HASH)
        }

        private const val IS_BACKSPACE_DELETES_COMPOSITE_CHARACTER_ENABLED_HASH = 36873697L
        private val isBackspaceDeletesCompositeCharacterEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_backspace_deletes_composite_character_enabled", IS_BACKSPACE_DELETES_COMPOSITE_CHARACTER_ENABLED_HASH)
        }

        private const val SET_VIRTUAL_KEYBOARD_ENABLED_HASH = 2586408642L
        private val setVirtualKeyboardEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_virtual_keyboard_enabled", SET_VIRTUAL_KEYBOARD_ENABLED_HASH)
        }

        private const val IS_VIRTUAL_KEYBOARD_ENABLED_HASH = 36873697L
        private val isVirtualKeyboardEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_virtual_keyboard_enabled", IS_VIRTUAL_KEYBOARD_ENABLED_HASH)
        }

        private const val SET_VIRTUAL_KEYBOARD_SHOW_ON_FOCUS_HASH = 2586408642L
        private val setVirtualKeyboardShowOnFocusBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_virtual_keyboard_show_on_focus", SET_VIRTUAL_KEYBOARD_SHOW_ON_FOCUS_HASH)
        }

        private const val GET_VIRTUAL_KEYBOARD_SHOW_ON_FOCUS_HASH = 36873697L
        private val getVirtualKeyboardShowOnFocusBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_virtual_keyboard_show_on_focus", GET_VIRTUAL_KEYBOARD_SHOW_ON_FOCUS_HASH)
        }

        private const val SET_VIRTUAL_KEYBOARD_TYPE_HASH = 2696893573L
        private val setVirtualKeyboardTypeBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_virtual_keyboard_type", SET_VIRTUAL_KEYBOARD_TYPE_HASH)
        }

        private const val GET_VIRTUAL_KEYBOARD_TYPE_HASH = 1928699316L
        private val getVirtualKeyboardTypeBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_virtual_keyboard_type", GET_VIRTUAL_KEYBOARD_TYPE_HASH)
        }

        private const val SET_CLEAR_BUTTON_ENABLED_HASH = 2586408642L
        private val setClearButtonEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_clear_button_enabled", SET_CLEAR_BUTTON_ENABLED_HASH)
        }

        private const val IS_CLEAR_BUTTON_ENABLED_HASH = 36873697L
        private val isClearButtonEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_clear_button_enabled", IS_CLEAR_BUTTON_ENABLED_HASH)
        }

        private const val SET_SHORTCUT_KEYS_ENABLED_HASH = 2586408642L
        private val setShortcutKeysEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_shortcut_keys_enabled", SET_SHORTCUT_KEYS_ENABLED_HASH)
        }

        private const val IS_SHORTCUT_KEYS_ENABLED_HASH = 36873697L
        private val isShortcutKeysEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_shortcut_keys_enabled", IS_SHORTCUT_KEYS_ENABLED_HASH)
        }

        private const val SET_MIDDLE_MOUSE_PASTE_ENABLED_HASH = 2586408642L
        private val setMiddleMousePasteEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_middle_mouse_paste_enabled", SET_MIDDLE_MOUSE_PASTE_ENABLED_HASH)
        }

        private const val IS_MIDDLE_MOUSE_PASTE_ENABLED_HASH = 36873697L
        private val isMiddleMousePasteEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_middle_mouse_paste_enabled", IS_MIDDLE_MOUSE_PASTE_ENABLED_HASH)
        }

        private const val SET_SELECTING_ENABLED_HASH = 2586408642L
        private val setSelectingEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_selecting_enabled", SET_SELECTING_ENABLED_HASH)
        }

        private const val IS_SELECTING_ENABLED_HASH = 36873697L
        private val isSelectingEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_selecting_enabled", IS_SELECTING_ENABLED_HASH)
        }

        private const val SET_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH = 2586408642L
        private val setDeselectOnFocusLossEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_deselect_on_focus_loss_enabled", SET_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH)
        }

        private const val IS_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH = 36873697L
        private val isDeselectOnFocusLossEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_deselect_on_focus_loss_enabled", IS_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH)
        }

        private const val SET_DRAG_AND_DROP_SELECTION_ENABLED_HASH = 2586408642L
        private val setDragAndDropSelectionEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_drag_and_drop_selection_enabled", SET_DRAG_AND_DROP_SELECTION_ENABLED_HASH)
        }

        private const val IS_DRAG_AND_DROP_SELECTION_ENABLED_HASH = 36873697L
        private val isDragAndDropSelectionEnabledBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_drag_and_drop_selection_enabled", IS_DRAG_AND_DROP_SELECTION_ENABLED_HASH)
        }

        private const val SET_RIGHT_ICON_HASH = 4051416890L
        private val setRightIconBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_right_icon", SET_RIGHT_ICON_HASH)
        }

        private const val GET_RIGHT_ICON_HASH = 255860311L
        private val getRightIconBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_right_icon", GET_RIGHT_ICON_HASH)
        }

        private const val SET_ICON_EXPAND_MODE_HASH = 3019903192L
        private val setIconExpandModeBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_icon_expand_mode", SET_ICON_EXPAND_MODE_HASH)
        }

        private const val GET_ICON_EXPAND_MODE_HASH = 3273584435L
        private val getIconExpandModeBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_icon_expand_mode", GET_ICON_EXPAND_MODE_HASH)
        }

        private const val SET_RIGHT_ICON_SCALE_HASH = 373806689L
        private val setRightIconScaleBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_right_icon_scale", SET_RIGHT_ICON_SCALE_HASH)
        }

        private const val GET_RIGHT_ICON_SCALE_HASH = 1740695150L
        private val getRightIconScaleBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "get_right_icon_scale", GET_RIGHT_ICON_SCALE_HASH)
        }

        private const val SET_FLAT_HASH = 2586408642L
        private val setFlatBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_flat", SET_FLAT_HASH)
        }

        private const val IS_FLAT_HASH = 36873697L
        private val isFlatBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_flat", IS_FLAT_HASH)
        }

        private const val SET_SELECT_ALL_ON_FOCUS_HASH = 2586408642L
        private val setSelectAllOnFocusBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "set_select_all_on_focus", SET_SELECT_ALL_ON_FOCUS_HASH)
        }

        private const val IS_SELECT_ALL_ON_FOCUS_HASH = 36873697L
        private val isSelectAllOnFocusBind by lazy {
            ObjectCalls.getMethodBind("LineEdit", "is_select_all_on_focus", IS_SELECT_ALL_ON_FOCUS_HASH)
        }
    }
}
