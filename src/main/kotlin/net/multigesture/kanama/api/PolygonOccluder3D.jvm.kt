package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for PolygonOccluder3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PolygonOccluder3D waits on: ptrcallWithPackedVector2ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The polygon to use for occlusion culling. The polygon can be convex or concave, but it should
 * have as few points as possible to maximize performance. The polygon must not have intersecting
 * lines. Otherwise, triangulation will fail (with an error message printed).
 *
 * Generated from Godot docs: PolygonOccluder3D.set_polygon
 */
fun PolygonOccluder3D.setPolygon(polygon: List<Vector2>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListArg(setPolygonBind, handle, polygon)
}

private const val SET_POLYGON_HASH = 1509147220L
private val setPolygonBind by lazy {
    ObjectCalls.getMethodBind("PolygonOccluder3D", "set_polygon", SET_POLYGON_HASH)
}
