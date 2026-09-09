package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for CollisionPolygon3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP CollisionPolygon3D waits on: ptrcallWithPackedVector2ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Array of vertices which define the 2D polygon in the local XY plane.
 *
 * Generated from Godot docs: CollisionPolygon3D.set_polygon
 */
fun CollisionPolygon3D.setPolygon(polygon: List<Vector2>) {
    ObjectCalls.ptrcallWithPackedVector2ListArg(setPolygonBind, handle, polygon)
}

private const val SET_POLYGON_HASH = 1509147220L
private val setPolygonBind by lazy {
    ObjectCalls.getMethodBind("CollisionPolygon3D", "set_polygon", SET_POLYGON_HASH)
}
