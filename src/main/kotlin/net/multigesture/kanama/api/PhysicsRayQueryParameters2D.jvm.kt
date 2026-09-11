package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for PhysicsRayQueryParameters2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PhysicsRayQueryParameters2D waits on: ptrcallWithRIDListArg,
//   ptrcallWithTwoVector2UInt32RIDListArgsRetObject
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns a new, pre-configured `PhysicsRayQueryParameters2D` object. Use it to quickly create
 * query parameters using the most common options.
 *
 * Generated from Godot docs: PhysicsRayQueryParameters2D.create
 */
fun PhysicsRayQueryParameters2D.Companion.create(from: Vector2, to: Vector2, collisionMask: Long = 4294967295L, exclude: List<RID>): PhysicsRayQueryParameters2D? {
    return PhysicsRayQueryParameters2D.wrap(ObjectCalls.ptrcallWithTwoVector2UInt32RIDListArgsRetObject(createBind, MemorySegment.NULL, from, to, collisionMask, exclude))
}

/**
 * The list of object `RID`s that will be excluded from collisions. Use `CollisionObject2D.get_rid`
 * to get the `RID` associated with a `CollisionObject2D`-derived node. Note: The returned array is
 * copied and any changes to it will not update the original property value. To update the value
 * you need to modify the returned array, and then assign it to the property again.
 *
 * Generated from Godot docs: PhysicsRayQueryParameters2D.set_exclude
 */
fun PhysicsRayQueryParameters2D.setExclude(exclude: List<RID>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDListArg(setExcludeBind, handle, exclude)
}

private const val CREATE_HASH = 3196569324L
private val createBind by lazy {
    ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "create", CREATE_HASH)
}

private const val SET_EXCLUDE_HASH = 381264803L
private val setExcludeBind by lazy {
    ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "set_exclude", SET_EXCLUDE_HASH)
}
