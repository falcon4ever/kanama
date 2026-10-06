package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for filters. Use effects that inherit this class instead of using it directly.
 *
 * Generated from Godot docs: AudioEffectFilter
 */
open class AudioEffectFilter(handle: GodotHandle) : AudioEffect(handle) {
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

    var db: AudioEffectFilter.FilterDB
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCutoffBind, segment, freq)
    }

    /**
     * Frequency threshold for the filter, in Hz. Value can range from 1 to 20500.
     *
     * Generated from Godot docs: AudioEffectFilter.get_cutoff
     */
    fun getCutoff(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCutoffBind, segment)
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setResonanceBind, segment, amount)
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
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getResonanceBind, segment)
    }

    /**
     * Gain of the frequencies affected by the filter. This property is only available for
     * `AudioEffectLowShelfFilter` and `AudioEffectHighShelfFilter`. Value can range from 0 to 4.
     *
     * Generated from Godot docs: AudioEffectFilter.set_gain
     */
    fun setGain(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setGainBind, segment, amount)
    }

    /**
     * Gain of the frequencies affected by the filter. This property is only available for
     * `AudioEffectLowShelfFilter` and `AudioEffectHighShelfFilter`. Value can range from 0 to 4.
     *
     * Generated from Godot docs: AudioEffectFilter.get_gain
     */
    fun getGain(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getGainBind, segment)
    }

    /**
     * Steepness of the cutoff curve in dB per octave (twice the frequency above `cutoff_hz`, or half
     * the frequency below `cutoff_hz`), also known as the "order" of the filter. Higher orders have a
     * more aggressive cutoff.
     *
     * Generated from Godot docs: AudioEffectFilter.set_db
     */
    fun setDb(amount: AudioEffectFilter.FilterDB) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(Binds.setDbBind, segment, amount.value)
    }

    /**
     * Steepness of the cutoff curve in dB per octave (twice the frequency above `cutoff_hz`, or half
     * the frequency below `cutoff_hz`), also known as the "order" of the filter. Higher orders have a
     * more aggressive cutoff.
     *
     * Generated from Godot docs: AudioEffectFilter.get_db
     */
    fun getDb(): AudioEffectFilter.FilterDB {
        checkOpen()
        return AudioEffectFilter.FilterDB(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDbBind, segment))
    }

    /**
     * Godot's `AudioEffectFilter.FilterDB` enum as a typed value: `.value` is the raw number Godot
     * uses, and the companion holds the named values (`AudioEffectFilter.FilterDB.<NAME>`).
     *
     * Generated from Godot docs: AudioEffectFilter.FilterDB
     */
    @JvmInline
    value class FilterDB(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Cutting off at 6 dB per octave. One octave is twice the frequency above `cutoff_hz`, or half the
             * frequency below `cutoff_hz`.
             *
             * Generated from Godot docs: AudioEffectFilter.FILTER_6DB
             */
            val FILTER_6DB: FilterDB get() = FilterDB(0L)
            /**
             * Cutting off at 12 dB per octave. One octave is twice the frequency above `cutoff_hz`, or half
             * the frequency below `cutoff_hz`.
             *
             * Generated from Godot docs: AudioEffectFilter.FILTER_12DB
             */
            val FILTER_12DB: FilterDB get() = FilterDB(1L)
            /**
             * Cutting off at 18 dB per octave. One octave is twice the frequency above `cutoff_hz`, or half
             * the frequency below `cutoff_hz`.
             *
             * Generated from Godot docs: AudioEffectFilter.FILTER_18DB
             */
            val FILTER_18DB: FilterDB get() = FilterDB(2L)
            /**
             * Cutting off at 24 dB per octave. One octave is twice the frequency above `cutoff_hz`, or half
             * the frequency below `cutoff_hz`.
             *
             * Generated from Godot docs: AudioEffectFilter.FILTER_24DB
             */
            val FILTER_24DB: FilterDB get() = FilterDB(3L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectFilter? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectFilter? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectFilter(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectFilter? =
            if (handle.address() == 0L) null else AudioEffectFilter(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CUTOFF_HASH = 373806689L
        @JvmField
        val setCutoffBind =
            ObjectCalls.getMethodBind("AudioEffectFilter", "set_cutoff", SET_CUTOFF_HASH)

        private const val GET_CUTOFF_HASH = 1740695150L
        @JvmField
        val getCutoffBind =
            ObjectCalls.getMethodBind("AudioEffectFilter", "get_cutoff", GET_CUTOFF_HASH)

        private const val SET_RESONANCE_HASH = 373806689L
        @JvmField
        val setResonanceBind =
            ObjectCalls.getMethodBind("AudioEffectFilter", "set_resonance", SET_RESONANCE_HASH)

        private const val GET_RESONANCE_HASH = 1740695150L
        @JvmField
        val getResonanceBind =
            ObjectCalls.getMethodBind("AudioEffectFilter", "get_resonance", GET_RESONANCE_HASH)

        private const val SET_GAIN_HASH = 373806689L
        @JvmField
        val setGainBind =
            ObjectCalls.getMethodBind("AudioEffectFilter", "set_gain", SET_GAIN_HASH)

        private const val GET_GAIN_HASH = 1740695150L
        @JvmField
        val getGainBind =
            ObjectCalls.getMethodBind("AudioEffectFilter", "get_gain", GET_GAIN_HASH)

        private const val SET_DB_HASH = 771740901L
        @JvmField
        val setDbBind =
            ObjectCalls.getMethodBind("AudioEffectFilter", "set_db", SET_DB_HASH)

        private const val GET_DB_HASH = 3981721890L
        @JvmField
        val getDbBind =
            ObjectCalls.getMethodBind("AudioEffectFilter", "get_db", GET_DB_HASH)
    }
}
