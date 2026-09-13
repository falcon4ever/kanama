package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A GUI control that displays a `StyleBox`.
 *
 * Generated from Godot docs: Panel
 */
class Panel(handle: GodotHandle) : Control(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): Panel? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): Panel? =
            if (handle.address() == 0L) null else Panel(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
