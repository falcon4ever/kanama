package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * A 3D agent used to pathfind to a position while avoiding obstacles.
 *
 * Generated from Godot docs: NavigationAgent3D
 */
class NavigationAgent3D(handle: MemorySegment) : Node(handle) {
    var targetPosition: Vector3
        @JvmName("targetPositionProperty")
        get() = getTargetPosition()
        @JvmName("setTargetPositionProperty")
        set(value) = setTargetPosition(value)

    var pathDesiredDistance: Double
        @JvmName("pathDesiredDistanceProperty")
        get() = getPathDesiredDistance()
        @JvmName("setPathDesiredDistanceProperty")
        set(value) = setPathDesiredDistance(value)

    var targetDesiredDistance: Double
        @JvmName("targetDesiredDistanceProperty")
        get() = getTargetDesiredDistance()
        @JvmName("setTargetDesiredDistanceProperty")
        set(value) = setTargetDesiredDistance(value)

    var pathHeightOffset: Double
        @JvmName("pathHeightOffsetProperty")
        get() = getPathHeightOffset()
        @JvmName("setPathHeightOffsetProperty")
        set(value) = setPathHeightOffset(value)

    var pathMaxDistance: Double
        @JvmName("pathMaxDistanceProperty")
        get() = getPathMaxDistance()
        @JvmName("setPathMaxDistanceProperty")
        set(value) = setPathMaxDistance(value)

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

    var pathMetadataFlags: Long
        @JvmName("pathMetadataFlagsProperty")
        get() = getPathMetadataFlags()
        @JvmName("setPathMetadataFlagsProperty")
        set(value) = setPathMetadataFlags(value)

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

    var avoidanceEnabled: Boolean
        @JvmName("avoidanceEnabledProperty")
        get() = getAvoidanceEnabled()
        @JvmName("setAvoidanceEnabledProperty")
        set(value) = setAvoidanceEnabled(value)

    var velocity: Vector3
        @JvmName("velocityProperty")
        get() = getVelocity()
        @JvmName("setVelocityProperty")
        set(value) = setVelocity(value)

    var height: Double
        @JvmName("heightProperty")
        get() = getHeight()
        @JvmName("setHeightProperty")
        set(value) = setHeight(value)

    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    var neighborDistance: Double
        @JvmName("neighborDistanceProperty")
        get() = getNeighborDistance()
        @JvmName("setNeighborDistanceProperty")
        set(value) = setNeighborDistance(value)

    var maxNeighbors: Int
        @JvmName("maxNeighborsProperty")
        get() = getMaxNeighbors()
        @JvmName("setMaxNeighborsProperty")
        set(value) = setMaxNeighbors(value)

    var timeHorizonAgents: Double
        @JvmName("timeHorizonAgentsProperty")
        get() = getTimeHorizonAgents()
        @JvmName("setTimeHorizonAgentsProperty")
        set(value) = setTimeHorizonAgents(value)

    var timeHorizonObstacles: Double
        @JvmName("timeHorizonObstaclesProperty")
        get() = getTimeHorizonObstacles()
        @JvmName("setTimeHorizonObstaclesProperty")
        set(value) = setTimeHorizonObstacles(value)

    var maxSpeed: Double
        @JvmName("maxSpeedProperty")
        get() = getMaxSpeed()
        @JvmName("setMaxSpeedProperty")
        set(value) = setMaxSpeed(value)

    var use3dAvoidance: Boolean
        @JvmName("use3dAvoidanceProperty")
        get() = getUse3dAvoidance()
        @JvmName("setUse3dAvoidanceProperty")
        set(value) = setUse3dAvoidance(value)

    var keepYVelocity: Boolean
        @JvmName("keepYVelocityProperty")
        get() = getKeepYVelocity()
        @JvmName("setKeepYVelocityProperty")
        set(value) = setKeepYVelocity(value)

    var avoidanceLayers: Long
        @JvmName("avoidanceLayersProperty")
        get() = getAvoidanceLayers()
        @JvmName("setAvoidanceLayersProperty")
        set(value) = setAvoidanceLayers(value)

    var avoidanceMask: Long
        @JvmName("avoidanceMaskProperty")
        get() = getAvoidanceMask()
        @JvmName("setAvoidanceMaskProperty")
        set(value) = setAvoidanceMask(value)

    var avoidancePriority: Double
        @JvmName("avoidancePriorityProperty")
        get() = getAvoidancePriority()
        @JvmName("setAvoidancePriorityProperty")
        set(value) = setAvoidancePriority(value)

    var debugEnabled: Boolean
        @JvmName("debugEnabledProperty")
        get() = getDebugEnabled()
        @JvmName("setDebugEnabledProperty")
        set(value) = setDebugEnabled(value)

    var debugUseCustom: Boolean
        @JvmName("debugUseCustomProperty")
        get() = getDebugUseCustom()
        @JvmName("setDebugUseCustomProperty")
        set(value) = setDebugUseCustom(value)

    var debugPathCustomColor: Color
        @JvmName("debugPathCustomColorProperty")
        get() = getDebugPathCustomColor()
        @JvmName("setDebugPathCustomColorProperty")
        set(value) = setDebugPathCustomColor(value)

    var debugPathCustomPointSize: Double
        @JvmName("debugPathCustomPointSizeProperty")
        get() = getDebugPathCustomPointSize()
        @JvmName("setDebugPathCustomPointSizeProperty")
        set(value) = setDebugPathCustomPointSize(value)

    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
    }

    fun setAvoidanceEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAvoidanceEnabledBind, handle, enabled)
    }

    fun getAvoidanceEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAvoidanceEnabledBind, handle)
    }

    fun setPathDesiredDistance(desiredDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathDesiredDistanceBind, handle, desiredDistance)
    }

    fun getPathDesiredDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathDesiredDistanceBind, handle)
    }

    fun setTargetDesiredDistance(desiredDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTargetDesiredDistanceBind, handle, desiredDistance)
    }

    fun getTargetDesiredDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTargetDesiredDistanceBind, handle)
    }

    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeightBind, handle, height)
    }

    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightBind, handle)
    }

    fun setPathHeightOffset(pathHeightOffset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathHeightOffsetBind, handle, pathHeightOffset)
    }

    fun getPathHeightOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathHeightOffsetBind, handle)
    }

    fun setUse3dAvoidance(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUse3dAvoidanceBind, handle, enabled)
    }

    fun getUse3dAvoidance(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUse3dAvoidanceBind, handle)
    }

    fun setKeepYVelocity(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setKeepYVelocityBind, handle, enabled)
    }

    fun getKeepYVelocity(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getKeepYVelocityBind, handle)
    }

    fun setNeighborDistance(neighborDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setNeighborDistanceBind, handle, neighborDistance)
    }

    fun getNeighborDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getNeighborDistanceBind, handle)
    }

    fun setMaxNeighbors(maxNeighbors: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxNeighborsBind, handle, maxNeighbors)
    }

    fun getMaxNeighbors(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxNeighborsBind, handle)
    }

    fun setTimeHorizonAgents(timeHorizon: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTimeHorizonAgentsBind, handle, timeHorizon)
    }

    fun getTimeHorizonAgents(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimeHorizonAgentsBind, handle)
    }

    fun setTimeHorizonObstacles(timeHorizon: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTimeHorizonObstaclesBind, handle, timeHorizon)
    }

    fun getTimeHorizonObstacles(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimeHorizonObstaclesBind, handle)
    }

    fun setMaxSpeed(maxSpeed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxSpeedBind, handle, maxSpeed)
    }

    fun getMaxSpeed(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxSpeedBind, handle)
    }

    fun setPathMaxDistance(maxSpeed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathMaxDistanceBind, handle, maxSpeed)
    }

    fun getPathMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathMaxDistanceBind, handle)
    }

    fun setNavigationLayers(navigationLayers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setNavigationLayersBind, handle, navigationLayers)
    }

    fun getNavigationLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getNavigationLayersBind, handle)
    }

    fun setNavigationLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setNavigationLayerValueBind, handle, layerNumber, value)
    }

    fun getNavigationLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getNavigationLayerValueBind, handle, layerNumber)
    }

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

    fun setPathMetadataFlags(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setPathMetadataFlagsBind, handle, flags)
    }

    fun getPathMetadataFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPathMetadataFlagsBind, handle)
    }

    fun setNavigationMap(navigationMap: RID) {
        ObjectCalls.ptrcallWithRIDArg(setNavigationMapBind, handle, navigationMap)
    }

    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getNavigationMapBind, handle)
    }

    fun setTargetPosition(position: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setTargetPositionBind, handle, position)
    }

    fun getTargetPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getTargetPositionBind, handle)
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

    fun getPathLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathLengthBind, handle)
    }

    fun getNextPathPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getNextPathPositionBind, handle)
    }

    fun setVelocityForced(velocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setVelocityForcedBind, handle, velocity)
    }

    fun setVelocity(velocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setVelocityBind, handle, velocity)
    }

    fun getVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getVelocityBind, handle)
    }

    fun distanceToTarget(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(distanceToTargetBind, handle)
    }

    fun getCurrentNavigationResult(): NavigationPathQueryResult3D? {
        return NavigationPathQueryResult3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurrentNavigationResultBind, handle))
    }

    fun getCurrentNavigationPath(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getCurrentNavigationPathBind, handle)
    }

    fun getCurrentNavigationPathIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCurrentNavigationPathIndexBind, handle)
    }

    fun isTargetReached(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTargetReachedBind, handle)
    }

    fun isTargetReachable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTargetReachableBind, handle)
    }

    fun isNavigationFinished(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isNavigationFinishedBind, handle)
    }

    fun getFinalPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getFinalPositionBind, handle)
    }

    fun setAvoidanceLayers(layers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setAvoidanceLayersBind, handle, layers)
    }

    fun getAvoidanceLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getAvoidanceLayersBind, handle)
    }

    fun setAvoidanceMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setAvoidanceMaskBind, handle, mask)
    }

    fun getAvoidanceMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getAvoidanceMaskBind, handle)
    }

    fun setAvoidanceLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAvoidanceLayerValueBind, handle, layerNumber, value)
    }

    fun getAvoidanceLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getAvoidanceLayerValueBind, handle, layerNumber)
    }

    fun setAvoidanceMaskValue(maskNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAvoidanceMaskValueBind, handle, maskNumber, value)
    }

    fun getAvoidanceMaskValue(maskNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getAvoidanceMaskValueBind, handle, maskNumber)
    }

    fun setAvoidancePriority(priority: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAvoidancePriorityBind, handle, priority)
    }

    fun getAvoidancePriority(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAvoidancePriorityBind, handle)
    }

    fun setDebugEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugEnabledBind, handle, enabled)
    }

    fun getDebugEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDebugEnabledBind, handle)
    }

    fun setDebugUseCustom(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugUseCustomBind, handle, enabled)
    }

    fun getDebugUseCustom(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDebugUseCustomBind, handle)
    }

    fun setDebugPathCustomColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setDebugPathCustomColorBind, handle, color)
    }

    fun getDebugPathCustomColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDebugPathCustomColorBind, handle)
    }

    fun setDebugPathCustomPointSize(pointSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDebugPathCustomPointSizeBind, handle, pointSize)
    }

    fun getDebugPathCustomPointSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDebugPathCustomPointSizeBind, handle)
    }

    object Signals {
        const val pathChanged: String = "path_changed"
        const val targetReached: String = "target_reached"
        const val waypointReached: String = "waypoint_reached"
        const val linkReached: String = "link_reached"
        const val navigationFinished: String = "navigation_finished"
        const val velocityComputed: String = "velocity_computed"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): NavigationAgent3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationAgent3D? =
            if (handle.address() == 0L) null else NavigationAgent3D(handle)

        private const val GET_RID_HASH = 2944877500L
        private val getRidBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_rid", GET_RID_HASH)
        }

        private const val SET_AVOIDANCE_ENABLED_HASH = 2586408642L
        private val setAvoidanceEnabledBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_avoidance_enabled", SET_AVOIDANCE_ENABLED_HASH)
        }

        private const val GET_AVOIDANCE_ENABLED_HASH = 36873697L
        private val getAvoidanceEnabledBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_avoidance_enabled", GET_AVOIDANCE_ENABLED_HASH)
        }

        private const val SET_PATH_DESIRED_DISTANCE_HASH = 373806689L
        private val setPathDesiredDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_desired_distance", SET_PATH_DESIRED_DISTANCE_HASH)
        }

        private const val GET_PATH_DESIRED_DISTANCE_HASH = 1740695150L
        private val getPathDesiredDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_desired_distance", GET_PATH_DESIRED_DISTANCE_HASH)
        }

        private const val SET_TARGET_DESIRED_DISTANCE_HASH = 373806689L
        private val setTargetDesiredDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_target_desired_distance", SET_TARGET_DESIRED_DISTANCE_HASH)
        }

        private const val GET_TARGET_DESIRED_DISTANCE_HASH = 1740695150L
        private val getTargetDesiredDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_target_desired_distance", GET_TARGET_DESIRED_DISTANCE_HASH)
        }

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_HEIGHT_HASH = 373806689L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_HEIGHT_HASH = 1740695150L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_height", GET_HEIGHT_HASH)
        }

        private const val SET_PATH_HEIGHT_OFFSET_HASH = 373806689L
        private val setPathHeightOffsetBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_height_offset", SET_PATH_HEIGHT_OFFSET_HASH)
        }

        private const val GET_PATH_HEIGHT_OFFSET_HASH = 1740695150L
        private val getPathHeightOffsetBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_height_offset", GET_PATH_HEIGHT_OFFSET_HASH)
        }

        private const val SET_USE_3D_AVOIDANCE_HASH = 2586408642L
        private val setUse3dAvoidanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_use_3d_avoidance", SET_USE_3D_AVOIDANCE_HASH)
        }

        private const val GET_USE_3D_AVOIDANCE_HASH = 36873697L
        private val getUse3dAvoidanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_use_3d_avoidance", GET_USE_3D_AVOIDANCE_HASH)
        }

        private const val SET_KEEP_Y_VELOCITY_HASH = 2586408642L
        private val setKeepYVelocityBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_keep_y_velocity", SET_KEEP_Y_VELOCITY_HASH)
        }

        private const val GET_KEEP_Y_VELOCITY_HASH = 36873697L
        private val getKeepYVelocityBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_keep_y_velocity", GET_KEEP_Y_VELOCITY_HASH)
        }

        private const val SET_NEIGHBOR_DISTANCE_HASH = 373806689L
        private val setNeighborDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_neighbor_distance", SET_NEIGHBOR_DISTANCE_HASH)
        }

        private const val GET_NEIGHBOR_DISTANCE_HASH = 1740695150L
        private val getNeighborDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_neighbor_distance", GET_NEIGHBOR_DISTANCE_HASH)
        }

        private const val SET_MAX_NEIGHBORS_HASH = 1286410249L
        private val setMaxNeighborsBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_max_neighbors", SET_MAX_NEIGHBORS_HASH)
        }

        private const val GET_MAX_NEIGHBORS_HASH = 3905245786L
        private val getMaxNeighborsBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_max_neighbors", GET_MAX_NEIGHBORS_HASH)
        }

        private const val SET_TIME_HORIZON_AGENTS_HASH = 373806689L
        private val setTimeHorizonAgentsBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_time_horizon_agents", SET_TIME_HORIZON_AGENTS_HASH)
        }

        private const val GET_TIME_HORIZON_AGENTS_HASH = 1740695150L
        private val getTimeHorizonAgentsBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_time_horizon_agents", GET_TIME_HORIZON_AGENTS_HASH)
        }

        private const val SET_TIME_HORIZON_OBSTACLES_HASH = 373806689L
        private val setTimeHorizonObstaclesBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_time_horizon_obstacles", SET_TIME_HORIZON_OBSTACLES_HASH)
        }

        private const val GET_TIME_HORIZON_OBSTACLES_HASH = 1740695150L
        private val getTimeHorizonObstaclesBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_time_horizon_obstacles", GET_TIME_HORIZON_OBSTACLES_HASH)
        }

        private const val SET_MAX_SPEED_HASH = 373806689L
        private val setMaxSpeedBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_max_speed", SET_MAX_SPEED_HASH)
        }

        private const val GET_MAX_SPEED_HASH = 1740695150L
        private val getMaxSpeedBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_max_speed", GET_MAX_SPEED_HASH)
        }

        private const val SET_PATH_MAX_DISTANCE_HASH = 373806689L
        private val setPathMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_max_distance", SET_PATH_MAX_DISTANCE_HASH)
        }

        private const val GET_PATH_MAX_DISTANCE_HASH = 191475506L
        private val getPathMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_max_distance", GET_PATH_MAX_DISTANCE_HASH)
        }

        private const val SET_NAVIGATION_LAYERS_HASH = 1286410249L
        private val setNavigationLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_navigation_layers", SET_NAVIGATION_LAYERS_HASH)
        }

        private const val GET_NAVIGATION_LAYERS_HASH = 3905245786L
        private val getNavigationLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_navigation_layers", GET_NAVIGATION_LAYERS_HASH)
        }

        private const val SET_NAVIGATION_LAYER_VALUE_HASH = 300928843L
        private val setNavigationLayerValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_navigation_layer_value", SET_NAVIGATION_LAYER_VALUE_HASH)
        }

        private const val GET_NAVIGATION_LAYER_VALUE_HASH = 1116898809L
        private val getNavigationLayerValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_navigation_layer_value", GET_NAVIGATION_LAYER_VALUE_HASH)
        }

        private const val SET_PATHFINDING_ALGORITHM_HASH = 394560454L
        private val setPathfindingAlgorithmBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_pathfinding_algorithm", SET_PATHFINDING_ALGORITHM_HASH)
        }

        private const val GET_PATHFINDING_ALGORITHM_HASH = 3398491350L
        private val getPathfindingAlgorithmBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_pathfinding_algorithm", GET_PATHFINDING_ALGORITHM_HASH)
        }

        private const val SET_PATH_POSTPROCESSING_HASH = 2267362344L
        private val setPathPostprocessingBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_postprocessing", SET_PATH_POSTPROCESSING_HASH)
        }

        private const val GET_PATH_POSTPROCESSING_HASH = 3883858360L
        private val getPathPostprocessingBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_postprocessing", GET_PATH_POSTPROCESSING_HASH)
        }

        private const val SET_PATH_METADATA_FLAGS_HASH = 2713846708L
        private val setPathMetadataFlagsBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_metadata_flags", SET_PATH_METADATA_FLAGS_HASH)
        }

        private const val GET_PATH_METADATA_FLAGS_HASH = 1582332802L
        private val getPathMetadataFlagsBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_metadata_flags", GET_PATH_METADATA_FLAGS_HASH)
        }

        private const val SET_NAVIGATION_MAP_HASH = 2722037293L
        private val setNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_navigation_map", SET_NAVIGATION_MAP_HASH)
        }

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        private val getNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_navigation_map", GET_NAVIGATION_MAP_HASH)
        }

        private const val SET_TARGET_POSITION_HASH = 3460891852L
        private val setTargetPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_target_position", SET_TARGET_POSITION_HASH)
        }

        private const val GET_TARGET_POSITION_HASH = 3360562783L
        private val getTargetPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_target_position", GET_TARGET_POSITION_HASH)
        }

        private const val SET_SIMPLIFY_PATH_HASH = 2586408642L
        private val setSimplifyPathBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_simplify_path", SET_SIMPLIFY_PATH_HASH)
        }

        private const val GET_SIMPLIFY_PATH_HASH = 36873697L
        private val getSimplifyPathBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_simplify_path", GET_SIMPLIFY_PATH_HASH)
        }

        private const val SET_SIMPLIFY_EPSILON_HASH = 373806689L
        private val setSimplifyEpsilonBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_simplify_epsilon", SET_SIMPLIFY_EPSILON_HASH)
        }

        private const val GET_SIMPLIFY_EPSILON_HASH = 1740695150L
        private val getSimplifyEpsilonBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_simplify_epsilon", GET_SIMPLIFY_EPSILON_HASH)
        }

        private const val SET_PATH_RETURN_MAX_LENGTH_HASH = 373806689L
        private val setPathReturnMaxLengthBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_return_max_length", SET_PATH_RETURN_MAX_LENGTH_HASH)
        }

        private const val GET_PATH_RETURN_MAX_LENGTH_HASH = 1740695150L
        private val getPathReturnMaxLengthBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_return_max_length", GET_PATH_RETURN_MAX_LENGTH_HASH)
        }

        private const val SET_PATH_RETURN_MAX_RADIUS_HASH = 373806689L
        private val setPathReturnMaxRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_return_max_radius", SET_PATH_RETURN_MAX_RADIUS_HASH)
        }

        private const val GET_PATH_RETURN_MAX_RADIUS_HASH = 1740695150L
        private val getPathReturnMaxRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_return_max_radius", GET_PATH_RETURN_MAX_RADIUS_HASH)
        }

        private const val SET_PATH_SEARCH_MAX_POLYGONS_HASH = 1286410249L
        private val setPathSearchMaxPolygonsBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_search_max_polygons", SET_PATH_SEARCH_MAX_POLYGONS_HASH)
        }

        private const val GET_PATH_SEARCH_MAX_POLYGONS_HASH = 3905245786L
        private val getPathSearchMaxPolygonsBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_search_max_polygons", GET_PATH_SEARCH_MAX_POLYGONS_HASH)
        }

        private const val SET_PATH_SEARCH_MAX_DISTANCE_HASH = 373806689L
        private val setPathSearchMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_search_max_distance", SET_PATH_SEARCH_MAX_DISTANCE_HASH)
        }

        private const val GET_PATH_SEARCH_MAX_DISTANCE_HASH = 1740695150L
        private val getPathSearchMaxDistanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_search_max_distance", GET_PATH_SEARCH_MAX_DISTANCE_HASH)
        }

        private const val GET_PATH_LENGTH_HASH = 1740695150L
        private val getPathLengthBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_length", GET_PATH_LENGTH_HASH)
        }

        private const val GET_NEXT_PATH_POSITION_HASH = 3783033775L
        private val getNextPathPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_next_path_position", GET_NEXT_PATH_POSITION_HASH)
        }

        private const val SET_VELOCITY_FORCED_HASH = 3460891852L
        private val setVelocityForcedBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_velocity_forced", SET_VELOCITY_FORCED_HASH)
        }

        private const val SET_VELOCITY_HASH = 3460891852L
        private val setVelocityBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_velocity", SET_VELOCITY_HASH)
        }

        private const val GET_VELOCITY_HASH = 3783033775L
        private val getVelocityBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_velocity", GET_VELOCITY_HASH)
        }

        private const val DISTANCE_TO_TARGET_HASH = 1740695150L
        private val distanceToTargetBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "distance_to_target", DISTANCE_TO_TARGET_HASH)
        }

        private const val GET_CURRENT_NAVIGATION_RESULT_HASH = 728825684L
        private val getCurrentNavigationResultBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_current_navigation_result", GET_CURRENT_NAVIGATION_RESULT_HASH)
        }

        private const val GET_CURRENT_NAVIGATION_PATH_HASH = 497664490L
        private val getCurrentNavigationPathBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_current_navigation_path", GET_CURRENT_NAVIGATION_PATH_HASH)
        }

        private const val GET_CURRENT_NAVIGATION_PATH_INDEX_HASH = 3905245786L
        private val getCurrentNavigationPathIndexBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_current_navigation_path_index", GET_CURRENT_NAVIGATION_PATH_INDEX_HASH)
        }

        private const val IS_TARGET_REACHED_HASH = 36873697L
        private val isTargetReachedBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "is_target_reached", IS_TARGET_REACHED_HASH)
        }

        private const val IS_TARGET_REACHABLE_HASH = 2240911060L
        private val isTargetReachableBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "is_target_reachable", IS_TARGET_REACHABLE_HASH)
        }

        private const val IS_NAVIGATION_FINISHED_HASH = 2240911060L
        private val isNavigationFinishedBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "is_navigation_finished", IS_NAVIGATION_FINISHED_HASH)
        }

        private const val GET_FINAL_POSITION_HASH = 3783033775L
        private val getFinalPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_final_position", GET_FINAL_POSITION_HASH)
        }

        private const val SET_AVOIDANCE_LAYERS_HASH = 1286410249L
        private val setAvoidanceLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_avoidance_layers", SET_AVOIDANCE_LAYERS_HASH)
        }

        private const val GET_AVOIDANCE_LAYERS_HASH = 3905245786L
        private val getAvoidanceLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_avoidance_layers", GET_AVOIDANCE_LAYERS_HASH)
        }

        private const val SET_AVOIDANCE_MASK_HASH = 1286410249L
        private val setAvoidanceMaskBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_avoidance_mask", SET_AVOIDANCE_MASK_HASH)
        }

        private const val GET_AVOIDANCE_MASK_HASH = 3905245786L
        private val getAvoidanceMaskBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_avoidance_mask", GET_AVOIDANCE_MASK_HASH)
        }

        private const val SET_AVOIDANCE_LAYER_VALUE_HASH = 300928843L
        private val setAvoidanceLayerValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_avoidance_layer_value", SET_AVOIDANCE_LAYER_VALUE_HASH)
        }

        private const val GET_AVOIDANCE_LAYER_VALUE_HASH = 1116898809L
        private val getAvoidanceLayerValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_avoidance_layer_value", GET_AVOIDANCE_LAYER_VALUE_HASH)
        }

        private const val SET_AVOIDANCE_MASK_VALUE_HASH = 300928843L
        private val setAvoidanceMaskValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_avoidance_mask_value", SET_AVOIDANCE_MASK_VALUE_HASH)
        }

        private const val GET_AVOIDANCE_MASK_VALUE_HASH = 1116898809L
        private val getAvoidanceMaskValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_avoidance_mask_value", GET_AVOIDANCE_MASK_VALUE_HASH)
        }

        private const val SET_AVOIDANCE_PRIORITY_HASH = 373806689L
        private val setAvoidancePriorityBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_avoidance_priority", SET_AVOIDANCE_PRIORITY_HASH)
        }

        private const val GET_AVOIDANCE_PRIORITY_HASH = 1740695150L
        private val getAvoidancePriorityBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_avoidance_priority", GET_AVOIDANCE_PRIORITY_HASH)
        }

        private const val SET_DEBUG_ENABLED_HASH = 2586408642L
        private val setDebugEnabledBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_debug_enabled", SET_DEBUG_ENABLED_HASH)
        }

        private const val GET_DEBUG_ENABLED_HASH = 36873697L
        private val getDebugEnabledBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_debug_enabled", GET_DEBUG_ENABLED_HASH)
        }

        private const val SET_DEBUG_USE_CUSTOM_HASH = 2586408642L
        private val setDebugUseCustomBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_debug_use_custom", SET_DEBUG_USE_CUSTOM_HASH)
        }

        private const val GET_DEBUG_USE_CUSTOM_HASH = 36873697L
        private val getDebugUseCustomBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_debug_use_custom", GET_DEBUG_USE_CUSTOM_HASH)
        }

        private const val SET_DEBUG_PATH_CUSTOM_COLOR_HASH = 2920490490L
        private val setDebugPathCustomColorBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_debug_path_custom_color", SET_DEBUG_PATH_CUSTOM_COLOR_HASH)
        }

        private const val GET_DEBUG_PATH_CUSTOM_COLOR_HASH = 3444240500L
        private val getDebugPathCustomColorBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_debug_path_custom_color", GET_DEBUG_PATH_CUSTOM_COLOR_HASH)
        }

        private const val SET_DEBUG_PATH_CUSTOM_POINT_SIZE_HASH = 373806689L
        private val setDebugPathCustomPointSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_debug_path_custom_point_size", SET_DEBUG_PATH_CUSTOM_POINT_SIZE_HASH)
        }

        private const val GET_DEBUG_PATH_CUSTOM_POINT_SIZE_HASH = 1740695150L
        private val getDebugPathCustomPointSizeBind by lazy {
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_debug_path_custom_point_size", GET_DEBUG_PATH_CUSTOM_POINT_SIZE_HASH)
        }
    }
}
