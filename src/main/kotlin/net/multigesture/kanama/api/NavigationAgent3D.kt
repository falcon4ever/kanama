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

    /**
     * Returns the `RID` of this agent on the `NavigationServer3D`.
     *
     * Generated from Godot docs: NavigationAgent3D.get_rid
     */
    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setAvoidanceEnabledBind, handle, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(getAvoidanceEnabledBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setPathDesiredDistanceBind, handle, desiredDistance)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathDesiredDistanceBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setTargetDesiredDistanceBind, handle, desiredDistance)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getTargetDesiredDistanceBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    /**
     * The height of the avoidance agent. Agents will ignore other agents or obstacles that are above
     * or below their current position + height in 2D avoidance. Does nothing in 3D avoidance which
     * uses radius spheres alone.
     *
     * Generated from Godot docs: NavigationAgent3D.set_height
     */
    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeightBind, handle, height)
    }

    /**
     * The height of the avoidance agent. Agents will ignore other agents or obstacles that are above
     * or below their current position + height in 2D avoidance. Does nothing in 3D avoidance which
     * uses radius spheres alone.
     *
     * Generated from Godot docs: NavigationAgent3D.get_height
     */
    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setPathHeightOffsetBind, handle, pathHeightOffset)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathHeightOffsetBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setUse3dAvoidanceBind, handle, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(getUse3dAvoidanceBind, handle)
    }

    /**
     * If `true`, and the agent uses 2D avoidance, it will remember the set y-axis velocity and reapply
     * it after the avoidance step. While 2D avoidance has no y-axis and simulates on a flat plane this
     * setting can help to soften the most obvious clipping on uneven 3D geometry.
     *
     * Generated from Godot docs: NavigationAgent3D.set_keep_y_velocity
     */
    fun setKeepYVelocity(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setKeepYVelocityBind, handle, enabled)
    }

    /**
     * If `true`, and the agent uses 2D avoidance, it will remember the set y-axis velocity and reapply
     * it after the avoidance step. While 2D avoidance has no y-axis and simulates on a flat plane this
     * setting can help to soften the most obvious clipping on uneven 3D geometry.
     *
     * Generated from Godot docs: NavigationAgent3D.get_keep_y_velocity
     */
    fun getKeepYVelocity(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getKeepYVelocityBind, handle)
    }

    /**
     * The distance to search for other agents.
     *
     * Generated from Godot docs: NavigationAgent3D.set_neighbor_distance
     */
    fun setNeighborDistance(neighborDistance: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setNeighborDistanceBind, handle, neighborDistance)
    }

    /**
     * The distance to search for other agents.
     *
     * Generated from Godot docs: NavigationAgent3D.get_neighbor_distance
     */
    fun getNeighborDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getNeighborDistanceBind, handle)
    }

    /**
     * The maximum number of neighbors for the agent to consider.
     *
     * Generated from Godot docs: NavigationAgent3D.set_max_neighbors
     */
    fun setMaxNeighbors(maxNeighbors: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxNeighborsBind, handle, maxNeighbors)
    }

    /**
     * The maximum number of neighbors for the agent to consider.
     *
     * Generated from Godot docs: NavigationAgent3D.get_max_neighbors
     */
    fun getMaxNeighbors(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxNeighborsBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setTimeHorizonAgentsBind, handle, timeHorizon)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimeHorizonAgentsBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setTimeHorizonObstaclesBind, handle, timeHorizon)
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
        return ObjectCalls.ptrcallNoArgsRetDouble(getTimeHorizonObstaclesBind, handle)
    }

    /**
     * The maximum speed that an agent can move.
     *
     * Generated from Godot docs: NavigationAgent3D.set_max_speed
     */
    fun setMaxSpeed(maxSpeed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMaxSpeedBind, handle, maxSpeed)
    }

    /**
     * The maximum speed that an agent can move.
     *
     * Generated from Godot docs: NavigationAgent3D.get_max_speed
     */
    fun getMaxSpeed(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMaxSpeedBind, handle)
    }

    /**
     * The maximum distance the agent is allowed away from the ideal path to the final position. This
     * can happen due to trying to avoid collisions. When the maximum distance is exceeded, it
     * recalculates the ideal path.
     *
     * Generated from Godot docs: NavigationAgent3D.set_path_max_distance
     */
    fun setPathMaxDistance(maxSpeed: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathMaxDistanceBind, handle, maxSpeed)
    }

    /**
     * The maximum distance the agent is allowed away from the ideal path to the final position. This
     * can happen due to trying to avoid collisions. When the maximum distance is exceeded, it
     * recalculates the ideal path.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_max_distance
     */
    fun getPathMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathMaxDistanceBind, handle)
    }

    /**
     * A bitfield determining which navigation layers of navigation regions this agent will use to
     * calculate a path. Changing it during runtime will clear the current navigation path and generate
     * a new one, according to the new navigation layers.
     *
     * Generated from Godot docs: NavigationAgent3D.set_navigation_layers
     */
    fun setNavigationLayers(navigationLayers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setNavigationLayersBind, handle, navigationLayers)
    }

    /**
     * A bitfield determining which navigation layers of navigation regions this agent will use to
     * calculate a path. Changing it during runtime will clear the current navigation path and generate
     * a new one, according to the new navigation layers.
     *
     * Generated from Godot docs: NavigationAgent3D.get_navigation_layers
     */
    fun getNavigationLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getNavigationLayersBind, handle)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `navigation_layers` bitmask,
     * given a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationAgent3D.set_navigation_layer_value
     */
    fun setNavigationLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setNavigationLayerValueBind, handle, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `navigation_layers` bitmask is enabled, given
     * a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationAgent3D.get_navigation_layer_value
     */
    fun getNavigationLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getNavigationLayerValueBind, handle, layerNumber)
    }

    /**
     * The pathfinding algorithm used in the path query.
     *
     * Generated from Godot docs: NavigationAgent3D.set_pathfinding_algorithm
     */
    fun setPathfindingAlgorithm(pathfindingAlgorithm: Long) {
        ObjectCalls.ptrcallWithLongArg(setPathfindingAlgorithmBind, handle, pathfindingAlgorithm)
    }

    /**
     * The pathfinding algorithm used in the path query.
     *
     * Generated from Godot docs: NavigationAgent3D.get_pathfinding_algorithm
     */
    fun getPathfindingAlgorithm(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPathfindingAlgorithmBind, handle)
    }

    /**
     * The path postprocessing applied to the raw path corridor found by the `pathfinding_algorithm`.
     *
     * Generated from Godot docs: NavigationAgent3D.set_path_postprocessing
     */
    fun setPathPostprocessing(pathPostprocessing: Long) {
        ObjectCalls.ptrcallWithLongArg(setPathPostprocessingBind, handle, pathPostprocessing)
    }

    /**
     * The path postprocessing applied to the raw path corridor found by the `pathfinding_algorithm`.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_postprocessing
     */
    fun getPathPostprocessing(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPathPostprocessingBind, handle)
    }

    /**
     * Additional information to return with the navigation path.
     *
     * Generated from Godot docs: NavigationAgent3D.set_path_metadata_flags
     */
    fun setPathMetadataFlags(flags: Long) {
        ObjectCalls.ptrcallWithLongArg(setPathMetadataFlagsBind, handle, flags)
    }

    /**
     * Additional information to return with the navigation path.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_metadata_flags
     */
    fun getPathMetadataFlags(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getPathMetadataFlagsBind, handle)
    }

    /**
     * Sets the `RID` of the navigation map this NavigationAgent node should use and also updates the
     * `agent` on the NavigationServer.
     *
     * Generated from Godot docs: NavigationAgent3D.set_navigation_map
     */
    fun setNavigationMap(navigationMap: RID) {
        ObjectCalls.ptrcallWithRIDArg(setNavigationMapBind, handle, navigationMap)
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
        return ObjectCalls.ptrcallNoArgsRetRID(getNavigationMapBind, handle)
    }

    /**
     * If set, a new navigation path from the current agent position to the `target_position` is
     * requested from the NavigationServer.
     *
     * Generated from Godot docs: NavigationAgent3D.set_target_position
     */
    fun setTargetPosition(position: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setTargetPositionBind, handle, position)
    }

    /**
     * If set, a new navigation path from the current agent position to the `target_position` is
     * requested from the NavigationServer.
     *
     * Generated from Godot docs: NavigationAgent3D.get_target_position
     */
    fun getTargetPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getTargetPositionBind, handle)
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
        ObjectCalls.ptrcallWithBoolArg(setSimplifyPathBind, handle, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(getSimplifyPathBind, handle)
    }

    /**
     * The path simplification amount in worlds units.
     *
     * Generated from Godot docs: NavigationAgent3D.set_simplify_epsilon
     */
    fun setSimplifyEpsilon(epsilon: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSimplifyEpsilonBind, handle, epsilon)
    }

    /**
     * The path simplification amount in worlds units.
     *
     * Generated from Godot docs: NavigationAgent3D.get_simplify_epsilon
     */
    fun getSimplifyEpsilon(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSimplifyEpsilonBind, handle)
    }

    /**
     * The maximum allowed length of the returned path in world units. A path will be clipped when
     * going over this length.
     *
     * Generated from Godot docs: NavigationAgent3D.set_path_return_max_length
     */
    fun setPathReturnMaxLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setPathReturnMaxLengthBind, handle, length)
    }

    /**
     * The maximum allowed length of the returned path in world units. A path will be clipped when
     * going over this length.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_return_max_length
     */
    fun getPathReturnMaxLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathReturnMaxLengthBind, handle)
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
        ObjectCalls.ptrcallWithDoubleArg(setPathReturnMaxRadiusBind, handle, radius)
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
     * Generated from Godot docs: NavigationAgent3D.set_path_search_max_polygons
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
     * Generated from Godot docs: NavigationAgent3D.get_path_search_max_polygons
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
     * Generated from Godot docs: NavigationAgent3D.set_path_search_max_distance
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
     * Generated from Godot docs: NavigationAgent3D.get_path_search_max_distance
     */
    fun getPathSearchMaxDistance(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathSearchMaxDistanceBind, handle)
    }

    /**
     * Returns the length of the currently calculated path. The returned value is `0.0`, if the path is
     * still calculating or no calculation has been requested yet.
     *
     * Generated from Godot docs: NavigationAgent3D.get_path_length
     */
    fun getPathLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getPathLengthBind, handle)
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
        return ObjectCalls.ptrcallNoArgsRetVector3(getNextPathPositionBind, handle)
    }

    /**
     * Replaces the internal velocity in the collision avoidance simulation with `velocity`. When an
     * agent is teleported to a new position this function should be used in the same frame. If called
     * frequently this function can get agents stuck.
     *
     * Generated from Godot docs: NavigationAgent3D.set_velocity_forced
     */
    fun setVelocityForced(velocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setVelocityForcedBind, handle, velocity)
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
        ObjectCalls.ptrcallWithVector3Arg(setVelocityBind, handle, velocity)
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
        return ObjectCalls.ptrcallNoArgsRetVector3(getVelocityBind, handle)
    }

    /**
     * Returns the distance to the target position, using the agent's global position. The user must
     * set `target_position` in order for this to be accurate.
     *
     * Generated from Godot docs: NavigationAgent3D.distance_to_target
     */
    fun distanceToTarget(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(distanceToTargetBind, handle)
    }

    /**
     * Returns the path query result for the path the agent is currently following.
     *
     * Generated from Godot docs: NavigationAgent3D.get_current_navigation_result
     */
    fun getCurrentNavigationResult(): NavigationPathQueryResult3D? {
        return NavigationPathQueryResult3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getCurrentNavigationResultBind, handle))
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
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getCurrentNavigationPathBind, handle)
    }

    /**
     * Returns which index the agent is currently on in the navigation path's `PackedVector3Array`.
     *
     * Generated from Godot docs: NavigationAgent3D.get_current_navigation_path_index
     */
    fun getCurrentNavigationPathIndex(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getCurrentNavigationPathIndexBind, handle)
    }

    /**
     * Returns `true` if the agent reached the target, i.e. the agent moved within
     * `target_desired_distance` of the `target_position`. It may not always be possible to reach the
     * target but it should always be possible to reach the final position. See `get_final_position`.
     *
     * Generated from Godot docs: NavigationAgent3D.is_target_reached
     */
    fun isTargetReached(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTargetReachedBind, handle)
    }

    /**
     * Returns `true` if `get_final_position` is within `target_desired_distance` of the
     * `target_position`.
     *
     * Generated from Godot docs: NavigationAgent3D.is_target_reachable
     */
    fun isTargetReachable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isTargetReachableBind, handle)
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
        return ObjectCalls.ptrcallNoArgsRetBool(isNavigationFinishedBind, handle)
    }

    /**
     * Returns the reachable final position of the current navigation path in global coordinates. This
     * position can change if the agent needs to update the navigation path which makes the agent emit
     * the `path_changed` signal.
     *
     * Generated from Godot docs: NavigationAgent3D.get_final_position
     */
    fun getFinalPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getFinalPositionBind, handle)
    }

    /**
     * A bitfield determining the avoidance layers for this NavigationAgent. Other agents with a
     * matching bit on the `avoidance_mask` will avoid this agent.
     *
     * Generated from Godot docs: NavigationAgent3D.set_avoidance_layers
     */
    fun setAvoidanceLayers(layers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setAvoidanceLayersBind, handle, layers)
    }

    /**
     * A bitfield determining the avoidance layers for this NavigationAgent. Other agents with a
     * matching bit on the `avoidance_mask` will avoid this agent.
     *
     * Generated from Godot docs: NavigationAgent3D.get_avoidance_layers
     */
    fun getAvoidanceLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getAvoidanceLayersBind, handle)
    }

    /**
     * A bitfield determining what other avoidance agents and obstacles this NavigationAgent will avoid
     * when a bit matches at least one of their `avoidance_layers`.
     *
     * Generated from Godot docs: NavigationAgent3D.set_avoidance_mask
     */
    fun setAvoidanceMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setAvoidanceMaskBind, handle, mask)
    }

    /**
     * A bitfield determining what other avoidance agents and obstacles this NavigationAgent will avoid
     * when a bit matches at least one of their `avoidance_layers`.
     *
     * Generated from Godot docs: NavigationAgent3D.get_avoidance_mask
     */
    fun getAvoidanceMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getAvoidanceMaskBind, handle)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `avoidance_layers` bitmask,
     * given a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationAgent3D.set_avoidance_layer_value
     */
    fun setAvoidanceLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAvoidanceLayerValueBind, handle, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `avoidance_layers` bitmask is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationAgent3D.get_avoidance_layer_value
     */
    fun getAvoidanceLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getAvoidanceLayerValueBind, handle, layerNumber)
    }

    /**
     * Based on `value`, enables or disables the specified mask in the `avoidance_mask` bitmask, given
     * a `mask_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationAgent3D.set_avoidance_mask_value
     */
    fun setAvoidanceMaskValue(maskNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAvoidanceMaskValueBind, handle, maskNumber, value)
    }

    /**
     * Returns whether or not the specified mask of the `avoidance_mask` bitmask is enabled, given a
     * `mask_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationAgent3D.get_avoidance_mask_value
     */
    fun getAvoidanceMaskValue(maskNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getAvoidanceMaskValueBind, handle, maskNumber)
    }

    /**
     * The agent does not adjust the velocity for other agents that would match the `avoidance_mask`
     * but have a lower `avoidance_priority`. This in turn makes the other agents with lower priority
     * adjust their velocities even more to avoid collision with this agent.
     *
     * Generated from Godot docs: NavigationAgent3D.set_avoidance_priority
     */
    fun setAvoidancePriority(priority: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAvoidancePriorityBind, handle, priority)
    }

    /**
     * The agent does not adjust the velocity for other agents that would match the `avoidance_mask`
     * but have a lower `avoidance_priority`. This in turn makes the other agents with lower priority
     * adjust their velocities even more to avoid collision with this agent.
     *
     * Generated from Godot docs: NavigationAgent3D.get_avoidance_priority
     */
    fun getAvoidancePriority(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAvoidancePriorityBind, handle)
    }

    /**
     * If `true` shows debug visuals for this agent.
     *
     * Generated from Godot docs: NavigationAgent3D.set_debug_enabled
     */
    fun setDebugEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugEnabledBind, handle, enabled)
    }

    /**
     * If `true` shows debug visuals for this agent.
     *
     * Generated from Godot docs: NavigationAgent3D.get_debug_enabled
     */
    fun getDebugEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDebugEnabledBind, handle)
    }

    /**
     * If `true` uses the defined `debug_path_custom_color` for this agent instead of global color.
     *
     * Generated from Godot docs: NavigationAgent3D.set_debug_use_custom
     */
    fun setDebugUseCustom(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugUseCustomBind, handle, enabled)
    }

    /**
     * If `true` uses the defined `debug_path_custom_color` for this agent instead of global color.
     *
     * Generated from Godot docs: NavigationAgent3D.get_debug_use_custom
     */
    fun getDebugUseCustom(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDebugUseCustomBind, handle)
    }

    /**
     * If `debug_use_custom` is `true` uses this color for this agent instead of global color.
     *
     * Generated from Godot docs: NavigationAgent3D.set_debug_path_custom_color
     */
    fun setDebugPathCustomColor(color: Color) {
        ObjectCalls.ptrcallWithColorArg(setDebugPathCustomColorBind, handle, color)
    }

    /**
     * If `debug_use_custom` is `true` uses this color for this agent instead of global color.
     *
     * Generated from Godot docs: NavigationAgent3D.get_debug_path_custom_color
     */
    fun getDebugPathCustomColor(): Color {
        return ObjectCalls.ptrcallNoArgsRetColor(getDebugPathCustomColorBind, handle)
    }

    /**
     * If `debug_use_custom` is `true` uses this rasterized point size for rendering path points for
     * this agent instead of global point size.
     *
     * Generated from Godot docs: NavigationAgent3D.set_debug_path_custom_point_size
     */
    fun setDebugPathCustomPointSize(pointSize: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDebugPathCustomPointSizeBind, handle, pointSize)
    }

    /**
     * If `debug_use_custom` is `true` uses this rasterized point size for rendering path points for
     * this agent instead of global point size.
     *
     * Generated from Godot docs: NavigationAgent3D.get_debug_path_custom_point_size
     */
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
