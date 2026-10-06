package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An audio stream with utilities for procedural sound generation.
 *
 * Generated from Godot docs: AudioStreamGenerator
 */
class AudioStreamGenerator(handle: GodotHandle) : AudioStream(handle) {
    var mixRateMode: AudioStreamGenerator.AudioStreamGeneratorMixRate
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
     * `AudioEffectCapture` as the source of your data, set `mix_rate_mode` to
     * `AudioStreamGeneratorMixRate.INPUT` or `AudioStreamGeneratorMixRate.OUTPUT` to automatically
     * match current `AudioServer` mixing rate.
     *
     * Generated from Godot docs: AudioStreamGenerator.set_mix_rate
     */
    fun setMixRate(hz: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMixRateBind, segment, hz)
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
     * `AudioEffectCapture` as the source of your data, set `mix_rate_mode` to
     * `AudioStreamGeneratorMixRate.INPUT` or `AudioStreamGeneratorMixRate.OUTPUT` to automatically
     * match current `AudioServer` mixing rate.
     *
     * Generated from Godot docs: AudioStreamGenerator.get_mix_rate
     */
    fun getMixRate(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMixRateBind, segment)
    }

    /**
     * Mixing rate mode. If set to `AudioStreamGeneratorMixRate.CUSTOM`, `mix_rate` is used, otherwise
     * current `AudioServer` mixing rate is used.
     *
     * Generated from Godot docs: AudioStreamGenerator.set_mix_rate_mode
     */
    fun setMixRateMode(mode: AudioStreamGenerator.AudioStreamGeneratorMixRate) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setMixRateModeBind, segment, mode.value)
    }

    /**
     * Mixing rate mode. If set to `AudioStreamGeneratorMixRate.CUSTOM`, `mix_rate` is used, otherwise
     * current `AudioServer` mixing rate is used.
     *
     * Generated from Godot docs: AudioStreamGenerator.get_mix_rate_mode
     */
    fun getMixRateMode(): AudioStreamGenerator.AudioStreamGeneratorMixRate {
        checkOpen()
        return AudioStreamGenerator.AudioStreamGeneratorMixRate(ObjectCalls.ptrcallNoArgsRetLong(Binds.getMixRateModeBind, segment))
    }

    /**
     * The length of the buffer to generate (in seconds). Lower values result in less latency, but
     * require the script to generate audio data faster, resulting in increased CPU usage and more risk
     * for audio cracking if the CPU can't keep up.
     *
     * Generated from Godot docs: AudioStreamGenerator.set_buffer_length
     */
    fun setBufferLength(seconds: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setBufferLengthBind, segment, seconds)
    }

    /**
     * The length of the buffer to generate (in seconds). Lower values result in less latency, but
     * require the script to generate audio data faster, resulting in increased CPU usage and more risk
     * for audio cracking if the CPU can't keep up.
     *
     * Generated from Godot docs: AudioStreamGenerator.get_buffer_length
     */
    fun getBufferLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getBufferLengthBind, segment)
    }

    /**
     * Godot's `AudioStreamGenerator.AudioStreamGeneratorMixRate` enum as a typed value: `.value` is
     * the raw number Godot uses, and the companion holds the named values
     * (`AudioStreamGenerator.AudioStreamGeneratorMixRate.<NAME>`).
     *
     * Generated from Godot docs: AudioStreamGenerator.AudioStreamGeneratorMixRate
     */
    @JvmInline
    value class AudioStreamGeneratorMixRate(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Current `AudioServer` output mixing rate.
             *
             * Generated from Godot docs: AudioStreamGenerator.MIX_RATE_OUTPUT
             */
            val OUTPUT: AudioStreamGeneratorMixRate get() = AudioStreamGeneratorMixRate(0L)
            /**
             * Current `AudioServer` input mixing rate.
             *
             * Generated from Godot docs: AudioStreamGenerator.MIX_RATE_INPUT
             */
            val INPUT: AudioStreamGeneratorMixRate get() = AudioStreamGeneratorMixRate(1L)
            /**
             * Custom mixing rate, specified by `mix_rate`.
             *
             * Generated from Godot docs: AudioStreamGenerator.MIX_RATE_CUSTOM
             */
            val CUSTOM: AudioStreamGeneratorMixRate get() = AudioStreamGeneratorMixRate(2L)
            /**
             * Maximum value for the mixing rate mode enum.
             *
             * Generated from Godot docs: AudioStreamGenerator.MIX_RATE_MAX
             */
            val MAX: AudioStreamGeneratorMixRate get() = AudioStreamGeneratorMixRate(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioStreamGenerator? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioStreamGenerator? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioStreamGenerator(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioStreamGenerator? =
            if (handle.address() == 0L) null else AudioStreamGenerator(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_MIX_RATE_HASH = 373806689L
        @JvmField
        val setMixRateBind =
            ObjectCalls.getMethodBind("AudioStreamGenerator", "set_mix_rate", SET_MIX_RATE_HASH)

        private const val GET_MIX_RATE_HASH = 1740695150L
        @JvmField
        val getMixRateBind =
            ObjectCalls.getMethodBind("AudioStreamGenerator", "get_mix_rate", GET_MIX_RATE_HASH)

        private const val SET_MIX_RATE_MODE_HASH = 3354885803L
        @JvmField
        val setMixRateModeBind =
            ObjectCalls.getMethodBind("AudioStreamGenerator", "set_mix_rate_mode", SET_MIX_RATE_MODE_HASH)

        private const val GET_MIX_RATE_MODE_HASH = 3537132591L
        @JvmField
        val getMixRateModeBind =
            ObjectCalls.getMethodBind("AudioStreamGenerator", "get_mix_rate_mode", GET_MIX_RATE_MODE_HASH)

        private const val SET_BUFFER_LENGTH_HASH = 373806689L
        @JvmField
        val setBufferLengthBind =
            ObjectCalls.getMethodBind("AudioStreamGenerator", "set_buffer_length", SET_BUFFER_LENGTH_HASH)

        private const val GET_BUFFER_LENGTH_HASH = 1740695150L
        @JvmField
        val getBufferLengthBind =
            ObjectCalls.getMethodBind("AudioStreamGenerator", "get_buffer_length", GET_BUFFER_LENGTH_HASH)
    }
}
