package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for PhysicsDirectSpaceState2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PhysicsDirectSpaceState2D waits on: ptrcallWithObjectAndIntArgRetDictionaryList,
//   ptrcallWithObjectArgRetDictionary
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Checks whether a point is inside any solid shape. Position and other parameters are defined
 * through `PhysicsPointQueryParameters2D`. The shapes the point is inside of are returned in an
 * array containing dictionaries with the following fields: `collider`: The colliding object.
 * `collider_id`: The colliding object's ID. `rid`: The intersecting object's `RID`. `shape`: The
 * shape index of the colliding shape. The number of intersections can be limited with the
 * `max_results` parameter, to reduce the processing time. Note: `ConcavePolygonShape2D`s and
 * `CollisionPolygon2D`s in `Segments` build mode are not solid shapes. Therefore, they will not be
 * detected.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState2D.intersect_point
 */
fun PhysicsDirectSpaceState2D.intersectPoint(parameters: PhysicsPointQueryParameters2D, maxResults: Int = 32): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallWithObjectAndIntArgRetDictionaryList(intersectPointBind, handle, parameters.requireOpenHandle(), maxResults)
}

/**
 * Intersects a ray in a given space. Ray position and other parameters are defined through
 * `PhysicsRayQueryParameters2D`. The returned object is a dictionary with the following fields:
 * `collider`: The colliding object. `collider_id`: The colliding object's ID. `normal`: The
 * object's surface normal at the intersection point, or `Vector2(0, 0)` if the ray starts inside
 * the shape and `PhysicsRayQueryParameters2D.hit_from_inside` is `true`. `position`: The
 * intersection point. `rid`: The intersecting object's `RID`. `shape`: The shape index of the
 * colliding shape. If the ray did not intersect anything, then an empty dictionary is returned
 * instead.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState2D.intersect_ray
 */
fun PhysicsDirectSpaceState2D.intersectRay(parameters: PhysicsRayQueryParameters2D): Map<String, Any?> {
    return ObjectCalls.ptrcallWithObjectArgRetDictionary(intersectRayBind, handle, parameters.requireOpenHandle())
}

/**
 * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters2D` object,
 * against the space. The intersected shapes are returned in an array containing dictionaries with
 * the following fields: `collider`: The colliding object. `collider_id`: The colliding object's
 * ID. `rid`: The intersecting object's `RID`. `shape`: The shape index of the colliding shape. The
 * number of intersections can be limited with the `max_results` parameter, to reduce the
 * processing time.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState2D.intersect_shape
 */
fun PhysicsDirectSpaceState2D.intersectShape(parameters: PhysicsShapeQueryParameters2D, maxResults: Int = 32): List<Map<String, Any?>> {
    return ObjectCalls.ptrcallWithObjectAndIntArgRetDictionaryList(intersectShapeBind, handle, parameters.requireOpenHandle(), maxResults)
}

/**
 * Checks the intersections of a shape, given through a `PhysicsShapeQueryParameters2D` object,
 * against the space. If it collides with more than one shape, the nearest one is selected. The
 * returned object is a dictionary containing the following fields: `collider_id`: The colliding
 * object's ID. `linear_velocity`: The colliding object's velocity `Vector2`. If the object is an
 * `Area2D`, the result is `(0, 0)`. `normal`: The collision normal of the query shape at the
 * intersection point, pointing away from the intersecting object. `point`: The intersection point.
 * `rid`: The intersecting object's `RID`. `shape`: The shape index of the colliding shape. If the
 * shape did not intersect anything, then an empty dictionary is returned instead.
 *
 * Generated from Godot docs: PhysicsDirectSpaceState2D.get_rest_info
 */
fun PhysicsDirectSpaceState2D.getRestInfo(parameters: PhysicsShapeQueryParameters2D): Map<String, Any?> {
    return ObjectCalls.ptrcallWithObjectArgRetDictionary(getRestInfoBind, handle, parameters.requireOpenHandle())
}

private const val INTERSECT_POINT_HASH = 2118456068L
private val intersectPointBind by lazy {
    ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "intersect_point", INTERSECT_POINT_HASH)
}

private const val INTERSECT_RAY_HASH = 1590275562L
private val intersectRayBind by lazy {
    ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "intersect_ray", INTERSECT_RAY_HASH)
}

private const val INTERSECT_SHAPE_HASH = 2488867228L
private val intersectShapeBind by lazy {
    ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "intersect_shape", INTERSECT_SHAPE_HASH)
}

private const val GET_REST_INFO_HASH = 2803666496L
private val getRestInfoBind by lazy {
    ObjectCalls.getMethodBind("PhysicsDirectSpaceState2D", "get_rest_info", GET_REST_INFO_HASH)
}
