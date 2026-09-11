package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFPhysicsShape (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFPhysicsShape waits on: ptrcallWithDictionaryArgRetObject
// Index: docs/reference/generated/ios-shape-gap.md

fun GLTFPhysicsShape.Companion.fromDictionary(dictionary: Map<String, Any?>): GLTFPhysicsShape? {
    return GLTFPhysicsShape.wrap(ObjectCalls.ptrcallWithDictionaryArgRetObject(fromDictionaryBind, MemorySegment.NULL, dictionary))
}

private const val FROM_DICTIONARY_HASH = 2390691823L
private val fromDictionaryBind by lazy {
    ObjectCalls.getMethodBind("GLTFPhysicsShape", "from_dictionary", FROM_DICTIONARY_HASH)
}
