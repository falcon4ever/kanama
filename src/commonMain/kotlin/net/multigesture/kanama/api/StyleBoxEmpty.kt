package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * An empty `StyleBox` (does not display anything).
 *
 * Generated from Godot docs: StyleBoxEmpty
 */
class StyleBoxEmpty(handle: MemorySegment) : StyleBox(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): StyleBoxEmpty? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StyleBoxEmpty? =
            if (handle.address() == 0L) null else StyleBoxEmpty(handle)

        // No MethodBinds emitted yet.
    }
}
