package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GraphEdit (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GraphEdit waits on: ptrcallWithDictionaryArg, ptrcallWithDictionaryListArg,
//   ptrcallWithStringNameArgRetStringNameList
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The connections between `GraphNode`s. A connection is represented as a `Dictionary` in the form
 * of:
 *
 * Generated from Godot docs: GraphEdit.set_connections
 */
fun GraphEdit.setConnections(connections: List<Map<String, Any?>>) {
    ObjectCalls.ptrcallWithDictionaryListArg(setConnectionsBind, handle, connections)
}

/**
 * Returns an array of node names that are attached to the `GraphFrame` with the given name.
 *
 * Generated from Godot docs: GraphEdit.get_attached_nodes_of_frame
 */
fun GraphEdit.getAttachedNodesOfFrame(frame: String): List<String> {
    return ObjectCalls.ptrcallWithStringNameArgRetStringNameList(getAttachedNodesOfFrameBind, handle, frame)
}

/**
 * `Dictionary` of human-readable port type names.
 *
 * Generated from Godot docs: GraphEdit.set_type_names
 */
fun GraphEdit.setTypeNames(typeNames: Map<String, Any?>) {
    ObjectCalls.ptrcallWithDictionaryArg(setTypeNamesBind, handle, typeNames)
}

private const val SET_CONNECTIONS_HASH = 381264803L
private val setConnectionsBind by lazy {
    ObjectCalls.getMethodBind("GraphEdit", "set_connections", SET_CONNECTIONS_HASH)
}

private const val GET_ATTACHED_NODES_OF_FRAME_HASH = 689397652L
private val getAttachedNodesOfFrameBind by lazy {
    ObjectCalls.getMethodBind("GraphEdit", "get_attached_nodes_of_frame", GET_ATTACHED_NODES_OF_FRAME_HASH)
}

private const val SET_TYPE_NAMES_HASH = 4155329257L
private val setTypeNamesBind by lazy {
    ObjectCalls.getMethodBind("GraphEdit", "set_type_names", SET_TYPE_NAMES_HASH)
}
