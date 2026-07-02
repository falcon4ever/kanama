package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A horizontal line used for separating other controls.
 *
 * Generated from Godot docs: HSeparator
 */
class HSeparator(handle: MemorySegment) : Separator(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): HSeparator? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): HSeparator? =
            if (handle.address() == 0L) null else HSeparator(handle)

        // No MethodBinds emitted yet.
    }
}
