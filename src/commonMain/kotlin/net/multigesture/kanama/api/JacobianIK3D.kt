package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireConstructed

/**
 * Jacobian transpose based inverse kinematics solver.
 *
 * Generated from Godot docs: JacobianIK3D
 */
class JacobianIK3D(handle: GodotHandle) : IterateIK3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): JacobianIK3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): JacobianIK3D? =
            if (handle.address() == 0L) null else JacobianIK3D(GodotHandle(handle))

        /** A new JacobianIK3D, GDScript's `JacobianIK3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): JacobianIK3D =
            JacobianIK3D(GodotHandle(requireConstructed(ObjectCalls.constructObject("JacobianIK3D"), "JacobianIK3D")))
    }
}
