package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFNode (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFNode waits on: ptrcallWithPackedInt32ListArg,
//   ptrcallWithStringNameAndVariantArg
// Index: docs/contributing/ios-shape-gap.md

fun GLTFNode.setChildren(children: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(setChildrenBind, handle, children)
}

fun GLTFNode.setAdditionalData(extensionName: String, additionalData: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithStringNameAndVariantArg(setAdditionalDataBind, handle, extensionName, additionalData)
}

private const val SET_CHILDREN_HASH = 3614634198L
private val setChildrenBind by lazy {
    ObjectCalls.getMethodBind("GLTFNode", "set_children", SET_CHILDREN_HASH)
}

private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
private val setAdditionalDataBind by lazy {
    ObjectCalls.getMethodBind("GLTFNode", "set_additional_data", SET_ADDITIONAL_DATA_HASH)
}
