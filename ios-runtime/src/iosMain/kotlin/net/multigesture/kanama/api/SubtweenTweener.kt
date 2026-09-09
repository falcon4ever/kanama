package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: SubtweenTweener
 */
class SubtweenTweener(handle: MemorySegment) : Tweener(handle) {
    fun setDelay(delay: Double): SubtweenTweener? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetObject(setDelayBind, handle, delay)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return SubtweenTweener.wrap(ret)
    }

    companion object {
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
