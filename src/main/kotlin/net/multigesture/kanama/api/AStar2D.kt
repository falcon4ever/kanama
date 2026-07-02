package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * An implementation of A* for finding the shortest path between two vertices on a connected graph
 * in 2D space.
 *
 * Generated from Godot docs: AStar2D
 */
class AStar2D(handle: MemorySegment) : RefCounted(handle) {
    var neighborFilterEnabled: Boolean
        @JvmName("neighborFilterEnabledProperty")
        get() = isNeighborFilterEnabled()
        @JvmName("setNeighborFilterEnabledProperty")
        set(value) = setNeighborFilterEnabled(value)

    fun getAvailablePointId(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAvailablePointIdBind, handle)
    }

    fun addPoint(id: Long, position: Vector2, weightScale: Double = 1.0) {
        ObjectCalls.ptrcallWithLongVector2AndDoubleArgs(addPointBind, handle, id, position, weightScale)
    }

    fun getPointPosition(id: Long): Vector2 {
        return ObjectCalls.ptrcallWithLongArgRetVector2(getPointPositionBind, handle, id)
    }

    fun setPointPosition(id: Long, position: Vector2) {
        ObjectCalls.ptrcallWithLongAndVector2Arg(setPointPositionBind, handle, id, position)
    }

    fun getPointWeightScale(id: Long): Double {
        return ObjectCalls.ptrcallWithLongArgRetDouble(getPointWeightScaleBind, handle, id)
    }

    fun setPointWeightScale(id: Long, weightScale: Double) {
        ObjectCalls.ptrcallWithLongAndDoubleArg(setPointWeightScaleBind, handle, id, weightScale)
    }

    fun removePoint(id: Long) {
        ObjectCalls.ptrcallWithLongArg(removePointBind, handle, id)
    }

    fun hasPoint(id: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(hasPointBind, handle, id)
    }

    fun getPointConnections(id: Long): List<Long> {
        return ObjectCalls.ptrcallWithLongArgRetPackedInt64List(getPointConnectionsBind, handle, id)
    }

    fun getPointIds(): List<Long> {
        return ObjectCalls.ptrcallNoArgsRetPackedInt64List(getPointIdsBind, handle)
    }

    fun setNeighborFilterEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setNeighborFilterEnabledBind, handle, enabled)
    }

    fun isNeighborFilterEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNeighborFilterEnabledBind, handle)
    }

    fun setPointDisabled(id: Long, disabled: Boolean = true) {
        ObjectCalls.ptrcallWithLongAndBoolArgs(setPointDisabledBind, handle, id, disabled)
    }

    fun isPointDisabled(id: Long): Boolean {
        return ObjectCalls.ptrcallWithLongArgRetBool(isPointDisabledBind, handle, id)
    }

    fun connectPoints(id: Long, toId: Long, bidirectional: Boolean = true) {
        ObjectCalls.ptrcallWithTwoLongAndBoolArgs(connectPointsBind, handle, id, toId, bidirectional)
    }

    fun disconnectPoints(id: Long, toId: Long, bidirectional: Boolean = true) {
        ObjectCalls.ptrcallWithTwoLongAndBoolArgs(disconnectPointsBind, handle, id, toId, bidirectional)
    }

    fun arePointsConnected(id: Long, toId: Long, bidirectional: Boolean = true): Boolean {
        return ObjectCalls.ptrcallWithTwoLongAndBoolArgsRetBool(arePointsConnectedBind, handle, id, toId, bidirectional)
    }

    fun getPointCount(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPointCountBind, handle)
    }

    fun getPointCapacity(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPointCapacityBind, handle)
    }

    fun reserveSpace(numNodes: Long) {
        ObjectCalls.ptrcallWithLongArg(reserveSpaceBind, handle, numNodes)
    }

    /**
     * Clears all the points and segments.
     *
     * Generated from Godot docs: AStar2D.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun getClosestPoint(toPosition: Vector2, includeDisabled: Boolean = false): Long {
        return ObjectCalls.ptrcallWithVector2AndBoolArgRetLong(getClosestPointBind, handle, toPosition, includeDisabled)
    }

    fun getClosestPositionInSegment(toPosition: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2(getClosestPositionInSegmentBind, handle, toPosition)
    }

    fun getPointPath(fromId: Long, toId: Long, allowPartialPath: Boolean = false): List<Vector2> {
        return ObjectCalls.ptrcallWithTwoLongAndBoolArgsRetPackedVector2List(getPointPathBind, handle, fromId, toId, allowPartialPath)
    }

    fun getIdPath(fromId: Long, toId: Long, allowPartialPath: Boolean = false): List<Long> {
        return ObjectCalls.ptrcallWithTwoLongAndBoolArgsRetPackedInt64List(getIdPathBind, handle, fromId, toId, allowPartialPath)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): AStar2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AStar2D? =
            if (handle.address() == 0L) null else AStar2D(handle)

        private const val GET_AVAILABLE_POINT_ID_HASH = 3905245786L
        private val getAvailablePointIdBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "get_available_point_id", GET_AVAILABLE_POINT_ID_HASH)
        }

        private const val ADD_POINT_HASH = 4074201818L
        private val addPointBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "add_point", ADD_POINT_HASH)
        }

        private const val GET_POINT_POSITION_HASH = 2299179447L
        private val getPointPositionBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "get_point_position", GET_POINT_POSITION_HASH)
        }

        private const val SET_POINT_POSITION_HASH = 163021252L
        private val setPointPositionBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "set_point_position", SET_POINT_POSITION_HASH)
        }

        private const val GET_POINT_WEIGHT_SCALE_HASH = 2339986948L
        private val getPointWeightScaleBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "get_point_weight_scale", GET_POINT_WEIGHT_SCALE_HASH)
        }

        private const val SET_POINT_WEIGHT_SCALE_HASH = 1602489585L
        private val setPointWeightScaleBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "set_point_weight_scale", SET_POINT_WEIGHT_SCALE_HASH)
        }

        private const val REMOVE_POINT_HASH = 1286410249L
        private val removePointBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "remove_point", REMOVE_POINT_HASH)
        }

        private const val HAS_POINT_HASH = 1116898809L
        private val hasPointBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "has_point", HAS_POINT_HASH)
        }

        private const val GET_POINT_CONNECTIONS_HASH = 2865087369L
        private val getPointConnectionsBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "get_point_connections", GET_POINT_CONNECTIONS_HASH)
        }

        private const val GET_POINT_IDS_HASH = 3851388692L
        private val getPointIdsBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "get_point_ids", GET_POINT_IDS_HASH)
        }

        private const val SET_NEIGHBOR_FILTER_ENABLED_HASH = 2586408642L
        private val setNeighborFilterEnabledBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "set_neighbor_filter_enabled", SET_NEIGHBOR_FILTER_ENABLED_HASH)
        }

        private const val IS_NEIGHBOR_FILTER_ENABLED_HASH = 36873697L
        private val isNeighborFilterEnabledBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "is_neighbor_filter_enabled", IS_NEIGHBOR_FILTER_ENABLED_HASH)
        }

        private const val SET_POINT_DISABLED_HASH = 972357352L
        private val setPointDisabledBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "set_point_disabled", SET_POINT_DISABLED_HASH)
        }

        private const val IS_POINT_DISABLED_HASH = 1116898809L
        private val isPointDisabledBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "is_point_disabled", IS_POINT_DISABLED_HASH)
        }

        private const val CONNECT_POINTS_HASH = 3710494224L
        private val connectPointsBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "connect_points", CONNECT_POINTS_HASH)
        }

        private const val DISCONNECT_POINTS_HASH = 3710494224L
        private val disconnectPointsBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "disconnect_points", DISCONNECT_POINTS_HASH)
        }

        private const val ARE_POINTS_CONNECTED_HASH = 2288175859L
        private val arePointsConnectedBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "are_points_connected", ARE_POINTS_CONNECTED_HASH)
        }

        private const val GET_POINT_COUNT_HASH = 3905245786L
        private val getPointCountBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "get_point_count", GET_POINT_COUNT_HASH)
        }

        private const val GET_POINT_CAPACITY_HASH = 3905245786L
        private val getPointCapacityBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "get_point_capacity", GET_POINT_CAPACITY_HASH)
        }

        private const val RESERVE_SPACE_HASH = 1286410249L
        private val reserveSpaceBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "reserve_space", RESERVE_SPACE_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "clear", CLEAR_HASH)
        }

        private const val GET_CLOSEST_POINT_HASH = 2300324924L
        private val getClosestPointBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "get_closest_point", GET_CLOSEST_POINT_HASH)
        }

        private const val GET_CLOSEST_POSITION_IN_SEGMENT_HASH = 2656412154L
        private val getClosestPositionInSegmentBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "get_closest_position_in_segment", GET_CLOSEST_POSITION_IN_SEGMENT_HASH)
        }

        private const val GET_POINT_PATH_HASH = 3427490392L
        private val getPointPathBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "get_point_path", GET_POINT_PATH_HASH)
        }

        private const val GET_ID_PATH_HASH = 3136199648L
        private val getIdPathBind by lazy {
            ObjectCalls.getMethodBind("AStar2D", "get_id_path", GET_ID_PATH_HASH)
        }
    }
}
