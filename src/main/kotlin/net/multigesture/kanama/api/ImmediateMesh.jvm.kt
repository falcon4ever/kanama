package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Plane

// GENERATED desktop/Android companion for ImmediateMesh (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ImmediateMesh waits on: ptrcallWithPlaneArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * Set the tangent attribute that will be pushed with the next vertex. Note: Even though `tangent`
 * is a `Plane`, it does not directly represent the tangent plane. Its `Plane.x`, `Plane.y`, and
 * `Plane.z` represent the tangent vector and `Plane.d` should be either `-1` or `1`. See also
 * `Mesh.ARRAY_TANGENT`.
 *
 * Generated from Godot docs: ImmediateMesh.surface_set_tangent
 */
fun ImmediateMesh.surfaceSetTangent(tangent: Plane) {
    checkOpen()
    ObjectCalls.ptrcallWithPlaneArg(surfaceSetTangentBind, handle, tangent)
}

private const val SURFACE_SET_TANGENT_HASH = 3505987427L
private val surfaceSetTangentBind by lazy {
    ObjectCalls.getMethodBind("ImmediateMesh", "surface_set_tangent", SURFACE_SET_TANGENT_HASH)
}
