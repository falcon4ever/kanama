package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Adds a stereo manipulation audio effect to an audio bus. Controls gain of the side channels, and
 * widens the stereo image.
 *
 * Generated from Godot docs: AudioEffectStereoEnhance
 */
class AudioEffectStereoEnhance(handle: MemorySegment) : AudioEffect(handle) {
    var panPullout: Double
        @JvmName("panPulloutProperty")
        get() = getPanPullout()
        @JvmName("setPanPulloutProperty")
        set(value) = setPanPullout(value)

    var timePulloutMs: Double
        @JvmName("timePulloutMsProperty")
        get() = getTimePullout()
        @JvmName("setTimePulloutMsProperty")
        set(value) = setTimePullout(value)

    var surround: Double
        @JvmName("surroundProperty")
        get() = getSurround()
        @JvmName("setSurroundProperty")
        set(value) = setSurround(value)

    /**
     * Gain of the side channels, if they exist. A value of 0 will downmix stereo to mono. Value can
     * range from 0 to 4.
     *
     * Generated from Godot docs: AudioEffectStereoEnhance.set_pan_pullout
     */
    fun setPanPullout(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPanPulloutBind, handle, amount)
    }

    /**
     * Gain of the side channels, if they exist. A value of 0 will downmix stereo to mono. Value can
     * range from 0 to 4.
     *
     * Generated from Godot docs: AudioEffectStereoEnhance.get_pan_pullout
     */
    fun getPanPullout(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPanPulloutBind, handle)
    }

    /**
     * Widens the stereo image through phase shifting in conjunction with `surround`. Just delays the
     * right channel if `surround` is 0. Value is in milliseconds, and can range from 0 to 50.
     *
     * Generated from Godot docs: AudioEffectStereoEnhance.set_time_pullout
     */
    fun setTimePullout(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTimePulloutBind, handle, amount)
    }

    /**
     * Widens the stereo image through phase shifting in conjunction with `surround`. Just delays the
     * right channel if `surround` is 0. Value is in milliseconds, and can range from 0 to 50.
     *
     * Generated from Godot docs: AudioEffectStereoEnhance.get_time_pullout
     */
    fun getTimePullout(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimePulloutBind, handle)
    }

    /**
     * Widens the stereo image through phase shifting in conjunction with `time_pullout_ms`. Just pans
     * sound to the left channel if `time_pullout_ms` is 0. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectStereoEnhance.set_surround
     */
    fun setSurround(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSurroundBind, handle, amount)
    }

    /**
     * Widens the stereo image through phase shifting in conjunction with `time_pullout_ms`. Just pans
     * sound to the left channel if `time_pullout_ms` is 0. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectStereoEnhance.get_surround
     */
    fun getSurround(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSurroundBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectStereoEnhance? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectStereoEnhance? =
            if (handle.address() == 0L) null else AudioEffectStereoEnhance(handle)

        private const val SET_PAN_PULLOUT_HASH = 373806689L
        private val setPanPulloutBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "set_pan_pullout", SET_PAN_PULLOUT_HASH)
        }

        private const val GET_PAN_PULLOUT_HASH = 1740695150L
        private val getPanPulloutBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "get_pan_pullout", GET_PAN_PULLOUT_HASH)
        }

        private const val SET_TIME_PULLOUT_HASH = 373806689L
        private val setTimePulloutBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "set_time_pullout", SET_TIME_PULLOUT_HASH)
        }

        private const val GET_TIME_PULLOUT_HASH = 1740695150L
        private val getTimePulloutBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "get_time_pullout", GET_TIME_PULLOUT_HASH)
        }

        private const val SET_SURROUND_HASH = 373806689L
        private val setSurroundBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "set_surround", SET_SURROUND_HASH)
        }

        private const val GET_SURROUND_HASH = 1740695150L
        private val getSurroundBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "get_surround", GET_SURROUND_HASH)
        }
    }
}
