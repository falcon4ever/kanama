package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Adds a downward compressor audio effect to an audio bus. Allows control of the dynamic range via
 * a volume threshold and timing controls.
 *
 * Generated from Godot docs: AudioEffectCompressor
 */
class AudioEffectCompressor(handle: MemorySegment) : AudioEffect(handle) {
    var threshold: Double
        @JvmName("thresholdProperty")
        get() = getThreshold()
        @JvmName("setThresholdProperty")
        set(value) = setThreshold(value)

    var ratio: Double
        @JvmName("ratioProperty")
        get() = getRatio()
        @JvmName("setRatioProperty")
        set(value) = setRatio(value)

    var gain: Double
        @JvmName("gainProperty")
        get() = getGain()
        @JvmName("setGainProperty")
        set(value) = setGain(value)

    var attackUs: Double
        @JvmName("attackUsProperty")
        get() = getAttackUs()
        @JvmName("setAttackUsProperty")
        set(value) = setAttackUs(value)

    var releaseMs: Double
        @JvmName("releaseMsProperty")
        get() = getReleaseMs()
        @JvmName("setReleaseMsProperty")
        set(value) = setReleaseMs(value)

    var mix: Double
        @JvmName("mixProperty")
        get() = getMix()
        @JvmName("setMixProperty")
        set(value) = setMix(value)

    var sidechain: String
        @JvmName("sidechainProperty")
        get() = getSidechain()
        @JvmName("setSidechainProperty")
        set(value) = setSidechain(value)

    /**
     * The volume level above which compression is applied to the audio, in dB. Value can range from
     * -60 to 0.
     *
     * Generated from Godot docs: AudioEffectCompressor.set_threshold
     */
    fun setThreshold(threshold: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setThresholdBind, handle, threshold)
    }

    /**
     * The volume level above which compression is applied to the audio, in dB. Value can range from
     * -60 to 0.
     *
     * Generated from Godot docs: AudioEffectCompressor.get_threshold
     */
    fun getThreshold(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getThresholdBind, handle)
    }

    /**
     * Amount of compression applied to the audio once it passes the volume threshold level. The higher
     * the ratio, the stronger the compression applied to audio signals that pass the volume threshold
     * level. Value can range from 1 to 48.
     *
     * Generated from Godot docs: AudioEffectCompressor.set_ratio
     */
    fun setRatio(ratio: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRatioBind, handle, ratio)
    }

    /**
     * Amount of compression applied to the audio once it passes the volume threshold level. The higher
     * the ratio, the stronger the compression applied to audio signals that pass the volume threshold
     * level. Value can range from 1 to 48.
     *
     * Generated from Godot docs: AudioEffectCompressor.get_ratio
     */
    fun getRatio(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRatioBind, handle)
    }

    /**
     * Gain of the audio signal, in dB. Value can range from -20 to 20.
     *
     * Generated from Godot docs: AudioEffectCompressor.set_gain
     */
    fun setGain(gain: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGainBind, handle, gain)
    }

    /**
     * Gain of the audio signal, in dB. Value can range from -20 to 20.
     *
     * Generated from Godot docs: AudioEffectCompressor.get_gain
     */
    fun getGain(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGainBind, handle)
    }

    /**
     * Compressor's reaction time when the audio exceeds the volume threshold level, in microseconds.
     * Value can range from 20 to 2000.
     *
     * Generated from Godot docs: AudioEffectCompressor.set_attack_us
     */
    fun setAttackUs(attackUs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAttackUsBind, handle, attackUs)
    }

    /**
     * Compressor's reaction time when the audio exceeds the volume threshold level, in microseconds.
     * Value can range from 20 to 2000.
     *
     * Generated from Godot docs: AudioEffectCompressor.get_attack_us
     */
    fun getAttackUs(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAttackUsBind, handle)
    }

    /**
     * Compressor's delay time to stop decreasing the volume after the it falls below the volume
     * threshold level, in milliseconds. Value can range from 20 to 2000.
     *
     * Generated from Godot docs: AudioEffectCompressor.set_release_ms
     */
    fun setReleaseMs(releaseMs: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setReleaseMsBind, handle, releaseMs)
    }

    /**
     * Compressor's delay time to stop decreasing the volume after the it falls below the volume
     * threshold level, in milliseconds. Value can range from 20 to 2000.
     *
     * Generated from Godot docs: AudioEffectCompressor.get_release_ms
     */
    fun getReleaseMs(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getReleaseMsBind, handle)
    }

    /**
     * Balance between the original audio and the compressed audio. Value can range from 0 (totally
     * dry) to 1 (totally wet).
     *
     * Generated from Godot docs: AudioEffectCompressor.set_mix
     */
    fun setMix(mix: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMixBind, handle, mix)
    }

    /**
     * Balance between the original audio and the compressed audio. Value can range from 0 (totally
     * dry) to 1 (totally wet).
     *
     * Generated from Godot docs: AudioEffectCompressor.get_mix
     */
    fun getMix(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMixBind, handle)
    }

    /**
     * Audio bus to use for the volume threshold detection.
     *
     * Generated from Godot docs: AudioEffectCompressor.set_sidechain
     */
    fun setSidechain(sidechain: String) {
        ObjectCalls.ptrcallWithStringNameArg(setSidechainBind, handle, sidechain)
    }

    /**
     * Audio bus to use for the volume threshold detection.
     *
     * Generated from Godot docs: AudioEffectCompressor.get_sidechain
     */
    fun getSidechain(): String {
        return ObjectCalls.ptrcallNoArgsRetStringName(getSidechainBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectCompressor? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectCompressor? =
            if (handle.address() == 0L) null else AudioEffectCompressor(handle)

        private const val SET_THRESHOLD_HASH = 373806689L
        private val setThresholdBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "set_threshold", SET_THRESHOLD_HASH)
        }

        private const val GET_THRESHOLD_HASH = 1740695150L
        private val getThresholdBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "get_threshold", GET_THRESHOLD_HASH)
        }

        private const val SET_RATIO_HASH = 373806689L
        private val setRatioBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "set_ratio", SET_RATIO_HASH)
        }

        private const val GET_RATIO_HASH = 1740695150L
        private val getRatioBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "get_ratio", GET_RATIO_HASH)
        }

        private const val SET_GAIN_HASH = 373806689L
        private val setGainBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "set_gain", SET_GAIN_HASH)
        }

        private const val GET_GAIN_HASH = 1740695150L
        private val getGainBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "get_gain", GET_GAIN_HASH)
        }

        private const val SET_ATTACK_US_HASH = 373806689L
        private val setAttackUsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "set_attack_us", SET_ATTACK_US_HASH)
        }

        private const val GET_ATTACK_US_HASH = 1740695150L
        private val getAttackUsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "get_attack_us", GET_ATTACK_US_HASH)
        }

        private const val SET_RELEASE_MS_HASH = 373806689L
        private val setReleaseMsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "set_release_ms", SET_RELEASE_MS_HASH)
        }

        private const val GET_RELEASE_MS_HASH = 1740695150L
        private val getReleaseMsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "get_release_ms", GET_RELEASE_MS_HASH)
        }

        private const val SET_MIX_HASH = 373806689L
        private val setMixBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "set_mix", SET_MIX_HASH)
        }

        private const val GET_MIX_HASH = 1740695150L
        private val getMixBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "get_mix", GET_MIX_HASH)
        }

        private const val SET_SIDECHAIN_HASH = 3304788590L
        private val setSidechainBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "set_sidechain", SET_SIDECHAIN_HASH)
        }

        private const val GET_SIDECHAIN_HASH = 2002593661L
        private val getSidechainBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectCompressor", "get_sidechain", GET_SIDECHAIN_HASH)
        }
    }
}
