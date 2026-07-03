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

    /**
     * Controls the text's horizontal alignment. Supports left, center, right, and fill (also known as
     * justify).
     *
     * Generated from Godot docs: Label3D.set_horizontal_alignment
     */
    fun setHorizontalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setHorizontalAlignmentBind, handle, alignment)
    }

    /**
     * Controls the text's horizontal alignment. Supports left, center, right, and fill (also known as
     * justify).
     *
     * Generated from Godot docs: Label3D.get_horizontal_alignment
     */
    fun getHorizontalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getHorizontalAlignmentBind, handle)
    }

    /**
     * Controls the text's vertical alignment. Supports top, center, and bottom.
     *
     * Generated from Godot docs: Label3D.set_vertical_alignment
     */
    fun setVerticalAlignment(alignment: Long) {
        ObjectCalls.ptrcallWithLongArg(setVerticalAlignmentBind, handle, alignment)
    }

    /**
     * Controls the text's vertical alignment. Supports top, center, and bottom.
     *
     * Generated from Godot docs: Label3D.get_vertical_alignment
     */
    fun getVerticalAlignment(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getVerticalAlignmentBind, handle)
    }

    /**
     * Text `Color` of the `Label3D`.
     *
     * Generated from Godot docs: Label3D.set_modulate
     */
    fun setModulate(modulate: Color) {
        ObjectCalls.ptrcallWithColorArg(setModulateBind, handle, modulate)
    }

    /**
     * Text `Color` of the `Label3D`.
     *
     * Generated from Godot docs: Label3D.get_modulate
     */
    fun getModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getModulateBind, handle)
    }

    /**
     * The tint of text outline.
     *
     * Generated from Godot docs: Label3D.set_outline_modulate
     */
    fun setOutlineModulate(modulate: Color) {
        ObjectCalls.ptrcallWithColorArg(setOutlineModulateBind, handle, modulate)
    }

    /**
     * The tint of text outline.
     *
     * Generated from Godot docs: Label3D.get_outline_modulate
     */
    fun getOutlineModulate(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getOutlineModulateBind, handle)
    }

    /**
     * The text to display on screen.
     *
     * Generated from Godot docs: Label3D.set_text
     */
    fun setText(text: String) {
        ObjectCalls.ptrcallWithStringArg(setTextBind, handle, text)
    }

    /**
     * The text to display on screen.
     *
     * Generated from Godot docs: Label3D.get_text
     */
    fun getText(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getTextBind, handle)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: Label3D.set_text_direction
     */
    fun setTextDirection(direction: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextDirectionBind, handle, direction)
    }

    /**
     * Base text writing direction.
     *
     * Generated from Godot docs: Label3D.get_text_direction
     */
    fun getTextDirection(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextDirectionBind, handle)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: Label3D.set_language
     */
    fun setLanguage(language: String) {
        ObjectCalls.ptrcallWithStringArg(setLanguageBind, handle, language)
    }

    /**
     * Language code used for line-breaking and text shaping algorithms. If left empty, the current
     * locale is used instead.
     *
     * Generated from Godot docs: Label3D.get_language
     */
    fun getLanguage(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getLanguageBind, handle)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: Label3D.set_structured_text_bidi_override
     */
    fun setStructuredTextBidiOverride(parser: Long) {
        ObjectCalls.ptrcallWithLongArg(setStructuredTextBidiOverrideBind, handle, parser)
    }

    /**
     * Set BiDi algorithm override for the structured text.
     *
     * Generated from Godot docs: Label3D.get_structured_text_bidi_override
     */
    fun getStructuredTextBidiOverride(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getStructuredTextBidiOverrideBind, handle)
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: Label3D.set_structured_text_bidi_override_options
     */
    fun setStructuredTextBidiOverrideOptions(args: List<Any?>) {
        ObjectCalls.ptrcallWithArrayArg(setStructuredTextBidiOverrideOptionsBind, handle, args)
    }

    /**
     * Set additional options for BiDi override.
     *
     * Generated from Godot docs: Label3D.get_structured_text_bidi_override_options
     */
    fun getStructuredTextBidiOverrideOptions(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(getStructuredTextBidiOverrideOptionsBind, handle)
    }

    /**
     * If `true`, all the text displays as UPPERCASE.
     *
     * Generated from Godot docs: Label3D.set_uppercase
     */
    fun setUppercase(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUppercaseBind, handle, enable)
    }

    /**
     * If `true`, all the text displays as UPPERCASE.
     *
     * Generated from Godot docs: Label3D.is_uppercase
     */
    fun isUppercase(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUppercaseBind, handle)
    }

    /**
     * Sets the render priority for the text. Higher priority objects will be sorted in front of lower
     * priority objects. Note: This only applies if `alpha_cut` is set to `ALPHA_CUT_DISABLED` (default
     * value). Note: This only applies to sorting of transparent objects. This will not impact how
     * transparent objects are sorted relative to opaque objects. This is because opaque objects are
     * not sorted, while transparent objects are sorted from back to front (subject to priority).
     *
     * Generated from Godot docs: Label3D.set_render_priority
     */
    fun setRenderPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setRenderPriorityBind, handle, priority)
    }

    /**
     * Sets the render priority for the text. Higher priority objects will be sorted in front of lower
     * priority objects. Note: This only applies if `alpha_cut` is set to `ALPHA_CUT_DISABLED` (default
     * value). Note: This only applies to sorting of transparent objects. This will not impact how
     * transparent objects are sorted relative to opaque objects. This is because opaque objects are
     * not sorted, while transparent objects are sorted from back to front (subject to priority).
     *
     * Generated from Godot docs: Label3D.get_render_priority
     */
    fun getRenderPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getRenderPriorityBind, handle)
    }

    /**
     * Sets the render priority for the text outline. Higher priority objects will be sorted in front
     * of lower priority objects. Note: This only applies if `alpha_cut` is set to `ALPHA_CUT_DISABLED`
     * (default value). Note: This only applies to sorting of transparent objects. This will not impact
     * how transparent objects are sorted relative to opaque objects. This is because opaque objects
     * are not sorted, while transparent objects are sorted from back to front (subject to priority).
     *
     * Generated from Godot docs: Label3D.set_outline_render_priority
     */
    fun setOutlineRenderPriority(priority: Int) {
        ObjectCalls.ptrcallWithIntArg(setOutlineRenderPriorityBind, handle, priority)
    }

    /**
     * Sets the render priority for the text outline. Higher priority objects will be sorted in front
     * of lower priority objects. Note: This only applies if `alpha_cut` is set to `ALPHA_CUT_DISABLED`
     * (default value). Note: This only applies to sorting of transparent objects. This will not impact
     * how transparent objects are sorted relative to opaque objects. This is because opaque objects
     * are not sorted, while transparent objects are sorted from back to front (subject to priority).
     *
     * Generated from Godot docs: Label3D.get_outline_render_priority
     */
    fun getOutlineRenderPriority(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOutlineRenderPriorityBind, handle)
    }

    /**
     * Font configuration used to display text.
     *
     * Generated from Godot docs: Label3D.set_font
     */
    fun setFont(font: Font?) {
        ObjectCalls.ptrcallWithObjectArgs(setFontBind, handle, listOf(font?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Font configuration used to display text.
     *
     * Generated from Godot docs: Label3D.get_font
     */
    fun getFont(): Font? {
        return Font.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFontBind, handle))
    }

    /**
     * Font size of the `Label3D`'s text. To make the font look more detailed when up close, increase
     * `font_size` while decreasing `pixel_size` at the same time. Higher font sizes require more time
     * to render new characters, which can cause stuttering during gameplay.
     *
     * Generated from Godot docs: Label3D.set_font_size
     */
    fun setFontSize(size: Int) {
        ObjectCalls.ptrcallWithIntArg(setFontSizeBind, handle, size)
    }

    /**
     * Font size of the `Label3D`'s text. To make the font look more detailed when up close, increase
     * `font_size` while decreasing `pixel_size` at the same time. Higher font sizes require more time
     * to render new characters, which can cause stuttering during gameplay.
     *
     * Generated from Godot docs: Label3D.get_font_size
     */
    fun getFontSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getFontSizeBind, handle)
    }

    /**
     * Text outline size.
     *
     * Generated from Godot docs: Label3D.set_outline_size
     */
    fun setOutlineSize(outlineSize: Int) {
        ObjectCalls.ptrcallWithIntArg(setOutlineSizeBind, handle, outlineSize)
    }

    /**
     * Text outline size.
     *
     * Generated from Godot docs: Label3D.get_outline_size
     */
    fun getOutlineSize(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOutlineSizeBind, handle)
    }

    /**
     * Additional vertical spacing between lines (in pixels), spacing is added to line descent. This
     * value can be negative.
     *
     * Generated from Godot docs: Label3D.set_line_spacing
     */
    fun setLineSpacing(lineSpacing: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLineSpacingBind, handle, lineSpacing)
    }

    /**
     * Additional vertical spacing between lines (in pixels), spacing is added to line descent. This
     * value can be negative.
     *
     * Generated from Godot docs: Label3D.get_line_spacing
     */
    fun getLineSpacing(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLineSpacingBind, handle)
    }

    /**
     * If set to something other than `TextServer.AUTOWRAP_OFF`, the text gets wrapped inside the
     * node's bounding rectangle. If you resize the node, it will change its height automatically to
     * show all the text.
     *
     * Generated from Godot docs: Label3D.set_autowrap_mode
     */
    fun setAutowrapMode(autowrapMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapModeBind, handle, autowrapMode)
    }

    /**
     * If set to something other than `TextServer.AUTOWRAP_OFF`, the text gets wrapped inside the
     * node's bounding rectangle. If you resize the node, it will change its height automatically to
     * show all the text.
     *
     * Generated from Godot docs: Label3D.get_autowrap_mode
     */
    fun getAutowrapMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapModeBind, handle)
    }

    /**
     * Autowrap space trimming flags. See `TextServer.BREAK_TRIM_START_EDGE_SPACES` and
     * `TextServer.BREAK_TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: Label3D.set_autowrap_trim_flags
     */
    fun setAutowrapTrimFlags(autowrapTrimFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setAutowrapTrimFlagsBind, handle, autowrapTrimFlags)
    }

    /**
     * Autowrap space trimming flags. See `TextServer.BREAK_TRIM_START_EDGE_SPACES` and
     * `TextServer.BREAK_TRIM_END_EDGE_SPACES` for more info.
     *
     * Generated from Godot docs: Label3D.get_autowrap_trim_flags
     */
    fun getAutowrapTrimFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAutowrapTrimFlagsBind, handle)
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: Label3D.set_justification_flags
     */
    fun setJustificationFlags(justificationFlags: Long) {
        ObjectCalls.ptrcallWithLongArg(setJustificationFlagsBind, handle, justificationFlags)
    }

    /**
     * Line fill alignment rules.
     *
     * Generated from Godot docs: Label3D.get_justification_flags
     */
    fun getJustificationFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getJustificationFlagsBind, handle)
    }

    /**
     * Text width (in pixels), used for autowrap and fill alignment.
     *
     * Generated from Godot docs: Label3D.set_width
     */
    fun setWidth(width: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWidthBind, handle, width)
    }

    /**
     * Text width (in pixels), used for autowrap and fill alignment.
     *
     * Generated from Godot docs: Label3D.get_width
     */
    fun getWidth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWidthBind, handle)
    }

    /**
     * The size of one pixel's width on the label to scale it in 3D. To make the font look more
     * detailed when up close, increase `font_size` while decreasing `pixel_size` at the same time.
     *
     * Generated from Godot docs: Label3D.set_pixel_size
     */
    fun setPixelSize(pixelSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPixelSizeBind, handle, pixelSize)
    }

    /**
     * The size of one pixel's width on the label to scale it in 3D. To make the font look more
     * detailed when up close, increase `font_size` while decreasing `pixel_size` at the same time.
     *
     * Generated from Godot docs: Label3D.get_pixel_size
     */
    fun getPixelSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPixelSizeBind, handle)
    }

    /**
     * The text drawing offset (in pixels).
     *
     * Generated from Godot docs: Label3D.set_offset
     */
    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, handle, offset)
    }

    /**
     * The text drawing offset (in pixels).
     *
     * Generated from Godot docs: Label3D.get_offset
     */
    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    /**
     * If `true`, the `Light3D` in the `Environment` has effects on the label.
     *
     * Generated from Godot docs: Label3D.set_draw_flag
     */
    fun setDrawFlag(flag: Long, enabled: Boolean) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setDrawFlagBind, handle, flag, enabled)
    }

    /**
     * If `true`, the `Light3D` in the `Environment` has effects on the label.
     *
     * Generated from Godot docs: Label3D.get_draw_flag
     */
    fun getDrawFlag(flag: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(getDrawFlagBind, handle, flag)
    }

    /**
     * The billboard mode to use for the label.
     *
     * Generated from Godot docs: Label3D.set_billboard_mode
     */
    fun setBillboardMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setBillboardModeBind, handle, mode)
    }

    /**
     * The billboard mode to use for the label.
     *
     * Generated from Godot docs: Label3D.get_billboard_mode
     */
    fun getBillboardMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getBillboardModeBind, handle)
    }

    /**
     * The alpha cutting mode to use for the sprite.
     *
     * Generated from Godot docs: Label3D.set_alpha_cut_mode
     */
    fun setAlphaCutMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlphaCutModeBind, handle, mode)
    }

    /**
     * The alpha cutting mode to use for the sprite.
     *
     * Generated from Godot docs: Label3D.get_alpha_cut_mode
     */
    fun getAlphaCutMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlphaCutModeBind, handle)
    }

    /**
     * Threshold at which the alpha scissor will discard values.
     *
     * Generated from Godot docs: Label3D.set_alpha_scissor_threshold
     */
    fun setAlphaScissorThreshold(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAlphaScissorThresholdBind, handle, threshold)
    }

    /**
     * Threshold at which the alpha scissor will discard values.
     *
     * Generated from Godot docs: Label3D.get_alpha_scissor_threshold
     */
    fun getAlphaScissorThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaScissorThresholdBind, handle)
    }

    /**
     * The hashing scale for Alpha Hash. Recommended values between `0` and `2`.
     *
     * Generated from Godot docs: Label3D.set_alpha_hash_scale
     */
    fun setAlphaHashScale(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAlphaHashScaleBind, handle, threshold)
    }

    /**
     * The hashing scale for Alpha Hash. Recommended values between `0` and `2`.
     *
     * Generated from Godot docs: Label3D.get_alpha_hash_scale
     */
    fun getAlphaHashScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaHashScaleBind, handle)
    }

    /**
     * The type of alpha antialiasing to apply.
     *
     * Generated from Godot docs: Label3D.set_alpha_antialiasing
     */
    fun setAlphaAntialiasing(alphaAa: Long) {
        ObjectCalls.ptrcallWithLongArg(setAlphaAntialiasingBind, handle, alphaAa)
    }

    /**
     * The type of alpha antialiasing to apply.
     *
     * Generated from Godot docs: Label3D.get_alpha_antialiasing
     */
    fun getAlphaAntialiasing(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAlphaAntialiasingBind, handle)
    }

    /**
     * Threshold at which antialiasing will be applied on the alpha channel.
     *
     * Generated from Godot docs: Label3D.set_alpha_antialiasing_edge
     */
    fun setAlphaAntialiasingEdge(edge: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAlphaAntialiasingEdgeBind, handle, edge)
    }

    /**
     * Threshold at which antialiasing will be applied on the alpha channel.
     *
     * Generated from Godot docs: Label3D.get_alpha_antialiasing_edge
     */
    fun getAlphaAntialiasingEdge(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAlphaAntialiasingEdgeBind, handle)
    }

    /**
     * Filter flags for the texture.
     *
     * Generated from Godot docs: Label3D.set_texture_filter
     */
    fun setTextureFilter(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setTextureFilterBind, handle, mode)
    }

    /**
     * Filter flags for the texture.
     *
     * Generated from Godot docs: Label3D.get_texture_filter
     */
    fun getTextureFilter(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTextureFilterBind, handle)
    }

    /**
     * Returns a `TriangleMesh` with the label's vertices following its current configuration (such as
     * its `pixel_size`).
     *
     * Generated from Godot docs: Label3D.generate_triangle_mesh
     */
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
