package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * A server interface for low-level 3D navigation access.
 *
 * Generated from Godot docs: NavigationServer3D
 */
object NavigationServer3D {
    private val singleton: MemorySegment by lazy {
        ObjectCalls.getSingleton("NavigationServer3D")
    }

    const val INFO_ACTIVE_MAPS: Long = 0L
    const val INFO_REGION_COUNT: Long = 1L
    const val INFO_AGENT_COUNT: Long = 2L
    const val INFO_LINK_COUNT: Long = 3L
    const val INFO_POLYGON_COUNT: Long = 4L
    const val INFO_EDGE_COUNT: Long = 5L
    const val INFO_EDGE_MERGE_COUNT: Long = 6L
    const val INFO_EDGE_CONNECTION_COUNT: Long = 7L
    const val INFO_EDGE_FREE_COUNT: Long = 8L
    const val INFO_OBSTACLE_COUNT: Long = 9L

    @JvmStatic
    fun getMaps(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(getMapsBind, singleton)
    }

    @JvmStatic
    fun mapCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(mapCreateBind, singleton)
    }

    @JvmStatic
    fun mapSetActive(map: RID, active: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(mapSetActiveBind, singleton, map, active)
    }

    @JvmStatic
    fun mapIsActive(map: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(mapIsActiveBind, singleton, map)
    }

    @JvmStatic
    fun mapSetUp(map: RID, up: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(mapSetUpBind, singleton, map, up)
    }

    @JvmStatic
    fun mapGetUp(map: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(mapGetUpBind, singleton, map)
    }

    @JvmStatic
    fun mapSetCellSize(map: RID, cellSize: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(mapSetCellSizeBind, singleton, map, cellSize)
    }

    @JvmStatic
    fun mapGetCellSize(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(mapGetCellSizeBind, singleton, map)
    }

    @JvmStatic
    fun mapSetCellHeight(map: RID, cellHeight: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(mapSetCellHeightBind, singleton, map, cellHeight)
    }

    @JvmStatic
    fun mapGetCellHeight(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(mapGetCellHeightBind, singleton, map)
    }

    @JvmStatic
    fun mapSetMergeRasterizerCellScale(map: RID, scale: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(mapSetMergeRasterizerCellScaleBind, singleton, map, scale)
    }

    @JvmStatic
    fun mapGetMergeRasterizerCellScale(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(mapGetMergeRasterizerCellScaleBind, singleton, map)
    }

    @JvmStatic
    fun mapSetUseEdgeConnections(map: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(mapSetUseEdgeConnectionsBind, singleton, map, enabled)
    }

    @JvmStatic
    fun mapGetUseEdgeConnections(map: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(mapGetUseEdgeConnectionsBind, singleton, map)
    }

    @JvmStatic
    fun mapSetEdgeConnectionMargin(map: RID, margin: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(mapSetEdgeConnectionMarginBind, singleton, map, margin)
    }

    @JvmStatic
    fun mapGetEdgeConnectionMargin(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(mapGetEdgeConnectionMarginBind, singleton, map)
    }

    @JvmStatic
    fun mapSetLinkConnectionRadius(map: RID, radius: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(mapSetLinkConnectionRadiusBind, singleton, map, radius)
    }

    @JvmStatic
    fun mapGetLinkConnectionRadius(map: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(mapGetLinkConnectionRadiusBind, singleton, map)
    }

    @JvmStatic
    fun mapGetPath(map: RID, origin: Vector3, destination: Vector3, optimize: Boolean, navigationLayers: Long = 1L): List<Vector3> {
        return ObjectCalls.ptrcallWithRIDTwoVector3BoolUInt32ArgsRetPackedVector3List(mapGetPathBind, singleton, map, origin, destination, optimize, navigationLayers)
    }

    @JvmStatic
    fun mapGetClosestPointToSegment(map: RID, start: Vector3, end: Vector3, useCollision: Boolean = false): Vector3 {
        return ObjectCalls.ptrcallWithRIDTwoVector3BoolArgsRetVector3(mapGetClosestPointToSegmentBind, singleton, map, start, end, useCollision)
    }

    @JvmStatic
    fun mapGetClosestPoint(map: RID, toPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetVector3(mapGetClosestPointBind, singleton, map, toPoint)
    }

    @JvmStatic
    fun mapGetClosestPointNormal(map: RID, toPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetVector3(mapGetClosestPointNormalBind, singleton, map, toPoint)
    }

    @JvmStatic
    fun mapGetClosestPointOwner(map: RID, toPoint: Vector3): RID {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetRID(mapGetClosestPointOwnerBind, singleton, map, toPoint)
    }

    @JvmStatic
    fun mapGetLinks(map: RID): List<RID> {
        return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetLinksBind, singleton, map)
    }

    @JvmStatic
    fun mapGetRegions(map: RID): List<RID> {
        return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetRegionsBind, singleton, map)
    }

    @JvmStatic
    fun mapGetAgents(map: RID): List<RID> {
        return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetAgentsBind, singleton, map)
    }

    @JvmStatic
    fun mapGetObstacles(map: RID): List<RID> {
        return ObjectCalls.ptrcallWithRIDArgRetRIDList(mapGetObstaclesBind, singleton, map)
    }

    @JvmStatic
    fun mapForceUpdate(map: RID) {
        ObjectCalls.ptrcallWithRIDArg(mapForceUpdateBind, singleton, map)
    }

    @JvmStatic
    fun mapGetIterationId(map: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(mapGetIterationIdBind, singleton, map)
    }

    @JvmStatic
    fun mapSetUseAsyncIterations(map: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(mapSetUseAsyncIterationsBind, singleton, map, enabled)
    }

    @JvmStatic
    fun mapGetUseAsyncIterations(map: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(mapGetUseAsyncIterationsBind, singleton, map)
    }

    @JvmStatic
    fun mapGetRandomPoint(map: RID, navigationLayers: Long, uniformly: Boolean): Vector3 {
        return ObjectCalls.ptrcallWithRIDUInt32BoolArgsRetVector3(mapGetRandomPointBind, singleton, map, navigationLayers, uniformly)
    }

    @JvmStatic
    fun queryPath(parameters: NavigationPathQueryParameters3D?, result: NavigationPathQueryResult3D?, callback: GodotCallable) {
        ObjectCalls.ptrcallWithTwoObjectCallableArgs(queryPathBind, singleton, parameters?.requireOpenHandle() ?: MemorySegment.NULL, result?.requireOpenHandle() ?: MemorySegment.NULL, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun regionCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(regionCreateBind, singleton)
    }

    @JvmStatic
    fun regionGetIterationId(region: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(regionGetIterationIdBind, singleton, region)
    }

    @JvmStatic
    fun regionSetUseAsyncIterations(region: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(regionSetUseAsyncIterationsBind, singleton, region, enabled)
    }

    @JvmStatic
    fun regionGetUseAsyncIterations(region: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(regionGetUseAsyncIterationsBind, singleton, region)
    }

    @JvmStatic
    fun regionSetEnabled(region: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(regionSetEnabledBind, singleton, region, enabled)
    }

    @JvmStatic
    fun regionGetEnabled(region: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(regionGetEnabledBind, singleton, region)
    }

    @JvmStatic
    fun regionSetUseEdgeConnections(region: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(regionSetUseEdgeConnectionsBind, singleton, region, enabled)
    }

    @JvmStatic
    fun regionGetUseEdgeConnections(region: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(regionGetUseEdgeConnectionsBind, singleton, region)
    }

    @JvmStatic
    fun regionSetEnterCost(region: RID, enterCost: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(regionSetEnterCostBind, singleton, region, enterCost)
    }

    @JvmStatic
    fun regionGetEnterCost(region: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(regionGetEnterCostBind, singleton, region)
    }

    @JvmStatic
    fun regionSetTravelCost(region: RID, travelCost: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(regionSetTravelCostBind, singleton, region, travelCost)
    }

    @JvmStatic
    fun regionGetTravelCost(region: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(regionGetTravelCostBind, singleton, region)
    }

    @JvmStatic
    fun regionSetOwnerId(region: RID, ownerId: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(regionSetOwnerIdBind, singleton, region, ownerId)
    }

    @JvmStatic
    fun regionGetOwnerId(region: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(regionGetOwnerIdBind, singleton, region)
    }

    @JvmStatic
    fun regionOwnsPoint(region: RID, point: Vector3): Boolean {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetBool(regionOwnsPointBind, singleton, region, point)
    }

    @JvmStatic
    fun regionSetMap(region: RID, map: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(regionSetMapBind, singleton, region, map)
    }

    @JvmStatic
    fun regionGetMap(region: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(regionGetMapBind, singleton, region)
    }

    @JvmStatic
    fun regionSetNavigationLayers(region: RID, navigationLayers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(regionSetNavigationLayersBind, singleton, region, navigationLayers)
    }

    @JvmStatic
    fun regionGetNavigationLayers(region: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(regionGetNavigationLayersBind, singleton, region)
    }

    @JvmStatic
    fun regionSetTransform(region: RID, transform: Transform3D) {
        ObjectCalls.ptrcallWithRIDAndTransform3DArg(regionSetTransformBind, singleton, region, transform)
    }

    @JvmStatic
    fun regionGetTransform(region: RID): Transform3D {
        return ObjectCalls.ptrcallWithRIDArgRetTransform3D(regionGetTransformBind, singleton, region)
    }

    @JvmStatic
    fun regionSetNavigationMesh(region: RID, navigationMesh: NavigationMesh?) {
        ObjectCalls.ptrcallWithRIDAndObjectArg(regionSetNavigationMeshBind, singleton, region, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    @JvmStatic
    fun regionBakeNavigationMesh(navigationMesh: NavigationMesh?, rootNode: Node) {
        ObjectCalls.ptrcallWithTwoObjectArgs(regionBakeNavigationMeshBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL, rootNode.handle)
    }

    @JvmStatic
    fun regionGetConnectionsCount(region: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(regionGetConnectionsCountBind, singleton, region)
    }

    @JvmStatic
    fun regionGetConnectionPathwayStart(region: RID, connection: Int): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetVector3(regionGetConnectionPathwayStartBind, singleton, region, connection)
    }

    @JvmStatic
    fun regionGetConnectionPathwayEnd(region: RID, connection: Int): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndIntArgRetVector3(regionGetConnectionPathwayEndBind, singleton, region, connection)
    }

    @JvmStatic
    fun regionGetClosestPointToSegment(region: RID, start: Vector3, end: Vector3, useCollision: Boolean = false): Vector3 {
        return ObjectCalls.ptrcallWithRIDTwoVector3BoolArgsRetVector3(regionGetClosestPointToSegmentBind, singleton, region, start, end, useCollision)
    }

    @JvmStatic
    fun regionGetClosestPoint(region: RID, toPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetVector3(regionGetClosestPointBind, singleton, region, toPoint)
    }

    @JvmStatic
    fun regionGetClosestPointNormal(region: RID, toPoint: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithRIDAndVector3ArgRetVector3(regionGetClosestPointNormalBind, singleton, region, toPoint)
    }

    @JvmStatic
    fun regionGetRandomPoint(region: RID, navigationLayers: Long, uniformly: Boolean): Vector3 {
        return ObjectCalls.ptrcallWithRIDUInt32BoolArgsRetVector3(regionGetRandomPointBind, singleton, region, navigationLayers, uniformly)
    }

    @JvmStatic
    fun regionGetBounds(region: RID): AABB {
        return ObjectCalls.ptrcallWithRIDArgRetAABB(regionGetBoundsBind, singleton, region)
    }

    @JvmStatic
    fun linkCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(linkCreateBind, singleton)
    }

    @JvmStatic
    fun linkGetIterationId(link: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(linkGetIterationIdBind, singleton, link)
    }

    @JvmStatic
    fun linkSetMap(link: RID, map: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(linkSetMapBind, singleton, link, map)
    }

    @JvmStatic
    fun linkGetMap(link: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(linkGetMapBind, singleton, link)
    }

    @JvmStatic
    fun linkSetEnabled(link: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(linkSetEnabledBind, singleton, link, enabled)
    }

    @JvmStatic
    fun linkGetEnabled(link: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(linkGetEnabledBind, singleton, link)
    }

    @JvmStatic
    fun linkSetBidirectional(link: RID, bidirectional: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(linkSetBidirectionalBind, singleton, link, bidirectional)
    }

    @JvmStatic
    fun linkIsBidirectional(link: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(linkIsBidirectionalBind, singleton, link)
    }

    @JvmStatic
    fun linkSetNavigationLayers(link: RID, navigationLayers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(linkSetNavigationLayersBind, singleton, link, navigationLayers)
    }

    @JvmStatic
    fun linkGetNavigationLayers(link: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(linkGetNavigationLayersBind, singleton, link)
    }

    @JvmStatic
    fun linkSetStartPosition(link: RID, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(linkSetStartPositionBind, singleton, link, position)
    }

    @JvmStatic
    fun linkGetStartPosition(link: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(linkGetStartPositionBind, singleton, link)
    }

    @JvmStatic
    fun linkSetEndPosition(link: RID, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(linkSetEndPositionBind, singleton, link, position)
    }

    @JvmStatic
    fun linkGetEndPosition(link: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(linkGetEndPositionBind, singleton, link)
    }

    @JvmStatic
    fun linkSetEnterCost(link: RID, enterCost: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(linkSetEnterCostBind, singleton, link, enterCost)
    }

    @JvmStatic
    fun linkGetEnterCost(link: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(linkGetEnterCostBind, singleton, link)
    }

    @JvmStatic
    fun linkSetTravelCost(link: RID, travelCost: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(linkSetTravelCostBind, singleton, link, travelCost)
    }

    @JvmStatic
    fun linkGetTravelCost(link: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(linkGetTravelCostBind, singleton, link)
    }

    @JvmStatic
    fun linkSetOwnerId(link: RID, ownerId: Long) {
        ObjectCalls.ptrcallWithRIDAndLongArg(linkSetOwnerIdBind, singleton, link, ownerId)
    }

    @JvmStatic
    fun linkGetOwnerId(link: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetLong(linkGetOwnerIdBind, singleton, link)
    }

    @JvmStatic
    fun agentCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(agentCreateBind, singleton)
    }

    @JvmStatic
    fun agentSetAvoidanceEnabled(agent: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(agentSetAvoidanceEnabledBind, singleton, agent, enabled)
    }

    @JvmStatic
    fun agentGetAvoidanceEnabled(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(agentGetAvoidanceEnabledBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetUse3dAvoidance(agent: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(agentSetUse3dAvoidanceBind, singleton, agent, enabled)
    }

    @JvmStatic
    fun agentGetUse3dAvoidance(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(agentGetUse3dAvoidanceBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetMap(agent: RID, map: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(agentSetMapBind, singleton, agent, map)
    }

    @JvmStatic
    fun agentGetMap(agent: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(agentGetMapBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetPaused(agent: RID, paused: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(agentSetPausedBind, singleton, agent, paused)
    }

    @JvmStatic
    fun agentGetPaused(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(agentGetPausedBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetNeighborDistance(agent: RID, distance: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetNeighborDistanceBind, singleton, agent, distance)
    }

    @JvmStatic
    fun agentGetNeighborDistance(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetNeighborDistanceBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetMaxNeighbors(agent: RID, count: Int) {
        ObjectCalls.ptrcallWithRIDAndIntArg(agentSetMaxNeighborsBind, singleton, agent, count)
    }

    @JvmStatic
    fun agentGetMaxNeighbors(agent: RID): Int {
        return ObjectCalls.ptrcallWithRIDArgRetInt(agentGetMaxNeighborsBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetTimeHorizonAgents(agent: RID, timeHorizon: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetTimeHorizonAgentsBind, singleton, agent, timeHorizon)
    }

    @JvmStatic
    fun agentGetTimeHorizonAgents(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetTimeHorizonAgentsBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetTimeHorizonObstacles(agent: RID, timeHorizon: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetTimeHorizonObstaclesBind, singleton, agent, timeHorizon)
    }

    @JvmStatic
    fun agentGetTimeHorizonObstacles(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetTimeHorizonObstaclesBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetRadius(agent: RID, radius: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetRadiusBind, singleton, agent, radius)
    }

    @JvmStatic
    fun agentGetRadius(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetRadiusBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetHeight(agent: RID, height: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetHeightBind, singleton, agent, height)
    }

    @JvmStatic
    fun agentGetHeight(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetHeightBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetMaxSpeed(agent: RID, maxSpeed: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetMaxSpeedBind, singleton, agent, maxSpeed)
    }

    @JvmStatic
    fun agentGetMaxSpeed(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetMaxSpeedBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetVelocityForced(agent: RID, velocity: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(agentSetVelocityForcedBind, singleton, agent, velocity)
    }

    @JvmStatic
    fun agentSetVelocity(agent: RID, velocity: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(agentSetVelocityBind, singleton, agent, velocity)
    }

    @JvmStatic
    fun agentGetVelocity(agent: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(agentGetVelocityBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetPosition(agent: RID, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(agentSetPositionBind, singleton, agent, position)
    }

    @JvmStatic
    fun agentGetPosition(agent: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(agentGetPositionBind, singleton, agent)
    }

    @JvmStatic
    fun agentIsMapChanged(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(agentIsMapChangedBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetAvoidanceCallback(agent: RID, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDCallableArgs(agentSetAvoidanceCallbackBind, singleton, agent, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun agentHasAvoidanceCallback(agent: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(agentHasAvoidanceCallbackBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetAvoidanceLayers(agent: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(agentSetAvoidanceLayersBind, singleton, agent, layers)
    }

    @JvmStatic
    fun agentGetAvoidanceLayers(agent: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(agentGetAvoidanceLayersBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetAvoidanceMask(agent: RID, mask: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(agentSetAvoidanceMaskBind, singleton, agent, mask)
    }

    @JvmStatic
    fun agentGetAvoidanceMask(agent: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(agentGetAvoidanceMaskBind, singleton, agent)
    }

    @JvmStatic
    fun agentSetAvoidancePriority(agent: RID, priority: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(agentSetAvoidancePriorityBind, singleton, agent, priority)
    }

    @JvmStatic
    fun agentGetAvoidancePriority(agent: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(agentGetAvoidancePriorityBind, singleton, agent)
    }

    @JvmStatic
    fun obstacleCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(obstacleCreateBind, singleton)
    }

    @JvmStatic
    fun obstacleSetAvoidanceEnabled(obstacle: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(obstacleSetAvoidanceEnabledBind, singleton, obstacle, enabled)
    }

    @JvmStatic
    fun obstacleGetAvoidanceEnabled(obstacle: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(obstacleGetAvoidanceEnabledBind, singleton, obstacle)
    }

    @JvmStatic
    fun obstacleSetUse3dAvoidance(obstacle: RID, enabled: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(obstacleSetUse3dAvoidanceBind, singleton, obstacle, enabled)
    }

    @JvmStatic
    fun obstacleGetUse3dAvoidance(obstacle: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(obstacleGetUse3dAvoidanceBind, singleton, obstacle)
    }

    @JvmStatic
    fun obstacleSetMap(obstacle: RID, map: RID) {
        ObjectCalls.ptrcallWithTwoRIDArgs(obstacleSetMapBind, singleton, obstacle, map)
    }

    @JvmStatic
    fun obstacleGetMap(obstacle: RID): RID {
        return ObjectCalls.ptrcallWithRIDArgRetRID(obstacleGetMapBind, singleton, obstacle)
    }

    @JvmStatic
    fun obstacleSetPaused(obstacle: RID, paused: Boolean) {
        ObjectCalls.ptrcallWithRIDAndBoolArg(obstacleSetPausedBind, singleton, obstacle, paused)
    }

    @JvmStatic
    fun obstacleGetPaused(obstacle: RID): Boolean {
        return ObjectCalls.ptrcallWithRIDArgRetBool(obstacleGetPausedBind, singleton, obstacle)
    }

    @JvmStatic
    fun obstacleSetRadius(obstacle: RID, radius: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(obstacleSetRadiusBind, singleton, obstacle, radius)
    }

    @JvmStatic
    fun obstacleGetRadius(obstacle: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(obstacleGetRadiusBind, singleton, obstacle)
    }

    @JvmStatic
    fun obstacleSetHeight(obstacle: RID, height: Double) {
        ObjectCalls.ptrcallWithRIDAndDoubleArg(obstacleSetHeightBind, singleton, obstacle, height)
    }

    @JvmStatic
    fun obstacleGetHeight(obstacle: RID): Double {
        return ObjectCalls.ptrcallWithRIDArgRetDouble(obstacleGetHeightBind, singleton, obstacle)
    }

    @JvmStatic
    fun obstacleSetVelocity(obstacle: RID, velocity: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(obstacleSetVelocityBind, singleton, obstacle, velocity)
    }

    @JvmStatic
    fun obstacleGetVelocity(obstacle: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(obstacleGetVelocityBind, singleton, obstacle)
    }

    @JvmStatic
    fun obstacleSetPosition(obstacle: RID, position: Vector3) {
        ObjectCalls.ptrcallWithRIDAndVector3Arg(obstacleSetPositionBind, singleton, obstacle, position)
    }

    @JvmStatic
    fun obstacleGetPosition(obstacle: RID): Vector3 {
        return ObjectCalls.ptrcallWithRIDArgRetVector3(obstacleGetPositionBind, singleton, obstacle)
    }

    @JvmStatic
    fun obstacleSetVertices(obstacle: RID, vertices: List<Vector3>) {
        ObjectCalls.ptrcallWithRIDAndPackedVector3ListArg(obstacleSetVerticesBind, singleton, obstacle, vertices)
    }

    @JvmStatic
    fun obstacleGetVertices(obstacle: RID): List<Vector3> {
        return ObjectCalls.ptrcallWithRIDArgRetPackedVector3List(obstacleGetVerticesBind, singleton, obstacle)
    }

    @JvmStatic
    fun obstacleSetAvoidanceLayers(obstacle: RID, layers: Long) {
        ObjectCalls.ptrcallWithRIDAndUInt32Arg(obstacleSetAvoidanceLayersBind, singleton, obstacle, layers)
    }

    @JvmStatic
    fun obstacleGetAvoidanceLayers(obstacle: RID): Long {
        return ObjectCalls.ptrcallWithRIDArgRetUInt32(obstacleGetAvoidanceLayersBind, singleton, obstacle)
    }

    @JvmStatic
    fun parseSourceGeometryData(navigationMesh: NavigationMesh?, sourceGeometryData: NavigationMeshSourceGeometryData3D?, rootNode: Node, callback: GodotCallable) {
        ObjectCalls.ptrcallWithThreeObjectCallableArgs(parseSourceGeometryDataBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL, sourceGeometryData?.requireOpenHandle() ?: MemorySegment.NULL, rootNode.handle, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun bakeFromSourceGeometryData(navigationMesh: NavigationMesh?, sourceGeometryData: NavigationMeshSourceGeometryData3D?, callback: GodotCallable) {
        ObjectCalls.ptrcallWithTwoObjectCallableArgs(bakeFromSourceGeometryDataBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL, sourceGeometryData?.requireOpenHandle() ?: MemorySegment.NULL, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun bakeFromSourceGeometryDataAsync(navigationMesh: NavigationMesh?, sourceGeometryData: NavigationMeshSourceGeometryData3D?, callback: GodotCallable) {
        ObjectCalls.ptrcallWithTwoObjectCallableArgs(bakeFromSourceGeometryDataAsyncBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL, sourceGeometryData?.requireOpenHandle() ?: MemorySegment.NULL, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun isBakingNavigationMesh(navigationMesh: NavigationMesh?): Boolean {
        return ObjectCalls.ptrcallWithObjectArgRetBool(isBakingNavigationMeshBind, singleton, navigationMesh?.requireOpenHandle() ?: MemorySegment.NULL)
    }

    @JvmStatic
    fun sourceGeometryParserCreate(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(sourceGeometryParserCreateBind, singleton)
    }

    @JvmStatic
    fun sourceGeometryParserSetCallback(parser: RID, callback: GodotCallable) {
        ObjectCalls.ptrcallWithRIDCallableArgs(sourceGeometryParserSetCallbackBind, singleton, parser, callback.target.handle, callback.method)
    }

    @JvmStatic
    fun simplifyPath(path: List<Vector3>, epsilon: Double): List<Vector3> {
        return ObjectCalls.ptrcallWithPackedVector3ListAndDoubleArgRetPackedVector3List(simplifyPathBind, singleton, path, epsilon)
    }

    @JvmStatic
    fun freeRid(rid: RID) {
        ObjectCalls.ptrcallWithRIDArg(freeRidBind, singleton, rid)
    }

    @JvmStatic
    fun setActive(active: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setActiveBind, singleton, active)
    }

    @JvmStatic
    fun setDebugEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setDebugEnabledBind, singleton, enabled)
    }

    @JvmStatic
    fun getDebugEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getDebugEnabledBind, singleton)
    }

    @JvmStatic
    fun getProcessInfo(processInfo: Long): Int {
        return ObjectCalls.ptrcallWithLongArgRetInt(getProcessInfoBind, singleton, processInfo)
    }

    object Signals {
        const val mapChanged: String = "map_changed"
        const val navigationDebugChanged: String = "navigation_debug_changed"
        const val avoidanceDebugChanged: String = "avoidance_debug_changed"
    }

    @JvmStatic
    fun fromHandle(handle: MemorySegment): NavigationServer3D? =
        wrap(handle)

    internal fun wrap(handle: MemorySegment): NavigationServer3D? =
        if (handle.address() == 0L) null else this

    private const val GET_MAPS_HASH = 3995934104L
    private val getMapsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "get_maps", GET_MAPS_HASH)
    }

    private const val MAP_CREATE_HASH = 529393457L
    private val mapCreateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_create", MAP_CREATE_HASH)
    }

    private const val MAP_SET_ACTIVE_HASH = 1265174801L
    private val mapSetActiveBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_active", MAP_SET_ACTIVE_HASH)
    }

    private const val MAP_IS_ACTIVE_HASH = 4155700596L
    private val mapIsActiveBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_is_active", MAP_IS_ACTIVE_HASH)
    }

    private const val MAP_SET_UP_HASH = 3227306858L
    private val mapSetUpBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_up", MAP_SET_UP_HASH)
    }

    private const val MAP_GET_UP_HASH = 531438156L
    private val mapGetUpBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_up", MAP_GET_UP_HASH)
    }

    private const val MAP_SET_CELL_SIZE_HASH = 1794382983L
    private val mapSetCellSizeBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_cell_size", MAP_SET_CELL_SIZE_HASH)
    }

    private const val MAP_GET_CELL_SIZE_HASH = 866169185L
    private val mapGetCellSizeBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_cell_size", MAP_GET_CELL_SIZE_HASH)
    }

    private const val MAP_SET_CELL_HEIGHT_HASH = 1794382983L
    private val mapSetCellHeightBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_cell_height", MAP_SET_CELL_HEIGHT_HASH)
    }

    private const val MAP_GET_CELL_HEIGHT_HASH = 866169185L
    private val mapGetCellHeightBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_cell_height", MAP_GET_CELL_HEIGHT_HASH)
    }

    private const val MAP_SET_MERGE_RASTERIZER_CELL_SCALE_HASH = 1794382983L
    private val mapSetMergeRasterizerCellScaleBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_merge_rasterizer_cell_scale", MAP_SET_MERGE_RASTERIZER_CELL_SCALE_HASH)
    }

    private const val MAP_GET_MERGE_RASTERIZER_CELL_SCALE_HASH = 866169185L
    private val mapGetMergeRasterizerCellScaleBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_merge_rasterizer_cell_scale", MAP_GET_MERGE_RASTERIZER_CELL_SCALE_HASH)
    }

    private const val MAP_SET_USE_EDGE_CONNECTIONS_HASH = 1265174801L
    private val mapSetUseEdgeConnectionsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_use_edge_connections", MAP_SET_USE_EDGE_CONNECTIONS_HASH)
    }

    private const val MAP_GET_USE_EDGE_CONNECTIONS_HASH = 4155700596L
    private val mapGetUseEdgeConnectionsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_use_edge_connections", MAP_GET_USE_EDGE_CONNECTIONS_HASH)
    }

    private const val MAP_SET_EDGE_CONNECTION_MARGIN_HASH = 1794382983L
    private val mapSetEdgeConnectionMarginBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_edge_connection_margin", MAP_SET_EDGE_CONNECTION_MARGIN_HASH)
    }

    private const val MAP_GET_EDGE_CONNECTION_MARGIN_HASH = 866169185L
    private val mapGetEdgeConnectionMarginBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_edge_connection_margin", MAP_GET_EDGE_CONNECTION_MARGIN_HASH)
    }

    private const val MAP_SET_LINK_CONNECTION_RADIUS_HASH = 1794382983L
    private val mapSetLinkConnectionRadiusBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_link_connection_radius", MAP_SET_LINK_CONNECTION_RADIUS_HASH)
    }

    private const val MAP_GET_LINK_CONNECTION_RADIUS_HASH = 866169185L
    private val mapGetLinkConnectionRadiusBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_link_connection_radius", MAP_GET_LINK_CONNECTION_RADIUS_HASH)
    }

    private const val MAP_GET_PATH_HASH = 276783190L
    private val mapGetPathBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_path", MAP_GET_PATH_HASH)
    }

    private const val MAP_GET_CLOSEST_POINT_TO_SEGMENT_HASH = 3830095642L
    private val mapGetClosestPointToSegmentBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_closest_point_to_segment", MAP_GET_CLOSEST_POINT_TO_SEGMENT_HASH)
    }

    private const val MAP_GET_CLOSEST_POINT_HASH = 2056183332L
    private val mapGetClosestPointBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_closest_point", MAP_GET_CLOSEST_POINT_HASH)
    }

    private const val MAP_GET_CLOSEST_POINT_NORMAL_HASH = 2056183332L
    private val mapGetClosestPointNormalBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_closest_point_normal", MAP_GET_CLOSEST_POINT_NORMAL_HASH)
    }

    private const val MAP_GET_CLOSEST_POINT_OWNER_HASH = 553364610L
    private val mapGetClosestPointOwnerBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_closest_point_owner", MAP_GET_CLOSEST_POINT_OWNER_HASH)
    }

    private const val MAP_GET_LINKS_HASH = 2684255073L
    private val mapGetLinksBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_links", MAP_GET_LINKS_HASH)
    }

    private const val MAP_GET_REGIONS_HASH = 2684255073L
    private val mapGetRegionsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_regions", MAP_GET_REGIONS_HASH)
    }

    private const val MAP_GET_AGENTS_HASH = 2684255073L
    private val mapGetAgentsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_agents", MAP_GET_AGENTS_HASH)
    }

    private const val MAP_GET_OBSTACLES_HASH = 2684255073L
    private val mapGetObstaclesBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_obstacles", MAP_GET_OBSTACLES_HASH)
    }

    private const val MAP_FORCE_UPDATE_HASH = 2722037293L
    private val mapForceUpdateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_force_update", MAP_FORCE_UPDATE_HASH)
    }

    private const val MAP_GET_ITERATION_ID_HASH = 2198884583L
    private val mapGetIterationIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_iteration_id", MAP_GET_ITERATION_ID_HASH)
    }

    private const val MAP_SET_USE_ASYNC_ITERATIONS_HASH = 1265174801L
    private val mapSetUseAsyncIterationsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_set_use_async_iterations", MAP_SET_USE_ASYNC_ITERATIONS_HASH)
    }

    private const val MAP_GET_USE_ASYNC_ITERATIONS_HASH = 4155700596L
    private val mapGetUseAsyncIterationsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_use_async_iterations", MAP_GET_USE_ASYNC_ITERATIONS_HASH)
    }

    private const val MAP_GET_RANDOM_POINT_HASH = 722801526L
    private val mapGetRandomPointBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "map_get_random_point", MAP_GET_RANDOM_POINT_HASH)
    }

    private const val QUERY_PATH_HASH = 2146930868L
    private val queryPathBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "query_path", QUERY_PATH_HASH)
    }

    private const val REGION_CREATE_HASH = 529393457L
    private val regionCreateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_create", REGION_CREATE_HASH)
    }

    private const val REGION_GET_ITERATION_ID_HASH = 2198884583L
    private val regionGetIterationIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_iteration_id", REGION_GET_ITERATION_ID_HASH)
    }

    private const val REGION_SET_USE_ASYNC_ITERATIONS_HASH = 1265174801L
    private val regionSetUseAsyncIterationsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_use_async_iterations", REGION_SET_USE_ASYNC_ITERATIONS_HASH)
    }

    private const val REGION_GET_USE_ASYNC_ITERATIONS_HASH = 4155700596L
    private val regionGetUseAsyncIterationsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_use_async_iterations", REGION_GET_USE_ASYNC_ITERATIONS_HASH)
    }

    private const val REGION_SET_ENABLED_HASH = 1265174801L
    private val regionSetEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_enabled", REGION_SET_ENABLED_HASH)
    }

    private const val REGION_GET_ENABLED_HASH = 4155700596L
    private val regionGetEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_enabled", REGION_GET_ENABLED_HASH)
    }

    private const val REGION_SET_USE_EDGE_CONNECTIONS_HASH = 1265174801L
    private val regionSetUseEdgeConnectionsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_use_edge_connections", REGION_SET_USE_EDGE_CONNECTIONS_HASH)
    }

    private const val REGION_GET_USE_EDGE_CONNECTIONS_HASH = 4155700596L
    private val regionGetUseEdgeConnectionsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_use_edge_connections", REGION_GET_USE_EDGE_CONNECTIONS_HASH)
    }

    private const val REGION_SET_ENTER_COST_HASH = 1794382983L
    private val regionSetEnterCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_enter_cost", REGION_SET_ENTER_COST_HASH)
    }

    private const val REGION_GET_ENTER_COST_HASH = 866169185L
    private val regionGetEnterCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_enter_cost", REGION_GET_ENTER_COST_HASH)
    }

    private const val REGION_SET_TRAVEL_COST_HASH = 1794382983L
    private val regionSetTravelCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_travel_cost", REGION_SET_TRAVEL_COST_HASH)
    }

    private const val REGION_GET_TRAVEL_COST_HASH = 866169185L
    private val regionGetTravelCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_travel_cost", REGION_GET_TRAVEL_COST_HASH)
    }

    private const val REGION_SET_OWNER_ID_HASH = 3411492887L
    private val regionSetOwnerIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_owner_id", REGION_SET_OWNER_ID_HASH)
    }

    private const val REGION_GET_OWNER_ID_HASH = 2198884583L
    private val regionGetOwnerIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_owner_id", REGION_GET_OWNER_ID_HASH)
    }

    private const val REGION_OWNS_POINT_HASH = 2360011153L
    private val regionOwnsPointBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_owns_point", REGION_OWNS_POINT_HASH)
    }

    private const val REGION_SET_MAP_HASH = 395945892L
    private val regionSetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_map", REGION_SET_MAP_HASH)
    }

    private const val REGION_GET_MAP_HASH = 3814569979L
    private val regionGetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_map", REGION_GET_MAP_HASH)
    }

    private const val REGION_SET_NAVIGATION_LAYERS_HASH = 3411492887L
    private val regionSetNavigationLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_navigation_layers", REGION_SET_NAVIGATION_LAYERS_HASH)
    }

    private const val REGION_GET_NAVIGATION_LAYERS_HASH = 2198884583L
    private val regionGetNavigationLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_navigation_layers", REGION_GET_NAVIGATION_LAYERS_HASH)
    }

    private const val REGION_SET_TRANSFORM_HASH = 3935195649L
    private val regionSetTransformBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_transform", REGION_SET_TRANSFORM_HASH)
    }

    private const val REGION_GET_TRANSFORM_HASH = 1128465797L
    private val regionGetTransformBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_transform", REGION_GET_TRANSFORM_HASH)
    }

    private const val REGION_SET_NAVIGATION_MESH_HASH = 2764952978L
    private val regionSetNavigationMeshBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_set_navigation_mesh", REGION_SET_NAVIGATION_MESH_HASH)
    }

    private const val REGION_BAKE_NAVIGATION_MESH_HASH = 1401173477L
    private val regionBakeNavigationMeshBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_bake_navigation_mesh", REGION_BAKE_NAVIGATION_MESH_HASH)
    }

    private const val REGION_GET_CONNECTIONS_COUNT_HASH = 2198884583L
    private val regionGetConnectionsCountBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_connections_count", REGION_GET_CONNECTIONS_COUNT_HASH)
    }

    private const val REGION_GET_CONNECTION_PATHWAY_START_HASH = 3440143363L
    private val regionGetConnectionPathwayStartBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_connection_pathway_start", REGION_GET_CONNECTION_PATHWAY_START_HASH)
    }

    private const val REGION_GET_CONNECTION_PATHWAY_END_HASH = 3440143363L
    private val regionGetConnectionPathwayEndBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_connection_pathway_end", REGION_GET_CONNECTION_PATHWAY_END_HASH)
    }

    private const val REGION_GET_CLOSEST_POINT_TO_SEGMENT_HASH = 3830095642L
    private val regionGetClosestPointToSegmentBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_closest_point_to_segment", REGION_GET_CLOSEST_POINT_TO_SEGMENT_HASH)
    }

    private const val REGION_GET_CLOSEST_POINT_HASH = 2056183332L
    private val regionGetClosestPointBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_closest_point", REGION_GET_CLOSEST_POINT_HASH)
    }

    private const val REGION_GET_CLOSEST_POINT_NORMAL_HASH = 2056183332L
    private val regionGetClosestPointNormalBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_closest_point_normal", REGION_GET_CLOSEST_POINT_NORMAL_HASH)
    }

    private const val REGION_GET_RANDOM_POINT_HASH = 722801526L
    private val regionGetRandomPointBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_random_point", REGION_GET_RANDOM_POINT_HASH)
    }

    private const val REGION_GET_BOUNDS_HASH = 974181306L
    private val regionGetBoundsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "region_get_bounds", REGION_GET_BOUNDS_HASH)
    }

    private const val LINK_CREATE_HASH = 529393457L
    private val linkCreateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_create", LINK_CREATE_HASH)
    }

    private const val LINK_GET_ITERATION_ID_HASH = 2198884583L
    private val linkGetIterationIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_iteration_id", LINK_GET_ITERATION_ID_HASH)
    }

    private const val LINK_SET_MAP_HASH = 395945892L
    private val linkSetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_map", LINK_SET_MAP_HASH)
    }

    private const val LINK_GET_MAP_HASH = 3814569979L
    private val linkGetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_map", LINK_GET_MAP_HASH)
    }

    private const val LINK_SET_ENABLED_HASH = 1265174801L
    private val linkSetEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_enabled", LINK_SET_ENABLED_HASH)
    }

    private const val LINK_GET_ENABLED_HASH = 4155700596L
    private val linkGetEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_enabled", LINK_GET_ENABLED_HASH)
    }

    private const val LINK_SET_BIDIRECTIONAL_HASH = 1265174801L
    private val linkSetBidirectionalBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_bidirectional", LINK_SET_BIDIRECTIONAL_HASH)
    }

    private const val LINK_IS_BIDIRECTIONAL_HASH = 4155700596L
    private val linkIsBidirectionalBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_is_bidirectional", LINK_IS_BIDIRECTIONAL_HASH)
    }

    private const val LINK_SET_NAVIGATION_LAYERS_HASH = 3411492887L
    private val linkSetNavigationLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_navigation_layers", LINK_SET_NAVIGATION_LAYERS_HASH)
    }

    private const val LINK_GET_NAVIGATION_LAYERS_HASH = 2198884583L
    private val linkGetNavigationLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_navigation_layers", LINK_GET_NAVIGATION_LAYERS_HASH)
    }

    private const val LINK_SET_START_POSITION_HASH = 3227306858L
    private val linkSetStartPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_start_position", LINK_SET_START_POSITION_HASH)
    }

    private const val LINK_GET_START_POSITION_HASH = 531438156L
    private val linkGetStartPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_start_position", LINK_GET_START_POSITION_HASH)
    }

    private const val LINK_SET_END_POSITION_HASH = 3227306858L
    private val linkSetEndPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_end_position", LINK_SET_END_POSITION_HASH)
    }

    private const val LINK_GET_END_POSITION_HASH = 531438156L
    private val linkGetEndPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_end_position", LINK_GET_END_POSITION_HASH)
    }

    private const val LINK_SET_ENTER_COST_HASH = 1794382983L
    private val linkSetEnterCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_enter_cost", LINK_SET_ENTER_COST_HASH)
    }

    private const val LINK_GET_ENTER_COST_HASH = 866169185L
    private val linkGetEnterCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_enter_cost", LINK_GET_ENTER_COST_HASH)
    }

    private const val LINK_SET_TRAVEL_COST_HASH = 1794382983L
    private val linkSetTravelCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_travel_cost", LINK_SET_TRAVEL_COST_HASH)
    }

    private const val LINK_GET_TRAVEL_COST_HASH = 866169185L
    private val linkGetTravelCostBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_travel_cost", LINK_GET_TRAVEL_COST_HASH)
    }

    private const val LINK_SET_OWNER_ID_HASH = 3411492887L
    private val linkSetOwnerIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_set_owner_id", LINK_SET_OWNER_ID_HASH)
    }

    private const val LINK_GET_OWNER_ID_HASH = 2198884583L
    private val linkGetOwnerIdBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "link_get_owner_id", LINK_GET_OWNER_ID_HASH)
    }

    private const val AGENT_CREATE_HASH = 529393457L
    private val agentCreateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_create", AGENT_CREATE_HASH)
    }

    private const val AGENT_SET_AVOIDANCE_ENABLED_HASH = 1265174801L
    private val agentSetAvoidanceEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_enabled", AGENT_SET_AVOIDANCE_ENABLED_HASH)
    }

    private const val AGENT_GET_AVOIDANCE_ENABLED_HASH = 4155700596L
    private val agentGetAvoidanceEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_avoidance_enabled", AGENT_GET_AVOIDANCE_ENABLED_HASH)
    }

    private const val AGENT_SET_USE_3D_AVOIDANCE_HASH = 1265174801L
    private val agentSetUse3dAvoidanceBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_use_3d_avoidance", AGENT_SET_USE_3D_AVOIDANCE_HASH)
    }

    private const val AGENT_GET_USE_3D_AVOIDANCE_HASH = 4155700596L
    private val agentGetUse3dAvoidanceBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_use_3d_avoidance", AGENT_GET_USE_3D_AVOIDANCE_HASH)
    }

    private const val AGENT_SET_MAP_HASH = 395945892L
    private val agentSetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_map", AGENT_SET_MAP_HASH)
    }

    private const val AGENT_GET_MAP_HASH = 3814569979L
    private val agentGetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_map", AGENT_GET_MAP_HASH)
    }

    private const val AGENT_SET_PAUSED_HASH = 1265174801L
    private val agentSetPausedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_paused", AGENT_SET_PAUSED_HASH)
    }

    private const val AGENT_GET_PAUSED_HASH = 4155700596L
    private val agentGetPausedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_paused", AGENT_GET_PAUSED_HASH)
    }

    private const val AGENT_SET_NEIGHBOR_DISTANCE_HASH = 1794382983L
    private val agentSetNeighborDistanceBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_neighbor_distance", AGENT_SET_NEIGHBOR_DISTANCE_HASH)
    }

    private const val AGENT_GET_NEIGHBOR_DISTANCE_HASH = 866169185L
    private val agentGetNeighborDistanceBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_neighbor_distance", AGENT_GET_NEIGHBOR_DISTANCE_HASH)
    }

    private const val AGENT_SET_MAX_NEIGHBORS_HASH = 3411492887L
    private val agentSetMaxNeighborsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_max_neighbors", AGENT_SET_MAX_NEIGHBORS_HASH)
    }

    private const val AGENT_GET_MAX_NEIGHBORS_HASH = 2198884583L
    private val agentGetMaxNeighborsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_max_neighbors", AGENT_GET_MAX_NEIGHBORS_HASH)
    }

    private const val AGENT_SET_TIME_HORIZON_AGENTS_HASH = 1794382983L
    private val agentSetTimeHorizonAgentsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_time_horizon_agents", AGENT_SET_TIME_HORIZON_AGENTS_HASH)
    }

    private const val AGENT_GET_TIME_HORIZON_AGENTS_HASH = 866169185L
    private val agentGetTimeHorizonAgentsBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_time_horizon_agents", AGENT_GET_TIME_HORIZON_AGENTS_HASH)
    }

    private const val AGENT_SET_TIME_HORIZON_OBSTACLES_HASH = 1794382983L
    private val agentSetTimeHorizonObstaclesBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_time_horizon_obstacles", AGENT_SET_TIME_HORIZON_OBSTACLES_HASH)
    }

    private const val AGENT_GET_TIME_HORIZON_OBSTACLES_HASH = 866169185L
    private val agentGetTimeHorizonObstaclesBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_time_horizon_obstacles", AGENT_GET_TIME_HORIZON_OBSTACLES_HASH)
    }

    private const val AGENT_SET_RADIUS_HASH = 1794382983L
    private val agentSetRadiusBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_radius", AGENT_SET_RADIUS_HASH)
    }

    private const val AGENT_GET_RADIUS_HASH = 866169185L
    private val agentGetRadiusBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_radius", AGENT_GET_RADIUS_HASH)
    }

    private const val AGENT_SET_HEIGHT_HASH = 1794382983L
    private val agentSetHeightBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_height", AGENT_SET_HEIGHT_HASH)
    }

    private const val AGENT_GET_HEIGHT_HASH = 866169185L
    private val agentGetHeightBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_height", AGENT_GET_HEIGHT_HASH)
    }

    private const val AGENT_SET_MAX_SPEED_HASH = 1794382983L
    private val agentSetMaxSpeedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_max_speed", AGENT_SET_MAX_SPEED_HASH)
    }

    private const val AGENT_GET_MAX_SPEED_HASH = 866169185L
    private val agentGetMaxSpeedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_max_speed", AGENT_GET_MAX_SPEED_HASH)
    }

    private const val AGENT_SET_VELOCITY_FORCED_HASH = 3227306858L
    private val agentSetVelocityForcedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_velocity_forced", AGENT_SET_VELOCITY_FORCED_HASH)
    }

    private const val AGENT_SET_VELOCITY_HASH = 3227306858L
    private val agentSetVelocityBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_velocity", AGENT_SET_VELOCITY_HASH)
    }

    private const val AGENT_GET_VELOCITY_HASH = 531438156L
    private val agentGetVelocityBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_velocity", AGENT_GET_VELOCITY_HASH)
    }

    private const val AGENT_SET_POSITION_HASH = 3227306858L
    private val agentSetPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_position", AGENT_SET_POSITION_HASH)
    }

    private const val AGENT_GET_POSITION_HASH = 531438156L
    private val agentGetPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_position", AGENT_GET_POSITION_HASH)
    }

    private const val AGENT_IS_MAP_CHANGED_HASH = 4155700596L
    private val agentIsMapChangedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_is_map_changed", AGENT_IS_MAP_CHANGED_HASH)
    }

    private const val AGENT_SET_AVOIDANCE_CALLBACK_HASH = 3379118538L
    private val agentSetAvoidanceCallbackBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_callback", AGENT_SET_AVOIDANCE_CALLBACK_HASH)
    }

    private const val AGENT_HAS_AVOIDANCE_CALLBACK_HASH = 4155700596L
    private val agentHasAvoidanceCallbackBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_has_avoidance_callback", AGENT_HAS_AVOIDANCE_CALLBACK_HASH)
    }

    private const val AGENT_SET_AVOIDANCE_LAYERS_HASH = 3411492887L
    private val agentSetAvoidanceLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_layers", AGENT_SET_AVOIDANCE_LAYERS_HASH)
    }

    private const val AGENT_GET_AVOIDANCE_LAYERS_HASH = 2198884583L
    private val agentGetAvoidanceLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_avoidance_layers", AGENT_GET_AVOIDANCE_LAYERS_HASH)
    }

    private const val AGENT_SET_AVOIDANCE_MASK_HASH = 3411492887L
    private val agentSetAvoidanceMaskBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_mask", AGENT_SET_AVOIDANCE_MASK_HASH)
    }

    private const val AGENT_GET_AVOIDANCE_MASK_HASH = 2198884583L
    private val agentGetAvoidanceMaskBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_avoidance_mask", AGENT_GET_AVOIDANCE_MASK_HASH)
    }

    private const val AGENT_SET_AVOIDANCE_PRIORITY_HASH = 1794382983L
    private val agentSetAvoidancePriorityBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_set_avoidance_priority", AGENT_SET_AVOIDANCE_PRIORITY_HASH)
    }

    private const val AGENT_GET_AVOIDANCE_PRIORITY_HASH = 866169185L
    private val agentGetAvoidancePriorityBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "agent_get_avoidance_priority", AGENT_GET_AVOIDANCE_PRIORITY_HASH)
    }

    private const val OBSTACLE_CREATE_HASH = 529393457L
    private val obstacleCreateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_create", OBSTACLE_CREATE_HASH)
    }

    private const val OBSTACLE_SET_AVOIDANCE_ENABLED_HASH = 1265174801L
    private val obstacleSetAvoidanceEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_avoidance_enabled", OBSTACLE_SET_AVOIDANCE_ENABLED_HASH)
    }

    private const val OBSTACLE_GET_AVOIDANCE_ENABLED_HASH = 4155700596L
    private val obstacleGetAvoidanceEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_avoidance_enabled", OBSTACLE_GET_AVOIDANCE_ENABLED_HASH)
    }

    private const val OBSTACLE_SET_USE_3D_AVOIDANCE_HASH = 1265174801L
    private val obstacleSetUse3dAvoidanceBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_use_3d_avoidance", OBSTACLE_SET_USE_3D_AVOIDANCE_HASH)
    }

    private const val OBSTACLE_GET_USE_3D_AVOIDANCE_HASH = 4155700596L
    private val obstacleGetUse3dAvoidanceBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_use_3d_avoidance", OBSTACLE_GET_USE_3D_AVOIDANCE_HASH)
    }

    private const val OBSTACLE_SET_MAP_HASH = 395945892L
    private val obstacleSetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_map", OBSTACLE_SET_MAP_HASH)
    }

    private const val OBSTACLE_GET_MAP_HASH = 3814569979L
    private val obstacleGetMapBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_map", OBSTACLE_GET_MAP_HASH)
    }

    private const val OBSTACLE_SET_PAUSED_HASH = 1265174801L
    private val obstacleSetPausedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_paused", OBSTACLE_SET_PAUSED_HASH)
    }

    private const val OBSTACLE_GET_PAUSED_HASH = 4155700596L
    private val obstacleGetPausedBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_paused", OBSTACLE_GET_PAUSED_HASH)
    }

    private const val OBSTACLE_SET_RADIUS_HASH = 1794382983L
    private val obstacleSetRadiusBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_radius", OBSTACLE_SET_RADIUS_HASH)
    }

    private const val OBSTACLE_GET_RADIUS_HASH = 866169185L
    private val obstacleGetRadiusBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_radius", OBSTACLE_GET_RADIUS_HASH)
    }

    private const val OBSTACLE_SET_HEIGHT_HASH = 1794382983L
    private val obstacleSetHeightBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_height", OBSTACLE_SET_HEIGHT_HASH)
    }

    private const val OBSTACLE_GET_HEIGHT_HASH = 866169185L
    private val obstacleGetHeightBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_height", OBSTACLE_GET_HEIGHT_HASH)
    }

    private const val OBSTACLE_SET_VELOCITY_HASH = 3227306858L
    private val obstacleSetVelocityBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_velocity", OBSTACLE_SET_VELOCITY_HASH)
    }

    private const val OBSTACLE_GET_VELOCITY_HASH = 531438156L
    private val obstacleGetVelocityBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_velocity", OBSTACLE_GET_VELOCITY_HASH)
    }

    private const val OBSTACLE_SET_POSITION_HASH = 3227306858L
    private val obstacleSetPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_position", OBSTACLE_SET_POSITION_HASH)
    }

    private const val OBSTACLE_GET_POSITION_HASH = 531438156L
    private val obstacleGetPositionBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_position", OBSTACLE_GET_POSITION_HASH)
    }

    private const val OBSTACLE_SET_VERTICES_HASH = 4030257846L
    private val obstacleSetVerticesBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_vertices", OBSTACLE_SET_VERTICES_HASH)
    }

    private const val OBSTACLE_GET_VERTICES_HASH = 808965560L
    private val obstacleGetVerticesBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_vertices", OBSTACLE_GET_VERTICES_HASH)
    }

    private const val OBSTACLE_SET_AVOIDANCE_LAYERS_HASH = 3411492887L
    private val obstacleSetAvoidanceLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_set_avoidance_layers", OBSTACLE_SET_AVOIDANCE_LAYERS_HASH)
    }

    private const val OBSTACLE_GET_AVOIDANCE_LAYERS_HASH = 2198884583L
    private val obstacleGetAvoidanceLayersBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "obstacle_get_avoidance_layers", OBSTACLE_GET_AVOIDANCE_LAYERS_HASH)
    }

    private const val PARSE_SOURCE_GEOMETRY_DATA_HASH = 3172802542L
    private val parseSourceGeometryDataBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "parse_source_geometry_data", PARSE_SOURCE_GEOMETRY_DATA_HASH)
    }

    private const val BAKE_FROM_SOURCE_GEOMETRY_DATA_HASH = 1286748856L
    private val bakeFromSourceGeometryDataBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "bake_from_source_geometry_data", BAKE_FROM_SOURCE_GEOMETRY_DATA_HASH)
    }

    private const val BAKE_FROM_SOURCE_GEOMETRY_DATA_ASYNC_HASH = 1286748856L
    private val bakeFromSourceGeometryDataAsyncBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "bake_from_source_geometry_data_async", BAKE_FROM_SOURCE_GEOMETRY_DATA_ASYNC_HASH)
    }

    private const val IS_BAKING_NAVIGATION_MESH_HASH = 3142026141L
    private val isBakingNavigationMeshBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "is_baking_navigation_mesh", IS_BAKING_NAVIGATION_MESH_HASH)
    }

    private const val SOURCE_GEOMETRY_PARSER_CREATE_HASH = 529393457L
    private val sourceGeometryParserCreateBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "source_geometry_parser_create", SOURCE_GEOMETRY_PARSER_CREATE_HASH)
    }

    private const val SOURCE_GEOMETRY_PARSER_SET_CALLBACK_HASH = 3379118538L
    private val sourceGeometryParserSetCallbackBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "source_geometry_parser_set_callback", SOURCE_GEOMETRY_PARSER_SET_CALLBACK_HASH)
    }

    private const val SIMPLIFY_PATH_HASH = 2344122170L
    private val simplifyPathBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "simplify_path", SIMPLIFY_PATH_HASH)
    }

    private const val FREE_RID_HASH = 2722037293L
    private val freeRidBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "free_rid", FREE_RID_HASH)
    }

    private const val SET_ACTIVE_HASH = 2586408642L
    private val setActiveBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "set_active", SET_ACTIVE_HASH)
    }

    private const val SET_DEBUG_ENABLED_HASH = 2586408642L
    private val setDebugEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "set_debug_enabled", SET_DEBUG_ENABLED_HASH)
    }

    private const val GET_DEBUG_ENABLED_HASH = 36873697L
    private val getDebugEnabledBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "get_debug_enabled", GET_DEBUG_ENABLED_HASH)
    }

    private const val GET_PROCESS_INFO_HASH = 1938440894L
    private val getProcessInfoBind by lazy {
        ObjectCalls.getMethodBind("NavigationServer3D", "get_process_info", GET_PROCESS_INFO_HASH)
    }
}
