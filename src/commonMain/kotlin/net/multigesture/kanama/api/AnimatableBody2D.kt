package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * A 2D physics body that can't be moved by external forces. When moved manually, it affects other
 * bodies in its path.
 *
 * Generated from Godot docs: AnimatableBody2D
 */
class AnimatableBody2D(handle: GodotHandle) : StaticBody2D(handle) {
    var syncToPhysics: Boolean
        @JvmName("syncToPhysicsProperty")
        get() = isSyncToPhysicsEnabled()
        @JvmName("setSyncToPhysicsProperty")
        set(value) = setSyncToPhysics(value)

    /**
     * If `true`, the body's movement will be synchronized to the physics frame. This is useful when
     * animating movement via `AnimationPlayer`, for example on moving platforms. Do not use together
     * with `PhysicsBody2D.move_and_collide`.
     *
     * Generated from Godot docs: AnimatableBody2D.set_sync_to_physics
     */
    fun setSyncToPhysics(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSyncToPhysicsBind, segment, enable)
    }

    /**
     * If `true`, the body's movement will be synchronized to the physics frame. This is useful when
     * animating movement via `AnimationPlayer`, for example on moving platforms. Do not use together
     * with `PhysicsBody2D.move_and_collide`.
     *
     * Generated from Godot docs: AnimatableBody2D.is_sync_to_physics_enabled
     */
    fun isSyncToPhysicsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSyncToPhysicsEnabledBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): AnimatableBody2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): AnimatableBody2D? =
            if (handle.address() == 0L) null else AnimatableBody2D(GodotHandle(handle))

        private const val SET_SYNC_TO_PHYSICS_HASH = 2586408642L
        private val setSyncToPhysicsBind by lazy {
            ObjectCalls.getMethodBind("AnimatableBody2D", "set_sync_to_physics", SET_SYNC_TO_PHYSICS_HASH)
        }

        private const val IS_SYNC_TO_PHYSICS_ENABLED_HASH = 36873697L
        private val isSyncToPhysicsEnabledBind by lazy {
            ObjectCalls.getMethodBind("AnimatableBody2D", "is_sync_to_physics_enabled", IS_SYNC_TO_PHYSICS_ENABLED_HASH)
        }
    }
}
