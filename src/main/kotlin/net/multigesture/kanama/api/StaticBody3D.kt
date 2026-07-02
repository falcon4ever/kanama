package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector3

/**
 * A 3D physics body that can't be moved by external forces. When moved manually, it doesn't affect
 * other bodies in its path.
 *
 * Generated from Godot docs: StaticBody3D
 */
open class StaticBody3D(handle: MemorySegment) : PhysicsBody3D(handle) {
    var physicsMaterialOverride: PhysicsMaterial?
        @JvmName("physicsMaterialOverrideProperty")
        get() = getPhysicsMaterialOverride()
        @JvmName("setPhysicsMaterialOverrideProperty")
        set(value) = setPhysicsMaterialOverride(value)

    var constantLinearVelocity: Vector3
        @JvmName("constantLinearVelocityProperty")
        get() = getConstantLinearVelocity()
        @JvmName("setConstantLinearVelocityProperty")
        set(value) = setConstantLinearVelocity(value)

    var constantAngularVelocity: Vector3
        @JvmName("constantAngularVelocityProperty")
        get() = getConstantAngularVelocity()
        @JvmName("setConstantAngularVelocityProperty")
        set(value) = setConstantAngularVelocity(value)

    fun setConstantLinearVelocity(vel: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setConstantLinearVelocityBind, handle, vel)
    }

    fun setConstantAngularVelocity(vel: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(setConstantAngularVelocityBind, handle, vel)
    }

    fun getConstantLinearVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getConstantLinearVelocityBind, handle)
    }

    fun getConstantAngularVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(getConstantAngularVelocityBind, handle)
    }

    fun setPhysicsMaterialOverride(physicsMaterialOverride: PhysicsMaterial?) {
        ObjectCalls.ptrcallWithObjectArgs(setPhysicsMaterialOverrideBind, handle, listOf(physicsMaterialOverride?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getPhysicsMaterialOverride(): PhysicsMaterial? {
        return PhysicsMaterial.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPhysicsMaterialOverrideBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): StaticBody3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StaticBody3D? =
            if (handle.address() == 0L) null else StaticBody3D(handle)

        private const val SET_CONSTANT_LINEAR_VELOCITY_HASH = 3460891852L
        private val setConstantLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("StaticBody3D", "set_constant_linear_velocity", SET_CONSTANT_LINEAR_VELOCITY_HASH)
        }

        private const val SET_CONSTANT_ANGULAR_VELOCITY_HASH = 3460891852L
        private val setConstantAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("StaticBody3D", "set_constant_angular_velocity", SET_CONSTANT_ANGULAR_VELOCITY_HASH)
        }

        private const val GET_CONSTANT_LINEAR_VELOCITY_HASH = 3360562783L
        private val getConstantLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("StaticBody3D", "get_constant_linear_velocity", GET_CONSTANT_LINEAR_VELOCITY_HASH)
        }

        private const val GET_CONSTANT_ANGULAR_VELOCITY_HASH = 3360562783L
        private val getConstantAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("StaticBody3D", "get_constant_angular_velocity", GET_CONSTANT_ANGULAR_VELOCITY_HASH)
        }

        private const val SET_PHYSICS_MATERIAL_OVERRIDE_HASH = 1784508650L
        private val setPhysicsMaterialOverrideBind by lazy {
            ObjectCalls.getMethodBind("StaticBody3D", "set_physics_material_override", SET_PHYSICS_MATERIAL_OVERRIDE_HASH)
        }

        private const val GET_PHYSICS_MATERIAL_OVERRIDE_HASH = 2521850424L
        private val getPhysicsMaterialOverrideBind by lazy {
            ObjectCalls.getMethodBind("StaticBody3D", "get_physics_material_override", GET_PHYSICS_MATERIAL_OVERRIDE_HASH)
        }
    }
}
