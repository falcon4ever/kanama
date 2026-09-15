package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRExtensionWrapperExtension
 */
class OpenXRExtensionWrapperExtension(handle: GodotHandle) : OpenXRExtensionWrapper(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRExtensionWrapperExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRExtensionWrapperExtension? =
            if (handle.address() == 0L) null else OpenXRExtensionWrapperExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
