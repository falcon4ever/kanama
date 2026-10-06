package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Holds font source data and prerendered glyph cache, imported from a dynamic or a bitmap font.
 *
 * Generated from Godot docs: FontFile
 */
class FontFile(handle: GodotHandle) : Font(handle) {
    var data: ByteArray
        @JvmName("dataProperty")
        get() = getData()
        @JvmName("setDataProperty")
        set(value) = setData(value)

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

    var antialiasing: TextServer.FontAntialiasing
        @JvmName("antialiasingProperty")
        get() = getAntialiasing()
        @JvmName("setAntialiasingProperty")
        set(value) = setAntialiasing(value)

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

    var fixedSize: Int
        @JvmName("fixedSizeProperty")
        get() = getFixedSize()
        @JvmName("setFixedSizeProperty")
        set(value) = setFixedSize(value)

    var fixedSizeScaleMode: TextServer.FixedSizeScaleMode
        @JvmName("fixedSizeScaleModeProperty")
        get() = getFixedSizeScaleMode()
        @JvmName("setFixedSizeScaleModeProperty")
        set(value) = setFixedSizeScaleMode(value)

    var opentypeFeatureOverrides: Map<String, Any?>
        @JvmName("opentypeFeatureOverridesProperty")
        get() = getOpentypeFeatureOverrides()
        @JvmName("setOpentypeFeatureOverridesProperty")
        set(value) = setOpentypeFeatureOverrides(value)

    var oversampling: Double
        @JvmName("oversamplingProperty")
        get() = getOversampling()
        @JvmName("setOversamplingProperty")
        set(value) = setOversampling(value)

    /**
     * Loads an AngelCode BMFont (.fnt, .font) bitmap font from file `path`. Warning: This method
     * should only be used in the editor or in cases when you need to load external fonts at run-time,
     * such as fonts located at the `user://` directory.
     *
     * Generated from Godot docs: FontFile.load_bitmap_font
     */
    fun loadBitmapFont(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.loadBitmapFontBind, segment, path))
    }

    /**
     * Loads a TrueType (.ttf), OpenType (.otf), WOFF (.woff), WOFF2 (.woff2) or Type 1 (.pfb, .pfm)
     * dynamic font from file `path`. Warning: This method should only be used in the editor or in
     * cases when you need to load external fonts at run-time, such as fonts located at the `user://`
     * directory.
     *
     * Generated from Godot docs: FontFile.load_dynamic_font
     */
    fun loadDynamicFont(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.loadDynamicFontBind, segment, path))
    }

    /**
     * Contents of the dynamic font source file.
     *
     * Generated from Godot docs: FontFile.set_data
     */
    fun setData(data: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithByteArrayArg(Binds.setDataBind, segment, data)
    }

    /**
     * Contents of the dynamic font source file.
     *
     * Generated from Godot docs: FontFile.get_data
     */
    fun getData(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(Binds.getDataBind, segment)
    }

    /**
     * Font family name.
     *
     * Generated from Godot docs: FontFile.set_font_name
     */
    fun setFontName(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setFontNameBind, segment, name)
    }

    /**
     * Font style name.
     *
     * Generated from Godot docs: FontFile.set_font_style_name
     */
    fun setFontStyleName(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.setFontStyleNameBind, segment, name)
    }

    /**
     * Font style flags.
     *
     * Generated from Godot docs: FontFile.set_font_style
     */
    fun setFontStyle(style: TextServer.FontStyle) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFontStyleBind, segment, style.value)
    }

    /**
     * Weight (boldness) of the font. A value in the `100...999` range, normal font weight is `400`,
     * bold font weight is `700`.
     *
     * Generated from Godot docs: FontFile.set_font_weight
     */
    fun setFontWeight(weight: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setFontWeightBind, segment, weight)
    }

    /**
     * Font stretch amount, compared to a normal width. A percentage value between `50%` and `200%`.
     *
     * Generated from Godot docs: FontFile.set_font_stretch
     */
    fun setFontStretch(stretch: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setFontStretchBind, segment, stretch)
    }

    /**
     * Font anti-aliasing mode.
     *
     * Generated from Godot docs: FontFile.set_antialiasing
     */
    fun setAntialiasing(antialiasing: TextServer.FontAntialiasing) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setAntialiasingBind, segment, antialiasing.value)
    }

    /**
     * Font anti-aliasing mode.
     *
     * Generated from Godot docs: FontFile.get_antialiasing
     */
    fun getAntialiasing(): TextServer.FontAntialiasing {
        checkOpen()
        return TextServer.FontAntialiasing(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAntialiasingBind, segment))
    }

    /**
     * If set to `true`, embedded font bitmap loading is disabled (bitmap-only and color fonts ignore
     * this property).
     *
     * Generated from Godot docs: FontFile.set_disable_embedded_bitmaps
     */
    fun setDisableEmbeddedBitmaps(disableEmbeddedBitmaps: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setDisableEmbeddedBitmapsBind, segment, disableEmbeddedBitmaps)
    }

    /**
     * If set to `true`, embedded font bitmap loading is disabled (bitmap-only and color fonts ignore
     * this property).
     *
     * Generated from Godot docs: FontFile.get_disable_embedded_bitmaps
     */
    fun getDisableEmbeddedBitmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getDisableEmbeddedBitmapsBind, segment)
    }

    /**
     * If set to `true`, generate mipmaps for the font textures.
     *
     * Generated from Godot docs: FontFile.set_generate_mipmaps
     */
    fun setGenerateMipmaps(generateMipmaps: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setGenerateMipmapsBind, segment, generateMipmaps)
    }

    /**
     * If set to `true`, generate mipmaps for the font textures.
     *
     * Generated from Godot docs: FontFile.get_generate_mipmaps
     */
    fun getGenerateMipmaps(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getGenerateMipmapsBind, segment)
    }

    /**
     * If set to `true`, glyphs of all sizes are rendered using single multichannel signed distance
     * field (MSDF) generated from the dynamic font vector data. Since this approach does not rely on
     * rasterizing the font every time its size changes, this allows for resizing the font in real-time
     * without any performance penalty. Text will also not look grainy for `Control`s that are scaled
     * down (or for `Label3D`s viewed from a long distance). As a downside, font hinting is not
     * available with MSDF. The lack of font hinting may result in less crisp and less readable fonts
     * at small sizes. Note: If using font outlines, `msdf_pixel_range` must be set to at least twice
     * the size of the largest font outline. Note: MSDF font rendering does not render glyphs with
     * overlapping shapes correctly. Overlapping shapes are not valid per the OpenType standard, but
     * are still commonly found in many font files, especially those converted by Google Fonts. To
     * avoid issues with overlapping glyphs, consider downloading the font file directly from the type
     * foundry instead of relying on Google Fonts.
     *
     * Generated from Godot docs: FontFile.set_multichannel_signed_distance_field
     */
    fun setMultichannelSignedDistanceField(msdf: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setMultichannelSignedDistanceFieldBind, segment, msdf)
    }

    /**
     * If set to `true`, glyphs of all sizes are rendered using single multichannel signed distance
     * field (MSDF) generated from the dynamic font vector data. Since this approach does not rely on
     * rasterizing the font every time its size changes, this allows for resizing the font in real-time
     * without any performance penalty. Text will also not look grainy for `Control`s that are scaled
     * down (or for `Label3D`s viewed from a long distance). As a downside, font hinting is not
     * available with MSDF. The lack of font hinting may result in less crisp and less readable fonts
     * at small sizes. Note: If using font outlines, `msdf_pixel_range` must be set to at least twice
     * the size of the largest font outline. Note: MSDF font rendering does not render glyphs with
     * overlapping shapes correctly. Overlapping shapes are not valid per the OpenType standard, but
     * are still commonly found in many font files, especially those converted by Google Fonts. To
     * avoid issues with overlapping glyphs, consider downloading the font file directly from the type
     * foundry instead of relying on Google Fonts.
     *
     * Generated from Godot docs: FontFile.is_multichannel_signed_distance_field
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
     * Generated from Godot docs: FontFile.set_msdf_pixel_range
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
     * Generated from Godot docs: FontFile.get_msdf_pixel_range
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
     * Generated from Godot docs: FontFile.set_msdf_size
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
     * Generated from Godot docs: FontFile.get_msdf_size
     */
    fun getMsdfSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMsdfSizeBind, segment)
    }

    /**
     * Font size, used only for the bitmap fonts.
     *
     * Generated from Godot docs: FontFile.set_fixed_size
     */
    fun setFixedSize(fixedSize: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setFixedSizeBind, segment, fixedSize)
    }

    /**
     * Font size, used only for the bitmap fonts.
     *
     * Generated from Godot docs: FontFile.get_fixed_size
     */
    fun getFixedSize(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getFixedSizeBind, segment)
    }

    /**
     * Scaling mode, used only for the bitmap fonts with `fixed_size` greater than zero.
     *
     * Generated from Godot docs: FontFile.set_fixed_size_scale_mode
     */
    fun setFixedSizeScaleMode(fixedSizeScaleMode: TextServer.FixedSizeScaleMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFixedSizeScaleModeBind, segment, fixedSizeScaleMode.value)
    }

    /**
     * Scaling mode, used only for the bitmap fonts with `fixed_size` greater than zero.
     *
     * Generated from Godot docs: FontFile.get_fixed_size_scale_mode
     */
    fun getFixedSizeScaleMode(): TextServer.FixedSizeScaleMode {
        checkOpen()
        return TextServer.FixedSizeScaleMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFixedSizeScaleModeBind, segment))
    }

    /**
     * If set to `true`, system fonts can be automatically used as fallbacks.
     *
     * Generated from Godot docs: FontFile.set_allow_system_fallback
     */
    fun setAllowSystemFallback(allowSystemFallback: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAllowSystemFallbackBind, segment, allowSystemFallback)
    }

    /**
     * If set to `true`, system fonts can be automatically used as fallbacks.
     *
     * Generated from Godot docs: FontFile.is_allow_system_fallback
     */
    fun isAllowSystemFallback(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAllowSystemFallbackBind, segment)
    }

    /**
     * If set to `true`, auto-hinting is supported and preferred over font built-in hinting. Used by
     * dynamic fonts only (MSDF fonts don't support hinting).
     *
     * Generated from Godot docs: FontFile.set_force_autohinter
     */
    fun setForceAutohinter(forceAutohinter: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setForceAutohinterBind, segment, forceAutohinter)
    }

    /**
     * If set to `true`, auto-hinting is supported and preferred over font built-in hinting. Used by
     * dynamic fonts only (MSDF fonts don't support hinting).
     *
     * Generated from Godot docs: FontFile.is_force_autohinter
     */
    fun isForceAutohinter(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isForceAutohinterBind, segment)
    }

    /**
     * If set to `true`, color modulation is applied when drawing colored glyphs, otherwise it's
     * applied to the monochrome glyphs only.
     *
     * Generated from Godot docs: FontFile.set_modulate_color_glyphs
     */
    fun setModulateColorGlyphs(modulate: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setModulateColorGlyphsBind, segment, modulate)
    }

    /**
     * If set to `true`, color modulation is applied when drawing colored glyphs, otherwise it's
     * applied to the monochrome glyphs only.
     *
     * Generated from Godot docs: FontFile.is_modulate_color_glyphs
     */
    fun isModulateColorGlyphs(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isModulateColorGlyphsBind, segment)
    }

    /**
     * Font hinting mode. Used by dynamic fonts only.
     *
     * Generated from Godot docs: FontFile.set_hinting
     */
    fun setHinting(hinting: TextServer.Hinting) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setHintingBind, segment, hinting.value)
    }

    /**
     * Font hinting mode. Used by dynamic fonts only.
     *
     * Generated from Godot docs: FontFile.get_hinting
     */
    fun getHinting(): TextServer.Hinting {
        checkOpen()
        return TextServer.Hinting(ObjectCalls.ptrcallNoArgsRetLong(Binds.getHintingBind, segment))
    }

    /**
     * Font glyph subpixel positioning mode. Subpixel positioning provides shaper text and better
     * kerning for smaller font sizes, at the cost of higher memory usage and lower font rasterization
     * speed. Use `TextServer.SubpixelPositioning.AUTO` to automatically enable it based on the font
     * size.
     *
     * Generated from Godot docs: FontFile.set_subpixel_positioning
     */
    fun setSubpixelPositioning(subpixelPositioning: TextServer.SubpixelPositioning) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setSubpixelPositioningBind, segment, subpixelPositioning.value)
    }

    /**
     * Font glyph subpixel positioning mode. Subpixel positioning provides shaper text and better
     * kerning for smaller font sizes, at the cost of higher memory usage and lower font rasterization
     * speed. Use `TextServer.SubpixelPositioning.AUTO` to automatically enable it based on the font
     * size.
     *
     * Generated from Godot docs: FontFile.get_subpixel_positioning
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
     * Generated from Godot docs: FontFile.set_keep_rounding_remainders
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
     * Generated from Godot docs: FontFile.get_keep_rounding_remainders
     */
    fun getKeepRoundingRemainders(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getKeepRoundingRemaindersBind, segment)
    }

    /**
     * If set to a positive value, overrides the oversampling factor of the viewport this font is used
     * in. See `Viewport.oversampling`. This value doesn't override the `oversampling` parameter of
     * `draw_*` methods.
     *
     * Generated from Godot docs: FontFile.set_oversampling
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
     * Generated from Godot docs: FontFile.get_oversampling
     */
    fun getOversampling(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getOversamplingBind, segment)
    }

    /**
     * Returns number of the font cache entries.
     *
     * Generated from Godot docs: FontFile.get_cache_count
     */
    fun getCacheCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCacheCountBind, segment)
    }

    /**
     * Removes all font cache entries.
     *
     * Generated from Godot docs: FontFile.clear_cache
     */
    fun clearCache() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearCacheBind, segment)
    }

    /**
     * Removes specified font cache entry.
     *
     * Generated from Godot docs: FontFile.remove_cache
     */
    fun removeCache(cacheIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeCacheBind, segment, cacheIndex)
    }

    /**
     * Returns list of the font sizes in the cache. Each size is `Vector2i` with font size and outline
     * size.
     *
     * Generated from Godot docs: FontFile.get_size_cache_list
     */
    fun getSizeCacheList(cacheIndex: Int): List<Vector2i> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetVector2iList(Binds.getSizeCacheListBind, segment, cacheIndex)
    }

    /**
     * Removes all font sizes from the cache entry.
     *
     * Generated from Godot docs: FontFile.clear_size_cache
     */
    fun clearSizeCache(cacheIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.clearSizeCacheBind, segment, cacheIndex)
    }

    /**
     * Removes specified font size from the cache entry.
     *
     * Generated from Godot docs: FontFile.remove_size_cache
     */
    fun removeSizeCache(cacheIndex: Int, size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2iArg(Binds.removeSizeCacheBind, segment, cacheIndex, size)
    }

    /**
     * Sets variation coordinates for the specified font cache entry. See
     * `Font.get_supported_variation_list` for more info.
     *
     * Generated from Godot docs: FontFile.set_variation_coordinates
     */
    fun setVariationCoordinates(cacheIndex: Int, variationCoordinates: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDictionaryArg(Binds.setVariationCoordinatesBind, segment, cacheIndex, variationCoordinates)
    }

    /**
     * Returns variation coordinates for the specified font cache entry. See
     * `Font.get_supported_variation_list` for more info.
     *
     * Generated from Godot docs: FontFile.get_variation_coordinates
     */
    fun getVariationCoordinates(cacheIndex: Int): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDictionary(Binds.getVariationCoordinatesBind, segment, cacheIndex)
    }

    /**
     * Sets embolden strength, if is not equal to zero, emboldens the font outlines. Negative values
     * reduce the outline thickness.
     *
     * Generated from Godot docs: FontFile.set_embolden
     */
    fun setEmbolden(cacheIndex: Int, strength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setEmboldenBind, segment, cacheIndex, strength)
    }

    /**
     * Returns embolden strength, if is not equal to zero, emboldens the font outlines. Negative values
     * reduce the outline thickness.
     *
     * Generated from Godot docs: FontFile.get_embolden
     */
    fun getEmbolden(cacheIndex: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getEmboldenBind, segment, cacheIndex)
    }

    /**
     * Sets 2D transform, applied to the font outlines, can be used for slanting, flipping, and
     * rotating glyphs.
     *
     * Generated from Godot docs: FontFile.set_transform
     */
    fun setTransform(cacheIndex: Int, transform: Transform2D) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndTransform2DArg(Binds.setTransformBind, segment, cacheIndex, transform)
    }

    /**
     * Returns 2D transform, applied to the font outlines, can be used for slanting, flipping and
     * rotating glyphs.
     *
     * Generated from Godot docs: FontFile.get_transform
     */
    fun getTransform(cacheIndex: Int): Transform2D {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetTransform2D(Binds.getTransformBind, segment, cacheIndex)
    }

    /**
     * Sets the spacing for `spacing` to `value` in pixels (not relative to the font size).
     *
     * Generated from Godot docs: FontFile.set_extra_spacing
     */
    fun setExtraSpacing(cacheIndex: Int, spacing: TextServer.SpacingType, value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithIntTwoLongArgs(Binds.setExtraSpacingBind, segment, cacheIndex, spacing.value, value)
    }

    /**
     * Returns spacing for `spacing` in pixels (not relative to the font size).
     *
     * Generated from Godot docs: FontFile.get_extra_spacing
     */
    fun getExtraSpacing(cacheIndex: Int, spacing: TextServer.SpacingType): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndLongArgsRetLong(Binds.getExtraSpacingBind, segment, cacheIndex, spacing.value)
    }

    /**
     * Sets extra baseline offset (as a fraction of font height).
     *
     * Generated from Godot docs: FontFile.set_extra_baseline_offset
     */
    fun setExtraBaselineOffset(cacheIndex: Int, baselineOffset: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setExtraBaselineOffsetBind, segment, cacheIndex, baselineOffset)
    }

    /**
     * Returns extra baseline offset (as a fraction of font height).
     *
     * Generated from Godot docs: FontFile.get_extra_baseline_offset
     */
    fun getExtraBaselineOffset(cacheIndex: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getExtraBaselineOffsetBind, segment, cacheIndex)
    }

    /**
     * Sets an active face index in the TrueType / OpenType collection.
     *
     * Generated from Godot docs: FontFile.set_face_index
     */
    fun setFaceIndex(cacheIndex: Int, faceIndex: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setFaceIndexBind, segment, cacheIndex, faceIndex)
    }

    /**
     * Returns an active face index in the TrueType / OpenType collection.
     *
     * Generated from Godot docs: FontFile.get_face_index
     */
    fun getFaceIndex(cacheIndex: Int): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetLong(Binds.getFaceIndexBind, segment, cacheIndex)
    }

    /**
     * Sets the font ascent (number of pixels above the baseline).
     *
     * Generated from Godot docs: FontFile.set_cache_ascent
     */
    fun setCacheAscent(cacheIndex: Int, size: Int, ascent: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.setCacheAscentBind, segment, cacheIndex, size, ascent)
    }

    /**
     * Returns the font ascent (number of pixels above the baseline).
     *
     * Generated from Godot docs: FontFile.get_cache_ascent
     */
    fun getCacheAscent(cacheIndex: Int, size: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.getCacheAscentBind, segment, cacheIndex, size)
    }

    /**
     * Sets the font descent (number of pixels below the baseline).
     *
     * Generated from Godot docs: FontFile.set_cache_descent
     */
    fun setCacheDescent(cacheIndex: Int, size: Int, descent: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.setCacheDescentBind, segment, cacheIndex, size, descent)
    }

    /**
     * Returns the font descent (number of pixels below the baseline).
     *
     * Generated from Godot docs: FontFile.get_cache_descent
     */
    fun getCacheDescent(cacheIndex: Int, size: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.getCacheDescentBind, segment, cacheIndex, size)
    }

    /**
     * Sets pixel offset of the underline below the baseline.
     *
     * Generated from Godot docs: FontFile.set_cache_underline_position
     */
    fun setCacheUnderlinePosition(cacheIndex: Int, size: Int, underlinePosition: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.setCacheUnderlinePositionBind, segment, cacheIndex, size, underlinePosition)
    }

    /**
     * Returns pixel offset of the underline below the baseline.
     *
     * Generated from Godot docs: FontFile.get_cache_underline_position
     */
    fun getCacheUnderlinePosition(cacheIndex: Int, size: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.getCacheUnderlinePositionBind, segment, cacheIndex, size)
    }

    /**
     * Sets thickness of the underline in pixels.
     *
     * Generated from Godot docs: FontFile.set_cache_underline_thickness
     */
    fun setCacheUnderlineThickness(cacheIndex: Int, size: Int, underlineThickness: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.setCacheUnderlineThicknessBind, segment, cacheIndex, size, underlineThickness)
    }

    /**
     * Returns thickness of the underline in pixels.
     *
     * Generated from Godot docs: FontFile.get_cache_underline_thickness
     */
    fun getCacheUnderlineThickness(cacheIndex: Int, size: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.getCacheUnderlineThicknessBind, segment, cacheIndex, size)
    }

    /**
     * Sets scaling factor of the color bitmap font.
     *
     * Generated from Godot docs: FontFile.set_cache_scale
     */
    fun setCacheScale(cacheIndex: Int, size: Int, scale: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.setCacheScaleBind, segment, cacheIndex, size, scale)
    }

    /**
     * Returns scaling factor of the color bitmap font.
     *
     * Generated from Godot docs: FontFile.get_cache_scale
     */
    fun getCacheScale(cacheIndex: Int, size: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.getCacheScaleBind, segment, cacheIndex, size)
    }

    /**
     * Returns number of textures used by font cache entry.
     *
     * Generated from Godot docs: FontFile.get_texture_count
     */
    fun getTextureCount(cacheIndex: Int, size: Vector2i): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndVector2iArgRetInt(Binds.getTextureCountBind, segment, cacheIndex, size)
    }

    /**
     * Removes all textures from font cache entry. Note: This function will not remove glyphs
     * associated with the texture, use `remove_glyph` to remove them manually.
     *
     * Generated from Godot docs: FontFile.clear_textures
     */
    fun clearTextures(cacheIndex: Int, size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2iArg(Binds.clearTexturesBind, segment, cacheIndex, size)
    }

    /**
     * Removes specified texture from the cache entry. Note: This function will not remove glyphs
     * associated with the texture. Remove them manually using `remove_glyph`.
     *
     * Generated from Godot docs: FontFile.remove_texture
     */
    fun removeTexture(cacheIndex: Int, size: Vector2i, textureIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iAndIntArg(Binds.removeTextureBind, segment, cacheIndex, size, textureIndex)
    }

    /**
     * Sets font cache texture image.
     *
     * Generated from Godot docs: FontFile.set_texture_image
     */
    fun setTextureImage(cacheIndex: Int, size: Vector2i, textureIndex: Int, image: Image?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iIntObjectArgs(Binds.setTextureImageBind, segment, cacheIndex, size, textureIndex, image?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns a copy of the font cache texture image.
     *
     * Generated from Godot docs: FontFile.get_texture_image
     */
    fun getTextureImage(cacheIndex: Int, size: Vector2i, textureIndex: Int): Image? {
        checkOpen()
        return Image.wrapOwned(ObjectCalls.ptrcallWithIntVector2iIntArgsRetObject(Binds.getTextureImageBind, segment, cacheIndex, size, textureIndex))
    }

    /**
     * Sets array containing glyph packing data.
     *
     * Generated from Godot docs: FontFile.set_texture_offsets
     */
    fun setTextureOffsets(cacheIndex: Int, size: Vector2i, textureIndex: Int, offset: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iIntPackedInt32ListArgs(Binds.setTextureOffsetsBind, segment, cacheIndex, size, textureIndex, offset)
    }

    /**
     * Returns a copy of the array containing glyph packing data.
     *
     * Generated from Godot docs: FontFile.get_texture_offsets
     */
    fun getTextureOffsets(cacheIndex: Int, size: Vector2i, textureIndex: Int): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetPackedInt32List(Binds.getTextureOffsetsBind, segment, cacheIndex, size, textureIndex)
    }

    /**
     * Returns list of rendered glyphs in the cache entry.
     *
     * Generated from Godot docs: FontFile.get_glyph_list
     */
    fun getGlyphList(cacheIndex: Int, size: Vector2i): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iArgsRetPackedInt32List(Binds.getGlyphListBind, segment, cacheIndex, size)
    }

    /**
     * Removes all rendered glyph information from the cache entry. Note: This function will not remove
     * textures associated with the glyphs, use `remove_texture` to remove them manually.
     *
     * Generated from Godot docs: FontFile.clear_glyphs
     */
    fun clearGlyphs(cacheIndex: Int, size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndVector2iArg(Binds.clearGlyphsBind, segment, cacheIndex, size)
    }

    /**
     * Removes specified rendered glyph information from the cache entry. Note: This function will not
     * remove textures associated with the glyphs, use `remove_texture` to remove them manually.
     *
     * Generated from Godot docs: FontFile.remove_glyph
     */
    fun removeGlyph(cacheIndex: Int, size: Vector2i, glyph: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iAndIntArg(Binds.removeGlyphBind, segment, cacheIndex, size, glyph)
    }

    /**
     * Sets glyph advance (offset of the next glyph). Note: Advance for glyphs outlines is the same as
     * the base glyph advance and is not saved.
     *
     * Generated from Godot docs: FontFile.set_glyph_advance
     */
    fun setGlyphAdvance(cacheIndex: Int, size: Int, glyph: Int, advance: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithThreeIntAndVector2Arg(Binds.setGlyphAdvanceBind, segment, cacheIndex, size, glyph, advance)
    }

    /**
     * Returns glyph advance (offset of the next glyph). Note: Advance for glyphs outlines is the same
     * as the base glyph advance and is not saved.
     *
     * Generated from Godot docs: FontFile.get_glyph_advance
     */
    fun getGlyphAdvance(cacheIndex: Int, size: Int, glyph: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithThreeIntArgsRetVector2(Binds.getGlyphAdvanceBind, segment, cacheIndex, size, glyph)
    }

    /**
     * Sets glyph offset from the baseline.
     *
     * Generated from Godot docs: FontFile.set_glyph_offset
     */
    fun setGlyphOffset(cacheIndex: Int, size: Vector2i, glyph: Int, offset: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iIntVector2Args(Binds.setGlyphOffsetBind, segment, cacheIndex, size, glyph, offset)
    }

    /**
     * Returns glyph offset from the baseline.
     *
     * Generated from Godot docs: FontFile.get_glyph_offset
     */
    fun getGlyphOffset(cacheIndex: Int, size: Vector2i, glyph: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetVector2(Binds.getGlyphOffsetBind, segment, cacheIndex, size, glyph)
    }

    /**
     * Sets glyph size.
     *
     * Generated from Godot docs: FontFile.set_glyph_size
     */
    fun setGlyphSize(cacheIndex: Int, size: Vector2i, glyph: Int, glSize: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iIntVector2Args(Binds.setGlyphSizeBind, segment, cacheIndex, size, glyph, glSize)
    }

    /**
     * Returns glyph size.
     *
     * Generated from Godot docs: FontFile.get_glyph_size
     */
    fun getGlyphSize(cacheIndex: Int, size: Vector2i, glyph: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetVector2(Binds.getGlyphSizeBind, segment, cacheIndex, size, glyph)
    }

    /**
     * Sets rectangle in the cache texture containing the glyph.
     *
     * Generated from Godot docs: FontFile.set_glyph_uv_rect
     */
    fun setGlyphUvRect(cacheIndex: Int, size: Vector2i, glyph: Int, uvRect: Rect2) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iIntRect2Args(Binds.setGlyphUvRectBind, segment, cacheIndex, size, glyph, uvRect)
    }

    /**
     * Returns rectangle in the cache texture containing the glyph.
     *
     * Generated from Godot docs: FontFile.get_glyph_uv_rect
     */
    fun getGlyphUvRect(cacheIndex: Int, size: Vector2i, glyph: Int): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetRect2(Binds.getGlyphUvRectBind, segment, cacheIndex, size, glyph)
    }

    /**
     * Sets index of the cache texture containing the glyph.
     *
     * Generated from Godot docs: FontFile.set_glyph_texture_idx
     */
    fun setGlyphTextureIdx(cacheIndex: Int, size: Vector2i, glyph: Int, textureIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iTwoIntArgs(Binds.setGlyphTextureIdxBind, segment, cacheIndex, size, glyph, textureIdx)
    }

    /**
     * Returns index of the cache texture containing the glyph.
     *
     * Generated from Godot docs: FontFile.get_glyph_texture_idx
     */
    fun getGlyphTextureIdx(cacheIndex: Int, size: Vector2i, glyph: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntVector2iIntArgsRetInt(Binds.getGlyphTextureIdxBind, segment, cacheIndex, size, glyph)
    }

    /**
     * Returns list of the kerning overrides.
     *
     * Generated from Godot docs: FontFile.get_kerning_list
     */
    fun getKerningList(cacheIndex: Int, size: Int): List<Vector2i> {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector2iList(Binds.getKerningListBind, segment, cacheIndex, size)
    }

    /**
     * Removes all kerning overrides.
     *
     * Generated from Godot docs: FontFile.clear_kerning_map
     */
    fun clearKerningMap(cacheIndex: Int, size: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.clearKerningMapBind, segment, cacheIndex, size)
    }

    /**
     * Removes kerning override for the pair of glyphs.
     *
     * Generated from Godot docs: FontFile.remove_kerning
     */
    fun removeKerning(cacheIndex: Int, size: Int, glyphPair: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndVector2iArg(Binds.removeKerningBind, segment, cacheIndex, size, glyphPair)
    }

    /**
     * Sets kerning for the pair of glyphs.
     *
     * Generated from Godot docs: FontFile.set_kerning
     */
    fun setKerning(cacheIndex: Int, size: Int, glyphPair: Vector2i, kerning: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntVector2iVector2Args(Binds.setKerningBind, segment, cacheIndex, size, glyphPair, kerning)
    }

    /**
     * Returns kerning for the pair of glyphs.
     *
     * Generated from Godot docs: FontFile.get_kerning
     */
    fun getKerning(cacheIndex: Int, size: Int, glyphPair: Vector2i): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntVector2iArgRetVector2(Binds.getKerningBind, segment, cacheIndex, size, glyphPair)
    }

    /**
     * Renders the range of characters to the font cache texture.
     *
     * Generated from Godot docs: FontFile.render_range
     */
    fun renderRange(cacheIndex: Int, size: Vector2i, start: Int, end: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iTwoIntArgs(Binds.renderRangeBind, segment, cacheIndex, size, start, end)
    }

    /**
     * Renders specified glyph to the font cache texture.
     *
     * Generated from Godot docs: FontFile.render_glyph
     */
    fun renderGlyph(cacheIndex: Int, size: Vector2i, index: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntVector2iAndIntArg(Binds.renderGlyphBind, segment, cacheIndex, size, index)
    }

    /**
     * Adds override for `Font.is_language_supported`.
     *
     * Generated from Godot docs: FontFile.set_language_support_override
     */
    fun setLanguageSupportOverride(language: String, supported: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndBoolArg(Binds.setLanguageSupportOverrideBind, segment, language, supported)
    }

    /**
     * Returns `true` if support override is enabled for the `language`.
     *
     * Generated from Godot docs: FontFile.get_language_support_override
     */
    fun getLanguageSupportOverride(language: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.getLanguageSupportOverrideBind, segment, language)
    }

    /**
     * Remove language support override.
     *
     * Generated from Godot docs: FontFile.remove_language_support_override
     */
    fun removeLanguageSupportOverride(language: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.removeLanguageSupportOverrideBind, segment, language)
    }

    /**
     * Returns list of language support overrides.
     *
     * Generated from Godot docs: FontFile.get_language_support_overrides
     */
    fun getLanguageSupportOverrides(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getLanguageSupportOverridesBind, segment)
    }

    /**
     * Adds override for `Font.is_script_supported`.
     *
     * Generated from Godot docs: FontFile.set_script_support_override
     */
    fun setScriptSupportOverride(script: String, supported: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithStringAndBoolArg(Binds.setScriptSupportOverrideBind, segment, script, supported)
    }

    /**
     * Returns `true` if support override is enabled for the `script`.
     *
     * Generated from Godot docs: FontFile.get_script_support_override
     */
    fun getScriptSupportOverride(script: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.getScriptSupportOverrideBind, segment, script)
    }

    /**
     * Removes script support override.
     *
     * Generated from Godot docs: FontFile.remove_script_support_override
     */
    fun removeScriptSupportOverride(script: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringArg(Binds.removeScriptSupportOverrideBind, segment, script)
    }

    /**
     * Returns list of script support overrides.
     *
     * Generated from Godot docs: FontFile.get_script_support_overrides
     */
    fun getScriptSupportOverrides(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getScriptSupportOverridesBind, segment)
    }

    /**
     * Font OpenType feature set override.
     *
     * Generated from Godot docs: FontFile.set_opentype_feature_overrides
     */
    fun setOpentypeFeatureOverrides(overrides: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithDictionaryArg(Binds.setOpentypeFeatureOverridesBind, segment, overrides)
    }

    /**
     * Font OpenType feature set override.
     *
     * Generated from Godot docs: FontFile.get_opentype_feature_overrides
     */
    fun getOpentypeFeatureOverrides(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getOpentypeFeatureOverridesBind, segment)
    }

    /**
     * Returns the glyph index of a `char`, optionally modified by the `variation_selector`.
     *
     * Generated from Godot docs: FontFile.get_glyph_index
     */
    fun getGlyphIndex(size: Int, char: Int, variationSelector: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithThreeIntArgsRetInt(Binds.getGlyphIndexBind, segment, size, char, variationSelector)
    }

    /**
     * Returns character code associated with `glyph_index`, or `0` if `glyph_index` is invalid. See
     * `get_glyph_index`.
     *
     * Generated from Godot docs: FontFile.get_char_from_glyph_index
     */
    fun getCharFromGlyphIndex(size: Int, glyphIndex: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(Binds.getCharFromGlyphIndexBind, segment, size, glyphIndex)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): FontFile? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): FontFile? =
            if (handle.address() == 0L) null else RefCounted.owned(FontFile(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): FontFile? =
            if (handle.address() == 0L) null else FontFile(GodotHandle(handle))
    }

    private object Binds {
        private const val LOAD_BITMAP_FONT_HASH = 166001499L
        @JvmField
        val loadBitmapFontBind =
            ObjectCalls.getMethodBind("FontFile", "load_bitmap_font", LOAD_BITMAP_FONT_HASH)

        private const val LOAD_DYNAMIC_FONT_HASH = 166001499L
        @JvmField
        val loadDynamicFontBind =
            ObjectCalls.getMethodBind("FontFile", "load_dynamic_font", LOAD_DYNAMIC_FONT_HASH)

        private const val SET_DATA_HASH = 2971499966L
        @JvmField
        val setDataBind =
            ObjectCalls.getMethodBind("FontFile", "set_data", SET_DATA_HASH)

        private const val GET_DATA_HASH = 2362200018L
        @JvmField
        val getDataBind =
            ObjectCalls.getMethodBind("FontFile", "get_data", GET_DATA_HASH)

        private const val SET_FONT_NAME_HASH = 83702148L
        @JvmField
        val setFontNameBind =
            ObjectCalls.getMethodBind("FontFile", "set_font_name", SET_FONT_NAME_HASH)

        private const val SET_FONT_STYLE_NAME_HASH = 83702148L
        @JvmField
        val setFontStyleNameBind =
            ObjectCalls.getMethodBind("FontFile", "set_font_style_name", SET_FONT_STYLE_NAME_HASH)

        private const val SET_FONT_STYLE_HASH = 918070724L
        @JvmField
        val setFontStyleBind =
            ObjectCalls.getMethodBind("FontFile", "set_font_style", SET_FONT_STYLE_HASH)

        private const val SET_FONT_WEIGHT_HASH = 1286410249L
        @JvmField
        val setFontWeightBind =
            ObjectCalls.getMethodBind("FontFile", "set_font_weight", SET_FONT_WEIGHT_HASH)

        private const val SET_FONT_STRETCH_HASH = 1286410249L
        @JvmField
        val setFontStretchBind =
            ObjectCalls.getMethodBind("FontFile", "set_font_stretch", SET_FONT_STRETCH_HASH)

        private const val SET_ANTIALIASING_HASH = 1669900L
        @JvmField
        val setAntialiasingBind =
            ObjectCalls.getMethodBind("FontFile", "set_antialiasing", SET_ANTIALIASING_HASH)

        private const val GET_ANTIALIASING_HASH = 4262718649L
        @JvmField
        val getAntialiasingBind =
            ObjectCalls.getMethodBind("FontFile", "get_antialiasing", GET_ANTIALIASING_HASH)

        private const val SET_DISABLE_EMBEDDED_BITMAPS_HASH = 2586408642L
        @JvmField
        val setDisableEmbeddedBitmapsBind =
            ObjectCalls.getMethodBind("FontFile", "set_disable_embedded_bitmaps", SET_DISABLE_EMBEDDED_BITMAPS_HASH)

        private const val GET_DISABLE_EMBEDDED_BITMAPS_HASH = 36873697L
        @JvmField
        val getDisableEmbeddedBitmapsBind =
            ObjectCalls.getMethodBind("FontFile", "get_disable_embedded_bitmaps", GET_DISABLE_EMBEDDED_BITMAPS_HASH)

        private const val SET_GENERATE_MIPMAPS_HASH = 2586408642L
        @JvmField
        val setGenerateMipmapsBind =
            ObjectCalls.getMethodBind("FontFile", "set_generate_mipmaps", SET_GENERATE_MIPMAPS_HASH)

        private const val GET_GENERATE_MIPMAPS_HASH = 36873697L
        @JvmField
        val getGenerateMipmapsBind =
            ObjectCalls.getMethodBind("FontFile", "get_generate_mipmaps", GET_GENERATE_MIPMAPS_HASH)

        private const val SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 2586408642L
        @JvmField
        val setMultichannelSignedDistanceFieldBind =
            ObjectCalls.getMethodBind("FontFile", "set_multichannel_signed_distance_field", SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)

        private const val IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 36873697L
        @JvmField
        val isMultichannelSignedDistanceFieldBind =
            ObjectCalls.getMethodBind("FontFile", "is_multichannel_signed_distance_field", IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)

        private const val SET_MSDF_PIXEL_RANGE_HASH = 1286410249L
        @JvmField
        val setMsdfPixelRangeBind =
            ObjectCalls.getMethodBind("FontFile", "set_msdf_pixel_range", SET_MSDF_PIXEL_RANGE_HASH)

        private const val GET_MSDF_PIXEL_RANGE_HASH = 3905245786L
        @JvmField
        val getMsdfPixelRangeBind =
            ObjectCalls.getMethodBind("FontFile", "get_msdf_pixel_range", GET_MSDF_PIXEL_RANGE_HASH)

        private const val SET_MSDF_SIZE_HASH = 1286410249L
        @JvmField
        val setMsdfSizeBind =
            ObjectCalls.getMethodBind("FontFile", "set_msdf_size", SET_MSDF_SIZE_HASH)

        private const val GET_MSDF_SIZE_HASH = 3905245786L
        @JvmField
        val getMsdfSizeBind =
            ObjectCalls.getMethodBind("FontFile", "get_msdf_size", GET_MSDF_SIZE_HASH)

        private const val SET_FIXED_SIZE_HASH = 1286410249L
        @JvmField
        val setFixedSizeBind =
            ObjectCalls.getMethodBind("FontFile", "set_fixed_size", SET_FIXED_SIZE_HASH)

        private const val GET_FIXED_SIZE_HASH = 3905245786L
        @JvmField
        val getFixedSizeBind =
            ObjectCalls.getMethodBind("FontFile", "get_fixed_size", GET_FIXED_SIZE_HASH)

        private const val SET_FIXED_SIZE_SCALE_MODE_HASH = 1660989956L
        @JvmField
        val setFixedSizeScaleModeBind =
            ObjectCalls.getMethodBind("FontFile", "set_fixed_size_scale_mode", SET_FIXED_SIZE_SCALE_MODE_HASH)

        private const val GET_FIXED_SIZE_SCALE_MODE_HASH = 753873478L
        @JvmField
        val getFixedSizeScaleModeBind =
            ObjectCalls.getMethodBind("FontFile", "get_fixed_size_scale_mode", GET_FIXED_SIZE_SCALE_MODE_HASH)

        private const val SET_ALLOW_SYSTEM_FALLBACK_HASH = 2586408642L
        @JvmField
        val setAllowSystemFallbackBind =
            ObjectCalls.getMethodBind("FontFile", "set_allow_system_fallback", SET_ALLOW_SYSTEM_FALLBACK_HASH)

        private const val IS_ALLOW_SYSTEM_FALLBACK_HASH = 36873697L
        @JvmField
        val isAllowSystemFallbackBind =
            ObjectCalls.getMethodBind("FontFile", "is_allow_system_fallback", IS_ALLOW_SYSTEM_FALLBACK_HASH)

        private const val SET_FORCE_AUTOHINTER_HASH = 2586408642L
        @JvmField
        val setForceAutohinterBind =
            ObjectCalls.getMethodBind("FontFile", "set_force_autohinter", SET_FORCE_AUTOHINTER_HASH)

        private const val IS_FORCE_AUTOHINTER_HASH = 36873697L
        @JvmField
        val isForceAutohinterBind =
            ObjectCalls.getMethodBind("FontFile", "is_force_autohinter", IS_FORCE_AUTOHINTER_HASH)

        private const val SET_MODULATE_COLOR_GLYPHS_HASH = 2586408642L
        @JvmField
        val setModulateColorGlyphsBind =
            ObjectCalls.getMethodBind("FontFile", "set_modulate_color_glyphs", SET_MODULATE_COLOR_GLYPHS_HASH)

        private const val IS_MODULATE_COLOR_GLYPHS_HASH = 36873697L
        @JvmField
        val isModulateColorGlyphsBind =
            ObjectCalls.getMethodBind("FontFile", "is_modulate_color_glyphs", IS_MODULATE_COLOR_GLYPHS_HASH)

        private const val SET_HINTING_HASH = 1827459492L
        @JvmField
        val setHintingBind =
            ObjectCalls.getMethodBind("FontFile", "set_hinting", SET_HINTING_HASH)

        private const val GET_HINTING_HASH = 3683214614L
        @JvmField
        val getHintingBind =
            ObjectCalls.getMethodBind("FontFile", "get_hinting", GET_HINTING_HASH)

        private const val SET_SUBPIXEL_POSITIONING_HASH = 4225742182L
        @JvmField
        val setSubpixelPositioningBind =
            ObjectCalls.getMethodBind("FontFile", "set_subpixel_positioning", SET_SUBPIXEL_POSITIONING_HASH)

        private const val GET_SUBPIXEL_POSITIONING_HASH = 1069238588L
        @JvmField
        val getSubpixelPositioningBind =
            ObjectCalls.getMethodBind("FontFile", "get_subpixel_positioning", GET_SUBPIXEL_POSITIONING_HASH)

        private const val SET_KEEP_ROUNDING_REMAINDERS_HASH = 2586408642L
        @JvmField
        val setKeepRoundingRemaindersBind =
            ObjectCalls.getMethodBind("FontFile", "set_keep_rounding_remainders", SET_KEEP_ROUNDING_REMAINDERS_HASH)

        private const val GET_KEEP_ROUNDING_REMAINDERS_HASH = 36873697L
        @JvmField
        val getKeepRoundingRemaindersBind =
            ObjectCalls.getMethodBind("FontFile", "get_keep_rounding_remainders", GET_KEEP_ROUNDING_REMAINDERS_HASH)

        private const val SET_OVERSAMPLING_HASH = 373806689L
        @JvmField
        val setOversamplingBind =
            ObjectCalls.getMethodBind("FontFile", "set_oversampling", SET_OVERSAMPLING_HASH)

        private const val GET_OVERSAMPLING_HASH = 1740695150L
        @JvmField
        val getOversamplingBind =
            ObjectCalls.getMethodBind("FontFile", "get_oversampling", GET_OVERSAMPLING_HASH)

        private const val GET_CACHE_COUNT_HASH = 3905245786L
        @JvmField
        val getCacheCountBind =
            ObjectCalls.getMethodBind("FontFile", "get_cache_count", GET_CACHE_COUNT_HASH)

        private const val CLEAR_CACHE_HASH = 3218959716L
        @JvmField
        val clearCacheBind =
            ObjectCalls.getMethodBind("FontFile", "clear_cache", CLEAR_CACHE_HASH)

        private const val REMOVE_CACHE_HASH = 1286410249L
        @JvmField
        val removeCacheBind =
            ObjectCalls.getMethodBind("FontFile", "remove_cache", REMOVE_CACHE_HASH)

        private const val GET_SIZE_CACHE_LIST_HASH = 663333327L
        @JvmField
        val getSizeCacheListBind =
            ObjectCalls.getMethodBind("FontFile", "get_size_cache_list", GET_SIZE_CACHE_LIST_HASH)

        private const val CLEAR_SIZE_CACHE_HASH = 1286410249L
        @JvmField
        val clearSizeCacheBind =
            ObjectCalls.getMethodBind("FontFile", "clear_size_cache", CLEAR_SIZE_CACHE_HASH)

        private const val REMOVE_SIZE_CACHE_HASH = 2311374912L
        @JvmField
        val removeSizeCacheBind =
            ObjectCalls.getMethodBind("FontFile", "remove_size_cache", REMOVE_SIZE_CACHE_HASH)

        private const val SET_VARIATION_COORDINATES_HASH = 64545446L
        @JvmField
        val setVariationCoordinatesBind =
            ObjectCalls.getMethodBind("FontFile", "set_variation_coordinates", SET_VARIATION_COORDINATES_HASH)

        private const val GET_VARIATION_COORDINATES_HASH = 3485342025L
        @JvmField
        val getVariationCoordinatesBind =
            ObjectCalls.getMethodBind("FontFile", "get_variation_coordinates", GET_VARIATION_COORDINATES_HASH)

        private const val SET_EMBOLDEN_HASH = 1602489585L
        @JvmField
        val setEmboldenBind =
            ObjectCalls.getMethodBind("FontFile", "set_embolden", SET_EMBOLDEN_HASH)

        private const val GET_EMBOLDEN_HASH = 2339986948L
        @JvmField
        val getEmboldenBind =
            ObjectCalls.getMethodBind("FontFile", "get_embolden", GET_EMBOLDEN_HASH)

        private const val SET_TRANSFORM_HASH = 30160968L
        @JvmField
        val setTransformBind =
            ObjectCalls.getMethodBind("FontFile", "set_transform", SET_TRANSFORM_HASH)

        private const val GET_TRANSFORM_HASH = 3836996910L
        @JvmField
        val getTransformBind =
            ObjectCalls.getMethodBind("FontFile", "get_transform", GET_TRANSFORM_HASH)

        private const val SET_EXTRA_SPACING_HASH = 62942285L
        @JvmField
        val setExtraSpacingBind =
            ObjectCalls.getMethodBind("FontFile", "set_extra_spacing", SET_EXTRA_SPACING_HASH)

        private const val GET_EXTRA_SPACING_HASH = 1924257185L
        @JvmField
        val getExtraSpacingBind =
            ObjectCalls.getMethodBind("FontFile", "get_extra_spacing", GET_EXTRA_SPACING_HASH)

        private const val SET_EXTRA_BASELINE_OFFSET_HASH = 1602489585L
        @JvmField
        val setExtraBaselineOffsetBind =
            ObjectCalls.getMethodBind("FontFile", "set_extra_baseline_offset", SET_EXTRA_BASELINE_OFFSET_HASH)

        private const val GET_EXTRA_BASELINE_OFFSET_HASH = 2339986948L
        @JvmField
        val getExtraBaselineOffsetBind =
            ObjectCalls.getMethodBind("FontFile", "get_extra_baseline_offset", GET_EXTRA_BASELINE_OFFSET_HASH)

        private const val SET_FACE_INDEX_HASH = 3937882851L
        @JvmField
        val setFaceIndexBind =
            ObjectCalls.getMethodBind("FontFile", "set_face_index", SET_FACE_INDEX_HASH)

        private const val GET_FACE_INDEX_HASH = 923996154L
        @JvmField
        val getFaceIndexBind =
            ObjectCalls.getMethodBind("FontFile", "get_face_index", GET_FACE_INDEX_HASH)

        private const val SET_CACHE_ASCENT_HASH = 3506521499L
        @JvmField
        val setCacheAscentBind =
            ObjectCalls.getMethodBind("FontFile", "set_cache_ascent", SET_CACHE_ASCENT_HASH)

        private const val GET_CACHE_ASCENT_HASH = 3085491603L
        @JvmField
        val getCacheAscentBind =
            ObjectCalls.getMethodBind("FontFile", "get_cache_ascent", GET_CACHE_ASCENT_HASH)

        private const val SET_CACHE_DESCENT_HASH = 3506521499L
        @JvmField
        val setCacheDescentBind =
            ObjectCalls.getMethodBind("FontFile", "set_cache_descent", SET_CACHE_DESCENT_HASH)

        private const val GET_CACHE_DESCENT_HASH = 3085491603L
        @JvmField
        val getCacheDescentBind =
            ObjectCalls.getMethodBind("FontFile", "get_cache_descent", GET_CACHE_DESCENT_HASH)

        private const val SET_CACHE_UNDERLINE_POSITION_HASH = 3506521499L
        @JvmField
        val setCacheUnderlinePositionBind =
            ObjectCalls.getMethodBind("FontFile", "set_cache_underline_position", SET_CACHE_UNDERLINE_POSITION_HASH)

        private const val GET_CACHE_UNDERLINE_POSITION_HASH = 3085491603L
        @JvmField
        val getCacheUnderlinePositionBind =
            ObjectCalls.getMethodBind("FontFile", "get_cache_underline_position", GET_CACHE_UNDERLINE_POSITION_HASH)

        private const val SET_CACHE_UNDERLINE_THICKNESS_HASH = 3506521499L
        @JvmField
        val setCacheUnderlineThicknessBind =
            ObjectCalls.getMethodBind("FontFile", "set_cache_underline_thickness", SET_CACHE_UNDERLINE_THICKNESS_HASH)

        private const val GET_CACHE_UNDERLINE_THICKNESS_HASH = 3085491603L
        @JvmField
        val getCacheUnderlineThicknessBind =
            ObjectCalls.getMethodBind("FontFile", "get_cache_underline_thickness", GET_CACHE_UNDERLINE_THICKNESS_HASH)

        private const val SET_CACHE_SCALE_HASH = 3506521499L
        @JvmField
        val setCacheScaleBind =
            ObjectCalls.getMethodBind("FontFile", "set_cache_scale", SET_CACHE_SCALE_HASH)

        private const val GET_CACHE_SCALE_HASH = 3085491603L
        @JvmField
        val getCacheScaleBind =
            ObjectCalls.getMethodBind("FontFile", "get_cache_scale", GET_CACHE_SCALE_HASH)

        private const val GET_TEXTURE_COUNT_HASH = 1987661582L
        @JvmField
        val getTextureCountBind =
            ObjectCalls.getMethodBind("FontFile", "get_texture_count", GET_TEXTURE_COUNT_HASH)

        private const val CLEAR_TEXTURES_HASH = 2311374912L
        @JvmField
        val clearTexturesBind =
            ObjectCalls.getMethodBind("FontFile", "clear_textures", CLEAR_TEXTURES_HASH)

        private const val REMOVE_TEXTURE_HASH = 2328951467L
        @JvmField
        val removeTextureBind =
            ObjectCalls.getMethodBind("FontFile", "remove_texture", REMOVE_TEXTURE_HASH)

        private const val SET_TEXTURE_IMAGE_HASH = 4157974066L
        @JvmField
        val setTextureImageBind =
            ObjectCalls.getMethodBind("FontFile", "set_texture_image", SET_TEXTURE_IMAGE_HASH)

        private const val GET_TEXTURE_IMAGE_HASH = 3878418953L
        @JvmField
        val getTextureImageBind =
            ObjectCalls.getMethodBind("FontFile", "get_texture_image", GET_TEXTURE_IMAGE_HASH)

        private const val SET_TEXTURE_OFFSETS_HASH = 2849993437L
        @JvmField
        val setTextureOffsetsBind =
            ObjectCalls.getMethodBind("FontFile", "set_texture_offsets", SET_TEXTURE_OFFSETS_HASH)

        private const val GET_TEXTURE_OFFSETS_HASH = 3703444828L
        @JvmField
        val getTextureOffsetsBind =
            ObjectCalls.getMethodBind("FontFile", "get_texture_offsets", GET_TEXTURE_OFFSETS_HASH)

        private const val GET_GLYPH_LIST_HASH = 681709689L
        @JvmField
        val getGlyphListBind =
            ObjectCalls.getMethodBind("FontFile", "get_glyph_list", GET_GLYPH_LIST_HASH)

        private const val CLEAR_GLYPHS_HASH = 2311374912L
        @JvmField
        val clearGlyphsBind =
            ObjectCalls.getMethodBind("FontFile", "clear_glyphs", CLEAR_GLYPHS_HASH)

        private const val REMOVE_GLYPH_HASH = 2328951467L
        @JvmField
        val removeGlyphBind =
            ObjectCalls.getMethodBind("FontFile", "remove_glyph", REMOVE_GLYPH_HASH)

        private const val SET_GLYPH_ADVANCE_HASH = 947991729L
        @JvmField
        val setGlyphAdvanceBind =
            ObjectCalls.getMethodBind("FontFile", "set_glyph_advance", SET_GLYPH_ADVANCE_HASH)

        private const val GET_GLYPH_ADVANCE_HASH = 1601573536L
        @JvmField
        val getGlyphAdvanceBind =
            ObjectCalls.getMethodBind("FontFile", "get_glyph_advance", GET_GLYPH_ADVANCE_HASH)

        private const val SET_GLYPH_OFFSET_HASH = 921719850L
        @JvmField
        val setGlyphOffsetBind =
            ObjectCalls.getMethodBind("FontFile", "set_glyph_offset", SET_GLYPH_OFFSET_HASH)

        private const val GET_GLYPH_OFFSET_HASH = 3205412300L
        @JvmField
        val getGlyphOffsetBind =
            ObjectCalls.getMethodBind("FontFile", "get_glyph_offset", GET_GLYPH_OFFSET_HASH)

        private const val SET_GLYPH_SIZE_HASH = 921719850L
        @JvmField
        val setGlyphSizeBind =
            ObjectCalls.getMethodBind("FontFile", "set_glyph_size", SET_GLYPH_SIZE_HASH)

        private const val GET_GLYPH_SIZE_HASH = 3205412300L
        @JvmField
        val getGlyphSizeBind =
            ObjectCalls.getMethodBind("FontFile", "get_glyph_size", GET_GLYPH_SIZE_HASH)

        private const val SET_GLYPH_UV_RECT_HASH = 3821620992L
        @JvmField
        val setGlyphUvRectBind =
            ObjectCalls.getMethodBind("FontFile", "set_glyph_uv_rect", SET_GLYPH_UV_RECT_HASH)

        private const val GET_GLYPH_UV_RECT_HASH = 3927917900L
        @JvmField
        val getGlyphUvRectBind =
            ObjectCalls.getMethodBind("FontFile", "get_glyph_uv_rect", GET_GLYPH_UV_RECT_HASH)

        private const val SET_GLYPH_TEXTURE_IDX_HASH = 355564111L
        @JvmField
        val setGlyphTextureIdxBind =
            ObjectCalls.getMethodBind("FontFile", "set_glyph_texture_idx", SET_GLYPH_TEXTURE_IDX_HASH)

        private const val GET_GLYPH_TEXTURE_IDX_HASH = 1629411054L
        @JvmField
        val getGlyphTextureIdxBind =
            ObjectCalls.getMethodBind("FontFile", "get_glyph_texture_idx", GET_GLYPH_TEXTURE_IDX_HASH)

        private const val GET_KERNING_LIST_HASH = 2345056839L
        @JvmField
        val getKerningListBind =
            ObjectCalls.getMethodBind("FontFile", "get_kerning_list", GET_KERNING_LIST_HASH)

        private const val CLEAR_KERNING_MAP_HASH = 3937882851L
        @JvmField
        val clearKerningMapBind =
            ObjectCalls.getMethodBind("FontFile", "clear_kerning_map", CLEAR_KERNING_MAP_HASH)

        private const val REMOVE_KERNING_HASH = 3930204747L
        @JvmField
        val removeKerningBind =
            ObjectCalls.getMethodBind("FontFile", "remove_kerning", REMOVE_KERNING_HASH)

        private const val SET_KERNING_HASH = 3182200918L
        @JvmField
        val setKerningBind =
            ObjectCalls.getMethodBind("FontFile", "set_kerning", SET_KERNING_HASH)

        private const val GET_KERNING_HASH = 1611912865L
        @JvmField
        val getKerningBind =
            ObjectCalls.getMethodBind("FontFile", "get_kerning", GET_KERNING_HASH)

        private const val RENDER_RANGE_HASH = 355564111L
        @JvmField
        val renderRangeBind =
            ObjectCalls.getMethodBind("FontFile", "render_range", RENDER_RANGE_HASH)

        private const val RENDER_GLYPH_HASH = 2328951467L
        @JvmField
        val renderGlyphBind =
            ObjectCalls.getMethodBind("FontFile", "render_glyph", RENDER_GLYPH_HASH)

        private const val SET_LANGUAGE_SUPPORT_OVERRIDE_HASH = 2678287736L
        @JvmField
        val setLanguageSupportOverrideBind =
            ObjectCalls.getMethodBind("FontFile", "set_language_support_override", SET_LANGUAGE_SUPPORT_OVERRIDE_HASH)

        private const val GET_LANGUAGE_SUPPORT_OVERRIDE_HASH = 3927539163L
        @JvmField
        val getLanguageSupportOverrideBind =
            ObjectCalls.getMethodBind("FontFile", "get_language_support_override", GET_LANGUAGE_SUPPORT_OVERRIDE_HASH)

        private const val REMOVE_LANGUAGE_SUPPORT_OVERRIDE_HASH = 83702148L
        @JvmField
        val removeLanguageSupportOverrideBind =
            ObjectCalls.getMethodBind("FontFile", "remove_language_support_override", REMOVE_LANGUAGE_SUPPORT_OVERRIDE_HASH)

        private const val GET_LANGUAGE_SUPPORT_OVERRIDES_HASH = 1139954409L
        @JvmField
        val getLanguageSupportOverridesBind =
            ObjectCalls.getMethodBind("FontFile", "get_language_support_overrides", GET_LANGUAGE_SUPPORT_OVERRIDES_HASH)

        private const val SET_SCRIPT_SUPPORT_OVERRIDE_HASH = 2678287736L
        @JvmField
        val setScriptSupportOverrideBind =
            ObjectCalls.getMethodBind("FontFile", "set_script_support_override", SET_SCRIPT_SUPPORT_OVERRIDE_HASH)

        private const val GET_SCRIPT_SUPPORT_OVERRIDE_HASH = 3927539163L
        @JvmField
        val getScriptSupportOverrideBind =
            ObjectCalls.getMethodBind("FontFile", "get_script_support_override", GET_SCRIPT_SUPPORT_OVERRIDE_HASH)

        private const val REMOVE_SCRIPT_SUPPORT_OVERRIDE_HASH = 83702148L
        @JvmField
        val removeScriptSupportOverrideBind =
            ObjectCalls.getMethodBind("FontFile", "remove_script_support_override", REMOVE_SCRIPT_SUPPORT_OVERRIDE_HASH)

        private const val GET_SCRIPT_SUPPORT_OVERRIDES_HASH = 1139954409L
        @JvmField
        val getScriptSupportOverridesBind =
            ObjectCalls.getMethodBind("FontFile", "get_script_support_overrides", GET_SCRIPT_SUPPORT_OVERRIDES_HASH)

        private const val SET_OPENTYPE_FEATURE_OVERRIDES_HASH = 4155329257L
        @JvmField
        val setOpentypeFeatureOverridesBind =
            ObjectCalls.getMethodBind("FontFile", "set_opentype_feature_overrides", SET_OPENTYPE_FEATURE_OVERRIDES_HASH)

        private const val GET_OPENTYPE_FEATURE_OVERRIDES_HASH = 3102165223L
        @JvmField
        val getOpentypeFeatureOverridesBind =
            ObjectCalls.getMethodBind("FontFile", "get_opentype_feature_overrides", GET_OPENTYPE_FEATURE_OVERRIDES_HASH)

        private const val GET_GLYPH_INDEX_HASH = 864943070L
        @JvmField
        val getGlyphIndexBind =
            ObjectCalls.getMethodBind("FontFile", "get_glyph_index", GET_GLYPH_INDEX_HASH)

        private const val GET_CHAR_FROM_GLYPH_INDEX_HASH = 3175239445L
        @JvmField
        val getCharFromGlyphIndexBind =
            ObjectCalls.getMethodBind("FontFile", "get_char_from_glyph_index", GET_CHAR_FROM_GLYPH_INDEX_HASH)
    }
}
