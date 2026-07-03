package net.multigesture.kanama.api

import java.lang.foreign.MemorySegment
import kotlin.jvm.JvmName
import net.multigesture.kanama.binding.runtime.ObjectCalls
import net.multigesture.kanama.types.RID
import net.multigesture.kanama.types.Vector2

/**
 * Provides parameters for `PhysicsDirectSpaceState2D.intersect_ray`.
 *
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

    var exclude: List<RID>
        @JvmName("excludeProperty")
        get() = getExclude()
        @JvmName("setExcludeProperty")
        set(value) = setExclude(value)

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

    /**
     * The starting point of the ray being queried for, in global coordinates.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.set_from
     */
    fun setFrom(from: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setFromBind, handle, from)
    }

    /**
     * The starting point of the ray being queried for, in global coordinates.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.get_from
     */
    fun getFrom(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getFromBind, handle)
    }

    /**
     * The ending point of the ray being queried for, in global coordinates.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.set_to
     */
    fun setTo(to: Vector2) {
        ObjectCalls.ptrcallWithVector2Arg(setToBind, handle, to)
    }

    /**
     * The ending point of the ray being queried for, in global coordinates.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.get_to
     */
    fun getTo(): Vector2 {
        return ObjectCalls.ptrcallNoArgsRetVector2(getToBind, handle)
    }

    /**
     * The physics layers the query will detect (as a bitmask). By default, all collision layers are
     * detected. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.set_collision_mask
     */
    fun setCollisionMask(collisionMask: Long) {
        ObjectCalls.ptrcallWithUInt32Arg(setCollisionMaskBind, handle, collisionMask)
    }

    /**
     * The physics layers the query will detect (as a bitmask). By default, all collision layers are
     * detected. See Collision layers and masks
     * ($DOCS_URL/tutorials/physics/physics_introduction.html#collision-layers-and-masks) in the
     * documentation for more information.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.get_collision_mask
     */
    fun getCollisionMask(): Long {
        return ObjectCalls.ptrcallNoArgsRetUInt32(getCollisionMaskBind, handle)
    }

    /**
     * The list of object `RID`s that will be excluded from collisions. Use `CollisionObject2D.get_rid`
     * to get the `RID` associated with a `CollisionObject2D`-derived node. Note: The returned array is
     * copied and any changes to it will not update the original property value. To update the value
     * you need to modify the returned array, and then assign it to the property again.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.set_exclude
     */
    fun setExclude(exclude: List<RID>) {
        ObjectCalls.ptrcallWithRIDListArg(setExcludeBind, handle, exclude)
    }

    /**
     * The list of object `RID`s that will be excluded from collisions. Use `CollisionObject2D.get_rid`
     * to get the `RID` associated with a `CollisionObject2D`-derived node. Note: The returned array is
     * copied and any changes to it will not update the original property value. To update the value
     * you need to modify the returned array, and then assign it to the property again.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.get_exclude
     */
    fun getExclude(): List<RID> {
        return ObjectCalls.ptrcallNoArgsRetRIDList(getExcludeBind, handle)
    }

    /**
     * If `true`, the query will take `PhysicsBody2D`s into account.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.set_collide_with_bodies
     */
    fun setCollideWithBodies(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollideWithBodiesBind, handle, enable)
    }

    /**
     * If `true`, the query will take `PhysicsBody2D`s into account.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.is_collide_with_bodies_enabled
     */
    fun isCollideWithBodiesEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithBodiesEnabledBind, handle)
    }

    /**
     * If `true`, the query will take `Area2D`s into account.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.set_collide_with_areas
     */
    fun setCollideWithAreas(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setCollideWithAreasBind, handle, enable)
    }

    /**
     * If `true`, the query will take `Area2D`s into account.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.is_collide_with_areas_enabled
     */
    fun isCollideWithAreasEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isCollideWithAreasEnabledBind, handle)
    }

    /**
     * If `true`, the query will detect a hit when starting inside shapes. In this case the collision
     * normal will be `Vector2(0, 0)`. Does not affect concave polygon shapes.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.set_hit_from_inside
     */
    fun setHitFromInside(enable: Boolean) {
        ObjectCalls.ptrcallWithBoolArg(setHitFromInsideBind, handle, enable)
    }

    /**
     * If `true`, the query will detect a hit when starting inside shapes. In this case the collision
     * normal will be `Vector2(0, 0)`. Does not affect concave polygon shapes.
     *
     * Generated from Godot docs: PhysicsRayQueryParameters2D.is_hit_from_inside_enabled
     */
    fun isHitFromInsideEnabled(): Boolean {
        return ObjectCalls.ptrcallNoArgsRetBool(isHitFromInsideEnabledBind, handle)
    }

    companion object {
        /**
         * Returns a new, pre-configured `PhysicsRayQueryParameters2D` object. Use it to quickly create
         * query parameters using the most common options.
         *
         * Generated from Godot docs: PhysicsRayQueryParameters2D.create
         */
        fun create(from: Vector2, to: Vector2, collisionMask: Long = 4294967295L, exclude: List<RID>): PhysicsRayQueryParameters2D? {
            return PhysicsRayQueryParameters2D.wrap(ObjectCalls.ptrcallWithTwoVector2UInt32RIDListArgsRetObject(createBind, MemorySegment.NULL, from, to, collisionMask, exclude))
        }

        @JvmStatic
        fun fromHandle(handle: MemorySegment): PhysicsRayQueryParameters2D? =
            wrap(handle)

        internal fun wrap(handle: MemorySegment): PhysicsRayQueryParameters2D? =
            if (handle.address() == 0L) null else PhysicsRayQueryParameters2D(handle)

        private const val CREATE_HASH = 3196569324L
        private val createBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "create", CREATE_HASH)
        }

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

        private const val SET_EXCLUDE_HASH = 381264803L
        private val setExcludeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "set_exclude", SET_EXCLUDE_HASH)
        }

        private const val GET_EXCLUDE_HASH = 3995934104L
        private val getExcludeBind by lazy {
            ObjectCalls.getMethodBind("PhysicsRayQueryParameters2D", "get_exclude", GET_EXCLUDE_HASH)
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
