package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Holds a paragraph of text.
 *
 * Generated from Godot docs: TextParagraph
 */
class TextParagraph(handle: MemorySegment) : RefCounted(handle) {
    var direction: Long
        @JvmName("directionProperty")
        get() = getDirection()
        @JvmName("setDirectionProperty")
        set(value) = setDirection(value)

    var customPunctuation: String
        @JvmName("customPunctuationProperty")
        get() = getCustomPunctuation()
        @JvmName("setCustomPunctuationProperty")
        set(value) = setCustomPunctuation(value)

    var orientation: Long
        @JvmName("orientationProperty")
        get() = getOrientation()
        @JvmName("setOrientationProperty")
        set(value) = setOrientation(value)

    var preserveInvalid: Boolean
        @JvmName("preserveInvalidProperty")
        get() = getPreserveInvalid()
        @JvmName("setPreserveInvalidProperty")
        set(value) = setPreserveInvalid(value)

    var preserveControl: Boolean
        @JvmName("preserveControlProperty")
        get() = getPreserveControl()
        @JvmName("setPreserveControlProperty")
        set(value) = setPreserveControl(value)

    var alignment: Long
        @JvmName("alignmentProperty")
        get() = getAlignment()
        @JvmName("setAlignmentProperty")
        set(value) = setAlignment(value)

    var breakFlags: Long
        @JvmName("breakFlagsProperty")
        get() = getBreakFlags()
        @JvmName("setBreakFlagsProperty")
        set(value) = setBreakFlags(value)

    var justificationFlags: Long
        @JvmName("justificationFlagsProperty")
        get() = getJustificationFlags()
        @JvmName("setJustificationFlagsProperty")
        set(value) = setJustificationFlags(value)

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

    var width: Double
        @JvmName("widthProperty")
        get() = getWidth()
        @JvmName("setWidthProperty")
        set(value) = setWidth(value)

    var maxLinesVisible: Int
        @JvmName("maxLinesVisibleProperty")
        get() = getMaxLinesVisible()
        @JvmName("setMaxLinesVisibleProperty")
        set(value) = setMaxLinesVisible(value)

    var lineSpacing: Double
        @JvmName("lineSpacingProperty")
        get() = getLineSpacing()
        @JvmName("setLineSpacingProperty")
        set(value) = setLineSpacing(value)

    /**
     * Clears text paragraph (removes text and inline objects).
     *
     * Generated from Godot docs: TextParagraph.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Duplicates this `TextParagraph`.
     *
     * Generated from Godot docs: TextParagraph.duplicate
     */
    fun duplicate(): TextParagraph? {
        return TextParagraph.wrap(ObjectCalls.ptrcallNoArgsRetObject(duplicateBind, handle))
    }

    /**
     * Text writing direction.
     *
     * Generated from Godot docs: TextParagraph.set_direction
     */
    fun setDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setDirectionBind, handle, direction)
    }

    /**
     * Text writing direction.
     *
     * Generated from Godot docs: TextParagraph.get_direction
     */
    fun getDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDirectionBind, handle)
    }

    /**
     * Returns the text writing direction inferred by the BiDi algorithm.
     *
     * Generated from Godot docs: TextParagraph.get_inferred_direction
     */
    fun getInferredDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getInferredDirectionBind, handle)
    }

    /**
     * Custom punctuation character list, used for word breaking. If set to empty string, server
     * defaults are used.
     *
     * Generated from Godot docs: TextParagraph.set_custom_punctuation
     */
    fun setCustomPunctuation(customPunctuation: String) {
        ObjectCalls.ptrcallWithStringArg(setCustomPunctuationBind, handle, customPunctuation)
    }

    /**
     * Custom punctuation character list, used for word breaking. If set to empty string, server
     * defaults are used.
     *
     * Generated from Godot docs: TextParagraph.get_custom_punctuation
     */
    fun getCustomPunctuation(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getCustomPunctuationBind, handle)
    }

    /**
     * Text orientation.
     *
     * Generated from Godot docs: TextParagraph.set_orientation
     */
    fun setOrientation(orientation: Long) {
        ObjectCalls.ptrcallWithLongArg(setOrientationBind, handle, orientation)
    }

    /**
     * Text orientation.
     *
     * Generated from Godot docs: TextParagraph.get_orientation
     */
    fun getOrientation(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getOrientationBind, handle)
    }

    /**
     * If set to `true` text will display invalid characters.
     *
     * Generated from Godot docs: TextParagraph.set_preserve_invalid
     */
    fun setPreserveInvalid(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPreserveInvalidBind, handle, enabled)
    }

    /**
     * If set to `true` text will display invalid characters.
     *
     * Generated from Godot docs: TextParagraph.get_preserve_invalid
     */
    fun getPreserveInvalid(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPreserveInvalidBind, handle)
    }

    /**
     * If set to `true` text will display control characters.
     *
     * Generated from Godot docs: TextParagraph.set_preserve_control
     */
    fun setPreserveControl(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPreserveControlBind, handle, enabled)
    }

    /**
     * If set to `true` text will display control characters.
     *
     * Generated from Godot docs: TextParagraph.get_preserve_control
     */
    fun getPreserveControl(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPreserveControlBind, handle)
    }

    /**
     * Overrides BiDi for the structured text. Override ranges should cover full source text without
     * overlaps. BiDi algorithm will be used on each range separately.
     *
     * Generated from Godot docs: TextParagraph.set_bidi_override
     */
    fun setBidiOverride(override: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setBidiOverrideBind, handle, override)
    }

    /**
     * Sets drop cap, overrides previously set drop cap. Drop cap (dropped capital) is a decorative
     * element at the beginning of a paragraph that is larger than the rest of the text.
     *
     * Generated from Godot docs: TextParagraph.set_dropcap
     */
    fun setDropcap(text: String, font: Font?, fontSize: Int, dropcapMargins: Rect2, language: String = ""): Boolean {
        return ObjectCalls.ptrcallWithStringObjectIntRect2StringArgsRetBool(setDropcapBind, handle, text, font?.requireOpenHandle() ?: MemorySegment.NULL, fontSize, dropcapMargins, language)
    }

    /**
     * Removes dropcap.
     *
     * Generated from Godot docs: TextParagraph.clear_dropcap
     */
    fun clearDropcap() {
        ObjectCalls.ptrcallNoArgs(clearDropcapBind, handle)
    }

    /**
     * Adds text span and font to draw it.
     *
     * Generated from Godot docs: TextParagraph.add_string
     */
    fun addString(text: String, font: Font?, fontSize: Int, language: String = "", meta: Any? = null): Boolean {
        return ObjectCalls.ptrcallWithStringObjectIntStringVariantArgsRetBool(addStringBind, handle, text, font?.requireOpenHandle() ?: MemorySegment.NULL, fontSize, language, meta)
    }

    /**
     * Adds inline object to the text buffer, `key` must be unique. In the text, object is represented
     * as `length` object replacement characters.
     *
     * Generated from Godot docs: TextParagraph.add_object
     */
    fun addObject(key: Any?, size: Vector2, inlineAlign: Long = 5L, length: Int = 1, baseline: Double = 0.0): Boolean {
        return ObjectCalls.ptrcallWithVariantVector2LongIntDoubleArgsRetBool(addObjectBind, handle, key, size, inlineAlign, length, baseline)
    }

    /**
     * Sets new size and alignment of embedded object.
     *
     * Generated from Godot docs: TextParagraph.resize_object
     */
    fun resizeObject(key: Any?, size: Vector2, inlineAlign: Long = 5L, baseline: Double = 0.0): Boolean {
        return ObjectCalls.ptrcallWithVariantVector2LongDoubleArgsRetBool(resizeObjectBind, handle, key, size, inlineAlign, baseline)
    }

    /**
     * Returns `true` if an object with `key` is embedded in this shaped text buffer.
     *
     * Generated from Godot docs: TextParagraph.has_object
     */
    fun hasObject(key: Any?): Boolean {
        return ObjectCalls.ptrcallWithVariantArgRetBool(hasObjectBind, handle, key)
    }

    /**
     * Paragraph horizontal alignment.
     *
     * Generated from Godot docs: TextParagraph.set_alignment
     */
    fun setAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlignmentBind, handle, alignment)
    }

    /**
     * Paragraph horizontal alignment.
     *
     * Generated from Godot docs: TextParagraph.get_alignment
     */
    fun getAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlignmentBind, handle)
    }

    /**
     * Aligns paragraph to the given tab-stops.
     *
     * Generated from Godot docs: TextParagraph.tab_align
     */
    fun tabAlign(tabStops: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(tabAlignBind, handle, tabStops)
    }

    /**
     * Line breaking rules. For more info see `TextServer`.
     *
     * Generated from Godot docs: TextParagraph.set_break_flags
     */
    fun setBreakFlags(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setBreakFlagsBind, handle, flags)
    }

    /**
     * Line breaking rules. For more info see `TextServer`.
     *
     * Generated from Godot docs: TextParagraph.get_break_flags
     */
    fun getBreakFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBreakFlagsBind, handle)
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: TextParagraph.set_justification_flags
     */
    fun setJustificationFlags(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setJustificationFlagsBind, handle, flags)
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: TextParagraph.get_justification_flags
     */
    fun getJustificationFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getJustificationFlagsBind, handle)
    }

    /**
     * The clipping behavior when the text exceeds the paragraph's set width.
     *
     * Generated from Godot docs: TextParagraph.set_text_overrun_behavior
     */
    fun setTextOverrunBehavior(overrunBehavior: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextOverrunBehaviorBind, handle, overrunBehavior)
    }

    /**
     * The clipping behavior when the text exceeds the paragraph's set width.
     *
     * Generated from Godot docs: TextParagraph.get_text_overrun_behavior
     */
    fun getTextOverrunBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextOverrunBehaviorBind, handle)
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: TextParagraph.set_ellipsis_char
     */
    fun setEllipsisChar(char: String) {
        ObjectCalls.ptrcallWithStringArg(setEllipsisCharBind, handle, char)
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: TextParagraph.get_ellipsis_char
     */
    fun getEllipsisChar(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getEllipsisCharBind, handle)
    }

    /**
     * Paragraph width.
     *
     * Generated from Godot docs: TextParagraph.set_width
     */
    fun setWidth(width: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWidthBind, handle, width)
    }

    /**
     * Paragraph width.
     *
     * Generated from Godot docs: TextParagraph.get_width
     */
    fun getWidth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWidthBind, handle)
    }

    /**
     * Returns the size of the bounding box of the paragraph, without line breaks.
     *
     * Generated from Godot docs: TextParagraph.get_non_wrapped_size
     */
    fun getNonWrappedSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getNonWrappedSizeBind, handle)
    }

    /**
     * Returns the size of the bounding box of the paragraph.
     *
     * Generated from Godot docs: TextParagraph.get_size
     */
    fun getSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getSizeBind, handle)
    }

    /**
     * Returns TextServer full string buffer RID.
     *
     * Generated from Godot docs: TextParagraph.get_rid
     */
    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
    }

    /**
     * Returns TextServer line buffer RID.
     *
     * Generated from Godot docs: TextParagraph.get_line_rid
     */
    fun getLineRid(line: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(getLineRidBind, handle, line)
    }

    /**
     * Returns drop cap text buffer RID.
     *
     * Generated from Godot docs: TextParagraph.get_dropcap_rid
     */
    fun getDropcapRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getDropcapRidBind, handle)
    }

    /**
     * Returns the character range of the paragraph.
     *
     * Generated from Godot docs: TextParagraph.get_range
     */
    fun getRange(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getRangeBind, handle)
    }

    /**
     * Returns number of lines in the paragraph.
     *
     * Generated from Godot docs: TextParagraph.get_line_count
     */
    fun getLineCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLineCountBind, handle)
    }

    /**
     * Limits the lines of text shown.
     *
     * Generated from Godot docs: TextParagraph.set_max_lines_visible
     */
    fun setMaxLinesVisible(maxLinesVisible: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxLinesVisibleBind, handle, maxLinesVisible)
    }

    /**
     * Limits the lines of text shown.
     *
     * Generated from Godot docs: TextParagraph.get_max_lines_visible
     */
    fun getMaxLinesVisible(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxLinesVisibleBind, handle)
    }

    /**
     * Additional vertical spacing between lines (in pixels), spacing is added to line descent. This
     * value can be negative.
     *
     * Generated from Godot docs: TextParagraph.set_line_spacing
     */
    fun setLineSpacing(lineSpacing: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLineSpacingBind, handle, lineSpacing)
    }

    /**
     * Additional vertical spacing between lines (in pixels), spacing is added to line descent. This
     * value can be negative.
     *
     * Generated from Godot docs: TextParagraph.get_line_spacing
     */
    fun getLineSpacing(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineSpacingBind, handle)
    }

    /**
     * Returns array of inline objects in the line.
     *
     * Generated from Godot docs: TextParagraph.get_line_objects
     */
    fun getLineObjects(line: Int): List<Any?> {
        return ObjectCalls.ptrcallWithIntArgRetArray(getLineObjectsBind, handle, line)
    }

    /**
     * Returns bounding rectangle of the inline object.
     *
     * Generated from Godot docs: TextParagraph.get_line_object_rect
     */
    fun getLineObjectRect(line: Int, key: Any?): Rect2 {
        return ObjectCalls.ptrcallWithIntAndVariantArgRetRect2(getLineObjectRectBind, handle, line, key)
    }

    /**
     * Returns size of the bounding box of the line of text. Returned size is rounded up.
     *
     * Generated from Godot docs: TextParagraph.get_line_size
     */
    fun getLineSize(line: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(getLineSizeBind, handle, line)
    }

    /**
     * Returns character range of the line.
     *
     * Generated from Godot docs: TextParagraph.get_line_range
     */
    fun getLineRange(line: Int): Vector2i {
        return ObjectCalls.ptrcallWithIntArgRetVector2i(getLineRangeBind, handle, line)
    }

    /**
     * Returns the text line ascent (number of pixels above the baseline for horizontal layout or to
     * the left of baseline for vertical).
     *
     * Generated from Godot docs: TextParagraph.get_line_ascent
     */
    fun getLineAscent(line: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getLineAscentBind, handle, line)
    }

    /**
     * Returns the text line descent (number of pixels below the baseline for horizontal layout or to
     * the right of baseline for vertical).
     *
     * Generated from Godot docs: TextParagraph.get_line_descent
     */
    fun getLineDescent(line: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getLineDescentBind, handle, line)
    }

    /**
     * Returns width (for horizontal layout) or height (for vertical) of the line of text.
     *
     * Generated from Godot docs: TextParagraph.get_line_width
     */
    fun getLineWidth(line: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getLineWidthBind, handle, line)
    }

    /**
     * Returns pixel offset of the underline below the baseline.
     *
     * Generated from Godot docs: TextParagraph.get_line_underline_position
     */
    fun getLineUnderlinePosition(line: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getLineUnderlinePositionBind, handle, line)
    }

    /**
     * Returns thickness of the underline.
     *
     * Generated from Godot docs: TextParagraph.get_line_underline_thickness
     */
    fun getLineUnderlineThickness(line: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getLineUnderlineThicknessBind, handle, line)
    }

    /**
     * Returns drop cap bounding box size.
     *
     * Generated from Godot docs: TextParagraph.get_dropcap_size
     */
    fun getDropcapSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getDropcapSizeBind, handle)
    }

    /**
     * Returns number of lines used by dropcap.
     *
     * Generated from Godot docs: TextParagraph.get_dropcap_lines
     */
    fun getDropcapLines(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getDropcapLinesBind, handle)
    }

    /**
     * Draw all lines of the text and drop cap into a canvas item at a given position, with `color`.
     * `pos` specifies the top left corner of the bounding box. If `oversampling` is greater than zero,
     * it is used as font oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextParagraph.draw
     */
    fun draw(canvas: RID, pos: Vector2, color: Color, dcColor: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDVector2TwoColorDoubleArgs(drawBind, handle, canvas, pos, color, dcColor, oversampling)
    }

    /**
     * Draw outlines of all lines of the text and drop cap into a canvas item at a given position, with
     * `color`. `pos` specifies the top left corner of the bounding box. If `oversampling` is greater
     * than zero, it is used as font oversampling factor, otherwise viewport oversampling settings are
     * used.
     *
     * Generated from Godot docs: TextParagraph.draw_outline
     */
    fun drawOutline(canvas: RID, pos: Vector2, outlineSize: Int = 1, color: Color, dcColor: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDVector2IntTwoColorDoubleArgs(drawOutlineBind, handle, canvas, pos, outlineSize, color, dcColor, oversampling)
    }

    /**
     * Draw single line of text into a canvas item at a given position, with `color`. `pos` specifies
     * the top left corner of the bounding box. If `oversampling` is greater than zero, it is used as
     * font oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextParagraph.draw_line
     */
    fun drawLine(canvas: RID, pos: Vector2, line: Int, color: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDVector2IntColorDoubleArgs(drawLineBind, handle, canvas, pos, line, color, oversampling)
    }

    /**
     * Draw outline of the single line of text into a canvas item at a given position, with `color`.
     * `pos` specifies the top left corner of the bounding box. If `oversampling` is greater than zero,
     * it is used as font oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextParagraph.draw_line_outline
     */
    fun drawLineOutline(canvas: RID, pos: Vector2, line: Int, outlineSize: Int = 1, color: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDVector2TwoIntColorDoubleArgs(drawLineOutlineBind, handle, canvas, pos, line, outlineSize, color, oversampling)
    }

    /**
     * Draw drop cap into a canvas item at a given position, with `color`. `pos` specifies the top left
     * corner of the bounding box. If `oversampling` is greater than zero, it is used as font
     * oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextParagraph.draw_dropcap
     */
    fun drawDropcap(canvas: RID, pos: Vector2, color: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDVector2ColorDoubleArgs(drawDropcapBind, handle, canvas, pos, color, oversampling)
    }

    /**
     * Draw drop cap outline into a canvas item at a given position, with `color`. `pos` specifies the
     * top left corner of the bounding box. If `oversampling` is greater than zero, it is used as font
     * oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextParagraph.draw_dropcap_outline
     */
    fun drawDropcapOutline(canvas: RID, pos: Vector2, outlineSize: Int = 1, color: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDVector2IntColorDoubleArgs(drawDropcapOutlineBind, handle, canvas, pos, outlineSize, color, oversampling)
    }

    /**
     * Returns caret character offset at the specified coordinates. This function always returns a
     * valid position.
     *
     * Generated from Godot docs: TextParagraph.hit_test
     */
    fun hitTest(coords: Vector2): Int {
        return ObjectCalls.ptrcallWithVector2ArgRetInt(hitTestBind, handle, coords)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TextParagraph? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextParagraph? =
            if (handle.address() == 0L) null else TextParagraph(handle)

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "clear", CLEAR_HASH)
        }

        private const val DUPLICATE_HASH = 3607706709L
        private val duplicateBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "duplicate", DUPLICATE_HASH)
        }

        private const val SET_DIRECTION_HASH = 1418190634L
        private val setDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_direction", SET_DIRECTION_HASH)
        }

        private const val GET_DIRECTION_HASH = 2516697328L
        private val getDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_direction", GET_DIRECTION_HASH)
        }

        private const val GET_INFERRED_DIRECTION_HASH = 2516697328L
        private val getInferredDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_inferred_direction", GET_INFERRED_DIRECTION_HASH)
        }

        private const val SET_CUSTOM_PUNCTUATION_HASH = 83702148L
        private val setCustomPunctuationBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_custom_punctuation", SET_CUSTOM_PUNCTUATION_HASH)
        }

        private const val GET_CUSTOM_PUNCTUATION_HASH = 201670096L
        private val getCustomPunctuationBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_custom_punctuation", GET_CUSTOM_PUNCTUATION_HASH)
        }

        private const val SET_ORIENTATION_HASH = 42823726L
        private val setOrientationBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_orientation", SET_ORIENTATION_HASH)
        }

        private const val GET_ORIENTATION_HASH = 175768116L
        private val getOrientationBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_orientation", GET_ORIENTATION_HASH)
        }

        private const val SET_PRESERVE_INVALID_HASH = 2586408642L
        private val setPreserveInvalidBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_preserve_invalid", SET_PRESERVE_INVALID_HASH)
        }

        private const val GET_PRESERVE_INVALID_HASH = 36873697L
        private val getPreserveInvalidBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_preserve_invalid", GET_PRESERVE_INVALID_HASH)
        }

        private const val SET_PRESERVE_CONTROL_HASH = 2586408642L
        private val setPreserveControlBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_preserve_control", SET_PRESERVE_CONTROL_HASH)
        }

        private const val GET_PRESERVE_CONTROL_HASH = 36873697L
        private val getPreserveControlBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_preserve_control", GET_PRESERVE_CONTROL_HASH)
        }

        private const val SET_BIDI_OVERRIDE_HASH = 381264803L
        private val setBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_bidi_override", SET_BIDI_OVERRIDE_HASH)
        }

        private const val SET_DROPCAP_HASH = 2498990330L
        private val setDropcapBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_dropcap", SET_DROPCAP_HASH)
        }

        private const val CLEAR_DROPCAP_HASH = 3218959716L
        private val clearDropcapBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "clear_dropcap", CLEAR_DROPCAP_HASH)
        }

        private const val ADD_STRING_HASH = 621426851L
        private val addStringBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "add_string", ADD_STRING_HASH)
        }

        private const val ADD_OBJECT_HASH = 1316529304L
        private val addObjectBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "add_object", ADD_OBJECT_HASH)
        }

        private const val RESIZE_OBJECT_HASH = 2095776372L
        private val resizeObjectBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "resize_object", RESIZE_OBJECT_HASH)
        }

        private const val HAS_OBJECT_HASH = 77467830L
        private val hasObjectBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "has_object", HAS_OBJECT_HASH)
        }

        private const val SET_ALIGNMENT_HASH = 2312603777L
        private val setAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_alignment", SET_ALIGNMENT_HASH)
        }

        private const val GET_ALIGNMENT_HASH = 341400642L
        private val getAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_alignment", GET_ALIGNMENT_HASH)
        }

        private const val TAB_ALIGN_HASH = 2899603908L
        private val tabAlignBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "tab_align", TAB_ALIGN_HASH)
        }

        private const val SET_BREAK_FLAGS_HASH = 2809697122L
        private val setBreakFlagsBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_break_flags", SET_BREAK_FLAGS_HASH)
        }

        private const val GET_BREAK_FLAGS_HASH = 2340632602L
        private val getBreakFlagsBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_break_flags", GET_BREAK_FLAGS_HASH)
        }

        private const val SET_JUSTIFICATION_FLAGS_HASH = 2877345813L
        private val setJustificationFlagsBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_justification_flags", SET_JUSTIFICATION_FLAGS_HASH)
        }

        private const val GET_JUSTIFICATION_FLAGS_HASH = 1583363614L
        private val getJustificationFlagsBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_justification_flags", GET_JUSTIFICATION_FLAGS_HASH)
        }

        private const val SET_TEXT_OVERRUN_BEHAVIOR_HASH = 1008890932L
        private val setTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_text_overrun_behavior", SET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val GET_TEXT_OVERRUN_BEHAVIOR_HASH = 3779142101L
        private val getTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_text_overrun_behavior", GET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val SET_ELLIPSIS_CHAR_HASH = 83702148L
        private val setEllipsisCharBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_ellipsis_char", SET_ELLIPSIS_CHAR_HASH)
        }

        private const val GET_ELLIPSIS_CHAR_HASH = 201670096L
        private val getEllipsisCharBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_ellipsis_char", GET_ELLIPSIS_CHAR_HASH)
        }

        private const val SET_WIDTH_HASH = 373806689L
        private val setWidthBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_width", SET_WIDTH_HASH)
        }

        private const val GET_WIDTH_HASH = 1740695150L
        private val getWidthBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_width", GET_WIDTH_HASH)
        }

        private const val GET_NON_WRAPPED_SIZE_HASH = 3341600327L
        private val getNonWrappedSizeBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_non_wrapped_size", GET_NON_WRAPPED_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3341600327L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_size", GET_SIZE_HASH)
        }

        private const val GET_RID_HASH = 2944877500L
        private val getRidBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_rid", GET_RID_HASH)
        }

        private const val GET_LINE_RID_HASH = 495598643L
        private val getLineRidBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_line_rid", GET_LINE_RID_HASH)
        }

        private const val GET_DROPCAP_RID_HASH = 2944877500L
        private val getDropcapRidBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_dropcap_rid", GET_DROPCAP_RID_HASH)
        }

        private const val GET_RANGE_HASH = 3690982128L
        private val getRangeBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_range", GET_RANGE_HASH)
        }

        private const val GET_LINE_COUNT_HASH = 3905245786L
        private val getLineCountBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_line_count", GET_LINE_COUNT_HASH)
        }

        private const val SET_MAX_LINES_VISIBLE_HASH = 1286410249L
        private val setMaxLinesVisibleBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_max_lines_visible", SET_MAX_LINES_VISIBLE_HASH)
        }

        private const val GET_MAX_LINES_VISIBLE_HASH = 3905245786L
        private val getMaxLinesVisibleBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_max_lines_visible", GET_MAX_LINES_VISIBLE_HASH)
        }

        private const val SET_LINE_SPACING_HASH = 373806689L
        private val setLineSpacingBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "set_line_spacing", SET_LINE_SPACING_HASH)
        }

        private const val GET_LINE_SPACING_HASH = 1740695150L
        private val getLineSpacingBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_line_spacing", GET_LINE_SPACING_HASH)
        }

        private const val GET_LINE_OBJECTS_HASH = 663333327L
        private val getLineObjectsBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_line_objects", GET_LINE_OBJECTS_HASH)
        }

        private const val GET_LINE_OBJECT_RECT_HASH = 204315017L
        private val getLineObjectRectBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_line_object_rect", GET_LINE_OBJECT_RECT_HASH)
        }

        private const val GET_LINE_SIZE_HASH = 2299179447L
        private val getLineSizeBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_line_size", GET_LINE_SIZE_HASH)
        }

        private const val GET_LINE_RANGE_HASH = 880721226L
        private val getLineRangeBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_line_range", GET_LINE_RANGE_HASH)
        }

        private const val GET_LINE_ASCENT_HASH = 2339986948L
        private val getLineAscentBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_line_ascent", GET_LINE_ASCENT_HASH)
        }

        private const val GET_LINE_DESCENT_HASH = 2339986948L
        private val getLineDescentBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_line_descent", GET_LINE_DESCENT_HASH)
        }

        private const val GET_LINE_WIDTH_HASH = 2339986948L
        private val getLineWidthBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_line_width", GET_LINE_WIDTH_HASH)
        }

        private const val GET_LINE_UNDERLINE_POSITION_HASH = 2339986948L
        private val getLineUnderlinePositionBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_line_underline_position", GET_LINE_UNDERLINE_POSITION_HASH)
        }

        private const val GET_LINE_UNDERLINE_THICKNESS_HASH = 2339986948L
        private val getLineUnderlineThicknessBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_line_underline_thickness", GET_LINE_UNDERLINE_THICKNESS_HASH)
        }

        private const val GET_DROPCAP_SIZE_HASH = 3341600327L
        private val getDropcapSizeBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_dropcap_size", GET_DROPCAP_SIZE_HASH)
        }

        private const val GET_DROPCAP_LINES_HASH = 3905245786L
        private val getDropcapLinesBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "get_dropcap_lines", GET_DROPCAP_LINES_HASH)
        }

        private const val DRAW_HASH = 1492808103L
        private val drawBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "draw", DRAW_HASH)
        }

        private const val DRAW_OUTLINE_HASH = 3820500590L
        private val drawOutlineBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "draw_outline", DRAW_OUTLINE_HASH)
        }

        private const val DRAW_LINE_HASH = 828033758L
        private val drawLineBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "draw_line", DRAW_LINE_HASH)
        }

        private const val DRAW_LINE_OUTLINE_HASH = 2822696703L
        private val drawLineOutlineBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "draw_line_outline", DRAW_LINE_OUTLINE_HASH)
        }

        private const val DRAW_DROPCAP_HASH = 3625105422L
        private val drawDropcapBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "draw_dropcap", DRAW_DROPCAP_HASH)
        }

        private const val DRAW_DROPCAP_OUTLINE_HASH = 2592177763L
        private val drawDropcapOutlineBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "draw_dropcap_outline", DRAW_DROPCAP_OUTLINE_HASH)
        }

        private const val HIT_TEST_HASH = 3820158470L
        private val hitTestBind by lazy {
            ObjectCalls.getMethodBind("TextParagraph", "hit_test", HIT_TEST_HASH)
        }
    }
}
