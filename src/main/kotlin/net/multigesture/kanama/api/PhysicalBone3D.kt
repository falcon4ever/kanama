package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * A physics body used to make bones in a `Skeleton3D` react to physics.
 *
 * Generated from Godot docs: PhysicalBone3D
 */
class PhysicalBone3D(handle: MemorySegment) : PhysicsBody3D(handle) {
    var jointType: Long
        @JvmName("jointTypeProperty")
        get() = getJointType()
        @JvmName("setJointTypeProperty")
        set(value) = setJointType(value)

    var jointOffset: Transform3D
        @JvmName("jointOffsetProperty")
        get() = getJointOffset()
        @JvmName("setJointOffsetProperty")
        set(value) = setJointOffset(value)

    var jointRotation: Vector3
        @JvmName("jointRotationProperty")
        get() = getJointRotation()
        @JvmName("setJointRotationProperty")
        set(value) = setJointRotation(value)

    var bodyOffset: Transform3D
        @JvmName("bodyOffsetProperty")
        get() = getBodyOffset()
        @JvmName("setBodyOffsetProperty")
        set(value) = setBodyOffset(value)

    var mass: Double
        @JvmName("massProperty")
        get() = getMass()
        @JvmName("setMassProperty")
        set(value) = setMass(value)

    var friction: Double
        @JvmName("frictionProperty")
        get() = getFriction()
        @JvmName("setFrictionProperty")
        set(value) = setFriction(value)

    var bounce: Double
        @JvmName("bounceProperty")
        get() = getBounce()
        @JvmName("setBounceProperty")
        set(value) = setBounce(value)

    var gravityScale: Double
        @JvmName("gravityScaleProperty")
        get() = getGravityScale()
        @JvmName("setGravityScaleProperty")
        set(value) = setGravityScale(value)

    var customIntegrator: Boolean
        @JvmName("customIntegratorProperty")
        get() = isUsingCustomIntegrator()
        @JvmName("setCustomIntegratorProperty")
        set(value) = setUseCustomIntegrator(value)

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

    var linearVelocity: Vector3
        @JvmName("linearVelocityProperty")
        get() = getLinearVelocity()
        @JvmName("setLinearVelocityProperty")
        set(value) = setLinearVelocity(value)

    var angularVelocity: Vector3
        @JvmName("angularVelocityProperty")
        get() = getAngularVelocity()
        @JvmName("setAngularVelocityProperty")
        set(value) = setAngularVelocity(value)

    var canSleep: Boolean
        @JvmName("canSleepProperty")
        get() = isAbleToSleep()
        @JvmName("setCanSleepProperty")
        set(value) = setCanSleep(value)

    fun applyCentralImpulse(impulse: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(applyCentralImpulseBind, handle, impulse)
    }

    fun applyImpulse(impulse: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithTwoVector3Args(applyImpulseBind, handle, impulse, position)
    }

    fun setJointType(jointType: Long) {
        ObjectCalls.ptrcallWithLongArg(setJointTypeBind, handle, jointType)
    }

    fun getJointType(): Long {
        return ObjectCalls.ptrcallNoArgsRetLong(getJointTypeBind, handle)
    }

    fun setJointOffset(offset: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setJointOffsetBind, handle, offset)
    }

    fun getJointOffset(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getJointOffsetBind, handle)
    }

    fun setJointRotation(euler: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setJointRotationBind, handle, euler)
    }

    fun getJointRotation(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getJointRotationBind, handle)
    }

    fun setBodyOffset(offset: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(setBodyOffsetBind, handle, offset)
    }

    fun getBodyOffset(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(getBodyOffsetBind, handle)
    }

    fun getSimulatePhysics(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(getSimulatePhysicsBind, handle)
    }

    fun isSimulatingPhysics(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isSimulatingPhysicsBind, handle)
    }

    fun getBoneId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(getBoneIdBind, handle)
    }

    fun setMass(mass: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setMassBind, handle, mass)
    }

    fun getMass(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getMassBind, handle)
    }

    fun setFriction(friction: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFrictionBind, handle, friction)
    }

    fun getFriction(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFrictionBind, handle)
    }

    fun setBounce(bounce: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBounceBind, handle, bounce)
    }

    fun getBounce(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBounceBind, handle)
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

    fun setLinearVelocity(linearVelocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setLinearVelocityBind, handle, linearVelocity)
    }

    fun getLinearVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getLinearVelocityBind, handle)
    }

    fun setAngularVelocity(angularVelocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setAngularVelocityBind, handle, angularVelocity)
    }

    fun getAngularVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getAngularVelocityBind, handle)
    }

    fun setUseCustomIntegrator(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseCustomIntegratorBind, handle, enable)
    }

    fun isUsingCustomIntegrator(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsingCustomIntegratorBind, handle)
    }

    fun setCanSleep(ableToSleep: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCanSleepBind, handle, ableToSleep)
    }

    fun isAbleToSleep(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAbleToSleepBind, handle)
    }

    companion object {
        const val DAMP_MODE_COMBINE: Long = 0L
        const val DAMP_MODE_REPLACE: Long = 1L
        const val JOINT_TYPE_NONE: Long = 0L
        const val JOINT_TYPE_PIN: Long = 1L
        const val JOINT_TYPE_CONE: Long = 2L
        const val JOINT_TYPE_HINGE: Long = 3L
        const val JOINT_TYPE_SLIDER: Long = 4L
        const val JOINT_TYPE_6DOF: Long = 5L

        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicalBone3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicalBone3D? =
            if (handle.address() == 0L) null else PhysicalBone3D(handle)

        private const val APPLY_CENTRAL_IMPULSE_HASH = 3460891852L
        private val applyCentralImpulseBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "apply_central_impulse", APPLY_CENTRAL_IMPULSE_HASH)
        }

        private const val APPLY_IMPULSE_HASH = 2754756483L
        private val applyImpulseBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "apply_impulse", APPLY_IMPULSE_HASH)
        }

        private const val SET_JOINT_TYPE_HASH = 2289552604L
        private val setJointTypeBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_joint_type", SET_JOINT_TYPE_HASH)
        }

        private const val GET_JOINT_TYPE_HASH = 931347320L
        private val getJointTypeBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_joint_type", GET_JOINT_TYPE_HASH)
        }

        private const val SET_JOINT_OFFSET_HASH = 2952846383L
        private val setJointOffsetBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_joint_offset", SET_JOINT_OFFSET_HASH)
        }

        private const val GET_JOINT_OFFSET_HASH = 3229777777L
        private val getJointOffsetBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_joint_offset", GET_JOINT_OFFSET_HASH)
        }

        private const val SET_JOINT_ROTATION_HASH = 3460891852L
        private val setJointRotationBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_joint_rotation", SET_JOINT_ROTATION_HASH)
        }

        private const val GET_JOINT_ROTATION_HASH = 3360562783L
        private val getJointRotationBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_joint_rotation", GET_JOINT_ROTATION_HASH)
        }

        private const val SET_BODY_OFFSET_HASH = 2952846383L
        private val setBodyOffsetBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_body_offset", SET_BODY_OFFSET_HASH)
        }

        private const val GET_BODY_OFFSET_HASH = 3229777777L
        private val getBodyOffsetBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_body_offset", GET_BODY_OFFSET_HASH)
        }

        private const val GET_SIMULATE_PHYSICS_HASH = 2240911060L
        private val getSimulatePhysicsBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_simulate_physics", GET_SIMULATE_PHYSICS_HASH)
        }

        private const val IS_SIMULATING_PHYSICS_HASH = 2240911060L
        private val isSimulatingPhysicsBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "is_simulating_physics", IS_SIMULATING_PHYSICS_HASH)
        }

        private const val GET_BONE_ID_HASH = 3905245786L
        private val getBoneIdBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_bone_id", GET_BONE_ID_HASH)
        }

        private const val SET_MASS_HASH = 373806689L
        private val setMassBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_mass", SET_MASS_HASH)
        }

        private const val GET_MASS_HASH = 1740695150L
        private val getMassBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_mass", GET_MASS_HASH)
        }

        private const val SET_FRICTION_HASH = 373806689L
        private val setFrictionBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_friction", SET_FRICTION_HASH)
        }

        private const val GET_FRICTION_HASH = 1740695150L
        private val getFrictionBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_friction", GET_FRICTION_HASH)
        }

        private const val SET_BOUNCE_HASH = 373806689L
        private val setBounceBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_bounce", SET_BOUNCE_HASH)
        }

        private const val GET_BOUNCE_HASH = 1740695150L
        private val getBounceBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_bounce", GET_BOUNCE_HASH)
        }

        private const val SET_GRAVITY_SCALE_HASH = 373806689L
        private val setGravityScaleBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_gravity_scale", SET_GRAVITY_SCALE_HASH)
        }

        private const val GET_GRAVITY_SCALE_HASH = 1740695150L
        private val getGravityScaleBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_gravity_scale", GET_GRAVITY_SCALE_HASH)
        }

        private const val SET_LINEAR_DAMP_MODE_HASH = 1244972221L
        private val setLinearDampModeBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_linear_damp_mode", SET_LINEAR_DAMP_MODE_HASH)
        }

        private const val GET_LINEAR_DAMP_MODE_HASH = 205884699L
        private val getLinearDampModeBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_linear_damp_mode", GET_LINEAR_DAMP_MODE_HASH)
        }

        private const val SET_ANGULAR_DAMP_MODE_HASH = 1244972221L
        private val setAngularDampModeBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_angular_damp_mode", SET_ANGULAR_DAMP_MODE_HASH)
        }

        private const val GET_ANGULAR_DAMP_MODE_HASH = 205884699L
        private val getAngularDampModeBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_angular_damp_mode", GET_ANGULAR_DAMP_MODE_HASH)
        }

        private const val SET_LINEAR_DAMP_HASH = 373806689L
        private val setLinearDampBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_linear_damp", SET_LINEAR_DAMP_HASH)
        }

        private const val GET_LINEAR_DAMP_HASH = 1740695150L
        private val getLinearDampBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_linear_damp", GET_LINEAR_DAMP_HASH)
        }

        private const val SET_ANGULAR_DAMP_HASH = 373806689L
        private val setAngularDampBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_angular_damp", SET_ANGULAR_DAMP_HASH)
        }

        private const val GET_ANGULAR_DAMP_HASH = 1740695150L
        private val getAngularDampBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_angular_damp", GET_ANGULAR_DAMP_HASH)
        }

        private const val SET_LINEAR_VELOCITY_HASH = 3460891852L
        private val setLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_linear_velocity", SET_LINEAR_VELOCITY_HASH)
        }

        private const val GET_LINEAR_VELOCITY_HASH = 3360562783L
        private val getLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_linear_velocity", GET_LINEAR_VELOCITY_HASH)
        }

        private const val SET_ANGULAR_VELOCITY_HASH = 3460891852L
        private val setAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_angular_velocity", SET_ANGULAR_VELOCITY_HASH)
        }

        private const val GET_ANGULAR_VELOCITY_HASH = 3360562783L
        private val getAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_angular_velocity", GET_ANGULAR_VELOCITY_HASH)
        }

        private const val SET_USE_CUSTOM_INTEGRATOR_HASH = 2586408642L
        private val setUseCustomIntegratorBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_use_custom_integrator", SET_USE_CUSTOM_INTEGRATOR_HASH)
        }

        private const val IS_USING_CUSTOM_INTEGRATOR_HASH = 2240911060L
        private val isUsingCustomIntegratorBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "is_using_custom_integrator", IS_USING_CUSTOM_INTEGRATOR_HASH)
        }

        private const val SET_CAN_SLEEP_HASH = 2586408642L
        private val setCanSleepBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_can_sleep", SET_CAN_SLEEP_HASH)
        }

        private const val IS_ABLE_TO_SLEEP_HASH = 36873697L
        private val isAbleToSleepBind by lazy {
            ObjectCalls.getMethodBind("PhysicalBone3D", "is_able_to_sleep", IS_ABLE_TO_SLEEP_HASH)
        }
    }
}
