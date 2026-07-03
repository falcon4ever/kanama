package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * An input animation for an `AnimationNodeBlendTree`.
 *
 * Generated from Godot docs: AnimationNodeAnimation
 */
class AnimationNodeAnimation(handle: MemorySegment) : AnimationRootNode(handle) {
    var animation: String
        @JvmName("animationProperty")
        get() = getAnimation()
        @JvmName("setAnimationProperty")
        set(value) = setAnimation(value)

    var playMode: Long
        @JvmName("playModeProperty")
        get() = getPlayMode()
        @JvmName("setPlayModeProperty")
        set(value) = setPlayMode(value)

    var advanceOnStart: Boolean
        @JvmName("advanceOnStartProperty")
        get() = isAdvanceOnStart()
        @JvmName("setAdvanceOnStartProperty")
        set(value) = setAdvanceOnStart(value)

    var useCustomTimeline: Boolean
        @JvmName("useCustomTimelineProperty")
        get() = isUsingCustomTimeline()
        @JvmName("setUseCustomTimelineProperty")
        set(value) = setUseCustomTimeline(value)

    var timelineLength: Double
        @JvmName("timelineLengthProperty")
        get() = getTimelineLength()
        @JvmName("setTimelineLengthProperty")
        set(value) = setTimelineLength(value)

    var stretchTimeScale: Boolean
        @JvmName("stretchTimeScaleProperty")
        get() = isStretchingTimeScale()
        @JvmName("setStretchTimeScaleProperty")
        set(value) = setStretchTimeScale(value)

    var startOffset: Double
        @JvmName("startOffsetProperty")
        get() = getStartOffset()
        @JvmName("setStartOffsetProperty")
        set(value) = setStartOffset(value)

    var loopMode: Long
        @JvmName("loopModeProperty")
        get() = getLoopMode()
        @JvmName("setLoopModeProperty")
        set(value) = setLoopMode(value)

    /**
     * Animation to use as an output. It is one of the animations provided by
     * `AnimationTree.anim_player`.
     *
     * Generated from Godot docs: AnimationNodeAnimation.set_animation
     */
    fun setAnimation(name: String) {
        ObjectCalls.ptrcallWithStringNameArg(setAnimationBind, handle, name)
    }

    /**
     * Animation to use as an output. It is one of the animations provided by
     * `AnimationTree.anim_player`.
     *
     * Generated from Godot docs: AnimationNodeAnimation.get_animation
     */
    fun getAnimation(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getAnimationBind, handle)
    }

    /**
     * Determines the playback direction of the animation.
     *
     * Generated from Godot docs: AnimationNodeAnimation.set_play_mode
     */
    fun setPlayMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setPlayModeBind, handle, mode)
    }

    /**
     * Determines the playback direction of the animation.
     *
     * Generated from Godot docs: AnimationNodeAnimation.get_play_mode
     */
    fun getPlayMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPlayModeBind, handle)
    }

    /**
     * If `true`, on receiving a request to play an animation from the start, the first frame is not
     * drawn, but only processed, and playback starts from the next frame. See also the notes of
     * `AnimationPlayer.play`.
     *
     * Generated from Godot docs: AnimationNodeAnimation.set_advance_on_start
     */
    fun setAdvanceOnStart(advanceOnStart: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAdvanceOnStartBind, handle, advanceOnStart)
    }

    /**
     * If `true`, on receiving a request to play an animation from the start, the first frame is not
     * drawn, but only processed, and playback starts from the next frame. See also the notes of
     * `AnimationPlayer.play`.
     *
     * Generated from Godot docs: AnimationNodeAnimation.is_advance_on_start
     */
    fun isAdvanceOnStart(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAdvanceOnStartBind, handle)
    }

    /**
     * If `true`, `AnimationNode` provides an animation based on the `Animation` resource with some
     * parameters adjusted.
     *
     * Generated from Godot docs: AnimationNodeAnimation.set_use_custom_timeline
     */
    fun setUseCustomTimeline(useCustomTimeline: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseCustomTimelineBind, handle, useCustomTimeline)
    }

    /**
     * If `true`, `AnimationNode` provides an animation based on the `Animation` resource with some
     * parameters adjusted.
     *
     * Generated from Godot docs: AnimationNodeAnimation.is_using_custom_timeline
     */
    fun isUsingCustomTimeline(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingCustomTimelineBind, handle)
    }

    /**
     * The length of the custom timeline. If `stretch_time_scale` is `true`, scales the animation to
     * this length.
     *
     * Generated from Godot docs: AnimationNodeAnimation.set_timeline_length
     */
    fun setTimelineLength(timelineLength: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTimelineLengthBind, handle, timelineLength)
    }

    /**
     * The length of the custom timeline. If `stretch_time_scale` is `true`, scales the animation to
     * this length.
     *
     * Generated from Godot docs: AnimationNodeAnimation.get_timeline_length
     */
    fun getTimelineLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimelineLengthBind, handle)
    }

    /**
     * If `true`, scales the time so that the length specified in `timeline_length` is one cycle. This
     * is useful for matching the periods of walking and running animations. If `false`, the original
     * animation length is respected. If you set the loop to `loop_mode`, the animation will loop in
     * `timeline_length`.
     *
     * Generated from Godot docs: AnimationNodeAnimation.set_stretch_time_scale
     */
    fun setStretchTimeScale(stretchTimeScale: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setStretchTimeScaleBind, handle, stretchTimeScale)
    }

    /**
     * If `true`, scales the time so that the length specified in `timeline_length` is one cycle. This
     * is useful for matching the periods of walking and running animations. If `false`, the original
     * animation length is respected. If you set the loop to `loop_mode`, the animation will loop in
     * `timeline_length`.
     *
     * Generated from Godot docs: AnimationNodeAnimation.is_stretching_time_scale
     */
    fun isStretchingTimeScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isStretchingTimeScaleBind, handle)
    }

    /**
     * If `use_custom_timeline` is `true`, offset the start position of the animation. This is useful
     * for adjusting which foot steps first in 3D walking animations.
     *
     * Generated from Godot docs: AnimationNodeAnimation.set_start_offset
     */
    fun setStartOffset(startOffset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setStartOffsetBind, handle, startOffset)
    }

    /**
     * If `use_custom_timeline` is `true`, offset the start position of the animation. This is useful
     * for adjusting which foot steps first in 3D walking animations.
     *
     * Generated from Godot docs: AnimationNodeAnimation.get_start_offset
     */
    fun getStartOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStartOffsetBind, handle)
    }

    /**
     * If `use_custom_timeline` is `true`, override the loop settings of the original `Animation`
     * resource with the value. Note: If the `Animation.loop_mode` isn't set to looping, the
     * `Animation.track_set_interpolation_loop_wrap` option will not be respected. If you cannot get
     * the expected behavior, consider duplicating the `Animation` resource and changing the loop
     * settings.
     *
     * Generated from Godot docs: AnimationNodeAnimation.set_loop_mode
     */
    fun setLoopMode(loopMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setLoopModeBind, handle, loopMode)
    }

    /**
     * If `use_custom_timeline` is `true`, override the loop settings of the original `Animation`
     * resource with the value. Note: If the `Animation.loop_mode` isn't set to looping, the
     * `Animation.track_set_interpolation_loop_wrap` option will not be respected. If you cannot get
     * the expected behavior, consider duplicating the `Animation` resource and changing the loop
     * settings.
     *
     * Generated from Godot docs: AnimationNodeAnimation.get_loop_mode
     */
    fun getLoopMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLoopModeBind, handle)
    }

    companion object {
        const val PLAY_MODE_FORWARD: Long = 0L
        const val PLAY_MODE_BACKWARD: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNodeAnimation? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeAnimation? =
            if (handle.address() == 0L) null else AnimationNodeAnimation(handle)

        private const val SET_ANIMATION_HASH = 3304788590L
        private val setAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_animation", SET_ANIMATION_HASH)
        }

        private const val GET_ANIMATION_HASH = 2002593661L
        private val getAnimationBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "get_animation", GET_ANIMATION_HASH)
        }

        private const val SET_PLAY_MODE_HASH = 3347718873L
        private val setPlayModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_play_mode", SET_PLAY_MODE_HASH)
        }

        private const val GET_PLAY_MODE_HASH = 2061244637L
        private val getPlayModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "get_play_mode", GET_PLAY_MODE_HASH)
        }

        private const val SET_ADVANCE_ON_START_HASH = 2586408642L
        private val setAdvanceOnStartBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_advance_on_start", SET_ADVANCE_ON_START_HASH)
        }

        private const val IS_ADVANCE_ON_START_HASH = 36873697L
        private val isAdvanceOnStartBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "is_advance_on_start", IS_ADVANCE_ON_START_HASH)
        }

        private const val SET_USE_CUSTOM_TIMELINE_HASH = 2586408642L
        private val setUseCustomTimelineBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_use_custom_timeline", SET_USE_CUSTOM_TIMELINE_HASH)
        }

        private const val IS_USING_CUSTOM_TIMELINE_HASH = 36873697L
        private val isUsingCustomTimelineBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "is_using_custom_timeline", IS_USING_CUSTOM_TIMELINE_HASH)
        }

        private const val SET_TIMELINE_LENGTH_HASH = 373806689L
        private val setTimelineLengthBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_timeline_length", SET_TIMELINE_LENGTH_HASH)
        }

        private const val GET_TIMELINE_LENGTH_HASH = 1740695150L
        private val getTimelineLengthBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "get_timeline_length", GET_TIMELINE_LENGTH_HASH)
        }

        private const val SET_STRETCH_TIME_SCALE_HASH = 2586408642L
        private val setStretchTimeScaleBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_stretch_time_scale", SET_STRETCH_TIME_SCALE_HASH)
        }

        private const val IS_STRETCHING_TIME_SCALE_HASH = 36873697L
        private val isStretchingTimeScaleBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "is_stretching_time_scale", IS_STRETCHING_TIME_SCALE_HASH)
        }

        private const val SET_START_OFFSET_HASH = 373806689L
        private val setStartOffsetBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_start_offset", SET_START_OFFSET_HASH)
        }

        private const val GET_START_OFFSET_HASH = 1740695150L
        private val getStartOffsetBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "get_start_offset", GET_START_OFFSET_HASH)
        }

        private const val SET_LOOP_MODE_HASH = 3155355575L
        private val setLoopModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_loop_mode", SET_LOOP_MODE_HASH)
        }

        private const val GET_LOOP_MODE_HASH = 1988889481L
        private val getLoopModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "get_loop_mode", GET_LOOP_MODE_HASH)
        }
    }
}
