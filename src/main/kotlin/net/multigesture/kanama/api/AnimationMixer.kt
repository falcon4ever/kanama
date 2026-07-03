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

    /**
     * Adds `library` to the animation player, under the key `name`. AnimationMixer has a global
     * library by default with an empty string as key. For adding an animation to the global library:
     *
     * Generated from Godot docs: AnimationMixer.add_animation_library
     */
    fun addAnimationLibrary(name: String, library: AnimationLibrary?): Long {
        return ObjectCalls.ptrcallWithStringNameAndObjectArgRetLong(addAnimationLibraryBind, handle, name, library?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Removes the `AnimationLibrary` associated with the key `name`.
     *
     * Generated from Godot docs: AnimationMixer.remove_animation_library
     */
    fun removeAnimationLibrary(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(removeAnimationLibraryBind, handle, name)
    }

    /**
     * Moves the `AnimationLibrary` associated with the key `name` to the key `newname`.
     *
     * Generated from Godot docs: AnimationMixer.rename_animation_library
     */
    fun renameAnimationLibrary(name: String, newname: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(renameAnimationLibraryBind, handle, name, newname)
    }

    /**
     * Returns `true` if the `AnimationMixer` stores an `AnimationLibrary` with key `name`.
     *
     * Generated from Godot docs: AnimationMixer.has_animation_library
     */
    fun hasAnimationLibrary(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasAnimationLibraryBind, handle, name)
    }

    /**
     * Returns the first `AnimationLibrary` with key `name` or `null` if not found. To get the
     * `AnimationMixer`'s global animation library, use `get_animation_library("")`.
     *
     * Generated from Godot docs: AnimationMixer.get_animation_library
     */
    fun getAnimationLibrary(name: String): AnimationLibrary? {
        return AnimationLibrary.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getAnimationLibraryBind, handle, name))
    }

    /**
     * Returns the list of stored library keys.
     *
     * Generated from Godot docs: AnimationMixer.get_animation_library_list
     */
    fun getAnimationLibraryList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(getAnimationLibraryListBind, handle)
    }

    /**
     * Returns `true` if the `AnimationMixer` stores an `Animation` with key `name`.
     *
     * Generated from Godot docs: AnimationMixer.has_animation
     */
    fun hasAnimation(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(hasAnimationBind, handle, name)
    }

    /**
     * Returns the `Animation` with the key `name`. If the animation does not exist, `null` is returned
     * and an error is logged.
     *
     * Generated from Godot docs: AnimationMixer.get_animation
     */
    fun getAnimation(name: String): Animation? {
        return Animation.wrap(ObjectCalls.ptrcallWithStringNameArgRetObject(getAnimationBind, handle, name))
    }

    /**
     * Returns the list of stored animation keys.
     *
     * Generated from Godot docs: AnimationMixer.get_animation_list
     */
    fun getAnimationList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(getAnimationListBind, handle)
    }

    /**
     * If `true`, the `AnimationMixer` will be processing.
     *
     * Generated from Godot docs: AnimationMixer.set_active
     */
    fun setActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setActiveBind, handle, active)
    }

    /**
     * If `true`, the `AnimationMixer` will be processing.
     *
     * Generated from Godot docs: AnimationMixer.is_active
     */
    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isActiveBind, handle)
    }

    /**
     * If `true`, the blending uses the deterministic algorithm. The total weight is not normalized and
     * the result is accumulated with an initial value (`0` or a `"RESET"` animation if present). This
     * means that if the total amount of blending is `0.0`, the result is equal to the `"RESET"`
     * animation. If the number of tracks between the blended animations is different, the animation
     * with the missing track is treated as if it had the initial value. If `false`, The blend does not
     * use the deterministic algorithm. The total weight is normalized and always `1.0`. If the number
     * of tracks between the blended animations is different, nothing is done about the animation that
     * is missing a track. Note: In `AnimationTree`, the blending with `AnimationNodeAdd2`,
     * `AnimationNodeAdd3`, `AnimationNodeSub2` or the weight greater than `1.0` may produce unexpected
     * results. For example, if `AnimationNodeAdd2` blends two nodes with the amount `1.0`, then total
     * weight is `2.0` but it will be normalized to make the total amount `1.0` and the result will be
     * equal to `AnimationNodeBlend2` with the amount `0.5`.
     *
     * Generated from Godot docs: AnimationMixer.set_deterministic
     */
    fun setDeterministic(deterministic: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDeterministicBind, handle, deterministic)
    }

    /**
     * If `true`, the blending uses the deterministic algorithm. The total weight is not normalized and
     * the result is accumulated with an initial value (`0` or a `"RESET"` animation if present). This
     * means that if the total amount of blending is `0.0`, the result is equal to the `"RESET"`
     * animation. If the number of tracks between the blended animations is different, the animation
     * with the missing track is treated as if it had the initial value. If `false`, The blend does not
     * use the deterministic algorithm. The total weight is normalized and always `1.0`. If the number
     * of tracks between the blended animations is different, nothing is done about the animation that
     * is missing a track. Note: In `AnimationTree`, the blending with `AnimationNodeAdd2`,
     * `AnimationNodeAdd3`, `AnimationNodeSub2` or the weight greater than `1.0` may produce unexpected
     * results. For example, if `AnimationNodeAdd2` blends two nodes with the amount `1.0`, then total
     * weight is `2.0` but it will be normalized to make the total amount `1.0` and the result will be
     * equal to `AnimationNodeBlend2` with the amount `0.5`.
     *
     * Generated from Godot docs: AnimationMixer.is_deterministic
     */
    fun isDeterministic(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDeterministicBind, handle)
    }

    /**
     * The node which node path references will travel from.
     *
     * Generated from Godot docs: AnimationMixer.set_root_node
     */
    fun setRootNode(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setRootNodeBind, handle, path)
    }

    /**
     * The node which node path references will travel from.
     *
     * Generated from Godot docs: AnimationMixer.get_root_node
     */
    fun getRootNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getRootNodeBind, handle)
    }

    /**
     * The process notification in which to update animations.
     *
     * Generated from Godot docs: AnimationMixer.set_callback_mode_process
     */
    fun setCallbackModeProcess(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCallbackModeProcessBind, handle, mode)
    }

    /**
     * The process notification in which to update animations.
     *
     * Generated from Godot docs: AnimationMixer.get_callback_mode_process
     */
    fun getCallbackModeProcess(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCallbackModeProcessBind, handle)
    }

    /**
     * The call mode used for "Call Method" tracks.
     *
     * Generated from Godot docs: AnimationMixer.set_callback_mode_method
     */
    fun setCallbackModeMethod(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCallbackModeMethodBind, handle, mode)
    }

    /**
     * The call mode used for "Call Method" tracks.
     *
     * Generated from Godot docs: AnimationMixer.get_callback_mode_method
     */
    fun getCallbackModeMethod(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCallbackModeMethodBind, handle)
    }

    /**
     * Ordinarily, tracks can be set to `Animation.UPDATE_DISCRETE` to update infrequently, usually
     * when using nearest interpolation. However, when blending with `Animation.UPDATE_CONTINUOUS`
     * several results are considered. The `callback_mode_discrete` specify it explicitly. See also
     * `AnimationCallbackModeDiscrete`. To make the blended results look good, it is recommended to set
     * this to `ANIMATION_CALLBACK_MODE_DISCRETE_FORCE_CONTINUOUS` to update every frame during
     * blending. Other values exist for compatibility and they are fine if there is no blending, but
     * not so, may produce artifacts.
     *
     * Generated from Godot docs: AnimationMixer.set_callback_mode_discrete
     */
    fun setCallbackModeDiscrete(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCallbackModeDiscreteBind, handle, mode)
    }

    /**
     * Ordinarily, tracks can be set to `Animation.UPDATE_DISCRETE` to update infrequently, usually
     * when using nearest interpolation. However, when blending with `Animation.UPDATE_CONTINUOUS`
     * several results are considered. The `callback_mode_discrete` specify it explicitly. See also
     * `AnimationCallbackModeDiscrete`. To make the blended results look good, it is recommended to set
     * this to `ANIMATION_CALLBACK_MODE_DISCRETE_FORCE_CONTINUOUS` to update every frame during
     * blending. Other values exist for compatibility and they are fine if there is no blending, but
     * not so, may produce artifacts.
     *
     * Generated from Godot docs: AnimationMixer.get_callback_mode_discrete
     */
    fun getCallbackModeDiscrete(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCallbackModeDiscreteBind, handle)
    }

    /**
     * The number of possible simultaneous sounds for each of the assigned AudioStreamPlayers. For
     * example, if this value is `32` and the animation has two audio tracks, the two
     * `AudioStreamPlayer`s assigned can play simultaneously up to `32` voices each.
     *
     * Generated from Godot docs: AnimationMixer.set_audio_max_polyphony
     */
    fun setAudioMaxPolyphony(maxPolyphony: Int) {
        ObjectCalls.ptrcallWithIntArg(setAudioMaxPolyphonyBind, handle, maxPolyphony)
    }

    /**
     * The number of possible simultaneous sounds for each of the assigned AudioStreamPlayers. For
     * example, if this value is `32` and the animation has two audio tracks, the two
     * `AudioStreamPlayer`s assigned can play simultaneously up to `32` voices each.
     *
     * Generated from Godot docs: AnimationMixer.get_audio_max_polyphony
     */
    fun getAudioMaxPolyphony(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getAudioMaxPolyphonyBind, handle)
    }

    /**
     * The path to the Animation track used for root motion. Paths must be valid scene-tree paths to a
     * node, and must be specified starting from the parent node of the node that will reproduce the
     * animation. The `root_motion_track` uses the same format as `Animation.track_set_path`, but note
     * that a bone must be specified. If the track has type `Animation.TYPE_POSITION_3D`,
     * `Animation.TYPE_ROTATION_3D`, or `Animation.TYPE_SCALE_3D` the transformation will be canceled
     * visually, and the animation will appear to stay in place. See also `get_root_motion_position`,
     * `get_root_motion_rotation`, `get_root_motion_scale`, and `RootMotionView`.
     *
     * Generated from Godot docs: AnimationMixer.set_root_motion_track
     */
    fun setRootMotionTrack(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(setRootMotionTrackBind, handle, path)
    }

    /**
     * The path to the Animation track used for root motion. Paths must be valid scene-tree paths to a
     * node, and must be specified starting from the parent node of the node that will reproduce the
     * animation. The `root_motion_track` uses the same format as `Animation.track_set_path`, but note
     * that a bone must be specified. If the track has type `Animation.TYPE_POSITION_3D`,
     * `Animation.TYPE_ROTATION_3D`, or `Animation.TYPE_SCALE_3D` the transformation will be canceled
     * visually, and the animation will appear to stay in place. See also `get_root_motion_position`,
     * `get_root_motion_rotation`, `get_root_motion_scale`, and `RootMotionView`.
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_track
     */
    fun getRootMotionTrack(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(getRootMotionTrackBind, handle)
    }

    /**
     * If `true`, `get_root_motion_position` value is extracted as a local translation value before
     * blending. In other words, it is treated like the translation is done after the rotation.
     *
     * Generated from Godot docs: AnimationMixer.set_root_motion_local
     */
    fun setRootMotionLocal(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRootMotionLocalBind, handle, enabled)
    }

    /**
     * If `true`, `get_root_motion_position` value is extracted as a local translation value before
     * blending. In other words, it is treated like the translation is done after the rotation.
     *
     * Generated from Godot docs: AnimationMixer.is_root_motion_local
     */
    fun isRootMotionLocal(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRootMotionLocalBind, handle)
    }

    /**
     * Retrieve the motion delta of position with the `root_motion_track` as a `Vector3` that can be
     * used elsewhere. If `root_motion_track` is not a path to a track of type
     * `Animation.TYPE_POSITION_3D`, returns `Vector3(0, 0, 0)`. See also `root_motion_track` and
     * `RootMotionView`. The most basic example is applying position to `CharacterBody3D`:
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_position
     */
    fun getRootMotionPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRootMotionPositionBind, handle)
    }

    /**
     * Retrieve the motion delta of rotation with the `root_motion_track` as a `Quaternion` that can be
     * used elsewhere. If `root_motion_track` is not a path to a track of type
     * `Animation.TYPE_ROTATION_3D`, returns `Quaternion(0, 0, 0, 1)`. See also `root_motion_track` and
     * `RootMotionView`. The most basic example is applying rotation to `CharacterBody3D`:
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_rotation
     */
    fun getRootMotionRotation(): Quaternion {
        return ObjectCalls.ptrcallNoArgsRetQuaternion(getRootMotionRotationBind, handle)
    }

    /**
     * Retrieve the motion delta of scale with the `root_motion_track` as a `Vector3` that can be used
     * elsewhere. If `root_motion_track` is not a path to a track of type `Animation.TYPE_SCALE_3D`,
     * returns `Vector3(0, 0, 0)`. See also `root_motion_track` and `RootMotionView`. The most basic
     * example is applying scale to `CharacterBody3D`:
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_scale
     */
    fun getRootMotionScale(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRootMotionScaleBind, handle)
    }

    /**
     * Retrieve the blended value of the position tracks with the `root_motion_track` as a `Vector3`
     * that can be used elsewhere. This is useful in cases where you want to respect the initial key
     * values of the animation. For example, if an animation with only one key `Vector3(0, 0, 0)` is
     * played in the previous frame and then an animation with only one key `Vector3(1, 0, 1)` is
     * played in the next frame, the difference can be calculated as follows:
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_position_accumulator
     */
    fun getRootMotionPositionAccumulator(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRootMotionPositionAccumulatorBind, handle)
    }

    /**
     * Retrieve the blended value of the rotation tracks with the `root_motion_track` as a `Quaternion`
     * that can be used elsewhere. This is necessary to apply the root motion position correctly,
     * taking rotation into account. See also `get_root_motion_position`. Also, this is useful in cases
     * where you want to respect the initial key values of the animation. For example, if an animation
     * with only one key `Quaternion(0, 0, 0, 1)` is played in the previous frame and then an animation
     * with only one key `Quaternion(0, 0.707, 0, 0.707)` is played in the next frame, the difference
     * can be calculated as follows:
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_rotation_accumulator
     */
    fun getRootMotionRotationAccumulator(): Quaternion {
        return ObjectCalls.ptrcallNoArgsRetQuaternion(getRootMotionRotationAccumulatorBind, handle)
    }

    /**
     * Retrieve the blended value of the scale tracks with the `root_motion_track` as a `Vector3` that
     * can be used elsewhere. For example, if an animation with only one key `Vector3(1, 1, 1)` is
     * played in the previous frame and then an animation with only one key `Vector3(2, 2, 2)` is
     * played in the next frame, the difference can be calculated as follows:
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_scale_accumulator
     */
    fun getRootMotionScaleAccumulator(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getRootMotionScaleAccumulatorBind, handle)
    }

    /**
     * `AnimationMixer` caches animated nodes. It may not notice if a node disappears; `clear_caches`
     * forces it to update the cache again.
     *
     * Generated from Godot docs: AnimationMixer.clear_caches
     */
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

    /**
     * This is used by the editor. If set to `true`, the scene will be saved with the effects of the
     * reset animation (the animation with the key `"RESET"`) applied as if it had been seeked to time
     * 0, with the editor keeping the values that the scene had before saving. This makes it more
     * convenient to preview and edit animations in the editor, as changes to the scene will not be
     * saved as long as they are set in the reset animation.
     *
     * Generated from Godot docs: AnimationMixer.set_reset_on_save_enabled
     */
    fun setResetOnSaveEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setResetOnSaveEnabledBind, handle, enabled)
    }

    /**
     * This is used by the editor. If set to `true`, the scene will be saved with the effects of the
     * reset animation (the animation with the key `"RESET"`) applied as if it had been seeked to time
     * 0, with the editor keeping the values that the scene had before saving. This makes it more
     * convenient to preview and edit animations in the editor, as changes to the scene will not be
     * saved as long as they are set in the reset animation.
     *
     * Generated from Godot docs: AnimationMixer.is_reset_on_save_enabled
     */
    fun isResetOnSaveEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isResetOnSaveEnabledBind, handle)
    }

    /**
     * Returns the key of `animation` or an empty `StringName` if not found.
     *
     * Generated from Godot docs: AnimationMixer.find_animation
     */
    fun findAnimation(animation: Animation?): String {
        return ObjectCalls.ptrcallWithObjectArgRetStringName(findAnimationBind, handle, animation?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the key for the `AnimationLibrary` that contains `animation` or an empty `StringName` if
     * not found.
     *
     * Generated from Godot docs: AnimationMixer.find_animation_library
     */
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
