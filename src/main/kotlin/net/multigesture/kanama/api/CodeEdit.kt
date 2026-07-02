package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A multiline text editor designed for editing code.
 *
 * Generated from Godot docs: CodeEdit
 */
class CodeEdit(handle: MemorySegment) : TextEdit(handle) {
    var symbolLookupOnClick: Boolean
        @JvmName("symbolLookupOnClickProperty")
        get() = isSymbolLookupOnClickEnabled()
        @JvmName("setSymbolLookupOnClickProperty")
        set(value) = setSymbolLookupOnClickEnabled(value)

    var symbolTooltipOnHover: Boolean
        @JvmName("symbolTooltipOnHoverProperty")
        get() = isSymbolTooltipOnHoverEnabled()
        @JvmName("setSymbolTooltipOnHoverProperty")
        set(value) = setSymbolTooltipOnHoverEnabled(value)

    var lineFolding: Boolean
        @JvmName("lineFoldingProperty")
        get() = isLineFoldingEnabled()
        @JvmName("setLineFoldingProperty")
        set(value) = setLineFoldingEnabled(value)

    var lineLengthGuidelines: List<Long>
        @JvmName("lineLengthGuidelinesProperty")
        get() = getLineLengthGuidelines()
        @JvmName("setLineLengthGuidelinesProperty")
        set(value) = setLineLengthGuidelines(value)

    var guttersDrawBreakpointsGutter: Boolean
        @JvmName("guttersDrawBreakpointsGutterProperty")
        get() = isDrawingBreakpointsGutter()
        @JvmName("setGuttersDrawBreakpointsGutterProperty")
        set(value) = setDrawBreakpointsGutter(value)

    var guttersDrawBookmarks: Boolean
        @JvmName("guttersDrawBookmarksProperty")
        get() = isDrawingBookmarksGutter()
        @JvmName("setGuttersDrawBookmarksProperty")
        set(value) = setDrawBookmarksGutter(value)

    var guttersDrawExecutingLines: Boolean
        @JvmName("guttersDrawExecutingLinesProperty")
        get() = isDrawingExecutingLinesGutter()
        @JvmName("setGuttersDrawExecutingLinesProperty")
        set(value) = setDrawExecutingLinesGutter(value)

    var guttersDrawLineNumbers: Boolean
        @JvmName("guttersDrawLineNumbersProperty")
        get() = isDrawLineNumbersEnabled()
        @JvmName("setGuttersDrawLineNumbersProperty")
        set(value) = setDrawLineNumbers(value)

    var guttersZeroPadLineNumbers: Boolean
        @JvmName("guttersZeroPadLineNumbersProperty")
        get() = isLineNumbersZeroPadded()
        @JvmName("setGuttersZeroPadLineNumbersProperty")
        set(value) = setLineNumbersZeroPadded(value)

    var guttersLineNumbersMinDigits: Int
        @JvmName("guttersLineNumbersMinDigitsProperty")
        get() = getLineNumbersMinDigits()
        @JvmName("setGuttersLineNumbersMinDigitsProperty")
        set(value) = setLineNumbersMinDigits(value)

    var guttersDrawFoldGutter: Boolean
        @JvmName("guttersDrawFoldGutterProperty")
        get() = isDrawingFoldGutter()
        @JvmName("setGuttersDrawFoldGutterProperty")
        set(value) = setDrawFoldGutter(value)

    var delimiterStrings: List<String>
        @JvmName("delimiterStringsProperty")
        get() = getStringDelimiters()
        @JvmName("setDelimiterStringsProperty")
        set(value) = setStringDelimiters(value)

    var delimiterComments: List<String>
        @JvmName("delimiterCommentsProperty")
        get() = getCommentDelimiters()
        @JvmName("setDelimiterCommentsProperty")
        set(value) = setCommentDelimiters(value)

    var codeCompletionEnabled: Boolean
        @JvmName("codeCompletionEnabledProperty")
        get() = isCodeCompletionEnabled()
        @JvmName("setCodeCompletionEnabledProperty")
        set(value) = setCodeCompletionEnabled(value)

    var codeCompletionPrefixes: List<String>
        @JvmName("codeCompletionPrefixesProperty")
        get() = getCodeCompletionPrefixes()
        @JvmName("setCodeCompletionPrefixesProperty")
        set(value) = setCodeCompletionPrefixes(value)

    var indentSize: Int
        @JvmName("indentSizeProperty")
        get() = getIndentSize()
        @JvmName("setIndentSizeProperty")
        set(value) = setIndentSize(value)

    var indentUseSpaces: Boolean
        @JvmName("indentUseSpacesProperty")
        get() = isIndentUsingSpaces()
        @JvmName("setIndentUseSpacesProperty")
        set(value) = setIndentUsingSpaces(value)

    var indentAutomatic: Boolean
        @JvmName("indentAutomaticProperty")
        get() = isAutoIndentEnabled()
        @JvmName("setIndentAutomaticProperty")
        set(value) = setAutoIndentEnabled(value)

    var indentAutomaticPrefixes: List<String>
        @JvmName("indentAutomaticPrefixesProperty")
        get() = getAutoIndentPrefixes()
        @JvmName("setIndentAutomaticPrefixesProperty")
        set(value) = setAutoIndentPrefixes(value)

    var autoBraceCompletionEnabled: Boolean
        @JvmName("autoBraceCompletionEnabledProperty")
        get() = isAutoBraceCompletionEnabled()
        @JvmName("setAutoBraceCompletionEnabledProperty")
        set(value) = setAutoBraceCompletionEnabled(value)

    var autoBraceCompletionHighlightMatching: Boolean
        @JvmName("autoBraceCompletionHighlightMatchingProperty")
        get() = isHighlightMatchingBracesEnabled()
        @JvmName("setAutoBraceCompletionHighlightMatchingProperty")
        set(value) = setHighlightMatchingBracesEnabled(value)

    var autoBraceCompletionPairs: Map<String, Any?>
        @JvmName("autoBraceCompletionPairsProperty")
        get() = getAutoBraceCompletionPairs()
        @JvmName("setAutoBraceCompletionPairsProperty")
        set(value) = setAutoBraceCompletionPairs(value)

    fun setIndentSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setIndentSizeBind, handle, size)
    }

    fun getIndentSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getIndentSizeBind, handle)
    }

    fun setIndentUsingSpaces(useSpaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIndentUsingSpacesBind, handle, useSpaces)
    }

    fun isIndentUsingSpaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIndentUsingSpacesBind, handle)
    }

    fun setAutoIndentEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoIndentEnabledBind, handle, enable)
    }

    fun isAutoIndentEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutoIndentEnabledBind, handle)
    }

    fun setAutoIndentPrefixes(prefixes: List<String>) {
        ObjectCalls.ptrcallWithTypedStringListArg(setAutoIndentPrefixesBind, handle, prefixes)
    }

    fun getAutoIndentPrefixes(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(getAutoIndentPrefixesBind, handle)
    }

    fun doIndent() {
        ObjectCalls.ptrcallNoArgs(doIndentBind, handle)
    }

    fun indentLines() {
        ObjectCalls.ptrcallNoArgs(indentLinesBind, handle)
    }

    fun unindentLines() {
        ObjectCalls.ptrcallNoArgs(unindentLinesBind, handle)
    }

    fun convertIndent(fromLine: Int = -1, toLine: Int = -1) {
        ObjectCalls.ptrcallWithTwoIntArgs(convertIndentBind, handle, fromLine, toLine)
    }

    fun setAutoBraceCompletionEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoBraceCompletionEnabledBind, handle, enable)
    }

    fun isAutoBraceCompletionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutoBraceCompletionEnabledBind, handle)
    }

    fun setHighlightMatchingBracesEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHighlightMatchingBracesEnabledBind, handle, enable)
    }

    fun isHighlightMatchingBracesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHighlightMatchingBracesEnabledBind, handle)
    }

    fun addAutoBraceCompletionPair(startKey: String, endKey: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(addAutoBraceCompletionPairBind, handle, startKey, endKey)
    }

    fun setAutoBraceCompletionPairs(pairs: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setAutoBraceCompletionPairsBind, handle, pairs)
    }

    fun getAutoBraceCompletionPairs(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getAutoBraceCompletionPairsBind, handle)
    }

    fun hasAutoBraceCompletionOpenKey(openKey: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasAutoBraceCompletionOpenKeyBind, handle, openKey)
    }

    fun hasAutoBraceCompletionCloseKey(closeKey: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasAutoBraceCompletionCloseKeyBind, handle, closeKey)
    }

    fun getAutoBraceCompletionCloseKey(openKey: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getAutoBraceCompletionCloseKeyBind, handle, openKey)
    }

    fun setDrawBreakpointsGutter(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawBreakpointsGutterBind, handle, enable)
    }

    fun isDrawingBreakpointsGutter(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawingBreakpointsGutterBind, handle)
    }

    fun setDrawBookmarksGutter(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawBookmarksGutterBind, handle, enable)
    }

    fun isDrawingBookmarksGutter(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawingBookmarksGutterBind, handle)
    }

    fun setDrawExecutingLinesGutter(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawExecutingLinesGutterBind, handle, enable)
    }

    fun isDrawingExecutingLinesGutter(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawingExecutingLinesGutterBind, handle)
    }

    fun setLineAsBreakpoint(line: Int, breakpointed: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLineAsBreakpointBind, handle, line, breakpointed)
    }

    fun isLineBreakpointed(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineBreakpointedBind, handle, line)
    }

    fun clearBreakpointedLines() {
        ObjectCalls.ptrcallNoArgs(clearBreakpointedLinesBind, handle)
    }

    fun getBreakpointedLines(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getBreakpointedLinesBind, handle)
    }

    fun setLineAsBookmarked(line: Int, bookmarked: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLineAsBookmarkedBind, handle, line, bookmarked)
    }

    fun isLineBookmarked(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineBookmarkedBind, handle, line)
    }

    fun clearBookmarkedLines() {
        ObjectCalls.ptrcallNoArgs(clearBookmarkedLinesBind, handle)
    }

    fun getBookmarkedLines(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getBookmarkedLinesBind, handle)
    }

    fun setLineAsExecuting(line: Int, executing: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLineAsExecutingBind, handle, line, executing)
    }

    fun isLineExecuting(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineExecutingBind, handle, line)
    }

    fun clearExecutingLines() {
        ObjectCalls.ptrcallNoArgs(clearExecutingLinesBind, handle)
    }

    fun getExecutingLines(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getExecutingLinesBind, handle)
    }

    fun setDrawLineNumbers(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawLineNumbersBind, handle, enable)
    }

    fun isDrawLineNumbersEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawLineNumbersEnabledBind, handle)
    }

    fun setLineNumbersZeroPadded(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLineNumbersZeroPaddedBind, handle, enable)
    }

    fun isLineNumbersZeroPadded(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLineNumbersZeroPaddedBind, handle)
    }

    fun setLineNumbersMinDigits(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setLineNumbersMinDigitsBind, handle, count)
    }

    fun getLineNumbersMinDigits(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLineNumbersMinDigitsBind, handle)
    }

    fun setDrawFoldGutter(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawFoldGutterBind, handle, enable)
    }

    fun isDrawingFoldGutter(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawingFoldGutterBind, handle)
    }

    fun setLineFoldingEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLineFoldingEnabledBind, handle, enabled)
    }

    fun isLineFoldingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLineFoldingEnabledBind, handle)
    }

    fun canFoldLine(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(canFoldLineBind, handle, line)
    }

    fun foldLine(line: Int) {
        ObjectCalls.ptrcallWithIntArg(foldLineBind, handle, line)
    }

    fun unfoldLine(line: Int) {
        ObjectCalls.ptrcallWithIntArg(unfoldLineBind, handle, line)
    }

    fun foldAllLines() {
        ObjectCalls.ptrcallNoArgs(foldAllLinesBind, handle)
    }

    fun unfoldAllLines() {
        ObjectCalls.ptrcallNoArgs(unfoldAllLinesBind, handle)
    }

    fun toggleFoldableLine(line: Int) {
        ObjectCalls.ptrcallWithIntArg(toggleFoldableLineBind, handle, line)
    }

    fun toggleFoldableLinesAtCarets() {
        ObjectCalls.ptrcallNoArgs(toggleFoldableLinesAtCaretsBind, handle)
    }

    fun isLineFolded(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineFoldedBind, handle, line)
    }

    fun getFoldedLines(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetLongList(getFoldedLinesBind, handle)
    }

    fun createCodeRegion() {
        ObjectCalls.ptrcallNoArgs(createCodeRegionBind, handle)
    }

    fun getCodeRegionStartTag(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCodeRegionStartTagBind, handle)
    }

    fun getCodeRegionEndTag(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCodeRegionEndTagBind, handle)
    }

    fun setCodeRegionTags(start: String = "region", end: String = "endregion") {
        ObjectCalls.ptrcallWithTwoStringArgs(setCodeRegionTagsBind, handle, start, end)
    }

    fun isLineCodeRegionStart(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineCodeRegionStartBind, handle, line)
    }

    fun isLineCodeRegionEnd(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineCodeRegionEndBind, handle, line)
    }

    fun addStringDelimiter(startKey: String, endKey: String, lineOnly: Boolean = false) {
        ObjectCalls.ptrcallWithTwoStringAndBoolArgs(addStringDelimiterBind, handle, startKey, endKey, lineOnly)
    }

    fun removeStringDelimiter(startKey: String) {
        ObjectCalls.ptrcallWithStringArg(removeStringDelimiterBind, handle, startKey)
    }

    fun hasStringDelimiter(startKey: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasStringDelimiterBind, handle, startKey)
    }

    fun setStringDelimiters(stringDelimiters: List<String>) {
        ObjectCalls.ptrcallWithTypedStringListArg(setStringDelimitersBind, handle, stringDelimiters)
    }

    fun clearStringDelimiters() {
        ObjectCalls.ptrcallNoArgs(clearStringDelimitersBind, handle)
    }

    fun getStringDelimiters(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(getStringDelimitersBind, handle)
    }

    fun isInString(line: Int, column: Int = -1): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(isInStringBind, handle, line, column)
    }

    fun addCommentDelimiter(startKey: String, endKey: String, lineOnly: Boolean = false) {
        ObjectCalls.ptrcallWithTwoStringAndBoolArgs(addCommentDelimiterBind, handle, startKey, endKey, lineOnly)
    }

    fun removeCommentDelimiter(startKey: String) {
        ObjectCalls.ptrcallWithStringArg(removeCommentDelimiterBind, handle, startKey)
    }

    fun hasCommentDelimiter(startKey: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasCommentDelimiterBind, handle, startKey)
    }

    fun setCommentDelimiters(commentDelimiters: List<String>) {
        ObjectCalls.ptrcallWithTypedStringListArg(setCommentDelimitersBind, handle, commentDelimiters)
    }

    fun clearCommentDelimiters() {
        ObjectCalls.ptrcallNoArgs(clearCommentDelimitersBind, handle)
    }

    fun getCommentDelimiters(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(getCommentDelimitersBind, handle)
    }

    fun isInComment(line: Int, column: Int = -1): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(isInCommentBind, handle, line, column)
    }

    fun getDelimiterStartKey(delimiterIndex: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getDelimiterStartKeyBind, handle, delimiterIndex)
    }

    fun getDelimiterEndKey(delimiterIndex: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getDelimiterEndKeyBind, handle, delimiterIndex)
    }

    fun getDelimiterStartPosition(line: Int, column: Int): Vector2 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector2(getDelimiterStartPositionBind, handle, line, column)
    }

    fun getDelimiterEndPosition(line: Int, column: Int): Vector2 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector2(getDelimiterEndPositionBind, handle, line, column)
    }

    fun setCodeHint(codeHint: String) {
        ObjectCalls.ptrcallWithStringArg(setCodeHintBind, handle, codeHint)
    }

    fun setCodeHintDrawBelow(drawBelow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCodeHintDrawBelowBind, handle, drawBelow)
    }

    fun getTextForCodeCompletion(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextForCodeCompletionBind, handle)
    }

    fun requestCodeCompletion(force: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(requestCodeCompletionBind, handle, force)
    }

    fun addCodeCompletionOption(type: Long, displayText: String, insertText: String, textColor: Color, icon: Resource?, value: Any? = null, location: Int = 1024) {
        ObjectCalls.ptrcallWithLongTwoStringColorObjectVariantIntArgs(addCodeCompletionOptionBind, handle, type, displayText, insertText, textColor, icon?.requireOpenHandle() ?: MemorySegment.NULL, value, location)
    }

    fun updateCodeCompletionOptions(force: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(updateCodeCompletionOptionsBind, handle, force)
    }

    fun getCodeCompletionOptions(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(getCodeCompletionOptionsBind, handle)
    }

    fun getCodeCompletionOption(index: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntArgRetDictionary(getCodeCompletionOptionBind, handle, index)
    }

    fun getCodeCompletionSelectedIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCodeCompletionSelectedIndexBind, handle)
    }

    fun setCodeCompletionSelectedIndex(index: Int) {
        ObjectCalls.ptrcallWithIntArg(setCodeCompletionSelectedIndexBind, handle, index)
    }

    fun confirmCodeCompletion(replace: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(confirmCodeCompletionBind, handle, replace)
    }

    fun cancelCodeCompletion() {
        ObjectCalls.ptrcallNoArgs(cancelCodeCompletionBind, handle)
    }

    fun setCodeCompletionEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCodeCompletionEnabledBind, handle, enable)
    }

    fun isCodeCompletionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCodeCompletionEnabledBind, handle)
    }

    fun setCodeCompletionPrefixes(prefixes: List<String>) {
        ObjectCalls.ptrcallWithTypedStringListArg(setCodeCompletionPrefixesBind, handle, prefixes)
    }

    fun getCodeCompletionPrefixes(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(getCodeCompletionPrefixesBind, handle)
    }

    fun setLineLengthGuidelines(guidelineColumns: List<Long>) {
        ObjectCalls.ptrcallWithTypedIntListArg(setLineLengthGuidelinesBind, handle, guidelineColumns)
    }

    fun getLineLengthGuidelines(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetLongList(getLineLengthGuidelinesBind, handle)
    }

    fun setSymbolLookupOnClickEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSymbolLookupOnClickEnabledBind, handle, enable)
    }

    fun isSymbolLookupOnClickEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSymbolLookupOnClickEnabledBind, handle)
    }

    fun getTextForSymbolLookup(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextForSymbolLookupBind, handle)
    }

    fun getTextWithCursorChar(line: Int, column: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(getTextWithCursorCharBind, handle, line, column)
    }

    fun setSymbolLookupWordAsValid(valid: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSymbolLookupWordAsValidBind, handle, valid)
    }

    fun setSymbolTooltipOnHoverEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSymbolTooltipOnHoverEnabledBind, handle, enable)
    }

    fun isSymbolTooltipOnHoverEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSymbolTooltipOnHoverEnabledBind, handle)
    }

    fun moveLinesUp() {
        ObjectCalls.ptrcallNoArgs(moveLinesUpBind, handle)
    }

    fun moveLinesDown() {
        ObjectCalls.ptrcallNoArgs(moveLinesDownBind, handle)
    }

    fun deleteLines() {
        ObjectCalls.ptrcallNoArgs(deleteLinesBind, handle)
    }

    fun joinLines(lineEnding: String = " ") {
        ObjectCalls.ptrcallWithStringArg(joinLinesBind, handle, lineEnding)
    }

    fun duplicateSelection() {
        ObjectCalls.ptrcallNoArgs(duplicateSelectionBind, handle)
    }

    fun duplicateLines() {
        ObjectCalls.ptrcallNoArgs(duplicateLinesBind, handle)
    }

    object Signals {
        const val breakpointToggled: String = "breakpoint_toggled"
        const val codeCompletionRequested: String = "code_completion_requested"
        const val symbolLookup: String = "symbol_lookup"
        const val symbolValidate: String = "symbol_validate"
        const val symbolHovered: String = "symbol_hovered"
    }

    companion object {
        const val KIND_CLASS: Long = 0L
        const val KIND_FUNCTION: Long = 1L
        const val KIND_SIGNAL: Long = 2L
        const val KIND_VARIABLE: Long = 3L
        const val KIND_MEMBER: Long = 4L
        const val KIND_ENUM: Long = 5L
        const val KIND_CONSTANT: Long = 6L
        const val KIND_NODE_PATH: Long = 7L
        const val KIND_FILE_PATH: Long = 8L
        const val KIND_PLAIN_TEXT: Long = 9L
        const val KIND_KEYWORD: Long = 10L
        const val LOCATION_LOCAL: Long = 0L
        const val LOCATION_PARENT_MASK: Long = 256L
        const val LOCATION_OTHER_USER_CODE: Long = 512L
        const val LOCATION_OTHER: Long = 1024L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): CodeEdit? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CodeEdit? =
            if (handle.address() == 0L) null else CodeEdit(handle)

        private const val SET_INDENT_SIZE_HASH = 1286410249L
        private val setIndentSizeBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_indent_size", SET_INDENT_SIZE_HASH)
        }

        private const val GET_INDENT_SIZE_HASH = 3905245786L
        private val getIndentSizeBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_indent_size", GET_INDENT_SIZE_HASH)
        }

        private const val SET_INDENT_USING_SPACES_HASH = 2586408642L
        private val setIndentUsingSpacesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_indent_using_spaces", SET_INDENT_USING_SPACES_HASH)
        }

        private const val IS_INDENT_USING_SPACES_HASH = 36873697L
        private val isIndentUsingSpacesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_indent_using_spaces", IS_INDENT_USING_SPACES_HASH)
        }

        private const val SET_AUTO_INDENT_ENABLED_HASH = 2586408642L
        private val setAutoIndentEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_auto_indent_enabled", SET_AUTO_INDENT_ENABLED_HASH)
        }

        private const val IS_AUTO_INDENT_ENABLED_HASH = 36873697L
        private val isAutoIndentEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_auto_indent_enabled", IS_AUTO_INDENT_ENABLED_HASH)
        }

        private const val SET_AUTO_INDENT_PREFIXES_HASH = 381264803L
        private val setAutoIndentPrefixesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_auto_indent_prefixes", SET_AUTO_INDENT_PREFIXES_HASH)
        }

        private const val GET_AUTO_INDENT_PREFIXES_HASH = 3995934104L
        private val getAutoIndentPrefixesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_auto_indent_prefixes", GET_AUTO_INDENT_PREFIXES_HASH)
        }

        private const val DO_INDENT_HASH = 3218959716L
        private val doIndentBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "do_indent", DO_INDENT_HASH)
        }

        private const val INDENT_LINES_HASH = 3218959716L
        private val indentLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "indent_lines", INDENT_LINES_HASH)
        }

        private const val UNINDENT_LINES_HASH = 3218959716L
        private val unindentLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "unindent_lines", UNINDENT_LINES_HASH)
        }

        private const val CONVERT_INDENT_HASH = 423910286L
        private val convertIndentBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "convert_indent", CONVERT_INDENT_HASH)
        }

        private const val SET_AUTO_BRACE_COMPLETION_ENABLED_HASH = 2586408642L
        private val setAutoBraceCompletionEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_auto_brace_completion_enabled", SET_AUTO_BRACE_COMPLETION_ENABLED_HASH)
        }

        private const val IS_AUTO_BRACE_COMPLETION_ENABLED_HASH = 36873697L
        private val isAutoBraceCompletionEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_auto_brace_completion_enabled", IS_AUTO_BRACE_COMPLETION_ENABLED_HASH)
        }

        private const val SET_HIGHLIGHT_MATCHING_BRACES_ENABLED_HASH = 2586408642L
        private val setHighlightMatchingBracesEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_highlight_matching_braces_enabled", SET_HIGHLIGHT_MATCHING_BRACES_ENABLED_HASH)
        }

        private const val IS_HIGHLIGHT_MATCHING_BRACES_ENABLED_HASH = 36873697L
        private val isHighlightMatchingBracesEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_highlight_matching_braces_enabled", IS_HIGHLIGHT_MATCHING_BRACES_ENABLED_HASH)
        }

        private const val ADD_AUTO_BRACE_COMPLETION_PAIR_HASH = 3186203200L
        private val addAutoBraceCompletionPairBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "add_auto_brace_completion_pair", ADD_AUTO_BRACE_COMPLETION_PAIR_HASH)
        }

        private const val SET_AUTO_BRACE_COMPLETION_PAIRS_HASH = 4155329257L
        private val setAutoBraceCompletionPairsBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_auto_brace_completion_pairs", SET_AUTO_BRACE_COMPLETION_PAIRS_HASH)
        }

        private const val GET_AUTO_BRACE_COMPLETION_PAIRS_HASH = 3102165223L
        private val getAutoBraceCompletionPairsBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_auto_brace_completion_pairs", GET_AUTO_BRACE_COMPLETION_PAIRS_HASH)
        }

        private const val HAS_AUTO_BRACE_COMPLETION_OPEN_KEY_HASH = 3927539163L
        private val hasAutoBraceCompletionOpenKeyBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "has_auto_brace_completion_open_key", HAS_AUTO_BRACE_COMPLETION_OPEN_KEY_HASH)
        }

        private const val HAS_AUTO_BRACE_COMPLETION_CLOSE_KEY_HASH = 3927539163L
        private val hasAutoBraceCompletionCloseKeyBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "has_auto_brace_completion_close_key", HAS_AUTO_BRACE_COMPLETION_CLOSE_KEY_HASH)
        }

        private const val GET_AUTO_BRACE_COMPLETION_CLOSE_KEY_HASH = 3135753539L
        private val getAutoBraceCompletionCloseKeyBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_auto_brace_completion_close_key", GET_AUTO_BRACE_COMPLETION_CLOSE_KEY_HASH)
        }

        private const val SET_DRAW_BREAKPOINTS_GUTTER_HASH = 2586408642L
        private val setDrawBreakpointsGutterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_draw_breakpoints_gutter", SET_DRAW_BREAKPOINTS_GUTTER_HASH)
        }

        private const val IS_DRAWING_BREAKPOINTS_GUTTER_HASH = 36873697L
        private val isDrawingBreakpointsGutterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_drawing_breakpoints_gutter", IS_DRAWING_BREAKPOINTS_GUTTER_HASH)
        }

        private const val SET_DRAW_BOOKMARKS_GUTTER_HASH = 2586408642L
        private val setDrawBookmarksGutterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_draw_bookmarks_gutter", SET_DRAW_BOOKMARKS_GUTTER_HASH)
        }

        private const val IS_DRAWING_BOOKMARKS_GUTTER_HASH = 36873697L
        private val isDrawingBookmarksGutterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_drawing_bookmarks_gutter", IS_DRAWING_BOOKMARKS_GUTTER_HASH)
        }

        private const val SET_DRAW_EXECUTING_LINES_GUTTER_HASH = 2586408642L
        private val setDrawExecutingLinesGutterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_draw_executing_lines_gutter", SET_DRAW_EXECUTING_LINES_GUTTER_HASH)
        }

        private const val IS_DRAWING_EXECUTING_LINES_GUTTER_HASH = 36873697L
        private val isDrawingExecutingLinesGutterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_drawing_executing_lines_gutter", IS_DRAWING_EXECUTING_LINES_GUTTER_HASH)
        }

        private const val SET_LINE_AS_BREAKPOINT_HASH = 300928843L
        private val setLineAsBreakpointBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_line_as_breakpoint", SET_LINE_AS_BREAKPOINT_HASH)
        }

        private const val IS_LINE_BREAKPOINTED_HASH = 1116898809L
        private val isLineBreakpointedBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_line_breakpointed", IS_LINE_BREAKPOINTED_HASH)
        }

        private const val CLEAR_BREAKPOINTED_LINES_HASH = 3218959716L
        private val clearBreakpointedLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "clear_breakpointed_lines", CLEAR_BREAKPOINTED_LINES_HASH)
        }

        private const val GET_BREAKPOINTED_LINES_HASH = 1930428628L
        private val getBreakpointedLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_breakpointed_lines", GET_BREAKPOINTED_LINES_HASH)
        }

        private const val SET_LINE_AS_BOOKMARKED_HASH = 300928843L
        private val setLineAsBookmarkedBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_line_as_bookmarked", SET_LINE_AS_BOOKMARKED_HASH)
        }

        private const val IS_LINE_BOOKMARKED_HASH = 1116898809L
        private val isLineBookmarkedBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_line_bookmarked", IS_LINE_BOOKMARKED_HASH)
        }

        private const val CLEAR_BOOKMARKED_LINES_HASH = 3218959716L
        private val clearBookmarkedLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "clear_bookmarked_lines", CLEAR_BOOKMARKED_LINES_HASH)
        }

        private const val GET_BOOKMARKED_LINES_HASH = 1930428628L
        private val getBookmarkedLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_bookmarked_lines", GET_BOOKMARKED_LINES_HASH)
        }

        private const val SET_LINE_AS_EXECUTING_HASH = 300928843L
        private val setLineAsExecutingBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_line_as_executing", SET_LINE_AS_EXECUTING_HASH)
        }

        private const val IS_LINE_EXECUTING_HASH = 1116898809L
        private val isLineExecutingBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_line_executing", IS_LINE_EXECUTING_HASH)
        }

        private const val CLEAR_EXECUTING_LINES_HASH = 3218959716L
        private val clearExecutingLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "clear_executing_lines", CLEAR_EXECUTING_LINES_HASH)
        }

        private const val GET_EXECUTING_LINES_HASH = 1930428628L
        private val getExecutingLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_executing_lines", GET_EXECUTING_LINES_HASH)
        }

        private const val SET_DRAW_LINE_NUMBERS_HASH = 2586408642L
        private val setDrawLineNumbersBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_draw_line_numbers", SET_DRAW_LINE_NUMBERS_HASH)
        }

        private const val IS_DRAW_LINE_NUMBERS_ENABLED_HASH = 36873697L
        private val isDrawLineNumbersEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_draw_line_numbers_enabled", IS_DRAW_LINE_NUMBERS_ENABLED_HASH)
        }

        private const val SET_LINE_NUMBERS_ZERO_PADDED_HASH = 2586408642L
        private val setLineNumbersZeroPaddedBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_line_numbers_zero_padded", SET_LINE_NUMBERS_ZERO_PADDED_HASH)
        }

        private const val IS_LINE_NUMBERS_ZERO_PADDED_HASH = 36873697L
        private val isLineNumbersZeroPaddedBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_line_numbers_zero_padded", IS_LINE_NUMBERS_ZERO_PADDED_HASH)
        }

        private const val SET_LINE_NUMBERS_MIN_DIGITS_HASH = 1286410249L
        private val setLineNumbersMinDigitsBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_line_numbers_min_digits", SET_LINE_NUMBERS_MIN_DIGITS_HASH)
        }

        private const val GET_LINE_NUMBERS_MIN_DIGITS_HASH = 3905245786L
        private val getLineNumbersMinDigitsBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_line_numbers_min_digits", GET_LINE_NUMBERS_MIN_DIGITS_HASH)
        }

        private const val SET_DRAW_FOLD_GUTTER_HASH = 2586408642L
        private val setDrawFoldGutterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_draw_fold_gutter", SET_DRAW_FOLD_GUTTER_HASH)
        }

        private const val IS_DRAWING_FOLD_GUTTER_HASH = 36873697L
        private val isDrawingFoldGutterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_drawing_fold_gutter", IS_DRAWING_FOLD_GUTTER_HASH)
        }

        private const val SET_LINE_FOLDING_ENABLED_HASH = 2586408642L
        private val setLineFoldingEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_line_folding_enabled", SET_LINE_FOLDING_ENABLED_HASH)
        }

        private const val IS_LINE_FOLDING_ENABLED_HASH = 36873697L
        private val isLineFoldingEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_line_folding_enabled", IS_LINE_FOLDING_ENABLED_HASH)
        }

        private const val CAN_FOLD_LINE_HASH = 1116898809L
        private val canFoldLineBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "can_fold_line", CAN_FOLD_LINE_HASH)
        }

        private const val FOLD_LINE_HASH = 1286410249L
        private val foldLineBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "fold_line", FOLD_LINE_HASH)
        }

        private const val UNFOLD_LINE_HASH = 1286410249L
        private val unfoldLineBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "unfold_line", UNFOLD_LINE_HASH)
        }

        private const val FOLD_ALL_LINES_HASH = 3218959716L
        private val foldAllLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "fold_all_lines", FOLD_ALL_LINES_HASH)
        }

        private const val UNFOLD_ALL_LINES_HASH = 3218959716L
        private val unfoldAllLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "unfold_all_lines", UNFOLD_ALL_LINES_HASH)
        }

        private const val TOGGLE_FOLDABLE_LINE_HASH = 1286410249L
        private val toggleFoldableLineBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "toggle_foldable_line", TOGGLE_FOLDABLE_LINE_HASH)
        }

        private const val TOGGLE_FOLDABLE_LINES_AT_CARETS_HASH = 3218959716L
        private val toggleFoldableLinesAtCaretsBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "toggle_foldable_lines_at_carets", TOGGLE_FOLDABLE_LINES_AT_CARETS_HASH)
        }

        private const val IS_LINE_FOLDED_HASH = 1116898809L
        private val isLineFoldedBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_line_folded", IS_LINE_FOLDED_HASH)
        }

        private const val GET_FOLDED_LINES_HASH = 3995934104L
        private val getFoldedLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_folded_lines", GET_FOLDED_LINES_HASH)
        }

        private const val CREATE_CODE_REGION_HASH = 3218959716L
        private val createCodeRegionBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "create_code_region", CREATE_CODE_REGION_HASH)
        }

        private const val GET_CODE_REGION_START_TAG_HASH = 201670096L
        private val getCodeRegionStartTagBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_code_region_start_tag", GET_CODE_REGION_START_TAG_HASH)
        }

        private const val GET_CODE_REGION_END_TAG_HASH = 201670096L
        private val getCodeRegionEndTagBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_code_region_end_tag", GET_CODE_REGION_END_TAG_HASH)
        }

        private const val SET_CODE_REGION_TAGS_HASH = 708800718L
        private val setCodeRegionTagsBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_code_region_tags", SET_CODE_REGION_TAGS_HASH)
        }

        private const val IS_LINE_CODE_REGION_START_HASH = 1116898809L
        private val isLineCodeRegionStartBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_line_code_region_start", IS_LINE_CODE_REGION_START_HASH)
        }

        private const val IS_LINE_CODE_REGION_END_HASH = 1116898809L
        private val isLineCodeRegionEndBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_line_code_region_end", IS_LINE_CODE_REGION_END_HASH)
        }

        private const val ADD_STRING_DELIMITER_HASH = 3146098955L
        private val addStringDelimiterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "add_string_delimiter", ADD_STRING_DELIMITER_HASH)
        }

        private const val REMOVE_STRING_DELIMITER_HASH = 83702148L
        private val removeStringDelimiterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "remove_string_delimiter", REMOVE_STRING_DELIMITER_HASH)
        }

        private const val HAS_STRING_DELIMITER_HASH = 3927539163L
        private val hasStringDelimiterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "has_string_delimiter", HAS_STRING_DELIMITER_HASH)
        }

        private const val SET_STRING_DELIMITERS_HASH = 381264803L
        private val setStringDelimitersBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_string_delimiters", SET_STRING_DELIMITERS_HASH)
        }

        private const val CLEAR_STRING_DELIMITERS_HASH = 3218959716L
        private val clearStringDelimitersBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "clear_string_delimiters", CLEAR_STRING_DELIMITERS_HASH)
        }

        private const val GET_STRING_DELIMITERS_HASH = 3995934104L
        private val getStringDelimitersBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_string_delimiters", GET_STRING_DELIMITERS_HASH)
        }

        private const val IS_IN_STRING_HASH = 688195400L
        private val isInStringBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_in_string", IS_IN_STRING_HASH)
        }

        private const val ADD_COMMENT_DELIMITER_HASH = 3146098955L
        private val addCommentDelimiterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "add_comment_delimiter", ADD_COMMENT_DELIMITER_HASH)
        }

        private const val REMOVE_COMMENT_DELIMITER_HASH = 83702148L
        private val removeCommentDelimiterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "remove_comment_delimiter", REMOVE_COMMENT_DELIMITER_HASH)
        }

        private const val HAS_COMMENT_DELIMITER_HASH = 3927539163L
        private val hasCommentDelimiterBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "has_comment_delimiter", HAS_COMMENT_DELIMITER_HASH)
        }

        private const val SET_COMMENT_DELIMITERS_HASH = 381264803L
        private val setCommentDelimitersBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_comment_delimiters", SET_COMMENT_DELIMITERS_HASH)
        }

        private const val CLEAR_COMMENT_DELIMITERS_HASH = 3218959716L
        private val clearCommentDelimitersBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "clear_comment_delimiters", CLEAR_COMMENT_DELIMITERS_HASH)
        }

        private const val GET_COMMENT_DELIMITERS_HASH = 3995934104L
        private val getCommentDelimitersBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_comment_delimiters", GET_COMMENT_DELIMITERS_HASH)
        }

        private const val IS_IN_COMMENT_HASH = 688195400L
        private val isInCommentBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_in_comment", IS_IN_COMMENT_HASH)
        }

        private const val GET_DELIMITER_START_KEY_HASH = 844755477L
        private val getDelimiterStartKeyBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_delimiter_start_key", GET_DELIMITER_START_KEY_HASH)
        }

        private const val GET_DELIMITER_END_KEY_HASH = 844755477L
        private val getDelimiterEndKeyBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_delimiter_end_key", GET_DELIMITER_END_KEY_HASH)
        }

        private const val GET_DELIMITER_START_POSITION_HASH = 3016396712L
        private val getDelimiterStartPositionBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_delimiter_start_position", GET_DELIMITER_START_POSITION_HASH)
        }

        private const val GET_DELIMITER_END_POSITION_HASH = 3016396712L
        private val getDelimiterEndPositionBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_delimiter_end_position", GET_DELIMITER_END_POSITION_HASH)
        }

        private const val SET_CODE_HINT_HASH = 83702148L
        private val setCodeHintBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_code_hint", SET_CODE_HINT_HASH)
        }

        private const val SET_CODE_HINT_DRAW_BELOW_HASH = 2586408642L
        private val setCodeHintDrawBelowBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_code_hint_draw_below", SET_CODE_HINT_DRAW_BELOW_HASH)
        }

        private const val GET_TEXT_FOR_CODE_COMPLETION_HASH = 201670096L
        private val getTextForCodeCompletionBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_text_for_code_completion", GET_TEXT_FOR_CODE_COMPLETION_HASH)
        }

        private const val REQUEST_CODE_COMPLETION_HASH = 107499316L
        private val requestCodeCompletionBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "request_code_completion", REQUEST_CODE_COMPLETION_HASH)
        }

        private const val ADD_CODE_COMPLETION_OPTION_HASH = 3944379502L
        private val addCodeCompletionOptionBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "add_code_completion_option", ADD_CODE_COMPLETION_OPTION_HASH)
        }

        private const val UPDATE_CODE_COMPLETION_OPTIONS_HASH = 2586408642L
        private val updateCodeCompletionOptionsBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "update_code_completion_options", UPDATE_CODE_COMPLETION_OPTIONS_HASH)
        }

        private const val GET_CODE_COMPLETION_OPTIONS_HASH = 3995934104L
        private val getCodeCompletionOptionsBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_code_completion_options", GET_CODE_COMPLETION_OPTIONS_HASH)
        }

        private const val GET_CODE_COMPLETION_OPTION_HASH = 3485342025L
        private val getCodeCompletionOptionBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_code_completion_option", GET_CODE_COMPLETION_OPTION_HASH)
        }

        private const val GET_CODE_COMPLETION_SELECTED_INDEX_HASH = 3905245786L
        private val getCodeCompletionSelectedIndexBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_code_completion_selected_index", GET_CODE_COMPLETION_SELECTED_INDEX_HASH)
        }

        private const val SET_CODE_COMPLETION_SELECTED_INDEX_HASH = 1286410249L
        private val setCodeCompletionSelectedIndexBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_code_completion_selected_index", SET_CODE_COMPLETION_SELECTED_INDEX_HASH)
        }

        private const val CONFIRM_CODE_COMPLETION_HASH = 107499316L
        private val confirmCodeCompletionBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "confirm_code_completion", CONFIRM_CODE_COMPLETION_HASH)
        }

        private const val CANCEL_CODE_COMPLETION_HASH = 3218959716L
        private val cancelCodeCompletionBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "cancel_code_completion", CANCEL_CODE_COMPLETION_HASH)
        }

        private const val SET_CODE_COMPLETION_ENABLED_HASH = 2586408642L
        private val setCodeCompletionEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_code_completion_enabled", SET_CODE_COMPLETION_ENABLED_HASH)
        }

        private const val IS_CODE_COMPLETION_ENABLED_HASH = 36873697L
        private val isCodeCompletionEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_code_completion_enabled", IS_CODE_COMPLETION_ENABLED_HASH)
        }

        private const val SET_CODE_COMPLETION_PREFIXES_HASH = 381264803L
        private val setCodeCompletionPrefixesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_code_completion_prefixes", SET_CODE_COMPLETION_PREFIXES_HASH)
        }

        private const val GET_CODE_COMPLETION_PREFIXES_HASH = 3995934104L
        private val getCodeCompletionPrefixesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_code_completion_prefixes", GET_CODE_COMPLETION_PREFIXES_HASH)
        }

        private const val SET_LINE_LENGTH_GUIDELINES_HASH = 381264803L
        private val setLineLengthGuidelinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_line_length_guidelines", SET_LINE_LENGTH_GUIDELINES_HASH)
        }

        private const val GET_LINE_LENGTH_GUIDELINES_HASH = 3995934104L
        private val getLineLengthGuidelinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_line_length_guidelines", GET_LINE_LENGTH_GUIDELINES_HASH)
        }

        private const val SET_SYMBOL_LOOKUP_ON_CLICK_ENABLED_HASH = 2586408642L
        private val setSymbolLookupOnClickEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_symbol_lookup_on_click_enabled", SET_SYMBOL_LOOKUP_ON_CLICK_ENABLED_HASH)
        }

        private const val IS_SYMBOL_LOOKUP_ON_CLICK_ENABLED_HASH = 36873697L
        private val isSymbolLookupOnClickEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_symbol_lookup_on_click_enabled", IS_SYMBOL_LOOKUP_ON_CLICK_ENABLED_HASH)
        }

        private const val GET_TEXT_FOR_SYMBOL_LOOKUP_HASH = 201670096L
        private val getTextForSymbolLookupBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_text_for_symbol_lookup", GET_TEXT_FOR_SYMBOL_LOOKUP_HASH)
        }

        private const val GET_TEXT_WITH_CURSOR_CHAR_HASH = 1391810591L
        private val getTextWithCursorCharBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "get_text_with_cursor_char", GET_TEXT_WITH_CURSOR_CHAR_HASH)
        }

        private const val SET_SYMBOL_LOOKUP_WORD_AS_VALID_HASH = 2586408642L
        private val setSymbolLookupWordAsValidBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_symbol_lookup_word_as_valid", SET_SYMBOL_LOOKUP_WORD_AS_VALID_HASH)
        }

        private const val SET_SYMBOL_TOOLTIP_ON_HOVER_ENABLED_HASH = 2586408642L
        private val setSymbolTooltipOnHoverEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "set_symbol_tooltip_on_hover_enabled", SET_SYMBOL_TOOLTIP_ON_HOVER_ENABLED_HASH)
        }

        private const val IS_SYMBOL_TOOLTIP_ON_HOVER_ENABLED_HASH = 36873697L
        private val isSymbolTooltipOnHoverEnabledBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "is_symbol_tooltip_on_hover_enabled", IS_SYMBOL_TOOLTIP_ON_HOVER_ENABLED_HASH)
        }

        private const val MOVE_LINES_UP_HASH = 3218959716L
        private val moveLinesUpBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "move_lines_up", MOVE_LINES_UP_HASH)
        }

        private const val MOVE_LINES_DOWN_HASH = 3218959716L
        private val moveLinesDownBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "move_lines_down", MOVE_LINES_DOWN_HASH)
        }

        private const val DELETE_LINES_HASH = 3218959716L
        private val deleteLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "delete_lines", DELETE_LINES_HASH)
        }

        private const val JOIN_LINES_HASH = 4063782979L
        private val joinLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "join_lines", JOIN_LINES_HASH)
        }

        private const val DUPLICATE_SELECTION_HASH = 3218959716L
        private val duplicateSelectionBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "duplicate_selection", DUPLICATE_SELECTION_HASH)
        }

        private const val DUPLICATE_LINES_HASH = 3218959716L
        private val duplicateLinesBind by lazy {
            ObjectCalls.getMethodBind("CodeEdit", "duplicate_lines", DUPLICATE_LINES_HASH)
        }
    }
}
