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

/**
 * Holds a line of text.
 *
 * Generated from Godot docs: TextLine
 */
class TextLine(handle: GodotHandle) : RefCounted(handle) {
    var direction: TextServer.Direction
        @JvmName("directionProperty")
        get() = getDirection()
        @JvmName("setDirectionProperty")
        set(value) = setDirection(value)

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

    var width: Double
        @JvmName("widthProperty")
        get() = getWidth()
        @JvmName("setWidthProperty")
        set(value) = setWidth(value)

    var alignment: HorizontalAlignment
        @JvmName("alignmentProperty")
        get() = getHorizontalAlignment()
        @JvmName("setAlignmentProperty")
        set(value) = setHorizontalAlignment(value)

    var flags: TextServer.JustificationFlag
        @JvmName("flagsProperty")
        get() = getFlags()
        @JvmName("setFlagsProperty")
        set(value) = setFlags(value)

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

    /**
     * Clears text line (removes text and inline objects).
     *
     * Generated from Godot docs: TextLine.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
    }

    /**
     * Duplicates this `TextLine`.
     *
     * Generated from Godot docs: TextLine.duplicate
     */
    fun duplicate(): TextLine? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(Binds.duplicateBind, segment)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return TextLine.wrapOwned(ret)
    }

    /**
     * Text writing direction.
     *
     * Generated from Godot docs: TextLine.set_direction
     */
    fun setDirection(direction: TextServer.Direction) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setDirectionBind, segment, direction.value)
    }

    /**
     * Text writing direction.
     *
     * Generated from Godot docs: TextLine.get_direction
     */
    fun getDirection(): TextServer.Direction {
        checkOpen()
        return TextServer.Direction(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDirectionBind, segment))
    }

    /**
     * Returns the text writing direction inferred by the BiDi algorithm.
     *
     * Generated from Godot docs: TextLine.get_inferred_direction
     */
    fun getInferredDirection(): TextServer.Direction {
        checkOpen()
        return TextServer.Direction(ObjectCalls.ptrcallNoArgsRetLong(Binds.getInferredDirectionBind, segment))
    }

    /**
     * Text orientation.
     *
     * Generated from Godot docs: TextLine.set_orientation
     */
    fun setOrientation(orientation: TextServer.Orientation) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setOrientationBind, segment, orientation.value)
    }

    /**
     * Text orientation.
     *
     * Generated from Godot docs: TextLine.get_orientation
     */
    fun getOrientation(): TextServer.Orientation {
        checkOpen()
        return TextServer.Orientation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOrientationBind, segment))
    }

    /**
     * If set to `true` text will display invalid characters.
     *
     * Generated from Godot docs: TextLine.set_preserve_invalid
     */
    fun setPreserveInvalid(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPreserveInvalidBind, segment, enabled)
    }

    /**
     * If set to `true` text will display invalid characters.
     *
     * Generated from Godot docs: TextLine.get_preserve_invalid
     */
    fun getPreserveInvalid(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getPreserveInvalidBind, segment)
    }

    /**
     * If set to `true` text will display control characters.
     *
     * Generated from Godot docs: TextLine.set_preserve_control
     */
    fun setPreserveControl(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setPreserveControlBind, segment, enabled)
    }

    /**
     * If set to `true` text will display control characters.
     *
     * Generated from Godot docs: TextLine.get_preserve_control
     */
    fun getPreserveControl(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getPreserveControlBind, segment)
    }

    /**
     * Overrides BiDi for the structured text. Override ranges should cover full source text without
     * overlaps. BiDi algorithm will be used on each range separately.
     *
     * Generated from Godot docs: TextLine.set_bidi_override
     */
    fun setBidiOverride(override: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithArrayArg(Binds.setBidiOverrideBind, segment, override)
    }

    /**
     * Adds text span and font to draw it.
     *
     * Generated from Godot docs: TextLine.add_string
     */
    fun addString(text: String, font: Font?, fontSize: Int, language: String = "", meta: Any? = null): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringObjectIntStringVariantArgsRetBool(Binds.addStringBind, segment, text, font?.requireOpenHandle() ?: NULL_SEGMENT, fontSize, language, meta)
    }

    /**
     * Adds inline object to the text buffer, `key` must be unique. In the text, object is represented
     * as `length` object replacement characters.
     *
     * Generated from Godot docs: TextLine.add_object
     */
    fun addObject(key: Any?, size: Vector2, inlineAlign: InlineAlignment = InlineAlignment.CENTER, length: Int = 1, baseline: Double = 0.0): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithVariantVector2LongIntDoubleArgsRetBool(Binds.addObjectBind, segment, key, size, inlineAlign.value, length, baseline)
    }

    /**
     * Sets new size and alignment of embedded object.
     *
     * Generated from Godot docs: TextLine.resize_object
     */
    fun resizeObject(key: Any?, size: Vector2, inlineAlign: InlineAlignment = InlineAlignment.CENTER, baseline: Double = 0.0): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithVariantVector2LongDoubleArgsRetBool(Binds.resizeObjectBind, segment, key, size, inlineAlign.value, baseline)
    }

    /**
     * Returns `true` if an object with `key` is embedded in this line.
     *
     * Generated from Godot docs: TextLine.has_object
     */
    fun hasObject(key: Any?): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithVariantArgRetBool(Binds.hasObjectBind, segment, key)
    }

    /**
     * Text line width.
     *
     * Generated from Godot docs: TextLine.set_width
     */
    fun setWidth(width: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setWidthBind, segment, width)
    }

    /**
     * Text line width.
     *
     * Generated from Godot docs: TextLine.get_width
     */
    fun getWidth(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getWidthBind, segment)
    }

    /**
     * Sets text alignment within the line as if the line was horizontal.
     *
     * Generated from Godot docs: TextLine.set_horizontal_alignment
     */
    fun setHorizontalAlignment(alignment: HorizontalAlignment) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setHorizontalAlignmentBind, segment, alignment.value)
    }

    /**
     * Sets text alignment within the line as if the line was horizontal.
     *
     * Generated from Godot docs: TextLine.get_horizontal_alignment
     */
    fun getHorizontalAlignment(): HorizontalAlignment {
        checkOpen()
        return HorizontalAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHorizontalAlignmentBind, segment))
    }

    /**
     * Aligns text to the given tab-stops.
     *
     * Generated from Godot docs: TextLine.tab_align
     */
    fun tabAlign(tabStops: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat32ListArg(Binds.tabAlignBind, segment, tabStops)
    }

    /**
     * Line alignment rules. For more info see `TextServer`.
     *
     * Generated from Godot docs: TextLine.set_flags
     */
    fun setFlags(flags: TextServer.JustificationFlag) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFlagsBind, segment, flags.value)
    }

    /**
     * Line alignment rules. For more info see `TextServer`.
     *
     * Generated from Godot docs: TextLine.get_flags
     */
    fun getFlags(): TextServer.JustificationFlag {
        checkOpen()
        return TextServer.JustificationFlag(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFlagsBind, segment))
    }

    /**
     * The clipping behavior when the text exceeds the text line's set width.
     *
     * Generated from Godot docs: TextLine.set_text_overrun_behavior
     */
    fun setTextOverrunBehavior(overrunBehavior: TextServer.OverrunBehavior) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setTextOverrunBehaviorBind, segment, overrunBehavior.value)
    }

    /**
     * The clipping behavior when the text exceeds the text line's set width.
     *
     * Generated from Godot docs: TextLine.get_text_overrun_behavior
     */
    fun getTextOverrunBehavior(): TextServer.OverrunBehavior {
        checkOpen()
        return TextServer.OverrunBehavior(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextOverrunBehaviorBind, segment))
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: TextLine.set_ellipsis_char
     */
    fun setEllipsisChar(char: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setEllipsisCharBind, segment, char)
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: TextLine.get_ellipsis_char
     */
    fun getEllipsisChar(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getEllipsisCharBind, segment)
    }

    /**
     * Returns array of inline objects.
     *
     * Generated from Godot docs: TextLine.get_objects
     */
    fun getObjects(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getObjectsBind, segment)
    }

    /**
     * Returns bounding rectangle of the inline object.
     *
     * Generated from Godot docs: TextLine.get_object_rect
     */
    fun getObjectRect(key: Any?): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallWithVariantArgRetRect2(Binds.getObjectRectBind, segment, key)
    }

    /**
     * Returns size of the bounding box of the text.
     *
     * Generated from Godot docs: TextLine.get_size
     */
    fun getSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getSizeBind, segment)
    }

    /**
     * Returns TextServer buffer RID.
     *
     * Generated from Godot docs: TextLine.get_rid
     */
    fun getRid(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getRidBind, segment)
    }

    /**
     * Returns the text ascent (number of pixels above the baseline for horizontal layout or to the
     * left of baseline for vertical).
     *
     * Generated from Godot docs: TextLine.get_line_ascent
     */
    fun getLineAscent(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLineAscentBind, segment)
    }

    /**
     * Returns the text descent (number of pixels below the baseline for horizontal layout or to the
     * right of baseline for vertical).
     *
     * Generated from Godot docs: TextLine.get_line_descent
     */
    fun getLineDescent(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLineDescentBind, segment)
    }

    /**
     * Returns width (for horizontal layout) or height (for vertical) of the text.
     *
     * Generated from Godot docs: TextLine.get_line_width
     */
    fun getLineWidth(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLineWidthBind, segment)
    }

    /**
     * Returns pixel offset of the underline below the baseline.
     *
     * Generated from Godot docs: TextLine.get_line_underline_position
     */
    fun getLineUnderlinePosition(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLineUnderlinePositionBind, segment)
    }

    /**
     * Returns thickness of the underline.
     *
     * Generated from Godot docs: TextLine.get_line_underline_thickness
     */
    fun getLineUnderlineThickness(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLineUnderlineThicknessBind, segment)
    }

    /**
     * Draw text into a canvas item at a given position, with `color`. `pos` specifies the top left
     * corner of the bounding box. If `oversampling` is greater than zero, it is used as font
     * oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextLine.draw
     */
    fun draw(canvas: RID, pos: Vector2, color: Color, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2ColorDoubleArgs(Binds.drawBind, segment, canvas, pos, color, oversampling)
    }

    /**
     * Draw text into a canvas item at a given position, with `color`. `pos` specifies the top left
     * corner of the bounding box. If `oversampling` is greater than zero, it is used as font
     * oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextLine.draw_outline
     */
    fun drawOutline(canvas: RID, pos: Vector2, outlineSize: Int = 1, color: Color, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2IntColorDoubleArgs(Binds.drawOutlineBind, segment, canvas, pos, outlineSize, color, oversampling)
    }

    /**
     * Returns caret character offset at the specified pixel offset at the baseline. This function
     * always returns a valid position.
     *
     * Generated from Godot docs: TextLine.hit_test
     */
    fun hitTest(coords: Double): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithDoubleArgRetInt(Binds.hitTestBind, segment, coords)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextLine? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): TextLine? =
            if (handle.address() == 0L) null else RefCounted.owned(TextLine(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): TextLine? =
            if (handle.address() == 0L) null else TextLine(GodotHandle(handle))
    }

    private object Binds {
        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("TextLine", "clear", CLEAR_HASH)

        private const val DUPLICATE_HASH = 1912703884L
        @JvmField
        val duplicateBind =
            ObjectCalls.getMethodBind("TextLine", "duplicate", DUPLICATE_HASH)

        private const val SET_DIRECTION_HASH = 1418190634L
        @JvmField
        val setDirectionBind =
            ObjectCalls.getMethodBind("TextLine", "set_direction", SET_DIRECTION_HASH)

        private const val GET_DIRECTION_HASH = 2516697328L
        @JvmField
        val getDirectionBind =
            ObjectCalls.getMethodBind("TextLine", "get_direction", GET_DIRECTION_HASH)

        private const val GET_INFERRED_DIRECTION_HASH = 2516697328L
        @JvmField
        val getInferredDirectionBind =
            ObjectCalls.getMethodBind("TextLine", "get_inferred_direction", GET_INFERRED_DIRECTION_HASH)

        private const val SET_ORIENTATION_HASH = 42823726L
        @JvmField
        val setOrientationBind =
            ObjectCalls.getMethodBind("TextLine", "set_orientation", SET_ORIENTATION_HASH)

        private const val GET_ORIENTATION_HASH = 175768116L
        @JvmField
        val getOrientationBind =
            ObjectCalls.getMethodBind("TextLine", "get_orientation", GET_ORIENTATION_HASH)

        private const val SET_PRESERVE_INVALID_HASH = 2586408642L
        @JvmField
        val setPreserveInvalidBind =
            ObjectCalls.getMethodBind("TextLine", "set_preserve_invalid", SET_PRESERVE_INVALID_HASH)

        private const val GET_PRESERVE_INVALID_HASH = 36873697L
        @JvmField
        val getPreserveInvalidBind =
            ObjectCalls.getMethodBind("TextLine", "get_preserve_invalid", GET_PRESERVE_INVALID_HASH)

        private const val SET_PRESERVE_CONTROL_HASH = 2586408642L
        @JvmField
        val setPreserveControlBind =
            ObjectCalls.getMethodBind("TextLine", "set_preserve_control", SET_PRESERVE_CONTROL_HASH)

        private const val GET_PRESERVE_CONTROL_HASH = 36873697L
        @JvmField
        val getPreserveControlBind =
            ObjectCalls.getMethodBind("TextLine", "get_preserve_control", GET_PRESERVE_CONTROL_HASH)

        private const val SET_BIDI_OVERRIDE_HASH = 381264803L
        @JvmField
        val setBidiOverrideBind =
            ObjectCalls.getMethodBind("TextLine", "set_bidi_override", SET_BIDI_OVERRIDE_HASH)

        private const val ADD_STRING_HASH = 621426851L
        @JvmField
        val addStringBind =
            ObjectCalls.getMethodBind("TextLine", "add_string", ADD_STRING_HASH)

        private const val ADD_OBJECT_HASH = 1316529304L
        @JvmField
        val addObjectBind =
            ObjectCalls.getMethodBind("TextLine", "add_object", ADD_OBJECT_HASH)

        private const val RESIZE_OBJECT_HASH = 2095776372L
        @JvmField
        val resizeObjectBind =
            ObjectCalls.getMethodBind("TextLine", "resize_object", RESIZE_OBJECT_HASH)

        private const val HAS_OBJECT_HASH = 77467830L
        @JvmField
        val hasObjectBind =
            ObjectCalls.getMethodBind("TextLine", "has_object", HAS_OBJECT_HASH)

        private const val SET_WIDTH_HASH = 373806689L
        @JvmField
        val setWidthBind =
            ObjectCalls.getMethodBind("TextLine", "set_width", SET_WIDTH_HASH)

        private const val GET_WIDTH_HASH = 1740695150L
        @JvmField
        val getWidthBind =
            ObjectCalls.getMethodBind("TextLine", "get_width", GET_WIDTH_HASH)

        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        @JvmField
        val setHorizontalAlignmentBind =
            ObjectCalls.getMethodBind("TextLine", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)

        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        @JvmField
        val getHorizontalAlignmentBind =
            ObjectCalls.getMethodBind("TextLine", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)

        private const val TAB_ALIGN_HASH = 2899603908L
        @JvmField
        val tabAlignBind =
            ObjectCalls.getMethodBind("TextLine", "tab_align", TAB_ALIGN_HASH)

        private const val SET_FLAGS_HASH = 2877345813L
        @JvmField
        val setFlagsBind =
            ObjectCalls.getMethodBind("TextLine", "set_flags", SET_FLAGS_HASH)

        private const val GET_FLAGS_HASH = 1583363614L
        @JvmField
        val getFlagsBind =
            ObjectCalls.getMethodBind("TextLine", "get_flags", GET_FLAGS_HASH)

        private const val SET_TEXT_OVERRUN_BEHAVIOR_HASH = 1008890932L
        @JvmField
        val setTextOverrunBehaviorBind =
            ObjectCalls.getMethodBind("TextLine", "set_text_overrun_behavior", SET_TEXT_OVERRUN_BEHAVIOR_HASH)

        private const val GET_TEXT_OVERRUN_BEHAVIOR_HASH = 3779142101L
        @JvmField
        val getTextOverrunBehaviorBind =
            ObjectCalls.getMethodBind("TextLine", "get_text_overrun_behavior", GET_TEXT_OVERRUN_BEHAVIOR_HASH)

        private const val SET_ELLIPSIS_CHAR_HASH = 83702148L
        @JvmField
        val setEllipsisCharBind =
            ObjectCalls.getMethodBind("TextLine", "set_ellipsis_char", SET_ELLIPSIS_CHAR_HASH)

        private const val GET_ELLIPSIS_CHAR_HASH = 201670096L
        @JvmField
        val getEllipsisCharBind =
            ObjectCalls.getMethodBind("TextLine", "get_ellipsis_char", GET_ELLIPSIS_CHAR_HASH)

        private const val GET_OBJECTS_HASH = 3995934104L
        @JvmField
        val getObjectsBind =
            ObjectCalls.getMethodBind("TextLine", "get_objects", GET_OBJECTS_HASH)

        private const val GET_OBJECT_RECT_HASH = 1742700391L
        @JvmField
        val getObjectRectBind =
            ObjectCalls.getMethodBind("TextLine", "get_object_rect", GET_OBJECT_RECT_HASH)

        private const val GET_SIZE_HASH = 3341600327L
        @JvmField
        val getSizeBind =
            ObjectCalls.getMethodBind("TextLine", "get_size", GET_SIZE_HASH)

        private const val GET_RID_HASH = 2944877500L
        @JvmField
        val getRidBind =
            ObjectCalls.getMethodBind("TextLine", "get_rid", GET_RID_HASH)

        private const val GET_LINE_ASCENT_HASH = 1740695150L
        @JvmField
        val getLineAscentBind =
            ObjectCalls.getMethodBind("TextLine", "get_line_ascent", GET_LINE_ASCENT_HASH)

        private const val GET_LINE_DESCENT_HASH = 1740695150L
        @JvmField
        val getLineDescentBind =
            ObjectCalls.getMethodBind("TextLine", "get_line_descent", GET_LINE_DESCENT_HASH)

        private const val GET_LINE_WIDTH_HASH = 1740695150L
        @JvmField
        val getLineWidthBind =
            ObjectCalls.getMethodBind("TextLine", "get_line_width", GET_LINE_WIDTH_HASH)

        private const val GET_LINE_UNDERLINE_POSITION_HASH = 1740695150L
        @JvmField
        val getLineUnderlinePositionBind =
            ObjectCalls.getMethodBind("TextLine", "get_line_underline_position", GET_LINE_UNDERLINE_POSITION_HASH)

        private const val GET_LINE_UNDERLINE_THICKNESS_HASH = 1740695150L
        @JvmField
        val getLineUnderlineThicknessBind =
            ObjectCalls.getMethodBind("TextLine", "get_line_underline_thickness", GET_LINE_UNDERLINE_THICKNESS_HASH)

        private const val DRAW_HASH = 3625105422L
        @JvmField
        val drawBind =
            ObjectCalls.getMethodBind("TextLine", "draw", DRAW_HASH)

        private const val DRAW_OUTLINE_HASH = 2592177763L
        @JvmField
        val drawOutlineBind =
            ObjectCalls.getMethodBind("TextLine", "draw_outline", DRAW_OUTLINE_HASH)

        private const val HIT_TEST_HASH = 2401831903L
        @JvmField
        val hitTestBind =
            ObjectCalls.getMethodBind("TextLine", "hit_test", HIT_TEST_HASH)
    }
}
