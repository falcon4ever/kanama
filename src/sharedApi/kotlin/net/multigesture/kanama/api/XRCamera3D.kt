package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * A camera node which automatically positions itself based on XR tracking data.
 *
 * Generated from Godot docs: XRCamera3D
 */
class XRCamera3D(handle: GodotHandle) : Camera3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): XRCamera3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): XRCamera3D? =
            if (handle.address() == 0L) null else XRCamera3D(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
