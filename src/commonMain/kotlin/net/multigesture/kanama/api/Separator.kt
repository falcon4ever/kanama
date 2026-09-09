package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Abstract base class for separators.
 *
 * Generated from Godot docs: Separator
 */
open class Separator(handle: MemorySegment) : Control(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): Separator? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): Separator? =
            if (handle.address() == 0L) null else Separator(handle)

        // No MethodBinds emitted yet.
    }
}
