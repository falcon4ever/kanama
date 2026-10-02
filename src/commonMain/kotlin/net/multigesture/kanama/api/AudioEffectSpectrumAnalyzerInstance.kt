package net.multigesture.kanama.api

import kotlin.jvm.JvmInline
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
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
    fun getMagnitudeForFrequencyRange(fromHz: Double, toHz: Double, mode: AudioEffectSpectrumAnalyzerInstance.MagnitudeMode = AudioEffectSpectrumAnalyzerInstance.MagnitudeMode.MAX): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoDoubleAndLongArgsRetVector2(getMagnitudeForFrequencyRangeBind, segment, fromHz, toHz, mode.value)
    }

    @JvmInline
    value class MagnitudeMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Use the average value across the frequency range as magnitude.
             *
             * Generated from Godot docs: AudioEffectSpectrumAnalyzerInstance.MAGNITUDE_AVERAGE
             */
            val AVERAGE: MagnitudeMode get() = MagnitudeMode(0L)
            /**
             * Use the maximum value of the frequency range as magnitude.
             *
             * Generated from Godot docs: AudioEffectSpectrumAnalyzerInstance.MAGNITUDE_MAX
             */
            val MAX: MagnitudeMode get() = MagnitudeMode(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectSpectrumAnalyzerInstance? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioEffectSpectrumAnalyzerInstance? =
            if (handle.address() == 0L) null else AudioEffectSpectrumAnalyzerInstance(GodotHandle(handle))

        private const val GET_MAGNITUDE_FOR_FREQUENCY_RANGE_HASH = 797993915L
        private val getMagnitudeForFrequencyRangeBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectSpectrumAnalyzerInstance", "get_magnitude_for_frequency_range", GET_MAGNITUDE_FOR_FREQUENCY_RANGE_HASH)
        }
    }
}
