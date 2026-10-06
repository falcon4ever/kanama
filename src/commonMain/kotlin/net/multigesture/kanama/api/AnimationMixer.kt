package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.Quaternion
import net.multigesture.kanama.types.Vector3

/**
 * Base class for `AnimationPlayer` and `AnimationTree`.
 *
 * Generated from Godot docs: AnimationMixer
 */
open class AnimationMixer(handle: GodotHandle) : Node(handle) {
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

    var callbackModeProcess: AnimationMixer.AnimationCallbackModeProcess
        @JvmName("callbackModeProcessProperty")
        get() = getCallbackModeProcess()
        @JvmName("setCallbackModeProcessProperty")
        set(value) = setCallbackModeProcess(value)

    var callbackModeMethod: AnimationMixer.AnimationCallbackModeMethod
        @JvmName("callbackModeMethodProperty")
        get() = getCallbackModeMethod()
        @JvmName("setCallbackModeMethodProperty")
        set(value) = setCallbackModeMethod(value)

    var callbackModeDiscrete: AnimationMixer.AnimationCallbackModeDiscrete
        @JvmName("callbackModeDiscreteProperty")
        get() = getCallbackModeDiscrete()
        @JvmName("setCallbackModeDiscreteProperty")
        set(value) = setCallbackModeDiscrete(value)

    /**
     * Adds `library` to the animation player, under the key `name`. AnimationMixer has a global
     * library by default with an empty string as key.
     *
     * Generated from Godot docs: AnimationMixer.add_animation_library
     */
    fun addAnimationLibrary(name: String, library: AnimationLibrary?): GodotError {
        return GodotError(ObjectCalls.ptrcallWithStringNameAndObjectArgRetLong(Binds.addAnimationLibraryBind, segment, name, library?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * Removes the `AnimationLibrary` associated with the key `name`.
     *
     * Generated from Godot docs: AnimationMixer.remove_animation_library
     */
    fun removeAnimationLibrary(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.removeAnimationLibraryBind, segment, name)
    }

    /**
     * Moves the `AnimationLibrary` associated with the key `name` to the key `newname`.
     *
     * Generated from Godot docs: AnimationMixer.rename_animation_library
     */
    fun renameAnimationLibrary(name: String, newname: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(Binds.renameAnimationLibraryBind, segment, name, newname)
    }

    /**
     * Returns `true` if the `AnimationMixer` stores an `AnimationLibrary` with key `name`.
     *
     * Generated from Godot docs: AnimationMixer.has_animation_library
     */
    fun hasAnimationLibrary(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasAnimationLibraryBind, segment, name)
    }

    /**
     * Returns the first `AnimationLibrary` with key `name` or `null` if not found. To get the
     * `AnimationMixer`'s global animation library, use `get_animation_library("")`.
     *
     * Generated from Godot docs: AnimationMixer.get_animation_library
     */
    fun getAnimationLibrary(name: String): AnimationLibrary? {
        return AnimationLibrary.wrapOwned(ObjectCalls.ptrcallWithStringNameArgRetObject(Binds.getAnimationLibraryBind, segment, name))
    }

    /**
     * Returns the list of stored library keys.
     *
     * Generated from Godot docs: AnimationMixer.get_animation_library_list
     */
    fun getAnimationLibraryList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(Binds.getAnimationLibraryListBind, segment)
    }

    /**
     * Returns `true` if the `AnimationMixer` stores an `Animation` with key `name`.
     *
     * Generated from Godot docs: AnimationMixer.has_animation
     */
    fun hasAnimation(name: String): Boolean {
        return ObjectCalls.ptrcallWithStringNameArgRetBool(Binds.hasAnimationBind, segment, name)
    }

    /**
     * Returns the `Animation` with the key `name`. If the animation does not exist, `null` is returned
     * and an error is logged.
     *
     * Generated from Godot docs: AnimationMixer.get_animation
     */
    fun getAnimation(name: String): Animation? {
        return Animation.wrapOwned(ObjectCalls.ptrcallWithStringNameArgRetObject(Binds.getAnimationBind, segment, name))
    }

    /**
     * Returns the list of stored animation keys.
     *
     * Generated from Godot docs: AnimationMixer.get_animation_list
     */
    fun getAnimationList(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetPackedStringList(Binds.getAnimationListBind, segment)
    }

    /**
     * If `true`, the `AnimationMixer` will be processing.
     *
     * Generated from Godot docs: AnimationMixer.set_active
     */
    fun setActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setActiveBind, segment, active)
    }

    /**
     * If `true`, the `AnimationMixer` will be processing.
     *
     * Generated from Godot docs: AnimationMixer.is_active
     */
    fun isActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isActiveBind, segment)
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setDeterministicBind, segment, deterministic)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isDeterministicBind, segment)
    }

    /**
     * The node which node path references will travel from.
     *
     * Generated from Godot docs: AnimationMixer.set_root_node
     */
    fun setRootNode(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setRootNodeBind, segment, path)
    }

    /**
     * The node which node path references will travel from.
     *
     * Generated from Godot docs: AnimationMixer.get_root_node
     */
    fun getRootNode(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getRootNodeBind, segment)
    }

    /**
     * The process notification in which to update animations.
     *
     * Generated from Godot docs: AnimationMixer.set_callback_mode_process
     */
    fun setCallbackModeProcess(mode: AnimationMixer.AnimationCallbackModeProcess) {
        ObjectCalls.ptrcallWithLongArg(Binds.setCallbackModeProcessBind, segment, mode.value)
    }

    /**
     * The process notification in which to update animations.
     *
     * Generated from Godot docs: AnimationMixer.get_callback_mode_process
     */
    fun getCallbackModeProcess(): AnimationMixer.AnimationCallbackModeProcess {
        return AnimationMixer.AnimationCallbackModeProcess(ObjectCalls.ptrcallNoArgsRetLong(Binds.getCallbackModeProcessBind, segment))
    }

    /**
     * The call mode used for "Call Method" tracks.
     *
     * Generated from Godot docs: AnimationMixer.set_callback_mode_method
     */
    fun setCallbackModeMethod(mode: AnimationMixer.AnimationCallbackModeMethod) {
        ObjectCalls.ptrcallWithLongArg(Binds.setCallbackModeMethodBind, segment, mode.value)
    }

    /**
     * The call mode used for "Call Method" tracks.
     *
     * Generated from Godot docs: AnimationMixer.get_callback_mode_method
     */
    fun getCallbackModeMethod(): AnimationMixer.AnimationCallbackModeMethod {
        return AnimationMixer.AnimationCallbackModeMethod(ObjectCalls.ptrcallNoArgsRetLong(Binds.getCallbackModeMethodBind, segment))
    }

    /**
     * Ordinarily, tracks can be set to `Animation.UpdateMode.DISCRETE` to update infrequently, usually
     * when using nearest interpolation. However, when blending with `Animation.UpdateMode.CONTINUOUS`
     * several results are considered. The `callback_mode_discrete` specify it explicitly. See also
     * `AnimationCallbackModeDiscrete`. To make the blended results look good, it is recommended to set
     * this to `AnimationCallbackModeDiscrete.FORCE_CONTINUOUS` to update every frame during blending.
     * Other values exist for compatibility and they are fine if there is no blending, but not so, may
     * produce artifacts.
     *
     * Generated from Godot docs: AnimationMixer.set_callback_mode_discrete
     */
    fun setCallbackModeDiscrete(mode: AnimationMixer.AnimationCallbackModeDiscrete) {
        ObjectCalls.ptrcallWithLongArg(Binds.setCallbackModeDiscreteBind, segment, mode.value)
    }

    /**
     * Ordinarily, tracks can be set to `Animation.UpdateMode.DISCRETE` to update infrequently, usually
     * when using nearest interpolation. However, when blending with `Animation.UpdateMode.CONTINUOUS`
     * several results are considered. The `callback_mode_discrete` specify it explicitly. See also
     * `AnimationCallbackModeDiscrete`. To make the blended results look good, it is recommended to set
     * this to `AnimationCallbackModeDiscrete.FORCE_CONTINUOUS` to update every frame during blending.
     * Other values exist for compatibility and they are fine if there is no blending, but not so, may
     * produce artifacts.
     *
     * Generated from Godot docs: AnimationMixer.get_callback_mode_discrete
     */
    fun getCallbackModeDiscrete(): AnimationMixer.AnimationCallbackModeDiscrete {
        return AnimationMixer.AnimationCallbackModeDiscrete(ObjectCalls.ptrcallNoArgsRetLong(Binds.getCallbackModeDiscreteBind, segment))
    }

    /**
     * The number of possible simultaneous sounds for each of the assigned AudioStreamPlayers. For
     * example, if this value is `32` and the animation has two audio tracks, the two
     * `AudioStreamPlayer`s assigned can play simultaneously up to `32` voices each.
     *
     * Generated from Godot docs: AnimationMixer.set_audio_max_polyphony
     */
    fun setAudioMaxPolyphony(maxPolyphony: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setAudioMaxPolyphonyBind, segment, maxPolyphony)
    }

    /**
     * The number of possible simultaneous sounds for each of the assigned AudioStreamPlayers. For
     * example, if this value is `32` and the animation has two audio tracks, the two
     * `AudioStreamPlayer`s assigned can play simultaneously up to `32` voices each.
     *
     * Generated from Godot docs: AnimationMixer.get_audio_max_polyphony
     */
    fun getAudioMaxPolyphony(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getAudioMaxPolyphonyBind, segment)
    }

    /**
     * The path to the Animation track used for root motion. Paths must be valid scene-tree paths to a
     * node, and must be specified starting from the parent node of the node that will reproduce the
     * animation. The `root_motion_track` uses the same format as `Animation.track_set_path`, but note
     * that a bone must be specified. If the track has type `Animation.TrackType.POSITION_3D`,
     * `Animation.TrackType.ROTATION_3D`, or `Animation.TrackType.SCALE_3D` the transformation will be
     * canceled visually, and the animation will appear to stay in place. See also
     * `get_root_motion_position`, `get_root_motion_rotation`, `get_root_motion_scale`, and
     * `RootMotionView`.
     *
     * Generated from Godot docs: AnimationMixer.set_root_motion_track
     */
    fun setRootMotionTrack(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setRootMotionTrackBind, segment, path)
    }

    /**
     * The path to the Animation track used for root motion. Paths must be valid scene-tree paths to a
     * node, and must be specified starting from the parent node of the node that will reproduce the
     * animation. The `root_motion_track` uses the same format as `Animation.track_set_path`, but note
     * that a bone must be specified. If the track has type `Animation.TrackType.POSITION_3D`,
     * `Animation.TrackType.ROTATION_3D`, or `Animation.TrackType.SCALE_3D` the transformation will be
     * canceled visually, and the animation will appear to stay in place. See also
     * `get_root_motion_position`, `get_root_motion_rotation`, `get_root_motion_scale`, and
     * `RootMotionView`.
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_track
     */
    fun getRootMotionTrack(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getRootMotionTrackBind, segment)
    }

    /**
     * If `true`, `get_root_motion_position` value is extracted as a local translation value before
     * blending. In other words, it is treated like the translation is done after the rotation.
     *
     * Generated from Godot docs: AnimationMixer.set_root_motion_local
     */
    fun setRootMotionLocal(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setRootMotionLocalBind, segment, enabled)
    }

    /**
     * If `true`, `get_root_motion_position` value is extracted as a local translation value before
     * blending. In other words, it is treated like the translation is done after the rotation.
     *
     * Generated from Godot docs: AnimationMixer.is_root_motion_local
     */
    fun isRootMotionLocal(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isRootMotionLocalBind, segment)
    }

    /**
     * Retrieve the motion delta of position with the `root_motion_track` as a `Vector3` that can be
     * used elsewhere. If `root_motion_track` is not a path to a track of type
     * `Animation.TrackType.POSITION_3D`, returns `Vector3(0, 0, 0)`. See also `root_motion_track` and
     * `RootMotionView`.
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_position
     */
    fun getRootMotionPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getRootMotionPositionBind, segment)
    }

    /**
     * Retrieve the motion delta of rotation with the `root_motion_track` as a `Quaternion` that can be
     * used elsewhere. If `root_motion_track` is not a path to a track of type
     * `Animation.TrackType.ROTATION_3D`, returns `Quaternion(0, 0, 0, 1)`. See also
     * `root_motion_track` and `RootMotionView`.
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_rotation
     */
    fun getRootMotionRotation(): Quaternion {
        return ObjectCalls.ptrcallNoArgsRetQuaternion(Binds.getRootMotionRotationBind, segment)
    }

    /**
     * Retrieve the motion delta of scale with the `root_motion_track` as a `Vector3` that can be used
     * elsewhere. If `root_motion_track` is not a path to a track of type
     * `Animation.TrackType.SCALE_3D`, returns `Vector3(0, 0, 0)`. See also `root_motion_track` and
     * `RootMotionView`.
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_scale
     */
    fun getRootMotionScale(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getRootMotionScaleBind, segment)
    }

    /**
     * Retrieve the blended value of the position tracks with the `root_motion_track` as a `Vector3`
     * that can be used elsewhere. This is useful in cases where you want to respect the initial key
     * values of the animation.
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_position_accumulator
     */
    fun getRootMotionPositionAccumulator(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getRootMotionPositionAccumulatorBind, segment)
    }

    /**
     * Retrieve the blended value of the rotation tracks with the `root_motion_track` as a `Quaternion`
     * that can be used elsewhere. This is necessary to apply the root motion position correctly,
     * taking rotation into account. See also `get_root_motion_position`. Also, this is useful in cases
     * where you want to respect the initial key values of the animation.
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_rotation_accumulator
     */
    fun getRootMotionRotationAccumulator(): Quaternion {
        return ObjectCalls.ptrcallNoArgsRetQuaternion(Binds.getRootMotionRotationAccumulatorBind, segment)
    }

    /**
     * Retrieve the blended value of the scale tracks with the `root_motion_track` as a `Vector3` that
     * can be used elsewhere.
     *
     * Generated from Godot docs: AnimationMixer.get_root_motion_scale_accumulator
     */
    fun getRootMotionScaleAccumulator(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getRootMotionScaleAccumulatorBind, segment)
    }

    /**
     * `AnimationMixer` caches animated nodes. It may not notice if a node disappears; `clear_caches`
     * forces it to update the cache again.
     *
     * Generated from Godot docs: AnimationMixer.clear_caches
     */
    fun clearCaches() {
        ObjectCalls.ptrcallNoArgs(Binds.clearCachesBind, segment)
    }

    /**
     * Manually advance the animations by the specified time (in seconds).
     *
     * Generated from Godot docs: AnimationMixer.advance
     */
    fun advance(delta: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.advanceBind, segment, delta)
    }

    /**
     * If the animation track specified by `name` has an option `Animation.UpdateMode.CAPTURE`, stores
     * current values of the objects indicated by the track path as a cache. If there is already a
     * captured cache, the old cache is discarded. After this it will interpolate with current
     * animation blending result during the playback process for the time specified by `duration`,
     * working like a crossfade. You can specify `trans_type` as the curve for the interpolation. For
     * better results, it may be appropriate to specify `Tween.TransitionType.LINEAR` for cases where
     * the first key of the track begins with a non-zero value or where the key value does not change,
     * and `Tween.TransitionType.QUAD` for cases where the key value changes linearly.
     *
     * Generated from Godot docs: AnimationMixer.capture
     */
    fun capture(name: String, duration: Double, transType: Tween.TransitionType = Tween.TransitionType.LINEAR, easeType: Tween.EaseType = Tween.EaseType.IN) {
        ObjectCalls.ptrcallWithStringNameDoubleTwoLongArgs(Binds.captureBind, segment, name, duration, transType.value, easeType.value)
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setResetOnSaveEnabledBind, segment, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isResetOnSaveEnabledBind, segment)
    }

    /**
     * Returns the key of `animation` or an empty `StringName` if not found.
     *
     * Generated from Godot docs: AnimationMixer.find_animation
     */
    fun findAnimation(animation: Animation?): String {
        return ObjectCalls.ptrcallWithObjectArgRetStringName(Binds.findAnimationBind, segment, animation?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the key for the `AnimationLibrary` that contains `animation` or an empty `StringName` if
     * not found.
     *
     * Generated from Godot docs: AnimationMixer.find_animation_library
     */
    fun findAnimationLibrary(animation: Animation?): String {
        return ObjectCalls.ptrcallWithObjectArgRetStringName(Binds.findAnimationLibraryBind, segment, animation?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /** Signal `animation_list_changed()`; see [TypedSignal]. */
    val animationListChanged: Signal0
        @JvmName("animationListChangedTypedSignal")
        get() = Signal0(this, "animation_list_changed")

    /** Signal `animation_libraries_updated()`; see [TypedSignal]. */
    val animationLibrariesUpdated: Signal0
        @JvmName("animationLibrariesUpdatedTypedSignal")
        get() = Signal0(this, "animation_libraries_updated")

    /** Signal `animation_finished(anim_name: StringName)`; see [TypedSignal]. */
    val animationFinished: Signal1<String>
        @JvmName("animationFinishedTypedSignal")
        get() = Signal1(this, "animation_finished", SignalArgType.STRING)

    /** Signal `animation_started(anim_name: StringName)`; see [TypedSignal]. */
    val animationStarted: Signal1<String>
        @JvmName("animationStartedTypedSignal")
        get() = Signal1(this, "animation_started", SignalArgType.STRING)

    /** Signal `caches_cleared()`; see [TypedSignal]. */
    val cachesCleared: Signal0
        @JvmName("cachesClearedTypedSignal")
        get() = Signal0(this, "caches_cleared")

    /** Signal `mixer_applied()`; see [TypedSignal]. */
    val mixerApplied: Signal0
        @JvmName("mixerAppliedTypedSignal")
        get() = Signal0(this, "mixer_applied")

    /** Signal `mixer_updated()`; see [TypedSignal]. */
    val mixerUpdated: Signal0
        @JvmName("mixerUpdatedTypedSignal")
        get() = Signal0(this, "mixer_updated")

    object Signals {
        const val animationListChanged: String = "animation_list_changed"
        const val animationLibrariesUpdated: String = "animation_libraries_updated"
        const val animationFinished: String = "animation_finished"
        const val animationStarted: String = "animation_started"
        const val cachesCleared: String = "caches_cleared"
        const val mixerApplied: String = "mixer_applied"
        const val mixerUpdated: String = "mixer_updated"
    }

    /**
     * Godot's `AnimationMixer.AnimationCallbackModeProcess` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AnimationMixer.AnimationCallbackModeProcess.<NAME>`).
     *
     * Generated from Godot docs: AnimationMixer.AnimationCallbackModeProcess
     */
    @JvmInline
    value class AnimationCallbackModeProcess(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Process animation during physics frames (see `Node.NOTIFICATION_INTERNAL_PHYSICS_PROCESS`). This
             * is especially useful when animating physics bodies.
             *
             * Generated from Godot docs: AnimationMixer.ANIMATION_CALLBACK_MODE_PROCESS_PHYSICS
             */
            val PHYSICS: AnimationCallbackModeProcess get() = AnimationCallbackModeProcess(0L)
            /**
             * Process animation during process frames (see `Node.NOTIFICATION_INTERNAL_PROCESS`).
             *
             * Generated from Godot docs: AnimationMixer.ANIMATION_CALLBACK_MODE_PROCESS_IDLE
             */
            val IDLE: AnimationCallbackModeProcess get() = AnimationCallbackModeProcess(1L)
            /**
             * Do not process animation. Use `advance` to process the animation manually.
             *
             * Generated from Godot docs: AnimationMixer.ANIMATION_CALLBACK_MODE_PROCESS_MANUAL
             */
            val MANUAL: AnimationCallbackModeProcess get() = AnimationCallbackModeProcess(2L)
        }
    }

    /**
     * Godot's `AnimationMixer.AnimationCallbackModeMethod` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AnimationMixer.AnimationCallbackModeMethod.<NAME>`).
     *
     * Generated from Godot docs: AnimationMixer.AnimationCallbackModeMethod
     */
    @JvmInline
    value class AnimationCallbackModeMethod(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Batch method calls during the animation process, then do the calls after events are processed.
             * This avoids bugs involving deleting nodes or modifying the AnimationPlayer while playing.
             *
             * Generated from Godot docs: AnimationMixer.ANIMATION_CALLBACK_MODE_METHOD_DEFERRED
             */
            val DEFERRED: AnimationCallbackModeMethod get() = AnimationCallbackModeMethod(0L)
            /**
             * Make method calls immediately when reached in the animation.
             *
             * Generated from Godot docs: AnimationMixer.ANIMATION_CALLBACK_MODE_METHOD_IMMEDIATE
             */
            val IMMEDIATE: AnimationCallbackModeMethod get() = AnimationCallbackModeMethod(1L)
        }
    }

    /**
     * Godot's `AnimationMixer.AnimationCallbackModeDiscrete` enum as a typed value: `.value` is the
     * raw number Godot uses, and the companion holds the named values
     * (`AnimationMixer.AnimationCallbackModeDiscrete.<NAME>`).
     *
     * Generated from Godot docs: AnimationMixer.AnimationCallbackModeDiscrete
     */
    @JvmInline
    value class AnimationCallbackModeDiscrete(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * An `Animation.UpdateMode.DISCRETE` track value takes precedence when blending
             * `Animation.UpdateMode.CONTINUOUS` or `Animation.UpdateMode.CAPTURE` track values and
             * `Animation.UpdateMode.DISCRETE` track values.
             *
             * Generated from Godot docs: AnimationMixer.ANIMATION_CALLBACK_MODE_DISCRETE_DOMINANT
             */
            val DOMINANT: AnimationCallbackModeDiscrete get() = AnimationCallbackModeDiscrete(0L)
            /**
             * An `Animation.UpdateMode.CONTINUOUS` or `Animation.UpdateMode.CAPTURE` track value takes
             * precedence when blending the `Animation.UpdateMode.CONTINUOUS` or `Animation.UpdateMode.CAPTURE`
             * track values and the `Animation.UpdateMode.DISCRETE` track values. This is the default behavior
             * for `AnimationPlayer`.
             *
             * Generated from Godot docs: AnimationMixer.ANIMATION_CALLBACK_MODE_DISCRETE_RECESSIVE
             */
            val RECESSIVE: AnimationCallbackModeDiscrete get() = AnimationCallbackModeDiscrete(1L)
            /**
             * Always treat the `Animation.UpdateMode.DISCRETE` track value as
             * `Animation.UpdateMode.CONTINUOUS` with `Animation.InterpolationType.NEAREST`. This is the
             * default behavior for `AnimationTree`. If a value track has un-interpolatable type key values, it
             * is internally converted to use `AnimationCallbackModeDiscrete.RECESSIVE` with
             * `Animation.UpdateMode.DISCRETE`. Un-interpolatable type list: - `VariantType.NIL` -
             * `VariantType.NODE_PATH` - `VariantType.RID` - `VariantType.OBJECT` - `VariantType.CALLABLE` -
             * `VariantType.SIGNAL` - `VariantType.DICTIONARY` - `VariantType.PACKED_BYTE_ARRAY`
             * `VariantType.BOOL` and `VariantType.INT` are treated as `VariantType.FLOAT` during blending and
             * rounded when the result is retrieved. It is same for arrays and vectors with them such as
             * `VariantType.PACKED_INT32_ARRAY` or `VariantType.VECTOR2I`, they are treated as
             * `VariantType.PACKED_FLOAT32_ARRAY` or `VariantType.VECTOR2`. Also note that for arrays, the size
             * is also interpolated. `VariantType.STRING` and `VariantType.STRING_NAME` are interpolated
             * between character codes and lengths, but note that there is a difference in algorithm between
             * interpolation between keys and interpolation by blending.
             *
             * Generated from Godot docs: AnimationMixer.ANIMATION_CALLBACK_MODE_DISCRETE_FORCE_CONTINUOUS
             */
            val FORCE_CONTINUOUS: AnimationCallbackModeDiscrete get() = AnimationCallbackModeDiscrete(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationMixer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AnimationMixer? =
            if (handle.address() == 0L) null else AnimationMixer(GodotHandle(handle))
    }

    private object Binds {
        private const val ADD_ANIMATION_LIBRARY_HASH = 618909818L
        @JvmField
        val addAnimationLibraryBind =
            ObjectCalls.getMethodBind("AnimationMixer", "add_animation_library", ADD_ANIMATION_LIBRARY_HASH)

        private const val REMOVE_ANIMATION_LIBRARY_HASH = 3304788590L
        @JvmField
        val removeAnimationLibraryBind =
            ObjectCalls.getMethodBind("AnimationMixer", "remove_animation_library", REMOVE_ANIMATION_LIBRARY_HASH)

        private const val RENAME_ANIMATION_LIBRARY_HASH = 3740211285L
        @JvmField
        val renameAnimationLibraryBind =
            ObjectCalls.getMethodBind("AnimationMixer", "rename_animation_library", RENAME_ANIMATION_LIBRARY_HASH)

        private const val HAS_ANIMATION_LIBRARY_HASH = 2619796661L
        @JvmField
        val hasAnimationLibraryBind =
            ObjectCalls.getMethodBind("AnimationMixer", "has_animation_library", HAS_ANIMATION_LIBRARY_HASH)

        private const val GET_ANIMATION_LIBRARY_HASH = 147342321L
        @JvmField
        val getAnimationLibraryBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_animation_library", GET_ANIMATION_LIBRARY_HASH)

        private const val GET_ANIMATION_LIBRARY_LIST_HASH = 3995934104L
        @JvmField
        val getAnimationLibraryListBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_animation_library_list", GET_ANIMATION_LIBRARY_LIST_HASH)

        private const val HAS_ANIMATION_HASH = 2619796661L
        @JvmField
        val hasAnimationBind =
            ObjectCalls.getMethodBind("AnimationMixer", "has_animation", HAS_ANIMATION_HASH)

        private const val GET_ANIMATION_HASH = 2933122410L
        @JvmField
        val getAnimationBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_animation", GET_ANIMATION_HASH)

        private const val GET_ANIMATION_LIST_HASH = 1139954409L
        @JvmField
        val getAnimationListBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_animation_list", GET_ANIMATION_LIST_HASH)

        private const val SET_ACTIVE_HASH = 2586408642L
        @JvmField
        val setActiveBind =
            ObjectCalls.getMethodBind("AnimationMixer", "set_active", SET_ACTIVE_HASH)

        private const val IS_ACTIVE_HASH = 36873697L
        @JvmField
        val isActiveBind =
            ObjectCalls.getMethodBind("AnimationMixer", "is_active", IS_ACTIVE_HASH)

        private const val SET_DETERMINISTIC_HASH = 2586408642L
        @JvmField
        val setDeterministicBind =
            ObjectCalls.getMethodBind("AnimationMixer", "set_deterministic", SET_DETERMINISTIC_HASH)

        private const val IS_DETERMINISTIC_HASH = 36873697L
        @JvmField
        val isDeterministicBind =
            ObjectCalls.getMethodBind("AnimationMixer", "is_deterministic", IS_DETERMINISTIC_HASH)

        private const val SET_ROOT_NODE_HASH = 1348162250L
        @JvmField
        val setRootNodeBind =
            ObjectCalls.getMethodBind("AnimationMixer", "set_root_node", SET_ROOT_NODE_HASH)

        private const val GET_ROOT_NODE_HASH = 4075236667L
        @JvmField
        val getRootNodeBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_node", GET_ROOT_NODE_HASH)

        private const val SET_CALLBACK_MODE_PROCESS_HASH = 2153733086L
        @JvmField
        val setCallbackModeProcessBind =
            ObjectCalls.getMethodBind("AnimationMixer", "set_callback_mode_process", SET_CALLBACK_MODE_PROCESS_HASH)

        private const val GET_CALLBACK_MODE_PROCESS_HASH = 1394468472L
        @JvmField
        val getCallbackModeProcessBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_callback_mode_process", GET_CALLBACK_MODE_PROCESS_HASH)

        private const val SET_CALLBACK_MODE_METHOD_HASH = 742218271L
        @JvmField
        val setCallbackModeMethodBind =
            ObjectCalls.getMethodBind("AnimationMixer", "set_callback_mode_method", SET_CALLBACK_MODE_METHOD_HASH)

        private const val GET_CALLBACK_MODE_METHOD_HASH = 489449656L
        @JvmField
        val getCallbackModeMethodBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_callback_mode_method", GET_CALLBACK_MODE_METHOD_HASH)

        private const val SET_CALLBACK_MODE_DISCRETE_HASH = 1998944670L
        @JvmField
        val setCallbackModeDiscreteBind =
            ObjectCalls.getMethodBind("AnimationMixer", "set_callback_mode_discrete", SET_CALLBACK_MODE_DISCRETE_HASH)

        private const val GET_CALLBACK_MODE_DISCRETE_HASH = 3493168860L
        @JvmField
        val getCallbackModeDiscreteBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_callback_mode_discrete", GET_CALLBACK_MODE_DISCRETE_HASH)

        private const val SET_AUDIO_MAX_POLYPHONY_HASH = 1286410249L
        @JvmField
        val setAudioMaxPolyphonyBind =
            ObjectCalls.getMethodBind("AnimationMixer", "set_audio_max_polyphony", SET_AUDIO_MAX_POLYPHONY_HASH)

        private const val GET_AUDIO_MAX_POLYPHONY_HASH = 3905245786L
        @JvmField
        val getAudioMaxPolyphonyBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_audio_max_polyphony", GET_AUDIO_MAX_POLYPHONY_HASH)

        private const val SET_ROOT_MOTION_TRACK_HASH = 1348162250L
        @JvmField
        val setRootMotionTrackBind =
            ObjectCalls.getMethodBind("AnimationMixer", "set_root_motion_track", SET_ROOT_MOTION_TRACK_HASH)

        private const val GET_ROOT_MOTION_TRACK_HASH = 4075236667L
        @JvmField
        val getRootMotionTrackBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_track", GET_ROOT_MOTION_TRACK_HASH)

        private const val SET_ROOT_MOTION_LOCAL_HASH = 2586408642L
        @JvmField
        val setRootMotionLocalBind =
            ObjectCalls.getMethodBind("AnimationMixer", "set_root_motion_local", SET_ROOT_MOTION_LOCAL_HASH)

        private const val IS_ROOT_MOTION_LOCAL_HASH = 36873697L
        @JvmField
        val isRootMotionLocalBind =
            ObjectCalls.getMethodBind("AnimationMixer", "is_root_motion_local", IS_ROOT_MOTION_LOCAL_HASH)

        private const val GET_ROOT_MOTION_POSITION_HASH = 3360562783L
        @JvmField
        val getRootMotionPositionBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_position", GET_ROOT_MOTION_POSITION_HASH)

        private const val GET_ROOT_MOTION_ROTATION_HASH = 1222331677L
        @JvmField
        val getRootMotionRotationBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_rotation", GET_ROOT_MOTION_ROTATION_HASH)

        private const val GET_ROOT_MOTION_SCALE_HASH = 3360562783L
        @JvmField
        val getRootMotionScaleBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_scale", GET_ROOT_MOTION_SCALE_HASH)

        private const val GET_ROOT_MOTION_POSITION_ACCUMULATOR_HASH = 3360562783L
        @JvmField
        val getRootMotionPositionAccumulatorBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_position_accumulator", GET_ROOT_MOTION_POSITION_ACCUMULATOR_HASH)

        private const val GET_ROOT_MOTION_ROTATION_ACCUMULATOR_HASH = 1222331677L
        @JvmField
        val getRootMotionRotationAccumulatorBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_rotation_accumulator", GET_ROOT_MOTION_ROTATION_ACCUMULATOR_HASH)

        private const val GET_ROOT_MOTION_SCALE_ACCUMULATOR_HASH = 3360562783L
        @JvmField
        val getRootMotionScaleAccumulatorBind =
            ObjectCalls.getMethodBind("AnimationMixer", "get_root_motion_scale_accumulator", GET_ROOT_MOTION_SCALE_ACCUMULATOR_HASH)

        private const val CLEAR_CACHES_HASH = 3218959716L
        @JvmField
        val clearCachesBind =
            ObjectCalls.getMethodBind("AnimationMixer", "clear_caches", CLEAR_CACHES_HASH)

        private const val ADVANCE_HASH = 373806689L
        @JvmField
        val advanceBind =
            ObjectCalls.getMethodBind("AnimationMixer", "advance", ADVANCE_HASH)

        private const val CAPTURE_HASH = 1333632127L
        @JvmField
        val captureBind =
            ObjectCalls.getMethodBind("AnimationMixer", "capture", CAPTURE_HASH)

        private const val SET_RESET_ON_SAVE_ENABLED_HASH = 2586408642L
        @JvmField
        val setResetOnSaveEnabledBind =
            ObjectCalls.getMethodBind("AnimationMixer", "set_reset_on_save_enabled", SET_RESET_ON_SAVE_ENABLED_HASH)

        private const val IS_RESET_ON_SAVE_ENABLED_HASH = 36873697L
        @JvmField
        val isResetOnSaveEnabledBind =
            ObjectCalls.getMethodBind("AnimationMixer", "is_reset_on_save_enabled", IS_RESET_ON_SAVE_ENABLED_HASH)

        private const val FIND_ANIMATION_HASH = 1559484580L
        @JvmField
        val findAnimationBind =
            ObjectCalls.getMethodBind("AnimationMixer", "find_animation", FIND_ANIMATION_HASH)

        private const val FIND_ANIMATION_LIBRARY_HASH = 1559484580L
        @JvmField
        val findAnimationLibraryBind =
            ObjectCalls.getMethodBind("AnimationMixer", "find_animation_library", FIND_ANIMATION_LIBRARY_HASH)
    }
}
