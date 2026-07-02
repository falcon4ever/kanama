package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A container that keeps its child controls within the area of a `StyleBox`.
 *
 * Generated from Godot docs: PanelContainer
 */
open class PanelContainer(handle: MemorySegment) : Container(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PanelContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PanelContainer? =
            if (handle.address() == 0L) null else PanelContainer(handle)

        // No MethodBinds emitted yet.
    }
}
