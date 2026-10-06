package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A font loaded from a system font. Falls back to a default theme font if not implemented on the
 * host OS.
 *
 * Generated from Godot docs: SystemFont
 */
class SystemFont(handle: GodotHandle) : Font(handle) {
    var fontNames: List<String>
        @JvmName("fontNamesProperty")
        get() = getFontNames()
        @JvmName("setFontNamesProperty")
        set(value) = setFontNames(value)

    var fontItalic: Boolean
        @JvmName("fontItalicProperty")
        get() = getFontItalic()
        @JvmName("setFontItalicProperty")
        set(value) = setFontItalic(value)

    var antialiasing: TextServer.FontAntialiasing
        @JvmName("antialiasingProperty")
        get() = getAntialiasing()
        @JvmName("setAntialiasingProperty")
        set(value) = setAntialiasing(value)

    var generateMipmaps: Boolean
        @JvmName("generateMipmapsProperty")
        get() = getGenerateMipmaps()
        @JvmName("setGenerateMipmapsProperty")
        set(value) = setGenerateMipmaps(value)

    var disableEmbeddedBitmaps: Boolean
        @JvmName("disableEmbeddedBitmapsProperty")
        get() = getDisableEmbeddedBitmaps()
        @JvmName("setDisableEmbeddedBitmapsProperty")
        set(value) = setDisableEmbeddedBitmaps(value)

    var allowSystemFallback: Boolean
        @JvmName("allowSystemFallbackProperty")
        get() = isAllowSystemFallback()
        @JvmName("setAllowSystemFallbackProperty")
        set(value) = setAllowSystemFallback(value)

    var forceAutohinter: Boolean
        @JvmName("forceAutohinterProperty")
        get() = isForceAutohinter()
        @JvmName("setForceAutohinterProperty")
        set(value) = setForceAutohinter(value)

    var modulateColorGlyphs: Boolean
        @JvmName("modulateColorGlyphsProperty")
        get() = isModulateColorGlyphs()
        @JvmName("setModulateColorGlyphsProperty")
        set(value) = setModulateColorGlyphs(value)

    var hinting: TextServer.Hinting
        @JvmName("hintingProperty")
        get() = getHinting()
        @JvmName("setHintingProperty")
        set(value) = setHinting(value)

    var subpixelPositioning: TextServer.SubpixelPositioning
        @JvmName("subpixelPositioningProperty")
        get() = getSubpixelPositioning()
        @JvmName("setSubpixelPositioningProperty")
        set(value) = setSubpixelPositioning(value)

    var keepRoundingRemainders: Boolean
        @JvmName("keepRoundingRemaindersProperty")
        get() = getKeepRoundingRemainders()
        @JvmName("setKeepRoundingRemaindersProperty")
        set(value) = setKeepRoundingRemainders(value)

    var multichannelSignedDistanceField: Boolean
        @JvmName("multichannelSignedDistanceFieldProperty")
        get() = isMultichannelSignedDistanceField()
        @JvmName("setMultichannelSignedDistanceFieldProperty")
        set(value) = setMultichannelSignedDistanceField(value)

    var msdfPixelRange: Int
        @JvmName("msdfPixelRangeProperty")
        get() = getMsdfPixelRange()
        @JvmName("setMsdfPixelRangeProperty")
        set(value) = setMsdfPixelRange(value)

    var msdfSize: Int
        @JvmName("msdfSizeProperty")
        get() = getMsdfSize()
        @JvmName("setMsdfSizeProperty")
        set(value) = setMsdfSize(value)

    var oversampling: Double
        @JvmName("oversamplingProperty")
        get() = getOversampling()
        @JvmName("setOversamplingProperty")
        set(value) = setOversampling(value)

    /**
     * Font anti-aliasing mode.
     *
     * Generated from Godot docs: SystemFont.set_antialiasing
     */
    fun setAntialiasing(antialiasing: TextServer.FontAntialiasing) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setAntialiasingBind, segment, antialiasing.value)
    }

    /**
     * Font anti-aliasing mode.
     *
     * Generated from Godot docs: SystemFont.get_antialiasing
     */
    fun getAntialiasing(): TextServer.FontAntialiasing {
        checkOpen()
        return TextServer.FontAntialiasing(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAntialiasingBind, segment))
    }

    /**
     * If set to `true`, embedded font bitmap loading is disabled (bitmap-only and color fonts ignore
     * this property).
     *
     * Generated from Godot docs: SystemFont.set_disable_embedded_bitmaps
     */
    fun setDisableEmbeddedBitmaps(disableEmbeddedBitmaps: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDisableEmbeddedBitmapsBind, segment, disableEmbeddedBitmaps)
    }

    /**
     * If set to `true`, embedded font bitmap loading is disabled (bitmap-only and color fonts ignore
     * this property).
     *
     * Generated from Godot docs: SystemFont.get_disable_embedded_bitmaps
     */
    fun getDisableEmbeddedBitmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getDisableEmbeddedBitmapsBind, segment)
    }

    /**
     * If set to `true`, generate mipmaps for the font textures.
     *
     * Generated from Godot docs: SystemFont.set_generate_mipmaps
     */
    fun setGenerateMipmaps(generateMipmaps: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setGenerateMipmapsBind, segment, generateMipmaps)
    }

    /**
     * If set to `true`, generate mipmaps for the font textures.
     *
     * Generated from Godot docs: SystemFont.get_generate_mipmaps
     */
    fun getGenerateMipmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getGenerateMipmapsBind, segment)
    }

    /**
     * If set to `true`, system fonts can be automatically used as fallbacks.
     *
     * Generated from Godot docs: SystemFont.set_allow_system_fallback
     */
    fun setAllowSystemFallback(allowSystemFallback: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAllowSystemFallbackBind, segment, allowSystemFallback)
    }

    /**
     * If set to `true`, system fonts can be automatically used as fallbacks.
     *
     * Generated from Godot docs: SystemFont.is_allow_system_fallback
     */
    fun isAllowSystemFallback(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAllowSystemFallbackBind, segment)
    }

    /**
     * If set to `true`, auto-hinting is supported and preferred over font built-in hinting.
     *
     * Generated from Godot docs: SystemFont.set_force_autohinter
     */
    fun setForceAutohinter(forceAutohinter: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setForceAutohinterBind, segment, forceAutohinter)
    }

    /**
     * If set to `true`, auto-hinting is supported and preferred over font built-in hinting.
     *
     * Generated from Godot docs: SystemFont.is_force_autohinter
     */
    fun isForceAutohinter(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isForceAutohinterBind, segment)
    }

    /**
     * If set to `true`, color modulation is applied when drawing colored glyphs, otherwise it's
     * applied to the monochrome glyphs only.
     *
     * Generated from Godot docs: SystemFont.set_modulate_color_glyphs
     */
    fun setModulateColorGlyphs(modulate: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setModulateColorGlyphsBind, segment, modulate)
    }

    /**
     * If set to `true`, color modulation is applied when drawing colored glyphs, otherwise it's
     * applied to the monochrome glyphs only.
     *
     * Generated from Godot docs: SystemFont.is_modulate_color_glyphs
     */
    fun isModulateColorGlyphs(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isModulateColorGlyphsBind, segment)
    }

    /**
     * Font hinting mode.
     *
     * Generated from Godot docs: SystemFont.set_hinting
     */
    fun setHinting(hinting: TextServer.Hinting) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setHintingBind, segment, hinting.value)
    }

    /**
     * Font hinting mode.
     *
     * Generated from Godot docs: SystemFont.get_hinting
     */
    fun getHinting(): TextServer.Hinting {
        checkOpen()
        return TextServer.Hinting(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHintingBind, segment))
    }

    /**
     * Font glyph subpixel positioning mode. Subpixel positioning provides shaper text and better
     * kerning for smaller font sizes, at the cost of memory usage and font rasterization speed. Use
     * `TextServer.SubpixelPositioning.AUTO` to automatically enable it based on the font size.
     *
     * Generated from Godot docs: SystemFont.set_subpixel_positioning
     */
    fun setSubpixelPositioning(subpixelPositioning: TextServer.SubpixelPositioning) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSubpixelPositioningBind, segment, subpixelPositioning.value)
    }

    /**
     * Font glyph subpixel positioning mode. Subpixel positioning provides shaper text and better
     * kerning for smaller font sizes, at the cost of memory usage and font rasterization speed. Use
     * `TextServer.SubpixelPositioning.AUTO` to automatically enable it based on the font size.
     *
     * Generated from Godot docs: SystemFont.get_subpixel_positioning
     */
    fun getSubpixelPositioning(): TextServer.SubpixelPositioning {
        checkOpen()
        return TextServer.SubpixelPositioning(ObjectCalls.ptrcallNoArgsRetLong(Binds.getSubpixelPositioningBind, segment))
    }

    /**
     * If set to `true`, when aligning glyphs to the pixel boundaries rounding remainders are
     * accumulated to ensure more uniform glyph distribution. This setting has no effect if subpixel
     * positioning is enabled.
     *
     * Generated from Godot docs: SystemFont.set_keep_rounding_remainders
     */
    fun setKeepRoundingRemainders(keepRoundingRemainders: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setKeepRoundingRemaindersBind, segment, keepRoundingRemainders)
    }

    /**
     * If set to `true`, when aligning glyphs to the pixel boundaries rounding remainders are
     * accumulated to ensure more uniform glyph distribution. This setting has no effect if subpixel
     * positioning is enabled.
     *
     * Generated from Godot docs: SystemFont.get_keep_rounding_remainders
     */
    fun getKeepRoundingRemainders(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getKeepRoundingRemaindersBind, segment)
    }

    /**
     * If set to `true`, glyphs of all sizes are rendered using single multichannel signed distance
     * field generated from the dynamic font vector data.
     *
     * Generated from Godot docs: SystemFont.set_multichannel_signed_distance_field
     */
    fun setMultichannelSignedDistanceField(msdf: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setMultichannelSignedDistanceFieldBind, segment, msdf)
    }

    /**
     * If set to `true`, glyphs of all sizes are rendered using single multichannel signed distance
     * field generated from the dynamic font vector data.
     *
     * Generated from Godot docs: SystemFont.is_multichannel_signed_distance_field
     */
    fun isMultichannelSignedDistanceField(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMultichannelSignedDistanceFieldBind, segment)
    }

    /**
     * The width of the range around the shape between the minimum and maximum representable signed
     * distance. If using font outlines, `msdf_pixel_range` must be set to at least twice the size of
     * the largest font outline. The default `msdf_pixel_range` value of `16` allows outline sizes up
     * to `8` to look correct.
     *
     * Generated from Godot docs: SystemFont.set_msdf_pixel_range
     */
    fun setMsdfPixelRange(msdfPixelRange: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMsdfPixelRangeBind, segment, msdfPixelRange)
    }

    /**
     * The width of the range around the shape between the minimum and maximum representable signed
     * distance. If using font outlines, `msdf_pixel_range` must be set to at least twice the size of
     * the largest font outline. The default `msdf_pixel_range` value of `16` allows outline sizes up
     * to `8` to look correct.
     *
     * Generated from Godot docs: SystemFont.get_msdf_pixel_range
     */
    fun getMsdfPixelRange(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMsdfPixelRangeBind, segment)
    }

    /**
     * Source font size used to generate MSDF textures. Higher values allow for more precision, but are
     * slower to render and require more memory. Only increase this value if you notice a visible lack
     * of precision in glyph rendering.
     *
     * Generated from Godot docs: SystemFont.set_msdf_size
     */
    fun setMsdfSize(msdfSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMsdfSizeBind, segment, msdfSize)
    }

    /**
     * Source font size used to generate MSDF textures. Higher values allow for more precision, but are
     * slower to render and require more memory. Only increase this value if you notice a visible lack
     * of precision in glyph rendering.
     *
     * Generated from Godot docs: SystemFont.get_msdf_size
     */
    fun getMsdfSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMsdfSizeBind, segment)
    }

    /**
     * If set to a positive value, overrides the oversampling factor of the viewport this font is used
     * in. See `Viewport.oversampling`. This value doesn't override the `oversampling` parameter of
     * `draw_*` methods.
     *
     * Generated from Godot docs: SystemFont.set_oversampling
     */
    fun setOversampling(oversampling: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setOversamplingBind, segment, oversampling)
    }

    /**
     * If set to a positive value, overrides the oversampling factor of the viewport this font is used
     * in. See `Viewport.oversampling`. This value doesn't override the `oversampling` parameter of
     * `draw_*` methods.
     *
     * Generated from Godot docs: SystemFont.get_oversampling
     */
    fun getOversampling(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getOversamplingBind, segment)
    }

    /**
     * Array of font family names to search, first matching font found is used.
     *
     * Generated from Godot docs: SystemFont.get_font_names
     */
    fun getFontNames(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getFontNamesBind, segment)
    }

    /**
     * Array of font family names to search, first matching font found is used.
     *
     * Generated from Godot docs: SystemFont.set_font_names
     */
    fun setFontNames(names: List<String>) {
        checkOpen()
        ObjectCalls.ptrcallWithPackedStringListArg(Binds.setFontNamesBind, segment, names)
    }

    /**
     * If set to `true`, italic or oblique font is preferred.
     *
     * Generated from Godot docs: SystemFont.get_font_italic
     */
    fun getFontItalic(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getFontItalicBind, segment)
    }

    /**
     * If set to `true`, italic or oblique font is preferred.
     *
     * Generated from Godot docs: SystemFont.set_font_italic
     */
    fun setFontItalic(italic: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setFontItalicBind, segment, italic)
    }

    /**
     * Preferred weight (boldness) of the font. A value in the `100...999` range, normal font weight is
     * `400`, bold font weight is `700`.
     *
     * Generated from Godot docs: SystemFont.set_font_weight
     */
    fun setFontWeight(weight: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setFontWeightBind, segment, weight)
    }

    /**
     * Preferred font stretch amount, compared to a normal width. A percentage value between `50%` and
     * `200%`.
     *
     * Generated from Godot docs: SystemFont.set_font_stretch
     */
    fun setFontStretch(stretch: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setFontStretchBind, segment, stretch)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SystemFont? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): SystemFont? =
            if (handle.address() == 0L) null else RefCounted.owned(SystemFont(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): SystemFont? =
            if (handle.address() == 0L) null else SystemFont(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ANTIALIASING_HASH = 1669900L
        @JvmField
        val setAntialiasingBind =
            ObjectCalls.getMethodBind("SystemFont", "set_antialiasing", SET_ANTIALIASING_HASH)

        private const val GET_ANTIALIASING_HASH = 4262718649L
        @JvmField
        val getAntialiasingBind =
            ObjectCalls.getMethodBind("SystemFont", "get_antialiasing", GET_ANTIALIASING_HASH)

        private const val SET_DISABLE_EMBEDDED_BITMAPS_HASH = 2586408642L
        @JvmField
        val setDisableEmbeddedBitmapsBind =
            ObjectCalls.getMethodBind("SystemFont", "set_disable_embedded_bitmaps", SET_DISABLE_EMBEDDED_BITMAPS_HASH)

        private const val GET_DISABLE_EMBEDDED_BITMAPS_HASH = 36873697L
        @JvmField
        val getDisableEmbeddedBitmapsBind =
            ObjectCalls.getMethodBind("SystemFont", "get_disable_embedded_bitmaps", GET_DISABLE_EMBEDDED_BITMAPS_HASH)

        private const val SET_GENERATE_MIPMAPS_HASH = 2586408642L
        @JvmField
        val setGenerateMipmapsBind =
            ObjectCalls.getMethodBind("SystemFont", "set_generate_mipmaps", SET_GENERATE_MIPMAPS_HASH)

        private const val GET_GENERATE_MIPMAPS_HASH = 36873697L
        @JvmField
        val getGenerateMipmapsBind =
            ObjectCalls.getMethodBind("SystemFont", "get_generate_mipmaps", GET_GENERATE_MIPMAPS_HASH)

        private const val SET_ALLOW_SYSTEM_FALLBACK_HASH = 2586408642L
        @JvmField
        val setAllowSystemFallbackBind =
            ObjectCalls.getMethodBind("SystemFont", "set_allow_system_fallback", SET_ALLOW_SYSTEM_FALLBACK_HASH)

        private const val IS_ALLOW_SYSTEM_FALLBACK_HASH = 36873697L
        @JvmField
        val isAllowSystemFallbackBind =
            ObjectCalls.getMethodBind("SystemFont", "is_allow_system_fallback", IS_ALLOW_SYSTEM_FALLBACK_HASH)

        private const val SET_FORCE_AUTOHINTER_HASH = 2586408642L
        @JvmField
        val setForceAutohinterBind =
            ObjectCalls.getMethodBind("SystemFont", "set_force_autohinter", SET_FORCE_AUTOHINTER_HASH)

        private const val IS_FORCE_AUTOHINTER_HASH = 36873697L
        @JvmField
        val isForceAutohinterBind =
            ObjectCalls.getMethodBind("SystemFont", "is_force_autohinter", IS_FORCE_AUTOHINTER_HASH)

        private const val SET_MODULATE_COLOR_GLYPHS_HASH = 2586408642L
        @JvmField
        val setModulateColorGlyphsBind =
            ObjectCalls.getMethodBind("SystemFont", "set_modulate_color_glyphs", SET_MODULATE_COLOR_GLYPHS_HASH)

        private const val IS_MODULATE_COLOR_GLYPHS_HASH = 36873697L
        @JvmField
        val isModulateColorGlyphsBind =
            ObjectCalls.getMethodBind("SystemFont", "is_modulate_color_glyphs", IS_MODULATE_COLOR_GLYPHS_HASH)

        private const val SET_HINTING_HASH = 1827459492L
        @JvmField
        val setHintingBind =
            ObjectCalls.getMethodBind("SystemFont", "set_hinting", SET_HINTING_HASH)

        private const val GET_HINTING_HASH = 3683214614L
        @JvmField
        val getHintingBind =
            ObjectCalls.getMethodBind("SystemFont", "get_hinting", GET_HINTING_HASH)

        private const val SET_SUBPIXEL_POSITIONING_HASH = 4225742182L
        @JvmField
        val setSubpixelPositioningBind =
            ObjectCalls.getMethodBind("SystemFont", "set_subpixel_positioning", SET_SUBPIXEL_POSITIONING_HASH)

        private const val GET_SUBPIXEL_POSITIONING_HASH = 1069238588L
        @JvmField
        val getSubpixelPositioningBind =
            ObjectCalls.getMethodBind("SystemFont", "get_subpixel_positioning", GET_SUBPIXEL_POSITIONING_HASH)

        private const val SET_KEEP_ROUNDING_REMAINDERS_HASH = 2586408642L
        @JvmField
        val setKeepRoundingRemaindersBind =
            ObjectCalls.getMethodBind("SystemFont", "set_keep_rounding_remainders", SET_KEEP_ROUNDING_REMAINDERS_HASH)

        private const val GET_KEEP_ROUNDING_REMAINDERS_HASH = 36873697L
        @JvmField
        val getKeepRoundingRemaindersBind =
            ObjectCalls.getMethodBind("SystemFont", "get_keep_rounding_remainders", GET_KEEP_ROUNDING_REMAINDERS_HASH)

        private const val SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 2586408642L
        @JvmField
        val setMultichannelSignedDistanceFieldBind =
            ObjectCalls.getMethodBind("SystemFont", "set_multichannel_signed_distance_field", SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)

        private const val IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 36873697L
        @JvmField
        val isMultichannelSignedDistanceFieldBind =
            ObjectCalls.getMethodBind("SystemFont", "is_multichannel_signed_distance_field", IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)

        private const val SET_MSDF_PIXEL_RANGE_HASH = 1286410249L
        @JvmField
        val setMsdfPixelRangeBind =
            ObjectCalls.getMethodBind("SystemFont", "set_msdf_pixel_range", SET_MSDF_PIXEL_RANGE_HASH)

        private const val GET_MSDF_PIXEL_RANGE_HASH = 3905245786L
        @JvmField
        val getMsdfPixelRangeBind =
            ObjectCalls.getMethodBind("SystemFont", "get_msdf_pixel_range", GET_MSDF_PIXEL_RANGE_HASH)

        private const val SET_MSDF_SIZE_HASH = 1286410249L
        @JvmField
        val setMsdfSizeBind =
            ObjectCalls.getMethodBind("SystemFont", "set_msdf_size", SET_MSDF_SIZE_HASH)

        private const val GET_MSDF_SIZE_HASH = 3905245786L
        @JvmField
        val getMsdfSizeBind =
            ObjectCalls.getMethodBind("SystemFont", "get_msdf_size", GET_MSDF_SIZE_HASH)

        private const val SET_OVERSAMPLING_HASH = 373806689L
        @JvmField
        val setOversamplingBind =
            ObjectCalls.getMethodBind("SystemFont", "set_oversampling", SET_OVERSAMPLING_HASH)

        private const val GET_OVERSAMPLING_HASH = 1740695150L
        @JvmField
        val getOversamplingBind =
            ObjectCalls.getMethodBind("SystemFont", "get_oversampling", GET_OVERSAMPLING_HASH)

        private const val GET_FONT_NAMES_HASH = 1139954409L
        @JvmField
        val getFontNamesBind =
            ObjectCalls.getMethodBind("SystemFont", "get_font_names", GET_FONT_NAMES_HASH)

        private const val SET_FONT_NAMES_HASH = 4015028928L
        @JvmField
        val setFontNamesBind =
            ObjectCalls.getMethodBind("SystemFont", "set_font_names", SET_FONT_NAMES_HASH)

        private const val GET_FONT_ITALIC_HASH = 36873697L
        @JvmField
        val getFontItalicBind =
            ObjectCalls.getMethodBind("SystemFont", "get_font_italic", GET_FONT_ITALIC_HASH)

        private const val SET_FONT_ITALIC_HASH = 2586408642L
        @JvmField
        val setFontItalicBind =
            ObjectCalls.getMethodBind("SystemFont", "set_font_italic", SET_FONT_ITALIC_HASH)

        private const val SET_FONT_WEIGHT_HASH = 1286410249L
        @JvmField
        val setFontWeightBind =
            ObjectCalls.getMethodBind("SystemFont", "set_font_weight", SET_FONT_WEIGHT_HASH)

        private const val SET_FONT_STRETCH_HASH = 1286410249L
        @JvmField
        val setFontStretchBind =
            ObjectCalls.getMethodBind("SystemFont", "set_font_stretch", SET_FONT_STRETCH_HASH)
    }
}
