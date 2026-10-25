package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A container that keeps a margin around its child controls.
 *
 * Generated from Godot docs: MarginContainer
 */
open class MarginContainer(handle: GodotHandle) : Container(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): MarginContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): MarginContainer? =
            if (handle.address() == 0L) null else MarginContainer(GodotHandle(handle))

        /** A new MarginContainer, GDScript's `MarginContainer.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): MarginContainer =
            MarginContainer(GodotHandle(requireConstructed(ObjectCalls.constructObject("MarginContainer"), "MarginContainer")))
    }
}
