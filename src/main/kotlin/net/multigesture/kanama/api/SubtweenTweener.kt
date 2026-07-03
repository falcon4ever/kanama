package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Runs a `Tween` nested within another `Tween`.
 *
 * Generated from Godot docs: SubtweenTweener
 */
class SubtweenTweener(handle: MemorySegment) : Tweener(handle) {
    /**
     * Sets the time in seconds after which the `SubtweenTweener` will start running the subtween. By
     * default there's no delay.
     *
     * Generated from Godot docs: SubtweenTweener.set_delay
     */
    fun setDelay(delay: Double): SubtweenTweener? {
        return SubtweenTweener.wrap(ObjectCalls.ptrcallWithDoubleArgRetObject(setDelayBind, handle, delay))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SubtweenTweener? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SubtweenTweener? =
            if (handle.address() == 0L) null else SubtweenTweener(handle)

        private const val SET_DELAY_HASH = 449181780L
        private val setDelayBind by lazy {
            ObjectCalls.getMethodBind("SubtweenTweener", "set_delay", SET_DELAY_HASH)
        }
    }
}
