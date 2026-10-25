package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A container that arranges its child controls horizontally.
 *
 * Generated from Godot docs: HBoxContainer
 */
open class HBoxContainer(handle: GodotHandle) : BoxContainer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): HBoxContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): HBoxContainer? =
            if (handle.address() == 0L) null else HBoxContainer(GodotHandle(handle))

        /** A new HBoxContainer, GDScript's `HBoxContainer.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): HBoxContainer =
            HBoxContainer(GodotHandle(requireConstructed(ObjectCalls.constructObject("HBoxContainer"), "HBoxContainer")))
    }
}
