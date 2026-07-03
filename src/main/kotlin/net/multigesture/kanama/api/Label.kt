package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2

/**
 * A control for displaying plain text.
 *
 * Generated from Godot docs: Label
 */
class Label(handle: MemorySegment) : Control(handle) {
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

    var justificationFlags: Long
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

    var textOverrunBehavior: Long
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
     * Controls the text's horizontal alignment. Supports left, center, right, and fill (also known as
     * justify).
     *
     * Generated from Godot docs: Label.set_horizontal_alignment
     */
    fun setHorizontalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setHorizontalAlignmentBind, handle, alignment)
    }

    /**
     * Controls the text's horizontal alignment. Supports left, center, right, and fill (also known as
     * justify).
     *
     * Generated from Godot docs: Label.get_horizontal_alignment
     */
    fun getHorizontalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHorizontalAlignmentBind, handle)
    }

    /**
     * Controls the text's vertical alignment. Supports top, center, bottom, and fill.
     *
     * Generated from Godot docs: Label.set_vertical_alignment
     */
    fun setVerticalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setVerticalAlignmentBind, handle, alignment)
    }

    /**
     * Controls the text's vertical alignment. Supports top, center, bottom, and fill.
     *
     * Generated from Godot docs: Label.get_vertical_alignment
     */
    fun getVerticalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVerticalAlignmentBind, handle)
    }

    /**
     * The text to display on screen.
     *
     * Generated from Godot docs: Label.set_text
     */
    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, handle, text)
    }

    /**
     * The text to display on screen.
     *
     * Generated from Godot docs: Label.get_text
     */
    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextBind, handle)
    }

    /**
     * A `LabelSettings` resource that can be shared between multiple `Label` nodes. Takes priority
     * over theme properties.
     *
     * Generated from Godot docs: Label.set_label_settings
     */
    fun setLabelSettings(settings: LabelSettings?) {
        ObjectCalls.ptrcallWithObjectArgs(setLabelSettingsBind, handle, listOf(settings?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * A `LabelSettings` resource that can be shared between multiple `Label` nodes. Takes priority
     * over theme properties.
     *
     * Generated from Godot docs: Label.get_label_settings
     */
    fun getLabelSettings(): LabelSettings? {
        return LabelSettings.wrap(ObjectCalls.ptrcallNoArgsRetObject(getLabelSettingsBind, handle))
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: Label.set_text_direction
     */
    fun setTextDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextDirectionBind, handle, direction)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: Label.get_text_direction
     */
    fun getTextDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextDirectionBind, handle)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: Label.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(setLanguageBind, handle, language)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: Label.get_language
     */
    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageBind, handle)
    }

    /**
     * String used as a paragraph separator. Each paragraph is processed independently, in its own BiDi
     * context.
     *
     * Generated from Godot docs: Label.set_paragraph_separator
     */
    fun setParagraphSeparator(paragraphSeparator: String) {
        ObjectCalls.ptrcallWithStringArg(setParagraphSeparatorBind, handle, paragraphSeparator)
    }

    /**
     * String used as a paragraph separator. Each paragraph is processed independently, in its own BiDi
     * context.
     *
     * Generated from Godot docs: Label.get_paragraph_separator
     */
    fun getParagraphSeparator(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getParagraphSeparatorBind, handle)
    }

    /**
     * If set to something other than `TextServer.AUTOWRAP_OFF`, the text gets wrapped inside the
     * node's bounding rectangle. If you resize the node, it will change its height automatically to
     * show all the text. Note: Labels with autowrapping enabled must have a custom maximum width
     * configured to work correctly, either through the Label's own `Control.custom_maximum_size` or as
     * a result of a propagated maximum size from a parent Control with
     * `Control.propagate_maximum_size` enabled.
     *
     * Generated from Godot docs: Label.set_autowrap_mode
     */
    fun setAutowrapMode(autowrapMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapModeBind, handle, autowrapMode)
    }

    /**
     * If set to something other than `TextServer.AUTOWRAP_OFF`, the text gets wrapped inside the
     * node's bounding rectangle. If you resize the node, it will change its height automatically to
     * show all the text. Note: Labels with autowrapping enabled must have a custom maximum width
     * configured to work correctly, either through the Label's own `Control.custom_maximum_size` or as
     * a result of a propagated maximum size from a parent Control with
     * `Control.propagate_maximum_size` enabled.
     *
     * Generated from Godot docs: Label.get_autowrap_mode
     */
    fun getAutowrapMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapModeBind, handle)
    }

    /**
     * Autowrap space trimming flags. See `TextServer.BREAK_TRIM_START_EDGE_SPACES` and
     * `TextServer.BREAK_TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: Label.set_autowrap_trim_flags
     */
    fun setAutowrapTrimFlags(autowrapTrimFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapTrimFlagsBind, handle, autowrapTrimFlags)
    }

    /**
     * Autowrap space trimming flags. See `TextServer.BREAK_TRIM_START_EDGE_SPACES` and
     * `TextServer.BREAK_TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: Label.get_autowrap_trim_flags
     */
    fun getAutowrapTrimFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapTrimFlagsBind, handle)
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: Label.set_justification_flags
     */
    fun setJustificationFlags(justificationFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setJustificationFlagsBind, handle, justificationFlags)
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: Label.get_justification_flags
     */
    fun getJustificationFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getJustificationFlagsBind, handle)
    }

    /**
     * If `true`, the Label only shows the text that fits inside its bounding rectangle and will clip
     * text horizontally.
     *
     * Generated from Godot docs: Label.set_clip_text
     */
    fun setClipText(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setClipTextBind, handle, enable)
    }

    /**
     * If `true`, the Label only shows the text that fits inside its bounding rectangle and will clip
     * text horizontally.
     *
     * Generated from Godot docs: Label.is_clipping_text
     */
    fun isClippingText(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isClippingTextBind, handle)
    }

    /**
     * Aligns text to the given tab-stops.
     *
     * Generated from Godot docs: Label.set_tab_stops
     */
    fun setTabStops(tabStops: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(setTabStopsBind, handle, tabStops)
    }

    /**
     * Aligns text to the given tab-stops.
     *
     * Generated from Godot docs: Label.get_tab_stops
     */
    fun getTabStops(): List<Float> {
        return ObjectCalls.ptrcallNoArgsRetPackedFloat32List(getTabStopsBind, handle)
    }

    /**
     * The clipping behavior when the text exceeds the node's bounding rectangle.
     *
     * Generated from Godot docs: Label.set_text_overrun_behavior
     */
    fun setTextOverrunBehavior(overrunBehavior: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextOverrunBehaviorBind, handle, overrunBehavior)
    }

    /**
     * The clipping behavior when the text exceeds the node's bounding rectangle.
     *
     * Generated from Godot docs: Label.get_text_overrun_behavior
     */
    fun getTextOverrunBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextOverrunBehaviorBind, handle)
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: Label.set_ellipsis_char
     */
    fun setEllipsisChar(char: String) {
        ObjectCalls.ptrcallWithStringArg(setEllipsisCharBind, handle, char)
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: Label.get_ellipsis_char
     */
    fun getEllipsisChar(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getEllipsisCharBind, handle)
    }

    /**
     * If `true`, all the text displays as UPPERCASE.
     *
     * Generated from Godot docs: Label.set_uppercase
     */
    fun setUppercase(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUppercaseBind, handle, enable)
    }

    /**
     * If `true`, all the text displays as UPPERCASE.
     *
     * Generated from Godot docs: Label.is_uppercase
     */
    fun isUppercase(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUppercaseBind, handle)
    }

    /**
     * Returns the height of the line `line`. If `line` is set to `-1`, returns the biggest line
     * height. If there are no lines, returns font size in pixels.
     *
     * Generated from Godot docs: Label.get_line_height
     */
    fun getLineHeight(line: Int = -1): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getLineHeightBind, handle, line)
    }

    /**
     * Returns the number of lines of text the Label has.
     *
     * Generated from Godot docs: Label.get_line_count
     */
    fun getLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLineCountBind, handle)
    }

    /**
     * Returns the number of lines shown. Useful if the `Label`'s height cannot currently display all
     * lines.
     *
     * Generated from Godot docs: Label.get_visible_line_count
     */
    fun getVisibleLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVisibleLineCountBind, handle)
    }

    /**
     * Returns the total number of printable characters in the text (excluding spaces and newlines).
     *
     * Generated from Godot docs: Label.get_total_character_count
     */
    fun getTotalCharacterCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTotalCharacterCountBind, handle)
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
        ObjectCalls.ptrcallWithIntArg(setVisibleCharactersBind, handle, amount)
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
        return ObjectCalls.ptrcallNoArgsRetInt(getVisibleCharactersBind, handle)
    }

    /**
     * The clipping behavior when `visible_characters` or `visible_ratio` is set.
     *
     * Generated from Godot docs: Label.get_visible_characters_behavior
     */
    fun getVisibleCharactersBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVisibleCharactersBehaviorBind, handle)
    }

    /**
     * The clipping behavior when `visible_characters` or `visible_ratio` is set.
     *
     * Generated from Godot docs: Label.set_visible_characters_behavior
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
     * Generated from Godot docs: Label.set_visible_ratio
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
     * Generated from Godot docs: Label.get_visible_ratio
     */
    fun getVisibleRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVisibleRatioBind, handle)
    }

    /**
     * The number of the lines ignored and not displayed from the start of the `text` value.
     *
     * Generated from Godot docs: Label.set_lines_skipped
     */
    fun setLinesSkipped(linesSkipped: Int) {
        ObjectCalls.ptrcallWithIntArg(setLinesSkippedBind, handle, linesSkipped)
    }

    /**
     * The number of the lines ignored and not displayed from the start of the `text` value.
     *
     * Generated from Godot docs: Label.get_lines_skipped
     */
    fun getLinesSkipped(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLinesSkippedBind, handle)
    }

    /**
     * Limits the lines of text the node shows on screen.
     *
     * Generated from Godot docs: Label.set_max_lines_visible
     */
    fun setMaxLinesVisible(linesVisible: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxLinesVisibleBind, handle, linesVisible)
    }

    /**
     * Limits the lines of text the node shows on screen.
     *
     * Generated from Godot docs: Label.get_max_lines_visible
     */
    fun getMaxLinesVisible(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxLinesVisibleBind, handle)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: Label.set_structured_text_bidi_override
     */
    fun setStructuredTextBidiOverride(parser: Long) {
        ObjectCalls.ptrcallWithLongArg(setStructuredTextBidiOverrideBind, handle, parser)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: Label.get_structured_text_bidi_override
     */
    fun getStructuredTextBidiOverride(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStructuredTextBidiOverrideBind, handle)
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: Label.set_structured_text_bidi_override_options
     */
    fun setStructuredTextBidiOverrideOptions(args: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setStructuredTextBidiOverrideOptionsBind, handle, args)
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: Label.get_structured_text_bidi_override_options
     */
    fun getStructuredTextBidiOverrideOptions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getStructuredTextBidiOverrideOptionsBind, handle)
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
        return ObjectCalls.ptrcallWithIntArgRetRect2(getCharacterBoundsBind, handle, pos)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Label? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Label? =
            if (handle.address() == 0L) null else Label(handle)

        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        private val setHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        private val getHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val SET_VERTICAL_ALIGNMENT_HASH = 1796458609L
        private val setVerticalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_vertical_alignment", SET_VERTICAL_ALIGNMENT_HASH)
        }

        private const val GET_VERTICAL_ALIGNMENT_HASH = 3274884059L
        private val getVerticalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_vertical_alignment", GET_VERTICAL_ALIGNMENT_HASH)
        }

        private const val SET_TEXT_HASH = 83702148L
        private val setTextBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_text", SET_TEXT_HASH)
        }

        private const val GET_TEXT_HASH = 201670096L
        private val getTextBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_text", GET_TEXT_HASH)
        }

        private const val SET_LABEL_SETTINGS_HASH = 1030653839L
        private val setLabelSettingsBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_label_settings", SET_LABEL_SETTINGS_HASH)
        }

        private const val GET_LABEL_SETTINGS_HASH = 826676056L
        private val getLabelSettingsBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_label_settings", GET_LABEL_SETTINGS_HASH)
        }

        private const val SET_TEXT_DIRECTION_HASH = 119160795L
        private val setTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_text_direction", SET_TEXT_DIRECTION_HASH)
        }

        private const val GET_TEXT_DIRECTION_HASH = 797257663L
        private val getTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_text_direction", GET_TEXT_DIRECTION_HASH)
        }

        private const val SET_LANGUAGE_HASH = 83702148L
        private val setLanguageBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_language", SET_LANGUAGE_HASH)
        }

        private const val GET_LANGUAGE_HASH = 201670096L
        private val getLanguageBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_language", GET_LANGUAGE_HASH)
        }

        private const val SET_PARAGRAPH_SEPARATOR_HASH = 83702148L
        private val setParagraphSeparatorBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_paragraph_separator", SET_PARAGRAPH_SEPARATOR_HASH)
        }

        private const val GET_PARAGRAPH_SEPARATOR_HASH = 201670096L
        private val getParagraphSeparatorBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_paragraph_separator", GET_PARAGRAPH_SEPARATOR_HASH)
        }

        private const val SET_AUTOWRAP_MODE_HASH = 3289138044L
        private val setAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_autowrap_mode", SET_AUTOWRAP_MODE_HASH)
        }

        private const val GET_AUTOWRAP_MODE_HASH = 1549071663L
        private val getAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_autowrap_mode", GET_AUTOWRAP_MODE_HASH)
        }

        private const val SET_AUTOWRAP_TRIM_FLAGS_HASH = 2809697122L
        private val setAutowrapTrimFlagsBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_autowrap_trim_flags", SET_AUTOWRAP_TRIM_FLAGS_HASH)
        }

        private const val GET_AUTOWRAP_TRIM_FLAGS_HASH = 2340632602L
        private val getAutowrapTrimFlagsBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_autowrap_trim_flags", GET_AUTOWRAP_TRIM_FLAGS_HASH)
        }

        private const val SET_JUSTIFICATION_FLAGS_HASH = 2877345813L
        private val setJustificationFlagsBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_justification_flags", SET_JUSTIFICATION_FLAGS_HASH)
        }

        private const val GET_JUSTIFICATION_FLAGS_HASH = 1583363614L
        private val getJustificationFlagsBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_justification_flags", GET_JUSTIFICATION_FLAGS_HASH)
        }

        private const val SET_CLIP_TEXT_HASH = 2586408642L
        private val setClipTextBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_clip_text", SET_CLIP_TEXT_HASH)
        }

        private const val IS_CLIPPING_TEXT_HASH = 36873697L
        private val isClippingTextBind by lazy {
            ObjectCalls.getMethodBind("Label", "is_clipping_text", IS_CLIPPING_TEXT_HASH)
        }

        private const val SET_TAB_STOPS_HASH = 2899603908L
        private val setTabStopsBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_tab_stops", SET_TAB_STOPS_HASH)
        }

        private const val GET_TAB_STOPS_HASH = 675695659L
        private val getTabStopsBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_tab_stops", GET_TAB_STOPS_HASH)
        }

        private const val SET_TEXT_OVERRUN_BEHAVIOR_HASH = 1008890932L
        private val setTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_text_overrun_behavior", SET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val GET_TEXT_OVERRUN_BEHAVIOR_HASH = 3779142101L
        private val getTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_text_overrun_behavior", GET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val SET_ELLIPSIS_CHAR_HASH = 83702148L
        private val setEllipsisCharBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_ellipsis_char", SET_ELLIPSIS_CHAR_HASH)
        }

        private const val GET_ELLIPSIS_CHAR_HASH = 201670096L
        private val getEllipsisCharBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_ellipsis_char", GET_ELLIPSIS_CHAR_HASH)
        }

        private const val SET_UPPERCASE_HASH = 2586408642L
        private val setUppercaseBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_uppercase", SET_UPPERCASE_HASH)
        }

        private const val IS_UPPERCASE_HASH = 36873697L
        private val isUppercaseBind by lazy {
            ObjectCalls.getMethodBind("Label", "is_uppercase", IS_UPPERCASE_HASH)
        }

        private const val GET_LINE_HEIGHT_HASH = 181039630L
        private val getLineHeightBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_line_height", GET_LINE_HEIGHT_HASH)
        }

        private const val GET_LINE_COUNT_HASH = 3905245786L
        private val getLineCountBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_line_count", GET_LINE_COUNT_HASH)
        }

        private const val GET_VISIBLE_LINE_COUNT_HASH = 3905245786L
        private val getVisibleLineCountBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_visible_line_count", GET_VISIBLE_LINE_COUNT_HASH)
        }

        private const val GET_TOTAL_CHARACTER_COUNT_HASH = 3905245786L
        private val getTotalCharacterCountBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_total_character_count", GET_TOTAL_CHARACTER_COUNT_HASH)
        }

        private const val SET_VISIBLE_CHARACTERS_HASH = 1286410249L
        private val setVisibleCharactersBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_visible_characters", SET_VISIBLE_CHARACTERS_HASH)
        }

        private const val GET_VISIBLE_CHARACTERS_HASH = 3905245786L
        private val getVisibleCharactersBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_visible_characters", GET_VISIBLE_CHARACTERS_HASH)
        }

        private const val GET_VISIBLE_CHARACTERS_BEHAVIOR_HASH = 258789322L
        private val getVisibleCharactersBehaviorBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_visible_characters_behavior", GET_VISIBLE_CHARACTERS_BEHAVIOR_HASH)
        }

        private const val SET_VISIBLE_CHARACTERS_BEHAVIOR_HASH = 3383839701L
        private val setVisibleCharactersBehaviorBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_visible_characters_behavior", SET_VISIBLE_CHARACTERS_BEHAVIOR_HASH)
        }

        private const val SET_VISIBLE_RATIO_HASH = 373806689L
        private val setVisibleRatioBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_visible_ratio", SET_VISIBLE_RATIO_HASH)
        }

        private const val GET_VISIBLE_RATIO_HASH = 1740695150L
        private val getVisibleRatioBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_visible_ratio", GET_VISIBLE_RATIO_HASH)
        }

        private const val SET_LINES_SKIPPED_HASH = 1286410249L
        private val setLinesSkippedBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_lines_skipped", SET_LINES_SKIPPED_HASH)
        }

        private const val GET_LINES_SKIPPED_HASH = 3905245786L
        private val getLinesSkippedBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_lines_skipped", GET_LINES_SKIPPED_HASH)
        }

        private const val SET_MAX_LINES_VISIBLE_HASH = 1286410249L
        private val setMaxLinesVisibleBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_max_lines_visible", SET_MAX_LINES_VISIBLE_HASH)
        }

        private const val GET_MAX_LINES_VISIBLE_HASH = 3905245786L
        private val getMaxLinesVisibleBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_max_lines_visible", GET_MAX_LINES_VISIBLE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 55961453L
        private val setStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_structured_text_bidi_override", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3385126229L
        private val getStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_structured_text_bidi_override", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
        private val setStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("Label", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 3995934104L
        private val getStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val GET_CHARACTER_BOUNDS_HASH = 3327874267L
        private val getCharacterBoundsBind by lazy {
            ObjectCalls.getMethodBind("Label", "get_character_bounds", GET_CHARACTER_BOUNDS_HASH)
        }
    }
}
