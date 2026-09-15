package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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

        internal fun wrap(handle: RawSegment): Panel? =
            if (handle.address() == 0L) null else Panel(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
