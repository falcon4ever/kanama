package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Rect2

/**
 * A traversable 2D region that `NavigationAgent2D`s can use for pathfinding.
 *
 * Generated from Godot docs: NavigationRegion2D
 */
class NavigationRegion2D(handle: MemorySegment) : Node2D(handle) {
    var navigationPolygon: NavigationPolygon?
        @JvmName("navigationPolygonProperty")
        get() = getNavigationPolygon()
        @JvmName("setNavigationPolygonProperty")
        set(value) = setNavigationPolygon(value)

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

    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
    }

    fun setNavigationPolygon(navigationPolygon: NavigationPolygon?) {
        ObjectCalls.ptrcallWithObjectArgs(setNavigationPolygonBind, handle, listOf(navigationPolygon?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getNavigationPolygon(): NavigationPolygon? {
        return NavigationPolygon.wrap(ObjectCalls.ptrcallNoArgsRetObject(getNavigationPolygonBind, handle))
    }

    fun setEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setEnabledBind, handle, enabled)
    }

    fun isEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isEnabledBind, handle)
    }

    fun setNavigationMap(navigationMap: RID) {
        ObjectCalls.ptrcallWithRIDArg(setNavigationMapBind, handle, navigationMap)
    }

    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getNavigationMapBind, handle)
    }

    fun setUseEdgeConnections(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseEdgeConnectionsBind, handle, enabled)
    }

    fun getUseEdgeConnections(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUseEdgeConnectionsBind, handle)
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

    fun getRegionRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRegionRidBind, handle)
    }

    fun setEnterCost(enterCost: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEnterCostBind, handle, enterCost)
    }

    fun getEnterCost(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEnterCostBind, handle)
    }

    fun setTravelCost(travelCost: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setTravelCostBind, handle, travelCost)
    }

    fun getTravelCost(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTravelCostBind, handle)
    }

    fun bakeNavigationPolygon(onThread: Boolean = true) {
        ObjectCalls.ptrcallWithBoolArg(bakeNavigationPolygonBind, handle, onThread)
    }

    fun isBaking(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBakingBind, handle)
    }

    fun getBounds(): Rect2 {
        return ObjectCalls.ptrcallNoArgsRetRect2(getBoundsBind, handle)
    }

    object Signals {
        const val navigationPolygonChanged: String = "navigation_polygon_changed"
        const val bakeFinished: String = "bake_finished"
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): NavigationRegion2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationRegion2D? =
            if (handle.address() == 0L) null else NavigationRegion2D(handle)

        private const val GET_RID_HASH = 2944877500L
        private val getRidBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "get_rid", GET_RID_HASH)
        }

        private const val SET_NAVIGATION_POLYGON_HASH = 1515040758L
        private val setNavigationPolygonBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "set_navigation_polygon", SET_NAVIGATION_POLYGON_HASH)
        }

        private const val GET_NAVIGATION_POLYGON_HASH = 1046532237L
        private val getNavigationPolygonBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "get_navigation_polygon", GET_NAVIGATION_POLYGON_HASH)
        }

        private const val SET_ENABLED_HASH = 2586408642L
        private val setEnabledBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "set_enabled", SET_ENABLED_HASH)
        }

        private const val IS_ENABLED_HASH = 36873697L
        private val isEnabledBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "is_enabled", IS_ENABLED_HASH)
        }

        private const val SET_NAVIGATION_MAP_HASH = 2722037293L
        private val setNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "set_navigation_map", SET_NAVIGATION_MAP_HASH)
        }

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        private val getNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "get_navigation_map", GET_NAVIGATION_MAP_HASH)
        }

        private const val SET_USE_EDGE_CONNECTIONS_HASH = 2586408642L
        private val setUseEdgeConnectionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "set_use_edge_connections", SET_USE_EDGE_CONNECTIONS_HASH)
        }

        private const val GET_USE_EDGE_CONNECTIONS_HASH = 36873697L
        private val getUseEdgeConnectionsBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "get_use_edge_connections", GET_USE_EDGE_CONNECTIONS_HASH)
        }

        private const val SET_NAVIGATION_LAYERS_HASH = 1286410249L
        private val setNavigationLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "set_navigation_layers", SET_NAVIGATION_LAYERS_HASH)
        }

        private const val GET_NAVIGATION_LAYERS_HASH = 3905245786L
        private val getNavigationLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "get_navigation_layers", GET_NAVIGATION_LAYERS_HASH)
        }

        private const val SET_NAVIGATION_LAYER_VALUE_HASH = 300928843L
        private val setNavigationLayerValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "set_navigation_layer_value", SET_NAVIGATION_LAYER_VALUE_HASH)
        }

        private const val GET_NAVIGATION_LAYER_VALUE_HASH = 1116898809L
        private val getNavigationLayerValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "get_navigation_layer_value", GET_NAVIGATION_LAYER_VALUE_HASH)
        }

        private const val GET_REGION_RID_HASH = 2944877500L
        private val getRegionRidBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "get_region_rid", GET_REGION_RID_HASH)
        }

        private const val SET_ENTER_COST_HASH = 373806689L
        private val setEnterCostBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "set_enter_cost", SET_ENTER_COST_HASH)
        }

        private const val GET_ENTER_COST_HASH = 1740695150L
        private val getEnterCostBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "get_enter_cost", GET_ENTER_COST_HASH)
        }

        private const val SET_TRAVEL_COST_HASH = 373806689L
        private val setTravelCostBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "set_travel_cost", SET_TRAVEL_COST_HASH)
        }

        private const val GET_TRAVEL_COST_HASH = 1740695150L
        private val getTravelCostBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "get_travel_cost", GET_TRAVEL_COST_HASH)
        }

        private const val BAKE_NAVIGATION_POLYGON_HASH = 3216645846L
        private val bakeNavigationPolygonBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "bake_navigation_polygon", BAKE_NAVIGATION_POLYGON_HASH)
        }

        private const val IS_BAKING_HASH = 36873697L
        private val isBakingBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "is_baking", IS_BAKING_HASH)
        }

        private const val GET_BOUNDS_HASH = 1639390495L
        private val getBoundsBind by lazy {
            ObjectCalls.getMethodBind("NavigationRegion2D", "get_bounds", GET_BOUNDS_HASH)
        }
    }
}
