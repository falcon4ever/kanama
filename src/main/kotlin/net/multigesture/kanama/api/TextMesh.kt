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

    /**
     * Controls the text's horizontal alignment. Supports left, center, right, and fill (also known as
     * justify).
     *
     * Generated from Godot docs: TextMesh.set_horizontal_alignment
     */
    fun setHorizontalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setHorizontalAlignmentBind, handle, alignment)
    }

    /**
     * Controls the text's horizontal alignment. Supports left, center, right, and fill (also known as
     * justify).
     *
     * Generated from Godot docs: TextMesh.get_horizontal_alignment
     */
    fun getHorizontalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHorizontalAlignmentBind, handle)
    }

    /**
     * Controls the text's vertical alignment. Supports top, center, and bottom.
     *
     * Generated from Godot docs: TextMesh.set_vertical_alignment
     */
    fun setVerticalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setVerticalAlignmentBind, handle, alignment)
    }

    /**
     * Controls the text's vertical alignment. Supports top, center, and bottom.
     *
     * Generated from Godot docs: TextMesh.get_vertical_alignment
     */
    fun getVerticalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVerticalAlignmentBind, handle)
    }

    /**
     * The text to generate mesh from. Note: Due to being a `Resource`, it doesn't follow the rules of
     * `Node.auto_translate_mode`. If disabling translation is desired, it should be done manually with
     * `Object.set_message_translation`.
     *
     * Generated from Godot docs: TextMesh.set_text
     */
    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, handle, text)
    }

    /**
     * The text to generate mesh from. Note: Due to being a `Resource`, it doesn't follow the rules of
     * `Node.auto_translate_mode`. If disabling translation is desired, it should be done manually with
     * `Object.set_message_translation`.
     *
     * Generated from Godot docs: TextMesh.get_text
     */
    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextBind, handle)
    }

    /**
     * Font configuration used to display text.
     *
     * Generated from Godot docs: TextMesh.set_font
     */
    fun setFont(font: Font?) {
        ObjectCalls.ptrcallWithObjectArgs(setFontBind, handle, listOf(font?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Font configuration used to display text.
     *
     * Generated from Godot docs: TextMesh.get_font
     */
    fun getFont(): Font? {
        return Font.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFontBind, handle))
    }

    /**
     * Font size of the `TextMesh`'s text. This property works in tandem with `pixel_size`. Higher
     * values will result in a more detailed font, regardless of `curve_step` and `pixel_size`.
     * Consider keeping this value below 63 (inclusive) for good performance, and adjust `pixel_size`
     * as needed to enlarge text. Note: Changing this property will regenerate the mesh, which is a
     * slow operation, especially with large font sizes and long texts. To change the text's size in
     * real-time efficiently, change the node's `Node3D.scale` instead.
     *
     * Generated from Godot docs: TextMesh.set_font_size
     */
    fun setFontSize(fontSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setFontSizeBind, handle, fontSize)
    }

    /**
     * Font size of the `TextMesh`'s text. This property works in tandem with `pixel_size`. Higher
     * values will result in a more detailed font, regardless of `curve_step` and `pixel_size`.
     * Consider keeping this value below 63 (inclusive) for good performance, and adjust `pixel_size`
     * as needed to enlarge text. Note: Changing this property will regenerate the mesh, which is a
     * slow operation, especially with large font sizes and long texts. To change the text's size in
     * real-time efficiently, change the node's `Node3D.scale` instead.
     *
     * Generated from Godot docs: TextMesh.get_font_size
     */
    fun getFontSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFontSizeBind, handle)
    }

    /**
     * Additional vertical spacing between lines (in pixels), spacing is added to line descent. This
     * value can be negative.
     *
     * Generated from Godot docs: TextMesh.set_line_spacing
     */
    fun setLineSpacing(lineSpacing: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLineSpacingBind, handle, lineSpacing)
    }

    /**
     * Additional vertical spacing between lines (in pixels), spacing is added to line descent. This
     * value can be negative.
     *
     * Generated from Godot docs: TextMesh.get_line_spacing
     */
    fun getLineSpacing(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineSpacingBind, handle)
    }

    /**
     * If set to something other than `TextServer.AUTOWRAP_OFF`, the text gets wrapped inside the
     * node's bounding rectangle. If you resize the node, it will change its height automatically to
     * show all the text.
     *
     * Generated from Godot docs: TextMesh.set_autowrap_mode
     */
    fun setAutowrapMode(autowrapMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapModeBind, handle, autowrapMode)
    }

    /**
     * If set to something other than `TextServer.AUTOWRAP_OFF`, the text gets wrapped inside the
     * node's bounding rectangle. If you resize the node, it will change its height automatically to
     * show all the text.
     *
     * Generated from Godot docs: TextMesh.get_autowrap_mode
     */
    fun getAutowrapMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapModeBind, handle)
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: TextMesh.set_justification_flags
     */
    fun setJustificationFlags(justificationFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setJustificationFlagsBind, handle, justificationFlags)
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: TextMesh.get_justification_flags
     */
    fun getJustificationFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getJustificationFlagsBind, handle)
    }

    /**
     * Depths of the mesh, if set to `0.0` only front surface, is generated, and UV layout is changed
     * to use full texture for the front face only.
     *
     * Generated from Godot docs: TextMesh.set_depth
     */
    fun setDepth(depth: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDepthBind, handle, depth)
    }

    /**
     * Depths of the mesh, if set to `0.0` only front surface, is generated, and UV layout is changed
     * to use full texture for the front face only.
     *
     * Generated from Godot docs: TextMesh.get_depth
     */
    fun getDepth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthBind, handle)
    }

    /**
     * Text width (in pixels), used for fill alignment.
     *
     * Generated from Godot docs: TextMesh.set_width
     */
    fun setWidth(width: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWidthBind, handle, width)
    }

    /**
     * Text width (in pixels), used for fill alignment.
     *
     * Generated from Godot docs: TextMesh.get_width
     */
    fun getWidth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWidthBind, handle)
    }

    /**
     * The size of one pixel's width on the text to scale it in 3D. This property works in tandem with
     * `font_size`. Note: Changing this property will regenerate the mesh, which is a slow operation,
     * especially with large font sizes and long texts. To change the text's size in real-time
     * efficiently, change the node's `Node3D.scale` instead.
     *
     * Generated from Godot docs: TextMesh.set_pixel_size
     */
    fun setPixelSize(pixelSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPixelSizeBind, handle, pixelSize)
    }

    /**
     * The size of one pixel's width on the text to scale it in 3D. This property works in tandem with
     * `font_size`. Note: Changing this property will regenerate the mesh, which is a slow operation,
     * especially with large font sizes and long texts. To change the text's size in real-time
     * efficiently, change the node's `Node3D.scale` instead.
     *
     * Generated from Godot docs: TextMesh.get_pixel_size
     */
    fun getPixelSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPixelSizeBind, handle)
    }

    /**
     * The text drawing offset (in pixels). Note: Changing this property will regenerate the mesh,
     * which is a slow operation. To change the text's position in real-time efficiently, change the
     * node's `Node3D.position` instead.
     *
     * Generated from Godot docs: TextMesh.set_offset
     */
    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, handle, offset)
    }

    /**
     * The text drawing offset (in pixels). Note: Changing this property will regenerate the mesh,
     * which is a slow operation. To change the text's position in real-time efficiently, change the
     * node's `Node3D.position` instead.
     *
     * Generated from Godot docs: TextMesh.get_offset
     */
    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    /**
     * Step (in pixels) used to approximate Bézier curves. Lower values result in smoother curves, but
     * is slower to generate and render. Consider adjusting this according to the font size and the
     * typical viewing distance. Note: Changing this property will regenerate the mesh, which is a slow
     * operation, especially with large font sizes and long texts.
     *
     * Generated from Godot docs: TextMesh.set_curve_step
     */
    fun setCurveStep(curveStep: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCurveStepBind, handle, curveStep)
    }

    /**
     * Step (in pixels) used to approximate Bézier curves. Lower values result in smoother curves, but
     * is slower to generate and render. Consider adjusting this according to the font size and the
     * typical viewing distance. Note: Changing this property will regenerate the mesh, which is a slow
     * operation, especially with large font sizes and long texts.
     *
     * Generated from Godot docs: TextMesh.get_curve_step
     */
    fun getCurveStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCurveStepBind, handle)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: TextMesh.set_text_direction
     */
    fun setTextDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextDirectionBind, handle, direction)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: TextMesh.get_text_direction
     */
    fun getTextDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextDirectionBind, handle)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: TextMesh.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(setLanguageBind, handle, language)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: TextMesh.get_language
     */
    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageBind, handle)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: TextMesh.set_structured_text_bidi_override
     */
    fun setStructuredTextBidiOverride(parser: Long) {
        ObjectCalls.ptrcallWithLongArg(setStructuredTextBidiOverrideBind, handle, parser)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: TextMesh.get_structured_text_bidi_override
     */
    fun getStructuredTextBidiOverride(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStructuredTextBidiOverrideBind, handle)
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: TextMesh.set_structured_text_bidi_override_options
     */
    fun setStructuredTextBidiOverrideOptions(args: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setStructuredTextBidiOverrideOptionsBind, handle, args)
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: TextMesh.get_structured_text_bidi_override_options
     */
    fun getStructuredTextBidiOverrideOptions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getStructuredTextBidiOverrideOptionsBind, handle)
    }

    /**
     * If `true`, all the text displays as UPPERCASE.
     *
     * Generated from Godot docs: TextMesh.set_uppercase
     */
    fun setUppercase(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUppercaseBind, handle, enable)
    }

    /**
     * If `true`, all the text displays as UPPERCASE.
     *
     * Generated from Godot docs: TextMesh.is_uppercase
     */
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
