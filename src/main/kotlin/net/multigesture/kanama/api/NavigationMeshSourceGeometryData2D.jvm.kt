package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls

// GENERATED desktop/Android companion for NavigationMeshSourceGeometryData2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationMeshSourceGeometryData2D waits on: ptrcallWithArrayArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the projected obstructions with an Array of Dictionaries with the following key value
 * pairs:
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData2D.set_projected_obstructions
 */
fun NavigationMeshSourceGeometryData2D.setProjectedObstructions(projectedObstructions: List<Any?>) {
    checkOpen()
    ObjectCalls.ptrcallWithArrayArg(setProjectedObstructionsBind, handle, projectedObstructions)
}

private const val SET_PROJECTED_OBSTRUCTIONS_HASH = 381264803L
private val setProjectedObstructionsBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "set_projected_obstructions", SET_PROJECTED_OBSTRUCTIONS_HASH)
}
