package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Tree (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Tree waits on: ptrcallWithIntArgRetString
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the column's title.
 *
 * Generated from Godot docs: Tree.get_column_title
 */
fun Tree.getColumnTitle(column: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getColumnTitleBind, handle, column)
}

/**
 * Returns the column title's tooltip text.
 *
 * Generated from Godot docs: Tree.get_column_title_tooltip_text
 */
fun Tree.getColumnTitleTooltipText(column: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getColumnTitleTooltipTextBind, handle, column)
}

/**
 * Returns column title language code.
 *
 * Generated from Godot docs: Tree.get_column_title_language
 */
fun Tree.getColumnTitleLanguage(column: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getColumnTitleLanguageBind, handle, column)
}

private const val GET_COLUMN_TITLE_HASH = 844755477L
private val getColumnTitleBind by lazy {
    ObjectCalls.getMethodBind("Tree", "get_column_title", GET_COLUMN_TITLE_HASH)
}

private const val GET_COLUMN_TITLE_TOOLTIP_TEXT_HASH = 844755477L
private val getColumnTitleTooltipTextBind by lazy {
    ObjectCalls.getMethodBind("Tree", "get_column_title_tooltip_text", GET_COLUMN_TITLE_TOOLTIP_TEXT_HASH)
}

private const val GET_COLUMN_TITLE_LANGUAGE_HASH = 844755477L
private val getColumnTitleLanguageBind by lazy {
    ObjectCalls.getMethodBind("Tree", "get_column_title_language", GET_COLUMN_TITLE_LANGUAGE_HASH)
}
