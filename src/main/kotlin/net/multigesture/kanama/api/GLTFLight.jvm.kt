package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFLight (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFLight waits on: ptrcallNoArgsRetDictionary, ptrcallWithDictionaryArgRetObject,
//   ptrcallWithStringNameAndVariantArg
// Index: docs/reference/generated/ios-shape-gap.md

fun GLTFLight.Companion.fromDictionary(dictionary: Map<String, Any?>): GLTFLight? {
    return GLTFLight.wrap(ObjectCalls.ptrcallWithDictionaryArgRetObject(fromDictionaryBind, MemorySegment.NULL, dictionary))
}

fun GLTFLight.toDictionary(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(toDictionaryBind, handle)
}

fun GLTFLight.setAdditionalData(extensionName: String, additionalData: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithStringNameAndVariantArg(setAdditionalDataBind, handle, extensionName, additionalData)
}

private const val FROM_DICTIONARY_HASH = 4057087208L
private val fromDictionaryBind by lazy {
    ObjectCalls.getMethodBind("GLTFLight", "from_dictionary", FROM_DICTIONARY_HASH)
}

private const val TO_DICTIONARY_HASH = 3102165223L
private val toDictionaryBind by lazy {
    ObjectCalls.getMethodBind("GLTFLight", "to_dictionary", TO_DICTIONARY_HASH)
}

private const val SET_ADDITIONAL_DATA_HASH = 3776071444L
private val setAdditionalDataBind by lazy {
    ObjectCalls.getMethodBind("GLTFLight", "set_additional_data", SET_ADDITIONAL_DATA_HASH)
}
