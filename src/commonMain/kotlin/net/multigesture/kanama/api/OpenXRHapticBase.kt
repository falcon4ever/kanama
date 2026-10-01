package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Generated from Godot docs: OpenXRHapticBase
 */
open class OpenXRHapticBase(handle: GodotHandle) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): OpenXRHapticBase? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): OpenXRHapticBase? =
            if (handle.address() == 0L) null else OpenXRHapticBase(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
