package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a chorus audio effect to an audio bus. Gives the impression of multiple audio sources.
 *
 * Generated from Godot docs: AudioEffectChorus
 */
class AudioEffectChorus(handle: GodotHandle) : AudioEffect(handle) {
    var voiceCount: Int
        @JvmName("voiceCountProperty")
        get() = getVoiceCount()
        @JvmName("setVoiceCountProperty")
        set(value) = setVoiceCount(value)

    var dry: Double
        @JvmName("dryProperty")
        get() = getDry()
        @JvmName("setDryProperty")
        set(value) = setDry(value)

    var wet: Double
        @JvmName("wetProperty")
        get() = getWet()
        @JvmName("setWetProperty")
        set(value) = setWet(value)

    /**
     * The number of voices in the effect. Value can range from 1 to 4.
     *
     * Generated from Godot docs: AudioEffectChorus.set_voice_count
     */
    fun setVoiceCount(voices: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(Binds.setVoiceCountBind, segment, voices)
    }

    /**
     * The number of voices in the effect. Value can range from 1 to 4.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_count
     */
    fun getVoiceCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getVoiceCountBind, segment)
    }

    /**
     * The delay of the voice in milliseconds, compared to the original audio.
     *
     * Generated from Godot docs: AudioEffectChorus.set_voice_delay_ms
     */
    fun setVoiceDelayMs(voiceIdx: Int, delayMs: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setVoiceDelayMsBind, segment, voiceIdx, delayMs)
    }

    /**
     * The delay of the voice in milliseconds, compared to the original audio.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_delay_ms
     */
    fun getVoiceDelayMs(voiceIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getVoiceDelayMsBind, segment, voiceIdx)
    }

    /**
     * The rate of the voice's low-frequency oscillator in Hz.
     *
     * Generated from Godot docs: AudioEffectChorus.set_voice_rate_hz
     */
    fun setVoiceRateHz(voiceIdx: Int, rateHz: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setVoiceRateHzBind, segment, voiceIdx, rateHz)
    }

    /**
     * The rate of the voice's low-frequency oscillator in Hz.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_rate_hz
     */
    fun getVoiceRateHz(voiceIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getVoiceRateHzBind, segment, voiceIdx)
    }

    /**
     * The depth of the voice's low-frequency oscillator in milliseconds.
     *
     * Generated from Godot docs: AudioEffectChorus.set_voice_depth_ms
     */
    fun setVoiceDepthMs(voiceIdx: Int, depthMs: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setVoiceDepthMsBind, segment, voiceIdx, depthMs)
    }

    /**
     * The depth of the voice's low-frequency oscillator in milliseconds.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_depth_ms
     */
    fun getVoiceDepthMs(voiceIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getVoiceDepthMsBind, segment, voiceIdx)
    }

    /**
     * The gain of the voice in dB.
     *
     * Generated from Godot docs: AudioEffectChorus.set_voice_level_db
     */
    fun setVoiceLevelDb(voiceIdx: Int, levelDb: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setVoiceLevelDbBind, segment, voiceIdx, levelDb)
    }

    /**
     * The gain of the voice in dB.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_level_db
     */
    fun getVoiceLevelDb(voiceIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getVoiceLevelDbBind, segment, voiceIdx)
    }

    /**
     * The frequency threshold of the voice's low-pass filter in Hz.
     *
     * Generated from Godot docs: AudioEffectChorus.set_voice_cutoff_hz
     */
    fun setVoiceCutoffHz(voiceIdx: Int, cutoffHz: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setVoiceCutoffHzBind, segment, voiceIdx, cutoffHz)
    }

    /**
     * The frequency threshold of the voice's low-pass filter in Hz.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_cutoff_hz
     */
    fun getVoiceCutoffHz(voiceIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getVoiceCutoffHzBind, segment, voiceIdx)
    }

    /**
     * The pan position of the voice.
     *
     * Generated from Godot docs: AudioEffectChorus.set_voice_pan
     */
    fun setVoicePan(voiceIdx: Int, pan: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setVoicePanBind, segment, voiceIdx, pan)
    }

    /**
     * The pan position of the voice.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_pan
     */
    fun getVoicePan(voiceIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getVoicePanBind, segment, voiceIdx)
    }

    /**
     * The volume ratio of all voices. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectChorus.set_wet
     */
    fun setWet(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setWetBind, segment, amount)
    }

    /**
     * The volume ratio of all voices. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectChorus.get_wet
     */
    fun getWet(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getWetBind, segment)
    }

    /**
     * The volume ratio of the original audio. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectChorus.set_dry
     */
    fun setDry(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDryBind, segment, amount)
    }

    /**
     * The volume ratio of the original audio. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectChorus.get_dry
     */
    fun getDry(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDryBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectChorus? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectChorus? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectChorus(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectChorus? =
            if (handle.address() == 0L) null else AudioEffectChorus(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_VOICE_COUNT_HASH = 1286410249L
        @JvmField
        val setVoiceCountBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_count", SET_VOICE_COUNT_HASH)

        private const val GET_VOICE_COUNT_HASH = 3905245786L
        @JvmField
        val getVoiceCountBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_count", GET_VOICE_COUNT_HASH)

        private const val SET_VOICE_DELAY_MS_HASH = 1602489585L
        @JvmField
        val setVoiceDelayMsBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_delay_ms", SET_VOICE_DELAY_MS_HASH)

        private const val GET_VOICE_DELAY_MS_HASH = 2339986948L
        @JvmField
        val getVoiceDelayMsBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_delay_ms", GET_VOICE_DELAY_MS_HASH)

        private const val SET_VOICE_RATE_HZ_HASH = 1602489585L
        @JvmField
        val setVoiceRateHzBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_rate_hz", SET_VOICE_RATE_HZ_HASH)

        private const val GET_VOICE_RATE_HZ_HASH = 2339986948L
        @JvmField
        val getVoiceRateHzBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_rate_hz", GET_VOICE_RATE_HZ_HASH)

        private const val SET_VOICE_DEPTH_MS_HASH = 1602489585L
        @JvmField
        val setVoiceDepthMsBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_depth_ms", SET_VOICE_DEPTH_MS_HASH)

        private const val GET_VOICE_DEPTH_MS_HASH = 2339986948L
        @JvmField
        val getVoiceDepthMsBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_depth_ms", GET_VOICE_DEPTH_MS_HASH)

        private const val SET_VOICE_LEVEL_DB_HASH = 1602489585L
        @JvmField
        val setVoiceLevelDbBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_level_db", SET_VOICE_LEVEL_DB_HASH)

        private const val GET_VOICE_LEVEL_DB_HASH = 2339986948L
        @JvmField
        val getVoiceLevelDbBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_level_db", GET_VOICE_LEVEL_DB_HASH)

        private const val SET_VOICE_CUTOFF_HZ_HASH = 1602489585L
        @JvmField
        val setVoiceCutoffHzBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_cutoff_hz", SET_VOICE_CUTOFF_HZ_HASH)

        private const val GET_VOICE_CUTOFF_HZ_HASH = 2339986948L
        @JvmField
        val getVoiceCutoffHzBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_cutoff_hz", GET_VOICE_CUTOFF_HZ_HASH)

        private const val SET_VOICE_PAN_HASH = 1602489585L
        @JvmField
        val setVoicePanBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_pan", SET_VOICE_PAN_HASH)

        private const val GET_VOICE_PAN_HASH = 2339986948L
        @JvmField
        val getVoicePanBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_pan", GET_VOICE_PAN_HASH)

        private const val SET_WET_HASH = 373806689L
        @JvmField
        val setWetBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_wet", SET_WET_HASH)

        private const val GET_WET_HASH = 1740695150L
        @JvmField
        val getWetBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_wet", GET_WET_HASH)

        private const val SET_DRY_HASH = 373806689L
        @JvmField
        val setDryBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_dry", SET_DRY_HASH)

        private const val GET_DRY_HASH = 1740695150L
        @JvmField
        val getDryBind =
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_dry", GET_DRY_HASH)
    }
}
