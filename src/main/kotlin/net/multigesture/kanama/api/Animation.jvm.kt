package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath

// GENERATED desktop/Android companion for Animation (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Animation waits on: ptrcallWithFloatArgRetStringName, ptrcallWithIntArgRetNodePath,
//   ptrcallWithIntDoubleBoolArgsRetVariantScalar, ptrcallWithIntDoubleVariantDoubleArgsRetInt,
//   ptrcallWithTwoIntAndVariantArg, ptrcallWithTwoIntArgsRetArray,
//   ptrcallWithTwoIntArgsRetStringName, ptrcallWithTwoIntArgsRetVariantScalar
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Gets the path of a track. For more information on the path format, see `track_set_path`.
 *
 * Generated from Godot docs: Animation.track_get_path
 */
fun Animation.trackGetPath(trackIdx: Int): NodePath {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetNodePath(trackGetPathBind, handle, trackIdx)
}

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
 * Returns the value of a given key in a given track.
 *
 * Generated from Godot docs: Animation.track_get_key_value
 */
fun Animation.trackGetKeyValue(trackIdx: Int, keyIdx: Int): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetVariantScalar(trackGetKeyValueBind, handle, trackIdx, keyIdx)
}

/**
 * Returns the interpolated value at the given time (in seconds). The `track_idx` must be the index
 * of a value track. A `backward` mainly affects the direction of key retrieval of the track with
 * `UPDATE_DISCRETE` converted by
 * `AnimationMixer.ANIMATION_CALLBACK_MODE_DISCRETE_FORCE_CONTINUOUS` to match the result with
 * `track_find_key`.
 *
 * Generated from Godot docs: Animation.value_track_interpolate
 */
fun Animation.valueTrackInterpolate(trackIdx: Int, timeSec: Double, backward: Boolean = false): Any? {
    checkOpen()
    return ObjectCalls.ptrcallWithIntDoubleBoolArgsRetVariantScalar(valueTrackInterpolateBind, handle, trackIdx, timeSec, backward)
}

/**
 * Returns the method name of a method track.
 *
 * Generated from Godot docs: Animation.method_track_get_name
 */
fun Animation.methodTrackGetName(trackIdx: Int, keyIdx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetStringName(methodTrackGetNameBind, handle, trackIdx, keyIdx)
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

/**
 * Returns the animation name at the key identified by `key_idx`. The `track_idx` must be the index
 * of an Animation Track.
 *
 * Generated from Godot docs: Animation.animation_track_get_key_animation
 */
fun Animation.animationTrackGetKeyAnimation(trackIdx: Int, keyIdx: Int): String {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoIntArgsRetStringName(animationTrackGetKeyAnimationBind, handle, trackIdx, keyIdx)
}

/**
 * Returns the name of the marker located at the given time.
 *
 * Generated from Godot docs: Animation.get_marker_at_time
 */
fun Animation.getMarkerAtTime(time: Double): String {
    checkOpen()
    return ObjectCalls.ptrcallWithFloatArgRetStringName(getMarkerAtTimeBind, handle, time)
}

/**
 * Returns the closest marker that comes after the given time. If no such marker exists, an empty
 * string is returned.
 *
 * Generated from Godot docs: Animation.get_next_marker
 */
fun Animation.getNextMarker(time: Double): String {
    checkOpen()
    return ObjectCalls.ptrcallWithFloatArgRetStringName(getNextMarkerBind, handle, time)
}

/**
 * Returns the closest marker that comes before the given time. If no such marker exists, an empty
 * string is returned.
 *
 * Generated from Godot docs: Animation.get_prev_marker
 */
fun Animation.getPrevMarker(time: Double): String {
    checkOpen()
    return ObjectCalls.ptrcallWithFloatArgRetStringName(getPrevMarkerBind, handle, time)
}

private const val TRACK_GET_PATH_HASH = 408788394L
private val trackGetPathBind by lazy {
    ObjectCalls.getMethodBind("Animation", "track_get_path", TRACK_GET_PATH_HASH)
}

private const val TRACK_INSERT_KEY_HASH = 808952278L
private val trackInsertKeyBind by lazy {
    ObjectCalls.getMethodBind("Animation", "track_insert_key", TRACK_INSERT_KEY_HASH)
}

private const val TRACK_SET_KEY_VALUE_HASH = 2060538656L
private val trackSetKeyValueBind by lazy {
    ObjectCalls.getMethodBind("Animation", "track_set_key_value", TRACK_SET_KEY_VALUE_HASH)
}

private const val TRACK_GET_KEY_VALUE_HASH = 678354945L
private val trackGetKeyValueBind by lazy {
    ObjectCalls.getMethodBind("Animation", "track_get_key_value", TRACK_GET_KEY_VALUE_HASH)
}

private const val VALUE_TRACK_INTERPOLATE_HASH = 747269075L
private val valueTrackInterpolateBind by lazy {
    ObjectCalls.getMethodBind("Animation", "value_track_interpolate", VALUE_TRACK_INTERPOLATE_HASH)
}

private const val METHOD_TRACK_GET_NAME_HASH = 351665558L
private val methodTrackGetNameBind by lazy {
    ObjectCalls.getMethodBind("Animation", "method_track_get_name", METHOD_TRACK_GET_NAME_HASH)
}

private const val METHOD_TRACK_GET_PARAMS_HASH = 2345056839L
private val methodTrackGetParamsBind by lazy {
    ObjectCalls.getMethodBind("Animation", "method_track_get_params", METHOD_TRACK_GET_PARAMS_HASH)
}

private const val ANIMATION_TRACK_GET_KEY_ANIMATION_HASH = 351665558L
private val animationTrackGetKeyAnimationBind by lazy {
    ObjectCalls.getMethodBind("Animation", "animation_track_get_key_animation", ANIMATION_TRACK_GET_KEY_ANIMATION_HASH)
}

private const val GET_MARKER_AT_TIME_HASH = 4079494655L
private val getMarkerAtTimeBind by lazy {
    ObjectCalls.getMethodBind("Animation", "get_marker_at_time", GET_MARKER_AT_TIME_HASH)
}

private const val GET_NEXT_MARKER_HASH = 4079494655L
private val getNextMarkerBind by lazy {
    ObjectCalls.getMethodBind("Animation", "get_next_marker", GET_NEXT_MARKER_HASH)
}

private const val GET_PREV_MARKER_HASH = 4079494655L
private val getPrevMarkerBind by lazy {
    ObjectCalls.getMethodBind("Animation", "get_prev_marker", GET_PREV_MARKER_HASH)
}
