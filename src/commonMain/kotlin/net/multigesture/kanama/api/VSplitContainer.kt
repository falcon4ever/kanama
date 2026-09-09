package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A container that splits two child controls vertically and provides a grabber for adjusting the
 * split ratio.
 *
 * Generated from Godot docs: VSplitContainer
 */
class VSplitContainer(handle: MemorySegment) : SplitContainer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VSplitContainer? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VSplitContainer? =
            if (handle.address() == 0L) null else VSplitContainer(handle)

        // No MethodBinds emitted yet.
    }
}
