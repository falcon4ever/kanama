package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFAccessor (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFAccessor waits on: ptrcallNoArgsRetDictionary,
//   ptrcallWithDictionaryArgRetObject
// Index: docs/reference/generated/ios-shape-gap.md

fun GLTFAccessor.Companion.fromDictionary(dictionary: Map<String, Any?>): GLTFAccessor? {
    return GLTFAccessor.wrap(ObjectCalls.ptrcallWithDictionaryArgRetObject(fromDictionaryBind, MemorySegment.NULL, dictionary))
}

fun GLTFAccessor.toDictionary(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(toDictionaryBind, handle)
}

private const val FROM_DICTIONARY_HASH = 3495091019L
private val fromDictionaryBind by lazy {
    ObjectCalls.getMethodBind("GLTFAccessor", "from_dictionary", FROM_DICTIONARY_HASH)
}

private const val TO_DICTIONARY_HASH = 3102165223L
private val toDictionaryBind by lazy {
    ObjectCalls.getMethodBind("GLTFAccessor", "to_dictionary", TO_DICTIONARY_HASH)
}
