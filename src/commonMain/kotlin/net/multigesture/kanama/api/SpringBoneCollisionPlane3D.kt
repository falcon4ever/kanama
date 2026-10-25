package net.multigesture.kanama.api

import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * An infinite plane collision that interacts with `SpringBoneSimulator3D`.
 *
 * Generated from Godot docs: SpringBoneCollisionPlane3D
 */
class SpringBoneCollisionPlane3D(handle: GodotHandle) : SpringBoneCollision3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SpringBoneCollisionPlane3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SpringBoneCollisionPlane3D? =
            if (handle.address() == 0L) null else SpringBoneCollisionPlane3D(GodotHandle(handle))

        /** A new SpringBoneCollisionPlane3D, GDScript's `SpringBoneCollisionPlane3D.new()`. Add it to the tree, or `queueFree()` it. */
        @JvmStatic
        fun create(): SpringBoneCollisionPlane3D =
            SpringBoneCollisionPlane3D(GodotHandle(ObjectCalls.constructObject("SpringBoneCollisionPlane3D")))
    }
}
