package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for TreeItem (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP TreeItem waits on: ptrcallWithIntArgRetCallable
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the custom callback of column `column`.
 *
 * Generated from Godot docs: TreeItem.get_custom_draw_callback
 */
fun TreeItem.getCustomDrawCallback(column: Int): GodotCallable? {
    return ObjectCalls.ptrcallWithIntArgRetCallable(getCustomDrawCallbackBind, handle, column)
}

private const val GET_CUSTOM_DRAW_CALLBACK_HASH = 1317077508L
private val getCustomDrawCallbackBind by lazy {
    ObjectCalls.getMethodBind("TreeItem", "get_custom_draw_callback", GET_CUSTOM_DRAW_CALLBACK_HASH)
}
