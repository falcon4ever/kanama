package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base class for filters. Use effects that inherit this class instead of using it directly.
 *
 * Generated from Godot docs: AudioEffectFilter
 */
open class AudioEffectFilter(handle: MemorySegment) : AudioEffect(handle) {
    var cutoffHz: Double
        @JvmName("cutoffHzProperty")
        get() = getCutoff()
        @JvmName("setCutoffHzProperty")
        set(value) = setCutoff(value)

    var resonance: Double
        @JvmName("resonanceProperty")
        get() = getResonance()
        @JvmName("setResonanceProperty")
        set(value) = setResonance(value)

    var gain: Double
        @JvmName("gainProperty")
        get() = getGain()
        @JvmName("setGainProperty")
        set(value) = setGain(value)

    var db: Long
        @JvmName("dbProperty")
        get() = getDb()
        @JvmName("setDbProperty")
        set(value) = setDb(value)

    /**
     * Frequency threshold for the filter, in Hz. Value can range from 1 to 20500.
     *
     * Generated from Godot docs: AudioEffectFilter.set_cutoff
     */
    fun setCutoff(freq: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setCutoffBind, handle, freq)
    }

    /**
     * Frequency threshold for the filter, in Hz. Value can range from 1 to 20500.
     *
     * Generated from Godot docs: AudioEffectFilter.get_cutoff
     */
    fun getCutoff(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getCutoffBind, handle)
    }

    /**
     * Gain at or directly next to the `cutoff_hz` frequency threshold. Value can range from 0 to 1.
     * Its exact behavior depends on the selected filter type: - For shelf filters, it accentuates or
     * masks the order by increasing frequencies right next to the `cutoff_hz` frequency and decreasing
     * frequencies on the opposite side. - For the band-pass and notch filters, it widens or narrows
     * the filter at the `cutoff_hz` frequency threshold. - For low/high-pass filters, it increases or
     * decreases frequencies at the `cutoff_hz` frequency threshold.
     *
     * Generated from Godot docs: AudioEffectFilter.set_resonance
     */
    fun setResonance(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setResonanceBind, handle, amount)
    }

    /**
     * Gain at or directly next to the `cutoff_hz` frequency threshold. Value can range from 0 to 1.
     * Its exact behavior depends on the selected filter type: - For shelf filters, it accentuates or
     * masks the order by increasing frequencies right next to the `cutoff_hz` frequency and decreasing
     * frequencies on the opposite side. - For the band-pass and notch filters, it widens or narrows
     * the filter at the `cutoff_hz` frequency threshold. - For low/high-pass filters, it increases or
     * decreases frequencies at the `cutoff_hz` frequency threshold.
     *
     * Generated from Godot docs: AudioEffectFilter.get_resonance
     */
    fun getResonance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getResonanceBind, handle)
    }

    /**
     * Gain of the frequencies affected by the filter. This property is only available for
     * `AudioEffectLowShelfFilter` and `AudioEffectHighShelfFilter`. Value can range from 0 to 4.
     *
     * Generated from Godot docs: AudioEffectFilter.set_gain
     */
    fun setGain(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGainBind, handle, amount)
    }

    /**
     * Gain of the frequencies affected by the filter. This property is only available for
     * `AudioEffectLowShelfFilter` and `AudioEffectHighShelfFilter`. Value can range from 0 to 4.
     *
     * Generated from Godot docs: AudioEffectFilter.get_gain
     */
    fun getGain(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGainBind, handle)
    }

    /**
     * Steepness of the cutoff curve in dB per octave (twice the frequency above `cutoff_hz`, or half
     * the frequency below `cutoff_hz`), also known as the "order" of the filter. Higher orders have a
     * more aggressive cutoff.
     *
     * Generated from Godot docs: AudioEffectFilter.set_db
     */
    fun setDb(amount: Long) {
        ObjectCalls.ptrcallWithLongArg(setDbBind, handle, amount)
    }

    /**
     * Steepness of the cutoff curve in dB per octave (twice the frequency above `cutoff_hz`, or half
     * the frequency below `cutoff_hz`), also known as the "order" of the filter. Higher orders have a
     * more aggressive cutoff.
     *
     * Generated from Godot docs: AudioEffectFilter.get_db
     */
    fun getDb(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDbBind, handle)
    }

    companion object {
        const val FILTER_6DB: Long = 0L
        const val FILTER_12DB: Long = 1L
        const val FILTER_18DB: Long = 2L
        const val FILTER_24DB: Long = 3L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectFilter? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectFilter? =
            if (handle.address() == 0L) null else AudioEffectFilter(handle)

        private const val SET_CUTOFF_HASH = 373806689L
        private val setCutoffBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectFilter", "set_cutoff", SET_CUTOFF_HASH)
        }

        private const val GET_CUTOFF_HASH = 1740695150L
        private val getCutoffBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectFilter", "get_cutoff", GET_CUTOFF_HASH)
        }

        private const val SET_RESONANCE_HASH = 373806689L
        private val setResonanceBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectFilter", "set_resonance", SET_RESONANCE_HASH)
        }

        private const val GET_RESONANCE_HASH = 1740695150L
        private val getResonanceBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectFilter", "get_resonance", GET_RESONANCE_HASH)
        }

        private const val SET_GAIN_HASH = 373806689L
        private val setGainBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectFilter", "set_gain", SET_GAIN_HASH)
        }

        private const val GET_GAIN_HASH = 1740695150L
        private val getGainBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectFilter", "get_gain", GET_GAIN_HASH)
        }

        private const val SET_DB_HASH = 771740901L
        private val setDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectFilter", "set_db", SET_DB_HASH)
        }

        private const val GET_DB_HASH = 3981721890L
        private val getDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectFilter", "get_db", GET_DB_HASH)
        }
    }
}
