package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A button that represents a binary choice.
 *
 * Generated from Godot docs: CheckButton
 */
class CheckButton(handle: MemorySegment) : Button(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): CheckButton? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): CheckButton? =
            if (handle.address() == 0L) null else CheckButton(handle)

        // No MethodBinds emitted yet.
    }
}
