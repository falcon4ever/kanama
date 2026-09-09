package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for NavigationMeshSourceGeometryData2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationMeshSourceGeometryData2D waits on: ptrcallNoArgsRetPackedVector2ListList,
//   ptrcallWithArrayArg, ptrcallWithPackedVector2ListAndBoolArg, ptrcallWithPackedVector2ListArg,
//   ptrcallWithPackedVector2ListListArg
// Index: docs/contributing/ios-shape-gap.md

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
 * Returns all the traversable area outlines arrays.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData2D.get_traversable_outlines
 */
fun NavigationMeshSourceGeometryData2D.getTraversableOutlines(): List<List<Vector2>> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedVector2ListList(getTraversableOutlinesBind, handle)
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
 * Returns all the obstructed area outlines arrays.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData2D.get_obstruction_outlines
 */
fun NavigationMeshSourceGeometryData2D.getObstructionOutlines(): List<List<Vector2>> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedVector2ListList(getObstructionOutlinesBind, handle)
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
 * Adds the outline points of a shape as traversable area.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData2D.add_traversable_outline
 */
fun NavigationMeshSourceGeometryData2D.addTraversableOutline(shapeOutline: List<Vector2>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListArg(addTraversableOutlineBind, handle, shapeOutline)
}

/**
 * Adds the outline points of a shape as obstructed area.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData2D.add_obstruction_outline
 */
fun NavigationMeshSourceGeometryData2D.addObstructionOutline(shapeOutline: List<Vector2>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListArg(addObstructionOutlineBind, handle, shapeOutline)
}

/**
 * Adds a projected obstruction shape to the source geometry. If `carve` is `true` the carved shape
 * will not be affected by additional offsets (e.g. agent radius) of the navigation mesh baking
 * process.
 *
 * Generated from Godot docs: NavigationMeshSourceGeometryData2D.add_projected_obstruction
 */
fun NavigationMeshSourceGeometryData2D.addProjectedObstruction(vertices: List<Vector2>, carve: Boolean) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListAndBoolArg(addProjectedObstructionBind, handle, vertices, carve)
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

var NavigationMeshSourceGeometryData2D.traversableOutlines: List<List<Vector2>>
    @JvmName("traversableOutlinesProperty")
    get() = getTraversableOutlines()
    @JvmName("setTraversableOutlinesProperty")
    set(value) = setTraversableOutlines(value)

var NavigationMeshSourceGeometryData2D.obstructionOutlines: List<List<Vector2>>
    @JvmName("obstructionOutlinesProperty")
    get() = getObstructionOutlines()
    @JvmName("setObstructionOutlinesProperty")
    set(value) = setObstructionOutlines(value)

private const val SET_TRAVERSABLE_OUTLINES_HASH = 381264803L
private val setTraversableOutlinesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "set_traversable_outlines", SET_TRAVERSABLE_OUTLINES_HASH)
}

private const val GET_TRAVERSABLE_OUTLINES_HASH = 3995934104L
private val getTraversableOutlinesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "get_traversable_outlines", GET_TRAVERSABLE_OUTLINES_HASH)
}

private const val SET_OBSTRUCTION_OUTLINES_HASH = 381264803L
private val setObstructionOutlinesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "set_obstruction_outlines", SET_OBSTRUCTION_OUTLINES_HASH)
}

private const val GET_OBSTRUCTION_OUTLINES_HASH = 3995934104L
private val getObstructionOutlinesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "get_obstruction_outlines", GET_OBSTRUCTION_OUTLINES_HASH)
}

private const val APPEND_TRAVERSABLE_OUTLINES_HASH = 381264803L
private val appendTraversableOutlinesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "append_traversable_outlines", APPEND_TRAVERSABLE_OUTLINES_HASH)
}

private const val APPEND_OBSTRUCTION_OUTLINES_HASH = 381264803L
private val appendObstructionOutlinesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "append_obstruction_outlines", APPEND_OBSTRUCTION_OUTLINES_HASH)
}

private const val ADD_TRAVERSABLE_OUTLINE_HASH = 1509147220L
private val addTraversableOutlineBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "add_traversable_outline", ADD_TRAVERSABLE_OUTLINE_HASH)
}

private const val ADD_OBSTRUCTION_OUTLINE_HASH = 1509147220L
private val addObstructionOutlineBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "add_obstruction_outline", ADD_OBSTRUCTION_OUTLINE_HASH)
}

private const val ADD_PROJECTED_OBSTRUCTION_HASH = 3882407395L
private val addProjectedObstructionBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "add_projected_obstruction", ADD_PROJECTED_OBSTRUCTION_HASH)
}

private const val SET_PROJECTED_OBSTRUCTIONS_HASH = 381264803L
private val setProjectedObstructionsBind by lazy {
    ObjectCalls.getMethodBind("NavigationMeshSourceGeometryData2D", "set_projected_obstructions", SET_PROJECTED_OBSTRUCTIONS_HASH)
}
