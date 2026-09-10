package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
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
//   ptrcallWithRIDAndArrayArg, ptrcallWithRIDAndDictionaryArg, ptrcallWithRIDAndVariantArgRetBool,
//   ptrcallWithRIDAndVariantArgRetLong, ptrcallWithRIDAndVariantArgRetRect2,
//   ptrcallWithRIDAndVariantArgRetVector2i, ptrcallWithRIDLongRIDListLongDictionaryArgs,
//   ptrcallWithRIDStringRIDListLongDictionaryStringVariantArgsRetBool,
//   ptrcallWithRIDVariantVector2LongDoubleArgsRetBool,
//   ptrcallWithRIDVariantVector2LongLongDoubleArgsRetBool
// Index: docs/reference/generated/ios-shape-gap.md

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
 * Sets font OpenType feature set override.
 *
 * Generated from Godot docs: TextServer.font_set_opentype_feature_overrides
 */
fun TextServer.fontSetOpentypeFeatureOverrides(fontRid: RID, overrides: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDAndDictionaryArg(fontSetOpentypeFeatureOverridesBind, handle, fontRid, overrides)
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
 * Default implementation of the BiDi algorithm override function.
 *
 * Generated from Godot docs: TextServer.parse_structured_text
 */
fun TextServer.parseStructuredText(parserType: Long, args: List<Any?>, text: String): List<Vector3i> {
    checkOpen()
    return ObjectCalls.ptrcallWithLongArrayStringArgsRetVector3iList(parseStructuredTextBind, handle, parserType, args, text)
}

private const val FONT_SET_VARIATION_COORDINATES_HASH = 1217542888L
private val fontSetVariationCoordinatesBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_set_variation_coordinates", FONT_SET_VARIATION_COORDINATES_HASH)
}

private const val FONT_SET_OPENTYPE_FEATURE_OVERRIDES_HASH = 1217542888L
private val fontSetOpentypeFeatureOverridesBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "font_set_opentype_feature_overrides", FONT_SET_OPENTYPE_FEATURE_OVERRIDES_HASH)
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

private const val PARSE_STRUCTURED_TEXT_HASH = 3310685015L
private val parseStructuredTextBind by lazy {
    ObjectCalls.getMethodBind("TextServer", "parse_structured_text", PARSE_STRUCTURED_TEXT_HASH)
}
