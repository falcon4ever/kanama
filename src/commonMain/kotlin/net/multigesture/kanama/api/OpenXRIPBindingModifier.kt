package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRIPBindingModifier
 */
open class OpenXRIPBindingModifier(handle: GodotHandle) : OpenXRBindingModifier(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRIPBindingModifier? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRIPBindingModifier? =
            if (handle.address() == 0L) null else OpenXRIPBindingModifier(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
