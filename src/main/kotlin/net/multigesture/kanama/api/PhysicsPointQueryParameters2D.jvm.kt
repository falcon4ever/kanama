package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID

// GENERATED desktop/Android companion for PhysicsPointQueryParameters2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PhysicsPointQueryParameters2D waits on: ptrcallNoArgsRetRIDList,
//   ptrcallWithRIDListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * The list of object `RID`s that will be excluded from collisions. Use `CollisionObject2D.get_rid`
 * to get the `RID` associated with a `CollisionObject2D`-derived node. Note: The returned array is
 * copied and any changes to it will not update the original property value. To update the value
 * you need to modify the returned array, and then assign it to the property again.
 *
 * Generated from Godot docs: PhysicsPointQueryParameters2D.set_exclude
 */
fun PhysicsPointQueryParameters2D.setExclude(exclude: List<RID>) {
    checkOpen()
    ObjectCalls.ptrcallWithRIDListArg(setExcludeBind, handle, exclude)
}

/**
 * The list of object `RID`s that will be excluded from collisions. Use `CollisionObject2D.get_rid`
 * to get the `RID` associated with a `CollisionObject2D`-derived node. Note: The returned array is
 * copied and any changes to it will not update the original property value. To update the value
 * you need to modify the returned array, and then assign it to the property again.
 *
 * Generated from Godot docs: PhysicsPointQueryParameters2D.get_exclude
 */
fun PhysicsPointQueryParameters2D.getExclude(): List<RID> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetRIDList(getExcludeBind, handle)
}

var PhysicsPointQueryParameters2D.exclude: List<RID>
    @JvmName("excludeProperty")
    get() = getExclude()
    @JvmName("setExcludeProperty")
    set(value) = setExclude(value)

private const val SET_EXCLUDE_HASH = 381264803L
private val setExcludeBind by lazy {
    ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "set_exclude", SET_EXCLUDE_HASH)
}

private const val GET_EXCLUDE_HASH = 3995934104L
private val getExcludeBind by lazy {
    ObjectCalls.getMethodBind("PhysicsPointQueryParameters2D", "get_exclude", GET_EXCLUDE_HASH)
}
