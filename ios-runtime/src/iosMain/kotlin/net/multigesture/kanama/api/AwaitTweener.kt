package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: AwaitTweener
 */
class AwaitTweener(handle: MemorySegment) : Tweener(handle) {
    fun setTimeout(timeout: Double): AwaitTweener? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetObject(setTimeoutBind, handle, timeout)
        if (ret.address() == handle.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return AwaitTweener.wrap(ret)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): AwaitTweener? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AwaitTweener? =
            if (handle.address() == 0L) null else AwaitTweener(handle)

        private const val SET_TIMEOUT_HASH = 3123469156L
        private val setTimeoutBind by lazy {
            ObjectCalls.getMethodBind("AwaitTweener", "set_timeout", SET_TIMEOUT_HASH)
        }
    }
}
