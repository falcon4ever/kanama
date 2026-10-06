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
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): OpenXRHapticBase? =
            if (handle.address() == 0L) null else RefCounted.owned(OpenXRHapticBase(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): OpenXRHapticBase? =
            if (handle.address() == 0L) null else OpenXRHapticBase(GodotHandle(handle))
    }
}
