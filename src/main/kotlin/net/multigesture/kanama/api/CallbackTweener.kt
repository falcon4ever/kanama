package net.multigesture.kanama.api

import net.multigesture.kanama.binding.runtime.ObjectCalls
import java.lang.foreign.MemorySegment

/**
 * Calls the specified method after optional delay.
 *
 * Generated from Godot docs: CallbackTweener
 */
class CallbackTweener internal constructor(handle: GodotHandle) : Tweener(handle) {

    /**
     * Makes the callback call delayed by given time in seconds.
     *
     * Generated from Godot docs: CallbackTweener.set_delay
     */
    fun setDelay(delay: Double): CallbackTweener {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetObject(setDelayBind, segment, delay)
        return if (ret.address() == 0L) {
            this
        } else if (ret.address() == segment.address()) {
            releaseHandle(ret)
            this
        } else {
            CallbackTweener(GodotHandle(ret))
        }
    }

    companion object {
        private const val SET_DELAY_HASH = 3008182292L

        private val setDelayBind by lazy {
            ObjectCalls.getMethodBind("CallbackTweener", "set_delay", SET_DELAY_HASH)
        }

        internal fun wrap(handle: MemorySegment): CallbackTweener? =
            if (handle.address() == 0L) null else CallbackTweener(GodotHandle(handle))
    }
}
