package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.binding.runtime.*
import net.multigesture.kanama.types.Vector3

/**
 * Generated from Godot docs: PhysicsRayQueryParameters3D
 */
class PhysicsRayQueryParameters3D(handle: MemorySegment) : RefCounted(handle) {
    var from: Vector3
        @JvmName("fromProperty")
        get() = getFrom()
        @JvmName("setFromProperty")
        set(value) = setFrom(value)

    var to: Vector3
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

    var hitBackFaces: Boolean
        @JvmName("hitBackFacesProperty")
        get() = isHitBackFacesEnabled()
        @JvmName("setHitBackFacesProperty")
        set(value) = setHitBackFaces(value)

    fun setFrom(from: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setFromBind, handle, from)
    }

    fun getFrom(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getFromBind, handle)
    }

    fun setTo(to: Vector3) {
        checkOpen()
        ObjectCalls.ptrcallWithVector3Arg(setToBind, handle, to)
    }

    fun getTo(): Vector3 {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetVector3(getToBind, handle)
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

    fun setHitBackFaces(enable: Boolean) {
        checkOpen()
        ObjectCalls.ptrcallWithBoolArg(setHitBackFacesBind, handle, enable)
    }

    fun isHitBackFacesEnabled(): Boolean {
        checkOpen()
        return ObjectCalls.ptrcallNoArgsRetBool(isHitBackFacesEnabledBind, handle)
    }

    // The RID list excluded from collisions (e.g. the caster's own body). Marshalled to a Godot
    // Array[RID] by the C-shim. set_exclude takes an Array[RID] arg the generator otherwise skips.
    fun setExclude(exclude: List<net.multigesture.kanama.types.RID>) {
        checkOpen()
        ObjectCalls.ptrcallWithRIDListArg(setExcludeBind, handle, exclude)
    }

    companion object {
        fun fromHandle(handle: MemorySegment): PhysicsRayQueryParameters3D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsRayQueryParameters3D? =
            if (handle.address() == 0L) null else PhysicsRayQueryParameters3D(handle)

        // Build a ray query: instantiate and set the scalar/Vector3 properties + the exclude RID-list
        // (marshalled through the Array[RID] C-shim so intersect_ray skips the caster's own collider).
        fun create(
            from: Vector3,
            to: Vector3,
            collisionMask: Long = 4294967295L,
            exclude: List<net.multigesture.kanama.types.RID> = emptyList(),
        ): PhysicsRayQueryParameters3D {
            val query = PhysicsRayQueryParameters3D(MemorySegment.ofAddress(IosGodot.constructObject("PhysicsRayQueryParameters3D")))
            query.from = from
            query.to = to
            query.collisionMask = collisionMask
            if (exclude.isNotEmpty()) query.setExclude(exclude)
            return query
        }

        private const val SET_EXCLUDE_HASH = 381264803L
        private val setExcludeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "set_exclude", SET_EXCLUDE_HASH)
        }

        private const val SET_FROM_HASH = 3460891852L
        private val setFromBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "set_from", SET_FROM_HASH)
        }

        private const val GET_FROM_HASH = 3360562783L
        private val getFromBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "get_from", GET_FROM_HASH)
        }

        private const val SET_TO_HASH = 3460891852L
        private val setToBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "set_to", SET_TO_HASH)
        }

        private const val GET_TO_HASH = 3360562783L
        private val getToBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "get_to", GET_TO_HASH)
        }

        private const val SET_COLLISION_MASK_HASH = 1286410249L
        private val setCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "set_collision_mask", SET_COLLISION_MASK_HASH)
        }

        private const val GET_COLLISION_MASK_HASH = 3905245786L
        private val getCollisionMaskBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "get_collision_mask", GET_COLLISION_MASK_HASH)
        }

        private const val SET_COLLIDE_WITH_BODIES_HASH = 2586408642L
        private val setCollideWithBodiesBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "set_collide_with_bodies", SET_COLLIDE_WITH_BODIES_HASH)
        }

        private const val IS_COLLIDE_WITH_BODIES_ENABLED_HASH = 36873697L
        private val isCollideWithBodiesEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "is_collide_with_bodies_enabled", IS_COLLIDE_WITH_BODIES_ENABLED_HASH)
        }

        private const val SET_COLLIDE_WITH_AREAS_HASH = 2586408642L
        private val setCollideWithAreasBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "set_collide_with_areas", SET_COLLIDE_WITH_AREAS_HASH)
        }

        private const val IS_COLLIDE_WITH_AREAS_ENABLED_HASH = 36873697L
        private val isCollideWithAreasEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "is_collide_with_areas_enabled", IS_COLLIDE_WITH_AREAS_ENABLED_HASH)
        }

        private const val SET_HIT_FROM_INSIDE_HASH = 2586408642L
        private val setHitFromInsideBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "set_hit_from_inside", SET_HIT_FROM_INSIDE_HASH)
        }

        private const val IS_HIT_FROM_INSIDE_ENABLED_HASH = 36873697L
        private val isHitFromInsideEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "is_hit_from_inside_enabled", IS_HIT_FROM_INSIDE_ENABLED_HASH)
        }

        private const val SET_HIT_BACK_FACES_HASH = 2586408642L
        private val setHitBackFacesBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "set_hit_back_faces", SET_HIT_BACK_FACES_HASH)
        }

        private const val IS_HIT_BACK_FACES_ENABLED_HASH = 36873697L
        private val isHitBackFacesEnabledBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters3D", "is_hit_back_faces_enabled", IS_HIT_BACK_FACES_ENABLED_HASH)
        }
    }
}
