package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * An audio stream with utilities for procedural sound generation.
 *
 * Generated from Godot docs: AudioStreamGenerator
 */
class AudioStreamGenerator(handle: MemorySegment) : AudioStream(handle) {
    var mixRateMode: Long
        @JvmName("mixRateModeProperty")
        get() = getMixRateMode()
        @JvmName("setMixRateModeProperty")
        set(value) = setMixRateMode(value)

    var mixRate: Double
        @JvmName("mixRateProperty")
        get() = getMixRate()
        @JvmName("setMixRateProperty")
        set(value) = setMixRate(value)

    var bufferLength: Double
        @JvmName("bufferLengthProperty")
        get() = getBufferLength()
        @JvmName("setBufferLengthProperty")
        set(value) = setBufferLength(value)

    /**
     * The sample rate to use (in Hz). Higher values are more demanding for the CPU to generate, but
     * result in better quality. In games, common sample rates in use are `11025`, `16000`, `22050`,
     * `32000`, `44100`, and `48000`. According to the Nyquist-Shannon sampling theorem
     * (https://en.wikipedia.org/wiki/Nyquist%E2%80%93Shannon_sampling_theorem), there is no quality
     * difference to human hearing when going past 40,000 Hz (since most humans can only hear up to
     * ~20,000 Hz, often less). If you are generating lower-pitched sounds such as voices, lower sample
     * rates such as `32000` or `22050` may be usable with no loss in quality. Note:
     * `AudioStreamGenerator` is not automatically resampling input data, to produce expected result
     * `mix_rate_mode` should match the sampling rate of input data. Note: If you are using
     * `AudioEffectCapture` as the source of your data, set `mix_rate_mode` to `MIX_RATE_INPUT` or
     * `MIX_RATE_OUTPUT` to automatically match current `AudioServer` mixing rate.
     *
     * Generated from Godot docs: AudioStreamGenerator.set_mix_rate
     */
    fun setMixRate(hz: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMixRateBind, handle, hz)
    }

    /**
     * The sample rate to use (in Hz). Higher values are more demanding for the CPU to generate, but
     * result in better quality. In games, common sample rates in use are `11025`, `16000`, `22050`,
     * `32000`, `44100`, and `48000`. According to the Nyquist-Shannon sampling theorem
     * (https://en.wikipedia.org/wiki/Nyquist%E2%80%93Shannon_sampling_theorem), there is no quality
     * difference to human hearing when going past 40,000 Hz (since most humans can only hear up to
     * ~20,000 Hz, often less). If you are generating lower-pitched sounds such as voices, lower sample
     * rates such as `32000` or `22050` may be usable with no loss in quality. Note:
     * `AudioStreamGenerator` is not automatically resampling input data, to produce expected result
     * `mix_rate_mode` should match the sampling rate of input data. Note: If you are using
     * `AudioEffectCapture` as the source of your data, set `mix_rate_mode` to `MIX_RATE_INPUT` or
     * `MIX_RATE_OUTPUT` to automatically match current `AudioServer` mixing rate.
     *
     * Generated from Godot docs: AudioStreamGenerator.get_mix_rate
     */
    fun getMixRate(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMixRateBind, handle)
    }

    /**
     * Mixing rate mode. If set to `MIX_RATE_CUSTOM`, `mix_rate` is used, otherwise current
     * `AudioServer` mixing rate is used.
     *
     * Generated from Godot docs: AudioStreamGenerator.set_mix_rate_mode
     */
    fun setMixRateMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setMixRateModeBind, handle, mode)
    }

    /**
     * Mixing rate mode. If set to `MIX_RATE_CUSTOM`, `mix_rate` is used, otherwise current
     * `AudioServer` mixing rate is used.
     *
     * Generated from Godot docs: AudioStreamGenerator.get_mix_rate_mode
     */
    fun getMixRateMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMixRateModeBind, handle)
    }

    /**
     * The length of the buffer to generate (in seconds). Lower values result in less latency, but
     * require the script to generate audio data faster, resulting in increased CPU usage and more risk
     * for audio cracking if the CPU can't keep up.
     *
     * Generated from Godot docs: AudioStreamGenerator.set_buffer_length
     */
    fun setBufferLength(seconds: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBufferLengthBind, handle, seconds)
    }

    /**
     * The length of the buffer to generate (in seconds). Lower values result in less latency, but
     * require the script to generate audio data faster, resulting in increased CPU usage and more risk
     * for audio cracking if the CPU can't keep up.
     *
     * Generated from Godot docs: AudioStreamGenerator.get_buffer_length
     */
    fun getBufferLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBufferLengthBind, handle)
    }

    companion object {
        const val MIX_RATE_OUTPUT: Long = 0L
        const val MIX_RATE_INPUT: Long = 1L
        const val MIX_RATE_CUSTOM: Long = 2L
        const val MIX_RATE_MAX: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioStreamGenerator? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioStreamGenerator? =
            if (handle.address() == 0L) null else AudioStreamGenerator(handle)

        private const val SET_MIX_RATE_HASH = 373806689L
        private val setMixRateBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGenerator", "set_mix_rate", SET_MIX_RATE_HASH)
        }

        private const val GET_MIX_RATE_HASH = 1740695150L
        private val getMixRateBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGenerator", "get_mix_rate", GET_MIX_RATE_HASH)
        }

        private const val SET_MIX_RATE_MODE_HASH = 3354885803L
        private val setMixRateModeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGenerator", "set_mix_rate_mode", SET_MIX_RATE_MODE_HASH)
        }

        private const val GET_MIX_RATE_MODE_HASH = 3537132591L
        private val getMixRateModeBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGenerator", "get_mix_rate_mode", GET_MIX_RATE_MODE_HASH)
        }

        private const val SET_BUFFER_LENGTH_HASH = 373806689L
        private val setBufferLengthBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGenerator", "set_buffer_length", SET_BUFFER_LENGTH_HASH)
        }

        private const val GET_BUFFER_LENGTH_HASH = 1740695150L
        private val getBufferLengthBind by lazy {
            ObjectCalls.getMethodBind("AudioStreamGenerator", "get_buffer_length", GET_BUFFER_LENGTH_HASH)
        }
    }
}
