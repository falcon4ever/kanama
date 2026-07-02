package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: AudioStreamOggVorbis
 */
class AudioStreamOggVorbis(handle: MemorySegment) : AudioStream(handle) {
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
        ObjectCalls.ptrcallWithObjectArgs(setPacketSequenceBind, handle, listOf(packetSequence?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getPacketSequence(): OggPacketSequence? {
        return OggPacketSequence.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPacketSequenceBind, handle))
    }

    fun setLoop(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLoopBind, handle, enable)
    }

    fun hasLoop(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasLoopBind, handle)
    }

    fun setLoopOffset(seconds: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLoopOffsetBind, handle, seconds)
    }

    fun getLoopOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLoopOffsetBind, handle)
    }

    fun setBpm(bpm: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBpmBind, handle, bpm)
    }

    fun getBpm(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBpmBind, handle)
    }

    fun setBeatCount(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setBeatCountBind, handle, count)
    }

    fun getBeatCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBeatCountBind, handle)
    }

    fun setBarBeats(count: Int) {
        ObjectCalls.ptrcallWithIntArg(setBarBeatsBind, handle, count)
    }

    fun getBarBeats(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBarBeatsBind, handle)
    }

    fun setTags(tags: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setTagsBind, handle, tags)
    }

    fun getTags(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getTagsBind, handle)
    }

    companion object {
        fun loadFromBuffer(streamData: ByteArray): AudioStreamOggVorbis? {
            return AudioStreamOggVorbis.wrap(ObjectCalls.ptrcallWithByteArrayArgRetObject(loadFromBufferBind, MemorySegment.NULL, streamData))
        }

        fun loadFromFile(path: String): AudioStreamOggVorbis? {
            return AudioStreamOggVorbis.wrap(ObjectCalls.ptrcallWithStringArgRetObject(loadFromFileBind, MemorySegment.NULL, path))
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamOggVorbis? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamOggVorbis? =
            if (handle.address() == 0L) null else AudioStreamOggVorbis(handle)

        private const val LOAD_FROM_BUFFER_HASH = 354904730L
        private val loadFromBufferBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "load_from_buffer", LOAD_FROM_BUFFER_HASH)
        }

        private const val LOAD_FROM_FILE_HASH = 797568536L
        private val loadFromFileBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "load_from_file", LOAD_FROM_FILE_HASH)
        }

        private const val SET_PACKET_SEQUENCE_HASH = 438882457L
        private val setPacketSequenceBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_packet_sequence", SET_PACKET_SEQUENCE_HASH)
        }

        private const val GET_PACKET_SEQUENCE_HASH = 2801636033L
        private val getPacketSequenceBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "get_packet_sequence", GET_PACKET_SEQUENCE_HASH)
        }

        private const val SET_LOOP_HASH = 2586408642L
        private val setLoopBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_loop", SET_LOOP_HASH)
        }

        private const val HAS_LOOP_HASH = 36873697L
        private val hasLoopBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "has_loop", HAS_LOOP_HASH)
        }

        private const val SET_LOOP_OFFSET_HASH = 373806689L
        private val setLoopOffsetBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_loop_offset", SET_LOOP_OFFSET_HASH)
        }

        private const val GET_LOOP_OFFSET_HASH = 1740695150L
        private val getLoopOffsetBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "get_loop_offset", GET_LOOP_OFFSET_HASH)
        }

        private const val SET_BPM_HASH = 373806689L
        private val setBpmBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_bpm", SET_BPM_HASH)
        }

        private const val GET_BPM_HASH = 1740695150L
        private val getBpmBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "get_bpm", GET_BPM_HASH)
        }

        private const val SET_BEAT_COUNT_HASH = 1286410249L
        private val setBeatCountBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_beat_count", SET_BEAT_COUNT_HASH)
        }

        private const val GET_BEAT_COUNT_HASH = 3905245786L
        private val getBeatCountBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "get_beat_count", GET_BEAT_COUNT_HASH)
        }

        private const val SET_BAR_BEATS_HASH = 1286410249L
        private val setBarBeatsBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_bar_beats", SET_BAR_BEATS_HASH)
        }

        private const val GET_BAR_BEATS_HASH = 3905245786L
        private val getBarBeatsBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "get_bar_beats", GET_BAR_BEATS_HASH)
        }

        private const val SET_TAGS_HASH = 4155329257L
        private val setTagsBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "set_tags", SET_TAGS_HASH)
        }

        private const val GET_TAGS_HASH = 3102165223L
        private val getTagsBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamOggVorbis", "get_tags", GET_TAGS_HASH)
        }
    }
}
