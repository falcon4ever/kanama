package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Stores audio data loaded from WAV files.
 *
 * Generated from Godot docs: AudioStreamWAV
 */
class AudioStreamWAV(handle: MemorySegment) : AudioStream(handle) {
    var data: ByteArray
        @JvmName("dataProperty")
        get() = getData()
        @JvmName("setDataProperty")
        set(value) = setData(value)

    var format: Long
        @JvmName("formatProperty")
        get() = getFormat()
        @JvmName("setFormatProperty")
        set(value) = setFormat(value)

    var loopMode: Long
        @JvmName("loopModeProperty")
        get() = getLoopMode()
        @JvmName("setLoopModeProperty")
        set(value) = setLoopMode(value)

    var loopBegin: Int
        @JvmName("loopBeginProperty")
        get() = getLoopBegin()
        @JvmName("setLoopBeginProperty")
        set(value) = setLoopBegin(value)

    var loopEnd: Int
        @JvmName("loopEndProperty")
        get() = getLoopEnd()
        @JvmName("setLoopEndProperty")
        set(value) = setLoopEnd(value)

    var mixRate: Int
        @JvmName("mixRateProperty")
        get() = getMixRate()
        @JvmName("setMixRateProperty")
        set(value) = setMixRate(value)

    var stereo: Boolean
        @JvmName("stereoProperty")
        get() = isStereo()
        @JvmName("setStereoProperty")
        set(value) = setStereo(value)

    var tags: Map<String, Any?>
        @JvmName("tagsProperty")
        get() = getTags()
        @JvmName("setTagsProperty")
        set(value) = setTags(value)

    fun setData(data: ByteArray) {
        ObjectCalls.ptrcallWithByteArrayArg(setDataBind, handle, data)
    }

    fun getData(): ByteArray {
        return ObjectCalls.ptrcallNoArgsRetByteArray(getDataBind, handle)
    }

    fun setFormat(format: Long) {
        ObjectCalls.ptrcallWithLongArg(setFormatBind, handle, format)
    }

    fun getFormat(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFormatBind, handle)
    }

    fun setLoopMode(loopMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setLoopModeBind, handle, loopMode)
    }

    fun getLoopMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLoopModeBind, handle)
    }

    fun setLoopBegin(loopBegin: Int) {
        ObjectCalls.ptrcallWithIntArg(setLoopBeginBind, handle, loopBegin)
    }

    fun getLoopBegin(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLoopBeginBind, handle)
    }

    fun setLoopEnd(loopEnd: Int) {
        ObjectCalls.ptrcallWithIntArg(setLoopEndBind, handle, loopEnd)
    }

    fun getLoopEnd(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getLoopEndBind, handle)
    }

    fun setMixRate(mixRate: Int) {
        ObjectCalls.ptrcallWithIntArg(setMixRateBind, handle, mixRate)
    }

    fun getMixRate(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMixRateBind, handle)
    }

    fun setStereo(stereo: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setStereoBind, handle, stereo)
    }

    fun isStereo(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isStereoBind, handle)
    }

    fun setTags(tags: Map<String, Any?>) {
        ObjectCalls.ptrcallWithDictionaryArg(setTagsBind, handle, tags)
    }

    fun getTags(): Map<String, Any?> {
        return ObjectCalls.ptrcallNoArgsRetDictionary(getTagsBind, handle)
    }

    fun saveToWav(path: String): Long {
        return ObjectCalls.ptrcallWithStringArgRetLong(saveToWavBind, handle, path)
    }

    companion object {
        fun loadFromBuffer(streamData: ByteArray, options: Map<String, Any?> = emptyMap()): AudioStreamWAV? {
            return AudioStreamWAV.wrap(ObjectCalls.ptrcallWithByteArrayAndDictionaryArgRetObject(loadFromBufferBind, MemorySegment.NULL, streamData, options))
        }

        fun loadFromFile(path: String, options: Map<String, Any?> = emptyMap()): AudioStreamWAV? {
            return AudioStreamWAV.wrap(ObjectCalls.ptrcallWithStringAndDictionaryArgRetObject(loadFromFileBind, MemorySegment.NULL, path, options))
        }

        const val FORMAT_8_BITS: Long = 0L
        const val FORMAT_16_BITS: Long = 1L
        const val FORMAT_IMA_ADPCM: Long = 2L
        const val FORMAT_QOA: Long = 3L
        const val LOOP_DISABLED: Long = 0L
        const val LOOP_FORWARD: Long = 1L
        const val LOOP_PINGPONG: Long = 2L
        const val LOOP_BACKWARD: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamWAV? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamWAV? =
            if (handle.address() == 0L) null else AudioStreamWAV(handle)

        private const val LOAD_FROM_BUFFER_HASH = 4266838938L
        private val loadFromBufferBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "load_from_buffer", LOAD_FROM_BUFFER_HASH)
        }

        private const val LOAD_FROM_FILE_HASH = 4015802384L
        private val loadFromFileBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "load_from_file", LOAD_FROM_FILE_HASH)
        }

        private const val SET_DATA_HASH = 2971499966L
        private val setDataBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_data", SET_DATA_HASH)
        }

        private const val GET_DATA_HASH = 2362200018L
        private val getDataBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_data", GET_DATA_HASH)
        }

        private const val SET_FORMAT_HASH = 60648488L
        private val setFormatBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_format", SET_FORMAT_HASH)
        }

        private const val GET_FORMAT_HASH = 3151724922L
        private val getFormatBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_format", GET_FORMAT_HASH)
        }

        private const val SET_LOOP_MODE_HASH = 2444882972L
        private val setLoopModeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_loop_mode", SET_LOOP_MODE_HASH)
        }

        private const val GET_LOOP_MODE_HASH = 393560655L
        private val getLoopModeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_loop_mode", GET_LOOP_MODE_HASH)
        }

        private const val SET_LOOP_BEGIN_HASH = 1286410249L
        private val setLoopBeginBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_loop_begin", SET_LOOP_BEGIN_HASH)
        }

        private const val GET_LOOP_BEGIN_HASH = 3905245786L
        private val getLoopBeginBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_loop_begin", GET_LOOP_BEGIN_HASH)
        }

        private const val SET_LOOP_END_HASH = 1286410249L
        private val setLoopEndBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_loop_end", SET_LOOP_END_HASH)
        }

        private const val GET_LOOP_END_HASH = 3905245786L
        private val getLoopEndBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_loop_end", GET_LOOP_END_HASH)
        }

        private const val SET_MIX_RATE_HASH = 1286410249L
        private val setMixRateBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_mix_rate", SET_MIX_RATE_HASH)
        }

        private const val GET_MIX_RATE_HASH = 3905245786L
        private val getMixRateBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_mix_rate", GET_MIX_RATE_HASH)
        }

        private const val SET_STEREO_HASH = 2586408642L
        private val setStereoBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_stereo", SET_STEREO_HASH)
        }

        private const val IS_STEREO_HASH = 36873697L
        private val isStereoBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "is_stereo", IS_STEREO_HASH)
        }

        private const val SET_TAGS_HASH = 4155329257L
        private val setTagsBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_tags", SET_TAGS_HASH)
        }

        private const val GET_TAGS_HASH = 3102165223L
        private val getTagsBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_tags", GET_TAGS_HASH)
        }

        private const val SAVE_TO_WAV_HASH = 166001499L
        private val saveToWavBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamWAV", "save_to_wav", SAVE_TO_WAV_HASH)
        }
    }
}
