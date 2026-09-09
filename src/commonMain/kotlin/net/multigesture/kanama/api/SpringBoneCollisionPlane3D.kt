package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

/**
 * An infinite plane collision that interacts with `SpringBoneSimulator3D`.
 *
 * Generated from Godot docs: SpringBoneCollisionPlane3D
 */
class SpringBoneCollisionPlane3D(handle: MemorySegment) : SpringBoneCollision3D(handle) {
    // No conservative instance methods emitted yet.

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): SpringBoneCollisionPlane3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): SpringBoneCollisionPlane3D? =
            if (handle.address() == 0L) null else SpringBoneCollisionPlane3D(handle)

        // No MethodBinds emitted yet.
    }
}
