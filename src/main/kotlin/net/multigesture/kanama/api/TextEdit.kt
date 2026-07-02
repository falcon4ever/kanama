package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * A multiline text editor.
 *
 * Generated from Godot docs: TextEdit
 */
open class TextEdit(handle: MemorySegment) : Control(handle) {
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

    var editable: Boolean
        @JvmName("editableProperty")
        get() = isEditable()
        @JvmName("setEditableProperty")
        set(value) = setEditable(value)

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

    var shortcutKeysEnabled: Boolean
        @JvmName("shortcutKeysEnabledProperty")
        get() = isShortcutKeysEnabled()
        @JvmName("setShortcutKeysEnabledProperty")
        set(value) = setShortcutKeysEnabled(value)

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

    var middleMousePasteEnabled: Boolean
        @JvmName("middleMousePasteEnabledProperty")
        get() = isMiddleMousePasteEnabled()
        @JvmName("setMiddleMousePasteEnabledProperty")
        set(value) = setMiddleMousePasteEnabled(value)

    var emptySelectionClipboardEnabled: Boolean
        @JvmName("emptySelectionClipboardEnabledProperty")
        get() = isEmptySelectionClipboardEnabled()
        @JvmName("setEmptySelectionClipboardEnabledProperty")
        set(value) = setEmptySelectionClipboardEnabled(value)

    var wrapMode: Long
        @JvmName("wrapModeProperty")
        get() = getLineWrappingMode()
        @JvmName("setWrapModeProperty")
        set(value) = setLineWrappingMode(value)

    var autowrapMode: Long
        @JvmName("autowrapModeProperty")
        get() = getAutowrapMode()
        @JvmName("setAutowrapModeProperty")
        set(value) = setAutowrapMode(value)

    var indentWrappedLines: Boolean
        @JvmName("indentWrappedLinesProperty")
        get() = isIndentWrappedLines()
        @JvmName("setIndentWrappedLinesProperty")
        set(value) = setIndentWrappedLines(value)

    var tabInputMode: Boolean
        @JvmName("tabInputModeProperty")
        get() = getTabInputMode()
        @JvmName("setTabInputModeProperty")
        set(value) = setTabInputMode(value)

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

    var scrollSmooth: Boolean
        @JvmName("scrollSmoothProperty")
        get() = isSmoothScrollEnabled()
        @JvmName("setScrollSmoothProperty")
        set(value) = setSmoothScrollEnabled(value)

    var scrollVScrollSpeed: Double
        @JvmName("scrollVScrollSpeedProperty")
        get() = getVScrollSpeed()
        @JvmName("setScrollVScrollSpeedProperty")
        set(value) = setVScrollSpeed(value)

    var scrollPastEndOfFile: Boolean
        @JvmName("scrollPastEndOfFileProperty")
        get() = isScrollPastEndOfFileEnabled()
        @JvmName("setScrollPastEndOfFileProperty")
        set(value) = setScrollPastEndOfFileEnabled(value)

    var scrollVertical: Double
        @JvmName("scrollVerticalProperty")
        get() = getVScroll()
        @JvmName("setScrollVerticalProperty")
        set(value) = setVScroll(value)

    var scrollHorizontal: Int
        @JvmName("scrollHorizontalProperty")
        get() = getHScroll()
        @JvmName("setScrollHorizontalProperty")
        set(value) = setHScroll(value)

    var scrollFitContentHeight: Boolean
        @JvmName("scrollFitContentHeightProperty")
        get() = isFitContentHeightEnabled()
        @JvmName("setScrollFitContentHeightProperty")
        set(value) = setFitContentHeightEnabled(value)

    var scrollFitContentWidth: Boolean
        @JvmName("scrollFitContentWidthProperty")
        get() = isFitContentWidthEnabled()
        @JvmName("setScrollFitContentWidthProperty")
        set(value) = setFitContentWidthEnabled(value)

    var minimapDraw: Boolean
        @JvmName("minimapDrawProperty")
        get() = isDrawingMinimap()
        @JvmName("setMinimapDrawProperty")
        set(value) = setDrawMinimap(value)

    var minimapWidth: Int
        @JvmName("minimapWidthProperty")
        get() = getMinimapWidth()
        @JvmName("setMinimapWidthProperty")
        set(value) = setMinimapWidth(value)

    var caretType: Long
        @JvmName("caretTypeProperty")
        get() = getCaretType()
        @JvmName("setCaretTypeProperty")
        set(value) = setCaretType(value)

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

    var caretDrawWhenEditableDisabled: Boolean
        @JvmName("caretDrawWhenEditableDisabledProperty")
        get() = isDrawingCaretWhenEditableDisabled()
        @JvmName("setCaretDrawWhenEditableDisabledProperty")
        set(value) = setDrawCaretWhenEditableDisabled(value)

    var caretMoveOnRightClick: Boolean
        @JvmName("caretMoveOnRightClickProperty")
        get() = isMoveCaretOnRightClickEnabled()
        @JvmName("setCaretMoveOnRightClickProperty")
        set(value) = setMoveCaretOnRightClickEnabled(value)

    var caretMidGrapheme: Boolean
        @JvmName("caretMidGraphemeProperty")
        get() = isCaretMidGraphemeEnabled()
        @JvmName("setCaretMidGraphemeProperty")
        set(value) = setCaretMidGraphemeEnabled(value)

    var caretMultiple: Boolean
        @JvmName("caretMultipleProperty")
        get() = isMultipleCaretsEnabled()
        @JvmName("setCaretMultipleProperty")
        set(value) = setMultipleCaretsEnabled(value)

    var useDefaultWordSeparators: Boolean
        @JvmName("useDefaultWordSeparatorsProperty")
        get() = isDefaultWordSeparatorsEnabled()
        @JvmName("setUseDefaultWordSeparatorsProperty")
        set(value) = setUseDefaultWordSeparators(value)

    var useCustomWordSeparators: Boolean
        @JvmName("useCustomWordSeparatorsProperty")
        get() = isCustomWordSeparatorsEnabled()
        @JvmName("setUseCustomWordSeparatorsProperty")
        set(value) = setUseCustomWordSeparators(value)

    var customWordSeparators: String
        @JvmName("customWordSeparatorsProperty")
        get() = getCustomWordSeparators()
        @JvmName("setCustomWordSeparatorsProperty")
        set(value) = setCustomWordSeparators(value)

    var syntaxHighlighter: SyntaxHighlighter?
        @JvmName("syntaxHighlighterProperty")
        get() = getSyntaxHighlighter()
        @JvmName("setSyntaxHighlighterProperty")
        set(value) = setSyntaxHighlighter(value)

    var highlightAllOccurrences: Boolean
        @JvmName("highlightAllOccurrencesProperty")
        get() = isHighlightAllOccurrencesEnabled()
        @JvmName("setHighlightAllOccurrencesProperty")
        set(value) = setHighlightAllOccurrences(value)

    var highlightCurrentLine: Boolean
        @JvmName("highlightCurrentLineProperty")
        get() = isHighlightCurrentLineEnabled()
        @JvmName("setHighlightCurrentLineProperty")
        set(value) = setHighlightCurrentLine(value)

    var drawControlChars: Boolean
        @JvmName("drawControlCharsProperty")
        get() = getDrawControlChars()
        @JvmName("setDrawControlCharsProperty")
        set(value) = setDrawControlChars(value)

    var drawTabs: Boolean
        @JvmName("drawTabsProperty")
        get() = isDrawingTabs()
        @JvmName("setDrawTabsProperty")
        set(value) = setDrawTabs(value)

    var drawSpaces: Boolean
        @JvmName("drawSpacesProperty")
        get() = isDrawingSpaces()
        @JvmName("setDrawSpacesProperty")
        set(value) = setDrawSpaces(value)

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

    fun hasImeText(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasImeTextBind, handle)
    }

    fun cancelIme() {
        ObjectCalls.ptrcallNoArgs(cancelImeBind, handle)
    }

    fun applyIme() {
        ObjectCalls.ptrcallNoArgs(applyImeBind, handle)
    }

    fun setEditable(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEditableBind, handle, enabled)
    }

    fun isEditable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEditableBind, handle)
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

    fun setTabSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setTabSizeBind, handle, size)
    }

    fun getTabSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTabSizeBind, handle)
    }

    fun setIndentWrappedLines(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIndentWrappedLinesBind, handle, enabled)
    }

    fun isIndentWrappedLines(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIndentWrappedLinesBind, handle)
    }

    fun setTabInputMode(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTabInputModeBind, handle, enabled)
    }

    fun getTabInputMode(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getTabInputModeBind, handle)
    }

    fun setOvertypeModeEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOvertypeModeEnabledBind, handle, enabled)
    }

    fun isOvertypeModeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOvertypeModeEnabledBind, handle)
    }

    fun setContextMenuEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setContextMenuEnabledBind, handle, enabled)
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

    fun setShortcutKeysEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShortcutKeysEnabledBind, handle, enabled)
    }

    fun isShortcutKeysEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShortcutKeysEnabledBind, handle)
    }

    fun setVirtualKeyboardEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setVirtualKeyboardEnabledBind, handle, enabled)
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

    fun setMiddleMousePasteEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMiddleMousePasteEnabledBind, handle, enabled)
    }

    fun isMiddleMousePasteEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMiddleMousePasteEnabledBind, handle)
    }

    fun setEmptySelectionClipboardEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEmptySelectionClipboardEnabledBind, handle, enabled)
    }

    fun isEmptySelectionClipboardEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEmptySelectionClipboardEnabledBind, handle)
    }

    /**
     * Performs a full reset of `TextEdit`, including undo history.
     *
     * Generated from Godot docs: TextEdit.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, handle, text)
    }

    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextBind, handle)
    }

    fun getLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLineCountBind, handle)
    }

    fun setPlaceholder(text: String) {
        ObjectCalls.ptrcallWithStringArg(setPlaceholderBind, handle, text)
    }

    fun getPlaceholder(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getPlaceholderBind, handle)
    }

    fun setLine(line: Int, newText: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setLineBind, handle, line, newText)
    }

    fun getLine(line: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getLineBind, handle, line)
    }

    fun getLineWithIme(line: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getLineWithImeBind, handle, line)
    }

    fun getLineWidth(line: Int, wrapIndex: Int = -1): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getLineWidthBind, handle, line, wrapIndex)
    }

    fun getLineHeight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLineHeightBind, handle)
    }

    fun getIndentLevel(line: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getIndentLevelBind, handle, line)
    }

    fun getFirstNonWhitespaceColumn(line: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getFirstNonWhitespaceColumnBind, handle, line)
    }

    fun swapLines(fromLine: Int, toLine: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(swapLinesBind, handle, fromLine, toLine)
    }

    fun insertLineAt(line: Int, text: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(insertLineAtBind, handle, line, text)
    }

    fun removeLineAt(line: Int, moveCaretsDown: Boolean = true) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(removeLineAtBind, handle, line, moveCaretsDown)
    }

    fun insertTextAtCaret(text: String, caretIndex: Int = -1) {
        ObjectCalls.ptrcallWithStringAndIntArg(insertTextAtCaretBind, handle, text, caretIndex)
    }

    fun insertText(text: String, line: Int, column: Int, beforeSelectionBegin: Boolean = true, beforeSelectionEnd: Boolean = false) {
        ObjectCalls.ptrcallWithStringTwoIntTwoBoolArgs(insertTextBind, handle, text, line, column, beforeSelectionBegin, beforeSelectionEnd)
    }

    fun removeText(fromLine: Int, fromColumn: Int, toLine: Int, toColumn: Int) {
        ObjectCalls.ptrcallWithFourIntArgs(removeTextBind, handle, fromLine, fromColumn, toLine, toColumn)
    }

    fun getLastUnhiddenLine(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLastUnhiddenLineBind, handle)
    }

    fun getNextVisibleLineOffsetFrom(line: Int, visibleAmount: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getNextVisibleLineOffsetFromBind, handle, line, visibleAmount)
    }

    fun getNextVisibleLineIndexOffsetFrom(line: Int, wrapIndex: Int, visibleAmount: Int): Vector2i {
        return ObjectCalls.ptrcallWithThreeIntArgsRetVector2i(getNextVisibleLineIndexOffsetFromBind, handle, line, wrapIndex, visibleAmount)
    }

    /**
     * Called when the user presses the backspace key. Can be overridden with `_backspace`.
     *
     * Generated from Godot docs: TextEdit.backspace
     */
    fun backspace(caretIndex: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(backspaceBind, handle, caretIndex)
    }

    /**
     * Cut's the current selection. Can be overridden with `_cut`.
     *
     * Generated from Godot docs: TextEdit.cut
     */
    fun cut(caretIndex: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(cutBind, handle, caretIndex)
    }

    /**
     * Copies the current text selection. Can be overridden with `_copy`.
     *
     * Generated from Godot docs: TextEdit.copy
     */
    fun copy(caretIndex: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(copyBind, handle, caretIndex)
    }

    /**
     * Paste at the current location. Can be overridden with `_paste`.
     *
     * Generated from Godot docs: TextEdit.paste
     */
    fun paste(caretIndex: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(pasteBind, handle, caretIndex)
    }

    fun pastePrimaryClipboard(caretIndex: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(pastePrimaryClipboardBind, handle, caretIndex)
    }

    fun startAction(action: Long) {
        ObjectCalls.ptrcallWithLongArg(startActionBind, handle, action)
    }

    fun endAction() {
        ObjectCalls.ptrcallNoArgs(endActionBind, handle)
    }

    fun beginComplexOperation() {
        ObjectCalls.ptrcallNoArgs(beginComplexOperationBind, handle)
    }

    fun endComplexOperation() {
        ObjectCalls.ptrcallNoArgs(endComplexOperationBind, handle)
    }

    fun hasUndo(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasUndoBind, handle)
    }

    fun hasRedo(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasRedoBind, handle)
    }

    /**
     * Perform undo operation.
     *
     * Generated from Godot docs: TextEdit.undo
     */
    fun undo() {
        ObjectCalls.ptrcallNoArgs(undoBind, handle)
    }

    /**
     * Perform redo operation.
     *
     * Generated from Godot docs: TextEdit.redo
     */
    fun redo() {
        ObjectCalls.ptrcallNoArgs(redoBind, handle)
    }

    fun clearUndoHistory() {
        ObjectCalls.ptrcallNoArgs(clearUndoHistoryBind, handle)
    }

    fun tagSavedVersion() {
        ObjectCalls.ptrcallNoArgs(tagSavedVersionBind, handle)
    }

    fun getVersion(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getVersionBind, handle)
    }

    fun getSavedVersion(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getSavedVersionBind, handle)
    }

    fun setSearchText(searchText: String) {
        ObjectCalls.ptrcallWithStringArg(setSearchTextBind, handle, searchText)
    }

    fun setSearchFlags(flags: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setSearchFlagsBind, handle, flags)
    }

    /**
     * Perform a search inside the text. Search flags can be specified in the `SearchFlags` enum. In
     * the returned vector, `x` is the column, `y` is the line. If no results are found, both are equal
     * to `-1`.
     *
     * Generated from Godot docs: TextEdit.search
     */
    fun search(text: String, flags: Long, fromLine: Int, fromColumn: Int): Vector2i {
        return ObjectCalls.ptrcallWithStringUInt32TwoIntArgsRetVector2i(searchBind, handle, text, flags, fromLine, fromColumn)
    }

    fun setTooltipRequestFunc(callback: GodotCallable) {
        ObjectCalls.ptrcallWithCallableArg(setTooltipRequestFuncBind, handle, callback.target.handle, callback.method)
    }

    fun getLocalMousePos(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLocalMousePosBind, handle)
    }

    fun getWordAtPos(position: Vector2): String {
        return ObjectCalls.ptrcallWithVector2ArgRetString(getWordAtPosBind, handle, position)
    }

    fun getLineColumnAtPos(position: Vector2i, clampLine: Boolean = true, clampColumn: Boolean = true): Vector2i {
        return ObjectCalls.ptrcallWithVector2iAndTwoBoolArgsRetVector2i(getLineColumnAtPosBind, handle, position, clampLine, clampColumn)
    }

    fun getPosAtLineColumn(line: Int, column: Int): Vector2i {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector2i(getPosAtLineColumnBind, handle, line, column)
    }

    fun getRectAtLineColumn(line: Int, column: Int): Rect2i {
        return ObjectCalls.ptrcallWithTwoIntArgsRetRect2i(getRectAtLineColumnBind, handle, line, column)
    }

    fun getMinimapLineAtPos(position: Vector2i): Int {
        return ObjectCalls.ptrcallWithVector2iArgRetInt(getMinimapLineAtPosBind, handle, position)
    }

    fun isDraggingCursor(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDraggingCursorBind, handle)
    }

    fun isMouseOverSelection(edges: Boolean, caretIndex: Int = -1): Boolean {
        return ObjectCalls.ptrcallWithBoolAndIntArgsRetBool(isMouseOverSelectionBind, handle, edges, caretIndex)
    }

    fun setCaretType(type: Long) {
        ObjectCalls.ptrcallWithLongArg(setCaretTypeBind, handle, type)
    }

    fun getCaretType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCaretTypeBind, handle)
    }

    fun setCaretBlinkEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCaretBlinkEnabledBind, handle, enable)
    }

    fun isCaretBlinkEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCaretBlinkEnabledBind, handle)
    }

    fun setCaretBlinkInterval(interval: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCaretBlinkIntervalBind, handle, interval)
    }

    fun getCaretBlinkInterval(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCaretBlinkIntervalBind, handle)
    }

    fun setDrawCaretWhenEditableDisabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawCaretWhenEditableDisabledBind, handle, enable)
    }

    fun isDrawingCaretWhenEditableDisabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawingCaretWhenEditableDisabledBind, handle)
    }

    fun setMoveCaretOnRightClickEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMoveCaretOnRightClickEnabledBind, handle, enable)
    }

    fun isMoveCaretOnRightClickEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMoveCaretOnRightClickEnabledBind, handle)
    }

    fun setCaretMidGraphemeEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCaretMidGraphemeEnabledBind, handle, enabled)
    }

    fun isCaretMidGraphemeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCaretMidGraphemeEnabledBind, handle)
    }

    fun setMultipleCaretsEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMultipleCaretsEnabledBind, handle, enabled)
    }

    fun isMultipleCaretsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMultipleCaretsEnabledBind, handle)
    }

    fun addCaret(line: Int, column: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(addCaretBind, handle, line, column)
    }

    fun removeCaret(caret: Int) {
        ObjectCalls.ptrcallWithIntArg(removeCaretBind, handle, caret)
    }

    fun removeSecondaryCarets() {
        ObjectCalls.ptrcallNoArgs(removeSecondaryCaretsBind, handle)
    }

    fun getCaretCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCaretCountBind, handle)
    }

    fun addCaretAtCarets(below: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(addCaretAtCaretsBind, handle, below)
    }

    fun getSortedCarets(includeIgnoredCarets: Boolean = false): List<Int> {
        return ObjectCalls.ptrcallWithBoolArgRetPackedInt32List(getSortedCaretsBind, handle, includeIgnoredCarets)
    }

    fun collapseCarets(fromLine: Int, fromColumn: Int, toLine: Int, toColumn: Int, inclusive: Boolean = false) {
        ObjectCalls.ptrcallWithFourIntAndBoolArgs(collapseCaretsBind, handle, fromLine, fromColumn, toLine, toColumn, inclusive)
    }

    fun mergeOverlappingCarets() {
        ObjectCalls.ptrcallNoArgs(mergeOverlappingCaretsBind, handle)
    }

    fun beginMulticaretEdit() {
        ObjectCalls.ptrcallNoArgs(beginMulticaretEditBind, handle)
    }

    fun endMulticaretEdit() {
        ObjectCalls.ptrcallNoArgs(endMulticaretEditBind, handle)
    }

    fun isInMulitcaretEdit(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInMulitcaretEditBind, handle)
    }

    fun multicaretEditIgnoreCaret(caretIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(multicaretEditIgnoreCaretBind, handle, caretIndex)
    }

    fun isCaretVisible(caretIndex: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isCaretVisibleBind, handle, caretIndex)
    }

    fun getCaretDrawPos(caretIndex: Int = 0): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getCaretDrawPosBind, handle, caretIndex)
    }

    fun setCaretLine(line: Int, adjustViewport: Boolean = true, canBeHidden: Boolean = true, wrapIndex: Int = 0, caretIndex: Int = 0) {
        ObjectCalls.ptrcallWithIntTwoBoolTwoIntArgs(setCaretLineBind, handle, line, adjustViewport, canBeHidden, wrapIndex, caretIndex)
    }

    fun getCaretLine(caretIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getCaretLineBind, handle, caretIndex)
    }

    fun setCaretColumn(column: Int, adjustViewport: Boolean = true, caretIndex: Int = 0) {
        ObjectCalls.ptrcallWithIntBoolIntArgs(setCaretColumnBind, handle, column, adjustViewport, caretIndex)
    }

    fun getCaretColumn(caretIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getCaretColumnBind, handle, caretIndex)
    }

    fun getNextCompositeCharacterColumn(line: Int, column: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getNextCompositeCharacterColumnBind, handle, line, column)
    }

    fun getPreviousCompositeCharacterColumn(line: Int, column: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getPreviousCompositeCharacterColumnBind, handle, line, column)
    }

    fun getCaretWrapIndex(caretIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getCaretWrapIndexBind, handle, caretIndex)
    }

    fun getWordUnderCaret(caretIndex: Int = -1): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getWordUnderCaretBind, handle, caretIndex)
    }

    fun setUseDefaultWordSeparators(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseDefaultWordSeparatorsBind, handle, enabled)
    }

    fun isDefaultWordSeparatorsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDefaultWordSeparatorsEnabledBind, handle)
    }

    fun setUseCustomWordSeparators(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseCustomWordSeparatorsBind, handle, enabled)
    }

    fun isCustomWordSeparatorsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCustomWordSeparatorsEnabledBind, handle)
    }

    fun setCustomWordSeparators(customWordSeparators: String) {
        ObjectCalls.ptrcallWithStringArg(setCustomWordSeparatorsBind, handle, customWordSeparators)
    }

    fun getCustomWordSeparators(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCustomWordSeparatorsBind, handle)
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

    fun setSelectionMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setSelectionModeBind, handle, mode)
    }

    fun getSelectionMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getSelectionModeBind, handle)
    }

    fun selectAll() {
        ObjectCalls.ptrcallNoArgs(selectAllBind, handle)
    }

    fun selectWordUnderCaret(caretIndex: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(selectWordUnderCaretBind, handle, caretIndex)
    }

    fun addSelectionForNextOccurrence() {
        ObjectCalls.ptrcallNoArgs(addSelectionForNextOccurrenceBind, handle)
    }

    fun skipSelectionForNextOccurrence() {
        ObjectCalls.ptrcallNoArgs(skipSelectionForNextOccurrenceBind, handle)
    }

    /**
     * Selects text from `origin_line` and `origin_column` to `caret_line` and `caret_column` for the
     * given `caret_index`. This moves the selection origin and the caret. If the positions are the
     * same, the selection will be deselected. If `selecting_enabled` is `false`, no selection will
     * occur. Note: If supporting multiple carets this will not check for any overlap. See
     * `merge_overlapping_carets`.
     *
     * Generated from Godot docs: TextEdit.select
     */
    fun select(originLine: Int, originColumn: Int, caretLine: Int, caretColumn: Int, caretIndex: Int = 0) {
        ObjectCalls.ptrcallWithFiveIntArgs(selectBind, handle, originLine, originColumn, caretLine, caretColumn, caretIndex)
    }

    fun hasSelection(caretIndex: Int = -1): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(hasSelectionBind, handle, caretIndex)
    }

    fun getSelectedText(caretIndex: Int = -1): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getSelectedTextBind, handle, caretIndex)
    }

    fun getSelectionAtLineColumn(line: Int, column: Int, includeEdges: Boolean = true, onlySelections: Boolean = true): Int {
        return ObjectCalls.ptrcallWithTwoIntTwoBoolArgsRetInt(getSelectionAtLineColumnBind, handle, line, column, includeEdges, onlySelections)
    }

    fun getLineRangesFromCarets(onlySelections: Boolean = false, mergeAdjacent: Boolean = true): List<Vector2i> {
        return ObjectCalls.ptrcallWithTwoBoolArgsRetVector2iList(getLineRangesFromCaretsBind, handle, onlySelections, mergeAdjacent)
    }

    fun getSelectionOriginLine(caretIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSelectionOriginLineBind, handle, caretIndex)
    }

    fun getSelectionOriginColumn(caretIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSelectionOriginColumnBind, handle, caretIndex)
    }

    fun setSelectionOriginLine(line: Int, canBeHidden: Boolean = true, wrapIndex: Int = -1, caretIndex: Int = 0) {
        ObjectCalls.ptrcallWithIntBoolTwoIntArgs(setSelectionOriginLineBind, handle, line, canBeHidden, wrapIndex, caretIndex)
    }

    fun setSelectionOriginColumn(column: Int, caretIndex: Int = 0) {
        ObjectCalls.ptrcallWithTwoIntArgs(setSelectionOriginColumnBind, handle, column, caretIndex)
    }

    fun getSelectionFromLine(caretIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSelectionFromLineBind, handle, caretIndex)
    }

    fun getSelectionFromColumn(caretIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSelectionFromColumnBind, handle, caretIndex)
    }

    fun getSelectionToLine(caretIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSelectionToLineBind, handle, caretIndex)
    }

    fun getSelectionToColumn(caretIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSelectionToColumnBind, handle, caretIndex)
    }

    fun isCaretAfterSelectionOrigin(caretIndex: Int = 0): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isCaretAfterSelectionOriginBind, handle, caretIndex)
    }

    /**
     * Deselects the current selection.
     *
     * Generated from Godot docs: TextEdit.deselect
     */
    fun deselect(caretIndex: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(deselectBind, handle, caretIndex)
    }

    fun deleteSelection(caretIndex: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(deleteSelectionBind, handle, caretIndex)
    }

    fun setLineWrappingMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setLineWrappingModeBind, handle, mode)
    }

    fun getLineWrappingMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLineWrappingModeBind, handle)
    }

    fun setAutowrapMode(autowrapMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapModeBind, handle, autowrapMode)
    }

    fun getAutowrapMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapModeBind, handle)
    }

    fun isLineWrapped(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineWrappedBind, handle, line)
    }

    fun getLineWrapCount(line: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getLineWrapCountBind, handle, line)
    }

    fun getLineWrapIndexAtColumn(line: Int, column: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getLineWrapIndexAtColumnBind, handle, line, column)
    }

    fun getLineWrappedText(line: Int): List<String> {
        return ObjectCalls.ptrcallWithIntArgRetPackedStringList(getLineWrappedTextBind, handle, line)
    }

    fun setSmoothScrollEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSmoothScrollEnabledBind, handle, enable)
    }

    fun isSmoothScrollEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSmoothScrollEnabledBind, handle)
    }

    fun getVScrollBar(): VScrollBar? {
        return VScrollBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVScrollBarBind, handle))
    }

    fun getHScrollBar(): HScrollBar? {
        return HScrollBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(getHScrollBarBind, handle))
    }

    fun setVScroll(value: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVScrollBind, handle, value)
    }

    fun getVScroll(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVScrollBind, handle)
    }

    fun setHScroll(value: Int) {
        ObjectCalls.ptrcallWithIntArg(setHScrollBind, handle, value)
    }

    fun getHScroll(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getHScrollBind, handle)
    }

    fun setScrollPastEndOfFileEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollPastEndOfFileEnabledBind, handle, enable)
    }

    fun isScrollPastEndOfFileEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollPastEndOfFileEnabledBind, handle)
    }

    fun setVScrollSpeed(speed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVScrollSpeedBind, handle, speed)
    }

    fun getVScrollSpeed(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVScrollSpeedBind, handle)
    }

    fun setFitContentHeightEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFitContentHeightEnabledBind, handle, enabled)
    }

    fun isFitContentHeightEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFitContentHeightEnabledBind, handle)
    }

    fun setFitContentWidthEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFitContentWidthEnabledBind, handle, enabled)
    }

    fun isFitContentWidthEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFitContentWidthEnabledBind, handle)
    }

    fun getScrollPosForLine(line: Int, wrapIndex: Int = 0): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getScrollPosForLineBind, handle, line, wrapIndex)
    }

    fun setLineAsFirstVisible(line: Int, wrapIndex: Int = 0) {
        ObjectCalls.ptrcallWithTwoIntArgs(setLineAsFirstVisibleBind, handle, line, wrapIndex)
    }

    fun getFirstVisibleLine(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFirstVisibleLineBind, handle)
    }

    fun isLineInViewport(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineInViewportBind, handle, line)
    }

    fun setLineAsCenterVisible(line: Int, wrapIndex: Int = 0) {
        ObjectCalls.ptrcallWithTwoIntArgs(setLineAsCenterVisibleBind, handle, line, wrapIndex)
    }

    fun setLineAsLastVisible(line: Int, wrapIndex: Int = 0) {
        ObjectCalls.ptrcallWithTwoIntArgs(setLineAsLastVisibleBind, handle, line, wrapIndex)
    }

    fun getLastFullVisibleLine(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLastFullVisibleLineBind, handle)
    }

    fun getLastFullVisibleLineWrapIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLastFullVisibleLineWrapIndexBind, handle)
    }

    fun getVisibleLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVisibleLineCountBind, handle)
    }

    fun getVisibleLineCountInRange(fromLine: Int, toLine: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getVisibleLineCountInRangeBind, handle, fromLine, toLine)
    }

    fun getTotalVisibleLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTotalVisibleLineCountBind, handle)
    }

    fun adjustViewportToCaret(caretIndex: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(adjustViewportToCaretBind, handle, caretIndex)
    }

    fun centerViewportToCaret(caretIndex: Int = 0) {
        ObjectCalls.ptrcallWithIntArg(centerViewportToCaretBind, handle, caretIndex)
    }

    fun setDrawMinimap(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawMinimapBind, handle, enabled)
    }

    fun isDrawingMinimap(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawingMinimapBind, handle)
    }

    fun setMinimapWidth(width: Int) {
        ObjectCalls.ptrcallWithIntArg(setMinimapWidthBind, handle, width)
    }

    fun getMinimapWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMinimapWidthBind, handle)
    }

    fun getMinimapVisibleLines(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMinimapVisibleLinesBind, handle)
    }

    fun addGutter(at: Int = -1) {
        ObjectCalls.ptrcallWithIntArg(addGutterBind, handle, at)
    }

    fun removeGutter(gutter: Int) {
        ObjectCalls.ptrcallWithIntArg(removeGutterBind, handle, gutter)
    }

    fun getGutterCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getGutterCountBind, handle)
    }

    fun setGutterName(gutter: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setGutterNameBind, handle, gutter, name)
    }

    fun getGutterName(gutter: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getGutterNameBind, handle, gutter)
    }

    fun setGutterType(gutter: Int, type: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setGutterTypeBind, handle, gutter, type)
    }

    fun getGutterType(gutter: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getGutterTypeBind, handle, gutter)
    }

    fun setGutterWidth(gutter: Int, width: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setGutterWidthBind, handle, gutter, width)
    }

    fun getGutterWidth(gutter: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getGutterWidthBind, handle, gutter)
    }

    fun setGutterDraw(gutter: Int, draw: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setGutterDrawBind, handle, gutter, draw)
    }

    fun isGutterDrawn(gutter: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isGutterDrawnBind, handle, gutter)
    }

    fun setGutterClickable(gutter: Int, clickable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setGutterClickableBind, handle, gutter, clickable)
    }

    fun isGutterClickable(gutter: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isGutterClickableBind, handle, gutter)
    }

    fun setGutterOverwritable(gutter: Int, overwritable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setGutterOverwritableBind, handle, gutter, overwritable)
    }

    fun isGutterOverwritable(gutter: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isGutterOverwritableBind, handle, gutter)
    }

    fun mergeGutters(fromLine: Int, toLine: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(mergeGuttersBind, handle, fromLine, toLine)
    }

    fun setGutterCustomDraw(column: Int, drawCallback: GodotCallable) {
        ObjectCalls.ptrcallWithIntCallableArgs(setGutterCustomDrawBind, handle, column, drawCallback.target.handle, drawCallback.method)
    }

    fun getTotalGutterWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTotalGutterWidthBind, handle)
    }

    fun setLineGutterMetadata(line: Int, gutter: Int, metadata: Any?) {
        ObjectCalls.ptrcallWithTwoIntAndVariantArg(setLineGutterMetadataBind, handle, line, gutter, metadata)
    }

    fun getLineGutterMetadata(line: Int, gutter: Int): Any? {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVariantScalar(getLineGutterMetadataBind, handle, line, gutter)
    }

    fun setLineGutterText(line: Int, gutter: Int, text: String) {
        ObjectCalls.ptrcallWithTwoIntAndStringArgs(setLineGutterTextBind, handle, line, gutter, text)
    }

    fun getLineGutterText(line: Int, gutter: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(getLineGutterTextBind, handle, line, gutter)
    }

    fun setLineGutterIcon(line: Int, gutter: Int, icon: Texture2D?) {
        ObjectCalls.ptrcallWithTwoIntAndObjectArg(setLineGutterIconBind, handle, line, gutter, icon?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getLineGutterIcon(line: Int, gutter: Int): Texture2D? {
        return Texture2D.wrap(ObjectCalls.ptrcallWithTwoIntArgsRetObject(getLineGutterIconBind, handle, line, gutter))
    }

    fun setLineGutterItemColor(line: Int, gutter: Int, color: Color) {
        ObjectCalls.ptrcallWithTwoIntAndColorArg(setLineGutterItemColorBind, handle, line, gutter, color)
    }

    fun getLineGutterItemColor(line: Int, gutter: Int): Color {
        return ObjectCalls.ptrcallWithTwoIntArgsRetColor(getLineGutterItemColorBind, handle, line, gutter)
    }

    fun setLineGutterClickable(line: Int, gutter: Int, clickable: Boolean) {
        ObjectCalls.ptrcallWithTwoIntAndBoolArgs(setLineGutterClickableBind, handle, line, gutter, clickable)
    }

    fun isLineGutterClickable(line: Int, gutter: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(isLineGutterClickableBind, handle, line, gutter)
    }

    fun setLineBackgroundColor(line: Int, color: Color) {
        ObjectCalls.ptrcallWithIntAndColorArg(setLineBackgroundColorBind, handle, line, color)
    }

    fun getLineBackgroundColor(line: Int): Color {
        return ObjectCalls.ptrcallWithIntArgRetColor(getLineBackgroundColorBind, handle, line)
    }

    fun setSyntaxHighlighter(syntaxHighlighter: SyntaxHighlighter?) {
        ObjectCalls.ptrcallWithObjectArgs(setSyntaxHighlighterBind, handle, listOf(syntaxHighlighter?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getSyntaxHighlighter(): SyntaxHighlighter? {
        return SyntaxHighlighter.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSyntaxHighlighterBind, handle))
    }

    fun setHighlightCurrentLine(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHighlightCurrentLineBind, handle, enabled)
    }

    fun isHighlightCurrentLineEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHighlightCurrentLineEnabledBind, handle)
    }

    fun setHighlightAllOccurrences(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHighlightAllOccurrencesBind, handle, enabled)
    }

    fun isHighlightAllOccurrencesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHighlightAllOccurrencesEnabledBind, handle)
    }

    fun getDrawControlChars(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDrawControlCharsBind, handle)
    }

    fun setDrawControlChars(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawControlCharsBind, handle, enabled)
    }

    fun setDrawTabs(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawTabsBind, handle, enabled)
    }

    fun isDrawingTabs(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawingTabsBind, handle)
    }

    fun setDrawSpaces(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawSpacesBind, handle, enabled)
    }

    fun isDrawingSpaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawingSpacesBind, handle)
    }

    fun getMenu(): PopupMenu? {
        return PopupMenu.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMenuBind, handle))
    }

    fun isMenuVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMenuVisibleBind, handle)
    }

    fun menuOption(option: Int) {
        ObjectCalls.ptrcallWithIntArg(menuOptionBind, handle, option)
    }

    fun adjustCaretsAfterEdit(caret: Int, fromLine: Int, fromCol: Int, toLine: Int, toCol: Int) {
        ObjectCalls.ptrcallWithFiveIntArgs(adjustCaretsAfterEditBind, handle, caret, fromLine, fromCol, toLine, toCol)
    }

    fun getCaretIndexEditOrder(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getCaretIndexEditOrderBind, handle)
    }

    fun getSelectionLine(caretIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSelectionLineBind, handle, caretIndex)
    }

    fun getSelectionColumn(caretIndex: Int = 0): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getSelectionColumnBind, handle, caretIndex)
    }

    object Signals {
        const val textSet: String = "text_set"
        const val textChanged: String = "text_changed"
        const val linesEditedFrom: String = "lines_edited_from"
        const val caretChanged: String = "caret_changed"
        const val gutterClicked: String = "gutter_clicked"
        const val gutterAdded: String = "gutter_added"
        const val gutterRemoved: String = "gutter_removed"
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
        const val ACTION_NONE: Long = 0L
        const val ACTION_TYPING: Long = 1L
        const val ACTION_BACKSPACE: Long = 2L
        const val ACTION_DELETE: Long = 3L
        const val SEARCH_MATCH_CASE: Long = 1L
        const val SEARCH_WHOLE_WORDS: Long = 2L
        const val SEARCH_BACKWARDS: Long = 4L
        const val CARET_TYPE_LINE: Long = 0L
        const val CARET_TYPE_BLOCK: Long = 1L
        const val SELECTION_MODE_NONE: Long = 0L
        const val SELECTION_MODE_SHIFT: Long = 1L
        const val SELECTION_MODE_POINTER: Long = 2L
        const val SELECTION_MODE_WORD: Long = 3L
        const val SELECTION_MODE_LINE: Long = 4L
        const val LINE_WRAPPING_NONE: Long = 0L
        const val LINE_WRAPPING_BOUNDARY: Long = 1L
        const val GUTTER_TYPE_STRING: Long = 0L
        const val GUTTER_TYPE_ICON: Long = 1L
        const val GUTTER_TYPE_CUSTOM: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): TextEdit? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextEdit? =
            if (handle.address() == 0L) null else TextEdit(handle)

        private const val HAS_IME_TEXT_HASH = 36873697L
        private val hasImeTextBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "has_ime_text", HAS_IME_TEXT_HASH)
        }

        private const val CANCEL_IME_HASH = 3218959716L
        private val cancelImeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "cancel_ime", CANCEL_IME_HASH)
        }

        private const val APPLY_IME_HASH = 3218959716L
        private val applyImeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "apply_ime", APPLY_IME_HASH)
        }

        private const val SET_EDITABLE_HASH = 2586408642L
        private val setEditableBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_editable", SET_EDITABLE_HASH)
        }

        private const val IS_EDITABLE_HASH = 36873697L
        private val isEditableBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_editable", IS_EDITABLE_HASH)
        }

        private const val SET_TEXT_DIRECTION_HASH = 119160795L
        private val setTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_text_direction", SET_TEXT_DIRECTION_HASH)
        }

        private const val GET_TEXT_DIRECTION_HASH = 797257663L
        private val getTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_text_direction", GET_TEXT_DIRECTION_HASH)
        }

        private const val SET_LANGUAGE_HASH = 83702148L
        private val setLanguageBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_language", SET_LANGUAGE_HASH)
        }

        private const val GET_LANGUAGE_HASH = 201670096L
        private val getLanguageBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_language", GET_LANGUAGE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 55961453L
        private val setStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_structured_text_bidi_override", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3385126229L
        private val getStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_structured_text_bidi_override", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
        private val setStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 3995934104L
        private val getStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val SET_TAB_SIZE_HASH = 1286410249L
        private val setTabSizeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_tab_size", SET_TAB_SIZE_HASH)
        }

        private const val GET_TAB_SIZE_HASH = 3905245786L
        private val getTabSizeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_tab_size", GET_TAB_SIZE_HASH)
        }

        private const val SET_INDENT_WRAPPED_LINES_HASH = 2586408642L
        private val setIndentWrappedLinesBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_indent_wrapped_lines", SET_INDENT_WRAPPED_LINES_HASH)
        }

        private const val IS_INDENT_WRAPPED_LINES_HASH = 36873697L
        private val isIndentWrappedLinesBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_indent_wrapped_lines", IS_INDENT_WRAPPED_LINES_HASH)
        }

        private const val SET_TAB_INPUT_MODE_HASH = 2586408642L
        private val setTabInputModeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_tab_input_mode", SET_TAB_INPUT_MODE_HASH)
        }

        private const val GET_TAB_INPUT_MODE_HASH = 36873697L
        private val getTabInputModeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_tab_input_mode", GET_TAB_INPUT_MODE_HASH)
        }

        private const val SET_OVERTYPE_MODE_ENABLED_HASH = 2586408642L
        private val setOvertypeModeEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_overtype_mode_enabled", SET_OVERTYPE_MODE_ENABLED_HASH)
        }

        private const val IS_OVERTYPE_MODE_ENABLED_HASH = 36873697L
        private val isOvertypeModeEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_overtype_mode_enabled", IS_OVERTYPE_MODE_ENABLED_HASH)
        }

        private const val SET_CONTEXT_MENU_ENABLED_HASH = 2586408642L
        private val setContextMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_context_menu_enabled", SET_CONTEXT_MENU_ENABLED_HASH)
        }

        private const val IS_CONTEXT_MENU_ENABLED_HASH = 36873697L
        private val isContextMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_context_menu_enabled", IS_CONTEXT_MENU_ENABLED_HASH)
        }

        private const val SET_EMOJI_MENU_ENABLED_HASH = 2586408642L
        private val setEmojiMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_emoji_menu_enabled", SET_EMOJI_MENU_ENABLED_HASH)
        }

        private const val IS_EMOJI_MENU_ENABLED_HASH = 36873697L
        private val isEmojiMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_emoji_menu_enabled", IS_EMOJI_MENU_ENABLED_HASH)
        }

        private const val SET_BACKSPACE_DELETES_COMPOSITE_CHARACTER_ENABLED_HASH = 2586408642L
        private val setBackspaceDeletesCompositeCharacterEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_backspace_deletes_composite_character_enabled", SET_BACKSPACE_DELETES_COMPOSITE_CHARACTER_ENABLED_HASH)
        }

        private const val IS_BACKSPACE_DELETES_COMPOSITE_CHARACTER_ENABLED_HASH = 36873697L
        private val isBackspaceDeletesCompositeCharacterEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_backspace_deletes_composite_character_enabled", IS_BACKSPACE_DELETES_COMPOSITE_CHARACTER_ENABLED_HASH)
        }

        private const val SET_SHORTCUT_KEYS_ENABLED_HASH = 2586408642L
        private val setShortcutKeysEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_shortcut_keys_enabled", SET_SHORTCUT_KEYS_ENABLED_HASH)
        }

        private const val IS_SHORTCUT_KEYS_ENABLED_HASH = 36873697L
        private val isShortcutKeysEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_shortcut_keys_enabled", IS_SHORTCUT_KEYS_ENABLED_HASH)
        }

        private const val SET_VIRTUAL_KEYBOARD_ENABLED_HASH = 2586408642L
        private val setVirtualKeyboardEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_virtual_keyboard_enabled", SET_VIRTUAL_KEYBOARD_ENABLED_HASH)
        }

        private const val IS_VIRTUAL_KEYBOARD_ENABLED_HASH = 36873697L
        private val isVirtualKeyboardEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_virtual_keyboard_enabled", IS_VIRTUAL_KEYBOARD_ENABLED_HASH)
        }

        private const val SET_VIRTUAL_KEYBOARD_SHOW_ON_FOCUS_HASH = 2586408642L
        private val setVirtualKeyboardShowOnFocusBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_virtual_keyboard_show_on_focus", SET_VIRTUAL_KEYBOARD_SHOW_ON_FOCUS_HASH)
        }

        private const val GET_VIRTUAL_KEYBOARD_SHOW_ON_FOCUS_HASH = 36873697L
        private val getVirtualKeyboardShowOnFocusBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_virtual_keyboard_show_on_focus", GET_VIRTUAL_KEYBOARD_SHOW_ON_FOCUS_HASH)
        }

        private const val SET_MIDDLE_MOUSE_PASTE_ENABLED_HASH = 2586408642L
        private val setMiddleMousePasteEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_middle_mouse_paste_enabled", SET_MIDDLE_MOUSE_PASTE_ENABLED_HASH)
        }

        private const val IS_MIDDLE_MOUSE_PASTE_ENABLED_HASH = 36873697L
        private val isMiddleMousePasteEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_middle_mouse_paste_enabled", IS_MIDDLE_MOUSE_PASTE_ENABLED_HASH)
        }

        private const val SET_EMPTY_SELECTION_CLIPBOARD_ENABLED_HASH = 2586408642L
        private val setEmptySelectionClipboardEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_empty_selection_clipboard_enabled", SET_EMPTY_SELECTION_CLIPBOARD_ENABLED_HASH)
        }

        private const val IS_EMPTY_SELECTION_CLIPBOARD_ENABLED_HASH = 36873697L
        private val isEmptySelectionClipboardEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_empty_selection_clipboard_enabled", IS_EMPTY_SELECTION_CLIPBOARD_ENABLED_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "clear", CLEAR_HASH)
        }

        private const val SET_TEXT_HASH = 83702148L
        private val setTextBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_text", SET_TEXT_HASH)
        }

        private const val GET_TEXT_HASH = 201670096L
        private val getTextBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_text", GET_TEXT_HASH)
        }

        private const val GET_LINE_COUNT_HASH = 3905245786L
        private val getLineCountBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_count", GET_LINE_COUNT_HASH)
        }

        private const val SET_PLACEHOLDER_HASH = 83702148L
        private val setPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_placeholder", SET_PLACEHOLDER_HASH)
        }

        private const val GET_PLACEHOLDER_HASH = 201670096L
        private val getPlaceholderBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_placeholder", GET_PLACEHOLDER_HASH)
        }

        private const val SET_LINE_HASH = 501894301L
        private val setLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_line", SET_LINE_HASH)
        }

        private const val GET_LINE_HASH = 844755477L
        private val getLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line", GET_LINE_HASH)
        }

        private const val GET_LINE_WITH_IME_HASH = 844755477L
        private val getLineWithImeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_with_ime", GET_LINE_WITH_IME_HASH)
        }

        private const val GET_LINE_WIDTH_HASH = 688195400L
        private val getLineWidthBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_width", GET_LINE_WIDTH_HASH)
        }

        private const val GET_LINE_HEIGHT_HASH = 3905245786L
        private val getLineHeightBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_height", GET_LINE_HEIGHT_HASH)
        }

        private const val GET_INDENT_LEVEL_HASH = 923996154L
        private val getIndentLevelBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_indent_level", GET_INDENT_LEVEL_HASH)
        }

        private const val GET_FIRST_NON_WHITESPACE_COLUMN_HASH = 923996154L
        private val getFirstNonWhitespaceColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_first_non_whitespace_column", GET_FIRST_NON_WHITESPACE_COLUMN_HASH)
        }

        private const val SWAP_LINES_HASH = 3937882851L
        private val swapLinesBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "swap_lines", SWAP_LINES_HASH)
        }

        private const val INSERT_LINE_AT_HASH = 501894301L
        private val insertLineAtBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "insert_line_at", INSERT_LINE_AT_HASH)
        }

        private const val REMOVE_LINE_AT_HASH = 972357352L
        private val removeLineAtBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "remove_line_at", REMOVE_LINE_AT_HASH)
        }

        private const val INSERT_TEXT_AT_CARET_HASH = 2697778442L
        private val insertTextAtCaretBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "insert_text_at_caret", INSERT_TEXT_AT_CARET_HASH)
        }

        private const val INSERT_TEXT_HASH = 1881564334L
        private val insertTextBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "insert_text", INSERT_TEXT_HASH)
        }

        private const val REMOVE_TEXT_HASH = 4275841770L
        private val removeTextBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "remove_text", REMOVE_TEXT_HASH)
        }

        private const val GET_LAST_UNHIDDEN_LINE_HASH = 3905245786L
        private val getLastUnhiddenLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_last_unhidden_line", GET_LAST_UNHIDDEN_LINE_HASH)
        }

        private const val GET_NEXT_VISIBLE_LINE_OFFSET_FROM_HASH = 3175239445L
        private val getNextVisibleLineOffsetFromBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_next_visible_line_offset_from", GET_NEXT_VISIBLE_LINE_OFFSET_FROM_HASH)
        }

        private const val GET_NEXT_VISIBLE_LINE_INDEX_OFFSET_FROM_HASH = 3386475622L
        private val getNextVisibleLineIndexOffsetFromBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_next_visible_line_index_offset_from", GET_NEXT_VISIBLE_LINE_INDEX_OFFSET_FROM_HASH)
        }

        private const val BACKSPACE_HASH = 1025054187L
        private val backspaceBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "backspace", BACKSPACE_HASH)
        }

        private const val CUT_HASH = 1025054187L
        private val cutBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "cut", CUT_HASH)
        }

        private const val COPY_HASH = 1025054187L
        private val copyBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "copy", COPY_HASH)
        }

        private const val PASTE_HASH = 1025054187L
        private val pasteBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "paste", PASTE_HASH)
        }

        private const val PASTE_PRIMARY_CLIPBOARD_HASH = 1025054187L
        private val pastePrimaryClipboardBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "paste_primary_clipboard", PASTE_PRIMARY_CLIPBOARD_HASH)
        }

        private const val START_ACTION_HASH = 2834827583L
        private val startActionBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "start_action", START_ACTION_HASH)
        }

        private const val END_ACTION_HASH = 3218959716L
        private val endActionBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "end_action", END_ACTION_HASH)
        }

        private const val BEGIN_COMPLEX_OPERATION_HASH = 3218959716L
        private val beginComplexOperationBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "begin_complex_operation", BEGIN_COMPLEX_OPERATION_HASH)
        }

        private const val END_COMPLEX_OPERATION_HASH = 3218959716L
        private val endComplexOperationBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "end_complex_operation", END_COMPLEX_OPERATION_HASH)
        }

        private const val HAS_UNDO_HASH = 36873697L
        private val hasUndoBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "has_undo", HAS_UNDO_HASH)
        }

        private const val HAS_REDO_HASH = 36873697L
        private val hasRedoBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "has_redo", HAS_REDO_HASH)
        }

        private const val UNDO_HASH = 3218959716L
        private val undoBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "undo", UNDO_HASH)
        }

        private const val REDO_HASH = 3218959716L
        private val redoBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "redo", REDO_HASH)
        }

        private const val CLEAR_UNDO_HISTORY_HASH = 3218959716L
        private val clearUndoHistoryBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "clear_undo_history", CLEAR_UNDO_HISTORY_HASH)
        }

        private const val TAG_SAVED_VERSION_HASH = 3218959716L
        private val tagSavedVersionBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "tag_saved_version", TAG_SAVED_VERSION_HASH)
        }

        private const val GET_VERSION_HASH = 3905245786L
        private val getVersionBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_version", GET_VERSION_HASH)
        }

        private const val GET_SAVED_VERSION_HASH = 3905245786L
        private val getSavedVersionBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_saved_version", GET_SAVED_VERSION_HASH)
        }

        private const val SET_SEARCH_TEXT_HASH = 83702148L
        private val setSearchTextBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_search_text", SET_SEARCH_TEXT_HASH)
        }

        private const val SET_SEARCH_FLAGS_HASH = 1286410249L
        private val setSearchFlagsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_search_flags", SET_SEARCH_FLAGS_HASH)
        }

        private const val SEARCH_HASH = 1203739136L
        private val searchBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "search", SEARCH_HASH)
        }

        private const val SET_TOOLTIP_REQUEST_FUNC_HASH = 1611583062L
        private val setTooltipRequestFuncBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_tooltip_request_func", SET_TOOLTIP_REQUEST_FUNC_HASH)
        }

        private const val GET_LOCAL_MOUSE_POS_HASH = 3341600327L
        private val getLocalMousePosBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_local_mouse_pos", GET_LOCAL_MOUSE_POS_HASH)
        }

        private const val GET_WORD_AT_POS_HASH = 3674420000L
        private val getWordAtPosBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_word_at_pos", GET_WORD_AT_POS_HASH)
        }

        private const val GET_LINE_COLUMN_AT_POS_HASH = 3472935744L
        private val getLineColumnAtPosBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_column_at_pos", GET_LINE_COLUMN_AT_POS_HASH)
        }

        private const val GET_POS_AT_LINE_COLUMN_HASH = 410388347L
        private val getPosAtLineColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_pos_at_line_column", GET_POS_AT_LINE_COLUMN_HASH)
        }

        private const val GET_RECT_AT_LINE_COLUMN_HASH = 3256618057L
        private val getRectAtLineColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_rect_at_line_column", GET_RECT_AT_LINE_COLUMN_HASH)
        }

        private const val GET_MINIMAP_LINE_AT_POS_HASH = 2485466453L
        private val getMinimapLineAtPosBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_minimap_line_at_pos", GET_MINIMAP_LINE_AT_POS_HASH)
        }

        private const val IS_DRAGGING_CURSOR_HASH = 36873697L
        private val isDraggingCursorBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_dragging_cursor", IS_DRAGGING_CURSOR_HASH)
        }

        private const val IS_MOUSE_OVER_SELECTION_HASH = 1840282309L
        private val isMouseOverSelectionBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_mouse_over_selection", IS_MOUSE_OVER_SELECTION_HASH)
        }

        private const val SET_CARET_TYPE_HASH = 1211596914L
        private val setCaretTypeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_caret_type", SET_CARET_TYPE_HASH)
        }

        private const val GET_CARET_TYPE_HASH = 2830252959L
        private val getCaretTypeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_caret_type", GET_CARET_TYPE_HASH)
        }

        private const val SET_CARET_BLINK_ENABLED_HASH = 2586408642L
        private val setCaretBlinkEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_caret_blink_enabled", SET_CARET_BLINK_ENABLED_HASH)
        }

        private const val IS_CARET_BLINK_ENABLED_HASH = 36873697L
        private val isCaretBlinkEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_caret_blink_enabled", IS_CARET_BLINK_ENABLED_HASH)
        }

        private const val SET_CARET_BLINK_INTERVAL_HASH = 373806689L
        private val setCaretBlinkIntervalBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_caret_blink_interval", SET_CARET_BLINK_INTERVAL_HASH)
        }

        private const val GET_CARET_BLINK_INTERVAL_HASH = 1740695150L
        private val getCaretBlinkIntervalBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_caret_blink_interval", GET_CARET_BLINK_INTERVAL_HASH)
        }

        private const val SET_DRAW_CARET_WHEN_EDITABLE_DISABLED_HASH = 2586408642L
        private val setDrawCaretWhenEditableDisabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_draw_caret_when_editable_disabled", SET_DRAW_CARET_WHEN_EDITABLE_DISABLED_HASH)
        }

        private const val IS_DRAWING_CARET_WHEN_EDITABLE_DISABLED_HASH = 36873697L
        private val isDrawingCaretWhenEditableDisabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_drawing_caret_when_editable_disabled", IS_DRAWING_CARET_WHEN_EDITABLE_DISABLED_HASH)
        }

        private const val SET_MOVE_CARET_ON_RIGHT_CLICK_ENABLED_HASH = 2586408642L
        private val setMoveCaretOnRightClickEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_move_caret_on_right_click_enabled", SET_MOVE_CARET_ON_RIGHT_CLICK_ENABLED_HASH)
        }

        private const val IS_MOVE_CARET_ON_RIGHT_CLICK_ENABLED_HASH = 36873697L
        private val isMoveCaretOnRightClickEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_move_caret_on_right_click_enabled", IS_MOVE_CARET_ON_RIGHT_CLICK_ENABLED_HASH)
        }

        private const val SET_CARET_MID_GRAPHEME_ENABLED_HASH = 2586408642L
        private val setCaretMidGraphemeEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_caret_mid_grapheme_enabled", SET_CARET_MID_GRAPHEME_ENABLED_HASH)
        }

        private const val IS_CARET_MID_GRAPHEME_ENABLED_HASH = 36873697L
        private val isCaretMidGraphemeEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_caret_mid_grapheme_enabled", IS_CARET_MID_GRAPHEME_ENABLED_HASH)
        }

        private const val SET_MULTIPLE_CARETS_ENABLED_HASH = 2586408642L
        private val setMultipleCaretsEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_multiple_carets_enabled", SET_MULTIPLE_CARETS_ENABLED_HASH)
        }

        private const val IS_MULTIPLE_CARETS_ENABLED_HASH = 36873697L
        private val isMultipleCaretsEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_multiple_carets_enabled", IS_MULTIPLE_CARETS_ENABLED_HASH)
        }

        private const val ADD_CARET_HASH = 50157827L
        private val addCaretBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "add_caret", ADD_CARET_HASH)
        }

        private const val REMOVE_CARET_HASH = 1286410249L
        private val removeCaretBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "remove_caret", REMOVE_CARET_HASH)
        }

        private const val REMOVE_SECONDARY_CARETS_HASH = 3218959716L
        private val removeSecondaryCaretsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "remove_secondary_carets", REMOVE_SECONDARY_CARETS_HASH)
        }

        private const val GET_CARET_COUNT_HASH = 3905245786L
        private val getCaretCountBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_caret_count", GET_CARET_COUNT_HASH)
        }

        private const val ADD_CARET_AT_CARETS_HASH = 2586408642L
        private val addCaretAtCaretsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "add_caret_at_carets", ADD_CARET_AT_CARETS_HASH)
        }

        private const val GET_SORTED_CARETS_HASH = 2131714034L
        private val getSortedCaretsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_sorted_carets", GET_SORTED_CARETS_HASH)
        }

        private const val COLLAPSE_CARETS_HASH = 228654177L
        private val collapseCaretsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "collapse_carets", COLLAPSE_CARETS_HASH)
        }

        private const val MERGE_OVERLAPPING_CARETS_HASH = 3218959716L
        private val mergeOverlappingCaretsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "merge_overlapping_carets", MERGE_OVERLAPPING_CARETS_HASH)
        }

        private const val BEGIN_MULTICARET_EDIT_HASH = 3218959716L
        private val beginMulticaretEditBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "begin_multicaret_edit", BEGIN_MULTICARET_EDIT_HASH)
        }

        private const val END_MULTICARET_EDIT_HASH = 3218959716L
        private val endMulticaretEditBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "end_multicaret_edit", END_MULTICARET_EDIT_HASH)
        }

        private const val IS_IN_MULITCARET_EDIT_HASH = 36873697L
        private val isInMulitcaretEditBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_in_mulitcaret_edit", IS_IN_MULITCARET_EDIT_HASH)
        }

        private const val MULTICARET_EDIT_IGNORE_CARET_HASH = 1116898809L
        private val multicaretEditIgnoreCaretBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "multicaret_edit_ignore_caret", MULTICARET_EDIT_IGNORE_CARET_HASH)
        }

        private const val IS_CARET_VISIBLE_HASH = 1051549951L
        private val isCaretVisibleBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_caret_visible", IS_CARET_VISIBLE_HASH)
        }

        private const val GET_CARET_DRAW_POS_HASH = 478253731L
        private val getCaretDrawPosBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_caret_draw_pos", GET_CARET_DRAW_POS_HASH)
        }

        private const val SET_CARET_LINE_HASH = 1302582944L
        private val setCaretLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_caret_line", SET_CARET_LINE_HASH)
        }

        private const val GET_CARET_LINE_HASH = 1591665591L
        private val getCaretLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_caret_line", GET_CARET_LINE_HASH)
        }

        private const val SET_CARET_COLUMN_HASH = 3796796178L
        private val setCaretColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_caret_column", SET_CARET_COLUMN_HASH)
        }

        private const val GET_CARET_COLUMN_HASH = 1591665591L
        private val getCaretColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_caret_column", GET_CARET_COLUMN_HASH)
        }

        private const val GET_NEXT_COMPOSITE_CHARACTER_COLUMN_HASH = 3175239445L
        private val getNextCompositeCharacterColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_next_composite_character_column", GET_NEXT_COMPOSITE_CHARACTER_COLUMN_HASH)
        }

        private const val GET_PREVIOUS_COMPOSITE_CHARACTER_COLUMN_HASH = 3175239445L
        private val getPreviousCompositeCharacterColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_previous_composite_character_column", GET_PREVIOUS_COMPOSITE_CHARACTER_COLUMN_HASH)
        }

        private const val GET_CARET_WRAP_INDEX_HASH = 1591665591L
        private val getCaretWrapIndexBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_caret_wrap_index", GET_CARET_WRAP_INDEX_HASH)
        }

        private const val GET_WORD_UNDER_CARET_HASH = 3929349208L
        private val getWordUnderCaretBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_word_under_caret", GET_WORD_UNDER_CARET_HASH)
        }

        private const val SET_USE_DEFAULT_WORD_SEPARATORS_HASH = 2586408642L
        private val setUseDefaultWordSeparatorsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_use_default_word_separators", SET_USE_DEFAULT_WORD_SEPARATORS_HASH)
        }

        private const val IS_DEFAULT_WORD_SEPARATORS_ENABLED_HASH = 36873697L
        private val isDefaultWordSeparatorsEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_default_word_separators_enabled", IS_DEFAULT_WORD_SEPARATORS_ENABLED_HASH)
        }

        private const val SET_USE_CUSTOM_WORD_SEPARATORS_HASH = 2586408642L
        private val setUseCustomWordSeparatorsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_use_custom_word_separators", SET_USE_CUSTOM_WORD_SEPARATORS_HASH)
        }

        private const val IS_CUSTOM_WORD_SEPARATORS_ENABLED_HASH = 36873697L
        private val isCustomWordSeparatorsEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_custom_word_separators_enabled", IS_CUSTOM_WORD_SEPARATORS_ENABLED_HASH)
        }

        private const val SET_CUSTOM_WORD_SEPARATORS_HASH = 83702148L
        private val setCustomWordSeparatorsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_custom_word_separators", SET_CUSTOM_WORD_SEPARATORS_HASH)
        }

        private const val GET_CUSTOM_WORD_SEPARATORS_HASH = 201670096L
        private val getCustomWordSeparatorsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_custom_word_separators", GET_CUSTOM_WORD_SEPARATORS_HASH)
        }

        private const val SET_SELECTING_ENABLED_HASH = 2586408642L
        private val setSelectingEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_selecting_enabled", SET_SELECTING_ENABLED_HASH)
        }

        private const val IS_SELECTING_ENABLED_HASH = 36873697L
        private val isSelectingEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_selecting_enabled", IS_SELECTING_ENABLED_HASH)
        }

        private const val SET_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH = 2586408642L
        private val setDeselectOnFocusLossEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_deselect_on_focus_loss_enabled", SET_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH)
        }

        private const val IS_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH = 36873697L
        private val isDeselectOnFocusLossEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_deselect_on_focus_loss_enabled", IS_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH)
        }

        private const val SET_DRAG_AND_DROP_SELECTION_ENABLED_HASH = 2586408642L
        private val setDragAndDropSelectionEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_drag_and_drop_selection_enabled", SET_DRAG_AND_DROP_SELECTION_ENABLED_HASH)
        }

        private const val IS_DRAG_AND_DROP_SELECTION_ENABLED_HASH = 36873697L
        private val isDragAndDropSelectionEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_drag_and_drop_selection_enabled", IS_DRAG_AND_DROP_SELECTION_ENABLED_HASH)
        }

        private const val SET_SELECTION_MODE_HASH = 1658801786L
        private val setSelectionModeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_selection_mode", SET_SELECTION_MODE_HASH)
        }

        private const val GET_SELECTION_MODE_HASH = 3750106938L
        private val getSelectionModeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_selection_mode", GET_SELECTION_MODE_HASH)
        }

        private const val SELECT_ALL_HASH = 3218959716L
        private val selectAllBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "select_all", SELECT_ALL_HASH)
        }

        private const val SELECT_WORD_UNDER_CARET_HASH = 1025054187L
        private val selectWordUnderCaretBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "select_word_under_caret", SELECT_WORD_UNDER_CARET_HASH)
        }

        private const val ADD_SELECTION_FOR_NEXT_OCCURRENCE_HASH = 3218959716L
        private val addSelectionForNextOccurrenceBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "add_selection_for_next_occurrence", ADD_SELECTION_FOR_NEXT_OCCURRENCE_HASH)
        }

        private const val SKIP_SELECTION_FOR_NEXT_OCCURRENCE_HASH = 3218959716L
        private val skipSelectionForNextOccurrenceBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "skip_selection_for_next_occurrence", SKIP_SELECTION_FOR_NEXT_OCCURRENCE_HASH)
        }

        private const val SELECT_HASH = 2560984452L
        private val selectBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "select", SELECT_HASH)
        }

        private const val HAS_SELECTION_HASH = 2824505868L
        private val hasSelectionBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "has_selection", HAS_SELECTION_HASH)
        }

        private const val GET_SELECTED_TEXT_HASH = 2309358862L
        private val getSelectedTextBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_selected_text", GET_SELECTED_TEXT_HASH)
        }

        private const val GET_SELECTION_AT_LINE_COLUMN_HASH = 1810224333L
        private val getSelectionAtLineColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_selection_at_line_column", GET_SELECTION_AT_LINE_COLUMN_HASH)
        }

        private const val GET_LINE_RANGES_FROM_CARETS_HASH = 2393089247L
        private val getLineRangesFromCaretsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_ranges_from_carets", GET_LINE_RANGES_FROM_CARETS_HASH)
        }

        private const val GET_SELECTION_ORIGIN_LINE_HASH = 1591665591L
        private val getSelectionOriginLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_selection_origin_line", GET_SELECTION_ORIGIN_LINE_HASH)
        }

        private const val GET_SELECTION_ORIGIN_COLUMN_HASH = 1591665591L
        private val getSelectionOriginColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_selection_origin_column", GET_SELECTION_ORIGIN_COLUMN_HASH)
        }

        private const val SET_SELECTION_ORIGIN_LINE_HASH = 195434140L
        private val setSelectionOriginLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_selection_origin_line", SET_SELECTION_ORIGIN_LINE_HASH)
        }

        private const val SET_SELECTION_ORIGIN_COLUMN_HASH = 2230941749L
        private val setSelectionOriginColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_selection_origin_column", SET_SELECTION_ORIGIN_COLUMN_HASH)
        }

        private const val GET_SELECTION_FROM_LINE_HASH = 1591665591L
        private val getSelectionFromLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_selection_from_line", GET_SELECTION_FROM_LINE_HASH)
        }

        private const val GET_SELECTION_FROM_COLUMN_HASH = 1591665591L
        private val getSelectionFromColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_selection_from_column", GET_SELECTION_FROM_COLUMN_HASH)
        }

        private const val GET_SELECTION_TO_LINE_HASH = 1591665591L
        private val getSelectionToLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_selection_to_line", GET_SELECTION_TO_LINE_HASH)
        }

        private const val GET_SELECTION_TO_COLUMN_HASH = 1591665591L
        private val getSelectionToColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_selection_to_column", GET_SELECTION_TO_COLUMN_HASH)
        }

        private const val IS_CARET_AFTER_SELECTION_ORIGIN_HASH = 1051549951L
        private val isCaretAfterSelectionOriginBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_caret_after_selection_origin", IS_CARET_AFTER_SELECTION_ORIGIN_HASH)
        }

        private const val DESELECT_HASH = 1025054187L
        private val deselectBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "deselect", DESELECT_HASH)
        }

        private const val DELETE_SELECTION_HASH = 1025054187L
        private val deleteSelectionBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "delete_selection", DELETE_SELECTION_HASH)
        }

        private const val SET_LINE_WRAPPING_MODE_HASH = 2525115309L
        private val setLineWrappingModeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_line_wrapping_mode", SET_LINE_WRAPPING_MODE_HASH)
        }

        private const val GET_LINE_WRAPPING_MODE_HASH = 3562716114L
        private val getLineWrappingModeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_wrapping_mode", GET_LINE_WRAPPING_MODE_HASH)
        }

        private const val SET_AUTOWRAP_MODE_HASH = 3289138044L
        private val setAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_autowrap_mode", SET_AUTOWRAP_MODE_HASH)
        }

        private const val GET_AUTOWRAP_MODE_HASH = 1549071663L
        private val getAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_autowrap_mode", GET_AUTOWRAP_MODE_HASH)
        }

        private const val IS_LINE_WRAPPED_HASH = 1116898809L
        private val isLineWrappedBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_line_wrapped", IS_LINE_WRAPPED_HASH)
        }

        private const val GET_LINE_WRAP_COUNT_HASH = 923996154L
        private val getLineWrapCountBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_wrap_count", GET_LINE_WRAP_COUNT_HASH)
        }

        private const val GET_LINE_WRAP_INDEX_AT_COLUMN_HASH = 3175239445L
        private val getLineWrapIndexAtColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_wrap_index_at_column", GET_LINE_WRAP_INDEX_AT_COLUMN_HASH)
        }

        private const val GET_LINE_WRAPPED_TEXT_HASH = 647634434L
        private val getLineWrappedTextBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_wrapped_text", GET_LINE_WRAPPED_TEXT_HASH)
        }

        private const val SET_SMOOTH_SCROLL_ENABLED_HASH = 2586408642L
        private val setSmoothScrollEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_smooth_scroll_enabled", SET_SMOOTH_SCROLL_ENABLED_HASH)
        }

        private const val IS_SMOOTH_SCROLL_ENABLED_HASH = 36873697L
        private val isSmoothScrollEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_smooth_scroll_enabled", IS_SMOOTH_SCROLL_ENABLED_HASH)
        }

        private const val GET_V_SCROLL_BAR_HASH = 3226026593L
        private val getVScrollBarBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_v_scroll_bar", GET_V_SCROLL_BAR_HASH)
        }

        private const val GET_H_SCROLL_BAR_HASH = 3774687988L
        private val getHScrollBarBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_h_scroll_bar", GET_H_SCROLL_BAR_HASH)
        }

        private const val SET_V_SCROLL_HASH = 373806689L
        private val setVScrollBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_v_scroll", SET_V_SCROLL_HASH)
        }

        private const val GET_V_SCROLL_HASH = 1740695150L
        private val getVScrollBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_v_scroll", GET_V_SCROLL_HASH)
        }

        private const val SET_H_SCROLL_HASH = 1286410249L
        private val setHScrollBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_h_scroll", SET_H_SCROLL_HASH)
        }

        private const val GET_H_SCROLL_HASH = 3905245786L
        private val getHScrollBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_h_scroll", GET_H_SCROLL_HASH)
        }

        private const val SET_SCROLL_PAST_END_OF_FILE_ENABLED_HASH = 2586408642L
        private val setScrollPastEndOfFileEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_scroll_past_end_of_file_enabled", SET_SCROLL_PAST_END_OF_FILE_ENABLED_HASH)
        }

        private const val IS_SCROLL_PAST_END_OF_FILE_ENABLED_HASH = 36873697L
        private val isScrollPastEndOfFileEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_scroll_past_end_of_file_enabled", IS_SCROLL_PAST_END_OF_FILE_ENABLED_HASH)
        }

        private const val SET_V_SCROLL_SPEED_HASH = 373806689L
        private val setVScrollSpeedBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_v_scroll_speed", SET_V_SCROLL_SPEED_HASH)
        }

        private const val GET_V_SCROLL_SPEED_HASH = 1740695150L
        private val getVScrollSpeedBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_v_scroll_speed", GET_V_SCROLL_SPEED_HASH)
        }

        private const val SET_FIT_CONTENT_HEIGHT_ENABLED_HASH = 2586408642L
        private val setFitContentHeightEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_fit_content_height_enabled", SET_FIT_CONTENT_HEIGHT_ENABLED_HASH)
        }

        private const val IS_FIT_CONTENT_HEIGHT_ENABLED_HASH = 36873697L
        private val isFitContentHeightEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_fit_content_height_enabled", IS_FIT_CONTENT_HEIGHT_ENABLED_HASH)
        }

        private const val SET_FIT_CONTENT_WIDTH_ENABLED_HASH = 2586408642L
        private val setFitContentWidthEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_fit_content_width_enabled", SET_FIT_CONTENT_WIDTH_ENABLED_HASH)
        }

        private const val IS_FIT_CONTENT_WIDTH_ENABLED_HASH = 36873697L
        private val isFitContentWidthEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_fit_content_width_enabled", IS_FIT_CONTENT_WIDTH_ENABLED_HASH)
        }

        private const val GET_SCROLL_POS_FOR_LINE_HASH = 3929084198L
        private val getScrollPosForLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_scroll_pos_for_line", GET_SCROLL_POS_FOR_LINE_HASH)
        }

        private const val SET_LINE_AS_FIRST_VISIBLE_HASH = 2230941749L
        private val setLineAsFirstVisibleBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_line_as_first_visible", SET_LINE_AS_FIRST_VISIBLE_HASH)
        }

        private const val GET_FIRST_VISIBLE_LINE_HASH = 3905245786L
        private val getFirstVisibleLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_first_visible_line", GET_FIRST_VISIBLE_LINE_HASH)
        }

        private const val IS_LINE_IN_VIEWPORT_HASH = 1116898809L
        private val isLineInViewportBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_line_in_viewport", IS_LINE_IN_VIEWPORT_HASH)
        }

        private const val SET_LINE_AS_CENTER_VISIBLE_HASH = 2230941749L
        private val setLineAsCenterVisibleBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_line_as_center_visible", SET_LINE_AS_CENTER_VISIBLE_HASH)
        }

        private const val SET_LINE_AS_LAST_VISIBLE_HASH = 2230941749L
        private val setLineAsLastVisibleBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_line_as_last_visible", SET_LINE_AS_LAST_VISIBLE_HASH)
        }

        private const val GET_LAST_FULL_VISIBLE_LINE_HASH = 3905245786L
        private val getLastFullVisibleLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_last_full_visible_line", GET_LAST_FULL_VISIBLE_LINE_HASH)
        }

        private const val GET_LAST_FULL_VISIBLE_LINE_WRAP_INDEX_HASH = 3905245786L
        private val getLastFullVisibleLineWrapIndexBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_last_full_visible_line_wrap_index", GET_LAST_FULL_VISIBLE_LINE_WRAP_INDEX_HASH)
        }

        private const val GET_VISIBLE_LINE_COUNT_HASH = 3905245786L
        private val getVisibleLineCountBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_visible_line_count", GET_VISIBLE_LINE_COUNT_HASH)
        }

        private const val GET_VISIBLE_LINE_COUNT_IN_RANGE_HASH = 3175239445L
        private val getVisibleLineCountInRangeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_visible_line_count_in_range", GET_VISIBLE_LINE_COUNT_IN_RANGE_HASH)
        }

        private const val GET_TOTAL_VISIBLE_LINE_COUNT_HASH = 3905245786L
        private val getTotalVisibleLineCountBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_total_visible_line_count", GET_TOTAL_VISIBLE_LINE_COUNT_HASH)
        }

        private const val ADJUST_VIEWPORT_TO_CARET_HASH = 1995695955L
        private val adjustViewportToCaretBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "adjust_viewport_to_caret", ADJUST_VIEWPORT_TO_CARET_HASH)
        }

        private const val CENTER_VIEWPORT_TO_CARET_HASH = 1995695955L
        private val centerViewportToCaretBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "center_viewport_to_caret", CENTER_VIEWPORT_TO_CARET_HASH)
        }

        private const val SET_DRAW_MINIMAP_HASH = 2586408642L
        private val setDrawMinimapBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_draw_minimap", SET_DRAW_MINIMAP_HASH)
        }

        private const val IS_DRAWING_MINIMAP_HASH = 36873697L
        private val isDrawingMinimapBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_drawing_minimap", IS_DRAWING_MINIMAP_HASH)
        }

        private const val SET_MINIMAP_WIDTH_HASH = 1286410249L
        private val setMinimapWidthBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_minimap_width", SET_MINIMAP_WIDTH_HASH)
        }

        private const val GET_MINIMAP_WIDTH_HASH = 3905245786L
        private val getMinimapWidthBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_minimap_width", GET_MINIMAP_WIDTH_HASH)
        }

        private const val GET_MINIMAP_VISIBLE_LINES_HASH = 3905245786L
        private val getMinimapVisibleLinesBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_minimap_visible_lines", GET_MINIMAP_VISIBLE_LINES_HASH)
        }

        private const val ADD_GUTTER_HASH = 1025054187L
        private val addGutterBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "add_gutter", ADD_GUTTER_HASH)
        }

        private const val REMOVE_GUTTER_HASH = 1286410249L
        private val removeGutterBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "remove_gutter", REMOVE_GUTTER_HASH)
        }

        private const val GET_GUTTER_COUNT_HASH = 3905245786L
        private val getGutterCountBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_gutter_count", GET_GUTTER_COUNT_HASH)
        }

        private const val SET_GUTTER_NAME_HASH = 501894301L
        private val setGutterNameBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_gutter_name", SET_GUTTER_NAME_HASH)
        }

        private const val GET_GUTTER_NAME_HASH = 844755477L
        private val getGutterNameBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_gutter_name", GET_GUTTER_NAME_HASH)
        }

        private const val SET_GUTTER_TYPE_HASH = 1088959071L
        private val setGutterTypeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_gutter_type", SET_GUTTER_TYPE_HASH)
        }

        private const val GET_GUTTER_TYPE_HASH = 1159699127L
        private val getGutterTypeBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_gutter_type", GET_GUTTER_TYPE_HASH)
        }

        private const val SET_GUTTER_WIDTH_HASH = 3937882851L
        private val setGutterWidthBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_gutter_width", SET_GUTTER_WIDTH_HASH)
        }

        private const val GET_GUTTER_WIDTH_HASH = 923996154L
        private val getGutterWidthBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_gutter_width", GET_GUTTER_WIDTH_HASH)
        }

        private const val SET_GUTTER_DRAW_HASH = 300928843L
        private val setGutterDrawBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_gutter_draw", SET_GUTTER_DRAW_HASH)
        }

        private const val IS_GUTTER_DRAWN_HASH = 1116898809L
        private val isGutterDrawnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_gutter_drawn", IS_GUTTER_DRAWN_HASH)
        }

        private const val SET_GUTTER_CLICKABLE_HASH = 300928843L
        private val setGutterClickableBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_gutter_clickable", SET_GUTTER_CLICKABLE_HASH)
        }

        private const val IS_GUTTER_CLICKABLE_HASH = 1116898809L
        private val isGutterClickableBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_gutter_clickable", IS_GUTTER_CLICKABLE_HASH)
        }

        private const val SET_GUTTER_OVERWRITABLE_HASH = 300928843L
        private val setGutterOverwritableBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_gutter_overwritable", SET_GUTTER_OVERWRITABLE_HASH)
        }

        private const val IS_GUTTER_OVERWRITABLE_HASH = 1116898809L
        private val isGutterOverwritableBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_gutter_overwritable", IS_GUTTER_OVERWRITABLE_HASH)
        }

        private const val MERGE_GUTTERS_HASH = 3937882851L
        private val mergeGuttersBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "merge_gutters", MERGE_GUTTERS_HASH)
        }

        private const val SET_GUTTER_CUSTOM_DRAW_HASH = 957362965L
        private val setGutterCustomDrawBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_gutter_custom_draw", SET_GUTTER_CUSTOM_DRAW_HASH)
        }

        private const val GET_TOTAL_GUTTER_WIDTH_HASH = 3905245786L
        private val getTotalGutterWidthBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_total_gutter_width", GET_TOTAL_GUTTER_WIDTH_HASH)
        }

        private const val SET_LINE_GUTTER_METADATA_HASH = 2060538656L
        private val setLineGutterMetadataBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_line_gutter_metadata", SET_LINE_GUTTER_METADATA_HASH)
        }

        private const val GET_LINE_GUTTER_METADATA_HASH = 678354945L
        private val getLineGutterMetadataBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_gutter_metadata", GET_LINE_GUTTER_METADATA_HASH)
        }

        private const val SET_LINE_GUTTER_TEXT_HASH = 2285447957L
        private val setLineGutterTextBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_line_gutter_text", SET_LINE_GUTTER_TEXT_HASH)
        }

        private const val GET_LINE_GUTTER_TEXT_HASH = 1391810591L
        private val getLineGutterTextBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_gutter_text", GET_LINE_GUTTER_TEXT_HASH)
        }

        private const val SET_LINE_GUTTER_ICON_HASH = 176101966L
        private val setLineGutterIconBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_line_gutter_icon", SET_LINE_GUTTER_ICON_HASH)
        }

        private const val GET_LINE_GUTTER_ICON_HASH = 2584904275L
        private val getLineGutterIconBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_gutter_icon", GET_LINE_GUTTER_ICON_HASH)
        }

        private const val SET_LINE_GUTTER_ITEM_COLOR_HASH = 3733378741L
        private val setLineGutterItemColorBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_line_gutter_item_color", SET_LINE_GUTTER_ITEM_COLOR_HASH)
        }

        private const val GET_LINE_GUTTER_ITEM_COLOR_HASH = 2165839948L
        private val getLineGutterItemColorBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_gutter_item_color", GET_LINE_GUTTER_ITEM_COLOR_HASH)
        }

        private const val SET_LINE_GUTTER_CLICKABLE_HASH = 1383440665L
        private val setLineGutterClickableBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_line_gutter_clickable", SET_LINE_GUTTER_CLICKABLE_HASH)
        }

        private const val IS_LINE_GUTTER_CLICKABLE_HASH = 2522259332L
        private val isLineGutterClickableBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_line_gutter_clickable", IS_LINE_GUTTER_CLICKABLE_HASH)
        }

        private const val SET_LINE_BACKGROUND_COLOR_HASH = 2878471219L
        private val setLineBackgroundColorBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_line_background_color", SET_LINE_BACKGROUND_COLOR_HASH)
        }

        private const val GET_LINE_BACKGROUND_COLOR_HASH = 3457211756L
        private val getLineBackgroundColorBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_line_background_color", GET_LINE_BACKGROUND_COLOR_HASH)
        }

        private const val SET_SYNTAX_HIGHLIGHTER_HASH = 2765644541L
        private val setSyntaxHighlighterBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_syntax_highlighter", SET_SYNTAX_HIGHLIGHTER_HASH)
        }

        private const val GET_SYNTAX_HIGHLIGHTER_HASH = 2721131626L
        private val getSyntaxHighlighterBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_syntax_highlighter", GET_SYNTAX_HIGHLIGHTER_HASH)
        }

        private const val SET_HIGHLIGHT_CURRENT_LINE_HASH = 2586408642L
        private val setHighlightCurrentLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_highlight_current_line", SET_HIGHLIGHT_CURRENT_LINE_HASH)
        }

        private const val IS_HIGHLIGHT_CURRENT_LINE_ENABLED_HASH = 36873697L
        private val isHighlightCurrentLineEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_highlight_current_line_enabled", IS_HIGHLIGHT_CURRENT_LINE_ENABLED_HASH)
        }

        private const val SET_HIGHLIGHT_ALL_OCCURRENCES_HASH = 2586408642L
        private val setHighlightAllOccurrencesBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_highlight_all_occurrences", SET_HIGHLIGHT_ALL_OCCURRENCES_HASH)
        }

        private const val IS_HIGHLIGHT_ALL_OCCURRENCES_ENABLED_HASH = 36873697L
        private val isHighlightAllOccurrencesEnabledBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_highlight_all_occurrences_enabled", IS_HIGHLIGHT_ALL_OCCURRENCES_ENABLED_HASH)
        }

        private const val GET_DRAW_CONTROL_CHARS_HASH = 36873697L
        private val getDrawControlCharsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_draw_control_chars", GET_DRAW_CONTROL_CHARS_HASH)
        }

        private const val SET_DRAW_CONTROL_CHARS_HASH = 2586408642L
        private val setDrawControlCharsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_draw_control_chars", SET_DRAW_CONTROL_CHARS_HASH)
        }

        private const val SET_DRAW_TABS_HASH = 2586408642L
        private val setDrawTabsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_draw_tabs", SET_DRAW_TABS_HASH)
        }

        private const val IS_DRAWING_TABS_HASH = 36873697L
        private val isDrawingTabsBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_drawing_tabs", IS_DRAWING_TABS_HASH)
        }

        private const val SET_DRAW_SPACES_HASH = 2586408642L
        private val setDrawSpacesBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "set_draw_spaces", SET_DRAW_SPACES_HASH)
        }

        private const val IS_DRAWING_SPACES_HASH = 36873697L
        private val isDrawingSpacesBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_drawing_spaces", IS_DRAWING_SPACES_HASH)
        }

        private const val GET_MENU_HASH = 229722558L
        private val getMenuBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_menu", GET_MENU_HASH)
        }

        private const val IS_MENU_VISIBLE_HASH = 36873697L
        private val isMenuVisibleBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "is_menu_visible", IS_MENU_VISIBLE_HASH)
        }

        private const val MENU_OPTION_HASH = 1286410249L
        private val menuOptionBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "menu_option", MENU_OPTION_HASH)
        }

        private const val ADJUST_CARETS_AFTER_EDIT_HASH = 1770277138L
        private val adjustCaretsAfterEditBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "adjust_carets_after_edit", ADJUST_CARETS_AFTER_EDIT_HASH)
        }

        private const val GET_CARET_INDEX_EDIT_ORDER_HASH = 969006518L
        private val getCaretIndexEditOrderBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_caret_index_edit_order", GET_CARET_INDEX_EDIT_ORDER_HASH)
        }

        private const val GET_SELECTION_LINE_HASH = 1591665591L
        private val getSelectionLineBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_selection_line", GET_SELECTION_LINE_HASH)
        }

        private const val GET_SELECTION_COLUMN_HASH = 1591665591L
        private val getSelectionColumnBind by lazy {
            ObjectCalls.getMethodBind("TextEdit", "get_selection_column", GET_SELECTION_COLUMN_HASH)
        }
    }
}
