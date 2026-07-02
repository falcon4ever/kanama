package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Adds a volume manipulation audio effect to an audio bus.
 *
 * Generated from Godot docs: AudioEffectAmplify
 */
class AudioEffectAmplify(handle: MemorySegment) : AudioEffect(handle) {
    var volumeDb: Double
        @JvmName("volumeDbProperty")
        get() = getVolumeDb()
        @JvmName("setVolumeDbProperty")
        set(value) = setVolumeDb(value)

    var volumeLinear: Double
        @JvmName("volumeLinearProperty")
        get() = getVolumeLinear()
        @JvmName("setVolumeLinearProperty")
        set(value) = setVolumeLinear(value)

    fun setVolumeDb(volume: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumeDbBind, handle, volume)
    }

    fun getVolumeDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumeDbBind, handle)
    }

    fun setVolumeLinear(volume: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setVolumeLinearBind, handle, volume)
    }

    fun getVolumeLinear(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getVolumeLinearBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectAmplify? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectAmplify? =
            if (handle.address() == 0L) null else AudioEffectAmplify(handle)

        private const val SET_VOLUME_DB_HASH = 373806689L
        private val setVolumeDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectAmplify", "set_volume_db", SET_VOLUME_DB_HASH)
        }

        private const val GET_VOLUME_DB_HASH = 1740695150L
        private val getVolumeDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectAmplify", "get_volume_db", GET_VOLUME_DB_HASH)
        }

        private const val SET_VOLUME_LINEAR_HASH = 373806689L
        private val setVolumeLinearBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectAmplify", "set_volume_linear", SET_VOLUME_LINEAR_HASH)
        }

        private const val GET_VOLUME_LINEAR_HASH = 1740695150L
        private val getVolumeLinearBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectAmplify", "get_volume_linear", GET_VOLUME_LINEAR_HASH)
        }
    }
}
