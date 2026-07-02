package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRIPBindingModifier
 */
open class OpenXRIPBindingModifier(handle: MemorySegment) : OpenXRBindingModifier(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRIPBindingModifier? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRIPBindingModifier? =
            if (handle.address() == 0L) null else OpenXRIPBindingModifier(handle)

        // No MethodBinds emitted yet.
    }
}
