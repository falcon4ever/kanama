package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for NavigationMeshSourceGeometryData2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationMeshSourceGeometryData2D waits on: ptrcallWithArrayArg,
//   ptrcallWithPackedVector2ListListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets all the traversable area outlines arrays.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData2D.set_traversable_outlines
 */
fun NavigationMeshSourceGeometryData2D.setTraversableOutlines(traversableOutlines: List<List<Vector2>>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListListArg(setTraversableOutlinesBind, handle, traversableOutlines)
}

/**
 * Sets all the obstructed area outlines arrays.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData2D.set_obstruction_outlines
 */
fun NavigationMeshSourceGeometryData2D.setObstructionOutlines(obstructionOutlines: List<List<Vector2>>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListListArg(setObstructionOutlinesBind, handle, obstructionOutlines)
}

/**
 * Appends another array of `traversable_outlines` at the end of the existing traversable outlines
 * array.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData2D.append_traversable_outlines
 */
fun NavigationMeshSourceGeometryData2D.appendTraversableOutlines(traversableOutlines: List<List<Vector2>>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListListArg(appendTraversableOutlinesBind, handle, traversableOutlines)
}

/**
 * Appends another array of `obstruction_outlines` at the end of the existing obstruction outlines
 * array.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData2D.append_obstruction_outlines
 */
fun NavigationMeshSourceGeometryData2D.appendObstructionOutlines(obstructionOutlines: List<List<Vector2>>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListListArg(appendObstructionOutlinesBind, handle, obstructionOutlines)
}

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

private const val SET_TRAVERSABLE_OUTLINES_HASH = 381264803L
private val setTraversableOutlinesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "set_traversable_outlines", SET_TRAVERSABLE_OUTLINES_HASH)
}

private const val SET_OBSTRUCTION_OUTLINES_HASH = 381264803L
private val setObstructionOutlinesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "set_obstruction_outlines", SET_OBSTRUCTION_OUTLINES_HASH)
}

private const val APPEND_TRAVERSABLE_OUTLINES_HASH = 381264803L
private val appendTraversableOutlinesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "append_traversable_outlines", APPEND_TRAVERSABLE_OUTLINES_HASH)
}

private const val APPEND_OBSTRUCTION_OUTLINES_HASH = 381264803L
private val appendObstructionOutlinesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "append_obstruction_outlines", APPEND_OBSTRUCTION_OUTLINES_HASH)
}

private const val SET_PROJECTED_OBSTRUCTIONS_HASH = 381264803L
private val setProjectedObstructionsBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "set_projected_obstructions", SET_PROJECTED_OBSTRUCTIONS_HASH)
}
