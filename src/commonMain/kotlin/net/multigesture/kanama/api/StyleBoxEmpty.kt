package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * An empty `StyleBox` (does not display anything).
 *
 * Generated from Godot docs: StyleBoxEmpty
 */
class StyleBoxEmpty(handle: GodotHandle) : StyleBox(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StyleBoxEmpty? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): StyleBoxEmpty? =
            if (handle.address() == 0L) null else RefCounted.owned(StyleBoxEmpty(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): StyleBoxEmpty? =
            if (handle.address() == 0L) null else StyleBoxEmpty(GodotHandle(handle))

        /** A new StyleBoxEmpty, GDScript's `StyleBoxEmpty.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): StyleBoxEmpty =
            RefCounted.owned(StyleBoxEmpty(GodotHandle(requireConstructed(ObjectCalls.constructObject("StyleBoxEmpty"), "StyleBoxEmpty"))))
    }
}
