package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for PopupMenu (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PopupMenu waits on: ptrcallWithIntAndVariantArg, ptrcallWithIntArgRetString,
//   ptrcallWithIntArgRetVariantScalar
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the metadata of an item, which may be of any type. You can later get it with
 * `get_item_metadata`, which provides a simple way of assigning context data to items.
 *
 * Generated from Godot docs: PopupMenu.set_item_metadata
 */
fun PopupMenu.setItemMetadata(index: Int, metadata: Any?) {
    ObjectCalls.ptrcallWithIntAndVariantArg(setItemMetadataBind, handle, index, metadata)
}

/**
 * Returns the text of the item at the given `index`.
 *
 * Generated from Godot docs: PopupMenu.get_item_text
 */
fun PopupMenu.getItemText(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getItemTextBind, handle, index)
}

/**
 * Returns item's text language code.
 *
 * Generated from Godot docs: PopupMenu.get_item_language
 */
fun PopupMenu.getItemLanguage(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getItemLanguageBind, handle, index)
}

/**
 * Returns the metadata of the specified item, which might be of any type. You can set it with
 * `set_item_metadata`, which provides a simple way of assigning context data to items.
 *
 * Generated from Godot docs: PopupMenu.get_item_metadata
 */
fun PopupMenu.getItemMetadata(index: Int): Any? {
    return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getItemMetadataBind, handle, index)
}

/**
 * Returns the submenu name of the item at the given `index`. See `add_submenu_item` for more info
 * on how to add a submenu.
 *
 * Generated from Godot docs: PopupMenu.get_item_submenu
 */
fun PopupMenu.getItemSubmenu(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getItemSubmenuBind, handle, index)
}

/**
 * Returns the tooltip associated with the item at the given `index`.
 *
 * Generated from Godot docs: PopupMenu.get_item_tooltip
 */
fun PopupMenu.getItemTooltip(index: Int): String {
    return ObjectCalls.ptrcallWithIntArgRetString(getItemTooltipBind, handle, index)
}

private const val SET_ITEM_METADATA_HASH = 2152698145L
private val setItemMetadataBind by lazy {
    ObjectCalls.getMethodBind("PopupMenu", "set_item_metadata", SET_ITEM_METADATA_HASH)
}

private const val GET_ITEM_TEXT_HASH = 844755477L
private val getItemTextBind by lazy {
    ObjectCalls.getMethodBind("PopupMenu", "get_item_text", GET_ITEM_TEXT_HASH)
}

private const val GET_ITEM_LANGUAGE_HASH = 844755477L
private val getItemLanguageBind by lazy {
    ObjectCalls.getMethodBind("PopupMenu", "get_item_language", GET_ITEM_LANGUAGE_HASH)
}

private const val GET_ITEM_METADATA_HASH = 4227898402L
private val getItemMetadataBind by lazy {
    ObjectCalls.getMethodBind("PopupMenu", "get_item_metadata", GET_ITEM_METADATA_HASH)
}

private const val GET_ITEM_SUBMENU_HASH = 844755477L
private val getItemSubmenuBind by lazy {
    ObjectCalls.getMethodBind("PopupMenu", "get_item_submenu", GET_ITEM_SUBMENU_HASH)
}

private const val GET_ITEM_TOOLTIP_HASH = 844755477L
private val getItemTooltipBind by lazy {
    ObjectCalls.getMethodBind("PopupMenu", "get_item_tooltip", GET_ITEM_TOOLTIP_HASH)
}
