package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for NativeMenu (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NativeMenu waits on: ptrcallWithRIDArgRetCallable,
//   ptrcallWithRIDIntArgsRetCallable
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns global menu open callback. Note: This method is implemented only on macOS.
 *
 * Generated from Godot docs: NativeMenu.get_popup_open_callback
 */
fun NativeMenu.getPopupOpenCallback(rid: RID): GodotCallable? {
    return ObjectCalls.ptrcallWithRIDArgRetCallable(getPopupOpenCallbackBind, nativeMenuSingleton, rid)
}

/**
 * Returns global menu close callback. Note: This method is implemented on macOS and Windows.
 *
 * Generated from Godot docs: NativeMenu.get_popup_close_callback
 */
fun NativeMenu.getPopupCloseCallback(rid: RID): GodotCallable? {
    return ObjectCalls.ptrcallWithRIDArgRetCallable(getPopupCloseCallbackBind, nativeMenuSingleton, rid)
}

/**
 * Returns the callback of the item at index `idx`. Note: This method is implemented on macOS and
 * Windows.
 *
 * Generated from Godot docs: NativeMenu.get_item_callback
 */
fun NativeMenu.getItemCallback(rid: RID, idx: Int): GodotCallable? {
    return ObjectCalls.ptrcallWithRIDIntArgsRetCallable(getItemCallbackBind, nativeMenuSingleton, rid, idx)
}

/**
 * Returns the callback of the item accelerator at index `idx`. Note: This method is implemented
 * only on macOS.
 *
 * Generated from Godot docs: NativeMenu.get_item_key_callback
 */
fun NativeMenu.getItemKeyCallback(rid: RID, idx: Int): GodotCallable? {
    return ObjectCalls.ptrcallWithRIDIntArgsRetCallable(getItemKeyCallbackBind, nativeMenuSingleton, rid, idx)
}

private val nativeMenuSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("NativeMenu")
}

private const val GET_POPUP_OPEN_CALLBACK_HASH = 3170603026L
private val getPopupOpenCallbackBind by lazy {
    ObjectCalls.getMethodBind("NativeMenu", "get_popup_open_callback", GET_POPUP_OPEN_CALLBACK_HASH)
}

private const val GET_POPUP_CLOSE_CALLBACK_HASH = 3170603026L
private val getPopupCloseCallbackBind by lazy {
    ObjectCalls.getMethodBind("NativeMenu", "get_popup_close_callback", GET_POPUP_CLOSE_CALLBACK_HASH)
}

private const val GET_ITEM_CALLBACK_HASH = 1639989698L
private val getItemCallbackBind by lazy {
    ObjectCalls.getMethodBind("NativeMenu", "get_item_callback", GET_ITEM_CALLBACK_HASH)
}

private const val GET_ITEM_KEY_CALLBACK_HASH = 1639989698L
private val getItemKeyCallbackBind by lazy {
    ObjectCalls.getMethodBind("NativeMenu", "get_item_key_callback", GET_ITEM_KEY_CALLBACK_HASH)
}
