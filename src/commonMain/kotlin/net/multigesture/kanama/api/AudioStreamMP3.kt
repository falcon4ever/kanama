package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
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

    fun setData(data: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithByteArrayArg(Binds.setDataBind, segment, data)
    }

    fun getData(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(Binds.getDataBind, segment)
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

    companion object {
        fun loadFromBuffer(streamData: ByteArray): AudioStreamMP3? {
            return AudioStreamMP3.wrapOwned(ObjectCalls.ptrcallWithByteArrayArgRetObject(Binds.loadFromBufferBind, NULL_SEGMENT, streamData))
        }

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
