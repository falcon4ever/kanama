package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for SceneState (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP SceneState waits on: ptrcallWithIntArgRetPackedStringList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the list of group names associated with the node at `idx`.
 *
 * Generated from Godot docs: SceneState.get_node_groups
 */
fun SceneState.getNodeGroups(idx: Int): List<String> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetPackedStringList(getNodeGroupsBind, handle, idx)
}

private const val GET_NODE_GROUPS_HASH = 647634434L
private val getNodeGroupsBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_node_groups", GET_NODE_GROUPS_HASH)
}
