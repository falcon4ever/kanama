package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
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
class Animation(handle: MemorySegment) : Resource(handle) {
    var length: Double
        @JvmName("lengthProperty")
        get() = getLength()
        @JvmName("setLengthProperty")
        set(value) = setLength(value)

    var loopMode: Long
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

    fun addTrack(type: Long, atPosition: Int = -1): Int {
        return ObjectCalls.ptrcallWithLongAndIntArgsRetInt(addTrackBind, handle, type, atPosition)
    }

    fun removeTrack(trackIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(removeTrackBind, handle, trackIdx)
    }

    fun getTrackCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getTrackCountBind, handle)
    }

    fun trackGetType(trackIdx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(trackGetTypeBind, handle, trackIdx)
    }

    fun trackGetPath(trackIdx: Int): NodePath {
        return ObjectCalls.ptrcallWithIntArgRetNodePath(trackGetPathBind, handle, trackIdx)
    }

    fun trackSetPath(trackIdx: Int, path: NodePath) {
        ObjectCalls.ptrcallWithIntAndNodePathArg(trackSetPathBind, handle, trackIdx, path)
    }

    fun findTrack(path: NodePath, type: Long): Int {
        return ObjectCalls.ptrcallWithNodePathAndLongArgRetInt(findTrackBind, handle, path, type)
    }

    fun trackMoveUp(trackIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(trackMoveUpBind, handle, trackIdx)
    }

    fun trackMoveDown(trackIdx: Int) {
        ObjectCalls.ptrcallWithIntArg(trackMoveDownBind, handle, trackIdx)
    }

    fun trackMoveTo(trackIdx: Int, toIdx: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(trackMoveToBind, handle, trackIdx, toIdx)
    }

    fun trackSwap(trackIdx: Int, withIdx: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(trackSwapBind, handle, trackIdx, withIdx)
    }

    fun trackSetImported(trackIdx: Int, imported: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(trackSetImportedBind, handle, trackIdx, imported)
    }

    fun trackIsImported(trackIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(trackIsImportedBind, handle, trackIdx)
    }

    fun trackSetEnabled(trackIdx: Int, enabled: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(trackSetEnabledBind, handle, trackIdx, enabled)
    }

    fun trackIsEnabled(trackIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(trackIsEnabledBind, handle, trackIdx)
    }

    fun positionTrackInsertKey(trackIdx: Int, time: Double, position: Vector3): Int {
        return ObjectCalls.ptrcallWithIntDoubleVector3ArgsRetInt(positionTrackInsertKeyBind, handle, trackIdx, time, position)
    }

    fun rotationTrackInsertKey(trackIdx: Int, time: Double, rotation: Quaternion): Int {
        return ObjectCalls.ptrcallWithIntDoubleQuaternionArgsRetInt(rotationTrackInsertKeyBind, handle, trackIdx, time, rotation)
    }

    fun scaleTrackInsertKey(trackIdx: Int, time: Double, scale: Vector3): Int {
        return ObjectCalls.ptrcallWithIntDoubleVector3ArgsRetInt(scaleTrackInsertKeyBind, handle, trackIdx, time, scale)
    }

    fun blendShapeTrackInsertKey(trackIdx: Int, time: Double, amount: Double): Int {
        return ObjectCalls.ptrcallWithIntAndTwoDoubleArgsRetInt(blendShapeTrackInsertKeyBind, handle, trackIdx, time, amount)
    }

    fun positionTrackInterpolate(trackIdx: Int, timeSec: Double, backward: Boolean = false): Vector3 {
        return ObjectCalls.ptrcallWithIntDoubleBoolArgsRetVector3(positionTrackInterpolateBind, handle, trackIdx, timeSec, backward)
    }

    fun rotationTrackInterpolate(trackIdx: Int, timeSec: Double, backward: Boolean = false): Quaternion {
        return ObjectCalls.ptrcallWithIntDoubleBoolArgsRetQuaternion(rotationTrackInterpolateBind, handle, trackIdx, timeSec, backward)
    }

    fun scaleTrackInterpolate(trackIdx: Int, timeSec: Double, backward: Boolean = false): Vector3 {
        return ObjectCalls.ptrcallWithIntDoubleBoolArgsRetVector3(scaleTrackInterpolateBind, handle, trackIdx, timeSec, backward)
    }

    fun blendShapeTrackInterpolate(trackIdx: Int, timeSec: Double, backward: Boolean = false): Double {
        return ObjectCalls.ptrcallWithIntDoubleBoolArgsRetDouble(blendShapeTrackInterpolateBind, handle, trackIdx, timeSec, backward)
    }

    fun trackInsertKey(trackIdx: Int, time: Double, key: Any?, transition: Double = 1.0): Int {
        return ObjectCalls.ptrcallWithIntDoubleVariantDoubleArgsRetInt(trackInsertKeyBind, handle, trackIdx, time, key, transition)
    }

    fun trackRemoveKey(trackIdx: Int, keyIdx: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(trackRemoveKeyBind, handle, trackIdx, keyIdx)
    }

    fun trackRemoveKeyAtTime(trackIdx: Int, time: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(trackRemoveKeyAtTimeBind, handle, trackIdx, time)
    }

    fun trackSetKeyValue(trackIdx: Int, key: Int, value: Any?) {
        ObjectCalls.ptrcallWithTwoIntAndVariantArg(trackSetKeyValueBind, handle, trackIdx, key, value)
    }

    fun trackSetKeyTransition(trackIdx: Int, keyIdx: Int, transition: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(trackSetKeyTransitionBind, handle, trackIdx, keyIdx, transition)
    }

    fun trackSetKeyTime(trackIdx: Int, keyIdx: Int, time: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(trackSetKeyTimeBind, handle, trackIdx, keyIdx, time)
    }

    fun trackGetKeyTransition(trackIdx: Int, keyIdx: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(trackGetKeyTransitionBind, handle, trackIdx, keyIdx)
    }

    fun trackGetKeyCount(trackIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(trackGetKeyCountBind, handle, trackIdx)
    }

    fun trackGetKeyValue(trackIdx: Int, keyIdx: Int): Any? {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVariantScalar(trackGetKeyValueBind, handle, trackIdx, keyIdx)
    }

    fun trackGetKeyTime(trackIdx: Int, keyIdx: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(trackGetKeyTimeBind, handle, trackIdx, keyIdx)
    }

    fun trackFindKey(trackIdx: Int, time: Double, findMode: Long = 0L, limit: Boolean = false, backward: Boolean = false): Int {
        return ObjectCalls.ptrcallWithIntDoubleLongTwoBoolArgsRetInt(trackFindKeyBind, handle, trackIdx, time, findMode, limit, backward)
    }

    fun trackSetInterpolationType(trackIdx: Int, interpolation: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(trackSetInterpolationTypeBind, handle, trackIdx, interpolation)
    }

    fun trackGetInterpolationType(trackIdx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(trackGetInterpolationTypeBind, handle, trackIdx)
    }

    fun trackSetInterpolationLoopWrap(trackIdx: Int, interpolation: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(trackSetInterpolationLoopWrapBind, handle, trackIdx, interpolation)
    }

    fun trackGetInterpolationLoopWrap(trackIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(trackGetInterpolationLoopWrapBind, handle, trackIdx)
    }

    fun trackIsCompressed(trackIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(trackIsCompressedBind, handle, trackIdx)
    }

    fun valueTrackSetUpdateMode(trackIdx: Int, mode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(valueTrackSetUpdateModeBind, handle, trackIdx, mode)
    }

    fun valueTrackGetUpdateMode(trackIdx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(valueTrackGetUpdateModeBind, handle, trackIdx)
    }

    fun valueTrackInterpolate(trackIdx: Int, timeSec: Double, backward: Boolean = false): Any? {
        return ObjectCalls.ptrcallWithIntDoubleBoolArgsRetVariantScalar(valueTrackInterpolateBind, handle, trackIdx, timeSec, backward)
    }

    fun methodTrackGetName(trackIdx: Int, keyIdx: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetStringName(methodTrackGetNameBind, handle, trackIdx, keyIdx)
    }

    fun methodTrackGetParams(trackIdx: Int, keyIdx: Int): List<Any?> {
        return ObjectCalls.ptrcallWithTwoIntArgsRetArray(methodTrackGetParamsBind, handle, trackIdx, keyIdx)
    }

    fun bezierTrackInsertKey(trackIdx: Int, time: Double, value: Double, inHandle: Vector2 = Vector2(0f, 0f), outHandle: Vector2 = Vector2(0f, 0f)): Int {
        return ObjectCalls.ptrcallWithIntTwoDoubleTwoVector2ArgsRetInt(bezierTrackInsertKeyBind, handle, trackIdx, time, value, inHandle, outHandle)
    }

    fun bezierTrackSetKeyValue(trackIdx: Int, keyIdx: Int, value: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(bezierTrackSetKeyValueBind, handle, trackIdx, keyIdx, value)
    }

    fun bezierTrackSetKeyInHandle(trackIdx: Int, keyIdx: Int, inHandle: Vector2, balancedValueTimeRatio: Double = 1.0) {
        ObjectCalls.ptrcallWithTwoIntVector2DoubleArgs(bezierTrackSetKeyInHandleBind, handle, trackIdx, keyIdx, inHandle, balancedValueTimeRatio)
    }

    fun bezierTrackSetKeyOutHandle(trackIdx: Int, keyIdx: Int, outHandle: Vector2, balancedValueTimeRatio: Double = 1.0) {
        ObjectCalls.ptrcallWithTwoIntVector2DoubleArgs(bezierTrackSetKeyOutHandleBind, handle, trackIdx, keyIdx, outHandle, balancedValueTimeRatio)
    }

    fun bezierTrackGetKeyValue(trackIdx: Int, keyIdx: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(bezierTrackGetKeyValueBind, handle, trackIdx, keyIdx)
    }

    fun bezierTrackGetKeyInHandle(trackIdx: Int, keyIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector2(bezierTrackGetKeyInHandleBind, handle, trackIdx, keyIdx)
    }

    fun bezierTrackGetKeyOutHandle(trackIdx: Int, keyIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithTwoIntArgsRetVector2(bezierTrackGetKeyOutHandleBind, handle, trackIdx, keyIdx)
    }

    fun bezierTrackInterpolate(trackIdx: Int, time: Double): Double {
        return ObjectCalls.ptrcallWithIntAndDoubleArgRetDouble(bezierTrackInterpolateBind, handle, trackIdx, time)
    }

    fun audioTrackInsertKey(trackIdx: Int, time: Double, stream: Resource?, startOffset: Double = 0.0, endOffset: Double = 0.0): Int {
        return ObjectCalls.ptrcallWithIntDoubleObjectTwoDoubleArgsRetInt(audioTrackInsertKeyBind, handle, trackIdx, time, stream?.requireOpenHandle() ?: MemorySegment.NULL, startOffset, endOffset)
    }

    fun audioTrackSetKeyStream(trackIdx: Int, keyIdx: Int, stream: Resource?) {
        ObjectCalls.ptrcallWithTwoIntAndObjectArg(audioTrackSetKeyStreamBind, handle, trackIdx, keyIdx, stream?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun audioTrackSetKeyStartOffset(trackIdx: Int, keyIdx: Int, offset: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(audioTrackSetKeyStartOffsetBind, handle, trackIdx, keyIdx, offset)
    }

    fun audioTrackSetKeyEndOffset(trackIdx: Int, keyIdx: Int, offset: Double) {
        ObjectCalls.ptrcallWithTwoIntAndDoubleArgs(audioTrackSetKeyEndOffsetBind, handle, trackIdx, keyIdx, offset)
    }

    fun audioTrackGetKeyStream(trackIdx: Int, keyIdx: Int): Resource? {
        return Resource.wrap(ObjectCalls.ptrcallWithTwoIntArgsRetObject(audioTrackGetKeyStreamBind, handle, trackIdx, keyIdx))
    }

    fun audioTrackGetKeyStartOffset(trackIdx: Int, keyIdx: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(audioTrackGetKeyStartOffsetBind, handle, trackIdx, keyIdx)
    }

    fun audioTrackGetKeyEndOffset(trackIdx: Int, keyIdx: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(audioTrackGetKeyEndOffsetBind, handle, trackIdx, keyIdx)
    }

    fun audioTrackSetUseBlend(trackIdx: Int, enable: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(audioTrackSetUseBlendBind, handle, trackIdx, enable)
    }

    fun audioTrackIsUseBlend(trackIdx: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(audioTrackIsUseBlendBind, handle, trackIdx)
    }

    fun animationTrackInsertKey(trackIdx: Int, time: Double, animation: String): Int {
        return ObjectCalls.ptrcallWithIntDoubleStringNameArgsRetInt(animationTrackInsertKeyBind, handle, trackIdx, time, animation)
    }

    fun animationTrackSetKeyAnimation(trackIdx: Int, keyIdx: Int, animation: String) {
        ObjectCalls.ptrcallWithTwoIntAndStringNameArg(animationTrackSetKeyAnimationBind, handle, trackIdx, keyIdx, animation)
    }

    fun animationTrackGetKeyAnimation(trackIdx: Int, keyIdx: Int): String {
        return ObjectCalls.ptrcallWithTwoIntArgsRetStringName(animationTrackGetKeyAnimationBind, handle, trackIdx, keyIdx)
    }

    fun addMarker(name: String, time: Double) {
        ObjectCalls.ptrcallWithStringNameAndDoubleArg(addMarkerBind, handle, name, time)
    }

    fun removeMarker(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeMarkerBind, handle, name)
    }

    fun hasMarker(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasMarkerBind, handle, name)
    }

    fun getMarkerAtTime(time: Double): String {
        return ObjectCalls.ptrcallWithFloatArgRetStringName(getMarkerAtTimeBind, handle, time)
    }

    fun getNextMarker(time: Double): String {
        return ObjectCalls.ptrcallWithFloatArgRetStringName(getNextMarkerBind, handle, time)
    }

    fun getPrevMarker(time: Double): String {
        return ObjectCalls.ptrcallWithFloatArgRetStringName(getPrevMarkerBind, handle, time)
    }

    fun getMarkerTime(name: String): Double {
        return ObjectCalls.ptrcallWithStringNameArgRetDouble(getMarkerTimeBind, handle, name)
    }

    fun getMarkerNames(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getMarkerNamesBind, handle)
    }

    fun getMarkerColor(name: String): Color {
        return ObjectCalls.ptrcallWithStringNameArgRetColor(getMarkerColorBind, handle, name)
    }

    fun setMarkerColor(name: String, color: Color) {
        ObjectCalls.ptrcallWithStringNameAndColorArg(setMarkerColorBind, handle, name, color)
    }

    fun setLength(timeSec: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLengthBind, handle, timeSec)
    }

    fun getLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLengthBind, handle)
    }

    fun setLoopMode(loopMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setLoopModeBind, handle, loopMode)
    }

    fun getLoopMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLoopModeBind, handle)
    }

    fun setStep(sizeSec: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStepBind, handle, sizeSec)
    }

    fun getStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStepBind, handle)
    }

    /**
     * Clear the animation (clear all tracks and reset all).
     *
     * Generated from Godot docs: Animation.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun copyTrack(trackIdx: Int, toAnimation: Animation?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(copyTrackBind, handle, trackIdx, toAnimation?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Optimize the animation and all its tracks in-place. This will preserve only as many keys as are
     * necessary to keep the animation within the specified bounds.
     *
     * Generated from Godot docs: Animation.optimize
     */
    fun optimize(allowedVelocityErr: Double = 0.01, allowedAngularErr: Double = 0.01, precision: Int = 3) {
        ObjectCalls.ptrcallWithTwoDoubleAndIntArgs(optimizeBind, handle, allowedVelocityErr, allowedAngularErr, precision)
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
        ObjectCalls.ptrcallWithTwoUInt32AndDoubleArg(compressBind, handle, pageSize, fps, splitTolerance)
    }

    fun isCaptureIncluded(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCaptureIncludedBind, handle)
    }

    companion object {
        const val TYPE_VALUE: Long = 0L
        const val TYPE_POSITION_3D: Long = 1L
        const val TYPE_ROTATION_3D: Long = 2L
        const val TYPE_SCALE_3D: Long = 3L
        const val TYPE_BLEND_SHAPE: Long = 4L
        const val TYPE_METHOD: Long = 5L
        const val TYPE_BEZIER: Long = 6L
        const val TYPE_AUDIO: Long = 7L
        const val TYPE_ANIMATION: Long = 8L
        const val INTERPOLATION_NEAREST: Long = 0L
        const val INTERPOLATION_LINEAR: Long = 1L
        const val INTERPOLATION_CUBIC: Long = 2L
        const val INTERPOLATION_LINEAR_ANGLE: Long = 3L
        const val INTERPOLATION_CUBIC_ANGLE: Long = 4L
        const val UPDATE_CONTINUOUS: Long = 0L
        const val UPDATE_DISCRETE: Long = 1L
        const val UPDATE_CAPTURE: Long = 2L
        const val LOOP_NONE: Long = 0L
        const val LOOP_LINEAR: Long = 1L
        const val LOOP_PINGPONG: Long = 2L
        const val LOOPED_FLAG_NONE: Long = 0L
        const val LOOPED_FLAG_END: Long = 1L
        const val LOOPED_FLAG_START: Long = 2L
        const val FIND_MODE_NEAREST: Long = 0L
        const val FIND_MODE_APPROX: Long = 1L
        const val FIND_MODE_EXACT: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Animation? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Animation? =
            if (handle.address() == 0L) null else Animation(handle)

        private const val ADD_TRACK_HASH = 3843682357L
        private val addTrackBind by lazy {
            ObjectCalls.getMethodBind("Animation", "add_track", ADD_TRACK_HASH)
        }

        private const val REMOVE_TRACK_HASH = 1286410249L
        private val removeTrackBind by lazy {
            ObjectCalls.getMethodBind("Animation", "remove_track", REMOVE_TRACK_HASH)
        }

        private const val GET_TRACK_COUNT_HASH = 3905245786L
        private val getTrackCountBind by lazy {
            ObjectCalls.getMethodBind("Animation", "get_track_count", GET_TRACK_COUNT_HASH)
        }

        private const val TRACK_GET_TYPE_HASH = 3445944217L
        private val trackGetTypeBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_get_type", TRACK_GET_TYPE_HASH)
        }

        private const val TRACK_GET_PATH_HASH = 408788394L
        private val trackGetPathBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_get_path", TRACK_GET_PATH_HASH)
        }

        private const val TRACK_SET_PATH_HASH = 2761262315L
        private val trackSetPathBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_set_path", TRACK_SET_PATH_HASH)
        }

        private const val FIND_TRACK_HASH = 245376003L
        private val findTrackBind by lazy {
            ObjectCalls.getMethodBind("Animation", "find_track", FIND_TRACK_HASH)
        }

        private const val TRACK_MOVE_UP_HASH = 1286410249L
        private val trackMoveUpBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_move_up", TRACK_MOVE_UP_HASH)
        }

        private const val TRACK_MOVE_DOWN_HASH = 1286410249L
        private val trackMoveDownBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_move_down", TRACK_MOVE_DOWN_HASH)
        }

        private const val TRACK_MOVE_TO_HASH = 3937882851L
        private val trackMoveToBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_move_to", TRACK_MOVE_TO_HASH)
        }

        private const val TRACK_SWAP_HASH = 3937882851L
        private val trackSwapBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_swap", TRACK_SWAP_HASH)
        }

        private const val TRACK_SET_IMPORTED_HASH = 300928843L
        private val trackSetImportedBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_set_imported", TRACK_SET_IMPORTED_HASH)
        }

        private const val TRACK_IS_IMPORTED_HASH = 1116898809L
        private val trackIsImportedBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_is_imported", TRACK_IS_IMPORTED_HASH)
        }

        private const val TRACK_SET_ENABLED_HASH = 300928843L
        private val trackSetEnabledBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_set_enabled", TRACK_SET_ENABLED_HASH)
        }

        private const val TRACK_IS_ENABLED_HASH = 1116898809L
        private val trackIsEnabledBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_is_enabled", TRACK_IS_ENABLED_HASH)
        }

        private const val POSITION_TRACK_INSERT_KEY_HASH = 2540608232L
        private val positionTrackInsertKeyBind by lazy {
            ObjectCalls.getMethodBind("Animation", "position_track_insert_key", POSITION_TRACK_INSERT_KEY_HASH)
        }

        private const val ROTATION_TRACK_INSERT_KEY_HASH = 4165004800L
        private val rotationTrackInsertKeyBind by lazy {
            ObjectCalls.getMethodBind("Animation", "rotation_track_insert_key", ROTATION_TRACK_INSERT_KEY_HASH)
        }

        private const val SCALE_TRACK_INSERT_KEY_HASH = 2540608232L
        private val scaleTrackInsertKeyBind by lazy {
            ObjectCalls.getMethodBind("Animation", "scale_track_insert_key", SCALE_TRACK_INSERT_KEY_HASH)
        }

        private const val BLEND_SHAPE_TRACK_INSERT_KEY_HASH = 1534913637L
        private val blendShapeTrackInsertKeyBind by lazy {
            ObjectCalls.getMethodBind("Animation", "blend_shape_track_insert_key", BLEND_SHAPE_TRACK_INSERT_KEY_HASH)
        }

        private const val POSITION_TRACK_INTERPOLATE_HASH = 3530011197L
        private val positionTrackInterpolateBind by lazy {
            ObjectCalls.getMethodBind("Animation", "position_track_interpolate", POSITION_TRACK_INTERPOLATE_HASH)
        }

        private const val ROTATION_TRACK_INTERPOLATE_HASH = 2915876792L
        private val rotationTrackInterpolateBind by lazy {
            ObjectCalls.getMethodBind("Animation", "rotation_track_interpolate", ROTATION_TRACK_INTERPOLATE_HASH)
        }

        private const val SCALE_TRACK_INTERPOLATE_HASH = 3530011197L
        private val scaleTrackInterpolateBind by lazy {
            ObjectCalls.getMethodBind("Animation", "scale_track_interpolate", SCALE_TRACK_INTERPOLATE_HASH)
        }

        private const val BLEND_SHAPE_TRACK_INTERPOLATE_HASH = 2482365182L
        private val blendShapeTrackInterpolateBind by lazy {
            ObjectCalls.getMethodBind("Animation", "blend_shape_track_interpolate", BLEND_SHAPE_TRACK_INTERPOLATE_HASH)
        }

        private const val TRACK_INSERT_KEY_HASH = 808952278L
        private val trackInsertKeyBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_insert_key", TRACK_INSERT_KEY_HASH)
        }

        private const val TRACK_REMOVE_KEY_HASH = 3937882851L
        private val trackRemoveKeyBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_remove_key", TRACK_REMOVE_KEY_HASH)
        }

        private const val TRACK_REMOVE_KEY_AT_TIME_HASH = 1602489585L
        private val trackRemoveKeyAtTimeBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_remove_key_at_time", TRACK_REMOVE_KEY_AT_TIME_HASH)
        }

        private const val TRACK_SET_KEY_VALUE_HASH = 2060538656L
        private val trackSetKeyValueBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_set_key_value", TRACK_SET_KEY_VALUE_HASH)
        }

        private const val TRACK_SET_KEY_TRANSITION_HASH = 3506521499L
        private val trackSetKeyTransitionBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_set_key_transition", TRACK_SET_KEY_TRANSITION_HASH)
        }

        private const val TRACK_SET_KEY_TIME_HASH = 3506521499L
        private val trackSetKeyTimeBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_set_key_time", TRACK_SET_KEY_TIME_HASH)
        }

        private const val TRACK_GET_KEY_TRANSITION_HASH = 3085491603L
        private val trackGetKeyTransitionBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_get_key_transition", TRACK_GET_KEY_TRANSITION_HASH)
        }

        private const val TRACK_GET_KEY_COUNT_HASH = 923996154L
        private val trackGetKeyCountBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_get_key_count", TRACK_GET_KEY_COUNT_HASH)
        }

        private const val TRACK_GET_KEY_VALUE_HASH = 678354945L
        private val trackGetKeyValueBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_get_key_value", TRACK_GET_KEY_VALUE_HASH)
        }

        private const val TRACK_GET_KEY_TIME_HASH = 3085491603L
        private val trackGetKeyTimeBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_get_key_time", TRACK_GET_KEY_TIME_HASH)
        }

        private const val TRACK_FIND_KEY_HASH = 4230953007L
        private val trackFindKeyBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_find_key", TRACK_FIND_KEY_HASH)
        }

        private const val TRACK_SET_INTERPOLATION_TYPE_HASH = 4112932513L
        private val trackSetInterpolationTypeBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_set_interpolation_type", TRACK_SET_INTERPOLATION_TYPE_HASH)
        }

        private const val TRACK_GET_INTERPOLATION_TYPE_HASH = 1530756894L
        private val trackGetInterpolationTypeBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_get_interpolation_type", TRACK_GET_INTERPOLATION_TYPE_HASH)
        }

        private const val TRACK_SET_INTERPOLATION_LOOP_WRAP_HASH = 300928843L
        private val trackSetInterpolationLoopWrapBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_set_interpolation_loop_wrap", TRACK_SET_INTERPOLATION_LOOP_WRAP_HASH)
        }

        private const val TRACK_GET_INTERPOLATION_LOOP_WRAP_HASH = 1116898809L
        private val trackGetInterpolationLoopWrapBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_get_interpolation_loop_wrap", TRACK_GET_INTERPOLATION_LOOP_WRAP_HASH)
        }

        private const val TRACK_IS_COMPRESSED_HASH = 1116898809L
        private val trackIsCompressedBind by lazy {
            ObjectCalls.getMethodBind("Animation", "track_is_compressed", TRACK_IS_COMPRESSED_HASH)
        }

        private const val VALUE_TRACK_SET_UPDATE_MODE_HASH = 2854058312L
        private val valueTrackSetUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("Animation", "value_track_set_update_mode", VALUE_TRACK_SET_UPDATE_MODE_HASH)
        }

        private const val VALUE_TRACK_GET_UPDATE_MODE_HASH = 1440326473L
        private val valueTrackGetUpdateModeBind by lazy {
            ObjectCalls.getMethodBind("Animation", "value_track_get_update_mode", VALUE_TRACK_GET_UPDATE_MODE_HASH)
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

        private const val BEZIER_TRACK_INSERT_KEY_HASH = 3656773645L
        private val bezierTrackInsertKeyBind by lazy {
            ObjectCalls.getMethodBind("Animation", "bezier_track_insert_key", BEZIER_TRACK_INSERT_KEY_HASH)
        }

        private const val BEZIER_TRACK_SET_KEY_VALUE_HASH = 3506521499L
        private val bezierTrackSetKeyValueBind by lazy {
            ObjectCalls.getMethodBind("Animation", "bezier_track_set_key_value", BEZIER_TRACK_SET_KEY_VALUE_HASH)
        }

        private const val BEZIER_TRACK_SET_KEY_IN_HANDLE_HASH = 1719223284L
        private val bezierTrackSetKeyInHandleBind by lazy {
            ObjectCalls.getMethodBind("Animation", "bezier_track_set_key_in_handle", BEZIER_TRACK_SET_KEY_IN_HANDLE_HASH)
        }

        private const val BEZIER_TRACK_SET_KEY_OUT_HANDLE_HASH = 1719223284L
        private val bezierTrackSetKeyOutHandleBind by lazy {
            ObjectCalls.getMethodBind("Animation", "bezier_track_set_key_out_handle", BEZIER_TRACK_SET_KEY_OUT_HANDLE_HASH)
        }

        private const val BEZIER_TRACK_GET_KEY_VALUE_HASH = 3085491603L
        private val bezierTrackGetKeyValueBind by lazy {
            ObjectCalls.getMethodBind("Animation", "bezier_track_get_key_value", BEZIER_TRACK_GET_KEY_VALUE_HASH)
        }

        private const val BEZIER_TRACK_GET_KEY_IN_HANDLE_HASH = 3016396712L
        private val bezierTrackGetKeyInHandleBind by lazy {
            ObjectCalls.getMethodBind("Animation", "bezier_track_get_key_in_handle", BEZIER_TRACK_GET_KEY_IN_HANDLE_HASH)
        }

        private const val BEZIER_TRACK_GET_KEY_OUT_HANDLE_HASH = 3016396712L
        private val bezierTrackGetKeyOutHandleBind by lazy {
            ObjectCalls.getMethodBind("Animation", "bezier_track_get_key_out_handle", BEZIER_TRACK_GET_KEY_OUT_HANDLE_HASH)
        }

        private const val BEZIER_TRACK_INTERPOLATE_HASH = 1900462983L
        private val bezierTrackInterpolateBind by lazy {
            ObjectCalls.getMethodBind("Animation", "bezier_track_interpolate", BEZIER_TRACK_INTERPOLATE_HASH)
        }

        private const val AUDIO_TRACK_INSERT_KEY_HASH = 4021027286L
        private val audioTrackInsertKeyBind by lazy {
            ObjectCalls.getMethodBind("Animation", "audio_track_insert_key", AUDIO_TRACK_INSERT_KEY_HASH)
        }

        private const val AUDIO_TRACK_SET_KEY_STREAM_HASH = 3886397084L
        private val audioTrackSetKeyStreamBind by lazy {
            ObjectCalls.getMethodBind("Animation", "audio_track_set_key_stream", AUDIO_TRACK_SET_KEY_STREAM_HASH)
        }

        private const val AUDIO_TRACK_SET_KEY_START_OFFSET_HASH = 3506521499L
        private val audioTrackSetKeyStartOffsetBind by lazy {
            ObjectCalls.getMethodBind("Animation", "audio_track_set_key_start_offset", AUDIO_TRACK_SET_KEY_START_OFFSET_HASH)
        }

        private const val AUDIO_TRACK_SET_KEY_END_OFFSET_HASH = 3506521499L
        private val audioTrackSetKeyEndOffsetBind by lazy {
            ObjectCalls.getMethodBind("Animation", "audio_track_set_key_end_offset", AUDIO_TRACK_SET_KEY_END_OFFSET_HASH)
        }

        private const val AUDIO_TRACK_GET_KEY_STREAM_HASH = 635277205L
        private val audioTrackGetKeyStreamBind by lazy {
            ObjectCalls.getMethodBind("Animation", "audio_track_get_key_stream", AUDIO_TRACK_GET_KEY_STREAM_HASH)
        }

        private const val AUDIO_TRACK_GET_KEY_START_OFFSET_HASH = 3085491603L
        private val audioTrackGetKeyStartOffsetBind by lazy {
            ObjectCalls.getMethodBind("Animation", "audio_track_get_key_start_offset", AUDIO_TRACK_GET_KEY_START_OFFSET_HASH)
        }

        private const val AUDIO_TRACK_GET_KEY_END_OFFSET_HASH = 3085491603L
        private val audioTrackGetKeyEndOffsetBind by lazy {
            ObjectCalls.getMethodBind("Animation", "audio_track_get_key_end_offset", AUDIO_TRACK_GET_KEY_END_OFFSET_HASH)
        }

        private const val AUDIO_TRACK_SET_USE_BLEND_HASH = 300928843L
        private val audioTrackSetUseBlendBind by lazy {
            ObjectCalls.getMethodBind("Animation", "audio_track_set_use_blend", AUDIO_TRACK_SET_USE_BLEND_HASH)
        }

        private const val AUDIO_TRACK_IS_USE_BLEND_HASH = 1116898809L
        private val audioTrackIsUseBlendBind by lazy {
            ObjectCalls.getMethodBind("Animation", "audio_track_is_use_blend", AUDIO_TRACK_IS_USE_BLEND_HASH)
        }

        private const val ANIMATION_TRACK_INSERT_KEY_HASH = 158676774L
        private val animationTrackInsertKeyBind by lazy {
            ObjectCalls.getMethodBind("Animation", "animation_track_insert_key", ANIMATION_TRACK_INSERT_KEY_HASH)
        }

        private const val ANIMATION_TRACK_SET_KEY_ANIMATION_HASH = 117615382L
        private val animationTrackSetKeyAnimationBind by lazy {
            ObjectCalls.getMethodBind("Animation", "animation_track_set_key_animation", ANIMATION_TRACK_SET_KEY_ANIMATION_HASH)
        }

        private const val ANIMATION_TRACK_GET_KEY_ANIMATION_HASH = 351665558L
        private val animationTrackGetKeyAnimationBind by lazy {
            ObjectCalls.getMethodBind("Animation", "animation_track_get_key_animation", ANIMATION_TRACK_GET_KEY_ANIMATION_HASH)
        }

        private const val ADD_MARKER_HASH = 4135858297L
        private val addMarkerBind by lazy {
            ObjectCalls.getMethodBind("Animation", "add_marker", ADD_MARKER_HASH)
        }

        private const val REMOVE_MARKER_HASH = 3304788590L
        private val removeMarkerBind by lazy {
            ObjectCalls.getMethodBind("Animation", "remove_marker", REMOVE_MARKER_HASH)
        }

        private const val HAS_MARKER_HASH = 2619796661L
        private val hasMarkerBind by lazy {
            ObjectCalls.getMethodBind("Animation", "has_marker", HAS_MARKER_HASH)
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

        private const val GET_MARKER_TIME_HASH = 2349060816L
        private val getMarkerTimeBind by lazy {
            ObjectCalls.getMethodBind("Animation", "get_marker_time", GET_MARKER_TIME_HASH)
        }

        private const val GET_MARKER_NAMES_HASH = 1139954409L
        private val getMarkerNamesBind by lazy {
            ObjectCalls.getMethodBind("Animation", "get_marker_names", GET_MARKER_NAMES_HASH)
        }

        private const val GET_MARKER_COLOR_HASH = 3742943038L
        private val getMarkerColorBind by lazy {
            ObjectCalls.getMethodBind("Animation", "get_marker_color", GET_MARKER_COLOR_HASH)
        }

        private const val SET_MARKER_COLOR_HASH = 4260178595L
        private val setMarkerColorBind by lazy {
            ObjectCalls.getMethodBind("Animation", "set_marker_color", SET_MARKER_COLOR_HASH)
        }

        private const val SET_LENGTH_HASH = 373806689L
        private val setLengthBind by lazy {
            ObjectCalls.getMethodBind("Animation", "set_length", SET_LENGTH_HASH)
        }

        private const val GET_LENGTH_HASH = 1740695150L
        private val getLengthBind by lazy {
            ObjectCalls.getMethodBind("Animation", "get_length", GET_LENGTH_HASH)
        }

        private const val SET_LOOP_MODE_HASH = 3155355575L
        private val setLoopModeBind by lazy {
            ObjectCalls.getMethodBind("Animation", "set_loop_mode", SET_LOOP_MODE_HASH)
        }

        private const val GET_LOOP_MODE_HASH = 1988889481L
        private val getLoopModeBind by lazy {
            ObjectCalls.getMethodBind("Animation", "get_loop_mode", GET_LOOP_MODE_HASH)
        }

        private const val SET_STEP_HASH = 373806689L
        private val setStepBind by lazy {
            ObjectCalls.getMethodBind("Animation", "set_step", SET_STEP_HASH)
        }

        private const val GET_STEP_HASH = 1740695150L
        private val getStepBind by lazy {
            ObjectCalls.getMethodBind("Animation", "get_step", GET_STEP_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("Animation", "clear", CLEAR_HASH)
        }

        private const val COPY_TRACK_HASH = 148001024L
        private val copyTrackBind by lazy {
            ObjectCalls.getMethodBind("Animation", "copy_track", COPY_TRACK_HASH)
        }

        private const val OPTIMIZE_HASH = 3303583852L
        private val optimizeBind by lazy {
            ObjectCalls.getMethodBind("Animation", "optimize", OPTIMIZE_HASH)
        }

        private const val COMPRESS_HASH = 3608408117L
        private val compressBind by lazy {
            ObjectCalls.getMethodBind("Animation", "compress", COMPRESS_HASH)
        }

        private const val IS_CAPTURE_INCLUDED_HASH = 36873697L
        private val isCaptureIncludedBind by lazy {
            ObjectCalls.getMethodBind("Animation", "is_capture_included", IS_CAPTURE_INCLUDED_HASH)
        }
    }
}
