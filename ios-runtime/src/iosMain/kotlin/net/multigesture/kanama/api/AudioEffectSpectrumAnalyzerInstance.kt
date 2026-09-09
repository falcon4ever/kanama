package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: AudioEffectSpectrumAnalyzerInstance
 */
class AudioEffectSpectrumAnalyzerInstance(handle: MemorySegment) : AudioEffectInstance(handle) {
    fun getMagnitudeForFrequencyRange(fromHz: Double, toHz: Double, mode: Long = 1L): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoDoubleAndLongArgsRetVector2(getMagnitudeForFrequencyRangeBind, handle, fromHz, toHz, mode)
    }

    companion object {
        const val MAGNITUDE_AVERAGE: Long = 0L
        const val MAGNITUDE_MAX: Long = 1L

        fun fromHandle(handle: MemorySegment): AudioEffectSpectrumAnalyzerInstance? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectSpectrumAnalyzerInstance? =
            if (handle.address() == 0L) null else AudioEffectSpectrumAnalyzerInstance(handle)

        private const val GET_MAGNITUDE_FOR_FREQUENCY_RANGE_HASH = 797993915L
        private val getMagnitudeForFrequencyRangeBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectSpectrumAnalyzerInstance", "get_magnitude_for_frequency_range", GET_MAGNITUDE_FOR_FREQUENCY_RANGE_HASH)
        }
    }
}
