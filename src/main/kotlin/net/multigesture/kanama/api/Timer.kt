package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A countdown timer.
 *
 * Generated from Godot docs: Timer
 */
class Timer(handle: MemorySegment) : Node(handle) {
    var processCallback: Long
        @JvmName("processCallbackProperty")
        get() = getTimerProcessCallback()
        @JvmName("setProcessCallbackProperty")
        set(value) = setTimerProcessCallback(value)

    var waitTime: Double
        @JvmName("waitTimeProperty")
        get() = getWaitTime()
        @JvmName("setWaitTimeProperty")
        set(value) = setWaitTime(value)

    var oneShot: Boolean
        @JvmName("oneShotProperty")
        get() = isOneShot()
        @JvmName("setOneShotProperty")
        set(value) = setOneShot(value)

    var autostart: Boolean
        @JvmName("autostartProperty")
        get() = hasAutostart()
        @JvmName("setAutostartProperty")
        set(value) = setAutostart(value)

    var paused: Boolean
        @JvmName("pausedProperty")
        get() = isPaused()
        @JvmName("setPausedProperty")
        set(value) = setPaused(value)

    var ignoreTimeScale: Boolean
        @JvmName("ignoreTimeScaleProperty")
        get() = isIgnoringTimeScale()
        @JvmName("setIgnoreTimeScaleProperty")
        set(value) = setIgnoreTimeScale(value)

    val timeLeft: Double
        @JvmName("timeLeftProperty")
        get() = getTimeLeft()

    /**
     * The time required for the timer to end, in seconds. This property can also be set every time
     * `start` is called. Note: Timers can only process once per physics or process frame (depending on
     * the `process_callback`). An unstable framerate may cause the timer to end inconsistently, which
     * is especially noticeable if the wait time is lower than roughly `0.05` seconds. For very short
     * timers, it is recommended to write your own code instead of using a `Timer` node. Timers are
     * also affected by `Engine.time_scale`.
     *
     * Generated from Godot docs: Timer.set_wait_time
     */
    fun setWaitTime(timeSec: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWaitTimeBind, handle, timeSec)
    }

    /**
     * The time required for the timer to end, in seconds. This property can also be set every time
     * `start` is called. Note: Timers can only process once per physics or process frame (depending on
     * the `process_callback`). An unstable framerate may cause the timer to end inconsistently, which
     * is especially noticeable if the wait time is lower than roughly `0.05` seconds. For very short
     * timers, it is recommended to write your own code instead of using a `Timer` node. Timers are
     * also affected by `Engine.time_scale`.
     *
     * Generated from Godot docs: Timer.get_wait_time
     */
    fun getWaitTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWaitTimeBind, handle)
    }

    /**
     * If `true`, the timer will stop after reaching the end. Otherwise, as by default, the timer will
     * automatically restart.
     *
     * Generated from Godot docs: Timer.set_one_shot
     */
    fun setOneShot(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOneShotBind, handle, enable)
    }

    /**
     * If `true`, the timer will stop after reaching the end. Otherwise, as by default, the timer will
     * automatically restart.
     *
     * Generated from Godot docs: Timer.is_one_shot
     */
    fun isOneShot(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOneShotBind, handle)
    }

    /**
     * If `true`, the timer will start immediately when it enters the scene tree. Note: After the timer
     * enters the tree, this property is automatically set to `false`. Note: This property does nothing
     * when the timer is running in the editor.
     *
     * Generated from Godot docs: Timer.set_autostart
     */
    fun setAutostart(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutostartBind, handle, enable)
    }

    /**
     * If `true`, the timer will start immediately when it enters the scene tree. Note: After the timer
     * enters the tree, this property is automatically set to `false`. Note: This property does nothing
     * when the timer is running in the editor.
     *
     * Generated from Godot docs: Timer.has_autostart
     */
    fun hasAutostart(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(hasAutostartBind, handle)
    }

    /**
     * Starts the timer, or resets the timer if it was started already. Fails if the timer is not
     * inside the scene tree. If `time_sec` is greater than `0`, this value is used for the
     * `wait_time`. Note: This method does not resume a paused timer. See `paused`.
     *
     * Generated from Godot docs: Timer.start
     */
    fun start(timeSec: Double = -1.0) {
        ObjectCalls.ptrcallWithDoubleArg(startBind, handle, timeSec)
    }

    /**
     * Stops the timer. See also `paused`. Unlike `start`, this can safely be called if the timer is
     * not inside the scene tree. Note: Calling `stop` does not emit the `timeout` signal, as the timer
     * is not considered to have timed out. If this is desired, use `$Timer.timeout.emit()` after
     * calling `stop` to manually emit the signal.
     *
     * Generated from Godot docs: Timer.stop
     */
    fun stop() {
        ObjectCalls.ptrcallNoArgs(stopBind, handle)
    }

    /**
     * If `true`, the timer is paused. A paused timer does not process until this property is set back
     * to `false`, even when `start` is called. See also `stop`.
     *
     * Generated from Godot docs: Timer.set_paused
     */
    fun setPaused(paused: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPausedBind, handle, paused)
    }

    /**
     * If `true`, the timer is paused. A paused timer does not process until this property is set back
     * to `false`, even when `start` is called. See also `stop`.
     *
     * Generated from Godot docs: Timer.is_paused
     */
    fun isPaused(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPausedBind, handle)
    }

    /**
     * If `true`, the timer will ignore `Engine.time_scale` and update with the real, elapsed time.
     *
     * Generated from Godot docs: Timer.set_ignore_time_scale
     */
    fun setIgnoreTimeScale(ignore: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreTimeScaleBind, handle, ignore)
    }

    /**
     * If `true`, the timer will ignore `Engine.time_scale` and update with the real, elapsed time.
     *
     * Generated from Godot docs: Timer.is_ignoring_time_scale
     */
    fun isIgnoringTimeScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIgnoringTimeScaleBind, handle)
    }

    /**
     * Returns `true` if the timer is stopped or has not started.
     *
     * Generated from Godot docs: Timer.is_stopped
     */
    fun isStopped(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isStoppedBind, handle)
    }

    /**
     * The timer's remaining time in seconds. This is always `0` if the timer is stopped. Note: This
     * property is read-only and cannot be modified. It is based on `wait_time`.
     *
     * Generated from Godot docs: Timer.get_time_left
     */
    fun getTimeLeft(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimeLeftBind, handle)
    }

    /**
     * Specifies when the timer is updated during the main loop.
     *
     * Generated from Godot docs: Timer.set_timer_process_callback
     */
    fun setTimerProcessCallback(callback: Long) {
        ObjectCalls.ptrcallWithLongArg(setTimerProcessCallbackBind, handle, callback)
    }

    /**
     * Specifies when the timer is updated during the main loop.
     *
     * Generated from Godot docs: Timer.get_timer_process_callback
     */
    fun getTimerProcessCallback(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getTimerProcessCallbackBind, handle)
    }

    object Signals {
        const val timeout: String = "timeout"
    }

    companion object {
        const val TIMER_PROCESS_PHYSICS: Long = 0L
        const val TIMER_PROCESS_IDLE: Long = 1L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): Timer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Timer? =
            if (handle.address() == 0L) null else Timer(handle)

        private const val SET_WAIT_TIME_HASH = 373806689L
        private val setWaitTimeBind by lazy {
            ObjectCalls.getMethodBind("Timer", "set_wait_time", SET_WAIT_TIME_HASH)
        }

        private const val GET_WAIT_TIME_HASH = 1740695150L
        private val getWaitTimeBind by lazy {
            ObjectCalls.getMethodBind("Timer", "get_wait_time", GET_WAIT_TIME_HASH)
        }

        private const val SET_ONE_SHOT_HASH = 2586408642L
        private val setOneShotBind by lazy {
            ObjectCalls.getMethodBind("Timer", "set_one_shot", SET_ONE_SHOT_HASH)
        }

        private const val IS_ONE_SHOT_HASH = 36873697L
        private val isOneShotBind by lazy {
            ObjectCalls.getMethodBind("Timer", "is_one_shot", IS_ONE_SHOT_HASH)
        }

        private const val SET_AUTOSTART_HASH = 2586408642L
        private val setAutostartBind by lazy {
            ObjectCalls.getMethodBind("Timer", "set_autostart", SET_AUTOSTART_HASH)
        }

        private const val HAS_AUTOSTART_HASH = 36873697L
        private val hasAutostartBind by lazy {
            ObjectCalls.getMethodBind("Timer", "has_autostart", HAS_AUTOSTART_HASH)
        }

        private const val START_HASH = 1392008558L
        private val startBind by lazy {
            ObjectCalls.getMethodBind("Timer", "start", START_HASH)
        }

        private const val STOP_HASH = 3218959716L
        private val stopBind by lazy {
            ObjectCalls.getMethodBind("Timer", "stop", STOP_HASH)
        }

        private const val SET_PAUSED_HASH = 2586408642L
        private val setPausedBind by lazy {
            ObjectCalls.getMethodBind("Timer", "set_paused", SET_PAUSED_HASH)
        }

        private const val IS_PAUSED_HASH = 36873697L
        private val isPausedBind by lazy {
            ObjectCalls.getMethodBind("Timer", "is_paused", IS_PAUSED_HASH)
        }

        private const val SET_IGNORE_TIME_SCALE_HASH = 2586408642L
        private val setIgnoreTimeScaleBind by lazy {
            ObjectCalls.getMethodBind("Timer", "set_ignore_time_scale", SET_IGNORE_TIME_SCALE_HASH)
        }

        private const val IS_IGNORING_TIME_SCALE_HASH = 2240911060L
        private val isIgnoringTimeScaleBind by lazy {
            ObjectCalls.getMethodBind("Timer", "is_ignoring_time_scale", IS_IGNORING_TIME_SCALE_HASH)
        }

        private const val IS_STOPPED_HASH = 36873697L
        private val isStoppedBind by lazy {
            ObjectCalls.getMethodBind("Timer", "is_stopped", IS_STOPPED_HASH)
        }

        private const val GET_TIME_LEFT_HASH = 1740695150L
        private val getTimeLeftBind by lazy {
            ObjectCalls.getMethodBind("Timer", "get_time_left", GET_TIME_LEFT_HASH)
        }

        private const val SET_TIMER_PROCESS_CALLBACK_HASH = 3469495063L
        private val setTimerProcessCallbackBind by lazy {
            ObjectCalls.getMethodBind("Timer", "set_timer_process_callback", SET_TIMER_PROCESS_CALLBACK_HASH)
        }

        private const val GET_TIMER_PROCESS_CALLBACK_HASH = 2672570227L
        private val getTimerProcessCallbackBind by lazy {
            ObjectCalls.getMethodBind("Timer", "get_timer_process_callback", GET_TIMER_PROCESS_CALLBACK_HASH)
        }
    }
}
