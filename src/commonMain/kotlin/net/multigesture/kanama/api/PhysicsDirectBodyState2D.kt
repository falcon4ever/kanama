package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.requireGodotReturn
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Transform2D
import net.multigesture.kanama.types.Vector2

/**
 * Provides direct access to a physics body in the `PhysicsServer2D`.
 *
 * Generated from Godot docs: PhysicsDirectBodyState2D
 */
open class PhysicsDirectBodyState2D(handle: GodotHandle) : GodotObject(handle) {
    val step: Double
        @JvmName("stepProperty")
        get() = getStep()

    val inverseMass: Double
        @JvmName("inverseMassProperty")
        get() = getInverseMass()

    val inverseInertia: Double
        @JvmName("inverseInertiaProperty")
        get() = getInverseInertia()

    val totalAngularDamp: Double
        @JvmName("totalAngularDampProperty")
        get() = getTotalAngularDamp()

    val totalLinearDamp: Double
        @JvmName("totalLinearDampProperty")
        get() = getTotalLinearDamp()

    val totalGravity: Vector2
        @JvmName("totalGravityProperty")
        get() = getTotalGravity()

    val centerOfMass: Vector2
        @JvmName("centerOfMassProperty")
        get() = getCenterOfMass()

    val centerOfMassLocal: Vector2
        @JvmName("centerOfMassLocalProperty")
        get() = getCenterOfMassLocal()

    var angularVelocity: Double
        @JvmName("angularVelocityProperty")
        get() = getAngularVelocity()
        @JvmName("setAngularVelocityProperty")
        set(value) = setAngularVelocity(value)

    var linearVelocity: Vector2
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

    var transform: Transform2D
        @JvmName("transformProperty")
        get() = getTransform()
        @JvmName("setTransformProperty")
        set(value) = setTransform(value)

    /**
     * The total gravity vector being currently applied to this body.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_total_gravity
     */
    fun getTotalGravity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getTotalGravityBind, segment)
    }

    /**
     * The rate at which the body stops moving, if there are not any other forces moving it.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_total_linear_damp
     */
    fun getTotalLinearDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTotalLinearDampBind, segment)
    }

    /**
     * The rate at which the body stops rotating, if there are not any other forces moving it.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_total_angular_damp
     */
    fun getTotalAngularDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getTotalAngularDampBind, segment)
    }

    /**
     * The body's center of mass position relative to the body's center in the global coordinate
     * system.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_center_of_mass
     */
    fun getCenterOfMass(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getCenterOfMassBind, segment)
    }

    /**
     * The body's center of mass position in the body's local coordinate system.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_center_of_mass_local
     */
    fun getCenterOfMassLocal(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getCenterOfMassLocalBind, segment)
    }

    /**
     * The inverse of the mass of the body.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_inverse_mass
     */
    fun getInverseMass(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getInverseMassBind, segment)
    }

    /**
     * The inverse of the inertia of the body.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_inverse_inertia
     */
    fun getInverseInertia(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getInverseInertiaBind, segment)
    }

    /**
     * The body's linear velocity in pixels per second.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.set_linear_velocity
     */
    fun setLinearVelocity(velocity: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setLinearVelocityBind, segment, velocity)
    }

    /**
     * The body's linear velocity in pixels per second.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_linear_velocity
     */
    fun getLinearVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getLinearVelocityBind, segment)
    }

    /**
     * The body's rotational velocity in radians per second.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.set_angular_velocity
     */
    fun setAngularVelocity(velocity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAngularVelocityBind, segment, velocity)
    }

    /**
     * The body's rotational velocity in radians per second.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_angular_velocity
     */
    fun getAngularVelocity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAngularVelocityBind, segment)
    }

    /**
     * The body's transformation matrix.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.set_transform
     */
    fun setTransform(transform: Transform2D) {
        ObjectCalls.ptrcallWithTransform2DArg(Binds.setTransformBind, segment, transform)
    }

    /**
     * The body's transformation matrix.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_transform
     */
    fun getTransform(): Transform2D {
        return ObjectCalls.ptrcallNoArgsRetTransform2D(Binds.getTransformBind, segment)
    }

    /**
     * Returns the body's velocity at the given relative position. `local_position` is the offset from
     * the body origin in global coordinates.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_velocity_at_local_position
     */
    fun getVelocityAtLocalPosition(localPosition: Vector2): Vector2 {
        return ObjectCalls.ptrcallWithVector2ArgRetVector2(Binds.getVelocityAtLocalPositionBind, segment, localPosition)
    }

    /**
     * Applies a directional impulse without affecting rotation. An impulse is time-independent!
     * Applying an impulse every frame would result in a framerate-dependent force. For this reason, it
     * should only be used when simulating one-time impacts (use the "_force" functions otherwise).
     * This is equivalent to using `apply_impulse` at the body's center of mass.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.apply_central_impulse
     */
    fun applyCentralImpulse(impulse: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.applyCentralImpulseBind, segment, impulse)
    }

    /**
     * Applies a rotational impulse to the body without affecting the position. An impulse is
     * time-independent! Applying an impulse every frame would result in a framerate-dependent force.
     * For this reason, it should only be used when simulating one-time impacts (use the "_force"
     * functions otherwise). Note: `inverse_inertia` is required for this to work. To have
     * `inverse_inertia`, an active `CollisionShape2D` must be a child of the node, or you can manually
     * set `inverse_inertia`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.apply_torque_impulse
     */
    fun applyTorqueImpulse(impulse: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.applyTorqueImpulseBind, segment, impulse)
    }

    /**
     * Applies a positioned impulse to the body. An impulse is time-independent! Applying an impulse
     * every frame would result in a framerate-dependent force. For this reason, it should only be used
     * when simulating one-time impacts (use the "_force" functions otherwise). `position` is the
     * offset from the body origin in global coordinates.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.apply_impulse
     */
    fun applyImpulse(impulse: Vector2, position: Vector2 = Vector2(0.0, 0.0)) {
        ObjectCalls.ptrcallWithTwoVector2Args(Binds.applyImpulseBind, segment, impulse, position)
    }

    /**
     * Applies a directional force without affecting rotation. A force is time dependent and meant to
     * be applied every physics update. This is equivalent to using `apply_force` at the body's center
     * of mass.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.apply_central_force
     */
    fun applyCentralForce(force: Vector2 = Vector2(0.0, 0.0)) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.applyCentralForceBind, segment, force)
    }

    /**
     * Applies a positioned force to the body. A force is time dependent and meant to be applied every
     * physics update. `position` is the offset from the body origin in global coordinates.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.apply_force
     */
    fun applyForce(force: Vector2, position: Vector2 = Vector2(0.0, 0.0)) {
        ObjectCalls.ptrcallWithTwoVector2Args(Binds.applyForceBind, segment, force, position)
    }

    /**
     * Applies a rotational force without affecting position. A force is time dependent and meant to be
     * applied every physics update. Note: `inverse_inertia` is required for this to work. To have
     * `inverse_inertia`, an active `CollisionShape2D` must be a child of the node, or you can manually
     * set `inverse_inertia`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.apply_torque
     */
    fun applyTorque(torque: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.applyTorqueBind, segment, torque)
    }

    /**
     * Adds a constant directional force without affecting rotation that keeps being applied over time
     * until cleared with `constant_force = Vector2(0, 0)`. This is equivalent to using
     * `add_constant_force` at the body's center of mass.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.add_constant_central_force
     */
    fun addConstantCentralForce(force: Vector2 = Vector2(0.0, 0.0)) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.addConstantCentralForceBind, segment, force)
    }

    /**
     * Adds a constant positioned force to the body that keeps being applied over time until cleared
     * with `constant_force = Vector2(0, 0)`. `position` is the offset from the body origin in global
     * coordinates.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.add_constant_force
     */
    fun addConstantForce(force: Vector2, position: Vector2 = Vector2(0.0, 0.0)) {
        ObjectCalls.ptrcallWithTwoVector2Args(Binds.addConstantForceBind, segment, force, position)
    }

    /**
     * Adds a constant rotational force without affecting position that keeps being applied over time
     * until cleared with `constant_torque = 0`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.add_constant_torque
     */
    fun addConstantTorque(torque: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.addConstantTorqueBind, segment, torque)
    }

    /**
     * Sets the body's total constant positional forces applied during each physics update. See
     * `add_constant_force` and `add_constant_central_force`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.set_constant_force
     */
    fun setConstantForce(force: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(Binds.setConstantForceBind, segment, force)
    }

    /**
     * Returns the body's total constant positional forces applied during each physics update. See
     * `add_constant_force` and `add_constant_central_force`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_constant_force
     */
    fun getConstantForce(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(Binds.getConstantForceBind, segment)
    }

    /**
     * Sets the body's total constant rotational forces applied during each physics update. See
     * `add_constant_torque`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.set_constant_torque
     */
    fun setConstantTorque(torque: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setConstantTorqueBind, segment, torque)
    }

    /**
     * Returns the body's total constant rotational forces applied during each physics update. See
     * `add_constant_torque`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_constant_torque
     */
    fun getConstantTorque(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getConstantTorqueBind, segment)
    }

    /**
     * If `true`, this body is currently sleeping (not active).
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.set_sleep_state
     */
    fun setSleepState(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setSleepStateBind, segment, enabled)
    }

    /**
     * If `true`, this body is currently sleeping (not active).
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.is_sleeping
     */
    fun isSleeping(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSleepingBind, segment)
    }

    /**
     * The body's collision layer.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.set_collision_layer
     */
    fun setCollisionLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionLayerBind, segment, layer)
    }

    /**
     * The body's collision layer.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_collision_layer
     */
    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionLayerBind, segment)
    }

    /**
     * The body's collision mask.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.set_collision_mask
     */
    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(Binds.setCollisionMaskBind, segment, mask)
    }

    /**
     * The body's collision mask.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_collision_mask
     */
    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(Binds.getCollisionMaskBind, segment)
    }

    /**
     * Returns the number of contacts this body has with other bodies. Note: By default, this returns 0
     * unless bodies are configured to monitor contacts. See `RigidBody2D.contact_monitor`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_contact_count
     */
    fun getContactCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getContactCountBind, segment)
    }

    /**
     * Returns the position of the contact point on the body in the global coordinate system.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_contact_local_position
     */
    fun getContactLocalPosition(contactIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getContactLocalPositionBind, segment, contactIdx)
    }

    /**
     * Returns the local normal at the contact point.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_contact_local_normal
     */
    fun getContactLocalNormal(contactIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getContactLocalNormalBind, segment, contactIdx)
    }

    /**
     * Returns the local shape index of the collision.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_contact_local_shape
     */
    fun getContactLocalShape(contactIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getContactLocalShapeBind, segment, contactIdx)
    }

    /**
     * Returns the velocity vector at the body's contact point.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_contact_local_velocity_at_position
     */
    fun getContactLocalVelocityAtPosition(contactIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getContactLocalVelocityAtPositionBind, segment, contactIdx)
    }

    /**
     * Returns the collider's `RID`.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_contact_collider
     */
    fun getContactCollider(contactIdx: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(Binds.getContactColliderBind, segment, contactIdx)
    }

    /**
     * Returns the position of the contact point on the collider in the global coordinate system.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_contact_collider_position
     */
    fun getContactColliderPosition(contactIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getContactColliderPositionBind, segment, contactIdx)
    }

    /**
     * Returns the collider's object id.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_contact_collider_id
     */
    fun getContactColliderId(contactIdx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(Binds.getContactColliderIdBind, segment, contactIdx)
    }

    /**
     * Returns the collider object. This depends on how it was created (will return a scene node if
     * such was used to create it).
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_contact_collider_object
     */
    fun getContactColliderObject(contactIdx: Int): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallWithIntArgRetObject(Binds.getContactColliderObjectBind, segment, contactIdx))
    }

    /**
     * Returns the collider's shape index.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_contact_collider_shape
     */
    fun getContactColliderShape(contactIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(Binds.getContactColliderShapeBind, segment, contactIdx)
    }

    /**
     * Returns the velocity vector at the collider's contact point.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_contact_collider_velocity_at_position
     */
    fun getContactColliderVelocityAtPosition(contactIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getContactColliderVelocityAtPositionBind, segment, contactIdx)
    }

    /**
     * Returns the impulse created by the contact.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_contact_impulse
     */
    fun getContactImpulse(contactIdx: Int): Vector2 {
        return ObjectCalls.ptrcallWithIntArgRetVector2(Binds.getContactImpulseBind, segment, contactIdx)
    }

    /**
     * The timestep (delta) used for the simulation.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_step
     */
    fun getStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getStepBind, segment)
    }

    /**
     * Updates the body's linear and angular velocity by applying gravity and damping for the
     * equivalent of one physics tick.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.integrate_forces
     */
    fun integrateForces() {
        ObjectCalls.ptrcallNoArgs(Binds.integrateForcesBind, segment)
    }

    /**
     * Returns the current state of the space, useful for queries.
     *
     * Generated from Godot docs: PhysicsDirectBodyState2D.get_space_state
     */
    fun getSpaceState(): PhysicsDirectSpaceState2D {
        return requireGodotReturn(PhysicsDirectSpaceState2D.wrap(ObjectCalls.ptrcallNoArgsRetObject(Binds.getSpaceStateBind, segment)), "PhysicsDirectBodyState2D.get_space_state")
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsDirectBodyState2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PhysicsDirectBodyState2D? =
            if (handle.address() == 0L) null else PhysicsDirectBodyState2D(GodotHandle(handle))
    }

    private object Binds {
        private const val GET_TOTAL_GRAVITY_HASH = 3341600327L
        @JvmField
        val getTotalGravityBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_total_gravity", GET_TOTAL_GRAVITY_HASH)

        private const val GET_TOTAL_LINEAR_DAMP_HASH = 1740695150L
        @JvmField
        val getTotalLinearDampBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_total_linear_damp", GET_TOTAL_LINEAR_DAMP_HASH)

        private const val GET_TOTAL_ANGULAR_DAMP_HASH = 1740695150L
        @JvmField
        val getTotalAngularDampBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_total_angular_damp", GET_TOTAL_ANGULAR_DAMP_HASH)

        private const val GET_CENTER_OF_MASS_HASH = 3341600327L
        @JvmField
        val getCenterOfMassBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_center_of_mass", GET_CENTER_OF_MASS_HASH)

        private const val GET_CENTER_OF_MASS_LOCAL_HASH = 3341600327L
        @JvmField
        val getCenterOfMassLocalBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_center_of_mass_local", GET_CENTER_OF_MASS_LOCAL_HASH)

        private const val GET_INVERSE_MASS_HASH = 1740695150L
        @JvmField
        val getInverseMassBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_inverse_mass", GET_INVERSE_MASS_HASH)

        private const val GET_INVERSE_INERTIA_HASH = 1740695150L
        @JvmField
        val getInverseInertiaBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_inverse_inertia", GET_INVERSE_INERTIA_HASH)

        private const val SET_LINEAR_VELOCITY_HASH = 743155724L
        @JvmField
        val setLinearVelocityBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "set_linear_velocity", SET_LINEAR_VELOCITY_HASH)

        private const val GET_LINEAR_VELOCITY_HASH = 3341600327L
        @JvmField
        val getLinearVelocityBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_linear_velocity", GET_LINEAR_VELOCITY_HASH)

        private const val SET_ANGULAR_VELOCITY_HASH = 373806689L
        @JvmField
        val setAngularVelocityBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "set_angular_velocity", SET_ANGULAR_VELOCITY_HASH)

        private const val GET_ANGULAR_VELOCITY_HASH = 1740695150L
        @JvmField
        val getAngularVelocityBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_angular_velocity", GET_ANGULAR_VELOCITY_HASH)

        private const val SET_TRANSFORM_HASH = 2761652528L
        @JvmField
        val setTransformBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "set_transform", SET_TRANSFORM_HASH)

        private const val GET_TRANSFORM_HASH = 3814499831L
        @JvmField
        val getTransformBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_transform", GET_TRANSFORM_HASH)

        private const val GET_VELOCITY_AT_LOCAL_POSITION_HASH = 2656412154L
        @JvmField
        val getVelocityAtLocalPositionBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_velocity_at_local_position", GET_VELOCITY_AT_LOCAL_POSITION_HASH)

        private const val APPLY_CENTRAL_IMPULSE_HASH = 743155724L
        @JvmField
        val applyCentralImpulseBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "apply_central_impulse", APPLY_CENTRAL_IMPULSE_HASH)

        private const val APPLY_TORQUE_IMPULSE_HASH = 373806689L
        @JvmField
        val applyTorqueImpulseBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "apply_torque_impulse", APPLY_TORQUE_IMPULSE_HASH)

        private const val APPLY_IMPULSE_HASH = 4288681949L
        @JvmField
        val applyImpulseBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "apply_impulse", APPLY_IMPULSE_HASH)

        private const val APPLY_CENTRAL_FORCE_HASH = 3862383994L
        @JvmField
        val applyCentralForceBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "apply_central_force", APPLY_CENTRAL_FORCE_HASH)

        private const val APPLY_FORCE_HASH = 4288681949L
        @JvmField
        val applyForceBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "apply_force", APPLY_FORCE_HASH)

        private const val APPLY_TORQUE_HASH = 373806689L
        @JvmField
        val applyTorqueBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "apply_torque", APPLY_TORQUE_HASH)

        private const val ADD_CONSTANT_CENTRAL_FORCE_HASH = 3862383994L
        @JvmField
        val addConstantCentralForceBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "add_constant_central_force", ADD_CONSTANT_CENTRAL_FORCE_HASH)

        private const val ADD_CONSTANT_FORCE_HASH = 4288681949L
        @JvmField
        val addConstantForceBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "add_constant_force", ADD_CONSTANT_FORCE_HASH)

        private const val ADD_CONSTANT_TORQUE_HASH = 373806689L
        @JvmField
        val addConstantTorqueBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "add_constant_torque", ADD_CONSTANT_TORQUE_HASH)

        private const val SET_CONSTANT_FORCE_HASH = 743155724L
        @JvmField
        val setConstantForceBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "set_constant_force", SET_CONSTANT_FORCE_HASH)

        private const val GET_CONSTANT_FORCE_HASH = 3341600327L
        @JvmField
        val getConstantForceBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_constant_force", GET_CONSTANT_FORCE_HASH)

        private const val SET_CONSTANT_TORQUE_HASH = 373806689L
        @JvmField
        val setConstantTorqueBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "set_constant_torque", SET_CONSTANT_TORQUE_HASH)

        private const val GET_CONSTANT_TORQUE_HASH = 1740695150L
        @JvmField
        val getConstantTorqueBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_constant_torque", GET_CONSTANT_TORQUE_HASH)

        private const val SET_SLEEP_STATE_HASH = 2586408642L
        @JvmField
        val setSleepStateBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "set_sleep_state", SET_SLEEP_STATE_HASH)

        private const val IS_SLEEPING_HASH = 36873697L
        @JvmField
        val isSleepingBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "is_sleeping", IS_SLEEPING_HASH)

        private const val SET_COLLISION_LAYER_HASH = 1286410249L
        @JvmField
        val setCollisionLayerBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "set_collision_layer", SET_COLLISION_LAYER_HASH)

        private const val GET_COLLISION_LAYER_HASH = 3905245786L
        @JvmField
        val getCollisionLayerBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_collision_layer", GET_COLLISION_LAYER_HASH)

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        @JvmField
        val setCollisionMaskBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "set_collision_mask", SET_COLLISION_MASK_HASH)

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        @JvmField
        val getCollisionMaskBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_collision_mask", GET_COLLISION_MASK_HASH)

        private const val GET_CONTACT_COUNT_HASH = 3905245786L
        @JvmField
        val getContactCountBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_contact_count", GET_CONTACT_COUNT_HASH)

        private const val GET_CONTACT_LOCAL_POSITION_HASH = 2299179447L
        @JvmField
        val getContactLocalPositionBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_contact_local_position", GET_CONTACT_LOCAL_POSITION_HASH)

        private const val GET_CONTACT_LOCAL_NORMAL_HASH = 2299179447L
        @JvmField
        val getContactLocalNormalBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_contact_local_normal", GET_CONTACT_LOCAL_NORMAL_HASH)

        private const val GET_CONTACT_LOCAL_SHAPE_HASH = 923996154L
        @JvmField
        val getContactLocalShapeBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_contact_local_shape", GET_CONTACT_LOCAL_SHAPE_HASH)

        private const val GET_CONTACT_LOCAL_VELOCITY_AT_POSITION_HASH = 2299179447L
        @JvmField
        val getContactLocalVelocityAtPositionBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_contact_local_velocity_at_position", GET_CONTACT_LOCAL_VELOCITY_AT_POSITION_HASH)

        private const val GET_CONTACT_COLLIDER_HASH = 495598643L
        @JvmField
        val getContactColliderBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_contact_collider", GET_CONTACT_COLLIDER_HASH)

        private const val GET_CONTACT_COLLIDER_POSITION_HASH = 2299179447L
        @JvmField
        val getContactColliderPositionBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_contact_collider_position", GET_CONTACT_COLLIDER_POSITION_HASH)

        private const val GET_CONTACT_COLLIDER_ID_HASH = 923996154L
        @JvmField
        val getContactColliderIdBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_contact_collider_id", GET_CONTACT_COLLIDER_ID_HASH)

        private const val GET_CONTACT_COLLIDER_OBJECT_HASH = 3332903315L
        @JvmField
        val getContactColliderObjectBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_contact_collider_object", GET_CONTACT_COLLIDER_OBJECT_HASH)

        private const val GET_CONTACT_COLLIDER_SHAPE_HASH = 923996154L
        @JvmField
        val getContactColliderShapeBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_contact_collider_shape", GET_CONTACT_COLLIDER_SHAPE_HASH)

        private const val GET_CONTACT_COLLIDER_VELOCITY_AT_POSITION_HASH = 2299179447L
        @JvmField
        val getContactColliderVelocityAtPositionBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_contact_collider_velocity_at_position", GET_CONTACT_COLLIDER_VELOCITY_AT_POSITION_HASH)

        private const val GET_CONTACT_IMPULSE_HASH = 2299179447L
        @JvmField
        val getContactImpulseBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_contact_impulse", GET_CONTACT_IMPULSE_HASH)

        private const val GET_STEP_HASH = 1740695150L
        @JvmField
        val getStepBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_step", GET_STEP_HASH)

        private const val INTEGRATE_FORCES_HASH = 3218959716L
        @JvmField
        val integrateForcesBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "integrate_forces", INTEGRATE_FORCES_HASH)

        private const val GET_SPACE_STATE_HASH = 2506717822L
        @JvmField
        val getSpaceStateBind =
            ObjectCalls.getMethodBind("PhysicsDirectBodyState2D", "get_space_state", GET_SPACE_STATE_HASH)
    }
}
