package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*

// GENERATED iOS companion for PhysicsDirectSpaceState3D (scripts/generate_api_wrapper.py --write-tree, from
// IOS_EXTENSION_SECTIONS). DO NOT EDIT BY HAND. iOS-only sugar over the shared wrapper: it
// uses C-shim helpers desktop/Android do not have, so it cannot live in the shared file.

// intersect_ray returns a Godot Dictionary, decoded via the fixed-schema raycast C-shim
// (kanama_ios_godot_ptrcall_ret_raycast_dict). Empty map = no hit. "collider" is wrapped from the
// raw handle into a GodotObject so scripts can `hit["collider"] as? GodotObject`.
fun PhysicsDirectSpaceState3D.intersectRay(parameters: PhysicsRayQueryParameters3D?): Map<String, Any?> {
    val query = parameters ?: return emptyMap()
    val raw = ObjectCalls.ptrcallIntersectRay(intersectRayBind, handle, query.handle)
    if (raw.isEmpty()) return emptyMap()
    val result = raw.toMutableMap()
    (raw["collider"] as? MemorySegment)?.let { result["collider"] = GodotObject(it) }
    return result
}

private const val INTERSECT_RAY_HASH = 3957970750L
private val intersectRayBind by lazy {
    ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "intersect_ray", INTERSECT_RAY_HASH)
}
