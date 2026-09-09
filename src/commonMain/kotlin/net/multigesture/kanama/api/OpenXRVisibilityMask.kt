package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRVisibilityMask
 */
class OpenXRVisibilityMask(handle: MemorySegment) : VisualInstance3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): OpenXRVisibilityMask? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): OpenXRVisibilityMask? =
            if (handle.address() == 0L) null else OpenXRVisibilityMask(handle)

        // No MethodBinds emitted yet.
    }
}
