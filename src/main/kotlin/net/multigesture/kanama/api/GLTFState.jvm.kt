package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFState (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFState waits on: ptrcallWithByteArrayListArg, ptrcallWithDictionaryArg,
//   ptrcallWithStringNameAndVariantArg
// Index: docs/reference/generated/ios-shape-gap.md

fun GLTFState.setJson(json: Map<String, Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithDictionaryArg(setJsonBind, handle, json)
}

fun GLTFState.setBuffers(buffers: List<ByteArray>) {
    checkOpen()
    ObjectCalls.ptrcallWithByteArrayListArg(setBuffersBind, handle, buffers)
}

fun GLTFState.setAdditionalData(extensionName: String, additionalData: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithStringNameAndVariantArg(setAdditionalDataBind, handle, extensionName, additionalData)
}

private const val SET_JSON_HASH = 4155329257L
private val setJsonBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_json", SET_JSON_HASH)
}

private const val SET_BUFFERS_HASH = 381264803L
private val setBuffersBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_buffers", SET_BUFFERS_HASH)
}

private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
private val setAdditionalDataBind by lazy {
    ObjectCalls.getMethodBind("GLTFState", "set_additional_data", SET_ADDITIONAL_DATA_HASH)
}
