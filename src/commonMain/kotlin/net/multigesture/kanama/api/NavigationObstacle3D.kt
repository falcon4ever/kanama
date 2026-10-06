package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * 3D obstacle used to affect navigation mesh baking or constrain velocities of avoidance
 * controlled agents.
 *
 * Generated from Godot docs: NavigationObstacle3D
 */
class NavigationObstacle3D(handle: GodotHandle) : Node3D(handle) {
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
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getRidBind, segment)
    }

    /**
     * If `true` the obstacle affects avoidance using agents.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_avoidance_enabled
     */
    fun setAvoidanceEnabled(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAvoidanceEnabledBind, segment, enabled)
    }

    /**
     * If `true` the obstacle affects avoidance using agents.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_avoidance_enabled
     */
    fun getAvoidanceEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getAvoidanceEnabledBind, segment)
    }

    /**
     * Sets the `RID` of the navigation map this NavigationObstacle node should use and also updates
     * the `obstacle` on the NavigationServer.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_navigation_map
     */
    fun setNavigationMap(navigationMap: RID) {
        ObjectCalls.ptrcallWithRIDArg(Binds.setNavigationMapBind, segment, navigationMap)
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
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getNavigationMapBind, segment)
    }

    /**
     * Sets the avoidance radius for the obstacle.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_radius
     */
    fun setRadius(radius: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setRadiusBind, segment, radius)
    }

    /**
     * Sets the avoidance radius for the obstacle.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getRadiusBind, segment)
    }

    /**
     * Sets the obstacle height used in 2D avoidance. 2D avoidance using agent's ignore obstacles that
     * are below or above them.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_height
     */
    fun setHeight(height: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setHeightBind, segment, height)
    }

    /**
     * Sets the obstacle height used in 2D avoidance. 2D avoidance using agent's ignore obstacles that
     * are below or above them.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_height
     */
    fun getHeight(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getHeightBind, segment)
    }

    /**
     * Sets the wanted velocity for the obstacle so other agent's can better predict the obstacle if it
     * is moved with a velocity regularly (every frame) instead of warped to a new position. Does only
     * affect avoidance for the obstacles `radius`. Does nothing for the obstacles static vertices.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_velocity
     */
    fun setVelocity(velocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setVelocityBind, segment, velocity)
    }

    /**
     * Sets the wanted velocity for the obstacle so other agent's can better predict the obstacle if it
     * is moved with a velocity regularly (every frame) instead of warped to a new position. Does only
     * affect avoidance for the obstacles `radius`. Does nothing for the obstacles static vertices.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_velocity
     */
    fun getVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getVelocityBind, segment)
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
        ObjectCalls.ptrcallWithPackedVector3ListArg(Binds.setVerticesBind, segment, vertices)
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
        return ObjectCalls.ptrcallNoArgsRetPackedVector3List(Binds.getVerticesBind, segment)
    }

    /**
     * A bitfield determining the avoidance layers for this obstacle. Agents with a matching bit on the
     * their avoidance mask will avoid this obstacle.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_avoidance_layers
     */
    fun setAvoidanceLayers(layers: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setAvoidanceLayersBind, segment, layers)
    }

    /**
     * A bitfield determining the avoidance layers for this obstacle. Agents with a matching bit on the
     * their avoidance mask will avoid this obstacle.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_avoidance_layers
     */
    fun getAvoidanceLayers(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getAvoidanceLayersBind, segment)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `avoidance_layers` bitmask,
     * given a `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_avoidance_layer_value
     */
    fun setAvoidanceLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setAvoidanceLayerValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `avoidance_layers` bitmask is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_avoidance_layer_value
     */
    fun getAvoidanceLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getAvoidanceLayerValueBind, segment, layerNumber)
    }

    /**
     * If `true` the obstacle affects 3D avoidance using agent's with obstacle `radius`. If `false` the
     * obstacle affects 2D avoidance using agent's with both obstacle `vertices` as well as obstacle
     * `radius`.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_use_3d_avoidance
     */
    fun setUse3dAvoidance(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUse3dAvoidanceBind, segment, enabled)
    }

    /**
     * If `true` the obstacle affects 3D avoidance using agent's with obstacle `radius`. If `false` the
     * obstacle affects 2D avoidance using agent's with both obstacle `vertices` as well as obstacle
     * `radius`.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_use_3d_avoidance
     */
    fun getUse3dAvoidance(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getUse3dAvoidanceBind, segment)
    }

    /**
     * If enabled and parsed in a navigation mesh baking process the obstacle will discard source
     * geometry inside its `vertices` and `height` defined shape.
     *
     * Generated from Godot docs: NavigationObstacle3D.set_affect_navigation_mesh
     */
    fun setAffectNavigationMesh(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAffectNavigationMeshBind, segment, enabled)
    }

    /**
     * If enabled and parsed in a navigation mesh baking process the obstacle will discard source
     * geometry inside its `vertices` and `height` defined shape.
     *
     * Generated from Godot docs: NavigationObstacle3D.get_affect_navigation_mesh
     */
    fun getAffectNavigationMesh(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getAffectNavigationMeshBind, segment)
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setCarveNavigationMeshBind, segment, enabled)
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
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getCarveNavigationMeshBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): NavigationObstacle3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): NavigationObstacle3D? =
            if (handle.address() == 0L) null else NavigationObstacle3D(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_RID_HASH = 2944877500L
        @JvmField
        val getRidBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_rid", GET_RID_HASH)

        private const val SET_AVOIDANCE_ENABLED_HASH = 2586408642L
        @JvmField
        val setAvoidanceEnabledBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_avoidance_enabled", SET_AVOIDANCE_ENABLED_HASH)

        private const val GET_AVOIDANCE_ENABLED_HASH = 36873697L
        @JvmField
        val getAvoidanceEnabledBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_avoidance_enabled", GET_AVOIDANCE_ENABLED_HASH)

        private const val SET_NAVIGATION_MAP_HASH = 2722037293L
        @JvmField
        val setNavigationMapBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_navigation_map", SET_NAVIGATION_MAP_HASH)

        private const val GET_NAVIGATION_MAP_HASH = 2944877500L
        @JvmField
        val getNavigationMapBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_navigation_map", GET_NAVIGATION_MAP_HASH)

        private const val SET_RADIUS_HASH = 373806689L
        @JvmField
        val setRadiusBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_radius", SET_RADIUS_HASH)

        private const val GET_RADIUS_HASH = 1740695150L
        @JvmField
        val getRadiusBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_radius", GET_RADIUS_HASH)

        private const val SET_HEIGHT_HASH = 373806689L
        @JvmField
        val setHeightBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_height", SET_HEIGHT_HASH)

        private const val GET_HEIGHT_HASH = 1740695150L
        @JvmField
        val getHeightBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_height", GET_HEIGHT_HASH)

        private const val SET_VELOCITY_HASH = 3460891852L
        @JvmField
        val setVelocityBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_velocity", SET_VELOCITY_HASH)

        private const val GET_VELOCITY_HASH = 3360562783L
        @JvmField
        val getVelocityBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_velocity", GET_VELOCITY_HASH)

        private const val SET_VERTICES_HASH = 334873810L
        @JvmField
        val setVerticesBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_vertices", SET_VERTICES_HASH)

        private const val GET_VERTICES_HASH = 497664490L
        @JvmField
        val getVerticesBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_vertices", GET_VERTICES_HASH)

        private const val SET_AVOIDANCE_LAYERS_HASH = 1286410249L
        @JvmField
        val setAvoidanceLayersBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_avoidance_layers", SET_AVOIDANCE_LAYERS_HASH)

        private const val GET_AVOIDANCE_LAYERS_HASH = 3905245786L
        @JvmField
        val getAvoidanceLayersBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_avoidance_layers", GET_AVOIDANCE_LAYERS_HASH)

        private const val SET_AVOIDANCE_LAYER_VALUE_HASH = 300928843L
        @JvmField
        val setAvoidanceLayerValueBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_avoidance_layer_value", SET_AVOIDANCE_LAYER_VALUE_HASH)

        private const val GET_AVOIDANCE_LAYER_VALUE_HASH = 1116898809L
        @JvmField
        val getAvoidanceLayerValueBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_avoidance_layer_value", GET_AVOIDANCE_LAYER_VALUE_HASH)

        private const val SET_USE_3D_AVOIDANCE_HASH = 2586408642L
        @JvmField
        val setUse3dAvoidanceBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_use_3d_avoidance", SET_USE_3D_AVOIDANCE_HASH)

        private const val GET_USE_3D_AVOIDANCE_HASH = 36873697L
        @JvmField
        val getUse3dAvoidanceBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_use_3d_avoidance", GET_USE_3D_AVOIDANCE_HASH)

        private const val SET_AFFECT_NAVIGATION_MESH_HASH = 2586408642L
        @JvmField
        val setAffectNavigationMeshBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_affect_navigation_mesh", SET_AFFECT_NAVIGATION_MESH_HASH)

        private const val GET_AFFECT_NAVIGATION_MESH_HASH = 36873697L
        @JvmField
        val getAffectNavigationMeshBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_affect_navigation_mesh", GET_AFFECT_NAVIGATION_MESH_HASH)

        private const val SET_CARVE_NAVIGATION_MESH_HASH = 2586408642L
        @JvmField
        val setCarveNavigationMeshBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "set_carve_navigation_mesh", SET_CARVE_NAVIGATION_MESH_HASH)

        private const val GET_CARVE_NAVIGATION_MESH_HASH = 36873697L
        @JvmField
        val getCarveNavigationMeshBind =
            ObjectCalls.getMethodBind("NavigationObstacle3D", "get_carve_navigation_mesh", GET_CARVE_NAVIGATION_MESH_HASH)
    }
}
