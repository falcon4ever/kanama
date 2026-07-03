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

    /**
     * Returns the text without BBCode mark-up.
     *
     * Generated from Godot docs: RichTextLabel.get_parsed_text
     */
    fun getParsedText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getParsedTextBind, handle)
    }

    /**
     * Adds raw non-BBCode-parsed text to the tag stack.
     *
     * Generated from Godot docs: RichTextLabel.add_text
     */
    fun addText(text: String) {
        ObjectCalls.ptrcallWithStringArg(addTextBind, handle, text)
    }

    /**
     * The label's text in BBCode format. Is not representative of manual modifications to the internal
     * tag stack. Erases changes made by other methods when edited. Note: If `bbcode_enabled` is
     * `true`, it is unadvised to use the `+=` operator with `text` (e.g. `text += "some string"`) as
     * it replaces the whole text and can cause slowdowns. It will also erase all BBCode that was added
     * to stack using `push_*` methods. Use `append_text` for adding text instead, unless you
     * absolutely need to close a tag that was opened in an earlier method call.
     *
     * Generated from Godot docs: RichTextLabel.set_text
     */
    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, handle, text)
    }

    /**
     * Adds a horizontal rule that can be used to separate content. If `width_in_percent` is set,
     * `width` values are percentages of the control width instead of pixels. If `height_in_percent` is
     * set, `height` values are percentages of the control width instead of pixels.
     *
     * Generated from Godot docs: RichTextLabel.add_hr
     */
    fun addHr(width: Int = 90, height: Int = 2, color: Color, alignment: Long = 1L, widthInPercent: Boolean = true, heightInPercent: Boolean = false) {
        ObjectCalls.ptrcallWithTwoIntColorLongTwoBoolArgs(addHrBind, handle, width, height, color, alignment, widthInPercent, heightInPercent)
    }

    /**
     * Adds an image's opening and closing tags to the tag stack, optionally providing a `width` and
     * `height` to resize the image, a `color` to tint the image and a `region` to only use parts of
     * the image. If `width` or `height` is set to 0, the image size will be adjusted in order to keep
     * the original aspect ratio. If `width` and `height` are not set, but `region` is, the region's
     * rect will be used. `key` is an optional identifier, that can be used to modify the image via
     * `update_image`. If `pad` is set, and the image is smaller than the size specified by `width` and
     * `height`, the image padding is added to match the size instead of upscaling. Parameters
     * `width_unit` and `height_unit` determine the units used to calculate the image width and height,
     * respectively. `alt_text` is used as the image description for assistive apps.
     *
     * Generated from Godot docs: RichTextLabel.add_image
     */
    fun addImage(image: Texture2D?, width: Double = 0.0, height: Double = 0.0, color: Color, inlineAlign: Long = 5L, region: Rect2, key: Any? = null, pad: Boolean = false, tooltip: String = "", widthUnit: Long = 0L, heightUnit: Long = 0L, altText: String = "") {
        ObjectCalls.ptrcallWithObjectTwoDoubleColorLongRect2VariantBoolStringTwoLongStringArgs(addImageBind, handle, image?.requireOpenHandle() ?: MemorySegment.NULL, width, height, color, inlineAlign, region, key, pad, tooltip, widthUnit, heightUnit, altText)
    }

    /**
     * Updates the existing images with the key `key`. Only properties specified by `mask` bits are
     * updated. See `add_image`.
     *
     * Generated from Godot docs: RichTextLabel.update_image
     */
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

    /**
     * Removes a paragraph of content from the label. Returns `true` if the paragraph exists. The
     * `paragraph` argument is the index of the paragraph to remove, it can take values in the interval
     * `[0, get_paragraph_count() - 1]`. If `no_invalidate` is set to `true`, cache for the subsequent
     * paragraphs is not invalidated. Use it for faster updates if deleted paragraph is fully
     * self-contained (have no unclosed tags), or this call is part of the complex edit operation and
     * `invalidate_paragraph` will be called at the end of operation.
     *
     * Generated from Godot docs: RichTextLabel.remove_paragraph
     */
    fun removeParagraph(paragraph: Int, noInvalidate: Boolean = false): Boolean {
        return ObjectCalls.ptrcallWithIntAndBoolArgsRetBool(removeParagraphBind, handle, paragraph, noInvalidate)
    }

    /**
     * Invalidates `paragraph` and all subsequent paragraphs cache.
     *
     * Generated from Godot docs: RichTextLabel.invalidate_paragraph
     */
    fun invalidateParagraph(paragraph: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(invalidateParagraphBind, handle, paragraph)
    }

    /**
     * Adds a ``font`` tag to the tag stack. Overrides default fonts for its duration. Passing `0` to
     * `font_size` will use the existing default font size.
     *
     * Generated from Godot docs: RichTextLabel.push_font
     */
    fun pushFont(font: Font?, fontSize: Int = 0) {
        ObjectCalls.ptrcallWithObjectAndIntArg(pushFontBind, handle, font?.requireOpenHandle() ?: MemorySegment.NULL, fontSize)
    }

    /**
     * Adds a ``font_size`` tag to the tag stack. Overrides default font size for its duration.
     *
     * Generated from Godot docs: RichTextLabel.push_font_size
     */
    fun pushFontSize(fontSize: Int) {
        ObjectCalls.ptrcallWithIntArg(pushFontSizeBind, handle, fontSize)
    }

    /**
     * Adds a ``font`` tag with a normal font to the tag stack.
     *
     * Generated from Godot docs: RichTextLabel.push_normal
     */
    fun pushNormal() {
        ObjectCalls.ptrcallNoArgs(pushNormalBind, handle)
    }

    /**
     * Adds a ``font`` tag with a bold font to the tag stack. This is the same as adding a `` tag if
     * not currently in a `` tag.
     *
     * Generated from Godot docs: RichTextLabel.push_bold
     */
    fun pushBold() {
        ObjectCalls.ptrcallNoArgs(pushBoldBind, handle)
    }

    /**
     * Adds a ``font`` tag with a bold italics font to the tag stack.
     *
     * Generated from Godot docs: RichTextLabel.push_bold_italics
     */
    fun pushBoldItalics() {
        ObjectCalls.ptrcallNoArgs(pushBoldItalicsBind, handle)
    }

    /**
     * Adds a ``font`` tag with an italics font to the tag stack. This is the same as adding an `` tag
     * if not currently in a `` tag.
     *
     * Generated from Godot docs: RichTextLabel.push_italics
     */
    fun pushItalics() {
        ObjectCalls.ptrcallNoArgs(pushItalicsBind, handle)
    }

    /**
     * Adds a ``font`` tag with a monospace font to the tag stack.
     *
     * Generated from Godot docs: RichTextLabel.push_mono
     */
    fun pushMono() {
        ObjectCalls.ptrcallNoArgs(pushMonoBind, handle)
    }

    /**
     * Adds a ``color`` tag to the tag stack.
     *
     * Generated from Godot docs: RichTextLabel.push_color
     */
    fun pushColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(pushColorBind, handle, color)
    }

    /**
     * Adds a ``outline_size`` tag to the tag stack. Overrides default text outline size for its
     * duration.
     *
     * Generated from Godot docs: RichTextLabel.push_outline_size
     */
    fun pushOutlineSize(outlineSize: Int) {
        ObjectCalls.ptrcallWithIntArg(pushOutlineSizeBind, handle, outlineSize)
    }

    /**
     * Adds a ``outline_color`` tag to the tag stack. Adds text outline for its duration.
     *
     * Generated from Godot docs: RichTextLabel.push_outline_color
     */
    fun pushOutlineColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(pushOutlineColorBind, handle, color)
    }

    /**
     * Adds a ``p`` tag to the tag stack.
     *
     * Generated from Godot docs: RichTextLabel.push_paragraph
     */
    fun pushParagraph(alignment: Long, baseDirection: Long = 0L, language: String = "", stParser: Long = 0L, justificationFlags: Long = 163L, tabStops: List<Float>) {
        ObjectCalls.ptrcallWithTwoLongStringTwoLongPackedFloat32ListArgs(pushParagraphBind, handle, alignment, baseDirection, language, stParser, justificationFlags, tabStops)
    }

    /**
     * Adds an ``indent`` tag to the tag stack. Multiplies `level` by current `tab_size` to determine
     * new margin length.
     *
     * Generated from Godot docs: RichTextLabel.push_indent
     */
    fun pushIndent(level: Int) {
        ObjectCalls.ptrcallWithIntArg(pushIndentBind, handle, level)
    }

    /**
     * Adds ``ol`` or ``ul`` tag to the tag stack. Multiplies `level` by current `tab_size` to
     * determine new margin length.
     *
     * Generated from Godot docs: RichTextLabel.push_list
     */
    fun pushList(level: Int, type: Long, capitalize: Boolean, bullet: String = "•") {
        ObjectCalls.ptrcallWithIntLongBoolStringArgs(pushListBind, handle, level, type, capitalize, bullet)
    }

    /**
     * Adds a meta tag to the tag stack. Similar to the BBCode `{text} (something)`, but supports
     * non-`String` metadata types. If `meta_underlined` is `true`, meta tags display an underline.
     * This behavior can be customized with `underline_mode`. Note: Meta tags do nothing by default
     * when clicked. To assign behavior when clicked, connect `meta_clicked` to a function that is
     * called when the meta tag is clicked.
     *
     * Generated from Godot docs: RichTextLabel.push_meta
     */
    fun pushMeta(data: Any?, underlineMode: Long = 1L, tooltip: String = "") {
        ObjectCalls.ptrcallWithVariantLongStringArgs(pushMetaBind, handle, data, underlineMode, tooltip)
    }

    /**
     * Adds a ``hint`` tag to the tag stack. Same as BBCode `[hint=something]{text}`.
     *
     * Generated from Godot docs: RichTextLabel.push_hint
     */
    fun pushHint(description: String) {
        ObjectCalls.ptrcallWithStringArg(pushHintBind, handle, description)
    }

    /**
     * Adds language code used for text shaping algorithm and Open-Type font features.
     *
     * Generated from Godot docs: RichTextLabel.push_language
     */
    fun pushLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(pushLanguageBind, handle, language)
    }

    /**
     * Adds a `` tag to the tag stack. If `color`'s alpha value is `0.0`, the current font's color with
     * its alpha multiplied by `underline_alpha` is used.
     *
     * Generated from Godot docs: RichTextLabel.push_underline
     */
    fun pushUnderline(color: Color) {
        ObjectCalls.ptrcallWithColorArg(pushUnderlineBind, handle, color)
    }

    /**
     * Adds a ``s`` tag to the tag stack. If `color`'s alpha value is `0.0`, the current font's color
     * with its alpha multiplied by `strikethrough_alpha` is used.
     *
     * Generated from Godot docs: RichTextLabel.push_strikethrough
     */
    fun pushStrikethrough(color: Color) {
        ObjectCalls.ptrcallWithColorArg(pushStrikethroughBind, handle, color)
    }

    /**
     * Adds a `[table=columns,inline_align]` tag to the tag stack. Use `set_table_column_expand` to set
     * column expansion ratio. Use `push_cell` to add cells. `name` is used as the table name for
     * assistive apps.
     *
     * Generated from Godot docs: RichTextLabel.push_table
     */
    fun pushTable(columns: Int, inlineAlign: Long = 0L, alignToRow: Int = -1, name: String = "") {
        ObjectCalls.ptrcallWithIntLongIntStringArgs(pushTableBind, handle, columns, inlineAlign, alignToRow, name)
    }

    /**
     * Adds a ``dropcap`` tag to the tag stack. Drop cap (dropped capital) is a decorative element at
     * the beginning of a paragraph that is larger than the rest of the text.
     *
     * Generated from Godot docs: RichTextLabel.push_dropcap
     */
    fun pushDropcap(string: String, font: Font?, size: Int, dropcapMargins: Rect2, color: Color, outlineSize: Int = 0, outlineColor: Color) {
        ObjectCalls.ptrcallWithStringObjectIntRect2ColorIntColorArgs(pushDropcapBind, handle, string, font?.requireOpenHandle() ?: MemorySegment.NULL, size, dropcapMargins, color, outlineSize, outlineColor)
    }

    /**
     * Edits the selected column's expansion options. If `expand` is `true`, the column expands in
     * proportion to its expansion ratio versus the other columns' ratios. For example, 2 columns with
     * ratios 3 and 4 plus 70 pixels in available width would expand 30 and 40 pixels, respectively. If
     * `expand` is `false`, the column will not contribute to the total ratio.
     *
     * Generated from Godot docs: RichTextLabel.set_table_column_expand
     */
    fun setTableColumnExpand(column: Int, expand: Boolean, ratio: Int = 1, shrink: Boolean = true) {
        ObjectCalls.ptrcallWithIntBoolIntBoolArgs(setTableColumnExpandBind, handle, column, expand, ratio, shrink)
    }

    /**
     * Sets table column name for assistive apps.
     *
     * Generated from Godot docs: RichTextLabel.set_table_column_name
     */
    fun setTableColumnName(column: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringArg(setTableColumnNameBind, handle, column, name)
    }

    /**
     * Sets color of a table cell. Separate colors for alternating rows can be specified.
     *
     * Generated from Godot docs: RichTextLabel.set_cell_row_background_color
     */
    fun setCellRowBackgroundColor(oddRowBg: Color, evenRowBg: Color) {
        ObjectCalls.ptrcallWithTwoColorArgs(setCellRowBackgroundColorBind, handle, oddRowBg, evenRowBg)
    }

    /**
     * Sets color of a table cell border.
     *
     * Generated from Godot docs: RichTextLabel.set_cell_border_color
     */
    fun setCellBorderColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setCellBorderColorBind, handle, color)
    }

    /**
     * Sets minimum and maximum size overrides for a table cell.
     *
     * Generated from Godot docs: RichTextLabel.set_cell_size_override
     */
    fun setCellSizeOverride(minSize: Vector2, maxSize: Vector2) {
        ObjectCalls.ptrcallWithTwoVector2Args(setCellSizeOverrideBind, handle, minSize, maxSize)
    }

    /**
     * Sets inner padding of a table cell.
     *
     * Generated from Godot docs: RichTextLabel.set_cell_padding
     */
    fun setCellPadding(padding: Rect2) {
        ObjectCalls.ptrcallWithRect2Arg(setCellPaddingBind, handle, padding)
    }

    /**
     * Adds a ``cell`` tag to the tag stack. Must be inside a ``table`` tag. See `push_table` for
     * details. Use `set_table_column_expand` to set column expansion ratio, `set_cell_border_color` to
     * set cell border, `set_cell_row_background_color` to set cell background,
     * `set_cell_size_override` to override cell size, and `set_cell_padding` to set padding.
     *
     * Generated from Godot docs: RichTextLabel.push_cell
     */
    fun pushCell() {
        ObjectCalls.ptrcallNoArgs(pushCellBind, handle)
    }

    /**
     * Adds a ``fgcolor`` tag to the tag stack. Note: The foreground color has padding applied by
     * default, which is controlled using `text_highlight_h_padding` and `text_highlight_v_padding`.
     * This can lead to overlapping highlights if foreground colors are placed on neighboring
     * lines/columns, so consider setting those theme items to `0` if you want to avoid this.
     *
     * Generated from Godot docs: RichTextLabel.push_fgcolor
     */
    fun pushFgcolor(fgcolor: Color) {
        ObjectCalls.ptrcallWithColorArg(pushFgcolorBind, handle, fgcolor)
    }

    /**
     * Adds a ``bgcolor`` tag to the tag stack. Note: The background color has padding applied by
     * default, which is controlled using `text_highlight_h_padding` and `text_highlight_v_padding`.
     * This can lead to overlapping highlights if background colors are placed on neighboring
     * lines/columns, so consider setting those theme items to `0` if you want to avoid this.
     *
     * Generated from Godot docs: RichTextLabel.push_bgcolor
     */
    fun pushBgcolor(bgcolor: Color) {
        ObjectCalls.ptrcallWithColorArg(pushBgcolorBind, handle, bgcolor)
    }

    /**
     * Adds a custom effect tag to the tag stack. The effect does not need to be in `custom_effects`.
     * The environment is directly passed to the effect.
     *
     * Generated from Godot docs: RichTextLabel.push_customfx
     */
    fun pushCustomfx(effect: RichTextEffect?, env: Map<String, Any?>) {
        ObjectCalls.ptrcallWithObjectAndDictionaryArg(pushCustomfxBind, handle, effect?.requireOpenHandle() ?: MemorySegment.NULL, env)
    }

    /**
     * Adds a context marker to the tag stack. See `pop_context`.
     *
     * Generated from Godot docs: RichTextLabel.push_context
     */
    fun pushContext() {
        ObjectCalls.ptrcallNoArgs(pushContextBind, handle)
    }

    /**
     * Terminates tags opened after the last `push_context` call (including context marker), or all
     * tags if there's no context marker on the stack.
     *
     * Generated from Godot docs: RichTextLabel.pop_context
     */
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

    /**
     * Terminates all tags opened by `push_*` methods.
     *
     * Generated from Godot docs: RichTextLabel.pop_all
     */
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

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: RichTextLabel.set_structured_text_bidi_override
     */
    fun setStructuredTextBidiOverride(parser: Long) {
        ObjectCalls.ptrcallWithLongArg(setStructuredTextBidiOverrideBind, handle, parser)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: RichTextLabel.get_structured_text_bidi_override
     */
    fun getStructuredTextBidiOverride(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStructuredTextBidiOverrideBind, handle)
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: RichTextLabel.set_structured_text_bidi_override_options
     */
    fun setStructuredTextBidiOverrideOptions(args: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setStructuredTextBidiOverrideOptionsBind, handle, args)
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: RichTextLabel.get_structured_text_bidi_override_options
     */
    fun getStructuredTextBidiOverrideOptions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getStructuredTextBidiOverrideOptionsBind, handle)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: RichTextLabel.set_text_direction
     */
    fun setTextDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextDirectionBind, handle, direction)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: RichTextLabel.get_text_direction
     */
    fun getTextDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextDirectionBind, handle)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: RichTextLabel.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(setLanguageBind, handle, language)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: RichTextLabel.get_language
     */
    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageBind, handle)
    }

    /**
     * Controls the text's horizontal alignment. Supports left, center, right, and fill (also known as
     * justify).
     *
     * Generated from Godot docs: RichTextLabel.set_horizontal_alignment
     */
    fun setHorizontalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setHorizontalAlignmentBind, handle, alignment)
    }

    /**
     * Controls the text's horizontal alignment. Supports left, center, right, and fill (also known as
     * justify).
     *
     * Generated from Godot docs: RichTextLabel.get_horizontal_alignment
     */
    fun getHorizontalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHorizontalAlignmentBind, handle)
    }

    /**
     * Controls the text's vertical alignment. Supports top, center, bottom, and fill.
     *
     * Generated from Godot docs: RichTextLabel.set_vertical_alignment
     */
    fun setVerticalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setVerticalAlignmentBind, handle, alignment)
    }

    /**
     * Controls the text's vertical alignment. Supports top, center, bottom, and fill.
     *
     * Generated from Godot docs: RichTextLabel.get_vertical_alignment
     */
    fun getVerticalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVerticalAlignmentBind, handle)
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: RichTextLabel.set_justification_flags
     */
    fun setJustificationFlags(justificationFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setJustificationFlagsBind, handle, justificationFlags)
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: RichTextLabel.get_justification_flags
     */
    fun getJustificationFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getJustificationFlagsBind, handle)
    }

    /**
     * Aligns text to the given tab-stops.
     *
     * Generated from Godot docs: RichTextLabel.set_tab_stops
     */
    fun setTabStops(tabStops: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setTabStopsBind, handle, tabStops)
    }

    /**
     * Aligns text to the given tab-stops.
     *
     * Generated from Godot docs: RichTextLabel.get_tab_stops
     */
    fun getTabStops(): List<Float> {
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getTabStopsBind, handle)
    }

    /**
     * If set to something other than `TextServer.AUTOWRAP_OFF`, the text gets wrapped inside the
     * node's bounding rectangle. Note: RichTextLabels with autowrapping and `fit_content` enabled must
     * have a custom maximum width configured to work correctly, either through the RichTextLabel's own
     * `Control.custom_maximum_size` or as a result of a propagated maximum size from a parent Control
     * with `Control.propagate_maximum_size` enabled.
     *
     * Generated from Godot docs: RichTextLabel.set_autowrap_mode
     */
    fun setAutowrapMode(autowrapMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapModeBind, handle, autowrapMode)
    }

    /**
     * If set to something other than `TextServer.AUTOWRAP_OFF`, the text gets wrapped inside the
     * node's bounding rectangle. Note: RichTextLabels with autowrapping and `fit_content` enabled must
     * have a custom maximum width configured to work correctly, either through the RichTextLabel's own
     * `Control.custom_maximum_size` or as a result of a propagated maximum size from a parent Control
     * with `Control.propagate_maximum_size` enabled.
     *
     * Generated from Godot docs: RichTextLabel.get_autowrap_mode
     */
    fun getAutowrapMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapModeBind, handle)
    }

    /**
     * Autowrap space trimming flags. See `TextServer.BREAK_TRIM_START_EDGE_SPACES` and
     * `TextServer.BREAK_TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: RichTextLabel.set_autowrap_trim_flags
     */
    fun setAutowrapTrimFlags(autowrapTrimFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapTrimFlagsBind, handle, autowrapTrimFlags)
    }

    /**
     * Autowrap space trimming flags. See `TextServer.BREAK_TRIM_START_EDGE_SPACES` and
     * `TextServer.BREAK_TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: RichTextLabel.get_autowrap_trim_flags
     */
    fun getAutowrapTrimFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapTrimFlagsBind, handle)
    }

    /**
     * If `true`, the label underlines meta tags such as ``url`{text}`. These tags can call a function
     * when clicked if `meta_clicked` is connected to a function.
     *
     * Generated from Godot docs: RichTextLabel.set_meta_underline
     */
    fun setMetaUnderline(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setMetaUnderlineBind, handle, enable)
    }

    /**
     * If `true`, the label underlines meta tags such as ``url`{text}`. These tags can call a function
     * when clicked if `meta_clicked` is connected to a function.
     *
     * Generated from Godot docs: RichTextLabel.is_meta_underlined
     */
    fun isMetaUnderlined(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMetaUnderlinedBind, handle)
    }

    /**
     * If `true`, the label underlines hint tags such as `[hint=description]{text}`.
     *
     * Generated from Godot docs: RichTextLabel.set_hint_underline
     */
    fun setHintUnderline(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHintUnderlineBind, handle, enable)
    }

    /**
     * If `true`, the label underlines hint tags such as `[hint=description]{text}`.
     *
     * Generated from Godot docs: RichTextLabel.is_hint_underlined
     */
    fun isHintUnderlined(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHintUnderlinedBind, handle)
    }

    /**
     * If `true`, the scrollbar is visible. Setting this to `false` does not block scrolling
     * completely. See `scroll_to_line`.
     *
     * Generated from Godot docs: RichTextLabel.set_scroll_active
     */
    fun setScrollActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollActiveBind, handle, active)
    }

    /**
     * If `true`, the scrollbar is visible. Setting this to `false` does not block scrolling
     * completely. See `scroll_to_line`.
     *
     * Generated from Godot docs: RichTextLabel.is_scroll_active
     */
    fun isScrollActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollActiveBind, handle)
    }

    /**
     * If `true`, the window scrolls to display the last visible line when `visible_characters` or
     * `visible_ratio` is changed.
     *
     * Generated from Godot docs: RichTextLabel.set_scroll_follow_visible_characters
     */
    fun setScrollFollowVisibleCharacters(follow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollFollowVisibleCharactersBind, handle, follow)
    }

    /**
     * If `true`, the window scrolls to display the last visible line when `visible_characters` or
     * `visible_ratio` is changed.
     *
     * Generated from Godot docs: RichTextLabel.is_scroll_following_visible_characters
     */
    fun isScrollFollowingVisibleCharacters(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollFollowingVisibleCharactersBind, handle)
    }

    /**
     * If `true`, the window scrolls down to display new content automatically.
     *
     * Generated from Godot docs: RichTextLabel.set_scroll_follow
     */
    fun setScrollFollow(follow: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setScrollFollowBind, handle, follow)
    }

    /**
     * If `true`, the window scrolls down to display new content automatically.
     *
     * Generated from Godot docs: RichTextLabel.is_scroll_following
     */
    fun isScrollFollowing(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isScrollFollowingBind, handle)
    }

    /**
     * Returns the vertical scrollbar. Warning: This is a required internal node, removing and freeing
     * it may cause a crash. If you wish to hide it or any of its children, use their
     * `CanvasItem.visible` property.
     *
     * Generated from Godot docs: RichTextLabel.get_v_scroll_bar
     */
    fun getVScrollBar(): VScrollBar? {
        return VScrollBar.wrap(ObjectCalls.ptrcallNoArgsRetObject(getVScrollBarBind, handle))
    }

    /**
     * Scrolls the window's top line to match `line`.
     *
     * Generated from Godot docs: RichTextLabel.scroll_to_line
     */
    fun scrollToLine(line: Int) {
        ObjectCalls.ptrcallWithIntArg(scrollToLineBind, handle, line)
    }

    /**
     * Scrolls the window's top line to match first line of the `paragraph`.
     *
     * Generated from Godot docs: RichTextLabel.scroll_to_paragraph
     */
    fun scrollToParagraph(paragraph: Int) {
        ObjectCalls.ptrcallWithIntArg(scrollToParagraphBind, handle, paragraph)
    }

    /**
     * Scrolls to the beginning of the current selection.
     *
     * Generated from Godot docs: RichTextLabel.scroll_to_selection
     */
    fun scrollToSelection() {
        ObjectCalls.ptrcallNoArgs(scrollToSelectionBind, handle)
    }

    /**
     * The number of spaces associated with a single tab length. Does not affect `\t` in text tags,
     * only indent tags.
     *
     * Generated from Godot docs: RichTextLabel.set_tab_size
     */
    fun setTabSize(spaces: Int) {
        ObjectCalls.ptrcallWithIntArg(setTabSizeBind, handle, spaces)
    }

    /**
     * The number of spaces associated with a single tab length. Does not affect `\t` in text tags,
     * only indent tags.
     *
     * Generated from Godot docs: RichTextLabel.get_tab_size
     */
    fun getTabSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTabSizeBind, handle)
    }

    /**
     * If `true`, the label's minimum size will be automatically updated to fit its content, matching
     * the behavior of `Label`. Note: RichTextLabels with autowrapping and `fit_content` enabled must
     * have a custom maximum width configured to work correctly, either through the RichTextLabel's own
     * `Control.custom_maximum_size` or as a result of a propagated maximum size from a parent Control
     * with `Control.propagate_maximum_size` enabled.
     *
     * Generated from Godot docs: RichTextLabel.set_fit_content
     */
    fun setFitContent(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFitContentBind, handle, enabled)
    }

    /**
     * If `true`, the label's minimum size will be automatically updated to fit its content, matching
     * the behavior of `Label`. Note: RichTextLabels with autowrapping and `fit_content` enabled must
     * have a custom maximum width configured to work correctly, either through the RichTextLabel's own
     * `Control.custom_maximum_size` or as a result of a propagated maximum size from a parent Control
     * with `Control.propagate_maximum_size` enabled.
     *
     * Generated from Godot docs: RichTextLabel.is_fit_content_enabled
     */
    fun isFitContentEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFitContentEnabledBind, handle)
    }

    /**
     * If `true`, the label allows text selection.
     *
     * Generated from Godot docs: RichTextLabel.set_selection_enabled
     */
    fun setSelectionEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSelectionEnabledBind, handle, enabled)
    }

    /**
     * If `true`, the label allows text selection.
     *
     * Generated from Godot docs: RichTextLabel.is_selection_enabled
     */
    fun isSelectionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSelectionEnabledBind, handle)
    }

    /**
     * If `true`, a right-click displays the context menu.
     *
     * Generated from Godot docs: RichTextLabel.set_context_menu_enabled
     */
    fun setContextMenuEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setContextMenuEnabledBind, handle, enabled)
    }

    /**
     * If `true`, a right-click displays the context menu.
     *
     * Generated from Godot docs: RichTextLabel.is_context_menu_enabled
     */
    fun isContextMenuEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isContextMenuEnabledBind, handle)
    }

    /**
     * If `true`, shortcut keys for context menu items are enabled, even if the context menu is
     * disabled.
     *
     * Generated from Godot docs: RichTextLabel.set_shortcut_keys_enabled
     */
    fun setShortcutKeysEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setShortcutKeysEnabledBind, handle, enabled)
    }

    /**
     * If `true`, shortcut keys for context menu items are enabled, even if the context menu is
     * disabled.
     *
     * Generated from Godot docs: RichTextLabel.is_shortcut_keys_enabled
     */
    fun isShortcutKeysEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isShortcutKeysEnabledBind, handle)
    }

    /**
     * If `true`, the selected text will be deselected when focus is lost.
     *
     * Generated from Godot docs: RichTextLabel.set_deselect_on_focus_loss_enabled
     */
    fun setDeselectOnFocusLossEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDeselectOnFocusLossEnabledBind, handle, enable)
    }

    /**
     * If `true`, the selected text will be deselected when focus is lost.
     *
     * Generated from Godot docs: RichTextLabel.is_deselect_on_focus_loss_enabled
     */
    fun isDeselectOnFocusLossEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDeselectOnFocusLossEnabledBind, handle)
    }

    /**
     * If `true`, allow drag and drop of selected text.
     *
     * Generated from Godot docs: RichTextLabel.set_drag_and_drop_selection_enabled
     */
    fun setDragAndDropSelectionEnabled(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDragAndDropSelectionEnabledBind, handle, enable)
    }

    /**
     * If `true`, allow drag and drop of selected text.
     *
     * Generated from Godot docs: RichTextLabel.is_drag_and_drop_selection_enabled
     */
    fun isDragAndDropSelectionEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDragAndDropSelectionEnabledBind, handle)
    }

    /**
     * Returns the current selection first character index if a selection is active, `-1` otherwise.
     * Does not include BBCodes.
     *
     * Generated from Godot docs: RichTextLabel.get_selection_from
     */
    fun getSelectionFrom(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSelectionFromBind, handle)
    }

    /**
     * Returns the current selection last character index if a selection is active, `-1` otherwise.
     * Does not include BBCodes.
     *
     * Generated from Godot docs: RichTextLabel.get_selection_to
     */
    fun getSelectionTo(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getSelectionToBind, handle)
    }

    /**
     * Returns the current selection vertical line offset if a selection is active, `-1.0` otherwise.
     *
     * Generated from Godot docs: RichTextLabel.get_selection_line_offset
     */
    fun getSelectionLineOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSelectionLineOffsetBind, handle)
    }

    /**
     * Select all the text. If `selection_enabled` is `false`, no selection will occur.
     *
     * Generated from Godot docs: RichTextLabel.select_all
     */
    fun selectAll() {
        ObjectCalls.ptrcallNoArgs(selectAllBind, handle)
    }

    /**
     * Returns the current selection text. Does not include BBCodes.
     *
     * Generated from Godot docs: RichTextLabel.get_selected_text
     */
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

    /**
     * The assignment version of `append_text`. Clears the tag stack and inserts the new content.
     *
     * Generated from Godot docs: RichTextLabel.parse_bbcode
     */
    fun parseBbcode(bbcode: String) {
        ObjectCalls.ptrcallWithStringArg(parseBbcodeBind, handle, bbcode)
    }

    /**
     * Parses `bbcode` and adds tags to the tag stack as needed. Note: Using this method, you can't
     * close a tag that was opened in a previous `append_text` call. This is done to improve
     * performance, especially when updating large RichTextLabels since rebuilding the whole BBCode
     * every time would be slower. If you absolutely need to close a tag in a future method call,
     * append the `text` instead of using `append_text`.
     *
     * Generated from Godot docs: RichTextLabel.append_text
     */
    fun appendText(bbcode: String) {
        ObjectCalls.ptrcallWithStringArg(appendTextBind, handle, bbcode)
    }

    /**
     * The label's text in BBCode format. Is not representative of manual modifications to the internal
     * tag stack. Erases changes made by other methods when edited. Note: If `bbcode_enabled` is
     * `true`, it is unadvised to use the `+=` operator with `text` (e.g. `text += "some string"`) as
     * it replaces the whole text and can cause slowdowns. It will also erase all BBCode that was added
     * to stack using `push_*` methods. Use `append_text` for adding text instead, unless you
     * absolutely need to close a tag that was opened in an earlier method call.
     *
     * Generated from Godot docs: RichTextLabel.get_text
     */
    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextBind, handle)
    }

    /**
     * If `threaded` is enabled, returns `true` if the background thread has finished text processing,
     * otherwise always return `true`.
     *
     * Generated from Godot docs: RichTextLabel.is_ready
     */
    fun isReady(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isReadyBind, handle)
    }

    /**
     * If `threaded` is enabled, returns `true` if the background thread has finished text processing,
     * otherwise always return `true`.
     *
     * Generated from Godot docs: RichTextLabel.is_finished
     */
    fun isFinished(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFinishedBind, handle)
    }

    /**
     * If `true`, text processing is done in a background thread.
     *
     * Generated from Godot docs: RichTextLabel.set_threaded
     */
    fun setThreaded(threaded: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setThreadedBind, handle, threaded)
    }

    /**
     * If `true`, text processing is done in a background thread.
     *
     * Generated from Godot docs: RichTextLabel.is_threaded
     */
    fun isThreaded(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isThreadedBind, handle)
    }

    /**
     * The delay after which the loading progress bar is displayed, in milliseconds. Set to `-1` to
     * disable progress bar entirely. Note: Progress bar is displayed only if `threaded` is enabled.
     *
     * Generated from Godot docs: RichTextLabel.set_progress_bar_delay
     */
    fun setProgressBarDelay(delayMs: Int) {
        ObjectCalls.ptrcallWithIntArg(setProgressBarDelayBind, handle, delayMs)
    }

    /**
     * The delay after which the loading progress bar is displayed, in milliseconds. Set to `-1` to
     * disable progress bar entirely. Note: Progress bar is displayed only if `threaded` is enabled.
     *
     * Generated from Godot docs: RichTextLabel.get_progress_bar_delay
     */
    fun getProgressBarDelay(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getProgressBarDelayBind, handle)
    }

    /**
     * The number of characters to display. If set to `-1`, all characters are displayed. This can be
     * useful when animating the text appearing in a dialog box. Note: Setting this property updates
     * `visible_ratio` accordingly. Note: Characters are counted as Unicode codepoints. A single
     * visible grapheme may contain multiple codepoints (e.g. certain emoji use three codepoints). A
     * single codepoint may contain two UTF-16 characters, which are used in C# strings.
     *
     * Generated from Godot docs: RichTextLabel.set_visible_characters
     */
    fun setVisibleCharacters(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setVisibleCharactersBind, handle, amount)
    }

    /**
     * The number of characters to display. If set to `-1`, all characters are displayed. This can be
     * useful when animating the text appearing in a dialog box. Note: Setting this property updates
     * `visible_ratio` accordingly. Note: Characters are counted as Unicode codepoints. A single
     * visible grapheme may contain multiple codepoints (e.g. certain emoji use three codepoints). A
     * single codepoint may contain two UTF-16 characters, which are used in C# strings.
     *
     * Generated from Godot docs: RichTextLabel.get_visible_characters
     */
    fun getVisibleCharacters(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVisibleCharactersBind, handle)
    }

    /**
     * The clipping behavior when `visible_characters` or `visible_ratio` is set.
     *
     * Generated from Godot docs: RichTextLabel.get_visible_characters_behavior
     */
    fun getVisibleCharactersBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVisibleCharactersBehaviorBind, handle)
    }

    /**
     * The clipping behavior when `visible_characters` or `visible_ratio` is set.
     *
     * Generated from Godot docs: RichTextLabel.set_visible_characters_behavior
     */
    fun setVisibleCharactersBehavior(behavior: Long) {
        ObjectCalls.ptrcallWithLongArg(setVisibleCharactersBehaviorBind, handle, behavior)
    }

    /**
     * The fraction of characters to display, relative to the total number of characters (see
     * `get_total_character_count`). If set to `1.0`, all characters are displayed. If set to `0.5`,
     * only half of the characters will be displayed. This can be useful when animating the text
     * appearing in a dialog box. Note: Setting this property updates `visible_characters` accordingly.
     *
     * Generated from Godot docs: RichTextLabel.set_visible_ratio
     */
    fun setVisibleRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVisibleRatioBind, handle, ratio)
    }

    /**
     * The fraction of characters to display, relative to the total number of characters (see
     * `get_total_character_count`). If set to `1.0`, all characters are displayed. If set to `0.5`,
     * only half of the characters will be displayed. This can be useful when animating the text
     * appearing in a dialog box. Note: Setting this property updates `visible_characters` accordingly.
     *
     * Generated from Godot docs: RichTextLabel.get_visible_ratio
     */
    fun getVisibleRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibleRatioBind, handle)
    }

    /**
     * Returns the line number of the character position provided. Line and character numbers are both
     * zero-indexed. Note: If `threaded` is enabled, this method returns a value for the loaded part of
     * the document. Use `is_finished` or `finished` to determine whether document is fully loaded.
     *
     * Generated from Godot docs: RichTextLabel.get_character_line
     */
    fun getCharacterLine(character: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getCharacterLineBind, handle, character)
    }

    /**
     * Returns the paragraph number of the character position provided. Paragraph and character numbers
     * are both zero-indexed. Note: If `threaded` is enabled, this method returns a value for the
     * loaded part of the document. Use `is_finished` or `finished` to determine whether document is
     * fully loaded.
     *
     * Generated from Godot docs: RichTextLabel.get_character_paragraph
     */
    fun getCharacterParagraph(character: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getCharacterParagraphBind, handle, character)
    }

    /**
     * Returns the total number of characters from text tags. Does not include BBCodes.
     *
     * Generated from Godot docs: RichTextLabel.get_total_character_count
     */
    fun getTotalCharacterCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTotalCharacterCountBind, handle)
    }

    /**
     * If `true`, the label uses BBCode formatting. Note: This only affects the contents of `text`, not
     * the tag stack.
     *
     * Generated from Godot docs: RichTextLabel.set_use_bbcode
     */
    fun setUseBbcode(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseBbcodeBind, handle, enable)
    }

    /**
     * If `true`, the label uses BBCode formatting. Note: This only affects the contents of `text`, not
     * the tag stack.
     *
     * Generated from Godot docs: RichTextLabel.is_using_bbcode
     */
    fun isUsingBbcode(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingBbcodeBind, handle)
    }

    /**
     * Returns the total number of lines in the text. Wrapped text is counted as multiple lines. Note:
     * If `threaded` is enabled, this method returns a value for the loaded part of the document. Use
     * `is_finished` or `finished` to determine whether document is fully loaded.
     *
     * Generated from Godot docs: RichTextLabel.get_line_count
     */
    fun getLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLineCountBind, handle)
    }

    /**
     * Returns the indexes of the first and last visible characters for the given `line`, as a
     * `Vector2i`. Note: If `visible_characters_behavior` is set to
     * `TextServer.VC_CHARS_BEFORE_SHAPING` only visible wrapped lines are counted. Note: If `threaded`
     * is enabled, this method returns a value for the loaded part of the document. Use `is_finished`
     * or `finished` to determine whether document is fully loaded.
     *
     * Generated from Godot docs: RichTextLabel.get_line_range
     */
    fun getLineRange(line: Int): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(getLineRangeBind, handle, line)
    }

    /**
     * Returns the number of visible lines. Note: This method returns a correct value only after the
     * label has been drawn. Note: If `threaded` is enabled, this method returns a value for the loaded
     * part of the document. Use `is_finished` or `finished` to determine whether document is fully
     * loaded.
     *
     * Generated from Godot docs: RichTextLabel.get_visible_line_count
     */
    fun getVisibleLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVisibleLineCountBind, handle)
    }

    /**
     * Returns the total number of paragraphs (newlines or `p` tags in the tag stack's text tags).
     * Considers wrapped text as one paragraph.
     *
     * Generated from Godot docs: RichTextLabel.get_paragraph_count
     */
    fun getParagraphCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getParagraphCountBind, handle)
    }

    /**
     * Returns the number of visible paragraphs. A paragraph is considered visible if at least one of
     * its lines is visible. Note: This method returns a correct value only after the label has been
     * drawn. Note: If `threaded` is enabled, this method returns a value for the loaded part of the
     * document. Use `is_finished` or `finished` to determine whether document is fully loaded.
     *
     * Generated from Godot docs: RichTextLabel.get_visible_paragraph_count
     */
    fun getVisibleParagraphCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVisibleParagraphCountBind, handle)
    }

    /**
     * Returns the height of the content. Note: This method always returns the full content size, and
     * is not affected by `visible_ratio` and `visible_characters`. To get the visible content size,
     * use `get_visible_content_rect`. Note: If `threaded` is enabled, this method returns a value for
     * the loaded part of the document. Use `is_finished` or `finished` to determine whether document
     * is fully loaded.
     *
     * Generated from Godot docs: RichTextLabel.get_content_height
     */
    fun getContentHeight(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getContentHeightBind, handle)
    }

    /**
     * Returns the width of the content. Note: This method always returns the full content size, and is
     * not affected by `visible_ratio` and `visible_characters`. To get the visible content size, use
     * `get_visible_content_rect`. Note: If `threaded` is enabled, this method returns a value for the
     * loaded part of the document. Use `is_finished` or `finished` to determine whether document is
     * fully loaded.
     *
     * Generated from Godot docs: RichTextLabel.get_content_width
     */
    fun getContentWidth(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getContentWidthBind, handle)
    }

    /**
     * Returns the height of the line found at the provided index. Note: If `threaded` is enabled, this
     * method returns a value for the loaded part of the document. Use `is_finished` or `finished` to
     * determine whether the document is fully loaded.
     *
     * Generated from Godot docs: RichTextLabel.get_line_height
     */
    fun getLineHeight(line: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getLineHeightBind, handle, line)
    }

    /**
     * Returns the width of the line found at the provided index. Note: If `threaded` is enabled, this
     * method returns a value for the loaded part of the document. Use `is_finished` or `finished` to
     * determine whether the document is fully loaded.
     *
     * Generated from Godot docs: RichTextLabel.get_line_width
     */
    fun getLineWidth(line: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getLineWidthBind, handle, line)
    }

    /**
     * Returns the bounding rectangle of the visible content. Note: This method returns a correct value
     * only after the label has been drawn.
     *
     * Generated from Godot docs: RichTextLabel.get_visible_content_rect
     */
    fun getVisibleContentRect(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(getVisibleContentRectBind, handle)
    }

    /**
     * Returns the vertical offset of the line found at the provided index. Note: If `threaded` is
     * enabled, this method returns a value for the loaded part of the document. Use `is_finished` or
     * `finished` to determine whether document is fully loaded.
     *
     * Generated from Godot docs: RichTextLabel.get_line_offset
     */
    fun getLineOffset(line: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getLineOffsetBind, handle, line)
    }

    /**
     * Returns the vertical offset of the paragraph found at the provided index. Note: If `threaded` is
     * enabled, this method returns a value for the loaded part of the document. Use `is_finished` or
     * `finished` to determine whether document is fully loaded.
     *
     * Generated from Godot docs: RichTextLabel.get_paragraph_offset
     */
    fun getParagraphOffset(paragraph: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getParagraphOffsetBind, handle, paragraph)
    }

    /**
     * Parses BBCode parameter `expressions` into a dictionary.
     *
     * Generated from Godot docs: RichTextLabel.parse_expressions_for_values
     */
    fun parseExpressionsForValues(expressions: List<String>): Map<String, Any?> {
        return ObjectCalls.ptrcallWithPackedStringListArgRetDictionary(parseExpressionsForValuesBind, handle, expressions)
    }

    /**
     * The currently installed custom effects. This is an array of `RichTextEffect`s. To add a custom
     * effect, it's more convenient to use `install_effect`.
     *
     * Generated from Godot docs: RichTextLabel.set_effects
     */
    fun setEffects(effects: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setEffectsBind, handle, effects)
    }

    /**
     * The currently installed custom effects. This is an array of `RichTextEffect`s. To add a custom
     * effect, it's more convenient to use `install_effect`.
     *
     * Generated from Godot docs: RichTextLabel.get_effects
     */
    fun getEffects(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getEffectsBind, handle)
    }

    /**
     * Installs a custom effect. This can also be done in the Inspector through the `custom_effects`
     * property. `effect` should be a valid `RichTextEffect`.
     *
     * Generated from Godot docs: RichTextLabel.install_effect
     */
    fun installEffect(effect: Any?) {
        ObjectCalls.ptrcallWithVariantArg(installEffectBind, handle, effect)
    }

    /**
     * Reloads custom effects. Useful when `custom_effects` is modified manually.
     *
     * Generated from Godot docs: RichTextLabel.reload_effects
     */
    fun reloadEffects() {
        ObjectCalls.ptrcallNoArgs(reloadEffectsBind, handle)
    }

    /**
     * Returns the `PopupMenu` of this `RichTextLabel`. By default, this menu is displayed when
     * right-clicking on the `RichTextLabel`. You can add custom menu items or remove standard ones.
     * Make sure your IDs don't conflict with the standard ones (see `MenuItems`). For example:
     *
     * Generated from Godot docs: RichTextLabel.get_menu
     */
    fun getMenu(): PopupMenu? {
        return PopupMenu.wrap(ObjectCalls.ptrcallNoArgsRetObject(getMenuBind, handle))
    }

    /**
     * Returns whether the menu is visible. Use this instead of `get_menu().visible` to improve
     * performance (so the creation of the menu is avoided).
     *
     * Generated from Godot docs: RichTextLabel.is_menu_visible
     */
    fun isMenuVisible(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isMenuVisibleBind, handle)
    }

    /**
     * Executes a given action as defined in the `MenuItems` enum.
     *
     * Generated from Godot docs: RichTextLabel.menu_option
     */
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
