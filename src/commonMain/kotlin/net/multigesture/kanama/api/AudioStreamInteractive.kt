package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Audio stream that can playback music interactively, combining clips and a transition table.
 *
 * Generated from Godot docs: AudioStreamInteractive
 */
class AudioStreamInteractive(handle: GodotHandle) : AudioStream(handle) {
    var clipCount: Int
        @JvmName("clipCountProperty")
        get() = getClipCount()
        @JvmName("setClipCountProperty")
        set(value) = setClipCount(value)

    var initialClip: Int
        @JvmName("initialClipProperty")
        get() = getInitialClip()
        @JvmName("setInitialClipProperty")
        set(value) = setInitialClip(value)

    /**
     * Amount of clips contained in this interactive player.
     *
     * Generated from Godot docs: AudioStreamInteractive.set_clip_count
     */
    fun setClipCount(clipCount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setClipCountBind, segment, clipCount)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Amount of clips contained in this interactive player.
     *
     * Generated from Godot docs: AudioStreamInteractive.get_clip_count
     */
    fun getClipCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getClipCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Index of the initial clip, which will be played first when this stream is played.
     *
     * Generated from Godot docs: AudioStreamInteractive.set_initial_clip
     */
    fun setInitialClip(clipIndex: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setInitialClipBind, segment, clipIndex)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Index of the initial clip, which will be played first when this stream is played.
     *
     * Generated from Godot docs: AudioStreamInteractive.get_initial_clip
     */
    fun getInitialClip(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getInitialClipBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Set the name of the current clip (for easier identification).
     *
     * Generated from Godot docs: AudioStreamInteractive.set_clip_name
     */
    fun setClipName(clipIndex: Int, name: String) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndStringNameArg(Binds.setClipNameBind, segment, clipIndex, name)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Return the name of a clip.
     *
     * Generated from Godot docs: AudioStreamInteractive.get_clip_name
     */
    fun getClipName(clipIndex: Int): String {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetStringName(Binds.getClipNameBind, segment, clipIndex)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Set the `AudioStream` associated with the current clip.
     *
     * Generated from Godot docs: AudioStreamInteractive.set_clip_stream
     */
    fun setClipStream(clipIndex: Int, stream: AudioStream?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setClipStreamBind, segment, clipIndex, stream?.requireOpenHandle() ?: NULL_SEGMENT)
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(stream)
    }

    /**
     * Return the `AudioStream` associated with a clip.
     *
     * Generated from Godot docs: AudioStreamInteractive.get_clip_stream
     */
    fun getClipStream(clipIndex: Int): AudioStream? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetObject(Binds.getClipStreamBind, segment, clipIndex)
        ObjectCalls.keepReachable(this)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return AudioStream.wrapOwned(ret)
    }

    /**
     * Set whether a clip will auto-advance by changing the auto-advance mode.
     *
     * Generated from Godot docs: AudioStreamInteractive.set_clip_auto_advance
     */
    fun setClipAutoAdvance(clipIndex: Int, mode: AudioStreamInteractive.AutoAdvanceMode) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndLongArgs(Binds.setClipAutoAdvanceBind, segment, clipIndex, mode.value)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Return whether a clip has auto-advance enabled. See `set_clip_auto_advance`.
     *
     * Generated from Godot docs: AudioStreamInteractive.get_clip_auto_advance
     */
    fun getClipAutoAdvance(clipIndex: Int): AudioStreamInteractive.AutoAdvanceMode {
        checkOpen()
        val ret = AudioStreamInteractive.AutoAdvanceMode(ObjectCalls.ptrcallWithIntArgRetLong(Binds.getClipAutoAdvanceBind, segment, clipIndex))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Set the index of the next clip towards which this clip will auto advance to when finished. If
     * the clip being played loops, then auto-advance will be ignored.
     *
     * Generated from Godot docs: AudioStreamInteractive.set_clip_auto_advance_next_clip
     */
    fun setClipAutoAdvanceNextClip(clipIndex: Int, autoAdvanceNextClip: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.setClipAutoAdvanceNextClipBind, segment, clipIndex, autoAdvanceNextClip)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Return the clip towards which the clip referenced by `clip_index` will auto-advance to.
     *
     * Generated from Godot docs: AudioStreamInteractive.get_clip_auto_advance_next_clip
     */
    fun getClipAutoAdvanceNextClip(clipIndex: Int): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetInt(Binds.getClipAutoAdvanceNextClipBind, segment, clipIndex)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Add a transition between two clips. Provide the indices of the source and destination clips, or
     * use the `CLIP_ANY` constant to indicate that transition happens to/from any clip to this one. *
     * `from_time` indicates the moment in the current clip the transition will begin after triggered.
     * * `to_time` indicates the time in the next clip that the playback will start from. * `fade_mode`
     * indicates how the fade will happen between clips. If unsure, just use `FadeMode.AUTOMATIC` which
     * uses the most common type of fade for each situation. * `fade_beats` indicates how many beats
     * the fade will take. Using decimals is allowed. * `use_filler_clip` indicates that there will be
     * a filler clip used between the source and destination clips. * `filler_clip` the index of the
     * filler clip. * If `hold_previous` is used, then this clip will be remembered. This can be used
     * together with `AutoAdvanceMode.RETURN_TO_HOLD` to return to this clip after another is done
     * playing.
     *
     * Generated from Godot docs: AudioStreamInteractive.add_transition
     */
    fun addTransition(fromClip: Int, toClip: Int, fromTime: AudioStreamInteractive.TransitionFromTime, toTime: AudioStreamInteractive.TransitionToTime, fadeMode: AudioStreamInteractive.FadeMode, fadeBeats: Double, useFillerClip: Boolean = false, fillerClip: Int = -1, holdPrevious: Boolean = false) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntThreeLongDoubleBoolIntBoolArgs(Binds.addTransitionBind, segment, fromClip, toClip, fromTime.value, toTime.value, fadeMode.value, fadeBeats, useFillerClip, fillerClip, holdPrevious)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Returns `true` if a given transition exists (was added via `add_transition`).
     *
     * Generated from Godot docs: AudioStreamInteractive.has_transition
     */
    fun hasTransition(fromClip: Int, toClip: Int): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithTwoIntArgsRetBool(Binds.hasTransitionBind, segment, fromClip, toClip)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Erase a transition by providing `from_clip` and `to_clip` clip indices. `CLIP_ANY` can be used
     * for either argument or both.
     *
     * Generated from Godot docs: AudioStreamInteractive.erase_transition
     */
    fun eraseTransition(fromClip: Int, toClip: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.eraseTransitionBind, segment, fromClip, toClip)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Return the list of transitions (from, to interleaved).
     *
     * Generated from Godot docs: AudioStreamInteractive.get_transition_list
     */
    fun getTransitionList(): List<Int> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetPackedInt32List(Binds.getTransitionListBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Return the source time position for a transition (see `add_transition`).
     *
     * Generated from Godot docs: AudioStreamInteractive.get_transition_from_time
     */
    fun getTransitionFromTime(fromClip: Int, toClip: Int): AudioStreamInteractive.TransitionFromTime {
        checkOpen()
        val ret = AudioStreamInteractive.TransitionFromTime(ObjectCalls.ptrcallWithTwoIntArgsRetLong(Binds.getTransitionFromTimeBind, segment, fromClip, toClip))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Return the destination time position for a transition (see `add_transition`).
     *
     * Generated from Godot docs: AudioStreamInteractive.get_transition_to_time
     */
    fun getTransitionToTime(fromClip: Int, toClip: Int): AudioStreamInteractive.TransitionToTime {
        checkOpen()
        val ret = AudioStreamInteractive.TransitionToTime(ObjectCalls.ptrcallWithTwoIntArgsRetLong(Binds.getTransitionToTimeBind, segment, fromClip, toClip))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Return the mode for a transition (see `add_transition`).
     *
     * Generated from Godot docs: AudioStreamInteractive.get_transition_fade_mode
     */
    fun getTransitionFadeMode(fromClip: Int, toClip: Int): AudioStreamInteractive.FadeMode {
        checkOpen()
        val ret = AudioStreamInteractive.FadeMode(ObjectCalls.ptrcallWithTwoIntArgsRetLong(Binds.getTransitionFadeModeBind, segment, fromClip, toClip))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Return the time (in beats) for a transition (see `add_transition`).
     *
     * Generated from Godot docs: AudioStreamInteractive.get_transition_fade_beats
     */
    fun getTransitionFadeBeats(fromClip: Int, toClip: Int): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithTwoIntArgsRetDouble(Binds.getTransitionFadeBeatsBind, segment, fromClip, toClip)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Return whether a transition uses the filler clip functionality (see `add_transition`).
     *
     * Generated from Godot docs: AudioStreamInteractive.is_transition_using_filler_clip
     */
    fun isTransitionUsingFillerClip(fromClip: Int, toClip: Int): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithTwoIntArgsRetBool(Binds.isTransitionUsingFillerClipBind, segment, fromClip, toClip)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Return the filler clip for a transition (see `add_transition`).
     *
     * Generated from Godot docs: AudioStreamInteractive.get_transition_filler_clip
     */
    fun getTransitionFillerClip(fromClip: Int, toClip: Int): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithTwoIntArgsRetInt(Binds.getTransitionFillerClipBind, segment, fromClip, toClip)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Return whether a transition uses the hold previous functionality (see `add_transition`).
     *
     * Generated from Godot docs: AudioStreamInteractive.is_transition_holding_previous
     */
    fun isTransitionHoldingPrevious(fromClip: Int, toClip: Int): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithTwoIntArgsRetBool(Binds.isTransitionHoldingPreviousBind, segment, fromClip, toClip)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Godot's `AudioStreamInteractive.TransitionFromTime` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AudioStreamInteractive.TransitionFromTime.<NAME>`).
     *
     * Generated from Godot docs: AudioStreamInteractive.TransitionFromTime
     */
    @JvmInline
    value class TransitionFromTime(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Start transition as soon as possible, don't wait for any specific time position.
             *
             * Generated from Godot docs: AudioStreamInteractive.TRANSITION_FROM_TIME_IMMEDIATE
             */
            val IMMEDIATE: TransitionFromTime get() = TransitionFromTime(0L)
            /**
             * Transition when the clip playback position reaches the next beat.
             *
             * Generated from Godot docs: AudioStreamInteractive.TRANSITION_FROM_TIME_NEXT_BEAT
             */
            val NEXT_BEAT: TransitionFromTime get() = TransitionFromTime(1L)
            /**
             * Transition when the clip playback position reaches the next bar.
             *
             * Generated from Godot docs: AudioStreamInteractive.TRANSITION_FROM_TIME_NEXT_BAR
             */
            val NEXT_BAR: TransitionFromTime get() = TransitionFromTime(2L)
            /**
             * Transition when the current clip finished playing.
             *
             * Generated from Godot docs: AudioStreamInteractive.TRANSITION_FROM_TIME_END
             */
            val END: TransitionFromTime get() = TransitionFromTime(3L)
        }
    }

    /**
     * Godot's `AudioStreamInteractive.TransitionToTime` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AudioStreamInteractive.TransitionToTime.<NAME>`).
     *
     * Generated from Godot docs: AudioStreamInteractive.TransitionToTime
     */
    @JvmInline
    value class TransitionToTime(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Transition to the same position in the destination clip. This is useful when both clips have
             * exactly the same length and the music should fade between them.
             *
             * Generated from Godot docs: AudioStreamInteractive.TRANSITION_TO_TIME_SAME_POSITION
             */
            val SAME_POSITION: TransitionToTime get() = TransitionToTime(0L)
            /**
             * Transition to the start of the destination clip.
             *
             * Generated from Godot docs: AudioStreamInteractive.TRANSITION_TO_TIME_START
             */
            val START: TransitionToTime get() = TransitionToTime(1L)
            /**
             * Transition to the last played position in the destination clip, if there was a previous
             * transition from that clip. Otherwise, plays from the start of the destination clip.
             *
             * Generated from Godot docs: AudioStreamInteractive.TRANSITION_TO_TIME_PREVIOUS_POSITION
             */
            val PREVIOUS_POSITION: TransitionToTime get() = TransitionToTime(2L)
        }
    }

    /**
     * Godot's `AudioStreamInteractive.FadeMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values (`AudioStreamInteractive.FadeMode.<NAME>`).
     *
     * Generated from Godot docs: AudioStreamInteractive.FadeMode
     */
    @JvmInline
    value class FadeMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Do not use fade for the transition. This is useful when transitioning from a clip-end to
             * clip-beginning, and each clip has their begin/end.
             *
             * Generated from Godot docs: AudioStreamInteractive.FADE_DISABLED
             */
            val DISABLED: FadeMode get() = FadeMode(0L)
            /**
             * Use a fade-in in the next clip, let the current clip finish.
             *
             * Generated from Godot docs: AudioStreamInteractive.FADE_IN
             */
            val IN: FadeMode get() = FadeMode(1L)
            /**
             * Use a fade-out in the current clip, the next clip will start by itself.
             *
             * Generated from Godot docs: AudioStreamInteractive.FADE_OUT
             */
            val OUT: FadeMode get() = FadeMode(2L)
            /**
             * Use a cross-fade between clips.
             *
             * Generated from Godot docs: AudioStreamInteractive.FADE_CROSS
             */
            val CROSS: FadeMode get() = FadeMode(3L)
            /**
             * Use automatic fade logic depending on the transition from/to. It is recommended to use this by
             * default.
             *
             * Generated from Godot docs: AudioStreamInteractive.FADE_AUTOMATIC
             */
            val AUTOMATIC: FadeMode get() = FadeMode(4L)
        }
    }

    /**
     * Godot's `AudioStreamInteractive.AutoAdvanceMode` enum as a typed value: `.value` is the raw
     * number Godot uses, and the companion holds the named values
     * (`AudioStreamInteractive.AutoAdvanceMode.<NAME>`).
     *
     * Generated from Godot docs: AudioStreamInteractive.AutoAdvanceMode
     */
    @JvmInline
    value class AutoAdvanceMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Disable auto-advance (default).
             *
             * Generated from Godot docs: AudioStreamInteractive.AUTO_ADVANCE_DISABLED
             */
            val DISABLED: AutoAdvanceMode get() = AutoAdvanceMode(0L)
            /**
             * Enable auto-advance, a clip must be specified.
             *
             * Generated from Godot docs: AudioStreamInteractive.AUTO_ADVANCE_ENABLED
             */
            val ENABLED: AutoAdvanceMode get() = AutoAdvanceMode(1L)
            /**
             * Enable auto-advance, but instead of specifying a clip, the playback will return to hold (see
             * `add_transition`).
             *
             * Generated from Godot docs: AudioStreamInteractive.AUTO_ADVANCE_RETURN_TO_HOLD
             */
            val RETURN_TO_HOLD: AutoAdvanceMode get() = AutoAdvanceMode(2L)
        }
    }

    companion object {
        const val CLIP_ANY: Long = -1L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamInteractive? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamInteractive? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamInteractive(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamInteractive? =
            if (handle.address() == 0L) null else AudioStreamInteractive(GodotHandle(handle))

        /** A new AudioStreamInteractive, GDScript's `AudioStreamInteractive.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): AudioStreamInteractive =
            RefCounted.owned(AudioStreamInteractive(GodotHandle(requireConstructed(ObjectCalls.constructObject("AudioStreamInteractive"), "AudioStreamInteractive"))))
    }

    private object Binds {
        private const val SET_CLIP_COUNT_HASH = 1286410249L
        @JvmField
        val setClipCountBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "set_clip_count", SET_CLIP_COUNT_HASH)

        private const val GET_CLIP_COUNT_HASH = 3905245786L
        @JvmField
        val getClipCountBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_clip_count", GET_CLIP_COUNT_HASH)

        private const val SET_INITIAL_CLIP_HASH = 1286410249L
        @JvmField
        val setInitialClipBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "set_initial_clip", SET_INITIAL_CLIP_HASH)

        private const val GET_INITIAL_CLIP_HASH = 3905245786L
        @JvmField
        val getInitialClipBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_initial_clip", GET_INITIAL_CLIP_HASH)

        private const val SET_CLIP_NAME_HASH = 3780747571L
        @JvmField
        val setClipNameBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "set_clip_name", SET_CLIP_NAME_HASH)

        private const val GET_CLIP_NAME_HASH = 659327637L
        @JvmField
        val getClipNameBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_clip_name", GET_CLIP_NAME_HASH)

        private const val SET_CLIP_STREAM_HASH = 111075094L
        @JvmField
        val setClipStreamBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "set_clip_stream", SET_CLIP_STREAM_HASH)

        private const val GET_CLIP_STREAM_HASH = 2739380747L
        @JvmField
        val getClipStreamBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_clip_stream", GET_CLIP_STREAM_HASH)

        private const val SET_CLIP_AUTO_ADVANCE_HASH = 57217598L
        @JvmField
        val setClipAutoAdvanceBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "set_clip_auto_advance", SET_CLIP_AUTO_ADVANCE_HASH)

        private const val GET_CLIP_AUTO_ADVANCE_HASH = 1778634807L
        @JvmField
        val getClipAutoAdvanceBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_clip_auto_advance", GET_CLIP_AUTO_ADVANCE_HASH)

        private const val SET_CLIP_AUTO_ADVANCE_NEXT_CLIP_HASH = 3937882851L
        @JvmField
        val setClipAutoAdvanceNextClipBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "set_clip_auto_advance_next_clip", SET_CLIP_AUTO_ADVANCE_NEXT_CLIP_HASH)

        private const val GET_CLIP_AUTO_ADVANCE_NEXT_CLIP_HASH = 923996154L
        @JvmField
        val getClipAutoAdvanceNextClipBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_clip_auto_advance_next_clip", GET_CLIP_AUTO_ADVANCE_NEXT_CLIP_HASH)

        private const val ADD_TRANSITION_HASH = 1630280552L
        @JvmField
        val addTransitionBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "add_transition", ADD_TRANSITION_HASH)

        private const val HAS_TRANSITION_HASH = 2522259332L
        @JvmField
        val hasTransitionBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "has_transition", HAS_TRANSITION_HASH)

        private const val ERASE_TRANSITION_HASH = 3937882851L
        @JvmField
        val eraseTransitionBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "erase_transition", ERASE_TRANSITION_HASH)

        private const val GET_TRANSITION_LIST_HASH = 1930428628L
        @JvmField
        val getTransitionListBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_transition_list", GET_TRANSITION_LIST_HASH)

        private const val GET_TRANSITION_FROM_TIME_HASH = 3453338158L
        @JvmField
        val getTransitionFromTimeBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_transition_from_time", GET_TRANSITION_FROM_TIME_HASH)

        private const val GET_TRANSITION_TO_TIME_HASH = 1369651373L
        @JvmField
        val getTransitionToTimeBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_transition_to_time", GET_TRANSITION_TO_TIME_HASH)

        private const val GET_TRANSITION_FADE_MODE_HASH = 4065396087L
        @JvmField
        val getTransitionFadeModeBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_transition_fade_mode", GET_TRANSITION_FADE_MODE_HASH)

        private const val GET_TRANSITION_FADE_BEATS_HASH = 3085491603L
        @JvmField
        val getTransitionFadeBeatsBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_transition_fade_beats", GET_TRANSITION_FADE_BEATS_HASH)

        private const val IS_TRANSITION_USING_FILLER_CLIP_HASH = 2522259332L
        @JvmField
        val isTransitionUsingFillerClipBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "is_transition_using_filler_clip", IS_TRANSITION_USING_FILLER_CLIP_HASH)

        private const val GET_TRANSITION_FILLER_CLIP_HASH = 3175239445L
        @JvmField
        val getTransitionFillerClipBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_transition_filler_clip", GET_TRANSITION_FILLER_CLIP_HASH)

        private const val IS_TRANSITION_HOLDING_PREVIOUS_HASH = 2522259332L
        @JvmField
        val isTransitionHoldingPreviousBind =
            ObjectCalls.getMethodBind("AudioStreamInteractive", "is_transition_holding_previous", IS_TRANSITION_HOLDING_PREVIOUS_HASH)
    }
}
