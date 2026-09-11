package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Control (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Control waits on: ptrcallWithVariantAndObjectArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Forces drag and bypasses `_get_drag_data` and `set_drag_preview` by passing `data` and
 * `preview`. Drag will start even if the mouse is neither over nor pressed on this control. The
 * methods `_can_drop_data` and `_drop_data` must be implemented on controls that want to receive
 * drop data.
 *
 * Generated from Godot docs: Control.force_drag
 */
fun Control.forceDrag(data: Any?, preview: Control) {
    ObjectCalls.ptrcallWithVariantAndObjectArg(forceDragBind, handle, data, preview.handle)
}

private const val FORCE_DRAG_HASH = 3191844692L
private val forceDragBind by lazy {
    ObjectCalls.getMethodBind("Control", "force_drag", FORCE_DRAG_HASH)
}
