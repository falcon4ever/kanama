package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Adds a reverberation audio effect to an audio bus. Emulates an echo by playing a blurred version
 * of the input audio.
 *
 * Generated from Godot docs: AudioEffectReverb
 */
class AudioEffectReverb(handle: MemorySegment) : AudioEffect(handle) {
    var predelayMsec: Double
        @JvmName("predelayMsecProperty")
        get() = getPredelayMsec()
        @JvmName("setPredelayMsecProperty")
        set(value) = setPredelayMsec(value)

    var predelayFeedback: Double
        @JvmName("predelayFeedbackProperty")
        get() = getPredelayFeedback()
        @JvmName("setPredelayFeedbackProperty")
        set(value) = setPredelayFeedback(value)

    var roomSize: Double
        @JvmName("roomSizeProperty")
        get() = getRoomSize()
        @JvmName("setRoomSizeProperty")
        set(value) = setRoomSize(value)

    var damping: Double
        @JvmName("dampingProperty")
        get() = getDamping()
        @JvmName("setDampingProperty")
        set(value) = setDamping(value)

    var spread: Double
        @JvmName("spreadProperty")
        get() = getSpread()
        @JvmName("setSpreadProperty")
        set(value) = setSpread(value)

    var hipass: Double
        @JvmName("hipassProperty")
        get() = getHpf()
        @JvmName("setHipassProperty")
        set(value) = setHpf(value)

    var dry: Double
        @JvmName("dryProperty")
        get() = getDry()
        @JvmName("setDryProperty")
        set(value) = setDry(value)

    var wet: Double
        @JvmName("wetProperty")
        get() = getWet()
        @JvmName("setWetProperty")
        set(value) = setWet(value)

    fun setPredelayMsec(msec: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPredelayMsecBind, handle, msec)
    }

    fun getPredelayMsec(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPredelayMsecBind, handle)
    }

    fun setPredelayFeedback(feedback: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPredelayFeedbackBind, handle, feedback)
    }

    fun getPredelayFeedback(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPredelayFeedbackBind, handle)
    }

    fun setRoomSize(size: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRoomSizeBind, handle, size)
    }

    fun getRoomSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRoomSizeBind, handle)
    }

    fun setDamping(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDampingBind, handle, amount)
    }

    fun getDamping(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDampingBind, handle)
    }

    fun setSpread(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSpreadBind, handle, amount)
    }

    fun getSpread(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSpreadBind, handle)
    }

    fun setDry(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDryBind, handle, amount)
    }

    fun getDry(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDryBind, handle)
    }

    fun setWet(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWetBind, handle, amount)
    }

    fun getWet(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWetBind, handle)
    }

    fun setHpf(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHpfBind, handle, amount)
    }

    fun getHpf(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHpfBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectReverb? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectReverb? =
            if (handle.address() == 0L) null else AudioEffectReverb(handle)

        private const val SET_PREDELAY_MSEC_HASH = 373806689L
        private val setPredelayMsecBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_predelay_msec", SET_PREDELAY_MSEC_HASH)
        }

        private const val GET_PREDELAY_MSEC_HASH = 1740695150L
        private val getPredelayMsecBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_predelay_msec", GET_PREDELAY_MSEC_HASH)
        }

        private const val SET_PREDELAY_FEEDBACK_HASH = 373806689L
        private val setPredelayFeedbackBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_predelay_feedback", SET_PREDELAY_FEEDBACK_HASH)
        }

        private const val GET_PREDELAY_FEEDBACK_HASH = 1740695150L
        private val getPredelayFeedbackBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_predelay_feedback", GET_PREDELAY_FEEDBACK_HASH)
        }

        private const val SET_ROOM_SIZE_HASH = 373806689L
        private val setRoomSizeBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_room_size", SET_ROOM_SIZE_HASH)
        }

        private const val GET_ROOM_SIZE_HASH = 1740695150L
        private val getRoomSizeBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_room_size", GET_ROOM_SIZE_HASH)
        }

        private const val SET_DAMPING_HASH = 373806689L
        private val setDampingBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_damping", SET_DAMPING_HASH)
        }

        private const val GET_DAMPING_HASH = 1740695150L
        private val getDampingBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_damping", GET_DAMPING_HASH)
        }

        private const val SET_SPREAD_HASH = 373806689L
        private val setSpreadBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_spread", SET_SPREAD_HASH)
        }

        private const val GET_SPREAD_HASH = 1740695150L
        private val getSpreadBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_spread", GET_SPREAD_HASH)
        }

        private const val SET_DRY_HASH = 373806689L
        private val setDryBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_dry", SET_DRY_HASH)
        }

        private const val GET_DRY_HASH = 1740695150L
        private val getDryBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_dry", GET_DRY_HASH)
        }

        private const val SET_WET_HASH = 373806689L
        private val setWetBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_wet", SET_WET_HASH)
        }

        private const val GET_WET_HASH = 1740695150L
        private val getWetBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_wet", GET_WET_HASH)
        }

        private const val SET_HPF_HASH = 373806689L
        private val setHpfBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_hpf", SET_HPF_HASH)
        }

        private const val GET_HPF_HASH = 1740695150L
        private val getHpfBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_hpf", GET_HPF_HASH)
        }
    }
}
