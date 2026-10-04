package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRActionBindingModifier
 */
open class OpenXRActionBindingModifier(handle: GodotHandle) : OpenXRBindingModifier(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRActionBindingModifier? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRActionBindingModifier? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRActionBindingModifier(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRActionBindingModifier? =
            if (handle.address() == 0L) null else OpenXRActionBindingModifier(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
