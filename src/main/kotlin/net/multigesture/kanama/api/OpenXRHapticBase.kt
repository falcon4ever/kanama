package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Base resource for OpenXR haptic feedback definitions.
 *
 * Generated from Godot docs: OpenXRHapticBase
 */
open class OpenXRHapticBase(handle: MemorySegment) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRHapticBase? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRHapticBase? =
            if (handle.address() == 0L) null else OpenXRHapticBase(handle)

        // No MethodBinds emitted yet.
    }
}
