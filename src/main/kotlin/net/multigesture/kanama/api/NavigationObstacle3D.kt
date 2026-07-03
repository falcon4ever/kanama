package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * 3D obstacle used to affect navigation mesh baking or constrain velocities of avoidance
 * controlled agents.
 *
 * Generated from Godot docs: NavigationObstacle3D
 */
class NavigationObstacle3D(handle: MemorySegment) : Node3D(handle) {
    var radius: Double
        @JvmName("radiusProperty")
        get() = getRadius()
        @JvmName("setRadiusProperty")
        set(value) = setRadius(value)

    var height: Double
        @JvmName("heightProperty")
        get() = getHeight()
        @JvmName("setHeightProperty")
        set(value) = setHeight(value)

    var vertices: List<Vector3>
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

    var velocity: Vector3
        @JvmName("velocityProperty")
        get() = getVelocity()
        @JvmName("setVelocityProperty")
        set(value) = setVelocity(value)

    var avoidanceLayers: Long
        @JvmName("avoidanceLayersProperty")
        get() = getAvoidanceLayers()
        @JvmName("setAvoidanceLayersProperty")
        set(value) = setAvoidanceLayers(value)

    var use3dAvoidance: Boolean
        @JvmName("use3dAvoidanceProperty")
        get() = getUse3dAvoidance()
        @JvmName("setUse3dAvoidanceProperty")
        set(value) = setUse3dAvoidance(value)

    /**
     * Returns the `RID` of this obstacle on the `NavigationServer3D`.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_rid
     */
    fun getRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getRidBind, handle)
    }

    /**
     * If `true` the obstacle affects avoidance using agents.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_avoidance_enabled
     */
    fun setAvoidanceEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAvoidanceEnabledBind, handle, enabled)
    }

    /**
     * If `true` the obstacle affects avoidance using agents.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_avoidance_enabled
     */
    fun getAvoidanceEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getAvoidanceEnabledBind, handle)
    }

    /**
     * Sets the `RID` of the navigation map this NavigationObstacle node should use and also updates
     * the `obstacle` on the NavigationServer.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_navigation_map
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
     * Generated from Godot docs: NavigationObstacle3D.get_navigation_map
     */
    fun getNavigationMap(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(getNavigationMapBind, handle)
    }

    /**
     * Sets the avoidance radius for the obstacle.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_radius
     */
    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, radius)
    }

    /**
     * Sets the avoidance radius for the obstacle.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    /**
     * Sets the obstacle height used in 2D avoidance. 2D avoidance using agent's ignore obstacles that
     * are below or above them.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_height
     */
    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setHeightBind, handle, height)
    }

    /**
     * Sets the obstacle height used in 2D avoidance. 2D avoidance using agent's ignore obstacles that
     * are below or above them.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_height
     */
    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getHeightBind, handle)
    }

    /**
     * Sets the wanted velocity for the obstacle so other agent's can better predict the obstacle if it
     * is moved with a velocity regularly (every frame) instead of warped to a new position. Does only
     * affect avoidance for the obstacles `radius`. Does nothing for the obstacles static vertices.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_velocity
     */
    fun setVelocity(velocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setVelocityBind, handle, velocity)
    }

    /**
     * Sets the wanted velocity for the obstacle so other agent's can better predict the obstacle if it
     * is moved with a velocity regularly (every frame) instead of warped to a new position. Does only
     * affect avoidance for the obstacles `radius`. Does nothing for the obstacles static vertices.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_velocity
     */
    fun getVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getVelocityBind, handle)
    }

    /**
     * The outline vertices of the obstacle. If the vertices are winded in clockwise order agents will
     * be pushed in by the obstacle, else they will be pushed out. Outlines can not be crossed or
     * overlap. Should the vertices using obstacle be warped to a new position agent's can not predict
     * this movement and may get trapped inside the obstacle.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_vertices
     */
    fun setVertices(vertices: List<Vector3>) {
        ObjectCalls.ptrcallWithPackedVector3ListArg(setVerticesBind, handle, vertices)
    }

    /**
     * The outline vertices of the obstacle. If the vertices are winded in clockwise order agents will
     * be pushed in by the obstacle, else they will be pushed out. Outlines can not be crossed or
     * overlap. Should the vertices using obstacle be warped to a new position agent's can not predict
     * this movement and may get trapped inside the obstacle.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_vertices
     */
    fun getVertices(): List<Vector3> {
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(getVerticesBind, handle)
    }

    /**
     * A bitfield determining the avoidance layers for this obstacle. Agents with a matching bit on the
     * their avoidance mask will avoid this obstacle.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_avoidance_layers
     */
    fun setAvoidanceLayers(layers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setAvoidanceLayersBind, handle, layers)
    }

    /**
     * A bitfield determining the avoidance layers for this obstacle. Agents with a matching bit on the
     * their avoidance mask will avoid this obstacle.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_avoidance_layers
     */
    fun getAvoidanceLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getAvoidanceLayersBind, handle)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `avoidance_layers` bitmask,
     * given a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_avoidance_layer_value
     */
    fun setAvoidanceLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(setAvoidanceLayerValueBind, handle, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `avoidance_layers` bitmask is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_avoidance_layer_value
     */
    fun getAvoidanceLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(getAvoidanceLayerValueBind, handle, layerNumber)
    }

    /**
     * If `true` the obstacle affects 3D avoidance using agent's with obstacle `radius`. If `false` the
     * obstacle affects 2D avoidance using agent's with both obstacle `vertices` as well as obstacle
     * `radius`.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_use_3d_avoidance
     */
    fun setUse3dAvoidance(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUse3dAvoidanceBind, handle, enabled)
    }

    /**
     * If `true` the obstacle affects 3D avoidance using agent's with obstacle `radius`. If `false` the
     * obstacle affects 2D avoidance using agent's with both obstacle `vertices` as well as obstacle
     * `radius`.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_use_3d_avoidance
     */
    fun getUse3dAvoidance(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getUse3dAvoidanceBind, handle)
    }

    /**
     * If enabled and parsed in a navigation mesh baking process the obstacle will discard source
     * geometry inside its `vertices` and `height` defined shape.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_affect_navigation_mesh
     */
    fun setAffectNavigationMesh(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAffectNavigationMeshBind, handle, enabled)
    }

    /**
     * If enabled and parsed in a navigation mesh baking process the obstacle will discard source
     * geometry inside its `vertices` and `height` defined shape.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_affect_navigation_mesh
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
     * Generated from Godot docs: NavigationObstacle3D.set_carve_navigation_mesh
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
     * Generated from Godot docs: NavigationObstacle3D.get_carve_navigation_mesh
     */
    fun getCarveNavigationMesh(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getCarveNavigationMeshBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): NavigationObstacle3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): NavigationObstacle3D? =
            if (handle.address() == 0L) null else NavigationObstacle3D(handle)

        private const val GET_RID_HASH = 2944877500L
        private val getRidBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_rid", GET_RID_HASH)
        }

        private const val SET_AVOIDANCE_ENABLED_HASH = 2586408642L
        private val setAvoidanceEnabledBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_avoidance_enabled", SET_AVOIDANCE_ENABLED_HASH)
        }

        private const val GET_AVOIDANCE_ENABLED_HASH = 36873697L
        private val getAvoidanceEnabledBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_avoidance_enabled", GET_AVOIDANCE_ENABLED_HASH)
        }

        private const val SET_NAVIGATION_MAP_HASH = 2722037293L
        private val setNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_navigation_map", SET_NAVIGATION_MAP_HASH)
        }

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        private val getNavigationMapBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_navigation_map", GET_NAVIGATION_MAP_HASH)
        }

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_HEIGHT_HASH = 373806689L
        private val setHeightBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_height", SET_HEIGHT_HASH)
        }

        private const val GET_HEIGHT_HASH = 1740695150L
        private val getHeightBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_height", GET_HEIGHT_HASH)
        }

        private const val SET_VELOCITY_HASH = 3460891852L
        private val setVelocityBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_velocity", SET_VELOCITY_HASH)
        }

        private const val GET_VELOCITY_HASH = 3360562783L
        private val getVelocityBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_velocity", GET_VELOCITY_HASH)
        }

        private const val SET_VERTICES_HASH = 334873810L
        private val setVerticesBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_vertices", SET_VERTICES_HASH)
        }

        private const val GET_VERTICES_HASH = 497664490L
        private val getVerticesBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_vertices", GET_VERTICES_HASH)
        }

        private const val SET_AVOIDANCE_LAYERS_HASH = 1286410249L
        private val setAvoidanceLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_avoidance_layers", SET_AVOIDANCE_LAYERS_HASH)
        }

        private const val GET_AVOIDANCE_LAYERS_HASH = 3905245786L
        private val getAvoidanceLayersBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_avoidance_layers", GET_AVOIDANCE_LAYERS_HASH)
        }

        private const val SET_AVOIDANCE_LAYER_VALUE_HASH = 300928843L
        private val setAvoidanceLayerValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_avoidance_layer_value", SET_AVOIDANCE_LAYER_VALUE_HASH)
        }

        private const val GET_AVOIDANCE_LAYER_VALUE_HASH = 1116898809L
        private val getAvoidanceLayerValueBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_avoidance_layer_value", GET_AVOIDANCE_LAYER_VALUE_HASH)
        }

        private const val SET_USE_3D_AVOIDANCE_HASH = 2586408642L
        private val setUse3dAvoidanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_use_3d_avoidance", SET_USE_3D_AVOIDANCE_HASH)
        }

        private const val GET_USE_3D_AVOIDANCE_HASH = 36873697L
        private val getUse3dAvoidanceBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_use_3d_avoidance", GET_USE_3D_AVOIDANCE_HASH)
        }

        private const val SET_AFFECT_NAVIGATION_MESH_HASH = 2586408642L
        private val setAffectNavigationMeshBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_affect_navigation_mesh", SET_AFFECT_NAVIGATION_MESH_HASH)
        }

        private const val GET_AFFECT_NAVIGATION_MESH_HASH = 36873697L
        private val getAffectNavigationMeshBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_affect_navigation_mesh", GET_AFFECT_NAVIGATION_MESH_HASH)
        }

        private const val SET_CARVE_NAVIGATION_MESH_HASH = 2586408642L
        private val setCarveNavigationMeshBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_carve_navigation_mesh", SET_CARVE_NAVIGATION_MESH_HASH)
        }

        private const val GET_CARVE_NAVIGATION_MESH_HASH = 36873697L
        private val getCarveNavigationMeshBind by lazy {
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_carve_navigation_mesh", GET_CARVE_NAVIGATION_MESH_HASH)
        }
    }
}
