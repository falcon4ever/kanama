package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.AABB
import net.multigesture.kanama.types.RID

/**
 * A traversable 3D region that `NavigationAgent3D`s can use for pathfinding.
 *
 * Generated from Godot docs: NavigationRegion3D
 */
class NavigationRegion3D(handle: GodotHandle) : Node3D(handle) {
    var navigationMesh: NavigationMesh?
        @JvmName("navigationMeshProperty")
        get() = getNavigationMesh()
        @JvmName("setNavigationMeshProperty")
        set(value) = setNavigationMesh(value)

    var enabled: Boolean
        @JvmName("enabledProperty")
        get() = isEnabled()
        @JvmName("setEnabledProperty")
        set(value) = setEnabled(value)

    var useEdgeConnections: Boolean
        @JvmName("useEdgeConnectionsProperty")
        get() = getUseEdgeConnections()
        @JvmName("setUseEdgeConnectionsProperty")
        set(value) = setUseEdgeConnections(value)

    var navigationLayers: Long
        @JvmName("navigationLayersProperty")
        get() = getNavigationLayers()
        @JvmName("setNavigationLayersProperty")
        set(value) = setNavigationLayers(value)

    var enterCost: Double
        @JvmName("enterCostProperty")
        get() = getEnterCost()
        @JvmName("setEnterCostProperty")
        set(value) = setEnterCost(value)

    var travelCost: Double
        @JvmName("travelCostProperty")
        get() = getTravelCost()
        @JvmName("setTravelCostProperty")
        set(value) = setTravelCost(value)

    /**
     * Returns the `RID` of this region on the `NavigationServer3D`. Combined with
     * `NavigationServer3D.map_get_closest_point_owner` can be used to identify the
     * `NavigationRegion3D` closest to a point on the merged navigation map.
     *
     * Generated from Godot docs: NavigationRegion3D.get_rid
     */
    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getRidBind, segment)
    }

    /**
     * The `NavigationMesh` resource to use.
     *
     * Generated from Godot docs: NavigationRegion3D.set_navigation_mesh
     */
    fun setNavigationMesh(navigationMesh: NavigationMesh?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setNavigationMeshBind, segment, listOf(navigationMesh?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The `NavigationMesh` resource to use.
     *
     * Generated from Godot docs: NavigationRegion3D.get_navigation_mesh
     */
    fun getNavigationMesh(): NavigationMesh? {
        return NavigationMesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getNavigationMeshBind, segment))
    }

    /**
     * Determines if the `NavigationRegion3D` is enabled or disabled.
     *
     * Generated from Godot docs: NavigationRegion3D.set_enabled
     */
    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnabledBind, segment, enabled)
    }

    /**
     * Determines if the `NavigationRegion3D` is enabled or disabled.
     *
     * Generated from Godot docs: NavigationRegion3D.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEnabledBind, segment)
    }

    /**
     * Sets the `RID` of the navigation map this region should use. By default the region will
     * automatically join the `World3D` default navigation map so this function is only required to
     * override the default map.
     *
     * Generated from Godot docs: NavigationRegion3D.set_navigation_map
     */
    fun setNavigationMap(navigationMap: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.setNavigationMapBind, segment, navigationMap)
    }

    /**
     * Returns the current navigation map `RID` used by this region.
     *
     * Generated from Godot docs: NavigationRegion3D.get_navigation_map
     */
    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getNavigationMapBind, segment)
    }

    /**
     * If enabled the navigation region will use edge connections to connect with other navigation
     * regions within proximity of the navigation map edge connection margin.
     *
     * Generated from Godot docs: NavigationRegion3D.set_use_edge_connections
     */
    fun setUseEdgeConnections(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseEdgeConnectionsBind, segment, enabled)
    }

    /**
     * If enabled the navigation region will use edge connections to connect with other navigation
     * regions within proximity of the navigation map edge connection margin.
     *
     * Generated from Godot docs: NavigationRegion3D.get_use_edge_connections
     */
    fun getUseEdgeConnections(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUseEdgeConnectionsBind, segment)
    }

    /**
     * A bitfield determining all navigation layers the region belongs to. These navigation layers can
     * be checked upon when requesting a path with `NavigationServer3D.map_get_path`.
     *
     * Generated from Godot docs: NavigationRegion3D.set_navigation_layers
     */
    fun setNavigationLayers(navigationLayers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setNavigationLayersBind, segment, navigationLayers)
    }

    /**
     * A bitfield determining all navigation layers the region belongs to. These navigation layers can
     * be checked upon when requesting a path with `NavigationServer3D.map_get_path`.
     *
     * Generated from Godot docs: NavigationRegion3D.get_navigation_layers
     */
    fun getNavigationLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getNavigationLayersBind, segment)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `navigation_layers` bitmask,
     * given a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationRegion3D.set_navigation_layer_value
     */
    fun setNavigationLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setNavigationLayerValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `navigation_layers` bitmask is enabled, given
     * a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationRegion3D.get_navigation_layer_value
     */
    fun getNavigationLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getNavigationLayerValueBind, segment, layerNumber)
    }

    /**
     * Returns the `RID` of this region on the `NavigationServer3D`.
     *
     * Generated from Godot docs: NavigationRegion3D.get_region_rid
     */
    fun getRegionRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getRegionRidBind, segment)
    }

    /**
     * When pathfinding enters this region's navigation mesh from another regions navigation mesh the
     * `enter_cost` value is added to the path distance for determining the shortest path.
     *
     * Generated from Godot docs: NavigationRegion3D.set_enter_cost
     */
    fun setEnterCost(enterCost: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setEnterCostBind, segment, enterCost)
    }

    /**
     * When pathfinding enters this region's navigation mesh from another regions navigation mesh the
     * `enter_cost` value is added to the path distance for determining the shortest path.
     *
     * Generated from Godot docs: NavigationRegion3D.get_enter_cost
     */
    fun getEnterCost(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getEnterCostBind, segment)
    }

    /**
     * When pathfinding moves inside this region's navigation mesh the traveled distances are
     * multiplied with `travel_cost` for determining the shortest path.
     *
     * Generated from Godot docs: NavigationRegion3D.set_travel_cost
     */
    fun setTravelCost(travelCost: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTravelCostBind, segment, travelCost)
    }

    /**
     * When pathfinding moves inside this region's navigation mesh the traveled distances are
     * multiplied with `travel_cost` for determining the shortest path.
     *
     * Generated from Godot docs: NavigationRegion3D.get_travel_cost
     */
    fun getTravelCost(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTravelCostBind, segment)
    }

    /**
     * Bakes the `NavigationMesh`. If `on_thread` is set to `true` (default), the baking is done on a
     * separate thread. Baking on separate thread is useful because navigation baking is not a cheap
     * operation. When it is completed, it automatically sets the new `NavigationMesh`. Please note
     * that baking on separate thread may be very slow if geometry is parsed from meshes as async
     * access to each mesh involves heavy synchronization. Also, please note that baking on a separate
     * thread is automatically disabled on operating systems that cannot use threads (such as Web with
     * threads disabled).
     *
     * Generated from Godot docs: NavigationRegion3D.bake_navigation_mesh
     */
    fun bakeNavigationMesh(onThread: Boolean = true) {
        ObjectCalls.ptrcallWithBoolArg(Binds.bakeNavigationMeshBind, segment, onThread)
    }

    /**
     * Returns `true` when the `NavigationMesh` is being baked on a background thread.
     *
     * Generated from Godot docs: NavigationRegion3D.is_baking
     */
    fun isBaking(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isBakingBind, segment)
    }

    /**
     * Returns the axis-aligned bounding box for the region's transformed navigation mesh.
     *
     * Generated from Godot docs: NavigationRegion3D.get_bounds
     */
    fun getBounds(): AABB {
        return ObjectCalls.ptrcallNoArgsRetAABB(Binds.getBoundsBind, segment)
    }

    /** Signal `navigation_mesh_changed()`; see [TypedSignal]. */
    val navigationMeshChanged: Signal0
        @JvmName("navigationMeshChangedTypedSignal")
        get() = Signal0(this, "navigation_mesh_changed")

    /** Signal `bake_finished()`; see [TypedSignal]. */
    val bakeFinished: Signal0
        @JvmName("bakeFinishedTypedSignal")
        get() = Signal0(this, "bake_finished")

    object Signals {
        const val navigationMeshChanged: String = "navigation_mesh_changed"
        const val bakeFinished: String = "bake_finished"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): NavigationRegion3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): NavigationRegion3D? =
            if (handle.address() == 0L) null else NavigationRegion3D(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_RID_HASH = 2944877500L
        @JvmField
        val getRidBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "get_rid", GET_RID_HASH)

        private const val SET_NAVIGATION_MESH_HASH = 2923361153L
        @JvmField
        val setNavigationMeshBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "set_navigation_mesh", SET_NAVIGATION_MESH_HASH)

        private const val GET_NAVIGATION_MESH_HASH = 1468720886L
        @JvmField
        val getNavigationMeshBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "get_navigation_mesh", GET_NAVIGATION_MESH_HASH)

        private const val SET_ENABLED_HASH = 2586408642L
        @JvmField
        val setEnabledBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "set_enabled", SET_ENABLED_HASH)

        private const val IS_ENABLED_HASH = 36873697L
        @JvmField
        val isEnabledBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "is_enabled", IS_ENABLED_HASH)

        private const val SET_NAVIGATION_MAP_HASH = 2722037293L
        @JvmField
        val setNavigationMapBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "set_navigation_map", SET_NAVIGATION_MAP_HASH)

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        @JvmField
        val getNavigationMapBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "get_navigation_map", GET_NAVIGATION_MAP_HASH)

        private const val SET_USE_EDGE_CONNECTIONS_HASH = 2586408642L
        @JvmField
        val setUseEdgeConnectionsBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "set_use_edge_connections", SET_USE_EDGE_CONNECTIONS_HASH)

        private const val GET_USE_EDGE_CONNECTIONS_HASH = 36873697L
        @JvmField
        val getUseEdgeConnectionsBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "get_use_edge_connections", GET_USE_EDGE_CONNECTIONS_HASH)

        private const val SET_NAVIGATION_LAYERS_HASH = 1286410249L
        @JvmField
        val setNavigationLayersBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "set_navigation_layers", SET_NAVIGATION_LAYERS_HASH)

        private const val GET_NAVIGATION_LAYERS_HASH = 3905245786L
        @JvmField
        val getNavigationLayersBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "get_navigation_layers", GET_NAVIGATION_LAYERS_HASH)

        private const val SET_NAVIGATION_LAYER_VALUE_HASH = 300928843L
        @JvmField
        val setNavigationLayerValueBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "set_navigation_layer_value", SET_NAVIGATION_LAYER_VALUE_HASH)

        private const val GET_NAVIGATION_LAYER_VALUE_HASH = 1116898809L
        @JvmField
        val getNavigationLayerValueBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "get_navigation_layer_value", GET_NAVIGATION_LAYER_VALUE_HASH)

        private const val GET_REGION_RID_HASH = 2944877500L
        @JvmField
        val getRegionRidBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "get_region_rid", GET_REGION_RID_HASH)

        private const val SET_ENTER_COST_HASH = 373806689L
        @JvmField
        val setEnterCostBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "set_enter_cost", SET_ENTER_COST_HASH)

        private const val GET_ENTER_COST_HASH = 1740695150L
        @JvmField
        val getEnterCostBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "get_enter_cost", GET_ENTER_COST_HASH)

        private const val SET_TRAVEL_COST_HASH = 373806689L
        @JvmField
        val setTravelCostBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "set_travel_cost", SET_TRAVEL_COST_HASH)

        private const val GET_TRAVEL_COST_HASH = 1740695150L
        @JvmField
        val getTravelCostBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "get_travel_cost", GET_TRAVEL_COST_HASH)

        private const val BAKE_NAVIGATION_MESH_HASH = 3216645846L
        @JvmField
        val bakeNavigationMeshBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "bake_navigation_mesh", BAKE_NAVIGATION_MESH_HASH)

        private const val IS_BAKING_HASH = 36873697L
        @JvmField
        val isBakingBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "is_baking", IS_BAKING_HASH)

        private const val GET_BOUNDS_HASH = 1068685055L
        @JvmField
        val getBoundsBind =
            ObjectCalls.getMethodBind("NavigationRegion3D", "get_bounds", GET_BOUNDS_HASH)
    }
}
