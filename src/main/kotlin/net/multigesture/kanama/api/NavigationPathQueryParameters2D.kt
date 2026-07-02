package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * Provides parameters for 2D navigation path queries.
 *
 * Generated from Godot docs: NavigationPathQueryParameters2D
 */
class NavigationPathQueryParameters2D(handle: MemorySegment) : RefCounted(handle) {
    var map: RID
        @JvmName("mapProperty")
        get() = getMap()
        @JvmName("setMapProperty")
        set(value) = setMap(value)

    var startPosition: Vector2
        @JvmName("startPositionProperty")
        get() = getStartPosition()
        @JvmName("setStartPositionProperty")
        set(value) = setStartPosition(value)

    var targetPosition: Vector2
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

    fun setPathfindingAlgorithm(pathfindingAlgorithm: Long) {
        ObjectCalls.ptrcallWithLongArg(setPathfindingAlgorithmBind, handle, pathfindingAlgorithm)
    }

    fun getPathfindingAlgorithm(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPathfindingAlgorithmBind, handle)
    }

    fun setPathPostprocessing(pathPostprocessing: Long) {
        ObjectCalls.ptrcallWithLongArg(setPathPostprocessingBind, handle, pathPostprocessing)
    }

    fun getPathPostprocessing(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPathPostprocessingBind, handle)
    }

    fun setMap(map: RID) {
        ObjectCalls.ptrcallWithRIDArg(setMapBind, handle, map)
    }

    fun getMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getMapBind, handle)
    }

    fun setStartPosition(startPosition: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setStartPositionBind, handle, startPosition)
    }

    fun getStartPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getStartPositionBind, handle)
    }

    fun setTargetPosition(targetPosition: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setTargetPositionBind, handle, targetPosition)
    }

    fun getTargetPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getTargetPositionBind, handle)
    }

    fun setNavigationLayers(navigationLayers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setNavigationLayersBind, handle, navigationLayers)
    }

    fun getNavigationLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getNavigationLayersBind, handle)
    }

    fun setMetadataFlags(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setMetadataFlagsBind, handle, flags)
    }

    fun getMetadataFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getMetadataFlagsBind, handle)
    }

    fun setSimplifyPath(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSimplifyPathBind, handle, enabled)
    }

    fun getSimplifyPath(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSimplifyPathBind, handle)
    }

    fun setSimplifyEpsilon(epsilon: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSimplifyEpsilonBind, handle, epsilon)
    }

    fun getSimplifyEpsilon(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSimplifyEpsilonBind, handle)
    }

    fun setIncludedRegions(regions: List<RID>) {
        ObjectCalls.ptrcallWithRIDListArg(setIncludedRegionsBind, handle, regions)
    }

    fun getIncludedRegions(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(getIncludedRegionsBind, handle)
    }

    fun setExcludedRegions(regions: List<RID>) {
        ObjectCalls.ptrcallWithRIDListArg(setExcludedRegionsBind, handle, regions)
    }

    fun getExcludedRegions(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(getExcludedRegionsBind, handle)
    }

    fun setPathReturnMaxLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathReturnMaxLengthBind, handle, length)
    }

    fun getPathReturnMaxLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathReturnMaxLengthBind, handle)
    }

    fun setPathReturnMaxRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathReturnMaxRadiusBind, handle, radius)
    }

    fun getPathReturnMaxRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathReturnMaxRadiusBind, handle)
    }

    fun setPathSearchMaxPolygons(maxPolygons: Int) {
        ObjectCalls.ptrcallWithIntArg(setPathSearchMaxPolygonsBind, handle, maxPolygons)
    }

    fun getPathSearchMaxPolygons(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getPathSearchMaxPolygonsBind, handle)
    }

    fun setPathSearchMaxDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathSearchMaxDistanceBind, handle, distance)
    }

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
        fun fromHandle(handle: MemorySegment): NavigationPathQueryParameters2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationPathQueryParameters2D? =
            if (handle.address() == 0L) null else NavigationPathQueryParameters2D(handle)

        private const val SET_PATHFINDING_ALGORITHM_HASH = 2783519915L
        private val setPathfindingAlgorithmBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_pathfinding_algorithm", SET_PATHFINDING_ALGORITHM_HASH)
        }

        private const val GET_PATHFINDING_ALGORITHM_HASH = 3000421146L
        private val getPathfindingAlgorithmBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_pathfinding_algorithm", GET_PATHFINDING_ALGORITHM_HASH)
        }

        private const val SET_PATH_POSTPROCESSING_HASH = 2864409082L
        private val setPathPostprocessingBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_path_postprocessing", SET_PATH_POSTPROCESSING_HASH)
        }

        private const val GET_PATH_POSTPROCESSING_HASH = 3798118993L
        private val getPathPostprocessingBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_path_postprocessing", GET_PATH_POSTPROCESSING_HASH)
        }

        private const val SET_MAP_HASH = 2722037293L
        private val setMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_map", SET_MAP_HASH)
        }

        private const val GET_MAP_HASH = 2944877500L
        private val getMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_map", GET_MAP_HASH)
        }

        private const val SET_START_POSITION_HASH = 743155724L
        private val setStartPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_start_position", SET_START_POSITION_HASH)
        }

        private const val GET_START_POSITION_HASH = 3341600327L
        private val getStartPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_start_position", GET_START_POSITION_HASH)
        }

        private const val SET_TARGET_POSITION_HASH = 743155724L
        private val setTargetPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_target_position", SET_TARGET_POSITION_HASH)
        }

        private const val GET_TARGET_POSITION_HASH = 3341600327L
        private val getTargetPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_target_position", GET_TARGET_POSITION_HASH)
        }

        private const val SET_NAVIGATION_LAYERS_HASH = 1286410249L
        private val setNavigationLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_navigation_layers", SET_NAVIGATION_LAYERS_HASH)
        }

        private const val GET_NAVIGATION_LAYERS_HASH = 3905245786L
        private val getNavigationLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_navigation_layers", GET_NAVIGATION_LAYERS_HASH)
        }

        private const val SET_METADATA_FLAGS_HASH = 24274129L
        private val setMetadataFlagsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_metadata_flags", SET_METADATA_FLAGS_HASH)
        }

        private const val GET_METADATA_FLAGS_HASH = 488152976L
        private val getMetadataFlagsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_metadata_flags", GET_METADATA_FLAGS_HASH)
        }

        private const val SET_SIMPLIFY_PATH_HASH = 2586408642L
        private val setSimplifyPathBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_simplify_path", SET_SIMPLIFY_PATH_HASH)
        }

        private const val GET_SIMPLIFY_PATH_HASH = 36873697L
        private val getSimplifyPathBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_simplify_path", GET_SIMPLIFY_PATH_HASH)
        }

        private const val SET_SIMPLIFY_EPSILON_HASH = 373806689L
        private val setSimplifyEpsilonBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_simplify_epsilon", SET_SIMPLIFY_EPSILON_HASH)
        }

        private const val GET_SIMPLIFY_EPSILON_HASH = 1740695150L
        private val getSimplifyEpsilonBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_simplify_epsilon", GET_SIMPLIFY_EPSILON_HASH)
        }

        private const val SET_INCLUDED_REGIONS_HASH = 381264803L
        private val setIncludedRegionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_included_regions", SET_INCLUDED_REGIONS_HASH)
        }

        private const val GET_INCLUDED_REGIONS_HASH = 3995934104L
        private val getIncludedRegionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_included_regions", GET_INCLUDED_REGIONS_HASH)
        }

        private const val SET_EXCLUDED_REGIONS_HASH = 381264803L
        private val setExcludedRegionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_excluded_regions", SET_EXCLUDED_REGIONS_HASH)
        }

        private const val GET_EXCLUDED_REGIONS_HASH = 3995934104L
        private val getExcludedRegionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_excluded_regions", GET_EXCLUDED_REGIONS_HASH)
        }

        private const val SET_PATH_RETURN_MAX_LENGTH_HASH = 373806689L
        private val setPathReturnMaxLengthBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_path_return_max_length", SET_PATH_RETURN_MAX_LENGTH_HASH)
        }

        private const val GET_PATH_RETURN_MAX_LENGTH_HASH = 1740695150L
        private val getPathReturnMaxLengthBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_path_return_max_length", GET_PATH_RETURN_MAX_LENGTH_HASH)
        }

        private const val SET_PATH_RETURN_MAX_RADIUS_HASH = 373806689L
        private val setPathReturnMaxRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_path_return_max_radius", SET_PATH_RETURN_MAX_RADIUS_HASH)
        }

        private const val GET_PATH_RETURN_MAX_RADIUS_HASH = 1740695150L
        private val getPathReturnMaxRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_path_return_max_radius", GET_PATH_RETURN_MAX_RADIUS_HASH)
        }

        private const val SET_PATH_SEARCH_MAX_POLYGONS_HASH = 1286410249L
        private val setPathSearchMaxPolygonsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_path_search_max_polygons", SET_PATH_SEARCH_MAX_POLYGONS_HASH)
        }

        private const val GET_PATH_SEARCH_MAX_POLYGONS_HASH = 3905245786L
        private val getPathSearchMaxPolygonsBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_path_search_max_polygons", GET_PATH_SEARCH_MAX_POLYGONS_HASH)
        }

        private const val SET_PATH_SEARCH_MAX_DISTANCE_HASH = 373806689L
        private val setPathSearchMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "set_path_search_max_distance", SET_PATH_SEARCH_MAX_DISTANCE_HASH)
        }

        private const val GET_PATH_SEARCH_MAX_DISTANCE_HASH = 1740695150L
        private val getPathSearchMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationPathQueryParameters2D", "get_path_search_max_distance", GET_PATH_SEARCH_MAX_DISTANCE_HASH)
        }
    }
}
