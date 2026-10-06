package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * A link between two positions on `NavigationRegion2D`s that agents can be routed through.
 *
 * Generated from Godot docs: NavigationLink2D
 */
class NavigationLink2D(handle: GodotHandle) : Node2D(handle) {
    var enabled: Boolean
        @JvmName("enabledProperty")
        get() = isEnabled()
        @JvmName("setEnabledProperty")
        set(value) = setEnabled(value)

    var bidirectional: Boolean
        @JvmName("bidirectionalProperty")
        get() = isBidirectional()
        @JvmName("setBidirectionalProperty")
        set(value) = setBidirectional(value)

    var navigationLayers: Long
        @JvmName("navigationLayersProperty")
        get() = getNavigationLayers()
        @JvmName("setNavigationLayersProperty")
        set(value) = setNavigationLayers(value)

    var startPosition: Vector2
        @JvmName("startPositionProperty")
        get() = getStartPosition()
        @JvmName("setStartPositionProperty")
        set(value) = setStartPosition(value)

    var endPosition: Vector2
        @JvmName("endPositionProperty")
        get() = getEndPosition()
        @JvmName("setEndPositionProperty")
        set(value) = setEndPosition(value)

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
     * Returns the `RID` of this link on the `NavigationServer2D`.
     *
     * Generated from Godot docs: NavigationLink2D.get_rid
     */
    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getRidBind, segment)
    }

    /**
     * Whether this link is currently active. If `false`, `NavigationServer2D.map_get_path` will ignore
     * this link.
     *
     * Generated from Godot docs: NavigationLink2D.set_enabled
     */
    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setEnabledBind, segment, enabled)
    }

    /**
     * Whether this link is currently active. If `false`, `NavigationServer2D.map_get_path` will ignore
     * this link.
     *
     * Generated from Godot docs: NavigationLink2D.is_enabled
     */
    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isEnabledBind, segment)
    }

    /**
     * Sets the `RID` of the navigation map this link should use. By default the link will
     * automatically join the `World2D` default navigation map so this function is only required to
     * override the default map.
     *
     * Generated from Godot docs: NavigationLink2D.set_navigation_map
     */
    fun setNavigationMap(navigationMap: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.setNavigationMapBind, segment, navigationMap)
    }

    /**
     * Returns the current navigation map `RID` used by this link.
     *
     * Generated from Godot docs: NavigationLink2D.get_navigation_map
     */
    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getNavigationMapBind, segment)
    }

    /**
     * Whether this link can be traveled in both directions or only from `start_position` to
     * `end_position`.
     *
     * Generated from Godot docs: NavigationLink2D.set_bidirectional
     */
    fun setBidirectional(bidirectional: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setBidirectionalBind, segment, bidirectional)
    }

    /**
     * Whether this link can be traveled in both directions or only from `start_position` to
     * `end_position`.
     *
     * Generated from Godot docs: NavigationLink2D.is_bidirectional
     */
    fun isBidirectional(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isBidirectionalBind, segment)
    }

    /**
     * A bitfield determining all navigation layers the link belongs to. These navigation layers will
     * be checked when requesting a path with `NavigationServer2D.map_get_path`.
     *
     * Generated from Godot docs: NavigationLink2D.set_navigation_layers
     */
    fun setNavigationLayers(navigationLayers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setNavigationLayersBind, segment, navigationLayers)
    }

    /**
     * A bitfield determining all navigation layers the link belongs to. These navigation layers will
     * be checked when requesting a path with `NavigationServer2D.map_get_path`.
     *
     * Generated from Godot docs: NavigationLink2D.get_navigation_layers
     */
    fun getNavigationLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getNavigationLayersBind, segment)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `navigation_layers` bitmask,
     * given a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationLink2D.set_navigation_layer_value
     */
    fun setNavigationLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setNavigationLayerValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `navigation_layers` bitmask is enabled, given
     * a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationLink2D.get_navigation_layer_value
     */
    fun getNavigationLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getNavigationLayerValueBind, segment, layerNumber)
    }

    /**
     * Starting position of the link. This position will search out the nearest polygon in the
     * navigation mesh to attach to. The distance the link will search is controlled by
     * `NavigationServer2D.map_set_link_connection_radius`.
     *
     * Generated from Godot docs: NavigationLink2D.set_start_position
     */
    fun setStartPosition(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setStartPositionBind, segment, position)
    }

    /**
     * Starting position of the link. This position will search out the nearest polygon in the
     * navigation mesh to attach to. The distance the link will search is controlled by
     * `NavigationServer2D.map_set_link_connection_radius`.
     *
     * Generated from Godot docs: NavigationLink2D.get_start_position
     */
    fun getStartPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getStartPositionBind, segment)
    }

    /**
     * Ending position of the link. This position will search out the nearest polygon in the navigation
     * mesh to attach to. The distance the link will search is controlled by
     * `NavigationServer2D.map_set_link_connection_radius`.
     *
     * Generated from Godot docs: NavigationLink2D.set_end_position
     */
    fun setEndPosition(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setEndPositionBind, segment, position)
    }

    /**
     * Ending position of the link. This position will search out the nearest polygon in the navigation
     * mesh to attach to. The distance the link will search is controlled by
     * `NavigationServer2D.map_set_link_connection_radius`.
     *
     * Generated from Godot docs: NavigationLink2D.get_end_position
     */
    fun getEndPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getEndPositionBind, segment)
    }

    /**
     * Sets the `start_position` that is relative to the link from a global `position`.
     *
     * Generated from Godot docs: NavigationLink2D.set_global_start_position
     */
    fun setGlobalStartPosition(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setGlobalStartPositionBind, segment, position)
    }

    /**
     * Returns the `start_position` that is relative to the link as a global position.
     *
     * Generated from Godot docs: NavigationLink2D.get_global_start_position
     */
    fun getGlobalStartPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getGlobalStartPositionBind, segment)
    }

    /**
     * Sets the `end_position` that is relative to the link from a global `position`.
     *
     * Generated from Godot docs: NavigationLink2D.set_global_end_position
     */
    fun setGlobalEndPosition(position: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setGlobalEndPositionBind, segment, position)
    }

    /**
     * Returns the `end_position` that is relative to the link as a global position.
     *
     * Generated from Godot docs: NavigationLink2D.get_global_end_position
     */
    fun getGlobalEndPosition(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getGlobalEndPositionBind, segment)
    }

    /**
     * When pathfinding enters this link from another regions navigation mesh the `enter_cost` value is
     * added to the path distance for determining the shortest path.
     *
     * Generated from Godot docs: NavigationLink2D.set_enter_cost
     */
    fun setEnterCost(enterCost: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setEnterCostBind, segment, enterCost)
    }

    /**
     * When pathfinding enters this link from another regions navigation mesh the `enter_cost` value is
     * added to the path distance for determining the shortest path.
     *
     * Generated from Godot docs: NavigationLink2D.get_enter_cost
     */
    fun getEnterCost(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getEnterCostBind, segment)
    }

    /**
     * When pathfinding moves along the link the traveled distance is multiplied with `travel_cost` for
     * determining the shortest path.
     *
     * Generated from Godot docs: NavigationLink2D.set_travel_cost
     */
    fun setTravelCost(travelCost: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTravelCostBind, segment, travelCost)
    }

    /**
     * When pathfinding moves along the link the traveled distance is multiplied with `travel_cost` for
     * determining the shortest path.
     *
     * Generated from Godot docs: NavigationLink2D.get_travel_cost
     */
    fun getTravelCost(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTravelCostBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): NavigationLink2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): NavigationLink2D? =
            if (handle.address() == 0L) null else NavigationLink2D(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_RID_HASH = 2944877500L
        @JvmField
        val getRidBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "get_rid", GET_RID_HASH)

        private const val SET_ENABLED_HASH = 2586408642L
        @JvmField
        val setEnabledBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "set_enabled", SET_ENABLED_HASH)

        private const val IS_ENABLED_HASH = 36873697L
        @JvmField
        val isEnabledBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "is_enabled", IS_ENABLED_HASH)

        private const val SET_NAVIGATION_MAP_HASH = 2722037293L
        @JvmField
        val setNavigationMapBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "set_navigation_map", SET_NAVIGATION_MAP_HASH)

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        @JvmField
        val getNavigationMapBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "get_navigation_map", GET_NAVIGATION_MAP_HASH)

        private const val SET_BIDIRECTIONAL_HASH = 2586408642L
        @JvmField
        val setBidirectionalBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "set_bidirectional", SET_BIDIRECTIONAL_HASH)

        private const val IS_BIDIRECTIONAL_HASH = 36873697L
        @JvmField
        val isBidirectionalBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "is_bidirectional", IS_BIDIRECTIONAL_HASH)

        private const val SET_NAVIGATION_LAYERS_HASH = 1286410249L
        @JvmField
        val setNavigationLayersBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "set_navigation_layers", SET_NAVIGATION_LAYERS_HASH)

        private const val GET_NAVIGATION_LAYERS_HASH = 3905245786L
        @JvmField
        val getNavigationLayersBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "get_navigation_layers", GET_NAVIGATION_LAYERS_HASH)

        private const val SET_NAVIGATION_LAYER_VALUE_HASH = 300928843L
        @JvmField
        val setNavigationLayerValueBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "set_navigation_layer_value", SET_NAVIGATION_LAYER_VALUE_HASH)

        private const val GET_NAVIGATION_LAYER_VALUE_HASH = 1116898809L
        @JvmField
        val getNavigationLayerValueBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "get_navigation_layer_value", GET_NAVIGATION_LAYER_VALUE_HASH)

        private const val SET_START_POSITION_HASH = 743155724L
        @JvmField
        val setStartPositionBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "set_start_position", SET_START_POSITION_HASH)

        private const val GET_START_POSITION_HASH = 3341600327L
        @JvmField
        val getStartPositionBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "get_start_position", GET_START_POSITION_HASH)

        private const val SET_END_POSITION_HASH = 743155724L
        @JvmField
        val setEndPositionBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "set_end_position", SET_END_POSITION_HASH)

        private const val GET_END_POSITION_HASH = 3341600327L
        @JvmField
        val getEndPositionBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "get_end_position", GET_END_POSITION_HASH)

        private const val SET_GLOBAL_START_POSITION_HASH = 743155724L
        @JvmField
        val setGlobalStartPositionBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "set_global_start_position", SET_GLOBAL_START_POSITION_HASH)

        private const val GET_GLOBAL_START_POSITION_HASH = 3341600327L
        @JvmField
        val getGlobalStartPositionBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "get_global_start_position", GET_GLOBAL_START_POSITION_HASH)

        private const val SET_GLOBAL_END_POSITION_HASH = 743155724L
        @JvmField
        val setGlobalEndPositionBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "set_global_end_position", SET_GLOBAL_END_POSITION_HASH)

        private const val GET_GLOBAL_END_POSITION_HASH = 3341600327L
        @JvmField
        val getGlobalEndPositionBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "get_global_end_position", GET_GLOBAL_END_POSITION_HASH)

        private const val SET_ENTER_COST_HASH = 373806689L
        @JvmField
        val setEnterCostBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "set_enter_cost", SET_ENTER_COST_HASH)

        private const val GET_ENTER_COST_HASH = 1740695150L
        @JvmField
        val getEnterCostBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "get_enter_cost", GET_ENTER_COST_HASH)

        private const val SET_TRAVEL_COST_HASH = 373806689L
        @JvmField
        val setTravelCostBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "set_travel_cost", SET_TRAVEL_COST_HASH)

        private const val GET_TRAVEL_COST_HASH = 1740695150L
        @JvmField
        val getTravelCostBind =
            ObjectCalls.getMethodBind("NavigationLink2D", "get_travel_cost", GET_TRAVEL_COST_HASH)
    }
}
