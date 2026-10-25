package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A horizontal line used for separating other controls.
 *
 * Generated from Godot docs: HSeparator
 */
class HSeparator(handle: GodotHandle) : Separator(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): HSeparator? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): HSeparator? =
            if (handle.address() == 0L) null else HSeparator(GodotHandle(handle))

        /** A new HSeparator, GDScript's `HSeparator.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): HSeparator =
            HSeparator(GodotHandle(ObjectCalls.constructObject("HSeparator")))
    }
}
