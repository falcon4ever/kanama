package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A container that arranges its child controls horizontally.
 *
 * Generated from Godot docs: HBoxContainer
 */
open class HBoxContainer(handle: GodotHandle) : BoxContainer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): HBoxContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): HBoxContainer? =
            if (handle.address() == 0L) null else HBoxContainer(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
