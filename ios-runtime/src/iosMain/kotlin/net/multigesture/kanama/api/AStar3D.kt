package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: AStar3D
 */
class AStar3D(handle: MemorySegment) : RefCounted(handle) {
    var neighborFilterEnabled: Boolean
        @JvmName("neighborFilterEnabledProperty")
        get() = isNeighborFilterEnabled()
        @JvmName("setNeighborFilterEnabledProperty")
        set(value) = setNeighborFilterEnabled(value)

    fun getAvailablePointId(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getAvailablePointIdBind, handle)
    }

    fun addPoint(id: Long, position: Vector3, weightScale: Double = 1.0) {
        checkOpen()
        ObjectCalls.ptrcallWithLongVector3AndDoubleArgs(addPointBind, handle, id, position, weightScale)
    }

    fun getPointPosition(id: Long): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetVector3(getPointPositionBind, handle, id)
    }

    fun setPointPosition(id: Long, position: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndVector3Arg(setPointPositionBind, handle, id, position)
    }

    fun getPointWeightScale(id: Long): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetDouble(getPointWeightScaleBind, handle, id)
    }

    fun setPointWeightScale(id: Long, weightScale: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndDoubleArg(setPointWeightScaleBind, handle, id, weightScale)
    }

    fun removePoint(id: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(removePointBind, handle, id)
    }

    fun hasPoint(id: Long): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetBool(hasPointBind, handle, id)
    }

    fun setPointDisabled(id: Long, disabled: Boolean = true) {
        checkOpen()
        ObjectCalls.ptrcallWithLongAndBoolArgs(setPointDisabledBind, handle, id, disabled)
    }

    fun isPointDisabled(id: Long): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithLongArgRetBool(isPointDisabledBind, handle, id)
    }

    fun setNeighborFilterEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setNeighborFilterEnabledBind, handle, enabled)
    }

    fun isNeighborFilterEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isNeighborFilterEnabledBind, handle)
    }

    fun connectPoints(id: Long, toId: Long, bidirectional: Boolean = true) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoLongAndBoolArgs(connectPointsBind, handle, id, toId, bidirectional)
    }

    fun disconnectPoints(id: Long, toId: Long, bidirectional: Boolean = true) {
        checkOpen()
        ObjectCalls.ptrcallWithTwoLongAndBoolArgs(disconnectPointsBind, handle, id, toId, bidirectional)
    }

    fun arePointsConnected(id: Long, toId: Long, bidirectional: Boolean = true): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoLongAndBoolArgsRetBool(arePointsConnectedBind, handle, id, toId, bidirectional)
    }

    fun getPointCount(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getPointCountBind, handle)
    }

    fun getPointCapacity(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getPointCapacityBind, handle)
    }

    fun reserveSpace(numNodes: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(reserveSpaceBind, handle, numNodes)
    }

    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun getClosestPoint(toPosition: Vector3, includeDisabled: Boolean = false): Long {
        checkOpen()
        return ObjectCalls.ptrcallWithVector3AndBoolArgRetLong(getClosestPointBind, handle, toPosition, includeDisabled)
    }

    fun getClosestPositionInSegment(toPosition: Vector3): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallWithVector3ArgRetVector3(getClosestPositionInSegmentBind, handle, toPosition)
    }

    companion object {
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
    }
}
