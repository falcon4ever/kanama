package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for NavigationServer3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP NavigationServer3D waits on: ptrcallWithPackedVector3ListAndDoubleArgRetPackedVector3List,
//   ptrcallWithRIDAndPackedVector3ListArg
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Sets the outline vertices for the obstacle. If the vertices are winded in clockwise order agents
 * will be pushed in by the obstacle, else they will be pushed out.
 *
 * Generated from Godot docs: NavigationServer3D.obstacle_set_vertices
 */
fun NavigationServer3D.obstacleSetVertices(obstacle: RID, vertices: List<Vector3>) {
    ObjectCalls.ptrcallWithRIDAndPackedVector3ListArg(obstacleSetVerticesBind, navigationServer3DSingleton, obstacle, vertices)
}

/**
 * Returns a simplified version of `path` with less critical path points removed. The
 * simplification amount is in worlds units and controlled by `epsilon`. The simplification uses a
 * variant of Ramer-Douglas-Peucker algorithm for curve point decimation. Path simplification can
 * be helpful to mitigate various path following issues that can arise with certain agent types and
 * script behaviors. E.g. "steering" agents or avoidance in "open fields".
 *
 * Generated from Godot docs: NavigationServer3D.simplify_path
 */
fun NavigationServer3D.simplifyPath(path: List<Vector3>, epsilon: Double): List<Vector3> {
    return ObjectCalls.ptrcallWithPackedVector3ListAndDoubleArgRetPackedVector3List(simplifyPathBind, navigationServer3DSingleton, path, epsilon)
}

private val navigationServer3DSingleton: MemorySegment by lazy {
    ObjectCalls.getSingleton("NavigationServer3D")
}

private const val OBSTACLE_SET_VERTICES_HASH = 4030257846L
private val obstacleSetVerticesBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_vertices", OBSTACLE_SET_VERTICES_HASH)
}

private const val SIMPLIFY_PATH_HASH = 2344122170L
private val simplifyPathBind by lazy {
    ObjectCalls.getMethodBind("NavigationServer3D", "simplify_path", SIMPLIFY_PATH_HASH)
}
