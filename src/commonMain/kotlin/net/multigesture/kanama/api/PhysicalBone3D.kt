package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmInline
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Transform3D
import net.multigesture.kanama.types.Vector3

/**
 * A physics body used to make bones in a `Skeleton3D` react to physics.
 *
 * Generated from Godot docs: PhysicalBone3D
 */
class PhysicalBone3D(handle: GodotHandle) : PhysicsBody3D(handle) {
    var jointType: PhysicalBone3D.JointType
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

    var linearDampMode: PhysicalBone3D.DampMode
        @JvmName("linearDampModeProperty")
        get() = getLinearDampMode()
        @JvmName("setLinearDampModeProperty")
        set(value) = setLinearDampMode(value)

    var linearDamp: Double
        @JvmName("linearDampProperty")
        get() = getLinearDamp()
        @JvmName("setLinearDampProperty")
        set(value) = setLinearDamp(value)

    var angularDampMode: PhysicalBone3D.DampMode
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

    /**
     * Applies a directional impulse without affecting rotation. An impulse is time-independent!
     * Applying an impulse every frame would result in a framerate-dependent force. For this reason, it
     * should only be used when simulating one-time impacts (use the "_integrate_forces" functions
     * otherwise). This is equivalent to using `apply_impulse` at the body's center of mass.
     *
     * Generated from Godot docs: PhysicalBone3D.apply_central_impulse
     */
    fun applyCentralImpulse(impulse: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.applyCentralImpulseBind, segment, impulse)
    }

    /**
     * Applies a positioned impulse to the PhysicsBone3D. An impulse is time-independent! Applying an
     * impulse every frame would result in a framerate-dependent force. For this reason, it should only
     * be used when simulating one-time impacts (use the "_integrate_forces" functions otherwise).
     * `position` is the offset from the PhysicsBone3D origin in global coordinates.
     *
     * Generated from Godot docs: PhysicalBone3D.apply_impulse
     */
    fun applyImpulse(impulse: Vector3, position: Vector3) {
        ObjectCalls.ptrcallWithTwoVector3Args(Binds.applyImpulseBind, segment, impulse, position)
    }

    /**
     * Sets the joint type.
     *
     * Generated from Godot docs: PhysicalBone3D.set_joint_type
     */
    fun setJointType(jointType: PhysicalBone3D.JointType) {
        ObjectCalls.ptrcallWithLongArg(Binds.setJointTypeBind, segment, jointType.value)
    }

    /**
     * Sets the joint type.
     *
     * Generated from Godot docs: PhysicalBone3D.get_joint_type
     */
    fun getJointType(): PhysicalBone3D.JointType {
        return PhysicalBone3D.JointType(ObjectCalls.ptrcallNoArgsRetLong(Binds.getJointTypeBind, segment))
    }

    /**
     * Sets the joint's transform.
     *
     * Generated from Godot docs: PhysicalBone3D.set_joint_offset
     */
    fun setJointOffset(offset: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(Binds.setJointOffsetBind, segment, offset)
    }

    /**
     * Sets the joint's transform.
     *
     * Generated from Godot docs: PhysicalBone3D.get_joint_offset
     */
    fun getJointOffset(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(Binds.getJointOffsetBind, segment)
    }

    /**
     * Sets the joint's rotation in radians.
     *
     * Generated from Godot docs: PhysicalBone3D.set_joint_rotation
     */
    fun setJointRotation(euler: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setJointRotationBind, segment, euler)
    }

    /**
     * Sets the joint's rotation in radians.
     *
     * Generated from Godot docs: PhysicalBone3D.get_joint_rotation
     */
    fun getJointRotation(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getJointRotationBind, segment)
    }

    /**
     * Sets the body's transform.
     *
     * Generated from Godot docs: PhysicalBone3D.set_body_offset
     */
    fun setBodyOffset(offset: Transform3D) {
        ObjectCalls.ptrcallWithTransform3DArg(Binds.setBodyOffsetBind, segment, offset)
    }

    /**
     * Sets the body's transform.
     *
     * Generated from Godot docs: PhysicalBone3D.get_body_offset
     */
    fun getBodyOffset(): Transform3D {
        return ObjectCalls.ptrcallNoArgsRetTransform3D(Binds.getBodyOffsetBind, segment)
    }

    /**
     * Returns `true` if the PhysicsBone3D is allowed to simulate physics.
     *
     * Generated from Godot docs: PhysicalBone3D.get_simulate_physics
     */
    fun getSimulatePhysics(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.getSimulatePhysicsBind, segment)
    }

    /**
     * Returns `true` if the PhysicsBone3D is currently simulating physics.
     *
     * Generated from Godot docs: PhysicalBone3D.is_simulating_physics
     */
    fun isSimulatingPhysics(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isSimulatingPhysicsBind, segment)
    }

    /**
     * Returns the unique identifier of the PhysicsBone3D.
     *
     * Generated from Godot docs: PhysicalBone3D.get_bone_id
     */
    fun getBoneId(): Int {
        return ObjectCalls.ptrcallNoArgsRetInt(Binds.getBoneIdBind, segment)
    }

    /**
     * The body's mass.
     *
     * Generated from Godot docs: PhysicalBone3D.set_mass
     */
    fun setMass(mass: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setMassBind, segment, mass)
    }

    /**
     * The body's mass.
     *
     * Generated from Godot docs: PhysicalBone3D.get_mass
     */
    fun getMass(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getMassBind, segment)
    }

    /**
     * The body's friction, from `0` (frictionless) to `1` (max friction).
     *
     * Generated from Godot docs: PhysicalBone3D.set_friction
     */
    fun setFriction(friction: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFrictionBind, segment, friction)
    }

    /**
     * The body's friction, from `0` (frictionless) to `1` (max friction).
     *
     * Generated from Godot docs: PhysicalBone3D.get_friction
     */
    fun getFriction(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFrictionBind, segment)
    }

    /**
     * The body's bounciness. Values range from `0` (no bounce) to `1` (full bounciness). Note: Even
     * with `bounce` set to `1.0`, some energy will be lost over time due to linear and angular
     * damping. To have a `PhysicalBone3D` that preserves all its energy over time, set `bounce` to
     * `1.0`, `linear_damp_mode` to `DampMode.REPLACE`, `linear_damp` to `0.0`, `angular_damp_mode` to
     * `DampMode.REPLACE`, and `angular_damp` to `0.0`.
     *
     * Generated from Godot docs: PhysicalBone3D.set_bounce
     */
    fun setBounce(bounce: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setBounceBind, segment, bounce)
    }

    /**
     * The body's bounciness. Values range from `0` (no bounce) to `1` (full bounciness). Note: Even
     * with `bounce` set to `1.0`, some energy will be lost over time due to linear and angular
     * damping. To have a `PhysicalBone3D` that preserves all its energy over time, set `bounce` to
     * `1.0`, `linear_damp_mode` to `DampMode.REPLACE`, `linear_damp` to `0.0`, `angular_damp_mode` to
     * `DampMode.REPLACE`, and `angular_damp` to `0.0`.
     *
     * Generated from Godot docs: PhysicalBone3D.get_bounce
     */
    fun getBounce(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getBounceBind, segment)
    }

    /**
     * This is multiplied by `ProjectSettings.physics/3d/default_gravity` to produce this body's
     * gravity. For example, a value of `1.0` will apply normal gravity, `2.0` will apply double the
     * gravity, and `0.5` will apply half the gravity to this body.
     *
     * Generated from Godot docs: PhysicalBone3D.set_gravity_scale
     */
    fun setGravityScale(gravityScale: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setGravityScaleBind, segment, gravityScale)
    }

    /**
     * This is multiplied by `ProjectSettings.physics/3d/default_gravity` to produce this body's
     * gravity. For example, a value of `1.0` will apply normal gravity, `2.0` will apply double the
     * gravity, and `0.5` will apply half the gravity to this body.
     *
     * Generated from Godot docs: PhysicalBone3D.get_gravity_scale
     */
    fun getGravityScale(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getGravityScaleBind, segment)
    }

    /**
     * Defines how `linear_damp` is applied.
     *
     * Generated from Godot docs: PhysicalBone3D.set_linear_damp_mode
     */
    fun setLinearDampMode(linearDampMode: PhysicalBone3D.DampMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setLinearDampModeBind, segment, linearDampMode.value)
    }

    /**
     * Defines how `linear_damp` is applied.
     *
     * Generated from Godot docs: PhysicalBone3D.get_linear_damp_mode
     */
    fun getLinearDampMode(): PhysicalBone3D.DampMode {
        return PhysicalBone3D.DampMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getLinearDampModeBind, segment))
    }

    /**
     * Defines how `angular_damp` is applied.
     *
     * Generated from Godot docs: PhysicalBone3D.set_angular_damp_mode
     */
    fun setAngularDampMode(angularDampMode: PhysicalBone3D.DampMode) {
        ObjectCalls.ptrcallWithLongArg(Binds.setAngularDampModeBind, segment, angularDampMode.value)
    }

    /**
     * Defines how `angular_damp` is applied.
     *
     * Generated from Godot docs: PhysicalBone3D.get_angular_damp_mode
     */
    fun getAngularDampMode(): PhysicalBone3D.DampMode {
        return PhysicalBone3D.DampMode(ObjectCalls.ptrcallNoArgsRetLong(Binds.getAngularDampModeBind, segment))
    }

    /**
     * Damps the body's movement. By default, the body will use
     * `ProjectSettings.physics/3d/default_linear_damp` or any value override set by an `Area3D` the
     * body is in. Depending on `linear_damp_mode`, `linear_damp` may be added to or replace the body's
     * damping value. See `ProjectSettings.physics/3d/default_linear_damp` for more details about
     * damping.
     *
     * Generated from Godot docs: PhysicalBone3D.set_linear_damp
     */
    fun setLinearDamp(linearDamp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setLinearDampBind, segment, linearDamp)
    }

    /**
     * Damps the body's movement. By default, the body will use
     * `ProjectSettings.physics/3d/default_linear_damp` or any value override set by an `Area3D` the
     * body is in. Depending on `linear_damp_mode`, `linear_damp` may be added to or replace the body's
     * damping value. See `ProjectSettings.physics/3d/default_linear_damp` for more details about
     * damping.
     *
     * Generated from Godot docs: PhysicalBone3D.get_linear_damp
     */
    fun getLinearDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getLinearDampBind, segment)
    }

    /**
     * Damps the body's rotation. By default, the body will use the
     * `ProjectSettings.physics/3d/default_angular_damp` project setting or any value override set by
     * an `Area3D` the body is in. Depending on `angular_damp_mode`, you can set `angular_damp` to be
     * added to or to replace the body's damping value. See
     * `ProjectSettings.physics/3d/default_angular_damp` for more details about damping.
     *
     * Generated from Godot docs: PhysicalBone3D.set_angular_damp
     */
    fun setAngularDamp(angularDamp: Double) {
        ObjectCalls.ptrcallWithDoubleArg(Binds.setAngularDampBind, segment, angularDamp)
    }

    /**
     * Damps the body's rotation. By default, the body will use the
     * `ProjectSettings.physics/3d/default_angular_damp` project setting or any value override set by
     * an `Area3D` the body is in. Depending on `angular_damp_mode`, you can set `angular_damp` to be
     * added to or to replace the body's damping value. See
     * `ProjectSettings.physics/3d/default_angular_damp` for more details about damping.
     *
     * Generated from Godot docs: PhysicalBone3D.get_angular_damp
     */
    fun getAngularDamp(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(Binds.getAngularDampBind, segment)
    }

    /**
     * The body's linear velocity in units per second. Can be used sporadically, but don't set this
     * every frame, because physics may run in another thread and runs at a different granularity. Use
     * `_integrate_forces` as your process loop for precise control of the body state.
     *
     * Generated from Godot docs: PhysicalBone3D.set_linear_velocity
     */
    fun setLinearVelocity(linearVelocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setLinearVelocityBind, segment, linearVelocity)
    }

    /**
     * The body's linear velocity in units per second. Can be used sporadically, but don't set this
     * every frame, because physics may run in another thread and runs at a different granularity. Use
     * `_integrate_forces` as your process loop for precise control of the body state.
     *
     * Generated from Godot docs: PhysicalBone3D.get_linear_velocity
     */
    fun getLinearVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getLinearVelocityBind, segment)
    }

    /**
     * The PhysicalBone3D's rotational velocity in radians per second.
     *
     * Generated from Godot docs: PhysicalBone3D.set_angular_velocity
     */
    fun setAngularVelocity(angularVelocity: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setAngularVelocityBind, segment, angularVelocity)
    }

    /**
     * The PhysicalBone3D's rotational velocity in radians per second.
     *
     * Generated from Godot docs: PhysicalBone3D.get_angular_velocity
     */
    fun getAngularVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getAngularVelocityBind, segment)
    }

    /**
     * If `true`, the standard force integration (like gravity or damping) will be disabled for this
     * body. Other than collision response, the body will only move as determined by the
     * `_integrate_forces` method, if that virtual method is overridden. Setting this property will
     * call the method `PhysicsServer3D.body_set_omit_force_integration` internally.
     *
     * Generated from Godot docs: PhysicalBone3D.set_use_custom_integrator
     */
    fun setUseCustomIntegrator(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setUseCustomIntegratorBind, segment, enable)
    }

    /**
     * If `true`, the standard force integration (like gravity or damping) will be disabled for this
     * body. Other than collision response, the body will only move as determined by the
     * `_integrate_forces` method, if that virtual method is overridden. Setting this property will
     * call the method `PhysicsServer3D.body_set_omit_force_integration` internally.
     *
     * Generated from Godot docs: PhysicalBone3D.is_using_custom_integrator
     */
    fun isUsingCustomIntegrator(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isUsingCustomIntegratorBind, segment)
    }

    /**
     * If `true`, the body is deactivated when there is no movement, so it will not take part in the
     * simulation until it is awakened by an external force.
     *
     * Generated from Godot docs: PhysicalBone3D.set_can_sleep
     */
    fun setCanSleep(ableToSleep: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(Binds.setCanSleepBind, segment, ableToSleep)
    }

    /**
     * If `true`, the body is deactivated when there is no movement, so it will not take part in the
     * simulation until it is awakened by an external force.
     *
     * Generated from Godot docs: PhysicalBone3D.is_able_to_sleep
     */
    fun isAbleToSleep(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(Binds.isAbleToSleepBind, segment)
    }

    /**
     * Godot's `PhysicalBone3D.DampMode` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`PhysicalBone3D.DampMode.<NAME>`).
     *
     * Generated from Godot docs: PhysicalBone3D.DampMode
     */
    @JvmInline
    value class DampMode(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * In this mode, the body's damping value is added to any value set in areas or the default value.
             *
             * Generated from Godot docs: PhysicalBone3D.DAMP_MODE_COMBINE
             */
            val COMBINE: DampMode get() = DampMode(0L)
            /**
             * In this mode, the body's damping value replaces any value set in areas or the default value.
             *
             * Generated from Godot docs: PhysicalBone3D.DAMP_MODE_REPLACE
             */
            val REPLACE: DampMode get() = DampMode(1L)
        }
    }

    /**
     * Godot's `PhysicalBone3D.JointType` enum as a typed value: `.value` is the raw number Godot uses,
     * and the companion holds the named values (`PhysicalBone3D.JointType.<NAME>`).
     *
     * Generated from Godot docs: PhysicalBone3D.JointType
     */
    @JvmInline
    value class JointType(override val value: Long) : GodotEnumValue {
        companion object {
            /**
             * No joint is applied to the PhysicsBone3D.
             *
             * Generated from Godot docs: PhysicalBone3D.JOINT_TYPE_NONE
             */
            val NONE: JointType get() = JointType(0L)
            /**
             * A pin joint is applied to the PhysicsBone3D.
             *
             * Generated from Godot docs: PhysicalBone3D.JOINT_TYPE_PIN
             */
            val PIN: JointType get() = JointType(1L)
            /**
             * A cone joint is applied to the PhysicsBone3D.
             *
             * Generated from Godot docs: PhysicalBone3D.JOINT_TYPE_CONE
             */
            val CONE: JointType get() = JointType(2L)
            /**
             * A hinge joint is applied to the PhysicsBone3D.
             *
             * Generated from Godot docs: PhysicalBone3D.JOINT_TYPE_HINGE
             */
            val HINGE: JointType get() = JointType(3L)
            /**
             * A slider joint is applied to the PhysicsBone3D.
             *
             * Generated from Godot docs: PhysicalBone3D.JOINT_TYPE_SLIDER
             */
            val SLIDER: JointType get() = JointType(4L)
            /**
             * A 6 degrees of freedom joint is applied to the PhysicsBone3D.
             *
             * Generated from Godot docs: PhysicalBone3D.JOINT_TYPE_6DOF
             */
            val TYPE_6DOF: JointType get() = JointType(5L)
        }
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicalBone3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PhysicalBone3D? =
            if (handle.address() == 0L) null else PhysicalBone3D(GodotHandle(handle))
    }

    private object Binds {
        private const val APPLY_CENTRAL_IMPULSE_HASH = 3460891852L
        @JvmField
        val applyCentralImpulseBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "apply_central_impulse", APPLY_CENTRAL_IMPULSE_HASH)

        private const val APPLY_IMPULSE_HASH = 2754756483L
        @JvmField
        val applyImpulseBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "apply_impulse", APPLY_IMPULSE_HASH)

        private const val SET_JOINT_TYPE_HASH = 2289552604L
        @JvmField
        val setJointTypeBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_joint_type", SET_JOINT_TYPE_HASH)

        private const val GET_JOINT_TYPE_HASH = 931347320L
        @JvmField
        val getJointTypeBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_joint_type", GET_JOINT_TYPE_HASH)

        private const val SET_JOINT_OFFSET_HASH = 2952846383L
        @JvmField
        val setJointOffsetBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_joint_offset", SET_JOINT_OFFSET_HASH)

        private const val GET_JOINT_OFFSET_HASH = 3229777777L
        @JvmField
        val getJointOffsetBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_joint_offset", GET_JOINT_OFFSET_HASH)

        private const val SET_JOINT_ROTATION_HASH = 3460891852L
        @JvmField
        val setJointRotationBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_joint_rotation", SET_JOINT_ROTATION_HASH)

        private const val GET_JOINT_ROTATION_HASH = 3360562783L
        @JvmField
        val getJointRotationBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_joint_rotation", GET_JOINT_ROTATION_HASH)

        private const val SET_BODY_OFFSET_HASH = 2952846383L
        @JvmField
        val setBodyOffsetBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_body_offset", SET_BODY_OFFSET_HASH)

        private const val GET_BODY_OFFSET_HASH = 3229777777L
        @JvmField
        val getBodyOffsetBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_body_offset", GET_BODY_OFFSET_HASH)

        private const val GET_SIMULATE_PHYSICS_HASH = 2240911060L
        @JvmField
        val getSimulatePhysicsBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_simulate_physics", GET_SIMULATE_PHYSICS_HASH)

        private const val IS_SIMULATING_PHYSICS_HASH = 2240911060L
        @JvmField
        val isSimulatingPhysicsBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "is_simulating_physics", IS_SIMULATING_PHYSICS_HASH)

        private const val GET_BONE_ID_HASH = 3905245786L
        @JvmField
        val getBoneIdBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_bone_id", GET_BONE_ID_HASH)

        private const val SET_MASS_HASH = 373806689L
        @JvmField
        val setMassBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_mass", SET_MASS_HASH)

        private const val GET_MASS_HASH = 1740695150L
        @JvmField
        val getMassBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_mass", GET_MASS_HASH)

        private const val SET_FRICTION_HASH = 373806689L
        @JvmField
        val setFrictionBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_friction", SET_FRICTION_HASH)

        private const val GET_FRICTION_HASH = 1740695150L
        @JvmField
        val getFrictionBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_friction", GET_FRICTION_HASH)

        private const val SET_BOUNCE_HASH = 373806689L
        @JvmField
        val setBounceBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_bounce", SET_BOUNCE_HASH)

        private const val GET_BOUNCE_HASH = 1740695150L
        @JvmField
        val getBounceBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_bounce", GET_BOUNCE_HASH)

        private const val SET_GRAVITY_SCALE_HASH = 373806689L
        @JvmField
        val setGravityScaleBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_gravity_scale", SET_GRAVITY_SCALE_HASH)

        private const val GET_GRAVITY_SCALE_HASH = 1740695150L
        @JvmField
        val getGravityScaleBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_gravity_scale", GET_GRAVITY_SCALE_HASH)

        private const val SET_LINEAR_DAMP_MODE_HASH = 1244972221L
        @JvmField
        val setLinearDampModeBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_linear_damp_mode", SET_LINEAR_DAMP_MODE_HASH)

        private const val GET_LINEAR_DAMP_MODE_HASH = 205884699L
        @JvmField
        val getLinearDampModeBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_linear_damp_mode", GET_LINEAR_DAMP_MODE_HASH)

        private const val SET_ANGULAR_DAMP_MODE_HASH = 1244972221L
        @JvmField
        val setAngularDampModeBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_angular_damp_mode", SET_ANGULAR_DAMP_MODE_HASH)

        private const val GET_ANGULAR_DAMP_MODE_HASH = 205884699L
        @JvmField
        val getAngularDampModeBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_angular_damp_mode", GET_ANGULAR_DAMP_MODE_HASH)

        private const val SET_LINEAR_DAMP_HASH = 373806689L
        @JvmField
        val setLinearDampBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_linear_damp", SET_LINEAR_DAMP_HASH)

        private const val GET_LINEAR_DAMP_HASH = 1740695150L
        @JvmField
        val getLinearDampBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_linear_damp", GET_LINEAR_DAMP_HASH)

        private const val SET_ANGULAR_DAMP_HASH = 373806689L
        @JvmField
        val setAngularDampBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_angular_damp", SET_ANGULAR_DAMP_HASH)

        private const val GET_ANGULAR_DAMP_HASH = 1740695150L
        @JvmField
        val getAngularDampBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_angular_damp", GET_ANGULAR_DAMP_HASH)

        private const val SET_LINEAR_VELOCITY_HASH = 3460891852L
        @JvmField
        val setLinearVelocityBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_linear_velocity", SET_LINEAR_VELOCITY_HASH)

        private const val GET_LINEAR_VELOCITY_HASH = 3360562783L
        @JvmField
        val getLinearVelocityBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_linear_velocity", GET_LINEAR_VELOCITY_HASH)

        private const val SET_ANGULAR_VELOCITY_HASH = 3460891852L
        @JvmField
        val setAngularVelocityBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_angular_velocity", SET_ANGULAR_VELOCITY_HASH)

        private const val GET_ANGULAR_VELOCITY_HASH = 3360562783L
        @JvmField
        val getAngularVelocityBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "get_angular_velocity", GET_ANGULAR_VELOCITY_HASH)

        private const val SET_USE_CUSTOM_INTEGRATOR_HASH = 2586408642L
        @JvmField
        val setUseCustomIntegratorBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_use_custom_integrator", SET_USE_CUSTOM_INTEGRATOR_HASH)

        private const val IS_USING_CUSTOM_INTEGRATOR_HASH = 2240911060L
        @JvmField
        val isUsingCustomIntegratorBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "is_using_custom_integrator", IS_USING_CUSTOM_INTEGRATOR_HASH)

        private const val SET_CAN_SLEEP_HASH = 2586408642L
        @JvmField
        val setCanSleepBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "set_can_sleep", SET_CAN_SLEEP_HASH)

        private const val IS_ABLE_TO_SLEEP_HASH = 36873697L
        @JvmField
        val isAbleToSleepBind =
            ObjectCalls.getMethodBind("PhysicalBone3D", "is_able_to_sleep", IS_ABLE_TO_SLEEP_HASH)
    }
}
