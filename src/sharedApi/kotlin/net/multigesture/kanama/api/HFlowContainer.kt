package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A container that arranges its child controls horizontally and wraps them around at the borders.
 *
 * Generated from Godot docs: HFlowContainer
 */
class HFlowContainer(handle: GodotHandle) : FlowContainer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): HFlowContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): HFlowContainer? =
            if (handle.address() == 0L) null else HFlowContainer(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
