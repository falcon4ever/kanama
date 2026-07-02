package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Generated from Godot docs: OpenXRActionBindingModifier
 */
open class OpenXRActionBindingModifier(handle: MemorySegment) : OpenXRBindingModifier(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRActionBindingModifier? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRActionBindingModifier? =
            if (handle.address() == 0L) null else OpenXRActionBindingModifier(handle)

        // No MethodBinds emitted yet.
    }
}
