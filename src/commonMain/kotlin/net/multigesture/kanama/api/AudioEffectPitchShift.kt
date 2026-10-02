package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a pitch-shifting audio effect to an audio bus. Raises or lowers the pitch of the input
 * audio.
 *
 * Generated from Godot docs: AudioEffectPitchShift
 */
class AudioEffectPitchShift(handle: GodotHandle) : AudioEffect(handle) {
    var pitchScale: Double
        @JvmName("pitchScaleProperty")
        get() = getPitchScale()
        @JvmName("setPitchScaleProperty")
        set(value) = setPitchScale(value)

    var oversampling: Int
        @JvmName("oversamplingProperty")
        get() = getOversampling()
        @JvmName("setOversamplingProperty")
        set(value) = setOversampling(value)

    var fftSize: AudioEffectPitchShift.FFTSize
        @JvmName("fftSizeProperty")
        get() = getFftSize()
        @JvmName("setFftSizeProperty")
        set(value) = setFftSize(value)

    /**
     * The pitch scale to use. `1.0` is the default pitch and plays sounds unaffected. `pitch_scale`
     * can range from 0 (infinitely low pitch, inaudible) to 16 (16 times higher than the initial
     * pitch).
     *
     * Generated from Godot docs: AudioEffectPitchShift.set_pitch_scale
     */
    fun setPitchScale(rate: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setPitchScaleBind, segment, rate)
    }

    /**
     * The pitch scale to use. `1.0` is the default pitch and plays sounds unaffected. `pitch_scale`
     * can range from 0 (infinitely low pitch, inaudible) to 16 (16 times higher than the initial
     * pitch).
     *
     * Generated from Godot docs: AudioEffectPitchShift.get_pitch_scale
     */
    fun getPitchScale(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getPitchScaleBind, segment)
    }

    /**
     * The oversampling factor to use. Higher values result in better quality, but are more demanding
     * on the CPU and may cause audio cracking if the CPU can't keep up.
     *
     * Generated from Godot docs: AudioEffectPitchShift.set_oversampling
     */
    fun setOversampling(amount: Int) {
        checkOpen()
        ObjectCalls.ptrcallWithIntArg(setOversamplingBind, segment, amount)
    }

    /**
     * The oversampling factor to use. Higher values result in better quality, but are more demanding
     * on the CPU and may cause audio cracking if the CPU can't keep up.
     *
     * Generated from Godot docs: AudioEffectPitchShift.get_oversampling
     */
    fun getOversampling(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getOversamplingBind, segment)
    }

    /**
     * The size of the Fast Fourier transform (https://en.wikipedia.org/wiki/Fast_Fourier_transform)
     * buffer. Higher values smooth out the effect over time, but have greater latency. The effects of
     * this higher latency are especially noticeable on audio signals that have sudden amplitude
     * changes.
     *
     * Generated from Godot docs: AudioEffectPitchShift.set_fft_size
     */
    fun setFftSize(size: AudioEffectPitchShift.FFTSize) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFftSizeBind, segment, size.value)
    }

    /**
     * The size of the Fast Fourier transform (https://en.wikipedia.org/wiki/Fast_Fourier_transform)
     * buffer. Higher values smooth out the effect over time, but have greater latency. The effects of
     * this higher latency are especially noticeable on audio signals that have sudden amplitude
     * changes.
     *
     * Generated from Godot docs: AudioEffectPitchShift.get_fft_size
     */
    fun getFftSize(): AudioEffectPitchShift.FFTSize {
        checkOpen()
        return AudioEffectPitchShift.FFTSize(ObjectCalls.ptrcallNoArgsRetLong(getFftSizeBind, segment))
    }

    /**
     * Godot's `AudioEffectPitchShift.FFTSize` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`AudioEffectPitchShift.FFTSize.<NAME>`).
     *
     * Generated from Godot docs: AudioEffectPitchShift.FFTSize
     */
    @JvmInline
    value class FFTSize(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use a buffer of 256 samples for the Fast Fourier transform. Lowest latency, but least stable
             * over time.
             *
             * Generated from Godot docs: AudioEffectPitchShift.FFT_SIZE_256
             */
            val SIZE_256: FFTSize get() = FFTSize(0L)
            /**
             * Use a buffer of 512 samples for the Fast Fourier transform. Low latency, but less stable over
             * time.
             *
             * Generated from Godot docs: AudioEffectPitchShift.FFT_SIZE_512
             */
            val SIZE_512: FFTSize get() = FFTSize(1L)
            /**
             * Use a buffer of 1024 samples for the Fast Fourier transform. This is a compromise between
             * latency and stability over time.
             *
             * Generated from Godot docs: AudioEffectPitchShift.FFT_SIZE_1024
             */
            val SIZE_1024: FFTSize get() = FFTSize(2L)
            /**
             * Use a buffer of 2048 samples for the Fast Fourier transform. High latency, but stable over time.
             *
             * Generated from Godot docs: AudioEffectPitchShift.FFT_SIZE_2048
             */
            val SIZE_2048: FFTSize get() = FFTSize(3L)
            /**
             * Use a buffer of 4096 samples for the Fast Fourier transform. Highest latency, but most stable
             * over time.
             *
             * Generated from Godot docs: AudioEffectPitchShift.FFT_SIZE_4096
             */
            val SIZE_4096: FFTSize get() = FFTSize(4L)
            /**
             * Represents the size of the `FFTSize` enum.
             *
             * Generated from Godot docs: AudioEffectPitchShift.FFT_SIZE_MAX
             */
            val MAX: FFTSize get() = FFTSize(5L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectPitchShift? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioEffectPitchShift? =
            if (handle.address() == 0L) null else AudioEffectPitchShift(GodotHandle(handle))

        private const val SET_PITCH_SCALE_HASH = 373806689L
        private val setPitchScaleBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPitchShift", "set_pitch_scale", SET_PITCH_SCALE_HASH)
        }

        private const val GET_PITCH_SCALE_HASH = 1740695150L
        private val getPitchScaleBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPitchShift", "get_pitch_scale", GET_PITCH_SCALE_HASH)
        }

        private const val SET_OVERSAMPLING_HASH = 1286410249L
        private val setOversamplingBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPitchShift", "set_oversampling", SET_OVERSAMPLING_HASH)
        }

        private const val GET_OVERSAMPLING_HASH = 3905245786L
        private val getOversamplingBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPitchShift", "get_oversampling", GET_OVERSAMPLING_HASH)
        }

        private const val SET_FFT_SIZE_HASH = 2323518741L
        private val setFftSizeBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPitchShift", "set_fft_size", SET_FFT_SIZE_HASH)
        }

        private const val GET_FFT_SIZE_HASH = 2361246789L
        private val getFftSizeBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPitchShift", "get_fft_size", GET_FFT_SIZE_HASH)
        }
    }
}
