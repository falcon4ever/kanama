package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for PhysicsDirectSpaceState2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PhysicsDirectSpaceState2D waits on: ptrcallWithObjectAndIntArgRetVector2List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters2D` object,
 * against the space. The resulting array contains a list of points where the shape intersects
 * another. Like with `intersect_shape`, the number of returned results can be limited to save
 * processing time. Returned points are a list of pairs of contact points. For each pair the first
 * one is in the shape passed in `PhysicsShapeQueryParameters2D` object, second one is in the
 * collided shape from the physics space.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState2D.collide_shape
 */
fun PhysicsDirectSpaceState2D.collideShape(parameters: PhysicsShapeQueryParameters2D, maxResults: Int = 32): List<Vector2> {
    return ObjectCalls.ptrcallWithObjectAndIntArgRetVector2List(collideShapeBind, handle, parameters.requireOpenHandle(), maxResults)
}

private const val COLLIDE_SHAPE_HASH = 2488867228L
private val collideShapeBind by lazy {
    ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "collide_shape", COLLIDE_SHAPE_HASH)
}
