package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i
import net.multigesture.kanama.types.Vector3i

// GENERATED desktop/Android companion for TextServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TextServer waits on: ptrcallNoArgsRetByteArray,
//   ptrcallWithLongArrayStringArgsRetVector3iList, ptrcallWithRIDAndArrayArg,
//   ptrcallWithRIDAndByteArrayArg, ptrcallWithRIDAndDictionaryArg,
//   ptrcallWithRIDAndLongArgRetDictionary, ptrcallWithRIDAndLongArgRetPackedColorList,
//   ptrcallWithRIDAndLongArgRetVector2iList, ptrcallWithRIDAndPackedColorListArgs,
//   ptrcallWithRIDAndPackedFloat32ListArgRetDouble, ptrcallWithRIDAndTwoLongArgsRetDictionary,
//   ptrcallWithRIDAndTwoLongArgsRetPackedInt32List,
//   ptrcallWithRIDAndTwoLongArgsRetPackedVector2List, ptrcallWithRIDAndVariantArgRetBool,
//   ptrcallWithRIDAndVariantArgRetLong, ptrcallWithRIDAndVariantArgRetRect2,
//   ptrcallWithRIDAndVariantArgRetVector2i, ptrcallWithRIDAndVector2iArgRetPackedInt32List,
//   ptrcallWithRIDArgRetArray, ptrcallWithRIDArgRetDictionary, ptrcallWithRIDArgRetDictionaryList,
//   ptrcallWithRIDArgRetPackedColorList, ptrcallWithRIDArgRetPackedInt32List,
//   ptrcallWithRIDArgRetPackedStringList, ptrcallWithRIDArgRetVector2iList,
//   ptrcallWithRIDDoubleTwoLongArgsRetPackedInt32List, ptrcallWithRIDLongRIDListLongDictionaryArgs,
//   ptrcallWithRIDPackedFloat32ListLongBoolLongArgsRetPackedInt32List,
//   ptrcallWithRIDStringRIDListLongDictionaryStringVariantArgsRetBool,
//   ptrcallWithRIDVariantVector2LongDoubleArgsRetBool,
//   ptrcallWithRIDVariantVector2LongLongDoubleArgsRetBool,
//   ptrcallWithRIDVector2iLongArgsRetPackedInt32List, ptrcallWithRIDVector2iLongPackedInt32ListArgs,
//   ptrcallWithStringAndPackedStringListArgRetLong,
//   ptrcallWithTwoStringAndLongArgRetPackedInt32List, ptrcallWithTwoStringArgsRetPackedInt32List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns default TextServer database (e.g. ICU break iterators and dictionaries).
 *
 * Generated from Godot docs: TextServer.get_support_data
 */
fun TextServer.getSupportData(): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetByteArray(getSupportDataBind, handle)
}

/**
 * Sets font source data, e.g contents of the dynamic font source file.
 *
 * Generated from Godot docs: TextServer.font_set_data
 */
fun TextServer.fontSetData(fontRid: RID, data: ByteArray) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDAndByteArrayArg(fontSetDataBind, handle, fontRid, data)
}

/**
 * Returns `Dictionary` with OpenType font name strings (localized font names, version,
 * description, license information, sample text, etc.).
 *
 * Generated from Godot docs: TextServer.font_get_ot_name_strings
 */
fun TextServer.fontGetOtNameStrings(fontRid: RID): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetDictionary(fontGetOtNameStringsBind, handle, fontRid)
}

/**
 * Returns the array in the predefined color palette at `index`. Palette contains all colors used
 * to render font glyphs. Each palette has the same number of colors. Colors can be overridden
 * using `font_set_palette_custom_colors`.
 *
 * Generated from Godot docs: TextServer.font_get_palette_colors
 */
fun TextServer.fontGetPaletteColors(fontRid: RID, index: Long): List<Color> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndLongArgRetPackedColorList(fontGetPaletteColorsBind, handle, fontRid, index)
}

/**
 * Sets array of custom colors to override predefined palette. Set to empty array to reset
 * overrides. Use `Color(0, 0, 0, 0)`, to keep predefined palette color at specific position.
 *
 * Generated from Godot docs: TextServer.font_set_palette_custom_colors
 */
fun TextServer.fontSetPaletteCustomColors(fontRid: RID, colors: List<Color>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDAndPackedColorListArgs(fontSetPaletteCustomColorsBind, handle, fontRid, colors)
}

/**
 * Returns array of custom colors to override predefined palette.
 *
 * Generated from Godot docs: TextServer.font_get_palette_custom_colors
 */
fun TextServer.fontGetPaletteCustomColors(fontRid: RID): List<Color> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetPackedColorList(fontGetPaletteCustomColorsBind, handle, fontRid)
}

/**
 * Sets variation coordinates for the specified font cache entry. See
 * `font_supported_variation_list` for more info.
 *
 * Generated from Godot docs: TextServer.font_set_variation_coordinates
 */
fun TextServer.fontSetVariationCoordinates(fontRid: RID, variationCoordinates: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDAndDictionaryArg(fontSetVariationCoordinatesBind, handle, fontRid, variationCoordinates)
}

/**
 * Returns variation coordinates for the specified font cache entry. See
 * `font_supported_variation_list` for more info.
 *
 * Generated from Godot docs: TextServer.font_get_variation_coordinates
 */
fun TextServer.fontGetVariationCoordinates(fontRid: RID): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetDictionary(fontGetVariationCoordinatesBind, handle, fontRid)
}

/**
 * Returns list of the font sizes in the cache. Each size is `Vector2i` with font size and outline
 * size.
 *
 * Generated from Godot docs: TextServer.font_get_size_cache_list
 */
fun TextServer.fontGetSizeCacheList(fontRid: RID): List<Vector2i> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetVector2iList(fontGetSizeCacheListBind, handle, fontRid)
}

/**
 * Returns font cache information, each entry contains the following fields: `Vector2i size_px` -
 * font size in pixels, `float viewport_oversampling` - viewport oversampling factor, `int glyphs`
 * - number of rendered glyphs, `int textures` - number of used textures, `int textures_size` -
 * size of texture data in bytes.
 *
 * Generated from Godot docs: TextServer.font_get_size_cache_info
 */
fun TextServer.fontGetSizeCacheInfo(fontRid: RID): List<Map<String, Any?>> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(fontGetSizeCacheInfoBind, handle, fontRid)
}

/**
 * Sets array containing glyph packing data.
 *
 * Generated from Godot docs: TextServer.font_set_texture_offsets
 */
fun TextServer.fontSetTextureOffsets(fontRid: RID, size: Vector2i, textureIndex: Long, offset: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDVector2iLongPackedInt32ListArgs(fontSetTextureOffsetsBind, handle, fontRid, size, textureIndex, offset)
}

/**
 * Returns array containing glyph packing data.
 *
 * Generated from Godot docs: TextServer.font_get_texture_offsets
 */
fun TextServer.fontGetTextureOffsets(fontRid: RID, size: Vector2i, textureIndex: Long): List<Int> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDVector2iLongArgsRetPackedInt32List(fontGetTextureOffsetsBind, handle, fontRid, size, textureIndex)
}

/**
 * Returns list of rendered glyphs in the cache entry.
 *
 * Generated from Godot docs: TextServer.font_get_glyph_list
 */
fun TextServer.fontGetGlyphList(fontRid: RID, size: Vector2i): List<Int> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndVector2iArgRetPackedInt32List(fontGetGlyphListBind, handle, fontRid, size)
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
fun TextServer.fontGetGlyphContours(font: RID, size: Long, index: Long): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetDictionary(fontGetGlyphContoursBind, handle, font, size, index)
}

/**
 * Returns list of the kerning overrides.
 *
 * Generated from Godot docs: TextServer.font_get_kerning_list
 */
fun TextServer.fontGetKerningList(fontRid: RID, size: Long): List<Vector2i> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndLongArgRetVector2iList(fontGetKerningListBind, handle, fontRid, size)
}

/**
 * Returns an array containing all glyph indices in the font.
 *
 * Generated from Godot docs: TextServer.font_get_supported_glyphs
 */
fun TextServer.fontGetSupportedGlyphs(fontRid: RID): List<Int> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(fontGetSupportedGlyphsBind, handle, fontRid)
}

/**
 * Returns list of language support overrides.
 *
 * Generated from Godot docs: TextServer.font_get_language_support_overrides
 */
fun TextServer.fontGetLanguageSupportOverrides(fontRid: RID): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetPackedStringList(fontGetLanguageSupportOverridesBind, handle, fontRid)
}

/**
 * Returns list of script support overrides.
 *
 * Generated from Godot docs: TextServer.font_get_script_support_overrides
 */
fun TextServer.fontGetScriptSupportOverrides(fontRid: RID): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetPackedStringList(fontGetScriptSupportOverridesBind, handle, fontRid)
}

/**
 * Sets font OpenType feature set override.
 *
 * Generated from Godot docs: TextServer.font_set_opentype_feature_overrides
 */
fun TextServer.fontSetOpentypeFeatureOverrides(fontRid: RID, overrides: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDAndDictionaryArg(fontSetOpentypeFeatureOverridesBind, handle, fontRid, overrides)
}

/**
 * Returns font OpenType feature set override.
 *
 * Generated from Godot docs: TextServer.font_get_opentype_feature_overrides
 */
fun TextServer.fontGetOpentypeFeatureOverrides(fontRid: RID): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetDictionary(fontGetOpentypeFeatureOverridesBind, handle, fontRid)
}

/**
 * Returns the dictionary of the supported OpenType features.
 *
 * Generated from Godot docs: TextServer.font_supported_feature_list
 */
fun TextServer.fontSupportedFeatureList(fontRid: RID): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetDictionary(fontSupportedFeatureListBind, handle, fontRid)
}

/**
 * Returns the dictionary of the supported OpenType variation coordinates.
 *
 * Generated from Godot docs: TextServer.font_supported_variation_list
 */
fun TextServer.fontSupportedVariationList(fontRid: RID): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetDictionary(fontSupportedVariationListBind, handle, fontRid)
}

/**
 * Overrides BiDi for the structured text. Override ranges should cover full source text without
 * overlaps. BiDi algorithm will be used on each range separately.
 *
 * Generated from Godot docs: TextServer.shaped_text_set_bidi_override
 */
fun TextServer.shapedTextSetBidiOverride(shaped: RID, override: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDAndArrayArg(shapedTextSetBidiOverrideBind, handle, shaped, override)
}

/**
 * Adds text span and font to draw it to the text buffer.
 *
 * Generated from Godot docs: TextServer.shaped_text_add_string
 */
fun TextServer.shapedTextAddString(shaped: RID, text: String, fonts: List<RID>, size: Long, opentypeFeatures: Map<String, Any?> = emptyMap(), language: String = "", meta: Any? = null): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDStringRIDListLongDictionaryStringVariantArgsRetBool(shapedTextAddStringBind, handle, shaped, text, fonts, size, opentypeFeatures, language, meta)
}

/**
 * Adds inline object to the text buffer, `key` must be unique. In the text, object is represented
 * as `length` object replacement characters.
 *
 * Generated from Godot docs: TextServer.shaped_text_add_object
 */
fun TextServer.shapedTextAddObject(shaped: RID, key: Any?, size: Vector2, inlineAlign: Long = 5L, length: Long = 1L, baseline: Double = 0.0): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDVariantVector2LongLongDoubleArgsRetBool(shapedTextAddObjectBind, handle, shaped, key, size, inlineAlign, length, baseline)
}

/**
 * Sets new size and alignment of embedded object.
 *
 * Generated from Godot docs: TextServer.shaped_text_resize_object
 */
fun TextServer.shapedTextResizeObject(shaped: RID, key: Any?, size: Vector2, inlineAlign: Long = 5L, baseline: Double = 0.0): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDVariantVector2LongDoubleArgsRetBool(shapedTextResizeObjectBind, handle, shaped, key, size, inlineAlign, baseline)
}

/**
 * Returns `true` if an object with `key` is embedded in this shaped text buffer.
 *
 * Generated from Godot docs: TextServer.shaped_text_has_object
 */
fun TextServer.shapedTextHasObject(shaped: RID, key: Any?): Boolean {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndVariantArgRetBool(shapedTextHasObjectBind, handle, shaped, key)
}

/**
 * Changes text span font, font size, and OpenType features, without changing the text.
 *
 * Generated from Godot docs: TextServer.shaped_set_span_update_font
 */
fun TextServer.shapedSetSpanUpdateFont(shaped: RID, index: Long, fonts: List<RID>, size: Long, opentypeFeatures: Map<String, Any?> = emptyMap()) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDLongRIDListLongDictionaryArgs(shapedSetSpanUpdateFontBind, handle, shaped, index, fonts, size, opentypeFeatures)
}

/**
 * Aligns shaped text to the given tab-stops.
 *
 * Generated from Godot docs: TextServer.shaped_text_tab_align
 */
fun TextServer.shapedTextTabAlign(shaped: RID, tabStops: List<Float>): Double {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndPackedFloat32ListArgRetDouble(shapedTextTabAlignBind, handle, shaped, tabStops)
}

/**
 * Returns an array of glyphs in the visual order.
 *
 * Generated from Godot docs: TextServer.shaped_text_get_glyphs
 */
fun TextServer.shapedTextGetGlyphs(shaped: RID): List<Map<String, Any?>> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(shapedTextGetGlyphsBind, handle, shaped)
}

/**
 * Returns text glyphs in the logical order.
 *
 * Generated from Godot docs: TextServer.shaped_text_sort_logical
 */
fun TextServer.shapedTextSortLogical(shaped: RID): List<Map<String, Any?>> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(shapedTextSortLogicalBind, handle, shaped)
}

/**
 * Breaks text to the lines and columns. Returns character ranges for each segment.
 *
 * Generated from Godot docs: TextServer.shaped_text_get_line_breaks_adv
 */
fun TextServer.shapedTextGetLineBreaksAdv(shaped: RID, width: List<Float>, start: Long = 0L, once: Boolean = true, breakFlags: Long = 3L): List<Int> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDPackedFloat32ListLongBoolLongArgsRetPackedInt32List(shapedTextGetLineBreaksAdvBind, handle, shaped, width, start, once, breakFlags)
}

/**
 * Breaks text to the lines and returns character ranges for each line.
 *
 * Generated from Godot docs: TextServer.shaped_text_get_line_breaks
 */
fun TextServer.shapedTextGetLineBreaks(shaped: RID, width: Double, start: Long = 0L, breakFlags: Long = 3L): List<Int> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDDoubleTwoLongArgsRetPackedInt32List(shapedTextGetLineBreaksBind, handle, shaped, width, start, breakFlags)
}

/**
 * Breaks text into words and returns array of character ranges. Use `grapheme_flags` to set what
 * characters are used for breaking.
 *
 * Generated from Godot docs: TextServer.shaped_text_get_word_breaks
 */
fun TextServer.shapedTextGetWordBreaks(shaped: RID, graphemeFlags: Long = 264L, skipGraphemeFlags: Long = 4L): List<Int> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetPackedInt32List(shapedTextGetWordBreaksBind, handle, shaped, graphemeFlags, skipGraphemeFlags)
}

/**
 * Returns array of the glyphs in the ellipsis.
 *
 * Generated from Godot docs: TextServer.shaped_text_get_ellipsis_glyphs
 */
fun TextServer.shapedTextGetEllipsisGlyphs(shaped: RID): List<Map<String, Any?>> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetDictionaryList(shapedTextGetEllipsisGlyphsBind, handle, shaped)
}

/**
 * Returns array of inline objects.
 *
 * Generated from Godot docs: TextServer.shaped_text_get_objects
 */
fun TextServer.shapedTextGetObjects(shaped: RID): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetArray(shapedTextGetObjectsBind, handle, shaped)
}

/**
 * Returns bounding rectangle of the inline object.
 *
 * Generated from Godot docs: TextServer.shaped_text_get_object_rect
 */
fun TextServer.shapedTextGetObjectRect(shaped: RID, key: Any?): Rect2 {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndVariantArgRetRect2(shapedTextGetObjectRectBind, handle, shaped, key)
}

/**
 * Returns the character range of the inline object.
 *
 * Generated from Godot docs: TextServer.shaped_text_get_object_range
 */
fun TextServer.shapedTextGetObjectRange(shaped: RID, key: Any?): Vector2i {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndVariantArgRetVector2i(shapedTextGetObjectRangeBind, handle, shaped, key)
}

/**
 * Returns the glyph index of the inline object.
 *
 * Generated from Godot docs: TextServer.shaped_text_get_object_glyph
 */
fun TextServer.shapedTextGetObjectGlyph(shaped: RID, key: Any?): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndVariantArgRetLong(shapedTextGetObjectGlyphBind, handle, shaped, key)
}

/**
 * Returns shapes of the carets corresponding to the character offset `position` in the text.
 * Returned caret shape is 1 pixel wide rectangle.
 *
 * Generated from Godot docs: TextServer.shaped_text_get_carets
 */
fun TextServer.shapedTextGetCarets(shaped: RID, position: Long): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndLongArgRetDictionary(shapedTextGetCaretsBind, handle, shaped, position)
}

/**
 * Returns selection rectangles for the specified character range.
 *
 * Generated from Godot docs: TextServer.shaped_text_get_selection
 */
fun TextServer.shapedTextGetSelection(shaped: RID, start: Long, end: Long): List<Vector2> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDAndTwoLongArgsRetPackedVector2List(shapedTextGetSelectionBind, handle, shaped, start, end)
}

/**
 * Returns array of the composite character boundaries.
 *
 * Generated from Godot docs: TextServer.shaped_text_get_character_breaks
 */
fun TextServer.shapedTextGetCharacterBreaks(shaped: RID): List<Int> {
    checkOpen()
    return ObjectCalls.ptrcallWithRIDArgRetPackedInt32List(shapedTextGetCharacterBreaksBind, handle, shaped)
}

/**
 * Returns an array of the word break boundaries. Elements in the returned array are the offsets of
 * the start and end of words. Therefore the length of the array is always even. When
 * `chars_per_line` is greater than zero, line break boundaries are returned instead.
 *
 * Generated from Godot docs: TextServer.string_get_word_breaks
 */
fun TextServer.stringGetWordBreaks(string: String, language: String = "", charsPerLine: Long = 0L): List<Int> {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoStringAndLongArgRetPackedInt32List(stringGetWordBreaksBind, handle, string, language, charsPerLine)
}

/**
 * Returns array of the composite character boundaries.
 *
 * Generated from Godot docs: TextServer.string_get_character_breaks
 */
fun TextServer.stringGetCharacterBreaks(string: String, language: String = ""): List<Int> {
    checkOpen()
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
fun TextServer.isConfusable(string: String, dict: List<String>): Long {
    checkOpen()
    return ObjectCalls.ptrcallWithStringAndPackedStringListArgRetLong(isConfusableBind, handle, string, dict)
}

/**
 * Default implementation of the BiDi algorithm override function.
 *
 * Generated from Godot docs: TextServer.parse_structured_text
 */
fun TextServer.parseStructuredText(parserType: Long, args: List<Any?>, text: String): List<Vector3i> {
    checkOpen()
    return ObjectCalls.ptrcallWithLongArrayStringArgsRetVector3iList(parseStructuredTextBind, handle, parserType, args, text)
}

private const val GET_SUPPORT_DATA_HASH = 2362200018L
private val getSupportDataBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "get_support_data", GET_SUPPORT_DATA_HASH)
}

private const val FONT_SET_DATA_HASH = 1355495400L
private val fontSetDataBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_set_data", FONT_SET_DATA_HASH)
}

private const val FONT_GET_OT_NAME_STRINGS_HASH = 1882737106L
private val fontGetOtNameStringsBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_get_ot_name_strings", FONT_GET_OT_NAME_STRINGS_HASH)
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

private const val FONT_SET_VARIATION_COORDINATES_HASH = 1217542888L
private val fontSetVariationCoordinatesBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_set_variation_coordinates", FONT_SET_VARIATION_COORDINATES_HASH)
}

private const val FONT_GET_VARIATION_COORDINATES_HASH = 1882737106L
private val fontGetVariationCoordinatesBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_get_variation_coordinates", FONT_GET_VARIATION_COORDINATES_HASH)
}

private const val FONT_GET_SIZE_CACHE_LIST_HASH = 2684255073L
private val fontGetSizeCacheListBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_get_size_cache_list", FONT_GET_SIZE_CACHE_LIST_HASH)
}

private const val FONT_GET_SIZE_CACHE_INFO_HASH = 2684255073L
private val fontGetSizeCacheInfoBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_get_size_cache_info", FONT_GET_SIZE_CACHE_INFO_HASH)
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

private const val FONT_GET_GLYPH_CONTOURS_HASH = 2903964473L
private val fontGetGlyphContoursBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_get_glyph_contours", FONT_GET_GLYPH_CONTOURS_HASH)
}

private const val FONT_GET_KERNING_LIST_HASH = 1778388067L
private val fontGetKerningListBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_get_kerning_list", FONT_GET_KERNING_LIST_HASH)
}

private const val FONT_GET_SUPPORTED_GLYPHS_HASH = 788230395L
private val fontGetSupportedGlyphsBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_get_supported_glyphs", FONT_GET_SUPPORTED_GLYPHS_HASH)
}

private const val FONT_GET_LANGUAGE_SUPPORT_OVERRIDES_HASH = 2801473409L
private val fontGetLanguageSupportOverridesBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_get_language_support_overrides", FONT_GET_LANGUAGE_SUPPORT_OVERRIDES_HASH)
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

private const val SHAPED_TEXT_SET_BIDI_OVERRIDE_HASH = 684822712L
private val shapedTextSetBidiOverrideBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "shaped_text_set_bidi_override", SHAPED_TEXT_SET_BIDI_OVERRIDE_HASH)
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

private const val SHAPED_SET_SPAN_UPDATE_FONT_HASH = 2022725822L
private val shapedSetSpanUpdateFontBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "shaped_set_span_update_font", SHAPED_SET_SPAN_UPDATE_FONT_HASH)
}

private const val SHAPED_TEXT_TAB_ALIGN_HASH = 1283669550L
private val shapedTextTabAlignBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "shaped_text_tab_align", SHAPED_TEXT_TAB_ALIGN_HASH)
}

private const val SHAPED_TEXT_GET_GLYPHS_HASH = 2684255073L
private val shapedTextGetGlyphsBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "shaped_text_get_glyphs", SHAPED_TEXT_GET_GLYPHS_HASH)
}

private const val SHAPED_TEXT_SORT_LOGICAL_HASH = 2670461153L
private val shapedTextSortLogicalBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "shaped_text_sort_logical", SHAPED_TEXT_SORT_LOGICAL_HASH)
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

private const val SHAPED_TEXT_GET_ELLIPSIS_GLYPHS_HASH = 2684255073L
private val shapedTextGetEllipsisGlyphsBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "shaped_text_get_ellipsis_glyphs", SHAPED_TEXT_GET_ELLIPSIS_GLYPHS_HASH)
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

private const val SHAPED_TEXT_GET_CARETS_HASH = 1574219346L
private val shapedTextGetCaretsBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "shaped_text_get_carets", SHAPED_TEXT_GET_CARETS_HASH)
}

private const val SHAPED_TEXT_GET_SELECTION_HASH = 3714187733L
private val shapedTextGetSelectionBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "shaped_text_get_selection", SHAPED_TEXT_GET_SELECTION_HASH)
}

private const val SHAPED_TEXT_GET_CHARACTER_BREAKS_HASH = 788230395L
private val shapedTextGetCharacterBreaksBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "shaped_text_get_character_breaks", SHAPED_TEXT_GET_CHARACTER_BREAKS_HASH)
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

private const val PARSE_STRUCTURED_TEXT_HASH = 3310685015L
private val parseStructuredTextBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "parse_structured_text", PARSE_STRUCTURED_TEXT_HASH)
}
