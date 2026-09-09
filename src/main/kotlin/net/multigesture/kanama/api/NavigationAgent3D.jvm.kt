package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for NavigationAgent3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationAgent3D waits on: ptrcallNoArgsRetPackedVector3List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns this agent's current path from start to finish in global coordinates. The path only
 * updates when the target position is changed or the agent requires a repath. The path array is
 * not intended to be used in direct path movement as the agent has its own internal path logic
 * that would get corrupted by changing the path array manually. Use the intended
 * `get_next_path_position` once every physics frame to receive the next path point for the agents
 * movement as this function also updates the internal path logic.
 *
 * Generated from Godot docs: NavigationAgent3D.get_current_navigation_path
 */
fun NavigationAgent3D.getCurrentNavigationPath(): List<Vector3> {
    return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getCurrentNavigationPathBind, handle)
}

private const val GET_CURRENT_NAVIGATION_PATH_HASH = 497664490L
private val getCurrentNavigationPathBind by lazy {
    ObjectCalls.getMethodBind("NavigationAgent3D", "get_current_navigation_path", GET_CURRENT_NAVIGATION_PATH_HASH)
}
