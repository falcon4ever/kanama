package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * One-shot timer.
 *
 * Generated from Godot docs: SceneTreeTimer
 */
class SceneTreeTimer(handle: GodotHandle) : RefCounted(handle) {
    var timeLeft: Double
        @JvmName("timeLeftProperty")
        get() = getTimeLeft()
        @JvmName("setTimeLeftProperty")
        set(value) = setTimeLeft(value)

    /**
     * The time remaining (in seconds).
     *
     * Generated from Godot docs: SceneTreeTimer.set_time_left
     */
    fun setTimeLeft(time: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setTimeLeftBind, segment, time)
    }

    /**
     * The time remaining (in seconds).
     *
     * Generated from Godot docs: SceneTreeTimer.get_time_left
     */
    fun getTimeLeft(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimeLeftBind, segment)
    }

    object Signals {
        const val timeout: String = "timeout"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SceneTreeTimer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SceneTreeTimer? =
            if (handle.address() == 0L) null else SceneTreeTimer(GodotHandle(handle))

        private const val SET_TIME_LEFT_HASH = 373806689L
        private val setTimeLeftBind by lazy {
            ObjectCalls.getMethodBind("SceneTreeTimer", "set_time_left", SET_TIME_LEFT_HASH)
        }

        private const val GET_TIME_LEFT_HASH = 1740695150L
        private val getTimeLeftBind by lazy {
            ObjectCalls.getMethodBind("SceneTreeTimer", "get_time_left", GET_TIME_LEFT_HASH)
        }
    }
}
