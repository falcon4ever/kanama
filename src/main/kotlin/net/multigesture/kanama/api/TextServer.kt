package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3i

/**
 * A server interface for font management and text rendering.
 *
 * Generated from Godot docs: TextServer
 */
open class TextServer(handle: MemorySegment) : RefCounted(handle) {
    /**
     * Returns `true` if the server supports a feature.
     *
     * Generated from Godot docs: TextServer.has_feature
     */
    fun hasFeature(feature: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasFeatureBind, handle, feature)
    }

    /**
     * Returns the name of the server interface.
     *
     * Generated from Godot docs: TextServer.get_name
     */
    fun getName(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getNameBind, handle)
    }

    /**
     * Returns text server features, see `Feature`.
     *
     * Generated from Godot docs: TextServer.get_features
     */
    fun getFeatures(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFeaturesBind, handle)
    }

    /**
     * Loads optional TextServer database (e.g. ICU break iterators and dictionaries). Note: This
     * function should be called before any other TextServer functions used, otherwise it won't have
     * any effect.
     *
     * Generated from Godot docs: TextServer.load_support_data
     */
    fun loadSupportData(filename: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(loadSupportDataBind, handle, filename)
    }

    /**
     * Returns default TextServer database (e.g. ICU break iterators and dictionaries) filename.
     *
     * Generated from Godot docs: TextServer.get_support_data_filename
     */
    fun getSupportDataFilename(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSupportDataFilenameBind, handle)
    }

    /**
     * Returns TextServer database (e.g. ICU break iterators and dictionaries) description.
     *
     * Generated from Godot docs: TextServer.get_support_data_info
     */
    fun getSupportDataInfo(): String {
        return ObjectCalls.ptrcallNoArgsRetString(getSupportDataInfoBind, handle)
    }

    /**
     * Saves optional TextServer database (e.g. ICU break iterators and dictionaries) to the file.
     * Note: This function is used by during project export, to include TextServer database.
     *
     * Generated from Godot docs: TextServer.save_support_data
     */
    fun saveSupportData(filename: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(saveSupportDataBind, handle, filename)
    }

    /**
     * Returns default TextServer database (e.g. ICU break iterators and dictionaries).
     *
     * Generated from Godot docs: TextServer.get_support_data
     */
    fun getSupportData(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(getSupportDataBind, handle)
    }

    /**
     * Returns `true` if the locale requires text server support data for line/word breaking.
     *
     * Generated from Godot docs: TextServer.is_locale_using_support_data
     */
    fun isLocaleUsingSupportData(locale: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(isLocaleUsingSupportDataBind, handle, locale)
    }

    /**
     * Returns `true` if locale is right-to-left.
     *
     * Generated from Godot docs: TextServer.is_locale_right_to_left
     */
    fun isLocaleRightToLeft(locale: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(isLocaleRightToLeftBind, handle, locale)
    }

    /**
     * Converts the given readable name of a feature, variation, script, or language to an OpenType
     * tag.
     *
     * Generated from Godot docs: TextServer.name_to_tag
     */
    fun nameToTag(name: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(nameToTagBind, handle, name)
    }

    /**
     * Converts the given OpenType tag to the readable name of a feature, variation, script, or
     * language.
     *
     * Generated from Godot docs: TextServer.tag_to_name
     */
    fun tagToName(tag: Long): String {
        return ObjectCalls.ptrcallWithLongArgRetString(tagToNameBind, handle, tag)
    }

    /**
     * Returns `true` if `rid` is valid resource owned by this text server.
     *
     * Generated from Godot docs: TextServer.has
     */
    fun has(rid: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(hasBind, handle, rid)
    }

    /**
     * Frees an object created by this `TextServer`.
     *
     * Generated from Godot docs: TextServer.free_rid
     */
    fun freeRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeRidBind, handle, rid)
    }

    /**
     * Creates a new, empty font cache entry resource. To free the resulting resource, use the
     * `free_rid` method.
     *
     * Generated from Godot docs: TextServer.create_font
     */
    fun createFont(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(createFontBind, handle)
    }

    /**
     * Creates a new variation existing font which is reusing the same glyph cache and font data. To
     * free the resulting resource, use the `free_rid` method.
     *
     * Generated from Godot docs: TextServer.create_font_linked_variation
     */
    fun createFontLinkedVariation(fontRid: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(createFontLinkedVariationBind, handle, fontRid)
    }

    /**
     * Sets font source data, e.g contents of the dynamic font source file.
     *
     * Generated from Godot docs: TextServer.font_set_data
     */
    fun fontSetData(fontRid: RID, data: ByteArray) {
        ObjectCalls.ptrcallWithRIDAndByteArrayArg(fontSetDataBind, handle, fontRid, data)
    }

    /**
     * Sets an active face index in the TrueType / OpenType collection.
     *
     * Generated from Godot docs: TextServer.font_set_face_index
     */
    fun fontSetFaceIndex(fontRid: RID, faceIndex: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetFaceIndexBind, handle, fontRid, faceIndex)
    }

    /**
     * Returns an active face index in the TrueType / OpenType collection.
     *
     * Generated from Godot docs: TextServer.font_get_face_index
     */
    fun fontGetFaceIndex(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetFaceIndexBind, handle, fontRid)
    }

    /**
     * Returns number of faces in the TrueType / OpenType collection.
     *
     * Generated from Godot docs: TextServer.font_get_face_count
     */
    fun fontGetFaceCount(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetFaceCountBind, handle, fontRid)
    }

    /**
     * Sets the font style flags. Note: This value is used for font matching only and will not affect
     * font rendering. Use `font_set_face_index`, `font_set_variation_coordinates`,
     * `font_set_embolden`, or `font_set_transform` instead.
     *
     * Generated from Godot docs: TextServer.font_set_style
     */
    fun fontSetStyle(fontRid: RID, style: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetStyleBind, handle, fontRid, style)
    }

    /**
     * Returns font style flags.
     *
     * Generated from Godot docs: TextServer.font_get_style
     */
    fun fontGetStyle(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetStyleBind, handle, fontRid)
    }

    /**
     * Sets the font family name.
     *
     * Generated from Godot docs: TextServer.font_set_name
     */
    fun fontSetName(fontRid: RID, name: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(fontSetNameBind, handle, fontRid, name)
    }

    /**
     * Returns font family name.
     *
     * Generated from Godot docs: TextServer.font_get_name
     */
    fun fontGetName(fontRid: RID): String {
        return ObjectCalls.ptrcallWithRIDArgRetString(fontGetNameBind, handle, fontRid)
    }

    /**
     * Returns `Dictionary` with OpenType font name strings (localized font names, version,
     * description, license information, sample text, etc.).
     *
     * Generated from Godot docs: TextServer.font_get_ot_name_strings
     */
    fun fontGetOtNameStrings(fontRid: RID): Map<String, Any?> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionary(fontGetOtNameStringsBind, handle, fontRid)
    }

    /**
     * Sets the font style name.
     *
     * Generated from Godot docs: TextServer.font_set_style_name
     */
    fun fontSetStyleName(fontRid: RID, name: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(fontSetStyleNameBind, handle, fontRid, name)
    }

    /**
     * Returns font style name.
     *
     * Generated from Godot docs: TextServer.font_get_style_name
     */
    fun fontGetStyleName(fontRid: RID): String {
        return ObjectCalls.ptrcallWithRIDArgRetString(fontGetStyleNameBind, handle, fontRid)
    }

    /**
     * Sets weight (boldness) of the font. A value in the `100...999` range, normal font weight is
     * `400`, bold font weight is `700`. Note: This value is used for font matching only and will not
     * affect font rendering. Use `font_set_face_index`, `font_set_variation_coordinates`, or
     * `font_set_embolden` instead.
     *
     * Generated from Godot docs: TextServer.font_set_weight
     */
    fun fontSetWeight(fontRid: RID, weight: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetWeightBind, handle, fontRid, weight)
    }

    /**
     * Returns weight (boldness) of the font. A value in the `100...999` range, normal font weight is
     * `400`, bold font weight is `700`.
     *
     * Generated from Godot docs: TextServer.font_get_weight
     */
    fun fontGetWeight(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetWeightBind, handle, fontRid)
    }

    /**
     * Sets font stretch amount, compared to a normal width. A percentage value between `50%` and
     * `200%`. Note: This value is used for font matching only and will not affect font rendering. Use
     * `font_set_face_index`, `font_set_variation_coordinates`, or `font_set_transform` instead.
     *
     * Generated from Godot docs: TextServer.font_set_stretch
     */
    fun fontSetStretch(fontRid: RID, weight: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetStretchBind, handle, fontRid, weight)
    }

    /**
     * Returns font stretch amount, compared to a normal width. A percentage value between `50%` and
     * `200%`.
     *
     * Generated from Godot docs: TextServer.font_get_stretch
     */
    fun fontGetStretch(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetStretchBind, handle, fontRid)
    }

    /**
     * Sets font anti-aliasing mode.
     *
     * Generated from Godot docs: TextServer.font_set_antialiasing
     */
    fun fontSetAntialiasing(fontRid: RID, antialiasing: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetAntialiasingBind, handle, fontRid, antialiasing)
    }

    /**
     * Returns font anti-aliasing mode.
     *
     * Generated from Godot docs: TextServer.font_get_antialiasing
     */
    fun fontGetAntialiasing(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetAntialiasingBind, handle, fontRid)
    }

    /**
     * If set to `true`, embedded font bitmap loading is disabled (bitmap-only and color fonts ignore
     * this property).
     *
     * Generated from Godot docs: TextServer.font_set_disable_embedded_bitmaps
     */
    fun fontSetDisableEmbeddedBitmaps(fontRid: RID, disableEmbeddedBitmaps: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetDisableEmbeddedBitmapsBind, handle, fontRid, disableEmbeddedBitmaps)
    }

    /**
     * Returns whether the font's embedded bitmap loading is disabled.
     *
     * Generated from Godot docs: TextServer.font_get_disable_embedded_bitmaps
     */
    fun fontGetDisableEmbeddedBitmaps(fontRid: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontGetDisableEmbeddedBitmapsBind, handle, fontRid)
    }

    /**
     * If set to `true` font texture mipmap generation is enabled.
     *
     * Generated from Godot docs: TextServer.font_set_generate_mipmaps
     */
    fun fontSetGenerateMipmaps(fontRid: RID, generateMipmaps: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetGenerateMipmapsBind, handle, fontRid, generateMipmaps)
    }

    /**
     * Returns `true` if font texture mipmap generation is enabled.
     *
     * Generated from Godot docs: TextServer.font_get_generate_mipmaps
     */
    fun fontGetGenerateMipmaps(fontRid: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontGetGenerateMipmapsBind, handle, fontRid)
    }

    /**
     * If set to `true`, glyphs of all sizes are rendered using single multichannel signed distance
     * field generated from the dynamic font vector data. MSDF rendering allows displaying the font at
     * any scaling factor without blurriness, and without incurring a CPU cost when the font size
     * changes (since the font no longer needs to be rasterized on the CPU). As a downside, font
     * hinting is not available with MSDF. The lack of font hinting may result in less crisp and less
     * readable fonts at small sizes. Note: MSDF font rendering does not render glyphs with overlapping
     * shapes correctly. Overlapping shapes are not valid per the OpenType standard, but are still
     * commonly found in many font files, especially those converted by Google Fonts. To avoid issues
     * with overlapping glyphs, consider downloading the font file directly from the type foundry
     * instead of relying on Google Fonts.
     *
     * Generated from Godot docs: TextServer.font_set_multichannel_signed_distance_field
     */
    fun fontSetMultichannelSignedDistanceField(fontRid: RID, msdf: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetMultichannelSignedDistanceFieldBind, handle, fontRid, msdf)
    }

    /**
     * Returns `true` if glyphs of all sizes are rendered using single multichannel signed distance
     * field generated from the dynamic font vector data.
     *
     * Generated from Godot docs: TextServer.font_is_multichannel_signed_distance_field
     */
    fun fontIsMultichannelSignedDistanceField(fontRid: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontIsMultichannelSignedDistanceFieldBind, handle, fontRid)
    }

    /**
     * Sets the width of the range around the shape between the minimum and maximum representable
     * signed distance.
     *
     * Generated from Godot docs: TextServer.font_set_msdf_pixel_range
     */
    fun fontSetMsdfPixelRange(fontRid: RID, msdfPixelRange: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetMsdfPixelRangeBind, handle, fontRid, msdfPixelRange)
    }

    /**
     * Returns the width of the range around the shape between the minimum and maximum representable
     * signed distance.
     *
     * Generated from Godot docs: TextServer.font_get_msdf_pixel_range
     */
    fun fontGetMsdfPixelRange(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetMsdfPixelRangeBind, handle, fontRid)
    }

    /**
     * Sets source font size used to generate MSDF textures.
     *
     * Generated from Godot docs: TextServer.font_set_msdf_size
     */
    fun fontSetMsdfSize(fontRid: RID, msdfSize: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetMsdfSizeBind, handle, fontRid, msdfSize)
    }

    /**
     * Returns source font size used to generate MSDF textures.
     *
     * Generated from Godot docs: TextServer.font_get_msdf_size
     */
    fun fontGetMsdfSize(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetMsdfSizeBind, handle, fontRid)
    }

    /**
     * Sets bitmap font fixed size. If set to value greater than zero, same cache entry will be used
     * for all font sizes.
     *
     * Generated from Godot docs: TextServer.font_set_fixed_size
     */
    fun fontSetFixedSize(fontRid: RID, fixedSize: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetFixedSizeBind, handle, fontRid, fixedSize)
    }

    /**
     * Returns bitmap font fixed size.
     *
     * Generated from Godot docs: TextServer.font_get_fixed_size
     */
    fun fontGetFixedSize(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetFixedSizeBind, handle, fontRid)
    }

    /**
     * Sets bitmap font scaling mode. This property is used only if `fixed_size` is greater than zero.
     *
     * Generated from Godot docs: TextServer.font_set_fixed_size_scale_mode
     */
    fun fontSetFixedSizeScaleMode(fontRid: RID, fixedSizeScaleMode: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetFixedSizeScaleModeBind, handle, fontRid, fixedSizeScaleMode)
    }

    /**
     * Returns bitmap font scaling mode.
     *
     * Generated from Godot docs: TextServer.font_get_fixed_size_scale_mode
     */
    fun fontGetFixedSizeScaleMode(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetFixedSizeScaleModeBind, handle, fontRid)
    }

    /**
     * If set to `true`, system fonts can be automatically used as fallbacks.
     *
     * Generated from Godot docs: TextServer.font_set_allow_system_fallback
     */
    fun fontSetAllowSystemFallback(fontRid: RID, allowSystemFallback: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetAllowSystemFallbackBind, handle, fontRid, allowSystemFallback)
    }

    /**
     * Returns `true` if system fonts can be automatically used as fallbacks.
     *
     * Generated from Godot docs: TextServer.font_is_allow_system_fallback
     */
    fun fontIsAllowSystemFallback(fontRid: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontIsAllowSystemFallbackBind, handle, fontRid)
    }

    /**
     * Frees all automatically loaded system fonts.
     *
     * Generated from Godot docs: TextServer.font_clear_system_fallback_cache
     */
    fun fontClearSystemFallbackCache() {
        ObjectCalls.ptrcallNoArgs(fontClearSystemFallbackCacheBind, handle)
    }

    /**
     * If set to `true` auto-hinting is preferred over font built-in hinting.
     *
     * Generated from Godot docs: TextServer.font_set_force_autohinter
     */
    fun fontSetForceAutohinter(fontRid: RID, forceAutohinter: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetForceAutohinterBind, handle, fontRid, forceAutohinter)
    }

    /**
     * Returns `true` if auto-hinting is supported and preferred over font built-in hinting. Used by
     * dynamic fonts only.
     *
     * Generated from Godot docs: TextServer.font_is_force_autohinter
     */
    fun fontIsForceAutohinter(fontRid: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontIsForceAutohinterBind, handle, fontRid)
    }

    /**
     * If set to `true`, color modulation is applied when drawing colored glyphs, otherwise it's
     * applied to the monochrome glyphs only.
     *
     * Generated from Godot docs: TextServer.font_set_modulate_color_glyphs
     */
    fun fontSetModulateColorGlyphs(fontRid: RID, modulate: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetModulateColorGlyphsBind, handle, fontRid, modulate)
    }

    /**
     * Returns `true` if color modulation is applied when drawing the font's colored glyphs.
     *
     * Generated from Godot docs: TextServer.font_is_modulate_color_glyphs
     */
    fun fontIsModulateColorGlyphs(fontRid: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontIsModulateColorGlyphsBind, handle, fontRid)
    }

    /**
     * Returns the number of predefined color palettes. Palette contains all colors used to render font
     * glyphs. Each palette has the same number of colors.
     *
     * Generated from Godot docs: TextServer.font_get_palette_count
     */
    fun fontGetPaletteCount(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetPaletteCountBind, handle, fontRid)
    }

    /**
     * Returns the name of the predefined color palette at `index`. Palette contains all colors used to
     * render font glyphs. Each palette has the same number of colors.
     *
     * Generated from Godot docs: TextServer.font_get_palette_name
     */
    fun fontGetPaletteName(fontRid: RID, index: Long): String {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetString(fontGetPaletteNameBind, handle, fontRid, index)
    }

    /**
     * Returns the array in the predefined color palette at `index`. Palette contains all colors used
     * to render font glyphs. Each palette has the same number of colors. Colors can be overridden
     * using `font_set_palette_custom_colors`.
     *
     * Generated from Godot docs: TextServer.font_get_palette_colors
     */
    fun fontGetPaletteColors(fontRid: RID, index: Long): List<Color> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedColorList(fontGetPaletteColorsBind, handle, fontRid, index)
    }

    /**
     * Sets array of custom colors to override predefined palette. Set to empty array to reset
     * overrides. Use `Color(0, 0, 0, 0)`, to keep predefined palette color at specific position.
     *
     * Generated from Godot docs: TextServer.font_set_palette_custom_colors
     */
    fun fontSetPaletteCustomColors(fontRid: RID, colors: List<Color>) {
        ObjectCalls.ptrcallWithRIDAndPackedColorListArgs(fontSetPaletteCustomColorsBind, handle, fontRid, colors)
    }

    /**
     * Returns array of custom colors to override predefined palette.
     *
     * Generated from Godot docs: TextServer.font_get_palette_custom_colors
     */
    fun fontGetPaletteCustomColors(fontRid: RID): List<Color> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedColorList(fontGetPaletteCustomColorsBind, handle, fontRid)
    }

    /**
     * Returns used palette index.
     *
     * Generated from Godot docs: TextServer.font_get_used_palette
     */
    fun fontGetUsedPalette(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetUsedPaletteBind, handle, fontRid)
    }

    /**
     * Sets used palette index.
     *
     * Generated from Godot docs: TextServer.font_set_used_palette
     */
    fun fontSetUsedPalette(fontRid: RID, index: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetUsedPaletteBind, handle, fontRid, index)
    }

    /**
     * Sets font hinting mode. Used by dynamic fonts only.
     *
     * Generated from Godot docs: TextServer.font_set_hinting
     */
    fun fontSetHinting(fontRid: RID, hinting: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetHintingBind, handle, fontRid, hinting)
    }

    /**
     * Returns the font hinting mode. Used by dynamic fonts only.
     *
     * Generated from Godot docs: TextServer.font_get_hinting
     */
    fun fontGetHinting(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetHintingBind, handle, fontRid)
    }

    /**
     * Sets font subpixel glyph positioning mode.
     *
     * Generated from Godot docs: TextServer.font_set_subpixel_positioning
     */
    fun fontSetSubpixelPositioning(fontRid: RID, subpixelPositioning: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fontSetSubpixelPositioningBind, handle, fontRid, subpixelPositioning)
    }

    /**
     * Returns font subpixel glyph positioning mode.
     *
     * Generated from Godot docs: TextServer.font_get_subpixel_positioning
     */
    fun fontGetSubpixelPositioning(fontRid: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(fontGetSubpixelPositioningBind, handle, fontRid)
    }

    /**
     * Sets glyph position rounding behavior. If set to `true`, when aligning glyphs to the pixel
     * boundaries rounding remainders are accumulated to ensure more uniform glyph distribution. This
     * setting has no effect if subpixel positioning is enabled.
     *
     * Generated from Godot docs: TextServer.font_set_keep_rounding_remainders
     */
    fun fontSetKeepRoundingRemainders(fontRid: RID, keepRoundingRemainders: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(fontSetKeepRoundingRemaindersBind, handle, fontRid, keepRoundingRemainders)
    }

    /**
     * Returns glyph position rounding behavior. If set to `true`, when aligning glyphs to the pixel
     * boundaries rounding remainders are accumulated to ensure more uniform glyph distribution. This
     * setting has no effect if subpixel positioning is enabled.
     *
     * Generated from Godot docs: TextServer.font_get_keep_rounding_remainders
     */
    fun fontGetKeepRoundingRemainders(fontRid: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(fontGetKeepRoundingRemaindersBind, handle, fontRid)
    }

    /**
     * Sets font embolden strength. If `strength` is not equal to zero, emboldens the font outlines.
     * Negative values reduce the outline thickness.
     *
     * Generated from Godot docs: TextServer.font_set_embolden
     */
    fun fontSetEmbolden(fontRid: RID, strength: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(fontSetEmboldenBind, handle, fontRid, strength)
    }

    /**
     * Returns font embolden strength.
     *
     * Generated from Godot docs: TextServer.font_get_embolden
     */
    fun fontGetEmbolden(fontRid: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(fontGetEmboldenBind, handle, fontRid)
    }

    /**
     * Sets the spacing for `spacing` to `value` in pixels (not relative to the font size).
     *
     * Generated from Godot docs: TextServer.font_set_spacing
     */
    fun fontSetSpacing(fontRid: RID, spacing: Long, value: Long) {
        ObjectCalls.ptrcallWithRIDAndTwoLongArgs(fontSetSpacingBind, handle, fontRid, spacing, value)
    }

    /**
     * Returns the spacing for `spacing` in pixels (not relative to the font size).
     *
     * Generated from Godot docs: TextServer.font_get_spacing
     */
    fun fontGetSpacing(fontRid: RID, spacing: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(fontGetSpacingBind, handle, fontRid, spacing)
    }

    /**
     * Sets extra baseline offset (as a fraction of font height).
     *
     * Generated from Godot docs: TextServer.font_set_baseline_offset
     */
    fun fontSetBaselineOffset(fontRid: RID, baselineOffset: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(fontSetBaselineOffsetBind, handle, fontRid, baselineOffset)
    }

    /**
     * Returns extra baseline offset (as a fraction of font height).
     *
     * Generated from Godot docs: TextServer.font_get_baseline_offset
     */
    fun fontGetBaselineOffset(fontRid: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(fontGetBaselineOffsetBind, handle, fontRid)
    }

    /**
     * Sets 2D transform, applied to the font outlines, can be used for slanting, flipping, and
     * rotating glyphs. For example, to simulate italic typeface by slanting, apply the following
     * transform `Transform2D(1.0, slant, 0.0, 1.0, 0.0, 0.0)`.
     *
     * Generated from Godot docs: TextServer.font_set_transform
     */
    fun fontSetTransform(fontRid: RID, transform: Transform2D) {
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(fontSetTransformBind, handle, fontRid, transform)
    }

    /**
     * Returns 2D transform applied to the font outlines.
     *
     * Generated from Godot docs: TextServer.font_get_transform
     */
    fun fontGetTransform(fontRid: RID): Transform2D {
        return ObjectCalls.ptrcallWithRIDArgRetTransform2D(fontGetTransformBind, handle, fontRid)
    }

    /**
     * Sets variation coordinates for the specified font cache entry. See
     * `font_supported_variation_list` for more info.
     *
     * Generated from Godot docs: TextServer.font_set_variation_coordinates
     */
    fun fontSetVariationCoordinates(fontRid: RID, variationCoordinates: Map<String, Any?>) {
        ObjectCalls.ptrcallWithRIDAndDictionaryArg(fontSetVariationCoordinatesBind, handle, fontRid, variationCoordinates)
    }

    /**
     * Returns variation coordinates for the specified font cache entry. See
     * `font_supported_variation_list` for more info.
     *
     * Generated from Godot docs: TextServer.font_get_variation_coordinates
     */
    fun fontGetVariationCoordinates(fontRid: RID): Map<String, Any?> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionary(fontGetVariationCoordinatesBind, handle, fontRid)
    }

    /**
     * If set to a positive value, overrides the oversampling factor of the viewport this font is used
     * in. See `Viewport.oversampling`. This value doesn't override the `oversampling` parameter of
     * `draw_*` methods. Used by dynamic fonts only.
     *
     * Generated from Godot docs: TextServer.font_set_oversampling
     */
    fun fontSetOversampling(fontRid: RID, oversampling: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(fontSetOversamplingBind, handle, fontRid, oversampling)
    }

    /**
     * Returns oversampling factor override. If set to a positive value, overrides the oversampling
     * factor of the viewport this font is used in. See `Viewport.oversampling`. This value doesn't
     * override the `oversampling` parameter of `draw_*` methods. Used by dynamic fonts only.
     *
     * Generated from Godot docs: TextServer.font_get_oversampling
     */
    fun fontGetOversampling(fontRid: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(fontGetOversamplingBind, handle, fontRid)
    }

    /**
     * Returns list of the font sizes in the cache. Each size is `Vector2i` with font size and outline
     * size.
     *
     * Generated from Godot docs: TextServer.font_get_size_cache_list
     */
    fun fontGetSizeCacheList(fontRid: RID): List<Vector2i> {
        return ObjectCalls.ptrcallWithRIDArgRetVector2iList(fontGetSizeCacheListBind, handle, fontRid)
    }

    /**
     * Removes all font sizes from the cache entry.
     *
     * Generated from Godot docs: TextServer.font_clear_size_cache
     */
    fun fontClearSizeCache(fontRid: RID) {
        ObjectCalls.ptrcallWithRIDArg(fontClearSizeCacheBind, handle, fontRid)
    }

    /**
     * Removes specified font size from the cache entry.
     *
     * Generated from Godot docs: TextServer.font_remove_size_cache
     */
    fun fontRemoveSizeCache(fontRid: RID, size: Vector2i) {
        ObjectCalls.ptrcallWithRIDAndVector2iArg(fontRemoveSizeCacheBind, handle, fontRid, size)
    }

    /**
     * Returns font cache information, each entry contains the following fields: `Vector2i size_px` -
     * font size in pixels, `float viewport_oversampling` - viewport oversampling factor, `int glyphs`
     * - number of rendered glyphs, `int textures` - number of used textures, `int textures_size` -
     * size of texture data in bytes.
     *
     * Generated from Godot docs: TextServer.font_get_size_cache_info
     */
    fun fontGetSizeCacheInfo(fontRid: RID): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(fontGetSizeCacheInfoBind, handle, fontRid)
    }

    /**
     * Sets the font ascent (number of pixels above the baseline).
     *
     * Generated from Godot docs: TextServer.font_set_ascent
     */
    fun fontSetAscent(fontRid: RID, size: Long, ascent: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(fontSetAscentBind, handle, fontRid, size, ascent)
    }

    /**
     * Returns the font ascent (number of pixels above the baseline).
     *
     * Generated from Godot docs: TextServer.font_get_ascent
     */
    fun fontGetAscent(fontRid: RID, size: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(fontGetAscentBind, handle, fontRid, size)
    }

    /**
     * Sets the font descent (number of pixels below the baseline).
     *
     * Generated from Godot docs: TextServer.font_set_descent
     */
    fun fontSetDescent(fontRid: RID, size: Long, descent: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(fontSetDescentBind, handle, fontRid, size, descent)
    }

    /**
     * Returns the font descent (number of pixels below the baseline).
     *
     * Generated from Godot docs: TextServer.font_get_descent
     */
    fun fontGetDescent(fontRid: RID, size: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(fontGetDescentBind, handle, fontRid, size)
    }

    /**
     * Sets pixel offset of the underline below the baseline.
     *
     * Generated from Godot docs: TextServer.font_set_underline_position
     */
    fun fontSetUnderlinePosition(fontRid: RID, size: Long, underlinePosition: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(fontSetUnderlinePositionBind, handle, fontRid, size, underlinePosition)
    }

    /**
     * Returns pixel offset of the underline below the baseline.
     *
     * Generated from Godot docs: TextServer.font_get_underline_position
     */
    fun fontGetUnderlinePosition(fontRid: RID, size: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(fontGetUnderlinePositionBind, handle, fontRid, size)
    }

    /**
     * Sets thickness of the underline in pixels.
     *
     * Generated from Godot docs: TextServer.font_set_underline_thickness
     */
    fun fontSetUnderlineThickness(fontRid: RID, size: Long, underlineThickness: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(fontSetUnderlineThicknessBind, handle, fontRid, size, underlineThickness)
    }

    /**
     * Returns thickness of the underline in pixels.
     *
     * Generated from Godot docs: TextServer.font_get_underline_thickness
     */
    fun fontGetUnderlineThickness(fontRid: RID, size: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(fontGetUnderlineThicknessBind, handle, fontRid, size)
    }

    /**
     * Sets scaling factor of the color bitmap font.
     *
     * Generated from Godot docs: TextServer.font_set_scale
     */
    fun fontSetScale(fontRid: RID, size: Long, scale: Double) {
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(fontSetScaleBind, handle, fontRid, size, scale)
    }

    /**
     * Returns scaling factor of the color bitmap font.
     *
     * Generated from Godot docs: TextServer.font_get_scale
     */
    fun fontGetScale(fontRid: RID, size: Long): Double {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(fontGetScaleBind, handle, fontRid, size)
    }

    /**
     * Returns number of textures used by font cache entry.
     *
     * Generated from Godot docs: TextServer.font_get_texture_count
     */
    fun fontGetTextureCount(fontRid: RID, size: Vector2i): Long {
        return ObjectCalls.ptrcallWithRIDAndVector2iArgRetLong(fontGetTextureCountBind, handle, fontRid, size)
    }

    /**
     * Removes all textures from font cache entry. Note: This function will not remove glyphs
     * associated with the texture, use `font_remove_glyph` to remove them manually.
     *
     * Generated from Godot docs: TextServer.font_clear_textures
     */
    fun fontClearTextures(fontRid: RID, size: Vector2i) {
        ObjectCalls.ptrcallWithRIDAndVector2iArg(fontClearTexturesBind, handle, fontRid, size)
    }

    /**
     * Removes specified texture from the cache entry. Note: This function will not remove glyphs
     * associated with the texture, remove them manually, using `font_remove_glyph`.
     *
     * Generated from Godot docs: TextServer.font_remove_texture
     */
    fun fontRemoveTexture(fontRid: RID, size: Vector2i, textureIndex: Long) {
        ObjectCalls.ptrcallWithRIDVector2iLongArgs(fontRemoveTextureBind, handle, fontRid, size, textureIndex)
    }

    /**
     * Sets font cache texture image data.
     *
     * Generated from Godot docs: TextServer.font_set_texture_image
     */
    fun fontSetTextureImage(fontRid: RID, size: Vector2i, textureIndex: Long, image: Image?) {
        ObjectCalls.ptrcallWithRIDVector2iLongObjectArgs(fontSetTextureImageBind, handle, fontRid, size, textureIndex, image?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns font cache texture image data.
     *
     * Generated from Godot docs: TextServer.font_get_texture_image
     */
    fun fontGetTextureImage(fontRid: RID, size: Vector2i, textureIndex: Long): Image? {
        return Image.wrap(ObjectCalls.ptrcallWithRIDVector2iLongArgsRetObject(fontGetTextureImageBind, handle, fontRid, size, textureIndex))
    }

    /**
     * Sets array containing glyph packing data.
     *
     * Generated from Godot docs: TextServer.font_set_texture_offsets
     */
    fun fontSetTextureOffsets(fontRid: RID, size: Vector2i, textureIndex: Long, offset: List<Int>) {
        ObjectCalls.ptrcallWithRIDVector2iLongPackedInt32ListArgs(fontSetTextureOffsetsBind, handle, fontRid, size, textureIndex, offset)
    }

    /**
     * Returns array containing glyph packing data.
     *
     * Generated from Godot docs: TextServer.font_get_texture_offsets
     */
    fun fontGetTextureOffsets(fontRid: RID, size: Vector2i, textureIndex: Long): List<Int> {
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetPackedInt32List(fontGetTextureOffsetsBind, handle, fontRid, size, textureIndex)
    }

    /**
     * Returns list of rendered glyphs in the cache entry.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_list
     */
    fun fontGetGlyphList(fontRid: RID, size: Vector2i): List<Int> {
        return ObjectCalls.ptrcallWithRIDAndVector2iArgRetPackedInt32List(fontGetGlyphListBind, handle, fontRid, size)
    }

    /**
     * Removes all rendered glyph information from the cache entry. Note: This function will not remove
     * textures associated with the glyphs, use `font_remove_texture` to remove them manually.
     *
     * Generated from Godot docs: TextServer.font_clear_glyphs
     */
    fun fontClearGlyphs(fontRid: RID, size: Vector2i) {
        ObjectCalls.ptrcallWithRIDAndVector2iArg(fontClearGlyphsBind, handle, fontRid, size)
    }

    /**
     * Removes specified rendered glyph information from the cache entry. Note: This function will not
     * remove textures associated with the glyphs, use `font_remove_texture` to remove them manually.
     *
     * Generated from Godot docs: TextServer.font_remove_glyph
     */
    fun fontRemoveGlyph(fontRid: RID, size: Vector2i, glyph: Long) {
        ObjectCalls.ptrcallWithRIDVector2iLongArgs(fontRemoveGlyphBind, handle, fontRid, size, glyph)
    }

    /**
     * Returns glyph advance (offset of the next glyph). Note: Advance for glyphs outlines is the same
     * as the base glyph advance and is not saved.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_advance
     */
    fun fontGetGlyphAdvance(fontRid: RID, size: Long, glyph: Long): Vector2 {
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetVector2(fontGetGlyphAdvanceBind, handle, fontRid, size, glyph)
    }

    /**
     * Sets glyph advance (offset of the next glyph). Note: Advance for glyphs outlines is the same as
     * the base glyph advance and is not saved.
     *
     * Generated from Godot docs: TextServer.font_set_glyph_advance
     */
    fun fontSetGlyphAdvance(fontRid: RID, size: Long, glyph: Long, advance: Vector2) {
        ObjectCalls.ptrcallWithRIDTwoLongAndVector2Args(fontSetGlyphAdvanceBind, handle, fontRid, size, glyph, advance)
    }

    /**
     * Returns glyph offset from the baseline.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_offset
     */
    fun fontGetGlyphOffset(fontRid: RID, size: Vector2i, glyph: Long): Vector2 {
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetVector2(fontGetGlyphOffsetBind, handle, fontRid, size, glyph)
    }

    /**
     * Sets glyph offset from the baseline.
     *
     * Generated from Godot docs: TextServer.font_set_glyph_offset
     */
    fun fontSetGlyphOffset(fontRid: RID, size: Vector2i, glyph: Long, offset: Vector2) {
        ObjectCalls.ptrcallWithRIDVector2iLongVector2Args(fontSetGlyphOffsetBind, handle, fontRid, size, glyph, offset)
    }

    /**
     * Returns size of the glyph.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_size
     */
    fun fontGetGlyphSize(fontRid: RID, size: Vector2i, glyph: Long): Vector2 {
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetVector2(fontGetGlyphSizeBind, handle, fontRid, size, glyph)
    }

    /**
     * Sets size of the glyph.
     *
     * Generated from Godot docs: TextServer.font_set_glyph_size
     */
    fun fontSetGlyphSize(fontRid: RID, size: Vector2i, glyph: Long, glSize: Vector2) {
        ObjectCalls.ptrcallWithRIDVector2iLongVector2Args(fontSetGlyphSizeBind, handle, fontRid, size, glyph, glSize)
    }

    /**
     * Returns rectangle in the cache texture containing the glyph.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_uv_rect
     */
    fun fontGetGlyphUvRect(fontRid: RID, size: Vector2i, glyph: Long): Rect2 {
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetRect2(fontGetGlyphUvRectBind, handle, fontRid, size, glyph)
    }

    /**
     * Sets rectangle in the cache texture containing the glyph.
     *
     * Generated from Godot docs: TextServer.font_set_glyph_uv_rect
     */
    fun fontSetGlyphUvRect(fontRid: RID, size: Vector2i, glyph: Long, uvRect: Rect2) {
        ObjectCalls.ptrcallWithRIDVector2iLongRect2Args(fontSetGlyphUvRectBind, handle, fontRid, size, glyph, uvRect)
    }

    /**
     * Returns index of the cache texture containing the glyph.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_texture_idx
     */
    fun fontGetGlyphTextureIdx(fontRid: RID, size: Vector2i, glyph: Long): Long {
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetLong(fontGetGlyphTextureIdxBind, handle, fontRid, size, glyph)
    }

    /**
     * Sets index of the cache texture containing the glyph.
     *
     * Generated from Godot docs: TextServer.font_set_glyph_texture_idx
     */
    fun fontSetGlyphTextureIdx(fontRid: RID, size: Vector2i, glyph: Long, textureIdx: Long) {
        ObjectCalls.ptrcallWithRIDVector2iTwoLongArgs(fontSetGlyphTextureIdxBind, handle, fontRid, size, glyph, textureIdx)
    }

    /**
     * Returns resource ID of the cache texture containing the glyph. Note: If there are pending glyphs
     * to render, calling this function might trigger the texture cache update.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_texture_rid
     */
    fun fontGetGlyphTextureRid(fontRid: RID, size: Vector2i, glyph: Long): RID {
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetRID(fontGetGlyphTextureRidBind, handle, fontRid, size, glyph)
    }

    /**
     * Returns size of the cache texture containing the glyph. Note: If there are pending glyphs to
     * render, calling this function might trigger the texture cache update.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_texture_size
     */
    fun fontGetGlyphTextureSize(fontRid: RID, size: Vector2i, glyph: Long): Vector2 {
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetVector2(fontGetGlyphTextureSizeBind, handle, fontRid, size, glyph)
    }

    /**
     * Returns outline contours of the glyph as a `Dictionary` with the following contents: `points` -
     * `PackedVector3Array`, containing outline points. `x` and `y` are point coordinates. `z` is the
     * type of the point, using the `ContourPointTag` values. `contours` - `PackedInt32Array`,
     * containing indices the end points of each contour. `orientation` - `bool`, contour orientation.
     * If `true`, clockwise contours must be filled. - Two successive `CONTOUR_CURVE_TAG_ON` points
     * indicate a line segment. - One `CONTOUR_CURVE_TAG_OFF_CONIC` point between two
     * `CONTOUR_CURVE_TAG_ON` points indicates a single conic (quadratic) Bézier arc. - Two
     * `CONTOUR_CURVE_TAG_OFF_CUBIC` points between two `CONTOUR_CURVE_TAG_ON` points indicate a single
     * cubic Bézier arc. - Two successive `CONTOUR_CURVE_TAG_OFF_CONIC` points indicate two successive
     * conic (quadratic) Bézier arcs with a virtual `CONTOUR_CURVE_TAG_ON` point at their middle. -
     * Each contour is closed. The last point of a contour uses the first point of a contour as its
     * next point, and vice versa. The first point can be `CONTOUR_CURVE_TAG_OFF_CONIC` point.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_contours
     */
    fun fontGetGlyphContours(font: RID, size: Long, index: Long): Map<String, Any?> {
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetDictionary(fontGetGlyphContoursBind, handle, font, size, index)
    }

    /**
     * Returns list of the kerning overrides.
     *
     * Generated from Godot docs: TextServer.font_get_kerning_list
     */
    fun fontGetKerningList(fontRid: RID, size: Long): List<Vector2i> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVector2iList(fontGetKerningListBind, handle, fontRid, size)
    }

    /**
     * Removes all kerning overrides.
     *
     * Generated from Godot docs: TextServer.font_clear_kerning_map
     */
    fun fontClearKerningMap(fontRid: RID, size: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(fontClearKerningMapBind, handle, fontRid, size)
    }

    /**
     * Removes kerning override for the pair of glyphs.
     *
     * Generated from Godot docs: TextServer.font_remove_kerning
     */
    fun fontRemoveKerning(fontRid: RID, size: Long, glyphPair: Vector2i) {
        ObjectCalls.ptrcallWithRIDLongVector2iArgs(fontRemoveKerningBind, handle, fontRid, size, glyphPair)
    }

    /**
     * Sets kerning for the pair of glyphs.
     *
     * Generated from Godot docs: TextServer.font_set_kerning
     */
    fun fontSetKerning(fontRid: RID, size: Long, glyphPair: Vector2i, kerning: Vector2) {
        ObjectCalls.ptrcallWithRIDLongVector2iAndVector2Args(fontSetKerningBind, handle, fontRid, size, glyphPair, kerning)
    }

    /**
     * Returns kerning for the pair of glyphs.
     *
     * Generated from Godot docs: TextServer.font_get_kerning
     */
    fun fontGetKerning(fontRid: RID, size: Long, glyphPair: Vector2i): Vector2 {
        return ObjectCalls.ptrcallWithRIDLongVector2iArgsRetVector2(fontGetKerningBind, handle, fontRid, size, glyphPair)
    }

    /**
     * Returns the glyph index of a `char`, optionally modified by the `variation_selector`. See
     * `font_get_char_from_glyph_index`.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_index
     */
    fun fontGetGlyphIndex(fontRid: RID, size: Long, char: Long, variationSelector: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndThreeLongArgsRetLong(fontGetGlyphIndexBind, handle, fontRid, size, char, variationSelector)
    }

    /**
     * Returns character code associated with `glyph_index`, or `0` if `glyph_index` is invalid. See
     * `font_get_glyph_index`.
     *
     * Generated from Godot docs: TextServer.font_get_char_from_glyph_index
     */
    fun fontGetCharFromGlyphIndex(fontRid: RID, size: Long, glyphIndex: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetLong(fontGetCharFromGlyphIndexBind, handle, fontRid, size, glyphIndex)
    }

    /**
     * Returns `true` if a Unicode `char` is available in the font.
     *
     * Generated from Godot docs: TextServer.font_has_char
     */
    fun fontHasChar(fontRid: RID, char: Long): Boolean {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetBool(fontHasCharBind, handle, fontRid, char)
    }

    /**
     * Returns a string containing all the characters available in the font.
     *
     * Generated from Godot docs: TextServer.font_get_supported_chars
     */
    fun fontGetSupportedChars(fontRid: RID): String {
        return ObjectCalls.ptrcallWithRIDArgRetString(fontGetSupportedCharsBind, handle, fontRid)
    }

    /**
     * Returns an array containing all glyph indices in the font.
     *
     * Generated from Godot docs: TextServer.font_get_supported_glyphs
     */
    fun fontGetSupportedGlyphs(fontRid: RID): List<Int> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(fontGetSupportedGlyphsBind, handle, fontRid)
    }

    /**
     * Renders the range of characters to the font cache texture.
     *
     * Generated from Godot docs: TextServer.font_render_range
     */
    fun fontRenderRange(fontRid: RID, size: Vector2i, start: Long, end: Long) {
        ObjectCalls.ptrcallWithRIDVector2iTwoLongArgs(fontRenderRangeBind, handle, fontRid, size, start, end)
    }

    /**
     * Renders specified glyph to the font cache texture.
     *
     * Generated from Godot docs: TextServer.font_render_glyph
     */
    fun fontRenderGlyph(fontRid: RID, size: Vector2i, index: Long) {
        ObjectCalls.ptrcallWithRIDVector2iLongArgs(fontRenderGlyphBind, handle, fontRid, size, index)
    }

    /**
     * Draws single glyph into a canvas item at the position, using `font_rid` at the size `size`. If
     * `oversampling` is greater than zero, it is used as font oversampling factor, otherwise viewport
     * oversampling settings are used. Note: Glyph index is specific to the font, use glyphs indices
     * returned by `shaped_text_get_glyphs` or `font_get_glyph_index`. Note: If there are pending
     * glyphs to render, calling this function might trigger the texture cache update.
     *
     * Generated from Godot docs: TextServer.font_draw_glyph
     */
    fun fontDrawGlyph(fontRid: RID, canvas: RID, size: Long, pos: Vector2, index: Long, color: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithTwoRIDLongVector2LongColorDoubleArgs(fontDrawGlyphBind, handle, fontRid, canvas, size, pos, index, color, oversampling)
    }

    /**
     * Draws single glyph outline of size `outline_size` into a canvas item at the position, using
     * `font_rid` at the size `size`. If `oversampling` is greater than zero, it is used as font
     * oversampling factor, otherwise viewport oversampling settings are used. Note: Glyph index is
     * specific to the font, use glyphs indices returned by `shaped_text_get_glyphs` or
     * `font_get_glyph_index`. Note: If there are pending glyphs to render, calling this function might
     * trigger the texture cache update.
     *
     * Generated from Godot docs: TextServer.font_draw_glyph_outline
     */
    fun fontDrawGlyphOutline(fontRid: RID, canvas: RID, size: Long, outlineSize: Long, pos: Vector2, index: Long, color: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithTwoRIDTwoLongVector2LongColorDoubleArgs(fontDrawGlyphOutlineBind, handle, fontRid, canvas, size, outlineSize, pos, index, color, oversampling)
    }

    /**
     * Returns `true` if the font supports the given language (as a ISO 639
     * (https://en.wikipedia.org/wiki/ISO_639-1) code).
     *
     * Generated from Godot docs: TextServer.font_is_language_supported
     */
    fun fontIsLanguageSupported(fontRid: RID, language: String): Boolean {
        return ObjectCalls.ptrcallWithRIDAndStringArgRetBool(fontIsLanguageSupportedBind, handle, fontRid, language)
    }

    /**
     * Adds override for `font_is_language_supported`.
     *
     * Generated from Godot docs: TextServer.font_set_language_support_override
     */
    fun fontSetLanguageSupportOverride(fontRid: RID, language: String, supported: Boolean) {
        ObjectCalls.ptrcallWithRIDStringAndBoolArgs(fontSetLanguageSupportOverrideBind, handle, fontRid, language, supported)
    }

    /**
     * Returns `true` if support override is enabled for the `language`.
     *
     * Generated from Godot docs: TextServer.font_get_language_support_override
     */
    fun fontGetLanguageSupportOverride(fontRid: RID, language: String): Boolean {
        return ObjectCalls.ptrcallWithRIDAndStringArgRetBool(fontGetLanguageSupportOverrideBind, handle, fontRid, language)
    }

    /**
     * Remove language support override.
     *
     * Generated from Godot docs: TextServer.font_remove_language_support_override
     */
    fun fontRemoveLanguageSupportOverride(fontRid: RID, language: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(fontRemoveLanguageSupportOverrideBind, handle, fontRid, language)
    }

    /**
     * Returns list of language support overrides.
     *
     * Generated from Godot docs: TextServer.font_get_language_support_overrides
     */
    fun fontGetLanguageSupportOverrides(fontRid: RID): List<String> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedStringList(fontGetLanguageSupportOverridesBind, handle, fontRid)
    }

    /**
     * Returns `true` if the font supports the given script (as a ISO 15924
     * (https://en.wikipedia.org/wiki/ISO_15924) code).
     *
     * Generated from Godot docs: TextServer.font_is_script_supported
     */
    fun fontIsScriptSupported(fontRid: RID, script: String): Boolean {
        return ObjectCalls.ptrcallWithRIDAndStringArgRetBool(fontIsScriptSupportedBind, handle, fontRid, script)
    }

    /**
     * Adds override for `font_is_script_supported`.
     *
     * Generated from Godot docs: TextServer.font_set_script_support_override
     */
    fun fontSetScriptSupportOverride(fontRid: RID, script: String, supported: Boolean) {
        ObjectCalls.ptrcallWithRIDStringAndBoolArgs(fontSetScriptSupportOverrideBind, handle, fontRid, script, supported)
    }

    /**
     * Returns `true` if support override is enabled for the `script`.
     *
     * Generated from Godot docs: TextServer.font_get_script_support_override
     */
    fun fontGetScriptSupportOverride(fontRid: RID, script: String): Boolean {
        return ObjectCalls.ptrcallWithRIDAndStringArgRetBool(fontGetScriptSupportOverrideBind, handle, fontRid, script)
    }

    /**
     * Removes script support override.
     *
     * Generated from Godot docs: TextServer.font_remove_script_support_override
     */
    fun fontRemoveScriptSupportOverride(fontRid: RID, script: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(fontRemoveScriptSupportOverrideBind, handle, fontRid, script)
    }

    /**
     * Returns list of script support overrides.
     *
     * Generated from Godot docs: TextServer.font_get_script_support_overrides
     */
    fun fontGetScriptSupportOverrides(fontRid: RID): List<String> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedStringList(fontGetScriptSupportOverridesBind, handle, fontRid)
    }

    /**
     * Sets font OpenType feature set override.
     *
     * Generated from Godot docs: TextServer.font_set_opentype_feature_overrides
     */
    fun fontSetOpentypeFeatureOverrides(fontRid: RID, overrides: Map<String, Any?>) {
        ObjectCalls.ptrcallWithRIDAndDictionaryArg(fontSetOpentypeFeatureOverridesBind, handle, fontRid, overrides)
    }

    /**
     * Returns font OpenType feature set override.
     *
     * Generated from Godot docs: TextServer.font_get_opentype_feature_overrides
     */
    fun fontGetOpentypeFeatureOverrides(fontRid: RID): Map<String, Any?> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionary(fontGetOpentypeFeatureOverridesBind, handle, fontRid)
    }

    /**
     * Returns the dictionary of the supported OpenType features.
     *
     * Generated from Godot docs: TextServer.font_supported_feature_list
     */
    fun fontSupportedFeatureList(fontRid: RID): Map<String, Any?> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionary(fontSupportedFeatureListBind, handle, fontRid)
    }

    /**
     * Returns the dictionary of the supported OpenType variation coordinates.
     *
     * Generated from Godot docs: TextServer.font_supported_variation_list
     */
    fun fontSupportedVariationList(fontRid: RID): Map<String, Any?> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionary(fontSupportedVariationListBind, handle, fontRid)
    }

    /**
     * This method does nothing and always returns `1.0`.
     *
     * Generated from Godot docs: TextServer.font_get_global_oversampling
     */
    fun fontGetGlobalOversampling(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(fontGetGlobalOversamplingBind, handle)
    }

    /**
     * This method does nothing.
     *
     * Generated from Godot docs: TextServer.font_set_global_oversampling
     */
    fun fontSetGlobalOversampling(oversampling: Double) {
        ObjectCalls.ptrcallWithDoubleArg(fontSetGlobalOversamplingBind, handle, oversampling)
    }

    /**
     * Returns size of the replacement character (box with character hexadecimal code that is drawn in
     * place of invalid characters).
     *
     * Generated from Godot docs: TextServer.get_hex_code_box_size
     */
    fun getHexCodeBoxSize(size: Long, index: Long): Vector2 {
        return ObjectCalls.ptrcallWithTwoLongArgsRetVector2(getHexCodeBoxSizeBind, handle, size, index)
    }

    /**
     * Draws box displaying character hexadecimal code. Used for replacing missing characters.
     *
     * Generated from Godot docs: TextServer.draw_hex_code_box
     */
    fun drawHexCodeBox(canvas: RID, size: Long, pos: Vector2, index: Long, color: Color) {
        ObjectCalls.ptrcallWithRIDLongVector2LongColorArgs(drawHexCodeBoxBind, handle, canvas, size, pos, index, color)
    }

    /**
     * Creates a new buffer for complex text layout, with the given `direction` and `orientation`. To
     * free the resulting buffer, use `free_rid` method. Note: Direction is ignored if server does not
     * support `FEATURE_BIDI_LAYOUT` feature (supported by `TextServerAdvanced`). Note: Orientation is
     * ignored if server does not support `FEATURE_VERTICAL_LAYOUT` feature (supported by
     * `TextServerAdvanced`).
     *
     * Generated from Godot docs: TextServer.create_shaped_text
     */
    fun createShapedText(direction: Long = 0L, orientation: Long = 0L): RID {
        return ObjectCalls.ptrcallWithTwoLongArgsRetRID(createShapedTextBind, handle, direction, orientation)
    }

    /**
     * Clears text buffer (removes text and inline objects).
     *
     * Generated from Godot docs: TextServer.shaped_text_clear
     */
    fun shapedTextClear(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(shapedTextClearBind, handle, rid)
    }

    /**
     * Duplicates shaped text buffer.
     *
     * Generated from Godot docs: TextServer.shaped_text_duplicate
     */
    fun shapedTextDuplicate(rid: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(shapedTextDuplicateBind, handle, rid)
    }

    /**
     * Sets desired text direction. If set to `DIRECTION_AUTO`, direction will be detected based on the
     * buffer contents and current locale. Note: Direction is ignored if server does not support
     * `FEATURE_BIDI_LAYOUT` feature (supported by `TextServerAdvanced`).
     *
     * Generated from Godot docs: TextServer.shaped_text_set_direction
     */
    fun shapedTextSetDirection(shaped: RID, direction: Long = 0L) {
        ObjectCalls.ptrcallWithRIDAndLongArg(shapedTextSetDirectionBind, handle, shaped, direction)
    }

    /**
     * Returns direction of the text.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_direction
     */
    fun shapedTextGetDirection(shaped: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetDirectionBind, handle, shaped)
    }

    /**
     * Returns direction of the text, inferred by the BiDi algorithm.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_inferred_direction
     */
    fun shapedTextGetInferredDirection(shaped: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetInferredDirectionBind, handle, shaped)
    }

    /**
     * Overrides BiDi for the structured text. Override ranges should cover full source text without
     * overlaps. BiDi algorithm will be used on each range separately.
     *
     * Generated from Godot docs: TextServer.shaped_text_set_bidi_override
     */
    fun shapedTextSetBidiOverride(shaped: RID, override: List<Any?>) {
        ObjectCalls.ptrcallWithRIDAndArrayArg(shapedTextSetBidiOverrideBind, handle, shaped, override)
    }

    /**
     * Sets custom punctuation character list, used for word breaking. If set to empty string, server
     * defaults are used.
     *
     * Generated from Godot docs: TextServer.shaped_text_set_custom_punctuation
     */
    fun shapedTextSetCustomPunctuation(shaped: RID, punct: String) {
        ObjectCalls.ptrcallWithRIDAndStringArg(shapedTextSetCustomPunctuationBind, handle, shaped, punct)
    }

    /**
     * Returns custom punctuation character list, used for word breaking. If set to empty string,
     * server defaults are used.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_custom_punctuation
     */
    fun shapedTextGetCustomPunctuation(shaped: RID): String {
        return ObjectCalls.ptrcallWithRIDArgRetString(shapedTextGetCustomPunctuationBind, handle, shaped)
    }

    /**
     * Sets ellipsis character used for text clipping.
     *
     * Generated from Godot docs: TextServer.shaped_text_set_custom_ellipsis
     */
    fun shapedTextSetCustomEllipsis(shaped: RID, char: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(shapedTextSetCustomEllipsisBind, handle, shaped, char)
    }

    /**
     * Returns ellipsis character used for text clipping.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_custom_ellipsis
     */
    fun shapedTextGetCustomEllipsis(shaped: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetCustomEllipsisBind, handle, shaped)
    }

    /**
     * Sets desired text orientation. Note: Orientation is ignored if server does not support
     * `FEATURE_VERTICAL_LAYOUT` feature (supported by `TextServerAdvanced`).
     *
     * Generated from Godot docs: TextServer.shaped_text_set_orientation
     */
    fun shapedTextSetOrientation(shaped: RID, orientation: Long = 0L) {
        ObjectCalls.ptrcallWithRIDAndLongArg(shapedTextSetOrientationBind, handle, shaped, orientation)
    }

    /**
     * Returns text orientation.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_orientation
     */
    fun shapedTextGetOrientation(shaped: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetOrientationBind, handle, shaped)
    }

    /**
     * If set to `true` text buffer will display invalid characters as hexadecimal codes, otherwise
     * nothing is displayed.
     *
     * Generated from Godot docs: TextServer.shaped_text_set_preserve_invalid
     */
    fun shapedTextSetPreserveInvalid(shaped: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(shapedTextSetPreserveInvalidBind, handle, shaped, enabled)
    }

    /**
     * Returns `true` if text buffer is configured to display hexadecimal codes in place of invalid
     * characters. Note: If set to `false`, nothing is displayed in place of invalid characters.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_preserve_invalid
     */
    fun shapedTextGetPreserveInvalid(shaped: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(shapedTextGetPreserveInvalidBind, handle, shaped)
    }

    /**
     * If set to `true` text buffer will display control characters.
     *
     * Generated from Godot docs: TextServer.shaped_text_set_preserve_control
     */
    fun shapedTextSetPreserveControl(shaped: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(shapedTextSetPreserveControlBind, handle, shaped, enabled)
    }

    /**
     * Returns `true` if text buffer is configured to display control characters.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_preserve_control
     */
    fun shapedTextGetPreserveControl(shaped: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(shapedTextGetPreserveControlBind, handle, shaped)
    }

    /**
     * Sets extra spacing added between glyphs or lines in pixels.
     *
     * Generated from Godot docs: TextServer.shaped_text_set_spacing
     */
    fun shapedTextSetSpacing(shaped: RID, spacing: Long, value: Long) {
        ObjectCalls.ptrcallWithRIDAndTwoLongArgs(shapedTextSetSpacingBind, handle, shaped, spacing, value)
    }

    /**
     * Returns extra spacing added between glyphs or lines in pixels.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_spacing
     */
    fun shapedTextGetSpacing(shaped: RID, spacing: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedTextGetSpacingBind, handle, shaped, spacing)
    }

    /**
     * Adds text span and font to draw it to the text buffer.
     *
     * Generated from Godot docs: TextServer.shaped_text_add_string
     */
    fun shapedTextAddString(shaped: RID, text: String, fonts: List<RID>, size: Long, opentypeFeatures: Map<String, Any?> = emptyMap(), language: String = "", meta: Any? = null): Boolean {
        return ObjectCalls.ptrcallWithRIDStringRIDListLongDictionaryStringVariantArgsRetBool(shapedTextAddStringBind, handle, shaped, text, fonts, size, opentypeFeatures, language, meta)
    }

    /**
     * Adds inline object to the text buffer, `key` must be unique. In the text, object is represented
     * as `length` object replacement characters.
     *
     * Generated from Godot docs: TextServer.shaped_text_add_object
     */
    fun shapedTextAddObject(shaped: RID, key: Any?, size: Vector2, inlineAlign: Long = 5L, length: Long = 1L, baseline: Double = 0.0): Boolean {
        return ObjectCalls.ptrcallWithRIDVariantVector2LongLongDoubleArgsRetBool(shapedTextAddObjectBind, handle, shaped, key, size, inlineAlign, length, baseline)
    }

    /**
     * Sets new size and alignment of embedded object.
     *
     * Generated from Godot docs: TextServer.shaped_text_resize_object
     */
    fun shapedTextResizeObject(shaped: RID, key: Any?, size: Vector2, inlineAlign: Long = 5L, baseline: Double = 0.0): Boolean {
        return ObjectCalls.ptrcallWithRIDVariantVector2LongDoubleArgsRetBool(shapedTextResizeObjectBind, handle, shaped, key, size, inlineAlign, baseline)
    }

    /**
     * Returns `true` if an object with `key` is embedded in this shaped text buffer.
     *
     * Generated from Godot docs: TextServer.shaped_text_has_object
     */
    fun shapedTextHasObject(shaped: RID, key: Any?): Boolean {
        return ObjectCalls.ptrcallWithRIDAndVariantArgRetBool(shapedTextHasObjectBind, handle, shaped, key)
    }

    /**
     * Returns the text buffer source text, including object replacement characters.
     *
     * Generated from Godot docs: TextServer.shaped_get_text
     */
    fun shapedGetText(shaped: RID): String {
        return ObjectCalls.ptrcallWithRIDArgRetString(shapedGetTextBind, handle, shaped)
    }

    /**
     * Returns number of text spans added using `shaped_text_add_string` or `shaped_text_add_object`.
     *
     * Generated from Godot docs: TextServer.shaped_get_span_count
     */
    fun shapedGetSpanCount(shaped: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedGetSpanCountBind, handle, shaped)
    }

    /**
     * Returns text span metadata.
     *
     * Generated from Godot docs: TextServer.shaped_get_span_meta
     */
    fun shapedGetSpanMeta(shaped: RID, index: Long): Any? {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(shapedGetSpanMetaBind, handle, shaped, index)
    }

    /**
     * Returns text embedded object key.
     *
     * Generated from Godot docs: TextServer.shaped_get_span_embedded_object
     */
    fun shapedGetSpanEmbeddedObject(shaped: RID, index: Long): Any? {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(shapedGetSpanEmbeddedObjectBind, handle, shaped, index)
    }

    /**
     * Returns the text span source text.
     *
     * Generated from Godot docs: TextServer.shaped_get_span_text
     */
    fun shapedGetSpanText(shaped: RID, index: Long): String {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetString(shapedGetSpanTextBind, handle, shaped, index)
    }

    /**
     * Returns the text span embedded object key.
     *
     * Generated from Godot docs: TextServer.shaped_get_span_object
     */
    fun shapedGetSpanObject(shaped: RID, index: Long): Any? {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(shapedGetSpanObjectBind, handle, shaped, index)
    }

    /**
     * Changes text span font, font size, and OpenType features, without changing the text.
     *
     * Generated from Godot docs: TextServer.shaped_set_span_update_font
     */
    fun shapedSetSpanUpdateFont(shaped: RID, index: Long, fonts: List<RID>, size: Long, opentypeFeatures: Map<String, Any?> = emptyMap()) {
        ObjectCalls.ptrcallWithRIDLongRIDListLongDictionaryArgs(shapedSetSpanUpdateFontBind, handle, shaped, index, fonts, size, opentypeFeatures)
    }

    /**
     * Returns the number of uniform text runs in the buffer.
     *
     * Generated from Godot docs: TextServer.shaped_get_run_count
     */
    fun shapedGetRunCount(shaped: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedGetRunCountBind, handle, shaped)
    }

    /**
     * Returns the source text of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_text
     */
    fun shapedGetRunText(shaped: RID, index: Long): String {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetString(shapedGetRunTextBind, handle, shaped, index)
    }

    /**
     * Returns the source text range of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_range
     */
    fun shapedGetRunRange(shaped: RID, index: Long): Vector2i {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVector2i(shapedGetRunRangeBind, handle, shaped, index)
    }

    /**
     * Returns the glyph range of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_glyph_range
     */
    fun shapedGetRunGlyphRange(shaped: RID, index: Long): Vector2i {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVector2i(shapedGetRunGlyphRangeBind, handle, shaped, index)
    }

    /**
     * Returns the font RID of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_font_rid
     */
    fun shapedGetRunFontRid(shaped: RID, index: Long): RID {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetRID(shapedGetRunFontRidBind, handle, shaped, index)
    }

    /**
     * Returns the font size of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_font_size
     */
    fun shapedGetRunFontSize(shaped: RID, index: Long): Int {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetInt(shapedGetRunFontSizeBind, handle, shaped, index)
    }

    /**
     * Returns the language of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_language
     */
    fun shapedGetRunLanguage(shaped: RID, index: Long): String {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetString(shapedGetRunLanguageBind, handle, shaped, index)
    }

    /**
     * Returns the direction of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_direction
     */
    fun shapedGetRunDirection(shaped: RID, index: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedGetRunDirectionBind, handle, shaped, index)
    }

    /**
     * Returns the embedded object of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_object
     */
    fun shapedGetRunObject(shaped: RID, index: Long): Any? {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(shapedGetRunObjectBind, handle, shaped, index)
    }

    /**
     * Returns text buffer for the substring of the text in the `shaped` text buffer (including inline
     * objects).
     *
     * Generated from Godot docs: TextServer.shaped_text_substr
     */
    fun shapedTextSubstr(shaped: RID, start: Long, length: Long): RID {
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetRID(shapedTextSubstrBind, handle, shaped, start, length)
    }

    /**
     * Returns the parent buffer from which the substring originates.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_parent
     */
    fun shapedTextGetParent(shaped: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(shapedTextGetParentBind, handle, shaped)
    }

    /**
     * Adjusts text width to fit to specified width, returns new text width.
     *
     * Generated from Godot docs: TextServer.shaped_text_fit_to_width
     */
    fun shapedTextFitToWidth(shaped: RID, width: Double, justificationFlags: Long = 3L): Double {
        return ObjectCalls.ptrcallWithRIDDoubleAndLongArgsRetDouble(shapedTextFitToWidthBind, handle, shaped, width, justificationFlags)
    }

    /**
     * Aligns shaped text to the given tab-stops.
     *
     * Generated from Godot docs: TextServer.shaped_text_tab_align
     */
    fun shapedTextTabAlign(shaped: RID, tabStops: List<Float>): Double {
        return ObjectCalls.ptrcallWithRIDAndPackedFloat32ListArgRetDouble(shapedTextTabAlignBind, handle, shaped, tabStops)
    }

    /**
     * Shapes buffer if it's not shaped. Returns `true` if the string is shaped successfully. Note: It
     * is not necessary to call this function manually, buffer will be shaped automatically as soon as
     * any of its output data is requested.
     *
     * Generated from Godot docs: TextServer.shaped_text_shape
     */
    fun shapedTextShape(shaped: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(shapedTextShapeBind, handle, shaped)
    }

    /**
     * Returns `true` if buffer is successfully shaped.
     *
     * Generated from Godot docs: TextServer.shaped_text_is_ready
     */
    fun shapedTextIsReady(shaped: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(shapedTextIsReadyBind, handle, shaped)
    }

    /**
     * Returns `true` if text buffer contains any visible characters.
     *
     * Generated from Godot docs: TextServer.shaped_text_has_visible_chars
     */
    fun shapedTextHasVisibleChars(shaped: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(shapedTextHasVisibleCharsBind, handle, shaped)
    }

    /**
     * Returns an array of glyphs in the visual order.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_glyphs
     */
    fun shapedTextGetGlyphs(shaped: RID): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(shapedTextGetGlyphsBind, handle, shaped)
    }

    /**
     * Returns text glyphs in the logical order.
     *
     * Generated from Godot docs: TextServer.shaped_text_sort_logical
     */
    fun shapedTextSortLogical(shaped: RID): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(shapedTextSortLogicalBind, handle, shaped)
    }

    /**
     * Returns number of glyphs in the buffer.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_glyph_count
     */
    fun shapedTextGetGlyphCount(shaped: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetGlyphCountBind, handle, shaped)
    }

    /**
     * Returns substring buffer character range in the parent buffer.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_range
     */
    fun shapedTextGetRange(shaped: RID): Vector2i {
        return ObjectCalls.ptrcallWithRIDArgRetVector2i(shapedTextGetRangeBind, handle, shaped)
    }

    /**
     * Breaks text to the lines and columns. Returns character ranges for each segment.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_line_breaks_adv
     */
    fun shapedTextGetLineBreaksAdv(shaped: RID, width: List<Float>, start: Long = 0L, once: Boolean = true, breakFlags: Long = 3L): List<Int> {
        return ObjectCalls.ptrcallWithRIDPackedFloat32ListLongBoolLongArgsRetPackedInt32List(shapedTextGetLineBreaksAdvBind, handle, shaped, width, start, once, breakFlags)
    }

    /**
     * Breaks text to the lines and returns character ranges for each line.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_line_breaks
     */
    fun shapedTextGetLineBreaks(shaped: RID, width: Double, start: Long = 0L, breakFlags: Long = 3L): List<Int> {
        return ObjectCalls.ptrcallWithRIDDoubleTwoLongArgsRetPackedInt32List(shapedTextGetLineBreaksBind, handle, shaped, width, start, breakFlags)
    }

    /**
     * Breaks text into words and returns array of character ranges. Use `grapheme_flags` to set what
     * characters are used for breaking.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_word_breaks
     */
    fun shapedTextGetWordBreaks(shaped: RID, graphemeFlags: Long = 264L, skipGraphemeFlags: Long = 4L): List<Int> {
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetPackedInt32List(shapedTextGetWordBreaksBind, handle, shaped, graphemeFlags, skipGraphemeFlags)
    }

    /**
     * Returns the position of the overrun trim.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_trim_pos
     */
    fun shapedTextGetTrimPos(shaped: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetTrimPosBind, handle, shaped)
    }

    /**
     * Returns position of the ellipsis.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_ellipsis_pos
     */
    fun shapedTextGetEllipsisPos(shaped: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetEllipsisPosBind, handle, shaped)
    }

    /**
     * Returns array of the glyphs in the ellipsis.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_ellipsis_glyphs
     */
    fun shapedTextGetEllipsisGlyphs(shaped: RID): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(shapedTextGetEllipsisGlyphsBind, handle, shaped)
    }

    /**
     * Returns number of glyphs in the ellipsis.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_ellipsis_glyph_count
     */
    fun shapedTextGetEllipsisGlyphCount(shaped: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(shapedTextGetEllipsisGlyphCountBind, handle, shaped)
    }

    /**
     * Trims text if it exceeds the given width.
     *
     * Generated from Godot docs: TextServer.shaped_text_overrun_trim_to_width
     */
    fun shapedTextOverrunTrimToWidth(shaped: RID, width: Double = 0.0, overrunTrimFlags: Long = 0L) {
        ObjectCalls.ptrcallWithRIDDoubleAndLongArgs(shapedTextOverrunTrimToWidthBind, handle, shaped, width, overrunTrimFlags)
    }

    /**
     * Returns array of inline objects.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_objects
     */
    fun shapedTextGetObjects(shaped: RID): List<Any?> {
        return ObjectCalls.ptrcallWithRIDArgRetArray(shapedTextGetObjectsBind, handle, shaped)
    }

    /**
     * Returns bounding rectangle of the inline object.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_object_rect
     */
    fun shapedTextGetObjectRect(shaped: RID, key: Any?): Rect2 {
        return ObjectCalls.ptrcallWithRIDAndVariantArgRetRect2(shapedTextGetObjectRectBind, handle, shaped, key)
    }

    /**
     * Returns the character range of the inline object.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_object_range
     */
    fun shapedTextGetObjectRange(shaped: RID, key: Any?): Vector2i {
        return ObjectCalls.ptrcallWithRIDAndVariantArgRetVector2i(shapedTextGetObjectRangeBind, handle, shaped, key)
    }

    /**
     * Returns the glyph index of the inline object.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_object_glyph
     */
    fun shapedTextGetObjectGlyph(shaped: RID, key: Any?): Long {
        return ObjectCalls.ptrcallWithRIDAndVariantArgRetLong(shapedTextGetObjectGlyphBind, handle, shaped, key)
    }

    /**
     * Returns size of the text.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_size
     */
    fun shapedTextGetSize(shaped: RID): Vector2 {
        return ObjectCalls.ptrcallWithRIDArgRetVector2(shapedTextGetSizeBind, handle, shaped)
    }

    /**
     * Returns the text ascent (number of pixels above the baseline for horizontal layout or to the
     * left of baseline for vertical). Note: Overall ascent can be higher than font ascent, if some
     * glyphs are displaced from the baseline.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_ascent
     */
    fun shapedTextGetAscent(shaped: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(shapedTextGetAscentBind, handle, shaped)
    }

    /**
     * Returns the text descent (number of pixels below the baseline for horizontal layout or to the
     * right of baseline for vertical). Note: Overall descent can be higher than font descent, if some
     * glyphs are displaced from the baseline.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_descent
     */
    fun shapedTextGetDescent(shaped: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(shapedTextGetDescentBind, handle, shaped)
    }

    /**
     * Returns width (for horizontal layout) or height (for vertical) of the text.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_width
     */
    fun shapedTextGetWidth(shaped: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(shapedTextGetWidthBind, handle, shaped)
    }

    /**
     * Returns pixel offset of the underline below the baseline.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_underline_position
     */
    fun shapedTextGetUnderlinePosition(shaped: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(shapedTextGetUnderlinePositionBind, handle, shaped)
    }

    /**
     * Returns thickness of the underline.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_underline_thickness
     */
    fun shapedTextGetUnderlineThickness(shaped: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(shapedTextGetUnderlineThicknessBind, handle, shaped)
    }

    /**
     * Returns shapes of the carets corresponding to the character offset `position` in the text.
     * Returned caret shape is 1 pixel wide rectangle.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_carets
     */
    fun shapedTextGetCarets(shaped: RID, position: Long): Map<String, Any?> {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDictionary(shapedTextGetCaretsBind, handle, shaped, position)
    }

    /**
     * Returns selection rectangles for the specified character range.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_selection
     */
    fun shapedTextGetSelection(shaped: RID, start: Long, end: Long): List<Vector2> {
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetPackedVector2List(shapedTextGetSelectionBind, handle, shaped, start, end)
    }

    /**
     * Returns grapheme index at the specified pixel offset at the baseline, or `-1` if none is found.
     *
     * Generated from Godot docs: TextServer.shaped_text_hit_test_grapheme
     */
    fun shapedTextHitTestGrapheme(shaped: RID, coords: Double): Long {
        return ObjectCalls.ptrcallWithRIDAndDoubleArgRetLong(shapedTextHitTestGraphemeBind, handle, shaped, coords)
    }

    /**
     * Returns caret character offset at the specified pixel offset at the baseline. This function
     * always returns a valid position.
     *
     * Generated from Godot docs: TextServer.shaped_text_hit_test_position
     */
    fun shapedTextHitTestPosition(shaped: RID, coords: Double): Long {
        return ObjectCalls.ptrcallWithRIDAndDoubleArgRetLong(shapedTextHitTestPositionBind, handle, shaped, coords)
    }

    /**
     * Returns composite character's bounds as offsets from the start of the line.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_grapheme_bounds
     */
    fun shapedTextGetGraphemeBounds(shaped: RID, pos: Long): Vector2 {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVector2(shapedTextGetGraphemeBoundsBind, handle, shaped, pos)
    }

    /**
     * Returns grapheme end position closest to the `pos`.
     *
     * Generated from Godot docs: TextServer.shaped_text_next_grapheme_pos
     */
    fun shapedTextNextGraphemePos(shaped: RID, pos: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedTextNextGraphemePosBind, handle, shaped, pos)
    }

    /**
     * Returns grapheme start position closest to the `pos`.
     *
     * Generated from Godot docs: TextServer.shaped_text_prev_grapheme_pos
     */
    fun shapedTextPrevGraphemePos(shaped: RID, pos: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedTextPrevGraphemePosBind, handle, shaped, pos)
    }

    /**
     * Returns array of the composite character boundaries.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_character_breaks
     */
    fun shapedTextGetCharacterBreaks(shaped: RID): List<Int> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(shapedTextGetCharacterBreaksBind, handle, shaped)
    }

    /**
     * Returns composite character end position closest to the `pos`.
     *
     * Generated from Godot docs: TextServer.shaped_text_next_character_pos
     */
    fun shapedTextNextCharacterPos(shaped: RID, pos: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedTextNextCharacterPosBind, handle, shaped, pos)
    }

    /**
     * Returns composite character start position closest to the `pos`.
     *
     * Generated from Godot docs: TextServer.shaped_text_prev_character_pos
     */
    fun shapedTextPrevCharacterPos(shaped: RID, pos: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedTextPrevCharacterPosBind, handle, shaped, pos)
    }

    /**
     * Returns composite character position closest to the `pos`.
     *
     * Generated from Godot docs: TextServer.shaped_text_closest_character_pos
     */
    fun shapedTextClosestCharacterPos(shaped: RID, pos: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(shapedTextClosestCharacterPosBind, handle, shaped, pos)
    }

    /**
     * Draw shaped text into a canvas item at a given position, with `color`. `pos` specifies the
     * leftmost point of the baseline (for horizontal layout) or topmost point of the baseline (for
     * vertical layout). If `oversampling` is greater than zero, it is used as font oversampling
     * factor, otherwise viewport oversampling settings are used. `clip_l` and `clip_r` are offsets
     * relative to `pos`, going to the right in horizontal layout and downward in vertical layout. If
     * `clip_l` is not negative, glyphs starting before the offset are clipped. If `clip_r` is not
     * negative, glyphs ending after the offset are clipped.
     *
     * Generated from Godot docs: TextServer.shaped_text_draw
     */
    fun shapedTextDraw(shaped: RID, canvas: RID, pos: Vector2, clipL: Double = -1.0, clipR: Double = -1.0, color: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithTwoRIDVector2TwoDoubleColorDoubleArgs(shapedTextDrawBind, handle, shaped, canvas, pos, clipL, clipR, color, oversampling)
    }

    /**
     * Draw the outline of the shaped text into a canvas item at a given position, with `color`. `pos`
     * specifies the leftmost point of the baseline (for horizontal layout) or topmost point of the
     * baseline (for vertical layout). If `oversampling` is greater than zero, it is used as font
     * oversampling factor, otherwise viewport oversampling settings are used. `clip_l` and `clip_r`
     * are offsets relative to `pos`, going to the right in horizontal layout and downward in vertical
     * layout. If `clip_l` is not negative, glyphs starting before the offset are clipped. If `clip_r`
     * is not negative, glyphs ending after the offset are clipped.
     *
     * Generated from Godot docs: TextServer.shaped_text_draw_outline
     */
    fun shapedTextDrawOutline(shaped: RID, canvas: RID, pos: Vector2, clipL: Double = -1.0, clipR: Double = -1.0, outlineSize: Long = 1L, color: Color, oversampling: Double = 0.0) {
        ObjectCalls.ptrcallWithTwoRIDVector2TwoDoubleLongColorDoubleArgs(shapedTextDrawOutlineBind, handle, shaped, canvas, pos, clipL, clipR, outlineSize, color, oversampling)
    }

    /**
     * Returns dominant direction of in the range of text.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_dominant_direction_in_range
     */
    fun shapedTextGetDominantDirectionInRange(shaped: RID, start: Long, end: Long): Long {
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetLong(shapedTextGetDominantDirectionInRangeBind, handle, shaped, start, end)
    }

    /**
     * Converts a number from Western Arabic (0..9) to the numeral system used in the given `language`.
     * If `language` is an empty string, the active locale will be used.
     *
     * Generated from Godot docs: TextServer.format_number
     */
    fun formatNumber(number: String, language: String = ""): String {
        return ObjectCalls.ptrcallWithTwoStringArgsRetString(formatNumberBind, handle, number, language)
    }

    /**
     * Converts `number` from the numeral system used in the given `language` to Western Arabic (0..9).
     * If `language` is an empty string, the active locale will be used.
     *
     * Generated from Godot docs: TextServer.parse_number
     */
    fun parseNumber(number: String, language: String = ""): String {
        return ObjectCalls.ptrcallWithTwoStringArgsRetString(parseNumberBind, handle, number, language)
    }

    /**
     * Returns the percent sign used in the given `language`. If `language` is an empty string, the
     * active locale will be used.
     *
     * Generated from Godot docs: TextServer.percent_sign
     */
    fun percentSign(language: String = ""): String {
        return ObjectCalls.ptrcallWithStringArgRetString(percentSignBind, handle, language)
    }

    /**
     * Returns an array of the word break boundaries. Elements in the returned array are the offsets of
     * the start and end of words. Therefore the length of the array is always even. When
     * `chars_per_line` is greater than zero, line break boundaries are returned instead.
     *
     * Generated from Godot docs: TextServer.string_get_word_breaks
     */
    fun stringGetWordBreaks(string: String, language: String = "", charsPerLine: Long = 0L): List<Int> {
        return ObjectCalls.ptrcallWithTwoStringAndLongArgRetPackedInt32List(stringGetWordBreaksBind, handle, string, language, charsPerLine)
    }

    /**
     * Returns array of the composite character boundaries.
     *
     * Generated from Godot docs: TextServer.string_get_character_breaks
     */
    fun stringGetCharacterBreaks(string: String, language: String = ""): List<Int> {
        return ObjectCalls.ptrcallWithTwoStringArgsRetPackedInt32List(stringGetCharacterBreaksBind, handle, string, language)
    }

    /**
     * Returns index of the first string in `dict` which is visually confusable with the `string`, or
     * `-1` if none is found. Note: This method doesn't detect invisible characters, for spoof
     * detection use it in combination with `spoof_check`. Note: Always returns `-1` if the server does
     * not support the `FEATURE_UNICODE_SECURITY` feature.
     *
     * Generated from Godot docs: TextServer.is_confusable
     */
    fun isConfusable(string: String, dict: List<String>): Long {
        return ObjectCalls.ptrcallWithStringAndPackedStringListArgRetLong(isConfusableBind, handle, string, dict)
    }

    /**
     * Returns `true` if `string` is likely to be an attempt at confusing the reader. Note: Always
     * returns `false` if the server does not support the `FEATURE_UNICODE_SECURITY` feature.
     *
     * Generated from Godot docs: TextServer.spoof_check
     */
    fun spoofCheck(string: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(spoofCheckBind, handle, string)
    }

    /**
     * Strips diacritics from the string. Note: The result may be longer or shorter than the original.
     *
     * Generated from Godot docs: TextServer.strip_diacritics
     */
    fun stripDiacritics(string: String): String {
        return ObjectCalls.ptrcallWithStringArgRetString(stripDiacriticsBind, handle, string)
    }

    /**
     * Returns `true` if `string` is a valid identifier. If the text server supports the
     * `FEATURE_UNICODE_IDENTIFIERS` feature, a valid identifier must: - Conform to normalization form
     * C. - Begin with a Unicode character of class XID_Start or `"_"`. - May contain Unicode
     * characters of class XID_Continue in the other positions. - Use UAX #31 recommended scripts only
     * (mixed scripts are allowed). If the `FEATURE_UNICODE_IDENTIFIERS` feature is not supported, a
     * valid identifier must: - Begin with a Unicode character of class XID_Start or `"_"`. - May
     * contain Unicode characters of class XID_Continue in the other positions.
     *
     * Generated from Godot docs: TextServer.is_valid_identifier
     */
    fun isValidIdentifier(string: String): Boolean {
        return ObjectCalls.ptrcallWithStringArgRetBool(isValidIdentifierBind, handle, string)
    }

    /**
     * Returns `true` if the given code point is a valid letter, i.e. it belongs to the Unicode
     * category "L".
     *
     * Generated from Godot docs: TextServer.is_valid_letter
     */
    fun isValidLetter(unicode: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isValidLetterBind, handle, unicode)
    }

    /**
     * Returns the string converted to `UPPERCASE`. Note: Casing is locale dependent and context
     * sensitive if server support `FEATURE_CONTEXT_SENSITIVE_CASE_CONVERSION` feature (supported by
     * `TextServerAdvanced`). Note: The result may be longer or shorter than the original.
     *
     * Generated from Godot docs: TextServer.string_to_upper
     */
    fun stringToUpper(string: String, language: String = ""): String {
        return ObjectCalls.ptrcallWithTwoStringArgsRetString(stringToUpperBind, handle, string, language)
    }

    /**
     * Returns the string converted to `lowercase`. Note: Casing is locale dependent and context
     * sensitive if server support `FEATURE_CONTEXT_SENSITIVE_CASE_CONVERSION` feature (supported by
     * `TextServerAdvanced`). Note: The result may be longer or shorter than the original.
     *
     * Generated from Godot docs: TextServer.string_to_lower
     */
    fun stringToLower(string: String, language: String = ""): String {
        return ObjectCalls.ptrcallWithTwoStringArgsRetString(stringToLowerBind, handle, string, language)
    }

    /**
     * Returns the string converted to `Title Case`. Note: Casing is locale dependent and context
     * sensitive if server support `FEATURE_CONTEXT_SENSITIVE_CASE_CONVERSION` feature (supported by
     * `TextServerAdvanced`). Note: The result may be longer or shorter than the original.
     *
     * Generated from Godot docs: TextServer.string_to_title
     */
    fun stringToTitle(string: String, language: String = ""): String {
        return ObjectCalls.ptrcallWithTwoStringArgsRetString(stringToTitleBind, handle, string, language)
    }

    /**
     * Default implementation of the BiDi algorithm override function.
     *
     * Generated from Godot docs: TextServer.parse_structured_text
     */
    fun parseStructuredText(parserType: Long, args: List<Any?>, text: String): List<Vector3i> {
        return ObjectCalls.ptrcallWithLongArrayStringArgsRetVector3iList(parseStructuredTextBind, handle, parserType, args, text)
    }

    companion object {
        const val FONT_ANTIALIASING_NONE: Long = 0L
        const val FONT_ANTIALIASING_GRAY: Long = 1L
        const val FONT_ANTIALIASING_LCD: Long = 2L
        const val FONT_LCD_SUBPIXEL_LAYOUT_NONE: Long = 0L
        const val FONT_LCD_SUBPIXEL_LAYOUT_HRGB: Long = 1L
        const val FONT_LCD_SUBPIXEL_LAYOUT_HBGR: Long = 2L
        const val FONT_LCD_SUBPIXEL_LAYOUT_VRGB: Long = 3L
        const val FONT_LCD_SUBPIXEL_LAYOUT_VBGR: Long = 4L
        const val FONT_LCD_SUBPIXEL_LAYOUT_MAX: Long = 5L
        const val DIRECTION_AUTO: Long = 0L
        const val DIRECTION_LTR: Long = 1L
        const val DIRECTION_RTL: Long = 2L
        const val DIRECTION_INHERITED: Long = 3L
        const val ORIENTATION_HORIZONTAL: Long = 0L
        const val ORIENTATION_VERTICAL: Long = 1L
        const val JUSTIFICATION_NONE: Long = 0L
        const val JUSTIFICATION_KASHIDA: Long = 1L
        const val JUSTIFICATION_WORD_BOUND: Long = 2L
        const val JUSTIFICATION_TRIM_EDGE_SPACES: Long = 4L
        const val JUSTIFICATION_AFTER_LAST_TAB: Long = 8L
        const val JUSTIFICATION_CONSTRAIN_ELLIPSIS: Long = 16L
        const val JUSTIFICATION_SKIP_LAST_LINE: Long = 32L
        const val JUSTIFICATION_SKIP_LAST_LINE_WITH_VISIBLE_CHARS: Long = 64L
        const val JUSTIFICATION_DO_NOT_SKIP_SINGLE_LINE: Long = 128L
        const val AUTOWRAP_OFF: Long = 0L
        const val AUTOWRAP_ARBITRARY: Long = 1L
        const val AUTOWRAP_WORD: Long = 2L
        const val AUTOWRAP_WORD_SMART: Long = 3L
        const val BREAK_NONE: Long = 0L
        const val BREAK_MANDATORY: Long = 1L
        const val BREAK_WORD_BOUND: Long = 2L
        const val BREAK_GRAPHEME_BOUND: Long = 4L
        const val BREAK_ADAPTIVE: Long = 8L
        const val BREAK_TRIM_EDGE_SPACES: Long = 16L
        const val BREAK_TRIM_INDENT: Long = 32L
        const val BREAK_TRIM_START_EDGE_SPACES: Long = 64L
        const val BREAK_TRIM_END_EDGE_SPACES: Long = 128L
        const val VC_CHARS_BEFORE_SHAPING: Long = 0L
        const val VC_CHARS_AFTER_SHAPING: Long = 1L
        const val VC_GLYPHS_AUTO: Long = 2L
        const val VC_GLYPHS_LTR: Long = 3L
        const val VC_GLYPHS_RTL: Long = 4L
        const val OVERRUN_NO_TRIMMING: Long = 0L
        const val OVERRUN_TRIM_CHAR: Long = 1L
        const val OVERRUN_TRIM_WORD: Long = 2L
        const val OVERRUN_TRIM_ELLIPSIS: Long = 3L
        const val OVERRUN_TRIM_WORD_ELLIPSIS: Long = 4L
        const val OVERRUN_TRIM_ELLIPSIS_FORCE: Long = 5L
        const val OVERRUN_TRIM_WORD_ELLIPSIS_FORCE: Long = 6L
        const val OVERRUN_NO_TRIM: Long = 0L
        const val OVERRUN_TRIM: Long = 1L
        const val OVERRUN_TRIM_WORD_ONLY: Long = 2L
        const val OVERRUN_ADD_ELLIPSIS: Long = 4L
        const val OVERRUN_ENFORCE_ELLIPSIS: Long = 8L
        const val OVERRUN_JUSTIFICATION_AWARE: Long = 16L
        const val OVERRUN_SHORT_STRING_ELLIPSIS: Long = 32L
        const val GRAPHEME_IS_VALID: Long = 1L
        const val GRAPHEME_IS_RTL: Long = 2L
        const val GRAPHEME_IS_VIRTUAL: Long = 4L
        const val GRAPHEME_IS_SPACE: Long = 8L
        const val GRAPHEME_IS_BREAK_HARD: Long = 16L
        const val GRAPHEME_IS_BREAK_SOFT: Long = 32L
        const val GRAPHEME_IS_TAB: Long = 64L
        const val GRAPHEME_IS_ELONGATION: Long = 128L
        const val GRAPHEME_IS_PUNCTUATION: Long = 256L
        const val GRAPHEME_IS_UNDERSCORE: Long = 512L
        const val GRAPHEME_IS_CONNECTED: Long = 1024L
        const val GRAPHEME_IS_SAFE_TO_INSERT_TATWEEL: Long = 2048L
        const val GRAPHEME_IS_EMBEDDED_OBJECT: Long = 4096L
        const val GRAPHEME_IS_SOFT_HYPHEN: Long = 8192L
        const val HINTING_NONE: Long = 0L
        const val HINTING_LIGHT: Long = 1L
        const val HINTING_NORMAL: Long = 2L
        const val SUBPIXEL_POSITIONING_DISABLED: Long = 0L
        const val SUBPIXEL_POSITIONING_AUTO: Long = 1L
        const val SUBPIXEL_POSITIONING_ONE_HALF: Long = 2L
        const val SUBPIXEL_POSITIONING_ONE_QUARTER: Long = 3L
        const val SUBPIXEL_POSITIONING_ONE_HALF_MAX_SIZE: Long = 20L
        const val SUBPIXEL_POSITIONING_ONE_QUARTER_MAX_SIZE: Long = 16L
        const val FEATURE_SIMPLE_LAYOUT: Long = 1L
        const val FEATURE_BIDI_LAYOUT: Long = 2L
        const val FEATURE_VERTICAL_LAYOUT: Long = 4L
        const val FEATURE_SHAPING: Long = 8L
        const val FEATURE_KASHIDA_JUSTIFICATION: Long = 16L
        const val FEATURE_BREAK_ITERATORS: Long = 32L
        const val FEATURE_FONT_BITMAP: Long = 64L
        const val FEATURE_FONT_DYNAMIC: Long = 128L
        const val FEATURE_FONT_MSDF: Long = 256L
        const val FEATURE_FONT_SYSTEM: Long = 512L
        const val FEATURE_FONT_VARIABLE: Long = 1024L
        const val FEATURE_CONTEXT_SENSITIVE_CASE_CONVERSION: Long = 2048L
        const val FEATURE_USE_SUPPORT_DATA: Long = 4096L
        const val FEATURE_UNICODE_IDENTIFIERS: Long = 8192L
        const val FEATURE_UNICODE_SECURITY: Long = 16384L
        const val CONTOUR_CURVE_TAG_ON: Long = 1L
        const val CONTOUR_CURVE_TAG_OFF_CONIC: Long = 0L
        const val CONTOUR_CURVE_TAG_OFF_CUBIC: Long = 2L
        const val SPACING_GLYPH: Long = 0L
        const val SPACING_SPACE: Long = 1L
        const val SPACING_TOP: Long = 2L
        const val SPACING_BOTTOM: Long = 3L
        const val SPACING_MAX: Long = 4L
        const val FONT_BOLD: Long = 1L
        const val FONT_ITALIC: Long = 2L
        const val FONT_FIXED_WIDTH: Long = 4L
        const val STRUCTURED_TEXT_DEFAULT: Long = 0L
        const val STRUCTURED_TEXT_URI: Long = 1L
        const val STRUCTURED_TEXT_FILE: Long = 2L
        const val STRUCTURED_TEXT_EMAIL: Long = 3L
        const val STRUCTURED_TEXT_LIST: Long = 4L
        const val STRUCTURED_TEXT_GDSCRIPT: Long = 5L
        const val STRUCTURED_TEXT_CUSTOM: Long = 6L
        const val FIXED_SIZE_SCALE_DISABLE: Long = 0L
        const val FIXED_SIZE_SCALE_INTEGER_ONLY: Long = 1L
        const val FIXED_SIZE_SCALE_ENABLED: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): TextServer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): TextServer? =
            if (handle.address() == 0L) null else TextServer(handle)

        private const val HAS_FEATURE_HASH = 3967367083L
        private val hasFeatureBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "has_feature", HAS_FEATURE_HASH)
        }

        private const val GET_NAME_HASH = 201670096L
        private val getNameBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "get_name", GET_NAME_HASH)
        }

        private const val GET_FEATURES_HASH = 3905245786L
        private val getFeaturesBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "get_features", GET_FEATURES_HASH)
        }

        private const val LOAD_SUPPORT_DATA_HASH = 2323990056L
        private val loadSupportDataBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "load_support_data", LOAD_SUPPORT_DATA_HASH)
        }

        private const val GET_SUPPORT_DATA_FILENAME_HASH = 201670096L
        private val getSupportDataFilenameBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "get_support_data_filename", GET_SUPPORT_DATA_FILENAME_HASH)
        }

        private const val GET_SUPPORT_DATA_INFO_HASH = 201670096L
        private val getSupportDataInfoBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "get_support_data_info", GET_SUPPORT_DATA_INFO_HASH)
        }

        private const val SAVE_SUPPORT_DATA_HASH = 3927539163L
        private val saveSupportDataBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "save_support_data", SAVE_SUPPORT_DATA_HASH)
        }

        private const val GET_SUPPORT_DATA_HASH = 2362200018L
        private val getSupportDataBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "get_support_data", GET_SUPPORT_DATA_HASH)
        }

        private const val IS_LOCALE_USING_SUPPORT_DATA_HASH = 3927539163L
        private val isLocaleUsingSupportDataBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "is_locale_using_support_data", IS_LOCALE_USING_SUPPORT_DATA_HASH)
        }

        private const val IS_LOCALE_RIGHT_TO_LEFT_HASH = 3927539163L
        private val isLocaleRightToLeftBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "is_locale_right_to_left", IS_LOCALE_RIGHT_TO_LEFT_HASH)
        }

        private const val NAME_TO_TAG_HASH = 1321353865L
        private val nameToTagBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "name_to_tag", NAME_TO_TAG_HASH)
        }

        private const val TAG_TO_NAME_HASH = 844755477L
        private val tagToNameBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "tag_to_name", TAG_TO_NAME_HASH)
        }

        private const val HAS_HASH = 3521089500L
        private val hasBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "has", HAS_HASH)
        }

        private const val FREE_RID_HASH = 2722037293L
        private val freeRidBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "free_rid", FREE_RID_HASH)
        }

        private const val CREATE_FONT_HASH = 529393457L
        private val createFontBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "create_font", CREATE_FONT_HASH)
        }

        private const val CREATE_FONT_LINKED_VARIATION_HASH = 41030802L
        private val createFontLinkedVariationBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "create_font_linked_variation", CREATE_FONT_LINKED_VARIATION_HASH)
        }

        private const val FONT_SET_DATA_HASH = 1355495400L
        private val fontSetDataBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_data", FONT_SET_DATA_HASH)
        }

        private const val FONT_SET_FACE_INDEX_HASH = 3411492887L
        private val fontSetFaceIndexBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_face_index", FONT_SET_FACE_INDEX_HASH)
        }

        private const val FONT_GET_FACE_INDEX_HASH = 2198884583L
        private val fontGetFaceIndexBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_face_index", FONT_GET_FACE_INDEX_HASH)
        }

        private const val FONT_GET_FACE_COUNT_HASH = 2198884583L
        private val fontGetFaceCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_face_count", FONT_GET_FACE_COUNT_HASH)
        }

        private const val FONT_SET_STYLE_HASH = 898466325L
        private val fontSetStyleBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_style", FONT_SET_STYLE_HASH)
        }

        private const val FONT_GET_STYLE_HASH = 3082502592L
        private val fontGetStyleBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_style", FONT_GET_STYLE_HASH)
        }

        private const val FONT_SET_NAME_HASH = 2726140452L
        private val fontSetNameBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_name", FONT_SET_NAME_HASH)
        }

        private const val FONT_GET_NAME_HASH = 642473191L
        private val fontGetNameBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_name", FONT_GET_NAME_HASH)
        }

        private const val FONT_GET_OT_NAME_STRINGS_HASH = 1882737106L
        private val fontGetOtNameStringsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_ot_name_strings", FONT_GET_OT_NAME_STRINGS_HASH)
        }

        private const val FONT_SET_STYLE_NAME_HASH = 2726140452L
        private val fontSetStyleNameBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_style_name", FONT_SET_STYLE_NAME_HASH)
        }

        private const val FONT_GET_STYLE_NAME_HASH = 642473191L
        private val fontGetStyleNameBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_style_name", FONT_GET_STYLE_NAME_HASH)
        }

        private const val FONT_SET_WEIGHT_HASH = 3411492887L
        private val fontSetWeightBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_weight", FONT_SET_WEIGHT_HASH)
        }

        private const val FONT_GET_WEIGHT_HASH = 2198884583L
        private val fontGetWeightBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_weight", FONT_GET_WEIGHT_HASH)
        }

        private const val FONT_SET_STRETCH_HASH = 3411492887L
        private val fontSetStretchBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_stretch", FONT_SET_STRETCH_HASH)
        }

        private const val FONT_GET_STRETCH_HASH = 2198884583L
        private val fontGetStretchBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_stretch", FONT_GET_STRETCH_HASH)
        }

        private const val FONT_SET_ANTIALIASING_HASH = 958337235L
        private val fontSetAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_antialiasing", FONT_SET_ANTIALIASING_HASH)
        }

        private const val FONT_GET_ANTIALIASING_HASH = 3389420495L
        private val fontGetAntialiasingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_antialiasing", FONT_GET_ANTIALIASING_HASH)
        }

        private const val FONT_SET_DISABLE_EMBEDDED_BITMAPS_HASH = 1265174801L
        private val fontSetDisableEmbeddedBitmapsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_disable_embedded_bitmaps", FONT_SET_DISABLE_EMBEDDED_BITMAPS_HASH)
        }

        private const val FONT_GET_DISABLE_EMBEDDED_BITMAPS_HASH = 4155700596L
        private val fontGetDisableEmbeddedBitmapsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_disable_embedded_bitmaps", FONT_GET_DISABLE_EMBEDDED_BITMAPS_HASH)
        }

        private const val FONT_SET_GENERATE_MIPMAPS_HASH = 1265174801L
        private val fontSetGenerateMipmapsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_generate_mipmaps", FONT_SET_GENERATE_MIPMAPS_HASH)
        }

        private const val FONT_GET_GENERATE_MIPMAPS_HASH = 4155700596L
        private val fontGetGenerateMipmapsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_generate_mipmaps", FONT_GET_GENERATE_MIPMAPS_HASH)
        }

        private const val FONT_SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 1265174801L
        private val fontSetMultichannelSignedDistanceFieldBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_multichannel_signed_distance_field", FONT_SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)
        }

        private const val FONT_IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 4155700596L
        private val fontIsMultichannelSignedDistanceFieldBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_is_multichannel_signed_distance_field", FONT_IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)
        }

        private const val FONT_SET_MSDF_PIXEL_RANGE_HASH = 3411492887L
        private val fontSetMsdfPixelRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_msdf_pixel_range", FONT_SET_MSDF_PIXEL_RANGE_HASH)
        }

        private const val FONT_GET_MSDF_PIXEL_RANGE_HASH = 2198884583L
        private val fontGetMsdfPixelRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_msdf_pixel_range", FONT_GET_MSDF_PIXEL_RANGE_HASH)
        }

        private const val FONT_SET_MSDF_SIZE_HASH = 3411492887L
        private val fontSetMsdfSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_msdf_size", FONT_SET_MSDF_SIZE_HASH)
        }

        private const val FONT_GET_MSDF_SIZE_HASH = 2198884583L
        private val fontGetMsdfSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_msdf_size", FONT_GET_MSDF_SIZE_HASH)
        }

        private const val FONT_SET_FIXED_SIZE_HASH = 3411492887L
        private val fontSetFixedSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_fixed_size", FONT_SET_FIXED_SIZE_HASH)
        }

        private const val FONT_GET_FIXED_SIZE_HASH = 2198884583L
        private val fontGetFixedSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_fixed_size", FONT_GET_FIXED_SIZE_HASH)
        }

        private const val FONT_SET_FIXED_SIZE_SCALE_MODE_HASH = 1029390307L
        private val fontSetFixedSizeScaleModeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_fixed_size_scale_mode", FONT_SET_FIXED_SIZE_SCALE_MODE_HASH)
        }

        private const val FONT_GET_FIXED_SIZE_SCALE_MODE_HASH = 4113120379L
        private val fontGetFixedSizeScaleModeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_fixed_size_scale_mode", FONT_GET_FIXED_SIZE_SCALE_MODE_HASH)
        }

        private const val FONT_SET_ALLOW_SYSTEM_FALLBACK_HASH = 1265174801L
        private val fontSetAllowSystemFallbackBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_allow_system_fallback", FONT_SET_ALLOW_SYSTEM_FALLBACK_HASH)
        }

        private const val FONT_IS_ALLOW_SYSTEM_FALLBACK_HASH = 4155700596L
        private val fontIsAllowSystemFallbackBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_is_allow_system_fallback", FONT_IS_ALLOW_SYSTEM_FALLBACK_HASH)
        }

        private const val FONT_CLEAR_SYSTEM_FALLBACK_CACHE_HASH = 3218959716L
        private val fontClearSystemFallbackCacheBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_clear_system_fallback_cache", FONT_CLEAR_SYSTEM_FALLBACK_CACHE_HASH)
        }

        private const val FONT_SET_FORCE_AUTOHINTER_HASH = 1265174801L
        private val fontSetForceAutohinterBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_force_autohinter", FONT_SET_FORCE_AUTOHINTER_HASH)
        }

        private const val FONT_IS_FORCE_AUTOHINTER_HASH = 4155700596L
        private val fontIsForceAutohinterBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_is_force_autohinter", FONT_IS_FORCE_AUTOHINTER_HASH)
        }

        private const val FONT_SET_MODULATE_COLOR_GLYPHS_HASH = 1265174801L
        private val fontSetModulateColorGlyphsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_modulate_color_glyphs", FONT_SET_MODULATE_COLOR_GLYPHS_HASH)
        }

        private const val FONT_IS_MODULATE_COLOR_GLYPHS_HASH = 4155700596L
        private val fontIsModulateColorGlyphsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_is_modulate_color_glyphs", FONT_IS_MODULATE_COLOR_GLYPHS_HASH)
        }

        private const val FONT_GET_PALETTE_COUNT_HASH = 2198884583L
        private val fontGetPaletteCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_palette_count", FONT_GET_PALETTE_COUNT_HASH)
        }

        private const val FONT_GET_PALETTE_NAME_HASH = 1464764419L
        private val fontGetPaletteNameBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_palette_name", FONT_GET_PALETTE_NAME_HASH)
        }

        private const val FONT_GET_PALETTE_COLORS_HASH = 1595517857L
        private val fontGetPaletteColorsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_palette_colors", FONT_GET_PALETTE_COLORS_HASH)
        }

        private const val FONT_SET_PALETTE_CUSTOM_COLORS_HASH = 4037098590L
        private val fontSetPaletteCustomColorsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_palette_custom_colors", FONT_SET_PALETTE_CUSTOM_COLORS_HASH)
        }

        private const val FONT_GET_PALETTE_CUSTOM_COLORS_HASH = 1569415609L
        private val fontGetPaletteCustomColorsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_palette_custom_colors", FONT_GET_PALETTE_CUSTOM_COLORS_HASH)
        }

        private const val FONT_GET_USED_PALETTE_HASH = 2198884583L
        private val fontGetUsedPaletteBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_used_palette", FONT_GET_USED_PALETTE_HASH)
        }

        private const val FONT_SET_USED_PALETTE_HASH = 3411492887L
        private val fontSetUsedPaletteBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_used_palette", FONT_SET_USED_PALETTE_HASH)
        }

        private const val FONT_SET_HINTING_HASH = 1520010864L
        private val fontSetHintingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_hinting", FONT_SET_HINTING_HASH)
        }

        private const val FONT_GET_HINTING_HASH = 3971592737L
        private val fontGetHintingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_hinting", FONT_GET_HINTING_HASH)
        }

        private const val FONT_SET_SUBPIXEL_POSITIONING_HASH = 3830459669L
        private val fontSetSubpixelPositioningBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_subpixel_positioning", FONT_SET_SUBPIXEL_POSITIONING_HASH)
        }

        private const val FONT_GET_SUBPIXEL_POSITIONING_HASH = 2752233671L
        private val fontGetSubpixelPositioningBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_subpixel_positioning", FONT_GET_SUBPIXEL_POSITIONING_HASH)
        }

        private const val FONT_SET_KEEP_ROUNDING_REMAINDERS_HASH = 1265174801L
        private val fontSetKeepRoundingRemaindersBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_keep_rounding_remainders", FONT_SET_KEEP_ROUNDING_REMAINDERS_HASH)
        }

        private const val FONT_GET_KEEP_ROUNDING_REMAINDERS_HASH = 4155700596L
        private val fontGetKeepRoundingRemaindersBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_keep_rounding_remainders", FONT_GET_KEEP_ROUNDING_REMAINDERS_HASH)
        }

        private const val FONT_SET_EMBOLDEN_HASH = 1794382983L
        private val fontSetEmboldenBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_embolden", FONT_SET_EMBOLDEN_HASH)
        }

        private const val FONT_GET_EMBOLDEN_HASH = 866169185L
        private val fontGetEmboldenBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_embolden", FONT_GET_EMBOLDEN_HASH)
        }

        private const val FONT_SET_SPACING_HASH = 1307259930L
        private val fontSetSpacingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_spacing", FONT_SET_SPACING_HASH)
        }

        private const val FONT_GET_SPACING_HASH = 1213653558L
        private val fontGetSpacingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_spacing", FONT_GET_SPACING_HASH)
        }

        private const val FONT_SET_BASELINE_OFFSET_HASH = 1794382983L
        private val fontSetBaselineOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_baseline_offset", FONT_SET_BASELINE_OFFSET_HASH)
        }

        private const val FONT_GET_BASELINE_OFFSET_HASH = 866169185L
        private val fontGetBaselineOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_baseline_offset", FONT_GET_BASELINE_OFFSET_HASH)
        }

        private const val FONT_SET_TRANSFORM_HASH = 1246044741L
        private val fontSetTransformBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_transform", FONT_SET_TRANSFORM_HASH)
        }

        private const val FONT_GET_TRANSFORM_HASH = 213527486L
        private val fontGetTransformBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_transform", FONT_GET_TRANSFORM_HASH)
        }

        private const val FONT_SET_VARIATION_COORDINATES_HASH = 1217542888L
        private val fontSetVariationCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_variation_coordinates", FONT_SET_VARIATION_COORDINATES_HASH)
        }

        private const val FONT_GET_VARIATION_COORDINATES_HASH = 1882737106L
        private val fontGetVariationCoordinatesBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_variation_coordinates", FONT_GET_VARIATION_COORDINATES_HASH)
        }

        private const val FONT_SET_OVERSAMPLING_HASH = 1794382983L
        private val fontSetOversamplingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_oversampling", FONT_SET_OVERSAMPLING_HASH)
        }

        private const val FONT_GET_OVERSAMPLING_HASH = 866169185L
        private val fontGetOversamplingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_oversampling", FONT_GET_OVERSAMPLING_HASH)
        }

        private const val FONT_GET_SIZE_CACHE_LIST_HASH = 2684255073L
        private val fontGetSizeCacheListBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_size_cache_list", FONT_GET_SIZE_CACHE_LIST_HASH)
        }

        private const val FONT_CLEAR_SIZE_CACHE_HASH = 2722037293L
        private val fontClearSizeCacheBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_clear_size_cache", FONT_CLEAR_SIZE_CACHE_HASH)
        }

        private const val FONT_REMOVE_SIZE_CACHE_HASH = 2450610377L
        private val fontRemoveSizeCacheBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_remove_size_cache", FONT_REMOVE_SIZE_CACHE_HASH)
        }

        private const val FONT_GET_SIZE_CACHE_INFO_HASH = 2684255073L
        private val fontGetSizeCacheInfoBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_size_cache_info", FONT_GET_SIZE_CACHE_INFO_HASH)
        }

        private const val FONT_SET_ASCENT_HASH = 1892459533L
        private val fontSetAscentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_ascent", FONT_SET_ASCENT_HASH)
        }

        private const val FONT_GET_ASCENT_HASH = 755457166L
        private val fontGetAscentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_ascent", FONT_GET_ASCENT_HASH)
        }

        private const val FONT_SET_DESCENT_HASH = 1892459533L
        private val fontSetDescentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_descent", FONT_SET_DESCENT_HASH)
        }

        private const val FONT_GET_DESCENT_HASH = 755457166L
        private val fontGetDescentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_descent", FONT_GET_DESCENT_HASH)
        }

        private const val FONT_SET_UNDERLINE_POSITION_HASH = 1892459533L
        private val fontSetUnderlinePositionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_underline_position", FONT_SET_UNDERLINE_POSITION_HASH)
        }

        private const val FONT_GET_UNDERLINE_POSITION_HASH = 755457166L
        private val fontGetUnderlinePositionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_underline_position", FONT_GET_UNDERLINE_POSITION_HASH)
        }

        private const val FONT_SET_UNDERLINE_THICKNESS_HASH = 1892459533L
        private val fontSetUnderlineThicknessBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_underline_thickness", FONT_SET_UNDERLINE_THICKNESS_HASH)
        }

        private const val FONT_GET_UNDERLINE_THICKNESS_HASH = 755457166L
        private val fontGetUnderlineThicknessBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_underline_thickness", FONT_GET_UNDERLINE_THICKNESS_HASH)
        }

        private const val FONT_SET_SCALE_HASH = 1892459533L
        private val fontSetScaleBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_scale", FONT_SET_SCALE_HASH)
        }

        private const val FONT_GET_SCALE_HASH = 755457166L
        private val fontGetScaleBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_scale", FONT_GET_SCALE_HASH)
        }

        private const val FONT_GET_TEXTURE_COUNT_HASH = 1311001310L
        private val fontGetTextureCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_texture_count", FONT_GET_TEXTURE_COUNT_HASH)
        }

        private const val FONT_CLEAR_TEXTURES_HASH = 2450610377L
        private val fontClearTexturesBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_clear_textures", FONT_CLEAR_TEXTURES_HASH)
        }

        private const val FONT_REMOVE_TEXTURE_HASH = 3810512262L
        private val fontRemoveTextureBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_remove_texture", FONT_REMOVE_TEXTURE_HASH)
        }

        private const val FONT_SET_TEXTURE_IMAGE_HASH = 2354485091L
        private val fontSetTextureImageBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_texture_image", FONT_SET_TEXTURE_IMAGE_HASH)
        }

        private const val FONT_GET_TEXTURE_IMAGE_HASH = 2451761155L
        private val fontGetTextureImageBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_texture_image", FONT_GET_TEXTURE_IMAGE_HASH)
        }

        private const val FONT_SET_TEXTURE_OFFSETS_HASH = 3005398047L
        private val fontSetTextureOffsetsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_texture_offsets", FONT_SET_TEXTURE_OFFSETS_HASH)
        }

        private const val FONT_GET_TEXTURE_OFFSETS_HASH = 3420028887L
        private val fontGetTextureOffsetsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_texture_offsets", FONT_GET_TEXTURE_OFFSETS_HASH)
        }

        private const val FONT_GET_GLYPH_LIST_HASH = 46086620L
        private val fontGetGlyphListBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_list", FONT_GET_GLYPH_LIST_HASH)
        }

        private const val FONT_CLEAR_GLYPHS_HASH = 2450610377L
        private val fontClearGlyphsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_clear_glyphs", FONT_CLEAR_GLYPHS_HASH)
        }

        private const val FONT_REMOVE_GLYPH_HASH = 3810512262L
        private val fontRemoveGlyphBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_remove_glyph", FONT_REMOVE_GLYPH_HASH)
        }

        private const val FONT_GET_GLYPH_ADVANCE_HASH = 2555689501L
        private val fontGetGlyphAdvanceBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_advance", FONT_GET_GLYPH_ADVANCE_HASH)
        }

        private const val FONT_SET_GLYPH_ADVANCE_HASH = 3219397315L
        private val fontSetGlyphAdvanceBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_advance", FONT_SET_GLYPH_ADVANCE_HASH)
        }

        private const val FONT_GET_GLYPH_OFFSET_HASH = 513728628L
        private val fontGetGlyphOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_offset", FONT_GET_GLYPH_OFFSET_HASH)
        }

        private const val FONT_SET_GLYPH_OFFSET_HASH = 1812632090L
        private val fontSetGlyphOffsetBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_offset", FONT_SET_GLYPH_OFFSET_HASH)
        }

        private const val FONT_GET_GLYPH_SIZE_HASH = 513728628L
        private val fontGetGlyphSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_size", FONT_GET_GLYPH_SIZE_HASH)
        }

        private const val FONT_SET_GLYPH_SIZE_HASH = 1812632090L
        private val fontSetGlyphSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_size", FONT_SET_GLYPH_SIZE_HASH)
        }

        private const val FONT_GET_GLYPH_UV_RECT_HASH = 2274268786L
        private val fontGetGlyphUvRectBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_uv_rect", FONT_GET_GLYPH_UV_RECT_HASH)
        }

        private const val FONT_SET_GLYPH_UV_RECT_HASH = 1973324081L
        private val fontSetGlyphUvRectBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_uv_rect", FONT_SET_GLYPH_UV_RECT_HASH)
        }

        private const val FONT_GET_GLYPH_TEXTURE_IDX_HASH = 4292800474L
        private val fontGetGlyphTextureIdxBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_texture_idx", FONT_GET_GLYPH_TEXTURE_IDX_HASH)
        }

        private const val FONT_SET_GLYPH_TEXTURE_IDX_HASH = 4254580980L
        private val fontSetGlyphTextureIdxBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_texture_idx", FONT_SET_GLYPH_TEXTURE_IDX_HASH)
        }

        private const val FONT_GET_GLYPH_TEXTURE_RID_HASH = 1451696141L
        private val fontGetGlyphTextureRidBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_texture_rid", FONT_GET_GLYPH_TEXTURE_RID_HASH)
        }

        private const val FONT_GET_GLYPH_TEXTURE_SIZE_HASH = 513728628L
        private val fontGetGlyphTextureSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_texture_size", FONT_GET_GLYPH_TEXTURE_SIZE_HASH)
        }

        private const val FONT_GET_GLYPH_CONTOURS_HASH = 2903964473L
        private val fontGetGlyphContoursBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_contours", FONT_GET_GLYPH_CONTOURS_HASH)
        }

        private const val FONT_GET_KERNING_LIST_HASH = 1778388067L
        private val fontGetKerningListBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_kerning_list", FONT_GET_KERNING_LIST_HASH)
        }

        private const val FONT_CLEAR_KERNING_MAP_HASH = 3411492887L
        private val fontClearKerningMapBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_clear_kerning_map", FONT_CLEAR_KERNING_MAP_HASH)
        }

        private const val FONT_REMOVE_KERNING_HASH = 2141860016L
        private val fontRemoveKerningBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_remove_kerning", FONT_REMOVE_KERNING_HASH)
        }

        private const val FONT_SET_KERNING_HASH = 3630965883L
        private val fontSetKerningBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_kerning", FONT_SET_KERNING_HASH)
        }

        private const val FONT_GET_KERNING_HASH = 1019980169L
        private val fontGetKerningBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_kerning", FONT_GET_KERNING_HASH)
        }

        private const val FONT_GET_GLYPH_INDEX_HASH = 1765635060L
        private val fontGetGlyphIndexBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_index", FONT_GET_GLYPH_INDEX_HASH)
        }

        private const val FONT_GET_CHAR_FROM_GLYPH_INDEX_HASH = 2156738276L
        private val fontGetCharFromGlyphIndexBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_char_from_glyph_index", FONT_GET_CHAR_FROM_GLYPH_INDEX_HASH)
        }

        private const val FONT_HAS_CHAR_HASH = 3120086654L
        private val fontHasCharBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_has_char", FONT_HAS_CHAR_HASH)
        }

        private const val FONT_GET_SUPPORTED_CHARS_HASH = 642473191L
        private val fontGetSupportedCharsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_supported_chars", FONT_GET_SUPPORTED_CHARS_HASH)
        }

        private const val FONT_GET_SUPPORTED_GLYPHS_HASH = 788230395L
        private val fontGetSupportedGlyphsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_supported_glyphs", FONT_GET_SUPPORTED_GLYPHS_HASH)
        }

        private const val FONT_RENDER_RANGE_HASH = 4254580980L
        private val fontRenderRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_render_range", FONT_RENDER_RANGE_HASH)
        }

        private const val FONT_RENDER_GLYPH_HASH = 3810512262L
        private val fontRenderGlyphBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_render_glyph", FONT_RENDER_GLYPH_HASH)
        }

        private const val FONT_DRAW_GLYPH_HASH = 3103234926L
        private val fontDrawGlyphBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_draw_glyph", FONT_DRAW_GLYPH_HASH)
        }

        private const val FONT_DRAW_GLYPH_OUTLINE_HASH = 1976041553L
        private val fontDrawGlyphOutlineBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_draw_glyph_outline", FONT_DRAW_GLYPH_OUTLINE_HASH)
        }

        private const val FONT_IS_LANGUAGE_SUPPORTED_HASH = 3199320846L
        private val fontIsLanguageSupportedBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_is_language_supported", FONT_IS_LANGUAGE_SUPPORTED_HASH)
        }

        private const val FONT_SET_LANGUAGE_SUPPORT_OVERRIDE_HASH = 2313957094L
        private val fontSetLanguageSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_language_support_override", FONT_SET_LANGUAGE_SUPPORT_OVERRIDE_HASH)
        }

        private const val FONT_GET_LANGUAGE_SUPPORT_OVERRIDE_HASH = 2829184646L
        private val fontGetLanguageSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_language_support_override", FONT_GET_LANGUAGE_SUPPORT_OVERRIDE_HASH)
        }

        private const val FONT_REMOVE_LANGUAGE_SUPPORT_OVERRIDE_HASH = 2726140452L
        private val fontRemoveLanguageSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_remove_language_support_override", FONT_REMOVE_LANGUAGE_SUPPORT_OVERRIDE_HASH)
        }

        private const val FONT_GET_LANGUAGE_SUPPORT_OVERRIDES_HASH = 2801473409L
        private val fontGetLanguageSupportOverridesBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_language_support_overrides", FONT_GET_LANGUAGE_SUPPORT_OVERRIDES_HASH)
        }

        private const val FONT_IS_SCRIPT_SUPPORTED_HASH = 3199320846L
        private val fontIsScriptSupportedBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_is_script_supported", FONT_IS_SCRIPT_SUPPORTED_HASH)
        }

        private const val FONT_SET_SCRIPT_SUPPORT_OVERRIDE_HASH = 2313957094L
        private val fontSetScriptSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_script_support_override", FONT_SET_SCRIPT_SUPPORT_OVERRIDE_HASH)
        }

        private const val FONT_GET_SCRIPT_SUPPORT_OVERRIDE_HASH = 2829184646L
        private val fontGetScriptSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_script_support_override", FONT_GET_SCRIPT_SUPPORT_OVERRIDE_HASH)
        }

        private const val FONT_REMOVE_SCRIPT_SUPPORT_OVERRIDE_HASH = 2726140452L
        private val fontRemoveScriptSupportOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_remove_script_support_override", FONT_REMOVE_SCRIPT_SUPPORT_OVERRIDE_HASH)
        }

        private const val FONT_GET_SCRIPT_SUPPORT_OVERRIDES_HASH = 2801473409L
        private val fontGetScriptSupportOverridesBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_script_support_overrides", FONT_GET_SCRIPT_SUPPORT_OVERRIDES_HASH)
        }

        private const val FONT_SET_OPENTYPE_FEATURE_OVERRIDES_HASH = 1217542888L
        private val fontSetOpentypeFeatureOverridesBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_opentype_feature_overrides", FONT_SET_OPENTYPE_FEATURE_OVERRIDES_HASH)
        }

        private const val FONT_GET_OPENTYPE_FEATURE_OVERRIDES_HASH = 1882737106L
        private val fontGetOpentypeFeatureOverridesBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_opentype_feature_overrides", FONT_GET_OPENTYPE_FEATURE_OVERRIDES_HASH)
        }

        private const val FONT_SUPPORTED_FEATURE_LIST_HASH = 1882737106L
        private val fontSupportedFeatureListBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_supported_feature_list", FONT_SUPPORTED_FEATURE_LIST_HASH)
        }

        private const val FONT_SUPPORTED_VARIATION_LIST_HASH = 1882737106L
        private val fontSupportedVariationListBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_supported_variation_list", FONT_SUPPORTED_VARIATION_LIST_HASH)
        }

        private const val FONT_GET_GLOBAL_OVERSAMPLING_HASH = 1740695150L
        private val fontGetGlobalOversamplingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_get_global_oversampling", FONT_GET_GLOBAL_OVERSAMPLING_HASH)
        }

        private const val FONT_SET_GLOBAL_OVERSAMPLING_HASH = 373806689L
        private val fontSetGlobalOversamplingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "font_set_global_oversampling", FONT_SET_GLOBAL_OVERSAMPLING_HASH)
        }

        private const val GET_HEX_CODE_BOX_SIZE_HASH = 3016396712L
        private val getHexCodeBoxSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "get_hex_code_box_size", GET_HEX_CODE_BOX_SIZE_HASH)
        }

        private const val DRAW_HEX_CODE_BOX_HASH = 1602046441L
        private val drawHexCodeBoxBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "draw_hex_code_box", DRAW_HEX_CODE_BOX_HASH)
        }

        private const val CREATE_SHAPED_TEXT_HASH = 1231398698L
        private val createShapedTextBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "create_shaped_text", CREATE_SHAPED_TEXT_HASH)
        }

        private const val SHAPED_TEXT_CLEAR_HASH = 2722037293L
        private val shapedTextClearBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_clear", SHAPED_TEXT_CLEAR_HASH)
        }

        private const val SHAPED_TEXT_DUPLICATE_HASH = 41030802L
        private val shapedTextDuplicateBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_duplicate", SHAPED_TEXT_DUPLICATE_HASH)
        }

        private const val SHAPED_TEXT_SET_DIRECTION_HASH = 1551430183L
        private val shapedTextSetDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_direction", SHAPED_TEXT_SET_DIRECTION_HASH)
        }

        private const val SHAPED_TEXT_GET_DIRECTION_HASH = 3065904362L
        private val shapedTextGetDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_direction", SHAPED_TEXT_GET_DIRECTION_HASH)
        }

        private const val SHAPED_TEXT_GET_INFERRED_DIRECTION_HASH = 3065904362L
        private val shapedTextGetInferredDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_inferred_direction", SHAPED_TEXT_GET_INFERRED_DIRECTION_HASH)
        }

        private const val SHAPED_TEXT_SET_BIDI_OVERRIDE_HASH = 684822712L
        private val shapedTextSetBidiOverrideBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_bidi_override", SHAPED_TEXT_SET_BIDI_OVERRIDE_HASH)
        }

        private const val SHAPED_TEXT_SET_CUSTOM_PUNCTUATION_HASH = 2726140452L
        private val shapedTextSetCustomPunctuationBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_custom_punctuation", SHAPED_TEXT_SET_CUSTOM_PUNCTUATION_HASH)
        }

        private const val SHAPED_TEXT_GET_CUSTOM_PUNCTUATION_HASH = 642473191L
        private val shapedTextGetCustomPunctuationBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_custom_punctuation", SHAPED_TEXT_GET_CUSTOM_PUNCTUATION_HASH)
        }

        private const val SHAPED_TEXT_SET_CUSTOM_ELLIPSIS_HASH = 3411492887L
        private val shapedTextSetCustomEllipsisBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_custom_ellipsis", SHAPED_TEXT_SET_CUSTOM_ELLIPSIS_HASH)
        }

        private const val SHAPED_TEXT_GET_CUSTOM_ELLIPSIS_HASH = 2198884583L
        private val shapedTextGetCustomEllipsisBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_custom_ellipsis", SHAPED_TEXT_GET_CUSTOM_ELLIPSIS_HASH)
        }

        private const val SHAPED_TEXT_SET_ORIENTATION_HASH = 3019609126L
        private val shapedTextSetOrientationBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_orientation", SHAPED_TEXT_SET_ORIENTATION_HASH)
        }

        private const val SHAPED_TEXT_GET_ORIENTATION_HASH = 3142708106L
        private val shapedTextGetOrientationBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_orientation", SHAPED_TEXT_GET_ORIENTATION_HASH)
        }

        private const val SHAPED_TEXT_SET_PRESERVE_INVALID_HASH = 1265174801L
        private val shapedTextSetPreserveInvalidBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_preserve_invalid", SHAPED_TEXT_SET_PRESERVE_INVALID_HASH)
        }

        private const val SHAPED_TEXT_GET_PRESERVE_INVALID_HASH = 4155700596L
        private val shapedTextGetPreserveInvalidBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_preserve_invalid", SHAPED_TEXT_GET_PRESERVE_INVALID_HASH)
        }

        private const val SHAPED_TEXT_SET_PRESERVE_CONTROL_HASH = 1265174801L
        private val shapedTextSetPreserveControlBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_preserve_control", SHAPED_TEXT_SET_PRESERVE_CONTROL_HASH)
        }

        private const val SHAPED_TEXT_GET_PRESERVE_CONTROL_HASH = 4155700596L
        private val shapedTextGetPreserveControlBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_preserve_control", SHAPED_TEXT_GET_PRESERVE_CONTROL_HASH)
        }

        private const val SHAPED_TEXT_SET_SPACING_HASH = 1307259930L
        private val shapedTextSetSpacingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_spacing", SHAPED_TEXT_SET_SPACING_HASH)
        }

        private const val SHAPED_TEXT_GET_SPACING_HASH = 1213653558L
        private val shapedTextGetSpacingBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_spacing", SHAPED_TEXT_GET_SPACING_HASH)
        }

        private const val SHAPED_TEXT_ADD_STRING_HASH = 623473029L
        private val shapedTextAddStringBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_add_string", SHAPED_TEXT_ADD_STRING_HASH)
        }

        private const val SHAPED_TEXT_ADD_OBJECT_HASH = 3664424789L
        private val shapedTextAddObjectBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_add_object", SHAPED_TEXT_ADD_OBJECT_HASH)
        }

        private const val SHAPED_TEXT_RESIZE_OBJECT_HASH = 790361552L
        private val shapedTextResizeObjectBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_resize_object", SHAPED_TEXT_RESIZE_OBJECT_HASH)
        }

        private const val SHAPED_TEXT_HAS_OBJECT_HASH = 2360964694L
        private val shapedTextHasObjectBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_has_object", SHAPED_TEXT_HAS_OBJECT_HASH)
        }

        private const val SHAPED_GET_TEXT_HASH = 642473191L
        private val shapedGetTextBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_text", SHAPED_GET_TEXT_HASH)
        }

        private const val SHAPED_GET_SPAN_COUNT_HASH = 2198884583L
        private val shapedGetSpanCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_span_count", SHAPED_GET_SPAN_COUNT_HASH)
        }

        private const val SHAPED_GET_SPAN_META_HASH = 4069510997L
        private val shapedGetSpanMetaBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_span_meta", SHAPED_GET_SPAN_META_HASH)
        }

        private const val SHAPED_GET_SPAN_EMBEDDED_OBJECT_HASH = 4069510997L
        private val shapedGetSpanEmbeddedObjectBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_span_embedded_object", SHAPED_GET_SPAN_EMBEDDED_OBJECT_HASH)
        }

        private const val SHAPED_GET_SPAN_TEXT_HASH = 1464764419L
        private val shapedGetSpanTextBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_span_text", SHAPED_GET_SPAN_TEXT_HASH)
        }

        private const val SHAPED_GET_SPAN_OBJECT_HASH = 4069510997L
        private val shapedGetSpanObjectBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_span_object", SHAPED_GET_SPAN_OBJECT_HASH)
        }

        private const val SHAPED_SET_SPAN_UPDATE_FONT_HASH = 2022725822L
        private val shapedSetSpanUpdateFontBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_set_span_update_font", SHAPED_SET_SPAN_UPDATE_FONT_HASH)
        }

        private const val SHAPED_GET_RUN_COUNT_HASH = 2198884583L
        private val shapedGetRunCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_count", SHAPED_GET_RUN_COUNT_HASH)
        }

        private const val SHAPED_GET_RUN_TEXT_HASH = 1464764419L
        private val shapedGetRunTextBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_text", SHAPED_GET_RUN_TEXT_HASH)
        }

        private const val SHAPED_GET_RUN_RANGE_HASH = 4069534484L
        private val shapedGetRunRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_range", SHAPED_GET_RUN_RANGE_HASH)
        }

        private const val SHAPED_GET_RUN_GLYPH_RANGE_HASH = 4069534484L
        private val shapedGetRunGlyphRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_glyph_range", SHAPED_GET_RUN_GLYPH_RANGE_HASH)
        }

        private const val SHAPED_GET_RUN_FONT_RID_HASH = 1066463050L
        private val shapedGetRunFontRidBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_font_rid", SHAPED_GET_RUN_FONT_RID_HASH)
        }

        private const val SHAPED_GET_RUN_FONT_SIZE_HASH = 1120910005L
        private val shapedGetRunFontSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_font_size", SHAPED_GET_RUN_FONT_SIZE_HASH)
        }

        private const val SHAPED_GET_RUN_LANGUAGE_HASH = 1464764419L
        private val shapedGetRunLanguageBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_language", SHAPED_GET_RUN_LANGUAGE_HASH)
        }

        private const val SHAPED_GET_RUN_DIRECTION_HASH = 2413896864L
        private val shapedGetRunDirectionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_direction", SHAPED_GET_RUN_DIRECTION_HASH)
        }

        private const val SHAPED_GET_RUN_OBJECT_HASH = 4069510997L
        private val shapedGetRunObjectBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_object", SHAPED_GET_RUN_OBJECT_HASH)
        }

        private const val SHAPED_TEXT_SUBSTR_HASH = 1937682086L
        private val shapedTextSubstrBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_substr", SHAPED_TEXT_SUBSTR_HASH)
        }

        private const val SHAPED_TEXT_GET_PARENT_HASH = 3814569979L
        private val shapedTextGetParentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_parent", SHAPED_TEXT_GET_PARENT_HASH)
        }

        private const val SHAPED_TEXT_FIT_TO_WIDTH_HASH = 530670926L
        private val shapedTextFitToWidthBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_fit_to_width", SHAPED_TEXT_FIT_TO_WIDTH_HASH)
        }

        private const val SHAPED_TEXT_TAB_ALIGN_HASH = 1283669550L
        private val shapedTextTabAlignBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_tab_align", SHAPED_TEXT_TAB_ALIGN_HASH)
        }

        private const val SHAPED_TEXT_SHAPE_HASH = 3521089500L
        private val shapedTextShapeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_shape", SHAPED_TEXT_SHAPE_HASH)
        }

        private const val SHAPED_TEXT_IS_READY_HASH = 4155700596L
        private val shapedTextIsReadyBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_is_ready", SHAPED_TEXT_IS_READY_HASH)
        }

        private const val SHAPED_TEXT_HAS_VISIBLE_CHARS_HASH = 4155700596L
        private val shapedTextHasVisibleCharsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_has_visible_chars", SHAPED_TEXT_HAS_VISIBLE_CHARS_HASH)
        }

        private const val SHAPED_TEXT_GET_GLYPHS_HASH = 2684255073L
        private val shapedTextGetGlyphsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_glyphs", SHAPED_TEXT_GET_GLYPHS_HASH)
        }

        private const val SHAPED_TEXT_SORT_LOGICAL_HASH = 2670461153L
        private val shapedTextSortLogicalBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_sort_logical", SHAPED_TEXT_SORT_LOGICAL_HASH)
        }

        private const val SHAPED_TEXT_GET_GLYPH_COUNT_HASH = 2198884583L
        private val shapedTextGetGlyphCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_glyph_count", SHAPED_TEXT_GET_GLYPH_COUNT_HASH)
        }

        private const val SHAPED_TEXT_GET_RANGE_HASH = 733700038L
        private val shapedTextGetRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_range", SHAPED_TEXT_GET_RANGE_HASH)
        }

        private const val SHAPED_TEXT_GET_LINE_BREAKS_ADV_HASH = 2376991424L
        private val shapedTextGetLineBreaksAdvBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_line_breaks_adv", SHAPED_TEXT_GET_LINE_BREAKS_ADV_HASH)
        }

        private const val SHAPED_TEXT_GET_LINE_BREAKS_HASH = 2651359741L
        private val shapedTextGetLineBreaksBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_line_breaks", SHAPED_TEXT_GET_LINE_BREAKS_HASH)
        }

        private const val SHAPED_TEXT_GET_WORD_BREAKS_HASH = 4099476853L
        private val shapedTextGetWordBreaksBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_word_breaks", SHAPED_TEXT_GET_WORD_BREAKS_HASH)
        }

        private const val SHAPED_TEXT_GET_TRIM_POS_HASH = 2198884583L
        private val shapedTextGetTrimPosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_trim_pos", SHAPED_TEXT_GET_TRIM_POS_HASH)
        }

        private const val SHAPED_TEXT_GET_ELLIPSIS_POS_HASH = 2198884583L
        private val shapedTextGetEllipsisPosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_ellipsis_pos", SHAPED_TEXT_GET_ELLIPSIS_POS_HASH)
        }

        private const val SHAPED_TEXT_GET_ELLIPSIS_GLYPHS_HASH = 2684255073L
        private val shapedTextGetEllipsisGlyphsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_ellipsis_glyphs", SHAPED_TEXT_GET_ELLIPSIS_GLYPHS_HASH)
        }

        private const val SHAPED_TEXT_GET_ELLIPSIS_GLYPH_COUNT_HASH = 2198884583L
        private val shapedTextGetEllipsisGlyphCountBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_ellipsis_glyph_count", SHAPED_TEXT_GET_ELLIPSIS_GLYPH_COUNT_HASH)
        }

        private const val SHAPED_TEXT_OVERRUN_TRIM_TO_WIDTH_HASH = 2723146520L
        private val shapedTextOverrunTrimToWidthBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_overrun_trim_to_width", SHAPED_TEXT_OVERRUN_TRIM_TO_WIDTH_HASH)
        }

        private const val SHAPED_TEXT_GET_OBJECTS_HASH = 2684255073L
        private val shapedTextGetObjectsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_objects", SHAPED_TEXT_GET_OBJECTS_HASH)
        }

        private const val SHAPED_TEXT_GET_OBJECT_RECT_HASH = 447978354L
        private val shapedTextGetObjectRectBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_object_rect", SHAPED_TEXT_GET_OBJECT_RECT_HASH)
        }

        private const val SHAPED_TEXT_GET_OBJECT_RANGE_HASH = 2524675647L
        private val shapedTextGetObjectRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_object_range", SHAPED_TEXT_GET_OBJECT_RANGE_HASH)
        }

        private const val SHAPED_TEXT_GET_OBJECT_GLYPH_HASH = 1260085030L
        private val shapedTextGetObjectGlyphBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_object_glyph", SHAPED_TEXT_GET_OBJECT_GLYPH_HASH)
        }

        private const val SHAPED_TEXT_GET_SIZE_HASH = 2440833711L
        private val shapedTextGetSizeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_size", SHAPED_TEXT_GET_SIZE_HASH)
        }

        private const val SHAPED_TEXT_GET_ASCENT_HASH = 866169185L
        private val shapedTextGetAscentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_ascent", SHAPED_TEXT_GET_ASCENT_HASH)
        }

        private const val SHAPED_TEXT_GET_DESCENT_HASH = 866169185L
        private val shapedTextGetDescentBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_descent", SHAPED_TEXT_GET_DESCENT_HASH)
        }

        private const val SHAPED_TEXT_GET_WIDTH_HASH = 866169185L
        private val shapedTextGetWidthBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_width", SHAPED_TEXT_GET_WIDTH_HASH)
        }

        private const val SHAPED_TEXT_GET_UNDERLINE_POSITION_HASH = 866169185L
        private val shapedTextGetUnderlinePositionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_underline_position", SHAPED_TEXT_GET_UNDERLINE_POSITION_HASH)
        }

        private const val SHAPED_TEXT_GET_UNDERLINE_THICKNESS_HASH = 866169185L
        private val shapedTextGetUnderlineThicknessBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_underline_thickness", SHAPED_TEXT_GET_UNDERLINE_THICKNESS_HASH)
        }

        private const val SHAPED_TEXT_GET_CARETS_HASH = 1574219346L
        private val shapedTextGetCaretsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_carets", SHAPED_TEXT_GET_CARETS_HASH)
        }

        private const val SHAPED_TEXT_GET_SELECTION_HASH = 3714187733L
        private val shapedTextGetSelectionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_selection", SHAPED_TEXT_GET_SELECTION_HASH)
        }

        private const val SHAPED_TEXT_HIT_TEST_GRAPHEME_HASH = 3149310417L
        private val shapedTextHitTestGraphemeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_hit_test_grapheme", SHAPED_TEXT_HIT_TEST_GRAPHEME_HASH)
        }

        private const val SHAPED_TEXT_HIT_TEST_POSITION_HASH = 3149310417L
        private val shapedTextHitTestPositionBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_hit_test_position", SHAPED_TEXT_HIT_TEST_POSITION_HASH)
        }

        private const val SHAPED_TEXT_GET_GRAPHEME_BOUNDS_HASH = 2546185844L
        private val shapedTextGetGraphemeBoundsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_grapheme_bounds", SHAPED_TEXT_GET_GRAPHEME_BOUNDS_HASH)
        }

        private const val SHAPED_TEXT_NEXT_GRAPHEME_POS_HASH = 1120910005L
        private val shapedTextNextGraphemePosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_next_grapheme_pos", SHAPED_TEXT_NEXT_GRAPHEME_POS_HASH)
        }

        private const val SHAPED_TEXT_PREV_GRAPHEME_POS_HASH = 1120910005L
        private val shapedTextPrevGraphemePosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_prev_grapheme_pos", SHAPED_TEXT_PREV_GRAPHEME_POS_HASH)
        }

        private const val SHAPED_TEXT_GET_CHARACTER_BREAKS_HASH = 788230395L
        private val shapedTextGetCharacterBreaksBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_character_breaks", SHAPED_TEXT_GET_CHARACTER_BREAKS_HASH)
        }

        private const val SHAPED_TEXT_NEXT_CHARACTER_POS_HASH = 1120910005L
        private val shapedTextNextCharacterPosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_next_character_pos", SHAPED_TEXT_NEXT_CHARACTER_POS_HASH)
        }

        private const val SHAPED_TEXT_PREV_CHARACTER_POS_HASH = 1120910005L
        private val shapedTextPrevCharacterPosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_prev_character_pos", SHAPED_TEXT_PREV_CHARACTER_POS_HASH)
        }

        private const val SHAPED_TEXT_CLOSEST_CHARACTER_POS_HASH = 1120910005L
        private val shapedTextClosestCharacterPosBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_closest_character_pos", SHAPED_TEXT_CLOSEST_CHARACTER_POS_HASH)
        }

        private const val SHAPED_TEXT_DRAW_HASH = 1647687596L
        private val shapedTextDrawBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_draw", SHAPED_TEXT_DRAW_HASH)
        }

        private const val SHAPED_TEXT_DRAW_OUTLINE_HASH = 1217146601L
        private val shapedTextDrawOutlineBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_draw_outline", SHAPED_TEXT_DRAW_OUTLINE_HASH)
        }

        private const val SHAPED_TEXT_GET_DOMINANT_DIRECTION_IN_RANGE_HASH = 3326907668L
        private val shapedTextGetDominantDirectionInRangeBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_dominant_direction_in_range", SHAPED_TEXT_GET_DOMINANT_DIRECTION_IN_RANGE_HASH)
        }

        private const val FORMAT_NUMBER_HASH = 2664628024L
        private val formatNumberBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "format_number", FORMAT_NUMBER_HASH)
        }

        private const val PARSE_NUMBER_HASH = 2664628024L
        private val parseNumberBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "parse_number", PARSE_NUMBER_HASH)
        }

        private const val PERCENT_SIGN_HASH = 993269549L
        private val percentSignBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "percent_sign", PERCENT_SIGN_HASH)
        }

        private const val STRING_GET_WORD_BREAKS_HASH = 581857818L
        private val stringGetWordBreaksBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "string_get_word_breaks", STRING_GET_WORD_BREAKS_HASH)
        }

        private const val STRING_GET_CHARACTER_BREAKS_HASH = 2333794773L
        private val stringGetCharacterBreaksBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "string_get_character_breaks", STRING_GET_CHARACTER_BREAKS_HASH)
        }

        private const val IS_CONFUSABLE_HASH = 1433197768L
        private val isConfusableBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "is_confusable", IS_CONFUSABLE_HASH)
        }

        private const val SPOOF_CHECK_HASH = 3927539163L
        private val spoofCheckBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "spoof_check", SPOOF_CHECK_HASH)
        }

        private const val STRIP_DIACRITICS_HASH = 3135753539L
        private val stripDiacriticsBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "strip_diacritics", STRIP_DIACRITICS_HASH)
        }

        private const val IS_VALID_IDENTIFIER_HASH = 3927539163L
        private val isValidIdentifierBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "is_valid_identifier", IS_VALID_IDENTIFIER_HASH)
        }

        private const val IS_VALID_LETTER_HASH = 1116898809L
        private val isValidLetterBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "is_valid_letter", IS_VALID_LETTER_HASH)
        }

        private const val STRING_TO_UPPER_HASH = 2664628024L
        private val stringToUpperBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "string_to_upper", STRING_TO_UPPER_HASH)
        }

        private const val STRING_TO_LOWER_HASH = 2664628024L
        private val stringToLowerBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "string_to_lower", STRING_TO_LOWER_HASH)
        }

        private const val STRING_TO_TITLE_HASH = 2664628024L
        private val stringToTitleBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "string_to_title", STRING_TO_TITLE_HASH)
        }

        private const val PARSE_STRUCTURED_TEXT_HASH = 3310685015L
        private val parseStructuredTextBind by lazy {
            ObjectCalls.getMethodBind("TextServer", "parse_structured_text", PARSE_STRUCTURED_TEXT_HASH)
        }
    }
}
