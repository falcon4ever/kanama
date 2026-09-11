package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFAccessor (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFAccessor waits on: ptrcallWithDictionaryArgRetObject,
//   ptrcallWithPackedFloat64ListArg
// Index: docs/reference/generated/ios-shape-gap.md

fun GLTFAccessor.Companion.fromDictionary(dictionary: Map<String, Any?>): GLTFAccessor? {
    return GLTFAccessor.wrap(ObjectCalls.ptrcallWithDictionaryArgRetObject(fromDictionaryBind, MemorySegment.NULL, dictionary))
}

fun GLTFAccessor.setMin(min: List<Double>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedFloat64ListArg(setMinBind, handle, min)
}

fun GLTFAccessor.setMax(max: List<Double>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedFloat64ListArg(setMaxBind, handle, max)
}

private const val FROM_DICTIONARY_HASH = 3495091019L
private val fromDictionaryBind by lazy {
    ObjectCalls.getMethodBind("GLTFAccessor", "from_dictionary", FROM_DICTIONARY_HASH)
}

private const val SET_MIN_HASH = 2576592201L
private val setMinBind by lazy {
    ObjectCalls.getMethodBind("GLTFAccessor", "set_min", SET_MIN_HASH)
}

private const val SET_MAX_HASH = 2576592201L
private val setMaxBind by lazy {
    ObjectCalls.getMethodBind("GLTFAccessor", "set_max", SET_MAX_HASH)
}
