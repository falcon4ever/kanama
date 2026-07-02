package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A container that keeps a margin around its child controls.
 *
 * Generated from Godot docs: MarginContainer
 */
open class MarginContainer(handle: MemorySegment) : Container(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): MarginContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): MarginContainer? =
            if (handle.address() == 0L) null else MarginContainer(handle)

        // No MethodBinds emitted yet.
    }
}
