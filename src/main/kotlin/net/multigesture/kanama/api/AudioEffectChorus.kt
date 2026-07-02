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

    fun setVoiceCount(voices: Int) {
        ObjectCalls.ptrcallWithIntArg(setVoiceCountBind, handle, voices)
    }

    fun getVoiceCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getVoiceCountBind, handle)
    }

    fun setVoiceDelayMs(voiceIdx: Int, delayMs: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setVoiceDelayMsBind, handle, voiceIdx, delayMs)
    }

    fun getVoiceDelayMs(voiceIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getVoiceDelayMsBind, handle, voiceIdx)
    }

    fun setVoiceRateHz(voiceIdx: Int, rateHz: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setVoiceRateHzBind, handle, voiceIdx, rateHz)
    }

    fun getVoiceRateHz(voiceIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getVoiceRateHzBind, handle, voiceIdx)
    }

    fun setVoiceDepthMs(voiceIdx: Int, depthMs: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setVoiceDepthMsBind, handle, voiceIdx, depthMs)
    }

    fun getVoiceDepthMs(voiceIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getVoiceDepthMsBind, handle, voiceIdx)
    }

    fun setVoiceLevelDb(voiceIdx: Int, levelDb: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setVoiceLevelDbBind, handle, voiceIdx, levelDb)
    }

    fun getVoiceLevelDb(voiceIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getVoiceLevelDbBind, handle, voiceIdx)
    }

    fun setVoiceCutoffHz(voiceIdx: Int, cutoffHz: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setVoiceCutoffHzBind, handle, voiceIdx, cutoffHz)
    }

    fun getVoiceCutoffHz(voiceIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getVoiceCutoffHzBind, handle, voiceIdx)
    }

    fun setVoicePan(voiceIdx: Int, pan: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setVoicePanBind, handle, voiceIdx, pan)
    }

    fun getVoicePan(voiceIdx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getVoicePanBind, handle, voiceIdx)
    }

    fun setWet(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWetBind, handle, amount)
    }

    fun getWet(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWetBind, handle)
    }

    fun setDry(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDryBind, handle, amount)
    }

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
