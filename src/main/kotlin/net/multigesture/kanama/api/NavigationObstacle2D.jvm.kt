package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for NavigationObstacle2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationObstacle2D waits on: ptrcallWithPackedVector2ListArg
// Index: docs/contributing/ios-shape-gap.md

/**
 * The outline vertices of the obstacle. If the vertices are winded in clockwise order agents will
 * be pushed in by the obstacle, else they will be pushed out. Outlines can not be crossed or
 * overlap. Should the vertices using obstacle be warped to a new position agent's can not predict
 * this movement and may get trapped inside the obstacle.
 *
 * Generated from Godot docs: NavigationObstacle2D.set_vertices
 */
fun NavigationObstacle2D.setVertices(vertices: List<Vector2>) {
    ObjectCalls.ptrcallWithPackedVector2ListArg(setVerticesBind, handle, vertices)
}

private const val SET_VERTICES_HASH = 1509147220L
private val setVerticesBind by lazy {
    ObjectCalls.getMethodBind("NavigationObstacle2D", "set_vertices", SET_VERTICES_HASH)
}
