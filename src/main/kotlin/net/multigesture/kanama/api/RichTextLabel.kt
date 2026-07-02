package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * A control for displaying text that can contain different font styles, images, and basic
 * formatting.
 *
 * Generated from Godot docs: RichTextLabel
 */
class RichTextLabel(handle: MemorySegment) : Control(handle) {
    var bbcodeEnabled: Boolean
        @JvmName("bbcodeEnabledProperty")
        get() = isUsingBbcode()
        @JvmName("setBbcodeEnabledProperty")
        set(value) = setUseBbcode(value)

    var text: String
        @JvmName("textProperty")
        get() = getText()
        @JvmName("setTextProperty")
        set(value) = setText(value)

    var fitContent: Boolean
        @JvmName("fitContentProperty")
        get() = isFitContentEnabled()
        @JvmName("setFitContentProperty")
        set(value) = setFitContent(value)

    var scrollActive: Boolean
        @JvmName("scrollActiveProperty")
        get() = isScrollActive()
        @JvmName("setScrollActiveProperty")
        set(value) = setScrollActive(value)

    var scrollFollowing: Boolean
        @JvmName("scrollFollowingProperty")
        get() = isScrollFollowing()
        @JvmName("setScrollFollowingProperty")
        set(value) = setScrollFollow(value)

    var scrollFollowingVisibleCharacters: Boolean
        @JvmName("scrollFollowingVisibleCharactersProperty")
        get() = isScrollFollowingVisibleCharacters()
        @JvmName("setScrollFollowingVisibleCharactersProperty")
        set(value) = setScrollFollowVisibleCharacters(value)

    var autowrapMode: Long
        @JvmName("autowrapModeProperty")
        get() = getAutowrapMode()
        @JvmName("setAutowrapModeProperty")
        set(value) = setAutowrapMode(value)

    var autowrapTrimFlags: Long
        @JvmName("autowrapTrimFlagsProperty")
        get() = getAutowrapTrimFlags()
        @JvmName("setAutowrapTrimFlagsProperty")
        set(value) = setAutowrapTrimFlags(value)

    var tabSize: Int
        @JvmName("tabSizeProperty")
        get() = getTabSize()
        @JvmName("setTabSizeProperty")
        set(value) = setTabSize(value)

    var contextMenuEnabled: Boolean
        @JvmName("contextMenuEnabledProperty")
        get() = isContextMenuEnabled()
        @JvmName("setContextMenuEnabledProperty")
        set(value) = setContextMenuEnabled(value)

    var shortcutKeysEnabled: Boolean
        @JvmName("shortcutKeysEnabledProperty")
        get() = isShortcutKeysEnabled()
        @JvmName("setShortcutKeysEnabledProperty")
        set(value) = setShortcutKeysEnabled(value)

    var horizontalAlignment: Long
        @JvmName("horizontalAlignmentProperty")
        get() = getHorizontalAlignment()
        @JvmName("setHorizontalAlignmentProperty")
        set(value) = setHorizontalAlignment(value)

    var verticalAlignment: Long
        @JvmName("verticalAlignmentProperty")
        get() = getVerticalAlignment()
        @JvmName("setVerticalAlignmentProperty")
        set(value) = setVerticalAlignment(value)

    var justificationFlags: Long
        @JvmName("justificationFlagsProperty")
        get() = getJustificationFlags()
        @JvmName("setJustificationFlagsProperty")
        set(value) = setJustificationFlags(value)

    var tabStops: List<Float>
        @JvmName("tabStopsProperty")
        get() = getTabStops()
        @JvmName("setTabStopsProperty")
        set(value) = setTabStops(value)

    var customEffects: List<Any?>
        @JvmName("customEffectsProperty")
        get() = getEffects()
        @JvmName("setCustomEffectsProperty")
        set(value) = setEffects(value)

    var metaUnderlined: Boolean
        @JvmName("metaUnderlinedProperty")
        get() = isMetaUnderlined()
        @JvmName("setMetaUnderlinedProperty")
        set(value) = setMetaUnderline(value)

    var hintUnderlined: Boolean
        @JvmName("hintUnderlinedProperty")
        get() = isHintUnderlined()
        @JvmName("setHintUnderlinedProperty")
        set(value) = setHintUnderline(value)

    var threaded: Boolean
        @JvmName("threadedProperty")
        get() = isThreaded()
        @JvmName("setThreadedProperty")
        set(value) = setThreaded(value)

    var progressBarDelay: Int
        @JvmName("progressBarDelayProperty")
        get() = getProgressBarDelay()
        @JvmName("setProgressBarDelayProperty")
        set(value) = setProgressBarDelay(value)

    var selectionEnabled: Boolean
        @JvmName("selectionEnabledProperty")
        get() = isSelectionEnabled()
        @JvmName("setSelectionEnabledProperty")
        set(value) = setSelectionEnabled(value)

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

    var visibleCharacters: Int
        @JvmName("visibleCharactersProperty")
        get() = getVisibleCharacters()
        @JvmName("setVisibleCharactersProperty")
        set(value) = setVisibleCharacters(value)

    var visibleCharactersBehavior: Long
        @JvmName("visibleCharactersBehaviorProperty")
        get() = getVisibleCharactersBehavior()
        @JvmName("setVisibleCharactersBehaviorProperty")
        set(value) = setVisibleCharactersBehavior(value)

    var visibleRatio: Double
        @JvmName("visibleRatioProperty")
        get() = getVisibleRatio()
        @JvmName("setVisibleRatioProperty")
        set(value) = setVisibleRatio(value)

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

    fun getParsedText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getParsedTextBind, handle)
    }

    fun addText(text: String) {
        ObjectCalls.ptrcallWithStringArg(addTextBind, handle, text)
    }

    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, handle, text)
    }

    fun addHr(width: Int = 90, height: Int = 2, color: Color, alignment: Long = 1L, widthInPercent: Boolean = true, heightInPercent: Boolean = false) {
        ObjectCalls.ptrcallWithTwoIntColorLongTwoBoolArgs(addHrBind, handle, width, height, color, alignment, widthInPercent, heightInPercent)
    }

    fun addImage(image: Texture2D?, width: Double = 0.0, height: Double = 0.0, color: Color, inlineAlign: Long = 5L, region: Rect2, key: Any? = null, pad: Boolean = false, tooltip: String = "", widthUnit: Long = 0L, heightUnit: Long = 0L, altText: String = "") {
        ObjectCalls.ptrcallWithObjectTwoDoubleColorLongRect2VariantBoolStringTwoLongStringArgs(addImageBind, handle, image?.requireOpenHandle() ?: MemorySegment.NULL, width, height, color, inlineAlign, region, key, pad, tooltip, widthUnit, heightUnit, altText)
    }

    fun updateImage(key: Any?, mask: Long, image: Texture2D?, width: Double = 0.0, height: Double = 0.0, color: Color, inlineAlign: Long = 5L, region: Rect2, pad: Boolean = false, tooltip: String = "", widthUnit: Long = 0L, heightUnit: Long = 0L) {
        ObjectCalls.ptrcallWithVariantLongObjectTwoDoubleColorLongRect2BoolStringTwoLongArgs(updateImageBind, handle, key, mask, image?.requireOpenHandle() ?: MemorySegment.NULL, width, height, color, inlineAlign, region, pad, tooltip, widthUnit, heightUnit)
    }

    /**
     * Adds a newline tag to the tag stack.
     *
     * Generated from Godot docs: RichTextLabel.newline
     */
    fun newline() {
        ObjectCalls.ptrcallNoArgs(newlineBind, handle)
    }

    fun removeParagraph(paragraph: Int, noInvalidate: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithIntAndBoolArgsRetBool(removeParagraphBind, handle, paragraph, noInvalidate)
    }

    fun invalidateParagraph(paragraph: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(invalidateParagraphBind, handle, paragraph)
    }

    fun pushFont(font: Font?, fontSize: Int = 0) {
        ObjectCalls.ptrcallWithObjectAndIntArg(pushFontBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, fontSize)
    }

    fun pushFontSize(fontSize: Int) {
        ObjectCalls.ptrcallWithIntArg(pushFontSizeBind, handle, fontSize)
    }

    fun pushNormal() {
        ObjectCalls.ptrcallNoArgs(pushNormalBind, handle)
    }

    fun pushBold() {
        ObjectCalls.ptrcallNoArgs(pushBoldBind, handle)
    }

    fun pushBoldItalics() {
        ObjectCalls.ptrcallNoArgs(pushBoldItalicsBind, handle)
    }

    fun pushItalics() {
        ObjectCalls.ptrcallNoArgs(pushItalicsBind, handle)
    }

    fun pushMono() {
        ObjectCalls.ptrcallNoArgs(pushMonoBind, handle)
    }

    fun pushColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(pushColorBind, handle, color)
    }

    fun pushOutlineSize(outlineSize: Int) {
        ObjectCalls.ptrcallWithIntArg(pushOutlineSizeBind, handle, outlineSize)
    }

    fun pushOutlineColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(pushOutlineColorBind, handle, color)
    }

    fun pushParagraph(alignment: Long, baseDirection: Long = 0L, language: String = "", stParser: Long = 0L, justificationFlags: Long = 163L, tabStops: List<Float>) {
        ObjectCalls.ptrcallWithTwoLongStringTwoLongPackedFloat32ListArgs(pushParagraphBind, handle, alignment, baseDirection, language, stParser, justificationFlags, tabStops)
    }

    fun pushIndent(level: Int) {
        ObjectCalls.ptrcallWithIntArg(pushIndentBind, handle, level)
    }

    fun pushList(level: Int, type: Long, capitalize: Boolean, bullet: String = "•") {
        ObjectCalls.ptrcallWithIntLongBoolStringArgs(pushListBind, handle, level, type, capitalize, bullet)
    }

    fun pushMeta(data: Any?, underlineMode: Long = 1L, tooltip: String = "") {
        ObjectCalls.ptrcallWithVariantLongStringArgs(pushMetaBind, handle, data, underlineMode, tooltip)
    }

    fun pushHint(description: String) {
        ObjectCalls.ptrcallWithStringArg(pushHintBind, handle, description)
    }

    fun pushLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(pushLanguageBind, handle, language)
    }

    fun pushUnderline(color: Color) {
        ObjectCalls.ptrcallWithColorArg(pushUnderlineBind, handle, color)
    }

    fun pushStrikethrough(color: Color) {
        ObjectCalls.ptrcallWithColorArg(pushStrikethroughBind, handle, color)
    }

    fun pushTable(columns: Int, inlineAlign: Long = 0L, alignToRow: Int = -1, name: String = "") {
        ObjectCalls.ptrcallWithIntLongIntStringArgs(pushTableBind, handle, columns, inlineAlign, alignToRow, name)
    }

    fun pushDropcap(string: String, font: Font?, size: Int, dropcapMargins: Rect2, color: Color, outlineSize: Int = 0, outlineColor: Color) {
        ObjectCalls.ptrcallWithStringObjectIntRect2ColorIntColorArgs(pushDropcapBind, handle, string, font?.requireOpenHandle() ?: MemorySegment.NULL, size, dropcapMargins, color, outlineSize, outlineColor)
    }

    fun setTableColumnExpand(column: Int, expand: Boolean, ratio: Int = 1, shrink: Boolean = true) {
        ObjectCalls.ptrcallWithIntBoolIntBoolArgs(setTableColumnExpandBind, handle, column, expand, ratio, shrink)
    }

    fun setTableColumnName(column: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setTableColumnNameBind, handle, column, name)
    }

    fun setCellRowBackgroundColor(oddRowBg: Color, evenRowBg: Color) {
        ObjectCalls.ptrcallWithTwoColorArgs(setCellRowBackgroundColorBind, handle, oddRowBg, evenRowBg)
    }

    fun setCellBorderColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setCellBorderColorBind, handle, color)
    }

    fun setCellSizeOverride(minSize: Vector2, maxSize: Vector2) {
        ObjectCalls.ptrcallWithTwoVector2Args(setCellSizeOverrideBind, handle, minSize, maxSize)
    }

    fun setCellPadding(padding: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(setCellPaddingBind, handle, padding)
    }

    fun pushCell() {
        ObjectCalls.ptrcallNoArgs(pushCellBind, handle)
    }

    fun pushFgcolor(fgcolor: Color) {
        ObjectCalls.ptrcallWithColorArg(pushFgcolorBind, handle, fgcolor)
    }

    fun pushBgcolor(bgcolor: Color) {
        ObjectCalls.ptrcallWithColorArg(pushBgcolorBind, handle, bgcolor)
    }

    fun pushCustomfx(effect: RichTextEffect?, env: Map<String, Any?>) {
        ObjectCalls.ptrcallWithObjectAndDictionaryArg(pushCustomfxBind, handle, effect?.requireOpenHandle() ?: MemorySegment.NULL, env)
    }

    fun pushContext() {
        ObjectCalls.ptrcallNoArgs(pushContextBind, handle)
    }

    fun popContext() {
        ObjectCalls.ptrcallNoArgs(popContextBind, handle)
    }

    /**
     * Terminates the current tag. Use after `push_*` methods to close BBCodes manually. Does not need
     * to follow `add_*` methods.
     *
     * Generated from Godot docs: RichTextLabel.pop
     */
    fun pop() {
        ObjectCalls.ptrcallNoArgs(popBind, handle)
    }

    fun popAll() {
        ObjectCalls.ptrcallNoArgs(popAllBind, handle)
    }

    /**
     * Clears the tag stack, causing the label to display nothing. Note: This method does not affect
     * `text`, and its contents will show again if the label is redrawn. However, setting `text` to an
     * empty `String` also clears the stack.
     *
     * Generated from Godot docs: RichTextLabel.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
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

    fun setHorizontalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setHorizontalAlignmentBind, handle, alignment)
    }

    fun getHorizontalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHorizontalAlignmentBind, handle)
    }

    fun setVerticalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setVerticalAlignmentBind, handle, alignment)
    }

    fun getVerticalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVerticalAlignmentBind, handle)
    }

    fun setJustificationFlags(justificationFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setJustificationFlagsBind, handle, justificationFlags)
    }

    fun getJustificationFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getJustificationFlagsBind, handle)
    }

    fun setTabStops(tabStops: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setTabStopsBind, handle, tabStops)
    }

    fun getTabStops(): List<Float> {
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getTabStopsBind, handle)
    }

    fun setAutowrapMode(autowrapMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapModeBind, handle, autowrapMode)
    }

    fun getAutowrapMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapModeBind, handle)
    }

    fun setAutowrapTrimFlags(autowrapTrimFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapTrimFlagsBind, handle, autowrapTrimFlags)
    }

    fun getAutowrapTrimFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapTrimFlagsBind, handle)
    }

    fun setMetaUnderline(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMetaUnderlineBind, handle, enable)
    }

    fun isMetaUnderlined(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMetaUnderlinedBind, handle)
    }

    fun setHintUnderline(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHintUnderlineBind, handle, enable)
    }

    fun isHintUnderlined(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHintUnderlinedBind, handle)
    }

    fun setScrollActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollActiveBind, handle, active)
    }

    fun isScrollActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollActiveBind, handle)
    }

    fun setScrollFollowVisibleCharacters(follow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollFollowVisibleCharactersBind, handle, follow)
    }

    fun isScrollFollowingVisibleCharacters(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollFollowingVisibleCharactersBind, handle)
    }

    fun setScrollFollow(follow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollFollowBind, handle, follow)
    }

    fun isScrollFollowing(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollFollowingBind, handle)
    }

    fun getVScrollBar(): VScrollBar? {
        return VScrollBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVScrollBarBind, handle))
    }

    fun scrollToLine(line: Int) {
        ObjectCalls.ptrcallWithIntArg(scrollToLineBind, handle, line)
    }

    fun scrollToParagraph(paragraph: Int) {
        ObjectCalls.ptrcallWithIntArg(scrollToParagraphBind, handle, paragraph)
    }

    fun scrollToSelection() {
        ObjectCalls.ptrcallNoArgs(scrollToSelectionBind, handle)
    }

    fun setTabSize(spaces: Int) {
        ObjectCalls.ptrcallWithIntArg(setTabSizeBind, handle, spaces)
    }

    fun getTabSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTabSizeBind, handle)
    }

    fun setFitContent(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFitContentBind, handle, enabled)
    }

    fun isFitContentEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFitContentEnabledBind, handle)
    }

    fun setSelectionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSelectionEnabledBind, handle, enabled)
    }

    fun isSelectionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSelectionEnabledBind, handle)
    }

    fun setContextMenuEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setContextMenuEnabledBind, handle, enabled)
    }

    fun isContextMenuEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isContextMenuEnabledBind, handle)
    }

    fun setShortcutKeysEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShortcutKeysEnabledBind, handle, enabled)
    }

    fun isShortcutKeysEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShortcutKeysEnabledBind, handle)
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

    fun getSelectionFrom(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSelectionFromBind, handle)
    }

    fun getSelectionTo(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSelectionToBind, handle)
    }

    fun getSelectionLineOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSelectionLineOffsetBind, handle)
    }

    fun selectAll() {
        ObjectCalls.ptrcallNoArgs(selectAllBind, handle)
    }

    fun getSelectedText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSelectedTextBind, handle)
    }

    /**
     * Clears the current selection.
     *
     * Generated from Godot docs: RichTextLabel.deselect
     */
    fun deselect() {
        ObjectCalls.ptrcallNoArgs(deselectBind, handle)
    }

    fun parseBbcode(bbcode: String) {
        ObjectCalls.ptrcallWithStringArg(parseBbcodeBind, handle, bbcode)
    }

    fun appendText(bbcode: String) {
        ObjectCalls.ptrcallWithStringArg(appendTextBind, handle, bbcode)
    }

    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextBind, handle)
    }

    fun isReady(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isReadyBind, handle)
    }

    fun isFinished(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFinishedBind, handle)
    }

    fun setThreaded(threaded: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setThreadedBind, handle, threaded)
    }

    fun isThreaded(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isThreadedBind, handle)
    }

    fun setProgressBarDelay(delayMs: Int) {
        ObjectCalls.ptrcallWithIntArg(setProgressBarDelayBind, handle, delayMs)
    }

    fun getProgressBarDelay(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getProgressBarDelayBind, handle)
    }

    fun setVisibleCharacters(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setVisibleCharactersBind, handle, amount)
    }

    fun getVisibleCharacters(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVisibleCharactersBind, handle)
    }

    fun getVisibleCharactersBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVisibleCharactersBehaviorBind, handle)
    }

    fun setVisibleCharactersBehavior(behavior: Long) {
        ObjectCalls.ptrcallWithLongArg(setVisibleCharactersBehaviorBind, handle, behavior)
    }

    fun setVisibleRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibleRatioBind, handle, ratio)
    }

    fun getVisibleRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibleRatioBind, handle)
    }

    fun getCharacterLine(character: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getCharacterLineBind, handle, character)
    }

    fun getCharacterParagraph(character: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getCharacterParagraphBind, handle, character)
    }

    fun getTotalCharacterCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTotalCharacterCountBind, handle)
    }

    fun setUseBbcode(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseBbcodeBind, handle, enable)
    }

    fun isUsingBbcode(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingBbcodeBind, handle)
    }

    fun getLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLineCountBind, handle)
    }

    fun getLineRange(line: Int): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(getLineRangeBind, handle, line)
    }

    fun getVisibleLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVisibleLineCountBind, handle)
    }

    fun getParagraphCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getParagraphCountBind, handle)
    }

    fun getVisibleParagraphCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVisibleParagraphCountBind, handle)
    }

    fun getContentHeight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getContentHeightBind, handle)
    }

    fun getContentWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getContentWidthBind, handle)
    }

    fun getLineHeight(line: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getLineHeightBind, handle, line)
    }

    fun getLineWidth(line: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getLineWidthBind, handle, line)
    }

    fun getVisibleContentRect(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(getVisibleContentRectBind, handle)
    }

    fun getLineOffset(line: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getLineOffsetBind, handle, line)
    }

    fun getParagraphOffset(paragraph: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getParagraphOffsetBind, handle, paragraph)
    }

    fun parseExpressionsForValues(expressions: List<String>): Map<String, Any?> {
        return ObjectCalls.ptrcallWithPackedStringListArgRetDictionary(parseExpressionsForValuesBind, handle, expressions)
    }

    fun setEffects(effects: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setEffectsBind, handle, effects)
    }

    fun getEffects(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getEffectsBind, handle)
    }

    fun installEffect(effect: Any?) {
        ObjectCalls.ptrcallWithVariantArg(installEffectBind, handle, effect)
    }

    fun reloadEffects() {
        ObjectCalls.ptrcallNoArgs(reloadEffectsBind, handle)
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

    object Signals {
        const val metaClicked: String = "meta_clicked"
        const val metaHoverStarted: String = "meta_hover_started"
        const val metaHoverEnded: String = "meta_hover_ended"
        const val finished: String = "finished"
    }

    companion object {
        const val LIST_NUMBERS: Long = 0L
        const val LIST_LETTERS: Long = 1L
        const val LIST_ROMAN: Long = 2L
        const val LIST_DOTS: Long = 3L
        const val MENU_COPY: Long = 0L
        const val MENU_SELECT_ALL: Long = 1L
        const val MENU_MAX: Long = 2L
        const val META_UNDERLINE_NEVER: Long = 0L
        const val META_UNDERLINE_ALWAYS: Long = 1L
        const val META_UNDERLINE_ON_HOVER: Long = 2L
        const val UPDATE_TEXTURE: Long = 1L
        const val UPDATE_SIZE: Long = 2L
        const val UPDATE_COLOR: Long = 4L
        const val UPDATE_ALIGNMENT: Long = 8L
        const val UPDATE_REGION: Long = 16L
        const val UPDATE_PAD: Long = 32L
        const val UPDATE_TOOLTIP: Long = 64L
        const val UPDATE_WIDTH_UNIT: Long = 128L
        const val IMAGE_UNIT_PIXEL: Long = 0L
        const val IMAGE_UNIT_PERCENT: Long = 1L
        const val IMAGE_UNIT_EM: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): RichTextLabel? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RichTextLabel? =
            if (handle.address() == 0L) null else RichTextLabel(handle)

        private const val GET_PARSED_TEXT_HASH = 201670096L
        private val getParsedTextBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_parsed_text", GET_PARSED_TEXT_HASH)
        }

        private const val ADD_TEXT_HASH = 83702148L
        private val addTextBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "add_text", ADD_TEXT_HASH)
        }

        private const val SET_TEXT_HASH = 83702148L
        private val setTextBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_text", SET_TEXT_HASH)
        }

        private const val ADD_HR_HASH = 16816895L
        private val addHrBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "add_hr", ADD_HR_HASH)
        }

        private const val ADD_IMAGE_HASH = 1980227702L
        private val addImageBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "add_image", ADD_IMAGE_HASH)
        }

        private const val UPDATE_IMAGE_HASH = 202998225L
        private val updateImageBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "update_image", UPDATE_IMAGE_HASH)
        }

        private const val NEWLINE_HASH = 3218959716L
        private val newlineBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "newline", NEWLINE_HASH)
        }

        private const val REMOVE_PARAGRAPH_HASH = 3262369265L
        private val removeParagraphBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "remove_paragraph", REMOVE_PARAGRAPH_HASH)
        }

        private const val INVALIDATE_PARAGRAPH_HASH = 3067735520L
        private val invalidateParagraphBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "invalidate_paragraph", INVALIDATE_PARAGRAPH_HASH)
        }

        private const val PUSH_FONT_HASH = 2347424842L
        private val pushFontBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_font", PUSH_FONT_HASH)
        }

        private const val PUSH_FONT_SIZE_HASH = 1286410249L
        private val pushFontSizeBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_font_size", PUSH_FONT_SIZE_HASH)
        }

        private const val PUSH_NORMAL_HASH = 3218959716L
        private val pushNormalBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_normal", PUSH_NORMAL_HASH)
        }

        private const val PUSH_BOLD_HASH = 3218959716L
        private val pushBoldBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_bold", PUSH_BOLD_HASH)
        }

        private const val PUSH_BOLD_ITALICS_HASH = 3218959716L
        private val pushBoldItalicsBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_bold_italics", PUSH_BOLD_ITALICS_HASH)
        }

        private const val PUSH_ITALICS_HASH = 3218959716L
        private val pushItalicsBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_italics", PUSH_ITALICS_HASH)
        }

        private const val PUSH_MONO_HASH = 3218959716L
        private val pushMonoBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_mono", PUSH_MONO_HASH)
        }

        private const val PUSH_COLOR_HASH = 2920490490L
        private val pushColorBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_color", PUSH_COLOR_HASH)
        }

        private const val PUSH_OUTLINE_SIZE_HASH = 1286410249L
        private val pushOutlineSizeBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_outline_size", PUSH_OUTLINE_SIZE_HASH)
        }

        private const val PUSH_OUTLINE_COLOR_HASH = 2920490490L
        private val pushOutlineColorBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_outline_color", PUSH_OUTLINE_COLOR_HASH)
        }

        private const val PUSH_PARAGRAPH_HASH = 3089306873L
        private val pushParagraphBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_paragraph", PUSH_PARAGRAPH_HASH)
        }

        private const val PUSH_INDENT_HASH = 1286410249L
        private val pushIndentBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_indent", PUSH_INDENT_HASH)
        }

        private const val PUSH_LIST_HASH = 3017143144L
        private val pushListBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_list", PUSH_LIST_HASH)
        }

        private const val PUSH_META_HASH = 3765356747L
        private val pushMetaBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_meta", PUSH_META_HASH)
        }

        private const val PUSH_HINT_HASH = 83702148L
        private val pushHintBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_hint", PUSH_HINT_HASH)
        }

        private const val PUSH_LANGUAGE_HASH = 83702148L
        private val pushLanguageBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_language", PUSH_LANGUAGE_HASH)
        }

        private const val PUSH_UNDERLINE_HASH = 1458098034L
        private val pushUnderlineBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_underline", PUSH_UNDERLINE_HASH)
        }

        private const val PUSH_STRIKETHROUGH_HASH = 1458098034L
        private val pushStrikethroughBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_strikethrough", PUSH_STRIKETHROUGH_HASH)
        }

        private const val PUSH_TABLE_HASH = 3426862026L
        private val pushTableBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_table", PUSH_TABLE_HASH)
        }

        private const val PUSH_DROPCAP_HASH = 4061635501L
        private val pushDropcapBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_dropcap", PUSH_DROPCAP_HASH)
        }

        private const val SET_TABLE_COLUMN_EXPAND_HASH = 117236061L
        private val setTableColumnExpandBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_table_column_expand", SET_TABLE_COLUMN_EXPAND_HASH)
        }

        private const val SET_TABLE_COLUMN_NAME_HASH = 501894301L
        private val setTableColumnNameBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_table_column_name", SET_TABLE_COLUMN_NAME_HASH)
        }

        private const val SET_CELL_ROW_BACKGROUND_COLOR_HASH = 3465483165L
        private val setCellRowBackgroundColorBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_cell_row_background_color", SET_CELL_ROW_BACKGROUND_COLOR_HASH)
        }

        private const val SET_CELL_BORDER_COLOR_HASH = 2920490490L
        private val setCellBorderColorBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_cell_border_color", SET_CELL_BORDER_COLOR_HASH)
        }

        private const val SET_CELL_SIZE_OVERRIDE_HASH = 3108078480L
        private val setCellSizeOverrideBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_cell_size_override", SET_CELL_SIZE_OVERRIDE_HASH)
        }

        private const val SET_CELL_PADDING_HASH = 2046264180L
        private val setCellPaddingBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_cell_padding", SET_CELL_PADDING_HASH)
        }

        private const val PUSH_CELL_HASH = 3218959716L
        private val pushCellBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_cell", PUSH_CELL_HASH)
        }

        private const val PUSH_FGCOLOR_HASH = 2920490490L
        private val pushFgcolorBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_fgcolor", PUSH_FGCOLOR_HASH)
        }

        private const val PUSH_BGCOLOR_HASH = 2920490490L
        private val pushBgcolorBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_bgcolor", PUSH_BGCOLOR_HASH)
        }

        private const val PUSH_CUSTOMFX_HASH = 2337942958L
        private val pushCustomfxBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_customfx", PUSH_CUSTOMFX_HASH)
        }

        private const val PUSH_CONTEXT_HASH = 3218959716L
        private val pushContextBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "push_context", PUSH_CONTEXT_HASH)
        }

        private const val POP_CONTEXT_HASH = 3218959716L
        private val popContextBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "pop_context", POP_CONTEXT_HASH)
        }

        private const val POP_HASH = 3218959716L
        private val popBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "pop", POP_HASH)
        }

        private const val POP_ALL_HASH = 3218959716L
        private val popAllBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "pop_all", POP_ALL_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "clear", CLEAR_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 55961453L
        private val setStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_structured_text_bidi_override", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3385126229L
        private val getStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_structured_text_bidi_override", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
        private val setStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 3995934104L
        private val getStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val SET_TEXT_DIRECTION_HASH = 119160795L
        private val setTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_text_direction", SET_TEXT_DIRECTION_HASH)
        }

        private const val GET_TEXT_DIRECTION_HASH = 797257663L
        private val getTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_text_direction", GET_TEXT_DIRECTION_HASH)
        }

        private const val SET_LANGUAGE_HASH = 83702148L
        private val setLanguageBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_language", SET_LANGUAGE_HASH)
        }

        private const val GET_LANGUAGE_HASH = 201670096L
        private val getLanguageBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_language", GET_LANGUAGE_HASH)
        }

        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        private val setHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        private val getHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val SET_VERTICAL_ALIGNMENT_HASH = 1796458609L
        private val setVerticalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_vertical_alignment", SET_VERTICAL_ALIGNMENT_HASH)
        }

        private const val GET_VERTICAL_ALIGNMENT_HASH = 3274884059L
        private val getVerticalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_vertical_alignment", GET_VERTICAL_ALIGNMENT_HASH)
        }

        private const val SET_JUSTIFICATION_FLAGS_HASH = 2877345813L
        private val setJustificationFlagsBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_justification_flags", SET_JUSTIFICATION_FLAGS_HASH)
        }

        private const val GET_JUSTIFICATION_FLAGS_HASH = 1583363614L
        private val getJustificationFlagsBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_justification_flags", GET_JUSTIFICATION_FLAGS_HASH)
        }

        private const val SET_TAB_STOPS_HASH = 2899603908L
        private val setTabStopsBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_tab_stops", SET_TAB_STOPS_HASH)
        }

        private const val GET_TAB_STOPS_HASH = 675695659L
        private val getTabStopsBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_tab_stops", GET_TAB_STOPS_HASH)
        }

        private const val SET_AUTOWRAP_MODE_HASH = 3289138044L
        private val setAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_autowrap_mode", SET_AUTOWRAP_MODE_HASH)
        }

        private const val GET_AUTOWRAP_MODE_HASH = 1549071663L
        private val getAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_autowrap_mode", GET_AUTOWRAP_MODE_HASH)
        }

        private const val SET_AUTOWRAP_TRIM_FLAGS_HASH = 2809697122L
        private val setAutowrapTrimFlagsBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_autowrap_trim_flags", SET_AUTOWRAP_TRIM_FLAGS_HASH)
        }

        private const val GET_AUTOWRAP_TRIM_FLAGS_HASH = 2340632602L
        private val getAutowrapTrimFlagsBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_autowrap_trim_flags", GET_AUTOWRAP_TRIM_FLAGS_HASH)
        }

        private const val SET_META_UNDERLINE_HASH = 2586408642L
        private val setMetaUnderlineBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_meta_underline", SET_META_UNDERLINE_HASH)
        }

        private const val IS_META_UNDERLINED_HASH = 36873697L
        private val isMetaUnderlinedBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_meta_underlined", IS_META_UNDERLINED_HASH)
        }

        private const val SET_HINT_UNDERLINE_HASH = 2586408642L
        private val setHintUnderlineBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_hint_underline", SET_HINT_UNDERLINE_HASH)
        }

        private const val IS_HINT_UNDERLINED_HASH = 36873697L
        private val isHintUnderlinedBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_hint_underlined", IS_HINT_UNDERLINED_HASH)
        }

        private const val SET_SCROLL_ACTIVE_HASH = 2586408642L
        private val setScrollActiveBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_scroll_active", SET_SCROLL_ACTIVE_HASH)
        }

        private const val IS_SCROLL_ACTIVE_HASH = 36873697L
        private val isScrollActiveBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_scroll_active", IS_SCROLL_ACTIVE_HASH)
        }

        private const val SET_SCROLL_FOLLOW_VISIBLE_CHARACTERS_HASH = 2586408642L
        private val setScrollFollowVisibleCharactersBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_scroll_follow_visible_characters", SET_SCROLL_FOLLOW_VISIBLE_CHARACTERS_HASH)
        }

        private const val IS_SCROLL_FOLLOWING_VISIBLE_CHARACTERS_HASH = 36873697L
        private val isScrollFollowingVisibleCharactersBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_scroll_following_visible_characters", IS_SCROLL_FOLLOWING_VISIBLE_CHARACTERS_HASH)
        }

        private const val SET_SCROLL_FOLLOW_HASH = 2586408642L
        private val setScrollFollowBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_scroll_follow", SET_SCROLL_FOLLOW_HASH)
        }

        private const val IS_SCROLL_FOLLOWING_HASH = 36873697L
        private val isScrollFollowingBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_scroll_following", IS_SCROLL_FOLLOWING_HASH)
        }

        private const val GET_V_SCROLL_BAR_HASH = 2630340773L
        private val getVScrollBarBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_v_scroll_bar", GET_V_SCROLL_BAR_HASH)
        }

        private const val SCROLL_TO_LINE_HASH = 1286410249L
        private val scrollToLineBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "scroll_to_line", SCROLL_TO_LINE_HASH)
        }

        private const val SCROLL_TO_PARAGRAPH_HASH = 1286410249L
        private val scrollToParagraphBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "scroll_to_paragraph", SCROLL_TO_PARAGRAPH_HASH)
        }

        private const val SCROLL_TO_SELECTION_HASH = 3218959716L
        private val scrollToSelectionBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "scroll_to_selection", SCROLL_TO_SELECTION_HASH)
        }

        private const val SET_TAB_SIZE_HASH = 1286410249L
        private val setTabSizeBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_tab_size", SET_TAB_SIZE_HASH)
        }

        private const val GET_TAB_SIZE_HASH = 3905245786L
        private val getTabSizeBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_tab_size", GET_TAB_SIZE_HASH)
        }

        private const val SET_FIT_CONTENT_HASH = 2586408642L
        private val setFitContentBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_fit_content", SET_FIT_CONTENT_HASH)
        }

        private const val IS_FIT_CONTENT_ENABLED_HASH = 36873697L
        private val isFitContentEnabledBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_fit_content_enabled", IS_FIT_CONTENT_ENABLED_HASH)
        }

        private const val SET_SELECTION_ENABLED_HASH = 2586408642L
        private val setSelectionEnabledBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_selection_enabled", SET_SELECTION_ENABLED_HASH)
        }

        private const val IS_SELECTION_ENABLED_HASH = 36873697L
        private val isSelectionEnabledBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_selection_enabled", IS_SELECTION_ENABLED_HASH)
        }

        private const val SET_CONTEXT_MENU_ENABLED_HASH = 2586408642L
        private val setContextMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_context_menu_enabled", SET_CONTEXT_MENU_ENABLED_HASH)
        }

        private const val IS_CONTEXT_MENU_ENABLED_HASH = 36873697L
        private val isContextMenuEnabledBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_context_menu_enabled", IS_CONTEXT_MENU_ENABLED_HASH)
        }

        private const val SET_SHORTCUT_KEYS_ENABLED_HASH = 2586408642L
        private val setShortcutKeysEnabledBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_shortcut_keys_enabled", SET_SHORTCUT_KEYS_ENABLED_HASH)
        }

        private const val IS_SHORTCUT_KEYS_ENABLED_HASH = 36873697L
        private val isShortcutKeysEnabledBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_shortcut_keys_enabled", IS_SHORTCUT_KEYS_ENABLED_HASH)
        }

        private const val SET_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH = 2586408642L
        private val setDeselectOnFocusLossEnabledBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_deselect_on_focus_loss_enabled", SET_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH)
        }

        private const val IS_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH = 36873697L
        private val isDeselectOnFocusLossEnabledBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_deselect_on_focus_loss_enabled", IS_DESELECT_ON_FOCUS_LOSS_ENABLED_HASH)
        }

        private const val SET_DRAG_AND_DROP_SELECTION_ENABLED_HASH = 2586408642L
        private val setDragAndDropSelectionEnabledBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_drag_and_drop_selection_enabled", SET_DRAG_AND_DROP_SELECTION_ENABLED_HASH)
        }

        private const val IS_DRAG_AND_DROP_SELECTION_ENABLED_HASH = 36873697L
        private val isDragAndDropSelectionEnabledBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_drag_and_drop_selection_enabled", IS_DRAG_AND_DROP_SELECTION_ENABLED_HASH)
        }

        private const val GET_SELECTION_FROM_HASH = 3905245786L
        private val getSelectionFromBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_selection_from", GET_SELECTION_FROM_HASH)
        }

        private const val GET_SELECTION_TO_HASH = 3905245786L
        private val getSelectionToBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_selection_to", GET_SELECTION_TO_HASH)
        }

        private const val GET_SELECTION_LINE_OFFSET_HASH = 1740695150L
        private val getSelectionLineOffsetBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_selection_line_offset", GET_SELECTION_LINE_OFFSET_HASH)
        }

        private const val SELECT_ALL_HASH = 3218959716L
        private val selectAllBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "select_all", SELECT_ALL_HASH)
        }

        private const val GET_SELECTED_TEXT_HASH = 201670096L
        private val getSelectedTextBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_selected_text", GET_SELECTED_TEXT_HASH)
        }

        private const val DESELECT_HASH = 3218959716L
        private val deselectBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "deselect", DESELECT_HASH)
        }

        private const val PARSE_BBCODE_HASH = 83702148L
        private val parseBbcodeBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "parse_bbcode", PARSE_BBCODE_HASH)
        }

        private const val APPEND_TEXT_HASH = 83702148L
        private val appendTextBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "append_text", APPEND_TEXT_HASH)
        }

        private const val GET_TEXT_HASH = 201670096L
        private val getTextBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_text", GET_TEXT_HASH)
        }

        private const val IS_READY_HASH = 36873697L
        private val isReadyBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_ready", IS_READY_HASH)
        }

        private const val IS_FINISHED_HASH = 36873697L
        private val isFinishedBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_finished", IS_FINISHED_HASH)
        }

        private const val SET_THREADED_HASH = 2586408642L
        private val setThreadedBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_threaded", SET_THREADED_HASH)
        }

        private const val IS_THREADED_HASH = 36873697L
        private val isThreadedBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_threaded", IS_THREADED_HASH)
        }

        private const val SET_PROGRESS_BAR_DELAY_HASH = 1286410249L
        private val setProgressBarDelayBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_progress_bar_delay", SET_PROGRESS_BAR_DELAY_HASH)
        }

        private const val GET_PROGRESS_BAR_DELAY_HASH = 3905245786L
        private val getProgressBarDelayBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_progress_bar_delay", GET_PROGRESS_BAR_DELAY_HASH)
        }

        private const val SET_VISIBLE_CHARACTERS_HASH = 1286410249L
        private val setVisibleCharactersBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_visible_characters", SET_VISIBLE_CHARACTERS_HASH)
        }

        private const val GET_VISIBLE_CHARACTERS_HASH = 3905245786L
        private val getVisibleCharactersBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_visible_characters", GET_VISIBLE_CHARACTERS_HASH)
        }

        private const val GET_VISIBLE_CHARACTERS_BEHAVIOR_HASH = 258789322L
        private val getVisibleCharactersBehaviorBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_visible_characters_behavior", GET_VISIBLE_CHARACTERS_BEHAVIOR_HASH)
        }

        private const val SET_VISIBLE_CHARACTERS_BEHAVIOR_HASH = 3383839701L
        private val setVisibleCharactersBehaviorBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_visible_characters_behavior", SET_VISIBLE_CHARACTERS_BEHAVIOR_HASH)
        }

        private const val SET_VISIBLE_RATIO_HASH = 373806689L
        private val setVisibleRatioBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_visible_ratio", SET_VISIBLE_RATIO_HASH)
        }

        private const val GET_VISIBLE_RATIO_HASH = 1740695150L
        private val getVisibleRatioBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_visible_ratio", GET_VISIBLE_RATIO_HASH)
        }

        private const val GET_CHARACTER_LINE_HASH = 3744713108L
        private val getCharacterLineBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_character_line", GET_CHARACTER_LINE_HASH)
        }

        private const val GET_CHARACTER_PARAGRAPH_HASH = 3744713108L
        private val getCharacterParagraphBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_character_paragraph", GET_CHARACTER_PARAGRAPH_HASH)
        }

        private const val GET_TOTAL_CHARACTER_COUNT_HASH = 3905245786L
        private val getTotalCharacterCountBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_total_character_count", GET_TOTAL_CHARACTER_COUNT_HASH)
        }

        private const val SET_USE_BBCODE_HASH = 2586408642L
        private val setUseBbcodeBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_use_bbcode", SET_USE_BBCODE_HASH)
        }

        private const val IS_USING_BBCODE_HASH = 36873697L
        private val isUsingBbcodeBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_using_bbcode", IS_USING_BBCODE_HASH)
        }

        private const val GET_LINE_COUNT_HASH = 3905245786L
        private val getLineCountBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_line_count", GET_LINE_COUNT_HASH)
        }

        private const val GET_LINE_RANGE_HASH = 3665014314L
        private val getLineRangeBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_line_range", GET_LINE_RANGE_HASH)
        }

        private const val GET_VISIBLE_LINE_COUNT_HASH = 3905245786L
        private val getVisibleLineCountBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_visible_line_count", GET_VISIBLE_LINE_COUNT_HASH)
        }

        private const val GET_PARAGRAPH_COUNT_HASH = 3905245786L
        private val getParagraphCountBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_paragraph_count", GET_PARAGRAPH_COUNT_HASH)
        }

        private const val GET_VISIBLE_PARAGRAPH_COUNT_HASH = 3905245786L
        private val getVisibleParagraphCountBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_visible_paragraph_count", GET_VISIBLE_PARAGRAPH_COUNT_HASH)
        }

        private const val GET_CONTENT_HEIGHT_HASH = 3905245786L
        private val getContentHeightBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_content_height", GET_CONTENT_HEIGHT_HASH)
        }

        private const val GET_CONTENT_WIDTH_HASH = 3905245786L
        private val getContentWidthBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_content_width", GET_CONTENT_WIDTH_HASH)
        }

        private const val GET_LINE_HEIGHT_HASH = 923996154L
        private val getLineHeightBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_line_height", GET_LINE_HEIGHT_HASH)
        }

        private const val GET_LINE_WIDTH_HASH = 923996154L
        private val getLineWidthBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_line_width", GET_LINE_WIDTH_HASH)
        }

        private const val GET_VISIBLE_CONTENT_RECT_HASH = 410525958L
        private val getVisibleContentRectBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_visible_content_rect", GET_VISIBLE_CONTENT_RECT_HASH)
        }

        private const val GET_LINE_OFFSET_HASH = 4025615559L
        private val getLineOffsetBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_line_offset", GET_LINE_OFFSET_HASH)
        }

        private const val GET_PARAGRAPH_OFFSET_HASH = 4025615559L
        private val getParagraphOffsetBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_paragraph_offset", GET_PARAGRAPH_OFFSET_HASH)
        }

        private const val PARSE_EXPRESSIONS_FOR_VALUES_HASH = 1522900837L
        private val parseExpressionsForValuesBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "parse_expressions_for_values", PARSE_EXPRESSIONS_FOR_VALUES_HASH)
        }

        private const val SET_EFFECTS_HASH = 381264803L
        private val setEffectsBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "set_effects", SET_EFFECTS_HASH)
        }

        private const val GET_EFFECTS_HASH = 2915620761L
        private val getEffectsBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_effects", GET_EFFECTS_HASH)
        }

        private const val INSTALL_EFFECT_HASH = 1114965689L
        private val installEffectBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "install_effect", INSTALL_EFFECT_HASH)
        }

        private const val RELOAD_EFFECTS_HASH = 3218959716L
        private val reloadEffectsBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "reload_effects", RELOAD_EFFECTS_HASH)
        }

        private const val GET_MENU_HASH = 229722558L
        private val getMenuBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "get_menu", GET_MENU_HASH)
        }

        private const val IS_MENU_VISIBLE_HASH = 36873697L
        private val isMenuVisibleBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "is_menu_visible", IS_MENU_VISIBLE_HASH)
        }

        private const val MENU_OPTION_HASH = 1286410249L
        private val menuOptionBind by lazy {
            ObjectCalls.getMethodBind("RichTextLabel", "menu_option", MENU_OPTION_HASH)
        }
    }
}
