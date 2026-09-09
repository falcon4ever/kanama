package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for OpenXRActionSet (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRActionSet waits on: ptrcallWithArrayArg
// Index: docs/contributing/ios-shape-gap.md

fun OpenXRActionSet.setActions(actions: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayArg(setActionsBind, handle, actions)
}

private const val SET_ACTIONS_HASH = 381264803L
private val setActionsBind by lazy {
    ObjectCalls.getMethodBind("OpenXRActionSet", "set_actions", SET_ACTIONS_HASH)
}
