package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Adds a delay audio effect to an audio bus. Emulates an echo by playing the input audio back
 * after a period of time.
 *
 * Generated from Godot docs: AudioEffectDelay
 */
class AudioEffectDelay(handle: MemorySegment) : AudioEffect(handle) {
    var dry: Double
        @JvmName("dryProperty")
        get() = getDry()
        @JvmName("setDryProperty")
        set(value) = setDry(value)

    var tap1Active: Boolean
        @JvmName("tap1ActiveProperty")
        get() = isTap1Active()
        @JvmName("setTap1ActiveProperty")
        set(value) = setTap1Active(value)

    var tap1DelayMs: Double
        @JvmName("tap1DelayMsProperty")
        get() = getTap1DelayMs()
        @JvmName("setTap1DelayMsProperty")
        set(value) = setTap1DelayMs(value)

    var tap1LevelDb: Double
        @JvmName("tap1LevelDbProperty")
        get() = getTap1LevelDb()
        @JvmName("setTap1LevelDbProperty")
        set(value) = setTap1LevelDb(value)

    var tap1Pan: Double
        @JvmName("tap1PanProperty")
        get() = getTap1Pan()
        @JvmName("setTap1PanProperty")
        set(value) = setTap1Pan(value)

    var tap2Active: Boolean
        @JvmName("tap2ActiveProperty")
        get() = isTap2Active()
        @JvmName("setTap2ActiveProperty")
        set(value) = setTap2Active(value)

    var tap2DelayMs: Double
        @JvmName("tap2DelayMsProperty")
        get() = getTap2DelayMs()
        @JvmName("setTap2DelayMsProperty")
        set(value) = setTap2DelayMs(value)

    var tap2LevelDb: Double
        @JvmName("tap2LevelDbProperty")
        get() = getTap2LevelDb()
        @JvmName("setTap2LevelDbProperty")
        set(value) = setTap2LevelDb(value)

    var tap2Pan: Double
        @JvmName("tap2PanProperty")
        get() = getTap2Pan()
        @JvmName("setTap2PanProperty")
        set(value) = setTap2Pan(value)

    var feedbackActive: Boolean
        @JvmName("feedbackActiveProperty")
        get() = isFeedbackActive()
        @JvmName("setFeedbackActiveProperty")
        set(value) = setFeedbackActive(value)

    var feedbackDelayMs: Double
        @JvmName("feedbackDelayMsProperty")
        get() = getFeedbackDelayMs()
        @JvmName("setFeedbackDelayMsProperty")
        set(value) = setFeedbackDelayMs(value)

    var feedbackLevelDb: Double
        @JvmName("feedbackLevelDbProperty")
        get() = getFeedbackLevelDb()
        @JvmName("setFeedbackLevelDbProperty")
        set(value) = setFeedbackLevelDb(value)

    var feedbackLowpass: Double
        @JvmName("feedbackLowpassProperty")
        get() = getFeedbackLowpass()
        @JvmName("setFeedbackLowpassProperty")
        set(value) = setFeedbackLowpass(value)

    /**
     * The volume ratio of the original audio. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectDelay.set_dry
     */
    fun setDry(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDryBind, handle, amount)
    }

    /**
     * The volume ratio of the original audio. Value can range from 0 to 1.
     *
     * Generated from Godot docs: AudioEffectDelay.get_dry
     */
    fun getDry(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDryBind, handle)
    }

    /**
     * If `true`, the first tap will be enabled.
     *
     * Generated from Godot docs: AudioEffectDelay.set_tap1_active
     */
    fun setTap1Active(amount: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTap1ActiveBind, handle, amount)
    }

    /**
     * If `true`, the first tap will be enabled.
     *
     * Generated from Godot docs: AudioEffectDelay.is_tap1_active
     */
    fun isTap1Active(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTap1ActiveBind, handle)
    }

    /**
     * First tap delay time in milliseconds, compared to the original audio. Value can range from 0 to
     * 1500.
     *
     * Generated from Godot docs: AudioEffectDelay.set_tap1_delay_ms
     */
    fun setTap1DelayMs(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTap1DelayMsBind, handle, amount)
    }

    /**
     * First tap delay time in milliseconds, compared to the original audio. Value can range from 0 to
     * 1500.
     *
     * Generated from Godot docs: AudioEffectDelay.get_tap1_delay_ms
     */
    fun getTap1DelayMs(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTap1DelayMsBind, handle)
    }

    /**
     * Gain for the first tap, in dB. Value can range from -60 to 0.
     *
     * Generated from Godot docs: AudioEffectDelay.set_tap1_level_db
     */
    fun setTap1LevelDb(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTap1LevelDbBind, handle, amount)
    }

    /**
     * Gain for the first tap, in dB. Value can range from -60 to 0.
     *
     * Generated from Godot docs: AudioEffectDelay.get_tap1_level_db
     */
    fun getTap1LevelDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTap1LevelDbBind, handle)
    }

    /**
     * Pan position for the first tap. Negative values pan the sound to the left, positive pan to the
     * right. Value can range from -1 to 1.
     *
     * Generated from Godot docs: AudioEffectDelay.set_tap1_pan
     */
    fun setTap1Pan(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTap1PanBind, handle, amount)
    }

    /**
     * Pan position for the first tap. Negative values pan the sound to the left, positive pan to the
     * right. Value can range from -1 to 1.
     *
     * Generated from Godot docs: AudioEffectDelay.get_tap1_pan
     */
    fun getTap1Pan(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTap1PanBind, handle)
    }

    /**
     * If `true`, the second tap will be enabled.
     *
     * Generated from Godot docs: AudioEffectDelay.set_tap2_active
     */
    fun setTap2Active(amount: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setTap2ActiveBind, handle, amount)
    }

    /**
     * If `true`, the second tap will be enabled.
     *
     * Generated from Godot docs: AudioEffectDelay.is_tap2_active
     */
    fun isTap2Active(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTap2ActiveBind, handle)
    }

    /**
     * Second tap delay time in milliseconds, compared to the original audio. Value can range from 0 to
     * 1500.
     *
     * Generated from Godot docs: AudioEffectDelay.set_tap2_delay_ms
     */
    fun setTap2DelayMs(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTap2DelayMsBind, handle, amount)
    }

    /**
     * Second tap delay time in milliseconds, compared to the original audio. Value can range from 0 to
     * 1500.
     *
     * Generated from Godot docs: AudioEffectDelay.get_tap2_delay_ms
     */
    fun getTap2DelayMs(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTap2DelayMsBind, handle)
    }

    /**
     * Gain for the second tap, in dB. Value can range from -60 to 0.
     *
     * Generated from Godot docs: AudioEffectDelay.set_tap2_level_db
     */
    fun setTap2LevelDb(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTap2LevelDbBind, handle, amount)
    }

    /**
     * Gain for the second tap, in dB. Value can range from -60 to 0.
     *
     * Generated from Godot docs: AudioEffectDelay.get_tap2_level_db
     */
    fun getTap2LevelDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTap2LevelDbBind, handle)
    }

    /**
     * Pan position for the second tap. Negative values pan the sound to the left, positive pan to the
     * right. Value can range from -1 to 1.
     *
     * Generated from Godot docs: AudioEffectDelay.set_tap2_pan
     */
    fun setTap2Pan(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTap2PanBind, handle, amount)
    }

    /**
     * Pan position for the second tap. Negative values pan the sound to the left, positive pan to the
     * right. Value can range from -1 to 1.
     *
     * Generated from Godot docs: AudioEffectDelay.get_tap2_pan
     */
    fun getTap2Pan(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTap2PanBind, handle)
    }

    /**
     * If `true`, feedback is enabled, repeating taps after they are played.
     *
     * Generated from Godot docs: AudioEffectDelay.set_feedback_active
     */
    fun setFeedbackActive(amount: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFeedbackActiveBind, handle, amount)
    }

    /**
     * If `true`, feedback is enabled, repeating taps after they are played.
     *
     * Generated from Godot docs: AudioEffectDelay.is_feedback_active
     */
    fun isFeedbackActive(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFeedbackActiveBind, handle)
    }

    /**
     * Feedback delay time in milliseconds. Value can range from 0 to 1500.
     *
     * Generated from Godot docs: AudioEffectDelay.set_feedback_delay_ms
     */
    fun setFeedbackDelayMs(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFeedbackDelayMsBind, handle, amount)
    }

    /**
     * Feedback delay time in milliseconds. Value can range from 0 to 1500.
     *
     * Generated from Godot docs: AudioEffectDelay.get_feedback_delay_ms
     */
    fun getFeedbackDelayMs(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFeedbackDelayMsBind, handle)
    }

    /**
     * Gain for feedback, in dB. Value can range from -60 to 0.
     *
     * Generated from Godot docs: AudioEffectDelay.set_feedback_level_db
     */
    fun setFeedbackLevelDb(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFeedbackLevelDbBind, handle, amount)
    }

    /**
     * Gain for feedback, in dB. Value can range from -60 to 0.
     *
     * Generated from Godot docs: AudioEffectDelay.get_feedback_level_db
     */
    fun getFeedbackLevelDb(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFeedbackLevelDbBind, handle)
    }

    /**
     * Low-pass filter for feedback, in Hz. Frequencies above this value are filtered out. Value can
     * range from 1 to 16000.
     *
     * Generated from Godot docs: AudioEffectDelay.set_feedback_lowpass
     */
    fun setFeedbackLowpass(amount: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFeedbackLowpassBind, handle, amount)
    }

    /**
     * Low-pass filter for feedback, in Hz. Frequencies above this value are filtered out. Value can
     * range from 1 to 16000.
     *
     * Generated from Godot docs: AudioEffectDelay.get_feedback_lowpass
     */
    fun getFeedbackLowpass(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFeedbackLowpassBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AudioEffectDelay? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AudioEffectDelay? =
            if (handle.address() == 0L) null else AudioEffectDelay(handle)

        private const val SET_DRY_HASH = 373806689L
        private val setDryBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "set_dry", SET_DRY_HASH)
        }

        private const val GET_DRY_HASH = 191475506L
        private val getDryBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "get_dry", GET_DRY_HASH)
        }

        private const val SET_TAP1_ACTIVE_HASH = 2586408642L
        private val setTap1ActiveBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "set_tap1_active", SET_TAP1_ACTIVE_HASH)
        }

        private const val IS_TAP1_ACTIVE_HASH = 36873697L
        private val isTap1ActiveBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "is_tap1_active", IS_TAP1_ACTIVE_HASH)
        }

        private const val SET_TAP1_DELAY_MS_HASH = 373806689L
        private val setTap1DelayMsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "set_tap1_delay_ms", SET_TAP1_DELAY_MS_HASH)
        }

        private const val GET_TAP1_DELAY_MS_HASH = 1740695150L
        private val getTap1DelayMsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "get_tap1_delay_ms", GET_TAP1_DELAY_MS_HASH)
        }

        private const val SET_TAP1_LEVEL_DB_HASH = 373806689L
        private val setTap1LevelDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "set_tap1_level_db", SET_TAP1_LEVEL_DB_HASH)
        }

        private const val GET_TAP1_LEVEL_DB_HASH = 1740695150L
        private val getTap1LevelDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "get_tap1_level_db", GET_TAP1_LEVEL_DB_HASH)
        }

        private const val SET_TAP1_PAN_HASH = 373806689L
        private val setTap1PanBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "set_tap1_pan", SET_TAP1_PAN_HASH)
        }

        private const val GET_TAP1_PAN_HASH = 1740695150L
        private val getTap1PanBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "get_tap1_pan", GET_TAP1_PAN_HASH)
        }

        private const val SET_TAP2_ACTIVE_HASH = 2586408642L
        private val setTap2ActiveBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "set_tap2_active", SET_TAP2_ACTIVE_HASH)
        }

        private const val IS_TAP2_ACTIVE_HASH = 36873697L
        private val isTap2ActiveBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "is_tap2_active", IS_TAP2_ACTIVE_HASH)
        }

        private const val SET_TAP2_DELAY_MS_HASH = 373806689L
        private val setTap2DelayMsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "set_tap2_delay_ms", SET_TAP2_DELAY_MS_HASH)
        }

        private const val GET_TAP2_DELAY_MS_HASH = 1740695150L
        private val getTap2DelayMsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "get_tap2_delay_ms", GET_TAP2_DELAY_MS_HASH)
        }

        private const val SET_TAP2_LEVEL_DB_HASH = 373806689L
        private val setTap2LevelDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "set_tap2_level_db", SET_TAP2_LEVEL_DB_HASH)
        }

        private const val GET_TAP2_LEVEL_DB_HASH = 1740695150L
        private val getTap2LevelDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "get_tap2_level_db", GET_TAP2_LEVEL_DB_HASH)
        }

        private const val SET_TAP2_PAN_HASH = 373806689L
        private val setTap2PanBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "set_tap2_pan", SET_TAP2_PAN_HASH)
        }

        private const val GET_TAP2_PAN_HASH = 1740695150L
        private val getTap2PanBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "get_tap2_pan", GET_TAP2_PAN_HASH)
        }

        private const val SET_FEEDBACK_ACTIVE_HASH = 2586408642L
        private val setFeedbackActiveBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "set_feedback_active", SET_FEEDBACK_ACTIVE_HASH)
        }

        private const val IS_FEEDBACK_ACTIVE_HASH = 36873697L
        private val isFeedbackActiveBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "is_feedback_active", IS_FEEDBACK_ACTIVE_HASH)
        }

        private const val SET_FEEDBACK_DELAY_MS_HASH = 373806689L
        private val setFeedbackDelayMsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "set_feedback_delay_ms", SET_FEEDBACK_DELAY_MS_HASH)
        }

        private const val GET_FEEDBACK_DELAY_MS_HASH = 1740695150L
        private val getFeedbackDelayMsBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "get_feedback_delay_ms", GET_FEEDBACK_DELAY_MS_HASH)
        }

        private const val SET_FEEDBACK_LEVEL_DB_HASH = 373806689L
        private val setFeedbackLevelDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "set_feedback_level_db", SET_FEEDBACK_LEVEL_DB_HASH)
        }

        private const val GET_FEEDBACK_LEVEL_DB_HASH = 1740695150L
        private val getFeedbackLevelDbBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "get_feedback_level_db", GET_FEEDBACK_LEVEL_DB_HASH)
        }

        private const val SET_FEEDBACK_LOWPASS_HASH = 373806689L
        private val setFeedbackLowpassBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "set_feedback_lowpass", SET_FEEDBACK_LOWPASS_HASH)
        }

        private const val GET_FEEDBACK_LOWPASS_HASH = 1740695150L
        private val getFeedbackLowpassBind by lazy {
            ObjectCalls.getMethodBind("AudioEffectDelay", "get_feedback_lowpass", GET_FEEDBACK_LOWPASS_HASH)
        }
    }
}
