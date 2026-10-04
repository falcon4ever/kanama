package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRBindingModifier
 */
open class OpenXRBindingModifier(handle: GodotHandle) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRBindingModifier? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRBindingModifier? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRBindingModifier(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRBindingModifier? =
            if (handle.address() == 0L) null else OpenXRBindingModifier(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
