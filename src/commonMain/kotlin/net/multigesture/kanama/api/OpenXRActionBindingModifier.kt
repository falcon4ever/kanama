package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Binding modifier that applies on individual actions related to an interaction profile.
 *
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

        /** A new OpenXRActionBindingModifier, GDScript's `OpenXRActionBindingModifier.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): OpenXRActionBindingModifier =
            RefCounted.owned(OpenXRActionBindingModifier(GodotHandle(requireConstructed(ObjectCalls.constructObject("OpenXRActionBindingModifier"), "OpenXRActionBindingModifier"))))
    }
}
