package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for NavigationPathQueryResult3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationPathQueryResult3D waits on: ptrcallWithRIDListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The `RID`s of the regions and links that each point of the path goes through.
 *
 * Generated from Godot docs: NavigationPathQueryResult3D.set_path_rids
 */
fun NavigationPathQueryResult3D.setPathRids(pathRids: List<RID>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDListArg(setPathRidsBind, handle, pathRids)
}

private const val SET_PATH_RIDS_HASH = 381264803L
private val setPathRidsBind by lazy {
    ObjectCalls.getMethodBind("NavigationPathQueryResult3D", "set_path_rids", SET_PATH_RIDS_HASH)
}
