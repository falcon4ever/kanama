package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A horizontal scrollbar that goes from left (min) to right (max).
 *
 * Generated from Godot docs: HScrollBar
 */
class HScrollBar(handle: MemorySegment) : ScrollBar(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): HScrollBar? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): HScrollBar? =
            if (handle.address() == 0L) null else HScrollBar(handle)

        // No MethodBinds emitted yet.
    }
}
