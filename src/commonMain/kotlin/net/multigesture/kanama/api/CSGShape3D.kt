package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

/**
 * The CSG base class.
 *
 * Generated from Godot docs: CSGShape3D
 */
open class CSGShape3D(handle: GodotHandle) : GeometryInstance3D(handle) {
    var autosmooth: Boolean
        @JvmName("autosmoothProperty")
        get() = isAutosmooth()
        @JvmName("setAutosmoothProperty")
        set(value) = setAutosmooth(value)

    var smoothingAngle: Double
        @JvmName("smoothingAngleProperty")
        get() = getSmoothingAngle()
        @JvmName("setSmoothingAngleProperty")
        set(value) = setSmoothingAngle(value)

    var operation: CSGShape3D.Operation
        @JvmName("operationProperty")
        get() = getOperation()
        @JvmName("setOperationProperty")
        set(value) = setOperation(value)

    var snap: Double
        @JvmName("snapProperty")
        get() = getSnap()
        @JvmName("setSnapProperty")
        set(value) = setSnap(value)

    var calculateTangents: Boolean
        @JvmName("calculateTangentsProperty")
        get() = isCalculatingTangents()
        @JvmName("setCalculateTangentsProperty")
        set(value) = setCalculateTangents(value)

    var useCollision: Boolean
        @JvmName("useCollisionProperty")
        get() = isUsingCollision()
        @JvmName("setUseCollisionProperty")
        set(value) = setUseCollision(value)

    var collisionLayer: Long
        @JvmName("collisionLayerProperty")
        get() = getCollisionLayer()
        @JvmName("setCollisionLayerProperty")
        set(value) = setCollisionLayer(value)

    var collisionMask: Long
        @JvmName("collisionMaskProperty")
        get() = getCollisionMask()
        @JvmName("setCollisionMaskProperty")
        set(value) = setCollisionMask(value)

    var collisionPriority: Double
        @JvmName("collisionPriorityProperty")
        get() = getCollisionPriority()
        @JvmName("setCollisionPriorityProperty")
        set(value) = setCollisionPriority(value)

    /**
     * Returns `true` if this is a root shape and is thus the object that is rendered.
     *
     * Generated from Godot docs: CSGShape3D.is_root_shape
     */
    fun isRootShape(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isRootShapeBind, segment)
    }

    /**
     * The operation that is performed on this shape. This is ignored for the first CSG child node as
     * the operation is between this node and the previous child of this nodes parent.
     *
     * Generated from Godot docs: CSGShape3D.set_operation
     */
    fun setOperation(operation: CSGShape3D.Operation) {
        ObjectCalls.ptrcallWithLongArg(Binds.setOperationBind, segment, operation.value)
    }

    /**
     * The operation that is performed on this shape. This is ignored for the first CSG child node as
     * the operation is between this node and the previous child of this nodes parent.
     *
     * Generated from Godot docs: CSGShape3D.get_operation
     */
    fun getOperation(): CSGShape3D.Operation {
        return CSGShape3D.Operation(ObjectCalls.ptrcallNoArgsRetLong(Binds.getOperationBind, segment))
    }

    /**
     * This property does nothing.
     *
     * Generated from Godot docs: CSGShape3D.set_snap
     */
    fun setSnap(snap: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSnapBind, segment, snap)
    }

    /**
     * This property does nothing.
     *
     * Generated from Godot docs: CSGShape3D.get_snap
     */
    fun getSnap(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSnapBind, segment)
    }

    /**
     * Adds a collision shape to the physics engine for our CSG shape. This will always act like a
     * static body. Note that the collision shape is still active even if the CSG shape itself is
     * hidden. See also `collision_mask` and `collision_priority`.
     *
     * Generated from Godot docs: CSGShape3D.set_use_collision
     */
    fun setUseCollision(operation: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseCollisionBind, segment, operation)
    }

    /**
     * Adds a collision shape to the physics engine for our CSG shape. This will always act like a
     * static body. Note that the collision shape is still active even if the CSG shape itself is
     * hidden. See also `collision_mask` and `collision_priority`.
     *
     * Generated from Godot docs: CSGShape3D.is_using_collision
     */
    fun isUsingCollision(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingCollisionBind, segment)
    }

    /**
     * The physics layers this area is in. Collidable objects can exist in any of 32 different layers.
     * These layers work like a tagging system, and are not visual. A collidable can use these layers
     * to select with which objects it can collide, using the collision_mask property. A contact is
     * detected if object A is in any of the layers that object B scans, or object B is in any layer
     * scanned by object A. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: CSGShape3D.set_collision_layer
     */
    fun setCollisionLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionLayerBind, segment, layer)
    }

    /**
     * The physics layers this area is in. Collidable objects can exist in any of 32 different layers.
     * These layers work like a tagging system, and are not visual. A collidable can use these layers
     * to select with which objects it can collide, using the collision_mask property. A contact is
     * detected if object A is in any of the layers that object B scans, or object B is in any layer
     * scanned by object A. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: CSGShape3D.get_collision_layer
     */
    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionLayerBind, segment)
    }

    /**
     * The physics layers this CSG shape scans for collisions. Only effective if `use_collision` is
     * `true`. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: CSGShape3D.set_collision_mask
     */
    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionMaskBind, segment, mask)
    }

    /**
     * The physics layers this CSG shape scans for collisions. Only effective if `use_collision` is
     * `true`. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: CSGShape3D.get_collision_mask
     */
    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionMaskBind, segment)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_mask`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: CSGShape3D.set_collision_mask_value
     */
    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionMaskValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_mask` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: CSGShape3D.get_collision_mask_value
     */
    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionMaskValueBind, segment, layerNumber)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_layer`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: CSGShape3D.set_collision_layer_value
     */
    fun setCollisionLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionLayerValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_layer` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: CSGShape3D.get_collision_layer_value
     */
    fun getCollisionLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionLayerValueBind, segment, layerNumber)
    }

    /**
     * The priority used to solve colliding when occurring penetration. Only effective if
     * `use_collision` is `true`. The higher the priority is, the lower the penetration into the object
     * will be. This can for example be used to prevent the player from breaking through the boundaries
     * of a level.
     *
     * Generated from Godot docs: CSGShape3D.set_collision_priority
     */
    fun setCollisionPriority(priority: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setCollisionPriorityBind, segment, priority)
    }

    /**
     * The priority used to solve colliding when occurring penetration. Only effective if
     * `use_collision` is `true`. The higher the priority is, the lower the penetration into the object
     * will be. This can for example be used to prevent the player from breaking through the boundaries
     * of a level.
     *
     * Generated from Godot docs: CSGShape3D.get_collision_priority
     */
    fun getCollisionPriority(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getCollisionPriorityBind, segment)
    }

    /**
     * Returns a baked physics `ConcavePolygonShape3D` of this node's CSG operation result. Returns an
     * empty shape if the node is not a CSG root node or has no valid geometry. Performance: If the CSG
     * operation results in a very detailed geometry with many faces physics performance will be very
     * slow. Concave shapes should in general only be used for static level geometry and not with
     * dynamic objects that are moving. Note: CSG mesh data updates are deferred, which means they are
     * updated with a delay of one rendered frame. To avoid getting an empty shape or outdated mesh
     * data, make sure to call `await get_tree().process_frame` before using `bake_collision_shape` in
     * `Node._ready` or after changing properties on the `CSGShape3D`.
     *
     * Generated from Godot docs: CSGShape3D.bake_collision_shape
     */
    fun bakeCollisionShape(): ConcavePolygonShape3D? {
        return ConcavePolygonShape3D.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.bakeCollisionShapeBind, segment))
    }

    /**
     * Calculate tangents for the CSG shape which allows the use of normal and height maps. This is
     * only applied on the root shape, this setting is ignored on any child. Setting this to `false`
     * can speed up shape generation slightly.
     *
     * Generated from Godot docs: CSGShape3D.set_calculate_tangents
     */
    fun setCalculateTangents(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCalculateTangentsBind, segment, enabled)
    }

    /**
     * Calculate tangents for the CSG shape which allows the use of normal and height maps. This is
     * only applied on the root shape, this setting is ignored on any child. Setting this to `false`
     * can speed up shape generation slightly.
     *
     * Generated from Godot docs: CSGShape3D.is_calculating_tangents
     */
    fun isCalculatingTangents(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isCalculatingTangentsBind, segment)
    }

    /**
     * Returns an `Array` with two elements, the first is the `Transform3D` of this node and the second
     * is the root `Mesh` of this node. Only works when this node is the root shape. Note: CSG mesh
     * data updates are deferred, which means they are updated with a delay of one rendered frame. To
     * avoid getting an empty shape or outdated mesh data, make sure to call `await
     * get_tree().process_frame` before using `get_meshes` in `Node._ready` or after changing
     * properties on the `CSGShape3D`.
     *
     * Generated from Godot docs: CSGShape3D.get_meshes
     */
    fun getMeshes(): List<Any?> {
        return ObjectCalls.ptrcallNoArgsRetArray(Binds.getMeshesBind, segment)
    }

    /**
     * Returns a baked static `ArrayMesh` of this node's CSG operation result. Materials from involved
     * CSG nodes are added as extra mesh surfaces. Returns an empty mesh if the node is not a CSG root
     * node or has no valid geometry. Note: CSG mesh data updates are deferred, which means they are
     * updated with a delay of one rendered frame. To avoid getting an empty mesh or outdated mesh
     * data, make sure to call `await get_tree().process_frame` before using `bake_static_mesh` in
     * `Node._ready` or after changing properties on the `CSGShape3D`.
     *
     * Generated from Godot docs: CSGShape3D.bake_static_mesh
     */
    fun bakeStaticMesh(): ArrayMesh? {
        return ArrayMesh.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.bakeStaticMeshBind, segment))
    }

    /**
     * Enables automatic smoothing. This overrides any smoothing on the CSG node and instead uses
     * `smoothing_angle` to calculate normals based on the angle between faces. Children of a
     * `CSGCombiner3D` node will be treated as a single mesh.
     *
     * Generated from Godot docs: CSGShape3D.set_autosmooth
     */
    fun setAutosmooth(autosmooth: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setAutosmoothBind, segment, autosmooth)
    }

    /**
     * Enables automatic smoothing. This overrides any smoothing on the CSG node and instead uses
     * `smoothing_angle` to calculate normals based on the angle between faces. Children of a
     * `CSGCombiner3D` node will be treated as a single mesh.
     *
     * Generated from Godot docs: CSGShape3D.is_autosmooth
     */
    fun isAutosmooth(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAutosmoothBind, segment)
    }

    /**
     * When autosmooth is enabled, faces with an angle between them greater than this will be smoothed,
     * while faces with a smaller angle will remain sharp. Note: An angle lower than 0.1 will cause all
     * smoothing to be disabled, this can be used to increase performance.
     *
     * Generated from Godot docs: CSGShape3D.set_smoothing_angle
     */
    fun setSmoothingAngle(smoothingAngle: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setSmoothingAngleBind, segment, smoothingAngle)
    }

    /**
     * When autosmooth is enabled, faces with an angle between them greater than this will be smoothed,
     * while faces with a smaller angle will remain sharp. Note: An angle lower than 0.1 will cause all
     * smoothing to be disabled, this can be used to increase performance.
     *
     * Generated from Godot docs: CSGShape3D.get_smoothing_angle
     */
    fun getSmoothingAngle(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getSmoothingAngleBind, segment)
    }

    /**
     * Godot's `CSGShape3D.Operation` enum as a typed value: `.value` is the raw number Godot uses, and
     * the companion holds the named values (`CSGShape3D.Operation.<NAME>`).
     *
     * Generated from Godot docs: CSGShape3D.Operation
     */
    @JvmInline
    value class Operation(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * Geometry of both primitives is merged, intersecting geometry is removed.
             *
             * Generated from Godot docs: CSGShape3D.OPERATION_UNION
             */
            val UNION: Operation get() = Operation(0L)
            /**
             * Only intersecting geometry remains, the rest is removed.
             *
             * Generated from Godot docs: CSGShape3D.OPERATION_INTERSECTION
             */
            val INTERSECTION: Operation get() = Operation(1L)
            /**
             * The second shape is subtracted from the first, leaving a dent with its shape.
             *
             * Generated from Godot docs: CSGShape3D.OPERATION_SUBTRACTION
             */
            val SUBTRACTION: Operation get() = Operation(2L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): CSGShape3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): CSGShape3D? =
            if (handle.address() == 0L) null else CSGShape3D(GodotHandle(handle))
    }

    private object Binds {
        private const val IS_ROOT_SHAPE_HASH = 36873697L
        @JvmField
        val isRootShapeBind =
            ObjectCalls.getMethodBind("CSGShape3D", "is_root_shape", IS_ROOT_SHAPE_HASH)

        private const val SET_OPERATION_HASH = 811425055L
        @JvmField
        val setOperationBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_operation", SET_OPERATION_HASH)

        private const val GET_OPERATION_HASH = 2662425879L
        @JvmField
        val getOperationBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_operation", GET_OPERATION_HASH)

        private const val SET_SNAP_HASH = 373806689L
        @JvmField
        val setSnapBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_snap", SET_SNAP_HASH)

        private const val GET_SNAP_HASH = 1740695150L
        @JvmField
        val getSnapBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_snap", GET_SNAP_HASH)

        private const val SET_USE_COLLISION_HASH = 2586408642L
        @JvmField
        val setUseCollisionBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_use_collision", SET_USE_COLLISION_HASH)

        private const val IS_USING_COLLISION_HASH = 36873697L
        @JvmField
        val isUsingCollisionBind =
            ObjectCalls.getMethodBind("CSGShape3D", "is_using_collision", IS_USING_COLLISION_HASH)

        private const val SET_COLLISION_LAYER_HASH = 1286410249L
        @JvmField
        val setCollisionLayerBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_layer", SET_COLLISION_LAYER_HASH)

        private const val GET_COLLISION_LAYER_HASH = 3905245786L
        @JvmField
        val getCollisionLayerBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_layer", GET_COLLISION_LAYER_HASH)

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        @JvmField
        val setCollisionMaskBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_mask", SET_COLLISION_MASK_HASH)

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        @JvmField
        val getCollisionMaskBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_mask", GET_COLLISION_MASK_HASH)

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        @JvmField
        val setCollisionMaskValueBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        @JvmField
        val getCollisionMaskValueBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)

        private const val SET_COLLISION_LAYER_VALUE_HASH = 300928843L
        @JvmField
        val setCollisionLayerValueBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_layer_value", SET_COLLISION_LAYER_VALUE_HASH)

        private const val GET_COLLISION_LAYER_VALUE_HASH = 1116898809L
        @JvmField
        val getCollisionLayerValueBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_layer_value", GET_COLLISION_LAYER_VALUE_HASH)

        private const val SET_COLLISION_PRIORITY_HASH = 373806689L
        @JvmField
        val setCollisionPriorityBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_collision_priority", SET_COLLISION_PRIORITY_HASH)

        private const val GET_COLLISION_PRIORITY_HASH = 1740695150L
        @JvmField
        val getCollisionPriorityBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_collision_priority", GET_COLLISION_PRIORITY_HASH)

        private const val BAKE_COLLISION_SHAPE_HASH = 36102322L
        @JvmField
        val bakeCollisionShapeBind =
            ObjectCalls.getMethodBind("CSGShape3D", "bake_collision_shape", BAKE_COLLISION_SHAPE_HASH)

        private const val SET_CALCULATE_TANGENTS_HASH = 2586408642L
        @JvmField
        val setCalculateTangentsBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_calculate_tangents", SET_CALCULATE_TANGENTS_HASH)

        private const val IS_CALCULATING_TANGENTS_HASH = 36873697L
        @JvmField
        val isCalculatingTangentsBind =
            ObjectCalls.getMethodBind("CSGShape3D", "is_calculating_tangents", IS_CALCULATING_TANGENTS_HASH)

        private const val GET_MESHES_HASH = 3995934104L
        @JvmField
        val getMeshesBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_meshes", GET_MESHES_HASH)

        private const val BAKE_STATIC_MESH_HASH = 1605880883L
        @JvmField
        val bakeStaticMeshBind =
            ObjectCalls.getMethodBind("CSGShape3D", "bake_static_mesh", BAKE_STATIC_MESH_HASH)

        private const val SET_AUTOSMOOTH_HASH = 2586408642L
        @JvmField
        val setAutosmoothBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_autosmooth", SET_AUTOSMOOTH_HASH)

        private const val IS_AUTOSMOOTH_HASH = 36873697L
        @JvmField
        val isAutosmoothBind =
            ObjectCalls.getMethodBind("CSGShape3D", "is_autosmooth", IS_AUTOSMOOTH_HASH)

        private const val SET_SMOOTHING_ANGLE_HASH = 373806689L
        @JvmField
        val setSmoothingAngleBind =
            ObjectCalls.getMethodBind("CSGShape3D", "set_smoothing_angle", SET_SMOOTHING_ANGLE_HASH)

        private const val GET_SMOOTHING_ANGLE_HASH = 1740695150L
        @JvmField
        val getSmoothingAngleBind =
            ObjectCalls.getMethodBind("CSGShape3D", "get_smoothing_angle", GET_SMOOTHING_ANGLE_HASH)
    }
}
