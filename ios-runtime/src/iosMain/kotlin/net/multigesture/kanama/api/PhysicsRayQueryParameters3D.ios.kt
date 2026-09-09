package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3
import net.multigesture.kanama.binding.runtime.*

// GENERATED iOS companion for PhysicsRayQueryParameters3D (scripts/generate_api_wrapper.py --write-tree, from
// IOS_EXTENSION_SECTIONS). DO NOT EDIT BY HAND. iOS-only sugar over the shared wrapper: it
// uses C-shim helpers desktop/Android do not have, so it cannot live in the shared file.

// The RID list excluded from collisions (e.g. the caster's own body). Marshalled to a Godot
// Array[RID] by the C-shim. set_exclude takes an Array[RID] arg the generator otherwise skips.
fun PhysicsRayQueryParameters3D.setExclude(exclude: List<RID>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDListArg(setExcludeBind, handle, exclude)
}

// Build a ray query: instantiate and set the scalar/Vector3 properties + the exclude RID-list
// (marshalled through the Array[RID] C-shim so intersect_ray skips the caster's own collider).
fun PhysicsRayQueryParameters3D.Companion.create(
    from: Vector3,
    to: Vector3,
    collisionMask: Long = 4294967295L,
    exclude: List<RID> = emptyList(),
): PhysicsRayQueryParameters3D {
    val query = PhysicsRayQueryParameters3D(ObjectCalls.constructObject("PhysicsRayQueryParameters3D"))
    query.from = from
    query.to = to
    query.collisionMask = collisionMask
    if (exclude.isNotEmpty()) query.setExclude(exclude)
    return query
}

private const val SET_EXCLUDE_HASH = 381264803L
private val setExcludeBind by lazy {
    ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "set_exclude", SET_EXCLUDE_HASH)
}
