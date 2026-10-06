package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Color
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * A 3D agent used to pathfind to a position while avoiding obstacles.
 *
 * Generated from Godot docs: NavigationAgent3D
 */
class NavigationAgent3D(handle: GodotHandle) : Node(handle) {
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

    var pathfindingAlgorithm: NavigationPathQueryParameters3D.PathfindingAlgorithm
        @JvmName("pathfindingAlgorithmProperty")
        get() = getPathfindingAlgorithm()
        @JvmName("setPathfindingAlgorithmProperty")
        set(value) = setPathfindingAlgorithm(value)

    var pathPostprocessing: NavigationPathQueryParameters3D.PathPostProcessing
        @JvmName("pathPostprocessingProperty")
        get() = getPathPostprocessing()
        @JvmName("setPathPostprocessingProperty")
        set(value) = setPathPostprocessing(value)

    var pathMetadataFlags: NavigationPathQueryParameters3D.PathMetadataFlags
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

    /**
     * Returns the `RID` of this agent on the `NavigationServer3D`.
     *
     * Generated from Godot docs: NavigationAgent3D.get_rid
     */
    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getRidBind, segment)
    }

    /**
     * If `true` the agent is registered for an RVO avoidance callback on the `NavigationServer3D`.
     * When `velocity` is set and the processing is completed a `safe_velocity` Vector3 is received
     * with a signal connection to `velocity_computed`. Avoidance processing with many registered
     * agents has a significant performance cost and should only be enabled on agents that currently
     * require it.
     *
     * Generated from Godot docs: NavigationAgent3D.set_avoidance_enabled
     */
    fun setAvoidanceEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAvoidanceEnabledBind, segment, enabled)
    }

    /**
     * If `true` the agent is registered for an RVO avoidance callback on the `NavigationServer3D`.
     * When `velocity` is set and the processing is completed a `safe_velocity` Vector3 is received
     * with a signal connection to `velocity_computed`. Avoidance processing with many registered
     * agents has a significant performance cost and should only be enabled on agents that currently
     * require it.
     *
     * Generated from Godot docs: NavigationAgent3D.get_avoidance_enabled
     */
    fun getAvoidanceEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getAvoidanceEnabledBind, segment)
    }

    /**
     * The distance threshold before a path point is considered to be reached. This allows agents to
     * not have to hit a path point on the path exactly, but only to reach its general area. If this
     * value is set too high, the NavigationAgent will skip points on the path, which can lead to it
     * leaving the navigation mesh. If this value is set too low, the NavigationAgent will be stuck in
     * a repath loop because it will constantly overshoot the distance to the next point on each
     * physics frame update.
     *
     * Generated from Godot docs: NavigationAgent3D.set_path_desired_distance
     */
    fun setPathDesiredDistance(desiredDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPathDesiredDistanceBind, segment, desiredDistance)
    }

    /**
     * The distance threshold before a path point is considered to be reached. This allows agents to
     * not have to hit a path point on the path exactly, but only to reach its general area. If this
     * value is set too high, the NavigationAgent will skip points on the path, which can lead to it
     * leaving the navigation mesh. If this value is set too low, the NavigationAgent will be stuck in
     * a repath loop because it will constantly overshoot the distance to the next point on each
     * physics frame update.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_desired_distance
     */
    fun getPathDesiredDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathDesiredDistanceBind, segment)
    }

    /**
     * The distance threshold before the target is considered to be reached. On reaching the target,
     * `target_reached` is emitted and navigation ends (see `is_navigation_finished` and
     * `navigation_finished`). You can make navigation end early by setting this property to a value
     * greater than `path_desired_distance` (navigation will end before reaching the last waypoint).
     * You can also make navigation end closer to the target than each individual path position by
     * setting this property to a value lower than `path_desired_distance` (navigation won't
     * immediately end when reaching the last waypoint). However, if the value set is too low, the
     * agent will be stuck in a repath loop because it will constantly overshoot the distance to the
     * target on each physics frame update.
     *
     * Generated from Godot docs: NavigationAgent3D.set_target_desired_distance
     */
    fun setTargetDesiredDistance(desiredDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTargetDesiredDistanceBind, segment, desiredDistance)
    }

    /**
     * The distance threshold before the target is considered to be reached. On reaching the target,
     * `target_reached` is emitted and navigation ends (see `is_navigation_finished` and
     * `navigation_finished`). You can make navigation end early by setting this property to a value
     * greater than `path_desired_distance` (navigation will end before reaching the last waypoint).
     * You can also make navigation end closer to the target than each individual path position by
     * setting this property to a value lower than `path_desired_distance` (navigation won't
     * immediately end when reaching the last waypoint). However, if the value set is too low, the
     * agent will be stuck in a repath loop because it will constantly overshoot the distance to the
     * target on each physics frame update.
     *
     * Generated from Godot docs: NavigationAgent3D.get_target_desired_distance
     */
    fun getTargetDesiredDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTargetDesiredDistanceBind, segment)
    }

    /**
     * The radius of the avoidance agent. This is the "body" of the avoidance agent and not the
     * avoidance maneuver starting radius (which is controlled by `neighbor_distance`). Does not affect
     * normal pathfinding. To change an actor's pathfinding radius bake `NavigationMesh` resources with
     * a different `NavigationMesh.agent_radius` property and use different navigation maps for each
     * actor size.
     *
     * Generated from Godot docs: NavigationAgent3D.set_radius
     */
    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
    }

    /**
     * The radius of the avoidance agent. This is the "body" of the avoidance agent and not the
     * avoidance maneuver starting radius (which is controlled by `neighbor_distance`). Does not affect
     * normal pathfinding. To change an actor's pathfinding radius bake `NavigationMesh` resources with
     * a different `NavigationMesh.agent_radius` property and use different navigation maps for each
     * actor size.
     *
     * Generated from Godot docs: NavigationAgent3D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
    }

    /**
     * The height of the avoidance agent. Agents will ignore other agents or obstacles that are above
     * or below their current position + height in 2D avoidance. Does nothing in 3D avoidance which
     * uses radius spheres alone.
     *
     * Generated from Godot docs: NavigationAgent3D.set_height
     */
    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHeightBind, segment, height)
    }

    /**
     * The height of the avoidance agent. Agents will ignore other agents or obstacles that are above
     * or below their current position + height in 2D avoidance. Does nothing in 3D avoidance which
     * uses radius spheres alone.
     *
     * Generated from Godot docs: NavigationAgent3D.get_height
     */
    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHeightBind, segment)
    }

    /**
     * The height offset is subtracted from the y-axis value of any vector path position for this
     * NavigationAgent. The NavigationAgent height offset does not change or influence the navigation
     * mesh or pathfinding query result. Additional navigation maps that use regions with navigation
     * meshes that the developer baked with appropriate agent radius or height values are required to
     * support different-sized agents.
     *
     * Generated from Godot docs: NavigationAgent3D.set_path_height_offset
     */
    fun setPathHeightOffset(pathHeightOffset: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPathHeightOffsetBind, segment, pathHeightOffset)
    }

    /**
     * The height offset is subtracted from the y-axis value of any vector path position for this
     * NavigationAgent. The NavigationAgent height offset does not change or influence the navigation
     * mesh or pathfinding query result. Additional navigation maps that use regions with navigation
     * meshes that the developer baked with appropriate agent radius or height values are required to
     * support different-sized agents.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_height_offset
     */
    fun getPathHeightOffset(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathHeightOffsetBind, segment)
    }

    /**
     * If `true`, the agent calculates avoidance velocities in 3D omnidirectionally, e.g. for games
     * that take place in air, underwater or space. Agents using 3D avoidance only avoid other agents
     * using 3D avoidance, and react to radius-based avoidance obstacles. They ignore any vertex-based
     * obstacles. If `false`, the agent calculates avoidance velocities in 2D along the x and z-axes,
     * ignoring the y-axis. Agents using 2D avoidance only avoid other agents using 2D avoidance, and
     * react to radius-based avoidance obstacles or vertex-based avoidance obstacles. Other agents
     * using 2D avoidance that are below or above their current position including `height` are
     * ignored.
     *
     * Generated from Godot docs: NavigationAgent3D.set_use_3d_avoidance
     */
    fun setUse3dAvoidance(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUse3dAvoidanceBind, segment, enabled)
    }

    /**
     * If `true`, the agent calculates avoidance velocities in 3D omnidirectionally, e.g. for games
     * that take place in air, underwater or space. Agents using 3D avoidance only avoid other agents
     * using 3D avoidance, and react to radius-based avoidance obstacles. They ignore any vertex-based
     * obstacles. If `false`, the agent calculates avoidance velocities in 2D along the x and z-axes,
     * ignoring the y-axis. Agents using 2D avoidance only avoid other agents using 2D avoidance, and
     * react to radius-based avoidance obstacles or vertex-based avoidance obstacles. Other agents
     * using 2D avoidance that are below or above their current position including `height` are
     * ignored.
     *
     * Generated from Godot docs: NavigationAgent3D.get_use_3d_avoidance
     */
    fun getUse3dAvoidance(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUse3dAvoidanceBind, segment)
    }

    /**
     * If `true`, and the agent uses 2D avoidance, it will remember the set y-axis velocity and reapply
     * it after the avoidance step. While 2D avoidance has no y-axis and simulates on a flat plane this
     * setting can help to soften the most obvious clipping on uneven 3D geometry.
     *
     * Generated from Godot docs: NavigationAgent3D.set_keep_y_velocity
     */
    fun setKeepYVelocity(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setKeepYVelocityBind, segment, enabled)
    }

    /**
     * If `true`, and the agent uses 2D avoidance, it will remember the set y-axis velocity and reapply
     * it after the avoidance step. While 2D avoidance has no y-axis and simulates on a flat plane this
     * setting can help to soften the most obvious clipping on uneven 3D geometry.
     *
     * Generated from Godot docs: NavigationAgent3D.get_keep_y_velocity
     */
    fun getKeepYVelocity(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getKeepYVelocityBind, segment)
    }

    /**
     * The distance to search for other agents.
     *
     * Generated from Godot docs: NavigationAgent3D.set_neighbor_distance
     */
    fun setNeighborDistance(neighborDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setNeighborDistanceBind, segment, neighborDistance)
    }

    /**
     * The distance to search for other agents.
     *
     * Generated from Godot docs: NavigationAgent3D.get_neighbor_distance
     */
    fun getNeighborDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getNeighborDistanceBind, segment)
    }

    /**
     * The maximum number of neighbors for the agent to consider.
     *
     * Generated from Godot docs: NavigationAgent3D.set_max_neighbors
     */
    fun setMaxNeighbors(maxNeighbors: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setMaxNeighborsBind, segment, maxNeighbors)
    }

    /**
     * The maximum number of neighbors for the agent to consider.
     *
     * Generated from Godot docs: NavigationAgent3D.get_max_neighbors
     */
    fun getMaxNeighbors(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getMaxNeighborsBind, segment)
    }

    /**
     * The minimal amount of time for which this agent's velocities, that are computed with the
     * collision avoidance algorithm, are safe with respect to other agents. The larger the number, the
     * sooner the agent will respond to other agents, but less freedom in choosing its velocities. A
     * too high value will slow down agents movement considerably. Must be positive.
     *
     * Generated from Godot docs: NavigationAgent3D.set_time_horizon_agents
     */
    fun setTimeHorizonAgents(timeHorizon: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTimeHorizonAgentsBind, segment, timeHorizon)
    }

    /**
     * The minimal amount of time for which this agent's velocities, that are computed with the
     * collision avoidance algorithm, are safe with respect to other agents. The larger the number, the
     * sooner the agent will respond to other agents, but less freedom in choosing its velocities. A
     * too high value will slow down agents movement considerably. Must be positive.
     *
     * Generated from Godot docs: NavigationAgent3D.get_time_horizon_agents
     */
    fun getTimeHorizonAgents(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTimeHorizonAgentsBind, segment)
    }

    /**
     * The minimal amount of time for which this agent's velocities, that are computed with the
     * collision avoidance algorithm, are safe with respect to static avoidance obstacles. The larger
     * the number, the sooner the agent will respond to static avoidance obstacles, but less freedom in
     * choosing its velocities. A too high value will slow down agents movement considerably. Must be
     * positive.
     *
     * Generated from Godot docs: NavigationAgent3D.set_time_horizon_obstacles
     */
    fun setTimeHorizonObstacles(timeHorizon: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTimeHorizonObstaclesBind, segment, timeHorizon)
    }

    /**
     * The minimal amount of time for which this agent's velocities, that are computed with the
     * collision avoidance algorithm, are safe with respect to static avoidance obstacles. The larger
     * the number, the sooner the agent will respond to static avoidance obstacles, but less freedom in
     * choosing its velocities. A too high value will slow down agents movement considerably. Must be
     * positive.
     *
     * Generated from Godot docs: NavigationAgent3D.get_time_horizon_obstacles
     */
    fun getTimeHorizonObstacles(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTimeHorizonObstaclesBind, segment)
    }

    /**
     * The maximum speed that an agent can move.
     *
     * Generated from Godot docs: NavigationAgent3D.set_max_speed
     */
    fun setMaxSpeed(maxSpeed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMaxSpeedBind, segment, maxSpeed)
    }

    /**
     * The maximum speed that an agent can move.
     *
     * Generated from Godot docs: NavigationAgent3D.get_max_speed
     */
    fun getMaxSpeed(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMaxSpeedBind, segment)
    }

    /**
     * The maximum distance the agent is allowed away from the ideal path to the final position. This
     * can happen due to trying to avoid collisions. When the maximum distance is exceeded, it
     * recalculates the ideal path.
     *
     * Generated from Godot docs: NavigationAgent3D.set_path_max_distance
     */
    fun setPathMaxDistance(maxSpeed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPathMaxDistanceBind, segment, maxSpeed)
    }

    /**
     * The maximum distance the agent is allowed away from the ideal path to the final position. This
     * can happen due to trying to avoid collisions. When the maximum distance is exceeded, it
     * recalculates the ideal path.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_max_distance
     */
    fun getPathMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathMaxDistanceBind, segment)
    }

    /**
     * A bitfield determining which navigation layers of navigation regions this agent will use to
     * calculate a path. Changing it during runtime will clear the current navigation path and generate
     * a new one, according to the new navigation layers.
     *
     * Generated from Godot docs: NavigationAgent3D.set_navigation_layers
     */
    fun setNavigationLayers(navigationLayers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setNavigationLayersBind, segment, navigationLayers)
    }

    /**
     * A bitfield determining which navigation layers of navigation regions this agent will use to
     * calculate a path. Changing it during runtime will clear the current navigation path and generate
     * a new one, according to the new navigation layers.
     *
     * Generated from Godot docs: NavigationAgent3D.get_navigation_layers
     */
    fun getNavigationLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getNavigationLayersBind, segment)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `navigation_layers` bitmask,
     * given a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationAgent3D.set_navigation_layer_value
     */
    fun setNavigationLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setNavigationLayerValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `navigation_layers` bitmask is enabled, given
     * a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationAgent3D.get_navigation_layer_value
     */
    fun getNavigationLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getNavigationLayerValueBind, segment, layerNumber)
    }

    /**
     * The pathfinding algorithm used in the path query.
     *
     * Generated from Godot docs: NavigationAgent3D.set_pathfinding_algorithm
     */
    fun setPathfindingAlgorithm(pathfindingAlgorithm: NavigationPathQueryParameters3D.PathfindingAlgorithm) {
        ObjectCalls.ptrcallWithLongArg(Binds.setPathfindingAlgorithmBind, segment, pathfindingAlgorithm.value)
    }

    /**
     * The pathfinding algorithm used in the path query.
     *
     * Generated from Godot docs: NavigationAgent3D.get_pathfinding_algorithm
     */
    fun getPathfindingAlgorithm(): NavigationPathQueryParameters3D.PathfindingAlgorithm {
        return NavigationPathQueryParameters3D.PathfindingAlgorithm(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPathfindingAlgorithmBind, segment))
    }

    /**
     * The path postprocessing applied to the raw path corridor found by the `pathfinding_algorithm`.
     *
     * Generated from Godot docs: NavigationAgent3D.set_path_postprocessing
     */
    fun setPathPostprocessing(pathPostprocessing: NavigationPathQueryParameters3D.PathPostProcessing) {
        ObjectCalls.ptrcallWithLongArg(Binds.setPathPostprocessingBind, segment, pathPostprocessing.value)
    }

    /**
     * The path postprocessing applied to the raw path corridor found by the `pathfinding_algorithm`.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_postprocessing
     */
    fun getPathPostprocessing(): NavigationPathQueryParameters3D.PathPostProcessing {
        return NavigationPathQueryParameters3D.PathPostProcessing(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPathPostprocessingBind, segment))
    }

    /**
     * Additional information to return with the navigation path.
     *
     * Generated from Godot docs: NavigationAgent3D.set_path_metadata_flags
     */
    fun setPathMetadataFlags(flags: NavigationPathQueryParameters3D.PathMetadataFlags) {
        ObjectCalls.ptrcallWithLongArg(Binds.setPathMetadataFlagsBind, segment, flags.value)
    }

    /**
     * Additional information to return with the navigation path.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_metadata_flags
     */
    fun getPathMetadataFlags(): NavigationPathQueryParameters3D.PathMetadataFlags {
        return NavigationPathQueryParameters3D.PathMetadataFlags(ObjectCalls.ptrcallNoArgsRetLong(Binds.getPathMetadataFlagsBind, segment))
    }

    /**
     * Sets the `RID` of the navigation map this NavigationAgent node should use and also updates the
     * `agent` on the NavigationServer.
     *
     * Generated from Godot docs: NavigationAgent3D.set_navigation_map
     */
    fun setNavigationMap(navigationMap: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.setNavigationMapBind, segment, navigationMap)
    }

    /**
     * Returns the `RID` of the navigation map for this NavigationAgent node. This function returns
     * always the map set on the NavigationAgent node and not the map of the abstract agent on the
     * NavigationServer. If the agent map is changed directly with the NavigationServer API the
     * NavigationAgent node will not be aware of the map change. Use `set_navigation_map` to change the
     * navigation map for the NavigationAgent and also update the agent on the NavigationServer.
     *
     * Generated from Godot docs: NavigationAgent3D.get_navigation_map
     */
    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getNavigationMapBind, segment)
    }

    /**
     * If set, a new navigation path from the current agent position to the `target_position` is
     * requested from the NavigationServer.
     *
     * Generated from Godot docs: NavigationAgent3D.set_target_position
     */
    fun setTargetPosition(position: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setTargetPositionBind, segment, position)
    }

    /**
     * If set, a new navigation path from the current agent position to the `target_position` is
     * requested from the NavigationServer.
     *
     * Generated from Godot docs: NavigationAgent3D.get_target_position
     */
    fun getTargetPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getTargetPositionBind, segment)
    }

    /**
     * If `true` a simplified version of the path will be returned with less critical path points
     * removed. The simplification amount is controlled by `simplify_epsilon`. The simplification uses
     * a variant of Ramer-Douglas-Peucker algorithm for curve point decimation. Path simplification can
     * be helpful to mitigate various path following issues that can arise with certain agent types and
     * script behaviors. E.g. "steering" agents or avoidance in "open fields".
     *
     * Generated from Godot docs: NavigationAgent3D.set_simplify_path
     */
    fun setSimplifyPath(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSimplifyPathBind, segment, enabled)
    }

    /**
     * If `true` a simplified version of the path will be returned with less critical path points
     * removed. The simplification amount is controlled by `simplify_epsilon`. The simplification uses
     * a variant of Ramer-Douglas-Peucker algorithm for curve point decimation. Path simplification can
     * be helpful to mitigate various path following issues that can arise with certain agent types and
     * script behaviors. E.g. "steering" agents or avoidance in "open fields".
     *
     * Generated from Godot docs: NavigationAgent3D.get_simplify_path
     */
    fun getSimplifyPath(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSimplifyPathBind, segment)
    }

    /**
     * The path simplification amount in worlds units.
     *
     * Generated from Godot docs: NavigationAgent3D.set_simplify_epsilon
     */
    fun setSimplifyEpsilon(epsilon: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSimplifyEpsilonBind, segment, epsilon)
    }

    /**
     * The path simplification amount in worlds units.
     *
     * Generated from Godot docs: NavigationAgent3D.get_simplify_epsilon
     */
    fun getSimplifyEpsilon(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSimplifyEpsilonBind, segment)
    }

    /**
     * The maximum allowed length of the returned path in world units. A path will be clipped when
     * going over this length.
     *
     * Generated from Godot docs: NavigationAgent3D.set_path_return_max_length
     */
    fun setPathReturnMaxLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPathReturnMaxLengthBind, segment, length)
    }

    /**
     * The maximum allowed length of the returned path in world units. A path will be clipped when
     * going over this length.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_return_max_length
     */
    fun getPathReturnMaxLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathReturnMaxLengthBind, segment)
    }

    /**
     * The maximum allowed radius in world units that the returned path can be from the path start. The
     * path will be clipped when going over this radius. Compared to `path_return_max_length`, this
     * allows the agent to go that much further, if they need to walk around a corner. Note: This will
     * perform a sphere clip considering only the actual navigation mesh path points with the first
     * path position being the sphere's center.
     *
     * Generated from Godot docs: NavigationAgent3D.set_path_return_max_radius
     */
    fun setPathReturnMaxRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPathReturnMaxRadiusBind, segment, radius)
    }

    /**
     * The maximum allowed radius in world units that the returned path can be from the path start. The
     * path will be clipped when going over this radius. Compared to `path_return_max_length`, this
     * allows the agent to go that much further, if they need to walk around a corner. Note: This will
     * perform a sphere clip considering only the actual navigation mesh path points with the first
     * path position being the sphere's center.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_return_max_radius
     */
    fun getPathReturnMaxRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathReturnMaxRadiusBind, segment)
    }

    /**
     * The maximum number of polygons that are searched before the pathfinding cancels the search for a
     * path to the (possibly unreachable or very far away) target position polygon. In this case the
     * pathfinding resets and builds a path from the start polygon to the polygon that was found
     * closest to the target position so far. A value of `0` or below counts as unlimited. In case of
     * unlimited the pathfinding will search all polygons connected with the start polygon until either
     * the target position polygon is found or all available polygon search options are exhausted.
     *
     * Generated from Godot docs: NavigationAgent3D.set_path_search_max_polygons
     */
    fun setPathSearchMaxPolygons(maxPolygons: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setPathSearchMaxPolygonsBind, segment, maxPolygons)
    }

    /**
     * The maximum number of polygons that are searched before the pathfinding cancels the search for a
     * path to the (possibly unreachable or very far away) target position polygon. In this case the
     * pathfinding resets and builds a path from the start polygon to the polygon that was found
     * closest to the target position so far. A value of `0` or below counts as unlimited. In case of
     * unlimited the pathfinding will search all polygons connected with the start polygon until either
     * the target position polygon is found or all available polygon search options are exhausted.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_search_max_polygons
     */
    fun getPathSearchMaxPolygons(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getPathSearchMaxPolygonsBind, segment)
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
     * Generated from Godot docs: NavigationAgent3D.set_path_search_max_distance
     */
    fun setPathSearchMaxDistance(distance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPathSearchMaxDistanceBind, segment, distance)
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
     * Generated from Godot docs: NavigationAgent3D.get_path_search_max_distance
     */
    fun getPathSearchMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathSearchMaxDistanceBind, segment)
    }

    /**
     * Returns the length of the currently calculated path. The returned value is `0.0`, if the path is
     * still calculating or no calculation has been requested yet.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_length
     */
    fun getPathLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPathLengthBind, segment)
    }

    /**
     * Returns the next position in global coordinates that can be moved to, making sure that there are
     * no static objects in the way. If the agent does not have a navigation path, it will return the
     * position of the agent's parent. The use of this function once every physics frame is required to
     * update the internal path logic of the NavigationAgent.
     *
     * Generated from Godot docs: NavigationAgent3D.get_next_path_position
     */
    fun getNextPathPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getNextPathPositionBind, segment)
    }

    /**
     * Replaces the internal velocity in the collision avoidance simulation with `velocity`. When an
     * agent is teleported to a new position this function should be used in the same frame. If called
     * frequently this function can get agents stuck.
     *
     * Generated from Godot docs: NavigationAgent3D.set_velocity_forced
     */
    fun setVelocityForced(velocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setVelocityForcedBind, segment, velocity)
    }

    /**
     * Sets the new wanted velocity for the agent. The avoidance simulation will try to fulfill this
     * velocity if possible but will modify it to avoid collision with other agents and obstacles. When
     * an agent is teleported to a new position, use `set_velocity_forced` as well to reset the
     * internal simulation velocity.
     *
     * Generated from Godot docs: NavigationAgent3D.set_velocity
     */
    fun setVelocity(velocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setVelocityBind, segment, velocity)
    }

    /**
     * Sets the new wanted velocity for the agent. The avoidance simulation will try to fulfill this
     * velocity if possible but will modify it to avoid collision with other agents and obstacles. When
     * an agent is teleported to a new position, use `set_velocity_forced` as well to reset the
     * internal simulation velocity.
     *
     * Generated from Godot docs: NavigationAgent3D.get_velocity
     */
    fun getVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getVelocityBind, segment)
    }

    /**
     * Returns the distance to the target position, using the agent's global position. The user must
     * set `target_position` in order for this to be accurate.
     *
     * Generated from Godot docs: NavigationAgent3D.distance_to_target
     */
    fun distanceToTarget(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.distanceToTargetBind, segment)
    }

    /**
     * Returns the path query result for the path the agent is currently following.
     *
     * Generated from Godot docs: NavigationAgent3D.get_current_navigation_result
     */
    fun getCurrentNavigationResult(): NavigationPathQueryResult3D? {
        return NavigationPathQueryResult3D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getCurrentNavigationResultBind, segment))
    }

    /**
     * Returns this agent's current path from start to finish in global coordinates. The path only
     * updates when the target position is changed or the agent requires a repath. The path array is
     * not intended to be used in direct path movement as the agent has its own internal path logic
     * that would get corrupted by changing the path array manually. Use the intended
     * `get_next_path_position` once every physics frame to receive the next path point for the agents
     * movement as this function also updates the internal path logic.
     *
     * Generated from Godot docs: NavigationAgent3D.get_current_navigation_path
     */
    fun getCurrentNavigationPath(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(Binds.getCurrentNavigationPathBind, segment)
    }

    /**
     * Returns which index the agent is currently on in the navigation path's `PackedVector3Array`.
     *
     * Generated from Godot docs: NavigationAgent3D.get_current_navigation_path_index
     */
    fun getCurrentNavigationPathIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getCurrentNavigationPathIndexBind, segment)
    }

    /**
     * Returns `true` if the agent reached the target, i.e. the agent moved within
     * `target_desired_distance` of the `target_position`. It may not always be possible to reach the
     * target but it should always be possible to reach the final position. See `get_final_position`.
     *
     * Generated from Godot docs: NavigationAgent3D.is_target_reached
     */
    fun isTargetReached(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isTargetReachedBind, segment)
    }

    /**
     * Returns `true` if `get_final_position` is within `target_desired_distance` of the
     * `target_position`.
     *
     * Generated from Godot docs: NavigationAgent3D.is_target_reachable
     */
    fun isTargetReachable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isTargetReachableBind, segment)
    }

    /**
     * Returns `true` if the agent's navigation has finished. If the target is reachable, navigation
     * ends when the target is reached. If the target is unreachable, navigation ends when the last
     * waypoint of the path is reached. Note: While `true` prefer to stop calling update functions like
     * `get_next_path_position`. This avoids jittering the standing agent due to calling repeated path
     * updates.
     *
     * Generated from Godot docs: NavigationAgent3D.is_navigation_finished
     */
    fun isNavigationFinished(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isNavigationFinishedBind, segment)
    }

    /**
     * Returns the reachable final position of the current navigation path in global coordinates. This
     * position can change if the agent needs to update the navigation path which makes the agent emit
     * the `path_changed` signal.
     *
     * Generated from Godot docs: NavigationAgent3D.get_final_position
     */
    fun getFinalPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getFinalPositionBind, segment)
    }

    /**
     * A bitfield determining the avoidance layers for this NavigationAgent. Other agents with a
     * matching bit on the `avoidance_mask` will avoid this agent.
     *
     * Generated from Godot docs: NavigationAgent3D.set_avoidance_layers
     */
    fun setAvoidanceLayers(layers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setAvoidanceLayersBind, segment, layers)
    }

    /**
     * A bitfield determining the avoidance layers for this NavigationAgent. Other agents with a
     * matching bit on the `avoidance_mask` will avoid this agent.
     *
     * Generated from Godot docs: NavigationAgent3D.get_avoidance_layers
     */
    fun getAvoidanceLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getAvoidanceLayersBind, segment)
    }

    /**
     * A bitfield determining what other avoidance agents and obstacles this NavigationAgent will avoid
     * when a bit matches at least one of their `avoidance_layers`.
     *
     * Generated from Godot docs: NavigationAgent3D.set_avoidance_mask
     */
    fun setAvoidanceMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setAvoidanceMaskBind, segment, mask)
    }

    /**
     * A bitfield determining what other avoidance agents and obstacles this NavigationAgent will avoid
     * when a bit matches at least one of their `avoidance_layers`.
     *
     * Generated from Godot docs: NavigationAgent3D.get_avoidance_mask
     */
    fun getAvoidanceMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getAvoidanceMaskBind, segment)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `avoidance_layers` bitmask,
     * given a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationAgent3D.set_avoidance_layer_value
     */
    fun setAvoidanceLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setAvoidanceLayerValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `avoidance_layers` bitmask is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationAgent3D.get_avoidance_layer_value
     */
    fun getAvoidanceLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getAvoidanceLayerValueBind, segment, layerNumber)
    }

    /**
     * Based on `value`, enables or disables the specified mask in the `avoidance_mask` bitmask, given
     * a `mask_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationAgent3D.set_avoidance_mask_value
     */
    fun setAvoidanceMaskValue(maskNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setAvoidanceMaskValueBind, segment, maskNumber, value)
    }

    /**
     * Returns whether or not the specified mask of the `avoidance_mask` bitmask is enabled, given a
     * `mask_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationAgent3D.get_avoidance_mask_value
     */
    fun getAvoidanceMaskValue(maskNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getAvoidanceMaskValueBind, segment, maskNumber)
    }

    /**
     * The agent does not adjust the velocity for other agents that would match the `avoidance_mask`
     * but have a lower `avoidance_priority`. This in turn makes the other agents with lower priority
     * adjust their velocities even more to avoid collision with this agent.
     *
     * Generated from Godot docs: NavigationAgent3D.set_avoidance_priority
     */
    fun setAvoidancePriority(priority: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAvoidancePriorityBind, segment, priority)
    }

    /**
     * The agent does not adjust the velocity for other agents that would match the `avoidance_mask`
     * but have a lower `avoidance_priority`. This in turn makes the other agents with lower priority
     * adjust their velocities even more to avoid collision with this agent.
     *
     * Generated from Godot docs: NavigationAgent3D.get_avoidance_priority
     */
    fun getAvoidancePriority(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAvoidancePriorityBind, segment)
    }

    /**
     * If `true` shows debug visuals for this agent.
     *
     * Generated from Godot docs: NavigationAgent3D.set_debug_enabled
     */
    fun setDebugEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDebugEnabledBind, segment, enabled)
    }

    /**
     * If `true` shows debug visuals for this agent.
     *
     * Generated from Godot docs: NavigationAgent3D.get_debug_enabled
     */
    fun getDebugEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getDebugEnabledBind, segment)
    }

    /**
     * If `true` uses the defined `debug_path_custom_color` for this agent instead of global color.
     *
     * Generated from Godot docs: NavigationAgent3D.set_debug_use_custom
     */
    fun setDebugUseCustom(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setDebugUseCustomBind, segment, enabled)
    }

    /**
     * If `true` uses the defined `debug_path_custom_color` for this agent instead of global color.
     *
     * Generated from Godot docs: NavigationAgent3D.get_debug_use_custom
     */
    fun getDebugUseCustom(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getDebugUseCustomBind, segment)
    }

    /**
     * If `debug_use_custom` is `true` uses this color for this agent instead of global color.
     *
     * Generated from Godot docs: NavigationAgent3D.set_debug_path_custom_color
     */
    fun setDebugPathCustomColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(Binds.setDebugPathCustomColorBind, segment, color)
    }

    /**
     * If `debug_use_custom` is `true` uses this color for this agent instead of global color.
     *
     * Generated from Godot docs: NavigationAgent3D.get_debug_path_custom_color
     */
    fun getDebugPathCustomColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(Binds.getDebugPathCustomColorBind, segment)
    }

    /**
     * If `debug_use_custom` is `true` uses this rasterized point size for rendering path points for
     * this agent instead of global point size.
     *
     * Generated from Godot docs: NavigationAgent3D.set_debug_path_custom_point_size
     */
    fun setDebugPathCustomPointSize(pointSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDebugPathCustomPointSizeBind, segment, pointSize)
    }

    /**
     * If `debug_use_custom` is `true` uses this rasterized point size for rendering path points for
     * this agent instead of global point size.
     *
     * Generated from Godot docs: NavigationAgent3D.get_debug_path_custom_point_size
     */
    fun getDebugPathCustomPointSize(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDebugPathCustomPointSizeBind, segment)
    }

    /** Signal `path_changed()`; see [TypedSignal]. */
    val pathChanged: Signal0
        @JvmName("pathChangedTypedSignal")
        get() = Signal0(this, "path_changed")

    /** Signal `target_reached()`; see [TypedSignal]. */
    val targetReached: Signal0
        @JvmName("targetReachedTypedSignal")
        get() = Signal0(this, "target_reached")

    /** Signal `waypoint_reached(details: Dictionary)`; see [TypedSignal]. On iOS a Dictionary argument is not delivered yet: a connection reports a script error. */
    val waypointReached: Signal1<Map<Any?, Any?>>
        @JvmName("waypointReachedTypedSignal")
        get() = Signal1(this, "waypoint_reached", SignalArgType.valueOf<Map<Any?, Any?>>("Dictionary", Map::class))

    /** Signal `link_reached(details: Dictionary)`; see [TypedSignal]. On iOS a Dictionary argument is not delivered yet: a connection reports a script error. */
    val linkReached: Signal1<Map<Any?, Any?>>
        @JvmName("linkReachedTypedSignal")
        get() = Signal1(this, "link_reached", SignalArgType.valueOf<Map<Any?, Any?>>("Dictionary", Map::class))

    /** Signal `navigation_finished()`; see [TypedSignal]. */
    val navigationFinished: Signal0
        @JvmName("navigationFinishedTypedSignal")
        get() = Signal0(this, "navigation_finished")

    /** Signal `velocity_computed(safe_velocity: Vector3)`; see [TypedSignal]. */
    val velocityComputed: Signal1<Vector3>
        @JvmName("velocityComputedTypedSignal")
        get() = Signal1(this, "velocity_computed", SignalArgType.valueOf<Vector3>("Vector3", Vector3::class))

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
        fun fromHandle(handle: GodotHandle): NavigationAgent3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): NavigationAgent3D? =
            if (handle.address() == 0L) null else NavigationAgent3D(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_RID_HASH = 2944877500L
        @JvmField
        val getRidBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_rid", GET_RID_HASH)

        private const val SET_AVOIDANCE_ENABLED_HASH = 2586408642L
        @JvmField
        val setAvoidanceEnabledBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_avoidance_enabled", SET_AVOIDANCE_ENABLED_HASH)

        private const val GET_AVOIDANCE_ENABLED_HASH = 36873697L
        @JvmField
        val getAvoidanceEnabledBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_avoidance_enabled", GET_AVOIDANCE_ENABLED_HASH)

        private const val SET_PATH_DESIRED_DISTANCE_HASH = 373806689L
        @JvmField
        val setPathDesiredDistanceBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_desired_distance", SET_PATH_DESIRED_DISTANCE_HASH)

        private const val GET_PATH_DESIRED_DISTANCE_HASH = 1740695150L
        @JvmField
        val getPathDesiredDistanceBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_desired_distance", GET_PATH_DESIRED_DISTANCE_HASH)

        private const val SET_TARGET_DESIRED_DISTANCE_HASH = 373806689L
        @JvmField
        val setTargetDesiredDistanceBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_target_desired_distance", SET_TARGET_DESIRED_DISTANCE_HASH)

        private const val GET_TARGET_DESIRED_DISTANCE_HASH = 1740695150L
        @JvmField
        val getTargetDesiredDistanceBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_target_desired_distance", GET_TARGET_DESIRED_DISTANCE_HASH)

        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_radius", GET_RADIUS_HASH)

        private const val SET_HEIGHT_HASH = 373806689L
        @JvmField
        val setHeightBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_height", SET_HEIGHT_HASH)

        private const val GET_HEIGHT_HASH = 1740695150L
        @JvmField
        val getHeightBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_height", GET_HEIGHT_HASH)

        private const val SET_PATH_HEIGHT_OFFSET_HASH = 373806689L
        @JvmField
        val setPathHeightOffsetBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_height_offset", SET_PATH_HEIGHT_OFFSET_HASH)

        private const val GET_PATH_HEIGHT_OFFSET_HASH = 1740695150L
        @JvmField
        val getPathHeightOffsetBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_height_offset", GET_PATH_HEIGHT_OFFSET_HASH)

        private const val SET_USE_3D_AVOIDANCE_HASH = 2586408642L
        @JvmField
        val setUse3dAvoidanceBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_use_3d_avoidance", SET_USE_3D_AVOIDANCE_HASH)

        private const val GET_USE_3D_AVOIDANCE_HASH = 36873697L
        @JvmField
        val getUse3dAvoidanceBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_use_3d_avoidance", GET_USE_3D_AVOIDANCE_HASH)

        private const val SET_KEEP_Y_VELOCITY_HASH = 2586408642L
        @JvmField
        val setKeepYVelocityBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_keep_y_velocity", SET_KEEP_Y_VELOCITY_HASH)

        private const val GET_KEEP_Y_VELOCITY_HASH = 36873697L
        @JvmField
        val getKeepYVelocityBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_keep_y_velocity", GET_KEEP_Y_VELOCITY_HASH)

        private const val SET_NEIGHBOR_DISTANCE_HASH = 373806689L
        @JvmField
        val setNeighborDistanceBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_neighbor_distance", SET_NEIGHBOR_DISTANCE_HASH)

        private const val GET_NEIGHBOR_DISTANCE_HASH = 1740695150L
        @JvmField
        val getNeighborDistanceBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_neighbor_distance", GET_NEIGHBOR_DISTANCE_HASH)

        private const val SET_MAX_NEIGHBORS_HASH = 1286410249L
        @JvmField
        val setMaxNeighborsBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_max_neighbors", SET_MAX_NEIGHBORS_HASH)

        private const val GET_MAX_NEIGHBORS_HASH = 3905245786L
        @JvmField
        val getMaxNeighborsBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_max_neighbors", GET_MAX_NEIGHBORS_HASH)

        private const val SET_TIME_HORIZON_AGENTS_HASH = 373806689L
        @JvmField
        val setTimeHorizonAgentsBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_time_horizon_agents", SET_TIME_HORIZON_AGENTS_HASH)

        private const val GET_TIME_HORIZON_AGENTS_HASH = 1740695150L
        @JvmField
        val getTimeHorizonAgentsBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_time_horizon_agents", GET_TIME_HORIZON_AGENTS_HASH)

        private const val SET_TIME_HORIZON_OBSTACLES_HASH = 373806689L
        @JvmField
        val setTimeHorizonObstaclesBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_time_horizon_obstacles", SET_TIME_HORIZON_OBSTACLES_HASH)

        private const val GET_TIME_HORIZON_OBSTACLES_HASH = 1740695150L
        @JvmField
        val getTimeHorizonObstaclesBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_time_horizon_obstacles", GET_TIME_HORIZON_OBSTACLES_HASH)

        private const val SET_MAX_SPEED_HASH = 373806689L
        @JvmField
        val setMaxSpeedBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_max_speed", SET_MAX_SPEED_HASH)

        private const val GET_MAX_SPEED_HASH = 1740695150L
        @JvmField
        val getMaxSpeedBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_max_speed", GET_MAX_SPEED_HASH)

        private const val SET_PATH_MAX_DISTANCE_HASH = 373806689L
        @JvmField
        val setPathMaxDistanceBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_max_distance", SET_PATH_MAX_DISTANCE_HASH)

        private const val GET_PATH_MAX_DISTANCE_HASH = 191475506L
        @JvmField
        val getPathMaxDistanceBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_max_distance", GET_PATH_MAX_DISTANCE_HASH)

        private const val SET_NAVIGATION_LAYERS_HASH = 1286410249L
        @JvmField
        val setNavigationLayersBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_navigation_layers", SET_NAVIGATION_LAYERS_HASH)

        private const val GET_NAVIGATION_LAYERS_HASH = 3905245786L
        @JvmField
        val getNavigationLayersBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_navigation_layers", GET_NAVIGATION_LAYERS_HASH)

        private const val SET_NAVIGATION_LAYER_VALUE_HASH = 300928843L
        @JvmField
        val setNavigationLayerValueBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_navigation_layer_value", SET_NAVIGATION_LAYER_VALUE_HASH)

        private const val GET_NAVIGATION_LAYER_VALUE_HASH = 1116898809L
        @JvmField
        val getNavigationLayerValueBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_navigation_layer_value", GET_NAVIGATION_LAYER_VALUE_HASH)

        private const val SET_PATHFINDING_ALGORITHM_HASH = 394560454L
        @JvmField
        val setPathfindingAlgorithmBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_pathfinding_algorithm", SET_PATHFINDING_ALGORITHM_HASH)

        private const val GET_PATHFINDING_ALGORITHM_HASH = 3398491350L
        @JvmField
        val getPathfindingAlgorithmBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_pathfinding_algorithm", GET_PATHFINDING_ALGORITHM_HASH)

        private const val SET_PATH_POSTPROCESSING_HASH = 2267362344L
        @JvmField
        val setPathPostprocessingBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_postprocessing", SET_PATH_POSTPROCESSING_HASH)

        private const val GET_PATH_POSTPROCESSING_HASH = 3883858360L
        @JvmField
        val getPathPostprocessingBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_postprocessing", GET_PATH_POSTPROCESSING_HASH)

        private const val SET_PATH_METADATA_FLAGS_HASH = 2713846708L
        @JvmField
        val setPathMetadataFlagsBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_metadata_flags", SET_PATH_METADATA_FLAGS_HASH)

        private const val GET_PATH_METADATA_FLAGS_HASH = 1582332802L
        @JvmField
        val getPathMetadataFlagsBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_metadata_flags", GET_PATH_METADATA_FLAGS_HASH)

        private const val SET_NAVIGATION_MAP_HASH = 2722037293L
        @JvmField
        val setNavigationMapBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_navigation_map", SET_NAVIGATION_MAP_HASH)

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        @JvmField
        val getNavigationMapBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_navigation_map", GET_NAVIGATION_MAP_HASH)

        private const val SET_TARGET_POSITION_HASH = 3460891852L
        @JvmField
        val setTargetPositionBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_target_position", SET_TARGET_POSITION_HASH)

        private const val GET_TARGET_POSITION_HASH = 3360562783L
        @JvmField
        val getTargetPositionBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_target_position", GET_TARGET_POSITION_HASH)

        private const val SET_SIMPLIFY_PATH_HASH = 2586408642L
        @JvmField
        val setSimplifyPathBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_simplify_path", SET_SIMPLIFY_PATH_HASH)

        private const val GET_SIMPLIFY_PATH_HASH = 36873697L
        @JvmField
        val getSimplifyPathBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_simplify_path", GET_SIMPLIFY_PATH_HASH)

        private const val SET_SIMPLIFY_EPSILON_HASH = 373806689L
        @JvmField
        val setSimplifyEpsilonBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_simplify_epsilon", SET_SIMPLIFY_EPSILON_HASH)

        private const val GET_SIMPLIFY_EPSILON_HASH = 1740695150L
        @JvmField
        val getSimplifyEpsilonBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_simplify_epsilon", GET_SIMPLIFY_EPSILON_HASH)

        private const val SET_PATH_RETURN_MAX_LENGTH_HASH = 373806689L
        @JvmField
        val setPathReturnMaxLengthBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_return_max_length", SET_PATH_RETURN_MAX_LENGTH_HASH)

        private const val GET_PATH_RETURN_MAX_LENGTH_HASH = 1740695150L
        @JvmField
        val getPathReturnMaxLengthBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_return_max_length", GET_PATH_RETURN_MAX_LENGTH_HASH)

        private const val SET_PATH_RETURN_MAX_RADIUS_HASH = 373806689L
        @JvmField
        val setPathReturnMaxRadiusBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_return_max_radius", SET_PATH_RETURN_MAX_RADIUS_HASH)

        private const val GET_PATH_RETURN_MAX_RADIUS_HASH = 1740695150L
        @JvmField
        val getPathReturnMaxRadiusBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_return_max_radius", GET_PATH_RETURN_MAX_RADIUS_HASH)

        private const val SET_PATH_SEARCH_MAX_POLYGONS_HASH = 1286410249L
        @JvmField
        val setPathSearchMaxPolygonsBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_search_max_polygons", SET_PATH_SEARCH_MAX_POLYGONS_HASH)

        private const val GET_PATH_SEARCH_MAX_POLYGONS_HASH = 3905245786L
        @JvmField
        val getPathSearchMaxPolygonsBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_search_max_polygons", GET_PATH_SEARCH_MAX_POLYGONS_HASH)

        private const val SET_PATH_SEARCH_MAX_DISTANCE_HASH = 373806689L
        @JvmField
        val setPathSearchMaxDistanceBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_path_search_max_distance", SET_PATH_SEARCH_MAX_DISTANCE_HASH)

        private const val GET_PATH_SEARCH_MAX_DISTANCE_HASH = 1740695150L
        @JvmField
        val getPathSearchMaxDistanceBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_search_max_distance", GET_PATH_SEARCH_MAX_DISTANCE_HASH)

        private const val GET_PATH_LENGTH_HASH = 1740695150L
        @JvmField
        val getPathLengthBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_path_length", GET_PATH_LENGTH_HASH)

        private const val GET_NEXT_PATH_POSITION_HASH = 3783033775L
        @JvmField
        val getNextPathPositionBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_next_path_position", GET_NEXT_PATH_POSITION_HASH)

        private const val SET_VELOCITY_FORCED_HASH = 3460891852L
        @JvmField
        val setVelocityForcedBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_velocity_forced", SET_VELOCITY_FORCED_HASH)

        private const val SET_VELOCITY_HASH = 3460891852L
        @JvmField
        val setVelocityBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_velocity", SET_VELOCITY_HASH)

        private const val GET_VELOCITY_HASH = 3783033775L
        @JvmField
        val getVelocityBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_velocity", GET_VELOCITY_HASH)

        private const val DISTANCE_TO_TARGET_HASH = 1740695150L
        @JvmField
        val distanceToTargetBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "distance_to_target", DISTANCE_TO_TARGET_HASH)

        private const val GET_CURRENT_NAVIGATION_RESULT_HASH = 728825684L
        @JvmField
        val getCurrentNavigationResultBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_current_navigation_result", GET_CURRENT_NAVIGATION_RESULT_HASH)

        private const val GET_CURRENT_NAVIGATION_PATH_HASH = 497664490L
        @JvmField
        val getCurrentNavigationPathBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_current_navigation_path", GET_CURRENT_NAVIGATION_PATH_HASH)

        private const val GET_CURRENT_NAVIGATION_PATH_INDEX_HASH = 3905245786L
        @JvmField
        val getCurrentNavigationPathIndexBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_current_navigation_path_index", GET_CURRENT_NAVIGATION_PATH_INDEX_HASH)

        private const val IS_TARGET_REACHED_HASH = 36873697L
        @JvmField
        val isTargetReachedBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "is_target_reached", IS_TARGET_REACHED_HASH)

        private const val IS_TARGET_REACHABLE_HASH = 2240911060L
        @JvmField
        val isTargetReachableBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "is_target_reachable", IS_TARGET_REACHABLE_HASH)

        private const val IS_NAVIGATION_FINISHED_HASH = 2240911060L
        @JvmField
        val isNavigationFinishedBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "is_navigation_finished", IS_NAVIGATION_FINISHED_HASH)

        private const val GET_FINAL_POSITION_HASH = 3783033775L
        @JvmField
        val getFinalPositionBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_final_position", GET_FINAL_POSITION_HASH)

        private const val SET_AVOIDANCE_LAYERS_HASH = 1286410249L
        @JvmField
        val setAvoidanceLayersBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_avoidance_layers", SET_AVOIDANCE_LAYERS_HASH)

        private const val GET_AVOIDANCE_LAYERS_HASH = 3905245786L
        @JvmField
        val getAvoidanceLayersBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_avoidance_layers", GET_AVOIDANCE_LAYERS_HASH)

        private const val SET_AVOIDANCE_MASK_HASH = 1286410249L
        @JvmField
        val setAvoidanceMaskBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_avoidance_mask", SET_AVOIDANCE_MASK_HASH)

        private const val GET_AVOIDANCE_MASK_HASH = 3905245786L
        @JvmField
        val getAvoidanceMaskBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_avoidance_mask", GET_AVOIDANCE_MASK_HASH)

        private const val SET_AVOIDANCE_LAYER_VALUE_HASH = 300928843L
        @JvmField
        val setAvoidanceLayerValueBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_avoidance_layer_value", SET_AVOIDANCE_LAYER_VALUE_HASH)

        private const val GET_AVOIDANCE_LAYER_VALUE_HASH = 1116898809L
        @JvmField
        val getAvoidanceLayerValueBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_avoidance_layer_value", GET_AVOIDANCE_LAYER_VALUE_HASH)

        private const val SET_AVOIDANCE_MASK_VALUE_HASH = 300928843L
        @JvmField
        val setAvoidanceMaskValueBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_avoidance_mask_value", SET_AVOIDANCE_MASK_VALUE_HASH)

        private const val GET_AVOIDANCE_MASK_VALUE_HASH = 1116898809L
        @JvmField
        val getAvoidanceMaskValueBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_avoidance_mask_value", GET_AVOIDANCE_MASK_VALUE_HASH)

        private const val SET_AVOIDANCE_PRIORITY_HASH = 373806689L
        @JvmField
        val setAvoidancePriorityBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_avoidance_priority", SET_AVOIDANCE_PRIORITY_HASH)

        private const val GET_AVOIDANCE_PRIORITY_HASH = 1740695150L
        @JvmField
        val getAvoidancePriorityBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_avoidance_priority", GET_AVOIDANCE_PRIORITY_HASH)

        private const val SET_DEBUG_ENABLED_HASH = 2586408642L
        @JvmField
        val setDebugEnabledBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_debug_enabled", SET_DEBUG_ENABLED_HASH)

        private const val GET_DEBUG_ENABLED_HASH = 36873697L
        @JvmField
        val getDebugEnabledBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_debug_enabled", GET_DEBUG_ENABLED_HASH)

        private const val SET_DEBUG_USE_CUSTOM_HASH = 2586408642L
        @JvmField
        val setDebugUseCustomBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_debug_use_custom", SET_DEBUG_USE_CUSTOM_HASH)

        private const val GET_DEBUG_USE_CUSTOM_HASH = 36873697L
        @JvmField
        val getDebugUseCustomBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_debug_use_custom", GET_DEBUG_USE_CUSTOM_HASH)

        private const val SET_DEBUG_PATH_CUSTOM_COLOR_HASH = 2920490490L
        @JvmField
        val setDebugPathCustomColorBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_debug_path_custom_color", SET_DEBUG_PATH_CUSTOM_COLOR_HASH)

        private const val GET_DEBUG_PATH_CUSTOM_COLOR_HASH = 3444240500L
        @JvmField
        val getDebugPathCustomColorBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_debug_path_custom_color", GET_DEBUG_PATH_CUSTOM_COLOR_HASH)

        private const val SET_DEBUG_PATH_CUSTOM_POINT_SIZE_HASH = 373806689L
        @JvmField
        val setDebugPathCustomPointSizeBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "set_debug_path_custom_point_size", SET_DEBUG_PATH_CUSTOM_POINT_SIZE_HASH)

        private const val GET_DEBUG_PATH_CUSTOM_POINT_SIZE_HASH = 1740695150L
        @JvmField
        val getDebugPathCustomPointSizeBind =
            ObjectCalls.getMethodBind("NavigationAgent3D", "get_debug_path_custom_point_size", GET_DEBUG_PATH_CUSTOM_POINT_SIZE_HASH)
    }
}
