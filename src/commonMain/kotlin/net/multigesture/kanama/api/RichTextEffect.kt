package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A custom effect for a `RichTextLabel`.
 *
 * Generated from Godot docs: RichTextEffect
 */
class RichTextEffect(handle: GodotHandle) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): RichTextEffect? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): RichTextEffect? =
            if (handle.address() == 0L) null else RefCounted.owned(RichTextEffect(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): RichTextEffect? =
            if (handle.address() == 0L) null else RichTextEffect(GodotHandle(handle))

        /** A new RichTextEffect, GDScript's `RichTextEffect.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): RichTextEffect =
            RefCounted.owned(RichTextEffect(GodotHandle(requireConstructed(ObjectCalls.constructObject("RichTextEffect"), "RichTextEffect"))))
    }
}
