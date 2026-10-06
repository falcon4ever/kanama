package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a reverberation audio effect to an audio bus. Emulates an echo by playing a blurred version
 * of the input audio.
 *
 * Generated from Godot docs: AudioEffectReverb
 */
class AudioEffectReverb(handle: GodotHandle) : AudioEffect(handle) {
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

    /**
     * Time between the original audio and the early reflections of the reverb signal, in milliseconds.
     * Value can range from 20 to 500.
     *
     * Generated from Godot docs: AudioEffectReverb.set_predelay_msec
     */
    fun setPredelayMsec(msec: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPredelayMsecBind, segment, msec)
    }

    /**
     * Time between the original audio and the early reflections of the reverb signal, in milliseconds.
     * Value can range from 20 to 500.
     *
     * Generated from Godot docs: AudioEffectReverb.get_predelay_msec
     */
    fun getPredelayMsec(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPredelayMsecBind, segment)
    }

    /**
     * Gain of early reflection copies. At higher values, early reflection copies are louder and ring
     * out for longer. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.set_predelay_feedback
     */
    fun setPredelayFeedback(feedback: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPredelayFeedbackBind, segment, feedback)
    }

    /**
     * Gain of early reflection copies. At higher values, early reflection copies are louder and ring
     * out for longer. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.get_predelay_feedback
     */
    fun getPredelayFeedback(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPredelayFeedbackBind, segment)
    }

    /**
     * Dimensions of simulated room. Bigger means more echoes. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.set_room_size
     */
    fun setRoomSize(size: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRoomSizeBind, segment, size)
    }

    /**
     * Dimensions of simulated room. Bigger means more echoes. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.get_room_size
     */
    fun getRoomSize(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRoomSizeBind, segment)
    }

    /**
     * Defines how reflective the imaginary room's walls are. The more reflective, the more high
     * frequency content the reverb has. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.set_damping
     */
    fun setDamping(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDampingBind, segment, amount)
    }

    /**
     * Defines how reflective the imaginary room's walls are. The more reflective, the more high
     * frequency content the reverb has. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.get_damping
     */
    fun getDamping(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDampingBind, segment)
    }

    /**
     * Widens or narrows the stereo image of the reverb tail. At 1, it fully widens. Value can range
     * from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.set_spread
     */
    fun setSpread(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSpreadBind, segment, amount)
    }

    /**
     * Widens or narrows the stereo image of the reverb tail. At 1, it fully widens. Value can range
     * from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.get_spread
     */
    fun getSpread(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSpreadBind, segment)
    }

    /**
     * The volume ratio of the original audio. At 0, only the modified audio is outputted. Value can
     * range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.set_dry
     */
    fun setDry(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDryBind, segment, amount)
    }

    /**
     * The volume ratio of the original audio. At 0, only the modified audio is outputted. Value can
     * range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.get_dry
     */
    fun getDry(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDryBind, segment)
    }

    /**
     * The volume ratio of the modified audio. At 0, only the original audio is outputted. Value can
     * range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.set_wet
     */
    fun setWet(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setWetBind, segment, amount)
    }

    /**
     * The volume ratio of the modified audio. At 0, only the original audio is outputted. Value can
     * range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.get_wet
     */
    fun getWet(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getWetBind, segment)
    }

    /**
     * High-pass filter allows frequencies higher than a certain cutoff threshold and attenuates
     * frequencies lower than the cutoff threshold. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.set_hpf
     */
    fun setHpf(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHpfBind, segment, amount)
    }

    /**
     * High-pass filter allows frequencies higher than a certain cutoff threshold and attenuates
     * frequencies lower than the cutoff threshold. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectReverb.get_hpf
     */
    fun getHpf(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHpfBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectReverb? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectReverb? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectReverb(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectReverb? =
            if (handle.address() == 0L) null else AudioEffectReverb(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_PREDELAY_MSEC_HASH = 373806689L
        @JvmField
        val setPredelayMsecBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_predelay_msec", SET_PREDELAY_MSEC_HASH)

        private const val GET_PREDELAY_MSEC_HASH = 1740695150L
        @JvmField
        val getPredelayMsecBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_predelay_msec", GET_PREDELAY_MSEC_HASH)

        private const val SET_PREDELAY_FEEDBACK_HASH = 373806689L
        @JvmField
        val setPredelayFeedbackBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_predelay_feedback", SET_PREDELAY_FEEDBACK_HASH)

        private const val GET_PREDELAY_FEEDBACK_HASH = 1740695150L
        @JvmField
        val getPredelayFeedbackBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_predelay_feedback", GET_PREDELAY_FEEDBACK_HASH)

        private const val SET_ROOM_SIZE_HASH = 373806689L
        @JvmField
        val setRoomSizeBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_room_size", SET_ROOM_SIZE_HASH)

        private const val GET_ROOM_SIZE_HASH = 1740695150L
        @JvmField
        val getRoomSizeBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_room_size", GET_ROOM_SIZE_HASH)

        private const val SET_DAMPING_HASH = 373806689L
        @JvmField
        val setDampingBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_damping", SET_DAMPING_HASH)

        private const val GET_DAMPING_HASH = 1740695150L
        @JvmField
        val getDampingBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_damping", GET_DAMPING_HASH)

        private const val SET_SPREAD_HASH = 373806689L
        @JvmField
        val setSpreadBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_spread", SET_SPREAD_HASH)

        private const val GET_SPREAD_HASH = 1740695150L
        @JvmField
        val getSpreadBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_spread", GET_SPREAD_HASH)

        private const val SET_DRY_HASH = 373806689L
        @JvmField
        val setDryBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_dry", SET_DRY_HASH)

        private const val GET_DRY_HASH = 1740695150L
        @JvmField
        val getDryBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_dry", GET_DRY_HASH)

        private const val SET_WET_HASH = 373806689L
        @JvmField
        val setWetBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_wet", SET_WET_HASH)

        private const val GET_WET_HASH = 1740695150L
        @JvmField
        val getWetBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_wet", GET_WET_HASH)

        private const val SET_HPF_HASH = 373806689L
        @JvmField
        val setHpfBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "set_hpf", SET_HPF_HASH)

        private const val GET_HPF_HASH = 1740695150L
        @JvmField
        val getHpfBind =
            ObjectCalls.getMethodBind("AudioEffectReverb", "get_hpf", GET_HPF_HASH)
    }
}
