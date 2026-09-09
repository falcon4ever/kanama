package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for CodeHighlighter (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP CodeHighlighter waits on: ptrcallNoArgsRetDictionary, ptrcallWithDictionaryArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * Sets the keyword colors. All existing keywords will be removed. The `Dictionary` key is the
 * keyword. The value is the keyword color.
 *
 * Generated from Godot docs: CodeHighlighter.set_keyword_colors
 */
fun CodeHighlighter.setKeywordColors(keywords: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setKeywordColorsBind, handle, keywords)
}

/**
 * Sets the keyword colors. All existing keywords will be removed. The `Dictionary` key is the
 * keyword. The value is the keyword color.
 *
 * Generated from Godot docs: CodeHighlighter.get_keyword_colors
 */
fun CodeHighlighter.getKeywordColors(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(getKeywordColorsBind, handle)
}

/**
 * Sets the member keyword colors. All existing member keyword will be removed. The `Dictionary`
 * key is the member keyword. The value is the member keyword color.
 *
 * Generated from Godot docs: CodeHighlighter.set_member_keyword_colors
 */
fun CodeHighlighter.setMemberKeywordColors(memberKeyword: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setMemberKeywordColorsBind, handle, memberKeyword)
}

/**
 * Sets the member keyword colors. All existing member keyword will be removed. The `Dictionary`
 * key is the member keyword. The value is the member keyword color.
 *
 * Generated from Godot docs: CodeHighlighter.get_member_keyword_colors
 */
fun CodeHighlighter.getMemberKeywordColors(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(getMemberKeywordColorsBind, handle)
}

/**
 * Sets the color regions. All existing regions will be removed. The `Dictionary` key is the region
 * start and end key, separated by a space. The value is the region color.
 *
 * Generated from Godot docs: CodeHighlighter.set_color_regions
 */
fun CodeHighlighter.setColorRegions(colorRegions: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setColorRegionsBind, handle, colorRegions)
}

/**
 * Sets the color regions. All existing regions will be removed. The `Dictionary` key is the region
 * start and end key, separated by a space. The value is the region color.
 *
 * Generated from Godot docs: CodeHighlighter.get_color_regions
 */
fun CodeHighlighter.getColorRegions(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(getColorRegionsBind, handle)
}

var CodeHighlighter.keywordColors: Map<String, Any?>
    @JvmName("keywordColorsProperty")
    get() = getKeywordColors()
    @JvmName("setKeywordColorsProperty")
    set(value) = setKeywordColors(value)

var CodeHighlighter.memberKeywordColors: Map<String, Any?>
    @JvmName("memberKeywordColorsProperty")
    get() = getMemberKeywordColors()
    @JvmName("setMemberKeywordColorsProperty")
    set(value) = setMemberKeywordColors(value)

var CodeHighlighter.colorRegions: Map<String, Any?>
    @JvmName("colorRegionsProperty")
    get() = getColorRegions()
    @JvmName("setColorRegionsProperty")
    set(value) = setColorRegions(value)

private const val SET_KEYWORD_COLORS_HASH = 4155329257L
private val setKeywordColorsBind by lazy {
    ObjectCalls.getMethodBind("CodeHighlighter", "set_keyword_colors", SET_KEYWORD_COLORS_HASH)
}

private const val GET_KEYWORD_COLORS_HASH = 3102165223L
private val getKeywordColorsBind by lazy {
    ObjectCalls.getMethodBind("CodeHighlighter", "get_keyword_colors", GET_KEYWORD_COLORS_HASH)
}

private const val SET_MEMBER_KEYWORD_COLORS_HASH = 4155329257L
private val setMemberKeywordColorsBind by lazy {
    ObjectCalls.getMethodBind("CodeHighlighter", "set_member_keyword_colors", SET_MEMBER_KEYWORD_COLORS_HASH)
}

private const val GET_MEMBER_KEYWORD_COLORS_HASH = 3102165223L
private val getMemberKeywordColorsBind by lazy {
    ObjectCalls.getMethodBind("CodeHighlighter", "get_member_keyword_colors", GET_MEMBER_KEYWORD_COLORS_HASH)
}

private const val SET_COLOR_REGIONS_HASH = 4155329257L
private val setColorRegionsBind by lazy {
    ObjectCalls.getMethodBind("CodeHighlighter", "set_color_regions", SET_COLOR_REGIONS_HASH)
}

private const val GET_COLOR_REGIONS_HASH = 3102165223L
private val getColorRegionsBind by lazy {
    ObjectCalls.getMethodBind("CodeHighlighter", "get_color_regions", GET_COLOR_REGIONS_HASH)
}
