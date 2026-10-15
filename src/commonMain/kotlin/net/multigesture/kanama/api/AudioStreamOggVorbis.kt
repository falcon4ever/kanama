package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A class representing an Ogg Vorbis audio stream.
 *
 * Generated from Godot docs: AudioStreamOggVorbis
 */
class AudioStreamOggVorbis(handle: GodotHandle) : AudioStream(handle) {
    var packetSequence: OggPacketSequence?
        @JvmName("packetSequenceProperty")
        get() = getPacketSequence()
        @JvmName("setPacketSequenceProperty")
        set(value) = setPacketSequence(value)

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

    var tags: Map<String, Any?>
        @JvmName("tagsProperty")
        get() = getTags()
        @JvmName("setTagsProperty")
        set(value) = setTags(value)

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
     * Contains the raw Ogg data for this stream.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.set_packet_sequence
     */
    fun setPacketSequence(packetSequence: OggPacketSequence?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setPacketSequenceBind, segment, listOf(packetSequence?.requireOpenHandle() ?: NULL_SEGMENT))
        ObjectCalls.keepReachable(this)
        ObjectCalls.keepReachable(packetSequence)
    }

    /**
     * Contains the raw Ogg data for this stream.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.get_packet_sequence
     */
    fun getPacketSequence(): OggPacketSequence? {
        checkOpen()
        val ret = OggPacketSequence.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getPacketSequenceBind, segment))
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, the stream will play again from the specified `loop_offset` once it reaches the end
     * of the audio track, or once it reaches the end of the last beat according to the amount
     * specified in `beat_count`. Useful for ambient sounds and background music.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.set_loop
     */
    fun setLoop(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setLoopBind, segment, enable)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, the stream will play again from the specified `loop_offset` once it reaches the end
     * of the audio track, or once it reaches the end of the last beat according to the amount
     * specified in `beat_count`. Useful for ambient sounds and background music.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.has_loop
     */
    fun hasLoop(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.hasLoopBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Time in seconds at which the stream starts after being looped.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.set_loop_offset
     */
    fun setLoopOffset(seconds: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLoopOffsetBind, segment, seconds)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Time in seconds at which the stream starts after being looped.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.get_loop_offset
     */
    fun getLoopOffset(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLoopOffsetBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The tempo of the audio track, measured in beats per minute.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.set_bpm
     */
    fun setBpm(bpm: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setBpmBind, segment, bpm)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The tempo of the audio track, measured in beats per minute.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.get_bpm
     */
    fun getBpm(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getBpmBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The length of the audio track, in beats. The actual duration of the audio file might be longer
     * than what is indicated by this property. It defines the end of the audio for looping,
     * `AudioStreamPlaylist`, and `AudioStreamInteractive`.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.set_beat_count
     */
    fun setBeatCount(count: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBeatCountBind, segment, count)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The length of the audio track, in beats. The actual duration of the audio file might be longer
     * than what is indicated by this property. It defines the end of the audio for looping,
     * `AudioStreamPlaylist`, and `AudioStreamInteractive`.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.get_beat_count
     */
    fun getBeatCount(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getBeatCountBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * The number of beats within a single bar in the audio track.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.set_bar_beats
     */
    fun setBarBeats(count: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBarBeatsBind, segment, count)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The number of beats within a single bar in the audio track.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.get_bar_beats
     */
    fun getBarBeats(): Int {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetInt(Binds.getBarBeatsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Contains user-defined tags if found in the Ogg Vorbis data. Commonly used tags include `title`,
     * `artist`, `album`, `tracknumber`, and `date` (`date` does not have a standard date format).
     * Note: No tag is guaranteed to be present in every file, so make sure to account for the keys not
     * always existing.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.set_tags
     */
    fun setTags(tags: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithDictionaryArg(Binds.setTagsBind, segment, tags)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Contains user-defined tags if found in the Ogg Vorbis data. Commonly used tags include `title`,
     * `artist`, `album`, `tracknumber`, and `date` (`date` does not have a standard date format).
     * Note: No tag is guaranteed to be present in every file, so make sure to account for the keys not
     * always existing.
     *
     * Generated from Godot docs: AudioStreamOggVorbis.get_tags
     */
    fun getTags(): Map<String, Any?> {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getTagsBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        /**
         * Creates a new `AudioStreamOggVorbis` instance from the given buffer. The buffer must contain Ogg
         * Vorbis data.
         *
         * Generated from Godot docs: AudioStreamOggVorbis.load_from_buffer
         */
        fun loadFromBuffer(streamData: ByteArray): AudioStreamOggVorbis? {
            return AudioStreamOggVorbis.wrapOwned(ObjectCalls.ptrcallWithByteArrayArgRetObject(Binds.loadFromBufferBind, NULL_SEGMENT, streamData))
        }

        /**
         * Creates a new `AudioStreamOggVorbis` instance from the given file path. The file must be in Ogg
         * Vorbis format.
         *
         * Generated from Godot docs: AudioStreamOggVorbis.load_from_file
         */
        fun loadFromFile(path: String): AudioStreamOggVorbis? {
            return AudioStreamOggVorbis.wrapOwned(ObjectCalls.ptrcallWithStringArgRetObject(Binds.loadFromFileBind, NULL_SEGMENT, path))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamOggVorbis? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamOggVorbis? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamOggVorbis(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamOggVorbis? =
            if (handle.address() == 0L) null else AudioStreamOggVorbis(GodotHandle(handle))
    }

    private object Binds {
        private const val LOAD_FROM_BUFFER_HASH = 354904730L
        @JvmField
        val loadFromBufferBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "load_from_buffer", LOAD_FROM_BUFFER_HASH)

        private const val LOAD_FROM_FILE_HASH = 797568536L
        @JvmField
        val loadFromFileBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "load_from_file", LOAD_FROM_FILE_HASH)

        private const val SET_PACKET_SEQUENCE_HASH = 438882457L
        @JvmField
        val setPacketSequenceBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_packet_sequence", SET_PACKET_SEQUENCE_HASH)

        private const val GET_PACKET_SEQUENCE_HASH = 2801636033L
        @JvmField
        val getPacketSequenceBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "get_packet_sequence", GET_PACKET_SEQUENCE_HASH)

        private const val SET_LOOP_HASH = 2586408642L
        @JvmField
        val setLoopBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_loop", SET_LOOP_HASH)

        private const val HAS_LOOP_HASH = 36873697L
        @JvmField
        val hasLoopBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "has_loop", HAS_LOOP_HASH)

        private const val SET_LOOP_OFFSET_HASH = 373806689L
        @JvmField
        val setLoopOffsetBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_loop_offset", SET_LOOP_OFFSET_HASH)

        private const val GET_LOOP_OFFSET_HASH = 1740695150L
        @JvmField
        val getLoopOffsetBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "get_loop_offset", GET_LOOP_OFFSET_HASH)

        private const val SET_BPM_HASH = 373806689L
        @JvmField
        val setBpmBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_bpm", SET_BPM_HASH)

        private const val GET_BPM_HASH = 1740695150L
        @JvmField
        val getBpmBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "get_bpm", GET_BPM_HASH)

        private const val SET_BEAT_COUNT_HASH = 1286410249L
        @JvmField
        val setBeatCountBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_beat_count", SET_BEAT_COUNT_HASH)

        private const val GET_BEAT_COUNT_HASH = 3905245786L
        @JvmField
        val getBeatCountBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "get_beat_count", GET_BEAT_COUNT_HASH)

        private const val SET_BAR_BEATS_HASH = 1286410249L
        @JvmField
        val setBarBeatsBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_bar_beats", SET_BAR_BEATS_HASH)

        private const val GET_BAR_BEATS_HASH = 3905245786L
        @JvmField
        val getBarBeatsBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "get_bar_beats", GET_BAR_BEATS_HASH)

        private const val SET_TAGS_HASH = 4155329257L
        @JvmField
        val setTagsBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_tags", SET_TAGS_HASH)

        private const val GET_TAGS_HASH = 3102165223L
        @JvmField
        val getTagsBind =
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "get_tags", GET_TAGS_HASH)
    }
}
