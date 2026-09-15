package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Awaits a specified signal.
 *
 * Generated from Godot docs: AwaitTweener
 */
class AwaitTweener(handle: GodotHandle) : Tweener(handle) {
    /**
     * Sets the maximum time an `AwaitTweener` can wait for the signal. Can be used as a safeguard for
     * signals that may never be emitted. If not specified, the tweener will wait indefinitely.
     *
     * Generated from Godot docs: AwaitTweener.set_timeout
     */
    fun setTimeout(timeout: Double): AwaitTweener? {
        checkOpen()
        val ret = ObjectCalls.ptrcallWithDoubleArgRetObject(setTimeoutBind, segment, timeout)
        if (ret.address() == segment.address()) {
            RefCounted.releaseHandle(ret)
            return this
        }
        return AwaitTweener.wrap(ret)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AwaitTweener? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AwaitTweener? =
            if (handle.address() == 0L) null else AwaitTweener(GodotHandle(handle))

        private const val SET_TIMEOUT_HASH = 3123469156L
        private val setTimeoutBind by lazy {
            ObjectCalls.getMethodBind("AwaitTweener", "set_timeout", SET_TIMEOUT_HASH)
        }
    }
}
