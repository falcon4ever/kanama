package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color

// GENERATED desktop/Android companion for FontVariation (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP FontVariation waits on: ptrcallWithDictionaryArg, ptrcallWithPackedColorListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Font OpenType variation coordinates. More info: OpenType variation tags
 * (https://docs.microsoft.com/en-us/typography/opentype/spec/dvaraxisreg). Note: This `Dictionary`
 * uses OpenType tags as keys. Variation axes can be identified both by tags (`int`, e.g.
 * `0x77678674`) and names (`String`, e.g. `wght`). Some axes might be accessible by multiple
 * names. For example, `wght` refers to the same axis as `weight`. Tags on the other hand are
 * unique. To convert between names and tags, use `TextServer.name_to_tag` and
 * `TextServer.tag_to_name`. Note: To get available variation axes of a font, use
 * `Font.get_supported_variation_list`.
 *
 * Generated from Godot docs: FontVariation.set_variation_opentype
 */
fun FontVariation.setVariationOpentype(coords: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setVariationOpentypeBind, handle, coords)
}

/**
 * A set of OpenType feature tags. More info: OpenType feature tags
 * (https://docs.microsoft.com/en-us/typography/opentype/spec/featuretags).
 *
 * Generated from Godot docs: FontVariation.set_opentype_features
 */
fun FontVariation.setOpentypeFeatures(features: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setOpentypeFeaturesBind, handle, features)
}

/**
 * An array of colors to override predefined palette. Use `Color(0, 0, 0, 0)`, to keep predefined
 * palette color at specific position.
 *
 * Generated from Godot docs: FontVariation.set_palette_custom_colors
 */
fun FontVariation.setPaletteCustomColors(colors: List<Color>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedColorListArg(setPaletteCustomColorsBind, handle, colors)
}

private const val SET_VARIATION_OPENTYPE_HASH = 4155329257L
private val setVariationOpentypeBind by lazy {
    ObjectCalls.getMethodBind("FontVariation", "set_variation_opentype", SET_VARIATION_OPENTYPE_HASH)
}

private const val SET_OPENTYPE_FEATURES_HASH = 4155329257L
private val setOpentypeFeaturesBind by lazy {
    ObjectCalls.getMethodBind("FontVariation", "set_opentype_features", SET_OPENTYPE_FEATURES_HASH)
}

private const val SET_PALETTE_CUSTOM_COLORS_HASH = 3546319833L
private val setPaletteCustomColorsBind by lazy {
    ObjectCalls.getMethodBind("FontVariation", "set_palette_custom_colors", SET_PALETTE_CUSTOM_COLORS_HASH)
}
