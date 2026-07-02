package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * A link between two positions on `NavigationRegion3D`s that agents can be routed through.
 *
 * Generated from Godot docs: NavigationLink3D
 */
class NavigationLink3D(handle: MemorySegment) : Node3D(handle) {
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

    var startPosition: Vector3
        @JvmName("startPositionProperty")
        get() = getStartPosition()
        @JvmName("setStartPositionProperty")
        set(value) = setStartPosition(value)

    var endPosition: Vector3
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

    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
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

    fun setBidirectional(bidirectional: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setBidirectionalBind, handle, bidirectional)
    }

    fun isBidirectional(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isBidirectionalBind, handle)
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

    fun setStartPosition(position: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setStartPositionBind, handle, position)
    }

    fun getStartPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getStartPositionBind, handle)
    }

    fun setEndPosition(position: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setEndPositionBind, handle, position)
    }

    fun getEndPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getEndPositionBind, handle)
    }

    fun setGlobalStartPosition(position: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGlobalStartPositionBind, handle, position)
    }

    fun getGlobalStartPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGlobalStartPositionBind, handle)
    }

    fun setGlobalEndPosition(position: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setGlobalEndPositionBind, handle, position)
    }

    fun getGlobalEndPosition(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getGlobalEndPositionBind, handle)
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

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): NavigationLink3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationLink3D? =
            if (handle.address() == 0L) null else NavigationLink3D(handle)

        private const val GET_RID_HASH = 2944877500L
        private val getRidBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "get_rid", GET_RID_HASH)
        }

        private const val SET_ENABLED_HASH = 2586408642L
        private val setEnabledBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "set_enabled", SET_ENABLED_HASH)
        }

        private const val IS_ENABLED_HASH = 36873697L
        private val isEnabledBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "is_enabled", IS_ENABLED_HASH)
        }

        private const val SET_NAVIGATION_MAP_HASH = 2722037293L
        private val setNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "set_navigation_map", SET_NAVIGATION_MAP_HASH)
        }

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        private val getNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "get_navigation_map", GET_NAVIGATION_MAP_HASH)
        }

        private const val SET_BIDIRECTIONAL_HASH = 2586408642L
        private val setBidirectionalBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "set_bidirectional", SET_BIDIRECTIONAL_HASH)
        }

        private const val IS_BIDIRECTIONAL_HASH = 36873697L
        private val isBidirectionalBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "is_bidirectional", IS_BIDIRECTIONAL_HASH)
        }

        private const val SET_NAVIGATION_LAYERS_HASH = 1286410249L
        private val setNavigationLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "set_navigation_layers", SET_NAVIGATION_LAYERS_HASH)
        }

        private const val GET_NAVIGATION_LAYERS_HASH = 3905245786L
        private val getNavigationLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "get_navigation_layers", GET_NAVIGATION_LAYERS_HASH)
        }

        private const val SET_NAVIGATION_LAYER_VALUE_HASH = 300928843L
        private val setNavigationLayerValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "set_navigation_layer_value", SET_NAVIGATION_LAYER_VALUE_HASH)
        }

        private const val GET_NAVIGATION_LAYER_VALUE_HASH = 1116898809L
        private val getNavigationLayerValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "get_navigation_layer_value", GET_NAVIGATION_LAYER_VALUE_HASH)
        }

        private const val SET_START_POSITION_HASH = 3460891852L
        private val setStartPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "set_start_position", SET_START_POSITION_HASH)
        }

        private const val GET_START_POSITION_HASH = 3360562783L
        private val getStartPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "get_start_position", GET_START_POSITION_HASH)
        }

        private const val SET_END_POSITION_HASH = 3460891852L
        private val setEndPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "set_end_position", SET_END_POSITION_HASH)
        }

        private const val GET_END_POSITION_HASH = 3360562783L
        private val getEndPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "get_end_position", GET_END_POSITION_HASH)
        }

        private const val SET_GLOBAL_START_POSITION_HASH = 3460891852L
        private val setGlobalStartPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "set_global_start_position", SET_GLOBAL_START_POSITION_HASH)
        }

        private const val GET_GLOBAL_START_POSITION_HASH = 3360562783L
        private val getGlobalStartPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "get_global_start_position", GET_GLOBAL_START_POSITION_HASH)
        }

        private const val SET_GLOBAL_END_POSITION_HASH = 3460891852L
        private val setGlobalEndPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "set_global_end_position", SET_GLOBAL_END_POSITION_HASH)
        }

        private const val GET_GLOBAL_END_POSITION_HASH = 3360562783L
        private val getGlobalEndPositionBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "get_global_end_position", GET_GLOBAL_END_POSITION_HASH)
        }

        private const val SET_ENTER_COST_HASH = 373806689L
        private val setEnterCostBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "set_enter_cost", SET_ENTER_COST_HASH)
        }

        private const val GET_ENTER_COST_HASH = 1740695150L
        private val getEnterCostBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "get_enter_cost", GET_ENTER_COST_HASH)
        }

        private const val SET_TRAVEL_COST_HASH = 373806689L
        private val setTravelCostBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "set_travel_cost", SET_TRAVEL_COST_HASH)
        }

        private const val GET_TRAVEL_COST_HASH = 1740695150L
        private val getTravelCostBind by lazy {
            ObjectCalls.getMethodBind("NavigationLink3D", "get_travel_cost", GET_TRAVEL_COST_HASH)
        }
    }
}
