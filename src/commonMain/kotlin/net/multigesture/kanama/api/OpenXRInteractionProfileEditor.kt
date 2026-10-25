package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Default OpenXR interaction profile editor.
 *
 * Generated from Godot docs: OpenXRInteractionProfileEditor
 */
class OpenXRInteractionProfileEditor(handle: GodotHandle) : OpenXRInteractionProfileEditorBase(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRInteractionProfileEditor? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRInteractionProfileEditor? =
            if (handle.address() == 0L) null else OpenXRInteractionProfileEditor(GodotHandle(handle))

        /** A new OpenXRInteractionProfileEditor, GDScript's `OpenXRInteractionProfileEditor.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): OpenXRInteractionProfileEditor =
            OpenXRInteractionProfileEditor(GodotHandle(requireConstructed(ObjectCalls.constructObject("OpenXRInteractionProfileEditor"), "OpenXRInteractionProfileEditor")))
    }
}
