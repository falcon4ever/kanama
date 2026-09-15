package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A horizontal scrollbar that goes from left (min) to right (max).
 *
 * Generated from Godot docs: HScrollBar
 */
class HScrollBar(handle: GodotHandle) : ScrollBar(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): HScrollBar? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): HScrollBar? =
            if (handle.address() == 0L) null else HScrollBar(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
