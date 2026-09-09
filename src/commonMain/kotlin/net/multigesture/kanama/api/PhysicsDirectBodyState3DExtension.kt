package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Provides virtual methods that can be overridden to create custom `PhysicsDirectBodyState3D`
 * implementations.
 *
 * Generated from Godot docs: PhysicsDirectBodyState3DExtension
 */
class PhysicsDirectBodyState3DExtension(handle: MemorySegment) : PhysicsDirectBodyState3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsDirectBodyState3DExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsDirectBodyState3DExtension? =
            if (handle.address() == 0L) null else PhysicsDirectBodyState3DExtension(handle)

        // No MethodBinds emitted yet.
    }
}
