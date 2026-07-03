package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Adds a phaser audio effect to an audio bus. Creates several notch and peak filters that sweep
 * across the spectrum.
 *
 * Generated from Godot docs: AudioEffectPhaser
 */
class AudioEffectPhaser(handle: MemorySegment) : AudioEffect(handle) {
    var rangeMinHz: Double
        @JvmName("rangeMinHzProperty")
        get() = getRangeMinHz()
        @JvmName("setRangeMinHzProperty")
        set(value) = setRangeMinHz(value)

    var rangeMaxHz: Double
        @JvmName("rangeMaxHzProperty")
        get() = getRangeMaxHz()
        @JvmName("setRangeMaxHzProperty")
        set(value) = setRangeMaxHz(value)

    var rateHz: Double
        @JvmName("rateHzProperty")
        get() = getRateHz()
        @JvmName("setRateHzProperty")
        set(value) = setRateHz(value)

    var feedback: Double
        @JvmName("feedbackProperty")
        get() = getFeedback()
        @JvmName("setFeedbackProperty")
        set(value) = setFeedback(value)

    var depth: Double
        @JvmName("depthProperty")
        get() = getDepth()
        @JvmName("setDepthProperty")
        set(value) = setDepth(value)

    /**
     * Determines the minimum frequency affected by the low-frequency oscillator modulations, in Hz.
     * Value can range from 10 to 10000.
     *
     * Generated from Godot docs: AudioEffectPhaser.set_range_min_hz
     */
    fun setRangeMinHz(hz: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRangeMinHzBind, handle, hz)
    }

    /**
     * Determines the minimum frequency affected by the low-frequency oscillator modulations, in Hz.
     * Value can range from 10 to 10000.
     *
     * Generated from Godot docs: AudioEffectPhaser.get_range_min_hz
     */
    fun getRangeMinHz(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRangeMinHzBind, handle)
    }

    /**
     * Determines the maximum frequency affected by the low-frequency oscillator modulations, in Hz.
     * Value can range from 10 to 10000.
     *
     * Generated from Godot docs: AudioEffectPhaser.set_range_max_hz
     */
    fun setRangeMaxHz(hz: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRangeMaxHzBind, handle, hz)
    }

    /**
     * Determines the maximum frequency affected by the low-frequency oscillator modulations, in Hz.
     * Value can range from 10 to 10000.
     *
     * Generated from Godot docs: AudioEffectPhaser.get_range_max_hz
     */
    fun getRangeMaxHz(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRangeMaxHzBind, handle)
    }

    /**
     * Adjusts the rate in Hz at which the effect sweeps up and down across the frequency range. Value
     * can range from 0.01 to 20.
     *
     * Generated from Godot docs: AudioEffectPhaser.set_rate_hz
     */
    fun setRateHz(hz: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRateHzBind, handle, hz)
    }

    /**
     * Adjusts the rate in Hz at which the effect sweeps up and down across the frequency range. Value
     * can range from 0.01 to 20.
     *
     * Generated from Godot docs: AudioEffectPhaser.get_rate_hz
     */
    fun getRateHz(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRateHzBind, handle)
    }

    /**
     * The volume ratio of the filtered audio that is fed back to the all-pass filters. The higher the
     * value, the sharper and louder the peak filters created by the effect. Value can range from 0.1
     * to 0.9.
     *
     * Generated from Godot docs: AudioEffectPhaser.set_feedback
     */
    fun setFeedback(fbk: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFeedbackBind, handle, fbk)
    }

    /**
     * The volume ratio of the filtered audio that is fed back to the all-pass filters. The higher the
     * value, the sharper and louder the peak filters created by the effect. Value can range from 0.1
     * to 0.9.
     *
     * Generated from Godot docs: AudioEffectPhaser.get_feedback
     */
    fun getFeedback(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFeedbackBind, handle)
    }

    /**
     * Intensity of the effect. Value can range from 0.1 to 4.0.
     *
     * Generated from Godot docs: AudioEffectPhaser.set_depth
     */
    fun setDepth(depth: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDepthBind, handle, depth)
    }

    /**
     * Intensity of the effect. Value can range from 0.1 to 4.0.
     *
     * Generated from Godot docs: AudioEffectPhaser.get_depth
     */
    fun getDepth(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectPhaser? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectPhaser? =
            if (handle.address() == 0L) null else AudioEffectPhaser(handle)

        private const val SET_RANGE_MIN_HZ_HASH = 373806689L
        private val setRangeMinHzBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPhaser", "set_range_min_hz", SET_RANGE_MIN_HZ_HASH)
        }

        private const val GET_RANGE_MIN_HZ_HASH = 1740695150L
        private val getRangeMinHzBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPhaser", "get_range_min_hz", GET_RANGE_MIN_HZ_HASH)
        }

        private const val SET_RANGE_MAX_HZ_HASH = 373806689L
        private val setRangeMaxHzBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPhaser", "set_range_max_hz", SET_RANGE_MAX_HZ_HASH)
        }

        private const val GET_RANGE_MAX_HZ_HASH = 1740695150L
        private val getRangeMaxHzBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPhaser", "get_range_max_hz", GET_RANGE_MAX_HZ_HASH)
        }

        private const val SET_RATE_HZ_HASH = 373806689L
        private val setRateHzBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPhaser", "set_rate_hz", SET_RATE_HZ_HASH)
        }

        private const val GET_RATE_HZ_HASH = 1740695150L
        private val getRateHzBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPhaser", "get_rate_hz", GET_RATE_HZ_HASH)
        }

        private const val SET_FEEDBACK_HASH = 373806689L
        private val setFeedbackBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPhaser", "set_feedback", SET_FEEDBACK_HASH)
        }

        private const val GET_FEEDBACK_HASH = 1740695150L
        private val getFeedbackBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPhaser", "get_feedback", GET_FEEDBACK_HASH)
        }

        private const val SET_DEPTH_HASH = 373806689L
        private val setDepthBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPhaser", "set_depth", SET_DEPTH_HASH)
        }

        private const val GET_DEPTH_HASH = 1740695150L
        private val getDepthBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectPhaser", "get_depth", GET_DEPTH_HASH)
        }
    }
}
