package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for NavigationPolygon (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationPolygon waits on: ptrcallWithIntAndPackedVector2ListArgs,
//   ptrcallWithPackedInt32ListArg, ptrcallWithPackedVector2ListAndIntArgs,
//   ptrcallWithPackedVector2ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the vertices that can be then indexed to create polygons with the `add_polygon` method.
 *
 * Generated from Godot docs: NavigationPolygon.set_vertices
 */
fun NavigationPolygon.setVertices(vertices: List<Vector2>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListArg(setVerticesBind, handle, vertices)
}

/**
 * Adds a polygon using the indices of the vertices you get when calling `get_vertices`.
 *
 * Generated from Godot docs: NavigationPolygon.add_polygon
 */
fun NavigationPolygon.addPolygon(polygon: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(addPolygonBind, handle, polygon)
}

/**
 * Appends a `PackedVector2Array` that contains the vertices of an outline to the internal array
 * that contains all the outlines.
 *
 * Generated from Godot docs: NavigationPolygon.add_outline
 */
fun NavigationPolygon.addOutline(outline: List<Vector2>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListArg(addOutlineBind, handle, outline)
}

/**
 * Adds a `PackedVector2Array` that contains the vertices of an outline to the internal array that
 * contains all the outlines at a fixed position.
 *
 * Generated from Godot docs: NavigationPolygon.add_outline_at_index
 */
fun NavigationPolygon.addOutlineAtIndex(outline: List<Vector2>, index: Int) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListAndIntArgs(addOutlineAtIndexBind, handle, outline, index)
}

/**
 * Changes an outline created in the editor or by script. You have to call
 * `make_polygons_from_outlines` for the polygons to update.
 *
 * Generated from Godot docs: NavigationPolygon.set_outline
 */
fun NavigationPolygon.setOutline(idx: Int, outline: List<Vector2>) {
    checkOpen()
    ObjectCalls.ptrcallWithIntAndPackedVector2ListArgs(setOutlineBind, handle, idx, outline)
}

private const val SET_VERTICES_HASH = 1509147220L
private val setVerticesBind by lazy {
    ObjectCalls.getMethodBind("NavigationPolygon", "set_vertices", SET_VERTICES_HASH)
}

private const val ADD_POLYGON_HASH = 3614634198L
private val addPolygonBind by lazy {
    ObjectCalls.getMethodBind("NavigationPolygon", "add_polygon", ADD_POLYGON_HASH)
}

private const val ADD_OUTLINE_HASH = 1509147220L
private val addOutlineBind by lazy {
    ObjectCalls.getMethodBind("NavigationPolygon", "add_outline", ADD_OUTLINE_HASH)
}

private const val ADD_OUTLINE_AT_INDEX_HASH = 1569738947L
private val addOutlineAtIndexBind by lazy {
    ObjectCalls.getMethodBind("NavigationPolygon", "add_outline_at_index", ADD_OUTLINE_AT_INDEX_HASH)
}

private const val SET_OUTLINE_HASH = 1201971903L
private val setOutlineBind by lazy {
    ObjectCalls.getMethodBind("NavigationPolygon", "set_outline", SET_OUTLINE_HASH)
}
