package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.Vector2

/**
 * A 2D physics body that can't be moved by external forces. When moved manually, it doesn't affect
 * other bodies in its path.
 *
 * Generated from Godot docs: StaticBody2D
 */
open class StaticBody2D(handle: MemorySegment) : PhysicsBody2D(handle) {
    var physicsMaterialOverride: PhysicsMaterial?
        @JvmName("physicsMaterialOverrideProperty")
        get() = getPhysicsMaterialOverride()
        @JvmName("setPhysicsMaterialOverrideProperty")
        set(value) = setPhysicsMaterialOverride(value)

    var constantLinearVelocity: Vector2
        @JvmName("constantLinearVelocityProperty")
        get() = getConstantLinearVelocity()
        @JvmName("setConstantLinearVelocityProperty")
        set(value) = setConstantLinearVelocity(value)

    var constantAngularVelocity: Double
        @JvmName("constantAngularVelocityProperty")
        get() = getConstantAngularVelocity()
        @JvmName("setConstantAngularVelocityProperty")
        set(value) = setConstantAngularVelocity(value)

    fun setConstantLinearVelocity(vel: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setConstantLinearVelocityBind, handle, vel)
    }

    fun setConstantAngularVelocity(vel: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setConstantAngularVelocityBind, handle, vel)
    }

    fun getConstantLinearVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getConstantLinearVelocityBind, handle)
    }

    fun getConstantAngularVelocity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getConstantAngularVelocityBind, handle)
    }

    fun setPhysicsMaterialOverride(physicsMaterialOverride: PhysicsMaterial?) {
        ObjectCalls.ptrcallWithObjectArgs(setPhysicsMaterialOverrideBind, handle, listOf(physicsMaterialOverride?.requireOpenHandle() ?: MemorySegment.NULL))
    }

    fun getPhysicsMaterialOverride(): PhysicsMaterial? {
        return PhysicsMaterial.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPhysicsMaterialOverrideBind, handle))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): StaticBody2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): StaticBody2D? =
            if (handle.address() == 0L) null else StaticBody2D(handle)

        private const val SET_CONSTANT_LINEAR_VELOCITY_HASH = 743155724L
        private val setConstantLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("StaticBody2D", "set_constant_linear_velocity", SET_CONSTANT_LINEAR_VELOCITY_HASH)
        }

        private const val SET_CONSTANT_ANGULAR_VELOCITY_HASH = 373806689L
        private val setConstantAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("StaticBody2D", "set_constant_angular_velocity", SET_CONSTANT_ANGULAR_VELOCITY_HASH)
        }

        private const val GET_CONSTANT_LINEAR_VELOCITY_HASH = 3341600327L
        private val getConstantLinearVelocityBind by lazy {
            ObjectCalls.getMethodBind("StaticBody2D", "get_constant_linear_velocity", GET_CONSTANT_LINEAR_VELOCITY_HASH)
        }

        private const val GET_CONSTANT_ANGULAR_VELOCITY_HASH = 1740695150L
        private val getConstantAngularVelocityBind by lazy {
            ObjectCalls.getMethodBind("StaticBody2D", "get_constant_angular_velocity", GET_CONSTANT_ANGULAR_VELOCITY_HASH)
        }

        private const val SET_PHYSICS_MATERIAL_OVERRIDE_HASH = 1784508650L
        private val setPhysicsMaterialOverrideBind by lazy {
            ObjectCalls.getMethodBind("StaticBody2D", "set_physics_material_override", SET_PHYSICS_MATERIAL_OVERRIDE_HASH)
        }

        private const val GET_PHYSICS_MATERIAL_OVERRIDE_HASH = 2521850424L
        private val getPhysicsMaterialOverrideBind by lazy {
            ObjectCalls.getMethodBind("StaticBody2D", "get_physics_material_override", GET_PHYSICS_MATERIAL_OVERRIDE_HASH)
        }
    }
}
