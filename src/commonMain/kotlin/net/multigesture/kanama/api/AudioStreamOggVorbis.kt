package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
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

    fun setPacketSequence(packetSequence: OggPacketSequence?) {
        checkOpen()
        ObjectCalls.ptrcallWithObjectArgs(Binds.setPacketSequenceBind, segment, listOf(packetSequence?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    fun getPacketSequence(): OggPacketSequence? {
        checkOpen()
        return OggPacketSequence.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getPacketSequenceBind, segment))
    }

    fun setLoop(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setLoopBind, segment, enable)
    }

    fun hasLoop(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.hasLoopBind, segment)
    }

    fun setLoopOffset(seconds: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLoopOffsetBind, segment, seconds)
    }

    fun getLoopOffset(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLoopOffsetBind, segment)
    }

    fun setBpm(bpm: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setBpmBind, segment, bpm)
    }

    fun getBpm(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getBpmBind, segment)
    }

    fun setBeatCount(count: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBeatCountBind, segment, count)
    }

    fun getBeatCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBeatCountBind, segment)
    }

    fun setBarBeats(count: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setBarBeatsBind, segment, count)
    }

    fun getBarBeats(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBarBeatsBind, segment)
    }

    fun setTags(tags: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithDictionaryArg(Binds.setTagsBind, segment, tags)
    }

    fun getTags(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getTagsBind, segment)
    }

    companion object {
        fun loadFromBuffer(streamData: ByteArray): AudioStreamOggVorbis? {
            return AudioStreamOggVorbis.wrapOwned(ObjectCalls.ptrcallWithByteArrayArgRetObject(Binds.loadFromBufferBind, NULL_SEGMENT, streamData))
        }

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
