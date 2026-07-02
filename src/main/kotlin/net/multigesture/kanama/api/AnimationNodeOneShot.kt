package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Plays an animation once in an `AnimationNodeBlendTree`.
 *
 * Generated from Godot docs: AnimationNodeOneShot
 */
class AnimationNodeOneShot(handle: MemorySegment) : AnimationNodeSync(handle) {
    var mixMode: Long
        @JvmName("mixModeProperty")
        get() = getMixMode()
        @JvmName("setMixModeProperty")
        set(value) = setMixMode(value)

    var fadeinTime: Double
        @JvmName("fadeinTimeProperty")
        get() = getFadeinTime()
        @JvmName("setFadeinTimeProperty")
        set(value) = setFadeinTime(value)

    var fadeinCurve: Curve?
        @JvmName("fadeinCurveProperty")
        get() = getFadeinCurve()
        @JvmName("setFadeinCurveProperty")
        set(value) = setFadeinCurve(value)

    var fadeoutTime: Double
        @JvmName("fadeoutTimeProperty")
        get() = getFadeoutTime()
        @JvmName("setFadeoutTimeProperty")
        set(value) = setFadeoutTime(value)

    var fadeoutCurve: Curve?
        @JvmName("fadeoutCurveProperty")
        get() = getFadeoutCurve()
        @JvmName("setFadeoutCurveProperty")
        set(value) = setFadeoutCurve(value)

    var breakLoopAtEnd: Boolean
        @JvmName("breakLoopAtEndProperty")
        get() = isLoopBrokenAtEnd()
        @JvmName("setBreakLoopAtEndProperty")
        set(value) = setBreakLoopAtEnd(value)

    var abortOnReset: Boolean
        @JvmName("abortOnResetProperty")
        get() = isAbortedOnReset()
        @JvmName("setAbortOnResetProperty")
        set(value) = setAbortOnReset(value)

    var autorestart: Boolean
        @JvmName("autorestartProperty")
        get() = hasAutorestart()
        @JvmName("setAutorestartProperty")
        set(value) = setAutorestart(value)

    var autorestartDelay: Double
        @JvmName("autorestartDelayProperty")
        get() = getAutorestartDelay()
        @JvmName("setAutorestartDelayProperty")
        set(value) = setAutorestartDelay(value)

    var autorestartRandomDelay: Double
        @JvmName("autorestartRandomDelayProperty")
        get() = getAutorestartRandomDelay()
        @JvmName("setAutorestartRandomDelayProperty")
        set(value) = setAutorestartRandomDelay(value)

    fun setFadeinTime(time: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFadeinTimeBind, handle, time)
    }

    fun getFadeinTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFadeinTimeBind, handle)
    }

    fun setFadeinCurve(curve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setFadeinCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getFadeinCurve(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFadeinCurveBind, handle))
    }

    fun setFadeoutTime(time: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFadeoutTimeBind, handle, time)
    }

    fun getFadeoutTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFadeoutTimeBind, handle)
    }

    fun setFadeoutCurve(curve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setFadeoutCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getFadeoutCurve(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFadeoutCurveBind, handle))
    }

    fun setBreakLoopAtEnd(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBreakLoopAtEndBind, handle, enable)
    }

    fun isLoopBrokenAtEnd(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLoopBrokenAtEndBind, handle)
    }

    fun setAbortOnReset(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAbortOnResetBind, handle, enable)
    }

    fun isAbortedOnReset(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAbortedOnResetBind, handle)
    }

    fun setAutorestart(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutorestartBind, handle, active)
    }

    fun hasAutorestart(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasAutorestartBind, handle)
    }

    fun setAutorestartDelay(time: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutorestartDelayBind, handle, time)
    }

    fun getAutorestartDelay(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAutorestartDelayBind, handle)
    }

    fun setAutorestartRandomDelay(time: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutorestartRandomDelayBind, handle, time)
    }

    fun getAutorestartRandomDelay(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAutorestartRandomDelayBind, handle)
    }

    fun setMixMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setMixModeBind, handle, mode)
    }

    fun getMixMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMixModeBind, handle)
    }

    companion object {
        const val ONE_SHOT_REQUEST_NONE: Long = 0L
        const val ONE_SHOT_REQUEST_FIRE: Long = 1L
        const val ONE_SHOT_REQUEST_ABORT: Long = 2L
        const val ONE_SHOT_REQUEST_FADE_OUT: Long = 3L
        const val MIX_MODE_BLEND: Long = 0L
        const val MIX_MODE_ADD: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimationNodeOneShot? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimationNodeOneShot? =
            if (handle.address() == 0L) null else AnimationNodeOneShot(handle)

        private const val SET_FADEIN_TIME_HASH = 373806689L
        private val setFadeinTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "set_fadein_time", SET_FADEIN_TIME_HASH)
        }

        private const val GET_FADEIN_TIME_HASH = 1740695150L
        private val getFadeinTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "get_fadein_time", GET_FADEIN_TIME_HASH)
        }

        private const val SET_FADEIN_CURVE_HASH = 270443179L
        private val setFadeinCurveBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "set_fadein_curve", SET_FADEIN_CURVE_HASH)
        }

        private const val GET_FADEIN_CURVE_HASH = 2460114913L
        private val getFadeinCurveBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "get_fadein_curve", GET_FADEIN_CURVE_HASH)
        }

        private const val SET_FADEOUT_TIME_HASH = 373806689L
        private val setFadeoutTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "set_fadeout_time", SET_FADEOUT_TIME_HASH)
        }

        private const val GET_FADEOUT_TIME_HASH = 1740695150L
        private val getFadeoutTimeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "get_fadeout_time", GET_FADEOUT_TIME_HASH)
        }

        private const val SET_FADEOUT_CURVE_HASH = 270443179L
        private val setFadeoutCurveBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "set_fadeout_curve", SET_FADEOUT_CURVE_HASH)
        }

        private const val GET_FADEOUT_CURVE_HASH = 2460114913L
        private val getFadeoutCurveBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "get_fadeout_curve", GET_FADEOUT_CURVE_HASH)
        }

        private const val SET_BREAK_LOOP_AT_END_HASH = 2586408642L
        private val setBreakLoopAtEndBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "set_break_loop_at_end", SET_BREAK_LOOP_AT_END_HASH)
        }

        private const val IS_LOOP_BROKEN_AT_END_HASH = 36873697L
        private val isLoopBrokenAtEndBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "is_loop_broken_at_end", IS_LOOP_BROKEN_AT_END_HASH)
        }

        private const val SET_ABORT_ON_RESET_HASH = 2586408642L
        private val setAbortOnResetBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "set_abort_on_reset", SET_ABORT_ON_RESET_HASH)
        }

        private const val IS_ABORTED_ON_RESET_HASH = 36873697L
        private val isAbortedOnResetBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "is_aborted_on_reset", IS_ABORTED_ON_RESET_HASH)
        }

        private const val SET_AUTORESTART_HASH = 2586408642L
        private val setAutorestartBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "set_autorestart", SET_AUTORESTART_HASH)
        }

        private const val HAS_AUTORESTART_HASH = 36873697L
        private val hasAutorestartBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "has_autorestart", HAS_AUTORESTART_HASH)
        }

        private const val SET_AUTORESTART_DELAY_HASH = 373806689L
        private val setAutorestartDelayBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "set_autorestart_delay", SET_AUTORESTART_DELAY_HASH)
        }

        private const val GET_AUTORESTART_DELAY_HASH = 1740695150L
        private val getAutorestartDelayBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "get_autorestart_delay", GET_AUTORESTART_DELAY_HASH)
        }

        private const val SET_AUTORESTART_RANDOM_DELAY_HASH = 373806689L
        private val setAutorestartRandomDelayBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "set_autorestart_random_delay", SET_AUTORESTART_RANDOM_DELAY_HASH)
        }

        private const val GET_AUTORESTART_RANDOM_DELAY_HASH = 1740695150L
        private val getAutorestartRandomDelayBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "get_autorestart_random_delay", GET_AUTORESTART_RANDOM_DELAY_HASH)
        }

        private const val SET_MIX_MODE_HASH = 1018899799L
        private val setMixModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "set_mix_mode", SET_MIX_MODE_HASH)
        }

        private const val GET_MIX_MODE_HASH = 3076550526L
        private val getMixModeBind by lazy {
            ObjectCalls.getMethodBind("AnimationNodeOneShot", "get_mix_mode", GET_MIX_MODE_HASH)
        }
    }
}
