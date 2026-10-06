package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector3

/**
 * Holds data that can be used to animate anything in the engine.
 *
 * Generated from Godot docs: Animation
 */
class Animation(handle: GodotHandle) : Resource(handle) {
    var length: Double
        @JvmName("lengthProperty")
        get() = getLength()
        @JvmName("setLengthProperty")
        set(value) = setLength(value)

    var loopMode: Animation.LoopMode
        @JvmName("loopModeProperty")
        get() = getLoopMode()
        @JvmName("setLoopModeProperty")
        set(value) = setLoopMode(value)

    var step: Double
        @JvmName("stepProperty")
        get() = getStep()
        @JvmName("setStepProperty")
        set(value) = setStep(value)

    val captureIncluded: Boolean
        @JvmName("captureIncludedProperty")
        get() = isCaptureIncluded()

    /**
     * Adds a track to the Animation.
     *
     * Generated from Godot docs: Animation.add_track
     */
    fun addTrack(type: Animation.TrackType, atPosition: Int = -1): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithLongAndIntArgsRetInt(Binds.addTrackBind, segment, type.value, atPosition)
    }

    /**
     * Removes a track by specifying the track index.
     *
     * Generated from Godot docs: Animation.remove_track
     */
    fun removeTrack(trackIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeTrackBind, segment, trackIdx)
    }

    /**
     * Returns the amount of tracks in the animation.
     *
     * Generated from Godot docs: Animation.get_track_count
     */
    fun getTrackCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getTrackCountBind, segment)
    }

    /**
     * Gets the type of a track.
     *
     * Generated from Godot docs: Animation.track_get_type
     */
    fun trackGetType(trackIdx: Int): Animation.TrackType {
        checkOpen()
        return Animation.TrackType(ObjectCalls.ptrcallWithIntArgRetLong(Binds.trackGetTypeBind, segment, trackIdx))
    }

    /**
     * Gets the path of a track. For more information on the path format, see `track_set_path`.
     *
     * Generated from Godot docs: Animation.track_get_path
     */
    fun trackGetPath(trackIdx: Int): NodePath {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetNodePath(Binds.trackGetPathBind, segment, trackIdx)
    }

    /**
     * Sets the path of a track. Paths must be valid scene-tree paths to a node and must be specified
     * starting from the `AnimationMixer.root_node` that will reproduce the animation. Tracks that
     * control properties or bones must append their name after the path, separated by `":"`. For
     * example, `"character/skeleton:ankle"` or `"character/mesh:transform/local"`.
     *
     * Generated from Godot docs: Animation.track_set_path
     */
    fun trackSetPath(trackIdx: Int, path: NodePath) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndNodePathArg(Binds.trackSetPathBind, segment, trackIdx, path)
    }

    /**
     * Returns the index of the specified track. If the track is not found, return -1.
     *
     * Generated from Godot docs: Animation.find_track
     */
    fun findTrack(path: NodePath, type: Animation.TrackType): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithNodePathAndLongArgRetInt(Binds.findTrackBind, segment, path, type.value)
    }

    /**
     * Moves a track up.
     *
     * Generated from Godot docs: Animation.track_move_up
     */
    fun trackMoveUp(trackIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.trackMoveUpBind, segment, trackIdx)
    }

    /**
     * Moves a track down.
     *
     * Generated from Godot docs: Animation.track_move_down
     */
    fun trackMoveDown(trackIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.trackMoveDownBind, segment, trackIdx)
    }

    /**
     * Changes the index position of track `track_idx` to the one defined in `to_idx`.
     *
     * Generated from Godot docs: Animation.track_move_to
     */
    fun trackMoveTo(trackIdx: Int, toIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.trackMoveToBind, segment, trackIdx, toIdx)
    }

    /**
     * Swaps the track `track_idx`'s index position with the track `with_idx`.
     *
     * Generated from Godot docs: Animation.track_swap
     */
    fun trackSwap(trackIdx: Int, withIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.trackSwapBind, segment, trackIdx, withIdx)
    }

    /**
     * Sets the given track as imported or not.
     *
     * Generated from Godot docs: Animation.track_set_imported
     */
    fun trackSetImported(trackIdx: Int, imported: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.trackSetImportedBind, segment, trackIdx, imported)
    }

    /**
     * Returns `true` if the given track is imported. Else, return `false`.
     *
     * Generated from Godot docs: Animation.track_is_imported
     */
    fun trackIsImported(trackIdx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.trackIsImportedBind, segment, trackIdx)
    }

    /**
     * Enables/disables the given track. Tracks are enabled by default.
     *
     * Generated from Godot docs: Animation.track_set_enabled
     */
    fun trackSetEnabled(trackIdx: Int, enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.trackSetEnabledBind, segment, trackIdx, enabled)
    }

    /**
     * Returns `true` if the track at index `track_idx` is enabled.
     *
     * Generated from Godot docs: Animation.track_is_enabled
     */
    fun trackIsEnabled(trackIdx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.trackIsEnabledBind, segment, trackIdx)
    }

    /**
     * Inserts a key in a given 3D position track. Returns the key index.
     *
     * Generated from Godot docs: Animation.position_track_insert_key
     */
    fun positionTrackInsertKey(trackIdx: Int, time: Double, position: Vector3): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntDoubleVector3ArgsRetInt(Binds.positionTrackInsertKeyBind, segment, trackIdx, time, position)
    }

    /**
     * Inserts a key in a given 3D rotation track. Returns the key index.
     *
     * Generated from Godot docs: Animation.rotation_track_insert_key
     */
    fun rotationTrackInsertKey(trackIdx: Int, time: Double, rotation: Quaternion): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntDoubleQuaternionArgsRetInt(Binds.rotationTrackInsertKeyBind, segment, trackIdx, time, rotation)
    }

    /**
     * Inserts a key in a given 3D scale track. Returns the key index.
     *
     * Generated from Godot docs: Animation.scale_track_insert_key
     */
    fun scaleTrackInsertKey(trackIdx: Int, time: Double, scale: Vector3): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntDoubleVector3ArgsRetInt(Binds.scaleTrackInsertKeyBind, segment, trackIdx, time, scale)
    }

    /**
     * Inserts a key in a given blend shape track. Returns the key index.
     *
     * Generated from Godot docs: Animation.blend_shape_track_insert_key
     */
    fun blendShapeTrackInsertKey(trackIdx: Int, time: Double, amount: Double): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndTwoDoubleArgsRetInt(Binds.blendShapeTrackInsertKeyBind, segment, trackIdx, time, amount)
    }

    /**
     * Returns the interpolated position value at the given time (in seconds). The `track_idx` must be
     * the index of a 3D position track.
     *
     * Generated from Godot docs: Animation.position_track_interpolate
     */
    fun positionTrackInterpolate(trackIdx: Int, timeSec: Double, backward: Boolean = false): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntDoubleBoolArgsRetVector3(Binds.positionTrackInterpolateBind, segment, trackIdx, timeSec, backward)
    }

    /**
     * Returns the interpolated rotation value at the given time (in seconds). The `track_idx` must be
     * the index of a 3D rotation track.
     *
     * Generated from Godot docs: Animation.rotation_track_interpolate
     */
    fun rotationTrackInterpolate(trackIdx: Int, timeSec: Double, backward: Boolean = false): Quaternion {
        checkOpen()
        return ObjectCalls.ptrcallWithIntDoubleBoolArgsRetQuaternion(Binds.rotationTrackInterpolateBind, segment, trackIdx, timeSec, backward)
    }

    /**
     * Returns the interpolated scale value at the given time (in seconds). The `track_idx` must be the
     * index of a 3D scale track.
     *
     * Generated from Godot docs: Animation.scale_track_interpolate
     */
    fun scaleTrackInterpolate(trackIdx: Int, timeSec: Double, backward: Boolean = false): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithIntDoubleBoolArgsRetVector3(Binds.scaleTrackInterpolateBind, segment, trackIdx, timeSec, backward)
    }

    /**
     * Returns the interpolated blend shape value at the given time (in seconds). The `track_idx` must
     * be the index of a blend shape track.
     *
     * Generated from Godot docs: Animation.blend_shape_track_interpolate
     */
    fun blendShapeTrackInterpolate(trackIdx: Int, timeSec: Double, backward: Boolean = false): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntDoubleBoolArgsRetDouble(Binds.blendShapeTrackInterpolateBind, segment, trackIdx, timeSec, backward)
    }

    /**
     * Inserts a generic key in a given track. Returns the key index.
     *
     * Generated from Godot docs: Animation.track_insert_key
     */
    fun trackInsertKey(trackIdx: Int, time: Double, key: Any?, transition: Double = 1.0): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntDoubleVariantDoubleArgsRetInt(Binds.trackInsertKeyBind, segment, trackIdx, time, key, transition)
    }

    /**
     * Removes a key by index in a given track.
     *
     * Generated from Godot docs: Animation.track_remove_key
     */
    fun trackRemoveKey(trackIdx: Int, keyIdx: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.trackRemoveKeyBind, segment, trackIdx, keyIdx)
    }

    /**
     * Removes a key at `time` in a given track.
     *
     * Generated from Godot docs: Animation.track_remove_key_at_time
     */
    fun trackRemoveKeyAtTime(trackIdx: Int, time: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.trackRemoveKeyAtTimeBind, segment, trackIdx, time)
    }

    /**
     * Sets the value of an existing key.
     *
     * Generated from Godot docs: Animation.track_set_key_value
     */
    fun trackSetKeyValue(trackIdx: Int, key: Int, value: Any?) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndVariantArg(Binds.trackSetKeyValueBind, segment, trackIdx, key, value)
    }

    /**
     * Sets the transition curve (easing) for a specific key (see the built-in math function
     * `@GlobalScope.ease`).
     *
     * Generated from Godot docs: Animation.track_set_key_transition
     */
    fun trackSetKeyTransition(trackIdx: Int, keyIdx: Int, transition: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.trackSetKeyTransitionBind, segment, trackIdx, keyIdx, transition)
    }

    /**
     * Sets the time of an existing key.
     *
     * Generated from Godot docs: Animation.track_set_key_time
     */
    fun trackSetKeyTime(trackIdx: Int, keyIdx: Int, time: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.trackSetKeyTimeBind, segment, trackIdx, keyIdx, time)
    }

    /**
     * Returns the transition curve (easing) for a specific key (see the built-in math function
     * `@GlobalScope.ease`).
     *
     * Generated from Godot docs: Animation.track_get_key_transition
     */
    fun trackGetKeyTransition(trackIdx: Int, keyIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.trackGetKeyTransitionBind, segment, trackIdx, keyIdx)
    }

    /**
     * Returns the number of keys in a given track.
     *
     * Generated from Godot docs: Animation.track_get_key_count
     */
    fun trackGetKeyCount(trackIdx: Int): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.trackGetKeyCountBind, segment, trackIdx)
    }

    /**
     * Returns the value of a given key in a given track.
     *
     * Generated from Godot docs: Animation.track_get_key_value
     */
    fun trackGetKeyValue(trackIdx: Int, keyIdx: Int): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetVariantScalar(Binds.trackGetKeyValueBind, segment, trackIdx, keyIdx)
    }

    /**
     * Returns the time at which the key is located.
     *
     * Generated from Godot docs: Animation.track_get_key_time
     */
    fun trackGetKeyTime(trackIdx: Int, keyIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.trackGetKeyTimeBind, segment, trackIdx, keyIdx)
    }

    /**
     * Finds the key index by time in a given track. Optionally, only find it if the approx/exact time
     * is given. If `limit` is `true`, it does not return keys outside the animation range. If
     * `backward` is `true`, the direction is reversed in methods that rely on one directional
     * processing. For example, in case `find_mode` is `FindMode.NEAREST`, if there is no key in the
     * current position just after seeked, the first key found is retrieved by searching before the
     * position, but if `backward` is `true`, the first key found is retrieved after the position.
     *
     * Generated from Godot docs: Animation.track_find_key
     */
    fun trackFindKey(trackIdx: Int, time: Double, findMode: Animation.FindMode = Animation.FindMode.NEAREST, limit: Boolean = false, backward: Boolean = false): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntDoubleLongTwoBoolArgsRetInt(Binds.trackFindKeyBind, segment, trackIdx, time, findMode.value, limit, backward)
    }

    /**
     * Sets the interpolation type of a given track.
     *
     * Generated from Godot docs: Animation.track_set_interpolation_type
     */
    fun trackSetInterpolationType(trackIdx: Int, interpolation: Animation.InterpolationType) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.trackSetInterpolationTypeBind, segment, trackIdx, interpolation.value)
    }

    /**
     * Returns the interpolation type of a given track.
     *
     * Generated from Godot docs: Animation.track_get_interpolation_type
     */
    fun trackGetInterpolationType(trackIdx: Int): Animation.InterpolationType {
        checkOpen()
        return Animation.InterpolationType(ObjectCalls.ptrcallWithIntArgRetLong(Binds.trackGetInterpolationTypeBind, segment, trackIdx))
    }

    /**
     * If `true`, the track at `track_idx` wraps the interpolation loop.
     *
     * Generated from Godot docs: Animation.track_set_interpolation_loop_wrap
     */
    fun trackSetInterpolationLoopWrap(trackIdx: Int, interpolation: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.trackSetInterpolationLoopWrapBind, segment, trackIdx, interpolation)
    }

    /**
     * Returns `true` if the track at `track_idx` wraps the interpolation loop. New tracks wrap the
     * interpolation loop by default.
     *
     * Generated from Godot docs: Animation.track_get_interpolation_loop_wrap
     */
    fun trackGetInterpolationLoopWrap(trackIdx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.trackGetInterpolationLoopWrapBind, segment, trackIdx)
    }

    /**
     * Returns `true` if the track is compressed, `false` otherwise. See also `compress`.
     *
     * Generated from Godot docs: Animation.track_is_compressed
     */
    fun trackIsCompressed(trackIdx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.trackIsCompressedBind, segment, trackIdx)
    }

    /**
     * Sets the update mode of a value track.
     *
     * Generated from Godot docs: Animation.value_track_set_update_mode
     */
    fun valueTrackSetUpdateMode(trackIdx: Int, mode: Animation.UpdateMode) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.valueTrackSetUpdateModeBind, segment, trackIdx, mode.value)
    }

    /**
     * Returns the update mode of a value track.
     *
     * Generated from Godot docs: Animation.value_track_get_update_mode
     */
    fun valueTrackGetUpdateMode(trackIdx: Int): Animation.UpdateMode {
        checkOpen()
        return Animation.UpdateMode(ObjectCalls.ptrcallWithIntArgRetLong(Binds.valueTrackGetUpdateModeBind, segment, trackIdx))
    }

    /**
     * Returns the interpolated value at the given time (in seconds). The `track_idx` must be the index
     * of a value track. A `backward` mainly affects the direction of key retrieval of the track with
     * `UpdateMode.DISCRETE` converted by
     * `AnimationMixer.AnimationCallbackModeDiscrete.FORCE_CONTINUOUS` to match the result with
     * `track_find_key`.
     *
     * Generated from Godot docs: Animation.value_track_interpolate
     */
    fun valueTrackInterpolate(trackIdx: Int, timeSec: Double, backward: Boolean = false): Any? {
        checkOpen()
        return ObjectCalls.ptrcallWithIntDoubleBoolArgsRetVariantScalar(Binds.valueTrackInterpolateBind, segment, trackIdx, timeSec, backward)
    }

    /**
     * Returns the method name of a method track.
     *
     * Generated from Godot docs: Animation.method_track_get_name
     */
    fun methodTrackGetName(trackIdx: Int, keyIdx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetStringName(Binds.methodTrackGetNameBind, segment, trackIdx, keyIdx)
    }

    /**
     * Returns the arguments values to be called on a method track for a given key in a given track.
     *
     * Generated from Godot docs: Animation.method_track_get_params
     */
    fun methodTrackGetParams(trackIdx: Int, keyIdx: Int): List<Any?> {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetArray(Binds.methodTrackGetParamsBind, segment, trackIdx, keyIdx)
    }

    /**
     * Inserts a Bezier Track key at the given `time` in seconds. The `track_idx` must be the index of
     * a Bezier Track. `in_handle` is the left-side weight of the added Bezier curve point,
     * `out_handle` is the right-side one, while `value` is the actual value at this point.
     *
     * Generated from Godot docs: Animation.bezier_track_insert_key
     */
    fun bezierTrackInsertKey(trackIdx: Int, time: Double, value: Double, inHandle: Vector2 = Vector2(0.0, 0.0), outHandle: Vector2 = Vector2(0.0, 0.0)): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntTwoDoubleTwoVector2ArgsRetInt(Binds.bezierTrackInsertKeyBind, segment, trackIdx, time, value, inHandle, outHandle)
    }

    /**
     * Sets the value of the key identified by `key_idx` to the given value. The `track_idx` must be
     * the index of a Bezier Track.
     *
     * Generated from Godot docs: Animation.bezier_track_set_key_value
     */
    fun bezierTrackSetKeyValue(trackIdx: Int, keyIdx: Int, value: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.bezierTrackSetKeyValueBind, segment, trackIdx, keyIdx, value)
    }

    /**
     * Sets the in handle of the key identified by `key_idx` to value `in_handle`. The `track_idx` must
     * be the index of a Bezier Track.
     *
     * Generated from Godot docs: Animation.bezier_track_set_key_in_handle
     */
    fun bezierTrackSetKeyInHandle(trackIdx: Int, keyIdx: Int, inHandle: Vector2, balancedValueTimeRatio: Double = 1.0) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntVector2DoubleArgs(Binds.bezierTrackSetKeyInHandleBind, segment, trackIdx, keyIdx, inHandle, balancedValueTimeRatio)
    }

    /**
     * Sets the out handle of the key identified by `key_idx` to value `out_handle`. The `track_idx`
     * must be the index of a Bezier Track.
     *
     * Generated from Godot docs: Animation.bezier_track_set_key_out_handle
     */
    fun bezierTrackSetKeyOutHandle(trackIdx: Int, keyIdx: Int, outHandle: Vector2, balancedValueTimeRatio: Double = 1.0) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntVector2DoubleArgs(Binds.bezierTrackSetKeyOutHandleBind, segment, trackIdx, keyIdx, outHandle, balancedValueTimeRatio)
    }

    /**
     * Returns the value of the key identified by `key_idx`. The `track_idx` must be the index of a
     * Bezier Track.
     *
     * Generated from Godot docs: Animation.bezier_track_get_key_value
     */
    fun bezierTrackGetKeyValue(trackIdx: Int, keyIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.bezierTrackGetKeyValueBind, segment, trackIdx, keyIdx)
    }

    /**
     * Returns the in handle of the key identified by `key_idx`. The `track_idx` must be the index of a
     * Bezier Track.
     *
     * Generated from Godot docs: Animation.bezier_track_get_key_in_handle
     */
    fun bezierTrackGetKeyInHandle(trackIdx: Int, keyIdx: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector2(Binds.bezierTrackGetKeyInHandleBind, segment, trackIdx, keyIdx)
    }

    /**
     * Returns the out handle of the key identified by `key_idx`. The `track_idx` must be the index of
     * a Bezier Track.
     *
     * Generated from Godot docs: Animation.bezier_track_get_key_out_handle
     */
    fun bezierTrackGetKeyOutHandle(trackIdx: Int, keyIdx: Int): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector2(Binds.bezierTrackGetKeyOutHandleBind, segment, trackIdx, keyIdx)
    }

    /**
     * Returns the interpolated value at the given `time` (in seconds). The `track_idx` must be the
     * index of a Bezier Track.
     *
     * Generated from Godot docs: Animation.bezier_track_interpolate
     */
    fun bezierTrackInterpolate(trackIdx: Int, time: Double): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntAndDoubleArgRetDouble(Binds.bezierTrackInterpolateBind, segment, trackIdx, time)
    }

    /**
     * Inserts an Audio Track key at the given `time` in seconds. The `track_idx` must be the index of
     * an Audio Track. `stream` is the `AudioStream` resource to play. `start_offset` is the number of
     * seconds cut off at the beginning of the audio stream, while `end_offset` is at the ending.
     *
     * Generated from Godot docs: Animation.audio_track_insert_key
     */
    fun audioTrackInsertKey(trackIdx: Int, time: Double, stream: Resource?, startOffset: Double = 0.0, endOffset: Double = 0.0): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntDoubleObjectTwoDoubleArgsRetInt(Binds.audioTrackInsertKeyBind, segment, trackIdx, time, stream?.requireOpenHandle() ?: NULL_SEGMENT, startOffset, endOffset)
    }

    /**
     * Sets the stream of the key identified by `key_idx` to value `stream`. The `track_idx` must be
     * the index of an Audio Track.
     *
     * Generated from Godot docs: Animation.audio_track_set_key_stream
     */
    fun audioTrackSetKeyStream(trackIdx: Int, keyIdx: Int, stream: Resource?) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndObjectArg(Binds.audioTrackSetKeyStreamBind, segment, trackIdx, keyIdx, stream?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Sets the start offset of the key identified by `key_idx` to value `offset`. The `track_idx` must
     * be the index of an Audio Track.
     *
     * Generated from Godot docs: Animation.audio_track_set_key_start_offset
     */
    fun audioTrackSetKeyStartOffset(trackIdx: Int, keyIdx: Int, offset: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.audioTrackSetKeyStartOffsetBind, segment, trackIdx, keyIdx, offset)
    }

    /**
     * Sets the end offset of the key identified by `key_idx` to value `offset`. The `track_idx` must
     * be the index of an Audio Track.
     *
     * Generated from Godot docs: Animation.audio_track_set_key_end_offset
     */
    fun audioTrackSetKeyEndOffset(trackIdx: Int, keyIdx: Int, offset: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(Binds.audioTrackSetKeyEndOffsetBind, segment, trackIdx, keyIdx, offset)
    }

    /**
     * Returns the audio stream of the key identified by `key_idx`. The `track_idx` must be the index
     * of an Audio Track.
     *
     * Generated from Godot docs: Animation.audio_track_get_key_stream
     */
    fun audioTrackGetKeyStream(trackIdx: Int, keyIdx: Int): Resource? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithTwoIntArgsRetObject(Binds.audioTrackGetKeyStreamBind, segment, trackIdx, keyIdx)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return Resource.wrapOwned(ret)
    }

    /**
     * Returns the start offset of the key identified by `key_idx`. The `track_idx` must be the index
     * of an Audio Track. Start offset is the number of seconds cut off at the beginning of the audio
     * stream.
     *
     * Generated from Godot docs: Animation.audio_track_get_key_start_offset
     */
    fun audioTrackGetKeyStartOffset(trackIdx: Int, keyIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.audioTrackGetKeyStartOffsetBind, segment, trackIdx, keyIdx)
    }

    /**
     * Returns the end offset of the key identified by `key_idx`. The `track_idx` must be the index of
     * an Audio Track. End offset is the number of seconds cut off at the ending of the audio stream.
     *
     * Generated from Godot docs: Animation.audio_track_get_key_end_offset
     */
    fun audioTrackGetKeyEndOffset(trackIdx: Int, keyIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.audioTrackGetKeyEndOffsetBind, segment, trackIdx, keyIdx)
    }

    /**
     * Sets whether the track will be blended with other animations. If `true`, the audio playback
     * volume changes depending on the blend value.
     *
     * Generated from Godot docs: Animation.audio_track_set_use_blend
     */
    fun audioTrackSetUseBlend(trackIdx: Int, enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.audioTrackSetUseBlendBind, segment, trackIdx, enable)
    }

    /**
     * Returns `true` if the track at `track_idx` will be blended with other animations.
     *
     * Generated from Godot docs: Animation.audio_track_is_use_blend
     */
    fun audioTrackIsUseBlend(trackIdx: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.audioTrackIsUseBlendBind, segment, trackIdx)
    }

    /**
     * Inserts a key with value `animation` at the given `time` (in seconds). The `track_idx` must be
     * the index of an Animation Track.
     *
     * Generated from Godot docs: Animation.animation_track_insert_key
     */
    fun animationTrackInsertKey(trackIdx: Int, time: Double, animation: String): Int {
        checkOpen()
        return ObjectCalls.ptrcallWithIntDoubleStringNameArgsRetInt(Binds.animationTrackInsertKeyBind, segment, trackIdx, time, animation)
    }

    /**
     * Sets the key identified by `key_idx` to value `animation`. The `track_idx` must be the index of
     * an Animation Track.
     *
     * Generated from Godot docs: Animation.animation_track_set_key_animation
     */
    fun animationTrackSetKeyAnimation(trackIdx: Int, keyIdx: Int, animation: String) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntAndStringNameArg(Binds.animationTrackSetKeyAnimationBind, segment, trackIdx, keyIdx, animation)
    }

    /**
     * Returns the animation name at the key identified by `key_idx`. The `track_idx` must be the index
     * of an Animation Track.
     *
     * Generated from Godot docs: Animation.animation_track_get_key_animation
     */
    fun animationTrackGetKeyAnimation(trackIdx: Int, keyIdx: Int): String {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetStringName(Binds.animationTrackGetKeyAnimationBind, segment, trackIdx, keyIdx)
    }

    /**
     * Adds a marker to this Animation.
     *
     * Generated from Godot docs: Animation.add_marker
     */
    fun addMarker(name: String, time: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndDoubleArg(Binds.addMarkerBind, segment, name, time)
    }

    /**
     * Removes the marker with the given name from this Animation.
     *
     * Generated from Godot docs: Animation.remove_marker
     */
    fun removeMarker(name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(Binds.removeMarkerBind, segment, name)
    }

    /**
     * Returns `true` if this Animation contains a marker with the given name.
     *
     * Generated from Godot docs: Animation.has_marker
     */
    fun hasMarker(name: String): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasMarkerBind, segment, name)
    }

    /**
     * Returns the name of the marker located at the given time.
     *
     * Generated from Godot docs: Animation.get_marker_at_time
     */
    fun getMarkerAtTime(time: Double): String {
        checkOpen()
        return ObjectCalls.ptrcallWithFloatArgRetStringName(Binds.getMarkerAtTimeBind, segment, time)
    }

    /**
     * Returns the closest marker that comes after the given time. If no such marker exists, an empty
     * string is returned.
     *
     * Generated from Godot docs: Animation.get_next_marker
     */
    fun getNextMarker(time: Double): String {
        checkOpen()
        return ObjectCalls.ptrcallWithFloatArgRetStringName(Binds.getNextMarkerBind, segment, time)
    }

    /**
     * Returns the closest marker that comes before the given time. If no such marker exists, an empty
     * string is returned.
     *
     * Generated from Godot docs: Animation.get_prev_marker
     */
    fun getPrevMarker(time: Double): String {
        checkOpen()
        return ObjectCalls.ptrcallWithFloatArgRetStringName(Binds.getPrevMarkerBind, segment, time)
    }

    /**
     * Returns the given marker's time.
     *
     * Generated from Godot docs: Animation.get_marker_time
     */
    fun getMarkerTime(name: String): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetDouble(Binds.getMarkerTimeBind, segment, name)
    }

    /**
     * Returns every marker in this Animation, sorted ascending by time.
     *
     * Generated from Godot docs: Animation.get_marker_names
     */
    fun getMarkerNames(): List<String> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getMarkerNamesBind, segment)
    }

    /**
     * Returns the given marker's color.
     *
     * Generated from Godot docs: Animation.get_marker_color
     */
    fun getMarkerColor(name: String): Color {
        checkOpen()
        return ObjectCalls.ptrcallWithStringNameArgRetColor(Binds.getMarkerColorBind, segment, name)
    }

    /**
     * Sets the given marker's color.
     *
     * Generated from Godot docs: Animation.set_marker_color
     */
    fun setMarkerColor(name: String, color: Color) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameAndColorArg(Binds.setMarkerColorBind, segment, name, color)
    }

    /**
     * The total length of the animation (in seconds). Note: Length is not delimited by the last key,
     * as this one may be before or after the end to ensure correct interpolation and looping.
     *
     * Generated from Godot docs: Animation.set_length
     */
    fun setLength(timeSec: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLengthBind, segment, timeSec)
    }

    /**
     * The total length of the animation (in seconds). Note: Length is not delimited by the last key,
     * as this one may be before or after the end to ensure correct interpolation and looping.
     *
     * Generated from Godot docs: Animation.get_length
     */
    fun getLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLengthBind, segment)
    }

    /**
     * Determines the behavior of both ends of the animation timeline during animation playback. This
     * indicates whether and how the animation should be restarted, and is also used to correctly
     * interpolate animation cycles.
     *
     * Generated from Godot docs: Animation.set_loop_mode
     */
    fun setLoopMode(loopMode: Animation.LoopMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setLoopModeBind, segment, loopMode.value)
    }

    /**
     * Determines the behavior of both ends of the animation timeline during animation playback. This
     * indicates whether and how the animation should be restarted, and is also used to correctly
     * interpolate animation cycles.
     *
     * Generated from Godot docs: Animation.get_loop_mode
     */
    fun getLoopMode(): Animation.LoopMode {
        checkOpen()
        return Animation.LoopMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getLoopModeBind, segment))
    }

    /**
     * The animation step value.
     *
     * Generated from Godot docs: Animation.set_step
     */
    fun setStep(sizeSec: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setStepBind, segment, sizeSec)
    }

    /**
     * The animation step value.
     *
     * Generated from Godot docs: Animation.get_step
     */
    fun getStep(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getStepBind, segment)
    }

    /**
     * Clear the animation (clear all tracks and reset all).
     *
     * Generated from Godot docs: Animation.clear
     */
    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(Binds.clearBind, segment)
    }

    /**
     * Adds a new track to `to_animation` that is a copy of the given track from this animation.
     *
     * Generated from Godot docs: Animation.copy_track
     */
    fun copyTrack(trackIdx: Int, toAnimation: Animation?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.copyTrackBind, segment, trackIdx, toAnimation?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Optimize the animation and all its tracks in-place. This will preserve only as many keys as are
     * necessary to keep the animation within the specified bounds.
     *
     * Generated from Godot docs: Animation.optimize
     */
    fun optimize(allowedVelocityErr: Double = 0.01, allowedAngularErr: Double = 0.01, precision: Int = 3) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoDoubleAndIntArgs(Binds.optimizeBind, segment, allowedVelocityErr, allowedAngularErr, precision)
    }

    /**
     * Compress the animation and all its tracks in-place. This will make `track_is_compressed` return
     * `true` once called on this `Animation`. Compressed tracks require less memory to be played, and
     * are designed to be used for complex 3D animations (such as cutscenes) imported from external 3D
     * software. Compression is lossy, but the difference is usually not noticeable in real world
     * conditions. Note: Compressed tracks have various limitations (such as not being editable from
     * the editor), so only use compressed animations if you actually need them.
     *
     * Generated from Godot docs: Animation.compress
     */
    fun compress(pageSize: Long = 8192L, fps: Long = 120L, splitTolerance: Double = 4.0) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoUInt32AndDoubleArg(Binds.compressBind, segment, pageSize, fps, splitTolerance)
    }

    /**
     * Returns `true` if the capture track is included. This is a cached readonly value for
     * performance.
     *
     * Generated from Godot docs: Animation.is_capture_included
     */
    fun isCaptureIncluded(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCaptureIncludedBind, segment)
    }

    /**
     * Godot's `Animation.TrackType` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Animation.TrackType.<NAME>`).
     *
     * Generated from Godot docs: Animation.TrackType
     */
    @JvmInline
    value class TrackType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Value tracks set values in node properties, but only those which can be interpolated. For 3D
             * position/rotation/scale, using the dedicated `TrackType.POSITION_3D`, `TrackType.ROTATION_3D`
             * and `TrackType.SCALE_3D` track types instead of `TrackType.VALUE` is recommended for performance
             * reasons.
             *
             * Generated from Godot docs: Animation.TYPE_VALUE
             */
            val VALUE: TrackType get() = TrackType(0L)
            /**
             * 3D position track (values are stored in `Vector3`s).
             *
             * Generated from Godot docs: Animation.TYPE_POSITION_3D
             */
            val POSITION_3D: TrackType get() = TrackType(1L)
            /**
             * 3D rotation track (values are stored in `Quaternion`s).
             *
             * Generated from Godot docs: Animation.TYPE_ROTATION_3D
             */
            val ROTATION_3D: TrackType get() = TrackType(2L)
            /**
             * 3D scale track (values are stored in `Vector3`s).
             *
             * Generated from Godot docs: Animation.TYPE_SCALE_3D
             */
            val SCALE_3D: TrackType get() = TrackType(3L)
            /**
             * Blend shape track.
             *
             * Generated from Godot docs: Animation.TYPE_BLEND_SHAPE
             */
            val BLEND_SHAPE: TrackType get() = TrackType(4L)
            /**
             * Method tracks call functions with given arguments per key.
             *
             * Generated from Godot docs: Animation.TYPE_METHOD
             */
            val METHOD: TrackType get() = TrackType(5L)
            /**
             * Bezier tracks are used to interpolate a value using custom curves. They can also be used to
             * animate sub-properties of vectors and colors (e.g. alpha value of a `Color`).
             *
             * Generated from Godot docs: Animation.TYPE_BEZIER
             */
            val BEZIER: TrackType get() = TrackType(6L)
            /**
             * Audio tracks are used to play an audio stream with either type of `AudioStreamPlayer`. The
             * stream can be trimmed and previewed in the animation.
             *
             * Generated from Godot docs: Animation.TYPE_AUDIO
             */
            val AUDIO: TrackType get() = TrackType(7L)
            /**
             * Animation tracks play animations in other `AnimationPlayer` nodes.
             *
             * Generated from Godot docs: Animation.TYPE_ANIMATION
             */
            val ANIMATION: TrackType get() = TrackType(8L)
        }
    }

    /**
     * Godot's `Animation.InterpolationType` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`Animation.InterpolationType.<NAME>`).
     *
     * Generated from Godot docs: Animation.InterpolationType
     */
    @JvmInline
    value class InterpolationType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No interpolation (nearest value).
             *
             * Generated from Godot docs: Animation.INTERPOLATION_NEAREST
             */
            val NEAREST: InterpolationType get() = InterpolationType(0L)
            /**
             * Linear interpolation.
             *
             * Generated from Godot docs: Animation.INTERPOLATION_LINEAR
             */
            val LINEAR: InterpolationType get() = InterpolationType(1L)
            /**
             * Cubic interpolation. This looks smoother than linear interpolation, but is more expensive to
             * interpolate. Stick to `InterpolationType.LINEAR` for complex 3D animations imported from
             * external software, even if it requires using a higher animation framerate in return.
             *
             * Generated from Godot docs: Animation.INTERPOLATION_CUBIC
             */
            val CUBIC: InterpolationType get() = InterpolationType(2L)
            /**
             * Linear interpolation with shortest path rotation. Note: The result value is always normalized
             * and may not match the key value.
             *
             * Generated from Godot docs: Animation.INTERPOLATION_LINEAR_ANGLE
             */
            val LINEAR_ANGLE: InterpolationType get() = InterpolationType(3L)
            /**
             * Cubic interpolation with shortest path rotation. Note: The result value is always normalized and
             * may not match the key value.
             *
             * Generated from Godot docs: Animation.INTERPOLATION_CUBIC_ANGLE
             */
            val CUBIC_ANGLE: InterpolationType get() = InterpolationType(4L)
        }
    }

    /**
     * Godot's `Animation.UpdateMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Animation.UpdateMode.<NAME>`).
     *
     * Generated from Godot docs: Animation.UpdateMode
     */
    @JvmInline
    value class UpdateMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Update between keyframes and hold the value.
             *
             * Generated from Godot docs: Animation.UPDATE_CONTINUOUS
             */
            val CONTINUOUS: UpdateMode get() = UpdateMode(0L)
            /**
             * Update at the keyframes.
             *
             * Generated from Godot docs: Animation.UPDATE_DISCRETE
             */
            val DISCRETE: UpdateMode get() = UpdateMode(1L)
            /**
             * Same as `UpdateMode.CONTINUOUS` but works as a flag to capture the value of the current object
             * and perform interpolation in some methods. See also `AnimationMixer.capture`,
             * `AnimationPlayer.playback_auto_capture`, and `AnimationPlayer.play_with_capture`.
             *
             * Generated from Godot docs: Animation.UPDATE_CAPTURE
             */
            val CAPTURE: UpdateMode get() = UpdateMode(2L)
        }
    }

    /**
     * Godot's `Animation.LoopMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Animation.LoopMode.<NAME>`).
     *
     * Generated from Godot docs: Animation.LoopMode
     */
    @JvmInline
    value class LoopMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * At both ends of the animation, the animation will stop playing.
             *
             * Generated from Godot docs: Animation.LOOP_NONE
             */
            val NONE: LoopMode get() = LoopMode(0L)
            /**
             * At both ends of the animation, the animation will be repeated without changing the playback
             * direction.
             *
             * Generated from Godot docs: Animation.LOOP_LINEAR
             */
            val LINEAR: LoopMode get() = LoopMode(1L)
            /**
             * Repeats playback and reverse playback at both ends of the animation.
             *
             * Generated from Godot docs: Animation.LOOP_PINGPONG
             */
            val PINGPONG: LoopMode get() = LoopMode(2L)
        }
    }

    /**
     * Godot's `Animation.LoopedFlag` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Animation.LoopedFlag.<NAME>`).
     *
     * Generated from Godot docs: Animation.LoopedFlag
     */
    @JvmInline
    value class LoopedFlag(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * This flag indicates that the animation proceeds without any looping.
             *
             * Generated from Godot docs: Animation.LOOPED_FLAG_NONE
             */
            val NONE: LoopedFlag get() = LoopedFlag(0L)
            /**
             * This flag indicates that the animation has reached the end of the animation and just after loop
             * processed.
             *
             * Generated from Godot docs: Animation.LOOPED_FLAG_END
             */
            val END: LoopedFlag get() = LoopedFlag(1L)
            /**
             * This flag indicates that the animation has reached the start of the animation and just after
             * loop processed.
             *
             * Generated from Godot docs: Animation.LOOPED_FLAG_START
             */
            val START: LoopedFlag get() = LoopedFlag(2L)
        }
    }

    /**
     * Godot's `Animation.FindMode` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`Animation.FindMode.<NAME>`).
     *
     * Generated from Godot docs: Animation.FindMode
     */
    @JvmInline
    value class FindMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Finds the nearest time key.
             *
             * Generated from Godot docs: Animation.FIND_MODE_NEAREST
             */
            val NEAREST: FindMode get() = FindMode(0L)
            /**
             * Finds only the key with approximating the time.
             *
             * Generated from Godot docs: Animation.FIND_MODE_APPROX
             */
            val APPROX: FindMode get() = FindMode(1L)
            /**
             * Finds only the key with matching the time.
             *
             * Generated from Godot docs: Animation.FIND_MODE_EXACT
             */
            val EXACT: FindMode get() = FindMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Animation? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): Animation? =
            if (handle.address() == 0L) null else RefCounted.owned(Animation(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): Animation? =
            if (handle.address() == 0L) null else Animation(GodotHandle(handle))
    }

    private object Binds {
        private const val ADD_TRACK_HASH = 3843682357L
        @JvmField
        val addTrackBind =
            ObjectCalls.getMethodBind("Animation", "add_track", ADD_TRACK_HASH)

        private const val REMOVE_TRACK_HASH = 1286410249L
        @JvmField
        val removeTrackBind =
            ObjectCalls.getMethodBind("Animation", "remove_track", REMOVE_TRACK_HASH)

        private const val GET_TRACK_COUNT_HASH = 3905245786L
        @JvmField
        val getTrackCountBind =
            ObjectCalls.getMethodBind("Animation", "get_track_count", GET_TRACK_COUNT_HASH)

        private const val TRACK_GET_TYPE_HASH = 3445944217L
        @JvmField
        val trackGetTypeBind =
            ObjectCalls.getMethodBind("Animation", "track_get_type", TRACK_GET_TYPE_HASH)

        private const val TRACK_GET_PATH_HASH = 408788394L
        @JvmField
        val trackGetPathBind =
            ObjectCalls.getMethodBind("Animation", "track_get_path", TRACK_GET_PATH_HASH)

        private const val TRACK_SET_PATH_HASH = 2761262315L
        @JvmField
        val trackSetPathBind =
            ObjectCalls.getMethodBind("Animation", "track_set_path", TRACK_SET_PATH_HASH)

        private const val FIND_TRACK_HASH = 245376003L
        @JvmField
        val findTrackBind =
            ObjectCalls.getMethodBind("Animation", "find_track", FIND_TRACK_HASH)

        private const val TRACK_MOVE_UP_HASH = 1286410249L
        @JvmField
        val trackMoveUpBind =
            ObjectCalls.getMethodBind("Animation", "track_move_up", TRACK_MOVE_UP_HASH)

        private const val TRACK_MOVE_DOWN_HASH = 1286410249L
        @JvmField
        val trackMoveDownBind =
            ObjectCalls.getMethodBind("Animation", "track_move_down", TRACK_MOVE_DOWN_HASH)

        private const val TRACK_MOVE_TO_HASH = 3937882851L
        @JvmField
        val trackMoveToBind =
            ObjectCalls.getMethodBind("Animation", "track_move_to", TRACK_MOVE_TO_HASH)

        private const val TRACK_SWAP_HASH = 3937882851L
        @JvmField
        val trackSwapBind =
            ObjectCalls.getMethodBind("Animation", "track_swap", TRACK_SWAP_HASH)

        private const val TRACK_SET_IMPORTED_HASH = 300928843L
        @JvmField
        val trackSetImportedBind =
            ObjectCalls.getMethodBind("Animation", "track_set_imported", TRACK_SET_IMPORTED_HASH)

        private const val TRACK_IS_IMPORTED_HASH = 1116898809L
        @JvmField
        val trackIsImportedBind =
            ObjectCalls.getMethodBind("Animation", "track_is_imported", TRACK_IS_IMPORTED_HASH)

        private const val TRACK_SET_ENABLED_HASH = 300928843L
        @JvmField
        val trackSetEnabledBind =
            ObjectCalls.getMethodBind("Animation", "track_set_enabled", TRACK_SET_ENABLED_HASH)

        private const val TRACK_IS_ENABLED_HASH = 1116898809L
        @JvmField
        val trackIsEnabledBind =
            ObjectCalls.getMethodBind("Animation", "track_is_enabled", TRACK_IS_ENABLED_HASH)

        private const val POSITION_TRACK_INSERT_KEY_HASH = 2540608232L
        @JvmField
        val positionTrackInsertKeyBind =
            ObjectCalls.getMethodBind("Animation", "position_track_insert_key", POSITION_TRACK_INSERT_KEY_HASH)

        private const val ROTATION_TRACK_INSERT_KEY_HASH = 4165004800L
        @JvmField
        val rotationTrackInsertKeyBind =
            ObjectCalls.getMethodBind("Animation", "rotation_track_insert_key", ROTATION_TRACK_INSERT_KEY_HASH)

        private const val SCALE_TRACK_INSERT_KEY_HASH = 2540608232L
        @JvmField
        val scaleTrackInsertKeyBind =
            ObjectCalls.getMethodBind("Animation", "scale_track_insert_key", SCALE_TRACK_INSERT_KEY_HASH)

        private const val BLEND_SHAPE_TRACK_INSERT_KEY_HASH = 1534913637L
        @JvmField
        val blendShapeTrackInsertKeyBind =
            ObjectCalls.getMethodBind("Animation", "blend_shape_track_insert_key", BLEND_SHAPE_TRACK_INSERT_KEY_HASH)

        private const val POSITION_TRACK_INTERPOLATE_HASH = 3530011197L
        @JvmField
        val positionTrackInterpolateBind =
            ObjectCalls.getMethodBind("Animation", "position_track_interpolate", POSITION_TRACK_INTERPOLATE_HASH)

        private const val ROTATION_TRACK_INTERPOLATE_HASH = 2915876792L
        @JvmField
        val rotationTrackInterpolateBind =
            ObjectCalls.getMethodBind("Animation", "rotation_track_interpolate", ROTATION_TRACK_INTERPOLATE_HASH)

        private const val SCALE_TRACK_INTERPOLATE_HASH = 3530011197L
        @JvmField
        val scaleTrackInterpolateBind =
            ObjectCalls.getMethodBind("Animation", "scale_track_interpolate", SCALE_TRACK_INTERPOLATE_HASH)

        private const val BLEND_SHAPE_TRACK_INTERPOLATE_HASH = 2482365182L
        @JvmField
        val blendShapeTrackInterpolateBind =
            ObjectCalls.getMethodBind("Animation", "blend_shape_track_interpolate", BLEND_SHAPE_TRACK_INTERPOLATE_HASH)

        private const val TRACK_INSERT_KEY_HASH = 808952278L
        @JvmField
        val trackInsertKeyBind =
            ObjectCalls.getMethodBind("Animation", "track_insert_key", TRACK_INSERT_KEY_HASH)

        private const val TRACK_REMOVE_KEY_HASH = 3937882851L
        @JvmField
        val trackRemoveKeyBind =
            ObjectCalls.getMethodBind("Animation", "track_remove_key", TRACK_REMOVE_KEY_HASH)

        private const val TRACK_REMOVE_KEY_AT_TIME_HASH = 1602489585L
        @JvmField
        val trackRemoveKeyAtTimeBind =
            ObjectCalls.getMethodBind("Animation", "track_remove_key_at_time", TRACK_REMOVE_KEY_AT_TIME_HASH)

        private const val TRACK_SET_KEY_VALUE_HASH = 2060538656L
        @JvmField
        val trackSetKeyValueBind =
            ObjectCalls.getMethodBind("Animation", "track_set_key_value", TRACK_SET_KEY_VALUE_HASH)

        private const val TRACK_SET_KEY_TRANSITION_HASH = 3506521499L
        @JvmField
        val trackSetKeyTransitionBind =
            ObjectCalls.getMethodBind("Animation", "track_set_key_transition", TRACK_SET_KEY_TRANSITION_HASH)

        private const val TRACK_SET_KEY_TIME_HASH = 3506521499L
        @JvmField
        val trackSetKeyTimeBind =
            ObjectCalls.getMethodBind("Animation", "track_set_key_time", TRACK_SET_KEY_TIME_HASH)

        private const val TRACK_GET_KEY_TRANSITION_HASH = 3085491603L
        @JvmField
        val trackGetKeyTransitionBind =
            ObjectCalls.getMethodBind("Animation", "track_get_key_transition", TRACK_GET_KEY_TRANSITION_HASH)

        private const val TRACK_GET_KEY_COUNT_HASH = 923996154L
        @JvmField
        val trackGetKeyCountBind =
            ObjectCalls.getMethodBind("Animation", "track_get_key_count", TRACK_GET_KEY_COUNT_HASH)

        private const val TRACK_GET_KEY_VALUE_HASH = 678354945L
        @JvmField
        val trackGetKeyValueBind =
            ObjectCalls.getMethodBind("Animation", "track_get_key_value", TRACK_GET_KEY_VALUE_HASH)

        private const val TRACK_GET_KEY_TIME_HASH = 3085491603L
        @JvmField
        val trackGetKeyTimeBind =
            ObjectCalls.getMethodBind("Animation", "track_get_key_time", TRACK_GET_KEY_TIME_HASH)

        private const val TRACK_FIND_KEY_HASH = 4230953007L
        @JvmField
        val trackFindKeyBind =
            ObjectCalls.getMethodBind("Animation", "track_find_key", TRACK_FIND_KEY_HASH)

        private const val TRACK_SET_INTERPOLATION_TYPE_HASH = 4112932513L
        @JvmField
        val trackSetInterpolationTypeBind =
            ObjectCalls.getMethodBind("Animation", "track_set_interpolation_type", TRACK_SET_INTERPOLATION_TYPE_HASH)

        private const val TRACK_GET_INTERPOLATION_TYPE_HASH = 1530756894L
        @JvmField
        val trackGetInterpolationTypeBind =
            ObjectCalls.getMethodBind("Animation", "track_get_interpolation_type", TRACK_GET_INTERPOLATION_TYPE_HASH)

        private const val TRACK_SET_INTERPOLATION_LOOP_WRAP_HASH = 300928843L
        @JvmField
        val trackSetInterpolationLoopWrapBind =
            ObjectCalls.getMethodBind("Animation", "track_set_interpolation_loop_wrap", TRACK_SET_INTERPOLATION_LOOP_WRAP_HASH)

        private const val TRACK_GET_INTERPOLATION_LOOP_WRAP_HASH = 1116898809L
        @JvmField
        val trackGetInterpolationLoopWrapBind =
            ObjectCalls.getMethodBind("Animation", "track_get_interpolation_loop_wrap", TRACK_GET_INTERPOLATION_LOOP_WRAP_HASH)

        private const val TRACK_IS_COMPRESSED_HASH = 1116898809L
        @JvmField
        val trackIsCompressedBind =
            ObjectCalls.getMethodBind("Animation", "track_is_compressed", TRACK_IS_COMPRESSED_HASH)

        private const val VALUE_TRACK_SET_UPDATE_MODE_HASH = 2854058312L
        @JvmField
        val valueTrackSetUpdateModeBind =
            ObjectCalls.getMethodBind("Animation", "value_track_set_update_mode", VALUE_TRACK_SET_UPDATE_MODE_HASH)

        private const val VALUE_TRACK_GET_UPDATE_MODE_HASH = 1440326473L
        @JvmField
        val valueTrackGetUpdateModeBind =
            ObjectCalls.getMethodBind("Animation", "value_track_get_update_mode", VALUE_TRACK_GET_UPDATE_MODE_HASH)

        private const val VALUE_TRACK_INTERPOLATE_HASH = 747269075L
        @JvmField
        val valueTrackInterpolateBind =
            ObjectCalls.getMethodBind("Animation", "value_track_interpolate", VALUE_TRACK_INTERPOLATE_HASH)

        private const val METHOD_TRACK_GET_NAME_HASH = 351665558L
        @JvmField
        val methodTrackGetNameBind =
            ObjectCalls.getMethodBind("Animation", "method_track_get_name", METHOD_TRACK_GET_NAME_HASH)

        private const val METHOD_TRACK_GET_PARAMS_HASH = 2345056839L
        @JvmField
        val methodTrackGetParamsBind =
            ObjectCalls.getMethodBind("Animation", "method_track_get_params", METHOD_TRACK_GET_PARAMS_HASH)

        private const val BEZIER_TRACK_INSERT_KEY_HASH = 3656773645L
        @JvmField
        val bezierTrackInsertKeyBind =
            ObjectCalls.getMethodBind("Animation", "bezier_track_insert_key", BEZIER_TRACK_INSERT_KEY_HASH)

        private const val BEZIER_TRACK_SET_KEY_VALUE_HASH = 3506521499L
        @JvmField
        val bezierTrackSetKeyValueBind =
            ObjectCalls.getMethodBind("Animation", "bezier_track_set_key_value", BEZIER_TRACK_SET_KEY_VALUE_HASH)

        private const val BEZIER_TRACK_SET_KEY_IN_HANDLE_HASH = 1719223284L
        @JvmField
        val bezierTrackSetKeyInHandleBind =
            ObjectCalls.getMethodBind("Animation", "bezier_track_set_key_in_handle", BEZIER_TRACK_SET_KEY_IN_HANDLE_HASH)

        private const val BEZIER_TRACK_SET_KEY_OUT_HANDLE_HASH = 1719223284L
        @JvmField
        val bezierTrackSetKeyOutHandleBind =
            ObjectCalls.getMethodBind("Animation", "bezier_track_set_key_out_handle", BEZIER_TRACK_SET_KEY_OUT_HANDLE_HASH)

        private const val BEZIER_TRACK_GET_KEY_VALUE_HASH = 3085491603L
        @JvmField
        val bezierTrackGetKeyValueBind =
            ObjectCalls.getMethodBind("Animation", "bezier_track_get_key_value", BEZIER_TRACK_GET_KEY_VALUE_HASH)

        private const val BEZIER_TRACK_GET_KEY_IN_HANDLE_HASH = 3016396712L
        @JvmField
        val bezierTrackGetKeyInHandleBind =
            ObjectCalls.getMethodBind("Animation", "bezier_track_get_key_in_handle", BEZIER_TRACK_GET_KEY_IN_HANDLE_HASH)

        private const val BEZIER_TRACK_GET_KEY_OUT_HANDLE_HASH = 3016396712L
        @JvmField
        val bezierTrackGetKeyOutHandleBind =
            ObjectCalls.getMethodBind("Animation", "bezier_track_get_key_out_handle", BEZIER_TRACK_GET_KEY_OUT_HANDLE_HASH)

        private const val BEZIER_TRACK_INTERPOLATE_HASH = 1900462983L
        @JvmField
        val bezierTrackInterpolateBind =
            ObjectCalls.getMethodBind("Animation", "bezier_track_interpolate", BEZIER_TRACK_INTERPOLATE_HASH)

        private const val AUDIO_TRACK_INSERT_KEY_HASH = 4021027286L
        @JvmField
        val audioTrackInsertKeyBind =
            ObjectCalls.getMethodBind("Animation", "audio_track_insert_key", AUDIO_TRACK_INSERT_KEY_HASH)

        private const val AUDIO_TRACK_SET_KEY_STREAM_HASH = 3886397084L
        @JvmField
        val audioTrackSetKeyStreamBind =
            ObjectCalls.getMethodBind("Animation", "audio_track_set_key_stream", AUDIO_TRACK_SET_KEY_STREAM_HASH)

        private const val AUDIO_TRACK_SET_KEY_START_OFFSET_HASH = 3506521499L
        @JvmField
        val audioTrackSetKeyStartOffsetBind =
            ObjectCalls.getMethodBind("Animation", "audio_track_set_key_start_offset", AUDIO_TRACK_SET_KEY_START_OFFSET_HASH)

        private const val AUDIO_TRACK_SET_KEY_END_OFFSET_HASH = 3506521499L
        @JvmField
        val audioTrackSetKeyEndOffsetBind =
            ObjectCalls.getMethodBind("Animation", "audio_track_set_key_end_offset", AUDIO_TRACK_SET_KEY_END_OFFSET_HASH)

        private const val AUDIO_TRACK_GET_KEY_STREAM_HASH = 635277205L
        @JvmField
        val audioTrackGetKeyStreamBind =
            ObjectCalls.getMethodBind("Animation", "audio_track_get_key_stream", AUDIO_TRACK_GET_KEY_STREAM_HASH)

        private const val AUDIO_TRACK_GET_KEY_START_OFFSET_HASH = 3085491603L
        @JvmField
        val audioTrackGetKeyStartOffsetBind =
            ObjectCalls.getMethodBind("Animation", "audio_track_get_key_start_offset", AUDIO_TRACK_GET_KEY_START_OFFSET_HASH)

        private const val AUDIO_TRACK_GET_KEY_END_OFFSET_HASH = 3085491603L
        @JvmField
        val audioTrackGetKeyEndOffsetBind =
            ObjectCalls.getMethodBind("Animation", "audio_track_get_key_end_offset", AUDIO_TRACK_GET_KEY_END_OFFSET_HASH)

        private const val AUDIO_TRACK_SET_USE_BLEND_HASH = 300928843L
        @JvmField
        val audioTrackSetUseBlendBind =
            ObjectCalls.getMethodBind("Animation", "audio_track_set_use_blend", AUDIO_TRACK_SET_USE_BLEND_HASH)

        private const val AUDIO_TRACK_IS_USE_BLEND_HASH = 1116898809L
        @JvmField
        val audioTrackIsUseBlendBind =
            ObjectCalls.getMethodBind("Animation", "audio_track_is_use_blend", AUDIO_TRACK_IS_USE_BLEND_HASH)

        private const val ANIMATION_TRACK_INSERT_KEY_HASH = 158676774L
        @JvmField
        val animationTrackInsertKeyBind =
            ObjectCalls.getMethodBind("Animation", "animation_track_insert_key", ANIMATION_TRACK_INSERT_KEY_HASH)

        private const val ANIMATION_TRACK_SET_KEY_ANIMATION_HASH = 117615382L
        @JvmField
        val animationTrackSetKeyAnimationBind =
            ObjectCalls.getMethodBind("Animation", "animation_track_set_key_animation", ANIMATION_TRACK_SET_KEY_ANIMATION_HASH)

        private const val ANIMATION_TRACK_GET_KEY_ANIMATION_HASH = 351665558L
        @JvmField
        val animationTrackGetKeyAnimationBind =
            ObjectCalls.getMethodBind("Animation", "animation_track_get_key_animation", ANIMATION_TRACK_GET_KEY_ANIMATION_HASH)

        private const val ADD_MARKER_HASH = 4135858297L
        @JvmField
        val addMarkerBind =
            ObjectCalls.getMethodBind("Animation", "add_marker", ADD_MARKER_HASH)

        private const val REMOVE_MARKER_HASH = 3304788590L
        @JvmField
        val removeMarkerBind =
            ObjectCalls.getMethodBind("Animation", "remove_marker", REMOVE_MARKER_HASH)

        private const val HAS_MARKER_HASH = 2619796661L
        @JvmField
        val hasMarkerBind =
            ObjectCalls.getMethodBind("Animation", "has_marker", HAS_MARKER_HASH)

        private const val GET_MARKER_AT_TIME_HASH = 4079494655L
        @JvmField
        val getMarkerAtTimeBind =
            ObjectCalls.getMethodBind("Animation", "get_marker_at_time", GET_MARKER_AT_TIME_HASH)

        private const val GET_NEXT_MARKER_HASH = 4079494655L
        @JvmField
        val getNextMarkerBind =
            ObjectCalls.getMethodBind("Animation", "get_next_marker", GET_NEXT_MARKER_HASH)

        private const val GET_PREV_MARKER_HASH = 4079494655L
        @JvmField
        val getPrevMarkerBind =
            ObjectCalls.getMethodBind("Animation", "get_prev_marker", GET_PREV_MARKER_HASH)

        private const val GET_MARKER_TIME_HASH = 2349060816L
        @JvmField
        val getMarkerTimeBind =
            ObjectCalls.getMethodBind("Animation", "get_marker_time", GET_MARKER_TIME_HASH)

        private const val GET_MARKER_NAMES_HASH = 1139954409L
        @JvmField
        val getMarkerNamesBind =
            ObjectCalls.getMethodBind("Animation", "get_marker_names", GET_MARKER_NAMES_HASH)

        private const val GET_MARKER_COLOR_HASH = 3742943038L
        @JvmField
        val getMarkerColorBind =
            ObjectCalls.getMethodBind("Animation", "get_marker_color", GET_MARKER_COLOR_HASH)

        private const val SET_MARKER_COLOR_HASH = 4260178595L
        @JvmField
        val setMarkerColorBind =
            ObjectCalls.getMethodBind("Animation", "set_marker_color", SET_MARKER_COLOR_HASH)

        private const val SET_LENGTH_HASH = 373806689L
        @JvmField
        val setLengthBind =
            ObjectCalls.getMethodBind("Animation", "set_length", SET_LENGTH_HASH)

        private const val GET_LENGTH_HASH = 1740695150L
        @JvmField
        val getLengthBind =
            ObjectCalls.getMethodBind("Animation", "get_length", GET_LENGTH_HASH)

        private const val SET_LOOP_MODE_HASH = 3155355575L
        @JvmField
        val setLoopModeBind =
            ObjectCalls.getMethodBind("Animation", "set_loop_mode", SET_LOOP_MODE_HASH)

        private const val GET_LOOP_MODE_HASH = 1988889481L
        @JvmField
        val getLoopModeBind =
            ObjectCalls.getMethodBind("Animation", "get_loop_mode", GET_LOOP_MODE_HASH)

        private const val SET_STEP_HASH = 373806689L
        @JvmField
        val setStepBind =
            ObjectCalls.getMethodBind("Animation", "set_step", SET_STEP_HASH)

        private const val GET_STEP_HASH = 1740695150L
        @JvmField
        val getStepBind =
            ObjectCalls.getMethodBind("Animation", "get_step", GET_STEP_HASH)

        private const val CLEAR_HASH = 3218959716L
        @JvmField
        val clearBind =
            ObjectCalls.getMethodBind("Animation", "clear", CLEAR_HASH)

        private const val COPY_TRACK_HASH = 148001024L
        @JvmField
        val copyTrackBind =
            ObjectCalls.getMethodBind("Animation", "copy_track", COPY_TRACK_HASH)

        private const val OPTIMIZE_HASH = 3303583852L
        @JvmField
        val optimizeBind =
            ObjectCalls.getMethodBind("Animation", "optimize", OPTIMIZE_HASH)

        private const val COMPRESS_HASH = 3608408117L
        @JvmField
        val compressBind =
            ObjectCalls.getMethodBind("Animation", "compress", COMPRESS_HASH)

        private const val IS_CAPTURE_INCLUDED_HASH = 36873697L
        @JvmField
        val isCaptureIncludedBind =
            ObjectCalls.getMethodBind("Animation", "is_capture_included", IS_CAPTURE_INCLUDED_HASH)
    }
}
