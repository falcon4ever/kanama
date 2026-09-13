package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A tracked controller.
 *
 * Generated from Godot docs: XRControllerTracker
 */
class XRControllerTracker(handle: GodotHandle) : XRPositionalTracker(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRControllerTracker? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): XRControllerTracker? =
            if (handle.address() == 0L) null else XRControllerTracker(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
