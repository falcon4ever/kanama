package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for PolygonPathFinder (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP PolygonPathFinder waits on: ptrcallWithPackedVector2ListAndPackedInt32ListArgs,
//   ptrcallWithTwoVector2ArgsRetPackedVector2List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets up `PolygonPathFinder` with an array of points that define the vertices of the polygon, and
 * an array of indices that determine the edges of the polygon. The length of `connections` must be
 * even, returns an error if odd.
 *
 * Generated from Godot docs: PolygonPathFinder.setup
 */
fun PolygonPathFinder.setup(points: List<Vector2>, connections: List<Int>) {
    checkOpen()
    ObjectCalls.ptrcallWithPackedVector2ListAndPackedInt32ListArgs(setupBind, handle, points, connections)
}

fun PolygonPathFinder.findPath(from: Vector2, to: Vector2): List<Vector2> {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoVector2ArgsRetPackedVector2List(findPathBind, handle, from, to)
}

fun PolygonPathFinder.getIntersections(from: Vector2, to: Vector2): List<Vector2> {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoVector2ArgsRetPackedVector2List(getIntersectionsBind, handle, from, to)
}

private const val SETUP_HASH = 3251786936L
private val setupBind by lazy {
    ObjectCalls.getMethodBind("PolygonPathFinder", "setup", SETUP_HASH)
}

private const val FIND_PATH_HASH = 1562168077L
private val findPathBind by lazy {
    ObjectCalls.getMethodBind("PolygonPathFinder", "find_path", FIND_PATH_HASH)
}

private const val GET_INTERSECTIONS_HASH = 3932192302L
private val getIntersectionsBind by lazy {
    ObjectCalls.getMethodBind("PolygonPathFinder", "get_intersections", GET_INTERSECTIONS_HASH)
}
