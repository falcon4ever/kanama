package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

// GENERATED desktop/Android companion for IterateIK3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP IterateIK3D waits on: ptrcallWithIntArgRetNodePath
// Index: docs/contributing/ios-shape-gap.md

/**
 * Returns the target node that the end bone is trying to reach.
 *
 * Generated from Godot docs: IterateIK3D.get_target_node
 */
fun IterateIK3D.getTargetNode(index: Int): NodePath {
    return ObjectCalls.ptrcallWithIntArgRetNodePath(getTargetNodeBind, handle, index)
}

private const val GET_TARGET_NODE_HASH = 408788394L
private val getTargetNodeBind by lazy {
    ObjectCalls.getMethodBind("IterateIK3D", "get_target_node", GET_TARGET_NODE_HASH)
}
