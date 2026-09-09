package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * Provides virtual methods that can be overridden to create custom `PhysicsDirectBodyState2D`
 * implementations.
 *
 * Generated from Godot docs: PhysicsDirectBodyState2DExtension
 */
class PhysicsDirectBodyState2DExtension(handle: MemorySegment) : PhysicsDirectBodyState2D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsDirectBodyState2DExtension? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsDirectBodyState2DExtension? =
            if (handle.address() == 0L) null else PhysicsDirectBodyState2DExtension(handle)

        // No MethodBinds emitted yet.
    }
}
