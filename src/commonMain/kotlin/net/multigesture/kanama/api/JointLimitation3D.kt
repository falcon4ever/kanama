package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A base class of the limitation that interacts with `ChainIK3D`.
 *
 * Generated from Godot docs: JointLimitation3D
 */
open class JointLimitation3D(handle: GodotHandle) : Resource(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): JointLimitation3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): JointLimitation3D? =
            if (handle.address() == 0L) null else JointLimitation3D(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
