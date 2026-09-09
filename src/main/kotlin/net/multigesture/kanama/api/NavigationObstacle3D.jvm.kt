package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for NavigationObstacle3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationObstacle3D waits on: ptrcallNoArgsRetPackedVector3List,
//   ptrcallWithPackedVector3ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * The outline vertices of the obstacle. If the vertices are winded in clockwise order agents will
 * be pushed in by the obstacle, else they will be pushed out. Outlines can not be crossed or
 * overlap. Should the vertices using obstacle be warped to a new position agent's can not predict
 * this movement and may get trapped inside the obstacle.
 *
 * Generated from Godot docs: NavigationObstacle3D.set_vertices
 */
fun NavigationObstacle3D.setVertices(vertices: List<Vector3>) {
    ObjectCalls.ptrcallWithPackedVector3ListArg(setVerticesBind, handle, vertices)
}

/**
 * The outline vertices of the obstacle. If the vertices are winded in clockwise order agents will
 * be pushed in by the obstacle, else they will be pushed out. Outlines can not be crossed or
 * overlap. Should the vertices using obstacle be warped to a new position agent's can not predict
 * this movement and may get trapped inside the obstacle.
 *
 * Generated from Godot docs: NavigationObstacle3D.get_vertices
 */
fun NavigationObstacle3D.getVertices(): List<Vector3> {
    return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getVerticesBind, handle)
}

var NavigationObstacle3D.vertices: List<Vector3>
    @JvmName("verticesProperty")
    get() = getVertices()
    @JvmName("setVerticesProperty")
    set(value) = setVertices(value)

private const val SET_VERTICES_HASH = 334873810L
private val setVerticesBind by lazy {
    ObjectCalls.getMethodBind("NavigationObstacle3D", "set_vertices", SET_VERTICES_HASH)
}

private const val GET_VERTICES_HASH = 497664490L
private val getVerticesBind by lazy {
    ObjectCalls.getMethodBind("NavigationObstacle3D", "get_vertices", GET_VERTICES_HASH)
}
