package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for Animation (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Animation waits on: ptrcallWithIntDoubleVariantDoubleArgsRetInt,
//   ptrcallWithTwoIntAndVariantArg, ptrcallWithTwoIntArgsRetArray
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Inserts a generic key in a given track. Returns the key index.
 *
 * Generated from Godot docs: Animation.track_insert_key
 */
fun Animation.trackInsertKey(trackIdx: Int, time: Double, key: Any?, transition: Double = 1.0): Int {
    checkOpen()
    return ObjectCalls.ptrcallWithIntDoubleVariantDoubleArgsRetInt(trackInsertKeyBind, handle, trackIdx, time, key, transition)
}

/**
 * Sets the value of an existing key.
 *
 * Generated from Godot docs: Animation.track_set_key_value
 */
fun Animation.trackSetKeyValue(trackIdx: Int, key: Int, value: Any?) {
    checkOpen()
    ObjectCalls.ptrcallWithTwoIntAndVariantArg(trackSetKeyValueBind, handle, trackIdx, key, value)
}

/**
 * Returns the arguments values to be called on a method track for a given key in a given track.
 *
 * Generated from Godot docs: Animation.method_track_get_params
 */
fun Animation.methodTrackGetParams(trackIdx: Int, keyIdx: Int): List<Any?> {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetArray(methodTrackGetParamsBind, handle, trackIdx, keyIdx)
}

private const val TRACK_INSERT_KEY_HASH = 808952278L
private val trackInsertKeyBind by lazy {
    ObjectCalls.getMethodBind("Animation", "track_insert_key", TRACK_INSERT_KEY_HASH)
}

private const val TRACK_SET_KEY_VALUE_HASH = 2060538656L
private val trackSetKeyValueBind by lazy {
    ObjectCalls.getMethodBind("Animation", "track_set_key_value", TRACK_SET_KEY_VALUE_HASH)
}

private const val METHOD_TRACK_GET_PARAMS_HASH = 2345056839L
private val methodTrackGetParamsBind by lazy {
    ObjectCalls.getMethodBind("Animation", "method_track_get_params", METHOD_TRACK_GET_PARAMS_HASH)
}
