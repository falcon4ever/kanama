package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFBufferView (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFBufferView waits on: ptrcallNoArgsRetDictionary,
//   ptrcallWithDictionaryArgRetObject, ptrcallWithObjectArgRetByteArray
// Index: docs/contributing/ios-shape-gap.md

fun GLTFBufferView.loadBufferViewData(state: GLTFState?): ByteArray {
    checkOpen()
    return ObjectCalls.ptrcallWithObjectArgRetByteArray(loadBufferViewDataBind, handle, state?.requireOpenHandle() ?: MemorySegment.NULL)
}

fun GLTFBufferView.Companion.fromDictionary(dictionary: Map<String, Any?>): GLTFBufferView? {
    return GLTFBufferView.wrap(ObjectCalls.ptrcallWithDictionaryArgRetObject(fromDictionaryBind, MemorySegment.NULL, dictionary))
}

fun GLTFBufferView.toDictionary(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(toDictionaryBind, handle)
}

private const val LOAD_BUFFER_VIEW_DATA_HASH = 3945446907L
private val loadBufferViewDataBind by lazy {
    ObjectCalls.getMethodBind("GLTFBufferView", "load_buffer_view_data", LOAD_BUFFER_VIEW_DATA_HASH)
}

private const val FROM_DICTIONARY_HASH = 2594413512L
private val fromDictionaryBind by lazy {
    ObjectCalls.getMethodBind("GLTFBufferView", "from_dictionary", FROM_DICTIONARY_HASH)
}

private const val TO_DICTIONARY_HASH = 3102165223L
private val toDictionaryBind by lazy {
    ObjectCalls.getMethodBind("GLTFBufferView", "to_dictionary", TO_DICTIONARY_HASH)
}
