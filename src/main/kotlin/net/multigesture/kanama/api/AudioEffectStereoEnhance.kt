package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Adds a stereo manipulation audio effect to an audio bus. Controls gain of the side channels, and
 * widens the stereo image.
 *
 * Generated from Godot docs: AudioEffectStereoEnhance
 */
class AudioEffectStereoEnhance(handle: MemorySegment) : AudioEffect(handle) {
    var panPullout: Double
        @JvmName("panPulloutProperty")
        get() = getPanPullout()
        @JvmName("setPanPulloutProperty")
        set(value) = setPanPullout(value)

    var timePulloutMs: Double
        @JvmName("timePulloutMsProperty")
        get() = getTimePullout()
        @JvmName("setTimePulloutMsProperty")
        set(value) = setTimePullout(value)

    var surround: Double
        @JvmName("surroundProperty")
        get() = getSurround()
        @JvmName("setSurroundProperty")
        set(value) = setSurround(value)

    fun setPanPullout(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPanPulloutBind, handle, amount)
    }

    fun getPanPullout(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPanPulloutBind, handle)
    }

    fun setTimePullout(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTimePulloutBind, handle, amount)
    }

    fun getTimePullout(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimePulloutBind, handle)
    }

    fun setSurround(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSurroundBind, handle, amount)
    }

    fun getSurround(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSurroundBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectStereoEnhance? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectStereoEnhance? =
            if (handle.address() == 0L) null else AudioEffectStereoEnhance(handle)

        private const val SET_PAN_PULLOUT_HASH = 373806689L
        private val setPanPulloutBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "set_pan_pullout", SET_PAN_PULLOUT_HASH)
        }

        private const val GET_PAN_PULLOUT_HASH = 1740695150L
        private val getPanPulloutBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "get_pan_pullout", GET_PAN_PULLOUT_HASH)
        }

        private const val SET_TIME_PULLOUT_HASH = 373806689L
        private val setTimePulloutBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "set_time_pullout", SET_TIME_PULLOUT_HASH)
        }

        private const val GET_TIME_PULLOUT_HASH = 1740695150L
        private val getTimePulloutBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "get_time_pullout", GET_TIME_PULLOUT_HASH)
        }

        private const val SET_SURROUND_HASH = 373806689L
        private val setSurroundBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "set_surround", SET_SURROUND_HASH)
        }

        private const val GET_SURROUND_HASH = 1740695150L
        private val getSurroundBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "get_surround", GET_SURROUND_HASH)
        }
    }
}
