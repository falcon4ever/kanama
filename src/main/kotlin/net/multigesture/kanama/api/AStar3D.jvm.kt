package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

// GENERATED desktop/Android companion for AStar3D (scripts/generate_api_wrapper.py --write-tree).
// DO NOT EDIT BY HAND. These members are not in the shared wrapper tree: iOS has no audited
// ObjectCalls helper for their ptrcall shape yet (or does not host a wrapper type they use), so
// they compile for desktop/Android only. Re-run the generator when iOS gains the helper.
// KANAMA-IOS-GAP AStar3D waits on: ptrcallNoArgsRetPackedInt64List,
//   ptrcallWithLongArgRetPackedInt64List, ptrcallWithTwoLongAndBoolArgsRetPackedInt64List,
//   ptrcallWithTwoLongAndBoolArgsRetPackedVector3List
// Index: docs/reference/generated/ios-shape-gap.md

/**
 * Returns an array with the IDs of the points that form the connection with the given point.
 *
 * Generated from Godot docs: AStar3D.get_point_connections
 */
fun AStar3D.getPointConnections(id: Long): List<Long> {
    checkOpen()
    return ObjectCalls.ptrcallWithLongArgRetPackedInt64List(getPointConnectionsBind, handle, id)
}

/**
 * Returns an array of all point IDs.
 *
 * Generated from Godot docs: AStar3D.get_point_ids
 */
fun AStar3D.getPointIds(): List<Long> {
    checkOpen()
    return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getPointIdsBind, handle)
}

/**
 * Returns an array with the points that are in the path found by AStar3D between the given points.
 * The array is ordered from the starting point to the ending point of the path. If `from_id` point
 * is disabled, returns an empty array (even if `from_id == to_id`). If `from_id` point is not
 * disabled, there is no valid path to the target, and `allow_partial_path` is `true`, returns a
 * path to the point closest to the target that can be reached. Note: This method is not
 * thread-safe; it can only be used from a single `Thread` at a given time. Consider using `Mutex`
 * to ensure exclusive access to one thread to avoid race conditions. Additionally, when
 * `allow_partial_path` is `true` and `to_id` is disabled the search may take an unusually long
 * time to finish.
 *
 * Generated from Godot docs: AStar3D.get_point_path
 */
fun AStar3D.getPointPath(fromId: Long, toId: Long, allowPartialPath: Boolean = false): List<Vector3> {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoLongAndBoolArgsRetPackedVector3List(getPointPathBind, handle, fromId, toId, allowPartialPath)
}

/**
 * Returns an array with the IDs of the points that form the path found by AStar3D between the
 * given points. The array is ordered from the starting point to the ending point of the path. If
 * `from_id` point is disabled, returns an empty array (even if `from_id == to_id`). If `from_id`
 * point is not disabled, there is no valid path to the target, and `allow_partial_path` is `true`,
 * returns a path to the point closest to the target that can be reached. Note: When
 * `allow_partial_path` is `true` and `to_id` is disabled the search may take an unusually long
 * time to finish.
 *
 * Generated from Godot docs: AStar3D.get_id_path
 */
fun AStar3D.getIdPath(fromId: Long, toId: Long, allowPartialPath: Boolean = false): List<Long> {
    checkOpen()
    return ObjectCalls.ptrcallWithTwoLongAndBoolArgsRetPackedInt64List(getIdPathBind, handle, fromId, toId, allowPartialPath)
}

private const val GET_POINT_CONNECTIONS_HASH = 2865087369L
private val getPointConnectionsBind by lazy {
    ObjectCalls.getMethodBind("AStar3D", "get_point_connections", GET_POINT_CONNECTIONS_HASH)
}

private const val GET_POINT_IDS_HASH = 3851388692L
private val getPointIdsBind by lazy {
    ObjectCalls.getMethodBind("AStar3D", "get_point_ids", GET_POINT_IDS_HASH)
}

private const val GET_POINT_PATH_HASH = 1562654675L
private val getPointPathBind by lazy {
    ObjectCalls.getMethodBind("AStar3D", "get_point_path", GET_POINT_PATH_HASH)
}

private const val GET_ID_PATH_HASH = 3136199648L
private val getIdPathBind by lazy {
    ObjectCalls.getMethodBind("AStar3D", "get_id_path", GET_ID_PATH_HASH)
}
