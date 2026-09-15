package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Adds a phaser audio effect to an audio bus. Creates several notch and peak filters that sweep
 * across the spectrum.
 *
 * Generated from Godot docs: AudioEffectPhaser
 */
class AudioEffectPhaser(handle: GodotHandle) : AudioEffect(handle) {
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
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setRangeMinHzBind, segment, hz)
    }

    /**
     * Determines the minimum frequency affected by the low-frequency oscillator modulations, in Hz.
     * Value can range from 10 to 10000.
     *
     * Generated from Godot docs: AudioEffectPhaser.get_range_min_hz
     */
    fun getRangeMinHz(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getRangeMinHzBind, segment)
    }

    /**
     * Determines the maximum frequency affected by the low-frequency oscillator modulations, in Hz.
     * Value can range from 10 to 10000.
     *
     * Generated from Godot docs: AudioEffectPhaser.set_range_max_hz
     */
    fun setRangeMaxHz(hz: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setRangeMaxHzBind, segment, hz)
    }

    /**
     * Determines the maximum frequency affected by the low-frequency oscillator modulations, in Hz.
     * Value can range from 10 to 10000.
     *
     * Generated from Godot docs: AudioEffectPhaser.get_range_max_hz
     */
    fun getRangeMaxHz(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getRangeMaxHzBind, segment)
    }

    /**
     * Adjusts the rate in Hz at which the effect sweeps up and down across the frequency range. Value
     * can range from 0.01 to 20.
     *
     * Generated from Godot docs: AudioEffectPhaser.set_rate_hz
     */
    fun setRateHz(hz: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setRateHzBind, segment, hz)
    }

    /**
     * Adjusts the rate in Hz at which the effect sweeps up and down across the frequency range. Value
     * can range from 0.01 to 20.
     *
     * Generated from Godot docs: AudioEffectPhaser.get_rate_hz
     */
    fun getRateHz(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getRateHzBind, segment)
    }

    /**
     * The volume ratio of the filtered audio that is fed back to the all-pass filters. The higher the
     * value, the sharper and louder the peak filters created by the effect. Value can range from 0.1
     * to 0.9.
     *
     * Generated from Godot docs: AudioEffectPhaser.set_feedback
     */
    fun setFeedback(fbk: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setFeedbackBind, segment, fbk)
    }

    /**
     * The volume ratio of the filtered audio that is fed back to the all-pass filters. The higher the
     * value, the sharper and louder the peak filters created by the effect. Value can range from 0.1
     * to 0.9.
     *
     * Generated from Godot docs: AudioEffectPhaser.get_feedback
     */
    fun getFeedback(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getFeedbackBind, segment)
    }

    /**
     * Intensity of the effect. Value can range from 0.1 to 4.0.
     *
     * Generated from Godot docs: AudioEffectPhaser.set_depth
     */
    fun setDepth(depth: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setDepthBind, segment, depth)
    }

    /**
     * Intensity of the effect. Value can range from 0.1 to 4.0.
     *
     * Generated from Godot docs: AudioEffectPhaser.get_depth
     */
    fun getDepth(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getDepthBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AudioEffectPhaser? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AudioEffectPhaser? =
            if (handle.address() == 0L) null else AudioEffectPhaser(GodotHandle(handle))

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
