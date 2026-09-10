package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for NavigationMesh (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationMesh waits on: ptrcallWithPackedInt32ListArg,
//   ptrcallWithPackedVector3ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the vertices that can be then indexed to create polygons with the `add_polygon` method.
 *
 * Generated from Godot docs: NavigationMesh.set_vertices
 */
fun NavigationMesh.setVertices(vertices: List<Vector3>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector3ListArg(setVerticesBind, handle, vertices)
}

/**
 * Adds a polygon using the indices of the vertices you get when calling `get_vertices`.
 *
 * Generated from Godot docs: NavigationMesh.add_polygon
 */
fun NavigationMesh.addPolygon(polygon: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedInt32ListArg(addPolygonBind, handle, polygon)
}

private const val SET_VERTICES_HASH = 334873810L
private val setVerticesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMesh", "set_vertices", SET_VERTICES_HASH)
}

private const val ADD_POLYGON_HASH = 3614634198L
private val addPolygonBind by lazy {
    ObjectCalls.getMethodBind("NavigationMesh", "add_polygon", ADD_POLYGON_HASH)
}
