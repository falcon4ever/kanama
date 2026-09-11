package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for DisplayServer (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP DisplayServer waits on: ptrcallWithStringIntArgsRetCallable
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the callback of the item at index `idx`. Note: This method is implemented only on macOS.
 *
 * Generated from Godot docs: DisplayServer.global_menu_get_item_callback
 */
fun DisplayServer.globalMenuGetItemCallback(menuRoot: String, idx: Int): GodotCallable? {
    return ObjectCalls.ptrcallWithStringIntArgsRetCallable(globalMenuGetItemCallbackBind, displayServerSingleton, menuRoot, idx)
}

/**
 * Returns the callback of the item accelerator at index `idx`. Note: This method is implemented
 * only on macOS.
 *
 * Generated from Godot docs: DisplayServer.global_menu_get_item_key_callback
 */
fun DisplayServer.globalMenuGetItemKeyCallback(menuRoot: String, idx: Int): GodotCallable? {
    return ObjectCalls.ptrcallWithStringIntArgsRetCallable(globalMenuGetItemKeyCallbackBind, displayServerSingleton, menuRoot, idx)
}

private val displayServerSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("DisplayServer")
}

private const val GLOBAL_MENU_GET_ITEM_CALLBACK_HASH = 748666903L
private val globalMenuGetItemCallbackBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_callback", GLOBAL_MENU_GET_ITEM_CALLBACK_HASH)
}

private const val GLOBAL_MENU_GET_ITEM_KEY_CALLBACK_HASH = 748666903L
private val globalMenuGetItemKeyCallbackBind by lazy {
    ObjectCalls.getMethodBind("DisplayServer", "global_menu_get_item_key_callback", GLOBAL_MENU_GET_ITEM_KEY_CALLBACK_HASH)
}
