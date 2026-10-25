package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a stereo manipulation audio effect to an audio bus. Controls gain of the side channels, and
 * widens the stereo image.
 *
 * Generated from Godot docs: AudioEffectStereoEnhance
 */
class AudioEffectStereoEnhance(handle: GodotHandle) : AudioEffect(handle) {
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

    /**
     * Gain of the side channels, if they exist. A value of 0 will downmix stereo to mono. Value can
     * range from 0 to 4.
     *
     * Generated from Godot docs: AudioEffectStereoEnhance.set_pan_pullout
     */
    fun setPanPullout(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPanPulloutBind, segment, amount)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Gain of the side channels, if they exist. A value of 0 will downmix stereo to mono. Value can
     * range from 0 to 4.
     *
     * Generated from Godot docs: AudioEffectStereoEnhance.get_pan_pullout
     */
    fun getPanPullout(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPanPulloutBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Widens the stereo image through phase shifting in conjunction with `surround`. Just delays the
     * right channel if `surround` is 0. Value is in milliseconds, and can range from 0 to 50.
     *
     * Generated from Godot docs: AudioEffectStereoEnhance.set_time_pullout
     */
    fun setTimePullout(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTimePulloutBind, segment, amount)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Widens the stereo image through phase shifting in conjunction with `surround`. Just delays the
     * right channel if `surround` is 0. Value is in milliseconds, and can range from 0 to 50.
     *
     * Generated from Godot docs: AudioEffectStereoEnhance.get_time_pullout
     */
    fun getTimePullout(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTimePulloutBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * Widens the stereo image through phase shifting in conjunction with `time_pullout_ms`. Just pans
     * sound to the left channel if `time_pullout_ms` is 0. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectStereoEnhance.set_surround
     */
    fun setSurround(amount: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSurroundBind, segment, amount)
        ObjectCalls.keepReachable(this)
    }

    /**
     * Widens the stereo image through phase shifting in conjunction with `time_pullout_ms`. Just pans
     * sound to the left channel if `time_pullout_ms` is 0. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectStereoEnhance.get_surround
     */
    fun getSurround(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSurroundBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectStereoEnhance? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): AudioEffectStereoEnhance? =
            if (handle.address() == 0L) null else RefCounted.owned(AudioEffectStereoEnhance(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): AudioEffectStereoEnhance? =
            if (handle.address() == 0L) null else AudioEffectStereoEnhance(GodotHandle(handle))

        /** A new AudioEffectStereoEnhance, GDScript's `AudioEffectStereoEnhance.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): AudioEffectStereoEnhance =
            RefCounted.owned(AudioEffectStereoEnhance(GodotHandle(ObjectCalls.constructObject("AudioEffectStereoEnhance"))))
    }

    private object Binds {
        private const val SET_PAN_PULLOUT_HASH = 373806689L
        @JvmField
        val setPanPulloutBind =
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "set_pan_pullout", SET_PAN_PULLOUT_HASH)

        private const val GET_PAN_PULLOUT_HASH = 1740695150L
        @JvmField
        val getPanPulloutBind =
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "get_pan_pullout", GET_PAN_PULLOUT_HASH)

        private const val SET_TIME_PULLOUT_HASH = 373806689L
        @JvmField
        val setTimePulloutBind =
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "set_time_pullout", SET_TIME_PULLOUT_HASH)

        private const val GET_TIME_PULLOUT_HASH = 1740695150L
        @JvmField
        val getTimePulloutBind =
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "get_time_pullout", GET_TIME_PULLOUT_HASH)

        private const val SET_SURROUND_HASH = 373806689L
        @JvmField
        val setSurroundBind =
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "set_surround", SET_SURROUND_HASH)

        private const val GET_SURROUND_HASH = 1740695150L
        @JvmField
        val getSurroundBind =
            ObjectCalls.getMethodBind("AudioEffectStereoEnhance", "get_surround", GET_SURROUND_HASH)
    }
}
