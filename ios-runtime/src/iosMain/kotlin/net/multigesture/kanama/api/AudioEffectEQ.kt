package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AudioEffectEQ
 */
open class AudioEffectEQ(handle: MemorySegment) : AudioEffect(handle) {
    fun setBandGainDb(bandIdx: Int, volumeDb: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithIntAndDoubleArg(setBandGainDbBind, handle, bandIdx, volumeDb)
    }

    fun getBandGainDb(bandIdx: Int): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithIntArgRetDouble(getBandGainDbBind, handle, bandIdx)
    }

    fun getBandCount(): Int {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetInt(getBandCountBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): AudioEffectEQ? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectEQ? =
            if (handle.address() == 0L) null else AudioEffectEQ(handle)

        private const val SET_BAND_GAIN_DB_HASH = 1602489585L
        private val setBandGainDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectEQ", "set_band_gain_db", SET_BAND_GAIN_DB_HASH)
        }

        private const val GET_BAND_GAIN_DB_HASH = 2339986948L
        private val getBandGainDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectEQ", "get_band_gain_db", GET_BAND_GAIN_DB_HASH)
        }

        private const val GET_BAND_COUNT_HASH = 3905245786L
        private val getBandCountBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectEQ", "get_band_count", GET_BAND_COUNT_HASH)
        }
    }
}
