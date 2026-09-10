package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for OptionButton (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OptionButton waits on: ptrcallWithIntAndVariantArg,
//   ptrcallWithIntArgRetVariantScalar
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the metadata of an item. Metadata may be of any type and can be used to store extra
 * information about an item, such as an external string ID.
 *
 * Generated from Godot docs: OptionButton.set_item_metadata
 */
fun OptionButton.setItemMetadata(idx: Int, metadata: Any?) {
    ObjectCalls.ptrcallWithIntAndVariantArg(setItemMetadataBind, handle, idx, metadata)
}

/**
 * Retrieves the metadata of an item. Metadata may be any type and can be used to store extra
 * information about an item, such as an external string ID.
 *
 * Generated from Godot docs: OptionButton.get_item_metadata
 */
fun OptionButton.getItemMetadata(idx: Int): Any? {
    return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getItemMetadataBind, handle, idx)
}

private const val SET_ITEM_METADATA_HASH = 2152698145L
private val setItemMetadataBind by lazy {
    ObjectCalls.getMethodBind("OptionButton", "set_item_metadata", SET_ITEM_METADATA_HASH)
}

private const val GET_ITEM_METADATA_HASH = 4227898402L
private val getItemMetadataBind by lazy {
    ObjectCalls.getMethodBind("OptionButton", "get_item_metadata", GET_ITEM_METADATA_HASH)
}
