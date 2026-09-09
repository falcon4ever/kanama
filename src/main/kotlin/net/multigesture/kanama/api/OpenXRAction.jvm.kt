package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for OpenXRAction (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP OpenXRAction waits on: ptrcallWithPackedStringListArg
// Index: docs/contributing/ios-shape-gap.md

fun OpenXRAction.setToplevelPaths(toplevelPaths: List<String>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedStringListArg(setToplevelPathsBind, handle, toplevelPaths)
}

private const val SET_TOPLEVEL_PATHS_HASH = 4015028928L
private val setToplevelPathsBind by lazy {
    ObjectCalls.getMethodBind("OpenXRAction", "set_toplevel_paths", SET_TOPLEVEL_PATHS_HASH)
}
