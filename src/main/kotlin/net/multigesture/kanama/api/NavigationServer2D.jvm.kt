package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

// GENERATED desktop/Android companion for NavigationServer2D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationServer2D waits on: ptrcallWithPackedVector2ListAndDoubleArgRetPackedVector2List,
//   ptrcallWithRIDAndPackedVector2ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the outline vertices for the obstacle. If the vertices are winded in clockwise order agents
 * will be pushed in by the obstacle, else they will be pushed out.
 *
 * Generated from Godot docs: NavigationServer2D.obstacle_set_vertices
 */
fun NavigationServer2D.obstacleSetVertices(obstacle: RID, vertices: List<Vector2>) {
    ObjectCalls.ptrcallWithRIDAndPackedVector2ListArg(obstacleSetVerticesBind, navigationServer2DSingleton, obstacle, vertices)
}

/**
 * Returns a simplified version of `path` with less critical path points removed. The
 * simplification amount is in worlds units and controlled by `epsilon`. The simplification uses a
 * variant of Ramer-Douglas-Peucker algorithm for curve point decimation. Path simplification can
 * be helpful to mitigate various path following issues that can arise with certain agent types and
 * script behaviors. E.g. "steering" agents or avoidance in "open fields".
 *
 * Generated from Godot docs: NavigationServer2D.simplify_path
 */
fun NavigationServer2D.simplifyPath(path: List<Vector2>, epsilon: Double): List<Vector2> {
    return ObjectCalls.ptrcallWithPackedVector2ListAndDoubleArgRetPackedVector2List(simplifyPathBind, navigationServer2DSingleton, path, epsilon)
}

private val navigationServer2DSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("NavigationServer2D")
}

private const val OBSTACLE_SET_VERTICES_HASH = 29476483L
private val obstacleSetVerticesBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer2D", "obstacle_set_vertices", OBSTACLE_SET_VERTICES_HASH)
}

private const val SIMPLIFY_PATH_HASH = 2457191505L
private val simplifyPathBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer2D", "simplify_path", SIMPLIFY_PATH_HASH)
}
