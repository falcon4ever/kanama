package net.multigesture.kanama.api

import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment
import net.multigesture.kanama.binding.runtime.*

/**
 * Holds physics-related properties of a surface, namely its roughness and bounciness.
 *
 * Generated from Godot docs: PhysicsMaterial
 */
class PhysicsMaterial(handle: GodotHandle) : Resource(handle) {
    var friction: Double
        @JvmName("frictionProperty")
        get() = getFriction()
        @JvmName("setFrictionProperty")
        set(value) = setFriction(value)

    var rough: Boolean
        @JvmName("roughProperty")
        get() = isRough()
        @JvmName("setRoughProperty")
        set(value) = setRough(value)

    var bounce: Double
        @JvmName("bounceProperty")
        get() = getBounce()
        @JvmName("setBounceProperty")
        set(value) = setBounce(value)

    var absorbent: Boolean
        @JvmName("absorbentProperty")
        get() = isAbsorbent()
        @JvmName("setAbsorbentProperty")
        set(value) = setAbsorbent(value)

    /**
     * The body's friction. Values range from `0` (frictionless) to `1` (maximum friction).
     *
     * Generated from Godot docs: PhysicsMaterial.set_friction
     */
    fun setFriction(friction: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setFrictionBind, segment, friction)
    }

    /**
     * The body's friction. Values range from `0` (frictionless) to `1` (maximum friction).
     *
     * Generated from Godot docs: PhysicsMaterial.get_friction
     */
    fun getFriction(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getFrictionBind, segment)
    }

    /**
     * If `true`, the physics engine will use the friction of the object marked as "rough" when two
     * objects collide. If `false`, the physics engine will use the lowest friction of all colliding
     * objects instead. If `true` for both colliding objects, the physics engine will use the highest
     * friction.
     *
     * Generated from Godot docs: PhysicsMaterial.set_rough
     */
    fun setRough(rough: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setRoughBind, segment, rough)
    }

    /**
     * If `true`, the physics engine will use the friction of the object marked as "rough" when two
     * objects collide. If `false`, the physics engine will use the lowest friction of all colliding
     * objects instead. If `true` for both colliding objects, the physics engine will use the highest
     * friction.
     *
     * Generated from Godot docs: PhysicsMaterial.is_rough
     */
    fun isRough(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isRoughBind, segment)
    }

    /**
     * The body's bounciness. Values range from `0` (no bounce) to `1` (full bounciness). Note: Even
     * with `bounce` set to `1.0`, some energy will be lost over time due to linear and angular
     * damping. To have a physics body that preserves all its energy over time, set `bounce` to `1.0`,
     * the body's linear damp mode to Replace (if applicable), its linear damp to `0.0`, its angular
     * damp mode to Replace (if applicable), and its angular damp to `0.0`.
     *
     * Generated from Godot docs: PhysicsMaterial.set_bounce
     */
    fun setBounce(bounce: Double) {
        checkOpen()
        ObjectCalls.ptrcallWithDoubleArg(setBounceBind, segment, bounce)
    }

    /**
     * The body's bounciness. Values range from `0` (no bounce) to `1` (full bounciness). Note: Even
     * with `bounce` set to `1.0`, some energy will be lost over time due to linear and angular
     * damping. To have a physics body that preserves all its energy over time, set `bounce` to `1.0`,
     * the body's linear damp mode to Replace (if applicable), its linear damp to `0.0`, its angular
     * damp mode to Replace (if applicable), and its angular damp to `0.0`.
     *
     * Generated from Godot docs: PhysicsMaterial.get_bounce
     */
    fun getBounce(): Double {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetDouble(getBounceBind, segment)
    }

    /**
     * If `true`, subtracts the bounciness from the colliding object's bounciness instead of adding it.
     *
     * Generated from Godot docs: PhysicsMaterial.set_absorbent
     */
    fun setAbsorbent(absorbent: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setAbsorbentBind, segment, absorbent)
    }

    /**
     * If `true`, subtracts the bounciness from the colliding object's bounciness instead of adding it.
     *
     * Generated from Godot docs: PhysicsMaterial.is_absorbent
     */
    fun isAbsorbent(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isAbsorbentBind, segment)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsMaterial? =
            wrap(handle.segment)

        internal fun wrap(handle: RawSegment): PhysicsMaterial? =
            if (handle.address() == 0L) null else PhysicsMaterial(GodotHandle(handle))

        private const val SET_FRICTION_HASH = 373806689L
        private val setFrictionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsMaterial", "set_friction", SET_FRICTION_HASH)
        }

        private const val GET_FRICTION_HASH = 1740695150L
        private val getFrictionBind by lazy {
            ObjectCalls.getMethodBind("PhysicsMaterial", "get_friction", GET_FRICTION_HASH)
        }

        private const val SET_ROUGH_HASH = 2586408642L
        private val setRoughBind by lazy {
            ObjectCalls.getMethodBind("PhysicsMaterial", "set_rough", SET_ROUGH_HASH)
        }

        private const val IS_ROUGH_HASH = 36873697L
        private val isRoughBind by lazy {
            ObjectCalls.getMethodBind("PhysicsMaterial", "is_rough", IS_ROUGH_HASH)
        }

        private const val SET_BOUNCE_HASH = 373806689L
        private val setBounceBind by lazy {
            ObjectCalls.getMethodBind("PhysicsMaterial", "set_bounce", SET_BOUNCE_HASH)
        }

        private const val GET_BOUNCE_HASH = 1740695150L
        private val getBounceBind by lazy {
            ObjectCalls.getMethodBind("PhysicsMaterial", "get_bounce", GET_BOUNCE_HASH)
        }

        private const val SET_ABSORBENT_HASH = 2586408642L
        private val setAbsorbentBind by lazy {
            ObjectCalls.getMethodBind("PhysicsMaterial", "set_absorbent", SET_ABSORBENT_HASH)
        }

        private const val IS_ABSORBENT_HASH = 36873697L
        private val isAbsorbentBind by lazy {
            ObjectCalls.getMethodBind("PhysicsMaterial", "is_absorbent", IS_ABSORBENT_HASH)
        }
    }
}
