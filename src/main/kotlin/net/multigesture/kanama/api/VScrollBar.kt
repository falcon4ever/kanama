package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A vertical scrollbar that goes from top (min) to bottom (max).
 *
 * Generated from Godot docs: VScrollBar
 */
class VScrollBar(handle: MemorySegment) : ScrollBar(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VScrollBar? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VScrollBar? =
            if (handle.address() == 0L) null else VScrollBar(handle)

        // No MethodBinds emitted yet.
    }
}
