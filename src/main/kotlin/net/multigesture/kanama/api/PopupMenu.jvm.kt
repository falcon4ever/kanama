package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for PopupMenu (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PopupMenu waits on: ptrcallWithIntAndVariantArg, ptrcallWithIntArgRetVariantScalar
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
 * Returns the metadata of the specified item, which might be of any type. You can set it with
 * `set_item_metadata`, which provides a simple way of assigning context data to items.
 *
 * Generated from Godot docs: PopupMenu.get_item_metadata
 */
fun PopupMenu.getItemMetadata(index: Int): Any? {
    return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getItemMetadataBind, handle, index)
}

private const val SET_ITEM_METADATA_HASH = 2152698145L
private val setItemMetadataBind by lazy {
    ObjectCalls.getMethodBind("PopupMenu", "set_item_metadata", SET_ITEM_METADATA_HASH)
}

private const val GET_ITEM_METADATA_HASH = 4227898402L
private val getItemMetadataBind by lazy {
    ObjectCalls.getMethodBind("PopupMenu", "get_item_metadata", GET_ITEM_METADATA_HASH)
}
