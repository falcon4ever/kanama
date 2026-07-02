package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * One-shot timer.
 *
 * Generated from Godot docs: SceneTreeTimer
 */
class SceneTreeTimer(handle: MemorySegment) : RefCounted(handle) {
    var timeLeft: Double
        @JvmName("timeLeftProperty")
        get() = getTimeLeft()
        @JvmName("setTimeLeftProperty")
        set(value) = setTimeLeft(value)

    fun setTimeLeft(time: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTimeLeftBind, handle, time)
    }

    fun getTimeLeft(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimeLeftBind, handle)
    }

    object Signals {
        const val timeout: String = "timeout"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SceneTreeTimer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SceneTreeTimer? =
            if (handle.address() == 0L) null else SceneTreeTimer(handle)

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
