package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A popup with a panel background.
 *
 * Generated from Godot docs: PopupPanel
 */
class PopupPanel(handle: MemorySegment) : Popup(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PopupPanel? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PopupPanel? =
            if (handle.address() == 0L) null else PopupPanel(handle)

        // No MethodBinds emitted yet.
    }
}
