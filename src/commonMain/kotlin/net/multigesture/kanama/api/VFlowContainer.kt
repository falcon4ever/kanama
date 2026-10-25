package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A container that arranges its child controls vertically and wraps them around at the borders.
 *
 * Generated from Godot docs: VFlowContainer
 */
class VFlowContainer(handle: GodotHandle) : FlowContainer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): VFlowContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): VFlowContainer? =
            if (handle.address() == 0L) null else VFlowContainer(GodotHandle(handle))

        /** A new VFlowContainer, GDScript's `VFlowContainer.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): VFlowContainer =
            VFlowContainer(GodotHandle(ObjectCalls.constructObject("VFlowContainer")))
    }
}
