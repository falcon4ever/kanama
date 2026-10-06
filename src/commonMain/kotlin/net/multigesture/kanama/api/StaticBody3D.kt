package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector3

/**
 * A 3D physics body that can't be moved by external forces. When moved manually, it doesn't affect
 * other bodies in its path.
 *
 * Generated from Godot docs: StaticBody3D
 */
open class StaticBody3D(handle: GodotHandle) : PhysicsBody3D(handle) {
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

    /**
     * The body's constant linear velocity. This does not move the body, but affects touching bodies,
     * as if it were moving.
     *
     * Generated from Godot docs: StaticBody3D.set_constant_linear_velocity
     */
    fun setConstantLinearVelocity(vel: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setConstantLinearVelocityBind, segment, vel)
    }

    /**
     * The body's constant angular velocity. This does not rotate the body, but affects touching
     * bodies, as if it were rotating.
     *
     * Generated from Godot docs: StaticBody3D.set_constant_angular_velocity
     */
    fun setConstantAngularVelocity(vel: Vector3) {
        ObjectCalls.ptrcallWithVector3Arg(Binds.setConstantAngularVelocityBind, segment, vel)
    }

    /**
     * The body's constant linear velocity. This does not move the body, but affects touching bodies,
     * as if it were moving.
     *
     * Generated from Godot docs: StaticBody3D.get_constant_linear_velocity
     */
    fun getConstantLinearVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getConstantLinearVelocityBind, segment)
    }

    /**
     * The body's constant angular velocity. This does not rotate the body, but affects touching
     * bodies, as if it were rotating.
     *
     * Generated from Godot docs: StaticBody3D.get_constant_angular_velocity
     */
    fun getConstantAngularVelocity(): Vector3 {
        return ObjectCalls.ptrcallNoArgsRetVector3(Binds.getConstantAngularVelocityBind, segment)
    }

    /**
     * The physics material override for the body. If a material is assigned to this property, it will
     * be used instead of any other physics material, such as an inherited one.
     *
     * Generated from Godot docs: StaticBody3D.set_physics_material_override
     */
    fun setPhysicsMaterialOverride(physicsMaterialOverride: PhysicsMaterial?) {
        ObjectCalls.ptrcallWithObjectArgs(Binds.setPhysicsMaterialOverrideBind, segment, listOf(physicsMaterialOverride?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The physics material override for the body. If a material is assigned to this property, it will
     * be used instead of any other physics material, such as an inherited one.
     *
     * Generated from Godot docs: StaticBody3D.get_physics_material_override
     */
    fun getPhysicsMaterialOverride(): PhysicsMaterial? {
        return PhysicsMaterial.wrapOwned(ObjectCalls.ptrcallNoArgsRetObject(Binds.getPhysicsMaterialOverrideBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StaticBody3D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): StaticBody3D? =
            if (handle.address() == 0L) null else StaticBody3D(GodotHandle(handle))
    }

    private object Binds {
        private const val SET_CONSTANT_LINEAR_VELOCITY_HASH = 3460891852L
        @JvmField
        val setConstantLinearVelocityBind =
            ObjectCalls.getMethodBind("StaticBody3D", "set_constant_linear_velocity", SET_CONSTANT_LINEAR_VELOCITY_HASH)

        private const val SET_CONSTANT_ANGULAR_VELOCITY_HASH = 3460891852L
        @JvmField
        val setConstantAngularVelocityBind =
            ObjectCalls.getMethodBind("StaticBody3D", "set_constant_angular_velocity", SET_CONSTANT_ANGULAR_VELOCITY_HASH)

        private const val GET_CONSTANT_LINEAR_VELOCITY_HASH = 3360562783L
        @JvmField
        val getConstantLinearVelocityBind =
            ObjectCalls.getMethodBind("StaticBody3D", "get_constant_linear_velocity", GET_CONSTANT_LINEAR_VELOCITY_HASH)

        private const val GET_CONSTANT_ANGULAR_VELOCITY_HASH = 3360562783L
        @JvmField
        val getConstantAngularVelocityBind =
            ObjectCalls.getMethodBind("StaticBody3D", "get_constant_angular_velocity", GET_CONSTANT_ANGULAR_VELOCITY_HASH)

        private const val SET_PHYSICS_MATERIAL_OVERRIDE_HASH = 1784508650L
        @JvmField
        val setPhysicsMaterialOverrideBind =
            ObjectCalls.getMethodBind("StaticBody3D", "set_physics_material_override", SET_PHYSICS_MATERIAL_OVERRIDE_HASH)

        private const val GET_PHYSICS_MATERIAL_OVERRIDE_HASH = 2521850424L
        @JvmField
        val getPhysicsMaterialOverrideBind =
            ObjectCalls.getMethodBind("StaticBody3D", "get_physics_material_override", GET_PHYSICS_MATERIAL_OVERRIDE_HASH)
    }
}
