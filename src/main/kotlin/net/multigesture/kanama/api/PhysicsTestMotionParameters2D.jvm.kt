package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for PhysicsTestMotionParameters2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PhysicsTestMotionParameters2D waits on: ptrcallWithRIDListArg,
//   ptrcallWithTypedIntListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Optional array of body `RID` to exclude from collision. Use `CollisionObject2D.get_rid` to get
 * the `RID` associated with a `CollisionObject2D`-derived node.
 *
 * Generated from Godot docs: PhysicsTestMotionParameters2D.set_exclude_bodies
 */
fun PhysicsTestMotionParameters2D.setExcludeBodies(excludeList: List<RID>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDListArg(setExcludeBodiesBind, handle, excludeList)
}

/**
 * Optional array of object unique instance ID to exclude from collision. See
 * `Object.get_instance_id`.
 *
 * Generated from Godot docs: PhysicsTestMotionParameters2D.set_exclude_objects
 */
fun PhysicsTestMotionParameters2D.setExcludeObjects(excludeList: List<Long>) {
    checkOpen()
    ObjectCalls.ptrcallWithTypedIntListArg(setExcludeObjectsBind, handle, excludeList)
}

private const val SET_EXCLUDE_BODIES_HASH = 381264803L
private val setExcludeBodiesBind by lazy {
    ObjectCalls.getMethodBind("PhysicsTestMotionParameters2D", "set_exclude_bodies", SET_EXCLUDE_BODIES_HASH)
}

private const val SET_EXCLUDE_OBJECTS_HASH = 381264803L
private val setExcludeObjectsBind by lazy {
    ObjectCalls.getMethodBind("PhysicsTestMotionParameters2D", "set_exclude_objects", SET_EXCLUDE_OBJECTS_HASH)
}
