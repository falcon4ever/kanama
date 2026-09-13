package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A popup with a panel background.
 *
 * Generated from Godot docs: PopupPanel
 */
class PopupPanel(handle: GodotHandle) : Popup(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PopupPanel? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): PopupPanel? =
            if (handle.address() == 0L) null else PopupPanel(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
