package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for XRPositionalTracker (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP XRPositionalTracker waits on: ptrcallWithStringNameAndVariantArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Changes the value for the given input. This method is called by an `XRInterface` implementation
 * and should not be used directly.
 *
 * Generated from Godot docs: XRPositionalTracker.set_input
 */
fun XRPositionalTracker.setInput(name: String, value: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithStringNameAndVariantArg(setInputBind, handle, name, value)
}

private const val SET_INPUT_HASH = 3776071444L
private val setInputBind by lazy {
    ObjectCalls.getMethodBind("XRPositionalTracker", "set_input", SET_INPUT_HASH)
}
