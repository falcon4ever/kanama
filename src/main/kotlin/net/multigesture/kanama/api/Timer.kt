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

    fun setWaitTime(timeSec: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setWaitTimeBind, handle, timeSec)
    }

    fun getWaitTime(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getWaitTimeBind, handle)
    }

    fun setOneShot(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setOneShotBind, handle, enable)
    }

    fun isOneShot(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isOneShotBind, handle)
    }

    fun setAutostart(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAutostartBind, handle, enable)
    }

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

    fun setPaused(paused: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setPausedBind, handle, paused)
    }

    fun isPaused(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isPausedBind, handle)
    }

    fun setIgnoreTimeScale(ignore: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setIgnoreTimeScaleBind, handle, ignore)
    }

    fun isIgnoringTimeScale(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isIgnoringTimeScaleBind, handle)
    }

    fun isStopped(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isStoppedBind, handle)
    }

    fun getTimeLeft(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimeLeftBind, handle)
    }

    fun setTimerProcessCallback(callback: Long) {
        ObjectCalls.ptrcallWithLongArg(setTimerProcessCallbackBind, handle, callback)
    }

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
