package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for AnimationNodeStateMachine (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AnimationNodeStateMachine waits on: ptrcallWithIntArgRetStringName,
//   ptrcallWithObjectArgRetStringName
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns the given animation node's name.
 *
 * Generated from Godot docs: AnimationNodeStateMachine.get_node_name
 */
fun AnimationNodeStateMachine.getNodeName(node: AnimationNode?): String {
    checkOpen()
    return ObjectCalls.ptrcallWithObjectArgRetStringName(getNodeNameBind, handle, node?.requireOpenHandle() ?: MemorySegment.NULL)
}

/**
 * Returns the given transition's start node.
 *
 * Generated from Godot docs: AnimationNodeStateMachine.get_transition_from
 */
fun AnimationNodeStateMachine.getTransitionFrom(idx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getTransitionFromBind, handle, idx)
}

/**
 * Returns the given transition's end node.
 *
 * Generated from Godot docs: AnimationNodeStateMachine.get_transition_to
 */
fun AnimationNodeStateMachine.getTransitionTo(idx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetStringName(getTransitionToBind, handle, idx)
}

private const val GET_NODE_NAME_HASH = 739213945L
private val getNodeNameBind by lazy {
    ObjectCalls.getMethodBind("AnimationNodeStateMachine", "get_node_name", GET_NODE_NAME_HASH)
}

private const val GET_TRANSITION_FROM_HASH = 659327637L
private val getTransitionFromBind by lazy {
    ObjectCalls.getMethodBind("AnimationNodeStateMachine", "get_transition_from", GET_TRANSITION_FROM_HASH)
}

private const val GET_TRANSITION_TO_HASH = 659327637L
private val getTransitionToBind by lazy {
    ObjectCalls.getMethodBind("AnimationNodeStateMachine", "get_transition_to", GET_TRANSITION_TO_HASH)
}
