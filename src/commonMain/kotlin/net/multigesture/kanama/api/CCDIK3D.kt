package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Rotation based cyclic coordinate descent inverse kinematics solver.
 *
 * Generated from Godot docs: CCDIK3D
 */
class CCDIK3D(handle: GodotHandle) : IterateIK3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CCDIK3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CCDIK3D? =
            if (handle.address() == 0L) null else CCDIK3D(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
