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

    /**
     * Size of the tabulation indent (one Tab press) in characters. If `indent_use_spaces` is enabled
     * the number of spaces to use.
     *
     * Generated from Godot docs: CodeEdit.set_indent_size
     */
    fun setIndentSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setIndentSizeBind, handle, size)
    }

    /**
     * Size of the tabulation indent (one Tab press) in characters. If `indent_use_spaces` is enabled
     * the number of spaces to use.
     *
     * Generated from Godot docs: CodeEdit.get_indent_size
     */
    fun getIndentSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getIndentSizeBind, handle)
    }

    /**
     * Use spaces instead of tabs for indentation.
     *
     * Generated from Godot docs: CodeEdit.set_indent_using_spaces
     */
    fun setIndentUsingSpaces(useSpaces: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIndentUsingSpacesBind, handle, useSpaces)
    }

    /**
     * Use spaces instead of tabs for indentation.
     *
     * Generated from Godot docs: CodeEdit.is_indent_using_spaces
     */
    fun isIndentUsingSpaces(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIndentUsingSpacesBind, handle)
    }

    /**
     * If `true`, an extra indent is automatically inserted when a new line is added and a prefix in
     * `indent_automatic_prefixes` is found. If a brace pair opening key is found, the matching closing
     * brace will be moved to another new line (see `auto_brace_completion_pairs`).
     *
     * Generated from Godot docs: CodeEdit.set_auto_indent_enabled
     */
    fun setAutoIndentEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoIndentEnabledBind, handle, enable)
    }

    /**
     * If `true`, an extra indent is automatically inserted when a new line is added and a prefix in
     * `indent_automatic_prefixes` is found. If a brace pair opening key is found, the matching closing
     * brace will be moved to another new line (see `auto_brace_completion_pairs`).
     *
     * Generated from Godot docs: CodeEdit.is_auto_indent_enabled
     */
    fun isAutoIndentEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutoIndentEnabledBind, handle)
    }

    /**
     * Prefixes to trigger an automatic indent. Used when `indent_automatic` is set to `true`.
     *
     * Generated from Godot docs: CodeEdit.set_auto_indent_prefixes
     */
    fun setAutoIndentPrefixes(prefixes: List<String>) {
        ObjectCalls.ptrcallWithTypedStringListArg(setAutoIndentPrefixesBind, handle, prefixes)
    }

    /**
     * Prefixes to trigger an automatic indent. Used when `indent_automatic` is set to `true`.
     *
     * Generated from Godot docs: CodeEdit.get_auto_indent_prefixes
     */
    fun getAutoIndentPrefixes(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(getAutoIndentPrefixesBind, handle)
    }

    /**
     * If there is no selection, indentation is inserted at the caret. Otherwise, the selected lines
     * are indented like `indent_lines`. Equivalent to the `ProjectSettings.input/ui_text_indent`
     * action. The indentation characters used depend on `indent_use_spaces` and `indent_size`.
     *
     * Generated from Godot docs: CodeEdit.do_indent
     */
    fun doIndent() {
        ObjectCalls.ptrcallNoArgs(doIndentBind, handle)
    }

    /**
     * Indents all lines that are selected or have a caret on them. Uses spaces or a tab depending on
     * `indent_use_spaces`. See `unindent_lines`.
     *
     * Generated from Godot docs: CodeEdit.indent_lines
     */
    fun indentLines() {
        ObjectCalls.ptrcallNoArgs(indentLinesBind, handle)
    }

    /**
     * Unindents all lines that are selected or have a caret on them. Uses spaces or a tab depending on
     * `indent_use_spaces`. Equivalent to the `ProjectSettings.input/ui_text_dedent` action. See
     * `indent_lines`.
     *
     * Generated from Godot docs: CodeEdit.unindent_lines
     */
    fun unindentLines() {
        ObjectCalls.ptrcallNoArgs(unindentLinesBind, handle)
    }

    /**
     * Converts the indents of lines between `from_line` and `to_line` to tabs or spaces as set by
     * `indent_use_spaces`. Values of `-1` convert the entire text.
     *
     * Generated from Godot docs: CodeEdit.convert_indent
     */
    fun convertIndent(fromLine: Int = -1, toLine: Int = -1) {
        ObjectCalls.ptrcallWithTwoIntArgs(convertIndentBind, handle, fromLine, toLine)
    }

    /**
     * If `true`, uses `auto_brace_completion_pairs` to automatically insert the closing brace when the
     * opening brace is inserted by typing or autocompletion. Also automatically removes the closing
     * brace when using backspace on the opening brace.
     *
     * Generated from Godot docs: CodeEdit.set_auto_brace_completion_enabled
     */
    fun setAutoBraceCompletionEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutoBraceCompletionEnabledBind, handle, enable)
    }

    /**
     * If `true`, uses `auto_brace_completion_pairs` to automatically insert the closing brace when the
     * opening brace is inserted by typing or autocompletion. Also automatically removes the closing
     * brace when using backspace on the opening brace.
     *
     * Generated from Godot docs: CodeEdit.is_auto_brace_completion_enabled
     */
    fun isAutoBraceCompletionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAutoBraceCompletionEnabledBind, handle)
    }

    /**
     * If `true`, highlights brace pairs when the caret is on either one, using
     * `auto_brace_completion_pairs`. If matching, the pairs will be underlined. If a brace is
     * unmatched, it is colored with `brace_mismatch_color`.
     *
     * Generated from Godot docs: CodeEdit.set_highlight_matching_braces_enabled
     */
    fun setHighlightMatchingBracesEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHighlightMatchingBracesEnabledBind, handle, enable)
    }

    /**
     * If `true`, highlights brace pairs when the caret is on either one, using
     * `auto_brace_completion_pairs`. If matching, the pairs will be underlined. If a brace is
     * unmatched, it is colored with `brace_mismatch_color`.
     *
     * Generated from Godot docs: CodeEdit.is_highlight_matching_braces_enabled
     */
    fun isHighlightMatchingBracesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHighlightMatchingBracesEnabledBind, handle)
    }

    /**
     * Adds a brace pair. Both the start and end keys must be symbols. Only the start key has to be
     * unique.
     *
     * Generated from Godot docs: CodeEdit.add_auto_brace_completion_pair
     */
    fun addAutoBraceCompletionPair(startKey: String, endKey: String) {
        ObjectCalls.ptrcallWithTwoStringArgs(addAutoBraceCompletionPairBind, handle, startKey, endKey)
    }

    /**
     * Sets the brace pairs to be autocompleted. For each entry in the dictionary, the key is the
     * opening brace and the value is the closing brace that matches it. A brace is a `String` made of
     * symbols. See `auto_brace_completion_enabled` and `auto_brace_completion_highlight_matching`.
     *
     * Generated from Godot docs: CodeEdit.set_auto_brace_completion_pairs
     */
    fun setAutoBraceCompletionPairs(pairs: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setAutoBraceCompletionPairsBind, handle, pairs)
    }

    /**
     * Sets the brace pairs to be autocompleted. For each entry in the dictionary, the key is the
     * opening brace and the value is the closing brace that matches it. A brace is a `String` made of
     * symbols. See `auto_brace_completion_enabled` and `auto_brace_completion_highlight_matching`.
     *
     * Generated from Godot docs: CodeEdit.get_auto_brace_completion_pairs
     */
    fun getAutoBraceCompletionPairs(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getAutoBraceCompletionPairsBind, handle)
    }

    /**
     * Returns `true` if open key `open_key` exists.
     *
     * Generated from Godot docs: CodeEdit.has_auto_brace_completion_open_key
     */
    fun hasAutoBraceCompletionOpenKey(openKey: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasAutoBraceCompletionOpenKeyBind, handle, openKey)
    }

    /**
     * Returns `true` if close key `close_key` exists.
     *
     * Generated from Godot docs: CodeEdit.has_auto_brace_completion_close_key
     */
    fun hasAutoBraceCompletionCloseKey(closeKey: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasAutoBraceCompletionCloseKeyBind, handle, closeKey)
    }

    /**
     * Gets the matching auto brace close key for `open_key`.
     *
     * Generated from Godot docs: CodeEdit.get_auto_brace_completion_close_key
     */
    fun getAutoBraceCompletionCloseKey(openKey: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(getAutoBraceCompletionCloseKeyBind, handle, openKey)
    }

    /**
     * If `true`, breakpoints are drawn in the gutter. This gutter is shared with bookmarks and
     * executing lines. Clicking the gutter will toggle the breakpoint for the line, see
     * `set_line_as_breakpoint`.
     *
     * Generated from Godot docs: CodeEdit.set_draw_breakpoints_gutter
     */
    fun setDrawBreakpointsGutter(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawBreakpointsGutterBind, handle, enable)
    }

    /**
     * If `true`, breakpoints are drawn in the gutter. This gutter is shared with bookmarks and
     * executing lines. Clicking the gutter will toggle the breakpoint for the line, see
     * `set_line_as_breakpoint`.
     *
     * Generated from Godot docs: CodeEdit.is_drawing_breakpoints_gutter
     */
    fun isDrawingBreakpointsGutter(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawingBreakpointsGutterBind, handle)
    }

    /**
     * If `true`, bookmarks are drawn in the gutter. This gutter is shared with breakpoints and
     * executing lines. See `set_line_as_bookmarked`.
     *
     * Generated from Godot docs: CodeEdit.set_draw_bookmarks_gutter
     */
    fun setDrawBookmarksGutter(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawBookmarksGutterBind, handle, enable)
    }

    /**
     * If `true`, bookmarks are drawn in the gutter. This gutter is shared with breakpoints and
     * executing lines. See `set_line_as_bookmarked`.
     *
     * Generated from Godot docs: CodeEdit.is_drawing_bookmarks_gutter
     */
    fun isDrawingBookmarksGutter(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawingBookmarksGutterBind, handle)
    }

    /**
     * If `true`, executing lines are marked in the gutter. This gutter is shared with breakpoints and
     * bookmarks. See `set_line_as_executing`.
     *
     * Generated from Godot docs: CodeEdit.set_draw_executing_lines_gutter
     */
    fun setDrawExecutingLinesGutter(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawExecutingLinesGutterBind, handle, enable)
    }

    /**
     * If `true`, executing lines are marked in the gutter. This gutter is shared with breakpoints and
     * bookmarks. See `set_line_as_executing`.
     *
     * Generated from Godot docs: CodeEdit.is_drawing_executing_lines_gutter
     */
    fun isDrawingExecutingLinesGutter(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawingExecutingLinesGutterBind, handle)
    }

    /**
     * Sets the given line as a breakpoint. If `true` and `gutters_draw_breakpoints_gutter` is `true`,
     * draws the `breakpoint` icon in the gutter for this line. See `get_breakpointed_lines` and
     * `is_line_breakpointed`.
     *
     * Generated from Godot docs: CodeEdit.set_line_as_breakpoint
     */
    fun setLineAsBreakpoint(line: Int, breakpointed: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLineAsBreakpointBind, handle, line, breakpointed)
    }

    /**
     * Returns `true` if the given line is breakpointed. See `set_line_as_breakpoint`.
     *
     * Generated from Godot docs: CodeEdit.is_line_breakpointed
     */
    fun isLineBreakpointed(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineBreakpointedBind, handle, line)
    }

    /**
     * Clears all breakpointed lines.
     *
     * Generated from Godot docs: CodeEdit.clear_breakpointed_lines
     */
    fun clearBreakpointedLines() {
        ObjectCalls.ptrcallNoArgs(clearBreakpointedLinesBind, handle)
    }

    /**
     * Gets all breakpointed lines.
     *
     * Generated from Godot docs: CodeEdit.get_breakpointed_lines
     */
    fun getBreakpointedLines(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getBreakpointedLinesBind, handle)
    }

    /**
     * Sets the given line as bookmarked. If `true` and `gutters_draw_bookmarks` is `true`, draws the
     * `bookmark` icon in the gutter for this line. See `get_bookmarked_lines` and
     * `is_line_bookmarked`.
     *
     * Generated from Godot docs: CodeEdit.set_line_as_bookmarked
     */
    fun setLineAsBookmarked(line: Int, bookmarked: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLineAsBookmarkedBind, handle, line, bookmarked)
    }

    /**
     * Returns `true` if the given line is bookmarked. See `set_line_as_bookmarked`.
     *
     * Generated from Godot docs: CodeEdit.is_line_bookmarked
     */
    fun isLineBookmarked(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineBookmarkedBind, handle, line)
    }

    /**
     * Clears all bookmarked lines.
     *
     * Generated from Godot docs: CodeEdit.clear_bookmarked_lines
     */
    fun clearBookmarkedLines() {
        ObjectCalls.ptrcallNoArgs(clearBookmarkedLinesBind, handle)
    }

    /**
     * Gets all bookmarked lines.
     *
     * Generated from Godot docs: CodeEdit.get_bookmarked_lines
     */
    fun getBookmarkedLines(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getBookmarkedLinesBind, handle)
    }

    /**
     * Sets the given line as executing. If `true` and `gutters_draw_executing_lines` is `true`, draws
     * the `executing_line` icon in the gutter for this line. See `get_executing_lines` and
     * `is_line_executing`.
     *
     * Generated from Godot docs: CodeEdit.set_line_as_executing
     */
    fun setLineAsExecuting(line: Int, executing: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setLineAsExecutingBind, handle, line, executing)
    }

    /**
     * Returns `true` if the given line is marked as executing. See `set_line_as_executing`.
     *
     * Generated from Godot docs: CodeEdit.is_line_executing
     */
    fun isLineExecuting(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineExecutingBind, handle, line)
    }

    /**
     * Clears all executed lines.
     *
     * Generated from Godot docs: CodeEdit.clear_executing_lines
     */
    fun clearExecutingLines() {
        ObjectCalls.ptrcallNoArgs(clearExecutingLinesBind, handle)
    }

    /**
     * Gets all executing lines.
     *
     * Generated from Godot docs: CodeEdit.get_executing_lines
     */
    fun getExecutingLines(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getExecutingLinesBind, handle)
    }

    /**
     * If `true`, the line number gutter is drawn. Line numbers start at `1` and are incremented for
     * each line of text. Clicking and dragging in the line number gutter will select entire lines of
     * text.
     *
     * Generated from Godot docs: CodeEdit.set_draw_line_numbers
     */
    fun setDrawLineNumbers(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawLineNumbersBind, handle, enable)
    }

    /**
     * If `true`, the line number gutter is drawn. Line numbers start at `1` and are incremented for
     * each line of text. Clicking and dragging in the line number gutter will select entire lines of
     * text.
     *
     * Generated from Godot docs: CodeEdit.is_draw_line_numbers_enabled
     */
    fun isDrawLineNumbersEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawLineNumbersEnabledBind, handle)
    }

    /**
     * If `true`, line numbers drawn in the gutter are zero padded based on the total line count.
     * Requires `gutters_draw_line_numbers` to be set to `true`.
     *
     * Generated from Godot docs: CodeEdit.set_line_numbers_zero_padded
     */
    fun setLineNumbersZeroPadded(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLineNumbersZeroPaddedBind, handle, enable)
    }

    /**
     * If `true`, line numbers drawn in the gutter are zero padded based on the total line count.
     * Requires `gutters_draw_line_numbers` to be set to `true`.
     *
     * Generated from Godot docs: CodeEdit.is_line_numbers_zero_padded
     */
    fun isLineNumbersZeroPadded(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLineNumbersZeroPaddedBind, handle)
    }

    /**
     * The minimum width in digits reserved for the line number gutter.
     *
     * Generated from Godot docs: CodeEdit.set_line_numbers_min_digits
     */
    fun setLineNumbersMinDigits(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setLineNumbersMinDigitsBind, handle, count)
    }

    /**
     * The minimum width in digits reserved for the line number gutter.
     *
     * Generated from Godot docs: CodeEdit.get_line_numbers_min_digits
     */
    fun getLineNumbersMinDigits(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLineNumbersMinDigitsBind, handle)
    }

    /**
     * If `true`, the fold gutter is drawn. In this gutter, the `can_fold_code_region` icon is drawn
     * for each foldable line (see `can_fold_line`) and the `folded_code_region` icon is drawn for each
     * folded line (see `is_line_folded`). These icons can be clicked to toggle the fold state, see
     * `toggle_foldable_line`. `line_folding` must be `true` to show icons.
     *
     * Generated from Godot docs: CodeEdit.set_draw_fold_gutter
     */
    fun setDrawFoldGutter(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDrawFoldGutterBind, handle, enable)
    }

    /**
     * If `true`, the fold gutter is drawn. In this gutter, the `can_fold_code_region` icon is drawn
     * for each foldable line (see `can_fold_line`) and the `folded_code_region` icon is drawn for each
     * folded line (see `is_line_folded`). These icons can be clicked to toggle the fold state, see
     * `toggle_foldable_line`. `line_folding` must be `true` to show icons.
     *
     * Generated from Godot docs: CodeEdit.is_drawing_fold_gutter
     */
    fun isDrawingFoldGutter(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDrawingFoldGutterBind, handle)
    }

    /**
     * If `true`, lines can be folded. Otherwise, line folding methods like `fold_line` will not work
     * and `can_fold_line` will always return `false`. See `gutters_draw_fold_gutter`.
     *
     * Generated from Godot docs: CodeEdit.set_line_folding_enabled
     */
    fun setLineFoldingEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLineFoldingEnabledBind, handle, enabled)
    }

    /**
     * If `true`, lines can be folded. Otherwise, line folding methods like `fold_line` will not work
     * and `can_fold_line` will always return `false`. See `gutters_draw_fold_gutter`.
     *
     * Generated from Godot docs: CodeEdit.is_line_folding_enabled
     */
    fun isLineFoldingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLineFoldingEnabledBind, handle)
    }

    /**
     * Returns `true` if the given line is foldable. A line is foldable if it is the start of a valid
     * code region (see `get_code_region_start_tag`), if it is the start of a comment or string block,
     * or if the next non-empty line is more indented (see `TextEdit.get_indent_level`).
     *
     * Generated from Godot docs: CodeEdit.can_fold_line
     */
    fun canFoldLine(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(canFoldLineBind, handle, line)
    }

    /**
     * Folds the given line, if possible (see `can_fold_line`).
     *
     * Generated from Godot docs: CodeEdit.fold_line
     */
    fun foldLine(line: Int) {
        ObjectCalls.ptrcallWithIntArg(foldLineBind, handle, line)
    }

    /**
     * Unfolds the given line if it is folded or if it is hidden under a folded line.
     *
     * Generated from Godot docs: CodeEdit.unfold_line
     */
    fun unfoldLine(line: Int) {
        ObjectCalls.ptrcallWithIntArg(unfoldLineBind, handle, line)
    }

    /**
     * Folds all lines that are possible to be folded (see `can_fold_line`).
     *
     * Generated from Godot docs: CodeEdit.fold_all_lines
     */
    fun foldAllLines() {
        ObjectCalls.ptrcallNoArgs(foldAllLinesBind, handle)
    }

    /**
     * Unfolds all lines that are folded.
     *
     * Generated from Godot docs: CodeEdit.unfold_all_lines
     */
    fun unfoldAllLines() {
        ObjectCalls.ptrcallNoArgs(unfoldAllLinesBind, handle)
    }

    /**
     * Toggle the folding of the code block at the given line.
     *
     * Generated from Godot docs: CodeEdit.toggle_foldable_line
     */
    fun toggleFoldableLine(line: Int) {
        ObjectCalls.ptrcallWithIntArg(toggleFoldableLineBind, handle, line)
    }

    /**
     * Toggle the folding of the code block on all lines with a caret on them.
     *
     * Generated from Godot docs: CodeEdit.toggle_foldable_lines_at_carets
     */
    fun toggleFoldableLinesAtCarets() {
        ObjectCalls.ptrcallNoArgs(toggleFoldableLinesAtCaretsBind, handle)
    }

    /**
     * Returns `true` if the given line is folded. See `fold_line`.
     *
     * Generated from Godot docs: CodeEdit.is_line_folded
     */
    fun isLineFolded(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineFoldedBind, handle, line)
    }

    /**
     * Returns all lines that are currently folded.
     *
     * Generated from Godot docs: CodeEdit.get_folded_lines
     */
    fun getFoldedLines(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetLongList(getFoldedLinesBind, handle)
    }

    /**
     * Creates a new code region with the selection. At least one single line comment delimiter have to
     * be defined (see `add_comment_delimiter`). A code region is a part of code that is highlighted
     * when folded and can help organize your script. Code region start and end tags can be customized
     * (see `set_code_region_tags`). Code regions are delimited using start and end tags (respectively
     * `region` and `endregion` by default) preceded by one line comment delimiter. (eg. `#region` and
     * `#endregion`)
     *
     * Generated from Godot docs: CodeEdit.create_code_region
     */
    fun createCodeRegion() {
        ObjectCalls.ptrcallNoArgs(createCodeRegionBind, handle)
    }

    /**
     * Returns the code region start tag (without comment delimiter).
     *
     * Generated from Godot docs: CodeEdit.get_code_region_start_tag
     */
    fun getCodeRegionStartTag(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCodeRegionStartTagBind, handle)
    }

    /**
     * Returns the code region end tag (without comment delimiter).
     *
     * Generated from Godot docs: CodeEdit.get_code_region_end_tag
     */
    fun getCodeRegionEndTag(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCodeRegionEndTagBind, handle)
    }

    /**
     * Sets the code region start and end tags (without comment delimiter).
     *
     * Generated from Godot docs: CodeEdit.set_code_region_tags
     */
    fun setCodeRegionTags(start: String = "region", end: String = "endregion") {
        ObjectCalls.ptrcallWithTwoStringArgs(setCodeRegionTagsBind, handle, start, end)
    }

    /**
     * Returns `true` if the given line is a code region start. See `set_code_region_tags`.
     *
     * Generated from Godot docs: CodeEdit.is_line_code_region_start
     */
    fun isLineCodeRegionStart(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineCodeRegionStartBind, handle, line)
    }

    /**
     * Returns `true` if the given line is a code region end. See `set_code_region_tags`.
     *
     * Generated from Godot docs: CodeEdit.is_line_code_region_end
     */
    fun isLineCodeRegionEnd(line: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(isLineCodeRegionEndBind, handle, line)
    }

    /**
     * Defines a string delimiter from `start_key` to `end_key`. Both keys should be symbols, and
     * `start_key` must not be shared with other delimiters. If `line_only` is `true` or `end_key` is
     * an empty `String`, the region does not carry over to the next line.
     *
     * Generated from Godot docs: CodeEdit.add_string_delimiter
     */
    fun addStringDelimiter(startKey: String, endKey: String, lineOnly: Boolean = false) {
        ObjectCalls.ptrcallWithTwoStringAndBoolArgs(addStringDelimiterBind, handle, startKey, endKey, lineOnly)
    }

    /**
     * Removes the string delimiter with `start_key`.
     *
     * Generated from Godot docs: CodeEdit.remove_string_delimiter
     */
    fun removeStringDelimiter(startKey: String) {
        ObjectCalls.ptrcallWithStringArg(removeStringDelimiterBind, handle, startKey)
    }

    /**
     * Returns `true` if string `start_key` exists.
     *
     * Generated from Godot docs: CodeEdit.has_string_delimiter
     */
    fun hasStringDelimiter(startKey: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasStringDelimiterBind, handle, startKey)
    }

    /**
     * Sets the string delimiters. All existing string delimiters will be removed.
     *
     * Generated from Godot docs: CodeEdit.set_string_delimiters
     */
    fun setStringDelimiters(stringDelimiters: List<String>) {
        ObjectCalls.ptrcallWithTypedStringListArg(setStringDelimitersBind, handle, stringDelimiters)
    }

    /**
     * Removes all string delimiters.
     *
     * Generated from Godot docs: CodeEdit.clear_string_delimiters
     */
    fun clearStringDelimiters() {
        ObjectCalls.ptrcallNoArgs(clearStringDelimitersBind, handle)
    }

    /**
     * Sets the string delimiters. All existing string delimiters will be removed.
     *
     * Generated from Godot docs: CodeEdit.get_string_delimiters
     */
    fun getStringDelimiters(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(getStringDelimitersBind, handle)
    }

    /**
     * Returns the delimiter index if `line` `column` is in a string. If `column` is not provided, will
     * return the delimiter index if the entire `line` is a string. Otherwise `-1`.
     *
     * Generated from Godot docs: CodeEdit.is_in_string
     */
    fun isInString(line: Int, column: Int = -1): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(isInStringBind, handle, line, column)
    }

    /**
     * Adds a comment delimiter from `start_key` to `end_key`. Both keys should be symbols, and
     * `start_key` must not be shared with other delimiters. If `line_only` is `true` or `end_key` is
     * an empty `String`, the region does not carry over to the next line.
     *
     * Generated from Godot docs: CodeEdit.add_comment_delimiter
     */
    fun addCommentDelimiter(startKey: String, endKey: String, lineOnly: Boolean = false) {
        ObjectCalls.ptrcallWithTwoStringAndBoolArgs(addCommentDelimiterBind, handle, startKey, endKey, lineOnly)
    }

    /**
     * Removes the comment delimiter with `start_key`.
     *
     * Generated from Godot docs: CodeEdit.remove_comment_delimiter
     */
    fun removeCommentDelimiter(startKey: String) {
        ObjectCalls.ptrcallWithStringArg(removeCommentDelimiterBind, handle, startKey)
    }

    /**
     * Returns `true` if comment `start_key` exists.
     *
     * Generated from Godot docs: CodeEdit.has_comment_delimiter
     */
    fun hasCommentDelimiter(startKey: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(hasCommentDelimiterBind, handle, startKey)
    }

    /**
     * Sets the comment delimiters. All existing comment delimiters will be removed.
     *
     * Generated from Godot docs: CodeEdit.set_comment_delimiters
     */
    fun setCommentDelimiters(commentDelimiters: List<String>) {
        ObjectCalls.ptrcallWithTypedStringListArg(setCommentDelimitersBind, handle, commentDelimiters)
    }

    /**
     * Removes all comment delimiters.
     *
     * Generated from Godot docs: CodeEdit.clear_comment_delimiters
     */
    fun clearCommentDelimiters() {
        ObjectCalls.ptrcallNoArgs(clearCommentDelimitersBind, handle)
    }

    /**
     * Sets the comment delimiters. All existing comment delimiters will be removed.
     *
     * Generated from Godot docs: CodeEdit.get_comment_delimiters
     */
    fun getCommentDelimiters(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(getCommentDelimitersBind, handle)
    }

    /**
     * Returns delimiter index if `line` `column` is in a comment. If `column` is not provided, will
     * return delimiter index if the entire `line` is a comment. Otherwise `-1`.
     *
     * Generated from Godot docs: CodeEdit.is_in_comment
     */
    fun isInComment(line: Int, column: Int = -1): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(isInCommentBind, handle, line, column)
    }

    /**
     * Gets the start key for a string or comment region index.
     *
     * Generated from Godot docs: CodeEdit.get_delimiter_start_key
     */
    fun getDelimiterStartKey(delimiterIndex: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getDelimiterStartKeyBind, handle, delimiterIndex)
    }

    /**
     * Gets the end key for a string or comment region index.
     *
     * Generated from Godot docs: CodeEdit.get_delimiter_end_key
     */
    fun getDelimiterEndKey(delimiterIndex: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetString(getDelimiterEndKeyBind, handle, delimiterIndex)
    }

    /**
     * If `line` `column` is in a string or comment, returns the start position of the region. If not
     * or no start could be found, both `Vector2` values will be `-1`.
     *
     * Generated from Godot docs: CodeEdit.get_delimiter_start_position
     */
    fun getDelimiterStartPosition(line: Int, column: Int): Vector2 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector2(getDelimiterStartPositionBind, handle, line, column)
    }

    /**
     * If `line` `column` is in a string or comment, returns the end position of the region. If not or
     * no end could be found, both `Vector2` values will be `-1`.
     *
     * Generated from Godot docs: CodeEdit.get_delimiter_end_position
     */
    fun getDelimiterEndPosition(line: Int, column: Int): Vector2 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector2(getDelimiterEndPositionBind, handle, line, column)
    }

    /**
     * Sets the code hint text. Pass an empty string to clear.
     *
     * Generated from Godot docs: CodeEdit.set_code_hint
     */
    fun setCodeHint(codeHint: String) {
        ObjectCalls.ptrcallWithStringArg(setCodeHintBind, handle, codeHint)
    }

    /**
     * If `true`, the code hint will draw below the main caret. If `false`, the code hint will draw
     * above the main caret. See `set_code_hint`.
     *
     * Generated from Godot docs: CodeEdit.set_code_hint_draw_below
     */
    fun setCodeHintDrawBelow(drawBelow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCodeHintDrawBelowBind, handle, drawBelow)
    }

    /**
     * Returns the full text with char `0xFFFF` at the caret location.
     *
     * Generated from Godot docs: CodeEdit.get_text_for_code_completion
     */
    fun getTextForCodeCompletion(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextForCodeCompletionBind, handle)
    }

    /**
     * Emits `code_completion_requested`, if `force` is `true` will bypass all checks. Otherwise will
     * check that the caret is in a word or in front of a prefix. Will ignore the request if all
     * current options are of type file path, node path, or signal.
     *
     * Generated from Godot docs: CodeEdit.request_code_completion
     */
    fun requestCodeCompletion(force: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(requestCodeCompletionBind, handle, force)
    }

    /**
     * Submits an item to the queue of potential candidates for the autocomplete menu. Call
     * `update_code_completion_options` to update the list. `location` indicates location of the option
     * relative to the location of the code completion query. See `CodeEdit.CodeCompletionLocation` for
     * how to set this value. Note: This list will replace all current candidates.
     *
     * Generated from Godot docs: CodeEdit.add_code_completion_option
     */
    fun addCodeCompletionOption(type: Long, displayText: String, insertText: String, textColor: Color, icon: Resource?, value: Any? = null, location: Int = 1024) {
        ObjectCalls.ptrcallWithLongTwoStringColorObjectVariantIntArgs(addCodeCompletionOptionBind, handle, type, displayText, insertText, textColor, icon?.requireOpenHandle() ?: MemorySegment.NULL, value, location)
    }

    /**
     * Submits all completion options added with `add_code_completion_option`. Will try to force the
     * autocomplete menu to popup, if `force` is `true`. Note: This will replace all current
     * candidates.
     *
     * Generated from Godot docs: CodeEdit.update_code_completion_options
     */
    fun updateCodeCompletionOptions(force: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(updateCodeCompletionOptionsBind, handle, force)
    }

    /**
     * Gets all completion options, see `get_code_completion_option` for return content.
     *
     * Generated from Godot docs: CodeEdit.get_code_completion_options
     */
    fun getCodeCompletionOptions(): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallNoArgsRetDictionaryList(getCodeCompletionOptionsBind, handle)
    }

    /**
     * Gets the completion option at `index`. The return `Dictionary` has the following key-values:
     * `kind`: `CodeCompletionKind` `display_text`: Text that is shown on the autocomplete menu.
     * `insert_text`: Text that is to be inserted when this item is selected. `font_color`: Color of
     * the text on the autocomplete menu. `icon`: Icon to draw on the autocomplete menu.
     * `default_value`: Value of the symbol.
     *
     * Generated from Godot docs: CodeEdit.get_code_completion_option
     */
    fun getCodeCompletionOption(index: Int): Map<String, Any?> {
        return ObjectCalls.ptrcallWithIntArgRetDictionary(getCodeCompletionOptionBind, handle, index)
    }

    /**
     * Gets the index of the current selected completion option.
     *
     * Generated from Godot docs: CodeEdit.get_code_completion_selected_index
     */
    fun getCodeCompletionSelectedIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCodeCompletionSelectedIndexBind, handle)
    }

    /**
     * Sets the current selected completion option.
     *
     * Generated from Godot docs: CodeEdit.set_code_completion_selected_index
     */
    fun setCodeCompletionSelectedIndex(index: Int) {
        ObjectCalls.ptrcallWithIntArg(setCodeCompletionSelectedIndexBind, handle, index)
    }

    /**
     * Inserts the selected entry into the text. If `replace` is `true`, any existing text is replaced
     * rather than merged.
     *
     * Generated from Godot docs: CodeEdit.confirm_code_completion
     */
    fun confirmCodeCompletion(replace: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(confirmCodeCompletionBind, handle, replace)
    }

    /**
     * Cancels the autocomplete menu.
     *
     * Generated from Godot docs: CodeEdit.cancel_code_completion
     */
    fun cancelCodeCompletion() {
        ObjectCalls.ptrcallNoArgs(cancelCodeCompletionBind, handle)
    }

    /**
     * If `true`, the `ProjectSettings.input/ui_text_completion_query` action requests code completion.
     * To handle it, see `_request_code_completion` or `code_completion_requested`.
     *
     * Generated from Godot docs: CodeEdit.set_code_completion_enabled
     */
    fun setCodeCompletionEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCodeCompletionEnabledBind, handle, enable)
    }

    /**
     * If `true`, the `ProjectSettings.input/ui_text_completion_query` action requests code completion.
     * To handle it, see `_request_code_completion` or `code_completion_requested`.
     *
     * Generated from Godot docs: CodeEdit.is_code_completion_enabled
     */
    fun isCodeCompletionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCodeCompletionEnabledBind, handle)
    }

    /**
     * Sets prefixes that will trigger code completion.
     *
     * Generated from Godot docs: CodeEdit.set_code_completion_prefixes
     */
    fun setCodeCompletionPrefixes(prefixes: List<String>) {
        ObjectCalls.ptrcallWithTypedStringListArg(setCodeCompletionPrefixesBind, handle, prefixes)
    }

    /**
     * Sets prefixes that will trigger code completion.
     *
     * Generated from Godot docs: CodeEdit.get_code_completion_prefixes
     */
    fun getCodeCompletionPrefixes(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetTypedStringList(getCodeCompletionPrefixesBind, handle)
    }

    /**
     * Draws vertical lines at the provided columns. The first entry is considered a main hard
     * guideline and is drawn more prominently.
     *
     * Generated from Godot docs: CodeEdit.set_line_length_guidelines
     */
    fun setLineLengthGuidelines(guidelineColumns: List<Long>) {
        ObjectCalls.ptrcallWithTypedIntListArg(setLineLengthGuidelinesBind, handle, guidelineColumns)
    }

    /**
     * Draws vertical lines at the provided columns. The first entry is considered a main hard
     * guideline and is drawn more prominently.
     *
     * Generated from Godot docs: CodeEdit.get_line_length_guidelines
     */
    fun getLineLengthGuidelines(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetLongList(getLineLengthGuidelinesBind, handle)
    }

    /**
     * Set when a validated word from `symbol_validate` is clicked, the `symbol_lookup` should be
     * emitted.
     *
     * Generated from Godot docs: CodeEdit.set_symbol_lookup_on_click_enabled
     */
    fun setSymbolLookupOnClickEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSymbolLookupOnClickEnabledBind, handle, enable)
    }

    /**
     * Set when a validated word from `symbol_validate` is clicked, the `symbol_lookup` should be
     * emitted.
     *
     * Generated from Godot docs: CodeEdit.is_symbol_lookup_on_click_enabled
     */
    fun isSymbolLookupOnClickEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSymbolLookupOnClickEnabledBind, handle)
    }

    /**
     * Returns the full text with char `0xFFFF` at the cursor location.
     *
     * Generated from Godot docs: CodeEdit.get_text_for_symbol_lookup
     */
    fun getTextForSymbolLookup(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextForSymbolLookupBind, handle)
    }

    /**
     * Returns the full text with char `0xFFFF` at the specified location.
     *
     * Generated from Godot docs: CodeEdit.get_text_with_cursor_char
     */
    fun getTextWithCursorChar(line: Int, column: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetString(getTextWithCursorCharBind, handle, line, column)
    }

    /**
     * Sets the symbol emitted by `symbol_validate` as a valid lookup.
     *
     * Generated from Godot docs: CodeEdit.set_symbol_lookup_word_as_valid
     */
    fun setSymbolLookupWordAsValid(valid: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSymbolLookupWordAsValidBind, handle, valid)
    }

    /**
     * If `true`, the `symbol_hovered` signal is emitted when hovering over a word.
     *
     * Generated from Godot docs: CodeEdit.set_symbol_tooltip_on_hover_enabled
     */
    fun setSymbolTooltipOnHoverEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSymbolTooltipOnHoverEnabledBind, handle, enable)
    }

    /**
     * If `true`, the `symbol_hovered` signal is emitted when hovering over a word.
     *
     * Generated from Godot docs: CodeEdit.is_symbol_tooltip_on_hover_enabled
     */
    fun isSymbolTooltipOnHoverEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSymbolTooltipOnHoverEnabledBind, handle)
    }

    /**
     * Moves all lines up that are selected or have a caret on them.
     *
     * Generated from Godot docs: CodeEdit.move_lines_up
     */
    fun moveLinesUp() {
        ObjectCalls.ptrcallNoArgs(moveLinesUpBind, handle)
    }

    /**
     * Moves all lines down that are selected or have a caret on them.
     *
     * Generated from Godot docs: CodeEdit.move_lines_down
     */
    fun moveLinesDown() {
        ObjectCalls.ptrcallNoArgs(moveLinesDownBind, handle)
    }

    /**
     * Deletes all lines that are selected or have a caret on them.
     *
     * Generated from Godot docs: CodeEdit.delete_lines
     */
    fun deleteLines() {
        ObjectCalls.ptrcallNoArgs(deleteLinesBind, handle)
    }

    /**
     * Joins all selected lines or lines containing a caret with their next line. Whitespace in between
     * will be removed. If the next line has content, the `line_ending` will be inserted in between.
     *
     * Generated from Godot docs: CodeEdit.join_lines
     */
    fun joinLines(lineEnding: String = " ") {
        ObjectCalls.ptrcallWithStringArg(joinLinesBind, handle, lineEnding)
    }

    /**
     * Duplicates all selected text and duplicates all lines with a caret on them.
     *
     * Generated from Godot docs: CodeEdit.duplicate_selection
     */
    fun duplicateSelection() {
        ObjectCalls.ptrcallNoArgs(duplicateSelectionBind, handle)
    }

    /**
     * Duplicates all lines currently selected with any caret. Duplicates the entire line beneath the
     * current one no matter where the caret is within the line.
     *
     * Generated from Godot docs: CodeEdit.duplicate_lines
     */
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
