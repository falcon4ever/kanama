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

    /**
     * Accelerates the vehicle by applying an engine force. The vehicle is only sped up if the wheels
     * that have `VehicleWheel3D.use_as_traction` set to `true` and are in contact with a surface. The
     * `RigidBody3D.mass` of the vehicle has an effect on the acceleration of the vehicle. For a
     * vehicle with a mass set to 1000, try a value in the 25 - 50 range for acceleration. Note: The
     * simulation does not take the effect of gears into account, you will need to add logic for this
     * if you wish to simulate gears. A negative value will result in the vehicle reversing.
     *
     * Generated from Godot docs: VehicleBody3D.set_engine_force
     */
    fun setEngineForce(engineForce: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setEngineForceBind, handle, engineForce)
    }

    /**
     * Accelerates the vehicle by applying an engine force. The vehicle is only sped up if the wheels
     * that have `VehicleWheel3D.use_as_traction` set to `true` and are in contact with a surface. The
     * `RigidBody3D.mass` of the vehicle has an effect on the acceleration of the vehicle. For a
     * vehicle with a mass set to 1000, try a value in the 25 - 50 range for acceleration. Note: The
     * simulation does not take the effect of gears into account, you will need to add logic for this
     * if you wish to simulate gears. A negative value will result in the vehicle reversing.
     *
     * Generated from Godot docs: VehicleBody3D.get_engine_force
     */
    fun getEngineForce(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getEngineForceBind, handle)
    }

    /**
     * Slows down the vehicle by applying a braking force. The vehicle is only slowed down if the
     * wheels are in contact with a surface. The force you need to apply to adequately slow down your
     * vehicle depends on the `RigidBody3D.mass` of the vehicle. For a vehicle with a mass set to 1000,
     * try a value in the 25 - 30 range for hard braking.
     *
     * Generated from Godot docs: VehicleBody3D.set_brake
     */
    fun setBrake(brake: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBrakeBind, handle, brake)
    }

    /**
     * Slows down the vehicle by applying a braking force. The vehicle is only slowed down if the
     * wheels are in contact with a surface. The force you need to apply to adequately slow down your
     * vehicle depends on the `RigidBody3D.mass` of the vehicle. For a vehicle with a mass set to 1000,
     * try a value in the 25 - 30 range for hard braking.
     *
     * Generated from Godot docs: VehicleBody3D.get_brake
     */
    fun getBrake(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBrakeBind, handle)
    }

    /**
     * The steering angle for the vehicle. Setting this to a non-zero value will result in the vehicle
     * turning when it's moving. Wheels that have `VehicleWheel3D.use_as_steering` set to `true` will
     * automatically be rotated. Note: This property is edited in the inspector in degrees. In code the
     * property is set in radians.
     *
     * Generated from Godot docs: VehicleBody3D.set_steering
     */
    fun setSteering(steering: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setSteeringBind, handle, steering)
    }

    /**
     * The steering angle for the vehicle. Setting this to a non-zero value will result in the vehicle
     * turning when it's moving. Wheels that have `VehicleWheel3D.use_as_steering` set to `true` will
     * automatically be rotated. Note: This property is edited in the inspector in degrees. In code the
     * property is set in radians.
     *
     * Generated from Godot docs: VehicleBody3D.get_steering
     */
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
