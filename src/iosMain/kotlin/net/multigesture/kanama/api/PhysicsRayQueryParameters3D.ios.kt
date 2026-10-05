package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.binding.runtime.*

// GENERATED iOS companion for PhysicsRayQueryParameters3D (scripts/generate_api_wrapper.py --write-tree, from
// IOS_EXTENSION_SECTIONS). DO NOT EDIT BY HAND. iOS-only sugar over the shared wrapper: it
// uses C-shim helpers desktop/Android do not have, so it cannot live in the shared file.

// Build a ray query: instantiate and set the scalar/Vector3 properties + the exclude RID-list
// (marshalled through the Array[RID] C-shim so intersect_ray skips the caster's own collider).
fun PhysicsRayQueryParameters3D.Companion.create(
    from: Vector3,
    to: Vector3,
    collisionMask: Long = 4294967295L,
    exclude: List<RID> = emptyList(),
): PhysicsRayQueryParameters3D {
    val query = RefCounted.owned(PhysicsRayQueryParameters3D(GodotHandle(ObjectCalls.constructObject("PhysicsRayQueryParameters3D"))))
    query.from = from
    query.to = to
    query.collisionMask = collisionMask
    if (exclude.isNotEmpty()) query.setExclude(exclude)
    return query
}
