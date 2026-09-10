package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for ConvexPolygonShape3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP ConvexPolygonShape3D waits on: ptrcallWithPackedVector3ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The list of 3D points forming the convex polygon shape.
 *
 * Generated from Godot docs: ConvexPolygonShape3D.set_points
 */
fun ConvexPolygonShape3D.setPoints(points: List<Vector3>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector3ListArg(setPointsBind, handle, points)
}

private const val SET_POINTS_HASH = 334873810L
private val setPointsBind by lazy {
    ObjectCalls.getMethodBind("ConvexPolygonShape3D", "set_points", SET_POINTS_HASH)
}
