package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Plane

// GENERATED desktop/Android companion for XRAnchor3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP XRAnchor3D waits on: ptrcallNoArgsRetPlane
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns a plane aligned with our anchor; handy for intersection testing.
 *
 * Generated from Godot docs: XRAnchor3D.get_plane
 */
fun XRAnchor3D.getPlane(): Plane {
    return ObjectCalls.ptrcallNoArgsRetPlane(getPlaneBind, handle)
}

private const val GET_PLANE_HASH = 2753500971L
private val getPlaneBind by lazy {
    ObjectCalls.getMethodBind("XRAnchor3D", "get_plane", GET_PLANE_HASH)
}
