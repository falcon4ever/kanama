package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2i

// GENERATED desktop/Android companion for FontFile (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP FontFile waits on: ptrcallWithByteArrayArg, ptrcallWithDictionaryArg,
//   ptrcallWithIntAndDictionaryArg, ptrcallWithIntArgRetVector2iList,
//   ptrcallWithIntVector2iIntPackedInt32ListArgs, ptrcallWithTwoIntArgsRetVector2iList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Contents of the dynamic font source file.
 *
 * Generated from Godot docs: FontFile.set_data
 */
fun FontFile.setData(data: ByteArray) {
    checkOpen()
    ObjectCalls.ptrcallWithByteArrayArg(setDataBind, handle, data)
}

/**
 * Returns list of the font sizes in the cache. Each size is `Vector2i` with font size and outline
 * size.
 *
 * Generated from Godot docs: FontFile.get_size_cache_list
 */
fun FontFile.getSizeCacheList(cacheIndex: Int): List<Vector2i> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetVector2iList(getSizeCacheListBind, handle, cacheIndex)
}

/**
 * Sets variation coordinates for the specified font cache entry. See
 * `Font.get_supported_variation_list` for more info.
 *
 * Generated from Godot docs: FontFile.set_variation_coordinates
 */
fun FontFile.setVariationCoordinates(cacheIndex: Int, variationCoordinates: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithIntAndDictionaryArg(setVariationCoordinatesBind, handle, cacheIndex, variationCoordinates)
}

/**
 * Sets array containing glyph packing data.
 *
 * Generated from Godot docs: FontFile.set_texture_offsets
 */
fun FontFile.setTextureOffsets(cacheIndex: Int, size: Vector2i, textureIndex: Int, offset: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithIntVector2iIntPackedInt32ListArgs(setTextureOffsetsBind, handle, cacheIndex, size, textureIndex, offset)
}

/**
 * Returns list of the kerning overrides.
 *
 * Generated from Godot docs: FontFile.get_kerning_list
 */
fun FontFile.getKerningList(cacheIndex: Int, size: Int): List<Vector2i> {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetVector2iList(getKerningListBind, handle, cacheIndex, size)
}

/**
 * Font OpenType feature set override.
 *
 * Generated from Godot docs: FontFile.set_opentype_feature_overrides
 */
fun FontFile.setOpentypeFeatureOverrides(overrides: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setOpentypeFeatureOverridesBind, handle, overrides)
}

private const val SET_DATA_HASH = 2971499966L
private val setDataBind by lazy {
    ObjectCalls.getMethodBind("FontFile", "set_data", SET_DATA_HASH)
}

private const val GET_SIZE_CACHE_LIST_HASH = 663333327L
private val getSizeCacheListBind by lazy {
    ObjectCalls.getMethodBind("FontFile", "get_size_cache_list", GET_SIZE_CACHE_LIST_HASH)
}

private const val SET_VARIATION_COORDINATES_HASH = 64545446L
private val setVariationCoordinatesBind by lazy {
    ObjectCalls.getMethodBind("FontFile", "set_variation_coordinates", SET_VARIATION_COORDINATES_HASH)
}

private const val SET_TEXTURE_OFFSETS_HASH = 2849993437L
private val setTextureOffsetsBind by lazy {
    ObjectCalls.getMethodBind("FontFile", "set_texture_offsets", SET_TEXTURE_OFFSETS_HASH)
}

private const val GET_KERNING_LIST_HASH = 2345056839L
private val getKerningListBind by lazy {
    ObjectCalls.getMethodBind("FontFile", "get_kerning_list", GET_KERNING_LIST_HASH)
}

private const val SET_OPENTYPE_FEATURE_OVERRIDES_HASH = 4155329257L
private val setOpentypeFeatureOverridesBind by lazy {
    ObjectCalls.getMethodBind("FontFile", "set_opentype_feature_overrides", SET_OPENTYPE_FEATURE_OVERRIDES_HASH)
}
