package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: AudioStreamMP3
 */
class AudioStreamMP3(handle: MemorySegment) : AudioStream(handle) {
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
        ObjectCalls.ptrcallWithByteArrayArg(setDataBind, handle, data)
    }

    fun getData(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(getDataBind, handle)
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

    companion object {
        fun loadFromBuffer(streamData: ByteArray): AudioStreamMP3? {
            return AudioStreamMP3.wrap(ObjectCalls.ptrcallWithByteArrayArgRetObject(loadFromBufferBind, MemorySegment.NULL, streamData))
        }

        fun loadFromFile(path: String): AudioStreamMP3? {
            return AudioStreamMP3.wrap(ObjectCalls.ptrcallWithStringArgRetObject(loadFromFileBind, MemorySegment.NULL, path))
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamMP3? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamMP3? =
            if (handle.address() == 0L) null else AudioStreamMP3(handle)

        private const val LOAD_FROM_BUFFER_HASH = 1674970313L
        private val loadFromBufferBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "load_from_buffer", LOAD_FROM_BUFFER_HASH)
        }

        private const val LOAD_FROM_FILE_HASH = 4238362998L
        private val loadFromFileBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "load_from_file", LOAD_FROM_FILE_HASH)
        }

        private const val SET_DATA_HASH = 2971499966L
        private val setDataBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "set_data", SET_DATA_HASH)
        }

        private const val GET_DATA_HASH = 2362200018L
        private val getDataBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "get_data", GET_DATA_HASH)
        }

        private const val SET_LOOP_HASH = 2586408642L
        private val setLoopBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "set_loop", SET_LOOP_HASH)
        }

        private const val HAS_LOOP_HASH = 36873697L
        private val hasLoopBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "has_loop", HAS_LOOP_HASH)
        }

        private const val SET_LOOP_OFFSET_HASH = 373806689L
        private val setLoopOffsetBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "set_loop_offset", SET_LOOP_OFFSET_HASH)
        }

        private const val GET_LOOP_OFFSET_HASH = 1740695150L
        private val getLoopOffsetBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "get_loop_offset", GET_LOOP_OFFSET_HASH)
        }

        private const val SET_BPM_HASH = 373806689L
        private val setBpmBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "set_bpm", SET_BPM_HASH)
        }

        private const val GET_BPM_HASH = 1740695150L
        private val getBpmBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "get_bpm", GET_BPM_HASH)
        }

        private const val SET_BEAT_COUNT_HASH = 1286410249L
        private val setBeatCountBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "set_beat_count", SET_BEAT_COUNT_HASH)
        }

        private const val GET_BEAT_COUNT_HASH = 3905245786L
        private val getBeatCountBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "get_beat_count", GET_BEAT_COUNT_HASH)
        }

        private const val SET_BAR_BEATS_HASH = 1286410249L
        private val setBarBeatsBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "set_bar_beats", SET_BAR_BEATS_HASH)
        }

        private const val GET_BAR_BEATS_HASH = 3905245786L
        private val getBarBeatsBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamMP3", "get_bar_beats", GET_BAR_BEATS_HASH)
        }
    }
}
