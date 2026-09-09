package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Jacobian transpose based inverse kinematics solver.
 *
 * Generated from Godot docs: JacobianIK3D
 */
class JacobianIK3D(handle: MemorySegment) : IterateIK3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): JacobianIK3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): JacobianIK3D? =
            if (handle.address() == 0L) null else JacobianIK3D(handle)

        // No MethodBinds emitted yet.
    }
}
