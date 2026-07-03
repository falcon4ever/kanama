package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * Provides parameters for 3D navigation path queries.
 *
 * Generated from Godot docs: NavigationPathQueryParameters3D
 */
class NavigationPathQueryParameters3D(handle: MemorySegment) : RefCounted(handle) {
    var map: RID
        @JvmName("mapProperty")
        get() = getMap()
        @JvmName("setMapProperty")
        set(value) = setMap(value)

    var startPosition: Vector3
        @JvmName("startPositionProperty")
        get() = getStartPosition()
        @JvmName("setStartPositionProperty")
        set(value) = setStartPosition(value)

    var targetPosition: Vector3
        @JvmName("targetPositionProperty")
        get() = getTargetPosition()
        @JvmName("setTargetPositionProperty")
        set(value) = setTargetPosition(value)

    var navigationLayers: Long
        @JvmName("navigationLayersProperty")
        get() = getNavigationLayers()
        @JvmName("setNavigationLayersProperty")
        set(value) = setNavigationLayers(value)

    var pathfindingAlgorithm: Long
        @JvmName("pathfindingAlgorithmProperty")
        get() = getPathfindingAlgorithm()
        @JvmName("setPathfindingAlgorithmProperty")
        set(value) = setPathfindingAlgorithm(value)

    var pathPostprocessing: Long
        @JvmName("pathPostprocessingProperty")
        get() = getPathPostprocessing()
        @JvmName("setPathPostprocessingProperty")
        set(value) = setPathPostprocessing(value)

    var metadataFlags: Long
        @JvmName("metadataFlagsProperty")
        get() = getMetadataFlags()
        @JvmName("setMetadataFlagsProperty")
        set(value) = setMetadataFlags(value)

    var simplifyPath: Boolean
        @JvmName("simplifyPathProperty")
        get() = getSimplifyPath()
        @JvmName("setSimplifyPathProperty")
        set(value) = setSimplifyPath(value)

    var simplifyEpsilon: Double
        @JvmName("simplifyEpsilonProperty")
        get() = getSimplifyEpsilon()
        @JvmName("setSimplifyEpsilonProperty")
        set(value) = setSimplifyEpsilon(value)

    var excludedRegions: List<RID>
        @JvmName("excludedRegionsProperty")
        get() = getExcludedRegions()
        @JvmName("setExcludedRegionsProperty")
        set(value) = setExcludedRegions(value)

    var includedRegions: List<RID>
        @JvmName("includedRegionsProperty")
        get() = getIncludedRegions()
        @JvmName("setIncludedRegionsProperty")
        set(value) = setIncludedRegions(value)

    var pathReturnMaxLength: Double
        @JvmName("pathReturnMaxLengthProperty")
        get() = getPathReturnMaxLength()
        @JvmName("setPathReturnMaxLengthProperty")
        set(value) = setPathReturnMaxLength(value)

    var pathReturnMaxRadius: Double
        @JvmName("pathReturnMaxRadiusProperty")
        get() = getPathReturnMaxRadius()
        @JvmName("setPathReturnMaxRadiusProperty")
        set(value) = setPathReturnMaxRadius(value)

    var pathSearchMaxPolygons: Int
        @JvmName("pathSearchMaxPolygonsProperty")
        get() = getPathSearchMaxPolygons()
        @JvmName("setPathSearchMaxPolygonsProperty")
        set(value) = setPathSearchMaxPolygons(value)

    var pathSearchMaxDistance: Double
        @JvmName("pathSearchMaxDistanceProperty")
        get() = getPathSearchMaxDistance()
        @JvmName("setPathSearchMaxDistanceProperty")
        set(value) = setPathSearchMaxDistance(value)

    /**
     * The pathfinding algorithm used in the path query.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_pathfinding_algorithm
     */
    fun setPathfindingAlgorithm(pathfindingAlgorithm: Long) {
        ObjectCalls.ptrcallWithLongArg(setPathfindingAlgorithmBind, handle, pathfindingAlgorithm)
    }

    /**
     * The pathfinding algorithm used in the path query.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_pathfinding_algorithm
     */
    fun getPathfindingAlgorithm(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPathfindingAlgorithmBind, handle)
    }

    /**
     * The path postprocessing applied to the raw path corridor found by the `pathfinding_algorithm`.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_path_postprocessing
     */
    fun setPathPostprocessing(pathPostprocessing: Long) {
        ObjectCalls.ptrcallWithLongArg(setPathPostprocessingBind, handle, pathPostprocessing)
    }

    /**
     * The path postprocessing applied to the raw path corridor found by the `pathfinding_algorithm`.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_path_postprocessing
     */
    fun getPathPostprocessing(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPathPostprocessingBind, handle)
    }

    /**
     * The navigation map `RID` used in the path query.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_map
     */
    fun setMap(map: RID) {
        ObjectCalls.ptrcallWithRIDArg(setMapBind, handle, map)
    }

    /**
     * The navigation map `RID` used in the path query.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_map
     */
    fun getMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getMapBind, handle)
    }

    /**
     * The pathfinding start position in global coordinates.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_start_position
     */
    fun setStartPosition(startPosition: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setStartPositionBind, handle, startPosition)
    }

    /**
     * The pathfinding start position in global coordinates.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_start_position
     */
    fun getStartPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getStartPositionBind, handle)
    }

    /**
     * The pathfinding target position in global coordinates.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_target_position
     */
    fun setTargetPosition(targetPosition: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setTargetPositionBind, handle, targetPosition)
    }

    /**
     * The pathfinding target position in global coordinates.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_target_position
     */
    fun getTargetPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getTargetPositionBind, handle)
    }

    /**
     * The navigation layers the query will use (as a bitmask).
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_navigation_layers
     */
    fun setNavigationLayers(navigationLayers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setNavigationLayersBind, handle, navigationLayers)
    }

    /**
     * The navigation layers the query will use (as a bitmask).
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_navigation_layers
     */
    fun getNavigationLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getNavigationLayersBind, handle)
    }

    /**
     * Additional information to include with the navigation path.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_metadata_flags
     */
    fun setMetadataFlags(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setMetadataFlagsBind, handle, flags)
    }

    /**
     * Additional information to include with the navigation path.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_metadata_flags
     */
    fun getMetadataFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMetadataFlagsBind, handle)
    }

    /**
     * If `true` a simplified version of the path will be returned with less critical path points
     * removed. The simplification amount is controlled by `simplify_epsilon`. The simplification uses
     * a variant of Ramer-Douglas-Peucker algorithm for curve point decimation. Path simplification can
     * be helpful to mitigate various path following issues that can arise with certain agent types and
     * script behaviors. E.g. "steering" agents or avoidance in "open fields".
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_simplify_path
     */
    fun setSimplifyPath(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSimplifyPathBind, handle, enabled)
    }

    /**
     * If `true` a simplified version of the path will be returned with less critical path points
     * removed. The simplification amount is controlled by `simplify_epsilon`. The simplification uses
     * a variant of Ramer-Douglas-Peucker algorithm for curve point decimation. Path simplification can
     * be helpful to mitigate various path following issues that can arise with certain agent types and
     * script behaviors. E.g. "steering" agents or avoidance in "open fields".
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_simplify_path
     */
    fun getSimplifyPath(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSimplifyPathBind, handle)
    }

    /**
     * The path simplification amount in worlds units.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_simplify_epsilon
     */
    fun setSimplifyEpsilon(epsilon: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSimplifyEpsilonBind, handle, epsilon)
    }

    /**
     * The path simplification amount in worlds units.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_simplify_epsilon
     */
    fun getSimplifyEpsilon(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSimplifyEpsilonBind, handle)
    }

    /**
     * The list of region `RID`s that will be included by the path query. Use
     * `NavigationRegion3D.get_rid` to get the `RID` associated with a `NavigationRegion3D` node. If
     * left empty all regions are included. If a region ends up being both included and excluded at the
     * same time it will be excluded. Note: The returned array is copied and any changes to it will not
     * update the original property value. To update the value you need to modify the returned array,
     * and then set it to the property again.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_included_regions
     */
    fun setIncludedRegions(regions: List<RID>) {
        ObjectCalls.ptrcallWithRIDListArg(setIncludedRegionsBind, handle, regions)
    }

    /**
     * The list of region `RID`s that will be included by the path query. Use
     * `NavigationRegion3D.get_rid` to get the `RID` associated with a `NavigationRegion3D` node. If
     * left empty all regions are included. If a region ends up being both included and excluded at the
     * same time it will be excluded. Note: The returned array is copied and any changes to it will not
     * update the original property value. To update the value you need to modify the returned array,
     * and then set it to the property again.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_included_regions
     */
    fun getIncludedRegions(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(getIncludedRegionsBind, handle)
    }

    /**
     * The list of region `RID`s that will be excluded from the path query. Use
     * `NavigationRegion3D.get_rid` to get the `RID` associated with a `NavigationRegion3D` node. Note:
     * The returned array is copied and any changes to it will not update the original property value.
     * To update the value you need to modify the returned array, and then set it to the property
     * again.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_excluded_regions
     */
    fun setExcludedRegions(regions: List<RID>) {
        ObjectCalls.ptrcallWithRIDListArg(setExcludedRegionsBind, handle, regions)
    }

    /**
     * The list of region `RID`s that will be excluded from the path query. Use
     * `NavigationRegion3D.get_rid` to get the `RID` associated with a `NavigationRegion3D` node. Note:
     * The returned array is copied and any changes to it will not update the original property value.
     * To update the value you need to modify the returned array, and then set it to the property
     * again.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_excluded_regions
     */
    fun getExcludedRegions(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(getExcludedRegionsBind, handle)
    }

    /**
     * The maximum allowed length of the returned path in world units. A path will be clipped when
     * going over this length. A value of `0` or below counts as disabled.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_path_return_max_length
     */
    fun setPathReturnMaxLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathReturnMaxLengthBind, handle, length)
    }

    /**
     * The maximum allowed length of the returned path in world units. A path will be clipped when
     * going over this length. A value of `0` or below counts as disabled.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_path_return_max_length
     */
    fun getPathReturnMaxLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathReturnMaxLengthBind, handle)
    }

    /**
     * The maximum allowed radius in world units that the returned path can be from the path start. The
     * path will be clipped when going over this radius. A value of `0` or below counts as disabled.
     * Note: This will perform a sphere shaped clip operation on the path with the first path position
     * being the sphere's center position.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_path_return_max_radius
     */
    fun setPathReturnMaxRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathReturnMaxRadiusBind, handle, radius)
    }

    /**
     * The maximum allowed radius in world units that the returned path can be from the path start. The
     * path will be clipped when going over this radius. A value of `0` or below counts as disabled.
     * Note: This will perform a sphere shaped clip operation on the path with the first path position
     * being the sphere's center position.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_path_return_max_radius
     */
    fun getPathReturnMaxRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathReturnMaxRadiusBind, handle)
    }

    /**
     * The maximum number of polygons that are searched before the pathfinding cancels the search for a
     * path to the (possibly unreachable or very far away) target position polygon. In this case the
     * pathfinding resets and builds a path from the start polygon to the polygon that was found
     * closest to the target position so far. A value of `0` or below counts as unlimited. In case of
     * unlimited the pathfinding will search all polygons connected with the start polygon until either
     * the target position polygon is found or all available polygon search options are exhausted.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_path_search_max_polygons
     */
    fun setPathSearchMaxPolygons(maxPolygons: Int) {
        ObjectCalls.ptrcallWithIntArg(setPathSearchMaxPolygonsBind, handle, maxPolygons)
    }

    /**
     * The maximum number of polygons that are searched before the pathfinding cancels the search for a
     * path to the (possibly unreachable or very far away) target position polygon. In this case the
     * pathfinding resets and builds a path from the start polygon to the polygon that was found
     * closest to the target position so far. A value of `0` or below counts as unlimited. In case of
     * unlimited the pathfinding will search all polygons connected with the start polygon until either
     * the target position polygon is found or all available polygon search options are exhausted.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_path_search_max_polygons
     */
    fun getPathSearchMaxPolygons(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPathSearchMaxPolygonsBind, handle)
    }

    /**
     * The maximum distance a searched polygon can be away from the start polygon before the
     * pathfinding cancels the search for a path to the (possibly unreachable or very far away) target
     * position polygon. In this case the pathfinding resets and builds a path from the start polygon
     * to the polygon that was found closest to the target position so far. A value of `0` or below
     * counts as unlimited. In case of unlimited the pathfinding will search all polygons connected
     * with the start polygon until either the target position polygon is found or all available
     * polygon search options are exhausted.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.set_path_search_max_distance
     */
    fun setPathSearchMaxDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathSearchMaxDistanceBind, handle, distance)
    }

    /**
     * The maximum distance a searched polygon can be away from the start polygon before the
     * pathfinding cancels the search for a path to the (possibly unreachable or very far away) target
     * position polygon. In this case the pathfinding resets and builds a path from the start polygon
     * to the polygon that was found closest to the target position so far. A value of `0` or below
     * counts as unlimited. In case of unlimited the pathfinding will search all polygons connected
     * with the start polygon until either the target position polygon is found or all available
     * polygon search options are exhausted.
     *
     * Generated from Godot docs: NavigationPathQueryParameters3D.get_path_search_max_distance
     */
    fun getPathSearchMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathSearchMaxDistanceBind, handle)
    }

    companion object {
        const val PATHFINDING_ALGORITHM_ASTAR: Long = 0L
        const val PATH_POSTPROCESSING_CORRIDORFUNNEL: Long = 0L
        const val PATH_POSTPROCESSING_EDGECENTERED: Long = 1L
        const val PATH_POSTPROCESSING_NONE: Long = 2L
        const val PATH_METADATA_INCLUDE_NONE: Long = 0L
        const val PATH_METADATA_INCLUDE_TYPES: Long = 1L
        const val PATH_METADATA_INCLUDE_RIDS: Long = 2L
        const val PATH_METADATA_INCLUDE_OWNERS: Long = 4L
        const val PATH_METADATA_INCLUDE_ALL: Long = 7L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): NavigationPathQueryParameters3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationPathQueryParameters3D? =
            if (handle.address() == 0L) null else NavigationPathQueryParameters3D(handle)

        private const val SET_PATHFINDING_ALGORITHM_HASH = 394560454L
        private val setPathfindingAlgorithmBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_pathfinding_algorithm", SET_PATHFINDING_ALGORITHM_HASH)
        }

        private const val GET_PATHFINDING_ALGORITHM_HASH = 3398491350L
        private val getPathfindingAlgorithmBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_pathfinding_algorithm", GET_PATHFINDING_ALGORITHM_HASH)
        }

        private const val SET_PATH_POSTPROCESSING_HASH = 2267362344L
        private val setPathPostprocessingBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_path_postprocessing", SET_PATH_POSTPROCESSING_HASH)
        }

        private const val GET_PATH_POSTPROCESSING_HASH = 3883858360L
        private val getPathPostprocessingBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_path_postprocessing", GET_PATH_POSTPROCESSING_HASH)
        }

        private const val SET_MAP_HASH = 2722037293L
        private val setMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_map", SET_MAP_HASH)
        }

        private const val GET_MAP_HASH = 2944877500L
        private val getMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_map", GET_MAP_HASH)
        }

        private const val SET_START_POSITION_HASH = 3460891852L
        private val setStartPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_start_position", SET_START_POSITION_HASH)
        }

        private const val GET_START_POSITION_HASH = 3360562783L
        private val getStartPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_start_position", GET_START_POSITION_HASH)
        }

        private const val SET_TARGET_POSITION_HASH = 3460891852L
        private val setTargetPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_target_position", SET_TARGET_POSITION_HASH)
        }

        private const val GET_TARGET_POSITION_HASH = 3360562783L
        private val getTargetPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_target_position", GET_TARGET_POSITION_HASH)
        }

        private const val SET_NAVIGATION_LAYERS_HASH = 1286410249L
        private val setNavigationLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_navigation_layers", SET_NAVIGATION_LAYERS_HASH)
        }

        private const val GET_NAVIGATION_LAYERS_HASH = 3905245786L
        private val getNavigationLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_navigation_layers", GET_NAVIGATION_LAYERS_HASH)
        }

        private const val SET_METADATA_FLAGS_HASH = 2713846708L
        private val setMetadataFlagsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_metadata_flags", SET_METADATA_FLAGS_HASH)
        }

        private const val GET_METADATA_FLAGS_HASH = 1582332802L
        private val getMetadataFlagsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_metadata_flags", GET_METADATA_FLAGS_HASH)
        }

        private const val SET_SIMPLIFY_PATH_HASH = 2586408642L
        private val setSimplifyPathBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_simplify_path", SET_SIMPLIFY_PATH_HASH)
        }

        private const val GET_SIMPLIFY_PATH_HASH = 36873697L
        private val getSimplifyPathBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_simplify_path", GET_SIMPLIFY_PATH_HASH)
        }

        private const val SET_SIMPLIFY_EPSILON_HASH = 373806689L
        private val setSimplifyEpsilonBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_simplify_epsilon", SET_SIMPLIFY_EPSILON_HASH)
        }

        private const val GET_SIMPLIFY_EPSILON_HASH = 1740695150L
        private val getSimplifyEpsilonBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_simplify_epsilon", GET_SIMPLIFY_EPSILON_HASH)
        }

        private const val SET_INCLUDED_REGIONS_HASH = 381264803L
        private val setIncludedRegionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_included_regions", SET_INCLUDED_REGIONS_HASH)
        }

        private const val GET_INCLUDED_REGIONS_HASH = 3995934104L
        private val getIncludedRegionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_included_regions", GET_INCLUDED_REGIONS_HASH)
        }

        private const val SET_EXCLUDED_REGIONS_HASH = 381264803L
        private val setExcludedRegionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_excluded_regions", SET_EXCLUDED_REGIONS_HASH)
        }

        private const val GET_EXCLUDED_REGIONS_HASH = 3995934104L
        private val getExcludedRegionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_excluded_regions", GET_EXCLUDED_REGIONS_HASH)
        }

        private const val SET_PATH_RETURN_MAX_LENGTH_HASH = 373806689L
        private val setPathReturnMaxLengthBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_path_return_max_length", SET_PATH_RETURN_MAX_LENGTH_HASH)
        }

        private const val GET_PATH_RETURN_MAX_LENGTH_HASH = 1740695150L
        private val getPathReturnMaxLengthBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_path_return_max_length", GET_PATH_RETURN_MAX_LENGTH_HASH)
        }

        private const val SET_PATH_RETURN_MAX_RADIUS_HASH = 373806689L
        private val setPathReturnMaxRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_path_return_max_radius", SET_PATH_RETURN_MAX_RADIUS_HASH)
        }

        private const val GET_PATH_RETURN_MAX_RADIUS_HASH = 1740695150L
        private val getPathReturnMaxRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_path_return_max_radius", GET_PATH_RETURN_MAX_RADIUS_HASH)
        }

        private const val SET_PATH_SEARCH_MAX_POLYGONS_HASH = 1286410249L
        private val setPathSearchMaxPolygonsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_path_search_max_polygons", SET_PATH_SEARCH_MAX_POLYGONS_HASH)
        }

        private const val GET_PATH_SEARCH_MAX_POLYGONS_HASH = 3905245786L
        private val getPathSearchMaxPolygonsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_path_search_max_polygons", GET_PATH_SEARCH_MAX_POLYGONS_HASH)
        }

        private const val SET_PATH_SEARCH_MAX_DISTANCE_HASH = 373806689L
        private val setPathSearchMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "set_path_search_max_distance", SET_PATH_SEARCH_MAX_DISTANCE_HASH)
        }

        private const val GET_PATH_SEARCH_MAX_DISTANCE_HASH = 1740695150L
        private val getPathSearchMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters3D", "get_path_search_max_distance", GET_PATH_SEARCH_MAX_DISTANCE_HASH)
        }
    }
}
