package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath

/**
 * A node used for animation playback.
 *
 * Generated from Godot docs: AnimationPlayer
 */
class AnimationPlayer(handle: GodotHandle) : AnimationMixer(handle) {
    var currentAnimation: String
        @JvmName("currentAnimationProperty")
        get() = getCurrentAnimation()
        @JvmName("setCurrentAnimationProperty")
        set(value) = setCurrentAnimation(value)

    var assignedAnimation: String
        @JvmName("assignedAnimationProperty")
        get() = getAssignedAnimation()
        @JvmName("setAssignedAnimationProperty")
        set(value) = setAssignedAnimation(value)

    var autoplay: String
        @JvmName("autoplayProperty")
        get() = getAutoplay()
        @JvmName("setAutoplayProperty")
        set(value) = setAutoplay(value)

    val currentAnimationLength: Double
        @JvmName("currentAnimationLengthProperty")
        get() = getCurrentAnimationLength()

    val currentAnimationPosition: Double
        @JvmName("currentAnimationPositionProperty")
        get() = getCurrentAnimationPosition()

    var playbackAutoCapture: Boolean
        @JvmName("playbackAutoCaptureProperty")
        get() = isAutoCapture()
        @JvmName("setPlaybackAutoCaptureProperty")
        set(value) = setAutoCapture(value)

    var playbackAutoCaptureDuration: Double
        @JvmName("playbackAutoCaptureDurationProperty")
        get() = getAutoCaptureDuration()
        @JvmName("setPlaybackAutoCaptureDurationProperty")
        set(value) = setAutoCaptureDuration(value)

    var playbackAutoCaptureTransitionType: Tween.TransitionType
        @JvmName("playbackAutoCaptureTransitionTypeProperty")
        get() = getAutoCaptureTransitionType()
        @JvmName("setPlaybackAutoCaptureTransitionTypeProperty")
        set(value) = setAutoCaptureTransitionType(value)

    var playbackAutoCaptureEaseType: Tween.EaseType
        @JvmName("playbackAutoCaptureEaseTypeProperty")
        get() = getAutoCaptureEaseType()
        @JvmName("setPlaybackAutoCaptureEaseTypeProperty")
        set(value) = setAutoCaptureEaseType(value)

    var playbackDefaultBlendTime: Double
        @JvmName("playbackDefaultBlendTimeProperty")
        get() = getDefaultBlendTime()
        @JvmName("setPlaybackDefaultBlendTimeProperty")
        set(value) = setDefaultBlendTime(value)

    var speedScale: Double
        @JvmName("speedScaleProperty")
        get() = getSpeedScale()
        @JvmName("setSpeedScaleProperty")
        set(value) = setSpeedScale(value)

    var movieQuitOnFinish: Boolean
        @JvmName("movieQuitOnFinishProperty")
        get() = isMovieQuitOnFinishEnabled()
        @JvmName("setMovieQuitOnFinishProperty")
        set(value) = setMovieQuitOnFinishEnabled(value)

    /**
     * Triggers the `animation_to` animation when the `animation_from` animation completes.
     *
     * Generated from Godot docs: AnimationPlayer.animation_set_next
     */
    fun animationSetNext(animationFrom: String, animationTo: String) {
        ObjectCalls.ptrcallWithTwoStringNameArgs(Binds.animationSetNextBind, segment, animationFrom, animationTo)
    }

    /**
     * Returns the key of the animation which is queued to play after the `animation_from` animation.
     *
     * Generated from Godot docs: AnimationPlayer.animation_get_next
     */
    fun animationGetNext(animationFrom: String): String {
        return ObjectCalls.ptrcallWithStringNameArgRetStringName(Binds.animationGetNextBind, segment, animationFrom)
    }

    /**
     * Specifies a blend time (in seconds) between two animations, referenced by their keys.
     *
     * Generated from Godot docs: AnimationPlayer.set_blend_time
     */
    fun setBlendTime(animationFrom: String, animationTo: String, sec: Double) {
        ObjectCalls.ptrcallWithTwoStringNameAndDoubleArg(Binds.setBlendTimeBind, segment, animationFrom, animationTo, sec)
    }

    /**
     * Returns the blend time (in seconds) between two animations, referenced by their keys.
     *
     * Generated from Godot docs: AnimationPlayer.get_blend_time
     */
    fun getBlendTime(animationFrom: String, animationTo: String): Double {
        return ObjectCalls.ptrcallWithTwoStringNameArgsRetDouble(Binds.getBlendTimeBind, segment, animationFrom, animationTo)
    }

    /**
     * The default time in which to blend animations. Ranges from 0 to 4096 with 0.01 precision.
     *
     * Generated from Godot docs: AnimationPlayer.set_default_blend_time
     */
    fun setDefaultBlendTime(sec: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDefaultBlendTimeBind, segment, sec)
    }

    /**
     * The default time in which to blend animations. Ranges from 0 to 4096 with 0.01 precision.
     *
     * Generated from Godot docs: AnimationPlayer.get_default_blend_time
     */
    fun getDefaultBlendTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDefaultBlendTimeBind, segment)
    }

    /**
     * If `true`, performs `AnimationMixer.capture` before playback automatically. This means just
     * `play_with_capture` is executed with default arguments instead of `play`. Note: Capture
     * interpolation is only performed if the animation contains a capture track. See also
     * `Animation.UpdateMode.CAPTURE`.
     *
     * Generated from Godot docs: AnimationPlayer.set_auto_capture
     */
    fun setAutoCapture(autoCapture: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAutoCaptureBind, segment, autoCapture)
    }

    /**
     * If `true`, performs `AnimationMixer.capture` before playback automatically. This means just
     * `play_with_capture` is executed with default arguments instead of `play`. Note: Capture
     * interpolation is only performed if the animation contains a capture track. See also
     * `Animation.UpdateMode.CAPTURE`.
     *
     * Generated from Godot docs: AnimationPlayer.is_auto_capture
     */
    fun isAutoCapture(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAutoCaptureBind, segment)
    }

    /**
     * See also `play_with_capture` and `AnimationMixer.capture`. If `playback_auto_capture_duration`
     * is negative value, the duration is set to the interval between the current position and the
     * first key.
     *
     * Generated from Godot docs: AnimationPlayer.set_auto_capture_duration
     */
    fun setAutoCaptureDuration(autoCaptureDuration: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAutoCaptureDurationBind, segment, autoCaptureDuration)
    }

    /**
     * See also `play_with_capture` and `AnimationMixer.capture`. If `playback_auto_capture_duration`
     * is negative value, the duration is set to the interval between the current position and the
     * first key.
     *
     * Generated from Godot docs: AnimationPlayer.get_auto_capture_duration
     */
    fun getAutoCaptureDuration(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAutoCaptureDurationBind, segment)
    }

    /**
     * The transition type of the capture interpolation. See also `Tween.TransitionType`.
     *
     * Generated from Godot docs: AnimationPlayer.set_auto_capture_transition_type
     */
    fun setAutoCaptureTransitionType(autoCaptureTransitionType: Tween.TransitionType) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAutoCaptureTransitionTypeBind, segment, autoCaptureTransitionType.value)
    }

    /**
     * The transition type of the capture interpolation. See also `Tween.TransitionType`.
     *
     * Generated from Godot docs: AnimationPlayer.get_auto_capture_transition_type
     */
    fun getAutoCaptureTransitionType(): Tween.TransitionType {
        return Tween.TransitionType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAutoCaptureTransitionTypeBind, segment))
    }

    /**
     * The ease type of the capture interpolation. See also `Tween.EaseType`.
     *
     * Generated from Godot docs: AnimationPlayer.set_auto_capture_ease_type
     */
    fun setAutoCaptureEaseType(autoCaptureEaseType: Tween.EaseType) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAutoCaptureEaseTypeBind, segment, autoCaptureEaseType.value)
    }

    /**
     * The ease type of the capture interpolation. See also `Tween.EaseType`.
     *
     * Generated from Godot docs: AnimationPlayer.get_auto_capture_ease_type
     */
    fun getAutoCaptureEaseType(): Tween.EaseType {
        return Tween.EaseType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAutoCaptureEaseTypeBind, segment))
    }

    /**
     * Plays the animation with key `name`. Custom blend times and speed can be set. The `from_end`
     * option only affects when switching to a new animation track, or if the same track but at the
     * start or end. It does not affect resuming playback that was paused in the middle of an
     * animation. If `custom_speed` is negative and `from_end` is `true`, the animation will play
     * backwards (which is equivalent to calling `play_backwards`). The `AnimationPlayer` keeps track
     * of its current or last played animation with `assigned_animation`. If this method is called with
     * that same animation `name`, or with no `name` parameter, the assigned animation will resume
     * playing if it was paused. Note: The animation will be updated the next time the
     * `AnimationPlayer` is processed. If other variables are updated at the same time this is called,
     * they may be updated too early. To perform the update immediately, call `advance(0)`.
     *
     * Generated from Godot docs: AnimationPlayer.play
     */
    fun play(name: String = "", customBlend: Double = -1.0, customSpeed: Double = 1.0, fromEnd: Boolean = false) {
        ObjectCalls.ptrcallWithStringNameDoubleDoubleBoolArgs(Binds.playBind, segment, name, customBlend, customSpeed, fromEnd)
    }

    /**
     * Plays the animation with key `name` and the section starting from `start_marker` and ending on
     * `end_marker`. If the start marker is empty, the section starts from the beginning of the
     * animation. If the end marker is empty, the section ends on the end of the animation. See also
     * `play`.
     *
     * Generated from Godot docs: AnimationPlayer.play_section_with_markers
     */
    fun playSectionWithMarkers(name: String = "", startMarker: String = "", endMarker: String = "", customBlend: Double = -1.0, customSpeed: Double = 1.0, fromEnd: Boolean = false) {
        ObjectCalls.ptrcallWithThreeStringNameTwoDoubleBoolArgs(Binds.playSectionWithMarkersBind, segment, name, startMarker, endMarker, customBlend, customSpeed, fromEnd)
    }

    /**
     * Plays the animation with key `name` and the section starting from `start_time` and ending on
     * `end_time`. See also `play`. Setting `start_time` to a value outside the range of the animation
     * means the start of the animation will be used instead, and setting `end_time` to a value outside
     * the range of the animation means the end of the animation will be used instead. `start_time`
     * cannot be equal to `end_time`.
     *
     * Generated from Godot docs: AnimationPlayer.play_section
     */
    fun playSection(name: String = "", startTime: Double = -1.0, endTime: Double = -1.0, customBlend: Double = -1.0, customSpeed: Double = 1.0, fromEnd: Boolean = false) {
        ObjectCalls.ptrcallWithStringNameFourDoubleBoolArgs(Binds.playSectionBind, segment, name, startTime, endTime, customBlend, customSpeed, fromEnd)
    }

    /**
     * Plays the animation with key `name` in reverse. This method is a shorthand for `play` with
     * `custom_speed = -1.0` and `from_end = true`, so see its description for more information.
     *
     * Generated from Godot docs: AnimationPlayer.play_backwards
     */
    fun playBackwards(name: String = "", customBlend: Double = -1.0) {
        ObjectCalls.ptrcallWithStringNameAndDoubleArg(Binds.playBackwardsBind, segment, name, customBlend)
    }

    /**
     * Plays the animation with key `name` and the section starting from `start_marker` and ending on
     * `end_marker` in reverse. This method is a shorthand for `play_section_with_markers` with
     * `custom_speed = -1.0` and `from_end = true`, see its description for more information.
     *
     * Generated from Godot docs: AnimationPlayer.play_section_with_markers_backwards
     */
    fun playSectionWithMarkersBackwards(name: String = "", startMarker: String = "", endMarker: String = "", customBlend: Double = -1.0) {
        ObjectCalls.ptrcallWithThreeStringNameAndDoubleArg(Binds.playSectionWithMarkersBackwardsBind, segment, name, startMarker, endMarker, customBlend)
    }

    /**
     * Plays the animation with key `name` and the section starting from `start_time` and ending on
     * `end_time` in reverse. This method is a shorthand for `play_section` with `custom_speed = -1.0`
     * and `from_end = true`, see its description for more information.
     *
     * Generated from Godot docs: AnimationPlayer.play_section_backwards
     */
    fun playSectionBackwards(name: String = "", startTime: Double = -1.0, endTime: Double = -1.0, customBlend: Double = -1.0) {
        ObjectCalls.ptrcallWithStringNameAndThreeDoubleArgs(Binds.playSectionBackwardsBind, segment, name, startTime, endTime, customBlend)
    }

    /**
     * See also `AnimationMixer.capture`. You can use this method to use more detailed options for
     * capture than those performed by `playback_auto_capture`.
     *
     * Generated from Godot docs: AnimationPlayer.play_with_capture
     */
    fun playWithCapture(name: String = "", duration: Double = -1.0, customBlend: Double = -1.0, customSpeed: Double = 1.0, fromEnd: Boolean = false, transType: Tween.TransitionType = Tween.TransitionType.LINEAR, easeType: Tween.EaseType = Tween.EaseType.IN) {
        ObjectCalls.ptrcallWithStringNameThreeDoubleBoolTwoLongArgs(Binds.playWithCaptureBind, segment, name, duration, customBlend, customSpeed, fromEnd, transType.value, easeType.value)
    }

    /**
     * Pauses the currently playing animation. The `current_animation_position` will be kept and
     * calling `play` or `play_backwards` without arguments or with the same animation name as
     * `assigned_animation` will resume the animation. See also `stop`.
     *
     * Generated from Godot docs: AnimationPlayer.pause
     */
    fun pause() {
        ObjectCalls.ptrcallNoArgs(Binds.pauseBind, segment)
    }

    /**
     * Stops the currently playing animation. The animation position is reset to `0` and the
     * `custom_speed` is reset to `1.0`. See also `pause`. If `keep_state` is `true`, the animation
     * state is not updated visually. Note: The method / audio / animation playback tracks will not be
     * processed by this method.
     *
     * Generated from Godot docs: AnimationPlayer.stop
     */
    fun stop(keepState: Boolean = false) {
        ObjectCalls.ptrcallWithBoolArg(Binds.stopBind, segment, keepState)
    }

    /**
     * Returns `true` if an animation is currently playing (even if `speed_scale` and/or `custom_speed`
     * are `0`).
     *
     * Generated from Godot docs: AnimationPlayer.is_playing
     */
    fun isPlaying(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isPlayingBind, segment)
    }

    /**
     * Returns `true` if the an animation is currently active. An animation is active if it was played
     * by calling `play` and was not finished yet, or was stopped by calling `stop`. This can be used
     * to check whether an animation is currently paused or stopped.
     *
     * Generated from Godot docs: AnimationPlayer.is_animation_active
     */
    fun isAnimationActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAnimationActiveBind, segment)
    }

    /**
     * The key of the currently playing animation. If no animation is playing, the property's value is
     * an empty string. Changing this value does not restart the animation. See `play` for more
     * information on playing animations. Note: While this property appears in the Inspector, it's not
     * meant to be edited, and it's not saved in the scene. This property is mainly used to get the
     * currently playing animation, and internally for animation playback tracks. For more information,
     * see `Animation`.
     *
     * Generated from Godot docs: AnimationPlayer.set_current_animation
     */
    fun setCurrentAnimation(animation: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.setCurrentAnimationBind, segment, animation)
    }

    /**
     * The key of the currently playing animation. If no animation is playing, the property's value is
     * an empty string. Changing this value does not restart the animation. See `play` for more
     * information on playing animations. Note: While this property appears in the Inspector, it's not
     * meant to be edited, and it's not saved in the scene. This property is mainly used to get the
     * currently playing animation, and internally for animation playback tracks. For more information,
     * see `Animation`.
     *
     * Generated from Godot docs: AnimationPlayer.get_current_animation
     */
    fun getCurrentAnimation(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getCurrentAnimationBind, segment)
    }

    /**
     * If playing, the current animation's key, otherwise, the animation last played. When set, this
     * changes the animation, but will not play it unless already playing. See also
     * `current_animation`.
     *
     * Generated from Godot docs: AnimationPlayer.set_assigned_animation
     */
    fun setAssignedAnimation(animation: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.setAssignedAnimationBind, segment, animation)
    }

    /**
     * If playing, the current animation's key, otherwise, the animation last played. When set, this
     * changes the animation, but will not play it unless already playing. See also
     * `current_animation`.
     *
     * Generated from Godot docs: AnimationPlayer.get_assigned_animation
     */
    fun getAssignedAnimation(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getAssignedAnimationBind, segment)
    }

    /**
     * Queues an animation for playback once the current animation and all previously queued animations
     * are done. Note: If a looped animation is currently playing, the queued animation will never play
     * unless the looped animation is stopped somehow.
     *
     * Generated from Godot docs: AnimationPlayer.queue
     */
    fun queue(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.queueBind, segment, name)
    }

    /**
     * Returns a list of the animation keys that are currently queued to play.
     *
     * Generated from Godot docs: AnimationPlayer.get_queue
     */
    fun getQueue(): List<String> {
        return ObjectCalls.ptrcallNoArgsRetStringNameList(Binds.getQueueBind, segment)
    }

    /**
     * Clears all queued, unplayed animations.
     *
     * Generated from Godot docs: AnimationPlayer.clear_queue
     */
    fun clearQueue() {
        ObjectCalls.ptrcallNoArgs(Binds.clearQueueBind, segment)
    }

    /**
     * The speed scaling ratio. For example, if this value is `1`, then the animation plays at normal
     * speed. If it's `0.5`, then it plays at half speed. If it's `2`, then it plays at double speed.
     * If set to a negative value, the animation is played in reverse. If set to `0`, the animation
     * will not advance.
     *
     * Generated from Godot docs: AnimationPlayer.set_speed_scale
     */
    fun setSpeedScale(speed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSpeedScaleBind, segment, speed)
    }

    /**
     * The speed scaling ratio. For example, if this value is `1`, then the animation plays at normal
     * speed. If it's `0.5`, then it plays at half speed. If it's `2`, then it plays at double speed.
     * If set to a negative value, the animation is played in reverse. If set to `0`, the animation
     * will not advance.
     *
     * Generated from Godot docs: AnimationPlayer.get_speed_scale
     */
    fun getSpeedScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSpeedScaleBind, segment)
    }

    /**
     * Returns the actual playing speed of current animation or `0` if not playing. This speed is the
     * `speed_scale` property multiplied by `custom_speed` argument specified when calling the `play`
     * method. Returns a negative value if the current animation is playing backwards.
     *
     * Generated from Godot docs: AnimationPlayer.get_playing_speed
     */
    fun getPlayingSpeed(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPlayingSpeedBind, segment)
    }

    /**
     * The key of the animation to play when the scene loads.
     *
     * Generated from Godot docs: AnimationPlayer.set_autoplay
     */
    fun setAutoplay(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(Binds.setAutoplayBind, segment, name)
    }

    /**
     * The key of the animation to play when the scene loads.
     *
     * Generated from Godot docs: AnimationPlayer.get_autoplay
     */
    fun getAutoplay(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getAutoplayBind, segment)
    }

    /**
     * If `true` and the engine is running in Movie Maker mode (see `MovieWriter`), exits the engine
     * with `SceneTree.quit` as soon as an animation is done playing in this `AnimationPlayer`. A
     * message is printed when the engine quits for this reason. Note: This obeys the same logic as the
     * `AnimationMixer.animation_finished` signal, so it will not quit the engine if the animation is
     * set to be looping.
     *
     * Generated from Godot docs: AnimationPlayer.set_movie_quit_on_finish_enabled
     */
    fun setMovieQuitOnFinishEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setMovieQuitOnFinishEnabledBind, segment, enabled)
    }

    /**
     * If `true` and the engine is running in Movie Maker mode (see `MovieWriter`), exits the engine
     * with `SceneTree.quit` as soon as an animation is done playing in this `AnimationPlayer`. A
     * message is printed when the engine quits for this reason. Note: This obeys the same logic as the
     * `AnimationMixer.animation_finished` signal, so it will not quit the engine if the animation is
     * set to be looping.
     *
     * Generated from Godot docs: AnimationPlayer.is_movie_quit_on_finish_enabled
     */
    fun isMovieQuitOnFinishEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isMovieQuitOnFinishEnabledBind, segment)
    }

    /**
     * The position (in seconds) of the currently playing animation.
     *
     * Generated from Godot docs: AnimationPlayer.get_current_animation_position
     */
    fun getCurrentAnimationPosition(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCurrentAnimationPositionBind, segment)
    }

    /**
     * The length (in seconds) of the currently playing animation.
     *
     * Generated from Godot docs: AnimationPlayer.get_current_animation_length
     */
    fun getCurrentAnimationLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCurrentAnimationLengthBind, segment)
    }

    /**
     * Changes the start and end markers of the section being played. The current playback position
     * will be clamped within the new section. See also `play_section_with_markers`. If the argument is
     * empty, the section uses the beginning or end of the animation. If both are empty, it means that
     * the section is not set.
     *
     * Generated from Godot docs: AnimationPlayer.set_section_with_markers
     */
    fun setSectionWithMarkers(startMarker: String = "", endMarker: String = "") {
        ObjectCalls.ptrcallWithTwoStringNameArgs(Binds.setSectionWithMarkersBind, segment, startMarker, endMarker)
    }

    /**
     * Changes the start and end times of the section being played. The current playback position will
     * be clamped within the new section. See also `play_section`.
     *
     * Generated from Godot docs: AnimationPlayer.set_section
     */
    fun setSection(startTime: Double = -1.0, endTime: Double = -1.0) {
        ObjectCalls.ptrcallWithTwoDoubleArgs(Binds.setSectionBind, segment, startTime, endTime)
    }

    /**
     * Resets the current section. Does nothing if a section has not been set.
     *
     * Generated from Godot docs: AnimationPlayer.reset_section
     */
    fun resetSection() {
        ObjectCalls.ptrcallNoArgs(Binds.resetSectionBind, segment)
    }

    /**
     * Returns the start time of the section currently being played.
     *
     * Generated from Godot docs: AnimationPlayer.get_section_start_time
     */
    fun getSectionStartTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSectionStartTimeBind, segment)
    }

    /**
     * Returns the end time of the section currently being played.
     *
     * Generated from Godot docs: AnimationPlayer.get_section_end_time
     */
    fun getSectionEndTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSectionEndTimeBind, segment)
    }

    /**
     * Returns `true` if an animation is currently playing with a section.
     *
     * Generated from Godot docs: AnimationPlayer.has_section
     */
    fun hasSection(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasSectionBind, segment)
    }

    /**
     * Seeks the animation to the `seconds` point in time (in seconds). If `update` is `true`, the
     * animation updates too, otherwise it updates at process time. Events between the current frame
     * and `seconds` are skipped. If `update_only` is `true`, the method / audio / animation playback
     * tracks will not be processed. Note: Seeking to the end of the animation doesn't emit
     * `AnimationMixer.animation_finished`. If you want to skip animation and emit the signal, use
     * `AnimationMixer.advance`.
     *
     * Generated from Godot docs: AnimationPlayer.seek
     */
    fun seek(seconds: Double, update: Boolean = false, updateOnly: Boolean = false) {
        ObjectCalls.ptrcallWithDoubleAndTwoBoolArgs(Binds.seekBind, segment, seconds, update, updateOnly)
    }

    /**
     * Sets the process notification in which to update animations.
     *
     * Generated from Godot docs: AnimationPlayer.set_process_callback
     */
    fun setProcessCallback(mode: AnimationPlayer.AnimationProcessCallback) {
        ObjectCalls.ptrcallWithLongArg(Binds.setProcessCallbackBind, segment, mode.value)
    }

    /**
     * Returns the process notification in which to update animations.
     *
     * Generated from Godot docs: AnimationPlayer.get_process_callback
     */
    fun getProcessCallback(): AnimationPlayer.AnimationProcessCallback {
        return AnimationPlayer.AnimationProcessCallback(ObjectCalls.ptrcallNoArgsRetLong(Binds.getProcessCallbackBind, segment))
    }

    /**
     * Sets the call mode used for "Call Method" tracks.
     *
     * Generated from Godot docs: AnimationPlayer.set_method_call_mode
     */
    fun setMethodCallMode(mode: AnimationPlayer.AnimationMethodCallMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setMethodCallModeBind, segment, mode.value)
    }

    /**
     * Returns the call mode used for "Call Method" tracks.
     *
     * Generated from Godot docs: AnimationPlayer.get_method_call_mode
     */
    fun getMethodCallMode(): AnimationPlayer.AnimationMethodCallMode {
        return AnimationPlayer.AnimationMethodCallMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMethodCallModeBind, segment))
    }

    /**
     * Sets the node which node path references will travel from.
     *
     * Generated from Godot docs: AnimationPlayer.set_root
     */
    fun setRoot(path: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setRootBind, segment, path)
    }

    /**
     * Returns the node which node path references will travel from.
     *
     * Generated from Godot docs: AnimationPlayer.get_root
     */
    fun getRoot(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getRootBind, segment)
    }

    /** Signal `current_animation_changed(anim_name: StringName)`; see [TypedSignal]. */
    val currentAnimationChanged: Signal1<String>
        @JvmName("currentAnimationChangedTypedSignal")
        get() = Signal1(this, "current_animation_changed", SignalArgType.STRING)

    /** Signal `animation_changed(old_name: StringName, new_name: StringName)`; see [TypedSignal]. */
    val animationChanged: Signal2<String, String>
        @JvmName("animationChangedTypedSignal")
        get() = Signal2(this, "animation_changed", SignalArgType.STRING, SignalArgType.STRING)

    object Signals {
        const val currentAnimationChanged: String = "current_animation_changed"
        const val animationChanged: String = "animation_changed"
    }

    /**
     * Godot's `AnimationPlayer.AnimationProcessCallback` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AnimationPlayer.AnimationProcessCallback.<NAME>`).
     *
     * Generated from Godot docs: AnimationPlayer.AnimationProcessCallback
     */
    @JvmInline
    value class AnimationProcessCallback(override val value: Long) : GodotEnumValue {
        companion object {
            val PHYSICS: AnimationProcessCallback get() = AnimationProcessCallback(0L)
            val IDLE: AnimationProcessCallback get() = AnimationProcessCallback(1L)
            val MANUAL: AnimationProcessCallback get() = AnimationProcessCallback(2L)
        }
    }

    /**
     * Godot's `AnimationPlayer.AnimationMethodCallMode` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AnimationPlayer.AnimationMethodCallMode.<NAME>`).
     *
     * Generated from Godot docs: AnimationPlayer.AnimationMethodCallMode
     */
    @JvmInline
    value class AnimationMethodCallMode(override val value: Long) : GodotEnumValue {
        companion object {
            val DEFERRED: AnimationMethodCallMode get() = AnimationMethodCallMode(0L)
            val IMMEDIATE: AnimationMethodCallMode get() = AnimationMethodCallMode(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationPlayer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AnimationPlayer? =
            if (handle.address() == 0L) null else AnimationPlayer(GodotHandle(handle))
    }

    private object Binds {
        private const val ANIMATION_SET_NEXT_HASH = 3740211285L
        @JvmField
        val animationSetNextBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "animation_set_next", ANIMATION_SET_NEXT_HASH)

        private const val ANIMATION_GET_NEXT_HASH = 1965194235L
        @JvmField
        val animationGetNextBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "animation_get_next", ANIMATION_GET_NEXT_HASH)

        private const val SET_BLEND_TIME_HASH = 3231131886L
        @JvmField
        val setBlendTimeBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_blend_time", SET_BLEND_TIME_HASH)

        private const val GET_BLEND_TIME_HASH = 1958752504L
        @JvmField
        val getBlendTimeBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_blend_time", GET_BLEND_TIME_HASH)

        private const val SET_DEFAULT_BLEND_TIME_HASH = 373806689L
        @JvmField
        val setDefaultBlendTimeBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_default_blend_time", SET_DEFAULT_BLEND_TIME_HASH)

        private const val GET_DEFAULT_BLEND_TIME_HASH = 1740695150L
        @JvmField
        val getDefaultBlendTimeBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_default_blend_time", GET_DEFAULT_BLEND_TIME_HASH)

        private const val SET_AUTO_CAPTURE_HASH = 2586408642L
        @JvmField
        val setAutoCaptureBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_auto_capture", SET_AUTO_CAPTURE_HASH)

        private const val IS_AUTO_CAPTURE_HASH = 36873697L
        @JvmField
        val isAutoCaptureBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "is_auto_capture", IS_AUTO_CAPTURE_HASH)

        private const val SET_AUTO_CAPTURE_DURATION_HASH = 373806689L
        @JvmField
        val setAutoCaptureDurationBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_auto_capture_duration", SET_AUTO_CAPTURE_DURATION_HASH)

        private const val GET_AUTO_CAPTURE_DURATION_HASH = 1740695150L
        @JvmField
        val getAutoCaptureDurationBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_auto_capture_duration", GET_AUTO_CAPTURE_DURATION_HASH)

        private const val SET_AUTO_CAPTURE_TRANSITION_TYPE_HASH = 1058637742L
        @JvmField
        val setAutoCaptureTransitionTypeBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_auto_capture_transition_type", SET_AUTO_CAPTURE_TRANSITION_TYPE_HASH)

        private const val GET_AUTO_CAPTURE_TRANSITION_TYPE_HASH = 3842314528L
        @JvmField
        val getAutoCaptureTransitionTypeBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_auto_capture_transition_type", GET_AUTO_CAPTURE_TRANSITION_TYPE_HASH)

        private const val SET_AUTO_CAPTURE_EASE_TYPE_HASH = 1208105857L
        @JvmField
        val setAutoCaptureEaseTypeBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_auto_capture_ease_type", SET_AUTO_CAPTURE_EASE_TYPE_HASH)

        private const val GET_AUTO_CAPTURE_EASE_TYPE_HASH = 631880200L
        @JvmField
        val getAutoCaptureEaseTypeBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_auto_capture_ease_type", GET_AUTO_CAPTURE_EASE_TYPE_HASH)

        private const val PLAY_HASH = 3118260607L
        @JvmField
        val playBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "play", PLAY_HASH)

        private const val PLAY_SECTION_WITH_MARKERS_HASH = 1421431412L
        @JvmField
        val playSectionWithMarkersBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "play_section_with_markers", PLAY_SECTION_WITH_MARKERS_HASH)

        private const val PLAY_SECTION_HASH = 284774635L
        @JvmField
        val playSectionBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "play_section", PLAY_SECTION_HASH)

        private const val PLAY_BACKWARDS_HASH = 2787282401L
        @JvmField
        val playBackwardsBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "play_backwards", PLAY_BACKWARDS_HASH)

        private const val PLAY_SECTION_WITH_MARKERS_BACKWARDS_HASH = 910195100L
        @JvmField
        val playSectionWithMarkersBackwardsBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "play_section_with_markers_backwards", PLAY_SECTION_WITH_MARKERS_BACKWARDS_HASH)

        private const val PLAY_SECTION_BACKWARDS_HASH = 831955981L
        @JvmField
        val playSectionBackwardsBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "play_section_backwards", PLAY_SECTION_BACKWARDS_HASH)

        private const val PLAY_WITH_CAPTURE_HASH = 1572969103L
        @JvmField
        val playWithCaptureBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "play_with_capture", PLAY_WITH_CAPTURE_HASH)

        private const val PAUSE_HASH = 3218959716L
        @JvmField
        val pauseBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "pause", PAUSE_HASH)

        private const val STOP_HASH = 107499316L
        @JvmField
        val stopBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "stop", STOP_HASH)

        private const val IS_PLAYING_HASH = 36873697L
        @JvmField
        val isPlayingBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "is_playing", IS_PLAYING_HASH)

        private const val IS_ANIMATION_ACTIVE_HASH = 36873697L
        @JvmField
        val isAnimationActiveBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "is_animation_active", IS_ANIMATION_ACTIVE_HASH)

        private const val SET_CURRENT_ANIMATION_HASH = 3304788590L
        @JvmField
        val setCurrentAnimationBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_current_animation", SET_CURRENT_ANIMATION_HASH)

        private const val GET_CURRENT_ANIMATION_HASH = 2002593661L
        @JvmField
        val getCurrentAnimationBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_current_animation", GET_CURRENT_ANIMATION_HASH)

        private const val SET_ASSIGNED_ANIMATION_HASH = 3304788590L
        @JvmField
        val setAssignedAnimationBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_assigned_animation", SET_ASSIGNED_ANIMATION_HASH)

        private const val GET_ASSIGNED_ANIMATION_HASH = 2002593661L
        @JvmField
        val getAssignedAnimationBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_assigned_animation", GET_ASSIGNED_ANIMATION_HASH)

        private const val QUEUE_HASH = 3304788590L
        @JvmField
        val queueBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "queue", QUEUE_HASH)

        private const val GET_QUEUE_HASH = 2915620761L
        @JvmField
        val getQueueBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_queue", GET_QUEUE_HASH)

        private const val CLEAR_QUEUE_HASH = 3218959716L
        @JvmField
        val clearQueueBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "clear_queue", CLEAR_QUEUE_HASH)

        private const val SET_SPEED_SCALE_HASH = 373806689L
        @JvmField
        val setSpeedScaleBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_speed_scale", SET_SPEED_SCALE_HASH)

        private const val GET_SPEED_SCALE_HASH = 1740695150L
        @JvmField
        val getSpeedScaleBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_speed_scale", GET_SPEED_SCALE_HASH)

        private const val GET_PLAYING_SPEED_HASH = 1740695150L
        @JvmField
        val getPlayingSpeedBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_playing_speed", GET_PLAYING_SPEED_HASH)

        private const val SET_AUTOPLAY_HASH = 3304788590L
        @JvmField
        val setAutoplayBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_autoplay", SET_AUTOPLAY_HASH)

        private const val GET_AUTOPLAY_HASH = 2002593661L
        @JvmField
        val getAutoplayBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_autoplay", GET_AUTOPLAY_HASH)

        private const val SET_MOVIE_QUIT_ON_FINISH_ENABLED_HASH = 2586408642L
        @JvmField
        val setMovieQuitOnFinishEnabledBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_movie_quit_on_finish_enabled", SET_MOVIE_QUIT_ON_FINISH_ENABLED_HASH)

        private const val IS_MOVIE_QUIT_ON_FINISH_ENABLED_HASH = 36873697L
        @JvmField
        val isMovieQuitOnFinishEnabledBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "is_movie_quit_on_finish_enabled", IS_MOVIE_QUIT_ON_FINISH_ENABLED_HASH)

        private const val GET_CURRENT_ANIMATION_POSITION_HASH = 1740695150L
        @JvmField
        val getCurrentAnimationPositionBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_current_animation_position", GET_CURRENT_ANIMATION_POSITION_HASH)

        private const val GET_CURRENT_ANIMATION_LENGTH_HASH = 1740695150L
        @JvmField
        val getCurrentAnimationLengthBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_current_animation_length", GET_CURRENT_ANIMATION_LENGTH_HASH)

        private const val SET_SECTION_WITH_MARKERS_HASH = 794792241L
        @JvmField
        val setSectionWithMarkersBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_section_with_markers", SET_SECTION_WITH_MARKERS_HASH)

        private const val SET_SECTION_HASH = 3749779719L
        @JvmField
        val setSectionBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_section", SET_SECTION_HASH)

        private const val RESET_SECTION_HASH = 3218959716L
        @JvmField
        val resetSectionBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "reset_section", RESET_SECTION_HASH)

        private const val GET_SECTION_START_TIME_HASH = 1740695150L
        @JvmField
        val getSectionStartTimeBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_section_start_time", GET_SECTION_START_TIME_HASH)

        private const val GET_SECTION_END_TIME_HASH = 1740695150L
        @JvmField
        val getSectionEndTimeBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_section_end_time", GET_SECTION_END_TIME_HASH)

        private const val HAS_SECTION_HASH = 36873697L
        @JvmField
        val hasSectionBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "has_section", HAS_SECTION_HASH)

        private const val SEEK_HASH = 1807872683L
        @JvmField
        val seekBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "seek", SEEK_HASH)

        private const val SET_PROCESS_CALLBACK_HASH = 1663839457L
        @JvmField
        val setProcessCallbackBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_process_callback", SET_PROCESS_CALLBACK_HASH)

        private const val GET_PROCESS_CALLBACK_HASH = 4207496604L
        @JvmField
        val getProcessCallbackBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_process_callback", GET_PROCESS_CALLBACK_HASH)

        private const val SET_METHOD_CALL_MODE_HASH = 3413514846L
        @JvmField
        val setMethodCallModeBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_method_call_mode", SET_METHOD_CALL_MODE_HASH)

        private const val GET_METHOD_CALL_MODE_HASH = 3583380054L
        @JvmField
        val getMethodCallModeBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_method_call_mode", GET_METHOD_CALL_MODE_HASH)

        private const val SET_ROOT_HASH = 1348162250L
        @JvmField
        val setRootBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "set_root", SET_ROOT_HASH)

        private const val GET_ROOT_HASH = 4075236667L
        @JvmField
        val getRootBind =
            ObjectCalls.getMethodBind("AnimationPlayer", "get_root", GET_ROOT_HASH)
    }
}
