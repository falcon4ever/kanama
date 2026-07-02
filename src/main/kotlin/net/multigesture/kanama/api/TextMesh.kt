package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * Generate a `PrimitiveMesh` from the text.
 *
 * Generated from Godot docs: TextMesh
 */
class TextMesh(handle: MemorySegment) : PrimitiveMesh(handle) {
    var text: String
        @JvmName("textProperty")
        get() = getText()
        @JvmName("setTextProperty")
        set(value) = setText(value)

    var font: Font?
        @JvmName("fontProperty")
        get() = getFont()
        @JvmName("setFontProperty")
        set(value) = setFont(value)

    var fontSize: Int
        @JvmName("fontSizeProperty")
        get() = getFontSize()
        @JvmName("setFontSizeProperty")
        set(value) = setFontSize(value)

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

    var uppercase: Boolean
        @JvmName("uppercaseProperty")
        get() = isUppercase()
        @JvmName("setUppercaseProperty")
        set(value) = setUppercase(value)

    var lineSpacing: Double
        @JvmName("lineSpacingProperty")
        get() = getLineSpacing()
        @JvmName("setLineSpacingProperty")
        set(value) = setLineSpacing(value)

    var autowrapMode: Long
        @JvmName("autowrapModeProperty")
        get() = getAutowrapMode()
        @JvmName("setAutowrapModeProperty")
        set(value) = setAutowrapMode(value)

    var justificationFlags: Long
        @JvmName("justificationFlagsProperty")
        get() = getJustificationFlags()
        @JvmName("setJustificationFlagsProperty")
        set(value) = setJustificationFlags(value)

    var pixelSize: Double
        @JvmName("pixelSizeProperty")
        get() = getPixelSize()
        @JvmName("setPixelSizeProperty")
        set(value) = setPixelSize(value)

    var curveStep: Double
        @JvmName("curveStepProperty")
        get() = getCurveStep()
        @JvmName("setCurveStepProperty")
        set(value) = setCurveStep(value)

    var depth: Double
        @JvmName("depthProperty")
        get() = getDepth()
        @JvmName("setDepthProperty")
        set(value) = setDepth(value)

    var width: Double
        @JvmName("widthProperty")
        get() = getWidth()
        @JvmName("setWidthProperty")
        set(value) = setWidth(value)

    var offset: Vector2
        @JvmName("offsetProperty")
        get() = getOffset()
        @JvmName("setOffsetProperty")
        set(value) = setOffset(value)

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

    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, handle, text)
    }

    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextBind, handle)
    }

    fun setFont(font: Font?) {
        ObjectCalls.ptrcallWithObjectArgs(setFontBind, handle, listOf(font?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getFont(): Font? {
        return Font.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFontBind, handle))
    }

    fun setFontSize(fontSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setFontSizeBind, handle, fontSize)
    }

    fun getFontSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFontSizeBind, handle)
    }

    fun setLineSpacing(lineSpacing: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLineSpacingBind, handle, lineSpacing)
    }

    fun getLineSpacing(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineSpacingBind, handle)
    }

    fun setAutowrapMode(autowrapMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapModeBind, handle, autowrapMode)
    }

    fun getAutowrapMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapModeBind, handle)
    }

    fun setJustificationFlags(justificationFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setJustificationFlagsBind, handle, justificationFlags)
    }

    fun getJustificationFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getJustificationFlagsBind, handle)
    }

    fun setDepth(depth: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDepthBind, handle, depth)
    }

    fun getDepth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthBind, handle)
    }

    fun setWidth(width: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWidthBind, handle, width)
    }

    fun getWidth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWidthBind, handle)
    }

    fun setPixelSize(pixelSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPixelSizeBind, handle, pixelSize)
    }

    fun getPixelSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPixelSizeBind, handle)
    }

    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, handle, offset)
    }

    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    fun setCurveStep(curveStep: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCurveStepBind, handle, curveStep)
    }

    fun getCurveStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCurveStepBind, handle)
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

    fun setUppercase(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUppercaseBind, handle, enable)
    }

    fun isUppercase(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUppercaseBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): TextMesh? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextMesh? =
            if (handle.address() == 0L) null else TextMesh(handle)

        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        private val setHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        private val getHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val SET_VERTICAL_ALIGNMENT_HASH = 1796458609L
        private val setVerticalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_vertical_alignment", SET_VERTICAL_ALIGNMENT_HASH)
        }

        private const val GET_VERTICAL_ALIGNMENT_HASH = 3274884059L
        private val getVerticalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_vertical_alignment", GET_VERTICAL_ALIGNMENT_HASH)
        }

        private const val SET_TEXT_HASH = 83702148L
        private val setTextBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_text", SET_TEXT_HASH)
        }

        private const val GET_TEXT_HASH = 201670096L
        private val getTextBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_text", GET_TEXT_HASH)
        }

        private const val SET_FONT_HASH = 1262170328L
        private val setFontBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_font", SET_FONT_HASH)
        }

        private const val GET_FONT_HASH = 3229501585L
        private val getFontBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_font", GET_FONT_HASH)
        }

        private const val SET_FONT_SIZE_HASH = 1286410249L
        private val setFontSizeBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_font_size", SET_FONT_SIZE_HASH)
        }

        private const val GET_FONT_SIZE_HASH = 3905245786L
        private val getFontSizeBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_font_size", GET_FONT_SIZE_HASH)
        }

        private const val SET_LINE_SPACING_HASH = 373806689L
        private val setLineSpacingBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_line_spacing", SET_LINE_SPACING_HASH)
        }

        private const val GET_LINE_SPACING_HASH = 1740695150L
        private val getLineSpacingBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_line_spacing", GET_LINE_SPACING_HASH)
        }

        private const val SET_AUTOWRAP_MODE_HASH = 3289138044L
        private val setAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_autowrap_mode", SET_AUTOWRAP_MODE_HASH)
        }

        private const val GET_AUTOWRAP_MODE_HASH = 1549071663L
        private val getAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_autowrap_mode", GET_AUTOWRAP_MODE_HASH)
        }

        private const val SET_JUSTIFICATION_FLAGS_HASH = 2877345813L
        private val setJustificationFlagsBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_justification_flags", SET_JUSTIFICATION_FLAGS_HASH)
        }

        private const val GET_JUSTIFICATION_FLAGS_HASH = 1583363614L
        private val getJustificationFlagsBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_justification_flags", GET_JUSTIFICATION_FLAGS_HASH)
        }

        private const val SET_DEPTH_HASH = 373806689L
        private val setDepthBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_depth", SET_DEPTH_HASH)
        }

        private const val GET_DEPTH_HASH = 1740695150L
        private val getDepthBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_depth", GET_DEPTH_HASH)
        }

        private const val SET_WIDTH_HASH = 373806689L
        private val setWidthBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_width", SET_WIDTH_HASH)
        }

        private const val GET_WIDTH_HASH = 1740695150L
        private val getWidthBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_width", GET_WIDTH_HASH)
        }

        private const val SET_PIXEL_SIZE_HASH = 373806689L
        private val setPixelSizeBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_pixel_size", SET_PIXEL_SIZE_HASH)
        }

        private const val GET_PIXEL_SIZE_HASH = 1740695150L
        private val getPixelSizeBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_pixel_size", GET_PIXEL_SIZE_HASH)
        }

        private const val SET_OFFSET_HASH = 743155724L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_OFFSET_HASH = 3341600327L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_offset", GET_OFFSET_HASH)
        }

        private const val SET_CURVE_STEP_HASH = 373806689L
        private val setCurveStepBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_curve_step", SET_CURVE_STEP_HASH)
        }

        private const val GET_CURVE_STEP_HASH = 1740695150L
        private val getCurveStepBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_curve_step", GET_CURVE_STEP_HASH)
        }

        private const val SET_TEXT_DIRECTION_HASH = 1418190634L
        private val setTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_text_direction", SET_TEXT_DIRECTION_HASH)
        }

        private const val GET_TEXT_DIRECTION_HASH = 2516697328L
        private val getTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_text_direction", GET_TEXT_DIRECTION_HASH)
        }

        private const val SET_LANGUAGE_HASH = 83702148L
        private val setLanguageBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_language", SET_LANGUAGE_HASH)
        }

        private const val GET_LANGUAGE_HASH = 201670096L
        private val getLanguageBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_language", GET_LANGUAGE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 55961453L
        private val setStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_structured_text_bidi_override", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3385126229L
        private val getStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_structured_text_bidi_override", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
        private val setStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 3995934104L
        private val getStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val SET_UPPERCASE_HASH = 2586408642L
        private val setUppercaseBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "set_uppercase", SET_UPPERCASE_HASH)
        }

        private const val IS_UPPERCASE_HASH = 36873697L
        private val isUppercaseBind by lazy {
            ObjectCalls.getMethodBind("TextMesh", "is_uppercase", IS_UPPERCASE_HASH)
        }
    }
}
