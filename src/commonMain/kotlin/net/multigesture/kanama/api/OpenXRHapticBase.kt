package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRHapticBase
 */
open class OpenXRHapticBase(handle: GodotHandle) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRHapticBase? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): OpenXRHapticBase? =
            if (handle.address() == 0L) null else OpenXRHapticBase(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
