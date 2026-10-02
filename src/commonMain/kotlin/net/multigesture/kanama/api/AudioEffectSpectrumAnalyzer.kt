package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Creates an `AudioEffectInstance` which performs frequency analysis and exposes results to be
 * accessed in real-time.
 *
 * Generated from Godot docs: AudioEffectSpectrumAnalyzer
 */
class AudioEffectSpectrumAnalyzer(handle: GodotHandle) : AudioEffect(handle) {
    var bufferLength: Double
        @JvmName("bufferLengthProperty")
        get() = getBufferLength()
        @JvmName("setBufferLengthProperty")
        set(value) = setBufferLength(value)

    var fftSize: AudioEffectSpectrumAnalyzer.FFTSize
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setBufferLengthBind, segment, seconds)
    }

    /**
     * The length of the buffer to keep, in seconds. Higher values keep data around for longer, but
     * require more memory. Value can range from 0.1 to 4.
     *
     * Generated from Godot docs: AudioEffectSpectrumAnalyzer.get_buffer_length
     */
    fun getBufferLength(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getBufferLengthBind, segment)
    }

    /**
     * The size of the Fast Fourier transform (https://en.wikipedia.org/wiki/Fast_Fourier_transform)
     * buffer. Higher values smooth out the spectrum analysis over time, but have greater latency. The
     * effects of this higher latency are especially noticeable with sudden amplitude changes.
     *
     * Generated from Godot docs: AudioEffectSpectrumAnalyzer.set_fft_size
     */
    fun setFftSize(size: AudioEffectSpectrumAnalyzer.FFTSize) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setFftSizeBind, segment, size.value)
    }

    /**
     * The size of the Fast Fourier transform (https://en.wikipedia.org/wiki/Fast_Fourier_transform)
     * buffer. Higher values smooth out the spectrum analysis over time, but have greater latency. The
     * effects of this higher latency are especially noticeable with sudden amplitude changes.
     *
     * Generated from Godot docs: AudioEffectSpectrumAnalyzer.get_fft_size
     */
    fun getFftSize(): AudioEffectSpectrumAnalyzer.FFTSize {
        checkOpen()
        return AudioEffectSpectrumAnalyzer.FFTSize(ObjectCalls.ptrcallNoArgsRetLong(getFftSizeBind, segment))
    }

    @JvmInline
    value class FFTSize(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use a buffer of 256 samples for the Fast Fourier transform. Lowest latency, but least stable
             * over time.
             *
             * Generated from Godot docs: AudioEffectSpectrumAnalyzer.FFT_SIZE_256
             */
            val SIZE_256: FFTSize get() = FFTSize(0L)
            /**
             * Use a buffer of 512 samples for the Fast Fourier transform. Low latency, but less stable over
             * time.
             *
             * Generated from Godot docs: AudioEffectSpectrumAnalyzer.FFT_SIZE_512
             */
            val SIZE_512: FFTSize get() = FFTSize(1L)
            /**
             * Use a buffer of 1024 samples for the Fast Fourier transform. This is a compromise between
             * latency and stability over time.
             *
             * Generated from Godot docs: AudioEffectSpectrumAnalyzer.FFT_SIZE_1024
             */
            val SIZE_1024: FFTSize get() = FFTSize(2L)
            /**
             * Use a buffer of 2048 samples for the Fast Fourier transform. High latency, but stable over time.
             *
             * Generated from Godot docs: AudioEffectSpectrumAnalyzer.FFT_SIZE_2048
             */
            val SIZE_2048: FFTSize get() = FFTSize(3L)
            /**
             * Use a buffer of 4096 samples for the Fast Fourier transform. Highest latency, but most stable
             * over time.
             *
             * Generated from Godot docs: AudioEffectSpectrumAnalyzer.FFT_SIZE_4096
             */
            val SIZE_4096: FFTSize get() = FFTSize(4L)
            /**
             * Represents the size of the `FFTSize` enum.
             *
             * Generated from Godot docs: AudioEffectSpectrumAnalyzer.FFT_SIZE_MAX
             */
            val MAX: FFTSize get() = FFTSize(5L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectSpectrumAnalyzer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioEffectSpectrumAnalyzer? =
            if (handle.address() == 0L) null else AudioEffectSpectrumAnalyzer(GodotHandle(handle))

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
