package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
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
open class TextServer(handle: GodotHandle) : RefCounted(handle) {
    /**
     * Returns `true` if the server supports a feature.
     *
     * Generated from Godot docs: TextServer.has_feature
     */
    fun hasFeature(feature: TextServer.Feature): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.hasFeatureBind, segment, feature.value)
    }

    /**
     * Returns the name of the server interface.
     *
     * Generated from Godot docs: TextServer.get_name
     */
    fun getName(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getNameBind, segment)
    }

    /**
     * Returns text server features, see `Feature`.
     *
     * Generated from Godot docs: TextServer.get_features
     */
    fun getFeatures(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(Binds.getFeaturesBind, segment)
    }

    /**
     * Loads optional TextServer database (e.g. ICU break iterators and dictionaries). Note: This
     * function should be called before any other TextServer functions used, otherwise it won't have
     * any effect.
     *
     * Generated from Godot docs: TextServer.load_support_data
     */
    fun loadSupportData(filename: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.loadSupportDataBind, segment, filename)
    }

    /**
     * Returns default TextServer database (e.g. ICU break iterators and dictionaries) filename.
     *
     * Generated from Godot docs: TextServer.get_support_data_filename
     */
    fun getSupportDataFilename(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getSupportDataFilenameBind, segment)
    }

    /**
     * Returns TextServer database (e.g. ICU break iterators and dictionaries) description.
     *
     * Generated from Godot docs: TextServer.get_support_data_info
     */
    fun getSupportDataInfo(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetString(Binds.getSupportDataInfoBind, segment)
    }

    /**
     * Saves optional TextServer database (e.g. ICU break iterators and dictionaries) to the file.
     * Note: This function is used by during project export, to include TextServer database.
     *
     * Generated from Godot docs: TextServer.save_support_data
     */
    fun saveSupportData(filename: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.saveSupportDataBind, segment, filename)
    }

    /**
     * Returns default TextServer database (e.g. ICU break iterators and dictionaries).
     *
     * Generated from Godot docs: TextServer.get_support_data
     */
    fun getSupportData(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(Binds.getSupportDataBind, segment)
    }

    /**
     * Returns `true` if the locale requires text server support data for line/word breaking.
     *
     * Generated from Godot docs: TextServer.is_locale_using_support_data
     */
    fun isLocaleUsingSupportData(locale: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.isLocaleUsingSupportDataBind, segment, locale)
    }

    /**
     * Returns `true` if locale is right-to-left.
     *
     * Generated from Godot docs: TextServer.is_locale_right_to_left
     */
    fun isLocaleRightToLeft(locale: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.isLocaleRightToLeftBind, segment, locale)
    }

    /**
     * Converts the given readable name of a feature, variation, script, or language to an OpenType
     * tag.
     *
     * Generated from Godot docs: TextServer.name_to_tag
     */
    fun nameToTag(name: String): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetLong(Binds.nameToTagBind, segment, name)
    }

    /**
     * Converts the given OpenType tag to the readable name of a feature, variation, script, or
     * language.
     *
     * Generated from Godot docs: TextServer.tag_to_name
     */
    fun tagToName(tag: Long): String {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetString(Binds.tagToNameBind, segment, tag)
    }

    /**
     * Returns `true` if `rid` is valid resource owned by this text server.
     *
     * Generated from Godot docs: TextServer.has
     */
    fun has(rid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.hasBind, segment, rid)
    }

    /**
     * Frees an object created by this `TextServer`.
     *
     * Generated from Godot docs: TextServer.free_rid
     */
    fun freeRid(rid: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.freeRidBind, segment, rid)
    }

    /**
     * Creates a new, empty font cache entry resource. To free the resulting resource, use the
     * `free_rid` method.
     *
     * Generated from Godot docs: TextServer.create_font
     */
    fun createFont(): RID {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.createFontBind, segment)
    }

    /**
     * Creates a new variation existing font which is reusing the same glyph cache and font data. To
     * free the resulting resource, use the `free_rid` method.
     *
     * Generated from Godot docs: TextServer.create_font_linked_variation
     */
    fun createFontLinkedVariation(fontRid: RID): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetRID(Binds.createFontLinkedVariationBind, segment, fontRid)
    }

    /**
     * Sets font source data, e.g contents of the dynamic font source file.
     *
     * Generated from Godot docs: TextServer.font_set_data
     */
    fun fontSetData(fontRid: RID, data: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndByteArrayArg(Binds.fontSetDataBind, segment, fontRid, data)
    }

    /**
     * Sets an active face index in the TrueType / OpenType collection.
     *
     * Generated from Godot docs: TextServer.font_set_face_index
     */
    fun fontSetFaceIndex(fontRid: RID, faceIndex: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.fontSetFaceIndexBind, segment, fontRid, faceIndex)
    }

    /**
     * Returns an active face index in the TrueType / OpenType collection.
     *
     * Generated from Godot docs: TextServer.font_get_face_index
     */
    fun fontGetFaceIndex(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetFaceIndexBind, segment, fontRid)
    }

    /**
     * Returns number of faces in the TrueType / OpenType collection.
     *
     * Generated from Godot docs: TextServer.font_get_face_count
     */
    fun fontGetFaceCount(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetFaceCountBind, segment, fontRid)
    }

    /**
     * Sets the font style flags. Note: This value is used for font matching only and will not affect
     * font rendering. Use `font_set_face_index`, `font_set_variation_coordinates`,
     * `font_set_embolden`, or `font_set_transform` instead.
     *
     * Generated from Godot docs: TextServer.font_set_style
     */
    fun fontSetStyle(fontRid: RID, style: TextServer.FontStyle) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.fontSetStyleBind, segment, fontRid, style.value)
    }

    /**
     * Returns font style flags.
     *
     * Generated from Godot docs: TextServer.font_get_style
     */
    fun fontGetStyle(fontRid: RID): TextServer.FontStyle {
        checkOpen()
        return TextServer.FontStyle(ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetStyleBind, segment, fontRid))
    }

    /**
     * Sets the font family name.
     *
     * Generated from Godot docs: TextServer.font_set_name
     */
    fun fontSetName(fontRid: RID, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndStringArg(Binds.fontSetNameBind, segment, fontRid, name)
    }

    /**
     * Returns font family name.
     *
     * Generated from Godot docs: TextServer.font_get_name
     */
    fun fontGetName(fontRid: RID): String {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetString(Binds.fontGetNameBind, segment, fontRid)
    }

    /**
     * Returns `Dictionary` with OpenType font name strings (localized font names, version,
     * description, license information, sample text, etc.).
     *
     * Generated from Godot docs: TextServer.font_get_ot_name_strings
     */
    fun fontGetOtNameStrings(fontRid: RID): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDictionary(Binds.fontGetOtNameStringsBind, segment, fontRid)
    }

    /**
     * Sets the font style name.
     *
     * Generated from Godot docs: TextServer.font_set_style_name
     */
    fun fontSetStyleName(fontRid: RID, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndStringArg(Binds.fontSetStyleNameBind, segment, fontRid, name)
    }

    /**
     * Returns font style name.
     *
     * Generated from Godot docs: TextServer.font_get_style_name
     */
    fun fontGetStyleName(fontRid: RID): String {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetString(Binds.fontGetStyleNameBind, segment, fontRid)
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
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.fontSetWeightBind, segment, fontRid, weight)
    }

    /**
     * Returns weight (boldness) of the font. A value in the `100...999` range, normal font weight is
     * `400`, bold font weight is `700`.
     *
     * Generated from Godot docs: TextServer.font_get_weight
     */
    fun fontGetWeight(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetWeightBind, segment, fontRid)
    }

    /**
     * Sets font stretch amount, compared to a normal width. A percentage value between `50%` and
     * `200%`. Note: This value is used for font matching only and will not affect font rendering. Use
     * `font_set_face_index`, `font_set_variation_coordinates`, or `font_set_transform` instead.
     *
     * Generated from Godot docs: TextServer.font_set_stretch
     */
    fun fontSetStretch(fontRid: RID, weight: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.fontSetStretchBind, segment, fontRid, weight)
    }

    /**
     * Returns font stretch amount, compared to a normal width. A percentage value between `50%` and
     * `200%`.
     *
     * Generated from Godot docs: TextServer.font_get_stretch
     */
    fun fontGetStretch(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetStretchBind, segment, fontRid)
    }

    /**
     * Sets font anti-aliasing mode.
     *
     * Generated from Godot docs: TextServer.font_set_antialiasing
     */
    fun fontSetAntialiasing(fontRid: RID, antialiasing: TextServer.FontAntialiasing) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.fontSetAntialiasingBind, segment, fontRid, antialiasing.value)
    }

    /**
     * Returns font anti-aliasing mode.
     *
     * Generated from Godot docs: TextServer.font_get_antialiasing
     */
    fun fontGetAntialiasing(fontRid: RID): TextServer.FontAntialiasing {
        checkOpen()
        return TextServer.FontAntialiasing(ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetAntialiasingBind, segment, fontRid))
    }

    /**
     * If set to `true`, embedded font bitmap loading is disabled (bitmap-only and color fonts ignore
     * this property).
     *
     * Generated from Godot docs: TextServer.font_set_disable_embedded_bitmaps
     */
    fun fontSetDisableEmbeddedBitmaps(fontRid: RID, disableEmbeddedBitmaps: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.fontSetDisableEmbeddedBitmapsBind, segment, fontRid, disableEmbeddedBitmaps)
    }

    /**
     * Returns whether the font's embedded bitmap loading is disabled.
     *
     * Generated from Godot docs: TextServer.font_get_disable_embedded_bitmaps
     */
    fun fontGetDisableEmbeddedBitmaps(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.fontGetDisableEmbeddedBitmapsBind, segment, fontRid)
    }

    /**
     * If set to `true` font texture mipmap generation is enabled.
     *
     * Generated from Godot docs: TextServer.font_set_generate_mipmaps
     */
    fun fontSetGenerateMipmaps(fontRid: RID, generateMipmaps: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.fontSetGenerateMipmapsBind, segment, fontRid, generateMipmaps)
    }

    /**
     * Returns `true` if font texture mipmap generation is enabled.
     *
     * Generated from Godot docs: TextServer.font_get_generate_mipmaps
     */
    fun fontGetGenerateMipmaps(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.fontGetGenerateMipmapsBind, segment, fontRid)
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
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.fontSetMultichannelSignedDistanceFieldBind, segment, fontRid, msdf)
    }

    /**
     * Returns `true` if glyphs of all sizes are rendered using single multichannel signed distance
     * field generated from the dynamic font vector data.
     *
     * Generated from Godot docs: TextServer.font_is_multichannel_signed_distance_field
     */
    fun fontIsMultichannelSignedDistanceField(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.fontIsMultichannelSignedDistanceFieldBind, segment, fontRid)
    }

    /**
     * Sets the width of the range around the shape between the minimum and maximum representable
     * signed distance.
     *
     * Generated from Godot docs: TextServer.font_set_msdf_pixel_range
     */
    fun fontSetMsdfPixelRange(fontRid: RID, msdfPixelRange: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.fontSetMsdfPixelRangeBind, segment, fontRid, msdfPixelRange)
    }

    /**
     * Returns the width of the range around the shape between the minimum and maximum representable
     * signed distance.
     *
     * Generated from Godot docs: TextServer.font_get_msdf_pixel_range
     */
    fun fontGetMsdfPixelRange(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetMsdfPixelRangeBind, segment, fontRid)
    }

    /**
     * Sets source font size used to generate MSDF textures.
     *
     * Generated from Godot docs: TextServer.font_set_msdf_size
     */
    fun fontSetMsdfSize(fontRid: RID, msdfSize: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.fontSetMsdfSizeBind, segment, fontRid, msdfSize)
    }

    /**
     * Returns source font size used to generate MSDF textures.
     *
     * Generated from Godot docs: TextServer.font_get_msdf_size
     */
    fun fontGetMsdfSize(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetMsdfSizeBind, segment, fontRid)
    }

    /**
     * Sets bitmap font fixed size. If set to value greater than zero, same cache entry will be used
     * for all font sizes.
     *
     * Generated from Godot docs: TextServer.font_set_fixed_size
     */
    fun fontSetFixedSize(fontRid: RID, fixedSize: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.fontSetFixedSizeBind, segment, fontRid, fixedSize)
    }

    /**
     * Returns bitmap font fixed size.
     *
     * Generated from Godot docs: TextServer.font_get_fixed_size
     */
    fun fontGetFixedSize(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetFixedSizeBind, segment, fontRid)
    }

    /**
     * Sets bitmap font scaling mode. This property is used only if `fixed_size` is greater than zero.
     *
     * Generated from Godot docs: TextServer.font_set_fixed_size_scale_mode
     */
    fun fontSetFixedSizeScaleMode(fontRid: RID, fixedSizeScaleMode: TextServer.FixedSizeScaleMode) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.fontSetFixedSizeScaleModeBind, segment, fontRid, fixedSizeScaleMode.value)
    }

    /**
     * Returns bitmap font scaling mode.
     *
     * Generated from Godot docs: TextServer.font_get_fixed_size_scale_mode
     */
    fun fontGetFixedSizeScaleMode(fontRid: RID): TextServer.FixedSizeScaleMode {
        checkOpen()
        return TextServer.FixedSizeScaleMode(ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetFixedSizeScaleModeBind, segment, fontRid))
    }

    /**
     * If set to `true`, system fonts can be automatically used as fallbacks.
     *
     * Generated from Godot docs: TextServer.font_set_allow_system_fallback
     */
    fun fontSetAllowSystemFallback(fontRid: RID, allowSystemFallback: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.fontSetAllowSystemFallbackBind, segment, fontRid, allowSystemFallback)
    }

    /**
     * Returns `true` if system fonts can be automatically used as fallbacks.
     *
     * Generated from Godot docs: TextServer.font_is_allow_system_fallback
     */
    fun fontIsAllowSystemFallback(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.fontIsAllowSystemFallbackBind, segment, fontRid)
    }

    /**
     * Frees all automatically loaded system fonts.
     *
     * Generated from Godot docs: TextServer.font_clear_system_fallback_cache
     */
    fun fontClearSystemFallbackCache() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.fontClearSystemFallbackCacheBind, segment)
    }

    /**
     * If set to `true` auto-hinting is preferred over font built-in hinting.
     *
     * Generated from Godot docs: TextServer.font_set_force_autohinter
     */
    fun fontSetForceAutohinter(fontRid: RID, forceAutohinter: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.fontSetForceAutohinterBind, segment, fontRid, forceAutohinter)
    }

    /**
     * Returns `true` if auto-hinting is supported and preferred over font built-in hinting. Used by
     * dynamic fonts only.
     *
     * Generated from Godot docs: TextServer.font_is_force_autohinter
     */
    fun fontIsForceAutohinter(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.fontIsForceAutohinterBind, segment, fontRid)
    }

    /**
     * If set to `true`, color modulation is applied when drawing colored glyphs, otherwise it's
     * applied to the monochrome glyphs only.
     *
     * Generated from Godot docs: TextServer.font_set_modulate_color_glyphs
     */
    fun fontSetModulateColorGlyphs(fontRid: RID, modulate: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.fontSetModulateColorGlyphsBind, segment, fontRid, modulate)
    }

    /**
     * Returns `true` if color modulation is applied when drawing the font's colored glyphs.
     *
     * Generated from Godot docs: TextServer.font_is_modulate_color_glyphs
     */
    fun fontIsModulateColorGlyphs(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.fontIsModulateColorGlyphsBind, segment, fontRid)
    }

    /**
     * Returns the number of predefined color palettes. Palette contains all colors used to render font
     * glyphs. Each palette has the same number of colors.
     *
     * Generated from Godot docs: TextServer.font_get_palette_count
     */
    fun fontGetPaletteCount(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetPaletteCountBind, segment, fontRid)
    }

    /**
     * Returns the name of the predefined color palette at `index`. Palette contains all colors used to
     * render font glyphs. Each palette has the same number of colors.
     *
     * Generated from Godot docs: TextServer.font_get_palette_name
     */
    fun fontGetPaletteName(fontRid: RID, index: Long): String {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetString(Binds.fontGetPaletteNameBind, segment, fontRid, index)
    }

    /**
     * Returns the array in the predefined color palette at `index`. Palette contains all colors used
     * to render font glyphs. Each palette has the same number of colors. Colors can be overridden
     * using `font_set_palette_custom_colors`.
     *
     * Generated from Godot docs: TextServer.font_get_palette_colors
     */
    fun fontGetPaletteColors(fontRid: RID, index: Long): List<Color> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedColorList(Binds.fontGetPaletteColorsBind, segment, fontRid, index)
    }

    /**
     * Sets array of custom colors to override predefined palette. Set to empty array to reset
     * overrides. Use `Color(0, 0, 0, 0)`, to keep predefined palette color at specific position.
     *
     * Generated from Godot docs: TextServer.font_set_palette_custom_colors
     */
    fun fontSetPaletteCustomColors(fontRid: RID, colors: List<Color>) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndPackedColorListArgs(Binds.fontSetPaletteCustomColorsBind, segment, fontRid, colors)
    }

    /**
     * Returns array of custom colors to override predefined palette.
     *
     * Generated from Godot docs: TextServer.font_get_palette_custom_colors
     */
    fun fontGetPaletteCustomColors(fontRid: RID): List<Color> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetPackedColorList(Binds.fontGetPaletteCustomColorsBind, segment, fontRid)
    }

    /**
     * Returns used palette index.
     *
     * Generated from Godot docs: TextServer.font_get_used_palette
     */
    fun fontGetUsedPalette(fontRid: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetUsedPaletteBind, segment, fontRid)
    }

    /**
     * Sets used palette index.
     *
     * Generated from Godot docs: TextServer.font_set_used_palette
     */
    fun fontSetUsedPalette(fontRid: RID, index: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.fontSetUsedPaletteBind, segment, fontRid, index)
    }

    /**
     * Sets font hinting mode. Used by dynamic fonts only.
     *
     * Generated from Godot docs: TextServer.font_set_hinting
     */
    fun fontSetHinting(fontRid: RID, hinting: TextServer.Hinting) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.fontSetHintingBind, segment, fontRid, hinting.value)
    }

    /**
     * Returns the font hinting mode. Used by dynamic fonts only.
     *
     * Generated from Godot docs: TextServer.font_get_hinting
     */
    fun fontGetHinting(fontRid: RID): TextServer.Hinting {
        checkOpen()
        return TextServer.Hinting(ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetHintingBind, segment, fontRid))
    }

    /**
     * Sets font subpixel glyph positioning mode.
     *
     * Generated from Godot docs: TextServer.font_set_subpixel_positioning
     */
    fun fontSetSubpixelPositioning(fontRid: RID, subpixelPositioning: TextServer.SubpixelPositioning) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.fontSetSubpixelPositioningBind, segment, fontRid, subpixelPositioning.value)
    }

    /**
     * Returns font subpixel glyph positioning mode.
     *
     * Generated from Godot docs: TextServer.font_get_subpixel_positioning
     */
    fun fontGetSubpixelPositioning(fontRid: RID): TextServer.SubpixelPositioning {
        checkOpen()
        return TextServer.SubpixelPositioning(ObjectCalls.ptrcallWithRIDArgRetLong(Binds.fontGetSubpixelPositioningBind, segment, fontRid))
    }

    /**
     * Sets glyph position rounding behavior. If set to `true`, when aligning glyphs to the pixel
     * boundaries rounding remainders are accumulated to ensure more uniform glyph distribution. This
     * setting has no effect if subpixel positioning is enabled.
     *
     * Generated from Godot docs: TextServer.font_set_keep_rounding_remainders
     */
    fun fontSetKeepRoundingRemainders(fontRid: RID, keepRoundingRemainders: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.fontSetKeepRoundingRemaindersBind, segment, fontRid, keepRoundingRemainders)
    }

    /**
     * Returns glyph position rounding behavior. If set to `true`, when aligning glyphs to the pixel
     * boundaries rounding remainders are accumulated to ensure more uniform glyph distribution. This
     * setting has no effect if subpixel positioning is enabled.
     *
     * Generated from Godot docs: TextServer.font_get_keep_rounding_remainders
     */
    fun fontGetKeepRoundingRemainders(fontRid: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.fontGetKeepRoundingRemaindersBind, segment, fontRid)
    }

    /**
     * Sets font embolden strength. If `strength` is not equal to zero, emboldens the font outlines.
     * Negative values reduce the outline thickness.
     *
     * Generated from Godot docs: TextServer.font_set_embolden
     */
    fun fontSetEmbolden(fontRid: RID, strength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.fontSetEmboldenBind, segment, fontRid, strength)
    }

    /**
     * Returns font embolden strength.
     *
     * Generated from Godot docs: TextServer.font_get_embolden
     */
    fun fontGetEmbolden(fontRid: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.fontGetEmboldenBind, segment, fontRid)
    }

    /**
     * Sets the spacing for `spacing` to `value` in pixels (not relative to the font size).
     *
     * Generated from Godot docs: TextServer.font_set_spacing
     */
    fun fontSetSpacing(fontRid: RID, spacing: TextServer.SpacingType, value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndTwoLongArgs(Binds.fontSetSpacingBind, segment, fontRid, spacing.value, value)
    }

    /**
     * Returns the spacing for `spacing` in pixels (not relative to the font size).
     *
     * Generated from Godot docs: TextServer.font_get_spacing
     */
    fun fontGetSpacing(fontRid: RID, spacing: TextServer.SpacingType): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(Binds.fontGetSpacingBind, segment, fontRid, spacing.value)
    }

    /**
     * Sets extra baseline offset (as a fraction of font height).
     *
     * Generated from Godot docs: TextServer.font_set_baseline_offset
     */
    fun fontSetBaselineOffset(fontRid: RID, baselineOffset: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.fontSetBaselineOffsetBind, segment, fontRid, baselineOffset)
    }

    /**
     * Returns extra baseline offset (as a fraction of font height).
     *
     * Generated from Godot docs: TextServer.font_get_baseline_offset
     */
    fun fontGetBaselineOffset(fontRid: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.fontGetBaselineOffsetBind, segment, fontRid)
    }

    /**
     * Sets 2D transform, applied to the font outlines, can be used for slanting, flipping, and
     * rotating glyphs. For example, to simulate italic typeface by slanting, apply the following
     * transform `Transform2D(1.0, slant, 0.0, 1.0, 0.0, 0.0)`.
     *
     * Generated from Godot docs: TextServer.font_set_transform
     */
    fun fontSetTransform(fontRid: RID, transform: Transform2D) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndTransform2DArg(Binds.fontSetTransformBind, segment, fontRid, transform)
    }

    /**
     * Returns 2D transform applied to the font outlines.
     *
     * Generated from Godot docs: TextServer.font_get_transform
     */
    fun fontGetTransform(fontRid: RID): Transform2D {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetTransform2D(Binds.fontGetTransformBind, segment, fontRid)
    }

    /**
     * Sets variation coordinates for the specified font cache entry. See
     * `font_supported_variation_list` for more info.
     *
     * Generated from Godot docs: TextServer.font_set_variation_coordinates
     */
    fun fontSetVariationCoordinates(fontRid: RID, variationCoordinates: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndDictionaryArg(Binds.fontSetVariationCoordinatesBind, segment, fontRid, variationCoordinates)
    }

    /**
     * Returns variation coordinates for the specified font cache entry. See
     * `font_supported_variation_list` for more info.
     *
     * Generated from Godot docs: TextServer.font_get_variation_coordinates
     */
    fun fontGetVariationCoordinates(fontRid: RID): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDictionary(Binds.fontGetVariationCoordinatesBind, segment, fontRid)
    }

    /**
     * If set to a positive value, overrides the oversampling factor of the viewport this font is used
     * in. See `Viewport.oversampling`. This value doesn't override the `oversampling` parameter of
     * `draw_*` methods. Used by dynamic fonts only.
     *
     * Generated from Godot docs: TextServer.font_set_oversampling
     */
    fun fontSetOversampling(fontRid: RID, oversampling: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndDoubleArg(Binds.fontSetOversamplingBind, segment, fontRid, oversampling)
    }

    /**
     * Returns oversampling factor override. If set to a positive value, overrides the oversampling
     * factor of the viewport this font is used in. See `Viewport.oversampling`. This value doesn't
     * override the `oversampling` parameter of `draw_*` methods. Used by dynamic fonts only.
     *
     * Generated from Godot docs: TextServer.font_get_oversampling
     */
    fun fontGetOversampling(fontRid: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.fontGetOversamplingBind, segment, fontRid)
    }

    /**
     * Returns list of the font sizes in the cache. Each size is `Vector2i` with font size and outline
     * size.
     *
     * Generated from Godot docs: TextServer.font_get_size_cache_list
     */
    fun fontGetSizeCacheList(fontRid: RID): List<Vector2i> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetVector2iList(Binds.fontGetSizeCacheListBind, segment, fontRid)
    }

    /**
     * Removes all font sizes from the cache entry.
     *
     * Generated from Godot docs: TextServer.font_clear_size_cache
     */
    fun fontClearSizeCache(fontRid: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.fontClearSizeCacheBind, segment, fontRid)
    }

    /**
     * Removes specified font size from the cache entry.
     *
     * Generated from Godot docs: TextServer.font_remove_size_cache
     */
    fun fontRemoveSizeCache(fontRid: RID, size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndVector2iArg(Binds.fontRemoveSizeCacheBind, segment, fontRid, size)
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
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(Binds.fontGetSizeCacheInfoBind, segment, fontRid)
    }

    /**
     * Sets the font ascent (number of pixels above the baseline).
     *
     * Generated from Godot docs: TextServer.font_set_ascent
     */
    fun fontSetAscent(fontRid: RID, size: Long, ascent: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(Binds.fontSetAscentBind, segment, fontRid, size, ascent)
    }

    /**
     * Returns the font ascent (number of pixels above the baseline).
     *
     * Generated from Godot docs: TextServer.font_get_ascent
     */
    fun fontGetAscent(fontRid: RID, size: Long): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(Binds.fontGetAscentBind, segment, fontRid, size)
    }

    /**
     * Sets the font descent (number of pixels below the baseline).
     *
     * Generated from Godot docs: TextServer.font_set_descent
     */
    fun fontSetDescent(fontRid: RID, size: Long, descent: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(Binds.fontSetDescentBind, segment, fontRid, size, descent)
    }

    /**
     * Returns the font descent (number of pixels below the baseline).
     *
     * Generated from Godot docs: TextServer.font_get_descent
     */
    fun fontGetDescent(fontRid: RID, size: Long): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(Binds.fontGetDescentBind, segment, fontRid, size)
    }

    /**
     * Sets pixel offset of the underline below the baseline.
     *
     * Generated from Godot docs: TextServer.font_set_underline_position
     */
    fun fontSetUnderlinePosition(fontRid: RID, size: Long, underlinePosition: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(Binds.fontSetUnderlinePositionBind, segment, fontRid, size, underlinePosition)
    }

    /**
     * Returns pixel offset of the underline below the baseline.
     *
     * Generated from Godot docs: TextServer.font_get_underline_position
     */
    fun fontGetUnderlinePosition(fontRid: RID, size: Long): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(Binds.fontGetUnderlinePositionBind, segment, fontRid, size)
    }

    /**
     * Sets thickness of the underline in pixels.
     *
     * Generated from Godot docs: TextServer.font_set_underline_thickness
     */
    fun fontSetUnderlineThickness(fontRid: RID, size: Long, underlineThickness: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(Binds.fontSetUnderlineThicknessBind, segment, fontRid, size, underlineThickness)
    }

    /**
     * Returns thickness of the underline in pixels.
     *
     * Generated from Godot docs: TextServer.font_get_underline_thickness
     */
    fun fontGetUnderlineThickness(fontRid: RID, size: Long): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(Binds.fontGetUnderlineThicknessBind, segment, fontRid, size)
    }

    /**
     * Sets scaling factor of the color bitmap font.
     *
     * Generated from Godot docs: TextServer.font_set_scale
     */
    fun fontSetScale(fontRid: RID, size: Long, scale: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongAndDoubleArgs(Binds.fontSetScaleBind, segment, fontRid, size, scale)
    }

    /**
     * Returns scaling factor of the color bitmap font.
     *
     * Generated from Godot docs: TextServer.font_get_scale
     */
    fun fontGetScale(fontRid: RID, size: Long): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDouble(Binds.fontGetScaleBind, segment, fontRid, size)
    }

    /**
     * Returns number of textures used by font cache entry.
     *
     * Generated from Godot docs: TextServer.font_get_texture_count
     */
    fun fontGetTextureCount(fontRid: RID, size: Vector2i): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndVector2iArgRetLong(Binds.fontGetTextureCountBind, segment, fontRid, size)
    }

    /**
     * Removes all textures from font cache entry. Note: This function will not remove glyphs
     * associated with the texture, use `font_remove_glyph` to remove them manually.
     *
     * Generated from Godot docs: TextServer.font_clear_textures
     */
    fun fontClearTextures(fontRid: RID, size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndVector2iArg(Binds.fontClearTexturesBind, segment, fontRid, size)
    }

    /**
     * Removes specified texture from the cache entry. Note: This function will not remove glyphs
     * associated with the texture, remove them manually, using `font_remove_glyph`.
     *
     * Generated from Godot docs: TextServer.font_remove_texture
     */
    fun fontRemoveTexture(fontRid: RID, size: Vector2i, textureIndex: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongArgs(Binds.fontRemoveTextureBind, segment, fontRid, size, textureIndex)
    }

    /**
     * Sets font cache texture image data.
     *
     * Generated from Godot docs: TextServer.font_set_texture_image
     */
    fun fontSetTextureImage(fontRid: RID, size: Vector2i, textureIndex: Long, image: Image?) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongObjectArgs(Binds.fontSetTextureImageBind, segment, fontRid, size, textureIndex, image?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns font cache texture image data.
     *
     * Generated from Godot docs: TextServer.font_get_texture_image
     */
    fun fontGetTextureImage(fontRid: RID, size: Vector2i, textureIndex: Long): Image? {
        checkOpen()
        return Image.wrapOwned(ObjectCalls.ptrcallWithRIDVector2iLongArgsRetObject(Binds.fontGetTextureImageBind, segment, fontRid, size, textureIndex))
    }

    /**
     * Sets array containing glyph packing data.
     *
     * Generated from Godot docs: TextServer.font_set_texture_offsets
     */
    fun fontSetTextureOffsets(fontRid: RID, size: Vector2i, textureIndex: Long, offset: List<Int>) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongPackedInt32ListArgs(Binds.fontSetTextureOffsetsBind, segment, fontRid, size, textureIndex, offset)
    }

    /**
     * Returns array containing glyph packing data.
     *
     * Generated from Godot docs: TextServer.font_get_texture_offsets
     */
    fun fontGetTextureOffsets(fontRid: RID, size: Vector2i, textureIndex: Long): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetPackedInt32List(Binds.fontGetTextureOffsetsBind, segment, fontRid, size, textureIndex)
    }

    /**
     * Returns list of rendered glyphs in the cache entry.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_list
     */
    fun fontGetGlyphList(fontRid: RID, size: Vector2i): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndVector2iArgRetPackedInt32List(Binds.fontGetGlyphListBind, segment, fontRid, size)
    }

    /**
     * Removes all rendered glyph information from the cache entry. Note: This function will not remove
     * textures associated with the glyphs, use `font_remove_texture` to remove them manually.
     *
     * Generated from Godot docs: TextServer.font_clear_glyphs
     */
    fun fontClearGlyphs(fontRid: RID, size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndVector2iArg(Binds.fontClearGlyphsBind, segment, fontRid, size)
    }

    /**
     * Removes specified rendered glyph information from the cache entry. Note: This function will not
     * remove textures associated with the glyphs, use `font_remove_texture` to remove them manually.
     *
     * Generated from Godot docs: TextServer.font_remove_glyph
     */
    fun fontRemoveGlyph(fontRid: RID, size: Vector2i, glyph: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongArgs(Binds.fontRemoveGlyphBind, segment, fontRid, size, glyph)
    }

    /**
     * Returns glyph advance (offset of the next glyph). Note: Advance for glyphs outlines is the same
     * as the base glyph advance and is not saved.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_advance
     */
    fun fontGetGlyphAdvance(fontRid: RID, size: Long, glyph: Long): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetVector2(Binds.fontGetGlyphAdvanceBind, segment, fontRid, size, glyph)
    }

    /**
     * Sets glyph advance (offset of the next glyph). Note: Advance for glyphs outlines is the same as
     * the base glyph advance and is not saved.
     *
     * Generated from Godot docs: TextServer.font_set_glyph_advance
     */
    fun fontSetGlyphAdvance(fontRid: RID, size: Long, glyph: Long, advance: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDTwoLongAndVector2Args(Binds.fontSetGlyphAdvanceBind, segment, fontRid, size, glyph, advance)
    }

    /**
     * Returns glyph offset from the baseline.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_offset
     */
    fun fontGetGlyphOffset(fontRid: RID, size: Vector2i, glyph: Long): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetVector2(Binds.fontGetGlyphOffsetBind, segment, fontRid, size, glyph)
    }

    /**
     * Sets glyph offset from the baseline.
     *
     * Generated from Godot docs: TextServer.font_set_glyph_offset
     */
    fun fontSetGlyphOffset(fontRid: RID, size: Vector2i, glyph: Long, offset: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongVector2Args(Binds.fontSetGlyphOffsetBind, segment, fontRid, size, glyph, offset)
    }

    /**
     * Returns size of the glyph.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_size
     */
    fun fontGetGlyphSize(fontRid: RID, size: Vector2i, glyph: Long): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetVector2(Binds.fontGetGlyphSizeBind, segment, fontRid, size, glyph)
    }

    /**
     * Sets size of the glyph.
     *
     * Generated from Godot docs: TextServer.font_set_glyph_size
     */
    fun fontSetGlyphSize(fontRid: RID, size: Vector2i, glyph: Long, glSize: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongVector2Args(Binds.fontSetGlyphSizeBind, segment, fontRid, size, glyph, glSize)
    }

    /**
     * Returns rectangle in the cache texture containing the glyph.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_uv_rect
     */
    fun fontGetGlyphUvRect(fontRid: RID, size: Vector2i, glyph: Long): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetRect2(Binds.fontGetGlyphUvRectBind, segment, fontRid, size, glyph)
    }

    /**
     * Sets rectangle in the cache texture containing the glyph.
     *
     * Generated from Godot docs: TextServer.font_set_glyph_uv_rect
     */
    fun fontSetGlyphUvRect(fontRid: RID, size: Vector2i, glyph: Long, uvRect: Rect2) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongRect2Args(Binds.fontSetGlyphUvRectBind, segment, fontRid, size, glyph, uvRect)
    }

    /**
     * Returns index of the cache texture containing the glyph.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_texture_idx
     */
    fun fontGetGlyphTextureIdx(fontRid: RID, size: Vector2i, glyph: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetLong(Binds.fontGetGlyphTextureIdxBind, segment, fontRid, size, glyph)
    }

    /**
     * Sets index of the cache texture containing the glyph.
     *
     * Generated from Godot docs: TextServer.font_set_glyph_texture_idx
     */
    fun fontSetGlyphTextureIdx(fontRid: RID, size: Vector2i, glyph: Long, textureIdx: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iTwoLongArgs(Binds.fontSetGlyphTextureIdxBind, segment, fontRid, size, glyph, textureIdx)
    }

    /**
     * Returns resource ID of the cache texture containing the glyph. Note: If there are pending glyphs
     * to render, calling this function might trigger the texture cache update.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_texture_rid
     */
    fun fontGetGlyphTextureRid(fontRid: RID, size: Vector2i, glyph: Long): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetRID(Binds.fontGetGlyphTextureRidBind, segment, fontRid, size, glyph)
    }

    /**
     * Returns size of the cache texture containing the glyph. Note: If there are pending glyphs to
     * render, calling this function might trigger the texture cache update.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_texture_size
     */
    fun fontGetGlyphTextureSize(fontRid: RID, size: Vector2i, glyph: Long): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetVector2(Binds.fontGetGlyphTextureSizeBind, segment, fontRid, size, glyph)
    }

    /**
     * Returns outline contours of the glyph as a `Dictionary` with the following contents: `points` -
     * `PackedVector3Array`, containing outline points. `x` and `y` are point coordinates. `z` is the
     * type of the point, using the `ContourPointTag` values. `contours` - `PackedInt32Array`,
     * containing indices the end points of each contour. `orientation` - `bool`, contour orientation.
     * If `true`, clockwise contours must be filled. - Two successive `ContourPointTag.ON` points
     * indicate a line segment. - One `ContourPointTag.OFF_CONIC` point between two
     * `ContourPointTag.ON` points indicates a single conic (quadratic) Bézier arc. - Two
     * `ContourPointTag.OFF_CUBIC` points between two `ContourPointTag.ON` points indicate a single
     * cubic Bézier arc. - Two successive `ContourPointTag.OFF_CONIC` points indicate two successive
     * conic (quadratic) Bézier arcs with a virtual `ContourPointTag.ON` point at their middle. - Each
     * contour is closed. The last point of a contour uses the first point of a contour as its next
     * point, and vice versa. The first point can be `ContourPointTag.OFF_CONIC` point.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_contours
     */
    fun fontGetGlyphContours(font: RID, size: Long, index: Long): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetDictionary(Binds.fontGetGlyphContoursBind, segment, font, size, index)
    }

    /**
     * Returns list of the kerning overrides.
     *
     * Generated from Godot docs: TextServer.font_get_kerning_list
     */
    fun fontGetKerningList(fontRid: RID, size: Long): List<Vector2i> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVector2iList(Binds.fontGetKerningListBind, segment, fontRid, size)
    }

    /**
     * Removes all kerning overrides.
     *
     * Generated from Godot docs: TextServer.font_clear_kerning_map
     */
    fun fontClearKerningMap(fontRid: RID, size: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.fontClearKerningMapBind, segment, fontRid, size)
    }

    /**
     * Removes kerning override for the pair of glyphs.
     *
     * Generated from Godot docs: TextServer.font_remove_kerning
     */
    fun fontRemoveKerning(fontRid: RID, size: Long, glyphPair: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongVector2iArgs(Binds.fontRemoveKerningBind, segment, fontRid, size, glyphPair)
    }

    /**
     * Sets kerning for the pair of glyphs.
     *
     * Generated from Godot docs: TextServer.font_set_kerning
     */
    fun fontSetKerning(fontRid: RID, size: Long, glyphPair: Vector2i, kerning: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongVector2iAndVector2Args(Binds.fontSetKerningBind, segment, fontRid, size, glyphPair, kerning)
    }

    /**
     * Returns kerning for the pair of glyphs.
     *
     * Generated from Godot docs: TextServer.font_get_kerning
     */
    fun fontGetKerning(fontRid: RID, size: Long, glyphPair: Vector2i): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDLongVector2iArgsRetVector2(Binds.fontGetKerningBind, segment, fontRid, size, glyphPair)
    }

    /**
     * Returns the glyph index of a `char`, optionally modified by the `variation_selector`. See
     * `font_get_char_from_glyph_index`.
     *
     * Generated from Godot docs: TextServer.font_get_glyph_index
     */
    fun fontGetGlyphIndex(fontRid: RID, size: Long, char: Long, variationSelector: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndThreeLongArgsRetLong(Binds.fontGetGlyphIndexBind, segment, fontRid, size, char, variationSelector)
    }

    /**
     * Returns character code associated with `glyph_index`, or `0` if `glyph_index` is invalid. See
     * `font_get_glyph_index`.
     *
     * Generated from Godot docs: TextServer.font_get_char_from_glyph_index
     */
    fun fontGetCharFromGlyphIndex(fontRid: RID, size: Long, glyphIndex: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetLong(Binds.fontGetCharFromGlyphIndexBind, segment, fontRid, size, glyphIndex)
    }

    /**
     * Returns `true` if a Unicode `char` is available in the font.
     *
     * Generated from Godot docs: TextServer.font_has_char
     */
    fun fontHasChar(fontRid: RID, char: Long): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetBool(Binds.fontHasCharBind, segment, fontRid, char)
    }

    /**
     * Returns a string containing all the characters available in the font.
     *
     * Generated from Godot docs: TextServer.font_get_supported_chars
     */
    fun fontGetSupportedChars(fontRid: RID): String {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetString(Binds.fontGetSupportedCharsBind, segment, fontRid)
    }

    /**
     * Returns an array containing all glyph indices in the font.
     *
     * Generated from Godot docs: TextServer.font_get_supported_glyphs
     */
    fun fontGetSupportedGlyphs(fontRid: RID): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(Binds.fontGetSupportedGlyphsBind, segment, fontRid)
    }

    /**
     * Renders the range of characters to the font cache texture.
     *
     * Generated from Godot docs: TextServer.font_render_range
     */
    fun fontRenderRange(fontRid: RID, size: Vector2i, start: Long, end: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iTwoLongArgs(Binds.fontRenderRangeBind, segment, fontRid, size, start, end)
    }

    /**
     * Renders specified glyph to the font cache texture.
     *
     * Generated from Godot docs: TextServer.font_render_glyph
     */
    fun fontRenderGlyph(fontRid: RID, size: Vector2i, index: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDVector2iLongArgs(Binds.fontRenderGlyphBind, segment, fontRid, size, index)
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
        checkOpen()
        ObjectCalls.ptrcallWithTwoRIDLongVector2LongColorDoubleArgs(Binds.fontDrawGlyphBind, segment, fontRid, canvas, size, pos, index, color, oversampling)
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
        checkOpen()
        ObjectCalls.ptrcallWithTwoRIDTwoLongVector2LongColorDoubleArgs(Binds.fontDrawGlyphOutlineBind, segment, fontRid, canvas, size, outlineSize, pos, index, color, oversampling)
    }

    /**
     * Returns `true` if the font supports the given language (as a ISO 639
     * (https://en.wikipedia.org/wiki/ISO_639-1) code).
     *
     * Generated from Godot docs: TextServer.font_is_language_supported
     */
    fun fontIsLanguageSupported(fontRid: RID, language: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndStringArgRetBool(Binds.fontIsLanguageSupportedBind, segment, fontRid, language)
    }

    /**
     * Adds override for `font_is_language_supported`.
     *
     * Generated from Godot docs: TextServer.font_set_language_support_override
     */
    fun fontSetLanguageSupportOverride(fontRid: RID, language: String, supported: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDStringAndBoolArgs(Binds.fontSetLanguageSupportOverrideBind, segment, fontRid, language, supported)
    }

    /**
     * Returns `true` if support override is enabled for the `language`.
     *
     * Generated from Godot docs: TextServer.font_get_language_support_override
     */
    fun fontGetLanguageSupportOverride(fontRid: RID, language: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndStringArgRetBool(Binds.fontGetLanguageSupportOverrideBind, segment, fontRid, language)
    }

    /**
     * Remove language support override.
     *
     * Generated from Godot docs: TextServer.font_remove_language_support_override
     */
    fun fontRemoveLanguageSupportOverride(fontRid: RID, language: String) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndStringArg(Binds.fontRemoveLanguageSupportOverrideBind, segment, fontRid, language)
    }

    /**
     * Returns list of language support overrides.
     *
     * Generated from Godot docs: TextServer.font_get_language_support_overrides
     */
    fun fontGetLanguageSupportOverrides(fontRid: RID): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetPackedStringList(Binds.fontGetLanguageSupportOverridesBind, segment, fontRid)
    }

    /**
     * Returns `true` if the font supports the given script (as a ISO 15924
     * (https://en.wikipedia.org/wiki/ISO_15924) code).
     *
     * Generated from Godot docs: TextServer.font_is_script_supported
     */
    fun fontIsScriptSupported(fontRid: RID, script: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndStringArgRetBool(Binds.fontIsScriptSupportedBind, segment, fontRid, script)
    }

    /**
     * Adds override for `font_is_script_supported`.
     *
     * Generated from Godot docs: TextServer.font_set_script_support_override
     */
    fun fontSetScriptSupportOverride(fontRid: RID, script: String, supported: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDStringAndBoolArgs(Binds.fontSetScriptSupportOverrideBind, segment, fontRid, script, supported)
    }

    /**
     * Returns `true` if support override is enabled for the `script`.
     *
     * Generated from Godot docs: TextServer.font_get_script_support_override
     */
    fun fontGetScriptSupportOverride(fontRid: RID, script: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndStringArgRetBool(Binds.fontGetScriptSupportOverrideBind, segment, fontRid, script)
    }

    /**
     * Removes script support override.
     *
     * Generated from Godot docs: TextServer.font_remove_script_support_override
     */
    fun fontRemoveScriptSupportOverride(fontRid: RID, script: String) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndStringArg(Binds.fontRemoveScriptSupportOverrideBind, segment, fontRid, script)
    }

    /**
     * Returns list of script support overrides.
     *
     * Generated from Godot docs: TextServer.font_get_script_support_overrides
     */
    fun fontGetScriptSupportOverrides(fontRid: RID): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetPackedStringList(Binds.fontGetScriptSupportOverridesBind, segment, fontRid)
    }

    /**
     * Sets font OpenType feature set override.
     *
     * Generated from Godot docs: TextServer.font_set_opentype_feature_overrides
     */
    fun fontSetOpentypeFeatureOverrides(fontRid: RID, overrides: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndDictionaryArg(Binds.fontSetOpentypeFeatureOverridesBind, segment, fontRid, overrides)
    }

    /**
     * Returns font OpenType feature set override.
     *
     * Generated from Godot docs: TextServer.font_get_opentype_feature_overrides
     */
    fun fontGetOpentypeFeatureOverrides(fontRid: RID): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDictionary(Binds.fontGetOpentypeFeatureOverridesBind, segment, fontRid)
    }

    /**
     * Returns the dictionary of the supported OpenType features.
     *
     * Generated from Godot docs: TextServer.font_supported_feature_list
     */
    fun fontSupportedFeatureList(fontRid: RID): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDictionary(Binds.fontSupportedFeatureListBind, segment, fontRid)
    }

    /**
     * Returns the dictionary of the supported OpenType variation coordinates.
     *
     * Generated from Godot docs: TextServer.font_supported_variation_list
     */
    fun fontSupportedVariationList(fontRid: RID): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDictionary(Binds.fontSupportedVariationListBind, segment, fontRid)
    }

    /**
     * This method does nothing and always returns `1.0`.
     *
     * Generated from Godot docs: TextServer.font_get_global_oversampling
     */
    fun fontGetGlobalOversampling(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.fontGetGlobalOversamplingBind, segment)
    }

    /**
     * This method does nothing.
     *
     * Generated from Godot docs: TextServer.font_set_global_oversampling
     */
    fun fontSetGlobalOversampling(oversampling: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.fontSetGlobalOversamplingBind, segment, oversampling)
    }

    /**
     * Returns size of the replacement character (box with character hexadecimal code that is drawn in
     * place of invalid characters).
     *
     * Generated from Godot docs: TextServer.get_hex_code_box_size
     */
    fun getHexCodeBoxSize(size: Long, index: Long): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetVector2(Binds.getHexCodeBoxSizeBind, segment, size, index)
    }

    /**
     * Draws box displaying character hexadecimal code. Used for replacing missing characters.
     *
     * Generated from Godot docs: TextServer.draw_hex_code_box
     */
    fun drawHexCodeBox(canvas: RID, size: Long, pos: Vector2, index: Long, color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongVector2LongColorArgs(Binds.drawHexCodeBoxBind, segment, canvas, size, pos, index, color)
    }

    /**
     * Creates a new buffer for complex text layout, with the given `direction` and `orientation`. To
     * free the resulting buffer, use `free_rid` method. Note: Direction is ignored if server does not
     * support `Feature.BIDI_LAYOUT` feature (supported by `TextServerAdvanced`). Note: Orientation is
     * ignored if server does not support `Feature.VERTICAL_LAYOUT` feature (supported by
     * `TextServerAdvanced`).
     *
     * Generated from Godot docs: TextServer.create_shaped_text
     */
    fun createShapedText(direction: TextServer.Direction = TextServer.Direction.AUTO, orientation: TextServer.Orientation = TextServer.Orientation.HORIZONTAL): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongArgsRetRID(Binds.createShapedTextBind, segment, direction.value, orientation.value)
    }

    /**
     * Clears text buffer (removes text and inline objects).
     *
     * Generated from Godot docs: TextServer.shaped_text_clear
     */
    fun shapedTextClear(rid: RID) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDArg(Binds.shapedTextClearBind, segment, rid)
    }

    /**
     * Duplicates shaped text buffer.
     *
     * Generated from Godot docs: TextServer.shaped_text_duplicate
     */
    fun shapedTextDuplicate(rid: RID): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetRID(Binds.shapedTextDuplicateBind, segment, rid)
    }

    /**
     * Sets desired text direction. If set to `Direction.AUTO`, direction will be detected based on the
     * buffer contents and current locale. Note: Direction is ignored if server does not support
     * `Feature.BIDI_LAYOUT` feature (supported by `TextServerAdvanced`).
     *
     * Generated from Godot docs: TextServer.shaped_text_set_direction
     */
    fun shapedTextSetDirection(shaped: RID, direction: TextServer.Direction = TextServer.Direction.AUTO) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.shapedTextSetDirectionBind, segment, shaped, direction.value)
    }

    /**
     * Returns direction of the text.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_direction
     */
    fun shapedTextGetDirection(shaped: RID): TextServer.Direction {
        checkOpen()
        return TextServer.Direction(ObjectCalls.ptrcallWithRIDArgRetLong(Binds.shapedTextGetDirectionBind, segment, shaped))
    }

    /**
     * Returns direction of the text, inferred by the BiDi algorithm.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_inferred_direction
     */
    fun shapedTextGetInferredDirection(shaped: RID): TextServer.Direction {
        checkOpen()
        return TextServer.Direction(ObjectCalls.ptrcallWithRIDArgRetLong(Binds.shapedTextGetInferredDirectionBind, segment, shaped))
    }

    /**
     * Overrides BiDi for the structured text. Override ranges should cover full source text without
     * overlaps. BiDi algorithm will be used on each range separately.
     *
     * Generated from Godot docs: TextServer.shaped_text_set_bidi_override
     */
    fun shapedTextSetBidiOverride(shaped: RID, override: List<Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndArrayArg(Binds.shapedTextSetBidiOverrideBind, segment, shaped, override)
    }

    /**
     * Sets custom punctuation character list, used for word breaking. If set to empty string, server
     * defaults are used.
     *
     * Generated from Godot docs: TextServer.shaped_text_set_custom_punctuation
     */
    fun shapedTextSetCustomPunctuation(shaped: RID, punct: String) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndStringArg(Binds.shapedTextSetCustomPunctuationBind, segment, shaped, punct)
    }

    /**
     * Returns custom punctuation character list, used for word breaking. If set to empty string,
     * server defaults are used.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_custom_punctuation
     */
    fun shapedTextGetCustomPunctuation(shaped: RID): String {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetString(Binds.shapedTextGetCustomPunctuationBind, segment, shaped)
    }

    /**
     * Sets ellipsis character used for text clipping.
     *
     * Generated from Godot docs: TextServer.shaped_text_set_custom_ellipsis
     */
    fun shapedTextSetCustomEllipsis(shaped: RID, char: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.shapedTextSetCustomEllipsisBind, segment, shaped, char)
    }

    /**
     * Returns ellipsis character used for text clipping.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_custom_ellipsis
     */
    fun shapedTextGetCustomEllipsis(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.shapedTextGetCustomEllipsisBind, segment, shaped)
    }

    /**
     * Sets desired text orientation. Note: Orientation is ignored if server does not support
     * `Feature.VERTICAL_LAYOUT` feature (supported by `TextServerAdvanced`).
     *
     * Generated from Godot docs: TextServer.shaped_text_set_orientation
     */
    fun shapedTextSetOrientation(shaped: RID, orientation: TextServer.Orientation = TextServer.Orientation.HORIZONTAL) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndLongArg(Binds.shapedTextSetOrientationBind, segment, shaped, orientation.value)
    }

    /**
     * Returns text orientation.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_orientation
     */
    fun shapedTextGetOrientation(shaped: RID): TextServer.Orientation {
        checkOpen()
        return TextServer.Orientation(ObjectCalls.ptrcallWithRIDArgRetLong(Binds.shapedTextGetOrientationBind, segment, shaped))
    }

    /**
     * If set to `true` text buffer will display invalid characters as hexadecimal codes, otherwise
     * nothing is displayed.
     *
     * Generated from Godot docs: TextServer.shaped_text_set_preserve_invalid
     */
    fun shapedTextSetPreserveInvalid(shaped: RID, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.shapedTextSetPreserveInvalidBind, segment, shaped, enabled)
    }

    /**
     * Returns `true` if text buffer is configured to display hexadecimal codes in place of invalid
     * characters. Note: If set to `false`, nothing is displayed in place of invalid characters.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_preserve_invalid
     */
    fun shapedTextGetPreserveInvalid(shaped: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.shapedTextGetPreserveInvalidBind, segment, shaped)
    }

    /**
     * If set to `true` text buffer will display control characters.
     *
     * Generated from Godot docs: TextServer.shaped_text_set_preserve_control
     */
    fun shapedTextSetPreserveControl(shaped: RID, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndBoolArg(Binds.shapedTextSetPreserveControlBind, segment, shaped, enabled)
    }

    /**
     * Returns `true` if text buffer is configured to display control characters.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_preserve_control
     */
    fun shapedTextGetPreserveControl(shaped: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.shapedTextGetPreserveControlBind, segment, shaped)
    }

    /**
     * Sets extra spacing added between glyphs or lines in pixels.
     *
     * Generated from Godot docs: TextServer.shaped_text_set_spacing
     */
    fun shapedTextSetSpacing(shaped: RID, spacing: TextServer.SpacingType, value: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDAndTwoLongArgs(Binds.shapedTextSetSpacingBind, segment, shaped, spacing.value, value)
    }

    /**
     * Returns extra spacing added between glyphs or lines in pixels.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_spacing
     */
    fun shapedTextGetSpacing(shaped: RID, spacing: TextServer.SpacingType): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(Binds.shapedTextGetSpacingBind, segment, shaped, spacing.value)
    }

    /**
     * Adds text span and font to draw it to the text buffer.
     *
     * Generated from Godot docs: TextServer.shaped_text_add_string
     */
    fun shapedTextAddString(shaped: RID, text: String, fonts: List<RID>, size: Long, opentypeFeatures: Map<String, Any?> = emptyMap(), language: String = "", meta: Any? = null): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDStringRIDListLongDictionaryStringVariantArgsRetBool(Binds.shapedTextAddStringBind, segment, shaped, text, fonts, size, opentypeFeatures, language, meta)
    }

    /**
     * Adds inline object to the text buffer, `key` must be unique. In the text, object is represented
     * as `length` object replacement characters.
     *
     * Generated from Godot docs: TextServer.shaped_text_add_object
     */
    fun shapedTextAddObject(shaped: RID, key: Any?, size: Vector2, inlineAlign: InlineAlignment = InlineAlignment.CENTER, length: Long = 1L, baseline: Double = 0.0): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVariantVector2LongLongDoubleArgsRetBool(Binds.shapedTextAddObjectBind, segment, shaped, key, size, inlineAlign.value, length, baseline)
    }

    /**
     * Sets new size and alignment of embedded object.
     *
     * Generated from Godot docs: TextServer.shaped_text_resize_object
     */
    fun shapedTextResizeObject(shaped: RID, key: Any?, size: Vector2, inlineAlign: InlineAlignment = InlineAlignment.CENTER, baseline: Double = 0.0): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDVariantVector2LongDoubleArgsRetBool(Binds.shapedTextResizeObjectBind, segment, shaped, key, size, inlineAlign.value, baseline)
    }

    /**
     * Returns `true` if an object with `key` is embedded in this shaped text buffer.
     *
     * Generated from Godot docs: TextServer.shaped_text_has_object
     */
    fun shapedTextHasObject(shaped: RID, key: Any?): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndVariantArgRetBool(Binds.shapedTextHasObjectBind, segment, shaped, key)
    }

    /**
     * Returns the text buffer source text, including object replacement characters.
     *
     * Generated from Godot docs: TextServer.shaped_get_text
     */
    fun shapedGetText(shaped: RID): String {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetString(Binds.shapedGetTextBind, segment, shaped)
    }

    /**
     * Returns number of text spans added using `shaped_text_add_string` or `shaped_text_add_object`.
     *
     * Generated from Godot docs: TextServer.shaped_get_span_count
     */
    fun shapedGetSpanCount(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.shapedGetSpanCountBind, segment, shaped)
    }

    /**
     * Returns text span metadata.
     *
     * Generated from Godot docs: TextServer.shaped_get_span_meta
     */
    fun shapedGetSpanMeta(shaped: RID, index: Long): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(Binds.shapedGetSpanMetaBind, segment, shaped, index)
    }

    /**
     * Returns text embedded object key.
     *
     * Generated from Godot docs: TextServer.shaped_get_span_embedded_object
     */
    fun shapedGetSpanEmbeddedObject(shaped: RID, index: Long): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(Binds.shapedGetSpanEmbeddedObjectBind, segment, shaped, index)
    }

    /**
     * Returns the text span source text.
     *
     * Generated from Godot docs: TextServer.shaped_get_span_text
     */
    fun shapedGetSpanText(shaped: RID, index: Long): String {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetString(Binds.shapedGetSpanTextBind, segment, shaped, index)
    }

    /**
     * Returns the text span embedded object key.
     *
     * Generated from Godot docs: TextServer.shaped_get_span_object
     */
    fun shapedGetSpanObject(shaped: RID, index: Long): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(Binds.shapedGetSpanObjectBind, segment, shaped, index)
    }

    /**
     * Changes text span font, font size, and OpenType features, without changing the text.
     *
     * Generated from Godot docs: TextServer.shaped_set_span_update_font
     */
    fun shapedSetSpanUpdateFont(shaped: RID, index: Long, fonts: List<RID>, size: Long, opentypeFeatures: Map<String, Any?> = emptyMap()) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDLongRIDListLongDictionaryArgs(Binds.shapedSetSpanUpdateFontBind, segment, shaped, index, fonts, size, opentypeFeatures)
    }

    /**
     * Returns the number of uniform text runs in the buffer.
     *
     * Generated from Godot docs: TextServer.shaped_get_run_count
     */
    fun shapedGetRunCount(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.shapedGetRunCountBind, segment, shaped)
    }

    /**
     * Returns the source text of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_text
     */
    fun shapedGetRunText(shaped: RID, index: Long): String {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetString(Binds.shapedGetRunTextBind, segment, shaped, index)
    }

    /**
     * Returns the source text range of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_range
     */
    fun shapedGetRunRange(shaped: RID, index: Long): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVector2i(Binds.shapedGetRunRangeBind, segment, shaped, index)
    }

    /**
     * Returns the glyph range of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_glyph_range
     */
    fun shapedGetRunGlyphRange(shaped: RID, index: Long): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVector2i(Binds.shapedGetRunGlyphRangeBind, segment, shaped, index)
    }

    /**
     * Returns the font RID of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_font_rid
     */
    fun shapedGetRunFontRid(shaped: RID, index: Long): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetRID(Binds.shapedGetRunFontRidBind, segment, shaped, index)
    }

    /**
     * Returns the font size of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_font_size
     */
    fun shapedGetRunFontSize(shaped: RID, index: Long): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetInt(Binds.shapedGetRunFontSizeBind, segment, shaped, index)
    }

    /**
     * Returns the language of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_language
     */
    fun shapedGetRunLanguage(shaped: RID, index: Long): String {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetString(Binds.shapedGetRunLanguageBind, segment, shaped, index)
    }

    /**
     * Returns the direction of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_direction
     */
    fun shapedGetRunDirection(shaped: RID, index: Long): TextServer.Direction {
        checkOpen()
        return TextServer.Direction(ObjectCalls.ptrcallWithRIDAndLongArgRetLong(Binds.shapedGetRunDirectionBind, segment, shaped, index))
    }

    /**
     * Returns the embedded object of the `index` text run (in visual order).
     *
     * Generated from Godot docs: TextServer.shaped_get_run_object
     */
    fun shapedGetRunObject(shaped: RID, index: Long): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVariantScalar(Binds.shapedGetRunObjectBind, segment, shaped, index)
    }

    /**
     * Returns text buffer for the substring of the text in the `shaped` text buffer (including inline
     * objects).
     *
     * Generated from Godot docs: TextServer.shaped_text_substr
     */
    fun shapedTextSubstr(shaped: RID, start: Long, length: Long): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetRID(Binds.shapedTextSubstrBind, segment, shaped, start, length)
    }

    /**
     * Returns the parent buffer from which the substring originates.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_parent
     */
    fun shapedTextGetParent(shaped: RID): RID {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetRID(Binds.shapedTextGetParentBind, segment, shaped)
    }

    /**
     * Adjusts text width to fit to specified width, returns new text width.
     *
     * Generated from Godot docs: TextServer.shaped_text_fit_to_width
     */
    fun shapedTextFitToWidth(shaped: RID, width: Double, justificationFlags: TextServer.JustificationFlag = TextServer.JustificationFlag(3L)): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDDoubleAndLongArgsRetDouble(Binds.shapedTextFitToWidthBind, segment, shaped, width, justificationFlags.value)
    }

    /**
     * Aligns shaped text to the given tab-stops.
     *
     * Generated from Godot docs: TextServer.shaped_text_tab_align
     */
    fun shapedTextTabAlign(shaped: RID, tabStops: List<Float>): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndPackedFloat32ListArgRetDouble(Binds.shapedTextTabAlignBind, segment, shaped, tabStops)
    }

    /**
     * Shapes buffer if it's not shaped. Returns `true` if the string is shaped successfully. Note: It
     * is not necessary to call this function manually, buffer will be shaped automatically as soon as
     * any of its output data is requested.
     *
     * Generated from Godot docs: TextServer.shaped_text_shape
     */
    fun shapedTextShape(shaped: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.shapedTextShapeBind, segment, shaped)
    }

    /**
     * Returns `true` if buffer is successfully shaped.
     *
     * Generated from Godot docs: TextServer.shaped_text_is_ready
     */
    fun shapedTextIsReady(shaped: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.shapedTextIsReadyBind, segment, shaped)
    }

    /**
     * Returns `true` if text buffer contains any visible characters.
     *
     * Generated from Godot docs: TextServer.shaped_text_has_visible_chars
     */
    fun shapedTextHasVisibleChars(shaped: RID): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetBool(Binds.shapedTextHasVisibleCharsBind, segment, shaped)
    }

    /**
     * Returns an array of glyphs in the visual order.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_glyphs
     */
    fun shapedTextGetGlyphs(shaped: RID): List<Map<String, Any?>> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(Binds.shapedTextGetGlyphsBind, segment, shaped)
    }

    /**
     * Returns text glyphs in the logical order.
     *
     * Generated from Godot docs: TextServer.shaped_text_sort_logical
     */
    fun shapedTextSortLogical(shaped: RID): List<Map<String, Any?>> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(Binds.shapedTextSortLogicalBind, segment, shaped)
    }

    /**
     * Returns number of glyphs in the buffer.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_glyph_count
     */
    fun shapedTextGetGlyphCount(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.shapedTextGetGlyphCountBind, segment, shaped)
    }

    /**
     * Returns substring buffer character range in the parent buffer.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_range
     */
    fun shapedTextGetRange(shaped: RID): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetVector2i(Binds.shapedTextGetRangeBind, segment, shaped)
    }

    /**
     * Breaks text to the lines and columns. Returns character ranges for each segment.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_line_breaks_adv
     */
    fun shapedTextGetLineBreaksAdv(shaped: RID, width: List<Float>, start: Long = 0L, once: Boolean = true, breakFlags: TextServer.LineBreakFlag = TextServer.LineBreakFlag(3L)): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDPackedFloat32ListLongBoolLongArgsRetPackedInt32List(Binds.shapedTextGetLineBreaksAdvBind, segment, shaped, width, start, once, breakFlags.value)
    }

    /**
     * Breaks text to the lines and returns character ranges for each line.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_line_breaks
     */
    fun shapedTextGetLineBreaks(shaped: RID, width: Double, start: Long = 0L, breakFlags: TextServer.LineBreakFlag = TextServer.LineBreakFlag(3L)): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDDoubleTwoLongArgsRetPackedInt32List(Binds.shapedTextGetLineBreaksBind, segment, shaped, width, start, breakFlags.value)
    }

    /**
     * Breaks text into words and returns array of character ranges. Use `grapheme_flags` to set what
     * characters are used for breaking.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_word_breaks
     */
    fun shapedTextGetWordBreaks(shaped: RID, graphemeFlags: TextServer.GraphemeFlag = TextServer.GraphemeFlag(264L), skipGraphemeFlags: TextServer.GraphemeFlag = TextServer.GraphemeFlag.VIRTUAL): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetPackedInt32List(Binds.shapedTextGetWordBreaksBind, segment, shaped, graphemeFlags.value, skipGraphemeFlags.value)
    }

    /**
     * Returns the position of the overrun trim.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_trim_pos
     */
    fun shapedTextGetTrimPos(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.shapedTextGetTrimPosBind, segment, shaped)
    }

    /**
     * Returns position of the ellipsis.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_ellipsis_pos
     */
    fun shapedTextGetEllipsisPos(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.shapedTextGetEllipsisPosBind, segment, shaped)
    }

    /**
     * Returns array of the glyphs in the ellipsis.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_ellipsis_glyphs
     */
    fun shapedTextGetEllipsisGlyphs(shaped: RID): List<Map<String, Any?>> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(Binds.shapedTextGetEllipsisGlyphsBind, segment, shaped)
    }

    /**
     * Returns number of glyphs in the ellipsis.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_ellipsis_glyph_count
     */
    fun shapedTextGetEllipsisGlyphCount(shaped: RID): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetLong(Binds.shapedTextGetEllipsisGlyphCountBind, segment, shaped)
    }

    /**
     * Trims text if it exceeds the given width.
     *
     * Generated from Godot docs: TextServer.shaped_text_overrun_trim_to_width
     */
    fun shapedTextOverrunTrimToWidth(shaped: RID, width: Double = 0.0, overrunTrimFlags: TextServer.TextOverrunFlag = TextServer.TextOverrunFlag.NO_TRIM) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDDoubleAndLongArgs(Binds.shapedTextOverrunTrimToWidthBind, segment, shaped, width, overrunTrimFlags.value)
    }

    /**
     * Returns array of inline objects.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_objects
     */
    fun shapedTextGetObjects(shaped: RID): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetArray(Binds.shapedTextGetObjectsBind, segment, shaped)
    }

    /**
     * Returns bounding rectangle of the inline object.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_object_rect
     */
    fun shapedTextGetObjectRect(shaped: RID, key: Any?): Rect2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndVariantArgRetRect2(Binds.shapedTextGetObjectRectBind, segment, shaped, key)
    }

    /**
     * Returns the character range of the inline object.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_object_range
     */
    fun shapedTextGetObjectRange(shaped: RID, key: Any?): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndVariantArgRetVector2i(Binds.shapedTextGetObjectRangeBind, segment, shaped, key)
    }

    /**
     * Returns the glyph index of the inline object.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_object_glyph
     */
    fun shapedTextGetObjectGlyph(shaped: RID, key: Any?): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndVariantArgRetLong(Binds.shapedTextGetObjectGlyphBind, segment, shaped, key)
    }

    /**
     * Returns size of the text.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_size
     */
    fun shapedTextGetSize(shaped: RID): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetVector2(Binds.shapedTextGetSizeBind, segment, shaped)
    }

    /**
     * Returns the text ascent (number of pixels above the baseline for horizontal layout or to the
     * left of baseline for vertical). Note: Overall ascent can be higher than font ascent, if some
     * glyphs are displaced from the baseline.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_ascent
     */
    fun shapedTextGetAscent(shaped: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.shapedTextGetAscentBind, segment, shaped)
    }

    /**
     * Returns the text descent (number of pixels below the baseline for horizontal layout or to the
     * right of baseline for vertical). Note: Overall descent can be higher than font descent, if some
     * glyphs are displaced from the baseline.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_descent
     */
    fun shapedTextGetDescent(shaped: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.shapedTextGetDescentBind, segment, shaped)
    }

    /**
     * Returns width (for horizontal layout) or height (for vertical) of the text.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_width
     */
    fun shapedTextGetWidth(shaped: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.shapedTextGetWidthBind, segment, shaped)
    }

    /**
     * Returns pixel offset of the underline below the baseline.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_underline_position
     */
    fun shapedTextGetUnderlinePosition(shaped: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.shapedTextGetUnderlinePositionBind, segment, shaped)
    }

    /**
     * Returns thickness of the underline.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_underline_thickness
     */
    fun shapedTextGetUnderlineThickness(shaped: RID): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetDouble(Binds.shapedTextGetUnderlineThicknessBind, segment, shaped)
    }

    /**
     * Returns shapes of the carets corresponding to the character offset `position` in the text.
     * Returned caret shape is 1 pixel wide rectangle.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_carets
     */
    fun shapedTextGetCarets(shaped: RID, position: Long): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetDictionary(Binds.shapedTextGetCaretsBind, segment, shaped, position)
    }

    /**
     * Returns selection rectangles for the specified character range.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_selection
     */
    fun shapedTextGetSelection(shaped: RID, start: Long, end: Long): List<Vector2> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetPackedVector2List(Binds.shapedTextGetSelectionBind, segment, shaped, start, end)
    }

    /**
     * Returns grapheme index at the specified pixel offset at the baseline, or `-1` if none is found.
     *
     * Generated from Godot docs: TextServer.shaped_text_hit_test_grapheme
     */
    fun shapedTextHitTestGrapheme(shaped: RID, coords: Double): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndDoubleArgRetLong(Binds.shapedTextHitTestGraphemeBind, segment, shaped, coords)
    }

    /**
     * Returns caret character offset at the specified pixel offset at the baseline. This function
     * always returns a valid position.
     *
     * Generated from Godot docs: TextServer.shaped_text_hit_test_position
     */
    fun shapedTextHitTestPosition(shaped: RID, coords: Double): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndDoubleArgRetLong(Binds.shapedTextHitTestPositionBind, segment, shaped, coords)
    }

    /**
     * Returns composite character's bounds as offsets from the start of the line.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_grapheme_bounds
     */
    fun shapedTextGetGraphemeBounds(shaped: RID, pos: Long): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetVector2(Binds.shapedTextGetGraphemeBoundsBind, segment, shaped, pos)
    }

    /**
     * Returns grapheme end position closest to the `pos`.
     *
     * Generated from Godot docs: TextServer.shaped_text_next_grapheme_pos
     */
    fun shapedTextNextGraphemePos(shaped: RID, pos: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(Binds.shapedTextNextGraphemePosBind, segment, shaped, pos)
    }

    /**
     * Returns grapheme start position closest to the `pos`.
     *
     * Generated from Godot docs: TextServer.shaped_text_prev_grapheme_pos
     */
    fun shapedTextPrevGraphemePos(shaped: RID, pos: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(Binds.shapedTextPrevGraphemePosBind, segment, shaped, pos)
    }

    /**
     * Returns array of the composite character boundaries.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_character_breaks
     */
    fun shapedTextGetCharacterBreaks(shaped: RID): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(Binds.shapedTextGetCharacterBreaksBind, segment, shaped)
    }

    /**
     * Returns composite character end position closest to the `pos`.
     *
     * Generated from Godot docs: TextServer.shaped_text_next_character_pos
     */
    fun shapedTextNextCharacterPos(shaped: RID, pos: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(Binds.shapedTextNextCharacterPosBind, segment, shaped, pos)
    }

    /**
     * Returns composite character start position closest to the `pos`.
     *
     * Generated from Godot docs: TextServer.shaped_text_prev_character_pos
     */
    fun shapedTextPrevCharacterPos(shaped: RID, pos: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(Binds.shapedTextPrevCharacterPosBind, segment, shaped, pos)
    }

    /**
     * Returns composite character position closest to the `pos`.
     *
     * Generated from Godot docs: TextServer.shaped_text_closest_character_pos
     */
    fun shapedTextClosestCharacterPos(shaped: RID, pos: Long): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithRIDAndLongArgRetLong(Binds.shapedTextClosestCharacterPosBind, segment, shaped, pos)
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
        checkOpen()
        ObjectCalls.ptrcallWithTwoRIDVector2TwoDoubleColorDoubleArgs(Binds.shapedTextDrawBind, segment, shaped, canvas, pos, clipL, clipR, color, oversampling)
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
        checkOpen()
        ObjectCalls.ptrcallWithTwoRIDVector2TwoDoubleLongColorDoubleArgs(Binds.shapedTextDrawOutlineBind, segment, shaped, canvas, pos, clipL, clipR, outlineSize, color, oversampling)
    }

    /**
     * Returns dominant direction of in the range of text.
     *
     * Generated from Godot docs: TextServer.shaped_text_get_dominant_direction_in_range
     */
    fun shapedTextGetDominantDirectionInRange(shaped: RID, start: Long, end: Long): TextServer.Direction {
        checkOpen()
        return TextServer.Direction(ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetLong(Binds.shapedTextGetDominantDirectionInRangeBind, segment, shaped, start, end))
    }

    /**
     * Converts a number from Western Arabic (0..9) to the numeral system used in the given `language`.
     * If `language` is an empty string, the active locale will be used.
     *
     * Generated from Godot docs: TextServer.format_number
     */
    fun formatNumber(number: String, language: String = ""): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringArgsRetString(Binds.formatNumberBind, segment, number, language)
    }

    /**
     * Converts `number` from the numeral system used in the given `language` to Western Arabic (0..9).
     * If `language` is an empty string, the active locale will be used.
     *
     * Generated from Godot docs: TextServer.parse_number
     */
    fun parseNumber(number: String, language: String = ""): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringArgsRetString(Binds.parseNumberBind, segment, number, language)
    }

    /**
     * Returns the percent sign used in the given `language`. If `language` is an empty string, the
     * active locale will be used.
     *
     * Generated from Godot docs: TextServer.percent_sign
     */
    fun percentSign(language: String = ""): String {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetString(Binds.percentSignBind, segment, language)
    }

    /**
     * Returns an array of the word break boundaries. Elements in the returned array are the offsets of
     * the start and end of words. Therefore the length of the array is always even. When
     * `chars_per_line` is greater than zero, line break boundaries are returned instead.
     *
     * Generated from Godot docs: TextServer.string_get_word_breaks
     */
    fun stringGetWordBreaks(string: String, language: String = "", charsPerLine: Long = 0L): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringAndLongArgRetPackedInt32List(Binds.stringGetWordBreaksBind, segment, string, language, charsPerLine)
    }

    /**
     * Returns array of the composite character boundaries.
     *
     * Generated from Godot docs: TextServer.string_get_character_breaks
     */
    fun stringGetCharacterBreaks(string: String, language: String = ""): List<Int> {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringArgsRetPackedInt32List(Binds.stringGetCharacterBreaksBind, segment, string, language)
    }

    /**
     * Returns index of the first string in `dict` which is visually confusable with the `string`, or
     * `-1` if none is found. Note: This method doesn't detect invisible characters, for spoof
     * detection use it in combination with `spoof_check`. Note: Always returns `-1` if the server does
     * not support the `Feature.UNICODE_SECURITY` feature.
     *
     * Generated from Godot docs: TextServer.is_confusable
     */
    fun isConfusable(string: String, dict: List<String>): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithStringAndPackedStringListArgRetLong(Binds.isConfusableBind, segment, string, dict)
    }

    /**
     * Returns `true` if `string` is likely to be an attempt at confusing the reader. Note: Always
     * returns `false` if the server does not support the `Feature.UNICODE_SECURITY` feature.
     *
     * Generated from Godot docs: TextServer.spoof_check
     */
    fun spoofCheck(string: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.spoofCheckBind, segment, string)
    }

    /**
     * Strips diacritics from the string. Note: The result may be longer or shorter than the original.
     *
     * Generated from Godot docs: TextServer.strip_diacritics
     */
    fun stripDiacritics(string: String): String {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetString(Binds.stripDiacriticsBind, segment, string)
    }

    /**
     * Returns `true` if `string` is a valid identifier. If the text server supports the
     * `Feature.UNICODE_IDENTIFIERS` feature, a valid identifier must: - Conform to normalization form
     * C. - Begin with a Unicode character of class XID_Start or `"_"`. - May contain Unicode
     * characters of class XID_Continue in the other positions. - Use UAX #31 recommended scripts only
     * (mixed scripts are allowed). If the `Feature.UNICODE_IDENTIFIERS` feature is not supported, a
     * valid identifier must: - Begin with a Unicode character of class XID_Start or `"_"`. - May
     * contain Unicode characters of class XID_Continue in the other positions.
     *
     * Generated from Godot docs: TextServer.is_valid_identifier
     */
    fun isValidIdentifier(string: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringArgRetBool(Binds.isValidIdentifierBind, segment, string)
    }

    /**
     * Returns `true` if the given code point is a valid letter, i.e. it belongs to the Unicode
     * category "L".
     *
     * Generated from Godot docs: TextServer.is_valid_letter
     */
    fun isValidLetter(unicode: Long): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetBool(Binds.isValidLetterBind, segment, unicode)
    }

    /**
     * Returns the string converted to `UPPERCASE`. Note: Casing is locale dependent and context
     * sensitive if server support `Feature.CONTEXT_SENSITIVE_CASE_CONVERSION` feature (supported by
     * `TextServerAdvanced`). Note: The result may be longer or shorter than the original.
     *
     * Generated from Godot docs: TextServer.string_to_upper
     */
    fun stringToUpper(string: String, language: String = ""): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringArgsRetString(Binds.stringToUpperBind, segment, string, language)
    }

    /**
     * Returns the string converted to `lowercase`. Note: Casing is locale dependent and context
     * sensitive if server support `Feature.CONTEXT_SENSITIVE_CASE_CONVERSION` feature (supported by
     * `TextServerAdvanced`). Note: The result may be longer or shorter than the original.
     *
     * Generated from Godot docs: TextServer.string_to_lower
     */
    fun stringToLower(string: String, language: String = ""): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringArgsRetString(Binds.stringToLowerBind, segment, string, language)
    }

    /**
     * Returns the string converted to `Title Case`. Note: Casing is locale dependent and context
     * sensitive if server support `Feature.CONTEXT_SENSITIVE_CASE_CONVERSION` feature (supported by
     * `TextServerAdvanced`). Note: The result may be longer or shorter than the original.
     *
     * Generated from Godot docs: TextServer.string_to_title
     */
    fun stringToTitle(string: String, language: String = ""): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoStringArgsRetString(Binds.stringToTitleBind, segment, string, language)
    }

    /**
     * Default implementation of the BiDi algorithm override function.
     *
     * Generated from Godot docs: TextServer.parse_structured_text
     */
    fun parseStructuredText(parserType: TextServer.StructuredTextParser, args: List<Any?>, text: String): List<Vector3i> {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArrayStringArgsRetVector3iList(Binds.parseStructuredTextBind, segment, parserType.value, args, text)
    }

    /**
     * Godot's `TextServer.FontAntialiasing` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`TextServer.FontAntialiasing.<NAME>`).
     *
     * Generated from Godot docs: TextServer.FontAntialiasing
     */
    @JvmInline
    value class FontAntialiasing(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Font glyphs are rasterized as 1-bit bitmaps.
             *
             * Generated from Godot docs: TextServer.FONT_ANTIALIASING_NONE
             */
            val NONE: FontAntialiasing get() = FontAntialiasing(0L)
            /**
             * Font glyphs are rasterized as 8-bit grayscale anti-aliased bitmaps.
             *
             * Generated from Godot docs: TextServer.FONT_ANTIALIASING_GRAY
             */
            val GRAY: FontAntialiasing get() = FontAntialiasing(1L)
            /**
             * Font glyphs are rasterized for LCD screens. LCD subpixel layout is determined by the value of
             * the `ProjectSettings.gui/theme/lcd_subpixel_layout` setting. LCD subpixel anti-aliasing mode is
             * suitable only for rendering horizontal, unscaled text in 2D.
             *
             * Generated from Godot docs: TextServer.FONT_ANTIALIASING_LCD
             */
            val LCD: FontAntialiasing get() = FontAntialiasing(2L)
        }
    }

    /**
     * Godot's `TextServer.FontLCDSubpixelLayout` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`TextServer.FontLCDSubpixelLayout.<NAME>`).
     *
     * Generated from Godot docs: TextServer.FontLCDSubpixelLayout
     */
    @JvmInline
    value class FontLCDSubpixelLayout(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Unknown or unsupported subpixel layout, LCD subpixel antialiasing is disabled.
             *
             * Generated from Godot docs: TextServer.FONT_LCD_SUBPIXEL_LAYOUT_NONE
             */
            val NONE: FontLCDSubpixelLayout get() = FontLCDSubpixelLayout(0L)
            /**
             * Horizontal RGB subpixel layout.
             *
             * Generated from Godot docs: TextServer.FONT_LCD_SUBPIXEL_LAYOUT_HRGB
             */
            val HRGB: FontLCDSubpixelLayout get() = FontLCDSubpixelLayout(1L)
            /**
             * Horizontal BGR subpixel layout.
             *
             * Generated from Godot docs: TextServer.FONT_LCD_SUBPIXEL_LAYOUT_HBGR
             */
            val HBGR: FontLCDSubpixelLayout get() = FontLCDSubpixelLayout(2L)
            /**
             * Vertical RGB subpixel layout.
             *
             * Generated from Godot docs: TextServer.FONT_LCD_SUBPIXEL_LAYOUT_VRGB
             */
            val VRGB: FontLCDSubpixelLayout get() = FontLCDSubpixelLayout(3L)
            /**
             * Vertical BGR subpixel layout.
             *
             * Generated from Godot docs: TextServer.FONT_LCD_SUBPIXEL_LAYOUT_VBGR
             */
            val VBGR: FontLCDSubpixelLayout get() = FontLCDSubpixelLayout(4L)
            /**
             * Represents the size of the `FontLCDSubpixelLayout` enum.
             *
             * Generated from Godot docs: TextServer.FONT_LCD_SUBPIXEL_LAYOUT_MAX
             */
            val MAX: FontLCDSubpixelLayout get() = FontLCDSubpixelLayout(5L)
        }
    }

    /**
     * Godot's `TextServer.Direction` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`TextServer.Direction.<NAME>`).
     *
     * Generated from Godot docs: TextServer.Direction
     */
    @JvmInline
    value class Direction(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Text direction is determined based on contents and current locale.
             *
             * Generated from Godot docs: TextServer.DIRECTION_AUTO
             */
            val AUTO: Direction get() = Direction(0L)
            /**
             * Text is written from left to right.
             *
             * Generated from Godot docs: TextServer.DIRECTION_LTR
             */
            val LTR: Direction get() = Direction(1L)
            /**
             * Text is written from right to left.
             *
             * Generated from Godot docs: TextServer.DIRECTION_RTL
             */
            val RTL: Direction get() = Direction(2L)
            /**
             * Text writing direction is the same as base string writing direction. Used for BiDi override
             * only.
             *
             * Generated from Godot docs: TextServer.DIRECTION_INHERITED
             */
            val INHERITED: Direction get() = Direction(3L)
        }
    }

    /**
     * Godot's `TextServer.Orientation` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`TextServer.Orientation.<NAME>`).
     *
     * Generated from Godot docs: TextServer.Orientation
     */
    @JvmInline
    value class Orientation(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Text is written horizontally.
             *
             * Generated from Godot docs: TextServer.ORIENTATION_HORIZONTAL
             */
            val HORIZONTAL: Orientation get() = Orientation(0L)
            /**
             * Left to right text is written vertically from top to bottom. Right to left text is written
             * vertically from bottom to top.
             *
             * Generated from Godot docs: TextServer.ORIENTATION_VERTICAL
             */
            val VERTICAL: Orientation get() = Orientation(1L)
        }
    }

    /**
     * Godot's `TextServer.JustificationFlag` bitfield as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`TextServer.JustificationFlag.<NAME>`).
     *
     * Generated from Godot docs: TextServer.JustificationFlag
     */
    @JvmInline
    value class JustificationFlag(override val value: Long) : GodotEnumValue {
        infix fun or(other: JustificationFlag): JustificationFlag = JustificationFlag(value or other.value)

        infix fun and(other: JustificationFlag): JustificationFlag = JustificationFlag(value and other.value)

        infix fun xor(other: JustificationFlag): JustificationFlag = JustificationFlag(value xor other.value)

        fun inv(): JustificationFlag = JustificationFlag(value.inv())

        operator fun contains(other: JustificationFlag): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Do not justify text.
             *
             * Generated from Godot docs: TextServer.JUSTIFICATION_NONE
             */
            val NONE: JustificationFlag get() = JustificationFlag(0L)
            /**
             * Justify text by adding and removing kashidas.
             *
             * Generated from Godot docs: TextServer.JUSTIFICATION_KASHIDA
             */
            val KASHIDA: JustificationFlag get() = JustificationFlag(1L)
            /**
             * Justify text by changing width of the spaces between the words.
             *
             * Generated from Godot docs: TextServer.JUSTIFICATION_WORD_BOUND
             */
            val WORD_BOUND: JustificationFlag get() = JustificationFlag(2L)
            /**
             * Remove trailing and leading spaces from the justified text.
             *
             * Generated from Godot docs: TextServer.JUSTIFICATION_TRIM_EDGE_SPACES
             */
            val TRIM_EDGE_SPACES: JustificationFlag get() = JustificationFlag(4L)
            /**
             * Only apply justification to the part of the text after the last tab.
             *
             * Generated from Godot docs: TextServer.JUSTIFICATION_AFTER_LAST_TAB
             */
            val AFTER_LAST_TAB: JustificationFlag get() = JustificationFlag(8L)
            /**
             * Apply justification to the trimmed line with ellipsis.
             *
             * Generated from Godot docs: TextServer.JUSTIFICATION_CONSTRAIN_ELLIPSIS
             */
            val CONSTRAIN_ELLIPSIS: JustificationFlag get() = JustificationFlag(16L)
            /**
             * Do not apply justification to the last line of the paragraph.
             *
             * Generated from Godot docs: TextServer.JUSTIFICATION_SKIP_LAST_LINE
             */
            val SKIP_LAST_LINE: JustificationFlag get() = JustificationFlag(32L)
            /**
             * Do not apply justification to the last line of the paragraph with visible characters (takes
             * precedence over `JustificationFlag.SKIP_LAST_LINE`).
             *
             * Generated from Godot docs: TextServer.JUSTIFICATION_SKIP_LAST_LINE_WITH_VISIBLE_CHARS
             */
            val SKIP_LAST_LINE_WITH_VISIBLE_CHARS: JustificationFlag get() = JustificationFlag(64L)
            /**
             * Always apply justification to the paragraphs with a single line
             * (`JustificationFlag.SKIP_LAST_LINE` and `JustificationFlag.SKIP_LAST_LINE_WITH_VISIBLE_CHARS`
             * are ignored).
             *
             * Generated from Godot docs: TextServer.JUSTIFICATION_DO_NOT_SKIP_SINGLE_LINE
             */
            val DO_NOT_SKIP_SINGLE_LINE: JustificationFlag get() = JustificationFlag(128L)
        }
    }

    /**
     * Godot's `TextServer.AutowrapMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`TextServer.AutowrapMode.<NAME>`).
     *
     * Generated from Godot docs: TextServer.AutowrapMode
     */
    @JvmInline
    value class AutowrapMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Autowrap is disabled.
             *
             * Generated from Godot docs: TextServer.AUTOWRAP_OFF
             */
            val OFF: AutowrapMode get() = AutowrapMode(0L)
            /**
             * Wraps the text inside the node's bounding rectangle by allowing to break lines at arbitrary
             * positions, which is useful when very limited space is available.
             *
             * Generated from Godot docs: TextServer.AUTOWRAP_ARBITRARY
             */
            val ARBITRARY: AutowrapMode get() = AutowrapMode(1L)
            /**
             * Wraps the text inside the node's bounding rectangle by soft-breaking between words.
             *
             * Generated from Godot docs: TextServer.AUTOWRAP_WORD
             */
            val WORD: AutowrapMode get() = AutowrapMode(2L)
            /**
             * Behaves similarly to `AutowrapMode.WORD`, but force-breaks a word if that single word does not
             * fit in one line.
             *
             * Generated from Godot docs: TextServer.AUTOWRAP_WORD_SMART
             */
            val WORD_SMART: AutowrapMode get() = AutowrapMode(3L)
        }
    }

    /**
     * Godot's `TextServer.LineBreakFlag` bitfield as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`TextServer.LineBreakFlag.<NAME>`).
     *
     * Generated from Godot docs: TextServer.LineBreakFlag
     */
    @JvmInline
    value class LineBreakFlag(override val value: Long) : GodotEnumValue {
        infix fun or(other: LineBreakFlag): LineBreakFlag = LineBreakFlag(value or other.value)

        infix fun and(other: LineBreakFlag): LineBreakFlag = LineBreakFlag(value and other.value)

        infix fun xor(other: LineBreakFlag): LineBreakFlag = LineBreakFlag(value xor other.value)

        fun inv(): LineBreakFlag = LineBreakFlag(value.inv())

        operator fun contains(other: LineBreakFlag): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Do not break the line.
             *
             * Generated from Godot docs: TextServer.BREAK_NONE
             */
            val NONE: LineBreakFlag get() = LineBreakFlag(0L)
            /**
             * Break the line at the line mandatory break characters (e.g. `"\n"`).
             *
             * Generated from Godot docs: TextServer.BREAK_MANDATORY
             */
            val MANDATORY: LineBreakFlag get() = LineBreakFlag(1L)
            /**
             * Break the line between the words.
             *
             * Generated from Godot docs: TextServer.BREAK_WORD_BOUND
             */
            val WORD_BOUND: LineBreakFlag get() = LineBreakFlag(2L)
            /**
             * Break the line between any unconnected graphemes.
             *
             * Generated from Godot docs: TextServer.BREAK_GRAPHEME_BOUND
             */
            val GRAPHEME_BOUND: LineBreakFlag get() = LineBreakFlag(4L)
            /**
             * Should be used only in conjunction with `LineBreakFlag.WORD_BOUND`, break the line between any
             * unconnected graphemes, if it's impossible to break it between the words.
             *
             * Generated from Godot docs: TextServer.BREAK_ADAPTIVE
             */
            val ADAPTIVE: LineBreakFlag get() = LineBreakFlag(8L)
            /**
             * Remove edge spaces from the broken line segments.
             *
             * Generated from Godot docs: TextServer.BREAK_TRIM_EDGE_SPACES
             */
            val TRIM_EDGE_SPACES: LineBreakFlag get() = LineBreakFlag(16L)
            /**
             * Subtract first line indentation width from all lines after the first one.
             *
             * Generated from Godot docs: TextServer.BREAK_TRIM_INDENT
             */
            val TRIM_INDENT: LineBreakFlag get() = LineBreakFlag(32L)
            /**
             * Remove spaces and line break characters from the start of broken line segments. E.g, after line
             * breaking, the second segment of the following text `test \n next`, is `next` if the flag is set,
             * and ` next` if it is not.
             *
             * Generated from Godot docs: TextServer.BREAK_TRIM_START_EDGE_SPACES
             */
            val TRIM_START_EDGE_SPACES: LineBreakFlag get() = LineBreakFlag(64L)
            /**
             * Remove spaces and line break characters from the end of broken line segments. E.g, after line
             * breaking, the first segment of the following text `test \n next`, is `test` if the flag is set,
             * and `test \n` if it is not.
             *
             * Generated from Godot docs: TextServer.BREAK_TRIM_END_EDGE_SPACES
             */
            val TRIM_END_EDGE_SPACES: LineBreakFlag get() = LineBreakFlag(128L)
        }
    }

    /**
     * Godot's `TextServer.VisibleCharactersBehavior` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`TextServer.VisibleCharactersBehavior.<NAME>`).
     *
     * Generated from Godot docs: TextServer.VisibleCharactersBehavior
     */
    @JvmInline
    value class VisibleCharactersBehavior(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Trims text before the shaping. e.g, increasing `Label.visible_characters` or
             * `RichTextLabel.visible_characters` value is visually identical to typing the text. Note: In this
             * mode, trimmed text is not processed at all. It is not accounted for in line breaking and size
             * calculations.
             *
             * Generated from Godot docs: TextServer.VC_CHARS_BEFORE_SHAPING
             */
            val CHARS_BEFORE_SHAPING: VisibleCharactersBehavior get() = VisibleCharactersBehavior(0L)
            /**
             * Displays glyphs that are mapped to the first `Label.visible_characters` or
             * `RichTextLabel.visible_characters` characters from the beginning of the text.
             *
             * Generated from Godot docs: TextServer.VC_CHARS_AFTER_SHAPING
             */
            val CHARS_AFTER_SHAPING: VisibleCharactersBehavior get() = VisibleCharactersBehavior(1L)
            /**
             * Displays `Label.visible_ratio` or `RichTextLabel.visible_ratio` glyphs, starting from the left
             * or from the right, depending on `Control.layout_direction` value.
             *
             * Generated from Godot docs: TextServer.VC_GLYPHS_AUTO
             */
            val GLYPHS_AUTO: VisibleCharactersBehavior get() = VisibleCharactersBehavior(2L)
            /**
             * Displays `Label.visible_ratio` or `RichTextLabel.visible_ratio` glyphs, starting from the left.
             *
             * Generated from Godot docs: TextServer.VC_GLYPHS_LTR
             */
            val GLYPHS_LTR: VisibleCharactersBehavior get() = VisibleCharactersBehavior(3L)
            /**
             * Displays `Label.visible_ratio` or `RichTextLabel.visible_ratio` glyphs, starting from the right.
             *
             * Generated from Godot docs: TextServer.VC_GLYPHS_RTL
             */
            val GLYPHS_RTL: VisibleCharactersBehavior get() = VisibleCharactersBehavior(4L)
        }
    }

    /**
     * Godot's `TextServer.OverrunBehavior` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`TextServer.OverrunBehavior.<NAME>`).
     *
     * Generated from Godot docs: TextServer.OverrunBehavior
     */
    @JvmInline
    value class OverrunBehavior(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No text trimming is performed.
             *
             * Generated from Godot docs: TextServer.OVERRUN_NO_TRIMMING
             */
            val NO_TRIMMING: OverrunBehavior get() = OverrunBehavior(0L)
            /**
             * Trims the text per character.
             *
             * Generated from Godot docs: TextServer.OVERRUN_TRIM_CHAR
             */
            val TRIM_CHAR: OverrunBehavior get() = OverrunBehavior(1L)
            /**
             * Trims the text per word.
             *
             * Generated from Godot docs: TextServer.OVERRUN_TRIM_WORD
             */
            val TRIM_WORD: OverrunBehavior get() = OverrunBehavior(2L)
            /**
             * Trims the text per character and adds an ellipsis to indicate that parts are hidden if trimmed
             * text is 6 characters or longer.
             *
             * Generated from Godot docs: TextServer.OVERRUN_TRIM_ELLIPSIS
             */
            val TRIM_ELLIPSIS: OverrunBehavior get() = OverrunBehavior(3L)
            /**
             * Trims the text per word and adds an ellipsis to indicate that parts are hidden if trimmed text
             * is 6 characters or longer.
             *
             * Generated from Godot docs: TextServer.OVERRUN_TRIM_WORD_ELLIPSIS
             */
            val TRIM_WORD_ELLIPSIS: OverrunBehavior get() = OverrunBehavior(4L)
            /**
             * Trims the text per character and adds an ellipsis to indicate that parts are hidden regardless
             * of trimmed text length.
             *
             * Generated from Godot docs: TextServer.OVERRUN_TRIM_ELLIPSIS_FORCE
             */
            val TRIM_ELLIPSIS_FORCE: OverrunBehavior get() = OverrunBehavior(5L)
            /**
             * Trims the text per word and adds an ellipsis to indicate that parts are hidden regardless of
             * trimmed text length.
             *
             * Generated from Godot docs: TextServer.OVERRUN_TRIM_WORD_ELLIPSIS_FORCE
             */
            val TRIM_WORD_ELLIPSIS_FORCE: OverrunBehavior get() = OverrunBehavior(6L)
        }
    }

    /**
     * Godot's `TextServer.TextOverrunFlag` bitfield as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`TextServer.TextOverrunFlag.<NAME>`).
     *
     * Generated from Godot docs: TextServer.TextOverrunFlag
     */
    @JvmInline
    value class TextOverrunFlag(override val value: Long) : GodotEnumValue {
        infix fun or(other: TextOverrunFlag): TextOverrunFlag = TextOverrunFlag(value or other.value)

        infix fun and(other: TextOverrunFlag): TextOverrunFlag = TextOverrunFlag(value and other.value)

        infix fun xor(other: TextOverrunFlag): TextOverrunFlag = TextOverrunFlag(value xor other.value)

        fun inv(): TextOverrunFlag = TextOverrunFlag(value.inv())

        operator fun contains(other: TextOverrunFlag): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * No trimming is performed.
             *
             * Generated from Godot docs: TextServer.OVERRUN_NO_TRIM
             */
            val NO_TRIM: TextOverrunFlag get() = TextOverrunFlag(0L)
            /**
             * Trims the text when it exceeds the given width.
             *
             * Generated from Godot docs: TextServer.OVERRUN_TRIM
             */
            val TRIM: TextOverrunFlag get() = TextOverrunFlag(1L)
            /**
             * Trims the text per word instead of per grapheme.
             *
             * Generated from Godot docs: TextServer.OVERRUN_TRIM_WORD_ONLY
             */
            val TRIM_WORD_ONLY: TextOverrunFlag get() = TextOverrunFlag(2L)
            /**
             * Determines whether an ellipsis should be added at the end of the text.
             *
             * Generated from Godot docs: TextServer.OVERRUN_ADD_ELLIPSIS
             */
            val ADD_ELLIPSIS: TextOverrunFlag get() = TextOverrunFlag(4L)
            /**
             * Determines whether the ellipsis at the end of the text is enforced and may not be hidden.
             *
             * Generated from Godot docs: TextServer.OVERRUN_ENFORCE_ELLIPSIS
             */
            val ENFORCE_ELLIPSIS: TextOverrunFlag get() = TextOverrunFlag(8L)
            /**
             * Accounts for the text being justified before attempting to trim it (see `JustificationFlag`).
             *
             * Generated from Godot docs: TextServer.OVERRUN_JUSTIFICATION_AWARE
             */
            val JUSTIFICATION_AWARE: TextOverrunFlag get() = TextOverrunFlag(16L)
            /**
             * Determines whether the ellipsis should be added regardless of the string length, otherwise it is
             * added only if the string is 6 characters or longer.
             *
             * Generated from Godot docs: TextServer.OVERRUN_SHORT_STRING_ELLIPSIS
             */
            val SHORT_STRING_ELLIPSIS: TextOverrunFlag get() = TextOverrunFlag(32L)
        }
    }

    /**
     * Godot's `TextServer.GraphemeFlag` bitfield as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`TextServer.GraphemeFlag.<NAME>`).
     *
     * Generated from Godot docs: TextServer.GraphemeFlag
     */
    @JvmInline
    value class GraphemeFlag(override val value: Long) : GodotEnumValue {
        infix fun or(other: GraphemeFlag): GraphemeFlag = GraphemeFlag(value or other.value)

        infix fun and(other: GraphemeFlag): GraphemeFlag = GraphemeFlag(value and other.value)

        infix fun xor(other: GraphemeFlag): GraphemeFlag = GraphemeFlag(value xor other.value)

        fun inv(): GraphemeFlag = GraphemeFlag(value.inv())

        operator fun contains(other: GraphemeFlag): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Grapheme is supported by the font, and can be drawn.
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_VALID
             */
            val VALID: GraphemeFlag get() = GraphemeFlag(1L)
            /**
             * Grapheme is part of right-to-left or bottom-to-top run.
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_RTL
             */
            val RTL: GraphemeFlag get() = GraphemeFlag(2L)
            /**
             * Grapheme is not part of source text, it was added by justification process.
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_VIRTUAL
             */
            val VIRTUAL: GraphemeFlag get() = GraphemeFlag(4L)
            /**
             * Grapheme is whitespace.
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_SPACE
             */
            val SPACE: GraphemeFlag get() = GraphemeFlag(8L)
            /**
             * Grapheme is mandatory break point (e.g. `"\n"`).
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_BREAK_HARD
             */
            val BREAK_HARD: GraphemeFlag get() = GraphemeFlag(16L)
            /**
             * Grapheme is optional break point (e.g. space).
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_BREAK_SOFT
             */
            val BREAK_SOFT: GraphemeFlag get() = GraphemeFlag(32L)
            /**
             * Grapheme is the tabulation character.
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_TAB
             */
            val TAB: GraphemeFlag get() = GraphemeFlag(64L)
            /**
             * Grapheme is kashida.
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_ELONGATION
             */
            val ELONGATION: GraphemeFlag get() = GraphemeFlag(128L)
            /**
             * Grapheme is punctuation character.
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_PUNCTUATION
             */
            val PUNCTUATION: GraphemeFlag get() = GraphemeFlag(256L)
            /**
             * Grapheme is underscore character.
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_UNDERSCORE
             */
            val UNDERSCORE: GraphemeFlag get() = GraphemeFlag(512L)
            /**
             * Grapheme is connected to the previous grapheme. Breaking line before this grapheme is not safe.
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_CONNECTED
             */
            val CONNECTED: GraphemeFlag get() = GraphemeFlag(1024L)
            /**
             * It is safe to insert a U+0640 before this grapheme for elongation.
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_SAFE_TO_INSERT_TATWEEL
             */
            val SAFE_TO_INSERT_TATWEEL: GraphemeFlag get() = GraphemeFlag(2048L)
            /**
             * Grapheme is an object replacement character for the embedded object.
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_EMBEDDED_OBJECT
             */
            val EMBEDDED_OBJECT: GraphemeFlag get() = GraphemeFlag(4096L)
            /**
             * Grapheme is a soft hyphen.
             *
             * Generated from Godot docs: TextServer.GRAPHEME_IS_SOFT_HYPHEN
             */
            val SOFT_HYPHEN: GraphemeFlag get() = GraphemeFlag(8192L)
        }
    }

    /**
     * Godot's `TextServer.Hinting` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`TextServer.Hinting.<NAME>`).
     *
     * Generated from Godot docs: TextServer.Hinting
     */
    @JvmInline
    value class Hinting(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Disables font hinting (smoother but less crisp).
             *
             * Generated from Godot docs: TextServer.HINTING_NONE
             */
            val NONE: Hinting get() = Hinting(0L)
            /**
             * Use the light font hinting mode.
             *
             * Generated from Godot docs: TextServer.HINTING_LIGHT
             */
            val LIGHT: Hinting get() = Hinting(1L)
            /**
             * Use the default font hinting mode (crisper but less smooth). Note: This hinting mode changes
             * both horizontal and vertical glyph metrics. If applied to monospace font, some glyphs might have
             * different width.
             *
             * Generated from Godot docs: TextServer.HINTING_NORMAL
             */
            val NORMAL: Hinting get() = Hinting(2L)
        }
    }

    /**
     * Godot's `TextServer.SubpixelPositioning` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`TextServer.SubpixelPositioning.<NAME>`).
     *
     * Generated from Godot docs: TextServer.SubpixelPositioning
     */
    @JvmInline
    value class SubpixelPositioning(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Glyph horizontal position is rounded to the whole pixel size, each glyph is rasterized once.
             *
             * Generated from Godot docs: TextServer.SUBPIXEL_POSITIONING_DISABLED
             */
            val DISABLED: SubpixelPositioning get() = SubpixelPositioning(0L)
            /**
             * Glyph horizontal position is rounded based on font size. - To one quarter of the pixel size if
             * font size is smaller or equal to `SubpixelPositioning.ONE_QUARTER_MAX_SIZE`. - To one half of
             * the pixel size if font size is smaller or equal to `SubpixelPositioning.ONE_HALF_MAX_SIZE`. - To
             * the whole pixel size for larger fonts.
             *
             * Generated from Godot docs: TextServer.SUBPIXEL_POSITIONING_AUTO
             */
            val AUTO: SubpixelPositioning get() = SubpixelPositioning(1L)
            /**
             * Glyph horizontal position is rounded to one half of the pixel size, each glyph is rasterized up
             * to two times.
             *
             * Generated from Godot docs: TextServer.SUBPIXEL_POSITIONING_ONE_HALF
             */
            val ONE_HALF: SubpixelPositioning get() = SubpixelPositioning(2L)
            /**
             * Glyph horizontal position is rounded to one quarter of the pixel size, each glyph is rasterized
             * up to four times.
             *
             * Generated from Godot docs: TextServer.SUBPIXEL_POSITIONING_ONE_QUARTER
             */
            val ONE_QUARTER: SubpixelPositioning get() = SubpixelPositioning(3L)
            /**
             * Maximum font size which will use "one half of the pixel" subpixel positioning in
             * `SubpixelPositioning.AUTO` mode.
             *
             * Generated from Godot docs: TextServer.SUBPIXEL_POSITIONING_ONE_HALF_MAX_SIZE
             */
            val ONE_HALF_MAX_SIZE: SubpixelPositioning get() = SubpixelPositioning(20L)
            /**
             * Maximum font size which will use "one quarter of the pixel" subpixel positioning in
             * `SubpixelPositioning.AUTO` mode.
             *
             * Generated from Godot docs: TextServer.SUBPIXEL_POSITIONING_ONE_QUARTER_MAX_SIZE
             */
            val ONE_QUARTER_MAX_SIZE: SubpixelPositioning get() = SubpixelPositioning(16L)
        }
    }

    /**
     * Godot's `TextServer.Feature` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`TextServer.Feature.<NAME>`).
     *
     * Generated from Godot docs: TextServer.Feature
     */
    @JvmInline
    value class Feature(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * TextServer supports simple text layouts.
             *
             * Generated from Godot docs: TextServer.FEATURE_SIMPLE_LAYOUT
             */
            val SIMPLE_LAYOUT: Feature get() = Feature(1L)
            /**
             * TextServer supports bidirectional text layouts.
             *
             * Generated from Godot docs: TextServer.FEATURE_BIDI_LAYOUT
             */
            val BIDI_LAYOUT: Feature get() = Feature(2L)
            /**
             * TextServer supports vertical layouts.
             *
             * Generated from Godot docs: TextServer.FEATURE_VERTICAL_LAYOUT
             */
            val VERTICAL_LAYOUT: Feature get() = Feature(4L)
            /**
             * TextServer supports complex text shaping.
             *
             * Generated from Godot docs: TextServer.FEATURE_SHAPING
             */
            val SHAPING: Feature get() = Feature(8L)
            /**
             * TextServer supports justification using kashidas.
             *
             * Generated from Godot docs: TextServer.FEATURE_KASHIDA_JUSTIFICATION
             */
            val KASHIDA_JUSTIFICATION: Feature get() = Feature(16L)
            /**
             * TextServer supports complex line/word breaking rules (e.g. dictionary based).
             *
             * Generated from Godot docs: TextServer.FEATURE_BREAK_ITERATORS
             */
            val BREAK_ITERATORS: Feature get() = Feature(32L)
            /**
             * TextServer supports loading bitmap fonts.
             *
             * Generated from Godot docs: TextServer.FEATURE_FONT_BITMAP
             */
            val FONT_BITMAP: Feature get() = Feature(64L)
            /**
             * TextServer supports loading dynamic (TrueType, OpeType, etc.) fonts.
             *
             * Generated from Godot docs: TextServer.FEATURE_FONT_DYNAMIC
             */
            val FONT_DYNAMIC: Feature get() = Feature(128L)
            /**
             * TextServer supports multichannel signed distance field dynamic font rendering.
             *
             * Generated from Godot docs: TextServer.FEATURE_FONT_MSDF
             */
            val FONT_MSDF: Feature get() = Feature(256L)
            /**
             * TextServer supports loading system fonts.
             *
             * Generated from Godot docs: TextServer.FEATURE_FONT_SYSTEM
             */
            val FONT_SYSTEM: Feature get() = Feature(512L)
            /**
             * TextServer supports variable fonts.
             *
             * Generated from Godot docs: TextServer.FEATURE_FONT_VARIABLE
             */
            val FONT_VARIABLE: Feature get() = Feature(1024L)
            /**
             * TextServer supports locale dependent and context sensitive case conversion.
             *
             * Generated from Godot docs: TextServer.FEATURE_CONTEXT_SENSITIVE_CASE_CONVERSION
             */
            val CONTEXT_SENSITIVE_CASE_CONVERSION: Feature get() = Feature(2048L)
            /**
             * TextServer require external data file for some features, see `load_support_data`.
             *
             * Generated from Godot docs: TextServer.FEATURE_USE_SUPPORT_DATA
             */
            val USE_SUPPORT_DATA: Feature get() = Feature(4096L)
            /**
             * TextServer supports UAX #31 identifier validation, see `is_valid_identifier`.
             *
             * Generated from Godot docs: TextServer.FEATURE_UNICODE_IDENTIFIERS
             */
            val UNICODE_IDENTIFIERS: Feature get() = Feature(8192L)
            /**
             * TextServer supports Unicode Technical Report #36 (https://unicode.org/reports/tr36/) and Unicode
             * Technical Standard #39 (https://unicode.org/reports/tr39/) based spoof detection features.
             *
             * Generated from Godot docs: TextServer.FEATURE_UNICODE_SECURITY
             */
            val UNICODE_SECURITY: Feature get() = Feature(16384L)
        }
    }

    /**
     * Godot's `TextServer.ContourPointTag` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`TextServer.ContourPointTag.<NAME>`).
     *
     * Generated from Godot docs: TextServer.ContourPointTag
     */
    @JvmInline
    value class ContourPointTag(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Contour point is on the curve.
             *
             * Generated from Godot docs: TextServer.CONTOUR_CURVE_TAG_ON
             */
            val ON: ContourPointTag get() = ContourPointTag(1L)
            /**
             * Contour point isn't on the curve, but serves as a control point for a conic (quadratic) Bézier
             * arc.
             *
             * Generated from Godot docs: TextServer.CONTOUR_CURVE_TAG_OFF_CONIC
             */
            val OFF_CONIC: ContourPointTag get() = ContourPointTag(0L)
            /**
             * Contour point isn't on the curve, but serves as a control point for a cubic Bézier arc.
             *
             * Generated from Godot docs: TextServer.CONTOUR_CURVE_TAG_OFF_CUBIC
             */
            val OFF_CUBIC: ContourPointTag get() = ContourPointTag(2L)
        }
    }

    /**
     * Godot's `TextServer.SpacingType` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`TextServer.SpacingType.<NAME>`).
     *
     * Generated from Godot docs: TextServer.SpacingType
     */
    @JvmInline
    value class SpacingType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Spacing for each glyph.
             *
             * Generated from Godot docs: TextServer.SPACING_GLYPH
             */
            val GLYPH: SpacingType get() = SpacingType(0L)
            /**
             * Spacing for the space character.
             *
             * Generated from Godot docs: TextServer.SPACING_SPACE
             */
            val SPACE: SpacingType get() = SpacingType(1L)
            /**
             * Spacing at the top of the line.
             *
             * Generated from Godot docs: TextServer.SPACING_TOP
             */
            val TOP: SpacingType get() = SpacingType(2L)
            /**
             * Spacing at the bottom of the line.
             *
             * Generated from Godot docs: TextServer.SPACING_BOTTOM
             */
            val BOTTOM: SpacingType get() = SpacingType(3L)
            /**
             * Represents the size of the `SpacingType` enum.
             *
             * Generated from Godot docs: TextServer.SPACING_MAX
             */
            val MAX: SpacingType get() = SpacingType(4L)
        }
    }

    /**
     * Godot's `TextServer.FontStyle` bitfield as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`TextServer.FontStyle.<NAME>`).
     *
     * Generated from Godot docs: TextServer.FontStyle
     */
    @JvmInline
    value class FontStyle(override val value: Long) : GodotEnumValue {
        infix fun or(other: FontStyle): FontStyle = FontStyle(value or other.value)

        infix fun and(other: FontStyle): FontStyle = FontStyle(value and other.value)

        infix fun xor(other: FontStyle): FontStyle = FontStyle(value xor other.value)

        fun inv(): FontStyle = FontStyle(value.inv())

        operator fun contains(other: FontStyle): Boolean = (value and other.value) == other.value

        companion object {
            /**
             * Font is bold.
             *
             * Generated from Godot docs: TextServer.FONT_BOLD
             */
            val BOLD: FontStyle get() = FontStyle(1L)
            /**
             * Font is italic or oblique.
             *
             * Generated from Godot docs: TextServer.FONT_ITALIC
             */
            val ITALIC: FontStyle get() = FontStyle(2L)
            /**
             * Font has fixed-width characters (also known as monospace).
             *
             * Generated from Godot docs: TextServer.FONT_FIXED_WIDTH
             */
            val FIXED_WIDTH: FontStyle get() = FontStyle(4L)
        }
    }

    /**
     * Godot's `TextServer.StructuredTextParser` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`TextServer.StructuredTextParser.<NAME>`).
     *
     * Generated from Godot docs: TextServer.StructuredTextParser
     */
    @JvmInline
    value class StructuredTextParser(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use default Unicode BiDi algorithm.
             *
             * Generated from Godot docs: TextServer.STRUCTURED_TEXT_DEFAULT
             */
            val DEFAULT: StructuredTextParser get() = StructuredTextParser(0L)
            /**
             * BiDi override for URI.
             *
             * Generated from Godot docs: TextServer.STRUCTURED_TEXT_URI
             */
            val URI: StructuredTextParser get() = StructuredTextParser(1L)
            /**
             * BiDi override for file path.
             *
             * Generated from Godot docs: TextServer.STRUCTURED_TEXT_FILE
             */
            val FILE: StructuredTextParser get() = StructuredTextParser(2L)
            /**
             * BiDi override for email.
             *
             * Generated from Godot docs: TextServer.STRUCTURED_TEXT_EMAIL
             */
            val EMAIL: StructuredTextParser get() = StructuredTextParser(3L)
            /**
             * BiDi override for lists. Structured text options: list separator `String`.
             *
             * Generated from Godot docs: TextServer.STRUCTURED_TEXT_LIST
             */
            val LIST: StructuredTextParser get() = StructuredTextParser(4L)
            /**
             * BiDi override for GDScript.
             *
             * Generated from Godot docs: TextServer.STRUCTURED_TEXT_GDSCRIPT
             */
            val GDSCRIPT: StructuredTextParser get() = StructuredTextParser(5L)
            /**
             * User defined structured text BiDi override function.
             *
             * Generated from Godot docs: TextServer.STRUCTURED_TEXT_CUSTOM
             */
            val CUSTOM: StructuredTextParser get() = StructuredTextParser(6L)
        }
    }

    /**
     * Godot's `TextServer.FixedSizeScaleMode` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`TextServer.FixedSizeScaleMode.<NAME>`).
     *
     * Generated from Godot docs: TextServer.FixedSizeScaleMode
     */
    @JvmInline
    value class FixedSizeScaleMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Bitmap font is not scaled.
             *
             * Generated from Godot docs: TextServer.FIXED_SIZE_SCALE_DISABLE
             */
            val DISABLE: FixedSizeScaleMode get() = FixedSizeScaleMode(0L)
            /**
             * Bitmap font is scaled to the closest integer multiple of the font's fixed size. This is the
             * recommended option for pixel art fonts.
             *
             * Generated from Godot docs: TextServer.FIXED_SIZE_SCALE_INTEGER_ONLY
             */
            val INTEGER_ONLY: FixedSizeScaleMode get() = FixedSizeScaleMode(1L)
            /**
             * Bitmap font is scaled to an arbitrary (fractional) size. This is the recommended option for
             * non-pixel art fonts.
             *
             * Generated from Godot docs: TextServer.FIXED_SIZE_SCALE_ENABLED
             */
            val ENABLED: FixedSizeScaleMode get() = FixedSizeScaleMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): TextServer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): TextServer? =
            if (handle.address() == 0L) null else RefCounted.owned(TextServer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): TextServer? =
            if (handle.address() == 0L) null else TextServer(GodotHandle(handle))
    }

    private object Binds {
        private const val HAS_FEATURE_HASH = 3967367083L
        @JvmField
        val hasFeatureBind =
            ObjectCalls.getMethodBind("TextServer", "has_feature", HAS_FEATURE_HASH)

        private const val GET_NAME_HASH = 201670096L
        @JvmField
        val getNameBind =
            ObjectCalls.getMethodBind("TextServer", "get_name", GET_NAME_HASH)

        private const val GET_FEATURES_HASH = 3905245786L
        @JvmField
        val getFeaturesBind =
            ObjectCalls.getMethodBind("TextServer", "get_features", GET_FEATURES_HASH)

        private const val LOAD_SUPPORT_DATA_HASH = 2323990056L
        @JvmField
        val loadSupportDataBind =
            ObjectCalls.getMethodBind("TextServer", "load_support_data", LOAD_SUPPORT_DATA_HASH)

        private const val GET_SUPPORT_DATA_FILENAME_HASH = 201670096L
        @JvmField
        val getSupportDataFilenameBind =
            ObjectCalls.getMethodBind("TextServer", "get_support_data_filename", GET_SUPPORT_DATA_FILENAME_HASH)

        private const val GET_SUPPORT_DATA_INFO_HASH = 201670096L
        @JvmField
        val getSupportDataInfoBind =
            ObjectCalls.getMethodBind("TextServer", "get_support_data_info", GET_SUPPORT_DATA_INFO_HASH)

        private const val SAVE_SUPPORT_DATA_HASH = 3927539163L
        @JvmField
        val saveSupportDataBind =
            ObjectCalls.getMethodBind("TextServer", "save_support_data", SAVE_SUPPORT_DATA_HASH)

        private const val GET_SUPPORT_DATA_HASH = 2362200018L
        @JvmField
        val getSupportDataBind =
            ObjectCalls.getMethodBind("TextServer", "get_support_data", GET_SUPPORT_DATA_HASH)

        private const val IS_LOCALE_USING_SUPPORT_DATA_HASH = 3927539163L
        @JvmField
        val isLocaleUsingSupportDataBind =
            ObjectCalls.getMethodBind("TextServer", "is_locale_using_support_data", IS_LOCALE_USING_SUPPORT_DATA_HASH)

        private const val IS_LOCALE_RIGHT_TO_LEFT_HASH = 3927539163L
        @JvmField
        val isLocaleRightToLeftBind =
            ObjectCalls.getMethodBind("TextServer", "is_locale_right_to_left", IS_LOCALE_RIGHT_TO_LEFT_HASH)

        private const val NAME_TO_TAG_HASH = 1321353865L
        @JvmField
        val nameToTagBind =
            ObjectCalls.getMethodBind("TextServer", "name_to_tag", NAME_TO_TAG_HASH)

        private const val TAG_TO_NAME_HASH = 844755477L
        @JvmField
        val tagToNameBind =
            ObjectCalls.getMethodBind("TextServer", "tag_to_name", TAG_TO_NAME_HASH)

        private const val HAS_HASH = 3521089500L
        @JvmField
        val hasBind =
            ObjectCalls.getMethodBind("TextServer", "has", HAS_HASH)

        private const val FREE_RID_HASH = 2722037293L
        @JvmField
        val freeRidBind =
            ObjectCalls.getMethodBind("TextServer", "free_rid", FREE_RID_HASH)

        private const val CREATE_FONT_HASH = 529393457L
        @JvmField
        val createFontBind =
            ObjectCalls.getMethodBind("TextServer", "create_font", CREATE_FONT_HASH)

        private const val CREATE_FONT_LINKED_VARIATION_HASH = 41030802L
        @JvmField
        val createFontLinkedVariationBind =
            ObjectCalls.getMethodBind("TextServer", "create_font_linked_variation", CREATE_FONT_LINKED_VARIATION_HASH)

        private const val FONT_SET_DATA_HASH = 1355495400L
        @JvmField
        val fontSetDataBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_data", FONT_SET_DATA_HASH)

        private const val FONT_SET_FACE_INDEX_HASH = 3411492887L
        @JvmField
        val fontSetFaceIndexBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_face_index", FONT_SET_FACE_INDEX_HASH)

        private const val FONT_GET_FACE_INDEX_HASH = 2198884583L
        @JvmField
        val fontGetFaceIndexBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_face_index", FONT_GET_FACE_INDEX_HASH)

        private const val FONT_GET_FACE_COUNT_HASH = 2198884583L
        @JvmField
        val fontGetFaceCountBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_face_count", FONT_GET_FACE_COUNT_HASH)

        private const val FONT_SET_STYLE_HASH = 898466325L
        @JvmField
        val fontSetStyleBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_style", FONT_SET_STYLE_HASH)

        private const val FONT_GET_STYLE_HASH = 3082502592L
        @JvmField
        val fontGetStyleBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_style", FONT_GET_STYLE_HASH)

        private const val FONT_SET_NAME_HASH = 2726140452L
        @JvmField
        val fontSetNameBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_name", FONT_SET_NAME_HASH)

        private const val FONT_GET_NAME_HASH = 642473191L
        @JvmField
        val fontGetNameBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_name", FONT_GET_NAME_HASH)

        private const val FONT_GET_OT_NAME_STRINGS_HASH = 1882737106L
        @JvmField
        val fontGetOtNameStringsBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_ot_name_strings", FONT_GET_OT_NAME_STRINGS_HASH)

        private const val FONT_SET_STYLE_NAME_HASH = 2726140452L
        @JvmField
        val fontSetStyleNameBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_style_name", FONT_SET_STYLE_NAME_HASH)

        private const val FONT_GET_STYLE_NAME_HASH = 642473191L
        @JvmField
        val fontGetStyleNameBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_style_name", FONT_GET_STYLE_NAME_HASH)

        private const val FONT_SET_WEIGHT_HASH = 3411492887L
        @JvmField
        val fontSetWeightBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_weight", FONT_SET_WEIGHT_HASH)

        private const val FONT_GET_WEIGHT_HASH = 2198884583L
        @JvmField
        val fontGetWeightBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_weight", FONT_GET_WEIGHT_HASH)

        private const val FONT_SET_STRETCH_HASH = 3411492887L
        @JvmField
        val fontSetStretchBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_stretch", FONT_SET_STRETCH_HASH)

        private const val FONT_GET_STRETCH_HASH = 2198884583L
        @JvmField
        val fontGetStretchBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_stretch", FONT_GET_STRETCH_HASH)

        private const val FONT_SET_ANTIALIASING_HASH = 958337235L
        @JvmField
        val fontSetAntialiasingBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_antialiasing", FONT_SET_ANTIALIASING_HASH)

        private const val FONT_GET_ANTIALIASING_HASH = 3389420495L
        @JvmField
        val fontGetAntialiasingBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_antialiasing", FONT_GET_ANTIALIASING_HASH)

        private const val FONT_SET_DISABLE_EMBEDDED_BITMAPS_HASH = 1265174801L
        @JvmField
        val fontSetDisableEmbeddedBitmapsBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_disable_embedded_bitmaps", FONT_SET_DISABLE_EMBEDDED_BITMAPS_HASH)

        private const val FONT_GET_DISABLE_EMBEDDED_BITMAPS_HASH = 4155700596L
        @JvmField
        val fontGetDisableEmbeddedBitmapsBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_disable_embedded_bitmaps", FONT_GET_DISABLE_EMBEDDED_BITMAPS_HASH)

        private const val FONT_SET_GENERATE_MIPMAPS_HASH = 1265174801L
        @JvmField
        val fontSetGenerateMipmapsBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_generate_mipmaps", FONT_SET_GENERATE_MIPMAPS_HASH)

        private const val FONT_GET_GENERATE_MIPMAPS_HASH = 4155700596L
        @JvmField
        val fontGetGenerateMipmapsBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_generate_mipmaps", FONT_GET_GENERATE_MIPMAPS_HASH)

        private const val FONT_SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 1265174801L
        @JvmField
        val fontSetMultichannelSignedDistanceFieldBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_multichannel_signed_distance_field", FONT_SET_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)

        private const val FONT_IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH = 4155700596L
        @JvmField
        val fontIsMultichannelSignedDistanceFieldBind =
            ObjectCalls.getMethodBind("TextServer", "font_is_multichannel_signed_distance_field", FONT_IS_MULTICHANNEL_SIGNED_DISTANCE_FIELD_HASH)

        private const val FONT_SET_MSDF_PIXEL_RANGE_HASH = 3411492887L
        @JvmField
        val fontSetMsdfPixelRangeBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_msdf_pixel_range", FONT_SET_MSDF_PIXEL_RANGE_HASH)

        private const val FONT_GET_MSDF_PIXEL_RANGE_HASH = 2198884583L
        @JvmField
        val fontGetMsdfPixelRangeBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_msdf_pixel_range", FONT_GET_MSDF_PIXEL_RANGE_HASH)

        private const val FONT_SET_MSDF_SIZE_HASH = 3411492887L
        @JvmField
        val fontSetMsdfSizeBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_msdf_size", FONT_SET_MSDF_SIZE_HASH)

        private const val FONT_GET_MSDF_SIZE_HASH = 2198884583L
        @JvmField
        val fontGetMsdfSizeBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_msdf_size", FONT_GET_MSDF_SIZE_HASH)

        private const val FONT_SET_FIXED_SIZE_HASH = 3411492887L
        @JvmField
        val fontSetFixedSizeBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_fixed_size", FONT_SET_FIXED_SIZE_HASH)

        private const val FONT_GET_FIXED_SIZE_HASH = 2198884583L
        @JvmField
        val fontGetFixedSizeBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_fixed_size", FONT_GET_FIXED_SIZE_HASH)

        private const val FONT_SET_FIXED_SIZE_SCALE_MODE_HASH = 1029390307L
        @JvmField
        val fontSetFixedSizeScaleModeBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_fixed_size_scale_mode", FONT_SET_FIXED_SIZE_SCALE_MODE_HASH)

        private const val FONT_GET_FIXED_SIZE_SCALE_MODE_HASH = 4113120379L
        @JvmField
        val fontGetFixedSizeScaleModeBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_fixed_size_scale_mode", FONT_GET_FIXED_SIZE_SCALE_MODE_HASH)

        private const val FONT_SET_ALLOW_SYSTEM_FALLBACK_HASH = 1265174801L
        @JvmField
        val fontSetAllowSystemFallbackBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_allow_system_fallback", FONT_SET_ALLOW_SYSTEM_FALLBACK_HASH)

        private const val FONT_IS_ALLOW_SYSTEM_FALLBACK_HASH = 4155700596L
        @JvmField
        val fontIsAllowSystemFallbackBind =
            ObjectCalls.getMethodBind("TextServer", "font_is_allow_system_fallback", FONT_IS_ALLOW_SYSTEM_FALLBACK_HASH)

        private const val FONT_CLEAR_SYSTEM_FALLBACK_CACHE_HASH = 3218959716L
        @JvmField
        val fontClearSystemFallbackCacheBind =
            ObjectCalls.getMethodBind("TextServer", "font_clear_system_fallback_cache", FONT_CLEAR_SYSTEM_FALLBACK_CACHE_HASH)

        private const val FONT_SET_FORCE_AUTOHINTER_HASH = 1265174801L
        @JvmField
        val fontSetForceAutohinterBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_force_autohinter", FONT_SET_FORCE_AUTOHINTER_HASH)

        private const val FONT_IS_FORCE_AUTOHINTER_HASH = 4155700596L
        @JvmField
        val fontIsForceAutohinterBind =
            ObjectCalls.getMethodBind("TextServer", "font_is_force_autohinter", FONT_IS_FORCE_AUTOHINTER_HASH)

        private const val FONT_SET_MODULATE_COLOR_GLYPHS_HASH = 1265174801L
        @JvmField
        val fontSetModulateColorGlyphsBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_modulate_color_glyphs", FONT_SET_MODULATE_COLOR_GLYPHS_HASH)

        private const val FONT_IS_MODULATE_COLOR_GLYPHS_HASH = 4155700596L
        @JvmField
        val fontIsModulateColorGlyphsBind =
            ObjectCalls.getMethodBind("TextServer", "font_is_modulate_color_glyphs", FONT_IS_MODULATE_COLOR_GLYPHS_HASH)

        private const val FONT_GET_PALETTE_COUNT_HASH = 2198884583L
        @JvmField
        val fontGetPaletteCountBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_palette_count", FONT_GET_PALETTE_COUNT_HASH)

        private const val FONT_GET_PALETTE_NAME_HASH = 1464764419L
        @JvmField
        val fontGetPaletteNameBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_palette_name", FONT_GET_PALETTE_NAME_HASH)

        private const val FONT_GET_PALETTE_COLORS_HASH = 1595517857L
        @JvmField
        val fontGetPaletteColorsBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_palette_colors", FONT_GET_PALETTE_COLORS_HASH)

        private const val FONT_SET_PALETTE_CUSTOM_COLORS_HASH = 4037098590L
        @JvmField
        val fontSetPaletteCustomColorsBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_palette_custom_colors", FONT_SET_PALETTE_CUSTOM_COLORS_HASH)

        private const val FONT_GET_PALETTE_CUSTOM_COLORS_HASH = 1569415609L
        @JvmField
        val fontGetPaletteCustomColorsBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_palette_custom_colors", FONT_GET_PALETTE_CUSTOM_COLORS_HASH)

        private const val FONT_GET_USED_PALETTE_HASH = 2198884583L
        @JvmField
        val fontGetUsedPaletteBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_used_palette", FONT_GET_USED_PALETTE_HASH)

        private const val FONT_SET_USED_PALETTE_HASH = 3411492887L
        @JvmField
        val fontSetUsedPaletteBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_used_palette", FONT_SET_USED_PALETTE_HASH)

        private const val FONT_SET_HINTING_HASH = 1520010864L
        @JvmField
        val fontSetHintingBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_hinting", FONT_SET_HINTING_HASH)

        private const val FONT_GET_HINTING_HASH = 3971592737L
        @JvmField
        val fontGetHintingBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_hinting", FONT_GET_HINTING_HASH)

        private const val FONT_SET_SUBPIXEL_POSITIONING_HASH = 3830459669L
        @JvmField
        val fontSetSubpixelPositioningBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_subpixel_positioning", FONT_SET_SUBPIXEL_POSITIONING_HASH)

        private const val FONT_GET_SUBPIXEL_POSITIONING_HASH = 2752233671L
        @JvmField
        val fontGetSubpixelPositioningBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_subpixel_positioning", FONT_GET_SUBPIXEL_POSITIONING_HASH)

        private const val FONT_SET_KEEP_ROUNDING_REMAINDERS_HASH = 1265174801L
        @JvmField
        val fontSetKeepRoundingRemaindersBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_keep_rounding_remainders", FONT_SET_KEEP_ROUNDING_REMAINDERS_HASH)

        private const val FONT_GET_KEEP_ROUNDING_REMAINDERS_HASH = 4155700596L
        @JvmField
        val fontGetKeepRoundingRemaindersBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_keep_rounding_remainders", FONT_GET_KEEP_ROUNDING_REMAINDERS_HASH)

        private const val FONT_SET_EMBOLDEN_HASH = 1794382983L
        @JvmField
        val fontSetEmboldenBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_embolden", FONT_SET_EMBOLDEN_HASH)

        private const val FONT_GET_EMBOLDEN_HASH = 866169185L
        @JvmField
        val fontGetEmboldenBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_embolden", FONT_GET_EMBOLDEN_HASH)

        private const val FONT_SET_SPACING_HASH = 1307259930L
        @JvmField
        val fontSetSpacingBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_spacing", FONT_SET_SPACING_HASH)

        private const val FONT_GET_SPACING_HASH = 1213653558L
        @JvmField
        val fontGetSpacingBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_spacing", FONT_GET_SPACING_HASH)

        private const val FONT_SET_BASELINE_OFFSET_HASH = 1794382983L
        @JvmField
        val fontSetBaselineOffsetBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_baseline_offset", FONT_SET_BASELINE_OFFSET_HASH)

        private const val FONT_GET_BASELINE_OFFSET_HASH = 866169185L
        @JvmField
        val fontGetBaselineOffsetBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_baseline_offset", FONT_GET_BASELINE_OFFSET_HASH)

        private const val FONT_SET_TRANSFORM_HASH = 1246044741L
        @JvmField
        val fontSetTransformBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_transform", FONT_SET_TRANSFORM_HASH)

        private const val FONT_GET_TRANSFORM_HASH = 213527486L
        @JvmField
        val fontGetTransformBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_transform", FONT_GET_TRANSFORM_HASH)

        private const val FONT_SET_VARIATION_COORDINATES_HASH = 1217542888L
        @JvmField
        val fontSetVariationCoordinatesBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_variation_coordinates", FONT_SET_VARIATION_COORDINATES_HASH)

        private const val FONT_GET_VARIATION_COORDINATES_HASH = 1882737106L
        @JvmField
        val fontGetVariationCoordinatesBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_variation_coordinates", FONT_GET_VARIATION_COORDINATES_HASH)

        private const val FONT_SET_OVERSAMPLING_HASH = 1794382983L
        @JvmField
        val fontSetOversamplingBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_oversampling", FONT_SET_OVERSAMPLING_HASH)

        private const val FONT_GET_OVERSAMPLING_HASH = 866169185L
        @JvmField
        val fontGetOversamplingBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_oversampling", FONT_GET_OVERSAMPLING_HASH)

        private const val FONT_GET_SIZE_CACHE_LIST_HASH = 2684255073L
        @JvmField
        val fontGetSizeCacheListBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_size_cache_list", FONT_GET_SIZE_CACHE_LIST_HASH)

        private const val FONT_CLEAR_SIZE_CACHE_HASH = 2722037293L
        @JvmField
        val fontClearSizeCacheBind =
            ObjectCalls.getMethodBind("TextServer", "font_clear_size_cache", FONT_CLEAR_SIZE_CACHE_HASH)

        private const val FONT_REMOVE_SIZE_CACHE_HASH = 2450610377L
        @JvmField
        val fontRemoveSizeCacheBind =
            ObjectCalls.getMethodBind("TextServer", "font_remove_size_cache", FONT_REMOVE_SIZE_CACHE_HASH)

        private const val FONT_GET_SIZE_CACHE_INFO_HASH = 2684255073L
        @JvmField
        val fontGetSizeCacheInfoBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_size_cache_info", FONT_GET_SIZE_CACHE_INFO_HASH)

        private const val FONT_SET_ASCENT_HASH = 1892459533L
        @JvmField
        val fontSetAscentBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_ascent", FONT_SET_ASCENT_HASH)

        private const val FONT_GET_ASCENT_HASH = 755457166L
        @JvmField
        val fontGetAscentBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_ascent", FONT_GET_ASCENT_HASH)

        private const val FONT_SET_DESCENT_HASH = 1892459533L
        @JvmField
        val fontSetDescentBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_descent", FONT_SET_DESCENT_HASH)

        private const val FONT_GET_DESCENT_HASH = 755457166L
        @JvmField
        val fontGetDescentBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_descent", FONT_GET_DESCENT_HASH)

        private const val FONT_SET_UNDERLINE_POSITION_HASH = 1892459533L
        @JvmField
        val fontSetUnderlinePositionBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_underline_position", FONT_SET_UNDERLINE_POSITION_HASH)

        private const val FONT_GET_UNDERLINE_POSITION_HASH = 755457166L
        @JvmField
        val fontGetUnderlinePositionBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_underline_position", FONT_GET_UNDERLINE_POSITION_HASH)

        private const val FONT_SET_UNDERLINE_THICKNESS_HASH = 1892459533L
        @JvmField
        val fontSetUnderlineThicknessBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_underline_thickness", FONT_SET_UNDERLINE_THICKNESS_HASH)

        private const val FONT_GET_UNDERLINE_THICKNESS_HASH = 755457166L
        @JvmField
        val fontGetUnderlineThicknessBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_underline_thickness", FONT_GET_UNDERLINE_THICKNESS_HASH)

        private const val FONT_SET_SCALE_HASH = 1892459533L
        @JvmField
        val fontSetScaleBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_scale", FONT_SET_SCALE_HASH)

        private const val FONT_GET_SCALE_HASH = 755457166L
        @JvmField
        val fontGetScaleBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_scale", FONT_GET_SCALE_HASH)

        private const val FONT_GET_TEXTURE_COUNT_HASH = 1311001310L
        @JvmField
        val fontGetTextureCountBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_texture_count", FONT_GET_TEXTURE_COUNT_HASH)

        private const val FONT_CLEAR_TEXTURES_HASH = 2450610377L
        @JvmField
        val fontClearTexturesBind =
            ObjectCalls.getMethodBind("TextServer", "font_clear_textures", FONT_CLEAR_TEXTURES_HASH)

        private const val FONT_REMOVE_TEXTURE_HASH = 3810512262L
        @JvmField
        val fontRemoveTextureBind =
            ObjectCalls.getMethodBind("TextServer", "font_remove_texture", FONT_REMOVE_TEXTURE_HASH)

        private const val FONT_SET_TEXTURE_IMAGE_HASH = 2354485091L
        @JvmField
        val fontSetTextureImageBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_texture_image", FONT_SET_TEXTURE_IMAGE_HASH)

        private const val FONT_GET_TEXTURE_IMAGE_HASH = 2451761155L
        @JvmField
        val fontGetTextureImageBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_texture_image", FONT_GET_TEXTURE_IMAGE_HASH)

        private const val FONT_SET_TEXTURE_OFFSETS_HASH = 3005398047L
        @JvmField
        val fontSetTextureOffsetsBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_texture_offsets", FONT_SET_TEXTURE_OFFSETS_HASH)

        private const val FONT_GET_TEXTURE_OFFSETS_HASH = 3420028887L
        @JvmField
        val fontGetTextureOffsetsBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_texture_offsets", FONT_GET_TEXTURE_OFFSETS_HASH)

        private const val FONT_GET_GLYPH_LIST_HASH = 46086620L
        @JvmField
        val fontGetGlyphListBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_list", FONT_GET_GLYPH_LIST_HASH)

        private const val FONT_CLEAR_GLYPHS_HASH = 2450610377L
        @JvmField
        val fontClearGlyphsBind =
            ObjectCalls.getMethodBind("TextServer", "font_clear_glyphs", FONT_CLEAR_GLYPHS_HASH)

        private const val FONT_REMOVE_GLYPH_HASH = 3810512262L
        @JvmField
        val fontRemoveGlyphBind =
            ObjectCalls.getMethodBind("TextServer", "font_remove_glyph", FONT_REMOVE_GLYPH_HASH)

        private const val FONT_GET_GLYPH_ADVANCE_HASH = 2555689501L
        @JvmField
        val fontGetGlyphAdvanceBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_advance", FONT_GET_GLYPH_ADVANCE_HASH)

        private const val FONT_SET_GLYPH_ADVANCE_HASH = 3219397315L
        @JvmField
        val fontSetGlyphAdvanceBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_advance", FONT_SET_GLYPH_ADVANCE_HASH)

        private const val FONT_GET_GLYPH_OFFSET_HASH = 513728628L
        @JvmField
        val fontGetGlyphOffsetBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_offset", FONT_GET_GLYPH_OFFSET_HASH)

        private const val FONT_SET_GLYPH_OFFSET_HASH = 1812632090L
        @JvmField
        val fontSetGlyphOffsetBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_offset", FONT_SET_GLYPH_OFFSET_HASH)

        private const val FONT_GET_GLYPH_SIZE_HASH = 513728628L
        @JvmField
        val fontGetGlyphSizeBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_size", FONT_GET_GLYPH_SIZE_HASH)

        private const val FONT_SET_GLYPH_SIZE_HASH = 1812632090L
        @JvmField
        val fontSetGlyphSizeBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_size", FONT_SET_GLYPH_SIZE_HASH)

        private const val FONT_GET_GLYPH_UV_RECT_HASH = 2274268786L
        @JvmField
        val fontGetGlyphUvRectBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_uv_rect", FONT_GET_GLYPH_UV_RECT_HASH)

        private const val FONT_SET_GLYPH_UV_RECT_HASH = 1973324081L
        @JvmField
        val fontSetGlyphUvRectBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_uv_rect", FONT_SET_GLYPH_UV_RECT_HASH)

        private const val FONT_GET_GLYPH_TEXTURE_IDX_HASH = 4292800474L
        @JvmField
        val fontGetGlyphTextureIdxBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_texture_idx", FONT_GET_GLYPH_TEXTURE_IDX_HASH)

        private const val FONT_SET_GLYPH_TEXTURE_IDX_HASH = 4254580980L
        @JvmField
        val fontSetGlyphTextureIdxBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_glyph_texture_idx", FONT_SET_GLYPH_TEXTURE_IDX_HASH)

        private const val FONT_GET_GLYPH_TEXTURE_RID_HASH = 1451696141L
        @JvmField
        val fontGetGlyphTextureRidBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_texture_rid", FONT_GET_GLYPH_TEXTURE_RID_HASH)

        private const val FONT_GET_GLYPH_TEXTURE_SIZE_HASH = 513728628L
        @JvmField
        val fontGetGlyphTextureSizeBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_texture_size", FONT_GET_GLYPH_TEXTURE_SIZE_HASH)

        private const val FONT_GET_GLYPH_CONTOURS_HASH = 2903964473L
        @JvmField
        val fontGetGlyphContoursBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_contours", FONT_GET_GLYPH_CONTOURS_HASH)

        private const val FONT_GET_KERNING_LIST_HASH = 1778388067L
        @JvmField
        val fontGetKerningListBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_kerning_list", FONT_GET_KERNING_LIST_HASH)

        private const val FONT_CLEAR_KERNING_MAP_HASH = 3411492887L
        @JvmField
        val fontClearKerningMapBind =
            ObjectCalls.getMethodBind("TextServer", "font_clear_kerning_map", FONT_CLEAR_KERNING_MAP_HASH)

        private const val FONT_REMOVE_KERNING_HASH = 2141860016L
        @JvmField
        val fontRemoveKerningBind =
            ObjectCalls.getMethodBind("TextServer", "font_remove_kerning", FONT_REMOVE_KERNING_HASH)

        private const val FONT_SET_KERNING_HASH = 3630965883L
        @JvmField
        val fontSetKerningBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_kerning", FONT_SET_KERNING_HASH)

        private const val FONT_GET_KERNING_HASH = 1019980169L
        @JvmField
        val fontGetKerningBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_kerning", FONT_GET_KERNING_HASH)

        private const val FONT_GET_GLYPH_INDEX_HASH = 1765635060L
        @JvmField
        val fontGetGlyphIndexBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_glyph_index", FONT_GET_GLYPH_INDEX_HASH)

        private const val FONT_GET_CHAR_FROM_GLYPH_INDEX_HASH = 2156738276L
        @JvmField
        val fontGetCharFromGlyphIndexBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_char_from_glyph_index", FONT_GET_CHAR_FROM_GLYPH_INDEX_HASH)

        private const val FONT_HAS_CHAR_HASH = 3120086654L
        @JvmField
        val fontHasCharBind =
            ObjectCalls.getMethodBind("TextServer", "font_has_char", FONT_HAS_CHAR_HASH)

        private const val FONT_GET_SUPPORTED_CHARS_HASH = 642473191L
        @JvmField
        val fontGetSupportedCharsBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_supported_chars", FONT_GET_SUPPORTED_CHARS_HASH)

        private const val FONT_GET_SUPPORTED_GLYPHS_HASH = 788230395L
        @JvmField
        val fontGetSupportedGlyphsBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_supported_glyphs", FONT_GET_SUPPORTED_GLYPHS_HASH)

        private const val FONT_RENDER_RANGE_HASH = 4254580980L
        @JvmField
        val fontRenderRangeBind =
            ObjectCalls.getMethodBind("TextServer", "font_render_range", FONT_RENDER_RANGE_HASH)

        private const val FONT_RENDER_GLYPH_HASH = 3810512262L
        @JvmField
        val fontRenderGlyphBind =
            ObjectCalls.getMethodBind("TextServer", "font_render_glyph", FONT_RENDER_GLYPH_HASH)

        private const val FONT_DRAW_GLYPH_HASH = 3103234926L
        @JvmField
        val fontDrawGlyphBind =
            ObjectCalls.getMethodBind("TextServer", "font_draw_glyph", FONT_DRAW_GLYPH_HASH)

        private const val FONT_DRAW_GLYPH_OUTLINE_HASH = 1976041553L
        @JvmField
        val fontDrawGlyphOutlineBind =
            ObjectCalls.getMethodBind("TextServer", "font_draw_glyph_outline", FONT_DRAW_GLYPH_OUTLINE_HASH)

        private const val FONT_IS_LANGUAGE_SUPPORTED_HASH = 3199320846L
        @JvmField
        val fontIsLanguageSupportedBind =
            ObjectCalls.getMethodBind("TextServer", "font_is_language_supported", FONT_IS_LANGUAGE_SUPPORTED_HASH)

        private const val FONT_SET_LANGUAGE_SUPPORT_OVERRIDE_HASH = 2313957094L
        @JvmField
        val fontSetLanguageSupportOverrideBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_language_support_override", FONT_SET_LANGUAGE_SUPPORT_OVERRIDE_HASH)

        private const val FONT_GET_LANGUAGE_SUPPORT_OVERRIDE_HASH = 2829184646L
        @JvmField
        val fontGetLanguageSupportOverrideBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_language_support_override", FONT_GET_LANGUAGE_SUPPORT_OVERRIDE_HASH)

        private const val FONT_REMOVE_LANGUAGE_SUPPORT_OVERRIDE_HASH = 2726140452L
        @JvmField
        val fontRemoveLanguageSupportOverrideBind =
            ObjectCalls.getMethodBind("TextServer", "font_remove_language_support_override", FONT_REMOVE_LANGUAGE_SUPPORT_OVERRIDE_HASH)

        private const val FONT_GET_LANGUAGE_SUPPORT_OVERRIDES_HASH = 2801473409L
        @JvmField
        val fontGetLanguageSupportOverridesBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_language_support_overrides", FONT_GET_LANGUAGE_SUPPORT_OVERRIDES_HASH)

        private const val FONT_IS_SCRIPT_SUPPORTED_HASH = 3199320846L
        @JvmField
        val fontIsScriptSupportedBind =
            ObjectCalls.getMethodBind("TextServer", "font_is_script_supported", FONT_IS_SCRIPT_SUPPORTED_HASH)

        private const val FONT_SET_SCRIPT_SUPPORT_OVERRIDE_HASH = 2313957094L
        @JvmField
        val fontSetScriptSupportOverrideBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_script_support_override", FONT_SET_SCRIPT_SUPPORT_OVERRIDE_HASH)

        private const val FONT_GET_SCRIPT_SUPPORT_OVERRIDE_HASH = 2829184646L
        @JvmField
        val fontGetScriptSupportOverrideBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_script_support_override", FONT_GET_SCRIPT_SUPPORT_OVERRIDE_HASH)

        private const val FONT_REMOVE_SCRIPT_SUPPORT_OVERRIDE_HASH = 2726140452L
        @JvmField
        val fontRemoveScriptSupportOverrideBind =
            ObjectCalls.getMethodBind("TextServer", "font_remove_script_support_override", FONT_REMOVE_SCRIPT_SUPPORT_OVERRIDE_HASH)

        private const val FONT_GET_SCRIPT_SUPPORT_OVERRIDES_HASH = 2801473409L
        @JvmField
        val fontGetScriptSupportOverridesBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_script_support_overrides", FONT_GET_SCRIPT_SUPPORT_OVERRIDES_HASH)

        private const val FONT_SET_OPENTYPE_FEATURE_OVERRIDES_HASH = 1217542888L
        @JvmField
        val fontSetOpentypeFeatureOverridesBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_opentype_feature_overrides", FONT_SET_OPENTYPE_FEATURE_OVERRIDES_HASH)

        private const val FONT_GET_OPENTYPE_FEATURE_OVERRIDES_HASH = 1882737106L
        @JvmField
        val fontGetOpentypeFeatureOverridesBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_opentype_feature_overrides", FONT_GET_OPENTYPE_FEATURE_OVERRIDES_HASH)

        private const val FONT_SUPPORTED_FEATURE_LIST_HASH = 1882737106L
        @JvmField
        val fontSupportedFeatureListBind =
            ObjectCalls.getMethodBind("TextServer", "font_supported_feature_list", FONT_SUPPORTED_FEATURE_LIST_HASH)

        private const val FONT_SUPPORTED_VARIATION_LIST_HASH = 1882737106L
        @JvmField
        val fontSupportedVariationListBind =
            ObjectCalls.getMethodBind("TextServer", "font_supported_variation_list", FONT_SUPPORTED_VARIATION_LIST_HASH)

        private const val FONT_GET_GLOBAL_OVERSAMPLING_HASH = 1740695150L
        @JvmField
        val fontGetGlobalOversamplingBind =
            ObjectCalls.getMethodBind("TextServer", "font_get_global_oversampling", FONT_GET_GLOBAL_OVERSAMPLING_HASH)

        private const val FONT_SET_GLOBAL_OVERSAMPLING_HASH = 373806689L
        @JvmField
        val fontSetGlobalOversamplingBind =
            ObjectCalls.getMethodBind("TextServer", "font_set_global_oversampling", FONT_SET_GLOBAL_OVERSAMPLING_HASH)

        private const val GET_HEX_CODE_BOX_SIZE_HASH = 3016396712L
        @JvmField
        val getHexCodeBoxSizeBind =
            ObjectCalls.getMethodBind("TextServer", "get_hex_code_box_size", GET_HEX_CODE_BOX_SIZE_HASH)

        private const val DRAW_HEX_CODE_BOX_HASH = 1602046441L
        @JvmField
        val drawHexCodeBoxBind =
            ObjectCalls.getMethodBind("TextServer", "draw_hex_code_box", DRAW_HEX_CODE_BOX_HASH)

        private const val CREATE_SHAPED_TEXT_HASH = 1231398698L
        @JvmField
        val createShapedTextBind =
            ObjectCalls.getMethodBind("TextServer", "create_shaped_text", CREATE_SHAPED_TEXT_HASH)

        private const val SHAPED_TEXT_CLEAR_HASH = 2722037293L
        @JvmField
        val shapedTextClearBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_clear", SHAPED_TEXT_CLEAR_HASH)

        private const val SHAPED_TEXT_DUPLICATE_HASH = 41030802L
        @JvmField
        val shapedTextDuplicateBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_duplicate", SHAPED_TEXT_DUPLICATE_HASH)

        private const val SHAPED_TEXT_SET_DIRECTION_HASH = 1551430183L
        @JvmField
        val shapedTextSetDirectionBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_direction", SHAPED_TEXT_SET_DIRECTION_HASH)

        private const val SHAPED_TEXT_GET_DIRECTION_HASH = 3065904362L
        @JvmField
        val shapedTextGetDirectionBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_direction", SHAPED_TEXT_GET_DIRECTION_HASH)

        private const val SHAPED_TEXT_GET_INFERRED_DIRECTION_HASH = 3065904362L
        @JvmField
        val shapedTextGetInferredDirectionBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_inferred_direction", SHAPED_TEXT_GET_INFERRED_DIRECTION_HASH)

        private const val SHAPED_TEXT_SET_BIDI_OVERRIDE_HASH = 684822712L
        @JvmField
        val shapedTextSetBidiOverrideBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_bidi_override", SHAPED_TEXT_SET_BIDI_OVERRIDE_HASH)

        private const val SHAPED_TEXT_SET_CUSTOM_PUNCTUATION_HASH = 2726140452L
        @JvmField
        val shapedTextSetCustomPunctuationBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_custom_punctuation", SHAPED_TEXT_SET_CUSTOM_PUNCTUATION_HASH)

        private const val SHAPED_TEXT_GET_CUSTOM_PUNCTUATION_HASH = 642473191L
        @JvmField
        val shapedTextGetCustomPunctuationBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_custom_punctuation", SHAPED_TEXT_GET_CUSTOM_PUNCTUATION_HASH)

        private const val SHAPED_TEXT_SET_CUSTOM_ELLIPSIS_HASH = 3411492887L
        @JvmField
        val shapedTextSetCustomEllipsisBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_custom_ellipsis", SHAPED_TEXT_SET_CUSTOM_ELLIPSIS_HASH)

        private const val SHAPED_TEXT_GET_CUSTOM_ELLIPSIS_HASH = 2198884583L
        @JvmField
        val shapedTextGetCustomEllipsisBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_custom_ellipsis", SHAPED_TEXT_GET_CUSTOM_ELLIPSIS_HASH)

        private const val SHAPED_TEXT_SET_ORIENTATION_HASH = 3019609126L
        @JvmField
        val shapedTextSetOrientationBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_orientation", SHAPED_TEXT_SET_ORIENTATION_HASH)

        private const val SHAPED_TEXT_GET_ORIENTATION_HASH = 3142708106L
        @JvmField
        val shapedTextGetOrientationBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_orientation", SHAPED_TEXT_GET_ORIENTATION_HASH)

        private const val SHAPED_TEXT_SET_PRESERVE_INVALID_HASH = 1265174801L
        @JvmField
        val shapedTextSetPreserveInvalidBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_preserve_invalid", SHAPED_TEXT_SET_PRESERVE_INVALID_HASH)

        private const val SHAPED_TEXT_GET_PRESERVE_INVALID_HASH = 4155700596L
        @JvmField
        val shapedTextGetPreserveInvalidBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_preserve_invalid", SHAPED_TEXT_GET_PRESERVE_INVALID_HASH)

        private const val SHAPED_TEXT_SET_PRESERVE_CONTROL_HASH = 1265174801L
        @JvmField
        val shapedTextSetPreserveControlBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_preserve_control", SHAPED_TEXT_SET_PRESERVE_CONTROL_HASH)

        private const val SHAPED_TEXT_GET_PRESERVE_CONTROL_HASH = 4155700596L
        @JvmField
        val shapedTextGetPreserveControlBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_preserve_control", SHAPED_TEXT_GET_PRESERVE_CONTROL_HASH)

        private const val SHAPED_TEXT_SET_SPACING_HASH = 1307259930L
        @JvmField
        val shapedTextSetSpacingBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_set_spacing", SHAPED_TEXT_SET_SPACING_HASH)

        private const val SHAPED_TEXT_GET_SPACING_HASH = 1213653558L
        @JvmField
        val shapedTextGetSpacingBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_spacing", SHAPED_TEXT_GET_SPACING_HASH)

        private const val SHAPED_TEXT_ADD_STRING_HASH = 623473029L
        @JvmField
        val shapedTextAddStringBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_add_string", SHAPED_TEXT_ADD_STRING_HASH)

        private const val SHAPED_TEXT_ADD_OBJECT_HASH = 3664424789L
        @JvmField
        val shapedTextAddObjectBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_add_object", SHAPED_TEXT_ADD_OBJECT_HASH)

        private const val SHAPED_TEXT_RESIZE_OBJECT_HASH = 790361552L
        @JvmField
        val shapedTextResizeObjectBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_resize_object", SHAPED_TEXT_RESIZE_OBJECT_HASH)

        private const val SHAPED_TEXT_HAS_OBJECT_HASH = 2360964694L
        @JvmField
        val shapedTextHasObjectBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_has_object", SHAPED_TEXT_HAS_OBJECT_HASH)

        private const val SHAPED_GET_TEXT_HASH = 642473191L
        @JvmField
        val shapedGetTextBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_text", SHAPED_GET_TEXT_HASH)

        private const val SHAPED_GET_SPAN_COUNT_HASH = 2198884583L
        @JvmField
        val shapedGetSpanCountBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_span_count", SHAPED_GET_SPAN_COUNT_HASH)

        private const val SHAPED_GET_SPAN_META_HASH = 4069510997L
        @JvmField
        val shapedGetSpanMetaBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_span_meta", SHAPED_GET_SPAN_META_HASH)

        private const val SHAPED_GET_SPAN_EMBEDDED_OBJECT_HASH = 4069510997L
        @JvmField
        val shapedGetSpanEmbeddedObjectBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_span_embedded_object", SHAPED_GET_SPAN_EMBEDDED_OBJECT_HASH)

        private const val SHAPED_GET_SPAN_TEXT_HASH = 1464764419L
        @JvmField
        val shapedGetSpanTextBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_span_text", SHAPED_GET_SPAN_TEXT_HASH)

        private const val SHAPED_GET_SPAN_OBJECT_HASH = 4069510997L
        @JvmField
        val shapedGetSpanObjectBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_span_object", SHAPED_GET_SPAN_OBJECT_HASH)

        private const val SHAPED_SET_SPAN_UPDATE_FONT_HASH = 2022725822L
        @JvmField
        val shapedSetSpanUpdateFontBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_set_span_update_font", SHAPED_SET_SPAN_UPDATE_FONT_HASH)

        private const val SHAPED_GET_RUN_COUNT_HASH = 2198884583L
        @JvmField
        val shapedGetRunCountBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_count", SHAPED_GET_RUN_COUNT_HASH)

        private const val SHAPED_GET_RUN_TEXT_HASH = 1464764419L
        @JvmField
        val shapedGetRunTextBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_text", SHAPED_GET_RUN_TEXT_HASH)

        private const val SHAPED_GET_RUN_RANGE_HASH = 4069534484L
        @JvmField
        val shapedGetRunRangeBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_range", SHAPED_GET_RUN_RANGE_HASH)

        private const val SHAPED_GET_RUN_GLYPH_RANGE_HASH = 4069534484L
        @JvmField
        val shapedGetRunGlyphRangeBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_glyph_range", SHAPED_GET_RUN_GLYPH_RANGE_HASH)

        private const val SHAPED_GET_RUN_FONT_RID_HASH = 1066463050L
        @JvmField
        val shapedGetRunFontRidBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_font_rid", SHAPED_GET_RUN_FONT_RID_HASH)

        private const val SHAPED_GET_RUN_FONT_SIZE_HASH = 1120910005L
        @JvmField
        val shapedGetRunFontSizeBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_font_size", SHAPED_GET_RUN_FONT_SIZE_HASH)

        private const val SHAPED_GET_RUN_LANGUAGE_HASH = 1464764419L
        @JvmField
        val shapedGetRunLanguageBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_language", SHAPED_GET_RUN_LANGUAGE_HASH)

        private const val SHAPED_GET_RUN_DIRECTION_HASH = 2413896864L
        @JvmField
        val shapedGetRunDirectionBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_direction", SHAPED_GET_RUN_DIRECTION_HASH)

        private const val SHAPED_GET_RUN_OBJECT_HASH = 4069510997L
        @JvmField
        val shapedGetRunObjectBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_get_run_object", SHAPED_GET_RUN_OBJECT_HASH)

        private const val SHAPED_TEXT_SUBSTR_HASH = 1937682086L
        @JvmField
        val shapedTextSubstrBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_substr", SHAPED_TEXT_SUBSTR_HASH)

        private const val SHAPED_TEXT_GET_PARENT_HASH = 3814569979L
        @JvmField
        val shapedTextGetParentBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_parent", SHAPED_TEXT_GET_PARENT_HASH)

        private const val SHAPED_TEXT_FIT_TO_WIDTH_HASH = 530670926L
        @JvmField
        val shapedTextFitToWidthBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_fit_to_width", SHAPED_TEXT_FIT_TO_WIDTH_HASH)

        private const val SHAPED_TEXT_TAB_ALIGN_HASH = 1283669550L
        @JvmField
        val shapedTextTabAlignBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_tab_align", SHAPED_TEXT_TAB_ALIGN_HASH)

        private const val SHAPED_TEXT_SHAPE_HASH = 3521089500L
        @JvmField
        val shapedTextShapeBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_shape", SHAPED_TEXT_SHAPE_HASH)

        private const val SHAPED_TEXT_IS_READY_HASH = 4155700596L
        @JvmField
        val shapedTextIsReadyBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_is_ready", SHAPED_TEXT_IS_READY_HASH)

        private const val SHAPED_TEXT_HAS_VISIBLE_CHARS_HASH = 4155700596L
        @JvmField
        val shapedTextHasVisibleCharsBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_has_visible_chars", SHAPED_TEXT_HAS_VISIBLE_CHARS_HASH)

        private const val SHAPED_TEXT_GET_GLYPHS_HASH = 2684255073L
        @JvmField
        val shapedTextGetGlyphsBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_glyphs", SHAPED_TEXT_GET_GLYPHS_HASH)

        private const val SHAPED_TEXT_SORT_LOGICAL_HASH = 2670461153L
        @JvmField
        val shapedTextSortLogicalBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_sort_logical", SHAPED_TEXT_SORT_LOGICAL_HASH)

        private const val SHAPED_TEXT_GET_GLYPH_COUNT_HASH = 2198884583L
        @JvmField
        val shapedTextGetGlyphCountBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_glyph_count", SHAPED_TEXT_GET_GLYPH_COUNT_HASH)

        private const val SHAPED_TEXT_GET_RANGE_HASH = 733700038L
        @JvmField
        val shapedTextGetRangeBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_range", SHAPED_TEXT_GET_RANGE_HASH)

        private const val SHAPED_TEXT_GET_LINE_BREAKS_ADV_HASH = 2376991424L
        @JvmField
        val shapedTextGetLineBreaksAdvBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_line_breaks_adv", SHAPED_TEXT_GET_LINE_BREAKS_ADV_HASH)

        private const val SHAPED_TEXT_GET_LINE_BREAKS_HASH = 2651359741L
        @JvmField
        val shapedTextGetLineBreaksBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_line_breaks", SHAPED_TEXT_GET_LINE_BREAKS_HASH)

        private const val SHAPED_TEXT_GET_WORD_BREAKS_HASH = 4099476853L
        @JvmField
        val shapedTextGetWordBreaksBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_word_breaks", SHAPED_TEXT_GET_WORD_BREAKS_HASH)

        private const val SHAPED_TEXT_GET_TRIM_POS_HASH = 2198884583L
        @JvmField
        val shapedTextGetTrimPosBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_trim_pos", SHAPED_TEXT_GET_TRIM_POS_HASH)

        private const val SHAPED_TEXT_GET_ELLIPSIS_POS_HASH = 2198884583L
        @JvmField
        val shapedTextGetEllipsisPosBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_ellipsis_pos", SHAPED_TEXT_GET_ELLIPSIS_POS_HASH)

        private const val SHAPED_TEXT_GET_ELLIPSIS_GLYPHS_HASH = 2684255073L
        @JvmField
        val shapedTextGetEllipsisGlyphsBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_ellipsis_glyphs", SHAPED_TEXT_GET_ELLIPSIS_GLYPHS_HASH)

        private const val SHAPED_TEXT_GET_ELLIPSIS_GLYPH_COUNT_HASH = 2198884583L
        @JvmField
        val shapedTextGetEllipsisGlyphCountBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_ellipsis_glyph_count", SHAPED_TEXT_GET_ELLIPSIS_GLYPH_COUNT_HASH)

        private const val SHAPED_TEXT_OVERRUN_TRIM_TO_WIDTH_HASH = 2723146520L
        @JvmField
        val shapedTextOverrunTrimToWidthBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_overrun_trim_to_width", SHAPED_TEXT_OVERRUN_TRIM_TO_WIDTH_HASH)

        private const val SHAPED_TEXT_GET_OBJECTS_HASH = 2684255073L
        @JvmField
        val shapedTextGetObjectsBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_objects", SHAPED_TEXT_GET_OBJECTS_HASH)

        private const val SHAPED_TEXT_GET_OBJECT_RECT_HASH = 447978354L
        @JvmField
        val shapedTextGetObjectRectBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_object_rect", SHAPED_TEXT_GET_OBJECT_RECT_HASH)

        private const val SHAPED_TEXT_GET_OBJECT_RANGE_HASH = 2524675647L
        @JvmField
        val shapedTextGetObjectRangeBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_object_range", SHAPED_TEXT_GET_OBJECT_RANGE_HASH)

        private const val SHAPED_TEXT_GET_OBJECT_GLYPH_HASH = 1260085030L
        @JvmField
        val shapedTextGetObjectGlyphBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_object_glyph", SHAPED_TEXT_GET_OBJECT_GLYPH_HASH)

        private const val SHAPED_TEXT_GET_SIZE_HASH = 2440833711L
        @JvmField
        val shapedTextGetSizeBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_size", SHAPED_TEXT_GET_SIZE_HASH)

        private const val SHAPED_TEXT_GET_ASCENT_HASH = 866169185L
        @JvmField
        val shapedTextGetAscentBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_ascent", SHAPED_TEXT_GET_ASCENT_HASH)

        private const val SHAPED_TEXT_GET_DESCENT_HASH = 866169185L
        @JvmField
        val shapedTextGetDescentBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_descent", SHAPED_TEXT_GET_DESCENT_HASH)

        private const val SHAPED_TEXT_GET_WIDTH_HASH = 866169185L
        @JvmField
        val shapedTextGetWidthBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_width", SHAPED_TEXT_GET_WIDTH_HASH)

        private const val SHAPED_TEXT_GET_UNDERLINE_POSITION_HASH = 866169185L
        @JvmField
        val shapedTextGetUnderlinePositionBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_underline_position", SHAPED_TEXT_GET_UNDERLINE_POSITION_HASH)

        private const val SHAPED_TEXT_GET_UNDERLINE_THICKNESS_HASH = 866169185L
        @JvmField
        val shapedTextGetUnderlineThicknessBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_underline_thickness", SHAPED_TEXT_GET_UNDERLINE_THICKNESS_HASH)

        private const val SHAPED_TEXT_GET_CARETS_HASH = 1574219346L
        @JvmField
        val shapedTextGetCaretsBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_carets", SHAPED_TEXT_GET_CARETS_HASH)

        private const val SHAPED_TEXT_GET_SELECTION_HASH = 3714187733L
        @JvmField
        val shapedTextGetSelectionBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_selection", SHAPED_TEXT_GET_SELECTION_HASH)

        private const val SHAPED_TEXT_HIT_TEST_GRAPHEME_HASH = 3149310417L
        @JvmField
        val shapedTextHitTestGraphemeBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_hit_test_grapheme", SHAPED_TEXT_HIT_TEST_GRAPHEME_HASH)

        private const val SHAPED_TEXT_HIT_TEST_POSITION_HASH = 3149310417L
        @JvmField
        val shapedTextHitTestPositionBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_hit_test_position", SHAPED_TEXT_HIT_TEST_POSITION_HASH)

        private const val SHAPED_TEXT_GET_GRAPHEME_BOUNDS_HASH = 2546185844L
        @JvmField
        val shapedTextGetGraphemeBoundsBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_grapheme_bounds", SHAPED_TEXT_GET_GRAPHEME_BOUNDS_HASH)

        private const val SHAPED_TEXT_NEXT_GRAPHEME_POS_HASH = 1120910005L
        @JvmField
        val shapedTextNextGraphemePosBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_next_grapheme_pos", SHAPED_TEXT_NEXT_GRAPHEME_POS_HASH)

        private const val SHAPED_TEXT_PREV_GRAPHEME_POS_HASH = 1120910005L
        @JvmField
        val shapedTextPrevGraphemePosBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_prev_grapheme_pos", SHAPED_TEXT_PREV_GRAPHEME_POS_HASH)

        private const val SHAPED_TEXT_GET_CHARACTER_BREAKS_HASH = 788230395L
        @JvmField
        val shapedTextGetCharacterBreaksBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_character_breaks", SHAPED_TEXT_GET_CHARACTER_BREAKS_HASH)

        private const val SHAPED_TEXT_NEXT_CHARACTER_POS_HASH = 1120910005L
        @JvmField
        val shapedTextNextCharacterPosBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_next_character_pos", SHAPED_TEXT_NEXT_CHARACTER_POS_HASH)

        private const val SHAPED_TEXT_PREV_CHARACTER_POS_HASH = 1120910005L
        @JvmField
        val shapedTextPrevCharacterPosBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_prev_character_pos", SHAPED_TEXT_PREV_CHARACTER_POS_HASH)

        private const val SHAPED_TEXT_CLOSEST_CHARACTER_POS_HASH = 1120910005L
        @JvmField
        val shapedTextClosestCharacterPosBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_closest_character_pos", SHAPED_TEXT_CLOSEST_CHARACTER_POS_HASH)

        private const val SHAPED_TEXT_DRAW_HASH = 1647687596L
        @JvmField
        val shapedTextDrawBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_draw", SHAPED_TEXT_DRAW_HASH)

        private const val SHAPED_TEXT_DRAW_OUTLINE_HASH = 1217146601L
        @JvmField
        val shapedTextDrawOutlineBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_draw_outline", SHAPED_TEXT_DRAW_OUTLINE_HASH)

        private const val SHAPED_TEXT_GET_DOMINANT_DIRECTION_IN_RANGE_HASH = 3326907668L
        @JvmField
        val shapedTextGetDominantDirectionInRangeBind =
            ObjectCalls.getMethodBind("TextServer", "shaped_text_get_dominant_direction_in_range", SHAPED_TEXT_GET_DOMINANT_DIRECTION_IN_RANGE_HASH)

        private const val FORMAT_NUMBER_HASH = 2664628024L
        @JvmField
        val formatNumberBind =
            ObjectCalls.getMethodBind("TextServer", "format_number", FORMAT_NUMBER_HASH)

        private const val PARSE_NUMBER_HASH = 2664628024L
        @JvmField
        val parseNumberBind =
            ObjectCalls.getMethodBind("TextServer", "parse_number", PARSE_NUMBER_HASH)

        private const val PERCENT_SIGN_HASH = 993269549L
        @JvmField
        val percentSignBind =
            ObjectCalls.getMethodBind("TextServer", "percent_sign", PERCENT_SIGN_HASH)

        private const val STRING_GET_WORD_BREAKS_HASH = 581857818L
        @JvmField
        val stringGetWordBreaksBind =
            ObjectCalls.getMethodBind("TextServer", "string_get_word_breaks", STRING_GET_WORD_BREAKS_HASH)

        private const val STRING_GET_CHARACTER_BREAKS_HASH = 2333794773L
        @JvmField
        val stringGetCharacterBreaksBind =
            ObjectCalls.getMethodBind("TextServer", "string_get_character_breaks", STRING_GET_CHARACTER_BREAKS_HASH)

        private const val IS_CONFUSABLE_HASH = 1433197768L
        @JvmField
        val isConfusableBind =
            ObjectCalls.getMethodBind("TextServer", "is_confusable", IS_CONFUSABLE_HASH)

        private const val SPOOF_CHECK_HASH = 3927539163L
        @JvmField
        val spoofCheckBind =
            ObjectCalls.getMethodBind("TextServer", "spoof_check", SPOOF_CHECK_HASH)

        private const val STRIP_DIACRITICS_HASH = 3135753539L
        @JvmField
        val stripDiacriticsBind =
            ObjectCalls.getMethodBind("TextServer", "strip_diacritics", STRIP_DIACRITICS_HASH)

        private const val IS_VALID_IDENTIFIER_HASH = 3927539163L
        @JvmField
        val isValidIdentifierBind =
            ObjectCalls.getMethodBind("TextServer", "is_valid_identifier", IS_VALID_IDENTIFIER_HASH)

        private const val IS_VALID_LETTER_HASH = 1116898809L
        @JvmField
        val isValidLetterBind =
            ObjectCalls.getMethodBind("TextServer", "is_valid_letter", IS_VALID_LETTER_HASH)

        private const val STRING_TO_UPPER_HASH = 2664628024L
        @JvmField
        val stringToUpperBind =
            ObjectCalls.getMethodBind("TextServer", "string_to_upper", STRING_TO_UPPER_HASH)

        private const val STRING_TO_LOWER_HASH = 2664628024L
        @JvmField
        val stringToLowerBind =
            ObjectCalls.getMethodBind("TextServer", "string_to_lower", STRING_TO_LOWER_HASH)

        private const val STRING_TO_TITLE_HASH = 2664628024L
        @JvmField
        val stringToTitleBind =
            ObjectCalls.getMethodBind("TextServer", "string_to_title", STRING_TO_TITLE_HASH)

        private const val PARSE_STRUCTURED_TEXT_HASH = 3310685015L
        @JvmField
        val parseStructuredTextBind =
            ObjectCalls.getMethodBind("TextServer", "parse_structured_text", PARSE_STRUCTURED_TEXT_HASH)
    }
}
