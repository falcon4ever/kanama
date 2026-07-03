package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Wraps a pool of audio streams with pitch and volume shifting.
 *
 * Generated from Godot docs: AudioStreamRandomizer
 */
class AudioStreamRandomizer(handle: MemorySegment) : AudioStream(handle) {
    var playbackMode: Long
        @JvmName("playbackModeProperty")
        get() = getPlaybackMode()
        @JvmName("setPlaybackModeProperty")
        set(value) = setPlaybackMode(value)

    var randomPitch: Double
        @JvmName("randomPitchProperty")
        get() = getRandomPitch()
        @JvmName("setRandomPitchProperty")
        set(value) = setRandomPitch(value)

    var randomPitchSemitones: Double
        @JvmName("randomPitchSemitonesProperty")
        get() = getRandomPitchSemitones()
        @JvmName("setRandomPitchSemitonesProperty")
        set(value) = setRandomPitchSemitones(value)

    var randomVolumeOffsetDb: Double
        @JvmName("randomVolumeOffsetDbProperty")
        get() = getRandomVolumeOffsetDb()
        @JvmName("setRandomVolumeOffsetDbProperty")
        set(value) = setRandomVolumeOffsetDb(value)

    var streamsCount: Int
        @JvmName("streamsCountProperty")
        get() = getStreamsCount()
        @JvmName("setStreamsCountProperty")
        set(value) = setStreamsCount(value)

    /**
     * Insert a stream at the specified index. If the index is less than zero, the insertion occurs at
     * the end of the underlying pool.
     *
     * Generated from Godot docs: AudioStreamRandomizer.add_stream
     */
    fun addStream(index: Int, stream: AudioStream?, weight: Double = 1.0) {
        ObjectCalls.ptrcallWithIntObjectDoubleArgs(addStreamBind, handle, index, stream?.requireOpenHandle() ?: MemorySegment.NULL, weight)
    }

    /**
     * Move a stream from one index to another.
     *
     * Generated from Godot docs: AudioStreamRandomizer.move_stream
     */
    fun moveStream(indexFrom: Int, indexTo: Int) {
        ObjectCalls.ptrcallWithTwoIntArgs(moveStreamBind, handle, indexFrom, indexTo)
    }

    /**
     * Remove the stream at the specified index.
     *
     * Generated from Godot docs: AudioStreamRandomizer.remove_stream
     */
    fun removeStream(index: Int) {
        ObjectCalls.ptrcallWithIntArg(removeStreamBind, handle, index)
    }

    /**
     * Set the AudioStream at the specified index.
     *
     * Generated from Godot docs: AudioStreamRandomizer.set_stream
     */
    fun setStream(index: Int, stream: AudioStream?) {
        ObjectCalls.ptrcallWithIntAndObjectArg(setStreamBind, handle, index, stream?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    /**
     * Returns the stream at the specified index.
     *
     * Generated from Godot docs: AudioStreamRandomizer.get_stream
     */
    fun getStream(index: Int): AudioStream? {
        return AudioStream.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getStreamBind, handle, index))
    }

    /**
     * Set the probability weight of the stream at the specified index. The higher this value, the more
     * likely that the randomizer will choose this stream during random playback modes.
     *
     * Generated from Godot docs: AudioStreamRandomizer.set_stream_probability_weight
     */
    fun setStreamProbabilityWeight(index: Int, weight: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setStreamProbabilityWeightBind, handle, index, weight)
    }

    /**
     * Returns the probability weight associated with the stream at the given index.
     *
     * Generated from Godot docs: AudioStreamRandomizer.get_stream_probability_weight
     */
    fun getStreamProbabilityWeight(index: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getStreamProbabilityWeightBind, handle, index)
    }

    /**
     * The number of streams in the stream pool.
     *
     * Generated from Godot docs: AudioStreamRandomizer.set_streams_count
     */
    fun setStreamsCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setStreamsCountBind, handle, count)
    }

    /**
     * The number of streams in the stream pool.
     *
     * Generated from Godot docs: AudioStreamRandomizer.get_streams_count
     */
    fun getStreamsCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getStreamsCountBind, handle)
    }

    /**
     * The largest possible frequency multiplier of the random pitch variation. Pitch will be randomly
     * chosen within a range of `1.0 / random_pitch` and `random_pitch`. A value of `1.0` means no
     * variation. A value of `2.0` means pitch will be randomized between double and half. Note:
     * Setting this property also sets `random_pitch_semitones`.
     *
     * Generated from Godot docs: AudioStreamRandomizer.set_random_pitch
     */
    fun setRandomPitch(scale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRandomPitchBind, handle, scale)
    }

    /**
     * The largest possible frequency multiplier of the random pitch variation. Pitch will be randomly
     * chosen within a range of `1.0 / random_pitch` and `random_pitch`. A value of `1.0` means no
     * variation. A value of `2.0` means pitch will be randomized between double and half. Note:
     * Setting this property also sets `random_pitch_semitones`.
     *
     * Generated from Godot docs: AudioStreamRandomizer.get_random_pitch
     */
    fun getRandomPitch(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRandomPitchBind, handle)
    }

    /**
     * The largest possible distance, in semitones, of the random pitch variation. A value of `0.0`
     * means no variation. Note: Setting this property also sets `random_pitch`.
     *
     * Generated from Godot docs: AudioStreamRandomizer.set_random_pitch_semitones
     */
    fun setRandomPitchSemitones(semitones: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRandomPitchSemitonesBind, handle, semitones)
    }

    /**
     * The largest possible distance, in semitones, of the random pitch variation. A value of `0.0`
     * means no variation. Note: Setting this property also sets `random_pitch`.
     *
     * Generated from Godot docs: AudioStreamRandomizer.get_random_pitch_semitones
     */
    fun getRandomPitchSemitones(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRandomPitchSemitonesBind, handle)
    }

    /**
     * The intensity of random volume variation. Volume will be increased or decreased by a random
     * value up to `random_volume_offset_db`. A value of `0.0` means no variation. A value of `3.0`
     * means volume will be randomized between `-3.0 dB` and `+3.0 dB`.
     *
     * Generated from Godot docs: AudioStreamRandomizer.set_random_volume_offset_db
     */
    fun setRandomVolumeOffsetDb(dbOffset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRandomVolumeOffsetDbBind, handle, dbOffset)
    }

    /**
     * The intensity of random volume variation. Volume will be increased or decreased by a random
     * value up to `random_volume_offset_db`. A value of `0.0` means no variation. A value of `3.0`
     * means volume will be randomized between `-3.0 dB` and `+3.0 dB`.
     *
     * Generated from Godot docs: AudioStreamRandomizer.get_random_volume_offset_db
     */
    fun getRandomVolumeOffsetDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRandomVolumeOffsetDbBind, handle)
    }

    /**
     * Controls how this AudioStreamRandomizer picks which AudioStream to play next.
     *
     * Generated from Godot docs: AudioStreamRandomizer.set_playback_mode
     */
    fun setPlaybackMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setPlaybackModeBind, handle, mode)
    }

    /**
     * Controls how this AudioStreamRandomizer picks which AudioStream to play next.
     *
     * Generated from Godot docs: AudioStreamRandomizer.get_playback_mode
     */
    fun getPlaybackMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPlaybackModeBind, handle)
    }

    companion object {
        const val PLAYBACK_RANDOM_NO_REPEATS: Long = 0L
        const val PLAYBACK_RANDOM: Long = 1L
        const val PLAYBACK_SEQUENTIAL: Long = 2L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamRandomizer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamRandomizer? =
            if (handle.address() == 0L) null else AudioStreamRandomizer(handle)

        private const val ADD_STREAM_HASH = 1892018854L
        private val addStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "add_stream", ADD_STREAM_HASH)
        }

        private const val MOVE_STREAM_HASH = 3937882851L
        private val moveStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "move_stream", MOVE_STREAM_HASH)
        }

        private const val REMOVE_STREAM_HASH = 1286410249L
        private val removeStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "remove_stream", REMOVE_STREAM_HASH)
        }

        private const val SET_STREAM_HASH = 111075094L
        private val setStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_stream", SET_STREAM_HASH)
        }

        private const val GET_STREAM_HASH = 2739380747L
        private val getStreamBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_stream", GET_STREAM_HASH)
        }

        private const val SET_STREAM_PROBABILITY_WEIGHT_HASH = 1602489585L
        private val setStreamProbabilityWeightBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_stream_probability_weight", SET_STREAM_PROBABILITY_WEIGHT_HASH)
        }

        private const val GET_STREAM_PROBABILITY_WEIGHT_HASH = 2339986948L
        private val getStreamProbabilityWeightBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_stream_probability_weight", GET_STREAM_PROBABILITY_WEIGHT_HASH)
        }

        private const val SET_STREAMS_COUNT_HASH = 1286410249L
        private val setStreamsCountBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_streams_count", SET_STREAMS_COUNT_HASH)
        }

        private const val GET_STREAMS_COUNT_HASH = 3905245786L
        private val getStreamsCountBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_streams_count", GET_STREAMS_COUNT_HASH)
        }

        private const val SET_RANDOM_PITCH_HASH = 373806689L
        private val setRandomPitchBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_random_pitch", SET_RANDOM_PITCH_HASH)
        }

        private const val GET_RANDOM_PITCH_HASH = 1740695150L
        private val getRandomPitchBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_random_pitch", GET_RANDOM_PITCH_HASH)
        }

        private const val SET_RANDOM_PITCH_SEMITONES_HASH = 373806689L
        private val setRandomPitchSemitonesBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_random_pitch_semitones", SET_RANDOM_PITCH_SEMITONES_HASH)
        }

        private const val GET_RANDOM_PITCH_SEMITONES_HASH = 1740695150L
        private val getRandomPitchSemitonesBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_random_pitch_semitones", GET_RANDOM_PITCH_SEMITONES_HASH)
        }

        private const val SET_RANDOM_VOLUME_OFFSET_DB_HASH = 373806689L
        private val setRandomVolumeOffsetDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_random_volume_offset_db", SET_RANDOM_VOLUME_OFFSET_DB_HASH)
        }

        private const val GET_RANDOM_VOLUME_OFFSET_DB_HASH = 1740695150L
        private val getRandomVolumeOffsetDbBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_random_volume_offset_db", GET_RANDOM_VOLUME_OFFSET_DB_HASH)
        }

        private const val SET_PLAYBACK_MODE_HASH = 3950967023L
        private val setPlaybackModeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_playback_mode", SET_PLAYBACK_MODE_HASH)
        }

        private const val GET_PLAYBACK_MODE_HASH = 3943055077L
        private val getPlaybackModeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_playback_mode", GET_PLAYBACK_MODE_HASH)
        }
    }
}
