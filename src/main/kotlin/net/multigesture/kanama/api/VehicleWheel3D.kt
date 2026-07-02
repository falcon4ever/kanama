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

    fun setRadius(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRadiusBind, handle, length)
    }

    fun getRadius(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRadiusBind, handle)
    }

    fun setSuspensionRestLength(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSuspensionRestLengthBind, handle, length)
    }

    fun getSuspensionRestLength(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSuspensionRestLengthBind, handle)
    }

    fun setSuspensionTravel(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSuspensionTravelBind, handle, length)
    }

    fun getSuspensionTravel(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSuspensionTravelBind, handle)
    }

    fun setSuspensionStiffness(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSuspensionStiffnessBind, handle, length)
    }

    fun getSuspensionStiffness(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSuspensionStiffnessBind, handle)
    }

    fun setSuspensionMaxForce(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSuspensionMaxForceBind, handle, length)
    }

    fun getSuspensionMaxForce(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSuspensionMaxForceBind, handle)
    }

    fun setDampingCompression(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDampingCompressionBind, handle, length)
    }

    fun getDampingCompression(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDampingCompressionBind, handle)
    }

    fun setDampingRelaxation(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setDampingRelaxationBind, handle, length)
    }

    fun getDampingRelaxation(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getDampingRelaxationBind, handle)
    }

    fun setUseAsTraction(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseAsTractionBind, handle, enable)
    }

    fun isUsedAsTraction(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsedAsTractionBind, handle)
    }

    fun setUseAsSteering(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setUseAsSteeringBind, handle, enable)
    }

    fun isUsedAsSteering(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isUsedAsSteeringBind, handle)
    }

    fun setFrictionSlip(length: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFrictionSlipBind, handle, length)
    }

    fun getFrictionSlip(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFrictionSlipBind, handle)
    }

    fun isInContact(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isInContactBind, handle)
    }

    fun getContactBody(): Node3D? {
        return Node3D.wrap(ObjectCalls.ptrcallNoArgsRetObject(getContactBodyBind, handle))
    }

    fun getContactPoint(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getContactPointBind, handle)
    }

    fun getContactNormal(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getContactNormalBind, handle)
    }

    fun setRollInfluence(rollInfluence: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setRollInfluenceBind, handle, rollInfluence)
    }

    fun getRollInfluence(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRollInfluenceBind, handle)
    }

    fun getSkidinfo(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getSkidinfoBind, handle)
    }

    fun getRpm(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getRpmBind, handle)
    }

    fun setEngineForce(engineForce: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEngineForceBind, handle, engineForce)
    }

    fun getEngineForce(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEngineForceBind, handle)
    }

    fun setBrake(brake: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBrakeBind, handle, brake)
    }

    fun getBrake(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBrakeBind, handle)
    }

    fun setSteering(steering: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSteeringBind, handle, steering)
    }

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
