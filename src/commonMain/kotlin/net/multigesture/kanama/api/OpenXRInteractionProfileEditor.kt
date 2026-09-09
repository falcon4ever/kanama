package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRInteractionProfileEditor
 */
class OpenXRInteractionProfileEditor(handle: MemorySegment) : OpenXRInteractionProfileEditorBase(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRInteractionProfileEditor? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRInteractionProfileEditor? =
            if (handle.address() == 0L) null else OpenXRInteractionProfileEditor(handle)

        // No MethodBinds emitted yet.
    }
}
