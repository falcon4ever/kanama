package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRInteractionProfileEditor
 */
class OpenXRInteractionProfileEditor(handle: GodotHandle) : OpenXRInteractionProfileEditorBase(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRInteractionProfileEditor? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): OpenXRInteractionProfileEditor? =
            if (handle.address() == 0L) null else OpenXRInteractionProfileEditor(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
