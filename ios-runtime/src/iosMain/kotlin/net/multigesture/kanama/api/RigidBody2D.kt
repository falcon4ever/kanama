package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: RigidBody2D
 */
open class RigidBody2D(handle: MemorySegment) : PhysicsBody2D(handle) {
    var mass: Double
        @JvmName("massProperty")
        get() = getMass()
        @JvmName("setMassProperty")
        set(value) = setMass(value)

    var physicsMaterialOverride: PhysicsMaterial?
        @JvmName("physicsMaterialOverrideProperty")
        get() = getPhysicsMaterialOverride()
        @JvmName("setPhysicsMaterialOverrideProperty")
        set(value) = setPhysicsMaterialOverride(value)

    var gravityScale: Double
        @JvmName("gravityScaleProperty")
        get() = getGravityScale()
        @JvmName("setGravityScaleProperty")
        set(value) = setGravityScale(value)

    var centerOfMassMode: Long
        @JvmName("centerOfMassModeProperty")
        get() = getCenterOfMassMode()
        @JvmName("setCenterOfMassModeProperty")
        set(value) = setCenterOfMassMode(value)

    var centerOfMass: Vector2
        @JvmName("centerOfMassProperty")
        get() = getCenterOfMass()
        @JvmName("setCenterOfMassProperty")
        set(value) = setCenterOfMass(value)

    var inertia: Double
        @JvmName("inertiaProperty")
        get() = getInertia()
        @JvmName("setInertiaProperty")
        set(value) = setInertia(value)

    var sleeping: Boolean
        @JvmName("sleepingProperty")
        get() = isSleeping()
        @JvmName("setSleepingProperty")
        set(value) = setSleeping(value)

    var canSleep: Boolean
        @JvmName("canSleepProperty")
        get() = isAbleToSleep()
        @JvmName("setCanSleepProperty")
        set(value) = setCanSleep(value)

    var lockRotation: Boolean
        @JvmName("lockRotationProperty")
        get() = isLockRotationEnabled()
        @JvmName("setLockRotationProperty")
        set(value) = setLockRotationEnabled(value)

    var freeze: Boolean
        @JvmName("freezeProperty")
        get() = isFreezeEnabled()
        @JvmName("setFreezeProperty")
        set(value) = setFreezeEnabled(value)

    var freezeMode: Long
        @JvmName("freezeModeProperty")
        get() = getFreezeMode()
        @JvmName("setFreezeModeProperty")
        set(value) = setFreezeMode(value)

    var customIntegrator: Boolean
        @JvmName("customIntegratorProperty")
        get() = isUsingCustomIntegrator()
        @JvmName("setCustomIntegratorProperty")
        set(value) = setUseCustomIntegrator(value)

    var continuousCd: Long
        @JvmName("continuousCdProperty")
        get() = getContinuousCollisionDetectionMode()
        @JvmName("setContinuousCdProperty")
        set(value) = setContinuousCollisionDetectionMode(value)

    var contactMonitor: Boolean
        @JvmName("contactMonitorProperty")
        get() = isContactMonitorEnabled()
        @JvmName("setContactMonitorProperty")
        set(value) = setContactMonitor(value)

    var maxContactsReported: Int
        @JvmName("maxContactsReportedProperty")
        get() = getMaxContactsReported()
        @JvmName("setMaxContactsReportedProperty")
        set(value) = setMaxContactsReported(value)

    var linearVelocity: Vector2
        @JvmName("linearVelocityProperty")
        get() = getLinearVelocity()
        @JvmName("setLinearVelocityProperty")
        set(value) = setLinearVelocity(value)

    var linearDampMode: Long
        @JvmName("linearDampModeProperty")
        get() = getLinearDampMode()
        @JvmName("setLinearDampModeProperty")
        set(value) = setLinearDampMode(value)

    var linearDamp: Double
        @JvmName("linearDampProperty")
        get() = getLinearDamp()
        @JvmName("setLinearDampProperty")
        set(value) = setLinearDamp(value)

    var angularVelocity: Double
        @JvmName("angularVelocityProperty")
        get() = getAngularVelocity()
        @JvmName("setAngularVelocityProperty")
        set(value) = setAngularVelocity(value)

    var angularDampMode: Long
        @JvmName("angularDampModeProperty")
        get() = getAngularDampMode()
        @JvmName("setAngularDampModeProperty")
        set(value) = setAngularDampMode(value)

    var angularDamp: Double
        @JvmName("angularDampProperty")
        get() = getAngularDamp()
        @JvmName("setAngularDampProperty")
        set(value) = setAngularDamp(value)

    var constantForce: Vector2
        @JvmName("constantForceProperty")
        get() = getConstantForce()
        @JvmName("setConstantForceProperty")
        set(value) = setConstantForce(value)

    var constantTorque: Double
        @JvmName("constantTorqueProperty")
        get() = getConstantTorque()
        @JvmName("setConstantTorqueProperty")
        set(value) = setConstantTorque(value)

    fun setMass(mass: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMassBind, handle, mass)
    }

    fun getMass(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMassBind, handle)
    }

    fun getInertia(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getInertiaBind, handle)
    }

    fun setInertia(inertia: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setInertiaBind, handle, inertia)
    }

    fun setCenterOfMassMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setCenterOfMassModeBind, handle, mode)
    }

    fun getCenterOfMassMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getCenterOfMassModeBind, handle)
    }

    fun setCenterOfMass(centerOfMass: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setCenterOfMassBind, handle, centerOfMass)
    }

    fun getCenterOfMass(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getCenterOfMassBind, handle)
    }

    fun setPhysicsMaterialOverride(physicsMaterialOverride: PhysicsMaterial?) {
        ObjectCalls.ptrcallWithObjectArgs(setPhysicsMaterialOverrideBind, handle, listOf(physicsMaterialOverride?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getPhysicsMaterialOverride(): PhysicsMaterial? {
        return PhysicsMaterial.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPhysicsMaterialOverrideBind, handle))
    }

    fun setGravityScale(gravityScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setGravityScaleBind, handle, gravityScale)
    }

    fun getGravityScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getGravityScaleBind, handle)
    }

    fun setLinearDampMode(linearDampMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setLinearDampModeBind, handle, linearDampMode)
    }

    fun getLinearDampMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getLinearDampModeBind, handle)
    }

    fun setAngularDampMode(angularDampMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setAngularDampModeBind, handle, angularDampMode)
    }

    fun getAngularDampMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getAngularDampModeBind, handle)
    }

    fun setLinearDamp(linearDamp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setLinearDampBind, handle, linearDamp)
    }

    fun getLinearDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getLinearDampBind, handle)
    }

    fun setAngularDamp(angularDamp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAngularDampBind, handle, angularDamp)
    }

    fun getAngularDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAngularDampBind, handle)
    }

    fun setLinearVelocity(linearVelocity: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setLinearVelocityBind, handle, linearVelocity)
    }

    fun getLinearVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getLinearVelocityBind, handle)
    }

    fun setAngularVelocity(angularVelocity: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setAngularVelocityBind, handle, angularVelocity)
    }

    fun getAngularVelocity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getAngularVelocityBind, handle)
    }

    fun setMaxContactsReported(amount: Int) {
        ObjectCalls.ptrcallWithIntArg(setMaxContactsReportedBind, handle, amount)
    }

    fun getMaxContactsReported(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getMaxContactsReportedBind, handle)
    }

    fun getContactCount(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getContactCountBind, handle)
    }

    fun setUseCustomIntegrator(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseCustomIntegratorBind, handle, enable)
    }

    fun isUsingCustomIntegrator(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingCustomIntegratorBind, handle)
    }

    fun setContactMonitor(enabled: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setContactMonitorBind, handle, enabled)
    }

    fun isContactMonitorEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isContactMonitorEnabledBind, handle)
    }

    fun setContinuousCollisionDetectionMode(mode: Long) {
        ObjectCalls.ptrcallWithLongArg(setContinuousCollisionDetectionModeBind, handle, mode)
    }

    fun getContinuousCollisionDetectionMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getContinuousCollisionDetectionModeBind, handle)
    }

    fun setAxisVelocity(axisVelocity: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setAxisVelocityBind, handle, axisVelocity)
    }

    fun applyCentralImpulse(impulse: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithVector2Arg(applyCentralImpulseBind, handle, impulse)
    }

    fun applyImpulse(impulse: Vector2, position: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithTwoVector2Args(applyImpulseBind, handle, impulse, position)
    }

    fun applyTorqueImpulse(torque: Double) {
        ObjectCalls.ptrcallWithDoubleArg(applyTorqueImpulseBind, handle, torque)
    }

    fun applyCentralForce(force: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(applyCentralForceBind, handle, force)
    }

    fun applyForce(force: Vector2, position: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithTwoVector2Args(applyForceBind, handle, force, position)
    }

    fun applyTorque(torque: Double) {
        ObjectCalls.ptrcallWithDoubleArg(applyTorqueBind, handle, torque)
    }

    fun addConstantCentralForce(force: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(addConstantCentralForceBind, handle, force)
    }

    fun addConstantForce(force: Vector2, position: Vector2 = Vector2(0f, 0f)) {
        ObjectCalls.ptrcallWithTwoVector2Args(addConstantForceBind, handle, force, position)
    }

    fun addConstantTorque(torque: Double) {
        ObjectCalls.ptrcallWithDoubleArg(addConstantTorqueBind, handle, torque)
    }

    fun setConstantForce(force: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setConstantForceBind, handle, force)
    }

    fun getConstantForce(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getConstantForceBind, handle)
    }

    fun setConstantTorque(torque: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setConstantTorqueBind, handle, torque)
    }

    fun getConstantTorque(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getConstantTorqueBind, handle)
    }

    fun setSleeping(sleeping: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setSleepingBind, handle, sleeping)
    }

    fun isSleeping(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSleepingBind, handle)
    }

    fun setCanSleep(ableToSleep: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCanSleepBind, handle, ableToSleep)
    }

    fun isAbleToSleep(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAbleToSleepBind, handle)
    }

    fun setLockRotationEnabled(lockRotation: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setLockRotationEnabledBind, handle, lockRotation)
    }

    fun isLockRotationEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isLockRotationEnabledBind, handle)
    }

    fun setFreezeEnabled(freezeMode: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setFreezeEnabledBind, handle, freezeMode)
    }

    fun isFreezeEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isFreezeEnabledBind, handle)
    }

    fun setFreezeMode(freezeMode: Long) {
        ObjectCalls.ptrcallWithLongArg(setFreezeModeBind, handle, freezeMode)
    }

    fun getFreezeMode(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getFreezeModeBind, handle)
    }

    fun getCollidingBodies(): List<Node2D> {
        return ObjectCalls.ptrcallNoArgsRetTypedObjectList(getCollidingBodiesBind, handle, Node2D::fromHandle)
    }

    object Signals {
        const val bodyShapeEntered: String = "body_shape_entered"
        const val bodyShapeExited: String = "body_shape_exited"
        const val bodyEntered: String = "body_entered"
        const val bodyExited: String = "body_exited"
        const val sleepingStateChanged: String = "sleeping_state_changed"
    }

    companion object {
        const val FREEZE_MODE_STATIC: Long = 0L
        const val FREEZE_MODE_KINEMATIC: Long = 1L
        const val CENTER_OF_MASS_MODE_AUTO: Long = 0L
        const val CENTER_OF_MASS_MODE_CUSTOM: Long = 1L
        const val DAMP_MODE_COMBINE: Long = 0L
        const val DAMP_MODE_REPLACE: Long = 1L
        const val CCD_MODE_DISABLED: Long = 0L
        const val CCD_MODE_CAST_RAY: Long = 1L
        const val CCD_MODE_CAST_SHAPE: Long = 2L

        fun fromHandle(handle: MemorySegment): RigidBody2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): RigidBody2D? =
            if (handle.address() == 0L) null else RigidBody2D(handle)

        private const val SET_MASS_HASH = 373806689L
        private val setMassBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_mass", SET_MASS_HASH)
        }

        private const val GET_MASS_HASH = 1740695150L
        private val getMassBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_mass", GET_MASS_HASH)
        }

        private const val GET_INERTIA_HASH = 1740695150L
        private val getInertiaBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_inertia", GET_INERTIA_HASH)
        }

        private const val SET_INERTIA_HASH = 373806689L
        private val setInertiaBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_inertia", SET_INERTIA_HASH)
        }

        private const val SET_CENTER_OF_MASS_MODE_HASH = 1757235706L
        private val setCenterOfMassModeBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_center_of_mass_mode", SET_CENTER_OF_MASS_MODE_HASH)
        }

        private const val GET_CENTER_OF_MASS_MODE_HASH = 3277132817L
        private val getCenterOfMassModeBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_center_of_mass_mode", GET_CENTER_OF_MASS_MODE_HASH)
        }

        private const val SET_CENTER_OF_MASS_HASH = 743155724L
        private val setCenterOfMassBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_center_of_mass", SET_CENTER_OF_MASS_HASH)
        }

        private const val GET_CENTER_OF_MASS_HASH = 3341600327L
        private val getCenterOfMassBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_center_of_mass", GET_CENTER_OF_MASS_HASH)
        }

        private const val SET_PHYSICS_MATERIAL_OVERRIDE_HASH = 1784508650L
        private val setPhysicsMaterialOverrideBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_physics_material_override", SET_PHYSICS_MATERIAL_OVERRIDE_HASH)
        }

        private const val GET_PHYSICS_MATERIAL_OVERRIDE_HASH = 2521850424L
        private val getPhysicsMaterialOverrideBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_physics_material_override", GET_PHYSICS_MATERIAL_OVERRIDE_HASH)
        }

        private const val SET_GRAVITY_SCALE_HASH = 373806689L
        private val setGravityScaleBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_gravity_scale", SET_GRAVITY_SCALE_HASH)
        }

        private const val GET_GRAVITY_SCALE_HASH = 1740695150L
        private val getGravityScaleBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_gravity_scale", GET_GRAVITY_SCALE_HASH)
        }

        private const val SET_LINEAR_DAMP_MODE_HASH = 3406533708L
        private val setLinearDampModeBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_linear_damp_mode", SET_LINEAR_DAMP_MODE_HASH)
        }

        private const val GET_LINEAR_DAMP_MODE_HASH = 2970511462L
        private val getLinearDampModeBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_linear_damp_mode", GET_LINEAR_DAMP_MODE_HASH)
        }

        private const val SET_ANGULAR_DAMP_MODE_HASH = 3406533708L
        private val setAngularDampModeBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_angular_damp_mode", SET_ANGULAR_DAMP_MODE_HASH)
        }

        private const val GET_ANGULAR_DAMP_MODE_HASH = 2970511462L
        private val getAngularDampModeBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_angular_damp_mode", GET_ANGULAR_DAMP_MODE_HASH)
        }

        private const val SET_LINEAR_DAMP_HASH = 373806689L
        private val setLinearDampBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_linear_damp", SET_LINEAR_DAMP_HASH)
        }

        private const val GET_LINEAR_DAMP_HASH = 1740695150L
        private val getLinearDampBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_linear_damp", GET_LINEAR_DAMP_HASH)
        }

        private const val SET_ANGULAR_DAMP_HASH = 373806689L
        private val setAngularDampBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_angular_damp", SET_ANGULAR_DAMP_HASH)
        }

        private const val GET_ANGULAR_DAMP_HASH = 1740695150L
        private val getAngularDampBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_angular_damp", GET_ANGULAR_DAMP_HASH)
        }

        private const val SET_LINEAR_VELOCITY_HASH = 743155724L
        private val setLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_linear_velocity", SET_LINEAR_VELOCITY_HASH)
        }

        private const val GET_LINEAR_VELOCITY_HASH = 3341600327L
        private val getLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_linear_velocity", GET_LINEAR_VELOCITY_HASH)
        }

        private const val SET_ANGULAR_VELOCITY_HASH = 373806689L
        private val setAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_angular_velocity", SET_ANGULAR_VELOCITY_HASH)
        }

        private const val GET_ANGULAR_VELOCITY_HASH = 1740695150L
        private val getAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_angular_velocity", GET_ANGULAR_VELOCITY_HASH)
        }

        private const val SET_MAX_CONTACTS_REPORTED_HASH = 1286410249L
        private val setMaxContactsReportedBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_max_contacts_reported", SET_MAX_CONTACTS_REPORTED_HASH)
        }

        private const val GET_MAX_CONTACTS_REPORTED_HASH = 3905245786L
        private val getMaxContactsReportedBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_max_contacts_reported", GET_MAX_CONTACTS_REPORTED_HASH)
        }

        private const val GET_CONTACT_COUNT_HASH = 3905245786L
        private val getContactCountBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_contact_count", GET_CONTACT_COUNT_HASH)
        }

        private const val SET_USE_CUSTOM_INTEGRATOR_HASH = 2586408642L
        private val setUseCustomIntegratorBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_use_custom_integrator", SET_USE_CUSTOM_INTEGRATOR_HASH)
        }

        private const val IS_USING_CUSTOM_INTEGRATOR_HASH = 2240911060L
        private val isUsingCustomIntegratorBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "is_using_custom_integrator", IS_USING_CUSTOM_INTEGRATOR_HASH)
        }

        private const val SET_CONTACT_MONITOR_HASH = 2586408642L
        private val setContactMonitorBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_contact_monitor", SET_CONTACT_MONITOR_HASH)
        }

        private const val IS_CONTACT_MONITOR_ENABLED_HASH = 36873697L
        private val isContactMonitorEnabledBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "is_contact_monitor_enabled", IS_CONTACT_MONITOR_ENABLED_HASH)
        }

        private const val SET_CONTINUOUS_COLLISION_DETECTION_MODE_HASH = 1000241384L
        private val setContinuousCollisionDetectionModeBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_continuous_collision_detection_mode", SET_CONTINUOUS_COLLISION_DETECTION_MODE_HASH)
        }

        private const val GET_CONTINUOUS_COLLISION_DETECTION_MODE_HASH = 815214376L
        private val getContinuousCollisionDetectionModeBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_continuous_collision_detection_mode", GET_CONTINUOUS_COLLISION_DETECTION_MODE_HASH)
        }

        private const val SET_AXIS_VELOCITY_HASH = 743155724L
        private val setAxisVelocityBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_axis_velocity", SET_AXIS_VELOCITY_HASH)
        }

        private const val APPLY_CENTRAL_IMPULSE_HASH = 3862383994L
        private val applyCentralImpulseBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "apply_central_impulse", APPLY_CENTRAL_IMPULSE_HASH)
        }

        private const val APPLY_IMPULSE_HASH = 4288681949L
        private val applyImpulseBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "apply_impulse", APPLY_IMPULSE_HASH)
        }

        private const val APPLY_TORQUE_IMPULSE_HASH = 373806689L
        private val applyTorqueImpulseBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "apply_torque_impulse", APPLY_TORQUE_IMPULSE_HASH)
        }

        private const val APPLY_CENTRAL_FORCE_HASH = 743155724L
        private val applyCentralForceBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "apply_central_force", APPLY_CENTRAL_FORCE_HASH)
        }

        private const val APPLY_FORCE_HASH = 4288681949L
        private val applyForceBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "apply_force", APPLY_FORCE_HASH)
        }

        private const val APPLY_TORQUE_HASH = 373806689L
        private val applyTorqueBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "apply_torque", APPLY_TORQUE_HASH)
        }

        private const val ADD_CONSTANT_CENTRAL_FORCE_HASH = 743155724L
        private val addConstantCentralForceBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "add_constant_central_force", ADD_CONSTANT_CENTRAL_FORCE_HASH)
        }

        private const val ADD_CONSTANT_FORCE_HASH = 4288681949L
        private val addConstantForceBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "add_constant_force", ADD_CONSTANT_FORCE_HASH)
        }

        private const val ADD_CONSTANT_TORQUE_HASH = 373806689L
        private val addConstantTorqueBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "add_constant_torque", ADD_CONSTANT_TORQUE_HASH)
        }

        private const val SET_CONSTANT_FORCE_HASH = 743155724L
        private val setConstantForceBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_constant_force", SET_CONSTANT_FORCE_HASH)
        }

        private const val GET_CONSTANT_FORCE_HASH = 3341600327L
        private val getConstantForceBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_constant_force", GET_CONSTANT_FORCE_HASH)
        }

        private const val SET_CONSTANT_TORQUE_HASH = 373806689L
        private val setConstantTorqueBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_constant_torque", SET_CONSTANT_TORQUE_HASH)
        }

        private const val GET_CONSTANT_TORQUE_HASH = 1740695150L
        private val getConstantTorqueBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_constant_torque", GET_CONSTANT_TORQUE_HASH)
        }

        private const val SET_SLEEPING_HASH = 2586408642L
        private val setSleepingBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_sleeping", SET_SLEEPING_HASH)
        }

        private const val IS_SLEEPING_HASH = 36873697L
        private val isSleepingBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "is_sleeping", IS_SLEEPING_HASH)
        }

        private const val SET_CAN_SLEEP_HASH = 2586408642L
        private val setCanSleepBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_can_sleep", SET_CAN_SLEEP_HASH)
        }

        private const val IS_ABLE_TO_SLEEP_HASH = 36873697L
        private val isAbleToSleepBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "is_able_to_sleep", IS_ABLE_TO_SLEEP_HASH)
        }

        private const val SET_LOCK_ROTATION_ENABLED_HASH = 2586408642L
        private val setLockRotationEnabledBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_lock_rotation_enabled", SET_LOCK_ROTATION_ENABLED_HASH)
        }

        private const val IS_LOCK_ROTATION_ENABLED_HASH = 36873697L
        private val isLockRotationEnabledBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "is_lock_rotation_enabled", IS_LOCK_ROTATION_ENABLED_HASH)
        }

        private const val SET_FREEZE_ENABLED_HASH = 2586408642L
        private val setFreezeEnabledBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_freeze_enabled", SET_FREEZE_ENABLED_HASH)
        }

        private const val IS_FREEZE_ENABLED_HASH = 36873697L
        private val isFreezeEnabledBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "is_freeze_enabled", IS_FREEZE_ENABLED_HASH)
        }

        private const val SET_FREEZE_MODE_HASH = 1705112154L
        private val setFreezeModeBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "set_freeze_mode", SET_FREEZE_MODE_HASH)
        }

        private const val GET_FREEZE_MODE_HASH = 2016872314L
        private val getFreezeModeBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_freeze_mode", GET_FREEZE_MODE_HASH)
        }

        private const val GET_COLLIDING_BODIES_HASH = 3995934104L
        private val getCollidingBodiesBind by lazy {
            ObjectCalls.getMethodBind("RigidBody2D", "get_colliding_bodies", GET_COLLIDING_BODIES_HASH)
        }
    }
}
