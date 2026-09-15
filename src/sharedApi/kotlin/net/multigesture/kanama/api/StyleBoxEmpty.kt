package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * An empty `StyleBox` (does not display anything).
 *
 * Generated from Godot docs: StyleBoxEmpty
 */
class StyleBoxEmpty(handle: GodotHandle) : StyleBox(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StyleBoxEmpty? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): StyleBoxEmpty? =
            if (handle.address() == 0L) null else StyleBoxEmpty(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
