package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for VisualShader (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP VisualShader waits on: ptrcallWithLongArgRetDictionaryList,
//   ptrcallWithLongArgRetPackedInt32List
// Index: docs/contributing/ios-shape-gap.md

fun VisualShader.getNodeList(type: Long): List<Int> {
    checkOpen()
    return ObjectCalls.ptrcallWithLongArgRetPackedInt32List(getNodeListBind, handle, type)
}

fun VisualShader.getNodeConnections(type: Long): List<Map<String, Any?>> {
    checkOpen()
    return ObjectCalls.ptrcallWithLongArgRetDictionaryList(getNodeConnectionsBind, handle, type)
}

private const val GET_NODE_LIST_HASH = 2370592410L
private val getNodeListBind by lazy {
    ObjectCalls.getMethodBind("VisualShader", "get_node_list", GET_NODE_LIST_HASH)
}

private const val GET_NODE_CONNECTIONS_HASH = 1441964831L
private val getNodeConnectionsBind by lazy {
    ObjectCalls.getMethodBind("VisualShader", "get_node_connections", GET_NODE_CONNECTIONS_HASH)
}
