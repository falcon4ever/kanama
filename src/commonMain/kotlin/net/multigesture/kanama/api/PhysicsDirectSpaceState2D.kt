package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Provides direct access to a physics space in the `PhysicsServer2D`.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState2D
 */
open class PhysicsDirectSpaceState2D(handle: MemorySegment) : GodotObject(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsDirectSpaceState2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsDirectSpaceState2D? =
            if (handle.address() == 0L) null else PhysicsDirectSpaceState2D(handle)

        // No MethodBinds emitted yet.
    }
}
