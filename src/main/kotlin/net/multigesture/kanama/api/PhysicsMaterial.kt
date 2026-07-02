package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls

/**
 * Holds physics-related properties of a surface, namely its roughness and bounciness.
 *
 * Generated from Godot docs: PhysicsMaterial
 */
class PhysicsMaterial(handle: MemorySegment) : Resource(handle) {
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

    fun setFriction(friction: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setFrictionBind, handle, friction)
    }

    fun getFriction(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getFrictionBind, handle)
    }

    fun setRough(rough: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setRoughBind, handle, rough)
    }

    fun isRough(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isRoughBind, handle)
    }

    fun setBounce(bounce: Double) {
        ObjectCalls.ptrcallWithDoubleArg(setBounceBind, handle, bounce)
    }

    fun getBounce(): Double {
        return ObjectCalls.ptrcallNoArgsRetDouble(getBounceBind, handle)
    }

    fun setAbsorbent(absorbent: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setAbsorbentBind, handle, absorbent)
    }

    fun isAbsorbent(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isAbsorbentBind, handle)
    }

    companion object {
        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsMaterial? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsMaterial? =
            if (handle.address() == 0L) null else PhysicsMaterial(handle)

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
