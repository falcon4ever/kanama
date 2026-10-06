package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Rect2

/**
 * A control for displaying plain text.
 *
 * Generated from Godot docs: Label
 */
class Label(handle: GodotHandle) : Control(handle) {
    var text: String
        @JvmName("textProperty")
        get() = getText()
        @JvmName("setTextProperty")
        set(value) = setText(value)

    var labelSettings: LabelSettings?
        @JvmName("labelSettingsProperty")
        get() = getLabelSettings()
        @JvmName("setLabelSettingsProperty")
        set(value) = setLabelSettings(value)

    var horizontalAlignment: HorizontalAlignment
        @JvmName("horizontalAlignmentProperty")
        get() = getHorizontalAlignment()
        @JvmName("setHorizontalAlignmentProperty")
        set(value) = setHorizontalAlignment(value)

    var verticalAlignment: VerticalAlignment
        @JvmName("verticalAlignmentProperty")
        get() = getVerticalAlignment()
        @JvmName("setVerticalAlignmentProperty")
        set(value) = setVerticalAlignment(value)

    var autowrapMode: TextServer.AutowrapMode
        @JvmName("autowrapModeProperty")
        get() = getAutowrapMode()
        @JvmName("setAutowrapModeProperty")
        set(value) = setAutowrapMode(value)

    var autowrapTrimFlags: TextServer.LineBreakFlag
        @JvmName("autowrapTrimFlagsProperty")
        get() = getAutowrapTrimFlags()
        @JvmName("setAutowrapTrimFlagsProperty")
        set(value) = setAutowrapTrimFlags(value)

    var justificationFlags: TextServer.JustificationFlag
        @JvmName("justificationFlagsProperty")
        get() = getJustificationFlags()
        @JvmName("setJustificationFlagsProperty")
        set(value) = setJustificationFlags(value)

    var paragraphSeparator: String
        @JvmName("paragraphSeparatorProperty")
        get() = getParagraphSeparator()
        @JvmName("setParagraphSeparatorProperty")
        set(value) = setParagraphSeparator(value)

    var clipText: Boolean
        @JvmName("clipTextProperty")
        get() = isClippingText()
        @JvmName("setClipTextProperty")
        set(value) = setClipText(value)

    var textOverrunBehavior: TextServer.OverrunBehavior
        @JvmName("textOverrunBehaviorProperty")
        get() = getTextOverrunBehavior()
        @JvmName("setTextOverrunBehaviorProperty")
        set(value) = setTextOverrunBehavior(value)

    var ellipsisChar: String
        @JvmName("ellipsisCharProperty")
        get() = getEllipsisChar()
        @JvmName("setEllipsisCharProperty")
        set(value) = setEllipsisChar(value)

    var uppercase: Boolean
        @JvmName("uppercaseProperty")
        get() = isUppercase()
        @JvmName("setUppercaseProperty")
        set(value) = setUppercase(value)

    var tabStops: List<Float>
        @JvmName("tabStopsProperty")
        get() = getTabStops()
        @JvmName("setTabStopsProperty")
        set(value) = setTabStops(value)

    var linesSkipped: Int
        @JvmName("linesSkippedProperty")
        get() = getLinesSkipped()
        @JvmName("setLinesSkippedProperty")
        set(value) = setLinesSkipped(value)

    var maxLinesVisible: Int
        @JvmName("maxLinesVisibleProperty")
        get() = getMaxLinesVisible()
        @JvmName("setMaxLinesVisibleProperty")
        set(value) = setMaxLinesVisible(value)

    var visibleCharacters: Int
        @JvmName("visibleCharactersProperty")
        get() = getVisibleCharacters()
        @JvmName("setVisibleCharactersProperty")
        set(value) = setVisibleCharacters(value)

    var visibleCharactersBehavior: TextServer.VisibleCharactersBehavior
        @JvmName("visibleCharactersBehaviorProperty")
        get() = getVisibleCharactersBehavior()
        @JvmName("setVisibleCharactersBehaviorProperty")
        set(value) = setVisibleCharactersBehavior(value)

    var visibleRatio: Double
        @JvmName("visibleRatioProperty")
        get() = getVisibleRatio()
        @JvmName("setVisibleRatioProperty")
        set(value) = setVisibleRatio(value)

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

    /**
     * Controls the text's horizontal alignment. Supports left, center, right, and fill (also known as
     * justify).
     *
     * Generated from Godot docs: Label.set_horizontal_alignment
     */
    fun setHorizontalAlignment(alignment: HorizontalAlignment) {
        ObjectCalls.ptrcallWithLongArg(Binds.setHorizontalAlignmentBind, segment, alignment.value)
    }

    /**
     * Controls the text's horizontal alignment. Supports left, center, right, and fill (also known as
     * justify).
     *
     * Generated from Godot docs: Label.get_horizontal_alignment
     */
    fun getHorizontalAlignment(): HorizontalAlignment {
        return HorizontalAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHorizontalAlignmentBind, segment))
    }

    /**
     * Controls the text's vertical alignment. Supports top, center, bottom, and fill.
     *
     * Generated from Godot docs: Label.set_vertical_alignment
     */
    fun setVerticalAlignment(alignment: VerticalAlignment) {
        ObjectCalls.ptrcallWithLongArg(Binds.setVerticalAlignmentBind, segment, alignment.value)
    }

    /**
     * Controls the text's vertical alignment. Supports top, center, bottom, and fill.
     *
     * Generated from Godot docs: Label.get_vertical_alignment
     */
    fun getVerticalAlignment(): VerticalAlignment {
        return VerticalAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getVerticalAlignmentBind, segment))
    }

    /**
     * The text to display on screen.
     *
     * Generated from Godot docs: Label.set_text
     */
    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setTextBind, segment, text)
    }

    /**
     * The text to display on screen.
     *
     * Generated from Godot docs: Label.get_text
     */
    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getTextBind, segment)
    }

    /**
     * A `LabelSettings` resource that can be shared between multiple `Label` nodes. Takes priority
     * over theme properties.
     *
     * Generated from Godot docs: Label.set_label_settings
     */
    fun setLabelSettings(settings: LabelSettings?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setLabelSettingsBind, segment, listOf(settings?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * A `LabelSettings` resource that can be shared between multiple `Label` nodes. Takes priority
     * over theme properties.
     *
     * Generated from Godot docs: Label.get_label_settings
     */
    fun getLabelSettings(): LabelSettings? {
        return LabelSettings.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getLabelSettingsBind, segment))
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: Label.set_text_direction
     */
    fun setTextDirection(direction: Control.TextDirection) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTextDirectionBind, segment, direction.value)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: Label.get_text_direction
     */
    fun getTextDirection(): Control.TextDirection {
        return Control.TextDirection(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextDirectionBind, segment))
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: Label.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setLanguageBind, segment, language)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: Label.get_language
     */
    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getLanguageBind, segment)
    }

    /**
     * String used as a paragraph separator. Each paragraph is processed independently, in its own BiDi
     * context.
     *
     * Generated from Godot docs: Label.set_paragraph_separator
     */
    fun setParagraphSeparator(paragraphSeparator: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setParagraphSeparatorBind, segment, paragraphSeparator)
    }

    /**
     * String used as a paragraph separator. Each paragraph is processed independently, in its own BiDi
     * context.
     *
     * Generated from Godot docs: Label.get_paragraph_separator
     */
    fun getParagraphSeparator(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getParagraphSeparatorBind, segment)
    }

    /**
     * If set to something other than `TextServer.AutowrapMode.OFF`, the text gets wrapped inside the
     * node's bounding rectangle. If you resize the node, it will change its height automatically to
     * show all the text. Note: Labels with autowrapping enabled must have a custom maximum width
     * configured to work correctly, either through the Label's own `Control.custom_maximum_size` or as
     * a result of a propagated maximum size from a parent Control with
     * `Control.propagate_maximum_size` enabled.
     *
     * Generated from Godot docs: Label.set_autowrap_mode
     */
    fun setAutowrapMode(autowrapMode: TextServer.AutowrapMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAutowrapModeBind, segment, autowrapMode.value)
    }

    /**
     * If set to something other than `TextServer.AutowrapMode.OFF`, the text gets wrapped inside the
     * node's bounding rectangle. If you resize the node, it will change its height automatically to
     * show all the text. Note: Labels with autowrapping enabled must have a custom maximum width
     * configured to work correctly, either through the Label's own `Control.custom_maximum_size` or as
     * a result of a propagated maximum size from a parent Control with
     * `Control.propagate_maximum_size` enabled.
     *
     * Generated from Godot docs: Label.get_autowrap_mode
     */
    fun getAutowrapMode(): TextServer.AutowrapMode {
        return TextServer.AutowrapMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAutowrapModeBind, segment))
    }

    /**
     * Autowrap space trimming flags. See `TextServer.LineBreakFlag.TRIM_START_EDGE_SPACES` and
     * `TextServer.LineBreakFlag.TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: Label.set_autowrap_trim_flags
     */
    fun setAutowrapTrimFlags(autowrapTrimFlags: TextServer.LineBreakFlag) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAutowrapTrimFlagsBind, segment, autowrapTrimFlags.value)
    }

    /**
     * Autowrap space trimming flags. See `TextServer.LineBreakFlag.TRIM_START_EDGE_SPACES` and
     * `TextServer.LineBreakFlag.TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: Label.get_autowrap_trim_flags
     */
    fun getAutowrapTrimFlags(): TextServer.LineBreakFlag {
        return TextServer.LineBreakFlag(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAutowrapTrimFlagsBind, segment))
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: Label.set_justification_flags
     */
    fun setJustificationFlags(justificationFlags: TextServer.JustificationFlag) {
        ObjectCalls.ptrcallWithLongArg(Binds.setJustificationFlagsBind, segment, justificationFlags.value)
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: Label.get_justification_flags
     */
    fun getJustificationFlags(): TextServer.JustificationFlag {
        return TextServer.JustificationFlag(ObjectCalls.ptrcallNoArgsRetLong(Binds.getJustificationFlagsBind, segment))
    }

    /**
     * If `true`, the Label only shows the text that fits inside its bounding rectangle and will clip
     * text horizontally.
     *
     * Generated from Godot docs: Label.set_clip_text
     */
    fun setClipText(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setClipTextBind, segment, enable)
    }

    /**
     * If `true`, the Label only shows the text that fits inside its bounding rectangle and will clip
     * text horizontally.
     *
     * Generated from Godot docs: Label.is_clipping_text
     */
    fun isClippingText(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isClippingTextBind, segment)
    }

    /**
     * Aligns text to the given tab-stops.
     *
     * Generated from Godot docs: Label.set_tab_stops
     */
    fun setTabStops(tabStops: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(Binds.setTabStopsBind, segment, tabStops)
    }

    /**
     * Aligns text to the given tab-stops.
     *
     * Generated from Godot docs: Label.get_tab_stops
     */
    fun getTabStops(): List<Float> {
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(Binds.getTabStopsBind, segment)
    }

    /**
     * The clipping behavior when the text exceeds the node's bounding rectangle.
     *
     * Generated from Godot docs: Label.set_text_overrun_behavior
     */
    fun setTextOverrunBehavior(overrunBehavior: TextServer.OverrunBehavior) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTextOverrunBehaviorBind, segment, overrunBehavior.value)
    }

    /**
     * The clipping behavior when the text exceeds the node's bounding rectangle.
     *
     * Generated from Godot docs: Label.get_text_overrun_behavior
     */
    fun getTextOverrunBehavior(): TextServer.OverrunBehavior {
        return TextServer.OverrunBehavior(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextOverrunBehaviorBind, segment))
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: Label.set_ellipsis_char
     */
    fun setEllipsisChar(char: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setEllipsisCharBind, segment, char)
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: Label.get_ellipsis_char
     */
    fun getEllipsisChar(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getEllipsisCharBind, segment)
    }

    /**
     * If `true`, all the text displays as UPPERCASE.
     *
     * Generated from Godot docs: Label.set_uppercase
     */
    fun setUppercase(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUppercaseBind, segment, enable)
    }

    /**
     * If `true`, all the text displays as UPPERCASE.
     *
     * Generated from Godot docs: Label.is_uppercase
     */
    fun isUppercase(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUppercaseBind, segment)
    }

    /**
     * Returns the height of the line `line`. If `line` is set to `-1`, returns the biggest line
     * height. If there are no lines, returns font size in pixels.
     *
     * Generated from Godot docs: Label.get_line_height
     */
    fun getLineHeight(line: Int = -1): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getLineHeightBind, segment, line)
    }

    /**
     * Returns the number of lines of text the Label has.
     *
     * Generated from Godot docs: Label.get_line_count
     */
    fun getLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLineCountBind, segment)
    }

    /**
     * Returns the number of lines shown. Useful if the `Label`'s height cannot currently display all
     * lines.
     *
     * Generated from Godot docs: Label.get_visible_line_count
     */
    fun getVisibleLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getVisibleLineCountBind, segment)
    }

    /**
     * Returns the total number of printable characters in the text (excluding spaces and newlines).
     *
     * Generated from Godot docs: Label.get_total_character_count
     */
    fun getTotalCharacterCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTotalCharacterCountBind, segment)
    }

    /**
     * The number of characters to display. If set to `-1`, all characters are displayed. This can be
     * useful when animating the text appearing in a dialog box. Note: Setting this property updates
     * `visible_ratio` accordingly. Note: Characters are counted as Unicode codepoints. A single
     * visible grapheme may contain multiple codepoints (e.g. certain emoji use three codepoints). A
     * single codepoint may contain two UTF-16 characters, which are used in C# strings.
     *
     * Generated from Godot docs: Label.set_visible_characters
     */
    fun setVisibleCharacters(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setVisibleCharactersBind, segment, amount)
    }

    /**
     * The number of characters to display. If set to `-1`, all characters are displayed. This can be
     * useful when animating the text appearing in a dialog box. Note: Setting this property updates
     * `visible_ratio` accordingly. Note: Characters are counted as Unicode codepoints. A single
     * visible grapheme may contain multiple codepoints (e.g. certain emoji use three codepoints). A
     * single codepoint may contain two UTF-16 characters, which are used in C# strings.
     *
     * Generated from Godot docs: Label.get_visible_characters
     */
    fun getVisibleCharacters(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getVisibleCharactersBind, segment)
    }

    /**
     * The clipping behavior when `visible_characters` or `visible_ratio` is set.
     *
     * Generated from Godot docs: Label.get_visible_characters_behavior
     */
    fun getVisibleCharactersBehavior(): TextServer.VisibleCharactersBehavior {
        return TextServer.VisibleCharactersBehavior(ObjectCalls.ptrcallNoArgsRetLong(Binds.getVisibleCharactersBehaviorBind, segment))
    }

    /**
     * The clipping behavior when `visible_characters` or `visible_ratio` is set.
     *
     * Generated from Godot docs: Label.set_visible_characters_behavior
     */
    fun setVisibleCharactersBehavior(behavior: TextServer.VisibleCharactersBehavior) {
        ObjectCalls.ptrcallWithLongArg(Binds.setVisibleCharactersBehaviorBind, segment, behavior.value)
    }

    /**
     * The fraction of characters to display, relative to the total number of characters (see
     * `get_total_character_count`). If set to `1.0`, all characters are displayed. If set to `0.5`,
     * only half of the characters will be displayed. This can be useful when animating the text
     * appearing in a dialog box. Note: Setting this property updates `visible_characters` accordingly.
     *
     * Generated from Godot docs: Label.set_visible_ratio
     */
    fun setVisibleRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setVisibleRatioBind, segment, ratio)
    }

    /**
     * The fraction of characters to display, relative to the total number of characters (see
     * `get_total_character_count`). If set to `1.0`, all characters are displayed. If set to `0.5`,
     * only half of the characters will be displayed. This can be useful when animating the text
     * appearing in a dialog box. Note: Setting this property updates `visible_characters` accordingly.
     *
     * Generated from Godot docs: Label.get_visible_ratio
     */
    fun getVisibleRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getVisibleRatioBind, segment)
    }

    /**
     * The number of the lines ignored and not displayed from the start of the `text` value.
     *
     * Generated from Godot docs: Label.set_lines_skipped
     */
    fun setLinesSkipped(linesSkipped: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setLinesSkippedBind, segment, linesSkipped)
    }

    /**
     * The number of the lines ignored and not displayed from the start of the `text` value.
     *
     * Generated from Godot docs: Label.get_lines_skipped
     */
    fun getLinesSkipped(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLinesSkippedBind, segment)
    }

    /**
     * Limits the lines of text the node shows on screen.
     *
     * Generated from Godot docs: Label.set_max_lines_visible
     */
    fun setMaxLinesVisible(linesVisible: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxLinesVisibleBind, segment, linesVisible)
    }

    /**
     * Limits the lines of text the node shows on screen.
     *
     * Generated from Godot docs: Label.get_max_lines_visible
     */
    fun getMaxLinesVisible(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxLinesVisibleBind, segment)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: Label.set_structured_text_bidi_override
     */
    fun setStructuredTextBidiOverride(parser: TextServer.StructuredTextParser) {
        ObjectCalls.ptrcallWithLongArg(Binds.setStructuredTextBidiOverrideBind, segment, parser.value)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: Label.get_structured_text_bidi_override
     */
    fun getStructuredTextBidiOverride(): TextServer.StructuredTextParser {
        return TextServer.StructuredTextParser(ObjectCalls.ptrcallNoArgsRetLong(Binds.getStructuredTextBidiOverrideBind, segment))
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: Label.set_structured_text_bidi_override_options
     */
    fun setStructuredTextBidiOverrideOptions(args: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(Binds.setStructuredTextBidiOverrideOptionsBind, segment, args)
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: Label.get_structured_text_bidi_override_options
     */
    fun getStructuredTextBidiOverrideOptions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getStructuredTextBidiOverrideOptionsBind, segment)
    }

    /**
     * Returns the bounding rectangle of the character at position `pos` in the label's local
     * coordinate system. If the character is a non-visual character or `pos` is outside the valid
     * range, an empty `Rect2` is returned. If the character is a part of a composite grapheme, the
     * bounding rectangle of the whole grapheme is returned.
     *
     * Generated from Godot docs: Label.get_character_bounds
     */
    fun getCharacterBounds(pos: Int): Rect2 {
        return ObjectCalls.ptrcallWithIntArgRetRect2(Binds.getCharacterBoundsBind, segment, pos)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Label? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Label? =
            if (handle.address() == 0L) null else Label(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        @JvmField
        val setHorizontalAlignmentBind =
            ObjectCalls.getMethodBind("Label", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)

        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        @JvmField
        val getHorizontalAlignmentBind =
            ObjectCalls.getMethodBind("Label", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)

        private const val SET_VERTICAL_ALIGNMENT_HASH = 1796458609L
        @JvmField
        val setVerticalAlignmentBind =
            ObjectCalls.getMethodBind("Label", "set_vertical_alignment", SET_VERTICAL_ALIGNMENT_HASH)

        private const val GET_VERTICAL_ALIGNMENT_HASH = 3274884059L
        @JvmField
        val getVerticalAlignmentBind =
            ObjectCalls.getMethodBind("Label", "get_vertical_alignment", GET_VERTICAL_ALIGNMENT_HASH)

        private const val SET_TEXT_HASH = 83702148L
        @JvmField
        val setTextBind =
            ObjectCalls.getMethodBind("Label", "set_text", SET_TEXT_HASH)

        private const val GET_TEXT_HASH = 201670096L
        @JvmField
        val getTextBind =
            ObjectCalls.getMethodBind("Label", "get_text", GET_TEXT_HASH)

        private const val SET_LABEL_SETTINGS_HASH = 1030653839L
        @JvmField
        val setLabelSettingsBind =
            ObjectCalls.getMethodBind("Label", "set_label_settings", SET_LABEL_SETTINGS_HASH)

        private const val GET_LABEL_SETTINGS_HASH = 826676056L
        @JvmField
        val getLabelSettingsBind =
            ObjectCalls.getMethodBind("Label", "get_label_settings", GET_LABEL_SETTINGS_HASH)

        private const val SET_TEXT_DIRECTION_HASH = 119160795L
        @JvmField
        val setTextDirectionBind =
            ObjectCalls.getMethodBind("Label", "set_text_direction", SET_TEXT_DIRECTION_HASH)

        private const val GET_TEXT_DIRECTION_HASH = 797257663L
        @JvmField
        val getTextDirectionBind =
            ObjectCalls.getMethodBind("Label", "get_text_direction", GET_TEXT_DIRECTION_HASH)

        private const val SET_LANGUAGE_HASH = 83702148L
        @JvmField
        val setLanguageBind =
            ObjectCalls.getMethodBind("Label", "set_language", SET_LANGUAGE_HASH)

        private const val GET_LANGUAGE_HASH = 201670096L
        @JvmField
        val getLanguageBind =
            ObjectCalls.getMethodBind("Label", "get_language", GET_LANGUAGE_HASH)

        private const val SET_PARAGRAPH_SEPARATOR_HASH = 83702148L
        @JvmField
        val setParagraphSeparatorBind =
            ObjectCalls.getMethodBind("Label", "set_paragraph_separator", SET_PARAGRAPH_SEPARATOR_HASH)

        private const val GET_PARAGRAPH_SEPARATOR_HASH = 201670096L
        @JvmField
        val getParagraphSeparatorBind =
            ObjectCalls.getMethodBind("Label", "get_paragraph_separator", GET_PARAGRAPH_SEPARATOR_HASH)

        private const val SET_AUTOWRAP_MODE_HASH = 3289138044L
        @JvmField
        val setAutowrapModeBind =
            ObjectCalls.getMethodBind("Label", "set_autowrap_mode", SET_AUTOWRAP_MODE_HASH)

        private const val GET_AUTOWRAP_MODE_HASH = 1549071663L
        @JvmField
        val getAutowrapModeBind =
            ObjectCalls.getMethodBind("Label", "get_autowrap_mode", GET_AUTOWRAP_MODE_HASH)

        private const val SET_AUTOWRAP_TRIM_FLAGS_HASH = 2809697122L
        @JvmField
        val setAutowrapTrimFlagsBind =
            ObjectCalls.getMethodBind("Label", "set_autowrap_trim_flags", SET_AUTOWRAP_TRIM_FLAGS_HASH)

        private const val GET_AUTOWRAP_TRIM_FLAGS_HASH = 2340632602L
        @JvmField
        val getAutowrapTrimFlagsBind =
            ObjectCalls.getMethodBind("Label", "get_autowrap_trim_flags", GET_AUTOWRAP_TRIM_FLAGS_HASH)

        private const val SET_JUSTIFICATION_FLAGS_HASH = 2877345813L
        @JvmField
        val setJustificationFlagsBind =
            ObjectCalls.getMethodBind("Label", "set_justification_flags", SET_JUSTIFICATION_FLAGS_HASH)

        private const val GET_JUSTIFICATION_FLAGS_HASH = 1583363614L
        @JvmField
        val getJustificationFlagsBind =
            ObjectCalls.getMethodBind("Label", "get_justification_flags", GET_JUSTIFICATION_FLAGS_HASH)

        private const val SET_CLIP_TEXT_HASH = 2586408642L
        @JvmField
        val setClipTextBind =
            ObjectCalls.getMethodBind("Label", "set_clip_text", SET_CLIP_TEXT_HASH)

        private const val IS_CLIPPING_TEXT_HASH = 36873697L
        @JvmField
        val isClippingTextBind =
            ObjectCalls.getMethodBind("Label", "is_clipping_text", IS_CLIPPING_TEXT_HASH)

        private const val SET_TAB_STOPS_HASH = 2899603908L
        @JvmField
        val setTabStopsBind =
            ObjectCalls.getMethodBind("Label", "set_tab_stops", SET_TAB_STOPS_HASH)

        private const val GET_TAB_STOPS_HASH = 675695659L
        @JvmField
        val getTabStopsBind =
            ObjectCalls.getMethodBind("Label", "get_tab_stops", GET_TAB_STOPS_HASH)

        private const val SET_TEXT_OVERRUN_BEHAVIOR_HASH = 1008890932L
        @JvmField
        val setTextOverrunBehaviorBind =
            ObjectCalls.getMethodBind("Label", "set_text_overrun_behavior", SET_TEXT_OVERRUN_BEHAVIOR_HASH)

        private const val GET_TEXT_OVERRUN_BEHAVIOR_HASH = 3779142101L
        @JvmField
        val getTextOverrunBehaviorBind =
            ObjectCalls.getMethodBind("Label", "get_text_overrun_behavior", GET_TEXT_OVERRUN_BEHAVIOR_HASH)

        private const val SET_ELLIPSIS_CHAR_HASH = 83702148L
        @JvmField
        val setEllipsisCharBind =
            ObjectCalls.getMethodBind("Label", "set_ellipsis_char", SET_ELLIPSIS_CHAR_HASH)

        private const val GET_ELLIPSIS_CHAR_HASH = 201670096L
        @JvmField
        val getEllipsisCharBind =
            ObjectCalls.getMethodBind("Label", "get_ellipsis_char", GET_ELLIPSIS_CHAR_HASH)

        private const val SET_UPPERCASE_HASH = 2586408642L
        @JvmField
        val setUppercaseBind =
            ObjectCalls.getMethodBind("Label", "set_uppercase", SET_UPPERCASE_HASH)

        private const val IS_UPPERCASE_HASH = 36873697L
        @JvmField
        val isUppercaseBind =
            ObjectCalls.getMethodBind("Label", "is_uppercase", IS_UPPERCASE_HASH)

        private const val GET_LINE_HEIGHT_HASH = 181039630L
        @JvmField
        val getLineHeightBind =
            ObjectCalls.getMethodBind("Label", "get_line_height", GET_LINE_HEIGHT_HASH)

        private const val GET_LINE_COUNT_HASH = 3905245786L
        @JvmField
        val getLineCountBind =
            ObjectCalls.getMethodBind("Label", "get_line_count", GET_LINE_COUNT_HASH)

        private const val GET_VISIBLE_LINE_COUNT_HASH = 3905245786L
        @JvmField
        val getVisibleLineCountBind =
            ObjectCalls.getMethodBind("Label", "get_visible_line_count", GET_VISIBLE_LINE_COUNT_HASH)

        private const val GET_TOTAL_CHARACTER_COUNT_HASH = 3905245786L
        @JvmField
        val getTotalCharacterCountBind =
            ObjectCalls.getMethodBind("Label", "get_total_character_count", GET_TOTAL_CHARACTER_COUNT_HASH)

        private const val SET_VISIBLE_CHARACTERS_HASH = 1286410249L
        @JvmField
        val setVisibleCharactersBind =
            ObjectCalls.getMethodBind("Label", "set_visible_characters", SET_VISIBLE_CHARACTERS_HASH)

        private const val GET_VISIBLE_CHARACTERS_HASH = 3905245786L
        @JvmField
        val getVisibleCharactersBind =
            ObjectCalls.getMethodBind("Label", "get_visible_characters", GET_VISIBLE_CHARACTERS_HASH)

        private const val GET_VISIBLE_CHARACTERS_BEHAVIOR_HASH = 258789322L
        @JvmField
        val getVisibleCharactersBehaviorBind =
            ObjectCalls.getMethodBind("Label", "get_visible_characters_behavior", GET_VISIBLE_CHARACTERS_BEHAVIOR_HASH)

        private const val SET_VISIBLE_CHARACTERS_BEHAVIOR_HASH = 3383839701L
        @JvmField
        val setVisibleCharactersBehaviorBind =
            ObjectCalls.getMethodBind("Label", "set_visible_characters_behavior", SET_VISIBLE_CHARACTERS_BEHAVIOR_HASH)

        private const val SET_VISIBLE_RATIO_HASH = 373806689L
        @JvmField
        val setVisibleRatioBind =
            ObjectCalls.getMethodBind("Label", "set_visible_ratio", SET_VISIBLE_RATIO_HASH)

        private const val GET_VISIBLE_RATIO_HASH = 1740695150L
        @JvmField
        val getVisibleRatioBind =
            ObjectCalls.getMethodBind("Label", "get_visible_ratio", GET_VISIBLE_RATIO_HASH)

        private const val SET_LINES_SKIPPED_HASH = 1286410249L
        @JvmField
        val setLinesSkippedBind =
            ObjectCalls.getMethodBind("Label", "set_lines_skipped", SET_LINES_SKIPPED_HASH)

        private const val GET_LINES_SKIPPED_HASH = 3905245786L
        @JvmField
        val getLinesSkippedBind =
            ObjectCalls.getMethodBind("Label", "get_lines_skipped", GET_LINES_SKIPPED_HASH)

        private const val SET_MAX_LINES_VISIBLE_HASH = 1286410249L
        @JvmField
        val setMaxLinesVisibleBind =
            ObjectCalls.getMethodBind("Label", "set_max_lines_visible", SET_MAX_LINES_VISIBLE_HASH)

        private const val GET_MAX_LINES_VISIBLE_HASH = 3905245786L
        @JvmField
        val getMaxLinesVisibleBind =
            ObjectCalls.getMethodBind("Label", "get_max_lines_visible", GET_MAX_LINES_VISIBLE_HASH)

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 55961453L
        @JvmField
        val setStructuredTextBidiOverrideBind =
            ObjectCalls.getMethodBind("Label", "set_structured_text_bidi_override", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3385126229L
        @JvmField
        val getStructuredTextBidiOverrideBind =
            ObjectCalls.getMethodBind("Label", "get_structured_text_bidi_override", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
        @JvmField
        val setStructuredTextBidiOverrideOptionsBind =
            ObjectCalls.getMethodBind("Label", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 3995934104L
        @JvmField
        val getStructuredTextBidiOverrideOptionsBind =
            ObjectCalls.getMethodBind("Label", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)

        private const val GET_CHARACTER_BOUNDS_HASH = 3327874267L
        @JvmField
        val getCharacterBoundsBind =
            ObjectCalls.getMethodBind("Label", "get_character_bounds", GET_CHARACTER_BOUNDS_HASH)
    }
}
