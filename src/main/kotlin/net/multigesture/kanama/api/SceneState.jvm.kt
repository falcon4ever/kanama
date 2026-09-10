package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for SceneState (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP SceneState waits on: ptrcallWithIntArgRetArray,
//   ptrcallWithIntArgRetPackedStringList, ptrcallWithTwoIntArgsRetVariantScalar
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

/**
 * Returns the value of the property at `prop_idx` for the node at `idx`.
 *
 * Generated from Godot docs: SceneState.get_node_property_value
 */
fun SceneState.getNodePropertyValue(idx: Int, propIdx: Int): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetVariantScalar(getNodePropertyValueBind, handle, idx, propIdx)
}

/**
 * Returns the list of bound parameters for the signal at `idx`.
 *
 * Generated from Godot docs: SceneState.get_connection_binds
 */
fun SceneState.getConnectionBinds(idx: Int): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetArray(getConnectionBindsBind, handle, idx)
}

private const val GET_NODE_GROUPS_HASH = 647634434L
private val getNodeGroupsBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_node_groups", GET_NODE_GROUPS_HASH)
}

private const val GET_NODE_PROPERTY_VALUE_HASH = 678354945L
private val getNodePropertyValueBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_node_property_value", GET_NODE_PROPERTY_VALUE_HASH)
}

private const val GET_CONNECTION_BINDS_HASH = 663333327L
private val getConnectionBindsBind by lazy {
    ObjectCalls.getMethodBind("SceneState", "get_connection_binds", GET_CONNECTION_BINDS_HASH)
}
