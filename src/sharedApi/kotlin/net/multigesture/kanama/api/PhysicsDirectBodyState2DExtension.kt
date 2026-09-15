package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * Provides virtual methods that can be overridden to create custom `PhysicsDirectBodyState2D`
 * implementations.
 *
 * Generated from Godot docs: PhysicsDirectBodyState2DExtension
 */
class PhysicsDirectBodyState2DExtension(handle: GodotHandle) : PhysicsDirectBodyState2D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsDirectBodyState2DExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PhysicsDirectBodyState2DExtension? =
            if (handle.address() == 0L) null else PhysicsDirectBodyState2DExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
