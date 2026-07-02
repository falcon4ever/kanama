package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: AudioStreamInteractive
 */
class AudioStreamInteractive(handle: MemorySegment) : AudioStream(handle) {
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

    fun setClipCount(clipCount: Int) {
        ObjectCalls.ptrcallWithIntArg(setClipCountBind, handle, clipCount)
    }

    fun getClipCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getClipCountBind, handle)
    }

    fun setInitialClip(clipIndex: Int) {
        ObjectCalls.ptrcallWithIntArg(setInitialClipBind, handle, clipIndex)
    }

    fun getInitialClip(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getInitialClipBind, handle)
    }

    fun setClipName(clipIndex: Int, name: String) {
        ObjectCalls.ptrcallWithIntAndStringNameArg(setClipNameBind, handle, clipIndex, name)
    }

    fun getClipName(clipIndex: Int): String {
        return ObjectCalls.ptrcallWithIntArgRetStringName(getClipNameBind, handle, clipIndex)
    }

    fun setClipStream(clipIndex: Int, stream: AudioStream?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setClipStreamBind, handle, clipIndex, stream?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    fun getClipStream(clipIndex: Int): AudioStream? {
        return AudioStream.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getClipStreamBind, handle, clipIndex))
    }

    fun setClipAutoAdvance(clipIndex: Int, mode: Long) {
        ObjectCalls.ptrcallWithIntAndLongArgs(setClipAutoAdvanceBind, handle, clipIndex, mode)
    }

    fun getClipAutoAdvance(clipIndex: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getClipAutoAdvanceBind, handle, clipIndex)
    }

    fun setClipAutoAdvanceNextClip(clipIndex: Int, autoAdvanceNextClip: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(setClipAutoAdvanceNextClipBind, handle, clipIndex, autoAdvanceNextClip)
    }

    fun getClipAutoAdvanceNextClip(clipIndex: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getClipAutoAdvanceNextClipBind, handle, clipIndex)
    }

    fun addTransition(fromClip: Int, toClip: Int, fromTime: Long, toTime: Long, fadeMode: Long, fadeBeats: Double, useFillerClip: Boolean = false, fillerClip: Int = -1, holdPrevious: Boolean = false) {
        ObjectCalls.ptrcallWithTwoIntThreeLongDoubleBoolIntBoolArgs(addTransitionBind, handle, fromClip, toClip, fromTime, toTime, fadeMode, fadeBeats, useFillerClip, fillerClip, holdPrevious)
    }

    fun hasTransition(fromClip: Int, toClip: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(hasTransitionBind, handle, fromClip, toClip)
    }

    fun eraseTransition(fromClip: Int, toClip: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(eraseTransitionBind, handle, fromClip, toClip)
    }

    fun getTransitionList(): List<Int> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt32List(getTransitionListBind, handle)
    }

    fun getTransitionFromTime(fromClip: Int, toClip: Int): Long {
        return ObjectCalls.ptrcallWithTwoIntArgsRetLong(getTransitionFromTimeBind, handle, fromClip, toClip)
    }

    fun getTransitionToTime(fromClip: Int, toClip: Int): Long {
        return ObjectCalls.ptrcallWithTwoIntArgsRetLong(getTransitionToTimeBind, handle, fromClip, toClip)
    }

    fun getTransitionFadeMode(fromClip: Int, toClip: Int): Long {
        return ObjectCalls.ptrcallWithTwoIntArgsRetLong(getTransitionFadeModeBind, handle, fromClip, toClip)
    }

    fun getTransitionFadeBeats(fromClip: Int, toClip: Int): Double {
        return ObjectCalls.ptrcallWithTwoIntArgsRetDouble(getTransitionFadeBeatsBind, handle, fromClip, toClip)
    }

    fun isTransitionUsingFillerClip(fromClip: Int, toClip: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(isTransitionUsingFillerClipBind, handle, fromClip, toClip)
    }

    fun getTransitionFillerClip(fromClip: Int, toClip: Int): Int {
        return ObjectCalls.ptrcallWithTwoIntArgsRetInt(getTransitionFillerClipBind, handle, fromClip, toClip)
    }

    fun isTransitionHoldingPrevious(fromClip: Int, toClip: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(isTransitionHoldingPreviousBind, handle, fromClip, toClip)
    }

    companion object {
        const val CLIP_ANY: Long = -1L
        const val TRANSITION_FROM_TIME_IMMEDIATE: Long = 0L
        const val TRANSITION_FROM_TIME_NEXT_BEAT: Long = 1L
        const val TRANSITION_FROM_TIME_NEXT_BAR: Long = 2L
        const val TRANSITION_FROM_TIME_END: Long = 3L
        const val TRANSITION_TO_TIME_SAME_POSITION: Long = 0L
        const val TRANSITION_TO_TIME_START: Long = 1L
        const val TRANSITION_TO_TIME_PREVIOUS_POSITION: Long = 2L
        const val FADE_DISABLED: Long = 0L
        const val FADE_IN: Long = 1L
        const val FADE_OUT: Long = 2L
        const val FADE_CROSS: Long = 3L
        const val FADE_AUTOMATIC: Long = 4L
        const val AUTO_ADVANCE_DISABLED: Long = 0L
        const val AUTO_ADVANCE_ENABLED: Long = 1L
        const val AUTO_ADVANCE_RETURN_TO_HOLD: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamInteractive? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamInteractive? =
            if (handle.address() == 0L) null else AudioStreamInteractive(handle)

        private const val SET_CLIP_COUNT_HASH = 1286410249L
        private val setClipCountBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "set_clip_count", SET_CLIP_COUNT_HASH)
        }

        private const val GET_CLIP_COUNT_HASH = 3905245786L
        private val getClipCountBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_clip_count", GET_CLIP_COUNT_HASH)
        }

        private const val SET_INITIAL_CLIP_HASH = 1286410249L
        private val setInitialClipBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "set_initial_clip", SET_INITIAL_CLIP_HASH)
        }

        private const val GET_INITIAL_CLIP_HASH = 3905245786L
        private val getInitialClipBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_initial_clip", GET_INITIAL_CLIP_HASH)
        }

        private const val SET_CLIP_NAME_HASH = 3780747571L
        private val setClipNameBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "set_clip_name", SET_CLIP_NAME_HASH)
        }

        private const val GET_CLIP_NAME_HASH = 659327637L
        private val getClipNameBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_clip_name", GET_CLIP_NAME_HASH)
        }

        private const val SET_CLIP_STREAM_HASH = 111075094L
        private val setClipStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "set_clip_stream", SET_CLIP_STREAM_HASH)
        }

        private const val GET_CLIP_STREAM_HASH = 2739380747L
        private val getClipStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_clip_stream", GET_CLIP_STREAM_HASH)
        }

        private const val SET_CLIP_AUTO_ADVANCE_HASH = 57217598L
        private val setClipAutoAdvanceBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "set_clip_auto_advance", SET_CLIP_AUTO_ADVANCE_HASH)
        }

        private const val GET_CLIP_AUTO_ADVANCE_HASH = 1778634807L
        private val getClipAutoAdvanceBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_clip_auto_advance", GET_CLIP_AUTO_ADVANCE_HASH)
        }

        private const val SET_CLIP_AUTO_ADVANCE_NEXT_CLIP_HASH = 3937882851L
        private val setClipAutoAdvanceNextClipBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "set_clip_auto_advance_next_clip", SET_CLIP_AUTO_ADVANCE_NEXT_CLIP_HASH)
        }

        private const val GET_CLIP_AUTO_ADVANCE_NEXT_CLIP_HASH = 923996154L
        private val getClipAutoAdvanceNextClipBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_clip_auto_advance_next_clip", GET_CLIP_AUTO_ADVANCE_NEXT_CLIP_HASH)
        }

        private const val ADD_TRANSITION_HASH = 1630280552L
        private val addTransitionBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "add_transition", ADD_TRANSITION_HASH)
        }

        private const val HAS_TRANSITION_HASH = 2522259332L
        private val hasTransitionBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "has_transition", HAS_TRANSITION_HASH)
        }

        private const val ERASE_TRANSITION_HASH = 3937882851L
        private val eraseTransitionBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "erase_transition", ERASE_TRANSITION_HASH)
        }

        private const val GET_TRANSITION_LIST_HASH = 1930428628L
        private val getTransitionListBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_transition_list", GET_TRANSITION_LIST_HASH)
        }

        private const val GET_TRANSITION_FROM_TIME_HASH = 3453338158L
        private val getTransitionFromTimeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_transition_from_time", GET_TRANSITION_FROM_TIME_HASH)
        }

        private const val GET_TRANSITION_TO_TIME_HASH = 1369651373L
        private val getTransitionToTimeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_transition_to_time", GET_TRANSITION_TO_TIME_HASH)
        }

        private const val GET_TRANSITION_FADE_MODE_HASH = 4065396087L
        private val getTransitionFadeModeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_transition_fade_mode", GET_TRANSITION_FADE_MODE_HASH)
        }

        private const val GET_TRANSITION_FADE_BEATS_HASH = 3085491603L
        private val getTransitionFadeBeatsBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_transition_fade_beats", GET_TRANSITION_FADE_BEATS_HASH)
        }

        private const val IS_TRANSITION_USING_FILLER_CLIP_HASH = 2522259332L
        private val isTransitionUsingFillerClipBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "is_transition_using_filler_clip", IS_TRANSITION_USING_FILLER_CLIP_HASH)
        }

        private const val GET_TRANSITION_FILLER_CLIP_HASH = 3175239445L
        private val getTransitionFillerClipBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "get_transition_filler_clip", GET_TRANSITION_FILLER_CLIP_HASH)
        }

        private const val IS_TRANSITION_HOLDING_PREVIOUS_HASH = 2522259332L
        private val isTransitionHoldingPreviousBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamInteractive", "is_transition_holding_previous", IS_TRANSITION_HOLDING_PREVIOUS_HASH)
        }
    }
}
