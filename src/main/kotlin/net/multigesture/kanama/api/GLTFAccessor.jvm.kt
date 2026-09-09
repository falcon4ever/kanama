package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for GLTFAccessor (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP GLTFAccessor waits on: ptrcallNoArgsRetDictionary,
//   ptrcallNoArgsRetPackedFloat64List, ptrcallWithDictionaryArgRetObject,
//   ptrcallWithPackedFloat64ListArg
// Index: docs/contributing/ios-shape-gap.md

fun GLTFAccessor.Companion.fromDictionary(dictionary: Map<String, Any?>): GLTFAccessor? {
    return GLTFAccessor.wrap(ObjectCalls.ptrcallWithDictionaryArgRetObject(fromDictionaryBind, MemorySegment.NULL, dictionary))
}

fun GLTFAccessor.toDictionary(): Map<String, Any?> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetDictionary(toDictionaryBind, handle)
}

fun GLTFAccessor.getMin(): List<Double> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedFloat64List(getMinBind, handle)
}

fun GLTFAccessor.setMin(min: List<Double>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedFloat64ListArg(setMinBind, handle, min)
}

fun GLTFAccessor.getMax(): List<Double> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedFloat64List(getMaxBind, handle)
}

fun GLTFAccessor.setMax(max: List<Double>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedFloat64ListArg(setMaxBind, handle, max)
}

var GLTFAccessor.min: List<Double>
    @JvmName("minProperty")
    get() = getMin()
    @JvmName("setMinProperty")
    set(value) = setMin(value)

var GLTFAccessor.max: List<Double>
    @JvmName("maxProperty")
    get() = getMax()
    @JvmName("setMaxProperty")
    set(value) = setMax(value)

private const val FROM_DICTIONARY_HASH = 3495091019L
private val fromDictionaryBind by lazy {
    ObjectCalls.getMethodBind("GLTFAccessor", "from_dictionary", FROM_DICTIONARY_HASH)
}

private const val TO_DICTIONARY_HASH = 3102165223L
private val toDictionaryBind by lazy {
    ObjectCalls.getMethodBind("GLTFAccessor", "to_dictionary", TO_DICTIONARY_HASH)
}

private const val GET_MIN_HASH = 547233126L
private val getMinBind by lazy {
    ObjectCalls.getMethodBind("GLTFAccessor", "get_min", GET_MIN_HASH)
}

private const val SET_MIN_HASH = 2576592201L
private val setMinBind by lazy {
    ObjectCalls.getMethodBind("GLTFAccessor", "set_min", SET_MIN_HASH)
}

private const val GET_MAX_HASH = 547233126L
private val getMaxBind by lazy {
    ObjectCalls.getMethodBind("GLTFAccessor", "get_max", GET_MAX_HASH)
}

private const val SET_MAX_HASH = 2576592201L
private val setMaxBind by lazy {
    ObjectCalls.getMethodBind("GLTFAccessor", "set_max", SET_MAX_HASH)
}
