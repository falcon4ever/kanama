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

    /**
     * The fade-in duration. For example, setting this to `1.0` for a 5 second length animation will
     * produce a cross-fade that starts at 0 second and ends at 1 second during the animation. Note:
     * `AnimationNodeOneShot` transitions the current state after the fading has finished.
     *
     * Generated from Godot docs: AnimationNodeOneShot.set_fadein_time
     */
    fun setFadeinTime(time: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFadeinTimeBind, handle, time)
    }

    /**
     * The fade-in duration. For example, setting this to `1.0` for a 5 second length animation will
     * produce a cross-fade that starts at 0 second and ends at 1 second during the animation. Note:
     * `AnimationNodeOneShot` transitions the current state after the fading has finished.
     *
     * Generated from Godot docs: AnimationNodeOneShot.get_fadein_time
     */
    fun getFadeinTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFadeinTimeBind, handle)
    }

    /**
     * Determines how cross-fading between animations is eased. If empty, the transition will be
     * linear. Should be a unit `Curve`.
     *
     * Generated from Godot docs: AnimationNodeOneShot.set_fadein_curve
     */
    fun setFadeinCurve(curve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setFadeinCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Determines how cross-fading between animations is eased. If empty, the transition will be
     * linear. Should be a unit `Curve`.
     *
     * Generated from Godot docs: AnimationNodeOneShot.get_fadein_curve
     */
    fun getFadeinCurve(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFadeinCurveBind, handle))
    }

    /**
     * The fade-out duration. For example, setting this to `1.0` for a 5 second length animation will
     * produce a cross-fade that starts at 4 second and ends at 5 second during the animation. Note:
     * `AnimationNodeOneShot` transitions the current state after the fading has finished.
     *
     * Generated from Godot docs: AnimationNodeOneShot.set_fadeout_time
     */
    fun setFadeoutTime(time: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFadeoutTimeBind, handle, time)
    }

    /**
     * The fade-out duration. For example, setting this to `1.0` for a 5 second length animation will
     * produce a cross-fade that starts at 4 second and ends at 5 second during the animation. Note:
     * `AnimationNodeOneShot` transitions the current state after the fading has finished.
     *
     * Generated from Godot docs: AnimationNodeOneShot.get_fadeout_time
     */
    fun getFadeoutTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFadeoutTimeBind, handle)
    }

    /**
     * Determines how cross-fading between animations is eased. If empty, the transition will be
     * linear. Should be a unit `Curve`.
     *
     * Generated from Godot docs: AnimationNodeOneShot.set_fadeout_curve
     */
    fun setFadeoutCurve(curve: Curve?) {
        ObjectCalls.ptrcallWithObjectArgs(setFadeoutCurveBind, handle, listOf(curve?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    /**
     * Determines how cross-fading between animations is eased. If empty, the transition will be
     * linear. Should be a unit `Curve`.
     *
     * Generated from Godot docs: AnimationNodeOneShot.get_fadeout_curve
     */
    fun getFadeoutCurve(): Curve? {
        return Curve.wrap(ObjectCalls.ptrcallNoArgsRetObject(getFadeoutCurveBind, handle))
    }

    /**
     * If `true`, breaks the loop at the end of the loop cycle for transition, even if the animation is
     * looping.
     *
     * Generated from Godot docs: AnimationNodeOneShot.set_break_loop_at_end
     */
    fun setBreakLoopAtEnd(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBreakLoopAtEndBind, handle, enable)
    }

    /**
     * If `true`, breaks the loop at the end of the loop cycle for transition, even if the animation is
     * looping.
     *
     * Generated from Godot docs: AnimationNodeOneShot.is_loop_broken_at_end
     */
    fun isLoopBrokenAtEnd(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLoopBrokenAtEndBind, handle)
    }

    /**
     * If `true`, the sub-animation will abort if resumed with a reset after a prior interruption.
     *
     * Generated from Godot docs: AnimationNodeOneShot.set_abort_on_reset
     */
    fun setAbortOnReset(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAbortOnResetBind, handle, enable)
    }

    /**
     * If `true`, the sub-animation will abort if resumed with a reset after a prior interruption.
     *
     * Generated from Godot docs: AnimationNodeOneShot.is_aborted_on_reset
     */
    fun isAbortedOnReset(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAbortedOnResetBind, handle)
    }

    /**
     * If `true`, the sub-animation will restart automatically after finishing. In other words, to
     * start auto restarting, the animation must be played once with the `ONE_SHOT_REQUEST_FIRE`
     * request. The `ONE_SHOT_REQUEST_ABORT` request stops the auto restarting, but it does not disable
     * the `autorestart` itself. So, the `ONE_SHOT_REQUEST_FIRE` request will start auto restarting
     * again.
     *
     * Generated from Godot docs: AnimationNodeOneShot.set_autorestart
     */
    fun setAutorestart(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutorestartBind, handle, active)
    }

    /**
     * If `true`, the sub-animation will restart automatically after finishing. In other words, to
     * start auto restarting, the animation must be played once with the `ONE_SHOT_REQUEST_FIRE`
     * request. The `ONE_SHOT_REQUEST_ABORT` request stops the auto restarting, but it does not disable
     * the `autorestart` itself. So, the `ONE_SHOT_REQUEST_FIRE` request will start auto restarting
     * again.
     *
     * Generated from Godot docs: AnimationNodeOneShot.has_autorestart
     */
    fun hasAutorestart(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasAutorestartBind, handle)
    }

    /**
     * The delay after which the automatic restart is triggered, in seconds.
     *
     * Generated from Godot docs: AnimationNodeOneShot.set_autorestart_delay
     */
    fun setAutorestartDelay(time: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutorestartDelayBind, handle, time)
    }

    /**
     * The delay after which the automatic restart is triggered, in seconds.
     *
     * Generated from Godot docs: AnimationNodeOneShot.get_autorestart_delay
     */
    fun getAutorestartDelay(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAutorestartDelayBind, handle)
    }

    /**
     * If `autorestart` is `true`, a random additional delay (in seconds) between 0 and this value will
     * be added to `autorestart_delay`.
     *
     * Generated from Godot docs: AnimationNodeOneShot.set_autorestart_random_delay
     */
    fun setAutorestartRandomDelay(time: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAutorestartRandomDelayBind, handle, time)
    }

    /**
     * If `autorestart` is `true`, a random additional delay (in seconds) between 0 and this value will
     * be added to `autorestart_delay`.
     *
     * Generated from Godot docs: AnimationNodeOneShot.get_autorestart_random_delay
     */
    fun getAutorestartRandomDelay(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAutorestartRandomDelayBind, handle)
    }

    /**
     * The blend type.
     *
     * Generated from Godot docs: AnimationNodeOneShot.set_mix_mode
     */
    fun setMixMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setMixModeBind, handle, mode)
    }

    /**
     * The blend type.
     *
     * Generated from Godot docs: AnimationNodeOneShot.get_mix_mode
     */
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
