package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * A horizontal slider that goes from left (min) to right (max).
 *
 * Generated from Godot docs: HSlider
 */
class HSlider(handle: GodotHandle) : Slider(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): HSlider? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): HSlider? =
            if (handle.address() == 0L) null else HSlider(GodotHandle(handle))

        /** A new HSlider, GDScript's `HSlider.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): HSlider =
            HSlider(GodotHandle(requireConstructed(ObjectCalls.constructObject("HSlider"), "HSlider")))
    }
}
