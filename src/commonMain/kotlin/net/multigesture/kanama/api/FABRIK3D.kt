package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Position based forward and backward reaching inverse kinematics solver.
 *
 * Generated from Godot docs: FABRIK3D
 */
class FABRIK3D(handle: GodotHandle) : IterateIK3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): FABRIK3D? =
            wrap(handle.segment)

        internal fun wrap(handle: MemorySegment): FABRIK3D? =
            if (handle.address() == 0L) null else FABRIK3D(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
