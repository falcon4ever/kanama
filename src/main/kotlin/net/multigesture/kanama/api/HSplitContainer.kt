package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A container that splits two child controls horizontally and provides a grabber for adjusting the
 * split ratio.
 *
 * Generated from Godot docs: HSplitContainer
 */
class HSplitContainer(handle: MemorySegment) : SplitContainer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): HSplitContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): HSplitContainer? =
            if (handle.address() == 0L) null else HSplitContainer(handle)

        // No MethodBinds emitted yet.
    }
}
