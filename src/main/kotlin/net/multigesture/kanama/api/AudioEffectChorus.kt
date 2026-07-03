package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Adds a chorus audio effect to an audio bus. Gives the impression of multiple audio sources.
 *
 * Generated from Godot docs: AudioEffectChorus
 */
class AudioEffectChorus(handle: MemorySegment) : AudioEffect(handle) {
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
        ObjectCalls.ptrcallWithIntArg(setVoiceCountBind, handle, voices)
    }

    /**
     * The number of voices in the effect. Value can range from 1 to 4.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_count
     */
    fun getVoiceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVoiceCountBind, handle)
    }

    /**
     * The delay of the voice in milliseconds, compared to the original audio.
     *
     * Generated from Godot docs: AudioEffectChorus.set_voice_delay_ms
     */
    fun setVoiceDelayMs(voiceIdx: Int, delayMs: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setVoiceDelayMsBind, handle, voiceIdx, delayMs)
    }

    /**
     * The delay of the voice in milliseconds, compared to the original audio.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_delay_ms
     */
    fun getVoiceDelayMs(voiceIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getVoiceDelayMsBind, handle, voiceIdx)
    }

    /**
     * The rate of the voice's low-frequency oscillator in Hz.
     *
     * Generated from Godot docs: AudioEffectChorus.set_voice_rate_hz
     */
    fun setVoiceRateHz(voiceIdx: Int, rateHz: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setVoiceRateHzBind, handle, voiceIdx, rateHz)
    }

    /**
     * The rate of the voice's low-frequency oscillator in Hz.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_rate_hz
     */
    fun getVoiceRateHz(voiceIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getVoiceRateHzBind, handle, voiceIdx)
    }

    /**
     * The depth of the voice's low-frequency oscillator in milliseconds.
     *
     * Generated from Godot docs: AudioEffectChorus.set_voice_depth_ms
     */
    fun setVoiceDepthMs(voiceIdx: Int, depthMs: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setVoiceDepthMsBind, handle, voiceIdx, depthMs)
    }

    /**
     * The depth of the voice's low-frequency oscillator in milliseconds.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_depth_ms
     */
    fun getVoiceDepthMs(voiceIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getVoiceDepthMsBind, handle, voiceIdx)
    }

    /**
     * The gain of the voice in dB.
     *
     * Generated from Godot docs: AudioEffectChorus.set_voice_level_db
     */
    fun setVoiceLevelDb(voiceIdx: Int, levelDb: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setVoiceLevelDbBind, handle, voiceIdx, levelDb)
    }

    /**
     * The gain of the voice in dB.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_level_db
     */
    fun getVoiceLevelDb(voiceIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getVoiceLevelDbBind, handle, voiceIdx)
    }

    /**
     * The frequency threshold of the voice's low-pass filter in Hz.
     *
     * Generated from Godot docs: AudioEffectChorus.set_voice_cutoff_hz
     */
    fun setVoiceCutoffHz(voiceIdx: Int, cutoffHz: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setVoiceCutoffHzBind, handle, voiceIdx, cutoffHz)
    }

    /**
     * The frequency threshold of the voice's low-pass filter in Hz.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_cutoff_hz
     */
    fun getVoiceCutoffHz(voiceIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getVoiceCutoffHzBind, handle, voiceIdx)
    }

    /**
     * The pan position of the voice.
     *
     * Generated from Godot docs: AudioEffectChorus.set_voice_pan
     */
    fun setVoicePan(voiceIdx: Int, pan: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setVoicePanBind, handle, voiceIdx, pan)
    }

    /**
     * The pan position of the voice.
     *
     * Generated from Godot docs: AudioEffectChorus.get_voice_pan
     */
    fun getVoicePan(voiceIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getVoicePanBind, handle, voiceIdx)
    }

    /**
     * The volume ratio of all voices. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectChorus.set_wet
     */
    fun setWet(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWetBind, handle, amount)
    }

    /**
     * The volume ratio of all voices. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectChorus.get_wet
     */
    fun getWet(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWetBind, handle)
    }

    /**
     * The volume ratio of the original audio. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectChorus.set_dry
     */
    fun setDry(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDryBind, handle, amount)
    }

    /**
     * The volume ratio of the original audio. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectChorus.get_dry
     */
    fun getDry(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDryBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectChorus? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectChorus? =
            if (handle.address() == 0L) null else AudioEffectChorus(handle)

        private const val SET_VOICE_COUNT_HASH = 1286410249L
        private val setVoiceCountBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_count", SET_VOICE_COUNT_HASH)
        }

        private const val GET_VOICE_COUNT_HASH = 3905245786L
        private val getVoiceCountBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_count", GET_VOICE_COUNT_HASH)
        }

        private const val SET_VOICE_DELAY_MS_HASH = 1602489585L
        private val setVoiceDelayMsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_delay_ms", SET_VOICE_DELAY_MS_HASH)
        }

        private const val GET_VOICE_DELAY_MS_HASH = 2339986948L
        private val getVoiceDelayMsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_delay_ms", GET_VOICE_DELAY_MS_HASH)
        }

        private const val SET_VOICE_RATE_HZ_HASH = 1602489585L
        private val setVoiceRateHzBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_rate_hz", SET_VOICE_RATE_HZ_HASH)
        }

        private const val GET_VOICE_RATE_HZ_HASH = 2339986948L
        private val getVoiceRateHzBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_rate_hz", GET_VOICE_RATE_HZ_HASH)
        }

        private const val SET_VOICE_DEPTH_MS_HASH = 1602489585L
        private val setVoiceDepthMsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_depth_ms", SET_VOICE_DEPTH_MS_HASH)
        }

        private const val GET_VOICE_DEPTH_MS_HASH = 2339986948L
        private val getVoiceDepthMsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_depth_ms", GET_VOICE_DEPTH_MS_HASH)
        }

        private const val SET_VOICE_LEVEL_DB_HASH = 1602489585L
        private val setVoiceLevelDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_level_db", SET_VOICE_LEVEL_DB_HASH)
        }

        private const val GET_VOICE_LEVEL_DB_HASH = 2339986948L
        private val getVoiceLevelDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_level_db", GET_VOICE_LEVEL_DB_HASH)
        }

        private const val SET_VOICE_CUTOFF_HZ_HASH = 1602489585L
        private val setVoiceCutoffHzBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_cutoff_hz", SET_VOICE_CUTOFF_HZ_HASH)
        }

        private const val GET_VOICE_CUTOFF_HZ_HASH = 2339986948L
        private val getVoiceCutoffHzBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_cutoff_hz", GET_VOICE_CUTOFF_HZ_HASH)
        }

        private const val SET_VOICE_PAN_HASH = 1602489585L
        private val setVoicePanBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_voice_pan", SET_VOICE_PAN_HASH)
        }

        private const val GET_VOICE_PAN_HASH = 2339986948L
        private val getVoicePanBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_voice_pan", GET_VOICE_PAN_HASH)
        }

        private const val SET_WET_HASH = 373806689L
        private val setWetBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_wet", SET_WET_HASH)
        }

        private const val GET_WET_HASH = 1740695150L
        private val getWetBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_wet", GET_WET_HASH)
        }

        private const val SET_DRY_HASH = 373806689L
        private val setDryBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "set_dry", SET_DRY_HASH)
        }

        private const val GET_DRY_HASH = 1740695150L
        private val getDryBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectChorus", "get_dry", GET_DRY_HASH)
        }
    }
}
