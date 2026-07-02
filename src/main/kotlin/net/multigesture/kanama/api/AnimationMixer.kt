package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Vector3

/**
 * Base class for `AnimationPlayer` and `AnimationTree`.
 *
 * Generated from Godot docs: AnimationMixer
 */
open class AnimationMixer(handle: MemorySegment) : Node(handle) {
    var active: Boolean
        @JvmName("activeProperty")
        get() = isActive()
        @JvmName("setActiveProperty")
        set(value) = setActive(value)

    var deterministic: Boolean
        @JvmName("deterministicProperty")
        get() = isDeterministic()
        @JvmName("setDeterministicProperty")
        set(value) = setDeterministic(value)

    var resetOnSave: Boolean
        @JvmName("resetOnSaveProperty")
        get() = isResetOnSaveEnabled()
        @JvmName("setResetOnSaveProperty")
        set(value) = setResetOnSaveEnabled(value)

    var rootNode: NodePath
        @JvmName("rootNodeProperty")
        get() = getRootNode()
        @JvmName("setRootNodeProperty")
        set(value) = setRootNode(value)

    var rootMotionTrack: NodePath
        @JvmName("rootMotionTrackProperty")
        get() = getRootMotionTrack()
        @JvmName("setRootMotionTrackProperty")
        set(value) = setRootMotionTrack(value)

    var rootMotionLocal: Boolean
        @JvmName("rootMotionLocalProperty")
        get() = isRootMotionLocal()
        @JvmName("setRootMotionLocalProperty")
        set(value) = setRootMotionLocal(value)

    var audioMaxPolyphony: Int
        @JvmName("audioMaxPolyphonyProperty")
        get() = getAudioMaxPolyphony()
        @JvmName("setAudioMaxPolyphonyProperty")
        set(value) = setAudioMaxPolyphony(value)

    var callbackModeProcess: Long
        @JvmName("callbackModeProcessProperty")
        get() = getCallbackModeProcess()
        @JvmName("setCallbackModeProcessProperty")
        set(value) = setCallbackModeProcess(value)

    var callbackModeMethod: Long
        @JvmName("callbackModeMethodProperty")
        get() = getCallbackModeMethod()
        @JvmName("setCallbackModeMethodProperty")
        set(value) = setCallbackModeMethod(value)

    var callbackModeDiscrete: Long
        @JvmName("callbackModeDiscreteProperty")
        get() = getCallbackModeDiscrete()
        @JvmName("setCallbackModeDiscreteProperty")
        set(value) = setCallbackModeDiscrete(value)

    fun addAnimationLibrary(name: String, library: AnimationLibrary?): Long {
        return ObjectCalls.ptrcallWithStringNameAndObjectArgRetLong(addAnimationLibraryBind, handle, name, library?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun removeAnimationLibrary(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeAnimationLibraryBind, handle, name)
    }

    fun renameAnimationLibrary(name: String, newname: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(renameAnimationLibraryBind, handle, name, newname)
    }

    fun hasAnimationLibrary(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasAnimationLibraryBind, handle, name)
    }

    fun getAnimationLibrary(name: String): AnimationLibrary? {
        return AnimationLibrary.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getAnimationLibraryBind, handle, name))
    }

    fun getAnimationLibraryList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getAnimationLibraryListBind, handle)
    }

    fun hasAnimation(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasAnimationBind, handle, name)
    }

    fun getAnimation(name: String): Animation? {
        return Animation.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getAnimationBind, handle, name))
    }

    fun getAnimationList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getAnimationListBind, handle)
    }

    fun setActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setActiveBind, handle, active)
    }

    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isActiveBind, handle)
    }

    fun setDeterministic(deterministic: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDeterministicBind, handle, deterministic)
    }

    fun isDeterministic(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDeterministicBind, handle)
    }

    fun setRootNode(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setRootNodeBind, handle, path)
    }

    fun getRootNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getRootNodeBind, handle)
    }

    fun setCallbackModeProcess(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCallbackModeProcessBind, handle, mode)
    }

    fun getCallbackModeProcess(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCallbackModeProcessBind, handle)
    }

    fun setCallbackModeMethod(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCallbackModeMethodBind, handle, mode)
    }

    fun getCallbackModeMethod(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCallbackModeMethodBind, handle)
    }

    fun setCallbackModeDiscrete(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCallbackModeDiscreteBind, handle, mode)
    }

    fun getCallbackModeDiscrete(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCallbackModeDiscreteBind, handle)
    }

    fun setAudioMaxPolyphony(maxPolyphony: Int) {
        ObjectCalls.ptrcallWithIntArg(setAudioMaxPolyphonyBind, handle, maxPolyphony)
    }

    fun getAudioMaxPolyphony(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAudioMaxPolyphonyBind, handle)
    }

    fun setRootMotionTrack(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setRootMotionTrackBind, handle, path)
    }

    fun getRootMotionTrack(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getRootMotionTrackBind, handle)
    }

    fun setRootMotionLocal(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRootMotionLocalBind, handle, enabled)
    }

    fun isRootMotionLocal(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRootMotionLocalBind, handle)
    }

    fun getRootMotionPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRootMotionPositionBind, handle)
    }

    fun getRootMotionRotation(): Quaternion {
        return ObjectCalls.ptrcallNoArgsRetQuaternion(getRootMotionRotationBind, handle)
    }

    fun getRootMotionScale(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRootMotionScaleBind, handle)
    }

    fun getRootMotionPositionAccumulator(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRootMotionPositionAccumulatorBind, handle)
    }

    fun getRootMotionRotationAccumulator(): Quaternion {
        return ObjectCalls.ptrcallNoArgsRetQuaternion(getRootMotionRotationAccumulatorBind, handle)
    }

    fun getRootMotionScaleAccumulator(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRootMotionScaleAccumulatorBind, handle)
    }

    fun clearCaches() {
        ObjectCalls.ptrcallNoArgs(clearCachesBind, handle)
    }

    /**
     * Manually advance the animations by the specified time (in seconds).
     *
     * Generated from Godot docs: AnimationMixer.advance
     */
    fun advance(delta: Double) {
        ObjectCalls.ptrcallWithDoubleArg(advanceBind, handle, delta)
    }

    /**
     * If the animation track specified by `name` has an option `Animation.UPDATE_CAPTURE`, stores
     * current values of the objects indicated by the track path as a cache. If there is already a
     * captured cache, the old cache is discarded. After this it will interpolate with current
     * animation blending result during the playback process for the time specified by `duration`,
     * working like a crossfade. You can specify `trans_type` as the curve for the interpolation. For
     * better results, it may be appropriate to specify `Tween.TRANS_LINEAR` for cases where the first
     * key of the track begins with a non-zero value or where the key value does not change, and
     * `Tween.TRANS_QUAD` for cases where the key value changes linearly.
     *
     * Generated from Godot docs: AnimationMixer.capture
     */
    fun capture(name: String, duration: Double, transType: Long = 0L, easeType: Long = 0L) {
        ObjectCalls.ptrcallWithStringNameDoubleTwoLongArgs(captureBind, handle, name, duration, transType, easeType)
    }

    fun setResetOnSaveEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setResetOnSaveEnabledBind, handle, enabled)
    }

    fun isResetOnSaveEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isResetOnSaveEnabledBind, handle)
    }

    fun findAnimation(animation: Animation?): String {
        return ObjectCalls.ptrcallWithObjectArgRetStringName(findAnimationBind, handle, animation?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun findAnimationLibrary(animation: Animation?): String {
        return ObjectCalls.ptrcallWithObjectArgRetStringName(findAnimationLibraryBind, handle, animation?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun setParameter(path: String, value: Any?) {
        setIndexed(path, value)
    }

    fun getParameter(path: String): Any? =
        getIndexed(path)

    fun getStateMachinePlayback(path: String): AnimationNodeStateMachinePlayback {
        val value = getParameter(path)
        val playback = when (value) {
            is Resource -> AnimationNodeStateMachinePlayback.fromHandle(value.handle)
            is GodotObject -> AnimationNodeStateMachinePlayback.fromHandle(value.handle)
            else -> null
        }
        return playback ?: error("AnimationMixer parameter '$path' is not an AnimationNodeStateMachinePlayback")
    }

    object Signals {
        const val animationListChanged: String = "animation_list_changed"
        const val animationLibrariesUpdated: String = "animation_libraries_updated"
        const val animationFinished: String = "animation_finished"
        const val animationStarted: String = "animation_started"
        const val cachesCleared: String = "caches_cleared"
        const val mixerApplied: String = "mixer_applied"
        const val mixerUpdated: String = "mixer_updated"
    }

    companion object {
        const val ANIMATION_CALLBACK_MODE_PROCESS_PHYSICS: Long = 0L
        const val ANIMATION_CALLBACK_MODE_PROCESS_IDLE: Long = 1L
        const val ANIMATION_CALLBACK_MODE_PROCESS_MANUAL: Long = 2L
        const val ANIMATION_CALLBACK_MODE_METHOD_DEFERRED: Long = 0L
        const val ANIMATION_CALLBACK_MODE_METHOD_IMMEDIATE: Long = 1L
        const val ANIMATION_CALLBACK_MODE_DISCRETE_DOMINANT: Long = 0L
        const val ANIMATION_CALLBACK_MODE_DISCRETE_RECESSIVE: Long = 1L
        const val ANIMATION_CALLBACK_MODE_DISCRETE_FORCE_CONTINUOUS: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationMixer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationMixer? =
            if (handle.address() == 0L) null else AnimationMixer(handle)

        private const val ADD_ANIMATION_LIBRARY_HASH = 618909818L
        private val addAnimationLibraryBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "add_animation_library", ADD_ANIMATION_LIBRARY_HASH)
        }

        private const val REMOVE_ANIMATION_LIBRARY_HASH = 3304788590L
        private val removeAnimationLibraryBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "remove_animation_library", REMOVE_ANIMATION_LIBRARY_HASH)
        }

        private const val RENAME_ANIMATION_LIBRARY_HASH = 3740211285L
        private val renameAnimationLibraryBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "rename_animation_library", RENAME_ANIMATION_LIBRARY_HASH)
        }

        private const val HAS_ANIMATION_LIBRARY_HASH = 2619796661L
        private val hasAnimationLibraryBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "has_animation_library", HAS_ANIMATION_LIBRARY_HASH)
        }

        private const val GET_ANIMATION_LIBRARY_HASH = 147342321L
        private val getAnimationLibraryBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_animation_library", GET_ANIMATION_LIBRARY_HASH)
        }

        private const val GET_ANIMATION_LIBRARY_LIST_HASH = 3995934104L
        private val getAnimationLibraryListBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_animation_library_list", GET_ANIMATION_LIBRARY_LIST_HASH)
        }

        private const val HAS_ANIMATION_HASH = 2619796661L
        private val hasAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "has_animation", HAS_ANIMATION_HASH)
        }

        private const val GET_ANIMATION_HASH = 2933122410L
        private val getAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_animation", GET_ANIMATION_HASH)
        }

        private const val GET_ANIMATION_LIST_HASH = 1139954409L
        private val getAnimationListBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_animation_list", GET_ANIMATION_LIST_HASH)
        }

        private const val SET_ACTIVE_HASH = 2586408642L
        private val setActiveBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "set_active", SET_ACTIVE_HASH)
        }

        private const val IS_ACTIVE_HASH = 36873697L
        private val isActiveBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "is_active", IS_ACTIVE_HASH)
        }

        private const val SET_DETERMINISTIC_HASH = 2586408642L
        private val setDeterministicBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "set_deterministic", SET_DETERMINISTIC_HASH)
        }

        private const val IS_DETERMINISTIC_HASH = 36873697L
        private val isDeterministicBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "is_deterministic", IS_DETERMINISTIC_HASH)
        }

        private const val SET_ROOT_NODE_HASH = 1348162250L
        private val setRootNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "set_root_node", SET_ROOT_NODE_HASH)
        }

        private const val GET_ROOT_NODE_HASH = 4075236667L
        private val getRootNodeBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_node", GET_ROOT_NODE_HASH)
        }

        private const val SET_CALLBACK_MODE_PROCESS_HASH = 2153733086L
        private val setCallbackModeProcessBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "set_callback_mode_process", SET_CALLBACK_MODE_PROCESS_HASH)
        }

        private const val GET_CALLBACK_MODE_PROCESS_HASH = 1394468472L
        private val getCallbackModeProcessBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_callback_mode_process", GET_CALLBACK_MODE_PROCESS_HASH)
        }

        private const val SET_CALLBACK_MODE_METHOD_HASH = 742218271L
        private val setCallbackModeMethodBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "set_callback_mode_method", SET_CALLBACK_MODE_METHOD_HASH)
        }

        private const val GET_CALLBACK_MODE_METHOD_HASH = 489449656L
        private val getCallbackModeMethodBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_callback_mode_method", GET_CALLBACK_MODE_METHOD_HASH)
        }

        private const val SET_CALLBACK_MODE_DISCRETE_HASH = 1998944670L
        private val setCallbackModeDiscreteBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "set_callback_mode_discrete", SET_CALLBACK_MODE_DISCRETE_HASH)
        }

        private const val GET_CALLBACK_MODE_DISCRETE_HASH = 3493168860L
        private val getCallbackModeDiscreteBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_callback_mode_discrete", GET_CALLBACK_MODE_DISCRETE_HASH)
        }

        private const val SET_AUDIO_MAX_POLYPHONY_HASH = 1286410249L
        private val setAudioMaxPolyphonyBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "set_audio_max_polyphony", SET_AUDIO_MAX_POLYPHONY_HASH)
        }

        private const val GET_AUDIO_MAX_POLYPHONY_HASH = 3905245786L
        private val getAudioMaxPolyphonyBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_audio_max_polyphony", GET_AUDIO_MAX_POLYPHONY_HASH)
        }

        private const val SET_ROOT_MOTION_TRACK_HASH = 1348162250L
        private val setRootMotionTrackBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "set_root_motion_track", SET_ROOT_MOTION_TRACK_HASH)
        }

        private const val GET_ROOT_MOTION_TRACK_HASH = 4075236667L
        private val getRootMotionTrackBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_track", GET_ROOT_MOTION_TRACK_HASH)
        }

        private const val SET_ROOT_MOTION_LOCAL_HASH = 2586408642L
        private val setRootMotionLocalBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "set_root_motion_local", SET_ROOT_MOTION_LOCAL_HASH)
        }

        private const val IS_ROOT_MOTION_LOCAL_HASH = 36873697L
        private val isRootMotionLocalBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "is_root_motion_local", IS_ROOT_MOTION_LOCAL_HASH)
        }

        private const val GET_ROOT_MOTION_POSITION_HASH = 3360562783L
        private val getRootMotionPositionBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_position", GET_ROOT_MOTION_POSITION_HASH)
        }

        private const val GET_ROOT_MOTION_ROTATION_HASH = 1222331677L
        private val getRootMotionRotationBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_rotation", GET_ROOT_MOTION_ROTATION_HASH)
        }

        private const val GET_ROOT_MOTION_SCALE_HASH = 3360562783L
        private val getRootMotionScaleBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_scale", GET_ROOT_MOTION_SCALE_HASH)
        }

        private const val GET_ROOT_MOTION_POSITION_ACCUMULATOR_HASH = 3360562783L
        private val getRootMotionPositionAccumulatorBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_position_accumulator", GET_ROOT_MOTION_POSITION_ACCUMULATOR_HASH)
        }

        private const val GET_ROOT_MOTION_ROTATION_ACCUMULATOR_HASH = 1222331677L
        private val getRootMotionRotationAccumulatorBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_rotation_accumulator", GET_ROOT_MOTION_ROTATION_ACCUMULATOR_HASH)
        }

        private const val GET_ROOT_MOTION_SCALE_ACCUMULATOR_HASH = 3360562783L
        private val getRootMotionScaleAccumulatorBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_scale_accumulator", GET_ROOT_MOTION_SCALE_ACCUMULATOR_HASH)
        }

        private const val CLEAR_CACHES_HASH = 3218959716L
        private val clearCachesBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "clear_caches", CLEAR_CACHES_HASH)
        }

        private const val ADVANCE_HASH = 373806689L
        private val advanceBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "advance", ADVANCE_HASH)
        }

        private const val CAPTURE_HASH = 1333632127L
        private val captureBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "capture", CAPTURE_HASH)
        }

        private const val SET_RESET_ON_SAVE_ENABLED_HASH = 2586408642L
        private val setResetOnSaveEnabledBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "set_reset_on_save_enabled", SET_RESET_ON_SAVE_ENABLED_HASH)
        }

        private const val IS_RESET_ON_SAVE_ENABLED_HASH = 36873697L
        private val isResetOnSaveEnabledBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "is_reset_on_save_enabled", IS_RESET_ON_SAVE_ENABLED_HASH)
        }

        private const val FIND_ANIMATION_HASH = 1559484580L
        private val findAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "find_animation", FIND_ANIMATION_HASH)
        }

        private const val FIND_ANIMATION_LIBRARY_HASH = 1559484580L
        private val findAnimationLibraryBind by lazy {
            ObjectCalls.getMethodBind("AnimationMixer", "find_animation_library", FIND_ANIMATION_LIBRARY_HASH)
        }
    }
}
