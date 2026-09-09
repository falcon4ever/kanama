package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
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

    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun duplicate(): TextLine? {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetObject(duplicateBind, handle)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return TextLine.wrap(ret)
    }

    fun setDirection(direction: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDirectionBind, handle, direction)
    }

    fun getDirection(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getDirectionBind, handle)
    }

    fun getInferredDirection(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getInferredDirectionBind, handle)
    }

    fun setOrientation(orientation: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setOrientationBind, handle, orientation)
    }

    fun getOrientation(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getOrientationBind, handle)
    }

    fun setPreserveInvalid(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPreserveInvalidBind, handle, enabled)
    }

    fun getPreserveInvalid(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getPreserveInvalidBind, handle)
    }

    fun setPreserveControl(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setPreserveControlBind, handle, enabled)
    }

    fun getPreserveControl(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(getPreserveControlBind, handle)
    }

    fun setWidth(width: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setWidthBind, handle, width)
    }

    fun getWidth(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getWidthBind, handle)
    }

    fun setHorizontalAlignment(alignment: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setHorizontalAlignmentBind, handle, alignment)
    }

    fun getHorizontalAlignment(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getHorizontalAlignmentBind, handle)
    }

    fun tabAlign(tabStops: List<Float>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedFloat32ListArg(tabAlignBind, handle, tabStops)
    }

    fun setFlags(flags: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFlagsBind, handle, flags)
    }

    fun getFlags(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getFlagsBind, handle)
    }

    fun setTextOverrunBehavior(overrunBehavior: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setTextOverrunBehaviorBind, handle, overrunBehavior)
    }

    fun getTextOverrunBehavior(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getTextOverrunBehaviorBind, handle)
    }

    fun setEllipsisChar(char: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(setEllipsisCharBind, handle, char)
    }

    fun getEllipsisChar(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(getEllipsisCharBind, handle)
    }

    fun getObjects(): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetArray(getObjectsBind, handle)
    }

    fun getSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getSizeBind, handle)
    }

    fun getRid(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
    }

    fun getLineAscent(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineAscentBind, handle)
    }

    fun getLineDescent(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineDescentBind, handle)
    }

    fun getLineWidth(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineWidthBind, handle)
    }

    fun getLineUnderlinePosition(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineUnderlinePositionBind, handle)
    }

    fun getLineUnderlineThickness(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineUnderlineThicknessBind, handle)
    }

    fun draw(canvas: RID, pos: Vector2, color: Color, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2ColorDoubleArgs(drawBind, handle, canvas, pos, color, oversampling)
    }

    fun drawOutline(canvas: RID, pos: Vector2, outlineSize: Int = 1, color: Color, oversampling: Double = 0.0) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2IntColorDoubleArgs(drawOutlineBind, handle, canvas, pos, outlineSize, color, oversampling)
    }

    fun hitTest(coords: Double): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithDoubleArgRetInt(hitTestBind, handle, coords)
    }

    companion object {
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
