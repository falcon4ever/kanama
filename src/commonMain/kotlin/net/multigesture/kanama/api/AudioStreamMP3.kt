package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * MP3 audio stream driver.
 *
 * Generated from Godot docs: AudioStreamMP3
 */
class AudioStreamMP3(handle: GodotHandle) : AudioStream(handle) {
    var data: ByteArray
        @JvmName("dataProperty")
        get() = getData()
        @JvmName("setDataProperty")
        set(value) = setData(value)

    var bpm: Double
        @JvmName("bpmProperty")
        get() = getBpm()
        @JvmName("setBpmProperty")
        set(value) = setBpm(value)

    var beatCount: Int
        @JvmName("beatCountProperty")
        get() = getBeatCount()
        @JvmName("setBeatCountProperty")
        set(value) = setBeatCount(value)

    var barBeats: Int
        @JvmName("barBeatsProperty")
        get() = getBarBeats()
        @JvmName("setBarBeatsProperty")
        set(value) = setBarBeats(value)

    var loop: Boolean
        @JvmName("loopProperty")
        get() = hasLoop()
        @JvmName("setLoopProperty")
        set(value) = setLoop(value)

    var loopOffset: Double
        @JvmName("loopOffsetProperty")
        get() = getLoopOffset()
        @JvmName("setLoopOffsetProperty")
        set(value) = setLoopOffset(value)

    /**
     * Contains the audio data in bytes. You can load a file without having to import it beforehand
     * using the code snippet below. Keep in mind that this snippet loads the whole file into memory
     * and may not be ideal for huge files (hundreds of megabytes or more).
     *
     * Generated from Godot docs: AudioStreamMP3.set_data
     */
    fun setData(data: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithByteArrayArg(Binds.setDataBind, segment, data)
    }

    /**
     * Contains the audio data in bytes. You can load a file without having to import it beforehand
     * using the code snippet below. Keep in mind that this snippet loads the whole file into memory
     * and may not be ideal for huge files (hundreds of megabytes or more).
     *
     * Generated from Godot docs: AudioStreamMP3.get_data
     */
    fun getData(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(Binds.getDataBind, segment)
    }

    /**
     * If `true`, the stream will play again from the specified `loop_offset` once it reaches the end
     * of the audio track, or once it reaches the end of the last beat according to the amount
     * specified in `beat_count`. Useful for ambient sounds and background music.
     *
     * Generated from Godot docs: AudioStreamMP3.set_loop
     */
    fun setLoop(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setLoopBind, segment, enable)
    }

    /**
     * If `true`, the stream will play again from the specified `loop_offset` once it reaches the end
     * of the audio track, or once it reaches the end of the last beat according to the amount
     * specified in `beat_count`. Useful for ambient sounds and background music.
     *
     * Generated from Godot docs: AudioStreamMP3.has_loop
     */
    fun hasLoop(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasLoopBind, segment)
    }

    /**
     * Time in seconds at which the stream starts after being looped.
     *
     * Generated from Godot docs: AudioStreamMP3.set_loop_offset
     */
    fun setLoopOffset(seconds: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLoopOffsetBind, segment, seconds)
    }

    /**
     * Time in seconds at which the stream starts after being looped.
     *
     * Generated from Godot docs: AudioStreamMP3.get_loop_offset
     */
    fun getLoopOffset(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLoopOffsetBind, segment)
    }

    /**
     * The tempo of the audio track, measured in beats per minute.
     *
     * Generated from Godot docs: AudioStreamMP3.set_bpm
     */
    fun setBpm(bpm: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setBpmBind, segment, bpm)
    }

    /**
     * The tempo of the audio track, measured in beats per minute.
     *
     * Generated from Godot docs: AudioStreamMP3.get_bpm
     */
    fun getBpm(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getBpmBind, segment)
    }

    /**
     * The length of the audio track, in beats. The actual duration of the audio file might be longer
     * than what is indicated by this property. It defines the end of the audio for looping,
     * `AudioStreamPlaylist`, and `AudioStreamInteractive`.
     *
     * Generated from Godot docs: AudioStreamMP3.set_beat_count
     */
    fun setBeatCount(count: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBeatCountBind, segment, count)
    }

    /**
     * The length of the audio track, in beats. The actual duration of the audio file might be longer
     * than what is indicated by this property. It defines the end of the audio for looping,
     * `AudioStreamPlaylist`, and `AudioStreamInteractive`.
     *
     * Generated from Godot docs: AudioStreamMP3.get_beat_count
     */
    fun getBeatCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBeatCountBind, segment)
    }

    /**
     * The number of beats within a single bar in the audio track.
     *
     * Generated from Godot docs: AudioStreamMP3.set_bar_beats
     */
    fun setBarBeats(count: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBarBeatsBind, segment, count)
    }

    /**
     * The number of beats within a single bar in the audio track.
     *
     * Generated from Godot docs: AudioStreamMP3.get_bar_beats
     */
    fun getBarBeats(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBarBeatsBind, segment)
    }

    companion object {
        /**
         * Creates a new `AudioStreamMP3` instance from the given buffer. The buffer must contain MP3 data.
         *
         * Generated from Godot docs: AudioStreamMP3.load_from_buffer
         */
        fun loadFromBuffer(streamData: ByteArray): AudioStreamMP3? {
            return AudioStreamMP3.wrapOwned(ObjectCalls.ptrcallWithByteArrayArgRetObject(Binds.loadFromBufferBind, NULL_SEGMENT, streamData))
        }

        /**
         * Creates a new `AudioStreamMP3` instance from the given file path. The file must be in MP3
         * format.
         *
         * Generated from Godot docs: AudioStreamMP3.load_from_file
         */
        fun loadFromFile(path: String): AudioStreamMP3? {
            return AudioStreamMP3.wrapOwned(ObjectCalls.ptrcallWithStringArgRetObject(Binds.loadFromFileBind, NULL_SEGMENT, path))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamMP3? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamMP3? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamMP3(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamMP3? =
            if (handle.address() == 0L) null else AudioStreamMP3(GodotHandle(handle))
    }

    private object Binds {
        private const val LOAD_FROM_BUFFER_HASH = 1674970313L
        @JvmField
        val loadFromBufferBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "load_from_buffer", LOAD_FROM_BUFFER_HASH)

        private const val LOAD_FROM_FILE_HASH = 4238362998L
        @JvmField
        val loadFromFileBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "load_from_file", LOAD_FROM_FILE_HASH)

        private const val SET_DATA_HASH = 2971499966L
        @JvmField
        val setDataBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "set_data", SET_DATA_HASH)

        private const val GET_DATA_HASH = 2362200018L
        @JvmField
        val getDataBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "get_data", GET_DATA_HASH)

        private const val SET_LOOP_HASH = 2586408642L
        @JvmField
        val setLoopBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "set_loop", SET_LOOP_HASH)

        private const val HAS_LOOP_HASH = 36873697L
        @JvmField
        val hasLoopBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "has_loop", HAS_LOOP_HASH)

        private const val SET_LOOP_OFFSET_HASH = 373806689L
        @JvmField
        val setLoopOffsetBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "set_loop_offset", SET_LOOP_OFFSET_HASH)

        private const val GET_LOOP_OFFSET_HASH = 1740695150L
        @JvmField
        val getLoopOffsetBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "get_loop_offset", GET_LOOP_OFFSET_HASH)

        private const val SET_BPM_HASH = 373806689L
        @JvmField
        val setBpmBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "set_bpm", SET_BPM_HASH)

        private const val GET_BPM_HASH = 1740695150L
        @JvmField
        val getBpmBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "get_bpm", GET_BPM_HASH)

        private const val SET_BEAT_COUNT_HASH = 1286410249L
        @JvmField
        val setBeatCountBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "set_beat_count", SET_BEAT_COUNT_HASH)

        private const val GET_BEAT_COUNT_HASH = 3905245786L
        @JvmField
        val getBeatCountBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "get_beat_count", GET_BEAT_COUNT_HASH)

        private const val SET_BAR_BEATS_HASH = 1286410249L
        @JvmField
        val setBarBeatsBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "set_bar_beats", SET_BAR_BEATS_HASH)

        private const val GET_BAR_BEATS_HASH = 3905245786L
        @JvmField
        val getBarBeatsBind =
            ObjectCalls.getMethodBind("AudioStreamMP3", "get_bar_beats", GET_BAR_BEATS_HASH)
    }
}
