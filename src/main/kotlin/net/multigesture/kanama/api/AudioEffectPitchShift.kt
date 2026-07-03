package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Adds a pitch-shifting audio effect to an audio bus. Raises or lowers the pitch of the input
 * audio.
 *
 * Generated from Godot docs: AudioEffectPitchShift
 */
class AudioEffectPitchShift(handle: MemorySegment) : AudioEffect(handle) {
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

    var fftSize: Long
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
        ObjectCalls.ptrcallWithDoubleArg(setPitchScaleBind, handle, rate)
    }

    /**
     * The pitch scale to use. `1.0` is the default pitch and plays sounds unaffected. `pitch_scale`
     * can range from 0 (infinitely low pitch, inaudible) to 16 (16 times higher than the initial
     * pitch).
     *
     * Generated from Godot docs: AudioEffectPitchShift.get_pitch_scale
     */
    fun getPitchScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPitchScaleBind, handle)
    }

    /**
     * The oversampling factor to use. Higher values result in better quality, but are more demanding
     * on the CPU and may cause audio cracking if the CPU can't keep up.
     *
     * Generated from Godot docs: AudioEffectPitchShift.set_oversampling
     */
    fun setOversampling(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setOversamplingBind, handle, amount)
    }

    /**
     * The oversampling factor to use. Higher values result in better quality, but are more demanding
     * on the CPU and may cause audio cracking if the CPU can't keep up.
     *
     * Generated from Godot docs: AudioEffectPitchShift.get_oversampling
     */
    fun getOversampling(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getOversamplingBind, handle)
    }

    /**
     * The size of the Fast Fourier transform (https://en.wikipedia.org/wiki/Fast_Fourier_transform)
     * buffer. Higher values smooth out the effect over time, but have greater latency. The effects of
     * this higher latency are especially noticeable on audio signals that have sudden amplitude
     * changes.
     *
     * Generated from Godot docs: AudioEffectPitchShift.set_fft_size
     */
    fun setFftSize(size: Long) {
        ObjectCalls.ptrcallWithLongArg(setFftSizeBind, handle, size)
    }

    /**
     * The size of the Fast Fourier transform (https://en.wikipedia.org/wiki/Fast_Fourier_transform)
     * buffer. Higher values smooth out the effect over time, but have greater latency. The effects of
     * this higher latency are especially noticeable on audio signals that have sudden amplitude
     * changes.
     *
     * Generated from Godot docs: AudioEffectPitchShift.get_fft_size
     */
    fun getFftSize(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFftSizeBind, handle)
    }

    companion object {
        const val FFT_SIZE_256: Long = 0L
        const val FFT_SIZE_512: Long = 1L
        const val FFT_SIZE_1024: Long = 2L
        const val FFT_SIZE_2048: Long = 3L
        const val FFT_SIZE_4096: Long = 4L
        const val FFT_SIZE_MAX: Long = 5L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectPitchShift? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectPitchShift? =
            if (handle.address() == 0L) null else AudioEffectPitchShift(handle)

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
