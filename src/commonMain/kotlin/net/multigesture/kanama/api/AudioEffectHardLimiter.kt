package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Adds a limiter audio effect to an audio bus. Prevents audio signals from exceeding a specified
 * volume level.
 *
 * Generated from Godot docs: AudioEffectHardLimiter
 */
class AudioEffectHardLimiter(handle: GodotHandle) : AudioEffect(handle) {
    var preGainDb: Double
        @JvmName("preGainDbProperty")
        get() = getPreGainDb()
        @JvmName("setPreGainDbProperty")
        set(value) = setPreGainDb(value)

    var ceilingDb: Double
        @JvmName("ceilingDbProperty")
        get() = getCeilingDb()
        @JvmName("setCeilingDbProperty")
        set(value) = setCeilingDb(value)

    var release: Double
        @JvmName("releaseProperty")
        get() = getRelease()
        @JvmName("setReleaseProperty")
        set(value) = setRelease(value)

    /**
     * The waveform's maximum allowed value, in dB. This value can range from -24 to 0. The default
     * value of -0.3 prevents potential inter-sample peaks (ISP) from crossing over 0 dB, which can
     * cause slight distortion on some older hardware.
     *
     * Generated from Godot docs: AudioEffectHardLimiter.set_ceiling_db
     */
    fun setCeilingDb(ceiling: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setCeilingDbBind, segment, ceiling)
    }

    /**
     * The waveform's maximum allowed value, in dB. This value can range from -24 to 0. The default
     * value of -0.3 prevents potential inter-sample peaks (ISP) from crossing over 0 dB, which can
     * cause slight distortion on some older hardware.
     *
     * Generated from Godot docs: AudioEffectHardLimiter.get_ceiling_db
     */
    fun getCeilingDb(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getCeilingDbBind, segment)
    }

    /**
     * Gain before limiting, in dB. Value can range from -24 to 24.
     *
     * Generated from Godot docs: AudioEffectHardLimiter.set_pre_gain_db
     */
    fun setPreGainDb(preGain: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setPreGainDbBind, segment, preGain)
    }

    /**
     * Gain before limiting, in dB. Value can range from -24 to 24.
     *
     * Generated from Godot docs: AudioEffectHardLimiter.get_pre_gain_db
     */
    fun getPreGainDb(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getPreGainDbBind, segment)
    }

    /**
     * Time it takes in seconds for the gain reduction to fully release. Value can range from 0.01 to
     * 3.
     *
     * Generated from Godot docs: AudioEffectHardLimiter.set_release
     */
    fun setRelease(release: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setReleaseBind, segment, release)
    }

    /**
     * Time it takes in seconds for the gain reduction to fully release. Value can range from 0.01 to
     * 3.
     *
     * Generated from Godot docs: AudioEffectHardLimiter.get_release
     */
    fun getRelease(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getReleaseBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectHardLimiter? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioEffectHardLimiter? =
            if (handle.address() == 0L) null else AudioEffectHardLimiter(GodotHandle(handle))

        private const val SET_CEILING_DB_HASH = 373806689L
        private val setCeilingDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectHardLimiter", "set_ceiling_db", SET_CEILING_DB_HASH)
        }

        private const val GET_CEILING_DB_HASH = 1740695150L
        private val getCeilingDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectHardLimiter", "get_ceiling_db", GET_CEILING_DB_HASH)
        }

        private const val SET_PRE_GAIN_DB_HASH = 373806689L
        private val setPreGainDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectHardLimiter", "set_pre_gain_db", SET_PRE_GAIN_DB_HASH)
        }

        private const val GET_PRE_GAIN_DB_HASH = 1740695150L
        private val getPreGainDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectHardLimiter", "get_pre_gain_db", GET_PRE_GAIN_DB_HASH)
        }

        private const val SET_RELEASE_HASH = 373806689L
        private val setReleaseBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectHardLimiter", "set_release", SET_RELEASE_HASH)
        }

        private const val GET_RELEASE_HASH = 1740695150L
        private val getReleaseBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectHardLimiter", "get_release", GET_RELEASE_HASH)
        }
    }
}
