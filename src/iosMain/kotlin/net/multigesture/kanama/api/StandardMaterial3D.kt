package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Generated from Godot docs: StandardMaterial3D
 */
class StandardMaterial3D(handle: GodotHandle) : BaseMaterial3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        // KANAMA-IOS-SUGAR: [glue] desktop-parity constructor sugar (the desktop wrapper's
        // MemorySegment constructor is internal, so shared game code uses create()).
        fun create(): StandardMaterial3D =
            StandardMaterial3D(GodotHandle(ObjectCalls.constructObject("StandardMaterial3D")))

        fun fromHandle(handle: GodotHandle): StandardMaterial3D? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): StandardMaterial3D? =
            if (handle.address() == 0L) null else StandardMaterial3D(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
