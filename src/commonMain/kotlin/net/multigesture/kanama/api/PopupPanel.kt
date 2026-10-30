package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

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

        internal fun wrap(handle: RawSegment): PopupPanel? =
            if (handle.address() == 0L) null else PopupPanel(GodotHandle(handle))

        /** A new PopupPanel, GDScript's `PopupPanel.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): PopupPanel =
            PopupPanel(GodotHandle(requireConstructed(ObjectCalls.constructObject("PopupPanel"), "PopupPanel")))
    }
}
