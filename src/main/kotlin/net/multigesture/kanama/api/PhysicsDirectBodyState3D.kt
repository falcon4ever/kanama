package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Basis
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * Provides direct access to a physics body in the `PhysicsServer3D`.
 *
 * Generated from Godot docs: PhysicsDirectBodyState3D
 */
open class PhysicsDirectBodyState3D(handle: MemorySegment) : GodotObject(handle) {
    val step: Double
        @JvmName("stepProperty")
        get() = getStep()

    val inverseMass: Double
        @JvmName("inverseMassProperty")
        get() = getInverseMass()

    val totalAngularDamp: Double
        @JvmName("totalAngularDampProperty")
        get() = getTotalAngularDamp()

    val totalLinearDamp: Double
        @JvmName("totalLinearDampProperty")
        get() = getTotalLinearDamp()

    val inverseInertia: Vector3
        @JvmName("inverseInertiaProperty")
        get() = getInverseInertia()

    val inverseInertiaTensor: Basis
        @JvmName("inverseInertiaTensorProperty")
        get() = getInverseInertiaTensor()

    val totalGravity: Vector3
        @JvmName("totalGravityProperty")
        get() = getTotalGravity()

    val centerOfMass: Vector3
        @JvmName("centerOfMassProperty")
        get() = getCenterOfMass()

    val centerOfMassLocal: Vector3
        @JvmName("centerOfMassLocalProperty")
        get() = getCenterOfMassLocal()

    val principalInertiaAxes: Basis
        @JvmName("principalInertiaAxesProperty")
        get() = getPrincipalInertiaAxes()

    var angularVelocity: Vector3
        @JvmName("angularVelocityProperty")
        get() = getAngularVelocity()
        @JvmName("setAngularVelocityProperty")
        set(value) = setAngularVelocity(value)

    var linearVelocity: Vector3
        @JvmName("linearVelocityProperty")
        get() = getLinearVelocity()
        @JvmName("setLinearVelocityProperty")
        set(value) = setLinearVelocity(value)

    var sleeping: Boolean
        @JvmName("sleepingProperty")
        get() = isSleeping()
        @JvmName("setSleepingProperty")
        set(value) = setSleepState(value)

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

    var transform: Transform3D
        @JvmName("transformProperty")
        get() = getTransform()
        @JvmName("setTransformProperty")
        set(value) = setTransform(value)

    /**
     * The total gravity vector being currently applied to this body.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_total_gravity
     */
    fun getTotalGravity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getTotalGravityBind, handle)
    }

    /**
     * The rate at which the body stops moving, if there are not any other forces moving it.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_total_linear_damp
     */
    fun getTotalLinearDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTotalLinearDampBind, handle)
    }

    /**
     * The rate at which the body stops rotating, if there are not any other forces moving it.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_total_angular_damp
     */
    fun getTotalAngularDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTotalAngularDampBind, handle)
    }

    /**
     * The body's center of mass position relative to the body's center in the global coordinate
     * system.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_center_of_mass
     */
    fun getCenterOfMass(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getCenterOfMassBind, handle)
    }

    /**
     * The body's center of mass position in the body's local coordinate system.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_center_of_mass_local
     */
    fun getCenterOfMassLocal(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getCenterOfMassLocalBind, handle)
    }

    fun getPrincipalInertiaAxes(): Basis {
        return ObjectCalls.ptrcallNoArgsRetBasis(getPrincipalInertiaAxesBind, handle)
    }

    /**
     * The inverse of the mass of the body.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_inverse_mass
     */
    fun getInverseMass(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInverseMassBind, handle)
    }

    /**
     * The inverse of the inertia of the body.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_inverse_inertia
     */
    fun getInverseInertia(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getInverseInertiaBind, handle)
    }

    /**
     * The inverse of the inertia tensor of the body.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_inverse_inertia_tensor
     */
    fun getInverseInertiaTensor(): Basis {
        return ObjectCalls.ptrcallNoArgsRetBasis(getInverseInertiaTensorBind, handle)
    }

    /**
     * The body's linear velocity in units per second.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.set_linear_velocity
     */
    fun setLinearVelocity(velocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setLinearVelocityBind, handle, velocity)
    }

    /**
     * The body's linear velocity in units per second.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_linear_velocity
     */
    fun getLinearVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getLinearVelocityBind, handle)
    }

    /**
     * The body's rotational velocity in radians per second.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.set_angular_velocity
     */
    fun setAngularVelocity(velocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setAngularVelocityBind, handle, velocity)
    }

    /**
     * The body's rotational velocity in radians per second.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_angular_velocity
     */
    fun getAngularVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getAngularVelocityBind, handle)
    }

    /**
     * The body's transformation matrix.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.set_transform
     */
    fun setTransform(transform: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setTransformBind, handle, transform)
    }

    /**
     * The body's transformation matrix.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_transform
     */
    fun getTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getTransformBind, handle)
    }

    /**
     * Returns the body's velocity at the given relative position. `local_position` is the offset from
     * the body origin in global coordinates.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_velocity_at_local_position
     */
    fun getVelocityAtLocalPosition(localPosition: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithVector3ArgRetVector3(getVelocityAtLocalPositionBind, handle, localPosition)
    }

    /**
     * Applies a directional impulse without affecting rotation. An impulse is time-independent!
     * Applying an impulse every frame would result in a framerate-dependent force. For this reason, it
     * should only be used when simulating one-time impacts (use the "_force" functions otherwise).
     * This is equivalent to using `apply_impulse` at the body's center of mass.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.apply_central_impulse
     */
    fun applyCentralImpulse(impulse: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(applyCentralImpulseBind, handle, impulse)
    }

    /**
     * Applies a positioned impulse to the body. An impulse is time-independent! Applying an impulse
     * every frame would result in a framerate-dependent force. For this reason, it should only be used
     * when simulating one-time impacts (use the "_force" functions otherwise). `position` is the
     * offset from the body origin in global coordinates.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.apply_impulse
     */
    fun applyImpulse(impulse: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithTwoVector3Args(applyImpulseBind, handle, impulse, position)
    }

    /**
     * Applies a rotational impulse to the body without affecting the position. An impulse is
     * time-independent! Applying an impulse every frame would result in a framerate-dependent force.
     * For this reason, it should only be used when simulating one-time impacts (use the "_force"
     * functions otherwise). Note: `inverse_inertia` is required for this to work. To have
     * `inverse_inertia`, an active `CollisionShape3D` must be a child of the node, or you can manually
     * set `inverse_inertia`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.apply_torque_impulse
     */
    fun applyTorqueImpulse(impulse: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(applyTorqueImpulseBind, handle, impulse)
    }

    /**
     * Applies a directional force without affecting rotation. A force is time dependent and meant to
     * be applied every physics update. This is equivalent to using `apply_force` at the body's center
     * of mass.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.apply_central_force
     */
    fun applyCentralForce(force: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(applyCentralForceBind, handle, force)
    }

    /**
     * Applies a positioned force to the body. A force is time dependent and meant to be applied every
     * physics update. `position` is the offset from the body origin in global coordinates.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.apply_force
     */
    fun applyForce(force: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithTwoVector3Args(applyForceBind, handle, force, position)
    }

    /**
     * Applies a rotational force without affecting position. A force is time dependent and meant to be
     * applied every physics update. Note: `inverse_inertia` is required for this to work. To have
     * `inverse_inertia`, an active `CollisionShape3D` must be a child of the node, or you can manually
     * set `inverse_inertia`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.apply_torque
     */
    fun applyTorque(torque: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(applyTorqueBind, handle, torque)
    }

    /**
     * Adds a constant directional force without affecting rotation that keeps being applied over time
     * until cleared with `constant_force = Vector3(0, 0, 0)`. This is equivalent to using
     * `add_constant_force` at the body's center of mass.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.add_constant_central_force
     */
    fun addConstantCentralForce(force: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(addConstantCentralForceBind, handle, force)
    }

    /**
     * Adds a constant positioned force to the body that keeps being applied over time until cleared
     * with `constant_force = Vector3(0, 0, 0)`. `position` is the offset from the body origin in
     * global coordinates.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.add_constant_force
     */
    fun addConstantForce(force: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithTwoVector3Args(addConstantForceBind, handle, force, position)
    }

    /**
     * Adds a constant rotational force without affecting position that keeps being applied over time
     * until cleared with `constant_torque = Vector3(0, 0, 0)`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.add_constant_torque
     */
    fun addConstantTorque(torque: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(addConstantTorqueBind, handle, torque)
    }

    /**
     * Sets the body's total constant positional forces applied during each physics update. See
     * `add_constant_force` and `add_constant_central_force`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.set_constant_force
     */
    fun setConstantForce(force: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setConstantForceBind, handle, force)
    }

    /**
     * Returns the body's total constant positional forces applied during each physics update. See
     * `add_constant_force` and `add_constant_central_force`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_constant_force
     */
    fun getConstantForce(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getConstantForceBind, handle)
    }

    /**
     * Sets the body's total constant rotational forces applied during each physics update. See
     * `add_constant_torque`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.set_constant_torque
     */
    fun setConstantTorque(torque: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setConstantTorqueBind, handle, torque)
    }

    /**
     * Returns the body's total constant rotational forces applied during each physics update. See
     * `add_constant_torque`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_constant_torque
     */
    fun getConstantTorque(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getConstantTorqueBind, handle)
    }

    /**
     * If `true`, this body is currently sleeping (not active).
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.set_sleep_state
     */
    fun setSleepState(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSleepStateBind, handle, enabled)
    }

    /**
     * If `true`, this body is currently sleeping (not active).
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.is_sleeping
     */
    fun isSleeping(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSleepingBind, handle)
    }

    /**
     * The body's collision layer.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.set_collision_layer
     */
    fun setCollisionLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionLayerBind, handle, layer)
    }

    /**
     * The body's collision layer.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_collision_layer
     */
    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionLayerBind, handle)
    }

    /**
     * The body's collision mask.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.set_collision_mask
     */
    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, mask)
    }

    /**
     * The body's collision mask.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_collision_mask
     */
    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    /**
     * Returns the number of contacts this body has with other bodies. Note: By default, this returns 0
     * unless bodies are configured to monitor contacts. See `RigidBody3D.contact_monitor`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_contact_count
     */
    fun getContactCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getContactCountBind, handle)
    }

    /**
     * Returns the position of the contact point on the body in the global coordinate system.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_contact_local_position
     */
    fun getContactLocalPosition(contactIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getContactLocalPositionBind, handle, contactIdx)
    }

    /**
     * Returns the local normal at the contact point.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_contact_local_normal
     */
    fun getContactLocalNormal(contactIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getContactLocalNormalBind, handle, contactIdx)
    }

    /**
     * Impulse created by the contact.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_contact_impulse
     */
    fun getContactImpulse(contactIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getContactImpulseBind, handle, contactIdx)
    }

    /**
     * Returns the local shape index of the collision.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_contact_local_shape
     */
    fun getContactLocalShape(contactIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getContactLocalShapeBind, handle, contactIdx)
    }

    /**
     * Returns the linear velocity vector at the body's contact point.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_contact_local_velocity_at_position
     */
    fun getContactLocalVelocityAtPosition(contactIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getContactLocalVelocityAtPositionBind, handle, contactIdx)
    }

    /**
     * Returns the collider's `RID`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_contact_collider
     */
    fun getContactCollider(contactIdx: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(getContactColliderBind, handle, contactIdx)
    }

    /**
     * Returns the position of the contact point on the collider in the global coordinate system.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_contact_collider_position
     */
    fun getContactColliderPosition(contactIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getContactColliderPositionBind, handle, contactIdx)
    }

    /**
     * Returns the collider's object id.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_contact_collider_id
     */
    fun getContactColliderId(contactIdx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getContactColliderIdBind, handle, contactIdx)
    }

    /**
     * Returns the collider object.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_contact_collider_object
     */
    fun getContactColliderObject(contactIdx: Int): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getContactColliderObjectBind, handle, contactIdx))
    }

    /**
     * Returns the collider's shape index.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_contact_collider_shape
     */
    fun getContactColliderShape(contactIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getContactColliderShapeBind, handle, contactIdx)
    }

    /**
     * Returns the linear velocity vector at the collider's contact point.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_contact_collider_velocity_at_position
     */
    fun getContactColliderVelocityAtPosition(contactIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getContactColliderVelocityAtPositionBind, handle, contactIdx)
    }

    /**
     * The timestep (delta) used for the simulation.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_step
     */
    fun getStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStepBind, handle)
    }

    /**
     * Updates the body's linear and angular velocity by applying gravity and damping for the
     * equivalent of one physics tick.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.integrate_forces
     */
    fun integrateForces() {
        ObjectCalls.ptrcallNoArgs(integrateForcesBind, handle)
    }

    /**
     * Returns the current state of the space, useful for queries.
     *
     * Generated from Godot docs: PhysicsDirectBodyState3D.get_space_state
     */
    fun getSpaceState(): PhysicsDirectSpaceState3D? {
        return PhysicsDirectSpaceState3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getSpaceStateBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsDirectBodyState3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsDirectBodyState3D? =
            if (handle.address() == 0L) null else PhysicsDirectBodyState3D(handle)

        private const val GET_TOTAL_GRAVITY_HASH = 3360562783L
        private val getTotalGravityBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_total_gravity", GET_TOTAL_GRAVITY_HASH)
        }

        private const val GET_TOTAL_LINEAR_DAMP_HASH = 1740695150L
        private val getTotalLinearDampBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_total_linear_damp", GET_TOTAL_LINEAR_DAMP_HASH)
        }

        private const val GET_TOTAL_ANGULAR_DAMP_HASH = 1740695150L
        private val getTotalAngularDampBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_total_angular_damp", GET_TOTAL_ANGULAR_DAMP_HASH)
        }

        private const val GET_CENTER_OF_MASS_HASH = 3360562783L
        private val getCenterOfMassBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_center_of_mass", GET_CENTER_OF_MASS_HASH)
        }

        private const val GET_CENTER_OF_MASS_LOCAL_HASH = 3360562783L
        private val getCenterOfMassLocalBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_center_of_mass_local", GET_CENTER_OF_MASS_LOCAL_HASH)
        }

        private const val GET_PRINCIPAL_INERTIA_AXES_HASH = 2716978435L
        private val getPrincipalInertiaAxesBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_principal_inertia_axes", GET_PRINCIPAL_INERTIA_AXES_HASH)
        }

        private const val GET_INVERSE_MASS_HASH = 1740695150L
        private val getInverseMassBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_inverse_mass", GET_INVERSE_MASS_HASH)
        }

        private const val GET_INVERSE_INERTIA_HASH = 3360562783L
        private val getInverseInertiaBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_inverse_inertia", GET_INVERSE_INERTIA_HASH)
        }

        private const val GET_INVERSE_INERTIA_TENSOR_HASH = 2716978435L
        private val getInverseInertiaTensorBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_inverse_inertia_tensor", GET_INVERSE_INERTIA_TENSOR_HASH)
        }

        private const val SET_LINEAR_VELOCITY_HASH = 3460891852L
        private val setLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "set_linear_velocity", SET_LINEAR_VELOCITY_HASH)
        }

        private const val GET_LINEAR_VELOCITY_HASH = 3360562783L
        private val getLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_linear_velocity", GET_LINEAR_VELOCITY_HASH)
        }

        private const val SET_ANGULAR_VELOCITY_HASH = 3460891852L
        private val setAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "set_angular_velocity", SET_ANGULAR_VELOCITY_HASH)
        }

        private const val GET_ANGULAR_VELOCITY_HASH = 3360562783L
        private val getAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_angular_velocity", GET_ANGULAR_VELOCITY_HASH)
        }

        private const val SET_TRANSFORM_HASH = 2952846383L
        private val setTransformBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "set_transform", SET_TRANSFORM_HASH)
        }

        private const val GET_TRANSFORM_HASH = 3229777777L
        private val getTransformBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_transform", GET_TRANSFORM_HASH)
        }

        private const val GET_VELOCITY_AT_LOCAL_POSITION_HASH = 192990374L
        private val getVelocityAtLocalPositionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_velocity_at_local_position", GET_VELOCITY_AT_LOCAL_POSITION_HASH)
        }

        private const val APPLY_CENTRAL_IMPULSE_HASH = 2007698547L
        private val applyCentralImpulseBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "apply_central_impulse", APPLY_CENTRAL_IMPULSE_HASH)
        }

        private const val APPLY_IMPULSE_HASH = 2754756483L
        private val applyImpulseBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "apply_impulse", APPLY_IMPULSE_HASH)
        }

        private const val APPLY_TORQUE_IMPULSE_HASH = 3460891852L
        private val applyTorqueImpulseBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "apply_torque_impulse", APPLY_TORQUE_IMPULSE_HASH)
        }

        private const val APPLY_CENTRAL_FORCE_HASH = 2007698547L
        private val applyCentralForceBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "apply_central_force", APPLY_CENTRAL_FORCE_HASH)
        }

        private const val APPLY_FORCE_HASH = 2754756483L
        private val applyForceBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "apply_force", APPLY_FORCE_HASH)
        }

        private const val APPLY_TORQUE_HASH = 3460891852L
        private val applyTorqueBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "apply_torque", APPLY_TORQUE_HASH)
        }

        private const val ADD_CONSTANT_CENTRAL_FORCE_HASH = 2007698547L
        private val addConstantCentralForceBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "add_constant_central_force", ADD_CONSTANT_CENTRAL_FORCE_HASH)
        }

        private const val ADD_CONSTANT_FORCE_HASH = 2754756483L
        private val addConstantForceBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "add_constant_force", ADD_CONSTANT_FORCE_HASH)
        }

        private const val ADD_CONSTANT_TORQUE_HASH = 3460891852L
        private val addConstantTorqueBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "add_constant_torque", ADD_CONSTANT_TORQUE_HASH)
        }

        private const val SET_CONSTANT_FORCE_HASH = 3460891852L
        private val setConstantForceBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "set_constant_force", SET_CONSTANT_FORCE_HASH)
        }

        private const val GET_CONSTANT_FORCE_HASH = 3360562783L
        private val getConstantForceBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_constant_force", GET_CONSTANT_FORCE_HASH)
        }

        private const val SET_CONSTANT_TORQUE_HASH = 3460891852L
        private val setConstantTorqueBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "set_constant_torque", SET_CONSTANT_TORQUE_HASH)
        }

        private const val GET_CONSTANT_TORQUE_HASH = 3360562783L
        private val getConstantTorqueBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_constant_torque", GET_CONSTANT_TORQUE_HASH)
        }

        private const val SET_SLEEP_STATE_HASH = 2586408642L
        private val setSleepStateBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "set_sleep_state", SET_SLEEP_STATE_HASH)
        }

        private const val IS_SLEEPING_HASH = 36873697L
        private val isSleepingBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "is_sleeping", IS_SLEEPING_HASH)
        }

        private const val SET_COLLISION_LAYER_HASH = 1286410249L
        private val setCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "set_collision_layer", SET_COLLISION_LAYER_HASH)
        }

        private const val GET_COLLISION_LAYER_HASH = 3905245786L
        private val getCollisionLayerBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_collision_layer", GET_COLLISION_LAYER_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val GET_CONTACT_COUNT_HASH = 3905245786L
        private val getContactCountBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_contact_count", GET_CONTACT_COUNT_HASH)
        }

        private const val GET_CONTACT_LOCAL_POSITION_HASH = 711720468L
        private val getContactLocalPositionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_contact_local_position", GET_CONTACT_LOCAL_POSITION_HASH)
        }

        private const val GET_CONTACT_LOCAL_NORMAL_HASH = 711720468L
        private val getContactLocalNormalBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_contact_local_normal", GET_CONTACT_LOCAL_NORMAL_HASH)
        }

        private const val GET_CONTACT_IMPULSE_HASH = 711720468L
        private val getContactImpulseBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_contact_impulse", GET_CONTACT_IMPULSE_HASH)
        }

        private const val GET_CONTACT_LOCAL_SHAPE_HASH = 923996154L
        private val getContactLocalShapeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_contact_local_shape", GET_CONTACT_LOCAL_SHAPE_HASH)
        }

        private const val GET_CONTACT_LOCAL_VELOCITY_AT_POSITION_HASH = 711720468L
        private val getContactLocalVelocityAtPositionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_contact_local_velocity_at_position", GET_CONTACT_LOCAL_VELOCITY_AT_POSITION_HASH)
        }

        private const val GET_CONTACT_COLLIDER_HASH = 495598643L
        private val getContactColliderBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_contact_collider", GET_CONTACT_COLLIDER_HASH)
        }

        private const val GET_CONTACT_COLLIDER_POSITION_HASH = 711720468L
        private val getContactColliderPositionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_contact_collider_position", GET_CONTACT_COLLIDER_POSITION_HASH)
        }

        private const val GET_CONTACT_COLLIDER_ID_HASH = 923996154L
        private val getContactColliderIdBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_contact_collider_id", GET_CONTACT_COLLIDER_ID_HASH)
        }

        private const val GET_CONTACT_COLLIDER_OBJECT_HASH = 3332903315L
        private val getContactColliderObjectBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_contact_collider_object", GET_CONTACT_COLLIDER_OBJECT_HASH)
        }

        private const val GET_CONTACT_COLLIDER_SHAPE_HASH = 923996154L
        private val getContactColliderShapeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_contact_collider_shape", GET_CONTACT_COLLIDER_SHAPE_HASH)
        }

        private const val GET_CONTACT_COLLIDER_VELOCITY_AT_POSITION_HASH = 711720468L
        private val getContactColliderVelocityAtPositionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_contact_collider_velocity_at_position", GET_CONTACT_COLLIDER_VELOCITY_AT_POSITION_HASH)
        }

        private const val GET_STEP_HASH = 1740695150L
        private val getStepBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_step", GET_STEP_HASH)
        }

        private const val INTEGRATE_FORCES_HASH = 3218959716L
        private val integrateForcesBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "integrate_forces", INTEGRATE_FORCES_HASH)
        }

        private const val GET_SPACE_STATE_HASH = 2069328350L
        private val getSpaceStateBind by lazy {
            ObjectCalls.getMethodBind("PhysicsDirectBodyState3D", "get_space_state", GET_SPACE_STATE_HASH)
        }
    }
}
