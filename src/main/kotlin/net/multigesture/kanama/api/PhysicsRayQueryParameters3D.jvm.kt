package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for PhysicsRayQueryParameters3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PhysicsRayQueryParameters3D waits on: ptrcallWithRIDListArg,
//   ptrcallWithTwoVector3UInt32RIDListArgsRetObject
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns a new, pre-configured `PhysicsRayQueryParameters3D` object. Use it to quickly create
 * query parameters using the most common options.
 *
 * Generated from Godot docs: PhysicsRayQueryParameters3D.create
 */
fun PhysicsRayQueryParameters3D.Companion.create(from: Vector3, to: Vector3, collisionMask: Long = 4294967295L, exclude: List<RID>): PhysicsRayQueryParameters3D? {
    return PhysicsRayQueryParameters3D.wrap(ObjectCalls.ptrcallWithTwoVector3UInt32RIDListArgsRetObject(createBind, MemorySegment.NULL, from, to, collisionMask, exclude))
}

/**
 * The list of object `RID`s that will be excluded from collisions. Use `CollisionObject3D.get_rid`
 * to get the `RID` associated with a `CollisionObject3D`-derived node. Note: The returned array is
 * copied and any changes to it will not update the original property value. To update the value
 * you need to modify the returned array, and then assign it to the property again.
 *
 * Generated from Godot docs: PhysicsRayQueryParameters3D.set_exclude
 */
fun PhysicsRayQueryParameters3D.setExclude(exclude: List<RID>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDListArg(setExcludeBind, handle, exclude)
}

private const val CREATE_HASH = 3110599579L
private val createBind by lazy {
    ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "create", CREATE_HASH)
}

private const val SET_EXCLUDE_HASH = 381264803L
private val setExcludeBind by lazy {
    ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "set_exclude", SET_EXCLUDE_HASH)
}
