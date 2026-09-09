package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * Generated from Godot docs: AStarGrid2D
 */
class AStarGrid2D(handle: MemorySegment) : RefCounted(handle) {
    val region: Rect2i
        @JvmName("regionProperty")
        get() = getRegion()

    var size: Vector2i
        @JvmName("sizeProperty")
        get() = getSize()
        @JvmName("setSizeProperty")
        set(value) = setSize(value)

    var offset: Vector2
        @JvmName("offsetProperty")
        get() = getOffset()
        @JvmName("setOffsetProperty")
        set(value) = setOffset(value)

    var cellSize: Vector2
        @JvmName("cellSizeProperty")
        get() = getCellSize()
        @JvmName("setCellSizeProperty")
        set(value) = setCellSize(value)

    var cellShape: Long
        @JvmName("cellShapeProperty")
        get() = getCellShape()
        @JvmName("setCellShapeProperty")
        set(value) = setCellShape(value)

    var jumpingEnabled: Boolean
        @JvmName("jumpingEnabledProperty")
        get() = isJumpingEnabled()
        @JvmName("setJumpingEnabledProperty")
        set(value) = setJumpingEnabled(value)

    var defaultComputeHeuristic: Long
        @JvmName("defaultComputeHeuristicProperty")
        get() = getDefaultComputeHeuristic()
        @JvmName("setDefaultComputeHeuristicProperty")
        set(value) = setDefaultComputeHeuristic(value)

    var defaultEstimateHeuristic: Long
        @JvmName("defaultEstimateHeuristicProperty")
        get() = getDefaultEstimateHeuristic()
        @JvmName("setDefaultEstimateHeuristicProperty")
        set(value) = setDefaultEstimateHeuristic(value)

    var diagonalMode: Long
        @JvmName("diagonalModeProperty")
        get() = getDiagonalMode()
        @JvmName("setDiagonalModeProperty")
        set(value) = setDiagonalMode(value)

    fun getRegion(): Rect2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetRect2i(getRegionBind, handle)
    }

    fun setSize(size: Vector2i) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iArg(setSizeBind, handle, size)
    }

    fun getSize(): Vector2i {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSizeBind, handle)
    }

    fun setOffset(offset: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, handle, offset)
    }

    fun getOffset(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    fun setCellSize(cellSize: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setCellSizeBind, handle, cellSize)
    }

    fun getCellSize(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getCellSizeBind, handle)
    }

    fun setCellShape(cellShape: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setCellShapeBind, handle, cellShape)
    }

    fun getCellShape(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getCellShapeBind, handle)
    }

    fun isInBounds(x: Int, y: Int): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(isInBoundsBind, handle, x, y)
    }

    fun isInBoundsv(id: Vector2i): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2iArgRetBool(isInBoundsvBind, handle, id)
    }

    fun isDirty(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isDirtyBind, handle)
    }

    fun update() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(updateBind, handle)
    }

    fun setJumpingEnabled(enabled: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setJumpingEnabledBind, handle, enabled)
    }

    fun isJumpingEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isJumpingEnabledBind, handle)
    }

    fun setDiagonalMode(mode: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDiagonalModeBind, handle, mode)
    }

    fun getDiagonalMode(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getDiagonalModeBind, handle)
    }

    fun setDefaultComputeHeuristic(heuristic: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDefaultComputeHeuristicBind, handle, heuristic)
    }

    fun getDefaultComputeHeuristic(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getDefaultComputeHeuristicBind, handle)
    }

    fun setDefaultEstimateHeuristic(heuristic: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithLongArg(setDefaultEstimateHeuristicBind, handle, heuristic)
    }

    fun getDefaultEstimateHeuristic(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetLong(getDefaultEstimateHeuristicBind, handle)
    }

    fun setPointSolid(id: Vector2i, solid: Boolean = true) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iAndBoolArg(setPointSolidBind, handle, id, solid)
    }

    fun isPointSolid(id: Vector2i): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2iArgRetBool(isPointSolidBind, handle, id)
    }

    fun setPointWeightScale(id: Vector2i, weightScale: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2iAndDoubleArg(setPointWeightScaleBind, handle, id, weightScale)
    }

    fun getPointWeightScale(id: Vector2i): Double {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2iArgRetDouble(getPointWeightScaleBind, handle, id)
    }

    fun clear() {
        checkOpen()
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun getPointPosition(id: Vector2i): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallWithVector2iArgRetVector2(getPointPositionBind, handle, id)
    }

    companion object {
        const val HEURISTIC_EUCLIDEAN: Long = 0L
        const val HEURISTIC_MANHATTAN: Long = 1L
        const val HEURISTIC_OCTILE: Long = 2L
        const val HEURISTIC_CHEBYSHEV: Long = 3L
        const val HEURISTIC_MAX: Long = 4L
        const val DIAGONAL_MODE_ALWAYS: Long = 0L
        const val DIAGONAL_MODE_NEVER: Long = 1L
        const val DIAGONAL_MODE_AT_LEAST_ONE_WALKABLE: Long = 2L
        const val DIAGONAL_MODE_ONLY_IF_NO_OBSTACLES: Long = 3L
        const val DIAGONAL_MODE_MAX: Long = 4L
        const val CELL_SHAPE_SQUARE: Long = 0L
        const val CELL_SHAPE_ISOMETRIC_RIGHT: Long = 1L
        const val CELL_SHAPE_ISOMETRIC_DOWN: Long = 2L
        const val CELL_SHAPE_MAX: Long = 3L

        fun fromHandle(handle: MemorySegment): AStarGrid2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AStarGrid2D? =
            if (handle.address() == 0L) null else AStarGrid2D(handle)

        private const val GET_REGION_HASH = 410525958L
        private val getRegionBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_region", GET_REGION_HASH)
        }

        private const val SET_SIZE_HASH = 1130785943L
        private val setSizeBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "set_size", SET_SIZE_HASH)
        }

        private const val GET_SIZE_HASH = 3690982128L
        private val getSizeBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_size", GET_SIZE_HASH)
        }

        private const val SET_OFFSET_HASH = 743155724L
        private val setOffsetBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "set_offset", SET_OFFSET_HASH)
        }

        private const val GET_OFFSET_HASH = 3341600327L
        private val getOffsetBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_offset", GET_OFFSET_HASH)
        }

        private const val SET_CELL_SIZE_HASH = 743155724L
        private val setCellSizeBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "set_cell_size", SET_CELL_SIZE_HASH)
        }

        private const val GET_CELL_SIZE_HASH = 3341600327L
        private val getCellSizeBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_cell_size", GET_CELL_SIZE_HASH)
        }

        private const val SET_CELL_SHAPE_HASH = 4130591146L
        private val setCellShapeBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "set_cell_shape", SET_CELL_SHAPE_HASH)
        }

        private const val GET_CELL_SHAPE_HASH = 3293463634L
        private val getCellShapeBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_cell_shape", GET_CELL_SHAPE_HASH)
        }

        private const val IS_IN_BOUNDS_HASH = 2522259332L
        private val isInBoundsBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "is_in_bounds", IS_IN_BOUNDS_HASH)
        }

        private const val IS_IN_BOUNDSV_HASH = 3900751641L
        private val isInBoundsvBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "is_in_boundsv", IS_IN_BOUNDSV_HASH)
        }

        private const val IS_DIRTY_HASH = 36873697L
        private val isDirtyBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "is_dirty", IS_DIRTY_HASH)
        }

        private const val UPDATE_HASH = 3218959716L
        private val updateBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "update", UPDATE_HASH)
        }

        private const val SET_JUMPING_ENABLED_HASH = 2586408642L
        private val setJumpingEnabledBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "set_jumping_enabled", SET_JUMPING_ENABLED_HASH)
        }

        private const val IS_JUMPING_ENABLED_HASH = 36873697L
        private val isJumpingEnabledBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "is_jumping_enabled", IS_JUMPING_ENABLED_HASH)
        }

        private const val SET_DIAGONAL_MODE_HASH = 1017829798L
        private val setDiagonalModeBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "set_diagonal_mode", SET_DIAGONAL_MODE_HASH)
        }

        private const val GET_DIAGONAL_MODE_HASH = 3129282674L
        private val getDiagonalModeBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_diagonal_mode", GET_DIAGONAL_MODE_HASH)
        }

        private const val SET_DEFAULT_COMPUTE_HEURISTIC_HASH = 1044375519L
        private val setDefaultComputeHeuristicBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "set_default_compute_heuristic", SET_DEFAULT_COMPUTE_HEURISTIC_HASH)
        }

        private const val GET_DEFAULT_COMPUTE_HEURISTIC_HASH = 2074731422L
        private val getDefaultComputeHeuristicBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_default_compute_heuristic", GET_DEFAULT_COMPUTE_HEURISTIC_HASH)
        }

        private const val SET_DEFAULT_ESTIMATE_HEURISTIC_HASH = 1044375519L
        private val setDefaultEstimateHeuristicBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "set_default_estimate_heuristic", SET_DEFAULT_ESTIMATE_HEURISTIC_HASH)
        }

        private const val GET_DEFAULT_ESTIMATE_HEURISTIC_HASH = 2074731422L
        private val getDefaultEstimateHeuristicBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_default_estimate_heuristic", GET_DEFAULT_ESTIMATE_HEURISTIC_HASH)
        }

        private const val SET_POINT_SOLID_HASH = 1765703753L
        private val setPointSolidBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "set_point_solid", SET_POINT_SOLID_HASH)
        }

        private const val IS_POINT_SOLID_HASH = 3900751641L
        private val isPointSolidBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "is_point_solid", IS_POINT_SOLID_HASH)
        }

        private const val SET_POINT_WEIGHT_SCALE_HASH = 2262553149L
        private val setPointWeightScaleBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "set_point_weight_scale", SET_POINT_WEIGHT_SCALE_HASH)
        }

        private const val GET_POINT_WEIGHT_SCALE_HASH = 719993801L
        private val getPointWeightScaleBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_point_weight_scale", GET_POINT_WEIGHT_SCALE_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "clear", CLEAR_HASH)
        }

        private const val GET_POINT_POSITION_HASH = 108438297L
        private val getPointPositionBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_point_position", GET_POINT_POSITION_HASH)
        }
    }
}
