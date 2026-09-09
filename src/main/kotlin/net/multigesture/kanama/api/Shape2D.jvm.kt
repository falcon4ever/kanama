package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for Shape2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP Shape2D waits on: ptrcallWithTransform2DObjectTransform2DArgsRetPackedVector2List,
//   ptrcallWithTransform2DVector2ObjectTransform2DVector2ArgsRetPackedVector2List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns a list of contact point pairs where this shape touches another. If there are no
 * collisions, the returned list is empty. Otherwise, the returned list contains contact points
 * arranged in pairs, with entries alternating between points on the boundary of this shape and
 * points on the boundary of `with_shape`. A collision pair A, B can be used to calculate the
 * collision normal with `(B - A).normalized()`, and the collision depth with `(B - A).length()`.
 * This information is typically used to separate shapes, particularly in collision solvers. This
 * method needs the transformation matrix for this shape (`local_xform`), the shape to check
 * collisions with (`with_shape`), and the transformation matrix of that shape (`shape_xform`).
 *
 * Generated from Godot docs: Shape2D.collide_and_get_contacts
 */
fun Shape2D.collideAndGetContacts(localXform: Transform2D, withShape: Shape2D, shapeXform: Transform2D): List<Vector2> {
    checkOpen()
    return ObjectCalls.ptrcallWithTransform2DObjectTransform2DArgsRetPackedVector2List(collideAndGetContactsBind, handle, localXform, withShape.requireOpenHandle(), shapeXform)
}

/**
 * Returns a list of contact point pairs where this shape would touch another, if a given movement
 * was applied. If there would be no collisions, the returned list is empty. Otherwise, the
 * returned list contains contact points arranged in pairs, with entries alternating between points
 * on the boundary of this shape and points on the boundary of `with_shape`. A collision pair A, B
 * can be used to calculate the collision normal with `(B - A).normalized()`, and the collision
 * depth with `(B - A).length()`. This information is typically used to separate shapes,
 * particularly in collision solvers. This method needs the transformation matrix for this shape
 * (`local_xform`), the movement to test on this shape (`local_motion`), the shape to check
 * collisions with (`with_shape`), the transformation matrix of that shape (`shape_xform`), and the
 * movement to test onto the other object (`shape_motion`).
 *
 * Generated from Godot docs: Shape2D.collide_with_motion_and_get_contacts
 */
fun Shape2D.collideWithMotionAndGetContacts(localXform: Transform2D, localMotion: Vector2, withShape: Shape2D, shapeXform: Transform2D, shapeMotion: Vector2): List<Vector2> {
    checkOpen()
    return ObjectCalls.ptrcallWithTransform2DVector2ObjectTransform2DVector2ArgsRetPackedVector2List(collideWithMotionAndGetContactsBind, handle, localXform, localMotion, withShape.requireOpenHandle(), shapeXform, shapeMotion)
}

private const val COLLIDE_AND_GET_CONTACTS_HASH = 3056932662L
private val collideAndGetContactsBind by lazy {
    ObjectCalls.getMethodBind("Shape2D", "collide_and_get_contacts", COLLIDE_AND_GET_CONTACTS_HASH)
}

private const val COLLIDE_WITH_MOTION_AND_GET_CONTACTS_HASH = 3620351573L
private val collideWithMotionAndGetContactsBind by lazy {
    ObjectCalls.getMethodBind("Shape2D", "collide_with_motion_and_get_contacts", COLLIDE_WITH_MOTION_AND_GET_CONTACTS_HASH)
}
