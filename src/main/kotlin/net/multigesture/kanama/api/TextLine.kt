package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * Holds a line of text.
 *
 * Generated from Godot docs: TextLine
 */
class TextLine(handle: MemorySegment) : RefCounted(handle) {
    var direction: Long
        @JvmName("directionProperty")
        get() = getDirection()
        @JvmName("setDirectionProperty")
        set(value) = setDirection(value)

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

    var width: Double
        @JvmName("widthProperty")
        get() = getWidth()
        @JvmName("setWidthProperty")
        set(value) = setWidth(value)

    var alignment: Long
        @JvmName("alignmentProperty")
        get() = getHorizontalAlignment()
        @JvmName("setAlignmentProperty")
        set(value) = setHorizontalAlignment(value)

    var flags: Long
        @JvmName("flagsProperty")
        get() = getFlags()
        @JvmName("setFlagsProperty")
        set(value) = setFlags(value)

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

    /**
     * Clears text line (removes text and inline objects).
     *
     * Generated from Godot docs: TextLine.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Duplicates this `TextLine`.
     *
     * Generated from Godot docs: TextLine.duplicate
     */
    fun duplicate(): TextLine? {
        return TextLine.wrap(ObjectCalls.ptrcallNoArgsRetObject(duplicateBind, handle))
    }

    /**
     * Text writing direction.
     *
     * Generated from Godot docs: TextLine.set_direction
     */
    fun setDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setDirectionBind, handle, direction)
    }

    /**
     * Text writing direction.
     *
     * Generated from Godot docs: TextLine.get_direction
     */
    fun getDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDirectionBind, handle)
    }

    /**
     * Returns the text writing direction inferred by the BiDi algorithm.
     *
     * Generated from Godot docs: TextLine.get_inferred_direction
     */
    fun getInferredDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getInferredDirectionBind, handle)
    }

    /**
     * Text orientation.
     *
     * Generated from Godot docs: TextLine.set_orientation
     */
    fun setOrientation(orientation: Long) {
        ObjectCalls.ptrcallWithLongArg(setOrientationBind, handle, orientation)
    }

    /**
     * Text orientation.
     *
     * Generated from Godot docs: TextLine.get_orientation
     */
    fun getOrientation(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getOrientationBind, handle)
    }

    /**
     * If set to `true` text will display invalid characters.
     *
     * Generated from Godot docs: TextLine.set_preserve_invalid
     */
    fun setPreserveInvalid(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPreserveInvalidBind, handle, enabled)
    }

    /**
     * If set to `true` text will display invalid characters.
     *
     * Generated from Godot docs: TextLine.get_preserve_invalid
     */
    fun getPreserveInvalid(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPreserveInvalidBind, handle)
    }

    /**
     * If set to `true` text will display control characters.
     *
     * Generated from Godot docs: TextLine.set_preserve_control
     */
    fun setPreserveControl(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPreserveControlBind, handle, enabled)
    }

    /**
     * If set to `true` text will display control characters.
     *
     * Generated from Godot docs: TextLine.get_preserve_control
     */
    fun getPreserveControl(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getPreserveControlBind, handle)
    }

    /**
     * Overrides BiDi for the structured text. Override ranges should cover full source text without
     * overlaps. BiDi algorithm will be used on each range separately.
     *
     * Generated from Godot docs: TextLine.set_bidi_override
     */
    fun setBidiOverride(override: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setBidiOverrideBind, handle, override)
    }

    /**
     * Adds text span and font to draw it.
     *
     * Generated from Godot docs: TextLine.add_string
     */
    fun addString(text: String, font: Font?, fontSize: Int, language: String = "", meta: Any? = null): Boolean {
        return ObjectCalls.ptrcallWithStringObjectIntStringVariantArgsRetBool(addStringBind, handle, text, font?.requireOpenHandle() ?: MemorySegment.NULL, fontSize, language, meta)
    }

    /**
     * Adds inline object to the text buffer, `key` must be unique. In the text, object is represented
     * as `length` object replacement characters.
     *
     * Generated from Godot docs: TextLine.add_object
     */
    fun addObject(key: Any?, size: Vector2, inlineAlign: Long = 5L, length: Int = 1, baseline: Double = 0.0): Boolean {
        return ObjectCalls.ptrcallWithVariantVector2LongIntDoubleArgsRetBool(addObjectBind, handle, key, size, inlineAlign, length, baseline)
    }

    /**
     * Sets new size and alignment of embedded object.
     *
     * Generated from Godot docs: TextLine.resize_object
     */
    fun resizeObject(key: Any?, size: Vector2, inlineAlign: Long = 5L, baseline: Double = 0.0): Boolean {
        return ObjectCalls.ptrcallWithVariantVector2LongDoubleArgsRetBool(resizeObjectBind, handle, key, size, inlineAlign, baseline)
    }

    /**
     * Returns `true` if an object with `key` is embedded in this line.
     *
     * Generated from Godot docs: TextLine.has_object
     */
    fun hasObject(key: Any?): Boolean {
        return ObjectCalls.ptrcallWithVariantArgRetBool(hasObjectBind, handle, key)
    }

    /**
     * Text line width.
     *
     * Generated from Godot docs: TextLine.set_width
     */
    fun setWidth(width: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWidthBind, handle, width)
    }

    /**
     * Text line width.
     *
     * Generated from Godot docs: TextLine.get_width
     */
    fun getWidth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWidthBind, handle)
    }

    /**
     * Sets text alignment within the line as if the line was horizontal.
     *
     * Generated from Godot docs: TextLine.set_horizontal_alignment
     */
    fun setHorizontalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setHorizontalAlignmentBind, handle, alignment)
    }

    /**
     * Sets text alignment within the line as if the line was horizontal.
     *
     * Generated from Godot docs: TextLine.get_horizontal_alignment
     */
    fun getHorizontalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHorizontalAlignmentBind, handle)
    }

    /**
     * Aligns text to the given tab-stops.
     *
     * Generated from Godot docs: TextLine.tab_align
     */
    fun tabAlign(tabStops: List<Float>) {
        ObjectCalls.ptrcallWithPackedFloat32ListArg(tabAlignBind, handle, tabStops)
    }

    /**
     * Line alignment rules. For more info see `TextServer`.
     *
     * Generated from Godot docs: TextLine.set_flags
     */
    fun setFlags(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setFlagsBind, handle, flags)
    }

    /**
     * Line alignment rules. For more info see `TextServer`.
     *
     * Generated from Godot docs: TextLine.get_flags
     */
    fun getFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFlagsBind, handle)
    }

    /**
     * The clipping behavior when the text exceeds the text line's set width.
     *
     * Generated from Godot docs: TextLine.set_text_overrun_behavior
     */
    fun setTextOverrunBehavior(overrunBehavior: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextOverrunBehaviorBind, handle, overrunBehavior)
    }

    /**
     * The clipping behavior when the text exceeds the text line's set width.
     *
     * Generated from Godot docs: TextLine.get_text_overrun_behavior
     */
    fun getTextOverrunBehavior(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextOverrunBehaviorBind, handle)
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: TextLine.set_ellipsis_char
     */
    fun setEllipsisChar(char: String) {
        ObjectCalls.ptrcallWithStringArg(setEllipsisCharBind, handle, char)
    }

    /**
     * Ellipsis character used for text clipping.
     *
     * Generated from Godot docs: TextLine.get_ellipsis_char
     */
    fun getEllipsisChar(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getEllipsisCharBind, handle)
    }

    /**
     * Returns array of inline objects.
     *
     * Generated from Godot docs: TextLine.get_objects
     */
    fun getObjects(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getObjectsBind, handle)
    }

    /**
     * Returns bounding rectangle of the inline object.
     *
     * Generated from Godot docs: TextLine.get_object_rect
     */
    fun getObjectRect(key: Any?): Rect2 {
        return ObjectCalls.ptrcallWithVariantArgRetRect2(getObjectRectBind, handle, key)
    }

    /**
     * Returns size of the bounding box of the text.
     *
     * Generated from Godot docs: TextLine.get_size
     */
    fun getSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getSizeBind, handle)
    }

    /**
     * Returns TextServer buffer RID.
     *
     * Generated from Godot docs: TextLine.get_rid
     */
    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
    }

    /**
     * Returns the text ascent (number of pixels above the baseline for horizontal layout or to the
     * left of baseline for vertical).
     *
     * Generated from Godot docs: TextLine.get_line_ascent
     */
    fun getLineAscent(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineAscentBind, handle)
    }

    /**
     * Returns the text descent (number of pixels below the baseline for horizontal layout or to the
     * right of baseline for vertical).
     *
     * Generated from Godot docs: TextLine.get_line_descent
     */
    fun getLineDescent(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineDescentBind, handle)
    }

    /**
     * Returns width (for horizontal layout) or height (for vertical) of the text.
     *
     * Generated from Godot docs: TextLine.get_line_width
     */
    fun getLineWidth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineWidthBind, handle)
    }

    /**
     * Returns pixel offset of the underline below the baseline.
     *
     * Generated from Godot docs: TextLine.get_line_underline_position
     */
    fun getLineUnderlinePosition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineUnderlinePositionBind, handle)
    }

    /**
     * Returns thickness of the underline.
     *
     * Generated from Godot docs: TextLine.get_line_underline_thickness
     */
    fun getLineUnderlineThickness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineUnderlineThicknessBind, handle)
    }

    /**
     * Draw text into a canvas item at a given position, with `color`. `pos` specifies the top left
     * corner of the bounding box. If `oversampling` is greater than zero, it is used as font
     * oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextLine.draw
     */
    fun draw(canvas: RID, pos: Vector2, color: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDVector2ColorDoubleArgs(drawBind, handle, canvas, pos, color, oversampling)
    }

    /**
     * Draw text into a canvas item at a given position, with `color`. `pos` specifies the top left
     * corner of the bounding box. If `oversampling` is greater than zero, it is used as font
     * oversampling factor, otherwise viewport oversampling settings are used.
     *
     * Generated from Godot docs: TextLine.draw_outline
     */
    fun drawOutline(canvas: RID, pos: Vector2, outlineSize: Int = 1, color: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithRIDVector2IntColorDoubleArgs(drawOutlineBind, handle, canvas, pos, outlineSize, color, oversampling)
    }

    /**
     * Returns caret character offset at the specified pixel offset at the baseline. This function
     * always returns a valid position.
     *
     * Generated from Godot docs: TextLine.hit_test
     */
    fun hitTest(coords: Double): Int {
        return ObjectCalls.ptrcallWithDoubleArgRetInt(hitTestBind, handle, coords)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TextLine? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextLine? =
            if (handle.address() == 0L) null else TextLine(handle)

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "clear", CLEAR_HASH)
        }

        private const val DUPLICATE_HASH = 1912703884L
        private val duplicateBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "duplicate", DUPLICATE_HASH)
        }

        private const val SET_DIRECTION_HASH = 1418190634L
        private val setDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "set_direction", SET_DIRECTION_HASH)
        }

        private const val GET_DIRECTION_HASH = 2516697328L
        private val getDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_direction", GET_DIRECTION_HASH)
        }

        private const val GET_INFERRED_DIRECTION_HASH = 2516697328L
        private val getInferredDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_inferred_direction", GET_INFERRED_DIRECTION_HASH)
        }

        private const val SET_ORIENTATION_HASH = 42823726L
        private val setOrientationBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "set_orientation", SET_ORIENTATION_HASH)
        }

        private const val GET_ORIENTATION_HASH = 175768116L
        private val getOrientationBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_orientation", GET_ORIENTATION_HASH)
        }

        private const val SET_PRESERVE_INVALID_HASH = 2586408642L
        private val setPreserveInvalidBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "set_preserve_invalid", SET_PRESERVE_INVALID_HASH)
        }

        private const val GET_PRESERVE_INVALID_HASH = 36873697L
        private val getPreserveInvalidBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_preserve_invalid", GET_PRESERVE_INVALID_HASH)
        }

        private const val SET_PRESERVE_CONTROL_HASH = 2586408642L
        private val setPreserveControlBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "set_preserve_control", SET_PRESERVE_CONTROL_HASH)
        }

        private const val GET_PRESERVE_CONTROL_HASH = 36873697L
        private val getPreserveControlBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_preserve_control", GET_PRESERVE_CONTROL_HASH)
        }

        private const val SET_BIDI_OVERRIDE_HASH = 381264803L
        private val setBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "set_bidi_override", SET_BIDI_OVERRIDE_HASH)
        }

        private const val ADD_STRING_HASH = 621426851L
        private val addStringBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "add_string", ADD_STRING_HASH)
        }

        private const val ADD_OBJECT_HASH = 1316529304L
        private val addObjectBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "add_object", ADD_OBJECT_HASH)
        }

        private const val RESIZE_OBJECT_HASH = 2095776372L
        private val resizeObjectBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "resize_object", RESIZE_OBJECT_HASH)
        }

        private const val HAS_OBJECT_HASH = 77467830L
        private val hasObjectBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "has_object", HAS_OBJECT_HASH)
        }

        private const val SET_WIDTH_HASH = 373806689L
        private val setWidthBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "set_width", SET_WIDTH_HASH)
        }

        private const val GET_WIDTH_HASH = 1740695150L
        private val getWidthBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_width", GET_WIDTH_HASH)
        }

        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        private val setHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        private val getHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val TAB_ALIGN_HASH = 2899603908L
        private val tabAlignBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "tab_align", TAB_ALIGN_HASH)
        }

        private const val SET_FLAGS_HASH = 2877345813L
        private val setFlagsBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "set_flags", SET_FLAGS_HASH)
        }

        private const val GET_FLAGS_HASH = 1583363614L
        private val getFlagsBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_flags", GET_FLAGS_HASH)
        }

        private const val SET_TEXT_OVERRUN_BEHAVIOR_HASH = 1008890932L
        private val setTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "set_text_overrun_behavior", SET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val GET_TEXT_OVERRUN_BEHAVIOR_HASH = 3779142101L
        private val getTextOverrunBehaviorBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_text_overrun_behavior", GET_TEXT_OVERRUN_BEHAVIOR_HASH)
        }

        private const val SET_ELLIPSIS_CHAR_HASH = 83702148L
        private val setEllipsisCharBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "set_ellipsis_char", SET_ELLIPSIS_CHAR_HASH)
        }

        private const val GET_ELLIPSIS_CHAR_HASH = 201670096L
        private val getEllipsisCharBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_ellipsis_char", GET_ELLIPSIS_CHAR_HASH)
        }

        private const val GET_OBJECTS_HASH = 3995934104L
        private val getObjectsBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_objects", GET_OBJECTS_HASH)
        }

        private const val GET_OBJECT_RECT_HASH = 1742700391L
        private val getObjectRectBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_object_rect", GET_OBJECT_RECT_HASH)
        }

        private const val GET_SIZE_HASH = 3341600327L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_size", GET_SIZE_HASH)
        }

        private const val GET_RID_HASH = 2944877500L
        private val getRidBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_rid", GET_RID_HASH)
        }

        private const val GET_LINE_ASCENT_HASH = 1740695150L
        private val getLineAscentBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_line_ascent", GET_LINE_ASCENT_HASH)
        }

        private const val GET_LINE_DESCENT_HASH = 1740695150L
        private val getLineDescentBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_line_descent", GET_LINE_DESCENT_HASH)
        }

        private const val GET_LINE_WIDTH_HASH = 1740695150L
        private val getLineWidthBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_line_width", GET_LINE_WIDTH_HASH)
        }

        private const val GET_LINE_UNDERLINE_POSITION_HASH = 1740695150L
        private val getLineUnderlinePositionBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_line_underline_position", GET_LINE_UNDERLINE_POSITION_HASH)
        }

        private const val GET_LINE_UNDERLINE_THICKNESS_HASH = 1740695150L
        private val getLineUnderlineThicknessBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "get_line_underline_thickness", GET_LINE_UNDERLINE_THICKNESS_HASH)
        }

        private const val DRAW_HASH = 3625105422L
        private val drawBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "draw", DRAW_HASH)
        }

        private const val DRAW_OUTLINE_HASH = 2592177763L
        private val drawOutlineBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "draw_outline", DRAW_OUTLINE_HASH)
        }

        private const val HIT_TEST_HASH = 2401831903L
        private val hitTestBind by lazy {
            ObjectCalls.getMethodBind("TextLine", "hit_test", HIT_TEST_HASH)
        }
    }
}
