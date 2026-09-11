package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for PhysicsDirectSpaceState3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PhysicsDirectSpaceState3D waits on: ptrcallWithObjectAndIntArgRetVector3List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters3D` object,
 * against the space. The resulting array contains a list of points where the shape intersects
 * another. Like with `intersect_shape`, the number of returned results can be limited to save
 * processing time. Returned points are a list of pairs of contact points. For each pair the first
 * one is in the shape passed in `PhysicsShapeQueryParameters3D` object, second one is in the
 * collided shape from the physics space. Note: This method does not take into account the `motion`
 * property of the object.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState3D.collide_shape
 */
fun PhysicsDirectSpaceState3D.collideShape(parameters: PhysicsShapeQueryParameters3D, maxResults: Int = 32): List<Vector3> {
    return ObjectCalls.ptrcallWithObjectAndIntArgRetVector3List(collideShapeBind, handle, parameters.requireOpenHandle(), maxResults)
}

private const val COLLIDE_SHAPE_HASH = 3762137681L
private val collideShapeBind by lazy {
    ObjectCalls.getMethodBind("PhysicsDirectSpaceState3D", "collide_shape", COLLIDE_SHAPE_HASH)
}
