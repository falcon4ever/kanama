package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * A 3D physics body for a `VehicleBody3D` that simulates the behavior of a wheel.
 *
 * Generated from Godot docs: VehicleWheel3D
 */
class VehicleWheel3D(handle: MemorySegment) : Node3D(handle) {
    var engineForce: Double
        @JvmName("engineForceProperty")
        get() = getEngineForce()
        @JvmName("setEngineForceProperty")
        set(value) = setEngineForce(value)

    var brake: Double
        @JvmName("brakeProperty")
        get() = getBrake()
        @JvmName("setBrakeProperty")
        set(value) = setBrake(value)

    var steering: Double
        @JvmName("steeringProperty")
        get() = getSteering()
        @JvmName("setSteeringProperty")
        set(value) = setSteering(value)

    var useAsTraction: Boolean
        @JvmName("useAsTractionProperty")
        get() = isUsedAsTraction()
        @JvmName("setUseAsTractionProperty")
        set(value) = setUseAsTraction(value)

    var useAsSteering: Boolean
        @JvmName("useAsSteeringProperty")
        get() = isUsedAsSteering()
        @JvmName("setUseAsSteeringProperty")
        set(value) = setUseAsSteering(value)

    var wheelRollInfluence: Double
        @JvmName("wheelRollInfluenceProperty")
        get() = getRollInfluence()
        @JvmName("setWheelRollInfluenceProperty")
        set(value) = setRollInfluence(value)

    var wheelRadius: Double
        @JvmName("wheelRadiusProperty")
        get() = getRadius()
        @JvmName("setWheelRadiusProperty")
        set(value) = setRadius(value)

    var wheelRestLength: Double
        @JvmName("wheelRestLengthProperty")
        get() = getSuspensionRestLength()
        @JvmName("setWheelRestLengthProperty")
        set(value) = setSuspensionRestLength(value)

    var wheelFrictionSlip: Double
        @JvmName("wheelFrictionSlipProperty")
        get() = getFrictionSlip()
        @JvmName("setWheelFrictionSlipProperty")
        set(value) = setFrictionSlip(value)

    var suspensionTravel: Double
        @JvmName("suspensionTravelProperty")
        get() = getSuspensionTravel()
        @JvmName("setSuspensionTravelProperty")
        set(value) = setSuspensionTravel(value)

    var suspensionStiffness: Double
        @JvmName("suspensionStiffnessProperty")
        get() = getSuspensionStiffness()
        @JvmName("setSuspensionStiffnessProperty")
        set(value) = setSuspensionStiffness(value)

    var suspensionMaxForce: Double
        @JvmName("suspensionMaxForceProperty")
        get() = getSuspensionMaxForce()
        @JvmName("setSuspensionMaxForceProperty")
        set(value) = setSuspensionMaxForce(value)

    var dampingCompression: Double
        @JvmName("dampingCompressionProperty")
        get() = getDampingCompression()
        @JvmName("setDampingCompressionProperty")
        set(value) = setDampingCompression(value)

    var dampingRelaxation: Double
        @JvmName("dampingRelaxationProperty")
        get() = getDampingRelaxation()
        @JvmName("setDampingRelaxationProperty")
        set(value) = setDampingRelaxation(value)

    /**
     * The radius of the wheel in meters.
     *
     * Generated from Godot docs: VehicleWheel3D.set_radius
     */
    fun setRadius(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, length)
    }

    /**
     * The radius of the wheel in meters.
     *
     * Generated from Godot docs: VehicleWheel3D.get_radius
     */
    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    /**
     * This is the distance in meters the wheel is lowered from its origin point. Don't set this to 0.0
     * and move the wheel into position, instead move the origin point of your wheel (the gizmo in
     * Godot) to the position the wheel will take when bottoming out, then use the rest length to move
     * the wheel down to the position it should be in when the car is in rest.
     *
     * Generated from Godot docs: VehicleWheel3D.set_suspension_rest_length
     */
    fun setSuspensionRestLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSuspensionRestLengthBind, handle, length)
    }

    /**
     * This is the distance in meters the wheel is lowered from its origin point. Don't set this to 0.0
     * and move the wheel into position, instead move the origin point of your wheel (the gizmo in
     * Godot) to the position the wheel will take when bottoming out, then use the rest length to move
     * the wheel down to the position it should be in when the car is in rest.
     *
     * Generated from Godot docs: VehicleWheel3D.get_suspension_rest_length
     */
    fun getSuspensionRestLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSuspensionRestLengthBind, handle)
    }

    /**
     * This is the distance the suspension can travel. As Godot units are equivalent to meters, keep
     * this setting relatively low. Try a value between 0.1 and 0.3 depending on the type of car.
     *
     * Generated from Godot docs: VehicleWheel3D.set_suspension_travel
     */
    fun setSuspensionTravel(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSuspensionTravelBind, handle, length)
    }

    /**
     * This is the distance the suspension can travel. As Godot units are equivalent to meters, keep
     * this setting relatively low. Try a value between 0.1 and 0.3 depending on the type of car.
     *
     * Generated from Godot docs: VehicleWheel3D.get_suspension_travel
     */
    fun getSuspensionTravel(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSuspensionTravelBind, handle)
    }

    /**
     * The stiffness of the suspension, measured in Newtons per millimeter (N/mm), or megagrams per
     * second squared (Mg/s²). Use a value lower than 50 for an off-road car, a value between 50 and
     * 100 for a race car and try something around 200 for something like a Formula 1 car.
     *
     * Generated from Godot docs: VehicleWheel3D.set_suspension_stiffness
     */
    fun setSuspensionStiffness(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSuspensionStiffnessBind, handle, length)
    }

    /**
     * The stiffness of the suspension, measured in Newtons per millimeter (N/mm), or megagrams per
     * second squared (Mg/s²). Use a value lower than 50 for an off-road car, a value between 50 and
     * 100 for a race car and try something around 200 for something like a Formula 1 car.
     *
     * Generated from Godot docs: VehicleWheel3D.get_suspension_stiffness
     */
    fun getSuspensionStiffness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSuspensionStiffnessBind, handle)
    }

    /**
     * The maximum force the spring can resist. This value should be higher than a quarter of the
     * `RigidBody3D.mass` of the `VehicleBody3D` or the spring will not carry the weight of the
     * vehicle. Good results are often obtained by a value that is about 3× to 4× this number.
     *
     * Generated from Godot docs: VehicleWheel3D.set_suspension_max_force
     */
    fun setSuspensionMaxForce(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSuspensionMaxForceBind, handle, length)
    }

    /**
     * The maximum force the spring can resist. This value should be higher than a quarter of the
     * `RigidBody3D.mass` of the `VehicleBody3D` or the spring will not carry the weight of the
     * vehicle. Good results are often obtained by a value that is about 3× to 4× this number.
     *
     * Generated from Godot docs: VehicleWheel3D.get_suspension_max_force
     */
    fun getSuspensionMaxForce(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSuspensionMaxForceBind, handle)
    }

    /**
     * The damping applied to the suspension spring when being compressed, meaning when the wheel is
     * moving up relative to the vehicle. It is measured in Newton-seconds per millimeter (N⋅s/mm), or
     * megagrams per second (Mg/s). This value should be between 0.0 (no damping) and 1.0, but may be
     * more. A value of 0.0 means the car will keep bouncing as the spring keeps its energy. A good
     * value for this is around 0.3 for a normal car, 0.5 for a race car.
     *
     * Generated from Godot docs: VehicleWheel3D.set_damping_compression
     */
    fun setDampingCompression(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDampingCompressionBind, handle, length)
    }

    /**
     * The damping applied to the suspension spring when being compressed, meaning when the wheel is
     * moving up relative to the vehicle. It is measured in Newton-seconds per millimeter (N⋅s/mm), or
     * megagrams per second (Mg/s). This value should be between 0.0 (no damping) and 1.0, but may be
     * more. A value of 0.0 means the car will keep bouncing as the spring keeps its energy. A good
     * value for this is around 0.3 for a normal car, 0.5 for a race car.
     *
     * Generated from Godot docs: VehicleWheel3D.get_damping_compression
     */
    fun getDampingCompression(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDampingCompressionBind, handle)
    }

    /**
     * The damping applied to the suspension spring when rebounding or extending, meaning when the
     * wheel is moving down relative to the vehicle. It is measured in Newton-seconds per millimeter
     * (N⋅s/mm), or megagrams per second (Mg/s). This value should be between 0.0 (no damping) and 1.0,
     * but may be more. This value should always be slightly higher than the `damping_compression`
     * property. For a `damping_compression` value of 0.3, try a relaxation value of 0.5.
     *
     * Generated from Godot docs: VehicleWheel3D.set_damping_relaxation
     */
    fun setDampingRelaxation(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDampingRelaxationBind, handle, length)
    }

    /**
     * The damping applied to the suspension spring when rebounding or extending, meaning when the
     * wheel is moving down relative to the vehicle. It is measured in Newton-seconds per millimeter
     * (N⋅s/mm), or megagrams per second (Mg/s). This value should be between 0.0 (no damping) and 1.0,
     * but may be more. This value should always be slightly higher than the `damping_compression`
     * property. For a `damping_compression` value of 0.3, try a relaxation value of 0.5.
     *
     * Generated from Godot docs: VehicleWheel3D.get_damping_relaxation
     */
    fun getDampingRelaxation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDampingRelaxationBind, handle)
    }

    /**
     * If `true`, this wheel transfers engine force to the ground to propel the vehicle forward. This
     * value is used in conjunction with `VehicleBody3D.engine_force` and ignored if you are using the
     * per-wheel `engine_force` value instead.
     *
     * Generated from Godot docs: VehicleWheel3D.set_use_as_traction
     */
    fun setUseAsTraction(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseAsTractionBind, handle, enable)
    }

    /**
     * If `true`, this wheel transfers engine force to the ground to propel the vehicle forward. This
     * value is used in conjunction with `VehicleBody3D.engine_force` and ignored if you are using the
     * per-wheel `engine_force` value instead.
     *
     * Generated from Godot docs: VehicleWheel3D.is_used_as_traction
     */
    fun isUsedAsTraction(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsedAsTractionBind, handle)
    }

    /**
     * If `true`, this wheel will be turned when the car steers. This value is used in conjunction with
     * `VehicleBody3D.steering` and ignored if you are using the per-wheel `steering` value instead.
     *
     * Generated from Godot docs: VehicleWheel3D.set_use_as_steering
     */
    fun setUseAsSteering(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseAsSteeringBind, handle, enable)
    }

    /**
     * If `true`, this wheel will be turned when the car steers. This value is used in conjunction with
     * `VehicleBody3D.steering` and ignored if you are using the per-wheel `steering` value instead.
     *
     * Generated from Godot docs: VehicleWheel3D.is_used_as_steering
     */
    fun isUsedAsSteering(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsedAsSteeringBind, handle)
    }

    /**
     * This determines how much grip this wheel has. It is combined with the friction setting of the
     * surface the wheel is in contact with. 0.0 means no grip, 1.0 is normal grip. For a drift car
     * setup, try setting the grip of the rear wheels slightly lower than the front wheels, or use a
     * lower value to simulate tire wear. It's best to set this to 1.0 when starting out.
     *
     * Generated from Godot docs: VehicleWheel3D.set_friction_slip
     */
    fun setFrictionSlip(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFrictionSlipBind, handle, length)
    }

    /**
     * This determines how much grip this wheel has. It is combined with the friction setting of the
     * surface the wheel is in contact with. 0.0 means no grip, 1.0 is normal grip. For a drift car
     * setup, try setting the grip of the rear wheels slightly lower than the front wheels, or use a
     * lower value to simulate tire wear. It's best to set this to 1.0 when starting out.
     *
     * Generated from Godot docs: VehicleWheel3D.get_friction_slip
     */
    fun getFrictionSlip(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFrictionSlipBind, handle)
    }

    /**
     * Returns `true` if this wheel is in contact with a surface.
     *
     * Generated from Godot docs: VehicleWheel3D.is_in_contact
     */
    fun isInContact(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInContactBind, handle)
    }

    /**
     * Returns the contacting body node if valid in the tree, as `Node3D`. At the moment, `GridMap` is
     * not supported so the node will be always of type `PhysicsBody3D`. Returns `null` if the wheel is
     * not in contact with a surface, or the contact body is not a `PhysicsBody3D`.
     *
     * Generated from Godot docs: VehicleWheel3D.get_contact_body
     */
    fun getContactBody(): Node3D? {
        return Node3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getContactBodyBind, handle))
    }

    /**
     * Returns the point of the suspension's collision in world space if the wheel is in contact. If
     * the wheel isn't in contact with anything, returns the maximum point of the wheel's ray cast in
     * world space, which is defined by `wheel_rest_length + wheel_radius`.
     *
     * Generated from Godot docs: VehicleWheel3D.get_contact_point
     */
    fun getContactPoint(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getContactPointBind, handle)
    }

    /**
     * Returns the normal of the suspension's collision in world space if the wheel is in contact. If
     * the wheel isn't in contact with anything, returns a vector pointing directly along the
     * suspension axis toward the vehicle in world space.
     *
     * Generated from Godot docs: VehicleWheel3D.get_contact_normal
     */
    fun getContactNormal(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getContactNormalBind, handle)
    }

    /**
     * This value affects the roll of your vehicle. If set to 1.0 for all wheels, your vehicle will
     * resist body roll, while a value of 0.0 will be prone to rolling over.
     *
     * Generated from Godot docs: VehicleWheel3D.set_roll_influence
     */
    fun setRollInfluence(rollInfluence: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRollInfluenceBind, handle, rollInfluence)
    }

    /**
     * This value affects the roll of your vehicle. If set to 1.0 for all wheels, your vehicle will
     * resist body roll, while a value of 0.0 will be prone to rolling over.
     *
     * Generated from Godot docs: VehicleWheel3D.get_roll_influence
     */
    fun getRollInfluence(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRollInfluenceBind, handle)
    }

    /**
     * Returns a value between 0.0 and 1.0 that indicates whether this wheel is skidding. 0.0 is
     * skidding (the wheel has lost grip, e.g. icy terrain), 1.0 means not skidding (the wheel has full
     * grip, e.g. dry asphalt road).
     *
     * Generated from Godot docs: VehicleWheel3D.get_skidinfo
     */
    fun getSkidinfo(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSkidinfoBind, handle)
    }

    /**
     * Returns the rotational speed of the wheel in revolutions per minute.
     *
     * Generated from Godot docs: VehicleWheel3D.get_rpm
     */
    fun getRpm(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRpmBind, handle)
    }

    /**
     * Accelerates the wheel by applying an engine force. The wheel is only sped up if it is in contact
     * with a surface. The `RigidBody3D.mass` of the vehicle has an effect on the acceleration of the
     * vehicle. For a vehicle with a mass set to 1000, try a value in the 25 - 50 range for
     * acceleration. Note: The simulation does not take the effect of gears into account, you will need
     * to add logic for this if you wish to simulate gears. A negative value will result in the wheel
     * reversing.
     *
     * Generated from Godot docs: VehicleWheel3D.set_engine_force
     */
    fun setEngineForce(engineForce: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEngineForceBind, handle, engineForce)
    }

    /**
     * Accelerates the wheel by applying an engine force. The wheel is only sped up if it is in contact
     * with a surface. The `RigidBody3D.mass` of the vehicle has an effect on the acceleration of the
     * vehicle. For a vehicle with a mass set to 1000, try a value in the 25 - 50 range for
     * acceleration. Note: The simulation does not take the effect of gears into account, you will need
     * to add logic for this if you wish to simulate gears. A negative value will result in the wheel
     * reversing.
     *
     * Generated from Godot docs: VehicleWheel3D.get_engine_force
     */
    fun getEngineForce(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEngineForceBind, handle)
    }

    /**
     * Slows down the wheel by applying a braking force. The wheel is only slowed down if it is in
     * contact with a surface. The force you need to apply to adequately slow down your vehicle depends
     * on the `RigidBody3D.mass` of the vehicle. For a vehicle with a mass set to 1000, try a value in
     * the 25 - 30 range for hard braking.
     *
     * Generated from Godot docs: VehicleWheel3D.set_brake
     */
    fun setBrake(brake: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBrakeBind, handle, brake)
    }

    /**
     * Slows down the wheel by applying a braking force. The wheel is only slowed down if it is in
     * contact with a surface. The force you need to apply to adequately slow down your vehicle depends
     * on the `RigidBody3D.mass` of the vehicle. For a vehicle with a mass set to 1000, try a value in
     * the 25 - 30 range for hard braking.
     *
     * Generated from Godot docs: VehicleWheel3D.get_brake
     */
    fun getBrake(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBrakeBind, handle)
    }

    /**
     * The steering angle for the wheel, in radians. Setting this to a non-zero value will result in
     * the vehicle turning when it's moving.
     *
     * Generated from Godot docs: VehicleWheel3D.set_steering
     */
    fun setSteering(steering: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSteeringBind, handle, steering)
    }

    /**
     * The steering angle for the wheel, in radians. Setting this to a non-zero value will result in
     * the vehicle turning when it's moving.
     *
     * Generated from Godot docs: VehicleWheel3D.get_steering
     */
    fun getSteering(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSteeringBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): VehicleWheel3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VehicleWheel3D? =
            if (handle.address() == 0L) null else VehicleWheel3D(handle)

        private const val SET_RADIUS_HASH = 373806689L
        private val setRadiusBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_radius", SET_RADIUS_HASH)
        }

        private const val GET_RADIUS_HASH = 1740695150L
        private val getRadiusBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_radius", GET_RADIUS_HASH)
        }

        private const val SET_SUSPENSION_REST_LENGTH_HASH = 373806689L
        private val setSuspensionRestLengthBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_suspension_rest_length", SET_SUSPENSION_REST_LENGTH_HASH)
        }

        private const val GET_SUSPENSION_REST_LENGTH_HASH = 1740695150L
        private val getSuspensionRestLengthBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_suspension_rest_length", GET_SUSPENSION_REST_LENGTH_HASH)
        }

        private const val SET_SUSPENSION_TRAVEL_HASH = 373806689L
        private val setSuspensionTravelBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_suspension_travel", SET_SUSPENSION_TRAVEL_HASH)
        }

        private const val GET_SUSPENSION_TRAVEL_HASH = 1740695150L
        private val getSuspensionTravelBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_suspension_travel", GET_SUSPENSION_TRAVEL_HASH)
        }

        private const val SET_SUSPENSION_STIFFNESS_HASH = 373806689L
        private val setSuspensionStiffnessBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_suspension_stiffness", SET_SUSPENSION_STIFFNESS_HASH)
        }

        private const val GET_SUSPENSION_STIFFNESS_HASH = 1740695150L
        private val getSuspensionStiffnessBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_suspension_stiffness", GET_SUSPENSION_STIFFNESS_HASH)
        }

        private const val SET_SUSPENSION_MAX_FORCE_HASH = 373806689L
        private val setSuspensionMaxForceBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_suspension_max_force", SET_SUSPENSION_MAX_FORCE_HASH)
        }

        private const val GET_SUSPENSION_MAX_FORCE_HASH = 1740695150L
        private val getSuspensionMaxForceBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_suspension_max_force", GET_SUSPENSION_MAX_FORCE_HASH)
        }

        private const val SET_DAMPING_COMPRESSION_HASH = 373806689L
        private val setDampingCompressionBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_damping_compression", SET_DAMPING_COMPRESSION_HASH)
        }

        private const val GET_DAMPING_COMPRESSION_HASH = 1740695150L
        private val getDampingCompressionBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_damping_compression", GET_DAMPING_COMPRESSION_HASH)
        }

        private const val SET_DAMPING_RELAXATION_HASH = 373806689L
        private val setDampingRelaxationBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_damping_relaxation", SET_DAMPING_RELAXATION_HASH)
        }

        private const val GET_DAMPING_RELAXATION_HASH = 1740695150L
        private val getDampingRelaxationBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_damping_relaxation", GET_DAMPING_RELAXATION_HASH)
        }

        private const val SET_USE_AS_TRACTION_HASH = 2586408642L
        private val setUseAsTractionBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_use_as_traction", SET_USE_AS_TRACTION_HASH)
        }

        private const val IS_USED_AS_TRACTION_HASH = 36873697L
        private val isUsedAsTractionBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "is_used_as_traction", IS_USED_AS_TRACTION_HASH)
        }

        private const val SET_USE_AS_STEERING_HASH = 2586408642L
        private val setUseAsSteeringBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_use_as_steering", SET_USE_AS_STEERING_HASH)
        }

        private const val IS_USED_AS_STEERING_HASH = 36873697L
        private val isUsedAsSteeringBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "is_used_as_steering", IS_USED_AS_STEERING_HASH)
        }

        private const val SET_FRICTION_SLIP_HASH = 373806689L
        private val setFrictionSlipBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_friction_slip", SET_FRICTION_SLIP_HASH)
        }

        private const val GET_FRICTION_SLIP_HASH = 1740695150L
        private val getFrictionSlipBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_friction_slip", GET_FRICTION_SLIP_HASH)
        }

        private const val IS_IN_CONTACT_HASH = 36873697L
        private val isInContactBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "is_in_contact", IS_IN_CONTACT_HASH)
        }

        private const val GET_CONTACT_BODY_HASH = 151077316L
        private val getContactBodyBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_contact_body", GET_CONTACT_BODY_HASH)
        }

        private const val GET_CONTACT_POINT_HASH = 3360562783L
        private val getContactPointBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_contact_point", GET_CONTACT_POINT_HASH)
        }

        private const val GET_CONTACT_NORMAL_HASH = 3360562783L
        private val getContactNormalBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_contact_normal", GET_CONTACT_NORMAL_HASH)
        }

        private const val SET_ROLL_INFLUENCE_HASH = 373806689L
        private val setRollInfluenceBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_roll_influence", SET_ROLL_INFLUENCE_HASH)
        }

        private const val GET_ROLL_INFLUENCE_HASH = 1740695150L
        private val getRollInfluenceBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_roll_influence", GET_ROLL_INFLUENCE_HASH)
        }

        private const val GET_SKIDINFO_HASH = 1740695150L
        private val getSkidinfoBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_skidinfo", GET_SKIDINFO_HASH)
        }

        private const val GET_RPM_HASH = 1740695150L
        private val getRpmBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_rpm", GET_RPM_HASH)
        }

        private const val SET_ENGINE_FORCE_HASH = 373806689L
        private val setEngineForceBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_engine_force", SET_ENGINE_FORCE_HASH)
        }

        private const val GET_ENGINE_FORCE_HASH = 1740695150L
        private val getEngineForceBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_engine_force", GET_ENGINE_FORCE_HASH)
        }

        private const val SET_BRAKE_HASH = 373806689L
        private val setBrakeBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_brake", SET_BRAKE_HASH)
        }

        private const val GET_BRAKE_HASH = 1740695150L
        private val getBrakeBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_brake", GET_BRAKE_HASH)
        }

        private const val SET_STEERING_HASH = 373806689L
        private val setSteeringBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "set_steering", SET_STEERING_HASH)
        }

        private const val GET_STEERING_HASH = 1740695150L
        private val getSteeringBind by lazy {
            ObjectCalls.getMethodBind("VehicleWheel3D", "get_steering", GET_STEERING_HASH)
        }
    }
}
