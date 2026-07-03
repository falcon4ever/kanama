package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Rect2
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: PolygonPathFinder
 */
class PolygonPathFinder(handle: MemorySegment) : Resource(handle) {
    /**
     * Sets up `PolygonPathFinder` with an array of points that define the vertices of the polygon, and
     * an array of indices that determine the edges of the polygon. The length of `connections` must be
     * even, returns an error if odd.
     *
     * Generated from Godot docs: PolygonPathFinder.setup
     */
    fun setup(points: List<Vector2>, connections: List<Int>) {
        ObjectCalls.ptrcallWithPackedVector2ListAndPackedInt32ListArgs(setupBind, handle, points, connections)
    }

    fun findPath(from: Vector2, to: Vector2): List<Vector2> {
        return ObjectCalls.ptrcallWithTwoVector2ArgsRetPackedVector2List(findPathBind, handle, from, to)
    }

    fun getIntersections(from: Vector2, to: Vector2): List<Vector2> {
        return ObjectCalls.ptrcallWithTwoVector2ArgsRetPackedVector2List(getIntersectionsBind, handle, from, to)
    }

    fun getClosestPoint(point: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2(getClosestPointBind, handle, point)
    }

    /**
     * Returns `true` if `point` falls inside the polygon area.
     *
     * Generated from Godot docs: PolygonPathFinder.is_point_inside
     */
    fun isPointInside(point: Vector2): Boolean {
        return ObjectCalls.ptrcallWithVector2ArgRetBool(isPointInsideBind, handle, point)
    }

    fun setPointPenalty(idx: Int, penalty: Double) {
        ObjectCalls.ptrcallWithIntAndDoubleArg(setPointPenaltyBind, handle, idx, penalty)
    }

    fun getPointPenalty(idx: Int): Double {
        return ObjectCalls.ptrcallWithIntArgRetDouble(getPointPenaltyBind, handle, idx)
    }

    fun getBounds(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getBoundsBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PolygonPathFinder? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PolygonPathFinder? =
            if (handle.address() == 0L) null else PolygonPathFinder(handle)

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

        private const val GET_CLOSEST_POINT_HASH = 2656412154L
        private val getClosestPointBind by lazy {
            ObjectCalls.getMethodBind("PolygonPathFinder", "get_closest_point", GET_CLOSEST_POINT_HASH)
        }

        private const val IS_POINT_INSIDE_HASH = 556197845L
        private val isPointInsideBind by lazy {
            ObjectCalls.getMethodBind("PolygonPathFinder", "is_point_inside", IS_POINT_INSIDE_HASH)
        }

        private const val SET_POINT_PENALTY_HASH = 1602489585L
        private val setPointPenaltyBind by lazy {
            ObjectCalls.getMethodBind("PolygonPathFinder", "set_point_penalty", SET_POINT_PENALTY_HASH)
        }

        private const val GET_POINT_PENALTY_HASH = 2339986948L
        private val getPointPenaltyBind by lazy {
            ObjectCalls.getMethodBind("PolygonPathFinder", "get_point_penalty", GET_POINT_PENALTY_HASH)
        }

        private const val GET_BOUNDS_HASH = 1639390495L
        private val getBoundsBind by lazy {
            ObjectCalls.getMethodBind("PolygonPathFinder", "get_bounds", GET_BOUNDS_HASH)
        }
    }
}
