package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRBindingModifier
 */
open class OpenXRBindingModifier(handle: GodotHandle) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRBindingModifier? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): OpenXRBindingModifier? =
            if (handle.address() == 0L) null else OpenXRBindingModifier(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
