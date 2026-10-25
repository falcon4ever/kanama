package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Draws a stereo correct visibility mask.
 *
 * Generated from Godot docs: OpenXRVisibilityMask
 */
class OpenXRVisibilityMask(handle: GodotHandle) : VisualInstance3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRVisibilityMask? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRVisibilityMask? =
            if (handle.address() == 0L) null else OpenXRVisibilityMask(GodotHandle(handle))

        /** A new OpenXRVisibilityMask, GDScript's `OpenXRVisibilityMask.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): OpenXRVisibilityMask =
            OpenXRVisibilityMask(GodotHandle(ObjectCalls.constructObject("OpenXRVisibilityMask")))
    }
}
