package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An input animation for an `AnimationNodeBlendTree`.
 *
 * Generated from Godot docs: AnimationNodeAnimation
 */
class AnimationNodeAnimation(handle: GodotHandle) : AnimationRootNode(handle) {
    var animation: String
        @JvmName("animationProperty")
        get() = getAnimation()
        @JvmName("setAnimationProperty")
        set(value) = setAnimation(value)

    var playMode: AnimationNodeAnimation.PlayMode
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

    var loopMode: Animation.LoopMode
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
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(Binds.setAnimationBind, segment, name)
    }

    /**
     * Animation to use as an output. It is one of the animations provided by
     * `AnimationTree.anim_player`.
     *
     * Generated from Godot docs: AnimationNodeAnimation.get_animation
     */
    fun getAnimation(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(Binds.getAnimationBind, segment)
    }

    /**
     * Determines the playback direction of the animation.
     *
     * Generated from Godot docs: AnimationNodeAnimation.set_play_mode
     */
    fun setPlayMode(mode: AnimationNodeAnimation.PlayMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setPlayModeBind, segment, mode.value)
    }

    /**
     * Determines the playback direction of the animation.
     *
     * Generated from Godot docs: AnimationNodeAnimation.get_play_mode
     */
    fun getPlayMode(): AnimationNodeAnimation.PlayMode {
        checkOpen()
        return AnimationNodeAnimation.PlayMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPlayModeBind, segment))
    }

    /**
     * If `true`, on receiving a request to play an animation from the start, the first frame is not
     * drawn, but only processed, and playback starts from the next frame. See also the notes of
     * `AnimationPlayer.play`.
     *
     * Generated from Godot docs: AnimationNodeAnimation.set_advance_on_start
     */
    fun setAdvanceOnStart(advanceOnStart: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAdvanceOnStartBind, segment, advanceOnStart)
    }

    /**
     * If `true`, on receiving a request to play an animation from the start, the first frame is not
     * drawn, but only processed, and playback starts from the next frame. See also the notes of
     * `AnimationPlayer.play`.
     *
     * Generated from Godot docs: AnimationNodeAnimation.is_advance_on_start
     */
    fun isAdvanceOnStart(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAdvanceOnStartBind, segment)
    }

    /**
     * If `true`, `AnimationNode` provides an animation based on the `Animation` resource with some
     * parameters adjusted.
     *
     * Generated from Godot docs: AnimationNodeAnimation.set_use_custom_timeline
     */
    fun setUseCustomTimeline(useCustomTimeline: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseCustomTimelineBind, segment, useCustomTimeline)
    }

    /**
     * If `true`, `AnimationNode` provides an animation based on the `Animation` resource with some
     * parameters adjusted.
     *
     * Generated from Godot docs: AnimationNodeAnimation.is_using_custom_timeline
     */
    fun isUsingCustomTimeline(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingCustomTimelineBind, segment)
    }

    /**
     * The length of the custom timeline. If `stretch_time_scale` is `true`, scales the animation to
     * this length.
     *
     * Generated from Godot docs: AnimationNodeAnimation.set_timeline_length
     */
    fun setTimelineLength(timelineLength: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTimelineLengthBind, segment, timelineLength)
    }

    /**
     * The length of the custom timeline. If `stretch_time_scale` is `true`, scales the animation to
     * this length.
     *
     * Generated from Godot docs: AnimationNodeAnimation.get_timeline_length
     */
    fun getTimelineLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTimelineLengthBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setStretchTimeScaleBind, segment, stretchTimeScale)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isStretchingTimeScaleBind, segment)
    }

    /**
     * If `use_custom_timeline` is `true`, offset the start position of the animation. This is useful
     * for adjusting which foot steps first in 3D walking animations.
     *
     * Generated from Godot docs: AnimationNodeAnimation.set_start_offset
     */
    fun setStartOffset(startOffset: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setStartOffsetBind, segment, startOffset)
    }

    /**
     * If `use_custom_timeline` is `true`, offset the start position of the animation. This is useful
     * for adjusting which foot steps first in 3D walking animations.
     *
     * Generated from Godot docs: AnimationNodeAnimation.get_start_offset
     */
    fun getStartOffset(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getStartOffsetBind, segment)
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
    fun setLoopMode(loopMode: Animation.LoopMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setLoopModeBind, segment, loopMode.value)
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
    fun getLoopMode(): Animation.LoopMode {
        checkOpen()
        return Animation.LoopMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getLoopModeBind, segment))
    }

    /**
     * Godot's `AnimationNodeAnimation.PlayMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`AnimationNodeAnimation.PlayMode.<NAME>`).
     *
     * Generated from Godot docs: AnimationNodeAnimation.PlayMode
     */
    @JvmInline
    value class PlayMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Plays animation in forward direction.
             *
             * Generated from Godot docs: AnimationNodeAnimation.PLAY_MODE_FORWARD
             */
            val FORWARD: PlayMode get() = PlayMode(0L)
            /**
             * Plays animation in backward direction.
             *
             * Generated from Godot docs: AnimationNodeAnimation.PLAY_MODE_BACKWARD
             */
            val BACKWARD: PlayMode get() = PlayMode(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimationNodeAnimation? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AnimationNodeAnimation? =
            if (handle.address() == 0L) null else RefCounted.owned(AnimationNodeAnimation(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AnimationNodeAnimation? =
            if (handle.address() == 0L) null else AnimationNodeAnimation(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_ANIMATION_HASH = 3304788590L
        @JvmField
        val setAnimationBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_animation", SET_ANIMATION_HASH)

        private const val GET_ANIMATION_HASH = 2002593661L
        @JvmField
        val getAnimationBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "get_animation", GET_ANIMATION_HASH)

        private const val SET_PLAY_MODE_HASH = 3347718873L
        @JvmField
        val setPlayModeBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_play_mode", SET_PLAY_MODE_HASH)

        private const val GET_PLAY_MODE_HASH = 2061244637L
        @JvmField
        val getPlayModeBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "get_play_mode", GET_PLAY_MODE_HASH)

        private const val SET_ADVANCE_ON_START_HASH = 2586408642L
        @JvmField
        val setAdvanceOnStartBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_advance_on_start", SET_ADVANCE_ON_START_HASH)

        private const val IS_ADVANCE_ON_START_HASH = 36873697L
        @JvmField
        val isAdvanceOnStartBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "is_advance_on_start", IS_ADVANCE_ON_START_HASH)

        private const val SET_USE_CUSTOM_TIMELINE_HASH = 2586408642L
        @JvmField
        val setUseCustomTimelineBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_use_custom_timeline", SET_USE_CUSTOM_TIMELINE_HASH)

        private const val IS_USING_CUSTOM_TIMELINE_HASH = 36873697L
        @JvmField
        val isUsingCustomTimelineBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "is_using_custom_timeline", IS_USING_CUSTOM_TIMELINE_HASH)

        private const val SET_TIMELINE_LENGTH_HASH = 373806689L
        @JvmField
        val setTimelineLengthBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_timeline_length", SET_TIMELINE_LENGTH_HASH)

        private const val GET_TIMELINE_LENGTH_HASH = 1740695150L
        @JvmField
        val getTimelineLengthBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "get_timeline_length", GET_TIMELINE_LENGTH_HASH)

        private const val SET_STRETCH_TIME_SCALE_HASH = 2586408642L
        @JvmField
        val setStretchTimeScaleBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_stretch_time_scale", SET_STRETCH_TIME_SCALE_HASH)

        private const val IS_STRETCHING_TIME_SCALE_HASH = 36873697L
        @JvmField
        val isStretchingTimeScaleBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "is_stretching_time_scale", IS_STRETCHING_TIME_SCALE_HASH)

        private const val SET_START_OFFSET_HASH = 373806689L
        @JvmField
        val setStartOffsetBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_start_offset", SET_START_OFFSET_HASH)

        private const val GET_START_OFFSET_HASH = 1740695150L
        @JvmField
        val getStartOffsetBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "get_start_offset", GET_START_OFFSET_HASH)

        private const val SET_LOOP_MODE_HASH = 3155355575L
        @JvmField
        val setLoopModeBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "set_loop_mode", SET_LOOP_MODE_HASH)

        private const val GET_LOOP_MODE_HASH = 1988889481L
        @JvmField
        val getLoopModeBind =
            ObjectCalls.getMethodBind("AnimationNodeAnimation", "get_loop_mode", GET_LOOP_MODE_HASH)
    }
}
