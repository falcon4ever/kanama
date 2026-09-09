package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Shortcut (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Shortcut waits on: ptrcallWithArrayArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The shortcut's `InputEvent` array. Generally the `InputEvent` used is an `InputEventKey`, though
 * it can be any `InputEvent`, including an `InputEventAction`.
 *
 * Generated from Godot docs: Shortcut.set_events
 */
fun Shortcut.setEvents(events: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayArg(setEventsBind, handle, events)
}

private const val SET_EVENTS_HASH = 381264803L
private val setEventsBind by lazy {
    ObjectCalls.getMethodBind("Shortcut", "set_events", SET_EVENTS_HASH)
}
