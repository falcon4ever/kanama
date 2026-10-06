package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Wraps a pool of audio streams with pitch and volume shifting.
 *
 * Generated from Godot docs: AudioStreamRandomizer
 */
class AudioStreamRandomizer(handle: GodotHandle) : AudioStream(handle) {
    var playbackMode: AudioStreamRandomizer.PlaybackMode
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
        checkOpen()
        ObjectCalls.ptrcallWithIntObjectDoubleArgs(Binds.addStreamBind, segment, index, stream?.requireOpenHandle() ?: NULL_SEGMENT, weight)
    }

    /**
     * Move a stream from one index to another.
     *
     * Generated from Godot docs: AudioStreamRandomizer.move_stream
     */
    fun moveStream(indexFrom: Int, indexTo: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoIntArgs(Binds.moveStreamBind, segment, indexFrom, indexTo)
    }

    /**
     * Remove the stream at the specified index.
     *
     * Generated from Godot docs: AudioStreamRandomizer.remove_stream
     */
    fun removeStream(index: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.removeStreamBind, segment, index)
    }

    /**
     * Set the AudioStream at the specified index.
     *
     * Generated from Godot docs: AudioStreamRandomizer.set_stream
     */
    fun setStream(index: Int, stream: AudioStream?) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndObjectArg(Binds.setStreamBind, segment, index, stream?.requireOpenHandle() ?: NULL_SEGMENT)
    }

    /**
     * Returns the stream at the specified index.
     *
     * Generated from Godot docs: AudioStreamRandomizer.get_stream
     */
    fun getStream(index: Int): AudioStream? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithIntArgRetObject(Binds.getStreamBind, segment, index)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return AudioStream.wrapOwned(ret)
    }

    /**
     * Set the probability weight of the stream at the specified index. The higher this value, the more
     * likely that the randomizer will choose this stream during random playback modes.
     *
     * Generated from Godot docs: AudioStreamRandomizer.set_stream_probability_weight
     */
    fun setStreamProbabilityWeight(index: Int, weight: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setStreamProbabilityWeightBind, segment, index, weight)
    }

    /**
     * Returns the probability weight associated with the stream at the given index.
     *
     * Generated from Godot docs: AudioStreamRandomizer.get_stream_probability_weight
     */
    fun getStreamProbabilityWeight(index: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getStreamProbabilityWeightBind, segment, index)
    }

    /**
     * The number of streams in the stream pool.
     *
     * Generated from Godot docs: AudioStreamRandomizer.set_streams_count
     */
    fun setStreamsCount(count: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setStreamsCountBind, segment, count)
    }

    /**
     * The number of streams in the stream pool.
     *
     * Generated from Godot docs: AudioStreamRandomizer.get_streams_count
     */
    fun getStreamsCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getStreamsCountBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRandomPitchBind, segment, scale)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRandomPitchBind, segment)
    }

    /**
     * The largest possible distance, in semitones, of the random pitch variation. A value of `0.0`
     * means no variation. Note: Setting this property also sets `random_pitch`.
     *
     * Generated from Godot docs: AudioStreamRandomizer.set_random_pitch_semitones
     */
    fun setRandomPitchSemitones(semitones: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRandomPitchSemitonesBind, segment, semitones)
    }

    /**
     * The largest possible distance, in semitones, of the random pitch variation. A value of `0.0`
     * means no variation. Note: Setting this property also sets `random_pitch`.
     *
     * Generated from Godot docs: AudioStreamRandomizer.get_random_pitch_semitones
     */
    fun getRandomPitchSemitones(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRandomPitchSemitonesBind, segment)
    }

    /**
     * The intensity of random volume variation. Volume will be increased or decreased by a random
     * value up to `random_volume_offset_db`. A value of `0.0` means no variation. A value of `3.0`
     * means volume will be randomized between `-3.0 dB` and `+3.0 dB`.
     *
     * Generated from Godot docs: AudioStreamRandomizer.set_random_volume_offset_db
     */
    fun setRandomVolumeOffsetDb(dbOffset: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRandomVolumeOffsetDbBind, segment, dbOffset)
    }

    /**
     * The intensity of random volume variation. Volume will be increased or decreased by a random
     * value up to `random_volume_offset_db`. A value of `0.0` means no variation. A value of `3.0`
     * means volume will be randomized between `-3.0 dB` and `+3.0 dB`.
     *
     * Generated from Godot docs: AudioStreamRandomizer.get_random_volume_offset_db
     */
    fun getRandomVolumeOffsetDb(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRandomVolumeOffsetDbBind, segment)
    }

    /**
     * Controls how this AudioStreamRandomizer picks which AudioStream to play next.
     *
     * Generated from Godot docs: AudioStreamRandomizer.set_playback_mode
     */
    fun setPlaybackMode(mode: AudioStreamRandomizer.PlaybackMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setPlaybackModeBind, segment, mode.value)
    }

    /**
     * Controls how this AudioStreamRandomizer picks which AudioStream to play next.
     *
     * Generated from Godot docs: AudioStreamRandomizer.get_playback_mode
     */
    fun getPlaybackMode(): AudioStreamRandomizer.PlaybackMode {
        checkOpen()
        return AudioStreamRandomizer.PlaybackMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPlaybackModeBind, segment))
    }

    /**
     * Godot's `AudioStreamRandomizer.PlaybackMode` enum as a typed value: `.value` is the raw number
     * Godot uses, and the companion holds the named values
     * (`AudioStreamRandomizer.PlaybackMode.<NAME>`).
     *
     * Generated from Godot docs: AudioStreamRandomizer.PlaybackMode
     */
    @JvmInline
    value class PlaybackMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Pick a stream at random according to the probability weights chosen for each stream, but avoid
             * playing the same stream twice in a row whenever possible. If only 1 sound is present in the
             * pool, the same sound will always play, effectively allowing repeats to occur.
             *
             * Generated from Godot docs: AudioStreamRandomizer.PLAYBACK_RANDOM_NO_REPEATS
             */
            val RANDOM_NO_REPEATS: PlaybackMode get() = PlaybackMode(0L)
            /**
             * Pick a stream at random according to the probability weights chosen for each stream. If only 1
             * sound is present in the pool, the same sound will always play.
             *
             * Generated from Godot docs: AudioStreamRandomizer.PLAYBACK_RANDOM
             */
            val RANDOM: PlaybackMode get() = PlaybackMode(1L)
            /**
             * Play streams in the order they appear in the stream pool. If only 1 sound is present in the
             * pool, the same sound will always play.
             *
             * Generated from Godot docs: AudioStreamRandomizer.PLAYBACK_SEQUENTIAL
             */
            val SEQUENTIAL: PlaybackMode get() = PlaybackMode(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamRandomizer? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamRandomizer? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamRandomizer(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamRandomizer? =
            if (handle.address() == 0L) null else AudioStreamRandomizer(GodotHandle(handle))
    }

    private object Binds {
        private const val ADD_STREAM_HASH = 1892018854L
        @JvmField
        val addStreamBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "add_stream", ADD_STREAM_HASH)

        private const val MOVE_STREAM_HASH = 3937882851L
        @JvmField
        val moveStreamBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "move_stream", MOVE_STREAM_HASH)

        private const val REMOVE_STREAM_HASH = 1286410249L
        @JvmField
        val removeStreamBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "remove_stream", REMOVE_STREAM_HASH)

        private const val SET_STREAM_HASH = 111075094L
        @JvmField
        val setStreamBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_stream", SET_STREAM_HASH)

        private const val GET_STREAM_HASH = 2739380747L
        @JvmField
        val getStreamBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_stream", GET_STREAM_HASH)

        private const val SET_STREAM_PROBABILITY_WEIGHT_HASH = 1602489585L
        @JvmField
        val setStreamProbabilityWeightBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_stream_probability_weight", SET_STREAM_PROBABILITY_WEIGHT_HASH)

        private const val GET_STREAM_PROBABILITY_WEIGHT_HASH = 2339986948L
        @JvmField
        val getStreamProbabilityWeightBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_stream_probability_weight", GET_STREAM_PROBABILITY_WEIGHT_HASH)

        private const val SET_STREAMS_COUNT_HASH = 1286410249L
        @JvmField
        val setStreamsCountBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_streams_count", SET_STREAMS_COUNT_HASH)

        private const val GET_STREAMS_COUNT_HASH = 3905245786L
        @JvmField
        val getStreamsCountBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_streams_count", GET_STREAMS_COUNT_HASH)

        private const val SET_RANDOM_PITCH_HASH = 373806689L
        @JvmField
        val setRandomPitchBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_random_pitch", SET_RANDOM_PITCH_HASH)

        private const val GET_RANDOM_PITCH_HASH = 1740695150L
        @JvmField
        val getRandomPitchBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_random_pitch", GET_RANDOM_PITCH_HASH)

        private const val SET_RANDOM_PITCH_SEMITONES_HASH = 373806689L
        @JvmField
        val setRandomPitchSemitonesBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_random_pitch_semitones", SET_RANDOM_PITCH_SEMITONES_HASH)

        private const val GET_RANDOM_PITCH_SEMITONES_HASH = 1740695150L
        @JvmField
        val getRandomPitchSemitonesBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_random_pitch_semitones", GET_RANDOM_PITCH_SEMITONES_HASH)

        private const val SET_RANDOM_VOLUME_OFFSET_DB_HASH = 373806689L
        @JvmField
        val setRandomVolumeOffsetDbBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_random_volume_offset_db", SET_RANDOM_VOLUME_OFFSET_DB_HASH)

        private const val GET_RANDOM_VOLUME_OFFSET_DB_HASH = 1740695150L
        @JvmField
        val getRandomVolumeOffsetDbBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_random_volume_offset_db", GET_RANDOM_VOLUME_OFFSET_DB_HASH)

        private const val SET_PLAYBACK_MODE_HASH = 3950967023L
        @JvmField
        val setPlaybackModeBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "set_playback_mode", SET_PLAYBACK_MODE_HASH)

        private const val GET_PLAYBACK_MODE_HASH = 3943055077L
        @JvmField
        val getPlaybackModeBind =
            ObjectCalls.getMethodBind("AudioStreamRandomizer", "get_playback_mode", GET_PLAYBACK_MODE_HASH)
    }
}
