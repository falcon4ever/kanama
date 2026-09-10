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
// KANAMA-IOS-GAP TextServer waits on: ptrcallWithLongArrayStringArgsRetVector3iList,
//   ptrcallWithRIDAndArrayArg, ptrcallWithRIDAndByteArrayArg, ptrcallWithRIDAndDictionaryArg,
//   ptrcallWithRIDAndLongArgRetDictionary, ptrcallWithRIDAndPackedColorListArgs,
//   ptrcallWithRIDAndPackedFloat32ListArgRetDouble, ptrcallWithRIDAndTwoLongArgsRetDictionary,
//   ptrcallWithRIDAndVariantArgRetBool, ptrcallWithRIDAndVariantArgRetLong,
//   ptrcallWithRIDAndVariantArgRetRect2, ptrcallWithRIDAndVariantArgRetVector2i,
//   ptrcallWithRIDArgRetArray, ptrcallWithRIDArgRetDictionary, ptrcallWithRIDArgRetDictionaryList,
//   ptrcallWithRIDLongRIDListLongDictionaryArgs,
//   ptrcallWithRIDPackedFloat32ListLongBoolLongArgsRetPackedInt32List,
//   ptrcallWithRIDStringRIDListLongDictionaryStringVariantArgsRetBool,
//   ptrcallWithRIDVariantVector2LongDoubleArgsRetBool,
//   ptrcallWithRIDVariantVector2LongLongDoubleArgsRetBool,
//   ptrcallWithRIDVector2iLongPackedInt32ListArgs, ptrcallWithStringAndPackedStringListArgRetLong
// Index: docs/reference/generated/ios-shape-gap.md

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

private const val FONT_SET_DATA_HASH = 1355495400L
private val fontSetDataBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_set_data", FONT_SET_DATA_HASH)
}

private const val FONT_GET_OT_NAME_STRINGS_HASH = 1882737106L
private val fontGetOtNameStringsBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_get_ot_name_strings", FONT_GET_OT_NAME_STRINGS_HASH)
}

private const val FONT_SET_PALETTE_CUSTOM_COLORS_HASH = 4037098590L
private val fontSetPaletteCustomColorsBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_set_palette_custom_colors", FONT_SET_PALETTE_CUSTOM_COLORS_HASH)
}

private const val FONT_SET_VARIATION_COORDINATES_HASH = 1217542888L
private val fontSetVariationCoordinatesBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_set_variation_coordinates", FONT_SET_VARIATION_COORDINATES_HASH)
}

private const val FONT_GET_VARIATION_COORDINATES_HASH = 1882737106L
private val fontGetVariationCoordinatesBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_get_variation_coordinates", FONT_GET_VARIATION_COORDINATES_HASH)
}

private const val FONT_GET_SIZE_CACHE_INFO_HASH = 2684255073L
private val fontGetSizeCacheInfoBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_get_size_cache_info", FONT_GET_SIZE_CACHE_INFO_HASH)
}

private const val FONT_SET_TEXTURE_OFFSETS_HASH = 3005398047L
private val fontSetTextureOffsetsBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_set_texture_offsets", FONT_SET_TEXTURE_OFFSETS_HASH)
}

private const val FONT_GET_GLYPH_CONTOURS_HASH = 2903964473L
private val fontGetGlyphContoursBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_get_glyph_contours", FONT_GET_GLYPH_CONTOURS_HASH)
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

private const val IS_CONFUSABLE_HASH = 1433197768L
private val isConfusableBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "is_confusable", IS_CONFUSABLE_HASH)
}

private const val PARSE_STRUCTURED_TEXT_HASH = 3310685015L
private val parseStructuredTextBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "parse_structured_text", PARSE_STRUCTURED_TEXT_HASH)
}
