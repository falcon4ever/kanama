package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A node for displaying plain text in 3D space.
 *
 * Generated from Godot docs: Label3D
 */
class Label3D(handle: MemorySegment) : GeometryInstance3D(handle) {
    var pixelSize: Double
        @JvmName("pixelSizeProperty")
        get() = getPixelSize()
        @JvmName("setPixelSizeProperty")
        set(value) = setPixelSize(value)

    var offset: Vector2
        @JvmName("offsetProperty")
        get() = getOffset()
        @JvmName("setOffsetProperty")
        set(value) = setOffset(value)

    var billboard: Long
        @JvmName("billboardProperty")
        get() = getBillboardMode()
        @JvmName("setBillboardProperty")
        set(value) = setBillboardMode(value)

    var alphaCut: Long
        @JvmName("alphaCutProperty")
        get() = getAlphaCutMode()
        @JvmName("setAlphaCutProperty")
        set(value) = setAlphaCutMode(value)

    var alphaScissorThreshold: Double
        @JvmName("alphaScissorThresholdProperty")
        get() = getAlphaScissorThreshold()
        @JvmName("setAlphaScissorThresholdProperty")
        set(value) = setAlphaScissorThreshold(value)

    var alphaHashScale: Double
        @JvmName("alphaHashScaleProperty")
        get() = getAlphaHashScale()
        @JvmName("setAlphaHashScaleProperty")
        set(value) = setAlphaHashScale(value)

    var alphaAntialiasingMode: Long
        @JvmName("alphaAntialiasingModeProperty")
        get() = getAlphaAntialiasing()
        @JvmName("setAlphaAntialiasingModeProperty")
        set(value) = setAlphaAntialiasing(value)

    var alphaAntialiasingEdge: Double
        @JvmName("alphaAntialiasingEdgeProperty")
        get() = getAlphaAntialiasingEdge()
        @JvmName("setAlphaAntialiasingEdgeProperty")
        set(value) = setAlphaAntialiasingEdge(value)

    var textureFilter: Long
        @JvmName("textureFilterProperty")
        get() = getTextureFilter()
        @JvmName("setTextureFilterProperty")
        set(value) = setTextureFilter(value)

    var renderPriority: Int
        @JvmName("renderPriorityProperty")
        get() = getRenderPriority()
        @JvmName("setRenderPriorityProperty")
        set(value) = setRenderPriority(value)

    var outlineRenderPriority: Int
        @JvmName("outlineRenderPriorityProperty")
        get() = getOutlineRenderPriority()
        @JvmName("setOutlineRenderPriorityProperty")
        set(value) = setOutlineRenderPriority(value)

    var modulate: Color
        @JvmName("modulateProperty")
        get() = getModulate()
        @JvmName("setModulateProperty")
        set(value) = setModulate(value)

    var outlineModulate: Color
        @JvmName("outlineModulateProperty")
        get() = getOutlineModulate()
        @JvmName("setOutlineModulateProperty")
        set(value) = setOutlineModulate(value)

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

    var outlineSize: Int
        @JvmName("outlineSizeProperty")
        get() = getOutlineSize()
        @JvmName("setOutlineSizeProperty")
        set(value) = setOutlineSize(value)

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

    var width: Double
        @JvmName("widthProperty")
        get() = getWidth()
        @JvmName("setWidthProperty")
        set(value) = setWidth(value)

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

    fun setModulate(modulate: Color) {
        ObjectCalls.ptrcallWithColorArg(setModulateBind, handle, modulate)
    }

    fun getModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getModulateBind, handle)
    }

    fun setOutlineModulate(modulate: Color) {
        ObjectCalls.ptrcallWithColorArg(setOutlineModulateBind, handle, modulate)
    }

    fun getOutlineModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getOutlineModulateBind, handle)
    }

    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, handle, text)
    }

    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextBind, handle)
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

    fun setRenderPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setRenderPriorityBind, handle, priority)
    }

    fun getRenderPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRenderPriorityBind, handle)
    }

    fun setOutlineRenderPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setOutlineRenderPriorityBind, handle, priority)
    }

    fun getOutlineRenderPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOutlineRenderPriorityBind, handle)
    }

    fun setFont(font: Font?) {
        ObjectCalls.ptrcallWithObjectArgs(setFontBind, handle, listOf(font?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getFont(): Font? {
        return Font.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFontBind, handle))
    }

    fun setFontSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setFontSizeBind, handle, size)
    }

    fun getFontSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFontSizeBind, handle)
    }

    fun setOutlineSize(outlineSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setOutlineSizeBind, handle, outlineSize)
    }

    fun getOutlineSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOutlineSizeBind, handle)
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

    fun setAutowrapTrimFlags(autowrapTrimFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapTrimFlagsBind, handle, autowrapTrimFlags)
    }

    fun getAutowrapTrimFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapTrimFlagsBind, handle)
    }

    fun setJustificationFlags(justificationFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setJustificationFlagsBind, handle, justificationFlags)
    }

    fun getJustificationFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getJustificationFlagsBind, handle)
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

    fun setDrawFlag(flag: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setDrawFlagBind, handle, flag, enabled)
    }

    fun getDrawFlag(flag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getDrawFlagBind, handle, flag)
    }

    fun setBillboardMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBillboardModeBind, handle, mode)
    }

    fun getBillboardMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBillboardModeBind, handle)
    }

    fun setAlphaCutMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlphaCutModeBind, handle, mode)
    }

    fun getAlphaCutMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlphaCutModeBind, handle)
    }

    fun setAlphaScissorThreshold(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAlphaScissorThresholdBind, handle, threshold)
    }

    fun getAlphaScissorThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaScissorThresholdBind, handle)
    }

    fun setAlphaHashScale(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAlphaHashScaleBind, handle, threshold)
    }

    fun getAlphaHashScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaHashScaleBind, handle)
    }

    fun setAlphaAntialiasing(alphaAa: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlphaAntialiasingBind, handle, alphaAa)
    }

    fun getAlphaAntialiasing(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlphaAntialiasingBind, handle)
    }

    fun setAlphaAntialiasingEdge(edge: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAlphaAntialiasingEdgeBind, handle, edge)
    }

    fun getAlphaAntialiasingEdge(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaAntialiasingEdgeBind, handle)
    }

    fun setTextureFilter(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureFilterBind, handle, mode)
    }

    fun getTextureFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureFilterBind, handle)
    }

    fun generateTriangleMesh(): TriangleMesh? {
        return TriangleMesh.wrap(ObjectCalls.ptrcallNoArgsRetObject(generateTriangleMeshBind, handle))
    }

    companion object {
        const val FLAG_SHADED: Long = 0L
        const val FLAG_DOUBLE_SIDED: Long = 1L
        const val FLAG_DISABLE_DEPTH_TEST: Long = 2L
        const val FLAG_FIXED_SIZE: Long = 3L
        const val FLAG_MAX: Long = 4L
        const val ALPHA_CUT_DISABLED: Long = 0L
        const val ALPHA_CUT_DISCARD: Long = 1L
        const val ALPHA_CUT_OPAQUE_PREPASS: Long = 2L
        const val ALPHA_CUT_HASH: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Label3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Label3D? =
            if (handle.address() == 0L) null else Label3D(handle)

        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        private val setHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        private val getHorizontalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)
        }

        private const val SET_VERTICAL_ALIGNMENT_HASH = 1796458609L
        private val setVerticalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_vertical_alignment", SET_VERTICAL_ALIGNMENT_HASH)
        }

        private const val GET_VERTICAL_ALIGNMENT_HASH = 3274884059L
        private val getVerticalAlignmentBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_vertical_alignment", GET_VERTICAL_ALIGNMENT_HASH)
        }

        private const val SET_MODULATE_HASH = 2920490490L
        private val setModulateBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_modulate", SET_MODULATE_HASH)
        }

        private const val GET_MODULATE_HASH = 3444240500L
        private val getModulateBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_modulate", GET_MODULATE_HASH)
        }

        private const val SET_OUTLINE_MODULATE_HASH = 2920490490L
        private val setOutlineModulateBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_outline_modulate", SET_OUTLINE_MODULATE_HASH)
        }

        private const val GET_OUTLINE_MODULATE_HASH = 3444240500L
        private val getOutlineModulateBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_outline_modulate", GET_OUTLINE_MODULATE_HASH)
        }

        private const val SET_TEXT_HASH = 83702148L
        private val setTextBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_text", SET_TEXT_HASH)
        }

        private const val GET_TEXT_HASH = 201670096L
        private val getTextBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_text", GET_TEXT_HASH)
        }

        private const val SET_TEXT_DIRECTION_HASH = 1418190634L
        private val setTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_text_direction", SET_TEXT_DIRECTION_HASH)
        }

        private const val GET_TEXT_DIRECTION_HASH = 2516697328L
        private val getTextDirectionBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_text_direction", GET_TEXT_DIRECTION_HASH)
        }

        private const val SET_LANGUAGE_HASH = 83702148L
        private val setLanguageBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_language", SET_LANGUAGE_HASH)
        }

        private const val GET_LANGUAGE_HASH = 201670096L
        private val getLanguageBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_language", GET_LANGUAGE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 55961453L
        private val setStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_structured_text_bidi_override", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3385126229L
        private val getStructuredTextBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_structured_text_bidi_override", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)
        }

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
        private val setStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 3995934104L
        private val getStructuredTextBidiOverrideOptionsBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)
        }

        private const val SET_UPPERCASE_HASH = 2586408642L
        private val setUppercaseBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_uppercase", SET_UPPERCASE_HASH)
        }

        private const val IS_UPPERCASE_HASH = 36873697L
        private val isUppercaseBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "is_uppercase", IS_UPPERCASE_HASH)
        }

        private const val SET_RENDER_PRIORITY_HASH = 1286410249L
        private val setRenderPriorityBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_render_priority", SET_RENDER_PRIORITY_HASH)
        }

        private const val GET_RENDER_PRIORITY_HASH = 3905245786L
        private val getRenderPriorityBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_render_priority", GET_RENDER_PRIORITY_HASH)
        }

        private const val SET_OUTLINE_RENDER_PRIORITY_HASH = 1286410249L
        private val setOutlineRenderPriorityBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_outline_render_priority", SET_OUTLINE_RENDER_PRIORITY_HASH)
        }

        private const val GET_OUTLINE_RENDER_PRIORITY_HASH = 3905245786L
        private val getOutlineRenderPriorityBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_outline_render_priority", GET_OUTLINE_RENDER_PRIORITY_HASH)
        }

        private const val SET_FONT_HASH = 1262170328L
        private val setFontBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_font", SET_FONT_HASH)
        }

        private const val GET_FONT_HASH = 3229501585L
        private val getFontBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_font", GET_FONT_HASH)
        }

        private const val SET_FONT_SIZE_HASH = 1286410249L
        private val setFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_font_size", SET_FONT_SIZE_HASH)
        }

        private const val GET_FONT_SIZE_HASH = 3905245786L
        private val getFontSizeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_font_size", GET_FONT_SIZE_HASH)
        }

        private const val SET_OUTLINE_SIZE_HASH = 1286410249L
        private val setOutlineSizeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_outline_size", SET_OUTLINE_SIZE_HASH)
        }

        private const val GET_OUTLINE_SIZE_HASH = 3905245786L
        private val getOutlineSizeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_outline_size", GET_OUTLINE_SIZE_HASH)
        }

        private const val SET_LINE_SPACING_HASH = 373806689L
        private val setLineSpacingBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_line_spacing", SET_LINE_SPACING_HASH)
        }

        private const val GET_LINE_SPACING_HASH = 1740695150L
        private val getLineSpacingBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_line_spacing", GET_LINE_SPACING_HASH)
        }

        private const val SET_AUTOWRAP_MODE_HASH = 3289138044L
        private val setAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_autowrap_mode", SET_AUTOWRAP_MODE_HASH)
        }

        private const val GET_AUTOWRAP_MODE_HASH = 1549071663L
        private val getAutowrapModeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_autowrap_mode", GET_AUTOWRAP_MODE_HASH)
        }

        private const val SET_AUTOWRAP_TRIM_FLAGS_HASH = 2809697122L
        private val setAutowrapTrimFlagsBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_autowrap_trim_flags", SET_AUTOWRAP_TRIM_FLAGS_HASH)
        }

        private const val GET_AUTOWRAP_TRIM_FLAGS_HASH = 2340632602L
        private val getAutowrapTrimFlagsBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_autowrap_trim_flags", GET_AUTOWRAP_TRIM_FLAGS_HASH)
        }

        private const val SET_JUSTIFICATION_FLAGS_HASH = 2877345813L
        private val setJustificationFlagsBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_justification_flags", SET_JUSTIFICATION_FLAGS_HASH)
        }

        private const val GET_JUSTIFICATION_FLAGS_HASH = 1583363614L
        private val getJustificationFlagsBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_justification_flags", GET_JUSTIFICATION_FLAGS_HASH)
        }

        private const val SET_WIDTH_HASH = 373806689L
        private val setWidthBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_width", SET_WIDTH_HASH)
        }

        private const val GET_WIDTH_HASH = 1740695150L
        private val getWidthBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_width", GET_WIDTH_HASH)
        }

        private const val SET_PIXEL_SIZE_HASH = 373806689L
        private val setPixelSizeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_pixel_size", SET_PIXEL_SIZE_HASH)
        }

        private const val GET_PIXEL_SIZE_HASH = 1740695150L
        private val getPixelSizeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_pixel_size", GET_PIXEL_SIZE_HASH)
        }

        private const val SET_OFFSET_HASH = 743155724L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_OFFSET_HASH = 3341600327L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_offset", GET_OFFSET_HASH)
        }

        private const val SET_DRAW_FLAG_HASH = 1285833066L
        private val setDrawFlagBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_draw_flag", SET_DRAW_FLAG_HASH)
        }

        private const val GET_DRAW_FLAG_HASH = 259226453L
        private val getDrawFlagBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_draw_flag", GET_DRAW_FLAG_HASH)
        }

        private const val SET_BILLBOARD_MODE_HASH = 4202036497L
        private val setBillboardModeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_billboard_mode", SET_BILLBOARD_MODE_HASH)
        }

        private const val GET_BILLBOARD_MODE_HASH = 1283840139L
        private val getBillboardModeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_billboard_mode", GET_BILLBOARD_MODE_HASH)
        }

        private const val SET_ALPHA_CUT_MODE_HASH = 2549142916L
        private val setAlphaCutModeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_alpha_cut_mode", SET_ALPHA_CUT_MODE_HASH)
        }

        private const val GET_ALPHA_CUT_MODE_HASH = 219468601L
        private val getAlphaCutModeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_alpha_cut_mode", GET_ALPHA_CUT_MODE_HASH)
        }

        private const val SET_ALPHA_SCISSOR_THRESHOLD_HASH = 373806689L
        private val setAlphaScissorThresholdBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_alpha_scissor_threshold", SET_ALPHA_SCISSOR_THRESHOLD_HASH)
        }

        private const val GET_ALPHA_SCISSOR_THRESHOLD_HASH = 1740695150L
        private val getAlphaScissorThresholdBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_alpha_scissor_threshold", GET_ALPHA_SCISSOR_THRESHOLD_HASH)
        }

        private const val SET_ALPHA_HASH_SCALE_HASH = 373806689L
        private val setAlphaHashScaleBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_alpha_hash_scale", SET_ALPHA_HASH_SCALE_HASH)
        }

        private const val GET_ALPHA_HASH_SCALE_HASH = 1740695150L
        private val getAlphaHashScaleBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_alpha_hash_scale", GET_ALPHA_HASH_SCALE_HASH)
        }

        private const val SET_ALPHA_ANTIALIASING_HASH = 3212649852L
        private val setAlphaAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_alpha_antialiasing", SET_ALPHA_ANTIALIASING_HASH)
        }

        private const val GET_ALPHA_ANTIALIASING_HASH = 2889939400L
        private val getAlphaAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_alpha_antialiasing", GET_ALPHA_ANTIALIASING_HASH)
        }

        private const val SET_ALPHA_ANTIALIASING_EDGE_HASH = 373806689L
        private val setAlphaAntialiasingEdgeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_alpha_antialiasing_edge", SET_ALPHA_ANTIALIASING_EDGE_HASH)
        }

        private const val GET_ALPHA_ANTIALIASING_EDGE_HASH = 1740695150L
        private val getAlphaAntialiasingEdgeBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_alpha_antialiasing_edge", GET_ALPHA_ANTIALIASING_EDGE_HASH)
        }

        private const val SET_TEXTURE_FILTER_HASH = 22904437L
        private val setTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "set_texture_filter", SET_TEXTURE_FILTER_HASH)
        }

        private const val GET_TEXTURE_FILTER_HASH = 3289213076L
        private val getTextureFilterBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "get_texture_filter", GET_TEXTURE_FILTER_HASH)
        }

        private const val GENERATE_TRIANGLE_MESH_HASH = 3476533166L
        private val generateTriangleMeshBind by lazy {
            ObjectCalls.getMethodBind("Label3D", "generate_triangle_mesh", GENERATE_TRIANGLE_MESH_HASH)
        }
    }
}
