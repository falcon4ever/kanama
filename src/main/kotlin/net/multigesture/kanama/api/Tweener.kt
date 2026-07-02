package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Abstract class for all Tweeners used by `Tween`.
 *
 * Generated from Godot docs: Tweener
 */
open class Tweener(handle: MemorySegment) : RefCounted(handle) {
    // No conservative instance methods emitted yet.

    object Signals {
        const val finished: String = "finished"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Tweener? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Tweener? =
            if (handle.address() == 0L) null else Tweener(handle)

        // No MethodBinds emitted yet.
    }
}
