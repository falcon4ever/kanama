package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Queryable instance of an `AudioEffectSpectrumAnalyzer`.
 *
 * Generated from Godot docs: AudioEffectSpectrumAnalyzerInstance
 */
class AudioEffectSpectrumAnalyzerInstance(handle: GodotHandle) : AudioEffectInstance(handle) {
    /**
     * Returns the magnitude of the frequencies from `from_hz` to `to_hz` in linear energy as a
     * Vector2. The `x` component of the return value represents the left stereo channel, and `y`
     * represents the right channel. `mode` determines how the frequency range will be processed.
     *
     * Generated from Godot docs: AudioEffectSpectrumAnalyzerInstance.get_magnitude_for_frequency_range
     */
    fun getMagnitudeForFrequencyRange(fromHz: Double, toHz: Double, mode: Long = 1L): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoDoubleAndLongArgsRetVector2(getMagnitudeForFrequencyRangeBind, segment, fromHz, toHz, mode)
    }

    companion object {
        const val MAGNITUDE_AVERAGE: Long = 0L
        const val MAGNITUDE_MAX: Long = 1L

        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectSpectrumAnalyzerInstance? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): AudioEffectSpectrumAnalyzerInstance? =
            if (handle.address() == 0L) null else AudioEffectSpectrumAnalyzerInstance(GodotHandle(handle))

        private const val GET_MAGNITUDE_FOR_FREQUENCY_RANGE_HASH = 797993915L
        private val getMagnitudeForFrequencyRangeBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectSpectrumAnalyzerInstance", "get_magnitude_for_frequency_range", GET_MAGNITUDE_FOR_FREQUENCY_RANGE_HASH)
        }
    }
}
