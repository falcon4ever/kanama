package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.Vector2

/**
 * A node for displaying plain text in 3D space.
 *
 * Generated from Godot docs: Label3D
 */
class Label3D(handle: GodotHandle) : GeometryInstance3D(handle) {
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

    var billboard: BaseMaterial3D.BillboardMode
        @JvmName("billboardProperty")
        get() = getBillboardMode()
        @JvmName("setBillboardProperty")
        set(value) = setBillboardMode(value)

    var shaded: Boolean
        @JvmName("shadedProperty")
        get() = getDrawFlag(Label3D.DrawFlags.SHADED)
        @JvmName("setShadedProperty")
        set(value) = setDrawFlag(Label3D.DrawFlags.SHADED, value)

    var doubleSided: Boolean
        @JvmName("doubleSidedProperty")
        get() = getDrawFlag(Label3D.DrawFlags.DOUBLE_SIDED)
        @JvmName("setDoubleSidedProperty")
        set(value) = setDrawFlag(Label3D.DrawFlags.DOUBLE_SIDED, value)

    var noDepthTest: Boolean
        @JvmName("noDepthTestProperty")
        get() = getDrawFlag(Label3D.DrawFlags.DISABLE_DEPTH_TEST)
        @JvmName("setNoDepthTestProperty")
        set(value) = setDrawFlag(Label3D.DrawFlags.DISABLE_DEPTH_TEST, value)

    var fixedSize: Boolean
        @JvmName("fixedSizeProperty")
        get() = getDrawFlag(Label3D.DrawFlags.FIXED_SIZE)
        @JvmName("setFixedSizeProperty")
        set(value) = setDrawFlag(Label3D.DrawFlags.FIXED_SIZE, value)

    var alphaCut: Label3D.AlphaCutMode
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

    var alphaAntialiasingMode: BaseMaterial3D.AlphaAntiAliasing
        @JvmName("alphaAntialiasingModeProperty")
        get() = getAlphaAntialiasing()
        @JvmName("setAlphaAntialiasingModeProperty")
        set(value) = setAlphaAntialiasing(value)

    var alphaAntialiasingEdge: Double
        @JvmName("alphaAntialiasingEdgeProperty")
        get() = getAlphaAntialiasingEdge()
        @JvmName("setAlphaAntialiasingEdgeProperty")
        set(value) = setAlphaAntialiasingEdge(value)

    var textureFilter: BaseMaterial3D.TextureFilter
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

    var width: Double
        @JvmName("widthProperty")
        get() = getWidth()
        @JvmName("setWidthProperty")
        set(value) = setWidth(value)

    var textDirection: TextServer.Direction
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
     * Generated from Godot docs: Label3D.set_horizontal_alignment
     */
    fun setHorizontalAlignment(alignment: HorizontalAlignment) {
        ObjectCalls.ptrcallWithLongArg(Binds.setHorizontalAlignmentBind, segment, alignment.value)
    }

    /**
     * Controls the text's horizontal alignment. Supports left, center, right, and fill (also known as
     * justify).
     *
     * Generated from Godot docs: Label3D.get_horizontal_alignment
     */
    fun getHorizontalAlignment(): HorizontalAlignment {
        return HorizontalAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHorizontalAlignmentBind, segment))
    }

    /**
     * Controls the text's vertical alignment. Supports top, center, and bottom.
     *
     * Generated from Godot docs: Label3D.set_vertical_alignment
     */
    fun setVerticalAlignment(alignment: VerticalAlignment) {
        ObjectCalls.ptrcallWithLongArg(Binds.setVerticalAlignmentBind, segment, alignment.value)
    }

    /**
     * Controls the text's vertical alignment. Supports top, center, and bottom.
     *
     * Generated from Godot docs: Label3D.get_vertical_alignment
     */
    fun getVerticalAlignment(): VerticalAlignment {
        return VerticalAlignment(ObjectCalls.ptrcallNoArgsRetLong(Binds.getVerticalAlignmentBind, segment))
    }

    /**
     * Text `Color` of the `Label3D`.
     *
     * Generated from Godot docs: Label3D.set_modulate
     */
    fun setModulate(modulate: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setModulateBind, segment, modulate)
    }

    /**
     * Text `Color` of the `Label3D`.
     *
     * Generated from Godot docs: Label3D.get_modulate
     */
    fun getModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getModulateBind, segment)
    }

    /**
     * The tint of text outline.
     *
     * Generated from Godot docs: Label3D.set_outline_modulate
     */
    fun setOutlineModulate(modulate: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setOutlineModulateBind, segment, modulate)
    }

    /**
     * The tint of text outline.
     *
     * Generated from Godot docs: Label3D.get_outline_modulate
     */
    fun getOutlineModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getOutlineModulateBind, segment)
    }

    /**
     * The text to display on screen.
     *
     * Generated from Godot docs: Label3D.set_text
     */
    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setTextBind, segment, text)
    }

    /**
     * The text to display on screen.
     *
     * Generated from Godot docs: Label3D.get_text
     */
    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getTextBind, segment)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: Label3D.set_text_direction
     */
    fun setTextDirection(direction: TextServer.Direction) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTextDirectionBind, segment, direction.value)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: Label3D.get_text_direction
     */
    fun getTextDirection(): TextServer.Direction {
        return TextServer.Direction(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextDirectionBind, segment))
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: Label3D.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(Binds.setLanguageBind, segment, language)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: Label3D.get_language
     */
    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getLanguageBind, segment)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: Label3D.set_structured_text_bidi_override
     */
    fun setStructuredTextBidiOverride(parser: TextServer.StructuredTextParser) {
        ObjectCalls.ptrcallWithLongArg(Binds.setStructuredTextBidiOverrideBind, segment, parser.value)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: Label3D.get_structured_text_bidi_override
     */
    fun getStructuredTextBidiOverride(): TextServer.StructuredTextParser {
        return TextServer.StructuredTextParser(ObjectCalls.ptrcallNoArgsRetLong(Binds.getStructuredTextBidiOverrideBind, segment))
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: Label3D.set_structured_text_bidi_override_options
     */
    fun setStructuredTextBidiOverrideOptions(args: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(Binds.setStructuredTextBidiOverrideOptionsBind, segment, args)
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: Label3D.get_structured_text_bidi_override_options
     */
    fun getStructuredTextBidiOverrideOptions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getStructuredTextBidiOverrideOptionsBind, segment)
    }

    /**
     * If `true`, all the text displays as UPPERCASE.
     *
     * Generated from Godot docs: Label3D.set_uppercase
     */
    fun setUppercase(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUppercaseBind, segment, enable)
    }

    /**
     * If `true`, all the text displays as UPPERCASE.
     *
     * Generated from Godot docs: Label3D.is_uppercase
     */
    fun isUppercase(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUppercaseBind, segment)
    }

    /**
     * Sets the render priority for the text. Higher priority objects will be sorted in front of lower
     * priority objects. Note: This only applies if `alpha_cut` is set to `AlphaCutMode.DISABLED`
     * (default value). Note: This only applies to sorting of transparent objects. This will not impact
     * how transparent objects are sorted relative to opaque objects. This is because opaque objects
     * are not sorted, while transparent objects are sorted from back to front (subject to priority).
     *
     * Generated from Godot docs: Label3D.set_render_priority
     */
    fun setRenderPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setRenderPriorityBind, segment, priority)
    }

    /**
     * Sets the render priority for the text. Higher priority objects will be sorted in front of lower
     * priority objects. Note: This only applies if `alpha_cut` is set to `AlphaCutMode.DISABLED`
     * (default value). Note: This only applies to sorting of transparent objects. This will not impact
     * how transparent objects are sorted relative to opaque objects. This is because opaque objects
     * are not sorted, while transparent objects are sorted from back to front (subject to priority).
     *
     * Generated from Godot docs: Label3D.get_render_priority
     */
    fun getRenderPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getRenderPriorityBind, segment)
    }

    /**
     * Sets the render priority for the text outline. Higher priority objects will be sorted in front
     * of lower priority objects. Note: This only applies if `alpha_cut` is set to
     * `AlphaCutMode.DISABLED` (default value). Note: This only applies to sorting of transparent
     * objects. This will not impact how transparent objects are sorted relative to opaque objects.
     * This is because opaque objects are not sorted, while transparent objects are sorted from back to
     * front (subject to priority).
     *
     * Generated from Godot docs: Label3D.set_outline_render_priority
     */
    fun setOutlineRenderPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setOutlineRenderPriorityBind, segment, priority)
    }

    /**
     * Sets the render priority for the text outline. Higher priority objects will be sorted in front
     * of lower priority objects. Note: This only applies if `alpha_cut` is set to
     * `AlphaCutMode.DISABLED` (default value). Note: This only applies to sorting of transparent
     * objects. This will not impact how transparent objects are sorted relative to opaque objects.
     * This is because opaque objects are not sorted, while transparent objects are sorted from back to
     * front (subject to priority).
     *
     * Generated from Godot docs: Label3D.get_outline_render_priority
     */
    fun getOutlineRenderPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getOutlineRenderPriorityBind, segment)
    }

    /**
     * Font configuration used to display text.
     *
     * Generated from Godot docs: Label3D.set_font
     */
    fun setFont(font: Font?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setFontBind, segment, listOf(font?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Font configuration used to display text.
     *
     * Generated from Godot docs: Label3D.get_font
     */
    fun getFont(): Font? {
        return Font.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getFontBind, segment))
    }

    /**
     * Font size of the `Label3D`'s text. To make the font look more detailed when up close, increase
     * `font_size` while decreasing `pixel_size` at the same time. Higher font sizes require more time
     * to render new characters, which can cause stuttering during gameplay.
     *
     * Generated from Godot docs: Label3D.set_font_size
     */
    fun setFontSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setFontSizeBind, segment, size)
    }

    /**
     * Font size of the `Label3D`'s text. To make the font look more detailed when up close, increase
     * `font_size` while decreasing `pixel_size` at the same time. Higher font sizes require more time
     * to render new characters, which can cause stuttering during gameplay.
     *
     * Generated from Godot docs: Label3D.get_font_size
     */
    fun getFontSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFontSizeBind, segment)
    }

    /**
     * Text outline size.
     *
     * Generated from Godot docs: Label3D.set_outline_size
     */
    fun setOutlineSize(outlineSize: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setOutlineSizeBind, segment, outlineSize)
    }

    /**
     * Text outline size.
     *
     * Generated from Godot docs: Label3D.get_outline_size
     */
    fun getOutlineSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getOutlineSizeBind, segment)
    }

    /**
     * Additional vertical spacing between lines (in pixels), spacing is added to line descent. This
     * value can be negative.
     *
     * Generated from Godot docs: Label3D.set_line_spacing
     */
    fun setLineSpacing(lineSpacing: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLineSpacingBind, segment, lineSpacing)
    }

    /**
     * Additional vertical spacing between lines (in pixels), spacing is added to line descent. This
     * value can be negative.
     *
     * Generated from Godot docs: Label3D.get_line_spacing
     */
    fun getLineSpacing(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLineSpacingBind, segment)
    }

    /**
     * If set to something other than `TextServer.AutowrapMode.OFF`, the text gets wrapped inside the
     * node's bounding rectangle. If you resize the node, it will change its height automatically to
     * show all the text.
     *
     * Generated from Godot docs: Label3D.set_autowrap_mode
     */
    fun setAutowrapMode(autowrapMode: TextServer.AutowrapMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAutowrapModeBind, segment, autowrapMode.value)
    }

    /**
     * If set to something other than `TextServer.AutowrapMode.OFF`, the text gets wrapped inside the
     * node's bounding rectangle. If you resize the node, it will change its height automatically to
     * show all the text.
     *
     * Generated from Godot docs: Label3D.get_autowrap_mode
     */
    fun getAutowrapMode(): TextServer.AutowrapMode {
        return TextServer.AutowrapMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAutowrapModeBind, segment))
    }

    /**
     * Autowrap space trimming flags. See `TextServer.LineBreakFlag.TRIM_START_EDGE_SPACES` and
     * `TextServer.LineBreakFlag.TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: Label3D.set_autowrap_trim_flags
     */
    fun setAutowrapTrimFlags(autowrapTrimFlags: TextServer.LineBreakFlag) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAutowrapTrimFlagsBind, segment, autowrapTrimFlags.value)
    }

    /**
     * Autowrap space trimming flags. See `TextServer.LineBreakFlag.TRIM_START_EDGE_SPACES` and
     * `TextServer.LineBreakFlag.TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: Label3D.get_autowrap_trim_flags
     */
    fun getAutowrapTrimFlags(): TextServer.LineBreakFlag {
        return TextServer.LineBreakFlag(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAutowrapTrimFlagsBind, segment))
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: Label3D.set_justification_flags
     */
    fun setJustificationFlags(justificationFlags: TextServer.JustificationFlag) {
        ObjectCalls.ptrcallWithLongArg(Binds.setJustificationFlagsBind, segment, justificationFlags.value)
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: Label3D.get_justification_flags
     */
    fun getJustificationFlags(): TextServer.JustificationFlag {
        return TextServer.JustificationFlag(ObjectCalls.ptrcallNoArgsRetLong(Binds.getJustificationFlagsBind, segment))
    }

    /**
     * Text width (in pixels), used for autowrap and fill alignment.
     *
     * Generated from Godot docs: Label3D.set_width
     */
    fun setWidth(width: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setWidthBind, segment, width)
    }

    /**
     * Text width (in pixels), used for autowrap and fill alignment.
     *
     * Generated from Godot docs: Label3D.get_width
     */
    fun getWidth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getWidthBind, segment)
    }

    /**
     * The size of one pixel's width on the label to scale it in 3D. To make the font look more
     * detailed when up close, increase `font_size` while decreasing `pixel_size` at the same time.
     *
     * Generated from Godot docs: Label3D.set_pixel_size
     */
    fun setPixelSize(pixelSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPixelSizeBind, segment, pixelSize)
    }

    /**
     * The size of one pixel's width on the label to scale it in 3D. To make the font look more
     * detailed when up close, increase `font_size` while decreasing `pixel_size` at the same time.
     *
     * Generated from Godot docs: Label3D.get_pixel_size
     */
    fun getPixelSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPixelSizeBind, segment)
    }

    /**
     * The text drawing offset (in pixels).
     *
     * Generated from Godot docs: Label3D.set_offset
     */
    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setOffsetBind, segment, offset)
    }

    /**
     * The text drawing offset (in pixels).
     *
     * Generated from Godot docs: Label3D.get_offset
     */
    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getOffsetBind, segment)
    }

    /**
     * If `true`, the `Light3D` in the `Environment` has effects on the label.
     *
     * Generated from Godot docs: Label3D.set_draw_flag
     */
    fun setDrawFlag(flag: Label3D.DrawFlags, enabled: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(Binds.setDrawFlagBind, segment, flag.value, enabled)
    }

    /**
     * If `true`, the `Light3D` in the `Environment` has effects on the label.
     *
     * Generated from Godot docs: Label3D.get_draw_flag
     */
    fun getDrawFlag(flag: Label3D.DrawFlags): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.getDrawFlagBind, segment, flag.value)
    }

    /**
     * The billboard mode to use for the label.
     *
     * Generated from Godot docs: Label3D.set_billboard_mode
     */
    fun setBillboardMode(mode: BaseMaterial3D.BillboardMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setBillboardModeBind, segment, mode.value)
    }

    /**
     * The billboard mode to use for the label.
     *
     * Generated from Godot docs: Label3D.get_billboard_mode
     */
    fun getBillboardMode(): BaseMaterial3D.BillboardMode {
        return BaseMaterial3D.BillboardMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getBillboardModeBind, segment))
    }

    /**
     * The alpha cutting mode to use for the sprite.
     *
     * Generated from Godot docs: Label3D.set_alpha_cut_mode
     */
    fun setAlphaCutMode(mode: Label3D.AlphaCutMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAlphaCutModeBind, segment, mode.value)
    }

    /**
     * The alpha cutting mode to use for the sprite.
     *
     * Generated from Godot docs: Label3D.get_alpha_cut_mode
     */
    fun getAlphaCutMode(): Label3D.AlphaCutMode {
        return Label3D.AlphaCutMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAlphaCutModeBind, segment))
    }

    /**
     * Threshold at which the alpha scissor will discard values.
     *
     * Generated from Godot docs: Label3D.set_alpha_scissor_threshold
     */
    fun setAlphaScissorThreshold(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAlphaScissorThresholdBind, segment, threshold)
    }

    /**
     * Threshold at which the alpha scissor will discard values.
     *
     * Generated from Godot docs: Label3D.get_alpha_scissor_threshold
     */
    fun getAlphaScissorThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAlphaScissorThresholdBind, segment)
    }

    /**
     * The hashing scale for Alpha Hash. Recommended values between `0` and `2`.
     *
     * Generated from Godot docs: Label3D.set_alpha_hash_scale
     */
    fun setAlphaHashScale(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAlphaHashScaleBind, segment, threshold)
    }

    /**
     * The hashing scale for Alpha Hash. Recommended values between `0` and `2`.
     *
     * Generated from Godot docs: Label3D.get_alpha_hash_scale
     */
    fun getAlphaHashScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAlphaHashScaleBind, segment)
    }

    /**
     * The type of alpha antialiasing to apply.
     *
     * Generated from Godot docs: Label3D.set_alpha_antialiasing
     */
    fun setAlphaAntialiasing(alphaAa: BaseMaterial3D.AlphaAntiAliasing) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAlphaAntialiasingBind, segment, alphaAa.value)
    }

    /**
     * The type of alpha antialiasing to apply.
     *
     * Generated from Godot docs: Label3D.get_alpha_antialiasing
     */
    fun getAlphaAntialiasing(): BaseMaterial3D.AlphaAntiAliasing {
        return BaseMaterial3D.AlphaAntiAliasing(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAlphaAntialiasingBind, segment))
    }

    /**
     * Threshold at which antialiasing will be applied on the alpha channel.
     *
     * Generated from Godot docs: Label3D.set_alpha_antialiasing_edge
     */
    fun setAlphaAntialiasingEdge(edge: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAlphaAntialiasingEdgeBind, segment, edge)
    }

    /**
     * Threshold at which antialiasing will be applied on the alpha channel.
     *
     * Generated from Godot docs: Label3D.get_alpha_antialiasing_edge
     */
    fun getAlphaAntialiasingEdge(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAlphaAntialiasingEdgeBind, segment)
    }

    /**
     * Filter flags for the texture.
     *
     * Generated from Godot docs: Label3D.set_texture_filter
     */
    fun setTextureFilter(mode: BaseMaterial3D.TextureFilter) {
        ObjectCalls.ptrcallWithLongArg(Binds.setTextureFilterBind, segment, mode.value)
    }

    /**
     * Filter flags for the texture.
     *
     * Generated from Godot docs: Label3D.get_texture_filter
     */
    fun getTextureFilter(): BaseMaterial3D.TextureFilter {
        return BaseMaterial3D.TextureFilter(ObjectCalls.ptrcallNoArgsRetLong(Binds.getTextureFilterBind, segment))
    }

    /**
     * Returns a `TriangleMesh` with the label's vertices following its current configuration (such as
     * its `pixel_size`).
     *
     * Generated from Godot docs: Label3D.generate_triangle_mesh
     */
    fun generateTriangleMesh(): TriangleMesh? {
        return TriangleMesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.generateTriangleMeshBind, segment))
    }

    /**
     * Godot's `Label3D.DrawFlags` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Label3D.DrawFlags.<NAME>`).
     *
     * Generated from Godot docs: Label3D.DrawFlags
     */
    @JvmInline
    value class DrawFlags(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * If set, lights in the environment affect the label.
             *
             * Generated from Godot docs: Label3D.FLAG_SHADED
             */
            val SHADED: DrawFlags get() = DrawFlags(0L)
            /**
             * If set, text can be seen from the back as well. If not, the text is invisible when looking at it
             * from behind.
             *
             * Generated from Godot docs: Label3D.FLAG_DOUBLE_SIDED
             */
            val DOUBLE_SIDED: DrawFlags get() = DrawFlags(1L)
            /**
             * Disables the depth test, so this object is drawn on top of all others. However, objects drawn
             * after it in the draw order may cover it.
             *
             * Generated from Godot docs: Label3D.FLAG_DISABLE_DEPTH_TEST
             */
            val DISABLE_DEPTH_TEST: DrawFlags get() = DrawFlags(2L)
            /**
             * Label is scaled by depth so that it always appears the same size on screen.
             *
             * Generated from Godot docs: Label3D.FLAG_FIXED_SIZE
             */
            val FIXED_SIZE: DrawFlags get() = DrawFlags(3L)
            /**
             * Represents the size of the `DrawFlags` enum.
             *
             * Generated from Godot docs: Label3D.FLAG_MAX
             */
            val MAX: DrawFlags get() = DrawFlags(4L)
        }
    }

    /**
     * Godot's `Label3D.AlphaCutMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Label3D.AlphaCutMode.<NAME>`).
     *
     * Generated from Godot docs: Label3D.AlphaCutMode
     */
    @JvmInline
    value class AlphaCutMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * This mode performs standard alpha blending. It can display translucent areas, but transparency
             * sorting issues may be visible when multiple transparent materials are overlapping.
             * `GeometryInstance3D.cast_shadow` has no effect when this transparency mode is used; the
             * `Label3D` will never cast shadows.
             *
             * Generated from Godot docs: Label3D.ALPHA_CUT_DISABLED
             */
            val DISABLED: AlphaCutMode get() = AlphaCutMode(0L)
            /**
             * This mode only allows fully transparent or fully opaque pixels. Harsh edges will be visible
             * unless some form of screen-space antialiasing is enabled (see
             * `ProjectSettings.rendering/anti_aliasing/quality/screen_space_aa`). This mode is also known as
             * alpha testing or 1-bit transparency. Note: This mode might have issues with anti-aliased fonts
             * and outlines, try adjusting `alpha_scissor_threshold` or using MSDF font. Note: When using text
             * with overlapping glyphs (e.g., cursive scripts), this mode might have transparency sorting
             * issues between the main text and the outline.
             *
             * Generated from Godot docs: Label3D.ALPHA_CUT_DISCARD
             */
            val DISCARD: AlphaCutMode get() = AlphaCutMode(1L)
            /**
             * This mode draws fully opaque pixels in the depth prepass. This is slower than
             * `AlphaCutMode.DISABLED` or `AlphaCutMode.DISCARD`, but it allows displaying translucent areas
             * and smooth edges while using proper sorting. Note: When using text with overlapping glyphs
             * (e.g., cursive scripts), this mode might have transparency sorting issues between the main text
             * and the outline.
             *
             * Generated from Godot docs: Label3D.ALPHA_CUT_OPAQUE_PREPASS
             */
            val OPAQUE_PREPASS: AlphaCutMode get() = AlphaCutMode(2L)
            /**
             * This mode draws cuts off all values below a spatially-deterministic threshold, the rest will
             * remain opaque.
             *
             * Generated from Godot docs: Label3D.ALPHA_CUT_HASH
             */
            val HASH: AlphaCutMode get() = AlphaCutMode(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Label3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): Label3D? =
            if (handle.address() == 0L) null else Label3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_HORIZONTAL_ALIGNMENT_HASH = 2312603777L
        @JvmField
        val setHorizontalAlignmentBind =
            ObjectCalls.getMethodBind("Label3D", "set_horizontal_alignment", SET_HORIZONTAL_ALIGNMENT_HASH)

        private const val GET_HORIZONTAL_ALIGNMENT_HASH = 341400642L
        @JvmField
        val getHorizontalAlignmentBind =
            ObjectCalls.getMethodBind("Label3D", "get_horizontal_alignment", GET_HORIZONTAL_ALIGNMENT_HASH)

        private const val SET_VERTICAL_ALIGNMENT_HASH = 1796458609L
        @JvmField
        val setVerticalAlignmentBind =
            ObjectCalls.getMethodBind("Label3D", "set_vertical_alignment", SET_VERTICAL_ALIGNMENT_HASH)

        private const val GET_VERTICAL_ALIGNMENT_HASH = 3274884059L
        @JvmField
        val getVerticalAlignmentBind =
            ObjectCalls.getMethodBind("Label3D", "get_vertical_alignment", GET_VERTICAL_ALIGNMENT_HASH)

        private const val SET_MODULATE_HASH = 2920490490L
        @JvmField
        val setModulateBind =
            ObjectCalls.getMethodBind("Label3D", "set_modulate", SET_MODULATE_HASH)

        private const val GET_MODULATE_HASH = 3444240500L
        @JvmField
        val getModulateBind =
            ObjectCalls.getMethodBind("Label3D", "get_modulate", GET_MODULATE_HASH)

        private const val SET_OUTLINE_MODULATE_HASH = 2920490490L
        @JvmField
        val setOutlineModulateBind =
            ObjectCalls.getMethodBind("Label3D", "set_outline_modulate", SET_OUTLINE_MODULATE_HASH)

        private const val GET_OUTLINE_MODULATE_HASH = 3444240500L
        @JvmField
        val getOutlineModulateBind =
            ObjectCalls.getMethodBind("Label3D", "get_outline_modulate", GET_OUTLINE_MODULATE_HASH)

        private const val SET_TEXT_HASH = 83702148L
        @JvmField
        val setTextBind =
            ObjectCalls.getMethodBind("Label3D", "set_text", SET_TEXT_HASH)

        private const val GET_TEXT_HASH = 201670096L
        @JvmField
        val getTextBind =
            ObjectCalls.getMethodBind("Label3D", "get_text", GET_TEXT_HASH)

        private const val SET_TEXT_DIRECTION_HASH = 1418190634L
        @JvmField
        val setTextDirectionBind =
            ObjectCalls.getMethodBind("Label3D", "set_text_direction", SET_TEXT_DIRECTION_HASH)

        private const val GET_TEXT_DIRECTION_HASH = 2516697328L
        @JvmField
        val getTextDirectionBind =
            ObjectCalls.getMethodBind("Label3D", "get_text_direction", GET_TEXT_DIRECTION_HASH)

        private const val SET_LANGUAGE_HASH = 83702148L
        @JvmField
        val setLanguageBind =
            ObjectCalls.getMethodBind("Label3D", "set_language", SET_LANGUAGE_HASH)

        private const val GET_LANGUAGE_HASH = 201670096L
        @JvmField
        val getLanguageBind =
            ObjectCalls.getMethodBind("Label3D", "get_language", GET_LANGUAGE_HASH)

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 55961453L
        @JvmField
        val setStructuredTextBidiOverrideBind =
            ObjectCalls.getMethodBind("Label3D", "set_structured_text_bidi_override", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH = 3385126229L
        @JvmField
        val getStructuredTextBidiOverrideBind =
            ObjectCalls.getMethodBind("Label3D", "get_structured_text_bidi_override", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_HASH)

        private const val SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 381264803L
        @JvmField
        val setStructuredTextBidiOverrideOptionsBind =
            ObjectCalls.getMethodBind("Label3D", "set_structured_text_bidi_override_options", SET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)

        private const val GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH = 3995934104L
        @JvmField
        val getStructuredTextBidiOverrideOptionsBind =
            ObjectCalls.getMethodBind("Label3D", "get_structured_text_bidi_override_options", GET_STRUCTURED_TEXT_BIDI_OVERRIDE_OPTIONS_HASH)

        private const val SET_UPPERCASE_HASH = 2586408642L
        @JvmField
        val setUppercaseBind =
            ObjectCalls.getMethodBind("Label3D", "set_uppercase", SET_UPPERCASE_HASH)

        private const val IS_UPPERCASE_HASH = 36873697L
        @JvmField
        val isUppercaseBind =
            ObjectCalls.getMethodBind("Label3D", "is_uppercase", IS_UPPERCASE_HASH)

        private const val SET_RENDER_PRIORITY_HASH = 1286410249L
        @JvmField
        val setRenderPriorityBind =
            ObjectCalls.getMethodBind("Label3D", "set_render_priority", SET_RENDER_PRIORITY_HASH)

        private const val GET_RENDER_PRIORITY_HASH = 3905245786L
        @JvmField
        val getRenderPriorityBind =
            ObjectCalls.getMethodBind("Label3D", "get_render_priority", GET_RENDER_PRIORITY_HASH)

        private const val SET_OUTLINE_RENDER_PRIORITY_HASH = 1286410249L
        @JvmField
        val setOutlineRenderPriorityBind =
            ObjectCalls.getMethodBind("Label3D", "set_outline_render_priority", SET_OUTLINE_RENDER_PRIORITY_HASH)

        private const val GET_OUTLINE_RENDER_PRIORITY_HASH = 3905245786L
        @JvmField
        val getOutlineRenderPriorityBind =
            ObjectCalls.getMethodBind("Label3D", "get_outline_render_priority", GET_OUTLINE_RENDER_PRIORITY_HASH)

        private const val SET_FONT_HASH = 1262170328L
        @JvmField
        val setFontBind =
            ObjectCalls.getMethodBind("Label3D", "set_font", SET_FONT_HASH)

        private const val GET_FONT_HASH = 3229501585L
        @JvmField
        val getFontBind =
            ObjectCalls.getMethodBind("Label3D", "get_font", GET_FONT_HASH)

        private const val SET_FONT_SIZE_HASH = 1286410249L
        @JvmField
        val setFontSizeBind =
            ObjectCalls.getMethodBind("Label3D", "set_font_size", SET_FONT_SIZE_HASH)

        private const val GET_FONT_SIZE_HASH = 3905245786L
        @JvmField
        val getFontSizeBind =
            ObjectCalls.getMethodBind("Label3D", "get_font_size", GET_FONT_SIZE_HASH)

        private const val SET_OUTLINE_SIZE_HASH = 1286410249L
        @JvmField
        val setOutlineSizeBind =
            ObjectCalls.getMethodBind("Label3D", "set_outline_size", SET_OUTLINE_SIZE_HASH)

        private const val GET_OUTLINE_SIZE_HASH = 3905245786L
        @JvmField
        val getOutlineSizeBind =
            ObjectCalls.getMethodBind("Label3D", "get_outline_size", GET_OUTLINE_SIZE_HASH)

        private const val SET_LINE_SPACING_HASH = 373806689L
        @JvmField
        val setLineSpacingBind =
            ObjectCalls.getMethodBind("Label3D", "set_line_spacing", SET_LINE_SPACING_HASH)

        private const val GET_LINE_SPACING_HASH = 1740695150L
        @JvmField
        val getLineSpacingBind =
            ObjectCalls.getMethodBind("Label3D", "get_line_spacing", GET_LINE_SPACING_HASH)

        private const val SET_AUTOWRAP_MODE_HASH = 3289138044L
        @JvmField
        val setAutowrapModeBind =
            ObjectCalls.getMethodBind("Label3D", "set_autowrap_mode", SET_AUTOWRAP_MODE_HASH)

        private const val GET_AUTOWRAP_MODE_HASH = 1549071663L
        @JvmField
        val getAutowrapModeBind =
            ObjectCalls.getMethodBind("Label3D", "get_autowrap_mode", GET_AUTOWRAP_MODE_HASH)

        private const val SET_AUTOWRAP_TRIM_FLAGS_HASH = 2809697122L
        @JvmField
        val setAutowrapTrimFlagsBind =
            ObjectCalls.getMethodBind("Label3D", "set_autowrap_trim_flags", SET_AUTOWRAP_TRIM_FLAGS_HASH)

        private const val GET_AUTOWRAP_TRIM_FLAGS_HASH = 2340632602L
        @JvmField
        val getAutowrapTrimFlagsBind =
            ObjectCalls.getMethodBind("Label3D", "get_autowrap_trim_flags", GET_AUTOWRAP_TRIM_FLAGS_HASH)

        private const val SET_JUSTIFICATION_FLAGS_HASH = 2877345813L
        @JvmField
        val setJustificationFlagsBind =
            ObjectCalls.getMethodBind("Label3D", "set_justification_flags", SET_JUSTIFICATION_FLAGS_HASH)

        private const val GET_JUSTIFICATION_FLAGS_HASH = 1583363614L
        @JvmField
        val getJustificationFlagsBind =
            ObjectCalls.getMethodBind("Label3D", "get_justification_flags", GET_JUSTIFICATION_FLAGS_HASH)

        private const val SET_WIDTH_HASH = 373806689L
        @JvmField
        val setWidthBind =
            ObjectCalls.getMethodBind("Label3D", "set_width", SET_WIDTH_HASH)

        private const val GET_WIDTH_HASH = 1740695150L
        @JvmField
        val getWidthBind =
            ObjectCalls.getMethodBind("Label3D", "get_width", GET_WIDTH_HASH)

        private const val SET_PIXEL_SIZE_HASH = 373806689L
        @JvmField
        val setPixelSizeBind =
            ObjectCalls.getMethodBind("Label3D", "set_pixel_size", SET_PIXEL_SIZE_HASH)

        private const val GET_PIXEL_SIZE_HASH = 1740695150L
        @JvmField
        val getPixelSizeBind =
            ObjectCalls.getMethodBind("Label3D", "get_pixel_size", GET_PIXEL_SIZE_HASH)

        private const val SET_OFFSET_HASH = 743155724L
        @JvmField
        val setOffsetBind =
            ObjectCalls.getMethodBind("Label3D", "set_offset", SET_OFFSET_HASH)

        private const val GET_OFFSET_HASH = 3341600327L
        @JvmField
        val getOffsetBind =
            ObjectCalls.getMethodBind("Label3D", "get_offset", GET_OFFSET_HASH)

        private const val SET_DRAW_FLAG_HASH = 1285833066L
        @JvmField
        val setDrawFlagBind =
            ObjectCalls.getMethodBind("Label3D", "set_draw_flag", SET_DRAW_FLAG_HASH)

        private const val GET_DRAW_FLAG_HASH = 259226453L
        @JvmField
        val getDrawFlagBind =
            ObjectCalls.getMethodBind("Label3D", "get_draw_flag", GET_DRAW_FLAG_HASH)

        private const val SET_BILLBOARD_MODE_HASH = 4202036497L
        @JvmField
        val setBillboardModeBind =
            ObjectCalls.getMethodBind("Label3D", "set_billboard_mode", SET_BILLBOARD_MODE_HASH)

        private const val GET_BILLBOARD_MODE_HASH = 1283840139L
        @JvmField
        val getBillboardModeBind =
            ObjectCalls.getMethodBind("Label3D", "get_billboard_mode", GET_BILLBOARD_MODE_HASH)

        private const val SET_ALPHA_CUT_MODE_HASH = 2549142916L
        @JvmField
        val setAlphaCutModeBind =
            ObjectCalls.getMethodBind("Label3D", "set_alpha_cut_mode", SET_ALPHA_CUT_MODE_HASH)

        private const val GET_ALPHA_CUT_MODE_HASH = 219468601L
        @JvmField
        val getAlphaCutModeBind =
            ObjectCalls.getMethodBind("Label3D", "get_alpha_cut_mode", GET_ALPHA_CUT_MODE_HASH)

        private const val SET_ALPHA_SCISSOR_THRESHOLD_HASH = 373806689L
        @JvmField
        val setAlphaScissorThresholdBind =
            ObjectCalls.getMethodBind("Label3D", "set_alpha_scissor_threshold", SET_ALPHA_SCISSOR_THRESHOLD_HASH)

        private const val GET_ALPHA_SCISSOR_THRESHOLD_HASH = 1740695150L
        @JvmField
        val getAlphaScissorThresholdBind =
            ObjectCalls.getMethodBind("Label3D", "get_alpha_scissor_threshold", GET_ALPHA_SCISSOR_THRESHOLD_HASH)

        private const val SET_ALPHA_HASH_SCALE_HASH = 373806689L
        @JvmField
        val setAlphaHashScaleBind =
            ObjectCalls.getMethodBind("Label3D", "set_alpha_hash_scale", SET_ALPHA_HASH_SCALE_HASH)

        private const val GET_ALPHA_HASH_SCALE_HASH = 1740695150L
        @JvmField
        val getAlphaHashScaleBind =
            ObjectCalls.getMethodBind("Label3D", "get_alpha_hash_scale", GET_ALPHA_HASH_SCALE_HASH)

        private const val SET_ALPHA_ANTIALIASING_HASH = 3212649852L
        @JvmField
        val setAlphaAntialiasingBind =
            ObjectCalls.getMethodBind("Label3D", "set_alpha_antialiasing", SET_ALPHA_ANTIALIASING_HASH)

        private const val GET_ALPHA_ANTIALIASING_HASH = 2889939400L
        @JvmField
        val getAlphaAntialiasingBind =
            ObjectCalls.getMethodBind("Label3D", "get_alpha_antialiasing", GET_ALPHA_ANTIALIASING_HASH)

        private const val SET_ALPHA_ANTIALIASING_EDGE_HASH = 373806689L
        @JvmField
        val setAlphaAntialiasingEdgeBind =
            ObjectCalls.getMethodBind("Label3D", "set_alpha_antialiasing_edge", SET_ALPHA_ANTIALIASING_EDGE_HASH)

        private const val GET_ALPHA_ANTIALIASING_EDGE_HASH = 1740695150L
        @JvmField
        val getAlphaAntialiasingEdgeBind =
            ObjectCalls.getMethodBind("Label3D", "get_alpha_antialiasing_edge", GET_ALPHA_ANTIALIASING_EDGE_HASH)

        private const val SET_TEXTURE_FILTER_HASH = 22904437L
        @JvmField
        val setTextureFilterBind =
            ObjectCalls.getMethodBind("Label3D", "set_texture_filter", SET_TEXTURE_FILTER_HASH)

        private const val GET_TEXTURE_FILTER_HASH = 3289213076L
        @JvmField
        val getTextureFilterBind =
            ObjectCalls.getMethodBind("Label3D", "get_texture_filter", GET_TEXTURE_FILTER_HASH)

        private const val GENERATE_TRIANGLE_MESH_HASH = 3476533166L
        @JvmField
        val generateTriangleMeshBind =
            ObjectCalls.getMethodBind("Label3D", "generate_triangle_mesh", GENERATE_TRIANGLE_MESH_HASH)
    }
}
