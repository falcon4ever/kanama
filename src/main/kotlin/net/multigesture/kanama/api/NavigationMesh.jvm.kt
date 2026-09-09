package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for NavigationMesh (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationMesh waits on: ptrcallNoArgsRetPackedVector3List,
//   ptrcallWithIntArgRetPackedInt32List, ptrcallWithPackedInt32ListArg,
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
 * Returns a `PackedVector3Array` containing all the vertices being used to create the polygons.
 *
 * Generated from Godot docs: NavigationMesh.get_vertices
 */
fun NavigationMesh.getVertices(): List<Vector3> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getVerticesBind, handle)
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

/**
 * Returns a `PackedInt32Array` containing the indices of the vertices of a created polygon.
 *
 * Generated from Godot docs: NavigationMesh.get_polygon
 */
fun NavigationMesh.getPolygon(idx: Int): List<Int> {
    checkOpen()
    return ObjectCalls.ptrcallWithIntArgRetPackedInt32List(getPolygonBind, handle, idx)
}

var NavigationMesh.vertices: List<Vector3>
    @JvmName("verticesProperty")
    get() = getVertices()
    @JvmName("setVerticesProperty")
    set(value) = setVertices(value)

private const val SET_VERTICES_HASH = 334873810L
private val setVerticesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMesh", "set_vertices", SET_VERTICES_HASH)
}

private const val GET_VERTICES_HASH = 497664490L
private val getVerticesBind by lazy {
    ObjectCalls.getMethodBind("NavigationMesh", "get_vertices", GET_VERTICES_HASH)
}

private const val ADD_POLYGON_HASH = 3614634198L
private val addPolygonBind by lazy {
    ObjectCalls.getMethodBind("NavigationMesh", "add_polygon", ADD_POLYGON_HASH)
}

private const val GET_POLYGON_HASH = 3668444399L
private val getPolygonBind by lazy {
    ObjectCalls.getMethodBind("NavigationMesh", "get_polygon", GET_POLYGON_HASH)
}
