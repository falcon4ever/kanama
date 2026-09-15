package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A vertical scrollbar that goes from top (min) to bottom (max).
 *
 * Generated from Godot docs: VScrollBar
 */
class VScrollBar(handle: GodotHandle) : ScrollBar(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VScrollBar? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VScrollBar? =
            if (handle.address() == 0L) null else VScrollBar(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
