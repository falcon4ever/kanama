package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRBindingModifier
 */
open class OpenXRBindingModifier(handle: MemorySegment) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRBindingModifier? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRBindingModifier? =
            if (handle.address() == 0L) null else OpenXRBindingModifier(handle)

        // No MethodBinds emitted yet.
    }
}
