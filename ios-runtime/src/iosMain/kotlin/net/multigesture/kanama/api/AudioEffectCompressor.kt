package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
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

    fun setThreshold(threshold: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setThresholdBind, handle, threshold)
    }

    fun getThreshold(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getThresholdBind, handle)
    }

    fun setRatio(ratio: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setRatioBind, handle, ratio)
    }

    fun getRatio(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getRatioBind, handle)
    }

    fun setGain(gain: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setGainBind, handle, gain)
    }

    fun getGain(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getGainBind, handle)
    }

    fun setAttackUs(attackUs: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setAttackUsBind, handle, attackUs)
    }

    fun getAttackUs(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getAttackUsBind, handle)
    }

    fun setReleaseMs(releaseMs: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setReleaseMsBind, handle, releaseMs)
    }

    fun getReleaseMs(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getReleaseMsBind, handle)
    }

    fun setMix(mix: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setMixBind, handle, mix)
    }

    fun getMix(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getMixBind, handle)
    }

    fun setSidechain(sidechain: String) {
        checkOpen()
        ObjectCalls.ptrcallWithStringNameArg(setSidechainBind, handle, sidechain)
    }

    fun getSidechain(): String {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetStringName(getSidechainBind, handle)
    }

    companion object {
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
