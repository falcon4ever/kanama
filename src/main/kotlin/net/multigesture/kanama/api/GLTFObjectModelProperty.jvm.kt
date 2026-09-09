package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

// GENERATED desktop/Android companion for GLTFObjectModelProperty (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFObjectModelProperty waits on: ptrcallNoArgsRetPackedStringListList,
//   ptrcallWithNodePathListArg, ptrcallWithPackedStringListListArg
// Index: docs/contributing/ios-shape-gap.md

fun GLTFObjectModelProperty.setNodePaths(nodePaths: List<NodePath>) {
    checkOpen()
    ObjectCalls.ptrcallWithNodePathListArg(setNodePathsBind, handle, nodePaths)
}

fun GLTFObjectModelProperty.getJsonPointers(): List<List<String>> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedStringListList(getJsonPointersBind, handle)
}

fun GLTFObjectModelProperty.setJsonPointers(jsonPointers: List<List<String>>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedStringListListArg(setJsonPointersBind, handle, jsonPointers)
}

var GLTFObjectModelProperty.jsonPointers: List<List<String>>
    @JvmName("jsonPointersProperty")
    get() = getJsonPointers()
    @JvmName("setJsonPointersProperty")
    set(value) = setJsonPointers(value)

private const val SET_NODE_PATHS_HASH = 381264803L
private val setNodePathsBind by lazy {
    ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_node_paths", SET_NODE_PATHS_HASH)
}

private const val GET_JSON_POINTERS_HASH = 3995934104L
private val getJsonPointersBind by lazy {
    ObjectCalls.getMethodBind("GLTFObjectModelProperty", "get_json_pointers", GET_JSON_POINTERS_HASH)
}

private const val SET_JSON_POINTERS_HASH = 381264803L
private val setJsonPointersBind by lazy {
    ObjectCalls.getMethodBind("GLTFObjectModelProperty", "set_json_pointers", SET_JSON_POINTERS_HASH)
}
