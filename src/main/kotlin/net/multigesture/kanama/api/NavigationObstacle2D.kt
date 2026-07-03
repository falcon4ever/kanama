package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * 2D obstacle used to affect navigation mesh baking or constrain velocities of avoidance
 * controlled agents.
 *
 * Generated from Godot docs: NavigationObstacle2D
 */
class NavigationObstacle2D(handle: MemorySegment) : Node2D(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    var vertices: List<Vector2>
        @JvmName("verticesProperty")
        get() = getVertices()
        @JvmName("setVerticesProperty")
        set(value) = setVertices(value)

    var affectNavigationMesh: Boolean
        @JvmName("affectNavigationMeshProperty")
        get() = getAffectNavigationMesh()
        @JvmName("setAffectNavigationMeshProperty")
        set(value) = setAffectNavigationMesh(value)

    var carveNavigationMesh: Boolean
        @JvmName("carveNavigationMeshProperty")
        get() = getCarveNavigationMesh()
        @JvmName("setCarveNavigationMeshProperty")
        set(value) = setCarveNavigationMesh(value)

    var avoidanceEnabled: Boolean
        @JvmName("avoidanceEnabledProperty")
        get() = getAvoidanceEnabled()
        @JvmName("setAvoidanceEnabledProperty")
        set(value) = setAvoidanceEnabled(value)

    var velocity: Vector2
        @JvmName("velocityProperty")
        get() = getVelocity()
        @JvmName("setVelocityProperty")
        set(value) = setVelocity(value)

    var avoidanceLayers: Long
        @JvmName("avoidanceLayersProperty")
        get() = getAvoidanceLayers()
        @JvmName("setAvoidanceLayersProperty")
        set(value) = setAvoidanceLayers(value)

    /**
     * Returns the `RID` of this obstacle on the `NavigationServer2D`.
     *
     * Generated from Godot docs: NavigationObstacle2D.get_rid
     */
    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
    }

    /**
     * If `true` the obstacle affects avoidance using agents.
     *
     * Generated from Godot docs: NavigationObstacle2D.set_avoidance_enabled
     */
    fun setAvoidanceEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAvoidanceEnabledBind, handle, enabled)
    }

    /**
     * If `true` the obstacle affects avoidance using agents.
     *
     * Generated from Godot docs: NavigationObstacle2D.get_avoidance_enabled
     */
    fun getAvoidanceEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAvoidanceEnabledBind, handle)
    }

    /**
     * Sets the `RID` of the navigation map this NavigationObstacle node should use and also updates
     * the `obstacle` on the NavigationServer.
     *
     * Generated from Godot docs: NavigationObstacle2D.set_navigation_map
     */
    fun setNavigationMap(navigationMap: RID) {
        ObjectCalls.ptrcallWithRIDArg(setNavigationMapBind, handle, navigationMap)
    }

    /**
     * Returns the `RID` of the navigation map for this NavigationObstacle node. This function returns
     * always the map set on the NavigationObstacle node and not the map of the abstract obstacle on
     * the NavigationServer. If the obstacle map is changed directly with the NavigationServer API the
     * NavigationObstacle node will not be aware of the map change. Use `set_navigation_map` to change
     * the navigation map for the NavigationObstacle and also update the obstacle on the
     * NavigationServer.
     *
     * Generated from Godot docs: NavigationObstacle2D.get_navigation_map
     */
    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getNavigationMapBind, handle)
    }

    /**
     * Sets the avoidance radius for the obstacle.
     *
     * Generated from Godot docs: NavigationObstacle2D.set_radius
     */
    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    /**
     * Sets the avoidance radius for the obstacle.
     *
     * Generated from Godot docs: NavigationObstacle2D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    /**
     * Sets the wanted velocity for the obstacle so other agent's can better predict the obstacle if it
     * is moved with a velocity regularly (every frame) instead of warped to a new position. Does only
     * affect avoidance for the obstacles `radius`. Does nothing for the obstacles static vertices.
     *
     * Generated from Godot docs: NavigationObstacle2D.set_velocity
     */
    fun setVelocity(velocity: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setVelocityBind, handle, velocity)
    }

    /**
     * Sets the wanted velocity for the obstacle so other agent's can better predict the obstacle if it
     * is moved with a velocity regularly (every frame) instead of warped to a new position. Does only
     * affect avoidance for the obstacles `radius`. Does nothing for the obstacles static vertices.
     *
     * Generated from Godot docs: NavigationObstacle2D.get_velocity
     */
    fun getVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getVelocityBind, handle)
    }

    /**
     * The outline vertices of the obstacle. If the vertices are winded in clockwise order agents will
     * be pushed in by the obstacle, else they will be pushed out. Outlines can not be crossed or
     * overlap. Should the vertices using obstacle be warped to a new position agent's can not predict
     * this movement and may get trapped inside the obstacle.
     *
     * Generated from Godot docs: NavigationObstacle2D.set_vertices
     */
    fun setVertices(vertices: List<Vector2>) {
        ObjectCalls.ptrcallWithPackedVector2ListArg(setVerticesBind, handle, vertices)
    }

    /**
     * The outline vertices of the obstacle. If the vertices are winded in clockwise order agents will
     * be pushed in by the obstacle, else they will be pushed out. Outlines can not be crossed or
     * overlap. Should the vertices using obstacle be warped to a new position agent's can not predict
     * this movement and may get trapped inside the obstacle.
     *
     * Generated from Godot docs: NavigationObstacle2D.get_vertices
     */
    fun getVertices(): List<Vector2> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector2List(getVerticesBind, handle)
    }

    /**
     * A bitfield determining the avoidance layers for this obstacle. Agents with a matching bit on the
     * their avoidance mask will avoid this obstacle.
     *
     * Generated from Godot docs: NavigationObstacle2D.set_avoidance_layers
     */
    fun setAvoidanceLayers(layers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setAvoidanceLayersBind, handle, layers)
    }

    /**
     * A bitfield determining the avoidance layers for this obstacle. Agents with a matching bit on the
     * their avoidance mask will avoid this obstacle.
     *
     * Generated from Godot docs: NavigationObstacle2D.get_avoidance_layers
     */
    fun getAvoidanceLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getAvoidanceLayersBind, handle)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `avoidance_layers` bitmask,
     * given a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationObstacle2D.set_avoidance_layer_value
     */
    fun setAvoidanceLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAvoidanceLayerValueBind, handle, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `avoidance_layers` bitmask is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationObstacle2D.get_avoidance_layer_value
     */
    fun getAvoidanceLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getAvoidanceLayerValueBind, handle, layerNumber)
    }

    /**
     * If enabled and parsed in a navigation mesh baking process the obstacle will discard source
     * geometry inside its `vertices` defined shape.
     *
     * Generated from Godot docs: NavigationObstacle2D.set_affect_navigation_mesh
     */
    fun setAffectNavigationMesh(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAffectNavigationMeshBind, handle, enabled)
    }

    /**
     * If enabled and parsed in a navigation mesh baking process the obstacle will discard source
     * geometry inside its `vertices` defined shape.
     *
     * Generated from Godot docs: NavigationObstacle2D.get_affect_navigation_mesh
     */
    fun getAffectNavigationMesh(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAffectNavigationMeshBind, handle)
    }

    /**
     * If enabled the obstacle vertices will carve into the baked navigation mesh with the shape
     * unaffected by additional offsets (e.g. agent radius). It will still be affected by further
     * postprocessing of the baking process, like edge and polygon simplification. Requires
     * `affect_navigation_mesh` to be enabled.
     *
     * Generated from Godot docs: NavigationObstacle2D.set_carve_navigation_mesh
     */
    fun setCarveNavigationMesh(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCarveNavigationMeshBind, handle, enabled)
    }

    /**
     * If enabled the obstacle vertices will carve into the baked navigation mesh with the shape
     * unaffected by additional offsets (e.g. agent radius). It will still be affected by further
     * postprocessing of the baking process, like edge and polygon simplification. Requires
     * `affect_navigation_mesh` to be enabled.
     *
     * Generated from Godot docs: NavigationObstacle2D.get_carve_navigation_mesh
     */
    fun getCarveNavigationMesh(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getCarveNavigationMeshBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): NavigationObstacle2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationObstacle2D? =
            if (handle.address() == 0L) null else NavigationObstacle2D(handle)

        private const val GET_RID_HASH = 2944877500L
        private val getRidBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "get_rid", GET_RID_HASH)
        }

        private const val SET_AVOIDANCE_ENABLED_HASH = 2586408642L
        private val setAvoidanceEnabledBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "set_avoidance_enabled", SET_AVOIDANCE_ENABLED_HASH)
        }

        private const val GET_AVOIDANCE_ENABLED_HASH = 36873697L
        private val getAvoidanceEnabledBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "get_avoidance_enabled", GET_AVOIDANCE_ENABLED_HASH)
        }

        private const val SET_NAVIGATION_MAP_HASH = 2722037293L
        private val setNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "set_navigation_map", SET_NAVIGATION_MAP_HASH)
        }

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        private val getNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "get_navigation_map", GET_NAVIGATION_MAP_HASH)
        }

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_VELOCITY_HASH = 743155724L
        private val setVelocityBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "set_velocity", SET_VELOCITY_HASH)
        }

        private const val GET_VELOCITY_HASH = 3341600327L
        private val getVelocityBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "get_velocity", GET_VELOCITY_HASH)
        }

        private const val SET_VERTICES_HASH = 1509147220L
        private val setVerticesBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "set_vertices", SET_VERTICES_HASH)
        }

        private const val GET_VERTICES_HASH = 2961356807L
        private val getVerticesBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "get_vertices", GET_VERTICES_HASH)
        }

        private const val SET_AVOIDANCE_LAYERS_HASH = 1286410249L
        private val setAvoidanceLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "set_avoidance_layers", SET_AVOIDANCE_LAYERS_HASH)
        }

        private const val GET_AVOIDANCE_LAYERS_HASH = 3905245786L
        private val getAvoidanceLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "get_avoidance_layers", GET_AVOIDANCE_LAYERS_HASH)
        }

        private const val SET_AVOIDANCE_LAYER_VALUE_HASH = 300928843L
        private val setAvoidanceLayerValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "set_avoidance_layer_value", SET_AVOIDANCE_LAYER_VALUE_HASH)
        }

        private const val GET_AVOIDANCE_LAYER_VALUE_HASH = 1116898809L
        private val getAvoidanceLayerValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "get_avoidance_layer_value", GET_AVOIDANCE_LAYER_VALUE_HASH)
        }

        private const val SET_AFFECT_NAVIGATION_MESH_HASH = 2586408642L
        private val setAffectNavigationMeshBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "set_affect_navigation_mesh", SET_AFFECT_NAVIGATION_MESH_HASH)
        }

        private const val GET_AFFECT_NAVIGATION_MESH_HASH = 36873697L
        private val getAffectNavigationMeshBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "get_affect_navigation_mesh", GET_AFFECT_NAVIGATION_MESH_HASH)
        }

        private const val SET_CARVE_NAVIGATION_MESH_HASH = 2586408642L
        private val setCarveNavigationMeshBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "set_carve_navigation_mesh", SET_CARVE_NAVIGATION_MESH_HASH)
        }

        private const val GET_CARVE_NAVIGATION_MESH_HASH = 36873697L
        private val getCarveNavigationMeshBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle2D", "get_carve_navigation_mesh", GET_CARVE_NAVIGATION_MESH_HASH)
        }
    }
}
