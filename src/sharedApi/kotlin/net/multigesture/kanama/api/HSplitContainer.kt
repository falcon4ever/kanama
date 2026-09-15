package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A container that splits two child controls horizontally and provides a grabber for adjusting the
 * split ratio.
 *
 * Generated from Godot docs: HSplitContainer
 */
class HSplitContainer(handle: GodotHandle) : SplitContainer(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): HSplitContainer? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): HSplitContainer? =
            if (handle.address() == 0L) null else HSplitContainer(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
