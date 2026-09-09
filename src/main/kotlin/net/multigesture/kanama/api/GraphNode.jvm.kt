package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GraphNode (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GraphNode waits on: ptrcallWithIntAndVariantArg, ptrcallWithIntArgRetVariantScalar
// Index: docs/contributing/ios-shape-gap.md

/**
 * Sets the custom metadata for the left (input) side of the slot with the given `slot_index` to
 * `value`.
 *
 * Generated from Godot docs: GraphNode.set_slot_metadata_left
 */
fun GraphNode.setSlotMetadataLeft(slotIndex: Int, value: Any?) {
    ObjectCalls.ptrcallWithIntAndVariantArg(setSlotMetadataLeftBind, handle, slotIndex, value)
}

/**
 * Returns the left (input) metadata of the slot with the given `slot_index`.
 *
 * Generated from Godot docs: GraphNode.get_slot_metadata_left
 */
fun GraphNode.getSlotMetadataLeft(slotIndex: Int): Any? {
    return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getSlotMetadataLeftBind, handle, slotIndex)
}

/**
 * Sets the custom metadata for the right (output) side of the slot with the given `slot_index` to
 * `value`.
 *
 * Generated from Godot docs: GraphNode.set_slot_metadata_right
 */
fun GraphNode.setSlotMetadataRight(slotIndex: Int, value: Any?) {
    ObjectCalls.ptrcallWithIntAndVariantArg(setSlotMetadataRightBind, handle, slotIndex, value)
}

/**
 * Returns the right (output) metadata of the slot with the given `slot_index`.
 *
 * Generated from Godot docs: GraphNode.get_slot_metadata_right
 */
fun GraphNode.getSlotMetadataRight(slotIndex: Int): Any? {
    return ObjectCalls.ptrcallWithIntArgRetVariantScalar(getSlotMetadataRightBind, handle, slotIndex)
}

private const val SET_SLOT_METADATA_LEFT_HASH = 2152698145L
private val setSlotMetadataLeftBind by lazy {
    ObjectCalls.getMethodBind("GraphNode", "set_slot_metadata_left", SET_SLOT_METADATA_LEFT_HASH)
}

private const val GET_SLOT_METADATA_LEFT_HASH = 4227898402L
private val getSlotMetadataLeftBind by lazy {
    ObjectCalls.getMethodBind("GraphNode", "get_slot_metadata_left", GET_SLOT_METADATA_LEFT_HASH)
}

private const val SET_SLOT_METADATA_RIGHT_HASH = 2152698145L
private val setSlotMetadataRightBind by lazy {
    ObjectCalls.getMethodBind("GraphNode", "set_slot_metadata_right", SET_SLOT_METADATA_RIGHT_HASH)
}

private const val GET_SLOT_METADATA_RIGHT_HASH = 4227898402L
private val getSlotMetadataRightBind by lazy {
    ObjectCalls.getMethodBind("GraphNode", "get_slot_metadata_right", GET_SLOT_METADATA_RIGHT_HASH)
}
