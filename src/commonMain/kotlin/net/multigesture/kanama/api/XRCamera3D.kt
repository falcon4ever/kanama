package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * A camera node which automatically positions itself based on XR tracking data.
 *
 * Generated from Godot docs: XRCamera3D
 */
class XRCamera3D(handle: MemorySegment) : Camera3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): XRCamera3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): XRCamera3D? =
            if (handle.address() == 0L) null else XRCamera3D(handle)

        // No MethodBinds emitted yet.
    }
}
