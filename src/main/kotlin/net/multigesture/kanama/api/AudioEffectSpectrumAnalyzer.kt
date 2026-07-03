package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Creates an `AudioEffectInstance` which performs frequency analysis and exposes results to be
 * accessed in real-time.
 *
 * Generated from Godot docs: AudioEffectSpectrumAnalyzer
 */
class AudioEffectSpectrumAnalyzer(handle: MemorySegment) : AudioEffect(handle) {
    var bufferLength: Double
        @JvmName("bufferLengthProperty")
        get() = getBufferLength()
        @JvmName("setBufferLengthProperty")
        set(value) = setBufferLength(value)

    var fftSize: Long
        @JvmName("fftSizeProperty")
        get() = getFftSize()
        @JvmName("setFftSizeProperty")
        set(value) = setFftSize(value)

    /**
     * The length of the buffer to keep, in seconds. Higher values keep data around for longer, but
     * require more memory. Value can range from 0.1 to 4.
     *
     * Generated from Godot docs: AudioEffectSpectrumAnalyzer.set_buffer_length
     */
    fun setBufferLength(seconds: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBufferLengthBind, handle, seconds)
    }

    /**
     * The length of the buffer to keep, in seconds. Higher values keep data around for longer, but
     * require more memory. Value can range from 0.1 to 4.
     *
     * Generated from Godot docs: AudioEffectSpectrumAnalyzer.get_buffer_length
     */
    fun getBufferLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBufferLengthBind, handle)
    }

    /**
     * The size of the Fast Fourier transform (https://en.wikipedia.org/wiki/Fast_Fourier_transform)
     * buffer. Higher values smooth out the spectrum analysis over time, but have greater latency. The
     * effects of this higher latency are especially noticeable with sudden amplitude changes.
     *
     * Generated from Godot docs: AudioEffectSpectrumAnalyzer.set_fft_size
     */
    fun setFftSize(size: Long) {
        ObjectCalls.ptrcallWithLongArg(setFftSizeBind, handle, size)
    }

    /**
     * The size of the Fast Fourier transform (https://en.wikipedia.org/wiki/Fast_Fourier_transform)
     * buffer. Higher values smooth out the spectrum analysis over time, but have greater latency. The
     * effects of this higher latency are especially noticeable with sudden amplitude changes.
     *
     * Generated from Godot docs: AudioEffectSpectrumAnalyzer.get_fft_size
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
        fun fromHandle(handle: MemorySegment): AudioEffectSpectrumAnalyzer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectSpectrumAnalyzer? =
            if (handle.address() == 0L) null else AudioEffectSpectrumAnalyzer(handle)

        private const val SET_BUFFER_LENGTH_HASH = 373806689L
        private val setBufferLengthBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectSpectrumAnalyzer", "set_buffer_length", SET_BUFFER_LENGTH_HASH)
        }

        private const val GET_BUFFER_LENGTH_HASH = 1740695150L
        private val getBufferLengthBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectSpectrumAnalyzer", "get_buffer_length", GET_BUFFER_LENGTH_HASH)
        }

        private const val SET_FFT_SIZE_HASH = 1202879215L
        private val setFftSizeBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectSpectrumAnalyzer", "set_fft_size", SET_FFT_SIZE_HASH)
        }

        private const val GET_FFT_SIZE_HASH = 3925405343L
        private val getFftSizeBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectSpectrumAnalyzer", "get_fft_size", GET_FFT_SIZE_HASH)
        }
    }
}
