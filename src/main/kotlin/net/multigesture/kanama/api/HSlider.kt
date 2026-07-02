package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A horizontal slider that goes from left (min) to right (max).
 *
 * Generated from Godot docs: HSlider
 */
class HSlider(handle: MemorySegment) : Slider(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): HSlider? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): HSlider? =
            if (handle.address() == 0L) null else HSlider(handle)

        // No MethodBinds emitted yet.
    }
}
