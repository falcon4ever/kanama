package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A vertical slider that goes from bottom (min) to top (max).
 *
 * Generated from Godot docs: VSlider
 */
class VSlider(handle: GodotHandle) : Slider(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VSlider? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VSlider? =
            if (handle.address() == 0L) null else VSlider(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
