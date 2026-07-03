package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * An implementation of A* for finding the shortest path between two vertices on a connected graph
 * in 3D space.
 *
 * Generated from Godot docs: AStar3D
 */
class AStar3D(handle: MemorySegment) : RefCounted(handle) {
    var neighborFilterEnabled: Boolean
        @JvmName("neighborFilterEnabledProperty")
        get() = isNeighborFilterEnabled()
        @JvmName("setNeighborFilterEnabledProperty")
        set(value) = setNeighborFilterEnabled(value)

    /**
     * Returns the next available point ID with no point associated to it.
     *
     * Generated from Godot docs: AStar3D.get_available_point_id
     */
    fun getAvailablePointId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAvailablePointIdBind, handle)
    }

    /**
     * Adds a new point at the given position with the given identifier. The `id` must be 0 or larger,
     * and the `weight_scale` must be 0.0 or greater. The `weight_scale` is multiplied by the result of
     * `_compute_cost` when determining the overall cost of traveling across a segment from a
     * neighboring point to this point. Thus, all else being equal, the algorithm prefers points with
     * lower `weight_scale`s to form a path.
     *
     * Generated from Godot docs: AStar3D.add_point
     */
    fun addPoint(id: Long, position: Vector3, weightScale: Double = 1.0) {
        ObjectCalls.ptrcallWithLongVector3AndDoubleArgs(addPointBind, handle, id, position, weightScale)
    }

    /**
     * Returns the position of the point associated with the given `id`.
     *
     * Generated from Godot docs: AStar3D.get_point_position
     */
    fun getPointPosition(id: Long): Vector3 {
        return ObjectCalls.ptrcallWithLongArgRetVector3(getPointPositionBind, handle, id)
    }

    /**
     * Sets the `position` for the point with the given `id`.
     *
     * Generated from Godot docs: AStar3D.set_point_position
     */
    fun setPointPosition(id: Long, position: Vector3) {
        ObjectCalls.ptrcallWithLongAndVector3Arg(setPointPositionBind, handle, id, position)
    }

    /**
     * Returns the weight scale of the point associated with the given `id`.
     *
     * Generated from Godot docs: AStar3D.get_point_weight_scale
     */
    fun getPointWeightScale(id: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getPointWeightScaleBind, handle, id)
    }

    /**
     * Sets the `weight_scale` for the point with the given `id`. The `weight_scale` is multiplied by
     * the result of `_compute_cost` when determining the overall cost of traveling across a segment
     * from a neighboring point to this point.
     *
     * Generated from Godot docs: AStar3D.set_point_weight_scale
     */
    fun setPointWeightScale(id: Long, weightScale: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setPointWeightScaleBind, handle, id, weightScale)
    }

    /**
     * Removes the point associated with the given `id` from the points pool.
     *
     * Generated from Godot docs: AStar3D.remove_point
     */
    fun removePoint(id: Long) {
        ObjectCalls.ptrcallWithLongArg(removePointBind, handle, id)
    }

    /**
     * Returns whether a point associated with the given `id` exists.
     *
     * Generated from Godot docs: AStar3D.has_point
     */
    fun hasPoint(id: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasPointBind, handle, id)
    }

    /**
     * Returns an array with the IDs of the points that form the connection with the given point.
     *
     * Generated from Godot docs: AStar3D.get_point_connections
     */
    fun getPointConnections(id: Long): List<Long> {
        return ObjectCalls.ptrcallWithLongArgRetPackedInt64List(getPointConnectionsBind, handle, id)
    }

    /**
     * Returns an array of all point IDs.
     *
     * Generated from Godot docs: AStar3D.get_point_ids
     */
    fun getPointIds(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getPointIdsBind, handle)
    }

    /**
     * Disables or enables the specified point for pathfinding. Useful for making a temporary obstacle.
     *
     * Generated from Godot docs: AStar3D.set_point_disabled
     */
    fun setPointDisabled(id: Long, disabled: Boolean = true) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setPointDisabledBind, handle, id, disabled)
    }

    /**
     * Returns whether a point is disabled or not for pathfinding. By default, all points are enabled.
     *
     * Generated from Godot docs: AStar3D.is_point_disabled
     */
    fun isPointDisabled(id: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isPointDisabledBind, handle, id)
    }

    /**
     * If `true` enables the filtering of neighbors via `_filter_neighbor`.
     *
     * Generated from Godot docs: AStar3D.set_neighbor_filter_enabled
     */
    fun setNeighborFilterEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNeighborFilterEnabledBind, handle, enabled)
    }

    /**
     * If `true` enables the filtering of neighbors via `_filter_neighbor`.
     *
     * Generated from Godot docs: AStar3D.is_neighbor_filter_enabled
     */
    fun isNeighborFilterEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNeighborFilterEnabledBind, handle)
    }

    /**
     * Creates a segment between the given points. If `bidirectional` is `false`, only movement from
     * `id` to `to_id` is allowed, not the reverse direction.
     *
     * Generated from Godot docs: AStar3D.connect_points
     */
    fun connectPoints(id: Long, toId: Long, bidirectional: Boolean = true) {
        ObjectCalls.ptrcallWithTwoLongAndBoolArgs(connectPointsBind, handle, id, toId, bidirectional)
    }

    /**
     * Deletes the segment between the given points. If `bidirectional` is `false`, only movement from
     * `id` to `to_id` is prevented, and a unidirectional segment possibly remains.
     *
     * Generated from Godot docs: AStar3D.disconnect_points
     */
    fun disconnectPoints(id: Long, toId: Long, bidirectional: Boolean = true) {
        ObjectCalls.ptrcallWithTwoLongAndBoolArgs(disconnectPointsBind, handle, id, toId, bidirectional)
    }

    /**
     * Returns whether the two given points are directly connected by a segment. If `bidirectional` is
     * `false`, returns whether movement from `id` to `to_id` is possible through this segment.
     *
     * Generated from Godot docs: AStar3D.are_points_connected
     */
    fun arePointsConnected(id: Long, toId: Long, bidirectional: Boolean = true): Boolean {
        return ObjectCalls.ptrcallWithTwoLongAndBoolArgsRetBool(arePointsConnectedBind, handle, id, toId, bidirectional)
    }

    /**
     * Returns the number of points currently in the points pool.
     *
     * Generated from Godot docs: AStar3D.get_point_count
     */
    fun getPointCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPointCountBind, handle)
    }

    /**
     * Returns the capacity of the structure backing the points, useful in conjunction with
     * `reserve_space`.
     *
     * Generated from Godot docs: AStar3D.get_point_capacity
     */
    fun getPointCapacity(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPointCapacityBind, handle)
    }

    /**
     * Reserves space internally for `num_nodes` points. Useful if you're adding a known large number
     * of points at once, such as points on a grid.
     *
     * Generated from Godot docs: AStar3D.reserve_space
     */
    fun reserveSpace(numNodes: Long) {
        ObjectCalls.ptrcallWithLongArg(reserveSpaceBind, handle, numNodes)
    }

    /**
     * Clears all the points and segments.
     *
     * Generated from Godot docs: AStar3D.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    /**
     * Returns the ID of the closest point to `to_position`, optionally taking disabled points into
     * account. Returns `-1` if there are no points in the points pool. Note: If several points are the
     * closest to `to_position`, the one with the smallest ID will be returned, ensuring a
     * deterministic result.
     *
     * Generated from Godot docs: AStar3D.get_closest_point
     */
    fun getClosestPoint(toPosition: Vector3, includeDisabled: Boolean = false): Long {
        return ObjectCalls.ptrcallWithVector3AndBoolArgRetLong(getClosestPointBind, handle, toPosition, includeDisabled)
    }

    /**
     * Returns the closest position to `to_position` that resides inside a segment between two
     * connected points.
     *
     * Generated from Godot docs: AStar3D.get_closest_position_in_segment
     */
    fun getClosestPositionInSegment(toPosition: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithVector3ArgRetVector3(getClosestPositionInSegmentBind, handle, toPosition)
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
    fun getPointPath(fromId: Long, toId: Long, allowPartialPath: Boolean = false): List<Vector3> {
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
    fun getIdPath(fromId: Long, toId: Long, allowPartialPath: Boolean = false): List<Long> {
        return ObjectCalls.ptrcallWithTwoLongAndBoolArgsRetPackedInt64List(getIdPathBind, handle, fromId, toId, allowPartialPath)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AStar3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AStar3D? =
            if (handle.address() == 0L) null else AStar3D(handle)

        private const val GET_AVAILABLE_POINT_ID_HASH = 3905245786L
        private val getAvailablePointIdBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "get_available_point_id", GET_AVAILABLE_POINT_ID_HASH)
        }

        private const val ADD_POINT_HASH = 1038703438L
        private val addPointBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "add_point", ADD_POINT_HASH)
        }

        private const val GET_POINT_POSITION_HASH = 711720468L
        private val getPointPositionBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "get_point_position", GET_POINT_POSITION_HASH)
        }

        private const val SET_POINT_POSITION_HASH = 1530502735L
        private val setPointPositionBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "set_point_position", SET_POINT_POSITION_HASH)
        }

        private const val GET_POINT_WEIGHT_SCALE_HASH = 2339986948L
        private val getPointWeightScaleBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "get_point_weight_scale", GET_POINT_WEIGHT_SCALE_HASH)
        }

        private const val SET_POINT_WEIGHT_SCALE_HASH = 1602489585L
        private val setPointWeightScaleBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "set_point_weight_scale", SET_POINT_WEIGHT_SCALE_HASH)
        }

        private const val REMOVE_POINT_HASH = 1286410249L
        private val removePointBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "remove_point", REMOVE_POINT_HASH)
        }

        private const val HAS_POINT_HASH = 1116898809L
        private val hasPointBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "has_point", HAS_POINT_HASH)
        }

        private const val GET_POINT_CONNECTIONS_HASH = 2865087369L
        private val getPointConnectionsBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "get_point_connections", GET_POINT_CONNECTIONS_HASH)
        }

        private const val GET_POINT_IDS_HASH = 3851388692L
        private val getPointIdsBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "get_point_ids", GET_POINT_IDS_HASH)
        }

        private const val SET_POINT_DISABLED_HASH = 972357352L
        private val setPointDisabledBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "set_point_disabled", SET_POINT_DISABLED_HASH)
        }

        private const val IS_POINT_DISABLED_HASH = 1116898809L
        private val isPointDisabledBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "is_point_disabled", IS_POINT_DISABLED_HASH)
        }

        private const val SET_NEIGHBOR_FILTER_ENABLED_HASH = 2586408642L
        private val setNeighborFilterEnabledBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "set_neighbor_filter_enabled", SET_NEIGHBOR_FILTER_ENABLED_HASH)
        }

        private const val IS_NEIGHBOR_FILTER_ENABLED_HASH = 36873697L
        private val isNeighborFilterEnabledBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "is_neighbor_filter_enabled", IS_NEIGHBOR_FILTER_ENABLED_HASH)
        }

        private const val CONNECT_POINTS_HASH = 3710494224L
        private val connectPointsBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "connect_points", CONNECT_POINTS_HASH)
        }

        private const val DISCONNECT_POINTS_HASH = 3710494224L
        private val disconnectPointsBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "disconnect_points", DISCONNECT_POINTS_HASH)
        }

        private const val ARE_POINTS_CONNECTED_HASH = 2288175859L
        private val arePointsConnectedBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "are_points_connected", ARE_POINTS_CONNECTED_HASH)
        }

        private const val GET_POINT_COUNT_HASH = 3905245786L
        private val getPointCountBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "get_point_count", GET_POINT_COUNT_HASH)
        }

        private const val GET_POINT_CAPACITY_HASH = 3905245786L
        private val getPointCapacityBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "get_point_capacity", GET_POINT_CAPACITY_HASH)
        }

        private const val RESERVE_SPACE_HASH = 1286410249L
        private val reserveSpaceBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "reserve_space", RESERVE_SPACE_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "clear", CLEAR_HASH)
        }

        private const val GET_CLOSEST_POINT_HASH = 3241074317L
        private val getClosestPointBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "get_closest_point", GET_CLOSEST_POINT_HASH)
        }

        private const val GET_CLOSEST_POSITION_IN_SEGMENT_HASH = 192990374L
        private val getClosestPositionInSegmentBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "get_closest_position_in_segment", GET_CLOSEST_POSITION_IN_SEGMENT_HASH)
        }

        private const val GET_POINT_PATH_HASH = 1562654675L
        private val getPointPathBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "get_point_path", GET_POINT_PATH_HASH)
        }

        private const val GET_ID_PATH_HASH = 3136199648L
        private val getIdPathBind by lazy {
            ObjectCalls.getMethodBind("AStar3D", "get_id_path", GET_ID_PATH_HASH)
        }
    }
}
