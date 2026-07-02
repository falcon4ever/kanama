package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A 3D physics body that can't be moved by external forces. When moved manually, it affects other
 * bodies in its path.
 *
 * Generated from Godot docs: AnimatableBody3D
 */
class AnimatableBody3D(handle: MemorySegment) : StaticBody3D(handle) {
    var syncToPhysics: Boolean
        @JvmName("syncToPhysicsProperty")
        get() = isSyncToPhysicsEnabled()
        @JvmName("setSyncToPhysicsProperty")
        set(value) = setSyncToPhysics(value)

    fun setSyncToPhysics(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSyncToPhysicsBind, handle, enable)
    }

    fun isSyncToPhysicsEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSyncToPhysicsEnabledBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AnimatableBody3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AnimatableBody3D? =
            if (handle.address() == 0L) null else AnimatableBody3D(handle)

        private const val SET_SYNC_TO_PHYSICS_HASH = 2586408642L
        private val setSyncToPhysicsBind by lazy {
            ObjectCalls.getMethodBind("AnimatableBody3D", "set_sync_to_physics", SET_SYNC_TO_PHYSICS_HASH)
        }

        private const val IS_SYNC_TO_PHYSICS_ENABLED_HASH = 36873697L
        private val isSyncToPhysicsEnabledBind by lazy {
            ObjectCalls.getMethodBind("AnimatableBody3D", "is_sync_to_physics_enabled", IS_SYNC_TO_PHYSICS_ENABLED_HASH)
        }
    }
}
