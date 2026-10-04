package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRIPBindingModifier
 */
open class OpenXRIPBindingModifier(handle: GodotHandle) : OpenXRBindingModifier(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRIPBindingModifier? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRIPBindingModifier? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRIPBindingModifier(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRIPBindingModifier? =
            if (handle.address() == 0L) null else OpenXRIPBindingModifier(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
