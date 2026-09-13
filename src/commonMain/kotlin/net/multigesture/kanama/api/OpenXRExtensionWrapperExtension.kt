package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: OpenXRExtensionWrapperExtension
 */
class OpenXRExtensionWrapperExtension(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRExtensionWrapperExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): OpenXRExtensionWrapperExtension? =
            if (handle.address() == 0L) null else OpenXRExtensionWrapperExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
