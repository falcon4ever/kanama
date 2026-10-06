package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Stores audio data loaded from WAV files.
 *
 * Generated from Godot docs: AudioStreamWAV
 */
class AudioStreamWAV(handle: GodotHandle) : AudioStream(handle) {
    var data: ByteArray
        @JvmName("dataProperty")
        get() = getData()
        @JvmName("setDataProperty")
        set(value) = setData(value)

    var format: AudioStreamWAV.Format
        @JvmName("formatProperty")
        get() = getFormat()
        @JvmName("setFormatProperty")
        set(value) = setFormat(value)

    var loopMode: AudioStreamWAV.LoopMode
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

    /**
     * Contains the audio data in bytes. Note: If `format` is set to `Format.FORMAT_8_BITS`, this
     * property expects signed 8-bit PCM data. To convert from unsigned 8-bit PCM, subtract 128 from
     * each byte. Note: If `format` is set to `Format.QOA`, this property expects data from a full QOA
     * file.
     *
     * Generated from Godot docs: AudioStreamWAV.set_data
     */
    fun setData(data: ByteArray) {
        checkOpen()
        ObjectCalls.ptrcallWithByteArrayArg(Binds.setDataBind, segment, data)
    }

    /**
     * Contains the audio data in bytes. Note: If `format` is set to `Format.FORMAT_8_BITS`, this
     * property expects signed 8-bit PCM data. To convert from unsigned 8-bit PCM, subtract 128 from
     * each byte. Note: If `format` is set to `Format.QOA`, this property expects data from a full QOA
     * file.
     *
     * Generated from Godot docs: AudioStreamWAV.get_data
     */
    fun getData(): ByteArray {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetByteArray(Binds.getDataBind, segment)
    }

    /**
     * Audio format.
     *
     * Generated from Godot docs: AudioStreamWAV.set_format
     */
    fun setFormat(format: AudioStreamWAV.Format) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setFormatBind, segment, format.value)
    }

    /**
     * Audio format.
     *
     * Generated from Godot docs: AudioStreamWAV.get_format
     */
    fun getFormat(): AudioStreamWAV.Format {
        checkOpen()
        return AudioStreamWAV.Format(ObjectCalls.ptrcallNoArgsRetLong(Binds.getFormatBind, segment))
    }

    /**
     * The loop mode.
     *
     * Generated from Godot docs: AudioStreamWAV.set_loop_mode
     */
    fun setLoopMode(loopMode: AudioStreamWAV.LoopMode) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setLoopModeBind, segment, loopMode.value)
    }

    /**
     * The loop mode.
     *
     * Generated from Godot docs: AudioStreamWAV.get_loop_mode
     */
    fun getLoopMode(): AudioStreamWAV.LoopMode {
        checkOpen()
        return AudioStreamWAV.LoopMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getLoopModeBind, segment))
    }

    /**
     * The loop start point (in number of samples, relative to the beginning of the stream).
     *
     * Generated from Godot docs: AudioStreamWAV.set_loop_begin
     */
    fun setLoopBegin(loopBegin: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setLoopBeginBind, segment, loopBegin)
    }

    /**
     * The loop start point (in number of samples, relative to the beginning of the stream).
     *
     * Generated from Godot docs: AudioStreamWAV.get_loop_begin
     */
    fun getLoopBegin(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLoopBeginBind, segment)
    }

    /**
     * The loop end point (in number of samples, relative to the beginning of the stream).
     *
     * Generated from Godot docs: AudioStreamWAV.set_loop_end
     */
    fun setLoopEnd(loopEnd: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setLoopEndBind, segment, loopEnd)
    }

    /**
     * The loop end point (in number of samples, relative to the beginning of the stream).
     *
     * Generated from Godot docs: AudioStreamWAV.get_loop_end
     */
    fun getLoopEnd(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getLoopEndBind, segment)
    }

    /**
     * The sample rate for mixing this audio. Higher values require more storage space, but result in
     * better quality. In games, common sample rates in use are `11025`, `16000`, `22050`, `32000`,
     * `44100`, and `48000`. According to the Nyquist-Shannon sampling theorem
     * (https://en.wikipedia.org/wiki/Nyquist%E2%80%93Shannon_sampling_theorem), there is no quality
     * difference to human hearing when going past 40,000 Hz (since most humans can only hear up to
     * ~20,000 Hz, often less). If you are using lower-pitched sounds such as voices, lower sample
     * rates such as `32000` or `22050` may be usable with no loss in quality.
     *
     * Generated from Godot docs: AudioStreamWAV.set_mix_rate
     */
    fun setMixRate(mixRate: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setMixRateBind, segment, mixRate)
    }

    /**
     * The sample rate for mixing this audio. Higher values require more storage space, but result in
     * better quality. In games, common sample rates in use are `11025`, `16000`, `22050`, `32000`,
     * `44100`, and `48000`. According to the Nyquist-Shannon sampling theorem
     * (https://en.wikipedia.org/wiki/Nyquist%E2%80%93Shannon_sampling_theorem), there is no quality
     * difference to human hearing when going past 40,000 Hz (since most humans can only hear up to
     * ~20,000 Hz, often less). If you are using lower-pitched sounds such as voices, lower sample
     * rates such as `32000` or `22050` may be usable with no loss in quality.
     *
     * Generated from Godot docs: AudioStreamWAV.get_mix_rate
     */
    fun getMixRate(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMixRateBind, segment)
    }

    /**
     * If `true`, audio is stereo.
     *
     * Generated from Godot docs: AudioStreamWAV.set_stereo
     */
    fun setStereo(stereo: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setStereoBind, segment, stereo)
    }

    /**
     * If `true`, audio is stereo.
     *
     * Generated from Godot docs: AudioStreamWAV.is_stereo
     */
    fun isStereo(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isStereoBind, segment)
    }

    /**
     * Contains user-defined tags if found in the WAV data. Commonly used tags include `title`,
     * `artist`, `album`, `tracknumber`, and `date` (`date` does not have a standard date format).
     * Note: No tag is guaranteed to be present in every file, so make sure to account for the keys not
     * always existing. Note: Only WAV files using a `LIST` chunk with an identifier of `INFO` to
     * encode the tags are currently supported.
     *
     * Generated from Godot docs: AudioStreamWAV.set_tags
     */
    fun setTags(tags: Map<String, Any?>) {
        checkOpen()
        ObjectCalls.ptrcallWithDictionaryArg(Binds.setTagsBind, segment, tags)
    }

    /**
     * Contains user-defined tags if found in the WAV data. Commonly used tags include `title`,
     * `artist`, `album`, `tracknumber`, and `date` (`date` does not have a standard date format).
     * Note: No tag is guaranteed to be present in every file, so make sure to account for the keys not
     * always existing. Note: Only WAV files using a `LIST` chunk with an identifier of `INFO` to
     * encode the tags are currently supported.
     *
     * Generated from Godot docs: AudioStreamWAV.get_tags
     */
    fun getTags(): Map<String, Any?> {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDictionary(Binds.getTagsBind, segment)
    }

    /**
     * Saves the AudioStreamWAV as a WAV file to `path`. Samples with IMA ADPCM or Quite OK Audio
     * formats can't be saved. Note: A `.wav` extension is automatically appended to `path` if it is
     * missing.
     *
     * Generated from Godot docs: AudioStreamWAV.save_to_wav
     */
    fun saveToWav(path: String): GodotError {
        checkOpen()
        return GodotError(ObjectCalls.ptrcallWithStringArgRetLong(Binds.saveToWavBind, segment, path))
    }

    /**
     * Godot's `AudioStreamWAV.Format` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`AudioStreamWAV.Format.<NAME>`).
     *
     * Generated from Godot docs: AudioStreamWAV.Format
     */
    @JvmInline
    value class Format(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * 8-bit PCM audio codec.
             *
             * Generated from Godot docs: AudioStreamWAV.FORMAT_8_BITS
             */
            val FORMAT_8_BITS: Format get() = Format(0L)
            /**
             * 16-bit PCM audio codec.
             *
             * Generated from Godot docs: AudioStreamWAV.FORMAT_16_BITS
             */
            val FORMAT_16_BITS: Format get() = Format(1L)
            /**
             * Audio is lossily compressed as IMA ADPCM.
             *
             * Generated from Godot docs: AudioStreamWAV.FORMAT_IMA_ADPCM
             */
            val IMA_ADPCM: Format get() = Format(2L)
            /**
             * Audio is lossily compressed as Quite OK Audio (https://qoaformat.org/).
             *
             * Generated from Godot docs: AudioStreamWAV.FORMAT_QOA
             */
            val QOA: Format get() = Format(3L)
        }
    }

    /**
     * Godot's `AudioStreamWAV.LoopMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`AudioStreamWAV.LoopMode.<NAME>`).
     *
     * Generated from Godot docs: AudioStreamWAV.LoopMode
     */
    @JvmInline
    value class LoopMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Audio does not loop.
             *
             * Generated from Godot docs: AudioStreamWAV.LOOP_DISABLED
             */
            val DISABLED: LoopMode get() = LoopMode(0L)
            /**
             * Audio loops the data between `loop_begin` and `loop_end`, playing forward only.
             *
             * Generated from Godot docs: AudioStreamWAV.LOOP_FORWARD
             */
            val FORWARD: LoopMode get() = LoopMode(1L)
            /**
             * Audio loops the data between `loop_begin` and `loop_end`, playing back and forth.
             *
             * Generated from Godot docs: AudioStreamWAV.LOOP_PINGPONG
             */
            val PINGPONG: LoopMode get() = LoopMode(2L)
            /**
             * Audio loops the data between `loop_begin` and `loop_end`, playing backward only.
             *
             * Generated from Godot docs: AudioStreamWAV.LOOP_BACKWARD
             */
            val BACKWARD: LoopMode get() = LoopMode(3L)
        }
    }

    companion object {
        /**
         * Creates a new `AudioStreamWAV` instance from the given buffer. The buffer must contain WAV data.
         * The keys and values of `options` match the properties of `ResourceImporterWAV`. The usage of
         * `options` is identical to `AudioStreamWAV.load_from_file`.
         *
         * Generated from Godot docs: AudioStreamWAV.load_from_buffer
         */
        fun loadFromBuffer(streamData: ByteArray, options: Map<String, Any?> = emptyMap()): AudioStreamWAV? {
            return AudioStreamWAV.wrapOwned(ObjectCalls.ptrcallWithByteArrayAndDictionaryArgRetObject(Binds.loadFromBufferBind, NULL_SEGMENT, streamData, options))
        }

        /**
         * Creates a new `AudioStreamWAV` instance from the given file path. The file must be in WAV
         * format. The keys and values of `options` match the properties of `ResourceImporterWAV`.
         *
         * Generated from Godot docs: AudioStreamWAV.load_from_file
         */
        fun loadFromFile(path: String, options: Map<String, Any?> = emptyMap()): AudioStreamWAV? {
            return AudioStreamWAV.wrapOwned(ObjectCalls.ptrcallWithStringAndDictionaryArgRetObject(Binds.loadFromFileBind, NULL_SEGMENT, path, options))
        }

        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamWAV? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamWAV? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamWAV(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamWAV? =
            if (handle.address() == 0L) null else AudioStreamWAV(GodotHandle(handle))
    }

    private object Binds {
        private const val LOAD_FROM_BUFFER_HASH = 4266838938L
        @JvmField
        val loadFromBufferBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "load_from_buffer", LOAD_FROM_BUFFER_HASH)

        private const val LOAD_FROM_FILE_HASH = 4015802384L
        @JvmField
        val loadFromFileBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "load_from_file", LOAD_FROM_FILE_HASH)

        private const val SET_DATA_HASH = 2971499966L
        @JvmField
        val setDataBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_data", SET_DATA_HASH)

        private const val GET_DATA_HASH = 2362200018L
        @JvmField
        val getDataBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_data", GET_DATA_HASH)

        private const val SET_FORMAT_HASH = 60648488L
        @JvmField
        val setFormatBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_format", SET_FORMAT_HASH)

        private const val GET_FORMAT_HASH = 3151724922L
        @JvmField
        val getFormatBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_format", GET_FORMAT_HASH)

        private const val SET_LOOP_MODE_HASH = 2444882972L
        @JvmField
        val setLoopModeBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_loop_mode", SET_LOOP_MODE_HASH)

        private const val GET_LOOP_MODE_HASH = 393560655L
        @JvmField
        val getLoopModeBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_loop_mode", GET_LOOP_MODE_HASH)

        private const val SET_LOOP_BEGIN_HASH = 1286410249L
        @JvmField
        val setLoopBeginBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_loop_begin", SET_LOOP_BEGIN_HASH)

        private const val GET_LOOP_BEGIN_HASH = 3905245786L
        @JvmField
        val getLoopBeginBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_loop_begin", GET_LOOP_BEGIN_HASH)

        private const val SET_LOOP_END_HASH = 1286410249L
        @JvmField
        val setLoopEndBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_loop_end", SET_LOOP_END_HASH)

        private const val GET_LOOP_END_HASH = 3905245786L
        @JvmField
        val getLoopEndBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_loop_end", GET_LOOP_END_HASH)

        private const val SET_MIX_RATE_HASH = 1286410249L
        @JvmField
        val setMixRateBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_mix_rate", SET_MIX_RATE_HASH)

        private const val GET_MIX_RATE_HASH = 3905245786L
        @JvmField
        val getMixRateBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_mix_rate", GET_MIX_RATE_HASH)

        private const val SET_STEREO_HASH = 2586408642L
        @JvmField
        val setStereoBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_stereo", SET_STEREO_HASH)

        private const val IS_STEREO_HASH = 36873697L
        @JvmField
        val isStereoBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "is_stereo", IS_STEREO_HASH)

        private const val SET_TAGS_HASH = 4155329257L
        @JvmField
        val setTagsBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "set_tags", SET_TAGS_HASH)

        private const val GET_TAGS_HASH = 3102165223L
        @JvmField
        val getTagsBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "get_tags", GET_TAGS_HASH)

        private const val SAVE_TO_WAV_HASH = 166001499L
        @JvmField
        val saveToWavBind =
            ObjectCalls.getMethodBind("AudioStreamWAV", "save_to_wav", SAVE_TO_WAV_HASH)
    }
}
