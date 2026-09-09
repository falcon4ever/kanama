package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector2

/**
 * Generated from Godot docs: PhysicsRayQueryParameters2D
 */
class PhysicsRayQueryParameters2D(handle: MemorySegment) : RefCounted(handle) {
    var from: Vector2
        @JvmName("fromProperty")
        get() = getFrom()
        @JvmName("setFromProperty")
        set(value) = setFrom(value)

    var to: Vector2
        @JvmName("toProperty")
        get() = getTo()
        @JvmName("setToProperty")
        set(value) = setTo(value)

    var collisionMask: Long
        @JvmName("collisionMaskProperty")
        get() = getCollisionMask()
        @JvmName("setCollisionMaskProperty")
        set(value) = setCollisionMask(value)

    var collideWithBodies: Boolean
        @JvmName("collideWithBodiesProperty")
        get() = isCollideWithBodiesEnabled()
        @JvmName("setCollideWithBodiesProperty")
        set(value) = setCollideWithBodies(value)

    var collideWithAreas: Boolean
        @JvmName("collideWithAreasProperty")
        get() = isCollideWithAreasEnabled()
        @JvmName("setCollideWithAreasProperty")
        set(value) = setCollideWithAreas(value)

    var hitFromInside: Boolean
        @JvmName("hitFromInsideProperty")
        get() = isHitFromInsideEnabled()
        @JvmName("setHitFromInsideProperty")
        set(value) = setHitFromInside(value)

    fun setFrom(from: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setFromBind, handle, from)
    }

    fun getFrom(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getFromBind, handle)
    }

    fun setTo(to: Vector2) {
        checkOpen()
        ObjectCalls.ptrcallWithVector2Arg(setToBind, handle, to)
    }

    fun getTo(): Vector2 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector2(getToBind, handle)
    }

    fun setCollisionMask(collisionMask: Long) {
        checkOpen()
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, collisionMask)
    }

    fun getCollisionMask(): Long {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    fun setCollideWithBodies(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setCollideWithBodiesBind, handle, enable)
    }

    fun isCollideWithBodiesEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithBodiesEnabledBind, handle)
    }

    fun setCollideWithAreas(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setCollideWithAreasBind, handle, enable)
    }

    fun isCollideWithAreasEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithAreasEnabledBind, handle)
    }

    fun setHitFromInside(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setHitFromInsideBind, handle, enable)
    }

    fun isHitFromInsideEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isHitFromInsideEnabledBind, handle)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): PhysicsRayQueryParameters2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsRayQueryParameters2D? =
            if (handle.address() == 0L) null else PhysicsRayQueryParameters2D(handle)

        private const val SET_FROM_HASH = 743155724L
        private val setFromBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "set_from", SET_FROM_HASH)
        }

        private const val GET_FROM_HASH = 3341600327L
        private val getFromBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "get_from", GET_FROM_HASH)
        }

        private const val SET_TO_HASH = 743155724L
        private val setToBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "set_to", SET_TO_HASH)
        }

        private const val GET_TO_HASH = 3341600327L
        private val getToBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "get_to", GET_TO_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_COLLIDE_WITH_BODIES_HASH = 2586408642L
        private val setCollideWithBodiesBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "set_collide_with_bodies", SET_COLLIDE_WITH_BODIES_HASH)
        }

        private const val IS_COLLIDE_WITH_BODIES_ENABLED_HASH = 36873697L
        private val isCollideWithBodiesEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "is_collide_with_bodies_enabled", IS_COLLIDE_WITH_BODIES_ENABLED_HASH)
        }

        private const val SET_COLLIDE_WITH_AREAS_HASH = 2586408642L
        private val setCollideWithAreasBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "set_collide_with_areas", SET_COLLIDE_WITH_AREAS_HASH)
        }

        private const val IS_COLLIDE_WITH_AREAS_ENABLED_HASH = 36873697L
        private val isCollideWithAreasEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "is_collide_with_areas_enabled", IS_COLLIDE_WITH_AREAS_ENABLED_HASH)
        }

        private const val SET_HIT_FROM_INSIDE_HASH = 2586408642L
        private val setHitFromInsideBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "set_hit_from_inside", SET_HIT_FROM_INSIDE_HASH)
        }

        private const val IS_HIT_FROM_INSIDE_ENABLED_HASH = 36873697L
        private val isHitFromInsideEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "is_hit_from_inside_enabled", IS_HIT_FROM_INSIDE_ENABLED_HASH)
        }
    }
}
