package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2i
import net.multigesture.kanama.types.Vector2
import net.multigesture.kanama.types.Vector2i

/**
 * An implementation of A* for finding the shortest path between two points on a partial 2D grid.
 *
 * Generated from Godot docs: AStarGrid2D
 */
class AStarGrid2D(handle: MemorySegment) : RefCounted(handle) {
    var region: Rect2i
        @JvmName("regionProperty")
        get() = getRegion()
        @JvmName("setRegionProperty")
        set(value) = setRegion(value)

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

    fun setRegion(region: Rect2i) {
        ObjectCalls.ptrcallWithRect2iArg(setRegionBind, handle, region)
    }

    fun getRegion(): Rect2i {
        return ObjectCalls.ptrcallNoArgsRetRect2i(getRegionBind, handle)
    }

    fun setSize(size: Vector2i) {
        ObjectCalls.ptrcallWithVector2iArg(setSizeBind, handle, size)
    }

    fun getSize(): Vector2i {
        return ObjectCalls.ptrcallNoArgsRetVector2i(getSizeBind, handle)
    }

    fun setOffset(offset: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setOffsetBind, handle, offset)
    }

    fun getOffset(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getOffsetBind, handle)
    }

    fun setCellSize(cellSize: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setCellSizeBind, handle, cellSize)
    }

    fun getCellSize(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCellSizeBind, handle)
    }

    fun setCellShape(cellShape: Long) {
        ObjectCalls.ptrcallWithLongArg(setCellShapeBind, handle, cellShape)
    }

    fun getCellShape(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCellShapeBind, handle)
    }

    fun isInBounds(x: Int, y: Int): Boolean {
        return ObjectCalls.ptrcallWithTwoIntArgsRetBool(isInBoundsBind, handle, x, y)
    }

    fun isInBoundsv(id: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithVector2iArgRetBool(isInBoundsvBind, handle, id)
    }

    fun isDirty(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isDirtyBind, handle)
    }

    /**
     * Updates the internal state of the grid according to the parameters to prepare it to search the
     * path. Needs to be called if parameters like `region`, `cell_size` or `offset` are changed.
     * `is_dirty` will return `true` if this is the case and this needs to be called. Note: All point
     * data (solidity and weight scale) will be cleared.
     *
     * Generated from Godot docs: AStarGrid2D.update
     */
    fun update() {
        ObjectCalls.ptrcallNoArgs(updateBind, handle)
    }

    fun setJumpingEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setJumpingEnabledBind, handle, enabled)
    }

    fun isJumpingEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isJumpingEnabledBind, handle)
    }

    fun setDiagonalMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setDiagonalModeBind, handle, mode)
    }

    fun getDiagonalMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDiagonalModeBind, handle)
    }

    fun setDefaultComputeHeuristic(heuristic: Long) {
        ObjectCalls.ptrcallWithLongArg(setDefaultComputeHeuristicBind, handle, heuristic)
    }

    fun getDefaultComputeHeuristic(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDefaultComputeHeuristicBind, handle)
    }

    fun setDefaultEstimateHeuristic(heuristic: Long) {
        ObjectCalls.ptrcallWithLongArg(setDefaultEstimateHeuristicBind, handle, heuristic)
    }

    fun getDefaultEstimateHeuristic(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getDefaultEstimateHeuristicBind, handle)
    }

    fun setPointSolid(id: Vector2i, solid: Boolean = true) {
        ObjectCalls.ptrcallWithVector2iAndBoolArg(setPointSolidBind, handle, id, solid)
    }

    fun isPointSolid(id: Vector2i): Boolean {
        return ObjectCalls.ptrcallWithVector2iArgRetBool(isPointSolidBind, handle, id)
    }

    fun setPointWeightScale(id: Vector2i, weightScale: Double) {
        ObjectCalls.ptrcallWithVector2iAndDoubleArg(setPointWeightScaleBind, handle, id, weightScale)
    }

    fun getPointWeightScale(id: Vector2i): Double {
        return ObjectCalls.ptrcallWithVector2iArgRetDouble(getPointWeightScaleBind, handle, id)
    }

    fun fillSolidRegion(region: Rect2i, solid: Boolean = true) {
        ObjectCalls.ptrcallWithRect2iAndBoolArg(fillSolidRegionBind, handle, region, solid)
    }

    fun fillWeightScaleRegion(region: Rect2i, weightScale: Double) {
        ObjectCalls.ptrcallWithRect2iAndDoubleArg(fillWeightScaleRegionBind, handle, region, weightScale)
    }

    /**
     * Clears the grid and sets the `region` to `Rect2i(0, 0, 0, 0)`.
     *
     * Generated from Godot docs: AStarGrid2D.clear
     */
    fun clear() {
        ObjectCalls.ptrcallNoArgs(clearBind, handle)
    }

    fun getPointPosition(id: Vector2i): Vector2 {
        return ObjectCalls.ptrcallWithVector2iArgRetVector2(getPointPositionBind, handle, id)
    }

    fun getPointDataInRegion(region: Rect2i): List<Map<String, Any?>> {
        return ObjectCalls.ptrcallWithRect2iArgRetDictionaryList(getPointDataInRegionBind, handle, region)
    }

    fun getPointPath(fromId: Vector2i, toId: Vector2i, allowPartialPath: Boolean = false): List<Vector2> {
        return ObjectCalls.ptrcallWithTwoVector2iAndBoolArgsRetPackedVector2List(getPointPathBind, handle, fromId, toId, allowPartialPath)
    }

    fun getIdPath(fromId: Vector2i, toId: Vector2i, allowPartialPath: Boolean = false): List<Vector2i> {
        return ObjectCalls.ptrcallWithTwoVector2iAndBoolArgsRetVector2iList(getIdPathBind, handle, fromId, toId, allowPartialPath)
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

        @JvmStatic
        fun fromHandle(handle: MemorySegment): AStarGrid2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): AStarGrid2D? =
            if (handle.address() == 0L) null else AStarGrid2D(handle)

        private const val SET_REGION_HASH = 1763793166L
        private val setRegionBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "set_region", SET_REGION_HASH)
        }

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

        private const val FILL_SOLID_REGION_HASH = 2261970063L
        private val fillSolidRegionBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "fill_solid_region", FILL_SOLID_REGION_HASH)
        }

        private const val FILL_WEIGHT_SCALE_REGION_HASH = 2793244083L
        private val fillWeightScaleRegionBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "fill_weight_scale_region", FILL_WEIGHT_SCALE_REGION_HASH)
        }

        private const val CLEAR_HASH = 3218959716L
        private val clearBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "clear", CLEAR_HASH)
        }

        private const val GET_POINT_POSITION_HASH = 108438297L
        private val getPointPositionBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_point_position", GET_POINT_POSITION_HASH)
        }

        private const val GET_POINT_DATA_IN_REGION_HASH = 3893818462L
        private val getPointDataInRegionBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_point_data_in_region", GET_POINT_DATA_IN_REGION_HASH)
        }

        private const val GET_POINT_PATH_HASH = 1641925693L
        private val getPointPathBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_point_path", GET_POINT_PATH_HASH)
        }

        private const val GET_ID_PATH_HASH = 1918132273L
        private val getIdPathBind by lazy {
            ObjectCalls.getMethodBind("AStarGrid2D", "get_id_path", GET_ID_PATH_HASH)
        }
    }
}
