package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFPhysicsBody (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFPhysicsBody waits on: ptrcallNoArgsRetDictionary,
//   ptrcallWithDictionaryArgRetObject
// Index: docs/reference/generated/ios-shape-gap.md

fun GLTFPhysicsBody.Companion.fromDictionary(dictionary: Map<String, Any?>): GLTFPhysicsBody? {
    return GLTFPhysicsBody.wrap(ObjectCalls.ptrcallWithDictionaryArgRetObject(fromDictionaryBind, MemorySegment.NULL, dictionary))
}

fun GLTFPhysicsBody.toDictionary(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(toDictionaryBind, handle)
}

private const val FROM_DICTIONARY_HASH = 1177544336L
private val fromDictionaryBind by lazy {
    ObjectCalls.getMethodBind("GLTFPhysicsBody", "from_dictionary", FROM_DICTIONARY_HASH)
}

private const val TO_DICTIONARY_HASH = 3102165223L
private val toDictionaryBind by lazy {
    ObjectCalls.getMethodBind("GLTFPhysicsBody", "to_dictionary", TO_DICTIONARY_HASH)
}
