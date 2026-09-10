package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for FontFile (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP FontFile waits on: ptrcallWithDictionaryArg, ptrcallWithIntAndDictionaryArg
// Index: docs/reference/generated/ios-shape-gap.md

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
 * Font OpenType feature set override.
 *
 * Generated from Godot docs: FontFile.set_opentype_feature_overrides
 */
fun FontFile.setOpentypeFeatureOverrides(overrides: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setOpentypeFeatureOverridesBind, handle, overrides)
}

private const val SET_VARIATION_COORDINATES_HASH = 64545446L
private val setVariationCoordinatesBind by lazy {
    ObjectCalls.getMethodBind("FontFile", "set_variation_coordinates", SET_VARIATION_COORDINATES_HASH)
}

private const val SET_OPENTYPE_FEATURE_OVERRIDES_HASH = 4155329257L
private val setOpentypeFeatureOverridesBind by lazy {
    ObjectCalls.getMethodBind("FontFile", "set_opentype_feature_overrides", SET_OPENTYPE_FEATURE_OVERRIDES_HASH)
}
