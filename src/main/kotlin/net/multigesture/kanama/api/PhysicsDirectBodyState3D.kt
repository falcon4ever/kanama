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

    fun getTotalGravity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getTotalGravityBind, handle)
    }

    fun getTotalLinearDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTotalLinearDampBind, handle)
    }

    fun getTotalAngularDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getTotalAngularDampBind, handle)
    }

    fun getCenterOfMass(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getCenterOfMassBind, handle)
    }

    fun getCenterOfMassLocal(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getCenterOfMassLocalBind, handle)
    }

    fun getPrincipalInertiaAxes(): Basis {
        return ObjectCalls.ptrcallNoArgsRetBasis(getPrincipalInertiaAxesBind, handle)
    }

    fun getInverseMass(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInverseMassBind, handle)
    }

    fun getInverseInertia(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getInverseInertiaBind, handle)
    }

    fun getInverseInertiaTensor(): Basis {
        return ObjectCalls.ptrcallNoArgsRetBasis(getInverseInertiaTensorBind, handle)
    }

    fun setLinearVelocity(velocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setLinearVelocityBind, handle, velocity)
    }

    fun getLinearVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getLinearVelocityBind, handle)
    }

    fun setAngularVelocity(velocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setAngularVelocityBind, handle, velocity)
    }

    fun getAngularVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getAngularVelocityBind, handle)
    }

    fun setTransform(transform: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setTransformBind, handle, transform)
    }

    fun getTransform(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getTransformBind, handle)
    }

    fun getVelocityAtLocalPosition(localPosition: Vector3): Vector3 {
        return ObjectCalls.ptrcallWithVector3ArgRetVector3(getVelocityAtLocalPositionBind, handle, localPosition)
    }

    fun applyCentralImpulse(impulse: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(applyCentralImpulseBind, handle, impulse)
    }

    fun applyImpulse(impulse: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithTwoVector3Args(applyImpulseBind, handle, impulse, position)
    }

    fun applyTorqueImpulse(impulse: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(applyTorqueImpulseBind, handle, impulse)
    }

    fun applyCentralForce(force: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(applyCentralForceBind, handle, force)
    }

    fun applyForce(force: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithTwoVector3Args(applyForceBind, handle, force, position)
    }

    fun applyTorque(torque: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(applyTorqueBind, handle, torque)
    }

    fun addConstantCentralForce(force: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(addConstantCentralForceBind, handle, force)
    }

    fun addConstantForce(force: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithTwoVector3Args(addConstantForceBind, handle, force, position)
    }

    fun addConstantTorque(torque: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(addConstantTorqueBind, handle, torque)
    }

    fun setConstantForce(force: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setConstantForceBind, handle, force)
    }

    fun getConstantForce(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getConstantForceBind, handle)
    }

    fun setConstantTorque(torque: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setConstantTorqueBind, handle, torque)
    }

    fun getConstantTorque(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getConstantTorqueBind, handle)
    }

    fun setSleepState(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSleepStateBind, handle, enabled)
    }

    fun isSleeping(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSleepingBind, handle)
    }

    fun setCollisionLayer(layer: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionLayerBind, handle, layer)
    }

    fun getCollisionLayer(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionLayerBind, handle)
    }

    fun setCollisionMask(mask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, mask)
    }

    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    fun getContactCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getContactCountBind, handle)
    }

    fun getContactLocalPosition(contactIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getContactLocalPositionBind, handle, contactIdx)
    }

    fun getContactLocalNormal(contactIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getContactLocalNormalBind, handle, contactIdx)
    }

    fun getContactImpulse(contactIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getContactImpulseBind, handle, contactIdx)
    }

    fun getContactLocalShape(contactIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getContactLocalShapeBind, handle, contactIdx)
    }

    fun getContactLocalVelocityAtPosition(contactIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getContactLocalVelocityAtPositionBind, handle, contactIdx)
    }

    fun getContactCollider(contactIdx: Int): RID {
        return ObjectCalls.ptrcallWithIntArgRetRID(getContactColliderBind, handle, contactIdx)
    }

    fun getContactColliderPosition(contactIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getContactColliderPositionBind, handle, contactIdx)
    }

    fun getContactColliderId(contactIdx: Int): Long {
        return ObjectCalls.ptrcallWithIntArgRetLong(getContactColliderIdBind, handle, contactIdx)
    }

    fun getContactColliderObject(contactIdx: Int): GodotObject? {
        return GodotObject.wrap(ObjectCalls.ptrcallWithIntArgRetObject(getContactColliderObjectBind, handle, contactIdx))
    }

    fun getContactColliderShape(contactIdx: Int): Int {
        return ObjectCalls.ptrcallWithIntArgRetInt(getContactColliderShapeBind, handle, contactIdx)
    }

    fun getContactColliderVelocityAtPosition(contactIdx: Int): Vector3 {
        return ObjectCalls.ptrcallWithIntArgRetVector3(getContactColliderVelocityAtPositionBind, handle, contactIdx)
    }

    fun getStep(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getStepBind, handle)
    }

    fun integrateForces() {
        ObjectCalls.ptrcallNoArgs(integrateForcesBind, handle)
    }

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
