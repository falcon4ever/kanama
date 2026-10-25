package net.multigesture.kanama.api

import kotlin.jvm.JvmField
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.RawSegment

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
        ObjectCalls.ptrcallWithDoubleArg(Binds.setFrictionBind, segment, friction)
        ObjectCalls.keepReachable(this)
    }

    /**
     * The body's friction. Values range from `0` (frictionless) to `1` (maximum friction).
     *
     * Generated from Godot docs: PhysicsMaterial.get_friction
     */
    fun getFriction(): Double {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getFrictionBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
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
        ObjectCalls.ptrcallWithBoolArg(Binds.setRoughBind, segment, rough)
        ObjectCalls.keepReachable(this)
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
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isRoughBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
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
        ObjectCalls.ptrcallWithDoubleArg(Binds.setBounceBind, segment, bounce)
        ObjectCalls.keepReachable(this)
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
        val ret = ObjectCalls.ptrcallNoArgsRetDouble(Binds.getBounceBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    /**
     * If `true`, subtracts the bounciness from the colliding object's bounciness instead of adding it.
     *
     * Generated from Godot docs: PhysicsMaterial.set_absorbent
     */
    fun setAbsorbent(absorbent: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(Binds.setAbsorbentBind, segment, absorbent)
        ObjectCalls.keepReachable(this)
    }

    /**
     * If `true`, subtracts the bounciness from the colliding object's bounciness instead of adding it.
     *
     * Generated from Godot docs: PhysicsMaterial.is_absorbent
     */
    fun isAbsorbent(): Boolean {
        checkOpen()
        val ret = ObjectCalls.ptrcallNoArgsRetBool(Binds.isAbsorbentBind, segment)
        ObjectCalls.keepReachable(this)
        return ret
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: GodotHandle): PhysicsMaterial? =
            wrapBorrowed(handle.segment)

        internal fun wrapOwned(handle: RawSegment): PhysicsMaterial? =
            if (handle.address() == 0L) null else RefCounted.owned(PhysicsMaterial(GodotHandle(handle)))

        internal fun wrapBorrowed(handle: RawSegment): PhysicsMaterial? =
            if (handle.address() == 0L) null else PhysicsMaterial(GodotHandle(handle))

        /** A new PhysicsMaterial, GDScript's `PhysicsMaterial.new()`. You own the reference: `close()` or `use { }`. */
        @JvmStatic
        fun create(): PhysicsMaterial =
            RefCounted.owned(PhysicsMaterial(GodotHandle(ObjectCalls.constructObject("PhysicsMaterial"))))
    }

    private object Binds {
        private const val SET_FRICTION_HASH = 373806689L
        @JvmField
        val setFrictionBind =
            ObjectCalls.getMethodBind("PhysicsMaterial", "set_friction", SET_FRICTION_HASH)

        private const val GET_FRICTION_HASH = 1740695150L
        @JvmField
        val getFrictionBind =
            ObjectCalls.getMethodBind("PhysicsMaterial", "get_friction", GET_FRICTION_HASH)

        private const val SET_ROUGH_HASH = 2586408642L
        @JvmField
        val setRoughBind =
            ObjectCalls.getMethodBind("PhysicsMaterial", "set_rough", SET_ROUGH_HASH)

        private const val IS_ROUGH_HASH = 36873697L
        @JvmField
        val isRoughBind =
            ObjectCalls.getMethodBind("PhysicsMaterial", "is_rough", IS_ROUGH_HASH)

        private const val SET_BOUNCE_HASH = 373806689L
        @JvmField
        val setBounceBind =
            ObjectCalls.getMethodBind("PhysicsMaterial", "set_bounce", SET_BOUNCE_HASH)

        private const val GET_BOUNCE_HASH = 1740695150L
        @JvmField
        val getBounceBind =
            ObjectCalls.getMethodBind("PhysicsMaterial", "get_bounce", GET_BOUNCE_HASH)

        private const val SET_ABSORBENT_HASH = 2586408642L
        @JvmField
        val setAbsorbentBind =
            ObjectCalls.getMethodBind("PhysicsMaterial", "set_absorbent", SET_ABSORBENT_HASH)

        private const val IS_ABSORBENT_HASH = 36873697L
        @JvmField
        val isAbsorbentBind =
            ObjectCalls.getMethodBind("PhysicsMaterial", "is_absorbent", IS_ABSORBENT_HASH)
    }
}
