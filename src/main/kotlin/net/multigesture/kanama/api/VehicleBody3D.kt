package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * A 3D physics body that simulates the behavior of a car.
 *
 * Generated from Godot docs: VehicleBody3D
 */
class VehicleBody3D(handle: MemorySegment) : RigidBody3D(handle) {
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
        fun fromHandle(handle: MemorySegment): VehicleBody3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): VehicleBody3D? =
            if (handle.address() == 0L) null else VehicleBody3D(handle)

        private const val SET_ENGINE_FORCE_HASH = 373806689L
        private val setEngineForceBind by lazy {
            ObjectCalls.getMethodBind("VehicleBody3D", "set_engine_force", SET_ENGINE_FORCE_HASH)
        }

        private const val GET_ENGINE_FORCE_HASH = 1740695150L
        private val getEngineForceBind by lazy {
            ObjectCalls.getMethodBind("VehicleBody3D", "get_engine_force", GET_ENGINE_FORCE_HASH)
        }

        private const val SET_BRAKE_HASH = 373806689L
        private val setBrakeBind by lazy {
            ObjectCalls.getMethodBind("VehicleBody3D", "set_brake", SET_BRAKE_HASH)
        }

        private const val GET_BRAKE_HASH = 1740695150L
        private val getBrakeBind by lazy {
            ObjectCalls.getMethodBind("VehicleBody3D", "get_brake", GET_BRAKE_HASH)
        }

        private const val SET_STEERING_HASH = 373806689L
        private val setSteeringBind by lazy {
            ObjectCalls.getMethodBind("VehicleBody3D", "set_steering", SET_STEERING_HASH)
        }

        private const val GET_STEERING_HASH = 1740695150L
        private val getSteeringBind by lazy {
            ObjectCalls.getMethodBind("VehicleBody3D", "get_steering", GET_STEERING_HASH)
        }
    }
}
