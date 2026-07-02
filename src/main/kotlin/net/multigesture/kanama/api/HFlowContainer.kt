package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A container that arranges its child controls horizontally and wraps them around at the borders.
 *
 * Generated from Godot docs: HFlowContainer
 */
class HFlowContainer(handle: MemorySegment) : FlowContainer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): HFlowContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): HFlowContainer? =
            if (handle.address() == 0L) null else HFlowContainer(handle)

        // No MethodBinds emitted yet.
    }
}
