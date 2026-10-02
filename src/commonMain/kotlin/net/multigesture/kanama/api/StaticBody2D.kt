package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.NULL_SEGMENT
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.types.Vector2

/**
 * A 2D physics body that can't be moved by external forces. When moved manually, it doesn't affect
 * other bodies in its path.
 *
 * Generated from Godot docs: StaticBody2D
 */
open class StaticBody2D(handle: GodotHandle) : PhysicsBody2D(handle) {
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

    /**
     * The body's constant linear velocity. This does not move the body, but affects touching bodies,
     * as if it were moving.
     *
     * Generated from Godot docs: StaticBody2D.set_constant_linear_velocity
     */
    fun setConstantLinearVelocity(vel: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setConstantLinearVelocityBind, segment, vel)
    }

    /**
     * The body's constant angular velocity. This does not rotate the body, but affects touching
     * bodies, as if it were rotating.
     *
     * Generated from Godot docs: StaticBody2D.set_constant_angular_velocity
     */
    fun setConstantAngularVelocity(vel: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setConstantAngularVelocityBind, segment, vel)
    }

    /**
     * The body's constant linear velocity. This does not move the body, but affects touching bodies,
     * as if it were moving.
     *
     * Generated from Godot docs: StaticBody2D.get_constant_linear_velocity
     */
    fun getConstantLinearVelocity(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getConstantLinearVelocityBind, segment)
    }

    /**
     * The body's constant angular velocity. This does not rotate the body, but affects touching
     * bodies, as if it were rotating.
     *
     * Generated from Godot docs: StaticBody2D.get_constant_angular_velocity
     */
    fun getConstantAngularVelocity(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getConstantAngularVelocityBind, segment)
    }

    /**
     * The physics material override for the body. If a material is assigned to this property, it will
     * be used instead of any other physics material, such as an inherited one.
     *
     * Generated from Godot docs: StaticBody2D.set_physics_material_override
     */
    fun setPhysicsMaterialOverride(physicsMaterialOverride: PhysicsMaterial?) {
        ObjectCalls.ptrcallWithObjectArgs(setPhysicsMaterialOverrideBind, segment, listOf(physicsMaterialOverride?.requireOpenHandle() ?: NULL_SEGMENT))
    }

    /**
     * The physics material override for the body. If a material is assigned to this property, it will
     * be used instead of any other physics material, such as an inherited one.
     *
     * Generated from Godot docs: StaticBody2D.get_physics_material_override
     */
    fun getPhysicsMaterialOverride(): PhysicsMaterial? {
        return PhysicsMaterial.wrap(ObjectCalls.ptrcallNoArgsRetObject(getPhysicsMaterialOverrideBind, segment))
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): StaticBody2D? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): StaticBody2D? =
            if (handle.address() == 0L) null else StaticBody2D(GodotHandle(handle))

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
