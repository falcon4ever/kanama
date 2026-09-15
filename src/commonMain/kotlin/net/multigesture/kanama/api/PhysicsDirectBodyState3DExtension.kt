package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Provides virtual methods that can be overridden to create custom `PhysicsDirectBodyState3D`
 * implementations.
 *
 * Generated from Godot docs: PhysicsDirectBodyState3DExtension
 */
class PhysicsDirectBodyState3DExtension(handle: GodotHandle) : PhysicsDirectBodyState3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsDirectBodyState3DExtension? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PhysicsDirectBodyState3DExtension? =
            if (handle.address() == 0L) null else PhysicsDirectBodyState3DExtension(GodotHandle(handle))

        // No MethodBinds emitted yet.
    }
}
