package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for CollisionPolygon2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP CollisionPolygon2D waits on: ptrcallWithPackedVector2ListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * The polygon's list of vertices. Each point will be connected to the next, and the final point
 * will be connected to the first. Note: The returned vertices are in the local coordinate space of
 * the given `CollisionPolygon2D`.
 *
 * Generated from Godot docs: CollisionPolygon2D.set_polygon
 */
fun CollisionPolygon2D.setPolygon(polygon: List<Vector2>) {
    ObjectCalls.ptrcallWithPackedVector2ListArg(setPolygonBind, handle, polygon)
}

private const val SET_POLYGON_HASH = 1509147220L
private val setPolygonBind by lazy {
    ObjectCalls.getMethodBind("CollisionPolygon2D", "set_polygon", SET_POLYGON_HASH)
}
