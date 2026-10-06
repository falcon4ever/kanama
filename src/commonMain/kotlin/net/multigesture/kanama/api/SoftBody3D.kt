package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.NodePath
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector3

/**
 * A deformable 3D physics mesh.
 *
 * Generated from Godot docs: SoftBody3D
 */
class SoftBody3D(handle: GodotHandle) : MeshInstance3D(handle) {
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

    var parentCollisionIgnore: NodePath
        @JvmName("parentCollisionIgnoreProperty")
        get() = getParentCollisionIgnore()
        @JvmName("setParentCollisionIgnoreProperty")
        set(value) = setParentCollisionIgnore(value)

    var simulationPrecision: Int
        @JvmName("simulationPrecisionProperty")
        get() = getSimulationPrecision()
        @JvmName("setSimulationPrecisionProperty")
        set(value) = setSimulationPrecision(value)

    var totalMass: Double
        @JvmName("totalMassProperty")
        get() = getTotalMass()
        @JvmName("setTotalMassProperty")
        set(value) = setTotalMass(value)

    var linearStiffness: Double
        @JvmName("linearStiffnessProperty")
        get() = getLinearStiffness()
        @JvmName("setLinearStiffnessProperty")
        set(value) = setLinearStiffness(value)

    var shrinkingFactor: Double
        @JvmName("shrinkingFactorProperty")
        get() = getShrinkingFactor()
        @JvmName("setShrinkingFactorProperty")
        set(value) = setShrinkingFactor(value)

    var pressureCoefficient: Double
        @JvmName("pressureCoefficientProperty")
        get() = getPressureCoefficient()
        @JvmName("setPressureCoefficientProperty")
        set(value) = setPressureCoefficient(value)

    var dampingCoefficient: Double
        @JvmName("dampingCoefficientProperty")
        get() = getDampingCoefficient()
        @JvmName("setDampingCoefficientProperty")
        set(value) = setDampingCoefficient(value)

    var dragCoefficient: Double
        @JvmName("dragCoefficientProperty")
        get() = getDragCoefficient()
        @JvmName("setDragCoefficientProperty")
        set(value) = setDragCoefficient(value)

    var rayPickable: Boolean
        @JvmName("rayPickableProperty")
        get() = isRayPickable()
        @JvmName("setRayPickableProperty")
        set(value) = setRayPickable(value)

    var disableMode: SoftBody3D.DisableMode
        @JvmName("disableModeProperty")
        get() = getDisableMode()
        @JvmName("setDisableModeProperty")
        set(value) = setDisableMode(value)

    /**
     * Returns the internal `RID` used by the `PhysicsServer3D` for this body.
     *
     * Generated from Godot docs: SoftBody3D.get_physics_rid
     */
    fun getPhysicsRid(): RID {
        return ObjectCalls.ptrcallNoArgsRetRID(Binds.getPhysicsRidBind, segment)
    }

    /**
     * The physics layers this SoftBody3D scans. Collision objects can scan one or more of 32 different
     * layers. See also `collision_layer`. Note: Object A can detect a contact with object B only if
     * object B is in any of the layers that object A scans. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: SoftBody3D.set_collision_mask
     */
    fun setCollisionMask(collisionMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionMaskBind, segment, collisionMask)
    }

    /**
     * The physics layers this SoftBody3D scans. Collision objects can scan one or more of 32 different
     * layers. See also `collision_layer`. Note: Object A can detect a contact with object B only if
     * object B is in any of the layers that object A scans. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: SoftBody3D.get_collision_mask
     */
    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionMaskBind, segment)
    }

    /**
     * The physics layers this SoftBody3D is in. Collision objects can exist in one or more of 32
     * different layers. See also `collision_mask`. Note: Object A can detect a contact with object B
     * only if object B is in any of the layers that object A scans. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: SoftBody3D.set_collision_layer
     */
    fun setCollisionLayer(collisionLayer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionLayerBind, segment, collisionLayer)
    }

    /**
     * The physics layers this SoftBody3D is in. Collision objects can exist in one or more of 32
     * different layers. See also `collision_mask`. Note: Object A can detect a contact with object B
     * only if object B is in any of the layers that object A scans. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: SoftBody3D.get_collision_layer
     */
    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionLayerBind, segment)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_mask`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: SoftBody3D.set_collision_mask_value
     */
    fun setCollisionMaskValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionMaskValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_mask` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: SoftBody3D.get_collision_mask_value
     */
    fun getCollisionMaskValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionMaskValueBind, segment, layerNumber)
    }

    /**
     * Based on `value`, enables or disables the specified layer in the `collision_layer`, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: SoftBody3D.set_collision_layer_value
     */
    fun setCollisionLayerValue(layerNumber: Int, value: Boolean) {
        ObjectCalls.ptrcallWithIntAndBoolArgs(Binds.setCollisionLayerValueBind, segment, layerNumber, value)
    }

    /**
     * Returns whether or not the specified layer of the `collision_layer` is enabled, given a
     * `layer_number` between 1 and 32.
     *
     * Generated from Godot docs: SoftBody3D.get_collision_layer_value
     */
    fun getCollisionLayerValue(layerNumber: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.getCollisionLayerValueBind, segment, layerNumber)
    }

    /**
     * `NodePath` to a `CollisionObject3D` this SoftBody3D should avoid clipping.
     *
     * Generated from Godot docs: SoftBody3D.set_parent_collision_ignore
     */
    fun setParentCollisionIgnore(parentCollisionIgnore: NodePath) {
        ObjectCalls.ptrcallWithNodePathArg(Binds.setParentCollisionIgnoreBind, segment, parentCollisionIgnore)
    }

    /**
     * `NodePath` to a `CollisionObject3D` this SoftBody3D should avoid clipping.
     *
     * Generated from Godot docs: SoftBody3D.get_parent_collision_ignore
     */
    fun getParentCollisionIgnore(): NodePath {
        return ObjectCalls.ptrcallNoArgsRetNodePath(Binds.getParentCollisionIgnoreBind, segment)
    }

    /**
     * Defines the behavior in physics when `Node.process_mode` is set to `Node.ProcessMode.DISABLED`.
     *
     * Generated from Godot docs: SoftBody3D.set_disable_mode
     */
    fun setDisableMode(mode: SoftBody3D.DisableMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setDisableModeBind, segment, mode.value)
    }

    /**
     * Defines the behavior in physics when `Node.process_mode` is set to `Node.ProcessMode.DISABLED`.
     *
     * Generated from Godot docs: SoftBody3D.get_disable_mode
     */
    fun getDisableMode(): SoftBody3D.DisableMode {
        return SoftBody3D.DisableMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getDisableModeBind, segment))
    }

    /**
     * Returns an array of nodes that were added as collision exceptions for this body.
     *
     * Generated from Godot docs: SoftBody3D.get_collision_exceptions
     */
    fun getCollisionExceptions(): List<PhysicsBody3D> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(Binds.getCollisionExceptionsBind, segment, PhysicsBody3D::wrap)
    }

    /**
     * Adds a body to the list of bodies that this body can't collide with.
     *
     * Generated from Godot docs: SoftBody3D.add_collision_exception_with
     */
    fun addCollisionExceptionWith(body: Node) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.addCollisionExceptionWithBind, segment, listOf(body.segment))
    }

    /**
     * Removes a body from the list of bodies that this body can't collide with.
     *
     * Generated from Godot docs: SoftBody3D.remove_collision_exception_with
     */
    fun removeCollisionExceptionWith(body: Node) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.removeCollisionExceptionWithBind, segment, listOf(body.segment))
    }

    /**
     * Increasing this value will improve the resulting simulation, but can affect performance. Use
     * with care.
     *
     * Generated from Godot docs: SoftBody3D.set_simulation_precision
     */
    fun setSimulationPrecision(simulationPrecision: Int) {
        ObjectCalls.ptrcallWithIntArg(Binds.setSimulationPrecisionBind, segment, simulationPrecision)
    }

    /**
     * Increasing this value will improve the resulting simulation, but can affect performance. Use
     * with care.
     *
     * Generated from Godot docs: SoftBody3D.get_simulation_precision
     */
    fun getSimulationPrecision(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getSimulationPrecisionBind, segment)
    }

    /**
     * The SoftBody3D's mass.
     *
     * Generated from Godot docs: SoftBody3D.set_total_mass
     */
    fun setTotalMass(mass: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setTotalMassBind, segment, mass)
    }

    /**
     * The SoftBody3D's mass.
     *
     * Generated from Godot docs: SoftBody3D.get_total_mass
     */
    fun getTotalMass(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTotalMassBind, segment)
    }

    /**
     * Higher values will result in a stiffer body, while lower values will increase the body's ability
     * to bend. The value can be between `0.0` and `1.0` (inclusive).
     *
     * Generated from Godot docs: SoftBody3D.set_linear_stiffness
     */
    fun setLinearStiffness(linearStiffness: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLinearStiffnessBind, segment, linearStiffness)
    }

    /**
     * Higher values will result in a stiffer body, while lower values will increase the body's ability
     * to bend. The value can be between `0.0` and `1.0` (inclusive).
     *
     * Generated from Godot docs: SoftBody3D.get_linear_stiffness
     */
    fun getLinearStiffness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLinearStiffnessBind, segment)
    }

    /**
     * Scales the rest lengths of `SoftBody3D`'s edge constraints. Positive values shrink the mesh,
     * while negative values expand it. For example, a value of `0.1` shortens the edges of the mesh by
     * 10%, while `-0.1` expands the edges by 10%. Note: `shrinking_factor` is best used on surface
     * meshes with pinned points.
     *
     * Generated from Godot docs: SoftBody3D.set_shrinking_factor
     */
    fun setShrinkingFactor(shrinkingFactor: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setShrinkingFactorBind, segment, shrinkingFactor)
    }

    /**
     * Scales the rest lengths of `SoftBody3D`'s edge constraints. Positive values shrink the mesh,
     * while negative values expand it. For example, a value of `0.1` shortens the edges of the mesh by
     * 10%, while `-0.1` expands the edges by 10%. Note: `shrinking_factor` is best used on surface
     * meshes with pinned points.
     *
     * Generated from Godot docs: SoftBody3D.get_shrinking_factor
     */
    fun getShrinkingFactor(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getShrinkingFactorBind, segment)
    }

    /**
     * The pressure coefficient of this soft body. Simulate pressure build-up from inside this body.
     * Higher values increase the strength of this effect.
     *
     * Generated from Godot docs: SoftBody3D.set_pressure_coefficient
     */
    fun setPressureCoefficient(pressureCoefficient: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setPressureCoefficientBind, segment, pressureCoefficient)
    }

    /**
     * The pressure coefficient of this soft body. Simulate pressure build-up from inside this body.
     * Higher values increase the strength of this effect.
     *
     * Generated from Godot docs: SoftBody3D.get_pressure_coefficient
     */
    fun getPressureCoefficient(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getPressureCoefficientBind, segment)
    }

    /**
     * The body's damping coefficient. Higher values will slow down the body more noticeably when
     * forces are applied.
     *
     * Generated from Godot docs: SoftBody3D.set_damping_coefficient
     */
    fun setDampingCoefficient(dampingCoefficient: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDampingCoefficientBind, segment, dampingCoefficient)
    }

    /**
     * The body's damping coefficient. Higher values will slow down the body more noticeably when
     * forces are applied.
     *
     * Generated from Godot docs: SoftBody3D.get_damping_coefficient
     */
    fun getDampingCoefficient(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDampingCoefficientBind, segment)
    }

    /**
     * The body's drag coefficient. Higher values increase this body's air resistance. Note: This value
     * is currently unused by Godot's default physics implementation.
     *
     * Generated from Godot docs: SoftBody3D.set_drag_coefficient
     */
    fun setDragCoefficient(dragCoefficient: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setDragCoefficientBind, segment, dragCoefficient)
    }

    /**
     * The body's drag coefficient. Higher values increase this body's air resistance. Note: This value
     * is currently unused by Godot's default physics implementation.
     *
     * Generated from Godot docs: SoftBody3D.get_drag_coefficient
     */
    fun getDragCoefficient(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getDragCoefficientBind, segment)
    }

    /**
     * Returns local translation of a vertex in the surface array.
     *
     * Generated from Godot docs: SoftBody3D.get_point_transform
     */
    fun getPointTransform(pointIndex: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(Binds.getPointTransformBind, segment, pointIndex)
    }

    /**
     * Applies an impulse to a point. An impulse is time-independent! Applying an impulse every frame
     * would result in a framerate-dependent force. For this reason, it should only be used when
     * simulating one-time impacts (use the "_force" functions otherwise).
     *
     * Generated from Godot docs: SoftBody3D.apply_impulse
     */
    fun applyImpulse(pointIndex: Int, impulse: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(Binds.applyImpulseBind, segment, pointIndex, impulse)
    }

    /**
     * Applies a force to a point. A force is time dependent and meant to be applied every physics
     * update.
     *
     * Generated from Godot docs: SoftBody3D.apply_force
     */
    fun applyForce(pointIndex: Int, force: Vector3) {
        ObjectCalls.ptrcallWithIntAndVector3Arg(Binds.applyForceBind, segment, pointIndex, force)
    }

    /**
     * Distributes and applies an impulse to all points. An impulse is time-independent! Applying an
     * impulse every frame would result in a framerate-dependent force. For this reason, it should only
     * be used when simulating one-time impacts (use the "_force" functions otherwise).
     *
     * Generated from Godot docs: SoftBody3D.apply_central_impulse
     */
    fun applyCentralImpulse(impulse: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.applyCentralImpulseBind, segment, impulse)
    }

    /**
     * Distributes and applies a force to all points. A force is time dependent and meant to be applied
     * every physics update.
     *
     * Generated from Godot docs: SoftBody3D.apply_central_force
     */
    fun applyCentralForce(force: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.applyCentralForceBind, segment, force)
    }

    /**
     * Sets the pinned state of a surface vertex. When set to `true`, the optional `attachment_path`
     * can define a `Node3D` the pinned vertex will be attached to.
     *
     * Generated from Godot docs: SoftBody3D.set_point_pinned
     */
    fun setPointPinned(pointIndex: Int, pinned: Boolean, attachmentPath: NodePath, insertAt: Int = -1) {
        ObjectCalls.ptrcallWithIntBoolNodePathIntArgs(Binds.setPointPinnedBind, segment, pointIndex, pinned, attachmentPath, insertAt)
    }

    /**
     * Returns `true` if vertex is set to pinned.
     *
     * Generated from Godot docs: SoftBody3D.is_point_pinned
     */
    fun isPointPinned(pointIndex: Int): Boolean {
        return ObjectCalls.ptrcallWithIntArgRetBool(Binds.isPointPinnedBind, segment, pointIndex)
    }

    /**
     * If `true`, the `SoftBody3D` will respond to `RayCast3D`s.
     *
     * Generated from Godot docs: SoftBody3D.set_ray_pickable
     */
    fun setRayPickable(rayPickable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setRayPickableBind, segment, rayPickable)
    }

    /**
     * If `true`, the `SoftBody3D` will respond to `RayCast3D`s.
     *
     * Generated from Godot docs: SoftBody3D.is_ray_pickable
     */
    fun isRayPickable(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isRayPickableBind, segment)
    }

    /**
     * Godot's `SoftBody3D.DisableMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`SoftBody3D.DisableMode.<NAME>`).
     *
     * Generated from Godot docs: SoftBody3D.DisableMode
     */
    @JvmInline
    value class DisableMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * When `Node.process_mode` is set to `Node.ProcessMode.DISABLED`, remove from the physics
             * simulation to stop all physics interactions with this `SoftBody3D`. Automatically re-added to
             * the physics simulation when the `Node` is processed again.
             *
             * Generated from Godot docs: SoftBody3D.DISABLE_MODE_REMOVE
             */
            val REMOVE: DisableMode get() = DisableMode(0L)
            /**
             * When `Node.process_mode` is set to `Node.ProcessMode.DISABLED`, do not affect the physics
             * simulation.
             *
             * Generated from Godot docs: SoftBody3D.DISABLE_MODE_KEEP_ACTIVE
             */
            val KEEP_ACTIVE: DisableMode get() = DisableMode(1L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): SoftBody3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): SoftBody3D? =
            if (handle.address() == 0L) null else SoftBody3D(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_PHYSICS_RID_HASH = 2944877500L
        @JvmField
        val getPhysicsRidBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_physics_rid", GET_PHYSICS_RID_HASH)

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        @JvmField
        val setCollisionMaskBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_collision_mask", SET_COLLISION_MASK_HASH)

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        @JvmField
        val getCollisionMaskBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_collision_mask", GET_COLLISION_MASK_HASH)

        private const val SET_COLLISION_LAYER_HASH = 1286410249L
        @JvmField
        val setCollisionLayerBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_collision_layer", SET_COLLISION_LAYER_HASH)

        private const val GET_COLLISION_LAYER_HASH = 3905245786L
        @JvmField
        val getCollisionLayerBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_collision_layer", GET_COLLISION_LAYER_HASH)

        private const val SET_COLLISION_MASK_VALUE_HASH = 300928843L
        @JvmField
        val setCollisionMaskValueBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_collision_mask_value", SET_COLLISION_MASK_VALUE_HASH)

        private const val GET_COLLISION_MASK_VALUE_HASH = 1116898809L
        @JvmField
        val getCollisionMaskValueBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_collision_mask_value", GET_COLLISION_MASK_VALUE_HASH)

        private const val SET_COLLISION_LAYER_VALUE_HASH = 300928843L
        @JvmField
        val setCollisionLayerValueBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_collision_layer_value", SET_COLLISION_LAYER_VALUE_HASH)

        private const val GET_COLLISION_LAYER_VALUE_HASH = 1116898809L
        @JvmField
        val getCollisionLayerValueBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_collision_layer_value", GET_COLLISION_LAYER_VALUE_HASH)

        private const val SET_PARENT_COLLISION_IGNORE_HASH = 1348162250L
        @JvmField
        val setParentCollisionIgnoreBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_parent_collision_ignore", SET_PARENT_COLLISION_IGNORE_HASH)

        private const val GET_PARENT_COLLISION_IGNORE_HASH = 4075236667L
        @JvmField
        val getParentCollisionIgnoreBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_parent_collision_ignore", GET_PARENT_COLLISION_IGNORE_HASH)

        private const val SET_DISABLE_MODE_HASH = 1104158384L
        @JvmField
        val setDisableModeBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_disable_mode", SET_DISABLE_MODE_HASH)

        private const val GET_DISABLE_MODE_HASH = 4135042476L
        @JvmField
        val getDisableModeBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_disable_mode", GET_DISABLE_MODE_HASH)

        private const val GET_COLLISION_EXCEPTIONS_HASH = 2915620761L
        @JvmField
        val getCollisionExceptionsBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_collision_exceptions", GET_COLLISION_EXCEPTIONS_HASH)

        private const val ADD_COLLISION_EXCEPTION_WITH_HASH = 1078189570L
        @JvmField
        val addCollisionExceptionWithBind =
            ObjectCalls.getMethodBind("SoftBody3D", "add_collision_exception_with", ADD_COLLISION_EXCEPTION_WITH_HASH)

        private const val REMOVE_COLLISION_EXCEPTION_WITH_HASH = 1078189570L
        @JvmField
        val removeCollisionExceptionWithBind =
            ObjectCalls.getMethodBind("SoftBody3D", "remove_collision_exception_with", REMOVE_COLLISION_EXCEPTION_WITH_HASH)

        private const val SET_SIMULATION_PRECISION_HASH = 1286410249L
        @JvmField
        val setSimulationPrecisionBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_simulation_precision", SET_SIMULATION_PRECISION_HASH)

        private const val GET_SIMULATION_PRECISION_HASH = 2455072627L
        @JvmField
        val getSimulationPrecisionBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_simulation_precision", GET_SIMULATION_PRECISION_HASH)

        private const val SET_TOTAL_MASS_HASH = 373806689L
        @JvmField
        val setTotalMassBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_total_mass", SET_TOTAL_MASS_HASH)

        private const val GET_TOTAL_MASS_HASH = 191475506L
        @JvmField
        val getTotalMassBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_total_mass", GET_TOTAL_MASS_HASH)

        private const val SET_LINEAR_STIFFNESS_HASH = 373806689L
        @JvmField
        val setLinearStiffnessBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_linear_stiffness", SET_LINEAR_STIFFNESS_HASH)

        private const val GET_LINEAR_STIFFNESS_HASH = 191475506L
        @JvmField
        val getLinearStiffnessBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_linear_stiffness", GET_LINEAR_STIFFNESS_HASH)

        private const val SET_SHRINKING_FACTOR_HASH = 373806689L
        @JvmField
        val setShrinkingFactorBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_shrinking_factor", SET_SHRINKING_FACTOR_HASH)

        private const val GET_SHRINKING_FACTOR_HASH = 191475506L
        @JvmField
        val getShrinkingFactorBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_shrinking_factor", GET_SHRINKING_FACTOR_HASH)

        private const val SET_PRESSURE_COEFFICIENT_HASH = 373806689L
        @JvmField
        val setPressureCoefficientBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_pressure_coefficient", SET_PRESSURE_COEFFICIENT_HASH)

        private const val GET_PRESSURE_COEFFICIENT_HASH = 191475506L
        @JvmField
        val getPressureCoefficientBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_pressure_coefficient", GET_PRESSURE_COEFFICIENT_HASH)

        private const val SET_DAMPING_COEFFICIENT_HASH = 373806689L
        @JvmField
        val setDampingCoefficientBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_damping_coefficient", SET_DAMPING_COEFFICIENT_HASH)

        private const val GET_DAMPING_COEFFICIENT_HASH = 191475506L
        @JvmField
        val getDampingCoefficientBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_damping_coefficient", GET_DAMPING_COEFFICIENT_HASH)

        private const val SET_DRAG_COEFFICIENT_HASH = 373806689L
        @JvmField
        val setDragCoefficientBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_drag_coefficient", SET_DRAG_COEFFICIENT_HASH)

        private const val GET_DRAG_COEFFICIENT_HASH = 191475506L
        @JvmField
        val getDragCoefficientBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_drag_coefficient", GET_DRAG_COEFFICIENT_HASH)

        private const val GET_POINT_TRANSFORM_HASH = 871989493L
        @JvmField
        val getPointTransformBind =
            ObjectCalls.getMethodBind("SoftBody3D", "get_point_transform", GET_POINT_TRANSFORM_HASH)

        private const val APPLY_IMPULSE_HASH = 1530502735L
        @JvmField
        val applyImpulseBind =
            ObjectCalls.getMethodBind("SoftBody3D", "apply_impulse", APPLY_IMPULSE_HASH)

        private const val APPLY_FORCE_HASH = 1530502735L
        @JvmField
        val applyForceBind =
            ObjectCalls.getMethodBind("SoftBody3D", "apply_force", APPLY_FORCE_HASH)

        private const val APPLY_CENTRAL_IMPULSE_HASH = 3460891852L
        @JvmField
        val applyCentralImpulseBind =
            ObjectCalls.getMethodBind("SoftBody3D", "apply_central_impulse", APPLY_CENTRAL_IMPULSE_HASH)

        private const val APPLY_CENTRAL_FORCE_HASH = 3460891852L
        @JvmField
        val applyCentralForceBind =
            ObjectCalls.getMethodBind("SoftBody3D", "apply_central_force", APPLY_CENTRAL_FORCE_HASH)

        private const val SET_POINT_PINNED_HASH = 528784402L
        @JvmField
        val setPointPinnedBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_point_pinned", SET_POINT_PINNED_HASH)

        private const val IS_POINT_PINNED_HASH = 1116898809L
        @JvmField
        val isPointPinnedBind =
            ObjectCalls.getMethodBind("SoftBody3D", "is_point_pinned", IS_POINT_PINNED_HASH)

        private const val SET_RAY_PICKABLE_HASH = 2586408642L
        @JvmField
        val setRayPickableBind =
            ObjectCalls.getMethodBind("SoftBody3D", "set_ray_pickable", SET_RAY_PICKABLE_HASH)

        private const val IS_RAY_PICKABLE_HASH = 36873697L
        @JvmField
        val isRayPickableBind =
            ObjectCalls.getMethodBind("SoftBody3D", "is_ray_pickable", IS_RAY_PICKABLE_HASH)
    }
}
