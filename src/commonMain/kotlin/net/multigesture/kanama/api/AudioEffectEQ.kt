package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Base class for audio equalizers (EQ). Gives you control over frequencies. Use it to create a
 * custom equalizer if `AudioEffectEQ6`, `AudioEffectEQ10`, or `AudioEffectEQ21` don't fit your
 * needs.
 *
 * Generated from Godot docs: AudioEffectEQ
 */
open class AudioEffectEQ(handle: GodotHandle) : AudioEffect(handle) {
    /**
     * Sets band's gain at the specified index, in dB.
     *
     * Generated from Godot docs: AudioEffectEQ.set_band_gain_db
     */
    fun setBandGainDb(bandIdx: Int, volumeDb: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(Binds.setBandGainDbBind, segment, bandIdx, volumeDb)
    }

    /**
     * Returns the band's gain at the specified index, in dB.
     *
     * Generated from Godot docs: AudioEffectEQ.get_band_gain_db
     */
    fun getBandGainDb(bandIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(Binds.getBandGainDbBind, segment, bandIdx)
    }

    /**
     * Returns the number of bands of the equalizer.
     *
     * Generated from Godot docs: AudioEffectEQ.get_band_count
     */
    fun getBandCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBandCountBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectEQ? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectEQ? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectEQ(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectEQ? =
            if (handle.address() == 0L) null else AudioEffectEQ(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_BAND_GAIN_DB_HASH = 1602489585L
        @JvmField
        val setBandGainDbBind =
            ObjectCalls.getMethodBind("AudioEffectEQ", "set_band_gain_db", SET_BAND_GAIN_DB_HASH)

        private const val GET_BAND_GAIN_DB_HASH = 2339986948L
        @JvmField
        val getBandGainDbBind =
            ObjectCalls.getMethodBind("AudioEffectEQ", "get_band_gain_db", GET_BAND_GAIN_DB_HASH)

        private const val GET_BAND_COUNT_HASH = 3905245786L
        @JvmField
        val getBandCountBind =
            ObjectCalls.getMethodBind("AudioEffectEQ", "get_band_count", GET_BAND_COUNT_HASH)
    }
}
