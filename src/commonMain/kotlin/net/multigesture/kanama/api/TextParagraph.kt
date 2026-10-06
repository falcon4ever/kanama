package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
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
class TextParagraph(handle: GodotHandle) : RefCounted(handle) {
    var direction: TextServer.Direction
        @JvmName("directionProperty")
        get() = getDirection()
        @JvmName("setDirectionProperty")
        set(value) = setDirection(value)

    var customPunctuation: String
        @JvmName("customPunctuationProperty")
        get() = getCustomPunctuation()
        @JvmName("setCustomPunctuationProperty")
        set(value) = setCustomPunctuation(value)

    var orientation: TextServer.Orientation
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

    var alignment: HorizontalAlignment
        @JvmName("alignmentProperty")
        get() = getAlignment()
        @JvmName("setAlignmentProperty")
        set(value) = setAlignment(value)

    var breakFlags: TextServer.LineBreakFlag
        @JvmName("breakFlagsProperty")
        get() = getBreakFlags()
        @JvmName("setBreakFlagsProperty")
        set(value) = setBreakFlags(value)

    var justificationFlags: TextServer.JustificationFlag
        @JvmName("justificationFlagsProperty")
        get() = getJustificationFlags()
        @JvmName("setJustificationFlagsProperty")
        set(value) = setJustificationFlags(value)

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
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
    }

    /**
     * Duplicates this `TextParagraph`.
     *
     * Generated from Godot docs: TextParagraph.duplicate
     */
    fun duplicate(): TextParagraph? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.duplicateBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return TextParagraph.wrapOwned(ret)
    }

    /**
     * Text writing direction.
     *
     * Generated from Godot docs: TextParagraph.set_direction
     */
    fun setDirection(direction: TextServer.Direction) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setDirectionBind, segment, direction.value)
    }

    /**
     * Text writing direction.
     *
     * Generated from Godot docs: TextParagraph.get_direction
     */
    fun getDirection(): TextServer.Direction {
        checkOpen()
        return TextServer.Direction(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDirectionBind, segment))
    }

    /**
     * Returns the text writing direction inferred by the BiDi algorithm.
     *
     * Generated from Godot docs: TextParagraph.get_inferred_direction
     */
    fun getInferredDirection(): TextServer.Direction {
        checkOpen()
        return TextServer.Direction(ObjectCalls.ptrcallNoArgsRetLong(Binds.getInferredDirectionBind, segment))
    }

    /**
     * Custom punctuation character list, used for word breaking. If set to empty string, server
     * defaults are used.
     *
     * Generated from Godot docs: TextParagraph.set_custom_punctuation
     */
    fun setCustomPunctuation(customPunctuation: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setCustomPunctuationBind, segment, customPunctuation)
    }

    /**
     * Custom punctuation character list, used for word breaking. If set to empty string, server
     * defaults are used.
     *
     * Generated from Godot docs: TextParagraph.get_custom_punctuation
     */
    fun getCustomPunctuation(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getCustomPunctuationBind, segment)
    }

    /**
     * Text orientation.
     *
     * Generated from Godot docs: TextParagraph.set_orientation
     */
    fun setOrientation(orientation: TextServer.Orientation) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOrientationBind, segment, orientation.value)
    }

    /**
     * Text orientation.
     *
     * Generated from Godot docs: TextParagraph.get_orientation
     */
    fun getOrientation(): TextServer.Orientation {
        checkOpen()
        return TextServer.Orientation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOrientationBind, segment))
    }

    /**
     * If set to `true` text will display invalid characters.
     *
     * Generated from Godot docs: TextParagraph.set_preserve_invalid
     */
    fun setPreserveInvalid(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPreserveInvalidBind, segment, enabled)
    }

    /**
     * If set to `true` text will display invalid characters.
     *
     * Generated from Godot docs: TextParagraph.get_preserve_invalid
     */
    fun getPreserveInvalid(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getPreserveInvalidBind, segment)
    }

    /**
     * If set to `true` text will display control characters.
     *
     * Generated from Godot docs: TextParagraph.set_preserve_control
     */
    fun setPreserveControl(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPreserveControlBind, segment, enabled)
    }

    /**
     * If set to `true` text will display control characters.
     *
     * Generated from Godot docs: TextParagraph.get_preserve_control
     */
    fun getPreserveControl(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getPreserveControlBind, segment)
    }

    /**
     * Overrides BiDi for the structured text. Override ranges should cover full source text without
     * overlaps. BiDi algorithm will be used on each range separately.
     *
     * Generated from Godot docs: TextParagraph.set_bidi_override
     */
    fun setBidiOverride(override: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setBidiOverrideBind, segment, override)
    }

    /**
     * Sets drop cap, overrides previously set drop cap. Drop cap (dropped capital) is a decorative
     * element at the beginning of a paragraph that is larger than the rest of the text.
     *
     * Generated from Godot docs: TextParagraph.set_dropcap
     */
    fun setDropcap(text: String, font: Font?, fontSize: Int, dropcapMargins: Rect2, language: String = ""): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringObjectIntRect2StringArgsRetBool(Binds.setDropcapBind, segment, text, font?.requireOpenHandle() ?: NULL_SEGMENT, fontSize, dropcapMargins, language)
    }

    /**
     * Removes dropcap.
     *
     * Generated from Godot docs: TextParagraph.clear_dropcap
     */
    fun clearDropcap() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearDropcapBind, segment)
    }

    /**
     * Adds text span and font to draw it.
     *
     * Generated from Godot docs: TextParagraph.add_string
     */
    fun addString(text: String, font: Font?, fontSize: Int, language: String = "", meta: Any? = null): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringObjectIntStringVariantArgsRetBool(Binds.addStringBind, segment, text, font?.requireOpenHandle() ?: NULL_SEGMENT, fontSize, language, meta)
    }

    /**
     * Adds inline object to the text buffer, `key` must be unique. In the text, object is represented
     * as `length` object replacement characters.
     *
     * Generated from Godot docs: TextParagraph.add_object
     */
    fun addObject(key: Any?, size: Vector2, inlineAlign: InlineAlignment = InlineAlignment.CENTER, length: Int = 1, baseline: Double = 0.0): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithVariantVector2LongIntDoubleArgsRetBool(Binds.addObjectBind, segment, key, size, inlineAlign.value, length, baseline)
    }

    /**
     * Sets new size and alignment of embedded object.
     *
     * Generated from Godot docs: TextParagraph.resize_object
     */
    fun resizeObject(key: Any?, size: Vector2, inlineAlign: InlineAlignment = InlineAlignment.CENTER, baseline: Double = 0.0): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithVariantVector2LongDoubleArgsRetBool(Binds.resizeObjectBind, segment, key, size, inlineAlign.value, baseline)
    }

    /**
     * Returns `true` if an object with `key` is embedded in this shaped text buffer.
     *
     * Generated from Godot docs: TextParagraph.has_object
     */
    fun hasObject(key: Any?): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithVariantArgRetBool(Binds.hasObjectBind, segment, key)
    }

    /**
     * Paragraph horizontal alignment.
     *
     * Generated from Godot docs: TextParagraph.set_alignment
     */
    fun setAlignment(alignment: HorizontalAlignment) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setAlignmentBind, segment, alignment.value)
    }

    /**
     * Paragraph horizontal alignment.
     *
     * Generated from Godot docs: TextParagraph.get_alignment
     */
    fun getAlignment(): HorizontalAlignment {
        checkOpen()
        return HorizontalAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAlignmentBind, segment))
    }

    /**
     * Aligns paragraph to the given tab-stops.
     *
     * Generated from Godot docs: TextParagraph.tab_align
     */
    fun tabAlign(tabStops: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat32ListArg(Binds.tabAlignBind, segment, tabStops)
    }

    /**
     * Line breaking rules. For more info see `TextServer`.
     *
     * Generated from Godot docs: TextParagraph.set_break_flags
     */
    fun setBreakFlags(flags: TextServer.LineBreakFlag) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setBreakFlagsBind, segment, flags.value)
    }

    /**
     * Line breaking rules. For more info see `TextServer`.
     *
     * Generated from Godot docs: TextParagraph.get_break_flags
     */
    fun getBreakFlags(): TextServer.LineBreakFlag {
        checkOpen()
        return TextServer.LineBreakFlag(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBreakFlagsBind, segment))
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: TextParagraph.set_justification_flags
     */
    fun setJustificationFlags(flags: TextServer.JustificationFlag) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setJustificationFlagsBind, segment, flags.value)
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: TextParagraph.get_justification_flags
     */
    fun getJustificationFlags(): TextServer.JustificationFlag {
        checkOpen()
        return TextServer.JustificationFlag(ObjectCalls.ptrcallNoArgsRetLong(Binds.getJustificationFlagsBind, segment))
    }

    /**
     * The clipping behavior when the text exceeds the paragraph's set width.
     *
     * Generated from Godot docs: TextParagraph.set_text_overrun_behavior
     */
    fun setTextOverrunBehavior(overrunBehavior: TextServer.OverrunBehavior) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTextOverrunBehaviorBind, segment, overrunBehavior.value)
    }

    /**
     * The clipping behavior when the text exceeds the paragraph's set width.
     *
     * Generated from Godot docs: TextParagraph.get_text_overrun_behavior
     */
    fun getTextOverrunBehavior(): TextServer.OverrunBehavior {
        checkOpen()
        return TextServer.OverrunBehavior(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextOverrunBehaviorBind, segment))
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: TextParagraph.set_ellipsis_char
     */
    fun setEllipsisChar(char: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setEllipsisCharBind, segment, char)
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: TextParagraph.get_ellipsis_char
     */
    fun getEllipsisChar(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getEllipsisCharBind, segment)
    }

    /**
     * Paragraph width.
     *
     * Generated from Godot docs: TextParagraph.set_width
     */
    fun setWidth(width: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setWidthBind, segment, width)
    }

    /**
     * Paragraph width.
     *
     * Generated from Godot docs: TextParagraph.get_width
     */
    fun getWidth(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getWidthBind, segment)
    }

    /**
     * Returns the size of the bounding box of the paragraph, without line breaks.
     *
     * Generated from Godot docs: TextParagraph.get_non_wrapped_size
     */
    fun getNonWrappedSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getNonWrappedSizeBind, segment)
    }

    /**
     * Returns the size of the bounding box of the paragraph.
     *
     * Generated from Godot docs: TextParagraph.get_size
     */
    fun getSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getSizeBind, segment)
    }

    /**
     * Returns TextServer full string buffer RID.
     *
     * Generated from Godot docs: TextParagraph.get_rid
     */
    fun getRid(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getRidBind, segment)
    }

    /**
     * Returns TextServer line buffer RID.
     *
     * Generated from Godot docs: TextParagraph.get_line_rid
     */
    fun getLineRid(line: Int): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetRID(Binds.getLineRidBind, segment, line)
    }

    /**
     * Returns drop cap text buffer RID.
     *
     * Generated from Godot docs: TextParagraph.get_dropcap_rid
     */
    fun getDropcapRid(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getDropcapRidBind, segment)
    }

    /**
     * Returns the character range of the paragraph.
     *
     * Generated from Godot docs: TextParagraph.get_range
     */
    fun getRange(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(Binds.getRangeBind, segment)
    }

    /**
     * Returns number of lines in the paragraph.
     *
     * Generated from Godot docs: TextParagraph.get_line_count
     */
    fun getLineCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLineCountBind, segment)
    }

    /**
     * Limits the lines of text shown.
     *
     * Generated from Godot docs: TextParagraph.set_max_lines_visible
     */
    fun setMaxLinesVisible(maxLinesVisible: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxLinesVisibleBind, segment, maxLinesVisible)
    }

    /**
     * Limits the lines of text shown.
     *
     * Generated from Godot docs: TextParagraph.get_max_lines_visible
     */
    fun getMaxLinesVisible(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxLinesVisibleBind, segment)
    }

    /**
     * Additional vertical spacing between lines (in pixels), spacing is added to line descent. This
     * value can be negative.
     *
     * Generated from Godot docs: TextParagraph.set_line_spacing
     */
    fun setLineSpacing(lineSpacing: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLineSpacingBind, segment, lineSpacing)
    }

    /**
     * Additional vertical spacing between lines (in pixels), spacing is added to line descent. This
     * value can be negative.
     *
     * Generated from Godot docs: TextParagraph.get_line_spacing
     */
    fun getLineSpacing(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLineSpacingBind, segment)
    }

    /**
     * Returns array of inline objects in the line.
     *
     * Generated from Godot docs: TextParagraph.get_line_objects
     */
    fun getLineObjects(line: Int): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetArray(Binds.getLineObjectsBind, segment, line)
    }

    /**
     * Returns bounding rectangle of the inline object.
     *
     * Generated from Godot docs: TextParagraph.get_line_object_rect
     */
    fun getLineObjectRect(line: Int, key: Any?): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndVariantArgRetRect2(Binds.getLineObjectRectBind, segment, line, key)
    }

    /**
     * Returns size of the bounding box of the line of text. Returned size is rounded up.
     *
     * Generated from Godot docs: TextParagraph.get_line_size
     */
    fun getLineSize(line: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getLineSizeBind, segment, line)
    }

    /**
     * Returns character range of the line.
     *
     * Generated from Godot docs: TextParagraph.get_line_range
     */
    fun getLineRange(line: Int): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector2i(Binds.getLineRangeBind, segment, line)
    }

    /**
     * Returns the text line ascent (number of pixels above the baseline for horizontal layout or to
     * the left of baseline for vertical).
     *
     * Generated from Godot docs: TextParagraph.get_line_ascent
     */
    fun getLineAscent(line: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getLineAscentBind, segment, line)
    }

    /**
     * Returns the text line descent (number of pixels below the baseline for horizontal layout or to
     * the right of baseline for vertical).
     *
     * Generated from Godot docs: TextParagraph.get_line_descent
     */
    fun getLineDescent(line: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getLineDescentBind, segment, line)
    }

    /**
     * Returns width (for horizontal layout) or height (for vertical) of the line of text.
     *
     * Generated from Godot docs: TextParagraph.get_line_width
     */
    fun getLineWidth(line: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getLineWidthBind, segment, line)
    }

    /**
     * Returns pixel offset of the underline below the baseline.
     *
     * Generated from Godot docs: TextParagraph.get_line_underline_position
     */
    fun getLineUnderlinePosition(line: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getLineUnderlinePositionBind, segment, line)
    }

    /**
     * Returns thickness of the underline.
     *
     * Generated from Godot docs: TextParagraph.get_line_underline_thickness
     */
    fun getLineUnderlineThickness(line: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getLineUnderlineThicknessBind, segment, line)
    }

    /**
     * Returns drop cap bounding box size.
     *
     * Generated from Godot docs: TextParagraph.get_dropcap_size
     */
    fun getDropcapSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getDropcapSizeBind, segment)
    }

    /**
     * Returns number of lines used by dropcap.
     *
     * Generated from Godot docs: TextParagraph.get_dropcap_lines
     */
    fun getDropcapLines(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getDropcapLinesBind, segment)
    }

    /**
     * Draw all lines of the text and drop cap into a canvas item at a given position, with `color`.
     * `pos` specifies the top left corner of the bounding box. If `oversampling` is greater than zero,
     * it is used as font oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextParagraph.draw
     */
    fun draw(canvas: RID, pos: Vector2, color: Color, dcColor: Color, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2TwoColorDoubleArgs(Binds.drawBind, segment, canvas, pos, color, dcColor, oversampling)
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
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2IntTwoColorDoubleArgs(Binds.drawOutlineBind, segment, canvas, pos, outlineSize, color, dcColor, oversampling)
    }

    /**
     * Draw single line of text into a canvas item at a given position, with `color`. `pos` specifies
     * the top left corner of the bounding box. If `oversampling` is greater than zero, it is used as
     * font oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextParagraph.draw_line
     */
    fun drawLine(canvas: RID, pos: Vector2, line: Int, color: Color, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2IntColorDoubleArgs(Binds.drawLineBind, segment, canvas, pos, line, color, oversampling)
    }

    /**
     * Draw outline of the single line of text into a canvas item at a given position, with `color`.
     * `pos` specifies the top left corner of the bounding box. If `oversampling` is greater than zero,
     * it is used as font oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextParagraph.draw_line_outline
     */
    fun drawLineOutline(canvas: RID, pos: Vector2, line: Int, outlineSize: Int = 1, color: Color, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2TwoIntColorDoubleArgs(Binds.drawLineOutlineBind, segment, canvas, pos, line, outlineSize, color, oversampling)
    }

    /**
     * Draw drop cap into a canvas item at a given position, with `color`. `pos` specifies the top left
     * corner of the bounding box. If `oversampling` is greater than zero, it is used as font
     * oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextParagraph.draw_dropcap
     */
    fun drawDropcap(canvas: RID, pos: Vector2, color: Color, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2ColorDoubleArgs(Binds.drawDropcapBind, segment, canvas, pos, color, oversampling)
    }

    /**
     * Draw drop cap outline into a canvas item at a given position, with `color`. `pos` specifies the
     * top left corner of the bounding box. If `oversampling` is greater than zero, it is used as font
     * oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextParagraph.draw_dropcap_outline
     */
    fun drawDropcapOutline(canvas: RID, pos: Vector2, outlineSize: Int = 1, color: Color, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2IntColorDoubleArgs(Binds.drawDropcapOutlineBind, segment, canvas, pos, outlineSize, color, oversampling)
    }

    /**
     * Returns caret character offset at the specified coordinates. This function always returns a
     * valid position.
     *
     * Generated from Godot docs: TextParagraph.hit_test
     */
    fun hitTest(coords: Vector2): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2ArgRetInt(Binds.hitTestBind, segment, coords)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextParagraph? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): TextParagraph? =
            if (handle.address() == 0L) null else RefCounted.owned(TextParagraph(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): TextParagraph? =
            if (handle.address() == 0L) null else TextParagraph(GodotHandle(handle))
    }

    private object Binds {
        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("TextParagraph", "clear", CLEAR_HASH)

        private const val DUPLICATE_HASH = 3607706709L
        @JvmField
        val duplicateBind =
            ObjectCalls.getMethodBind("TextParagraph", "duplicate", DUPLICATE_HASH)

        private const val SET_DIRECTION_HASH = 1418190634L
        @JvmField
        val setDirectionBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_direction", SET_DIRECTION_HASH)

        private const val GET_DIRECTION_HASH = 2516697328L
        @JvmField
        val getDirectionBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_direction", GET_DIRECTION_HASH)

        private const val GET_INFERRED_DIRECTION_HASH = 2516697328L
        @JvmField
        val getInferredDirectionBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_inferred_direction", GET_INFERRED_DIRECTION_HASH)

        private const val SET_CUSTOM_PUNCTUATION_HASH = 83702148L
        @JvmField
        val setCustomPunctuationBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_custom_punctuation", SET_CUSTOM_PUNCTUATION_HASH)

        private const val GET_CUSTOM_PUNCTUATION_HASH = 201670096L
        @JvmField
        val getCustomPunctuationBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_custom_punctuation", GET_CUSTOM_PUNCTUATION_HASH)

        private const val SET_ORIENTATION_HASH = 42823726L
        @JvmField
        val setOrientationBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_orientation", SET_ORIENTATION_HASH)

        private const val GET_ORIENTATION_HASH = 175768116L
        @JvmField
        val getOrientationBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_orientation", GET_ORIENTATION_HASH)

        private const val SET_PRESERVE_INVALID_HASH = 2586408642L
        @JvmField
        val setPreserveInvalidBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_preserve_invalid", SET_PRESERVE_INVALID_HASH)

        private const val GET_PRESERVE_INVALID_HASH = 36873697L
        @JvmField
        val getPreserveInvalidBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_preserve_invalid", GET_PRESERVE_INVALID_HASH)

        private const val SET_PRESERVE_CONTROL_HASH = 2586408642L
        @JvmField
        val setPreserveControlBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_preserve_control", SET_PRESERVE_CONTROL_HASH)

        private const val GET_PRESERVE_CONTROL_HASH = 36873697L
        @JvmField
        val getPreserveControlBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_preserve_control", GET_PRESERVE_CONTROL_HASH)

        private const val SET_BIDI_OVERRIDE_HASH = 381264803L
        @JvmField
        val setBidiOverrideBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_bidi_override", SET_BIDI_OVERRIDE_HASH)

        private const val SET_DROPCAP_HASH = 2498990330L
        @JvmField
        val setDropcapBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_dropcap", SET_DROPCAP_HASH)

        private const val CLEAR_DROPCAP_HASH = 3218959716L
        @JvmField
        val clearDropcapBind =
            ObjectCalls.getMethodBind("TextParagraph", "clear_dropcap", CLEAR_DROPCAP_HASH)

        private const val ADD_STRING_HASH = 621426851L
        @JvmField
        val addStringBind =
            ObjectCalls.getMethodBind("TextParagraph", "add_string", ADD_STRING_HASH)

        private const val ADD_OBJECT_HASH = 1316529304L
        @JvmField
        val addObjectBind =
            ObjectCalls.getMethodBind("TextParagraph", "add_object", ADD_OBJECT_HASH)

        private const val RESIZE_OBJECT_HASH = 2095776372L
        @JvmField
        val resizeObjectBind =
            ObjectCalls.getMethodBind("TextParagraph", "resize_object", RESIZE_OBJECT_HASH)

        private const val HAS_OBJECT_HASH = 77467830L
        @JvmField
        val hasObjectBind =
            ObjectCalls.getMethodBind("TextParagraph", "has_object", HAS_OBJECT_HASH)

        private const val SET_ALIGNMENT_HASH = 2312603777L
        @JvmField
        val setAlignmentBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_alignment", SET_ALIGNMENT_HASH)

        private const val GET_ALIGNMENT_HASH = 341400642L
        @JvmField
        val getAlignmentBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_alignment", GET_ALIGNMENT_HASH)

        private const val TAB_ALIGN_HASH = 2899603908L
        @JvmField
        val tabAlignBind =
            ObjectCalls.getMethodBind("TextParagraph", "tab_align", TAB_ALIGN_HASH)

        private const val SET_BREAK_FLAGS_HASH = 2809697122L
        @JvmField
        val setBreakFlagsBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_break_flags", SET_BREAK_FLAGS_HASH)

        private const val GET_BREAK_FLAGS_HASH = 2340632602L
        @JvmField
        val getBreakFlagsBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_break_flags", GET_BREAK_FLAGS_HASH)

        private const val SET_JUSTIFICATION_FLAGS_HASH = 2877345813L
        @JvmField
        val setJustificationFlagsBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_justification_flags", SET_JUSTIFICATION_FLAGS_HASH)

        private const val GET_JUSTIFICATION_FLAGS_HASH = 1583363614L
        @JvmField
        val getJustificationFlagsBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_justification_flags", GET_JUSTIFICATION_FLAGS_HASH)

        private const val SET_TEXT_OVERRUN_BEHAVIOR_HASH = 1008890932L
        @JvmField
        val setTextOverrunBehaviorBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_text_overrun_behavior", SET_TEXT_OVERRUN_BEHAVIOR_HASH)

        private const val GET_TEXT_OVERRUN_BEHAVIOR_HASH = 3779142101L
        @JvmField
        val getTextOverrunBehaviorBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_text_overrun_behavior", GET_TEXT_OVERRUN_BEHAVIOR_HASH)

        private const val SET_ELLIPSIS_CHAR_HASH = 83702148L
        @JvmField
        val setEllipsisCharBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_ellipsis_char", SET_ELLIPSIS_CHAR_HASH)

        private const val GET_ELLIPSIS_CHAR_HASH = 201670096L
        @JvmField
        val getEllipsisCharBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_ellipsis_char", GET_ELLIPSIS_CHAR_HASH)

        private const val SET_WIDTH_HASH = 373806689L
        @JvmField
        val setWidthBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_width", SET_WIDTH_HASH)

        private const val GET_WIDTH_HASH = 1740695150L
        @JvmField
        val getWidthBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_width", GET_WIDTH_HASH)

        private const val GET_NON_WRAPPED_SIZE_HASH = 3341600327L
        @JvmField
        val getNonWrappedSizeBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_non_wrapped_size", GET_NON_WRAPPED_SIZE_HASH)

        private const val GET_SIZE_HASH = 3341600327L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_size", GET_SIZE_HASH)

        private const val GET_RID_HASH = 2944877500L
        @JvmField
        val getRidBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_rid", GET_RID_HASH)

        private const val GET_LINE_RID_HASH = 495598643L
        @JvmField
        val getLineRidBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_line_rid", GET_LINE_RID_HASH)

        private const val GET_DROPCAP_RID_HASH = 2944877500L
        @JvmField
        val getDropcapRidBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_dropcap_rid", GET_DROPCAP_RID_HASH)

        private const val GET_RANGE_HASH = 3690982128L
        @JvmField
        val getRangeBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_range", GET_RANGE_HASH)

        private const val GET_LINE_COUNT_HASH = 3905245786L
        @JvmField
        val getLineCountBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_line_count", GET_LINE_COUNT_HASH)

        private const val SET_MAX_LINES_VISIBLE_HASH = 1286410249L
        @JvmField
        val setMaxLinesVisibleBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_max_lines_visible", SET_MAX_LINES_VISIBLE_HASH)

        private const val GET_MAX_LINES_VISIBLE_HASH = 3905245786L
        @JvmField
        val getMaxLinesVisibleBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_max_lines_visible", GET_MAX_LINES_VISIBLE_HASH)

        private const val SET_LINE_SPACING_HASH = 373806689L
        @JvmField
        val setLineSpacingBind =
            ObjectCalls.getMethodBind("TextParagraph", "set_line_spacing", SET_LINE_SPACING_HASH)

        private const val GET_LINE_SPACING_HASH = 1740695150L
        @JvmField
        val getLineSpacingBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_line_spacing", GET_LINE_SPACING_HASH)

        private const val GET_LINE_OBJECTS_HASH = 663333327L
        @JvmField
        val getLineObjectsBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_line_objects", GET_LINE_OBJECTS_HASH)

        private const val GET_LINE_OBJECT_RECT_HASH = 204315017L
        @JvmField
        val getLineObjectRectBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_line_object_rect", GET_LINE_OBJECT_RECT_HASH)

        private const val GET_LINE_SIZE_HASH = 2299179447L
        @JvmField
        val getLineSizeBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_line_size", GET_LINE_SIZE_HASH)

        private const val GET_LINE_RANGE_HASH = 880721226L
        @JvmField
        val getLineRangeBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_line_range", GET_LINE_RANGE_HASH)

        private const val GET_LINE_ASCENT_HASH = 2339986948L
        @JvmField
        val getLineAscentBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_line_ascent", GET_LINE_ASCENT_HASH)

        private const val GET_LINE_DESCENT_HASH = 2339986948L
        @JvmField
        val getLineDescentBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_line_descent", GET_LINE_DESCENT_HASH)

        private const val GET_LINE_WIDTH_HASH = 2339986948L
        @JvmField
        val getLineWidthBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_line_width", GET_LINE_WIDTH_HASH)

        private const val GET_LINE_UNDERLINE_POSITION_HASH = 2339986948L
        @JvmField
        val getLineUnderlinePositionBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_line_underline_position", GET_LINE_UNDERLINE_POSITION_HASH)

        private const val GET_LINE_UNDERLINE_THICKNESS_HASH = 2339986948L
        @JvmField
        val getLineUnderlineThicknessBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_line_underline_thickness", GET_LINE_UNDERLINE_THICKNESS_HASH)

        private const val GET_DROPCAP_SIZE_HASH = 3341600327L
        @JvmField
        val getDropcapSizeBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_dropcap_size", GET_DROPCAP_SIZE_HASH)

        private const val GET_DROPCAP_LINES_HASH = 3905245786L
        @JvmField
        val getDropcapLinesBind =
            ObjectCalls.getMethodBind("TextParagraph", "get_dropcap_lines", GET_DROPCAP_LINES_HASH)

        private const val DRAW_HASH = 1492808103L
        @JvmField
        val drawBind =
            ObjectCalls.getMethodBind("TextParagraph", "draw", DRAW_HASH)

        private const val DRAW_OUTLINE_HASH = 3820500590L
        @JvmField
        val drawOutlineBind =
            ObjectCalls.getMethodBind("TextParagraph", "draw_outline", DRAW_OUTLINE_HASH)

        private const val DRAW_LINE_HASH = 828033758L
        @JvmField
        val drawLineBind =
            ObjectCalls.getMethodBind("TextParagraph", "draw_line", DRAW_LINE_HASH)

        private const val DRAW_LINE_OUTLINE_HASH = 2822696703L
        @JvmField
        val drawLineOutlineBind =
            ObjectCalls.getMethodBind("TextParagraph", "draw_line_outline", DRAW_LINE_OUTLINE_HASH)

        private const val DRAW_DROPCAP_HASH = 3625105422L
        @JvmField
        val drawDropcapBind =
            ObjectCalls.getMethodBind("TextParagraph", "draw_dropcap", DRAW_DROPCAP_HASH)

        private const val DRAW_DROPCAP_OUTLINE_HASH = 2592177763L
        @JvmField
        val drawDropcapOutlineBind =
            ObjectCalls.getMethodBind("TextParagraph", "draw_dropcap_outline", DRAW_DROPCAP_OUTLINE_HASH)

        private const val HIT_TEST_HASH = 3820158470L
        @JvmField
        val hitTestBind =
            ObjectCalls.getMethodBind("TextParagraph", "hit_test", HIT_TEST_HASH)
    }
}
