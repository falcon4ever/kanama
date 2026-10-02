package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Calls the specified method after optional delay.
 *
 * Generated from Godot docs: CallbackTweener
 */
class CallbackTweener(handle: GodotHandle) : Tweener(handle) {
    /**
     * Makes the callback call delayed by given time in seconds.
     *
     * Generated from Godot docs: CallbackTweener.set_delay
     */
    fun setDelay(delay: Double): CallbackTweener? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetObject(setDelayBind, segment, delay)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return CallbackTweener.wrap(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CallbackTweener? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CallbackTweener? =
            if (handle.address() == 0L) null else CallbackTweener(GodotHandle(handle))

        private const val SET_DELAY_HASH = 3008182292L
        private val setDelayBind by lazy {
            ObjectCalls.getMethodBind("CallbackTweener", "set_delay", SET_DELAY_HASH)
        }
    }
}
